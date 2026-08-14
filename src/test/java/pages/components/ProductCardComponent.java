package pages.components;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCardComponent extends BaseComponent {
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By addToCartButton = By.cssSelector(".btn_primary.btn_inventory");
    private final By removeFromCartButton = By.cssSelector(".btn_secondary.btn_inventory");
    private final By productLink = By.cssSelector("a[id$='_title_link']");

    public ProductCardComponent(WebDriver driver, WebElement container) {
        super(driver, container);
    }

    public int getProductId() {
        String rawId = getElement(productLink).getAttribute("id");

        if (rawId == null) {
            throw new IllegalStateException("Unexpected error: id-attribute is missing from productItem link");
        }

        String extractedNumber = rawId.replaceAll("\\D+", "");

        return Integer.parseInt(extractedNumber);
    }

    public boolean isProductLinkDisplayed() {
        return isElementDisplayed(productLink);
    }

    public void clickProductLink() {
        clickElement(productLink);
    }

    public boolean isProductNameDisplayed() {
        return isElementDisplayed(productName);
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public boolean isProductPriceDisplayed() {
        return isElementDisplayed(productPrice);
    }

    public double getProductPrice() {
        String price = getElementText(productPrice);
        price = price.substring(1);
        return Double.parseDouble(price);
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
}
