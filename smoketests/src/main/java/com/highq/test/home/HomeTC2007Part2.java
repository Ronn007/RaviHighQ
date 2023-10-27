package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.highq.labels.collaborate.AdminIsheetManagePermissionsLabels;
import com.highq.labels.collaborate.AdminIsheetManageViewLabels;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditViewPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPermissionsPage;
import com.highq.pageobjects.base.AdminIsheetManagePermissionsPage;
import com.highq.pageobjects.base.AdminIsheetManageViewsPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminIsheetViewPermissionsPage;
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

public class HomeTC2007Part2 extends BannerPageWeb
{
	private WebDriver driver;
	private String sysAdminEmail = "auto.user@highq.com";
	private String sysAdminPassword = "Pa&&worD123";
	private String siteName = "Home_TC2007_Part2";
	String jsonFileName = "home/preConfiguration_HomeTC2007.json";
	String[] userGroup = {"member.admintc2007", "reporting.admintc2007", "qa.admintc2007", "normal.usertc2007"};
	String domain = "highq.com";
	String[] userGroup1 = {"member.admintc2007@highq.com", "reporting.admintc2007@highq.com", "qa.admintc2007@highq.com", "normal.usertc2007@highq.com"};
	String[] userGroup2 = {"site.admintc2007@highq.com", "content.admintc2007@highq.com"};
	LinkedHashMap<String, String> iSheetData;
	IsheetData isheetData;
	Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	List<String> modalList = new ArrayList<>();
	String[] studentNameData = {"Ramesh", "Mahesh", "Yogesh"};
	// ==isheets data==//
	LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData = new LinkedHashMap<>();
	String permissionTypeView = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_VIEW;
	String permissionTypeEdit = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_EDIT;
	String permissionScopeAll = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_ALL;
	String view = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_VIEW;
	String print = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_PRINT;
	String export = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_EXPORT;
	String editPermissions = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_MOREACTIONS_EDITPERMISSIONS;
	String deleteView = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_MOREACTIONS_DELETE;
	String manageViews = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS;
	String manageColumns = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS;
	String manageIsheetPermission = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEPERMISSIONS;
	String chartTitle = "Data Visualisation-Stacked Chart for iSheet";
	String isheetName = "Student Data";
	String addIsheetOption = "iSheet";
	String title = "title";
	String[] columnTypesAddRecord = {"single line text", "number"};
	String[] columnTypesAddRecord2 = {"single line text", "number"};
	String[] columnTypesAddRecord3 = {"single line text", "number"};
	String columnName = "column name";
	String columnType = "column type";
	String studentName = "Student Name";
	String moreActionEdit = IsheetLabels.ISHEET_MOREACTIONS_EDIT;
	String singleLineColumnType = AdminIsheetLabels.SINGLE_LINE_TEXT_COLUMN;
	String studentId = "Student ID";
	String numberColumnType = AdminIsheetLabels.NUMBER;
	String createdByColumn = HomeLabels.CREATED_BY;
	String autoIncrementColumnName = "Auto increment Column";
	String autoIncrementColumnType = AdminIsheetLabels.AUTO_INCREMENT_COLUMN;
	String numberColumnData1 = "1";
	String numberColumnData2 = "2";
	String isheetActionDelete = IsheetLabels.ISHEET_DELETERECORD;
	String customModalDeleteRecordTitle = IsheetLabels.ISHEET_DELETE_DELETEMESSAGETITLE;
	String[] testRecord1 = {"Test Column 1 Record 1", "Test Column 2 Record 1", "Test Column 3 Record 1"};
	String viewName = "View1";
	String panelTitle = "Data Visualisation for iSheet";
	String noPermissionMessage = HomeLabels.NO_PERMISSION_MESSAGE;
	String validationMessage = HomeLabels.DATA_UNAVAILABLE_MESSAGE;
	String noItemMessage = HomeLabels.NO_ITEMS_TO_DISPLAY;
	List<String> chartType = new ArrayList<>();
	List<String> chartName = new ArrayList<>();
	Map<String, Object> columnData = new LinkedHashMap<>();
	LinkedHashMap<String, Boolean> isheetDataMap = new LinkedHashMap<>();
	List<String> isheetList = new ArrayList<>();
	Map<String, Boolean> columnMap = new LinkedHashMap<>();

