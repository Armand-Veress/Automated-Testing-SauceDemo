package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;

public class BaseTest {

    final private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser", "driverPath", "browserPath"})
    @BeforeMethod
    public void setUp(
            @Optional String browser,
            @Optional String driverPath,
            @Optional String browserPath) {

        browser = resolveParameter(browser, "LOCAL_DEFAULT_BROWSER");
        driverPath = resolveParameter(driverPath, "LOCAL_DRIVER_PATH");
        browserPath = resolveParameter(browserPath, "LOCAL_BROWSER_PATH");

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

    private String resolveParameter(String parameterValue, String configValue) {
        if (parameterValue != null && !parameterValue.trim().isEmpty()) {
            return parameterValue;
        }
        String fallback = ConfigReader.getProperty(configValue);
        return (fallback != null) ? fallback : "";
    }
}