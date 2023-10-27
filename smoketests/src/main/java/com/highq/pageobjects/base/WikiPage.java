package com.highq.pageobjects.base;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.openqa.selenium.By;

public interface WikiPage extends BannerPage
{
	public void goToMoreActionOption(String action);

	public void sortWiki(String sortingPage);

	public void selectVersionCheckBox(String version);

	public void selectWikiNotification(String notification);

	public void selectWikiStatus(String status);

	public void selectLevelInPRINT_AND_EXPORTModal(String level);

	public void goToWikiHome();

	public void goToFavourites();

	public void renameWikiTitle(String newTitle);

	public void addTag(String tag);

	public void clickOnWikiLikeTag();

	public void addComment(String comment);

	public void clickOnCommentCancelButton();

	public void clickOnLinkButtonOfComment();

	public void clickOnAttachmentButtonOfComment();

	public void clickOnTextForamtingButtonOfComment();

	public void clickOnWikiCommentFooterLabels(String userName, String action);

	public void clickOnWikiCommentFooterLabels(String userName, String action, String comment);

	public void deleteWikiComment();

	public void clickOnLoadMoreComments();

	public void clickOnAddWiki();

	public void clickOnHeadersInInsertLinkModal(String headerName);

	public void selectFilterByInInsertModal(String filterType);

	public void clickOnTitleOfInsertModal(String title) throws InterruptedException;

	public void selectInsertModalLinkToDropDownOptions(String link);

	public void clickOnLinkInInsertModalBrowseOption(String link) throws InterruptedException;

	public void clickOnSerchBarDropDownInInsertModalBrowseTab();

	public void clickOnSerchBarDropDownInInsertModalSearchTab();

	public void selectSearchOptionsInInsertLinkModalBrowseTab(String filter, String opt);

	public void selectSearchOptionsInInsertLinkModalSearchTab(String filter);

	public void clickOnStatusOptions(String option);

	public void sendTextInExternalURl(String URL);

	public void clickOnUseShortUrlCheckBox();

	public void selectTextFromOpenInDropDown(String option);

	public void attachFileWithBrowseButtonInInsertModal(String path);

	public void selectSiteInInsertModalRecentTab(String siteName) throws InterruptedException;

	public void selectSiteInInsertModalSearchTab(String siteName) throws InterruptedException;

	public void searchTextInInsertModal(String text);

	public void selectSiteInAddAttachmentModalRecentTab(String siteName);

	public void selectSiteInAddAttachmentModalBrowseTab(String siteName);

	public void sendTextInSerchTabInAddAttachmentModalRecentTabSiteDropDown(String siteName);

	public void sendTextInSerchTabInAddAttachmentModalBrowseTabSiteDropDown(String siteName);

	public void clcikOnModalsBottomButtons(String buttonName);

	public void selectTabInAddAttachmentModal(String tabName);

	public void sendTextInSerchTabInAddAttachmentModalsearchTabSiteDropDown(String siteName);

	public void selectSiteInAddAttachmentModalSearchTab(String siteName);

	public void sendTextInSerchBarInAddAttachmentModalSearchTab(String text);

	public void searchPages(String pageName);

	public boolean verifySearchedPage(String pageName);

	public void selectWikiFromLeftPanel(String wikiName);

	public void setTitle(String title);

	public void saveWiki();

	public void sendTextInCkContetTextBoxInAddWikiPage(String content);

	public void clearSearchItem();

	public void clickOnAttachmentsTabInAddWiki();

	public void clickOnSettingsTabInAddWiki();

	public void clickOnContentTabInAddWiki();

	public void attachFileInAddWiki(String path);

	public void addToFavourite();

	public void removeFromFavourite();

	public void sendTextInTabInAddWiki(String text);

	public boolean verifyWikiTitle(String actTitle);

	public boolean verifyFavouriteIconIsSelected();

	public boolean verifyWikiInFavourites(String wikiName);

	public void clickOnMyDraftsInLeftMenu();

	public boolean verifyPresenceOfWikiInDrafts(String wiki) throws InterruptedException;

	public boolean verifyMyDraftsLinkIsVisbile();

	public void cancelWiki() throws InterruptedException;

	public boolean verifyAutoSaveDraftDialogueContent(String content);

	public void enterTag(String text);

	public void discardAutoSaveDraftDialogue();

	public void saveAutoSaveDraftDialogue();

	public boolean verifyWikiStatus(String wikiTitle, String status);

	public void selectWikiFromMyDraft(String wikiTitle);

