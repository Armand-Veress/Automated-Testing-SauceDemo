package pages.components;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartItemCardComponent extends BaseComponent {
    private final By removeFromCartButton = By.cssSelector(".btn_secondary.cart_button");

    public CartItemCardComponent(WebDriver driver, WebElement container) {
        super(driver, container);
    }

    public boolean isRemoveFromCartButtonDisplayed() {
        return isElementDisplayed(removeFromCartButton);
    }

    public void clickRemove() {
        clickElement(removeFromCartButton);
    }

    public boolean isCartItemDisplayed() {
        return isContainerDisplayed();
    }
}
