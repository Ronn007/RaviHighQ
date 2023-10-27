package com.highq.test.dashboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.BannerLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.GlobalNavigationAdminPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.AdminWikiWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.test.wiki.BaseWikiTest;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0207 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String jsonFileName = "preConfiguration_Dashboard_TCR0207.json";
	String siteName = "Dashboard_TC0207_2";
	String[] userNames = {"dashboard.normaluser2072@dashboard207.com"};
	String domain = "dashboard207.com";
	String newPassword = "Password@123";
	String wikiTitle = "Dashboard_TC0207" + getRandomString();
	String wikiContent = "Dashboard_TC0207" + getRandomString();

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	AdminAdvancedPage adminAdvancedWeb;
	AddUserPage addUserWeb;
	LoginPage loginPageWeb;
	Map<Boolean, BannerPageWeb> result;
	List<String> modules;
	AdminWikiWeb adminWikiWeb;
	WikiPage wikiWeb;
	GlobalNavigationAdminPage globalNavigationAdminWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AspConfigurationPage aspConfigurationWeb;
	ConfigurationData configurationData = new ConfigurationData();

	BaseWikiTest baseWikiTest;
	Map<String, List<String>> userMap;
	List<String> userList;

	String landingPage = "Admin";
	String siteCategory = "Dashboard_TC0207_Category";

	String globalNavigationOption = "On";
	String menuItemOption = "Menu item";
	String menuItemTitle = "Google Search";
	String menuItemURL = "https://www.google.com";
	String openIn = "Same window";

	String[] searchFilters = {"All", "People", "Sites", "Content"};
	String[] notificationOptions = {"Settings", "See all notifications"};
	String[] privateMessageOptions = {"New message", "See all messages"};
	String[] verifyMiddlePanelFilters = {"All", "Posts", "Activity", "Filter"};
	String[] userAvtarOptions = {BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYPROFILE, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYDRAFTS, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYFILES, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYTASKS,
			BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SENDAFILE, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_APPROVALWORKFLOW, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SETTINGS, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SYSTEMADMIN,
			BannerLabels.BANNERPAGE_USERAVATAROPTIONS_ASPADMIN, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HIGHQHUB, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HELP, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_LOGOUT};

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
	public void dashboardTC0207() throws InterruptedException, IOException, JSONException
	{
		precondition();
		preCondition();
		scenario1();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = gotoDashboard();

		Assert.assertTrue(verifySearchLink());
		clickOnSearchButton();
		clickOnSearchDropDown();
		for (int i = 0; i < searchFilters.length; i++)
		{
			Assert.assertTrue(verifySearchFilterOptions(searchFilters[i]));
		}
		Assert.assertTrue(verifyAdvancedSearchOption());
		clickOnSearchDropDown();

		Assert.assertTrue(verifyNotificationLink());
		clickOnNotificationLink();
		Assert.assertTrue(verifyNotificationOptions(notificationOptions[0]));
		Assert.assertTrue(verifyNotificationOptions(notificationOptions[1]));
		Assert.assertTrue(verifyAwaitingApprovalMessage());
		Assert.assertTrue(verifyAwaitingApprovalCount());
		clickOnNotificationLink();

		Assert.assertTrue(verifyPrivateMessageLink());
		clickOnPrivateMessage();
		Assert.assertTrue(verifyPrivateMessageOptions(privateMessageOptions[0]));
		Assert.assertTrue(verifyPrivateMessageOptions(privateMessageOptions[1]));
		clickOnPrivateMessage();

		Assert.assertTrue(verifyFavouriteLink());
		clickOnFavouriteLink();
		Assert.assertTrue(verifySearchBoxOnFavouriteModal());
		Assert.assertTrue(verifyFavouriteFilterFavouriteModal());
		Assert.assertTrue(verifyRecentlyViewedFilterFavouriteModal());
		Assert.assertTrue(verifySeeAllFavourites());
		clickOnFavouriteLink();

		Assert.assertTrue(verifyUserAvtarLink());
		clickOnUserAvtar();
		for (int i = 0; i < userAvtarOptions.length; i++)
		{
			Assert.assertTrue(verifyUserAvtarOptions(userAvtarOptions[i]));
		}
		clickOnUserAvtar();

		Assert.assertTrue(dashboardWeb.verifyGlobalNavigationMenuOption(menuItemTitle));

		Assert.assertTrue(dashboardWeb.verifyRecentTabSiteList());
		Assert.assertTrue(dashboardWeb.verifyFavouritesTabSiteList());
		dashboardWeb.clickOnMoreLeftPanel();
		Assert.assertTrue(dashboardWeb.verifyCategoriesTabSiteList());
		dashboardWeb.clickOnMoreLeftPanel();
		Assert.assertTrue(dashboardWeb.verifyAddSiteLink());
		Assert.assertTrue(dashboardWeb.verifySearchSiteIcon());

		for (int i = 0; i < verifyMiddlePanelFilters.length; i++)
		{
			Assert.assertTrue(dashboardWeb.verifyTabsOfRecentActivity(verifyMiddlePanelFilters[i]));
		}

		Assert.assertTrue(dashboardWeb.verifyReceivedFilesTab());
		Assert.assertTrue(dashboardWeb.verifySharedFilesTab());
		Assert.assertTrue(dashboardWeb.verifySeeAllFilesLink());
		Assert.assertTrue(dashboardWeb.verifySendAFileButton());

		Assert.assertTrue(dashboardWeb.verifyPersonalTaskTab());
		Assert.assertTrue(dashboardWeb.verifyAssignToMeTaskTab());
		Assert.assertTrue(dashboardWeb.verifyAddTaskIcon());
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	void preCondition() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		globalNavigationAdminWeb = systemAdminWeb.gotoGlobalNavigation();
		globalNavigationAdminWeb.deleteMenuItem(menuItemTitle);
		globalNavigationAdminWeb.selectGlobalNavigationOption(globalNavigationOption);
		globalNavigationAdminWeb.clickOnMenuItemDropDown();
		globalNavigationAdminWeb.selectMenuItemOption(menuItemOption);
		globalNavigationAdminWeb.clickExternalOnAddMenuItemDialog();
		globalNavigationAdminWeb.addExternalMenuItem(menuItemTitle, menuItemURL, openIn);
		globalNavigationAdminWeb.clickSaveOnGlobalNavigation();

		dashboardWeb = globalNavigationAdminWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();

		adminWikiWeb = adminPageWeb.clickOnWikiInLeftPanel();
		adminWikiWeb.setWikiOptions("Enable approval workflow", true);
		adminWikiWeb.clickOnSave();

		aspAdminWeb = adminWikiWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableMyFiles(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_UI_OPTION_ON);
		aspConfigurationWeb.enableMyFilesSharing(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEFILESHARING_ALLSYSTEMUSERS);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyFiles(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_UI_OPTION_ON);
		systemAdminSystemSettingsWeb.enableMyFilesSharing(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEFILESHARING_ALLSYSTEMUSERS);
		systemAdminSystemSettingsWeb.saveSettings();

		logout();

		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		createWiki(wikiTitle);
		dashboardWeb.logout();
	}

	public void createWiki(String wikiName)
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
		wikiWeb.saveWiki();
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}
}
