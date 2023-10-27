package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.highq.labels.collaborate.AdminIsheetLabels;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.iSheetPageData.AddCalculationColumnData;
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
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetAddViewWeb;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;
import com.highq.test.iSheet.BaseIsheetTest;

public class HomeTC1969 extends BannerPageWeb
{

	private WebDriver driver;
	private String sysAdminEmail = "krishna.bhadani@highq.com";

	private String sysAdminPassword = "Admin@123";
	private String siteUserName = "Krishna Bhadani";
	private String createdTokenName = siteUserName + " (" + sysAdminEmail + ")";
	private String siteAdminEmail = "site.adminhome@highq.com";
	private String siteAdminUserName = "Site Adminhome";
	private String siteName = "Home_TC1969";
	String jsonFileName = "home/preConfiguration_HomeTC1969.json";

	private DashboardPage dashboardWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationPage;
	private HomePage homeWeb;
	private IsheetsPage isheetsWeb;
	private BannerPage bannerPageWeb;
	private AdminPage adminPageWeb;
	private AdminIsheetPage adminIsheetWeb;
	private AdminAddIsheetPage adminAddIsheetWeb;
	private AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	private AdminIsheetManageViewsPage adminIsheetManageViewWeb;
	private AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	private AdminIsheetAddViewWeb adminIsheetAddViewWeb;
	private BaseIsheetTest baseIsheetTest;
	private AdminIsheetEditViewPage adminIsheetEditViewWeb;
	private AddCalculationColumnData addCalculationColumnData;
	private DocumentPage documentWeb;
	private LinkedHashMap<String, String> iSheetData;
	DocumentAddDataPage addDoc;
	IsheetData isheetData;
	Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	List<String> modalList = new ArrayList<>();
	List<String> aggregationsName = new ArrayList<>();

	// ==isheets data==//
	private String isheetTitleRecordAdded = "Student Data";
	private String isheetTitleRecordNotAdded = "isheet Data";
	private String isheetLink = "isheet Data (Home_TC1969)";

	private String DisplayiniSheetlist = "Display in iSheet list";
	private String allowlookups = "Allow lookups";
	private String addIsheetOption = "iSheet";
	private String title = "title";
	private String isheet = "iSheet";
	private String searchKey = "stu";
	private String randomKey = "$tu%";
	String[] columnTypesAddRecord = {"single line text", "multiple line text", "number", "date and time", "user lookup", "hyperlink", "image", "attachment",
			"file link", "folder link", "choice"};
	String[] columnTypesAddRecord2 = {"single line text", "multiple line text", "number", "date and time", "user lookup"};
	String[] columnTypesAddRecord3 = {"single line text", "number", "date and time", "user lookup"};

	private String columnName = "column name";
	private String columnType = "column type";
	private String addChoice = "add choice";
	private String studentName = "Student Name";
	private String singleLineColumnType = AdminIsheetLabels.SINGLE_LINE_TEXT_COLUMN;

	private String studentId = "Student ID";
	private String numberColumnType = AdminIsheetLabels.NUMBER;
	private String folderDescription = "folderDescription";

	private String address = "Address";
	private String multiLineColumnType = AdminIsheetLabels.MULTIPLE_LINE_TEXT_COLUMN;

	private String dateAndTimeColumnName = "Date and Time Column";
	private String dateAndTimeColumnType = AdminIsheetLabels.DATE_AND_TIME_COLUMN;

	private String calculationColumnName = "Calculation Column";
	private String calculationColumnType = AdminIsheetLabels.CALCULATION_COLUMN;
	private String calculationFormula = studentId + "+" + studentId;
	private String choiceColumnName = "Choice Column";
	private String choiceColumnType = AdminIsheetLabels.CHOICE_COLUMN;

	private String autoIncrementColumnName = "Auto increment Column";
	private String autoIncrementColumnType = AdminIsheetLabels.AUTO_INCREMENT_COLUMN;

	private String userLookupColumnName = "User Lookup Column";
	private String userLookupColumnType = AdminIsheetLabels.USER_LOOKUP_COLUMN;

	private String hyperlinkColumnName = "Hyperlink Column";
	private String hyperlinkColumnType = AdminIsheetLabels.HYPERLINK_COLUMN;

	private String imageColumnName = "Image Column";
	private String imageColumnType = AdminIsheetLabels.IMAGE_COLUMN;

	private String attachmentColumnName = "Attachment Column";
	private String attachmentColumnType = AdminIsheetLabels.ATTACHMENT_COLUMN;

	private String fileLinkColumnName = "File link Column";
	private String fileLinkColumnType = AdminIsheetLabels.FILE_LINK_COLUMN;

	private String folderLinkColumnName = "Folder link Column";
	private String folderLinkColumnType = AdminIsheetLabels.FOLDER_LINK_COLUMN;

	private String isheetLinkColumnName = "ISheet link Column";
	private String iSheetLinkColumnType = AdminIsheetLabels.ISHEET_LINK_COLUMN;
	private String iShhetLinkDefaultValue = "iSheet link default value";
	private String defaultlinkvalue = "default link value";
	private String defaultValue = HomeLabels.DEFAULT;

	private String lookupColumnName = "Lookup Column";
	private String lookupColumnType = AdminIsheetLabels.LOOKUP_COLUMN;

	private String joinColumnName = "Join Column";
	private String joinColumnType = AdminIsheetLabels.JOIN_COLUMN;

	String singleLineColumnData1 = "Student Name-first";
	String legendsName = singleLineColumnData1 + "(Student ID)";
	String multiLineColumnData1 = "Ahmedabad";
	String numberColumnData1 = "01";

