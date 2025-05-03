package com.selenium.framework.pages;

import com.selenium.framework.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DropdownPage {
    private final WebDriver driver;
    private final WebDriverUtil webDriverUtil;
    
    // Locators
    private static final By DROPDOWN = By.id("dropdown");
    
    public DropdownPage(WebDriver driver) {
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
    
    public void selectOption(String optionText) {
        webDriverUtil.selectByVisibleText(DROPDOWN, optionText, "Dropdown");
    }
    
    public String getSelectedOption() {
        return webDriverUtil.getSelectedOption(DROPDOWN, "Dropdown");
    }
} 