	public void selectMoreOptionsOperationsOfWiki(String option);

	public void selectSearchedWiki(String wikiTitle);

	public boolean verifyDiscardedAutoSavedDraft(String wikiTitle, String status);

	public void waitForAutoSavedNotification();

	public void deleteWikiFromDraft(String wikiTitle);

	public boolean searchWikiInDraft(String wikiTitle);

	public void clickCancelInDeleteWikiFromDraft();

	public void clickDeleteInDeleteWikiFromDraft();

	public void goToFavourates();

	public void attachOnBrowseButtonInAttachmentModalUploadTab(String File);

	public boolean verifyCkContentData(String actContent);

	public boolean verifyTagsAdded(String actTag);

	public boolean verifyAttachedFile(String actFileName);

	public void clickOnMoreActionInSelectedWiki();

	public void selectOptionInMoreActionInSelectedWiki(String option);

	public void editTag(String oldTagName, String newTagName);

	public void editTagInEditWikiPage(String oldTagName, String newTagName);

	public void editAttachmentInEditWiki(String oldFileName, String newFileName);

	public void removeAttachmentInEditWiki(String fileName);

	public boolean verifyOptionInMoreActionInSelectedWiki(String option);

	public boolean verifyDeleteWikiModal();

	public void clickCancelOnDeleteWikiModal();

	public void clickOkOnDeleteWikiModal();

	public boolean verifyWikiFromLeftPanel(String wikiName);

	public boolean verifyWikiHomeLevelWiki(String wikiName) throws InterruptedException;

	public boolean verifyChildLevelWiki(String parentWiki, String childWiki);

	public boolean verifySiblingLevelWiki(String wiki, String siblingWiki) throws InterruptedException;

	public boolean verifyResumeDiscardYelloStrip();

	public void clickOnResumeYelloStrip();

	public void clickOnDiscardYelloStrip();

	public String getTextFromCKContentTextBox();

	public void clickOnEditTitleButton();

	public boolean activeWikiTitle();

	public Boolean verifyDeletedItemsLink();

	public void deleteWiki(String wikiName);

	public void deleteAllItemsFromDeletedItems();

	public void goToDeletedItems();

	public void selectMoreOptionsFromDeletedItems(String wikiName, String option);

	public String getDeleteWikiModalTitle();

	public String getCustomModalMessage();

	public Boolean verifyWikiInDeletedItems(String wikiName);

	public void deleteWikiFromDeletedItems(String wikiName);

	public Boolean veirfyRestoreWikiTree();

	public Boolean verifyWikisInRestoreWikiMainTree(String[] wikiTitles);

	public Boolean verifyRestoreWikiModal();

	public void selectWikiInRestoreWiki(String wikiName);

	public void clickCancelOnRestoreWiki();

	public void clickSaveOnRestoreWiki();

	public void goToOrphanPages();

	public boolean verifyWikiInOrphanPages(String wikiName);

	public void clickOnSettingsTabInEditWiki();

	public void clickOnChooseLocationButtonInSettingsTabInEditWiki();

	public boolean verifyLocationModal();

	public void selectSiteInSelectLocationModal(String siteName);

	public String selectWikiInSelectLocationModal(String wikiName);

	public void clickOnSaveButtonInSelectLocationModal();

	public boolean verifyWikiEditForm();

	public ArrayList<Boolean> getWikiPermissionStatus(String userName);

	public void shareViaEmail(String recipientMail, String subject, String message);

	public void shareViaMessage(String recipientMail, String message);

	public void copyShareLink(boolean shortUrl);

	public void openCopiedURL() throws UnsupportedFlavorException, IOException;

	public boolean verifyActiveWiki(String wikiTitle);

	public void clickSendInShareModal();

	public void clickCancelInShareModal();

	public boolean verifyDefaultSubjectIsPresent();

	public boolean verifyAddTagButton();

	public int getCommentCount();

	public boolean verifyCommentOptionsInCommentGrid(String comment, String option);

	public boolean verifyTagAdded(String tag);

	public void clickPostInShareModal();

	public void shareViaMicroblog(String message, String siteName);

	public void gotoEmailTab();

	public void gotoLinkTab();

	public void gotoMessageTab();

	public void gotoMicroblogTab();

	public boolean verifyInputTextIsPresent(By elementXpath);

	// public void selectOptionFromAutoSuggest(String option);

	public boolean verifyDefaultMessageIsPresent();

	public boolean verifyDefaultMicroblogSite(String siteName);

