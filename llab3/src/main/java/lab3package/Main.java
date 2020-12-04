package lab3package;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {
    private static final String AIRPORTS_FILE = "airports.csv";
    private static final String FLIGHTS_FILE = "passages.csv";
    private static final String TITLE = "Code,Description";
    private static final String PRE_DELIMETER = ",";
    private static final String FINAL_DELIMETER = "$%#$%#$#%#";


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_FILE);
        JavaPairRDD<Long,String> airportPair = airports.filter(x -> !x.contains(TITLE))
                .map(x -> x.replaceFirst(PRE_DELIMETER, FINAL_DELIMETER).split(FINAL_DELIMETER))
                .mapToPair()

    }
}

