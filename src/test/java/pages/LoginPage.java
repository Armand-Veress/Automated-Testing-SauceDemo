package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    final private WebDriver driver;

    final private By usernameField = By.id("user-name");
    final private By passwordField = By.id("password");
    final private By loginButton = By.id("login-button");
    final private By errorMessage = By.cssSelector("[data-test='error']");

    final private By loginCredentialsArea = By.id("login_credentials");
    final private By loginPasswordArea = By.cssSelector("[data-test='login-password']");

    public static final String EXPECTED_TITLE = "Swag Labs";
    public static final String ACCESS_DENIED_MSG_TEMPLATE = "Epic sadface: You can only access '%s' when you are logged in.";

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

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public String getLoginCredentialsText() {
        return driver.findElement(loginCredentialsArea).getText();
    }

    public String getLoginPasswordText() {
        return driver.findElement(loginPasswordArea).getText();
    }
}