package com.highq.pageobjects.pages;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pagedata.IsheetData;
import com.highq.pageobjects.base.IsheetsPage;

public class IsheetsWeb extends BannerPageWeb implements IsheetsPage
{
	By isheetsHeader_IsheetTitle = By.xpath("(//*[contains(@class,'titleResponsive')])[1]//*[contains(@class,'titleAttrTxt')]");
	By isheetsHeader_IsheetListDropDown = By.xpath("(//*[contains(@class,'titleResponsive')])[1]//*[@class='dropdown-menu']");
	final String isheetListDropDown = "(//*[contains(@class,'titleResponsive')])[1]//*[@class='dropdown-menu']";

	By searchInput = By.id("iSheetQuickSearch");
	By grayLoader = By.xpath("(//*[@class='grayloader' and @style='display: block;'])[last()]");
	By clearSearchIcon = By.xpath("//*[@id='sheetQuickSearchID' and not(contains(@class,'hide'))]");
	By isheetGrid = By.id("gridbox");
	By isheetTable_HeaderColumns = By.xpath("//*[@id='gridbox']//table[@class='hdr']//td//*[@class='hdrcell']");
	By grayloaderBig = By.xpath(".//*[@id='sheetDivLoading']/img");
	String isheetTable_ObjectColumns = "//*[@id='gridbox']//table[@class='obj']//td";
	String isheetTable_HeaderNames = "//*[@id='gridbox']//table[@class='hdr']//td";
	By gridBox = By.id("gridbox");
	By locBtnAdd = By.xpath(".//*[@class='headPageTitle']//*[normalize-space(text())='Add']");
	By locBtnActions = By.xpath(".//*[@class='headPageTitle']//*[normalize-space(text())='Actions']");
	By locBtnActionsDropDownBox = By.xpath(".//*[@class='headPageTitle']//*[normalize-space(text())='Actions' and @aria-expanded='true']//following-sibling::ul");
	String locBtnActionsDropDownLists = ".//*[@class='headPageTitle']//*[normalize-space(text())='Actions' and @aria-expanded='true']//following-sibling::ul//li";

	/** Add Isheet Record Modal */
	By locModalAddIsheetRecord = By.id("isheet_module_addItem_modal");
	By locModalAddIsheetRecordOpened = By.xpath(".//*[@id='isheet_module_addItem_modal' and contains(@style,'display: block')]");
	By locModalAddIsheetRecordClosed = By.xpath(".//*[@id='isheet_module_addItem_modal' and contains(@style,'display: none')]");
	By locChckBxAddAnotherRecord = By.id("isheet_module_addItem_modal_addAnother");
	By locBtnCancelAddRecordModal = By.id("isheet_module_addItem_modal_close");
	By locBtnAddRecord = By.id("isheet_module_addItem_modal_add");
	By locBtnCloseAddRecordModal = By.id("isheet_module_addItem_modal_MAIN_CLOSE_BUTTON");
	By addEditRecordGolbalMessage = By.xpath(".//*[@id='isheet_module_add_item_closeGlobal_msg']//following::*[@id='errorElementContainer']");

	/** Select files Modal */
	By locModalSelectFiles = By.id("ishhet_module_addDocument_modal");
	By locModalSelectFilesOpened = By.xpath(".//*[@id='ishhet_module_addDocument_modal' and contains(@style,'display: block;')]");
	By locModalSelectFilesClosed = By.xpath(".//*[@id='ishhet_module_addDocument_modal' and contains(@style,'display: none;')]");
	By locBtnAddFiles = By.id("ishhet_module_addDocument_modal_add");
	By locBtnCancelSelectFilesModal = By.id("ishhet_module_addDocument_modal_close");
	By locBtnCloseSelectFilesModal = By.id("ishhet_module_addDocument_modal_MAIN_CLOSE_BUTTON");
	By locLnkSelectFilesExpandAll = By.xpath(".//*[@id='ishhet_module_addDocument_modal']//*[contains(text(),'Expand all')]");
	By locLnkSelectFilesCollapseAll = By.xpath(".//*[@id='ishhet_module_addDocument_modal']//*[contains(text(),'Collapse all')]");
	By locSpnSelectFilesTreeExpanded = By.xpath("(.//*[@id='ishhet_module_addDocument_modal']//*[@class='fancytree-lastsib'])[last()]");

	/** Select Folders Modal */
	By locModalSelectFolders = By.id("ishhet_module_FOLDER_LINK_MODAL");
	By locModalSelectFoldersOpened = By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL' and contains(@style,'display: block;')]");
	By locModalSelectFoldersClosed = By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL' and contains(@style,'display: none;')]");
	By locBtnSaveSelectFolders = By.id("ishhet_module_FOLDER_LINK_MODAL_add");
	By locBtnCancelSelectFoldersModal = By.id("ishhet_module_FOLDER_LINK_MODAL_close");
	By locBtnCloseSelectFoldersModal = By.id("ishhet_module_FOLDER_LINK_MODAL_close");
	By locLnkSelectFoldersExpandAll = By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL']//*[contains(text(),'Expand all')]");
	By locLnkSelectFoldersCollapseAll = By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL']//*[contains(text(),'Collapse all')]");
	By locSpnSelectFoldersTreeExpanded = By.xpath("(.//*[@id='ishhet_module_FOLDER_LINK_MODAL']//*[@class='fancytree-lastsib'])[last()]");

	/** Isheet link - Add Record Modal */
	By locModalIsheetLinkAddRecord = By.id("isheet_module_injectSheetItem_modal_1");
	By locModalIsheetLinkAddRecordOpened = By.xpath(".//*[@id='isheet_module_injectSheetItem_modal_1' and contains(@style,'display: block;')]");
	By locModalIsheetLinkAddRecordClosed = By.xpath(".//*[@id='isheet_module_injectSheetItem_modal_1' and contains(@style,'display: none;')]");
	By locDrpdwnSelectIsheet = By.id("injectSheetID");
	By locBtnInsertIsheetLinkRecord = By.id("isheet_module_injectSheetItem_modal_1_add");
	By locBtnCancelIsheetLinkRecord = By.id("isheet_module_injectSheetItem_modal_1_close");
	By locBtnCloseAddIsheetLinkRecord = By.id("isheet_module_injectSheetItem_modal_1_MAIN_CLOSE_BUTTON");
	By autoSaveMessage = By.id("isheet_module_addEditItem_modal_autoSaveMsg");

	/** Lookup */
	By locModalLookup = By.id("isheet_module_lookupSheetItem_modal");
	By locModalLookupOpened = By.xpath(".//*[@id='isheet_module_lookupSheetItem_modal' and contains(@style,'display: block;')]");
	By locModalLookupClosed = By.xpath(".//*[@id='isheet_module_lookupSheetItem_modal' and contains(@style,'display: none;')]");
	By locBtnInsertLookup = By.id("isheet_module_lookupSheetItem_modal_add");
	By locBtnCancelLookupModal = By.id("isheet_module_lookupSheetItem_modal_close");
	By locBtnCloseLookupModal = By.id("isheet_module_lookupSheetItem_modal_MAIN_CLOSE_BUTTON");

	By userLookupHover = By.xpath("(//*[@class='twitter-typeahead']//*)[last()]");

	By imageLoaderDone = By.xpath("(//*[@id='progressbar_div']//*[text()='Done'])[last()]");

	/** Isheet View Record Modal */

	By locModalViewIsheetRecord = By.id("isheet_module_viewItem_modal");
	By locModalViewIsheetRecordOpened = By.xpath(".//*[@id='isheet_module_viewItem_modal' and contains(@style,'display: block;')]");
	By locModalViewIsheetRecordClosed = By.xpath(".//*[@id='isheet_module_viewItem_modal' and contains(@style,'display: none;')]");
	By viewRecordModalCloseButton = By.id("isheet_module_viewItem_modal_close");
	By viewRecordModalMoreAction = By.xpath("(.//*[@id='isheet_module_viewItem_modal' and contains(@style,'display: block;')]//*[@data-original-title='More actions'])[1]");
	By viewRecordModalMoreActionDropBox = By.xpath("(.//*[@id='isheet_module_viewItem_modal' and contains(@style,'display: block;')]//*[@data-original-title='More actions'])[1]//following-sibling::ul");
	String viewRecordModalMoreActionList = "(.//*[@id='isheet_module_viewItem_modal' and contains(@style,'display: block;')]//*[@data-original-title='More actions'])[1]//following-sibling::ul//li";

	/** Edit Isheet Record Modal */
	By locModalEditIsheetRecord = By.id("isheet_module_editItem_modal");
	By locModalEditIsheetRecordOpened = By.xpath(".//*[@id='isheet_module_editItem_modal' and contains(@style,'display: block')]");
	By locModalEditIsheetRecordClosed = By.xpath(".//*[@id='isheet_module_editItem_modal' and contains(@style,'display: none')]");
	By locBtnCancelEditRecordModal = By.id("isheet_module_editItem_modal_close");
	By locBtnSaveRecord = By.id("isheet_module_editItem_modal_add");
	By locBtnCloseEditRecordModal = By.id("isheet_module_editItem_modal_MAIN_CLOSE_BUTTON");
	By editNextRecordChkBox = By.id("isheet_module_editItem_modal_editNext");

	By addAttachmentButton = By.xpath("(.//*[@class='modal fade in'])[last()]//*[@id='ishhet_module_addDocument_modal_add']");

	/** Delete Record Modal */

