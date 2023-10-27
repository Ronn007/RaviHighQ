package com.highq.test.iSheet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.AdminAddIsheetWebLabels;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.labels.collaborate.AdminIsheetManageColumnsLabels;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
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
import com.highq.pageobjects.pages.AdminIsheetAddColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.LoginPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author janki.hirani
 *         Add Single Column -AI Hub
 */
public class IsheetTC2746 extends BannerPageWeb
{
	private WebDriver driver;
	String jsonFileName = "iSheet/preConfiguration_IsheetTC2746.json";
	String orgType = "Internal";
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
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData").get("Organization 1").get("user 1").get("newPassword").asText();;
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();
	String siteAdminEmail = resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get("user1").get("id").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText();
	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String addIsheetOption = "iSheet";
	String isheetTitle = "iSheet_DMD";
	String isheetTitle_FMD = "iSheet_FMD";
	String isheetTitle_Normal = "iSheet1";
	String enableIsheetVersionAudit = "TRUE";
	String value = "TRUE";

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage adminAddIsheetWeb;
	AdminIsheetManageColumnPage adminIsheetManageColumnWeb;
	AdminIsheetAddColumnPage adminIsheetAddColumnWeb;
	BaseIsheetTest baseIsheetTest;
	AdminFilesPage adminFilesWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData = new LinkedHashMap<>();

	LinkedHashMap<String, String> iSheetData;
	ConfigurationData configurationData = new ConfigurationData();

	BannerPage bannerPageWeb;
	BannerPage simulBannerPageWeb;
	DashboardPage simulDashboardWeb;
	AdminPage simulAdminPageWeb;
	AdminFilesPage simulAdminFilesWeb;

