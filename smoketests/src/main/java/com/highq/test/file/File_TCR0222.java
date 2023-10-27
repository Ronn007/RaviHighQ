package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0222 extends BannerPageWeb
{
	/** Bulk Print */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0222";
	String[] userNames = {"usernormal21", "usersiteadmin21"};// {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String optionToCheck = "Enable bulk downloads";
	String addMenuItem = "Files";

	String[] leftPanelOption = {"Index", "Deleted items"};
	String[] multiFiles = {"doc1.txt", "doc2.txt"};
	String userForLogin;
	String actionsItem = "Bulk print";
	String extension = ".pdf";
	String documentType = "Plain Text Document";
	String jsonFileName = "Files/preConfiguration_File_TCR0222.json";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	AdminSecurityPage adminSecurityWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFileTypesWeb;
	EditFileIconsPage editFileIconsWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0222() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Perform “Bulk print” for the documents which are PDF convertible. */
		precondition();
		/** Login with site admin user */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Add Files */
		documentWeb.selectItemFromAdd(addMenuItem);
		for (int i = 0; i < multiFiles.length; i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(multiFiles[i]);
			documentWeb.addFile(addDoc, null);
		}
		documentWeb.clickAddInModal();

		documentWeb.selectLeftPanelFileOptions(leftPanelOption[0]);
		for (int i = 0; i < multiFiles.length; i++)
			documentWeb.verifyFileInIndex(multiFiles[i]);

		for (int i = 0; i < multiFiles.length; i++)
			documentWeb.selectFileFromIndex(multiFiles[i]);

		documentWeb.selectItemFromActions(actionsItem);
		if (!documentWeb.verifyBulkPrintMessage())
			assertFalse(true);

		documentWeb.clickCancel();
		if (documentWeb.verifyBulkPrintMessage())
			assertFalse(true);

		documentWeb.cleanDownloadsFolder();
		documentWeb.selectItemFromActions(actionsItem);
		if (!documentWeb.verifyBulkPrintMessage())
			assertFalse(true);
		documentWeb.clickDownloadInModal();
		documentWeb.clickCancel();

		/** Verify pdf file in Downloads */
		assertTrue(documentWeb.verifyDownloadedFile(siteName + extension));

		/** Remove all deleted items */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteAllDocs();
		if (!documentWeb.verifyNoFilesOrFolderPresent())
			assertFalse(true);

		documentWeb.selectLeftPanelFileOptions(leftPanelOption[1]);
		documentWeb.deleteAllDocs();

		if (documentWeb.verifyLeftPanelItem(leftPanelOption[1]))
			assertFalse(true);

		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		/** Uncheck convert to PDF Checkbox from system admin */
		cleanUp();
	}

	private void cleanUp()
	{
		login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		sysAdminFileOrFileTypesWeb = systemAdminWeb.gotoFileOrFileTypes();
		editFileIconsWeb = sysAdminFileOrFileTypesWeb.gotoDocumentEditPage(documentType);
		editFileIconsWeb.convertToPDF(false);
		sysAdminFileOrFileTypesWeb = editFileIconsWeb.clickSave();
		dashboardWeb = sysAdminFileOrFileTypesWeb.gotoDashboard();
		logout();
	}

	private void precondition() throws InterruptedException
	{
		/** Site and user setup */
		preconfiguration();
		/** Set folder permission for normal user and enable bulk download */
		setFolderAdminPermission();
	}

	private void setFolderAdminPermission() throws InterruptedException
	{
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0] + "@" + domain);
		addUserWeb.setFolderAdminPermission(siteName, true);
		addUserWeb.clickSaveInSetPermissions();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(optionToCheck, true);
		adminFilesWeb.saveFilesChanges();
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		/**
		 * System admin >> File/ File Types >> File icon >> edit icon/extension >> Convert to PDF = Checked
		 * System admin >> File/ File Types >> File icon >> edit icon/extension >> Online viewer support = Checked
		 */
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		sysAdminFileOrFileTypesWeb = systemAdminWeb.gotoFileOrFileTypes();
		editFileIconsWeb = sysAdminFileOrFileTypesWeb.gotoDocumentEditPage(documentType);
		editFileIconsWeb.convertToPDF(true);
		editFileIconsWeb.onlineViewerSupport(true);
		sysAdminFileOrFileTypesWeb = editFileIconsWeb.clickSave();
		systemAdminWeb = sysAdminFileOrFileTypesWeb.goBack();

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
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}
}
