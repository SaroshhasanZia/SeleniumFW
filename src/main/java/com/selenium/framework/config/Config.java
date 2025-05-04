package com.selenium.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
private static final String GRID_URL = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getGridUrl() {
        return GRID_URL;
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    // public static String getBaseUrl() {
    //     return properties.getProperty("base.url", "");
    // }

    public static String getChromeDriverPath() {
        return properties.getProperty("chrome.driver.path", "");
    }

    public static String getFirefoxDriverPath() {
        return properties.getProperty("firefox.driver.path", "");
    }

    public static String getEdgeDriverPath() {
        return properties.getProperty("edge.driver.path", "");
    }
} 