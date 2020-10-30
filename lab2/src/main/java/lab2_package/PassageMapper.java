package lab2_package;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PassageMapper extends Mapper<LongWritable, Text, PassageWritableComparable, Text> {
    private static final String COMMA = ",";
    private static final int DEST_AIPORT_ID_NUMBER = 14;
    private static final int ARR_DELAY_NEW_NUMBER = 18;
    private static final String NO_DELAY = "0.00";

    public void map(LongWritable key, Text value, Context context) throws
            IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(COMMA);
        if (key.get() != 0 && words[ARR_DELAY_NEW_NUMBER] != NO_DELAY) {
            context.write(new PassageWritableComparable(Integer.parseInt(words[ARR_DELAY_NEW_NUMBER]), 1), new Text(words[DEST_AIPORT_ID_NUMBER]));
        }
    }
}