	String isheetTitleLabel = "title";
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
	String column = "Column";
	String importColumn = AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS;
	String manageColumns = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS;
	String documentAnalysis = AdminIsheetAddColumnLabel.DOCUMENTANALYSISENGINE;
	String engineErrorMsg = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ERRORMSG_SELECTENGINES;
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String fieldName1 = "Assignment";
	String fieldName2 = "Language";
	String fieldName3 = "License Grant";
	String fieldName4 = "Party Name";
	String space = "Space";
	String comma = "Comma";
	String smicol = "Semicolon";
	String newLine = "New line";
	String hypen = "Hypen";
	String pipe = "Pipe";
	String accessType = "Access type";
	String public1 = "Public";
	String filemetadata = "File metadata template";
	String folderMetadata = AdminAddIsheetWebLabels.SITEADMIN_MODULESETTINGS_ISHEETS_ADD_ISHEET_ALLOWSHEETASFOLDERMEATADATA;;
	String analysisOption1 = "ON";
	String analysisOption2 = "OFF";
	String option1 = "Enabled, default ON in every site";
	String option2 = "Enabled, default OFF in every site";
	String projectName = "M&A";
	String singleColumnName = "SingleLineColumn";
	String errorMsgForBothEngine = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ENGINES_UNAVAILABLE;
	String errorMsgForSingleEngine = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDENGINEOFF_ERRORMSG;
	String configKira = "Configure";
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	List<String> optionsList = new ArrayList<>();
	LinkedHashMap<String, String> fieldsValues = new LinkedHashMap<>();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void testIsheetTC2746() throws InterruptedException, IOException, ParseException, JSONException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Testcase start time: " + dtf.format(now));
		precondition();
		scenario01ToCheckOptionsInAddForDMD();
		scenario02ToCheckOptionsInAddForFMDAndNormal();
		scenario03ToCheckOptionsInAddForDMDWhenDADisabled();
		scenario04ToCheckValuesDisplayedInSourceAndAnalysisEng();
		scenario05ToCheckImportDataCheckBoxExcludingSupportedcolumnType();
		scenario06ToCheckImportDataCheckBoxIncludingSupportedcolumnType();
		scenario07ToCheckPropertiesOfImportDataFromExternalSource();
		scenario08ToCheckFieldsProperty();
		scenario09SaveColumnWithImportDataFromExternalSource();
		scenario10ToCheckImportDataSectionAfterChangingColumnType();
		scenario11EngineValueWhenOnlyOneEngineIsEnabled();
		scenario12SimultaneousCaseOne();
		scenario13SimultaneousCaseTwo();
		now = LocalDateTime.now();
		System.out.println("Testcase end time: " + dtf.format(now));
	}

	void scenario01ToCheckOptionsInAddForDMD() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetManageColumnWeb.clickOnAddDropdown();

		Assert.assertTrue(adminIsheetManageColumnWeb.verifyAddDropdown(column));
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyAddDropdown(importColumn));
	}

	void scenario02ToCheckOptionsInAddForFMDAndNormal() throws InterruptedException, ParseException
	{
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle_FMD, manageColumns);

		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddButton());
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(column));
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(importColumn));

		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();

		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(multiLineColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(numberColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(dateAndTimeColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(hyperlinkColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle_Normal, manageColumns);

		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddButton());
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(column));
		Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(importColumn));

		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();

		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(multiLineColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(numberColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(dateAndTimeColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		adminIsheetAddColumnWeb.selectColumnType(hyperlinkColumnType);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario03ToCheckOptionsInAddForDMDWhenDADisabled() throws InterruptedException
	{
		try
		{
			bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			siteAdminConfiguration(analysisOption2, analysisOption2);
			adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();
			adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);

			Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddButton());
			Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(column));
			Assert.assertFalse(adminIsheetManageColumnWeb.verifyAddDropdown(importColumn));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.logout();
		}
	}

	void scenario04ToCheckValuesDisplayedInSourceAndAnalysisEng() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		optionsList.clear();
		optionsList.add(documentAnalysis);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifySourceFromDropdown(optionsList));
		optionsList.clear();
		optionsList.add(highqEngine);
		optionsList.add(kiraEngine);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyListedEnginesInDropDown(optionsList));
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();

	}

	void scenario05ToCheckImportDataCheckBoxExcludingSupportedcolumnType() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
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
		for (String columnName : optionsList)
		{
			adminIsheetAddColumnWeb.selectColumnType(columnName);
			Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());
		}
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();

	}

	void scenario06ToCheckImportDataCheckBoxIncludingSupportedcolumnType() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);

		optionsList.clear();
		optionsList.add(singleLineColumnType);
		optionsList.add(multiLineColumnType);
		optionsList.add(numberColumnType);
		optionsList.add(dateAndTimeColumnType);
		optionsList.add(hyperlinkColumnType);
		for (String columnName : optionsList)
		{
			adminIsheetAddColumnWeb.selectColumnType(columnName);
			Assert.assertTrue(adminIsheetAddColumnWeb.verifyImportCheckBoxDisplayed());
			Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxSelcted());
		}

		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);

		Assert.assertTrue(adminIsheetAddColumnWeb.vrifySourceProprtyDisplayedWithDropdown());
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyAnalysisEngineDisplayedWithEngineDropdown());
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyFieldsPropertyDisplayedWithSelectButton());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario07ToCheckPropertiesOfImportDataFromExternalSource() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		Assert.assertTrue(((AdminIsheetAddColumnWeb) adminIsheetAddColumnWeb).verifySelectedSourceFromDropdown(documentAnalysis));
		adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyErrorMessageForSelectEngine(engineErrorMsg));
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyEnginesSelected(highqEngine + ", " + kiraEngine));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario08ToCheckFieldsProperty() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifySelectFieldsModal());
		Assert.assertTrue(adminIsheetAddColumnWeb.verifySaveButtonStatusInSelectFieldModal(false));
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName3, kiraEngine);
		fieldsValues.put(fieldName4, kiraEngine);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyFieldsWithEngineInSelectModal(fieldsValues));
		adminIsheetAddColumnWeb.enterValueInSearchBoxInSelectFieldModal(fieldName3);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifySearchedFieldIsDisplayed(fieldName3));
		adminIsheetAddColumnWeb.clearSearchBoxInSelectFieldModal();
		adminIsheetAddColumnWeb.clickOnFilterIconInSelectFieldModal();
		optionsList.clear();
		optionsList.add(highqEngine);
		adminIsheetAddColumnWeb.selectEngineFromFilter(optionsList);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyFilteredEnginesDisplayed(optionsList));
		adminIsheetAddColumnWeb.clearFilterInSelectFieldsModal();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyClearFilterIsDisabledAndNoAnyEngineIsSelected());

		adminIsheetAddColumnWeb.enterValueInSearchBoxInSelectFieldModal(fieldName2);
		adminIsheetAddColumnWeb.clickOnFilterIconInSelectFieldModal();
		adminIsheetAddColumnWeb.selectEngineFromFilter(optionsList);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyFilterAndSearchBothApplied(fieldName2, optionsList));
		adminIsheetAddColumnWeb.clearSearchBoxInSelectFieldModal();
		adminIsheetAddColumnWeb.clearFilterInSelectFieldsModal();

		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		fieldsValues.clear();
		fieldsValues.put(fieldName1, highqEngine);
		fieldsValues.put(fieldName2, highqEngine);
		fieldsValues.put(fieldName2, kiraEngine);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifySelectedFieldsDisplayed(fieldsValues));

		adminIsheetAddColumnWeb.clickOnSelectButton();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyCheckedFieldsOnModal(fieldName1, highqEngine));
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyCheckedFieldsOnModal(fieldName2, highqEngine));
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyCheckedFieldsOnModal(fieldName2, kiraEngine));
		adminIsheetAddColumnWeb.clickOnCancelSelectFieldModal();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyRemoveFieldTooltip());
		adminIsheetAddColumnWeb.removeField(fieldName1, highqEngine);

		Assert.assertTrue(adminIsheetAddColumnWeb.verifyDefaultSelectedDelimiterInDropdown(space));
		optionsList.clear();
		optionsList.add(space);
		optionsList.add(comma);
		optionsList.add(newLine);
		optionsList.add(smicol);
		optionsList.add(hypen);
		optionsList.add(pipe);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyDelimiterFieldsOptions(optionsList));

		adminIsheetAddColumnWeb.removeField(fieldName2, kiraEngine);

		Assert.assertFalse(adminIsheetAddColumnWeb.verifyDelimiterField());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario09SaveColumnWithImportDataFromExternalSource() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetManageColumnWeb = adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		Assert.assertTrue(adminIsheetManageColumnWeb.verifyColumnExist(singleColumnName));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario10ToCheckImportDataSectionAfterChangingColumnType() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();
		adminIsheetAddColumnWeb.selectColumnType(numberColumnType);
		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportCheckBoxSelcted());
		Assert.assertFalse(adminIsheetAddColumnWeb.verifyImportDataFieldModal());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}

	void scenario11EngineValueWhenOnlyOneEngineIsEnabled() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption2);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		optionsList.clear();
		optionsList.add(highqEngine);
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyListedEnginesInDropDown(optionsList));
		optionsList.clear();
		optionsList.add(kiraEngine);
		Assert.assertFalse(adminIsheetAddColumnWeb.verifyListedEnginesInDropDown(optionsList));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();

	}

	void scenario12SimultaneousCaseOne() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();

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

		adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyErrorMsg(errorMsgForBothEngine));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();

	}

	void scenario13SimultaneousCaseTwo() throws InterruptedException
	{
		bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		siteAdminConfiguration(analysisOption1, analysisOption1);
		adminIsheetWeb = ((AdminPage) adminFilesWeb).clickiSheetsInLeftPanel();

		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		adminIsheetAddColumnWeb = (AdminIsheetAddColumnPage) adminIsheetManageColumnWeb.selectFromAddDropdown(column);
		adminIsheetAddColumnWeb.selectColumnType(singleLineColumnType);
		adminIsheetAddColumnWeb.addColumnName(singleColumnName);
		adminIsheetAddColumnWeb.clickOnImportExternalDataCheckbox(true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(highqEngine, true);
		adminIsheetAddColumnWeb.selectEnginesFromDropdown(kiraEngine, true);
		adminIsheetAddColumnWeb.clickOnSelectButton();
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName1, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(kiraEngine, fieldName2, true);
		adminIsheetAddColumnWeb.selectDeselectFieldsFromModal(highqEngine, fieldName4, true);
		adminIsheetAddColumnWeb.clickOnSaveSelectFieldModal();

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

		adminIsheetAddColumnWeb.clickSaveOnAddColumn();
		Assert.assertTrue(adminIsheetAddColumnWeb.verifyErrorMsg(errorMsgForSingleEngine));
	}

	void preCondition() throws InterruptedException, ParseException
	{
		// bannerPageWeb = login(siteAdminEmail, siteAdminPassword);
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		// adminPageWeb = dashboardWeb.gotoAdminModule();
		baseIsheetTest = new BaseIsheetTest(driver);
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (adminIsheetWeb.isheetExist(isheetTitle))
		{
			adminIsheetWeb.deleteIsheet(isheetTitle);
		}

		if (adminIsheetWeb.isheetExist(isheetTitle_FMD))
		{
			adminIsheetWeb.deleteIsheet(isheetTitle_FMD);
		}

		if (adminIsheetWeb.isheetExist(isheetTitle_Normal))
		{
			adminIsheetWeb.deleteIsheet(isheetTitle_Normal);
		}

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(isheetTitle);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(filemetadata, true);
		adminIsheetWeb = adminAddIsheetWeb.addIsheetClickSave();

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		adminAddIsheetWeb.addIsheetTitle(isheetTitle_FMD);
		adminAddIsheetWeb.addIsheetSelectCheckBoxOption(folderMetadata, true);
		adminIsheetWeb = adminAddIsheetWeb.addIsheetClickSave();

		adminIsheetWeb.clickOnAddIsheet();
		adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
		baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
		iSheetData = new LinkedHashMap<>();
		iSheetData.put(isheetTitleLabel, isheetTitle_Normal);
		iSheetData.put(accessType, public1);
		adminIsheetWeb = baseIsheetTest.createIsheet(iSheetData, null);

		dashboardWeb = bannerPageWeb.gotoDashboard();
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

	void precondition() throws InterruptedException, IOException, JSONException, ParseException
	{
		siteAndUserConfiguration();
		preCondition();
		aspAndSystemAdminSetting();
		siteAdminConfiguration(analysisOption1, analysisOption1);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.logout();
	}
}
