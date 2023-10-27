package com.highq.test.tasks;

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
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class Task_TC0177 extends BannerPageWeb
{
	/** Search Task */

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "Task_TC0177";
	String[] userNames = {"normaluserforlogin", "site.admin"};
	String domain = "sitelevel0177.com";
	String newPassword = "Password@123";
	String orgType = "Internal";

	String dummyTask = "task" + getRandomString();
	String[] tasks = {"task-1", "task-2", "task-3"};
	String startsWithText = "task";

	DocumentPage documentWeb;
	TasksPage tasksWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TaskTC0177() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario01();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();
	}

	private void scenario01() throws InterruptedException, IOException
	{

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();
		/** Add task if it is not available */
		for (int i = 0; i < tasks.length; i++)
		{
			if (!tasksWeb.verifyTaskVisibility(tasks[i]))
			{
				tasksWeb.clickHeaderAddButton();
				tasksWeb.setTaskTitle(tasks[i]);
				tasksWeb.clickOnAddInAddTaskModal();
			}
		}
		/** Verify task via search */
		for (int i = 0; i < tasks.length; i++)
		{
			assertTrue(tasksWeb.verifyTaskVisibility(tasks[i]));
		}

		/** Verify task is not displayed in search results if it is not created */
		assertTrue(!tasksWeb.verifyTaskVisibility(dummyTask));

		/** Verify all the task with required start text are displayed in search result */
		tasksWeb.searchTask(startsWithText);
		assertTrue(tasksWeb.verifyTaskStartsWith(startsWithText));

		tasksWeb.gotoDashboard();
		logout();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin".toLowerCase().trim(), true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Tasks", moduleEditPermission);
		modulePermission.put("Files", moduleEditPermission);

		Map<String, String> userData = new LinkedHashMap<>();
		userData.put(domain, userNames[0]);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		logout();

	}
}
