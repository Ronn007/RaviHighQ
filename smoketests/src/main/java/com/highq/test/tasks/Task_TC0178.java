package com.highq.test.tasks;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
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
public class Task_TC0178 extends BannerPageWeb
{

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";

	String jsonFileName = "preConfiguration_TC0178.json";

	String siteName = "Task_TC0178_json_02";
	String[] userNames = {"normal.siteuser0161_0019@sitelevel0178.com", "site.admin@sitelevel0178.com"};
	String newPassword = "Password@123";

	String taskTitle = "Task_TC 0178";
	String taskList = "Task_" + getRandomString();
	String status = "Not started";
	String dueDate = "04 Dec 2018";
	String priority = "Normal";
	String description = "Description";
	String tag = "Test";
	String reminder = "3";
	String attachment = "doc1.txt";

	String recipientMail = userNames[1];
	String subject = "Task_TC0178 Share Task Automation";
	String message = "Testing functionality of Share Task.";

	Timestamp startTime;
	Timestamp endTime;

	DocumentPage documentWeb;
	TasksPage tasksWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	AdminAdvancedPage adminAdvancedWeb;
	ActivityPage activityWeb;
	AdminActivityPage adminActivityWeb;
	BannerPage bannerPageWeb;

	int currentCommentCount;
	ConfigurationData configurationData = new ConfigurationData();

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
	public void TaskTC0178() throws InterruptedException, IOException, UnsupportedFlavorException, JSONException
	{
		preconfiguration();
		scenario01();
		scenario02();
		scenario03();
		scenario04();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();

	}

	private void scenario01() throws InterruptedException, IOException
	{
		shareTaskViaEmail();
	}

	private void scenario02() throws UnsupportedFlavorException, IOException, InterruptedException
	{
		shareTaskByLink();
	}

	private void scenario03() throws InterruptedException, IOException
	{
		shareTaskViaMessage();
	}

	private void scenario04() throws InterruptedException, IOException
	{
		shareTaskViaMicroBlog();
	}

