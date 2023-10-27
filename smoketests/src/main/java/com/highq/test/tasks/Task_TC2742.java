package com.highq.test.tasks;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAuditsSiteManagementPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSiteSummaryPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author jyoti.raj
 */
public class Task_TC2742 extends BannerPageWeb
{
	private WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	String jsonFileName = "preConfiguration_Task_TC2742.json";

	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser
					.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();
	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	DashboardPage dashboardPage;
	AdminPage adminPage;
	AdminTaskPage adminTaskPage;
	AdminAuditsSiteManagementPage adminAuditsSiteManagementPage;
	AdminSiteSummaryPage adminSiteSummaryPage;
	TasksPage tasksPage;
	BannerPage bannerPage;
	AspAdminPage aspAdminPage;
	AspConfigurationPage aspConfigurationPage;
	LoginPage loginPage;
	BannerPage bannerPageSimultaneous;

	String dateFormat1 = "dd MMM YYYY";
	String dateFormat2 = "d MMM yyyy";
	String taskTitle1 = "Task_1_TC2742";
	String taskTitle2 = "Task_2_TC2742";
	String taskTitle3 = "Task_3_TC2742";
	String taskTitle4 = "Task_4_TC2742";
	String mailSubject;
	String expression = "0 0/1 * * * ?";
	Timestamp startTime;
	Timestamp endTime;
	String tasklistA = "TaskListA";
	String tasklistB = "TaskListB";
	String group1 = "group001";
	String group2 = "group002";
	String normalUser = "normal.user@task2742.com";
	String mailTo;
	String mailSub = " Tasks due soon";
	String dueOn = "Due on ";
	String oneDaysbeforeStartdate = "1 day(s) before Start date";
	String oneDaysbeforeDuedate = "1 day(s) before Due date";
	String fourDaysbeforeDuedate = "4 day(s) before Due date";
	String twoDaysbeforeDuedate = "2 day(s) before Due date";
	String twoDaysbeforeStartdate = "2 day(s) before Start date";
	String oneDaysBefore = "1 day(s) before";
	String fourDaysBefore = "4 day(s) before";

