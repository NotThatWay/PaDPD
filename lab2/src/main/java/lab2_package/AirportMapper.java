package lab2_package;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, RaceWritableComparable, Text>  {
    private static final String COMMA = ",";
    private static final String VERTICAL_SLASH = "|";

    public void map(LongWritable key, Text value, Context context) throws
            IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.replaceFirst(COMMA, VERTICAL_SLASH).split(VERTICAL_SLASH);
        if (key.get() != 0) {
            context.write(RaceWritableComparable());
        }
    }
}
