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
                    sender().tell(new StoredMessage(message.id, result), self());
                }).build();
    }

    public static Result executeTest(ExecuteMessage message) throws ScriptException, NoSuchMethodException {
        Result result = new Result();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(message.code);
        Invocable invocable = (Invocable) engine;
        for (Test test : message.tests) {
            String testResult = invocable.invokeFunction(message.functionName, test.getParams()).toString();
            if (testResult.equals(test.getExpectedResult())) {
                System.out.printf("%s: %s,  SUCCESS!", message.id, test.getTestName());
                result.results.put(test.getTestName(), true);
            }
            else {
                System.out.printf("%s: %s,  FAILURE!", message.id, test.getTestName());
                result.results.put(test.getTestName(), false);
            }
        }
        return result;
    }
}
