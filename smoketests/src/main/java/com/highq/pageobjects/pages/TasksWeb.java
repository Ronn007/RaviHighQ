package com.highq.pageobjects.pages;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.CollaborateLabel.TaskHeaderActionButtons;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.TaskAddDataPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.ViewUserProfilePage;

/**
 * The Class TasksWeb.
 */
public class TasksWeb extends BannerPageWeb implements TasksPage
{
	Actions build;

	/** The driver. */
	String dateformat = "dd MMM YYYY";
	By taskModal = By.xpath(".//*[@id='addTaskModalID']");
	/** * Menu buttons *. */
	By addButton = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//button[normalize-space(.)='" + TaskLabels.TASKS_ADD + "']");

	/** The view button. */
	By viewButton = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//button[normalize-space(.)='" + TaskLabels.TASKS_VIEW + "']");

	/** The group button. */
	By groupButton = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//button[normalize-space(.)='" + TaskLabels.TASKS_GROUP + "']");

	/** The filter button. */
	By filterButton = By.xpath(".//*[contains(@class,'input-group-btn dropdown')]/button");

	By filterButtonClosed = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[@class='input-group']//div[contains(@class,'open')]");

	By filterButtonExpanded = By.xpath(".//*[@id='taskHomeLeftPaneID']//div/button[@aria-expanded='true']");
	/** The search textbox. */
	By searchTextbox = By.id("task_module_quickSearchButtonID");

	By clearSearchBoxButton = By.id("task_module_quickSearchCrossID");

	By closeTaskButton = By.id("taskRightPanelCloseButton");

	/** * Tabs of taskmodule *. */
	By allTaskTab = By.xpath(".//*[@id='ALL_TASKS']/a");

	/** The assigned to me tab. */
	By assignedToMeTab = By.xpath(".//*[@id='ASSIGNED_TO_ME']/a");

	/** The created by me tab. */
	By createdByMeTab = By.xpath(".//*[@id='CREATED_BY_ME']/a");

	/** The favourites tab. */
	By favouritesTab = By.xpath(".//*[@id='FAVOURITES']/a");

	/** The global more action. */
	By globalMoreAction = By.xpath(".//*[@id='taskHomeLeftPaneID']/div[2]//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	/** The global full screen. */
	By globalFullScreen = By.xpath(
			".//*[@id='taskHomeLeftPaneID']/div[2]//a[@data-original-title='" + TaskLabels.TASKS_FULLSCREEN + "']");

	/** The full screen. */
	By fullScreen = By.xpath(".//div[@class='container-fluid']");
	By addColumnCancelButton = By.xpath(".//*[@id='editarea']//button[@id='cancelbtn']");
	/** set xpath for seeAllSavedFilters in task modal. */
	By seeAllSavedFilters = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//a[contains(text(),'" + TaskLabels.TASKS_SEEALLSAVEDFILTERS + "')]");

	/** set xpath for taskModuleSeeSavedFiltersLink in task modal. */
	By taskModuleSeeSavedFiltersLink = By
			.xpath(".//div[@id='task_module_see_saved_filters_link' and @aria-hidden='false']");

	By saveFilterModel = By.xpath(".//*[@id='task_module_see_saved_filters_link_TITLE']");
	By deletePopUp = By.xpath("//h4[@id='customMessageModalTitle']");
	/** * Menu inside Group by *. */
	By groupByInsideMenu = By.xpath(".//*[@id='taskGroupByOptions']/li[text()='" + TaskLabels.TASKS_GROUPBY + "']");

	/** The group by none. */
	By groupByNone = By.xpath(".//*[@id='NONE']/a");

	/** The group by due date. */
	By groupByDueDate = By.xpath(".//*[@id='DUE_DATE']/a");

	/** The group by list. */
	By groupByList = By.xpath(".//*[@id='LIST']/a");

	/** The group by priority. */
	By groupByPriority = By.xpath(".//*[@id='PRIORITY']/a");

	/** The group by status. */
	By groupByStatus = By.xpath(".//*[@id='STATUS']/a");

	/** The group by assignee. */
	By groupByAssignee = By.xpath(".//*[@id='ASSIGNEE']/a");

	/** * Menu inside View *. */
	By viewSwitchTo = By
			.xpath(".//*[@id='taskOrderByOptions']/li[normalize-space(.)='" + TaskLabels.TASKS_SWITCHTO + "']");

	/** The view switch list view. */
	By viewSwitchListView = By.xpath(".//*[@id='TASK_COLUMN_VIEW']/a");

	/** The view switch card view. */
	By viewSwitchCardView = By.xpath(".//*[@id='TASK_CARD_VIEW']/a");

	/** The view priority. */
	By viewPriority = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_PRIORITY']/a");

	/** The view sort by menu. */
	By viewSortByMenu = By
			.xpath(".//*[@id='taskOrderByOptions']/li[normalize-space(.)='" + TaskLabels.TASKS_SORTBY + "']");

	/** The view sort by title. */
	By viewSortByTitle = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_TITLE']/a");

	/** The view sort by due date. */
	By viewSortByDueDate = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_DUEDATE']/a");

	/** The view sort by list. */
	By viewSortByList = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_LIST']/a");

	/** The view sort by priority. */
	By viewSortByPriority = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_PRIORITY']/a");

	/** The view sort by status. */
	By viewSortByStatus = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_STATUS']/a");

	/** The view sort by assignee. */
	By viewSortByAssignee = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_ASSIGNEE']/a");

	/** The view ascending. */
	By viewAscending = By.xpath(".//*[@id='ORDER_ASC']/a");

	/** The view descending. */
	By viewDescending = By.xpath(".//*[@id='ORDER_DESC']/a");

	/** The view full screen icon. */
	By viewFullScreenIcon = By.xpath(
			".//*[@id='taskOrderByOptions']/li/a/span[normalize-space(.)='" + TaskLabels.TASKS_FULLSCREEN + "']");

	/** The view exit full screen. */
	By viewExitFullScreen = By.xpath(
			".//*[@id='taskOrderByOptions']/li/a/span[normalize-space(.)='" + TaskLabels.TASKS_EXITFULLSCREEN + "']");

	/** The no tasks label. */
	By noTasksLabel = By
			.xpath(".//*[@id='taskRowList']//strong[normalize-space(.)='" + TaskLabels.TASKS_NOTASKS + "']");

	By noTaskPresent = By.xpath("//*[@class='taskColListTitle greyFont']");

	/** * Task grid related *. */
	By columnViewTotalTask = By.xpath(".//*[@id='taskRowList']//div[contains(@class,'taskColList')]");// return total
	// number of
	// rows

	/**
	 * ***************** View task popup for column/List view
	 * ************************.
	 */
	public static final String COLUMNVIEW_VIEW_TASK_POPUP_PATH = ".//*[@id='taskDetailContainer' or @id='TaskCardViewModalID' and contains(@style,'display: block')]";

	/** The column view task view popup. */
	By columnViewTaskViewPopup = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH);

