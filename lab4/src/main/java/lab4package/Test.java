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
}
