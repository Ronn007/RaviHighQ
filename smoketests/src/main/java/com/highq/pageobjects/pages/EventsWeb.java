/**
 * 
 */
package com.highq.pageobjects.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.EventLabels;
import com.highq.pageobjects.base.EventsPage;

/**
 * @author vivek.mishra
 */
public class EventsWeb extends BannerPageWeb implements EventsPage
{
	public EventsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	String actualDay, actualMonth;

	int actualYear;

	/** Add event button */
	By addEvent_Btn = By.id("add_event");

	By list_Btn = By.id("dum_list");

	By day_Btn = By.id("dum_day");

	By week_Btn = By.id("dum_week");

	By month_Btn = By.id("dum_month");

	By quickSearch_TxtBox = By.id("eventSearchText");

	By dayNextInRightPanel_Btn = By.id("dum_next");

	By dayPreviousInRightPanel_Btn = By.id("dum_prev");

	By calendarNextInLeftPanel_Btn = By.xpath("//*[@id='eventModule_leftPanelID']//*[@class='dhx_cal_next_button']");

	By calendarPreviousInLeftPanel_Btn = By.xpath("//*[@id='eventModule_leftPanelID']//*[@class='dhx_cal_prev_button']");

	By myDraftsCount_Lbl = By.xpath("//*[@id='dum_draft']//span[contains(@class,'label label-default pull-right')]");

	By defaultCount_Lbl = By.xpath("//*[contains(@id,'cat')]//span[contains(@class,'label label-default pull-right')]");

	By showLeftPanel_Arw = By.className("greyFont tooltipShow icon-chevron-right");

	By hideLeftPanel_Arw = By.className("greyFont tooltipShow icon-chevron-left");

	By noEvent_Lbl = By.xpath("//*[contains(@class,'clearfix noResultFound margTop')]");

	By modal = By.xpath("(//*[@class='modal-content'])[last()]");

	By addInAddEventModal_Btn = By.id("addEventModal_addEventSave");

	By cancelInAddEventModal_Btn = By.id("addEventModal_eventModalCancel");

	By notificationInAddEventModal_Btn = By.className("btn btn-default dropdown-toggle notificationBtn");

	By eventPage = By.className("row");

	By detailsTabInAddEventModal = By.id("detailTabId");

	By attachmentTab = By.id("attachmentTabId");

	By allowCommentsCheckBoxInAddEventModal = By.id("event_showComment");

	By statusDropDownInAddEventModal = By.id("workFlowActionID");

	By titleInAddEventModal_TxtBox = By.id("eventTitleID");

	By locationInAddEventModal_TxtBox = By.id("event_location");

	By allDayEventInAddEventModal_ChkBox = By.id("allday");

	By contactsInAddEventModal_TxtBox = By.id("eventContact-tokenfield");

	By descriptionInAddEventModal_TxtBox = By.id("addEventCKContentID");

	By tagInAddEventModal_TxtBox = By.id("demowords-tokenfield");

	By categeoryInAddEventModal_DrpDwn = By.id("createEventModal_SelectedCategoriesCount");

	By browseButtonInAddEventModal = By.id("addEditEventAttachmentsckUploadFile");

	By startDateCalenderIconInModal = By.xpath("//*[@data-original-title='" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_STARTDATE + "']");

	By endDateCalenderIconInModal = By.xpath("//*[@data-original-title='" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_ENDDATE + "']");

	By startTimeWatchIconInModal = By.xpath("//*[@data-original-title='" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_STARTTIME + "']");

	By endTimeWatchIconInModal = By.xpath("//*[@data-original-title='label.display.site.endtime']");

	By startDateClenderTextBoxInModal = By.id("eventStartDate");

	By endDateCalenderTextBoxInModal = By.id("eventEndDate");

	By startTimeTextBox = By.id("eventStartTime");

	By endTimeTextBox = By.id("eventEndTime");

	By autoSaveDraftMessageInAutoSaveDraftModal = By.id("confirmDraftDialog_BODY");

	By discardInAutoSaveDraftModal_Btn = By.id("confirmDraftDialog_confDialogCancel");

	By saveInAutoSaveDraftModal_Btn = By.id("confirmDraftDialog_confDialogSave");

	By autoSaveMessage = By.xpath("//*[@id='eventModal_autoSaveMsg' and contains(normalize-space(text()),'" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_AUTOSAVEDAT + "')]");

	By dateAndTimeInEditSection = By.xpath("(//*[@class='clearfix']//*[contains(@class,'overflowHidden margLeft')])[1]");

	By locationInEditSection = By.xpath("(//*[@class='clearfix']//*[contains(@class,'overflowHidden margLeft')])[2]");

	By descriptionInEditSection = By.id("eventContent");

	By likeInEditSection_Btn = By.id("likeContent");

	By commentInEditSection_Btn = By.xpath("//*[contains(@id,'commentLink_')]");

	By commentInEditSection_TxtBox = By.xpath("//*[contains(@id,'addCommentField')]/p/..");

	By cancelComment_Btn = By.xpath("//*[@class='clearfix']//*[@class='btn btn-cancel']");

	By postComment_Btn = By.xpath("//*[contains(@id,'postBtn_addCommentField')]");

