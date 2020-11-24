package com.info6255.group6.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Sanket
 *
 */
public class Utils {

	public void takeScreenShot(WebDriver driver,int i) throws IOException {
        File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//        try {
//            FileUtils.copyFile(srcFile, new File(".\\Screenshots\\screen"+i+".png"));
//        } catch (IOException e)
//        {
//            System.out.println(e.getMessage());
//
//        }
    }
}
