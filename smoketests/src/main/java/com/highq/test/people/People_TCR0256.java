package com.highq.test.people;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminBidderOrganisationsPage;
import com.highq.pageobjects.base.AdminBidderTeamPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.PeoplePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class People_TCR0256 extends BannerPageWeb
{
	/** People Bidder by Organisation */
	/* Note: Internal organization users should not be marked as bidder. Always test with external organization users. */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "People_TCR0256";
	String[] bidder1Users = {"normalsmokeuserbidder1", "normalsmokeuser2bidder1"};
	String domainBidder1 = "bidder1.com";

	String[] bidder2Users = {"normalsmokeuserbidder2", "normalsmokeuser2bidder2"};
	String domainBidder2 = "bidder2.com";
	String[] nonBidderUsers = {"normalsmokeusernonbidder", "normalsmokeuser2nonbidder"};
	String domainNonBidder = "nonbidder.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";
	String bidder1Org = "bidder1";
	String bidder2Org = "bidder2";
	String nonBidderOrg = "nonbidder";
	int i;

	String userForLogin;

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	PeoplePage peopleWeb;
	AdminBidderOrganisationsPage adminBidderOrganisationsWeb;
	AdminBidderTeamPage adminBidderTeamWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void PeopleTCR0256() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: People Bidder by Organisation */
		precondition();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminBidderOrganisationsWeb = adminPageWeb.clickBidderOrganisationsInLeftPanel();
		/* Set bidder organisations */
		adminBidderOrganisationsWeb.setBidderOrganisation(bidder1Org, true);
		adminBidderOrganisationsWeb.setBidderOrganisation(bidder2Org, true);
		/* Set non bidder organisations */
		adminBidderOrganisationsWeb.setBidderOrganisation(nonBidderOrg, false);
		adminBidderOrganisationsWeb.clickSave();
		adminPageWeb = adminBidderOrganisationsWeb.gotoAdminModule();
		adminBidderTeamWeb = adminPageWeb.clickBidderTeamInLeftPanel();
		adminBidderTeamWeb.selectAllUsersOfABidderTeamOrganisation(nonBidderOrg, true);
		adminBidderTeamWeb.saveBidderTeamChanges();
		adminBidderTeamWeb.gotoDashboard();
		logout();

		/* Login with bidder 1 user and verify only bidder 1 users are shown in People */
		userForLogin = bidder1Users[0] + "@" + domainBidder1;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		verifyBidderUsers(bidder1Users, bidder1Org);
		verifyBidderUsers(nonBidderUsers, nonBidderOrg);
		peopleWeb.searchPeople(getUserData(bidder2Users[0]));
		assertTrue(peopleWeb.verifySearchResultIsEmpty());
		peopleWeb.gotoDashboard();
		logout();

		/* Login with bidder 2 user and verify only bidder 2 users are shown in People */
		userForLogin = bidder2Users[0] + "@" + domainBidder2;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		verifyBidderUsers(bidder2Users, bidder2Org);
		verifyBidderUsers(nonBidderUsers, nonBidderOrg);
		peopleWeb.searchPeople(getUserData(bidder1Users[0]));
		assertTrue(peopleWeb.verifySearchResultIsEmpty());
		peopleWeb.gotoDashboard();
		logout();

		/* Login with non bidder user and verify all users(bidder/non-bidder) users are shown in People */
		userForLogin = nonBidderUsers[0] + "@" + domainNonBidder;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		verifyBidderUsers(nonBidderUsers, nonBidderOrg);
		verifyBidderUsers(bidder2Users, bidder2Org);
		verifyBidderUsers(bidder1Users, bidder1Org);
		peopleWeb.gotoDashboard();
		logout();

	}

	public void verifyBidderUsers(String[] bidderUsers, String bidderOrganisation) throws InterruptedException
	{
		for (i = 0; i < bidderUsers.length; i++)
		{
			peopleWeb.searchPeople(getUserData(bidderUsers[i]));
			assertTrue(peopleWeb.verifyName(bidderUsers[i]));
			assertTrue(peopleWeb.verifyOrganisation(bidderOrganisation));
		}
	}

	private void precondition() throws InterruptedException
	{
		/** Create site,Create multiple external organization with multiple users,Enable bidder site */
		preconfiguration();

	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		// Add and Verify organization
		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, false);
		orgData.put(domainBidder1, permissionOfOrg);
		orgData.put(domainBidder2, permissionOfOrg);
		orgData.put(domainNonBidder, permissionOfOrg);

		configurationData.setOrgData(orgData);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domainBidder1, Arrays.asList(bidder1Users));
		userMap.put(domainBidder2, Arrays.asList(bidder2Users));
		userMap.put(domainNonBidder, Arrays.asList(nonBidderUsers));
		configurationData.setUserMap(userMap);

		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);

		configurationData.setModuleList("files", "people", "wiki");

		// Set module permission for users

		Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
		modulePermissions.put("View", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", modulePermissions);
		Map<String, String[]> userData = new LinkedHashMap<>();
		userData.put(domainBidder1, bidder1Users);
		userData.put(domainBidder2, bidder2Users);
		userData.put(domainNonBidder, nonBidderUsers);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		for (i = 0; i < bidder1Users.length; i++)
			siteUserModulePermission.put(bidder1Users[i] + "@" + domainBidder1, modulePermission);
		for (i = 0; i < bidder1Users.length; i++)
			siteUserModulePermission.put(bidder2Users[i] + "@" + domainBidder2, modulePermission);
		for (i = 0; i < bidder1Users.length; i++)
			siteUserModulePermission.put(nonBidderUsers[i] + "@" + domainNonBidder, modulePermission);

		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		// Enable Advance site admin options and bidder site
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.setBidderSitePermission(true);
		adminSecurityWeb.saveAdvancedChanges();
		dashboardWeb = adminSecurityWeb.gotoDashboard();
		logout();
	}

}
