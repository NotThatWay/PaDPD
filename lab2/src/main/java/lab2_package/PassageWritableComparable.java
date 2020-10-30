package lab2_package;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public class PassageWritableComparable implements WritableComparable {
    private int airportCode;
    private int flag;

    public PassageWritableComparable(int airportCode, int flag) {
        this.airportCode = airportCode;
        this.flag = flag;
    }

    public int getCode() {
        return this.airportCode;
    }

    public int getFlag() {
        return this.flag;
    }

    public int compareTo(PassageWritableComparable o) {
        if (this.getCode() > o.getCode()) {
            return 1;
        }
        else if (this.getCode() < o.getCode()) {
            return -1;
        }
        else {
            if (this.getFlag() < o.getFlag()) {
                return -1;
            }
            else if (this.getFlag() > o.getFlag()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public int write(DataOutput out) {
        out.writeInt(airportCode);
        out.writeInt(flag);
    }

    public void readFields(DataInput in) {
        airportCode = in.readInt();
        flag = in.readInt();
    }

    public int hashCode() {

    }
}