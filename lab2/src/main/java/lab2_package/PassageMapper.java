package lab2_package;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PassageMapper extends Mapper<LongWritable, Text, PassageWritableComparable, Text> {
    public void map(LongWritable key, Text value, Context context) throws
            IOException, InterruptedException {
        
    }
}
