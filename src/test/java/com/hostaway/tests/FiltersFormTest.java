package com.hostaway.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hostaway.pages.FilterPage;

import java.time.Duration;

public class FiltersFormTest {
    private WebDriver driver;
    private FilterPage filterPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        filterPage = new FilterPage(driver);

    }

    @Test(description="Validate filters work correctly and are cleared")
    @Description("Test Description: Login test with wrong username and wrong password.")
    public void testFiltersForm() {

        driver.get("https://kamil-demo.alpinizm.uz/");

        filterPage.waitForSearchButton();

        filterPage.clickOnSearchButton();

        filterPage.selectCheckInAndCheckOutDates();
        filterPage.clickOnFilterButton();

        //validate checkbox functionality
        Assert.assertTrue(filterPage.areAllCheckboxesSelected(false), "Checkboxes should not be selected by default.");
        filterPage.selectAllCheckboxes();
        Assert.assertTrue(filterPage.areAllCheckboxesSelected(true), "All checkboxes should be selected.");
        filterPage.deselectAllCheckboxes();
        Assert.assertTrue(filterPage.areAllCheckboxesSelected(false), "All checkboxes should be deselected.");
        filterPage.selectAllCheckboxes();

        //beds validation
        Assert.assertTrue(filterPage.isDecrementBedDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bed count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBedsCount();
            if (currentCount < 10) {
                filterPage.incrementBeds();
                Assert.assertFalse(filterPage.isDecrementBedDisabled(), "Decrement button should be enabled.");
                Assert.assertEquals(filterPage.getBedsCount(), currentCount + 1);
            } else {
                Assert.assertTrue(filterPage.isIncrementBedDisabled(), "Increment button should be disabled.");
                Assert.assertFalse(filterPage.isDecrementBedDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bed count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBedsCount();
            if (currentCount > 0) {
                filterPage.decrementBeds();
                Assert.assertEquals(filterPage.getBedsCount(), currentCount - 1);
            } else {
                Assert.assertTrue(filterPage.isDecrementBedDisabled(), "Decrement button should be disabled.");
                Assert.assertFalse(filterPage.isIncrementBedDisabled(), "Increment button should be disabled.");

            }
        }


        //bedroom validation
        Assert.assertTrue(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bedroom count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBedroomsCount();
            if (currentCount < 10) {
                filterPage.incrementBedrooms();
                Assert.assertEquals(filterPage.getBedroomsCount(), currentCount + 1);
            } else {
                Assert.assertTrue(filterPage.isIncrementBedroomsDisabled(), "Increment button should be disabled.");
                Assert.assertFalse(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bedroom count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBedroomsCount();
            if (currentCount > 0) {
                filterPage.decrementBedrooms();
                Assert.assertEquals(filterPage.getBedroomsCount(), currentCount - 1);
            } else {
                Assert.assertTrue(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be disabled.");
                Assert.assertFalse(filterPage.isIncrementBedroomsDisabled(), "Increment button should be disabled.");

            }
        }


        //bathroom validation
        Assert.assertTrue(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bedroom count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBathroomsCount();
            if (currentCount < 10) {
                filterPage.incrementBathrooms();
                Assert.assertEquals(filterPage.getBathroomsCount(), currentCount + 1);
            } else {
                Assert.assertTrue(filterPage.isIncrementBathroomsDisabled(), "Increment button should be disabled.");
                Assert.assertFalse(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bathroom count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBathroomsCount();
            if (currentCount > 0) {
                filterPage.decrementBathrooms();
                Assert.assertEquals(filterPage.getBathroomsCount(), currentCount - 1);
            } else {
                Assert.assertTrue(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be disabled.");
                Assert.assertFalse(filterPage.isIncrementBathroomsDisabled(), "Increment button should be disabled.");
            }
        }


        filterPage.setFromPrice("500");
        filterPage.setToPrice("550");
        filterPage.setFromPrice("600");
        Assert.assertEquals(filterPage.getToPrice(), 605, "'To' price did not adjust correctly.");

        filterPage.setToPrice("500");
        Assert.assertEquals(filterPage.getFromPrice(), 495, "'From' price did not adjust correctly.");


        filterPage.incrementBeds();
        filterPage.incrementBedrooms();
        filterPage.incrementBathrooms();

        filterPage.clickClearAll();

        //beds validation
        Assert.assertTrue(filterPage.isDecrementBedDisabled(), "Decrement button should be disabled.");
        //bathroom validation
        Assert.assertTrue(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be disabled.");
        //bedroom validation
        Assert.assertTrue(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be disabled.");

        Assert.assertEquals(filterPage.getBedsCount(), 0);
        Assert.assertEquals(filterPage.getBedroomsCount(), 0);
        Assert.assertEquals(filterPage.getBathroomsCount(), 0);

        Assert.assertTrue(filterPage.areAllCheckboxesSelected(false), "All checkboxes should be deselected.");

        Assert.assertTrue(filterPage.isToInputEmpty(), "'To' input field should be empty");
        Assert.assertTrue(filterPage.isFromInputEmpty(), "'From' input field should be empty");


    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
