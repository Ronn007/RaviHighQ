package com.highq.test.iSheet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditViewPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetManagePermissionsPage;
import com.highq.pageobjects.base.AdminIsheetManageViewsPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminIsheetViewPermissionsPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetAddViewWeb;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.AdminIsheetViewPermissionsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class Isheet_TCR0146 extends BannerPageWeb
{
	/** iSheet with multiple view */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "Isheet_TC0146_2";
	String[] userNames = {"normal.user146", "site.admin146"};
	String domain = "isheet0146.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String isheetName = "Test Isheet";
	String addIsheetOption = "iSheet";

	String singleLineColumnType = "Single line text";
	String[] columnTypesAddRecord = {"single line text", "multiple line text", "number", "choice"};
	String manageColumns = "Manage columns";
	String managePermissions = "Manage permissions";
	String manageViews = "Manage views";

	String defaultView = "Default";
	String viewName = "View1";
	String columnName = "column name";
	String columnType = "column type";
	String allowSearch = "allow search";
	String view = "view";
	String edit = "edit";
	String all = "all";

	String singleLineColumnName = "Single Line Text Column";
	String multiLineColumnName = "Multi Line Text Column";
	String multiLineColumnType = "Multiple line text";
	String numberColumnName = "Number Column";
	String numberColumnType = "Number";

	String choiceColumnName = "Choice Column";
	String choiceColumnType = "Choice";
	String choice1 = "First Choice";
	String choice2 = "Second Choice";
	String colorCode = "#000000";
	String title = "title";
	String manageViewsOption = "Edit permissions";

	String singleLineColumnData = "Single Line Column Data";
	String multiLineColumnData = "Multiline Column Data";
	String numberColumnData = "20";

	String[] columnHeadersOfDefaultView = {singleLineColumnName, multiLineColumnName, numberColumnName, choiceColumnName};
	String[] columnHeadersOfView1 = {singleLineColumnName, multiLineColumnName};
	String jsonFileName = "iSheet/preConfiguration_Isheet_TCR0146.json";

	String userForLogin;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	IsheetsPage iSheetsWeb;
	AdminSecurityPage adminSecurityWeb;
	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage adminAddIsheetWeb;
	BaseIsheetTest baseIsheetTest;
	AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	AdminIsheetManagePermissionsPage adminIsheetManagePermissionsWeb;
	IsheetData isheetData;
	AdminIsheetManageViewsPage adminIsheetManageViewsWeb;
	AdminIsheetEditViewPage adminIsheetEditViewWeb;
	AdminIsheetAddViewWeb adminIsheetAddViewWeb;
	AdminIsheetViewPermissionsPage adminIsheetViewPermissionsWeb;
	BannerPage bannerPageWeb;

	LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData = new LinkedHashMap<>();

	LinkedHashMap<String, String> iSheetConfig;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void IsheetTCR0146() throws InterruptedException, IOException, JSONException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException, JSONException
	{
		precondition();
		/* * Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		iSheetsWeb = dashboardWeb.gotoIsheetsModule();
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		/* Verify Default view is loaded by default */
		assertTrue(iSheetsWeb.verifyCurrentViewName(defaultView));

		/* Verify only default view columns are visible */
		for (int i = 0; i < columnHeadersOfDefaultView.length; i++)
		{
			assertTrue(iSheetsWeb.verifyColumnName(columnHeadersOfDefaultView[i]));
		}

		/* Select User created view */
		iSheetsWeb.selectIsheetView(viewName);
		assertTrue(iSheetsWeb.verifyCurrentViewName(viewName));

		/* Verify only User defined view columns are visible */
		for (int i = 0; i < columnHeadersOfView1.length; i++)
		{
			assertTrue(iSheetsWeb.verifyColumnName(columnHeadersOfView1[i]));
		}

		/* Verify column that are not in User defined view are not visible */
		assertTrue(!iSheetsWeb.verifyColumnName(choiceColumnName) && !iSheetsWeb.verifyColumnName(numberColumnName));

		logout();
	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		/* Site and user setup : Create site and add normal and site admin user to it */
		preCondition();
		/* Create iSheet with multiple views and allow view/edit permission to normal user and add records */
		createIsheetWithColumnsIfNotCreated();
	}

	private void createIsheetWithColumnsIfNotCreated() throws InterruptedException
	{
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		baseIsheetTest = new BaseIsheetTest(driver);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (!adminIsheetWeb.isheetExist(isheetName))
		{
			createIsheet(isheetName);
		}

		/* Create required columns */
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		if (!adminIsheetManageColumnWeb.verifyColumnExist(singleLineColumnName))
		{
			Map<String, Object> singleLineTextColumnData = new LinkedHashMap<>();
			singleLineTextColumnData.put(columnName, singleLineColumnName);
			singleLineTextColumnData.put(columnType, singleLineColumnType);
			singleLineTextColumnData.put(allowSearch, true);
			adminIsheetManageColumnWeb = createSingleLineTextColumn(singleLineTextColumnData);
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(multiLineColumnName))
		{
			Map<String, Object> multipleLineTextColumnData = new LinkedHashMap<>();
			multipleLineTextColumnData.put(columnName, multiLineColumnName);
			multipleLineTextColumnData.put(columnType, multiLineColumnType);
			multipleLineTextColumnData.put(allowSearch, true);
			adminIsheetManageColumnWeb = createMultipleLineTextColumn(multipleLineTextColumnData);
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(numberColumnName))
		{
			Map<String, Object> numberColumnData = new LinkedHashMap<>();
			numberColumnData.put(columnName, numberColumnName);
			numberColumnData.put(columnType, numberColumnType);
			numberColumnData.put(allowSearch, true);
			adminIsheetManageColumnWeb = createNumberColumn(numberColumnData);
		}

		if (!adminIsheetManageColumnWeb.verifyColumnExist(choiceColumnName))
		{
			adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
			adminIsheetAddColumnWeb.addColumnName(choiceColumnName);
			adminIsheetAddColumnWeb.selectColumnType(choiceColumnType);
			adminIsheetAddColumnWeb.selectAddColumnCheckBoxOptions(allowSearch, true);
			adminIsheetAddColumnWeb.enterMultipleChoiceForSameColor(colorCode, choice1, choice2);
			adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();
			assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(choiceColumnName));
		}

		/* Set View and Edit Permission to TRUE for notmal user */
		HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
		viewEditPermissionForAll.put(view, true);
		viewEditPermissionForAll.put(edit, true);
		HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
		iSheetScopePermission.put(all, viewEditPermissionForAll);
		iSheetScopePermission.put(all, viewEditPermissionForAll);
		permissionData.put(userNames[0] + "@" + domain, iSheetScopePermission);
		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, managePermissions);
		adminIsheetManagePermissionsWeb.selectEnablePermission(true);
		adminIsheetManagePermissionsWeb.setPermission(permissionData);
		adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

		/* Manage View: Add required columns in both the views */
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
		adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(defaultView);
		addAvailableColumnsToSelectedColumnsInDefaultView();
		adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		if (!adminIsheetManageViewsWeb.verifyView(viewName))
		{
			adminIsheetAddViewWeb = adminIsheetManageViewsWeb.clickAddView();
			adminIsheetAddViewWeb.enterViewName(viewName);

			addAvailableColumnsToSelectedColumnsInView1();
			adminIsheetManageViewsWeb = adminIsheetAddViewWeb.clickSaveOnAddView();
		}
		else
		{
			adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
			addAvailableColumnsToSelectedColumnsInView1();
			adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		}
		setViewPermissionForNormalUser(defaultView);
		setViewPermissionForNormalUser(viewName);
		adminIsheetWeb = adminIsheetManageViewsWeb.clickBackOnManageViews();

		/* Add records in Isheet */
		iSheetsWeb = adminIsheetWeb.gotoIsheetsModule();
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		isheetData = new IsheetData();

		if (iSheetsWeb.verifyNoDataIsPresent())
		{
			Map<String, String> singleLineText = new LinkedHashMap<>();
			singleLineText.put(singleLineColumnName, singleLineColumnData);

			Map<String, String> multiLineText = new LinkedHashMap<>();
			multiLineText.put(multiLineColumnName, multiLineColumnData);

			Map<String, String> number = new LinkedHashMap<>();
			number.put(numberColumnName, numberColumnData);

			Map<String, String> choice = new LinkedHashMap<>();
			choice.put(choiceColumnName, choice1);
			isheetData.setSingleLineText(singleLineText);
			isheetData.setMultiLineText(multiLineText);
			isheetData.setNumber(number);
			isheetData.setChoiceColumnData(choice);
			iSheetsWeb.clickAdd();
			iSheetsWeb.addRecord(columnTypesAddRecord, isheetData);
			iSheetsWeb.clickAddOnAddRecordModal();
		}
		logout();
	}

	private void addAvailableColumnsToSelectedColumnsInDefaultView()
	{
		if (!adminIsheetEditViewWeb.verifySelectedColumns(singleLineColumnName, multiLineColumnName, numberColumnName, choiceColumnName))
		{
			if (adminIsheetEditViewWeb.verifyAvailableColumns(singleLineColumnName, multiLineColumnName, numberColumnName, choiceColumnName))
			{
				adminIsheetEditViewWeb.selectAvailableColumns(singleLineColumnName, multiLineColumnName, numberColumnName, choiceColumnName);
				adminIsheetEditViewWeb.clickMoveRightSelected();
				assertTrue(adminIsheetEditViewWeb.verifySelectedColumns(singleLineColumnName, multiLineColumnName, numberColumnName, choiceColumnName));
			}
			else
			{
				assertFalse(true);
			}
		}
	}

	private void addAvailableColumnsToSelectedColumnsInView1()
	{
		if (!adminIsheetEditViewWeb.verifySelectedColumns(singleLineColumnName, multiLineColumnName))
		{
			if (adminIsheetEditViewWeb.verifyAvailableColumns(singleLineColumnName, multiLineColumnName))
			{
				adminIsheetEditViewWeb.selectAvailableColumns(singleLineColumnName, multiLineColumnName);
				adminIsheetEditViewWeb.clickMoveRightSelected();
				assertTrue(adminIsheetEditViewWeb.verifySelectedColumns(singleLineColumnName, multiLineColumnName));
			}
			else
			{
				assertFalse(true);
			}
		}
	}

	private void setViewPermissionForNormalUser(String nameOfView)
	{
		adminIsheetViewPermissionsWeb = (AdminIsheetViewPermissionsWeb) adminIsheetManageViewsWeb.selectOptionOnViewMoreAction(nameOfView, manageViewsOption);
		adminIsheetViewPermissionsWeb.setAccessToAllUsers(true);
		adminIsheetManageViewsWeb = adminIsheetViewPermissionsWeb.clickSaveOnViewPermissions();
	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn(Map<String, Object> singleLineTextColumnData) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		return baseIsheetTest.addSingleLineTextColumn(singleLineTextColumnData);
	}

	private AdminIsheetManageColumnWeb createMultipleLineTextColumn(Map<String, Object> multiLineTextColumnData) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		return baseIsheetTest.addMultipleLineTextColumn(multiLineTextColumnData);
	}

	private AdminIsheetManageColumnWeb createNumberColumn(Map<String, Object> numberColumnData) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		return baseIsheetTest.addNumberColumn(numberColumnData);
	}

	private void createIsheet(String nameOfIsheet)
	{
		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		iSheetConfig = new LinkedHashMap<>();
		iSheetConfig.put(title, nameOfIsheet);
		adminIsheetWeb = baseIsheetTest.createIsheet(iSheetConfig, null);
	}

	void preCondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}
}
