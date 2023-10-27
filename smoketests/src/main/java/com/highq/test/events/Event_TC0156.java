/**
 * 
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
 * @creation date 01/02/2018
 */
public class Event_TC0156 extends BannerPageWeb
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
	String[] userNames = {"sitenormaluser1", "siteadminuser1", "contentadminuser1"};
	String orgType = "Internal";
	String siteName = "Event_TC0156";
	String domain = "eventorg.com";

	String fileName = "Holidays.ics";
	String category = "Default";

	ConfigurationData configurationData = new ConfigurationData();

	String successMsg = "51 events imported";
	String eventName = "Maha Shivaratri";
	String eventName2 = "Holi";
	String editOption = "Edit";
	String date = "02/14/2018";
	String date1 = "03/02/2018";
	String previous = "previous";

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws ParseException
	 * @creation date 01/02/2018
	 */
	@Test
	public void EventTC0156() throws InterruptedException, IOException, ParseException
	{
		TC0156_PreConditionsOfUserCreation();
		scenario1();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 01/02/2018
	 */
	public void TC0156_PreConditionsOfUserCreation() throws InterruptedException
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
		LinkedHashMap<String, Boolean> userRolesOfUser1 = new LinkedHashMap<>();
		userRolesOfUser1.put("content admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);
		modulePermission.put("Events", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);
		siteUserRoles.put(userNames[2] + "@" + domain, userRolesOfUser1);

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
	 * @creation date 01/02/2018
	 */
	public void scenario1() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminEventPage = adminPageWeb.clickEventsInLeftPanel();
		adminEventPage.importIcsFile(fileName, category);
		Assert.assertTrue(adminEventPage.verifyImportICSFileProgressBar());

		Assert.assertTrue(adminEventPage.verifyImportResultMessage(successMsg));
		adminEventPage.clickOnCloseButtonInImportICSModal();
		Assert.assertFalse(adminEventPage.verifyImportICSModal());

		eventsPage = adminEventPage.gotoEventModule();
		eventsPage.setDateInLeftPannelCalender(date);
		eventsPage.verifyEventInEventList(eventName);
		eventsPage.clickOnMoreActionOfAnEventInEventList(eventName);
		Assert.assertTrue(eventsPage.verifyOptionInMoreActionOfAnEvent(eventName, editOption));

		eventsPage.clickOnDayButton();
		eventsPage.setDateInLeftPannelCalender(date1);
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(eventName2));
		eventsPage.logout();

		bannerPageWeb = login(userNames[2] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();
		eventsPage.setDateInLeftPannelCalender(date);
		eventsPage.verifyEventInEventList(eventName);
		eventsPage.clickOnMoreActionOfAnEventInEventList(eventName);
		Assert.assertTrue(eventsPage.verifyOptionInMoreActionOfAnEvent(eventName, editOption));
		eventsPage.logout();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();
		eventsPage.setDateInLeftPannelCalender(date);
		eventsPage.verifyEventInEventList(eventName);
		eventsPage.clickOnMoreActionOfAnEventInEventList(eventName);
		Assert.assertTrue(eventsPage.verifyOptionInMoreActionOfAnEvent(eventName, editOption));
		eventsPage.logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();
		eventsPage.setDateInLeftPannelCalender(date);
		eventsPage.verifyEventInEventList(eventName);
		eventsPage.clickOnEventInEventList(eventName);
		eventsPage.verifyEventInEditSection(eventName);
		eventsPage.clickOnMoreActionInEditSection();
		Assert.assertFalse(eventsPage.verifyAnOptionInMoreActionInEditSection(editOption));
	}

}
