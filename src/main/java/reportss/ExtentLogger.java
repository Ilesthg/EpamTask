package reportss;
import com.aventstack.extentreports.ExtentTest;
import reportss.ExtentTestSingleton;

import java.util.Optional;

public final class ExtentLogger {

    private ExtentLogger() {}

    private static Optional<ExtentTest> getLogger() {
        return Optional.ofNullable(
                ExtentTestSingleton.getInstance().getExtentTest()
        );
    }

    public static void passMessage(String message) {
        getLogger().ifPresent(test -> test.pass(message));
    }

    public static void failMessage(String message) {
        getLogger().ifPresent(test -> test.fail(message));
    }

    public static void skipMessage(String message) {
        getLogger().ifPresent(test -> test.skip(message));
    }

    public static void addAuthor(String author) {
        getLogger().ifPresent(test -> test.assignAuthor(author));
    }
}