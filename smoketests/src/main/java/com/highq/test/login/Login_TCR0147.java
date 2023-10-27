package com.highq.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.PasscodePage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.PasscodeWeb;

/**
 * @author ashlesha.shastri
 */
public class Login_TCR0147 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	DashboardPage dashboardPageWeb;
	SystemAdminPage systemAdminPageWeb;
	SystemAdminSystemSettingsPage systemSettingsPageWeb;
	LoginPage loginPageWeb;
	AspAdminPage aspAdminPageWeb;
	AspConfigurationPage aspConfigurationPageWeb;
	PasscodePage passcodePageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = "TRUE";
	String currentURL;
	String highqURL = "https://highq.com/";
	String termsOfUsePageURL = "/termsOfUse.action?timestamp=";
	String highqHelpPageURL = "http://kb-collaborate.highq.com/home";
	String privacyPolicyPageURL = "privacyPolicy.action?timestamp=";
	String resetPasswordPageURL = "ForgotPassword_loadEmail.action";
	String twoFactorAuthentication = "For all users";

	/**
	 * @author ashlesha.shastri
	 */
	@Test
	public void loginTCR0147() throws InterruptedException
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
	}

	public void scenario1()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(((DashboardPage) bannerPageWeb).verifyAddSiteLink());
		logout();
	}

	public void scenario2() throws InterruptedException
	{

		try
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			systemAdminPageWeb = bannerPageWeb.gotoSystemAdmin();
			systemSettingsPageWeb = systemAdminPageWeb.gotoSystemSettings();
			systemSettingsPageWeb.enableTwoFactorAuthenticationForUsersOrSystemAdmin(twoFactorAuthentication, true);
			systemSettingsPageWeb.saveSettings();
			logout();

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			passcodePageWeb = new PasscodeWeb(driver);
			Assert.assertTrue(passcodePageWeb.verifyPasscodePage());
			passcodePageWeb.enterPasscode(passcodePageWeb.getPasscode(sysAdminEmail));
			passcodePageWeb.clickOnVerifyPasscode();
			Assert.assertTrue(bannerPageWeb.verifyUserAvtarLink());
		}
		finally
		{

			systemAdminPageWeb = bannerPageWeb.gotoSystemAdmin();
			systemSettingsPageWeb = systemAdminPageWeb.gotoSystemSettings();
			systemSettingsPageWeb.enableTwoFactorAuthenticationForUsersOrSystemAdmin(twoFactorAuthentication, false);
			systemSettingsPageWeb.saveSettings();
			logout();
		}

		/** Verification for mail is not done as mail for passcode is not stored in database */
	}

	public void scenario3()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword, true);
		bannerPageWeb.openTabHitPath(url);
		Assert.assertTrue(bannerPageWeb.verifyUserAvtarLink());
		bannerPageWeb.closeCurrentTab();
		logout();
	}

	public void scenario4()
	{
		/** This scenario is about Login Through highq hub. It can not be automated. */
	}

	public void scenario5()
	{
		loginPageWeb = bannerPageWeb.login();
		Assert.assertTrue(loginPageWeb.verifyResetPasswordLInk());
		loginPageWeb.clickResetPasswordLink();
		currentURL = getCurrentURL();
		Assert.assertTrue(currentURL.contains(resetPasswordPageURL));
		loginPageWeb.goBackInBrowser();

		/**
		 * This scenario is about Reset Password.
		 * It is partially automated but can not fully automated
		 * because CAPTCHA word for verification can not handle by selenium
		 */
	}

	public void scenario6()
	{
		/** This scenario is about Login with Manual SSO. It can not be automated. */
	}

	public void scenario7() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminPageWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationPageWeb = aspAdminPageWeb.openConfigurationPage();
		aspConfigurationPageWeb.setEnablePrivacyPolicy(optionToSelect);
		aspConfigurationPageWeb.saveConfigurations();
		logout();

		loginPageWeb = bannerPageWeb.login();
		Assert.assertTrue(loginPageWeb.verifyEmailDisplayForTechnicalSupport());

		Assert.assertTrue(loginPageWeb.verifyHighQLogoDisplayInFooter());
		loginPageWeb.clickHighQLogoDisplayInFooter();
		switchWindow();
		currentURL = getCurrentURL();
		Assert.assertTrue(currentURL.equals(highqURL));
		closeCurrentTab();

		Assert.assertTrue(loginPageWeb.verifyCopyRightsLink());
		loginPageWeb.clickCopyRightsLink();
		currentURL = loginPageWeb.getCurrentURL();
		Assert.assertTrue(currentURL.equals(highqURL));
		goBackInBrowser();

		Assert.assertTrue(loginPageWeb.verifyTermsOfUseLink());
		loginPageWeb.clickTermsOfUseLink();
		switchWindow();
		currentURL = getCurrentURL();
		Assert.assertTrue(currentURL.contains(termsOfUsePageURL));
		Assert.assertTrue(loginPageWeb.verifyTermsAndConditionPage());
		closeCurrentTab();

		Assert.assertTrue(loginPageWeb.verifyHelpLink());
		loginPageWeb.clickHelpLink();
		switchWindow();
		currentURL = loginPageWeb.getCurrentURL();
		Assert.assertTrue(currentURL.contains(highqHelpPageURL));
		closeCurrentTab();

		Assert.assertTrue(loginPageWeb.verifyPrivacyPolicyLink());
		loginPageWeb.clickPrivacyPolicyLink();
		switchWindow();
		currentURL = getCurrentURL();
		Assert.assertTrue(currentURL.contains(privacyPolicyPageURL));
		closeCurrentTab();

	}

}