	DashboardPage dashboardWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationPage;
	HomePage homeWeb;
	IsheetsPage isheetsWeb;
	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	AdminIsheetPage adminIsheetWeb;
	AdminIsheetManagePermissionsPage adminIsheetManagePermissionsWeb;
	AdminAddIsheetPage adminAddIsheetWeb;
	AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	AdminIsheetManageViewsPage adminIsheetManageViewsWeb;
	AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	AdminIsheetAddViewWeb adminIsheetAddViewWeb;
	BaseIsheetTest baseIsheetTest;
	AdminIsheetEditViewPage adminIsheetEditViewWeb;
	AdminIsheetViewPermissionsPage adminIsheetViewPermissionsWeb;
	AdminIsheetManageColumnPermissionsPage adminIsheetManageColumnPermissionsPage;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws Exception
	 * @author tejash.trivedi
	 * @Created: 22 May 2018
	 */
	@SuppressWarnings("unchecked")
	@Test(priority = 1)
	public void HomeTC2007Part2TestCase() throws Exception
	{
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		chartType = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartType"), List.class);
		chartName = new ObjectMapper().convertValue(resultsFile.get("DataVisualisation").get("chartName"), List.class);

		baseIsheetTest = new BaseIsheetTest(driver);

		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
	}

	/**
	 * @throws Exception
	 */
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
		addIsheetAndView();
		logout();
	}

	/**
	 *
	 */
	public void scenario1()
	{
		Reporter.log("");
		Reporter.log("<B> scenario1: For selected Isheet view </B>");
		try
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			addVisualisationPanelInDashBoard();
			addchart(panelTitle, chartType.get(2), isheetName);
			homeWeb.clickOnSave();

			Reporter.log("<B> Case 1.: </B> ");
			Reporter.log("Permission of selected Isheet view is removed");

			LinkedHashMap<String, Boolean> viewPermission = new LinkedHashMap<>();
			viewPermission.put(view, false);
			viewPermission.put(print, false);
			viewPermission.put(export, false);
			LinkedHashMap<String, Map<String, Boolean>> viewPermissionData = new LinkedHashMap<>();
			for (int i = 0; i < userGroup.length; i++)
			{
				viewPermissionData.put(userGroup[i] + "@" + domain, viewPermission);
			}
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetViewPermissionsWeb = (AdminIsheetViewPermissionsPage) adminIsheetManageViewsWeb.selectOptionOnViewMoreAction(viewName, editPermissions);
			adminIsheetViewPermissionsWeb.setAccessToAllUsers(false);
			adminIsheetViewPermissionsWeb.setIsheetPermission(viewPermissionData);
			adminIsheetViewPermissionsWeb.clickSaveOnViewPermissions();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noPermissionMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message “You do not have the required permissions to view this panel. Contact your administrator for More information.”");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Configured chart will display on Home Dashboard");
				logout();
			}
			Reporter.log("<B> Case 2.: </B> ");
			Reporter.log("Delete selected Isheet view");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetManageViewsWeb.selectOptionOnViewMoreAction(viewName, deleteView);
			adminIsheetManageViewsWeb.clickDeleteOnDeleteViewModal();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message “You do not have the required permissions to view this panel. Contact your administrator for More information.”");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Chart will not plot & it will display validation message “You do not have the required permissions to view this panel. Contact your administrator for More information.”");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyOptionsOfViewDropdown(HomeLabels.NONE_SELECTED));
					Reporter.log("- In Edit modal, chart preview area should not be loaded & in View drop down 'None selected' should be selected");
					homeWeb.clickOnCancel();
				}
				logout();
			}
		}
		catch (Exception e)
		{
			Reporter.log("Exception" + e);
			logout();
		}
		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.clickAddView();
			adminIsheetAddViewWeb.enterViewName(viewName);
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();
			logout();
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario2() throws InterruptedException
	{
		Reporter.log("");
		Reporter.log("<B> scenario2: For Column selected on 'Category' </B>");
		try
		{
			Reporter.log("<B> Case 1.: </B> ");
			Reporter.log("View permission of column is removed which is selected on Category");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			addVisualisationPanelInDashBoard();
			addchart(panelTitle, chartType.get(2), isheetName);
			homeWeb.clickOnSave();

			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			// Set View and Edit Permission to TRUE.
			HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
			viewEditPermissionForAll.put(permissionTypeView, true);
			viewEditPermissionForAll.put(permissionTypeEdit, true);
			HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);

			for (int i = 0; i < userGroup.length; i++)
			{
				permissionData.put(userGroup[i] + "@" + domain, iSheetScopePermission);
			}

			adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageIsheetPermission);
			adminIsheetManagePermissionsWeb.selectEnablePermission(true);
			adminIsheetManagePermissionsWeb.setPermission(permissionData);
			adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

			LinkedHashMap<String, Boolean> viewColumnPermission = new LinkedHashMap<>();
			viewColumnPermission.put(view, false);

			LinkedHashMap<String, Map<String, Boolean>> viewColumnPermissionData = new LinkedHashMap<>();
			for (int i = 0; i < userGroup.length; i++)
			{
				viewColumnPermissionData.put(userGroup[i] + "@" + domain, viewColumnPermission);
			}

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentName);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
			adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noPermissionMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message “You do not have the required permissions to view this panel. Contact your administrator for More information.”");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Configured chart will display on Home Dashboard");
				logout();
			}
			Reporter.log("<B> Case 2.: </B> ");
			Reporter.log("Column which is selected on Category is removed from selected view");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();

			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, autoIncrementColumnName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noPermissionMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message “You do not have the required permissions to view this panel. Contact your administrator for More information.”");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noItemMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- On Home dashboard it will display a message \"No items to display\"");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyMessageInChartPreviewArea("No data to display."));
					Reporter.log("- on editing the panel in chart preview area it will display message \"No data to display\".");

					assertTrue(!homeWeb.verifyToken(HomeLabels.CATEGORY, studentName, HomeLabels.SUM));

					Reporter.log("- Selected token should be removed from Category label.");

					assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode()
							&& homeWeb.verifySaveButtonsInDisabledMode());

					Reporter.log("- Preview & Save buttons should be disabled");

					homeWeb.clickOnCancel();
				}
				logout();
			}

			Reporter.log("<B> Case 3.: </B> ");
			Reporter.log("Column which is selected on Category is deleted from Isheet.");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();

			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentName);
			adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
			adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
			assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(studentName)));

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message \"You do not have the required permissions to view this panel. Contact your administrator for More information.\"");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- On Home dashboard it will display a message \"No items to display\"");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyMessageInChartPreviewArea("No data to display."));
					Reporter.log("<B><B>Verification By :</B></B>" + userGroup2[i]);
					Reporter.log("- on editing the panel in chart preview area it will display message 'No data to display.'");

					assertTrue(!homeWeb.verifyToken(HomeLabels.CATEGORY, studentName, HomeLabels.SUM));

					Reporter.log("- Selected token should be removed from Category label.");

					assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode()
							&& homeWeb.verifySaveButtonsInDisabledMode());

					Reporter.log("- Preview & Save buttons should be disabled");
					homeWeb.clickOnCancel();
				}
				logout();
			}
		}
		catch (Exception e)
		{
			Reporter.log("Exception" + e);
			logout();
		}
		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb = createSingleLineTextColumn(isheetName);

			adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			isheetsWeb = adminIsheetManageViewsWeb.gotoIsheetsModule();
			isheetsWeb.selectIsheetFromHeader(isheetName);

			// Select record and Click On EDIT
			String[] columnName = null;
			isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, moreActionEdit);

			isheetData = new IsheetData();

			isheetsWeb.addSingleLineColumnData(studentName, studentNameData[0]);
			isheetsWeb.setEditAnotherRecordCheckBox(true);
			isheetsWeb.saveEditedIseetRecord();

			isheetsWeb.addSingleLineColumnData(studentName, studentNameData[1]);
			isheetsWeb.saveEditedIseetRecord();

			isheetsWeb.addSingleLineColumnData(studentName, studentNameData[2]);
			isheetsWeb.saveEditedIseetRecord();
			logout();
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario3() throws InterruptedException
	{
		Reporter.log("");
		Reporter.log("<B> scenario3: For Column selected on 'Value' </B>");
		try
		{
			Reporter.log("<B> Case 1.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & view permission of 1 column is removed");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			addVisualisationPanelInDashBoard();

			modalList.clear();
			modalList.add(studentId);

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
			addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
			addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
			addDataInVisualisation.put(HomeLabels.VIEW, viewName);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(2));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
			homeWeb.clickOnDoneInModalWindow();

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, autoIncrementColumnName);
			homeWeb.clickOnDoneInModalWindow();

			homeWeb.clickOnPreviewButton();
			homeWeb.gotoAddChartTitle(chartTitle);
			homeWeb.clickOnAdd();
			homeWeb.clickOnSave();

			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			// Set View and Edit Permission to TRUE.
			HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
			viewEditPermissionForAll.put(permissionTypeView, true);
			viewEditPermissionForAll.put(permissionTypeEdit, true);
			HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);

			for (int i = 0; i < userGroup.length; i++)
			{
				permissionData.put(userGroup[i] + "@" + domain, iSheetScopePermission);
			}

			adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageIsheetPermission);
			adminIsheetManagePermissionsWeb.selectEnablePermission(true);
			adminIsheetManagePermissionsWeb.setPermission(permissionData);
			adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

			LinkedHashMap<String, Boolean> viewColumnPermission = new LinkedHashMap<>();
			viewColumnPermission.put(view, false);

			LinkedHashMap<String, Map<String, Boolean>> viewColumnPermissionData = new LinkedHashMap<>();

			for (int i = 0; i < userGroup.length; i++)
			{
				viewColumnPermissionData.put(userGroup[i] + "@" + domain, viewColumnPermission);
			}

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
			adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("chart will plot with other Isheet column which is selected on 'Value' for which user has atleast view Permission");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Chart should plot with both selected columns on Value label.");
				logout();
			}

			Reporter.log("<B> Case 2.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & view permission of both columns is removed");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentId);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
			adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noPermissionMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message 'You do not have the required permissions to view this panel. Contact your administrator for More information.'");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Chart should plot with both selected columns on Value label.");
				logout();
			}

			Reporter.log("<B> Case 3.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & any 1 column is removed from the selected view");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentId);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(true);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();
			adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("chart will plot with other Isheet column selected on 'Value' label which still exist in the selected view");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- chart will plot with other Isheet column selected on 'Value' label which still exist in the selected view.");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyToken(HomeLabels.VALUE, studentId, HomeLabels.SUM));

					Reporter.log("- In edit modal, token of only 1 Isheet column should be selected with which chart is plotted.");

					assertTrue(!homeWeb.verifyToken(HomeLabels.VALUE, autoIncrementColumnName, HomeLabels.SUM));

					Reporter.log("- Column which is removed from selected view its token should be removed from Value label");

					homeWeb.clickOnCancel();
				}
				logout();
			}

			Reporter.log("<B> Case 4.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & both columns are removed from the selected view");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(true);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();
			adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noItemMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message " + noItemMessage);
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noItemMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- On Home dashboard it will display a message 'No items to display'");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyMessageInChartPreviewArea("No data to display."));
					Reporter.log("- on editing the panel in chart preview area it will display message \"No data to display\".");

					assertTrue(!homeWeb.verifyToken(HomeLabels.VALUE, studentName, HomeLabels.SUM)
							&& !homeWeb.verifyToken(HomeLabels.VALUE, studentId, HomeLabels.SUM));

					Reporter.log("- Tokens of all selected columns should be removed from Value label.");

					assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode()
							&& homeWeb.verifySaveButtonsInDisabledMode());

					Reporter.log("- Preview & Save buttons should be disabled");

					homeWeb.clickOnCancel();
				}
				logout();
			}

			Reporter.log("<B> Case 5.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & any one column is deleted from Isheet");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
			adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
			adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
			assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(autoIncrementColumnName)));

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message 'This information is currently Unavailable. Please contact your System Administrator for more Information.'");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- On Home dashboard it will display a message " + validationMessage);

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyMessageInChartPreviewArea("No data to display."));
					Reporter.log("<B><B>Verification By :</B></B>" + userGroup2[i]);
					Reporter.log("- on editing the panel in chart preview area it will display message 'No data to display.'");

					assertTrue(!homeWeb.verifyToken(HomeLabels.VALUE, studentName, HomeLabels.SUM));

					Reporter.log("- Selected token should be removed from Category label.");

					assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode()
							&& homeWeb.verifySaveButtonsInDisabledMode());

					Reporter.log("- Preview & Save buttons should be disabled");
					homeWeb.clickOnCancel();
				}
				logout();
			}

			Reporter.log("<B> Case 6.: </B> ");
			Reporter.log("2 Isheet columns are selected on Value label & both columns are deleted from Isheet");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentId);
			adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
			adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
			assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(studentId)));

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will not plot & it will display validation message " + validationMessage);
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- On Home dashboard it will display a message " + validationMessage);

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(homeWeb.verifyMessageInChartPreviewArea("No data to display."));
					Reporter.log("<B><B>Verification By :</B></B>" + userGroup2[i]);
					Reporter.log("- On Home dashboard it will display a message 'No items to display' & on editing the panel in chart preview area it will display message 'No data to display.'");

					assertTrue(!homeWeb.verifyToken(HomeLabels.VALUE, autoIncrementColumnName, HomeLabels.SUM)
							&& !homeWeb.verifyToken(HomeLabels.VALUE, studentId, HomeLabels.SUM));

					Reporter.log("- Tokens of all selected columns should be removed from Value label.");

					assertTrue(homeWeb.verifyPreviewButtonsInDisabledMode()
							&& homeWeb.verifySaveButtonsInDisabledMode());

					Reporter.log("- Preview & Save buttons should be disabled");
					homeWeb.clickOnCancel();
				}
				logout();
			}
		}
		catch (

		Exception e)
		{
			Reporter.log("Exception" + e);
			logout();
		}
		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb = createAutoIncrementColumn(isheetName);

			adminIsheetManageColumnWeb = createNumberColumn(isheetName);
			adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName, createdByColumn);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			isheetsWeb = adminIsheetManageViewsWeb.gotoIsheetsModule();
			isheetsWeb.selectIsheetFromHeader(isheetName);

			// Select record and Click On EDIT
			String[] columnName = null;
			IsheetData isheetData = new IsheetData();

			isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, moreActionEdit);

			isheetsWeb.addNumberColumnData(studentId, numberColumnData1);
			isheetsWeb.setEditAnotherRecordCheckBox(true);
			isheetsWeb.saveEditedIseetRecord();

			isheetsWeb.addNumberColumnData(studentId, numberColumnData2);
			isheetsWeb.saveEditedIseetRecord();

			isheetsWeb.addNumberColumnData(studentId, numberColumnData1);
			isheetsWeb.saveEditedIseetRecord();
			logout();
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario4() throws InterruptedException
	{
		Reporter.log("");
		Reporter.log("<B> scenario4: For Column selected on 'Series' (Series will be applicable from 4.4 onwards)</B>");
		try
		{
			Reporter.log("<B> Case 1.: </B> ");
			Reporter.log("View permission of Isheet Column is removed which is selected on Series");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			addVisualisationPanelInDashBoard();

			addDataInVisualisation.clear();
			addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
			addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
			addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
			addDataInVisualisation.put(HomeLabels.VIEW, viewName);
			addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(2));
			homeWeb.addDataInDataVisualisation(addDataInVisualisation);

			homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);

			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);

			homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
			homeWeb.clickOnDoneInModalWindow();

			homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
			homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, autoIncrementColumnName);

			homeWeb.clickOnPreviewButton();
			homeWeb.gotoAddChartTitle(chartTitle);

			homeWeb.clickOnAdd();

			homeWeb.clickOnSave();

			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			// Set View and Edit Permission to TRUE.
			HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
			viewEditPermissionForAll.put(permissionTypeView, true);
			viewEditPermissionForAll.put(permissionTypeEdit, true);
			HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
			iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);

			for (int i = 0; i < userGroup.length; i++)
			{
				permissionData.put(userGroup[i] + "@" + domain, iSheetScopePermission);
			}

			adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageIsheetPermission);
			adminIsheetManagePermissionsWeb.selectEnablePermission(true);
			adminIsheetManagePermissionsWeb.setPermission(permissionData);
			adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

			LinkedHashMap<String, Boolean> viewColumnPermission = new LinkedHashMap<>();
			viewColumnPermission.put(view, false);

			LinkedHashMap<String, Map<String, Boolean>> viewColumnPermissionData = new LinkedHashMap<>();
			for (int i = 0; i < userGroup.length; i++)
			{
				viewColumnPermissionData.put(userGroup[i] + "@" + domain, viewColumnPermission);
			}

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
			adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
			adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
			adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log("Chart will still plot with the Isheet columns selected on Category & Value");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("Chart will plot with Isheet columns selected on Category, Value & Series");
				logout();
			}
			Reporter.log("<B> Case 2.: </B> ");
			Reporter.log("Column which is selected on 'Series' is removed from the selected view");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log(" Chart will still plot with the Isheet columns selected on Category & Value");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- Chart will plot only with the selected category & Values.");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(!homeWeb.verifyToken(HomeLabels.SERIES, autoIncrementColumnName, HomeLabels.SUM));

					Reporter.log("- In Edit modal, token of isheet column selected on Series should be removed");

					homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
					assertTrue(!homeWeb.verifySearchInSelectOneItemModal(autoIncrementColumnName));
					Reporter.log("- Removed column should not display in select modal of Series.");

					homeWeb.clickOnCloseInModalWindow();
					homeWeb.clickOnCancel();
				}
				logout();
			}

			Reporter.log("<B> Case 3.: </B> ");
			Reporter.log("Column which is selected on 'Series' is deleted from Isheet");

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
			adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
			adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
			assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(autoIncrementColumnName)));

			logout();

			for (int i = 0; i < userGroup1.length; i++)
			{
				bannerPageWeb = login(userGroup1[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
				Reporter.log(" Chart will still plot with the Isheet columns selected on Category & Value");
				logout();
			}

			for (int i = 0; i < userGroup2.length; i++)
			{
				bannerPageWeb = login(userGroup2[i], sysAdminPassword);
				dashboardWeb = bannerPageWeb.gotoDashboard();
				dashboardWeb.searchSite(siteName);
				homeWeb = bannerPageWeb.gotoHomeModule();
				assertTrue(homeWeb.verifyChartVisibility(chartTitle));

				Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
				Reporter.log("- Chart will plot only with the selected category & Values.");

				if (userGroup2[i].equals(userGroup2[0]))
				{
					homeWeb.clickOnEditIcon();
					homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

					assertTrue(!homeWeb.verifyToken(HomeLabels.SERIES, autoIncrementColumnName, HomeLabels.SUM));

					Reporter.log("- In Edit modal, token of isheet column selected on Series should be removed");

					homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
					assertTrue(!homeWeb.verifySearchInSelectOneItemModal(autoIncrementColumnName));
					Reporter.log("- Removed column should not display in select modal of Series.");

					homeWeb.clickOnCloseInModalWindow();
					homeWeb.clickOnCancel();
				}
				logout();
			}
		}
		catch (Exception e)
		{
			Reporter.log("Exception" + e);
			logout();
		}
		finally
		{
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
			adminIsheetManageColumnWeb = createAutoIncrementColumn(isheetName);

			adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

			adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			adminIsheetAddViewWeb.clickMoveLeftAll();
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName, createdByColumn);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

			logout();
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario5() throws InterruptedException
	{
		Reporter.log("");
		Reporter.log("<B> scenario5: For Column selected on 'Filters' (Filter will be applicable from 4.4 onwards) </B>");
		Reporter.log("<B> Case 1.: </B> ");
		Reporter.log("2 Isheet columns are selected on Filter label & view permission of both columns is removed");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		addVisualisationPanelInDashBoard();

		modalList.clear();
		modalList.add(studentId);

		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
		addDataInVisualisation.put(HomeLabels.VIEW, viewName);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(2));
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, createdByColumn);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
		homeWeb.clickOnDoneInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);
		homeWeb.clickOnDoneInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, autoIncrementColumnName);
		homeWeb.clickOnDoneInModalWindow();
		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, autoIncrementColumnName, "1", true);

		homeWeb.clickOnPreviewButton();
		homeWeb.gotoAddChartTitle(chartTitle);
		homeWeb.clickOnAdd();
		homeWeb.clickOnSave();

		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		// Set View Permission to TRUE.
		HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
		viewEditPermissionForAll.put(permissionTypeView, true);
		HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
		iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
		iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);

		for (int i = 0; i < userGroup.length; i++)
		{
			permissionData.put(userGroup[i] + "@" + domain, iSheetScopePermission);
		}

		adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageIsheetPermission);
		adminIsheetManagePermissionsWeb.selectEnablePermission(true);
		adminIsheetManagePermissionsWeb.setPermission(permissionData);
		adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

		LinkedHashMap<String, Boolean> viewColumnPermission = new LinkedHashMap<>();
		viewColumnPermission.put(view, false);

		LinkedHashMap<String, Map<String, Boolean>> viewColumnPermissionData = new LinkedHashMap<>();

		for (int i = 0; i < userGroup.length; i++)
		{
			viewColumnPermissionData.put(userGroup[i] + "@" + domain, viewColumnPermission);
		}

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentName);
		adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
		adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
		adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

		adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
		adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
		adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(false);
		adminIsheetManageColumnPermissionsPage.setIsheetColumnPermission(viewColumnPermissionData);
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

		logout();

		for (int i = 0; i < userGroup1.length; i++)
		{
			bannerPageWeb = login(userGroup1[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();
			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
			Reporter.log("- Chart should display with both selected filter even if view permission of columns is removed");
			logout();
		}

		for (int i = 0; i < userGroup2.length; i++)
		{
			bannerPageWeb = login(userGroup2[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();

			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
			Reporter.log("- Chart should display with both selected filter");
			logout();
		}

		Reporter.log("<B> Case 3.: </B> ");
		Reporter.log("2 Isheet columns are selected on Filter & remove any 1 Isheet column from the selected view");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentName);
		adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
		adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(true);
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
		adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
		adminIsheetAddViewWeb.clickMoveLeftAll();
		adminIsheetAddViewWeb.selectAvailableColumns(studentId, autoIncrementColumnName, createdByColumn);
		adminIsheetAddViewWeb.clickMoveRightSelected();
		adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

		logout();

		for (int i = 0; i < userGroup1.length; i++)
		{
			bannerPageWeb = login(userGroup1[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();
			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
			Reporter.log("- Chart should plot with 1 filter applied which still exist in the selected view.");
			logout();
		}

		for (int i = 0; i < userGroup2.length; i++)
		{
			bannerPageWeb = login(userGroup2[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();

			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
			Reporter.log("- Chart should plot 1 with 1 filter applied which still exist in the selected view.");

			if (userGroup2[i].equals(userGroup2[0]))
			{
				homeWeb.clickOnEditIcon();
				homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

				assertTrue(!homeWeb.verifyToken(HomeLabels.FILTER, studentName, HomeLabels.FILTER));

				Reporter.log("- In edit modal,column which is removed from the selected view its token should be removed from Filter label.");

				assertTrue(homeWeb.verifyToken(HomeLabels.FILTER, autoIncrementColumnName, HomeLabels.FILTER));

				Reporter.log("- Chart should display in chart preview area where contents in chart filtered by 1 column which is selected on 'Filter'");

				homeWeb.clickOnCancel();
			}
			logout();
		}

		Reporter.log("<B> Case 5.: </B> ");
		Reporter.log("2 Isheet columns are selected on Filter & delete any 1 column from Isheet");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(studentName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(studentName)));

		logout();

		for (int i = 0; i < userGroup1.length; i++)
		{
			bannerPageWeb = login(userGroup1[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();
			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
			Reporter.log("- Chart should plot with 1 filter applied which still exist in the Isheet");
			logout();
		}

		for (int i = 0; i < userGroup2.length; i++)
		{
			bannerPageWeb = login(userGroup2[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();

			assertTrue(homeWeb.verifyChartVisibility(chartTitle));
			Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
			Reporter.log("- Chart should plot with 1 filter applied which still exist in the Isheet");

			if (userGroup2[i].equals(userGroup2[0]))
			{
				homeWeb.clickOnEditIcon();
				homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);
				assertTrue(!homeWeb.verifyToken(HomeLabels.FILTER, studentName, HomeLabels.FILTER));
				Reporter.log("- In edit modal, token of deleted column should be removed from Filter label.");
				homeWeb.clickOnCancel();
			}
			logout();
		}

		Reporter.log("<B> Case 2.: </B> ");
		Reporter.log("1 Isheet column is selected on Filter & remove that Isheet column from selected view");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		// adminIsheetManageColumnWeb = createAutoIncrementColumn(isheetName);

		adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
		adminIsheetManageColumnPermissionsPage = adminIsheetManageColumnWeb.selectEditPermissionsOnColumnMoreAction();
		adminIsheetManageColumnPermissionsPage.setInheritIsheetPermission(true);
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetManageColumnPermissionsPage.clickSaveOnEnableIsheetPermission();

		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
		adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
		adminIsheetAddViewWeb.clickMoveLeftAll();
		adminIsheetAddViewWeb.selectAvailableColumns(studentId, autoIncrementColumnName, createdByColumn);
		adminIsheetAddViewWeb.clickMoveRightSelected();
		adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

		addVisualisationPanelInDashBoard();

		modalList.clear();
		modalList.add(studentId);

		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
		addDataInVisualisation.put(HomeLabels.VIEW, viewName);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType.get(2));
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, createdByColumn);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
		homeWeb.clickOnDoneInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, autoIncrementColumnName);
		homeWeb.clickOnDoneInModalWindow();
		homeWeb.selectAggregationsFromDropDown(HomeLabels.FILTER, autoIncrementColumnName, "1", true);

		homeWeb.clickOnPreviewButton();
		homeWeb.gotoAddChartTitle(chartTitle);
		homeWeb.clickOnAdd();
		homeWeb.clickOnSave();

		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
		adminIsheetAddViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
		adminIsheetAddViewWeb.clickMoveLeftAll();
		adminIsheetAddViewWeb.selectAvailableColumns(studentId, createdByColumn);
		adminIsheetAddViewWeb.clickMoveRightSelected();
		adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();

		logout();

		for (int i = 0; i < userGroup1.length; i++)
		{
			bannerPageWeb = login(userGroup1[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noItemMessage));
			Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
			Reporter.log("- Chart will not display & it will display a validation message " + noItemMessage);
			logout();
		}

		for (int i = 0; i < userGroup2.length; i++)
		{
			bannerPageWeb = login(userGroup2[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();

			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, noItemMessage));
			Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
			Reporter.log("- Chart will not display & it will display a validation message " + noItemMessage);

			if (userGroup2[i].equals(userGroup2[0]))
			{
				homeWeb.clickOnEditIcon();
				homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("- on editing the panel in chart preview area it will display Chart of the columns selected on Category & Value.");

				assertTrue(!homeWeb.verifyToken(HomeLabels.FILTER, autoIncrementColumnName, HomeLabels.FILTER));

				Reporter.log("- Token of the selected Isheet column should be removed from Filter.");

				assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode()
						&& !homeWeb.verifySaveButtonsInDisabledMode());

				Reporter.log("- Preview & Save button should be enabled.");

				homeWeb.clickOnCancel();
			}
			logout();
		}

		Reporter.log("<B> Case 4.: </B> ");
		Reporter.log("1 Isheet column is selected on Filter & delete that column from Isheet");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(autoIncrementColumnName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		assertTrue(!(adminIsheetManageColumnWeb.verifyColumnExist(autoIncrementColumnName)));

		logout();

		for (int i = 0; i < userGroup1.length; i++)
		{
			bannerPageWeb = login(userGroup1[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();
			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
			Reporter.log("<B>Verification By :</B>" + userGroup1[i]);
			Reporter.log("- Chart will not display & it will display a validation message " + validationMessage);
			logout();
		}

		for (int i = 0; i < userGroup2.length; i++)
		{
			bannerPageWeb = login(userGroup2[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			homeWeb = bannerPageWeb.gotoHomeModule();

			assertTrue(homeWeb.verifyErrorMessageInDashBoard(panelTitle, validationMessage));
			Reporter.log("<B>Verification By :</B>" + userGroup2[i]);
			Reporter.log("- Chart will not display & it will display a validation message " + validationMessage);

			if (userGroup2[i].equals(userGroup2[0]))
			{
				homeWeb.clickOnEditIcon();
				homeWeb.clickOnMoreActionOption(panelTitle, HomeLabels.EDIT);

				assertTrue(homeWeb.verifyChartVisibility(chartTitle));
				Reporter.log("- on editing the panel in chart preview area it will display Chart of the columns selected on Category & Value.");

				assertTrue(!homeWeb.verifyToken(HomeLabels.FILTER, autoIncrementColumnName, HomeLabels.FILTER));

				Reporter.log("- Token of the selected Isheet column should be removed from Filter.");

				assertTrue(!homeWeb.verifyPreviewButtonsInDisabledMode()
						&& !homeWeb.verifySaveButtonsInDisabledMode());

				Reporter.log("- Preview & Save button should be enabled.");

				homeWeb.clickOnCancel();
			}
			logout();
		}
	}

	/**
	 * @param panelTitle
	 * @param chartType
	 * @param isheetName
	 */
	public void addchart(String panelTitle, String chartType, String isheetName)
	{
		modalList.clear();
		modalList.add(studentId);

		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.PANEL_TITLE, panelTitle);
		addDataInVisualisation.put(HomeLabels.SOURCE, HomeLabels.ISHEETS);
		addDataInVisualisation.put(HomeLabels.ISHEETS, isheetName);
		addDataInVisualisation.put(HomeLabels.VIEW, viewName);
		addDataInVisualisation.put(HomeLabels.CHART_TYPE, chartType);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		homeWeb.clickOnSelectButtonInData(HomeLabels.CATEGORY);

		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);

		homeWeb.clickOnSelectButtonInData(HomeLabels.VALUE);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentId);
		homeWeb.clickOnDoneInModalWindow();

		homeWeb.clickOnSelectButtonInData(HomeLabels.SERIES);
		homeWeb.selectOneItemInModalWindow(HomeLabels.ISHEET, studentName);

		homeWeb.clickOnSelectButtonInData(HomeLabels.FILTER);
		homeWeb.selectOneOrMoreItemsModalWindow(HomeLabels.ISHEET, modalList, true);

		homeWeb.clickOnPreviewButton();
		homeWeb.gotoAddChartTitle(chartTitle);

		homeWeb.clickOnAdd();
	}

	/**
	 *
	 */
	public void addVisualisationPanelInDashBoard()
	{
		homeWeb = bannerPageWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
	}

	/**
	 * @throws Exception
	 */
	public void addIsheetAndView() throws Exception
	{
		baseIsheetTest = new BaseIsheetTest(driver);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (adminIsheetWeb.isheetExist(isheetName))
		{
			adminIsheetWeb.deleteIsheet(isheetName);
		}
		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		iSheetData = new LinkedHashMap<>();
		iSheetData.put(title, isheetName);
		adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, null);

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS);

		columnAdd();
		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS);
		if (!adminIsheetManageViewsWeb.verifyView(viewName))
		{
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.clickAddView();
			adminIsheetAddViewWeb.enterViewName(viewName);
			adminIsheetAddViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName);
			adminIsheetAddViewWeb.clickMoveRightSelected();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();
		}
		else
		{
			adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			if (!adminIsheetEditViewWeb.verifySelectedColumns(studentId, studentName, autoIncrementColumnName))
			{
				if (adminIsheetEditViewWeb.verifyAvailableColumns(studentId, studentName, autoIncrementColumnName))
				{
					adminIsheetEditViewWeb.selectAvailableColumns(studentId, studentName, autoIncrementColumnName);
					adminIsheetEditViewWeb.clickMoveRightSelected();
					assertTrue(adminIsheetEditViewWeb.verifySelectedColumns(studentId, studentName, autoIncrementColumnName));
				}
			}
			adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		}
		setUpIsheetDataAddRecord1();
		isheetsWeb = adminPageWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetName);

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

	/**
	 * @throws Exception
	 */
	public void columnAdd() throws Exception
	{
		adminIsheetManageColumnWeb = createSingleLineTextColumn(isheetName);
		adminIsheetManageColumnWeb = createNumberColumn(isheetName);
		adminIsheetManageColumnWeb = createAutoIncrementColumn(isheetName);
	}

	/**
	 *
	 */
	public void setUpIsheetDataAddRecord1()
	{
		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, studentNameData[0]);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData1);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setNumber(number);
	}

	/**
	 *
	 */
	public void setUpIsheetDataAddRecord2()
	{
		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, studentNameData[1]);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData2);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setNumber(number);
	}

	/**
	 *
	 */
	public void setUpIsheetDataAddRecord3()
	{
		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(studentName, studentNameData[2]);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(studentId, numberColumnData2);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setNumber(number);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createSingleLineTextColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, studentName);
		columnData.put(columnType, singleLineColumnType);

		return baseIsheetTest.addSingleLineTextColumn(columnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createNumberColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		columnData.clear();
		columnData.put(columnName, studentId);
		columnData.put(columnType, numberColumnType);

		return baseIsheetTest.addNumberColumn(columnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createAutoIncrementColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		columnData.clear();
		columnData.put(columnName, autoIncrementColumnName);
		columnData.put(columnType, autoIncrementColumnType);

		return baseIsheetTest.addAutoIncrementColumn(columnData);
	}
}
