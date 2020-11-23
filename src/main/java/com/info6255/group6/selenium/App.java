package com.info6255.group6.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.info6255.group6.selenium.testscenarios.Scenario3BrowseClasses;

/**
 * Hello world!
 *
 */
public class App {

	static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		try (InputStream input = new FileInputStream("./config.properties")) {

//			-----------------------SETUP--------------------------------------
			long scriptStartTime = System.currentTimeMillis();
			Properties prop = new Properties();
			prop.load(input);

//			setting the driver executable
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

			// Initiating your chromedriver
			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();
//			-------------------------------------------------------------------

//			--------------------PERFORM TESTS----------------------------------
//			Login
			performLogin(driver, prop);

//			Browse Classes
			Scenario3BrowseClasses.runScenario(driver);

//			TODO Add other scenarios

			long scriptEndTime = System.currentTimeMillis();

			logger.log(Level.INFO, "Script took " + (scriptEndTime - scriptStartTime) / 100 + " seconds to complete");
//			driver.close();
//			---------------------------------------------------------------------
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private static void performLogin(WebDriver driver, Properties prop) {
		driver.get("https://my.northeastern.edu/");
		driver.findElement(By.linkText("Go To Login")).click();

		WebDriverWait wait = new WebDriverWait(driver, 100);
		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));

		username.sendKeys(prop.getProperty("NEU_USERNAME"));

		WebElement password = driver.findElement(By.id("password"));

		password.sendKeys(prop.getProperty("NEU_PASSWORD"));

		driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();

		// code to switch to iframe for duo two factor notification

		driver.switchTo().frame("duo_iframe");

		driver.findElement(By.xpath(" //*[@id=\"auth_methods\"]/fieldset/div[1]/button")).click();
		driver.switchTo().defaultContent();

	}
}
