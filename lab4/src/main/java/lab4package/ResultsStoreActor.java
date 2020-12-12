package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4package.Message;

public class ResultsStoreActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(Message.class, message -> {
                    System.out.printf(message.toString());
                })
    }
}
