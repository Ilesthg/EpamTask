package pages;

import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

public enum LoginBehaviours {
    VALID("Valid") {
        @Override
        public void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data) {

            loginPage.sendUsernamePassword(data);
            loginPage.clickSubmitBttn();

            assertThat(dashboard.getHomePageTittle()).isEqualTo(HomePage.TITTLE_HOME);
        }
    },
    EMPTY_USERNAME("Empty Username") {
        @Override
        public void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data) {

            loginPage.sendUsernamePassword(data);
            loginPage.clearUsername_Password();
            loginPage.clickSubmitBttn();

            assertThat(loginPage.getErrorMessage())
                    .isEqualTo("Epic sadface: Username is required");
        }
    },
    EMPTY_PASSWORD("Empty Password") {
        @Override
        public void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data) {

            loginPage.sendUsernamePassword(data);
            loginPage.clearPassword();
            loginPage.clickSubmitBttn();

            assertThat(loginPage.getErrorMessage())
                    .isEqualTo("Epic sadface: Password is required");
        }
    },
    INVALID("Invalid") {
        @Override
        public void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data) {

            loginPage.sendUsernamePassword(data);
            loginPage.clearPassword();
            loginPage.clickSubmitBttn();

            assertThat(loginPage.getErrorMessage())
                    .isEqualTo("Epic sadface: Username and password do not match any user in this service");
        }
    },
    LOCKED("Locked") {
        public void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data) {

            loginPage.sendUsernamePassword(data);
            loginPage.clearPassword();
            loginPage.clickSubmitBttn();

            assertThat(loginPage.getErrorMessage())
                    .isEqualTo("Epic sadface: Sorry, this user has been locked out.");
        }
    };

    private final String expectedOutcome;

    LoginBehaviours(String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    /**
     * Factory method to get the right behavior based on test data
     */
    public static LoginBehaviours fromTestData(HashMap<String, String> testData) {
        String expectedOutcome = testData.get("expectedOutcome");

        if (expectedOutcome == null || expectedOutcome.isEmpty()) {
            throw new IllegalArgumentException("expectedOutcome is missing in test data");
        }

        for (LoginBehaviours behavior : values()) {
            if (behavior.expectedOutcome.equalsIgnoreCase(expectedOutcome)) {
                return behavior;
            }
        }
        throw new IllegalArgumentException("Unknown expected outcome: " + expectedOutcome);
    }

    public abstract void execute(LoginSauceDemo loginPage, HomePage dashboard, HashMap<String, String> data);
}