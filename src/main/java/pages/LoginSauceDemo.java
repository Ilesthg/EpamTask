package pages;

import base.ParallelDriver;
import enums.ExplicitWaitStrategy;
import org.openqa.selenium.By;
import utilities.CustomMethods;

import java.util.HashMap;

public final class LoginSauceDemo extends CustomMethods {

    private static final By inputEmail = By.cssSelector("#user-name");
    private static final String TXTEMAIL = "Email";

    private static final By inputPassword = By.cssSelector("#password");
    private static final String TXTPASSWORD = "Password";


    private static final By buttonIngresar = By.cssSelector("#login-button");
    private static final String BTTNINGRESAR = "Button Login";

    private static final By errorMessage = By.cssSelector("h3[data-test='error']");

    public static final String ERRORMESSAGEUSERNAMEREQUIRED = "Epic sadface: Username is required";
    public static final String PASSWORDREQUIRED = "Epic sadface: Password is required";
    public static final String INVALID_LOGIN = "Epic sadface: Username and password do not match any user in this service";



    public LoginSauceDemo sendUsernamePassword(HashMap<String, String> hashMap) {
        sendKeys(inputEmail, hashMap.get("Username"), TXTEMAIL);
        sendKeys(inputPassword, hashMap.get("Password"), TXTPASSWORD);
        return this;
    }
    public void clickSubmitBttn(){
        click(buttonIngresar,BTTNINGRESAR, ExplicitWaitStrategy.CLICKABLE);

    }

    public LoginSauceDemo clearUsername_Password() {
        clear(inputEmail);
        clear(inputPassword);
        return this;
    }

    public LoginSauceDemo clearPassword() {
        clear(inputPassword);
        return this;
    }

    public String getErrorMessage() {
      return   driver.findElement(errorMessage).getText();
    }

}