package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    final private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser", "driverPath", "browserPath"})
    @BeforeMethod
    public void setUp(
            @Optional("firefox") String browser,
            @Optional("drivers/geckodriver.exe") String driverPath,
            @Optional("") String browserPath) {

        try {
            BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());

            driver.set(browserType.createDriver(driverPath, browserPath));
            getDriver().manage().window().maximize();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Illegal argument: [" + browser + "] is not defined in BrowserType.");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error -> " + e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            getDriver().quit();
        } catch (Exception e) {
            System.out.println("Unexpected error when quiting Driver -> " + e.getMessage());
        } finally {
            driver.remove();
        }
    }
}