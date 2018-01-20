package com.amazon.test;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

public class ComparePrice {
	
    public static void main(String[] args) {
        String expath = "C:\\Users\\cibin\\Downloads\\chromedriver_win32\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", expath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/cibin/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        System.out.println("test");
        driver.get("http:\\www.amazon.com");
        driver.get("https://www.amazon.com/dp/B00Q31K53M/ref=sspa_dk_detail_4?psc=1");
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame("pb-iframe");
        ((ChromeDriver) driver).findElementById("comparePricesBtn").click();System.out.println("test");
        List<WebElement> priceBlinkList = driver.findElements(By.cssSelector("#compare-prices-content > ul > li > a"));

        for (WebElement option : priceBlinkList) {
            System.out.println("Text :" + option.getAttribute("href"));
            driver.get(((WebElement) priceBlinkList.get(1)).getAttribute("href"));
            System.out.println(((ChromeDriver)driver).getCurrentUrl());
            if (((ChromeDriver)driver).getCurrentUrl().startsWith("https://www.ebay.com")) {
                System.out.println(priceBlinkList.size());
            }
        }
    }

}
