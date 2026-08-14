package pages.components;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BaseComponent {
    private final SideMenuComponent sideMenuComponent;

    final private By hamburgerMenuButton = By.id("react-burger-menu-btn");
    final private By shoppingCartButton = By.cssSelector("[data-test='shopping-cart-link']");
    final private By shoppingCartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public HeaderComponent(WebDriver driver) {
        super(driver, driver.findElement(By.cssSelector(".primary_header")));
        this.sideMenuComponent = new SideMenuComponent(driver);
    }

    public boolean isShoppingCartDisplayed() {
        return isElementDisplayed(shoppingCartButton);
    }

    public boolean isShoppingCartBadgeDisplayed() {
        return isElementDisplayed(shoppingCartBadge);
    }

    public int getShoppingCartBadgeNumber() {
        String number = getElementText(shoppingCartBadge);
        return Integer.parseInt(number);
    }

    public void clickShoppingCartButton() {
        clickElement(shoppingCartButton);
    }

    public boolean isHamburgerMenuButtonDisplayed() {
        return isElementDisplayed(hamburgerMenuButton);
    }

    public void clickHamburgerMenuButton() {
        clickElement(hamburgerMenuButton);
    }

    public SideMenuComponent getSideMenuComponent() {
        return sideMenuComponent;
    }
}