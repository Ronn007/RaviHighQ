package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.DocFolderOperation;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0217 extends BannerPageWeb
{
	/** Add_Edit Folder */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0217";
	String[] userNames = {"usernormalforsmokefileupdated@l5.com", "usersiteadminforsmokefileupdated@l5.com"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String folderPermissionName = "Admin";
	String isheetTitle = "Test Isheet";
	String moreActionOption = "Edit details";

	String isheetColumnName = "FOLDER NAME";
	String isheetColumnData = "Scenario2 of File_TCR0217";
	String newIsheetColumnData = "Scenario5 Edited metadata";
	String folderName = "FileTCR0217";
	String folderDescription = "Folder for FileTCR0217";
	String newFolderName = "FileTCR0217 Edited";
	String newFolderDescription = "Folder for FileTCR0217 Edited";
	String metaFolderName = "FileTCR0217 with metadata";
	String metaFolderDescription = "Folder for FileTCR0217 with metadata";

	String optionToCheck = "Enable bulk downloads";
	String addMenuItem = "Folder";
	final String NAME = "Name";
	final String DESCRIPTION = "Description";

	String newMetaValue = "FileTCR0217 meta updated";
	String leftPanelOption = "Deleted items";
	String extension = ".zip";
	String userForLogin;
	String jsonFileName = "Files/preConfiguration_File_TCR0217.json";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	IsheetsPage isheetsWeb;
	AdminSecurityPage adminSecurityWeb;
	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage createSheetWeb;
	AdminIsheetManageColumnPage isheetColumnWeb;
	AdminIsheetAddColumnPage addColumnsWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0217() throws InterruptedException, IOException, JSONException
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
	}

	private void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Add folder */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Create new folder with description */
		documentWeb.selectItemFromAdd(addMenuItem);
		documentWeb.createNewFolderInRoot(folderName, folderDescription);
		documentWeb.clickAddInModal();
		documentWeb.searchFolder(folderName);
		if (!documentWeb.verifySearchedFolder(folderName))
			assertFalse(true);
		documentWeb.selectLeftPanelFileOptions(folderName);
		documentWeb.clearLeftPanelSearchItem();

		/** Verify folder name and description matches */
		if (!(documentWeb.verifyFolderTitle(folderName) && documentWeb.verifyFolderDescription(folderDescription)))
			assertFalse(true);

		/** Edit folder title and description */
		documentWeb.gotoTitleHeaderMoreActionItem(moreActionOption);
		documentWeb.editFolderDetails(NAME, newFolderName);
		documentWeb.editFolderDetails(DESCRIPTION, newFolderDescription);
		documentWeb.clickSaveInModal();

		/** Verify folder title and description updated */
		if (!(documentWeb.verifyFolderTitle(newFolderName) && documentWeb.verifyFolderDescription(newFolderDescription)))
			assertFalse(true);

	}

	private void scenario2()
	{
		/** Create new folder with description,metadata */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.selectItemFromAdd(addMenuItem);
		documentWeb.createNewFolderInRoot(metaFolderName, metaFolderDescription);
		documentWeb.gotoMetadataTab();
		documentWeb.addMetadata(isheetColumnName, isheetColumnData);
		documentWeb.clickAddInModal();
		documentWeb.searchFolder(metaFolderName);
		if (!documentWeb.verifySearchedFolder(metaFolderName))
			assertFalse(true);
		documentWeb.selectLeftPanelFileOptions(metaFolderName);
		documentWeb.clearLeftPanelSearchItem();

		/** Verify folder name and description matches */
		if (!(documentWeb.verifyFolderTitle(metaFolderName) && documentWeb.verifyFolderDescription(metaFolderDescription)))
			assertFalse(true);

		/** Verify meta data value in edit modal and Isheets module */
		verifyMetaDataInEditAndInIsheets(metaFolderName, isheetTitle, isheetColumnName, isheetColumnData);

		documentWeb.searchFolder(metaFolderName);
		documentWeb.selectLeftPanelFileOptions(metaFolderName);
		documentWeb.clearLeftPanelSearchItem();

		/** Edit folder metadata */
		documentWeb.gotoTitleHeaderMoreActionItem(moreActionOption);
		documentWeb.editFolderMetadata(isheetColumnName, newMetaValue);
		documentWeb.clickSaveInModal();

		/** Verify edited meta data value in edit modal and Isheets module */
		verifyMetaDataInEditAndInIsheets(metaFolderName, isheetTitle, isheetColumnName, newMetaValue);
	}

	private void scenario3() throws IOException
	{
		/** Scenario 3: Download folder */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.clickSelectAllCheckbox();
		documentWeb.cleanDownloadsFolder();
		documentWeb.selectItemFromAction("Download");

		/** Verify folder in Downloads */
		assertTrue(documentWeb.verifyDownloadedFile(siteName + extension));
		if (documentWeb.verifyUploadedZipFiles(siteName + extension))
			System.out.println("All the files from site are available in zip");

	}

	private void scenario4() throws IOException, InterruptedException
	{
		/** Scenario 4: Delete folder */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteFile(metaFolderName);
		documentWeb.searchFile(metaFolderName);
		if (documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
		/** Login with Site Admin */
		userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Verify folder in deleted items */
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		documentWeb.searchFile(metaFolderName);
		if (!documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);

		/** Restore and verify folder is removed from deleted items */
		documentWeb.restoreFile(metaFolderName);
		documentWeb.searchFile(metaFolderName);
		if (documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);
		/** Verify folder is successfully restored in Site */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.searchFile(metaFolderName);
		if (!documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);

		/** Delete the folder and move the same folder to a folder from deleted items */
		documentWeb.deleteFile(metaFolderName);
		documentWeb.searchFile(metaFolderName);
		if (documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		documentWeb.gotoMoreActions(metaFolderName, DocFolderOperation.Move.toString());
		documentWeb.moveFile("", siteName, newFolderName);

		/** Verify file is successfully moved to folder */
		documentWeb.selectLeftPanelFileOptions(newFolderName);
		documentWeb.searchFile(metaFolderName);
		if (!documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);

		documentWeb.deleteFile(metaFolderName);
		documentWeb.searchFile(metaFolderName);
		if (documentWeb.verifyDocumentUploaded(metaFolderName))
			assertFalse(true);

		/** Remove all deleted items */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteAllDocs();
		if (!documentWeb.verifyNoFilesOrFolderPresent())
			assertFalse(true);

		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		documentWeb.deleteAllDocs();

		if (documentWeb.verifyLeftPanelItem(leftPanelOption))
			assertFalse(true);

		dashboardWeb = documentWeb.gotoDashboard();
		logout();
	}

	private void verifyMetaDataInEditAndInIsheets(String folderName, String iSheetTitle, String isheetField, String iSheetFieldValue)
	{
		documentWeb.gotoTitleHeaderMoreActionItem(moreActionOption);
		documentWeb.gotoMetadataTab();
		if (!documentWeb.verifyMetaFieldInEditWindow(isheetField, iSheetFieldValue))
			assertFalse(true);
		documentWeb.clickCancel();
		isheetsWeb = documentWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(iSheetTitle);
		if (!isheetsWeb.verifyIsheetFieldValue(isheetField, iSheetFieldValue))
			assertFalse(true);
		documentWeb = isheetsWeb.gotoFileModule();
	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		/** Site and user setup */
		preconfiguration();
		/** Set folder permission for normal user and enable bulk download */
		setFolderAdminPermission();
		/** Create folder metadata isheet if it is not created */
		createFolderMetaIsheetIfNotCreated();
	}

	private void setFolderAdminPermission() throws InterruptedException
	{
		userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0]);
		addUserWeb.setFolderAdminPermission(siteName, true);
		addUserWeb.clickSaveInSetPermissions();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(optionToCheck, true);
		adminFilesWeb.saveFilesChanges();
	}

	private void createFolderMetaIsheetIfNotCreated()
	{
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (!adminIsheetWeb.verifyIsheetTitleHasFolderMetaEnabled(isheetTitle))
		{
			adminIsheetWeb.clickOnAddIsheet();
			createSheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions("iSheet");
			createSheetWeb.addIsheetTitle(isheetTitle);
			createSheetWeb.addIsheetSelectCheckBoxOption("Folder metadata template", true);
			adminIsheetWeb = createSheetWeb.addIsheetClickSave();
		}

		if (!adminIsheetWeb.verifyIsheetTitleHasFolderMetaEnabled(isheetTitle))
		{
			assertFalse(true);
		}

		isheetColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, "Manage columns");
		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			addColumnsWeb = isheetColumnWeb.manageColumnsClickAddColumns();
			addColumnsWeb.addColumnName(isheetColumnName);
			addColumnsWeb.clickSaveOnAddColumn();
		}

		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			assertFalse(true);
		}
		logout();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}
}
