package com.highq.test.wiki;

import static org.testng.Assert.assertFalse;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
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
public class Wiki_TC0170 extends BannerPageWeb
{

	/** Automation test for Like Wiki scenarios */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "Wiki_TC0170_Test002";
	String[] userNames = {"normal.siteuser0114@sitelevel.com", "site.admin001@sitelevel.com"};
	String newPassword = "Password@123";

	String wikiStatus = "Draft";
	String wikiTitle = "Wiki_TC0170_Test001_001";
	String wikiComment = "Commented wiki";
	String autoSavedWiki = "Wiki_TC0170_AutoSaved_02";
	String wikiContent = "Wiki Content for TC0166";
	String autoSaveDraftMessage = "The system automatically saves a draft as you are editing. Auto-saves can be accessed by clicking your name in the top right and selecting \"My drafts\". Do you want to keep your auto-saved draft?";
	String tag = "demoTag";
	String file = "doc1.txt";
	String autoSavedStatus = "Auto-saved";
	String draftStatus = "Draft";
	String wikiExportOption = "Export to PDF";
	String extention = ".pdf";
	String jsonFileName = "preConfiguration_Wiki_TC0170.json";

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

	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	String exportOption = "Export wiki with hierarchy";
	BannerPage bannerPageWeb;
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
	public void WikiTC0170() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario01();
		scenario02();
		cleanup();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();

	}

	public void scenario01() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[1], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();

		if (wikiWeb.verifySearchedPage(wikiTitle))
		{
			selectWiki(wikiTitle);
		}
		else
		{
			createWiki(wikiTitle, wikiContent);
			selectWiki(wikiTitle);
		}

		wikiWeb.addComment(wikiComment);
		wikiWeb.selectMoreOptionsOperationsOfWiki(wikiExportOption);

		wikiWeb.selectThisPageOnly();
		wikiWeb.selectIncludeComment(true);
		wikiWeb.selectIncludeIndexOption(true);

		wikiWeb.cleanDownloadsFolder();
		wikiWeb.clickOnExportOrPrint();
		wikiWeb.waitTillFileisDownloading();
		Assert.assertTrue(wikiWeb.verifyDownloadedFile(wikiTitle + extention));

	}

	public void scenario02() throws InterruptedException, IOException
	{
		wikiWeb = dashboardWeb.gotoWikiModule();

		if (wikiWeb.verifySearchedPage(wikiTitle))
		{
			selectWiki(wikiTitle);
		}
		else
		{
			createWiki(wikiTitle, wikiContent);
			selectWiki(wikiTitle);
		}
		wikiWeb.selectMoreOptionsOperationsOfWiki("Print preview");

		wikiWeb.selectThisPageOnly();
		wikiWeb.selectIncludeComment(true);
		wikiWeb.selectIncludeIndexOption(true);

		wikiWeb.clickOnExportOrPrint();

		wikiWeb.switchToPrintWindow();
		Assert.assertTrue(wikiWeb.verifyWikiTitleInPrintWindow(wikiTitle));
		Assert.assertTrue(wikiWeb.verifyCommentInPrintWindow(wikiComment));
		wikiWeb.closePrintWindow();

	}

	private void cleanup()
	{
		wikiWeb.gotoWikiModule();
		wikiWeb.deleteWiki(wikiTitle);
		wikiWeb.logout();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		// Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		// Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		// permissionOfOrg.put(orgType, true);
		// orgData.put(domain, permissionOfOrg);
		//
		//
		// Map<String, List<String>> userMap = new HashMap<>();
		// userMap.put(domain, Arrays.asList(userNames));
		//
		// LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		// rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);
		//
		// Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
		//
		// modulePermissions.put("View", true);
		// modulePermissions.put("Edit", true);
		// Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		// modulePermission.put("Wiki", modulePermissions);
		// modulePermission.put("Files", modulePermissions);
		//
		// Map<String, String> userData = new LinkedHashMap<>();
		// userData.put(domain, userNames[0]);
		//
		// LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		// siteData.put("name", siteName);
		//
		// LinkedHashMap<String,LinkedHashMap<String,Boolean>> siteUserRoles = new LinkedHashMap<>();
		// siteUserRoles.put(userNames[1]+"@"+domain, rolesOfSiteUser);
		//
		// LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		// siteUserModulePermission.put(userNames[0]+"@"+domain, modulePermission);
		//
		// configurationData.setOrgData(orgData);
		// configurationData.setUserMap(userMap);
		// configurationData.setNewPassword(newPassword);
		// configurationData.setStatus(UserStatus.Active);
		// configurationData.setStausLocked(false);
		// configurationData.setSiteData(siteData);
		// configurationData.setSiteGroupPermission(false);
		// configurationData.setModuleList("home", "files", "wiki", "activity");
		// configurationData.setSiteUserRoles(siteUserRoles);
		// configurationData.setSiteUserModulePermission(siteUserModulePermission);
		//
		// String[] configurationList = {"setOrg","addSystemAdminUsers","createSite","setGroupPermission","enableModules","addsiteuserswithoutroles","setSiteUserRoles"};
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logout();
	}

	public void createWikiAndCommentOnIt(String comment) throws InterruptedException
	{
		if (wikiWeb.verifySearchedPage(wikiTitle))
		{
			selectWiki(wikiTitle);
		}
		else
		{
			createWiki(wikiTitle, wikiContent);
			selectWiki(wikiTitle);
		}
		if (wikiWeb.verifyWikiTitle(wikiTitle))
			System.out.println(wikiTitle + " created successfully.");
		else
			assertFalse(true);

		wikiWeb.addComment(wikiComment);
	}

	public void selectWiki(String wikiName)
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName);
		wikiWeb.clearSearchItem();
	}

	public void createWiki(String wikiName, String content)
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(content);
		wikiWeb.saveWiki();
		gotoWikiModule();
	}

}
