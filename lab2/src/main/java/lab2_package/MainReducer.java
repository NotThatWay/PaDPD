package lab2_package;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class MainReducer extends Reducer<PassageWritableComparable, Text, Text, Text> {
    protected void reduce(PassageWritableComparable key, Iterable<Text> values, Context context) throws
            IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text airportName = new Text(iter.next());
        float minDelay = 0;
        float maxDelay = Float.MAX_VALUE;
        while (iter.hasNext()) {
            if (iter.)
        }

    }
}
