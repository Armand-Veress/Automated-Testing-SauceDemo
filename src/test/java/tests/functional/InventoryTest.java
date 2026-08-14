package tests.functional;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.components.ProductCardComponent;
import utils.ConfigReader;
import utils.DefaultUserCredentials;
import utils.Routes;
import utils.SortOptions;

import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void testAddToAndRemoveFromCart() {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        List<ProductCardComponent> items =  inventoryPage.getAllProducts();
        int i=0;
        for(ProductCardComponent it : items) {
            Assert.assertTrue(it.isAddToCartButtonDisplayed(), "Add to cart button is not displayed.");
            it.clickAddToCart();
            Assert.assertTrue(it.isRemoveFromCartButtonDisplayed(), "Item added to cart but no button to remove.");
            i++;
            Assert.assertTrue(inventoryPage.getHeaderComponent().isShoppingCartBadgeDisplayed());
            int badgeNumber = inventoryPage.getHeaderComponent().getShoppingCartBadgeNumber();
            Assert.assertEquals(badgeNumber, i, "Shopping cart badge number was not increased when item added to cart");
        }

        for (int j=0; j < inventoryPage.getAllProducts().size(); j++) {
            ProductCardComponent it = inventoryPage.getProductById(j);
            Assert.assertTrue(it.isProductLinkDisplayed(), "Product link is not displayed");
            it.clickProductLink();
            InventoryItemPage inventoryItemPage = new InventoryItemPage(getDriver());

            Assert.assertTrue(inventoryItemPage.isRemoveFromCartButtonDisplayed(), "Remove from cart button is not displayed.");
            inventoryItemPage.clickRemove();
            Assert.assertTrue(inventoryItemPage.isAddToCartButtonDisplayed(), "Item removed from the card but no button to add it back.");
            i--;
            int badgeNumber = inventoryItemPage.getHeaderComponent().getShoppingCartBadgeNumber();
            Assert.assertEquals(badgeNumber, i, "Shopping cart badge number was not decreased when item removed from cart");

            inventoryItemPage.clickAddToCart();
            Assert.assertTrue(inventoryItemPage.isRemoveFromCartButtonDisplayed(), "Item added to cart but no button to remove it was displayed.");
            i++;
            badgeNumber = inventoryItemPage.getHeaderComponent().getShoppingCartBadgeNumber();
            Assert.assertEquals(badgeNumber, i, "Shopping cart badge number was not increased when item added to cart");

            inventoryItemPage.clickGoBack();
        }

        inventoryPage = new InventoryPage(getDriver());
        items =  inventoryPage.getAllProducts();
        for(ProductCardComponent it : items) {
            Assert.assertTrue(it.isRemoveFromCartButtonDisplayed());
            it.clickRemove();
            Assert.assertTrue(it.isAddToCartButtonDisplayed());
            i--;
            if(i == 0) {
                Assert.assertFalse(inventoryPage.getHeaderComponent().isShoppingCartBadgeDisplayed());
            } else {
                int badgeNumber = inventoryPage.getHeaderComponent().getShoppingCartBadgeNumber();
                Assert.assertEquals(badgeNumber, i, "Shopping cart badge number was not decreased when item removed from cart");
            }
        }
    }

    @Test
    public void testSortAll() {
        String baseUrl = ConfigReader.getProperty("BASE_URL");
        getDriver().get(baseUrl + Routes.LOGIN);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(DefaultUserCredentials.STANDARD_USER);
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        inventoryPage.selectSortOption(SortOptions.NAME_A_TO_Z);
        List<ProductCardComponent> items =  inventoryPage.getAllProducts();
        for (int i=0; i < items.size()-1; i++) {
            boolean AtoZ = items.get(i).getProductName().compareToIgnoreCase(items.get(i+1).getProductName()) <= 0;
            Assert.assertTrue(AtoZ, "Products are not sorted in ascending alphabetical order (A-Z).");
        }

        inventoryPage.selectSortOption(SortOptions.NAME_Z_TO_A);
        items =  inventoryPage.getAllProducts();
        for (int i=0; i < items.size()-1; i++) {
            boolean ZtoA = items.get(i).getProductName().compareToIgnoreCase(items.get(i+1).getProductName()) >= 0;
            Assert.assertTrue(ZtoA, "Products are not sorted in descending alphabetical order (Z-A).");
        }

        inventoryPage.selectSortOption(SortOptions.PRICE_LOW_TO_HIGH);
        items =  inventoryPage.getAllProducts();
        for (int i=0; i < items.size()-1; i++) {
            boolean LowToHigh = items.get(i).getProductPrice() <= items.get(i+1).getProductPrice();
            Assert.assertTrue(LowToHigh, "Products are not sorted in ascending price order (low to high).");
        }

        inventoryPage.selectSortOption(SortOptions.PRICE_HIGH_TO_LOW);
        items =  inventoryPage.getAllProducts();
        for (int i=0; i < items.size()-1; i++) {
            boolean HighToLow = items.get(i).getProductPrice() >= items.get(i+1).getProductPrice();
            Assert.assertTrue(HighToLow, "Products are not sorted in descending price order (high to low).");
        }
    }
}
