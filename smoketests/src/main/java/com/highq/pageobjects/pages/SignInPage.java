package com.highq.pageobjects.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.google.common.base.Function;
import com.highq.base.TestBaseSetup;

public class SignInPage
{

	private WebDriver driver;

	private By headerPageHeadingText = By.id("headingText");
	private By createAccountLink = By.xpath(".//*[@id='SIGNUP']");
	private By moreOptionsText = By.xpath(".//*[@id='view_container']/form/div[2]/div/div[2]/div[2]/div");
	private By emailTextBox = By.id("identifierId");
	private By nextBtn = By.id("identifierNext");
	private By passwordTextBox = By.name("password");
	private By loginBtn = By.id("passwordNext");
	private By errorMsgTxt = By.xpath(".//*[@id='password']/div[2]/div[2]");

	public SignInPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public String getSignInPageTitle()
	{
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public boolean verifySignInPageTitle()
	{
		String expectedTitle = "Sign in - Google Accounts";
		return getSignInPageTitle().contains(expectedTitle);
	}

	public boolean verifySignInPageText()
	{
		WebElement element = driver.findElement(headerPageHeadingText);
		String pageText = element.getText();
		String expectedPageText = "Sign in";
		return pageText.contains(expectedPageText);
	}

	public CreateAccountPage clickonCreateAnAccount() throws InterruptedException
	{

		WebElement moreOptionselement = driver.findElement(moreOptionsText);
		System.out.println("Searching for More options Text");
		if (moreOptionselement.isDisplayed() || moreOptionselement.isEnabled())
		{
			System.out.println("More options found - Clicking on More options link");
			moreOptionselement.click();
			System.out.println("Searching for Create Account link");
			WebElement createAccLnk = driver.findElement(createAccountLink);
			if (createAccLnk.isDisplayed())
			{
				System.out.println("Clicking on Create Account button");
				createAccLnk.click();
				Thread.sleep(2000L);
			}
			else
				System.out.println("Create Account Element not found");
		}
		else
			System.out.println("More Options not found");

		return new CreateAccountPage(driver);
	}

	public void clickCreateAccount()
	{

	}

	public boolean verifySignIn() throws InterruptedException, IOException
	{
		enterUserName("satsangi.beas");
		clickNext();
		Thread.sleep(2000L);
		enterPassword("pass");
		clickOnSignIn();
		Thread.sleep(2000L);
		return getErrorMessage().contains("Wrong");
	}

	public void enterUserName(String userName)
	{
		WebElement emailTxtBox = driver.findElement(emailTextBox);
		if (emailTxtBox.isDisplayed())
			emailTxtBox.sendKeys(userName);
	}

	public void enterPassword(String password) throws IOException
	{

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(TestBaseSetup.driverPath + "screenshot.png"));
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement passwordTxtBox = wait.until(new Function<WebDriver, WebElement>()
		{
			@Override
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(passwordTextBox);
			}
		});

		// WebElement passwordTxtBox = driver.findElement(passwordTextBox);
		if (passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(password);
	}

	public void clickNext()
	{
		WebElement nextButton = driver.findElement(nextBtn);
		if (nextButton.isDisplayed())
			nextButton.click();
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				return driver.findElement(passwordTextBox).isDisplayed();
			}
		});
	}

	public void clickOnSignIn()
	{
		WebElement signInBtn = driver.findElement(loginBtn);
		if (signInBtn.isDisplayed())
			signInBtn.click();
	}

	public String getErrorMessage()
	{
		String strErrorMsg = null;
		WebElement errorMsg = driver.findElement(errorMsgTxt);
		if (errorMsg.isDisplayed() && errorMsg.isEnabled())
			strErrorMsg = errorMsg.getText();
		return strErrorMsg;
	}
}
