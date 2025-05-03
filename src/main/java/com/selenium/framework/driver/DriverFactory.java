package com.selenium.framework.driver;

import com.selenium.framework.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String SAFARI = "safari";

    private DriverFactory() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initializeDriver() {
        String browser = Config.getBrowser();
        WebDriver driver = null;
        
        switch (browser.toLowerCase()) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case EDGE:
                driver = createEdgeDriver();
                break;
            case SAFARI:
                driver = createSafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        configureDriver(driver);
        driverThreadLocal.set(driver);
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        return new EdgeDriver(options);
    }

    private static WebDriver createSafariDriver() {
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Config.getImplicitWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Config.getPageLoadTimeout(), TimeUnit.SECONDS);
    }

    public static void initializeRemoteDriver() throws Exception {
        String browser = Config.getBrowser();
        String gridUrl = Config.getGridUrl();
        
        if (gridUrl.isEmpty()) {
            throw new IllegalArgumentException("Grid URL is not configured");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.toLowerCase());
        
        if (Config.isHeadless()) {
            capabilities.setCapability("headless", true);
        }

        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        configureDriver(driver);
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
} 