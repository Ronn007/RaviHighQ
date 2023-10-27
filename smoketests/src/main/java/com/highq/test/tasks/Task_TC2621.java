package com.highq.test.tasks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.base.CollaborateLabel.AddTaskLabels;
import com.highq.base.CollaborateLabel.DocFolderOperation;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAuditsSiteManagementPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSiteSummaryPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.TasksWeb;
import com.highq.parsers.JSONParser;

/**
 * @author jyoti.raj
 */
public class Task_TC2621 extends BannerPageWeb
{

	private WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	String jsonFileName = "preConfiguration_Task_TC2621.json";

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

	String siteName = "TaskTC2621";

	DashboardPage dashboardPage;
	AdminPage adminPage;
	AdminTaskPage adminTaskPage;
	AdminAuditsSiteManagementPage adminAuditsSiteManagementPage;
	AdminSiteSummaryPage adminSiteSummaryPage;
	TasksPage tasksPage;
	DocumentPage documentPage;
	DocumentAddDataPage addDoc;
	AdminFilesPage adminFilesPage;
	BannerPage bannerPage;

	String dateFormat1 = "dd MMM YYYY";
	String dateFormat2 = "E dd";
	String dateFormat3 = "MMM/d/yyyy";
	String dateFormat4 = "dd MMM yyyy";
	String dateFormat5 = "d MMM yyyy";
	String currentDate = getDateAndSystemTime(dateFormat1);
	String todayDate = getDateAndSystemTime(dateFormat5);
	String dateoftoday = getDateAndSystemTime(dateFormat3);
	String dueDateofTask = getCustomDateValue(1, dateFormat5);
	String[] taskTitle = {"TC2621Task_1", "TC2621Task_2", "TC2621Task_3", "TC2621Task_4", "TC2621Task_5", "TC2621Task_6", "TC2621Task_7", "TC2621Task_8", "TC2621Task_9"};
	String startDate = null;
	String dueDate = null;
	String[] fileName = {"testDoc.txt", "doc1.txt", "doc2.txt", "doc3.txt", "automation.jpg"};
	String newVersionTitle = "doc3";
	String newVersionNote = "New Version Added";
	String[] addMenuItems = {"Files", "New folder"};
	String myTaskTitle1 = "My Task Title1";
	String myTaskTitle2 = "My Task Title2";
	String myTaskTitle3 = "My Task Title3";
	String testDate = "Test";
	List<String> list = new ArrayList<String>();
	Map<String, String> taskDetails = new HashMap<>();

	@Test(priority = 1)
	public void TaskTC2621() throws InterruptedException, IOException, JSONException, ParseException
	{
		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
	}

	private void preconfiguration() throws IOException, JSONException, InterruptedException
	{
		siteAndUserConfiguration();
		enableTimelineViewAndSetAsDefault();
	}

	private void siteAndUserConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

	private void enableTimelineViewAndSetAsDefault()
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();

		adminTaskPage.enableTimelineView(true);
		adminTaskPage.selectDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();

