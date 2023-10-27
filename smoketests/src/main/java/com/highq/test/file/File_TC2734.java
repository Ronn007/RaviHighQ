package com.highq.test.file;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author paras.vankadi
 */
public class File_TC2734 extends BannerPageWeb
{

	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "File_TC2734";
	String siteName1 = "File_TC2734_1";

	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";

	String userForLogin;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2734() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();

	}

	private void scenario1() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		Assert.assertTrue(aspConfigurationWeb.verifyAspAdminSystemConfigurationLabel(SystemAdminLabels.SYSTEM_FILEFILETYPE_ENABLEOPENINOFFICEONLINE_LABLE));
		Reporter.log("In general section > below Edit in Office lock time out check time(in minutes) property.");
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		Reporter.log("Enable Open in Office Online with options ON and OFF  will be display.\r\n" +
				"By default ON option will be selected.");

		Assert.assertTrue(aspConfigurationWeb.verifyOnlineOfficeProperty(optionValueON, optionValueOFF));

		// 1 Scenario 1: Property in ASP/System/Site Admin for Office online.

		Reporter.log("2.1 If Enable Open in Office Online option is OFF in ASP admin.");
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueOFF);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMessageOnAspAdmin();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		Reporter.log("- In System admin > System settings > Open in Office Online property will not be display.\r\n");
		Assert.assertFalse(systemSettingsWeb.verifySystemAdminOpenOfficeOnline());

		// 2. If Enable Open in Office Online option is OFF in ASP admin.
		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Reporter.log(" +\r\n" +
				"				\"- In Site admin > Files > Advanced settings > Enable Open in Office Online property will not be display.\"");
		Assert.assertFalse(adminFilesWeb.verifySiteAdminOpenOfficeOnline());

		Reporter.log("3. If Enable Open in Office Online option is ON in ASP admin");
		aspAdminWeb = adminFilesWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();

		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMessageOnAspAdmin();
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Assert.assertTrue(systemSettingsWeb.verifySystemAdminOpenOfficeOnline());
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(adminFilesWeb.verifySiteAdminOpenOfficeOnline());
		Reporter.log("- In System admin > System settings > Open in Office Online property will be display with options ON and OFF.\r\n" +
				"- By default ON option will be selected.");

		// 3. If Enable Open in Office Online option is ON in ASP admin.
		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		Assert.assertTrue(systemSettingsWeb.verifyOnlineOfficeForSystemAdmin(optionValueON, optionValueOFF));

		// 4. If Enable Open in Office Online option is ON in ASP admin and Open in Office Online Option is OFF in System admin.
		Reporter.log("If Enable Open in Office Online option is ON in ASP admin and Open in Office Online Option is OFF in System admin");

		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueOFF);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Reporter.log("In Site admin > Files > Advanced settings > Enable Open in Office Online property will not be display.");
		Assert.assertFalse(adminFilesWeb.verifySiteAdminOpenOfficeOnline());

		// 5.If Enable Open in Office Online option is ON in ASP admin and Open in Office Online Option is ON in System admin
		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		Reporter.log(". If Enable Open in Office Online option is ON in ASP admin and Open in Office Online Option is ON in System admin.");
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();
		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Reporter.log("In Site admin > Files > Advanced settings > Enable Open in Office Online property will be display.\r\n" +
				"\r\n" +
				"By default this property is Checked in site admin.");
		Assert.assertTrue(adminFilesWeb.verifyCheckBoxSiteAdminOpenOfficeOnline());
		logout();
	}

	private void scenario2() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(true);

		adminFilesWeb.saveFilesChanges();

		dashboardWeb = adminFilesWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName1);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(false);

		adminFilesWeb.saveFilesChanges();

		aspAdminWeb = adminFilesWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueOFF);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMessageOnAspAdmin();
		dashboardWeb = aspConfigurationWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertFalse(adminFilesWeb.verifySiteAdminOpenOfficeOnline());
		adminPageWeb.clickFilesInLeftPanel();

		dashboardWeb = adminPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName1);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertFalse(adminFilesWeb.verifySiteAdminOpenOfficeOnline());

		aspAdminWeb = adminFilesWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMessageOnAspAdmin();
		dashboardWeb = aspConfigurationWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(adminFilesWeb.verifyCheckBoxSiteAdminOpenOfficeOnline());

		adminPageWeb.clickFilesInLeftPanel();
		dashboardWeb = adminPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName1);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(!adminFilesWeb.verifyCheckBoxSiteAdminOpenOfficeOnline());

		logout();
	}

}
