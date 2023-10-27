package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author krishna.bhadani
 */
public class HomeTC2706_part2 extends BannerPageWeb
{
	private String sysAdminEmail;
	private String sysAdminPassword;

	private String normalUserEmail;
	private String siteAdminEmail;

	private String jsonFileName = "home/preConfiguration_HomeTC2706_Part2.json";
	private String siteName = "Home_TC2706_part2";

	// =======task Details=====//
	private String taskTitle1 = "taskTitle1";
	private String taskList1 = "taskList1";
	private String statusNot = HomeLabels.NOT_STARTED;
	private String dueDate1;
	private String priorityNormal = "Normal";

	private String taskTitle2 = "taskTitle2";
	private String taskList2 = "taskList2";
	private String statusInProgress = HomeLabels.IN_PROGRESS;
	private String dueDate2;
	private String priorityLow = "Low";
	private WebDriver driver;

	private BannerPage bannerPageWeb;
	private TasksPage tasksWeb;
	private AdminPage adminWeb;
	private AdminTaskPage adminTaskPageWeb;
	private DashboardPage dashboardWeb;
	private AspAdminPage aspAdminWeb;
	private HomePage homeWeb;
	private AdminTaskPage adminTaskWeb;
	private AspConfigurationPage aspConfigurationPage;
	private ModulesPage modulesPageWeb;

	List<String> chartType = new ArrayList<>();
	private Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();

	String[] name;
	String groupName;
	String panelTitle = "Data Visualisation for Task";
	List<String> filterData = new LinkedList<>();
	List<String> valueData = new LinkedList<>();
	List<String> aggregationsData = new LinkedList<>();
	private String formate = "dd MMM YYYY";
	DateFormat dateFormat = new SimpleDateFormat(formate);

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void taskAggregation_Part2() throws Exception
	{
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0);
		dueDate1 = dateFormat.format(c.getTime());

		c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		dueDate2 = dateFormat.format(c.getTime());

