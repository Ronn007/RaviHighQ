package com.highq.pageobjects.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import com.highq.base.CollaborateLabel.DocumentNotifications;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.DocumentRecipientsData;
import com.highq.pagedata.TaskAddDataPage;
import com.highq.pageobjects.pages.TasksWeb;

public interface DocumentPage extends BannerPage
{
	public void deleteAllDocs();

	public void addFile(DocumentAddDataPage addDoc, TaskAddDataPage taskAddDataPage)
			throws IOException, InterruptedException;

	public void gotoEditFile(String fileuploadpath, String fileName);

	public boolean verifyDocumentUploaded(String docName);

	public void searchFile(String fileName);

	public void gotoMoreActions(String file, String operation);

	public boolean uploadDocOnAddNewVersionWin(String docName, String newVersionName, TaskAddDataPage taskAddDataPage);

	public void uploadDoc_onAddNewVersionWin(String docName, String newVersionName, String versionNote)
			throws IOException;

	@Override
	public void uploadFile(By browseInpXpath, String fileuploadpath);

	public void setFileNotification(DocumentNotifications documentNotifications);

	public void addFileDesclaimer(String filedisclaimer);

	public void addFileTags(String tag);

	public void addFileTask(TaskAddDataPage taskAddDataPage);

	public void searchFolder(String folderName);

	public boolean verifySearchedFolder(String folderName);

	public void selectLeftPanelFileOptions(String option);

	public void selectItemFromAdd(String item);

	public void selectItemFromAction(String item);

	public void selectItemFromView(String item);

	public void openFilter();

	public void expandFilterOption(String filterName);

	public void setFilter(String filterName, Object data[]);

	public void filterByTags(String tagName, String data);

	public void filterByAuthor(String authorName, String data);

	public void selectFilterCheckbox(String option, boolean check);

	public void setMultipleFilter(Map<String, Object[]> filterData);

	public void clearFilters();

	public void openAdvancedSearch();

	public void advancedSearch(Map<String, String> map);

	public void sendAnyOfTheseWords(String value);

	public void sendAllOfTheseWords(String value);

	public void sendExcludeTheseWords(String value);

	public void sendTitle(String value);

	public void selectContentType(String value);

	public void selectFileType(String value);

	public void selectFolders(String value);

	public void selectLastModifiedDate(String value);

	public void sendAuthor(String value);

	public void sendTags(String value);

	public boolean verifyAllFileTypeFiltersCleared();

	public boolean verifyAllTagsFiltersCleared();

	public boolean verifyAllAuthorFiltersCleared();

	public boolean verifyAllFitersCleared();

	public void editFileDetails(String fileName, Map<String, String> editDetails);

	public void removeAllTags();

	public boolean verifyTag(String tagValue);

	public boolean verifyEditFileTitle(String titleToVerify);

	public boolean verifyDisclaimerText(String textToVerify);

	public void clickCancel();

	public void clickAddInAddFileModal();

	public void clickSaveInModal();

	public void addZipFile(String fileName, Map<String, String> metadata);

	public void clickAddInModal();

	public boolean verifyFolder(String folderName);

	public void clearSearchedItem();

	public void addPlaceHolderFileDetails(Map<String, String> placeHolderDetails);

	public boolean verifyPlaceHolderFile(String fileName);

	public void addMetadata(String metaFieldName, String dataToSend);

	public void previewFile(String fileName);

	public boolean verifyPreviewMetaData(String fieldName, String metaDataToVerify);

	@Override
	public void closePreviewPage();

	public void gotoMetadataTab();

	public void editFileMetadata(String fileName, String fieldToEdit, String valueToSend);

	public void downloadFile(String fileName);

	public void deleteFile(String fileName);

	public void createNewFolderInRoot(String folderName, String description);

	public void restoreFile(String fileName);

	public void moveFile(String siteName, String parentFolder, String childFolder);

	public void clearLeftPanelSearchItem();

	public boolean verifyNoFilesOrFolderPresent();