	public String getMailContent(String mailQuery);

	public void createHtmlFile(String fileName, String fileContent);

	public void getLocalHtmlPage(String fileName);

	public boolean verifyContent(String mailto, String content);

	public void closeCurrentTab();

	public Timestamp getStartDateTimeStamp();

	public Timestamp getEndDateTimeStamp();

	public boolean verifyDeleteDraftMessage(String deleteMessage);

	public void selectThisPageOnly();

	public void selectThisPageAndAllOfItsChildren();

	public void selectWikiLevel(String level);

	public void selectIncludeIndexOption(boolean expectedState) throws InterruptedException;

	public void clickOnExportOrPrint();

	public void clickExportButton();

	public void clickCancelButton();

	public void selectIncludeComment(boolean expectedState) throws InterruptedException;

	public void switchToPrintWindow();

	public boolean verifyLogoInPrintWindow();

	public boolean verifyWikiTitleInPrintWindow(String wikiName);

	public boolean verifyCommentInPrintWindow(String commentText);

	public void closePrintWindow();

	public void compareWikiVersions(String version1, String version2);

	public boolean verifyCompareWikiDialogOpened();

	public void clickCompareOnInfoModal();

	public boolean verifyCompareWikiDialogClosed();

	public void clickOnVersionsTab();

	public boolean verifyVersionInCompareWikiModel(String version);

	public boolean verifyVersionMataDataInCompareWikiModel(String version, String mataData);

	public boolean verifyOverviewTabOnInfo();

	public void clickOnOverviewTab();

	public boolean verifyVersionsTabOnInfo();

	public boolean verifyAttachmentsTabOnInfo();

	public String getWikiLinkInfo();

	public void selectShortUrlCheckBox();

	public int getOptionValueFromInfoModal(String option);

	public void clickTagInInfoModal(String tag);

	public boolean verifyTagSearchDisplay();

	public void clickCancelOnInfoModal();

	public boolean verifyInfoModalClosed();

	public void clickExportOnInfoModal();

	public void clickPrintOnInfoModel();

	public String getCurrentVersion();

	public void restoreWikiVersion(String version);

	public void clickOKOnCustomMessageModal();

	public void clickCloseOnCompareDialog();

	public void clickOnAttachmentsTab();

	public void openAttachmentFromWikiInfo(String fileName) throws InterruptedException;

	public boolean verifyFileInFilePreviewWindow(String fileName);

	public void clickCancelOnFileOPreviewWindow();

	public boolean verifyFilePreviewWindowClosed();

	public String getSelectedWikiLink();

	public boolean verifyResumeDiscardYellowStrip();

	public void clickOnResumeYellowStrip();

	public boolean verifyDiscardTagInResumeEditingMessage();

	public boolean verifyResumeTagInResumeEditingMessage();

	public boolean verifyResumeEditingMessage(String actMessage);

	public void clickOnDiscardYellowStrip();

	public int getCommentLikeCount(String comment);

	public void clickOnLikeCommentCount(String comment);

	public boolean verifyPeopleWhoLikeThisModal();

	public void clickOnModalCloseButton();

	public boolean verifyCommentBoxText(String text);

	public void sendTextInWikiCommentBox(String text);

	public void clickOnWikiCommentPostButton();

	public String getCommentReplyData();

	public boolean verifyAttachmentModal();

	public void clickOnAttachButtonInAttachmentmodal();

	public boolean verifyWikiHome();

	public boolean verifyWikiLikeMessage(String message);

	public String getTextOfWikiLikeTag();

	public boolean verifyDeleteCommentModal();

	public boolean verifyWikiComment(String comment);

	public boolean verifyWikiCommentPostButton();

	public void clickOnRemoveAttachmentButtonInEditComment(String comment);

	public boolean verifyCommentBoxIsEditable(String comment);

	public String verifyCommentAttachment(String comment);

	public boolean verifyRemoveAttachmentButtonInCommentBox();

	public void deleteWikiComment(String comment);

	public void closeWikiCompareModel();

	public void closeWikiInfoModel();

	public void waitTillFileisDownloading();

	public boolean verifyLastCommentInSelectedWiki(String comment);

	public boolean verifyDefaultMicroblogMessageInShareModal(String messageToVerify);

	public boolean verifyWikiStatusInMyDrafts(String wikiName, String status);

	public boolean verifyCKEditorInWikiEditMode();

	public boolean verifyAttachmentsTabOpen();

	public boolean verifyLeftTree();
}
