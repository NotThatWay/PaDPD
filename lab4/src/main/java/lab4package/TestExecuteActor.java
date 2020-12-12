package lab4package;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestExecuteActor extends AbstractActor {
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ExecuteMessage.class, message -> {
                    System.out.printf("EXECUTE %s\n", message.toString());
                    Result result = executeTest(message);
                    sender().tell(new StoredMessage(message.id, ))
                })
    }

    public static Result executeTest(ExecuteMessage message) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(message.code);
        Invocable invocable = (Invocable) engine;
        for (Test test : message.tests) {
            String testResult = invocable.invokeFunction(message.functionName, test.params).toString();
            if (testResult.equals(test.expectedResult)) {
                System.out.printf("%s: %s,  SUCCESS!", message.id, test.testName);
            }
            else {
                System.out.printf("%s: %s,  FAIL!", message.id, test.testName);
            }
        }
        return
    }
}
