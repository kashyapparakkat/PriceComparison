package com.amazon.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.amazon.methods.PageBase;
import com.amazon.methods.SearchProductTuple;

public class GetSearchUrlsCardio {

	public static String CARDIO_SEARCH_PAGE = "https://www.amazon.com/Cardio-Life-Fitness/b/ref=amb_link_18?ie=UTF8&node=3407741&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-leftnav&pf_rd_r=28BVZCJBR769920JG2FT&pf_rd_r=28BVZCJBR769920JG2FT&pf_rd_t=101&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_p=ba6c51f4-2cff-4532-9410-74c17393ad73&pf_rd_i=3407731";
    public static String CHROME_DRIVER_PATH = "//Users//300013717//Drivers//chromedriver";
    public static String FILENAME = System.getProperty("user.home")+"/Amazon-price-comparison-urls.csv";
    public static WebDriver driver = null;
    public static PageBase pageBase;
    public static String currentCardioUrl= "";

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);	    
	    
	    
	    
        
	    //WebDriver driver = new ChromeDriver(options);
		List<String> urlList = new ArrayList<String>();
		openBrowser();
		OutputWriter.deleteFileIfExist(FILENAME);

		do {
			try {
			waitForNextPageVisibile();
			currentCardioUrl = driver.getCurrentUrl();
			
			List<SearchProductTuple> productDetails = pageBase.getAllCardioItemsRankAndUrl();
			
			OutputWriter.writeUrlRankToCsvFile(productDetails, currentCardioUrl, FILENAME);
			
			urlList.add(currentCardioUrl);
			}catch(org.openqa.selenium.WebDriverException e){
				e.printStackTrace();
				openBrowser();
				driver.get(currentCardioUrl);
				waitForNextPageVisibile();
			}
		}while(pageBase.clickOnNextPage());
		
	}
	
	public static void openBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("//Users//300013717//Downloads//MyFiles//PriceBlinkCouponsandPriceComparison.crx"), new File("//Users//300013717//Downloads//MyFiles//AMZ Seller Browser.crx"));
		options.addArguments("--start-maximized");

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    driver = new ChromeDriver(capabilities);
	    System.out.println("Opening extension");
	    
		pageBase = new PageBase(driver);
		driver.get(CARDIO_SEARCH_PAGE);

	    pageBase.closeAllTabsExceptFirst();
	}
	
	public static void waitForNextPageVisibile() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	int size = pageBase.getTotalCardioItems();
	System.out.println("Size: " + size);
	String rank = pageBase.getRankAfterWaitingTillRankLoaded(size);
	System.out.println("Rank: " + rank);
	
	
	pageBase.waitTillNextPageLinkPresent();
	}
	
	


}
