package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highq.labels.collaborate.AdminIsheetLabels;
import com.highq.labels.collaborate.AdminIsheetManageViewLabels;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditViewPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageViewsPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetAddViewWeb;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;
import com.highq.test.iSheet.BaseIsheetTest;

public class HomeTC2912 extends BannerPageWeb
{

	private WebDriver driver;
	private String sysAdminEmail = "krishna.bhadani@highq.com";

	private String sysAdminPassword = "Admin@123";
	private String siteName = "Home_TC2912";
	private HomePage homeWeb;
	String jsonFileName = "home/preConfiguration_HomeTC2912.json";

	private DashboardPage dashboardWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationPage;
	private IsheetsPage isheetsWeb;
	private AdminPage adminPageWeb;
	private AdminIsheetPage adminIsheetWeb;
	private AdminAddIsheetPage adminAddIsheetWeb;
	private AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	private AdminIsheetManageViewsPage adminIsheetManageViewWeb;
	private AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	private AdminIsheetAddViewWeb adminIsheetAddViewWeb;
	private BaseIsheetTest baseIsheetTest;
	private BannerPage bannerPageWeb;
	private AdminIsheetEditViewPage adminIsheetEditViewWeb;
	private LinkedHashMap<String, String> iSheetData;
	DocumentAddDataPage addDoc;
	IsheetData isheetData;
	Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	List<String> modalList = new ArrayList<>();
	List<String> aggregationsName = new ArrayList<>();

	// ==isheets data==//
	private String fullRecord = "isheets1";
	private String viewName = "View1";
	private String addIsheetOption = "iSheet";
	private String title = "title";
	private String companyName = "Company Name";
	private String month = "Month";
	private String sales = "Sales";

	private String columnName = "column name";
	private String columnType = "column type";
	private String[] companyList;
	private String[] monthList;
	private String[] salesList;
	String[] name;
	private String siteAdminEmail;
	String panelTitle = "Data Visualisation for iSheets";
	List<String> filterData = new LinkedList<>();
	List<String> valueData = new LinkedList<>();
	private String singleLineColumnType = AdminIsheetLabels.SINGLE_LINE_TEXT_COLUMN;
	private String numberColumnType = AdminIsheetLabels.NUMBER;

	private String isheetActionDelete = IsheetLabels.ISHEET_DELETERECORD;
	private String deleteView = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_MOREACTIONS_DELETE;

	private String customModalDeleteRecordTitle = IsheetLabels.ISHEET_DELETE_DELETEMESSAGETITLE;

