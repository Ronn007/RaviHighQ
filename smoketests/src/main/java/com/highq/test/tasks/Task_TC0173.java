package com.highq.test.tasks;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.TaskLabels;
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
public class Task_TC0173 extends BannerPageWeb
{
	/** Favourite Task */

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";// "auto@highq.com";//"ravi.middha@highq.com";//"admin.user@highq.com";//"tom.chick@highq.com";
	String sysAdminPassword = /* "Pa&&worD123"; */"Password@123";
	String siteName = "Task_TC0173";
	String[] userNames = {"normaluserforlogin_TC0173", "site.admin_TC0173"};
	String domain = "sitelevel0173.com";
	String newPassword = "Password@123";
	String orgType = "Internal";

	String taskTitle = "Task_TC0173";
	String taskList = "ListOfTC0173";
	String status = "Not started";

	DocumentPage documentWeb;
	TasksPage tasksWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TaskTC0173() throws InterruptedException, IOException
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
		if (!tasksWeb.verifyTaskVisibility(taskTitle))
		{
			tasksWeb.clickHeaderAddButton();
			tasksWeb.setTaskTitle(taskTitle);
			tasksWeb.clickOnAddNewListLink();
			tasksWeb.setTaskList(taskList);
			tasksWeb.clickAddListSubmittButtonEnable();
			Assert.assertTrue(tasksWeb.verifyAddNewListLink());
			Assert.assertTrue(tasksWeb.verifyTaskListOption(taskList));
			Map<String, String> addTaskData = new LinkedHashMap<>();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskList);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[1] + "@" + domain);
			tasksWeb.setTaskAddData(addTaskData);
			tasksWeb.clickOnAddTaskButtonInModel();
			Assert.assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));
		}
		/** Favourite a task */
		tasksWeb.selectTask(taskTitle);
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.addTaskToFavourites();
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());

		/** Verify same task is available in favourites tab */
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));
		tasksWeb.selectTask(taskTitle);
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());

		dashboardWeb = tasksWeb.gotoDashboard();
		logout();

		/** Verify same task is not in favourites of another user */
		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();
		tasksWeb.selectTask(taskTitle);
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(!tasksWeb.verifyTaskVisibility(taskTitle));
		dashboardWeb = tasksWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();

		/** Remove task from favourites */
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));
		tasksWeb.selectTask(taskTitle);
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.removeTaskFromFavourites();
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(!tasksWeb.verifyTaskVisibility(taskTitle));

		/** Verify favourite star is no longer selected for task */
		tasksWeb.clickAllTasksTab();
		tasksWeb.searchTask(taskTitle);
		tasksWeb.selectTask(taskTitle);
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());

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
