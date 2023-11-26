package com.hostaway.pages;

import com.hostaway.utils.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FilterPage extends BasePage {
    //extract the search button into it's own pom
    private By searchButton = By.cssSelector(".hzsGOz > button");

    private By filterButton = By.cssSelector(".iHrcjg > button");
    private By checkIn = By.cssSelector(".WzEhh .gQYBcx");
    private By clearAllLocator = By.cssSelector(".iONckA b");
    private By calendarDays = By.cssSelector("div.CalendarDay:not([disabled]):not(.fiDOnB)");
    private By fromPriceInput = By.cssSelector(".gAPEwx > div:nth-child(1) input");
    private By toPriceInput = By.cssSelector(".gAPEwx > div:nth-child(2) input");
    private By minusBedsButton = By.cssSelector("div:nth-child(6) .kTTKRj");
    private By plusBedsButton = By.cssSelector("div:nth-child(6) .ckwDLe");
    private By bedsCount = By.cssSelector("div:nth-child(6) .jHMcWv span");
    private By bedroomsCount = By.cssSelector("div:nth-child(7) .jHMcWv span");
    private By bathroomsCount = By.cssSelector("div:nth-child(8) .jHMcWv span");
    private By minusBedroomsButton = By.cssSelector("div:nth-child(7) .kTTKRj");
    private By plusBedroomsButton = By.cssSelector("div:nth-child(7) .ckwDLe");
    private By minusBathroomsButton = By.cssSelector("div:nth-child(8) .kTTKRj");
    private By plusBathroomsButton = By.cssSelector("div:nth-child(8) .ckwDLe");
    //    investigate how to merge this two into one
    private By checkboxes = By.cssSelector(".jrzUMQ");
    private By checkboxInput = By.cssSelector(".egQPEn");

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the Search button")
    public FilterPage clickOnSearchButton() {
        click(searchButton);
        return this;
    }

    @Step("Click on the Filter button")
    public FilterPage clickOnFilterButton() {
        click(filterButton);
        return this;
    }

    @Step("Select Check-in for 7 days period")
    public FilterPage selectCheckInAndCheckOutDates() {
        click(checkIn);
        List<WebElement> elements = driver.findElements(calendarDays);
        elements.get(0).click();
        elements.get(7).click();
        return this;
    }

    public List<WebElement> getCheckboxes() {
        waitForCheckboxes();
        return driver.findElements(checkboxes);
    }

    public List<WebElement> getCheckBoxStatus() {
        return driver.findElements(checkboxInput);
    }

    @Step("Select all check boxes")
    public void selectAllCheckboxes() {
        for (WebElement checkbox : getCheckboxes()) {
            checkbox.click();
        }
    }

    @Step("Deselect all check boxes")
    public void deselectAllCheckboxes() {
        for (WebElement checkbox : getCheckboxes()) {
            checkbox.click();
        }
    }

    public boolean areAllCheckboxesSelected(boolean selectedState) {
        for (WebElement checkbox : getCheckBoxStatus()) {
            if (checkbox.isSelected() != selectedState) {
                return false;
            }
        }
        return true;
    }

    public void waitForCheckboxes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(checkboxes, 10));
    }

    @Step("Wait for Search button to be visible")
    public void waitForSearchButton() {
        waitUntilElementVisible(searchButton, 20);
    }

    @Step("We increment the number of beds")
    public void incrementBeds() {
        click(plusBedsButton);
    }

    @Step("We decrement the number of beds")
    public void decrementBeds() {
        click(minusBedsButton);
    }

    public int getBedsCount() {
        return Integer.parseInt(driver.findElement(bedsCount).getText());
    }

    public boolean isIncrementBedDisabled() {
        return isAttributePresent(driver.findElement(plusBedsButton), Constants.DISABLED_ATTRIBUTE);

    }

    public boolean isDecrementBedDisabled() {
        return isAttributePresent(driver.findElement(minusBedsButton), Constants.DISABLED_ATTRIBUTE);

    }

    @Step("We increment the number of bedrooms")
    public void incrementBedrooms() {
        click(plusBedroomsButton);
    }

    @Step("We decrement the number of bedrooms")
    public void decrementBedrooms() {
        click(minusBedroomsButton);
    }

    public int getBedroomsCount() {
        return Integer.parseInt(driver.findElement(bedroomsCount).getText());
    }

    public boolean isIncrementBedroomsDisabled() {
        return isAttributePresent(driver.findElement(plusBedroomsButton), Constants.DISABLED_ATTRIBUTE);

    }

    public boolean isDecrementBedroomsDisabled() {
        return isAttributePresent(driver.findElement(minusBedroomsButton), Constants.DISABLED_ATTRIBUTE);
    }

    @Step("We increment the number of bathrooms")
    public void incrementBathrooms() {
        click(plusBathroomsButton);
    }
    @Step("We decrement the number of bathrooms")
    public void decrementBathrooms() {
        click(minusBathroomsButton);
    }

    public int getBathroomsCount() {
        return Integer.parseInt(driver.findElement(bathroomsCount).getText());
    }

    public boolean isIncrementBathroomsDisabled() {
        return isAttributePresent(driver.findElement(plusBathroomsButton), Constants.DISABLED_ATTRIBUTE);

    }

    public boolean isDecrementBathroomsDisabled() {
        return isAttributePresent(driver.findElement(minusBathroomsButton), Constants.DISABLED_ATTRIBUTE);
    }

    @Step("We set the price in the 'From' input {0}")
    public void setFromPrice(String price) {
        WebElement fromPriceElement = driver.findElement(fromPriceInput);
        fromPriceElement.clear();
        fromPriceElement.sendKeys(price, Keys.TAB);
    }
    @Step("We set the price in the 'To' input {0}")
    public void setToPrice(String price) {
        WebElement toPriceElement = driver.findElement(toPriceInput);
        toPriceElement.clear();
        toPriceElement.sendKeys(price, Keys.TAB);
    }

    public int getFromPrice() {
        return Integer.parseInt(driver.findElement(fromPriceInput).getAttribute(Constants.VALUE_ATTRIBUTE));
    }

    public int getToPrice() {
        return Integer.parseInt(driver.findElement(toPriceInput).getAttribute(Constants.VALUE_ATTRIBUTE));
    }

    public boolean isFromInputEmpty() {
        return isAttributePresent(driver.findElement(fromPriceInput), Constants.VALUE_ATTRIBUTE);
    }

    public boolean isToInputEmpty() {
        return isAttributePresent(driver.findElement(toPriceInput), Constants.VALUE_ATTRIBUTE);
    }

    @Step("Click on the Clear All button")
    public FilterPage clickClearAllButton() {
        click(clearAllLocator);
        return this;
    }

}
