package com.hostaway.tests;

import com.hostaway.pages.ListingsPage;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Listings")
public class ListingsTest extends BaseTest {
    private ListingsPage listingsPage;

    @BeforeMethod
    public void setup() {
        super.setup();
        listingsPage = new ListingsPage(driver);
    }

    @Test(description = "Validate the number of listings is correct")
    public void testListings() {

        listingsPage.navigateToAllListings();
        listingsPage.waitForLoaderToFinish();
        listingsPage.scrollToLoadAllListings();

        int listingsCount = listingsPage.getListingsCount();
        int totalCountFromLabel = listingsPage.getTotalListingsFromLabel();

        Assert.assertEquals(listingsCount, totalCountFromLabel, "The listings count on the page does not match the 'All' label.");

    }

}
