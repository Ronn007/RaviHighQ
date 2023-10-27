package com.highq.test.file;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.FileLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author vivek.mishra
 * @created on 01/06/2018
 */
public class File_TCR0240 extends BannerPageWeb
{
	/** Sorting */
	String jsonFileName = "Files/preConfiguration_File_TCR0240.json";
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
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();// "ravi.middha@highq.com";//
																						// "admin.user@highq.com";
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();// "Password@123";
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 1").get("newPassword").asText();
	String normalUser2Password = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 2").get("newPassword").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 3").get("newPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String orgType = "Internal";

	String[] userNames = {"alpha.user1240@l5.com", "beta.user1240@l5.com", "site.admin1240@l5.com"};
	String[] passwords = {normalUserPassword, normalUser2Password, siteAdminPassword};
	String domain = "l5.com";

	String[] addMenuItems = {FileLabels.FILES_ADDDROPDOWN_FILES, FileLabels.FILES_NEW_FOLDER};

	String[] files = {"doc1.txt", "doc2.txt", "doc3.txt"};

	String[] folders = {"Advanced", "Normal", "High"};
	String fileExtension = ".txt";

	String[] newFileNames = {"Alpha File", "Beta File", "XYZ File"};
	String[] filesForFolder = {"profilepicture.jpg"};
	String[] colNames = {"name","author","last modified","Size"};
	String folder = "Folder";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;


	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @creted on 01/06/2018
	 */
	@Test
	public void FileTCR0240() throws InterruptedException, IOException
	{
		precondition();
		scenario1();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 01/06/2018
	 */
	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Sort by clicking on column header. */
		
		/** Login with normal user and verify Actions button is not visible inside Index page */
		
		bannerPageWeb = login(userNames[0], normalUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		
		Map<String, ArrayList> tableDataBeforeSort = documentWeb.getTableData();
		
		///  *********   Ascending order verifications  ***********/////////
		
		documentWeb.clickOnCaretSign(colNames[0]);
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[0], folder));
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[0], " "));
		
		documentWeb.clickOnCaretSign(colNames[1]);
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[1], folder));
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[1], " "));
		
		documentWeb.clickOnCaretSign(colNames[2]);
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[2], folder));
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[2], " "));
		
		documentWeb.clickOnCaretSign(colNames[3]);
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[3], folder));
		Assert.assertTrue(documentWeb.verifyAscendingOrder(colNames[3], " "));
		
		///  *********   Descending order verifications  ***********/////////
		
		documentWeb.clickOnCaretSign(colNames[0]);
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[0], folder));
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[0], " "));
		
		documentWeb.clickOnCaretSign(colNames[1]);
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[1], folder));
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[1], " "));
		
		documentWeb.clickOnCaretSign(colNames[2]);
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[2], folder));
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[2], " "));
		
		documentWeb.clickOnCaretSign(colNames[3]);
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[3], folder));
		Assert.assertTrue(documentWeb.verifyDescendingOrder(colNames[3], " "));
		
		Map<String, ArrayList> tableDataAfterSort = documentWeb.getTableData();
		Assert.assertTrue(tableDataBeforeSort.equals(tableDataAfterSort));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 01/06/2018
	 */
	private void precondition() throws InterruptedException, IOException
	{
		/**
		 * Create site and add normal users and site admin
		 */
		 preconfiguration();
		/**
		 * Set folder admin permission for normal users, Upload files and folders with different users
		 */
		setFolderAdminPermissionAndUploadFilesAndFolders();

	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 01/06/2018
	 */
	private void setFolderAdminPermissionAndUploadFilesAndFolders() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[2], siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		setFolderAdminPermission(userNames[0], siteName, true);
		setFolderAdminPermission(userNames[1], siteName, true);
		logout();

		for (int i = 0; i < userNames.length; i++)
		{
			addFileAndFolderWithDiferentUsers(userNames[i], passwords[i], files[i], newFileNames[i], folders[i]);
		}
	}

	/**
	 * @author vivek.mishra
	 * @param userName
	 * @param password
	 * @param fileNameWithExtension
	 * @param newFileName
	 * @param folderName
	 * @throws IOException
	 * @throws InterruptedException
	 * @created on 01/06/2018
	 */
	private void addFileAndFolderWithDiferentUsers(String userName, String password, String fileNameWithExtension, String newFileName, String folderName) throws IOException, InterruptedException
	{
		bannerPageWeb = login(userName, password);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = addUserWeb.gotoFileModule();
		addAndRenameFile(fileNameWithExtension, newFileName);
		addFolderInRoot(folderName);
		documentWeb.gotoDashboard();
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @param user
	 * @param folderName
	 * @param permission
	 * @created on 01/06/2018
	 */
	private void setFolderAdminPermission(String user, String folderName, boolean permission)
	{
		addUserWeb.openUserPermissionModel(user);
		/** Set Folder Admin Permission */
		addUserWeb.setFolderAdminPermission(folderName, permission);
		addUserWeb.clickSaveInSetPermissions();
	}

	/**
	 * @author vivek.mishra
	 * @param fileNameWithExtension
	 * @param newName
	 * @throws IOException
	 * @throws InterruptedException
	 * @created on 01/06/2018
	 */
	private void addAndRenameFile(String fileNameWithExtension, String newName) throws IOException, InterruptedException
	{
		String newFile = newName + fileExtension;
		documentWeb.searchFile(newFile);
		if (!documentWeb.verifyDocumentUploaded(newFile))
		{
			documentWeb.selectItemFromUpload(addMenuItems[0]);
			documentWeb.uploadFileInAddFiles(fileNameWithExtension);
			documentWeb.renameFileInAddFilesModal(fileNameWithExtension, newName);
			assertTrue(documentWeb.verifyFileNameInAddFilesModal(newFile));
			documentWeb.clickAddInAddFileModal();
			/** Verify file is present */
			documentWeb.searchFile(newFile);
			assertTrue(documentWeb.verifyDocumentUploaded(newFile));
		}
	}

	/**
	 * @author vivek.mishra
	 * @param folderName
	 * @created on 01/06/2018
	 */
	private void addFolderInRoot(String folderName)
	{
		documentWeb.searchFolder(folderName);
		if (!documentWeb.verifySearchedFolder(folderName))
		{
			documentWeb.clearLeftPanelSearchItem();
			documentWeb.selectItemFromNew(addMenuItems[1]);
			documentWeb.createNewFolderInRoot(folderName, "");
			documentWeb.clickAddInModal();
			documentWeb.searchFolder(folderName);
			assertTrue(documentWeb.verifySearchedFolder(folderName));
		}
		documentWeb.clearLeftPanelSearchItem();
		documentWeb.selectLeftPanelFileOptions(siteName);
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @created on 01/06/2018
	 */
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
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}

}
