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
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0235 extends BannerPageWeb
{
	/** Page count */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0235";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String folderPermissionName = "Admin";
	String[] addMenuItems = {"Files", "Folder"};

	String fileName = "testDoc.txt";

	String enableAdeptolCount = "TRUE";
	String optionToCheck = "Pages";
	String drmOption = "None";
	String jsonFileName = "Files/preConfiguration_File_TCR0235.json";
	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminFilesPage adminFilesWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0235() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1 : Functioning of page count by viewing file in viewer. */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		/** Add File if it is not available */
		documentWeb.searchFile(fileName);
		if (!documentWeb.verifyDocumentUploaded(fileName))
		{
			documentWeb.selectItemFromAdd(addMenuItems[0]);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
			/** Verify file is present */
			documentWeb.searchFile(fileName);
			assertTrue(documentWeb.verifyDocumentUploaded(fileName));
		}

		documentWeb.previewFile(fileName);
		/** Fetch total page count from preview */
		int previewCount = documentWeb.getTotalPageCountFromPreview();
		documentWeb.closePreviewPage();
		documentWeb.gotoFileModule();
		/** Fetch file page count from pages column */
		int filePages = documentWeb.getFilePages(fileName);
		System.out.println("Preview Count = " + previewCount + " file pages = " + filePages);

		/** Verify preview count and pages count matches */
		assertTrue(previewCount == filePages);
		logout();
	}

	private void precondition() throws InterruptedException
	{
		/** Create site and add normal user, site admin */
		preconfiguration();
		/**
		 * Set folder admin permission for normal user, Set Show no. pages = true from
		 * site admin. Set DRM Options = None
		 */
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
		adminFilesWeb.disableOnlineViewerWithDRM();
		adminFilesWeb.saveFilesChanges();
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Enable adeptol page count */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableAdeptolPageCount(enableAdeptolCount);
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
