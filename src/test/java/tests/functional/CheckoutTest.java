package tests.functional;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pages.components.ProductCardComponent;
import utils.ConfigReader;
import utils.DataProviders;
import utils.DefaultUserCredentials;
import utils.Routes;

public class CheckoutTest extends BaseTest {

    @Test(dataProvider = "checkoutData", dataProviderClass = DataProviders.class)
    public void testCheckoutDataValidation(String firstName, String lastName, String postalCode, boolean validData, String expectedErrorMsg) {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.getHeaderComponent().clickShoppingCartButton();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickGoToCheckoutButton();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_ONE, "Checkout's first step was not reached. Reached url: " + currentUrl);

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(getDriver());
        checkoutStepOnePage.continueWithCheckoutData(firstName, lastName, postalCode);

        if(validData) {
            currentUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_TWO, "Checkout's second step was not reached. Reached url: " + currentUrl);
        } else {
            currentUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_ONE, "User should not have been redirected ans should have remained at checkout step one. Reached url: " + currentUrl);

            Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed());
            String errorMsg = checkoutStepOnePage.getErrorMessageText();
            Assert.assertEquals(errorMsg, expectedErrorMsg, "Displayed error message is different than expected message.");
            Assert.assertTrue(checkoutStepOnePage.closeErrorMessagePopupSuccessfully(), "Error when closing an expected error message.");
        }
    }

    @Test
    public void testOverviewSubtotalAndFinishCheckout() {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        double subtotal = 0;
        for (int i=0; i < 6; i+=2) {
            ProductCardComponent it = inventoryPage.getProductById(i);
            it.clickAddToCart();
            subtotal += it.getProductPrice();
        }

        inventoryPage.getHeaderComponent().clickShoppingCartButton();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickGoToCheckoutButton();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_ONE, "Checkout's first step was not reached. Reached url: " + currentUrl);

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(getDriver());
        checkoutStepOnePage.continueWithCheckoutAs(DefaultUserCredentials.STANDARD_USER);
        currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_TWO, "Checkout's second step was not reached. Reached url: " + currentUrl);

        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(getDriver());
        Assert.assertEquals(checkoutStepTwoPage.getSubtotal(), subtotal, "Item subtotal displayed does not correspond with the sum of selected product prices.");

        checkoutStepTwoPage.clickFinishCheckoutButton();
        currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_COMPLETE, "Checkout-complete page was not reached. Current url: " + currentUrl);

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(getDriver());
        Assert.assertEquals(checkoutCompletePage.getStatusTitle(), "Checkout: Complete!", "Checkout is not complete. A different title-message is displayed: " + checkoutCompletePage.getStatusTitle());
    }
}
