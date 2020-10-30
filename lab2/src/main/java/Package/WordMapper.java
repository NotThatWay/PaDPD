package Package;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    protected static final String reg_spec_symbols = "[.,:;\\[\\]\\)\\(—!?»…]";
    protected static final String DELIMETER = " ";
    protected void map(LongWritable key, Text value, Context context) throws
            IOException, InterruptedException {
        String line = value.toString();
        String[] words = removeSpecialSymbols(line).toLowerCase().split(DELIMETER);
        

        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }

    private static String removeSpecialSymbols(String line) {
        return line.replaceAll(reg_spec_symbols, DELIMETER);
    }
}