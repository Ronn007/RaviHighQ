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
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SiteNavigationPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemVocabularyPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;
import com.highq.test.iSheet.BaseIsheetTest;

/**
 * @author krishna.bhadani
 */
public class HomeTC2705 extends BannerPageWeb
{
	private String sysAdminEmail = "krishna.bhadani@highq.com";
	private String sysAdminPassword = "Admin@123";

	private String siteAdminEmail = "site.adminhome@highq.com";
	private String siteAdminUsername = "Site Adminhome";
	private String siteAdminUserToken = siteAdminUsername + " (" + siteAdminEmail + ")";
	private String jsonFileName = "home/preConfiguration_HomeTC2705.json";
	private String siteName = "Home_TC2705_NewSite";
	private String changeModuleNameSiteAdmin = "Task$!te@dmin";
	private String changeModuleNameSystemAdmin = "Task$system@dmin";
	private String searchKey = "task";
	private String randomKey = "ta$%";
	private String formate = "dd MMM YYYY";
	private Map<String, String> toolTip = new LinkedHashMap<>();
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
	private BannerPage bannerWeb;
	private AspConfigurationPage aspConfigurationPage;
	private HomePage homeWeb;
	private AspAdminPage aspAdminWeb;
	private AdminPage adminWeb;
	private SiteNavigationPage siteNavigationWeb;
	private ModulesPage modulesPageWeb;
	private SystemAdminPage systemAdminWeb;
	private BaseIsheetTest baseIsheetTest;
	private AdminIsheetPage adminIsheetWeb;
	private LinkedHashMap<String, String> iSheetData;
	private AdminAddIsheetPage adminAddIsheetWeb;
	private TasksPage tasksWeb;
	private SystemAdminSystemVocabularyPage systemAdminSystemVocabularyWeb;
	private AdminTaskPage adminTaskWeb;
	private String isheetTitleRecordAdded = "Student Data";
	private String title = "title";
	private String addIsheetOption = "iSheet";

	Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	List<String> chartType = new ArrayList<>();
	List<String> chartName = new ArrayList<>();
	List<String> itemName = new ArrayList<>();
	List<String> valueName = new ArrayList<>();
	List<String> aggregationsName = new ArrayList<>();
	List<String> modalList = new ArrayList<>();
	Map<String, Boolean> columnMap = new LinkedHashMap<>();

	private String panelTitle = "Data Visualisation for Task";

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void addTaskVisualisation() throws Exception
	{
		// =====Add ChartName ====//

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0);
		dueDate1 = dateFormat.format(c.getTime());

