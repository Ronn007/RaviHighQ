package com.highq.pageobjects.base;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.highq.pagedata.IsheetData;
import com.highq.pageobjects.pages.DocumentWeb;

public interface IsheetsPage extends BannerPage
{
	public void searchIsheetItem(String itemToSearch);

	public void clearIsheetsSearchBox();

	public void selectIsheetFromHeader(String isheetName);

	public String getColumnValue(String columnName);

	public boolean verifyIsheetFieldValue(String fieldName, String valueToVerify);

	public void clickAdd();

	public void selectIsheetActions(String isheetAction);

	public void addSingleLineColumnData(String columnName, String data);

	public void addMultipleLineColumnData(String columnName, String data);

	public void addNumberColumnData(String columnName, String value);

	public void addDateAndTimeColumnData(String columnName, String date);

	public void addUserLookupColumnData(String columnName, String... users);

	public void addHyperlinkColumnData(String columnName, String displayName, String url);

	public void addImageColumnData(String columnName, String imageFile);

	public void clickOnPreviewImage(String columnName);

	public void addAttachmentColumnData(String columnName, String... attachments);

	public void addFileLinkCoumnData(String columnName, String[] files);

	public void clickAddOnSelectFiles();

	public void addFolderLinkColumnData(String columnName, String[] folderNames);

	public void clickSaveOnSelectFolders();

	public void addIsheetLinkColumnData(String columnName, String isheetName, Map<List<String>, Boolean> columnData);

	public void addIsheetLinkColumnData(String columnName, String isheetName, List<List<String>> columnData);

	public void selectIsheetForIsheetLink(String isheetName);

	public void clickInsertOnAddIsheetLinkRecord();

	public void addLookupColumnData(String columnName, Map<List<String>, Boolean> columnData);

	public void clickInsertOnAddLookup();

	public void selectOptionFromUserLookup(String option);

	public void clickAddOnAddRecordModal();

	public void addRecord(String[] columnName, IsheetData isheetData);

	public boolean verifySingleLineTextRecord(String columnName, String columnData);

	public boolean verifyMultiLineTextRecord(String columnName, String columnData);

	public boolean verifyNumberRecord(String columnName, String columnData);

	public boolean verifyDateAndTimeRecord(String columnName, String columnData);

	public boolean verifyUserLookupRecord(String columnName, String... usersData);

	public boolean verifyHyperlinkRecord(String columnName, String linkDisplayName, String linkURL);

	public boolean verifyFileLinkRecord(String columnName, String[] fileNames);

	public boolean verifyFolderLinkRecord(String columnName, String[] folderNames);

	public boolean verifyImageColumnRecord(String columnName, String imageData);

	public boolean verifyIsheetLinkRecord(String columnName, String isheetName, List<List<String>> isheetLinkData);

	// public boolean verifyLookUpColumnRecords(Map<List<String>, Boolean> lookupColumnData) ;
	public boolean verifyLookUpColumnRecords(Map<List<String>, Boolean> lookupColumnRecords);

	public boolean verifyAttachmentRecord(String columnName, String[] fileNames);

	public boolean verifyIsheetRecord(String[] columnName, IsheetData isheetData);

	public void editSingleLineColumnData(String columnName, String data);

	public void editMultipleLineColumnData(String columnName, String data);

	public void editNumberColumnData(String columnName, String value);

	public void editDateAndTimeColumnData(String columnName, String date);

	public void editUserLookupColumnData(String columnName, String[] addUsers, Map<String, String[]> removeUsers);

	public void editHyperlinkColumnData(String columnName, String displayName, String url);

	public void editImageColumnData(String columnName, String imageFile);

	public void editAttachmentColumnData(String columnName, String... addAttachments);

	public void editFileLinkColumn(String columnName, String[] addFiles);

	public void editFolderLinkColumn(String columnName, String[] addFolders);

	public void editIsheetLinkColumn(String columnName, String isheetName, List<List<String>> columnData);

	public void editlookupcolumnData(String columnName, Map<List<String>, Boolean> columnData);

	public void editRecord(String[] columnName, IsheetData isheetData);

	public boolean removeUserFromUserLookup(Map<String, String[]> userLookUpData);

	public boolean removeImage(String... imageColumns);

	public boolean removeAttachmetns(Map<String, String[]> attachmentData);

	public boolean removeFilesFromFileLinkColumn(Map<String, String[]> fileLinkColumn);

	public boolean addFilesToFileLinkColumn(Map<String, String[]> fileLinkColumn);

