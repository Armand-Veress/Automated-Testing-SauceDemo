package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;

public class CheckoutStepTwoPage extends BasePage {
    HeaderComponent headerComponent;

    private final By subtotalText = By.cssSelector("[data-test='subtotal-label']");
    private final By finishCheckoutButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public double getSubtotal() {
        String subtotalText = getElementText(this.subtotalText);
        if (subtotalText == null || subtotalText.isEmpty()) {
            throw new IllegalStateException("Unexpected error: price text is missing or empty.");
        }

        String itemTotal = subtotalText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(itemTotal);
    }

    public void clickFinishCheckoutButton() {
        clickElement(finishCheckoutButton);
    }
}
