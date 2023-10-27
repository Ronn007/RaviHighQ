package com.highq.pageobjects.base;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.highq.base.CollaborateLabel.TaskHeaderActionButtons;
import com.highq.pagedata.TaskAddDataPage;

public interface TasksPage extends BannerPage {
	public void clickHeaderViewButton() throws InterruptedException;

	public void clickListFromGroupMenu() throws IOException, InterruptedException;

	public void deleteAllSavedFilters() throws InterruptedException;

	public void clickHeaderFilterButton() throws InterruptedException;

	public void clickHeaderGroupButton() throws InterruptedException;

	public void clickCardViewFromViewMenu() throws IOException, InterruptedException;

	public void clickPriorityFromViewMenu() throws IOException, InterruptedException;

	public void clickStatusFromGroupMenu() throws IOException, InterruptedException;

	public void clickAssigneeFromGroupMenu() throws IOException, InterruptedException;

	public void clickDueDateFromGroupMenu() throws IOException, InterruptedException;

	public void clickPriorityFromGroupMenu() throws IOException, InterruptedException;

	public void clickNoneFromGroupMenu() throws IOException, InterruptedException;

	public void clickHeaderAddButton() throws InterruptedException;

	public void clickOnAddNewListLink() throws IOException;

	public void setTaskTitle(String title);

	public void setTaskDescription(String description);

	public void setTaskDueDate(String dueDate);

	public void setTaskAssignees(String... assignees);

	public void setTaskAssignees(String assignees);

	public void attachFileInTask(String filePath) throws InterruptedException;

	public void setPriority(String priority);

	public void setList(String list);

	public void setStatus(String status);

	public void setTag(String tag);

	public void clickOnAddToAddReminder();

	public void setReminder(int remainderDay);

	public void clickOnAddReminder();

	public void clickOnCancelReminder();

	public void clickOnFullScreen();

	public void clickOnAddInAddTaskModal();

	public void clickOnCancelInModal() throws InterruptedException;

	public void clickOnMoreActionInModal();

	public void deleteAllTasks();

	public void selectTask(String task);

	public boolean verifyAllTasksTab();

	public boolean verifyTaskVisibility(String task) throws InterruptedException;

	public boolean verifyAssignToMeTab();

	public boolean verifyCreatedByMeTab();

	public boolean verifyFavouritesTab();

	public boolean verifyHeaderAddButton();

	public boolean verifyHeaderViewButton();

	public boolean verifyHeaderGroupButton();

	public boolean verifyHeaderFilterButton();

	public boolean verifySearchField();

	public boolean verifyGlobalFullScreenIcon();

	public boolean verifyGlobalMoreScreenIcon();

	public boolean verifyIsFullScreen();

	public boolean verifyViewTaskTitle(String taskTitle);

	public boolean verifyViewTaskDescription(String taskDescription);

	public boolean verifyViewTaskDetailsStatus(String taskStatus);

	public boolean verifyViewTaskDetailsPriority(String taskPriority);

	public boolean verifyViewTaskDetailsDueDate(String taskDuedate);

	public boolean verifyViewTaskDetailsList(String taskList);

	public boolean verifyViewTaskDetailsAssignee(String... taskAssignees);

	public boolean verifyViewTaskTags(String... taskTags);

	public boolean verifyViewTaskReminder(int... taskRemainder);

	public boolean verifyAttachmentInViewTask(String taskAttachment);

	public void setTaskAddData(Map<String, String> addTask) throws InterruptedException;

	public void addTask(TaskAddDataPage add) throws IOException, InterruptedException;

	public boolean verifyViewTaskDetails(TaskAddDataPage add, int total) throws InterruptedException;

	public boolean verifyViewTaskDetailsviaMap(Map<String, String> map) throws InterruptedException;

	public boolean verifyViewTaskTitle(String title, String newTitle) throws IOException;

	public String dateConversion(String date, String format);

	public void clickOnCloseInViewTask();

	public boolean saveCurrentFilters(String string) throws IOException, InterruptedException;

	public boolean verifyAddListCancelButton();

	public boolean verifyAddListSubmittButtonDisable();

	public boolean verifyAddListSubmittButtonEnable();

	public void setTaskList(String list);