	public boolean removeFoldersFromFolderLinkColumn(Map<String, String[]> folders);

	public void removeIsheetLinkData(List<List<String>> removeData);

	public void removelookupColumnData(String columnName, Map<List<String>, Boolean> columnData);

	public void closeViewRecordModal();

	public void saveEditedIseetRecord();

	public boolean verifyCustomModalMessage(String title, String message);

	public boolean verifyCancelButtonOnCustomModal(String title);

	public boolean verifyOKButtonOnCustomModal(String title);

	public void cancelCustomModal(String title);

	public void closeCustomModal(String title);

	public boolean verifyCustomModalClosed(String title);

	public void clickOKOnCustomModal(String title);

	public boolean verifyGlobalDeletionMessage(String globalMessage);

	public void cancelGolbalMessage();

	public void selectMoreActionOptionOnViewModal(String option);

	public void setAddAnotherRecordChkBox(boolean desiredState);

	public boolean verifyAddEditRecordGlobalMessage(String golbalMessage);

	public boolean verifyAddRecordModalOpened();

	public boolean verifyAddRecordModalClosed();

	public void verifyBlankColumn(String[] columnName);

	public void setEditAnotherRecordCheckBox(boolean desiredState);

	public boolean verifyEditRecordModalOpened();

	public boolean verifyEditRecordModalClosed();

	public boolean verifyFavouriteIconIsSelected();

	public void addIsheetToFavourites();

	public void removeIsheetFromFavourites();

	public void selectOptionFromMoreOptionsOfIsheetRecord(String[] columnName, IsheetData isheetData, String option);

	public void saveIsheetRecordAsDraft();

	public boolean verifyResumeEditMessage();

	public void clickResume();

	public void clickDiscard();

	public void selectIsheetRecord();

	public boolean verifyDraftItemOptionVisibility();

	public void selectIsheetDraftItems();

	public void selectIsheetView(String viewName);

	public boolean verifyCurrentViewName(String viewName);

	public boolean verifyIsheetRecordsEmpty();

	public void waitTillAutoSaveMessage();

	public void clickCancelOnAddRecordModal();

	public boolean verifyNoDataIsPresent();

	public boolean verifySearchResultContainsSearchText(String searchText);

	public void clickSaveSearch();

	public void setNameInSaveSearch(String name);

	public void clickSaveInSaveSearchModal();

	public void expandSearchFilter();

	@Override
	public void selectSearchFilterOption(String filterOption);

	public void clickMoreActionOfaSavedSearch(String savedSearchName);

	public void selectMoreActionItemOfaSavedSearch(String savedSearchName, String moreActionItem);

	public boolean verifySavedSearchNameInSavedSearches(String savedSearchName);

	public void clickCloseInViewAllSavedSearchesModal();

	public void setAllOfTheseWordsInAdvancedSearch(String allOfTheseWords);

	public void setAnyOfTheseWordsInAdvancedSearch(String anyOfTheseWords);

	public void setExcludeTheseWordsInAdvancedSearch(String excludeOfTheseWords);

	public void clickSearchInAdvancedSearch();

	public void selectChoiceColumnData(String columnName, String data);

	public boolean verifyColumnName(String headerName);

	public void setAllIsheetRecordsCheckbox(boolean desiredState);

	public boolean verifyDraftItemsFilterOptions(String filterOption);

	public boolean verifySelectedDraftItemsFilterOption(String filterOption);

	public void selectDraftItemFilterOption(String filterOption);

	public boolean verifyColumnValueForAllRecords(String columnName, String... columnValue);

	public boolean verifyEmptySearchBox();

	public boolean verifySearchHintMessage();

	public void selectLastIsheetRecord();

	public void enterTextInSearchBox(String itemToSearch);

	public void selectOptionFromMoreOptionsOfLastIsheetRecord(String[] columnName, IsheetData isheetData, String option);

	public void openIsheetMoreActions();

	public void selectOptionFromIsheetMoreActions(String option);

