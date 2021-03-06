package lab3package;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import java.util.Map;

public class Main {
    private static final String AIRPORTS_TITLE = "Code,Description";
    private static final String AIR_PRE_DELIMETER = ",";
    private static final String AIR_FINAL_DELIMETER = "%::#%;##%#";
    private static final String FLIGHT_DELIMETER = ",";
    private static final int AIRPORT_ID_COLUMN = 0;
    private static final int AIRPORT_NAME_COLUMN = 1;
    private static final String FLIGHTS_TITLE = "\"YEAR\",\"QUARTER\",\"MONTH\",\"DAY_OF_MONTH\",\"DAY_OF_WEEK\"";
    private static final int AIRPORT_ORIGIN = 11;
    private static final int AIRPORT_DESTINATION = 14;
    private static final int DELAY = 18;
    private static final String QUOTES = "\"";



    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: Main <passages path> <airports path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(args[1]);
        JavaRDD<String> flights = sc.textFile(args[0]);
        JavaPairRDD<Integer,String> airportPair = airports.filter(x -> !x.contains(AIRPORTS_TITLE))
                .map(x -> x.replaceFirst(AIR_PRE_DELIMETER, AIR_FINAL_DELIMETER).replaceAll(QUOTES, "").split(AIR_FINAL_DELIMETER))
                .mapToPair(x -> new Tuple2<>(Integer.parseInt(x[AIRPORT_ID_COLUMN]), x[AIRPORT_NAME_COLUMN]));
        JavaPairRDD<Tuple2<Integer,Integer>,FlightsSerializable> delaysAndCancelled = flights.filter(x -> !x.contains(FLIGHTS_TITLE))
                .map(x -> x.split(FLIGHT_DELIMETER))
                .mapToPair(x -> new Tuple2<>(new Tuple2<>(Integer.parseInt(x[AIRPORT_ORIGIN]), Integer.parseInt(x[AIRPORT_DESTINATION])), x[DELAY]))
                .groupByKey().mapValues(x -> FlightsSerializable.countDelays(x.iterator()));
        Map<Integer,String> stringAirportDataMap = airportPair.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(stringAirportDataMap);
        JavaRDD<FlightsSerializable> airportsDelayInfo = delaysAndCancelled
                .map(x -> x._2.addAirportsNames(airportsBroadcasted.value(), x._1));
        airportsDelayInfo.saveAsTextFile("output3");
    }
}

