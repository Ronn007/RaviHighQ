package com.highq.test.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
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
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0223 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteNames[] = {"Dashboard_TC0223_Site1", "Dashboard_TC0223_Site2", "Dashboard_TC0223_Site3", "Dashboard_TC0223_Site4", "Dashboard_TC0223_Site5"};
	String[] userNames = {"user.normalTCR0223"};
	String domain = "dashboard223.com";
	String newPassword = "Password@123";

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
	BannerPage bannerPageWeb;

	PreConfiguration preConfiguration;
	Map<String, List<String>> userMap;
	List<String> userList;

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
	public void dashboardTC0223() throws InterruptedException, IOException
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
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnInstanceName();

		dashboardWeb.selectOptionFromSiteDropDown("My site");
		Assert.assertTrue(dashboardWeb.verifyMySite());
		gotoDashboard();

		dashboardWeb.clickOnInstanceName();
		dashboardWeb.selectOptionFromSiteDropDown("All sites and templates");
		Assert.assertTrue(dashboardWeb.verifyAllSiteAndTemplates());
		gotoDashboard();

		dashboardWeb.clickOnInstanceName();
		dashboardWeb.makeSiteFavouriteInSiteDropDown(siteNames[2], true);
		clickOnFavourite();
		clickOnFavouriteFilter();
		Assert.assertTrue(verifySiteInFavouriteList(siteNames[2]));
		clickOnFavourite();

		dashboardWeb.clickOnInstanceName();
		dashboardWeb.makeSiteFavouriteInSiteDropDown(siteNames[2], false);
		clickOnFavourite();
		clickOnFavouriteFilter();
		Assert.assertFalse(verifySiteInFavouriteList(siteNames[2]));
		clickOnFavourite();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	void preCondition() throws InterruptedException, IOException
	{
		preConfiguration = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);
		systemAdminWeb = preConfiguration.setOrganisation(orgData);

		userList = new ArrayList<>();
		for (int i = 0; i < userNames.length; i++)
		{
			userList.add(userNames[i]);
		}

		userMap = new HashMap<>();
		userMap.put(domain, userList);

		searchUserweb = preConfiguration.createAndResetUsers(userMap, newPassword, UserStatus.Active, false);

		for (int i = 0; i < siteNames.length; i++)
		{
			dashboardWeb = searchUserweb.gotoDashboard();

			dashboardWeb.searchSite(siteNames[i]);
			adminPageWeb = dashboardWeb.gotoAdminModule();
			modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
			modulesPageWeb.enableSpecificModules(true, "home", "files", "wiki", "activity");
			adminPageWeb = modulesPageWeb.clickOnSaveButton();

			adminPageWeb = preConfiguration.createSiteUsers(userMap);
		}
		dashboardWeb.logout();
	}
}
