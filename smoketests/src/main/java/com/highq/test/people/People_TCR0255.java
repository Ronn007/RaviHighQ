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
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminPeoplePage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.PeoplePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class People_TCR0255 extends BannerPageWeb
{
	/** People Search */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "People_TCR0255";
	String[] userNames = {"normalsmokeuser2org1", "siteadminsmokeuser2org1"};
	String domain = "first.com";

	String[] usersOfSecondOrganisation = {"normalsmokeuser2org2", "siteadminsmokeuser2org2"};
	String domain2 = "second.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";
	String defaultOrgOption = "All organisations";
	String firstOrg = "first";
	String secondOrg = "second";
	int expectedOrgCount = 3;
	String[] fieldsToDisplay = {"Avatar", "Email address", "Office phone", "Mobile phone", "Job title", "Department"};

	String userForLogin;

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	PeoplePage peopleWeb;
	AdminPeoplePage adminPeopleWeb;
	AddUserPage addUserWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void PeopleTCR0255() throws InterruptedException, IOException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: People Search */
		precondition();
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		/* Verify organisations */
		assertTrue(peopleWeb.verifyOrganisationInSearchFilter(defaultOrgOption));
		assertTrue(peopleWeb.verifyOrganisationInSearchFilter(firstOrg));
		assertTrue(peopleWeb.verifyOrganisationInSearchFilter(secondOrg));

		/* Filter by organisation and verify only filtered records are displayed */
		peopleWeb.filterByOrganisation(firstOrg);
		assertTrue(peopleWeb.verifyFilteredOrgName(firstOrg));
		for (int i = 0; i < userNames.length; i++)
		{
			peopleWeb.searchPeople(userNames[i] + "@" + domain);
			assertTrue(peopleWeb.verifyName(userNames[i]));
			assertTrue(peopleWeb.verifyOrganisation(firstOrg));
		}
		peopleWeb.searchPeople(usersOfSecondOrganisation[0] + "@" + domain2);
		assertTrue(peopleWeb.verifySearchResultIsEmpty());

		peopleWeb.filterByOrganisation(secondOrg);
		assertTrue(peopleWeb.verifyFilteredOrgName(secondOrg));
		for (int i = 0; i < usersOfSecondOrganisation.length; i++)
		{
			peopleWeb.searchPeople(usersOfSecondOrganisation[i] + "@" + domain2);
			assertTrue(peopleWeb.verifyName(usersOfSecondOrganisation[i]));
			assertTrue(peopleWeb.verifyOrganisation(secondOrg));
		}
		peopleWeb.searchPeople(userNames[0] + "@" + domain);
		assertTrue(peopleWeb.verifySearchResultIsEmpty());

		/* Filter by all organisation and verify users */
		peopleWeb.filterByOrganisation(defaultOrgOption);
		for (int i = 0; i < userNames.length; i++)
		{
			peopleWeb.searchPeople(userNames[i] + "@" + domain);
			assertTrue(peopleWeb.verifyName(userNames[i]));
			assertTrue(peopleWeb.verifyOrganisation(firstOrg));
		}
		for (int i = 0; i < usersOfSecondOrganisation.length; i++)
		{
			peopleWeb.searchPeople(usersOfSecondOrganisation[i] + "@" + domain2);
			assertTrue(peopleWeb.verifyName(usersOfSecondOrganisation[i]));
			assertTrue(peopleWeb.verifyOrganisation(secondOrg));
		}
		/* Verify 1. Avatar 2. Email 3. Office Phone 4. Mobile Phone 5. Job Title 6. Department fields */
		peopleWeb.searchPeople(getUserData(userNames[0]));
		assertTrue(peopleWeb.verifyName(userNames[0]));

		assertTrue(peopleWeb.verifyUserAvatarIconIsPresent());
		assertTrue(peopleWeb.verifyNameFieldIsPresent());
		assertTrue(peopleWeb.verifyOrganisationFieldIsPresent());
		assertTrue(peopleWeb.verifyEmailFieldIsPresent());
		assertTrue(peopleWeb.verifyOfficePhoneFieldIsPresent());
		assertTrue(peopleWeb.verifyMobilePhoneFieldIsPresent());

		/* Verify Organisation users in Admin -> User Permissions -> Users */
		adminPageWeb = peopleWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		// assertTrue(addUserWeb.verifyDefaultFilterUserOrganisation(defaultOrgOption));
		addUserWeb.selectFilterUserOrganisation(firstOrg);
		addUserWeb.clickFilterButton();
		for (int i = 0; i < userNames.length; i++)
			assertTrue(addUserWeb.verifyUserName(userNames[i] + "@" + domain));

		addUserWeb.selectFilterUserOrganisation(secondOrg);
		addUserWeb.clickFilterButton();
		for (int i = 0; i < usersOfSecondOrganisation.length; i++)
			assertTrue(addUserWeb.verifyUserName(usersOfSecondOrganisation[i] + "@" + domain2));
		peopleWeb.gotoDashboard();
		logout();

	}

	private void precondition() throws InterruptedException
	{
		/** Create site,Create multiple organization with multiple users */
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
		orgData.put(domain2, permissionOfOrg);

		configurationData.setOrgData(orgData);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));
		userMap.put(domain2, Arrays.asList(usersOfSecondOrganisation));
		configurationData.setUserMap(userMap);

		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);

		configurationData.setModuleList("files", "people", "wiki");

		// create users with respective roles at site level begins
		LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
		userRolesOfUser.put(userRole.toLowerCase(), true);
		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);
		siteUserRoles.put(usersOfSecondOrganisation[1] + "@" + domain2, userRolesOfUser);
		// Set module permission for users

		Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
		modulePermissions.put("View", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", modulePermissions);
		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);
		siteUserModulePermission.put(usersOfSecondOrganisation[0] + "@" + domain2, modulePermission);

		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		adminPageWeb = adminSecurityWeb.gotoAdminModule();
		adminPeopleWeb = adminPageWeb.clickPeopleInLeftPanel();

		for (int i = 0; i < fieldsToDisplay.length; i++)
			adminPeopleWeb.setOption(fieldsToDisplay[i], true);

		adminPeopleWeb.clickOnSave();
		logout();
	}

}
