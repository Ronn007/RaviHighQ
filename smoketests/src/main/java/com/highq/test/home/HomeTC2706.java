package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
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
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author krishna.bhadani
 */
public class HomeTC2706 extends BannerPageWeb
{
	private String sysAdminEmail = "krishna.bhadani@highq.com";
	private String sysAdminPassword = "Admin@123";

	private String siteAdminEmail = "site.adminhome@highq.com";
	private String siteAdminEmail1 = "site.adminhome1@highq.com";

	private String siteAdminUsername = "Site Adminhome";
	private String siteAdminUsername1 = "Site Adminhome1";
	private String jsonFileName = "home/preConfiguration_HomeTC2706.json";
	private String siteAdminUserToken = siteAdminUsername + " (" + siteAdminEmail + ")";
	private String siteAdminUserToken1 = siteAdminUsername1 + " (" + siteAdminEmail1 + ")";
	private String siteName = "Home_TC2706";
	private String formate = "dd MMM YYYY";
	DateFormat dateFormat = new SimpleDateFormat(formate);

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

	private DashboardPage dashboardWeb;
	private BannerPage bannerPageWeb;
	private AspConfigurationPage aspConfigurationPage;
	private HomePage homeWeb;
	private AspAdminPage aspAdminWeb;
	private AdminPage adminWeb;
	private TasksPage tasksWeb;
	private AdminTaskPage adminTaskWeb;

	private Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	private List<String> chartType = new ArrayList<>();
	private List<String> categoryList = new ArrayList<>();
	private List<String> valueItemName = new ArrayList<>();
	private ArrayList<String> filterItemName = new ArrayList<>();
	private Map<String, String> toolTip = new LinkedHashMap<>();
	private Map<String, String> taskData = new LinkedHashMap<>();
	private List<String> seriesList = new ArrayList<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void taskAggregation() throws Exception
	{
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0);
		dueDate1 = dateFormat.format(c.getTime());

		c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		dueDate2 = dateFormat.format(c.getTime());

