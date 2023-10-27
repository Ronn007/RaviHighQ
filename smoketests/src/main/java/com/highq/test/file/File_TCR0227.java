package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0227 extends BannerPageWeb
{
	/** Copy/Move Files or Folders */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String[] siteName = {"File_TCR0227_Site1", "File_TCR0227_Site2"};
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String[] addMenuItems = {"Files", "Folder"};

	String[] leftPanelOption = {"Index", "Deleted items"};
	String[] files = {"doc1.txt", "doc2.txt"};
	String folderName = "FolderOfTCR0227";
	String folderDescription = "Folder created";
	String actionItem = "Move or Copy";

	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminSecurityPage adminSecurityWeb;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0227() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Copy files or folders from source to destination in another site */
		precondition();
		/** Login with site admin */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		addFilesAndFolders();

		dashboardWeb.searchSite(siteName[0]);
		documentWeb = dashboardWeb.gotoFileModule();
		/** Copy file from one site to another */
		documentWeb.searchFile(files[0]);
		documentWeb.selectFileCheckBox(files[0]);
		documentWeb.selectItemFromAction(actionItem);
		assertTrue(documentWeb.verifyChooseASiteName(siteName[0]));
		documentWeb.copyFile(siteName[1], siteName[1], folderName);

		/** Go to destination site */
		dashboardWeb = documentWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[1]);
		documentWeb = dashboardWeb.gotoFileModule();
		/** Verify file is successfully copied in destination */
		documentWeb.selectLeftPanelFileOptions(folderName);
		documentWeb.searchFile(files[0]);
		assertTrue(documentWeb.verifyDocumentUploaded(files[0]));
		assertTrue(documentWeb.verifyFileAuthorName(userNames[1]));

	}

	private void scenario2() throws IOException, InterruptedException
	{
		/** Scenario 2: Move files from source to destination in same site */
		documentWeb.selectLeftPanelFileOptions(siteName[1]);

		/** Move file from one site to another */
		documentWeb.searchFile(files[1]);
		documentWeb.selectFileCheckBox(files[1]);
		documentWeb.selectItemFromAction(actionItem);
		assertTrue(documentWeb.verifyChooseASiteName(siteName[1]));
		documentWeb.moveFile("", siteName[1], folderName);
		documentWeb.searchFile(files[1]);
		assertTrue(!documentWeb.verifyDocumentUploaded(files[1]));

		/** Verify file is successfully moved to destination */
		documentWeb.selectLeftPanelFileOptions(folderName);
		documentWeb.searchFile(files[1]);
		assertTrue(documentWeb.verifyDocumentUploaded(files[1]));

		/** Post Cleanup : Moving the file to it's original place and emptying the folder */
		documentWeb.selectFileCheckBox(files[1]);
		documentWeb.selectItemFromAction(actionItem);
		assertTrue(documentWeb.verifyChooseASiteName(siteName[1]));
		documentWeb.moveFile("", siteName[1], "");
		documentWeb.searchFile(files[1]);
		assertTrue(!documentWeb.verifyDocumentUploaded(files[1]));
		documentWeb.selectLeftPanelFileOptions(siteName[1]);
		documentWeb.searchFile(files[1]);
		assertTrue(documentWeb.verifyDocumentUploaded(files[1]));

		documentWeb.selectLeftPanelFileOptions(folderName);
		documentWeb.deleteAllDocs();
		assertTrue(documentWeb.verifyNoFilesOrFolderPresent());

		documentWeb.selectLeftPanelFileOptions(leftPanelOption[1]);
		documentWeb.deleteAllDocs();

		if (documentWeb.verifyLeftPanelItem(leftPanelOption[1]))
			assertFalse(true);

		dashboardWeb = documentWeb.gotoDashboard();
		logout();

	}

	private void precondition() throws InterruptedException
	{
		/** Create two sites and add normal user, site admin in both sites */
		preconfiguration();
		/** Set folder admin permission for normal user */
		siteAdminConfiguration();

	}

	private void siteAdminConfiguration() throws InterruptedException
	{
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		for (int i = 0; i < siteName.length; i++)
		{
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName[i]);
			adminPageWeb = dashboardWeb.gotoAdminModule();
			addUserWeb = adminPageWeb.clickUsersInLeftPanel();
			addUserWeb.openUserPermissionModel(userNames[0] + "@" + domain);
			addUserWeb.setFolderAdminPermission(siteName[i], true);
			addUserWeb.clickSaveInSetPermissions();
		}
		logout();
	}

	private void addFilesAndFolders() throws InterruptedException, IOException
	{
		for (int i = 0; i < siteName.length; i++)
		{
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName[i]);
			documentWeb = dashboardWeb.gotoFileModule();

			/** Add File */
			for (int j = 0; j < files.length; j++)
			{
				documentWeb.searchFile(files[j]);
				if (!documentWeb.verifyDocumentUploaded(files[j]))
				{
					documentWeb.selectItemFromAdd(addMenuItems[0]);
					addDoc = new DocumentAddDataPage();
					addDoc.clean();
					addDoc.setFileuploadpath(files[j]);
					documentWeb.addFile(addDoc, null);
					documentWeb.clickAddInModal();
				}

				/** Verify file uploaded successfully */
				documentWeb.searchFile(files[j]);
				assertTrue(documentWeb.verifyDocumentUploaded(files[j]));
			}

			documentWeb.searchFolder(folderName);
			if (!documentWeb.verifySearchedFolder(folderName))
			{
				documentWeb.clearLeftPanelSearchItem();
				documentWeb.selectLeftPanelFileOptions(siteName[i]);
				documentWeb.selectItemFromAdd(addMenuItems[1]);
				documentWeb.createNewFolderInRoot(folderName, folderDescription);
				documentWeb.clickAddInModal();
				documentWeb.searchFolder(folderName);
				assertTrue(documentWeb.verifySearchedFolder(folderName));
				documentWeb.clearLeftPanelSearchItem();
			}
		}
		dashboardWeb = documentWeb.gotoDashboard();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		for (int i = 0; i < siteName.length; i++)
		{

			// Add and Verify organization
			Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
			Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
			permissionOfOrg.put(orgType, true);
			orgData.put(domain, permissionOfOrg);

			configurationData.setOrgData(orgData);

			Map<String, List<String>> userMap = new HashMap<>();
			userMap.put(domain, Arrays.asList(userNames));
			configurationData.setUserMap(userMap);

			configurationData.setNewPassword(newPassword);
			configurationData.setStatus(UserStatus.Active);
			configurationData.setStausLocked(false);

			LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
			siteData.put("name", siteName[i]);
			configurationData.setSiteData(siteData);
			configurationData.setSiteGroupPermission(false);

			configurationData.setModuleList("all");

			LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
			userRolesOfUser.put(userRole.toLowerCase(), true);
			LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
			siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

			Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
			modulePermissions.put("View", true);
			Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
			modulePermission.put("Files", modulePermissions);
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
			siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);
			configurationData.setSiteUserRoles(siteUserRoles);
			configurationData.setSiteUserModulePermission(siteUserModulePermission);

			String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission",
					"enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
			Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
			adminPageWeb = gotoAdminModule();
			adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.enableAdvancedSiteAdminOption(true);
			adminSecurityWeb.saveAdvancedChanges();
		}
		logout();
	}

}
