package lab4package;

public class RetrieveResults {
    public String id;
    public String functionName;
    public String jsScript;
    public Test[] tests;

    public RetrieveResults(String id, String functionName, String jsScript, Test[] tests) {
        this.id = id;
        this.functionName = functionName;
        this.jsScript = jsScript;
        this.tests = tests;
    }

    public RetrieveResults() {}
}
