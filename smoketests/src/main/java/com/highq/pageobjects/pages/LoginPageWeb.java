package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.LoginLabels;
import com.highq.pageobjects.base.LoginPage;

public class LoginPageWeb extends BannerPageWeb implements LoginPage
{
	By logo = By.className("logoSection TxtCenter");
	By welcomeToCollaborateText = By.tagName("Strong");
	By emailIdTextBox = By.id("Login_email");
	By passWordTextBox = By.id("Login_password");
	By rememberMeCheckBox = By.id("margleft7");
	By signInButton = By.xpath("//button[text()='" + LoginLabels.LOGINPAGE_SIGNIN + "']");
	By resetPasswordLink = By.linkText(LoginLabels.LOGINPAGE_RESETYOURPASSWORD);
	By loginThroughHighQHubLink = By.linkText(LoginLabels.LOGINPAGE_LOGINTHROUGHHIGHQHUB);
	By supportEmailLink = By.xpath("//*[contains(@class,'supportSection')]/a");
	By footerLogo = By.xpath("//*[contains(@src,'footer_logo')]");
	By footerCopyRightLink = By.xpath("//*[@class='fooCopyright']/a");
	By footerTOCLink = By.xpath("//*[@class='fooTermsofuse']/a");
	By footerHelp = By.xpath("//*[@class='fooHelp']/a");
	By loginButton = By.xpath("//*[@onclick='SubmitForm();']");
	By loginError = By.id("normalloginPage_ErrorDiv");
	By termsAndConditions = By.xpath(".//*[text()='Terms and Conditions']");
	By resetPassword = By.xpath(".//*[text()='Reset password']");
	By footerPrivacyPolicyLink = By.xpath("//*[@class='fooPrivacy']/a");

	public LoginPageWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void setEmailId(String logInEmailId)
	{
		WebElement emailTextBox = findVisibleElement(emailIdTextBox);
		if (emailTextBox.isDisplayed())
		{
			emailTextBox.clear();
			emailTextBox.sendKeys(logInEmailId);
		}

	}

	@Override
	public void setPassword(String password)
	{
		WebElement passwordTextBox = findVisibleElement(passWordTextBox);

		if (passwordTextBox.isDisplayed())
		{
			passwordTextBox.clear();
			passwordTextBox.sendKeys(password);
		}

	}

	@Override
	public DashboardWeb clickSignIn()
	{
		WebElement signinButton = findVisibleElement(loginButton, Speed.slow);
		signinButton.click();
		if (!isDisplayed(loginError, Speed.slow))
		{
			return new DashboardWeb(driver);
		}
		else
		{
			System.err.println(findVisibleElement(loginError).getText());
			driver.quit();
			return null;
		}
	}
	
	/**
	 * @author ashlesha.shastri
	 * @param desiredState
	 *        To select remember me checkbox based on the boolean value passed
	 * @created on 21/05/2018
	 */
	@Override
	public void setRememberMe(boolean desiredState)
	{
		setSelection(rememberMeCheckBox, desiredState);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify support email link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyEmailDisplayForTechnicalSupport()
	{
		return isDisplayed(supportEmailLink, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify if footer logo is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyHighQLogoDisplayInFooter()
	{
		return isDisplayed(footerLogo, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify if copyrights link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyCopyRightsLink()
	{
		return isDisplayed(footerCopyRightLink, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify terms of use link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyTermsOfUseLink()
	{
		return isDisplayed(footerTOCLink, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify help link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyHelpLink()
	{
		return isDisplayed(footerHelp, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify Privacy Policy link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyPrivacyPolicyLink()
	{
		return isDisplayed(footerPrivacyPolicyLink, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 *         To click email in technical support
	 * @created on 21/05/2018
	 */
	@Override
	public void clickEmailDisplayForTechnicalSupport()
	{
		WebElement element = findVisibleElement(supportEmailLink, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on highQlogo in footer
	 * @created on 21/05/2018
	 */
	@Override
	public void clickHighQLogoDisplayInFooter()
	{
		WebElement element = findVisibleElement(footerLogo, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on copyrights link
	 * @created on 21/05/2018
	 */
	@Override
	public void clickCopyRightsLink()
	{
		WebElement element = findVisibleElement(footerCopyRightLink, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on terms of use link
	 * @created on 21/05/2018
	 */
	@Override
	public void clickTermsOfUseLink()
	{
		WebElement element = findVisibleElement(footerTOCLink, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on help link
	 * @created on 21/05/2018
	 */
	@Override
	public void clickHelpLink()
	{
		WebElement element = findVisibleElement(footerHelp, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on privacy policy link
	 * @created on 21/05/2018
	 */
	/**
	 * @author ashlesha.shastri
	 */
	@Override
	public void clickPrivacyPolicyLink()
	{
		WebElement element = findVisibleElement(footerPrivacyPolicyLink, Speed.slow);
		element.click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify if reset password link is displayed or not
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyResetPasswordLInk()
	{
		return isDisplayed(resetPasswordLink);
	}

	/**
	 * @author ashlesha.shastri
	 *         To click on reset password link
	 * @created on 21/05/2018
	 */
	@Override
	public void clickResetPasswordLink()
	{
		findVisibleElement(resetPasswordLink, Speed.slow).click();
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify terms and conditions page
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyTermsAndConditionPage()
	{
		return isDisplayed(termsAndConditions);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify reset password page
	 * @created on 21/05/2018
	 */
	@Override
	public boolean verifyResetPasswordPage()
	{
		return isDisplayed(resetPassword);
	}



}
