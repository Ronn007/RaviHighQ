/**
 * 
 */
package com.highq.pageobjects.base;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author vivek.mishra
 */
public interface EventsPage extends BannerPage
{
	public void setDateInLeftPannelCalender(String date) throws ParseException;

	public boolean verifyDate(String date) throws ParseException;

	public boolean verifyNoEvent();

	public boolean verifyNoEventMessage(String message);

	public boolean verifyMonthButton();

	public boolean verifyWeekButton();

	public boolean verifyDayButton();

	public boolean verifyListButton();

	public boolean verifyAddButton();

	public void clickOnMonthButton();

	public void clickOnWeekButton();

	public void clickOnDayButton();

	public void clickOnListButton();

	public void clickOnAddButton();

	public void sendTextInQuickSearchTextBox(String text);

	public void clickOnDayNextButtonInRightPanel();

	public void clickOnDayPreviousButtonInRightPanel();

	public void clickOnCalenderPreviousButtonInLeftPanel();

	public void clickOnCalenderNextButtonInLeftPanel();

	public int getDefaultCount();

	public int getMyDraftsCount();

	public void hideLeftPanel();

	public void showLeftPanel();

	public boolean verifyHideLeftPanel();

	public boolean verifyShowLeftPanel();

	public void clickOnLabelsInLeftPanel(String label);

	public boolean verifyModal();

	public boolean verifyEventPage();

	public void clickOnAddButtonInAddEventModal();

	public void clickOnCancelButtonInAddEventModal();

	public void clickOnNotificationButtonInAddEventModal();

	public void selectOptionInStatusDropDownInAddEventModal(String option);

	public void clickOnStatusDropDownInAddEventModal();

	public void deSelectAllowCommentsCheckBoxInAddEventModal();

	public void selectAllowCommentsCheckBoxInAddEventModal();

	public void clickOnAttachmentsTab();

	public void clickOnDetailsTabInAddEventModal();

	public void sendTextInTitleTextBoxInAddEventModal(String text);

	public void sendTextInLocationTextBoxInAddEventModal(String location);

	public void sendTextInDescriptionTextBoxInAddEventModal(String description);

	public void selectAllDayEventCheckBoxInAddEventModal();

	public void deSelectAllDayEventCheckBoxInAddEventModal();

	public void sendTextInTagTextBoxInAddEventModal(String tag);

	public void editTagTextBoxInAddEventModal(String tag, String newTag);

	public void editContactsTextBoxInAddEventModal(String contact, String newContact);

	public void clickOnCategeoryDropDownInAddEventModal();

	public void selectCategoryInCategeoryDropDownInAddEventModal(String category);

	public void attachFileInAddEventModal(String file);

	public void clickOnStartDateCalenderIconInModal();

	public void clickOnEndDateCalenderIconInModal();

	public void clickOnStartTimeWatchIconInModal();

	public void clickOnEndTimeWatchIconInModal();

	public void sendDateInStartDateTextBox(String date);

	public void sendDateInEndDateTextBox(String date);

	public void sendStartTimeInStartTimeTextBox(String time);

	public void sendEndTimeInEndTimeTextBox(String time);

	public void addEvent(String eventName);

	public boolean verifyAutoSaveDraftMessage(String message);

	public void clickOnSaveButtonInAutoSaveDraftModal();

	public void clickOnCancelButtonInAutoSaveDraftModal();

	public boolean verifyAutoSaveMessage();

	public void clickOnEventInEventList(String eventName);

	public boolean verifyEventInEventList(String eventName);

	public void clickOnStarIconOfAnEventInEventList(String eventName);

	public void clickOnMoreActionOfAnEventInEventList(String eventName);

	public void clickOnOptionInMoreActionOfAnEvent(String eventName, String option);

	public boolean verifyStartDateTimeOfAnEventInEventList(String eventName, String dateAndTime) throws ParseException;

	public boolean verifyLocationInEventList(String eventName, String location);

	public boolean verifyCategoryInEventList(String eventName, String category);

	public boolean verifyDescriptionInEventList(String eventName, String description);

	public boolean verifyEventDateTimeInEditSection(String dateTime);

	public boolean verifyLocationInEditSection(String location);

	public boolean verifyDescriptionInEditSection(String description);

	public boolean verifyTagInEditSection(String tag);

	public boolean verifyCategoryInEditSection(String category);

	public boolean verifyContactInEditSection(String contact);

	public void clickOnLikeUnlikeEventButonInEditSection(String button);

	public void clickOnCommentButonInEditSection();

	public void sendTextInCommentTextBoxInEditSection(String text);

	public void clickOnCancelCommentButton();