	public boolean verifyLeftPanelItem(String item);

	public String getFileVersion();

	public boolean verifyVersionCountIncremented(String fetchedVersion);

	public void gotoTasksTab();

	public void gotoAttachmentsTab();

	public TasksWeb clickAddButtonInTasksTab();

	public boolean verifyFolderTitle(String title);

	public boolean verifyFolderDescription(String description);

	public void editFolderDetails(String fieldName, String value);

	public void gotoTitleHeaderMoreActionItem(String item);

	public boolean verifyMetaFieldInEditWindow(String fieldName, String valueToVerify);

	public void editFolderMetadata(String metaField, String metaValue);

	public void clickSelectAllCheckbox();

	public void selectItemFromActions(String item);

	public void selectFileFromIndex(String fileName);

	public boolean verifyBulkPrintMessage();

	public void clickDownloadInModal();

	public boolean verifyFileInIndex(String fileName);

	public void checkoutFile(String fileName);

	public boolean verifyMoreActionsItem(String file, String operation);

	public boolean verifyCheckInFileName(String fileName);

	public boolean verifyCheckInFileStatus(String status);

	public void clickCheckIn();

	public boolean verifyCheckOutUserName(String userName);

	public void clickClose();

	public boolean verifyCancelCheckoutLinkIsPresent();

	public void cancelCheckout();

	public boolean verifyDisclaimerModalIsVisible();

	public void clickLike();

	public boolean verifyUnlikeLinkIsPresent();

	public boolean verifyLikeLinkIsPresent();

	public void addComment(String comment);

	public void clickPost();

	public boolean verifyVisibilityOfComment(String user, String comment);

	public boolean editComment(String user, String comment, String commentToEdit);

	public void clickCommentEditLink(String user, String comment);

	public void scrollToComment(String user, String comment) throws InterruptedException;

	public void clickCommentDeleteLink(String user, String comment);

	public void confirmDeleteCommentMessageBox();

	public boolean deleteComment(String user, String comment) throws InterruptedException;

	public int getCommentCount();

	public boolean verifyCommentCountIncremented(int currentCount);

	public boolean verifyCommentCountDecreased(int currentCount);

	public void clickUnlike();

	public void chooseAFolder(String parent, String child);

	public void chooseASite(String siteName);

	public boolean verifyChooseASiteName(String siteName);

	public void clickMove();

	public void clickCopy();

	public void copyFile(String siteName, String parentFolder, String childFolder);

	public void selectFileCheckBox(String fileName);

	public boolean verifyFileAuthorName(String author);

	public void addFileOrFolderToFavourites(String itemToFavourite);

	public void removeFileOrFolderFromFavourites(String itemToRemoveFromFavourites);

	public boolean verifyFileOrFolderFavouriteIconIsSelected(String favouriteItem);

	public boolean verifyItemInFavourites(String itemToVerify);

	public int getTotalPageCountFromPreview();

	public int getFilePages(String fileName);

	public boolean verifyUploadedZipFiles(String zipFile) throws IOException;

	public LinkedHashSet<String> getZipFileList(String zipFileName) throws IOException;

	public void uploadFileInAddFiles(String fileName);

	public void clickExpandAllInAddFolderSelectLocation();

	public void clickCollapseAllInAddFolderSelectLocation();

	public void selectFolderNameInSelectLocation(String folderName);

	public void clickSelectInChooseLocationModal();

	public void clickChooseButton();

	public void addFolderAtSpecificLocation(String folderName, String location);

	public void clickActionsButton();

	public boolean verifyActionsButtonIsVisible();

	public boolean verifyItemOfActionsButton(String itemToVerify);

	public boolean verifyFileStatusInIndex(String fileNameWithExtension, String statusToVerify);

	public boolean verifyCheckingAllocationMessage(String expectedMessage);

	public void clickCloseInCheckingAllocationModal();

	public void clickContinueInCheckingAllocationModal();

	public boolean verifyCheckingFilesAsNAForReviewTitleIsVisible(String title);

