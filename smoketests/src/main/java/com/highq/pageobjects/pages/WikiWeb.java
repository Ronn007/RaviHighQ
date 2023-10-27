package com.highq.pageobjects.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.DBConnection;
import com.highq.base.DBManager;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.WikiLabels;
import com.highq.pageobjects.base.WikiPage;

public class WikiWeb extends BannerPageWeb implements WikiPage
{
	Actions build;

	/** The driver. */

	/** Add wiki button */
	By addWiki = By.xpath("//*[contains(@class,'icon-plus-circle greyFont tooltipShow')]");

	By moreActionInSelectedWiki = By.xpath("//a[@class='icon icon-actions dropdown-toggle' and contains(@data-original-title,'" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "')]");

	By wikiHomeButton = By.className("fancytree-title");

	/** Wiki Modal quit button */
	By wikiModalQuitButton = By.xpath("//*[contains(@id,'MAIN_CLOSE_BUTTON')]");

	/** Edit wiki Favourites Button in left pannel */
	By wikiFavouritesLeftButton = By.xpath(".//*[@id='favouriteBtn']//*[normalize-space(text())='" + WikiLabels.WIKI_FAVOURITES + "']");

	/** Edit wiki Favourites no data found */
	By noFavourites = By.xpath("//*[@id='favouritesTableID']//h6");

	/** Edit wiki title */
	By wikiTitle = By.id("wikiTitleID");

	/** Wiki Title Text Box */
	By wikiTitleTextBox = By.xpath("//input[contains(@id,'ikiTitle')]");

	/** Save wiki title */
	By wikiTitleSaveButton = By.id("WIKI_TITLE_CHECK_BUTTON_ID");

	/** Cancel wiki title */
	By wikiTitleCancelButton = By.xpath("//*[@id='wikiTitleInlineControlsID']//button[@class='btn-xs edit-cancel']");

	/** Click on like */
	By wikiLikeTag = By.id("likeContent");

	/** Click on Comment */
	By wikiCommentTag = By.xpath("//*[contains(@id,'commentLink')]");

	/** Comment box */
	By wikiCommentBox = By.xpath("//*[contains(@id,'addCommentField') and @role='textbox']");

	/** Cancel Comment from comment box */
	By wikiCommentCancel = By.xpath("//*[contains(@id,'postBtn_addCommentField')]//preceding-sibling :: button");

	/** Post Comment button */
	By wikiCommentPostButton = By.xpath("//*[contains(@id,'postBtn_addCommentField')]");

	/** Comment Box footer */
	By wikiCommentBoxFooter = By.xpath("//*[contains(@id,'comment_commentFooter')]");

	/** insert link in Comment box */
	By linkButton = By.className("icon-link postIcon pull-left tooltipShow");

	/** Attachment in Comment box */
	By attachButton = By.xpath("//*[contains(@id,'comment_uploadAttachment_')]/..");

	By wikiDeleteCancelButton = By.id("collaborateMessageCancelButton");

	By wikiDeleteOKButton = By.id("collaborateMessageOkButton");

	/** Text Formating button in Comment box */
	By textFormateButton = By.xpath("//*[contains(@id,'comment_commentFooter')]//span[@class='inlineBlock dropup']/a");

	/** Getting tags count */
	By tagCount = By.id("tagsCountID");

	/** Add tags button */
	By addTagButton = By.id("addTagIconID");

	/** Add tags text box */
	By tagTextBox = By.id("wiki_modile_inlineEdit_tagList_ID-tokenfield");

	By disabledTagBox = By.xpath("//*[contains(@class,'tokenBig')]");

	By enabledTagBox = By.xpath("//*[contains(@class,'tokenBig focus')]");

	/** Add tags Save Button */
	By tagSaveButton = By.id("WIKI_TAG_CHECK_BUTTON_ID");

	/** Add tags Cancel Button */
	By tagCancelButton = By.xpath("//*[@id='WIKI_TAG_CHECK_BUTTON_ID']//following-sibling::button");

	/** Created Date */
	By wikiCreatedDateTag = By.xpath("//*[@class='metaConData']/span");

	/** Orphan Pages **/
	By leftpanel_OrphanPagesLink = By.xpath("//*[@id='leftTreeAndPageLinks']//*[normalize-space(text())='" + WikiLabels.WIKI_ORPHANPAGES + "']");
	By orphanPagesExpanded = By.xpath("//*[@id='leftTreeAndPageLinks']//*[normalize-space(text())='" + WikiLabels.WIKI_ORPHANPAGES + "']/../following-sibling::ul");

	/** Created By */
	By wikiCreatedByTag = By.xpath("//*[@class='metaConData']/a");

	/** Wiki Likes count */
	By likesCount = By.id("wikiLikeCount");

	/** Wiki Comment count */
	By CommentCount = By.id("wikiCommentCount");

	/** Wiki Text Edit Button */
	By wikiTextEditButton = By.className("icon icon-edit-circle margLeft10 editCkInlineIcon hidden-xs");

	/** Wiki favourate button */
	By favouriteButton = By.xpath("(//*[contains(@class,'icon-star') and @data-original-title='" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_ADDTOFAVOURITES + "'])[last()]");// By.className("icon icon-star margLeft10");

	/** Wiki favourite Selected */
	By favouriteStarSelected = By.xpath("(//*[contains(@class,'icon-star-selected') and @data-original-title='" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_REMOVEFROMFAVOURITES + "'])[last()]");

	/** Wiki Text Box */
	By wikiTextBox = By.xpath("//*[contains(@class,'ckContentArea') and @role='textbox']");

	/** Wiki Bottom Supress all notification Check Box */
	By wikiSupressAllNotificationBottomCheckBox = By.id("suppressAllNotificationID_Bottom");

	/** Wiki Top Supress all notification Check Box */
	By wikiSupressAllNotificationTopCheckBox = By.id("suppressAllNotificationID_Top");

	/** Wiki Text Bottom Save Button */
	By wikTextBottomSaveButton = By.id("WIKI_CKEDITOR_INLINE_CHECK_BUTTON_ID_Bottom");

	/** Wiki Text Bottom Save Button */
	By wikTextTopSaveButton = By.id("WIKI_CKEDITOR_INLINE_CHECK_BUTTON_ID_Top");

	/** Wiki Top Text cancel button */
	By wikTextTopCancelButton = By.xpath("//*[@id='WIKI_CKEDITOR_INLINE_CHECK_BUTTON_ID_Top']//following-sibling::button");

	/** Wiki Bottom Text cancel button */
	By wikTextBottomCancelButton = By.xpath("//*[@id='WIKI_CKEDITOR_INLINE_CHECK_BUTTON_ID_Bottom']//following-sibling::button");

	/** Wiki More Action button */
	By wikiMoreActionButton = By.className("icon icon-actions dropdown-toggle");

	/** Wiki More Action info modal created by */
	By wikiMoreActionInfoModalCreatedBy = By.xpath("(//*[@class='usercardLink'])[2]");

	/** Wiki More Action info modal Updated by */
	By wikiMoreActionInfoModalUpdatedBy = By.xpath("(//*[@class='usercardLink'])[3]");

	/** Wiki More Action info modal created Date */
	By wikiMoreActionInfoModalCreatedDate = By.xpath("(//*[@class='col-sm-9'])[1]/span");

	/** Wiki More Action info modal Updated Date */
	By wikiMoreActionInfoModalUpdatedDate = By.xpath("(//*[@class='col-sm-9'])[2]/span");

	/** Wiki More Action info modal Link text box */
	By wikiMoreActionInfoModalLinkTextBox = By.id("copyEmail");

	/** Wiki More Action info modal use short link check box */
	By wikiMoreActionInfoModalUseShortLinkCheckBox = By.id("chkshortenURL");

	/** Wiki More Action info modal versions number */
	By wikiMoreActionInfoModalversion = By.xpath("(//*[@class='col-sm-5'])[2]/span");

	/** Wiki More Action info modal close button */
	By wikiMoreActionInfoModalCloseButton = By.id("WIKI_INFO_MODAL_WikiInfo_CloseButton");

	/** Wiki More Action info modal Export as PDf button */
	By wikiMoreActionInfoModalExportButton = By.id("WIKI_INFO_MODAL_WikiInfo_ExportButton");

	/** Wiki More Action info modal Print Button */
	By wikiMoreActionInfoModalPrintButton = By.id("WIKI_INFO_MODAL_WikiInfo_PrintButton");

	/** Wiki More Action info modal Compare Button */
	By wikiMoreActionInfoModalCompareButton = By.id("WIKI_INFO_MODAL_WikiInfo_CompareButton");

	/** Wiki More Action info modal header overview Button */
	By wikiMoreActionInfoModalOverview = By.id("overviewTabHeader");

	/** Wiki More Action info modal header versions Button */
	By wikiMoreActionInfoModalVersions = By.id("versionHistoryTabHeader");

	/** Wiki More Action info modal header Attachments Button */
	By wikiMoreActionInfoModalAttachments = By.id("attachmentTabHeader");

	/** Wiki Left pannel hide arrow button */
	By wikiLeftPannelHideArrow = By.className("greyFont tooltipShow icon-chevron-left");

	/** Wiki Left pannel Show arrow button */
	By wikiLeftPannelShowArrow = By.className("greyFont tooltipShow icon-chevron-right");

	By wikiFavouratesLeftButton = By.xpath(".//*[@id='favouriteBtn']//*[normalize-space(text())='" + WikiLabels.WIKI_FAVOURITES + "']");

	/** Wiki Sort Wiki button */
	By wikiSortButton = By.className("icon-sort-az greyFont tooltipShow");

	/** Wiki Sort modal */
	By wikiSortModal = By.className("modal-content");

	/** Wiki Sort modal Drop DOwn */
	By wikiSortModalDropDown = By.id("wikiSortID");

	/** Wiki Sort modal drop down save Button */
	By wikiSortModalSaveButton = By.id("SORT_WIKI_MODAL_saveButton");

	/** Wiki Sort modal drop down Cancel Button */
	By wikiSortModalCancelButton = By.id("SORT_WIKI_MODAL_cancelButton");

	/** Wiki Sort modal Close Button */
	By wikiSortModalCloseButton = By.id("SORT_WIKI_MODAL_MAIN_CLOSE_BUTTON");

	/** Wiki Edit text from more action to click on notifications drop down */
	By wikiEditNotificationDropDown = By.xpath("//*[@id='wikiNotificationID']/..");

	/** Wiki Edit text from more action to click on Status drop down button */
	By wikiEditStatusDropDown = By.xpath("//*[@id='workFlowActionID']/..");

	/** Wiki Edit text from more action to click on top Save button */
	By wikiEditTopSaveButton = By.id("addEditWikiSaveBtnTopID");

	/** Wiki Edit text from more action to click on Bottom Save button */
	By wikiEditBottomSaveButton = By.id("addEditWikiSaveBtnBottomID");

	/** Wiki Edit text from more action to click on top Cancel button */
	By wikiEditTopCancelButton = By.xpath("//*[@id='addEditWikiSaveBtnTopID']//preceding-sibling::button");

	/** Wiki Edit text from more action to click on Bottom Cancel button */
	By wikiEditBottomCancelButton = By.xpath("//*[@id='addEditWikiSaveBtnBottomID']//preceding-sibling::button");

	/** Wiki Edit text from more action Content header */
	By wikiEditContentHeader = By.xpath("//*[@class='pageTab paddBott20 minusContent']//a[text()='" + WikiLabels.WIKI_EDIT_CONTENT + "']");

	/** Wiki Edit text from more action Attachment header */
	By wikiEditAttachmentHeader = By.xpath("//*[@class='pageTab paddBott20 minusContent']//a[text()='" + WikiLabels.WIKI_EDIT_ATTACHMENTS + "']");

	/** Wiki Edit text from more action Setting header */
	By wikiEditSettingHeader = By.xpath("//*[@class='pageTab paddBott20 minusContent']//a[text()='" + WikiLabels.WIKI_EDIT_SETTINGS + "']");

	/** Wiki Edit text from Setting header allow comments check box */
	By settingAllowComments = By.id("allowWikiCommentID");

	/** Wiki Edit text from Setting header Show and hide tag */
	By settingShowHideTag = By.xpath("//*[@class='permissionShowHide']");

	/** Wiki Edit text from Setting header Permission Quick serch text box */
	By permissionQuickSerchTextBox = By.id("wikiPermissionQuickSearch");

	/** Wiki Edit text from Setting header click on show after serch text no result vrify text */
	By settingSerchTextDataHeader = By.xpath("//*[@class='trDataHeader collapsed']/td");

	/** Wiki Edit text from Setting header view select all id */
	By settingViewSelectAllId = By.id("viewWikiSelectAllID");

	/** Wiki Edit text from Setting header Edit select all id */
	By settingEditSelectAllId = By.id("editWikiSelectAllID");

	/** Wiki Share Modal */
	By shareModal = By.id("file_module_shared_file_link");

	/** Wiki Sahre modal email header */
	By shareModalEmailHeader = By.xpath("//*[@class='modalBodyTop paddBott15 clearfix']//a[text()='" + WikiLabels.WIKI_SHARE_EMAIL + "']");

	/** Wiki Sahre modal Message header */
	By shareModalMessageHeader = By.xpath("//*[@class='modalBodyTop paddBott15 clearfix']//a[text()='" + WikiLabels.WIKI_SHARE_MESSAGE + "']");

	/** Wiki Sahre modal Link header */
	By shareModalLinkHeader = By.xpath("//*[@class='modalBodyTop paddBott15 clearfix']//a[text()='" + WikiLabels.WIKI_SHARE_LINK + "']");

	/** Wiki Sahre modal Receipient text box */
	By receipientTextBox = By.id("file_module_emailRecipients-tokenfield");

	/** Wiki Sahre modal Subject text box */
	By subjectTextBox = By.id("file_module_email_subject");

	/** Wiki Sahre modal message text box */
	By messageTextBox = By.id("addEditWikiAttachmentsckUploadFile");

	/** Wiki Sahre modal Link header */
	By attachmentBrowseButton = By.id("addEditWikiAttachmentsckUploadFile");

	/** Wiki Sahre modal Send Button */
	By shareModalSendButton = By.id("file_module_shared_file_link_file_module_shareModal_emailAndMessage_send_btn");

	/** Wiki Sahre modal Cancel Button */
	By shareModalCancelButton = By.id("file_module_shared_file_link_file_module_shareModal_close_btn");

