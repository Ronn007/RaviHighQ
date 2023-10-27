package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author paras.vankadi
 */
public class File_TC2703 extends BannerPageWeb
{

	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "TC2703";

	String jsonFileName = "Files/preConfiguration_File_TC2703.json";
	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	String[] option = {"Word Document", "Excel Spreadsheet", "PowerPoint Presentation", "Folder", "Placeholder file"};

	String userForLogin;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	DocumentPage documentWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2703() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1();

	}

	private void scenario1() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Enable adeptol page count */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		Reporter.log("Enable Open in O􀂣ce Online should be OFF from ASP admin → Con􀂡guration");
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueOFF);
		aspConfigurationWeb.saveConfigurations();

		dashboardWeb = aspConfigurationWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("2. There is two new button Upload and New.\r\n" +
				"3. Click on upload button.");
		documentWeb.clickNewFileButton();

		for (int i = 0; i < option.length - 2; i++)
		{
			Assert.assertFalse(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		for (int i = 3; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}

		documentWeb.goToMyFiles();
		documentWeb.clickNewFileButton();
		for (int i = 0; i < option.length - 2; i++)
		{
			Assert.assertFalse(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		for (int i = 3; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}

		// TC 2 : Disable 'Enable Online o􀂣ce' option from System Admin.
		aspAdminWeb = documentWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		Reporter.log("Enable Open in O􀂣ce Online should be OFF from ASP admin → Con􀂡guration");
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);

		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueOFF);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("2. There is two new button Upload and New.\r\n" +
				"3. Click on upload button.");
		documentWeb.clickNewFileButton();

		for (int i = 0; i < option.length - 2; i++)
		{
			Assert.assertFalse(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		for (int i = 3; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}

		documentWeb.goToMyFiles();
		documentWeb.clickNewFileButton();
		for (int i = 0; i < option.length - 2; i++)
		{
			Assert.assertFalse(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		for (int i = 3; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		// TC 3 : Disable 'Enable Online o􀂣ce' option from Site Admin
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(false);
		adminFilesWeb.saveFilesChanges();
		documentWeb = adminFilesWeb.gotoFileModule();
		documentWeb.clickNewFileButton();
		for (int i = 0; i < option.length - 2; i++)
		{
			Assert.assertFalse(documentWeb.verifyOptionOfNewButton(option[i]));
		}

		for (int i = 3; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();

		logout();

	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logout();
	}
}
