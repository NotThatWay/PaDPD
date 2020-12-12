package lab4package;

public class ExecuteMessage {
    public String id;
    public String code;
    public String functionName;
    public Test[] tests;

    public ExecuteMessage(String id, String code, String functionName, Test[] tests) {
        this.id = id;
        this.code = code;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String toString() {
        return id;
    }
}
