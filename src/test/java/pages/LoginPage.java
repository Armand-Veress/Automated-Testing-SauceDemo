package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DefaultUserCredentials;

public class LoginPage extends BasePage {
    final private By usernameField = By.id("user-name");
    final private By passwordField = By.id("password");
    final private By loginButton = By.id("login-button");
    final private By errorMessage = By.cssSelector("[data-test='error']");
    final private By errorMessageCloseButton = By.cssSelector("[data-test='error-button']");

    public static final String EXPECTED_TITLE = "Swag Labs";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginAs(DefaultUserCredentials user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLoginButton();
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