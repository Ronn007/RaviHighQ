package com.highq.test.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.ViewUserProfilePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author jhanvi.dave
 */
public class TaskTC2728 extends BannerPageWeb
{
	// Steps remain to automate::: steps 16 to 19 in Assignee and steps 15 to 18 in List due to drag and drop is required R&D
	DashboardPage dashboardPage;
	TasksPage tasksPage;
	AdminPage adminPage;
	AdminTaskPage adminTaskPage;
	BannerPage bannerPage;
	AddUserPage addUserPage;
	DocumentPage docPage;
	ViewUserProfilePage viewProfilePage;

	String jsonFileName = "preConfigurationTC2728.json";
	String sysAdminEmail = "jhanvi.dave@highq.com";
	String sysAdminPassword = "Pa$$worD123";
	String normalPassword = "Pa&&worD123";
	String siteName = "TaskTC2728";
	String dateFormat1 = "dd MMM YYYY";
	String duplicateList = "Temp";
	String startDate1 = getCustomDateValue(-1, dateFormat1);
	String startDate2 = getCustomDateValue(-2, dateFormat1);
	String startDate3 = getCustomDateValue(-3, dateFormat1);
	String startDate4 = getCustomDateValue(2, dateFormat1);
	String[] taskTitle = {"Task1_TC2728", "Task2_TC2728", "Task3_TC2728", "Task4_TC2728", "Task5_TC2728", "Task6_TC2728", "Task7_TC2728"};
	String[] taskName = {"TC2728_1", "TC2728_2", "TC2728_3", "TC2728_4"};
	String[] duplicateListTitle = {"Duplicate1", "Duplicate2"};
	String[] defaultGroup = {"None", "Assignee", "List", "Status", "Priority", "Due date"};
	String[] headerView = {"List view", "Card view", "Timeline view", "Start date", "Due date"};
	String[] userNames = {"normal1.user@task2728.com", "normal2.user@task2728.com", "normal3.user@task2728.com", "General", "normal4.user@task2728.com", "normal5.user@task2728.com"};
	String[] assignee1 = {"normal1.user@task2728.com", "normal2.user@task2728.com"};
	String[] assignee2 = {"normal1.user@task2728.com", "General"};
	String[] users = {"Normal1 User", "Normal2 User", "Normal3 User", "Normal4 User", "Normal5 User", "General [Site group]"};
	String[] lists = {"List-1", "List-2"};
	String[] permission = {"Edit", "View"};
	String[] images = {"img1.jpg", "img2.jpg"};
	String[] priority = {"Normal", "High", "Low"};
	String[] status = {"Not started", "In progress", "Complete", "Overdue"};
	String description = "Task2728 automation";
	String startDate = getDateAndSystemTime(dateFormat1);
	String anyone = "Unassigned";
	int numOfAvatars = 2;
	String secondGroup = "Group 2";
	String generalGroup = "General";
	LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();
	List<String> list2 = new ArrayList<>();
	String groupingAction;
	String element;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void taskTC2728() throws InterruptedException, IOException, JSONException
	{

		preconfiguration();
		scenario01();
		scenario02();
		scenario03();
		scenario04();
		scenario05();

	}

