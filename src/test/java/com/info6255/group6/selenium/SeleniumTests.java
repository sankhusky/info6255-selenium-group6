package com.info6255.group6.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.info6255.group6.selenium.testscenarios.Scenario1AddToFavorites;
import com.info6255.group6.selenium.testscenarios.Scenario3BrowseClasses;
import com.info6255.group6.selenium.testscenarios.Scenario5CoursePlan;

public class SeleniumTests {
	Properties prop = new Properties();
	WebDriver driver = null;
	static Logger logger = Logger.getLogger(SeleniumTests.class.getName());
	ExtentHtmlReporter reporter;
	ExtentReports reports;
	ExtentTest test;

	@Test(priority = 0)
	public void scenario1Test() {
		test = reports.createTest("Scenario 1: Add to favorites");
		/**
		 * Scenario 1
		 */

		try {
			// Login

			App.performLogin(driver, prop, test);

			Scenario1AddToFavorites.runScenario(driver, test);
			test.pass("Scenario 1 : PASSED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.fail("Scenario 1 : FAILED");
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void scenario3Test() {
		test = reports.createTest("Scenario 3: Browse Courses");

		try {
			//		login
			App.performLogin(driver, prop, test);
//		Browse Classes
			Scenario3BrowseClasses.runScenario(driver, test);
			test.pass("Scenario 3 : PASSED");
		} catch (Exception e) {
			test.fail("Scenario 3 : FAILED");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void scenario5Test() {
		test = reports.createTest("Scenario 5: Create Course Plan");
		/**
		 * Scenario 5
		 */

		try {
			// login
			App.performLogin(driver, prop, test);

			// Create a course plan
			Scenario5CoursePlan.runScenario(driver, test);
			test.pass("Scenario 5 : PASSED");
		} catch (Exception e) {
			test.fail("Scenario 5 : FAILED");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

			reporter = new ExtentHtmlReporter("./test-output-extent/selenium_test_report.html");
			reports = new ExtentReports();
			reports.attachReporter(reporter);

			reporter.config().setChartVisibilityOnOpen(true);
			reporter.config().setDocumentTitle("Selenium Test Report - Group 6");
			reporter.config().setReportName("Test Report");
			reporter.config().setTestViewChartLocation(ChartLocation.TOP);
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
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
		if (driver != null) {
			driver.quit();
		}
		driver = null;
	}
	
	@AfterClass
	public void afterClass() {	
		reports.flush();
	}

}
