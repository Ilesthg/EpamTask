package base;

import base.driversForWeb.WebDFactory;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import constants.Constants;
import enums.Environments;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseDriver {
    protected BaseDriver() {
    }

    private WebDriver driver;

    @BeforeSuite
    public void loadtemplates() {
        FixtureFactoryLoader.loadTemplates("fixtures.template");
    }
    @BeforeMethod
    protected void initDriver() {
        WebDriver driver = WebDFactory.generateWebDriver();


        ParallelDriver.getInstanceParallelDriver().setWebDriver(driver);
        this.driver = ParallelDriver.getInstanceParallelDriver().getWebDriver();
        this.driver.get(Constants.getUrl());
        //this.driver.get(Environments.PROD.getUrl());
        this.driver.manage().window().maximize();

    }
    @AfterMethod
    protected void closeDriver() {
        ParallelDriver.getInstanceParallelDriver().closeWebDriver();

    }
    protected String getUrl() {
        return Constants.getUrl();
    }


}






