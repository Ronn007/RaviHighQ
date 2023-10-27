package com.highq.test.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.LoginPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TC2754 extends BannerPageWeb
{
	/** Add_Edit_Delete_Download File */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String normalUserEmail = "normal.onlineOffice@Highq.com";// "admin.user@highq.com";
	String normalUserPassword = "Password@123";
	String siteUserEmail = "site.onlineOffice@highq.com";// "admin.user@highq.com";
	String SiteUserPassword = "Password@123";

	String siteName = "File_TC2754";
	String[] userNames = {"tom.chick", "site.onlineOffice", "contain.onlineOffice"};
	String[] userEmail = {"tom.chick@Highq.com", "site.onlineOffice@highq.com", "contain.onlineOffice@highq.com"};

	String jsonFileName = "Files/preConfiguration_File_TC2754.json";
	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	String[] addItem = {"Files", "Zipped files", "Placeholder file", "New folder"};
	String folderDescription = "Folder for File_TC2754";

	String[] files = {"File5.docx", "File8.pptx", "File1.xlsx"};
	String userForLogin;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DocumentPage documentWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	AddUserPage addUserWeb;
	SystemAdminPage systemAdminWeb;
	Map<String, String> fileOpts;
	BannerPage bannerPageWebSimultaneously;
	DocumentPage documentWebSimultaneously;
	DashboardPage dashboardWebSimultaneously;
	AdminPage adminPageWebSimultaneously;
	AdminFilesPage adminFilesWebSimultaneously;
	String[] documentOption = {"Word Online", "Excel Online", "PowerPoint Online"};
	String[] documentName = {"Word", "Excel", "PowerPoint"};
	String moreOptionItem = "Open in...";
	String[] testFileTypes = {"docx", "xlsx", "pptx"};
	Map<String, String> zipFileMap = new HashMap<>();
	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	String newTag = "tag2";
	String newDisclaimer = "disclaimer2";
	String zipFile = "onlineSupportedFiles.zip";
	String openInOpt = "Open in...";

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2754() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario6();

	}

	private void scenario1() throws InterruptedException, IOException
	{

		bannerPageWeb = login(normalUserEmail, normalUserPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);

		documentWeb = bannerPageWeb.gotoFileModule();
		for (int j = 0; j < files.length; j++)
		{

			documentWeb.clickOnSubmenuOptionOfFileMoreActions(files[j], moreOptionItem, documentOption[j]);
			documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(documentOption[j]);
			documentWeb.switchWindow(0);
			documentWeb.gotoFileModule();
			Assert.assertTrue(!documentWeb.verifyOnSubmenuOptionOfFile(files[j], moreOptionItem, documentName[j]));

		}

		logout();

	}

	private void scenario2() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		LoginPageWeb loginPageWeb;
		dashboardWeb.searchSite(siteName);
		documentWeb = bannerPageWeb.gotoFileModule();

		for (int j = 0; j < files.length; j++)
		{

			documentWeb.clickOnSubmenuOptionOfFileMoreActions(files[j], moreOptionItem, documentOption[j]);
			documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(documentOption[j]);
			documentWeb.switchWindow();

			documentWeb.closeCurrentTab();
			WebDriver simultaneousDriver = setDriver(url);
			simultaneousDriver.get(url);
			loginPageWeb = new LoginPageWeb(simultaneousDriver);
			loginPageWeb.setEmailId(normalUserEmail);
			loginPageWeb.setPassword(normalUserPassword);
			bannerPageWebSimultaneously = loginPageWeb.clickSignIn();
			dashboardWebSimultaneously = bannerPageWebSimultaneously.gotoDashboard();
			dashboardWebSimultaneously.searchSite(siteName);
			documentWebSimultaneously = bannerPageWebSimultaneously.gotoFileModule();
			documentWebSimultaneously.clickOnSubmenuOptionOfFileMoreActions(files[j], moreOptionItem, documentOption[j]);

			documentWebSimultaneously.switchWindow(0);

			documentWebSimultaneously.gotoFileModule();
			Assert.assertTrue(!documentWebSimultaneously.verifyOnSubmenuOptionOfFile(files[j], moreOptionItem, documentName[j]));

			simultaneousDriver.quit();

		}
		logout();
	}

	private void scenario3() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		LoginPageWeb loginPageWeb;
		documentWeb = bannerPageWeb.goToMyFiles();
		for (int j = 0; j < files.length; j++)
		{

			documentWeb.clickOnSubmenuOptionOfFileMyFiles(files[j], moreOptionItem, documentOption[j]);
			Assert.assertTrue(documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(documentOption[j]));
			documentWeb.switchWindow();

			documentWeb.closeCurrentTab();
			WebDriver simultaneousDriver = setDriver(url);
			simultaneousDriver.get(url);
			loginPageWeb = new LoginPageWeb(simultaneousDriver);
			loginPageWeb.setEmailId(normalUserEmail);
			loginPageWeb.setPassword(normalUserPassword);
			bannerPageWebSimultaneously = loginPageWeb.clickSignIn();
			documentWebSimultaneously = bannerPageWebSimultaneously.goToMyFiles();
			documentWebSimultaneously.clickOnSubmenuOptionOfFileMyFiles(files[j], moreOptionItem, documentOption[j]);

			documentWebSimultaneously.switchWindow(0);

			documentWebSimultaneously.goToMyFiles();
			Assert.assertTrue(!documentWebSimultaneously.verifyOnSubmenuOptionOfFileMyFiles(files[j], moreOptionItem, documentName[j]));

			simultaneousDriver.quit();

		}
		logout();
	}

	private void scenario4() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		LoginPageWeb loginPageWeb;
		dashboardWeb.searchSite(siteName);
		documentWeb = bannerPageWeb.gotoFileModule();
		for (int j = 0; j < files.length; j++)
		{

			documentWeb.clickOnSubmenuOptionOfFileMyFiles(files[j], moreOptionItem, documentName[j]);
			documentWeb.switchWindow();

			documentWeb.closeCurrentTab();
			WebDriver simultaneousDriver = setDriver(url);
			simultaneousDriver.get(url);
			loginPageWeb = new LoginPageWeb(simultaneousDriver);
			loginPageWeb.setEmailId(normalUserEmail);
			loginPageWeb.setPassword(normalUserPassword);
			bannerPageWebSimultaneously = loginPageWeb.clickSignIn();
			dashboardWebSimultaneously = bannerPageWebSimultaneously.gotoDashboard();
			dashboardWebSimultaneously.searchSite(siteName);
			documentWebSimultaneously = bannerPageWebSimultaneously.gotoFileModule();
			documentWebSimultaneously.clickOnSubmenuOptionOfFileMoreActions(files[j], moreOptionItem, documentName[j]);

			documentWebSimultaneously.switchWindow(0);

			documentWebSimultaneously.gotoFileModule();
			Assert.assertTrue(!documentWebSimultaneously.verifyOnSubmenuOptionOfFile(files[j], moreOptionItem, documentOption[j]));

			simultaneousDriver.quit();

		}
		logout();
	}

	private void scenario6() throws InterruptedException, IOException
	{
		try
		{

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb.gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(true);
			adminFilesWeb.saveFilesChanges();
			logout();
			bannerPageWeb = login(normalUserEmail, normalUserPassword);

			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			documentWeb = bannerPageWeb.gotoFileModule();
			for (int j = 0; j < files.length; j++)
			{

				documentWeb.gotoMoreActions(files[j], FileLabels.DOCUMENT_CHECKOUT_MESSAGE);
				documentWeb.clickOnCheckOut();
				Assert.assertFalse(documentWeb.verifyMoreActionsItem(files[j], openInOpt));

			}
			logout();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb.gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(false);
			adminFilesWeb.saveFilesChanges();
		}
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemPreconfiguration();

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
		siteAdminConfiguration();
	}

	private void siteAdminConfiguration() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		gotoFileModuleAndAddZippFile(zipFile);

		adminPageWeb = documentWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setFilePermission(SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES);
		adminFilesWeb.saveFilesChanges();
		documentWeb = adminFilesWeb.goToMyFiles();// documentWeb.deleteAllDocs();
		documentWeb.selectItemFromUpload("Zipped files");
		zipFileMap.clear();
		zipFileMap.put(TAGS, newTag);
		zipFileMap.put(DISCLAIMER, newDisclaimer);

		documentWeb.addZipFile(zipFile, zipFileMap);
		documentWeb.clickAddInModal();

		logout();
	}

	void gotoFileModuleAndAddZippFile(String zipFileName)
	{
		documentWeb = bannerPageWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromUpload("Zipped files");
		zipFileMap.clear();
		zipFileMap.put(TAGS, newTag);
		zipFileMap.put(DISCLAIMER, newDisclaimer);
		documentWeb.addZipFile(zipFileName, zipFileMap);
		documentWeb.clickAddInModal();
	}

	private void systemPreconfiguration() throws InterruptedException
	{

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.goBack();

		SystemAdminFileOrFileTypesPage fileTypes = systemAdminWeb.gotoFileOrFileTypes();

		for (int i = 0; i < testFileTypes.length; i++)
		{
			EditFileIconsPage editfile = fileTypes.gotoDocumentEditPage(testFileTypes[i]);
			{
				editfile.enableOnlineOfficeEdit(true);
				editfile.enableOnlineEditingViaWebDAV(true);
				editfile.clickSave();
			}
		}
	}

}
