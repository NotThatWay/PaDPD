package lab2_package;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;


public class AirportPartitioner extends Partitioner<PassageWritableComparable, Text> {

    @Override
    public int getPartition(PassageWritableComparable passageWritableComparable, Text text, int i) {
        return 0;
    }
}
