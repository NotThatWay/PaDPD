package lab5package;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CacheActor extends AbstractActor {
    public HashMap<String, Long> cache = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create().match(ReceiveMessage.class, message -> {
            getSender().tell(cache.getOrDefault(message.url, (long)-1), ActorRef.noSender());
        }).match(StoredMessage.class, message -> {
            cache.putIfAbsent(message.url, message.time);
        }).build();
    }
}