	public boolean verifyIsheetLinkMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage, String iSheetName);

	public boolean verifyRecentIsheetLinkMessageRecieved(String userName, String message, String iSheetName);

	public boolean verifyIsheetName(String isheetName);

	public boolean verifyCloseButtonOnPrintModal();

	public boolean verifyPrintButtonOnPrintModal();

	public boolean verifyIsheetNameOnPrintModal(String isheetName);

	public boolean verifyIsheetFieldValueOnPrintModal(String fieldName, String valueToVerify);

	public String getColumnValueOnPrintModal(String columnName);

	public void clickPrintButtonOnPrintModal();

	public boolean verifyPrintPreviewWindow();

	public void waitForFileGettingDownloaded();

	public boolean verifyExportOption(String exportOption);

	public void selectExportOption(String exportOption);

	public void clickExportOnExportIsheetModal();

	public void clickCancelOnExportIsheetModal();

	public boolean verifyBrowseButtonOnImportModal();

	public boolean verifyIsheetMoreActionOptions(String option);

	public boolean verifyOptionFromMoreOptionsOfIsheetRecord(String[] columnName, IsheetData isheetData, String option);

	public boolean verifyTabInAuditHistoryModal(String tab);
	
	public boolean verifyCloseButtonOnAuditHistoryModal();

	public boolean verifyCompareButtonOnAuditHistoryModal();

	public boolean verifyAuditHistoryVersionsTableHeaders();

	public boolean verifyCheckboxForAllRecordsInAuditHistoryTable();

	public boolean verifyMoreActionsForAllRecordsInAuditHistoryTable();

	public boolean verifyMoreActionOptionsOfCurrentVersion(String option);

	public boolean verifyMoreActionOptionsOfAllVersions(String option);

	public void clickMoreActionOptionOnCurrentVersion(String option);

	public void clickMoreActionOptionOnGivenVersion(String versionNumber, String option);

	public void clickCloseButtonOnAuditHistoryModal();

	public void compareIsheetVersions(String... versions);

	public void clickCompareButtonOnAuditHistoryModal();

	public boolean compareSingleLineTextData(String previousString, String currentString, String columnName);

	public void chooseTemplateToGenerateDocument(String template);

	public void clickGenerateButton();

	public void waitTillDocumentGetsGenerated();

	public boolean verifyHeaderOfDocumentGeneratedModal();

	public boolean verifyTextOfDocumentGeneratedModal();

	public boolean verifyDownloadButtonOnDocumentGeneratedModal();

	public boolean verifySaveButtonOnDocumentGeneratedModal();

	public boolean verifyCloseButtonOnDocumentGeneratedModal();

	public boolean verifyOptionFromIsheetMoreActions(String option);

	public boolean verifyMessageOfDocumentGeneratedModal(String message);

	boolean verifyAuditHistoryAuditsTableHeaders();

	boolean verifyCreatedRecordsLink();

	boolean verifyModifiedRecordsLink();

	void clickCreatedRecordsLink();

	void clickFirstModifiedRecord();

	public void clickDocumentLinkOnDocumentGeneratedModalMessage();

	public DocumentWeb clickSpecifiedFolderLinkOnDocumentGeneratedModalMessage();

	public boolean verifyDocumentTemplateInFilePreviewWindow(String fileName);

	public void clickDownloadOnFilePreviewPage();

	public void closeFilePreviewWindow();

	public boolean verifyDownloadedDocumentTemplate(String templateFileName);

	public void clickDownloadOnDocumentGeneratedModal();

	public void clickSaveFolderOnDocumentGeneratedModal();

	public boolean verifyHeaderOfSaveToSiteFolderModal(String headerName);

	public boolean verifySiteLabelOnSaveToSiteFolderModal();

	public boolean verifyDropDownOnSaveToSiteFolderModal();

	public boolean verifyDefaultSiteOnSaveToSiteFolderModal(String siteName);

	public boolean verifyChooseFolderLabelOnSaveToSiteFolderModal();

	public boolean verifyExpandAllOnSaveToSiteFolderModal();

	public boolean verifyCollapseAllOnSaveToSiteFolderModal();

	public boolean verifySaveToSiteFolderModalOpened();

	public void clickCancleOnSaveToSiteFolderModal();

	public void clickCloseOnSaveToSiteFolderModal();

	public void clickSaveOnSaveToSiteFolderModal();

	public void setSiteFolderLocationToSaveDocument(String location);

	public void clickIsheetRecordLinkOnDocumentGeneratedModalMessage();

	public boolean verifyAttachmentRecordForDocumentTemplate(String columnName, String fileNames);

	public boolean verifyExpandedTree();

	public void clickExpandAllOnSaveToSiteFolderModal();

	public void clickCollapseAllOnSaveToSiteFolderModal();

	boolean verifyChoiceRecord(String columnName, String columnData);

	void editChoiceData(String columnName, String data);

	int getRecordCountOfIsheet();
	
	public void clickTabInAuditHistoryModal(String tab);
	
	public void cancelEditRecordModal();

	
}
