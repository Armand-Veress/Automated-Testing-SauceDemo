package tests.functional;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.components.CartItemCardComponent;
import pages.components.ProductCardComponent;
import utils.ConfigReader;
import utils.DefaultUserCredentials;
import utils.Routes;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void testCartItems() {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        for (int i=0; i < 6; i+=2) {
            ProductCardComponent it = inventoryPage.getProductById(i);
            Assert.assertTrue(it.isAddToCartButtonDisplayed());
            it.clickAddToCart();
        }
        Assert.assertTrue(inventoryPage.getHeaderComponent().isShoppingCartBadgeDisplayed());
        int selectedItemsNumber = inventoryPage.getHeaderComponent().getShoppingCartBadgeNumber();
        Assert.assertEquals(selectedItemsNumber, 3, "Shopping cart badge was not updated with the number of selected inventory items.");

        Assert.assertTrue(inventoryPage.getHeaderComponent().isShoppingCartDisplayed(), "Shopping cart icon is not displayed");
        inventoryPage.getHeaderComponent().clickShoppingCartButton();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CART, "Cart page was not reached. Current url: " + currentUrl);

        CartPage cartPage = new CartPage(getDriver());
        List<CartItemCardComponent> cartItems = cartPage.getAllCartItems();
        Assert.assertEquals(cartPage.getHeaderComponent().getShoppingCartBadgeNumber(), selectedItemsNumber, "The shopping cart badge number does not correspond between inventory page and cart page.");
        Assert.assertEquals(cartItems.size(), selectedItemsNumber, "Number of cart items listed does not correspond with number of items selected");

        Assert.assertTrue(cartItems.get(1).isRemoveFromCartButtonDisplayed(), "Remove button is missing from cart item card.");
        cartItems.get(1).clickRemove();
        selectedItemsNumber--;
        cartItems = cartPage.getAllCartItems();
        Assert.assertEquals(cartItems.size(), selectedItemsNumber, "Error when removing cart item. List size should have decreased with 1");
        Assert.assertTrue(cartPage.getHeaderComponent().isShoppingCartBadgeDisplayed());
        Assert.assertEquals(cartPage.getHeaderComponent().getShoppingCartBadgeNumber(), selectedItemsNumber, "Shopping cart badge number was not decreased when removing a cart item.");

        Assert.assertTrue(cartPage.isGoToCheckoutButtonDisplayed());
        cartPage.clickGoToCheckoutButton();
        currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl + Routes.CHECKOUT_STEP_ONE, "Checkout's first step was not reached. Current url: " + currentUrl);
    }
}
