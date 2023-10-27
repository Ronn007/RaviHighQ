package com.highq.test.dashboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.AdminWikiWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.test.wiki.BaseWikiTest;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0210 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	String siteName = "Dashboard_TC0210_2";
	String[] userNames = {"dashboard.normaluser2102@dashboard210.com"};
	String domain = "dashboard210.com";
	String newPassword = "Password@123";
	String wikiTitle = "Dashboard_TC0210" + getRandomString();
	String wikiContent = "Dashboard_TC0210" + getRandomString();
	String jsonFileName = "preConfiguration_Dashboard_TCR0210.json";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	AdminAdvancedPage adminAdvancedWeb;
	AdminWikiWeb adminWikiWeb;
	AddUserPage addUserWeb;
	LoginPage loginPageWeb;
	WikiPage wikiWeb;
	BannerPage bannerPageWeb;
	Map<Boolean, BannerPageWeb> result;
	List<String> modules;

	BaseWikiTest baseWikiTest;
	Map<String, List<String>> userMap;
	List<String> userList;
	ConfigurationData configurationData = new ConfigurationData();

	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Override
	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 1)
	public void dashboardTC0210() throws InterruptedException, IOException, JSONException
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
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		Assert.assertTrue(verifyNotificationLink());

		Assert.assertTrue(dashboardWeb.verifyTooltipDisplayed("Notifications"));

		clickOnNotificationLink();
		Assert.assertTrue(verifyNotificationModal());

		Assert.assertTrue(verifyFirstNotification(getUserData(userNames[0]) + " liked " + wikiTitle));

		Assert.assertTrue(isSeeAllNotificationsEnabled());
		clickOnSeeAllNotifications();
		Assert.assertTrue(verifySeeAllNotificationsModalOpened());
		cancelSeeAllNotificationsModal();

		clickOnNotificationLink();
		Assert.assertTrue(verifyAwaitingApprovalMessage());
		Assert.assertTrue(verifyAwaitingApprovalCount());
		clickOnAwaitingApproval();
		Assert.assertTrue(dashboardWeb.verifyApprovalWorkflow());
	}

	void preCondition() throws InterruptedException, IOException
	{
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		createWiki(wikiTitle);

		adminPageWeb = wikiWeb.gotoAdminModule();

		adminWikiWeb = adminPageWeb.clickOnWikiInLeftPanel();
		adminWikiWeb.setWikiOptions("Enable approval workflow", true);
		adminWikiWeb.clickOnSave();

		dashboardWeb.logout();

		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		wikiWeb.selectWikiFromLeftPanel(wikiTitle);
		wikiWeb.clickOnWikiLikeTag();
		wikiWeb = dashboardWeb.gotoWikiModule();
		createWiki(wikiTitle + getRandomString());
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
		// logout();
	}
}
