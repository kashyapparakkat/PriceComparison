package com.amazon.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class PageBase {
	WebDriver driver;
	FluentWait webDriverWait;

	
	
	private String DEPARTMENT_HOME = "#nav-link-shopall > span.nav-line-2";
	private static String EXERCISE_AND_FITNESS_DEPARTMENT = "#a-page > div.a-container.fsdContainer.fsdFullWidthImage > div > div:nth-child(5) > div:nth-child(2) > div > a:nth-child(2)"; 
	private static String ALL_CARDIO_TRAINING = "#a-page > div.a-fixed-left-flipped-grid.s-padding-left-small.s-padding-right-small.s-span-page.a-spacing-top-small > div > div.a-fixed-left-grid-col.a-col-left > div > div:nth-child(1) > div.left_nav.browseBox > p:nth-child(9) > a";
	private static String FIRST_ITEM_CARDIO = "#anonCarousel2 > ol > li:nth-child(1)";
	private static String CARDIO_ITEM = "#anonCarousel2 > ol > li:nth-child(%d) >a";
	//#anonCarousel3 > ol > li:nth-child(1) > div.a-box-group.a-spacing-top-micro.acs_product-title > a > span
	private static String CARDIO_ALL_ITEM = "#anonCarousel2 > ol > li";
	private static String ITEM_EBAY = "#ListViewInner>li>ul.lvprices.left.space-zero>li:nth-child(1)>span";
	
	private static String PRODUCT_TITLE = "#productTitle";
	private static String PRODUCT_PRICE = "#anonCarousel2 > ol > li:nth-child(%d) > div.a-box-group.a-size-small.a-spacing-none.acs_product-price > span";
	private static String WALMART_PRICE = "body > div.js-content > div > div > div > div > div.atf-content > div > div > div:nth-child(2) > div > div > "
			+ "div.ResponsiveContainer.prod-ProductPage.prod-DefaultLayout.display-flex-ie-compat.direction-flex-column.width-full > "
			+ "div.prod-AboveTheFoldSection.direction-flex-column-m.display-flex-ie-compat > div.prod-rightContainer.prod-MarginTop--xs.display-flex-ie-compat.direction-flex-column > "
			+ "div:nth-child(1) > div.prod-Bot.prod-PositionedRelative > div > div.prod-BotRow.prod-showBottomBorder.prod-OfferSection.prod-OfferSection-twoPriceDisplay > "
			+ "div > div.prod-ProductOffer.prod-PositionedRelative.Grid.prod-ProductOfferWrapper.prod-ProductOffer-enhanced > div:nth-child(1) > div:nth-child(1) > span > "
			+ "div > span > span > span > span.Price-characteristic";

	
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 30);
    }

    public boolean isUrlLoaded(String url) {
        return driver.getCurrentUrl().contains(url);
    }
    
    
    public boolean isElementVisible(String cssSelector) {
    	if( driver.findElement(By.cssSelector(cssSelector)).isDisplayed()){
    		return true;
    		}
    	return false;
    }
    
    public boolean isElementPresent(String cssSelector) {
    	if(driver.findElements(By.cssSelector(cssSelector)).size() != 0){
    		return true;
    		}
    	return false;
    }
    
    public void clickOnDepartmentsHome() {
    	driver.findElement(By.cssSelector(DEPARTMENT_HOME)).click();;
    }
    
    public void clickOnExerciseAndFitness() {
    	driver.findElement(By.cssSelector(EXERCISE_AND_FITNESS_DEPARTMENT)).click();;
    }
    
    public void clickOnAllCardioTraining() {
    	driver.findElement(By.cssSelector(ALL_CARDIO_TRAINING)).click();;
    }
    
    public void clickOnFirstCardioItem() {
    	driver.findElement(By.cssSelector(FIRST_ITEM_CARDIO)).click();;
    }
    
    public void clickOnCardioItem(int i) {
    	driver.findElement(By.cssSelector(String.format(CARDIO_ITEM, i))).click();;
    }
    
    public int getTotalCardioItems() {
    return 	driver.findElements(By.cssSelector(CARDIO_ALL_ITEM)).size();
    }
    
    public List<String> getAllEbayPrices(){
    	List<String> priceList = new ArrayList<String>();
    	List<WebElement> ebayItemsElements = driver.findElements(By.cssSelector(ITEM_EBAY));
    	for(WebElement element : ebayItemsElements) {
    		priceList.add(element.getText());
    	}
    	return priceList;
    	
    }
    
    public boolean waitTillComparePriceFramePresent() {    	
    	try {
            // Wait till the server or server cluster name is present in connection table.
            webDriverWait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (driver.findElements(By.tagName("iframe")).size()!=0);
                }
            });
        } catch (TimeoutException toe) {
            // Time out occurred
        }
    	if(driver.findElements(By.id("pb-iframe")).size()!=0) {
    		return true;
    	}
    return false;
    
    }
    
    public boolean waitTillPageLoadedCompletely() {    	
    	try {
            // Wait till the server or server cluster name is present in connection table.
            webDriverWait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (!driver.getCurrentUrl().startsWith("https://www.priceblink.com"));
                }
            });
        } catch (TimeoutException toe) {
            // Time out occurred
        }
    	if(!driver.getCurrentUrl().startsWith("https://www.priceblink.com")) {
    		return true;
    	}
    return false;
    
    }
    
    public List<String> getSiteListTocompare(){
    	List<String> priceBlinkListUrls = new ArrayList<String>();
    
    	List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        if (iframes.size() == 0) {
            // No frames
            System.out.println("No priceblink available");
        } else {
        driver.switchTo().frame("pb-iframe");


            List<WebElement> priceBlinkList = driver.findElements(By.cssSelector("#compare-prices-content > ul > li > a"));
            
            for (WebElement option : priceBlinkList) {

                priceBlinkListUrls.add(option.getAttribute("href"));

            }
            driver.switchTo().defaultContent();
    }
        return priceBlinkListUrls;
    }
    
    public String getProductPrice(int i) {
    	String a = driver.findElement(By.cssSelector(String.format(PRODUCT_PRICE, i))).getText();
    	String b = a.substring(1, a.length());
    		double price = Double.parseDouble(b);
    		price =  price-(price*0.15);
    		return "$"+String.valueOf(price);
    		
    }
    
    public String getProductTitle() {
    		return driver.findElement(By.cssSelector(PRODUCT_TITLE)).getText();
    }
    
    public void closeAllTabsExceptFirst() {
    	String originalHandle = driver.getWindowHandle();

        //Do something to open new tabs

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);
    }
    
    public String getWalmartProductPrice() {
    	return driver.findElement(By.cssSelector(WALMART_PRICE)).getAttribute("content");
    }

    
    
}
