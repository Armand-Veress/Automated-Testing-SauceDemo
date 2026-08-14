package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;

public class CheckoutCompletePage extends BasePage {
    HeaderComponent headerComponent;

    private final By statusTitleText = By.cssSelector("[data-test='title']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public String getStatusTitle(){
        return getElementText(statusTitleText);
    }
}
