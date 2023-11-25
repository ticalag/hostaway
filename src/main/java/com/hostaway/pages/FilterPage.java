package com.hostaway.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FilterPage {
    private WebDriver driver;
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
        this.driver = driver;
    }

    public FilterPage clickOnSearchButton() {
        WebElement element = driver.findElement(searchButton);
        element.click();
        return this;
    }

    public FilterPage clickOnFilterButton() {
        WebElement element = driver.findElement(filterButton);
        element.click();
        return this;
    }

    public FilterPage selectCheckInAndCheckOutDates() {
        driver.findElement(checkIn).click();
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

    public void selectAllCheckboxes() {
        for (WebElement checkbox : getCheckboxes()) {
            checkbox.click();
        }
    }

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

    public void waitForSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
    }


    public void incrementBeds() {
        WebElement plusButton = driver.findElement(plusBedsButton);
        plusButton.click();
    }

    public void decrementBeds() {
        WebElement minusButton = driver.findElement(minusBedsButton);
        minusButton.click();

    }

    public int getBedsCount() {
        return Integer.parseInt(driver.findElement(bedsCount).getText());
    }

    public boolean isIncrementBedDisabled() {
        return isAttributePresent(driver.findElement(plusBedsButton), "disabled");

    }

    public boolean isDecrementBedDisabled() {
        return isAttributePresent(driver.findElement(minusBedsButton), "disabled");

    }


    public void incrementBedrooms() {
        WebElement plusButton = driver.findElement(plusBedroomsButton);
        plusButton.click();
    }

    public void decrementBedrooms() {
        WebElement minusButton = driver.findElement(minusBedroomsButton);
        minusButton.click();

    }

    public int getBedroomsCount() {
        return Integer.parseInt(driver.findElement(bedroomsCount).getText());
    }

    public boolean isIncrementBedroomsDisabled() {
        return isAttributePresent(driver.findElement(plusBedroomsButton), "disabled");

    }

    public boolean isDecrementBedroomsDisabled() {
        return isAttributePresent(driver.findElement(minusBedroomsButton), "disabled");
    }


    public void incrementBathrooms() {
        WebElement plusButton = driver.findElement(plusBathroomsButton);
        plusButton.click();
    }

    public void decrementBathrooms() {
        WebElement minusButton = driver.findElement(minusBathroomsButton);
        minusButton.click();

    }

    public int getBathroomsCount() {
        return Integer.parseInt(driver.findElement(bathroomsCount).getText());
    }

    public boolean isIncrementBathroomsDisabled() {
        return isAttributePresent(driver.findElement(plusBathroomsButton), "disabled");

    }

    public boolean isDecrementBathroomsDisabled() {
        return isAttributePresent(driver.findElement(minusBathroomsButton), "disabled");
    }


    public void setFromPrice(String price) {
        WebElement fromPriceElement = driver.findElement(fromPriceInput);
        fromPriceElement.clear();
        fromPriceElement.sendKeys(price, Keys.TAB);
    }

    public void setToPrice(String price) {
        WebElement toPriceElement = driver.findElement(toPriceInput);
        toPriceElement.clear();
        toPriceElement.sendKeys(price, Keys.TAB);
    }

    public int getFromPrice() {
        return Integer.parseInt(driver.findElement(fromPriceInput).getAttribute("value"));
    }

    public int getToPrice() {
        return Integer.parseInt(driver.findElement(toPriceInput).getAttribute("value"));
    }

    public boolean isFromInputEmpty() {
        return isAttributePresent(driver.findElement(fromPriceInput), "value");
    }

    public boolean isToInputEmpty() {
        return isAttributePresent(driver.findElement(toPriceInput), "value");
    }


    private boolean isAttributePresent(WebElement element, String attribute) {
        boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    public FilterPage clickClearAll() {
        driver.findElement(clearAllLocator).click();
        return this;
    }

}
