package lab4package;

public class RetrieveResults {
    public void setId(String id) {
        this.id = id;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    public void setTests(Test[] tests) {
        this.tests = tests;
    }

    public String id;

    public String getId() {
        return id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }

    public Test[] getTests() {
        return tests;
    }

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