		// ===chart type===//
		chartType = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartType"), List.class);
		categoryList = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("itemList"), List.class);
		valueItemName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("valueList"), List.class);

		preconfiguration();
		scenario1AddDataVisualisationWhenAnyTaskDoesNotExistInSelectedSite();
		scenario2AddDataVisualisationWhenTasksExistInSelectedSite();
		scenario3Case1PlotDataVisualisationChartForTaskSelectingDifferentCategories();
		scenario3Case2PlotDataVisualisationChartForTaskSelectingDifferentCategories();
		scenario3Case3PlotDataVisualisationChartForTaskSelectingDifferentCategories();
		scenario3Case4PlotDataVisualisationChartForTaskSelectingDifferentCategories();
		scenario3Case5PlotDataVisualisationChartForTaskSelectingDifferentCategories();
		scenario3Case6PlotDataVisualisationChartForTaskSelectingDifferentCategories();
	}

	public void preconfiguration() throws Exception
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		dashboardWeb = bannerPageWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDataVisualisationPanel(true);
		aspConfigurationPage.saveConfigurations();
		logout();
	}

	public void scenario1AddDataVisualisationWhenAnyTaskDoesNotExistInSelectedSite() throws Exception
	{
		valueItemName.clear();
		valueItemName.add(HomeLabels.TASK);

		Reporter.log("<font color='red'><B> scenario1 Add data visualisation when any task does not exist in selected site</B></font>");
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		removeTaskAndTaskList();
		homeWeb = bannerPageWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();

		for (int i = 0; i < chartType.size(); i++)
		{
			homeWeb.removeAllSections();
			homeWeb.clickOnAddSection();
			homeWeb.clickonSingleAddPanelIcon();
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, "Data Visualisation" + chartType.get(i));
			addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			Reporter.log("<B> chart Type Name:-" + chartType.get(i) + "</B>");
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueItemName, true);
			}
			else
			{
				homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK);
			}
			homeWeb.clickOnPreviewButton();
			assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.NO_DATA_TO_DISPLAY));

			homeWeb.clickOnAdd();
			assertTrue(homeWeb.verifyErrorMessageInDashBoard("Data Visualisation" + chartType.get(i), HomeLabels.NO_ITEMS_TO_DISPLAY));
		}
	}

	public void scenario2AddDataVisualisationWhenTasksExistInSelectedSite() throws Exception
	{
		valueItemName.clear();
		valueItemName.add(HomeLabels.TASK);

		Reporter.log("<font color='red'><B> scenario2 Add Data visualisation when tasks exist in selected site</B></font>");
		// ====Add Task===//

		addTaskAndList();

		homeWeb = gotoHomeModule();
		homeWeb.clickOnEditIcon();

		for (int i = 0; i < chartType.size(); i++)
		{
			homeWeb.removeAllSections();
			homeWeb.clickOnAddSection();
			homeWeb.clickonSingleAddPanelIcon();
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
			addDataInDatavisualisation(chartType.get(i));
			Reporter.log("<B> chart Type Name:-" + chartType.get(i) + "</B>");
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || HomeLabels.STACKED_COLUMN.equals(chartType.get(i)))
			{
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueItemName, true);
			}
			else
			{
				homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK);
			}
			homeWeb.clickOnPreviewButton();
			Reporter.log("Dummy chart should remove from chart preview area & it should display a validation message in chart preview area that No data to display.”");
			if (HomeLabels.STACKED_COLUMN.equals(chartType.get(i)) || HomeLabels.MULTI_SERIES_COLUMN.equals(chartType.get(i)))
			{
				toolTip.clear();
				toolTip.put(taskTitle1.trim(), "1");
				toolTip.put(taskTitle2.trim(), "1");
				assertTrue(homeWeb.verifyChart(chartType.get(i), toolTip, valueItemName.size()));

				homeWeb.clickOnAdd();
				assertTrue(homeWeb.verifyChart(chartType.get(i), toolTip, valueItemName.size()));

			}
			else
			{
				toolTip.clear();
				toolTip.put(taskTitle1, "1");
				toolTip.put(taskTitle2, "1");
				assertTrue(homeWeb.verifyChart(chartType.get(i), toolTip, 1));

				homeWeb.clickOnAdd();
				assertTrue(homeWeb.verifyChart(chartType.get(i), toolTip, 1));
			}
		}
	}

	public void scenario3Case1PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{

		Reporter.log("<font color='red'><B> scenario3 case 1 category -> Task Title Plot Data visualisation chart for Task selecting different categories</B></font>");
		homeWeb = gotoHomeModule();
		homeWeb.clickOnEditIcon();
		taskData = addData();

		String[] itemTitle = {taskTitle1, taskTitle2};
		String[] aggreCount = {"1", "1"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.TASK_TITLE, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.TASK_TITLE, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}
		Reporter.log("Data visualisation chart related to count of Tasks existing with same task titles should be plotted.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task name, count of task existing with that task name)");
		Reporter.log("All task titles existing in site should display as Legends in plotted chart. ");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each task title it should display a count of tasks grouped by different assignees.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Assignee name, Task Title, count of tasks assigned to that Assignee.)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
	}

	public void scenario3Case2PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{
		// dashboardWeb = (DashboardPage) login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb.searchSite(siteName);
		// homeWeb = gotoHomeModule();
		// homeWeb.clickOnEditIcon();

		Reporter.log("<font color='red'><B> scenario3 case 2 category -> List Plot Data visualisation chart for Task selecting different categories</B></font>");

		taskData = addData();

		String[] itemTitle = {taskList1, taskList2};
		String[] aggreCount = {"1", "1"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.LIST, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.LIST, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}
		Reporter.log("Data visualisation chart related to count of total Tasks existing in different Task Lists should be plotted.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Name of Task List, count of tasks existing in that task list)");
		Reporter.log("All task lists existing in site for which chart is plotted should display as Legends in plotted chart.");
		Reporter.log("If any of the task list does not have task associated with it then that task list should not display with the chart.");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each task list it should display a count of tasks grouped by different status.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task status, Name of Task List, count of tasks existing in that task list & status)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
	}

	public void scenario3Case3PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{

		Reporter.log("<font color='red'><B> scenario3 case 3 category -> Status Plot Data visualisation chart for Task selecting different categories</B></font>");

		taskData = addData();

		String[] itemTitle = {statusNot, statusInProgress};
		String[] aggreCount = {"1", "2"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.STATUS, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.STATUS, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}
		Reporter.log("Data visualisation chart related to count of Tasks in different Task status should be plotted. ");
		Reporter.log("Chart related to custom Task status should also display if tasks with custom status are existing in the selected site.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task status, count of tasks existing with that status)");
		Reporter.log("All task status along with custom status existing in site should display as Legends in plotted chart. ");
		Reporter.log("If any of the task status does not have task associated with it then that task status should not display with the chart.");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each task status it should display a count of tasks grouped by different priorites.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task priority, Task status, count of tasks existing with that priority & status)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
	}

	public void scenario3Case4PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{

		Reporter.log("<font color='red'><B> scenario3 case 4 category -> Priority  Plot Data visualisation chart for Task selecting different categories</B></font>");

		taskData = addData();

		String[] itemTitle = {HomeLabels.NORMAL, HomeLabels.LOW};
		String[] aggreCount = {"1", "1"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.PRIORITY, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.PRIORITY, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}
		Reporter.log("Data visualisation chart related to count of Tasks existing with different priorities should be plotted. ");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task priority, count of tasks existing with that priority)");
		Reporter.log("All task priorites to which tasks are assigned should display as Legends in plotted chart.");
		Reporter.log("If any of the task priority does not have task associated with it then that task status should not display with the chart.");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each task priority it should display a count of tasks grouped by different due dates.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Due date value, Task priority, count of tasks existing with that due date & priority)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
	}

	public void scenario3Case5PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{

		Reporter.log("<font color='red'><B> scenario3 case 5 category -> Assignee  Plot Data visualisation chart for Task selecting different categories</B></font>");

		taskData = addData();

		String[] itemTitle = {siteAdminUsername, siteAdminUsername1};
		String[] aggreCount = {"1", "1"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.ASSIGNEE, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.ASSIGNEE, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}
		Reporter.log("Data visualisation chart related to count of Tasks that are assigned to different users of the site should be plotted. (i.e count of tasks assigned to different users of the site)");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Assignee name, count of tasks assigned to that Assignee)");
		Reporter.log("All assignees to which tasks are assigned should display as Legends in plotted chart. ");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each Assignee it should display a count of tasks grouped by different task list.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task list, Assignee name, count of tasks assigned to that user with that particular task list)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
	}

	public void scenario3Case6PlotDataVisualisationChartForTaskSelectingDifferentCategories() throws Exception
	{

		Reporter.log("<font color='red'><B> scenario3 case 6 category -> Due Date  Plot Data visualisation chart for Task selecting different categories</B></font>");

		taskData = addData();

		String[] itemTitle = {dueDate1, dueDate2};
		String[] aggreCount = {"1", "1"};
		for (int i = 0; i < chartType.size(); i++)
		{
			if (chartType.get(i).equals(HomeLabels.STACKED_COLUMN) || chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN))
			{
				stackedColumn(taskData, chartType.get(i), HomeLabels.DUE_DATE, HomeLabels.TASK, categoryList, categoryList, itemTitle, aggreCount);
			}
			else
			{
				chartVerify(HomeLabels.DUE_DATE, HomeLabels.TASK, chartType.get(i), categoryList, itemTitle, aggreCount);
			}
		}

		Reporter.log("Data visualisation chart related to count of tasks that are existing with different due dates should be plotted. ");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Due date, count of tasks which are having that particular due date)");
		Reporter.log("All Due dates on which tasks are existing should display as Legends in plotted chart. ");
		Reporter.log("Data in plotted chart should group as per the value selected in series for each selected category.");
		Reporter.log("Considering our selection of Category & series, for each Due date it should display a count of tasks grouped by different priority.");
		Reporter.log("On hovering over the plotted chart it should display a tool tip value as (Task Priority, Due date, count of tasks existing with that priority for that particular due date)");
		Reporter.log("After selecting filters, data in plotted chart should filter & render based on the selected filter values.");
		logout();
	}

	/**
	 * @param addData
	 * @param chartName
	 * @param categoryItem
	 * @param valueItem
	 * @param seriesItem
	 * @param filterItem
	 * @param itemName
	 * @param aggregationsCount
	 */
	public void stackedColumn(Map<String, String> addData, String chartName, String categoryItem, String valueItem, List<String> seriesItem, List<String> filterItem, String[] itemName, String[] aggregationsCount)
	{

		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataInDatavisualisation(chartName);
		Reporter.log("<B> chart Type Name:-" + chartName + "</B>");

		// ====Category Task Title====//
		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, categoryItem);
		if (chartName.equals(HomeLabels.MULTI_SERIES_COLUMN) || chartName.equals(HomeLabels.STACKED_COLUMN))
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			Reporter.log(" <B>Value List" + valueItemName.toString() + "</B>");
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueItemName, true);
		}
		homeWeb.clickOnPreviewButton();
		toolTip.clear();
		for (int num = 0; num < itemName.length; num++)
		{
			toolTip.put(itemName[num].trim(), aggregationsCount[num]);
		}
		Reporter.log("Pass By test Script  " + toolTip.toString());
		assertTrue(homeWeb.verifyChart(chartName, toolTip, valueItemName.size()));

		for (int i = 0; i < seriesItem.size(); i++)
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, seriesItem.get(i));

			Reporter.log("<B> series selection " + seriesItem.get(i) + "</B>");
			for (int j = 0; j < filterItem.size(); j++)
			{
				filterItemName.clear();
				filterItemName.add(filterItem.get(j));

				homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterItemName, true);
				Reporter.log("<B> filter selection1" + filterItem.get(j) + "</B>");

				selectAggregationsFromFilter(filterItem.get(j));

				for (int k = j + 1; k < filterItem.size(); k++)
				{
					filterItemName.clear();
					filterItemName.add(filterItem.get(j));
					filterItemName.add(filterItem.get(k));

					homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
					homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterItemName, true);
					Reporter.log("<B> filter selection2" + filterItem.get(k) + "</B>");

					selectAggregationsFromFilter(filterItem.get(k));

					homeWeb.clickOnPreviewButton();
					seriesList.clear();
					for (Map.Entry<String, String> entry : addData.entrySet())
					{
						if (seriesItem.get(i).equals(entry.getValue()))
						{
							seriesList.add(entry.getKey());
						}
					}

					toolTip.clear();
					for (int num = 0; num < seriesList.size(); num++)
					{
						if (!categoryItem.equals(seriesItem.get(i)))
						{
							Reporter.log("chartName" + chartName + "legends" + seriesList.get(num).trim());
							assertTrue(homeWeb.verifyLegends(chartName, seriesList.get(num).trim()));
							toolTip.put(seriesList.get(num).trim() + itemName[num].trim(), aggregationsCount[num]);
						}
						else
						{
							toolTip.put(seriesList.get(num).trim(), aggregationsCount[num]);
						}
					}

					assertTrue(homeWeb.verifyChart(chartName, toolTip, valueItemName.size()));
					// str = seriesList.toArray(new String[0]);
					homeWeb.removeToken(HomeLabels.FILTER, filterItem.get(k));

				}
				homeWeb.clickOnPreviewButton();
				toolTip.clear();
				for (int num = 0; num < seriesList.size(); num++)
				{
					if (!categoryItem.equals(seriesItem.get(i)))
					{
						Reporter.log("chartName" + chartName + "legends" + seriesList.get(num).trim());
						assertTrue(homeWeb.verifyLegends(chartName, seriesList.get(num).trim()));
						toolTip.put(seriesList.get(num).trim() + itemName[num].trim(), aggregationsCount[num]);
					}
					else
					{
						toolTip.put(seriesList.get(num).trim(), aggregationsCount[num]);
					}
				}

				assertTrue(homeWeb.verifyChart(chartName, toolTip, valueItemName.size()));

				homeWeb.removeToken(HomeLabels.FILTER, filterItem.get(j));

			}
		}
		homeWeb.clickOnAdd();
		assertTrue(homeWeb.verifyChart(chartName, toolTip, valueItemName.size()));
	}

	/**
	 * @param categoryItem
	 * @param valueItem
	 * @param chartName
	 * @param filterItem
	 * @param itemName
	 * @param aggregationsCount
	 */
	public void chartVerify(String categoryItem, String valueItem, String chartName, List<String> filterItem, String[] itemName, String[] aggregationsCount)
	{
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataInDatavisualisation(chartName);
		Reporter.log("<B> chart Type Name:-" + chartName + "</B>");
		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);

		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, categoryItem);
		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, valueItem);

		homeWeb.clickOnPreviewButton();
		toolTip.clear();
		for (int num = 0; num < itemName.length; num++)
		{
			toolTip.put(itemName[num].trim(), aggregationsCount[num]);
			if (chartName.equals(HomeLabels.STANDARD_PIE))
			{
				Reporter.log("chartName" + chartName + "legends" + itemName[num].trim());
				assertTrue(homeWeb.verifyLegends(chartName, itemName[num].trim()));
			}
			if (chartName.equals(HomeLabels.STANDARD_COLUMN))
			{
				Reporter.log("chartName" + chartName + "legends" + itemName[num].trim());
				assertTrue(!homeWeb.verifyLegends(HomeLabels.STANDARD_COLUMN, itemName[num].trim()));
			}
		}

		assertTrue(homeWeb.verifyChart(chartName, toolTip, 1));

		for (int j = 0; j < filterItem.size(); j++)
		{
			filterItemName.clear();
			filterItemName.add(filterItem.get(j));

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterItemName, true);

			Reporter.log("<B> filter selection1" + filterItem.get(j) + "</B>");
			selectAggregationsFromFilter(filterItem.get(j));

			for (int k = j + 1; k < filterItem.size(); k++)
			{
				filterItemName.clear();
				filterItemName.add(filterItem.get(j));
				filterItemName.add(filterItem.get(k));

				homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterItemName, true);

				Reporter.log("<B> filter selection2" + filterItem.get(k) + "</B>");
				selectAggregationsFromFilter(filterItem.get(k));

				Reporter.log(filterItem.get(j));
				Reporter.log(filterItem.get(k));
				homeWeb.clickOnPreviewButton();

				toolTip.clear();
				for (int num = 0; num < itemName.length; num++)
				{
					toolTip.put(itemName[num].trim(), aggregationsCount[num]);
					if (chartName.equals(HomeLabels.STANDARD_PIE))
					{
						Reporter.log("chartName" + chartName + "legends" + itemName[num].trim());
						assertTrue(homeWeb.verifyLegends(chartName, itemName[num].trim()));
					}
					if (chartName.equals(HomeLabels.STANDARD_COLUMN))
					{
						Reporter.log("chartName" + chartName + "legends" + itemName[num].trim());
						assertTrue(!homeWeb.verifyLegends(HomeLabels.STANDARD_COLUMN, itemName[num].trim()));
					}
				}

				assertTrue(homeWeb.verifyChart(chartName, toolTip, 1));
				homeWeb.removeToken(HomeLabels.FILTER, filterItem.get(k));
			}
			homeWeb.removeToken(HomeLabels.FILTER, filterItem.get(j));
		}
		homeWeb.clickOnAdd();
		assertTrue(homeWeb.verifyChart(chartName, toolTip, 1));

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

	public void addDataInDatavisualisation(String charttype)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, charttype);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

	}

	public void removeTaskAndTaskList() throws Exception
	{
		adminWeb = gotoAdminModule();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();
		adminTaskWeb.removeAllTaskLists();
		tasksWeb = bannerPageWeb.gotoTaskModule();
		tasksWeb.deleteAllTasks();
	}

	public void addTaskAndList() throws Exception
	{

		adminWeb = bannerPageWeb.gotoAdminModule();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();

		adminTaskWeb.enterTaskListName(taskList1);
		adminTaskWeb.enterTaskListName(taskList2);
		tasksWeb = bannerPageWeb.gotoTaskModule();

		tasksWeb.clickHeaderAddButton();
		LinkedHashMap<String, String> addTaskData = new LinkedHashMap<>();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle1.toString());
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskList1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, statusNot);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDate1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, siteAdminEmail);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priorityNormal);
		tasksWeb.setTaskAddData(addTaskData);
		tasksWeb.clickOnAddTaskButtonInModel();

		tasksWeb.clickHeaderAddButton();
		addTaskData = new LinkedHashMap<>();
		addTaskData.clear();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle2.toString());
		addTaskData.put(TaskLabels.TASKS_ADDTASK_LIST, taskList2);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, statusInProgress);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_DUEDATE, dueDate2);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, siteAdminEmail1);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_PRIORITY, priorityLow);
		tasksWeb.setTaskAddData(addTaskData);
		tasksWeb.clickOnAddTaskButtonInModel();
	}

	public Map<String, String> addData()
	{
		Map<String, String> add = new LinkedHashMap<>();
		add.put(taskTitle1, HomeLabels.TASK_TITLE);
		add.put(taskTitle2, HomeLabels.TASK_TITLE);

		add.put(taskList1, HomeLabels.LIST);
		add.put(taskList2, HomeLabels.LIST);

		add.put(dueDate1, HomeLabels.DUE_DATE);
		add.put(dueDate2, HomeLabels.DUE_DATE);

		add.put(statusNot, HomeLabels.STATUS);
		add.put(statusInProgress, HomeLabels.STATUS);

		add.put(HomeLabels.NORMAL, HomeLabels.PRIORITY);
		add.put(HomeLabels.LOW, HomeLabels.PRIORITY);

		add.put(siteAdminUsername, HomeLabels.ASSIGNEE);
		add.put(siteAdminUsername1, HomeLabels.ASSIGNEE);
		return add;
	}

	public void selectAggregationsFromFilter(String itemName)
	{
		switch (itemName)
		{
			case HomeLabels.TASK_TITLE:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.TASK_TITLE, taskTitle1, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.TASK_TITLE, taskTitle2, true);
				break;
			case HomeLabels.LIST:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.LIST, taskList1, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.LIST, taskList2, true);
				break;
			case HomeLabels.STATUS:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.STATUS, statusInProgress, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.STATUS, statusNot, true);
				break;
			case HomeLabels.PRIORITY:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.PRIORITY, HomeLabels.NORMAL, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.PRIORITY, HomeLabels.LOW, true);
				break;
			case HomeLabels.ASSIGNEE:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.ASSIGNEE, siteAdminUserToken, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.ASSIGNEE, siteAdminUserToken1, true);
				break;
			case HomeLabels.DUE_DATE:
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.DUE_DATE, dueDate1, true);
				homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.DUE_DATE, dueDate2, true);
				break;
			default:
				break;
		}
	}
}
