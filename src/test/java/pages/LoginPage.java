package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DefaultUserCredentials;

public class LoginPage {
    final private WebDriver driver;

    final private By usernameField = By.id("user-name");
    final private By passwordField = By.id("password");
    final private By loginButton = By.id("login-button");
    final private By errorMessage = By.cssSelector("[data-test='error']");
    final private By errorMessageCloseButton = By.cssSelector("[data-test='error-button']");

    public static final String EXPECTED_TITLE = "Swag Labs";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
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
        return driver.findElement(errorMessage).getText();
    }

    public boolean closeErrorMessagePopupSuccessfully() {
        try {
            driver.findElement(errorMessageCloseButton).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}