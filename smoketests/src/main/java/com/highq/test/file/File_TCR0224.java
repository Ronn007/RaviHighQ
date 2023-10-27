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
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0224 extends BannerPageWeb
{
	/** Check-in Check-out */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0224";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String optionToCheck = "Enable check in/check out";
	String optionToUncheck = "Enable approval workflow";
	String addMenuItem = "Files";

	String[] leftPanelOption = {"Index", "Deleted items"};
	String[] multiFiles = {"doc1.txt", "doc2.txt"};
	String userForLogin;
	String actionsItem = "Bulk print";
	String checkOutEnable = "TRUE";
	String checkedOut = "Checked out";
	String checkOutUser = userNames[0];

	String newVersionTitle = "docUpdated";
	String newVersionFile = "testDoc.txt";
	String newVersionNote = "New Version Added";
	String jsonFileName = "Files/preConfiguration_File_TCR0224.json";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0224() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Check-In / Check-Out */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
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
		for (int i = 0; i < multiFiles.length; i++)
		{
			documentWeb.searchFile(multiFiles[i]);
			assertTrue(documentWeb.verifyDocumentUploaded(multiFiles[i]));
		}

		documentWeb.checkoutFile(multiFiles[0]);
		/** Verify More actions has Check In option available */
		assertTrue(documentWeb.verifyMoreActionsItem(multiFiles[0], DocFolderOperation.Check_in.toString().replace("_", " ")));

		/** Verify CheckIn File name and status */
		documentWeb.gotoMoreActions(multiFiles[0], DocFolderOperation.Check_in.toString().replace("_", " "));
		assertTrue(documentWeb.verifyCheckInFileName(multiFiles[0]) && documentWeb.verifyCheckInFileStatus(checkedOut));

		/** Check in new version of a file */
		documentWeb.clickCheckIn();
		documentWeb.uploadDoc_onAddNewVersionWin(newVersionFile, newVersionTitle, newVersionNote);
		documentWeb.clickAddInModal();

		/** Verify check out option is visible for new version */
		assertTrue(documentWeb.verifyMoreActionsItem(newVersionTitle, DocFolderOperation.Check_out.toString().replace("_", " ")));
		documentWeb.checkoutFile(multiFiles[1]);
		assertTrue(documentWeb.verifyMoreActionsItem(multiFiles[1], DocFolderOperation.Check_in.toString().replace("_", " ")));

		documentWeb.gotoDashboard();
		logout();

		/** Login with site admin */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		/** Verify file more action contains Checked out option */
		assertTrue(documentWeb.verifyMoreActionsItem(multiFiles[1], DocFolderOperation.Checked_out.toString().replace("_", " ")));

		/** Verify Checked out file name, status, username */
		documentWeb.gotoMoreActions(multiFiles[1], DocFolderOperation.Checked_out.toString().replace("_", " "));
		assertTrue(documentWeb.verifyCheckInFileName(multiFiles[1]) && documentWeb.verifyCheckInFileStatus(checkedOut));
		assertTrue(documentWeb.verifyCheckOutUserName(checkOutUser));
		documentWeb.cancelCheckout();

		/** Verify file more action contains Check out option for the file check out for which has been canceled */
		assertTrue(documentWeb.verifyMoreActionsItem(multiFiles[1], DocFolderOperation.Check_out.toString().replace("_", " ")));

		/** Remove all files */
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
	}

	private void precondition() throws InterruptedException
	{
		/** Site and user setup, Enable Checkout */
		preconfiguration();
		/** Enable check in/ check out and disable approval workflow from files in Site Admin */
		siteAdminConfiguration();
	}

	private void siteAdminConfiguration() throws InterruptedException
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
		adminFilesWeb.selectFileOptionCheckbox(optionToUncheck, false);
		adminFilesWeb.saveFilesChanges();
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Set checkout enable option TRUE */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setCheckoutEnableOption(checkOutEnable);
		aspConfigurationWeb.saveConfigurations();

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
