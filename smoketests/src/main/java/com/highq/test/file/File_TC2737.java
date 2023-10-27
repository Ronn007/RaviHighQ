package com.highq.test.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminGeneralPage;
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
public class File_TC2737 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String normalUserEmail = "normal.onlineOffice@Highq.com";// "admin.user@highq.com";
	String normalUserPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "File_TC2737";
	String[] userNames = {"tom.chick", "site.onlineOffice", "contain.onlineOffice"};
	String[] userEmail = {"tom.chick@Highq.com", "site.onlineOffice@highq.com", "contain.onlineOffice@highq.com"};

	String folderDescription = "Folder for File_TC2737";

	String jsonFileName = "Files/preConfiguration_File_TC2737.json";
	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	String[] addItem = {"Files", "Zipped files", "Placeholder file", "New folder"};

	String userForLogin;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DocumentPage documentWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	AdminGeneralPage adminGeneralWeb;
	AddUserPage addUserWeb;
	SystemAdminPage systemAdminWeb;
	String[] folderName = {"Folder 1", "Folder 2", "Folder 3", "Folder 4", "Folder1"};
	String createFolder = "Folder";
	String email = "paras.vankadi@highq.com";
	String wordDocument = "Word Document";
	String wordOnline = "Word Online";
	String excelSpreadsheet = "Excel Spreadsheet";
	String excelOnline = "Excel Online";
	String powerPointPresentation = "PowerPoint Presentation";
	String powerPointOnline = "PowerPoint Online";
	String myFiles = "My files";
	String[] option = {"Word Document", "Excel Spreadsheet", "PowerPoint Presentation", "Folder", "Placeholder file"};
	String[] fileOption = {"Files", "Zipped files", "Files via email", "files via email"};
	String[] status = {"Read only", "Active"};

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2737() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();

	}

	private void scenario1() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		Reporter.log("ASP admin > Enable Open in Office Online option = ON\r\n" +
				"System admin > Open in Office Online = ON\r\n" +
				"Site admin > Files > Advanced settings > Enable Open in Office Online = checked.");

		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);

		// 1 Scenario 1: Property in ASP/System/Site Admin for Office online.

		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();

		dashboardWeb = systemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setEnableSiteIndex(true);
		adminFilesWeb.addEmailIDSiteInbox(email);
		adminFilesWeb.enableOpenOfficeOnline(true);

		adminFilesWeb.saveFilesChanges();

		logout();
		for (int i = 0; i < userNames.length; i++)
		{
			bannerPageWeb = login(userEmail[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			Reporter.log("1. Go to file module.");
			documentWeb = dashboardWeb.gotoFileModule();

			Reporter.log("2. There is two new button Upload and New.\r\n" +
					"3. Click on upload button.");
			documentWeb.clickUploadFileButton();
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[0]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[1]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[2]));
			Reporter.log("Expected :\r\n" +
					"Drop-down option will be open with below options.\r\n" +
					"1) Files\r\n" +
					"2) Zipped files\r\n" +
					"3) Upload manager (In IE only)\r\n" +
					"4) Files via email (If Enable site inbox from site admin)\r\n");
			documentWeb.clickNewFileButton();
			Reporter.log("Drop-down option will be open with below options. \r\n" +
					"Folder\r\n" +
					"Placeholder file\r\n" +
					"Word Document\r\n" +
					"Excel Spreadsheet\r\n" +
					"PowerPoint Presentation\r\n" +
					"Need to check Upload and New button in List, Column and Thumbnail view.");
			// Assert.assertTrue(documentWeb.verifyNewbuttonOption());

			String[] option = {"Word Document", "Excel Spreadsheet", "PowerPoint Presentation", "Folder", "Placeholder file"};
			for (int j = 0; j < option.length; j++)
			{
				Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[j]));
			}
			documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);
			documentWeb.clickUploadFileButton();
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[0]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[1]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[3]));
			documentWeb.clickNewFileButton();

			for (int k = 0; k < option.length; k++)
			{
				Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[k]));
			}

			documentWeb.selectItemFromView(FileLabels.FILES_COLUMNVIEW);
			documentWeb.clickUploadFileButton();
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[0]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[1]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[2]));

			documentWeb.clickNewFileButton();
			for (int j = 0; j < option.length; j++)
			{
				Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[j]));
			}
			documentWeb.selectItemFromView(FileLabels.FILES_THUMBNAILVIEW);
			documentWeb.clickUploadFileButton();
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[0]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[1]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[3]));
			documentWeb.clickNewFileButton();

			for (int j = 0; j < option.length; j++)
			{
				Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[j]));
			}
			Reporter.log("New buttons added My files.");

			documentWeb = dashboardWeb.goToMyFiles();
			documentWeb.clickUploadFileButton();
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[0]));
			Assert.assertTrue(documentWeb.verifyOptionOfUploadButtonFile(fileOption[1]));
			Assert.assertTrue(!documentWeb.verifyOptionOfUploadButtonFile(fileOption[2]));

			documentWeb.clickNewFileButton();
			for (int j = 0; j < option.length; j++)
			{
				Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[j]));
			}
			logout();
		}

	}

	private void scenario2() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		Reporter.log("1. Go to file module.");
		documentWeb = dashboardWeb.gotoFileModule();
		Reporter.log(" Click on New button and select Word document from drop down option.");

		documentWeb.selectItemFromNew(wordDocument);

		Reporter.log("Expected:\r\n" +
				"New document word modal will be open with Name text field and choose folder Location button.\r\n" +
				"In the Name field 'Enter Document name' place holder text will be display.");
		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		Reporter.log("Enter document name and click on location choose button.\r\n" +
				"Expected :\r\n" +
				"Select location modal will be open with all folder structure of site.\r\n" +
				"Expand all and Collapse all link will be available for folder structure.\r\n" +
				"6. Select any folder location and click on Select button.\r\n" +
				"Expected :\r\n" +
				"Current folder location will be display below choose button.\r\n" +
				"7.Click on Save button of New Word Document modal.\r\n" +
				"Expected :\r\n" +
				"Modal window will be close.");

		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(siteName, folderName[0]);

		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(wordOnline));
		documentWeb.switchWindow(0);

		////// Scenario : Create New Excel Spreadsheet.
		Reporter.log("Scenario : Create New Excel Spreadsheet.");

		documentWeb = dashboardWeb.gotoFileModule();
		Reporter.log(" Click on New button and select  New Excel Spreadsheet. drop down option.");

		documentWeb.selectItemFromNew(excelSpreadsheet);

		Reporter.log("New Excel spreadsheet modal  will be open with Name text field and choose folder Location button.\r\n" +
				"In the Name field  'Enter Sheet name' place holder text will be display.");
		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		Reporter.log("Click on Save button without entered document name.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Document \"Save\" button will be disable if Sheet name is not entered in the Name box.\r\n" +
				"\r\n" +
				"5. Enter sheet name and click on location choose button.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Select location modal will be open with all folder structure of site.\r\n" +
				"Expand all and Collapse all link will be available for folder structure.\r\n" +
				"\r\n" +
				"6. Select any folder location and click on Select button of modal.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Current folder location will be display below choose button.\r\n" +
				"\r\n" +
				"7.Click on Save button of New Excel Spreadsheet modal.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Modal window will be close.");
		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(siteName, folderName[0]);

		documentWeb.clickNewWordDocumentSave();
		documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(excelOnline);
		documentWeb.switchWindow(0);
		//////////// Scenario : Create New PowerPoint Presentation.
		Reporter.log("Scenario : Create New PowerPoint Presentation..");
		documentWeb = dashboardWeb.gotoFileModule();
		Reporter.log(" Click on New button and select PowerPoint Presentation from drop down option.");

		documentWeb.selectItemFromNew(powerPointPresentation);

		Reporter.log("Expected:\r\n" +
				"New Powerpoint Presentation modal  will be open with Name text field and choose folder Location button.\r\n" +
				"In the Name field  'Enter Presentation name' place holder text will be display.");
		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		Reporter.log("4. Click on Save button without entered Presentation name.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"\"Save\" button will be disable if Presentation name is not entered in the Name box.\r\n" +
				"\r\n" +
				"5. Enter Presentation name and click on location choose button.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Select location modal will be open with all folder structure of site.\r\n" +
				"Expand all and Collapse all link will be available for folder structure.\r\n" +
				"\r\n" +
				"6. Select any folder location and click on Select button of modal.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Current folder location will be display below choose button.\r\n" +
				"\r\n" +
				"7.Click on Save button of New Powerpoint Presentation modal.\r\n" +
				"\r\n" +
				"Expected :\r\n" +
				"Modal window will be close.");

		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(siteName, folderName[0]);

		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(powerPointOnline));
		documentWeb.switchWindow(0);

		documentWeb = dashboardWeb.goToMyFiles();
		// MY File
		documentWeb.selectItemFromNew(wordDocument);

		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(myFiles, folderName[4]);

		documentWeb.clickNewWordDocumentSave();
		documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(wordOnline);
		documentWeb.switchWindow(0);

		////// Scenario : Create New Excel Spreadsheet.
		Reporter.log("Scenario : Create New Excel Spreadsheet.");

		documentWeb = documentWeb.goToMyFiles();
		Reporter.log(" Click on New button and select  New Excel Spreadsheet. drop down option.");

		documentWeb.selectItemFromNew(excelSpreadsheet);

		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(myFiles, folderName[4]);

		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(excelOnline));
		documentWeb.switchWindow(0);
		//////////// Scenario : Create New PowerPoint Presentation.
		Reporter.log("Scenario : Create New PowerPoint Presentation..");
		documentWeb = documentWeb.goToMyFiles();
		Reporter.log(" Click on New button and select PowerPoint Presentation from drop down option.");

		documentWeb.selectItemFromNew(powerPointPresentation);
		Assert.assertTrue(documentWeb.verifyNewWordDocument());

		documentWeb.clickChooseButton();
		Assert.assertTrue(documentWeb.verifyExpandallAndCollapseAlllink());
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(myFiles, folderName[4]);

		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifyOpenNewTabOfOnlineOfficeDocument(powerPointOnline));
		documentWeb.switchWindow(0);
		logout();
	}

	private void scenario3() throws InterruptedException, IOException
	{

		bannerPageWeb = login(normalUserEmail, normalUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		Reporter.log("1. Go to file module.");
		documentWeb = dashboardWeb.gotoFileModule();
		Reporter.log(" Click on New button and select Word document from drop down option.");

		documentWeb.selectItemFromNew(wordDocument);
		documentWeb.clickChooseButton();

		Map<String, Boolean> folderPerMap = new HashMap<>();

		folderPerMap.put(folderName[0], true);
		folderPerMap.put(folderName[1], true);
		folderPerMap.put(folderName[2], true);
		folderPerMap.put(folderName[3], false);

		for (Entry<String, Boolean> entry : folderPerMap.entrySet())
		{
			Assert.assertEquals(documentWeb.verifyFolderNameInFileSelectionOFOnlineOfficeDocument(entry.getKey()), entry.getValue());
		}
		documentWeb.closeAddFolderLocationModal();
		documentWeb.addNewWordDocumentAtSpecificLocation(siteName, folderName[0]);

		documentWeb.clickNewWordDocumentSave();
		documentWeb.selectItemFromNew(wordDocument);
		documentWeb.addNewWordDocumentAtSpecificLocation(siteName, folderName[2]);

		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifyAccessRestricted());
		logout();

	}

	private void scenario4() throws InterruptedException, IOException
	{

		bannerPageWeb = login(normalUserEmail, normalUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		Reporter.log("1. Go to file module.");
		documentWeb = dashboardWeb.gotoFileModule();
		Reporter.log(" Click on New button and select Word document from drop down option.");

		documentWeb.searchFolder(folderName[0]);
		documentWeb.selectLeftPanelFileOptions(folderName[0]);

		documentWeb.clickNewFileButton();

		for (int i = 0; i < option.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyOptionOfNewButton(option[i]));
		}
		documentWeb.clickUploadFileButton();
		Map<String, Boolean> folderPerMap = new HashMap<>();
		folderPerMap.clear();

		folderPerMap.put(fileOption[0], true);
		folderPerMap.put(fileOption[1], true);
		folderPerMap.put(fileOption[2], true);

		for (Entry<String, Boolean> entry : folderPerMap.entrySet())
		{
			Assert.assertEquals(documentWeb.verifyOptionOfUploadButtonFile(entry.getKey()), entry.getValue());
		}

		documentWeb.searchFolder(folderName[1]);
		documentWeb.selectLeftPanelFileOptions(folderName[1]);
		documentWeb.clickUploadFileButton();
		folderPerMap.clear();
		folderPerMap.put(fileOption[0], true);
		folderPerMap.put(fileOption[1], false);
		folderPerMap.put(fileOption[2], true);

		for (Entry<String, Boolean> entry : folderPerMap.entrySet())
		{
			Assert.assertEquals(documentWeb.verifyOptionOfUploadButtonFile(entry.getKey()), entry.getValue());
		}

		documentWeb.clickNewFileButton();
		folderPerMap.clear();
		folderPerMap.put(option[0], true);
		folderPerMap.put(option[1], true);
		folderPerMap.put(option[2], true);
		folderPerMap.put(option[3], false);
		folderPerMap.put(option[4], true);

		for (Entry<String, Boolean> entry : folderPerMap.entrySet())
		{
			System.out.println(entry.getKey() + "/" + entry.getValue());
			Assert.assertEquals(documentWeb.verifyOptionOfNewButton(entry.getKey()), entry.getValue());
		}

		documentWeb.searchFolder(folderName[2]);
		documentWeb.selectLeftPanelFileOptions(folderName[2]);
		Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_UPLOAD_BUTTION));
		Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_NEW_BUTTON));

		documentWeb.searchFolder(folderName[3]);
		documentWeb.selectLeftPanelFileOptions(folderName[3]);
		Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_UPLOAD_BUTTION));
		Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_NEW_BUTTON));
		Assert.assertTrue(documentWeb.verifyFileHeaderOption(FileLabels.FILES_VIEW));
		logout();
	}

	private void scenario5() throws InterruptedException, IOException

	{
		try
		{

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

			aspAdminWeb = bannerPageWeb.gotoASPAdmin();
			aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
			Reporter.log("ASP admin > Enable Open in Office Online option = ON\r\n" +
					"System admin > Open in Office Online = ON\r\n" +
					"Site admin > Files > Advanced settings > Enable Open in Office Online = checked.");

			aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);

			// 1 Scenario 1: Property in ASP/System/Site Admin for Office online.

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
			adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
			adminGeneralWeb.selectStatus(status[0]);
			adminGeneralWeb.clickOnSave();

			Reporter.log("1. Go to file module.");
			documentWeb = adminGeneralWeb.gotoFileModule();

			Reporter.log("2. There is two new button Upload and New.\r\n" +
					"3. Click on upload button.");
			Assert.assertTrue(documentWeb.verifyFileHeaderOption(FileLabels.FILES_UPLOAD_BUTTION));
			Assert.assertTrue(documentWeb.verifyFileHeaderOption(FileLabels.FILES_NEW_BUTTON));
			Assert.assertTrue(documentWeb.verifyFileHeaderOption(FileLabels.FILES_VIEW));
			logout();

			for (int i = 1; i < userNames.length; i++)
			{
				bannerPageWeb = login(userEmail[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);

				Reporter.log("1. Go to file module.");
				documentWeb = dashboardWeb.gotoFileModule();

				Reporter.log("2. There is two new button Upload and New.\r\n" +
						"3. Click on upload button.");
				Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_UPLOAD_BUTTION));
				Assert.assertTrue(!documentWeb.verifyFileHeaderOption(FileLabels.FILES_NEW_BUTTON));
				Assert.assertTrue(documentWeb.verifyFileHeaderOption(FileLabels.FILES_VIEW));
				logout();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			adminPageWeb = dashboardWeb.gotoAdminModule();

			adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
			adminGeneralWeb.selectStatus(status[1]);
			adminGeneralWeb.clickOnSave();
			logout();
		}

	}

	private void siteAdminConfiguration() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		Reporter.log("1. Go to file module.");
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();

		for (int i = 0; i < folderName.length; i++)
		{
			documentWeb.selectItemFromNew(createFolder);
			documentWeb.createNewFolderInRoot(folderName[i], folderDescription);
			documentWeb.clickAddInModal();

		}

		adminPageWeb = documentWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setFilePermission(SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEANDFOLDERS);
		adminFilesWeb.saveFilesChanges();

		adminPageWeb = adminFilesWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		String permissionName[] = {SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_VIEW, SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADDFILE, SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADMIN, SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_VIEW};
		Boolean permissionStatus[] = {true, true, true, false};
		for (int i = 0; i < folderName.length - 1; i++)
		{
			addUserWeb.openUserPermissionModel(normalUserEmail);
			addUserWeb.setInheritance(folderName[i], false);
			addUserWeb.setFolderPermission(folderName[i], permissionName[i], permissionStatus[i]);
			addUserWeb.clickSaveInSetPermissions();

		}

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
		siteAdminConfiguration();
	}
}
