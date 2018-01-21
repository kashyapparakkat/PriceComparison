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

        driver.get("https://www.amazon.com/dp/B00Q31K53M/ref=sspa_dk_detail_4?psc=1");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.switchTo().frame("pb-iframe");
//        ((ChromeDriver) driver).findElementById("comparePricesBtn").click();System.out.println("test");
        Integer ebay_price = 0;
        Integer walmart_price = 0;
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        if (iframes.size() == 0) {
            // No frames
            System.out.println("No priceblink available");
        } else {


            Integer amazon_price = Integer.parseInt(((ChromeDriver) driver).findElement(By.cssSelector("#priceblock_ourprice")).getText().replaceAll("[$,]", ""));
            List<WebElement> priceBlinkList = driver.findElements(By.cssSelector("#compare-prices-content > ul > li > a"));
            for (WebElement option : priceBlinkList) {
                System.out.println("Text :" + option.getAttribute("href"));
                driver.get(((WebElement) priceBlinkList.get(1)).getAttribute("href"));
                System.out.println(((ChromeDriver) driver).getCurrentUrl());
                List<String> pricelist = new ArrayList<String>();
                if (((ChromeDriver) driver).getCurrentUrl().startsWith("https://www.ebay.com")) {
//            pricelist.add(
                    ebay_price = Integer.parseInt(driver.findElements(By.cssSelector("#prcIsum")).toString().replaceAll("[$,]", ""));
                } else if (((ChromeDriver) driver).getCurrentUrl().startsWith("https://www.walmart.com")) {
                    walmart_price = Integer.parseInt(driver.findElements(By.cssSelector("body > div.js-content > div > div > div > div > div.atf-content > div > div > div:nth-child(2) > div > div.ResponsiveContainer.prod-ProductPage.prod-DefaultLayout.display-flex-ie-compat.direction-flex-column.width-full > div.prod-AboveTheFoldSection.direction-flex-column-m.display-flex-ie-compat > div.prod-rightContainer.prod-MarginTop--xs.display-flex-ie-compat.direction-flex-column > div:nth-child(1) > div.prod-Bot.prod-PositionedRelative > div > div.prod-BotRow.prod-showBottomBorder.prod-OfferSection.prod-OfferSection-twoPriceDisplay > div > div > div:nth-child(1) > div > span > div > span > span > span")).get(0).getText().replaceAll("[$,]", ""));
                }
                driver.navigate().back();
                System.out.println("asdf");

            }
            System.out.println(amazon_price);
            System.out.println(ebay_price);
            System.out.println(walmart_price);

            if(ebay_price<0.9*amazon_price)
                System.out.println("ebay is cheaper");
            if(walmart_price<0.9*amazon_price)
                System.out.println("walmart is cheaper");

        }

    }
}
