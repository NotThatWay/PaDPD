package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import lab4package.Message;
import lab4package.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultsStoreActor extends AbstractActor {
    private final Map<String,Result> results = new HashMap<String,Result>();

    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(Message.class, message -> {
                    System.out.printf(message.toString());
                    results.put(message.id, message.result);
                })
                .match()
    }
}
