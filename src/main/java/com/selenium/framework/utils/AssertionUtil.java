package com.selenium.framework.utils;

import com.selenium.framework.utils.ReportUtil;
import org.testng.Assert;

public class AssertionUtil {
    private static final LoggerUtil logger = new LoggerUtil();

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }

    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }

    public static void assertNotNull(Object object, String message) {
        try {
            Assert.assertNotNull(object, message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }

    public static void assertNull(Object object, String message) {
        try {
            Assert.assertNull(object, message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }

    public static void assertContains(String actual, String expected, String message) {
        try {
            Assert.assertTrue(actual.contains(expected), message);
            ReportUtil.logPass("Assertion passed: " + message);
            logger.info("Assertion passed: " + message);
        } catch (AssertionError e) {
            ReportUtil.logFail("Assertion failed: " + message, e);
            logger.error("Assertion failed: " + message, e);
            throw e;
        }
    }
} 