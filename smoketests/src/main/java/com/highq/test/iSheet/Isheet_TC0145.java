package com.highq.test.iSheet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
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
public class Isheet_TC0145 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String jsonFileName = "iSheet/preConfiguration_Isheet_TCR0145.json";
	String siteName = "Isheet_TCR0145_2";
	String[] userNames = {"normal.user0145@isheet0145.com", "site.admin0145@isheet0145.com"};
	String domain = "isheet0145.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String addIsheetOption = "iSheet";
	String isheetTitle = "iSheet0145";
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

	// Single Line Text Column
	String singleLineColumnName = "Single Line Text Column_0145";
	String singleLineColumnType = "Single line text";
	String singleLineColumnDisplayOption = "Display field";
	String singleLineColumnConditionType = "OR";
	String singleLineColumnFilterOption = "Modified by";
	String singleLineColumnFilterOperator = "contains";
	String singleLineColumnFilterValue = "A";
	String maximumChars = "255";
	boolean allowPopulation = false;

	String columnDescription = "Column Description";
	String columnWidth = "200";
	boolean mandatory = true;
	boolean allowSearch = true;
	boolean addToDefaultView = true;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void isheetTCR0145() throws InterruptedException, IOException, ParseException, JSONException
	{
		precondition();
		preCondition();
		scenario1();
		scenario2();
	}

	void preCondition() throws InterruptedException, ParseException
	{
		baseIsheetTest = new BaseIsheetTest(driver);
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

		// Enable Isheet Version/Audit
		dashboardWeb = adminIsheetWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setEnableIsheetVersionAuditOption(enableIsheetVersionAudit);
		aspConfigurationWeb.saveConfigurations();

		logout();
	}

	private void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		Assert.assertTrue(!isheetsWeb.verifyFavouriteIconIsSelected());
		isheetsWeb.addIsheetToFavourites();
		Assert.assertTrue(isheetsWeb.verifyFavouriteIconIsSelected());

		clickOnFavourite();
		Assert.assertTrue(searchInFavourite(isheetTitle));

		logout();
		bannerPageWeb = login(userNames[1], newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		Assert.assertTrue(!isheetsWeb.verifyFavouriteIconIsSelected());

		logout();
	}

	private void scenario2() throws InterruptedException
	{

		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		Assert.assertTrue(isheetsWeb.verifyFavouriteIconIsSelected());
		isheetsWeb.removeIsheetFromFavourites();
		Assert.assertTrue(!isheetsWeb.verifyFavouriteIconIsSelected());

		clickOnFavourite();
		Assert.assertTrue(!searchInFavourite(isheetTitle));
		logout();

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

	void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
		siteData.put("name", siteName);

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin", true);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteData(siteData);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("all");
		configurationData.setSiteUserRoles(siteUserRoles);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission",
				"enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		// logout();
	}
}
