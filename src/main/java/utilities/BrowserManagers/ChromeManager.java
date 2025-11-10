package utilities.BrowserManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeManager {
    private ChromeManager(){}
    public static WebDriver chromeManagerLocal(){
        try {
            WebDriverManager.chromedriver().setup();
            // Create ChromeOptions to configure browser settings
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-features=PasswordBreachDetection");
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Local ChromeDriver: " + e.getMessage(), e);
        }
    }
    public static WebDriver chromeManagerRemote(){
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        // capabilities.setCapability("browserVersion", "114.0");   //
        capabilities.setCapability("acceptInsecureCerts", true);
        //capabilities.setCapability("platformName", "WINDOWS");
        /*        capabilities.setCapability("goog:chromeOptions", new HashMap<String, Object>() {{
                    put("args", Arrays.asList("--headless", "--incognito"));
                }});
        */

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to initialize Remote ChromeDriver session: " + e.getMessage(), e);
        }
    }
}
