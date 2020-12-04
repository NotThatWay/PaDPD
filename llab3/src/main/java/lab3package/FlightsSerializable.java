package lab3package;

import java.io.Serializable;
import java.util.Iterator;

public class FlightsSerializable implements Serializable {
    public float maxDelay, percentDelay, percentCancelled;
    public FlightsSerializable countDelays(Iterator<String> delays) {
        
    }

    public FlightsSerializable(float maxDelay, float percentCancelled, float percentDelay) {
        this.maxDelay = maxDelay;
        this.percentDelay = percentDelay;
        this.percentCancelled = percentCancelled;
    }
}
