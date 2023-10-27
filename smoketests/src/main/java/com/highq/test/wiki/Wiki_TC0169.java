/**
 * 
 */
package com.highq.test.wiki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
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
 * @author vivek.mishra
 */
public class Wiki_TC0169 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	WikiPage wikiWeb;
	BannerPage bannerWeb;

	String[] userNames = {"Wikinormal", "wikisiteadmin"};
	String nPassword = "WikiNormal@123";
	String[] siteName = {"Wiki_TC0169", "Wiki_TC0169_Temp"};
	String domain = "mishra.com";
	String[] wikiName = {"wiki1" + getRandomString(), "wiki2" + getRandomString()};
	String copyWiki = "Copy of " + wikiName[0];

	ConfigurationData configurationData = new ConfigurationData();

	@Test
	public void wikiTC0169() throws InterruptedException, IOException
	{
		preConditions();
		scenario1();
		scenario2();
	}

	public void preConditions() throws InterruptedException, IOException
	{
		TC0169_PreConditionsOfUserCreation();
		TC0169_NormalUserPreCondition();
	}

	public void scenario1() throws InterruptedException
	{
		bannerWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[0]);

		wikiWeb = dashboardWeb.gotoWikiModule();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Copy");
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(copyWiki);
		Assert.assertTrue(wikiWeb.verifySiblingLevelWiki(wikiName[0], copyWiki));
	}

	public void scenario2() throws InterruptedException
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		wikiWeb.clickOnSettingsTabInEditWiki();
		wikiWeb.clickOnChooseLocationButtonInSettingsTabInEditWiki();
		wikiWeb.verifyLocationModal();
		wikiWeb.selectSiteInSelectLocationModal(siteName[1]);
		String status = wikiWeb.selectWikiInSelectLocationModal(wikiName[1]);
		wikiWeb.clickOnSaveButtonInSelectLocationModal();
		wikiWeb.verifyWikiEditForm();

		wikiWeb.saveWiki();
		Assert.assertTrue(wikiWeb.verifyWikiTitle(wikiName[0]));
		Assert.assertTrue(wikiWeb.verifySiteName(siteName[1]));
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		wikiWeb.clickOnSettingsTabInEditWiki();
		ArrayList<Boolean> permission = wikiWeb.getWikiPermissionStatus(userNames[0]);

		wikiWeb.saveWiki();
		wikiWeb.selectWikiFromLeftPanel(status);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		wikiWeb.clickOnSettingsTabInEditWiki();
		ArrayList<Boolean> permission2 = wikiWeb.getWikiPermissionStatus(userNames[0]);
		for (int i = 0; i < permission.size(); i++)
		{
			if (permission.get(i) != permission2.get(i))
				Assert.assertTrue(false);
		}

	}

	private void TC0169_PreConditionsOfUserCreation() throws InterruptedException, IOException
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);
		modulePermission.put("Wiki", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(nPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName[0]);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		configurationData.setSiteName(siteName[1]);

		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

	private void TC0169_NormalUserPreCondition() throws InterruptedException
	{
		bannerWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[0]);
		wikiWeb = dashboardWeb.gotoWikiModule();
		for (int i = 0; i < wikiName.length; i++)
		{
			wikiWeb.clickOnAddWiki();
			wikiWeb.setTitle(wikiName[i]);
			wikiWeb.saveWiki();
			wikiWeb.verifyWikiTitle(wikiName[i]);
		}
		wikiWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[1]);
		wikiWeb = dashboardWeb.gotoWikiModule();
		if (!wikiWeb.verifySearchedPage(wikiName[1]))
		{
			wikiWeb.clickOnAddWiki();
			wikiWeb.setTitle(wikiName[1]);
			wikiWeb.saveWiki();
			wikiWeb.verifyWikiTitle(wikiName[1]);
		}
		wikiWeb.logout();
	}

}
