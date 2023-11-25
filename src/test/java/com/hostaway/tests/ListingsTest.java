package com.hostaway.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hostaway.pages.ListingsPage;

import java.time.Duration;

public class ListingsTest {
    private WebDriver driver;
    private ListingsPage listingsPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        listingsPage = new ListingsPage(driver);
    }

    @Test (description="Validate the number of listings is correct")
    public void testListings() {
        driver.get("https://kamil-demo.alpinizm.uz/all-listings");

        listingsPage.waitForLoaderToFinish();
        listingsPage.scrollToLoadAllListings();

        int listingsCount = listingsPage.getListingsCount();
        int totalCountFromLabel = listingsPage.getTotalListingsFromLabel();

        Assert.assertEquals(listingsCount, totalCountFromLabel, "The listings count on the page does not match the 'All' label.");

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
