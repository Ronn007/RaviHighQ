package com.highq.test.iSheet;

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
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class Isheet_TCR0142 extends BannerPageWeb
{
	/** iSheet Search items */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "Isheet_TC0142_2";
	String[] userNames = {"normal.user142", "site.admin142"};
	String domain = "isheet0142.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String isheetName = "Test Isheet";
	String isheet_AccessType = "Public";
	String addIsheetOption = "iSheet";

	String singleLineColumnType = "Single line text";
	String[][] iSheetRecords = {{"Test Column1", "Test Column2", "Test Column3"},
			{"Test Column 1 Record 1", "Test Column 2 Record 1", "Test Column 3 Record 1"},
			{"Test Column 1 Record 2", "Test Column 2 Record 2", "Test Column 3 Record 2"},
			{"Test Column 1 Record 3", "Test Column 2 Record 3", "Test Column 3 Record 3"},
			{"DataRecord1", "DataRecord2", "DataRecord3"}};
	String[] columnTypesAddRecord = {"single line text"};
	String searchText = "Test Column";
	String searchText2 = "DataRecord";
	String saveSearchName = "Saved Search";
	String viewAllSavedSearches = "View all saved searches";
	String saveSearch_MoreActionItem = "Delete";
	String filter_AdvancedSearch = "Advanced search";
	String anyOfTheseWordsText = "Data";
	String anyOfTheseWordsDummyText = "Dummy";
	String manageColumns = "Manage columns";
	String managePermissions = "Manage permissions";
	String manageViews = "Manage views";
	String viewName = "Default";
	String columnName = "column name";
	String columnType = "column type";
	String allowSearch = "allow search";
	String view = "view";
	String edit = "edit";
	String all = "all";
	String jsonFileName = "iSheet/preConfiguration_Isheet_TCR0142.json";

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
	public void IsheetTCR0142() throws InterruptedException, IOException, JSONException
	{
		scenario1();
		scenario2();
		scenario3();
	}

	private void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Search items from search text box */
		precondition();
		/* * Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		iSheetsWeb = dashboardWeb.gotoIsheetsModule();
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		/* Verify search result contains search text */
		iSheetsWeb.searchIsheetItem(searchText);
		assertTrue(iSheetsWeb.verifySearchResultContainsSearchText(searchText));
	}

	private void scenario2()
	{
		/** Scenario 2: Search items from Saved Search */
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		iSheetsWeb.searchIsheetItem(searchText2);
		assertTrue(iSheetsWeb.verifySearchResultContainsSearchText(searchText2));
		/* Save Search */
		iSheetsWeb.clickSaveSearch();
		iSheetsWeb.setNameInSaveSearch(saveSearchName);
		iSheetsWeb.clickSaveInSaveSearchModal();
		/* Click on Saved Search name and verify result contains search text */
		iSheetsWeb.selectSearchFilterOption(saveSearchName);
		assertTrue(iSheetsWeb.verifySearchResultContainsSearchText(searchText2));
		iSheetsWeb.selectSearchFilterOption(viewAllSavedSearches);
		iSheetsWeb.selectMoreActionItemOfaSavedSearch(saveSearchName, saveSearch_MoreActionItem);
		assertTrue(!iSheetsWeb.verifySavedSearchNameInSavedSearches(saveSearchName));
		iSheetsWeb.clickCloseInViewAllSavedSearchesModal();
	}

	private void scenario3()
	{
		/** Scenario 3: Search items from Advance search */
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		/* Search text in Advanced Search - Any of these words and verify search results */
		searchAnyOfTheseWords(anyOfTheseWordsText);
		assertTrue(iSheetsWeb.verifySearchResultContainsSearchText(anyOfTheseWordsText));

		/* Search a dummy text in Advanced Search - Any of these words and verify search results does not contain any data */
		searchAnyOfTheseWords(anyOfTheseWordsDummyText);
		assertTrue(iSheetsWeb.verifyNoDataIsPresent());
		iSheetsWeb.gotoDashboard();
		logout();
	}

	void searchAnyOfTheseWords(String anyOfTheseWords)
	{
		iSheetsWeb.selectSearchFilterOption(filter_AdvancedSearch);
		iSheetsWeb.setAnyOfTheseWordsInAdvancedSearch(anyOfTheseWords);
		iSheetsWeb.clickSearchInAdvancedSearch();
	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		/** Site and user setup */
		preCondition();
		/** Create iSheet with multiple columns and allow view/edit permission to normal user */
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
			adminIsheetWeb.clickOnAddIsheet();
			adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
			baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
			iSheetConfig = new LinkedHashMap<>();
			iSheetConfig.put("title", isheetName);
			iSheetConfig.put("access type", isheet_AccessType);
			adminIsheetWeb = baseIsheetTest.createIsheet(iSheetConfig, null);
			assertTrue(adminIsheetWeb.isheetExist(isheetName));
		}

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageColumns);

		for (int i = 0; i < iSheetRecords[0].length; i++)
		{
			if (!adminIsheetManageColumnWeb.verifyColumnExist(iSheetRecords[0][i]))
			{
				Map<String, Object> singleLineTextColumnData = new LinkedHashMap<>();
				singleLineTextColumnData.put(columnName, iSheetRecords[0][i]);
				singleLineTextColumnData.put(columnType, singleLineColumnType);
				singleLineTextColumnData.put(allowSearch, true);
				adminIsheetManageColumnWeb = createSingleLineTextColumn(singleLineTextColumnData);
				assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(iSheetRecords[0][i]));
			}
		}

		// Set View and Edit Permission to TRUE.
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

		/* Manage View */
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetName, manageViews);
		adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(viewName);
		adminIsheetEditViewWeb.clickMoveRightAll();
		adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();
		adminIsheetWeb = adminIsheetManageViewsWeb.clickBackOnManageViews();

		/* Add records in Isheet */
		iSheetsWeb = adminIsheetWeb.gotoIsheetsModule();
		iSheetsWeb.selectIsheetFromHeader(isheetName);
		Map<String, String> singleLineText;
		isheetData = new IsheetData();
		if (iSheetsWeb.verifyNoDataIsPresent())
		{
			for (int i = 1; i < iSheetRecords.length; i++)
			{
				singleLineText = new LinkedHashMap<>();
				iSheetsWeb.clickAdd();
				for (int j = 0; j < iSheetRecords[i].length; j++)
				{
					singleLineText.put(iSheetRecords[0][j], iSheetRecords[i][j]);
				}
				isheetData.setSingleLineText(singleLineText);
				iSheetsWeb.addRecord(columnTypesAddRecord, isheetData);
				iSheetsWeb.clickAddOnAddRecordModal();
			}
		}

		logout();
	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn(Map<String, Object> singleLineTextColumnData) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		return baseIsheetTest.addSingleLineTextColumn(singleLineTextColumnData);
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
