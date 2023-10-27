/**
 * @author vivek.mishra
 * @creation date 06/02/2018
 */
package com.highq.test.events;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminEventPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchContentPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @creation date 06/02/2018
 */
public class Event_TC0157 extends BannerPageWeb
{
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	AdminEventPage adminEventPage;
	EventsPage eventsPage;
	TasksPage taskPage;
	SearchContentPage searchContentPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser1", "siteadminuser1"};
	String orgType = "Internal";
	String siteName = "Event_TC0157";
	String domain = "eventorg.com";

	ConfigurationData configurationData = new ConfigurationData();

	String calendarName = "First_ICAL " + getRandomString();
	String calURL = "https://www.officeholidays.com/ics/ics_country_iso.php?tbl_country=IN";
	String category = "Default";
	String eventName = "India: Bohag Bihu Holiday (Regional)";
	String date = "04/14/2018";

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws ParseException
	 * @creation date 06/02/2018
	 */
	@Test
	public void EventTC0157() throws InterruptedException, IOException, ParseException
	{
		TC0157_PreConditionsOfUserCreation();
		scenario1();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 06/02/2018
	 */
	public void TC0157_PreConditionsOfUserCreation() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
		userRolesOfUser.put("site admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);
		modulePermission.put("Events", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(nPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "events");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws ParseException
	 * @creation date 06/02/2018
	 */
	public void scenario1() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminEventPage = adminPageWeb.clickEventsInLeftPanel();

		adminEventPage.clickSubscribe();
		adminEventPage.verifySubscribeModal();
		adminEventPage.setCalendarName(calendarName);
		adminEventPage.setCalendarUrl(calURL);
		adminEventPage.selectSubscribeCategory(category);
		adminEventPage.clickSubscribeInSubscribeModal();

		eventsPage = adminEventPage.gotoEventModule();
		Assert.assertTrue(eventsPage.verifyDateIsAsLink(date));
		Assert.assertTrue(eventsPage.verifyEventInEventList(eventName));
		Assert.assertTrue(eventsPage.verifyCategoryInEventList(eventName, category));
	}
}
