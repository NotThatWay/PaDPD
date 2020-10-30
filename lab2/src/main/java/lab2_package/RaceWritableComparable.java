package lab2_package;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public class RaceWritableComparable implements WritableComparable {
    private int airportCode;
    private int flag;

    public RaceWritableComparable() {
        
    }

    public int compareTo(RaceWritableComparable o) {

    }

    public int write(DataOutput out) {

    }

    public void readFields(DataInput dataInput) {

    }

    public int hashCode() {

    }
}
