package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;
import utils.DefaultUserCredentials;

public class CheckoutStepOnePage extends BasePage {
    HeaderComponent headerComponent;

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    final private By errorMessage = By.cssSelector("[data-test='error']");
    final private By errorMessageCloseButton = By.cssSelector("[data-test='error-button']");
    final private By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver){
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public void enterFirstName(String firstName) {
        typeText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        typeText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        typeText(postalCodeField, postalCode);
    }

    public void clickContinueButton() {
        clickElement(continueButton);
    }

    public void continueWithCheckoutData(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinueButton();
    }

    public void continueWithCheckoutAs(DefaultUserCredentials user) {
        continueWithCheckoutData(user.getFirstName(), user.getLastName(), user.getPostalCode());
    }

    public String getErrorMessageText() {
        return getElementText(errorMessage);
    }

    public boolean closeErrorMessagePopupSuccessfully() {
        try {
            clickElement(errorMessageCloseButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
}
