package com.highq.pageobjects.base;

import java.util.ArrayList;
import java.util.Map;

public interface QandAPage extends BannerPage {

	public void clickOnAskQuestionButton();

	public void clickOnHeaderViewButton();

	public void selectView(String sortByOption);

	public void setSearchText(String searchText);

	public void clickOnFilterButton();

	public boolean verifyAskQuestionModal();

	public void sendTextInQuestionTextBox(String text);

	public void clickOnFootersInAskQuestionModal(String footerName);

	public boolean verifyAskQuestionButton();

	public boolean verifyFadeModal();

	public void clickOnOkButtonInFadeModalDialog();

	public void addQuestion(String question);

	public void addQuestion(String question, String topic, String prioty, String file);

	public void selectTopicInAskQuestionModal(String topic);

	public void clickOnTopicDropDown();

	public void clickOnPriorityDropDown();

	public void selectPriorityInAskQuestionModal(String priority);

	public void selectTabInAskQuestionModal(String tab);

	public void attachFileInAskQuestionModal(String file);

	public boolean verifyCustomMessage(String currentMessage);

	public void clickOnQuestion(String question);

	public void clickOnCloseButtonInQADetailContainer();

	public boolean verifyQADetailContainer();

	public void clickOnShowTagInQADetailContainer();

	public boolean verifyStatusValueInQADetailContainer(String status);

	public boolean verifyRelatedToValueInQADetailContainer(String relatedTo);

	public boolean verifyPriorityValueInQADetailContainer(String priority);

	public boolean verifyTopicValueInQADetailContainer(String topic);

	public boolean verifyAttachedFileInQADetailContainer(String file);

	public boolean verifyQuestionValueInQADetailContainer(String question);

	public void editQuestion(String question);

	public void clickOnDeleteInMoreAction();

	public void clickOnEditInMoreAction();

	public void clickOnQuestionMoreActionButtonInQADetailContainer();

	public void editTextInQuestionTextBox(String text);

	public void removeAttachmentInAskQuestionModal(String file);

	public void editAttachmentInAskQuestionModal(String file, String newFile);

	public void sendTextInQuickReplyTextBoxInQADetailContainer(String text);

	public void clickOnReplyTagInQADetailContainer();

	public void clickOnQADetailContainerBottomButtons(String button);

	public boolean verifyReplyValueInQADetailContainer(String actualReply);

	public boolean verifyStatusInQuestionListing(String question, String status);

	public boolean verifyDraftReplyStatus(String status);

	public boolean verifyAnswerModal();

	public void editAnswerTextBoxInAnswermodal(String text);

	public void clickOnAnswerModalFooterButtons(String button);

	public void sendTextInQuickSearchTextBox(String text);

	public void clickOnQuickSearchDropDown();

	public void clickOnOptionsInQuickSearch(String option);

	public boolean verifyQuestionInQuestionListing(String question);

	public void waitForLoader();

	public boolean verifyOrganizationInQuestionListing(String org);

	public boolean verifyAuthorInQuestionListing(String author);

	public boolean verifyNoQuestions();

	public boolean verifyQuestionID(String question, String ID);

	public boolean verifyQuestionNO(String question, String NO);

	public String getQuestionNO(String question);

	public String getQuestionID(String question);

	public boolean verifyCancelButtonInAdvanceSearchModal();

	public boolean verifySearchButtonInAdvanceSearchModal();

	public boolean verifyAdvanceSearchModal();

	public void clickOnCancelInAdvanceSearchModal();

	public void clickOnSearchInAdvanceSearchModal();

	public boolean verifyQuestionList();

	public boolean verifyFieldsInAdvanceSearchModal(String field);

	public void sendTextInAdvanceSearchTextBoxes(String textBoxName, String text);

	public void selectOptionInModifiedDateDropDown(String option);

	public void clickOnSelectButtonInselectItemsModal();

	public void clickOnCancelButtonInselectItemsModal();

	public void selectSiteInSelectItemsModal(String site);

	public boolean verifySelectItemsModalInAdvanceSearchModal();

	public void selectOptionInFileFolderDropDown(String option, String file);

	public void clickOnFileFolderDropDownInAdvanceSearchModal();

	public void clearQuickSearchTextBox();

	public void clickOnSuggestionBoxInOrganisationInAdvanceSearch(String suggestion);

	public void clickOnMoreActionInQAPage();

	public void selectOptionInMoreActionInQAPage(String option);

	public boolean verifyExportModal();

	public void clickOnExportButtonInExportModal();

	public void clickOnCancelButtonInExportModal();

	public void clickOnXLSXButtonInExportModal();

	public void clickOnImportTemplateButtonInExportModal();

	public void clickOnSubmitAnswerCheckBoxInImportModal();

	public void attachFileToBrowseButtonInImportModal(String file);

	public void clickOnCancelButtonInImportModal();

	public boolean verifyImportModal();

	public void clickOnNextButtonInImportModal();

	public void clickOnChangeTopic();

	public void clickOnTopMoreActionButtonInQADetailContainer();

	public void changeTopic(String topic);

	public void selectTopicInChangeTopicModal(String topic);

	public boolean verifyChangeTopicModal();

	public void clickOnSubmitButtonInChangeTopicModal();

	public void clickOnCancelButtonInChangeTopicModal();

	public boolean verifyTopicInQuestionListing(String question, String topic);

	public void replyQuestion(String question, String reply);

	public void selectOptionInTopMoreActionInQADetailContainer(String option);

	public boolean verifyDiscloseModal();

	public void clickOnCancelButtonInDiscloseModal();

	public void clickOnSaveButtonInDiscloseModal();

	public void clickOnDiscloseToAllOrganizationsCheckBoxInDiscloseModal();

	public void switchToPrintWindow();

	public void closePrintWindow();

	public boolean verifyDetailsInPrintPreviewPage(String field, String value);

	public boolean verifyQuestionValueInPrintPreviewPage(String question);

	public boolean verifyCloseButtonInPrintPreviewPage();

	public boolean verifyPrintButtonInPrintPreviewPage();

	public boolean verifyPrintPreviewPage();

	public void clickOnCloseButtonInPrintPreviewPage();

	public void clickOnPrintButtonInPrintPreviewPage();

	public String getAskedOnInQuestionList(String question);

	public void switchToPrintPage();

	public boolean verifyPrintPage();

	public boolean verifyLoader();

	public void clickOnCaretSign(String header);

	public boolean verifyDownCaretSign(String header);

	public boolean verifyUpCaretSign(String header);

	public boolean verifyUpDownCaretSign(String header);

	public boolean verifyQuestionOrder(int position, String questionValue);

	public void clickOnDeleteButtonInDeleteModal();

	public void clickOnCancelButtonInDeleteModal();

	public boolean verifyDeleteModal();

	public void addComment(String question, String comment);

	public boolean verifySuggestionBoxInOrganisationInAdvanceSearch(String suggestion);

	public void clickOnAutoSuggest(String text);

	public void waitForFileGettingDownloaded();

	public boolean verifyAttachmentsTabOpenedInAskQuestionModal();

	public boolean verifyModal();

	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList> getQAListData();

	public ArrayList<String> getColData(String columnName);

	public boolean verifyAscendingOrder(String colName);

	public boolean verifyDescendingOrder(String colName);

}
