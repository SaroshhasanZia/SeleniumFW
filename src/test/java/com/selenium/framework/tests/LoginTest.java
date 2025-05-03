package com.selenium.framework.tests;

import com.selenium.framework.base.BaseTest;
import com.selenium.framework.pages.LoginPage;
import com.selenium.framework.utils.AssertionUtil;
import com.selenium.framework.utils.MockData;
import com.selenium.framework.utils.ReportUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        ReportUtil.logInfo("Starting login test");
    }
    
    @Test
    public void testSuccessfulLogin() {
        ReportUtil.logInfo("Testing successful login");
        loginPage.login(MockData.VALID_USERNAME, MockData.VALID_PASSWORD);
        
        AssertionUtil.assertTrue(loginPage.isSuccessMessageVisible(), "Success message should be visible");
        AssertionUtil.assertContains(loginPage.getSuccessMessage(), "You logged into a secure area!", 
            "Success message should contain expected text");
    }
    
    @Test
    public void testInvalidLogin() {
        ReportUtil.logInfo("Testing invalid login");
        loginPage.login(MockData.INVALID_USERNAME, MockData.INVALID_PASSWORD);
        
        AssertionUtil.assertTrue(loginPage.isErrorMessageVisible(), "Error message should be visible");
        AssertionUtil.assertContains(loginPage.getErrorMessage(), "Your username is invalid!", 
            "Error message should contain expected text");
    }
} 