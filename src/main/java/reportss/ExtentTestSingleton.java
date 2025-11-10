package reportss;

import com.aventstack.extentreports.ExtentTest;

//Singleton approach to initialize a logger for each test and log the steps performed
public final class ExtentTestSingleton {
    private ExtentTestSingleton() {
    }

    private static final ExtentTestSingleton instance = new ExtentTestSingleton();
    private static final ThreadLocal<ExtentTest> loggerTest = new ThreadLocal<>();

    public static ExtentTestSingleton getInstance() {
        return instance;
    }

    public void setExtentTest(ExtentTest loggerT) {
        loggerTest.set(loggerT);
    }

    public ExtentTest getExtentTest() {
        return loggerTest.get();
    }

    public void removeExtentObject() {
        loggerTest.remove();
    }


}
