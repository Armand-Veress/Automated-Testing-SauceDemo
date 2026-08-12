package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowserType {
    CHROME {
        @Override
        public WebDriver createDriver(String driverPath, String browserPath) {
            try {
                System.setProperty("webdriver.chrome.driver", driverPath);
                return new ChromeDriver();
            } catch (WebDriverException e) {
                throw new RuntimeException("Error: couldn't initialize Chrome. -> " + e.getMessage());
            }
        }
    },

    BRAVE {
        @Override
        public WebDriver createDriver(String driverPath, String browserPath) {
            try {
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                if (browserPath != null && !browserPath.isEmpty()) {
                    options.setBinary(browserPath);
                }
                return new ChromeDriver(options);
            } catch (WebDriverException e) {
                throw new RuntimeException("Error: couldn't initialize Brave. -> " + e.getMessage());
            }
        }
    },

    FIREFOX {
        @Override
        public WebDriver createDriver(String driverPath, String browserPath) {
            try {
                System.setProperty("webdriver.gecko.driver", driverPath);
                return new FirefoxDriver();
            } catch (WebDriverException e) {
                throw new RuntimeException("Error: couldn't initialize Firefox. ->  " + e.getMessage());
            }
        }
    };

    public abstract WebDriver createDriver(String driverPath, String browserPath);
}