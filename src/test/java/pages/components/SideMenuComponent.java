package pages.components;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenuComponent extends BaseComponent {
    final private By logoutButton = By.id("logout_sidebar_link");

    public SideMenuComponent(WebDriver driver) {
        super(driver, driver.findElement(By.cssSelector(".bm-menu")));
    }

    public void clickLogoutButton() {
        clickElement(logoutButton);
    }
}