	private Map<String, Object> columnData = new LinkedHashMap<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws Exception
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Test(priority = 1)
	public void HomeTC2912IsheetColumnIsDeleted() throws Exception
	{
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		companyList = new ObjectMapper().convertValue(resultsFile.get("IsheetData").get("companyName"), String[].class);
		monthList = new ObjectMapper().convertValue(resultsFile.get("IsheetData").get("monthName"), String[].class);
		salesList = new ObjectMapper().convertValue(resultsFile.get("IsheetData").get("salesName"), String[].class);
		name = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("siteUsers"), String[].class);
		siteAdminEmail = name[0];
		preconfiguration();
		Scenario1CheckPlottedChartInDataVisualisationPanelfAnyIsheetColumnIsDeletedForWhichChartIsPlotted();
		Scenario2CheckPlottedChartInDataVisualisationPanelIfSelectedIsheetIsheetViewIsDeletedForWhichChartIsPlotted();
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
		addIsheet();
		logout();
	}

	public void Scenario1CheckPlottedChartInDataVisualisationPanelfAnyIsheetColumnIsDeletedForWhichChartIsPlotted()
	{
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		// ==category select company name==//
		addVisualisationPanelInDashBoard();
		addDataVisualisation(HomeLabels.CATEGORY + " " + companyName, HomeLabels.DEFAULT, companyName, HomeLabels.CREATED_BY, HomeLabels.SUM, sales, month, "January");

		// ==value select company name==//
		homeWeb.clickOnAddSection();
		homeWeb.clickOnMultiplePanelIcon(2);
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataVisualisation(HomeLabels.VALUE + " " + companyName, HomeLabels.DEFAULT, HomeLabels.CREATED_BY, companyName, HomeLabels.COUNT, sales, month, "January");

		// ==series select company name==//
		homeWeb.clickOnAddSection();
		homeWeb.clickOnMultiplePanelIcon(3);
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataVisualisation(HomeLabels.SERIES + " " + companyName, HomeLabels.DEFAULT, HomeLabels.CREATED_BY, HomeLabels.CREATED_BY, HomeLabels.COUNT, companyName, month, "January");

		// ==series select company name2==//
		homeWeb.clickOnAddSection();
		homeWeb.clickOnMultiplePanelIcon(3);
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataVisualisation(HomeLabels.SERIES + " " + companyName + " " + HomeLabels.SUM, HomeLabels.DEFAULT, HomeLabels.CREATED_BY, HomeLabels.CREATED_BY, HomeLabels.SUM, companyName, month, "January");

		// ==filter select company name==//
		homeWeb.clickOnAddSection();
		homeWeb.clickOnMultiplePanelIcon(4);
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataVisualisation(HomeLabels.FILTER + " " + companyName, HomeLabels.DEFAULT, HomeLabels.CREATED_BY, HomeLabels.CREATED_BY, HomeLabels.COUNT, sales, companyName, "Honda");

		homeWeb.clickOnSave();
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(fullRecord, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(companyName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();

		homeWeb = adminIsheetManageColumnWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();

		assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.CATEGORY + " " + companyName, HomeLabels.DATA_UNAVAILABLE_MESSAGE));
		assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.VALUE + " " + companyName, HomeLabels.DATA_UNAVAILABLE_MESSAGE));
		assertTrue(!homeWeb.verifyErrorMessageInDashBoard(HomeLabels.SERIES + " " + companyName, HomeLabels.AGGREGATION_MESSAGE));
		assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.SERIES + " " + companyName + " " + HomeLabels.SUM, HomeLabels.AGGREGATION_MESSAGE));
		assertTrue(homeWeb.verifyErrorMessageInDashBoard(HomeLabels.FILTER + " " + companyName, HomeLabels.DATA_UNAVAILABLE_MESSAGE));

		verifyEditPanel(HomeLabels.CATEGORY + " " + companyName, HomeLabels.CATEGORY, " ");

		verifyEditPanel(HomeLabels.VALUE + " " + companyName, HomeLabels.VALUE, HomeLabels.COUNT);

		verifyEditPanel(HomeLabels.SERIES + " " + companyName, HomeLabels.SERIES, " ");

		verifyEditPanel(HomeLabels.SERIES + " " + companyName + " " + HomeLabels.SUM, HomeLabels.SERIES, " ");

		verifyEditPanel(HomeLabels.FILTER + " " + companyName, HomeLabels.FILTER, HomeLabels.FILTER);

		logout();
	}

	public void Scenario2CheckPlottedChartInDataVisualisationPanelIfSelectedIsheetIsheetViewIsDeletedForWhichChartIsPlotted()
	{
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		addVisualisationPanelInDashBoard();
		addDataVisualisation(panelTitle + " " + viewName + HomeLabels.VIEW, viewName, HomeLabels.CREATED_BY, HomeLabels.CREATED_BY, HomeLabels.COUNT, sales, month, "February");
		homeWeb.clickOnSave();

		adminPageWeb = homeWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageViewWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(fullRecord, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS);
		adminIsheetManageViewWeb.selectOptionOnViewMoreAction(viewName, deleteView);
		adminIsheetManageViewWeb.clickDeleteOnDeleteViewModal();

		homeWeb = adminIsheetManageViewWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle + " " + viewName + HomeLabels.VIEW, HomeLabels.DATA_UNAVAILABLE_MESSAGE));

		homeWeb.clickOnMoreActionOption(panelTitle + " " + viewName + HomeLabels.VIEW, HomeLabels.EDIT);
		assertTrue(!homeWeb.verifyChartPreviewFields());
		assertTrue(!homeWeb.verifyView(viewName));
		assertTrue(homeWeb.verifyDefaultView(HomeLabels.NONE_SELECTED));
		assertTrue(homeWeb.verifyDefaultISheet(fullRecord));
		assertTrue(homeWeb.verifyDefaultSite(siteName));

		homeWeb.clickOnCancelInModalWindow();

		addVisualisationPanelInDashBoard();
		addDataVisualisation(panelTitle + " " + fullRecord, HomeLabels.DEFAULT, HomeLabels.CREATED_BY, HomeLabels.CREATED_BY, HomeLabels.COUNT, sales, month, "February");
		homeWeb.clickOnSave();

		adminPageWeb = homeWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetWeb.deleteIsheet(fullRecord);

		homeWeb = adminIsheetWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle + " " + fullRecord, HomeLabels.DATA_UNAVAILABLE_MESSAGE));

		homeWeb.clickOnMoreActionOption(panelTitle + " " + fullRecord, HomeLabels.EDIT);
		assertTrue(!homeWeb.verifyChartPreviewFields());
		assertTrue(homeWeb.verifyViewButtonInDisabledMode());
		assertTrue(homeWeb.verifyDefaultView(HomeLabels.NONE_SELECTED));
		assertTrue(homeWeb.verifyDefaultISheet(HomeLabels.NONE_SELECTED));
		assertTrue(homeWeb.verifyDefaultSite(siteName));
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

	public void addDataInDatavisualisation(String charttype, String panelTitle, String viewName)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SITE, siteName);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, fullRecord);
		addDataInVisualisation.put(HomeLabels.VIEW, viewName);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, charttype);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);
	}

	public void verifyEditPanel(String panelName, String selectButtonName, String agg)
	{
		homeWeb.clickOnMoreActionOption(panelName, HomeLabels.EDIT);
		if (selectButtonName.equals(HomeLabels.SERIES) || selectButtonName.equals(HomeLabels.FILTER))
		{
			if (panelName.equals(HomeLabels.SERIES + " " + companyName + " " + HomeLabels.SUM))
			{
				assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.AGGREGATION_MESSAGE));
				assertTrue(homeWeb.verifyCustomiseButtonInDisabledMode());
				assertTrue(!homeWeb.verifyToken(selectButtonName, companyName, agg));
				assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode());
				assertTrue(!homeWeb.verifySaveButtonsInDisabledMode());
			}
			else
			{
				assertTrue(homeWeb.verifyChartPreviewFields());
				assertTrue(!homeWeb.verifyCustomiseButtonInDisabledMode());
			}
		}
		else
		{
			assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.NO_DATA_TO_DISPLAY));
			assertTrue(homeWeb.verifyCustomiseButtonInDisabledMode());
			assertTrue(!homeWeb.verifyToken(selectButtonName, companyName, agg));
			assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());
			assertTrue(homeWeb.verifySaveButtonsInDisabledMode());
		}

		homeWeb.clickOnCancelInModalWindow();
	}

	public void addDataVisualisation(String panelTitle, String viewName, String categoryItem, String valueItem, String aggValue, String seriesItem, String filterItem, String aggregationsName)
	{
		filterData.clear();
		filterData.add(filterItem);

		valueData.clear();
		valueData.add(valueItem);

		addDataInDatavisualisation(HomeLabels.MULTI_SERIES_COLUMN, panelTitle, viewName);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, categoryItem);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, valueData, true);
		homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, valueItem, aggValue, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, seriesItem);

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, filterData, true);
		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, filterItem, aggregationsName, true);
		homeWeb.clickOnAdd();
	}

	public void addIsheet() throws Exception
	{

		baseIsheetTest = new BaseIsheetTest(driver);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (!adminIsheetWeb.isheetExist(fullRecord))
		{
			adminIsheetWeb.clickOnAddIsheet();
			adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
			baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
			iSheetData = new LinkedHashMap<>();
			iSheetData.put(title, fullRecord);
			adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, null);
		}

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(fullRecord, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS);
		columnAdd();
		adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManageViewWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(fullRecord, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS);

		if (!adminIsheetManageViewWeb.verifyView(viewName))
		{
			adminIsheetAddViewWeb = adminIsheetManageViewWeb.clickAddView();
			adminIsheetAddViewWeb.enterViewName(viewName);
			adminIsheetAddViewWeb.selectAvailableColumns(companyName, sales, month, HomeLabels.CREATED_BY, HomeLabels.CREATED_DATE);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewWeb = adminIsheetAddViewWeb.clickSaveOnAddView();
		}
		else
		{
			adminIsheetEditViewWeb = adminIsheetManageViewWeb.selectView(viewName);
			if (!adminIsheetEditViewWeb.verifySelectedColumns(companyName, sales, month, HomeLabels.CREATED_BY, HomeLabels.CREATED_DATE))
			{
				if (adminIsheetEditViewWeb.verifyAvailableColumns(companyName, sales, month, HomeLabels.CREATED_BY, HomeLabels.CREATED_DATE))
				{
					adminIsheetEditViewWeb.selectAvailableColumns(companyName, sales, month, HomeLabels.CREATED_BY, HomeLabels.CREATED_DATE);
					adminIsheetEditViewWeb.clickMoveRightSelected();
					assertTrue(adminIsheetEditViewWeb.verifySelectedColumns(companyName, sales, month, HomeLabels.CREATED_BY, HomeLabels.CREATED_DATE));
				}
			}
			adminIsheetManageViewWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		}

		isheetData = new IsheetData();
		isheetsWeb = adminPageWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(fullRecord);

		if (!isheetsWeb.verifyIsheetRecordsEmpty())
		{
			isheetsWeb.setAllIsheetRecordsCheckbox(true);
			isheetsWeb.selectIsheetActions(isheetActionDelete);
			isheetsWeb.clickOKOnCustomModal(customModalDeleteRecordTitle);
		}

		isheetsWeb.clickAdd();
		for (int i = 0; i < companyList.length; i++)
		{
			Map<String, String> singleLineText = new LinkedHashMap<>();
			singleLineText.clear();
			singleLineText.put(companyName, companyList[i]);
			singleLineText.put(month, monthList[i]);

			Map<String, String> number = new LinkedHashMap<>();
			number.clear();
			number.put(sales, salesList[i]);

			isheetData.setSingleLineText(singleLineText);
			isheetData.setNumber(number);

			String[] columnTypesAddRecord = {"single line text", "single line text", "number"};
			isheetsWeb.addRecord(columnTypesAddRecord, isheetData);
			isheetsWeb.setAddAnotherRecordChkBox(true);
			if (i + 1 == companyList.length)
			{
				isheetsWeb.setAddAnotherRecordChkBox(false);
			}
			isheetsWeb.clickAddOnAddRecordModal();
		}

	}

	public void columnAdd() throws Exception
	{
		if (!adminIsheetManageColumnWeb.verifyColumnExist(companyName))
		{
			adminIsheetManageColumnWeb = createSingleLineTextColumn(companyName);
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(month))
		{
			adminIsheetManageColumnWeb = createSingleLineTextColumn2(month);
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(sales))
		{
			adminIsheetManageColumnWeb = createNumberColumn(sales);
		}

	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn(String isheetTitle) throws InterruptedException
	{

		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, companyName);
		columnData.put(columnType, singleLineColumnType);

		return baseIsheetTest.addSingleLineTextColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn2(String isheetTitle) throws InterruptedException
	{

		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, month);
		columnData.put(columnType, singleLineColumnType);

		return baseIsheetTest.addSingleLineTextColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createNumberColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, sales);
		columnData.put(columnType, numberColumnType);

		return baseIsheetTest.addNumberColumn(columnData);
	}

}
