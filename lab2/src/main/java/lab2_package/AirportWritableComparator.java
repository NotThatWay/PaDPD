package lab2_package;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportWritableComparator extends WritableComparator {
    public AirportWritableComparator() {
        super(PassageWritableComparable.class, true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        PassageWritableComparable first = (PassageWritableComparable) a ;
        PassageWritableComparable second = (PassageWritableComparable) b ;
        if (first.getCode() > second.getCode()) {
            return 1;
        }
        else if (first.getCode() < second.getCode()) {
            return -1;
        }
        return 0;
    }
}
