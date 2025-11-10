package utilities;

import base.ParallelDriver;
import enums.ExplicitWaitStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;
import reportss.ExtentLogger;

public class CustomMethods {
    // private final WebDriver driver2 = ParallelDriver.getInstanceParallelDriver().getWebDriver();
    protected WebDriver driver;
    protected WebDriverWait wait;
    //private ExtentTest logger = ExtentReportsClass.getLoggerFromStaticMethod();

    protected CustomMethods() {
        this.driver = ParallelDriver.getInstanceParallelDriver().getWebDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));

    }

    protected void sendKeys(By by, String valueToSend, String elementDescription) {
        try {
            driver.findElement(by).sendKeys(valueToSend);


            ExtentLogger.passMessage(
                    "Send Keys SUCCESS -> element: " + elementDescription +
                            " | locator: " + by +
                            " | value: " + valueToSend
            );
        } catch (NoSuchElementException e) {
            String msg = "Failed to send keys. Element not found: " + elementDescription +
                    " | locator: " + by;
            ExtentLogger.failMessage(msg + " - " + e.getMessage());
            throw e;
        }
    }

    protected void sendKeys(By by, String valueToSend, String elementDescription, ExplicitWaitStrategy explicitWaitStrategy) {
        try {
            waitStrategy(by, explicitWaitStrategy).sendKeys(valueToSend);//2 option

            ExtentLogger.passMessage(
                    "Send Keys SUCCESS -> element: " + elementDescription +
                            " | locator: " + by +
                            " | value: " + valueToSend
            );
        } catch (NoSuchElementException e) {
            String msg = "Failed to send keys. Element not found: " + elementDescription +
                    " | locator: " + by;
            ExtentLogger.failMessage(msg + " - " + e.getMessage());
            throw e;
        }
    }

    protected void click(WebElement webElement, String element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
            ExtentLogger.passMessage("Click Method SUCCESS, able to click key on element: ->" + element);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on element located by: " + webElement, e);
        }
    }


    protected void click(By by, String element) {
        try {
            driver.findElement(by).click();
            //  System.out.println("Click Method SUCCESS, able to click key on element: ->" + element);
            ExtentLogger.passMessage("Click Method SUCCESS, able to click key on element: ->" + element);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on element located by: " + by, e);
        }
    }


    protected void click(By by, String element, ExplicitWaitStrategy explicitWaitStrategy) {
        try {
            waitStrategy(by, explicitWaitStrategy).click();
            ExtentLogger.passMessage("Click Method SUCCESS, able to click key on element: ->" + element);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on element located by: " + by, e);
        }
    }

    protected void refresh() {
        driver.navigate().refresh();
    }

    protected void clear(By by) {
        try {
            driver.findElement(by).clear();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear element located by: " + by, e);
        }
    }

    protected void getText(By by, String valueToSend) {
        try {

            driver.findElement(by).getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get text on element located by: " + by, e);
        }
    }

    protected void getText(By by, String valueToSend, ExplicitWaitStrategy explicitWaitStrategy) {
        try {
            waitStrategy(by, explicitWaitStrategy).getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get text on element located by: " + by, e);
        }
    }
  /*  protected void switchToAlertAndAccept() {
        try {
            Alert alert = driver.switchTo().alert();
            // Accept the alert
            alert.accept();
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept alert", e);
        }
    }*/

    protected Boolean isDisplayed(By by) {
        boolean flag;
        try {
            flag = false;
            flag = driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Failed to determine if element is displayed located by: " + by, e);
        }
        return flag;
    }

    protected void moveToElement(WebElement element, String elementName) {
        try {
            //WebDriver driver = GetDriverStaticM.getDriverStaticMethod();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("argument[0].scrollIntoView(true);", element);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            //  logger.log(Status.PASS, MarkupHelper.createLabel("Move to Element method SUCCESS,: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            throw new RuntimeException(e);
            //logger.log(Status.FAIL, MarkupHelper.createLabel("Move to Element method FAILED,: " + elementName + " due to exception: " + e, ExtentColor.RED));
        }
    }

    protected static void selectDropDownByVisibleText(WebElement selectWebElement, String elementName, String visibleText) {
        try {
            Select s = new Select(selectWebElement);
            s.selectByVisibleText(visibleText);

            ExtentLogger.passMessage("Select DD by Visible Text method SUCCESS,: " + elementName);
        } catch (Exception e) {
            ExtentLogger.failMessage("Select DD by Visible Text method FAILED,: " + elementName + " due to exception: " + e);
            throw new RuntimeException(e);
        }
    }

    protected static void consumerSelect(WebElement selectWebElement, Consumer<Select> consumer) {
        consumer.accept(new Select(selectWebElement));

    }

    public  WebElement  returnWebElement(By by){
        WebElement element = null;
        try {
            element = driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found: " + by, e);
        }
        return element;
    }


    private WebElement waitStrategy(By by, ExplicitWaitStrategy explicitWaitStrategy) {
        //WebDriver driver = GetDriverStaticM.getDriverStaticMethod();
        if (explicitWaitStrategy == ExplicitWaitStrategy.CLICKABLE) {
            //return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(by));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } else if (explicitWaitStrategy == ExplicitWaitStrategy.PRESENT) {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (explicitWaitStrategy == ExplicitWaitStrategy.VISIBLE) {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        return null;

    }

}
