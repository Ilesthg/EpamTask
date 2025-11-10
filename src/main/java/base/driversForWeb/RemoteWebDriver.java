package base.driversForWeb;

import constants.Constants;
import enums.WebPlatform;
import org.openqa.selenium.WebDriver;
import utilities.web.remote.docker.Docker;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class RemoteWebDriver {
    private RemoteWebDriver() {

    }
    private static Map<WebPlatform, Supplier<WebDriver>> MAP = new EnumMap<>(WebPlatform.class);

    static {
        Supplier<WebDriver> DOCKER = Docker::getRemoteDriver;
        MAP.put(WebPlatform.DOCKER, DOCKER);
    }

    public static WebDriver getRemoteDriver() {
        try {
            return MAP.get(Constants.getWebPlatformEnum()).get();
        }catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

 /*   public static WebDriver getRemoteDriver() {//String browser, Object[] data
        try {
            if (WebPlatform.DOCKER == Constants.getWebPlatformEnum()) {
                return Docker.getRemoteDriver();
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
        return null;
    }*/


}
