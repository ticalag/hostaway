package com.hostaway.pages;

import com.hostaway.utils.TestConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ListingsPage extends BasePage {

    private WebDriverWait wait;

    public ListingsPage(WebDriver driver) {
        super(driver);
    }

    private final By allListingsLabel = By.cssSelector(".hYJCa");
    private final By listingsTitle = By.cssSelector(".bAGyCr > h3");
    private final By listingsLoader = By.cssSelector(".jmOaEN .jFodJP");
    private final By listingsTab = By.cssSelector(".jfhxYY li:nth-child(2) > a");

    @Step("Scroll to the the end of the listings")
    public void scrollToLoadAllListings() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConstants.FIFTEEN_SECONDS));

        int totalNumberOfListings = getTotalListingsFromAllButton();
        int currentNumberOfListings = 0;

        while (currentNumberOfListings < totalNumberOfListings) {
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            int previousNumberOfListings = currentNumberOfListings;

            wait.until(ExpectedConditions.presenceOfElementLocated(listingsLoader));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(listingsLoader));

            wait.until(driver -> getListingsCount() > previousNumberOfListings);

            currentNumberOfListings = getListingsCount();

            if (currentNumberOfListings == previousNumberOfListings) {
                break;
            }
        }
    }

    @Step("Navigate to all listings")
    public void navigateToAllListings() {
        click(listingsTab);
    }

    @Step("Wait for the loader to finish")
    public void waitForLoaderToFinish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestConstants.TEN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(listingsLoader));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(listingsLoader));
    }

    @Step("Read the total listings from the All label")
    public int getTotalListingsFromLabel() {
        WebElement allLabel = driver.findElement(allListingsLabel);
        String allLabelText = allLabel.getText();

        return Integer.parseInt(allLabelText.replaceAll("[^0-9]", ""));
    }

    public int getListingsCount() {
        List<WebElement> listings = driver.findElements(listingsTitle);
        return listings.size();
    }

    public int getTotalListingsFromAllButton() {
        waitUntilElementVisible(allListingsLabel, TestConstants.TEN_SECONDS);

        // Wait for the "All" button text to contain digits
        wait.until((ExpectedCondition<Boolean>) wd ->
                driver.findElement(allListingsLabel).getText().matches(".*\\d.*"));

        String allButtonText = driver.findElement(allListingsLabel).getText();
        return Integer.parseInt(allButtonText.replaceAll("\\D+", ""));
    }
}