	public void clickAddListSubmittButtonEnable();

	public void clickAddListCancelButton();

	public boolean verifyAddNewListLink() throws IOException;

	public boolean verifycolumnViewTaskViewOptionShare();

	public boolean verifycolumnViewTaskViewOptionExportTOPdf();

	public boolean verifycolumnViewTaskViewOptionPrintPreview();

	public boolean verifycolumnViewTaskViewOptionDelete();

	public boolean verifySavedFilters(String filterName) throws IOException, InterruptedException;

	public boolean verifySavedFilterMenu(String collapse, String filterName) throws IOException, InterruptedException;

	public void saveCurrentFilters(String string, String options) throws IOException, InterruptedException;

	public boolean verifySelectedSiteInAddTask(String site);

	public boolean verifySelectedStatusInAddTask(String status);

	public void clickOnCancelButtonOfSaveFilterPopUp() throws InterruptedException;

	public int visibleTaskCount();

	public boolean verifyStatusColorForColumnView(String statusName, String taskTitle, String color) throws IOException;

	public String gethashValueOfColor(By xpath, String cssProperty);

	public boolean verifySortingTaskList(List<String> actualPriority, String groupingAction);

	public boolean verifySingleListGroup(String list) throws IOException, InterruptedException;

	public boolean verifyFilter(String collapse, String filterSuboption) throws IOException;

	public boolean verifyAddListTextBox();

	public void clickOnAddColumnButton();

	public void enterColumnTitleInAddColumn(String colTitle);

	public void clickOnAddColumnSaveButton();

	public void clickOnAddColumnCancelButton();

	public void setSite(String site);

	public void clickOnCreatedByMeTab() throws InterruptedException;

	public boolean verifyDisabledClearFilters() throws IOException;

	public boolean verifyTaskActionHeaderFilterMenu(String collapse, String filterSuboption)
			throws IOException, InterruptedException;

	public void selectFilterOption(String option, String filterSuboption) throws IOException, InterruptedException;

	public void clickTaskActionHeaderFilterMenu(String filterSuboption) throws IOException;

	public boolean verifyFilterTokens(String filterByMenu, String tokensString) throws IOException;

	public void clickOnClearFilters() throws IOException, InterruptedException;

	public boolean verifyMultipleTasks(List<String> tasksTitle) throws IOException;

	public void clickOnAddTaskButtonInModel() throws InterruptedException;

	public void scrollToTop() throws InterruptedException;

	public void editTask(Map<String, String> list) throws IOException, InterruptedException;

	public boolean verifyAddedListFromAddTaskModal(String list) throws IOException;

	public boolean verifyTaskListVisibilityOnEditTaskPanel(String tasktitle, String taskListName)
			throws IOException, InterruptedException;

	public boolean verifyAddAnotherTaskLink() throws IOException;

	public void clickOnAddAnotherTask(TaskHeaderActionButtons action) throws IOException;

	public boolean verifyEmptyFieldInAddAnother() throws IOException;

	public boolean verifyDefaultValueInAddTaskModel() throws IOException;

	public boolean verifyTaskListOption(String optionValue);

	public void closeTask();

	public void clickOnDeleteTask(String taskTitle) throws InterruptedException;

	public boolean verifyDeleteMessage(String message);

	public void cancelDeleteOperation();

	public void confirmDeleteOperation();

	public boolean noTaskIspresent();

	public void editTaskTitle(String title);

	public void editTaskList(String listOption);

	public void editTaskStatus(String desiredStatus);

	public void editTaskDueDate(String desiredDueDate);

	public void editTaskAssignee(String assignee);

	public void editTaskPriority(String desiredPriority);

	public void editTaskDescription(String desiredDescription);

	public void editTaskTag(String desiredTag);

	public void editReminder(String reminder);

	public void editAttachment(String attachment);

	public boolean verifyTaskTitle(String title);

	public boolean verifyTaskList(String listOption);

	public boolean verifyTaskStatus(String desiredStatus);

	public boolean verifyTaskDueDate(String desiredDueDate);

	public boolean verifyTaskAssignee(String assignee);

	public boolean verifyTaskPriority(String desiredPriority);

	public boolean verifyTaskDescription(String desiredDescription);

	public boolean verifyTag(String tagName);