	@Test(priority = 1)
	public void TaskTC2742() throws InterruptedException, IOException, JSONException, ParseException, UnsupportedFlavorException
	{
		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
		scenario10();
		scenario11();
		scenario12();
		scenario13();
		scenario14();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

	/**
	 * Reminder while Add task in Timeline View:
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		Reporter.log("1. Login with any user who has edit access of Task module");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		Reporter.log("2. Go to task module - Timeline view");
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("3. Click on Add button ");
		tasksPage.clickHeaderAddButton();

		Reporter.log("Expected Result: Add task modal should be open and 'Add' button for Reminder should be shown disable");
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		Assert.assertFalse(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());

		Reporter.log("4. Select only Start date (Ex: 26/04/2018) - enter other required details");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));

		Reporter.log(" 'Add' button should get enable and by deafault 1 day before Start date reminder should be added with remove icon. Reminder mail should get trigger 1 day before the start date");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("5. Select only Due date (Ex: 30/04/2018)- Enter other required deatils");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle2);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 'Add' button should get enable and by deafault 1 day before Due date reminder should be added with remove icon. Reminder mail should get trigger 1 day before the Due date");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle2);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("6. In Add task modal - First select start date and then Due date");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle3);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 1 day before Start date reminder should be added with remove icon and after that user can add more reminders with the help of Add button");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle3);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("7. In Add task modal - First select Due date and then Start date");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle4);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 1 day before Due date reminder should be added with remove icon and after that user can add more reminders with the help of Add button");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle4);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Reminder while Add task in Timeline View: for MY TASK
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario9() throws InterruptedException, IOException
	{
		Reporter.log("Note: Check in My Task as well after selecting such site from site dropdown in which 'Timeline View' is enable");
		Reporter.log("1. Login with any user who has edit access of Task module");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		Reporter.log("2. Go to task module - Timeline view");
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("3. Click on Add button ");
		tasksPage.clickHeaderAddButton();

		Reporter.log("Expected Result: Add task modal should be open and 'Add' button for Reminder should be shown disable");
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		Assert.assertFalse(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());

		Reporter.log("4. Select only Start date (Ex: 26/04/2018) - enter other required details");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));

		Reporter.log(" 'Add' button should get enable and by deafault 1 day before Start date reminder should be added with remove icon. Reminder mail should get trigger 1 day before the start date");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("5. Select only Due date (Ex: 30/04/2018)- Enter other required deatils");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle2);
		tasksPage.setSite(siteName);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 'Add' button should get enable and by deafault 1 day before Due date reminder should be added with remove icon. Reminder mail should get trigger 1 day before the Due date");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle2);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("6. In Add task modal - First select start date and then Due date");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle3);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 1 day before Start date reminder should be added with remove icon and after that user can add more reminders with the help of Add button");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle3);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("7. In Add task modal - First select Due date and then Start date");
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle4);
		tasksPage.setSite(siteName);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));

		Reporter.log("Expected Result: 1 day before Due date reminder should be added with remove icon and after that user can add more reminders with the help of Add button");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Assert.assertTrue(tasksPage.verifyMailSubject(mailSubject + mailSub));
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, "", taskTitle4);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Add multiple reminders while Add task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario2() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("2. Click on Add button to add multiple reminders");
		Reporter.log("Expected Result: New text box should be display with the dropdown from which user can select either Due date or Start date");
		Assert.assertTrue(tasksPage.verifyReminderDropdownInAddTaskModal(TaskLabels.TASKS_STARTDATE));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();

		Reporter.log("3. Enter any numeric value upto 3 digits (like 3) in text box - Select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable ");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Reporter.log("Expected Result: 3 Day(s) before Due date reminder should be added ");
		Assert.assertTrue(tasksPage.verifyReminderTitle(fourDaysbeforeDuedate));

		Reporter.log("5. Create task with added reminders");
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("Expected Result: Reminder mail should get trigger as below :");
		Reporter.log("1. 1 Day(s) before the Start date : Mail should trigger 1 day before of start means on 26/04/2018");
		Reporter.log("2. 3 Day(s) before the Due date : Mail should trigger on 3 days before Due date means on 27/04/2018");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		closeCurrentTab();
		logout();
	}

	/**
	 * Add multiple reminders while Add task : for MY TASK
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario10() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("2. Click on Add button to add multiple reminders");
		Reporter.log("Expected Result: New text box should be display with the dropdown from which user can select either Due date or Start date");
		Assert.assertTrue(tasksPage.verifyReminderDropdownInAddTaskModal(TaskLabels.TASKS_STARTDATE));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();

		Reporter.log("3. Enter any numeric value upto 3 digits (like 3) in text box - Select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable ");
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));
		Reporter.log("Expected Result: 3 Day(s) before Due date reminder should be added ");
		Assert.assertTrue(tasksPage.verifyReminderTitle(fourDaysbeforeDuedate));

		Reporter.log("5. Create task with added reminders");
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("Expected Result: Reminder mail should get trigger as below :");
		Reporter.log("1. 1 Day(s) before the Start date : Mail should trigger 1 day before of start means on 26/04/2018");
		Reporter.log("2. 3 Day(s) before the Due date : Mail should trigger on 3 days before Due date means on 27/04/2018");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Duplicate reminder:
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario3() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable ");
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));

		Reporter.log("2. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Due date ");
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Reporter.log("Expected Result: User should be able to add Same reminder for Start and Due date");
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));

		Reporter.log("3. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Due date again");
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminder(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();

		Reporter.log("4. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Start date again");
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		Reporter.log("Expected Result: 'Reminder already exist' validation message should be shown ");
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminder(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();
		tasksPage.clickOnCloseButtonInAddTaskModal();
		logout();
	}

	/**
	 * Duplicate reminder: For My Task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario11() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable ");
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeStartdate));

		Reporter.log("2. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Due date ");
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Reporter.log("Expected Result: User should be able to add Same reminder for Start and Due date");
		Assert.assertTrue(tasksPage.verifyReminderTitle(oneDaysbeforeDuedate));

		Reporter.log("3. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Due date again");
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminder(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();

		Reporter.log("4. Click on Add button to add another reminder - Add 1 Day(s) before reminder for Start date again");
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		Reporter.log("Expected Result: 'Reminder already exist' validation message should be shown ");
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminder(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInAddTaskModal();
		tasksPage.clickOnCloseButtonInAddTaskModal();
		logout();
	}

	/**
	 * Delete Reminder:
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario4() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("2. Add reminder for both Start date and Due date");
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Reporter.log("Expected Result: Reminder should be added with remove icon");
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("3. Click on 'Remove' icon of Start date reminder");
		tasksPage.clickOnTaskInTimelineView(taskTitle1);
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(oneDaysbeforeStartdate);
		Reporter.log("Expected Result: Reminder for start date should get removed and reminder mail for start date should not get triggered");
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeStartdate));

		Reporter.log("4. Click on 'Remove' icon of Due date reminder");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(oneDaysbeforeDuedate);
		Reporter.log("Expected Result: Reminder for Due date should get removed and reminder mail for start date should not get triggered");
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeDuedate));
		Thread.sleep(60000);
		endTime = getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		logout();
	}

	/**
	 * Delete Reminder: For My Task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void scenario12() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));

		Reporter.log("2. Add reminder for both Start date and Due date");
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);
		Reporter.log("Expected Result: Reminder should be added with remove icon");
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyRemoveButtonVisibilityForReminder(fourDaysbeforeDuedate));
		tasksPage.clickOnAddInAddTaskModal();

		Thread.sleep(60000);
		endTime = getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("3. Click on 'Remove' icon of Start date reminder");
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.selectTask(taskTitle1);
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(oneDaysbeforeStartdate);
		Reporter.log("Expected Result: Reminder for start date should get removed and reminder mail for start date should not get triggered");
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeStartdate));

		Reporter.log("4. Click on 'Remove' icon of Due date reminder");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(fourDaysbeforeDuedate);

		Reporter.log("Expected Result: Reminder for Due date should get removed and reminder mail for Due date should not get triggered");
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(fourDaysbeforeDuedate));
		tasksPage.clickOnCloseInViewTask();

		Thread.sleep(60000);
		endTime = getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Some more scenarios for mail trigger :
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario5() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_COMPLETED);

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable");
		tasksPage.verifyReminderTitle(oneDaysbeforeStartdate);
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		tasksPage.clickOnAddInAddTaskModal();
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();

		Reporter.log("2. Complete that task before the reminder. (Ex: Start date is set to 27/04/2018 and reminder is set to 1 Day(s) before means mail should trigger on 26/04/2018. So complete task on 25/04/2018)");
		Reporter.log("Expected Result: Mail should not get trigger on 26/04/2018 as task gets completed before the reminder");
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, taskTitle1));
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Some more scenarios for mail trigger : For My Task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario13() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("1. Open Add Task modal - Select Start date (Ex: 27/04/2018) and then Due date (Ex: 30/4/2018)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_COMPLETED);

		Reporter.log("Expected Result: 1 Day(s) before Start date reminder should be added and Add button should get enable");
		tasksPage.verifyReminderTitle(oneDaysbeforeStartdate);
		Assert.assertTrue(tasksPage.verifyEnableAddButtonVisibilityForReminderInAddTaskModal());
		tasksPage.clickOnAddInAddTaskModal();
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();

		Reporter.log("2. Complete that task before the reminder. (Ex: Start date is set to 27/04/2018 and reminder is set to 1 Day(s) before means mail should trigger on 26/04/2018. So complete task on 25/04/2018)");
		Reporter.log("Expected Result: Mail should not get trigger on 26/04/2018 as task gets completed before the reminder");
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, taskTitle1));
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Reminder while Edit Task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void scenario6() throws InterruptedException, IOException, ParseException
	{
		Reporter.log("1. Do login with user who has edit access of Task module");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);

		Reporter.log("2. Go to Task module - Timeline View ");
		tasksPage = adminTaskPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("3. Add task with Start date (27/04/2018) and Due date (30/04/2018) - Also add reminders for both Start and Due date (1 Day(s) before start date and 1 Day(s) before Due date)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("4. Open task detail modal of that task ");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle1);
		Reporter.log("Expected Result: Observe that reminder for both Start and Due dates are shown with remove icon next to reminder and also '+' icon to add another reminders");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(fourDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyAddButtonForReminderInViewTaskModal());

		Reporter.log("5. Click on Remove icon of start date reminder");
		Reporter.log("6. Click on Remove icon of Due date reminder");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(oneDaysbeforeStartdate);
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(fourDaysbeforeDuedate);
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Reporter.log("Expected Result: Start date reminder should get removed and reminder mail shuold not get trigger as reminder is removed");
		Reporter.log("Expected Result: Due date reminder should get removed and reminder mail shuold not get trigger as reminder is removed");
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("7. Click on '+' icon to add multiple reminders");
		Reporter.log("Expected Result: Text box along with dropdown should open (Start date and Due date should be shown in dropdown)");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle1);
		Assert.assertTrue(tasksPage.verifyReminderDropdownInViewTaskModal(TaskLabels.TASKS_STARTDATE));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();
		Assert.assertTrue(tasksPage.verifyReminderDropdownInViewTaskModal(TaskLabels.TASKS_DUEDATE));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();

		Reporter.log("8. Enter 2 in the text box and select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_DUEDATE);
		Reporter.log("Expected Result : 2 Day(s) before Due date reminder should be added");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(twoDaysbeforeDuedate));

		Reporter.log("9. Enter 2 in the text box and select Start date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_STARTDATE);
		Reporter.log("Expected Result : 2 Day(s) before Start date reminder should be added. ");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(twoDaysbeforeStartdate));

		Reporter.log("10. Enter 2 in the text box and select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_DUEDATE);
		Reporter.log("Expected Result : Reminder is already exists message should be shown as reminder for Due date is already added for 2 days\r\n" +
				"Note: Same case with Start date. ");
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminderInViewTaskModal(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_STARTDATE);
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminderInViewTaskModal(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Reporter.log("11. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				" Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder ");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle2);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(0, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("12. Open task detail modal - Open Start date picker and select No Start date - Close task detail modal and open it again");
		dateCompare(getCustomDateValue(0, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle2);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoStartDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		dateCompare(getCustomDateValue(0, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle2);
		Assert.assertTrue(tasksPage.verifyTaskStartDate(getCustomDateValue(1, dateFormat1)));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Reporter.log("Expected Result: Start date should be shown same as Due date (30/04/2018) and now reminder mail for start date should get triggerd based on new start date (30/04/2018)");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle2);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("13. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				" Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder ");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle3);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(0, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("14. Open task detail modal - Open Due date picker and select No Due date - Close task detail modal and open it again");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle3);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoDueDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle3);
		Assert.assertTrue(tasksPage.verifyTaskDueDate(getCustomDateValue(1, dateFormat1)));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Reporter.log("Expected Result: Due date should be shown same as Start date (28/04/2018) and now reminder mail for start date should get triggerd based on new start date (28/04/2018");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle3);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("15. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				"    Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle4);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("16. Open task detail modal - Open Due date picker and select No Due date - Open Start date picker and select No Start date ");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle4);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoStartDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnNoDueDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Reporter.log("Expected Result: Reminder for both start date and due date should removed and no reminder mail should get trigger");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle4);
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Reminder while Edit Task : For My Task
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void scenario14() throws InterruptedException, IOException, ParseException
	{
		Reporter.log("1. Do login with user who has edit access of Task module");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);

		Reporter.log("2. Go to Task module - Timeline View ");
		tasksPage = adminTaskPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.deleteAllTasks();
		tasksPage.clickOnPersonalTab();

		Reporter.log("3. Add task with Start date (27/04/2018) and Due date (30/04/2018) - Also add reminders for both Start and Due date (1 Day(s) before start date and 1 Day(s) before Due date)");
		tasksPage.clickHeaderAddButton();
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("4. Open task detail modal of that task ");
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.selectTask(taskTitle1);
		Reporter.log("Expected Result: Observe that reminder for both Start and Due dates are shown with remove icon next to reminder and also '+' icon to add another reminders");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(fourDaysbeforeDuedate));
		Assert.assertTrue(tasksPage.verifyAddButtonForReminderInViewTaskModal());

		Reporter.log("5. Click on Remove icon of start date reminder");
		Reporter.log("6. Click on Remove icon of Due date reminder");
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(oneDaysbeforeStartdate);
		tasksPage.clickOnRemoveButtonForParticularReminderInViewTaskModal(fourDaysbeforeDuedate);
		tasksPage.closeTask();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		Reporter.log("Expected Result: Start date reminder should get removed and reminder mail shuold not get trigger as reminder is removed");
		Reporter.log("Expected Result: Due date reminder should get removed and reminder mail shuold not get trigger as reminder is removed");
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("7. Click on '+' icon to add multiple reminders");
		Reporter.log("Expected Result: Text box along with dropdown should open (Start date and Due date should be shown in dropdown)");
		tasksPage.selectTask(taskTitle1);
		Assert.assertTrue(tasksPage.verifyReminderDropdownInViewTaskModal(TaskLabels.TASKS_STARTDATE));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();
		Assert.assertTrue(tasksPage.verifyReminderDropdownInViewTaskModal(TaskLabels.TASKS_DUEDATE));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();

		Reporter.log("8. Enter 2 in the text box and select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_DUEDATE);
		Reporter.log("Expected Result : 2 Day(s) before Due date reminder should be added");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(twoDaysbeforeDuedate));

		Reporter.log("9. Enter 2 in the text box and select Start date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_STARTDATE);
		Reporter.log("Expected Result : 2 Day(s) before Start date reminder should be added. ");
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(twoDaysbeforeStartdate));

		Reporter.log("10. Enter 2 in the text box and select Due date from the dropdown - Click on tick mark icon");
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_DUEDATE);
		Reporter.log("Expected Result : Reminder is already exists message should be shown as reminder for Due date is already added for 2 days\r\n" +
				"Note: Same case with Start date. ");
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminderInViewTaskModal(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));
		tasksPage.clickOnCloseButtonForReminderInViewTaskModal();
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_STARTDATE);
		Assert.assertTrue(tasksPage.verifyErrorMessageForDuplicateReminderInViewTaskModal(TaskLabels.TASKS_REMINDER_ALREADY_EXIST));

		Reporter.log("11. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				" Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder ");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle2);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(0, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("12. Open task detail modal - Open Start date picker and select No Start date - Close task detail modal and open it again");

		tasksPage.clickOnCreatedByMeTab();
		tasksPage.clearSearch();
		tasksPage.selectTask(taskTitle2);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoStartDateFromDatePickerInViewTaskModal();
		tasksPage.closeTask();

		tasksPage.selectTask(taskTitle2);
		Assert.assertTrue(tasksPage.verifyTaskStartDate(getCustomDateValue(1, dateFormat1)));
		tasksPage.closeTask();
		Reporter.log("Expected Result: Start date should be shown same as Due date (30/04/2018) and now reminder mail for start date should get triggerd based on new start date (30/04/2018)");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle2);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("13. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				" Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder ");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle3);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("14. Open task detail modal - Open Due date picker and select No Due date - Close task detail modal and open it again");
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.clearSearch();
		tasksPage.selectTask(taskTitle3);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoDueDateFromDatePickerInViewTaskModal();
		tasksPage.closeTask();

		tasksPage.selectTask(taskTitle3);
		Assert.assertTrue(tasksPage.verifyTaskDueDate(getCustomDateValue(1, dateFormat1)));
		tasksPage.closeTask();
		Reporter.log("Expected Result: Due date should be shown same as Start date (28/04/2018) and now reminder mail for start date should get triggerd based on new start date (28/04/2018");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle3);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(1, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("15. Add task with following start date and due date :- Start date : 28/04/2018 and Due date : 30/04/2018\r\n" +
				"    Add 1 Day(s) before Start date and 1 Day(s) before Due date reminder");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle4);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("16. Open task detail modal - Open Due date picker and select No Due date - Open Start date picker and select No Start date ");
		tasksPage.clickOnCreatedByMeTab();
		tasksPage.clearSearch();
		tasksPage.selectTask(taskTitle4);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.clickOnNoStartDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnNoDueDateFromDatePickerInViewTaskModal();
		tasksPage.closeTask();

		Reporter.log("Expected Result: Reminder for both start date and due date should removed and no reminder mail should get trigger");
		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle4);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertFalse(tasksPage.verifyContent(mailTo, taskTitle4));
		tasksPage.closeCurrentTab();
		logout();
	}

	/**
	 * Permission based Scenarios
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void scenario7() throws IOException, InterruptedException, ParseException
	{
		Reporter.log("Pre-condition: Add normal user (With edit access of Task module) and some task list ");
		Reporter.log("1. Login with admin user - Go to Site admin - Tasks");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		adminTaskPage.removeAllTaskLists();
		adminTaskPage.enterTaskListName(tasklistA);
		adminTaskPage.enterTaskListName(tasklistB);

		tasksPage = adminTaskPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setList(tasklistA);
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle2);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setList(tasklistB);
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(1, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.clickOnAddInAddTaskModal();

		Reporter.log("3. Give Edit permission of List 1 and View Permission of List 2 to Normal user");
		adminPage = tasksPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.clickOnPermissions(tasklistB);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(group2, TaskLabels.TASKS_PERMISSION_EDIT, false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();
		logout();

		Reporter.log("4. Login with Normal user - Go to Task module");
		login(normalUser, defaultPassword);
		dashboardPage = gotoDashboardByClickOnLogo();
		dashboardPage.searchSite(siteName);
		tasksPage = dashboardPage.gotoTaskModule();

		Reporter.log("5. Open task detail modal of the task which belongs to List 1");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle1);

		Reporter.log("Expected Result: User should be able to change start date and Due date as well as should be able to add reminder ");
		tasksPage.editTaskDueDate(getCustomDateValue(2, dateFormat1));
		tasksPage.editTaskStartDate(getCustomDateValue(2, dateFormat1));
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_ADDTASK_DUEDATE);
		tasksPage.setReminderInViewTask(2, TaskLabels.TASKS_STARTDATE);
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Reporter.log("6. Open task detail modal of the task which belongs to List 2");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle2);

		Reporter.log("Expected Result: User should not be able to change start date and Due date and also should not be able to add reminder");
		Assert.assertFalse(tasksPage.verifyEditIConOfStartDateInViewTaskModal());
		Assert.assertFalse(tasksPage.verifyEditIconOfDueDateInViewTaskModal());
		Assert.assertFalse(tasksPage.verifyAddButtonForReminderInViewTaskModal());
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		logout();

		Reporter.log("7. Login with admin user - Go to Site admin - Tasks");
		login(sysAdminEmail, defaultPassword);
		dashboardPage = gotoDashboardByClickOnLogo();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		AdminUserGroupsPage adminUserGroupsPage = adminPage.clickGroupsInLeftPanel();

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(TaskLabels.TASKS_EDIT, false);
		Map<String, Map<String, Boolean>> map2 = new HashMap<String, Map<String, Boolean>>();
		map2.put("Tasks", map);
		LinkedHashMap<String, Map<String, Map<String, Boolean>>> map3 = new LinkedHashMap<String, Map<String, Map<String, Boolean>>>();
		map3.put(group2, map2);
		adminUserGroupsPage.setGroupModulePermission(map3);
		logout();

		Reporter.log("9. Login with Normal user - Go to Task module ");
		login(normalUser, defaultPassword);
		dashboardPage = gotoDashboardByClickOnLogo();
		dashboardPage.searchSite(siteName);
		tasksPage = dashboardPage.gotoTaskModule();

		Reporter.log("10. Open task detail modal of any task - Try to add reminder");
		dateCompare(getCustomDateValue(1, dateFormat2));
		tasksPage.clickOnTaskInTimelineView(taskTitle2);

		Reporter.log("Expected Result: Normal user should not able to change start date and due date and also should not be able to add reminder");
		Assert.assertFalse(tasksPage.verifyEditIConOfStartDateInViewTaskModal());
		Assert.assertFalse(tasksPage.verifyEditIconOfDueDateInViewTaskModal());
		Assert.assertFalse(tasksPage.verifyAddButtonForReminderInViewTaskModal());
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		logout();
	}

	/**
	 * Simultaneous Case for Reminder:
	 * COL-68104 - BUG
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnsupportedFlavorException
	 */
	public void scenario8() throws InterruptedException, IOException, ParseException, UnsupportedFlavorException
	{
		Reporter.log("Pre-condition : Timeline view should be enable from Site admin - Tasks");
		Reporter.log("1. Do login with Site admin user ");
		bannerPage = login(sysAdminEmail, defaultPassword);
		mailSubject = getMailSubjectValueFromAspAdmin();
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage = adminTaskPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("2. Open task module in one tab and open Site admin - Tasks in another tab");
		Reporter.log("3. Open Add task modal - Add start date (28/014/2018) and Due date (30/04/2018) details along with 1 day(s) reminder for both");
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle1);
		tasksPage.setTaskAssignees(sysAdminEmail);
		tasksPage.setTaskStartDate(getCustomDateValue(1, dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(4, dateFormat1));
		tasksPage.setReminder(1, TaskLabels.TASKS_STARTDATE);
		tasksPage.setReminder(4, TaskLabels.TASKS_ADDTASK_DUEDATE);

		Reporter.log("4. Before clicking on Add button from Add task modal, disable Timeline view from another tab ");
		String newURL = driver.getCurrentUrl();
		tasksPage.switchToNextTab(newURL);
		adminPage = tasksPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(false);
		adminTaskPage.saveTaskChanges();

		Reporter.log("5. Click on Add button");
		startTime = tasksPage.getStartDateTimeStamp();
		adminTaskPage.switchToPreviosTab();
		tasksPage.clickOnAddInAddTaskModal();
		Reporter.log("Expected Result: Task should be added without any error page");
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		Assert.assertTrue(tasksPage.verifyTaskVisibility(taskTitle1));

		Reporter.log("6. Open task detail modal of added task");
		tasksPage.selectTask(taskTitle1);
		Reporter.log("Expected Result: Observe that only Due date should be shown (30/04/2018) and only due date reminder should be shown and mail should be sent for due date reminder");
		Assert.assertTrue(tasksPage.verifyViewTaskDetailsDueDate(getCustomDateValue(4, dateFormat1)));
		Assert.assertFalse(tasksPage.verifyEditIConOfStartDateInViewTaskModal());
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysBefore));
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(fourDaysBefore));

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle1);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();

		Reporter.log("7. Enable Timeline view again - Open task detail modal of that task - Edit anything in that task - Close - Open task detail modal again");
		tasksPage.switchToNextTab(newURL);
		adminPage = tasksPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(true);
		adminTaskPage.setDefaultView(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: Now both Start date (30/04/2018 - Same as Due date) and Due date (30/04/2018) should be shown and only Due date reminder should be shown. And mail should be sent due date only");
		tasksPage.switchToPreviosTab();
		tasksPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.selectTask(taskTitle1);
		startTime = tasksPage.getStartDateTimeStamp();
		tasksPage.editTaskTitle(taskTitle2);
		tasksPage.clickOnCloseInViewTask();
		tasksPage.clearSearch();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		dateCompare(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnTaskInTimelineView(taskTitle2);
		Assert.assertTrue(tasksPage.verifyTaskStartDate(getCustomDateValue(4, dateFormat1)));
		Assert.assertTrue(tasksPage.verifyViewTaskDetailsDueDate(getCustomDateValue(4, dateFormat1)));
		Assert.assertFalse(tasksPage.verifyReminderTitleInViewTaskModal(oneDaysbeforeStartdate));
		Assert.assertTrue(tasksPage.verifyReminderTitleInViewTaskModal(fourDaysbeforeDuedate));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Thread.sleep(60000);
		endTime = tasksPage.getEndDateTimeStamp();
		tasksPage.getMailForTask(sysAdminEmail, startTime, endTime, mailSubject + mailSub, taskTitle2);
		mailTo = getUserData(sysAdminEmail);
		Assert.assertTrue(tasksPage.verifyContent(mailTo, dueOn + getCustomDateValue(4, dateFormat1)));
		tasksPage.closeCurrentTab();
		logout();
	}

	private void enableDisableTimelineViewFormSiteAdmin(boolean flag)
	{
		Reporter.log("Pre-condition: Timeline view should be enable from Site admin - Tasks");
		dashboardPage = gotoDashboardByClickOnLogo();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(flag);
		if (flag == true)
			adminTaskPage.selectDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();
	}

	private String getMailSubjectValueFromAspAdmin() throws InterruptedException
	{
		dashboardPage = gotoDashboard();
		aspAdminPage = dashboardPage.gotoASPAdmin();
		aspConfigurationPage = aspAdminPage.openConfigurationPage();
		String mailSubject = aspConfigurationPage.getValueOfEmailSubjectPrefix();
		aspConfigurationPage.setTaskBeforeOneDayTriggerCronExpression(expression);
		aspConfigurationPage.saveConfigurations();
		dashboardPage = gotoDashboardByClickOnLogo();
		return mailSubject;
	}

	private void dateCompare(String date) throws ParseException
	{
		String dates[] = tasksPage.getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat2);
		Date findDate = sdf.parse(date);
		Date weekstartDate = sdf.parse(dates[0].trim());
		Date weekEndDate = sdf.parse(dates[1].trim());

		if (tasksPage.isDateAvailableInBetweenGivenDates(weekstartDate, weekEndDate, findDate))
		{
			System.out.println("Task is in current week");
		}
		else if (findDate.after(weekEndDate))
		{
			do
			{
				tasksPage.clickTimelineViewNextButton();
				String dates2[] = tasksPage.getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
				weekstartDate = sdf.parse(dates2[0].trim());
				weekEndDate = sdf.parse(dates2[1].trim());

			}
			while (findDate.after(weekEndDate));
		}
		else
		{
			do
			{
				tasksPage.clickTimelineViewPreviousButton();
				String dates3[] = tasksPage.getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
				weekstartDate = sdf.parse(dates3[0].trim());
				weekEndDate = sdf.parse(dates3[1].trim());
			}
			while (findDate.before(weekstartDate));
		}
	}

}