	/** Wiki Export To PDF Modal */
	By printAndExportModal = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL");

	/** Wiki Export To PDF modal Cancel Button */
	By printAndExportModalCancelButton = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_cancelButton");

	/** Wiki Export To PDF modal Export Button */
	By printAndExportModalExportButton = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_printOrExportButton");

	/** Wiki Export To PDF modal This page only radio button */
	By printAndExportThisPageRadio = By.id("thisPage");

	/** Wiki Export To PDF modal this page and all its children radio */
	By printAndExportThisPageAndAllRadio = By.id("includeChild");

	/** Wiki Export To PDF modal Include its index on first page */
	By printAndExportModalIncludeIndex = By.id("includeIndex");

	/** Wiki Export To PDF modal Level */
	By printAndExportModalLevel = By.id("levelOptions");

	/** Wiki delete comment confirmation modal after clicking on delete button */
	By deleteCommentConfirmationModal = By.id("collaborateCustomMessageModal");

	/** Wiki cancel button after clicking on delete comment confirmation modal */
	By deleteCommentConfirmationModalCancelButton = By.id("collaborateMessageCancelButton");

	/** Wiki save button after clicking on delete comment confirmation modal */
	By deleteCommentConfirmationModalDeleteButton = By.id("collaborateMessageOkButton");

	By closeCompareModel = By.xpath(".//*[@id='WIKI_MODULE_COMPARE_VERSION' and contains(@style,'display: block;')]//*[@class='icon icon-remove']");

	By closeWikiInfoModel = By.xpath("//*[@id='WIKI_INFO_MODAL']//*[@class='icon icon-remove']");

	/** To load more comments in wiki home page */
	By wikiLoadMoreComments = By.xpath("//*[@class='commentBlock TxtCenter loadMoreSection']/a");

	/** Insert link dialog modal comes after clicking on insert link button under comment box */
	By wikiInsertLinkDialogModal = By.id("INSERT_LINK_DIALOG_MODAL");

	/** In Insert link recent tab dialog modal Site Drop down */
	By siteDropDownInsertLinkRecentTab = By.xpath("(//*[@class='selectDrop dropdown'])[1]");

	/** In Insert link search tab dialog modal Site Drop down */
	By siteDropDownInsertLinkSearchTab = By.xpath("(//*[@class='selectDrop dropdown'])[2]");

	/** In Insert link dialog modal Filter By Drop down */
	By wikiInsertLinkFilterByDropDown = By.id("insertLinkModal_recentTab_filterbyDropDown");

	/** In Insert link dialog modal under browse option link to drop down */
	By wikiInsertModalLinkToDropDown = By.id("insertLinkBrowseTabLinkToDropDownID");

	/** In Insert link dialog modal under browse option search text Bar */
	By searchTextBoxInInsertlinkModal = By.id("browseTabSiteSearchInputEle");

	/** In Add Attachment modal under search option search text Bar */
	By searchTextBoxInAddAttachmentmodal = By.id("insertlink_searchTab_searchContentInput");

	/** In Insert link dialog modal under browse option above search text Bar drop down */
	By insertModalSerchBarDropDownInBrowseTab = By.xpath("(//*[contains(@id,'insertLink')]//button[@class='btn dropdown-toggle'])[2]");

	/** In Insert link dialog modal under browse option above search text Bar drop down */
	By insertModalSerchBarDropDownInSearchTab = By.xpath("(//*[contains(@id,'insertLink')]//button[@class='btn dropdown-toggle'])[3]");

	/** In Insert link dialog modal under browse option above search text Bar drop down status expand button */
	By statusExpandButton = By.xpath("//*[@id='accordionSearch']//span");

	/** In Insert link dialog modal under External option URL text box */
	By externalURLTextBox = By.id("externalURL");

	/** In Insert link dialog modal under External option use short URL check box */
	By useShortUrlCheckBox = By.id("externalIsShort");

	/** In Insert link dialog modal open in drop down */
	By openInDropDown = By.id("insertLinkExternalTab_windowTarget");

	/** In Insert link dialog modal under upload option browse button */
	By browseButtonInInsertModal = By.id("insertLinkModal_uploadTab_browseDocumentButton");

	/** In Insert link dialog modal under search option site drop down */
	By siteDropDownInInsertModal = By.xpath("(//*[@class='btn dropdown-toggle errorElement'])[1]");

	/** Search tab in add attachment modal site drop down search tab in recent tab */
	By serchTabInAddAttachmentModalRecentTabDropDownSiteList = By.id("attachmentModal_dropDownSiteList_recentTab_siteSearchInput");

	/** Search tab in add attachment modal site drop down search tab in recent tab */
	By serchTabInAddAttachmentModalBrowseTabDropDownSiteList = By.id("attachmentModal_dropDownSiteList_browseTab_filesFolder_siteSearchInput");

	/** Search tab in add attachment modal site drop down search tab in recent tab */
	By serchTabInAddAttachmentModalSearchTabDropDownSiteList = By.id("insertLinkModal_dropDownSiteList_searchTab_siteSearchInput");

	By ckContentTextBoxGetText = By.xpath("//*[@id='addWikiCKContentID']/p");

	/** Browse button in Attachment modal in upload tab */
	By browseButtonInAttachmentModalUploadTab = By.id("attachmentModal_uploadTab_fileInputTypeID");

	By attachedFile = By.xpath("//*[contains(@id,'docid')]");

	By ckContentData = By.xpath("//*[@id='wikiContentID']/p");

	By editTagButton = By.id("editTagIconID");

	/** yellow strip with resume and discard tag after editing draft wiki from more action */
	By yelloStripResumeDiscard = By.id("resumeDiscardID");

