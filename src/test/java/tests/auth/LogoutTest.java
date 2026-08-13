package tests.auth;

import base.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DefaultUserCredentials;
import utils.Routes;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);

        String mainWindow = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get(baseUrl + Routes.INVENTORY);
        Assert.assertEquals(getDriver().getCurrentUrl(), baseUrl + Routes.INVENTORY, "Logged-in user was redirected when opening a new tab.");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isHamburgerMenuButtonDisplayed(), "Hamburger menu button is not displayed.");

        inventoryPage.clickHamburgerMenuButton();
        Assert.assertTrue(inventoryPage.isLogoutButtonDisplayed(), "Logout button is not displayed.");

        inventoryPage.clickLogoutButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), baseUrl, "Logout failed: Logged-out user was not redirected to login page.");

        getDriver().switchTo().window(mainWindow);
        getDriver().navigate().refresh();
        Assert.assertEquals(getDriver().getCurrentUrl(), baseUrl, "Session sync failed: Logged-out user was not signed-out from all tabs.");
    }
}
