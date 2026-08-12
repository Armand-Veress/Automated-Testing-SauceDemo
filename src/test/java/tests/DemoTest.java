package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class DemoTest extends BaseTest {

    @Test
    public void testLoadLoginPage() {
        getDriver().get(ConfigReader.getProperty("BASE_URL"));
        String title = getDriver().getTitle();
        Assert.assertEquals(title, "Swag Labs", "The page title is wrong");
    }

    @Test
    public void testLoginSuccessfully() {
        getDriver().get(ConfigReader.getProperty("BASE_URL"));

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        boolean isCartDisplayed = inventoryPage.isShoppingCartDisplayed();

        Assert.assertTrue(isCartDisplayed, "Login failed. Expected cart button was not displayed");
    }
}