		chartType = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartType"), List.class);
		sysAdminEmail = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminEmail"), String.class);
		sysAdminPassword = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminPassword"), String.class);
		name = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("siteUsers"), String[].class);
		groupName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("UserGroups").get("group1").get("name"), String.class);

		siteAdminEmail = name[1];
		normalUserEmail = name[0];
		preconfiguration();
		scenario5AddDataVisualisationPanelForTaskRemoveTaskModuleFromSite();
		scenario6AddDataVisualisationPanelForTaskCheckPlottedChartBasedOnTaskListPermission();
		scenario7AddDataVisualisationPanelForTaskApplyCustomisationToChart();
	}

	public void preconfiguration() throws Exception
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDataVisualisationPanel(true);
		aspConfigurationPage.saveConfigurations();
		dashboardWeb = dashboardWeb.gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);
		addTaskAndList();

		valueData.clear();
		valueData.add(HomeLabels.TASK);
		addVisualisationPanelInDashBoard();
		addDataVisualisationPanelForTask(panelTitle, HomeLabels.TASK_TITLE, valueData, HomeLabels.LIST, HomeLabels.LIST, taskList1);
		homeWeb.clickOnSave();
		logout();
	}

	public void scenario5AddDataVisualisationPanelForTaskRemoveTaskModuleFromSite() throws Exception
	{
		try
		{
			bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminWeb = gotoAdminModule();
			modulesPageWeb = adminWeb.clickOnModulesInLeftPanel();
			modulesPageWeb.enableSpecificModules(false, HomeLabels.TASKS);
			adminWeb = modulesPageWeb.clickOnSaveButton();

			Reporter.log("Data visualisation chart for Tasks of selected site should not display on Home Dashboard if Tasks module is disabled in site.");
			Reporter.log("Instead of the plotted chart it should display validation message on Home Dashboard  You do not have the required permissions to view this panel. Contact your administrator for more information.");
			homeWeb = adminWeb.gotoHomeModule();
			homeWeb.clickOnEditIcon();
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, HomeLabels.DASHBOARDBUILDER_ERRORPERMISSION_MESSAGE));

			homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

			assertTrue(homeWeb.verifyDefaultSource(HomeLabels.NONE_SELECTED));
			assertTrue(homeWeb.verifySaveButtonsInDisabledMode());
			assertTrue(!homeWeb.verifyChartPreviewFields());
			homeWeb.clickOnCancelInModalWindow();
			Reporter.log("Source should be selected as 'None selected' & chart preview area should not be loaded.");
		}
		finally
		{
			adminWeb = gotoAdminModule();
			modulesPageWeb = adminWeb.clickOnModulesInLeftPanel();
			modulesPageWeb.enableSpecificModules(true, HomeLabels.TASKS);
			adminWeb = modulesPageWeb.clickOnSaveButton();
		}
	}

	public void scenario6AddDataVisualisationPanelForTaskCheckPlottedChartBasedOnTaskListPermission() throws IOException
	{
		try
		{
			// ====category select list====//
			valueData.clear();
			valueData.add(HomeLabels.TASK);
			addVisualisationPanelInDashBoard();
			addDataVisualisationPanelForTask(HomeLabels.CATEGORY + " " + HomeLabels.LIST, HomeLabels.LIST, valueData, HomeLabels.STATUS, HomeLabels.TASK_TITLE, taskTitle1);

			// ====series select list====//

			valueData.clear();
			valueData.add(HomeLabels.TASK);
			homeWeb.clickOnAddSection();
			homeWeb.clickOnMultiplePanelIcon(2);
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
			addDataVisualisationPanelForTask(HomeLabels.SERIES + " " + HomeLabels.LIST, HomeLabels.TASK_TITLE, valueData, HomeLabels.LIST, HomeLabels.STATUS, statusNot);

			// ====filter select list====//

			valueData.clear();
			valueData.add(HomeLabels.TASK);
			homeWeb.clickOnAddSection();
			homeWeb.clickOnMultiplePanelIcon(3);
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
			addDataVisualisationPanelForTask(HomeLabels.FILTER + " " + HomeLabels.LIST, HomeLabels.TASK_TITLE, valueData, HomeLabels.STATUS, HomeLabels.LIST, taskList1);
			homeWeb.clickOnSave();

			adminWeb = homeWeb.gotoAdminModule();
			adminTaskPageWeb = adminWeb.clickTasksInLeftPanel();
			adminTaskPageWeb.clickOnPermissions(taskList1);
			adminTaskPageWeb.clickOnRestricted();
			adminTaskPageWeb.setTaskListPermission(groupName, TaskLabels.TASKS_PREMISSION_VIEW, false);
			adminTaskPageWeb.clickOnSaveButtonInSetPermissionsOfList();

			adminTaskPageWeb.clickOnPermissions(taskList2);
			adminTaskPageWeb.clickOnRestricted();
			adminTaskPageWeb.setTaskListPermission(groupName, TaskLabels.TASKS_PREMISSION_VIEW, false);
			adminTaskPageWeb.clickOnSaveButtonInSetPermissionsOfList();
			logout();

			bannerPageWeb = login(normalUserEmail, sysAdminPassword);
			homeWeb = bannerPageWeb.gotoHomeModule();
			// ====category select list====//
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.CATEGORY + " " + HomeLabels.LIST, HomeLabels.NO_ITEMS_TO_DISPLAY));

			// ====series select list====//
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.SERIES + " " + HomeLabels.LIST, HomeLabels.NO_ITEMS_TO_DISPLAY));

			// ====filter select list====//
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.FILTER + " " + HomeLabels.LIST, HomeLabels.NO_ITEMS_TO_DISPLAY));
			logout();
		}
		finally
		{
			bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminWeb = bannerPageWeb.gotoAdminModule();
			adminTaskPageWeb = adminWeb.clickTasksInLeftPanel();
			adminTaskPageWeb.clickOnPermissions(taskList1);
			adminTaskPageWeb.clickOnRestricted();
			adminTaskPageWeb.setTaskListPermission(groupName, TaskLabels.TASKS_PREMISSION_VIEW, false);
			adminTaskPageWeb.clickOnSaveButtonInSetPermissionsOfList();

			adminTaskPageWeb.clickOnPermissions(taskList2);
			adminTaskPageWeb.clickOnRestricted();
			adminTaskPageWeb.setTaskListPermission(groupName, TaskLabels.TASKS_PREMISSION_VIEW, false);
			adminTaskPageWeb.clickOnSaveButtonInSetPermissionsOfList();
			logout();
		}
	}

	public void scenario7AddDataVisualisationPanelForTaskApplyCustomisationToChart()
	{
		List<Boolean> chartStatus = new ArrayList<>();
		chartStatus.add(true);
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		valueData.clear();
		valueData.add(HomeLabels.TASK);
		addVisualisationPanelInDashBoard();
		filterData.clear();
		filterData.add(HomeLabels.STATUS);

		addDataInDatavisualisation(HomeLabels.MULTI_SERIES_COLUMN, panelTitle);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueData, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.LIST);

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterData, true);
		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.STATUS, statusNot, true);

		homeWeb.clickOnPreviewButton();
		Reporter.log("Customise tab should enable when user clicks on Preview button & chart is plotted in chart preview area.");
		assertTrue(!homeWeb.verifyCustomiseButtonInDisabledMode());

		homeWeb.gotoAddChartTitle(HomeLabels.SERIES + " " + HomeLabels.LIST);

		assertTrue(homeWeb.verifyChartVisibility(HomeLabels.SERIES + " " + HomeLabels.LIST));

		homeWeb.clickOnCustomizeButton();
		homeWeb.clickOnDisplayLegendInCustomise(HomeLabels.NONE);
		homeWeb.clickOnApplyButton();
		assertTrue(!homeWeb.verifyLegends(HomeLabels.MULTI_SERIES_COLUMN, taskList1));

		homeWeb.clickOnData();
		homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, HomeLabels.TASK, HomeLabels.SUM, true);

		homeWeb.clickOnPreviewButton();
		assertTrue(homeWeb.verifyCustomiseButtonInDisabledMode());

		homeWeb.clickOnCancelInModalWindow();
		homeWeb.clickOnClose();

		tasksWeb = homeWeb.gotoTaskModule();
		tasksWeb.deleteAllTasks();

		homeWeb = tasksWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.clickOnMoreActionOption(HomeLabels.SERIES + " " + HomeLabels.LIST, HomeLabels.EDIT);
		assertTrue(homeWeb.verifyCustomiseButtonInDisabledMode());
		homeWeb.clickOnCancelInModalWindow();

	}

	public void addDataVisualisationPanelForTask(String panelTitle, String categoryItem, List<String> valueItem, String seriesItem, String filterItem, String aggregationsName)
	{
		filterData.clear();
		filterData.add(filterItem);

		addDataInDatavisualisation(HomeLabels.MULTI_SERIES_COLUMN, panelTitle);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, categoryItem);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueItem, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, seriesItem);

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterData, true);
		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, filterItem, aggregationsName, true);

		homeWeb.clickOnAdd();

	}

	public void addVisualisationPanelInDashBoard()
	{

		homeWeb = bannerPageWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();

		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
	}

	public void addDataInDatavisualisation(String charttype, String panelTitle)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SITE, siteName);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, charttype);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);
	}

	public void addTaskAndList() throws Exception
	{

		adminWeb = bannerPageWeb.gotoAdminModule();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();
		adminTaskWeb.removeAllTaskLists();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();
		adminTaskWeb.enterTaskListName(taskList1);
		adminTaskWeb.enterTaskListName(taskList2);

		tasksWeb = adminTaskWeb.gotoTaskModule();
		tasksWeb.deleteAllTasks();
		tasksWeb.clickHeaderAddButton();

		LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();
		addTaskData.clear();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle1.toString());
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskList1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, statusNot);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDate1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, siteAdminEmail);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priorityNormal);

		tasksWeb.setTaskAddData(addTaskData);
		tasksWeb.clickOnAddTaskButtonInModel();

		tasksWeb.clickHeaderAddButton();

		addTaskData.clear();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle2.toString());
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskList2);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, statusInProgress);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDate2);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, siteAdminEmail);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priorityLow);

		tasksWeb.setTaskAddData(addTaskData);
		tasksWeb.clickOnAddTaskButtonInModel();
	}
}
