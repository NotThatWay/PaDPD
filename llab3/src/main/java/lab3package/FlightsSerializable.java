package lab3package;

import java.io.Serializable;
import java.util.Iterator;

public class FlightsSerializable implements Serializable {
    public float maxDelay, percentDelay, percentCancelled;
    public FlightsSerializable countDelays(Iterator<String> flights) {
        float currDelay = 0;
        int delays = 0, cancelled = 0;
        while (flights.hasNext()) {
            String flight = flights.next();
            if (currDelay < Float.parseFloat(flight)) {
                if (!flight.isEmpty()) {
                    cancelled++;
                }
                else if (delay)
                currDelay = Float.parseFloat(flight);
            }
        }
    }

    public FlightsSerializable(float maxDelay, float percentCancelled, float percentDelay) {
        this.maxDelay = maxDelay;
        this.percentDelay = percentDelay;
        this.percentCancelled = percentCancelled;
    }
}
