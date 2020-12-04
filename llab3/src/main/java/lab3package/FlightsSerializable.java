package lab3package;

import java.io.Serializable;
import java.util.Iterator;

public class FlightsSerializable implements Serializable {
    public float maxDelay, percentDelay, percentCancelled;
    private final static String NO_DELAY = "0.00";
    public FlightsSerializable countDelays(Iterator<String> flights) {
        float currDelay = 0;
        int delays = 0, cancelled = 0, flightsNumber = 0;

        while (flights.hasNext()) {
            String flight = flights.next();
            flightsNumber++;
            if (!flight.isEmpty()) {
                cancelled++;
            }
            else if (!flight.equals(NO_DELAY)) {
                delays++;
                if (currDelay < Float.parseFloat(flight)) {
                    currDelay = Float.parseFloat(flight);
                }
            }
        }
        return new FlightsSerializable(currDelay,
                (float)cancelled/flightsNumber*100,
                (float)delays/flightsNumber*100);
    }

    public FlightsSerializable(float maxDelay, float percentCancelled, float percentDelay) {
        this.maxDelay = maxDelay;
        this.percentDelay = percentDelay;
        this.percentCancelled = percentCancelled;
    }
}
