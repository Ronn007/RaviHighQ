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
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.MyProfilePage;
import com.highq.pageobjects.base.PeoplePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class People_TCR0252 extends BannerPageWeb
{
	/** People Follow-Following/Unfollow */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "People_TCR0252";
	String[] userNames = {"normalsmokeuserfor252", "siteadminsmokeuserfor252"};
	String domain = "smoke.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String userForLogin;

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AdminSecurityPage adminSecurityWeb;
	PeoplePage peopleWeb;
	MyProfilePage myprofileWeb;
	BannerPage bannerPageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void PeopleTCR0252() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: People Follow/Following */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		/** Follow user */
		peopleWeb.followUser(userNames[1] + "@" + domain);
		/** Verify following button against user who has been followed */
		assertTrue(peopleWeb.verifyFollowingButtonAgainstUser(userNames[1] + "@" + domain));

		/** Verify user available under My profile -> Following */
		myprofileWeb = peopleWeb.gotoMyProfile();
		myprofileWeb.gotoFollowingInLeftPanel();
		assertTrue(myprofileWeb.verifyUserInFollowingOrFollowers(userNames[1]));
		logout();

		/** Login with site admin user */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		/** Verify user available under My profile -> Followers */
		myprofileWeb = dashboardWeb.gotoMyProfile();
		myprofileWeb.gotoFollowersInLeftPanel();
		assertTrue(myprofileWeb.verifyUserInFollowingOrFollowers(userNames[0]));
		logout();

		/** Case 2: Normal user Unfollows a user */
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		/** Unfollow user */
		assertTrue(peopleWeb.verifyUnfollowButtonOnMouseHover(userNames[1] + "@" + domain));
		peopleWeb.unfollowUser(userNames[1] + "@" + domain);
		/** Verify follow button against user who has been unfollowed */
		assertTrue(peopleWeb.verifyFollowButtonAgainstUser(userNames[1] + "@" + domain));

		/** Verify user is not available under My profile -> Following */
		myprofileWeb = peopleWeb.gotoMyProfile();
		myprofileWeb.gotoFollowingInLeftPanel();
		assertTrue(!myprofileWeb.verifyUserInFollowingOrFollowers(userNames[1]));
		logout();

		/** Login with site admin user */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		/** Verify user is not available under My profile -> Followers */
		myprofileWeb = dashboardWeb.gotoMyProfile();
		myprofileWeb.gotoFollowersInLeftPanel();
		assertTrue(!myprofileWeb.verifyUserInFollowingOrFollowers(userNames[0]));
		logout();
	}

	private void precondition() throws InterruptedException
	{
		/** Create site and add normal user, site admin */
		preconfiguration();

	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		// Add and Verify organization
		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		configurationData.setOrgData(orgData);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));
		configurationData.setUserMap(userMap);

		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);

		configurationData.setModuleList("files", "people", "wiki");

		LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
		userRolesOfUser.put(userRole.toLowerCase(), true);
		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

		Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
		modulePermissions.put("View", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", modulePermissions);
		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission",
				"enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}

}
