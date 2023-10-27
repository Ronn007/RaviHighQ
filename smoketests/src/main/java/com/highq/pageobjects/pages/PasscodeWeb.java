package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.PasscodePage;

public class PasscodeWeb extends BannerPageWeb implements PasscodePage
{

	WebElement element;
	String passcode;
	String sql;
	By passcodeTextBox = By.id("secondLevelAuthenticationForSystem_passcode");
	By verifyPasscodeButton = By.id("secondLevelAuthenticationForSystem_verifyPasscodeBtn");
	By sitePasscode = By.id("secondLevelAuthenticationForSite_passcode");
	By verifyPasscodeButtonSite = By.id("secondLevelAuthenticationForSite_verifyPasscodeBtn");

	public PasscodeWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author surbhi.khetan
	 *         verify passcode text field
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyPasscodePage()
	{
		return (isDisplayed(passcodeTextBox));
	}

	/**
	 * @author surbhi.khetan
	 * @param useremail
	 *        To fetch passcode of the passed user email from database
	 * @created on 21/05/2018
	 */
	@Override
	public String getPasscode(String userEmail)
	{
		sql = "SELECT passcode FROM gfn_userDetails WHERE userDetailID = (SELECT userID FROM Griffin_view_userDetail WHERE email = '" + userEmail + "')";
		String value = (String) getResult(sql);
		if (value != null)
		{
			passcode = value;
		}
		return passcode;
	}

	/**
	 * @author surbhi.khetan
	 * @param passcode
	 *        To input passcode in text field
	 * @created on 21/05/2018
	 */
	@Override
	public void enterPasscode(String passcode)
	{
		if (isDisplayed(passcodeTextBox))
		{
			element = findVisibleElement(passcodeTextBox, Speed.slow);
			element.sendKeys(passcode);
		}
		else {
			element = findVisibleElement(sitePasscode, Speed.slow);
			element.sendKeys(passcode);
		}
	}

	/**
	 * @author surbhi.khetan
	 *         To click on verify passcode button
	 * @created on 21/05/2018
	 */
	@Override
	public BannerPageWeb clickOnVerifyPasscode()
	{
		if (isDisplayed(verifyPasscodeButton))
		{
			element = findVisibleElement(verifyPasscodeButton, Speed.slow);
			element.click();
		}
		else 
		{
			element = findVisibleElement(verifyPasscodeButtonSite, Speed.slow);
			element.click();
		}
		return new BannerPageWeb();
	}
}
