package lab5package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class CacheActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create().match();
    }
}
