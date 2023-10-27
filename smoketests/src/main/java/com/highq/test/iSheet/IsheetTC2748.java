package com.highq.test.iSheet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.highq.pageobjects.base.AdminIsheetImportColumnPage;
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
 *         Import column Add- AI hub
 */
public class IsheetTC2748 extends BannerPageWeb
{
	Logger logger = Logger.getLogger(getClass());

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
	AdminIsheetImportColumnPage adminIsheetImportColumnWeb;

	BannerPage simulBannerPageWeb;
	DashboardPage simulDashboardWeb;
	AdminPage simulAdminPageWeb;
	AdminFilesPage simulAdminFilesWeb;

	LinkedHashMap<String, String> iSheetData;
	String addIsheetOption = "iSheet";
	String isheetTitle = "DMD_iSheet";
	String isheetTitleLabel = "title";
	String fileMetadata = "File metadata template";
	String allowSection = AdminAddIsheetWebLabels.SITEADMIN_MODULESETTINGS_ISHEETS_ADD_ISHEET_ALLOWSECTION;
	String analysisOption1 = "ON";
	String analysisOption2 = "OFF";
	String option1 = "Enabled, default ON in every site";
	String option2 = "Enabled, default OFF in every site";
	String value = "TRUE";
	String fieldName1 = "Currency";
	String fieldName2 = "DocumentClass";
	String fieldName3 = "Change of Control";
	String fieldName4 = "Party Name";
	String fieldName5 = "Confidentiality";
	String fieldName6 = "License Grant";
	String fieldName7 = "Language";
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String importColumn = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS;
	String manageColumns = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS;
	String documentAnalysis = AdminIsheetAddColumnLabel.DOCUMENTANALYSISENGINE;
	String engineErrorMsg = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ERRORMSG_SELECTENGINES;
	String columnName1 = "Fields";
	String columnName2 = "Column name";
	String columnName3 = "Column type";
	String columnName4 = "Section";
	String singleLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SINGLELINETEXT;
	String multiLineColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_MULTIPLELINETEXT;
	String numberColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NUMBER;
	String dateAndTimeColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_DATEANDTIME;
	String hyperlinkColumnType = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_HYPERLINK;
	String choice = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_CHOICE;
	String userLookUp = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_USERLOOKUP;
	String image = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_IMAGE;
	String attachment = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ATTACHMENT;
	String fileLink = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FILELINK;
	String folderLink = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FOLDERLINK;
	String iSheetLink = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ISHEETLINK;
	String lookUp = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_LOOKUP;
	String join = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_JOIN;
	String calculation = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_CALCULATION;
	String autoIncrement = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_AUTOINCREMENT;
	// String specialCharacter = "Çø–—‘‚“‹›¥©®¶»ΔΦΣβα‰℘‡↔∝≅⊗◊♠♣♥♦⊥|@#$%^&*!~`/汉字⌋≥≠√∝∞ζηθالف‬ કુંદન @<>×ÞŒƒšˆ˜÷¦¬«¾Ξπω…φ℘⇔⋅⊥()'?[]{}~¤🛄ఆలయం";
	String specialCharacter = "@#$%^&*!~()'?[]{}";
	String scriptAsName = "<script>alert(\"hi\")</script>";
	String simpleColumnName = "Column1";
	String projectName = "M&A";
	String sectionName = "General";
	String errorMsg = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDENGINEOFF_ERRORMSG;
	String configKira = "Configure";
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	String jsonFileName = "iSheet/preConfiguration_IsheetTC2748.json";
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

	List<String> optionsList = new ArrayList<>();
	List<String> sectionList = new ArrayList<>();
	LinkedHashMap<String, String> fieldsValues = new LinkedHashMap<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void testIsheetTC2748() throws IOException, JSONException, InterruptedException, ParseException
	{
		preCondition();
		scenarion01OptionToAddImportColumns();
		scenarion02SelectSourceAndAanlysisEngine();
		scenario03SelectingFieldsFromModalWindow();
		scenario04renderingSelectedFields();
		scenario05EditingRenderedFields();
		scenario06DeselectAll();
		scenario07Filter();
		scenario08Searching();
		scenario09FieldsAndColumnSetting();
		scenario11HandlingErrorMessage();
		scenario12HandlingErrorMessage();
		scenario13ValidationMessageWhenMoreThan20FieldsAdded();
		scenario10RenamingColumnNames();
		scenario14SimultaneousCaseOne();
		scenario15SimultaneousCaseTwo();
	}

	void precondition() throws InterruptedException, ParseException
	{
		baseIsheetTest = new BaseIsheetTest(driver);
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (adminIsheetWeb.isheetExist(isheetTitle))
		{
			adminIsheetWeb.deleteIsheet(isheetTitle);
		}

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(isheetTitle);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(fileMetadata, true);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(allowSection, true);
		adminAddIsheetWeb.addIsheetClickSave();

		dashboardWeb = gotoDashboard();
		dashboardWeb.logout();
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
	}

