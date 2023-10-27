package com.highq.test.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0213 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "Dashboard_TC0213";
	String wikiTitle = "";
	String wikiContent = "Dashboard_TC0210" + getRandomString();
	List<String> wikiNames = new ArrayList<>();

	DashboardPage dashboardWeb;
	BannerPage bannerPageWeb;

	WikiPage wikiWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;


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
	 */
	@Test(priority = 1)
	public void dashboardTC0213() throws InterruptedException, IOException
	{
		preCondition();
		scenario1();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		Collections.reverse(wikiNames);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		if (verifyFavouriteLink())
		{
			clickOnFavouriteLink();
			Assert.assertTrue(verifyFavouriteFilterFavouriteModal());
			Assert.assertTrue(verifyRecentlyViewedFilterFavouriteModal());

			clickOnFavouriteFilter();
			Assert.assertTrue(verifyFavouriteOrRecentlyViewedDisplayList());
			Assert.assertTrue(verifyFavouriteContentList(wikiNames));

			clickOnRecentlyViewedFilter();
			Assert.assertTrue(verifyFavouriteOrRecentlyViewedDisplayList());
			Assert.assertTrue(verifyRecentlyViewedContentList(wikiNames));
		}
	}


	public void preCondition() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableAllModules();
		adminPageWeb = modulesPageWeb.clickOnSaveButton();
		wikiWeb = adminPageWeb.gotoWikiModule();
		for (int i = 0; i < 10; i++)
		{
			createWiki();
		}
	}

	public void createWiki()
	{
		wikiTitle = "Dashbord_TCR0213_" + getRandomString();
		wikiNames.add(wikiTitle);
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiTitle);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent + getRandomString());
		wikiWeb.saveWiki();
		wikiWeb.addToFavourite();
	}

}