	public boolean verifyReminder(String reminder);

	public boolean verifyTaskData(Map<String, String> list) throws IOException, InterruptedException;

	public boolean addComment(String comment) throws InterruptedException;

	public void scrollToComment(String user, String comment) throws InterruptedException;

	public boolean verifyVisiblityOfCommentLikeLink(String user, String comment);

	public boolean verifyVisiblityOfCommentUnLikeLink(String user, String comment);

	public int getCommentLikeCount(String user, String comment);

	public boolean verifyCommentLinkCountIncremented(String user, String comment, int currentCount);

	public boolean verifyVisiblityOfCommentReplyLink(String user, String comment);

	public void clickOnCommentReplyLink(String user, String comment);

	public boolean verifyVisiblityOfCommentEditLink(String user, String comment);

	public boolean verifyVisiblityOfCommentDeleteLink(String user, String comment);

	public void clickCommentDeleteLink(String user, String comment);

	public boolean verifyDeleteCommentMessage();

	public void cancelDeleteCommentMessageBox();

	public void confirmDeleteCommentMessageBox();

	public boolean verifyVisibilityOfComment(String user, String comment);

	public boolean deleteComment(String user, String comment) throws InterruptedException;

	public boolean editComment(String user, String comment, String commentToEdit);

	public boolean replyComment(String user, String comment, String commentToReply) throws InterruptedException;

	public boolean verifyRepliedComment(String user, String comment);

	public void clickCommentEditLink(String user, String comment);

	public boolean verifyEnabledCommentBox();

	public boolean verifyCommentCancelButtonIsPresent() throws InterruptedException;

	public boolean verifyCommentPostButtonIsPresent() throws InterruptedException;

	public boolean verifyUserInReplyCommentBox(String user, String comment) throws InterruptedException;

	public int getCommentCount();

	public boolean verifyCommentCountIncremented(int currentCount);

	public boolean verifyCommentCountDecreased(int currentCount);

	public boolean verifyAttachementInAddTask(String fileName);

	public boolean completeTask(String taskName) throws InterruptedException;

	public void clearSearch();

	public boolean verifyFavouriteTaskIconIsSelected();

	public void addTaskToFavourites();

	public void clickFavouritesTabInTask();

	public void searchTask(String taskName);

	public void removeTaskFromFavourites();

	public void clickAllTasksTab();

	public boolean verifyTaskStartsWith(String startsWithText);

	public void clickMoreActionOfTask() throws InterruptedException;

	public void clickShareTask() throws InterruptedException;

	public boolean verifyActiveTask(String taskTitle);

	public boolean verifyCardViewRenderInTaskModule();

	public boolean verifyTimelineViewRenderInTaskModule();

	public boolean verifyTaskDetailsOrderInAddTaskModal(List<String> list);

	public boolean verifyDisableAddButtonVisibilityInAddTaskModal();

	public boolean verifyCancleButtonVisibilityInAddTaskModal();

	public boolean verifyXButtonVisibilityInAddTaskModal();

	public void clickOnCloseButtonInAddTaskModal();

	public boolean verifyAddTaskModalVisibility();

	public boolean verifyCKEditorInDiscription();

	public boolean verifyDefaultValueOfPriorityInAddTaskModal(String priorityName);

	public boolean verifyDefaultValueOfListInAddTaskModal(String listName);

	public boolean verifyDefaultValueOfStatusInAddTaskModal(String statusName);

	public void setTaskStartDate(String startDate);

	public boolean verifyInvalidStartDateMessage(String message);

	public boolean verifyInvalidDueDateMessage(String message);

	public boolean verifyTaskStartDate(String desiredStartDate);

	public void clickOnTaskInTimelineView(String taskName);

	public void clickTimelineViewFromViewMenu() throws IOException, InterruptedException;

	public boolean verifyDefaultViewInTimelineView(String defaultView);

	public boolean verifyTaskVisibilityInTimelineView(String taskName);

	public void clickTimelineViewPreviousButton();

	public void clickTimelineViewNextButton();

	public void clickTimelineViewTwoWeekViewButton();

	public void clickTimelineViewMonthViewButton();

	public void clickTimelineViewWeekViewButton();

	public void addTaskInTimelineViewWithDoubleClick(String date);

