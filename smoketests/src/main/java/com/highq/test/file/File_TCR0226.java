package com.highq.test.file;

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
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0226 extends BannerPageWeb
{
	/** Like and Comment */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0226";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String optionToCheck = "Enable commenting";
	String addMenuItem = "Files";

	String[] leftPanelOption = {"Index", "Deleted items"};
	String fileName = "doc1.txt";
	String enableComments = "TRUE";
	String commentsOnFile = "Default ON for new sites";
	String desclaimerToVerify = "";
	String moreOption = "Add comment";
	String comment = "Comment testing in File_TCR0226";
	String editedComment = "Comment testing in File_TCR0226 Edited";
	String jsonFileName = "Files/preConfiguration_File_TCR0226.json";

	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0226() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Like file */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Add File */
		documentWeb.searchFile(fileName);
		if (!documentWeb.verifyDocumentUploaded(fileName))
		{
			documentWeb.selectItemFromAdd(addMenuItem);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
		}

		/** Verify file uploaded successfully */
		documentWeb.searchFile(fileName);
		assertTrue(documentWeb.verifyDocumentUploaded(fileName));
		documentWeb.previewFile(fileName);
		if (documentWeb.verifyDisclaimerModalIsVisible())
		{
			assertTrue(documentWeb.verifyAndAcceptDisclaimer(desclaimerToVerify));
		}
		documentWeb.clickLike();
		assertTrue(documentWeb.verifyUnlikeLinkIsPresent());
		documentWeb.closePreviewPage();
	}

	private void scenario2() throws InterruptedException
	{
		/** Scenario 2: Add comment on file */
		/** Add file comment */
		documentWeb.gotoMoreActions(fileName, moreOption);
		int count = documentWeb.getCommentCount();
		documentWeb.addComment(comment);
		documentWeb.clickPost();
		/** Verify comment is available and comment count gets incremented */
		assertTrue(documentWeb.verifyVisibilityOfComment(userNames[0], comment));
		assertTrue(documentWeb.verifyCommentCountIncremented(count));
		/** Edit Comment */
		int countBeforeEdit = documentWeb.getCommentCount();
		documentWeb.editComment(userNames[0], comment, editedComment);

		/** Verify edited comment is visible and comment count does not increment */
		assertTrue(documentWeb.verifyVisibilityOfComment(userNames[0], editedComment));
		int countAfterEdit = documentWeb.getCommentCount();
		assertTrue(countBeforeEdit == countAfterEdit);

		/** Delete comment */
		documentWeb.deleteComment(userNames[0], editedComment);
		/** Verify comment is no longer available and comment count is decreased by 1 */
		assertTrue(!documentWeb.verifyVisibilityOfComment(userNames[0], editedComment));
		assertTrue(documentWeb.verifyCommentCountDecreased(countAfterEdit));

		documentWeb.clickUnlike();
		assertTrue(documentWeb.verifyLikeLinkIsPresent());
		documentWeb.closePreviewPage();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

	}

	private void precondition() throws InterruptedException
	{
		/**
		 * Site and user setup, Enable commenting for all site = True from ASP admin.
		 * Comments on files = True from System admin >> System settings
		 */
		preconfiguration();
		/** Enable commenting = True in created site and set folder admin permission */
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
		adminFilesWeb.saveFilesChanges();
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Enable commenting for all site = True from ASP admin */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableCommentingForAllSites(enableComments);
		aspConfigurationWeb.saveConfigurations();

		/** Comments on files = Default ON for new sites from System admin >> System settings. */
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.setCommentsOnFile(commentsOnFile);
		systemSettingsWeb.saveSettings();
		systemAdminWeb = systemSettingsWeb.goBack();

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