	public void preconfiguration() throws InterruptedException, IOException, JSONException
	{

		Reporter.log("Pre-condition :: ");
		Reporter.log("1. Timeline view should be enable from Site admin - Task");
		Reporter.log("2. Add one group named General and add multiple normal users in this group :  User 1 (With user avatar), User2 (With user avatar), User 3 (Without user avatar)");
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.deleteAllCompletedTasks();
		adminTaskPage.selectDefaultViewFromDropDown(headerView[0]);
		adminTaskPage.setDefaultGrouping(TaskLabels.TASKS_STATUS_NONE);
		adminTaskPage.enableTimelineView(true);
		adminTaskPage.saveTaskChanges();
		tasksPage = gotoTaskModule();
		tasksPage.deleteAllTasks();

		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.enableTimelineView(true);
		adminTaskPage.saveTaskChanges();
		adminTaskPage.removeAllTaskLists();

		adminTaskPage.enterTaskListName(lists[0]);
		adminTaskPage.enterTaskListName(lists[1]);
		adminTaskPage.clickOnPermissions(lists[0]);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(secondGroup, permission[0], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();
		adminTaskPage.clickOnPermissions(lists[1]);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(secondGroup, permission[1], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();
		adminTaskPage.saveTaskChanges();

		adminPage = gotoAdminModule();
		addUserPage = adminPage.clickUsersInLeftPanel();
		addUserPage.uploadUserAvatars(userNames[0], images[0]);
		addUserPage.uploadUserAvatars(userNames[1], images[1]);

	}

	public void scenario01() throws InterruptedException, IOException, JSONException
	{
		Reporter.log("Group by task by Assignee");
		Reporter.log("=====================");
		Reporter.log("Login with Site admin user or higher roles user - Go to task module");
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.setDefaultGrouping(TaskLabels.TASKS_STATUS_NONE);
		adminTaskPage.setDefaultView(headerView[0]);
		adminTaskPage.deleteAllCompletedTasks();
		adminTaskPage.saveTaskChanges();
		tasksPage = gotoTaskModule();

		tasksPage.deleteAllTasks();
		Reporter.log("3. Add some tasks without any assignee");
		Reporter.log("4. Add Task-1 with User 1 as assignee");
		Reporter.log("5. Add Task-2 with user 1 and user 2 as assignees");
		Reporter.log("6. Add Task-3 with the User 3 as assignee ");
		Reporter.log("7. Add Task-4 with User 1 and General Group as assignee");

		addTask();

		Reporter.log("12. Task module - Timeline view - Click on View drop down");

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickListFromGroupMenu();
		tasksPage.clickHeaderViewButton();

		Reporter.log("Expected Result: Two sorting options should be shown for Timeline view : Start date and Due date. Default sort order should be Start date and all the tasks in List view should be shown sorted in Ascending order based on start date");
		Assert.assertTrue(tasksPage.verifyHeaderViewOptionsInTimeline(headerView[3]));
		Assert.assertTrue(tasksPage.verifyHeaderViewOptionsInTimeline(headerView[4]));

		tasksPage.selectHeaderViewOptions(headerView[0]);
		list2.clear();
		list2.add(startDate3);
		list2.add(startDate);
		list2.add(startDate4);
		groupingAction = TaskLabels.TASKS_STARTDATE;
		element = TaskLabels.TASKS_STATUS_NONE;
		Assert.assertTrue(tasksPage.verifyGroupingForAscending(list2, groupingAction, element));

		list2.clear();
		tasksPage.clickOnStartDateDropdown();
		list2.add(startDate);
		list2.add(startDate4);
		list2.add(startDate3);
		groupingAction = TaskLabels.TASKS_STARTDATE;
		element = TaskLabels.TASKS_STATUS_NONE;
		Assert.assertTrue(tasksPage.verifyGroupingForDescending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickHeaderViewButton();
		tasksPage.selectHeaderViewOptions(headerView[4]);
		list2.add(getCustomDateValue(4, dateFormat1));
		list2.add(getCustomDateValue(3, dateFormat1));
		list2.add(getCustomDateValue(1, dateFormat1));
		groupingAction = TaskLabels.TASKS_DUEDATE;
		element = TaskLabels.TASKS_STATUS_NONE;
		Assert.assertTrue(tasksPage.verifyGroupingForDescending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickOnDueDateDropdown();
		list2.add(getCustomDateValue(1, dateFormat1));
		list2.add(getCustomDateValue(3, dateFormat1));
		list2.add(getCustomDateValue(4, dateFormat1));
		groupingAction = TaskLabels.TASKS_DUEDATE;
		element = TaskLabels.TASKS_STATUS_NONE;
		Assert.assertTrue(tasksPage.verifyGroupingForAscending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickHeaderGroupButton();
		Reporter.log("Expected Result: Task timeline view should render with None, Assignee, List, Priority, Status in Group by drop down");
		for (int i = 0; i < defaultGroup.length - 1; i++)
		{
			Assert.assertTrue(tasksPage.verifyHeaderGroupByOptions(defaultGroup[i]));
		}
		tasksPage = gotoTaskModule();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickAssigneeFromGroupMenu();
		list2.add(startDate2);
		list2.add(startDate1);
		list2.add(startDate4);
		groupingAction = TaskLabels.TASKS_STARTDATE;
		element = users[0];
		Assert.assertTrue(tasksPage.verifyGroupingForAscending(list2, groupingAction, element));
		list2.clear();

		list2.clear();
		tasksPage.clickOnStartDateDropdown();
		list2.add(startDate2);
		list2.add(startDate1);
		list2.add(startDate4);
		groupingAction = TaskLabels.TASKS_STARTDATE;
		element = users[0];
		Assert.assertTrue(tasksPage.verifyGroupingForDescending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickHeaderViewButton();
		tasksPage.selectHeaderViewOptions(headerView[4]);
		list2.add(getCustomDateValue(4, dateFormat1));
		list2.add(getCustomDateValue(2, dateFormat1));
		list2.add(getCustomDateValue(1, dateFormat1));
		groupingAction = TaskLabels.TASKS_DUEDATE;
		element = users[0];
		Assert.assertTrue(tasksPage.verifyGroupingForDescending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickOnDueDateDropdown();
		list2.add(getCustomDateValue(4, dateFormat1));
		list2.add(getCustomDateValue(1, dateFormat1));
		list2.add(getCustomDateValue(2, dateFormat1));
		groupingAction = TaskLabels.TASKS_DUEDATE;
		element = users[0];
		Assert.assertTrue(tasksPage.verifyGroupingForAscending(list2, groupingAction, element));
		list2.clear();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		Reporter.log("Expected Result:: ");
		Reporter.log("Anyone should be shown in Assignee column and all the task without assignee should be shown within the time frame(week, 2 week, month)Anyone should be a simple plain text");

		Assert.assertTrue(tasksPage.verifyUnassignedInTimelineView());
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(anyone, taskTitle[4]));

		Reporter.log("2. Select 'Assignee' from Group by drop down");

		Reporter.log("Expected Result: One column should display on left and all the Assignee should display with Name, user avatar and email");

		Assert.assertTrue(tasksPage.verifyGroupByAssigneeInTimelineView(userNames[0]));
		Assert.assertTrue(tasksPage.verifyGroupByAssigneeInTimelineView(userNames[1]));
		Assert.assertTrue(tasksPage.verifyGroupByAssigneeInTimelineView(userNames[2]));
		Assert.assertTrue(tasksPage.verifyGroupByAssigneeGroupNameInTimelineView(users[5]));

		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[1]));
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[2]));
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[3]));

		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[0], taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[1], taskTitle[1]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[2], taskTitle[2]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[5], taskTitle[3]));

		tasksPage = gotoTaskModule();
		tasksPage.deleteAllTasks();

		Reporter.log("8. Add multiple lists in Task module (List 1 and List 2) - Give only View access of List 1 to User 1 and remove view access of List 2 for User 1");
		Reporter.log("9. Add Task with User 1 as assignee in List 1 and add another task with User 2 as assignee in List 2");

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[5]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[4]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[0]);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[6]);
		tasksPage.setTaskAssignees(userNames[5]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[1]);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();
		logout();

		Reporter.log("10. Login with User 1 - Go to task module - Timeline View - Group by Assignee");

		login(userNames[4], normalPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = gotoTaskModule();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickAssigneeFromGroupMenu();

		Assert.assertTrue(tasksPage.verifyGroupByAssigneeInTimelineView(userNames[4]));
		Assert.assertTrue(tasksPage.verifyGroupByAssigneeInTimelineView(userNames[5]));

		Reporter.log("Expected Result: User 1 and User 2 both should be shown in 'Assignee' column but task associated with List 1 should be shown and task associated with List 2 should not be shown");
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[3], taskTitle[5]));
		Assert.assertTrue(!tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(users[4], taskTitle[6]));

		Reporter.log("11. Click on any assignee name");

		tasksPage.openMinicardInTimelineView(userNames[5]);
		viewProfilePage = tasksPage.clickUserInMinicard();

		Reporter.log("Expected Result: Mini card of that user should open with below details");
		Reporter.log("- Name of assignee and link on it which redirect to assignee profile");
		Reporter.log("- Avatar if uploaded");
		Reporter.log("- Send message button");
		Reporter.log(" - Follow button");

		Assert.assertTrue(viewProfilePage.verifyProfilePicture());
		Assert.assertTrue(viewProfilePage.isAvailableSendMessageButton());
		Assert.assertTrue(viewProfilePage.isAvailableFollowButton());

		Reporter.log("14. Drag start point of task visible in User 1 and drop somewhere in the same section");

		Reporter.log("Expected Result: Start date should be updated");
		logout();

	}

	private void addTask() throws InterruptedException, IOException
	{

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[4]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate1);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[0]);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate2);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(2, dateFormat1));
		tasksPage.setTaskTitle(taskTitle[1]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[1]);
		tasksPage.setTaskAssignees(assignee1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[1]);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate3);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(3, dateFormat1));
		tasksPage.setTaskTitle(taskTitle[2]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[2]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userNames[2]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

		tasksPage.clickHeaderAddButton();
		addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate4);
		addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(4, dateFormat1));
		tasksPage.setTaskTitle(taskTitle[3]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[3]);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
		tasksPage.setTaskAssignees(assignee2);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
		tasksPage.setTaskAddData(addTaskData);
		tasksPage.clickOnAddTaskButtonInModel();
		addTaskData.clear();

	}

	public void scenario02() throws InterruptedException, IOException
	{
		Reporter.log("Login with Site admin user or higher roles user - Go to task module");
		login(sysAdminEmail, sysAdminPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = gotoTaskModule();
		tasksPage.deleteAllTasks();

		Reporter.log("7. Give View permission of List 1 to General group and remove all the permission of List 2 for General group");
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.removeAllTaskLists();
		adminTaskPage.enterTaskListName(lists[0]);
		adminTaskPage.enterTaskListName(lists[1]);
		adminTaskPage.clickOnPermissions(lists[0]);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(generalGroup, permission[0], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();

		adminTaskPage.clickOnPermissions(lists[1]);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(generalGroup, permission[1], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();

		tasksPage = gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		Reporter.log("2. Select List' from Group by drop down");

		tasksPage.clickListFromGroupMenu();
		Reporter.log("3. Add some tasks with None list");
		Reporter.log("Expected Result : One column should display on left and all the Lists should display.None list should display by default even if there is no List");
		for (int j = 0; j < 2; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		Assert.assertTrue(tasksPage.verifyNoneListInTimeline());
		Reporter.log("Expected Result: Task added with None task list should display within the time frame(week, 2 week, month)");
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(defaultGroup[0], taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(defaultGroup[0], taskTitle[1]));

		Reporter.log("4. Add some tasks with List 1 and List 2 (Task 1 to 3 in List 1 & Task 4 to 6 in List 2)");

		for (int j = 0; j < 3; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[0]);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int j = 3; j < 6; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[1]);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(TaskLabels.TASKS_STATUS_NONE, taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(TaskLabels.TASKS_STATUS_NONE, taskTitle[1]));

		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[0], taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[0], taskTitle[1]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[0], taskTitle[2]));

		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[1], taskTitle[3]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[1], taskTitle[4]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[1], taskTitle[5]));

		Reporter.log("5. Create 2 lists with the same name like Temp - Add Task 7 and Task 8 in both the task list ");
		for (int j = 0; j < 2; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, duplicateListTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			tasksPage.clickOnAddNewListLink();
			tasksPage.setTaskList(duplicateList);
			tasksPage.clickAddListSubmittButtonEnable();
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}

		Reporter.log("6. Timeline view - Group by List");
		Reporter.log("Expected Result: Both the Task list with the same name should display and Task 7 - Task 8 should display for its respective task list within the time frame(week, 2 week, month)");

		Assert.assertTrue(tasksPage.verifiDuplicateListInTimeline(duplicateList));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(duplicateList, duplicateListTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(duplicateList, duplicateListTitle[1]));

		logout();
		Reporter.log("8. Login with any user from General group (Ex - User 1) - Go to task module - Timeline view - Group by List");
		login(userNames[0], normalPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickListFromGroupMenu();

		Reporter.log("Expected Result: List 1 should display in List column and all the task related to list 1 should display within the time frame(week, 2 week, month). But List 2 should not display in List column");
		Assert.assertTrue(!tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[1], taskTitle[0]));
		logout();

		login(sysAdminEmail, sysAdminPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.removeAllTaskLists();
		Reporter.log("7. Give View permission of List 1 to General group and remove all the permission of List 2 for General group");
		adminTaskPage.enterTaskListName(lists[0]);
		adminTaskPage.enterTaskListName(lists[1]);
		adminTaskPage.clickOnPermissions(lists[0]);

		Reporter.log("9. Remove all the permission of all task list for General group ");
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(generalGroup, permission[1], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();

		adminTaskPage.clickOnPermissions(lists[1]);
		adminTaskPage.clickOnRestricted();
		adminTaskPage.setTaskListPermission(generalGroup, permission[1], false);
		adminTaskPage.clickOnSaveButtonInSetPermissionsOfList();

		tasksPage = gotoTaskModule();
		for (int j = 0; j < 2; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, duplicateListTitle[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, lists[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}

		logout();

		Reporter.log("10. Login with User 1 - Go to Task module - Timeline view - Group by List");

		login(userNames[0], normalPassword);
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.clickHeaderGroupButton();
		tasksPage.clickListFromGroupMenu();

		Reporter.log("Expected Result: Only None list should display in List column and all the task added in None list should display within the time frame(week, 2 week, month). All other task list should not display ");
		Assert.assertTrue(!tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[0], duplicateListTitle[0]));

		Assert.assertTrue(!tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(lists[1], duplicateListTitle[1]));

		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(TaskLabels.TASKS_STATUS_NONE, taskTitle[0]));
		Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(TaskLabels.TASKS_STATUS_NONE, taskTitle[1]));
		logout();

		Reporter.log("11. Task module - Timeline view - Group by List - Click on View drop down ");
	}

	private void scenario03() throws InterruptedException, IOException
	{

		Reporter.log("1. Do login with Site admin or any higher role user");
		login(sysAdminEmail, sysAdminPassword);
		Reporter.log("2. Go to Site admin - Tasks");
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		Reporter.log("3. Select Timeline view from Default View drop down");
		adminTaskPage.enableTimelineView(true);
		adminTaskPage.selectDefaultViewFromDropDown(headerView[2]);

		Reporter.log("Expected Result: In Default grouping drop down following options should be shown : None, Assignee, List, Status and Priority");

		for (int i = 0; i < defaultGroup.length; i++)
		{
			Assert.assertTrue(adminTaskPage.verifyDefaultGrouping(defaultGroup[i]));
		}

		adminTaskPage.setDefaultGrouping(TaskLabels.TASKS_STATUS_NONE);
		adminTaskPage.saveTaskChanges();

		Reporter.log("4. Save the configuration by setting None as default grouping option - Go to Task module - Timeline view ");
		tasksPage = gotoTaskModule();

		Reporter.log("Expected Result: Timeline view should be displayed with none grouping. All the task should be shown within date parameters(week, 2week, month) None should be display selected in group drop down in task module");

		Assert.assertTrue(tasksPage.verifyTimelineViewRenderInTaskModule());
		Reporter.log("Timeline view should be display selected in view drop down in task module");

		Reporter.log("5. Site admin task - Select Assignee as default grouping - Save and go to Task module - Timeline view ");
		adminPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.setDefaultGrouping(defaultGroup[1]);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: Timeline view should be displayed with assignee grouping. All the task should be shown within the date parameters(week, 2week, month) for the assignee.");
		tasksPage = gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyDefaultGroupingInTimeline(defaultGroup[1]));

		Reporter.log("6. Site admin task - Select List as default grouping - Save and go to Task module - Timeline view ");
		adminPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.setDefaultGrouping(defaultGroup[2]);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: Task module should be shown based on List grouping. All the task should be shown within the date parameters for the List");
		tasksPage = gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyDefaultGroupingInTimeline(defaultGroup[2]));

		Reporter.log("7. Site admin task - Select Status as default grouping - Save and go to Task module - Timeline view ");
		adminPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.setDefaultGrouping(defaultGroup[3]);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: Task module should be shown based on Status grouping. All the task should be shown within the date parameters for the status");
		tasksPage = gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyDefaultGroupingInTimeline(defaultGroup[3]));

		Reporter.log("8. Site admin task - Select Priority as default grouping - Save and go to Task module - Timeline view ");
		adminPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.setDefaultGrouping(defaultGroup[4]);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: Task module should be shown based on Priority grouping. All the task should be shown within the date parameters for the priority");
		tasksPage = gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyDefaultGroupingInTimeline(defaultGroup[4]));

		Reporter.log("9. Site admin task - Set Due date as default grouping - Set Timeline view as default view - Go to Task module");
		adminPage.gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.setDefaultGrouping(defaultGroup[5]);
		adminTaskPage.saveTaskChanges();

		Reporter.log("Expected Result: When Task module gets render, Timeline view should render with Assignee grouping as there will be no grouping with Due date in Timeline view and None is default grouping for Timeline view");
		tasksPage = gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyDefaultGroupingInTimeline(defaultGroup[1]));
	}

	private void scenario04() throws InterruptedException, IOException
	{

		Reporter.log("Group by task by Priority");
		Reporter.log("=====================");
		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		adminTaskPage.selectDefaultViewFromDropDown(headerView[0]);
		adminTaskPage.setDefaultGrouping(TaskLabels.TASKS_STATUS_NONE);
		adminTaskPage.saveTaskChanges();
		tasksPage = gotoTaskModule();
		tasksPage.deleteAllTasks();

		Reporter.log("Pre-condition : ");
		Reporter.log("=========================== ");
		Reporter.log("1. Timeline view should be enable from Site admin - Task");

		Reporter.log("Steps============== ");
		Reporter.log("1. Login with Site admin user or higher roles user - Go to task module");
		Reporter.log("Expected Result: Task timeline view should render with None, Assignee, List, Priority, Status in Group by drop down");

		tasksPage.clickHeaderViewButton();
		tasksPage.selectHeaderViewOptions(headerView[2]);
		tasksPage.clickHeaderGroupButton();
		for (int i = 0; i < defaultGroup.length - 1; i++)
		{
			Assert.assertTrue(tasksPage.verifyHeaderGroupByOptions(defaultGroup[i]));
		}

		Reporter.log("2. Add below test data:");
		Reporter.log("A. Add 2 tasks (along with start date and due date) with High Priority");
		Reporter.log("B. Add 2 tasks (along with start date and due date) with Normal priority");
		Reporter.log("C. Add 3 tasks (along with start date and due date) with Low priority");

		for (int j = 0; j < 2; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[1]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status[0]);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int j = 2; j < 4; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[0]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status[1]);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int j = 4; j < 6; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[j]);
			tasksPage.setTaskAssignees(userNames[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priority[2]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_DESCRIPTION, description);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status[2]);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}

		Reporter.log("3. Select Priority from Group by dropdown");
		tasksPage.clickHeaderGroupButton();
		tasksPage.selectHeaderGroupByOptions(defaultGroup[4]);

		Reporter.log("Expected Result==============");
		Reporter.log("One column should display on left and all the 3 priorities (High, Normal and Low) should display.");

		for (int i = 0; i < priority.length; i++)
		{
			Assert.assertTrue(tasksPage.verifyPriorityAndStatusInTimeline(priority[i]));
		}

		Reporter.log("A. Tasks with High priority should be shown in High priority section within the time frame(week, 2 week, month)");
		for (int j = 0; j < 2; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(priority[1], taskTitle[j]));
		}

		Reporter.log("B. Tasks with Normal priority should be shown in High priority section within the time frame(week, 2 week, month)");
		for (int j = 2; j < 4; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(priority[0], taskTitle[j]));
		}

		Reporter.log("C. Tasks with Low priority should be shown in High priority section within the time frame(week, 2 week, month)");
		for (int j = 4; j < 6; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(priority[2], taskTitle[j]));
		}

	}

	private void scenario05() throws InterruptedException, IOException
	{

		Reporter.log("Group by task by Status");
		Reporter.log("====================");
		Reporter.log("Pre-condition : ");
		Reporter.log("1. Timeline view should be enable from Site admin - Task");
		Reporter.log("2. From site admin Tasks - 1 Custom status (Name: Temp) should be added ");

		adminPage = gotoAdminModule();
		adminTaskPage = adminPage.clickOnTaskInLeftPanel();
		if (!adminTaskPage.verifyCustomStatus(duplicateList))
		{
			adminTaskPage.addStatus(duplicateList, 1);
		}

		String completedStatusColor = adminTaskPage.getParticularStatusColor(TaskLabels.TASKS_STATUS_COMPLETED);
		String inProgressStatusColor = adminTaskPage.getParticularStatusColor(TaskLabels.TASKS_STATUS_INPROGRESS);
		String notStartedStatusColor = adminTaskPage.getParticularStatusColor(TaskLabels.TASKS_STATUS_NOTSTARTED);
		String tempStatusColor = adminTaskPage.getParticularStatusColor(duplicateList);

		Reporter.log("D. Add 2 tasks with Temp Status");
		Reporter.log("E. Add 2 tasks with Overdue dates and status can be anything (Ex: Not Started)");
		Reporter.log("====================");
		tasksPage = gotoTaskModule();
		for (int j = 0; j < 2; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskName[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, duplicateList);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, startDate);
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();
		}
		for (int j = 2; j < 4; j++)
		{
			tasksPage.clickHeaderAddButton();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskName[j]);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status[1]);
			addTaskData.put(TaskLabels.TASKS_STARTDATE, getCustomDateValue(-2, dateFormat1));
			addTaskData.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(-1, dateFormat1));
			tasksPage.setTaskAddData(addTaskData);
			tasksPage.clickOnAddTaskButtonInModel();
			addTaskData.clear();

		}
		Reporter.log("3. Select Status from Group by dropdown");
		tasksPage.clickHeaderGroupButton();
		tasksPage.selectHeaderGroupByOptions(defaultGroup[3]);
		tasksPage.clickHeaderViewButton();
		tasksPage.selectHeaderViewOptions(headerView[2]);

		Reporter.log("Expected Result : One column should display on left and all the Status names should display.");
		for (int i = 0; i < status.length - 1; i++)
		{
			Assert.assertTrue(tasksPage.verifyPriorityAndStatusInTimeline(status[i]));
		}

		Reporter.log("A. Tasks with Not Started status should be shown in Not Started section with its color code and also within the time frame(week, 2 week, month)");
		Reporter.log("D. Tasks with Temp Status should be shown in Temp section with its color code and also within the time frame(week, 2 week, month)");
		for (int j = 0; j < 2; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskTitle[j], notStartedStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(status[0], taskTitle[j]));
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskName[j], tempStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(duplicateList, taskName[j]));
		}

		Reporter.log("B. Tasks with In Progress status should be shown in In Progress section with its color code and also within the time frame(week, 2 week, month)");
		Reporter.log("E. One new status section named Overdue should be shown and Tasks with Overdue dates should be shown in that section with the color of the Not Started status and also withing the time frame (Week, 2 week, month)");
		for (int j = 2; j < 4; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskTitle[j], inProgressStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskName[j], inProgressStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(status[1], taskTitle[j]));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(status[1], taskName[j]));
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskName[j], inProgressStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskName[j], inProgressStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(status[3], taskName[j]));
		}

		Reporter.log("C. Tasks with Complete Status should be shown in Complete section with its color code");
		Reporter.log(" And with strike out on the task and also within the time frame(week, 2 week, month)");
		for (int j = 4; j < 6; j++)
		{
			Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskTitle[j], completedStatusColor));
			Assert.assertTrue(tasksPage.verifyTaskInParticularGroupByOptionInTimelineView(status[2], taskTitle[j]));
			Assert.assertTrue(tasksPage.verifyCompletedTaskWithStrikeOffInTimeline(taskTitle[j]));

		}

	}
}
