package org.tests;

import base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginBehaviours;
import pages.LoginSauceDemo;
import utilities.data_providers.DataProviderFiltered;

import java.util.HashMap;

import static pages.LoginSauceDemo.*;
public class LoginTest extends BaseDriver {

    @Test(dataProvider = "Data",  dataProviderClass = DataProviderFiltered.class)
    public void testLogin(HashMap<String, String> testData){

        LoginSauceDemo loginPage = new LoginSauceDemo();
        HomePage homePage = new HomePage();

        // Get scenario and execute
        LoginBehaviours scenario = LoginBehaviours.fromTestData(testData);
        scenario.execute(loginPage, homePage, testData);
    }

}
