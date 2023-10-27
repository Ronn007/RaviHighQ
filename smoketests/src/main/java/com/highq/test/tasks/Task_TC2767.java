package com.highq.test.tasks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminEventPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

public class Task_TC2767 extends BannerPageWeb
{
	private WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	String jsonFileName = "preConfiguration_Task_TC2767.json";

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

	BannerPage bannerPage;
	DashboardPage dashboardPage;
	TasksPage tasksPage;
	AdminPage adminPage;
	AdminTaskPage adminTaskPage;
	AdminEventPage adminEventPage;
	EventsPage eventsPage;

	String dateFormat1 = "dd MMM yyyy";
	List<String> startDateList = new ArrayList<String>();
	List<String> dueDateList = new ArrayList<String>();
	List<String> taskTitleList = new ArrayList<String>();
	LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();
	String taskTitle[] = {"Task_1", "Task_2", "Task_3", "Task_4", "Task_5", "Task_6", "Task_7", "Task_8", "Task_9", "Task_10", "Task_11", "Task_12", "Task_13"};

	@Test(priority = 1)
	public void TaskTC2767() throws InterruptedException, IOException, JSONException, ParseException
	{
		preconfiguration();
		scenario1();
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

	private void scenario1() throws InterruptedException, IOException
	{
		Reporter.log("Pre-condition :");
		Reporter.log("1. Timeline view should be enable from Site admin - Tasks");
		Reporter.log("2. 'Enable Task category' should be enable from Site admin - Event");

		Reporter.log("1. Login with any user who has Edit access of Task module and at least view access of Event module");
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		enableDisableTimelineViewFormSiteAdmin(true);
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();

		Reporter.log("2. Go to Task module - Timeline view");
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		Reporter.log("3. Create multiple task with below test data");
		startDateList.add(0, getCustomDateValue(-7, dateFormat1));
		dueDateList.add(0, getCustomDateValue(0, dateFormat1));

		startDateList.add(1, getCustomDateValue(0, dateFormat1));
		dueDateList.add(1, getCustomDateValue(7, dateFormat1));

		startDateList.add(2, getCustomDateValue(-7, dateFormat1));
		dueDateList.add(2, getCustomDateValue(-7, dateFormat1));

		startDateList.add(3, getCustomDateValue(0, dateFormat1));
		dueDateList.add(3, getCustomDateValue(0, dateFormat1));

		startDateList.add(4, getCustomDateValue(7, dateFormat1));
		dueDateList.add(4, getCustomDateValue(7, dateFormat1));

		startDateList.add(5, getCustomDateValue(-1, dateFormat1));
		dueDateList.add(5, getCustomDateValue(0, dateFormat1));

		startDateList.add(6, getCustomDateValue(0, dateFormat1));
		dueDateList.add(6, getCustomDateValue(1, dateFormat1));

		startDateList.add(7, getCustomDateValue(0, dateFormat1));
		dueDateList.add(7, getCustomDateValue(32, dateFormat1));

		startDateList.add(8, getCustomDateValue(-32, dateFormat1));
		dueDateList.add(8, getCustomDateValue(0, dateFormat1));

		startDateList.add(9, getCustomDateValue(-2, dateFormat1));
		dueDateList.add(9, getCustomDateValue(-1, dateFormat1));

		startDateList.add(10, getCustomDateValue(-1, dateFormat1));
		dueDateList.add(10, getCustomDateValue(-1, dateFormat1));

		startDateList.add(11, getCustomDateValue(1, dateFormat1));
		dueDateList.add(11, getCustomDateValue(2, dateFormat1));

		startDateList.add(12, getCustomDateValue(1, dateFormat1));
		dueDateList.add(12, getCustomDateValue(1, dateFormat1));

		taskTitleList.add(taskTitle[0]);
		taskTitleList.add(taskTitle[1]);
		taskTitleList.add(taskTitle[2]);
		taskTitleList.add(taskTitle[3]);
		taskTitleList.add(taskTitle[4]);
		taskTitleList.add(taskTitle[5]);
		taskTitleList.add(taskTitle[6]);
		taskTitleList.add(taskTitle[7]);
		taskTitleList.add(taskTitle[8]);
		taskTitleList.add(taskTitle[9]);
		taskTitleList.add(taskTitle[10]);
		taskTitleList.add(taskTitle[11]);
		taskTitleList.add(taskTitle[12]);

		for (int i = 0; i < taskTitleList.size(); i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitleList.get(i));
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(i));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDateList.get(i));
			if (i <= 4)
			{
				addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_NOTSTARTED);
			}
			if (i > 4)
			{
				addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);
			}
			if (i > 8)
			{
				addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_COMPLETED);
			}

			tasksPage.setTaskAddData(addTaskData);

			addTaskData.clear();
			tasksPage.clickOnAddInAddTaskModal();
		}

		Reporter.log("4. Go to Event module - Click on Task category from left panel");
		eventsPage = tasksPage.gotoEventModule();

		Reporter.log("Expected Result: ");
		Reporter.log("1. By default List view should render and following tasks should be shown");

		Reporter.log("A. Task whose start date is from last week and Due date is from current week");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[0]));
		eventsPage.clickOnEventInEventList(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Reporter.log("B. Task whose start date is from current week and Due date is from next week ");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[1]));
		eventsPage.clickOnEventInEventList(taskTitle[1]);
		commonMethodToVerifyTaskDetailsForAssertTrue(1);

		Reporter.log("C. Task whose start date and Due date from current week");
		Reporter.log("F. Task whose start date and due date both are today's date ");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[3]));
		eventsPage.clickOnEventInEventList(taskTitle[3]);
		commonMethodToVerifyTaskDetailsForAssertTrue(3);

		Reporter.log("D. Task whose start date is today's date and Due date is tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[6]));
		eventsPage.clickOnEventInEventList(taskTitle[6]);
		commonMethodToVerifyTaskDetailsForAssertTrue(6);

		Reporter.log("E. Task whose start date is yesterday's date and Due date is today's date");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[5]));
		eventsPage.clickOnEventInEventList(taskTitle[5]);
		commonMethodToVerifyTaskDetailsForAssertTrue(5);

		Reporter.log("2. Click on Day button from the top and following tasks should be shown");
		eventsPage.clickOnDayButton();

		Reporter.log("Expected Result: By default Today should be shown and follwing task should be shown :-");
		Reporter.log("B. Task whose start date is yesterday's date and Due date is today's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[5]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[5]);
		commonMethodToVerifyTaskDetailsForAssertTrue(5);

		Reporter.log("C. Task whose start date and due date both are today's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[3]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[3]);
		commonMethodToVerifyTaskDetailsForAssertTrue(3);

		Reporter.log("D. Task whose start date is today's date and Due date is tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[6]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[6]);
		commonMethodToVerifyTaskDetailsForAssertTrue(6);

		Reporter.log("E. Task whose start date is from last week and Due date is from current week");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Reporter.log("3. Navigate to previous date by clicking on previous arrow of date navigator and following tasks should be shown");
		eventsPage.clickOnDayPreviousButtonInRightPanel();

		Reporter.log("A. Task whose start date is yesterday's date and Due date is any date after yesterday");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[5]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[5]);
		commonMethodToVerifyTaskDetailsForAssertTrue(5);

		Reporter.log("B. Task whose start date is one day before yesterday and Due date is yesterday's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[9]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[9]);
		commonMethodToVerifyTaskDetailsForAssertTrue(9);

		Reporter.log("C. Task whose start date and due date both are yesterday's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[10]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[10]);
		commonMethodToVerifyTaskDetailsForAssertTrue(10);

		Reporter.log("D. Task whose start date is from last week and Due date is from current week");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Reporter.log("4. Click on 'Today' - Navigate to next day by clicking on Next arrow of date navigator and follwing tasks should be shown");
		eventsPage.clickOnTodayButtonInRightPanel();
		eventsPage.clickOnDayNextButtonInRightPanel();

		Reporter.log("A. Task whose start date is Tomorrow's date and Due date is any date after tomorrow");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[11]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[11]);
		commonMethodToVerifyTaskDetailsForAssertTrue(11);

		Reporter.log("B. Task whose start date is one day before tomorrow and Due date is tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[6]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[6]);
		commonMethodToVerifyTaskDetailsForAssertTrue(6);

		Reporter.log("C. Task whose start date and due date both are tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[12]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[12]);
		commonMethodToVerifyTaskDetailsForAssertTrue(12);

		// Reporter.log("D. Task whose start date is from last week and Due date is from current week");
		// Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));

		Reporter.log("5. Click on Week button from the top and following tasks should be shown");
		eventsPage.clickOnTodayButtonInRightPanel();
		eventsPage.clickOnWeekButton();

		Reporter.log("A. Task whose start date is from current week and due date is from next week");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[1]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[1]);
		commonMethodToVerifyTaskDetailsForAssertTrue(1);

		Reporter.log("B. Task whose start date and due date both are from current week");
		Reporter.log("F. Task whose start date and due date both are today's date ");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[3]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[3]);
		commonMethodToVerifyTaskDetailsForAssertTrue(3);

		Reporter.log("C. Task whose start date is from previous week and due date is from current week");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Reporter.log("D. Task whose start date is today's date and due date is tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[6]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[6]);
		commonMethodToVerifyTaskDetailsForAssertTrue(6);

		Reporter.log("E. Task whose start date is yesterday's date and due date is today's date");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[5]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[5]);
		commonMethodToVerifyTaskDetailsForAssertTrue(5);

		Reporter.log("Note: Click on Previous and Next arrow icons to navigate previous and next weeks and tasks should be shown accordingly");
		eventsPage.clickOnDayPreviousButtonInRightPanel();

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[2]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[2]);
		commonMethodToVerifyTaskDetailsForAssertTrue(2);

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[8]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[8]);
		commonMethodToVerifyTaskDetailsForAssertTrue(8);

		eventsPage.clickOnTodayButtonInRightPanel();
		eventsPage.clickOnDayNextButtonInRightPanel();

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[1]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[1]);
		commonMethodToVerifyTaskDetailsForAssertTrue(1);

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[4]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[4]);
		commonMethodToVerifyTaskDetailsForAssertTrue(4);

		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[7]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[7]);
		commonMethodToVerifyTaskDetailsForAssertTrue(7);

		Reporter.log("6. Click on Month button from the top and following tasks should be shown");
		eventsPage.clickOnTodayButtonInRightPanel();
		eventsPage.clickOnMonthButton();

		Reporter.log("A. Task whose start date is from current month and due date is from next month");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[7]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[7]);
		commonMethodToVerifyTaskDetailsForAssertTrue(7);

		Reporter.log("B. Task whose start date and due date both are from current month");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[0]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertTrue(0);

		Reporter.log("C. Task whose start date is from previous month and due date is from current month");
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[8]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[8]);
		commonMethodToVerifyTaskDetailsForAssertTrue(8);

		Reporter.log("Note: Click on Previous and Next arrow icons to navigate previous and next months and tasks should be shown accordingly");

		eventsPage.clickOnDayPreviousButtonInRightPanel();
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[8]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[8]);
		commonMethodToVerifyTaskDetailsForAssertTrue(8);

		eventsPage.clickOnTodayButtonInRightPanel();
		eventsPage.clickOnDayNextButtonInRightPanel();
		Assert.assertTrue(eventsPage.verifyEventIn_Day_Week_Month_View(taskTitle[7]));
		eventsPage.clickOnEventIn_Day_Week_Month_View(taskTitle[7]);
		commonMethodToVerifyTaskDetailsForAssertTrue(7);

		Reporter.log("7. Go to site admin - Task - uncheck 'Enable Timeline View' option");
		adminPage = eventsPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.enableTimelineView(false);
		adminTaskPage.saveTaskChanges();

		Reporter.log("8. Go to Event module ");
		eventsPage = adminTaskPage.gotoEventModule();
		Reporter.log("Expected Result: Tasks should be shown in Event module but start date should not be shown, only Due date should be shown");
		Reporter.log("A. Task whose start date is from last week and Due date is from current week");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[0]));
		eventsPage.clickOnEventInEventList(taskTitle[0]);
		commonMethodToVerifyTaskDetailsForAssertFalse(0);

		Reporter.log("B. Task whose start date is from current week and Due date is from next week ");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[1]));
		eventsPage.clickOnEventInEventList(taskTitle[1]);
		commonMethodToVerifyTaskDetailsForAssertFalse(1);

		Reporter.log("C. Task whose start date and Due date from current week");
		Reporter.log("F. Task whose start date and due date both are today's date  ");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[3]));
		eventsPage.clickOnEventInEventList(taskTitle[3]);
		commonMethodToVerifyTaskDetailsForAssertFalse(3);

		Reporter.log("D. Task whose start date is today's date and Due date is tomorrow's date");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[6]));
		eventsPage.clickOnEventInEventList(taskTitle[6]);
		commonMethodToVerifyTaskDetailsForAssertFalse(6);

		Reporter.log("E. Task whose start date is yesterday's date and Due date is today's date");
		Assert.assertTrue(eventsPage.verifyEventInEventList(taskTitle[5]));
		eventsPage.clickOnEventInEventList(taskTitle[5]);
		commonMethodToVerifyTaskDetailsForAssertFalse(5);
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

		adminEventPage = adminPage.clickEventsInLeftPanel();
		adminEventPage.enableEnableTasksCategory();
		adminEventPage.clickOnSave();
		tasksPage = adminEventPage.gotoTaskModule();
	}

	private void commonMethodToVerifyTaskDetailsForAssertTrue(int i)
	{
		Assert.assertTrue(tasksPage.verifyTaskStartDate(startDateList.get(i)));
		Assert.assertTrue(tasksPage.verifyTaskDueDate(dueDateList.get(i)));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
	}

	private void commonMethodToVerifyTaskDetailsForAssertFalse(int i)
	{
		Assert.assertFalse(tasksPage.verifyEditIConOfStartDateInViewTaskModal());
		Assert.assertTrue(tasksPage.verifyTaskDueDate(dueDateList.get(i)));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
	}
}