	public boolean verifyMarkingFilesAsNAForReviewTitleIsVisible(String title);

	public void renameFileInAddFilesModal(String fileNameWithExtension, String newName);

	public boolean verifyFileNameInAddFilesModal(String fileNameWithExtension);

	public void clickFilesTabInAddFilesModal();

	public List<String> getListOfDataOfColumn(String columnName, String dataType);

	public boolean verifyZipFileContainsDesiredFilesAndFolders(String zipFileNameWithExtension,
			String... filesOrFolders) throws IOException;

	public boolean verifyMyFileSendButton();

	void clickOnClassifierSearchRemoveButton();

	void addFolderNameAddFolderModal(String folderName);

	void addClassifierNameNewClassifierModal(String classifierName);

	void addClassifierDescriptionNewClassifierModal(String description);

	void clickCancelAddFolderModal();

	void clickCancelClassifiersModal();

	void clickAddButtonNewClassifiersModal();

	void clickSettingsTabAddFolderModal();

	void clickOnClassifierSelectButton();

	void clickNewClassifierButtonClassifierModal();

	boolean verifyDocumentAnalysisDisplayed();

	boolean verifyClassifierSelectButtonDisplayed();

	boolean verifyClassifiersModalDisplayed() throws InterruptedException;

	boolean verifyNewClassifierModalDisplayed();

	boolean verifyNewClassifierModalFieldsDisplayed();

	boolean verifyNewClassifierModalButtonsDisplayed();

	boolean verifyTooltipMessageScopeOfClassifier(String message);

	boolean verifyClassifiersLabelDisplayed();

	boolean verifyClassifierSearchTextboxDisplayed();

	boolean verifyClassifierButtonsDisplayed();

	void clickCancelClassifierModal();

	void clickCancelAddNewClassifierModal();

	void selectRadioButtonScopeOfClassifier(String option);

	void clickOnClassifierModalDoneButton();

	void clickOnAddFolderModalAddButton();

	boolean verifyClassifierDoneButtonsDisabledDisplayed();

	boolean verifyClassifierDoneButtonEnabledDisplayed();

	void clickOnClassifierTokenRemoveButton();

	boolean verifyClassifierTokenRemoveButtonDisplayed();

	boolean verifyClassifierTokenDisplayed(String classifierName);

	boolean verifyClassifiersList(ArrayList<String> classifiersList);

	boolean verifyClassifiersName(String classifiersName) throws InterruptedException;

	boolean verifyMessageForNoClassifiersNameDisplayed(String message);

	boolean verifyMessageForDuplicateClassifierAdd(String message);

	boolean verifyClassifiersDescriptionLink(String classifierDescription, String linkName);

	void clickOnClassifiersDescriptionLink(String classifierDescription, String linkName);

	void selectClassifierFromList(String classifierName);

	void searchClassifier(String classifierName);

	public boolean verifyDocumentTemplateSaved(String templateFileName) throws IOException;

	public boolean verifyAuditsTabVisibilityInAuditHistoryModal();

	public boolean verifyVersionsTabVisibilityInAuditHistoryModal();

	public boolean verifyAccessedbyTabVisibilityInAuditHistoryModal();

	public void clickCloseInAuditHistoryButton();

	public boolean verifyDeleteMessage(String messageToVerify);

	public void clickDeleteInDeleteModal();

	public void clickOnAddButtonOfTaskInAddNewVersion();

	public void clickOnAddInAddNewVersion();

	public void clickAddInAddFolderModal();

	public void selectItemFromNew(String item);

	public void selectItemFromUpload(String item);

	public boolean verifyActionOnAuditHistoryFromMoreAction(String option);

	public void clickOnCloseButtonOfAuditHistoryDialogBox();

	public void clickOnPrintButtInFileViewer();

	public void clickOnMoreActionOfParticularFile(String fileName) throws IOException, InterruptedException;

	public boolean verifyMoreActionOptionOfParticularFile(String option);