	private void shareTaskViaEmail() throws InterruptedException, IOException
	{
		login(userNames[0], newPassword);
		dashboardWeb = gotoDashboard();

		dashboardWeb.searchSite(siteName);
		tasksWeb = gotoTaskModule();

		addTaskPreCondition();

		tasksWeb = gotoTaskModule();
		tasksWeb.clickHeaderViewButton();
		tasksWeb.clickCardViewFromViewMenu();
		tasksWeb.selectTask(taskTitle);
		tasksWeb.clickShareTask();

		Assert.assertTrue(verifyEmptyRecipent());
		Assert.assertTrue(disabledSendButtonInShareDialogueBox());
		Assert.assertTrue(verifyDefaultSubjectIsPresent());

		// tasksWeb.clickSendInShareModal();

		// Verify Mail is not sent after clicking on cancel
		tasksWeb.shareViaEmail(recipientMail, subject, message);
		startTime = tasksWeb.getStartDateTimeStamp();

		tasksWeb.clickCancelInShareModal();
		endTime = tasksWeb.getEndDateTimeStamp();
		Assert.assertTrue(!verifyMail(recipientMail, startTime, endTime, subject, message));

		// Verify Mail sent after clicking on Send
		tasksWeb.clickShareTask();
		tasksWeb.shareViaEmail(recipientMail, subject, message);
		startTime = tasksWeb.getStartDateTimeStamp();
		tasksWeb.clickSendInShareModal();
		tasksWeb.closeTask();
		endTime = tasksWeb.getEndDateTimeStamp();

		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, subject, message));
		dashboardWeb = tasksWeb.gotoDashboard();
		logout();
	}

	private void shareTaskByLink() throws UnsupportedFlavorException, IOException, InterruptedException
	{

		/** Scenario 2: Share via link */

		login(userNames[1], newPassword);
		dashboardWeb = gotoDashboard();
		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		tasksWeb = gotoTaskModule();
		tasksWeb.clickHeaderViewButton();
		tasksWeb.clickCardViewFromViewMenu();

		// Open Task

		tasksWeb.selectTask(taskTitle);
		Assert.assertTrue(tasksWeb.verifyTaskTitle(taskTitle));

		tasksWeb.clickShareTask();
		tasksWeb.copyShareLink(false);
		tasksWeb.clickCancelInShareModal();
		tasksWeb.closeTask();
		dashboardWeb = tasksWeb.gotoDashboard();
		logout();

		// login with normal user
		login(userNames[0], newPassword);

		// open copied URL
		tasksWeb.openCopiedURL();

		// Verify active task is same as taskTitle
		Assert.assertTrue(tasksWeb.verifyActiveTask(taskTitle));
		tasksWeb.closeTask();
		logout();

	}

	private void shareTaskViaMessage() throws InterruptedException, IOException
	{

		/** Scenario 3: Share via message */

		login(userNames[0], newPassword);
		dashboardWeb = gotoDashboard();

		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		tasksWeb = gotoTaskModule();
		tasksWeb.clickHeaderViewButton();
		tasksWeb.clickCardViewFromViewMenu();

		// Open Task

		tasksWeb.selectTask(taskTitle);
		Assert.assertTrue(tasksWeb.verifyTaskTitle(taskTitle));

		tasksWeb.clickShareTask();
		tasksWeb.shareViaMessage(recipientMail, message);

		// Verify Message sent after clicking on Send
		startTime = tasksWeb.getStartDateTimeStamp();
		tasksWeb.clickSendInShareModal();
		tasksWeb.closeTask();
		endTime = tasksWeb.getEndDateTimeStamp();

		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, "", message));

		dashboardWeb = tasksWeb.gotoDashboard();
		logout();

		login(userNames[1], newPassword);
		dashboardWeb = gotoDashboard();
		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(userNames[0], message,true));
			dashboardWeb.clickCancelInMessageBox();
		}
		else
			Assert.assertFalse(true);
		logout();

	}

	private void shareTaskViaMicroBlog() throws InterruptedException, IOException
	{

		/** Scenario 3: Share via Micro blog */

		login(userNames[0], newPassword);
		dashboardWeb = gotoDashboard();

		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		tasksWeb = gotoTaskModule();
		tasksWeb.clickHeaderViewButton();
		tasksWeb.clickCardViewFromViewMenu();

		// Open Task

		tasksWeb.selectTask(taskTitle);
		Assert.assertTrue(tasksWeb.verifyTaskTitle(taskTitle));
		tasksWeb.clickShareTask();
		tasksWeb.gotoMicroblogTab();

		Assert.assertTrue(tasksWeb.verifyDefaultMessageIsPresent());
		Assert.assertTrue(tasksWeb.verifyDefaultMicroblogSite(siteName));

		tasksWeb.clickPostInShareModal();
		tasksWeb.closeTask();
		activityWeb = tasksWeb.gotoActivityModule();

		verifyMicroblogPost();

		activityWeb.gotoDashboard();
		// Verify microblog post in dashboard
		verifyMicroblogPost();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		// Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		// Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		// permissionOfOrg.put(orgType, true);
		// orgData.put(domain, permissionOfOrg);
		//
		// Map<String, List<String>> userMap = new HashMap<>();
		// userMap.put(domain, Arrays.asList(userNames));
		//
		// LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		// siteData.put("name", siteName);
		//
		// LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		// rolesOfSiteUser.put("site admin".toLowerCase().trim(), true);
		//
		// Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();
		//
		// moduleEditPermission.put("View", true);
		// moduleEditPermission.put("Edit", true);
		// Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		// modulePermission.put("Tasks", moduleEditPermission);
		// modulePermission.put("Files", moduleEditPermission);
		// modulePermission.put("People", moduleEditPermission);
		// modulePermission.put("Activity", moduleEditPermission);
		//
		// Map<String, String> userData = new LinkedHashMap<>();
		// userData.put(domain, userNames[0]);
		//
		// LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission = new LinkedHashMap<>();
		// siteGroupModulePermission.put(userGroupName, modulePermission);
		//
		// String[] siteAdminUsers = {userNames[1]+"@"+domain};
		// LinkedHashMap<String, String[]> adminUsers = new LinkedHashMap<>();
		// adminUsers.put("Site admin",siteAdminUsers);
		//
		// configurationData.setOrgData(orgData);
		// configurationData.setUserMap(userMap);
		// configurationData.setNewPassword(newPassword);
		// configurationData.setStatus(UserStatus.Active);
		// configurationData.setStausLocked(false);
		// configurationData.setSiteData(siteData);
		// configurationData.setSiteGroupPermission(true);
		// configurationData.setModuleList("all");
		// configurationData.setAdminGroupUsers(adminUsers);
		// configurationData.setGroupName(userGroupName);
		// configurationData.setMembers(userNames[0],userNames[1]);
		// configurationData.setSiteGroupModulePermission(siteGroupModulePermission);
		//
		//
		// String[] configurationList = {"setOrg","addSystemAdminUsers","createSite","setGroupPermission","enableModules","addsiteuserswithoutroles","addUsersInAdminGroups","createGroup","setModulePermissionOfGroup"};
		// Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList,configurationData));

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();

		// Site Admin -> Enable microblogging

		adminPageWeb = gotoAdminModule();
		adminActivityWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityWeb.enableMicroblogging(true);
		adminActivityWeb.clickOnSave();

		logout();

	}

	private void addTaskPreCondition() throws InterruptedException, IOException
	{
		tasksWeb.gotoTaskModule();
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
		// addTaskData.put(AddTaskLabels.Reminder, reminder); Reminder is not setting due to reminder text format missmatch in 4.2.8
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ATTACHMENT, attachment);

		tasksWeb.setTaskAddData(addTaskData);

		tasksWeb.clickOnAddTaskButtonInModel();
		Assert.assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));

	}

	public void verifyMicroblogPost()
	{
		activityWeb.gotoPosts();
		// Verify site name in activity post
		Assert.assertTrue(activityWeb.verifyGrayMetaSiteName(siteName));

		// Verify authenticity of link by opening it and verifying Task title
		activityWeb.openFirstPostLink();
		Assert.assertTrue(tasksWeb.verifyActiveTask(taskTitle));
		activityWeb.closeCurrentWindow();
	}

}