	String singleLineColumnData2 = "Student Name-second";
	String multiLineColumnData2 = "Ahmedabad";
	String numberColumnData2 = "02";

	String[] userLookupData = {siteAdminUserName};
	String[] filesToUpload = {"doc1.txt"};
	String[] folderNames = {"Test Folder1"};
	String hyperLinkDisplayName = "Google Link";
	String hyperLinkURL = "https://www.google.co.in";
	String imageData = "automation.jpg";
	String[] attachmentData = {filesToUpload[0]};
	String isheetLinkDataSheet = isheetLink;
	String[] files = {filesToUpload[0]};
	String[] folders = {folderNames[0]};
	String isheetActionDelete = IsheetLabels.ISHEET_DELETERECORD;
	String customModalDeleteRecordTitle = IsheetLabels.ISHEET_DELETE_DELETEMESSAGETITLE;
	String[] testRecord1 = {"Test Column 1 Record 1", "Test Column 2 Record 1", "Test Column 3 Record 1"};

	String[] addMenuItem = {"Files", "New folder"};
	String[] multiFiles = {"doc1.txt"};

	private String viewName = "View1";
	private String panelTitle = "Data Visualisation for iSheet ";
	private String redColorCode = "#FF0000";
	private String blueColorCode = "#0000ff";
	private String choice1 = "Choice1";
	private String choice2 = "Choice2";

	List<String> chartType = new ArrayList<>();
	List<String> chartName = new ArrayList<>();
	Map<String, Object> columnData = new LinkedHashMap<>();

