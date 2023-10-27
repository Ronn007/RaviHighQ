package com.highq.test.tasks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author jyoti.raj
 */
public class Task_TC2641 extends BannerPageWeb
{
	DocumentPage documentPage;
	TasksPage tasksPage;
	AdminPage adminPage;
	ModulesPage modulesPage;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardPage;
	LoginPage loginPagePage;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPage;
	AdminTaskPage adminTaskPage;

	String siteAdminUse = "site.admin@task2641.com";
	String taskListA = "TaskList_A";
	String taskListB = "TaskList_B";
	String dateFormat1 = "dd MMM yyyy";
	String dateFormat2 = "d MMM yyyy";
	String dateFormat3 = "E d";
	String listView = "List view";
	String anyone = "Unassigned";
	String none = "None";
	String[] assigneeGrps = {"group001 [Site group]", "group002 [Site group]"};
	String status = TaskLabels.TASKS_STATUS_COMPLETED;
	String startDate = getCustomDateValue(0, dateFormat1);
	String dueDate = getCustomDateValue(1, dateFormat1);
	String[] taskTitle = {"Task-1", "Task-2", "Task-3", "Task-4", "Task-5", "Task-6", "Task-7", "Task-8", "Task-9", "Task-10"};
	String[] taskTitle1 = {"Task-11", "Task-12", "Task-13", "Task-14", "Task-15", "Task-16", "Task-17", "Task-18", "Task-19", "Task-20"};
	String[] taskTitle2 = {"Task-21", "Task-22", "Task-23", "Task-24", "Task-25"};
	String priority = "Normal";
	String list = "List";

