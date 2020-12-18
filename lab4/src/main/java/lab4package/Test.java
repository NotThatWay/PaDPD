package lab4package;

public class Test {
    public String testName;
    public String expectedResult;
    public Object[] params;

    public Test(String testName, String expectedResult, Object[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public Test() {}

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