	By customModalCloseBtn = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='collaborateMessageCloseButton']");
	By customModalCancelBtn = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='collaborateMessageCancelButton']");
	By customModalOKBtn = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='collaborateMessageOkButton']");
	By customModalMessage = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='collaborateCustomModalMessage']");

	By globalDeletionMessage = By.xpath(".//*[@id='globalAlertMessageContainer' and @class='globalMsg']//*[text()='Deleted 1 of 1 selected records']");
	By globalDeletionMessageRemove = By.xpath(".//*[@id='globalAlertMessageContainer' and @class='globalMsg']//*[text()='Deleted 1 of 1 selected records']//preceding::*[@data-original-title='Remove']");

	/** Favourite */
	By favouriteStarSelected = By.xpath("(//*[contains(@class,'icon-star-selected') and @data-original-title='" + IsheetLabels.ISHEET_DATAORIGINALTITLEATTRIBUTE_REMOVEFROMFAVOURITES + "'])[last()]");
	By favouriteButton = By.xpath("(//*[contains(@class,'icon-star') and @data-original-title='" + IsheetLabels.ISHEET_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "'])[last()]");
	By lastOpenedModel = By.xpath("(.//*[@class='modal fade in'])[last()]");
	By isheetEditModelSaveArrow = By.xpath("(.//*[@class='modal fade in'])[last()]//*[@class='modal-footer']//*[@aria-haspopup='true']");
	By saveAsDraftLink = By.xpath("//*[@class='dropdown-menu copyDropdown' and contains(@style,'display: block')]//child::*[normalize-space(text())='" + IsheetLabels.ISHEET_EDITRECORD_SAVEASADRAFT + "']");

	By resumeEditingMessage_resumeLink = By.xpath("(.//*[@class='modal fade in'])[last()]//*[@id='resumeDiscardID']//child::*[normalize-space(text())='resume']");
	By resumeEditingMessage_discardLink = By.xpath("(.//*[@class='modal fade in'])[last()]//*[@id='resumeDiscardID']//child::*[normalize-space(text())='discard']");
	By resumeEditingMessage = By.xpath("(.//*[@class='modal fade in'])[last()]//*[@id='resumeDiscardID']");

	/** Isheet View */
	By isheetViewDropDown = By.id("sheetViewQuickDropLinkTxt");
	By isheetViewDropDownOpened = By.xpath("//*[@id='sheetViewQuickDropLinkTxt' and @aria-expanded='true']");
	By isheetViewDropDownBox = By.xpath("//*[@id='sheetViewQuickDropLinkTxt' and @aria-expanded='true']//following-sibling::ul");
	String isheetViewDropDownList = "//*[@id='sheetViewQuickDropLinkTxt' and @aria-expanded='true']//following-sibling::ul//li";
	By currentViewName = By.xpath("//*[@id='sheetViewQuickDropLinkTxt']//*[contains(@class,'titleAttrTxt')]");

	By isheetNoData = By.xpath(".//*[@id='gridbox']//*[contains(@class,'greyFont') and text()='No data']");

	By selectAllRecordsChkbox = By.id("selectAllCheckBoxID");
	By noData = By.id("sheetGridNoDataRowDivID");

	/* Isheet table */
	String allIsheetRows = "//*[@class='obj']//tr";

	/* Search */
	By saveSearchButton = By.xpath("//*[contains(@onclick,'saveSearch()')]");
	By saveSearch_NameInput = By.xpath("(//*[@id='saveSearchTitle'])[last()]");
	By saveSearch_SaveButton = By.id("SHEET_SAVE_SEARCH_MODAL_save");
	By saveSearch_CancelButton = By.id("SHEET_SAVE_SEARCH_MODAL_close");
	By searchFilter_ViewAllSavedSearchesLink = By.xpath(".//*[@class='fileSearch']//*[contains(@onclick,'allSavedSearch();')]");
	By saveSearchModal = By.id("SHEET_SAVE_SEARCH_MODAL");
	By search_FilterButton = By.xpath("//*[@class='fileSearch']//*[@data-original-title='Filter']");
	By search_FilterBox = By.id("sheetSavedSearchItemListID");
	String search_ViewAllSavedSearches_SavedList = ".//*[@id='isheet_module_allSavedSearch_modal_BODY']//*[@class='edit-value']";
	String search_FilterItems = "//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@id='sheetSavedSearchItemListID']//a";
	By search_ViewAllSavedSearches_MoreActionDropDown = By.xpath(".//*[@id='isheet_module_allSavedSearch_modal_BODY']//*[@class='dropdown open']");
	By search_ViewAllSavedSearchesModal = By.id("isheet_module_allSavedSearch_modal");
	By search_ViewAllSavedSearches_CloseButton = By.id("isheet_module_allSavedSearch_modal_close");

	/* Advanced Search */
	By advancedSearchModal = By.id("isheet_module_advanceSearch_modal");
	By advancedSearch_AllOfTheseWordsInput = By.id("allSearchID");
	By advancedSearch_AnyOfTheseWordsInput = By.xpath("//*[@id='isheet_module_advanceSearch_modal']//*[@id='anySearchID']");
	By advancedSearch_ExcludeTheseWordsInput = By.id("excludeSearchID");
	By advancedSearch_SearchButton = By.id("isheet_module_advanceSearch_modal_search");
	By advancedSearch_CancelButton = By.id("isheet_module_advanceSearch_modal_close");

	/** Draft Items */

	By draftItemsFilter = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter']//*");
	By draftItemsFilterOpened = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter' and @aria-expanded='true']");
	By draftItemsFilterClosed = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter' and @aria-expanded='false']");
	By selectedDraftItemOption = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter' and @aria-expanded='true']//following-sibling::*[@id='sheetSavedSearchItemListID']//*[contains(@class,'active')]//*");

	By totalIsheetRows = By.xpath(".//*[@id='gridbox']//*[contains(@class,'dhx_web')]");
	By searchBoxHintMessage = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@class='searchComp']//*[contains(@class,'srcSheet') and @style='display: block;']//*[text()='Hit the enter key to search']");

	By isheetMoreActions = By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions']");
	By isheetMoreActionsOpened = By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions' and @aria-expanded='true']");
	By isheetMoreActionsClosed = By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions' and @aria-expanded='false']");

	By closeButtonOnPrintModal = By.xpath(".//*[@onclick='closeIsheetItemPrintWindow();']");
	By printButtonOnPrintModal = By.xpath(".//*[@onclick='printIsheetItemPrintWindow();']");

	By isheetNameOnPrintModal = By.xpath("(//*[contains(@class,'TruncateTxt')])[1]");
	By isheetTableOnPrintModal = By.xpath(".//*[@class='printiSheetTableCont']");
	By isheetHeadersOnPrintModal = By.xpath(".//*[@class='printPreviewSheetHead']//th");
	By printPreviewWindow = By.id("printBodySection");

	By downloading = By.id("globalProcessMessageContainerMsgSpace");

	By exportIsheetModal = By.id("ishhet_module_export_MODAL");
	By exportIsheetModalOpened = By.xpath("//*[@id='ishhet_module_export_MODAL' and not(contains(@style,'display: none'))]");
	By exportIsheetModalClosed = By.xpath("//*[@id='ishhet_module_export_MODAL' and contains(@style,'display: none')]");
	By exportIsheetModalExportButton = By.id("ishhet_module_export_MODAL_add");
	By exportIsheetModalCancelButton = By.id("ishhet_module_export_MODAL_close");

	By importIsheetModal = By.id("ishhet_module_import_MODAL");
	By importIsheetModalOpened = By.xpath(".//*[@id='ishhet_module_import_MODAL' and contains(@style,'display: block')]");
	By importIsheetModalClosed = By.xpath(".//*[@id='ishhet_module_import_MODAL' and contains(@style,'display: none')]");
	By importIsheetModalBrowseButton = By.id("fileUploadButton");
	By importIsheetModalCancelButton = By.id("ishhet_module_import_MODAL_close");
	By importIsheetModalNextButton = By.id("ishhet_module_import_MODAL_next");

	By isheetAuditHistoryModal = By.id("isheetItem_auditHistory_modal");
	By isheetAuditHistoryModalClosed = By.xpath(".//*[@id='isheetItem_auditHistory_modal' and contains(@style,'display: none')]");
	By isheetAuditHistoryModalOpened = By.xpath(".//*[@id='isheetItem_auditHistory_modal' and contains(@style,'display: block')]");
	By isheetAuditHistoryVersionsTab = By.xpath(".//*[@id='isheetItem_auditHistory_modal' and contains(@style,'display: block')]//*[text()='" + IsheetLabels.ISHEET_RECORD_AUDITHISTORY_VERSIONS + "']");
	By isheetAuditHistoryModalCloseButton = By.id("isheetItem_auditHistory_modal_CloseButton");
	By isheetAuditHistoryModalCompareButton = By.id("isheetItem_auditHistory_modal_CompareButton");
	By isheetAuditHistoryVersionsTableHeaders = By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//th");
	By isheetAuditHistoryVersionsTableRows = By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr");
	By isheetAuditHistoryAuditTableHeaders = By.xpath(".//*[@id='isheetAuditTable']//th");

	By isheetComapreModal = By.id("ISHEET_MODULE_COMPARE_VERSION");
	By isheetCompareModalOpened = By.xpath(".//*[@id='ISHEET_MODULE_COMPARE_VERSION' and contains(@style,'display: block')]");
	By isheetCompareClosed = By.xpath(".//*[@id='ISHEET_MODULE_COMPARE_VERSION' and contains(@style,'display: none')]");

	By createdRecordLink = By.xpath(".//*[@id='isheete_module_Audit_Tbody']//tr[last()]//*[text()='" + IsheetLabels.ISHEET_RECORD_AUDITTABLE_ACTIONS_CREATEDRECORDS + "']");
	By auditTableRaws = By.xpath(".//*[@id='isheete_module_Audit_Tbody']//tr");
	By auditRecordTable = By.id("isheete_module_auditOnScoll_AUDIT");
	By firstModifiedRecord = By.xpath("(.//*[@id='isheete_module_Audit_Tbody']//tr[last()]//preceding::*[text()='" + IsheetLabels.ISHEET_RECORD_AUDITTABLE_ACTIONS_MODIFIEDRECORDS + "'])[last()]");
	By currentVersionMoreActionOpened = By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[2]//*[@data-original-title='More actions' and @aria-expanded='true']");
	By currentVersionMoreActionButton = By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[2]//*[@data-original-title='More actions']");

	String compareColorCode_green = "#d1eed1";
	String compareColorCode_red = "#fddfde";
	String compareColorCode_blue = "#ddf4fb";

	By documentTemplatesModal = By.id("ishhet_module_generateReport_MODAL");
	By docuemntTemplatesModalOpened = By.xpath(".//*[@id='ishhet_module_generateReport_MODAL' and contains(@style,'display: block')]");
	By documentTemplatesModalClosed = By.xpath(".//*[@id='ishhet_module_generateReport_MODAL' and contains(@style,'display: none')]");
	By documentTemplateCloseButton = By.id("ishhet_module_generateReport_MODAL_close");
	By documentTemplateGenerateButton = By.id("ishhet_module_generateReport_MODAL_generate");
	By documentGenerationGlobalMessage = By.id("globalProcessMessageContainerMsgSpace");

	By documentGeneratedModal = By.id("ishhet_module_generatedDDReport_MODAL");
	By documentGeneratedModalOpened = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL' and contains(@style,'display: block')]");
	By documentGeneratedModalClosed = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL' and contains(@style,'display: none')]");
	By documentGeneratedModalTitle = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[@class='modal-title']");
	By documentGeneratedModalText = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[@id='massageSaveDownload']");
	By documentGeneratedModalDownloadButton = By.xpath(".//*[@id='massageSaveDownload']//following::*[@id='downloadBtn']//*[text()='Download']");
	By documentGeneratedModalSaveFolderButton = By.xpath(".//*[@id='massageSaveDownload']//following::*[@id='saveBtn']//*[text()='Save to folder']");
	By documentGeneratedModalCloseButton = By.id("ishhet_module_generatedDDReport_MODAL_close");
	By documentGeneratedModalMessage = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[contains(@class,'alert-success')]");

	By documentGeneratedModalMessageDocumentLink = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[text()='document']");
	By documentGeneratedModalMessageSpecifiedFolderLink = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[text()='specified folder']");
	By documentGeneratedModalMessageIsheetRecordLink = By.xpath(".//*[@id='ishhet_module_generatedDDReport_MODAL']//*[text()='iSheet record']");

	By filePreviewWindowOpened = By.xpath(".//*[@id='FILE_MODULE_ADEPTOL' and contains(@style,'display: block;')]");
	By filePreviewWindowClosed = By.xpath(".//*[@id='FILE_MODULE_ADEPTOL' and contains(@style,'display: none;')]");
	By fileNameInfilePreviewWindow = By.xpath(".//*[@id='document_preview_modal_header_main']//*[@class='iconMeta']//span");
	By downloadButtonOnFileViewer = By.xpath(".//*[@id='document_preview_modal_header_main']//*[text()='Download' and @type='button']");
	By fileViewerCloseButton = By.xpath(".//*[@id='document_preview_modal_header_main']//*[@aria-label='Close']");

	By saveToSiteFolderModal = By.id("file_module_DDReport_modal");
	By saveToSiteFolderModalOpened = By.xpath(".//*[@id='file_module_DDReport_modal' and contains(@style,'display: block')]");
	By saveToSiteFolderModalClosed = By.xpath(".//*[@id='file_module_DDReport_modal' and contains(@style,'display: none')]");
	By saveToSiteFolderModalTitle = By.xpath(".//*[@id='file_module_DDReport_modal']//*[@class='modal-title']");
	By saveToSiteFolderModalSiteLabel = By.xpath(".//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='Site :']");
	By saveToSiteFolderModalSiteDropDown = By.xpath(".//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='Site :']//parent::*//*[@id='copyFilesFoldersSiteList']");
	By saveToSiteFolderModalChooseFolderLabel = By.xpath(".//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='Choose a folder']");
	By saveToSiteFolderModalExpandAllLink = By.xpath(".//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='Choose a folder']//parent::*//following-sibling::*//*[text()='Expand all']");
	By saveToSiteFolderModalCollapseAllLink = By.xpath(".//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='Choose a folder']//parent::*//following-sibling::*//*[text()='Collapse all']");
	By saveToSiteFolderModalCancelButton = By.id("file_module_DDReport_modal_close");
	By saveToSiteFolderModalCloseButton = By.id("file_module_DDReport_modal_MAIN_CLOSE_BUTTON");
	By saveToSiteFolderModalSaveButton = By.id("file_module_DDReport_modal_move");

	By expandedTree = By.xpath(".//*[contains(@class,'fancytree-expanded') and not(contains(@class,'fancytree-lastsib'))]");
