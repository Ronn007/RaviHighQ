package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.TestBaseSetup;

public class CreateAccountPage extends TestBaseSetup
{

	private By headerPageTxt = By.xpath(".//*[@id='wrapper']/div[2]/h1");

	public CreateAccountPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public String getPageTitle() throws InterruptedException
	{
		String title = driver.getTitle();
		System.out.println("Actual page title is" + title);
		// Thread.sleep(1000);
		return title;
	}

	public boolean verifyPageTitle() throws InterruptedException
	{
		// String pageTitle = "Create your Google Account";
		String pageTitle = "Sign in - Google Accounts";
		return getPageTitle().contains(pageTitle);
	}

	public boolean verifyCreateAccountPageText()
	{
		WebElement element = driver.findElement(headerPageTxt);
		String pageText = "Create your Google Account";
		return element.getText().contains(pageText);
	}

	public void createAccount()
	{
		// need to write steps for creating an account
	}
}
