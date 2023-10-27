package com.highq.test.wiki;

import static org.testng.Assert.assertFalse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Wiki_TC0168 extends BannerPageWeb
{
	/** Automation test for Like Wiki scenarios */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Wiki_TC0168_2";
	String[] userNames = {"normal.user0168@wiki0168.com", "site.admin0168@wiki0168.com"};
	String domain = "wiki168.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String jsonFileName = "preConfiguration_Wiki_TC0168.json";
	String wikiTitle = "Wiki_TC0168";
	String[] wikiTitles = {"Wiki_TC01681", "Wiki_TC01682", "Wiki_TC01683"};
	String wikiContent = "Wiki Content for TC0168";

	String wikiDelete = "Delete";
	String wikiRestore = "Restore";
	String deleteWikiModalTitle = "Permanently delete pages?";
	String deleteWikiModalMessage = "Are you sure you want to permanently delete these pages? They will not be retrievable once they are permanently deleted.";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	WikiPage wikiWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	AdminAdvancedPage adminAdvancedWeb;
	AddUserPage addUserWeb;
	LoginPage loginPageWeb;
	Map<Boolean, BannerPageWeb> result;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 1)
	public void wikiTC0168() throws InterruptedException, IOException, JSONException
	{
		preCondition();
		scenario1();
		scenario2();
		scenario3();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Deleted items page visibility */
		String userForLogin = userNames[1];
		// login(userForLogin, newPassword);
		dashboardWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();

		if (!wikiWeb.verifyWikiFromLeftPanel(wikiTitle))
		{
			createWiki(wikiTitle);
		}

		assertFalse(wikiWeb.verifyDeletedItemsLink());

		wikiWeb.deleteWiki(wikiTitle);
		wikiWeb.gotoWikiModule();
		// driver.navigate().refresh();

		Assert.assertTrue(wikiWeb.verifyDeletedItemsLink());

		/** Login using Normal User */
		logout();
		userForLogin = userNames[0];
		login(userForLogin, newPassword);
		dashboardWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();

		assertFalse(wikiWeb.verifyDeletedItemsLink());
		wikiWeb.logout();
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario2() throws InterruptedException
	{
		/** Scenario 2: Delete wiki */

		String userForLogin = userNames[1];
		login(userForLogin, newPassword);
		dashboardWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		if (wikiWeb.verifyDeletedItemsLink())
		{
			wikiWeb.goToDeletedItems();
			wikiWeb.selectMoreOptionsFromDeletedItems(wikiTitle, wikiDelete);

			Assert.assertTrue(wikiWeb.getDeleteWikiModalTitle().equalsIgnoreCase(deleteWikiModalTitle));
			Assert.assertTrue(wikiWeb.getCustomModalMessage().equalsIgnoreCase(deleteWikiModalMessage));

			wikiWeb.clickCancelOnDeleteWikiModal();
			Assert.assertTrue(wikiWeb.verifyWikiInDeletedItems(wikiTitle));

			wikiWeb.selectMoreOptionsFromDeletedItems(wikiTitle, wikiDelete);
			wikiWeb.clickOkOnDeleteWikiModal();

			Assert.assertFalse(wikiWeb.verifyWikiInDeletedItems(wikiTitle));
		}
		else
		{
			Assert.assertTrue(false);
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario3() throws InterruptedException
	{
		/** Scenario 3: Restore wiki */
		wikiWeb = dashboardWeb.gotoWikiModule();

		if (!wikiWeb.verifyWikiFromLeftPanel(wikiTitle))
		{
			createWiki(wikiTitle);
		}

		for (int i = 0; i < wikiTitles.length; i++)
		{
			if (!wikiWeb.verifyWikiFromLeftPanel(wikiTitles[i]))
			{
				createWiki(wikiTitles[i]);
			}
		}

		wikiWeb.deleteWiki(wikiTitle);
		wikiWeb.gotoWikiModule();

		if (wikiWeb.verifyDeletedItemsLink())
		{
			wikiWeb.goToDeletedItems();
			wikiWeb.selectMoreOptionsFromDeletedItems(wikiTitle, wikiRestore);

			Assert.assertTrue(wikiWeb.veirfyRestoreWikiTree());
			Assert.assertTrue(wikiWeb.verifyWikisInRestoreWikiMainTree(wikiTitles));

			wikiWeb.clickCancelOnRestoreWiki();

			// Modal window should be closed.
			Assert.assertFalse(wikiWeb.verifyRestoreWikiModal());

			// 5. Select a wiki location and click on save.
			// wikiWeb.goToDeletedItems();
			wikiWeb.selectMoreOptionsFromDeletedItems(wikiTitle, wikiRestore);
			wikiWeb.selectWikiInRestoreWiki(wikiTitles[0]);
			wikiWeb.clickSaveOnRestoreWiki();

			Assert.assertTrue(wikiWeb.verifyChildLevelWiki(wikiTitles[0], wikiTitle));
		}
		else
		{
			Assert.assertTrue(false);
		}
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	public void preCondition() throws InterruptedException, IOException, JSONException
	{
		// siteAndUserConfiguration();
		precondition();
		String userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		if (wikiWeb.verifyDeletedItemsLink())
		{
			wikiWeb.deleteAllItemsFromDeletedItems();
			wikiWeb.gotoWikiModule();
		}
	}

	/**
	 * @param wikiName
	 */
	public void selectWiki(String wikiName)
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName);
		wikiWeb.clearSearchItem();
	}

	/**
	 * @param wikiName
	 */
	public void createWiki(String wikiName)
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
		wikiWeb.saveWiki();
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Wiki", moduleEditPermission);
		modulePermission.put("Files", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission",
				"enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		logout();
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}

}
