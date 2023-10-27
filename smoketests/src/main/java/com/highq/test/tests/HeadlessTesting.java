package com.highq.test.tests;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTesting
{
	public static void main(String[] args) throws IOException
	{
		System.setProperty("webdriver.chrome.driver",
				"G:\\Projects\\poc-pom-sel\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://contentstack.built.io");
		System.out.println("title is: " + driver.getTitle());

		driver.get("https://www.google.co.in/");
		System.out.println("title is: " + driver.getTitle());
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("G:\\Projects\\poc-pom-sel\\resources\\screenshot.jpg"));
		driver.quit();
	}
}
