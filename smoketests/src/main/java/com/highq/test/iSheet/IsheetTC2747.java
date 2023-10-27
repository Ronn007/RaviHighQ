package com.highq.test.iSheet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
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
import com.highq.pageobjects.base.AdminIsheetEditColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author janki.hirani
 *         Single Column AI hub edit and Edit import column
 */
public class IsheetTC2747 extends BannerPageWeb
{
	Logger logger = Logger.getLogger(getClass());

	String jsonFileName = "iSheet/preConfiguration_IsheetTC2747.json";
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
	AdminIsheetEditColumnPage adminIsheetEditColumnWeb;
	AdminIsheetEditPage adminIsheetEditWeb;

	String column = "Column";
	String addIsheetOption = "iSheet";
	String dmdIsheet = "DMDiSheet";
	String filemetadata = "File metadata template";
	String allowLookUp = AdminAddIsheetWebLabels.SITEADMIN_MODULESETTINGS_ISHEETS_ADD_ISHEET;
	String analysisOption1 = "ON";
	String analysisOption2 = "OFF";
	String option1 = "Enabled, default ON in every site";
	String option2 = "Enabled, default OFF in every site";
	String projectName = "M&A";
	String value = "TRUE";
	String fieldName1 = "Currency";
	String fieldName2 = "Language";
	String fieldName3 = "Change of Control";
	String fieldName4 = "Party Name";
	String fieldName5 = "License Grant";
	String delimiterName = "Comma";

	String singleLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SINGLELINETEXT;
	String multiLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_MULTIPLELINETEXT;
	String numberColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NUMBER;
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String importColumn = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS;
	String manageColumns = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS;
	String singleColumnName = "SingleLineColumn";
	String multiLineColumnName = "MultiLineColumn";
	String numberColumnName = "NumberColumn";
	String createdByColumn = "Created by";
	String createdDateColumn = "Created date";
	String modifiedByColumn = "Modified by";
	String modifiedDateColumn = "Modified date";
	String fileColumn = "File";
	String folderColumn = "Folder";

	String documentAnalysis = AdminIsheetAddColumnLabel.DOCUMENTANALYSISENGINE;
	String configKira = "Configure";
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	LinkedHashMap<String, String> fieldsValues = new LinkedHashMap<>();
	List<String> optionsList = new ArrayList<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void testIsheetTC2747() throws IOException, JSONException, InterruptedException, ParseException
	{
		preCondition();
		case01EditingColumnAISingleColumnAlredayAdded();
		case02SelectingDelectingEngineValue();
		case03ModalVerifyAfterDeselectionOrEdit();
		case04EditingColumnWhenAIFieldsIsNotSelected();
		case05SelectingFieldsForCase04();
		case06CheckingFieldValueSelectionWhenRemovedFromCrossSign();
		case07ColumnSwtichingCaseForEditVerify();
		case08Swtich();
		case09EditingColumnWhenEnableDocumentAnalysisIsFalseAfterAddingColumn();
		case10EditingColumnWhenEnableDocumentAnalysisIsFalseAfterAddingColumnAndTurningItAgainToTrue();
		case11OneEngineOnAndSecondEngineOff();
	}

