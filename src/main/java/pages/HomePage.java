package pages;

import org.openqa.selenium.By;
import utilities.CustomMethods;

public final class HomePage extends CustomMethods {

    private static final By titleHomePage = By.cssSelector(".app_logo");
    public static final String TITTLE_HOME = "Swag Labs";

    public String getHomePageTittle() {
        return driver.findElement(titleHomePage).getText();
    }

}
