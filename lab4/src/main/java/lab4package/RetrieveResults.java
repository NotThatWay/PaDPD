package lab4package;

public class RetrieveResults {
    public void setPackageId(String id) {
        this.packageID = id;
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

    private String packageID;

    public String getPackageID() {
        return packageID;
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

    private String functionName;
    private String jsScript;
    private Test[] tests;

    public RetrieveResults(String id, String functionName, String jsScript, Test[] tests) {
        this.packageID = id;
        this.functionName = functionName;
        this.jsScript = jsScript;
        this.tests = tests;
    }

    public RetrieveResults() {}
}
