package com.amazon.test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.amazon.methods.PageBase;
import com.amazon.methods.SearchProductTuple;

import junit.framework.Assert;
public class Junk {
	
	ChromeDriver driver;
	PageBase pageBase;
	private static String URL_AMAZONE_HOME = "https://www.amazon.com/";
	private static String URL_AMAZONE_DEPARTMENTS = "https://www.amazon.com/gp/site-directory/ref=nav_shopall_btn";
	private static String URL_AMAZONE_EXERCISE_AND_FITNESS = "https://www.amazon.com/Exercise-Equipment-Gym-Equipment/b/ref=sd_allcat_sa_sp_exfit?ie=UTF8&node=3407731";
	private static String URL_EXERCISE_CARDIO= "https://www.amazon.com/Cardio-Life-Fitness/b/ref=amb_link_18?ie=UTF8&node=3407741&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-leftnav&pf_rd_r=2KW7M9RBT4KVGDTPZVJ9&pf_rd_r=2KW7M9RBT4KVGDTPZVJ9&pf_rd_t=101&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_i=3407731";
	
	private static String HOME_ORDERS = "#nav-orders > span.nav-line-2";
	private static String SPORTS_AND_FITNESS_DEPARTMENT = "#a-page > div.a-container.fsdContainer.fsdFullWidthImage > div > div:nth-child(5) > div:nth-child(2) > div > a:nth-child(9)"; 
	private static String TITLE_EXERCISE_AND_FITNESS = "#merchandised-content >div:nth-child(3) > div > div.a-row.a-spacing-top-base > h1";
	private static String TITLE_CARDIO = "#merchandised-content > div.unified_widget.pageBanner > h1 > b";
	private static String ALL_CARDIO_TRAINING = "#a-page > div.a-fixed-left-flipped-grid.s-padding-left-small.s-padding-right-small.s-span-page.a-spacing-top-small > div > div.a-fixed-left-grid-col.a-col-left > div > div:nth-child(1) > div.left_nav.browseBox > p:nth-child(9) > a";
	private static String CARDIO_ITEM_TITLE = "#productTitle";
	
	@Test
	public void test() {
    // TODO Auto-generated method stub
    	
    String expath = "//Users//300013717//Drivers//chromedriver";

    System.setProperty("webdriver.chrome.driver", expath);
    
    
    ChromeOptions options = new ChromeOptions();
    List<File> extensionFiles = new ArrayList<File>();
	    options.addExtensions(new File("//Users//300013717//Downloads//MyFiles//PriceBlinkCouponsandPriceComparison.crx"), new File("//Users//300013717//Downloads//MyFiles//AMZ Seller Browser.crx"));

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    driver = new ChromeDriver(capabilities);
    System.out.println("Opening extension");
    
    options.addArguments("--start-maximized");

    //WebDriver driver = new ChromeDriver(options);
    pageBase = new PageBase(driver);

    driver.get(URL_AMAZONE_HOME);
	Assert.assertTrue(pageBase.isUrlLoaded(URL_AMAZONE_HOME));
	//Assert.assertTrue(false);
	Assert.assertTrue(pageBase.isElementPresent(HOME_ORDERS));
    pageBase.closeAllTabsExceptFirst();
	
	
	pageBase.clickOnDepartmentsHome();
	Assert.assertTrue(pageBase.isUrlLoaded(URL_AMAZONE_DEPARTMENTS));
	Assert.assertTrue(pageBase.isElementPresent(SPORTS_AND_FITNESS_DEPARTMENT));
	
	pageBase.clickOnExerciseAndFitness();
	Assert.assertTrue(pageBase.isUrlLoaded(URL_AMAZONE_EXERCISE_AND_FITNESS));
	Assert.assertTrue(pageBase.isElementPresent(ALL_CARDIO_TRAINING));
	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pageBase.clickOnAllCardioTraining();
	String getCardioUrl = driver.getCurrentUrl();
	Assert.assertTrue(getCardioUrl.contains("Cardio-Life-Fitness"));
	Assert.assertTrue(pageBase.isElementPresent(TITLE_CARDIO));
	
	List<String> urlList = getUrls();
	for(int i =0; i<urlList.size(); i++) {
		System.out.println(urlList.get(i));
	}
	
	/*int size = pageBase.getTotalCardioItems();
	
	List<SearchProductTuple> prodtcDeatislList = extractCardioItemsUrlAndRank();
	*/
	
	
	
	
	
	//TODO:Need to remove below line later.
	//size=1;
	
	/*for (int i=1; i<=size; i++){
		pageBase.clickOnCardioItem(i);
		//Assert.assertTrue(pageBase.isElementPresent(CARDIO_ITEM_TITLE));
		
        boolean flag = pageBase.waitTillComparePriceFramePresent();
        if (flag) {
        	List<String> priceBlinkListUrls = pageBase.getSiteListTocompare();
            
            
            for(String url: priceBlinkListUrls){

                System.out.println(url);
                driver.get(url);
                
                List<String> pricelist = new ArrayList<String>();
                if (((ChromeDriver) driver).getCurrentUrl().startsWith("https://www.ebay.com")) {
                	if(driver.findElementsByCssSelector("#ListViewInner>li").size()>0) {
                		System.out.println(pageBase.getAllEbayPrices());
                	}
                }
            }

    
    driver.get(getCardioUrl);
    	Assert.assertTrue(driver.getCurrentUrl().contains("Cardio-Life-Fitness"));
    	Assert.assertTrue(pageBase.isElementPresent(TITLE_CARDIO));
		
        }
        else {
        	System.out.println("The product"+i+"is not having any other sites to compare");
        }
	}*/
	}
	
	
	public List<SearchProductTuple> extractCardioItemsUrlAndRank() {
		//List<String> urlList = OutputWriter.readFromExcelFile(urlFile);
		List<String> urlList = new ArrayList<String>();
		urlList.add("https://www.amazon.com/Cardio-Life-Fitness/b/ref=amb_link_18?ie=UTF8&node=3407741&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-leftnav&pf_rd_r=HQW8KWWBXCHTPSD5V93F&pf_rd_r=HQW8KWWBXCHTPSD5V93F&pf_rd_t=101&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_i=3407731");
		List<SearchProductTuple> productDetails = null;
		for (int i=0; i<urlList.size(); i++){					
			driver.get(urlList.get(i));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pageBase.waitTillNextPageLinkPresent();
			productDetails = pageBase.getAllCardioItemsRankAndUrl();			
		}
		return productDetails;
	}
	
	
	public List<String> getUrls(){
		List<String> urlList = new ArrayList<String>();
		do {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size  = pageBase.getTotalCardioItems();
		System.out.println("Size: "+size);
		String rank = pageBase.getRankAfterWaitingTillRankLoaded(size);
		System.out.println("Rank: "+rank);
		pageBase.waitTillNextPageLinkPresent();
		String currentCardioUrl = driver.getCurrentUrl();
		urlList.add(currentCardioUrl);
		}while(pageBase.clickOnNextPage());
		
		return urlList;
		
	}
    

}
