package com.highq.test.iSheet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.labels.collaborate.AdminIsheetManageColumnsLabels;
import com.highq.labels.collaborate.AdminIsheetSaveAsTemplateLabel;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditPage;
import com.highq.pageobjects.base.AdminIsheetImportColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminIsheetSaveAsTemplatePage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.SystemAdminEditColumnPage;
import com.highq.pageobjects.base.SystemAdminIsheetAdminAddColumnPage;
import com.highq.pageobjects.base.SystemAdminIsheetAdminPage;
import com.highq.pageobjects.base.SystemAdminIsheetColumnsListPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.SystemAdminTemplateIsheetsListPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.LoginPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author janki.hirani
 *         DMD iSheet saves as template with AI on
 */
public class IsheetTC2776 extends BannerPageWeb
{
	String jsonFileName = "iSheet/preConfiguration_IsheetTC2776.json";
	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String siteAdminEmail = resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get("user1").get("id").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText();
	String domain = resultsFile.get("GlobalData").get("OrgData").get("Organization 1").get("name").asText();
	String userName = resultsFile.get("GlobalData").get("OrgData").get("Organization 1").get("users").get(0).asText();
	String orgType = "Internal";
	String[] sitesName = {"IsheetTC2776One", "IsheetTC2776Two"};

	ConfigurationData configurationData = new ConfigurationData();

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	BaseIsheetTest baseIsheetTest = new BaseIsheetTest(driver);;
	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage adminAddIsheetWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminFilesPage adminFilesWeb;
	AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	AdminIsheetEditPage adminIsheetEditWeb;
	AdminIsheetSaveAsTemplatePage adminIsheetSaveAsTemplateWeb;
	SystemAdminIsheetAdminPage systemAdminIsheetAdminWeb;
	SystemAdminTemplateIsheetsListPage systemAdminTemplateIsheetsListWeb;
	SystemAdminIsheetColumnsListPage systemAdminIsheetColumnsListWeb;
	SystemAdminEditColumnPage systemAdminEditColumnWeb;
	SystemAdminIsheetAdminAddColumnPage systemAdminIsheetAdminAddColumnWeb;
	AdminIsheetEditColumnPage adminIsheetEditColumnWeb;
	AdminIsheetImportColumnPage adminIsheetImportColumnWeb;

	BannerPage simulBannerPageWeb;
	DashboardPage simulDashboardWeb;
	AdminPage simulAdminPageWeb;
	AdminFilesPage simulAdminFilesWeb;

	String addIsheetOption = "iSheet";
	String importTemplateOption = "Import from template";
	String dmdIsheet = "DMDiSheet";
	String filemetadata = "File metadata template";
	String analysisOption1 = "ON";
	String analysisOption2 = "OFF";
	String option1 = "Enabled, default ON in every site";
	String option2 = "Enabled, default OFF in every site";
	String projectName = "M&A";
	String value = "TRUE";
	String fieldName1 = "Currency";
	String fieldName2 = "DocumentClass";
	String fieldName3 = "Change of Control";
	String fieldName4 = "Party Name";
	String fieldName5 = "Confidentiality";
	String fieldName6 = "License Grant";
	String singleLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SINGLELINETEXT;
	String multiLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_MULTIPLELINETEXT;
	String numberColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NUMBER;
	String dateAndTimeColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_DATEANDTIME;
	String hyperLinkColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_HYPERLINK;
	String singleColumnName = "SingleLineColumn";
	String multiLineColumnName = "MultiLineColumn";
	String numberColumnName = "NumberColumn";
	String LookUpColumnName = "LookUpColumn";
	String hyperLinkColumnName = "HyperLinkColumn";
	String dateAndTimeColumnName = "DateAndTimeColumn";
	String column = "Column";
	String tempName1 = "TC2776TemplateOne";
	String tempName2 = "TC2776TemplateTwo";
	String tempName3 = "TC2776TemplateThree";
	String tempName4 = "TC2776TemplateFour";

