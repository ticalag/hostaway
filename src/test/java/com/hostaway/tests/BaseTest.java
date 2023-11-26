package com.hostaway.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://kamil-demo.alpinizm.uz/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
