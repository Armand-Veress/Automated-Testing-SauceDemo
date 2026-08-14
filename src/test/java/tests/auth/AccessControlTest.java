package tests.auth;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.ErrorMessages;
import utils.Routes;

public class AccessControlTest extends BaseTest {

    @Test
    public void checkBaseUrl(){
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl);
        String currentUrl = getDriver().getCurrentUrl();
        String title = getDriver().getTitle();
        Assert.assertEquals(currentUrl, baseUrl + Routes.LOGIN, "Base url not reached. Test was redirected to: " + currentUrl);
        Assert.assertEquals(title, LoginPage.EXPECTED_TITLE, "The page title is wrong wrong: " + title);
    }

    @Test(dataProvider = "protectedPages", dataProviderClass = DataProviders.class)
    public void testAccessLoggedOut(String page){
        String baseUrl = ConfigReader.getProperty("BASE_URL");

        getDriver().get(baseUrl + page);

        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(3));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(baseUrl + Routes.LOGIN));

        String currentUrl = getDriver().getCurrentUrl();
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertEquals(currentUrl, baseUrl + Routes.LOGIN, "LoggedOut User was not redirected to LogIn");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed upon failed login attempt.");

        String expectedError = String.format(ErrorMessages.ACCESS_DENIED_TEMPLATE.getMessage(), "/" + page);
        String errorMsg = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMsg, expectedError, "Access denied error response is wrong");

        Assert.assertTrue(loginPage.closeErrorMessagePopupSuccessfully(), "Error when closing an expected error message.");
    }
}