		c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		dueDate2 = dateFormat.format(c.getTime());

		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		chartType = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartType"), List.class);
		chartName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartName"), List.class);
		itemName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("itemList"), List.class);
		valueName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("valueList"), List.class);

		preconfiguration();
		scenario1CheckTaskOptionInSourceDropDownMenu();
		scenarioTaskModuleNameIsChangedFromSystemVocabularyOrResetFromSiteAdmin();
		scenario2CheckChartListWhenSourceIsSelectedAsTask();
		scenario3CheckChartPreviewLoadsOnSelectingTheChartTypeInDataVisualisationPanel();
		scenario4Case1ForCategoryCheckDifferentTaskFieldsDisplayingInSelectModal();
		scenario4Case2ForValueCheckDifferentTaskFieldsDisplayingInSelectModal();
		scenario4Case3ForSeriesCheckDifferentTaskFieldsDisplayingInSelectModal();
		scenario5CheckSearchableComponentInSelectModal();
		scenario6Case1CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal();
		scenario6Case2CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal();
		scenario6Case3CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal();
		scenario7CheckSearchableComponentSelectAllClearFiltersInFilterWindow();
		scenario8CheckValidationMessageOnChangingTheAggregationType();
		postconfiguration();
	}

	public void scenario1CheckTaskOptionInSourceDropDownMenu()
	{
		Reporter.log("<B> scenario1 Case 1: When Task module is disabled in the site.</B>");
		bannerWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminWeb = gotoAdminModule();
		modulesPageWeb = adminWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(false, HomeLabels.TASKS);

		adminWeb = modulesPageWeb.clickOnSaveButton();
		addVisualisationPanelInDashBoard();

		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SITE, siteName);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);

		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		assertTrue(!homeWeb.verifySourceInDataVisualisation(HomeLabels.TASKS));
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
		Reporter.log("'Task' option should not display in Source drop down menu if Task module is disabled in the selected site.");

		Reporter.log("Case 2: When Task module is enabled in the site.");

		adminWeb = gotoAdminModule();
		modulesPageWeb = adminWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(true, HomeLabels.TASKS);
		adminWeb = modulesPageWeb.clickOnSaveButton();

		addVisualisationPanelInDashBoard();

		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SITE, siteName);

		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		assertTrue(homeWeb.verifySourceInDataVisualisation(HomeLabels.TASKS));

		Reporter.log("'Task' option should display in Source drop down menu when Task module is enabled in the selected site.");
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
		logout();
	}

	public void scenarioTaskModuleNameIsChangedFromSystemVocabularyOrResetFromSiteAdmin()
	{
		try
		{
			Reporter.log("<B> scenario1 Case1 If Task/Isheet module name is changed from 'System vocabulary' or reset from Site Admin</B>");

			bannerWeb = login(siteAdminEmail, sysAdminPassword);
			dashboardWeb = bannerWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminWeb = gotoAdminModule();
			siteNavigationWeb = adminWeb.clickSiteNavigationInLeftPanel();
			siteNavigationWeb.clickOnAutomaticSiteNavigation();
			siteNavigationWeb.clickRenameModules(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
			siteNavigationWeb.renameModule(changeModuleNameSiteAdmin, "", "", "", "", "");
			siteNavigationWeb.clickRenameModalSaveButton();
			siteNavigationWeb.clickSaveonSiteNavigationPage();
			addVisualisationPanelInDashBoard();

			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
			addDataInVisualisation.put(HomeLabels.SITE, siteName);

			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			assertTrue(homeWeb.verifySourceInDataVisualisation(changeModuleNameSiteAdmin));
			homeWeb.clickOnCancel();
			homeWeb.clickOnClose();

			// =====Reset Module Name=====//
			adminWeb = gotoAdminModule();
			siteNavigationWeb = adminWeb.clickSiteNavigationInLeftPanel();
			siteNavigationWeb.clickRenameModules(changeModuleNameSiteAdmin);
			siteNavigationWeb.resetDefaultNameLink();
			siteNavigationWeb.clickRenameModalSaveButton();
			siteNavigationWeb.clickSaveonSiteNavigationPage();
			logout();

			dashboardWeb = (DashboardPage) login(sysAdminEmail, sysAdminPassword);
			systemAdminWeb = dashboardWeb.gotoSystemAdmin();
			systemAdminSystemVocabularyWeb = systemAdminWeb.gotoSystemVocabulary();
			systemAdminSystemVocabularyWeb.changeModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS, changeModuleNameSystemAdmin);
			systemAdminSystemVocabularyWeb.clickOnSave();
			systemAdminSystemVocabularyWeb.clickOnBack();

			dashboardWeb = bannerWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminWeb = gotoAdminModule();
			siteNavigationWeb = adminWeb.clickSiteNavigationInLeftPanel();
			siteNavigationWeb.clickRenameModules(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
			siteNavigationWeb.resetDefaultNameLink();
			siteNavigationWeb.clickRenameModalSaveButton();
			siteNavigationWeb.clickSaveonSiteNavigationPage();

			addVisualisationPanelInDashBoard();
			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
			addDataInVisualisation.put(HomeLabels.SITE, siteName);

			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			assertTrue(homeWeb.verifySourceInDataVisualisation(changeModuleNameSystemAdmin));
		}
		catch (Exception e)
		{
			Reporter.log("Exception" + e);
		}
		finally
		{
			postConfigurationForCaseSystemVocabulary();
		}
	}

	public void scenario2CheckChartListWhenSourceIsSelectedAsTask()
	{
		ArrayList<String> chartTypeNew = new ArrayList<>();

		chartTypeNew.add(HomeLabels.STANDARD_PIE);
		chartTypeNew.add(HomeLabels.STANDARD_PIE_3D);
		chartTypeNew.add(HomeLabels.DOUGHNUT);

		chartTypeNew.add(HomeLabels.STANDARD_COLUMN);
		chartTypeNew.add(HomeLabels.MULTI_SERIES_COLUMN);
		chartTypeNew.add(HomeLabels.STACKED_COLUMN);

		chartTypeNew.add(HomeLabels.STANDARD_BAR);
		chartTypeNew.add(HomeLabels.MULTI_SERIES_BAR);
		chartTypeNew.add(HomeLabels.STACKED_BAR);

		chartTypeNew.add(HomeLabels.STANDARD_LINE);
		chartTypeNew.add(HomeLabels.MULTI_SERIES_LINE);

		chartTypeNew.add(HomeLabels.STANDARD_AREA);
		chartTypeNew.add(HomeLabels.MULTI_SERIES_AREA);
		chartTypeNew.add(HomeLabels.STACKED_AREA);

		Reporter.log("<B> scenario2 Check chart list when source is selected as Task” </B>");
		bannerWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		addVisualisationPanelInDashBoard();

		assertTrue(homeWeb.verifyDefaultSource(HomeLabels.NONE_SELECTED));
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SITE, siteName);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);

		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		Reporter.log("Different charts displaying under Charts drop down should be as below:");

		assertTrue(homeWeb.verifyDefaultChartTypeName(HomeLabels.NONE_SELECTED));
		Reporter.log("chartName" + chartName);
		Reporter.log("chartTypeNew" + chartTypeNew);
		assertTrue(homeWeb.verifyChartType(chartTypeNew, chartName));
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario3CheckChartPreviewLoadsOnSelectingTheChartTypeInDataVisualisationPanel()
	{
		Reporter.log(" <B> scenario3 Check chart preview loads on selecting the chart type in Data Visualisation” panel</B>");

		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);
			assertTrue(homeWeb.verifyChartPreviewFields());
			assertTrue(homeWeb.verifyCustomiseButtonInDisabledMode());
			if (i == 0)
			{
				assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());
			}
			else
			{
				assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode());
				homeWeb.removeToken(HomeLabels.CATEGORY, HomeLabels.TASK_TITLE);
				homeWeb.removeToken(HomeLabels.VALUE, HomeLabels.TASK);
			}
			if (chartType.get(i).equals(HomeLabels.STANDARD_PIE)
					|| chartType.get(i).equals(HomeLabels.STANDARD_COLUMN))
			{
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.CATEGORY));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.VALUE));
				assertTrue(!homeWeb.verifySelectButtonInData(HomeLabels.SERIES));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.FILTER));
			}
			else
			{
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.CATEGORY));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.VALUE));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.SERIES));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.FILTER));
			}

			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);
			assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());

			homeWeb.removeToken(HomeLabels.CATEGORY, HomeLabels.TASK_TITLE);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueName, true);
			}
			else
			{
				homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK);
			}
			assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);
			assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode());
		}

		Reporter.log("1)On selecting the chart from Chart Type drop down, Chart preview area should get loaded. ");
		Reporter.log("2)Static/Dummy chart related to selected Chart Type should load/display in a Chart preview area.");
		Reporter.log("3)Chart preview label should display in top left of the loaded chart preview area. Preview of the selected chart type should display in this preview area.");
		Reporter.log("4)Data & Customise tab should display in the preview area. By Default Data tab should be selected & By default Customise tab should be disabled. ");
		Reporter.log("5)When Data tab is selected,  Category”, Value”, Series” & Filter” labels should display under it along with Select... buttons. (If Pie chart is selected then Series” label will not display) ");
		Reporter.log("6) Preview button should display below all labels. By default Preview button should be disabled.");
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario4Case1ForCategoryCheckDifferentTaskFieldsDisplayingInSelectModal()
	{

		Reporter.log("<B> scenario4 Case1 Check different Task fields displaying in Select modal</B>");
		Reporter.log("Case 1 : Select Category ");

		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();

		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + i + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);

			assertTrue(verifyCategoryAndSeriesModal(HomeLabels.CATEGORY));
			homeWeb.clickOnCloseInModalWindow();
		}
		Reporter.log("1->Category chooser modal should open with modal title as Select - Category”");
		Reporter.log("2->Close button should display in bottom right of the modal to close the modal window.");
		Reporter.log("3->List of: Task title, List, Status, Priority, Assignee, Start date, Due date, Estimated hours, Actual hours should display in the modal.");
		Reporter.log("4->Above those Task field list, auto suggest search field should display to search any item from the list using keywords. Placeholder label of the search field as Search”.");
		Reporter.log("5->Above those list, static label Select one item” should display.");
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario4Case2ForValueCheckDifferentTaskFieldsDisplayingInSelectModal()
	{
		Reporter.log("Case 2 : Select Value ");
		Reporter.log("<B>scenario4 Case2 Check different Task fields displaying in Select modal</B>");

		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + i + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			assertTrue(verifyValueModal(chartType.get(i)));

			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN)
					|| chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{

				homeWeb.clickOnCancelInSelectModalWindow();
			}
			else
			{
				homeWeb.clickOnCloseInModalWindow();
			}
		}
		Reporter.log("1->Value chooser modal should open with modal title as Select – Value”");
		Reporter.log("2->Close/Cancel button should display in bottom right of the modal to close the modal window. ");
		Reporter.log("3->Task, Estimated hours & Actual hours options should display in the modal. (If chart supports multiple field selection on 'Value' then checkbox will display to select multiple fields & Done button will display in bottom right to select all the checked field values)");
		Reporter.log("4->Above those Task field list, auto suggest search field should display to search any item from the list using keywords. Placeholder label of the search field as Search”.");
		Reporter.log("5->If chart supports single field selection on value then above item list, static label Select one item” should display else if chart supports multiple fields selection on value then a label Select one or more items” should display.");
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario4Case3ForSeriesCheckDifferentTaskFieldsDisplayingInSelectModal()
	{
		Reporter.log("Case 3 : Select Series ");
		Reporter.log("<B> scenario4 Case3 Check different Task fields displaying in Select modal</B>");

		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			if (chartType.get(i).equals(HomeLabels.STANDARD_PIE)
					|| chartType.get(i).equals(HomeLabels.STANDARD_COLUMN))
			{
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.CATEGORY));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.VALUE));
				assertTrue(!homeWeb.verifySelectButtonInData(HomeLabels.SERIES));
				assertTrue(homeWeb.verifySelectButtonInData(HomeLabels.FILTER));
				Reporter.log("Verify this chart:-" + i + chartType.get(i));
			}
			else
			{
				homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
				Reporter.log("Click on Select... button of 'Series'");
				assertTrue(verifyCategoryAndSeriesModal(HomeLabels.SERIES));
				Reporter.log("Verify this chart:-" + i + chartType.get(i));
				homeWeb.clickOnCloseInModalWindow();
			}
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario4Case4ForFilterCheckDifferentTaskFieldsDisplayingInSelectModal()
	{

		Reporter.log("Case 1 : Select Filter ");
		Reporter.log("<B> scenario4 Case 4Check different Task fields displaying in Select modal</B>");

		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + i + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			assertTrue(homeWeb.verifySelectModalTitle(HomeLabels.FILTER));
			assertTrue(homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_OR_MORE_ITEMS));
			assertTrue(homeWeb.verifyDoneCancelButtonInModal());
			assertTrue(homeWeb.verifyListInSelectOneOrMoreItemsModalWindow(HomeLabels.TASK, addListInCategoryFilterSeries()));

			homeWeb.clickOnCancelInSelectModalWindow();

		}
		Reporter.log("1->Filter chooser modal should open with modal title as Select - Filter”");
		Reporter.log("2->Cancel button should display in bottom right of the modal to close the modal window.");
		Reporter.log("3->List of: Task title, List, Status, Priority, Assignee, Start date, Due date, Estimated hours, Actual hours should display in the modal along with the checkbox to select multiple filter values.");
		Reporter.log("4->Above those Task field list, auto suggest search field should display to search any item from the list using keywords. Placeholder label of the search field as Search”.");
		Reporter.log("5->Above those list, static label Select one or more items” should display.");

		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario5CheckSearchableComponentInSelectModal()
	{
		// bannerWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		Reporter.log("<B> scenario5 Check searchable component in Select modal</B>");
		addVisualisationPanelInDashBoard();

		addDataInDatavisualisation();
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, HomeLabels.MULTI_SERIES_COLUMN);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		assertTrue(homeWeb.verifySearchInSelectOneItemModal(searchKey));
		homeWeb.clickOnCloseInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		assertTrue(!homeWeb.verifySearchInSelectOneItemModal(randomKey));
		homeWeb.clickOnCloseInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, false));
		homeWeb.clickOnCancelInSelectModalWindow();
		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueName, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(HomeLabels.TASK, true));
		homeWeb.clickOnCancelInSelectModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		assertTrue(homeWeb.verifySearchInSelectOneItemModal(searchKey));
		homeWeb.clickOnCloseInModalWindow();

		List<String> filterItem = new ArrayList<>();
		filterItem.clear();
		filterItem.add(HomeLabels.TASK_TITLE);
		filterItem.add(HomeLabels.LIST);
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, false));
		homeWeb.clickOnCancelInSelectModalWindow();
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, filterItem, true);
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, true));
		homeWeb.clickOnCancelInSelectModalWindow();

		Reporter.log("All Task fields which are matching with the entered keywords in the search field should display as the search result.");

		Reporter.log("If no item name matches with the entered keyword in the search field then it should display a message No results found” in a modal window.");

		Reporter.log("X” icon (to clear the search) should display in end of the search field once user start typing anything in the search field. Tool tip of the icon as Clear search item”. Clicking on the 'Clear search icon' search results should get clear and all Task fields should display again in the modal.");
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario6Case1CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal()
	{

		Reporter.log("<B> scenario6 Case1 Category And Series Check token generation on selecting Task field from the Select modal</B>");
		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");
			verifyTokenInCategoryAndSeries(HomeLabels.CATEGORY, chartType.get(i));
		}

		// =============Select... button of Series.====///

		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");
			if (!(chartType.get(i).equals(HomeLabels.STANDARD_PIE)
					|| chartType.get(i).equals(HomeLabels.STANDARD_COLUMN)))
			{
				verifyTokenInCategoryAndSeries(HomeLabels.SERIES, chartType.get(i));
			}
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario6Case2CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal()
	{

		aggregationsName.clear();
		aggregationsName.add(HomeLabels.COUNT);
		aggregationsName.add(HomeLabels.SUM);
		aggregationsName.add(HomeLabels.AVERAGE);

		Reporter.log("<B> scenario6 Case2 Value Check token generation on selecting Task field from the Select modal</B>");
		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");
			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{
				addDataInVisualisation.clear();
				addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
				homeWeb.addDataInDataVisualisation(addDataInVisualisation);

				for (int k = 0; k < valueName.size(); k++)
				{

					Reporter.log("Tokens of the selected field should generate with Select... button of Value.");

					if (i == 0)
					{
						homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);

						homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, valueName, true);

						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), HomeLabels.COUNT));
					}
					else
					{
						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), HomeLabels.AVERAGE));
					}
					assertTrue(homeWeb.verifyAggregationDropDownValue(valueName.get(k), aggregationsName));
					Reporter.log("User can select single/multiple fields on Value label based on the selected chart type. (For eg: Standard Column chart supports single field selection on Value whereas Stacked Column/Multi-series Column charts supports multiple fields selection)");

					for (int j = 0; j < aggregationsName.size(); j++)
					{

						homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, valueName.get(k), aggregationsName.get(j), true);

						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), aggregationsName.get(j)));

						Reporter.log("Sum, Average & Count options to perform different aggregations on Task data should display in a drop down menu of all selected columns on Value.");
						Reporter.log("By default 'Count' should be selected for all tokens generated on Value. (for eg: if user have selected :'Task' on Value then by default token should generate as 'Count of Task')");
					}
				}
			}
			else
			{
				addDataInVisualisation.clear();
				addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
				homeWeb.addDataInDataVisualisation(addDataInVisualisation);

				for (int k = 0; k < valueName.size(); k++)
				{

					Reporter.log("Tokens of the selected field should generate with Select... button of Value.");

					if (i == 0)
					{
						homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
						homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, valueName.get(k));

						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), HomeLabels.COUNT));
					}
					else
					{
						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), HomeLabels.AVERAGE));
					}
					Reporter.log("User can select single/multiple fields on Value label based on the selected chart type. (For eg: Standard Column chart supports single field selection on Value whereas Stacked Column/Multi-series Column charts supports multiple fields selection)");

					assertTrue(homeWeb.verifyAggregationDropDownValue(valueName.get(k), aggregationsName));

					for (int j = 0; j < aggregationsName.size(); j++)
					{

						homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, valueName.get(k), aggregationsName.get(j), true);
						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, valueName.get(k), aggregationsName.get(j)));

						Reporter.log("Sum, Average & Count options to perform different aggregations on Task data should display in a drop down menu of all selected columns on Value.");
						Reporter.log("By default 'Count' should be selected for all tokens generated on Value. (for eg: if user have selected :'Task' on Value then by default token should generate as 'Count of Task')");
					}
				}
			}
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario6Case3CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal() throws Exception
	{

		Reporter.log("<B> scenario6 Case3 Filter Value Check token generation on selecting Task field from the Select modal</B>");
		// bannerWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		removeTaskAndTaskList();
		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, itemName, true);

			Reporter.log("Select modal should close & tokens of the selected fields should generate below Select... button of Filter.");
			Reporter.log("Token of all selected fields should display with Remove (X) icon to remove the added fields from the Filter.");

			for (int j = 0; j < itemName.size(); j++)
			{
				assertTrue(homeWeb.verifyToken(HomeLabels.FILTER, itemName.get(j), HomeLabels.FILTER));
			}

			// ====Task Title=====//

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.TASK_TITLE));
			// ====List====//
			columnMap.clear();
			columnMap.put(HomeLabels.NONE, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.LIST, columnMap));

			// ====status====//
			columnMap.clear();
			columnMap.put(HomeLabels.COMPLETE, false);
			columnMap.put(HomeLabels.IN_PROGRESS, false);
			columnMap.put(HomeLabels.NOT_STARTED, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.STATUS, columnMap));

			// ====Priority====//
			columnMap.clear();
			columnMap.put(HomeLabels.LOW, false);
			columnMap.put(HomeLabels.NORMAL, false);
			columnMap.put(HomeLabels.HIGH, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.PRIORITY, columnMap));

			// ===assignee===//
			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.ASSIGNEE));

			// ===Due Date===//

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.DUE_DATE));
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();

		addTaskAndList();
		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, itemName, true);

			Reporter.log("Select modal should close & tokens of the selected fields should generate below Select... button of Filter.");
			Reporter.log("Token of all selected fields should display with Remove (X) icon to remove the added fields from the Filter.");

			// ====Task Title=====//
			Reporter.log("Task Title");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.TASK_TITLE, taskTitle1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle1));
			columnMap.clear();
			columnMap.put(taskTitle1, true);
			columnMap.put(taskTitle2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.TASK_TITLE, columnMap));

			// ====List====//
			Reporter.log("List");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.LIST, taskList1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList1));

			columnMap.clear();
			columnMap.put(HomeLabels.NONE, false);
			columnMap.put(taskList1, true);
			columnMap.put(taskList2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.LIST, columnMap));

			// ====status====//
			Reporter.log("status");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.STATUS, HomeLabels.COMPLETE, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.COMPLETE));

			columnMap.clear();
			columnMap.put(HomeLabels.COMPLETE, true);
			columnMap.put(HomeLabels.IN_PROGRESS, false);
			columnMap.put(HomeLabels.NOT_STARTED, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.STATUS, columnMap));

			// ====Priority====//
			Reporter.log("Priority");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.PRIORITY, HomeLabels.LOW, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.LOW));

			columnMap.clear();
			columnMap.put(HomeLabels.LOW, true);
			columnMap.put(HomeLabels.NORMAL, false);
			columnMap.put(HomeLabels.HIGH, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.PRIORITY, columnMap));

			// ===assignee===//
			Reporter.log("Assignee");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.ASSIGNEE, siteAdminUserToken, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.ASSIGNEE, siteAdminUserToken));

			columnMap.clear();
			columnMap.put(siteAdminUserToken, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.ASSIGNEE, columnMap));

			// ===Due Date===//
			Reporter.log("Due Date");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.DUE_DATE, dueDate1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate1));

			columnMap.clear();
			columnMap.put(dueDate1, true);
			columnMap.put(dueDate2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.DUE_DATE, columnMap));

		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario7CheckSearchableComponentSelectAllClearFiltersInFilterWindow() throws Exception
	{

		Reporter.log("<B> Scenario 7 Check searchable component, Select all & Clear filters in Filter window</B>");
		// bannerWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		addVisualisationPanelInDashBoard();
		addDataInDatavisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.TASK, itemName, true);

			// ======Task Title=====//
			Reporter.log("Task Title");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.TASK_TITLE, taskTitle1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle1));

			assertTrue(homeWeb.verifySearchInFilterToken(taskTitle1, HomeLabels.TASK_TITLE, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.TASK_TITLE));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.TASK_TITLE);
			columnMap.clear();
			columnMap.put(taskTitle1, true);
			columnMap.put(taskTitle2, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.TASK_TITLE, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle1));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle2));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.TASK_TITLE);
			columnMap.clear();
			columnMap.put(taskTitle1, false);
			columnMap.put(taskTitle2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.TASK_TITLE, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle1));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.TASK_TITLE, taskTitle2));
			// ======List=====//
			Reporter.log("List");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.LIST, taskList1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList1));
			assertTrue(homeWeb.verifySearchInFilterToken(taskList1, HomeLabels.LIST, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.LIST));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.LIST);
			columnMap.clear();
			columnMap.put(HomeLabels.NONE, true);
			columnMap.put(taskList1, true);
			columnMap.put(taskList2, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.LIST, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList1));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList2));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.LIST, HomeLabels.NONE));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.LIST);
			columnMap.clear();
			columnMap.put(HomeLabels.NONE, false);
			columnMap.put(taskList1, false);
			columnMap.put(taskList2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.LIST, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList1));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.LIST, taskList2));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.LIST, HomeLabels.NONE));

			// ====status====//
			Reporter.log("status");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.STATUS, statusNot, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.STATUS, statusNot));
			assertTrue(homeWeb.verifySearchInFilterToken(statusNot, HomeLabels.STATUS, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.STATUS));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.STATUS);
			columnMap.clear();
			columnMap.put(HomeLabels.COMPLETE, true);
			columnMap.put(HomeLabels.IN_PROGRESS, true);
			columnMap.put(HomeLabels.NOT_STARTED, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.STATUS, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.COMPLETE));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.IN_PROGRESS));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.NOT_STARTED));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.STATUS);
			columnMap.clear();
			columnMap.put(HomeLabels.COMPLETE, false);
			columnMap.put(HomeLabels.IN_PROGRESS, false);
			columnMap.put(HomeLabels.NOT_STARTED, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.STATUS, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.COMPLETE));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.IN_PROGRESS));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.STATUS, HomeLabels.NOT_STARTED));

			// ====Priority====//
			Reporter.log("priority");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.PRIORITY, HomeLabels.LOW, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.LOW));
			assertTrue(homeWeb.verifySearchInFilterToken(HomeLabels.LOW, HomeLabels.PRIORITY, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.PRIORITY));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.PRIORITY);
			columnMap.clear();
			columnMap.put(HomeLabels.HIGH, true);
			columnMap.put(HomeLabels.LOW, true);
			columnMap.put(HomeLabels.NORMAL, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.PRIORITY, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.HIGH));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.LOW));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.NORMAL));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.PRIORITY);
			columnMap.clear();
			columnMap.put(HomeLabels.HIGH, false);
			columnMap.put(HomeLabels.LOW, false);
			columnMap.put(HomeLabels.NORMAL, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.PRIORITY, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.HIGH));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.LOW));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.PRIORITY, HomeLabels.NORMAL));

			// ===assignee===//
			Reporter.log("Assignee");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.ASSIGNEE, siteAdminUserToken, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.ASSIGNEE, siteAdminUserToken));
			assertTrue(homeWeb.verifySearchInFilterToken(siteAdminUsername, HomeLabels.ASSIGNEE, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.ASSIGNEE));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.ASSIGNEE);
			columnMap.clear();
			columnMap.put(siteAdminUserToken, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.ASSIGNEE, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.ASSIGNEE, siteAdminUserToken));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.ASSIGNEE);
			columnMap.clear();
			columnMap.put(siteAdminUserToken, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.ASSIGNEE, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.ASSIGNEE, siteAdminUserToken));

			// ===Due Date===//
			Reporter.log("Due Date");
			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, HomeLabels.DUE_DATE, dueDate1, true);
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate1));
			assertTrue(homeWeb.verifySearchInFilterToken(dueDate1, HomeLabels.DUE_DATE, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, HomeLabels.DUE_DATE));

			homeWeb.clickOnSelectAllInFilterToken(HomeLabels.DUE_DATE);
			columnMap.clear();
			columnMap.put(dueDate1, true);
			columnMap.put(dueDate2, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.DUE_DATE, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate1));
			assertTrue(homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate2));

			homeWeb.clickOnClearFiltersInFilterToken(HomeLabels.DUE_DATE);
			columnMap.clear();
			columnMap.put(dueDate1, false);
			columnMap.put(dueDate2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(HomeLabels.DUE_DATE, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate1));
			assertTrue(!homeWeb.verifyAggregationToken(HomeLabels.DUE_DATE, dueDate2));

		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario8CheckValidationMessageOnChangingTheAggregationType() throws Exception
	{
		String[] itemTitle = {taskTitle1, taskTitle2};
		String[] aggreCount = {"1", "1"};

		aggregationsName.clear();
		aggregationsName.add(HomeLabels.COUNT);
		aggregationsName.add(HomeLabels.SUM);
		aggregationsName.add(HomeLabels.AVERAGE);

		Reporter.log("<B> scenario8 Check validation message on changing the aggregation type</B>");
		homeWeb = bannerWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();

		for (int j = 0; j < aggregationsName.size(); j++)
		{
			homeWeb.removeAllSections();
			homeWeb.clickOnAddSection();
			homeWeb.clickonSingleAddPanelIcon();
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.SITE, siteName);
			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, HomeLabels.DATA_VISUALISATION + HomeLabels.STANDARD_PIE);
			addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, HomeLabels.STANDARD_PIE);
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			Reporter.log("<B> chart Type Name:-" + HomeLabels.STANDARD_PIE + "</B>");
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK);

			homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, HomeLabels.TASK, aggregationsName.get(j), true);
			homeWeb.clickOnPreviewButton();
			if (aggregationsName.get(j).equals(HomeLabels.COUNT))
			{
				toolTip.clear();
				for (int num = 0; num < itemTitle.length; num++)
				{
					toolTip.put(itemTitle[num].trim(), aggreCount[num]);
				}
				Reporter.log(" Pass By test Script  " + toolTip.toString());
				assertTrue(homeWeb.verifyChart(HomeLabels.STANDARD_PIE, toolTip, 1));
				Reporter.log("For token generated on Value its default selected aggregation is 'Count'. (i.e if user have selected 'Task' on Value then its token is generated as Count of Task”)");
				homeWeb.clickOnAdd();
				assertTrue(homeWeb.verifyChart(HomeLabels.STANDARD_PIE, toolTip, 1));
			}
			else
			{
				assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.AGGREGATION_MESSAGE));
				homeWeb.clickOnAdd();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.DATA_VISUALISATION + HomeLabels.STANDARD_PIE, HomeLabels.AGGREGATION_MESSAGE));
			}
		}
		logout();
	}

	private List<String> addListInCategoryFilterSeries()
	{
		modalList.clear();
		modalList.add(HomeLabels.TASK_TITLE);
		modalList.add(HomeLabels.LIST);
		modalList.add(HomeLabels.STATUS);
		modalList.add(HomeLabels.PRIORITY);
		modalList.add(HomeLabels.ASSIGNEE);
		modalList.add(HomeLabels.DUE_DATE);
		return modalList;
	}

	private List<String> addListInValueModal()
	{
		modalList.clear();
		modalList.add(HomeLabels.TASK);
		return modalList;
	}

	/**
	 * @param modalName
	 * @return
	 */
	public boolean verifyCategoryAndSeriesModal(String modalName)
	{
		return homeWeb.verifySelectModalTitle(modalName)
				&& homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_ITEM)
				&& homeWeb.verifyCloseButtonInModal()
				&& homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.TASK, addListInCategoryFilterSeries());
	}

	/**
	 * @param charttype
	 * @return
	 */
	public boolean verifyValueModal(String charttype)
	{
		if (charttype.equals(HomeLabels.MULTI_SERIES_COLUMN)
				|| charttype.equals(HomeLabels.STACKED_COLUMN))
		{

			return homeWeb.verifySelectModalTitle(HomeLabels.VALUE)
					&& homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_OR_MORE_ITEMS)
					&& homeWeb.verifyDoneCancelButtonInModal()
					&& homeWeb.verifyListInSelectOneOrMoreItemsModalWindow(HomeLabels.TASK, addListInValueModal());
		}
		else
		{
			return homeWeb.verifySelectModalTitle(HomeLabels.VALUE)
					&& homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_ITEM)
					&& homeWeb.verifyCloseButtonInModal()
					&& homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.TASK, addListInValueModal());
		}
	}

	public void addVisualisationPanelInDashBoard()
	{
		homeWeb = bannerWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);

	}

	public void addDataInDatavisualisation()
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.TASKS);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);
	}

	public void addTaskAndList() throws Exception
	{
		adminWeb = gotoAdminModule();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();

		adminTaskWeb.enterTaskListName(taskList1);
		adminTaskWeb.enterTaskListName(taskList2);
		tasksWeb = gotoTaskModule();

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

	public void removeTaskAndTaskList() throws Exception
	{
		adminWeb = bannerWeb.gotoAdminModule();
		adminTaskWeb = adminWeb.clickOnTaskInLeftPanel();
		adminTaskWeb.removeAllTaskLists();
		tasksWeb = adminTaskWeb.gotoTaskModule();
		tasksWeb.deleteAllTasks();
	}

	/**
	 * @param selectButtonName
	 * @param chartType
	 */
	public void verifyTokenInCategoryAndSeries(String selectButtonName, String chartType)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);
		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

		Reporter.log("Click on Select... button of Category.");
		Reporter.log("Click on any Task field to select the category.");
		Reporter.log("Clicking on any Task field, 'Select- Category' modal should close and the token of the selected field should generate with the Select... button of Category.");

		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.TASK_TITLE, ""));

		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.TASK_TITLE);

		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.TASK_TITLE, ""));
		Reporter.log("Again click on Select... button of Category when task field is already selected.");
		Reporter.log("Select any other field from the 'Select- Category' modal.");
		Reporter.log("Token of the new selected field should generate with Select button of Category label & it will over-right the previously selected field token.");

		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.TASK, HomeLabels.LIST);

		assertTrue(!homeWeb.verifyToken(selectButtonName, HomeLabels.TASK_TITLE, ""));
		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.LIST, ""));

		Reporter.log("User should be able to select only one Task field on the Category. ");

		homeWeb.removeToken(selectButtonName, HomeLabels.LIST);
		assertTrue(!homeWeb.verifyToken(selectButtonName, HomeLabels.LIST, ""));
		Reporter.log("Selected field token should be removed from the Category and user should be able to select again the Task field for the Category.");

	}

	public void preconfiguration() throws Exception
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		dashboardWeb = gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDataVisualisationPanel(true);
		aspConfigurationPage.saveConfigurations();
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);
		logout();
	}

	public void addIsheet()
	{
		baseIsheetTest = new BaseIsheetTest(driver);
		adminWeb = gotoAdminModule();
		adminIsheetWeb = adminWeb.clickiSheetsInLeftPanel();
		if (adminIsheetWeb.isheetExist(isheetTitleRecordAdded))
		{
			adminIsheetWeb.deleteIsheet(isheetTitleRecordAdded);
		}
		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		iSheetData = new LinkedHashMap<>();
		iSheetData.put(title, isheetTitleRecordAdded);
		adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, null);
	}

	public void postconfiguration() throws Exception
	{
		login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminWeb = gotoAdminModule();
		modulesPageWeb = adminWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(false, "task");
		modulesPageWeb.clickOnSaveButton();
		logout();
	}

	public void postConfigurationForCaseSystemVocabulary()
	{
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
		systemAdminWeb = homeWeb.gotoSystemAdmin();
		systemAdminWeb.gotoSystemVocabulary();
		systemAdminSystemVocabularyWeb.resetAllModuleName();
		systemAdminSystemVocabularyWeb.clickOnSave();

		dashboardWeb = bannerWeb.gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);
		adminWeb = gotoAdminModule();
		siteNavigationWeb = adminWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickRenameModules(changeModuleNameSystemAdmin);
		siteNavigationWeb.resetDefaultNameLink();
		siteNavigationWeb.clickRenameModalSaveButton();
		siteNavigationWeb.clickSaveonSiteNavigationPage();
		logout();
	}
}
