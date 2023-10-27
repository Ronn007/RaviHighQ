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
public class File_TCR0239 extends BannerPageWeb
{
	/** PDF Conversion */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0239";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String folderPermissionName = "Admin";
	String[] addMenuItems = {"Files", "Folder"};

	String fileName = "testDoc";
	String[] extension = {".txt", ".pdf"};
	String documentType = "Plain Text Document";

	String optionToCheck = "Enable PDF conversion";
	String userForLogin;
	String jsonFileName = "Files/preConfiguration_File_TCR0239.json";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFileTypesWeb;
	EditFileIconsPage editFileIconsWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0239() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1 : PDF file conversion */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Add a non PDF File */
		documentWeb.selectItemFromAdd(addMenuItems[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName + extension[0]);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		/** Verify file extension is changed to .pdf */
		documentWeb.searchFile(fileName);
		assertTrue(documentWeb.verifyDocumentUploaded(fileName + extension[1]));

		documentWeb.deleteFile(fileName + extension[1]);
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		/** Uncheck convert to PDF Checkbox from system admin */
		cleanUp();
	}

	private void precondition() throws InterruptedException
	{
		/**
		 * Create site and add normal user, site admin
		 * Enable automatic PDF conversion and online Viewer Support from system admin
		 */
		preconfiguration();
		/** Set folder admin permission for normal user,Enable automatic PDF conversion */
		siteAdminConfiguration();

	}

	private void cleanUp()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		sysAdminFileOrFileTypesWeb = systemAdminWeb.gotoFileOrFileTypes();
		editFileIconsWeb = sysAdminFileOrFileTypesWeb.gotoDocumentEditPage(documentType);
		editFileIconsWeb.convertToPDF(false);
		sysAdminFileOrFileTypesWeb = editFileIconsWeb.clickSave();
		dashboardWeb = sysAdminFileOrFileTypesWeb.gotoDashboard();
		logout();
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
