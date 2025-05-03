package com.selenium.framework.base;

import com.selenium.framework.config.Config;
import com.selenium.framework.driver.DriverFactory;
import com.selenium.framework.utils.LoggerUtil;
import com.selenium.framework.utils.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    private final LoggerUtil logger = new LoggerUtil();

    @BeforeMethod
    @Parameters({"useGrid"})
    public void setup(boolean useGrid) {
        try {
            if (useGrid) {
                DriverFactory.initializeRemoteDriver();
            } else {
                DriverFactory.initializeDriver();
            }
            driver = DriverFactory.getDriver();
            driver.get(Config.getBaseUrl());
            
            String testName = this.getClass().getSimpleName();
            ReportUtil.startTest(testName, "Test execution started");
            ReportUtil.logInfo("Browser: " + Config.getBrowser());
            ReportUtil.logInfo("URL: " + Config.getBaseUrl());
            
            logger.info("Test setup completed for: " + testName);
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver", e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                ReportUtil.logFail("Test failed: " + result.getThrowable().getMessage(), result.getThrowable());
                ReportUtil.logScreenshot(driver, "Failure Screenshot");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ReportUtil.logPass("Test passed successfully");
            }
            
            ReportUtil.endTest();
            DriverFactory.quitDriver();
            logger.info("Test teardown completed");
        } catch (Exception e) {
            logger.error("Error during test teardown", e);
        }
    }
} 