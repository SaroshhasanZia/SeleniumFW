package com.selenium.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtil {
    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final String BASE_DIR = System.getProperty("user.dir");
    private static final String REPORT_DIR = BASE_DIR + "/reports";
    private static final String SCREENSHOT_DIR = BASE_DIR + "/screenshots";
    private static final String TIMESTAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    static {
        createDirectories();
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_DIR + "/TestReport_" + TIMESTAMP + ".html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Selenium Test Report");
        spark.config().setReportName("Test Execution Report");
        extent.attachReporter(spark);
    }

    private static void createDirectories() {
        try {
            Files.createDirectories(Paths.get(REPORT_DIR));
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
        } catch (IOException e) {
            LoggerUtil.error("Failed to create report directories", e);
        }
    }

    public static void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThreadLocal.set(test);
        LoggerUtil.info("Starting test: " + testName);
    }

    public static void logInfo(String message) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().log(Status.INFO, message);
        }
        LoggerUtil.info(message);
    }

    public static void logPass(String message) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().log(Status.PASS, message);
        }
        LoggerUtil.info("PASS: " + message);
    }

    public static void logFail(String message, Throwable throwable) {
        if (testThreadLocal.get() != null) {
            testThreadLocal.get().log(Status.FAIL, message);
        }
        LoggerUtil.error("FAIL: " + message, throwable);
    }

    public static void logScreenshot(WebDriver driver, String message) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = SCREENSHOT_DIR + "/screenshot_" + timestamp + ".png";
            
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            
            if (testThreadLocal.get() != null) {
                testThreadLocal.get().addScreenCaptureFromPath(screenshotPath, message);
            }
            LoggerUtil.info("Screenshot captured: " + message);
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot", e);
        }
    }

    public static void endTest() {
        if (testThreadLocal.get() != null) {
            extent.flush();
        }
        LoggerUtil.info("Test completed");
    }
} 