package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent {
    protected final WebElement container;
    protected final WebDriverWait wait;

    public BaseComponent(WebDriver driver, WebElement container) {
        this.container = container;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(container, locator));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(container, locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void typeText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(container, locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(container, locator));
        return element.getText();
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(container, locator));
    }
}