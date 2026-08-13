package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    final private By hamburgerMenuButton = By.id("react-burger-menu-btn");
    final private By logoutButton = By.id("logout_sidebar_link");
    final private By shoppingCartIcon = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isShoppingCartDisplayed() {
        return isElementDisplayed(shoppingCartIcon);
    }

    public boolean isHamburgerMenuButtonDisplayed() {
        return isElementDisplayed(hamburgerMenuButton);
    }

    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(logoutButton);
    }

    public void clickHamburgerMenuButton() {
        clickElement(hamburgerMenuButton);
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }
}