	public void clickOnPostCommentButton();

	public void addCommentInEditSection(String comment);

	public void clickOnMoreActionInEditSection();

	public void clickOnAddToFavouratesIconInEditSection();

	public void clickOnLikeIconInEditSection();

	public void clickOnOptionInMoreActionInEditSection(String option);

	public void clickOnCommentButtonOfEventInEventList(String eventName);

	public void clickOnStartTimeIncrementHoursButtonInModal();

	public void clickOnEndTimeIncrementHoursButtonInModal();

	public void clickOnEndTimeDecrementHoursButtonInModal();

	public void clickOnStartTimeDecrementHoursButtonInModal();

	public void clickOnStartTimeDecrementMinutesButtonInModal();

	public void clickOnEndTimeDecrementMinutesButtonInModal();

	public void clickOnEndTimeIncrementMinutesButtonInModal();

	public void clickOnStartTimeIncrementMinutesButtonInModal();

	public void setStartTimeInModal(int hours, int minute);

	public int getStartTimeHours();

	public int getEndTimeHours();

	public int getEndTimeMinutes();

	public int getStartTimeMinutes();

	public void clickOnStartTimeHours();

	public void clickOnEndTimeHours();

	public void clickOnEndTimeMinutes();

	public void clickOnStartTimeMinutes();

	public void setEndTimeInModal(int hours, int minute);

	public boolean verifyEventIn_Day_Week_Month_View(String eventName);

	public boolean verifyEventLocationInDayView(String eventName, int timeHours);

	public boolean verifyEventLocationInWeekView(String day, String eventName, int hour);

	public boolean verifyEventLocationInMonthView(String day, String eventName);

	public void clickOnEventIn_Day_Week_Month_View(String eventName);

	public boolean verifyEventPopup();

	public boolean verifyModal(String modalName);

	public boolean verifyEventInEventPopup(String eventName);

	public void clickOnViewButtonInEventPopup();

	public void clickOnEditButtonInEventPopup();

	public String getEventPopupDateTime();

	public void clickOnCommentInEventPopup();

	public int getCommentCountInEventPopup();

	public boolean verifyEventInMyDrafts(String eventName);

	public void deleteEventInMyDraft(String eventName);

	public void sendTextInContactsTextBoxInModal(String contact);

	public boolean verifyGlobalAlertMessage(String message);

	public boolean verifyEndDateTimeOfAnEventInEventList(String eventName, String dateAndTime) throws ParseException;

	public boolean verifyEndDateTimeOfAnEventInEditEvent(String dateAndTime);

	public boolean verifyStartDateTimeOfAnEventInEditEvent(String dateAndTime);

	public void editAttachedFileInAddEventModal(String file, String newFile);

	public boolean verifyAttachmentInEditSection(String file);

	public void editCategoryInCategeoryDropDownInAddEventModal(String category, String newCategory);

	public boolean verifyDeleteModalMessage(String message);

	public void clickOnCancelButtonInDeletModal();

	public String getTextFromTitleTextBoxInAddEventModal();

	public void clickOnFavourateIconInEventList(String eventName);

	public boolean verifyStatusInMyDraft(String eventName, String status);

	public boolean verifyMyDraftsEventTable();

	public boolean verifyMyDraftsHeader();

	public void sendTextInQuickSearchTextBoxInMyDrafts(String text);

	public boolean verifyOptionsInFiterIconInMyDrafts(String option);

	public List<String> getListOfElementsInMyDrafts();

	public void verifyAllEventsStatusInMyDrafts(String status);

	public void selectOptionInFiterIconInMyDrafts(String option);

	public void clickOnFilterIcon();

	public boolean verifyLoader();

	public void sortEventsInDescendingOrderInMyDrafts();

	public void sortEventsInAscendingOrderInMyDrafts();

	public boolean verifyEventPosition(int position, String eventName);

	public void clickOnTitleCaretSign();

	public String getTitleLabelStatusInMyDrafts();

	public boolean verifyQuitButtonInDeletModal();

	public boolean verifyCancelButtonInDeletModal();

	public boolean verifyDeleteButtonInDeletModal();

	public void clickOnDeleteButtonInMyDrafts();

	public void selectCheckBoxInMyDrafts(String eventName);

	public void clickOnEventInMyDrafts(String eventName);

	public boolean verifyStatusSelectedInModal(String status);

	public boolean verifyDiscardButtonInAutoSaveDraftModal();

	public boolean verifySaveButtonInAutoSaveDraftModal();

	public boolean verifyAddToFavouratesIconInEditSection();

	public boolean waitForAutoSaveMessage();

