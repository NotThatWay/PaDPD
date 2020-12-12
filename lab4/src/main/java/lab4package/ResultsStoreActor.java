package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ResultsStoreActor extends AbstractActor {
    private final Map<String,Result> results = new HashMap<String,Result>();

    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(StoredMessage.class, message -> {
                    System.out.println(message.toString());
                    results.put(message.id, message.result);
                })
                .match(RetrievedMessage.class, message -> {
                    System.out.println(message.toString());
                    sender().tell(new StoredMessage(message.id, results.(message.id)), self());
                }).build();
    }
}
