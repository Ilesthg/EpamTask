package base;

import org.openqa.selenium.WebDriver;

// Using singleton approach to get only one instance
public final class ParallelDriver {

    private ParallelDriver() {
    }

    private static final ParallelDriver parallelDriver = new ParallelDriver();


    public static ParallelDriver getInstanceParallelDriver() {
        return parallelDriver;
    }


    private static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();


    public   WebDriver getWebDriver() {
        if (threadLocalWebDriver.get() == null) {
            throw new RuntimeException("Driver is not initialized. Please call setWebDriver() first.");
        }
        return threadLocalWebDriver.get();
    }

    public void setWebDriver(WebDriver driver) {
        threadLocalWebDriver.set(driver);
    }

    public void closeWebDriver() {
        if (threadLocalWebDriver.get() != null) {
            getWebDriver().quit();
            threadLocalWebDriver.remove();
        }
    }


}