	public void clickOnMoreActionMyFiles(String fileName);

	public void clickOnActionButton();

	public String getFileExtension(String fileName);

	public boolean verifySendToInActionButton(String fileExtension, String option);

	public void clickOnCheckOut();

	public boolean verifySendToDisabledInActionButton(String option);

	public void clickOnCloseInCheckedOutWindow();

	public boolean verifyMoreActionOptionInViewer(String option);

	public void clickOnCancelCheckOut();

	public void clickOnSendToButtonFromActionButton(String option);

	public boolean verifyServiceOnSendToModel(String serviceName);

	public void clickOnCloseButtonSendToModel();

	public boolean verifyFilesOnSendToDialog(String fileName);

	public boolean verifyFileSizeMessage(String msg);

	public boolean verifyEmptyTextField();

	public void clickOnCancelGeneratedToken();

	public boolean verifyRecipientToken(String token);

	public void clickOnCloseInRecipientDialog();

	public boolean verifyDisabledRecipientNameAndEmailTextbox();

	public void setMember(String memberName);

	public boolean verifyEmailOfRecipient(String msg, String recipientName, String recipientEmail, String message);

	public boolean verifyOptionInNeedsToSignOnRecepientDialog();

	public void clickOnCloseOfAdobeSignDialoobx();

	public boolean verifyDetailsOnRecepientDialog(String serviceName);

	public boolean verifyDetailsOnSendToDialog(String filename);

	public void clickActionButton();

	public void clickCloseButtonOnSendToDialog();

	public boolean verifyErrorMessageForFileSendTo(String smg);

	public void enterDetailsIntoSendToDialog(String enterName, String email, String message);

	public void clickOnSpecifiedServiceFromSendToDialog(String serviceName);

	public void clickOnOptionFromFileMoreAction(String option);

	public boolean verifyDocuSignSendToModel(String serviceName);

	public void clickOnMoreActionOptionInMyFile(String fileName, String option)
			throws IOException, InterruptedException;

	public boolean verifyFileInMyFile(String fileName);

	public boolean checkTagginginterface();

	public void clickOnSendButtonInRecipientDialog();

	public boolean verifyFiles(String fileName) throws IOException, InterruptedException;

	void clickNewFileButton();

	void clickUploadFileButton();

	boolean verifyNewWordDocument();

	boolean verifyExpandallAndCollapseAlllink();

	void addNewWordDocumentAtSpecificLocation(String folderName, String destination);

	void clickNewWordDocumentSave();

	void closeAddFolderLocationModal();

	Boolean verifyFolderNameInFileSelectionOFOnlineOfficeDocument(String folder);

	boolean verifyFileHeaderOption(String option);

	boolean verifyOpenNewTabOfOnlineOfficeDocument(String documentName);

	void gotoMoreActionsOFHover(String file, String operation);

	void gotoMoreActions(String file, String operation, String actionType);

	void clickOnSubmenuOptionOfFileMoreActions(String file, String operation, String subMenuOptionValue);

	boolean verifySubmenuOption(String subMenuOptionValue);

	boolean verifyOnSubmenuOptionOfFile(String file, String operation, String subMenuOptionValue);

	void gotoMoreActionsMyFiles(String file, String operation, String actionType);

	void clickOnSubmenuOptionOfFileMyFiles(String file, String operation, String subMenuOptionValue);

	boolean verifyOnSubmenuOptionOfFileMyFiles(String file, String operation, String subMenuOptionValue);

	Boolean verifyOptionOfNewButton(String option);

	Boolean verifyOptionOfUploadButtonFile(String option);

	void switchWindow(int index);

	boolean verifyAccessRestricted();

	public void sendTextInVersionNotesTextBox(String text);

	public boolean verifyWatermarkInViewer(String textToVerify);

	public void setThirdPartyDropDown(String optionName);

	public void setMessage(String memberName);

	public void clickAddFieldsOnThirdPartyService();

