package lab3package;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {
    private static final String AIRPORTS_FILE = "airports.csv";
    private static final String FLIGHTS_FILE = "passages.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_FILE);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_FILE);
        JavaPairRDD<Long,String> airportPair = 

    }
}

