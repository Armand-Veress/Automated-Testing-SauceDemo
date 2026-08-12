package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    final private WebDriver driver;

    final private By shoppingCartIcon = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isShoppingCartDisplayed() {
        try {
            return driver.findElement(shoppingCartIcon).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}