	public WebElement getTaskColumnInTimelineView(String date);

	public void clickTimelineViewTodayButton();

	public boolean verifyTaskTimelineViewTodayDate(String todayDate);

	public boolean verifyTaskDetailsLabelVisibility(String taskDetailsLabel);

	public void clickAssignToMeTabInTasks();

	public boolean verifyTaskColorForTimelineView(String taskTitle, String color);

	public String getStartDateInViewTaskModal();

	public String getDueDateInViewTaskModal();

	public void clickColumnViewFromViewMenu();

	public void clickOnCloseButtonInViewTaskInCardOrTimelineView();

	public boolean verifyTaskVisibilityInCardView(String columnTitle, String taskTitle) throws IOException;

	public int getColumnNumberForCardView(String columnTitle) throws IOException;

	public boolean verifyAddColumnButton();

	public void setDateFromDatePicker(String date);

	public boolean verifyDateFromDatePicker(String date);

	public int getCurrentYearInCalenderModal();

	public String getCurrentMonthInCalenderModal();

	public void clickOnYearMonthPickerInCalenderModal();

	public int getCurrentYearFromMonthPicker();

	public void clickOnNextInMonthPicker();

	public void clickOnPreviousInMonthPicker();

	public void clickOnMonthInMonthPicker(String month);

	public boolean verifyTaskInTimelineViewWithRespectiveDate(String date, String taskTitle);

	public boolean verifyViewTaskDetailsSite(String taskSite);

	public void editTaskStartDate(String desiredStartDate);

	public boolean verifyTaskStartAndDueDate();

	public boolean verifyTaskInParticularView(String startDate, String endDate, String taskTitle)
			throws IOException, ParseException;

	public boolean verifyCloseButtonInViewTask();

	public boolean verifyViewTaskModalVisibility();

	public boolean verifyInvalidStartDateInViewTaskModal(String message);

	public boolean verifyInvalidDueDateInEditTaskModal(String message);

	public void clickOnNoDueDateFromDatePickerInViewTaskModal();

	public void clickOnNoStartDateFromDatePickerInViewTaskModal();

	public void clickOnEditIconOfTaskStartDate();

	public void clickOnCancelIconOfStartDateInViewTaskModal();

	public void clickOnEditIconOfTaskDueDate();

	public void clickOnCancelIconOfDueDateInViewTaskModal();

	public void clickOnSubmitIconOfStartDateInViewTaskModal();

	public void clickOnSubmitIconOfDueDateInViewTaskModal();

	public boolean verifyStatusColorInViewTaskModal(String taskTitle, String statusName, String color);

	public boolean verifyXButtonVisibilityInViewTaskModal();

	public void clickOnStartDateDatePickerInAddTaskModal();

	public void clickOnDueDateDatePickerInAddTaskModal();

	public void clickOnStartDateDatePickerInViewTaskModal();

	public void clickOnDueDateDatePickerInViewTaskModal();

	public void setDateFromDatePickerForStartDateInAddTaskModal(String date);

	public void setDateFromDatePickerForDueDateInAddTaskModal(String date);

	public void setDateFromDatePickerForStartDateInViewTaskModal(String date);

	public void setDateFromDatePickerForDueDateInViewTaskModal(String date);

	public boolean verifyStartDateFromDatePickerInAddTaskModal(String date);

	public boolean verifyDueDateFromDatePickerInAddTaskModal(String date);

	public boolean verifyStartDateFromDatePickerInViewTaskModal(String date);

	public boolean verifyDueDateDatePickerInViewTaskModal(String date);

	public boolean isDateAvailableInBetweenGivenDates(Date startDate, Date endDate, Date date);

	public String[] getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();

	public boolean verifyAllPoints(int taskSp, int taskEP, int weekColSP, int weekColEP);

	public boolean verifyToolTipOfTaskInTimelineView(String taskName);

	public void clickAllTasksInTimelineView();

	public void clickAssignToMeTasksInTimelineView();

	public void clickCreatedByMeTasksInTimelineView();

	public void clickFavouritesTasksInTimelineView();

	public boolean verifyAttachmentTabVisibilityInAddTask();

	public boolean verifyEnableAddButtonVisibilityForReminderInAddTaskModal();