		adminFilesPage = adminPage.clickFilesInLeftPanel();
		adminFilesPage.selectFileOptionCheckbox(AdminLabels.SITE_ADMIN_FILES_METADATATODISPLAY_ENABLEDOCREVIEWWORKFLOW, true);
		adminFilesPage.saveFilesChanges();
		logout();
	}

	private void scenario1() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		documentPage = dashboardPage.gotoFileModule();
		documentPage.deleteAllDocs();
		tasksPage = documentPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		tasksPage.clickHeaderAddButton();

		list.clear();
		list.add(AddTaskLabels.Title.toString());
		list.add(AddTaskLabels.Description.toString());
		list.add(TaskLabels.TASKS_STARTDATE);
		list.add(AddTaskLabels.Due_date.toString());
		list.add(AddTaskLabels.Assignee.toString());
		list.add(AddTaskLabels.Priority.toString());
		list.add(AddTaskLabels.List.toString());
		list.add(AddTaskLabels.Status.toString());
		list.add(AddTaskLabels.Tags.toString());
		list.add(AddTaskLabels.Reminder.toString());

		Assert.assertTrue(tasksPage.verifyTaskDetailsOrderInAddTaskModal(list));
		Assert.assertTrue(tasksPage.verifyDisableAddButtonVisibilityInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyCancleButtonVisibilityInAddTaskModal());
		Assert.assertTrue(tasksPage.verifyXButtonVisibilityInAddTaskModal());

		Assert.assertTrue(tasksPage.verifyCKEditorInDiscription());
		Assert.assertTrue(tasksPage.verifyDefaultValueOfPriorityInAddTaskModal(TaskLabels.TASKS_PRIORITY_NORMAL));
		Assert.assertTrue(tasksPage.verifyDefaultValueOfListInAddTaskModal(TaskLabels.TASKS_STATUS_NONE));
		Assert.assertTrue(tasksPage.verifyDefaultValueOfStatusInAddTaskModal(TaskLabels.TASKS_STATUS_NOTSTARTED));

		tasksPage.clickOnCloseButtonInAddTaskModal();
		Assert.assertFalse(tasksPage.verifyAddTaskModalVisibility());

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskStartDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidStartDateMessage(TaskLabels.TASKS_INVALID_STARTDATE));
		tasksPage.clickOnCloseButtonInAddTaskModal();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskDueDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidDueDateMessage(TaskLabels.TASKS_INVALID_DUEDATE));
		tasksPage.clickOnCloseButtonInAddTaskModal();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[0]);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(-1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyInvalidDueDateMessage(TaskLabels.TASKS_BEFORE_STARTDATE));
		tasksPage.clickOnCloseButtonInAddTaskModal();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[0]);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[0]));

		tasksPage.clickOnTaskInTimelineView(taskTitle[0]);
		startDate = tasksPage.getStartDateInViewTaskModal();
		dueDate = tasksPage.getDueDateInViewTaskModal();
		if (!(startDate.equals(dueDate)))
		{
			Assert.assertTrue(false);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[1]);
		tasksPage.setTaskDueDate(currentDate);
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[1]));

		tasksPage.clickOnTaskInTimelineView(taskTitle[1]);
		startDate = tasksPage.getStartDateInViewTaskModal();
		dueDate = tasksPage.getDueDateInViewTaskModal();
		if (!startDate.equals(dueDate))
		{
			Assert.assertTrue(false);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[2]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_COMPLETED);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_HIGH);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[2]));

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[2]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_HIGH);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_COMPLETED);

		tasksPage.clickOnTaskInTimelineView((taskTitle[2]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[3]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_INPROGRESS);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_LOW);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibility(taskTitle[3]));
		tasksPage.clearSearch();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[3]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_LOW);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);

		tasksPage.clickOnTaskInTimelineView((taskTitle[3]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		tasksPage.clickHeaderViewButton();
		tasksPage.clickCardViewFromViewMenu();
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[4]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_NOTSTARTED);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_NORMAL);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInCardView(TaskLabels.TASKS_STATUS_NOTSTARTED, taskTitle[4]));

		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[4]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_NORMAL);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_NOTSTARTED);

		tasksPage.clickOnTaskInTimelineView((taskTitle[4]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		documentPage = tasksPage.gotoFileModule();
		if (!documentPage.verifyDocumentUploaded(fileName[0]))
		{
			documentPage.selectItemFromAdd(addMenuItems[0]);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName[0]);
			documentPage.addFile(addDoc, null);
			documentPage.clickAddInModal();
			/** Verify file is present */
			documentPage.searchFile(fileName[0]);
			Assert.assertTrue(documentPage.verifyDocumentUploaded(fileName[0]));
		}

		documentPage.gotoMoreActions(fileName[0], DocFolderOperation.Add_task.toString().replace("_", " "));
		tasksPage.setTaskTitle(taskTitle[5]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_INPROGRESS);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_HIGH);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();

		tasksPage = documentPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[5]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_HIGH);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);

		tasksPage.clickOnTaskInTimelineView((taskTitle[5]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		documentPage = tasksPage.gotoFileModule();
		documentPage.selectItemFromAdd(addMenuItems[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName[1]);
		documentPage.addFile(addDoc, null);

		documentPage.gotoTasksTab();
		tasksPage = documentPage.clickAddButtonInTasksTab();
		tasksPage.setTaskTitle(taskTitle[6]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_COMPLETED);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_NORMAL);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		documentPage.clickAddInAddFileModal();

		tasksPage = documentPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[6]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_NORMAL);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_COMPLETED);

		tasksPage.clickOnTaskInTimelineView((taskTitle[6]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		documentPage = tasksPage.gotoFileModule();
		documentPage.selectItemFromAdd(addMenuItems[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName[2]);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInModal();
		documentPage.searchFile(fileName[2]);
		Assert.assertTrue(documentPage.verifyDocumentUploaded(fileName[2]));
		String version = documentPage.getFileVersion();

		documentPage.gotoMoreActions(fileName[2], DocFolderOperation.Add_version.toString().replace("_", " "));
		documentPage.uploadDoc_onAddNewVersionWin(fileName[3], newVersionTitle, newVersionNote);
		documentPage.gotoTasksTab();
		documentPage.clickOnAddButtonOfTaskInAddNewVersion();
		tasksPage.setTaskTitle(taskTitle[7]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_NOTSTARTED);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_LOW);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		documentPage.clickOnAddInAddNewVersion();
		documentPage.searchFile(fileName[3]);
		if (!documentPage.verifyVersionCountIncremented(version))
			Assert.assertFalse(true);

		tasksPage = documentPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[7]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_LOW);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_NOTSTARTED);

		tasksPage.clickOnTaskInTimelineView((taskTitle[7]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		documentPage = tasksPage.gotoFileModule();
		documentPage.selectItemFromAdd(addMenuItems[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName[4]);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInModal();
		documentPage.searchFile(fileName[4]);
		Assert.assertTrue(documentPage.verifyDocumentUploaded(fileName[4]));

		documentPage.selectLeftPanelFileOptions("Index");
		documentPage.selectFileFromIndex(fileName[4]);
		documentPage.selectItemFromActions(FileLabels.FILES_ALLOCATE_FOR_REVIEW);
		tasksPage = new TasksWeb(driver);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_INPROGRESS);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_NORMAL);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();

		tasksPage = documentPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, "Review automation");
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_NORMAL);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_INPROGRESS);

		tasksPage.clickOnTaskInTimelineView(("Review automation"));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Assert.assertTrue(tasksPage.verifyTaskInParticularView(todayDate, dueDateofTask, "Review automation"));
		logout();

	}

	private void scenario2() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		tasksPage = dashboardPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		tasksPage.addTaskInTimelineViewWithDoubleClick(todayDate);
		Assert.assertTrue(tasksPage.verifyAddTaskModalVisibility());

		Assert.assertTrue(tasksPage.verifyStartDateFromDatePickerInAddTaskModal(dateoftoday));
		tasksPage.clickOnStartDateDatePickerInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyDueDateFromDatePickerInAddTaskModal(dateoftoday));
		tasksPage.clickOnCloseButtonInAddTaskModal();
		logout();

	}

	private void scenario3() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);

		enableDisableTimelineViewFormSiteAdmin(false);

		dashboardPage = adminTaskPage.gotoDashboard();
		tasksPage = dashboardPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyViewTaskDetailsSite(TaskLabels.TASKS_SITE));
		tasksPage.setSite(siteName);
		Assert.assertFalse(tasksPage.verifyTaskDetailsLabelVisibility(TaskLabels.TASKS_STARTDATE));
		tasksPage.clickOnCloseButtonInAddTaskModal();
		enableDisableTimelineViewFormSiteAdmin(true);
		dashboardPage = gotoDashboard();
		tasksPage = dashboardPage.goToMyTasks();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderAddButton();
		Assert.assertTrue(tasksPage.verifyViewTaskDetailsSite(TaskLabels.TASKS_SITE));
		tasksPage.setSite(siteName);
		Assert.assertTrue(tasksPage.verifyTaskDetailsLabelVisibility(TaskLabels.TASKS_STARTDATE));
		tasksPage.setTaskTitle(myTaskTitle1);
		tasksPage.setTaskStartDate(testDate);
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyInvalidStartDateMessage(TaskLabels.TASKS_INVALID_STARTDATE));
		tasksPage.setTaskStartDate("");
		tasksPage.setTaskDueDate(testDate);
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyInvalidDueDateMessage(TaskLabels.TASKS_INVALID_DUEDATE));
		tasksPage.setTaskStartDate(getDateAndSystemTime(dateFormat1));
		tasksPage.setTaskDueDate(getCustomDateValue(-1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyInvalidDueDateMessage(TaskLabels.TASKS_BEFORE_STARTDATE));
		tasksPage.setTaskDueDate("");
		tasksPage.clickOnAddInAddTaskModal();
		tasksPage.clickAssignToMeTabInTasks();
		tasksPage.selectTask(myTaskTitle1);
		Assert.assertTrue(tasksPage.verifyTaskStartAndDueDate());
		verifyTaskInTimeLineView(myTaskTitle1);

		dashboardPage = gotoDashboard();
		tasksPage = dashboardPage.goToMyTasks();
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(myTaskTitle2);
		tasksPage.setSite(siteName);
		tasksPage.setTaskDueDate(getDateAndSystemTime(dateFormat1));
		tasksPage.setSite(siteName);
		tasksPage.clickOnAddInAddTaskModal();
		tasksPage.clickAssignToMeTabInTasks();
		tasksPage.selectTask(myTaskTitle2);
		Assert.assertTrue(tasksPage.verifyTaskStartAndDueDate());
		verifyTaskInTimeLineView(myTaskTitle2);

		dashboardPage = gotoDashboard();
		tasksPage = dashboardPage.goToMyTasks();
		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(myTaskTitle3);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(getDateAndSystemTime(dateFormat1));
		tasksPage.setTaskDueDate(getDateAndSystemTime(dateFormat1));
		tasksPage.setSite(siteName);
		tasksPage.clickOnAddInAddTaskModal();
		tasksPage.clickAssignToMeTabInTasks();
		tasksPage.selectTask(myTaskTitle3);
		verifyTaskInTimeLineView(myTaskTitle3);

		logout();
	}

	private void scenario4() throws InterruptedException, IOException, ParseException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		enableDisableTimelineViewFormSiteAdmin(true);
		String statusColor = adminTaskPage.getParticularStatusColor(TaskLabels.TASKS_STATUS_COMPLETED);
		tasksPage = adminTaskPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickColumnViewFromViewMenu();
		tasksPage.deleteAllTasks();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(taskTitle[0]);
		tasksPage.setStatus(TaskLabels.TASKS_STATUS_COMPLETED);
		tasksPage.setPriority(TaskLabels.TASKS_PRIORITY_HIGH);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(getCustomDateValue(1, dateFormat1));
		tasksPage.clickOnAddInAddTaskModal();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(taskTitle[0]));

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));
		taskDetails.put(TaskLabels.TASKS_PRIORITY, TaskLabels.TASKS_PRIORITY_HIGH);
		taskDetails.put(TaskLabels.TASKS_STATUS, TaskLabels.TASKS_STATUS_COMPLETED);

		tasksPage.clickOnTaskInTimelineView((taskTitle[0]));
		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		Assert.assertTrue(tasksPage.verifyXButtonVisibilityInViewTaskModal());
		Assert.assertTrue(tasksPage.verifyCloseButtonInViewTask());
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		Assert.assertFalse(tasksPage.verifyViewTaskModalVisibility());

		tasksPage.clickOnTaskInTimelineView((taskTitle[0]));
		tasksPage.editTaskStartDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidStartDateInViewTaskModal(TaskLabels.TASKS_INVALID_STARTDATE));
		tasksPage.editTaskDueDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidDueDateInEditTaskModal(TaskLabels.TASKS_INVALID_DUEDATE));

		tasksPage.editTaskStartDate(currentDate);
		tasksPage.editTaskDueDate(getCustomDateValue(-1, dateFormat1));
		Assert.assertTrue(tasksPage.verifyInvalidDueDateInEditTaskModal(TaskLabels.TASKS_BEFORE_STARTDATE));
		tasksPage.clickOnCancelIconOfDueDateInViewTaskModal();

		tasksPage.clickOnNoDueDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		tasksPage.clickOnTaskInTimelineView(taskTitle[0]);

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, currentDate);

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.clickOnNoStartDateFromDatePickerInViewTaskModal();
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		tasksPage.clickOnTaskInTimelineView(taskTitle[0]);

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, currentDate);

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.clickOnEditIconOfTaskStartDate();
		tasksPage.setDateFromDatePickerForStartDateInViewTaskModal(getCustomDateValue(-1, dateFormat3));
		tasksPage.clickOnCancelIconOfStartDateInViewTaskModal();

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.clickOnEditIconOfTaskStartDate();
		tasksPage.setDateFromDatePickerForStartDateInViewTaskModal(getCustomDateValue(-1, dateFormat3));
		tasksPage.clickOnSubmitIconOfStartDateInViewTaskModal();

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, getCustomDateValue(-1, dateFormat1));

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.clickOnEditIconOfTaskDueDate();
		tasksPage.setDateFromDatePickerForDueDateInViewTaskModal(getCustomDateValue(1, dateFormat3));
		tasksPage.clickOnCancelIconOfDueDateInViewTaskModal();

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, currentDate);

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.clickOnEditIconOfTaskDueDate();
		tasksPage.setDateFromDatePickerForDueDateInViewTaskModal(getCustomDateValue(1, dateFormat3));
		tasksPage.clickOnSubmitIconOfDueDateInViewTaskModal();

		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, getCustomDateValue(1, dateFormat1));

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}

		tasksPage.editTaskStartDate(currentDate);
		tasksPage.editTaskDueDate(currentDate);
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle[0]);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_DUEDATE, currentDate);

		if (!tasksPage.verifyTaskData(taskDetails))
		{
			Assert.assertFalse(true);
		}
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();

		Assert.assertTrue(tasksPage.verifyTaskInParticularView(todayDate, todayDate, taskTitle[0]));

		Assert.assertTrue(tasksPage.verifyTaskColorForTimelineView(taskTitle[0], statusColor));

		tasksPage.clickOnTaskInTimelineView(taskTitle[0]);
		Assert.assertTrue(tasksPage.verifyStatusColorInViewTaskModal(taskTitle[0], TaskLabels.TASKS_STATUS_COMPLETED, statusColor));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		logout();
	}

	private void scenario5() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, defaultPassword);

		enableDisableTimelineViewFormSiteAdmin(true);
		dashboardPage = adminTaskPage.gotoDashboard();
		tasksPage = dashboardPage.goToMyTasks();
		tasksPage.deleteAllTasks();

		tasksPage.clickHeaderAddButton();
		tasksPage.setTaskTitle(myTaskTitle1);
		tasksPage.setSite(siteName);
		tasksPage.setTaskStartDate(currentDate);
		tasksPage.setTaskDueDate(currentDate);
		tasksPage.clickOnAddInAddTaskModal();

		tasksPage.clickOnCreatedByMeTab();
		tasksPage.selectTask(myTaskTitle1);
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, myTaskTitle1);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_DUEDATE, currentDate);
		Assert.assertTrue(tasksPage.verifyTaskData(taskDetails));

		tasksPage.editTaskStartDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidStartDateInViewTaskModal(TaskLabels.TASKS_INVALID_STARTDATE));

		tasksPage.editTaskDueDate(testDate);
		Assert.assertTrue(tasksPage.verifyInvalidDueDateInEditTaskModal(TaskLabels.TASKS_INVALID_DUEDATE));

		tasksPage.editTaskStartDate(currentDate);
		tasksPage.editTaskDueDate(getCustomDateValue(-1, dateFormat1));
		Assert.assertTrue(tasksPage.verifyInvalidDueDateInEditTaskModal(TaskLabels.TASKS_BEFORE_STARTDATE));
		tasksPage.clickOnCancelIconOfDueDateInViewTaskModal();
		tasksPage.editTaskDueDate(" ");
		tasksPage.editTaskStartDate(currentDate);
		tasksPage.clickOnCloseInViewTask();

		tasksPage.selectTask(myTaskTitle1);
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, myTaskTitle1);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_DUEDATE, currentDate);
		Assert.assertTrue(tasksPage.verifyTaskData(taskDetails));

		tasksPage.editTaskStartDate(" ");
		tasksPage.editTaskDueDate(currentDate);
		tasksPage.clickOnCloseInViewTask();

		tasksPage.selectTask(myTaskTitle1);
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, myTaskTitle1);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_DUEDATE, currentDate);
		Assert.assertTrue(tasksPage.verifyTaskData(taskDetails));

		tasksPage.editTaskStartDate(currentDate);
		tasksPage.editTaskDueDate(currentDate);
		tasksPage.clickOnCloseInViewTask();

		Assert.assertTrue(tasksPage.verifyTaskVisibility(myTaskTitle1));

		dashboardPage = tasksPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = dashboardPage.gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(myTaskTitle1));

		tasksPage.clickOnTaskInTimelineView(myTaskTitle1);
		taskDetails.clear();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, myTaskTitle1);
		taskDetails.put(TaskLabels.TASKS_STARTDATE, currentDate);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_DUEDATE, currentDate);
		Assert.assertTrue(tasksPage.verifyTaskData(taskDetails));
		tasksPage.clickOnCloseButtonInViewTaskInCardOrTimelineView();
		logout();
	}

	private void enableDisableTimelineViewFormSiteAdmin(boolean flag)
	{
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(flag);
		if (flag == true)
			adminTaskPage.setDefaultView(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();
	}

	private void verifyTaskInTimeLineView(String TaskTitle) throws InterruptedException, IOException
	{
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		tasksPage = dashboardPage.gotoTaskModule();
		tasksPage.clickHeaderViewButton();
		tasksPage.clickTimelineViewFromViewMenu();
		Assert.assertTrue(tasksPage.verifyTaskVisibilityInTimelineView(TaskTitle));
	}

	private void dateCompare(String date) throws ParseException
	{
		String dates[] = tasksPage.getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat5);
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
