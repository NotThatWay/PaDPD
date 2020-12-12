package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecuteActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecuteMessage.class, message -> {
                    System.out.printf("EXECUTE %s\n", message.toString());
                    Result result = ...;
                    sender.tell(new StoredMessage(message.id, ))
                })
    }

    public static Result executeTest(ExecuteMessage message) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval()
    }
}
