package lab5package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;

public class ConfigurationStoreActor extends AbstractActor {
    ArrayList<String> servers = new ArrayList<String>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(ServersList.class, message -> {
                servers = message.servers; }).match()

    }
}
