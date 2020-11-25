package com.info6255.group6.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group6.selenium.Utils;
/**
 * Browse classes for spring 21 sem
 * @author Sanket Pimple
 *
 */
public class Scenario3BrowseClasses {
//	TODO Add code for screenshot
	static Logger logger = Logger.getLogger(Scenario3BrowseClasses.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) {
		WebDriverWait wait = new WebDriverWait(driver, 100);

		Utils.takeScreenShot(driver, "scenario3_myneu_homepage_before"); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label=\"Services & Links \"]"))).click();
//		String winHandleBefore = driver.getWindowHandle(); //--Store current window handle if needed to return here

		long startRegistrationTimer = System.currentTimeMillis();
//		 Perform the click operation that opens new window
		Utils.takeScreenShot(driver, "scenario3_services_and_links_after");
		driver.findElement(By.xpath("//a[@data-text=\"Course Registration (NEW)\"]")).click();
		logger.log(Level.INFO, "Clicked course registration, waiting for new window");

//		 Switch to new window
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

//		Click on Browse for classes
		logger.log(Level.INFO, "switched to new window, waiting for page to load.........");
		WebElement browseClassLink = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("classSearchLink"))));
		long endRegistrationTimer = System.currentTimeMillis();
		Utils.takeScreenShot(driver, "scenario3_course_registration_home");
		logger.log(Level.INFO, "Registration window loaded, time taken= " + (endRegistrationTimer-startRegistrationTimer) + " milliseconds");
		browseClassLink.click();

//		Select a Term - Spring 2021 semester 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
		WebElement semSearch = driver.findElement(By.id("s2id_autogen1_search"));
		semSearch.sendKeys("Spring 2021 Semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utils.takeScreenShot(driver, "scenario3_show_term");
		logger.log(Level.INFO, "got sem list");

		// Select the spring 2021 div from the dropdown and click
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id=\"202130\"]"))))
				.click();
		logger.log(Level.INFO, "clicked semester");

		Utils.takeScreenShot(driver, "scenario3_select_term");
//		Click Continue button
		driver.findElement(By.id("term-go")).click();
//		Click the div containing Subject input to activate it
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_subject")))).click();

		Utils.takeScreenShot(driver, "scenario3_select_subject_before");
//		Add text to input and select Information Systems program
		WebElement subjectInput = driver
//				.findElement(By.xpath("//input[@id=\"s2id_autogen1\" and @class=\"select2-input\"]"));
				.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));
		subjectInput.sendKeys("Information Systems");
		driver.findElement(By.id("INFO")).click();
		Utils.takeScreenShot(driver, "scenario3_select_subject_info_systems_after");
		driver.findElement(By.id("search-go")).click(); // Select
		logger.log(Level.INFO, "Selected the subject");

		long startTimeCourses = System.currentTimeMillis();
		logger.log(Level.INFO, "Selecting 50 items per page"); // Courses expected to be displayed here
//		Show 50 courses per page
		Select pagesDropDown = new Select(wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select")))));
		pagesDropDown.selectByValue("50");

		long endTimeCourses = System.currentTimeMillis();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select"))));
		Utils.takeScreenShot(driver, "scenario3_display_courses");
		logger.log(Level.INFO, "It took " + (endTimeCourses - startTimeCourses)
				+ "milliseconds to get the courses, and select 50 items per page");
		/*
		 * logger.log(Level.INFO,"Scrolling to bottom");
		 * jsEx.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 * logger.log(Level.INFO,"Finished Scrolling to bottom");
		 */
		test.log(Status.INFO, "Expected: Browse Courses, Actual: Browsed courses successfully");
//		driver.quit();
	}

}
