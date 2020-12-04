package lab3package;

import java.io.Serializable;
import java.util.Iterator;

public class FlightsSerializable implements Serializable {
    public float maxDelay, percentDelay, percentCancelled;
    public FlightsSerializable countDelays(Iterator<String> flights) {
        float currDelay = 0;
        int delays = 0, cancelled = 0;
        final static String NO_DELAY = "0.00";
        while (flights.hasNext()) {
            String flight = flights.next();
            if (!flight.isEmpty()) {
                cancelled++;
            }
            else if (flight.equals(NO_DELAY)) {

            }
            currDelay = Float.parseFloat(flight);
        }
    }

    public FlightsSerializable(float maxDelay, float percentCancelled, float percentDelay) {
        this.maxDelay = maxDelay;
        this.percentDelay = percentDelay;
        this.percentCancelled = percentCancelled;
    }
}
