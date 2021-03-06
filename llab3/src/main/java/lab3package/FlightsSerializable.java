package lab3package;

import scala.Tuple2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class FlightsSerializable implements Serializable {
    public float maxDelay, percentDelay, percentCancelled;
    public float originAirportID, destinationAirportID;
    public String originAirportName, destinationAirportName;

    private final static String NO_DELAY = "0.00";
    public static FlightsSerializable countDelays(Iterator<String> flights) {
        float currDelay = 0;
        int delays = 0, cancelled = 0, flightsNumber = 0;

        while (flights.hasNext()) {
            String flight = flights.next();
            flightsNumber++;
            if (flight.isEmpty()) {
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

    public FlightsSerializable addAirportsNames(Map<Integer,String> airports, Tuple2<Integer,Integer> airportIDs) {
        originAirportID = airportIDs._1;
        destinationAirportID = airportIDs._2;
        originAirportName = airports.get(airportIDs._1);
        destinationAirportName = airports.get(airportIDs._2);
        return this;
    }

    public FlightsSerializable(float maxDelay, float percentCancelled, float percentDelay) {
        this.maxDelay = maxDelay;
        this.percentDelay = percentDelay;
        this.percentCancelled = percentCancelled;
    }

    public String toString() {
        return originAirportName + " -> " + destinationAirportName +
                "  MaxDelay: " + maxDelay + "  DelaysPercentage: " + percentDelay +
                "  CancelledPercentage " + percentCancelled;
    }
}