	public boolean verifyMoreActionInEditSection();

	public void clickOnDiscardButtonInAutoSaveDraftModal();

	public boolean verifyEventInEditSection(String title);

	public void clickOnDeleteIconInMyDraft(String eventName);

	public void clickOnDeleteButtonInDeleteModal();

	public boolean verifyFavouriteIconSelectedInEventList(String eventName);

	public void deselectFavourateIconInEventList(String eventName);

	public void selectFavourateIconInEventList(String eventName);

	public boolean verifyFavouritedElementInEventFavourites(String eventName);

	public void clickOnOptionOfCommentInEditSection(String option, String comment);

	public int getCommentCountInEditSection();

	public boolean verifyCommentIconInEditSection();

	public boolean verifyActiveCommentTextBoxInEditSection();

	public boolean verifyCommentBoxRenderedTextInEditSection(String text);

	public void editTextInCommentTextBoxInEditSection(String newComment, String comment);

	public boolean verifyCommentInEditSection(String text);

	public boolean verifyReplyCommentRenderedTextInEditSection(String text);

	public void editReplyCommentTextBoxText(String reply);

	public void clickOnPostReplyButtonInEditSection();

	public boolean verifyLikeStatusTextInEditSection(String text);

	public boolean verifyLikeUnlikeEventButtonInEditSection(String button);

	public boolean verifyOptionInMoreActionOfAnEvent(String eventName, String option);

	public boolean verifyAnOptionInMoreActionInEditSection(String option);

	public boolean verifyDateIsAsLink(String date) throws ParseException;

	public boolean verifyLabelInShareModal(String label);

	public void clickOnCancelButtonInShareModal();

	public boolean verifyCancelButtonInShareModal();

	public void clickOnLabelsOnHeaderInShareModal(String header);

	public void clickOnQuitButtonInShareModal();

	public void clickOnSelectLinkButtonInShareModal();

	public void disableUseShortURLCheckBox();

	public void enableUseShortURLCheckBox();

	public boolean verifySendButtonDisabledInShareModal();

	public void clickOnSendButtonInShareModal();

	public boolean verifyOptionFromAutoSuggest(String mailID);

	public boolean verifySendButtonInShareModal();

	public String getSubjectTextBoxTextInShareModal() throws UnsupportedFlavorException, IOException;

	public void clearSubjectTextBoxInShareModal();

	public void sendTextInRecipientsTextBoxInShareModal(String text);

	public void sendTextInSubjectTextBoxInShareModal(String text);

	public boolean verifyPopUpModalText(String text);

	public void clickOnPopUpModalCloseButton();

	public void clearRecipientsTextBoxInShareModal();

	public String getLinkTextBoxTextInShareModal() throws UnsupportedFlavorException, IOException;

	public void openCopiedURL(String url);

	public String getMessageTextBoxTextInMessageTabInShareModal() throws UnsupportedFlavorException, IOException;

	public void clearMessageTextBoxTextInMessageTabInShareModal();

	public boolean verifyPostButtonDisabledInShareModal();

	public void clickOnPostButtonInShareModal();

	public void clearMessageTextBoxTextInMicroblogTabInShareModal();

	public void removeSiteFromShareWithTextBoxInShareModal(String siteName);

	public void sendTextInShareWithTextBoxInShareModal(String siteName);

	public void selectTextFromAutoSuggestInShareWithInShareModal(String siteName);

	public String getMessageTextBoxTextInMicroblogTabInShareModal() throws UnsupportedFlavorException, IOException;

	public void sendTextInMessageBoxInMicroblogTabInShareModal(String text);

	public boolean verifyPostButtonInShareModal();

	public void clickOnRSSFeedIcon();

	public void deSelectIncludeCommentsCheckBoxInModal();

	public void clickOnIncludeCommentsCheckBoxInModal();

	public void selectIncludeCommentsCheckBoxInModal();

	public void clickOnCancelButtonInModal();

	public void clickOnExportButtonInExportToPDFModal();

	public void clickOnPrintButtonInModal();

	public void clickOnEventInRSSFeed(String eventName);

	public boolean verifyEventInRSSFeed(String eventName);

	public boolean gotoEventInEventList(String eventName, String eventPosition);

	public void deleteEventInEventList(String eventName);

	public String getRssFeedLink();

	public boolean verifyDetailsSection();

	public void waitForFileGettingDownloaded();

	public boolean verifyAttachmentsTabOpenedInAddEventModal();

	public void selectEmailFromContactTextBox(String email);

	public void clickOnTodayButtonInRightPanel();

	public boolean verifyGreyMetaOfAnEventInEditEvent(String... metaData);
}
