package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.CartItemCardComponent;
import pages.components.HeaderComponent;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    HeaderComponent headerComponent;

    private final By productItems = By.cssSelector(".cart_item");
    private final By goToCheckoutButton = By.id("checkout");

    public CartPage(WebDriver driver){
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public List<CartItemCardComponent> getAllCartItems() {
        List<WebElement> elements = driver.findElements(productItems);
        List<CartItemCardComponent> products = new ArrayList<>();

        for (WebElement container : elements) {
            products.add(new CartItemCardComponent(driver, container));
        }

        return products;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public boolean isGoToCheckoutButtonDisplayed() {
        return isElementDisplayed(goToCheckoutButton);
    }

    public void clickGoToCheckoutButton() {
        clickElement(goToCheckoutButton);
    }
}
