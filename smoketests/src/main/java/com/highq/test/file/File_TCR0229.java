package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author khushbu.dhandhukiya
 */
public class File_TCR0229 extends BannerPageWeb
{
	String jsonFileName = "Files/preConfiguration_File_TCR0229.json";

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0229";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "file.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String addMenuItem = "Files";
	String[] fileName = {"doc1.txt", "mp3File.mp3"};
	String optionToCheck = "Enable online viewer with DRM";

	String optionFile="Watermark";
	String textToVerify="usernormal1@file.com"+ " IP address: "+getIpAdress().trim();
	String[] option= {"Download original","Download"};

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	BannerPage bannerPageWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesWeb;
	AddUserPage addUserWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0229() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1();
		scenario2();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Set DRM option = Online viewer with DRM and watermarking is checked. */
		siteAdminConfiguration();

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb=dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0] + "@" + domain);
		addUserWeb.clickFilesTabInUserPermissionsModal();
		addUserWeb.selectFileOptionWatermarkCheckbox(optionFile, true);
		addUserWeb.clickSaveInSetPermissions();
		logout();
		
		
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.clickOnSelectedFile(fileName[0]);
		
		assertTrue(documentWeb.verifyWatermarkInViewer(textToVerify));
		documentWeb.clickOnMoreActionOfFileViewer();
		documentWeb.gotoOptiOnOnMoreActionOfFileViewer(option[0]);
		
		/**after downloading file can't verify the content of downloaded file.()**/
//		assertTrue(!documentWeb.verifyWatermarkInViewer(textToVerify));
		documentWeb.clickOnCrossButtonInFileviewer();
		
		documentWeb.clickOnSelectedFile(fileName[1]);
		assertTrue(documentWeb.verifyNoPreview());
		documentWeb.clickOnCrossButtonInFileviewer();
		documentWeb.logout();
		
		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.clickOnSelectedFile(fileName[0]);
		assertFalse(documentWeb.verifyWatermarkNotDisplay());
		documentWeb.clickOnCrossButtonInFileviewer();
		documentWeb.logout();
	}
	
	private void scenario2() throws InterruptedException, IOException
	{
		/**Set DRM option = Convert all files to PDF and encrypt with FileOpen plugin from site admin.**/
		siteAdminConfigurationScenario2();
		
		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb=dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0] + "@" + domain);
		addUserWeb.clickFilesTabInUserPermissionsModal();
		addUserWeb.selectFileOptionWatermarkCheckbox(optionFile, true);
		addUserWeb.clickSaveInSetPermissions();
		logout();
		
		
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.cleanDownloadsFolder();
		documentWeb.clickOnSelectedFile(fileName[0]);
		documentWeb.clickOnMoreActionOfFileViewer();
		documentWeb.gotoOptiOnOnMoreActionOfFileViewer(option[1]);
		documentWeb.clickOnCrossButtonInFileviewer();
		
		
		assertTrue(documentWeb.verifyDownloadedFile("doc1.pdf"));
		
		
		
		
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb=login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(addMenuItem);
		for (int i = 0; i < fileName.length; i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName[i]);
			documentWeb.addFile(addDoc, null);
		}
		documentWeb.clickAddInModal();
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

	private void siteAdminConfiguration() throws InterruptedException
	{
		bannerPageWeb=login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb=adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(optionToCheck, true);
		adminFilesWeb.saveFilesChanges();
		logout();

	}

	private void siteAdminConfigurationScenario2() throws InterruptedException
	{
		bannerPageWeb=login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb=adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.convertAllFilesToPDFAndEncrypt(true);
		adminFilesWeb.saveFilesChanges();
		logout();

	}
}
