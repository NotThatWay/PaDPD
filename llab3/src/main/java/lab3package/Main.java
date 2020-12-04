package lab3package;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main {
    private static final String AIRPORTS_FILE = "airports.csv";
    private static final String FLIGHTS_FILE = "passages.csv";
    private static final String AIRPORTS_TITLE = "Code,Description";
    private static final String AIR_PRE_DELIMETER = ",";
    private static final String AIR_FINAL_DELIMETER = "%::#%;##%#";
    private static final String FLIGHT_DELIMETER = ",";
    private static final int AIRPORT_ID_COLUMN = 0;
    private static final int AIRPORT_NAME_COLUMN = 1;
    private static final String FLIGHTS_TITLE = "\"YEAR\",\"QUARTER\",\"MONTH\",\"DAY_OF_MONTH\",\"DAY_OF_WEEK\"";


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_FILE);
        JavaPairRDD<Integer,String> airportPair = airports.filter(x -> !x.contains(AIRPORTS_TITLE))
                .map(x -> x.replaceFirst(AIR_PRE_DELIMETER, AIR_FINAL_DELIMETER).split(AIR_FINAL_DELIMETER))
                .mapToPair(x -> new Tuple2<>(Integer.parseInt(x[AIRPORT_ID_COLUMN]), x[AIRPORT_NAME_COLUMN]));
        JavaPairRDD<Tuple2<Integer,Integer>,FlightsSerializable> delaysAndCancelled = flights.filter(x -> x.contains(FLIGHTS_TITLE))
                .map(x -> x.split(FLIGHT_DELIMETER)).mapToPair(x -> )
    }
}

