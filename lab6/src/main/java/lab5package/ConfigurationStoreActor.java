package lab5package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ConfigurationStoreActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(ServersList.class)

    }
}
