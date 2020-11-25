package com.info6255.group6.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Sanket
 *
 */
public class Utils {

	public static void takeScreenShot(WebDriver driver, String fileName) {

//		try {
//			File screenshotFolder = new File(".\\screenshot\\");
//			if (!screenshotFolder.exists() && !screenshotFolder.isDirectory()) {
//				screenshotFolder.mkdir();
//			}
//			Screenshot screenshot = new AShot().takeScreenshot(driver);
//			ImageIO.write(screenshot.getImage(), "jpg", new File(".\\screenshot\\" + fileName + "_screenshot.jpg"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(".\\screenshot\\" + fileName + "_screenshot.png"));
        } catch (IOException e)
        {
            System.out.println(e.getMessage());

        }
	}
}