	public boolean verifyReminderTitle(String reminder);

	public boolean verifyRemoveButtonVisibilityForReminder(String reminderName);

	public boolean verifyMailSubject(String mailSub);

	public void clickOnTaskAttachment();

	public boolean verifyModalMessage(String message);

	public void clickOnModalButton(String buttonName);

	public boolean verifyModal(String modalName);

	public boolean verifyModal();

	public boolean verifyModalButton(String buttonName);

	void importExcelFile(String fileName);

	void selectMoreActionOption(String option);

	void clickOnCloseButtonOfPrintPreview();

	boolean verifyTaskVisiblityInPrintPreview(String task);

	void selectMoreActionOptionForIndividualTask(String option);

	boolean verifyTaskVisiblityInPrintPreviewForIndividualTask(String task);


	public void setReminder(int remainderDay, String remiderType);

	public void getMailForTask(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject,
			String emailMessage);

	public boolean verifyReminderDropdownInAddTaskModal(String dateType);

	public void clickOnCloseButtonForReminderInAddTaskModal();

	public boolean verifyErrorMessageForDuplicateReminder(String message);

	public void clickOnRemoveButtonForParticularReminder(String reminderName);

	public boolean verifyReminderTitleInViewTaskModal(String reminder);

	public void clickOnRemoveButtonForParticularReminderInViewTaskModal(String reminderName);

	public boolean verifyRemoveButtonForParticularReminderInViewTaskModal(String reminderName);

	public boolean verifyAddButtonForReminderInViewTaskModal();

	public void clickOnAddButtonForReminderInViewTaskModal();

	public boolean verifyReminderDropdownInViewTaskModal(String dateType);

	public void clickOnCloseButtonForReminderInViewTaskModal();

	public void clickOnOkButtonForReminderInViewTask();

	public void setReminderInViewTask(int remainderDay, String remiderType);

	public boolean verifyEditIConOfStartDateInViewTaskModal();

	public boolean verifyEditIconOfDueDateInViewTaskModal();

	public void clickOnPersonalTab() throws InterruptedException;

	public boolean verifyErrorMessageForDuplicateReminderInViewTaskModal(String message);

	public void clickOnMoreActionInTaskModule();

	public void selectOptionFromMoreActionInTaskModule(String option);

	public void importTask(String fileName, boolean importStartAndDueDate, boolean importAssignees);

	public boolean verifyImportStartAndDueDatesOrImportAssigneesInImportModal(String option);

	public void clickOnImportButtonInImportModal();

	public boolean verifyImportButtonVisibilityInImportModal();

	public boolean verifyErrorMeassageInImportModal(String errorMessage);

	public boolean verifyLablesInViewPreviewOfImportTaskModal(String label);

	public boolean verifyGroupByAssigneeInTimelineView(String email);

	public boolean verifyGroupByAssigneeGroupNameInTimelineView(String groupName);

	public boolean verifyUnassignedInTimelineView();

	public void openMinicardInTimelineView(String userEmail);

	public ViewUserProfilePage clickUserInMinicard();

	public boolean verifyHeaderViewOptionsInTimeline(String viewOption);

	public void selectHeaderViewOptions(String viewOption);

	public boolean verifyNoneListInTimeline();

	public boolean verifyTaskInParticularGroupByOptionInTimelineView(String groupByoption, String taskname);

	public boolean verifiDuplicateListInTimeline(String duplicateList);

	public void clickOnStartDateDropdown();

	public void clickOnDueDateDropdown();

	public boolean verifyDefaultGroupingInTimeline(String defaultGrouping);

	public boolean verifyGroupingForAscending(List<String> elementsToBeSorted, String groupingAction,
			String anyGroupByLabel);

	public boolean verifyGroupingForDescending(List<String> elementsToBeSorted, String groupingAction,
			String anyGroupByLabel);

	public boolean verifyHeaderGroupByOptions(String groupByOption);

	public void selectHeaderGroupByOptions(String groupByOption);

	public boolean verifyPriorityAndStatusInTimeline(String priorityOrStatusName);

	public boolean verifyCompletedTaskWithStrikeOffInTimeline(String taskName);

	public boolean verifyTabIsActive(String tabName);
}
