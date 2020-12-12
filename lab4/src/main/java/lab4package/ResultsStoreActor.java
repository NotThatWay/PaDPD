package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import Message;

public class ResultsStoreActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create().match(Message.class)
    }
}
