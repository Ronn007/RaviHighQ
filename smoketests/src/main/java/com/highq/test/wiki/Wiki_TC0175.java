package com.highq.test.wiki;

import static org.testng.Assert.assertFalse;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class Wiki_TC0175 extends BannerPageWeb
{
	/** Automation test for Orphan Page Wiki */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Wiki_TC0175";
	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String wikiTitle = "Wiki_TC0175";
	String wikiContent = "Wiki Content for TC0175";
	String childWikiTitle = "ChildWiki_TC0175";
	String childWikiContent = "Wiki Content for child of TC0175";
	String jsonFileName = "preConfiguration_Wiki_TC0175.json";

	AdminPage adminPageWeb;
	WikiPage wikiWeb;
	DashboardPage dashboardWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void WikiTC0175() throws InterruptedException, IOException, JSONException
	{
		scenario1();
	}

	public void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Orphan pages tab getting displayed in left panel above Home wiki */
		siteAndUserSetup();
		String userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		precondition();
		wikiWeb.deleteWiki(wikiTitle);

		if (wikiWeb.verifySearchedPage(wikiTitle))
			assertFalse(true);
		wikiWeb.gotoWikiModule();

		if (wikiWeb.verifyWikiInOrphanPages(childWikiTitle))
			System.out.println(childWikiTitle + " found in Orphan Pages");
		else
			assertFalse(true);

		wikiWeb.deleteWiki(childWikiTitle);
		if (wikiWeb.verifySearchedPage(childWikiTitle))
			assertFalse(true);
		logout();
	}

	public void precondition()
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

		wikiWeb.selectOptionInMoreActionInSelectedWiki("Add child page");
		createWiki(childWikiTitle, childWikiContent);
		selectWiki(childWikiTitle);
		if (wikiWeb.verifyChildLevelWiki(wikiTitle, childWikiTitle))
			System.out.println("Child of " + wikiTitle + ", " + "[" + childWikiTitle + "] created successfully.");
		else
			assertFalse(true);
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

	private void siteAndUserSetup() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}
}
