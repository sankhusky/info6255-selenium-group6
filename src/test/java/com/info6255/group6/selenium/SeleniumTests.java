package com.info6255.group6.selenium;

import org.testng.annotations.Test;

import com.info6255.group6.selenium.testscenarios.Scenario1AddToFavorites;
import com.info6255.group6.selenium.testscenarios.Scenario3BrowseClasses;
import com.info6255.group6.selenium.testscenarios.Scenario5CoursePlan;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class SeleniumTests {
	Properties prop = new Properties();
	WebDriver driver = null;
	static Logger logger = Logger.getLogger(SeleniumTests.class.getName());

	@Test(priority = 0)
	public void scenario1Test() {
		/**
		 * Scenario 1
		 */
//		Login
		App.performLogin(driver, prop);

		Scenario1AddToFavorites.runScenario(driver);

	}

	@Test(priority = 1)
	public void scenario3Test() {

//		login
		App.performLogin(driver, prop);
//		Browse Classes
		Scenario3BrowseClasses.runScenario(driver);
	}

	@Test(priority = 2)
	public void scenario5Test() {
		/**
		 * Scenario 5
		 */
//		login
		App.performLogin(driver, prop);

//		Create a course plan
		Scenario5CoursePlan.runScenario(driver);

	}

	@BeforeClass
	public void beforeClass() {
		try (InputStream input = new FileInputStream("./config.properties")) {
//			Set properties
			prop.load(input);

//			setting the driver executable
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

//			driver = new ChromeDriver();
//
//			driver.manage().window().maximize();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@BeforeMethod(enabled = true)
	@BeforeTest(enabled = true)
	public void beforeTest() {
		logger.log(Level.INFO, "Before test: Instantiating driver");
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
	}

	@AfterMethod(enabled = true)
	@AfterTest(enabled = true)
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
		}
		driver =null;
	}

}
