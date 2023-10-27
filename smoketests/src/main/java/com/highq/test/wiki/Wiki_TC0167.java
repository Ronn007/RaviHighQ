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
public class Wiki_TC0167 extends BannerPageWeb
{
	/** Automation test for Favourite Wiki scenarios */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Wiki_TC0167";
	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String wikiTitle = "Wiki_TC0167";
	String wikiContent = "Wiki Content for TC0167";

	String jsonFileName = "preConfiguration_Wiki_TC0167.json";
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
	public void WikiTC0167() throws InterruptedException, IOException, JSONException
	{
		scenario1();
		scenario2();
	}

	public void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Mark Wiki Favorite */
		precondition();
		String userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
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
		if (wikiWeb.verifyWikiTitle(wikiTitle))
			System.out.println("Title of wiki : " + wikiTitle);
		else
			assertFalse(true);
		wikiWeb.addToFavourite();
		/** Verify Favorite Icon is Selected */
		if (wikiWeb.verifyFavouriteIconIsSelected())
			System.out.println(wikiTitle + " is added to Favourites");
		else
			assertFalse(true);

		wikiWeb.goToFavourites();

		/** Verify favorite wiki is available in Favourites tab */
		if (wikiWeb.verifyWikiInFavourites(wikiTitle))
			System.out.println(wikiTitle + " is available in Favourites screen");
		else
			assertFalse(true);
	}

	public void scenario2() throws InterruptedException
	{
		/** Scenario2: Unfavorite a Wiki */
		selectWiki(wikiTitle);
		if (wikiWeb.verifyWikiTitle(wikiTitle))
			System.out.println("Title of wiki : " + wikiTitle);
		else
			assertFalse(true);
		wikiWeb.removeFromFavourite();

		/** Verify Favorite Icon is not selected */
		if (!wikiWeb.verifyFavouriteIconIsSelected())
			System.out.println(wikiTitle + " is removed from Favourites");
		else
			assertFalse(true);

		wikiWeb.goToFavourites();

		/** Verify wiki is not available in Favourites tab */
		if (!wikiWeb.verifyWikiInFavourites(wikiTitle))
			System.out.println(wikiTitle + " is removed from Favourites screen");
		else
			assertFalse(true);

		logout();
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
	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}

}
