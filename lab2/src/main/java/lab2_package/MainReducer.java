package lab2_package;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MainReducer extends Reducer<PassageWritableComparable, Text, Text, Text> {
    protected void reduce(PassageWritableComparable key, Iterable<Text> values, Context context) throws
            IOException, InterruptedException {


    }
}
