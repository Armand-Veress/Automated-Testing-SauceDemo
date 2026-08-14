package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public enum BrowserType {
    CHROME {
        @Override
        public WebDriver createDriver(String driverPath, String browserPath) {
            try {
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless=new");
//                chromeOptions.addArguments("--window-size=1920,1080");

                chromeOptions.addArguments("--disable-features=PasswordLeakDetection");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-save-password-bubble");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("safebrowsing.enabled", false);

                chromeOptions.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(chromeOptions);
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
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                return new FirefoxDriver(firefoxOptions);
            } catch (WebDriverException e) {
                throw new RuntimeException("Error: couldn't initialize Firefox. ->  " + e.getMessage());
            }
        }
    };

    public abstract WebDriver createDriver(String driverPath, String browserPath);
}