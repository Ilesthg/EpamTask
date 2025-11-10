package utilities.web.remote.docker;

import constants.Constants;
import enums.Browsers;
import org.openqa.selenium.WebDriver;
import utilities.BrowserManagers.ChromeManager;
import utilities.BrowserManagers.EdgeManager;

import java.util.HashMap;
import java.util.function.Supplier;

public final class Docker {
    private Docker() {
    }

    public static WebDriver getRemoteDriver() {//String browser, Object[] data
        HashMap<Browsers, Supplier<WebDriver>> hmm = new HashMap<>();
        hmm.put(Browsers.CHROME, ChromeManager::chromeManagerRemote);
        hmm.put(Browsers.EDGE, EdgeManager::edgeManagerRemote);


        return hmm.get(Constants.getBrowserEnum()).get();
    }
}