	/** yellow strip discard tag after editing draft wiki from more action */
	By yelloStripDiscardTag = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space()='" + WikiLabels.WIKI_YELLOWSTRIP_DISCARD + "']");

	/** yellow strip resume tag after editing draft wiki from more action */
	By yelloStripResumeTag = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space()='" + WikiLabels.WIKI_YELLOWSTRIP_RESUME + "']");

	/** Search input box in left panel */
	By leftPanelSearchPagesInput = By.id("wikiSearchText");
	By grayLoader = By.xpath("(//*[@class='grayloader' and @style='display: block;'])[last()]");

	/** Ck contet text box in add wiki page */
	By ckContentTextBoxInAddWiki = By.id("addWikiCKContentID");

	/** Wiki Left Panel */
	By wikiLeftPanel = By.id("wiki_module_leftPanelPage");

	/** Wiki left Panel Search Section */
	By wikiSearchSection = By.id("wikiLeftPanelTreeSearchSection");

	/** Wiki clear search icon */
	By clearSearchIcon = By.xpath("(//*[@data-original-title='" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_CLEARSEARCHTERM + "']/i)[last()]");

	/** browse button in attachments tab in add wiki page */
	By browseButtonInAddWiki = By.id("addEditWikiAttachmentsckUploadFile");
	/** Attachments Tab in add wiki page */
	By attachmentsTabInAddWiki = By.xpath("//*[@id='wikiModuleMiddlePanelID']//*[text()='" + WikiLabels.WIKI_EDIT_ATTACHMENTS + "']");

	/** Settings Tab in add wiki page */
	By settingsTabInAddWiki = By.xpath("//*[@id='wikiModuleMiddlePanelID']//*[text()='" + WikiLabels.WIKI_EDIT_SETTINGS + "']");

	/** Content Tab in add wiki page */
	By contentTabInAddWiki = By.xpath("//*[@id='wikiModuleMiddlePanelID']//*[text()='" + WikiLabels.WIKI_EDIT_CONTENT + "']");

	/* Tag text box in add wiki page */
	By tagTextBoxInAddWiki = By.id("add_wiki_tagList_ID-tokenfield");

	By btnDone = By.xpath("(//span[contains(text(),'" + WikiLabels.WIKI_DONE + "')])[last()]");

	By deleteWikiModal = By.xpath("//*[@id='collaborateCustomMessageModal']//*[@class='modal-content']");

	/* Favourites tab rows */
	By favouritesTableRows = By.xpath("//*[@id='favouritesTableID']/tbody//tr");

	/** My drafts link in left menu **/
	By myDraftsLinkInLeftMenu = By.xpath("//*[@id='myDraftBtn']//*[normalize-space(text())='" + WikiLabels.WIKI_MYDRAFTS + "']");

	/** My drafts header label **/
	By myDraftsHeader = By.xpath(".//*[@id='wikiModuleMiddlePanelID']//div[normalize-space(text())='" + WikiLabels.WIKI_MYDRAFTS + "']");
	By autoSaveDraftModelTitle = By.id("customMessageModalTitle");

	By autoSaveMessage = By.id("collaborateCustomModalMessage");

	By autoSaveDiscardButton = By.id("collaborateMessageCancelButton");

	By saveButtonInAutoSaveModel = By.id("collaborateMessageOkButton");

	By moreOptionsLinkOfSearchedWiki = By.xpath(".//*[@id='wikiTitleID']//following::*[contains(@data-original-title,'" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "')][1]");

	By moreOptionsMenu = By.xpath(".//*[@id='attachmentsDropContainerID']//ul[@role='menu']");

	By wikiSearchResult = By.xpath("//*[@id='wikiSearchResult']");

	By autoSaveFooterNotification = By.xpath("//*[@id='addEditWikiAutoSaveMessageID' and contains(text(),'" + WikiLabels.WIKI_AUTOSAVED + "')]");

	By deleteDraftWikiModelTitle = By.xpath("//*[@id='customMessageModalTitle' and contains(text(),'" + WikiLabels.WIKI_DELETEDRAFTWIKIPAGE + "')]");

	By wikiDeleteModelDisappeared = By.xpath("//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none;')]");// By.xpath("//*[@id='collaborateCustomMessageModal' and @aria-hidden='true']");

	By wikiDraftSearchBox = By.id("myDraftSearch");

	By wikiMyDraftTable = By.id("myDraftsTableID");

	By deletedItemsButton = By.id("deletedItemsBtn");

	By checkAllDeletedItemsInput = By.xpath(".//*[@id='deletedItemsTableID']//*[@id='checkAllDeletedItems']");
	By deleteAllButton = By.id("deleteAllBtn");
	By wikiDeleteModalTitle = By.id("customMessageModalTitle");
	By wikiDeleteModalMessage = By.id("collaborateCustomModalMessage");

	By deleteModelTitle = By.xpath("//*[@class='modal-title' and contains(text(),'" + WikiLabels.WIKI_PERMANENTLYDELETEPAGES + "')]");

	By restoreWikiMainTree = By.xpath(".//*[@id='RESTORE_WIKI_MODAL_BODY']//*[@id='leftMainTree']");
	By restoreWikiModal = By.xpath(".//*[@id='RESTORE_WIKI_MODAL' and @class='modal fade']");
	By restoreWikiCancelButton = By.id("RESTORE_WIKI_MODAL_cancelButton");
	By restoreWikiSaveButton = By.id("RESTORE_WIKI_MODAL_saveButton");
	By restoreWikiModelBody = By.id("RESTORE_WIKI_MODAL_BODY");

	/** Choose Button in Edit wiki page settings tab */
	By chooseLocation = By.xpath("//*[normalize-space()='" + WikiLabels.WIKI_CHOOSELOCATION + "']");

	/** Select Location modal in edit wiki after click on choose location */
	By locationModal = By.id("wiki_module_changeLocation_modal");

	/** site drop down in Select Location modal in edit wiki after click on choose location */
	By siteDropDownInSelectLocationModal = By.id("wikiChangeLocationTargetSite");

	/** Save button in select location modal */
	By saveButtonInSelectLocationModal = By.id("wiki_module_changeLocation_modal_save");

	/** Auto generated modal */
	By wikiAutoGeneratedModal = By.xpath("//*[@class='modal-body overflowAuto']");

	/** Ok button in auto generated modal */
	By wikiAutoGeneratedMOdalOkButton = By.xpath("//*[normalize-space()='" + WikiLabels.WIKI_OK + "']");

	/** wiki edit form */
	By wikiEditForm = By.id("mainWikiForm");

	/** Wiki Edit text from Setting header Show and hide tag */
	By settingShowHideTagText = By.xpath("//*[@class='permissionShowHide']/span");

	/** organiser Expanded Button in seetings tab */
	By expandedButtonOfOrganiser = By.xpath("//span[contains(@class,'icon-chevron-down')]");

	/** organiser Collapsed Button in seetings tab */
	By collapseddButtonOfOrganiser = By.xpath("//span[contains(@class,'icon-chevron-right')]");

	/******** Share *******************/
	By modalContent = By.xpath("(//*[@class='modal-content'])[last()]");
	By share_Email = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_EMAIL + "']");
	By share_Link = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_LINK + "']");
	By share_Link_UseShortURLCheckbox = By.id("chkshortenURLID");
	By share_Link_URLInput = By.id("file_module_copyLink_txt");
	By share_Link_SelectLinkButton = By.xpath("//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_SELECTLINK + "']");
	By share_Message = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_MESSAGE + "']");
	By share_Email_recipientsInput = By.id("file_module_emailRecipients-tokenfield");
	By share_Email_subjectInput = By.id("file_module_email_subject");
	By share_Email_messageInput = By.id("file_module_email_message");
	By share_Message_recipientsInput = By.id("file_module_Site_Share_PrivateMessageUserList-tokenfield");
	By share_Message_messageInput = By.id("file_module_Site_Share_PrivateMessageEditor");
	By share_Cancel = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_CANCEL + "']");
	By share_Send = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_SEND + "']");
	By share_Post = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_POST + "']");
	By share_recipientHover = By.xpath("(//*[@class='typeahead_labels']//*)[last()]");
	By modalInvisible = By.xpath("(//*[@class='modal fade'])[last()]");
	By accessRestricted = By.xpath("//*[normalize-space(text())='" + WikiLabels.WIKI_ACCESSRESTRICTED + "']");
	By activeWiki = By.xpath("//*[@id='leftTreeAndPageLinks']//*[contains(@class,'fancytree-active')]");
	By share_contentModal = By.xpath("//*[contains(@class,'modalBodyScroll')]");
	By share_Microblog = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_MICROBLOG + "']");
	By share_Microblog_messageInput = By.id("file_module_Site_Share_MicroblogEditor");
	By share_Microblog_shareWithInput = By.id("file_module_Site_Share_Microblog_SiteList-tokenfield");
	By share_Microblog_selectedSite = By.xpath("(//*[contains(@class,'token-label')])[1]");
	By share_SentSuccessfully = By.xpath(".//*[normalize-space(text())='" + WikiLabels.WIKI_SHARE_LINKSUCCESSFULLYSENTTO + "']");
	By share_Close = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + WikiLabels.WIKI_CLOSE + "']");

	/** -------------- Export to PDF and print wiki xpaths ----------------------------- */

	By thisPageOnlyOption = By.xpath("//input[@type='radio' and @value='" + WikiLabels.WIKI_THISPAGEONLY + "']");
	By thisPageAndAllOfItsChildren = By.xpath("//input[@type='radio' and @value='" + WikiLabels.WIKI_THISPAGEANDALLOFITSCHILDREN + "']");
	By wikiLevelOption = By.id("levelOptions");
	By includeIndexCheckBox = By.id("includeIndex");
	By exportOrPrintButton = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_printOrExportButton");
	By cancelExportOrPrint = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL_cancelButton");
	By exportModelDisabled = By.xpath(".//*[@id='PRINT_AND_EXPORT_CONFIRM_MODAL' and contains(@style,'display: block;')]");
	By includeComment = By.id("includeCommentForPrintChildModal");

	By wikiTitleInPrintWindow = By.xpath(".//*[@class='edit-content margTop0 margBott0']");

	By printLogo = By.xpath("//*[@alt='" + WikiLabels.WIKI_HIGHQCOLLABORATE + "' and @class='logo']");

	/** ------------------ compare wiki xpaths ----------------------------- */
	By compareWikiModel = By.xpath(".//*[@id='WIKI_MODULE_COMPARE_VERSION' and contains(@style,'display: block')]");

	public String parentWindow;
	public String childWindow;

	By compareWikiDialogOpen = By.xpath(".//*[@id='WIKI_MODULE_COMPARE_VERSION' and contains(@style,'display: block')]");
	By compareWikiDialogClosed = By.xpath(".//*[@id='WIKI_MODULE_COMPARE_VERSION' and contains(@style,'display: none')]");

	By currentWikiVersion = By.xpath(".//*[@id='versionHistoryTab']//*[contains(text(),'" + WikiLabels.WIKI_CURRENT + "')]");
	By wikiInfoModalDisplay = By.xpath(".//*[@id='WIKI_INFO_MODAL' and contains(@style,'display: block')]");

	/** ---------------------------- -------- Custom Modal xpaths ------------------------------------------ */
	By customMessageModalOpened = By.xpath("//*[@id='collaborateCustomMessageModal' and not(contains(@style,'display: none;'))]");
	By customMessageModalClosed = By.xpath("//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none;')]");
	By customModalMessage = By.id("collaborateCustomModalMessage");

	/** ------------------------------------- Search xpaths ------------------------------------------ */
	By advanceSearchToken = By.id("file_advanceSearch_tokenFields");

	/** ------------------------------------- File Preview xpaths ------------------------------------------ */
	By filePreviewWindow = By.id("FILE_MODULE_ADEPTOL");
	By filePreviewCloseButton = By.xpath(".//*[@id='document_preview_modal_header_main']//*[@data-original-title='" + WikiLabels.WIKI_CLOSE + "']");
	By filePreviewWindowClosed = By.xpath(".//*[@id='FILE_MODULE_ADEPTOL' and contains(@style,'display: none;')]");
	By filePreviewWindowOpened = By.xpath(".//*[@id='FILE_MODULE_ADEPTOL' and contains(@style,'display: block;')]");
	By fileNameInfilePreviewWindow = By.xpath(".//*[@id='document_preview_modal_header_main']//*[@class='iconMeta']//span");

	By wikiInfoModalClosed = By.xpath(".//*[@id='WIKI_INFO_MODAL' and contains(@style,'display: none')]");
	By compareWikiDialogClose = By.xpath(".//*[@id='WIKI_MODULE_COMPARE_VERSION_BODY']//*[@data-original-title='" + WikiLabels.WIKI_CLOSE + "']");

	/** yellow strip with resume and discard tag after editing draft wiki from more action */
	By yellowStripResumeEditingLabel = By.id("resumeDiscardID");

	/** yellow strip resume tag after editing draft wiki from more action */
	By yellowStripResumeTag = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space()='" + WikiLabels.WIKI_YELLOWSTRIP_RESUME + "']");

	/** yellow strip discard tag after editing draft wiki from more action */
	By yellowStripDiscardTag = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space()='" + WikiLabels.WIKI_YELLOWSTRIP_DISCARD + "']");

	/** Comment box user name name in reply option */
	By userNameInCommentBox = By.xpath("//*[contains(@id,'CKContextMention')]");

	/** Wiki Like message after click on Like button */
	By wikiLikeMessage = By.xpath("//*[contains(@id,'contentLike_')]");

	/** Last reply done by user to get that data */
	By lastReply = By.xpath("(//*[contains(@class,'CKContextMention')]/..)[last()]");

	/** People who like this modal after click on like comment count */
	By peopleWhoLikeThisModal = By.id("CONTENT_LIKE_MODAL");

	/** Modal appears close button */
	By modalCloseButton = By.xpath("//*[contains(@id,'CLOSE_BTN')]");

	/** Remove attachment button when user edit the comment */
	By removeAttachmentButtonInEditComment = By.xpath("//*[contains(@class,'icon-remove progressClose tooltipShow pull-right')]");

	/** Modal appears close button */
	By attachmentModal = By.id("ATTACHMENT_MODAL");

	/** Attach button in Attachment button */
	By attachButtonInAttachmentModal = By.id("ATTACHMENT_MODAL_insertButton");

	By wikiLikeLink = By.xpath(".//*[@id='likeContent' and text()='" + WikiLabels.WIKI_LIKE + "']");

	By wikiTitleEditArea = By.id("wikiTitleInlineControlsID");

	By globalSuccessMessage = By.xpath("//*[@id='globalProcessMessageContainer' and @class='globalMsg hide']");
	By downloading = By.id("globalProcessMessageContainerMsgSpace");
	By ckEidtor = By.id("top_toolbar_div");

	By attachmentsTabOpen = By.xpath("//*[@id='addEditAttachmentContainerID' and not(contains(@class,'hide'))]");

	By leftTree = By.id("leftTreeAndPageLinks");

	public static final String GET_TIME_STAMP_QUERY = "SELECT GETUTCDATE()";
	public static final long ADD_TIME_DELAY = 3000L;

	DBManager dbManager = new DBManager();

	public WikiWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * To select the options availiable in wiki more action by passing more action name as a string
	 */

	@Override
	public void goToMoreActionOption(String action)
	{
		findClickableElement(wikiMoreActionButton).click();
		findClickableElement(By.xpath("//*[@class='pageActionIcon pull-right']//*[@class='dropdown-menu pull-right']//a[text()='" + action.trim() + "']")).click();

	}

	/**
	 * To Sort the wiki Elements According to the given sort order options
	 */

	@Override
	public void sortWiki(String sortingPage)
	{
		Select select = new Select((WebElement) wikiSortModalDropDown);
		select.selectByVisibleText(sortingPage);
		findClickableElement(wikiSortModalSaveButton).click();
	}

	/**
	 * To select the version check box from wiki more action info option versions tab
	 */
	@Override
	public void selectVersionCheckBox(String version)
	{
		findClickableElement(By.xpath("(//*[contains(text(),'" + version.trim() + "')]//preceding::input[@id='versionID'][1])[1]")).click();
	}

	/**
	 * To select the notification from Wiki more action edit option notifications drop down
	 */

	@Override
	public void selectWikiNotification(String notification)
	{
		findClickableElement(wikiEditNotificationDropDown).click();
		findClickableElement(By.xpath("//*[@id='wikiDropDownStatusID']//a[text()='" + notification.trim() + "']")).click();
	}

	/**
	 * To select the status from Wiki more action edit option status drop down
	 */

	@Override
	public void selectWikiStatus(String status)
	{
		findClickableElement(wikiEditStatusDropDown).click();
		findClickableElement(By.xpath("//*[@id='addEditWikiDropDownStatusID']//*[text()='" + status.trim() + "']")).click();
	}

	/**
	 * To select the level from more action export and print option
	 */

	@Override
	public void selectLevelInPRINT_AND_EXPORTModal(String level)
	{
		Select select = new Select((WebElement) printAndExportModalLevel);
		select.selectByVisibleText(level);
	}

	/**
	 * To go to Wiki Home page by clicking on home page Button From89i Wiki Left pannel
	 */

	@Override
	public void goToWikiHome()
	{
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		findClickableElement(wikiHomeButton).click();
	}

	/**
	 * To Click on Favourites button in wiki left pannel
	 */

	@Override
	public void goToFavourites()
	{
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		findClickableElement(wikiFavouritesLeftButton).click();
	}

	/**
	 * Rename the title at Wiki home page from rename button
	 */

	@Override
	public void renameWikiTitle(String newTitle)
	{
		findClickableElement(wikiTitle).click();
		findVisibleElement(wikiTitleTextBox).clear();
		findVisibleElement(wikiTitleTextBox).sendKeys(newTitle);
		findClickableElement(wikiTitleSaveButton).click();
	}

	/**
	 * To add a Tag from Add Tag Button in wiki home page
	 */

	@Override
	public void addTag(String tagValue)
	{
		if (isDisplayed(addTagButton))
		{
			findClickableElement(addTagButton).click();
		}
		else if (isDisplayed(editTagButton))
		{
			findClickableElement(editTagButton).click();
		}
		if (!verifyTagAdded(tagValue))
		{
			if (isDisplayed(disabledTagBox))
			{
				findVisibleElement(disabledTagBox).click();
			}
			findVisibleElement(tagTextBox, Speed.slow, 3).sendKeys(tagValue.trim() + Keys.ENTER + Keys.ESCAPE);
		}
		findClickableElement(tagSaveButton).click();
	}

	/**
	 * To click on wiki like tag in wiki home page
	 */

	@Override
	public void clickOnWikiLikeTag()
	{
		if (isDisplayed(wikiLikeLink))
		{
			findClickableElement(wikiLikeTag).click();
		}
	}

	/**
	 * To add a comment at home page in wiki module
	 */

	@Override
	public void addComment(String comment)
	{
		findClickableElement(wikiCommentTag).click();
		findVisibleElement(wikiCommentBox).sendKeys(comment);
		findClickableElement(wikiCommentPostButton).click();
	}

	/**
	 * To cancel a comment at wiki home page
	 */

	@Override
	public void clickOnCommentCancelButton()
	{
		findClickableElement(wikiCommentCancel).click();
	}

	/**
	 * TO click on link button under comment box
	 */
	@Override
	public void clickOnLinkButtonOfComment()
	{
		if (!isDisplayed(wikiCommentBoxFooter))
		{
			findClickableElement(wikiCommentTag).click();
		}
		findClickableElement(linkButton).click();
	}

	/**
	 * TO click on Attachment Button under comment box
	 */

	@Override
	public void clickOnAttachmentButtonOfComment()
	{
		if (!isDisplayed(wikiCommentBoxFooter))
		{
			findClickableElement(wikiCommentTag).click();
		}
		findClickableElement(attachButton).click();
	}

	/**
	 * TO click on link button under comment box
	 */

	@Override
	public void clickOnTextForamtingButtonOfComment()
	{
		if (!isDisplayed(wikiCommentBoxFooter))
		{
			findClickableElement(wikiCommentTag).click();
		}
		findClickableElement(textFormateButton).click();
	}

	/**
	 * TO click on options availiable under wiki comment box by passing user name and action
	 */

	@Override
	public void clickOnWikiCommentFooterLabels(String comment, String action)
	{
		findClickableElement(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]//following-sibling::*//a[normalize-space()='" + action.trim() + "']")).click();
	}

	/**
	 * TO click on options availiable under wiki comment box by passing user name, comment and action
	 */

	@Override
	public void clickOnWikiCommentFooterLabels(String userName, String action, String comment)
	{
		findClickableElement(By.xpath("(//*[contains(@id,'comment_renderedComment')]//*[text()='" + userName.trim() + "'])[1]/..//following-sibling :: div//p[contains(text(),'" + comment.trim() + "')]/../..//following-sibling :: div//a[translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '" + action.trim() + "']")).click();
	}

	/**
	 * To click on delete button in confirmation modal
	 */

	@Override
	public void deleteWikiComment()
	{
		if (!isDisplayed(deleteCommentConfirmationModal))
		{
			findClickableElement(deleteCommentConfirmationModalDeleteButton, Speed.slow);
		}
		findClickableElement(deleteCommentConfirmationModalDeleteButton).click();
	}

	/**
	 * Verify Wiki from left panel
	 *
	 * @param wikiName
	 *        Wiki name to select
	 */
	@Override
	public boolean verifyWikiFromLeftPanel(String wikiName)
	{
		boolean flag;
		findVisibleElement(wikiLeftPanel, Speed.slow);
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		By wiki = By.xpath("(//*[@id='wikiLeftPanelTreeSearchSection']//*[normalize-space(text())='" + wikiName.trim() + "'])[1]");
		if (isDisplayed(wiki))
		{
			flag = true;
		}
		else
		{
			flag = false;
		}
		return flag;
	}

	/**
	 * To click on Load more comments bar in wiki home page
	 */

	@Override
	public void clickOnLoadMoreComments()
	{
		findClickableElement(wikiLoadMoreComments).click();
	}

	/**
	 * To click on Load more comments bar in wiki home page
	 */

	@Override
	public void clickOnAddWiki()
	{
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		findClickableElement(addWiki).click();
	}

	/**
	 * To click on Load more comments bar in wiki home page
	 */

	/**
	 * To click on headers tab in insert link modal
	 */

	@Override
	public void clickOnHeadersInInsertLinkModal(String headerName)
	{
		findClickableElement(By.xpath("//*[@id='insertLink_navigationLinks']//a[text()='" + headerName.trim() + "']")).click();
	}

	/**
	 * To select the filter by option from filter by drop down in insert modal
	 */

	@Override
	public void selectFilterByInInsertModal(String filterType)
	{
		findClickableElement(wikiInsertLinkFilterByDropDown).click();
		findClickableElement(By.xpath("//*[@id='insertLinkModal_recentTab_filterbyDropDown']//a[text()='" + filterType.trim() + "']")).click();
	}

	/**
	 * To click on particular link set by the given title in insert modal
	 */

	@Override
	public void clickOnTitleOfInsertModal(String title) throws InterruptedException
	{
		// scrollToElement(By.xpath("//*[@id='activityList']//a[contains(text(),'" + title + "')]"));
		findClickableElement(By.xpath("//*[@id='activityList']//a[contains(text(),'" + title.trim() + "')]")).click();
	}

	/**
	 * To select drop down from link to drop down in insert modal drop down option
	 */
	@Override
	public void selectInsertModalLinkToDropDownOptions(String link)
	{
		Select select = new Select((WebElement) wikiInsertModalLinkToDropDown);
		select.selectByVisibleText(link);
	}

	/**
	 * To search text in search text box in insert link modal
	 */

	@Override
	public void searchTextInInsertModal(String text)
	{
		findPresentElement(searchTextBoxInInsertlinkModal).sendKeys(text);
	}

	/**
	 * To click on link in insert modal browse option
	 */
	@Override
	public void clickOnLinkInInsertModalBrowseOption(String link) throws InterruptedException
	{
		searchTextInInsertModal(link);
		findClickableElement(By.xpath("//*[@id='insertLinkBrowseSiteListDiv']//a[contains(text(),'" + link.trim() + "')]")).click();
		;
	}

	/**
	 * To click on Drop Down of serch bar in insert modal browse tab
	 */

	@Override
	public void clickOnSerchBarDropDownInInsertModalBrowseTab()
	{
		findClickableElement(insertModalSerchBarDropDownInBrowseTab).click();
	}

	/**
	 * To click on Drop Down of serch bar in insert modal serch tab
	 */

	@Override
	public void clickOnSerchBarDropDownInInsertModalSearchTab()
	{
		findClickableElement(insertModalSerchBarDropDownInSearchTab).click();
	}

	/**
	 * To click options under status expand button in serch bar drop down in insert modal browse option
	 */

	@Override
	public void clickOnStatusOptions(String option)
	{
		findClickableElement(statusExpandButton).click();
		findClickableElement(By.xpath("//*[@id='collapseListOne']//a[contains(text(),'" + option.trim() + "')]")).click();
	}

	/**
	 * Select filter from search bar drop down in insert modal browse option
	 */

	@Override
	public void selectSearchOptionsInInsertLinkModalBrowseTab(String filter, String opt)
	{
		clickOnSerchBarDropDownInInsertModalBrowseTab();
		if (filter.equalsIgnoreCase("Status"))
		{
			clickOnStatusOptions(opt);
		}
		findClickableElement(By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + filter.trim() + "'])[last()]")).click();
	}

	/**
	 * Select filter from search bar drop down in insert modal search option
	 */

	@Override
	public void selectSearchOptionsInInsertLinkModalSearchTab(String option)
	{
		clickOnSerchBarDropDownInInsertModalSearchTab();
		findClickableElement(By.xpath("//*[@class='input-group-btn open']//a[text()='" + option.trim() + "']")).click();
	}

	/**
	 * Send text in external URL text box in insert modal external option
	 */

	@Override
	public void sendTextInExternalURl(String URL)
	{
		findPresentElement(externalURLTextBox).sendKeys(url);
	}

	/**
	 * Click on use short URL check box in insert modal external option
	 */

	@Override
	public void clickOnUseShortUrlCheckBox()
	{
		findPresentElement(useShortUrlCheckBox).click();
	}

	/**
	 * Select text from open in drop down in insert modal external option
	 */

	@Override
	public void selectTextFromOpenInDropDown(String option)
	{
		Select select = new Select((WebElement) openInDropDown);
		select.selectByVisibleText(option);
	}

	/**
	 * Attach File with browse button in insert modal upload option
	 */

	@Override
	public void attachFileWithBrowseButtonInInsertModal(String path)
	{
		findPresentElement(browseButtonInInsertModal).sendKeys(path);
	}

	/**
	 * To select a site in insert modal recent tab from site drop down
	 */

	@Override
	public void selectSiteInInsertModalRecentTab(String siteName) throws InterruptedException
	{
		findClickableElement(siteDropDownInsertLinkRecentTab).click();
		// scrollToElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_recentTab_siteDropdownDiv']//a[text()='" + siteName + "']"));
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_recentTab_siteDropdownDiv']//a[text()='" + siteName.trim() + "']")).click();

	}

	/**
	 * To select a site in insert modal search tab from site drop down
	 */

	@Override
	public void selectSiteInInsertModalSearchTab(String siteName) throws InterruptedException
	{
		findClickableElement(siteDropDownInsertLinkSearchTab).click();
		// scrollToElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_searchTab_siteDropdownDiv']//a[text()='" + siteName + "']"));
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_searchTab_siteDropdownDiv']//a[text()='" + siteName.trim() + "']")).click();
	}

	/**
	 * To select tab in add attachment modal
	 */

	@Override
	public void selectTabInAddAttachmentModal(String tabName)
	{
		findPresentElement(By.xpath("//*[@id='attachmentModal_navigationLinks']//a[text()='" + tabName.trim() + "']")).click();
	}

	/**
	 * To send a text in search box in add attachment modal recent tab site drop down
	 */

	@Override
	public void sendTextInSerchTabInAddAttachmentModalRecentTabSiteDropDown(String siteName)
	{
		findPresentElement(serchTabInAddAttachmentModalRecentTabDropDownSiteList).sendKeys(siteName.trim());
	}

	/**
	 * To send a a text in search box in add attachment modal Browse tab site drop down
	 */

	@Override
	public void sendTextInSerchTabInAddAttachmentModalBrowseTabSiteDropDown(String siteName)
	{
		findPresentElement(serchTabInAddAttachmentModalBrowseTabDropDownSiteList).sendKeys(siteName.trim());
	}

	/**
	 * To send a a text in search box in add attachment modal Search tab site drop down
	 */

	/**
	 * To get text from ck content box
	 */
	@Override
	public String getTextFromCKContentTextBox()
	{
		String contentText = findVisibleElement(ckContentTextBoxGetText, Speed.slow).getText().trim();
		return (contentText);
	}

	@Override
	public void sendTextInSerchTabInAddAttachmentModalsearchTabSiteDropDown(String siteName)
	{
		findPresentElement(serchTabInAddAttachmentModalSearchTabDropDownSiteList).sendKeys(siteName.trim());
	}

	/**
	 * To select a site from site drop down in add attachment modal recent tab
	 */

	@Override
	public void selectSiteInAddAttachmentModalRecentTab(String siteName)
	{
		sendTextInSerchTabInAddAttachmentModalRecentTabSiteDropDown(siteName);
		findClickableElement(By.xpath("//*[@id='attachmentModal_dropDownSiteList_recentTab_siteList']//a[text()='" + siteName.trim() + "']")).click();
	}

	/**
	 * To select a site from site drop down in add attachment modal browse tab
	 */

	@Override
	public void selectSiteInAddAttachmentModalBrowseTab(String siteName)
	{
		sendTextInSerchTabInAddAttachmentModalBrowseTabSiteDropDown(siteName);
		findClickableElement(By.xpath("//*[@id='attachmentModal_dropDownSiteList_browseTab_filesFolder_siteDropdownDiv']//a[text()='" + siteName.trim() + "']")).click();
	}

	@Override
	public boolean activeWikiTitle()
	{
		return (findPresentElement(wikiTitleTextBox, Speed.slow).equals(driver.switchTo().activeElement()));
	}

	/**
	 * @author vivek.mishra
	 *         To click Edit title icon
	 */
	@Override
	public void clickOnEditTitleButton()
	{
		WebElement element = findPresentElement(wikiTitle);
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		findClickableElement(wikiTitle, Speed.slow).click();
	}

	/**
	 * To select a site from site drop down in add attachment modal search tab
	 */

	@Override
	public void selectSiteInAddAttachmentModalSearchTab(String siteName)
	{
		sendTextInSerchTabInAddAttachmentModalsearchTabSiteDropDown(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_searchTab_siteList']//a[text()='" + siteName.trim() + "']")).click();
	}

	/**
	 * To click on buttons comes in modal in Wiki
	 */

	@Override
	public void clcikOnModalsBottomButtons(String buttonName)
	{
		findClickableElement(By.xpath("(//*[contains(@class,'btn btn') and text()='" + buttonName.trim() + "'])[last()]")).click();
	}

	/**
	 * To Attach a file with browse button in Add attachment modal upload tab
	 */

	/**
	 * To Send a text in Serch text bar in Add Attachment modal serch tab
	 */

	@Override
	public void sendTextInSerchBarInAddAttachmentModalSearchTab(String text)
	{
		findPresentElement(searchTextBoxInAddAttachmentmodal).sendKeys(text);
	}

	/**
	 * Search Page/Wiki
	 *
	 * @param pageName
	 *        Wiki name/page name to search
	 */
	@Override
	public void searchPages(String pageName)
	{
		findVisibleElement(wikiLeftPanel, Speed.slow);
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		WebElement searchInput = findVisibleElement(leftPanelSearchPagesInput);
		searchInput.clear();
		searchInput.sendKeys(pageName.trim());
		// while (!isDisplayed(clearSearchIcon));
	}

	/**
	 * To verify the home level wiki
	 *
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyWikiHomeLevelWiki(String wikiName) throws InterruptedException
	{
		return (isDisplayed(By.xpath("(//*[@class='icon-home']/../following-sibling::ul/li/span//*[text()='" + wikiName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * To verify the Child level level wiki
	 */
	@Override
	public boolean verifyChildLevelWiki(String parentWiki, String childWiki)
	{
		By parentWikiExpandButton = By.xpath("//*[normalize-space(text())='" + parentWiki.trim() + "']/parent::*[contains(@class,'fancytree-has-children')][1]");
		if (isDisplayed(parentWikiExpandButton, Speed.slow))
		{
			WebElement parentWikiTree = findVisibleElement(parentWikiExpandButton, Speed.slow);
			parentWikiTree.click();
			findVisibleElement(By.xpath("//*[normalize-space(text())='" + parentWiki.trim() + "']/parent::*[contains(@class,'fancytree-expanded')][1]"), Speed.slow);
		}
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + parentWiki.trim() + "']//following::*[normalize-space(text())='" + childWiki.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * To verify the sibling level wiki
	 */
	@Override
	public boolean verifySiblingLevelWiki(String wiki, String siblingWiki) throws InterruptedException
	{
		return (isDisplayed(By.xpath("//*[normalize-space()='" + wiki.trim() + "']//parent::li//preceding::li//following::span[descendant-or-self::*/text()='" + siblingWiki.trim() + "' and @class='fancytree-title']"), Speed.slow));
	}

	/**
	 * yellow strip comes after editing discarded wiki to verify that
	 */
	@Override
	public boolean verifyResumeDiscardYelloStrip()
	{
		findVisibleElement(yelloStripResumeDiscard, Speed.slow);
		return (isDisplayed(yelloStripResumeDiscard));
	}

	/**
	 * yellow strip resume tag after editing discarded wiki
	 */
	@Override
	public void clickOnResumeYelloStrip()
	{
		findClickableElement(yelloStripResumeTag).click();
	}

	/**
	 * yellow strip discard tag after editing discarded wiki
	 */
	@Override
	public void clickOnDiscardYelloStrip()
	{
		findClickableElement(yelloStripDiscardTag).click();
	}

	/**
	 * Verify searched Page/Wiki
	 *
	 * @param pageName
	 *        Wiki name/page name to verify
	 */
	@Override
	public boolean verifySearchedPage(String pageName)
	{
		findVisibleElement(wikiLeftPanel, Speed.slow);
		searchPages(pageName);
		findVisibleElement(wikiSearchSection, Speed.slow);
		By searchResult = By.xpath("//*[@id='wikiSearchResult']//*[normalize-space(text())='" + pageName.trim() + "']");
		return isDisplayed(searchResult);
	}

	/**
	 * Select Wiki from left panel
	 *
	 * @param wikiName
	 *        Wiki name to select
	 */
	@Override
	public void selectWikiFromLeftPanel(String wikiName)
	{
		findVisibleElement(wikiLeftPanel, Speed.slow);
		if (isDisplayed(wikiLeftPannelShowArrow, Speed.slow))
		{
			findClickableElement(wikiLeftPannelShowArrow, Speed.slow).click();
		}
		By wiki = By.xpath("(//*[@id='wikiLeftPanelTreeSearchSection']//*[normalize-space(text())='" + wikiName.trim() + "'])[1]");
		if (isDisplayed(wiki, Speed.slow))
		{
			findVisibleElement(wiki, Speed.slow).click();
		}
		else
		{
			System.err.println(wikiName + " not found.");
		}
	}

	/**
	 * Set title for a Wiki
	 *
	 * @param title
	 *        title of the wiki which will be send in title box.
	 */
	@Override
	public void setTitle(String title)
	{
		WebElement titleInput = findVisibleElement(wikiTitleTextBox);
		titleInput.clear();
		titleInput.sendKeys(title);
	}

	/**
	 * Save Wiki is used to click on save button while creating the wiki.
	 */
	@Override
	public void saveWiki()
	{
		findVisibleElement(wikiEditTopSaveButton).click();
	}

	/**
	 * Clear searched item
	 */
	@Override
	public void clearSearchItem()
	{
		findVisibleElement(wikiLeftPanel, Speed.slow);
		if (isDisplayed(clearSearchIcon))
		{
			findVisibleElement(clearSearchIcon).click();
		}
	}

	/**
	 * Send content in content box in add wiki page
	 */
	@Override
	public void sendTextInCkContetTextBoxInAddWikiPage(String content)
	{
		verifyCKEditorInWikiEditMode();
		WebElement wikiContentArea = findVisibleElement(ckContentTextBoxInAddWiki, Speed.slow);
		wikiContentArea.click();
		wikiContentArea.clear();
		wikiContentArea.sendKeys(content.trim());
	}

	/**
	 * To click on attachments tab in add wiki page
	 */
	@Override
	public void clickOnAttachmentsTabInAddWiki()
	{
		findVisibleElement(attachmentsTabInAddWiki).click();
	}

	/**
	 * To click on Settings tab in add wiki page
	 */
	@Override
	public void clickOnSettingsTabInAddWiki()
	{
		findVisibleElement(settingsTabInAddWiki).click();
	}

	/**
	 * To click on Content tab in add wiki page
	 */
	@Override
	public void clickOnContentTabInAddWiki()
	{
		findVisibleElement(contentTabInAddWiki).click();
	}

	/**
	 * To Attach file in add wiki
	 */
	@Override
	public void attachFileInAddWiki(String file)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + file;
		clickOnAttachmentsTabInAddWiki();
		verifyAttachmentsTabOpen();
		findPresentElement(browseButtonInAddWiki, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * To Send text in tag text box in add wiki page
	 */
	@Override
	public void sendTextInTabInAddWiki(String text)
	{
		findPresentElement(tagTextBoxInAddWiki).sendKeys(text);
	}

	/**
	 * Click on add to favourite Star Icon
	 */
	@Override
	public void addToFavourite()
	{
		if (!isDisplayed(favouriteStarSelected))
		{
			findVisibleElement(favouriteButton, Speed.slow).click();
		}
		else
		{
			System.out.println("Favourite Star is already selected.");
		}
	}

	/**
	 * Click on remove from favourite Star Icon
	 */
	@Override
	public void removeFromFavourite()
	{
		if (isDisplayed(favouriteStarSelected))
		{
			findVisibleElement(favouriteStarSelected, Speed.slow).click();
		}
		else
		{
			System.out.println("No favourites star available.");
		}
	}

	/**
	 * To verify the title of wiki
	 */
	@Override
	public boolean verifyWikiTitle(String actTitle)
	{
		String currentTitle = findVisibleElement(wikiTitle, Speed.slow).getText();
		return (actTitle.trim().equals(currentTitle.trim()));
	}

	@Override
	public void goToFavourates()
	{
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		findClickableElement(wikiFavouratesLeftButton).click();
	}

	@Override
	public void attachOnBrowseButtonInAttachmentModalUploadTab(String file)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + file.trim();
		selectTabInAddAttachmentModal("Upload");
		findPresentElement(browseButtonInAttachmentModalUploadTab).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * To verify the file attached
	 */
	@Override
	public boolean verifyAttachedFile(String actFileName)
	{
		String currentFileName = findPresentElement(attachedFile).getAttribute("title").trim();
		return (currentFileName.equals(actFileName));
	}

	/**
	 * To verify the ck content
	 */
	@Override
	public boolean verifyCkContentData(String actContent)
	{
		String currentContent = findVisibleElement(ckContentData, Speed.slow).getText().trim();
		return (currentContent.equals(actContent.trim()));
	}

	/**
	 * To click on edit tag button in edit wiki page
	 */
	public void clickOnEditTagButton()
	{
		findVisibleElement(editTagButton).click();
	}

	/**
	 * To remove a tag in edit wiki page
	 */
	public void removeTag(String tagName)
	{
		clickOnEditTagButton();
		findClickableElement(By.xpath("//*[@class='token-label' and text()='" + tagName.trim() + "']//preceding-sibling::a")).click();
		findClickableElement(tagSaveButton).click();
	}

	/**
	 * To remove a tag in edit wiki page
	 */
	@Override
	public void editTag(String oldTagName, String newTagName)
	{
		clickOnEditTagButton();
		findClickableElement(By.xpath("//*[@class='token-label' and text()='" + oldTagName.trim() + "']//preceding-sibling::a")).click();
		enterTag(newTagName);
		findClickableElement(tagSaveButton).click();
	}

	/**
	 * @param oldFileName to remove existing file
	 * @param newFileName to add new file
	 *        To Edit the attachment in edit wiki page
	 */
	@Override
	public void editAttachmentInEditWiki(String oldFileName, String newFileName)
	{
		clickOnAttachmentsTabInAddWiki();
		verifyAttachmentsTabOpen();
		removeAttachmentInEditWiki(oldFileName);
		String path = TestBaseSetup.currentDir + "\\testData\\" + newFileName.trim();
		findPresentElement(browseButtonInAddWiki, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * TO remove the Attachment in Edit Wiki Page
	 */
	@Override
	public void removeAttachmentInEditWiki(String fileName)
	{
		findVisibleElement(By.xpath("//*[@class='TruncateTxt linkblack' and normalize-space()='" + fileName.trim() + "']//preceding-sibling::a"), Speed.slow).click();
	}

	/**
	 * To verify the wiki delete modal
	 */
	@Override
	public boolean verifyDeleteWikiModal()
	{
		findPresentElement(deleteWikiModal, Speed.slow);
		return (isDisplayed(deleteWikiModal));
	}

	/**
	 * To verify more action options in selected wiki page
	 * 
	 * @modified by vivek mishra
	 * @modified on 15/03/2018
	 */
	@Override
	public boolean verifyOptionInMoreActionInSelectedWiki(String option)
	{
		clickOnMoreActionInSelectedWiki();
		return (isDisplayed(By.xpath("(//*[contains(@class,'dropdown-menu pull-right')])[last()]//*[normalize-space(text())='" + getUserData(option.trim()) + "']"), Speed.slow));
	}

	/**
	 * To remove a tag in edit wiki page and add new tag
	 */
	@Override
	public void editTagInEditWikiPage(String oldTagName, String newTagName)
	{
		findClickableElement(By.xpath("//*[@class='token-label' and text()='" + oldTagName.toLowerCase().trim() + "']//preceding-sibling::a")).click();
		enterTag(newTagName);
	}

	/**
	 * Verifies if favourite star is selected or not.
	 *
	 * @return true
	 *         if successful.
	 */
	@Override
	public boolean verifyFavouriteIconIsSelected()
	{
		return isDisplayed(favouriteStarSelected);
	}

	/**
	 * To Send text in tag text box in add wiki page
	 */
	@Override
	public void enterTag(String text)
	{
		findPresentElement(tagTextBoxInAddWiki).sendKeys(text);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Verifies if favourited wiki is available in Favourites tab
	 *
	 * @return true
	 *         if successful.
	 */
	@Override
	public boolean verifyWikiInFavourites(String wikiName)
	{
		findVisibleElement(favouritesTableRows);
		if (isDisplayed(noFavourites))
		{
			System.err.println("No Favourited item present");
			return false;
		}
		else
		{
			By wiki = By.xpath("//*[@id='favouritesTableID']/tbody//*[normalize-space(text())='" + wikiName.trim() + "']/following::*[@data-original-title='" + WikiLabels.WIKI_DATAORIGINALTITLEATTRIBUTE_REMOVEFROMFAVOURITES + "'][1]");
			return isDisplayed(wiki);
		}
	}

	/**
	 * @modified by vivek.mishra
	 * @modified on 24/04/2018
	 */
	public boolean verifyMyDraftsLinkIsVisbile()
	{
		verifyLeftTree();
		return isDisplayed(myDraftsLinkInLeftMenu, Speed.slow);
	}

	/**
	 * @modified by vivek.mishra
	 *           Modified on 24/04/2018
	 */
	@Override
	public void clickOnMyDraftsInLeftMenu()
	{
		WebElement myDraftsLink = findVisibleElement(myDraftsLinkInLeftMenu, Speed.slow);
		myDraftsLink.click();
		findVisibleElement(myDraftsHeader, Speed.slow);
	}

	@Override
	public boolean verifyPresenceOfWikiInDrafts(String wiki) throws InterruptedException
	{
		findVisibleElement(myDraftsHeader, Speed.slow);
		return (isDisplayed(By.xpath("//a[normalize-space(text())='" + wiki.trim() + "']"), Speed.slow));
	}

	@Override
	public void cancelWiki() throws InterruptedException
	{
		// scrollToElement(wikiEditTopCancelButton);
		WebElement cancelButton = findVisibleElement(wikiEditTopCancelButton, Speed.slow);
		cancelButton.click();
	}

	@Override
	public boolean verifyAutoSaveDraftDialogueContent(String content)
	{
		findVisibleElement(autoSaveDraftModelTitle, Speed.slow);
		WebElement autoSaveDraftContent = findVisibleElement(autoSaveMessage, Speed.slow);

		return autoSaveDraftContent.getText().contains(content);
	}

	@Override
	public void discardAutoSaveDraftDialogue()
	{
		WebElement discardButton = findVisibleElement(autoSaveDiscardButton);
		discardButton.click();
	}

	@Override
	public void saveAutoSaveDraftDialogue()
	{
		WebElement discardButton = findVisibleElement(saveButtonInAutoSaveModel, Speed.slow);
		discardButton.click();
	}

	@Override
	public boolean verifyWikiStatus(String wikiTitle, String status)
	{
		By wikiPath = By.xpath("//*[normalize-space(text())='" + wikiTitle.trim() + "']");
		if (isDisplayed(wikiPath))
		{
			By wikiStatus = By.xpath("(//*[normalize-space(text())='" + wikiTitle.trim() + "']//following::td[1])[last()]");
			WebElement requiredWikiStatus = findVisibleElement(wikiStatus);
			return status.equalsIgnoreCase(requiredWikiStatus.getText().trim());
		}
		else
		{
			return false;
		}

	}

	/**
	 * To Select more action options in selected wiki page
	 */
	@Override
	public void selectOptionInMoreActionInSelectedWiki(String option)
	{
		clickOnMoreActionInSelectedWiki();
		findClickableElement(By.xpath("(//*[contains(@class,'dropdown-menu pull-right')])[last()]//*[normalize-space(text())='" + option.trim() + "']"), Speed.slow).click();
	}

	@Override
	public void clickOnMoreActionInSelectedWiki()
	{
		findVisibleElement(moreActionInSelectedWiki, Speed.slow).click();
	}

	@Override
	public void selectWikiFromMyDraft(String wikiTitle)
	{
		By wikiPath = By.xpath(".//*[@id='wikiMyDraftBody']//a[normalize-space(text())='" + wikiTitle.trim() + "']");
		WebElement requiredWiki = findVisibleElement(wikiPath);
		requiredWiki.click();
	}

	@Override
	public void selectMoreOptionsOperationsOfWiki(String option)
	{
		WebElement moreOptions = findVisibleElement(moreOptionsLinkOfSearchedWiki);
		moreOptions.click();

		findVisibleElement(moreOptionsMenu, Speed.slow);

		By shareMenuOption = By.xpath("(.//*[@id='attachmentsDropContainerID']//ul[@role='menu']//*[normalize-space(text())='" + option.trim() + "'][1])[1]");
		WebElement shareOption = findVisibleElement(shareMenuOption);
		shareOption.click();
	}

	@Override
	public void selectSearchedWiki(String wikiTitletext)
	{
		findVisibleElement(wikiSearchResult);
		By searchResult = By.xpath("//*[@id='wikiSearchResult']//*[normalize-space(text())='" + wikiTitletext.trim() + "']");
		WebElement searchedWiki = findVisibleElement(searchResult);
		searchedWiki.click();
		findVisibleElement(wikiTitle);
		findVisibleElement(clearSearchIcon, Speed.slow).click();
	}

	@Override
	public boolean verifyDiscardedAutoSavedDraft(String wikiTitle, String status)
	{
		By wikiStatus = By.xpath("(//*[normalize-space(text())='" + wikiTitle.trim() + "']//following::td[1])[last()]");
		if (isDisplayed(wikiStatus))
		{
			WebElement requiredWikiStatus = findVisibleElement(wikiStatus);
			return status.equalsIgnoreCase(requiredWikiStatus.getText().trim());
		}
		else
		{
			return false;
		}
	}

	@Override
	public void waitForAutoSavedNotification()
	{
		findPresentElement(autoSaveFooterNotification, Speed.slow);
	}

	@Override
	public void deleteWikiFromDraft(String wikiTitle)
	{
		By deleteButtonPath = By.xpath("//*[normalize-space(text())='" + wikiTitle.trim() + "']//following::a[contains(@data-original-title,'" + WikiLabels.WIKI_DELETE + "')][1]");
		WebElement deleteButton = findVisibleElement(deleteButtonPath);
		deleteButton.click();

	}

	@Override
	public void clickCancelInDeleteWikiFromDraft()
	{
		findVisibleElement(deleteDraftWikiModelTitle, Speed.slow);
		WebElement cancelButton = findVisibleElement(deleteCommentConfirmationModalCancelButton);
		cancelButton.click();
		findPresentElement(wikiDeleteModelDisappeared, Speed.slow);
	}

	@Override
	public void clickDeleteInDeleteWikiFromDraft()
	{
		findVisibleElement(deleteDraftWikiModelTitle, Speed.slow);
		WebElement deleteButton = findVisibleElement(deleteCommentConfirmationModalDeleteButton);
		deleteButton.click();
		findPresentElement(wikiDeleteModelDisappeared, Speed.slow);
	}

	@Override
	public boolean searchWikiInDraft(String wikiTitle)
	{
		WebElement draftSearchBox = findVisibleElement(wikiDraftSearchBox);
		draftSearchBox.clear();
		draftSearchBox.sendKeys(wikiTitle);

		findVisibleElement(wikiMyDraftTable, Speed.slow, 15);

		By wikiLink = By.xpath("//*[normalize-space(text())='" + wikiTitle.trim() + "'][1]");
		if (isDisplayed(wikiLink))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * To verify the tags added
	 */
	@Override
	public boolean verifyTagsAdded(String actTag)
	{
		String currentTag = findPresentElement(By.xpath("//*[@id='wikiTagID']//a[@title='" + actTag.toLowerCase().trim() + "']"), Speed.slow).getText();
		return (currentTag.trim().equals(actTag.toLowerCase().trim()));
	}

	@Override
	public Boolean verifyDeletedItemsLink()
	{
		verifyWikiHome();
		return isDisplayed(deletedItemsButton);
	}

	@Override
	public void clickCancelOnDeleteWikiModal()
	{
		WebElement wikiDeleteCancelEle = findClickableElement(wikiDeleteCancelButton);
		wikiDeleteCancelEle.click();
	}

	@Override
	public void clickOkOnDeleteWikiModal()
	{
		findVisibleElement(wikiDeleteModalTitle, Speed.slow);
		findVisibleElement(wikiDeleteOKButton, Speed.slow).click();

		findPresentElement(wikiDeleteModelDisappeared, Speed.slow);
	}

	@Override
	public void deleteWiki(String wikiName)
	{
		searchPages(wikiName);
		selectWikiFromLeftPanel(wikiName);

		findVisibleElement(wikiTitle);
		findVisibleElement(clearSearchIcon, Speed.slow).click();

		selectOptionInMoreActionInSelectedWiki("Delete");
		findVisibleElement(wikiDeleteModalTitle, Speed.slow);
		if (isDisplayed(wikiDeleteOKButton))
		{
			clickOkOnDeleteWikiModal();
		}
		// gotoWikiModule();
		// findPresentElement(By.xpath("//*[@class='wiki normalScreenMode delete']"));
	} // //*[@class='wiki normalScreenMode delete modal-open'] -- //*[@class='wiki normalScreenMode delete']

	@Override
	public void goToDeletedItems()
	{
		WebElement deletedItemsEle = findClickableElement(deletedItemsButton, Speed.slow);
		deletedItemsEle.click();
	}

	@Override
	public void deleteAllItemsFromDeletedItems()
	{
		WebElement deletedItemsEle = findClickableElement(deletedItemsButton);
		deletedItemsEle.click();

		WebElement checkAllDeletedItemsEle = findClickableElement(checkAllDeletedItemsInput);
		checkAllDeletedItemsEle.click();

		WebElement deleteAllEle = findClickableElement(deleteAllButton);
		deleteAllEle.click();

		WebElement wikiDeleteOKEle = findClickableElement(wikiDeleteOKButton);
		wikiDeleteOKEle.click();
	}

	@Override
	public void selectMoreOptionsFromDeletedItems(String wikiName, String option)
	{
		WebElement wikiEle = findClickableElement(By.xpath(".//*[text()='" + wikiName.trim() + "']//following::*[@class='icon icon-actions dropdown-toggle'][1]"), Speed.slow);
		wikiEle.click();

		WebElement shareMenuOptionEle = findClickableElement(By.xpath("//*[contains(@class,'dropdown inlineBlock open')]//a[normalize-space(text())='" + option.trim() + "']"), Speed.slow);
		shareMenuOptionEle.click();

		// findVisibleElement(deleteModelTitle, Speed.slow);
	}

	@Override
	public void deleteWikiFromDeletedItems(String wikiName)
	{
		selectMoreOptionsFromDeletedItems(wikiName, WikiLabels.WIKI_DELETE);
		if (isDisplayed(wikiDeleteOKButton))
		{
			clickOkOnDeleteWikiModal();
		}
	}

	@Override
	public String getDeleteWikiModalTitle()
	{
		WebElement wikiDeleteModalTitleEle = findVisibleElement(wikiDeleteModalTitle);
		return wikiDeleteModalTitleEle.getText().trim();
	}

	@Override
	public String getCustomModalMessage()
	{
		findVisibleElement(customMessageModalOpened, Speed.slow);
		WebElement wikiDeleteModalMessageEle = findVisibleElement(customModalMessage);
		return wikiDeleteModalMessageEle.getText().trim();
	}

	@Override
	public Boolean verifyWikiInDeletedItems(String wikiName)
	{
		return isDisplayed(By.xpath("//*[@title='" + wikiName.trim() + "']"));
	}

	@Override
	public Boolean verifyWikisInRestoreWikiMainTree(String[] wikiTitles)
	{
		for (int i = 0; i < wikiTitles.length; i++)
		{
			if (!isDisplayed(By.xpath(".//*[@id='RESTORE_WIKI_MODAL_BODY']//*[text()='" + wikiTitles[i].trim() + "']//preceding::*[@class='fancytree-checkbox'][1]")))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean verifyRestoreWikiModal()
	{
		return isDisplayed(restoreWikiModal, Speed.slow);
	}

	@Override
	public void selectWikiInRestoreWiki(String wikiName)
	{
		findVisibleElement(restoreWikiModelBody, Speed.slow);
		if (isDisplayed(By.xpath(".//*[@id='RESTORE_WIKI_MODAL_BODY']//*[text()='" + wikiName.trim() + "']//preceding::*[@class='fancytree-checkbox'][1]")))
		{
			findClickableElement(By.xpath(".//*[@id='RESTORE_WIKI_MODAL_BODY']//*[text()='" + wikiName.trim() + "']//preceding::*[@class='fancytree-checkbox'][1]")).click();
		}
	}

	@Override
	public void clickCancelOnRestoreWiki()
	{
		WebElement restoreWikiCancelEle = findClickableElement(restoreWikiCancelButton);
		restoreWikiCancelEle.click();
	}

	@Override
	public void clickSaveOnRestoreWiki()
	{
		WebElement restoreWikiSaveEle = findClickableElement(restoreWikiSaveButton);
		restoreWikiSaveEle.click();
	}

	@Override
	public Boolean veirfyRestoreWikiTree()
	{
		findVisibleElement(restoreWikiModelBody, Speed.slow);
		return isDisplayed(restoreWikiMainTree);
	}

	/**
	 * Go to Orphan Pages in Left Panel
	 */
	@Override
	public void goToOrphanPages()
	{
		if (isDisplayed(wikiLeftPannelShowArrow))
		{
			findClickableElement(wikiLeftPannelShowArrow).click();
		}
		findClickableElement(leftpanel_OrphanPagesLink).click();
		findVisibleElement(orphanPagesExpanded, Speed.slow);
	}

	/**
	 * Verify wiki present in orphan pages
	 */
	@Override
	public boolean verifyWikiInOrphanPages(String wikiName)
	{
		if (!isDisplayed(orphanPagesExpanded))
		{
			goToOrphanPages();
		}
		By orphanPageChild = By.xpath("(.//*[@id='leftOrphanTree']/ul//*[normalize-space(text())='" + wikiName.trim() + "'])[last()]");
		return isDisplayed(orphanPageChild);
	}

	/**
	 * To click on Settings tab in edit wiki page
	 */
	@Override
	public void clickOnSettingsTabInEditWiki()
	{
		findClickableElement(wikiEditSettingHeader).click();
	}

	/**
	 * To click on Choose loaction button in Settings tab in edit wiki page
	 */
	@Override
	public void clickOnChooseLocationButtonInSettingsTabInEditWiki()
	{
		findClickableElement(chooseLocation).click();
	}

	/**
	 * To verify the location modal in edit wiki page
	 */
	@Override
	public boolean verifyLocationModal()
	{
		findVisibleElement(locationModal, Speed.slow);
		return (isDisplayed(locationModal));
	}

	/**
	 * To select a site in choose location modal
	 */
	@Override
	public void selectSiteInSelectLocationModal(String siteName)
	{
		WebElement siteDropDown = findPresentElement(siteDropDownInSelectLocationModal);
		Select select = new Select(siteDropDown);
		select.selectByVisibleText(siteName);
	}

	@Override
	public String selectWikiInSelectLocationModal(String wikiName)
	{
		WebElement presentElement;
		By currentElement = By.xpath("(//*[@id='wikiChooseLocation_MainTreeID']//span[normalize-space()='" + wikiName.trim() + "'])[2]//preceding-sibling::span[@class='fancytree-checkbox']");
		presentElement = findVisibleElement(currentElement, Speed.slow);
		if (isDisplayed(currentElement, Speed.slow))
		{

			if (!presentElement.isSelected())
			{
				presentElement.click();
			}
			return (wikiName);
		}
		else
		{
			presentElement = findVisibleElement(By.xpath("//*[@id='wikiChooseLocation_MainTreeID']//span[Normalize-space()='" + WikiLabels.WIKI_HOME + "'])[2]//preceding-sibling::span[@class='fancytree-checkbox']"));
			if (!presentElement.isSelected())
			{
				presentElement.click();
			}
			return (WikiLabels.WIKI_HOME);
		}
	}

	/**
	 * To Click on save button in select location modal
	 */
	@Override
	public void clickOnSaveButtonInSelectLocationModal()
	{
		findClickableElement(saveButtonInSelectLocationModal).click();
		if (isDisplayed(wikiAutoGeneratedModal))
		{
			findClickableElement(wikiAutoGeneratedMOdalOkButton).click();
			findClickableElement(saveButtonInSelectLocationModal).click();
		}
	}

	@Override
	public boolean verifyWikiEditForm()
	{
		findPresentElement(wikiEditForm);
		return (isDisplayed(wikiEditForm));
	}

	public void clickOnShowTagSelectLocationModal()
	{
		if (isDisplayed(settingShowHideTag))
		{
			String text = findPresentElement(settingShowHideTagText).getText().trim();
			if (text.equals(WikiLabels.WIKI_SHOW))
			{
				findClickableElement(settingShowHideTagText).click();
			}
		}
	}

	/**
	 * Click on expand button in settings tab in edit wiki
	 */
	public void clickOnOrganiserExpandButton()
	{
		if (isDisplayed(collapseddButtonOfOrganiser))
		{
			findClickableElement(collapseddButtonOfOrganiser).click();
		}
	}

	@Override
	public ArrayList<Boolean> getWikiPermissionStatus(String userName)
	{
		clickOnShowTagSelectLocationModal();
		clickOnOrganiserExpandButton();
		ArrayList<Boolean> value = new ArrayList<>();
		for (int i = 2; i < 4; i++)
		{
			boolean state = isDisplayed((By.xpath("(//*[contains(@id,'userrow')]//*[contains(.,'" + getUserData(userName.trim()) + "')])[1]/../td[" + i + "]//*[@checked and @disabled]")));
			value.add(state);
		}

		for (int i = 0; i < value.size(); i++)
		{
			System.out.println(value.get(i));
		}
		return value;
	}

	/**
	 * Share content via e-mail(Share->Email).
	 * 
	 * @param recipientMail
	 *        e-mail of the recipient.
	 * @param subject
	 *        mail subject.
	 * @param message
	 *        to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaEmail(String recipientMail, String subject, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreActionInSelectedWiki(WikiLabels.WIKI_SHARE);
		}
		findVisibleElement(share_contentModal);
		gotoEmailTab();
		WebElement recipientInput = findVisibleElement(share_Email_recipientsInput);
		WebElement subjectInput = findVisibleElement(share_Email_subjectInput);
		WebElement messageInput = findVisibleElement(share_Email_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		findVisibleElement(share_Send, Speed.slow);
		selectOptionFromAutoSuggest(recipientMail);
		subjectInput.clear();
		subjectInput.sendKeys(subject);
		if (!message.isEmpty() || message != null)
		{
			messageInput.clear();
			messageInput.sendKeys(message);
		}
	}

	/**
	 * Share content via message(Share->Message).
	 * 
	 * @param recipientMail
	 *        e-mail of the recipient.
	 * @param message
	 *        to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaMessage(String recipientMail, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreActionInSelectedWiki(WikiLabels.WIKI_SHARE);
		}
		findVisibleElement(share_contentModal);
		gotoMessageTab();
		WebElement recipientInput = findVisibleElement(share_Message_recipientsInput);
		WebElement messageInput = findVisibleElement(share_Message_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		findVisibleElement(share_Send, Speed.slow, 5);
		selectOptionFromAutoSuggest(recipientMail);
		messageInput.clear();
		messageInput.sendKeys(message);
	}

	/**
	 * Copy share URL(Share->Link)
	 *
	 * @param shortUrl
	 *        true - check Use short URL checkbox
	 *        false - uncheck Use short URL checkbox
	 * @author dheeraj.rajput
	 */
	@Override
	public void copyShareLink(boolean shortUrl)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreActionInSelectedWiki(WikiLabels.WIKI_SHARE);
		}
		findVisibleElement(share_Link, Speed.slow).click();
		WebElement shortURL = findVisibleElement(share_Link_UseShortURLCheckbox);
		boolean selected = shortURL.isSelected();
		if (selected != shortUrl)
		{
			shortURL.click();
		}
		findVisibleElement(share_Link_SelectLinkButton).click();
		findVisibleElement(share_Link_URLInput).sendKeys(Keys.chord(Keys.CONTROL, "c"));
	}

	/**
	 * Open copied URL (Always use it after copyShareLink(boolean shortUrl))
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void openCopiedURL() throws UnsupportedFlavorException, IOException
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String url = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.println("URL: " + url);
		driver.get(url);
	}

	/**
	 * Verify active Wiki
	 * 
	 * @param wikiTitle
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyActiveWiki(String wikiTitle)
	{
		if (isDisplayed(accessRestricted))
		{
			System.out.println("Access is restricted.");
			return false;
		}
		else
		{
			WebElement activewiki = findVisibleElement(activeWiki);
			if (activewiki.getText().trim().equals(wikiTitle.trim()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Click on Send button in Share modal
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickSendInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Send).click();
			findPresentElement(modalContent, Speed.slow, 5);
			if (isDisplayed(share_SentSuccessfully, Speed.slow))
			{
				findVisibleElement(share_SentSuccessfully);
				findVisibleElement(share_Close).click();
			}
			findPresentElement(modalInvisible, Speed.slow, 5);
		}
		else
		{
			System.err.println("Share modal is not open");
		}
	}

	/**
	 * Click on Cancel button in Share modal
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCancelInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Cancel).click();
			findPresentElement(modalInvisible, Speed.slow);
		}
		else
		{
			System.err.println("Share modal is not open");
		}
	}

	/**
	 * To verify the add tag button is displayed or not
	 */
	@Override
	public boolean verifyAddTagButton()
	{
		if (isDisplayed(addTagButton))
		{
			return true;
		}
		else if (isDisplayed(editTagButton))
		{
			return true;
		}
		return false;
	}

	/**
	 * To get the count of number of comments
	 */
	@Override
	public int getCommentCount()
	{
		return (Integer.parseInt(findPresentElement(CommentCount).getText().trim()));
	}

	/**
	 * @param comment
	 * @param option
	 * @return the item is displayed or not
	 *         verify options available in comment grid
	 */
	@Override
	public boolean verifyCommentOptionsInCommentGrid(String comment, String option)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]//following::*[normalize-space()='" + option.trim() + "'][1]")));

	}

	@Override
	public boolean verifyTagAdded(String tag)
	{
		return (isDisplayed(By.xpath("//*[@class='token-label' and text()='" + tag.toLowerCase().trim() + "']"), Speed.slow));
	}

	/**
	 * Verify default subject in Share Modal present or not
	 * 
	 * @return true
	 *         if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultSubjectIsPresent()
	{
		return verifyInputTextIsPresent(share_Email_subjectInput);
	}

	/**
	 * Click on Post button in Share modal
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickPostInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Post).click();
			findPresentElement(modalInvisible, Speed.slow, 5);
		}
		else
		{
			System.err.println("Share modal is not open");
		}
	}

	/**
	 * Share content via microblog(Share->Microblog).
	 * 
	 * @param message
	 *        to be sent.
	 * @param siteName
	 *        to share blog with
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaMicroblog(String message, String siteName)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreActionInSelectedWiki(WikiLabels.WIKI_SHARE);
		}
		// findVisibleElement(share_contentModal);
		if (!isDisplayed(share_Microblog))
		{
			System.err.println("Microblog is disabled : enable it from Site Admin -> Advanced first.");
		}
		else
		{
			gotoMicroblogTab();
			WebElement messageInput = findVisibleElement(share_Microblog_messageInput);
			WebElement shareWithInput = findVisibleElement(share_Microblog_shareWithInput);
			messageInput.clear();
			messageInput.sendKeys(message);
			shareWithInput.clear();
			shareWithInput.sendKeys(siteName);
			findVisibleElement(share_Cancel, Speed.slow);
			selectOptionFromAutoSuggest(siteName);
		}
	}

	/**
	 * Click Email tab in opened window
	 */
	@Override
	public void gotoEmailTab()
	{
		findVisibleElement(modalContent);
		findVisibleElement(share_Email, Speed.slow).click();
	}

	/**
	 * Click Link tab in opened window
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoLinkTab()
	{
		findVisibleElement(modalContent);
		findVisibleElement(share_Link, Speed.slow).click();
	}

	/**
	 * Click Message tab in opened window
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoMessageTab()
	{
		findVisibleElement(modalContent);
		findVisibleElement(share_Message, Speed.slow).click();
	}

	/**
	 * Click Microblog tab in opened window
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoMicroblogTab()
	{
		try
		{
			findVisibleElement(modalContent);
			findVisibleElement(share_Microblog, Speed.slow).click();
		}
		catch (Exception e)
		{
			System.err.println("Microblog is disabled : enable it from Site Admin -> Advanced first.");
		}
	}

	/**
	 * Verify input element has default text or not
	 * 
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */

	@Override
	public boolean verifyInputTextIsPresent(By elementXpath)
	{
		WebElement elem = findVisibleElement(elementXpath, Speed.slow);
		String elemText = "";
		try
		{
			elemText = ((JavascriptExecutor) driver).executeScript("return arguments[0].value", elem).toString();
		}
		catch (Exception e)
		{
			elemText = elem.getText();
		}
		System.out.println("Input text: " + elemText);
		if (elemText.isEmpty() || elemText.equals(null))
		{
			return false;
		}
		return true;
	}

	/**
	 * Select option from auto suggest list
	 * 
	 * @param option
	 *        to select
	 * @author dheeraj.rajput
	 */
	// @Override
	// public void selectOptionFromAutoSuggest(String option)
	// {
	// if (isDisplayed(share_recipientHover))
	// {
	// WebElement recipientHover = findVisibleElement(share_recipientHover);
	// if (recipientHover.getText().trim().equals(option.trim().toLowerCase()))
	// {
	// recipientHover.click();
	// }
	// }
	// else
	// {
	// System.err.println(option + " not found.");
	// }
	// }

	/**
	 * Verify default message in Share Modal present or not
	 * 
	 * @return true
	 *         if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultMessageIsPresent()
	{
		findVisibleElement(shareModal, Speed.slow);
		gotoMicroblogTab();
		return verifyInputTextIsPresent(share_Microblog_messageInput);
	}

	/**
	 * Verify default microblog share with site name matches with expected site name
	 * 
	 * @param siteName
	 *        to verify.
	 * @return true
	 *         if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultMicroblogSite(String siteName)
	{
		WebElement shareWith = findVisibleElement(share_Microblog_selectedSite);
		return shareWith.getText().trim().equals(siteName.trim());
	}

	@Override
	public String getMailContent(String mailQuery)
	{
		System.out.println("Get mail Content Query : " + mailQuery);
		String mailBody = "";
		try
		{
			List<Map<String, Object>> rsMap = dbManager.getResultSet(mailQuery);
			if (!rsMap.isEmpty())
			{
				int size = rsMap.size() - 1;
				mailBody = (String) rsMap.get(size).get("htmlbody");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return mailBody;
	}

	@Override
	public void createHtmlFile(String fileName, String fileContent)
	{
		try
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileName.trim() + "").getCanonicalPath().trim();
			File file = new File(path);
			// If file does not exist, create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileContent);
			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void getLocalHtmlPage(String fileName)
	{
		try
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileName.trim() + "").getCanonicalPath().trim();
			// findPresentElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL + "t"));
			((JavascriptExecutor) driver).executeScript("window.open()");
			Set<String> highQCollaborateWindows = driver.getWindowHandles();
			parentWindow = (String) highQCollaborateWindows.toArray()[0];
			childWindow = (String) highQCollaborateWindows.toArray()[1];
			driver.switchTo().window(childWindow);
			driver.get(path);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyContent(String mailto, String content)
	{
		findVisibleElement(By.xpath("//body"), Speed.slow);
		By contentBody = By.xpath(".//body[contains(.,'" + getUserData(mailto) + "') and contains(.,'" + content.trim() + "')]");
		return isDisplayed(contentBody);
	}

	@Override
	public void closeCurrentTab()
	{
		// ((JavascriptExecutor)driver).executeScript("window.close()");
		driver.switchTo().window(childWindow).close();
		driver.switchTo().window(parentWindow);
	}

	/**
	 * Gets the start date time stamp.
	 * 
	 * @return the start date time stamp
	 */
	@Override
	public Timestamp getStartDateTimeStamp()
	{
		Timestamp timeStamp = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try
		{
			Object value = getResult(GET_TIME_STAMP_QUERY);

			if (value != null)
			{
				timeStamp = new java.sql.Timestamp(sdf.parse(value.toString()).getTime());
			}
			Thread.sleep(1000);
		}
		catch (Exception e)
		{

		}

		return timeStamp;
	}

	/**
	 * Gets the end date time stamp.
	 *
	 * @return the end date time stamp
	 * @throws Exception
	 *         the exception
	 */
	@Override
	public Timestamp getEndDateTimeStamp()
	{
		return new Timestamp(getStartDateTimeStamp().getTime() + ADD_TIME_DELAY);
	}

	/**
	 * @param sql
	 *        Query
	 * @param all
	 *        Dynamic parameters which are expected in Query
	 * @return value From First column and First Row
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public synchronized Object getResult(String sql, Object... params)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try
		{
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
			{
				pstmt.setObject(i + 1, params[i]);
			}
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				return rs.getObject(1);
			}
			else
			{
				return null;
			}
		}
		catch (SQLException se)
		{
			System.err.println("SQLException accured in getResult() method:" + se);

		}
		catch (Exception e)
		{
			// logger.error(null, e);

		}
		finally
		{
			DBConnection.closeDBconnection(con);
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// logger.error(null, e);
				}
			}
		}
		return null;
	}

	@Override
	public boolean verifyDeleteDraftMessage(String deleteMessage)
	{
		boolean result = false;
		WebElement messageBody = findVisibleElement(autoSaveMessage, Speed.slow);
		String messageText = messageBody.getText();
		if (messageText.equalsIgnoreCase(deleteMessage))
		{
			result = true;
		}

		return result;
	}

	@Override
	public void selectThisPageAndAllOfItsChildren()
	{
		WebElement thisPageOnlyRadioButton = findVisibleElement(thisPageOnlyOption);
		thisPageOnlyRadioButton.click();
	}

	@Override
	public void selectWikiLevel(String level)
	{
		WebElement levelDropDownList = findVisibleElement(wikiLevelOption);
		Select levelDropDown = new Select(levelDropDownList);
		levelDropDown.selectByVisibleText(level.trim());
	}

	@Override
	public void clickOnExportOrPrint()
	{
		if (isDisplayed(printAndExportModal, Speed.slow))
		{
			WebElement exportOrPrintButtonElement = findVisibleElement(exportOrPrintButton);
			exportOrPrintButtonElement.click();
		}
		while (isDisplayed(downloading, Speed.slow))
		{
			;
		}
	}

	@Override
	public void clickExportButton()
	{
		WebElement exportButtonElement = findVisibleElement(exportOrPrintButton);
		exportButtonElement.click();
		findPresentElement(exportModelDisabled);
	}

	@Override
	public void clickCancelButton()
	{
		WebElement exportButtonElement = findVisibleElement(cancelExportOrPrint);
		exportButtonElement.click();
		findPresentElement(exportModelDisabled);
	}

	@Override
	public void selectThisPageOnly()
	{
		if (isDisplayed(printAndExportModal, Speed.slow))
		{
			WebElement thisPageOnlyRadioButton = findVisibleElement(thisPageOnlyOption);
			thisPageOnlyRadioButton.click();
		}
	}

	@Override
	public void selectIncludeComment(boolean expectedState) throws InterruptedException
	{
		setSelection(includeComment, expectedState);
	}

	@Override
	public void switchToPrintWindow()
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
	}

	@Override
	public boolean verifyLogoInPrintWindow()
	{
		boolean result = false;
		findVisibleElement(printLogo, Speed.slow);
		if (isDisplayed(printLogo))
		{
			result = true;
		}
		return result;
	}

	@Override
	public void selectIncludeIndexOption(boolean expectedState) throws InterruptedException
	{
		if (isDisplayed(printAndExportModal, Speed.slow))
		{
			setSelection(includeIndexCheckBox, expectedState);
		}
	}

	@Override
	public boolean verifyWikiTitleInPrintWindow(String wikiName)
	{
		boolean result = false;
		if (findVisibleElement(wikiTitleInPrintWindow, Speed.slow).getText().trim().equalsIgnoreCase(wikiName))
		{
			result = true;
		}
		return result;
	}

	@Override
	public boolean verifyCommentInPrintWindow(String commentText)
	{
		boolean result = false;
		By commentXpath = By.xpath("//*[contains(text(),'" + commentText.trim() + "')][1]");
		findVisibleElement(commentXpath, Speed.slow);
		if (isDisplayed(commentXpath))
		{
			result = true;
		}
		return result;
	}

	@Override
	public void closePrintWindow()
	{
		driver.switchTo().window(childWindow).close();
		driver.switchTo().window(parentWindow);
	}

	@Override
	public void compareWikiVersions(String version1, String version2)
	{
		if (version1.split("\\.")[1].equalsIgnoreCase(getCurrentVersion()))
		{
			version1 = version1.toLowerCase().trim() + " (Current)";
		}
		if (version2.split("\\.")[1].equalsIgnoreCase(getCurrentVersion()))
		{
			version2 = version2.toLowerCase().trim() + " (Current)";
		}
		WebElement wikiVersion1 = findClickableElement(By.xpath(".//*[@id='versionHistoryTab']//*[text()='" + version1 + "']//preceding::input[1]"));
		wikiVersion1.click();

		WebElement wikiVersion2 = findClickableElement(By.xpath(".//*[@id='versionHistoryTab']//*[text()='" + version2 + "']//preceding::input[1]"));
		wikiVersion2.click();

		clickCompareOnInfoModal();
		findVisibleElement(compareWikiDialogOpen, Speed.slow);
	}

	@Override
	public boolean verifyCompareWikiDialogOpened()
	{
		findVisibleElement(compareWikiDialogOpen, Speed.slow);
		return isDisplayed(compareWikiDialogOpen, Speed.slow);
	}

	@Override
	public boolean verifyCompareWikiDialogClosed()
	{
		findPresentElement(compareWikiDialogClosed, Speed.slow);
		return !isDisplayed(compareWikiDialogClosed);
	}

	@Override
	public void clickCompareOnInfoModal()
	{
		WebElement wikiMoreActionInfoModalCompareEle = findClickableElement(wikiMoreActionInfoModalCompareButton);
		wikiMoreActionInfoModalCompareEle.click();
	}

	@Override
	public void clickOnVersionsTab()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalVersionsEle = findClickableElement(wikiMoreActionInfoModalVersions);
		wikiMoreActionInfoModalVersionsEle.click();
	}

	@Override
	public boolean verifyVersionInCompareWikiModel(String version)
	{
		findVisibleElement(compareWikiModel, Speed.slow);
		By versionInRightPanel = By.xpath(".//*[@id='compare_modal_right_main']//*[text()='" + version.trim() + "']");
		return isDisplayed(versionInRightPanel);
	}

	@Override
	public boolean verifyVersionMataDataInCompareWikiModel(String version, String mataData)
	{
		if (mataData.contains("."))
		{
			mataData = getUserData(mataData);
		}

		findVisibleElement(compareWikiModel, Speed.slow);
		By mataDataInRightPanel = By.xpath(".//*[@id='compare_modal_right_main']//*[text()='" + version.trim() + "']//following::*[normalize-space(text())='" + mataData.trim() + "'][1]");
		return isDisplayed(mataDataInRightPanel);
	}

	@Override
	public boolean verifyOverviewTabOnInfo()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		return isDisplayed(wikiMoreActionInfoModalOverview, Speed.slow);
	}

	@Override
	public void clickOnOverviewTab()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalOverviewEle = findClickableElement(wikiMoreActionInfoModalOverview);
		wikiMoreActionInfoModalOverviewEle.click();
	}

	@Override
	public boolean verifyVersionsTabOnInfo()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		return isDisplayed(wikiMoreActionInfoModalVersions);
	}

	@Override
	public boolean verifyAttachmentsTabOnInfo()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		return isDisplayed(wikiMoreActionInfoModalAttachments);
	}

	@Override
	public String getWikiLinkInfo()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		return findClickableElement(wikiMoreActionInfoModalLinkTextBox).getAttribute("value").trim();
	}

	@Override
	public void selectShortUrlCheckBox()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalUseShortLinkEle = findClickableElement(wikiMoreActionInfoModalUseShortLinkCheckBox);
		if (!wikiMoreActionInfoModalUseShortLinkEle.isSelected())
		{
			wikiMoreActionInfoModalUseShortLinkEle.click();
		}
	}

	@Override
	public int getOptionValueFromInfoModal(String option)
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		clickOnOverviewTab();
		WebElement infoModalOptionEle = findVisibleElement(By.xpath(".//*[@id='WIKI_INFO_MODAL_BODY']//label[text()='" + option.trim() + "']//following::span[1]"));
		return Integer.parseInt(infoModalOptionEle.getText().trim());
	}

	@Override
	public void clickTagInInfoModal(String tag)
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		findClickableElement(By.xpath(".//*[@id='WIKI_INFO_MODAL_BODY']//label[text()='Tags']//following::*[text()='" + tag.trim() + "']")).click();
		findVisibleElement(advanceSearchToken, Speed.slow, 15);
	}

	@Override
	public boolean verifyTagSearchDisplay()
	{
		findVisibleElement(advanceSearchToken, Speed.slow);
		return isDisplayed(advanceSearchToken);
	}

	@Override
	public void clickCancelOnInfoModal()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalCloseEle = findClickableElement(wikiMoreActionInfoModalCloseButton);
		wikiMoreActionInfoModalCloseEle.click();
		findPresentElement(wikiInfoModalClosed, Speed.slow);
	}

	@Override
	public boolean verifyInfoModalClosed()
	{
		return !isDisplayed(wikiInfoModalDisplay, Speed.slow);
	}

	@Override
	public void clickExportOnInfoModal()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalExportEle = findClickableElement(wikiMoreActionInfoModalExportButton);
		wikiMoreActionInfoModalExportEle.click();
	}

	@Override
	public void clickPrintOnInfoModel()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalPrintEle = findClickableElement(wikiMoreActionInfoModalPrintButton);
		wikiMoreActionInfoModalPrintEle.click();
	}

	@Override
	public String getCurrentVersion()
	{
		WebElement currentWikiVersionEle = findVisibleElement(currentWikiVersion);
		String[] version = currentWikiVersionEle.getText().trim().split("\\(");
		return version[0].split("\\.")[1].trim();
	}

	@Override

	public void restoreWikiVersion(String version)
	{
		if (isDisplayed(By.xpath(".//*[@id='versionHistoryTab']//*[text()='" + version + "']//following::*[text()='Restore'][1]"), Speed.slow))
		{
			WebElement restoreWikiEle = findClickableElement(By.xpath(".//*[@id='versionHistoryTab']//*[text()='" + version + "']//following::*[text()='Restore'][1]"));
			restoreWikiEle.click();
		}
		else
		{
			System.out.println("Invalid version");
		}
		findPresentElement(wikiInfoModalClosed, Speed.slow);
	}

	@Override
	public void clickOKOnCustomMessageModal()
	{
		WebElement customMessageModalOkEle = findClickableElement(wikiDeleteOKButton, Speed.slow);
		customMessageModalOkEle.click();
		findPresentElement(customMessageModalClosed, Speed.slow);
	}

	@Override
	public void clickCloseOnCompareDialog()
	{
		WebElement compareWikiDialogCloseEle = findClickableElement(compareWikiDialogClose);
		compareWikiDialogCloseEle.click();

		findPresentElement(compareWikiDialogClosed, Speed.slow);
	}

	@Override
	public void clickOnAttachmentsTab()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement wikiMoreActionInfoModalAttachmentsEle = findClickableElement(wikiMoreActionInfoModalAttachments);
		wikiMoreActionInfoModalAttachmentsEle.click();
	}

	@Override
	public void openAttachmentFromWikiInfo(String fileName) throws InterruptedException
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		WebElement fileEle = findPresentElement(By.xpath(".//*[@id='attachmentTab']//*[text()='" + fileName.trim() + "']"), Speed.slow);
		fileEle.click();
	}

	@Override
	public boolean verifyFileInFilePreviewWindow(String fileName)
	{
		findVisibleElement(filePreviewWindowOpened, Speed.slow);
		return findVisibleElement(fileNameInfilePreviewWindow).getText().trim().equalsIgnoreCase(fileName);
	}

	@Override
	public void clickCancelOnFileOPreviewWindow()
	{
		findVisibleElement(filePreviewWindowOpened);
		WebElement filePreviewCloseEle = findClickableElement(filePreviewCloseButton);
		filePreviewCloseEle.click();
		findPresentElement(filePreviewWindowClosed, Speed.slow);
	}

	@Override
	public boolean verifyFilePreviewWindowClosed()
	{
		findPresentElement(filePreviewWindowClosed, Speed.slow);
		return !isDisplayed(filePreviewWindowClosed);
	}

	@Override
	public String getSelectedWikiLink()
	{
		findVisibleElement(wikiInfoModalDisplay, Speed.slow);
		if (!findPresentElement(wikiMoreActionInfoModalLinkTextBox, Speed.slow).equals(driver.switchTo().activeElement()))
		{
			findClickableElement(wikiMoreActionInfoModalLinkTextBox).click();
		}
		String selectedWiki = ((JavascriptExecutor) driver).executeScript("return ").toString();
		return selectedWiki;
	}

	/**
	 * yellow strip comes after editing discarded wiki to verify that
	 */
	@Override
	public boolean verifyResumeDiscardYellowStrip()
	{
		findVisibleElement(yellowStripResumeEditingLabel, Speed.slow);
		return (isDisplayed(yellowStripResumeEditingLabel));
	}

	/**
	 * yellow strip resume tag after editing discarded wiki
	 */
	@Override
	public void clickOnResumeYellowStrip()
	{
		findClickableElement(yellowStripResumeTag).click();
	}

	/**
	 * @return the discard button is availiable
	 */
	@Override
	public boolean verifyDiscardTagInResumeEditingMessage()
	{
		return (isDisplayed(yellowStripDiscardTag));
	}

	/**
	 * @return the resume button is availiable
	 */
	@Override
	public boolean verifyResumeTagInResumeEditingMessage()
	{
		return (isDisplayed(yellowStripResumeTag));
	}

	/**
	 * @param message to be verified
	 * @return the actual message comparison letter
	 */
	@Override
	public boolean verifyResumeEditingMessage(String actMessage)
	{
		String currentMessage = findPresentElement(yellowStripResumeEditingLabel).getText();
		return (actMessage.trim().equals(currentMessage.trim()));
	}

	/**
	 * yellow strip discard tag after editing discarded wiki
	 */
	@Override
	public void clickOnDiscardYellowStrip()
	{
		findClickableElement(yellowStripDiscardTag).click();
	}

	/**
	 * @return count of comment likes
	 */
	@Override
	public int getCommentLikeCount(String comment)
	{
		if (findPresentElement(By.xpath("((//*[normalize-space()='" + comment + "'])[1]//following::a[contains(text(),'ike')]//following::span/a)[1]"), Speed.slow).getText().trim().equals(""))
		{
			return 0;
		}
		return (Integer.parseInt(findVisibleElement(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]//following-sibling::div//a[contains(@onclick,'ContentLikeCollection.OpenLikeModal')]"), Speed.slow).getText().trim()));
	}

	/**
	 * TO click on number of likes comment count
	 */
	@Override
	public void clickOnLikeCommentCount(String comment)
	{
		if (findPresentElement(By.xpath("((//*[normalize-space()='" + comment + "'])[1]//following::a[contains(text(),'ike')]//following::span/a)[1]")).getText() == null)
		{
			System.err.println("count is 0");
		}
		findClickableElement(By.xpath("((//*[normalize-space()='" + comment + "'])[1]//following::a[contains(text(),'ike')]//following::span/a)[1]")).click();
	}

	/**
	 * @return the people who like this modal displayed
	 */
	@Override
	public boolean verifyPeopleWhoLikeThisModal()
	{
		findPresentElement(peopleWhoLikeThisModal, Speed.slow);
		return (isDisplayed(peopleWhoLikeThisModal));
	}

	/**
	 * TO click on modal close button
	 */
	@Override
	public void clickOnModalCloseButton()
	{
		findClickableElement(modalCloseButton).click();
	}

	/**
	 * @param text should be available
	 * @return the match result
	 *         To verify the comment text box result
	 */
	@Override
	public boolean verifyCommentBoxText(String text)
	{
		return (getUserData(text).equals(findPresentElement(userNameInCommentBox).getText()));
	}

	/**
	 * To send text in wiki comment box
	 */
	@Override
	public void sendTextInWikiCommentBox(String text)
	{
		findPresentElement(wikiCommentBox).sendKeys(text);
	}

	/**
	 * To click on wiki comment post button
	 */
	@Override
	public void clickOnWikiCommentPostButton()
	{
		verifyWikiCommentPostButton();
		findClickableElement(wikiCommentPostButton).click();
	}

	/**
	 * It will return the last reply data of the user
	 * 
	 * @throws InterruptedException
	 */
	@Override
	public String getCommentReplyData()
	{
		return (findVisibleElement(lastReply, Speed.slow).getText());
	}

	/**
	 * TO verify that comment box has been edited or not
	 */
	@Override
	public boolean verifyCommentBoxIsEditable(String comment)
	{
		findVisibleElement(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]/../../following-sibling::*//*[contains(@class,'ckContentArea inlineCK cke_editable')]"));
		return (isDisplayed(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]/../../following-sibling::*//*[contains(@class,'ckContentArea inlineCK cke_editable')]")));
	}

	/**
	 * TO verify attached file in edit comment
	 */
	@Override
	public String verifyCommentAttachment(String comment)
	{
		WebElement element = findPresentElement(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[1]/../../following-sibling::*//*[@class='TruncateTxt linkblack']"));
		return (element.getText());
	}

	/**
	 * TO verify Attachment remove button
	 */
	@Override
	public boolean verifyRemoveAttachmentButtonInCommentBox()
	{
		findPresentElement(removeAttachmentButtonInEditComment);
		return (isDisplayed(removeAttachmentButtonInEditComment));
	}

	/**
	 * To verify the presence of attachment modal
	 * 
	 * @return
	 */
	@Override
	public boolean verifyAttachmentModal()
	{
		findPresentElement(attachmentModal, Speed.slow);
		return (isDisplayed(attachmentModal));
	}

	/**
	 * To click on attach button in attachment modal
	 */
	@Override
	public void clickOnAttachButtonInAttachmentmodal()
	{
		findClickableElement(attachButtonInAttachmentModal).click();
	}

	/**
	 * To click on attachmentRemove button in edit comment
	 */
	@Override
	public void clickOnRemoveAttachmentButtonInEditComment(String comment)
	{
		findClickableElement(removeAttachmentButtonInEditComment).click();
	}

	/**
	 * To verify comment post button
	 */
	@Override
	public boolean verifyWikiCommentPostButton()
	{
		findPresentElement(wikiCommentPostButton, Speed.slow, 20);
		return (isDisplayed(wikiCommentPostButton));
	}

	/**
	 * To verify comment post button
	 */
	@Override
	public boolean verifyWikiComment(String comment)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[3]"), Speed.slow));
	}

	/**
	 * To verify delete comment modal
	 */
	@Override
	public boolean verifyDeleteCommentModal()
	{
		findPresentElement(deleteCommentConfirmationModal, Speed.slow);
		return (isDisplayed(deleteCommentConfirmationModal));
	}

	/**
	 * To get text clicked or unclicked basically to get status
	 */
	@Override
	public String getTextOfWikiLikeTag()
	{
		return (findPresentElement(wikiLikeTag, Speed.slow).getText().trim());
	}

	/**
	 * To verify the wiki liked message
	 * 
	 * @return the status
	 */
	@Override
	public boolean verifyWikiLikeMessage(String message)
	{
		String currentMessage = findPresentElement(wikiLikeMessage, Speed.slow).getText();
		return (currentMessage.trim().equals(message));
	}

	/**
	 * To verify the wiki home button is displayed
	 * 
	 * @return
	 */
	@Override
	public boolean verifyWikiHome()
	{
		findVisibleElement(wikiHomeButton, Speed.slow);
		return (isDisplayed(wikiHomeButton));
	}

	/**
	 * To click on delete button in confirmation modal
	 */

	@Override
	public void deleteWikiComment(String comment)
	{
		clickOnWikiCommentFooterLabels(comment, WikiLabels.WIKI_DELETE);
		findPresentElement(deleteCommentConfirmationModal, Speed.slow);
		if (isDisplayed(deleteCommentConfirmationModal))
		{
			findClickableElement(deleteCommentConfirmationModalDeleteButton).click();
		}
	}

	@Override
	public void closeWikiCompareModel()
	{
		findVisibleElement(closeCompareModel).click();
	}

	@Override
	public void closeWikiInfoModel()
	{
		findPresentElement(wikiInfoModalDisplay, Speed.slow);
		findVisibleElement(closeWikiInfoModel).click();
		findPresentElement(wikiInfoModalClosed, Speed.slow);
	}

	@Override
	public void waitTillFileisDownloading()
	{
		findPresentElement(globalSuccessMessage, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @returnn the presence of last comment added
	 * @creation date 06/02/2018
	 */
	@Override
	public boolean verifyLastCommentInSelectedWiki(String comment)
	{
		findVisibleElement(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[last()]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[normalize-space()='" + comment.trim() + "'])[last()]")));
	}

	/**
	 * Verify default message in Share Modal -> Microblog
	 * 
	 * @param messageToVerify
	 *        message to verify
	 * @return true
	 *         if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultMicroblogMessageInShareModal(String messageToVerify)
	{
		findVisibleElement(share_Microblog).click();
		findPresentElement(shareModal, Speed.slow);
		WebElement elem = findVisibleElement(share_Microblog_messageInput, Speed.slow);
		String elemText = "";
		try
		{
			elemText = ((JavascriptExecutor) driver).executeScript("return arguments[0].value", elem).toString().trim();
		}
		catch (Exception e)
		{
			elemText = elem.getText().trim();
		}
		return elemText.equals(messageToVerify.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To verify the status in my drafts page
	 * @created on 06/04/2018
	 */
	@Override
	public boolean verifyWikiStatusInMyDrafts(String wikiName, String status)
	{
		String actualStatus = findVisibleElement(By.xpath("//*[@id='wikiMyDraftBody']//*[normalize-space(text())='" + wikiName.trim() + "']/ancestor::tr//td[contains(@class,'m-tdBlock m-margTop')]"), Speed.slow).getText().trim();
		return (actualStatus.equals(status.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return the ck editor availiability
	 * @created on 09/04/2018
	 */
	@Override
	public boolean verifyCKEditorInWikiEditMode()
	{
		findVisibleElement(ckEidtor, Speed.slow);
		return (isDisplayed(ckEidtor, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the status
	 * @created on 23/04/2018
	 */
	public boolean verifyAttachmentsTabOpen()
	{
		findVisibleElement(attachmentsTabOpen, Speed.slow);
		return (isDisplayed(attachmentsTabOpen, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the left pannel available
	 * @created on 24/04/2018
	 */
	public boolean verifyLeftTree()
	{
		findVisibleElement(leftTree, Speed.slow);
		return (isDisplayed(leftTree, Speed.slow));
	}
}
