package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAuditsContentManagementPage;
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
public class File_TC2778 extends BannerPageWeb
{

	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "File_2778";

	String jsonFileName = "Files/preConfiguration_File_TC2778.json";
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
	String reportingUserEmail = "reporting.onlineOffice@highq.com";// "admin.user@highq.com";
	String reportingUserPassword = "Password@123";
	AdminAuditsContentManagementPage adminAuditsContentManagementWeb;
	String openInOfficeOnlineCheckBox = "Opened in Office Online";
	String savedFromOfficeOnline = "Saved from Office Online";

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2778() throws InterruptedException, IOException
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
		logout();
		bannerPageWeb = login(reportingUserEmail, reportingUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminAuditsContentManagementWeb = adminPageWeb.clickContentManagementInLeftPanel();
		Assert.assertTrue(adminAuditsContentManagementWeb.verifyOpenedInOfficeOnlineOfContentManagement(openInOfficeOnlineCheckBox));
		Assert.assertTrue(adminAuditsContentManagementWeb.verifyOpenedInOfficeOnlineOfContentManagement(savedFromOfficeOnline));

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
