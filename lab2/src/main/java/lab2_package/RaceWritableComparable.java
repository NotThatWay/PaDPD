package lab2_package;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public class RaceWritableComparable implements WritableComparable {
    private int airportCode;
    private int flag;

    public RaceWritableComparable(int airportCode, int flag) {
        this.airportCode = airportCode;
        this.flag = flag;
    }

    public int getCode() {
        return this.airportCode;
    }

    public int getFlag() {
        return this.flag;
    }

    public int compareTo(RaceWritableComparable o) {
        if (this.getCode() > o.getCode()) {
            return 1;
        }
        else if (this.getCode() < o.getCode()) {
            return -1;
        }
        else {
            
        }
    }

    public int write(DataOutput out) {

    }

    public void readFields(DataInput dataInput) {

    }

    public int hashCode() {

    }
}