	By moreActionInEditSection_Btn = By.xpath("//*[contains(@class,'dropdown inlineBlock margLeft')]//*[@data-original-title='" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	By addToFavouratesIconInEditSection = By.xpath("(//*[@data-original-title='" + EventLabels.EVENT_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "'])[last()]");

	By likeIconInEditSection = By.id("eventLikeCircle");

	By startDateCalenderPrevious_Btn = By.xpath("(//div[@class='datepicker-days']//th[contains(@class,'prev')])[3]");

	By startDateCalenderNext_Btn = By.xpath("(//div[@class='datepicker-days']//th[contains(@class,'next')])[3]");

	By endDateCalenderNext_Btn = By.xpath("(//div[@class='datepicker-days']//th[contains(@class,'next')])[4]");

	By endDateCalenderPrevious_Btn = By.xpath("(//div[@class='datepicker-days']//th[contains(@class,'prev')])[4]");

	By startDateYearMonthPickerInModal = By.xpath("(//*[@class='datepicker-days']//*[@class='picker-switch'])[1]");

	By currentMonthYearInCalender = By.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[@class='picker-switch']");

	By decrementHoursInStartTimeInModal = By.xpath("(//*[@data-action='decrementHours']//*[@class='icon icon-chevron-down'])[1]");

	By decrementHoursInEndTimeInModal = By.xpath("(//*[@data-action='decrementHours']//*[@class='icon icon-chevron-down'])[2]");

	By incrementHoursInEndTimeInModal = By.xpath("(//*[@data-action='incrementHours']//*[@class='icon icon-chevron-up'])[2]");

	By incrementHoursInStartTimeInModal = By.xpath("(//*[@data-action='incrementHours']//*[@class='icon icon-chevron-up'])[1]");

	By incrementMinutesInStartTimeInModal = By.xpath("(//*[@data-action='incrementMinutes']//*[@class='icon icon-chevron-up'])[1]");

	By incrementMinutesInEndTimeInModal = By.xpath("(//*[@data-action='incrementMinutes']//*[@class='icon icon-chevron-up'])[2]");

	By decrementMinutesInEndTimeInModal = By.xpath("(//*[@data-action='decrementMinutes']//*[@class='icon icon-chevron-down'])[2]");

	By decrementMinutesInStartTimeInModal = By.xpath("(//*[@data-action='decrementMinutes']//*[@class='icon icon-chevron-down'])[1]");

	By startTimeHours = By.xpath("(//*[@data-action='showHours'])[1]");

	By endTimeHours = By.xpath("(//*[@data-action='showHours'])[2]");

	By endTimeMinutes = By.xpath("(//*[@data-action='showMinutes'])[2]");

	By startTimeMinutes = By.xpath("(//*[@data-action='showMinutes'])[1]");

	By eventPopup = By.id("eventPopoverDiv");

	By eventPopupCloseButton = By.className("close eventPopCloseID");

	By editButtonInEventPopup = By.id("eventPopoverEditLinkID");

	By viewButtonInEventPopup = By.id("eventPopoverViewLinkID");

	By eventPopupDateTime = By.id("eventPopoverTimeDurationID");

	By commentLabelInEventPopup = By.xpath("//*[@id='eventPopoverCommentID']/a");

	By globalAlertMessage = By.id("globalAlertMessageContainerMsgSpace");

	By dateTimeInEventEditSection = By.xpath("(//*[@id='listDetailView']//*[contains(@class,'overflowHidden margLeft')])[1]");

	By deleteModalMessage = By.id("collaborateCustomModalMessage");

	By deleteButtonInDeleteModal = By.id("collaborateMessageOkButton");

	By cancelButtonInDeleteModal = By.id("collaborateMessageCancelButton");

	By myDraftsTable = By.id("eventDrafttblBody");

	By myDraftsHeader = By.xpath("//*[@id='eventDraftTableID']//*[@class='wikiGridTitle fixedtblHeading']");

	By quitButtonInDeleteDraftModal = By.id("collaborateMessageCloseButton");

	By titleCaretSignInMyDrafts = By.xpath("//*[@id = 'eventDraftTableID']//*[@id='eventDrafttitle']");

	By titleLabelInMyDraftsHeader = By.xpath("//*[@id = 'eventDraftTableID']//*[@id='eventDrafttitle']/..");

	By eventLoader = By.xpath("//*[@id='collaborateCommon_imageLoader']//img");

	By filterIcon = By.xpath("//*[contains(@class,'input-group-btn dropdown')]/button");

	By myDraftsTableList = By.xpath("//*[@id='eventDrafttblBody']//tr");

	By draftQuickSearchTextBox = By.id("draftSearch");

	By deleteDraftButton = By.id("deleteDraftsBtn");

	By commentIconInEditSection = By.xpath("//*[@id='eventCommentCount']/..");

	By commentCountInEditSection = By.id("eventCommentCount");

	By commentedTextInEditSection = By.xpath("(//*[@class='ckContentArea']//p)[last()]");

	By replyRenderedText = By.xpath("(//*[contains(@id,'CKContextMention')])[last()]");

	By replyCommentTextBox = By.xpath("(//*[contains(@id,'CKContextMention')])[last()]/../..");

	By postReply_Btn = By.xpath("(//*[contains(@id,'postBtn_addCommentField')])[last()]");

	By cancelButtonInShareModal = By.id("file_module_shared_file_link_file_module_shareModal_close_btn");

	By useShortURLCheckBoxInShareModal = By.id("chkshortenURLID");

	By selectLinkButtonInShareModal = By.xpath("//*[@class='form-horizontal clearfix']//*[normalize-space(text())='Select link']");

	By quitButtonInShareModal = By.id("file_module_shared_file_link_MAIN_CLOSE_BUTTON");

	By sendButtonInShareModal = By.id("file_module_shared_file_link_file_module_shareModal_emailAndMessage_send_btn");

	By subjectTextBoxInShareModal = By.id("file_module_email_subject");

	By recipintsTextBoxInShareModal = By.xpath("//*[@class='form-horizontal clearfix']//*[normalize-space(text())='Recipients']//following::input[@class='token-input tt-input']");

	By popUpModalText = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_BODY");

	By popUpModalCloseButton = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_file_module_shareModal_close_btn");

	By noResultInAutoSuggest = By.xpath("//*[@id='emptyResult']/strong[normalize-space(text())='No results found']");

	By linkTextBoxInShareModal = By.id("file_module_copyLink_txt");

	By messageTextBoxInMessageTabInShareModal = By.id("file_module_Site_Share_PrivateMessageEditor");

	By postButtonInShareModal = By.id("file_module_shared_file_link_file_module_shareModal_microblog_post_btn");

	By messageBoxInMicroblogTabInShareModal = By.id("file_module_Site_Share_MicroblogEditor");

	By shareWithTextBoxInShareModal = By.id("file_module_Site_Share_Microblog_SiteList-tokenfield");

	By rssFeed = By.id("rssFeedID");

	By includeCommentsCheckBoxInModal = By.id("includeCommentForElseModal");

	By exportButtonInExportToPDFModal = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_printOrExportButton");

	By cancelButtonInModal = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_cancelButton");

	By printButtonInModal = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_printOrExportButton");

	By detailView = By.id("listDetailView");

	By downloading = By.id("globalProcessMessageContainerMsgSpace");

	By attachmentsTabOpened = By.xpath("//*[@id='addEditAttachmentContainerID']/div[@class='dragDropFiles']/..");

	By contactsAutoSuggestionEmail = By.xpath("// *[@class='typeahead_secondary']");

	By todayButtonInRightPanel = By.xpath(".//*[@id='dum_today' and not(contains(@class,'disabled'))]");

	WebElement element;

	List<String> draftElements = new ArrayList<String>();

	/**
	 * @author vivek.mishra
	 */
	public void clickOnLabelsInLeftPanel(String label)
	{
		findClickableElement(By.xpath("//*[@id='eventModule_leftPanelID']//*[text()='" + label.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 */
	public boolean verifyShowLeftPanel()
	{
		return (isDisplayed(showLeftPanel_Arw));
	}

	/**
	 * @author vivek.mishra
	 */
	public boolean verifyHideLeftPanel()
	{
		return (isDisplayed(hideLeftPanel_Arw));
	}

	/**
	 * @author vivek.mishra
	 *         To show the left panel
	 */
	public void showLeftPanel()
	{
		if (verifyShowLeftPanel())
			findClickableElement(showLeftPanel_Arw).click();
	}

	/**
	 * @author vivek.mishra
	 *         To hide the left panel
	 */
	public void hideLeftPanel()
	{
		if (verifyHideLeftPanel())
			findClickableElement(hideLeftPanel_Arw).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the count of my drafts from left panel My drafts label
	 */
	public int getMyDraftsCount()
	{
		return (Integer.parseInt(findVisibleElement(myDraftsCount_Lbl).getText()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the count of defaults from left panel default count label
	 */
	public int getDefaultCount()
	{
		return (Integer.parseInt(findVisibleElement(defaultCount_Lbl).getText()));
	}

	/**
	 * @author vivek.mishra
	 *         To move in next month from left side calendar
	 */
	public void clickOnCalenderNextButtonInLeftPanel()
	{
		findClickableElement(calendarNextInLeftPanel_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To move in previous month from left side calendar
	 */
	public void clickOnCalenderPreviousButtonInLeftPanel()
	{
		findClickableElement(calendarPreviousInLeftPanel_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To move previous day from right panel
	 */
	public void clickOnDayPreviousButtonInRightPanel()
	{
		scrollToTop();
		findClickableElement(dayPreviousInRightPanel_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To move next day from right panel
	 */
	public void clickOnDayNextButtonInRightPanel()
	{
		scrollToTop();
		findClickableElement(dayNextInRightPanel_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in quick search text box
	 */
	public void sendTextInQuickSearchTextBox(String text)
	{
		findVisibleElement(quickSearch_TxtBox, Speed.slow).sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click add a new event button
	 */
	public void clickOnAddButton()
	{
		element = findVisibleElement(addEvent_Btn, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on list button
	 */
	public void clickOnListButton()
	{
		scrollToTop();
		findClickableElement(list_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on day button
	 */
	public void clickOnDayButton()
	{
		scrollToTop();
		findClickableElement(day_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on week button
	 */
	public void clickOnWeekButton()
	{
		scrollToTop();
		findClickableElement(week_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on month button
	 */
	public void clickOnMonthButton()
	{
		scrollToTop();
		findClickableElement(month_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the add button
	 */
	public boolean verifyAddButton()
	{
		findVisibleElement(addEvent_Btn, Speed.slow);
		return (isDisplayed(addEvent_Btn, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the List button
	 */
	public boolean verifyListButton()
	{
		return (isDisplayed(list_Btn));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the Day button
	 */
	public boolean verifyDayButton()
	{
		return (isDisplayed(day_Btn));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the Week button
	 */
	public boolean verifyWeekButton()
	{
		return (isDisplayed(week_Btn));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the Month button
	 */
	public boolean verifyMonthButton()
	{
		return (isDisplayed(month_Btn));
	}

	/**
	 * @author vivek.mishra
	 * @param message
	 * @return
	 * 		To verify the no event message
	 */
	public boolean verifyNoEventMessage(String message)
	{
		return (findVisibleElement(noEvent_Lbl).getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the no event
	 */
	public boolean verifyNoEvent()
	{
		return (isDisplayed(noEvent_Lbl));
	}

	/**
	 * @author vivek.mishra
	 *         To click on particular date
	 *         Date should be String and in "mm/dd/yyyy" format
	 */
	public void setDateInLeftPannelCalender(String date) throws ParseException
	{
		String[] splitedDate = date.split("/");
		actualDay = splitedDate[1];
		splitedDate[1] = "01";
		date = splitedDate[0] + "/".concat(splitedDate[1]) + "/".concat(splitedDate[2]);
		if (verifyDate(date))
			findVisibleElement(By.xpath("//*[@class='dhx_cal_container dhx_mini_calendar']//td[@class=' ' or contains(@class,'dhx_now')]//*[text()='" + actualDay.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 */
	public boolean verifyDate(String date) throws ParseException
	{
		String attributeVal = findVisibleElement(By.xpath("//*[@class='dhx_cal_container dhx_mini_calendar']"), Speed.slow).getAttribute("date");
		attributeVal = attributeVal.split(" ")[0];

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Date actualDate = sdf.parse(date);
		Date currentDate = sdf.parse(attributeVal);
		int result = actualDate.compareTo(currentDate);
		while (!(result == 0))
		{
			attributeVal = findVisibleElement(By.xpath("//*[@class='dhx_cal_container dhx_mini_calendar']"), Speed.slow).getAttribute("date");
			attributeVal = attributeVal.split(" ")[0];
			currentDate = sdf.parse(attributeVal);
			result = actualDate.compareTo(currentDate);
			if (result > 0)
				clickOnCalenderNextButtonInLeftPanel();
			else if (result < 0)
				clickOnCalenderPreviousButtonInLeftPanel();
		}
		return (isDisplayed(By.xpath("//*[@class='dhx_cal_container dhx_mini_calendar']//td[@class=' ' or contains(@class,'dhx_now')]//*[text()='" + actualDay.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the modal
	 */
	public boolean verifyModal()
	{
		return (isDisplayed(modal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 */
	public boolean verifyEventPage()
	{
		return (isDisplayed(eventPage, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on Add button in Add event modal
	 */
	public void clickOnAddButtonInAddEventModal()
	{
		element = findVisibleElement(addInAddEventModal_Btn, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Cancel button in Add event modal
	 */
	public void clickOnCancelButtonInAddEventModal()
	{
		findClickableElement(cancelInAddEventModal_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Notification button in Add event modal
	 */
	public void clickOnNotificationButtonInAddEventModal()
	{
		findClickableElement(notificationInAddEventModal_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To select allow Comments check box
	 */
	public void selectAllowCommentsCheckBoxInAddEventModal()
	{
		WebElement checkBox = findVisibleElement(allowCommentsCheckBoxInAddEventModal);
		if (!checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 *         To deselect allow comments check box in add event modal
	 */
	public void deSelectAllowCommentsCheckBoxInAddEventModal()
	{
		WebElement checkBox = findVisibleElement(allowCommentsCheckBoxInAddEventModal);
		if (checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on status drop down in add event modal
	 */
	public void clickOnStatusDropDownInAddEventModal()
	{
		element = findVisibleElement(statusDropDownInAddEventModal, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select an option in Status drop down
	 */
	public void selectOptionInStatusDropDownInAddEventModal(String option)
	{
		clickOnStatusDropDownInAddEventModal();
		element = findVisibleElement(By.xpath("//*[@id='evenDropDownStatusID']//a[text()='" + option + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Details tab in Add event modal
	 */
	public void clickOnDetailsTabInAddEventModal()
	{
		findVisibleElement(detailsTabInAddEventModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Attachments tab in Add event modal
	 */
	public void clickOnAttachmentsTab()
	{
		findVisibleElement(attachmentTab, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in title text box in add event modal
	 */
	public void sendTextInTitleTextBoxInAddEventModal(String text)
	{
		element = findVisibleElement(titleInAddEventModal_TxtBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in location text box in add event modal
	 */
	public void sendTextInLocationTextBoxInAddEventModal(String location)
	{
		element = findVisibleElement(locationInAddEventModal_TxtBox, Speed.slow);
		element.clear();
		element.sendKeys(location.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in description text box in add event modal
	 */
	public void sendTextInDescriptionTextBoxInAddEventModal(String description)
	{
		element = findVisibleElement(descriptionInAddEventModal_TxtBox, Speed.slow);
		element.clear();
		element.sendKeys(description.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To select the all day event check box in add event modal
	 */
	public void selectAllDayEventCheckBoxInAddEventModal()
	{
		WebElement allDay = findVisibleElement(allDayEventInAddEventModal_ChkBox);
		if (!allDay.isSelected())
			allDay.click();
	}

	/**
	 * @author vivek.mishra
	 *         To deSelect the all day event check box in add event modal
	 */
	public void deSelectAllDayEventCheckBoxInAddEventModal()
	{
		WebElement allDay = findVisibleElement(allDayEventInAddEventModal_ChkBox);
		if (allDay.isSelected())
			allDay.click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in Tag text box in add event modal
	 */
	public void sendTextInTagTextBoxInAddEventModal(String tag)
	{
		findVisibleElement(tagInAddEventModal_TxtBox, Speed.slow).sendKeys(tag.trim() + Keys.ENTER + Keys.ESCAPE);
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To remove old and add new tag in add event modal
	 */
	public void editTagTextBoxInAddEventModal(String tag, String newTag)
	{
		findClickableElement(By.xpath("//*[@id='demowords']//following::*[@class='token']/span[normalize-space(text())='" + tag.toLowerCase() + "']//preceding-sibling::a")).click();
		findVisibleElement(tagInAddEventModal_TxtBox).sendKeys(newTag + Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To remove old contact and add new contact in add event modal
	 */
	public void editContactsTextBoxInAddEventModal(String contact, String newContact)
	{
		findClickableElement(By.xpath("//*[@class='token']//*[normalize-space()='" + contact.trim() + "']//preceding-sibling::a")).click();
		findVisibleElement(contactsInAddEventModal_TxtBox).sendKeys(newContact + Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 *         To click on categeory drop down in add event modal
	 */
	public void clickOnCategeoryDropDownInAddEventModal()
	{
		element = findVisibleElement(categeoryInAddEventModal_DrpDwn, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To Select option in categeory drop down in add event modal
	 */
	public void selectCategoryInCategeoryDropDownInAddEventModal(String category)
	{
		clickOnCategeoryDropDownInAddEventModal();
		WebElement cat = findVisibleElement(By.xpath("(//*[@class='selectDrop dropdown dropdown open']//*[normalize-space()='" + category.trim() + "'])[last()]/input"), Speed.slow);
		if (!cat.isSelected())
			cat.click();
		clickOnCategeoryDropDownInAddEventModal();
	}

	/**
	 * @author vivek.mishra
	 * @param file
	 *        To attach file in add event module
	 */
	public void attachFileInAddEventModal(String file)
	{
		clickOnAttachmentsTab();
		verifyAttachmentsTabOpenedInAddEventModal();
		String path = TestBaseSetup.currentDir + "\\testData\\" + file.trim();
		findPresentElement(browseButtonInAddEventModal, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 *         To click on start date calender icon in modal
	 */
	public void clickOnStartDateCalenderIconInModal()
	{
		findVisibleElement(startDateCalenderIconInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on End date calender icon in modal
	 */
	public void clickOnEndDateCalenderIconInModal()
	{
		findVisibleElement(endDateCalenderIconInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on start time watch icon in modal
	 */
	public void clickOnStartTimeWatchIconInModal()
	{
		findVisibleElement(startTimeWatchIconInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on end time watch icon in modal
	 */
	public void clickOnEndTimeWatchIconInModal()
	{
		findVisibleElement(endTimeWatchIconInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To send the start date in start date text box
	 * @modified on 17/02/2018
	 */
	public void sendDateInStartDateTextBox(String date)
	{
		element = findVisibleElement(startDateClenderTextBoxInModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(date.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param date
	 *        To send end date in end date text box
	 * @modified on 17/02/2018
	 */
	public void sendDateInEndDateTextBox(String date)
	{
		element = findVisibleElement(endDateCalenderTextBoxInModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(date.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param time
	 *        To send start time in start time text box
	 * @modified on 17/02/2018
	 */
	public void sendStartTimeInStartTimeTextBox(String time)
	{
		element = findVisibleElement(startTimeTextBox, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(time.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param time
	 *        To send end time in end time text box
	 * @modified on 17/02/2018
	 */
	public void sendEndTimeInEndTimeTextBox(String time)
	{
		element = findVisibleElement(endTimeTextBox, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(time.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To add a simple event with event title parameter only
	 */
	public void addEvent(String eventName)
	{
		clickOnAddButton();
		verifyModal();
		sendTextInTitleTextBoxInAddEventModal(eventName);
		selectCategoryInCategeoryDropDownInAddEventModal("Default");
		clickOnAddButtonInAddEventModal();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the auto save draft message in auto save modal
	 */
	public boolean verifyAutoSaveDraftMessage(String message)
	{
		return (findVisibleElement(autoSaveDraftMessageInAutoSaveDraftModal, Speed.slow).getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on save button in auto save draft modal
	 */
	public void clickOnSaveButtonInAutoSaveDraftModal()
	{
		findClickableElement(saveInAutoSaveDraftModal_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in auto save draft modal
	 */
	public void clickOnCancelButtonInAutoSaveDraftModal()
	{
		findClickableElement(discardInAutoSaveDraftModal_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		Automated auto save message
	 */
	public boolean verifyAutoSaveMessage()
	{
		return (isDisplayed(autoSaveMessage, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on an event in event list
	 */
	public void clickOnEventInEventList(String eventName)
	{
		scrollToTop();
		moveToElement(By.xpath("(//*[contains(@class,'eventlistTitle')]//*[normalize-space()='" + eventName.trim() + "'])[last()]"));
		element = findVisibleElement(By.xpath("(//*[contains(@class,'eventlistTitle')]//*[normalize-space()='" + eventName.trim() + "'])[last()]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the event in event list
	 */
	public boolean verifyEventInEventList(String eventName)
	{
		return (isDisplayed(By.xpath("(//*[contains(@class,'eventlistTitle')]//*[normalize-space()='" + eventName.trim() + "'])[last()]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on star icon of an event in event list
	 */
	public void clickOnStarIconOfAnEventInEventList(String eventName)
	{
		findClickableElement(By.xpath("//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[@class='icon icon-star']")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on more action of an event in event list
	 */
	public void clickOnMoreActionOfAnEventInEventList(String eventName)
	{
		element = findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[@class='icon icon-actions'])[last()]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @param option
	 *        To click on option of an event in more action
	 */
	public void clickOnOptionInMoreActionOfAnEvent(String eventName, String option)
	{
		clickOnMoreActionOfAnEventInEventList(eventName);
		findClickableElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[normalize-space()='" + getUserData(option.trim()) + "'])[last()]"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the start date and time of an event in event list
	 * @throws ParseException
	 */
	public boolean verifyStartDateTimeOfAnEventInEventList(String eventName, String dateAndTime) throws ParseException
	{
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd MMM yyyy HH:mm");
		String currentDate = findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//following-sibling::div//*[contains(@class,'overflowHidden')])[1]")).getText().split("-")[0];
		currentDate = currentDate.replaceAll(",", "");
		Date date1 = dateFormate.parse(currentDate);
		String[] date = dateAndTime.split("/");
		dateAndTime = date[1] + " " + date[0] + " " + date[2];
		Date date2 = dateFormate.parse(dateAndTime);
		return (date1.equals(date2));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @param location
	 * @return
	 * 		To verify the loaction of an event in event List
	 */
	public boolean verifyLocationInEventList(String eventName, String location)
	{
		return (findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//following-sibling::div//*[contains(@class,'overflowHidden')]/a[@href='#'])[last()]")).getText().trim().equals(location.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @param location
	 * @return
	 * 		To verify the category of an event in event List
	 */
	public boolean verifyCategoryInEventList(String eventName, String category)
	{
		return (findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//following-sibling::div//*[contains(@class,'overflowHidden')]/a[contains(@href,'javascript')])[1]"), Speed.slow).getText().trim().equals(category.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @param description
	 * @return
	 * 		To verify the description in event list
	 */
	public boolean verifyDescriptionInEventList(String eventName, String description)
	{
		return (findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']/..//div[contains(@class,'ckContentArea margTop')])[last()]"), Speed.slow).getText().trim().equals(description.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param dateTime
	 * @return
	 * 		To verify the add event date and time in edit section page
	 */
	public boolean verifyEventDateTimeInEditSection(String dateTime)
	{
		return (findVisibleElement(dateAndTimeInEditSection).getText().trim().equals(dateTime.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify location in edit section page
	 */
	public boolean verifyLocationInEditSection(String location)
	{
		return (findVisibleElement(locationInEditSection).getText().trim().equals(location.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param description
	 * @return
	 * 		To verify the description in edit section
	 */
	public boolean verifyDescriptionInEditSection(String description)
	{
		return (findVisibleElement(descriptionInEditSection).getText().trim().equals(description.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param tag
	 * @return
	 * 		To verify the tag in edit section
	 */
	public boolean verifyTagInEditSection(String tag)
	{
		return (isDisplayed(By.xpath("//*[@id='tags']/..//a[normalize-space()='" + tag.trim().toLowerCase() + "']")));
	}

	/**
	 * @author vivek.mishra
	 * @param category
	 * @return
	 * 		To verify category in edit section
	 */
	public boolean verifyCategoryInEditSection(String category)
	{
		return (isDisplayed(By.xpath("//*[@id='categorys']/..//a[normalize-space()='" + category.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 * @param contact
	 * @return
	 * 		To verify contact in edit section
	 */
	public boolean verifyContactInEditSection(String contact)
	{
		return (isDisplayed(By.xpath("//*[@class='contactContainer editBlock-content']//span[normalize-space()='" + contact.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 *         To click on like button in edit section
	 */
	public void clickOnLikeUnlikeEventButonInEditSection(String button)
	{
		findClickableElement(By.xpath("//*[@id='likeContent' and normalize-space(text())='" + getUserData(button.trim()) + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on comment button in edit section
	 */
	public void clickOnCommentButonInEditSection()
	{
		findClickableElement(commentInEditSection_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in comment text box
	 */
	public void sendTextInCommentTextBoxInEditSection(String text)
	{
		findVisibleElement(commentInEditSection_TxtBox, Speed.slow).click();
		findVisibleElement(commentInEditSection_TxtBox, Speed.slow).sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel comment button
	 */
	public void clickOnCancelCommentButton()
	{
		findClickableElement(cancelComment_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on save comment button
	 */
	public void clickOnPostCommentButton()
	{
		findClickableElement(postComment_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 *        To add a comment
	 */
	public void addCommentInEditSection(String comment)
	{
		clickOnCommentButonInEditSection();
		sendTextInCommentTextBoxInEditSection(comment);
		clickOnPostCommentButton();
	}

	/**
	 * @author vivek.mishra
	 *         To click on more action in edit section
	 */
	public void clickOnMoreActionInEditSection()
	{
		element = findVisibleElement(moreActionInEditSection_Btn, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Add to favourates icon in edit section
	 */
	public void clickOnAddToFavouratesIconInEditSection()
	{
		findClickableElement(addToFavouratesIconInEditSection).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on like icon in edit section
	 */
	public void clickOnLikeIconInEditSection()
	{
		findClickableElement(likeIconInEditSection, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on an option in more action in edit section
	 */
	public void clickOnOptionInMoreActionInEditSection(String option)
	{
		clickOnMoreActionInEditSection();
		element = findVisibleElement(By.xpath("//*[@class='icon icon-actions dropdown-toggle']/..//a[normalize-space()='" + option.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on an event comment button in event list
	 */
	public void clickOnCommentButtonOfEventInEventList(String eventName)
	{
		findClickableElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "'])[last()]/..//following-sibling::div//a[contains(text(),'" + EventLabels.EVENT_COMMENTS + "')]")).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on increment hours button in start time modal
	 */
	public void clickOnStartTimeIncrementHoursButtonInModal()
	{
		findClickableElement(incrementHoursInStartTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on increment hours button in end time modal
	 */
	public void clickOnEndTimeIncrementHoursButtonInModal()
	{
		findClickableElement(incrementHoursInEndTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on decrement hours button in end time modal
	 */
	public void clickOnEndTimeDecrementHoursButtonInModal()
	{
		findClickableElement(decrementHoursInEndTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on decrement hours button in start time modal
	 */
	public void clickOnStartTimeDecrementHoursButtonInModal()
	{
		findClickableElement(decrementHoursInStartTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on decrement minutes button in start time modal
	 */
	public void clickOnStartTimeDecrementMinutesButtonInModal()
	{
		findClickableElement(decrementMinutesInStartTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on decrement minutes button in end time modal
	 */
	public void clickOnEndTimeDecrementMinutesButtonInModal()
	{
		findClickableElement(decrementMinutesInEndTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on increment minutes button in end time modal
	 */
	public void clickOnEndTimeIncrementMinutesButtonInModal()
	{
		findClickableElement(incrementMinutesInEndTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on increment minutes button in start time modal
	 */
	public void clickOnStartTimeIncrementMinutesButtonInModal()
	{
		findClickableElement(incrementMinutesInStartTimeInModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @param hours
	 * @param minute
	 *        To select start time in modal
	 */
	public void setStartTimeInModal(int hours, int minute)
	{
		clickOnStartTimeWatchIconInModal();
		if (hours != getStartTimeHours())
		{
			clickOnStartTimeHours();
			if (isDisplayed(By.xpath("(//*[@data-action='selectHour'])[1]//*[normalize-space()='" + hours + "']"), Speed.slow))
				findClickableElement(By.xpath("(//*[@data-action='selectHour'])[1]//*[normalize-space()='" + hours + "']"), Speed.slow).click();
			else
			{
				if (isDisplayed(By.xpath("(//*[@data-action='selectHour'])[1]//*[normalize-space()='0" + hours + "']"), Speed.slow))
					findClickableElement(By.xpath("(//*[@data-action='selectHour'])[1]//*[normalize-space()='0" + hours + "']"), Speed.slow).click();
			}
		}

		int resultMinute = minute - getStartTimeMinutes();
		boolean status = true;
		while (status)
		{
			if (resultMinute != 0)
			{
				if (resultMinute > 0)
				{
					while (resultMinute > 0)
					{
						clickOnStartTimeIncrementMinutesButtonInModal();
						resultMinute--;
					}
				}
				else if (resultMinute < 0)
				{
					while (resultMinute < 0)
					{
						clickOnStartTimeDecrementMinutesButtonInModal();
						resultMinute++;
					}
				}
			}
			else
				status = false;
		}
		clickOnStartTimeWatchIconInModal();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the start time hours
	 */
	public int getStartTimeHours()
	{
		return (Integer.parseInt(findVisibleElement(startTimeHours, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the end time hours
	 */
	public int getEndTimeHours()
	{
		return (Integer.parseInt(findVisibleElement(endTimeHours, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the end time minutes
	 */
	public int getEndTimeMinutes()
	{
		return (Integer.parseInt(findVisibleElement(endTimeMinutes, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the start time minutes
	 */
	public int getStartTimeMinutes()
	{
		return (Integer.parseInt(findVisibleElement(startTimeMinutes, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on start time hours
	 */
	public void clickOnStartTimeHours()
	{
		findClickableElement(startTimeHours, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on end time hours
	 */
	public void clickOnEndTimeHours()
	{
		findClickableElement(endTimeHours, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on end time minutes
	 */
	public void clickOnEndTimeMinutes()
	{
		findClickableElement(endTimeMinutes, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on end time minutes
	 */
	public void clickOnStartTimeMinutes()
	{
		findClickableElement(startTimeMinutes, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param hours
	 * @param minute
	 *        To select End time in modal
	 */
	public void setEndTimeInModal(int hours, int minute)
	{
		clickOnEndTimeWatchIconInModal();
		if (hours != getEndTimeHours())
		{
			clickOnEndTimeHours();
			if (isDisplayed(By.xpath("(//*[@data-action='selectHour'])[2]//*[normalize-space()='" + hours + "']"), Speed.slow))
				findClickableElement(By.xpath("(//*[@data-action='selectHour'])[2]//*[normalize-space()='" + hours + "']"), Speed.slow).click();
			else
			{
				if (isDisplayed(By.xpath("(//*[@data-action='selectHour'])[2]//*[normalize-space()='0" + hours + "']"), Speed.slow))
					findClickableElement(By.xpath("(//*[@data-action='selectHour'])[2]//*[normalize-space()='0" + hours + "']"), Speed.slow).click();
			}
		}

		int resultMinute = minute - getEndTimeMinutes();
		boolean status = true;
		while (status)
		{
			if (resultMinute != 0)
			{
				if (resultMinute > 0)
				{
					while (resultMinute > 0)
					{
						clickOnEndTimeIncrementMinutesButtonInModal();
						resultMinute--;
					}
				}
				else if (resultMinute < 0)
				{
					while (resultMinute < 0)
					{
						clickOnEndTimeDecrementMinutesButtonInModal();
						resultMinute++;
					}
				}
			}
			else
				status = false;
		}
		clickOnEndTimeWatchIconInModal();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the event in week view
	 */
	public boolean verifyEventIn_Day_Week_Month_View(String eventName)
	{
		return (isDisplayed(By.xpath("(//*[@id='highqscheduler']//*[normalize-space(text())='" + eventName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the event in their respective scale in day view
	 */
	public boolean verifyEventLocationInDayView(String eventName, int hour)
	{
		Point eventPosition = findVisibleElement(By.xpath("//*[@class='ev_title_ref' and text()='" + eventName.trim() + "']")).getLocation();
		Point scaleHourPosition = findVisibleElement(By.xpath("//*[@class='dhx_scale_hour' and contains(text(),'" + hour + "')]")).getLocation();
		int heightDifference = eventPosition.getY() - scaleHourPosition.getY();
		if (heightDifference < 50 && heightDifference > 0)
			return true;
		else
			return false;
	}

	/**
	 * @author vivek.mishra
	 *         To verify the event loaction in week view
	 */
	public boolean verifyEventLocationInWeekView(String day, String eventName, int hour)
	{
		Point eventPosition = findVisibleElement(By.xpath("//*[@class='ev_title_ref' and text()='" + eventName.trim() + "']")).getLocation();
		Point scaleHourPosition = findVisibleElement(By.xpath("//*[@class='dhx_scale_hour' and contains(text(),'" + hour + "')]")).getLocation();
		Point dayPosition = findVisibleElement(By.xpath("//*[@class='dhx_cal_header']//*[@class='dhx_scale_bar' and contains(text(),'" + getUserData(day) + "')]")).getLocation();
		int widthDifference = eventPosition.getX() - dayPosition.getX();
		int heightDifference = eventPosition.getY() - scaleHourPosition.getY();
		if (heightDifference < 50 && heightDifference > 0 && widthDifference < 120 && widthDifference > 0)
			return true;
		else
			return false;
	}

	/**
	 * @author vivek.mishra
	 *         To verify the event location in month view
	 */
	public boolean verifyEventLocationInMonthView(String day, String eventName)
	{
		Point eventPosition = findVisibleElement(By.xpath("//*[@class='ev_title_ref' and text()='" + eventName.trim() + "']")).getLocation();
		Point dayPosition = findVisibleElement(By.xpath("//*[@class='dhx_cal_header']//*[@class='dhx_scale_bar' and contains(text(),'" + getUserData(day) + "')]")).getLocation();
		int widthDifference = eventPosition.getX() - dayPosition.getX();
		if (widthDifference < 70 && widthDifference > 0)
			return true;
		else
			return false;
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on an event in day week and month view
	 */
	public void clickOnEventIn_Day_Week_Month_View(String eventName)
	{
		scrollToTop();
		moveToElement(By.xpath("//*[@class='ev_title_ref' and text()='" + eventName.trim() + "']"));
		findClickableElement(By.xpath("//*[@class='ev_title_ref' and text()='" + eventName.trim() + "']")).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the event popup appears after clicking on an event
	 */
	public boolean verifyEventPopup()
	{
		return (isDisplayed(eventPopup, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param modalName
	 * @return
	 * 		To verify the modal
	 */
	public boolean verifyModal(String modalName)
	{
		return (isDisplayed(By.xpath("//*[@class='modal-title' and normalize-space()='" + modalName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To close the event popup
	 */
	public void clickOnClosePopupButton()
	{
		findClickableElement(eventPopup).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the event in event popup
	 */
	public boolean verifyEventInEventPopup(String eventName)
	{
		return (isDisplayed(By.xpath("//*[@id='eventPopoverTitleID']/a[normalize-space()='" + eventName.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 *         To click on view button in event popup
	 */
	public void clickOnViewButtonInEventPopup()
	{
		findClickableElement(viewButtonInEventPopup).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on edit button in event popup
	 */
	public void clickOnEditButtonInEventPopup()
	{
		findClickableElement(editButtonInEventPopup).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the event popup date and and time
	 */
	public String getEventPopupDateTime()
	{
		return (findVisibleElement(eventPopupDateTime).getText());
	}

	/**
	 * @author vivek.mishra
	 *         To click on comment in event popup
	 */
	public void clickOnCommentInEventPopup()
	{
		findClickableElement(commentLabelInEventPopup).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To get the count of comment in event popup
	 */
	public int getCommentCountInEventPopup()
	{
		return (Integer.parseInt(findVisibleElement(commentLabelInEventPopup).getText().split(" ")[0]));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the event in my drafts
	 */
	public boolean verifyEventInMyDrafts(String eventName)
	{
		return (isDisplayed(By.xpath("//*[@id='eventDraftTableID']//*[normalize-space()='" + eventName.trim() + "']/a")));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To delete an event in my drafts
	 */
	public void deleteEventInMyDraft(String eventName)
	{
		findClickableElement(By.xpath("//*[@id='eventDraftTableID']//*[normalize-space()='" + eventName.trim() + "']//following-sibling::td/a")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param contact
	 *        To send text in contacts text box
	 */
	public void sendTextInContactsTextBoxInModal(String contact)
	{
		findVisibleElement(contactsInAddEventModal_TxtBox, Speed.slow).sendKeys(contact.trim() + Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 *         To verify the text in global alert message
	 * @param message
	 * @return
	 */
	public boolean verifyGlobalAlertMessage(String message)
	{
		return (findVisibleElement(globalAlertMessage, Speed.slow).getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To verify the end date and time of an event in event list
	 * @throws ParseException
	 */
	public boolean verifyEndDateTimeOfAnEventInEventList(String eventName, String dateAndTime) throws ParseException
	{
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd MMM yyyy HH:mm");
		String currentDate = findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//following-sibling::div//*[contains(@class,'overflowHidden')])[1]")).getText().split("-")[1];
		currentDate = currentDate.replaceAll(",", "");
		Date date1 = dateFormate.parse(currentDate);
		String[] date = dateAndTime.split("/");
		dateAndTime = date[1] + " " + date[0] + " " + date[2];
		Date date2 = dateFormate.parse(dateAndTime);
		return (date1.equals(date2));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the end date and time of an event in edit event
	 */
	public boolean verifyEndDateTimeOfAnEventInEditEvent(String dateAndTime)
	{
		String currentDate = findVisibleElement(dateTimeInEventEditSection, Speed.slow).getText().split("-")[1];
		String date1 = currentDate.split(" GMT")[0];
		currentDate = date1.replaceAll(",", "");
		return (dateAndTime.trim().equals(currentDate.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the end date and time of an event in edit event
	 */
	public boolean verifyStartDateTimeOfAnEventInEditEvent(String dateAndTime)
	{
		String currentDate = findVisibleElement(dateTimeInEventEditSection, Speed.slow).getText().split("-")[0];
		currentDate = currentDate.replaceAll(",", "");
		return (dateAndTime.trim().equals(currentDate.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To Select option in categeory drop down in add event modal
	 */
	public void editCategoryInCategeoryDropDownInAddEventModal(String category, String newCategory)
	{
		clickOnCategeoryDropDownInAddEventModal();
		WebElement cat = findVisibleElement(By.xpath("(//*[@class='selectDrop dropdown dropdown open']//*[normalize-space()='" + category.trim() + "'])[last()]/input"));
		if (cat.isSelected())
			cat.click();
		cat = findVisibleElement(By.xpath("(//*[@class='selectDrop dropdown dropdown open']//*[normalize-space()='" + newCategory.trim() + "'])[last()]/input"));
		if (!cat.isSelected())
			cat.click();
		clickOnCategeoryDropDownInAddEventModal();
	}

	/**
	 * @author vivek.mishra
	 * @param file
	 * @return
	 * 		To verify the attachment in edit event section
	 */
	public boolean verifyAttachmentInEditSection(String file)
	{
		return (isDisplayed(By.xpath("(//*[@title='" + file.trim() + "'])[last()]")));
	}

	/**
	 * @author vivek.mishra
	 * @param file
	 *        To attach file in add event module
	 */
	public void editAttachedFileInAddEventModal(String file, String newFile)
	{
		clickOnAttachmentsTab();
		verifyAttachmentsTabOpenedInAddEventModal();
		String path = TestBaseSetup.currentDir + "\\testData\\" + newFile;
		element = findVisibleElement(By.xpath("//*[@class='postUpload']//*[@class='TruncateTxt linkblack' and normalize-space(text())='" + file.trim() + "']//preceding-sibling::a"), Speed.slow);
		element.click();
		findPresentElement(browseButtonInAddEventModal).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 * @param message
	 * @return
	 */
	public boolean verifyDeleteModalMessage(String message)
	{
		return (findVisibleElement(deleteModalMessage, Speed.slow).getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in delte modal
	 */
	public void clickOnCancelButtonInDeletModal()
	{
		findClickableElement(cancelButtonInDeleteModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 */
	public String getTextFromTitleTextBoxInAddEventModal()
	{
		return (findVisibleElement(titleInAddEventModal_TxtBox, Speed.slow).getAttribute("value").trim());
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on favourate icon of an event in event list
	 * @creation date 22/01/2018
	 */
	public void clickOnFavourateIconInEventList(String eventName)
	{
		findClickableElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[contains(@class,'icon icon-star')])[last()]"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify My drafts event table
	 * @creation date 23/01/2018
	 */
	public boolean verifyMyDraftsEventTable()
	{
		findVisibleElement(myDraftsTable, Speed.slow);
		return (isDisplayed(myDraftsTable, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the status in my drafts page
	 * @param eventName
	 * @return
	 * 		Creation date 22/01/2018
	 */
	public boolean verifyStatusInMyDraft(String eventName, String status)
	{
		return (isDisplayed(By.xpath("//*[@id='eventDrafttblBody']//*[contains(@id,'draft')]//a[normalize-space(text())='" + eventName.trim() + "']/..//following-sibling::td[normalize-space(text())='" + getUserData(status.trim()) + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         Verify Add to favourates icon in edit section
	 *         Creation date 22/01/2018
	 */
	public boolean verifyAddToFavouratesIconInEditSection()
	{
		return (isDisplayed(addToFavouratesIconInEditSection, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the save button in auto save draft modal
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifySaveButtonInAutoSaveDraftModal()
	{
		return (isDisplayed(saveInAutoSaveDraftModal_Btn));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the cancel button in auto save draft modal
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifyDiscardButtonInAutoSaveDraftModal()
	{
		return (isDisplayed(discardInAutoSaveDraftModal_Btn));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifyStatusSelectedInModal(String status)
	{
		return (findVisibleElement(statusDropDownInAddEventModal).getText().trim().equals(status.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		To click an event in my drafts
	 * @created on 22/01/2018
	 */
	public void clickOnEventInMyDrafts(String eventName)
	{
		findClickableElement(By.xpath("(//*[@id='eventDraftTableID']//*[normalize-space()='" + eventName.trim() + "']/a)[1]")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To select events check box in my drafts
	 * @created on 22/01/2018
	 */
	public void selectCheckBoxInMyDrafts(String eventName)
	{
		element = findVisibleElement(By.xpath("(//*[@id='eventDrafttblBody']//a[normalize-space(text())='" + eventName.trim() + "'])[1]/..//preceding-sibling::td/input"), Speed.slow);
		if (!element.isSelected())
			element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click an delete button on top in my drafts
	 * @created on 22/01/2018
	 */
	public void clickOnDeleteButtonInMyDrafts()
	{
		findClickableElement(deleteDraftButton).click();
	}

	/**
	 * @author vivek.mishra
	 *         To verify delet button in delte modal
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifyDeleteButtonInDeletModal()
	{
		return (isDisplayed(deleteButtonInDeleteModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify cancel button in delte modal
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifyCancelButtonInDeletModal()
	{
		return (isDisplayed(cancelButtonInDeleteModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify quit button in delte modal
	 * @return
	 * @created on 22/01/2018
	 */
	public boolean verifyQuitButtonInDeletModal()
	{
		return (isDisplayed(quitButtonInDeleteDraftModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the current state up or down
	 * @creation date 22/01/2018
	 */
	public String getTitleLabelStatusInMyDrafts()
	{
		if (findVisibleElement(titleLabelInMyDraftsHeader).getAttribute("class").equals("dropup"))
			return ("Caret up");
		else
			return ("Caret down");
	}

	/**
	 * @author vivek.mishra
	 * @creation date 22/01/2018
	 */
	public void clickOnTitleCaretSign()
	{
		findClickableElement(titleCaretSignInMyDrafts, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param position
	 * @param eventName
	 * @return
	 * @creation date 22/01/2018
	 */
	public boolean verifyEventPosition(int position, String eventName)
	{
		String event = findVisibleElement(By.xpath("(//*[@id='eventDraftTableID']//*[contains(@id,'draft')])[" + position + "]//a[contains(@class,'block overflowHidden fontsize')]")).getText();
		return (event.trim().equals(eventName.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To sort the my draft events in ascending order
	 * @creation date 22/01/2018
	 */
	public void sortEventsInAscendingOrderInMyDrafts()
	{
		clickOnTitleCaretSign();
		if (getTitleLabelStatusInMyDrafts().equals("Caret down"))
			clickOnTitleCaretSign();
	}

	/**
	 * @author vivek.mishra
	 *         To sort the my draft events in descending order
	 * @creation date 19/01/2018
	 */
	public void sortEventsInDescendingOrderInMyDrafts()
	{
		clickOnTitleCaretSign();
		if (getTitleLabelStatusInMyDrafts().equals("Caret up"))
			clickOnTitleCaretSign();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the loader
	 * @creation date 19/01/2018
	 */
	public boolean verifyLoader()
	{
		return (isDisplayed(eventLoader));
	}

	/**
	 * @author vivek.mishra
	 *         To click on top right corner filter button in my drafts
	 * @creation date 22/01/2018
	 */
	public void clickOnFilterIcon()
	{
		element = findVisibleElement(filterIcon, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select an option in filter icon in my drafts
	 * @creation date 22/01/2018
	 */
	public void selectOptionInFiterIconInMyDrafts(String option)
	{
		clickOnFilterIcon();
		findClickableElement(By.xpath("//*[@class='input-group']//*[@class='dropdown-menu pull-right']//a[normalize-space(text())='" + getUserData(option.trim()) + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param status
	 *        To verify the status of element availiable after applying filter in my drafts
	 * @creation date 19/01/2018
	 */
	public void verifyAllEventsStatusInMyDrafts(String status)
	{
		status = status.trim();
		draftElements.clear();
		draftElements = getListOfElementsInMyDrafts();
		for (int i = 0; i < draftElements.size(); i++)
			Assert.assertTrue(verifyStatusInMyDraft(draftElements.get(i), status));
	}

	/**
	 * @author vivek.mishra
	 *         To get all elemets availible in my drafts
	 * @return
	 * @creation date 19/01/2018
	 */
	public List<String> getListOfElementsInMyDrafts()
	{
		List<WebElement> elements = driver.findElements(myDraftsTableList);
		for (int i = 1; i <= elements.size(); i++)
			draftElements.add(findVisibleElement(By.xpath("//*[@id='eventDrafttblBody']//tr[" + i + "]//a[contains(@class,'block overflowHidden fontsize')]")).getText().trim());
		return (draftElements);
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To verify an option in filter icon in my drafts
	 * @creation date 22/01/2018
	 */
	public boolean verifyOptionsInFiterIconInMyDrafts(String option)
	{
		return (isDisplayed(By.xpath("//*[@class='input-group']//*[@class='dropdown-menu pull-right']//a[normalize-space(text())='" + getUserData(option.trim()) + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in quick search text box in my drafts
	 * @creation date 22/01/2018
	 */
	public void sendTextInQuickSearchTextBoxInMyDrafts(String text)
	{
		element = findVisibleElement(draftQuickSearchTextBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the my drafts header
	 * @creation date 23/01/2018
	 */
	public boolean verifyMyDraftsHeader()
	{
		findVisibleElement(myDraftsHeader, Speed.slow);
		return (isDisplayed(myDraftsHeader));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		Automated auto save message
	 */
	public boolean waitForAutoSaveMessage()
	{
		element = findVisibleElement(autoSaveMessage, Speed.slow);
		return (isDisplayed(autoSaveMessage));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 23/01/2018
	 */
	public boolean verifyMoreActionInEditSection()
	{
		return (isDisplayed(moreActionInEditSection_Btn, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in auto save draft modal
	 * @creation date 23/01/2018
	 */
	public void clickOnDiscardButtonInAutoSaveDraftModal()
	{
		findClickableElement(discardInAutoSaveDraftModal_Btn).click();
	}

	/**
	 * @author vivek.mishra
	 * @param title
	 * @return
	 * 		To verify the event title in an event page
	 * @creation date 23/01/2018
	 */
	public boolean verifyEventInEditSection(String title)
	{
		findVisibleElement(By.xpath("//*[@class='edit-section']//*[normalize-space()='" + title.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@class='edit-section']//*[normalize-space()='" + title.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To delete an event in my drafts
	 * @creation date 23/01/2018
	 */
	public void clickOnDeleteIconInMyDraft(String eventName)
	{
		findClickableElement(By.xpath("(//*[@id='eventDraftTableID']//*[normalize-space()='" + eventName.trim() + "']//following-sibling::td/a)[1]")).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on delet button in delte modal
	 * @creation date 23/01/2018
	 */
	public void clickOnDeleteButtonInDeleteModal()
	{
		element = findVisibleElement(deleteButtonInDeleteModal, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To select favorite icon in event list
	 * @creation date 24/01/2018
	 */
	public void selectFavourateIconInEventList(String eventName)
	{
		if (!verifyFavouriteIconSelectedInEventList(eventName))
			clickOnFavourateIconInEventList(eventName);
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To select favorite icon in event list
	 * @creation date 24/01/2018
	 */
	public void deselectFavourateIconInEventList(String eventName)
	{
		if (verifyFavouriteIconSelectedInEventList(eventName))
			clickOnFavourateIconInEventList(eventName);
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return
	 * 		Toverify the favourate icon selected or not
	 * @creation date 24/01/2018
	 */
	public boolean verifyFavouriteIconSelectedInEventList(String eventName)
	{
		return (findVisibleElement(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[contains(@class,'icon icon-star')])[last()]"), Speed.slow).getAttribute("class").equals("icon icon-star-selected"));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the favourited element in event favorites
	 * @creation date 24/01/2018
	 */
	public boolean verifyFavouritedElementInEventFavourites(String eventName)
	{
		return (isDisplayed(By.xpath("(//*[contains(@class,'eventlistTitle margBott')]/a[normalize-space(text())='" + eventName.trim() + "'])[last()]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the visibility
	 *         To verify the comment icon on top right corner in edit section
	 * @creation date 24/01/2018
	 */
	public boolean verifyCommentIconInEditSection()
	{
		findVisibleElement(commentIconInEditSection, Speed.slow);
		return (isDisplayed(commentIconInEditSection));
	}

	/**
	 * @author vivek.mishra
	 * @return the count of the comments
	 *         To verify the comment icon on top right corner in edit section
	 * @creation date 24/01/2018
	 */
	public int getCommentCountInEditSection()
	{
		return (Integer.parseInt(findVisibleElement(commentCountInEditSection, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To click an option of comment in edit section
	 * @creation date 24/01/2018
	 */
	public void clickOnOptionOfCommentInEditSection(String option, String comment)
	{
		element = findVisibleElement(By.xpath("(//*[@id='commentMeta']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "'])[last()]/../../..//preceding-sibling::a[normalize-space(text())='" + getUserData(option.trim()) + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return true if Comment box will be in edit mode
	 * @creation date 26/01/2018
	 */
	public boolean verifyActiveCommentTextBoxInEditSection()
	{
		return (findVisibleElement(commentInEditSection_TxtBox, Speed.slow).equals(driver.switchTo().activeElement()));
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @return the matching status of rendered text
	 * @creation date 26/01/2018
	 */
	public boolean verifyCommentBoxRenderedTextInEditSection(String text)
	{
		return (findVisibleElement(commentInEditSection_TxtBox, Speed.slow).getText().equals(text.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in comment text box
	 * @creation date 26/01/2018
	 */
	public void editTextInCommentTextBoxInEditSection(String newComment, String comment)
	{
		element = findVisibleElement(By.xpath("//*[contains(@id,'addCommentField')]/p[normalize-space(text())='" + comment.trim() + "']/.."), Speed.slow);
		element.clear();
		element.sendKeys(newComment);
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @return the matching status of comment in edit section
	 * @creation date 26/01/2018
	 */
	public boolean verifyCommentInEditSection(String text)
	{
		return (isDisplayed(By.xpath("//*[@class='ckContentArea']//p[normalize-space(text())='" + text.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the rendered text in reply comment text box
	 * @creation date 26/01/2018
	 */
	public boolean verifyReplyCommentRenderedTextInEditSection(String text)
	{
		return (findVisibleElement(replyRenderedText).getText().equalsIgnoreCase(text));
	}

	/**
	 * @author vivek.mishra
	 * @param reply
	 * @creation date 29/01/2018
	 */
	public void editReplyCommentTextBoxText(String reply)
	{
		element = findVisibleElement(replyCommentTextBox);
		element.clear();
		element.sendKeys(reply);
	}

	/**
	 * @author vivek.mishra
	 *         To click on post reply button
	 * @creation date 29/01/2018
	 */
	public void clickOnPostReplyButtonInEditSection()
	{
		findClickableElement(postReply_Btn, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @return the matching status
	 * @creation date 29/01/2018
	 */
	public boolean verifyLikeStatusTextInEditSection(String text)
	{
		return (isDisplayed(By.xpath("//*[normalize-space(text())='" + text.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the like unlike event button in edit section
	 * @creation date 29/01/2018
	 */
	public boolean verifyLikeUnlikeEventButtonInEditSection(String button)
	{
		return (isDisplayed(By.xpath("//*[@id='likeContent' and normalize-space(text())='" + getUserData(button.trim()) + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @param option
	 *        To click on option of an event in more action
	 * @creation date 02/02/2018
	 */
	public boolean verifyOptionInMoreActionOfAnEvent(String eventName, String option)
	{
		return (isDisplayed(By.xpath("(//*[contains(@class,'eventList')]//*[normalize-space()='" + eventName.trim() + "']//preceding-sibling::*//a[normalize-space()='" + getUserData(option.trim()) + "'])[last()]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify an option in more action in edit section
	 * @creation date 05/02/2018
	 */
	public boolean verifyAnOptionInMoreActionInEditSection(String option)
	{
		return (isDisplayed(By.xpath("//*[@class='icon icon-actions dropdown-toggle']/..//a[normalize-space()='" + option.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on particular date
	 *         Date should be String and in "mm/dd/yyyy" format
	 * @creation date 06/02/2018
	 */
	public boolean verifyDateIsAsLink(String date) throws ParseException
	{
		String[] splitedDate = date.split("/");
		setDateInLeftPannelCalender(date);
		actualDay = splitedDate[1];
		return (isDisplayed(By.xpath("//*[contains(@class,'dhx_month_head dhx_year_event') and normalize-space(text())='" + actualDay.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param label to verify
	 * @return the visibility of label
	 * @creation date 06/02/2018
	 */
	public boolean verifyLabelInShareModal(String label)
	{
		return (isDisplayed(By.xpath("//*[@class='form-horizontal clearfix']//*[normalize-space(text())='" + label.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To clickOn cancel button in share modal
	 * @creation date 06/02/2018
	 */
	public void clickOnCancelButtonInShareModal()
	{
		findClickableElement(cancelButtonInShareModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return the visibility of cancel button in share modal
	 * @creation date 06/02/2018
	 */
	public boolean verifyCancelButtonInShareModal()
	{
		return (isDisplayed(cancelButtonInShareModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To enable the use short URL check box in share modal
	 * @creation date 06/02/2018
	 */
	public void enableUseShortURLCheckBox()
	{
		element = findVisibleElement(useShortURLCheckBoxInShareModal, Speed.slow);
		if (!element.isSelected())
			element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To disable the use short URL check box in share modal
	 * @creation date 06/02/2018
	 */
	public void disableUseShortURLCheckBox()
	{
		element = findVisibleElement(useShortURLCheckBoxInShareModal, Speed.slow);
		if (element.isSelected())
			element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on select link button in share modal
	 * @creation date 06/02/2018
	 */
	public void clickOnSelectLinkButtonInShareModal()
	{
		findClickableElement(selectLinkButtonInShareModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on quit button in share modal
	 * @creation date 06/02/2018
	 */
	public void clickOnQuitButtonInShareModal()
	{
		findClickableElement(quitButtonInShareModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param header to select
	 * @creation date 06/02/2018
	 */
	public void clickOnLabelsOnHeaderInShareModal(String header)
	{
		findClickableElement(By.xpath("//*[@class='nav nav-pills']//a[normalize-space(text())='" + getUserData(header.trim()) + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on send button in share modal
	 * @creation date 07/02/2018
	 */
	public void clickOnSendButtonInShareModal()
	{
		findClickableElement(sendButtonInShareModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return the status of send button enable/disable
	 * @creation date 07/02/2018
	 */
	public boolean verifySendButtonDisabledInShareModal()
	{
		return (findPresentElement(sendButtonInShareModal, Speed.slow).getAttribute("disabled") != null);
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 * @creation date 07/02/2018
	 */
	public boolean verifyOptionFromAutoSuggest(String mailID)
	{
		clearRecipientsTextBoxInShareModal();
		sendTextInRecipientsTextBoxInShareModal(mailID);
		if (isDisplayed(noResultInAutoSuggest, Speed.slow))
			return false;
		if (isDisplayed(share_recipientHover, Speed.slow))
			element = findVisibleElement(share_recipientHover, Speed.slow);
		return ((element.getText().trim().equals(mailID.trim().toLowerCase())));
	}

	/**
	 * @author vivek.mishra
	 *         To verify send button in share modal
	 * @creation date 07/02/2018
	 */
	public boolean verifySendButtonInShareModal()
	{
		return (isDisplayed(sendButtonInShareModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To clear the subject text box in share modal
	 * @creation date 07/02/2018
	 */
	public void clearSubjectTextBoxInShareModal()
	{
		findVisibleElement(subjectTextBoxInShareModal, Speed.slow).clear();
	}

	/**
	 * @author vivek.mishra
	 * @return the text availiable in subject text box
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 07/02/2018
	 */
	public String getSubjectTextBoxTextInShareModal() throws UnsupportedFlavorException, IOException
	{
		element = findVisibleElement(subjectTextBoxInShareModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.CONTROL + "c");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String text = (String) clipboard.getData(DataFlavor.stringFlavor);
		return text.replace("  ", " ").trim();
	}

	/**
	 * @author vivek.mishra
	 * @param text to send
	 * @creation date 07/02/2018
	 */
	public void sendTextInRecipientsTextBoxInShareModal(String text)
	{
		findVisibleElement(recipintsTextBoxInShareModal, Speed.slow).sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To send text in subject text box in share modal
	 * @creation date 07/02/2018
	 */
	public void sendTextInSubjectTextBoxInShareModal(String text)
	{
		findVisibleElement(subjectTextBoxInShareModal, Speed.slow).sendKeys(text.trim());
		;
	}

	/**
	 * @author vivek.mishra
	 * @param text to be verified
	 * @return
	 * @creation date 07/02/2018
	 */
	public boolean verifyPopUpModalText(String text)
	{
		return (findVisibleElement(popUpModalText, Speed.slow).getText().trim().equalsIgnoreCase(text.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To close the popup modal
	 * @creation date 07/02/2018
	 */
	public void clickOnPopUpModalCloseButton()
	{
		findClickableElement(popUpModalCloseButton, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text to send
	 * @creation date 07/02/2018
	 */
	public void clearRecipientsTextBoxInShareModal()
	{
		findVisibleElement(recipintsTextBoxInShareModal, Speed.slow).clear();
	}

	/**
	 * @author vivek.mishra
	 * @return the text availiable in link text box
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 08/02/2018
	 */
	public String getLinkTextBoxTextInShareModal() throws UnsupportedFlavorException, IOException
	{
		element = findVisibleElement(linkTextBoxInShareModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.CONTROL + "c");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String text = (String) clipboard.getData(DataFlavor.stringFlavor);
		return text;
	}

	/**
	 * @author vivek.mishra
	 *         To open an URL in new tab
	 * @creation date 08/02/2018
	 */
	public void openCopiedURL(String url)
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
		driver.get(url);
	}

	/**
	 * @author vivek.mishra
	 * @return the text availiable in message text box in message tab in share modal
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 08/02/2018
	 */
	public String getMessageTextBoxTextInMessageTabInShareModal() throws UnsupportedFlavorException, IOException
	{
		element = findVisibleElement(messageTextBoxInMessageTabInShareModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.CONTROL + "c");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String text = (String) clipboard.getData(DataFlavor.stringFlavor);
		return text;
	}

	/**
	 * @author vivek.mishra
	 *         To clear the message text box in message tab in share modal
	 * @creation date 08/02/2018
	 */
	public void clearMessageTextBoxTextInMessageTabInShareModal()
	{
		findVisibleElement(messageTextBoxInMessageTabInShareModal, Speed.slow).clear();
	}

	/**
	 * @author vivek.mishra
	 * @return the status of post button enable/disable
	 * @creation date 07/02/2018
	 */
	public boolean verifyPostButtonDisabledInShareModal()
	{
		return (findPresentElement(postButtonInShareModal, Speed.slow).getAttribute("disabled") != null);
	}

	/**
	 * @author vivek.mishra
	 *         To click on post button in share modal
	 * @creation date 07/02/2018
	 */
	public void clickOnPostButtonInShareModal()
	{
		findClickableElement(postButtonInShareModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To clear the message text box in message tab in share modal
	 * @creation date 08/02/2018
	 */
	public void clearMessageTextBoxTextInMicroblogTabInShareModal()
	{
		findVisibleElement(messageBoxInMicroblogTabInShareModal, Speed.slow).clear();
	}

	/**
	 * @author vivek.mishra
	 *         To remove the site from share with text box in share modal
	 * @creation date 08/02/2018
	 */
	public void removeSiteFromShareWithTextBoxInShareModal(String siteName)
	{
		findClickableElement(By.xpath("//*[@class='token-label' and normalize-space(text())='" + siteName.trim() + "']//preceding-sibling::a"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To select event from autosuggest in sharewith text box in share modal
	 * @creation date 09/02/2018
	 */
	public void selectTextFromAutoSuggestInShareWithInShareModal(String siteName)
	{
		sendTextInShareWithTextBoxInShareModal(siteName);
		if (isDisplayed(By.xpath("//*[@class='typeahead_primary']//*[normalize-space(text())='" + siteName.trim() + "']"), Speed.slow))
			findClickableElement(By.xpath("//*[@class='typeahead_primary']//*[normalize-space(text())='" + siteName.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 *        To send the text in share with text box in share modal
	 * @creation date 09/02/2018
	 */
	public void sendTextInShareWithTextBoxInShareModal(String siteName)
	{
		findVisibleElement(shareWithTextBoxInShareModal, Speed.slow).sendKeys(siteName.trim());
	}

	/**
	 * @author vivek.mishra
	 * @return the text availiable in message text box in microblog tab in share modal
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 08/02/2018
	 */
	public String getMessageTextBoxTextInMicroblogTabInShareModal() throws UnsupportedFlavorException, IOException
	{
		element = findVisibleElement(messageBoxInMicroblogTabInShareModal, Speed.slow);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.CONTROL + "c");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String text = (String) clipboard.getData(DataFlavor.stringFlavor);
		return text;
	}

	/**
	 * @author vivek.mishra
	 *         To send text in message text box in microblog tab in share modal
	 * @creation date 08/02/2018
	 */
	public void sendTextInMessageBoxInMicroblogTabInShareModal(String text)
	{
		findVisibleElement(messageBoxInMicroblogTabInShareModal, Speed.slow).clear();
	}

	/**
	 * @author vivek.mishra
	 *         To verify post button in share modal
	 * @creation date 09/02/2018
	 */
	public boolean verifyPostButtonInShareModal()
	{
		return (isDisplayed(postButtonInShareModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on RSS feed icon
	 * @creation date 12/02/2018
	 */
	public void clickOnRSSFeedIcon()
	{
		element = findVisibleElement(rssFeed, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To select the inclucde comments checkbox in export to pdf modal
	 * @creation date 13/02/2018
	 */
	public void selectIncludeCommentsCheckBoxInModal()
	{
		element = findVisibleElement(includeCommentsCheckBoxInModal, Speed.slow);
		if (!element.isSelected())
			clickOnIncludeCommentsCheckBoxInModal();
	}

	/**
	 * @author vivek.mishra
	 *         To click on inclucde comments check box in export to pdf modal
	 * @creation date 13/02/2018
	 */
	public void clickOnIncludeCommentsCheckBoxInModal()
	{
		findClickableElement(includeCommentsCheckBoxInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To deselect the inclucde comments checkbox in export to pdf modal
	 * @creation date 13/02/2018
	 */
	public void deSelectIncludeCommentsCheckBoxInModal()
	{
		element = findVisibleElement(includeCommentsCheckBoxInModal, Speed.slow);
		if (element.isSelected())
			clickOnIncludeCommentsCheckBoxInModal();
	}

	/**
	 * @author vivek.mishra
	 *         To click on export button in export to pdf modal
	 * @creation date 13/02/2018
	 */
	public void clickOnExportButtonInExportToPDFModal()
	{
		findClickableElement(exportButtonInExportToPDFModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in export to pdf modal
	 * @creation date 13/02/2018
	 */
	public void clickOnCancelButtonInModal()
	{
		findClickableElement(cancelButtonInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on print button in modal
	 * @creation date 13/02/2018
	 */
	public void clickOnPrintButtonInModal()
	{
		findClickableElement(printButtonInModal, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 * @return the visibility of an event in rss feed page
	 * @creation date 13/02/2018
	 */
	public boolean verifyEventInRSSFeed(String eventName)
	{
		return (isDisplayed(By.xpath("//*[@id='item']//*[normalize-space(text())='" + eventName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To click on event in RSS feed
	 * @creation date 13/02/2018
	 */
	public void clickOnEventInRSSFeed(String eventName)
	{
		element = findVisibleElement(By.xpath("//*[@id='item']//*[normalize-space(text())='" + eventName.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To goto particular event in event list page
	 * @creation date 14/02/2018
	 */
	public boolean gotoEventInEventList(String eventName, String eventPosition)
	{
		if (verifyEventInEventList(eventName))
			return true;
		else
		{
			if (eventPosition.toLowerCase().equals("previous"))
				clickOnDayPreviousButtonInRightPanel();
			else
				clickOnDayNextButtonInRightPanel();
			gotoEventInEventList(eventName, eventPosition);
			return true;
		}
	}

	/**
	 * @author vivek.mishra
	 * @param eventName
	 *        To delete an event from event list
	 * @creation date 16/02/2018
	 */
	public void deleteEventInEventList(String eventName)
	{
		clickOnOptionInMoreActionOfAnEvent(eventName, EventLabels.EVENT_DELETE);
		verifyModal(EventLabels.EVENT_DELETE_MODAL);
		clickOnDeleteButtonInDeleteModal();
	}

	/**
	 * @author vivek.mishra
	 *         To get the link of link that will open after clicking on that
	 * @creation date 17/02/2018
	 */
	public String getRssFeedLink()
	{
		element = findPresentElement(rssFeed, Speed.slow);
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
		act.sendKeys(Keys.ESCAPE);
		String rssLink = element.getAttribute("href");
		return (rssLink);
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the details section page
	 * @creation date 14/03/2018
	 */
	public boolean verifyDetailsSection()
	{
		findVisibleElement(detailView, Speed.slow);
		return (isDisplayed(detailView, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To wait untill file is getting downloaded
	 * @creation date 26/03/2018
	 */
	public void waitForFileGettingDownloaded()
	{
		if (isDisplayed(downloading, Speed.slow))
			while (isDisplayed(downloading))
				;
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the tab is opened
	 * @creation date 03/04/2018
	 */
	public boolean verifyAttachmentsTabOpenedInAddEventModal()
	{
		findVisibleElement(attachmentsTabOpened, Speed.slow);
		return (isDisplayed(attachmentsTabOpened, Speed.slow));
	}

	public void selectEmailFromContactTextBox(String email)
	{
		findVisibleElement(contactsInAddEventModal_TxtBox, Speed.slow).sendKeys(email.trim());

		if (isDisplayed(share_recipientHover, Speed.slow))
		{
			WebElement recipientHover = findVisibleElement(contactsAutoSuggestionEmail, Speed.slow);
			if (recipientHover.getText().trim().equals(email.trim()))
			{
				recipientHover.click();
			}
		}
		else
		{
			System.err.println(email + " not found.");
		}
	}

	/**
	 * @author jyoti.raj
	 *         Create : 18th May 2018
	 *         Updated :
	 */
	public void clickOnTodayButtonInRightPanel()
	{
		scrollToTop();
		findVisibleElement(todayButtonInRightPanel, Speed.slow).click();
	}

	/**
	 * Verify grey meta of an event"(available under event Title)
	 * 
	 * @param metaData
	 *        meta data to verify
	 * @author dheeraj.rajput
	 * @return true
	 *         if greyMeta contains expected meta data
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyGreyMetaOfAnEventInEditEvent(String... metaData)
	{
		String greyMeta = findVisibleElement(dateTimeInEventEditSection, Speed.slow).getText().trim();
		for (int i = 0; i < metaData.length; i++)
		{
			if (!greyMeta.contains(metaData[i].trim()))
			{
				return false;
			}
		}
		return true;
	}
}
