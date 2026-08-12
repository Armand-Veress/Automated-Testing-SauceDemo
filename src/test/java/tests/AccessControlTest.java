package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class AccessControlTest extends BaseTest {

    @Test
    public void checkBaseUrl(){
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl);
        String currentUrl = getDriver().getCurrentUrl();
        String title = getDriver().getTitle();
        Assert.assertEquals(currentUrl, baseUrl, "Base url not reached. Test was redirected to: " + currentUrl);
        Assert.assertEquals(title, LoginPage.EXPECTED_TITLE, "The page title is wrong wrong: " + title);
    }

    @Test
    public void testAccessLoggedOut(){
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl);
        String[] pages = {"inventory.html", "cart.html"};
        for(String page : pages) {
            getDriver().get(baseUrl + page);

            String currentUrl = getDriver().getCurrentUrl();
            LoginPage loginPage = new LoginPage(getDriver());
            Assert.assertEquals(currentUrl, baseUrl, "LoggedOut User was not redirected");

            String expectedError = String.format(LoginPage.ACCESS_DENIED_MSG_TEMPLATE, "/" + page);
            String errorMsg = loginPage.getErrorMessageText();
            Assert.assertEquals(errorMsg, expectedError, "Access denied error response is wrong");
        }
    }
}
