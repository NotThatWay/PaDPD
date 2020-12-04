package lab3package;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main {
    private static final String AIRPORTS_FILE = "airports.csv";
    private static final String FLIGHTS_FILE = "passages.csv";
    private static final String TITLE = "Code,Description";
    private static final String PRE_DELIMETER = ",";
    private static final String FINAL_DELIMETER = "%::#%;##%#";
    private static final int AIRPORT_ID_COLUMN = 0;
    private static final int AIRPORT_NAME_COLUMN = 1;


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_FILE);
        JavaPairRDD<Integer,String> airportPair = airports.filter(x -> !x.contains(TITLE))
                .map(x -> x.replaceFirst(PRE_DELIMETER, FINAL_DELIMETER).split(FINAL_DELIMETER))
                .mapToPair(x -> new Tuple2<>(Integer.parseInt(x[AIRPORT_ID_COLUMN]), x[AIRPORT_NAME_COLUMN]));
        JavaPairRDD<Tuple2<Integer,Integer>,FlightsSerializable> delaysAndCancelled = 
    }
}

