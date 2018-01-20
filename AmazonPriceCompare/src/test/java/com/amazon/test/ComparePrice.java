package com.amazon.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ComparePrice {
	
    public static void main(String[] args) {
    // TODO Auto-generated method stub

    String expath = "//Users//300013717//Drivers//chromedriver";

    System.setProperty("webdriver.chrome.driver", expath);

    WebDriver driver = new ChromeDriver();



    driver.get("http:\\www.amazon.com");
    }
	

}
