package lab5package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CacheActor extends AbstractActor {
    public HashMap<String, Integer> cache = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create().match(ReceiveMessage.class, message -> {
            getSender().tell()
        });
    }
}