	public void clickOnMoreActionInSentForSignatureTab(String fileName, String option);

	public void clickMoreActionOnSentForSignature(String fileName);

	public void clickOnLeftPanelSentForSignature();

	public void selectOptionFromActionMenu(String option);

	public void clickMenuItemOnSentForSignature(String option);

	public void gotoLinkFromViewMenuDropDown(String option);

	public boolean verifyFileOnListView(String filename);

	public boolean verifySenderMiniCard(String user);

	public void clickOnMoreActionInSentForSignatureTab(String filename);

	public boolean verifyOptionsInMoreActionOnSentForSignature(String option);

	public boolean verifyUserLink(String... users);

	public void clickOnMoreLinkInSentForSignature();

	public boolean verifyUserDetailsOnMoreLink(String users);

	public boolean verifyCloseOnMoreLink();

	public boolean verifyfileOnMoreLink(String fileName);

	public void clickOnCancelButtonOfRecipientOfMoreLink();

	public boolean verifyOptionInMoreActionOfThumbnailView(String option);

	public boolean verifyUserLinkAtColumnView(String... users);

	public boolean verifyDetailsOnMoreLink(String fileName, String... users);

	public boolean verifyFileOnListViewAtThumbnailView(String filename);

	public boolean verifySenderMiniCardAtThumbnailView(String user);

	public void cancelMiniCard();

	public boolean verifyUserLinkAtThumbnailView(String... users);

	public void clickOnDeleteButtonFromActionButton(String option);

	public void enterDetailsMoreThanTwoRecipient(String message, DocumentRecipientsData... recipients)
			throws InterruptedException;

	public void clickOnSendFromTaggingInterface() throws InterruptedException;

	public void clickOnSentForSignatureLink();

	public void clickOnMoreActionFromSentForSignatureTab(String filename);

	public void clickOnOptionFromMoreActionInSentForSignature(String option);

	public boolean verifyRemindDialogSendButton();

	public boolean verifyCloseDetailsOnRemindDialog();

	public void clickOnCancelInRemindDialog();

	public void clickOnCancelInRevokeDialog();

	public void clickOnRevokeButton();

	public void clickOnRevokeButtonInRevokeDialog();

	public void clickOnSendButtonInRemindDialog();

	public boolean verifyReminderMessage();

	public boolean verifyMoreActionButton(String filename);

	public boolean verifyCloseButtonOnRevoke();

	public boolean verifyrevokeSignRequestOnRevoke();

	public boolean verifyCloseSignRequestOnRevoke();

	public boolean verifyRevokeModalOnRevoke();

	public boolean verifyRevokeBodyOnRevoke();

	public boolean verifyGlobalMessageOnRevoking();

	public boolean verifyFileInSentForSignatureLink(String filename);

	public boolean verifyStatusOnFileModule(String filename, String status);

	public void clickOnRevokeSigningRequestLinkInFileViewer();

	public void clickOnRevokeButtonFromFileViewer();

	public boolean verifyGlobalMsgRevokedSuccessfully();

	public void clickOnActionButtonInTaggingInterface() throws InterruptedException;

	public void clickOnOptionInActionButton(String option) throws InterruptedException;

	public boolean verifyMessageForDeletingFileWithSentForSignatureStatus(String msg);

	public boolean verifyMessageForDeletingFileWithVoidedStatus(String msg);

	public boolean verifyMessageForDeletingFileWithDraftStatus(String msg);

	public boolean verifyRemindDialogCloseButton();

	public boolean verifyRemindDialogSendRemiderBody();

	public boolean verifyRemindDialogSendRemider();

	public boolean verifyDocumentOnTaggingInterface();

	public boolean verifyStatus(String filename, String status);

	public boolean verifyFileInSentForSignatureTab(String filename);

	public boolean verifyDetailsOnMessageToAllRecipient();

	public boolean verifyDetailsCloseButtonOnEditRecipiet();

	public boolean verifyDetailsOnMessageEditeRecipient();

	public boolean verifyMissingRecipientMessage(String expectedMessage);

