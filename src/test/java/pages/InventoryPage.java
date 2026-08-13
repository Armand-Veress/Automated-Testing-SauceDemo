package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    final private By hamburgerMenuButton = By.id("react-burger-menu-btn");
    final private By logoutButton = By.id("logout_sidebar_link");
    final private By shoppingCartIcon = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isShoppingCartDisplayed() {
        try {
            return driver.findElement(shoppingCartIcon).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHamburgerMenuButtonDisplayed() {
        try {
            return driver.findElement(hamburgerMenuButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
            return driver.findElement(logoutButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHamburgerMenuButton() {
        driver.findElement(hamburgerMenuButton).click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }
}