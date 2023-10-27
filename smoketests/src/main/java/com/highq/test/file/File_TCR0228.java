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
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0228 extends BannerPageWeb
{
	/** Favourite */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TCR0228";
	String[] userNames = {"usernormal1", "usersiteadmin"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String folderPermissionName = "Admin";
	String[] addMenuItems = {"Files", "Folder"};

	String leftPanelOption = "Favourites";
	String fileName = "doc1.txt";
	String folderName = "FolderOfTCR0228";
	String folderDescription = "Folder created";
	String jsonFileName = "Files/preConfiguration_File_TCR0228.json";

	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0228() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Favourite / Unfavourite a file or folder */
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

		/** Upload a folder if it is not available */
		documentWeb.searchFolder(folderName);
		if (!documentWeb.verifySearchedFolder(folderName))
		{
			documentWeb.clearLeftPanelSearchItem();
			documentWeb.selectLeftPanelFileOptions(siteName);
			documentWeb.selectItemFromAdd(addMenuItems[1]);
			documentWeb.createNewFolderInRoot(folderName, folderDescription);
			documentWeb.clickAddInModal();
			documentWeb.searchFolder(folderName);
			assertTrue(documentWeb.verifySearchedFolder(folderName));
		}
		documentWeb.clearLeftPanelSearchItem();

		/** Favourite file and folder */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.addFileOrFolderToFavourites(fileName);
		documentWeb.addFileOrFolderToFavourites(folderName);

		/** Verify favourite icon is selected */
		assertTrue(documentWeb.verifyFileOrFolderFavouriteIconIsSelected(fileName));
		assertTrue(documentWeb.verifyFileOrFolderFavouriteIconIsSelected(folderName));

		/** Verify favourited item is present in favourite screen */
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		assertTrue(documentWeb.verifyItemInFavourites(fileName));
		assertTrue(documentWeb.verifyItemInFavourites(folderName));

		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		/** Login with site admin */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		/** Verify favourited item is not present in favourite screen */
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		assertTrue(!documentWeb.verifyItemInFavourites(fileName));
		assertTrue(!documentWeb.verifyItemInFavourites(folderName));

		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Remove item from favourites */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.removeFileOrFolderFromFavourites(fileName);
		documentWeb.removeFileOrFolderFromFavourites(folderName);

		/** Verify favourite icon is no longer selected for item removed from favourites */
		assertTrue(!documentWeb.verifyFileOrFolderFavouriteIconIsSelected(fileName));
		assertTrue(!documentWeb.verifyFileOrFolderFavouriteIconIsSelected(folderName));

		/** Verify item is no longer available in favourites screen */
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		assertTrue(!documentWeb.verifyItemInFavourites(fileName));
		assertTrue(!documentWeb.verifyItemInFavourites(folderName));

		dashboardWeb = documentWeb.gotoDashboard();
		logout();
	}

	private void precondition() throws InterruptedException
	{
		/** Create site and add normal user, site admin */
		preconfiguration();
		/** Set folder admin permission for normal user */
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
		logout();
	}

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
