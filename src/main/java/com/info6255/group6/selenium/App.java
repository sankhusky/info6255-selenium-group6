package com.info6255.group6.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//setting the driver executable
//    	System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
    	System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

    	//Initiating your chromedriver
    	WebDriver driver=new ChromeDriver();

    	//Applied wait time
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	//maximize window
    	driver.manage().window().maximize();

    	//open browser with desried URL
    	driver.get("https://www.google.com");

    	//closing the browser
    	driver.close();
    	
    }
}