	/** The column view task view favourites. */
	By columnViewTaskViewHeaderFavourites = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "']");

	/** The column view task view more action. */
	By columnViewTaskViewHeaderMoreAction = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	/** The column view task view attachments. */
	By columnViewTaskViewHeaderAttachments = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_ATTACHMENTS + "']");

	/** The column view task view comments. */
	By columnViewTaskViewHeaderComments = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_COMMENTS + "']");

	/** The column view task view close. */
	By columnViewTaskViewHeaderClose = By.id("taskRightPanelCloseButton");

	/** The column view task view title. */
	By columnViewTaskViewTitle = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//h3[@id='taskTitle_contentID']");

	/** The column view task view details section label. */
	By columnViewTaskViewDetailsSectionLabel = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[contains(@class,'dataTitle') and normalize-space(.)='" + TaskLabels.TASKS_DETAILS + "']");

	/** The column view task view status label. */
	By columnViewTaskViewStatusLabel = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_STATUS + "']");

	/** The column view task view duedate label. */
	By columnViewTaskViewDuedateLabel = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_DUEDATE + "']");

	/** The column view task view assignee label. */
	By columnViewTaskViewAssigneeLabel = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_ASSIGNEES + "']");

	/** The column view task view priority label. */
	By columnViewTaskViewPriorityLabel = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_PRIORITY + "']");

	public static final String CARDVIEW_VIEW_TASK_POPUP_PATH = ".//*[@id='TaskCardViewModalID' and contains(@style,'display: block')]";
	By cardViewTaskPopUp = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH);

	/** The card view task view popup. */
	By cardViewTaskViewPopup = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH);

	/** The card view task view favourites. */
	By cardViewTaskViewHeaderFavourites = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "']");

	/** The card view task view more action. */
	By cardViewTaskViewHeaderMoreAction = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	/** The card view task view attachments. */
	By cardViewTaskViewHeaderAttachments = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_ATTACHMENTS + "']");

	/** The card view task view comments. */
	By cardViewTaskViewHeaderComments = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_COMMENTS + "']");

	/** The card view task view close. */
	By cardViewTaskViewHeaderClose = By
			.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//i[@data-original-title='" + TaskLabels.TASKS_CLOSE + "']");

	/** The card view task view title. */
	By cardViewTaskViewTitle = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//h3[@id='taskTitle_contentID']");

	/** The card view task view details section label. */
	By cardViewTaskViewDetailsSectionLabel = By.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[contains(@class,'dataTitle') and normalize-space(.)='" + TaskLabels.TASKS_DETAILS + "']");

	/** The card view task view status label. */
	By cardViewTaskViewStatusLabel = By
			.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_STATUS + "']");

	/** The card view task view duedate label. */
	By cardViewTaskViewDuedateLabel = By
			.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_DUEDATE + "']");

	/** The card view task view assignee label. */
	By cardViewTaskViewAssigneeLabel = By
			.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_ASSIGNEES + "']");

	/** The card view task view priority label. */
	By cardViewTaskViewPriorityLabel = By
			.xpath(CARDVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_PRIORITY + "']");

	By addColumnButton = By.xpath(".//*[@id='addNewColumnDivID']/button[text()='" + TaskLabels.TASKS_ADDCOLUMN + "']");
	By addColumnTextBox = By.xpath(".//*[@id='editarea']//input[@id='addColumnID']");
	By addColumnSaveButton = By.xpath(".//*[@id='editarea']//button[@id='savebtn']");
	/** set xpath for linkColorDisabled in task modal. */
	By linkColorDisabled = By.xpath(".//*[@id='seelink']");

	By totalTask = By.xpath(".//*[starts-with(@id,'taskRowDivID')]");

	/** The column view task view list label. */
	By columnViewTaskViewListLabel = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_LIST + "']");

	/** The column view task view created by label. */
	By columnViewTaskViewCreatedByLabel = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_CREATEDBY + "']");

	/** The column view task view updated by label. */
	By columnViewTaskViewUpdatedByLabel = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//label[normalize-space(.)='" + TaskLabels.TASKS_UPDATEDBY + "']");

	/** The column view task view status value. */
	By columnViewTaskViewStatusValue = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//span[@id='taskStatusTypeName_contentID']");

	/** The column view task view due date value. */
	By columnViewTaskViewDueDateValue = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='task_inline_taskDueDate']");

	/** The column view task view option share. */
	By columnViewTaskViewOptionShare = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//ul//a[normalize-space(.)='" + TaskLabels.TASKS_SHARE + "']");

	/** The column view task view option export TO pdf. */
	By columnViewTaskViewOptionExportTOPdf = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//ul//a[normalize-space(.)='" + TaskLabels.TASKS_EXPORTTOPDF + "']");

	/** The column view task view option print preview. */
	By columnViewTaskViewOptionPrintPreview = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//ul//a[normalize-space(.)='" + TaskLabels.TASKS_PRINTPREVIEW + "']");

	/** The column view task view option delete. */
	By columnViewTaskViewOptionDelete = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//ul//a[normalize-space(.)='" + TaskLabels.TASKS_DELETE + "']");

	/** The Constant DELETE_TASK_MODAL. */
	public static final String DELETE_TASK_MODAL = ".//*[@id='collaborateCustomMessageModal' and @aria-hidden='false']";

	/** The delete task modal. */
	By deleteTaskModal = By.xpath(DELETE_TASK_MODAL);

	/** The delete task modal title. */
	By deleteTaskModalTitle = By.xpath(DELETE_TASK_MODAL + "//h4");

	/** The delete task modal cancel button. */
	By deleteTaskModalCancelButton = By
			.xpath(DELETE_TASK_MODAL + "//button[normalize-space(.)='" + TaskLabels.TASKS_CANCEL + "']");

	/** The delete task modal delete button. */
	By deleteTaskModalDeleteButton = By
			.xpath(DELETE_TASK_MODAL + "//button[normalize-space(.)='" + TaskLabels.TASKS_DELETE + "']");

	/** The delete task modal close icon. */
	By deleteTaskModalCloseIcon = By
			.xpath(DELETE_TASK_MODAL + "//button[@aria-label='" + TaskLabels.TASKS_CLOSE + "']");

	/** The delete task modal message. */
	By deleteTaskModalMessage = By.xpath(DELETE_TASK_MODAL + "//p");

	/**
	 * *** Xpath for assignee may returns multiple value due to multiple assignee
	 * also existed ****.
	 */
	By columnViewTaskViewAssigneeValue = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='task_inline_assigne_and_group_listID']/a");

	/** The column view task view priority value. */
	By columnViewTaskViewPriorityValue = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='task_inline_taskPriorityTypeNameID']");

	/** The column view task view list value. */
	By columnViewTaskViewListValue = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='task_inline_taskListNameID']");

	/** The column view task view description section text. */
	By columnViewTaskViewDescriptionSectionText = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[contains(@class,'dataTitle') and normalize-space(.)='" + TaskLabels.TASKS_DESCRIPTION + "']");

	/** The column view task view description section edit icon. */
	By columnViewTaskViewDescriptionSectionEditIcon = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='task_inline_editContent_descriptionDivID']//a[@data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_EDIT + "']");

	/** The column view task view description. */
	By columnViewTaskViewDescription = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//*[@id='task_inline_editContent_descriptionDivID']//div[@id='task_content_view_ID']");

	/** The column view task view attachment section text. */
	By columnViewTaskViewAttachmentSectionText = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='taskAttachmentContainerID']//span[@class='attachmentTitle']");

	/** The column view task view attachment count. */
	By columnViewTaskViewAttachmentCount = By.xpath(
			COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='taskAttachmentContainerID']//span[@class='greyFont']/strong");// count
	// inside
	// this
	// ()

	/** The column view task view attachment icon add. */
	By columnViewTaskViewAttachmentIconAdd = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='taskAttachmentContainerID']//a[@data-original-title='" + TaskLabels.TASKS_ADD + "']");

	/** The column view task view attachment icon download all. */
	By columnViewTaskViewAttachmentIconDownloadAll = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='taskAttachmentContainerID']//a[@data-original-title='"
					+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_DOWNLOADALL + "']");

	/** The column view task view attachment files section text. */
	By columnViewTaskViewAttachmentFilesSectionText = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='taskAttachmentContainerID']//h6[normalize-space(.)='" + TaskLabels.TASKS_FILES + "']");

	/** The column view task view attachment images section text. */
	By columnViewTaskViewAttachmentImagesSectionText = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='taskAttachmentContainerID']//h6[normalize-space(.)='" + TaskLabels.TASKS_IMAGES + "']");

	/**
	 * ***************** Need to seprate out logic for fetch out a file
	 * ********************.
	 */
	By columnViewTaskViewAttachmentFiles = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='taskAttachmentContainerID']//div[@class='row attachListview']");

	/** The column view task view attachment images. */
	By columnViewTaskViewAttachmentImages = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='taskAttachmentContainerID']//div[contains(@class,'thumbContainer')]");

	/** The column view task view tags section text. */
	By columnViewTaskViewTagsSectionText = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='task_inline_tagsDivID']/div/span");

	/** The column view task view tags section value. */
	By columnViewTaskViewTagsSectionValue = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div[@id='taskTagID']/div/a");// total
	// tags

	/** The column view task view remainder label. */
	By columnViewTaskViewRemainderLabel = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='task_inline_reminderDivID' and @style='display: block;']//div[contains(@class,'dataTitle')]");

	/** The column view task view remainder count. */
	By columnViewTaskViewRemainderCount = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='task_inline_reminderDivID' and @style='display: block;']//span[@id='taskReminderCountID']");

	/** The column view task view total remainder. */
	By columnViewTaskViewTotalRemainder = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//div/span[@class='paddRight5']");

	By columnViewTaskViewAttachment = By
			.xpath(".//*[@id='taskDetailContainer' or @class='infoSection']//a[@class='fileNameTruncate']");

	// By columnViewTaskViewAttachment = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH +
	// "//a[@class='fileNameTruncate']");

	/** ******************** Filter menu ********************. */
	By filterMenuList = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//li[@id='filterListID']/a");

	/** The filter menu priority. */
	By filterMenuPriority = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//ul[@id='accordion']/li[2]/a");

	/** The filter menu status. */
	By filterMenuStatus = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//li[@id='task_status_filter']/a");

	/** The filter menu assignee. */
	By filterMenuAssignee = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//li[@id='task_assignee_filter']/a");

	/** The filter menu saved filters. */
	By filterMenuSavedFilters = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[@id='taskSavedFilterCollapse']");

	/** The filter menu save current filter as. */
	By filterMenuSaveCurrentFilterAs = By
			.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[contains(text(),'"
					+ TaskLabels.TASKS_FILTERS_SAVECURRENTFILTERS + "')]");

	/** The filter disable see all saved filters. */
	By filterDisableSeeAllSavedFilters = By.xpath(
			".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[@id='seelink' and contains(@class,'disabled')]");

	/** The filter enable see all saved filters. */
	By filterEnableSeeAllSavedFilters = By.xpath(
			".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[@id='seelink' and not(contains(@class,'disabled'))]");

	/** The filter disable clear filters. */
	By filterDisableClearFilters = By.xpath(
			".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[@id='linkClearFilter' and contains(@class,'disabled')]");

	/** *************** Save curent filter modal *******************. */
	By saveFilterModal = By.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']");
	By saveFilterModalClosed = By.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='true']");

	/** The save filter modal title. */
	By saveFilterModalTitle = By.id("task_module_save_filters_link_TITLE");

	/** The save filter close button. */
	By saveFilterCloseButton = By.xpath(
			".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//button[@id='task_module_save_filters_link_MAIN_CLOSE_BUTTON']");

	/** The save filter name label. */
	By saveFilterNameLabel = By
			.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//label[text()='"
					+ TaskLabels.TASKS_NAME + "']");

	/** The save filter textbox. */
	By saveFilterTextbox = By
			.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//input[@id='taskSaveFilterID']");

	/** The save filter cancel button. */
	By saveFilterCancelButton = By.xpath(
			".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//button[@id='task_module_save_filters_link_file_module_shareModal_close_btn']");

	/** The save filter disable save button. */
	By saveFilterDisableSaveButton = By
			.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//button[text()='"
					+ TaskLabels.TASKS_SAVE + "' and @disabled='disabled']");

	/** The save filter enable save button. */
	By saveFilterEnableSaveButton = By
			.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//button[text()='"
					+ TaskLabels.TASKS_SAVE + "' and not(@disabled='disabled')]");

	/** **************** See allsaved filter modal *********************. */
	By seeAllSavedFilterModal = By.xpath(".//*[@id='task_module_see_saved_filters_link' and @aria-hidden='false']");

	/** The see all saved filter modal title. */
	By seeAllSavedFilterModalTitle = By.id("task_module_see_saved_filters_link_TITLE");

	/** The see all saved filter modal close icon. */
	By seeAllSavedFilterModalCloseIcon = By.xpath(
			".//*[@id='task_module_see_saved_filters_link' and @aria-hidden='false']//button[@id='task_module_see_saved_filters_link_MAIN_CLOSE_BUTTON']");

	/** The see all saved filter modal close button. */
	By seeAllSavedFilterModalCloseButton = By.xpath(
			".//*[@id='task_module_see_saved_filters_link' and @aria-hidden='false']//button[@id='task_module_see_saved_filters_link_file_module_shareModal_close_btn']");

	/** The see all saved filter total filters. */
	By seeAllSavedFilterTotalFilters = By
			.xpath(".//*[@id='task_module_see_saved_filters_link' and @aria-hidden='false']//li");// Total number of
	// saved filters

	/** *************** Add task modal *********************************. */
	public static final String ADDTASK = ".//*[@id='addTaskModalID' and contains(@class,'fade in')]";
	private static final String ADD_TASK_MODEL = ".//*[@id='addTaskModalTaskList']";
	By addTaskModalSiteList = By.xpath(ADDTASK + "//select[@id='addTaskModalSiteList']");

	/** The add task modal. */
	By addTaskModal = By.xpath(ADDTASK);

	/** The add task title. */
	By addTaskTitle = By.xpath(ADDTASK + "//div[@id='addTaskModalID_TITLE']");

	/** The add task details tab. */
	By addTaskDetailsTab = By.xpath(ADDTASK + "//a[@id='detailTabId']");

	/** The add task attachments tab. */
	By addTaskAttachmentsTab = By.xpath(ADDTASK + "//a[@id='attachmentTabId']");

	/** The add task error msg. */
	By addTaskErrorMsg = By.xpath(ADDTASK + "//span[@id='errorElementContainer']");

	/** The add task add title. */
	By addTaskAddTitle = By.xpath(ADDTASK + "//input[@id='addTaskModalTaskTitle']");

	/** The add task add description. */
	By addTaskAddDescription = By.xpath(ADDTASK + "//div[@id='addTaskModalDescription']");

	/** The add task add duedate. */
	By addTaskAddDuedate = By.xpath(ADDTASK + "//span[@id='addTaskModalTaskDueDate_DatePickerIcon']");

	/** The add task add assignee. */
	By addTaskAddAssignee = By.xpath(ADDTASK + "//input[@id='taskAssigneeAndGroup-tokenfield']");

	/** The add task add priority. */
	By addTaskAddPriority = By.xpath(ADDTASK + "//select[@id='taskPriorityTypeID']");

	/** The add task add list. */
	By addTaskAddList = By.xpath(ADDTASK + "//select[@id='addTaskModalTaskList']");

	By addTaskAddListLink = By.xpath(ADDTASK + "//div[@id='InlineTaskAddListDivID']/a");

	By addTaskAddListTextBox = By.xpath(ADDTASK
			+ "//div[contains(@class,'edit-area') and not(@style='display: none;')]//input[@id='taskListNameID']");
	By addTaskAddListCancelButton = By.xpath(ADDTASK
			+ "//div[contains(@class,'edit-area') and not(@style='display: none;')]//button[contains(@class,'edit-cancel')]");
	By addTaskAddListSubmittButtonDisable = By.xpath(ADDTASK
			+ "//div[contains(@class,'edit-area') and not(@style='display: none;')]//button[contains(@class,'edit-submit') and @disabled='disabled']");
	By addTaskAddListSubmittButtonEnable = By.xpath(ADDTASK
			+ "//div[contains(@class,'edit-area') and not(@style='display: none;')]//button[contains(@class,'edit-submit') and not(@disabled='disabled')]");

	By addTaskTableLabel = By.xpath("(//*[normalize-space(text())='" + TaskLabels.TASKS_TAGS + "'])[last()]");

	By taskRowList = By.id("taskRowList");
	/** The add task add status. */
	By addTaskAddStatus = By.xpath(ADDTASK + "//select[@id='taskStatusTypeID']");

	/** The add task add tags. */
	By addTaskAddTags = By.xpath(ADDTASK + "//input[@id='taskTagsID-tokenfield']");

	/** The add task add disabled add remainder button. */
	By addTaskAddDisabledAddRemainderButton = By
			.xpath(ADDTASK + "//button[@id='taskRemiderAddBtnID' and @disabled='disabled']");

	/** The add task add enabled add remainder button. */
	By addTaskAddEnabledAddRemainderButton = By
			.xpath(ADDTASK + "//button[@id='taskRemiderAddBtnID' and not(@disabled='disabled')]");

	/** The add task add another task check box. */
	By addTaskAddAnotherTaskCheckBox = By.xpath(ADDTASK + "//input[@id='taskAddAnotherID']");

	/** The add task add another task text. */
	By addTaskAddAnotherTaskText = By.xpath(ADDTASK + "//div[@id='addAnotherID']/div/label");

	/** The add task browse in attachment. */
	By addTaskBrowseInAttachment = By.xpath(ADDTASK + "//input[@id='addEditTaskAttachmentsckUploadFile']");

	/** The add task browse in attachment text. */
	By addTaskBrowseInAttachmentText = By.xpath(ADDTASK + "//div[@id='attachmentTab']/div//span[@class='GreyFont']");

	/** The add task close icon. */
	By addTaskCloseIcon = By.xpath(ADDTASK + "//button[@id='addTaskModalID_MAIN_CLOSE_BUTTON']");

	/** The add task add remainder. */
	By addTaskAddRemainder = By.xpath(ADDTASK
			+ "//div[@id='taskAddRemiderDivID' and not(contains(@class,'hide'))]//input[@id='taskRemiderInputID']");

	By taskReminder = By
			.xpath("//*[@id='deleteAddTaskModalReminder' and @data-original-title='" + TaskLabels.TASKS_REMOVE + "']");

	/** The add task add remainder OK button. */
	By addTaskAddRemainderOKButton = By.xpath(ADDTASK
			+ "//div[@id='taskAddRemiderDivID' and not(contains(@class,'hide'))]//button[@id='addTaskModalAddReminderId']");

	/** The add task add remainder cancel button. */
	By addTaskAddRemainderCancelButton = By.xpath(ADDTASK
			+ "//div[@id='taskAddRemiderDivID' and not(contains(@class,'hide'))]//button[contains(@class,'edit-cancel')]");

	/** The add task modal disable add button. */
	By addTaskModalDisableAddButton = By
			.xpath(ADDTASK + "//button[@id='addTaskModalID_addTaskSave' and @disabled='disabled']");

	/** The add task modal enable add button. */
	By addTaskModalEnableAddButton = By
			.xpath(ADDTASK + "//button[@id='addTaskModalID_addTaskSave' and not(@disabled='disabled')]");

	By addTaskModalClosed = By.id("addTaskModalID_addTaskSave");

	/** The add task modal cancel button. */
	By addTaskModalCancelButton = By.xpath(ADDTASK + "//button[@id='addTaskModalID_taskModalCancel']");

	By addTaskModalAssigneeList = By
			.xpath(ADDTASK + "//div[@id='taskAssigneesTokenField']//div//div[@class='typeahead_secondary']");

	By addTaskModalEnterDueDate = By.xpath(ADDTASK + "//input[@id='addTaskModalTaskDueDatePickerID']");

	/**
	 * ************************* Attachment modal at time of add task *************.
	 */
	public static final String ATTACHMENT_MODAL = " ";

	/** The add attachment recent. */
	By addAttachmentRecent = By
			.xpath(ATTACHMENT_MODAL + "//a[normalize-space(.)='" + TaskLabels.TASKS_ATTACHMENT_RECENT + "']");

	/** The add attachment browse. */
	By addAttachmentBrowse = By
			.xpath(ATTACHMENT_MODAL + "//a[normalize-space(.)='" + TaskLabels.TASKS_ATTACHMENT_BROWSE + "']");

	/** The add attachment upload. */
	By addAttachmentUpload = By
			.xpath(ATTACHMENT_MODAL + "//a[normalize-space(.)='" + TaskLabels.TASKS_ATTACHMENT_UPLOAD + "']");

	/** The add attachment search. */
	By addAttachmentSearch = By
			.xpath(ATTACHMENT_MODAL + "//a[normalize-space(.)='" + TaskLabels.TASKS_ATTACHMENT_SEARCH + "']");

	/** The add task attachment modal. */
	By addTaskAttachmentModal = By.xpath(ATTACHMENT_MODAL);

	/** The add attachment upload browse. */
	By addAttachmentUploadBrowse = By
			.xpath(ATTACHMENT_MODAL + "//input[@id='attachmentModal_uploadTab_fileInputTypeID']");
	/** The add attachment upload browse. */
	By addAttachmentUploadAttach = By
			.xpath(ATTACHMENT_MODAL + "//button[normalize-space(text())='" + TaskLabels.TASKS_ATTACHMENT_ATTACH + "']");

	By detailsTabInAddTaskModel = By.xpath("//*[@id='detailTabId']");

	/****************************************************************/
	By editTaskTitle = By.id("task_inline_TaskTitle");

	By editTaskTitleSubmit = By.id("inlineEdit_btnSubmitTitle");

	By editTaskTitleWaitTillSubmit = By.xpath(".//*[@id='task_inline_titleDivID']/div[contains(@class,'hide')]");

	By savefilterCancelButton = By
			.xpath(".//*[@id='task_module_save_filters_link' and @aria-hidden='false']//button[text()='"
					+ TaskLabels.TASKS_CANCEL + "']");

	By noFilterAvailableMessage = By.xpath(".//*[@id='task_module_taskSavedFilterDivID']//h6[normalize-space(.)='"
			+ TaskLabels.TASKS_NORESULTSFOUND + "']");
	/** set xpath for inlineEditTaskListID in task modal. */
	By inlineEditTaskListID = By.id("inlineEditTaskListID");

	/** The card view. */
	By cardView = By.xpath(".//*[@id='task_listDiv' or @id='cardViewWrapper']");

	By noTasksMessage = By
			.xpath(".//*[@id='taskRowList']//*[normalize-space(text())='" + TaskLabels.TASKS_NOTASKS + "']");

	/** Task Details Window */
	By totalTasks = By.xpath("//div[contains(@class,'TruncateLink')]/a");
	By mainSection = By.xpath("//*[@class='mainSection']");
	By menu = By.xpath("//*[@id='fromID']//ul[@role='menu']");
	By deleteDialog = By.xpath("//*[contains(text(),'" + TaskLabels.TASKS_DELETETASKWARNING + "')]");

	/** Edit Task */
	By taskTitle = By.xpath(".//*[@id='taskTitle_contentID'and contains(@class,'negativeTitle')]");
	By taskListname = By.xpath(".//*[@id='task_inline_taskListNameID' and starts-with(@class,'edit-content')]");
	By acceptIcon = By.xpath(".//*[@id='inlineEdit_btnSubmitList']");
	By btnDone = By.xpath("(//span[contains(text(),'" + TaskLabels.TASKS_DONE + "')])[last()]");

	By taskStatus = By.xpath(".//div[@class='edit-content']//span[@id='taskStatusTypeName_contentID']");
	By taskInlineStatus = By.id("task_inline_taskStatus");
	By inlineStatusSubmitButton = By.id("inlineEdit_btnSubmitStatus");

	By taskDueDate = By.id("task_inline_taskDueDate");
	By inlineDueDateDatePicker = By.id("inlineEditTaskDueDatePickerID");
	By inlineDueDatePickerSubmitButton = By.id("inlineEdit_btnSubmitDueDate");

	By taskAssignee = By
			.xpath(".//*[@id='task_inline_assigne_and_group_listID']//*[contains(@onclick,'TASK_ASSIGNE_AND_GROUP')]");
	By editTaskAssigneeTextBox = By.id("taskEditAssigneeAndGroup-tokenfield");
	By inlineEditSubmitAssignee = By.id("inlineEdit_btnSubmitAssineeAndGroup");

	By taskPriority = By.xpath(".//*[@id='task_inline_taskPriorityTypeNameID' and @class='edit-content']");
	By editTaskPriority = By.id("taskPriorityTypeEditID");
	By inlineEditSubmitPriority = By.id("inlineEdit_btnSubmitPriority");

	By taskList = By.xpath(".//*[@id='task_inline_taskListNameID' and starts-with(@class,'edit-content')]");
	By taskListDropDown = By.id("task_inline_taskListNameID");
	By inlineTaskListSubmit = By.id("inlineEdit_btnSubmitList");

	By taskContents = By.xpath(".//*[@id='task_inline_editContent_descriptionDivID']//a");
	By taskContentTextBox = By.id("inlineEdit_taskCKContentID");
	By inlineEditContentSubmit = By.id("inlineEdit_btnSubmitDescription");

	By taskDescription = By.xpath("//*[@id='task_content_view_ID']/p");

	By editTaskTag = By.id("editTagIconID");
	By editTaskTagInputBox = By.id("inlineTaskTagsID-tokenfield");
	By inlineEditTagSubmit = By.id("inlineEdit_btnSubmitTags");
	By tagsTitle = By.className("tagsTitle");

	By reminderRemoveButton = By.xpath(".//*[@id='deleteEditTaskModalReminder'][1]");
	By addReminderLink = By.id("task_inline_updateRemiderClass");
	By inlineTaskReminderInputId = By.id("inlineEdit_taskRemiderInputID");
	By inlineEditReminderSubmitButton = By.id("inlineEdit_btnSubmitReminder");

	By addAttachment = By.xpath(".//*[@id='taskAttachmentContainerID']//a[@data-original-title='" + TaskLabels.TASKS_ADD
			+ "' and not (contains(@class,'hide'))]");

	By addAttachmentInTask = By
			.xpath("//*[contains(@class,'uploadBtn') and @data-original-title='" + TaskLabels.TASKS_ADD + "']");

	/** Filter Related Xpath */
	By filterDropDown = By.xpath(".//*[@id='taskHomeLeftPaneID']//div[@class='input-group-btn dropdown open']");

	By taskHomeLeftPane = By.xpath(".//*[@id='taskHomeLeftPaneID']");
	By clearFilters = By.xpath(".//*[@id='taskHomeLeftPaneID']//div[contains(@class,'open')]//a[normalize-space(.)='"
			+ TaskLabels.TASKS_CLEARFILTERS + "']");

	/** The Constant CARDVIEWWRAPPER. */
	private static final String CARDVIEWWRAPPER = ".//*[@id='cardViewWrapper']/div";
	By cardList = By.xpath(".//*[starts-with(@id,'cardListPanelID_')]//div[@class='cardListTitle TruncateTxt']");

	/** set xpath for multiple occurance of tasklist div. */
	private static final String TASKLISTDIV = ".//*[@id='task_listDiv']";

	By taskColListTitle = By.xpath(".//div[contains(@class,'taskColListTitle')]//a");

	By removeButtonInDeleteSavedFilter = By
			.xpath(".//*[@id='task_module_see_saved_filters_link_BODY']//a[contains(@data-original-title,'"
					+ TaskLabels.TASKS_REMOVE + "')]");

	By deleteSavedFilterButton = By
			.xpath(".//*[@id='collaborateCustomMessageModal' and @aria-hidden='false']//button[text()='"
					+ TaskLabels.TASKS_DELETE + "']");

	By deletePopUpDisabled = By.xpath(".//*[@id='collaborateCustomMessageModal'and @aria-hidden='false']");
	By deleteSavedFilterPopUpDisabled = By
			.xpath(".//*[@id='task_module_see_saved_filters_link' and @aria-hidden='false']");

	By attachFileUploadButton = By.xpath(".//*[@id='addEditAttachmentContainerID']//a[contains(@class,'uploadBtn')]");
	By attachFileTextBox = By.xpath("//*[normalize-space(text())='" + TaskLabels.TASKS_ATTACHMENT_ADDATTACHMENT
			+ "']/following::span[@class='GreyFont']/preceding::a[contains(text(),'"
			+ TaskLabels.TASKS_ATTACHMENT_BROWSE + "')][1]/input");

	By attachmentModelHidden = By.xpath("//*[@id='ATTACHMENT_MODAL' and @aria-hidden='true']");

	By saveCurrentFilterButtonDisabled = By.xpath(
			".//*[@id='task_module_save_filters_link_task_module_save_filters_link_save_btn'][@disabled='disabled']");
	By saveButtonInSaveFilter = By.id("task_module_save_filters_link_task_module_save_filters_link_save_btn");

	By saveFilterCollapse = By.xpath("//a[@id='taskSavedFilterCollapse']");
	By saveFilterTextBox = By.xpath(".//*[@id='taskSaveFilterID']");
	By taskAddButton = By
			.xpath(".//*[@id='addTaskModalID_addTaskSave' and normalize-space()='" + TaskLabels.TASKS_ADD + "']");
	By clearFilterLink = By.xpath(".//*[@id='linkClearFilter']");
	By filterExpanded = By.xpath("//button[@data-toggle='dropdown' and @aria-expanded='true']");

	By inlineTaskListName = By.xpath(".//*[@id='task_inline_taskListNameID']");
	By inlineTaskListEdit = By.xpath(".//*[@id='inlineEditTaskListID']");
	By addAnotherTaskCheckBox = By
			.xpath(".//*[@id='addAnotherID']//label[normalize-space(.)='" + TaskLabels.TASKS_ADDANOTHERTASK + "']");
	By addButtonTaskModel = By
			.xpath(".//*[@id='addTaskModalID_addTaskSave' and normalize-space()='" + TaskLabels.TASKS_ADD + "']");
	By taskListDiv = By.id("task_listDiv");
	By share_recipientHover = By.xpath("(//*[@class='typeahead_labels']//*)[last()]");

	By deleteTask = By.xpath(
			".//*[contains(@style,'display: block')]//*[normalize-space(text())='" + TaskLabels.TASKS_DELETE + "']");
	By shareTask = By.xpath(
			".//*[contains(@style,'display: block')]//*[normalize-space(text())='" + TaskLabels.TASKS_SHARE + "']");

	By ckLoadedForCommentBox = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@id,'addCommentField') and @isckloaded='FALSE']");
	By disabledCommentBox = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@id,'addCommentField') and @role='textbox']");
	By enabledCommentBox = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@class,'cke_focus') and @role='textbox']");
	By postCommentButton = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(normalize-space(text()),'"
					+ TaskLabels.TASKS_POST + "')]");
	By likeLink = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(normalize-space(text()),'"
					+ TaskLabels.TASKS_LIKE + "')]");

	By messageModelClosed = By
			.xpath("//*[@id='collaborateCustomMessageModal' and contains(@class,'modal fade modal-dialog-center')]");
	By messageModelOpened = By
			.xpath("//*[@id='collaborateCustomMessageModal' and contains(@class,'modal fade modal-dialog-center in')]");
	By deleteCommentMessage = By.xpath("//*[@id='collaborateCustomModalMessage' and normalize-space(text())='"
			+ TaskLabels.TASKS_DELETECOMMENTWARNING + "']");
	By cancelMessageModel = By.id("collaborateMessageCancelButton");
	By confirmMessageModel = By.id("collaborateMessageOkButton");

	By loadMoreCommentsLink = By.xpath("//*[normalize-space(text())='" + TaskLabels.TASKS_LOADMORECOMMENTS + "']");

	By editCommentTextBox = By.xpath("//*[@role='textbox' and @contenteditable='true']");

	By replyCommentTextBox = By.xpath("(//*[@isckloaded='TRUE'])[last()]");

	By commentCancelButton = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//*[normalize-space(text())='" + TaskLabels.TASKS_CANCEL + "']");

	By commentCount = By.id("taskCommentCountIDComp");

	By reminderRemoveLink = By.xpath("//*[@data-original-title='" + TaskLabels.TASKS_REMOVE
			+ "' and contains(@onclick,'AddTaskCollection.deleteAddTaskModalReminder(this);')]");

	By attachmentTabInAddTaskModal = By.id("attachmentTabId");

	By enabledPostCommentButton = By.xpath(
			".//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(normalize-space(text()),'"
					+ TaskLabels.TASKS_POST + "') and not(contains(@class,'disabled'))]");

	By listViewCloseTaskButton = By.id("taskRightPanelCloseButton");
	By cardViewCloseTaskButton = By.id("TaskCardViewModalID_MAIN_CLOSE_BUTTON");

	/** Favourite */
	By favouriteStarSelected = By.xpath("(//*[contains(@class,'icon-star-selected') and @data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_REMOVEFROMFAVOURITES + "'])[last()]");
	By favouriteButton = By.xpath("(//*[contains(@class,'icon-star') and @data-original-title='"
			+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "'])[last()]");

	By addTaskModalEnterDueDateLabel = By
			.xpath(ADDTASK + "//label[normalize-space(text())='" + TaskLabels.TASKS_DUEDATE + "']");

	String taskTitleColumns = ".//*[@id='taskRowList']//*[@class='colTitle']";

	By taskTimelineView = By.xpath(
			"//div[@class='container']//div[not(contains(@class,'tasksListContainer hide'))]/*[@id='taskTimelineScheduler']");

	By disbaleAddButtonInAddTaskModal = By.xpath(".//*[@id='addTaskModalID_addTaskSave' and @disabled='disabled']");

	By cancelButtonInAddTaskModal = By.id("addTaskModalID_taskModalCancel");

	By closeButtonInAddTaskModal = By.id("addTaskModalID_MAIN_CLOSE_BUTTON");

	By notVisibleAddTaskModal = By.xpath(".//*[@id='addTaskModalID' and not(contains(@class,'fade in'))]");

	By visibleAddTaskModal = By.xpath(".//*[@id='addTaskModalID' and contains(@class,'fade in')]");

	By ckEditorOfDiscription = By.id("cke_addTaskModalDescription");

	By defaultPriorityInAddTaskModal = By.xpath(".//*[@id='taskPriorityTypeID']//option[@selected='selected']");

	By defaultListInAddTaskModal = By.xpath(".//*[@id='addTaskModalTaskList']//option[@selected='selected']");

	By defaultStatusInAddTaskModal = By.xpath(".//*[@id='taskStatusTypeID']//option[@selected='selected']");

	By addTaskModalEnterStartDate = By.xpath(ADDTASK + "//input[@id='addTaskModalTaskStartDatePickerID']");

	By invalidStartDateMessage = By.xpath(ADDTASK + "//*[@id='addTaskModalTaskStartDatePickerID_pID']");

	By invalidDueDateMessage = By.xpath(ADDTASK + "//*[@id='addTaskModalTaskDueDatePickerID_pID']");

	By taskStartDateInViewTaskModal = By.id("task_inline_taskStartDate");

	/* The view switch card view. */
	By viewSwitchTimelineView = By.xpath(".//*[@id='TASK_TIMELINE_VIEW']/a");

	By defaultViewNameInTimelineView = By.xpath("*//div[contains(@class,'btn-default active')]");

	By taskTimelineViewPrevoiusButton = By.xpath("//*[@aria-label='Previous']");

	By taskTimelineViewNextButton = By.xpath("//*[@aria-label='Next']");

	By taskTimelineViewBtnTwoWeekView = By.xpath("//*[@name='two_week_timeline_tab']");

	By taskTimelineViewBtnMonthkView = By.xpath("//*[@name='month_timeline_tab']");

	By taskTimelineViewBtnWeekView = By.xpath("//*[@name='week_timeline_tab']");

	By taskTimelineViewBtnToday = By.xpath("//*[@aria-label='" + TaskLabels.TASKS_TIMELINE_VIEW_LABEL_TODAY + "']");

	By taskTimelineViewTodayDate = By
			.xpath(".//*[@id='taskTimelineScheduler']//div[contains(@class,'dhx_cell_today')]");

	By timelineDateHeader = By.xpath(".//*[@id='taskTimelineScheduler']//div[contains(@class,'dhx_scale_bar')]");

	By errorContainer = By.xpath(
			".//*[@id='createTaskModal_ErrorDiv' and not(@style='display: none;')]//span[@id='errorElementContainer']");

	By taskTimelineViewAllTasks = By.id("ALL_TASKS");

	By taskTimelineViewAssignToMe = By.id("ASSIGNED_TO_ME");

	By taskTimelineViewCreatedByMe = By.id("CREATED_BY_ME");

	By taskTimelineViewFavourites = By.id("FAVOURITES");

	By viewTaskInCardOrTimelineView = By.xpath(".//*[@id='TaskCardViewModalID' and @class='modal fade in']");

	By closeButton = By.id("TaskCardViewModalID_MAIN_CLOSE_BUTTON");

	By currentMonthYearInCalender = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[@class='picker-switch']");

	By currentYearInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[@class='picker-switch']");

	By nextYearButtonInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[contains(@class,'next')]");

	By previousYearButtonInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[contains(@class,'prev')]");

	By defaultSiteInAddTaskModal = By.xpath(".//*[@id='addTaskModalSiteList']//option[@selected='selected']");

	By inlineStartDateDatePicker = By.id("inlineEditTaskStartDatePickerID");

	By inlineStartDatePickerSubmitButton = By.id("inlineEdit_btnSubmitStartDate");

	By closeButtonInViewTask = By.id("TaskCardViewModalID_taskCardViewModalClose");

	By viewTaskModal = By.xpath(".//*[@id='TaskCardViewModalID' and contains(@class,'fade in')]");

	By invalidStartDateInViewTaskModal = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//*[@id='inlineEditTaskStartDatePickerID_pID']");

	By invalidDueDateInViewTaskModal = By
			.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//*[@id='inlineEditTaskDueDatePickerID_pID']");

	By noDueDateInViewTaskModal = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//following::div[contains(@class,'picker-open')]//a[normalize-space(text())='"
			+ TaskLabels.TASKS_NODUEDATE + "']");

	By inlineEditTaskDueDateDatePickerIcon = By.id("inlineEditTaskDueDate_DatePickerIcon");

	By inlineEditTaskStartDateDatePickerIcon = By.id("inlineEditTaskStartDate_DatePickerIcon");

	By noStartDateInViewTaskModal = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//following::div[contains(@class,'picker-open')]//a[normalize-space(text())='"
			+ TaskLabels.TASKS_NOSTARTDATE + "']");

	By cancelIconOfStartDateInViewTaskModal = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='task_inline_taskStartDate']//following-sibling::*//button[@class='btn-xs edit-cancel']");

	By cancelIconOfDueDateInViewTaskModal = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH
			+ "//div[@id='task_inline_taskDueDate']//following-sibling::*//button[@class='btn-xs edit-cancel']");

	By taskTimelineScheduler = By.id("taskTimelineScheduler");

	By startdateDatePickerIconForAddTaskModal = By.id("addTaskModalTaskStartDate_DatePickerIcon");

	By dueDateDatePickerIconForAddTaskModal = By.id("addTaskModalTaskDueDate_DatePickerIcon");

	By startDateDatePickerIconForViewTaskModal = By.id("inlineEditTaskStartDate_DatePickerIcon");

	By dueDateDatePickerIconForViewTaskModal = By.id("inlineEditTaskDueDate_DatePickerIcon");

	By toolTipOfTask = By.xpath(".//*[@class='dhtmlXTooltip tooltip' and @role='tooltip']");

	By getDate = By.xpath("*//div[@class='dhx_cal_date']");

	By reminderAddButton = By.id("taskRemiderAddBtnID");

	By listOfReminders = By.xpath(".//*[@id='remiderList']//div[contains(@class,'paddTop10')]");

	By disclaimerModel = By.xpath(".//*[@id='DOC_TERMS_AND_CONDITION_DIV' and @class='modal fade in']");

	By docTermsAndConditionDivBody = By.id("DOC_TERMS_AND_CONDITION_DIV_BODY");

	By docTermsAndConditionDivCancelButton = By.id("DOC_TERMS_AND_CONDITION_DIV_cancelButton");

	By docTermsAndConditionDivAcceptButton = By.id("DOC_TERMS_AND_CONDITION_DIV_startDownloadBtn");

	By modal = By.xpath("//*[@class='modal fade in']//*[@class='modal-content']");

	By reminderErrorMessage = By.xpath(".//*[@id='taskRemiderInputID_pID' and contains(@style,'display: block;')]");

	By reminderDateType = By.id("taskReminderDateType");

	By closeButtonForReminder = By.xpath("//div[@class='pull-left paddTop2']//i[@class='icon icon-remove linkblack']");

	By listOfRemindersInViewTaskModal = By.xpath(".//*[@id='editRemiderList']//span[@class='paddRight5']");

	By closeButtonForReminderInViewTask = By
			.xpath(".//*[@id='task_inline_reminderDivID']//i[@class='icon icon-remove linkblack']");

	By reminderErrorMessageInViewTask = By
			.xpath(".//*[@id='inlineEdit_taskRemiderInputID_pID' and not(contains(@style,'display: none;'))]");

	By reminderDateTypeInViewTask = By.id("inlineEdit_taskReminderDateType");

	By personalTab = By.id("PERSONAL_TASKS");

	By editIconOfStartDate = By.xpath(".//*[@id='task_inline_taskStartDate' and @class='edit-content']");

	By editIconOfDueDate = By.xpath(".//*[@id='task_inline_taskDueDate' and @class='edit-content']");

	By moreActionOfTaskModule = By
			.xpath("//span[contains(@class,'dropdown inlineBlock margLeft10')]//a[@data-original-title='"
					+ TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	By importModal = By.xpath(".//*[@id='task_module_import_MODAL' and contains(@class,'fade in')]");

	By importModalClosed = By.xpath(".//*[@id='task_module_import_MODAL' and contains(@class,'fade')]");

	By browseFileInImportModal = By.id("task_module_importModal_FileUploadDivID");

	By fileDoneInImportModal = By
			.xpath(".//*[@id='progressbar_div']/span[normalize-space()='" + TaskLabels.TASKS_DONE + "']");

	By importDueDateInImportModal = By.id("importDueDates");

	By importAssigneesInImportModal = By.id("importAssignees");

	By nextButtonInImportModal = By.id("task_module_import_MODAL_next");

	By viewPreviewInImportModal = By.xpath("//a[normalize-space()='" + TaskLabels.TASKS_VIEW_PREVIEW + "']");

	By importButtonInImportModal = By.xpath(
			".//*[@id='task_module_import_MODAL_next' and normalize-space()='" + TaskLabels.TASKS_IMPORTTITLE + "']");

	By userTitleInMinicard = By.xpath(".//a[@class='usertitle']");
	By startDateInViewBtn = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_STARTDATE']");
	By dueDateInViewBtn = By.xpath(".//*[@id='ORDER_BY_TYPE_TASK_DUEDATE']");
	By listViewInViewBtn = By.xpath(".//*[@id='TASK_COLUMN_VIEW']/a");
	By tdOfGroupByInTimeline = By.xpath(".//*[@class='dhx_matrix_scell']");
	By startDateDropdown = By.id("COLUMN_ORDER_BY_TYPE_TASK_STARTDATE");
	By dueDateDropdown = By.id("COLUMN_ORDER_BY_TYPE_TASK_DUEDATE");
	By noneList = By.xpath(".//strong[text()='None']");
	By taskNameInTimeline = By.xpath(".//tr//*[@class='TruncateTxt']");
	By listNameInTimeline = By.xpath("//tr/td[@class='dhx_matrix_scell']//strong");
	By listTdsInTimeline = By.xpath("//td[1 and @class='dhx_matrix_scell']");
	By sortingIndex = By.xpath(".//*[@id='taskDataTableHead']//*[contains(@class,'taskColHead')]/div");
	By groupByBtnLinks = By.xpath(".//*[@id='taskGroupByOptions']//a");
	By assigneeNames = By.xpath(".//div[@class='overflowHidden lineHeightNormal']//a/strong");
	By userMinicard = By.xpath(".//div[@class='userinfoDropdown']");
	By unassignedAssignee = By.xpath(".//*[@id='taskTimelineScheduler']//*[contains(text(),'Unassigned')]");
	By taskMiddlePannel = By.id("taskHomeLeftPaneID");

	By userAvatarsInTimeline = By.id("imgTagID_0");
	By assigneeBlocksInTimeline = By.xpath(".//*[@id='taskTimelineScheduler']//tr//td[@class='dhx_matrix_scell']");
	By viewBtnDropdown = By.id("taskOrderByOptions");
	By groupByDropdown = By.id("taskGroupByOptions");

	By moreActionIcon = By
			.xpath(".//a[@data-original-title='" + TaskLabels.TASKS_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "']");

	By moreActionModal = By.xpath(".//*[@id='taskHomeLeftPaneID']//span/ul");

	By importFileBrowse = By.xpath(".//input[@id='task_module_importModal_FileUploadDivID']");

	By nextImportBtn = By.id("task_module_import_MODAL_next");

	By viewPreview = By
			.xpath(".//*[@id='taskImportFromExcelFinalStepForm']//*[text()='" + TaskLabels.TASKS_VIEW_PREVIEW + "']");

	By closeButtonPrint = By.xpath(".//button[text()='" + TaskLabels.TASKS_CLOSE + "']");

	By moreActionModalOfTask = By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//ul");

	By taskPanel = By.xpath(".//body[@class='task normalScreenMode']//div[@id='taskHomeLeftPaneID']");

	/**
	 * Instantiates a new tasks web.
	 *
	 * @param driver
	 *        the driver
	 */

	public TasksWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void clickHeaderViewButton() throws InterruptedException
	{
		WebElement view = findClickableElement(viewButton, Speed.slow);
		view.click();
	}

	@Override
	public void clickListFromGroupMenu() throws IOException, InterruptedException
	{
		WebElement groupMenu = findVisibleElement(groupByList, Speed.slow);
		groupMenu.click();
	}

	@Override
	public void deleteAllSavedFilters() throws InterruptedException
	{
		try
		{
			if (isDisplayed(filterDisableSeeAllSavedFilters))
			{
				System.out.println("No filters");
			}
			else
			{
				findClickableElement(seeAllSavedFilters).click();
				findPresentElement(taskModuleSeeSavedFiltersLink, Speed.slow);
				if (isDisplayed(saveFilterModel))
				{
					List<WebElement> elements = driver.findElements(removeButtonInDeleteSavedFilter);
					int totalFilters = elements.size();
					for (int i = 1; i <= totalFilters; i++)
					{
						findVisibleElement(saveFilterModel);
						findVisibleElement(removeButtonInDeleteSavedFilter).click();
						findVisibleElement(deletePopUp, Speed.slow);
						findVisibleElement(deleteSavedFilterButton, Speed.slow).click();
						findPresentElement(deletePopUpDisabled, Speed.slow);
						findPresentElement(deleteSavedFilterPopUpDisabled, Speed.slow);
					}

				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getCause());
		}
	}

	@Override
	public void clickHeaderFilterButton() throws InterruptedException
	{
		try
		{
			if (isDisplayed(filterButtonClosed))
			{
				System.out.println("Filter is already opened");

			}
			else
			{
				WebElement group = findVisibleElement(filterButton, Speed.slow);
				group.click();

			}
		}
		catch (Exception e)
		{
			System.out.println(e.getCause());
		}
	}

	@Override
	public void clickHeaderGroupButton() throws InterruptedException
	{
		findVisibleElement(groupButton, Speed.slow);
		WebElement group = findVisibleElement(groupButton, Speed.slow);
		group.click();
	}

	// Priority
	@Override
	public void clickCardViewFromViewMenu() throws IOException, InterruptedException
	{
		findClickableElement(viewSwitchCardView).click();
	}

	@Override
	public void clickPriorityFromViewMenu() throws IOException, InterruptedException
	{
		findClickableElement(viewSwitchCardView).click();
	}

	@Override
	public void clickStatusFromGroupMenu() throws IOException, InterruptedException
	{
		findClickableElement(groupByStatus).click();
	}

	@Override
	public void clickAssigneeFromGroupMenu() throws IOException, InterruptedException
	{
		findClickableElement(groupByAssignee).click();
	}

	@Override
	public void clickDueDateFromGroupMenu() throws IOException, InterruptedException
	{
		findClickableElement(groupByDueDate).click();
	}

	@Override
	public void clickPriorityFromGroupMenu() throws IOException, InterruptedException
	{
		findClickableElement(groupByPriority).click();
	}

	@Override
	public void clickNoneFromGroupMenu() throws IOException, InterruptedException
	{
		findClickableElement(groupByNone).click();
	}

	@Override
	public void clickHeaderAddButton()
	{
		WebElement add = findClickableElement(addButton, Speed.slow);
		add.click();
	}

	@Override
	public void clickOnAddNewListLink() throws IOException
	{
		WebElement addListLink = findVisibleElement(addTaskAddListLink, Speed.slow);
		addListLink.click();
	}

	/**
	 * Enter task title.
	 *
	 * @param title
	 *        the title
	 */
	@Override
	public void setTaskTitle(String title)
	{
		WebElement taskTitle = findVisibleElement(addTaskAddTitle, Speed.slow);
		taskTitle.clear();
		taskTitle.sendKeys(title.trim());
	}

	/**
	 * Enter task description.
	 *
	 * @param description
	 *        the description
	 */
	@Override
	public void setTaskDescription(String description)
	{
		WebElement taskDescription = findVisibleElement(addTaskAddDescription, Speed.slow);
		taskDescription.clear();
		taskDescription.sendKeys(description.trim());
	}

	@Override
	public void setTaskDueDate(String dueDate)
	{
		WebElement taskDuedate = findVisibleElement(addTaskModalEnterDueDate, Speed.slow);
		taskDuedate.clear();
		taskDuedate.sendKeys(dueDate);
		taskDuedate.sendKeys(Keys.TAB);
	}

	@Override
	public void setTaskAssignees(String assignees)
	{
		WebElement taskAssignee = findVisibleElement(addTaskAddAssignee, Speed.slow);
		taskAssignee.sendKeys(getUserData(assignees));
		if (assignees.contains("@"))
		{
			selectOptionFromAutoSuggest(assignees);
		}
		else
		{
			selectUserNameFromAutoSuggest(assignees);
		}
	}

	@Override
	public void setTaskAssignees(String... assignees)
	{
		WebElement taskAssignee = findVisibleElement(addTaskAddAssignee, Speed.slow);
		for (int i = 0; i < assignees.length; i++)
		{
			taskAssignee.sendKeys(getUserData(assignees[i]));
			if (assignees[i].contains("@"))
			{
				selectOptionFromAutoSuggest(assignees[i]);
			}
			else
			{
				selectUserNameFromAutoSuggest(assignees[i]);
			}
		}

	}

	@Override
	public void attachFileInTask(String filePath)
	{
		WebElement browse = null;
		WebElement upload = null;
		By uploadedFile = By.xpath(
				"(.//*[@id='dndTaskAddEditAttachmentsID']//div[normalize-space(.)='" + filePath.trim() + "'])[1]");
		WebElement attachment = findVisibleElement(addTaskAttachmentsTab);
		try
		{

			if (isDisplayed(addTaskAttachmentsTab))
			{
				attachment.click();
				if (isDisplayed(attachFileUploadButton))
				{
					browse = findVisibleElement(attachFileUploadButton);
					browse.click();
					addAttachmentFromUploadTab(filePath);
				}
				if (isDisplayed(detailsTabInAddTaskModel))
				{
					findVisibleElement(detailsTabInAddTaskModel).click();
				}

			}

		}
		catch (Exception e)
		{
			System.out.println(e.getCause());
		}
	}

	/**
	 * Select priority.
	 *
	 * @param priority
	 *        the priority
	 */
	@Override
	public void setPriority(String priority)
	{
		WebElement taskPriority = findVisibleElement(addTaskAddPriority);
		Select prioritySelect = new Select(taskPriority);
		prioritySelect.selectByVisibleText(priority);
	}

	/**
	 * Select list.
	 *
	 * @param list
	 *        the list
	 */
	@Override
	public void setList(String list)
	{
		WebElement taskList = findVisibleElement(addTaskAddList);
		Select listSelect = new Select(taskList);
		listSelect.selectByVisibleText(list);
	}

	/**
	 * Select status.
	 *
	 * @param status
	 *        the status
	 */
	@Override
	public void setStatus(String status)
	{
		WebElement taskStatus = findVisibleElement(addTaskAddStatus);
		Select statusSelect = new Select(taskStatus);
		statusSelect.selectByVisibleText(status);
	}

	/**
	 * Enter tag.
	 *
	 * @param tag
	 *        the tag
	 */
	@Override
	public void setTag(String tag)
	{
		WebElement taskTags = findClickableElement(addTaskAddTags);
		taskTags.clear();
		taskTags.sendKeys(tag.trim());
		taskTags.sendKeys(Keys.ENTER);

		findVisibleElement(addTaskTableLabel).click();
	}

	/**
	 * Click on add to add reminder.
	 */
	@Override
	public void clickOnAddToAddReminder()
	{
		WebElement addButton = findClickableElement(addTaskAddEnabledAddRemainderButton);
		addButton.click();
	}

	/**
	 * Enter remainder.
	 *
	 * @param remainderDay
	 *        the remainder day
	 */
	@Override
	public void setReminder(int remainderDay)
	{
		if (remainderDay != 1)
		{
			if (isDisplayed(reminderRemoveLink, Speed.slow))
			{
				findVisibleElement(reminderRemoveLink, Speed.slow).click();
			}
			findVisibleElement(addTaskAddEnabledAddRemainderButton, Speed.slow).click();
			WebElement taskReminder = findVisibleElement(addTaskAddRemainder, Speed.slow);
			taskReminder.click();
			taskReminder.sendKeys(Keys.BACK_SPACE);
			taskReminder.sendKeys(Integer.toString(remainderDay));
			clickOnAddReminder();
		}
	}

	/**
	 * Click on add reminder.
	 */
	@Override
	public void clickOnAddReminder()
	{
		WebElement addToAddRemainderButton = findVisibleElement(addTaskAddRemainderOKButton, Speed.slow);
		addToAddRemainderButton.click();
	}

	/**
	 * Click on cancel reminder.
	 */
	@Override
	public void clickOnCancelReminder()
	{
		WebElement addToCancelRemainderButton = findVisibleElement(addTaskAddRemainderCancelButton);
		addToCancelRemainderButton.click();
	}

	/**
	 * Click on full screen.
	 */
	@Override
	public void clickOnFullScreen()
	{
		System.out.println(driver);
		WebElement fullScreen = findVisibleElement(globalFullScreen);
		fullScreen.click();
	}

	/**
	 * Created : Updated : 12th April 2018 by jyoti.raj Click on add in add task
	 * modal.
	 */
	@Override
	public void clickOnAddInAddTaskModal()
	{
		WebElement addButton = findVisibleElement(addTaskModalEnableAddButton, Speed.slow);
		addButton.click();
		if (!isDisplayed(errorContainer))
		{
			findPresentElement(addTaskModalClosed);
		}

	}

	@Override
	public void clickOnCancelInModal() throws InterruptedException
	{
		WebElement cancelButton = findVisibleElement(addTaskModalCancelButton);
		cancelButton.click();
		findPresentElement(addTaskModalClosed);
	}

	@Override
	public void clickOnMoreActionInModal()
	{
		WebElement ele = findVisibleElement(columnViewTaskViewHeaderMoreAction);
		ele.click();
	}

	/**
	 * Delete all tasks.
	 *
	 * @throws InterruptedException
	 */
	@Override
	public void deleteAllTasks()
	{
		WebElement moreAction = null;
		WebElement deleteOption = null;
		WebElement deleteButton = null;
		try
		{
			findPresentElement(mainSection);
			if (isDisplayed(noTasksMessage))
			{
				System.out.println("No tasks is present");
			}
			else
			{
				List<WebElement> elementsRoot = driver.findElements(totalTasks);
				// System.out.println(elementsRoot.size());
				ArrayList<String> linkTxt = new ArrayList<>();
				for (WebElement elm : elementsRoot)
				{
					linkTxt.add(elm.getText());
				}
				for (int i = 0; i < linkTxt.size(); i++)
				{
					// System.out.println(linkTxt.get(i));
					// System.out.println("Task Name --- > " + linkTxt.get(i));
					findClickableElement(By.linkText(linkTxt.get(i).trim()), Speed.slow).click();
					moreAction = findVisibleElement(columnViewTaskViewHeaderMoreAction);
					moreAction.click();
					findPresentElement(menu);
					deleteOption = findVisibleElement(columnViewTaskViewOptionDelete);
					deleteOption.click();
					deleteButton = findVisibleElement(deleteTaskModalDeleteButton);
					findPresentElement(deleteDialog);
					deleteButton.click();
					findPresentElement(taskRowList);
				}
				findVisibleElement(noTasksMessage);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Select task.
	 *
	 * @param task
	 *        the task
	 * @throws InterruptedException
	 */
	@Override
	public void selectTask(String task)
	{

		WebElement searchBox = findPresentElement(searchTextbox, Speed.slow);
		searchBox.clear();
		searchBox.sendKeys(task);

		findVisibleElement(clearSearchBoxButton, Speed.slow);

		By taskLinkPath = By
				.xpath("(//*[@id='taskRowList']//*[normalize-space(text())='" + task.trim() + "'])[last()]");

		By cardViewTaskLink = By.xpath("//*[@id='cardViewWrapper']//*[normalize-space(text())='" + task.trim() + "']");
		WebElement taskLink;

		if (isDisplayed(taskLinkPath, Speed.slow))
		{
			taskLink = findVisibleElement(taskLinkPath, Speed.slow);
			taskLink.click();

			findPresentElement(columnViewTaskViewPopup);
		}
		if (isDisplayed(cardViewTaskLink, Speed.slow))
		{
			taskLink = findVisibleElement(cardViewTaskLink, Speed.slow);
			taskLink.click();

			findPresentElement(cardViewTaskPopUp);
		}
	}

	/**
	 * ************************** ALL VERIFICATION METHODS
	 * ************************************.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyAllTasksTab()
	{
		WebElement allTasks = findVisibleElement(allTaskTab);
		return allTasks.isDisplayed();
	}

	/**
	 * @updated by vivek mishra
	 * @updated date 30/01/2018
	 */
	@Override
	public boolean verifyTaskVisibility(String task)
	{
		sendTextInQuickSearchTextBox(task);
		findPresentElement(taskRowList, Speed.slow);
		// return(findVisibleElement(By.xpath("//div[@ class='colTitle']//a[text()='" +
		// task.trim() +"']"),Speed.slow).getText().trim().equals(task.trim()));
		return (isDisplayed(By.xpath("//div[@ class='colTitle']//a[text()='" + task.trim() + "']")));
	}

	/**
	 * Verify assign to me tab.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyAssignToMeTab()
	{
		WebElement assignedToMe = findVisibleElement(assignedToMeTab);
		return assignedToMe.isDisplayed();
	}

	/**
	 * Verify created by me tab.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyCreatedByMeTab()
	{
		WebElement createdByMe = findVisibleElement(createdByMeTab);
		return createdByMe.isDisplayed();
	}

	/**
	 * Verify favourites tab.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyFavouritesTab()
	{
		WebElement favourites = findVisibleElement(favouritesTab);
		return favourites.isDisplayed();
	}

	/**
	 * Verify header add button.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyHeaderAddButton()
	{
		WebElement add = findVisibleElement(addButton);
		return add.isDisplayed();
	}

	/**
	 * Verify header view button.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyHeaderViewButton()
	{
		WebElement view = findVisibleElement(viewButton);
		return view.isDisplayed();
	}

	/**
	 * Verify header group button.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyHeaderGroupButton()
	{
		WebElement group = findVisibleElement(groupButton);
		return group.isDisplayed();
	}

	/**
	 * Verify header filter button.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyHeaderFilterButton()
	{
		WebElement filter = findVisibleElement(filterButton);
		return filter.isDisplayed();
	}

	/**
	 * Verify search field.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifySearchField()
	{
		WebElement search = findVisibleElement(searchTextbox);
		return search.isDisplayed();
	}

	/**
	 * Verify full screen icon.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyGlobalFullScreenIcon()
	{
		WebElement fullScreen = findVisibleElement(globalFullScreen);
		return fullScreen.isDisplayed();
	}

	@Override
	public boolean verifyGlobalMoreScreenIcon()
	{
		WebElement fullScreen = findVisibleElement(globalMoreAction);
		return fullScreen.isDisplayed();
	}

	/**
	 * Verify is full screen.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean verifyIsFullScreen()
	{
		WebElement fullScreenExample = findVisibleElement(fullScreen);
		return fullScreenExample.isDisplayed();
	}

	/**
	 * ********* ALL METHODS FOR VERIFY TASK METHODS *********.
	 *
	 * @param taskTitle
	 *        the task title
	 * @return true, if successful
	 */

	@Override
	public boolean verifyViewTaskTitle(String taskTitle)
	{
		WebElement viewTaskTitle = findVisibleElement(columnViewTaskViewTitle);
		String title = viewTaskTitle.getText().trim();
		return title.equals(taskTitle.trim());
	}

	/**
	 * Verify view task description.
	 *
	 * @param taskDescription
	 *        the task description
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDescription(String taskDescription)
	{
		WebElement viewTaskDescription = findVisibleElement(columnViewTaskViewDescription);
		String description = viewTaskDescription.getText().trim();
		return description.equals(taskDescription.trim());
	}

	/**
	 * Verify view task details status.
	 *
	 * @param taskStatus
	 *        the task status
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsStatus(String taskStatus)
	{
		WebElement viewTaskStatus = findVisibleElement(columnViewTaskViewStatusValue);
		String status = viewTaskStatus.getText().trim();
		return status.equals(taskStatus.trim());
	}

	/**
	 * Verify view task details priority.
	 *
	 * @param taskPriority
	 *        the task priority
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsPriority(String taskPriority)
	{
		WebElement viewTaskPriority = findVisibleElement(columnViewTaskViewPriorityValue);
		String priority = viewTaskPriority.getText().trim();
		return priority.equals(taskPriority.trim());
	}

	/**
	 * Verify view task details due date.
	 *
	 * @param taskDuedate
	 *        the task duedate
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsDueDate(String taskDuedate)
	{
		WebElement viewTaskDuedate = findVisibleElement(columnViewTaskViewDueDateValue);
		String duedate = viewTaskDuedate.getText().trim();
		taskDuedate = dateConversion(taskDuedate, dateformat);
		return duedate.equals(taskDuedate.trim());
	}

	/**
	 * Verify view task details list.
	 *
	 * @param taskList
	 *        the task list
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsList(String taskList)
	{
		WebElement viewTaskList = findVisibleElement(columnViewTaskViewListValue);
		String list = viewTaskList.getText().trim();
		return list.equals(taskList.trim());
	}

	/**
	 * Verify view task details assignee.
	 *
	 * @param taskAssignees
	 *        the task assignees
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsAssignee(String... taskAssignees)
	{
		WebElement viewTaskAssignee = findVisibleElement(columnViewTaskViewAssigneeValue, 20);
		String assignees = viewTaskAssignee.getText().trim();
		String[] singleAssignee = assignees.split(",");
		return Arrays.equals(singleAssignee, taskAssignees);
	}

	/**
	 * Verify view task tags.
	 *
	 * @param taskTags
	 *        the task tags
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskTags(String... taskTags)
	{
		String[] tags = null;
		int i = 0;
		try
		{
			List<WebElement> viewTaskTags = driver.findElements(columnViewTaskViewTagsSectionValue);
			for (WebElement element : viewTaskTags)
			{
				tags[i] = element.getText();
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return Arrays.equals(tags, taskTags);
	}

	/**
	 * Verify view task reminder.
	 *
	 * @param taskRemainder
	 *        the task remainder
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskReminder(int... taskRemainder)
	{
		int[] remainder = null;
		int i = 0;
		try
		{
			List<WebElement> viewTaskTags = driver.findElements(columnViewTaskViewTotalRemainder);
			for (WebElement element : viewTaskTags)
			{
				String[] remainderParts = element.getText().split(" ");
				remainder[i] = Integer.parseInt(remainderParts[0]);
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return Arrays.equals(remainder, taskRemainder);
	}

	@Override
	public boolean verifyAttachmentInViewTask(String taskAttachment)
	{
		ArrayList<String> list = new ArrayList<>();
		try
		{
			findPresentElement(columnViewTaskViewAttachment, 200);
			List<WebElement> viewTaskTags = driver.findElements(columnViewTaskViewAttachment);
			for (WebElement element : viewTaskTags)
			{
				String s = element.getAttribute(TaskLabels.TASKS_ADDTASK_TITLE.toLowerCase());
				System.err.println(s.trim());
				list.add(s);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return list.contains(taskAttachment);
	}

	@Override
	public void setTaskAddData(Map<String, String> addTask)
	{

		for (Map.Entry<String, String> map : addTask.entrySet())
		{
			String key = map.getKey().toString();
			String value = map.getValue();
			// System.out.println("Map key :: " + key + " :: Map value :: " + value);
			if (key.contains("_"))
			{
				key = key.replaceAll("_", " ").trim();
			}
			String lowerCase = key.toLowerCase();
			if (TaskLabels.TASKS_ADDTASK_TITLE.toLowerCase().equals(lowerCase))
			{
				setTaskTitle(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DESCRIPTION.toLowerCase().equals(lowerCase))
			{
				setTaskDescription(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DUEDATE.toLowerCase().equals(lowerCase))
			{
				setTaskDueDate(value);
			}
			else if (TaskLabels.TASKS_STARTDATE.toLowerCase().equals(lowerCase))
			{
				setTaskStartDate(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_ASSIGNEE.toLowerCase().equals(lowerCase))
			{
				setTaskAssignees(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_PRIORITY.toLowerCase().equals(lowerCase))
			{
				setPriority(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_LIST.toLowerCase().equals(lowerCase))
			{
				setList(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_STATUS.toLowerCase().equals(lowerCase))
			{
				setStatus(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_TAGS.toLowerCase().equals(lowerCase))
			{
				setTag(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_REMINDER.toLowerCase().equals(lowerCase))
			{
				setReminder(Integer.parseInt(value));
			}
			else if (TaskLabels.TASKS_ADDTASK_ATTACHMENT.toLowerCase().equals(lowerCase))
			{
				attachFileInTask(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_SITE.toLowerCase().equals(lowerCase))
			{
				setSite(value);
			}
			else
			{
				System.out.println("No any Matches find");
			}
		}
	}

	@Override
	public void addTask(TaskAddDataPage add) throws IOException, InterruptedException
	{
		if (!("".equals(add.getTaskTitle())))
		{
			setTaskTitle(add.getTaskTitle());
		}
		if (!("".equals(add.getTaskDescription())))
		{
			setTaskDescription(add.getTaskDescription());
		}
		if (!("".equals(add.getTaskContents())))
		{
			setTaskDescription(add.getTaskContents());
		}
		if (!("".equals(add.getTaskDueDate())))
		{
			setTaskDueDate(add.getTaskDueDate());
		}
		if (!("".equals(add.getTaskStartDate())))
		{
			setTaskStartDate(add.getTaskStartDate());
		}
		if (!("".equals(add.getAssignee())))
		{
			setTaskAssignees(add.getAssignee());
		}
		if (!("".equals(add.getTaskPriority())))
		{
			setPriority(add.getTaskPriority());
		}
		if (!("".equals(add.getTaskList())))
		{
			setList(add.getTaskList());
		}
		if (!("".equals(add.getTaskStatus())))
		{
			setStatus(add.getTaskStatus());
		}
		if (add.getTaskReminder() != 0)
		{
			setReminder(add.getTaskReminder());
		}
		if (!("".equals(add.getTaskTag())))
		{
			setTag(add.getTaskTag());
		}
		if (!("".equals(add.getAttachment())))
		{
			attachFileInTask(add.getAttachment());
		}
	}

	/**
	 * this is applicable for all the views Verify view task details.
	 *
	 * @param add
	 *        the add
	 * @param total
	 *        the total
	 * @return true, if successful
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyViewTaskDetails(TaskAddDataPage add, int total) throws InterruptedException
	{
		// Thread.sleep(2000);
		int counter = 0;
		if (!StringUtils.isBlank(add.getTaskTitle()) && verifyViewTaskTitle(add.getTaskTitle()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskStatus()) && verifyViewTaskDetailsStatus(add.getTaskStatus()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskDueDate()) && verifyViewTaskDetailsDueDate(add.getTaskDueDate()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getAssignee()) && verifyViewTaskDetailsAssignee(add.getAssignee()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskPriority()) && verifyViewTaskDetailsPriority(add.getTaskPriority()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskList()) && verifyViewTaskDetailsList(add.getTaskList()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskContents()) && verifyViewTaskDescription(add.getTaskContents()))
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getTaskTag()) && verifyViewTaskTags(add.getTaskTag()))
		{
			counter++;
		}
		if ((add.getTaskReminder() != 0) && verifyViewTaskReminder())
		{
			counter++;
		}
		if (!StringUtils.isBlank(add.getAttachment()) && verifyAttachmentInViewTask(add.getAttachment()))
		{
			counter++;
		}
		// System.out.println(total);
		// System.out.println(counter);
		if (total == counter)
		{
			return true;
		}
		return false;
	}

	/**
	 * this is applicable for all the views
	 *
	 * @param map
	 * @return
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyViewTaskDetailsviaMap(Map<String, String> map) throws InterruptedException
	{
		int counter = 0;
		// Thread.sleep(2000);
		for (Map.Entry<String, String> taskMap : map.entrySet())
		{
			String key = taskMap.getKey();
			String value = taskMap.getValue();
			if (TaskLabels.TASKS_ADDTASK_TITLE.equals(key))
			{
				if (verifyViewTaskTitle(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_STATUS.equals(key))
			{
				if (verifyViewTaskDetailsStatus(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_STARTDATE.equals(key))
			{
				if (verifyTaskStartDate(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_DUEDATE.equals(key))
			{
				if (verifyViewTaskDetailsDueDate(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_ASSIGNEE.equals(key))
			{
				if (verifyViewTaskDetailsAssignee(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_PRIORITY.equals(key))
			{
				if (verifyViewTaskDetailsPriority(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_LIST.equals(key))
			{
				if (verifyViewTaskDetailsList(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_DESCRIPTION.equals(key))
			{
				if (verifyViewTaskDescription(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_TAGS.equals(key))
			{
				if (verifyViewTaskTags(value))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_REMINDER.equals(key))
			{
				if (verifyViewTaskReminder(Integer.parseInt(value)))
				{
					counter++;
				}
			}
			else if (TaskLabels.TASKS_ADDTASK_ATTACHMENT.equals(key))
			{
				if (verifyAttachmentInViewTask(value))
				{
					counter++;
				}
			}
			else
			{
				System.out.println(key + " not found");
			}
		}

		int size = map.size();
		// System.out.println(size);
		// System.out.println(counter);
		if (size == counter)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyViewTaskTitle(String title, String newTitle) throws IOException
	{
		WebElement editTask = null;
		WebElement editTaskTitleSaveButton = null;
		WebElement ele = findVisibleElement(columnViewTaskViewTitle);
		if (ele.isDisplayed())
		{
			ele.click();
			editTask = findClickableElement(editTaskTitle, Speed.slow, 3);
			editTask.clear();
			editTask.sendKeys(newTitle);
			editTaskTitleSaveButton = findClickableElement(editTaskTitleSubmit, Speed.slow);
			if (editTaskTitleSaveButton.isDisplayed())
			{
				editTaskTitleSaveButton.click();
			}
			findPresentElement(editTaskTitleWaitTillSubmit);

			return verifyViewTaskTitle(newTitle);
		}
		return false;
	}

	@Override
	public void editTask(Map<String, String> list)
	{
		for (Map.Entry<String, String> entry : list.entrySet())
		{
			String key = entry.getKey().toString();
			String value = entry.getValue();
			// System.out.println("key, " + key + " value " + value);
			if (key.contains("_"))
			{
				key = key.replaceAll("_", " ").trim();
			}
			String lowerCase = key.toLowerCase();
			if (TaskLabels.TASKS_ADDTASK_TITLE.toLowerCase().equals(lowerCase))
			{
				editTaskTitle(value.trim());
			}
			else if (TaskLabels.TASKS_ADDTASK_LIST.toLowerCase().equals(lowerCase))
			{
				editTaskList(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_STATUS.toLowerCase().equals(lowerCase))
			{
				editTaskStatus(value);
			}
			else if (TaskLabels.TASKS_STARTDATE.toLowerCase().equals(lowerCase))
			{
				editTaskStartDate(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DUEDATE.toLowerCase().equals(lowerCase))
			{
				editTaskDueDate(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_ASSIGNEE.toLowerCase().equals(lowerCase))
			{
				editTaskAssignee(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_PRIORITY.toLowerCase().equals(lowerCase))
			{
				editTaskPriority(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DESCRIPTION.toLowerCase().equals(lowerCase))
			{
				editTaskDescription(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_TAGS.toLowerCase().equals(lowerCase))
			{
				editTaskTag(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_REMINDER.toLowerCase().equals(lowerCase))
			{
				editReminder(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_ATTACHMENT.toLowerCase().equals(lowerCase))
			{
				editAttachment(value);
			}
			else
			{
				System.out.println(key + " not found.");
			}

		}
	}

	/**
	 * Date conversion.
	 *
	 * @param date
	 *        the date
	 * @param format
	 *        the format
	 * @return the string
	 */
	@Override
	public String dateConversion(String date, String format)
	{
		SimpleDateFormat sdfmt2 = new SimpleDateFormat(format);
		Date dDate = new Date(date);
		String strOutput = sdfmt2.format(dDate);
		// System.out.println(strOutput);
		return strOutput;
	}

	@Override
	public void clickOnCloseInViewTask()
	{
		WebElement viewTaskAssignee = findVisibleElement(columnViewTaskViewHeaderClose, Speed.slow);
		viewTaskAssignee.click();
	}

	@Override
	public boolean saveCurrentFilters(String string) throws IOException, InterruptedException
	{
		clickHeaderFilterButton();
		System.out.println("saveCurrentFilters method");

		WebElement saveFilterAs = findVisibleElement(filterMenuSaveCurrentFilterAs);
		System.out.println(saveFilterAs.getText());
		if (saveFilterAs.isDisplayed())
		{
			saveFilterAs.click();
		}
		try
		{
			// Thread.sleep(2000);
			if (isDisplayed(saveFilterModal, Speed.slow)
					&& !findVisibleElement(saveCurrentFilterButtonDisabled).isEnabled())
			{
				// Thread.sleep(1000);
				findVisibleElement(saveFilterTextbox, Speed.slow).sendKeys(string);
				findVisibleElement(saveButtonInSaveFilter).click();
				findPresentElement(saveFilterModalClosed);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean verifyAddListCancelButton()
	{
		/*
		 * WebElement listCancelButton = findVisibleElement(addTaskAddListCancelButton);
		 * return listCancelButton.isDisplayed();
		 */
		return isDisplayed(addTaskAddListCancelButton);
	}

	@Override
	public boolean verifyAddListSubmittButtonDisable()
	{
		/*
		 * WebElement listSubmittButton =
		 * findVisibleElement(addTaskAddListSubmittButtonDisable); return
		 * listSubmittButton.isDisplayed();
		 */
		return isDisplayed(addTaskAddListSubmittButtonDisable);
	}

	@Override
	public boolean verifyAddListSubmittButtonEnable()
	{
		/*
		 * WebElement listSubmittButton =
		 * findVisibleElement(addTaskAddListSubmittButtonEnable); return
		 * listSubmittButton.isDisplayed();
		 */
		return isDisplayed(addTaskAddListSubmittButtonEnable);
	}

	@Override
	public void setTaskList(String list)
	{
		WebElement taskList = findVisibleElement(addTaskAddListTextBox);
		taskList.sendKeys(list);
	}

	@Override
	public void clickAddListSubmittButtonEnable()
	{
		WebElement listSubmittButton = findVisibleElement(addTaskAddListSubmittButtonEnable);
		listSubmittButton.click();
	}

	@Override
	public void clickAddListCancelButton()
	{
		WebElement listCancelButton = findVisibleElement(addTaskAddListCancelButton);
		listCancelButton.click();
	}

	@Override
	public boolean verifyAddNewListLink() throws IOException
	{
		/*
		 * WebElement addListLink = findVisibleElement(addTaskAddListLink); return
		 * addListLink.isDisplayed();
		 */
		return isDisplayed(addTaskAddListLink);
	}

	@Override
	public boolean verifycolumnViewTaskViewOptionShare()
	{
		/*
		 * WebElement element = findVisibleElement(columnViewTaskViewOptionShare);
		 * return element.isDisplayed();
		 */
		return isDisplayed(columnViewTaskViewOptionShare);
	}

	@Override
	public boolean verifycolumnViewTaskViewOptionExportTOPdf()
	{
		/*
		 * WebElement element = findVisibleElement(columnViewTaskViewOptionExportTOPdf);
		 * return element.isDisplayed();
		 */
		return isDisplayed(columnViewTaskViewOptionExportTOPdf);
	}

	@Override
	public boolean verifycolumnViewTaskViewOptionPrintPreview()
	{
		/*
		 * WebElement element =
		 * findVisibleElement(columnViewTaskViewOptionPrintPreview); return
		 * element.isDisplayed();
		 */
		return isDisplayed(columnViewTaskViewOptionPrintPreview);
	}

	@Override
	public boolean verifycolumnViewTaskViewOptionDelete()
	{
		/*
		 * WebElement element = findVisibleElement(columnViewTaskViewOptionDelete);
		 * return element.isDisplayed();
		 */
		return isDisplayed(columnViewTaskViewOptionDelete);
	}

	@Override
	public boolean verifySavedFilters(String filterName) throws IOException, InterruptedException
	{
		try
		{
			if (verifySavedFilterMenu(TaskLabels.TASKS_FILTERS_SAVEDFILTERS, filterName)
					&& isDisplayed(By.xpath(".//*[@id='task_module_taskSavedFilterDivID']//a[normalize-space(.)='"
							+ filterName.trim() + "']")))
			{
				findClickableElement(By.xpath(".//*[@id='task_module_taskSavedFilterDivID']//a[normalize-space(.)='"
						+ filterName.trim() + "']")).click();

				return (isDisplayed(taskListDiv)) ? true : false;
			}
			// System.out.println("inside verifySavedFilters = =");
			// return false;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		return false;
	}

	@Override
	public boolean verifySavedFilterMenu(String collapse, String filterName) throws IOException, InterruptedException
	{
		// WebElement savedFilters = waitAndFindElement(
		// By.xpath(".//*[@id='accordion']//a[normalize-space(.)='" + collapse + "']"));
		WebElement savedFilters = findClickableElement(saveFilterCollapse);
		WebElement savedFilterName = findPresentElement(By.xpath(
				".//*[@id='task_module_taskSavedFilterDivID']//a[normalize-space(.)='" + filterName.trim() + "']"));
		if (!savedFilterName.isDisplayed())
		{
			savedFilters.click();
		}
		if (isDisplayed(By.xpath(
				".//*[@id='task_module_taskSavedFilterDivID']//a[normalize-space(.)='" + filterName.trim() + "']")))
		{
			return true;
		}
		if ("".equals(filterName) || null == filterName)
		{
			return findVisibleElement(noFilterAvailableMessage).isDisplayed();
		}
		else
		{
			return false;
		}
	}

	@Override
	public void saveCurrentFilters(String filterName, String options) throws IOException, InterruptedException
	{
		findClickableElement(filterMenuSaveCurrentFilterAs).click();
		WebElement saveFilterInputBox = findVisibleElement(saveFilterTextBox, Speed.slow);
		saveFilterInputBox.sendKeys(filterName);

		if (options != null && options.equals(TaskLabels.TASKS_SAVE))
		{
			findClickableElement(saveButtonInSaveFilter).click();
		}
		else if (!StringUtils.isBlank(options) && options.equalsIgnoreCase(TaskLabels.TASKS_CANCEL))
		{
			clickOnCancelButtonOfSaveFilterPopUp();
		}
	}

	@Override
	public boolean verifySelectedSiteInAddTask(String site)
	{
		String selectedOption = new Select(findVisibleElement(addTaskModalSiteList)).getFirstSelectedOption().getText()
				.trim();
		return selectedOption.equals(site.trim());
	}

	@Override
	public boolean verifySelectedStatusInAddTask(String status)
	{
		String selectedOption = new Select(findVisibleElement(addTaskAddStatus)).getFirstSelectedOption().getText()
				.trim();
		return selectedOption.equals(status.trim());
	}

	@Override
	public void clickOnCancelButtonOfSaveFilterPopUp() throws InterruptedException
	{
		try
		{
			WebElement cancelButton = findClickableElement(savefilterCancelButton, Speed.slow);
			if (isDisplayed(savefilterCancelButton) && cancelButton.isEnabled())
			{
				cancelButton.click();
			}
			else
			{
				System.out.println("Click on cancel button : ");
				findVisibleElement(savefilterCancelButton).click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getCause());
		}
	}

	@Override
	public int visibleTaskCount()
	{
		findVisibleElement(taskRowList, Speed.slow);
		List<WebElement> taskListData = driver.findElements(By.xpath(TASKLISTDIV + "//div[1]/a"));
		return taskListData.size();
	}

	@Override
	public boolean verifyStatusColorForColumnView(String statusName, String taskTitle, String color)
			throws IOException
	{
		findPresentElement(taskRowList);

		// scrollToElement(By.xpath("//div[@ class='colTitle']//a[text()='" + taskTitle
		// + "']"));
		WebElement taskElement = findVisibleElement(
				By.xpath("//div[@ class='colTitle']//a[text()='" + taskTitle.trim() + "']"), Speed.slow);

		String task = findVisibleElement(By.xpath("//div[@ class='colTitle']//a[text()='" + taskTitle.trim() + "']"))
				.getText().trim();
		String status = findVisibleElement(By.xpath("(//div[@ class='colTitle']//a[text()='" + taskTitle.trim()
				+ "']//following::div[contains(@class,'colStatus')])[1]")).getText().trim();
		String singleColor = gethashValueOfColor(By.xpath("//div[@ class='colTitle']//a[text()='" + taskTitle.trim()
				+ "']//ancestor::div[@class='taskColList normalRow clearfix']"), "border-left-color");
		System.out.println(task + ":::" + status + "::::" + singleColor);
		if ((singleColor.equalsIgnoreCase(color) && taskTitle.equalsIgnoreCase(task))
				&& statusName.equalsIgnoreCase(status))
		{
			return true;
		}
		else
		{
			return false;
		}

		// List<WebElement> ele = driver.findElements(totalTask);
		//
		// for (int i = 1; i <= ele.size(); i++)
		// {
		// findPresentElement( By.xpath(".//*[starts-with(@id,'taskRowDivID')]"));
		// String task = findVisibleElement(
		// By.xpath(".//*[starts-with(@id,'taskRowDivID')][" + i +
		// "]/div[2]//a")).getText();
		// String status = findVisibleElement(
		// By.xpath(".//*[starts-with(@id,'taskRowDivID')][" + i +
		// "]/div[6]")).getText();
		// String singleColor =
		// gethashValueOfColor(By.xpath(".//*[starts-with(@id,'taskRowDivID')][" + i +
		// "]"), "border-left-color");
		// System.out.println(task + ":::" + status + "::::" + singleColor);
		// if (singleColor.equalsIgnoreCase(color) && taskTitle.equalsIgnoreCase(task)
		// && statusName.equalsIgnoreCase(status))
		// {
		// return true;
		// }
		// }
		// return false;
	}

	@Override
	public String gethashValueOfColor(By xpath, String cssProperty)
	{
		String color = driver.findElement(xpath).getCssValue(cssProperty);
		System.out.println("Color ==== " + color);
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor;
	}

	/**
	 * @modified by vivek mishra
	 * @modified date 01/02/2018
	 */
	@Override
	public void clickOnAddTaskButtonInModel()
	{
		// findVisibleElement(addTaskModalEnterDueDateLabel,Speed.slow).click();
		findClickableElement(taskAddButton, Speed.slow).click();
		findPresentElement(addTaskModalClosed, Speed.slow);
	}

	@Override
	public boolean verifySortingTaskList(List<String> actualPriority, String groupingAction)
	{
		int index = 0;
		if (groupingAction.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_DUEDATE.replace("_", " ")))
		{
			index = 3;
		}
		else if (groupingAction.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_PRIORITY))
		{
			index = 5;
		}
		else if (groupingAction.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_STATUS))
		{
			index = 6;
		}
		List<WebElement> taskListData = driver.findElements(By.xpath(TASKLISTDIV + "/div//div[" + index + "]"));

		for (int i = 0; i < actualPriority.size(); i++)
		{
			for (int j = 0; j < taskListData.size(); j++)
			{
				if (!taskListData.get(j).getText().trim().equalsIgnoreCase(actualPriority.get(i)))
				{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean verifySingleListGroup(String list) throws IOException, InterruptedException
	{
		clickHeaderGroupButton();
		clickListFromGroupMenu();
		findVisibleElement(taskColListTitle, Speed.slow);
		By groupLink = By
				.xpath(".//div[contains(@class,'taskColListTitle')]//a[normalize-space(.)='" + list.trim() + "']");
		if (isDisplayed(groupLink))
		{
			findClickableElement(groupLink).click();
		}
		return verifyFilter(TaskLabels.TASKS_LIST, list);
	}

	@Override
	public boolean verifyFilter(String collapse, String filterSuboption) throws IOException
	{
		String columnClass = null;
		if (collapse.equalsIgnoreCase(TaskLabels.TASKS_PRIORITY))
		{
			columnClass = "colPriority";
		}
		else if (collapse.equalsIgnoreCase(TaskLabels.TASKS_STATUS))
		{
			columnClass = "colStatus";
		}
		else if (collapse.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_ASSIGNEE))
		{
			columnClass = "colAssigne";
		}
		else if (collapse.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_LIST))
		{
			columnClass = "colList";
		}
		else if (collapse.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_SITE))
		{
			columnClass = "colSite";
		}
		findVisibleElement(totalTask, Speed.slow);
		List<WebElement> taskListData = driver.findElements(
				By.xpath(".//*[starts-with(@id,'taskRowDivID')]/div[contains(@class,'" + columnClass + "')]"));
		for (WebElement element : taskListData)
		{
			if (!element.getText().trim().equalsIgnoreCase(filterSuboption))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean verifyAddListTextBox()
	{
		WebElement listTextBox = findVisibleElement(addTaskAddListTextBox);
		return listTextBox.isDisplayed();
	}

	@Override
	public void clickOnAddColumnButton()
	{
		WebElement ele = findVisibleElement(addColumnButton, Speed.slow);
		ele.click();
	}

	@Override
	public void enterColumnTitleInAddColumn(String colTitle)
	{
		WebElement ele = findVisibleElement(addColumnTextBox, Speed.slow);
		ele.sendKeys(colTitle);
	}

	@Override
	public void clickOnAddColumnSaveButton()
	{
		WebElement ele = findVisibleElement(addColumnSaveButton, Speed.slow);
		ele.click();
	}

	@Override
	public void clickOnAddColumnCancelButton()
	{
		WebElement ele = findVisibleElement(addColumnCancelButton, Speed.slow);
		ele.click();
	}

	@Override
	public void setSite(String site)
	{
		WebElement taskSite = findVisibleElement(addTaskModalSiteList);
		Select siteSelect = new Select(taskSite);
		siteSelect.selectByVisibleText(site);
	}

	@Override
	public void clickOnCreatedByMeTab() throws InterruptedException
	{
		WebElement ele = findVisibleElement(createdByMeTab);
		ele.click();
	}

	/**
	 * Ref : TC1132, TC1144.
	 *
	 * @return true, if successful
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean verifyDisabledClearFilters() throws IOException
	{
		WebElement element2 = findVisibleElement(clearFilterLink);
		String property = element2.getAttribute("class");
		if (property.contains("disabled"))
		{
			return true;
		}
		return false;
	}

	/**
	 * Ref : TC0992, TC1132 (Used in multiple testcases).
	 *
	 * @param collapse
	 *        Filter item to be collapsed
	 * @param filterSuboption
	 *        the filter sub option
	 * @return true, if successful
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyTaskActionHeaderFilterMenu(String collapse, String filterSuboption)
			throws IOException, InterruptedException
	{
		findClickableElement(By.xpath(".//*[@id='accordion']//a[normalize-space(.)='" + collapse.trim() + "']"))
				.click();
		return isDisplayed(By.xpath(".//label[normalize-space(.)='" + filterSuboption.trim() + "']"));
	}

	@Override
	public void selectFilterOption(String option, String filterSuboption) throws IOException, InterruptedException
	{

		if (!findVisibleElement(filterExpanded).isDisplayed())
		{
			clickHeaderFilterButton();
		}
		findClickableElement(By.xpath(".//*[@id='accordion']//a[normalize-space(.)='" + option.trim() + "']")).click();
	}

	/**
	 * Ref : TC1132, TC00992(Multiple Testcases).
	 *
	 * @param filterSuboption
	 *        the filter suboption
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public void clickTaskActionHeaderFilterMenu(String filterSuboption) throws IOException
	{
		findClickableElement(By.xpath(".//label[normalize-space(.)='" + filterSuboption.trim() + "']")).click();
	}

	/**
	 * Ref : TC1132 (Used in multiple test cases).
	 *
	 * @param filterByMenu
	 *        the filter by menu
	 * @param tokensString
	 *        the tokens string
	 * @return true, if successful
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean verifyFilterTokens(String filterByMenu, String tokensString) throws IOException
	{
		findPresentElement(cardView, 20);
		if (filterByMenu.equalsIgnoreCase(TaskLabels.TASKS_ADDTASK_PRIORITY))
		{
			String newToken = tokensString.concat(" Priority");
			return isDisplayed(By.xpath(".//span[normalize-space(.)='" + newToken.trim() + "'][@class='token-label']"));
		}
		else
		{
			return isDisplayed(
					By.xpath(".//span[normalize-space(.)='" + tokensString.trim() + "'][@class='token-label']"));
		}
	}

	/**
	 * Ref : TC1132, TC1144.
	 *
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 */
	@Override
	public void clickOnClearFilters() throws IOException, InterruptedException
	{

		if (!isDisplayed(filterDropDown))
		{
			clickHeaderFilterButton();
		}
		findVisibleElement(clearFilters).click();
		findPresentElement(cardView, Speed.slow);
	}

	/**
	 * Ref: TC1132.
	 *
	 * @param tasksTitle
	 *        the tasks title
	 * @return true, if successful
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean verifyMultipleTasks(List<String> tasksTitle) throws IOException
	{

		for (int i = 0; i < tasksTitle.size(); i++)
		{
			if (findVisibleElement(
					By.xpath(".//*[starts-with(@id,'taskRowDivID')]//div[@class='TruncateLink' and normalize-space(.)='"
							+ tasksTitle.get(i) + "']")).getText().equalsIgnoreCase(tasksTitle.get(i)))
			{
				findVisibleElement(By.xpath(
						".//*[starts-with(@id,'taskRowDivID')]//div[@class='TruncateLink' and normalize-space(.)='"
								+ tasksTitle.get(i) + "']"));
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	public void addTaskFromColumn(String colName, TaskAddDataPage addDataPage)
	{
		try
		{
			findVisibleElement(taskHomeLeftPane, Speed.slow);
			int totalElements = driver.findElements(By.xpath(CARDVIEWWRAPPER)).size();
			for (int i = 1; i <= totalElements; i++)
			{
				String columnheader = findVisibleElement(
						By.xpath(CARDVIEWWRAPPER + "[" + i + "]/div/div[contains(@class,'cardListTitle')]")).getText();
				if (columnheader.equalsIgnoreCase(colName))
				{
					findClickableElement(
							By.xpath(CARDVIEWWRAPPER + "[" + i + "]//button[normalize-space(.)='Add task']")).click();
					findPresentElement(addTaskModal);
					addTask(addDataPage);
					clickOnAddTaskButtonInModel();
					findPresentElement(cardView);
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyAddedListFromAddTaskModal(String list) throws IOException
	{
		findClickableElement(By.xpath(ADDTASK));
		return isDisplayed(By.xpath(ADD_TASK_MODEL + "/option[text()='" + list.trim() + "']"));
	}

	@Override
	public boolean verifyTaskListVisibilityOnEditTaskPanel(String tasktitle, String taskListName)
			throws IOException, InterruptedException
	{
		selectTask(tasktitle);
		try
		{
			WebElement inlineTaskListNameElement = findClickableElement(inlineTaskListName);
			inlineTaskListNameElement.click();

			WebElement inlineTaskListEditElement = findClickableElement(inlineTaskListEdit);
			inlineTaskListEditElement.click();

			return isDisplayed(
					By.xpath(".//*[@id='inlineEditTaskListID']/option[text()='" + taskListName.trim() + "']"));
		}
		catch (AssertionError ae)
		{
			return false;
		}
	}

	@Override
	public boolean verifyAddAnotherTaskLink() throws IOException
	{
		findVisibleElement(addAnotherTaskCheckBox, Speed.slow);
		return isDisplayed(addAnotherTaskCheckBox);
	}

	@Override
	public boolean verifyEmptyFieldInAddAnother() throws IOException
	{
		WebElement taskTitleTextBox = findVisibleElement(addTaskAddTitle);
		boolean defaultValuesVerification = verifyDefaultValueInAddTaskModel() && "".equals(taskTitleTextBox.getText());

		WebElement taskDescriptionTextBox = findVisibleElement(addTaskAddDescription);
		WebElement taskDueDate = findVisibleElement(addTaskModalEnterDueDate);
		boolean descriptionDateVerification = "".equals(taskDescriptionTextBox.getText())
				&& "".equals(taskDueDate.getText());

		WebElement taskAssignee = findVisibleElement(addTaskAddAssignee);
		WebElement taskTags = findVisibleElement(addTaskAddTags);
		boolean assigneeTagVerification = "".equals(taskAssignee.getText()) && "".equals(taskTags.getText());

		return defaultValuesVerification && descriptionDateVerification && assigneeTagVerification;
	}

	@Override
	public boolean verifyDefaultValueInAddTaskModel() throws IOException
	{
		if (!verifyDefaultValueInSelect(addTaskAddPriority, TaskLabels.TASKS_PRIORITY_NORMAL)
				&& !verifyDefaultValueInSelect(addTaskAddList, TaskLabels.TASKS_STATUS_NONE)
				&& !verifyDefaultValueInSelect(addTaskAddStatus, TaskLabels.TASKS_STATUS_NOTSTARTED))
		{
			return false;
		}
		return true;
	}

	public boolean verifyDefaultValueInSelect(By xpathofDropDown, String defaultValue)
	{
		findClickableElement(xpathofDropDown);
		String selectedOption = new Select(findVisibleElement(xpathofDropDown, Speed.slow)).getFirstSelectedOption()
				.getText();
		if (!selectedOption.equalsIgnoreCase(defaultValue))
		{
			return false;
		}
		return true;
	}

	/**
	 * Select list.
	 *
	 * @param list
	 *        the list
	 */

	@Override
	public boolean verifyTaskListOption(String optionValue)
	{
		WebElement taskList = findVisibleElement(addTaskAddList);
		Select listSelect = new Select(taskList);
		List<WebElement> options = listSelect.getOptions();
		boolean result = false;
		for (WebElement option : options)
		{
			if (option.getText().equalsIgnoreCase(optionValue))
			{
				result = true;
				return result;
			}
		}

		return result;
	}

	@Override
	public boolean verifyAttachementInAddTask(String fileName)
	{
		findVisibleElement(attachmentTabInAddTaskModal).click();
		String commonXpath = "(.//*[@id='dndTaskAddEditAttachmentsID']//div[normalize-space(.)='" + fileName.trim()
				+ "'])[1]";
		By uploadedFile = By.xpath(commonXpath);
		By fileSize = By.xpath(commonXpath + "/following-sibling::*[@class='greyMeta']");
		By fileIcon = By.xpath(commonXpath + "/ancestor::*[1]/preceding-sibling::*[contains(@class,'imgIcon')]");
		if (isDisplayed(uploadedFile, Speed.slow) && (isDisplayed(fileSize) && isDisplayed(fileIcon)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void closeTask()
	{
		findVisibleElement(moreActionMenuLinkInModel, Speed.slow);
		WebElement closeButton;
		if (isDisplayed(listViewCloseTaskButton, Speed.slow))
		{
			closeButton = findVisibleElement(listViewCloseTaskButton);
			closeButton.click();
		}
		else
		{
			closeButton = findVisibleElement(cardViewCloseTaskButton);
			closeButton.click();
		}
	}

	@Override
	public void clickOnDeleteTask(String taskTitle) throws InterruptedException
	{
		selectTask(taskTitle);

		WebElement moreOptions = findVisibleElement(columnViewTaskViewHeaderMoreAction, Speed.slow);
		moreOptions.click();

		WebElement deleteTaskLink = findVisibleElement(deleteTask, Speed.slow);
		deleteTaskLink.click();

		findPresentElement(deleteTaskModalTitle);
	}

	@Override
	public boolean verifyDeleteMessage(String message)
	{
		String deleteMessage = findVisibleElement(deleteTaskModalMessage).getText();

		return deleteMessage.equalsIgnoreCase(message);
	}

	@Override
	public void cancelDeleteOperation()
	{
		WebElement cancelDeleteButton = findVisibleElement(deleteTaskModalCancelButton);

		cancelDeleteButton.click();
	}

	@Override
	public void confirmDeleteOperation()
	{
		WebElement deleteTask = findVisibleElement(deleteTaskModalDeleteButton);

		deleteTask.click();
	}

	@Override
	public boolean noTaskIspresent()
	{
		findVisibleElement(noTaskPresent, Speed.slow);
		return isDisplayed(noTasksLabel);
	}

	@Override
	public void editTaskTitle(String title)
	{
		findVisibleElement(taskTitle, Speed.slow).click();
		findVisibleElement(editTaskTitle).clear();
		findVisibleElement(editTaskTitle).sendKeys(title);
		findVisibleElement(editTaskTitleSubmit).click();
	}

	@Override
	public void editTaskList(String listOption)
	{
		findVisibleElement(taskListname, Speed.slow).click();
		findVisibleElement(inlineEditTaskListID);
		Select select_list = new Select(findVisibleElement(inlineEditTaskListID));
		select_list.selectByVisibleText(listOption);
		findVisibleElement(acceptIcon).click();
	}

	@Override
	public void editTaskStatus(String desiredStatus)
	{
		findVisibleElement(taskStatus, Speed.slow).click();
		findVisibleElement(taskInlineStatus, Speed.slow);
		Select select_list = new Select(findVisibleElement(taskInlineStatus, Speed.slow));
		select_list.selectByVisibleText(desiredStatus);
		findVisibleElement(inlineStatusSubmitButton).click();
	}

	@Override
	public void editTaskDueDate(String desiredDueDate)
	{
		findVisibleElement(taskDueDate, Speed.slow).click();
		findVisibleElement(inlineDueDateDatePicker, Speed.slow);
		findVisibleElement(inlineDueDateDatePicker).clear();
		findVisibleElement(inlineDueDateDatePicker).sendKeys(desiredDueDate);
		findVisibleElement(inlineDueDatePickerSubmitButton).click();
	}

	@Override
	public void editTaskAssignee(String assignee)
	{
		findVisibleElement(taskAssignee, Speed.slow).click();

		WebElement taskAssigneeTextBox = findVisibleElement(editTaskAssigneeTextBox);
		if (taskAssigneeTextBox.isDisplayed())
		{
			taskAssigneeTextBox.sendKeys(assignee);
			selectOptionFromAutoSuggest(assignee);
		}
		findVisibleElement(inlineEditSubmitAssignee).click();
	}

	@Override
	public void editTaskPriority(String desiredPriority)
	{
		findVisibleElement(taskPriority, Speed.slow).click();

		findVisibleElement(editTaskPriority);
		Select select_list = new Select(findVisibleElement(editTaskPriority, Speed.slow));
		select_list.selectByVisibleText(desiredPriority);
		findVisibleElement(inlineEditSubmitPriority).click();
	}

	@Override
	public void editTaskDescription(String desiredDescription)
	{
		findVisibleElement(taskContents, Speed.slow).click();

		WebElement contentInputBox = findVisibleElement(taskContentTextBox);
		// scrollToElement(taskContentTextBox);
		contentInputBox.clear();
		contentInputBox.sendKeys(desiredDescription);
		findVisibleElement(inlineEditContentSubmit).click();
	}

	@Override
	public void editTaskTag(String desiredTag)
	{
		findVisibleElement(editTaskTag, Speed.slow);
		findVisibleElement(editTaskTag, Speed.slow).click();
		WebElement tagInputbox = findVisibleElement(editTaskTagInputBox);
		scrollToElement(tagInputbox);
		tagInputbox.sendKeys(Keys.BACK_SPACE);
		tagInputbox.sendKeys(Keys.BACK_SPACE);
		tagInputbox.sendKeys(desiredTag);
		tagInputbox.sendKeys(Keys.TAB);
		findVisibleElement(tagsTitle).click();
		findVisibleElement(inlineEditTagSubmit, Speed.slow).click();
	}

	@Override
	public void editReminder(String reminder)
	{
		findVisibleElement(addReminderLink, Speed.slow).click();
		WebElement reminderInputbox = findVisibleElement(inlineTaskReminderInputId);
		reminderInputbox.sendKeys(Keys.BACK_SPACE);
		reminderInputbox.sendKeys(reminder);
		findVisibleElement(inlineEditReminderSubmitButton).click();
	}

	@Override
	public void editAttachment(String attachment)
	{
		findVisibleElement(addAttachmentInTask, Speed.slow).click();
		addAttachment("upload", "", attachment);
	}

	@Override
	public boolean verifyTaskTitle(String title)
	{
		return verifyText(taskTitle, title);
	}

	@Override
	public boolean verifyTaskList(String listOption)
	{
		return verifyText(taskListname, listOption);
	}

	@Override
	public boolean verifyTaskStatus(String desiredStatus)
	{
		return verifyText(taskStatus, desiredStatus);
	}

	@Override
	public boolean verifyTaskDueDate(String desiredDueDate)
	{
		return verifyText(taskDueDate, desiredDueDate);
	}

	@Override
	public boolean verifyTaskAssignee(String assignee)
	{
		if (assignee.contains("@"))
		{
			assignee = assignee.split("@")[0];
		}
		By assigneeLink = By.xpath("//*[@id='task_inline_assigne_and_group_listID']/a[normalize-space(text())='"
				+ getUserData(assignee).trim() + "']");
		return verifyText(assigneeLink, getUserData(assignee));
	}

	@Override
	public boolean verifyTaskPriority(String desiredPriority)
	{
		return verifyText(taskPriority, desiredPriority);
	}

	@Override
	public boolean verifyTaskDescription(String desiredDescription)
	{
		return verifyText(taskDescription, desiredDescription);
	}

	@Override
	public boolean verifyTag(String tagName)
	{
		return (findVisibleElement(
				By.xpath("//*[@id='taskTagID']//a[normalize-space(.)='" + tagName.toLowerCase() + "']"), Speed.slow)
						.getText().trim().equals(tagName.trim().toLowerCase()));
	}

	@Override
	public boolean verifyReminder(String reminder)
	{
		By reminderXpath = By.xpath("//*[@id='editRemiderList']//span[contains(text(),'" + reminder.trim() + "')]");
		String setReminder = findVisibleElement(reminderXpath).getText();
		return setReminder.contains(reminder);
	}

	/*
	 * (non-Javadoc) this is method is used for all the views
	 * @see com.highq.pageobjects.base.TasksPage#verifyTaskData(java.util.Map)
	 */
	@Override
	public boolean verifyTaskData(Map<String, String> list) throws IOException, InterruptedException
	{
		boolean result = false;
		for (Entry<String, String> entry : list.entrySet())
		{
			String key = entry.getKey().toString();
			String value = entry.getValue();
			if (key.contains("_"))
			{
				key = key.replaceAll("_", " ").trim();
			}
			String lowerCase = key.toLowerCase();
			if (TaskLabels.TASKS_ADDTASK_TITLE.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskTitle(value.trim());
			}
			else if (TaskLabels.TASKS_ADDTASK_LIST.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskList(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_STATUS.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskStatus(value);
			}
			else if (TaskLabels.TASKS_STARTDATE.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskStartDate(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DUEDATE.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskDueDate(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_ASSIGNEE.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskAssignee(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_PRIORITY.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskPriority(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_DESCRIPTION.toLowerCase().equals(lowerCase))
			{
				result = verifyTaskDescription(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_TAGS.toLowerCase().equals(lowerCase))
			{
				result = verifyTag(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_REMINDER.toLowerCase().equals(lowerCase))
			{
				result = verifyReminder(value);
			}
			else if (TaskLabels.TASKS_ADDTASK_ATTACHMENT.toLowerCase().equals(lowerCase))
			{
				result = verifyAttachmentInViewTask(value);
			}
			else
			{
			}
			if (!result)
			{
				break;
			}
		}
		return result;

	}

	@Override
	public boolean addComment(String comment) throws InterruptedException
	{
		findClickableElement(columnViewTaskViewHeaderComments, Speed.slow).click();

		if (isDisplayed(ckLoadedForCommentBox, Speed.slow))
		{
			findVisibleElement(ckLoadedForCommentBox).click();
		}

		findClickableElement(disabledCommentBox).click();
		WebElement commentBox = findVisibleElement(enabledCommentBox);
		commentBox.clear();
		commentBox.sendKeys(comment.trim());

		WebElement postComment = findVisibleElement(enabledPostCommentButton);
		postComment.click();

		By commentXpath = By.xpath(
				"(.//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@id,'commentMeta')]//following::*[text()='"
						+ comment.trim() + "'])[1]");
		findVisibleElement(commentXpath);

		return isDisplayed(commentXpath);
	}

	@Override
	public void scrollToComment(String user, String comment) throws InterruptedException
	{
		By commentXpath = By.xpath(
				"(.//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@id,'commentMeta')]//following::*[text()='"
						+ comment.trim() + "'])[1]");
		if (!isDisplayed(commentXpath) && isDisplayed(loadMoreCommentsLink))
		{
			findVisibleElement(loadMoreCommentsLink).click();
			// scrollToElement(commentXpath);
		}
	}

	@Override
	public boolean verifyVisiblityOfCommentLikeLink(String user, String comment)
	{
		user = getUserData(user);

		By likeXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_LIKE + "'][1])[1]");
		return isDisplayed(likeXpath);
	}

	@Override
	public boolean verifyVisiblityOfCommentUnLikeLink(String user, String comment)
	{
		user = getUserData(user);

		By likeXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_UNLIKE + "'][1])[1]");
		return isDisplayed(likeXpath);
	}

	/**
	 * Get comment like count
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return comment count
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public int getCommentLikeCount(String user, String comment)
	{
		user = getUserData(user);

		By commentCountXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='Unlike']//following::*[contains(@onclick,'ACTIVITY_COMMENT_ADDED')][1])[1]");

		if (!isDisplayed(commentCountXpath))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(findVisibleElement(commentCountXpath).getText().trim());
		}
	}

	/**
	 * Verify comment like count gets incremented by 1 or not
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @param currentCount
	 *        current count of likes on comment
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyCommentLinkCountIncremented(String user, String comment, int currentCount)
	{
		int newVersionNumber = getCommentLikeCount(user, comment);
		if ((currentCount + 1) == newVersionNumber)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Verify reply link is visible
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyVisiblityOfCommentReplyLink(String user, String comment)
	{
		user = getUserData(user);

		By replyXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_REPLY + "'][1])[1]");
		return isDisplayed(replyXpath);
	}

	/**
	 * Click on comment reply link
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @author badal.gandhi
	 */
	@Override
	public void clickOnCommentReplyLink(String user, String comment)
	{
		user = getUserData(user);

		By replyXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_REPLY + "'][1])[1]");
		if (isDisplayed(replyXpath))
		{
			findVisibleElement(replyXpath).click();
		}
	}

	/**
	 * Verify Edit link is visible
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyVisiblityOfCommentEditLink(String user, String comment)
	{
		user = getUserData(user);

		By editXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_EDIT + "'][1])[1]");
		return isDisplayed(editXpath);
	}

	/**
	 * Click on edit link
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @author badal.gandhi
	 */
	@Override
	public void clickCommentEditLink(String user, String comment)
	{
		user = getUserData(user);

		By editXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_EDIT + "'][1])[1]");
		findVisibleElement(editXpath).click();
	}

	/**
	 * Verify Delete link is visible
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyVisiblityOfCommentDeleteLink(String user, String comment)
	{
		user = getUserData(user);
		By deleteXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_DELETE + "'][1])[1]");
		return isDisplayed(deleteXpath, Speed.slow);
	}

	/**
	 * Click on Delete link
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public void clickCommentDeleteLink(String user, String comment)
	{
		user = getUserData(user);

		By deleteXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + TaskLabels.TASKS_DELETE + "'][1])[1]");
		findVisibleElement(deleteXpath).click();
	}

	/**
	 * Verify delete comment message
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyDeleteCommentMessage()
	{
		WebElement deleteMessage = findVisibleElement(deleteCommentMessage, Speed.slow);

		return deleteMessage.isDisplayed();
	}

	/**
	 * Cancel delete message box
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void cancelDeleteCommentMessageBox()
	{
		findPresentElement(messageModelOpened);
		WebElement deleteMessage = findVisibleElement(cancelMessageModel, Speed.slow);
		deleteMessage.click();
		findPresentElement(messageModelClosed);
	}

	/**
	 * Confirm delete message box
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void confirmDeleteCommentMessageBox()
	{
		findPresentElement(messageModelOpened);
		WebElement deleteMessage = findVisibleElement(confirmMessageModel, Speed.slow);
		deleteMessage.click();
		findPresentElement(messageModelClosed);
	}

	/**
	 * Verify Comment is visible or not
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyVisibilityOfComment(String user, String comment)
	{
		user = getUserData(user);

		By commentXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim() + "'][1])[1]");
		return isDisplayed(commentXpath);
	}

	/**
	 * Delete comment
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public boolean deleteComment(String user, String comment) throws InterruptedException
	{
		scrollToComment(user, comment);
		clickCommentDeleteLink(user, comment);
		confirmDeleteCommentMessageBox();
		return !verifyVisibilityOfComment(user, comment);
	}

	/**
	 * Edit comment
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @param commentToEdit
	 *        new comment
	 * @return true if comment successfully edited
	 * @author badal.gandhi
	 */
	@Override
	public boolean editComment(String user, String comment, String commentToEdit)
	{
		clickCommentEditLink(user, comment);
		WebElement editBox = findVisibleElement(editCommentTextBox);
		editBox.clear();
		editBox.sendKeys(commentToEdit);
		findVisibleElement(enabledPostCommentButton).click();

		return verifyVisibilityOfComment(user, commentToEdit);

	}

	/**
	 * reply on comment
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @param commentToReply
	 *        new comment
	 * @return true if comment successfully edited
	 * @author badal.gandhi
	 */
	@Override
	public boolean replyComment(String user, String comment, String commentToReply) throws InterruptedException
	{
		boolean visibilityOfUserDisplayed = verifyUserInReplyCommentBox(user, comment);

		WebElement replyBox = findVisibleElement(replyCommentTextBox);
		replyBox.click();
		replyBox.sendKeys(commentToReply);
		findVisibleElement(enabledPostCommentButton).click();
		boolean repliedCommentResult = verifyRepliedComment(getUserData(user), commentToReply);

		return visibilityOfUserDisplayed && repliedCommentResult;

	}

	/**
	 * Verify user in reply comment
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return true if successful
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyUserInReplyCommentBox(String user, String comment) throws InterruptedException
	{
		clickOnCommentReplyLink(user, comment);
		// scrollToElement(replyCommentTextBox);

		By userXpath = By
				.xpath("(//*[@isckloaded='TRUE'])[last()]//*[contains(text(),'" + getUserData(user.trim()) + "')]");
		findVisibleElement(userXpath, Speed.slow, 2);

		return isDisplayed(userXpath, Speed.slow);
	}

	/**
	 * Verify replied comment
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyRepliedComment(String user, String comment)
	{
		By repliedCommentXpath = By.xpath("(//*[@class='CKContextMention' and contains(text(),'" + user.trim()
				+ "')]//ancestor::*[contains(text(),'" + comment.trim() + "')])[last()]");

		return isDisplayed(repliedCommentXpath, Speed.slow);
	}

	/**
	 * Verify commentBox is Enabled
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyEnabledCommentBox()
	{
		if (isDisplayed(ckLoadedForCommentBox, Speed.slow))
		{
			findVisibleElement(ckLoadedForCommentBox).click();
		}

		findClickableElement(disabledCommentBox).click();
		WebElement commentBox = findVisibleElement(enabledCommentBox);
		return commentBox.isDisplayed();
	}

	/**
	 * Verify comment cancel button is Enabled
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyCommentCancelButtonIsPresent() throws InterruptedException
	{
		// scrollToElement(commentCancelButton);
		return isDisplayed(commentCancelButton);
	}

	/**
	 * Verify comment cancel button is Enabled
	 *
	 * @return true if successful
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyCommentPostButtonIsPresent() throws InterruptedException
	{
		// scrollToElement(postCommentButton);
		return isDisplayed(postCommentButton);
	}

	/**
	 * Get comment count
	 *
	 * @return comment count
	 * @author badal.gandhi
	 * @throws InterruptedException
	 */
	@Override
	public int getCommentCount()
	{
		return Integer.parseInt(findVisibleElement(commentCount).getText().trim());
	}

	/**
	 * Verify comment count gets incremented by 1 or not
	 *
	 * @return true if successful
	 * @param currentCount
	 *        current comment count
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyCommentCountIncremented(int currentCount)
	{
		int newCommentCount = getCommentCount();
		if ((currentCount + 1) == newCommentCount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Verify comment count gets decreased by 1 or not
	 *
	 * @return true if successful
	 * @param currentCount
	 *        current comment count
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyCommentCountDecreased(int currentCount)
	{
		int newCommentCount = getCommentCount();
		if ((currentCount - 1) == newCommentCount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean completeTask(String taskName) throws InterruptedException
	{
		WebElement searchBox = findPresentElement(searchTextbox, Speed.slow);
		searchBox.clear();
		searchBox.sendKeys(taskName);

		findVisibleElement(clearSearchBoxButton, Speed.slow);

		By taskLinkPath = By.xpath("//*[normalize-space(text())='" + taskName.trim() + "'][1]");

		if (isDisplayed(taskLinkPath, Speed.slow))
		{
			By taskCompleteCheckBox = By.xpath(
					"(//a[normalize-space(text())='" + taskName.trim() + "']//preceding::*[@type='checkbox'][1])[1]");
			setSelection(taskCompleteCheckBox, true);

			By taskXpath = By.xpath("//a[normalize-space(text())='" + taskName.trim() + "']");

			WebElement checkedTask = findVisibleElement(taskXpath);
			String checkedXpathAttribute = checkedTask.getCssValue("text-decoration").trim();

			System.out.println(checkedXpathAttribute);

			return checkedXpathAttribute.contains("line-through");

		}
		return false;
	}

	@Override
	public void clearSearch()
	{
		if (isDisplayed(clearSearchBoxButton))
		{
			findVisibleElement(clearSearchBoxButton).click();
		}
	}

	/**
	 * Verifies if favourite star is selected or not.
	 *
	 * @return true if successful.
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFavouriteTaskIconIsSelected()
	{
		return isDisplayed(favouriteStarSelected, Speed.slow);
	}

	/**
	 * Click on add to favourite Star Icon
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void addTaskToFavourites()
	{
		if (!verifyFavouriteTaskIconIsSelected())
		{
			findVisibleElement(favouriteButton).click();
		}
		else
		{
			System.out.println("Favourite Star is already selected.");
		}
	}

	/**
	 * Open favourites tab
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickFavouritesTabInTask()
	{
		findVisibleElement(favouritesTab).click();
	}

	/**
	 * Search a task
	 *
	 * @param taskName
	 *        name of the task to be searched
	 * @author dheeraj.rajput
	 */
	@Override
	public void searchTask(String taskName)
	{
		WebElement searchBox = findPresentElement(searchTextbox);
		searchBox.clear();
		searchBox.sendKeys(taskName.trim());
		findPresentElement(taskRowList, Speed.slow);
	}

	/**
	 * Click on remove from Star Icon
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void removeTaskFromFavourites()
	{
		if (verifyFavouriteTaskIconIsSelected())
		{
			findVisibleElement(favouriteStarSelected).click();
		}
		else
		{
			System.out.println("No favourites star available.");
		}
	}

	/**
	 * Click on All Tasks tab
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickAllTasksTab()
	{
		findVisibleElement(allTaskTab).click();
	}

	/**
	 * Verify task title starts with required text
	 *
	 * @param startsWithText
	 *        starts with text to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyTaskStartsWith(String startsWithText)
	{
		boolean found = false;
		int size = driver.findElements(By.xpath(taskTitleColumns)).size();
		for (int i = 2; i <= size; i++)
		{
			By column = By.xpath(
					"(" + taskTitleColumns + ")[" + i + "]//*[starts-with(text(),'" + startsWithText.trim() + "')]");
			if (isDisplayed(column))
			{
				findVisibleElement(column);
				found = true;
			}
		}
		return found;
	}

	/**
	 * Search and open task and click on more actions of task
	 *
	 * @param taskTitle
	 *        Task name
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @created 18 January 2018
	 */
	@Override
	public void clickMoreActionOfTask() throws InterruptedException
	{
		WebElement ele = findVisibleElement(moreActionMenuLinkInModel);
		ele.click();

	}

	/**
	 * Click on share task option
	 *
	 * @param taskTitle
	 *        Task name
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @created 18 January 2018
	 */
	@Override
	public void clickShareTask() throws InterruptedException
	{
		clickMoreActionOfTask();
		WebElement shareTaskLink = findVisibleElement(shareTask, Speed.slow);
		shareTaskLink.click();

		findPresentElement(deleteTaskModalTitle);
	}

	@Override
	public boolean verifyActiveTask(String taskTitle)
	{
		if (isDisplayed(accessRestricted))
		{
			System.out.println("Access is restricted.");
			return false;
		}
		else
		{
			return verifyTaskTitle(taskTitle);
		}
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @creation date 30/01/2018
	 */
	public void sendTextInQuickSearchTextBox(String text)
	{
		WebElement searchBox = findPresentElement(searchTextbox, Speed.slow);
		searchBox.clear();
		searchBox.sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 * @param task
	 * @return
	 * @creation date 30/01/2018
	 */
	public boolean verifyTaskInTaskList(String task)
	{
		findPresentElement(taskRowList, Speed.slow);
		// return(findVisibleElement(By.xpath("//div[@ class='colTitle']//a[text()='" +
		// task.trim() +"']"),Speed.slow).getText().trim().equals(task.trim()));
		return (isDisplayed(By.xpath("//div[@ class='colTitle']//a[text()='" + task.trim() + "']")));
	}

	/**
	 * @author jyoti.raj Created : 5th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyCardViewRenderInTaskModule()
	{
		return isDisplayed(cardView);
	}

	/**
	 * Verify attachment tab visibility in add task modal
	 *
	 * @return true if attachment tab found
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyAttachmentTabVisibilityInAddTask()
	{
		findVisibleElement(taskModal);
		return isDisplayed(attachmentTabInAddTaskModal, Speed.slow);
	}

	/******************************************************************************************************************************************/
	/*** TimeLine View Methods ***/
	/******************************************************************************************************************************************/

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyTimelineViewRenderInTaskModule()
	{
		return isDisplayed(taskTimelineView);
	}

	/*
	 * (non-Javadoc)
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @see
	 * com.highq.pageobjects.base.TasksPage#verifyTaskDetailsOrderInAddTaskModal(
	 * java.util.List)
	 */
	@Override
	public boolean verifyTaskDetailsOrderInAddTaskModal(List<String> list)
	{

		int index = list.size() + 2;
		int size = list.size();
		for (int i = 2, j = 0; i < index && j < size; i++, j++)
		{

			WebElement element = findVisibleElement(By.xpath(".//*[@id='addTaskDetailsTab']/div[" + i + "]/label"));
			String label = element.getText();
			if (!label.contains(list.get(j).replace("_", " ")))
			{
				return false;
			}

		}
		return true;
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyDisableAddButtonVisibilityInAddTaskModal()
	{
		return isDisplayed(disbaleAddButtonInAddTaskModal);
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyCancleButtonVisibilityInAddTaskModal()
	{
		return isDisplayed(cancelButtonInAddTaskModal);
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyXButtonVisibilityInAddTaskModal()
	{
		return isDisplayed(closeButtonInAddTaskModal);
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 */
	@Override
	public void clickOnCloseButtonInAddTaskModal()
	{
		findVisibleElement(closeButtonInAddTaskModal).click();
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyAddTaskModalVisibility()
	{
		return isDisplayed(visibleAddTaskModal, Speed.slow);
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyCKEditorInDiscription()
	{
		WebElement taskDescription = findVisibleElement(addTaskAddDescription);
		taskDescription.sendKeys(" ");
		return isDisplayed(ckEditorOfDiscription);
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @param priorityName
	 * @return
	 */
	@Override
	public boolean verifyDefaultValueOfPriorityInAddTaskModal(String priorityName)
	{
		WebElement element = findVisibleElement(defaultPriorityInAddTaskModal);
		String priority = element.getText().trim();
		return priority.equals(priorityName.trim());
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @param listName
	 * @return
	 */
	@Override
	public boolean verifyDefaultValueOfListInAddTaskModal(String listName)
	{
		WebElement element = findVisibleElement(defaultListInAddTaskModal);
		String list = element.getText().trim();
		return list.equals(listName.trim());
	}

	/**
	 * @author jyoti.raj Created : 9th April 2018 Updated :
	 * @param statusName
	 * @return
	 */
	@Override
	public boolean verifyDefaultValueOfStatusInAddTaskModal(String statusName)
	{
		WebElement element = findVisibleElement(defaultStatusInAddTaskModal);
		String status = element.getText().trim();
		return status.equals(statusName.trim());
	}

	/**
	 * @author jyoti.raj Created : 10th April 2018 Updated :
	 * @param startDate
	 */
	@Override
	public void setTaskStartDate(String startDate)
	{
		WebElement taskStartdate = findVisibleElement(addTaskModalEnterStartDate);
		taskStartdate.clear();
		taskStartdate.sendKeys(startDate);
		taskStartdate.sendKeys(Keys.TAB);
	}

	/**
	 * @author jyoti.raj Created : 10th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyInvalidStartDateMessage(String message)
	{

		WebElement element = findVisibleElement(invalidStartDateMessage);
		String msg = element.getText().trim();
		return msg.equals(message.trim());

	}

	/**
	 * @author jyoti.raj Created : 10th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyInvalidDueDateMessage(String message)
	{
		WebElement element = findVisibleElement(invalidDueDateMessage);
		String msg = element.getText().trim();
		return msg.equals(message.trim());
	}

	/**
	 * @author jyoti.raj Created : 10th April 2018 Updated :
	 * @param desiredStartDate
	 * @return
	 */
	@Override
	public boolean verifyTaskStartDate(String desiredStartDate)
	{
		return verifyText(taskStartDateInViewTaskModal, desiredStartDate);
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewFromViewMenu()
	 */
	@Override
	public void clickTimelineViewFromViewMenu() throws IOException, InterruptedException
	{
		findClickableElement(viewSwitchTimelineView, Speed.slow).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @param defaultTimelineView
	 * @return
	 */
	@Override
	public boolean verifyDefaultViewInTimelineView(String defaultView)
	{
		String view = findVisibleElement(defaultViewNameInTimelineView).getText();
		return view.trim().equals(defaultView.trim());
	}

	/**
	 * @author jyoti.raj Created : 11th April 2018 Updated :
	 * @param taskName
	 * @return
	 */
	@Override
	public boolean verifyTaskVisibilityInTimelineView(String taskName)
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		return isDisplayed(By.xpath(".//*[@class='TruncateTxt' and text()='" + taskName.trim() + "']"));
	}

	/**
	 * @author jyoti.raj Created : 11th April 2018 Updated :
	 * @param taskName
	 */
	@Override
	public void clickOnTaskInTimelineView(String taskName)
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		WebElement element = findVisibleElement(
				By.xpath(".//*[@class='TruncateTxt' and text()='" + taskName.trim() + "']"));
		element.click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewPreviousButton()
	 */
	@Override
	public void clickTimelineViewPreviousButton()
	{
		findClickableElement(taskTimelineViewPrevoiusButton).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewNextButton()
	 */
	@Override
	public void clickTimelineViewNextButton()
	{
		findClickableElement(taskTimelineViewNextButton).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewNextButton()
	 */
	@Override
	public void clickTimelineViewTwoWeekViewButton()
	{
		findClickableElement(taskTimelineViewBtnTwoWeekView).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewNextButton()
	 */
	@Override
	public void clickTimelineViewMonthViewButton()
	{
		findClickableElement(taskTimelineViewBtnMonthkView).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewNextButton()
	 */
	@Override
	public void clickTimelineViewWeekViewButton()
	{
		findClickableElement(taskTimelineViewBtnWeekView).click();
	}

	/**
	 * @author jyoti.raj Created : 12th April 2018 Updated :
	 * @param date
	 */
	@Override
	public void addTaskInTimelineViewWithDoubleClick(String date)
	{
		Actions action = new Actions(driver);
		findVisibleElement(taskTimelineScheduler);
		List<WebElement> elements = driver.findElements(timelineDateHeader);
		int totalElements = elements.size();
		for (int i = 0; i < totalElements; i++)
		{
			String list = elements.get(i).getAttribute("class");
			String[] className = list.split("DATEAUTO");
			String dateFromClass = className[1].replace("_", " ").trim();
			if (dateFromClass.contains("today"))
			{
				dateFromClass = className[2].replace("_", " ");
			}
			System.out.println("list ====" + list);
			System.out.println(date.trim() + "=======" + dateFromClass.trim());
			if (date.trim().equals(dateFromClass.trim()))
			{
				WebElement element = findVisibleElement(By.xpath(
						".//*[@id='taskTimelineScheduler']//td[contains(@class,'dhx_matrix_cell')][" + (i + 1) + "]"),
						Speed.slow);
				action.moveToElement(element).doubleClick().build().perform();
				break;
			}
		}

	}

	/**
	 * @author jyoti.raj Created : 19th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public WebElement getTaskColumnInTimelineView(String date)
	{
		findVisibleElement(taskTimelineScheduler);
		List<WebElement> elements = driver.findElements(timelineDateHeader);
		int totalElements = elements.size();
		for (int i = 0; i < totalElements; i++)
		{
			String list = elements.get(i).getAttribute("class");
			String[] className = list.split("DATEAUTO");
			String dateFromClass = className[1].replace("_", " ").trim();
			if (dateFromClass.contains("today"))
			{
				dateFromClass = className[2].replace("_", " ");
			}
			System.out.println("list ====" + list);
			System.out.println(date.trim() + "=======" + dateFromClass.trim());
			if (date.trim().equals(dateFromClass.trim()))
			{
				return findVisibleElement(By.xpath(
						".//*[@id='taskTimelineScheduler']//td[contains(@class,'dhx_matrix_cell')][" + (i + 1) + "]"));
			}
		}
		return null;
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickTimelineViewTodayButton()
	 */
	@Override
	public void clickTimelineViewTodayButton()
	{
		findClickableElement(taskTimelineViewBtnToday).click();
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#varifyTaskTimelineViewTodayDate()
	 */
	@Override
	public boolean verifyTaskTimelineViewTodayDate(String todayDate)
	{
		findClickableElement(taskTimelineViewTodayDate).click();

		String date = findVisibleElement(taskTimelineViewTodayDate).getText();

		return todayDate.equals(date);
	}

	/**
	 * @author Bijesh.Sheth Created : 12th April 2018 Updated :
	 * @see
	 */
	@Override
	public boolean verifyTaskDetailsLabelVisibility(String taskDetailsLabel)
	{
		findVisibleElement(addTaskModal, Speed.slow);
		By element = By.xpath(
				"//*[@id='addTaskDetailsTab']//div[not(contains(@class,'hide'))]//label[normalize-space(text())='"
						+ taskDetailsLabel + "']");
		return isDisplayed(element, Speed.slow);
	}

	/**
	 * @author Anil.patidar Created : 13th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickAssignToMeTasksInTask()
	 */
	@Override
	public void clickAssignToMeTabInTasks()
	{
		findClickableElement(taskTimelineViewAssignToMe).click();
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @param statusName
	 * @param taskTitle
	 * @param color
	 * @return
	 */
	@Override
	public boolean verifyTaskColorForTimelineView(String taskTitle, String color)
	{
		String taskcolor = null;
		if (isDisplayed(By.xpath(".//*[@class='TruncateTxt' and text()='" + taskTitle.trim() + "']")))
		{
			taskcolor = gethashValueOfColor(By.xpath(".//*[@class='TruncateTxt' and text()='" + taskTitle.trim()
					+ "']/ancestor::div[contains(@class,'dhx_cal_event_line')]"), "background-color");
		}
		return color.trim().equalsIgnoreCase(taskcolor.trim());
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @return
	 */
	@Override
	public String getStartDateInViewTaskModal()
	{
		WebElement element = findVisibleElement(taskStartDateInViewTaskModal);
		return element.getText().trim();

	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @return
	 */
	@Override
	public String getDueDateInViewTaskModal()
	{
		WebElement element = findVisibleElement(taskDueDate);
		return element.getText().trim();
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 */
	@Override
	public void clickColumnViewFromViewMenu()
	{
		findClickableElement(viewSwitchListView).click();
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 */
	@Override
	public void clickOnCloseButtonInViewTaskInCardOrTimelineView()
	{
		findVisibleElement(columnViewTaskViewPopup);
		findClickableElement(closeButton).click();
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @param columnTitle
	 * @param taskTitle
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean verifyTaskVisibilityInCardView(String columnTitle, String taskTitle) throws IOException
	{
		findVisibleElement(taskListDiv, Speed.slow);
		int i = getColumnNumberForCardView(columnTitle);
		if (i > 0)
		{
			return isDisplayed(By.xpath(CARDVIEWWRAPPER + "[" + i + "]//a[normalize-space(.)='" + taskTitle + "']"));
		}
		return false;

	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @param columnTitle
	 * @return
	 * @throws IOException
	 */
	@Override
	public int getColumnNumberForCardView(String columnTitle) throws IOException
	{
		findVisibleElement(By.xpath(CARDVIEWWRAPPER), Speed.slow);
		int total = driver.findElements(By.xpath(CARDVIEWWRAPPER)).size();
		if (verifyAddColumnButton())
		{

			for (int i = 1; i < total; i++)
			{
				moveToElement(By.xpath(CARDVIEWWRAPPER + "[" + i + "]/div/div[contains(@class,'cardListTitle')]"));
				String columnheader = getText(
						By.xpath(CARDVIEWWRAPPER + "[" + i + "]/div/div[contains(@class,'cardListTitle')]"));
				if (columnheader.equals(columnTitle))
				{
					return i;
				}
			}
		}
		return 0;
	}

	/**
	 * @author jyoti.raj Create : 16th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyAddColumnButton()
	{
		return isDisplayed(addColumnButton);
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : date in MMM/dd/yyyy
	 *         formate To select the date in start date calender
	 */
	@Override
	public void setDateFromDatePicker(String date)
	{
		String actualDay, actualMonth;

		int actualYear;
		String[] date1 = date.split("/");
		actualYear = Integer.parseInt(date1[2].trim());
		actualDay = date1[1].trim();
		actualMonth = date1[0].trim();

		if (!(actualYear == getCurrentYearInCalenderModal()
				&& actualMonth.contains(getCurrentMonthInCalenderModal())))
		{
			clickOnYearMonthPickerInCalenderModal();
			boolean result = true;
			while (result)
			{
				int yearDiff = actualYear - getCurrentYearFromMonthPicker();
				if (yearDiff > 0)
				{
					clickOnNextInMonthPicker();
				}
				else if (yearDiff < 0)
				{
					clickOnPreviousInMonthPicker();
				}
				else
				{
					result = false;
				}
			}
			clickOnMonthInMonthPicker(actualMonth);
		}
		findVisibleElement(By.xpath(
				"//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[not(contains(@class,'day old')) and normalize-space()='"
						+ actualDay.trim() + "']"),
				Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : date in MMM/dd/yyyy
	 *         formate To select the date in start date calender
	 * @return
	 */
	@Override
	public boolean verifyDateFromDatePicker(String date)
	{
		String actualDay, actualMonth;

		int actualYear;
		String[] date1 = date.split("/");
		actualYear = Integer.parseInt(date1[2].trim());
		actualDay = date1[1].trim();
		actualMonth = date1[0].trim();

		if (!(actualYear == getCurrentYearInCalenderModal()
				&& actualMonth.contains(getCurrentMonthInCalenderModal())))
		{
			clickOnYearMonthPickerInCalenderModal();
			boolean result = true;
			while (result)
			{
				int yearDiff = actualYear - getCurrentYearFromMonthPicker();
				if (yearDiff > 0)
				{
					clickOnNextInMonthPicker();
				}
				else if (yearDiff < 0)
				{
					clickOnPreviousInMonthPicker();
				}
				else
				{
					result = false;
				}
			}
			clickOnMonthInMonthPicker(actualMonth);
		}
		return isDisplayed(By.xpath(
				"//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[not(contains(@class,'day old')) and normalize-space()='"
						+ actualDay.trim() + "']"),
				Speed.slow);
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated :
	 * @return To get the current year from the start date calender
	 */
	@Override
	public int getCurrentYearInCalenderModal()
	{
		return (Integer
				.parseInt(findVisibleElement(currentMonthYearInCalender, Speed.slow).getText().split(" ")[1].trim()));
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated :
	 * @return To get the current Month from the end date calender
	 */
	@Override
	public String getCurrentMonthInCalenderModal()
	{
		return (findVisibleElement(currentMonthYearInCalender, Speed.slow).getText().split(" ")[0].trim());
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : To click on year month
	 *         column in start date calender in modal
	 */
	@Override
	public void clickOnYearMonthPickerInCalenderModal()
	{
		findVisibleElement(currentMonthYearInCalender, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated :
	 * @return To get the current year from the month picker in End date calender
	 */
	@Override
	public int getCurrentYearFromMonthPicker()
	{
		return (Integer.parseInt(findVisibleElement(currentYearInMonthPicker, Speed.slow).getText().trim()));
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : To click on next button
	 *         in month picker in start date calender
	 */
	@Override
	public void clickOnNextInMonthPicker()
	{
		findVisibleElement(nextYearButtonInMonthPicker).click();
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : To click on prev button
	 *         in month picker in start date calender
	 */
	@Override
	public void clickOnPreviousInMonthPicker()
	{
		findVisibleElement(previousYearButtonInMonthPicker).click();
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated : To select the particular
	 *         month from month picker in start date calender
	 */
	@Override
	public void clickOnMonthInMonthPicker(String month)
	{
		findVisibleElement(By.xpath(
				"//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[not(@class='month disabled') and normalize-space(text())='"
						+ getUserData(month.trim()) + "']"),
				Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 18th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public boolean verifyTaskInTimelineViewWithRespectiveDate(String date, String taskTitle)
	{
		List<WebElement> elements = driver.findElements(timelineDateHeader);
		int totalElements = elements.size();
		for (int i = 0; i < totalElements; i++)
		{
			String list = elements.get(i).getText().trim();
			if (date.equals(list.trim()))
			{
				String task = findVisibleElement(By.xpath(
						".//*[@id='taskTimelineScheduler']//td[contains(@class,'dhx_matrix_cell')][" + (i + 1) + "]"))
								.getText();
				return task.equals(taskTitle);
			}
		}
		return false;

	}

	/**
	 * @author bijesh.shesh Create : 13th April 2018 Updated : Verify view task
	 *         details site.
	 * @param taskSite
	 * @return true, if successful
	 */
	@Override
	public boolean verifyViewTaskDetailsSite(String taskSite)
	{
		WebElement viewTaskPriority = findVisibleElement(defaultSiteInAddTaskModal);
		String priority = viewTaskPriority.getText().trim();
		return priority.equals(taskSite.trim());
	}

	/**
	 * @author bijesh.shesh Create : 13th April 2018 Updated :
	 * @param desiredStartDate
	 * @return
	 */
	@Override
	public void editTaskStartDate(String desiredStartDate)
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		if (isDisplayed(taskStartDateInViewTaskModal))
		{
			findVisibleElement(taskStartDateInViewTaskModal).click();
			findVisibleElement(inlineStartDateDatePicker, Speed.slow);
			findVisibleElement(inlineStartDateDatePicker).clear();
			findVisibleElement(inlineStartDateDatePicker).sendKeys(desiredStartDate);
			findVisibleElement(inlineStartDatePickerSubmitButton, Speed.slow).click();
		}
	}

	/**
	 * @author bijesh.shesh Create : 13th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyTaskStartAndDueDate()
	{
		String startDate = findVisibleElement(taskStartDateInViewTaskModal).getText().trim();
		String dueDate = findVisibleElement(taskDueDate).getText().trim();
		return startDate.equals(dueDate);
	}

	/**
	 * @author jyoti.raj Create : 20th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyCloseButtonInViewTask()
	{
		return isDisplayed(closeButtonInViewTask);
	}

	/**
	 * @author jyoti.raj Create : 20th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyViewTaskModalVisibility()
	{
		return isDisplayed(viewTaskModal);
	}

	/**
	 * @author jyoti.raj Create : 20th April 2018 Updated :
	 * @param message
	 * @return
	 */
	@Override
	public boolean verifyInvalidStartDateInViewTaskModal(String message)
	{
		WebElement element = findVisibleElement(invalidStartDateInViewTaskModal);
		String msg = element.getText().trim();
		return msg.equals(message.trim());
	}

	/**
	 * @author jyoti.raj Create : 20th April 2018 Updated :
	 * @param message
	 * @return
	 */
	@Override
	public boolean verifyInvalidDueDateInEditTaskModal(String message)
	{
		WebElement element = findVisibleElement(invalidDueDateInViewTaskModal);
		String msg = element.getText().trim();
		return msg.equals(message.trim());
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnNoDueDateFromDatePickerInViewTaskModal()
	{
		findVisibleElement(taskDueDate, Speed.slow).click();
		findVisibleElement(inlineEditTaskDueDateDatePickerIcon, Speed.slow).click();
		findVisibleElement(noDueDateInViewTaskModal).click();
		findVisibleElement(inlineDueDatePickerSubmitButton, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnNoStartDateFromDatePickerInViewTaskModal()
	{
		findVisibleElement(taskStartDateInViewTaskModal, Speed.slow).click();
		findVisibleElement(inlineEditTaskStartDateDatePickerIcon, Speed.slow).click();
		findVisibleElement(noStartDateInViewTaskModal).click();
		findVisibleElement(inlineStartDatePickerSubmitButton, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnEditIconOfTaskStartDate()
	{
		findVisibleElement(taskStartDateInViewTaskModal).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnCancelIconOfStartDateInViewTaskModal()
	{
		findVisibleElement(cancelIconOfStartDateInViewTaskModal, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnEditIconOfTaskDueDate()
	{
		findVisibleElement(taskDueDate).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnCancelIconOfDueDateInViewTaskModal()
	{
		findVisibleElement(cancelIconOfDueDateInViewTaskModal, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnSubmitIconOfStartDateInViewTaskModal()
	{
		findVisibleElement(inlineStartDatePickerSubmitButton).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 */
	@Override
	public void clickOnSubmitIconOfDueDateInViewTaskModal()
	{
		findVisibleElement(inlineDueDatePickerSubmitButton).click();
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 * @param taskTitle
	 * @param statusName
	 * @param color
	 * @return
	 */
	@Override
	public boolean verifyStatusColorInViewTaskModal(String taskTitle, String statusName, String color)
	{
		String taskcolor = null;
		if (findVisibleElement(columnViewTaskViewTitle).getText().trim().equals(taskTitle.trim()))
		{
			taskcolor = gethashValueOfColor(columnViewTaskViewStatusValue, "background-color");
		}
		return color.trim().equalsIgnoreCase(taskcolor.trim());
	}

	/**
	 * @author jyoti.raj Create : 23th April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyXButtonVisibilityInViewTaskModal()
	{
		return isDisplayed(closeButtonInViewTask);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 */
	@Override
	public void clickOnStartDateDatePickerInAddTaskModal()
	{
		findVisibleElement(startdateDatePickerIconForAddTaskModal).click();
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 */
	@Override
	public void clickOnDueDateDatePickerInAddTaskModal()
	{
		findVisibleElement(dueDateDatePickerIconForAddTaskModal).click();
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 */
	@Override
	public void clickOnStartDateDatePickerInViewTaskModal()
	{
		findVisibleElement(startDateDatePickerIconForViewTaskModal).click();
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 */
	@Override
	public void clickOnDueDateDatePickerInViewTaskModal()
	{
		findVisibleElement(dueDateDatePickerIconForViewTaskModal).click();
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 */
	@Override
	public void setDateFromDatePickerForStartDateInAddTaskModal(String date)
	{
		clickOnStartDateDatePickerInAddTaskModal();
		setDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 */
	@Override
	public void setDateFromDatePickerForDueDateInAddTaskModal(String date)
	{
		clickOnDueDateDatePickerInAddTaskModal();
		setDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 */
	@Override
	public void setDateFromDatePickerForStartDateInViewTaskModal(String date)
	{
		clickOnStartDateDatePickerInViewTaskModal();
		setDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 */
	@Override
	public void setDateFromDatePickerForDueDateInViewTaskModal(String date)
	{
		clickOnDueDateDatePickerInViewTaskModal();
		setDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public boolean verifyStartDateFromDatePickerInAddTaskModal(String date)
	{
		clickOnStartDateDatePickerInAddTaskModal();
		return verifyDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public boolean verifyDueDateFromDatePickerInAddTaskModal(String date)
	{
		clickOnDueDateDatePickerInAddTaskModal();
		return verifyDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public boolean verifyStartDateFromDatePickerInViewTaskModal(String date)
	{
		clickOnStartDateDatePickerInViewTaskModal();
		return verifyDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 25th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public boolean verifyDueDateDatePickerInViewTaskModal(String date)
	{
		clickOnDueDateDatePickerInViewTaskModal();
		return verifyDateFromDatePicker(date);
	}

	/**
	 * @author jyoti.raj Create : 26th April 2018 Updated :
	 * @param startDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	@Override
	public boolean isDateAvailableInBetweenGivenDates(Date startDate, Date endDate, Date date)
	{
		return date.after(addDays(startDate, -1)) && date.before(addDays(endDate, 1));
	}

	/**
	 * @author jyoti.raj Create : 26th April 2018 Updated :
	 * @return
	 */
	@Override
	public String[] getStartDateandDueDateOfWeekOrTwoWeeksOrMonth()
	{
		String dates = findVisibleElement(getDate).getText().trim();
		String[] twodates = dates.split("-");
		return twodates;

	}

	/*
	 * @author jyoti.raj Create : 27th April 2018 Updated : (non-Javadoc)
	 */
	@Override
	public boolean verifyTaskInParticularView(String startDate, String endDate, String taskTitle)
			throws IOException, ParseException
	{

		WebElement taskStartDate = getTaskColumnInTimelineView(startDate);
		int cstrtpoint = taskStartDate.getLocation().getX();
		By title = By.xpath("//div[@class='TruncateTxt' and text()='" + taskTitle + "'][1]/ancestor::div[1]");
		WebElement task = findVisibleElement(title);
		int taskStartPoint = task.getLocation().getX();

		String dates[] = getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		Date findDate = sdf.parse(endDate);
		Date weekstartDate = sdf.parse(dates[0].trim());
		Date weekEndDate = sdf.parse(dates[1].trim());

		while (findDate.after(weekEndDate))
		{
			clickTimelineViewNextButton();
			String dates2[] = getStartDateandDueDateOfWeekOrTwoWeeksOrMonth();
			weekstartDate = sdf.parse(dates2[0].trim());
			weekEndDate = sdf.parse(dates2[1].trim());
			if (isDateAvailableInBetweenGivenDates(weekstartDate, weekEndDate, findDate))
			{
				WebElement taskEndCol = getTaskColumnInTimelineView(endDate);
				int taskColEndPoint = taskEndCol.getLocation().getX() + taskEndCol.getSize().getWidth();

				int taskColStartPoint = getTaskColumnInTimelineView(dates2[0]).getLocation().getX();

				title = By.xpath("//div[@class='TruncateTxt' and text()='" + taskTitle + "'][1]/ancestor::div[1]");
				task = findVisibleElement(title);

				int taskSP = task.getLocation().getX();

				int taskEP = task.getLocation().getX() + task.getSize().getWidth();

				return verifyAllPoints(taskSP, taskEP, taskColStartPoint, taskColEndPoint);

			}
			else
			{

				int weekColStartPoint = getTaskColumnInTimelineView(dates2[0]).getLocation().getX();
				int weekColEndPoint = getTaskColumnInTimelineView(dates2[1]).getLocation().getX()
						+ getTaskColumnInTimelineView(dates2[1]).getSize().getWidth();

				title = By.xpath("//div[@class='TruncateTxt' and text()='" + taskTitle + "'][1]/ancestor::div[1]");
				task = findVisibleElement(title);

				int taskSP = task.getLocation().getX();

				int taskEP = task.getLocation().getX() + task.getSize().getWidth();

				return verifyAllPoints(taskSP, taskEP, weekColStartPoint, weekColEndPoint);

			}

		}

		WebElement taskDueDate = getTaskColumnInTimelineView(endDate);
		int cendpoint = taskDueDate.getLocation().getX() + taskDueDate.getSize().getWidth();
		int taskEndPoint = task.getLocation().getX() + task.getSize().getWidth();
		return verifyAllPoints(taskStartPoint, taskEndPoint, cstrtpoint, cendpoint);
	}

	/**
	 * @author jyoti.raj Create : 27th April 2018 Updated :
	 * @param taskSp
	 * @param taskEP
	 * @param weekColSP
	 * @param weekColEP
	 * @return
	 */
	@Override
	public boolean verifyAllPoints(int taskSp, int taskEP, int weekColSP, int weekColEP)
	{
		int offSet = 5;
		return ((taskEP == weekColEP || (taskEP > weekColEP - offSet && taskEP < weekColEP + offSet))
				&& (taskSp == weekColSP || (taskSp > weekColSP - offSet && taskSp < weekColSP + offSet)));
	}

	/**
	 * @author jyoti.raj Create : 1st May 2018 Updated :
	 * @param taskName
	 * @return
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyToolTipOfTaskInTimelineView(String taskName)
	{

		findVisibleElement(taskTimelineView, Speed.slow);
		moveToElement(By.xpath(".//*[@class='TruncateTxt' and text()='" + taskName.trim() + "']"));
		findVisibleElement(By.xpath(".//*[@class='TruncateTxt' and text()='" + taskName.trim() + "']"), Speed.slow);
		String task = findVisibleElement(toolTipOfTask).getText();
		return task.trim().equals(taskName.trim());
	}

	/**
	 * @author Anil.patidar Created : 13th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickAllTasksInTimelineView()
	 */
	@Override
	public void clickAllTasksInTimelineView()
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		findClickableElement(taskTimelineViewAllTasks).click();
	}

	/**
	 * @author Anil.patidar Created : 13th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickAssignToMeTasksInTimelineView()
	 */
	@Override
	public void clickAssignToMeTasksInTimelineView()
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		findClickableElement(taskTimelineViewAssignToMe).click();
	}

	/**
	 * @author Anil.patidar Created : 13th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickCreatedByMeTasksInTimelineView()
	 */
	@Override
	public void clickCreatedByMeTasksInTimelineView()
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		findClickableElement(taskTimelineViewCreatedByMe).click();
	}

	/**
	 * @author Anil.patidar Created : 13th April 2018 Updated :
	 * @see com.highq.pageobjects.base.TasksPage#clickFavouritesTasksInTimelineView()
	 */
	@Override
	public void clickFavouritesTasksInTimelineView()
	{
		findVisibleElement(taskTimelineView, Speed.slow);
		findClickableElement(taskTimelineViewFavourites).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 03 May 2018
	 * @Updated:
	 * @return
	 */
	@Override
	public boolean verifyEnableAddButtonVisibilityForReminderInAddTaskModal()
	{
		return findVisibleElement(reminderAddButton).isEnabled();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 03 May 2018
	 * @Updated:
	 * @param reminderName
	 * @return
	 */
	@Override
	public boolean verifyReminderTitle(String reminder)
	{
		findVisibleElement(visibleAddTaskModal);
		int totalReminders = driver.findElements(listOfReminders).size();
		for (int i = 1; i <= totalReminders; i++)
		{
			String reminderName = findVisibleElement(
					By.xpath(".//*[@id='remiderList']/div[" + i + "]/span[2 and @class='paddRight5']")).getText();
			if (reminderName.trim().equals(reminder.trim()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @author jyoti.raj
	 * @Created: 03 May 2018
	 * @Updated:
	 * @param reminderName
	 * @return
	 */
	@Override
	public boolean verifyRemoveButtonVisibilityForReminder(String reminderName)
	{
		return isDisplayed(By.xpath(ADDTASK + "//*[normalize-space()='" + reminderName
				+ "']/following-sibling::a[@class='icon-remove tooltipShow']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 03 May 2018
	 * @Updated:
	 * @param mailSub
	 * @return
	 */
	@Override
	public boolean verifyMailSubject(String mailSub)
	{
		String mailSubject = (String) getResult("select mailsubject from Email order by id desc;");
		return mailSubject.trim().equals(mailSub.trim());
	}

	@Override
	public void clickOnAddAnotherTask(TaskHeaderActionButtons action) throws IOException
	{
		WebElement addAnotherTaskCheckbox = findClickableElement(By.xpath(
				".//*[@id='addAnotherID']//label[normalize-space(.)='" + TaskLabels.TASKS_ADDANOTHERTASK + "']"));
		addAnotherTaskCheckbox.click();
		if (action.equals(TaskHeaderActionButtons.Add))
		{
			WebElement addTaskButton = findClickableElement(addButtonTaskModel);
			addTaskButton.click();
		}
		else
		{
			WebElement cancelButton = findClickableElement(addTaskModalCancelButton);
			cancelButton.click();
		}
	}

	/**
	 * Verify expected tab is active(Selected) or not
	 * 
	 * @param tabName
	 *        name of the tab
	 * @return true if tab is active
	 * @author dheeraj.rajput
	 * @Created 18 May 2018
	 */
	public boolean verifyTabIsActive(String tabName)
	{
		tabName = tabName.replaceAll(" ", "_").toUpperCase().trim();
		By activeTab = By.xpath(".//*[@id='" + tabName + "'and @class='active']");
		return isDisplayed(activeTab, Speed.slow);
	}

	/**
	 * @author ganga.charan
	 */
	public void clickOnTaskAttachment()
	{
		findVisibleElement(columnViewTaskViewAttachment, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return the model availability
	 * @created on 11/05/2018
	 */
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return (isDisplayed(modal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the model availability
	 * @created on 11/05/2018
	 */
	public boolean verifyModal(String modalName)
	{
		return (isDisplayed(
				By.xpath("//*[@class='modal fade in']//*[normalize-space(text())='" + modalName.trim() + "']"),
				Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName
	 *        to be clicked
	 * @created on 11/05/2018
	 */
	public void clickOnModalButton(String buttonName)
	{
		findVisibleElement(By.xpath("//*[@class='modal fade in']//*[@class='modal-footer']//*[normalize-space(text())='"
				+ buttonName.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param message
	 *        to be available
	 * @return
	 * @created on 11/05/2018
	 */
	public boolean verifyModalMessage(String message)
	{
		return (isDisplayed(
				By.xpath("//*[@class='modal fade in']//*[normalize-space(text())='" + message.trim() + "']"),
				Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName
	 *        to be vrified
	 * @return
	 * @created on 11/05/2018
	 */
	public boolean verifyModalButton(String buttonName)
	{
		return (isDisplayed(
				By.xpath("//*[@class='modal fade in']//*[@class='modal-footer']//*[normalize-space(text())='"
						+ buttonName.trim() + "']"),
				Speed.slow));
	}

	/*
	 * @author jyoti.raj
	 * @Created: 07 May 2018
	 * @Updated: (non-Javadoc)
	 * @see com.highq.pageobjects.base.TasksPage#setReminder(int, java.lang.String)
	 */
	@Override
	public void setReminder(int remainderDay, String remiderType)
	{
		findClickableElement(addTaskAddEnabledAddRemainderButton).click();
		commonStepsForSetReminderMethod(remainderDay, remiderType, addTaskAddRemainder, reminderDateType);
		if (!isDisplayed(reminderErrorMessage))
		{
			clickOnAddReminder();
		}
	}

	/**
	 * @author jyoti.raj
	 * @Created: 08 May 2018
	 * @Updated:
	 * @param mailto
	 * @param startTime
	 * @param endTime
	 * @param emailSubject
	 * @param emailMessage
	 * @return
	 * @return
	 */
	public void getMailForTask(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject,
			String emailMessage)
	{
		String query;
		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
		{
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'"
					+ startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		else
		{
			query = "select TOP 1 * from Email where mailsubject like '" + emailSubject.trim() + "%"
					+ "' and mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'"
					+ endTime + "\'" + " order by id DESC";
		}
		System.out.println(query);
		mailContent = getMailContent(query);
		createHtmlFile(mailHtmlFile, mailContent);
		getLocalHtmlPage(mailHtmlFile);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 08 May 2018
	 * @Updated:
	 * @param dateType
	 * @return
	 */
	public boolean verifyReminderDropdownInAddTaskModal(String dateType)
	{
		findClickableElement(addTaskAddEnabledAddRemainderButton).click();
		return isDisplayed(By.xpath("//*[@id='taskReminderDateType']//*[normalize-space()='" + dateType + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 08 May 2018
	 * @Updated:
	 */
	public void clickOnCloseButtonForReminderInAddTaskModal()
	{
		findVisibleElement(closeButtonForReminder).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 08 May 2018
	 * @Updated:
	 * @param message
	 * @return
	 */
	public boolean verifyErrorMessageForDuplicateReminder(String message)
	{
		String msg = findVisibleElement(reminderErrorMessage).getText();
		return msg.trim().equals(message.trim());
	}

	/**
	 * @author jyoti.raj
	 * @Created: 08 May 2018
	 * @Updated:
	 * @param reminderName
	 */
	public void clickOnRemoveButtonForParticularReminder(String reminderName)
	{
		findVisibleElement(By.xpath(ADDTASK + "//*[normalize-space()='" + reminderName
				+ "']/following-sibling::a[@class='icon-remove tooltipShow']")).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @param reminder
	 * @return
	 */
	public boolean verifyReminderTitleInViewTaskModal(String reminder)
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		return isDisplayed(By.xpath(".//*[@class='margBott5']//span[normalize-space()='" + reminder + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @param reminderName
	 */
	public void clickOnRemoveButtonForParticularReminderInViewTaskModal(String reminderName)
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		findVisibleElement(By.xpath(".//*[@class='margBott5']//span[normalize-space()='" + reminderName + "']/following-sibling::a[@id='deleteEditTaskModalReminder']")).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @param reminderName
	 * @return
	 */
	public boolean verifyRemoveButtonForParticularReminderInViewTaskModal(String reminderName)
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		return isDisplayed(By.xpath("//span[@class='paddRight5' and normalize-space()='" + reminderName
				+ "']/following-sibling::a[@id='deleteEditTaskModalReminder']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @return
	 */
	public boolean verifyAddButtonForReminderInViewTaskModal()
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		return isDisplayed(addReminderLink);
	}

	/*
	 * (non-Javadoc)
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @see com.highq.pageobjects.base.TasksPage#
	 * clickOnAddButtonForReminderInViewTaskModal(java.lang.String)
	 */
	public void clickOnAddButtonForReminderInViewTaskModal()
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		findVisibleElement(addReminderLink).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @param dateType
	 * @return
	 */
	public boolean verifyReminderDropdownInViewTaskModal(String dateType)
	{
		clickOnAddButtonForReminderInViewTaskModal();
		return isDisplayed(By.xpath(".//*[@id='inlineEdit_taskReminderDateType']//option[normalize-space()='" + dateType + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 */
	public void clickOnCloseButtonForReminderInViewTaskModal()
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		findVisibleElement(closeButtonForReminderInViewTask).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 */
	public void clickOnOkButtonForReminderInViewTask()
	{
		findVisibleElement(columnViewTaskViewPopup, Speed.slow);
		WebElement addToAddRemainderButton = findVisibleElement(inlineEditReminderSubmitButton);
		addToAddRemainderButton.click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 09 May 2018
	 * @Updated:
	 * @param remainderDay
	 * @param remiderType
	 */
	public void setReminderInViewTask(int remainderDay, String remiderType)
	{
		clickOnAddButtonForReminderInViewTaskModal();
		commonStepsForSetReminderMethod(remainderDay, remiderType, inlineTaskReminderInputId,
				reminderDateTypeInViewTask);
		if (!isDisplayed(reminderErrorMessageInViewTask))
		{
			clickOnOkButtonForReminderInViewTask();
		}

	}

	/**
	 * @author jyoti.raj
	 * @Created: 10 May 2018
	 * @Updated:
	 * @return
	 */
	public boolean verifyEditIConOfStartDateInViewTaskModal()
	{
		return isDisplayed(editIconOfStartDate);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 10 May 2018
	 * @Updated:
	 * @return
	 */
	public boolean verifyEditIconOfDueDateInViewTaskModal()
	{
		return isDisplayed(editIconOfDueDate);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 * @throws InterruptedException
	 */
	public void clickOnPersonalTab() throws InterruptedException
	{
		WebElement ele = findVisibleElement(personalTab);
		ele.click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 * @param message
	 * @return
	 */
	public boolean verifyErrorMessageForDuplicateReminderInViewTaskModal(String message)
	{
		String msg = findVisibleElement(reminderErrorMessageInViewTask).getText();
		return msg.trim().equals(message.trim());
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 */
	public void clickOnMoreActionInTaskModule()
	{
		findVisibleElement(moreActionOfTaskModule).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 * @param option
	 */
	public void selectOptionFromMoreActionInTaskModule(String option)
	{
		findVisibleElement(By.xpath(
				"//span[contains(@class,'dropdown inlineBlock margLeft10')]//a[@data-original-title='More actions']/following::*//a[normalize-space()='"
						+ option + "']")).click();
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 * @param fileName
	 * @param importStartAndDueDate
	 * @param importAssignees
	 */
	public void importTask(String fileName, boolean importStartAndDueDate, boolean importAssignees)
	{
		findVisibleElement(importModal, Speed.slow);
		findVisibleElement(browseFileInImportModal).sendKeys(fileName);
		findVisibleElement(fileDoneInImportModal, Speed.slow);
		setSelection(importDueDateInImportModal, importStartAndDueDate);
		setSelection(importAssigneesInImportModal, importAssignees);
		findVisibleElement(nextButtonInImportModal, Speed.slow).click();
		findVisibleElement(viewPreviewInImportModal, Speed.slow);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 * @return
	 */
	public boolean verifyImportStartAndDueDatesOrImportAssigneesInImportModal(String option)
	{
		return isDisplayed(By.xpath("//label[normalize-space()='" + option + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 15 May 2018
	 * @Updated:
	 */
	public void clickOnImportButtonInImportModal()
	{
		findVisibleElement(importButtonInImportModal, Speed.slow).click();
		findVisibleElement(importModalClosed, Speed.slow);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 16 May 2018
	 * @Updated:
	 * @return
	 */
	public boolean verifyImportButtonVisibilityInImportModal()
	{
		return isDisplayed(importButtonInImportModal);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 17 May 2018
	 * @Updated:
	 * @param errorMessage
	 * @return
	 */
	public boolean verifyErrorMeassageInImportModal(String errorMessage)
	{
		return isDisplayed(By.xpath("//a[normalize-space()='" + errorMessage + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 17 May 2018
	 * @Updated:
	 * @param label
	 * @return
	 */
	public boolean verifyLablesInViewPreviewOfImportTaskModal(String label)
	{
		return isDisplayed(By.xpath("//div[text()='" + label + "']"));
	}

	/**
	 * @author jyoti.raj
	 * @Created: 17 May 2018
	 * @Updated:
	 * @param remainderDay
	 * @param remiderType
	 * @param reminderinput
	 * @param reminderTypes
	 */
	public void commonStepsForSetReminderMethod(int remainderDay, String remiderType, By reminderinput,
			By reminderTypes)
	{
		WebElement taskReminder = findVisibleElement(reminderinput);
		taskReminder.click();
		taskReminder.sendKeys(Keys.BACK_SPACE);
		taskReminder.sendKeys(Integer.toString(remainderDay));
		WebElement taskStatus = findVisibleElement(reminderTypes);
		Select statusSelect = new Select(taskStatus);
		statusSelect.selectByVisibleText(remiderType);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 05 May 2018
	 * @Updated: 17 May 2018
	 */
	public boolean verifyUnassignedInTimelineView()
	{
		WebElement allTasks = findVisibleElement(unassignedAssignee);
		return allTasks.isDisplayed();

	}

	/**
	 * @author jhanvi.dave
	 * @Created: 06 May 2018
	 * @Updated: 16 May 2018
	 * @param :
	 *        userEmail
	 */
	public void openMinicardInTimelineView(String userEmail)
	{
		WebElement assigneeInTimeline = findVisibleElement(By.xpath(
				".//*[normalize-space(text())='" + userEmail + "']/../preceding-sibling::*[@class='usercardLink']"),
				Speed.slow);
		assigneeInTimeline.click();

	}

	/**
	 * @author jhanvi.dave
	 * @Created: 07 May 2018
	 */
	public ViewUserProfilePage clickUserInMinicard()
	{
		WebElement userInMinicard = findVisibleElement(userTitleInMinicard, Speed.slow);
		userInMinicard.click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 09 May 2018
	 * @Updated: 16 May 2018
	 * @param :
	 *        userEmail
	 */
	public boolean verifyHeaderViewOptionsInTimeline(String viewOption)
	{
		return isDisplayed(By.xpath(".//*[@id='taskOrderByOptions']//*[normalize-space(text())='" + viewOption + "']"),
				Speed.slow);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 09 May 2018
	 * @Updated: 16 May 2018
	 * @param :
	 *        viewOption
	 */
	public void selectHeaderViewOptions(String viewOption)
	{
		if (isDisplayed(viewBtnDropdown))
		{
			WebElement option = findVisibleElement(
					By.xpath(".//*[@id='taskOrderByOptions']//*[normalize-space(text())='" + viewOption + "']"),
					Speed.slow);
			option.click();
		}
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 06 May 2018
	 */
	public boolean verifyNoneListInTimeline()
	{
		return isDisplayed(noneList);

	}

	/**
	 * @author jhanvi.dave
	 * @Created: 10 May 2018
	 * @Updated: 16 May 2018
	 * @param :
	 *        listOrAssigneename, taskname
	 * @return
	 */
	public boolean verifyTaskInParticularGroupByOptionInTimelineView(String groupByoption, String taskname)
	{
		return isDisplayed(By.xpath(".//*[@id='taskTimelineScheduler']//*[normalize-space(text())='" + groupByoption
				+ "']/ancestor::td/following-sibling::*//*[@class='TruncateTxt' and normalize-space(text())='"
				+ taskname + "']"));

	}

	/**
	 * @author jhanvi.dave
	 * @Created: 11 May 2018
	 * @Updated:
	 * @param :
	 *        duplicateList
	 */
	public boolean verifiDuplicateListInTimeline(String duplicateList)
	{
		List<WebElement> lists = driver.findElements(
				By.xpath(".//*[@id='taskTimelineScheduler']//*[normalize-space(text())='" + duplicateList + "']"));
		if (lists.size() > 1)
			return true;

		return false;
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 11 May 2018
	 */
	public void clickOnStartDateDropdown()
	{
		WebElement dropdown = findClickableElement(startDateDropdown);
		dropdown.click();
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 11 May 2018
	 */
	public void clickOnDueDateDropdown()
	{
		WebElement dropdown = findClickableElement(dueDateDropdown);
		dropdown.click();
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 05 May 2018
	 * @Updated: 16 May 2018
	 * @param :
	 *        defaultGrouping
	 */
	public boolean verifyDefaultGroupingInTimeline(String defaultGrouping)
	{
		findVisibleElement(taskTimelineScheduler, Speed.slow);
		return isDisplayed(
				By.xpath(".//*[@id='timeline_sectionHeader_lable_ID' and text()='" + defaultGrouping + "']"));
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 12 May 2018
	 * @Updated:
	 * @param groupingAction
	 * @return div number of sort dropdown
	 */
	public int getColumnIndex(String groupingAction)
	{
		List<String> indexName = new ArrayList<>();
		List<WebElement> index = driver.findElements(sortingIndex);
		for (int i = 2, j = 0; i <= index.size() && j <= index.size(); i++, j++)
		{
			indexName.add(findVisibleElement(By.xpath(
					".//*[@id='taskDataTableHead']//*[contains(@class,'taskColHead')]/div[" + (i - 1) + "]/strong"))
							.getText().trim());
			if ((indexName.get(j)).equals(groupingAction))
			{
				indexName.clear();
				return i;
			}
		}
		return 0;
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 15 May 2018
	 * @param :
	 *        list2, groupingAction, element
	 * @return : true if elements are in sorted order
	 */
	public boolean verifyGroupingForAscending(List<String> elementsToBeSorted, String groupingAction,
			String anyGroupByLabel)
	{
		Collections.sort(elementsToBeSorted);
		findVisibleElement(taskMiddlePannel, Speed.slow);
		int index = getColumnIndex(groupingAction);
		List<WebElement> allTasks = driver.findElements(By.xpath(TASKLISTDIV + "/div/div"));
		String heading = "";
		List<String> list = new ArrayList<String>();
		for (int i = 3; i <= allTasks.size(); i++)
		{

			String css = findVisibleElement(By.xpath(TASKLISTDIV + "/div/div[" + i + "]")).getAttribute("class");
			if (css.startsWith("taskColListTitle"))
			{
				heading = getText(By.xpath(TASKLISTDIV + "/div/div[" + i + "]/strong"));
			}
			if (anyGroupByLabel.equals(heading))
			{
				List<WebElement> alllist = driver
						.findElements(By.xpath(TASKLISTDIV + "/div/div[" + (i + 1) + "]/div/div[" + index + "]"));
				for (WebElement e : alllist)
				{
					list.add(e.getText().trim());
				}
			}
		}
		return (list.equals(elementsToBeSorted));
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 17 May 2018
	 * @param list2
	 * @param groupingAction
	 * @param element
	 * @return
	 */
	public boolean verifyGroupingForDescending(List<String> elementsToBeSorted, String groupingAction,
			String anyGroupByLabel)
	{
		Collections.sort(elementsToBeSorted, Collections.reverseOrder());
		findVisibleElement(taskMiddlePannel, Speed.slow);
		int index = getColumnIndex(groupingAction);
		List<WebElement> allTasks = driver.findElements(By.xpath(TASKLISTDIV + "/div/div"));
		String heading = "";
		List<String> list = new ArrayList<String>();
		for (int i = 3; i <= allTasks.size(); i++)
		{

			String css = findVisibleElement(By.xpath(TASKLISTDIV + "/div/div[" + i + "]")).getAttribute("class");
			if (css.startsWith("taskColListTitle"))
			{
				heading = getText(By.xpath(TASKLISTDIV + "/div/div[" + i + "]/strong"));
			}
			if (anyGroupByLabel.equals(heading))
			{
				List<WebElement> alllist = driver
						.findElements(By.xpath(TASKLISTDIV + "/div/div[" + (i + 1) + "]/div/div[" + index + "]"));
				for (WebElement e : alllist)
				{
					list.add(e.getText().trim());
				}
			}
		}
		return (list.equals(elementsToBeSorted));
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 15 May 2018
	 * @param :
	 *        groupByOption
	 */
	public boolean verifyHeaderGroupByOptions(String groupByOption)
	{
		return isDisplayed(
				By.xpath(".//*[@id='taskGroupByOptions']//*[normalize-space(text())='" + groupByOption + "']"),
				Speed.slow);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 15 May 2018
	 * @param :
	 *        groupByOption
	 */
	public void selectHeaderGroupByOptions(String groupByOption)
	{
		if (isDisplayed(groupByDropdown))
		{
			WebElement option = findVisibleElement(
					By.xpath(".//*[@id='taskGroupByOptions']//*[normalize-space(text())='" + groupByOption + "']"),
					Speed.slow);
			option.click();
		}
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 18 May 2018
	 * @param :
	 *        groupByOption
	 */
	public boolean verifyPriorityAndStatusInTimeline(String priorityOrStatusName)
	{
		findVisibleElement(tdOfGroupByInTimeline);
		return isDisplayed(
				By.xpath(".//*[@class='dhx_matrix_scell']//*[normalize-space(text())='" + priorityOrStatusName + "']"),
				Speed.slow);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 18 May 2018
	 * @param :
	 *        taskName
	 */
	public boolean verifyCompletedTaskWithStrikeOffInTimeline(String taskName)
	{
		return isDisplayed(By.xpath(
				".//*[normalize-space(text())='Complete']/ancestor::td/following-sibling::*//*[contains(@class,'dhx_cal_event_line timelineTaskComplete')]//*[@class='TruncateTxt' and normalize-space(text())='"
						+ taskName + "']"));
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 05 May 2018
	 * @Updated: 17 may 2018
	 * @param :
	 *        email
	 */
	public boolean verifyGroupByAssigneeInTimelineView(String email)
	{

		return isDisplayed(By.xpath(".//*[@class='overflowHidden lineHeightNormal']//*[normalize-space()='" + email
				+ "']/preceding-sibling::*[@class='usercardLink']"))
				&& isDisplayed(By
						.xpath(".//*[@class='overflowHidden lineHeightNormal']//*[normalize-space()='" + email + "']"))
				&& isDisplayed(By.xpath(".//*[@class='overflowHidden lineHeightNormal']//*[normalize-space(text())='"
						+ email
						+ "']/ancestor::*[starts-with(@class,'overflow')]/preceding-sibling::*[@id='taskAvtarContainerID_0']"));
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 17 may 2018
	 * @Updated:
	 * @param :
	 *        email
	 */
	public boolean verifyGroupByAssigneeGroupNameInTimelineView(String groupName)
	{

		return isDisplayed(
				By.xpath(".//*[@class='overflowHidden lineHeightNormal']//a[normalize-space()='" + groupName + "']"));
	}

	/**
	 * @author surbhi.khetan
	 * @param option
	 *        To select option from more actions on tasks home page
	 * @created on 29/05/2018
	 */
	@Override
	public void selectMoreActionOption(String option)
	{
		findVisibleElement(moreActionIcon).click();
		findVisibleElement(moreActionModal);
		WebElement optionToClick = findVisibleElement(By.xpath("//*[normalize-space(text())='" + option.trim() + "']"));
		optionToClick.click();
	}

	/**
	 * @author surbhi.khetan
	 * @param option
	 *        To select option from more actions of an individual task
	 * @created on 29/05/2018
	 */
	@Override
	public void selectMoreActionOptionForIndividualTask(String option)
	{
		findVisibleElement(columnViewTaskViewHeaderMoreAction).click();
		findVisibleElement(moreActionModalOfTask);
		WebElement optionToClick = findVisibleElement(
				By.xpath(COLUMNVIEW_VIEW_TASK_POPUP_PATH + "//*[normalize-space(text())='" + option.trim() + "']"));
		optionToClick.click();
	}

	/**
	 * @author surbhi.khetan
	 * @param fileName
	 *        To import tasks from excel
	 * @created on 29/05/2018
	 */
	@Override
	public void importExcelFile(String fileName)
	{
		findVisibleElement(importModal);
		uploadFile(importFileBrowse, fileName);
		WebElement nxtBtn = findClickableElement(nextImportBtn, Speed.slow);
		nxtBtn.click();
		findVisibleElement(viewPreview, Speed.slow);
		nxtBtn.click();
		findVisibleElement(taskPanel, Speed.slow);
	}

	/**
	 * @author surbhi.khetan
	 * @created 30/05/2018 Click on close button of window when an item is opened
	 *          for print preview
	 */
	@Override
	public void clickOnCloseButtonOfPrintPreview()
	{

		findVisibleElement(closeButtonPrint, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @created 30/05/2018 To verify task visibility in print preview
	 * @param task
	 */
	@Override
	public boolean verifyTaskVisiblityInPrintPreview(String task)
	{
		return (isDisplayed(By.xpath(".//div[@ class='colTitle']//a[text()='" + task.trim() + "']"), Speed.slow));
	}

	/**
	 * @author surbhi.khetan
	 * @created 30/05/2018 To verify task visibility of an individual task in print
	 *          preview
	 * @param task
	 */
	@Override
	public boolean verifyTaskVisiblityInPrintPreviewForIndividualTask(String task)
	{
		return (isDisplayed(By.xpath(".//*[@id='printTaskDetailBodySection']/div/h3[text()='" + task.trim() + "']"),
				Speed.slow));
	}
}
