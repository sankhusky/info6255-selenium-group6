package com.info6255.group6.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Group 6 Selenium Testing Assignment. Main Driver class for testing the
 * scenarios
 *
 */
public class App {

	static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		try (InputStream input = new FileInputStream("./config.properties")) {

			/*
			 * // -----------------------SETUP-------------------------------------- long
			 * scriptStartTime = System.currentTimeMillis(); Properties prop = new
			 * Properties(); prop.load(input);
			 * 
			 * // setting the driver executable
			 * System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			 * 
			 * // -------------------------------------------------------------------
			 * 
			 * // --------------------PERFORM TESTS----------------------------------
			 *//**
				 * Scenario 1
				 */
			/*
			 * // Initiating your chromedriver WebDriver driver = new ChromeDriver();
			 * 
			 * driver.manage().window().maximize(); // Login performLogin(driver, prop);
			 * 
			 * Scenario1AddToFavorites.runScenario(driver);
			 * 
			 *//**
				 * Scenario 3
				 */
			/*
			 * // Initiating your chromedriver driver = new ChromeDriver();
			 * 
			 * driver.manage().window().maximize();
			 * 
			 * // login performLogin(driver, prop, test); // Browse Classes
			 * Scenario3BrowseClasses.runScenario(driver);
			 * 
			 *//**
				 * Scenario 5
				 *//*
					 * // Initiating your chromedriver driver = new ChromeDriver();
					 * 
					 * driver.manage().window().maximize();
					 * 
					 * // login performLogin(driver, prop);
					 * 
					 * // Create a course plan Scenario5CoursePlan.runScenario(driver);
					 * 
					 * long scriptEndTime = System.currentTimeMillis();
					 * 
					 * logger.log(Level.INFO, "Script took " + (scriptEndTime - scriptStartTime) /
					 * 1000 + " seconds to complete"); // driver.close();
					 */
			// ---------------------------------------------------------------------
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void performLogin(WebDriver driver, Properties prop, ExtentTest test) {
		driver.get("https://my.northeastern.edu/");
		Utils.takeScreenShot(driver, "myneu_main_page");
		driver.findElement(By.linkText("Go To Login")).click();

		WebDriverWait wait = new WebDriverWait(driver, 100);
		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));

		username.sendKeys(prop.getProperty("NEU_USERNAME"));

		WebElement password = driver.findElement(By.id("password"));

		password.sendKeys(prop.getProperty("NEU_PASSWORD"));

		Utils.takeScreenShot(driver, "myneu_login_page_credentials");
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();

		// code to switch to iframe for duo two factor notification

		driver.switchTo().frame("duo_iframe");

		Utils.takeScreenShot(driver, "login_duo");
		driver.findElement(By.xpath(" //*[@id=\"auth_methods\"]/fieldset/div[1]/button")).click();
		driver.switchTo().defaultContent();
		test.log(Status.INFO, "Expected: Login with 2FA, Actual: Logged in with 2FA");

	}
}
