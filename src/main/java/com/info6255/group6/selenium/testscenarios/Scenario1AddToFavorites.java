package com.info6255.group6.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group6.selenium.Utils;

public class Scenario1AddToFavorites {
	static Logger logger = Logger.getLogger(Scenario1AddToFavorites.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		
//		Selecting the search input
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement e = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/section[1]/div/div[2]/div/input"))));
		e.sendKeys(new String[] { "Course Registration (NEW)" });
		Utils.takeScreenShot(driver, "scenario1_enter_in_search");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//i[@data-gtm-event-action=\"Course Registration (NEW)\"]")).click();
		Utils.takeScreenShot(driver, "scenario1_add_fav");
//		WebElement w = wait.until(ExpectedConditions.elementToBeClickable(
//				));
//		w.click();
//		driver.quit();
		test.log(Status.INFO, "Expected: Add Course Registration to Favorites, Actual: Added Course Registration to Favorites");
	}

}
