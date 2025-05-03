package com.selenium.framework.tests;

import com.selenium.framework.base.BaseTest;
import com.selenium.framework.pages.DropdownPage;
import com.selenium.framework.utils.ReportUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTest extends BaseTest {
    private DropdownPage dropdownPage;

    @BeforeMethod
    public void setup() {
        dropdownPage = new DropdownPage(driver);
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void testInvalidDropdownOption() {
        ReportUtil.startTest("Test Invalid Dropdown Option", "Verify error handling for invalid dropdown option");
        
        try {
            ReportUtil.logInfo("Attempting to select non-existent option 'Invalid Option'");
            dropdownPage.selectOption("Invalid Option");
            
            ReportUtil.logInfo("Verifying selected option");
            String selectedOption = dropdownPage.getSelectedOption();
            ReportUtil.logInfo("Selected option: " + selectedOption);
            
            // This assertion will fail because the dropdown doesn't have an "Invalid Option"
            ReportUtil.logInfo("Verifying selected option is 'Invalid Option'");
            if (!selectedOption.equals("Invalid Option")) {
                ReportUtil.logFail("Selected option is not 'Invalid Option'", null);
            }
        } catch (Exception e) {
            ReportUtil.logFail("Error occurred while testing dropdown", e);
            throw e;
        }
    }
} 