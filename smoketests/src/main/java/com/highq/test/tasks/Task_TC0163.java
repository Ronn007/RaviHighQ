package com.highq.test.tasks;

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
 * @author badal.gandhi
 */
public class Task_TC0163 extends BannerPageWeb
{

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "Task_TC0163_Test001";
	String[] userNames = {"normal.siteuser0163", "site.admin"};
	String domain = "sitelevel.com";
	String newPassword = "Password@123";
	String orgType = "Internal";

	String taskTitle = "Task_" + getRandomString();
	String taskList = "Task_" + getRandomString();
	String status = "Not started";
	String dueDate = "04 Dec 2018";
	String priority = "Normal";
	String description = "Description";
	String tag = "Test";
	String reminder = "3";
	String attachment = "doc1.txt";

	String editedTaskTitle = "Edited_Task_" + getRandomString();
	String editedList = "None";
	String editedStatus = "In progress";
	String editedDueDate = "14 Dec 2018";
	String editedPriority = "Low";
	String editedTaskDescription = "Edited description";
	String editedTag = "EditedTag";
	String editedRemider = "5";
	String editedAttachment = "doc2.txt";

	String deleteMessage = "Are you sure you want to delete this task?";

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
	public void TaskTC0163() throws InterruptedException, IOException
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
		completeTask();
	}

	private void completeTask() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();

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
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDate);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[1] + "@" + domain);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TAGS, tag);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ATTACHMENT, attachment);

		tasksWeb.setTaskAddData(addTaskData);

		tasksWeb.clickOnAddTaskButtonInModel();
		Assert.assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));

		Assert.assertTrue(tasksWeb.completeTask(taskTitle));

		editComplitedTaskTask(taskTitle);

		deleteCompletedTask(editedTaskTitle);
	}

	private void editComplitedTaskTask(String taskTitle) throws InterruptedException, IOException
	{
		tasksWeb.selectTask(taskTitle);

		Map<String, String> editData = new LinkedHashMap<>();
		editData.put(TaskLabels.TASKS_ADDTASK_TITLE, editedTaskTitle);
		editData.put(TaskLabels.TASKS_ADDTASK_LIST, editedList);
		editData.put(TaskLabels.TASKS_ADDTASK_STATUS, editedStatus);
		editData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, editedDueDate);
		editData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[0] + "@" + domain);
		editData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, editedPriority);
		editData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, editedTaskDescription);
		editData.put(TaskLabels.TASKS_ADDTASK_TAGS, editedTag);
		editData.put(TaskLabels.TASKS_ADDTASK_ATTACHMENT, editedAttachment);

		tasksWeb.editTask(editData);
		Assert.assertTrue(tasksWeb.verifyTaskData(editData));
		tasksWeb.closeTask();
	}

	private void deleteCompletedTask(String taskTitle) throws InterruptedException
	{
		tasksWeb.selectTask(taskTitle);
		tasksWeb.clickOnDeleteTask(taskTitle);

		Assert.assertTrue(tasksWeb.verifyDeleteMessage(deleteMessage));
		tasksWeb.cancelDeleteOperation();
		tasksWeb.clickOnDeleteTask(taskTitle);
		Assert.assertTrue(tasksWeb.verifyDeleteMessage(deleteMessage));

		tasksWeb.confirmDeleteOperation();

		tasksWeb.selectTask(taskTitle);

		Assert.assertTrue(!tasksWeb.verifyTaskVisibility(taskTitle));
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

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Tasks", moduleEditPermission);
		modulePermission.put("Files", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();

	}

}
