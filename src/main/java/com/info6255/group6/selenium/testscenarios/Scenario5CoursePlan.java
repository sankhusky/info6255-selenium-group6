package com.info6255.group6.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Create Plan for Course Registration
 * 
 */
//TODO Call Utils.takeScreenShot(driver, "filename") wherever necessary for before + after actions
public class Scenario5CoursePlan {
//    @Test
	static Logger logger = Logger.getLogger(Scenario5CoursePlan.class.getName());

	public static void runScenario(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Services & Links"))))
				.click();
		driver.findElement(By.linkText("Course Registration (NEW)")).click();
		
		String handle = driver.getWindowHandle();

		// switch to new window to open Banner
		for (String handles : driver.getWindowHandles()) {
			if (handles.equals(handle))
				continue;
			driver.switchTo().window(handles);
		}

		driver.findElement(By.id("planningLink")).click();

		// select term in order to create a plan
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
		WebElement semSearch = driver.findElement(By.id("s2id_autogen1_search"));
		semSearch.sendKeys("Spring 2021 Semester");
		logger.log(Level.INFO, "Selecting semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id=\"202130\"]"))))
				.click();
		driver.findElement(By.id("term-go")).click();
		
		//Search courses and add them to create a plan
		logger.log(Level.INFO, "Clicking create plan");
		driver.findElement(By.id("createPlan")).click();
		logger.log(Level.INFO, "adding course number");
		driver.findElement(By.xpath("//input[@id='txt_keywordlike']")).sendKeys("INFO5100");
		driver.findElement(By.id("search-go")).click();
		logger.log(Level.INFO, "searched course, waiting to load...");
		// Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Using xpath to find the button Add Course to the Plan
		
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[6]/div/button[2]"))))
				.click();
		logger.log(Level.INFO, "Clicked add course button");
		// driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[6]/div/button[2]")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"saveButton\"]")))).click();
		logger.log(Level.INFO, "Clicked save plan");
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.id("txt_planDesc")))).sendKeys("plan1");
		logger.log(Level.INFO, "input text for plan name");
		logger.log(Level.INFO, "clicking save...");
		driver.findElement(By.xpath("//div[@class=\"ui-dialog-buttonset\"]/button/span[contains(text(),'Save')]//parent::button")).click();

		logger.log(Level.INFO, "Saved the plan");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		logger.log(Level.INFO, "plan display--");
		
	}
}