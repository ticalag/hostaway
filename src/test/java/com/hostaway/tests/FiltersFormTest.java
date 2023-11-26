package com.hostaway.tests;

import com.hostaway.pages.FilterPage;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Feature("Filters")
public class FiltersFormTest extends BaseTest {
    private FilterPage filterPage;

    @BeforeMethod
    public void setup() {
        super.setup();
        filterPage = new FilterPage(driver);
    }

    @Test(priority = 1, description = "Validate checkbox functionality")
    public void validateCheckboxFunctionality() {

        filterPage.waitForSearchButton();

        filterPage.clickOnSearchButton()
                .selectCheckInAndCheckOutDates()
                .clickOnFilterButton();

        Assert.assertTrue(filterPage.areAllCheckboxesSelected(false), "Checkboxes should not be selected by default.");
        filterPage.selectAllCheckboxes();
        Assert.assertTrue(filterPage.areAllCheckboxesSelected(true), "All checkboxes should be selected.");
        filterPage.deselectAllCheckboxes();
        Assert.assertTrue(filterPage.areAllCheckboxesSelected(false), "All checkboxes should be deselected.");
        filterPage.selectAllCheckboxes();
    }


    @Test(priority = 2, description = "Validate Rooms and beds functionality")
    public void validateRoomsFunctionality() {

        filterPage.waitForSearchButton();

        filterPage.clickOnSearchButton()
                .selectCheckInAndCheckOutDates()
                .clickOnFilterButton();

        //beds validation
        filterPage.waitTillButtonsAreClickable();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(filterPage.isDecrementBedDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bed count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBedsCount();
            if (currentCount < 10) {
                filterPage.incrementBeds();
                softAssert.assertFalse(filterPage.isDecrementBedDisabled(), "Decrement button should be enabled.");
                softAssert.assertEquals(filterPage.getBedsCount(), currentCount + 1);
            } else {
                softAssert.assertTrue(filterPage.isIncrementBedDisabled(), "Increment button should be disabled.");
                softAssert.assertFalse(filterPage.isDecrementBedDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bed count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBedsCount();
            if (currentCount > 0) {
                filterPage.decrementBeds();
                softAssert.assertEquals(filterPage.getBedsCount(), currentCount - 1);
            } else {
                softAssert.assertTrue(filterPage.isDecrementBedDisabled(), "Decrement button should be disabled.");
                softAssert.assertFalse(filterPage.isIncrementBedDisabled(), "Increment button should be disabled.");

            }
        }


        //bedroom validation
        softAssert.assertTrue(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bedroom count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBedroomsCount();
            if (currentCount < 10) {
                filterPage.incrementBedrooms();
                softAssert.assertEquals(filterPage.getBedroomsCount(), currentCount + 1);
            } else {
                softAssert.assertTrue(filterPage.isIncrementBedroomsDisabled(), "Increment button should be disabled.");
                softAssert.assertFalse(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bedroom count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBedroomsCount();
            if (currentCount > 0) {
                filterPage.decrementBedrooms();
                softAssert.assertEquals(filterPage.getBedroomsCount(), currentCount - 1);
            } else {
                softAssert.assertTrue(filterPage.isDecrementBedroomsDisabled(), "Decrement button should be disabled.");
                softAssert.assertFalse(filterPage.isIncrementBedroomsDisabled(), "Increment button should be disabled.");

            }
        }


        //bathroom validation
        softAssert.assertTrue(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be disabled.");

        // Check if incrementing bedroom count works within bounds
        for (int i = 0; i <= 10; i++) {
            int currentCount = filterPage.getBathroomsCount();
            if (currentCount < 10) {
                filterPage.incrementBathrooms();
                softAssert.assertEquals(filterPage.getBathroomsCount(), currentCount + 1);
            } else {
                softAssert.assertTrue(filterPage.isIncrementBathroomsDisabled(), "Increment button should be disabled.");
                softAssert.assertFalse(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be enabled.");
            }
        }

        // Check if decrementing bathroom count works within bounds
        for (int i = 10; i >= 0; i--) {
            int currentCount = filterPage.getBathroomsCount();
            if (currentCount > 0) {
                filterPage.decrementBathrooms();
                softAssert.assertEquals(filterPage.getBathroomsCount(), currentCount - 1);
            } else {
                softAssert.assertTrue(filterPage.isDecrementBathroomsDisabled(), "Decrement button should be disabled.");
                softAssert.assertFalse(filterPage.isIncrementBathroomsDisabled(), "Increment button should be disabled.");
            }
        }
        softAssert.assertAll();

    }

    @Test(priority = 3, description = "Validate price input functionality")
    public void validatePriceFunctionality() {

        filterPage.waitForSearchButton();
        filterPage.clickOnSearchButton()
                .selectCheckInAndCheckOutDates()
                .clickOnFilterButton();

        filterPage.setFromPrice("500");
        filterPage.setToPrice("550");
        filterPage.setFromPrice("600");
        Assert.assertEquals(filterPage.getToPrice(), 605, "'To' price did not adjust correctly.");

        filterPage.setToPrice("500");
        Assert.assertEquals(filterPage.getFromPrice(), 495, "'From' price did not adjust correctly.");
    }


    @Test(priority = 4, description = "Validate Clear all functionality")
    public void validateClearAllFunctionality() {

        filterPage.waitForSearchButton();
        filterPage.clickOnSearchButton()
                .selectCheckInAndCheckOutDates()
                .clickOnFilterButton();

        filterPage.incrementBeds();
        filterPage.incrementBedrooms();
        filterPage.incrementBathrooms();

        filterPage.clickClearAllButton();

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

}
