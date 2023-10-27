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
public class Task_TC0161 extends BannerPageWeb
{

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";// "auto@highq.com";//"ravi.middha@highq.com";//"admin.user@highq.com";//"tom.chick@highq.com";
	String sysAdminPassword = /* "Pa&&worD123"; */"Password@123";
	String siteName = "Task_TC0161_Test606";
	String[] userNames = {"normal.siteuser0161_0019", "site.admin"};
	String domain = "sitelevel22.com";
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
	public void TaskTC0161() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario01();
		scenario02();
		scenario03();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();
	}

	private void scenario01() throws InterruptedException, IOException
	{
		addTaskAndTaskList();
	}

	private void scenario02() throws InterruptedException, IOException
	{
		editTask();
	}

	private void scenario03() throws InterruptedException, IOException
	{
		deleteTask();
	}

	private void addTaskAndTaskList() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();

		tasksWeb.clickHeaderAddButton();

		tasksWeb.setTaskTitle(taskTitle);

		tasksWeb.clickOnAddNewListLink();
		tasksWeb.setTaskList(taskList);
		tasksWeb.clickAddListCancelButton();

		Assert.assertTrue(tasksWeb.verifyAddNewListLink());
		Assert.assertTrue(!tasksWeb.verifyTaskListOption(taskList));

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
		addTaskData.put(TaskLabels.TASKS_ADDTASK_REMINDER, reminder);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ATTACHMENT, attachment);

		tasksWeb.setTaskAddData(addTaskData);

		tasksWeb.clickOnAddTaskButtonInModel();
		Assert.assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));

		tasksWeb.selectTask(taskTitle);
		Assert.assertTrue(tasksWeb.verifyTaskData(addTaskData));
		tasksWeb.closeTask();

	}

	private void editTask() throws InterruptedException, IOException
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
		editData.put(TaskLabels.TASKS_ADDTASK_REMINDER, editedRemider);
		editData.put(TaskLabels.TASKS_ADDTASK_ATTACHMENT, editedAttachment);

		tasksWeb.editTask(editData);
		Assert.assertTrue(tasksWeb.verifyTaskData(editData));
		tasksWeb.closeTask();

	}

	private void deleteTask() throws InterruptedException, IOException
	{
		tasksWeb.selectTask(editedTaskTitle);
		tasksWeb.clickOnDeleteTask(editedTaskTitle);

		Assert.assertTrue(tasksWeb.verifyDeleteMessage(deleteMessage));

		tasksWeb.cancelDeleteOperation();
		Assert.assertTrue(tasksWeb.verifyViewTaskTitle(editedTaskTitle));

		tasksWeb.clickOnDeleteTask(editedTaskTitle);
		Assert.assertTrue(tasksWeb.verifyDeleteMessage(deleteMessage));

		tasksWeb.confirmDeleteOperation();
		tasksWeb.selectTask(editedTaskTitle);

		Assert.assertTrue(!tasksWeb.verifyTaskVisibility(editedTaskTitle));

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
