package com.highq.test.wiki;

import static org.testng.Assert.assertFalse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.SearchUserPageData;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminUserCreateGroupPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.AdminWikiPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulePermissionPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.OrgAdminPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminAddUsersPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.InsertSiteUserWeb;

/**
 * @author badal.gandhi
 */
public class Wiki_TC0172 extends BannerPageWeb
{

	/** Automation test for Like Wiki scenarios */
	private WebDriver driver;
	/** Login Credentials */
	String systemAdminUserId = "ravi.middha";
	String systemAmdinDomain = "highq.com";
	String sysAdminEmail = systemAdminUserId + "@" + systemAmdinDomain;
	String sysAdminPassword = "Password@123";

	String siteName = "Wiki_TC0172_Test003";
	String[] userNames = {"normal.siteuser0012", "site.admin"};
	String domain = "sitelevel.com";
	String newPassword = "Password@123";
	String orgType = "Internal";
	String wikiStatus = "Draft";
	String wikiTitle = "Wiki_TC0172_Test001_001";
	String wikiContent = "Wiki Content for TC0172";
	String[] wikiVersions = {"Wiki_TC0172_V2", "Wiki_TC0172_V3"};
	String version1 = "v.1";
	String version2 = "v.3";
	String exportOption = "Export wiki with hierarchy";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	WikiPage wikiWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	SystemAdminAddUsersPage addUsersWeb;
	ModulePermissionPage modulePermissionWeb;
	OrgAdminPage orgAdminWeb;
	InsertSiteUserWeb insertSiteUserWeb;
	AdminUserCreateGroupPage adminUserCreateGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	AdminWikiPage adminWikiWeb;
	BannerPage bannerPageWeb;

	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;

	ConfigurationData configurationData = new ConfigurationData();

	LoginPage loginPageWeb;

	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void WikiTC0172() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario01();
	}

	public void scenario01() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();
		createWikiAndItsVersions();
		wikiWeb.searchPages(wikiVersions[wikiVersions.length - 1]);
		wikiWeb.selectSearchedWiki(wikiVersions[wikiVersions.length - 1]);
		wikiWeb.selectMoreOptionsOperationsOfWiki("Info");
		wikiWeb.clickOnVersionsTab();
		wikiWeb.compareWikiVersions(version1, version2);

		Assert.assertTrue(wikiWeb.verifyVersionInCompareWikiModel(version1));
		Assert.assertTrue(wikiWeb.verifyVersionInCompareWikiModel(version2));
		boolean resultOfVersion01 = wikiWeb.verifyVersionMataDataInCompareWikiModel(version1, userNames[0]);
		Assert.assertTrue(resultOfVersion01);
		boolean resultOfVersion02 = wikiWeb.verifyVersionMataDataInCompareWikiModel(version2, userNames[0]);
		Assert.assertTrue(resultOfVersion02);

		wikiWeb.closeWikiCompareModel();
		wikiWeb.closeWikiInfoModel();

		wikiWeb.deleteWiki(wikiVersions[wikiVersions.length - 1]);
		logout();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));
		SearchUserPageData userStausData = new SearchUserPageData();
		userStausData.setStatus(UserStatus.Active);
		userStausData.setStausLocked(false);

		////// create users with respective roles at site level begins
		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);

		Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();

		modulePermissions.put("View", true);
		modulePermissions.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Wiki", modulePermissions);
		modulePermission.put("Files", modulePermissions);

		Map<String, String> userData = new LinkedHashMap<>();
		userData.put(domain, userNames[0]);

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);

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
		configurationData.setModuleList("home", "files", "wiki", "activity");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		logout();
	}

	public void createWikiAndItsVersions() throws InterruptedException
	{
		if (wikiWeb.verifySearchedPage(wikiTitle))
		{
			selectWiki(wikiTitle);
		}
		else
		{
			wikiWeb.clickOnAddWiki();
			createWiki(wikiTitle, wikiContent);
			selectWiki(wikiTitle);
		}
		if (wikiWeb.verifyWikiTitle(wikiTitle))
			System.out.println(wikiTitle + " created successfully.");
		else
			assertFalse(true);

		for (int i = 0; i < wikiVersions.length; i++)
		{
			wikiWeb.selectMoreOptionsOperationsOfWiki("Edit");
			wikiWeb.setTitle(wikiVersions[i]);
			wikiWeb.saveWiki();
		}

	}

	public void selectWiki(String wikiName)
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName);
		wikiWeb.clearSearchItem();
	}

	public void createWiki(String wikiName, String content)
	{
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(content);
		wikiWeb.saveWiki();
	}

}