	void precondition() throws InterruptedException, ParseException
	{
		baseIsheetTest = new BaseIsheetTest(driver);
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
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName5, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(numberColumnType);
		adminIsheetAddColumnWeb.addColumnName(numberColumnName);
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();

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

	void case01EditingColumnAISingleColumnAlredayAdded()
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());
	}

	void case02SelectingDelectingEngineValue()
	{
		adminIsheetEditColumnWeb.clickOnSelectButton();
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySaveButtonStatusInSelectFieldModal(true));
		adminIsheetEditColumnWeb.clickOnCancelSelectFieldModal();

		adminIsheetEditColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine + ", " + kiraEngine));
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(highqEngine, false);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		adminIsheetEditColumnWeb.clickOnSelectButton();
		adminIsheetEditColumnWeb.clickOnFilterIconInSelectFieldModal();
		optionsList.clear();
		optionsList.add(kiraEngine);
		adminIsheetEditColumnWeb.selectEngineFromFilter(optionsList);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyFilteredEnginesDisplayed(optionsList));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySaveButtonStatusInSelectFieldModal(false));
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName3, true);
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetEditColumnWeb.selectDelimiterOptionsFromDropDown(delimiterName);
		adminIsheetEditColumnWeb.removeField(fieldName2, kiraEngine);
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

	}

	void case03ModalVerifyAfterDeselectionOrEdit()
	{
		adminIsheetEditColumnWeb.clickOnSelectButton();
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName2, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName3, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName4, kiraEngine));
		adminIsheetEditColumnWeb.clickOnCancelSelectFieldModal();
		fieldsValues.clear();
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetEditColumnWeb.removeField(fieldName3, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());

		adminIsheetEditColumnWeb.clickOnSelectButton();
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName2, kiraEngine));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName3, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName4, kiraEngine));
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickCancelOnAddColumn();

		adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case04EditingColumnWhenAIFieldsIsNotSelected()
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(numberColumnName);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());
		adminIsheetEditColumnWeb.clickOnImportExternalDataCheckbox(true);

		Assert.assertTrue(adminIsheetEditColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());

		adminIsheetEditColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
	}

	void case05SelectingFieldsForCase04()
	{
		adminIsheetEditColumnWeb.clickOnSelectButton();
		optionsList.clear();
		optionsList.add(highqEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyFilteredEnginesDisplayed(optionsList));
		optionsList.clear();
		optionsList.add(kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyFilteredEnginesDisplayed(optionsList));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySaveButtonStatusInSelectFieldModal(false));
		adminIsheetEditColumnWeb.clickOnCancelSelectFieldModal();
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetEditColumnWeb.clickOnSelectButton();
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName5, true);
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName5, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetEditColumnWeb.selectDelimiterOptionsFromDropDown(delimiterName);

		adminIsheetEditColumnWeb.removeField(fieldName4, kiraEngine);
		fieldsValues.clear();
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
	}

	void case06CheckingFieldValueSelectionWhenRemovedFromCrossSign()
	{
		adminIsheetEditColumnWeb.clickOnSelectButton();
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName4, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName2, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName5, kiraEngine));
		adminIsheetEditColumnWeb.clickOnCancelSelectFieldModal();
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName5, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetEditColumnWeb.removeField(fieldName2, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());

		adminIsheetEditColumnWeb.clickOnSelectButton();
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName2, kiraEngine));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName4, kiraEngine));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyCheckedFieldsOnModal(fieldName5, kiraEngine));
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();
		fieldsValues.clear();
		fieldsValues.put(fieldName5, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case07ColumnSwtichingCaseForEditVerify()
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		adminIsheetEditColumnWeb.selectColumnType(multiLineColumnType);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());
		adminIsheetEditColumnWeb.clickOnImportExternalDataCheckbox(true);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDefaultNothingSelectedInAnalysisEngine());
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetEditColumnWeb.clickCancelOnAddColumn();

		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());

	}

	void case08Swtich()
	{
		adminIsheetEditColumnWeb.selectColumnType(multiLineColumnType);
		adminIsheetEditColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetEditColumnWeb.clickOnSelectButton();
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickSaveOnAddColumn();

		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(kiraEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickCancelOnAddColumn();

		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(multiLineColumnName);
		adminIsheetEditColumnWeb.setAllowRichHTMLText(true);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(kiraEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName5, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case09EditingColumnWhenEnableDocumentAnalysisIsFalseAfterAddingColumn() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption2, analysisOption2);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(adminIsheetEditColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickSaveOnAddColumn();
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(adminIsheetEditColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());
		fieldsValues.clear();
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());

		adminIsheetEditColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetEditColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetEditColumnWeb.clickOnSelectButton();
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName3, true);
		adminIsheetEditColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetEditColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickSaveOnAddColumn();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case10EditingColumnWhenEnableDocumentAnalysisIsFalseAfterAddingColumnAndTurningItAgainToTrue() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption2, analysisOption2);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertFalse(adminIsheetEditColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());
		adminIsheetEditColumnWeb.clickCancelOnAddColumn();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedSourceFromDropdown(documentAnalysis));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine + ", " + kiraEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName3, highqEngine);
		fieldsValues.put(fieldName2, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyDelimiterField());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void case11OneEngineOnAndSecondEngineOff() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption2, analysisOption1);

		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(dmdIsheet, manageColumns);
		adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(singleColumnName);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifyImportCheckBoxSelcted());
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedEngines(kiraEngine));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedEngines(highqEngine));
		fieldsValues.clear();
		fieldsValues.put(fieldName2, kiraEngine);
		Assert.assertTrue(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		fieldsValues.clear();
		fieldsValues.put(fieldName3, highqEngine);
		Assert.assertFalse(adminIsheetEditColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
		Assert.assertFalse(adminIsheetEditColumnWeb.verifyDelimiterField());
		adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickCancelOnAddColumn();

		optionsList.clear();
		optionsList.add(createdByColumn);
		optionsList.add(createdDateColumn);
		optionsList.add(modifiedByColumn);
		optionsList.add(modifiedDateColumn);
		optionsList.add(fileColumn);
		optionsList.add(folderColumn);
		for (int i = 0; i < optionsList.size(); i++)
		{
			adminIsheetEditColumnWeb = adminIsheetManageColumnWeb.clickOnColumn(optionsList.get(i).trim());
			Assert.assertFalse(adminIsheetEditColumnWeb.verifyImportCheckBoxDisplayed());
			adminIsheetManageColumnWeb = adminIsheetEditColumnWeb.clickCancelOnAddColumn();
		}
		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

		adminIsheetEditWeb = adminIsheetWeb.clickOnIsheetName(dmdIsheet);
		adminIsheetEditWeb.addIsheetSelectCheckBoxOption(filemetadata, false);
		adminIsheetEditWeb.editIsheetClickSave();

		Assert.assertTrue(adminIsheetEditWeb.verifyWarningMsgForDMDIsheet());
		adminIsheetEditWeb.clickCancelInWarningModal();
	}
}
