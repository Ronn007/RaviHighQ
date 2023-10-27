package com.highq.test.file;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
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
import com.highq.parsers.JSONParser;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0244 extends BannerPageWeb
{
	/** Bulk download */
	private WebDriver driver;
	String jsonFileName = "Files/preConfiguration_File_TCR0244.json";
	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser
					.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();// "ravi.middha@highq.com";//
																						// "admin.user@highq.com";
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();// "Password@123";
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 1").get("newPassword").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 2").get("newPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com"};
	String newPassword = "Password@123";

	String[] files = {"doc1.txt", "doc2.txt", "doc3.txt"};
	String[] folders = {"Alpha", "Beta", "Test"};
	String extension = ".zip";

	String enableIndexPage = "Enable Index page";
	String enableBulkDownloads = "Enable bulk downloads";
	String leftPanelItem = "Index";
	String bulkDownloadWithPipeStream = "TRUE";
	String actionsItem = "Download";
	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	AdminFilesPage adminFilesWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	BaseFileTest baseFileTest = new BaseFileTest(driver);

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0244() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1 : Perform Bulk download */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		baseFileTest.documentWeb = documentWeb;
		/** Add files */
		for (int i = 0; i < files.length; i++)
		{
			documentWeb.searchFile(files[i]);
			if (!documentWeb.verifyDocumentUploaded(files[i]))
			{
				baseFileTest.addFile(files[i]);
				documentWeb.searchFile(files[i]);
				assertTrue(documentWeb.verifyDocumentUploaded(files[i]));
			}
		}
		/** Add folders */
		for (int i = 0; i < folders.length; i++)
		{
			documentWeb.searchFolder(folders[i]);
			if (!documentWeb.verifySearchedFolder(folders[i]))
			{
				baseFileTest.addFolderInRoot(folders[i], "");
				documentWeb.searchFolder(folders[i]);
				assertTrue(documentWeb.verifySearchedFolder(folders[i]));
			}
		}
		documentWeb.clearLeftPanelSearchItem();
		/** Go to index page,select files and folders and download zip file */

		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		for (int i = 0; i < files.length; i++)
		{
			documentWeb.selectFileFromIndex(files[i]);
		}
		documentWeb.selectItemFromActions(actionsItem);
		documentWeb.cleanDownloadsFolder();
		documentWeb.clickDownloadInModal();

		assertTrue(documentWeb.verifyZipFileContainsDesiredFilesAndFolders(siteName + extension, files));

		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		documentWeb.selectFileFromIndex(siteName);
		documentWeb.selectItemFromActions(actionsItem);
		documentWeb.cleanDownloadsFolder();
		documentWeb.clickDownloadInModal();
		/** verify zip file contains desired files and folders */
		assertTrue(documentWeb.verifyZipFileContainsDesiredFilesAndFolders(siteName + extension, files));
		assertTrue(documentWeb.verifyZipFileContainsDesiredFilesAndFolders(siteName + extension, folders));
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
	}

	private void precondition() throws InterruptedException
	{
		/**
		 * Create site and add normal user, site admin
		 * Set Enable bulk download = True from ASP admin
		 */
		preconfiguration();
		/**
		 * Set folder admin permission for normal user,set Show files index = true from site admin >> Files.
		 * Set DRM options = none from site admin >> files.
		 * set Enable bulk downloads = true from Site admin >> Files.
		 */
		siteAdminConfiguration();

	}

	private void siteAdminConfiguration() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1], defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		/*
		 * addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		 * addUserWeb.openUserPermissionModel(userNames[0]);
		 * addUserWeb.setFolderAdminPermission(siteName, true);
		 * addUserWeb.clickSaveInSetPermissions();
		 */
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(enableIndexPage, true);
		adminFilesWeb.selectFileOptionCheckbox(enableBulkDownloads, true);
		adminFilesWeb.disableOnlineViewerWithDRM();
		adminFilesWeb.saveFilesChanges();
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Set Enable bulk download = True from ASP admin */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setBulkDownloadWithPipeStream(bulkDownloadWithPipeStream);
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
