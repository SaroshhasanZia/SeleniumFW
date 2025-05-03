package com.selenium.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.framework.config.Config;

import java.time.Duration;
import java.util.List;

public class WebDriverUtil {
    private static final LoggerUtil logger = new LoggerUtil();
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebDriverUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getImplicitWait()));
    }

    public void click(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = driver.findElement(locator);
            element.click();
            logger.info("Clicked on element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + elementName, e);
            throw e;
        }
    }

    public void sendKeys(By locator, String text, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text '" + text + "' in element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: " + elementName, e);
            throw e;
        }
    }

    public String getText(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = driver.findElement(locator).getText();
            logger.info("Retrieved text '" + text + "' from element: " + elementName);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + elementName, e);
            throw e;
        }
    }

    public boolean isElementVisible(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element is visible: " + elementName);
            return true;
        } catch (TimeoutException e) {
            logger.info("Element is not visible: " + elementName);
            return false;
        }
    }

    public void waitForElementToDisappear(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element has disappeared: " + elementName);
        } catch (Exception e) {
            logger.error("Element did not disappear: " + elementName, e);
            throw e;
        }
    }

    public void scrollToElement(By locator, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: " + elementName, e);
            throw e;
        }
    }

    public void selectByVisibleText(By locator, String text, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
            logger.info("Selected option '" + text + "' from dropdown: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown: " + elementName, e);
            throw e;
        }
    }

    public void selectByValue(By locator, String value, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            select.selectByValue(value);
            logger.info("Selected value '" + value + "' from dropdown: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to select value from dropdown: " + elementName, e);
            throw e;
        }
    }

    public void selectByIndex(By locator, int index, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            select.selectByIndex(index);
            logger.info("Selected index '" + index + "' from dropdown: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to select index from dropdown: " + elementName, e);
            throw e;
        }
    }

    public List<WebElement> getDropdownOptions(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            List<WebElement> options = select.getOptions();
            logger.info("Retrieved " + options.size() + " options from dropdown: " + elementName);
            return options;
        } catch (Exception e) {
            logger.error("Failed to get options from dropdown: " + elementName, e);
            throw e;
        }
    }

    public String getSelectedOption(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select select = new Select(driver.findElement(locator));
            String selectedText = select.getFirstSelectedOption().getText();
            logger.info("Selected option is '" + selectedText + "' from dropdown: " + elementName);
            return selectedText;
        } catch (Exception e) {
            logger.error("Failed to get selected option from dropdown: " + elementName, e);
            throw e;
        }
    }
} 