	List<String> startDateList = new ArrayList<String>();
	List<String> dueDateList = new ArrayList<String>();
	List<String> taskTitleList = new ArrayList<String>();
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat1);
	LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	String jsonFileName = "preConfiguration_Task_TC2641.json";

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
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();
	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();
	String siteAdmin = "site.admin@task2641.com";
	String normalUser = "normal.user@task2641.com";
	String group1 = "group001";
	String group2 = "group002";
	String task1 = "Task_1";
	String task2 = "Task_2";
	String task3 = "Task_3";
	String task4 = "Task_4";
	String task5 = "Task_5";
	String task6 = "Task_6";
	String specialCharTask = "<([{\\^-=$!|]})?*+.><script>alert(1)</script>";

	@Test(priority = 1)
	public void taskTC2641() throws InterruptedException, IOException, JSONException, ParseException
	{
		preconfiguration();
		scenario01();
		scenario02();
	}

	private void scenario01() throws InterruptedException, IOException, ParseException
	{
		addTaskAndTaskList();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();
		enableTimelineViewFromAdmin();
	}

	private void addTaskAndTaskList() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.removeAllTaskLists();
		tasksPage = adminTaskPage.gotoTaskModule();
		tasksPage.deleteAllTasks();

		startDateList.add(getCustomDateValue(-7, dateFormat1));
		startDateList.add(getCustomDateValue(0, dateFormat1));
		startDateList.add(getCustomDateValue(-7, dateFormat1));
		startDateList.add(getCustomDateValue(0, dateFormat1));
		startDateList.add(getCustomDateValue(7, dateFormat1));
		startDateList.add(getCustomDateValue(0, dateFormat1));

		dueDateList.add(getCustomDateValue(0, dateFormat1));
		dueDateList.add(getCustomDateValue(7, dateFormat1));
		dueDateList.add(getCustomDateValue(-7, dateFormat1));
		dueDateList.add(getCustomDateValue(0, dateFormat1));
		dueDateList.add(getCustomDateValue(7, dateFormat1));
		dueDateList.add(getCustomDateValue(0, dateFormat1));

		taskTitleList.add(task1);
		taskTitleList.add(task2);
		taskTitleList.add(task3);
		taskTitleList.add(task4);
		taskTitleList.add(task5);
		taskTitleList.add(specialCharTask);

		for (int i = 0; i < taskTitleList.size(); i++)
		{
			tasksPage.clickHeaderAddButton();
			if (i == 0)
			{
				tasksPage.clickOnAddNewListLink();
				tasksPage.setTaskList(taskListA);
				tasksPage.clickAddListSubmittButtonEnable();

				Assert.assertTrue(tasksPage.verifyAddNewListLink());
				Assert.assertTrue(tasksPage.verifyTaskListOption(taskListA));

				tasksPage.clickOnAddNewListLink();
				tasksPage.setTaskList(taskListB);
				tasksPage.clickAddListSubmittButtonEnable();

				Assert.assertTrue(tasksPage.verifyAddNewListLink());
				Assert.assertTrue(tasksPage.verifyTaskListOption(taskListB));
			}

			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitleList.get(i));
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(i));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDateList.get(i));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group1);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListA);
			addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_NOTSTARTED);
			if (i > 2)
			{
				addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListB);
				addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);
				addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group2);
			}
			tasksPage.setTaskAddData(addTaskData);

			addTaskData.clear();
			tasksPage.clickOnAddInAddTaskModal();
		}

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		tasksPage.clickTimelineViewTodayButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(-7, 0, task1);
		Assert.assertTrue(tasksPage.verifyToolTipOfTaskInTimelineView(task1));

		tasksPage.clickTimelineViewTodayButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(-7, -7, task3);
		Assert.assertTrue(tasksPage.verifyToolTipOfTaskInTimelineView(task3));

		tasksPage.clickTimelineViewTodayButton();
		Assert.assertTrue(tasksPage.verifyTimelineViewRenderInTaskModule());
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(-7, 0, task1);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 7, task2);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, task4);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, specialCharTask);

		tasksPage.clickTimelineViewTodayButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(0, 7, task2);

		tasksPage.clickTimelineViewTodayButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(7, 7, task5);
		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickTimelineViewTwoWeekViewButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_TWOWEEKS));

		dateCompareAndVerify(-7, 0, task1);
		dateCompare(getCustomDateValue(-7, dateFormat2));
		Assert.assertTrue(tasksPage.verifyTaskInParticularView(getCustomDateValue(-7, dateFormat2), getCustomDateValue(0, dateFormat2), task1));

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(7, 7, task5);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 7, task2);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, task4);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, specialCharTask);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickTimelineViewMonthViewButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_MONTH));

		dateCompareAndVerify(-7, 0, task1);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(7, 7, task5);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(-7, -7, task3);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 7, task2);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, task4);

		tasksPage.clickTimelineViewTodayButton();
		dateCompareAndVerify(0, 0, specialCharTask);

		tasksPage.clickTimelineViewWeekViewButton();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		tasksPage.clickTimelineViewNextButton();
		tasksPage.clickTimelineViewTodayButton();

		String todayDate = getDateAndSystemTime(dateFormat3);
		Assert.assertTrue(tasksPage.verifyTaskTimelineViewTodayDate(todayDate));
		logout();

		bannerPage = login(siteAdminUse, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = dashboardPage.gotoTaskModule();

		tasksPage.clickHeaderAddButton();
		LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, task6);
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(3));
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDateList.get(3));
		addTaskData.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddInAddTaskModal();

		tasksPage.selectTask(task6);
		tasksPage.addTaskToFavourites();
		tasksPage.closeTask();
		tasksPage.clearSearch();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAllTasksTab();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(-7, 0, task1);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAllTasksTab();
		dateCompareAndVerify(0, 7, task2);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAllTasksTab();
		dateCompareAndVerify(0, 0, task4);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAllTasksTab();
		dateCompareAndVerify(0, 0, task6);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAllTasksTab();
		dateCompareAndVerify(0, 0, specialCharTask);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAssignToMeTabInTasks();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(-7, 0, task1);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickAssignToMeTabInTasks();
		dateCompareAndVerify(0, 7, task2);
		dateCompare(getCustomDateValue(0, dateFormat2));
		Assert.assertTrue(tasksPage.verifyTaskInParticularView(getCustomDateValue(0, dateFormat2), getCustomDateValue(7, dateFormat2), task2));

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickOnCreatedByMeTab();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(0, 0, task6);

		tasksPage.clickTimelineViewTodayButton();
		tasksPage.clickFavouritesTabInTask();
		Assert.assertTrue(tasksPage.verifyDefaultViewInTimelineView(TaskLabels.TASKS_TIMELINE_VIEW_LABEL_WEEK));
		dateCompareAndVerify(0, 0, task6);

		adminPage = tasksPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.clickOnPermissions(taskListA);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(group2, TaskLabels.TASKS_PREMISSION_VIEW, false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();
		logout();

		bannerPage = login(normalUser, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		tasksPage = dashboardPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickTimelineViewTodayButton();
		dateCompare(getCustomDateValue(-7, dateFormat2));
		if (tasksPage.verifyTaskVisibilityInTimelineView(task1))
		{
			Assert.assertFalse(tasksPage.verifyTaskInParticularView(getCustomDateValue(-7, dateFormat2), getCustomDateValue(0, dateFormat2), task1));
		}
		tasksPage.clickTimelineViewTodayButton();
		dateCompare(getCustomDateValue(0, dateFormat2));
		if (tasksPage.verifyTaskVisibilityInTimelineView(task2))
		{
			Assert.assertFalse(tasksPage.verifyTaskInParticularView(getCustomDateValue(0, dateFormat2), getCustomDateValue(7, dateFormat2), task2));
		}
		tasksPage.clickTimelineViewTodayButton();
		dateCompare(getCustomDateValue(-7, dateFormat2));
		if (tasksPage.verifyTaskVisibilityInTimelineView(task3))
		{
			Assert.assertFalse(tasksPage.verifyTaskInParticularView(getCustomDateValue(-7, dateFormat2), getCustomDateValue(-7, dateFormat2), task3));
		}
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPage = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

	private void enableTimelineViewFromAdmin() throws IOException, InterruptedException
	{
		login(sysAdminEmail, defaultPassword);
		dashboardPage = gotoDashboard();

		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(true);
		adminTaskPage.removeAllTaskLists();
		adminTaskPage.saveTaskChanges();
		logout();
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

	private void dateCompareAndVerify(int sp, int ep, String taskTitle) throws ParseException, IOException
	{
		dateCompare(getCustomDateValue(sp, dateFormat2));
		Assert.assertTrue(tasksPage.verifyTaskInParticularView(getCustomDateValue(sp, dateFormat2), getCustomDateValue(ep, dateFormat2), taskTitle));
	}

	private void scenario02() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.setDefaultGrouping(TaskLabels.TASKS_STATUS_NONE);
		adminTaskPage.setDefaultView(listView);
		adminTaskPage.deleteAllCompletedTasks();
		adminTaskPage.saveTaskChanges();
		tasksPage = gotoTaskModule();
		tasksPage.deleteAllTasks();

		startDateList.clear();
		dueDateList.clear();
		startDateList.add(getCustomDateValue(-7, dateFormat1));
		dueDateList.add(getCustomDateValue(7, dateFormat1));

		for (int i = 0; i < 8; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDate);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group1);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListA);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int i = 8; i < 10; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group1);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListA);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}

		for (int i = 0; i < 8; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDate);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle1[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group2);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListB);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int i = 8; i < 10; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle1[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, group2);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskListB);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int i = 0; i < 3; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDate);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle2[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int i = 3; i < 5; i++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_DUEDATE, dueDateList.get(0));
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle2[i]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority);
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickAssigneeFromGroupMenu();

		verifyCompletedTasks();

		for (int i = 0; i < taskTitle2.length; i++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(anyone, taskTitle2[i]));
		}
		tasksPage.clickHeaderGroupButton();
		tasksPage.selectHeaderGroupByOptions(list);
		for (int i = 0; i < taskTitle2.length; i++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(none, taskTitle2[i]));
		}
	}

	private void verifyCompletedTasks() throws IOException, InterruptedException, ParseException
	{
		for (int i = 0; i < taskTitle.length; i++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(assigneeGrps[0], taskTitle[i]));
		}
		for (int i = 0; i < taskTitle1.length; i++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(assigneeGrps[1], taskTitle1[i]));
		}

		dateCompareAndVerify(-7, 7, taskTitle[8]);
		dateCompareAndVerify(-7, 7, taskTitle1[8]);
		dateCompareAndVerify(-7, 7, taskTitle2[3]);

	}
}
