package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.ViewSharedContentPage;

public class ViewSharedContentWeb extends BannerPageWeb implements ViewSharedContentPage
{

	By selfRegisterEmailIdTextBox = By.id("selfRegisteredUserEmailAddress");
	By verifyEmailAddress = By.xpath(".//*[@id='centerColorBox']//button");
	By passwordText = By.id("Login_password");
	By confirmPasswordText = By.id("Login_confirmpassword");
	By termsAndCondition = By.id("ChoosePasswordTermAndCondition");
	By setSubmitButton = By.xpath(".//*[@id='choosepasswordform']/button");
	By userAvtar = By.xpath("//*[@data-toggle = 'dropdown']//i");
	By logoutLink = By.xpath(".//*[@class='dropdown-menu pull-right']//*[normalize-space(text())='Logout']");

	public ViewSharedContentWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author ankit.motaval
	 *         Set Self Registered Email
	 * @param logInEmailId
	 * @created on 09/05/2018
	 */
	public void setSelfRegisterUserEmailId(String logInEmailId)
	{
		WebElement emailTextBox = findVisibleElement(selfRegisterEmailIdTextBox);
		if (emailTextBox.isDisplayed())
		{
			emailTextBox.clear();
			emailTextBox.sendKeys(logInEmailId);
		}
	}

	/**
	 * @author ankit.motaval
	 *         click on Verify your email address
	 * @created on 09/05/2018
	 */
	public void clickOnVerifyEmailAddress()
	{
		WebElement verifyButton = findVisibleElement(verifyEmailAddress);
		if (verifyButton.isDisplayed())
		{
			verifyButton.click();
		}
	}

	/**
	 * @author ankit.motaval
	 *         set Password
	 * @param password
	 * @created on 09/05/2018
	 */
	public void setPassword(String password)
	{
		WebElement passwordTextBox = findVisibleElement(passwordText);
		passwordTextBox.sendKeys(password);

		WebElement confirmPasswordTextBox = findVisibleElement(confirmPasswordText);
		confirmPasswordTextBox.sendKeys(password);

		WebElement termsAndConditionChk = findVisibleElement(termsAndCondition);
		termsAndConditionChk.click();
	}

	/**
	 * @author ankit.motaval
	 *         Click on submit Button
	 * @created on 09/05/2018
	 */
	public void clickSubmit()
	{
		WebElement submitButton = findVisibleElement(setSubmitButton);
		if (submitButton.isDisplayed())
		{
			submitButton.click();
		}
	}

	/**
	 * @author ankit.motaval
	 *         Logout
	 * @created on 09/05/2018
	 */
	public LoginPageWeb logout()
	{
		WebElement userAvatar = findClickableElement(userAvtar, Speed.slow);
		userAvatar.click();

		WebElement logout = findClickableElement(logoutLink, Speed.slow);
		logout.click();

		WebElement logOutConfirmationOkButton = findVisibleElement(footer_Ok);
		if (logOutConfirmationOkButton.isDisplayed())
		{
			logOutConfirmationOkButton.click();
		}
		return new LoginPageWeb(driver);
	}
}
