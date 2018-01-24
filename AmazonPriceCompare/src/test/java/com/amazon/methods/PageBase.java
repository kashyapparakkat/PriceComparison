package com.amazon.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
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
	private static String CARDIO_ITEM = "#anonCarousel2 > ol > li:nth-child(%d)";
	private static String CARDIO_ALL_ITEM = "#anonCarousel2 > ol > li";
	private static String ITEM_EBAY = "#ListViewInner>li>ul.lvprices.left.space-zero>li:nth-child(1)>span";

	
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
                    return (driver.findElements(By.tagName("pb-iframe")).size()!=0);
                }
            });
        } catch (TimeoutException toe) {
            // Time out occurred
        }
    	if(driver.findElements(By.tagName("pb-iframe")).size()!=0) {
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

    
    
}
