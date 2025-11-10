package base.driversForWeb;

import constants.Constants;
import enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.BrowserManagers.ChromeManager;
import utilities.BrowserManagers.EdgeManager;
import utilities.BrowserManagers.FirefoxManager;

import java.util.HashMap;
import java.util.function.Supplier;

public final class LocalWebDriver {

    private LocalWebDriver() {
    }


        public static WebDriver getLocalDriver() {//String browser, Object[] data

            try {
                HashMap<Browsers, Supplier<WebDriver>> hmm = new HashMap<>();

                hmm.put(Browsers.CHROME, ChromeManager::chromeManagerLocal);
                hmm.put(Browsers.EDGE, EdgeManager::edgeManagerLocal);
                hmm.put(Browsers.FIREFOX, FirefoxManager::firefoxManagerLocal);
                return hmm.get(Constants.getBrowserEnum()).get();

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
            }
        }

}

