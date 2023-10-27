package com.highq.test.iSheet;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.iSheetPageData.AddAutoIncrementColumnData;
import com.highq.pagedata.iSheetPageData.AddCalculationColumnData;
import com.highq.pagedata.iSheetPageData.AddDateAndTimeColumnData;
import com.highq.pagedata.iSheetPageData.AddImageColumnData;
import com.highq.pagedata.iSheetPageData.AddIsheetColumnData;
import com.highq.pagedata.iSheetPageData.AddIsheetLinkColumnData;
import com.highq.pagedata.iSheetPageData.AddJoinColumnData;
import com.highq.pagedata.iSheetPageData.AddLookupColumnData;
import com.highq.pagedata.iSheetPageData.AddMultipleLineTextColumnData;
import com.highq.pagedata.iSheetPageData.AddNumberColumnData;
import com.highq.pagedata.iSheetPageData.AddSingleLineTextColumnData;
import com.highq.pagedata.iSheetPageData.AddUserLookupColumnData;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetManagePermissionsPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author badal.gandhi
 */
public class Isheet_TC0143 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String jsonFileName = "iSheet/preConfiguration_Isheet_TCR0143.json";
	String siteName = "Isheet_0143_2";
	String[] userNames = {"normal.user0143@isheet0143.com", "site.admin0143@isheet0143.com"};
	String domain = "isheet0143.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String addIsheetOption = "iSheet";
	String isheetTitle = "iSheet0143";
	String enableIsheetVersionAudit = "TRUE";

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage adminAddIsheetWeb;
	AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	BaseIsheetTest baseIsheetTest;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminIsheetManagePermissionsPage adminIsheetManagePermissionsWeb;
	IsheetsPage isheetsWeb;
	LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData = new LinkedHashMap<>();

	LinkedHashMap<String, String> iSheetData;
	ConfigurationData configurationData = new ConfigurationData();

	AddSingleLineTextColumnData addSingleLineTextColumnData;
	AddMultipleLineTextColumnData addMultipleLineTextColumnData;
	AddNumberColumnData addNumberColumnData;
	AddDateAndTimeColumnData addDateAndTimeColumnData; // Pending
	AddUserLookupColumnData addUserLookupColumnData;
	AddIsheetColumnData addHyperlinkColumnData;
	AddImageColumnData addImageColumnData;
	AddIsheetColumnData addAttachmentColumnData;
	AddIsheetColumnData addFileLinkColumnData;
	AddIsheetColumnData addFolderLinkColumnData;
	AddIsheetLinkColumnData addIsheetLinkColumnData;
	AddLookupColumnData addLookupColumnData;
	AddJoinColumnData addJoinColumnData;
	AddCalculationColumnData addCalculationColumnData;
	AddAutoIncrementColumnData addAutoIncrementColumnData;
	BannerPage bannerPageWeb;

	// Add Column Data
	String columnDescription = "Column Description";
	// String defaultValue = "Default Value";
	String columnWidth = "200";
	boolean mandatory = true;
	boolean allowSearch = true;
	boolean addToDefaultView = true;

	// Single Line Text Column
	String singleLineColumnName = "SingleLineTextColumn0143";
	String singleLineColumnType = "Single line text";
	String singleLineColumnDisplayOption = "Display field";
	String singleLineColumnConditionType = "OR";
	String singleLineColumnFilterOption = "Modified by";
	String singleLineColumnFilterOperator = "contains";
	String singleLineColumnFilterValue = "A";
	String maximumChars = "255";
	boolean allowPopulation = false;

	String singleLineData = "Single";
	String editedSingleLineData = "Edited Single test";

	IsheetData isheetData;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void isheetTCR0143() throws InterruptedException, IOException, ParseException, JSONException
	{
		precondition();
		preCondition();
		scenario1();
	}

	void preCondition() throws InterruptedException, ParseException
	{
		baseIsheetTest = new BaseIsheetTest(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (!adminIsheetWeb.isheetExist(isheetTitle))
		{
			adminIsheetWeb.clickOnAddIsheet();
			adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
			baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
			iSheetData = new LinkedHashMap<>();
			iSheetData.put("title", isheetTitle);
			adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, null);
		}
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, "Manage columns");
		if (!adminIsheetManageColumnWeb.verifyColumnExist(singleLineColumnName))
		{
			adminIsheetManageColumnWeb = createSingleLineTextColumn(isheetTitle);
		}

		// Set View and Edit Permission to TRUE.
		HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
		viewEditPermissionForAll.put("view", true);
		viewEditPermissionForAll.put("edit", true);
		HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
		iSheetScopePermission.put("all", viewEditPermissionForAll);
		iSheetScopePermission.put("all", viewEditPermissionForAll);
		permissionData.put(userNames[0], iSheetScopePermission);

		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();
		adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, "Manage permissions");
		adminIsheetManagePermissionsWeb.selectEnablePermission(true);
		adminIsheetManagePermissionsWeb.setPermission(permissionData);
		adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(singleLineColumnName, singleLineData);
		isheetData.setSingleLineText(singleLineText);

		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		isheetsWeb.clickAdd();

		String[] columnTypesAddRecord = {"single line text"};

		isheetsWeb.addRecord(columnTypesAddRecord, isheetData);
		isheetsWeb.clickAddOnAddRecordModal();

		// Enable Isheet Version/Audit
		dashboardWeb = adminIsheetWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setEnableIsheetVersionAuditOption(enableIsheetVersionAudit);
		aspConfigurationWeb.saveConfigurations();

		// Hub Configuration
		if (!verifyHubConfiguration())
		{
			configureHub();
		}

		// Microservice Configuration
		if (!verifyMicroserviceConfiguration())
		{
			configureMicroService();
		}
		logout();
	}

	private void scenario1() throws InterruptedException
	{
		// Scenario 2 is covered.
		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);

		String[] columnName = null;
		IsheetData isheetData = new IsheetData();
		String operation = "Edit";

		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);

		isheetsWeb.addSingleLineColumnData(singleLineColumnName, editedSingleLineData);
		isheetsWeb.saveIsheetRecordAsDraft();

		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);
		Assert.assertTrue(isheetsWeb.verifyResumeEditMessage());
		isheetsWeb.clickDiscard();
		Assert.assertTrue(isheetsWeb.verifySingleLineTextRecord(singleLineColumnName, singleLineData));
		isheetsWeb.saveIsheetRecordAsDraft();
		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);

		isheetsWeb.addSingleLineColumnData(singleLineColumnName, editedSingleLineData);
		isheetsWeb.saveIsheetRecordAsDraft();

		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);
		Assert.assertTrue(isheetsWeb.verifyResumeEditMessage());
		isheetsWeb.clickResume();
		Assert.assertTrue(isheetsWeb.verifySingleLineTextRecord(singleLineColumnName, editedSingleLineData));
	}

	private AdminIsheetManageColumnWeb createSingleLineTextColumn(String isheetTitle) throws InterruptedException
	{
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addSingleLineTextColumnData = new AddSingleLineTextColumnData();
		addSingleLineTextColumnData.setColumnName(singleLineColumnName);
		addSingleLineTextColumnData.setColumnType(singleLineColumnType);
		addSingleLineTextColumnData.setDescription(columnDescription);
		addSingleLineTextColumnData.setMaxChar(maximumChars);
		// addSingleLineTextColumnData.setDefaultValue(defaultValue);
		addSingleLineTextColumnData.setColumnWidth(columnWidth);
		addSingleLineTextColumnData.setMandatory(mandatory);
		addSingleLineTextColumnData.setAllowSearch(allowSearch);
		addSingleLineTextColumnData.setAllowFieldPopulation(allowPopulation);
		addSingleLineTextColumnData.setAddToDefaultView(addToDefaultView);
		// addSingleLineTextColumnData.setColumnConditionDisplayOption(singleLineColumnDisplayOption);
		// addSingleLineTextColumnData.setColumnConditionType(singleLineColumnConditionType);
		// addSingleLineTextColumnData.setColumnConditionFilterOption(singleLineColumnFilterOption);
		// addSingleLineTextColumnData.setColumnConditionFilterOperator(singleLineColumnFilterOperator);
		// addSingleLineTextColumnData.setColumnConditionFilterValue(singleLineColumnFilterValue);
		return baseIsheetTest.addSingleLineTextColumn(addSingleLineTextColumnData);
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}
}