	public boolean verifyUploadDocumentTitle();

	public boolean verifyFileOnEditDocument(String finlename);

	public boolean verifyDoneButtonOnEditDocument();

	public boolean verifyDetailsCloseButtionOnEditDocument();

	public boolean verifyDetailsOnAdvancedOption();

	public boolean verifyAdvancedOption();

	public boolean verifyCloseOnAdvancedOption();

	public boolean verifySaveOnAdvancedOption();

	public boolean verifyCancelOnAdvancedOption();

	public boolean verifyRecipientPrivilegesOnAdvancedOption();

	public boolean verifyReminderOnAdvancedOption();

	public boolean verifyExpirationOnAdvancedOption();

	public boolean verifyTaggingInterfaceWindow();

	public void clickOnSendTaggingInterface() throws InterruptedException;

	public void clickOnAddFields();

	public void addFieldsInTaggingInterface();

	public void sendDocumentWithoutFields() throws InterruptedException;

	public void clickOnSendToButtonFromActionButtonInMyFile(String option);

	public boolean verifyAddRecipientFieldsOnTaggingInterface();

	public void enterDetailsIntoSendToDialog(String enterName, String message);

	public void gotoMoreActionsSendTo(String file, String operation);

	public boolean clickOnDocuSignSendButtonOfSendToModel(String clientEmail, String clientPassword);

	public boolean verifyAdobeDialogServiceName(String serviceName);

	public boolean verifyAdobeDialogEmailBox();

	public boolean verifyAdobeDialogNameBox();

	public boolean verifyAdobeDialogMessageBox();

	public boolean verifyAdobeDialogCloseButton();

	public boolean verifyAdobeDialogSendButton();

	public void clickOnCancelBtnOnTagging();

	public void clickOnMoreActionFromThumbnailsView(String filename);

	public void clickOnDiscardBtnOnDiscardDraftDialog();

	public void clickOnCancelBtnInEditMessage();

	public void clickOnCancelInDeleteDialog();

	public boolean verifyGlobalMsgOnUnlockinFile(String msg);

	public void clickOnUnlockBtnInUnlockDialog();

	public boolean verifyFilesTable();

	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList> getTableData();

	public void clickOnCaretSign(String colName);

	public boolean verifyUpCaretSign(String colName);

	public boolean verifyDownCaretSign(String colName);

	public boolean verifyAscendingOrder(String colName, String dataOf);

	public boolean verifyDescendingOrder(String colName, String dataOf);

	public boolean verifyElementsOnUnlockDialog();

	public boolean verifyStatusInViewer(String status);

	public void clickOnRevokeLinkInViewer();

	public void closeMoreActionInViewer();

	void gotoMoreActionsForFilePreview(String file, String operation, String actionType);

	void clickMoreActionsForFilePreviewSubOption(String file, String operation, String subMenuOptionValue);

	public boolean verifySpecialCharactersForOnlinOffice(String msg);

	public void closeForOnlineDocumentCreateModal();

	public boolean verifySpecialCharactersForFileSharIconOnlinOffice(String value);

	public boolean verifyStatusInAuditHistory(String actionStatus);

	public boolean verifyNoPreview();

	public boolean verifyWatermarkNotDisplay();

	public void gotoOptiOnOnMoreActionOfFileViewer(String option);

	public void clickOnCrossButtonInFileviewer();

	public void clickOnMoreActionOfFileViewer();

	void addFileOrFolderToFavouritesMyFiles(String itemToFavourite);

	void selectFileViewFromSettingTab(String option);

	void gotoSettingsTab();

	public void clickOnRevokeAndDeleteBtnInDeleteDialog();

	public void clickOnUnlockRevokeAndDeleteBtnInDeleteDialog();

	public void clickOnUnlockAndDeleteBtnInDeleteDialog();

	void clickOnSelectedFile(String filename);

	public boolean clickOnCancelBtnOnAdvancedOptionAtThirdPArty();
}