	LinkedHashMap<String, Boolean> isheetDataMap = new LinkedHashMap<>();
	List<String> isheetList = new ArrayList<>();
	Map<String, Boolean> columnMap = new LinkedHashMap<>();
	private String formate = "dd MMM YYYY";
	DateFormat dateFormat = new SimpleDateFormat(formate);
	private String dateAndTimeColumnData;

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
	public void HomeTC1969CheckFunctionalityOfDataVisualisationPanel() throws Exception
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0);
		dateAndTimeColumnData = dateFormat.format(c.getTime());

		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		chartType = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartType"), List.class);
		chartName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartName"), List.class);

		preconfiguration();
		scenario1CheckDifferentChartTypeDisplayingInDataVisualisationPanel();
		scenario2Case1CheckChartPreviewLoadsOnSelectingTheChartTypeInDataVisualisationPanel();
		scenario2Case2AndScenario3CheckDifferentIsheetColumnTypesDisplayingInSelectModal();
		scenario4CheckSearchableComponentForColumnChooserInDataVisualisationPanel();
		scenario5Case1CheckColumnSelectionInDataVisualisationPanel();
		scenario5Case2CheckColumnSelectionInDataVisualisationPanel();
		scenario5Case3CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal();
		scenario5Case4CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal();
		scenario6HideShowThePlottedChartByDisablingEnablingTheLegendsInDataVisualisationPanel();
		scenario7RemoveTheColumnTokensFromTheChartsAndVerifyTheMessageInChartPreviewArea();
		scenario8CheckTokenGenerationOnColumnSelectionWhenAnyRecordDoesNotExistInSelectedColumn();

	}

	public void preconfiguration() throws Exception
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		dashboardWeb = gotoDashboardByClickOnLogo();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDataVisualisationPanel(true);
		aspConfigurationPage.saveConfigurations();

		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);
		addFileAndFolder();
		addIsheetAndView();
		logout();
	}

	public void scenario1CheckDifferentChartTypeDisplayingInDataVisualisationPanel()
	{
		ArrayList<String> chartTypeNew = new ArrayList<>();

		Reporter.log("<font color='red'><B> scenario1 Check different chart type displaying in Data Visualisation” panel. </B></font>");

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

		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);
		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();

		assertTrue(homeWeb.verifyDefaultChartTypeName(HomeLabels.NONE_SELECTED));
		Reporter.log("chartName" + chartName);
		Reporter.log("chartTypeNew" + chartTypeNew);
		assertTrue(homeWeb.verifyChartType(chartTypeNew, chartName));
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario2Case1CheckChartPreviewLoadsOnSelectingTheChartTypeInDataVisualisationPanel()
	{
		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		Reporter.log("<font color='red'><B> scenario2 Case1 Check chart preview loads on selecting the chart type in Data Visualisation” panel. </B></font>");
		isheetList.clear();
		isheetList.add(studentId);
		isheetList.add(studentName);

		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
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
				homeWeb.removeToken(HomeLabels.CATEGORY, HomeLabels.CREATED_BY);
				homeWeb.removeToken(HomeLabels.VALUE, studentId);
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
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, HomeLabels.CREATED_BY);
			assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());

			homeWeb.removeToken(HomeLabels.CATEGORY, HomeLabels.CREATED_BY);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);
			}
			else
			{
				homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
			}
			assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode());
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, HomeLabels.CREATED_BY);
			assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode());
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario2Case2AndScenario3CheckDifferentIsheetColumnTypesDisplayingInSelectModal()
	{
		Reporter.log("<font color='red'><B> scenario2 Case2  Check different Isheet column types displaying in Select modal. and Scenario3 Check Isheet column selection on different labels in  Data Visualisation” panel.</B></font>");
		isheetList.clear();
		isheetList.add(studentId);
		isheetList.add(studentName);

		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			assertTrue(verifyOneItemModal(HomeLabels.CATEGORY));
			Reporter.log(HomeLabels.CATEGORY + "isheetList" + isheetList());
			assertTrue(!homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.ISHEET, isheetListNotDisplay()));
			Reporter.log(HomeLabels.CATEGORY + "isheetListNotDisplay" + isheetListNotDisplay());
			homeWeb.clickOnCloseInModalWindow();

			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN)
					|| chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{
				homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
				assertTrue(verifyOneOrMoreItemsModal(HomeLabels.VALUE));
				Reporter.log(HomeLabels.VALUE + "isheetList" + isheetList());
				assertTrue(!homeWeb.verifyListInSelectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetListNotDisplay()));
				Reporter.log(HomeLabels.VALUE + "isheetListNotDisplay" + isheetListNotDisplay());

				homeWeb.clickOnCancelInSelectModalWindow();
			}
			else
			{
				homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
				assertTrue(verifyOneItemModal(HomeLabels.VALUE));
				Reporter.log(HomeLabels.VALUE + "isheetList" + isheetList());
				assertTrue(!homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.ISHEET, isheetListNotDisplay()));
				Reporter.log(HomeLabels.VALUE + "isheetListNotDisplay" + isheetListNotDisplay());
				homeWeb.clickOnCloseInModalWindow();
			}
			if (!(chartType.get(i).equals(HomeLabels.STANDARD_PIE)
					|| chartType.get(i).equals(HomeLabels.STANDARD_COLUMN)))
			{
				homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
				assertTrue(verifyOneItemModal(HomeLabels.SERIES));
				Reporter.log(HomeLabels.SERIES + "isheetList" + isheetList());
				assertTrue(!homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.ISHEET, isheetListNotDisplay()));
				Reporter.log(HomeLabels.SERIES + "isheetListNotDisplay" + isheetListNotDisplay());
				homeWeb.clickOnCloseInModalWindow();
			}

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			assertTrue(verifyOneOrMoreItemsModal(HomeLabels.FILTER));
			Reporter.log(HomeLabels.FILTER + "isheetList" + isheetList());
			assertTrue(!homeWeb.verifyListInSelectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetListNotDisplay()));
			Reporter.log(HomeLabels.FILTER + "isheetListNotDisplay" + isheetListNotDisplay());
			homeWeb.clickOnCancelInSelectModalWindow();
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario4CheckSearchableComponentForColumnChooserInDataVisualisationPanel()
	{
		Reporter.log("<font color='red'><B> scenario4  Check searchable component for column chooser in Data visualisation panel.</B></font>");
		isheetList.clear();
		isheetList.add(studentId);
		isheetList.add(studentName);

		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
		addDataInVisualisation.clear();
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
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, true));
		homeWeb.clickOnCancelInSelectModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		assertTrue(homeWeb.verifySearchInSelectOneItemModal(searchKey));
		homeWeb.clickOnCloseInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, false));
		homeWeb.clickOnCancelInSelectModalWindow();
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		assertTrue(homeWeb.verifySearchInSelectOneOrMoreItemModal(searchKey, true));
		homeWeb.clickOnCancelInSelectModalWindow();
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario5Case1CheckColumnSelectionInDataVisualisationPanel()
	{
		Reporter.log("<font color='red'><B> scenario5 Case 1 Category And Series Check column selection in Data Visualisation” panel</B></font>");
		isheetList.clear();
		isheetList.add(studentId);
		isheetList.add(studentName);

		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);
			verifyTokenInCategoryAndSeries(HomeLabels.CATEGORY, chartType.get(i));
		}
		// =============Select... button of Series.====///
		for (int i = 0; i < chartType.size(); i++)
		{
			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);
			if (chartType.get(i).equals(HomeLabels.STANDARD_PIE)
					|| chartType.get(i).equals(HomeLabels.STANDARD_COLUMN))
			{
				assertTrue(!homeWeb.verifySelectButtonInData(HomeLabels.SERIES));
			}
			else
			{
				Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");
				verifyTokenInCategoryAndSeries(HomeLabels.SERIES, chartType.get(i));

			}
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario5Case2CheckColumnSelectionInDataVisualisationPanel()
	{
		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		isheetList = isheetList();
		aggregationsName.clear();
		aggregationsName.add(HomeLabels.COUNT);
		aggregationsName.add(HomeLabels.AVERAGE);
		aggregationsName.add(HomeLabels.SUM);

		Reporter.log("<font color='red'> <B> scenario5 Case2 Value Check token generation on selecting Task field from the Select modal</B></font>");
		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			if (chartType.get(i).equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.get(i).equals(HomeLabels.STACKED_COLUMN))
			{

				addDataInVisualisation.clear();
				addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
				homeWeb.addDataInDataVisualisation(addDataInVisualisation);

				homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
				homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);
				for (int k = 0; k < isheetList.size(); k++)
				{

					Reporter.log("Tokens of the selected field should generate with Select... button of Value.");

					assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, isheetList.get(k), HomeLabels.SUM));

					assertTrue(homeWeb.verifyAggregationDropDownValue(isheetList.get(k), aggregationsName));
					Reporter.log("User can select single/multiple fields on Value label based on the selected chart type. (For eg: Standard Column chart supports single field selection on Value whereas Stacked Column/Multi-series Column charts supports multiple fields selection)");

					for (int j = 0; j < aggregationsName.size(); j++)
					{

						homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, isheetList.get(k), aggregationsName.get(j), true);

						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, isheetList.get(k), aggregationsName.get(j)));

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

				for (int k = 0; k < isheetList.size(); k++)
				{
					homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
					homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, isheetList.get(k));

					Reporter.log("Tokens of the selected field should generate with Select... button of Value.");

					assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, isheetList.get(k), HomeLabels.SUM));

					Reporter.log("User can select single/multiple fields on Value label based on the selected chart type. (For eg: Standard Column chart supports single field selection on Value whereas Stacked Column/Multi-series Column charts supports multiple fields selection)");

					assertTrue(homeWeb.verifyAggregationDropDownValue(isheetList.get(k), aggregationsName));

					for (int j = 0; j < aggregationsName.size(); j++)
					{

						homeWeb.selectAggregationsFromDropDown(HomeLabels.VALUE, isheetList.get(k), aggregationsName.get(j), true);
						assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, isheetList.get(k), aggregationsName.get(j)));

						Reporter.log("Sum, Average & Count options to perform different aggregations on Task data should display in a drop down menu of all selected columns on Value.");
						Reporter.log("By default 'Count' should be selected for all tokens generated on Value. (for eg: if user have selected :'Task' on Value then by default token should generate as 'Count of Task')");
					}
				}
			}

		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	public void scenario5Case3CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal() throws Exception
	{
		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		isheetList = isheetList();
		Reporter.log("<font color='red'><B> scenario5 Case3 Filter Value Check token generation on selecting Task field from the Select modal</B></font>");
		addVisualisationPanelInDashBoard();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("Verify this chart:-" + chartType.get(i));

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
			addDataInVisualisation.put(HomeLabels.ISHEETS, isheetTitleRecordNotAdded);
			addDataInVisualisation.put(HomeLabels.VIEW, defaultValue);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);

			Reporter.log("Select modal should close & tokens of the selected fields should generate below Select... button of Filter.");
			Reporter.log("Token of all selected fields should display with Remove (X) icon to remove the added fields from the Filter.");

			for (int j = 0; j < isheetList.size(); j++)
			{
				assertTrue(homeWeb.verifyToken(HomeLabels.FILTER, isheetList.get(j), HomeLabels.FILTER));
			}

			// ====created by =====//

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.CREATED_BY));

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.CREATED_DATE));

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.MODIFIED_BY));

			assertTrue(homeWeb.verifyFilterTokenModalField(HomeLabels.MODIFIED_DATE));

			assertTrue(homeWeb.verifyFilterTokenModalField(studentName));

			assertTrue(homeWeb.verifyFilterTokenModalField(studentId));

			assertTrue(homeWeb.verifyFilterTokenModalField(choiceColumnName));

			assertTrue(homeWeb.verifyFilterTokenModalField(dateAndTimeColumnName));

			assertTrue(homeWeb.verifyFilterTokenModalField(autoIncrementColumnName));

			addDataInVisualisation.put(HomeLabels.ISHEETS, isheetTitleRecordAdded);
			addDataInVisualisation.put(HomeLabels.VIEW, defaultValue);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList, true);

			Reporter.log("Select modal should close & tokens of the selected fields should generate below Select... button of Filter.");
			Reporter.log("Token of all selected fields should display with Remove (X) icon to remove the added fields from the Filter.");

			for (int j = 0; j < isheetList.size(); j++)
			{
				assertTrue(homeWeb.verifyToken(HomeLabels.FILTER, isheetList.get(j), HomeLabels.FILTER));
			}
			// ===created by===//

			verifyAggregationForSingleSelection(createdTokenName, HomeLabels.CREATED_BY);
			// ===created Date===//

			verifyAggregationForSingleSelection(dateAndTimeColumnData, HomeLabels.CREATED_DATE);
			// ===modified by===//

			verifyAggregationForSingleSelection(createdTokenName, HomeLabels.MODIFIED_BY);
			// ===modified Date===//

			verifyAggregationForSingleSelection(dateAndTimeColumnData, HomeLabels.MODIFIED_DATE);
			// ===student name===//

			verifyAggregationForMultipleSelection(singleLineColumnData1, singleLineColumnData2, studentName);
			// ===student ID===//

			verifyAggregationForMultipleSelection(numberColumnData1, numberColumnData2, studentId);
			// ===choice column name===//

			verifyAggregationForSingleSelection(choice1, choiceColumnName);
			// ===date and time column===//

			verifyAggregationForSingleSelection(dateAndTimeColumnData, dateAndTimeColumnName);
			// ===auto increment column===//
			columnMap.clear();
			columnMap.put("1", false);
			columnMap.put("2", false);
			columnMap.put("3", false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(autoIncrementColumnName, columnMap));

			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, autoIncrementColumnName, "1", true);
			assertTrue(homeWeb.verifyAggregationToken(autoIncrementColumnName, "1"));
			columnMap.clear();
			columnMap.put("1", true);
			columnMap.put("2", false);
			columnMap.put("3", false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(autoIncrementColumnName, columnMap));
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
	}

	/**
	 * verify Aggregation For Single Selection
	 */
	public void verifyAggregationForSingleSelection(String data, String columnName)
	{
		columnMap.clear();
		columnMap.put(data, false);
		assertTrue(homeWeb.verifyAggregationDropDownFilter(columnName, columnMap));

		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, columnName, data, true);
		assertTrue(homeWeb.verifyAggregationToken(columnName, data));
		columnMap.clear();
		columnMap.put(data, true);
		assertTrue(homeWeb.verifyAggregationDropDownFilter(columnName, columnMap));

	}

	public void verifyAggregationForMultipleSelection(String data1, String data2, String columnName)
	{
		columnMap.clear();
		columnMap.put(data1, false);
		columnMap.put(data2, false);
		assertTrue(homeWeb.verifyAggregationDropDownFilter(columnName, columnMap));

		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, columnName, data1, true);
		assertTrue(homeWeb.verifyAggregationToken(columnName, data1));
		columnMap.clear();
		columnMap.put(data1, true);
		columnMap.put(data2, false);
		assertTrue(homeWeb.verifyAggregationDropDownFilter(columnName, columnMap));

	}

	public void scenario5Case4CheckTokenGenerationOnSelectingTaskFieldFromTheSelectModal() throws Exception
	{
		modalList.clear();
		modalList.add(studentName);

		Reporter.log("<font color='red'><B> Scenario 5 case 4 Check searchable component, Select all & Clear filters in Filter window</B></font>");
		addVisualisationPanelInDashBoard();
		addDataInDataVisualisation();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.VIEW, HomeLabels.DEFAULT);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(i));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);

			// ======student Name=====//
			Reporter.log("Student Name");
			columnMap.clear();
			columnMap.put(singleLineColumnData1, false);
			columnMap.put(singleLineColumnData2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(studentName, columnMap));

			homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, studentName, singleLineColumnData1, true);
			assertTrue(homeWeb.verifyAggregationToken(studentName, singleLineColumnData1));

			assertTrue(homeWeb.verifySearchInFilterToken(singleLineColumnData1, studentName, true));
			assertTrue(homeWeb.verifyNoResultsFoundInFilterToken(randomKey, studentName));

			homeWeb.clickOnSelectAllInFilterToken(studentName);
			columnMap.clear();
			columnMap.put(singleLineColumnData1, true);
			columnMap.put(singleLineColumnData2, true);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(studentName, columnMap));

			assertTrue(homeWeb.verifyAggregationToken(studentName, singleLineColumnData1));
			assertTrue(homeWeb.verifyAggregationToken(studentName, singleLineColumnData2));

			homeWeb.clickOnClearFiltersInFilterToken(studentName);
			columnMap.clear();
			columnMap.put(singleLineColumnData1, false);
			columnMap.put(singleLineColumnData2, false);
			assertTrue(homeWeb.verifyAggregationDropDownFilter(studentName, columnMap));

			assertTrue(!homeWeb.verifyAggregationToken(studentName, singleLineColumnData1));
			assertTrue(!homeWeb.verifyAggregationToken(studentName, singleLineColumnData2));
		}
		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();

	}

	public void scenario6HideShowThePlottedChartByDisablingEnablingTheLegendsInDataVisualisationPanel() throws Exception
	{
		// bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		List<Boolean> chartStatus = new ArrayList<>();
		chartStatus.add(false);
		modalList.clear();
		modalList.add(studentId);

		Reporter.log("<font color='red'><B> Scenario6 Hide & Show the plotted chart by disabling/enabling the Legends in Data visualisation panel</B></font>");
		homeWeb = bannerPageWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		for (int i = 0; i < chartType.size(); i++)
		{
			Reporter.log("<B>Verify this chart:-" + chartType.get(i) + "</B>");
			homeWeb.clickOnAddSection();
			homeWeb.clickOnMultiplePanelIcon(i + 1);
			homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
			addchart(panelTitle + chartType.get(i), chartType.get(i), isheetTitleRecordAdded);

			if (!(chartType.get(i).equals(HomeLabels.STANDARD_PIE) || chartType.get(i).equals(HomeLabels.STANDARD_COLUMN)))
			{
				homeWeb.clickOnMoreActionOption(panelTitle + chartType.get(i), HomeLabels.EDIT);
				homeWeb.clickOnLegends(HomeLabels.CHART_PREVIEW, chartType.get(i), legendsName);

				assertTrue(homeWeb.verifyChartForLegends(HomeLabels.CHART_PREVIEW, chartType.get(i), chartStatus));
				homeWeb.clickOnSaveInModal();
				homeWeb.clickOnLegends(HomeLabels.DASHBOARD, chartType.get(i), legendsName);
				assertTrue(homeWeb.verifyChartForLegends(HomeLabels.DASHBOARD, chartType.get(i), chartStatus));
			}
		}
		homeWeb.clickOnSave();
	}

	public void scenario7RemoveTheColumnTokensFromTheChartsAndVerifyTheMessageInChartPreviewArea() throws Exception
	{
		modalList.clear();
		modalList.add(studentId);
		Reporter.log("<font color='red'><B> Scenario7 Remove the column tokens from the charts and verify the message in chart preview area</B></font>");

		homeWeb.clickOnEditIcon();
		for (int i = 0; i < chartType.size(); i++)
		{
			homeWeb.clickOnMoreActionOption(panelTitle + chartType.get(i), HomeLabels.EDIT);
			homeWeb.removeToken(HomeLabels.CATEGORY, studentName);
			assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.NO_DATA_TO_DISPLAY));
			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
			homeWeb.removeToken(HomeLabels.VALUE, studentId);
			assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.NO_DATA_TO_DISPLAY));
			assertTrue(homeWeb.verifySaveButtonsInDisabledMode());
			homeWeb.removeToken(HomeLabels.FILTER, studentId);
			homeWeb.removeToken(HomeLabels.CATEGORY, studentName);
			assertTrue(homeWeb.verifySaveButtonsInDisabledMode());
			Reporter.log("In Chart preview area it should show message as No data to display.”" + chartType.get(i));

			homeWeb.clickOnCancelInModalWindow();
		}
		homeWeb.clickOnSave();
	}

	public void scenario8CheckTokenGenerationOnColumnSelectionWhenAnyRecordDoesNotExistInSelectedColumn() throws Exception
	{
		modalList.clear();
		modalList.add(studentName);

		Reporter.log("<font color='red'><B> Scenario8 Check Token Generation On Column Selection When Any Record Does Not Exist In Selected Column</B></font>");
		homeWeb.clickOnEditIcon();
		for (int i = 0; i < chartType.size(); i++)
		{
			homeWeb.clickOnMoreActionOption(panelTitle + chartType.get(i), HomeLabels.EDIT);
			editChart(chartType.get(i), isheetTitleRecordNotAdded);
			assertTrue(homeWeb.verifyMessageInChartPreviewArea(HomeLabels.NO_DATA_TO_DISPLAY));
			homeWeb.clickOnSaveInModal();
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle + chartType.get(i), HomeLabels.NO_ITEMS_TO_DISPLAY));
		}
	}

	public void addchart(String panelTitle, String chartType, String isheetName)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
		addDataInVisualisation.put(HomeLabels.VIEW, defaultValue);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
		if (chartType.equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.equals(HomeLabels.STACKED_COLUMN))
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);
		}
		else
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
		}
		if (!(chartType.equals(HomeLabels.STANDARD_PIE) || chartType.equals(HomeLabels.STANDARD_COLUMN)))
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
		}
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);

		homeWeb.clickOnPreviewButton();
		homeWeb.clickOnAdd();
	}

	public void editChart(String chartType, String isheetName)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
		addDataInVisualisation.put(HomeLabels.VIEW, defaultValue);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
		if (chartType.equals(HomeLabels.MULTI_SERIES_COLUMN) || chartType.equals(HomeLabels.STACKED_COLUMN))
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);
		}
		else
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
		}
		if (!(chartType.equals(HomeLabels.STANDARD_PIE) || chartType.equals(HomeLabels.STANDARD_COLUMN)))
		{
			homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
		}
		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);

		homeWeb.clickOnPreviewButton();
	}

	public boolean verifyOneItemModal(String modalName)
	{
		return homeWeb.verifySelectModalTitle(modalName)
				&& homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_ITEM)
				&& homeWeb.verifyCloseButtonInModal()
				&& homeWeb.verifyListInSelectOneItemModalWindow(HomeLabels.ISHEET, isheetList());
	}

	public boolean verifyOneOrMoreItemsModal(String modalName)
	{
		return homeWeb.verifySelectModalTitle(modalName)
				&& homeWeb.verifyModalWindowField(HomeLabels.SELECT_ONE_OR_MORE_ITEMS)
				&& homeWeb.verifyDoneCancelButtonInModal()
				&& homeWeb.verifyListInSelectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, isheetList());
	}

	private List<String> isheetList()
	{
		modalList.clear();
		modalList.add(HomeLabels.CREATED_BY);
		modalList.add(HomeLabels.CREATED_DATE);
		modalList.add(HomeLabels.MODIFIED_BY);
		modalList.add(HomeLabels.MODIFIED_DATE);
		modalList.add(studentName);
		modalList.add(studentId);
		modalList.add(choiceColumnName);
		modalList.add(dateAndTimeColumnName);
		modalList.add(autoIncrementColumnName);
		return modalList;
	}

	private List<String> isheetListNotDisplay()
	{
		modalList.clear();
		modalList.add(HomeLabels.CREATED_BY);
		modalList.add(HomeLabels.CREATED_DATE);
		modalList.add(HomeLabels.MODIFIED_BY);
		modalList.add(HomeLabels.MODIFIED_DATE);
		modalList.add(address);
		modalList.add(userLookupColumnName);
		modalList.add(hyperlinkColumnName);
		modalList.add(imageColumnName);
		modalList.add(attachmentColumnName);
		modalList.add(fileLinkColumnName);
		modalList.add(folderLinkColumnName);
		modalList.add(isheetLinkColumnName);
		modalList.add(lookupColumnName);
		modalList.add(joinColumnName);
		modalList.add(studentName);
		modalList.add(studentId);
		modalList.add(choiceColumnName);
		modalList.add(dateAndTimeColumnName);
		modalList.add(autoIncrementColumnName);
		return modalList;
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

	private void addDataInDataVisualisation()
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetTitleRecordAdded);
		addDataInVisualisation.put(HomeLabels.VIEW, defaultValue);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

	}

	public void verifyTokenInCategoryAndSeries(String selectButtonName, String chartType)
	{
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);
		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, HomeLabels.CREATED_BY);

		Reporter.log("Click on Select... button of Category.");
		Reporter.log("Click on any Task field to select the category.");
		Reporter.log("Clicking on any Task field, 'Select- Category' modal should close and the token of the selected field should generate with the Select... button of Category.");

		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.CREATED_BY, ""));

		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, HomeLabels.CREATED_BY);

		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.CREATED_BY, ""));
		Reporter.log("Again click on Select... button of Category when task field is already selected.");
		Reporter.log("Select any other field from the 'Select- Category' modal.");
		Reporter.log("Token of the new selected field should generate with Select button of Category label & it will over-right the previously selected field token.");

		homeWeb.clickOnSelectButtonInData(selectButtonName);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, HomeLabels.CREATED_DATE);

		assertTrue(!homeWeb.verifyToken(selectButtonName, HomeLabels.CREATED_BY, ""));
		assertTrue(homeWeb.verifyToken(selectButtonName, HomeLabels.CREATED_DATE, ""));

		Reporter.log("User should be able to select only one Task field on the Category. ");

		homeWeb.removeToken(selectButtonName, HomeLabels.CREATED_DATE);
		assertTrue(!homeWeb.verifyToken(selectButtonName, HomeLabels.CREATED_DATE, ""));
		Reporter.log("Selected field token should be removed from the Category and user should be able to select again the Task field for the Category.");
	}

	public void addIsheetAndView() throws Exception
	{

		baseIsheetTest = new BaseIsheetTest(driver);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
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
		if (adminIsheetWeb.isheetExist(isheetTitleRecordNotAdded))
		{
			adminIsheetWeb.deleteIsheet(isheetTitleRecordNotAdded);
		}
		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		iSheetData = new LinkedHashMap<>();
		iSheetData.put(title, isheetTitleRecordNotAdded);
		isheetDataMap.clear();
		isheetDataMap.put(DisplayiniSheetlist, true);
		isheetDataMap.put(allowlookups, true);
		adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, isheetDataMap);
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitleRecordNotAdded, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS);

		columnAdd();

		adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitleRecordAdded, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS);

		columnAdd();
		adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManageViewWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitleRecordAdded, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS);
		if (!adminIsheetManageViewWeb.verifyView(viewName))
		{
			adminIsheetAddViewWeb = adminIsheetManageViewWeb.clickAddView();
			adminIsheetAddViewWeb.enterViewName(viewName);
			adminIsheetAddViewWeb.selectAvailableColumns(studentName, address);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewWeb = adminIsheetAddViewWeb.clickSaveOnAddView();
		}
		else
		{
			adminIsheetEditViewWeb = adminIsheetManageViewWeb.selectView(viewName);
			if (!adminIsheetEditViewWeb.verifySelectedColumns(studentName, address))
			{
				if (adminIsheetEditViewWeb.verifyAvailableColumns(studentName, address))
				{
					adminIsheetEditViewWeb.selectAvailableColumns(studentName, address);
					adminIsheetEditViewWeb.clickMoveRightSelected();
					assertTrue(adminIsheetEditViewWeb.verifySelectedColumns(studentName, address));
				}
			}
			adminIsheetManageViewWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		}
		setUpIsheetDataAddRecord1();
		isheetsWeb = adminPageWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitleRecordAdded);

		if (!isheetsWeb.verifyIsheetRecordsEmpty())
		{
			isheetsWeb.setAllIsheetRecordsCheckbox(true);
			isheetsWeb.selectIsheetActions(isheetActionDelete);
			isheetsWeb.clickOKOnCustomModal(customModalDeleteRecordTitle);
		}

		isheetsWeb.clickAdd();
		isheetsWeb.addRecord(columnTypesAddRecord, isheetData);
		isheetsWeb.clickAddOnAddRecordModal();

		setUpIsheetDataAddRecord2();
		isheetsWeb.clickAdd();
		isheetsWeb.addRecord(columnTypesAddRecord2, isheetData);
		isheetsWeb.clickAddOnAddRecordModal();

		setUpIsheetDataAddRecord3();
		isheetsWeb.clickAdd();
		isheetsWeb.addRecord(columnTypesAddRecord3, isheetData);
		isheetsWeb.clickAddOnAddRecordModal();
	}

	public void columnAdd() throws Exception
	{
		adminIsheetManageColumnWeb = createSingleLineTextColumn(isheetTitleRecordNotAdded);

		adminIsheetManageColumnWeb = createNumberColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createChoiceColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createDateAndTimeColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createCalculationColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createAutoIncrementColumn(isheetTitleRecordAdded);
		// === columns should not display ====//
		adminIsheetManageColumnWeb = createMultiLineTextColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createUserLookupColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createHyperlinkColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createImageColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createAttachmentColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createFileLinkColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createFolderLinkColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createIsheetLinkColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createLookupColumn(isheetTitleRecordAdded);
		adminIsheetManageColumnWeb = createJoinColumn(isheetTitleRecordAdded);
	}

	public void addFileAndFolder() throws Exception
	{

		documentWeb = dashboardWeb.gotoFileModule();

		for (int i = 0; i < multiFiles.length; i++)
		{
			if (!documentWeb.verifyDocumentUploaded(multiFiles[i]))
			{
				documentWeb.selectItemFromAdd(addMenuItem[i]);
				addDoc = new DocumentAddDataPage();
				addDoc.clean();
				addDoc.setFileuploadpath(multiFiles[i]);
				documentWeb.addFile(addDoc, null);
				documentWeb.clickAddInModal();
			}
		}

		for (int i = 0; i < folderNames.length; i++)
		{
			if (!documentWeb.verifyDocumentUploaded(folderNames[i]))
			{
				documentWeb = dashboardWeb.gotoFileModule();
				documentWeb.selectItemFromAdd(addMenuItem[1]);
				documentWeb.createNewFolderInRoot(folderNames[i], folderDescription);
				documentWeb.clickAddInModal();
			}
		}
	}

	public void setUpIsheetDataAddRecord1()
	{

		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, singleLineColumnData1);

		Map<String, String> multiLineText = new LinkedHashMap<>();
		multiLineText.put(address, multiLineColumnData1);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData1);

		Map<String, String> dateAndTime = new LinkedHashMap<>();
		dateAndTime.put(dateAndTimeColumnName, dateAndTimeColumnData);

		Map<String, String[]> addUserLookUpRecord = new LinkedHashMap<>();
		addUserLookUpRecord.put(userLookupColumnName, userLookupData);

		Map<String, String> hyperLinkData = new LinkedHashMap<>();
		hyperLinkData.put(hyperlinkColumnName, hyperLinkDisplayName + "," + hyperLinkURL);

		Map<String, String[]> attachmenColumntData = new LinkedHashMap<>();
		attachmenColumntData.put(attachmentColumnName, attachmentData);

		Map<String, String[]> fileLinkData = new LinkedHashMap<>();
		fileLinkData.put(fileLinkColumnName, files);

		Map<String, String[]> folderLinkData = new LinkedHashMap<>();
		folderLinkData.put(folderLinkColumnName, folders);

		Map<String, String> imageColumnData = new LinkedHashMap<>();
		imageColumnData.put(imageColumnName, imageData);

		Map<String, String> choiceColumnData = new LinkedHashMap<>();
		choiceColumnData.put(choiceColumnName, choice1);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setMultiLineText(multiLineText);
		isheetData.setNumber(number);
		isheetData.setDateAndTime(dateAndTime);
		isheetData.setAddUserLookUpRecord(addUserLookUpRecord);
		isheetData.setHyperLinkData(hyperLinkData);
		isheetData.setAttachmentData(attachmenColumntData);
		isheetData.setFileLinkData(fileLinkData);
		isheetData.setFolderLinkData(folderLinkData);
		isheetData.setChoiceColumnData(choiceColumnData);
		isheetData.setImageColumnData(imageColumnData);
	}

	public void setUpIsheetDataAddRecord2()
	{

		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, singleLineColumnData2);

		Map<String, String> multiLineText = new LinkedHashMap<>();
		multiLineText.put(address, multiLineColumnData2);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData2);

		Map<String, String> dateAndTime = new LinkedHashMap<>();
		dateAndTime.put(dateAndTimeColumnName, dateAndTimeColumnData);

		Map<String, String[]> addUserLookUpRecord = new LinkedHashMap<>();
		addUserLookUpRecord.put(userLookupColumnName, userLookupData);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setMultiLineText(multiLineText);
		isheetData.setNumber(number);
		isheetData.setDateAndTime(dateAndTime);
		isheetData.setAddUserLookUpRecord(addUserLookUpRecord);
	}

	public void setUpIsheetDataAddRecord3()
	{

		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, singleLineColumnData2);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData2);

		Map<String, String> dateAndTime = new LinkedHashMap<>();
		dateAndTime.put(dateAndTimeColumnName, dateAndTimeColumnData);

		Map<String, String[]> addUserLookUpRecord = new LinkedHashMap<>();
		addUserLookUpRecord.put(userLookupColumnName, userLookupData);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setNumber(number);
		isheetData.setDateAndTime(dateAndTime);
		isheetData.setAddUserLookUpRecord(addUserLookUpRecord);
	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn(String isheetTitle) throws InterruptedException
	{

		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, studentName);
		columnData.put(columnType, singleLineColumnType);

		return baseIsheetTest.addSingleLineTextColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createNumberColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, studentId);
		columnData.put(columnType, numberColumnType);

		return baseIsheetTest.addNumberColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createDateAndTimeColumn(String isheetTitle) throws InterruptedException, ParseException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, dateAndTimeColumnName);
		columnData.put(columnType, dateAndTimeColumnType);

		return baseIsheetTest.addDateAndTimeColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createCalculationColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(studentId, "10");

		addCalculationColumnData = new AddCalculationColumnData();
		addCalculationColumnData.setColumnName(calculationColumnName);
		addCalculationColumnData.setColumnType(calculationColumnType);
		addCalculationColumnData.setFormula(calculationFormula);
		addCalculationColumnData.setExpectedResultOfFormula("20");
		addCalculationColumnData.setColumnNamesOfFormula((LinkedHashMap<String, Object>) columnData);
		return baseIsheetTest.addCalculationColumn(addCalculationColumnData);
	}

	private AdminIsheetManageColumnWeb createChoiceColumn(String isheetTitle) throws Exception
	{
		Map<String, String> data = new LinkedHashMap<>();
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, choiceColumnName);
		columnData.put(columnType, choiceColumnType);
		data.clear();
		data.put(redColorCode, choice1);
		data.put(blueColorCode, choice2);
		columnData.put(addChoice, data);
		return baseIsheetTest.addChoiceColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createMultiLineTextColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, address);
		columnData.put(columnType, multiLineColumnType);

		return baseIsheetTest.addMultipleLineTextColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createAutoIncrementColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, autoIncrementColumnName);
		columnData.put(columnType, autoIncrementColumnType);

		return baseIsheetTest.addAutoIncrementColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createUserLookupColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, userLookupColumnName);
		columnData.put(columnType, userLookupColumnType);

		return baseIsheetTest.addAutoIncrementColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createHyperlinkColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, hyperlinkColumnName);
		columnData.put(columnType, hyperlinkColumnType);

		return baseIsheetTest.addHyperlinkColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createImageColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, imageColumnName);
		columnData.put(columnType, imageColumnType);

		return baseIsheetTest.addImageColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createAttachmentColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, attachmentColumnName);
		columnData.put(columnType, attachmentColumnType);

		return baseIsheetTest.addAttachmentColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createFileLinkColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, fileLinkColumnName);
		columnData.put(columnType, fileLinkColumnType);

		return baseIsheetTest.addFileLinkColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createFolderLinkColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, folderLinkColumnName);
		columnData.put(columnType, folderLinkColumnType);

		return baseIsheetTest.addFolderLinkColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createIsheetLinkColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, isheetLinkColumnName);
		columnData.put(columnType, iSheetLinkColumnType);
		columnData.put(defaultlinkvalue, iShhetLinkDefaultValue);
		columnData.put(isheet, isheetLink);

		return baseIsheetTest.addIsheetLinkColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createLookupColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, lookupColumnName);
		columnData.put(columnType, lookupColumnType);
		columnData.put("sheet", isheetLink);
		columnData.put("select columns", HomeLabels.CREATED_DATE);
		return baseIsheetTest.addLookupColumn(columnData);
	}

	private AdminIsheetManageColumnWeb createJoinColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, joinColumnName);
		columnData.put(columnType, joinColumnType);
		columnData.put("link name", iShhetLinkDefaultValue);
		columnData.put("list of sheet", isheetLink);
		columnData.put("display view", "Default");
		columnData.put("current sheet", studentName);
		columnData.put("target sheet", studentName);
		return baseIsheetTest.addJoinColumn(columnData);
	}

}
