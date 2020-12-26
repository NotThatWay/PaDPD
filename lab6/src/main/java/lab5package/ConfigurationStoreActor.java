package lab5package;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.Random;

public class ConfigurationStoreActor extends AbstractActor {
    ArrayList<String> servers = new ArrayList<>();
    Random random = new Random();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(ServersList.class, message -> servers = message.servers)
                .match(ServerQuery.class, message -> getSender().tell(servers.get(random.nextInt(servers.size())), ActorRef.noSender()))
                .build();
    }
}