	String documentAnalysis = AdminIsheetAddColumnLabel.DOCUMENTANALYSISENGINE;
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String importColumn = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS;
	String manageColumns = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS;
	String warningMsg = AdminIsheetSaveAsTemplateLabel.SITEADMIN_ISHEET_AIMLCOLUMN_SAVEASTEMPLATE_WARNINGMSG;
	String configKira = "Configure";
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void testIsheetTC2776() throws InterruptedException, IOException, ParseException, JSONException
	{
		preCondition();
		case01SaveIsheetAsTemplate();
		case02SaveISheetAsTemplateWhenEnginesAreTurnedOff();
		case03EditingOrAddingColumnsInTemplate();
		case04ImportingTemplateInSiteWithLinkingTypeAsYes();
		case05ImportingTemplateInSiteWithLinkingTypeAsNo();
		case06SimultaneousCaseOne();
		case07SimultaneousCaseTwo();

	}

	void precondition() throws InterruptedException, ParseException
	{
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (adminIsheetWeb.isheetExist(dmdIsheet))
		{
			adminIsheetWeb.deleteIsheet(dmdIsheet);
		}

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(dmdIsheet);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(filemetadata, true);
		adminIsheetWeb = adminAddIsheetWeb.addIsheetClickSave();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(multiLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(multiLineColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(numberColumnType);
		adminIsheetAddColumnWeb.addColumnName(numberColumnName);
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(hyperLinkColumnType);
		adminIsheetAddColumnWeb.addColumnName(hyperLinkColumnName);
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(dateAndTimeColumnType);
		adminIsheetAddColumnWeb.addColumnName(dateAndTimeColumnName);
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

		for (int i = 0; i < sitesName.length; i++)
		{
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(sitesName[i]);
			adminPageWeb = dashboardWeb.gotoAdminModule();
			adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
			if (adminIsheetWeb.isheetExist(tempName1))
			{
				adminIsheetWeb.deleteIsheet(tempName1);
			}
		}

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		if (systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName1))
		{
			systemAdminIsheetAdminWeb.selectTemplate(tempName1);
			systemAdminIsheetAdminWeb.clickOnDeleteTemplateButton();
		}
		if (systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName2))
		{
			systemAdminIsheetAdminWeb.selectTemplate(tempName2);
			systemAdminIsheetAdminWeb.clickOnDeleteTemplateButton();
		}
		if (systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName3))
		{
			systemAdminIsheetAdminWeb.selectTemplate(tempName3);
			systemAdminIsheetAdminWeb.clickOnDeleteTemplateButton();
		}
		if (systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName4))
		{
			systemAdminIsheetAdminWeb.selectTemplate(tempName4);
			systemAdminIsheetAdminWeb.clickOnDeleteTemplateButton();
		}

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		dashboardWeb = bannerPageWeb.gotoDashboard();

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userName));

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<>();
		modulePermission.put("all", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userName + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userName + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(siteAdminPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(sitesName[0]);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("all");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		configurationData.setSiteName(sitesName[1]);

		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		dashboardWeb = bannerPageWeb.gotoDashboard();
	}

	void aspAndSystemAdminSetting() throws InterruptedException
	{
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableDocumentAnalysisByHighQ(value);
		aspConfigurationWeb.enableDocumentAnalysisByKira(value);
		aspConfigurationWeb.enableDocumentAnalysisByLeverton(value);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.clickOnMoreActionOfDocumentAnalysisEngine(kiraEngine);
		if (systemSettingsWeb.verifyConfigureServiceMoreAction(configKira))
		{
			systemSettingsWeb.selectOptionFromDocumentAnalysisEngineConfigurationDropdown(kiraEngine, configKira);
			systemSettingsWeb.documentAnalysisEngineConfigureKira(kiraInstanceUrl, kiraToken);
		}
		systemSettingsWeb.selectDocumentAnalysis(highqEngine, option1);
		systemSettingsWeb.selectDocumentAnalysis(kiraEngine, option1);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.waitTillGlobalMessageDissappears();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.clickMoreActionOptionDocumentAnalysisEngineConfiguration(kiraEngine, FileLabels.FILES_DOCUMENTANALYSIS_MODAL_MANAGE);
		adminFilesWeb.selectProjectFromManageModal(projectName);
		adminFilesWeb.clickOnSaveInManageModal();
		adminFilesWeb.saveFilesChanges();

		dashboardWeb = bannerPageWeb.gotoDashboard();
	}

	void siteAdminConfiguration(String highqOption, String kiraOption) throws InterruptedException
	{
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, highqOption);
		adminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, kiraOption);
		adminFilesWeb.saveFilesChanges();
	}

	void preCondition() throws InterruptedException, IOException, JSONException, ParseException
	{
		siteAndUserConfiguration();
		aspAndSystemAdminSetting();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		precondition();
	}

	void case01SaveIsheetAsTemplate()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetEditWeb = adminIsheetWeb.clickOnIsheetName(dmdIsheet);
		adminIsheetSaveAsTemplateWeb = adminIsheetEditWeb.clickOnSaveAsTemplate();
		Assert.assertTrue(adminIsheetSaveAsTemplateWeb.verifyWarningMsg(warningMsg, true));
		adminIsheetSaveAsTemplateWeb.enterTemplateName(tempName1);
		adminIsheetSaveAsTemplateWeb.addIsheetClickSave();

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		Assert.assertTrue(systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName1));
	}

	void case02SaveISheetAsTemplateWhenEnginesAreTurnedOff() throws InterruptedException
	{
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption2, analysisOption2);

		adminFilesWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetEditWeb = adminIsheetWeb.clickOnIsheetName(dmdIsheet);
		adminIsheetSaveAsTemplateWeb = adminIsheetEditWeb.clickOnSaveAsTemplate();
		Assert.assertTrue(adminIsheetSaveAsTemplateWeb.verifyWarningMsg("", false));
		adminIsheetSaveAsTemplateWeb.enterTemplateName(tempName2);
		adminIsheetSaveAsTemplateWeb.addIsheetClickSave();

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		Assert.assertTrue(systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName2));

	}

	void case03EditingOrAddingColumnsInTemplate()
	{

		systemAdminTemplateIsheetsListWeb = systemAdminIsheetAdminWeb.clickOnTemplateName(tempName1);
		systemAdminIsheetColumnsListWeb = systemAdminTemplateIsheetsListWeb.clickOnAddEditIsheetColumn(dmdIsheet);
		systemAdminEditColumnWeb = systemAdminIsheetColumnsListWeb.clickOnColumnName(singleColumnName);
		Assert.assertFalse(systemAdminEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(systemAdminEditColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertFalse(systemAdminEditColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertFalse(systemAdminEditColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		systemAdminTemplateIsheetsListWeb = systemAdminIsheetAdminWeb.clickOnTemplateName(tempName1);
		systemAdminIsheetColumnsListWeb = systemAdminTemplateIsheetsListWeb.clickOnAddEditIsheetColumn(dmdIsheet);
		systemAdminIsheetAdminAddColumnWeb = systemAdminIsheetColumnsListWeb.clickOnAddNewColumn();
		Assert.assertFalse(systemAdminIsheetAdminAddColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(systemAdminIsheetAdminAddColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertFalse(systemAdminIsheetAdminAddColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertFalse(systemAdminIsheetAdminAddColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());
		systemAdminIsheetAdminAddColumnWeb.enterColumnName(column);
		systemAdminIsheetColumnsListWeb = systemAdminIsheetAdminAddColumnWeb.clickOnSave();
		Assert.assertTrue(systemAdminIsheetColumnsListWeb.verifyColumnDisplayed(column));

		dashboardWeb = gotoDashboard();
		dashboardWeb.logout();
	}

	void case04ImportingTemplateInSiteWithLinkingTypeAsYes() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(sitesName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.clickMoreActionOptionDocumentAnalysisEngineConfiguration(kiraEngine, FileLabels.FILES_DOCUMENTANALYSIS_MODAL_MANAGE);
		adminFilesWeb.selectProjectFromManageModal(projectName);
		adminFilesWeb.clickOnSaveInManageModal();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetWeb.clickOnAddIsheet();
		adminIsheetWeb.selectAddIsheetOptions(importTemplateOption);
		adminIsheetWeb.selectImportIsheetTemplate(tempName1);
		adminIsheetWeb.selectLinkedIsheetOption(true);
		adminIsheetWeb.clickImportOnImportIsheetModal();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnsAreClickable(false));
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddButton());
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddColumnButton());

	}

	void case05ImportingTemplateInSiteWithLinkingTypeAsNo() throws InterruptedException
	{
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(sitesName[1]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.clickMoreActionOptionDocumentAnalysisEngineConfiguration(kiraEngine, FileLabels.FILES_DOCUMENTANALYSIS_MODAL_MANAGE);
		adminFilesWeb.selectProjectFromManageModal(projectName);
		adminFilesWeb.clickOnSaveInManageModal();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetWeb.clickOnAddIsheet();
		adminIsheetWeb.selectAddIsheetOptions(importTemplateOption);
		adminIsheetWeb.selectImportIsheetTemplate(tempName1);
		adminIsheetWeb.selectLinkedIsheetOption(false);
		adminIsheetWeb.clickImportOnImportIsheetModal();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		adminIsheetEditColumnWeb.clickOnImportExternalDataCheckbox(true);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetEditColumnWeb.clickOnSelectButton();
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		Assert.assertTrue(adminIsheetAddColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickCancelOnAddColumn();

		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		Assert.assertTrue(adminIsheetImportColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case06SimultaneousCaseOne() throws InterruptedException
	{
		login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetEditWeb = adminIsheetWeb.clickOnIsheetName(dmdIsheet);

		WebDriver simultaneousDriver;
		simultaneousDriver = setDriver(url);
		simultaneousDriver.get(url);
		LoginPage loginPageWeb = new LoginPageWeb(simultaneousDriver);
		loginPageWeb.setEmailId(siteAdminEmail);
		loginPageWeb.setPassword(siteAdminPassword);
		simulBannerPageWeb = loginPageWeb.clickSignIn();
		simulDashboardWeb = simulBannerPageWeb.gotoDashboard();
		simulDashboardWeb.searchSite(siteName);
		simulAdminPageWeb = simulDashboardWeb.gotoAdminModule();
		simulAdminFilesWeb = simulAdminPageWeb.clickFilesInLeftPanel();
		simulAdminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption2);
		simulAdminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption2);
		simulAdminFilesWeb.saveFilesChanges();
		simultaneousDriver.close();

		adminIsheetSaveAsTemplateWeb = adminIsheetEditWeb.clickOnSaveAsTemplate();
		Assert.assertTrue(adminIsheetSaveAsTemplateWeb.verifyWarningMsg(warningMsg, false));
		adminIsheetSaveAsTemplateWeb.enterTemplateName(tempName3);
		adminIsheetSaveAsTemplateWeb.addIsheetClickSave();

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		Assert.assertTrue(systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName3));

		dashboardWeb = gotoDashboard();
		dashboardWeb.logout();
	}

	void case07SimultaneousCaseTwo() throws InterruptedException
	{
		login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetEditWeb = adminIsheetWeb.clickOnIsheetName(dmdIsheet);

		WebDriver simultaneousDriver;
		simultaneousDriver = setDriver(url);
		simultaneousDriver.get(url);
		LoginPage loginPageWeb = new LoginPageWeb(simultaneousDriver);
		loginPageWeb.setEmailId(siteAdminEmail);
		loginPageWeb.setPassword(siteAdminPassword);
		simulBannerPageWeb = loginPageWeb.clickSignIn();
		simulDashboardWeb = simulBannerPageWeb.gotoDashboard();
		simulDashboardWeb.searchSite(siteName);
		simulAdminPageWeb = simulDashboardWeb.gotoAdminModule();
		simulAdminFilesWeb = simulAdminPageWeb.clickFilesInLeftPanel();
		simulAdminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		simulAdminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption1);
		simulAdminFilesWeb.saveFilesChanges();
		simultaneousDriver.close();

		adminIsheetSaveAsTemplateWeb = adminIsheetEditWeb.clickOnSaveAsTemplate();
		Assert.assertTrue(adminIsheetSaveAsTemplateWeb.verifyWarningMsg(warningMsg, true));
		adminIsheetSaveAsTemplateWeb.enterTemplateName(tempName4);
		adminIsheetSaveAsTemplateWeb.addIsheetClickSave();

		dashboardWeb = gotoDashboard();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminIsheetAdminWeb = systemAdminWeb.gotoIsheetAdmin();
		Assert.assertTrue(systemAdminIsheetAdminWeb.verifyIsheetTemplateIsDisplayed(tempName4));

		dashboardWeb = gotoDashboard();
		dashboardWeb.logout();
	}
}
