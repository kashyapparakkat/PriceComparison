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

import junit.framework.Assert;

public class ComparePrice2 {
	
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
	private static String FIRST_PRODUCT_PRICE = "#mainResults > ul > li:nth-child(1) > div > div:nth-child(7) > div:nth-child(1) > a > span.a-color-base.sx-zero-spacing > span > span";

	
	
	String fileName = System.getProperty("user.home")+"/Amazon-price-comparison.csv";
    String CHROME_DRIVER_PATH = "//Users//300013717//Drivers//chromedriver";
    
	ArrayList<PriceList> outputWriterList = new ArrayList<PriceList>();
	
	//@Test
	public void test() throws InterruptedException {
	    // TODO Auto-generated method stub
	    	
	
	    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
	    
	    
	    ChromeOptions options = new ChromeOptions();
	    List<File> extensionFiles = new ArrayList<File>();
		    options.addExtensions(new File("//Users//300013717//Downloads//MyFiles//PriceBlinkCouponsandPriceComparison.crx"), new File("//Users//300013717//Downloads//MyFiles//AMZ Seller Browser.crx"));
	
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    ChromeDriver driver = new ChromeDriver(capabilities);
	    System.out.println("Opening extension");
	    
	    options.addArguments("--start-maximized");
	
	    //WebDriver driver = new ChromeDriver(options);
		PageBase pageBase = new PageBase(driver);
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
		
		pageBase.clickOnAllCardioTraining();
		String getCardioUrl = driver.getCurrentUrl();
		Assert.assertTrue(getCardioUrl.contains("Cardio-Life-Fitness"));
		Assert.assertTrue(pageBase.isElementPresent(TITLE_CARDIO));
		
		
		int size = pageBase.getTotalCardioItems();
		
		//TODO:Need to remove below line later.
		size=10;
		
		outputWriter.createFileWithHeaders(fileName);
		
		for (int i=1; i<=size; i++){
			PriceList priceList1 = new PriceList();
			
			
			String rank = pageBase.clickOnCardioItem(i);
			System.out.println("\nProduct rank:"+rank);

			System.out.println("\nProduct Name:"+pageBase.getProductTitle());
			
			double a = Double.parseDouble(pageBase.getProductPrice(i).replaceAll("[$]",""))*0.85;
			System.out.println("\nProduct price(after reducing 15% profit used by amazon):"+a);
			
			priceList1.setRanking(rank);
			priceList1.setAmazonReducedPrice(0.85 * a);
			
			priceList1.setAmazonPrice(a);
			

			priceList1.setProductTitle(pageBase.getProductTitle());
			Assert.assertTrue(pageBase.isElementPresent(CARDIO_ITEM_TITLE));
			
	        Thread.sleep(10000);
	        boolean flag = pageBase.waitTillComparePriceFramePresent();
	        if (flag) {
	        		List<String> priceBlinkListUrls = pageBase.getSiteListTocompare();
	            
	            
	            for(String url: priceBlinkListUrls){
	
	                driver.get(url);
	                pageBase.waitTillPageLoadedCompletely();
	                
	//                List<String> pricelist = new ArrayList<String>();
	                
	                if (((ChromeDriver) driver).getCurrentUrl().startsWith("https://www.ebay.com")) {
		                	if(driver.findElementsByCssSelector("#ListViewInner>li").size()>0) {
		
		                		//System.out.println("\nThe price in ebay is "+pageBase.getAllEbayPrices());
		                		priceList1.setEbayUrl( ((ChromeDriver) driver).getCurrentUrl());
		                		try {
		                			priceList1.setEbayPrice(pageBase.getAllEbayPrices());
		    	                	}
		    	                	catch(Exception ex){
		    	                		priceList1.setComments(priceList1.getComments() + "ebay price capture failed;");
		    	                	}
		                	}
	                }
	                else if(driver.getCurrentUrl().startsWith("https://www.walmart.com")) {
		                	
		                	priceList1.setWalmartUrl( ((ChromeDriver) driver).getCurrentUrl());
		                	try {
		                		priceList1.setWalmartPrice(pageBase.getWalmartProductPrice());
		                		//System.out.println("\n The price in walmart is "+pageBase.getWalmartProductPrice());
		                	}
		                	catch(Exception ex){
		                		priceList1.setComments(priceList1.getComments() + "Walmart price capture failed;");
		                	}
	
	                }
	            }
	                	
	            if (priceList1.getAmazonReducedPrice()>0 && 
	            		(priceList1.getAmazonReducedPrice()<priceList1.getEbayPrice() || (priceList1.getEbayPrice() == -1)) && 
	            		(priceList1.getAmazonReducedPrice()<priceList1.getWalmartPrice() || (priceList1.getWalmartPrice() == -1)))
	            {
	    				priceList1.setCheapest("amazon");
	    				
	            }
	            else if (priceList1.getEbayPrice()>0 && 
	            		(priceList1.getEbayPrice()<priceList1.getAmazonReducedPrice() || (priceList1.getAmazonReducedPrice() == -1)) && 
	            		priceList1.getEbayPrice()<priceList1.getWalmartPrice() || (priceList1.getWalmartPrice() == -1))
	            {
	            		priceList1.setCheapest("ebay");
	            		priceList1.setProfitPercent(100*(priceList1.getAmazonReducedPrice()-priceList1.getEbayPrice())/priceList1.getAmazonReducedPrice());
	            }
	            else if (priceList1.getWalmartPrice()>0 && 
	            		(priceList1.getWalmartPrice()<priceList1.getAmazonReducedPrice() || (priceList1.getAmazonReducedPrice() == -1)) && 
	            		priceList1.getWalmartPrice()<priceList1.getEbayPrice() || (priceList1.getEbayPrice() == -1))
	            { 	
		            	priceList1.setCheapest("walmart");
		            priceList1.setProfitPercent(100*(priceList1.getAmazonReducedPrice()-priceList1.getWalmartPrice()/priceList1.getAmazonReducedPrice()));
	            }
	            
	        }
	       
			
	        
	        else {
	        	System.out.println("The product"+i+"is not having any other sites to compare");
	        	priceList1.setComments("The product is not having any suggestions from price Blink");
	        }
	        
	         
			outputWriterList.add(priceList1);
			
	    driver.get(getCardioUrl);
	    Thread.sleep(5000);
	    	Assert.assertTrue(driver.getCurrentUrl().contains("Cardio-Life-Fitness"));
	    	//Assert.assertTrue(pageBase.isElementPresent(FIRST_PRODUCT_PRICE));
	        System.out.println("\n\n\n");
	        
			outputWriter.writeOrAppendToFile(fileName, priceList1);

		}
	    		}

}
