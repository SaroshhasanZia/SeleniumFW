package com.selenium.framework.pages;

import com.selenium.framework.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverUtil webDriverUtil;
    
    // Locators
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".flash.success");
    private static final By ERROR_MESSAGE = By.cssSelector(".flash.error");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverUtil = new WebDriverUtil(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public void enterUsername(String username) {
        webDriverUtil.sendKeys(USERNAME_FIELD, username, "Username field");
    }
    
    public void enterPassword(String password) {
        webDriverUtil.sendKeys(PASSWORD_FIELD, password, "Password field");
    }
    
    public void clickSubmit() {
        webDriverUtil.click(SUBMIT_BUTTON, "Submit button");
    }
    
    public boolean isSuccessMessageVisible() {
        return webDriverUtil.isElementVisible(SUCCESS_MESSAGE, "Success message");
    }
    
    public boolean isErrorMessageVisible() {
        return webDriverUtil.isElementVisible(ERROR_MESSAGE, "Error message");
    }
    
    public String getSuccessMessage() {
        return webDriverUtil.getText(SUCCESS_MESSAGE, "Success message");
    }
    
    public String getErrorMessage() {
        return webDriverUtil.getText(ERROR_MESSAGE, "Error message");
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }
} 