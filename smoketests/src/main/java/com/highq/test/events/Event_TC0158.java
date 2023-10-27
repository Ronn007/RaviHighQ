/**
 * 
 */
package com.highq.test.events;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
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
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @creation date 09/02/2018
 */
public class Event_TC0158 extends BannerPageWeb
{
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPage;
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
	AspAdminPage aspAdminPage;
	AspConfigurationPage aspConfigurationPage;
	AdminSecurityPage adminSecurityPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Event_TC0158";
	String domain = "eventorg.com";
	String rssPermission = "TRUE";

	String eventName = "First Event " + getRandomString();

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @throws AWTException
	 * @creation date 09/02/2018
	 */
	@Test
	public void EventTC0158() throws InterruptedException, UnsupportedFlavorException, IOException, AWTException
	{
		TC0158_PreConditionsOfUserCreation();
		scenario1();
		cleanSite();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 09/02/2018
	 */
	public void TC0158_PreConditionsOfUserCreation() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** To Set asp configuration */
		aspAdminPage = bannerPageWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminPage.openConfigurationPage();
		aspConfigurationPage.setRssFeedSecurity(rssPermission);
		aspConfigurationPage.saveConfigurations();

		/** To set user permissions */
		systemAdminWeb = aspConfigurationPage.gotoSystemAdmin();

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

		adminPage = systemAdminWeb.gotoAdminModule();
		adminSecurityPage = adminPage.clickSecurityInLeftPanel();
		adminSecurityPage.setAuthenticationRequiredForRSS(true);
		adminSecurityPage.saveAdvancedChanges();
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @throws AWTException
	 * @creation date 09/02/2018
	 */
	public void scenario1() throws InterruptedException, UnsupportedFlavorException, IOException, AWTException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		eventsPage.addEvent(eventName);
		eventsPage.verifyEventInEventList(eventName);

		String link = eventsPage.getRssFeedLink();
		String[] url1 = link.split("//");
		link = url1[0] + "//" + userNames[1] + "@" + domain + ":" + nPassword + "@" + url1[1];
		eventsPage.openCopiedURL(link);
		Assert.assertTrue(eventsPage.verifyEventInRSSFeed(eventName));
		eventsPage.clickOnEventInRSSFeed(eventName);
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventName));
		eventsPage.closeCurrentTab();
	}

	/**
	 * @author vivek.mishra
	 *         To clean up the site
	 * @creation date 17/02/2018
	 */
	public void cleanSite()
	{
		eventsPage.deleteEventInEventList(eventName);
	}

}
