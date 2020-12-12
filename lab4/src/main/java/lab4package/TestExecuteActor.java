package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class TestExecuteActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match()
    }
}