By recordCountOfIsheet = By.xpath(".//*[@id='gridbox']//tr[contains(@class,'_dhx_web')]");

	public IsheetsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void searchIsheetItem(String itemToSearch)
	{
		// String fileExt="";
		if (itemToSearch.contains(" "))
		{
			String firstPart = itemToSearch.substring(0, itemToSearch.indexOf(' ')).trim();

			if (firstPart.matches("[0-9]+(\\.[0-9]+)*"))
			{
				itemToSearch = itemToSearch.substring(itemToSearch.indexOf(' ') + 1).trim();
			}
		}
		WebElement searchFileInput = findClickableElement(searchInput);
		searchFileInput.clear();
		if (itemToSearch.contains("."))
		{
			itemToSearch = itemToSearch.substring(0, itemToSearch.lastIndexOf('.')).trim();
		}
		String fileNameForSearch = itemToSearch.trim();
		searchFileInput.sendKeys(fileNameForSearch);
		searchFileInput.sendKeys(Keys.ENTER);
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(clearSearchIcon);
	}

	@Override
	public void clearIsheetsSearchBox()
	{
		findVisibleElement(searchInput);
		if (isDisplayed(clearSearchIcon))
		{
			findVisibleElement(clearSearchIcon).click();
		}
	}

	@Override
	public void selectIsheetFromHeader(String isheetName)
	{
		WebElement isheetTitleElement = findVisibleElement(isheetsHeader_IsheetTitle);
		if (!isheetTitleElement.getText().trim().equals(isheetName.trim()))
		{
			isheetTitleElement.click();
			findVisibleElement(isheetsHeader_IsheetListDropDown);
			By titleXpath = By.xpath(isheetListDropDown + "//*[normalize-space(text())='" + isheetName.trim() + "']");
			if (isDisplayed(titleXpath))
			{
				findVisibleElement(titleXpath).click();
				findVisibleElement(isheetGrid, Speed.slow);
			}
			else
			{
				System.err.println("Isheet with name " + isheetName + " not found ");
			}
		}
	}

	@Override
	public String getColumnValue(String columnName)
	{
		findPresentElement(gridBox, Speed.slow);
		int size = driver.findElements(isheetTable_HeaderColumns).size();
		for (int i = 1; i <= size; i++)
		{
			WebElement column = findPresentElement(By.xpath(isheetTable_HeaderNames + "[" + i + "]//*[@class='hdrcell']"));
			String columnHeader = column.getText().trim();
			if (columnHeader.equals(columnName.trim()))
			{
				String columnText = findVisibleElement(By.xpath("(" + isheetTable_ObjectColumns + "[" + i + "])[last()]")).getText().trim();
				return columnText;
			}
		}
		return "";
	}

	@Override
	public boolean verifyIsheetFieldValue(String fieldName, String valueToVerify)
	{
		findVisibleElement(isheetsHeader_IsheetTitle);
		while (isDisplayed(grayloaderBig))
		{
			;
		}
		String fetchedValue = getColumnValue(fieldName);
		return fetchedValue.equals(valueToVerify.trim());
	}

	@Override
	public void clickAdd()
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(locBtnAdd).click();
		findVisibleElement(locModalAddIsheetRecordOpened, Speed.slow);
	}

	@Override
	public void selectIsheetActions(String isheetAction)
	{
		scrollToTop();
		while (!isDisplayed(locBtnActions))
		{
			;
		}
		selectOptionFromDropDown(locBtnActions, locBtnActionsDropDownBox, locBtnActionsDropDownLists, isheetAction);
	}

	/*********************************************************************** ADD RECORD METHODS *******************************************************************/

	@Override
	public void addSingleLineColumnData(String columnName, String data)
	{
		WebElement singleLineColumnInput = findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//input[1]"));
		singleLineColumnInput.clear();
		singleLineColumnInput.sendKeys(data);
	}

	@Override
	public void addMultipleLineColumnData(String columnName, String data)
	{
		By ckContentEditor = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[contains(@class,'ckContentArea')]");
		By textArea = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//textarea[1]");
		if (isDisplayed(ckContentEditor))
		{
			WebElement ckContentAreaEle = findVisibleElement(ckContentEditor);
			ckContentAreaEle.clear();
			ckContentAreaEle.sendKeys(data);
		}
		else
		{
			WebElement textAreaEle = findVisibleElement(textArea);
			textAreaEle.clear();
			textAreaEle.sendKeys(data);
		}
	}

	@Override
	public void addNumberColumnData(String columnName, String value)
	{
		addSingleLineColumnData(columnName, value);
	}

	@Override
	public void addDateAndTimeColumnData(String columnName, String date)
	{
		By dateAndTimeInput = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//input[1]");
		findVisibleElement(dateAndTimeInput).click();
		findVisibleElement(dateAndTimeInput).sendKeys(date);
	}

	@Override
	public void addUserLookupColumnData(String columnName, String... users)
	{
		WebElement userLookup = findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[@class='relativeDiv']//*[@class='token-input tt-input']"));
		for (String user : users)
		{
			userLookup.sendKeys(user);
			selectOptionFromUserLookup(user);
		}
	}

	@Override
	public void addHyperlinkColumnData(String columnName, String displayName, String url)
	{
		WebElement linkDisplayName = findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[normalize-space(text())='Enter display name']//following-sibling::*"));
		linkDisplayName.clear();
		linkDisplayName.sendKeys(displayName);
		WebElement linkURL = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[normalize-space(text())='Enter URL']//parent::*//input)[1]"));
		linkURL.clear();
		linkURL.sendKeys(url);
		// findVisibleElement(By.xpath("//*[text()='Enter URL']")).click();
	}

	@Override
	public void addImageColumnData(String columnName, String imageFile)
	{
		By byBrowse = By.xpath("((.//*[@class='modal fade in'])[last()]//label[contains(normalize-space(.),'" + columnName.trim() + "')]//parent::*//following-sibling::*[@type='file'])[1]");
		By byInputBox = By.xpath("((.//*[@class='modal fade in'])[last()]//label[contains(normalize-space(.),'" + columnName.trim() + "')]//parent::*//following-sibling::*[@type='text'])[1]");
		if (isDisplayed(byInputBox, Speed.slow))
		{
			findVisibleElement(byInputBox, Speed.slow).clear();
			findVisibleElement(byInputBox, Speed.slow).sendKeys(imageFile);
			clickOnPreviewImage(columnName);
		}
		else
		{
			findPresentElement(byBrowse, Speed.slow).sendKeys(TestBaseSetup.currentDir + "\\testData\\" + imageFile);
			findVisibleElement(By.xpath(".//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[@id='progressbar_div']//*[text()='Done']"), Speed.slow);
		}
	}

	@Override
	public void clickOnPreviewImage(String columnName)
	{
		WebElement previewImageEle = findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[@data-original-title='Preview image'][1]"));
		previewImageEle.click();
	}

	@Override
	public void addAttachmentColumnData(String columnName, String... attachments)
	{
		WebElement attachmentEle = findPresentElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[contains(@id,'sheetUploadAttachment')][1]"));
		for (String attachment : attachments)
		{
			attachmentEle.sendKeys(TestBaseSetup.currentDir + "\\testData\\" + attachment);
			findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='" + attachment.trim() + "']//preceding::*[text()='Done'])[last()]"), Speed.slow);
		}
	}

	@Override
	public void addFileLinkCoumnData(String columnName, String[] files)
	{
		findVisibleElement(locModalAddIsheetRecordOpened, Speed.slow);
		WebElement selectFiles = findVisibleElement(By.xpath("(.//*[@id='isheet_module_addItem_modal_BODY']//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='Select files'])[1]"));
		selectFiles.click();
		findVisibleElement(locModalSelectFilesOpened, Speed.slow);
		// findVisibleElement(locLnkSelectFilesExpandAll).click();
		findVisibleElement(locSpnSelectFilesTreeExpanded, Speed.slow);
		for (String file : files)
		{
			// need to change implementation
			By locfile = By.xpath(".//*[@id='ishhet_module_addDocument_modal']//*[normalize-space(text())='" + file.trim() + "']//preceding::*[@class='fancytree-checkbox'][1]");
			setSelection(locfile, true);
		}
		clickAddOnSelectFiles();
	}

	@Override
	public void clickAddOnSelectFiles()
	{
		findVisibleElement(locModalSelectFilesOpened, Speed.slow);
		findVisibleElement(locBtnAddFiles).click();
		findPresentElement(locModalSelectFilesClosed, Speed.slow);
	}

	@Override
	public void addFolderLinkColumnData(String columnName, String[] folderNames)
	{
		for (String folderName : folderNames)
		{
			WebElement selectFolder = findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='Select folders'][1]"));
			selectFolder.click();
			findVisibleElement(locModalSelectFoldersOpened, Speed.slow);
			// findVisibleElement(locLnkSelectFoldersExpandAll).click();
			findVisibleElement(locSpnSelectFoldersTreeExpanded, Speed.slow);
			boolean currentState = findVisibleElement(By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL']//*[normalize-space(text())='" + folderName.trim() + "']//preceding::*[@class='fancytree-checkbox'][1]")).isSelected();
			if (currentState != true)
			{
				WebElement folderEle = findVisibleElement(By.xpath(".//*[@id='ishhet_module_FOLDER_LINK_MODAL']//*[normalize-space(text())='" + folderName.trim() + "']//preceding::*[@class='fancytree-checkbox'][1]"));
				folderEle.click();
			}
			clickSaveOnSelectFolders();
		}

	}

	@Override
	public void clickSaveOnSelectFolders()
	{
		findVisibleElement(locModalSelectFoldersOpened, Speed.slow);
		findVisibleElement(locBtnSaveSelectFolders).click();
		findPresentElement(locModalSelectFoldersClosed, Speed.slow);
	}

	@Override
	public void addIsheetLinkColumnData(String columnName, String isheetName, Map<List<String>, Boolean> columnData)
	{
		findVisibleElement(locModalAddIsheetRecordOpened, Speed.slow);
		findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='Browse…']")).click();
		findVisibleElement(locModalIsheetLinkAddRecordOpened, Speed.slow);
		selectIsheetForIsheetLink(isheetName);
		// put wait to load the grid

		for (Map.Entry<List<String>, Boolean> data : columnData.entrySet())
		{
			String locRecord = ".//*[normalize-space(.) = '";
			locRecord = locRecord.concat(data.getKey().get(0)) + "']";
			for (int column = 1; column < data.getKey().size(); column++)
			{
				locRecord = locRecord.concat("//following-sibling::*[normalize-space(.)='" + data.getKey().get(column) + "']");
			}
			locRecord = locRecord.concat("//preceding-sibling::*//img[1]");
			setSelection(By.xpath(locRecord), data.getValue());
		}
		clickInsertOnAddIsheetLinkRecord();
	}

	@Override
	public void addIsheetLinkColumnData(String columnName, String isheetName, List<List<String>> columnData)
	{
		findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='Browse…']")).click();
		findVisibleElement(locModalIsheetLinkAddRecordOpened, Speed.slow);
		selectIsheetForIsheetLink(isheetName);
		// put wait to load the grid

		for (List<String> data : columnData)
		{
			String locRecord = ".//*[normalize-space(.) = '";
			locRecord = locRecord.concat(data.get(0)) + "']";
			for (int column = 1; column < data.size(); column++)
			{
				locRecord = locRecord.concat("//following-sibling::*[normalize-space(.)='" + data.get(column) + "']");
			}
			locRecord = locRecord.concat("//preceding-sibling::*//img[1]");
			setSelection(By.xpath(locRecord), true);
		}
		clickInsertOnAddIsheetLinkRecord();
	}

	@Override
	public void selectIsheetForIsheetLink(String isheetName)
	{
		findVisibleElement(locModalIsheetLinkAddRecordOpened, Speed.slow);
		Select drpdwnSelectIsheet = new Select(findVisibleElement(locDrpdwnSelectIsheet));
		drpdwnSelectIsheet.selectByVisibleText(isheetName);
		// .//*[normalize-space(.) = 'Ravi Middha']//following-sibling::*[normalize-space(.)='12 Feb 2018 10:16']//following-sibling::*[normalize-space(.)='Ravi Middha']//following-sibling::*[normalize-space(.)='12 Feb 2018 10:16']//following-sibling::*[normalize-space(.)='Record1']//preceding-sibling::*//img
	}

	@Override
	public void clickInsertOnAddIsheetLinkRecord()
	{
		findVisibleElement(locModalIsheetLinkAddRecordOpened, Speed.slow);
		if (findVisibleElement(locBtnInsertIsheetLinkRecord).isEnabled())
		{
			findVisibleElement(locBtnInsertIsheetLinkRecord).click();
		}
		findPresentElement(locModalIsheetLinkAddRecordClosed, Speed.slow);
	}

	@Override
	public void addLookupColumnData(String columnName, Map<List<String>, Boolean> columnData)
	{
		// findVisibleElement(By.xpath(".//*[@id='isheet_module_addItem_modal_BODY']//*[normalize-space(text())='" + columnName.trim() + "']")).click();
		findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[text()='Browse…']")).click();
		findVisibleElement(locModalLookupOpened, Speed.slow);
		// wait for grid to load
		for (Map.Entry<List<String>, Boolean> data : columnData.entrySet())
		{
			String locRecord = "(.//*[normalize-space(.) = '";
			locRecord = locRecord.concat(data.getKey().get(0)) + "']";
			for (int column = 1; column < data.getKey().size(); column++)
			{
				locRecord = locRecord.concat("//following-sibling::*[normalize-space(.)='" + data.getKey().get(column) + "']");
			}
			locRecord = locRecord.concat("//preceding-sibling::*//img[1])[1]");
			boolean recordSelectionStatus = isDisplayed(By.xpath(locRecord.replace("img", "img[contains(@src,'chk1')]")));
			if (recordSelectionStatus != data.getValue())
			{
				findVisibleElement(By.xpath(locRecord)).click();
			}
		}
		clickInsertOnAddLookup();
	}

	@Override
	public void clickInsertOnAddLookup()
	{
		findVisibleElement(locModalLookupOpened, Speed.slow);
		if (findVisibleElement(locBtnInsertLookup).isEnabled())
		{
			findVisibleElement(locBtnInsertLookup).click();
		}
		findPresentElement(locModalLookupClosed, Speed.slow);
	}

	@Override
	public void selectOptionFromUserLookup(String option)
	{
		By optionSuggetion = By.xpath("//*[@class='twitter-typeahead']//following::*[@class='tt-dropdown-menu']//*[normalize-space(text())='" + option.trim() + "'][1]");
		if (isDisplayed(optionSuggetion, Speed.slow))
		{
			WebElement lookupHover = findVisibleElement(optionSuggetion, Speed.slow);
			if (lookupHover.getText().trim().equals(option.trim().toLowerCase()))
			{
				lookupHover.click();
			}
		}
		else
		{
			System.err.println(option + " not found.");
		}
	}

	@Override
	public void clickAddOnAddRecordModal()
	{
		WebElement btnAddRecord = findClickableElement(locBtnAddRecord);
		btnAddRecord.click();
		// findPresentElement(locModalAddIsheetRecordClosed, Speed.slow);
	}

	@Override
	public void addRecord(String[] columnName, IsheetData isheetData)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		for (int colCount = 0; colCount < columnName.length; colCount++)
		{

			switch (columnName[colCount].toLowerCase().trim())
			{
				case "single line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getSingleLineText().entrySet())
					{
						addSingleLineColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;
				case "multiple line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getMultiLineText().entrySet())
					{
						addMultipleLineColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "number":
					for (Map.Entry<String, String> columnRecord : isheetData.getNumber().entrySet())
					{
						addNumberColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "date and time":
					for (Map.Entry<String, String> columnRecord : isheetData.getDateAndTime().entrySet())
					{
						addDateAndTimeColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "user lookup":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAddUserLookUpRecord().entrySet())
					{
						addUserLookupColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "hyperlink":
					for (Map.Entry<String, String> columnRecord : isheetData.getHyperLinkData().entrySet())
					{
						String hyperlinkColumnName = columnRecord.getKey().trim();
						String hyperLinkData[] = columnRecord.getValue().trim().split(",");
						String linkName = hyperLinkData[0].trim();
						String url = hyperLinkData[1].trim();
						addHyperlinkColumnData(hyperlinkColumnName, linkName, url);
					}
					break;

				case "image":
					for (Map.Entry<String, String> columnRecord : isheetData.getImageColumnData().entrySet())
					{
						addImageColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "attachment":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAttachmentData().entrySet())
					{
						for (int i = 0; i < columnRecord.getValue().length; i++)
						{
							addAttachmentColumnData(columnRecord.getKey(), columnRecord.getValue()[i]);
						}
					}
					break;

				case "file link":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getFileLinkData().entrySet())
					{
						addFileLinkCoumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "folder link":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getFolderLinkData().entrySet())
					{
						addFolderLinkColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "isheet link":
					for (Map.Entry<String, Map<String, List<List<String>>>> columnRecord : isheetData.getIsheetLinkData().entrySet())
					{
						String colName = columnRecord.getKey().trim();
						Map<String, List<List<String>>> records = columnRecord.getValue();
						for (Map.Entry<String, List<List<String>>> record : records.entrySet())
						{
							addIsheetLinkColumnData(colName, record.getKey(), record.getValue());
						}
					}
					break;
				case "lookup":
					for (Map.Entry<String, Map<List<String>, Boolean>> columnRecord : isheetData.getLookUpData().entrySet())
					{
						String colName = columnRecord.getKey().trim();
						addLookupColumnData(colName, columnRecord.getValue());
					}
					break;

				case "choice":
					for (Map.Entry<String, String> columnRecord : isheetData.getChoiceColumnData().entrySet())
					{
						selectChoiceColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				default:
					break;
			}
		}
	}

	/*********************************************************************** VERIFY RECORD METHODS *******************************************************************/

	@Override
	public boolean verifySingleLineTextRecord(String columnName, String columnData)
	{
		// findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
		By colomunRecord = By.xpath("(.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@name,'itemValue')][1]");
		WebElement fieldValue = findPresentElement(colomunRecord);
		return fieldValue.getAttribute("value").trim().equals(columnData.trim());
	}

	@Override
	public boolean verifyMultiLineTextRecord(String columnName, String columnData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By colomunRecord = By.xpath(".//*[@id='isheet_module_viewItem_modal']//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//*[contains(@class,'ckContentArea')]//p");
		WebElement fieldValue = findPresentElement(colomunRecord, Speed.slow);
		return fieldValue.getText().trim().equals(columnData.trim());
	}

@Override
	public boolean verifyChoiceRecord(String columnName, String columnData)
	{
		// findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(text()),'" + columnName.trim() + "')]//following-sibling::*//td//*[@class='wordWrap']");
		WebElement fieldValue = findVisibleElement(columnRecord, Speed.slow);
		return fieldValue.getText().trim().equals(columnData.trim());
	}

	@Override
	public boolean verifyNumberRecord(String columnName, String columnData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath(".//*[@id='isheet_module_viewItem_modal']//*[normalize-space(text())='" + columnName.trim() + "']//following::*[contains(@name,'itemValue')][1]");
		WebElement fieldValue = findPresentElement(columnRecord, Speed.slow);
		return fieldValue.getAttribute("value").equals(columnData);
	}

	@Override
	public boolean verifyDateAndTimeRecord(String columnName, String columnData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath(".//*[@id='isheet_module_viewItem_modal']//*[normalize-space(text())='" + columnName.trim() + "']//following::*[contains(@name,'itemValue')][1]");
		WebElement fieldValue = findPresentElement(columnRecord, Speed.slow);
		return fieldValue.getAttribute("value").trim().equals(columnData.trim());
	}

	@Override
	public boolean verifyUserLookupRecord(String columnName, String... usersData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'userDisplay')]//a");
		List<WebElement> usersList = driver.findElements(columnRecord);
		String[] lookUpData = usersData;
		List<String> userLookUpData = Arrays.asList(lookUpData);
		boolean result = false;
		int count = 0;
		for (WebElement lookUp : usersList)
		{
			if (userLookUpData.contains(lookUp.getText().trim()))
			{
				count++;
			}
		}
		if (count == usersData.length)
		{
			result = true;
		}

		return result;

		// Following code will not work

		// if (usersList.size() == usersData.length) {
		// for (Object user : usersData) {
		// if (!usersList.contains(user)) { return false;
		// }
		// }
		// return true;
		// }
		// else {
		// return false;
		// }
	}

	@Override
	public boolean verifyHyperlinkRecord(String columnName, String linkDisplayName, String linkURL)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("(//*[@id='isheet_module_viewItem_modal']//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*//a)[1]");
		WebElement fieldValue = findPresentElement(columnRecord, Speed.slow);
		return (fieldValue.getText().trim().equals(linkDisplayName) && fieldValue.getAttribute("href").trim().startsWith(linkURL));
	}

	@Override
	public boolean verifyFileLinkRecord(String columnName, String[] fileNames)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[@module='ISHEET_DOCUMENT_LINK_TYPE_COLUMN']");
		List<WebElement> fileList = driver.findElements(columnRecord);

		List<String> fileNameList = Arrays.asList(fileNames);
		boolean result = false;
		int count = 0;
		for (WebElement fileNameRecord : fileList)
		{

			if (fileNameList.contains(fileNameRecord.getText().trim()))
			{
				count++;

			}
		}
		if (count == fileNames.length)
		{
			result = true;
		}
		return result;
	}

	@Override
	public boolean verifyFolderLinkRecord(String columnName, String[] folderNames)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[@class='icon icon-folder']");
		List<WebElement> folderList = driver.findElements(columnRecord);
		List<String> folderNameRecord = Arrays.asList(folderNames);
		boolean result = false;
		int count = 0;
		for (WebElement folder : folderList)
		{
			String folderName = folder.getAttribute("data-original-title").substring(1).trim();
			if (folderNameRecord.contains(folderName))
			{
				count++;
			}
		}
		if (count == folderNames.length)
		{
			result = true;
		}
		return result;
	}

	@Override
	public boolean verifyImageColumnRecord(String columnName, String imageData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		if (imageData.startsWith("http://") || imageData.startsWith("https://"))
		{
			By columnData = By.xpath(".//*[@id='isheet_module_viewItem_modal']//*[normalize-space(text())='" + columnName.trim() + "']//parent::*//img[1]");
			WebElement fieldValue = findPresentElement(columnData, Speed.slow);
			return fieldValue.getAttribute("src").trim().equals(imageData);
		}
		else
		{
			// Solution will be provided by Ravi
			return true;
		}
	}

	@Override
	public boolean verifyIsheetLinkRecord(String columnName, String isheetName, List<List<String>> isheetLinkData)
	{
		System.out.println("isheetLink");
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following-sibling::*//*[contains(@id,'injectLinkID')]//a");
		List<WebElement> isheetLinks = driver.findElements(columnRecord);
		boolean status = false;
		int count = 0;
		for (WebElement isheetLink : isheetLinks)
		{
			findVisibleElement(columnRecord, Speed.slow);
			isheetLink.click();
			while (isDisplayed(grayLoader))
			{
				;
			}
			findVisibleElement(By.xpath("(.//*[@class='modal fade in'])[last()]//*[contains(@id,'close')]"), Speed.slow);
			By itemValues = By.xpath(".//*[contains(@id,'isheet_module_viewItem_modal') and contains(@style,'display: block;')][last()]//*[contains(@name,'itemValue')]");
			List<WebElement> recordValuesCount = driver.findElements(itemValues);
			List<String> recordValuesData = new ArrayList<>();
			for (int i = 1; i <= recordValuesCount.size(); i++)
			{
				WebElement record = findPresentElement(By.xpath("(.//*[contains(@id,'isheet_module_viewItem_modal') and contains(@style,'display: block;')][last()]//*[contains(@name,'itemValue')])[" + i + "]"));
				recordValuesData.add(record.getAttribute("value"));
			}

			if (isheetLinkData.contains(recordValuesData))
			{
				count++;
			}
			findClickableElement(By.xpath(".//*[contains(@id,'isheet_module_viewItem_modal') and contains(@style,'display: block;')][last()]//*[contains(@id,'MAIN_CLOSE_BUTTON')]")).click();
		}
		if (count == isheetLinkData.size())
		{
			status = true;
		}
		return status;
	}

	// @Override
	// public boolean verifyLookUpColumnRecords(Map<List<String>, Boolean> lookupColumnData) {
	// findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
	// List<String> record = new ArrayList<>();
	// List<String> lookupRecord = new ArrayList<>();
	// By lookupDataView = By.xpath(".//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')]");
	// List<WebElement> lookupDataViewCount = driver.findElements(lookupDataView);
	// int recordCount = 0;
	// int verificationCount = 0;
	// boolean status = false;
	// for (Map.Entry<List<String>, Boolean> lookupColumn : lookupColumnData.entrySet()) {
	// if (lookupColumn.getValue()) {
	// recordCount++;
	// record = lookupColumn.getKey();
	// int count = 1;
	// for (int j = 0; j < record.size(); j++) {
	// String xpath = "";
	// lookupRecord.clear();
	// for (int i = 1; i <= lookupDataViewCount.size(); i++) {
	// if (i == 1) {
	// xpath = "(.//*[@class='PreFormat'])[" + count + "]//*";
	// lookupRecord.add(findVisibleElement(By.xpath(xpath)).getText());
	// } else {
	// xpath = "(" + xpath + "//ancestor::*[contains(@id,'contentTR')][1]//following-sibling::*[contains(@id,'contentTR')][1]//*[@class='PreFormat'])[" + count + "]//*";
	// lookupRecord.add(findVisibleElement(By.xpath(xpath)).getText());
	// }
	// }
	// count++;
	// if (lookupRecord.containsAll(record)) {
	// verificationCount++;
	// }
	// }
	// }
	// }
	// if (recordCount == verificationCount) {
	// status = true;
	// }
	// return status;
	// }

	// .//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')] - Total Lookup records (Columns in a record)
	// (.//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')])[3] - one by one column
	// (.//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')])[3]//descendant::*[@class='nextPreviousParent'] - Records for particular column

	@Override
	public boolean verifyLookUpColumnRecords(Map<List<String>, Boolean> lookupColumnRecords)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		List<List<String>> allColumnRecords = new ArrayList<>();
		By columnBlocks = By.xpath(".//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')]");
		List<WebElement> columnBlocksCount = driver.findElements(columnBlocks);
		for (int columnBlock = 1; columnBlock <= columnBlocksCount.size(); columnBlock++)
		{
			By columnRecords = By.xpath("(.//*[contains(@id,'itemValue')]//preceding-sibling::*[contains(@id,'injectLinkID')])[" + columnBlock + "]//descendant::*[@class='nextPreviousParent']//*[@class='PreFormat']//a");
			List<WebElement> columnRecordsCount = driver.findElements(columnRecords);
			List<String> columnRecordsList = new ArrayList<>();
			for (int columnRecord = 0; columnRecord < columnRecordsCount.size(); columnRecord++)
			{
				columnRecordsList.add(columnRecordsCount.get(columnRecord).getText());
			}
			allColumnRecords.add(columnRecordsList);
		}
		int count = 0;
		int columnCount = 0;
		boolean status = false;
		for (Map.Entry<List<String>, Boolean> lookupColumnRecord : lookupColumnRecords.entrySet())
		{
			if (lookupColumnRecord.getValue())
			{
				columnCount++;
				List<String> record = lookupColumnRecord.getKey();
				List<String> list = new ArrayList<>();
				try
				{
					for (int i = 0; i < lookupColumnRecords.size(); i++)
					{
						list.clear();
						for (int recordSize = 0; recordSize < record.size(); recordSize++)
						{
							list.add(allColumnRecords.get(recordSize).get(i));
						}
						if (list.containsAll(record))
						{
							count++;
							break;
						}
					}
				}
				catch (IndexOutOfBoundsException e)
				{
					continue;
				}
			}
		}
		if (columnCount == count)
		{
			status = true;
		}
		return status;
	}

	@Override
	public boolean verifyAttachmentRecord(String columnName, String[] fileNames)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(.),'" + columnName.trim() + "')]//child::span//a[contains(text(),'.')]");
		List<WebElement> attachmentList = driver.findElements(columnRecord);

		int count = 0;
		List<String> attachmentRecord = Arrays.asList(fileNames);
		boolean result = false;
		for (WebElement fileLink : attachmentList)
		{
			String attachmentName = fileLink.getText().trim();
			if (attachmentRecord.contains(attachmentName))
			{
				// result = true;
				count++;
			}
		}
		if (count == fileNames.length)
		{
			result = true;
		}

		return result;
	}

	boolean verifyJoinColumnRecords(String columnName, String[] columnJoinData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(.),'" + columnName.trim() + "')]//child::a[not(@data-original-title='Description')]");
		List<WebElement> dataList = driver.findElements(columnRecord);

		List<String> joinColumnRecords = Arrays.asList(columnJoinData);
		boolean result = false;
		int count = 0;
		for (WebElement recordList : dataList)
		{
			String record = recordList.getText().trim();
			if (joinColumnRecords.contains(record))
			{
				count++;
			}
		}
		if (count == columnJoinData.length)
		{
			result = true;
		}

		return result;
	}

	boolean verifyCalculationRecords(String columnName, String calculationData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(.),'" + columnName.trim() + "')]//child::div[contains(@class,'col')]");
		WebElement fieldValue = findPresentElement(columnRecord, Speed.slow);
		return fieldValue.getText().trim().equals(calculationData.trim());
	}

	boolean verifyAutoIncrementColumnRecords(String columnName, String calculationData)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(.),'" + columnName.trim() + "')]//child::div[contains(@class,'col')]");
		WebElement fieldValue = findPresentElement(columnRecord, Speed.slow);
		return fieldValue.getText().trim().equals(calculationData.trim());
	}

	@Override
	public boolean verifyIsheetRecord(String[] columnName, IsheetData isheetData)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		boolean result = false;
		for (int colCount = 0; colCount < columnName.length; colCount++)
		{
			switch (columnName[colCount].toLowerCase().trim())
			{
				case "single line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getSingleLineText().entrySet())
					{
						result = verifySingleLineTextRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;
				case "multiple line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getMultiLineText().entrySet())
					{
						result = verifyMultiLineTextRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "number":
					for (Map.Entry<String, String> columnRecord : isheetData.getNumber().entrySet())
					{
						result = verifyNumberRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "date and time":
					for (Map.Entry<String, String> columnRecord : isheetData.getDateAndTime().entrySet())
					{
						result = verifyDateAndTimeRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "user lookup":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAddUserLookUpRecord().entrySet())
					{
						result = verifyUserLookupRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "hyperlink":
					for (Map.Entry<String, String> columnRecord : isheetData.getHyperLinkData().entrySet())
					{
						String hyperLinkData[] = columnRecord.getValue().trim().split(",");
						String linkName = hyperLinkData[0].trim();
						String url = hyperLinkData[1].trim();
						result = verifyHyperlinkRecord(columnRecord.getKey(), linkName, url);
						if (!result)
						{
							break;
						}
					}
					break;

				case "attachment":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAttachmentData().entrySet())
					{
						result = verifyAttachmentRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "file link":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getFileLinkData().entrySet())
					{
						result = verifyFileLinkRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "folder link":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getFolderLinkData().entrySet())
					{
						result = verifyFolderLinkRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "isheet link":
					for (Map.Entry<String, Map<String, List<List<String>>>> columnRecord : isheetData.getIsheetLinkData().entrySet())
					{
						String colName = columnRecord.getKey().trim();
						for (Map.Entry<String, List<List<String>>> isheetRecord : columnRecord.getValue().entrySet())
						{
							String isheetName = isheetRecord.getKey().trim();
							result = verifyIsheetLinkRecord(colName, isheetName, isheetRecord.getValue());
							if (!result)
							{
								break;
							}
						}
						if (!result)
						{
							break;
						}
					}
					break;

				case "join":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getJoinData().entrySet())
					{
						result = verifyJoinColumnRecords(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "calculation":
					for (Map.Entry<String, String> columnRecord : isheetData.getCalculationData().entrySet())
					{
						result = verifyCalculationRecords(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "auto increment":
					for (Map.Entry<String, String> columnRecord : isheetData.getAutoIncrementData().entrySet())
					{
						result = verifyAutoIncrementColumnRecords(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "lookup":
					for (Map.Entry<String, Map<List<String>, Boolean>> columnRecord : isheetData.getLookUpData().entrySet())
					{
						result = verifyLookUpColumnRecords(columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;

				case "image":
					for (Map.Entry<String, String> columnRecord : isheetData.getImageColumnData().entrySet())
					{
						result = verifyImageColumnRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;
	case "choice":
					for (Map.Entry<String, String> columnRecord : isheetData.getChoiceColumnData().entrySet())
					{
						result = verifyChoiceRecord(columnRecord.getKey(), columnRecord.getValue());
						if (!result)
						{
							break;
						}
					}
					break;
				default:
					break;
			}
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/*********************************************************************** EDIT RECORD METHODS *******************************************************************/

	@Override
	public void editSingleLineColumnData(String columnName, String data)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		addSingleLineColumnData(columnName, data);
	}

	@Override
	public void editMultipleLineColumnData(String columnName, String data)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		addMultipleLineColumnData(columnName, data);
	}

@Override
	public void editChoiceData(String columnName, String data)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		selectChoiceColumnData(columnName, data);
	}

	@Override
	public void editNumberColumnData(String columnName, String value)
	{
		editSingleLineColumnData(columnName, value);
	}

	@Override
	public void editDateAndTimeColumnData(String columnName, String date)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		addDateAndTimeColumnData(columnName, date);
	}

	@Override
	public void editUserLookupColumnData(String columnName, String[] addUsers, Map<String, String[]> removeUsers)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (addUsers != null)
		{
			addUserLookupColumnData(columnName, addUsers);
		}
	}

	@Override
	public void editHyperlinkColumnData(String columnName, String displayName, String url)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		addHyperlinkColumnData(columnName, displayName, url);
		// findVisibleElement(By.xpath("//*[text()='Enter URL']")).click();
	}

	@Override
	public void editImageColumnData(String columnName, String imageFile)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (isDisplayed(By.xpath("((.//*[@class='modal fade in'])[last()]//label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@class,'editPictureDelete')])[1]")))
		{
			WebElement removeImage = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@class,'editPictureDelete')])[1]"));
			removeImage.click();
			addImageColumnData(columnName, imageFile);
		}
		else
		{
			addImageColumnData(columnName, imageFile);
		}
	}

	@Override
	public void editAttachmentColumnData(String columnName, String... addAttachments)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (addAttachments != null)
		{
			addAttachmentColumnData(columnName, addAttachments);
		}
	}

	@Override
	public void editFileLinkColumn(String columnName, String[] addFiles)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (addFiles != null)
		{
			addFileLinkCoumnData(columnName, addFiles);
		}
	}

	@Override
	public void editFolderLinkColumn(String columnName, String[] addFolders)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (addFolders != null)
		{
			addFolderLinkColumnData(columnName, addFolders);
		}
	}

	@Override
	public void editIsheetLinkColumn(String columnName, String isheetName, List<List<String>> columnData)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		if (isheetName != null && columnData != null)
		{
			addIsheetLinkColumnData(columnName, isheetName, columnData);
		}
	}

	@Override
	public void editlookupcolumnData(String columnName, Map<List<String>, Boolean> columnData)
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		addLookupColumnData(columnName, columnData);
	}

	@Override
	public void editRecord(String[] columnName, IsheetData isheetData)
	{
		for (int colCount = 0; colCount < columnName.length; colCount++)
		{

			switch (columnName[colCount].toLowerCase().trim())
			{
				case "single line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getSingleLineText().entrySet())
					{
						editSingleLineColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;
				case "multiple line text":
					for (Map.Entry<String, String> columnRecord : isheetData.getMultiLineText().entrySet())
					{
						editMultipleLineColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "number":
					for (Map.Entry<String, String> columnRecord : isheetData.getNumber().entrySet())
					{
						editNumberColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "date and time":
					for (Map.Entry<String, String> columnRecord : isheetData.getDateAndTime().entrySet())
					{
						editDateAndTimeColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "user lookup":
					if (isheetData.getRemoveUserLookUpRecord() != null)
					{
						removeUserFromUserLookup(isheetData.getRemoveUserLookUpRecord());
					}
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAddUserLookUpRecord().entrySet())
					{
						editUserLookupColumnData(columnRecord.getKey(), columnRecord.getValue(), isheetData.getRemoveUserLookUpRecord());
					}
					break;

				case "image":
					if (isheetData.getRemoveImageData() != null)
					{
						removeImage(isheetData.getRemoveImageData());
					}
					for (Map.Entry<String, String> columnRecord : isheetData.getImageColumnData().entrySet())
					{
						editImageColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "hyperlink":
					for (Map.Entry<String, String> columnRecord : isheetData.getHyperLinkData().entrySet())
					{
						String hyperlinkColumnName = columnRecord.getKey().trim();
						String hyperLinkData[] = columnRecord.getValue().trim().split(",");
						String linkName = hyperLinkData[0].trim();
						String url = hyperLinkData[1].trim();
						editHyperlinkColumnData(hyperlinkColumnName, linkName, url);
					}
					break;

				case "attachment":
					if (isheetData.getRemoveAttachmentData() != null)
					{
						removeAttachmetns(isheetData.getRemoveAttachmentData());
					}
					for (Map.Entry<String, String[]> columnRecord : isheetData.getAttachmentData().entrySet())
					{
						editAttachmentColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				case "file link":
					// for (Map.Entry<String, String[]> columnRecord : isheetData.getFileLinkData().entrySet()) {
					// editFileLinkColumn(columnRecord.getKey(), columnRecord.getValue());
					// }
					if (isheetData.getFileLinkData() != null)
					{
						addFilesToFileLinkColumn(isheetData.getFileLinkData());
					}
					if (isheetData.getRemoveFileLinkData() != null)
					{
						removeFilesFromFileLinkColumn(isheetData.getRemoveFileLinkData());
					}
					break;

				case "folder link":
					for (Map.Entry<String, String[]> columnRecord : isheetData.getFolderLinkData().entrySet())
					{
						editFolderLinkColumn(columnRecord.getKey(), columnRecord.getValue());
					}
					if (isheetData.getRemoveFolderLinkData() != null)
					{
						removeFoldersFromFolderLinkColumn(isheetData.getRemoveFolderLinkData());
					}
					break;

				case "isheet link":
					if (isheetData.getRemoveIsheetLinkData() != null)
					{
						removeIsheetLinkData(isheetData.getRemoveIsheetLinkData());
					}
					for (Map.Entry<String, Map<String, List<List<String>>>> columnRecord : isheetData.getIsheetLinkData().entrySet())
					{
						String colName = columnRecord.getKey().trim();
						Map<String, List<List<String>>> records = columnRecord.getValue();
						for (Map.Entry<String, List<List<String>>> record : records.entrySet())
						{
							editIsheetLinkColumn(colName, record.getKey(), record.getValue());
						}
					}
					break;
				case "lookup":
					for (Map.Entry<String, Map<List<String>, Boolean>> columnRecord : isheetData.getLookUpData().entrySet())
					{
						String colName = columnRecord.getKey().trim();
						editlookupcolumnData(colName, columnRecord.getValue());
					}
					break;
		case "choice":
					for (Map.Entry<String, String> columnRecord : isheetData.getChoiceColumnData().entrySet())
					{
						selectChoiceColumnData(columnRecord.getKey(), columnRecord.getValue());
					}
					break;

				default:
					break;
			}

		}
	}

	/*********************************************************************** REMOVE RECORD METHODS *******************************************************************/

	@Override
	public boolean removeUserFromUserLookup(Map<String, String[]> userLookUpData)
	{
		boolean result = true;
		for (Map.Entry<String, String[]> userData : userLookUpData.entrySet())
		{
			String columnName = userData.getKey().trim();
			String[] users = userData.getValue();

			for (String user : users)
			{
				By userXpath = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following::*[normalize-space(text())='" + user.trim() + "']//preceding-sibling::a[@class='close'][1]");
				findVisibleElement(userXpath, Speed.slow).click();

				if (isDisplayed(userXpath, Speed.slow))
				{
					result = false;
					break;
				}
			}
			if (!result)
			{
				break;
			}
		}

		return result;
	}

	@Override
	public boolean removeImage(String... imageColumns)
	{
		boolean result = true;
		for (String columnName : imageColumns)
		{
			By imageRemoveLink = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following::a[contains(@class,'editPictureDelete') and @data-original-title='Remove'][1]");
			findVisibleElement(imageRemoveLink, Speed.slow).click();
			if (isDisplayed(imageRemoveLink, Speed.slow))
			{
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean removeAttachmetns(Map<String, String[]> attachmentData)
	{
		boolean result = true;

		for (Map.Entry<String, String[]> attachmentRecord : attachmentData.entrySet())
		{
			String columnName = attachmentRecord.getKey().trim();

			for (String attachment : attachmentRecord.getValue())
			{
				By attachmentRemoveLink = By.xpath("(.//*[@class='modal fade in'])[last()]//following::*[normalize-space(text())='" + columnName.trim() + "']//following::*[normalize-space(text())='" + attachment.trim() + "'][1]//preceding::a[@data-original-title='Remove'][1]");
				findVisibleElement(attachmentRemoveLink, Speed.slow).click();
				if (isDisplayed(attachmentRemoveLink, Speed.slow))
				{
					result = false;
					break;
				}
			}
			if (!result)
			{
				break;
			}
		}

		return result;
	}

	@Override
	public boolean removeFilesFromFileLinkColumn(Map<String, String[]> fileLinkColumn)
	{
		boolean result = true;
		for (Map.Entry<String, String[]> fileData : fileLinkColumn.entrySet())
		{
			String columnName = fileData.getKey();
			By selectFileButton = By.xpath("(.//*[@class='modal fade in'])[last()]//following::*[normalize-space(text())='" + columnName.trim() + "']//following::*[normalize-space(text())='Select files'][1]");
			findVisibleElement(selectFileButton, Speed.slow).click();
			for (String fileName : fileData.getValue())
			{
				By selectedFile = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + fileName.trim() + "']//parent::*[contains(@class,'fancytree-selected')][1]");
				if (findPresentElement(selectedFile, Speed.slow) != null)
				{
					By fileCheckBox = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + fileName.trim() + "']//preceding-sibling::*[@class='fancytree-checkbox']");
					findVisibleElement(fileCheckBox, Speed.slow).click();
				}
				if (findPresentElement(selectedFile, Speed.slow) != null)
				{
					result = false;
					break;
				}
			}
			if (!result)
			{
				break;
			}
		}
		findVisibleElement(addAttachmentButton, Speed.slow).click();
		return result;
	}

	@Override
	public boolean addFilesToFileLinkColumn(Map<String, String[]> fileLinkColumn)
	{
		boolean result = true;
		for (Map.Entry<String, String[]> fileData : fileLinkColumn.entrySet())
		{
			String columnName = fileData.getKey();
			By selectFileButton = By.xpath("(.//*[@class='modal fade in'])[last()]//following::*[normalize-space(text())='" + columnName.trim() + "']//following::*[normalize-space(text())='Select files'][1]");
			findVisibleElement(selectFileButton, Speed.slow).click();
			for (String fileName : fileData.getValue())
			{
				By selectedFile = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + fileName.trim() + "']//parent::*[contains(@class,'fancytree-exp-n')][1]");
				if (findPresentElement(selectedFile, Speed.slow) != null)
				{
					By fileCheckBox = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + fileName.trim() + "']//preceding-sibling::*[@class='fancytree-checkbox']");
					findVisibleElement(fileCheckBox, Speed.slow).click();
				}
				if (findPresentElement(selectedFile, Speed.slow) != null)
				{
					result = false;
					break;
				}
			}
			if (!result)
			{
				break;
			}
		}
		findVisibleElement(addAttachmentButton, Speed.slow).click();
		return result;
	}

	@Override
	public boolean removeFoldersFromFolderLinkColumn(Map<String, String[]> folders)
	{
		boolean result = true;

		for (Map.Entry<String, String[]> folderLinkColumnData : folders.entrySet())
		{
			String columnName = folderLinkColumnData.getKey();
			for (String folderName : folderLinkColumnData.getValue())
			{
				By folderLinkRemoveButton = By.xpath("(.//*[@class='modal fade in'])[last()]//following::*[normalize-space(text())='" + columnName.trim() + "']//following::*[normalize-space(text())='/" + folderName.trim() + "'][1]//preceding::a[@data-original-title='Remove'][1]");
				findVisibleElement(folderLinkRemoveButton, Speed.slow).click();
				if (isDisplayed(folderLinkRemoveButton, Speed.slow))
				{
					result = false;
					break;
				}
			}
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	@Override
	public void removeIsheetLinkData(List<List<String>> removeData)
	{
	}

	@Override
	public void removelookupColumnData(String columnName, Map<List<String>, Boolean> columnData)
	{
		addLookupColumnData(columnName, columnData);
	}

	@Override
	public void closeViewRecordModal()
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		WebElement viewRecordModalCloseButtonEle = findClickableElement(viewRecordModalCloseButton);
		viewRecordModalCloseButtonEle.click();
		findPresentElement(locModalViewIsheetRecordClosed, Speed.slow);
	}

	@Override
	public void saveEditedIseetRecord()
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		WebElement saveButtonEle = findClickableElement(locBtnSaveRecord);
		saveButtonEle.click();
		// findPresentElement(locModalEditIsheetRecordClosed, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
	}

	@Override
	public boolean verifyCustomModalMessage(String title, String message)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		WebElement customModalMessageEle = findVisibleElement(customModalMessage);
		return customModalMessageEle.getText().equals(message.trim());
	}

	@Override
	public boolean verifyCancelButtonOnCustomModal(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		return isDisplayed(customModalCancelBtn);
	}

	@Override
	public boolean verifyOKButtonOnCustomModal(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		return isDisplayed(customModalOKBtn);
	}

	@Override
	public void cancelCustomModal(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		WebElement cancelBtn = findVisibleElement(customModalCancelBtn);
		cancelBtn.click();
		By customModalClosed = By.xpath("(.//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none')])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findPresentElement(customModalClosed, Speed.slow);
	}

	@Override
	public void closeCustomModal(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		WebElement cancelBtn = findVisibleElement(customModalCloseBtn);
		cancelBtn.click();
		By customModalClosed = By.xpath("(.//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none')])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findPresentElement(customModalClosed, Speed.slow);
	}

	@Override
	public boolean verifyCustomModalClosed(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		return !isDisplayed(customModalOpened, Speed.slow);
	}

	@Override
	public void clickOKOnCustomModal(String title)
	{
		By customModalOpened = By.xpath("(.//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none'))])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findVisibleElement(customModalOpened, Speed.slow);
		WebElement cancelBtn = findVisibleElement(customModalOKBtn);
		cancelBtn.click();
		By customModalClosed = By.xpath("(.//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none')])[last()]//*[@id='customMessageModalTitle' and text()='" + title.trim() + "']");
		findPresentElement(customModalClosed, Speed.slow);
	}

	@Override
	public boolean verifyGlobalDeletionMessage(String globalMessage)
	{
		findPresentElement(By.xpath("(.//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none')])[last()]//*[@id='customMessageModalTitle' and text()='Delete record']"), Speed.slow);
		WebElement message = findVisibleElement(globalDeletionMessage);
		return message.getText().equals(globalMessage);
	}

	@Override
	public void cancelGolbalMessage()
	{
		if (isDisplayed(globalDeletionMessage))
		{
			WebElement removeEle = findVisibleElement(globalDeletionMessageRemove);
			removeEle.click();
		}
	}

	@Override
	public void selectMoreActionOptionOnViewModal(String option)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		selectOptionFromDropDown(viewRecordModalMoreAction, viewRecordModalMoreActionDropBox, viewRecordModalMoreActionList, option);
	}

	@Override
	public void setAddAnotherRecordChkBox(boolean desiredState)
	{
		setSelection(locChckBxAddAnotherRecord, desiredState);
	}

	@Override
	public boolean verifyAddEditRecordGlobalMessage(String golbalMessage)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		WebElement message = findVisibleElement(addEditRecordGolbalMessage);
		return message.getText().equals(golbalMessage);
	}

	@Override
	public boolean verifyAddRecordModalOpened()
	{
		findVisibleElement(locModalAddIsheetRecordOpened, Speed.slow);
		return isDisplayed(locModalAddIsheetRecordOpened);
	}

	@Override
	public boolean verifyAddRecordModalClosed()
	{
		findPresentElement(locModalAddIsheetRecordClosed, Speed.slow);
		return !isDisplayed(locModalAddIsheetRecordOpened);
	}

	@Override
	public void verifyBlankColumn(String[] columnName)
	{

	}

	@Override
	public void setEditAnotherRecordCheckBox(boolean desiredState)
	{
		setSelection(editNextRecordChkBox, desiredState);
	}

	@Override
	public boolean verifyEditRecordModalOpened()
	{
		findVisibleElement(locModalEditIsheetRecordOpened, Speed.slow);
		return isDisplayed(locModalEditIsheetRecordOpened);
	}

	@Override
	public boolean verifyEditRecordModalClosed()
	{
		findPresentElement(locModalEditIsheetRecordClosed, Speed.slow);
		return !isDisplayed(locModalEditIsheetRecordClosed);
	}

	/**
	 * Verifies if favorite star is selected or not.
	 *
	 * @return true
	 *         if successful.
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyFavouriteIconIsSelected()
	{
		// System.out.println(favouriteStarSelected);
		return isDisplayed(favouriteStarSelected, Speed.slow);
	}

	/**
	 * Click on add to favorite Star Icon
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void addIsheetToFavourites()
	{
		if (!verifyFavouriteIconIsSelected())
		{
			findVisibleElement(favouriteButton).click();
		}
		else
		{
			System.out.println("Favourite Star is already selected.");
		}
	}

	/**
	 * Click on remove from Star Icon
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void removeIsheetFromFavourites()
	{
		if (verifyFavouriteIconIsSelected())
		{
			findVisibleElement(favouriteStarSelected).click();
		}
		else
		{
			System.out.println("No favourites star available.");
		}
	}

	/**
	 * get Isheet record index
	 *
	 * @return record index
	 * @author badal.gandhi
	 */
	@Override
	public void selectOptionFromMoreOptionsOfIsheetRecord(String[] columnName, IsheetData isheetData, String option)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(gridBox, Speed.slow);
		int recordIndex = 1; // need to create method to get isheet record index

		By recordMoreoptionsXpath = By.xpath("(//*[@id='gridbox']//a[@data-original-title='More actions'])[" + recordIndex + "]");
		findVisibleElement(recordMoreoptionsXpath, Speed.slow).click();

		System.out.println("//*[@role='menu' and contains(@style,'display: block;')]//child::*[normalize-space(text())='" + option.trim() + "']");
		By optionXpath = By.xpath("//*[@role='menu' and contains(@style,'display: block;')]//child::*[normalize-space(text())='" + option.trim() + "']");
		findVisibleElement(optionXpath, Speed.slow).click();

		findPresentElement(lastOpenedModel, Speed.slow);
	}

	/**
	 * Click save as draft link in edit isheet record model
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void saveIsheetRecordAsDraft()
	{
		findVisibleElement(isheetEditModelSaveArrow, Speed.slow).click();
		findVisibleElement(saveAsDraftLink).click();
		while (isDisplayed(grayLoader))
		{
			;
		}
	}

	/**
	 * To verify presence of resumeEditMessage
	 *
	 * @return true if message is present
	 *         false if message is not present
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyResumeEditMessage()
	{
		findVisibleElement(resumeEditingMessage, Speed.slow);
		return isDisplayed(resumeEditingMessage, Speed.slow);
	}

	/**
	 * click on resume link
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void clickResume()
	{
		findVisibleElement(resumeEditingMessage_resumeLink, Speed.slow).click();
	}

	/**
	 * click on discard link
	 * To verify presence of resumeEditMessage
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void clickDiscard()
	{
		findVisibleElement(resumeEditingMessage_discardLink, Speed.slow).click();
	}

	@Override
	public void selectIsheetRecord()
	{
		findVisibleElement(gridBox, Speed.slow);
		int recordIndex = 1; // need to create method to get isheet record index
		findClickableElement(By.xpath("(//*[@id='gridbox']//img)[" + recordIndex + "]")).click();
	}

	@Override
	public boolean verifyDraftItemOptionVisibility()
	{
		findVisibleElement(gridBox, Speed.slow);
		if (isDisplayed(isheetViewDropDownOpened))
		{
			findVisibleElement(isheetViewDropDown).click();
		}
		return verifyOptionFromDropDown(isheetViewDropDown, isheetViewDropDownBox, isheetViewDropDownList, "Draft items");
	}

	@Override
	public void selectIsheetDraftItems()
	{
		selectIsheetView("Draft items");
	}

	@Override
	public void selectIsheetView(String viewName)
	{
		scrollToTop();
		findVisibleElement(gridBox, Speed.slow);
		WebElement viewDropdown = findClickableElement(isheetViewDropDown, Speed.slow);
		if (!isDisplayed(isheetViewDropDownOpened, Speed.slow))
		{
			viewDropdown.click();
		}
		findVisibleElement(isheetViewDropDownOpened, Speed.slow);
		WebElement viewNameEle = findVisibleElement(By.xpath(isheetViewDropDownList + "[normalize-space(.)='" + viewName.trim() + "']"));
		viewNameEle.click();
	}

	@Override
	public boolean verifyCurrentViewName(String viewName)
	{
		findVisibleElement(gridBox, Speed.slow);
		WebElement currentViewNameEle = findVisibleElement(currentViewName);
		return currentViewNameEle.getText().equals(viewName.trim());
	}

	@Override
	public boolean verifyIsheetRecordsEmpty()
	{
		findVisibleElement(gridBox, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
		return isDisplayed(isheetNoData, Speed.slow);
	}

	@Override
	public void waitTillAutoSaveMessage()
	{
		while (!isDisplayed(autoSaveMessage))
		{
			;
		}
	}

	@Override
	public void clickCancelOnAddRecordModal()
	{
		WebElement cancelButton = findVisibleElement(locBtnCancelAddRecordModal);
		cancelButton.click();
	}

	/**
	 * Verify No data is present
	 *
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyNoDataIsPresent()
	{
		findVisibleElement(gridBox, Speed.slow);
		return isDisplayed(noData, Speed.slow);
	}

	/**
	 * Verify search result contains searched text
	 *
	 * @param searchText
	 *        text to search
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifySearchResultContainsSearchText(String searchText)
	{
		int count = 0;
		findVisibleElement(By.xpath(isheetTable_HeaderNames), Speed.slow);
		int rowCount = driver.findElements(By.xpath(allIsheetRows)).size();
		for (int i = 2; i <= rowCount; i++)
		{
			int columnCount = driver.findElements(By.xpath("(//*[@class='obj']//tr[" + i + "])//*[normalize-space(text())]")).size();
			for (int j = 1; j <= columnCount; j++)
			{
				By columnXpath = By.xpath("((//*[@class='obj']//tr[" + i + "])//*[normalize-space(text())])[" + j + "]");
				if (isDisplayed(columnXpath))
				{
					String[] searchResults;
					if (searchText.trim().contains(" "))
					{
						searchResults = searchText.split(" ");
						for (int s = 0; s < searchResults.length; s++)
						{
							if (findVisibleElement(columnXpath).getText().trim().contains(searchResults[s].trim()))
							{
								count++;
							}
						}
					}
					else if (findVisibleElement(columnXpath).getText().trim().contains(searchText.trim()))
					{
						count++;
					}
				}
				if (count > 0)
				{
					break;
				}
			}
			if (count == 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Click on Save Search
	 *
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public void clickSaveSearch()
	{
		WebElement saveSearchElem = findVisibleElement(saveSearchButton);
		saveSearchElem.click();
		findPresentElement(saveSearchModal, Speed.slow);
	}

	/**
	 * Set name in save search modal
	 *
	 * @param name
	 *        name to save search as
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public void setNameInSaveSearch(String name)
	{
		WebElement nameElem = findVisibleElement(saveSearch_NameInput);
		nameElem.clear();
		nameElem.sendKeys(name.trim());
	}

	/**
	 * Click save in search modal
	 * Created: 13 March 2018
	 *
	 * @author dheeraj.rajput
	 *         Updated:
	 */
	@Override
	public void clickSaveInSaveSearchModal()
	{
		WebElement saveElem = findVisibleElement(saveSearch_SaveButton);
		saveElem.click();
		findPresentElement(saveSearchModal, Speed.slow);
	}

	/**
	 * Expand search filter
	 *
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public void expandSearchFilter()
	{
		if (!isDisplayed(search_FilterBox, Speed.slow))
		{
			WebElement filterElem = findVisibleElement(search_FilterButton, Speed.slow);
			filterElem.click();
			findVisibleElement(search_FilterBox, Speed.slow);
		}
	}

	/**
	 * Select option from search filter
	 *
	 * @param filterOption
	 *        filter option to select
	 * @author dheeraj.rajput
	 *         Created: 13 March 2018
	 *         Updated:
	 */
	@Override
	public void selectSearchFilterOption(String filterOption)
	{
		expandSearchFilter();
		int size = driver.findElements(By.xpath(search_FilterItems)).size();
		for (int i = 1; i <= size; i++)
		{
			By link = By.xpath("(" + search_FilterItems + ")[" + i + "]");
			if (isDisplayed(link, Speed.slow))
			{
				WebElement elem = findVisibleElement(link);
				if (elem.getText().trim().equals(filterOption.trim()))
				{
					elem.click();
					break;
				}
			}
		}
	}

	/**
	 * Click on more action of a saved search item in Saved Searches modal
	 *
	 * @param savedSearchName
	 *        name of the saved search
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void clickMoreActionOfaSavedSearch(String savedSearchName)
	{
		findPresentElement(search_ViewAllSavedSearchesModal, Speed.slow);
		int size = driver.findElements(By.xpath(search_ViewAllSavedSearches_SavedList)).size();
		for (int i = 1; i <= size; i++)
		{
			String savedSearchXpath = "(.//*[@id='isheet_module_allSavedSearch_modal_BODY']//*[@class='edit-value'])[" + i + "]";
			if (isDisplayed(By.xpath(savedSearchXpath), Speed.slow))
			{
				if (findVisibleElement(By.xpath(savedSearchXpath)).getText().trim().equals(savedSearchName.trim()))
				{
					By moreActionIcon = By.xpath(savedSearchXpath + "/ancestor::*[2]/following-sibling::*//*[contains(@class,'dropdown-toggle')]");
					findVisibleElement(moreActionIcon).click();
					findVisibleElement(search_ViewAllSavedSearches_MoreActionDropDown, Speed.slow);
				}
			}
		}
	}

	/**
	 * Select more action item of a saved search in Saved Searches modal
	 *
	 * @param savedSearchName
	 *        name of the saved search
	 * @param moreActionItem
	 *        more action option name
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void selectMoreActionItemOfaSavedSearch(String savedSearchName, String moreActionItem)
	{
		clickMoreActionOfaSavedSearch(savedSearchName);
		By moreActionElem = By.xpath(".//*[@id='isheet_module_allSavedSearch_modal_BODY']//*[@class='dropdown open']//*[normalize-space(text())='" + moreActionItem.trim() + "']");
		findVisibleElement(moreActionElem).click();
	}

	/**
	 * Verify saved search name in Saved Searches modal
	 *
	 * @param savedSearchName
	 *        name of the saved search for verification
	 * @return true
	 *         if successful
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifySavedSearchNameInSavedSearches(String savedSearchName)
	{
		int size = driver.findElements(By.xpath(search_ViewAllSavedSearches_SavedList)).size();
		for (int i = 1; i <= size; i++)
		{
			String savedSearchXpath = "(.//*[@id='isheet_module_allSavedSearch_modal_BODY']//*[@class='edit-value'])[" + i + "]";
			if (isDisplayed(By.xpath(savedSearchXpath)))
			{
				return findVisibleElement(By.xpath(savedSearchXpath)).getText().trim().equals(savedSearchName.trim());
			}
		}
		return false;
	}

	/**
	 * Click on close button in view all saved searches modal
	 *
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void clickCloseInViewAllSavedSearchesModal()
	{
		WebElement closeElem = findVisibleElement(search_ViewAllSavedSearches_CloseButton, Speed.slow);
		closeElem.click();
		findPresentElement(search_ViewAllSavedSearchesModal);
	}

	/**
	 * Set All of these words text in Advanced Search(Advanced Search has to be opened first using "selectSearchFilterOption" method
	 *
	 * @param allOfTheseWords
	 *        text to set
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void setAllOfTheseWordsInAdvancedSearch(String allOfTheseWords)
	{
		findVisibleElement(advancedSearchModal);
		WebElement allOfTheseElem = findVisibleElement(advancedSearch_AllOfTheseWordsInput);
		allOfTheseElem.clear();
		allOfTheseElem.sendKeys(allOfTheseWords.trim());
	}

	/**
	 * Set Any of these words text in Advanced Search(Advanced Search has to be opened first using "selectSearchFilterOption" method
	 *
	 * @param anyOfTheseWords
	 *        text to set
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void setAnyOfTheseWordsInAdvancedSearch(String anyOfTheseWords)
	{
		findVisibleElement(advancedSearchModal);
		WebElement anyOfTheseElem = findVisibleElement(advancedSearch_AnyOfTheseWordsInput);
		anyOfTheseElem.clear();
		anyOfTheseElem.sendKeys(anyOfTheseWords.trim());
	}

	/**
	 * Set Exclude of these words text in Advanced Search(Advanced Search has to be opened first using "selectSearchFilterOption" method
	 *
	 * @param excludeOfTheseWords
	 *        text to set
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void setExcludeTheseWordsInAdvancedSearch(String excludeOfTheseWords)
	{
		findVisibleElement(advancedSearchModal);
		WebElement excludeTheseElem = findVisibleElement(advancedSearch_ExcludeTheseWordsInput);
		excludeTheseElem.clear();
		excludeTheseElem.sendKeys(excludeOfTheseWords.trim());
	}

	/**
	 * Click search in Advanced Search(Advanced Search has to be opened first using "selectSearchFilterOption" method
	 *
	 * @author dheeraj.rajput
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void clickSearchInAdvancedSearch()
	{
		findPresentElement(advancedSearchModal);
		WebElement searchElem = findVisibleElement(advancedSearch_SearchButton);
		searchElem.click();

	}

	/**
	 * Select choice column data
	 *
	 * @param columnName
	 *        name of the column
	 * @param data
	 *        choice to be selected
	 * @author dheeraj.rajput
	 *         Created: 16 March 2018
	 *         Updated:
	 */
	@Override
	public void selectChoiceColumnData(String columnName, String data)
	{
		By elementXpath = By.xpath("(.//*[@class='modal fade in'])[last()]//*[normalize-space(text())='" + columnName.trim() + "']//following-sibling::*//select[1]");
		Select dropDown = new Select(findVisibleElement(elementXpath));
		dropDown.selectByVisibleText(data.trim());
	}

	/**
	 * Verify column name
	 *
	 * @param headerName
	 *        name of column
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 16 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyColumnName(String headerName)
	{
		findVisibleElement(gridBox);
		By headerXpath = By.xpath("//*[@id='gridbox']//table[@class='hdr']//*[normalize-space(text())='" + headerName.trim() + "']");
		return isDisplayed(headerXpath, Speed.slow);
	}

	/**
	 * Check or uncheck 'Select All Records' checkbox.
	 *
	 * @param desiredState
	 *        Desired state of the check box
	 * @author nidhi.shah
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void setAllIsheetRecordsCheckbox(boolean desiredState)
	{
		findVisibleElement(gridBox, Speed.slow);
		if (isDisplayed(selectAllRecordsChkbox))
		{
			setSelection(selectAllRecordsChkbox, desiredState);
		}
	}

	/**
	 * Verify draft item filter options
	 *
	 * @author nidhi.shah
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyDraftItemsFilterOptions(String filterOption)
	{
		findVisibleElement(draftItemsFilterOpened, Speed.slow);
		By draftItemEle = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter' and @aria-expanded='true']//following-sibling::*[@id='sheetSavedSearchItemListID']//*[text()='" + filterOption.trim() + "']");
		return isDisplayed(draftItemEle);
	}

	/**
	 * Verify selected draft item filter option
	 *
	 * @author nidhi.shah
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifySelectedDraftItemsFilterOption(String filterOption)
	{
		findVisibleElement(draftItemsFilterOpened, Speed.slow);
		return findVisibleElement(selectedDraftItemOption).getText().equals(filterOption.trim());
	}

	/**
	 * Select any filter option
	 *
	 * @author nidhi.shah
	 *         Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public void selectDraftItemFilterOption(String filterOption)
	{
		findVisibleElement(draftItemsFilterOpened, Speed.slow);
		By draftItem = By.xpath(".//*[@id='iSheet_module_desktop_quickSearchDivID']//*[@data-original-title='Filter' and @aria-expanded='true']//following-sibling::*[@id='sheetSavedSearchItemListID']//*[text()='" + filterOption.trim() + "']");
		WebElement draftItemEle = findVisibleElement(draftItem);
		draftItemEle.click();
	}

	/**
	 * Verify given column value of given column name of all available records
	 *
	 * @author nidhi.shah
	 * @param columnName
	 * @param columnValue
	 *        Created: 14 March 2018
	 *        Updated:
	 */
	@Override
	public boolean verifyColumnValueForAllRecords(String columnName, String... columnValue)
	{
		findVisibleElement(gridBox, Speed.slow);
		List<String> columnValueList = Arrays.asList(columnValue);
		findPresentElement(gridBox, Speed.slow);
		int totalColumns = driver.findElements(isheetTable_HeaderColumns).size();
		int totalRecords = driver.findElements(totalIsheetRows).size();
		for (int j = 1; j <= totalRecords; j++)
		{
			for (int i = 1; i <= totalColumns; i++)
			{
				WebElement column = findPresentElement(By.xpath(isheetTable_HeaderNames + "[" + i + "]//*[@class='hdrcell']"));
				String columnHeader = column.getText().trim();
				if (columnHeader.equals(columnName.trim()))
				{
					String columnText = findVisibleElement(By.xpath(".//*[@id='gridbox']//*[contains(@class,'dhx_web')][" + j + "]//td[" + i + "]")).getText().trim();
					if (!columnValueList.contains(columnText))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Verify is search box is empty.
	 *
	 * @author nidhi.shah
	 * @return
	 * 		Created: 14 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyEmptySearchBox()
	{
		findVisibleElement(gridBox, Speed.slow);
		return findVisibleElement(searchInput).getText().isEmpty();
	}

	@Override
	public boolean verifySearchHintMessage()
	{
		findVisibleElement(gridBox, Speed.slow);
		return isDisplayed(searchBoxHintMessage);
	}

	@Override
	public void cancelEditRecordModal()
	{
		WebElement btnCancelEditRecordModal = findVisibleElement(locBtnCancelEditRecordModal);
		btnCancelEditRecordModal.click();
	}

	@Override
	public void selectLastIsheetRecord()
	{
		findVisibleElement(gridBox, Speed.slow);
		// int recordIndex = 1; // need to create method to get isheet record index
		findClickableElement(By.xpath("(//*[@id='gridbox']//img)[last()]")).click();
	}

	@Override
	public void enterTextInSearchBox(String itemToSearch)
	{
		if (itemToSearch.contains(" "))
		{
			String firstPart = itemToSearch.substring(0, itemToSearch.indexOf(' ')).trim();

			if (firstPart.matches("[0-9]+(\\.[0-9]+)*"))
			{
				itemToSearch = itemToSearch.substring(itemToSearch.indexOf(' ') + 1).trim();
			}
		}
		WebElement searchFileInput = findClickableElement(searchInput);
		searchFileInput.clear();
		if (itemToSearch.contains("."))
		{
			itemToSearch = itemToSearch.substring(0, itemToSearch.lastIndexOf('.')).trim();
		}
		String fileNameForSearch = itemToSearch.trim();
		searchFileInput.sendKeys(fileNameForSearch);
	}

	@Override
	public void selectOptionFromMoreOptionsOfLastIsheetRecord(String[] columnName, IsheetData isheetData, String option)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(gridBox, Speed.slow);
		// int recordIndex = 1; // need to create method to get isheet record index

		By recordMoreoptionsXpath = By.xpath("(//*[@id='gridbox']//a[@data-original-title='More actions'])[last()]");
		findVisibleElement(recordMoreoptionsXpath, Speed.slow).click();

		System.out.println("//*[@role='menu' and contains(@style,'display: block;')]//child::*[normalize-space(text())='" + option.trim() + "']");
		By optionXpath = By.xpath("//*[@role='menu' and contains(@style,'display: block;')]//child::*[normalize-space(text())='" + option.trim() + "']");
		findVisibleElement(optionXpath, Speed.slow).click();

		// findPresentElement(lastOpenedModel, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
	}

	/**
	 * Method to click on isheet more actions
	 *
	 * @author nidhi.shah
	 *         Created - 02-04-2018
	 *         Updated -
	 */
	@Override
	public void openIsheetMoreActions()
	{
		if (!isDisplayed(isheetMoreActionsOpened))
		{
			WebElement isheetMoreActionsEle = findClickableElement(isheetMoreActions, Speed.slow);
			isheetMoreActionsEle.click();
		}
		findVisibleElement(isheetMoreActionsOpened, Speed.slow);
	}

	/**
	 * @param option
	 * @author nidhi.shah
	 *         Created - 03-04-2018
	 *         Updated -
	 */
	@Override
	public void selectOptionFromIsheetMoreActions(String option)
	{
		if (!isDisplayed(isheetMoreActionsOpened))
		{
			openIsheetMoreActions();
		}
		WebElement optionEle = findVisibleElement(By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions' and @aria-expanded='true']//following-sibling::*//*[normalize-space(text())='" + option.trim() + "']"));
		optionEle.click();
	}

	/**
	 * Method to verify isheet after sharing it through mail.
	 *
	 * @param mailto
	 * @param startTime
	 * @param endTime
	 * @param emailSubject
	 * @param emailMessage
	 * @param iSheetName
	 * @author nidhi.shah
	 *         Created - 03 - 04 - 2018
	 *         Updated
	 */
	@Override
	public boolean verifyIsheetLinkMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage, String iSheetName)
	{
		String query;
		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
		{
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		else
		{
			query = "select TOP 1 * from Email where mailsubject = '" + emailSubject.trim() + "' and mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		mailContent = getMailContent(query);
		if (mailContent.isEmpty() || mailContent == null)
		{
			return false;
		}
		else
		{
			createHtmlFile(mailHtmlFile, mailContent);
			getLocalHtmlPage(mailHtmlFile);
			mailto = mailto.substring(0, mailto.indexOf("@"));
			if (verifyContent(mailto, emailMessage))
			{
				System.out.println(emailMessage + " is present");
				WebElement linkEle = findVisibleElement(By.xpath(".//a[normalize-space(text())='" + iSheetName.trim() + ":']"));
				linkEle.click();
				if (!verifyIsheetName(iSheetName.trim()))
				{
					closeCurrentTab();
					return false;
				}
				closeCurrentTab();
				return true;
			}
		}
		closeCurrentTab();
		return false;
	}

	/**
	 * Mwthid to verify isheet after sharing it through private message
	 *
	 * @param userName
	 * @param message
	 * @param iSheetName
	 * @author nidhi.shah
	 *         Created - 05-04-2018
	 *         Updated
	 */
	@Override
	public boolean verifyRecentIsheetLinkMessageRecieved(String userName, String message, String iSheetName)
	{
		openFirstMessage();
		By usernameXpath = By.xpath(lastMessageContainer + "//*[normalize-space(text())='" + getUserData(userName.trim()) + "']");
		By messageXpath = By.xpath(lastMessageContainer + "//*[normalize-space(text())='" + message.trim() + "']");
		if (isDisplayed(usernameXpath, Speed.slow) && isDisplayed(messageXpath))
		{
			findVisibleElement(usernameXpath);
			findVisibleElement(messageXpath);
			WebElement linkEle = findVisibleElement(By.xpath(lastMessageContainer + "//a"));
			linkEle.click();
			Set<String> highQCollaborateWindows = driver.getWindowHandles();
			parentWindow = (String) highQCollaborateWindows.toArray()[0];
			childWindow = (String) highQCollaborateWindows.toArray()[1];
			driver.switchTo().window(childWindow);
			if (verifyIsheetName(iSheetName.trim()))
			{
				closeCurrentTab();
				return true;
			}
			closeCurrentTab();
			return false;
		}
		else
		{
			closeCurrentTab();
			return false;
		}
	}

	/**
	 * Method to verify current iSheet name
	 *
	 * @param isheetName
	 * @return
	 * @author nidhi.shah
	 *         Created - 09 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyIsheetName(String isheetName)
	{
		WebElement isheetTitleElement = findVisibleElement(isheetsHeader_IsheetTitle);
		if (isheetTitleElement.getText().trim().equals(isheetName.trim()))
		{
			return true;
		}
		return false;
	}

	/**
	 * Method to verify Close button on Isheet Print Modal
	 *
	 * @return
	 * @author nidhi.shah
	 *         Created - 10 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyCloseButtonOnPrintModal()
	{
		return isDisplayed(closeButtonOnPrintModal, Speed.slow);
	}

	/**
	 * Method to verify Print button on Isheet Print Modal
	 *
	 * @return
	 * @author nidhi.shah
	 *         Created - 10 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyPrintButtonOnPrintModal()
	{
		return isDisplayed(printButtonOnPrintModal, Speed.slow);
	}

	/**
	 * Method to verify Isheet Name on Isheet Print Modal
	 *
	 * @param isheetName
	 * @return
	 * @author nidhi.shah
	 *         Created - 10 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyIsheetNameOnPrintModal(String isheetName)
	{
		WebElement isheetTitleElement = findVisibleElement(isheetNameOnPrintModal);
		if (isheetTitleElement.getText().trim().equals(isheetName.trim()))
		{
			return true;
		}
		return false;
	}

	/**
	 * Method to verify Isheet Column Value on Isheet Print Modal
	 *
	 * @param fieldName
	 * @param valueToVerify
	 * @return
	 * @author nidhi.shah
	 *         Created - 10 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyIsheetFieldValueOnPrintModal(String fieldName, String valueToVerify)
	{
		findVisibleElement(isheetNameOnPrintModal);
		while (isDisplayed(grayloaderBig))
		{
			;
		}
		String fetchedValue = getColumnValueOnPrintModal(fieldName);
		return fetchedValue.equals(valueToVerify.trim());
	}

	/**
	 * Method to get column value of a given column name from Print Modal
	 *
	 * @param columnName
	 * @return
	 * @author nidhi.shah
	 *         Created - 11 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public String getColumnValueOnPrintModal(String columnName)
	{
		findVisibleElement(isheetTableOnPrintModal, Speed.slow);
		int size = driver.findElements(isheetHeadersOnPrintModal).size();
		for (int i = 1; i <= size; i++)
		{
			WebElement column = findPresentElement(By.xpath(".//*[@class='printPreviewSheetHead']//th" + "[" + i + "]"));
			String columnHeader = column.getText().trim();
			if (columnHeader.equals(columnName.trim()))
			{
				String columnText = findVisibleElement(By.xpath("(.//*[@class='printPreview_grid_even']//td[" + i + "])[1][last()]")).getText().trim();
				return columnText;
			}
		}
		return "";
	}

	/**
	 * Method to click on Print Button on print modal
	 *
	 * @author nidhi.shah
	 *         Created - 11 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public void clickPrintButtonOnPrintModal()
	{
		WebElement elemenrt = findPresentElement(printButtonOnPrintModal);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", elemenrt);
	}

	/**
	 * Method to verify Print preview modal
	 *
	 * @author nidhi.shah
	 *         Created - 11 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyPrintPreviewWindow()
	{
		return isDisplayed(printPreviewWindow, Speed.slow);
	}

	/**
	 * Method to wait till file gets downloaded
	 *
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public void waitForFileGettingDownloaded()
	{
		if (isDisplayed(downloading, Speed.slow))
		{
			while (isDisplayed(downloading))
			{
				;
			}
		}
	}

	/**
	 * Method to verify Export option on export Isheet Modal
	 *
	 * @param exportOption
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyExportOption(String exportOption)
	{
		findVisibleElement(exportIsheetModalOpened, Speed.slow);
		return isDisplayed(By.xpath("//*[@id='ishhet_module_export_MODAL' and not(contains(@style,'display: none'))]//*[normalize-space(.)='" + exportOption.trim() + "']"));
	}

	/**
	 * Method to click any Export option on export Isheet Modal
	 *
	 * @param exportOption
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public void selectExportOption(String exportOption)
	{
		findVisibleElement(exportIsheetModalOpened, Speed.slow);
		WebElement exportOptionElement = findVisibleElement(By.xpath("//*[@id='ishhet_module_export_MODAL' and not(contains(@style,'display: none'))]//*[normalize-space(.)='" + exportOption.trim() + "']//input"));
		exportOptionElement.click();
	}

	/**
	 * Method to click Export button on export Isheet Modal
	 *
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public void clickExportOnExportIsheetModal()
	{
		findVisibleElement(exportIsheetModalOpened, Speed.slow);
		WebElement exportButton = findVisibleElement(exportIsheetModalExportButton);
		exportButton.click();
		findPresentElement(exportIsheetModalClosed, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
	}

	/**
	 * Method to click Cancel button on export Isheet Modal
	 *
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public void clickCancelOnExportIsheetModal()
	{
		findVisibleElement(exportIsheetModalOpened, Speed.slow);
		WebElement cancelButton = findVisibleElement(exportIsheetModalCancelButton);
		cancelButton.click();
		findPresentElement(exportIsheetModalClosed, Speed.slow);
	}

	/**
	 * Method to verify 'Browse' button on Import Isheet modal
	 *
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyBrowseButtonOnImportModal()
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow);
		return isDisplayed(importIsheetModalBrowseButton, Speed.slow);
	}

	/**
	 * Method to verify any Option of 'More Actions' of Isheet.
	 *
	 * @param option
	 * @author nidhi.shah
	 *         Created - 16 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyIsheetMoreActionOptions(String option)
	{
		if (!isDisplayed(isheetMoreActionsOpened))
		{
			openIsheetMoreActions();
		}
		return isDisplayed(By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions' and @aria-expanded='true']//following-sibling::*//*[normalize-space(text())='" + option.trim() + "']"));
	}

	/**
	 * Method to verify any Option of 'More Actions' of Isheet Record.
	 *
	 * @param columnName
	 * @param isheetData
	 * @param option
	 * @author nidhi.shah
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyOptionFromMoreOptionsOfIsheetRecord(String[] columnName, IsheetData isheetData, String option)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(gridBox, Speed.slow);
		int recordIndex = 1; // need to create method to get isheet record index

		By recordMoreoptionsXpath = By.xpath("(//*[@id='gridbox']//a[@data-original-title='More actions'])[" + recordIndex + "]");
		findVisibleElement(recordMoreoptionsXpath, Speed.slow).click();

		return isDisplayed(By.xpath("//*[@role='menu' and contains(@style,'display: block;')]//child::*[normalize-space(text())='" + option.trim() + "']"));
	}

	/**
	 * Method to verify given tab in Audit History Modal
	 *
	 * @param tab
	 * @author nidhi.shah
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyTabInAuditHistoryModal(String tab)
	{
		findVisibleElement(isheetAuditHistoryModalOpened, Speed.slow);
		return isDisplayed(By.xpath(".//*[@id='isheetItem_auditHistory_modal' and contains(@style,'display: block')]//*[text()='" + tab.trim() + "']"));
	}

	/**
	 * Method to verify Close button on Audit History Modal
	 *
	 * @author nidhi.shah
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyCloseButtonOnAuditHistoryModal()
	{
		return isDisplayed(isheetAuditHistoryModalCloseButton, Speed.slow);
	}

	/**
	 * Method to verify Compare button on Audit History Modal
	 *
	 * @author nidhi.shah
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyCompareButtonOnAuditHistoryModal()
	{
		return isDisplayed(isheetAuditHistoryModalCompareButton, Speed.slow);
	}

	/**
	 * Method to verify headers versions table under the 'Versions' tab of Audit History Table
	 *
	 * @author nidhi.shah
	 * @return
	 * 		Created - 18 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyAuditHistoryVersionsTableHeaders()
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		String[] headers = {"Version", "Date", "Author"};
		List<WebElement> eleHeaders = driver.findElements(isheetAuditHistoryVersionsTableHeaders);
		int j = 0;
		for (int i = 0; i < eleHeaders.size(); i++)
		{
			if (!eleHeaders.get(i).getText().equals(""))
			{
				if (!eleHeaders.get(i).getText().equals(headers[j]))
				{
					return false;
				}
				j++;
			}
		}
		return true;
	}

	/**
	 * Method to verify Check-boxes of all records in Audit History Table
	 *
	 * @author nidhi.shah
	 * @return
	 * 		Created - 18 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyCheckboxForAllRecordsInAuditHistoryTable()
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		List<WebElement> eleRows = driver.findElements(isheetAuditHistoryVersionsTableRows);
		for (int i = 2; i < eleRows.size(); i++)
		{
			if (!isDisplayed(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@type='checkbox']")))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to verify More Actions of all records of Audit History Table
	 *
	 * @author nidhi.shah
	 * @return
	 * 		Created - 18 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifyMoreActionsForAllRecordsInAuditHistoryTable()
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		List<WebElement> eleRows = driver.findElements(isheetAuditHistoryVersionsTableRows);
		for (int i = 2; i < eleRows.size(); i++)
		{
			if (!isDisplayed(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@data-original-title='More actions']")))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to verify given option of More Actions of Current Version
	 *
	 * @author nidhi.shah
	 * @return
	 * @param option
	 *        Created - 20-04-2018
	 *        Modified -
	 */
	@Override
	public boolean verifyMoreActionOptionsOfCurrentVersion(String option)
	{
		if (!isDisplayed(currentVersionMoreActionOpened))
		{
			findVisibleElement(currentVersionMoreActionButton).click();
		}
		if (!isDisplayed(By.xpath(".//*[contains(@class,'copyDropdown') and  contains(@style,'display: block')]//*[text()='" + option.trim() + "']")))
		{
			findVisibleElement(currentVersionMoreActionButton).click();
			return false;
		}
		findVisibleElement(currentVersionMoreActionButton).click();
		return true;
	}

	/**
	 * Method to verify given option of More Actions of All versions
	 *
	 * @author nidhi.shah
	 * @return
	 * @param option
	 *        Created - 20-04-2018
	 *        Modified -
	 */
	@Override
	public boolean verifyMoreActionOptionsOfAllVersions(String option)
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		List<WebElement> eleRows = driver.findElements(isheetAuditHistoryVersionsTableRows);
		for (int i = 3; i < eleRows.size(); i++)
		{
			findVisibleElement(isheetAuditHistoryModalOpened).click();
			if (!isDisplayed(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@data-original-title='More actions' and @aria-expanded='true']")))
			{
				findVisibleElement(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@data-original-title='More actions']")).click();
			}
			if (!isDisplayed(By.xpath(".//*[contains(@class,'copyDropdown') and  contains(@style,'display: block')]//*[text()='" + option.trim() + "']")))
			{
				findVisibleElement(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@data-original-title='More actions']")).click();
				return false;
			}
			findVisibleElement(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//tr[" + i + "]//*[@data-original-title='More actions']")).click();
		}
		return true;
	}

	/**
	 * Method to Click on given option of More Actions of Current Version
	 *
	 * @author nidhi.shah
	 * @param option
	 *        Created - 20-04-2018
	 *        Modified -
	 */
	@Override
	public void clickMoreActionOptionOnCurrentVersion(String option)
	{
		findVisibleElement(isheetAuditHistoryModalOpened).click();
		if (!isDisplayed(currentVersionMoreActionOpened))
		{
			findVisibleElement(currentVersionMoreActionButton).click();
		}
		findVisibleElement(By.xpath(".//*[contains(@class,'copyDropdown') and  contains(@style,'display: block')]//*[text()='" + option.trim() + "']")).click();
		while (isDisplayed(grayLoader))
		{
			;
		}
	}

	/**
	 * Method to Click on given option of More Actions of given Version
	 *
	 * @author nidhi.shah
	 * @param option
	 * @param versionNumber
	 *        Created - 20-04-2018
	 *        Modified -
	 */
	@Override
	public void clickMoreActionOptionOnGivenVersion(String versionNumber, String option)
	{
		if (!isDisplayed(By.xpath("(.//*[@id='isheet_module_auditOnScoll_VERSION']//td[text()='" + versionNumber.trim() + "']//following::*[@data-original-title='More actions' and @aria-expanded='true'])[1]")))
		{
			findVisibleElement(By.xpath("(.//*[@id='isheet_module_auditOnScoll_VERSION']//td[text()='" + versionNumber.trim() + "']//following::*[@data-original-title='More actions'])[1]")).click();
		}
		findVisibleElement(By.xpath(".//*[contains(@class,'copyDropdown') and  contains(@style,'display: block')]//*[text()='" + option.trim() + "']")).click();
	}

	/**
	 * Method to Click cancel on Audit History Modal
	 *
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public void clickCloseButtonOnAuditHistoryModal()
	{
		WebElement cancelEle = findVisibleElement(isheetAuditHistoryModalCloseButton, Speed.slow);
		cancelEle.click();
	}

	/**
	 * Method to compare given two isheets versions
	 *
	 * @author nidhi.shah
	 * @param option
	 * @param versionNumber
	 *        Created - 20-04-2018
	 *        Modified -
	 */
	@Override
	public void compareIsheetVersions(String... versions)
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		for (String version : versions)
		{
			if (version != null)
			{
				setSelection(By.xpath(".//*[@id='isheet_module_auditOnScoll_VERSION']//td[text()='" + version.trim() + "']//preceding-sibling::*//*[@type='checkbox']"), true);

			}
		}
		clickCompareButtonOnAuditHistoryModal();
	}

	/**
	 * Method to click on compare button on Audit History Modal
	 *
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public void clickCompareButtonOnAuditHistoryModal()
	{
		WebElement compareButton = findVisibleElement(isheetAuditHistoryModalCompareButton);
		if (compareButton.isEnabled())
		{
			findVisibleElement(isheetAuditHistoryModalCompareButton).click();
		}
		else
		{
			// Error Message regarding 'Select atleast 2 versions' of Isheet.
		}
	}

	/**
	 * Method to verify headers of Audit table under the 'Audits' Tab of Audit History Table
	 *
	 * @return
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public boolean verifyAuditHistoryAuditsTableHeaders()
	{
		findVisibleElement(isheetAuditHistoryModalOpened);
		String[] headers = {IsheetLabels.ISHEET_RECORD_AUDITTABLE_HEADERS_NAME, IsheetLabels.ISHEET_RECORD_AUDITTABLE_HEADERS_ACTION, IsheetLabels.ISHEET_RECORD_AUDITTABLE_HEADERS_DATE, IsheetLabels.ISHEET_RECORD_AUDITTABLE_HEADERS_IPADDRESS};
		List<WebElement> eleHeaders = driver.findElements(isheetAuditHistoryAuditTableHeaders);
		int j = 0;
		for (int i = 0; i < eleHeaders.size(); i++)
		{
			if (!eleHeaders.get(i).getText().equals(""))
			{
				if (!eleHeaders.get(i).getText().equals(headers[j]))
				{
					return false;
				}
				j++;
			}
		}
		return true;
	}

	/**
	 * Method to verify 'Created Records' Link in Audit History Modal
	 *
	 * @return
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public boolean verifyCreatedRecordsLink()
	{
		return isDisplayed(createdRecordLink);
	}

	/**
	 * Method to verify 'Modified Records' Link of all records in Audit History Modal
	 *
	 * @return
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public boolean verifyModifiedRecordsLink()
	{
		findVisibleElement(auditRecordTable).click();
		List<WebElement> auditRaws = driver.findElements(auditTableRaws);
		for (int i = 2; i < auditRaws.size(); i++)
		{
			if (!findVisibleElement(By.xpath(".//*[@id='isheete_module_Audit_Tbody']//tr[" + i + "]//td[2]")).getText().equals(IsheetLabels.ISHEET_RECORD_AUDITTABLE_ACTIONS_MODIFIEDRECORDS))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to click on 'Created Record' Link
	 *
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public void clickCreatedRecordsLink()
	{
		WebElement createdRecordLinkEle = findVisibleElement(createdRecordLink);
		createdRecordLinkEle.click();
	}

	/**
	 * Method to click on 'Modified Record' link of first modified record.
	 *
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public void clickFirstModifiedRecord()
	{
		WebElement modifiedRecordsLinkEle = findVisibleElement(firstModifiedRecord);
		modifiedRecordsLinkEle.click();
	}

	/**
	 * Method to click on given tab in Audit History Modal
	 *
	 * @author nidhi.shah
	 *         Created - 20-04-2018
	 *         Modified -
	 */
	@Override
	public void clickTabInAuditHistoryModal(String tab)
	{
		findVisibleElement(isheetAuditHistoryModalOpened, Speed.slow);
		findVisibleElement(By.xpath(".//*[@id='isheetItem_auditHistory_modal' and contains(@style,'display: block')]//*[text()='" + tab.trim() + "']")).click();
		;
	}

	/**
	 * Method to compare current and previous String of isheet column type 'Single Line Text Data'
	 *
	 * @param previousString
	 * @param currentString
	 * @param columnName
	 * @return
	 * @author nidhi.shah
	 *         Created - 23-04-2018
	 *         Modified -
	 */
	@Override
	public boolean compareSingleLineTextData(String previousString, String currentString, String columnName)
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(isheetCompareModalOpened, Speed.slow);
		String diff = StringUtils.difference(previousString, currentString);
		String colorCode = null;
		if (diff != null)
		{
			if (currentString.equals(diff))
			{
				if (previousString != null)
				{
					String removedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'removed') and normalize-space(text())='" + previousString.trim() + "'])[1]")).getCssValue("background-color");
					String addedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'added') and normalize-space(text())='" + diff.trim() + "'])[1]")).getCssValue("background-color");
					return removedColor.trim().equals(compareColorCode_red) && addedColor.equals(compareColorCode_green);
				}
				else
				{
					String addedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'added') and normalize-space(text())='" + diff.trim() + "'])[1]")).getCssValue("background-color");
					return addedColor.trim().equals(compareColorCode_green);
				}
			}
			else
			{
				String diffR = StringUtils.difference(currentString, previousString);
				if (diffR != null)
				{
					String removedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'removed') and normalize-space(text())='" + diffR.trim() + "'])[1]")).getCssValue("background-color");
					String addedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'added') and normalize-space(text())='" + diff.trim() + "'])[1]")).getCssValue("background-color");
					return removedColor.trim().equals(compareColorCode_red) && addedColor.trim().equals(compareColorCode_green);
				}
				else
				{
					String removedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'removed') and normalize-space(text())='" + diffR.trim() + "'])[1]")).getCssValue("background-color");
					return removedColor.trim().equals(compareColorCode_red);
				}
			}
		}
		else
		{
			if (previousString != null && currentString == null)
			{
				String removedColor = findVisibleElement(By.xpath("((.//*[@class='modal fade in'])[last()]//following::label[contains(normalize-space(.),'" + columnName.trim() + "')]//following::*[contains(@id,'removed') and normalize-space(text())='" + previousString.trim() + "'])[1]")).getCssValue("background-color");
				return removedColor.trim().equals(compareColorCode_red);
			}
			else if (previousString.trim().equals(currentString))
			{
				return true;
			}

		}
		return false;
	}

	/**
	 * Method to choose template from 'Document templates' Modal.
	 *
	 * @param template
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void chooseTemplateToGenerateDocument(String template)
	{
		findVisibleElement(docuemntTemplatesModalOpened, Speed.slow);
		WebElement templateEle = findVisibleElement(By.xpath(".//*[@id='ishhet_module_generateReport_MODAL']//*[normalize-space()='" + template.trim() + "']//*[@type='radio']"));
		templateEle.click();
	}

	/**
	 * Click on Generate Button on 'Document templates' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void clickGenerateButton()
	{
		WebElement generateButton = findVisibleElement(documentTemplateGenerateButton);
		generateButton.click();
	}

	/**
	 * Stop execution till documents get generated.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void waitTillDocumentGetsGenerated()
	{
		while (isDisplayed(documentGenerationGlobalMessage))
		{
			;
		}
		findVisibleElement(documentGeneratedModalOpened, Speed.slow);
	}

	/**
	 * Verify header of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyHeaderOfDocumentGeneratedModal()
	{
		WebElement titleEle = findVisibleElement(documentGeneratedModalTitle);
		return titleEle.getText().equals("Document generated");
	}

	/**
	 * Verify text of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyTextOfDocumentGeneratedModal()
	{
		WebElement textEle = findClickableElement(documentGeneratedModalText);
		return textEle.getText().equals("You can optionally download or save the document to a site folder:");
	}

	/**
	 * Verify "Download" button of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyDownloadButtonOnDocumentGeneratedModal()
	{
		return isDisplayed(documentGeneratedModalDownloadButton, Speed.slow);
	}

	/**
	 * Verify "Save to folder" button of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifySaveButtonOnDocumentGeneratedModal()
	{
		return isDisplayed(documentGeneratedModalSaveFolderButton, Speed.slow);
	}

	/**
	 * Verify "Close" button of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyCloseButtonOnDocumentGeneratedModal()
	{
		return isDisplayed(documentGeneratedModalCloseButton, Speed.slow);

	}

	/**
	 * Verify message of 'Document generated' Modal.
	 *
	 * @param message
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyMessageOfDocumentGeneratedModal(String message)
	{
		WebElement messageEle = findVisibleElement(documentGeneratedModalMessage);
		return messageEle.getText().equals(message.trim());
	}

	/**
	 * Verify option of Isheet More Action.
	 *
	 * @param option
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyOptionFromIsheetMoreActions(String option)
	{
		if (!isDisplayed(isheetMoreActionsOpened))
		{
			openIsheetMoreActions();
		}
		return isDisplayed(By.xpath(".//*[@class='pull-right']//*[@data-original-title='More actions' and @aria-expanded='true']//following-sibling::*//*[normalize-space(text())='" + option.trim() + "']"));
	}

	/**
	 * Click 'Document' Link of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void clickDocumentLinkOnDocumentGeneratedModalMessage()
	{
		WebElement documentLink = findVisibleElement(documentGeneratedModalMessageDocumentLink);
		documentLink.click();
	}

	/**
	 * Click 'Specified Folder' Link of 'Document generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public DocumentWeb clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage()
	{
	findVisibleElement(documentGeneratedModalOpened, Speed.slow);
		WebElement folderLink = findVisibleElement(documentGeneratedModalMessageSpecifiedFolderLink, Speed.slow);
		folderLink.click();
		return new DocumentWeb(driver);
	}

	/**
	 * Verify document template name in file preview window
	 *
	 * @param fileName
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyDocumentTemplateInFilePreviewWindow(String fileName)
	{
		findVisibleElement(filePreviewWindowOpened, Speed.slow);
		Pattern templateFileName = Pattern.compile(fileName.split("\\.")[0] + " +[0-3][0-9]-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9] +at +[0-2][0-9].[0-5][0-9].[0-5][0-9]" + "." + fileName.split("\\.")[1]);
		return templateFileName.matcher(findVisibleElement(fileNameInfilePreviewWindow).getText()).matches();
	}

	/**
	 * Click Download on file preview window
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void clickDownloadOnFilePreviewPage()
	{
		WebElement downloadEle = findVisibleElement(downloadButtonOnFileViewer);
		downloadEle.click();
	}

	/**
	 * Click Close on file preview window
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void closeFilePreviewWindow()
	{
		WebElement closeButton = findVisibleElement(fileViewerCloseButton);
		closeButton.click();
		findPresentElement(filePreviewWindowClosed, Speed.slow);
	}

	/**
	 * Verify downloaded document template file.
	 *
	 * @param templateFileName
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyDownloadedDocumentTemplate(String templateFileName)
	{
		try
		{
			findPresentElement(breadCrumb, Speed.slow);
			String folderPath = new File(TestBaseSetup.currentDir + "\\Downloads\\").getCanonicalPath().trim();
			File latestFile = getLatestFilefromDir(folderPath);
			System.out.println("Last modified file from Downloads: " + latestFile.getName().trim());
			Pattern templateFileNamePattern = Pattern.compile(templateFileName.split("\\.")[0] + " +[0-3][0-9]-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9] +at +[0-2][0-9].[0-5][0-9].[0-5][0-9]" + "." + templateFileName.split("\\.")[1]);
			return templateFileNamePattern.matcher(latestFile.getName().trim()).matches();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Click 'Download' on 'Document Generated' Window
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void clickDownloadOnDocumentGeneratedModal()
	{
		findVisibleElement(documentGeneratedModalOpened, Speed.slow).click();
		WebElement documentGeneratedModalDownloadButtonEle = findVisibleElement(documentGeneratedModalDownloadButton);
		documentGeneratedModalDownloadButtonEle.click();
	}

	/**
	 * Click 'Save to folder' on 'Document Generated' Window
	 *
	 * @author nidhi.shah
	 * @Created - 30-04-2018
	 * @Modified -
	 */
	@Override
	public void clickSaveFolderOnDocumentGeneratedModal()
	{
		findVisibleElement(documentGeneratedModalOpened, Speed.slow).click();
		WebElement documentGeneratedModalSaveFolderButtonEle = findVisibleElement(documentGeneratedModalSaveFolderButton);
		documentGeneratedModalSaveFolderButtonEle.click();
	}

	/**
	 * Verify Header of 'Save to site folder' modal.
	 *
	 * @param headerName
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyHeaderOfSaveToSiteFolderModal(String headerName)
	{
		findVisibleElement(saveToSiteFolderModalOpened, Speed.slow);
		Pattern headerPattern = Pattern.compile("Save to site folder - " + headerName.split("\\.")[0] + " +[0-3][0-9]-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9] +at +[0-2][0-9].[0-5][0-9].[0-5][0-9]");
		WebElement headerEle = findVisibleElement(saveToSiteFolderModalTitle);
		return headerPattern.matcher(headerEle.getText()).matches();
	}

	/**
	 * Verify label 'Site:' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifySiteLabelOnSaveToSiteFolderModal()
	{
		return isDisplayed(saveToSiteFolderModalSiteLabel, Speed.slow);
	}

	/**
	 * Verify site dropdown on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyDropDownOnSaveToSiteFolderModal()
	{
		return isDisplayed(saveToSiteFolderModalSiteDropDown, Speed.slow);
	}

	/**
	 * Verify default site name on site dropdown on 'Save to site folder' modal
	 *
	 * @param siteName
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyDefaultSiteOnSaveToSiteFolderModal(String siteName)
	{
		Select siteDropDown = new Select(findVisibleElement(saveToSiteFolderModalSiteDropDown));
		return siteDropDown.getFirstSelectedOption().getText().equals(siteName.trim());
	}

	/**
	 * Verify label 'Choose a folder' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyChooseFolderLabelOnSaveToSiteFolderModal()
	{
		return isDisplayed(saveToSiteFolderModalChooseFolderLabel, Speed.slow);
	}

	/**
	 * Verify link 'Expand All' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyExpandAllOnSaveToSiteFolderModal()
	{
		return isDisplayed(saveToSiteFolderModalExpandAllLink, Speed.slow);
	}

	/**
	 * Verify link 'Collapse All' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyCollapseAllOnSaveToSiteFolderModal()
	{
		return isDisplayed(saveToSiteFolderModalCollapseAllLink, Speed.slow);
	}

	/**
	 * Verify 'Save to site folder' modal is opened
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifySaveToSiteFolderModalOpened()
	{
		while (isDisplayed(grayLoader))
		{
			;
		}
		return isDisplayed(saveToSiteFolderModalOpened, Speed.slow);
	}

	/**
	 * Click Cancel on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public void clickCancleOnSaveToSiteFolderModal()
	{
		WebElement cancleEle = findVisibleElement(saveToSiteFolderModalCancelButton);
		cancleEle.click();
	}

	/**
	 * Click Close on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public void clickCloseOnSaveToSiteFolderModal()
	{
		WebElement closeEle = findVisibleElement(saveToSiteFolderModalCloseButton);
		closeEle.click();
	}

	/**
	 * Click Save on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public void clickSaveOnSaveToSiteFolderModal()
	{
		WebElement saveEle = findVisibleElement(saveToSiteFolderModalSaveButton);
		saveEle.click();
	}

	/**
	 * Select folder(Location) on 'Save to site folder' modal
	 *
	 * @paramlocation
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public void setSiteFolderLocationToSaveDocument(String location)
	{
		By locfile = By.xpath("(.//*[@id='file_module_DDReport_modal']//*[normalize-space(text())='" + location.trim() + "'])[1]//preceding::*[@class='fancytree-checkbox'][1]");
		setSelection(locfile, true);
	}

	/**
	 * Click 'iSheet record' in message on 'Document Generated' Modal.
	 *
	 * @author nidhi.shah
	 * @Created - 01-05-2018
	 * @Modified -
	 */
	@Override
	public void clickIsheetRecordLinkOnDocumentGeneratedModalMessage()
	{
		WebElement isheetRecordLink = findVisibleElement(documentGeneratedModalMessageIsheetRecordLink);
		isheetRecordLink.click();
	}

	/**
	 * Verify attached Document Template in Attachment column of the isheet record.
	 *
	 * @param columnName
	 * @param documentTemplateName
	 * @author nidhi.shah
	 * @Created - 02-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyAttachmentRecordForDocumentTemplate(String columnName, String documentTemplateName)
	{
		findVisibleElement(locModalViewIsheetRecordOpened, Speed.slow);
		findVisibleElement(By.xpath("//*[@id='isheet_module_viewItem_modal']//*[normalize-space(text())='" + columnName.trim() + "']"), Speed.slow);
		By columnRecord = By.xpath("//*[@id='isheet_module_viewItem_modal']//*[@class='row']//following::*[contains(normalize-space(.),'" + columnName.trim() + "')]//child::span//a[contains(text(),'.')]");
		List<WebElement> attachmentList = driver.findElements(columnRecord);
		List<String> attachments = new ArrayList<String>();
		for (WebElement fileLink : attachmentList)
		{
			attachments.add(fileLink.getText());
		}
		Pattern templateFileNamePattern = Pattern.compile(documentTemplateName.split("\\.")[0] + " +[0-3][0-9]-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9] +at +[0-2][0-9].[0-5][0-9].[0-5][0-9]" + "." + documentTemplateName.split("\\.")[1]);
		boolean flag = false;
		for (int i = 0; i < attachments.size(); i++)
		{
			if (templateFileNamePattern.matcher(attachments.get(i)).matches())
			{
				flag = true;
			}
		}
		if (flag)
		{
			return true;
		}
		return false;
	}

	/**
	 * Verify if fancy tree is expanded or not
	 *
	 * @return true -> If tree is expanded | false -> if tree is collapsed
	 * @author nidhi.shah
	 * @Created - 08-05-2018
	 * @Modified -
	 */
	@Override
	public boolean verifyExpandedTree()
	{
		return isDisplayed(expandedTree, Speed.slow);
	}

	/**
	 * Click 'Expand All' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 08-05-2018
	 * @Modified -
	 */
	@Override
	public void clickExpandAllOnSaveToSiteFolderModal()
	{
		findVisibleElement(saveToSiteFolderModalExpandAllLink, Speed.slow).click();
	}

	/**
	 * Click 'Collapse All' on 'Save to site folder' modal
	 *
	 * @author nidhi.shah
	 * @Created - 08-05-2018
	 * @Modified -
	 */
	@Override
	public void clickCollapseAllOnSaveToSiteFolderModal()
	{
		findVisibleElement(saveToSiteFolderModalCollapseAllLink, Speed.slow).click();
		;
	}

	/**
	 * Get the count of total records of isheet
	 *
	 * @author nidhi.shah
	 * @Created - 15-05-2018
	 * @Modified -
	 */
	@Override
	public int getRecordCountOfIsheet()
	{
		findVisibleElement(isheetGrid, Speed.slow);
		List<WebElement> recordsList = driver.findElements(recordCountOfIsheet);
		return recordsList.size();
	}
}
