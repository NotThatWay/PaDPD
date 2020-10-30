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
        float maxDelay = 0;
        float minDelay = Float.MAX_VALUE;
        float sum = 0;
        int number = 0;
//        Text a = iter.next();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
                float currDelay = Float.parseFloat(iter.next().toString());
                if (currDelay > maxDelay) {
                    maxDelay = currDelay;
                }
                if (currDelay < minDelay) {
                    minDelay = currDelay;
                }
                sum += currDelay;
                number++;
            }
            float avgDelay = sum / number;

            context.write(airportName, new Text(minDelay + ", " + maxDelay + ", " + avgDelay));
        }
    }
}
