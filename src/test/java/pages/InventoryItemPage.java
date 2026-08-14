package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;

public class InventoryItemPage extends BasePage {
    private final HeaderComponent headerComponent;

    private final By goBackButton = By.id("back-to-products");
    private final By addToCartButton = By.id("add-to-cart");
    private final By removeFromCartButton = By.id("remove");

    public InventoryItemPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isElementDisplayed(addToCartButton);
    }

    public void clickAddToCart() {
        clickElement(addToCartButton);
    }

    public boolean isRemoveFromCartButtonDisplayed() {
        return isElementDisplayed(removeFromCartButton);
    }

    public void clickRemove() {
        clickElement(removeFromCartButton);
    }

    public void clickGoBack() {
        clickElement(goBackButton);
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
}