	void aspAndSystemAdminSetting() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
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
		precondition();
		aspAndSystemAdminSetting();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenarion01OptionToAddImportColumns() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyImportColumnsPage());

	}

	void scenarion02SelectSourceAndAanlysisEngine()
	{
		optionsList.clear();
		optionsList.add(documentAnalysis);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySourceFromDropdown(optionsList));
		adminIsheetImportColumnWeb.clickOnAnalysisEngineDropdown();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyDefaultNothingSelectedInAnalysisEngine());
		optionsList.clear();
		optionsList.add(highqEngine);
		optionsList.add(kiraEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyListedEnginesInDropDown(optionsList));
		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMessageForSelectEngine(engineErrorMsg));
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectedEngines(highqEngine + ", " + kiraEngine));
		adminIsheetImportColumnWeb.clickOnSelectButton();
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyFieldsWithEngineInSelectModal(fieldsValues));
	}

	void scenario03SelectingFieldsFromModalWindow()
	{
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySaveButtonStatusInSelectFieldModal(false));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectFieldsModal());
	}

	void scenario04renderingSelectedFields()
	{
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName3, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
	}

	void scenario05EditingRenderedFields()
	{
		adminIsheetImportColumnWeb.clickOnSelectButton();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyCheckedFieldsOnModal(fieldName1, highqEngine));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyCheckedFieldsOnModal(fieldName2, highqEngine));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyCheckedFieldsOnModal(fieldName3, kiraEngine));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyCheckedFieldsOnModal(fieldName4, kiraEngine));

		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, false);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, false);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertFalse(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
	}

	void scenario06DeselectAll()
	{
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectOrDeselectAllFieldsFromSelectFieldModal(false);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySaveButtonStatusInSelectFieldModal(false));

		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName7, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName5, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName5, kiraEngine);
		fieldsValues.put(fieldName6, kiraEngine);
		fieldsValues.put(fieldName7, highqEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		fieldsValues.clear();
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		Assert.assertFalse(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		adminIsheetImportColumnWeb.clickOnSelectButton();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectFieldsModal());
		adminIsheetImportColumnWeb.clickOnCancelSelectFieldModal();
		Assert.assertFalse(adminIsheetImportColumnWeb.verifySelectFieldsModal());
	}

	void scenario07Filter()
	{
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.clickOnFilterIconInSelectFieldModal();
		optionsList.clear();
		optionsList.add(kiraEngine);
		adminIsheetImportColumnWeb.selectEngineFromFilter(optionsList);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyFilteredEnginesDisplayed(optionsList));
		adminIsheetImportColumnWeb.clearFilterInSelectFieldsModal();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyClearFilterIsDisabledAndNoAnyEngineIsSelected());
		adminIsheetImportColumnWeb.clickOnCancelSelectFieldModal();
	}

	void scenario08Searching()
	{
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.enterValueInSearchBoxInSelectFieldModal(fieldName3);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySearchedFieldIsDisplayed(fieldName3));
		adminIsheetImportColumnWeb.clickOnFilterIconInSelectFieldModal();
		optionsList.clear();
		optionsList.add(kiraEngine);
		adminIsheetImportColumnWeb.selectEngineFromFilter(optionsList);
		adminIsheetImportColumnWeb.enterValueInSearchBoxInSelectFieldModal(fieldName3);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyFilterAndSearchBothApplied(fieldName3, optionsList));
		adminIsheetImportColumnWeb.enterValueInSearchBoxInSelectFieldModal(fieldName2);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyNoAnyEnginesAreDisplayed());
		adminIsheetImportColumnWeb.clickOnCancelSelectFieldModal();
	}

	void scenario09FieldsAndColumnSetting()
	{
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		sectionList = adminIsheetManageColumnWeb.getAllSectionsName();
		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		Assert.assertTrue(adminIsheetImportColumnWeb.verifyDefaultColumnNameIsSameAsFieldName(fieldName1));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyDefaultColumnNameIsSameAsFieldName(fieldName4));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyDefaultColumnNameIsSameAsFieldName(fieldName6));

		optionsList.clear();
		optionsList.add(columnName1);
		optionsList.add(columnName2);
		optionsList.add(columnName3);
		optionsList.add(columnName4);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyColumnsInFieldsAndColumnSetting(optionsList));

		Assert.assertTrue(adminIsheetImportColumnWeb.verifyAllRowElementBelowColumnNameColumnIsEditable());
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyRemoveTooltopDisplayedForAllRows());
		adminIsheetImportColumnWeb.removeAllFields();

		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectOrDeselectAllFieldsFromSelectFieldModal(false);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName3, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName5, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		optionsList.clear();
		optionsList.add(singleLineColumnType);
		optionsList.add(multiLineColumnType);
		optionsList.add(numberColumnType);
		optionsList.add(dateAndTimeColumnType);
		optionsList.add(hyperlinkColumnType);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyAvailableColumnTypesInDropdown(fieldName1, optionsList));
		optionsList.clear();
		optionsList.add(choice);
		optionsList.add(userLookUp);
		optionsList.add(image);
		optionsList.add(attachment);
		optionsList.add(fileLink);
		optionsList.add(folderLink);
		optionsList.add(iSheetLink);
		optionsList.add(lookUp);
		optionsList.add(join);
		optionsList.add(calculation);
		optionsList.add(autoIncrement);
		Assert.assertFalse(adminIsheetImportColumnWeb.verifyAvailableColumnTypesInDropdown(fieldName1, optionsList));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyDefaultSelectedColumnTypeInFieldsAndColumnSetting(fieldName1, singleLineColumnType));
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyAvailableSectionsInDropdown(fieldName1, sectionList));

		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		adminIsheetImportColumnWeb.removeSelectedFieldsFromFieldsAndColumnSetting(fieldsValues);

		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		Assert.assertFalse(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		adminIsheetImportColumnWeb.clickOnSelectButton();
		Assert.assertFalse(adminIsheetImportColumnWeb.verifyCheckedFieldsOnModal(fieldName1, highqEngine));
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		fieldsValues.clear();
		fieldsValues.put(fieldName2, highqEngine);
		Assert.assertTrue(adminIsheetImportColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));
	}

	void scenario11HandlingErrorMessage()
	{
		adminIsheetImportColumnWeb.renameColumnNameInFieldsAndColumnSettingsBlock(fieldName4, kiraEngine, fieldName6);
		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMessageForDuplicateColumnName());
	}

	void scenario12HandlingErrorMessage()
	{
		adminIsheetImportColumnWeb.selectSectionFromDropdownInFieldsAndColumnSettingBlock(highqEngine, fieldName2, sectionName);
		adminIsheetImportColumnWeb.selectSectionFromDropdownInFieldsAndColumnSettingBlock(highqEngine, fieldName3, sectionName);
		adminIsheetImportColumnWeb.selectSectionFromDropdownInFieldsAndColumnSettingBlock(kiraEngine, fieldName5, sectionName);
		adminIsheetImportColumnWeb.selectSectionFromDropdownInFieldsAndColumnSettingBlock(kiraEngine, fieldName4, sectionName);
		adminIsheetImportColumnWeb.renameColumnNameInFieldsAndColumnSettingsBlock(fieldName4, kiraEngine, fieldName4);
		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMessageForSectionRequired());
	}

	void scenario13ValidationMessageWhenMoreThan20FieldsAdded()
	{
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectOrDeselectAllFieldsFromSelectFieldModal(true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMsgForSelectedMoreThan20Fields());
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectOrDeselectAllFieldsFromSelectFieldModal(false);
		adminIsheetImportColumnWeb.clickOnFilterIconInSelectFieldModal();
		optionsList.clear();
		optionsList.add(kiraEngine);
		adminIsheetImportColumnWeb.selectEngineFromFilter(optionsList);
		optionsList.clear();
		optionsList = adminIsheetImportColumnWeb.selectGivenNumberOfFieldsInSelectFieldModal(20);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetImportColumnWeb.selectSectionForGivenNumberOfFields(20);
		adminIsheetManageColumnWeb = adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		for (int i = 0; i < optionsList.size(); i++)
		{
			Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(optionsList.get(i)));
		}
	}

	void scenario10RenamingColumnNames()
	{
		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectOrDeselectAllFieldsFromSelectFieldModal(false);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName3, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName4, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName6, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName5, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();

		adminIsheetImportColumnWeb.selectSectionForGivenNumberOfFields(5);
		adminIsheetImportColumnWeb.renameColumnNameInFieldsAndColumnSettingsBlock(fieldName2, highqEngine, scriptAsName);
		adminIsheetImportColumnWeb.renameColumnNameInFieldsAndColumnSettingsBlock(fieldName3, highqEngine, specialCharacter);
		adminIsheetImportColumnWeb.renameColumnNameInFieldsAndColumnSettingsBlock(fieldName5, kiraEngine, simpleColumnName);
		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		optionsList.clear();
		optionsList.add(scriptAsName);
		optionsList.add(specialCharacter);
		optionsList.add(fieldName4);
		optionsList.add(simpleColumnName);
		optionsList.add(fieldName6);
		for (int i = 0; i < optionsList.size(); i++)
		{
			Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(optionsList.get(i)));
		}
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario14SimultaneousCaseOne() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName7, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetImportColumnWeb.selectSectionForGivenNumberOfFields(3);

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

		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMsg(errorMsg));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario15SimultaneousCaseTwo() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetImportColumnWeb = (AdminIsheetImportColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(importColumn);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetImportColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetImportColumnWeb.clickOnSelectButton();
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName7, true);
		adminIsheetImportColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName4, true);
		adminIsheetImportColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetImportColumnWeb.selectSectionForGivenNumberOfFields(4);

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
		simulAdminFilesWeb.enableDocumentAnalysisEngine(kiraEngine, analysisOption2);
		simulAdminFilesWeb.saveFilesChanges();
		simultaneousDriver.close();

		adminIsheetImportColumnWeb.clickSaveOnImportColumn();
		Assert.assertTrue(adminIsheetImportColumnWeb.verifyErrorMsg(errorMsg));
	}

}
