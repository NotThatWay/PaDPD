package lab2_package;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class MainReducer extends Reducer<PassageWritableComparable, Text, Text, Text> {
    protected void reduce(PassageWritableComparable key, Iterable<Text> values, Context context) throws
            IOException, InterruptedException {
        Iterator iter = values.iterator();
        Text airport_name = new Text(iter.next());
        while (iter.hasNext()) {

        }

    }
}
