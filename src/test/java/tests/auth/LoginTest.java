package tests.auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.Routes;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "validLoginCredentials", dataProviderClass = DataProviders.class)
    public void testSuccessfulLogin(String username, String password) {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(username, password);

        String expectedUrl = baseUrl + Routes.INVENTORY;
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Login failed ( " + username + "," + password + ") -> /inventory.html page was not reached");
    }

    @Test(dataProvider = "invalidLoginCredentials", dataProviderClass = DataProviders.class)
    public void testFailedLogin(String username, String password, String expectedErrorMsg) {
        String baseUrl= ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(username, password);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.LOGIN, "Login should have failed, but the test was redirected to a different page: " + currentUrl);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed upon failed login attempt.");

        String errorMsg = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMsg, expectedErrorMsg, "Displayed error message is different than expected message.");

        Assert.assertTrue(loginPage.closeErrorMessagePopupSuccessfully(), "Error when closing an expected error message.");
    }
}
