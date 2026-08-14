package pages.components;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenuComponent extends BaseComponent {
    final private By logoutButton = By.id("logout_sidebar_link");
    final private By collapseMenuButton = By.id("react-burger-cross-btn");

    public SideMenuComponent(WebDriver driver) {
        super(driver, driver.findElement(By.cssSelector(".bm-menu")));
    }

    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(logoutButton);
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }

    public boolean isCollapseMenuButtonDisplayed() {
        return isElementDisplayed(collapseMenuButton);
    }

    public void clickCollapseMenuButton() {
        clickElement(collapseMenuButton);
    }
}