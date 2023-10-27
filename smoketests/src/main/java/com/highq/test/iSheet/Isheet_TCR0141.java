package com.highq.test.iSheet;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AdminAddIsheetWebLabels;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.labels.collaborate.AdminIsheetLabels;
import com.highq.labels.collaborate.AdminIsheetManageDocumentTemplatesLabels;
import com.highq.labels.collaborate.AdminIsheetManagePermissionsLabels;
import com.highq.labels.collaborate.AdminIsheetManageViewLabels;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.IsheetData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.iSheetPageData.AddAutoIncrementColumnData;
import com.highq.pagedata.iSheetPageData.AddCalculationColumnData;
import com.highq.pagedata.iSheetPageData.AddChoiceColumnData;
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
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetEditViewPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageDocumentTemplatesPage;
import com.highq.pageobjects.base.AdminIsheetManagePermissionsPage;
import com.highq.pageobjects.base.AdminIsheetManageViewsPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminIsheetViewPermissionsPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.IsheetsPage;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Isheet_TCR0141 extends BannerPageWeb {
	private String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Isheet_TCR0141";
	String[] userNames = { "normal.user141", "site.admin141" };
	String domain = "isheet0141.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String addIsheetOption = "iSheet";
	String isheetTitle = "iSheet0141";
	String enableIsheetVersionAudit = "TRUE";

	Timestamp startTime;
	Timestamp endTime;
	String recipientMail = userNames[1] + "@" + domain;
	String subject = "Isheet_TCR0141 Share Isheet Automation";
	String message = "Testing functionality of Share Isheet.";
	String messageRecipient = userNames[1] + "@" + domain;
	String userName = "Normal User";
	String jsonFileName = "iSheet/preConfiguration_Isheet_TCR0141.json";
	String[] exportOptions = { IsheetLabels.ISHEET_MOREACTIONS_EXPORT_XLSX, IsheetLabels.ISHEET_MOREACTIONS_EXPORT_CSV,
			IsheetLabels.ISHEET_MOREACTIONS_EXPORT_XML, IsheetLabels.ISHEET_MOREACTIONS_EXPORT_IMPORTTEMPLATE };

	String manageColumns = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGECOLUMNS;
	String manageViews = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEVIEWS;
	String managePermissions = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MOREACTIONS_MANAGEPERMISSIONS;
	String defaultLabel = AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_DEFAULT;
	String draftItemsLabel = "Draft items";
	String allowLookupText = AdminAddIsheetWebLabels.SITEADMIN_MODULESETTINGS_ISHEETS_ADD_ISHEET;
	String isheetTitleLabel = "title";
	String columnNameLabel = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_COLUMNNAME;
	String columnTypeLabel = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_COLUMNTYPE;
	String permissionTypeView = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_VIEW; // isheet.admin.manage.permission.ui.view
	String permissionTypeEdit = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_EDIT; // isheet.admin.manage.permission.ui.edit
	String permissionScopeAll = AdminIsheetManagePermissionsLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEPERMISSIONS_ALL;
	String isheetActionDelete = IsheetLabels.ISHEET_DELETERECORD;
	String customModalDeleteRecordTitle = IsheetLabels.ISHEET_DELETE_DELETEMESSAGETITLE;
	String customModalDeleteRecordsMessage = IsheetLabels.ISHEET_DELETE_DELETERECORDSMESSAGE;
	String customModalDeleteRecordMessage = IsheetLabels.ISHEET_DELETE_DELETERECORDMESSAGE;
	String isheetViewDrftItems = "Draft items";
	String columnNameStatus = IsheetLabels.ISHEET_DRFTITEMS_STATUS;
	String statusTypeDraft = IsheetLabels.ISHEET_DRFTITEMS_STATUS_DRAFT;
	String statusTypeAutoSaved = IsheetLabels.ISHEET_DRFTITEMS_STATUS_AUTOSAVED;
	String customModalAutoSaveTitle = IsheetLabels.ISHEET_AUTOSAVED_TITLE;
	String customModalAutoSaveMessage = IsheetLabels.ISHEET_AUTOSAVED_MESSAGE;
	String recordDeletedGlobalMessage = "Deleted 1 of 1 selected records";
	String moreActionEdit = IsheetLabels.ISHEET_MOREACTIONS_EDIT;
	String moreActionView = IsheetLabels.ISHEET_MOREACTIONS_VIEW;
	String searchText = "Test";
	String searchIsheetText = "Single";
	String[] filterOptions = { "All", statusTypeAutoSaved, statusTypeDraft };
	String none = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NONE;
	String editPermissions = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_MOREACTIONS_EDITPERMISSIONS;
	String view = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_VIEW;
	String print = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_PRINT;
	String export = AdminIsheetManageViewLabels.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGEVIEWS_EDITPERMISSIONS_EXPORT;

	String isheetMoreActionShare = IsheetLabels.ISHEET_MOREACTIONS_SHARE;
	String isheetMoreActionPrint = IsheetLabels.ISHEET_MOREACTIONS_PRINT;
	String isheetMoreActionsExportToPDF = IsheetLabels.ISHEET_MOREACTIONS_EXPORTTOPDF;
	String isheetMoreActionsExport = IsheetLabels.ISHEET_MOREACTIONS_EXPORT;
	String isheetMoreActionsImport = IsheetLabels.ISHEET_MOREACTIONS_IMPORT;
	String isheetExportName = "ItemData.";
	String pdfExtension = IsheetLabels.ISHEET_EXPORT_PDFEXT;
	String xlsxExtension = "xlsx";
	String ddAndContractAutomationReportOption = "DD and contract automation report";

	DocumentAddDataPage addDoc;
	DocumentPage documentWeb;
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
	AdminIsheetManageViewsPage adminIsheetManageViewsWeb;
	AdminIsheetViewPermissionsPage adminIsheetViewPermissionsWeb;
	AdminIsheetEditViewPage adminIsheetEditViewWeb;
	AdminIsheetManageDocumentTemplatesPage adminIsheetManageDocumentTemplatesWeb;
	IsheetsPage isheetsWeb;
	BannerPage bannerPageWeb;
	AdminActivityPage adminActivityWeb;
	ActivityPage activityWeb;
	LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData = new LinkedHashMap<>();

	LinkedHashMap<String, String> iSheetConfig;
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
	AddChoiceColumnData addChoiceColumnData;

	IsheetData isheetData;

	/**
	 * ********************************************************* Precondition Data |
	 * STARTS ************************************************************
	 */

	// Isheet to be created
	String isheetName = "Test Data Isheet_0141";

	// Isheet Data

	String[][] iSheetRecords = { { "Test Column1", "Test Column2", "Test Column3" },
			{ "Test Column 1 Record 1", "Test Column 2 Record 1", "Test Column 3 Record 1" },
			{ "Test Column 1 Record 2", "Test Column 2 Record 2", "Test Column 3 Record 2" },
			{ "Test Column 1 Record 3", "Test Column 2 Record 3", "Test Column 3 Record 3" } };

	// Isheet Columns to be created
	String[] isheetColumns = { "Test Column1", "Test Column2", "Test Column3" };

	// Record 1 | DATA
	String[] testRecord1 = { "Test Column 1 Record 1", "Test Column 2 Record 1", "Test Column 3 Record 1" };

	// Record 2 | Data
	String[] testRecord2 = { "Test Column 1 Record 2", "Test Column 2 Record 2", "Test Column 3 Record 2" };

	// Record 3 | DATA
	String[] testRecord3 = { "Test Column 1 Record 3", "Test Column 2 Record 3", "Test Column 3 Record 3" };

	// Folder to be created
	String[] folderNames = { "Test Folder1", "Edited Test Folder1" };
	String folderDescription = "folderDescription";
	String editedFolderName = folderNames[1];

	// Files to upload
	String[] filesToUpload = { "doc1.txt", "doc2.txt", "doc3.txt" };

	/**
	 * ********************************************************* Precondition Data |
	 * ENDS ************************************************************
	 */

	/**
	 * ********************************************************* Column
	 * Configuration Data | STARTS
	 * ************************************************************
	 */

	String columnDescription = "Column Description";
	String columnWidth = "200";
	boolean mandatory = false;
	boolean allowSearch = true;
	boolean addToDefaultView = true;

	// Single Line Text Column
	String singleLineColumnName = "Single Line Text Column";
	String singleLineColumnType = "Single line text";
	String maximumChars = "255";
	boolean allowPopulation = false;

	// Multiple Line Text Column
	String multiLineColumnName = "Multi Line Text Column";
	String multiLineColumnType = "Multiple line text";
	String numberOfLines = "4";
	String width = "200";
	boolean allowRichHtmlTest = true;

	// Choice Column
	String choiceColumnName = "Choice Column";
	String choiceColumnType = "Choice";

	// Number Column
	String numberColumnName = "Number Column";
	String numberColumnType = "Number";
	String minValue = "1";
	String maxValue = "1000";
	String decimalPlaces = "3";
	boolean showAsPercentage = true;
	boolean showThousandSeparators = true;

	// Date and Time Column
	String dateAndTimeColumnName = "Date and Time Column";
	String dateAndTimeColumnType = "Date and time";
	String dateAndTimeColumnFormat = "Date Only";
	String dateFormat = "DD MMM YYYY";
	String defaultDateValue = none;

	// User Lookup Column
	String userLookupColumnName = "User Lookup Column";
	String userLookupColumnType = "User lookup";
	String lookup = "All Site Users";
	String fieldDisplay = "Email Address";
	boolean allowMultipleUsers = true;

	// Hyperlink Column
	String hyperlinkColumnName = "Hyperlink Column";
	String hyperlinkColumnType = "Hyperlink";

	// Image Column
	String imageColumnName = "Image Column";
	String imageColumnType = "Image";
	String selectMethod = "Image as attachment";

	// Attachment Column
	String attachmentColumnName = "Attachment Column";
	String attachmentColumnType = "Attachment";

	// File link Column
	String fileLinkColumnName = "File link Column";
	String fileLinkColumnType = "File link";

	// Folder Link Column
	String folderLinkColumnName = "Folder link Column";
	String folderLinkColumnType = "Folder link";

	// Isheet Link Coulmn
	String isheetLinkColumnName = "ISheet link Column";
	String iSheetLinkColumnType = "iSheet link";
	String iShhetLinkDefaultValue = "iSheet link default value";
	String[] sheetNames = { "Test Data Isheet_0141 (Isheet_TCR0141)" };
	boolean allowUsersToRenameLinks = true;

	// Lookup Column
	String lookupColumnName = "Lookup Column";
	String lookupColumnType = "Lookup";
	String lookupSheet = isheetName + " (" + siteName + ")"; //
	String lookupView = defaultLabel;
	String[] lookupColumnList = isheetColumns;
	boolean allowMultipleValues = true;
	boolean restrictDelete = false;

	// Join Column
	String joinColumnName = "Join Column";
	String joinColumnType = "Join";
	String joinLinkName = "JoinLink";
	String joinSheetName = isheetTitle;
	String joinSheetSite = siteName;
	String joinDisplayView = defaultLabel;
	String joinConditionCurrentSheet = singleLineColumnName;
	String joinConditionTargetSheet = singleLineColumnName;

	// Calculation Column
	String calculationColumnName = "Calculation Column";
	String calculationColumnType = "Calculation";
	String calculationDecimalPlaces = "3";
	String calculationFormula = numberColumnName + "+" + numberColumnName;
	String[] calculationColumns = { numberColumnName };
	LinkedHashMap<String, Object> calculationsColumnValues;
	boolean calculationShowThousandSeparators = true;
	boolean calculationShowAsPercentage = true;

	// Auto increment Column
	String autoIncrementColumnName = "Auto increment Column";
	String autoIncrementColumnType = "Auto increment";
	String autoIncrementStartValue = "1";
	String autoIncrementSuffix = "1";
	String autoIncrementPrefix = "1";
	String autoIncrementMinLength = "1";

	/**
	 * ********************************************************* Column
	 * Configuration Data | ENDS
	 * ************************************************************
	 */

	/**
	 * ********************************************************* Record 1 Data |
	 * STARTS ************************************************************
	 */
	String singleLineColumnData = "Single Line Column Data";
	String multiLineColumnData = "Multiline Column Data";
	String choiceColumnData = "No";
	String numberColumnData = "20.000";
	String dateAndTimeColumnData = "15 Feb 2018";
	String[] userLookupData = { userNames[0] + "@" + domain };
	String hyperLinkDisplayName = "Google Link";
	String hyperLinkURL = "https://www.google.co.in";
	String imageData = "automation.jpg";
	String[] attachmentData = { filesToUpload[0] };
	String isheetLinkDataSheet = isheetName + " (" + siteName + ")";
	String[] files = { filesToUpload[1] };
	String[] folders = { folderNames[0] };
	// String[] addMenuItem = {"Files", "New folder"};
	String[] addMenuItem = { "Files", "Folder" };
	String[] multiFiles = { "doc1.txt", "doc2.txt", "doc3.txt" };

	/**
	 * ********************************************************* Record 1 Data |
	 * ENDS ************************************************************
	 */

	/**
	 * ********************************************************* Record 1 Data |
	 * STARTS ************************************************************
	 */
	String editedSingleLineColumnData = "Edited Single Line Column Data";
	String editedMultiLineColumnData = "Edited Multiline Column Data";
	String editedChoiceColumnData = "Yes";
	String editedNumberColumnData = "120.000";
	String editedDateAndTimeColumnData = "25 Feb 2018";
	String[] editedUserLookupData = { userNames[1] + "@" + domain };
	String editedHyperLinkDisplayName = "Edited Google Link";
	String editedHyperLinkURL = "https://mail.google.com";
	String editedImageData = "images.png";
	String[] editedAttachmentData = { filesToUpload[1] };
	String[] editedFolderLinkData = { editedFolderName };
	String editedIsheetLinkDataSheet = isheetName + " (" + siteName + ")";
	String[] editedFiles = { filesToUpload[2] };

	/**
	 * ********************************************************* Record 1 Data |
	 * ENDS ************************************************************
	 */

	/**
	 * ********************************************************* DD/CA Reports Data
	 * | STARTS ************************************************************
	 */

	String siteWideTemplateName = "SiteWideTemplate";
	String singleRecordTemplateName = "SingleRecordTemplate";
	String templateName = "Isheet_TCR0141_dd_template.docx";
	String docTypeWord = "Word";
	String docTypePDF = "PDF";
	String ddReports = IsheetLabels.ISHEET_MOREACTIONS_DDREPORTS;
	String siteWideTemplateFile = "SiteWideTemplate.docx";
	String singleRecordTemplateFile = "SingleRecordTemplate.pdf";
	String siteWideTemplateModalMessage = "The document has been saved to the specified folder";
	String singleRecordTemplateModalMessage = "The document has been saved to the specified folder and attached to the iSheet record";

	/**
	 *
	 */
	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 * @throws UnsupportedFlavorException
	 * @throws JSONException
	 */
	@Test(priority = 1, invocationCount = 1)
	public void isheetTCR0141()
			throws InterruptedException, IOException, ParseException, UnsupportedFlavorException, JSONException {
		precondition();
		preCondition();
		scenario1();
		scenario2();
	}

	/**
	 * @throws InterruptedException
	 * @throws ParseException
	 * @throws IOException
	 */
	void preCondition() throws InterruptedException, ParseException, IOException {
		baseIsheetTest = new BaseIsheetTest(driver);

		/**********************************************************************
		 * Enable Isheet Version/Audit
		 *****************************************************************/
		dashboardWeb = gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setEnableIsheetVersionAuditOption(enableIsheetVersionAudit);
		aspConfigurationWeb.saveConfigurations();
		waitTillGlobalMessageDissappears();
		aspConfigurationWeb.setDDAndContractAutomationReport(ddAndContractAutomationReportOption);
		aspConfigurationWeb.saveConfigurations();
		waitTillGlobalMessageDissappears();
		/***************************************************************************
		 * Hub Configuration
		 *********************************************************************/
		// (This should be checked when instance is configured)
		// if (!verifyHubConfiguration())
		// {
		// configureHub();
		// }

		Assert.assertTrue(verifyHubConfiguration());

		/***********************************************************************
		 * Microservice Configuration
		 ***************************************************************/
		// (This should be checked when instance is configured)
		// if (!verifyMicroserviceConfiguration())
		// {
		// configureMicroService();
		// }

		waitTillGlobalMessageDissappears();

		/**************************************************************
		 * Add Isheet and Records to be used for Lookup
		 ******************************************************/
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (!adminIsheetWeb.isheetExist(isheetName)) {
			adminIsheetWeb.clickOnAddIsheet();
			adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
			baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
			iSheetConfig = new LinkedHashMap<>();
			iSheetConfig.put(isheetTitleLabel, isheetName);
			adminAddIsheetWeb.addIsheetSelectCheckBoxOption(allowLookupText, true);
			adminIsheetWeb = baseIsheetTest.createIsheet(iSheetConfig, null);
		}
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb
				.selectOptionOnIsheetMoreAction(isheetName, manageColumns);
		for (int i = 0; i < iSheetRecords[0].length; i++) {
			if (!adminIsheetManageColumnWeb.verifyColumnExist(iSheetRecords[0][i])) {
				Map<String, Object> singleLineTextColumnData = new LinkedHashMap<>();
				singleLineTextColumnData.put(columnNameLabel, iSheetRecords[0][i]);
				singleLineTextColumnData.put(columnTypeLabel, singleLineColumnType);
				adminIsheetManageColumnWeb = createSingleLineTextColumn(singleLineTextColumnData);
			}
		}

		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetName);
		Map<String, String> singleLineText;
		isheetData = new IsheetData();
		String[] columnTypesAddRecord = { "single line text" };
		for (int i = 1; i < iSheetRecords.length; i++) {
			singleLineText = new LinkedHashMap<>();
			isheetsWeb.clickAdd();
			for (int j = 0; j < iSheetRecords[i].length; j++) {
				singleLineText.put(iSheetRecords[0][j], iSheetRecords[i][j]);
			}
			isheetData.setSingleLineText(singleLineText);
			isheetsWeb.addRecord(columnTypesAddRecord, isheetData);
			isheetsWeb.clickAddOnAddRecordModal();
		}

		/*****************************************************************************
		 * Add Files
		 ************************************************************************/
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(addMenuItem[0]);
		for (int i = 0; i < multiFiles.length; i++) {
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(multiFiles[i]);
			documentWeb.addFile(addDoc, null);
		}
		documentWeb.clickAddInModal();

		/*****************************************************************************
		 * Add Folders
		 **********************************************************************/
		for (int i = 0; i < folderNames.length; i++) {
			documentWeb = dashboardWeb.gotoFileModule();
			documentWeb.selectItemFromAdd(addMenuItem[1]);
			documentWeb.createNewFolderInRoot(folderNames[i], folderDescription);
			documentWeb.clickAddInModal();
		}

		/*************************************************************
		 * Create isheet with all types of columns
		 ***********************************************************/

		adminPageWeb = gotoAdminModule();
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();

		if (!adminIsheetWeb.isheetExist(isheetTitle)) {
			adminIsheetWeb.clickOnAddIsheet();
			adminAddIsheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions(addIsheetOption);
			baseIsheetTest.adminAddIsheetWeb = adminAddIsheetWeb;
			iSheetConfig = new LinkedHashMap<>();
			iSheetConfig.put(isheetTitleLabel, isheetTitle);
			adminIsheetWeb = baseIsheetTest.createIsheet(iSheetConfig, null);
		}
		adminIsheetManageColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb
				.selectOptionOnIsheetMoreAction(isheetTitle, manageColumns);
		if (!adminIsheetManageColumnWeb.verifyColumnExist(singleLineColumnName)) {
			adminIsheetManageColumnWeb = createSingleLineTextColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(multiLineColumnName)) {
			adminIsheetManageColumnWeb = createMultiLineTextColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(choiceColumnName)) {
			adminIsheetManageColumnWeb = createChoiceColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(numberColumnName)) {
			adminIsheetManageColumnWeb = createNumberColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(dateAndTimeColumnName)) {
			adminIsheetManageColumnWeb = createDateAndTimeColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(userLookupColumnName)) {
			adminIsheetManageColumnWeb = createUserLookupColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(hyperlinkColumnName)) {
			adminIsheetManageColumnWeb = createHyperlinkColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(imageColumnName)) {
			adminIsheetManageColumnWeb = createImageColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(attachmentColumnName)) {
			adminIsheetManageColumnWeb = createAttachmentColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(fileLinkColumnName)) {
			adminIsheetManageColumnWeb = createFileLinkColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(folderLinkColumnName)) {
			adminIsheetManageColumnWeb = createFolderLinkColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(isheetLinkColumnName)) {
			adminIsheetManageColumnWeb = createIsheetLinkColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(lookupColumnName)) {
			adminIsheetManageColumnWeb = createLookupColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(joinColumnName)) {
			adminIsheetManageColumnWeb = createJoinColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(calculationColumnName)) {
			adminIsheetManageColumnWeb = createCalculationColumn();
		}
		if (!adminIsheetManageColumnWeb.verifyColumnExist(autoIncrementColumnName)) {
			adminIsheetManageColumnWeb = createAutoIncrementColumn();
		}

		adminIsheetWeb = adminIsheetManageColumnWeb.manageColumnsClickBack();

		/*******************************************************************
		 * Create isheet View
		 ******************************************************************/
		adminIsheetManageViewsWeb = (AdminIsheetManageViewsPage) adminIsheetWeb
				.selectOptionOnIsheetMoreAction(isheetTitle, manageViews);
		adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(defaultLabel);
		adminIsheetEditViewWeb.clickMoveRightAll();
		adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();

		LinkedHashMap<String, Boolean> viewPermission = new LinkedHashMap<>();
		viewPermission.put(view, true);
		viewPermission.put(print, true);
		viewPermission.put(export, true);
		LinkedHashMap<String, Map<String, Boolean>> viewPermissionData = new LinkedHashMap<>();
		viewPermissionData.put(userNames[0] + "@" + domain, viewPermission);
		adminIsheetViewPermissionsWeb = (AdminIsheetViewPermissionsPage) adminIsheetManageViewsWeb
				.selectOptionOnViewMoreAction(defaultLabel, editPermissions);
		adminIsheetViewPermissionsWeb.setAccessToAllUsers(false);
		adminIsheetViewPermissionsWeb.setIsheetPermission(viewPermissionData);
		adminIsheetViewPermissionsWeb.clickSaveOnViewPermissions();

		adminIsheetEditViewWeb = adminIsheetManageViewsWeb.selectView(draftItemsLabel);
		adminIsheetEditViewWeb.clickMoveRightAll();
		adminIsheetManageViewsWeb = adminIsheetEditViewWeb.clickSaveOnAddView();

		adminIsheetWeb = adminIsheetManageViewsWeb.clickBackOnManageViews();

		/*******************************************************************
		 * Set Isheet Permissions
		 ******************************************************************/
		HashMap<String, Boolean> viewEditPermissionForAll = new HashMap<>();
		viewEditPermissionForAll.put(permissionTypeView, true);
		viewEditPermissionForAll.put(permissionTypeEdit, true);
		HashMap<String, Map<String, Boolean>> iSheetScopePermission = new HashMap<>();
		iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
		iSheetScopePermission.put(permissionScopeAll, viewEditPermissionForAll);
		permissionData.put(userNames[0] + "@" + domain, iSheetScopePermission);

		adminIsheetManagePermissionsWeb = (AdminIsheetManagePermissionsPage) adminIsheetWeb
				.selectOptionOnIsheetMoreAction(isheetTitle, managePermissions);
		adminIsheetManagePermissionsWeb.selectEnablePermission(true);
		adminIsheetManagePermissionsWeb.setPermission(permissionData);
		adminIsheetWeb = adminIsheetManagePermissionsWeb.clickSaveOnEnableIsheetPermission();

		adminIsheetManageDocumentTemplatesWeb = adminIsheetWeb.clickManageDocumentTemplatesLink();
		if (!adminIsheetManageDocumentTemplatesWeb.verifyTemplateExists(siteWideTemplateName)) {
			adminIsheetManageDocumentTemplatesWeb.clickAddTemplate();
		} else {
			adminIsheetManageDocumentTemplatesWeb.clickOnTemplate(siteWideTemplateName);
		}
		adminIsheetManageDocumentTemplatesWeb.addTemplateName(siteWideTemplateName);
		adminIsheetManageDocumentTemplatesWeb.selectTemplateType(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_TEMPLATETYPE_SITEWIDE);
		adminIsheetManageDocumentTemplatesWeb.addTemplateFile(templateName);
		adminIsheetManageDocumentTemplatesWeb.selectDocumentGenerationType(docTypeWord);
		adminIsheetManageDocumentTemplatesWeb.selectUserPermissionOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_USERPERMISSION_DOWNLOADDOCUMENT,
				true);
		adminIsheetManageDocumentTemplatesWeb.selectUserPermissionOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_USERPERMISSION_SAVEDOCUMENTTOFOLDER,
				true);
		adminIsheetManageDocumentTemplatesWeb.selectSaveDocumentOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_SAVEDOCUMENT_SPECIFIEDFOLDER,
				true);
		adminIsheetManageDocumentTemplatesWeb.clickChooseButton();
		adminIsheetManageDocumentTemplatesWeb.setDocumentFolderLocation(folderNames[0]);
		adminIsheetManageDocumentTemplatesWeb.clickSaveOnChooseLocationModal();
		adminIsheetManageDocumentTemplatesWeb.clickSaveOnAddTemplateModal();

		if (!adminIsheetManageDocumentTemplatesWeb.verifyTemplateExists(singleRecordTemplateName)) {
			adminIsheetManageDocumentTemplatesWeb.clickAddTemplate();
		} else {
			adminIsheetManageDocumentTemplatesWeb.clickOnTemplate(singleRecordTemplateName);
		}
		adminIsheetManageDocumentTemplatesWeb.addTemplateName(singleRecordTemplateName);
		adminIsheetManageDocumentTemplatesWeb.selectTemplateType(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_TEMPLATETYPE_SINGLERECORD);
		adminIsheetManageDocumentTemplatesWeb.selectLinkedIsheet(isheetTitle);
		adminIsheetManageDocumentTemplatesWeb.addTemplateFile(templateName);
		adminIsheetManageDocumentTemplatesWeb.selectDocumentGenerationType(docTypePDF);
		adminIsheetManageDocumentTemplatesWeb.selectUserPermissionOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_USERPERMISSION_DOWNLOADDOCUMENT,
				true);
		adminIsheetManageDocumentTemplatesWeb.selectUserPermissionOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_USERPERMISSION_SAVEDOCUMENTTOFOLDER,
				true);
		adminIsheetManageDocumentTemplatesWeb.selectSaveDocumentOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_SAVEDOCUMENT_SPECIFIEDFOLDER,
				true);
		adminIsheetManageDocumentTemplatesWeb.clickChooseButton();
		adminIsheetManageDocumentTemplatesWeb.setDocumentFolderLocation(folderNames[0]);
		adminIsheetManageDocumentTemplatesWeb.clickSaveOnChooseLocationModal();
		adminIsheetManageDocumentTemplatesWeb.selectSaveDocumentOption(
				AdminIsheetManageDocumentTemplatesLabels.ISHEET_MANAGEDOCUMENTTEMPLATE_ADDTEMPLATE_SAVEDOCUMENT_ISHEETCOLUMN,
				true);
		adminIsheetManageDocumentTemplatesWeb.selectIsheetColumnToSaveDocument(attachmentColumnName);
		adminIsheetManageDocumentTemplatesWeb.clickSaveOnAddTemplateModal();

		isheetsWeb = adminIsheetManageDocumentTemplatesWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);

		setUpIsheetDataAddRecord();

		if (!isheetsWeb.verifyIsheetRecordsEmpty()) {
			isheetsWeb.setAllIsheetRecordsCheckbox(true);
			isheetsWeb.selectIsheetActions(isheetActionDelete);
			isheetsWeb.clickOKOnCustomModal(customModalDeleteRecordTitle);
		}

		isheetsWeb.clickAdd();

		String[] columnTypesAddRecordAll = { "single line text", "multiple line text", "number", "date and time",
				"user lookup", "hyperlink", "image", "attachment", "file link", "folder link", "isheet link", "lookup",
				"choice" };

		isheetsWeb.addRecord(columnTypesAddRecordAll, isheetData);
		isheetsWeb.clickAddOnAddRecordModal();
		logout();
	}

	public void scenario1() throws IOException {
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		isheetsWeb.openIsheetMoreActions();
		Assert.assertFalse(isheetsWeb.verifyOptionFromIsheetMoreActions(ddReports));
		isheetsWeb.gotoDashboard();
		isheetsWeb.logout();

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		isheetsWeb.openIsheetMoreActions();
		Assert.assertTrue(isheetsWeb.verifyOptionFromIsheetMoreActions(ddReports));
		isheetsWeb.selectOptionFromIsheetMoreActions(ddReports);
		isheetsWeb.chooseTemplateToGenerateDocument(siteWideTemplateName);
		isheetsWeb.clickGenerateButton();
		isheetsWeb.waitTillDocumentGetsGenerated();

		Assert.assertTrue(isheetsWeb.verifyMessageOfDocumentGeneratedModal(siteWideTemplateModalMessage));
		Assert.assertTrue(isheetsWeb.verifyHeaderOfDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyTextOfDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyDownloadButtonOnDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifySaveButtonOnDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyCloseButtonOnDocumentGeneratedModal());

		isheetsWeb.clickDocumentLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(isheetsWeb.verifyDocumentTemplateInFilePreviewWindow(siteWideTemplateFile));
		isheetsWeb.cleanDownloadsFolder();
		isheetsWeb.clickDownloadOnFilePreviewPage();
		isheetsWeb.closeFilePreviewWindow();
		isheetsWeb.waitForFileGettingDownloaded();
		Assert.assertTrue(isheetsWeb.verifyDownloadedDocumentTemplate(siteWideTemplateFile));

		isheetsWeb.cleanDownloadsFolder();
		isheetsWeb.clickDownloadOnDocumentGeneratedModal();
		isheetsWeb.waitForFileGettingDownloaded();
		Assert.assertTrue(isheetsWeb.verifyDownloadedDocumentTemplate(siteWideTemplateFile));

		documentWeb = isheetsWeb.clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(documentWeb.verifyFolderTitle(folderNames[0]));
		Assert.assertTrue(documentWeb.verifyDocumentTemplateSaved(siteWideTemplateFile));

		isheetsWeb = documentWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		isheetsWeb.openIsheetMoreActions();
		Assert.assertTrue(isheetsWeb.verifyOptionFromIsheetMoreActions(ddReports));
		isheetsWeb.selectOptionFromIsheetMoreActions(ddReports);
		isheetsWeb.chooseTemplateToGenerateDocument(siteWideTemplateName);
		isheetsWeb.clickGenerateButton();
		isheetsWeb.waitTillDocumentGetsGenerated();

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());
		Assert.assertTrue(isheetsWeb.verifyHeaderOfSaveToSiteFolderModal(siteWideTemplateFile));
		Assert.assertTrue(isheetsWeb.verifySiteLabelOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyDropDownOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyDefaultSiteOnSaveToSiteFolderModal(siteName));
		Assert.assertTrue(isheetsWeb.verifyChooseFolderLabelOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyExpandAllOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyCollapseAllOnSaveToSiteFolderModal());

		isheetsWeb.clickExpandAllOnSaveToSiteFolderModal();
		Assert.assertTrue(isheetsWeb.verifyExpandedTree());
		isheetsWeb.clickCollapseAllOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifyExpandedTree());

		isheetsWeb.clickCancleOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickCloseOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.setSiteFolderLocationToSaveDocument(folderNames[1]);
		isheetsWeb.clickSaveOnSaveToSiteFolderModal();
		documentWeb = isheetsWeb.clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(documentWeb.verifyFolderTitle(folderNames[1]));
		Assert.assertTrue(documentWeb.verifyDocumentTemplateSaved(siteWideTemplateFile));
		documentWeb.gotoDashboard();
		documentWeb.logout();
	}

	public void scenario2() throws IOException {
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		String[] columnName = null;
		String operation = singleRecordTemplateName;
		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);
		isheetsWeb.waitTillDocumentGetsGenerated();

		Assert.assertTrue(isheetsWeb.verifyMessageOfDocumentGeneratedModal(singleRecordTemplateModalMessage));
		Assert.assertTrue(isheetsWeb.verifyHeaderOfDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyTextOfDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyDownloadButtonOnDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifySaveButtonOnDocumentGeneratedModal());
		Assert.assertTrue(isheetsWeb.verifyCloseButtonOnDocumentGeneratedModal());

		isheetsWeb.clickDocumentLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(isheetsWeb.verifyDocumentTemplateInFilePreviewWindow(singleRecordTemplateFile));
		isheetsWeb.cleanDownloadsFolder();
		isheetsWeb.clickDownloadOnFilePreviewPage();
		isheetsWeb.closeFilePreviewWindow();
		isheetsWeb.waitForFileGettingDownloaded();
		Assert.assertTrue(isheetsWeb.verifyDownloadedDocumentTemplate(singleRecordTemplateFile));

		isheetsWeb.cleanDownloadsFolder();
		isheetsWeb.clickDownloadOnDocumentGeneratedModal();
		isheetsWeb.waitForFileGettingDownloaded();
		Assert.assertTrue(isheetsWeb.verifyDownloadedDocumentTemplate(singleRecordTemplateFile));

		isheetsWeb.clickIsheetRecordLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(
				isheetsWeb.verifyAttachmentRecordForDocumentTemplate(attachmentColumnName, singleRecordTemplateFile));
		isheetsWeb.closeViewRecordModal();

		documentWeb = isheetsWeb.clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(documentWeb.verifyFolderTitle(folderNames[0]));
		Assert.assertTrue(documentWeb.verifyDocumentTemplateSaved(singleRecordTemplateFile));

		isheetsWeb = dashboardWeb.gotoIsheetsModule();
		isheetsWeb.selectIsheetFromHeader(isheetTitle);
		columnName = null;
		operation = singleRecordTemplateName;
		isheetsWeb.selectOptionFromMoreOptionsOfIsheetRecord(columnName, isheetData, operation);
		isheetsWeb.waitTillDocumentGetsGenerated();

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());
		Assert.assertTrue(isheetsWeb.verifyHeaderOfSaveToSiteFolderModal(singleRecordTemplateFile));
		Assert.assertTrue(isheetsWeb.verifySiteLabelOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyDropDownOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyDefaultSiteOnSaveToSiteFolderModal(siteName));
		Assert.assertTrue(isheetsWeb.verifyChooseFolderLabelOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyExpandAllOnSaveToSiteFolderModal());
		Assert.assertTrue(isheetsWeb.verifyCollapseAllOnSaveToSiteFolderModal());

		isheetsWeb.clickExpandAllOnSaveToSiteFolderModal();
		Assert.assertTrue(isheetsWeb.verifyExpandedTree());
		isheetsWeb.clickCollapseAllOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifyExpandedTree());

		isheetsWeb.clickCancleOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickCloseOnSaveToSiteFolderModal();
		Assert.assertFalse(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.clickSaveFolderOnDocumentGeneratedModal();
		Assert.assertTrue(isheetsWeb.verifySaveToSiteFolderModalOpened());

		isheetsWeb.setSiteFolderLocationToSaveDocument(folderNames[1]);
		isheetsWeb.clickSaveOnSaveToSiteFolderModal();
		documentWeb = isheetsWeb.clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage();
		Assert.assertTrue(documentWeb.verifyFolderTitle(folderNames[1]));
		Assert.assertTrue(documentWeb.verifyDocumentTemplateSaved(siteWideTemplateFile));
		documentWeb.gotoDashboard();
		documentWeb.logout();

	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createSingleLineTextColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addSingleLineTextColumnData = new AddSingleLineTextColumnData();
		addSingleLineTextColumnData.setColumnName(singleLineColumnName);
		addSingleLineTextColumnData.setColumnType(singleLineColumnType);
		addSingleLineTextColumnData.setDescription(columnDescription);
		addSingleLineTextColumnData.setMaxChar(maximumChars);
		addSingleLineTextColumnData.setColumnWidth(columnWidth);
		addSingleLineTextColumnData.setMandatory(mandatory);
		addSingleLineTextColumnData.setAllowSearch(allowSearch);
		addSingleLineTextColumnData.setAllowFieldPopulation(allowPopulation);
		addSingleLineTextColumnData.setAddToDefaultView(addToDefaultView);
		return baseIsheetTest.addSingleLineTextColumn(addSingleLineTextColumnData);
	}

	/**
	 * @param singleLineTextColumnData
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createSingleLineTextColumn(Map<String, Object> singleLineTextColumnData)
			throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		return baseIsheetTest.addSingleLineTextColumn(singleLineTextColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createMultiLineTextColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addMultipleLineTextColumnData = new AddMultipleLineTextColumnData();
		addMultipleLineTextColumnData.setColumnName(multiLineColumnName);
		addMultipleLineTextColumnData.setColumnType(multiLineColumnType);
		addMultipleLineTextColumnData.setDescription(columnDescription);
		addMultipleLineTextColumnData.setNumberOfLines(numberOfLines);
		addMultipleLineTextColumnData.setWidth(width);
		addMultipleLineTextColumnData.setColumnWidth(columnWidth);
		addMultipleLineTextColumnData.setMandatory(mandatory);
		addMultipleLineTextColumnData.setAllowSearch(allowSearch);
		addMultipleLineTextColumnData.setAllowFieldPopulation(allowPopulation);
		addMultipleLineTextColumnData.setAddToDefaultView(addToDefaultView);
		addMultipleLineTextColumnData.setAllowRichHtmlext(allowRichHtmlTest);
		return baseIsheetTest.addMultipleLineTextColumn(addMultipleLineTextColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createNumberColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addNumberColumnData = new AddNumberColumnData();
		addNumberColumnData.setColumnName(numberColumnName);
		addNumberColumnData.setColumnType(numberColumnType);
		addNumberColumnData.setDescription(columnDescription);
		addNumberColumnData.setMinValue(minValue);
		addNumberColumnData.setMaxValue(maxValue);
		addNumberColumnData.setDecimalValue(decimalPlaces);
		addNumberColumnData.setColumnWidth(columnWidth);
		addNumberColumnData.setShowAsPercentage(showAsPercentage);
		addNumberColumnData.setShowThousandSeparator(showThousandSeparators);
		addNumberColumnData.setMandatory(mandatory);
		addNumberColumnData.setAllowSearch(allowSearch);
		addNumberColumnData.setAddToDefaultView(addToDefaultView);
		return baseIsheetTest.addNumberColumn(addNumberColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	private AdminIsheetManageColumnWeb createDateAndTimeColumn() throws InterruptedException, ParseException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addDateAndTimeColumnData = new AddDateAndTimeColumnData();
		addDateAndTimeColumnData.setColumnName(dateAndTimeColumnName);
		addDateAndTimeColumnData.setColumnType(dateAndTimeColumnType);
		addDateAndTimeColumnData.setDescription(columnDescription);
		addDateAndTimeColumnData.setSelectTheFormat(dateAndTimeColumnFormat);
		addDateAndTimeColumnData.setDateFormat(dateFormat);
		addDateAndTimeColumnData.setDefaultDateOption(defaultDateValue);
		return baseIsheetTest.addDateAndTimeColumn(addDateAndTimeColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createUserLookupColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addUserLookupColumnData = new AddUserLookupColumnData();
		addUserLookupColumnData.setColumnName(userLookupColumnName);
		addUserLookupColumnData.setColumnType(userLookupColumnType);
		addUserLookupColumnData.setDescription(columnDescription);
		addUserLookupColumnData.setColumnWidth(columnWidth);
		addUserLookupColumnData.setSelectLookup(lookup);
		addUserLookupColumnData.setFieldDisplay(fieldDisplay);
		addUserLookupColumnData.setAllowMultipleUsers(allowMultipleUsers);
		return baseIsheetTest.addUserLookupColumn(addUserLookupColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createHyperlinkColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addHyperlinkColumnData = new AddIsheetColumnData();
		addHyperlinkColumnData.setColumnName(hyperlinkColumnName);
		addHyperlinkColumnData.setColumnType(hyperlinkColumnType);
		addHyperlinkColumnData.setDescription(columnDescription);
		addHyperlinkColumnData.setColumnWidth(columnWidth);
		return baseIsheetTest.addHyperlinkColumn(addHyperlinkColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createImageColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addImageColumnData = new AddImageColumnData();
		addImageColumnData.setColumnName(imageColumnName);
		addImageColumnData.setColumnType(imageColumnType);
		addImageColumnData.setDescription(columnDescription);
		addImageColumnData.setColumnWidth(columnWidth);
		addImageColumnData.setSelectMethod(selectMethod);
		return baseIsheetTest.addImageColumn(addImageColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createAttachmentColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addAttachmentColumnData = new AddIsheetColumnData();
		addAttachmentColumnData.setColumnName(attachmentColumnName);
		addAttachmentColumnData.setColumnType(attachmentColumnType);
		addAttachmentColumnData.setDescription(columnDescription);
		addAttachmentColumnData.setColumnWidth(columnWidth);
		return baseIsheetTest.addAttachmentColumn(addAttachmentColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createFileLinkColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addFileLinkColumnData = new AddIsheetColumnData();
		addFileLinkColumnData.setColumnName(fileLinkColumnName);
		addFileLinkColumnData.setColumnType(fileLinkColumnType);
		addFileLinkColumnData.setDescription(columnDescription);
		addFileLinkColumnData.setColumnWidth(columnWidth);
		return baseIsheetTest.addFileLinkColumn(addFileLinkColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createFolderLinkColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addFolderLinkColumnData = new AddIsheetColumnData();
		addFolderLinkColumnData.setColumnName(folderLinkColumnName);
		addFolderLinkColumnData.setColumnType(folderLinkColumnType);
		addFolderLinkColumnData.setDescription(columnDescription);
		addFolderLinkColumnData.setColumnWidth(columnWidth);
		return baseIsheetTest.addFolderLinkColumn(addFolderLinkColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createIsheetLinkColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addIsheetLinkColumnData = new AddIsheetLinkColumnData();
		addIsheetLinkColumnData.setColumnName(isheetLinkColumnName);
		addIsheetLinkColumnData.setColumnType(iSheetLinkColumnType);
		addIsheetLinkColumnData.setDescription(columnDescription);
		addIsheetLinkColumnData.setColumnWidth(columnWidth);
		addIsheetLinkColumnData.setiSheetName(Arrays.asList(sheetNames));
		addIsheetLinkColumnData.setDefaultLinkValue(iShhetLinkDefaultValue);
		return baseIsheetTest.addIsheetLinkColumn(addIsheetLinkColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createLookupColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addLookupColumnData = new AddLookupColumnData();
		addLookupColumnData.setColumnName(lookupColumnName);
		addLookupColumnData.setColumnType(lookupColumnType);
		addLookupColumnData.setDescription(columnDescription);
		addLookupColumnData.setSheet(lookupSheet);
		addLookupColumnData.setView(lookupView);
		addLookupColumnData.setSelectColumns(Arrays.asList(lookupColumnList));
		addLookupColumnData.setAllowMultipleValues(allowMultipleValues);
		addLookupColumnData.setEnforceRelationshipBehavior(restrictDelete);
		return baseIsheetTest.addLookupColumn(addLookupColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createJoinColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addJoinColumnData = new AddJoinColumnData();
		addJoinColumnData.setColumnName(joinColumnName);
		addJoinColumnData.setColumnType(joinColumnType);
		addJoinColumnData.setDescription(columnDescription);
		addJoinColumnData.setLinkName(joinLinkName);
		addJoinColumnData.setSheet(joinSheetName + " (" + joinSheetSite + ")");
		addJoinColumnData.setDisplayView(joinDisplayView);
		addJoinColumnData.setJoinConditionCurrentSheet(joinConditionCurrentSheet);
		addJoinColumnData.setJoinConditionTargetSheet(joinConditionTargetSheet);
		return baseIsheetTest.addJoinColumn(addJoinColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createCalculationColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;
		calculationsColumnValues = new LinkedHashMap<>();
		calculationsColumnValues.put(numberColumnName, "10");

		addCalculationColumnData = new AddCalculationColumnData();
		addCalculationColumnData.setColumnName(calculationColumnName);
		addCalculationColumnData.setColumnType(calculationColumnType);
		addCalculationColumnData.setDescription(columnDescription);
		addCalculationColumnData.setDecimalPlaces(calculationDecimalPlaces);
		addCalculationColumnData.setFormula(calculationFormula);
		addCalculationColumnData.setColumnNamesOfFormula(calculationsColumnValues);
		addCalculationColumnData.setExpectedResultOfFormula("20");
		addCalculationColumnData.setShowThousandSeparators(calculationShowThousandSeparators);
		addCalculationColumnData.setShowAsPercentage(calculationShowAsPercentage);
		return baseIsheetTest.addCalculationColumn(addCalculationColumnData);
	}

	/**
	 * @param isheetTitle
	 * @return
	 * @throws InterruptedException
	 */
	private AdminIsheetManageColumnWeb createAutoIncrementColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addAutoIncrementColumnData = new AddAutoIncrementColumnData();
		addAutoIncrementColumnData.setColumnName(autoIncrementColumnName);
		addAutoIncrementColumnData.setColumnType(autoIncrementColumnType);
		addAutoIncrementColumnData.setStartValue(autoIncrementStartValue);
		addAutoIncrementColumnData.setPrefix(autoIncrementPrefix);
		addAutoIncrementColumnData.setSuffix(autoIncrementSuffix);
		addAutoIncrementColumnData.setMinLength(autoIncrementMinLength);
		return baseIsheetTest.addAutoIncrementColumn(addAutoIncrementColumnData);
	}

	AdminIsheetManageColumnWeb createChoiceColumn() throws InterruptedException {
		adminIsheetAddColumnWeb = adminIsheetManageColumnWeb.manageColumnsClickAddColumns();
		baseIsheetTest.adminIsheetAddColumnWeb = adminIsheetAddColumnWeb;

		addChoiceColumnData = new AddChoiceColumnData();
		addChoiceColumnData.setColumnName(choiceColumnName);
		addChoiceColumnData.setColumnType(choiceColumnType);
		Map<String, String> choiceData = new HashMap<>();
		choiceData.put("Yes", "#000000");
		choiceData.put("No", "#000000");
		addChoiceColumnData.setChoiceData(choiceData);
		return baseIsheetTest.addChoiceColumn(addChoiceColumnData);
	}

	/**
	 * Set up record to be added in isheet
	 */
	void setUpIsheetDataAddRecord() {

		isheetData = new IsheetData();

		Map<String, String> singleLineText = new LinkedHashMap<>();
		singleLineText.put(singleLineColumnName, singleLineColumnData);

		Map<String, String> multiLineText = new LinkedHashMap<>();
		multiLineText.put(multiLineColumnName, multiLineColumnData);

		Map<String, String> choice = new LinkedHashMap<>();
		choice.put(choiceColumnName, choiceColumnData);

		Map<String, String> number = new LinkedHashMap<>();
		number.put(numberColumnName, numberColumnData);

		Map<String, String> dateAndTime = new LinkedHashMap<>();
		dateAndTime.put(dateAndTimeColumnName, dateAndTimeColumnData);

		Map<String, String[]> addUserLookUpRecord = new LinkedHashMap<>();
		addUserLookUpRecord.put(userLookupColumnName, userLookupData);

		Map<String, String> hyperLinkData = new LinkedHashMap<>();
		hyperLinkData.put(hyperlinkColumnName, hyperLinkDisplayName + "," + hyperLinkURL);

		Map<String, String[]> attachmenColumntData = new LinkedHashMap<>();
		attachmenColumntData.put(attachmentColumnName, attachmentData);

		Map<String, String[]> fileLinkData = new LinkedHashMap<>();
		fileLinkData.put(fileLinkColumnName, files);

		Map<String, String[]> folderLinkData = new LinkedHashMap<>();
		folderLinkData.put(folderLinkColumnName, folders);

		Map<String, Map<String, List<List<String>>>> isheetsLinkData = new LinkedHashMap<>();
		List<List<String>> iSheetLinkDataRecords = new ArrayList<>();
		iSheetLinkDataRecords.add(Arrays.asList(testRecord1));
		Map<String, List<List<String>>> isheetLinkData = new LinkedHashMap<>();
		isheetLinkData.put(isheetLinkDataSheet, iSheetLinkDataRecords);
		isheetsLinkData.put(isheetLinkColumnName, isheetLinkData);

		Map<String, Map<List<String>, Boolean>> lookUpData = new LinkedHashMap<>();
		Map<List<String>, Boolean> lookupDataRecords = new LinkedHashMap<>();
		lookupDataRecords.put(Arrays.asList(testRecord1), true);
		lookupDataRecords.put(Arrays.asList(testRecord2), true);
		lookupDataRecords.put(Arrays.asList(testRecord3), true);
		lookUpData.put(lookupColumnName, lookupDataRecords);

		Map<String, String> imageColumnData = new LinkedHashMap<>();
		imageColumnData.put(imageColumnName, imageData);

		isheetData.setSingleLineText(singleLineText);
		isheetData.setMultiLineText(multiLineText);
		isheetData.setNumber(number);
		isheetData.setDateAndTime(dateAndTime);
		isheetData.setAddUserLookUpRecord(addUserLookUpRecord);
		isheetData.setHyperLinkData(hyperLinkData);
		isheetData.setAttachmentData(attachmenColumntData);
		isheetData.setFileLinkData(fileLinkData);
		isheetData.setFolderLinkData(folderLinkData);
		isheetData.setIsheetLinkData(isheetsLinkData);
		isheetData.setLookUpData(lookUpData);
		isheetData.setImageColumnData(imageColumnData);
		isheetData.setChoiceColumnData(choice);

	}

	/**
	 * Precondiion
	 *
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	void precondition() throws InterruptedException, IOException, JSONException {
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
	}
}
