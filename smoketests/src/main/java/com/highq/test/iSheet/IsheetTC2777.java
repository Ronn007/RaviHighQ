package com.highq.test.iSheet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.AdminAddIsheetWebLabels;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.labels.collaborate.AdminIsheetManageColumnsLabels;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.LoginPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author janki.hirani
 *         Delete AI hub column
 */
public class IsheetTC2777 extends BannerPageWeb
{
	Logger logger = Logger.getLogger(getClass());

	String jsonFileName = "iSheet/preConfiguration_IsheetTC2777.json";
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

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	BaseIsheetTest baseIsheetTest;
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

	BannerPage simulBannerPageWeb;
	DashboardPage simulDashboardWeb;
	AdminPage simulAdminPageWeb;
	AdminFilesPage simulAdminFilesWeb;

	String addIsheetOption = "iSheet";
	String dmdIsheet = "DMDiSheet";
	String iSheetName = "iSheetName";
	String filemetadata = "File metadata template";
	String allowLookUp = AdminAddIsheetWebLabels.SITEADMIN_MODULESETTINGS_ISHEETS_ADD_ISHEET;
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
	String fieldName7 = "Language";
	String singleLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SINGLELINETEXT;
	String multiLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_MULTIPLELINETEXT;
	String numberColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NUMBER;
	String dateAndTimeColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_DATEANDTIME;
	String hyperLinkColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_HYPERLINK;
	String lookUpColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_LOOKUP;
	String singleColumnName = "SingleLineColumn";
	String multiLineColumnName = "MultiLineColumn";
	String numberColumnName = "NumberColumn";
	String LookUpColumnName = "LookUpColumn";
	String hyperLinkColumnName = "HyperLinkColumn";
	String dateAndTimeColumnName = "DateAndTimeColumn";
	String column = "Column";

	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String importColumn = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS;
	String manageColumns = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS;
	String warningMsg = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_MANAGECOLUMN_AIMLCOLUMN_DELETE_ERRORMSG;
	String errorMessage = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_MANAGECOLUMN_CANNOT_TO_BE_DELETED;
	String configKira = "Configure";
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	List<String> listValue = new ArrayList<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void testIsheetTC2777() throws IOException, JSONException, InterruptedException, ParseException
	{
		preCondition();
		case02ClickCancelOnDeleteModal();
		case01DeleteAIHubColumn();
		case03DeleteAIColumnUsedAsLookupColumn();
		case04DeletingColumnWhenEngineIsTurnedOff();
		case05SimultaneousCaseOne();
		case06SimultaneousCaseTwo();
	}

	void precondition() throws InterruptedException, ParseException
	{
		baseIsheetTest = new BaseIsheetTest(driver);
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (adminIsheetWeb.isheetExist(iSheetName))
		{
			adminIsheetWeb.deleteIsheet(iSheetName);
		}
		if (adminIsheetWeb.isheetExist(dmdIsheet))
		{
			adminIsheetWeb.deleteIsheet(dmdIsheet);
		}

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(dmdIsheet);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(filemetadata, true);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(allowLookUp, true);
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
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(hyperLinkColumnType);
		adminIsheetAddColumnWeb.addColumnName(hyperLinkColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(dateAndTimeColumnType);
		adminIsheetAddColumnWeb.addColumnName(dateAndTimeColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(iSheetName);
		adminIsheetWeb = adminAddIsheetWeb.addIsheetClickSave();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(iSheetName, manageColumns);
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		adminIsheetAddColumnWeb.selectColumnType(lookUpColumnType);
		adminIsheetAddColumnWeb.addColumnName(LookUpColumnName);
		adminIsheetAddColumnWeb.addColumnSelectSheet(dmdIsheet + " " + "(" + siteName + ")");
		listValue.clear();
		listValue.add(singleColumnName);
		adminIsheetAddColumnWeb.selectLookupColumn(listValue, true);
		adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
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

	void case02ClickCancelOnDeleteModal()
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(multiLineColumnName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickCancelOnDeleteColumnModal();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(multiLineColumnName));

	}

	void case01DeleteAIHubColumn()
	{
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(multiLineColumnName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyColumnExist(multiLineColumnName));

	}

	void case03DeleteAIColumnUsedAsLookupColumn()
	{
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(singleColumnName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyGlobalMsgForColumns(errorMessage));
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(singleColumnName));

	}

	void case04DeletingColumnWhenEngineIsTurnedOff() throws InterruptedException
	{
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption2, analysisOption2);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetManageColumnWeb.clickOnColumnMoreAction(numberColumnName);
		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyGlobalMsgForColumns(errorMessage));
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyColumnExist(numberColumnName));

	}

	void case05SimultaneousCaseOne() throws InterruptedException
	{
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

		adminIsheetManageColumnWeb.clickOnColumnMoreAction(hyperLinkColumnName);

		simulAdminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		simulAdminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption1);
		simulAdminFilesWeb.saveFilesChanges();
		simultaneousDriver.close();

		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyColumnExist(hyperLinkColumnName));
	}

	void case06SimultaneousCaseTwo() throws InterruptedException
	{
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

		adminIsheetManageColumnWeb.clickOnColumnMoreAction(dateAndTimeColumnName);

		simulAdminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption2);
		simulAdminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption2);
		simulAdminFilesWeb.saveFilesChanges();
		simultaneousDriver.close();

		adminIsheetManageColumnWeb.selectDeleteOnColumnMoreAction();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyWarningMsgOnDeleteColumnModal(warningMsg));
		adminIsheetManageColumnWeb.clickDeleteOnDeleteColumnModal();
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyColumnExist(dateAndTimeColumnName));
	}
}
