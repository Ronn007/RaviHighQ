package com.highq.pageobjects.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.lang.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.CollaborateLabel.DocFolderOperation;
import com.highq.base.CollaborateLabel.DocumentNotifications;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.DocumentRecipientsData;
import com.highq.pagedata.TaskAddDataPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.TasksPage;

public class DocumentWeb extends BannerPageWeb implements DocumentPage
{
	String dotChar = new Character('\u2026').toString();
	/************ Left Panel - Files ***************/
	By leftPanel = By.id("file_module_leftPanelPage");
	By showHideLeftPanel = By.xpath("//*[contains(@onclick,'showHideLeft();')]");
	By searchFoldersInput = By.id("treeSearchFolderText");
	By rootFolder = By.xpath("(//*[contains(@class,'fancytree-title')])[1]");
	By index = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_INDEX + "']");
	By recent = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_RECENT + "']");
	By sentForSignature = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + SystemAdminLabels.SYSTEM_SENT_FOR_SIGNATURE_MSG + "']");
	By favourites = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_FAVOURITES + "']");
	By attachments = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_ATTACHMENTS + "']");
	By deletedItems = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_DELETEDITEMS + "']");
	By clearSearchFolderIcon = By.xpath("//*[@id='file_module_leftPanelPage']//*[@data-original-title='" + FileLabels.FILES_LEFTPANEL_CLEARSEARCH + "']");
	By leftPanel_noResultFound = By.xpath("//*[@id='folderSearchResult']//*[contains(text(),'" + FileLabels.FILES_LEFTPANEL_NORESULTSFOUND + "')]");
	By leftPanelSearchTree = By.id("documentLeftPanelTreeSearchSection");

	/************ Files Banner ***********************/
	By fileBanner = By.xpath("(//*[contains(@class,'rightSideSection')])[1]//*[@class='infoHeadFixed']");// By.id("File_module_FolderNameDivID");
	By fileBanner_AddButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_ADD + "'])[1]");
	By fileBanner_ActionButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_ACTION + "'])[1]");;
	By fileBanner_ViewButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_VIEW + "'])[1]");
	By fileBanner_SearchFileInput = By.id("file_module_filterTextValue");
	By fileBanner_SearchFileLoader = By.xpath("//input[@id='file_module_filterTextValue']//following-sibling::img[contains(@src,'gray-loader.gif')]");
	By fileBanner_ClearSearch = By.xpath("//*[contains(@data-original-title,'" + FileLabels.FILES_CLEARSEARCHTERM + "') and not(contains(@style,'none;'))]");// By.id("file_module_serachClearCroosButtonID");
	By fileBanner_SearchDropDown = By.xpath("//*[contains(@class,'fileSearch')]//*[@*='dropdown']");
	By fileBanner_DropDownExpanded = By.xpath("(//*[@aria-expanded='true']/following-sibling::*[contains(@class,'dropdown-menu')])[last()]");// By.xpath("//*[@id='File_module_FolderNameDivID']//*[@aria-expanded='true']/following-sibling::*[@class='dropdown-menu']");
	By fileBanner_ActionsButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_ACTIONS + "'])[1]");
	By fileBanner_PrintPreviewButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_PRINTPREVIEW + "'])[1]");
	By fileBanner_ExportButton = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_EXPORT + "'])[1]");
	By fileBanner_New_Button = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_NEW_BUTTON + "'])[1]");

	/**************** FilesBanner - Search DropDown **************/
	By search_FileTypes = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_FILETYPES + "'])[last()]");
	By search_Tags = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_TAGS + "'])[last()]");
	By search_Author = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_AUTHOR + "'])[last()]");
	By search_ReadCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_READ + "'])[last()]");
	By search_UnreadCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_UNREAD + "'])[last()]");
	By search_WithCommentsCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_WITHCOMMENTS + "'])[last()]");
	By search_WithTasksCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_WITHTASKS + "'])[last()]");

	By search_Tags_SearchTagInput = By.id("file_module_tagSerachButtonID");
	By search_noResult = By.xpath("(//*[contains(@class,'searchComp')]/following::*[contains(text(),'" + FileLabels.FILES_LEFTPANEL_NORESULTSFOUND + "')])[last()]");

	By search_Author_SearchAuthorInput = By.id("file_module_authorSerachButtonID");
	By search_AdvancedSearch = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_ADVANCEDSEARCH + "'])[last()]");
	By search_ClearFilters = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_CLEARFILTERS + "'])[last()]");

	By search_filterExpanded = By.xpath("(//*[contains(@class,'dropAccord')])[last()]");
	By search_FileTypes_allCheckBoxes = By.xpath("//*[@id='file_module_docExtensionListDivID']//div[@class='checkbox']//input");
	By search_Tags_allCheckBoxes = By.xpath("//*[@id='file_module_docTagListDivID']//div[@class='checkbox']//input");
	By search_Author_allCheckBoxes = By.xpath("//*[@id='file_module_docAuthorListDivID']//div[@class='checkbox']//input");
	By search_FileTypesArrow = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_FILETYPES + "'])[last()]//span");
	By search_TagsArrow = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_TAGS + "'])[last()]//span");
	By search_AuthorArrow = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + FileLabels.FILES_SEARCHFILTER_AUTHOR + "'])[last()]//span");

	By search_BottomCheckboxes = By.xpath("//*[contains(@class,'dropAccord')]//*[@class='divider'][1]/following::*[@class='checkbox']");
	By search_AuthorInputCloseIcon = By.xpath(".//*[@id='file_module_docAuthorScrollDivID']/div[1]/button");
	By search_TagsInputCloseIcon = By.id("file_module_tagSerachClearButtonID");

	/****************
	 * Advanced Search - Search DropDown
	 * (for search,cancel,... any other footer buttons look for "footer" xpaths in this file)
	 **************/
	By advancedSearch_Modal = By.xpath("(//*[contains(@class,'modal-content overflowHidden')])[last()]");
	By advancedSearch_AnyOfTheseWordsHelp = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_ANYOFTHESEWORDS + "']//*[@data-original-title='" + FileLabels.FILES_ADVANCEDSEARCH_DESCRIPTION + "']");
	By advancedSearch_AllOfTheseWordsHelp = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_ALLOFTHESEWORDS + "']//*[@data-original-title='" + FileLabels.FILES_ADVANCEDSEARCH_DESCRIPTION + "']");
	By advancedSearch_ExcludeTheseWordsHelp = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_EXCLUDETHESEWORDS + "']//*[@data-original-title='" + FileLabels.FILES_ADVANCEDSEARCH_DESCRIPTION + "']");
	By advancedSearch_AnyOfTheseWordsInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_ANYOFTHESEWORDS + "']//following-sibling::*//input");
	By advancedSearch_AllOfTheseWordsInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_ALLOFTHESEWORDS + "']//following-sibling::*//input");
	By advancedSearch_ExcludeTheseWordsInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_EXCLUDETHESEWORDS + "']//following-sibling::*//input");

	By advancedSearch_TitleInput = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_TITLE + "'])[last()]//following-sibling::*//input");
	By advancedSearch_ContentTypeDrpDwn = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_CONTENTTYPE + "'])[last()]/following-sibling::*//*[@data-toggle='dropdown']");
	By advancedSearch_FileTypeDrpDwn = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_FILETYPE + "'])[last()]/following-sibling::*//*[@data-toggle='dropdown']");
	By advancedSearch_FoldersDrpDwn = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_FOLDERS + "'])[last()]/following-sibling::*//*[@data-toggle='dropdown']");
	By advancedSearch_LastModifiedDateDrpDwn = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_LASTMODIFIEDDATE + "'])[last()]/following-sibling::*//select");
	By advancedSearch_AuthorInput = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_SEARCHFILTER_AUTHOR + "'][last()]//following-sibling::*//input[contains(@placeholder,'" + FileLabels.FILES_ADVANCEDSEARCH_SEARCHANDHIT + "')])[last()]");
	By advancedSearch_TagsInput = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_SEARCHFILTER_TAGS + "'][last()]//following-sibling::*//input[contains(@placeholder,'" + FileLabels.FILES_ADVANCEDSEARCH_SEARCHANDHIT + "')])[last()]");
	By advancedSearch_AlsoSearchSubFoldersCheckbox = By.xpath("(//*[normalize-space(.)='" + FileLabels.FILES_ADVANCEDSEARCH_ALSOSEARCHSUBFOLDERS + "'])[last()]//input[@type='checkbox']");
	By advancedSearch_HideHelp = By.xpath("//*[@class='panelCloseHead']");
	By advancedSearch_HelpDescription = By.xpath("//*[@class='panelCloseHead']/following::*[@class='ckContentArea']");
	By advancedSearch_FileType_searchInput = By.id("file_module_advSearch_docTypeSearchButtonID");

	/**************** Folder Title Container - Below File Banner **************/
	By folderTitle = By.xpath("//*[@class='folderTitle']");
	By fullScreenIcon = By.xpath("(//*[@data-original-title='" + FileLabels.FILES_FOLDERTITLE_FULLSCREEN + "'])[last()]");
	By favoritesStarIcon = By.xpath("(//*[@data-original-title='" + FileLabels.FILES_FOLDERTITLE_ADDTOFAVOURITES + "'])[last()]");
	By moreActions = By.xpath("(//*[@class='clearfix']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "'])[last()]");

	/************ File - Main Table Header ***************/
	By selectAllChkbox = By.xpath(".//div[@id='file_module_tableContentDivID']//input[@name='File_module_selectAll_checkBox_Name']");
	By header_Name = By.xpath("(//thead//*[normalize-space(.)='" + FileLabels.FILES_MAINTABLEHEADER_NAME + "'])[last()]");
	By header_Size = By.xpath("(//thead//*[normalize-space(.)='" + FileLabels.FILES_MAINTABLEHEADER_SIZE + "'])[last()]");
	By header_Author = By.xpath("(//thead//*[normalize-space(.)='" + FileLabels.FILES_MAINTABLEHEADER_AUTHOR + "'])[last()]");
	By header_LastModified = By.xpath("(//thead//*[normalize-space(.)='" + FileLabels.FILES_MAINTABLEHEADER_LASTMODIFIED + "'])[last()]");

	/************ File - Main Table ***************/
	By noFilesFolder = By.xpath("(.//*[@id='file_module_tableContentDivID']//*[normalize-space(text())='" + FileLabels.FILES_MAINTABLE_NOFILESORFOLDERS + "'])[1]");

	/************ File - Add Files(opens after Add->Files selection) ***************/
	By addFiles_Modal = By.xpath(".//*[@id='file_module_addFiles_modal' and contains(@aria-hidden,'false')]");
	By files = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_ADD + "')])[1]/following::a[contains(text(),'" + FileLabels.FILES_ADDDROPDOWN_FILES + "')][1]");

	By addFiles_FilesTab = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_COMMON_ADDFILES + "')])[1]/following::*[contains(text(),'" + FileLabels.FILES_ADDFILES_FILES + "')][1]");
	By addFiles_MetadataTab = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_COMMON_ADDFILES + "')])[1]/following::*[contains(text(),'" + FileLabels.FILES_ADDFILES_METADATA + "')][1]");
	By addFiles_TasksTab = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_COMMON_ADDFILES + "')])[1]/following::*[contains(text(),'" + FileLabels.FILES_ADDFILES_TASKS + "')][1]");

	By addFiles_Browse = By.xpath("//div[@id='file_module_addFiles_TabFiles']//input[@id='ckUploadFile1'][1]");
	By addFiles_Add = By.id("file_module_addFiles_modal_add");

	// Metadata tab
	By addFiles_MetadataTab_TagsInput = By.id("add_Placeholder_tagList_ID-tokenfield");

	// Tasks tab
	By addFiles_TasksTab_AddButton = By.xpath(".//*[@id='file_module_addFiles_TabTasks']//button[normalize-space(.)='" + FileLabels.FILES_ADD + "']");

	By addFiles_TaskTab_AddTask_Add = By.id("addTaskModalID_addTaskSave");
	By addFiles_TaskTab_AddTask_addTaskTitle = By.xpath("(//*[text()='" + FileLabels.FILES_ADDFILES_TASKSTAB_ADDTASK + "'])[last()]");

	By globalSuccessMsg = By.xpath(".//div[@id='uploadQueue_globalInfoMsg' and contains(@class,'globalSuccessMsg')]");

	// Delete Files
	By fileAction_Delete = By.xpath(".//*[@class = 'fixedDivWrapper']//a[text()= '" + FileLabels.FILES_COMMON_DELETE + "']");
	By fileAction_RevokeAndDelete = By.xpath(".//*[@id='file_module_deleteFoldDocModal_okForRevokeDelete']");
	By deleteFoldDocModel = By.xpath(".//*[@id='file_module_deleteFoldDocModal' and contains(@aria-hidden,'false')]");
	By deleteModal_Delete = By.id("file_module_deleteFoldDocModal_ok");

	// Edit Files
	By editFileModal = By.xpath("//*[@id='FILE_MODULE_EDIT_FILE_FOLDER_DETAILS' and contains(@style,'display: block;')]");
	By editFile_Title = By.id("file_module_old_fileName");
	By editFile_Save = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_COMMON_SAVE + "'])[last()]");
	By save = By.xpath(".//button[normalize-space(.)='" + FileLabels.FILES_COMMON_SAVE + "']");
	By editFile_tags = By.id("file_module_addTags-tokenfield");
	By editFile_DetailsTab = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DETAILS + "'])[last()]");
	By editFile_ErrorMsg = By.id("errorElementContainer");

	// tags
	By allTags = By.xpath("//*[@class='token']//*[@class='token-label']");
	By removeTag = By.xpath("//*[@class='token'][last()]/*[@class='close']");

	/** Add New Version */
	By newVer_Details_Title = By.id("docversion_name");
	By newVer_Details_Tags = By.id("document_version_tagList_ID-tokenfield");
	By newVer_Details_Browse = By.id("fileVersion");
	By newVer_Details_VersionNotes = By.id("documentVersion_documentComments");
	By newVer_TasksTab = By.xpath(".//*[@id='document_version_modal_tab_TasksClick']/a");
	By newVer_TasksTab_Addbtn = By.xpath(".//*[@id='document_version_modal_tab_Tasks']//button");
	By newVer_Addbtn = By.id("file_module_addNewFileVersion_modal_add");

	/******************** footer ********************/
	By footer_Add = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_ADD + "'])[last()]");
	By footer_Cancel = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CANCEL + "' and not(contains(@class,'hide'))])[last()]");
	By footer_Close = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CLOSE + "' and not(contains(@class,'hide'))])[last()]");
	By footer_Search = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[contains(text(),'" + FileLabels.FILES_COMMON_SEARCH + "')])[last()]");
	By footer_Continue = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CONTINUE + "'])[last()]");
	By footer_Move = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_MOVE + "'])[last()]");
	By footer_Download = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DOWNLOAD + "' and not(contains(@class,'hide'))])[last()]");
	By footer_CheckOut = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CHECKOUT + "' and not(contains(@class,'hide'))])[last()]");
	By footer_CheckIn = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CHECKIN + "' and not(contains(@class,'hide'))])[last()]");
	By footer_Copy = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_COPY + "'])[last()]");

	// other
	By notificationBell = By.xpath("(.//*[@class='modal-footer']//button[contains(@class,'notificationBtn')])[last()]");
	By desclaimerCheckbox = By.id("file_module_file_disclaimer");
	By desclaimerText = By.id("file_module_disclaimer_text");
	By moreOptionExpanded = By.xpath("//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']");// By.xpath(".//*[@id='file_module_mainTableId']//a[@data-original-title='More actions' and @aria-expanded='true']");
	By addtaskModal = By.xpath(".//*[@id='addTaskModalID' and @aria-hidden='false']");
	By btnDone = By.xpath("(//span[contains(text(),'" + FileLabels.FILES_COMMON_DONE + "')])[last()]");
	By deleteMsg = By.id("file_module_deleteFoldDocModal_BODY");
	By addTaskModalClosed = By.xpath(".//*[@id='addTaskModalID' and @aria-hidden='true']");
	By modalContent = By.xpath("(//*[@class='modal-content'])[last()]");
	By filterGrayLoader = By.xpath("(//*[contains(@class,'searchComp')]//*[@class='grayloader' and contains(@style,'display: block;')])[last()]");
	By modalContentClosed = By.xpath("(//*[@class='modal-content'])[last()]/ancestor::*[contains(@style,'display: none;')]");
	/** Add Zip file */
	By zipFile_FileTab_BrowseInput = By.id("zippedFile");
	By zipFile_MetadataTab_TagsInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_SEARCHFILTER_TAGS + "']/following-sibling::*[1]//*[contains(@id,'-tokenfield')]");
	By zipFile_MetadataTab_FileViewDropDown = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ZIPFILE_METADATATAB_FILEVIEW + "']/following-sibling::*[1]/select");
	By zipFile_MetadataTab_FileViewHelpIcon = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ZIPFILE_METADATATAB_FILEVIEW + "']/following-sibling::*[contains(@class,'icon-question')][1]");
	By zipFile_MetadataTab_DesclaimerCheckbox = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ZIPFILE_METADATATAB_DISCLAIMER + "']/following-sibling::*[1]//*[contains(@class,'chkbox')]");
	By zipFile_MetadataTab_DesclaimerInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_ZIPFILE_METADATATAB_DISCLAIMER + "']/following-sibling::*[1]//textarea");

	/** Add PlaceHolder File */
	By placeHolder_DetailsTab_NameInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PLACEHOLDERFILE_DETAILSTAB_NAME + "']/following-sibling::*[1]/*[@type='text']");
	By placeHolder_DetailsTab_VersionNotes = By.xpath("//*[normalize-space(text())='Version notes']/following-sibling::*[1]/textarea");

	/** Preview Page */

	final String DIALOGPREVIEW = "(//*[@class='dialogPrev'])[last()]";

	By disclaimerHeader = By.xpath("//*[@class='modal-content']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DISCLAIMER + "']");
	By previewPage = By.xpath(DIALOGBODY);
	By previewPageHeader_FolderName = By.xpath("(//*[contains(@id,'modal_header')])[last()]//*[@class='iconMeta']/*[2]");
	By previewPageHeader_DownloadButton = By.xpath("((//*[contains(@id,'modal_header')])[last()]//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DOWNLOAD + "'])[1]");

	By previewPage_DownloadButton = By.xpath("(//*[@class='dialogPrev'])[last()]//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DOWNLOAD + "']");

	By previewPage_HideArrow = By.xpath(DIALOGBODY + "//*[contains(@class,'icon-chevron-right')]");
	By previewPage_FavouriteStar = By.xpath(DIALOGBODY + "//*[contains(@class,'icon-star')]");
	By previewPage_MoreActionIcon = By.xpath("(" + DIALOGBODY + "//*[@data-toggle='dropdown'])[1]");

	By previewPage_Details_Type = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_TYPE + "']/following-sibling::*[1]");
	By previewPage_Details_Size = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_SIZE + "']/following-sibling::*[1]");
	By previewPage_Details_Author = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_AUTHOR + "']/following-sibling::*[1]/*[1]");
	By previewPage_Details_Created = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_CREATED + "']/following-sibling::*[1]");
	By previewPage_Details_Modified = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_MODIFIED + "']/following-sibling::*[1]");
	By previewPage_Details_lastTag = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_SEARCHFILTER_TAGS + "']/following-sibling::*[1]//*[@class='TruncateTxt'])[last()]");
	By previewPage_Details_Notes = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_NOTES + "']/following-sibling::*[1]");
	By previewPage_Details_LikeLink = By.xpath(DIALOGBODY + "//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_LIKE + "']");
	By previewPage_Details_CommentLink = By.xpath(DIALOGBODY + "//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_COMMENT + "']");
	By previewPage_MetadataTab = By.xpath(DIALOGBODY + "//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_METADATA + "']");
	By previewPage_CommentsTab = By.xpath(DIALOGBODY + "//*[normalize-space(text())='" + FileLabels.FILES_PREVIEWPAGE_DETAILS_COMMENTS + "']");
	By previewPage_EditPencil = By.xpath(DIALOGBODY + "//*[contains(@class,'icon-edit-circle')]");

	By previewPage_FileLoader = By.xpath("(//*[@class='dialogLoader']/img)[last()]");
	By previewPage_TotalPageCount = By.xpath(".//*[@id='viewerDocument']//*[@title='" + FileLabels.FILES_ATTRIBUTE_NEXTPAGE + "']/preceding-sibling::*[1]");
	By previewPage_FullScreen = By.xpath("//*[@id='viewerDocument']//*[@title='" + FileLabels.FILES_ATTRIBUTE_FULLSCREEN + "']");
	By previewPage_PageContentLoader = By.xpath(".//*[@id='viewerDocument']/div[@class='pccPageListItemLoading']");
	// By previewPage_PrintIcon = By.xpath("//*[contains(@class,'icon-print')]");

	By settingsAddFolderTab = By.xpath("//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_ADDFOLDER_SETTINGS + "'][last()]");
	By commonFilesTab = By.xpath("(//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_ADDFILES_FILES + "'])[last()]");
	By commonMetadataTab = By.xpath("(//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_ADDFILES_METADATA + "'])[last()]");
	By commonTasksTab = By.xpath("(//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_ADDFILES_TASKS + "'])[last()]");
	By commonDetailsTab = By.xpath("(//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DETAILS + "'])[last()]");
	By commonAttachmentsTab = By.xpath("(//*[contains(@style,'display: block;')]//*[normalize-space(text())='" + FileLabels.FILES_LEFTPANEL_ATTACHMENTS + "'])[last()]");

	By downloading = By.id("globalProcessMessageContainerMsgSpace");
	By deleting = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DELETING + "'])[last()]");
	By restoreSuccess = By.xpath(".//*[@id='globalAlertMessageContainer']//*[normalize-space(text())='" + FileLabels.FILES_COMMON_THERESTOREHASCOMPLETED + "']");

	/** Add Folder */
	By folder_Details_NameInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_FOLDER_DETAILS_NAME + "']/following-sibling::*//*[@type='text'][1]");
	By folder_Details_DescriptionInput = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_FOLDER_DETAILS_DESCRIPTION + "']/following-sibling::*//textarea");
	By cancelButtonAddFolderModal = By.id("file_module_addFolder_modal_MAIN_CLOSE_BUTTON");
	By cancelButtonClassifiersModal = By.id("addFdolder_classifier_list_cancle");
	By addButtobNewClassifiersModal = By.id("addFdolder_addNew_classifier_add");

	/** Move */
	By move_ChooseASiteDropDown = By.xpath("//*[contains(text(),'" + FileLabels.FILES_MOVE_CHOOSEASITE + "')]/following-sibling::*/select");
	By move_ExpandAllLink = By.xpath("(//*[normalize-space(text())='(+) " + FileLabels.FILES_MOVE_EXPANDALL + "'])[last()]");
	By move_CollapseAllLink = By.xpath("(//*[normalize-space(text())='(-) " + FileLabels.FILES_MOVE_COLLAPSEALL + "'])[last()]");
	By leftPanel_ClearSearchIcon = By.xpath(".//*[@id='file_module_leftPanelPage']//*[@data-original-title='Clear search']");

	By fileMeta_Version = By.xpath("(//*[@class='ffname']//*[contains(@class,'greyMeta')]//*[1])[last()]");
	By uploadingLoader = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_COMMON_UPLOADING + "'])[last()]");

	/** Folder Title Container */
	By titleContainer = By.xpath("//*[@class='folderTitleContainer']");
	By titleContainer_FolderTitle = By.xpath("(//*[@class='folderTitleContainer']//*[contains(@class,'folderTitle')])[1]");
	By titleContainer_FolderDescription = By.xpath("(//*[@class='folderTitleContainer']//*[contains(@id,'folderComment')])[1]//span");
	By titleContainer_FolderDescriptionMoreLink = By.xpath("(//*[@class='folderTitleContainer']//*[contains(@id,'folderComment')])[1]//*[normalize-space(text())='" + FileLabels.FILES_COMMON_MORE + "']");
	By titleContainer_MoreActionIcon = By.xpath("(//*[@class='folderTitleContainer']/preceding-sibling::*[1]//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "'])[1]");

	By grayLoader = By.xpath("(//*[contains(@src,'gray-loader')])[last()]");

	/** Bulk Print */
	By generatingPDF = By.xpath(".//*[@id='showPrintProccessID']/*[normalize-space(text())='" + FileLabels.FILES_BULKPRINT_GENERATINGPDFFILESMSG + "']");
	By yourFileIsNowReady = By.xpath(".//*[@id='showPrintReadyID']/*[normalize-space(text())='" + FileLabels.FILES_BULKPRINT_YOURFILEISREADYTODOWNLOADMSG + "']");
	By bulkDownloadMessage = By.xpath(".//*[@id='showPrintReadyID']/*[normalize-space(text())='" + FileLabels.FILES_BULKPRINT_CLICKDOWNLOADTOCONTINUEMSG + "']");

	/** Checkout */
	By checkout_FileName = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_CHECKOUT_FILENAME + "']/following-sibling::*[1]");
	By checkout_Status = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_CHECKOUT_STATUS + "']/following-sibling::*[1]/*[1]");
	By checkout_UserName = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_CHECKOUT_STATUS + "']/following-sibling::*[1]//*[contains(@onclick,'viewUserProfilePreview')]");
	By downloadingFiles = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_CHECKOUT_DOWNLOADINGFILES + "'])[last()]");
	By checkout_CancelCheckoutLink = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_CHECKOUT_CANCELCHECKOUT + "']");

	/** Like and comment */
	By likeLink = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_LIKE + "'])[last()]");
	By unlikeLink = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_UNLIKE + "'])[last()]");
	By likeContainer = By.xpath(".//*[contains(@id,'contentLike_')]");
	By commentInput = By.xpath("//*[contains(@id,'addCommentField') and contains(@class,'ckContentArea')]");
	By comment_attachLinkIcon = By.xpath("//*[contains(@class,'commentMeta')]//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_LINK + "']");
	By comment_attachmentIcon = By.xpath("//*[contains(@class,'commentMeta')]//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_ATTACHMENT + "']");
	By comment_textFormattingIcon = By.xpath("//*[contains(@class,'commentMeta')]//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_TEXTFORMATTING + "']");
	By comment_postButton = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_POST + "' and not(contains(@class,'hide'))])[last()]");
	By editCommentTextBox = By.xpath("//*[@role='textbox' and @contenteditable='true']");
	By loadMoreCommentsLink = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_LOADMORECOMMENTS + "']");
	By messageModelOpened = By.xpath("//*[@id='collaborateCustomMessageModal' and @aria-hidden='false']");
	By messageModelClosed = By.xpath("//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none;')]");
	By deleteCommentMessage = By.xpath("//*[@id='collaborateCustomModalMessage' and normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_DELETECOMMENTCONFIRMATION + "']");
	By cancelMessageModel = By.id("collaborateMessageCancelButton");
	By confirmMessageModel = By.id("collaborateMessageOkButton");
	By totalComments = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_UNLIKE + "'])[1]/following-sibling::*[contains(@id,'commentLink_')]");
	By editComment_PostButton = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_LIKEANDCOMMENT_POST + "' and not(contains(@class,'disabled'))])[last()]");

	By globalAlertMessageContainer = By.id("globalAlertMessageContainerMsgSpace");
	By globalProcessMessageContainer = By.id("globalProcessMessageContainer");
	By globalFailureMessageContainer = By.id("globalFailMessageContainer");
	By authorColumn = By.xpath(".//*[@id='file_module_mainTableId']//*[contains(@onclick,'viewUserProfilePreview')]");
	By noFavourites = By.xpath(".//*[@id='file_module_favFoldAndFiles_noItem']/div/h6");

	By downloadingFilesConfirmation = By.xpath("//*[contains(text(),'" + FileLabels.FILES_COMMON_DOWNLOADING + "')]");

	By addZippedFile_FileTab = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_COMMON_ADDZIPPEDFILETITLE + "')])[1]/following::*[contains(text(),'" + FileLabels.FILES_ADDDROPDOWN_FILES + "')][1]");

	By allFileLinks = By.xpath(".//*[@id='file_module_mainTableId']//*[@class='ffname']");
	By preparingDownload = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_COMMON_PREPARINGDOWNLOAD + "']");
	By previewPage_PrintIcon = By.xpath("//*[contains(@class,'icon-print')]");
	By placeHolder_DisclaimerInput = By.id("file_module_disclaimer_text");
	String VERSION = null;
	Actions build;
	By addFilesModal = By.id("file_module_addFiles_modal");
	By openedModal = By.xpath("//*[contains(@class,'modal-dialog')]/../*[contains(@style,'block')]");

	/* Add folder */
	By addFolder_ChooseLocation_expandAllLink = By.xpath(".//*[@id='file_module_addFolder_locationModal_BODY']//*[contains(@onclick,'addFolderModal.expandAllTree')]");
	By addFolder_ChooseLocation_collapseAllLink = By.xpath(".//*[@id='file_module_addFolder_locationModal_BODY']//*[contains(@onclick,'addFolderModal.collapseAllTree')]");
	By addFolder_ChooseLocation_selectButton = By.id("file_module_addFolder_locationModal_add");
	By addFolder_Details_chooseButton = By.id("editLocation");
	By addFolder_ChooseLocation_RootFolder = By.xpath("(.//*[@id='file_module_innerModalDiv']//*[contains(@class,'fancytree-node')])[1]");

	By classifierList = By.xpath(".//*[@id='showClassifiersList']//li");
	By documentAnalysisLbl = By.xpath(".//*[@id='file_module_addFolder_settingsTab']//*[text()='" + FileLabels.DOCUMENT_ANALYSIS_LABEL + "']");
	By classifiersModalLabel = By.xpath(".//*[@id='addFdolder_classifier_list']//*[@id='addFdolder_classifier_list_TITLE' and normalize-space(.)='Classifiers']");
	By newClassifierModal = By.xpath("//*[@id='addFdolder_addNew_classifier_TITLE' and normalize-space(.)='New classifier']");
	By newClassifierModalNameField = By.xpath(".//*[@id='add_new_classifier_name' and @placeholder='Enter classifier name']");
	By newClassifierModalDescriptionField = By.xpath(".//*[@id='add_new_classifier_decription' and @placeholder='Add description']");
	By newClassifierModalScopeOfClassifierPublicDefault = By.xpath(".//*[@id='classifierScopepublic' and @type='radio'and @value='true']");
	By newClassifierModalScopeOfClassifierPrivateDefault = By.xpath(".//*[@id='classifierScopePrivate' and @type='radio' and @value='false']");
	By newClassifierModalScopeOfClassifierPublicRB = By.id("classifierScopepublic");
	By newClassifierModalScopeOfClassifierPrivateRB = By.id("classifierScopePrivate");

	By newClassifierModalScopeOfClassifierCancelButton = By.id("addFdolder_addNew_classifier_cancle");
	By newClassifierModalScopeOfClassifierAddButtonDefault = By.xpath(".//*[@id='addFdolder_addNew_classifier_add' and @disabled='disabled']");

	By documentAnalysisClassifierSelectButton = By.xpath(".//*[@id='file_module_addFolder_settingsTab']//*[text()='" + FileLabels.CLASSIFIER_BUTTON + "" + dotChar + "']");
	By classifierAddLabel = By.xpath(".//*[@id='filterClassifierListView']//label[normalize-space()='" + FileLabels.CLASSIFIER_ADD_LABEL + "']");
	By classifierSearchTextboxDefault = By.xpath(".//*[@id='addFolder_classifier_search' and @onkeyup='AddFolderModalCollection.OnSearchClassifier(this)' and @placeholder='" + FileLabels.CLASSIFIER_SEARCH_PLACEHOLDER_LABEL + "']");
	By classifierSearchNoresultsfoundLabel = By.xpath(".//*[@id='noDataFoundDiv']//strong[normalize-space()='" + FileLabels.CLASSIFIER_SEARCH_NO_RESULTS_FOUND_LABEL + "']");
	By classifierNewClassifierButton = By.id("addFdolder_classifier_list_add_newClassifier");
	By classifierDoneButton = By.id("addFdolder_classifier_list_Done");
	By classifierCancelButton = By.id("addFdolder_classifier_list_cancle");
	By classifierDoneButtonDisabled = By.xpath(".//*[@id='addFdolder_classifier_list_Done' and @disabled='disabled']");
	By classifierDoneButtonEnabled = By.xpath(".//*[@id='addFdolder_classifier_list_Done' and not(@disabled='disabled')]");
	By classifierTokenRemoveButton = By.id("removeSelectedClassifierIcon");
	By classifierSearchTextbox = By.id("addFolder_classifier_search");
	By classifierClearSearchTextbox = By.id("clearSearchTextBtn");

	/* Index page Xpath */

	By index_ActionsButton = By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ACTIONS + "'])[1]");
	By index_AllocateReviewModal = By.id("file_module_allocateReview_MODAL");
	By index_CheckingAllocationMsg = By.xpath(".//*[@id='file_module_allocateReview_MODAL_BODY']//*[@class='row-fluid']/*[1]");
	By index_CheckingAllocation_CloseButton = By.id("file_module_allocateReview_MODAL_close");
	By index_CheckingAllocation_ContinueButton = By.id("file_module_allocateReview_MODAL_continue");
	By index_MarkAsNA_CheckingFileIsNotApplicableForReviewTitle = By.xpath(".//*[@id='file_module_allocateReview_MODAL_TITLE']/div");
	By index_MarkAsNA_MarkingFilesAsNotApplicableForReviewTitle = By.id("file_module_allocateReview_MODAL_TITLE");
	By index_AllocateReviewModalClosed = By.xpath(".//*[@id='file_module_allocateReview_MODAL' and contains(@style,'display: none')]");
	By indexModal = By.id("file_module_mainMiddlePanelDivID");
	By index_AllocateReviewModalGrayLoader = By.xpath(".//*[@id='file_module_allocateReview_MODAL_BODY']//*[contains(@src,'gray-loaderbig')]");

	/* File table */
	By fileContainer = By.id("file_module_tableContentDivID");
	String fileTable_allHeaders = ".//*[@id='file_module_mainTableId']//th";
	String fileTable_allColumns = ".//*[@id='file_module_mainTableId']//td";
	By addButtonAddFolderModal = By.id("file_module_addFolder_modal_add");
	By saveButtonEditFolderModal = By.id("file_module_addFolder_modal_edit");

	By addNewFolderModal = By.id("file_module_addFolder_modal");
	By classifiersModal = By.id("addFdolder_classifier_list");
	By addNewClassifierModal = By.id("addFdolder_addNew_classifier");

	By labelClassifier = By.xpath(".//*[@id='selectedClassifierlabel']");
	By documentTemplate = By.xpath(".//*[@class='ffname']//*[contains(@onclick,'checkDocumentAccess')]");

	By share_Email_recipientsInput = By.id("file_module_myFiles_emailRecipients-tokenfield");
	By share_Email_subjectInput = By.id("file_module_myFiles_emailLink_Subject");
	By share_Email_messageInput = By.id("file_module_myFiles_emailLink_Message");
	By shareSend = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_SEND + "']");
	By closeButtop = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_file_module_shareModal_close_btn");
	By activeTab = By.id("file_module_active_Link");

	By moreAction = By.xpath(".//*[@id='document_preview_modal_right_main']//*[@data-original-title='More actions']");
	By closeButton = By.id("FILE_MODULE_AUDIT_HISTORY_file_module_audit_history_modal_close");
	By printButton = By.xpath(".//*[@data-pcc-removable-id='printing']");
	By printSubmit = By.xpath(".//*[@data-pcc-print='submit']");
	By clickOnCross = By.xpath(".//*[@class='close tooltipShow']");
	By auditOnScoll = By.xpath(".//*[@id='file_module_auditOnScoll_AUDIT']");
	By iconButton = By.xpath(".//paper-icon-button[@id='print']");
	By printIcon = By.xpath(".//paper-icon-button[@id='print']");
	By toolbar = By.xpath(".//div[@id='toolbar']");
	By printPreviewCancelButton = By.xpath(".//*[@id='print-header']//*[text()='Cancel']");
	By printPreviewWindowHeader = By.id("print-header");

	/* Action Button */
	By locActionBtn = By.id("file_module_action_button");

	By fileBanner_AddButtonNew = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_UPLOAD_BUTTION + "'])[1]");

	By fileModuleRecipient = By.xpath(".//*[@id='file_module_recipient_modal_id']//*[@class='modal-content']");
	By recipientName = By.id("recipientName-tokenfield");
	By fileModuleRecepientModalId = By.id("file_module_recipient_modal_id_send");
	By collaborateCustomerModalMsg = By.id("collaborateCustomModalMessage");
	By fileModuleThirdParty = By.xpath(".//*[@id='file_module_third_party_services_modal_id']//*[@class='modal-content']");
	By modalTitle = By.xpath(".//*[@class='modal-title'][normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + "']");
	By fileModuleThirdPartyService = By.id("file_module_third_party_services_modal_id_MAIN_CLOSE_BUTTON");
	By chooseService = By.xpath(".//*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_CHOOSEANYONESERVICETOSENDABOVESELECTEDITEM + "']");
	By fileModuleThirdPartyModalClose = By.id("file_module_third_party_services_modal_id_Close");

	By recipientEmail = By.id("recipientEmail-tokenfield");
	By recipientName1 = By.id("recipientName");
	By recipientMessage = By.id("recipientMessage");
	By fileModalClose = By.id("file_module_recipient_modal_id_Close");
	By sendBtn = By.xpath(".//*[normalize-space(text())='" + FileLabels.SITE_ADMIN_USERS_SENDBUTTON + "']");

	By addRecipient = By.xpath(".//*[normalize-space(text())='Add recipient']");

	By needToSign = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Needs to Sign']");
	By inPersonSighner = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='In Person Signer']");
	By receivesCopy = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Receives a Copy']");
	By needToView = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Need to View']");
	By specifyRecepient = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Specify Recipients']");
	By allowToEdit = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Allow to Edit']");
	By updateRecipients = By.xpath(".//*[@class='dropdown-menu open']//span[normalize-space(text())='Update Recipients']");

	By recipiName = By.xpath("recipientName-tokenfield");
	By recipientEml = By.id("recipientEmail");
	By emailRecipient = By.xpath(".//*[@id='emailRecipientsGrp']//p");
	By recipientDisable = By.xpath(".//*[@id='nameRecipientGrp']//*[@disabled='disabled']");
	By emailDisabled = By.xpath(".//*[@id='emailRecipientsGrp']//*[@disabled='disabled']");

	By nameReci = By.xpath(".//*[@id='nameRecipientGrp']//*[@class='token']");
	By recipientToken = By.xpath(".//*[@id='nameRecipientGrp']//*[@class='token']//span");
	By recipientToken1 = By.xpath(".//*[@id='nameRecipientGrp']//*[@class='token']//a");
	By disabled = By.xpath(".//*[@disabled='disabled']");
	By reciEmail = By.id("recipientEmail");
	By collaborateMess = By.xpath(".//*[@id='collaborateCustomMessageModal']//*[@class='modal-content']");

	By locUserName = By.id("username");
	By locSubmit = By.xpath(".//*[@type='submit']");
	By locPassword = By.id("password");
	By locCheckinOutPage = By.id("CHECK_IN_CHECK_OUT_PAGE_MODAL_TITLE");
	By locCloseBtnCheckOutModel = By.id("CHECK_IN_CHECK_OUT_PAGE_MODAL_closeButton");
	By locCloseBtnThirdPartyService = By.id("file_module_third_party_services_modal_id_Close");
	By locRecipientMsg = By.id("recipientMessage");
	By locCustomModel = By.id("customMessageModalTitle");
	By locCustomMsg = By.id("collaborateCustomModalMessage");
	By locCustomOkMsg = By.id("collaborateMessageOkButton");
	By locInvalidRecipeintid = By.id("recipientEmail_pID");

	By deleteFoldDocModal = By.xpath(".//*[@id='file_module_deleteFoldDocModal']//*[@class='modal-content']");
	By OkForRevokeDelete = By.id("file_module_deleteFoldDocModal_ok");

	By unlockRevokeAndDelete = By.id("file_module_deleteFoldDocModal_okForRevokeUnlockDelete");
	By okForRevokeDelete = By.id("file_module_deleteFoldDocModal_okForUnlockDelete");
	By DeleteFoldDocModalOk = By.id("file_module_deleteFoldDocModal_okForRevokeDelete");
	By taggingInterface = By.xpath(".//*[@id='file_module_tagginginterface_modal_id']//*[@class='modal-dialog']");
	By DocuSign = By.xpath(".//*[@id='undefined_TITLE'][normalize-space(.)='Send to - DocuSign']");
	By locCheckOutBtn = By.id("CHECK_IN_CHECK_OUT_PAGE_MODAL_checkOutButton");
	By locThirdPartyServiceActionMenu = By.xpath(".//*[@data-id='thirdpartyServiceActionMenu']");
	By thirdPartyServiceComboBox = By.xpath(".//*[@data-id='thirdpartyServiceActionMenu']/following-sibling::*[@role='combobox']");
	String thirdPartyServiceList = ".//*[@data-id='thirdpartyServiceActionMenu']/following-sibling::*[@role='combobox']//li";

	Select dropDown;
	By file_module_sendAFile_button = By.id("file_module_sendAFile_button");
	By addFolder_Add = By.id("file_module_addFolder_modal_add");

	/* Audit History modal */
	By auditHistoryModal = By.id("FILE_MODULE_AUDIT_HISTORY");
	By auditHistory_AuditsTab = By.id("file_module_auditHistory_Link");
	By auditHistory_VersionsTab = By.id("file_module_versionHistory_Link");
	By auditHistory_AccessbyTab = By.id("file_module_whosReview_Link");
	By auditHistory_CloseButton = By.id("FILE_MODULE_AUDIT_HISTORY_file_module_audit_history_modal_close");
	By auditHistoryModalClosed = By.xpath(".//*[@id='FILE_MODULE_AUDIT_HISTORY' and contains(@style,'none')]");
	By deleteModal = By.id("file_module_deleteFoldDocModal");

	By newWordDocumentName = By.id("file_module_addFile_name_365");
	By office365CommonClose = By.id("Office365Common_close");
	By officeSaveButton = By.id("Office365Common_save");
	By office365Common_TITLE = By.id("Office365Common_TITLE");
	By fileBanner_Upload_Button = By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_UPLOAD_BUTTION + "'])[1]");
	By addFolderLocationModalClose = By.id("file_module_addFolder_locationModal_close");
	By subMenuMoreActionFile = By.xpath(".//*[@class='subDropdwonWrapper']//ul");
	By expandLink = By.xpath(".//*[@id='file_module_addFolder_locationModal_BODY']//a[contains(text(),'Expand ')]");
	By collapseLink = By.xpath(".//*[@id='file_module_addFolder_locationModal_BODY']//a[contains(text(),'Collapse')]");
	By file_module_addFolder_location = By.id("file_module_addFolder_locationModal_BODY");
	By closeForOnlineDocumentCreateModal = By.id("Office365Common_MAIN_CLOSE_BUTTON");
	By addFileTaskList = By.id("addFile_taskListID");

	By watermark = By.xpath(".//*[name()='svg']//*[name()='tspan']");

	By notViewreSupport = By.xpath(".//*[@id='viewerResponseID']//span[normalize-space(.)='No preview available']");

	By tableContent = By.xpath(".//*[@id='file_module_tableContentDivID']//p[@class='fontsize13']");

	By awaitingUserListClose = By.id("AwaitingUserListModalID_awaitingUserListClose");
	By esignList = By.xpath(".//*[@id='file_module_awaiting_esign_list']//td[6]");
	By more = By.xpath(".//*[normalize-space(.)='1 more']");
	By awaitingUser = By.xpath(".//*[@id='AwaitingUserListModalID']//*[@class='modal-content']");
	By awaintingClose = By.xpath(".//*[@id='AwaitingUserListModalID']//*[@class='close']");
	By awaitingUserClose = By.xpath(".//*[@id='AwaitingUserListModalID']//button[normalize-space(.)='Close']");
	By viewerResponse = By.id("viewerResponseID");
	By userinfo = By.xpath(".//*[@class='userinfoDropdown']");
	By userInfoDropDown = By.xpath(".//*[@class='userinfoDropdownHeader']//span");
	By paddBotl = By.xpath("//*[@class='paddBott5']//span");

	By addFieldsForRecipient = By.xpath(".//*[@class='modal_title ng-binding ng-scope'][normalize-space()='Add fields for your recipients']");
	By closeIcon = By.xpath(".//*[@class='icon icon-close modal_close']");
	By addField = By.xpath(".//*[@class='btn btn-lg btn-primary ng-binding'][normalize-space(text())='Add Fields']");
	By sendWithoutFields = By.xpath(".//*[@data-qa='send-without-fields'][normalize-space()='Send Without Fields']");

	By noFilesOrFolder = By.xpath(".//*[@id='showMsg']//*[normalize-space(.)='" + FileLabels.UI_TEXT_NOCONTENTFOUND + "']");

	By recipientAction = By.xpath(".//*[@data-id = 'thirdpartyServiceActionMenu']");
	By recipientActopnDropDown = By.xpath(".//*[@data-id='thirdpartyServiceActionMenu']//following-sibling::*[contains(@class,'dropdown-menu open') and @role='combobox']");
	String recipientActionDropdownList = ".//*[@data-id='thirdpartyServiceActionMenu']//following-sibling::*[contains(@class,'dropdown-menu open') and @role='combobox']//*[@role='option']";
	By addRecipientLink = By.xpath(".//*[@id='addRecipientLink']//a");

	By sidebar = By.xpath(".//*[@class='sidebar-fields ng-scope ng-isolate-scope']");
	By send = By.xpath(".//*[@class='btn btn-main btn-lg ng-scope'][normalize-space(.)='Send']");
	By scopeModal = By.xpath(".//*[@class='ng-scope modal modal-new modal-md']");
	By sendWithoutField = By.xpath(".//*[normalize-space(.)='Send Without Fields']");
	By sentForSingature = By.xpath(".//*[@id='file_module_folderTreeBottomDivId']//*[normalize-space(.)='Sent for signature']");

	By closeBtnRevoke = By.id("file_module_third_party_service_revoke_modal_id_MAIN_CLOSE_BUTTON");

	By closeSignRevoke = By.id("file_module_third_party_service_revoke_modal_id_Close");

	By revokeBody = By.id("file_module_third_party_service_revoke_modal_id_BODY");
	By globalAlertMsg = By.xpath(".//*[@id='globalAlertMessageContainerMsgSpace']");
	By fileLink = By.xpath(".//*[@class='linkblack file_module_awaiting_esign selected']//span[2]");

	By documentPreview = By.id("document_preview_modal_right_main");
	By revokeSigningRequest = By.xpath(".//*[@id='document_preview_modal_right_main']//*[normalize-space(.)='Revoke signing request']");
	By revokeSignRequest = By.xpath(".//*[@class='modal-title'][normalize-space(.)='Revoke sign request?']");
	By revokeModal = By.id("file_module_third_party_service_revoke_modal_id_revoke");
	By globalMsg = By.id("globleMsgForSentFile");

	By tagging = By.id("taggingInterface");
	By siteHeader = By.xpath(".//*[@class='site_header ng-scope']");
	By menuOtherActions = By.xpath(".//*[@olive-menu='menuOtherActions']");
	By fileModuleActionButton = By.id("file_module_action_button");

	By sendRemiderClosebtn = By.id("file_module_third_party_service_remind_modal_id_Close");
	By sendRemiderBody = By.id("file_module_third_party_service_remind_modal_id_BODY");
	By sendRemider = By.xpath(".//*[@class='modal-title'][normalize-space(.)='Send reminder?']");

	By modalHeader = By.xpath(".//*[@id='file_module_deleteFoldDocModal']//*[@class='modal-header']");
	By deleteFoldDoc = By.id("file_module_deleteFoldDocModal_cancel");
	By sendRemiderSendBtn = By.id("file_module_third_party_service_remind_modal_id_send");

	By imagePath = By.xpath(".//*[@class='img']");
	By discardDraft = By.xpath(".//*[@class='modal_header ng-scope']//*[normalize-space(.)='Discard Draft']");
	By discard = By.xpath(".//*[@class='btn btn-primary btn-lg ng-binding'][normalize-space(.)='Discard']");
	By editMessageDialog = By.xpath(".//*[@class='modal-content lg']//*[@class='modal_content']");
	By messageToAll = By.xpath(".//*[@class='modal_title ng-binding'][normalize-space(.)='Message to All Recipients']");
	By CLOSE_SCOPE = By.xpath(".//*[@class='icon icon-close close-x ng-scope']");
	By messageTextArea = By.id("message-text-area");
	By editRecipientModal = By.xpath(".//*[@class='ng-binding'][normalize-space(.)='Edit Recipients']");
	By formGroup = By.xpath(".//*[@class='form_group form_item-iconRight']//input");
	By done = By.xpath(".//*[@id='ng-app']//button[normalize-space(.)='Done']");
	By messageContent = By.xpath(".//*[@class='message_content']");
	By uploadDocument = By.xpath(".//*[@class='modal_title ng-binding'][normalize-space(.)='Upload a Document']");
	By primaryButtonDone = By.xpath(".//*[@class='btn btn-lg btn-primary ng-binding'][normalize-space(.)='Done']");
	By close = By.xpath(".//*[@class='ng-scope modal modal-new modal-xl']//*[@aria-label='Close']");
	By advancedOption = By.xpath(".//*[@class='modal_title ng-binding'][normalize-space(.)='Advanced Options']");
	By closeModal = By.xpath(".//*[@class='icon icon-close modal_close'][@aria-label='Close']");
	By saveOptionOnAdavancedButton = By.xpath(".//*[@class='btn btn-lg btn-primary ng-binding'][normalize-space(.)='Save']");
	By cancel = By.xpath(".//*[@class='btn btn-lg btn-minor ng-binding'][normalize-space(.)='Cancel']");
	By recipientPrivileges = By.xpath(".//*[@class='ng-binding ng-scope'][normalize-space(.)='Recipient Privileges']");
	By reminder = By.xpath(".//*[@class='ng-binding ng-scope'][normalize-space(.)='Reminders']");
	By expiration = By.xpath(".//*[@class='ng-binding ng-scope'][normalize-space(.)='Expiration']");
	By ModalWrap = By.xpath(".//*[@class='modal_wrap']");
	By signature = By.xpath(".//*[@class='sidebar_group ng-scope']//following::*[@class='menu_item u-ellipsis ng-scope']//following::*[normalize-space(text())='Signature']");
	By pageView = By.xpath(".//*[@data-view-name='PageView']");
	By locAdobeRecipientEmail = By.id("recipientEmail-tokenfield");
	By locAdobeRecipientName = By.id("recipientName");
	By locRevokeAndDelete = By.id("file_module_deleteFoldDocModal_okForRevokeDelete");
	By locDocumentView = By.xpath(".//*[@data-view-name='PageView']");
	By locTaggingInterfaceHeader = By.xpath(".//*[@class='header_wrap']");
	By cancelBtnOnTagging = By.xpath(".//*[@id='file_module_tagginginterface_modal_id']//*[@class='icon icon-remove']");
	By unlockModalId = By.xpath(".//*[@id='file_module_third_party_service_unlock_modal_id']//*[@class='modal-content']");
	By unlockModalBody = By.id("file_module_third_party_service_unlock_modal_id_BODY");
	By unlockModalClose = By.id("file_module_third_party_service_unlock_modal_id_Close");
	By unlockModal = By.id("file_module_third_party_service_unlock_modal_id_unlock");
	By locRightmodel = By.id("document_preview_modal_right_main");
	By locStatuslabel = By.id("lblViewerDocumentStatus");

	By tableRows = By.xpath("//*[@id='file_module_mainTableId']//tbody/tr");

	By tableColumns = By.xpath("//*[@id='file_module_mainTableId']//tbody/tr[1]/td");

	By folderListOfTable = By.xpath("//*[@id='file_module_mainTableId']//tr[contains(@id,'fold')]");

	By fileListOfTable = By.xpath("//*[@id='file_module_mainTableId']//tr[contains(@class,'dragfile')]");

	By fileFolderTable = By.id("file_module_mainTableId");

	By locAuditHistoryModel = By.id("FILE_MODULE_AUDIT_HISTORY");
	By deleteFoldDocModalOk = By.id("file_module_deleteFoldDocModal_okForRevokeDelete");

	By addFolderSettingsTab = By.id("file_module_addFolder_settingsTabClick");
	By fileViewSelect = By.name("folder.viewID");

	public DocumentWeb(WebDriver driver)
	{
		// super();
		this.driver = driver;
	}

	public void expandLeftPanel()
	{
		findVisibleElement(leftPanel, Speed.slow);
		WebElement leftPanelVisible = findVisibleElement(showHideLeftPanel);
		if (leftPanelVisible.getAttribute("class").toLowerCase().contains("icon-chevron-right"))
		{
			leftPanelVisible.click();
		}
	}

	/**
	 * Search folder
	 *
	 * @param folderName
	 *        folder name to search
	 * @author dheeraj.rajput
	 */
	@Override
	public void searchFolder(String folderName)
	{
		expandLeftPanel();
		WebElement txtSearchFolder = findVisibleElement(searchFoldersInput);
		txtSearchFolder.clear();
		txtSearchFolder.sendKeys(folderName);
	}

	/**
	 * Verify searched folder availability
	 *
	 * @param folderName
	 *        to verify
	 * @author dheeraj.rajput
	 * @return true if successful
	 */
	@Override
	public boolean verifySearchedFolder(String folderName)
	{
		findVisibleElement(leftPanelSearchTree, Speed.slow);
		boolean noResults = isDisplayed(leftPanel_noResultFound) || !isDisplayed(By.xpath(".//*[@id='folderSearchResult']//*[normalize-space(.)='" + folderName + "']"));
		if (noResults)
		{
			System.err.println("Folder with name [" + folderName + "] is not displayed.");
			return false;
		}
		else
		{
			return isDisplayed(By.xpath(".//*[@id='folderSearchResult']//*[normalize-space(.)='" + folderName + "']"));
		}
	}

	/**
	 * Go to file menu left panel options
	 *
	 * @param option
	 *        left panel menu name to go to.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectLeftPanelFileOptions(String option)
	{
		WebElement opt = null;
		expandLeftPanel();
		By leftPanelOption = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + option.trim() + "']");
		By leftPanelSearchedOption = By.xpath("(//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + option.trim() + "' and contains(@class,'TruncateTxt')])[last()]");

		if (isDisplayed(leftPanel_ClearSearchIcon, Speed.slow))
		{
			opt = findVisibleElement(leftPanelSearchedOption);
		}
		else
		{
			opt = findVisibleElement(leftPanelOption);
		}

		opt.click();
		/*
		 * opt = findClickableElement(By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='"+option.trim()+"']"));
		 * if(opt.isDisplayed())
		 * opt.click();
		 * else
		 * System.err.println(option+" is not displayed");
		 */
	}

	/**
	 * Open Add button option
	 *
	 * @param item
	 *        Add button option to click on.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectItemFromAdd(String item)
	{
		WebElement itemToClick;
		findVisibleElement(fileBanner);
		if (isDisplayed(fileBanner_AddButton, Speed.slow))
		{
			findClickableElement(fileBanner_AddButton, Speed.slow).click();
			findVisibleElement(fileBanner_DropDownExpanded);
			itemToClick = findVisibleElement(By.xpath("(//*[contains(text(),'" + FileLabels.FILES_ADD + "')])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"));
			itemToClick.click();
			findVisibleElement(openedModal);
		}
		else
		{
			try
			{
				selectItemFromUpload(item);
			}
			catch (TimeoutException te)
			{
				selectItemFromNew(item);
			}
		}
	}

	/**
	 * Open Action button option
	 *
	 * @param item
	 *        Action button option to click on.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectItemFromAction(String item)
	{
		WebElement itemToClick = null;
		findVisibleElement(fileBanner);
		WebElement actionButton = findVisibleElement(fileBanner_ActionButton);
		if (isDisplayed(fileBanner_ActionButton) && actionButton.isEnabled())
		{
			actionButton.click();
			itemToClick = findVisibleElement(By.xpath("(//*[contains(text(),'" + FileLabels.FILES_ACTION + "')])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"));
			itemToClick.click();
			if (item.equalsIgnoreCase("download"))
			{
				if (isDisplayed(preparingDownload, Speed.slow))
				{
					while (isDisplayed(preparingDownload))
					{
						;
					}
				}
			}
			findPresentElement(modalContent, Speed.slow);
		}
	}

	/**
	 * Open View button option
	 *
	 * @param item
	 *        View button option to click on.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectItemFromView(String item)
	{
		WebElement itemToClick = null;
		findVisibleElement(fileBanner);
		WebElement viewButton = findClickableElement(fileBanner_ViewButton);
		if (isDisplayed(fileBanner_ViewButton))
		{
			viewButton.click();
			itemToClick = findVisibleElement(By.xpath("(//*[contains(text(),'" + FileLabels.FILES_VIEW + "')])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"));
			itemToClick.click();
		}
	}

	@Override
	public void searchFile(String fileName)
	{
		// String fileExt="";
		if (fileName.contains(" "))
		{
			String firstPart = fileName.substring(0, fileName.indexOf(' ')).trim();

			if (firstPart.matches("[0-9]+(\\.[0-9]+)*"))
			{
				fileName = fileName.substring(fileName.indexOf(' ') + 1).trim();
			}
		}
		findVisibleElement(fileBanner);
		WebElement searchFileInput = findVisibleElement(fileBanner_SearchFileInput);

		searchFileInput.clear();

		if (fileName.contains("."))
		{
			// fileExt = FilenameUtils.getExtension(fileName);
			fileName = fileName.substring(0, fileName.lastIndexOf('.')).trim();
		}

		String fileNameForSearch = fileName.trim();
		searchFileInput.sendKeys(fileNameForSearch);
		while (isDisplayed(fileBanner_SearchFileLoader))
		{
			;
		}
		findVisibleElement(fileBanner_ClearSearch);
	}

	/**
	 * Opens search filter
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void openFilter()
	{
		findVisibleElement(fileBanner);
		if (isDisplayed(search_filterExpanded))
		{
			System.out.println("Search Filter is already expanded");
		}
		else
		{
			findClickableElement(fileBanner_SearchDropDown).click();
		}
		findVisibleElement(search_filterExpanded, Speed.slow);
	}

	/**
	 * Expands search filter option (use it after openFilter())
	 *
	 * @param filterName
	 *        to expand
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandFilterOption(String filterName)
	{
		findVisibleElement(search_filterExpanded);
		WebElement element = findVisibleElement(By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + filterName + "'])[last()]//span"));
		// System.err.println(element.getAttribute("class"));
		if (element.getAttribute("class").toLowerCase().contains("icon-chevron-right"))
		{
			element.click();
		}
	}

	/**
	 * Expands search filter option (use it after openFilter())
	 *
	 * @param filterXpath
	 *        - xpath of the element which has to be expanded
	 * @author dheeraj.rajput
	 */
	public void expandFilterOption(By filterXpath)
	{
		findVisibleElement(search_filterExpanded);
		WebElement element = findVisibleElement(filterXpath);
		// System.err.println(element.getAttribute("class"));
		if (element.getAttribute("class").toLowerCase().contains("icon-chevron-right"))
		{
			element.click();
		}
	}

	@Override
	public void setMultipleFilter(Map<String, Object[]> filterData)
	{
		for (Map.Entry<String, Object[]> entry : filterData.entrySet())
		{
			setFilter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Set file filter
	 *
	 * @param filterName
	 *        to select
	 * @param data
	 *        array of object
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFilter(String filterName, Object data[])
	{
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		findVisibleElement(search_filterExpanded, Speed.slow);
		String trim = filterName.toLowerCase().trim();
		if (FileLabels.FILES_SEARCHFILTER_TAGS.toLowerCase().equals(trim))
		{
			for (int i = 0; i < data.length; i++)
			{
				filterByTags(filterName, data[i].toString());
			}
		}
		else if (FileLabels.FILES_SEARCHFILTER_AUTHOR.toLowerCase().equals(trim))
		{
			for (int i = 0; i < data.length; i++)
			{
				filterByAuthor(filterName, data[i].toString());
			}
		}
		else if (FileLabels.FILES_SEARCHFILTER_FILETYPES.toLowerCase().equals(trim))
		{
			expandFilterOption(filterName);
			for (int i = 0; i < data.length; i++)
			{
				selectFilterCheckbox(data[i].toString(), true);
			}
		}
		else
		{
			for (int i = 0; i < data.length; i++)
			{
				selectFilterCheckbox(filterName, (boolean) data[i]);
			}
		}
	}

	/**
	 * Set filter for Tags
	 *
	 * @param filterName
	 *        to expand
	 * @param tag
	 *        name to select
	 * @author dheeraj.rajput
	 */
	@Override
	public void filterByTags(String filterName, String tag)
	{
		try
		{
			expandFilterOption(filterName);
			WebElement elem = findVisibleElement(search_Tags_SearchTagInput);
			elem.clear();
			elem.sendKeys(tag);
			if (isDisplayed(filterGrayLoader))
			{
				while (isDisplayed(filterGrayLoader))
				{
					;
				}
			}
			if (!isDisplayed(search_noResult))
			{
				selectFilterCheckbox(tag, true);
			}
			else
			{
				throw new Exception(tag + " not found.");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Set filter for Author
	 *
	 * @param filterName
	 *        to expand
	 * @param author
	 *        name to select
	 * @author dheeraj.rajput
	 */
	@Override
	public void filterByAuthor(String filterName, String author)
	{
		try
		{
			expandFilterOption(filterName);
			WebElement elem = findVisibleElement(search_Author_SearchAuthorInput);
			elem.clear();
			elem.sendKeys(author);
			if (isDisplayed(filterGrayLoader))
			{
				while (isDisplayed(filterGrayLoader))
				{
					;
				}
			}
			if (!isDisplayed(search_noResult))
			{
				selectFilterCheckbox(author, true);
			}
			else
			{
				throw new Exception(author + " not found.");// System.err.println(author+" not found.");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Set filter for Author
	 *
	 * @param option
	 *        to select
	 * @param check
	 *        boolean true(check) | false(uncheck)
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectFilterCheckbox(String option, boolean check)
	{
		WebElement element = findVisibleElement(By.xpath("(//*[contains(@class,'searchComp')]/following::label[normalize-space(.)='" + option + "'])[last()]//input"));
		boolean checked = element.isSelected();
		if (checked != check)
		{
			element.click();
		}
	}

	/**
	 * Clear all file filters
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clearFilters()
	{
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		findVisibleElement(search_filterExpanded, Speed.slow);
		findClickableElement(search_ClearFilters).click();

	}

	/**
	 * Verify cleared filters
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAllFitersCleared()
	{
		boolean success = false;
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		findVisibleElement(search_filterExpanded, Speed.slow);
		findClickableElement(search_ClearFilters).click();
		success = verifyAllFileTypeFiltersCleared();
		success = verifyAllTagsFiltersCleared();
		success = verifyAllAuthorFiltersCleared();
		int size = driver.findElements(search_BottomCheckboxes).size();

		if (!isDisplayed(search_noResult) && success)
		{
			for (int i = 1; i <= size; i++)
			{
				WebElement fileType = findVisibleElement(By.xpath("//*[contains(@class,'dropAccord')]//*[@class='divider'][1]/following::*[@class='checkbox'][" + i + "]"));
				boolean checked = fileType.isSelected();
				if (!checked)
				{
					success = true;
				}
			}
		}
		else if (!success)
		{
			success = false;
		}
		else
		{
			System.out.println(findVisibleElement(search_noResult).getText());
			success = true;
		}
		return success;
	}

	/**
	 * Verify all file type filters are cleared
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAllFileTypeFiltersCleared()
	{
		boolean success = false;
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		expandFilterOption(search_FileTypesArrow);
		while (isDisplayed(filterGrayLoader))
		{
			;
		}
		findVisibleElement(search_FileTypes_allCheckBoxes, Speed.slow);
		int size = driver.findElements(search_FileTypes_allCheckBoxes).size();
		if (!isDisplayed(search_noResult))
		{
			for (int i = 1; i <= size; i++)
			{
				WebElement fileType = findVisibleElement(By.xpath("//*[@id='file_module_docExtensionListDivID']//div[@class='checkbox'][" + i + "]//input"));
				findVisibleElement(By.xpath("//*[@id='file_module_docExtensionListDivID']//div[@class='checkbox'][" + i + "]//label"));
				boolean checked = fileType.isSelected();
				if (!checked)
				{
					success = true;
				}
			}
		}
		else
		{
			System.out.println(findVisibleElement(search_noResult).getText());
			success = true;
		}
		return success;
	}

	/**
	 * Verify all Tags filters are cleared
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAllTagsFiltersCleared()
	{
		boolean success = false;
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		expandFilterOption(search_TagsArrow);
		while (isDisplayed(filterGrayLoader))
		{
			;
		}
		if (isDisplayed(search_TagsInputCloseIcon))
		{
			findVisibleElement(search_TagsInputCloseIcon, Speed.slow).click();
		}
		findVisibleElement(search_Tags_allCheckBoxes, Speed.slow);
		int size = driver.findElements(search_Tags_allCheckBoxes).size();
		if (!isDisplayed(search_noResult))
		{
			for (int i = 1; i <= size; i++)
			{
				WebElement fileType = findVisibleElement(By.xpath("//*[@id='file_module_docTagListDivID']//div[@class='checkbox'][" + i + "]//input"));
				findVisibleElement(By.xpath("//*[@id='file_module_docTagListDivID']//div[@class='checkbox'][" + i + "]//label"));
				boolean checked = fileType.isSelected();
				if (!checked)
				{
					success = true;
				}
			}
		}
		else
		{
			System.out.println("Tags: " + findVisibleElement(search_noResult).getText());
			success = true;
		}
		return success;
	}

	/**
	 * Verify all Author filters are cleared
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAllAuthorFiltersCleared()
	{
		boolean success = false;
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		expandFilterOption(search_AuthorArrow);
		while (isDisplayed(filterGrayLoader))
		{
			;
		}
		if (isDisplayed(search_AuthorInputCloseIcon))
		{
			findVisibleElement(search_AuthorInputCloseIcon, Speed.slow).click();
		}
		findVisibleElement(search_Author_allCheckBoxes, Speed.slow);
		int size = driver.findElements(search_Author_allCheckBoxes).size();
		if (!isDisplayed(search_noResult))
		{
			for (int i = 1; i <= size; i++)
			{
				WebElement fileType = findVisibleElement(By.xpath("//*[@id='file_module_docAuthorListDivID']//div[@class='checkbox'][" + i + "]//input"), Speed.slow, 3);
				findVisibleElement(By.xpath("//*[@id='file_module_docAuthorListDivID']//div[@class='checkbox'][" + i + "]//label"));
				boolean checked = fileType.isSelected();
				if (!checked)
				{
					success = true;
				}
			}
		}
		else
		{
			System.out.println("Author: " + findVisibleElement(search_noResult).getText());
			success = true;
		}
		return success;
	}

	/**
	 * Open Advanced Search options from File filters
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void openAdvancedSearch()
	{
		if (!isDisplayed(search_filterExpanded))
		{
			openFilter();
		}
		findVisibleElement(search_filterExpanded, Speed.slow);
		findClickableElement(search_AdvancedSearch).click();
		findPresentElement(advancedSearch_Modal);
	}

	/**
	 * Advanced Search operations
	 *
	 * @param Map<String,
	 *        String> map
	 *        Key: Advanced search labels, Value: String values to input or select
	 * @author dheeraj.rajput
	 */
	@Override
	public void advancedSearch(Map<String, String> map)
	{
		if (!isDisplayed(advancedSearch_Modal))
		{
			openAdvancedSearch();
		}
		try
		{
			for (Map.Entry<String, String> dataMap : map.entrySet())
			{
				String key = dataMap.getKey().toLowerCase();
				String value = dataMap.getValue();
				System.out.println(key);
				if (FileLabels.FILES_ADVANCEDSEARCH_ANYOFTHESEWORDS.toLowerCase().equals(key))
				{
					sendAnyOfTheseWords(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_ALLOFTHESEWORDS.toLowerCase().equals(key))
				{
					sendAllOfTheseWords(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_EXCLUDETHESEWORDS.toLowerCase().equals(key))
				{
					sendExcludeTheseWords(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_TITLE.toLowerCase().equals(key))
				{
					sendTitle(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_CONTENTTYPE.toLowerCase().equals(key))
				{
					selectContentType(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_FILETYPE.toLowerCase().equals(key))
				{
					selectFileType(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_FOLDERS.toLowerCase().equals(key))
				{
					/** basic implementation of selecting All and choose; requires implementation for choose options */
					selectFolders(value);
				}
				else if (FileLabels.FILES_ADVANCEDSEARCH_LASTMODIFIEDDATE.toLowerCase().equals(key))
				{
					selectLastModifiedDate(value);
				}
				else if (FileLabels.FILES_SEARCHFILTER_AUTHOR.toLowerCase().equals(key))
				{
					sendAuthor(value);
				}
				else if (FileLabels.FILES_SEARCHFILTER_TAGS.toLowerCase().equals(key))
				{
					sendTags(value);
				}
				else
				{
					throw new Exception("Advanced Search option not available ");
				}
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		findVisibleElement(footer_Search).click();
	}

	/**
	 * Advanced Search - input "Any of these words"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendAnyOfTheseWords(String value)
	{
		if (isDisplayed(advancedSearch_AnyOfTheseWordsInput))
		{
			;
		}
		WebElement anyOfThese = findVisibleElement(advancedSearch_AnyOfTheseWordsInput);
		anyOfThese.clear();
		anyOfThese.sendKeys(value);
	}

	/**
	 * Advanced Search - input "All of these keywords"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendAllOfTheseWords(String value)
	{
		if (isDisplayed(advancedSearch_AllOfTheseWordsInput))
		{
			;
		}
		WebElement allOfThese = findVisibleElement(advancedSearch_AllOfTheseWordsInput);
		allOfThese.clear();
		allOfThese.sendKeys(value);
	}

	/**
	 * Advanced Search - input "Exclude these keywords"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendExcludeTheseWords(String value)
	{
		if (isDisplayed(advancedSearch_ExcludeTheseWordsInput))
		{
			;
		}
		WebElement excludeThese = findVisibleElement(advancedSearch_ExcludeTheseWordsInput);
		excludeThese.clear();
		excludeThese.sendKeys(value);
	}

	/**
	 * Advanced Search - input "Title"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendTitle(String value)
	{
		if (isDisplayed(advancedSearch_TitleInput))
		{
			;
		}
		WebElement title = findVisibleElement(advancedSearch_TitleInput);
		title.clear();
		title.sendKeys(value);
	}

	/**
	 * Advanced Search - select "Content Type"
	 *
	 * @param value
	 *        to select.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectContentType(String value)
	{
		if (isDisplayed(advancedSearch_ContentTypeDrpDwn))
		{
			;
		}
		WebElement contentTypeDrpDwn = findVisibleElement(advancedSearch_ContentTypeDrpDwn);
		contentTypeDrpDwn.click();
		WebElement contentType = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_CONTENTTYPE + "'])[last()]/following-sibling::*//*[@data-toggle='dropdown']/following-sibling::*//*[contains(text(),'" + value.trim() + "')]//input"));
		contentType.click();
	}

	/**
	 * Advanced Search - select "File Type"
	 *
	 * @param value
	 *        to select.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectFileType(String value)
	{
		try
		{
			if (isDisplayed(advancedSearch_FileTypeDrpDwn))
			{
				;
			}
			findVisibleElement(advancedSearch_FileTypeDrpDwn).click();
			WebElement elem = findVisibleElement(advancedSearch_FileType_searchInput);
			elem.clear();
			elem.sendKeys(value);
			while (isDisplayed(filterGrayLoader))
			{
				;
			}
			if (!isDisplayed(search_noResult))
			{
				selectFilterCheckbox(value, true);
			}
			else
			{
				throw new Exception(value + " not found.");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Advanced Search - select "Folders"
	 *
	 * @param value
	 *        to select.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectFolders(String value)
	{
		if (isDisplayed(advancedSearch_FoldersDrpDwn))
		{
			;
		}
		WebElement foldersDrpDwn = findVisibleElement(advancedSearch_FoldersDrpDwn);
		foldersDrpDwn.click();
		WebElement folderType = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + FileLabels.FILES_ADVANCEDSEARCH_FOLDERS + "'])[last()]/following-sibling::*//*[@data-toggle='dropdown']/following-sibling::*//*[contains(text(),'" + value.trim() + "')]"));
		folderType.click();
	}

	/**
	 * Advanced Search - select "Last Modified Date"
	 *
	 * @param value
	 *        to select.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectLastModifiedDate(String value)
	{
		if (isDisplayed(advancedSearch_LastModifiedDateDrpDwn))
		{
			;
		}
		WebElement lastModifiedDateDrpDwn = findVisibleElement(advancedSearch_LastModifiedDateDrpDwn);
		Select se = new Select(lastModifiedDateDrpDwn);
		se.selectByVisibleText(value);
	}

	/**
	 * Advanced Search - input "Author"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendAuthor(String value)
	{
		if (isDisplayed(advancedSearch_AuthorInput))
		{
			;
		}
		WebElement author = findVisibleElement(advancedSearch_AuthorInput);
		author.clear();
		author.sendKeys(value);
	}

	/**
	 * Advanced Search - input "Tags"
	 *
	 * @param value
	 *        to send.
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendTags(String value)
	{
		if (isDisplayed(advancedSearch_TagsInput))
		{
			;
		}
		WebElement tags = findVisibleElement(advancedSearch_TagsInput);
		tags.clear();
		tags.sendKeys(value);
	}

	/**
	 * Delete all documents
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void deleteAllDocs()
	{
		if (!isDisplayed(noFilesFolder, Speed.slow))
		{
			findVisibleElement(selectAllChkbox, Speed.slow).click();
			selectItemFromAction(FileLabels.FILES_COMMON_DELETE);
			findVisibleElement(deleteMsg);
			findClickableElement(deleteModal_Delete).click();
			// findVisibleElement(noFilesFolder,30);
			while (isDisplayed(deleting))
			{
				;
			}
			findVisibleElement(leftPanel);
		}
	}

	// This methods Upload a New File.[Migrated]
	/**
	 * Add File
	 *
	 * @param addDoc
	 *        file details - filename, notification,file desclaimer, tags
	 * @param taskAddDataPage
	 *        task details
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 */
	@Override
	public void addFile(DocumentAddDataPage addDoc, TaskAddDataPage taskAddDataPage) throws IOException, InterruptedException
	{
		String fileuploadpath = addDoc.getFileuploadpath();
		DocumentNotifications documentNotifications = addDoc.getDocumentNotifications();
		String filedisclaimer = addDoc.getFiledisclaimer();
		String tag = addDoc.getTag();
		String versionNote = addDoc.getVersionNotes();
		findVisibleElement(addFiles_FilesTab).click();
		if (fileuploadpath != null)
		{
			uploadFile(addFiles_Browse, fileuploadpath);
		}
		if (documentNotifications != null)
		{
			setFileNotification(documentNotifications);
		}

		if (filedisclaimer != null)
		{
			addFileDesclaimer(filedisclaimer);
		}
		if (tag != null)
		{
			addFileTags(tag);
		}
		if (versionNote != null)
		{
			sendTextInVersionNotesTextBox(versionNote);
		}

		if (taskAddDataPage != null)
		{
			addFileTask(taskAddDataPage);
			findVisibleElement(addFileTaskList, Speed.slow);
		}
	}

	// This methods Edit File Name.[Migrated]
	/**
	 * Goto edit file.
	 */
	@Override
	public void gotoEditFile(String fileuploadpath, String fileName)
	{
		if (fileuploadpath.contains("/"))
		{
			String file[] = fileuploadpath.split("/");
			fileuploadpath = file[1];
		}
		gotoMoreActions(fileuploadpath, DocFolderOperation.Edit_details.toString().replace("_", " "));
		if (isDisplayed(editFile_Title, Speed.slow))
		{
			findClickableElement(editFile_Title).sendKeys(fileName);
		}
		if (isDisplayed(editFile_Save))
		{
			findClickableElement(editFile_Save).click();
		}
		else
		{
			findClickableElement(save).click();
		}
		try
		{
			if (!verifyDocumentUploaded(fileName))
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * verifyDocumentUploaded. [Migrated]
	 *
	 * @param docName
	 *        the doc name
	 * @return true, if successful
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean verifyDocumentUploaded(String docName)
	{
		By document = By.xpath("(.//*[@id='file_module_mainTableId']//*[text()='" + docName.trim() + "'])[last()]");
		return isDisplayed(document, Speed.slow);
	}

	@Override
	public void gotoMoreActions(String file, String operation)
	{
		searchFile(file);
		By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim() + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
		findClickableElement(moreOption, Speed.slow).click();

		By oprationXpath = By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation.trim() + "'])[1]");

		findPresentElement(moreOptionExpanded, Speed.slow);
		if (isDisplayed(oprationXpath))
		{
			findClickableElement(oprationXpath).click();
		}
		while (isDisplayed(downloading, Speed.slow))
		{
			;
		}
	}

	/**
	 * @param docName
	 *        the doc name
	 * @param newVersionName
	 *        the new version name
	 * @param taskAddDataPage
	 *        the task add data page
	 * @return true, if successful
	 *         replaced
	 *         if (waitAndFindElement(driver,By.xpath(".//*[@id='dndTaskAddEditAttachmentsID']//div[normalize-space(.)='"+ taskAddDataPage.getAttachment() + "']")).isDisplayed()
	 *         && waitAndFindElement(driver,By.xpath(".//*[@id='dndTaskAddEditAttachmentsID']//span[@class='greyMeta']")).isDisplayed()
	 *         && waitAndFindElement(driver,By.xpath(".//*[@id='dndTaskAddEditAttachmentsID']//img")).isDisplayed() && waitAndFindElement(driver,
	 *         By.xpath(".//*[@id='addAnotherID']//label[normalize-space(.)='Add another task']")).isDisplayed())
	 *         with
	 *         if (waitAndFindElement(driver,By.xpath(".//*[@id='taskAttachmentsListID']//div[normalize-space(.)='"+ taskAddDataPage.getAttachment() + "']")).isDisplayed())
	 */
	@Override
	public boolean uploadDocOnAddNewVersionWin(String docName, String newVersionName, TaskAddDataPage taskAddDataPage)
	{
		System.out.println("Doc name : " + docName);

		try
		{
			uploadDoc_onAddNewVersionWin(docName, newVersionName, null);
			if (taskAddDataPage != null)
			{
				if (isDisplayed(newVer_TasksTab))
				{
					findVisibleElement(newVer_TasksTab).click();
					findVisibleElement(newVer_TasksTab_Addbtn).click();
					findPresentElement(addtaskModal);
					TasksWeb taskHomePage = new TasksWeb(driver);
					taskHomePage.addTask(taskAddDataPage);
					if (isDisplayed(By.xpath(".//*[@id='taskAttachmentsListID']//div[normalize-space(.)='" + taskAddDataPage.getAttachment().trim() + "']")))
					{
						taskHomePage.clickOnAddTaskButtonInModel();

						if (isDisplayed(By.xpath(".//*[starts-with(@id,'taskID')]//a[normalize-space(.)='" + taskAddDataPage.getTaskTitle() + "']"))
								&& isDisplayed(By.xpath(".//*[@id='addFileVersion_taskListID']//a[contains(@class,'icon-remove')]"))
								&& isDisplayed(By.xpath(".//*[@id='addFileVersion_taskListID']//a[normalize-space(.)='" + taskAddDataPage.getAssignee() + "']")))
						{
							findClickableElement(newVer_Addbtn).click();
							return true;
						}

					}
				}
			}
			else
			{
				findClickableElement(newVer_Addbtn).click();
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Used in Multiple Packages.
	 *
	 * @param docName
	 *        the doc name
	 * @param newVersionName
	 *        the new version name
	 * @param versionNote
	 *        TODO
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@Override
	public void uploadDoc_onAddNewVersionWin(String docName, String newVersionName, String versionNote) throws IOException
	{
		System.out.println("Doc name : " + docName);

		if ((docName != null))
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + docName + "").getCanonicalPath().trim();
			findPresentElement(newVer_Details_Browse).sendKeys(path);
			findVisibleElement(newVer_Details_Title).clear();
			findVisibleElement(newVer_Details_Title).sendKeys(newVersionName);
			if (versionNote != null && !versionNote.equals(""))
			{
				findVisibleElement(newVer_Details_VersionNotes).sendKeys(versionNote);
			}
			findVisibleElement(btnDone);
		}
	}

	/**
	 * Upload File
	 *
	 * @param fileName
	 *        the doc name
	 */
	@Override
	public void uploadFile(By browseInpXpath, String fileuploadpath)
	{
		String path = "";
		if (fileuploadpath.endsWith(".exe"))
		{
			try
			{
				path = new File(TestBaseSetup.driverPath + fileuploadpath + "").getCanonicalPath().trim();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileuploadpath + "").getCanonicalPath().trim();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.err.println("Path "+path);
		WebElement browseFile = findPresentElement(browseInpXpath, Speed.slow);
		browseFile.sendKeys(path);
		By progressDone = By.xpath("(//*[contains(@class,'overflowHidden')]//*[normalize-space(text())='" + fileuploadpath.trim() + "']/preceding-sibling::*//span[contains(text(),'" + FileLabels.FILES_COMMON_DONE + "')])[last()]");
		findVisibleElement(progressDone, 90, 200);
	}

	/**
	 * Set File Notification
	 *
	 * @param documentNotifications
	 *        notification type
	 */
	@Override
	public void setFileNotification(DocumentNotifications documentNotifications)
	{
		if (isDisplayed(notificationBell))
		{
			findVisibleElement(notificationBell).click();
			findVisibleElement(By.xpath(".//*[@class='modal-footer']//ul/li[normalize-space(.)='" + documentNotifications.toString().replace("_", " ") + "']")).click();
		}
	}

	/**
	 * Set File disclaimer
	 *
	 * @param filedisclaimer
	 *        file disclaimer to add
	 */
	@Override
	public void addFileDesclaimer(String filedisclaimer)
	{
		// findClickableElement(addFiles_MetadataTab).click();
		gotoMetadataTab();
		if (!findClickableElement(desclaimerCheckbox).isSelected())
		{
			findClickableElement(desclaimerCheckbox).click();
		}
		WebElement desclaimerInput = findVisibleElement(desclaimerText);
		desclaimerInput.clear();
		desclaimerInput.sendKeys(filedisclaimer);
	}

	/**
	 * Set File tags
	 *
	 * @param tag
	 *        tag name
	 */
	@Override
	public void addFileTags(String tag)
	{
		WebElement element = null;
		gotoMetadataTab();
		// findClickableElement(addFiles_MetadataTab).click();
		if (!verifyTag(tag))
		{
			StringTokenizer s = new StringTokenizer(tag, ",");
			// for Multiple tag
			while (s.hasMoreTokens())
			{
				element = driver.findElement(addFiles_MetadataTab_TagsInput);
				element.sendKeys(s.nextToken());
				element.sendKeys(Keys.ENTER);
			}
			element.sendKeys(Keys.TAB);
		}
	}

	/**
	 * Add File Task
	 *
	 * @param taskAddDataPage
	 *        task details
	 */
	@Override
	public void addFileTask(TaskAddDataPage taskAddDataPage)
	{
		try
		{
			gotoTasksTab();
			TasksPage tasksPage = clickAddButtonInTasksTab();
			findVisibleElement(addtaskModal, Speed.slow);
			tasksPage.addTask(taskAddDataPage);
			tasksPage.clickOnAddTaskButtonInModel();
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * Edit File details
	 *
	 * @param fileName
	 *        of the document
	 * @param editDetails
	 *        Map containing(Key: Field Name , Value: to edit)
	 * @author dheeraj.rajput
	 */
	@Override
	public void editFileDetails(String fileName, Map<String, String> editDetails)
	{
		if (fileName.contains("/"))
		{
			String file[] = fileName.split("/");
			fileName = file[1];
		}
		if (!isDisplayed(editFileModal))
		{
			gotoMoreActions(fileName, DocFolderOperation.Edit_details.toString().replace("_", " "));
		}
		findVisibleElement(editFileModal, Speed.slow);
		findVisibleElement(editFile_DetailsTab, Speed.slow).click();
		for (Map.Entry<String, String> entry : editDetails.entrySet())
		{
			String fieldToEdit = entry.getKey();
			String valueToSend = entry.getValue();
			String trim = fieldToEdit.trim().toLowerCase().trim();
			if (FileLabels.FILES_EDIT_TITLE.toLowerCase().equals(trim))
			{
				WebElement titleInput = findVisibleElement(editFile_Title);
				titleInput.clear();
				titleInput.sendKeys(valueToSend);
			}
			else if (FileLabels.FILES_EDIT_TAGS.toLowerCase().equals(trim))
			{
				removeAllTags();
				addTags(editFile_tags, valueToSend);
			}
			else if (FileLabels.FILES_EDIT_DISCLAIMERTEXT.toLowerCase().equals(trim))
			{
				addDesclaimer(desclaimerCheckbox, desclaimerText, valueToSend);
			}

		}
	}

	/**
	 * Remove all visible tags
	 */
	@Override
	public void removeAllTags()
	{
		int size = driver.findElements(allTags).size();
		for (int i = 1; i <= size; i++)
		{
			if (isDisplayed(removeTag))
			{
				findVisibleElement(removeTag).click();
			}
			else
			{
				System.out.println("No tags for removal.");
			}
		}
	}

	/**
	 * Verify input text matches required text
	 *
	 * @param elementXpath
	 *        element to get text from
	 * @param textToVerify
	 *        string value to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	public boolean verifyInputText(By elementXpath, String textToVerify)
	{
		WebElement elem = findVisibleElement(elementXpath);
		String elemText = "";
		try
		{
			elemText = ((JavascriptExecutor) driver).executeScript("return arguments[0].value", elem).toString().trim();
		}
		catch (Exception e)
		{
			elemText = elem.getText().trim();
		}
		return elemText.equals(textToVerify.trim());
	}

	/**
	 * Verify tag value matches with the string argument
	 *
	 * @param tagValue
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyTag(String tagValue)
	{
		List<WebElement> tagList = driver.findElements(allTags);
		for (WebElement tag : tagList)
		{
			if (tag.getText().trim().equalsIgnoreCase(tagValue.trim()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Verify Edit file title
	 *
	 * @param titleToVerify
	 *        title string to verify
	 * @return true
	 *         if successful.
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyEditFileTitle(String titleToVerify)
	{
		if (titleToVerify.contains("."))
		{
			titleToVerify = titleToVerify.substring(0, titleToVerify.indexOf("."));
		}
		return verifyInputText(editFile_Title, titleToVerify);
	}

	/**
	 * Verify Edit file disclaimer
	 *
	 * @param titleToVerify
	 *        title string to verify
	 * @return true
	 *         if successful.
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDisclaimerText(String textToVerify)
	{
		return verifyInputText(desclaimerText, textToVerify);
	}

	/**
	 * Click on cancel in modal that is open
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCancel()
	{
		findVisibleElement(footer_Cancel, Speed.slow).click();
	}

	/**
	 * @param classifierName
	 * @author tejash.trivedi
	 */
	@Override
	public void searchClassifier(String classifierName)
	{
		WebElement txtSearchClassifier = findVisibleElement(classifierSearchTextbox);
		txtSearchClassifier.clear();
		txtSearchClassifier.sendKeys(classifierName);
		findPresentElement(classifierClearSearchTextbox, Speed.slow);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnClassifierSearchRemoveButton()
	{
		findVisibleElement(classifierClearSearchTextbox, Speed.slow).click();
	}

	/**
	 * @param folderName
	 * @author tejash.trivedi
	 */
	@Override
	public void addFolderNameAddFolderModal(String folderName)
	{
		WebElement txtSearchFolder = findVisibleElement(folder_Details_NameInput);
		txtSearchFolder.clear();
		txtSearchFolder.sendKeys(folderName);
	}

	/**
	 * @param classifierName
	 * @author tejash.trivedi
	 */
	@Override
	public void addClassifierNameNewClassifierModal(String classifierName)
	{
		WebElement txtSearchFolder = findVisibleElement(newClassifierModalNameField);
		txtSearchFolder.clear();
		txtSearchFolder.sendKeys(classifierName);
	}

	/**
	 * @param description
	 * @author tejash.trivedi
	 */
	@Override
	public void addClassifierDescriptionNewClassifierModal(String description)
	{
		WebElement txtSearchFolder = findVisibleElement(newClassifierModalDescriptionField);
		txtSearchFolder.clear();
		txtSearchFolder.sendKeys(description);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickCancelAddFolderModal()
	{
		findPresentElement(cancelButtonAddFolderModal, Speed.slow);
		findVisibleElement(cancelButtonAddFolderModal, Speed.slow).click();
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickCancelClassifiersModal()
	{
		findPresentElement(cancelButtonClassifiersModal, Speed.slow);
		findVisibleElement(cancelButtonClassifiersModal, Speed.slow).click();
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickAddButtonNewClassifiersModal()
	{
		findVisibleElement(addButtobNewClassifiersModal, Speed.slow).click();
		findPresentElement(classifiersModal, Speed.slow);
	}

	/**
	 * Click add In Add File Modal
	 */
	@Override
	public void clickAddInAddFileModal()
	{
		findVisibleElement(addFiles_Add, Speed.slow).click();
		if (isDisplayed(uploadingLoader))
		{
			while (isDisplayed(uploadingLoader))
			{
				;
			}
		}
		findVisibleElement(globalSuccessMsg, 90);
	}

	/**
	 * Click save in modal which is open
	 */
	@Override
	public void clickSaveInModal()
	{
		findClickableElement(editFile_Save).click();
		if (isDisplayed(editFile_ErrorMsg))
		{
			System.err.println(findVisibleElement(editFile_ErrorMsg).getText());
		}
	}

	/**
	 * Upload Zip File
	 *
	 * @param fileName
	 *        of the document
	 * @param metadata
	 *        Map containing(Key: Field Name , Value: to send)
	 * @author dheeraj.rajput
	 */
	@Override
	public void addZipFile(String fileName, Map<String, String> metadata)
	{
		findVisibleElement(openedModal, Speed.slow);
		findVisibleElement(addZippedFile_FileTab, Speed.slow).click();
		uploadFile(zipFile_FileTab_BrowseInput, fileName);
		if (!metadata.isEmpty() || metadata != null)
		{
			// findVisibleElement(addFiles_MetadataTab).click();
			gotoMetadataTab();
			for (Map.Entry<String, String> entry : metadata.entrySet())
			{
				String fieldToEdit = entry.getKey();
				String valueToSend = entry.getValue();
				String lowerCase = fieldToEdit.trim().toLowerCase();
				if (FileLabels.FILES_ZIPFILE_METADATATAB_TAGS.toLowerCase().equals(lowerCase))
				{
					addTags(zipFile_MetadataTab_TagsInput, valueToSend);
				}
				else if (FileLabels.FILES_ZIPFILE_METADATATAB_DISCLAIMER.toLowerCase().equals(lowerCase))
				{
					addDesclaimer(zipFile_MetadataTab_DesclaimerCheckbox, zipFile_MetadataTab_DesclaimerInput, valueToSend);
				}
				else if (FileLabels.FILES_ZIPFILE_METADATATAB_FILEVIEW.toLowerCase().equals(lowerCase))
				{
					dropDown = new Select(findVisibleElement(zipFile_MetadataTab_FileViewDropDown));
					dropDown.selectByVisibleText(valueToSend);
				}
				else
				{
					System.out.println(fieldToEdit + " is not a correct choice.");
				}
			}
		}
	}

	/**
	 * Click on Add button visible modal/window
	 */
	@Override
	public void clickAddInModal()
	{
		findVisibleElement(footer_Add, Speed.slow).click();
		while (isDisplayed(uploadingLoader))
		{
			;
		}
	}

	/**
	 * Verify folder is present
	 *
	 * @param folderName
	 *        to verify
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyFolder(String folderName)
	{
		By folderXpath = By.xpath(".//*[@id='file_module_mainTableId']//*[text()='" + folderName.trim() + "']/../../preceding-sibling::*[1]//*[@data-original-title='Folder']");
		return isDisplayed(folderXpath);
	}

	/**
	 * Clear searched Item
	 */
	@Override
	public void clearSearchedItem()
	{
		findVisibleElement(fileBanner_SearchFileInput);
		findVisibleElement(fileBanner_ClearSearch).click();
	}

	/**
	 * Add placeholder file details(Placeholder -> Details tab)
	 *
	 * @param placeHolderDetails
	 *        Map containing(Key: Field Name , Value: to send)
	 * @author dheeraj.rajput
	 */
	@Override
	public void addPlaceHolderFileDetails(Map<String, String> placeHolderDetails)
	{
		findVisibleElement(addFilesModal, Speed.slow);
		// findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(editFile_DetailsTab, Speed.slow).click();
		if (!placeHolderDetails.isEmpty() || placeHolderDetails != null)
		{
			for (Map.Entry<String, String> entry : placeHolderDetails.entrySet())
			{
				String fieldToEdit = entry.getKey();
				String valueToSend = entry.getValue();
				String lowerCase = fieldToEdit.trim().toLowerCase();
				if (FileLabels.FILES_PLACEHOLDERFILE_TAGS.toLowerCase().equals(lowerCase))
				{
					addTags(zipFile_MetadataTab_TagsInput, valueToSend);
				}
				else if (FileLabels.FILES_PLACEHOLDERFILE_DISCLAIMER.toLowerCase().equals(lowerCase))
				{
					addDesclaimer(zipFile_MetadataTab_DesclaimerCheckbox, placeHolder_DisclaimerInput, valueToSend);
				}
				else if (FileLabels.FILES_PLACEHOLDERFILE_NAME.toLowerCase().equals(lowerCase))
				{
					WebElement nameInput = findVisibleElement(placeHolder_DetailsTab_NameInput);
					nameInput.clear();
					nameInput.sendKeys(valueToSend);
				}
				else if (FileLabels.FILES_PLACEHOLDERFILE_VERSIONNOTES.toLowerCase().equals(lowerCase))
				{
					WebElement versionNoteInput = findVisibleElement(placeHolder_DetailsTab_VersionNotes);
					versionNoteInput.clear();
					versionNoteInput.sendKeys(valueToSend);
				}
				else
				{
					System.out.println(fieldToEdit + " is not a correct choice.");
				}
			}
		}
	}

	/**
	 * Verify place holder file is present
	 *
	 * @param fileName
	 *        to verify
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyPlaceHolderFile(String fileName)
	{
		By folderXpath = By.xpath(".//*[@id='file_module_mainTableId']//*[text()='" + fileName.trim() + ".placeholder']/../../preceding-sibling::*[1]//*[@data-original-title='placeholder']");
		return isDisplayed(folderXpath, Speed.slow);
	}

	/**
	 * Input tag
	 *
	 * @param tagInputXpath
	 *        xpath of the input box
	 * @param tag
	 *        to send
	 */
	public void addTags(By tagInputXpath, String tag)
	{
		WebElement tagInput = findVisibleElement(tagInputXpath);
		tagInput.sendKeys(tag);
		tagInput.sendKeys(Keys.TAB);
	}

	/**
	 * Input disclaimer text
	 *
	 * @param disclaimerCheckBoxXpath
	 * @param disclaimerCheckBoxXpath
	 * @param disclaimerText
	 */
	public void addDesclaimer(By disclaimerCheckBoxXpath, By disclaimerInputXpath, String disclaimerText)
	{
		WebElement desclaimerChk = findVisibleElement(disclaimerCheckBoxXpath, Speed.slow);
		if (!desclaimerChk.isSelected())
		{
			desclaimerChk.click();
		}
		WebElement desclaimerInput = findVisibleElement(disclaimerInputXpath, Speed.slow);
		desclaimerInput.clear();
		desclaimerInput.sendKeys(disclaimerText.trim());
	}

	/**
	 * Add meta data
	 *
	 * @param metaFieldName
	 *        field name to in which metada data has to be sent
	 * @param dataToSend
	 *        data that needs to be sent
	 * @author dheeraj.rajput
	 */
	@Override
	public void addMetadata(String metaFieldName, String dataToSend)
	{
		// findVisibleElement(addFiles_MetadataTab).click();
		gotoMetadataTab();
		By metaInputBox = By.xpath("(//*[normalize-space(text())='" + metaFieldName.trim() + "']/ancestor::*[1]//*[@type='text'])[last()]");
		By metaTextAreaBox = By.xpath("(//*[normalize-space(text())='" + metaFieldName.trim() + "']/ancestor::*[1]//textarea)[last()]");
		WebElement metaField;
		if (isDisplayed(metaInputBox))
		{
			metaField = findVisibleElement(metaInputBox);
			metaField.clear();
			metaField.sendKeys(dataToSend);
		}
		else if (isDisplayed(metaTextAreaBox))
		{
			metaField = findVisibleElement(metaTextAreaBox);
			metaField.clear();
			metaField.sendKeys(dataToSend);
		}
		else
		{
			System.err.println(metaFieldName + " is not displayed");
		}
	}

	/**
	 * Search and click on file name to open file preview
	 *
	 * @param fileName
	 *        to click
	 * @author dheeraj.rajput
	 */
	@Override
	public void previewFile(String fileName)
	{
		searchFile(fileName);
		WebElement document = findVisibleElement(By.xpath("(.//*[@id='file_module_mainTableId']//*[text()='" + fileName.trim() + "'])[last()]"), Speed.slow);
		document.click();
	}

	/**
	 * Verify disclaimer
	 *
	 * @param disclaimer
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAndAcceptDisclaimer(String disclaimer)
	{
		findPresentElement(modalContent, Speed.slow);
		WebElement disclaimerBody = findVisibleElement(disclaimerText, Speed.slow);
		if (disclaimerBody.getText().trim().equals(disclaimer.trim()))
		{
			findVisibleElement(footer_Accept).click();
			return true;
		}
		return false;
	}

	/**
	 * Verify file title in file Preview page
	 *
	 * @param fileTitle
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyPreviewFileTitle(String fileTitle)
	{
		// while (isDisplayed(previewPage_FileLoader));
		findVisibleElement(previewPageHeader_CloseIcon, Speed.slow);
		String fetchedTitle = findVisibleElement(previewPageHeader_FileName, Speed.slow).getText().trim();
		return fetchedTitle.equals(fileTitle.trim());
	}

	/**
	 * Verify tag in file Preview page
	 *
	 * @param tag
	 *        tag name to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyPreviewTag(String tag)
	{
		expandPreviewRightPanel();
		findVisibleElement(previewPage_headerDetails, Speed.slow);
		int size = driver.findElements(By.xpath(PREVIEWPAGE_TOTALTAGS)).size();
		for (int i = 1; i <= size; i++)
		{
			By tagXpath = By.xpath(PREVIEWPAGE_TOTALTAGS + "[" + i + "]");
			if (isDisplayed(tagXpath))
			{
				WebElement elem = findVisibleElement(tagXpath);
				return elem.getText().trim().equalsIgnoreCase(tag.trim());
			}
		}
		return false;
	}

	/**
	 * Verify metadata field text in Preview page
	 *
	 * @param fieldName
	 *        to find metadata text for
	 * @param metaDataToVerify
	 *        meta data for verification
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyPreviewMetaData(String fieldName, String metaDataToVerify)
	{
		expandPreviewRightPanel();
		gotoMetadataTab();
		// findVisibleElement(previewPage_MetadataTab).click();
		By metaField = By.xpath(DIALOGBODY + "//*[@id='file_module_fileInfoCommentContainerID']//*[normalize-space(text())='" + fieldName.trim() + "']/following-sibling::*[2]");
		if (isDisplayed(metaField))
		{
			WebElement elem = findVisibleElement(metaField);
			return elem.getText().trim().equals(metaDataToVerify.trim());
		}
		return false;
	}

	/**
	 * Expand preview page right Panel
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandPreviewRightPanel()
	{
		if (isDisplayed(previewPage_Loader))
		{
			while (isDisplayed(previewPage_Loader))
			{
				;
			}
		}
		if (isDisplayed(previewPage_ShowArrow))
		{
			findVisibleElement(previewPage_ShowArrow).click();
		}
	}

	/**
	 * Close preview page
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void closePreviewPage()
	{
		findVisibleElement(previewPageHeader_CloseIcon).click();
		findPresentElement(modalContentClosed, Speed.slow);
	}

	/**
	 * Go to metadata tab of an opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoMetadataTab()
	{
		findVisibleElement(commonMetadataTab).click();
	}

	/**
	 * Edit File metadata
	 *
	 * @param fileName
	 *        of the document
	 * @param fieldToEdit
	 *        metadata field name
	 * @param valueToSend
	 *        metadata text
	 * @author dheeraj.rajput
	 */
	@Override
	public void editFileMetadata(String fileName, String fieldToEdit, String valueToSend)
	{
		if (fileName.contains("/"))
		{
			String file[] = fileName.split("/");
			fileName = file[1];
		}
		if (!isDisplayed(editFileModal))
		{
			gotoMoreActions(fileName, DocFolderOperation.Edit_details.toString().replace("_", " "));
		}
		findVisibleElement(editFileModal);
		gotoMetadataTab();
		addMetadata(fieldToEdit, valueToSend);
	}

	/**
	 * Download a file from more actions
	 *
	 * @param fileName
	 *        of the file to be downloaded
	 * @author dheeraj.rajput
	 */
	@Override
	public void downloadFile(String fileName)
	{
		gotoMoreActions(fileName, FileLabels.FILES_COMMON_DOWNLOAD);
		if (isDisplayed(footer_Accept, Speed.slow))
		{
			findVisibleElement(footer_Accept).click();
		}
		findVisibleElement(downloading);
		while (isDisplayed(downloading))
		{
			;
		}
	}

	/**
	 * Delete a specific file
	 *
	 * @param fileName
	 *        to delete
	 * @author dheeraj.rajput
	 */
	@Override
	public void deleteFile(String fileName)
	{

		gotoMoreActions(fileName, FileLabels.FILES_COMMON_DELETE);

		findVisibleElement(deleteMsg);

		if (isDisplayed(locRevokeAndDelete))
		{
			findClickableElement(locRevokeAndDelete).click();
		}
		else
		{

			findClickableElement(deleteModal_Delete).click();
		}

		while (isDisplayed(deleting))
		{
			;
		}

	}

	/**
	 * Create new folder in root
	 *
	 * @param folderName
	 *        to create
	 * @param description
	 *        of the folder
	 */
	@Override
	public void createNewFolderInRoot(String folderName, String description)
	{
		findPresentElement(modalContent, Speed.slow);
		while (isDisplayed(grayLoader))
		{
			;
		}
		findVisibleElement(commonDetailsTab).click();
		WebElement folderNameInput = findVisibleElement(folder_Details_NameInput, Speed.slow);
		WebElement folderDescInput = findVisibleElement(folder_Details_DescriptionInput);
		folderNameInput.clear();
		folderNameInput.sendKeys(folderName.trim());
		folderDescInput.clear();
		folderDescInput.sendKeys(description.trim());
		clickChooseButton();
		findPresentElement(modalContent, Speed.slow);
		WebElement rootFolder = findVisibleElement(addFolder_ChooseLocation_RootFolder);
		if (!rootFolder.getAttribute("class").contains("fancytree-selected"))
		{
			rootFolder.click();
		}
		clickSelectInChooseLocationModal();
	}

	/**
	 * Restore a Deleted File from Deleted Items
	 *
	 * @param fileName
	 *        to restore
	 * @author dheeraj.rajput
	 */
	@Override
	public void restoreFile(String fileName)
	{
		gotoMoreActions(fileName, DocFolderOperation.Restore.toString());
		findVisibleElement(footer_Continue, Speed.slow).click();
		while (!isDisplayed(restoreSuccess))
		{
			;
		}
	}

	/**
	 * Move File to a specific folder
	 *
	 * @param siteName
	 *        to select from choose a site drop down(Will work only if drop down is not disable).
	 * @param parentFolder
	 *        to select
	 * @param childFolder
	 *        actual place where file will be moved
	 * @author dheeraj.rajput
	 *         Note: method needs to be updated in order to select multiple childFolders and Sites
	 */
	@Override
	public void moveFile(String siteName, String parentFolder, String childFolder)
	{
		if (!siteName.isEmpty() && siteName != null)
		{
			chooseASite(siteName.trim());
		}
		chooseAFolder(parentFolder, childFolder);
		clickMove();
		if (isDisplayed(globalAlertMessageContainer, Speed.slow))
		{
			findPresentElement(globalAlertMessageContainer);
			while (isDisplayed(globalAlertMessageContainer))
			{
				;
			}
		}
	}

	/**
	 * Clear left Panel Search Box
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clearLeftPanelSearchItem()
	{
		findVisibleElement(leftPanel_ClearSearchIcon).click();
	}

	/**
	 * Verify no files or folder is present
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyNoFilesOrFolderPresent()
	{
		findVisibleElement(fileBanner);
		return isDisplayed(noFilesFolder);
	}

	/**
	 * Verify left panel item
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyLeftPanelItem(String item)
	{
		expandLeftPanel();
		By opt = By.xpath("//*[@id='file_module_leftPanelPage']//*[normalize-space(text())='" + item.trim() + "']");
		return isDisplayed(opt);
	}

	/**
	 * Store file version meta in VERSION variable
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public String getFileVersion()
	{
		WebElement elem = findVisibleElement(fileMeta_Version, Speed.slow);
		return elem.getText().trim();
	}

	/**
	 * Verify version count gets incremented by 1 or not
	 *
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyVersionCountIncremented(String fetchedVersion)
	{
		String newVersion = getFileVersion();
		int existingVerNumber = Integer.parseInt(fetchedVersion.trim().substring(fetchedVersion.indexOf("v") + 1));
		int newVersionNumber = Integer.parseInt(newVersion.trim().substring(newVersion.indexOf("v") + 1));
		if ((existingVerNumber + 1) == newVersionNumber)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Go to tasks tab of an opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoTasksTab()
	{
		findVisibleElement(commonTasksTab, Speed.slow).click();
	}

	/**
	 * Go to attachments tab of an opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoAttachmentsTab()
	{
		findVisibleElement(commonAttachmentsTab, Speed.slow).click();
	}

	/**
	 * Click on Add button in Tasks tab
	 */
	@Override
	public TasksWeb clickAddButtonInTasksTab()
	{
		findClickableElement(addFiles_TasksTab_AddButton).click();
		findPresentElement(addtaskModal);
		return new TasksWeb(driver);
	}

	/**
	 * Verify Folder title matches
	 *
	 * @param title
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFolderTitle(String title)
	{
		findVisibleElement(titleContainer, Speed.slow);
		WebElement elem = findVisibleElement(titleContainer_FolderTitle, Speed.slow);
		return title.trim().equals(elem.getText().trim());
	}

	/**
	 * Verify Folder description matches
	 *
	 * @param description
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFolderDescription(String description)
	{
		findVisibleElement(titleContainer);
		WebElement elem;
		if (isDisplayed(titleContainer_FolderDescriptionMoreLink))
		{
			findVisibleElement(titleContainer_FolderDescriptionMoreLink).click();
		}
		elem = findVisibleElement(titleContainer_FolderDescription, Speed.slow);
		return description.trim().equals(elem.getText().trim());
	}

	@Override
	public void editFolderDetails(String fieldName, String value)
	{
		findPresentElement(modalContent, Speed.slow);
		findVisibleElement(commonDetailsTab, Speed.slow).click();
		String lowerCase = fieldName.toLowerCase();
		if (FileLabels.FILES_FOLDER_DETAILS_NAME.toLowerCase().equals(lowerCase))
		{
			WebElement nameInput = findVisibleElement(folder_Details_NameInput, Speed.slow);
			nameInput.clear();
			nameInput.sendKeys(value.trim());
		}
		else if (FileLabels.FILES_FOLDER_DETAILS_DESCRIPTION.toLowerCase().equals(lowerCase))
		{
			WebElement descInput = findVisibleElement(folder_Details_DescriptionInput, Speed.slow);
			descInput.clear();
			descInput.sendKeys(value.trim());
		}
	}

	@Override
	public void gotoTitleHeaderMoreActionItem(String item)
	{
		findClickableElement(titleContainer_MoreActionIcon, Speed.slow).click();

		By oprationXpath = By.xpath("(//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + item.trim() + "'])[1]");

		findPresentElement(moreOptionExpanded, Speed.slow);
		if (isDisplayed(oprationXpath))
		{
			findClickableElement(oprationXpath).click();
		}
	}

	@Override
	public boolean verifyMetaFieldInEditWindow(String fieldName, String valueToVerify)
	{
		By metaInputBox = By.xpath("(//*[normalize-space(text())='" + fieldName.trim() + "']/ancestor::*[1]//*[@type='text'])[last()]");
		By metaTextAreaBox = By.xpath("(//*[normalize-space(text())='" + fieldName.trim() + "']/ancestor::*[1]//textarea)[last()]");
		if (isDisplayed(metaInputBox))
		{
			return verifyInputText(metaInputBox, valueToVerify);
		}
		else if (isDisplayed(metaTextAreaBox))
		{
			return verifyInputText(metaInputBox, valueToVerify);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Edit folder metadata
	 *
	 * @param metaField
	 *        metadata field name
	 * @param metaValue
	 *        metadata value to verify
	 * @author dheeraj.rajput
	 */
	@Override
	public void editFolderMetadata(String metaField, String metaValue)
	{
		gotoMetadataTab();
		addMetadata(metaField, metaValue);
	}

	/**
	 * Click on select all checkbox
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickSelectAllCheckbox()
	{
		findClickableElement(selectAllChkbox).click();
	}

	/**
	 * Verify file is present in Index page of file module
	 *
	 * @param fileName
	 * @return
	 */
	@Override
	public boolean verifyFileInIndex(String fileName)
	{
		findVisibleElement(fileBanner);
		By fileXpath = By.xpath("//*[@id='file_module_indexPageSiteTree']//*[normalize-space(text())='" + fileName.trim() + "']");
		return isDisplayed(fileXpath);
	}

	/**
	 * Open Actions button option
	 *
	 * @param item
	 *        Actions button option to click on.
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectItemFromActions(String item)
	{
		WebElement itemToClick = null;
		findVisibleElement(fileBanner);
		WebElement actionButton = findVisibleElement(fileBanner_ActionsButton, Speed.slow);
		if (actionButton.isDisplayed() && actionButton.isEnabled())
		{
			actionButton.click();
			itemToClick = findVisibleElement(By.xpath("(//*[contains(text(),'" + FileLabels.FILES_ACTIONS + "')])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"), Speed.slow);
			itemToClick.click();
			findVisibleElement(openedModal, Speed.slow);
		}
	}

	/**
	 * Select file from Index Page of file module
	 *
	 * @param fileName
	 *        to select
	 */
	@Override
	public void selectFileFromIndex(String fileName)
	{
		findVisibleElement(fileBanner);
		By fileXpath = By.xpath("//*[@id='file_module_indexPageSiteTree']//*[normalize-space(text())='" + fileName.trim() + "']/ancestor::*[2]/preceding-sibling::*//*[@class='fancytree-checkbox']");
		setSelection(fileXpath, true);
		// findVisibleElement(fileXpath).click();
	}

	/**
	 * Verify correct Bulk Print message is present
	 *
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyBulkPrintMessage()
	{
		findPresentElement(modalContent, Speed.slow);
		if (isDisplayed(generatingPDF))
		{
			while (isDisplayed(generatingPDF))
			{
				;
			}
		}
		if (isDisplayed(yourFileIsNowReady))
		{
			if (isDisplayed(bulkDownloadMessage))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Click on Download in modal which is open
	 */
	@Override
	public void clickDownloadInModal()
	{
		findVisibleElement(footer_Download, Speed.slow).click();
		while (isDisplayed(downloading, Speed.slow))
		{
			;
		}
	}

	/**
	 * Checkout a file
	 *
	 * @param fileName
	 *        to take checkout for
	 * @author dheeraj.rajput
	 */
	@Override
	public void checkoutFile(String fileName)
	{
		gotoMoreActions(fileName, FileLabels.FILES_COMMON_CHECKOUT.trim());
		findVisibleElement(modalContent);
		if (fileName.contains("."))
		{
			fileName = fileName.substring(0, fileName.lastIndexOf('.')).trim();
		}
		String checkoutFile = findVisibleElement(checkout_FileName).getText().trim();
		if (checkoutFile.equals(fileName.trim()))
		{
			findVisibleElement(footer_CheckOut).click();
			if (isDisplayed(downloadingFiles))
			{
				while (isDisplayed(downloadingFiles))
				{
					;
				}
			}
		}
		else
		{
			System.err.println(fileName + " not found in checkout modal");
		}
	}

	/**
	 * Verify more actions option for a specific file
	 *
	 * @param file
	 * @param operation
	 *        more options item to verify
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyMoreActionsItem(String file, String operation)
	{
		try
		{
			searchFile(file);
			By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
			findClickableElement(moreOption, Speed.slow).click();

			By oprationXpath = By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation + "'])[1]");

			findPresentElement(moreOptionExpanded, Speed.slow);

			return isDisplayed(oprationXpath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Verify name of check in file in modal that is open
	 *
	 * @param fileName
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyCheckInFileName(String fileName)
	{
		findPresentElement(modalContent);
		if (fileName.contains("."))
		{
			fileName = fileName.substring(0, fileName.lastIndexOf('.')).trim();
		}
		String checkoutFile = findVisibleElement(checkout_FileName).getText().trim();
		return checkoutFile.equals(fileName.trim());
	}

	/**
	 * Verify status of check in file in modal that is open
	 *
	 * @param status
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyCheckInFileStatus(String status)
	{
		findPresentElement(modalContent);
		String fetchedStatus = findVisibleElement(checkout_Status).getText().trim();
		return fetchedStatus.equals(status.trim());
	}

	/**
	 * Click on check in
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCheckIn()
	{
		findVisibleElement(modalContent);
		findVisibleElement(footer_CheckIn).click();
	}

	/**
	 * Verify check out user name
	 *
	 * @param userName
	 *        to verify(not email)
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyCheckOutUserName(String userName)
	{
		findPresentElement(modalContent);
		String fetchedStatus = findVisibleElement(checkout_UserName).getText().trim();
		return fetchedStatus.equals(getUserData(userName.trim()));
	}

	/**
	 * Click on close in modal that is open
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickClose()
	{
		findVisibleElement(footer_Close).click();
		findPresentElement(modalContentClosed, Speed.slow);
	}

	/**
	 * Verify cancel checkout link is present(Only for admin)
	 *
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyCancelCheckoutLinkIsPresent()
	{
		findPresentElement(modalContent);
		return isDisplayed(checkout_CancelCheckoutLink);
	}

	/**
	 * Cancel checkout(Only for admin)
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void cancelCheckout()
	{
		findPresentElement(modalContent);
		findVisibleElement(checkout_CancelCheckoutLink).click();
		findPresentElement(modalContentClosed);
	}

	/**
	 * Verify disclaimer modal is visible
	 *
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyDisclaimerModalIsVisible()
	{
		findPresentElement(modalContent);
		return isDisplayed(disclaimerHeader);
	}

	/**
	 * Click on like link
	 */
	@Override
	public void clickLike()
	{
		findVisibleElement(likeLink).click();
		findVisibleElement(likeContainer);
	}

	/**
	 * Verify unlike link is present
	 *
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyUnlikeLinkIsPresent()
	{
		return isDisplayed(unlikeLink);
	}

	/**
	 * Verify like link is present
	 *
	 * @return true
	 *         if successful
	 */
	@Override
	public boolean verifyLikeLinkIsPresent()
	{
		return isDisplayed(likeLink);
	}

	/**
	 * Add comment in commentbox
	 *
	 * @param comment
	 *        String value to send
	 * @author dheeraj.rajput
	 */
	@Override
	public void addComment(String comment)
	{
		WebElement commentBox = findVisibleElement(commentInput);
		commentBox.click();
		findVisibleElement(commentInput, Speed.slow);
		commentBox.clear();
		commentBox.sendKeys(comment.trim());
	}

	/**
	 * Click on Post button
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickPost()
	{
		findVisibleElement(comment_postButton, Speed.slow).click();
	}

	/**
	 * Verify visibility of comment(Will verify comment of a particular user).
	 *
	 * @param user
	 *        username of the commenter
	 * @param comment
	 *        to verify
	 * @return true if successful
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyVisibilityOfComment(String user, String comment)
	{
		user = getUserData(user);

		By commentXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim() + "'][1])[1]");
		return isDisplayed(commentXpath, Speed.slow);
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
		findVisibleElement(editComment_PostButton, Speed.slow).click();
		return verifyVisibilityOfComment(user, commentToEdit);
	}

	/**
	 * Click on edit link
	 *
	 * @param user
	 *        username of the commenter
	 * @param comment
	 *        against which edit button has to be clicked
	 * @author badal.gandhi
	 */
	@Override
	public void clickCommentEditLink(String user, String comment)
	{
		user = getUserData(user);

		By editXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + FileLabels.FILES_COMMON_EDIT + "'][1])[1]");
		findVisibleElement(editXpath).click();
	}

	@Override
	public void scrollToComment(String user, String comment) throws InterruptedException
	{
		By commentXpath = By.xpath("(.//*[@id='taskDetailContainer' and contains(@style,'display: block')]//*[contains(@id,'commentMeta')]//following::*[text()='" + comment.trim() + "'])[1]");
		if (!isDisplayed(commentXpath) && isDisplayed(loadMoreCommentsLink))
		{
			findVisibleElement(loadMoreCommentsLink).click();
			scrollToElement(commentXpath);
		}
	}

	/**
	 * Click on Delete link
	 *
	 * @param user
	 *        Username of the commenter
	 * @param comment
	 *        existing comment
	 * @return true
	 *         if successful
	 * @author badal.gandhi
	 */
	@Override
	public void clickCommentDeleteLink(String user, String comment)
	{
		user = getUserData(user);

		By deleteXpath = By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + user.trim()
				+ "']//following::*[normalize-space(text())='" + comment.trim()
				+ "']//following::*[normalize-space(text())='" + FileLabels.FILES_COMMON_DELETE + "'][1])[1]");
		findVisibleElement(deleteXpath).click();
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
		findPresentElement(messageModelClosed, Speed.slow);
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
	 * Get comment count
	 *
	 * @return comment count(Integer value)
	 */
	@Override
	public int getCommentCount()
	{
		String commentText = findVisibleElement(totalComments).getText().trim();
		if (!commentText.contains(" "))
		{
			return 0;
		}
		return Integer.parseInt(commentText.substring(0, commentText.indexOf(" ")));
	}

	/**
	 * Verify comment count gets incremented by 1 or not
	 *
	 * @return true
	 *         if successful
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
	 * Verify comment count gets decremented by 1 or not
	 *
	 * @return true
	 *         if successful
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

	/**
	 * Click on unlike link
	 */
	@Override
	public void clickUnlike()
	{
		findVisibleElement(unlikeLink).click();
	}

	/**
	 * Choose a folder in which file has to be moved or copied
	 *
	 * @param parent
	 *        folder name
	 * @param child
	 *        folder to select(If child is empty or null, parent checkbox will be selected)
	 * @author dheeraj.rajput
	 */
	@Override
	public void chooseAFolder(String parent, String child)
	{
		findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(move_ExpandAllLink).click();
		By folderCheckbox;
		if (!child.isEmpty() && child != null)
		{
			folderCheckbox = By.xpath("(//*[normalize-space(text())='" + parent.trim() + "'])[last()]/ancestor::*[1]/following-sibling::*[1]//*[normalize-space(text())='" + child.trim() + "']/preceding-sibling::*[contains(@class,'-checkbox')]");
		}
		else
		{
			folderCheckbox = By.xpath("(//*[normalize-space(text())='" + parent.trim() + "'])[last()]/preceding-sibling::*[contains(@class,'-checkbox')]");
		}
		if (isDisplayed(folderCheckbox, Speed.slow))
		{
			findVisibleElement(folderCheckbox).click();
		}
		else
		{
			System.err.println(folderCheckbox + " not found.");
		}
	}

	/**
	 * Choose a site from Choose a site dropdown of move or copy modal
	 *
	 * @param siteName
	 *        to select from drop down
	 * @author dheeraj.rajput
	 */
	@Override
	public void chooseASite(String siteName)
	{
		WebElement elem = findVisibleElement(move_ChooseASiteDropDown);
		if (elem.isEnabled())
		{
			Select siteDrpDwn = new Select(elem);
			siteDrpDwn.selectByVisibleText(siteName.trim());
		}
		else
		{
			System.err.println("Choose a site drop down is disabled");
		}
	}

	/**
	 * Verify chosen site name in Choos a site dropdown of move or copy modal
	 *
	 * @param siteName
	 *        to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyChooseASiteName(String siteName)
	{
		findVisibleElement(modalContent, Speed.slow);
		WebElement elem = findVisibleElement(move_ChooseASiteDropDown);
		if (elem.isEnabled())
		{
			Select siteDrpDwn = new Select(elem);
			return siteDrpDwn.getFirstSelectedOption().getText().trim().equals(siteName.trim());
		}
		return false;
	}

	/**
	 * Click on Move button available in modal footer
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickMove()
	{
		findPresentElement(modalContent);
		findVisibleElement(footer_Move, Speed.slow).click();
		findPresentElement(modalContentClosed);
	}

	/**
	 * Click on Copy button available in modal footer
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCopy()
	{
		findPresentElement(modalContent);
		findVisibleElement(footer_Copy, Speed.slow).click();
		findPresentElement(modalContentClosed);
	}

	/**
	 * Copy File to a specific folder
	 *
	 * @param siteName
	 *        to select from choose a site drop down(Will work only if drop down is not disable).
	 * @param parentFolder
	 *        to select
	 * @param childFolder
	 *        actual place where file will be moved
	 * @author dheeraj.rajput
	 */
	@Override
	public void copyFile(String siteName, String parentFolder, String childFolder)
	{
		if (!siteName.isEmpty() && siteName != null)
		{
			chooseASite(siteName.trim());
		}
		chooseAFolder(parentFolder, childFolder);
		clickCopy();
		while (!isDisplayed(globalAlertMessageContainer))
		{
			;
		}
		findVisibleElement(globalAlertMessageContainer);
	}

	/**
	 * Select file checkbox
	 *
	 * @param fileName
	 *        to select checkbox for
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectFileCheckBox(String fileName)
	{
		By fileChkBox = By.xpath("//*[normalize-space(text())='" + fileName.trim() + "']/ancestor::*[2]/preceding-sibling::*//*[@class='chkbox']");
		setSelection(fileChkBox, true);
	}

	/**
	 * Verify file author name
	 *
	 * @param author
	 *        name to verify
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFileAuthorName(String author)
	{
		String userName = getUserData(author).trim();
		WebElement authorElem = findVisibleElement(authorColumn);
		return userName.equals(authorElem.getText().trim());
	}

	/**
	 * Add file or folder to favourites
	 *
	 * @param itemToFavourite
	 *        item that needs to be favourited
	 * @author dheeraj.rajput
	 */
	@Override
	public void addFileOrFolderToFavourites(String itemToFavourite)
	{
		By alreadyFavXpath = By.xpath("(//*[text()='" + itemToFavourite.trim() + "'])[last()]/ancestor::*[2]/following-sibling::*/*[contains(@class,'icon-star-selected')]");
		By favStarXPath = By.xpath("(//*[text()='" + itemToFavourite.trim() + "'])[last()]/ancestor::*[2]/following-sibling::*/*[contains(@class,'icon-star')]");
		if (isDisplayed(alreadyFavXpath))
		{
			System.out.println(itemToFavourite + " is already in favourites");
		}
		else
		{
			findVisibleElement(favStarXPath).click();
		}
	}

	/**
	 * Remove file or folder from favourites
	 *
	 * @param itemToRemoveFromFavourites
	 *        item that has to be removed from favourites
	 * @author dheeraj.rajput
	 */
	@Override
	public void removeFileOrFolderFromFavourites(String itemToRemoveFromFavourites)
	{
		By alreadyFavXpath = By.xpath("(//*[text()='" + itemToRemoveFromFavourites.trim() + "'])[last()]/ancestor::*[2]/following-sibling::*/*[contains(@class,'icon-star-selected')]");
		if (isDisplayed(alreadyFavXpath))
		{
			findVisibleElement(alreadyFavXpath).click();
			moveToElement(fileBanner);
		}
		else
		{
			System.out.println(itemToRemoveFromFavourites + " is not in favourites.");
		}
	}

	/**
	 * Verify file or folder's favourite icon is selected
	 *
	 * @param favouriteItem
	 *        name of the file or folder
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFileOrFolderFavouriteIconIsSelected(String favouriteItem)
	{
		By alreadyFavXpath = By.xpath("(//*[text()='" + favouriteItem.trim() + "'])[last()]/ancestor::*[2]/following-sibling::*/*[contains(@class,'icon-star-selected')]");
		return isDisplayed(alreadyFavXpath);
	}

	/**
	 * Verify file or folder is available in favourite screen
	 *
	 * @param itemToVerify
	 *        file or folder name
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyItemInFavourites(String itemToVerify)
	{
		try
		{
			searchFile(itemToVerify);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isDisplayed(noFavourites))
		{
			findVisibleElement(noFavourites);
			return false;
		}
		return verifyFileOrFolderFavouriteIconIsSelected(itemToVerify);
	}

	/**
	 * Get total page count from preview file page
	 *
	 * @return page count
	 * @author dheeraj.rajput
	 */
	@Override
	public int getTotalPageCountFromPreview()
	{
		WebElement elem = findVisibleElement(previewPage_TotalPageCount, Speed.slow);
		while (isDisplayed(previewPage_PageContentLoader, Speed.slow))
		{
			findVisibleElement(previewPage_FullScreen, Speed.slow);
		}
		findVisibleElement(previewPage_PrintIcon, Speed.slow);
		return Integer.parseInt(elem.getText().trim());
	}

	/**
	 * Get value of page count from from Pages column
	 *
	 * @param fileName
	 *        to fetch count for
	 * @return page count
	 * @author dheeraj.rajput
	 */
	@Override
	public int getFilePages(String fileName)
	{
		By pagesCount = By.xpath("(//*[@id='file_module_tableContentDivID']//*[normalize-space(text())='" + fileName.trim() + "'])[last()]/ancestor::*[2]/following-sibling::*[1]");
		if (isDisplayed(pagesCount, Speed.slow))
		{
			WebElement elem = findVisibleElement(pagesCount, Speed.slow);
			try
			{
				return Integer.parseInt(elem.getText().trim());
			}
			catch (NumberFormatException ne)
			{
				System.out.println("Page Count is not displayed in Pages column");
				return 0;
			}
		}
		else
		{
			System.err.println("Pages not displayed");
			return 0;
		}
	}

	/**
	 * Verify uploaded zip files are present on File Module
	 *
	 * @param zipFile
	 *        name of the zipfile with extension.
	 * @return true
	 *         if successful.
	 * @author dheeraj.rajput
	 *         Note: Can be updated in future
	 */
	@Override
	public boolean verifyUploadedZipFiles(String zipFile) throws IOException
	{
		findVisibleElement(allFileLinks, Speed.slow);
		int size = driver.findElements(allFileLinks).size();
		ArrayList<String> files = new ArrayList<String>();
		for (int i = 1; i <= size; i++)
		{
			WebElement fileElem = findVisibleElement(By.xpath("(.//*[@id='file_module_mainTableId']//*[@class='ffname']//*[contains(@class,'blackLink')])[" + i + "]"));
			files.add(fileElem.getText().trim());
		}
		LinkedHashSet<String> set = getZipFileList(zipFile.trim());
		return files.containsAll(set);
	}

	/**
	 * Get names of file from zip and store it in a set
	 *
	 * @param zipFileName
	 *        name of the zip file with extension
	 * @return set
	 *         containing names of all the files
	 * @author dheeraj.rajput
	 *         Note: Can be updated in future
	 */
	@Override
	public LinkedHashSet<String> getZipFileList(String zipFileName) throws IOException
	{
		String filePath = new File(TestBaseSetup.currentDir + "\\testData\\" + zipFileName.trim()).getCanonicalPath().trim();
		ZipFile myZipFile;
		try
		{
			myZipFile = new ZipFile(filePath);
		}
		catch (Exception e)
		{
			filePath = new File(TestBaseSetup.currentDir + "\\Downloads\\" + zipFileName.trim()).getCanonicalPath().trim();
			myZipFile = new ZipFile(filePath);
		}
		Enumeration<? extends ZipEntry> zipEntries = myZipFile.entries();

		LinkedHashSet<String> set = new LinkedHashSet<>();
		while (zipEntries.hasMoreElements())
		{
			String element = ((ZipEntry) zipEntries.nextElement()).getName().split("/")[0].trim();
			set.add(element);
		}
		myZipFile.close();
		return set;
	}

	/**
	 * Upload file in add files modal
	 *
	 * @param fileName
	 *        name of the file to be uploaded
	 */
	@Override
	public void uploadFileInAddFiles(String fileName)
	{
		uploadFile(addFiles_Browse, fileName);
	}

	/**
	 * Click on expand all in select location modal of Add new folder modal
	 */
	@Override
	public void clickExpandAllInAddFolderSelectLocation()
	{
		findVisibleElement(addFolder_ChooseLocation_expandAllLink, Speed.slow).click();
	}

	/**
	 * Click on collapse all in select location modal of Add new folder modal
	 */
	@Override
	public void clickCollapseAllInAddFolderSelectLocation()
	{
		findVisibleElement(addFolder_ChooseLocation_collapseAllLink, Speed.slow).click();
	}

	/**
	 * Select name of the folder in select location modal
	 *
	 * @param folderName
	 */
	@Override
	public void selectFolderNameInSelectLocation(String folderName)
	{
		clickExpandAllInAddFolderSelectLocation();
		By folderRadio = By.xpath(".//*[@id='file_module_innerModalDiv']//*[normalize-space(text())='" + folderName.trim() + "']/preceding-sibling::*[@class='fancytree-checkbox']");
		if (isDisplayed(folderRadio, Speed.slow) && !findVisibleElement(folderRadio).getAttribute("class").contains("fancytree-selected"))
		{
			findVisibleElement(folderRadio).click();
		}
	}

	/**
	 * Click on select button in choose location modal
	 */
	@Override
	public void clickSelectInChooseLocationModal()
	{
		WebElement elem = findVisibleElement(addFolder_ChooseLocation_selectButton, Speed.slow);
		elem.click();
	}

	/**
	 * Click on choose button in Add new folder modal
	 */
	@Override
	public void clickChooseButton()
	{
		WebElement elem = findVisibleElement(addFolder_Details_chooseButton, Speed.slow);
		elem.click();
	}

	/**
	 * Add folder at specific location
	 *
	 * @param folderName
	 *        name of the folder
	 * @param destination
	 *        location in which folder has to be created
	 */
	@Override
	public void addFolderAtSpecificLocation(String folderName, String destination)
	{
		findPresentElement(modalContent, Speed.slow);
		findVisibleElement(commonDetailsTab).click();
		WebElement folderNameInput = findVisibleElement(folder_Details_NameInput, Speed.slow);
		folderNameInput.clear();
		folderNameInput.sendKeys(folderName.trim());
		clickChooseButton();
		findPresentElement(modalContent, Speed.slow);
		selectFolderNameInSelectLocation(destination);
		clickSelectInChooseLocationModal();
	}

	/**
	 * Click on Actions button
	 *
	 * @author dheeraj.rajput
	 * @Created: 03 April 2018
	 * @Updated:
	 */
	@Override
	public void clickActionsButton()
	{
		WebElement actionsButton = findVisibleElement(index_ActionsButton);
		if (actionsButton.isEnabled())
		{
			actionsButton.click();
		}
	}

	/**
	 * Verify Actions button is visible or not
	 *
	 * @return true
	 *         if button is visible
	 * @author dheeraj.rajput
	 * @Created: 03 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyActionsButtonIsVisible()
	{
		return isDisplayed(index_ActionsButton, Speed.slow);
	}

	/**
	 * Verify option of Actions Menu in Index page
	 *
	 * @param itemToVerify
	 *        option to verify
	 * @return true
	 *         if options is available
	 * @author dheeraj.rajput
	 * @Created: 03 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyItemOfActionsButton(String itemToVerify)
	{
		By actionItem = By.xpath("(//*[contains(text(),'" + FileLabels.FILES_ACTIONS + "')])[1]/following::*[contains(text(),'" + itemToVerify.trim() + "')][1]");
		return isDisplayed(actionItem, Speed.slow);
	}

	/**
	 * Verify File's status in Index page
	 *
	 * @param fileNameWithExtension
	 *        name of the file with extension
	 * @param statusToVerify
	 *        status that needs to be verified
	 * @return true
	 *         if status matches with the status fetched from Web.
	 * @author dheeraj.rajput
	 * @Created: 03 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyFileStatusInIndex(String fileNameWithExtension, String statusToVerify)
	{
		while (isDisplayed(index_AllocateReviewModalGrayLoader))
		{
			;
		}
		findVisibleElement(indexModal, Speed.slow);
		By fileStatus = By.xpath("//*[@id='file_module_indexPageSiteTree']//*[normalize-space(text())='" + fileNameWithExtension.trim() + "']/ancestor::td/following-sibling::*[1]");
		WebElement statusElem = findVisibleElement(fileStatus, Speed.slow);
		return statusToVerify.trim().equals(statusElem.getText().trim());
	}

	/**
	 * Verify Allocation message
	 *
	 * @param expectedMessage
	 *        expected message
	 * @return true
	 *         if actual message matches expected message.
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyCheckingAllocationMessage(String expectedMessage)
	{
		findVisibleElement(index_AllocateReviewModal);
		WebElement elem = findVisibleElement(index_CheckingAllocationMsg);
		String actualMessage = elem.getText().trim();
		return actualMessage.equals(expectedMessage.trim());
	}

	/**
	 * Click Close in checking allocation modal
	 *
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public void clickCloseInCheckingAllocationModal()
	{
		WebElement closeButton = findVisibleElement(index_CheckingAllocation_CloseButton);
		closeButton.click();
		findPresentElement(index_AllocateReviewModal, Speed.slow);
	}

	/**
	 * Click Close in checking allocation modal
	 *
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public void clickContinueInCheckingAllocationModal()
	{
		WebElement continueButton = findVisibleElement(index_CheckingAllocation_ContinueButton);
		continueButton.click();
		findPresentElement(index_AllocateReviewModalClosed, Speed.slow);
	}

	/**
	 * Verify Checking Files as not applicable for review title is present
	 *
	 * @return true
	 *         if title is present
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyCheckingFilesAsNAForReviewTitleIsVisible(String title)
	{
		String titleText = findVisibleElement(index_MarkAsNA_CheckingFileIsNotApplicableForReviewTitle, Speed.slow).getText().trim();
		return titleText.equals(title.trim());
	}

	/**
	 * Verify Marking files as not applicable for review title is present
	 *
	 * @return true
	 *         if title is present
	 * @author dheeraj.rajput
	 * @Created: 05 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyMarkingFilesAsNAForReviewTitleIsVisible(String title)
	{
		findPresentElement(index_AllocateReviewModalClosed);
		String titleText = findPresentElement(index_MarkAsNA_MarkingFilesAsNotApplicableForReviewTitle, Speed.slow).getText().trim();
		return titleText.equals(title.trim());
	}

	/**
	 * Rename file in add files modal
	 *
	 * @param fileNameWithExtension
	 *        file name which is going to be renamed
	 * @param newName
	 *        new name of the file
	 * @author dheeraj.rajput
	 * @Created 16 April 2018
	 * @Updated
	 */
	@Override
	public void renameFileInAddFilesModal(String fileNameWithExtension, String newName)
	{
		clickFilesTabInAddFilesModal();
		String fileExtension = fileNameWithExtension.substring(fileNameWithExtension.lastIndexOf(".")).trim();
		String renameLinkXpathText = ".//*[@id='addModalQueue']//*[contains(@class,'postUpload')]//*[normalize-space(text())='" + fileNameWithExtension.trim() + "']/following-sibling::*[normalize-space(text())='" + FileLabels.FILES_ADDFILES_FILES_RENAME + "']";
		By renameLink = By.xpath(renameLinkXpathText);
		findVisibleElement(renameLink).click();

		By renameInput = By.xpath(renameLinkXpathText + "/preceding-sibling::*//*[@type='text']");
		WebElement renameInputElem = findVisibleElement(renameInput);
		renameInputElem.clear();
		renameInputElem.sendKeys(newName.trim());

		By renameSubmitButton = By.xpath(renameLinkXpathText.replace(fileNameWithExtension, newName.trim() + fileExtension) + "/preceding-sibling::*//*[contains(@class,'edit-submit')]");
		findVisibleElement(renameSubmitButton).click();
	}

	/**
	 * Verify file name in files tab of Add files modal (Use it to verify file name after file is uploaded)
	 *
	 * @param fileNameWithExtension
	 *        name of the file to be verified with extension
	 * @author dheeraj.rajput
	 * @Created 16 April 2018
	 * @Updated
	 */
	@Override
	public boolean verifyFileNameInAddFilesModal(String fileNameWithExtension)
	{
		clickFilesTabInAddFilesModal();
		By fileXpath = By.xpath("(.//*[@id='addModalQueue']//*[contains(@class,'postUpload')]//*[normalize-space(text())='" + fileNameWithExtension.trim() + "'])[last()]");
		return isDisplayed(fileXpath, Speed.slow);
	}

	/**
	 * Click files tab in add files modal
	 *
	 * @author dheeraj.rajput
	 * @Created 16 April 2018
	 * @Updated
	 */
	@Override
	public void clickFilesTabInAddFilesModal()
	{
		findVisibleElement(addFilesModal);
		findVisibleElement(addFiles_FilesTab).click();
	}

	/**
	 * Get list of data of a perticular column
	 *
	 * @param columnName
	 *        name of the column for which data has to be fetched
	 * @param dataType
	 *        data to be taken of folder or file
	 * @author dheeraj.rajput
	 * @Created 16 April 2018
	 * @Updated
	 */
	@Override
	public List<String> getListOfDataOfColumn(String columnName, String dataType)
	{
		findVisibleElement(fileContainer, Speed.slow);
		List<String> dataList = new ArrayList<String>();
		By allHeaders = By.xpath(fileTable_allHeaders);
		int size = driver.findElements(allHeaders).size();
		for (int i = 1; i <= size; i++)
		{
			By header = By.xpath(".//*[@id='file_module_mainTableId']//th[" + i + "]");
			if (isDisplayed(header, Speed.slow))
			{
				WebElement headerElem = findVisibleElement(header);
				if (headerElem.getText().trim().equalsIgnoreCase(columnName.trim()))
				{
					List<WebElement> columnElemList = null;
					if (dataType.trim().toLowerCase().equals("folder"))
					{
						By allColumnLinkForMatchedHeaderFolder = By.xpath(".//*[@id='file_module_mainTableId']//tr[contains(@id,'fold')]//td[" + i + "]//a[1]");
						By allColumnsForMatchedHeaderFolder = By.xpath(".//*[@id='file_module_mainTableId']//tr[contains(@id,'fold')]//td[" + i + "]");
						if (isDisplayed(allColumnLinkForMatchedHeaderFolder, Speed.slow))
						{
							columnElemList = driver.findElements(allColumnLinkForMatchedHeaderFolder);
						}
						else
						{
							columnElemList = driver.findElements(allColumnsForMatchedHeaderFolder);
						}
					}
					else
					{
						By allColumnLinkForMatchedHeaderFolder = By.xpath(".//*[@id='file_module_mainTableId']//tr[not(contains(@id,'fold'))]//td[" + i + "]//a[1]");
						By allColumnsForMatchedHeaderFolder = By.xpath(".//*[@id='file_module_mainTableId']//tr[not(contains(@id,'fold'))]//td[" + i + "]");
						if (isDisplayed(allColumnLinkForMatchedHeaderFolder, Speed.slow))
						{
							columnElemList = driver.findElements(allColumnLinkForMatchedHeaderFolder);
						}
						else
						{
							columnElemList = driver.findElements(allColumnsForMatchedHeaderFolder);
						}
					}
					if (columnElemList != null)
					{
						for (WebElement elem : columnElemList)
						{
							dataList.add(elem.getText().trim());
						}
					}
					break;
				}
			}
		}
		return dataList;
	}

	/**
	 * Verify zip file contains expected file(s) or folder(s)
	 *
	 * @param zipFileNameWithExtension
	 *        name of the zip file with its extension
	 * @param fileOrFolder
	 *        name(s) of file or folders (Add file name with extension)
	 * @return true
	 *         if required file or folder is found in zip
	 * @throws IOException
	 * @author dheeraj.rajput
	 * @Created 19 April 2018
	 * @Updated
	 */
	@Override
	public boolean verifyZipFileContainsDesiredFilesAndFolders(String zipFileNameWithExtension, String... filesOrFolders) throws IOException
	{
		findVisibleElement(breadCrumb, Speed.slow);
		List<String> fileOrFolderList = new ArrayList<>();
		for (int i = 0; i < filesOrFolders.length; i++)
		{
			fileOrFolderList.add(filesOrFolders[i].trim());
		}
		Set<String> setOfFileAndFoldersOfZipFile = getZipFileList(zipFileNameWithExtension);
		return setOfFileAndFoldersOfZipFile.containsAll(fileOrFolderList);
	}

	@Override
	public boolean verifyMyFileSendButton()
	{
		return isDisplayed(file_module_sendAFile_button, Speed.slow);

	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickSettingsTabAddFolderModal()
	{
		findVisibleElement(settingsAddFolderTab, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj
	 *         Create : 24th April 2018
	 *         Updated :
	 */
	@Override
	public void clickOnAddButtonOfTaskInAddNewVersion()
	{
		findVisibleElement(newVer_TasksTab_Addbtn).click();
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnClassifierSelectButton()
	{
		findVisibleElement(documentAnalysisClassifierSelectButton, Speed.slow).click();

		if (isDisplayed(attachment_loaderBig))
		{
			while (isDisplayed(attachment_loaderBig))
			{
				;
			}
		}

		findPresentElement(classifiersModal, Speed.slow, 20);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickNewClassifierButtonClassifierModal()
	{
		findVisibleElement(classifierNewClassifierButton, Speed.slow).click();
		findPresentElement(addNewClassifierModal, Speed.slow);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyDocumentAnalysisDisplayed()
	{
		return isDisplayed(documentAnalysisLbl);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifierSelectButtonDisplayed()
	{
		return isDisplayed(documentAnalysisClassifierSelectButton, Speed.slow);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyClassifiersModalDisplayed() throws InterruptedException
	{
		return isDisplayed(classifiersModalLabel, Speed.slow);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyNewClassifierModalDisplayed()
	{
		return isDisplayed(newClassifierModal, Speed.slow);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyNewClassifierModalFieldsDisplayed()
	{
		return isDisplayed(newClassifierModalNameField, Speed.slow)
				&& isDisplayed(newClassifierModalDescriptionField, Speed.slow)
				&& isDisplayed(newClassifierModalScopeOfClassifierPublicDefault, Speed.slow)
				&& isDisplayed(newClassifierModalScopeOfClassifierPrivateDefault, Speed.slow);

	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyNewClassifierModalButtonsDisplayed()
	{
		return isDisplayed(newClassifierModalScopeOfClassifierCancelButton, Speed.slow)
				&& isDisplayed(newClassifierModalScopeOfClassifierAddButtonDefault, Speed.slow);
	}

	/**
	 * @param message
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyTooltipMessageScopeOfClassifier(String message)
	{
		WebElement elementTooltip = findVisibleElement(By.xpath(".//*[@id='addFdolder_addNew_classifier_BODY']//*[@data-original-title='" + message + "']"));
		return elementTooltip.isDisplayed();
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifiersLabelDisplayed()
	{
		return isDisplayed(classifierAddLabel);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifierSearchTextboxDisplayed()
	{
		return isDisplayed(classifierSearchTextboxDefault, Speed.slow);
	}

	/**
	 * @return
	 */
	@Override
	public boolean verifyClassifierButtonsDisplayed()
	{
		return isDisplayed(classifierNewClassifierButton, Speed.slow)
				&& isDisplayed(classifierDoneButton, Speed.slow)
				&& isDisplayed(classifierCancelButton, Speed.slow);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickCancelClassifierModal()
	{
		findVisibleElement(classifierCancelButton).click();
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickCancelAddNewClassifierModal()
	{
		findPresentElement(addNewClassifierModal, Speed.slow);
		findVisibleElement(newClassifierModalScopeOfClassifierCancelButton, Speed.slow).click();
	}

	/**
	 * @param option
	 */
	@Override
	public void selectRadioButtonScopeOfClassifier(String option)
	{
		if (("Public").equals(option.trim()))
		{
			findVisibleElement(newClassifierModalScopeOfClassifierPublicRB, Speed.slow).click();
		}
		else if (("Private").equals(option.trim()))
		{
			findVisibleElement(newClassifierModalScopeOfClassifierPrivateRB, Speed.slow).click();
		}
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnClassifierModalDoneButton()
	{
		findVisibleElement(classifierDoneButton, Speed.slow).click();
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnAddFolderModalAddButton()
	{
		if (isDisplayed(saveButtonEditFolderModal))
		{
			findVisibleElement(saveButtonEditFolderModal).click();
		}
		else if (isDisplayed(addButtonAddFolderModal))
		{
			findVisibleElement(addButtonAddFolderModal, Speed.slow).click();
		}
	}

	/**
	 * @author tejash.trivedi
	 */
	public void clickOnClassifierModalNewClassifierrButton()
	{
		findVisibleElement(classifierNewClassifierButton, Speed.slow).click();
	}

	/**
	 * @return
	 */
	@Override
	public boolean verifyClassifierDoneButtonsDisabledDisplayed()
	{
		return isDisplayed(classifierDoneButtonDisabled, Speed.slow);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifierDoneButtonEnabledDisplayed()
	{
		return isDisplayed(classifierDoneButtonEnabled, Speed.slow);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnClassifierTokenRemoveButton()
	{
		findVisibleElement(classifierTokenRemoveButton, Speed.slow).click();
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifierTokenRemoveButtonDisplayed()
	{
		return isDisplayed(classifierTokenRemoveButton, Speed.slow);
	}

	/**
	 * @param classifierName
	 * @return
	 */
	@Override
	public boolean verifyClassifierTokenDisplayed(String classifierName)
	{
		if (isDisplayed(labelClassifier))
		{
			String classifier = findVisibleElement(labelClassifier).getText().trim();
			return classifier.equals(classifierName);
		}
		return false;
	}

	/**
	 * @param classifiersList
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifiersList(ArrayList<String> classifiersList)
	{
		int count = 0;
		ArrayList<String> list = new ArrayList<>();
		if (!isDisplayed(classifierSearchNoresultsfoundLabel))
		{
			int size = driver.findElements(classifierList).size();
			for (int i = 1; i <= size; i++)
			{
				WebElement classifier = findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//strong"));
				String name = classifier.getText().trim();
				list.add(name);
			}
			for (int i = 0; i < classifiersList.size(); i++)
			{
				if (list.get(i).contains(classifiersList.get(i).trim()))
				{
					count++;
				}
			}
			if (count != 0 && count == classifiersList.size())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param classifiersName
	 * @return
	 * @author tejash.trivedi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyClassifiersName(String classifiersName) throws InterruptedException
	{
		if (!isDisplayed(classifierSearchNoresultsfoundLabel))
		{
			findPresentElement(classifiersModal);
			int size = driver.findElements(classifierList).size();
			for (int i = size; i >= 1; i--)
			{
				scrollToElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//strong"));
				String name = findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//strong")).getText().trim();
				if (name.equals(classifiersName))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param message
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyMessageForNoClassifiersNameDisplayed(String message)
	{
		WebElement classifier = findVisibleElement(By.xpath(".//*[@id='noDataFoundDiv']//strong[normalize-space()='" + message + "']"));
		return classifier.isDisplayed();
	}

	/**
	 * @param message
	 * @return
	 */
	@Override
	public boolean verifyMessageForDuplicateClassifierAdd(String message)
	{
		WebElement classifier = findVisibleElement(By.xpath(".//*[@id='addNewClassifiers_ErrorDiv']//span[normalize-space()='" + message + "']"));
		return classifier.isDisplayed();
	}

	/**
	 * @param classifiersList
	 * @param linkName
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyClassifiersDescriptionLink(String classifierDescription, String linkName)
	{
		findPresentElement(classifiersModal);
		int size = driver.findElements(classifierList).size();
		for (int i = size; i >= 1; i--)
		{
			WebElement descElement = findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//span[contains(@id,'description')]"));
			String[] desc = descElement.getText().trim().split(dotChar);
			if (classifierDescription.contains(desc[0]))
			{
				return findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//*[text()='" + linkName + "']")).isDisplayed();
			}
		}
		return false;
	}

	/**
	 * @param classifierDescription
	 * @param linkName
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnClassifiersDescriptionLink(String classifierDescription, String linkName)
	{
		int size = driver.findElements(classifierList).size();
		for (int i = size; i >= 1; i--)
		{
			WebElement descElement = findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//span[contains(@id,'description')]"));
			String[] desc = descElement.getText().trim().split(dotChar);
			if (classifierDescription.contains(desc[0]))
			{
				findVisibleElement(By.xpath(".//*[@id='showClassifiersList']//li[" + i + "]//*[text()='" + linkName + "']")).click();
				break;
			}
		}
	}

	/**
	 * @param classifierName
	 * @author tejash.trivedi
	 */
	@Override
	public void selectClassifierFromList(String classifierName)
	{
		WebElement classifier = findVisibleElement(By.xpath(".//*[@id='addFdolder_classifier_list']//*[normalize-space(text())='" + classifierName.trim() + "']/../preceding-sibling::*[@type='radio']"));
		scrollToElement(classifier);
		if (classifier.isDisplayed())
		{
			classifier.click();
		}
	}

	/**
	 * @author surbhi.khetan
	 *         Click add In Add Folder Modal
	 * @creation date 19/04/2018
	 */
	@Override
	public void clickAddInAddFolderModal()
	{
		findVisibleElement(addFolder_Add, Speed.slow).click();
	}

	/**
	 * @author jyoti.raj
	 *         Create : 24th April 2018
	 *         Updated :
	 */
	@Override
	public void clickOnAddInAddNewVersion()
	{
		findVisibleElement(newVer_Addbtn).click();
	}

	/**
	 * Verify the document that have been saved from the report generation.
	 *
	 * @param templateFileName
	 * @author nidhi.shah
	 * @created: 01 - 04 - 2018
	 */
	@Override
	public boolean verifyDocumentTemplateSaved(String templateFileName) throws IOException
	{
		List<WebElement> documentTemplateEleList = driver.findElements(documentTemplate);
		Pattern templateFileNamePattern = Pattern.compile(templateFileName.split("\\.")[0] + " +[0-3][0-9]-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9] +at +[0-2][0-9].[0-5][0-9].[0-5][0-9]" + "." + templateFileName.split("\\.")[1]);
		for (WebElement documentTemplateEle : documentTemplateEleList)
		{
			if (templateFileNamePattern.matcher(documentTemplateEle.getText()).matches())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Verify Audits tab title in audit history tab
	 *
	 * @return true
	 *         if Audits tab is visible
	 * @author dheeraj.rajput
	 * @Created 30 April 2018
	 */
	@Override
	public boolean verifyAuditsTabVisibilityInAuditHistoryModal()
	{
		findVisibleElement(auditHistoryModal, Speed.slow);
		return isDisplayed(auditHistory_AuditsTab, Speed.slow);
	}

	/**
	 * Verify Versions tab title in audit history tab
	 *
	 * @return true
	 *         if Versions tab is visible
	 * @author dheeraj.rajput
	 * @Created 30 April 2018
	 */
	@Override
	public boolean verifyVersionsTabVisibilityInAuditHistoryModal()
	{
		findVisibleElement(auditHistoryModal, Speed.slow);
		return isDisplayed(auditHistory_VersionsTab, Speed.slow);
	}

	/**
	 * Verify Accessed by tab title in audit history tab
	 *
	 * @return true
	 *         if Accessed by tab is visible
	 * @author dheeraj.rajput
	 * @Created 30 April 2018
	 */
	@Override
	public boolean verifyAccessedbyTabVisibilityInAuditHistoryModal()
	{
		findVisibleElement(auditHistoryModal, Speed.slow);
		return isDisplayed(auditHistory_AccessbyTab, Speed.slow);
	}

	/**
	 * Click on close button in audit history button
	 *
	 * @author dheeraj.rajput
	 * @Created 30 April 2018
	 */
	@Override
	public void clickCloseInAuditHistoryButton()
	{
		findVisibleElement(auditHistory_CloseButton, Speed.slow).click();
		findPresentElement(auditHistoryModalClosed, Speed.slow);
	}

	/**
	 * Verify delete message in Delete? modal
	 *
	 * @param messageToVerify
	 *        expected message to be verified
	 * @return true
	 *         if actual message matches expected message
	 * @author dheeraj.rajput
	 * @Created 30 April 2018
	 * @Updated
	 */
	@Override
	public boolean verifyDeleteMessage(String messageToVerify)
	{
		String fetchedDeleteMessage = findVisibleElement(deleteMsg, Speed.slow).getText().trim();
		return messageToVerify.trim().equals(fetchedDeleteMessage);

	}

	/**
	 * Click on delete button in Delete modal
	 *
	 * @Created 30 April 2018
	 * @Updated
	 */
	@Override
	public void clickDeleteInDeleteModal()
	{
		findVisibleElement(deleteModal, Speed.slow);
		findClickableElement(deleteModal_Delete).click();
		while (isDisplayed(deleting))
		{
			;
		}
	}

	/**
	 * @author hasmukh.prajapati
	 * @param filename
	 *        This method click "SelectedFile"
	 * @creation date 16/04/2018
	 *           Open Upload button option
	 * @param item
	 *        Upload button option to click on.
	 * @author paras.vankadi
	 */

	@Override
	public void clickOnSelectedFile(String filename)
	{
		WebElement elem = findVisibleElement(By.xpath(
				"(.//*[@id='file_module_mainTableId']//a[normalize-space(text())='" + filename.trim() + "'])[1]"),
				Speed.slow);
		elem.click();
	}

	/**
	 * @author hasmukh.prajapati
	 * @param optionToSelect
	 *        This method click on "MoreActionOfFileViewer"
	 * @creation date 17/04/2018
	 */

	@Override
	public void clickOnMoreActionOfFileViewer()
	{
		WebElement elem = findVisibleElement(moreAction, Speed.slow);
		elem.click();
	}

	/**
	 * @author hasmukh.prajapati
	 * @param optionToSelect
	 *        This method click on "gotoOptiOnOnMoreActionOfFileViewer"
	 * @creation date 17/04/2018
	 */

	@Override
	public void gotoOptiOnOnMoreActionOfFileViewer(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);
		WebElement elem = findVisibleElement(By.xpath(".//*[@id='document_preview_modal_right_main']//*[normalize-space(text())='" + optionResult.trim() + "']"), Speed.slow);
		elem.click();

	}

	/**
	 * @author hasmukh.prajapati
	 * @param option
	 *        This method verify "ActionOnAuditHistoryFromMoreAction"
	 * @creation date 17/04/2018
	 */

	@Override
	public boolean verifyActionOnAuditHistoryFromMoreAction(String option)
	{

		findVisibleElement(auditOnScoll, Speed.slow);
		return isDisplayed(By.xpath(".//*[@id='file_module_docAudit_Tbody']//tr[2]//td[@title='" + option.trim() + "']"), Speed.slow);

	}

	/**
	 * @author hasmukh.prajapati This method click
	 *         on"CloseButtonOfAuditHistoryDialogBox"
	 * @creation date 17/04/2018
	 */

	@Override
	public void clickOnCloseButtonOfAuditHistoryDialogBox()
	{
		WebElement elem = findVisibleElement(closeButton, Speed.slow);
		elem.click();
	}

	/**
	 * @author hasmukh.prajapati This method click on"PrintButtInFileViewer"
	 * @creation date 17/04/2018
	 */

	@Override
	public void clickOnPrintButtInFileViewer()
	{
		WebElement elem = findVisibleElement(printButton, Speed.slow);
		elem.click();

		if (isDisplayed(printSubmit))
		{
			WebElement elem1 = findVisibleElement(printSubmit, Speed.slow);
			elem1.click();
		}
		try
		{
			while (driver.getWindowHandles().size() <= 1)
			{
				;
			}
			switchWindow();
			findVisibleElement(printPreviewWindowHeader, Speed.slow);
			findVisibleElement(printPreviewCancelButton, Speed.slow).click();
			driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}

	/**
	 * @author hasmukh.prajapati This method click on "PrintInAdebReaderViewer"
	 * @creation date 17/04/2018
	 */

	public void clickOnPrintInAdebReaderViewer()
	{
		WebElement web_Element_To_Be_Hovered = driver.findElement(toolbar);
		Actions builder = new Actions(getDriver());
		builder.moveToElement(web_Element_To_Be_Hovered).build().perform();

		if (isDisplayed(iconButton))
		{
			WebElement elem1 = findVisibleElement(printIcon, Speed.slow);
			elem1.click();
		}

	}

	/**
	 * @author hasmukh.prajapati This method click on "CrossButtonInFileviewer"
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnCrossButtonInFileviewer()
	{
		WebElement elem1 = findVisibleElement(clickOnCross, Speed.slow);
		elem1.click();
	}

	/**
	 * @param fileName
	 * @author dharti.patel
	 *         click on More Action of Particular file
	 * @throws InterruptedException
	 * @throws IOException
	 * @creation date 25/04/2018
	 */
	@Override
	public void clickOnMoreActionOfParticularFile(String fileName) throws IOException, InterruptedException
	{
		searchFile(fileName);
		By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + fileName.trim() + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
		findClickableElement(moreOption, Speed.slow).click();

	}

	@Override
	public boolean verifyDetailsOnSendToDialog(String filename)
	{

		findVisibleElement(fileModuleThirdParty, Speed.slow);
		if (isDisplayed(modalTitle)
				&& isDisplayed(fileModuleThirdPartyService)
				&& isDisplayed(By.xpath(".//*[@class='modal-body overflowAuto']//*[normalize-space(text())='" + filename + "']"))
				&& isDisplayed(chooseService)
				&& isDisplayed(fileModuleThirdPartyModalClose))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * click on Send to Button In Action
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnSendToButtonFromActionButton(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);

		By option1 = By.xpath(".//*[@id='File_module_FolderNameDivID']//*[normalize-space(text())='" + optionResult.trim() + "']");
		WebElement elem1 = findVisibleElement(option1, Speed.slow);
		elem1.click();
	}

	/**
	 * verify Details of Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDetailsOnRecepientDialog(String serviceName)
	{
		findVisibleElement(fileModuleRecipient, Speed.slow);

		return (isDisplayed(By.xpath(".//*[@id='file_module_recipient_modal_id']//*[normalize-space(text())='" + serviceName.trim() + "']"))
				&& isDisplayed(recipientEmail)
				&& isDisplayed(recipientName1)
				&& isDisplayed(recipientMessage)
				&& isDisplayed(fileModalClose)
				&& isDisplayed(sendBtn));

	}

	/**
	 * Click on close Button of Adobe
	 */
	@Override
	public void clickOnCloseOfAdobeSignDialoobx()
	{
		WebElement ele = findVisibleElement(fileModalClose, Speed.slow);
		ele.click();
	}

	/**
	 * verify option of Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyOptionInNeedsToSignOnRecepientDialog()
	{

		findVisibleElement(fileModuleRecipient, Speed.slow);
		if (!isDisplayed(addRecipient))
		{
			return false;
		}

		WebElement ele = findVisibleElement(locThirdPartyServiceActionMenu, Speed.slow);
		ele.click();

		return (isDisplayed(needToSign)
				&& isDisplayed(inPersonSighner)
				&& isDisplayed(receivesCopy)
				&& isDisplayed(needToView)
				&& isDisplayed(specifyRecepient)
				&& isDisplayed(allowToEdit)
				&& isDisplayed(updateRecipients));

	}

	/**
	 * verify Error message of Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyEmailOfRecipient(String msg, String recipientName, String recipientEmail, String message)
	{
		findVisibleElement(fileModuleRecipient, Speed.slow);
		WebElement ele = findVisibleElement(recipiName, Speed.slow);
		ele.sendKeys(recipientName);
		WebElement ele1 = findVisibleElement(recipientEml, Speed.slow);
		ele1.sendKeys(recipientEmail);

		WebElement ele2 = findVisibleElement(recipientMessage, Speed.slow);
		ele2.sendKeys(message);

		if (isDisplayed(locInvalidRecipeintid))
		{
			String invalidmsg = driver.findElement(locInvalidRecipeintid).getText();
			if (msg.equals(invalidmsg))
			{
				return true;
			}
		}

		return false;

	}

	/**
	 * Set member in Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void setMember(String memberName)
	{
		WebElement addGroupMemberInputBox = findVisibleElement(recipientName, Speed.slow);
		addGroupMemberInputBox.clear();
		addGroupMemberInputBox.sendKeys(memberName);
		selectUserNameFromAutoSuggest(memberName);

	}

	/**
	 * verify Email and Name are not Editable After fill Details through autosuggest in Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDisabledRecipientNameAndEmailTextbox()
	{
		if (isDisplayed(recipientDisable) &&
				isDisplayed(emailDisabled))
		{
			return true;
		}

		return false;

	}

	/**
	 * click on close button of Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnCloseInRecipientDialog()
	{
		findVisibleElement(fileModuleRecipient, Speed.slow);
		WebElement ele = findVisibleElement(fileModalClose, Speed.slow);
		ele.click();
	}

	/**
	 * verify recipient token
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyRecipientToken(String token)
	{

		if (!isDisplayed(nameReci))
		{
			return false;
		}

		WebElement ele = findVisibleElement(recipientToken);

		String tokenName = ele.getText();

		if (!tokenName.equalsIgnoreCase(token))
		{
			return false;
		}

		if (!isDisplayed(recipientToken1))
		{
			return false;
		}

		if (!isDisplayed(disabled))
		{
			return false;
		}

		return true;

	}

	/**
	 * click on cancel button of Generated Token
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnCancelGeneratedToken()
	{
		WebElement ele = findVisibleElement(recipientToken1, Speed.slow);
		ele.click();
	}

	/**
	 * verify Empty text field on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyEmptyTextField()
	{
		WebElement ele = findVisibleElement(recipientName);
		String s = ele.getText();

		WebElement ele1 = findVisibleElement(reciEmail);
		String s1 = ele1.getText();

		return s.isEmpty() && s1.isEmpty();

	}

	/**
	 * verify Error Message of File size
	 */
	@Override
	public boolean verifyFileSizeMessage(String msg)
	{
		findVisibleElement(collaborateMess, Speed.slow);
		return (isDisplayed(By.xpath(".//*[normalize-space(text())='" + msg + "']")));

	}

	/**
	 * verify file on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyFilesOnSendToDialog(String fileName)
	{
		findVisibleElement(fileModuleThirdParty, Speed.slow);
		return isDisplayed(By.xpath(".//*[@id='file_module_third_party_services_modal_id_BODY']//*[normalize-space(text())='" + fileName.trim() + "']"));

	}

	/**
	 * verify Details of DocuSign Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDocuSignSendToModel(String serviceName)
	{
		findVisibleElement(fileModuleRecipient, Speed.slow);

		return (isDisplayed(By.xpath(".//*[@id='file_module_recipient_modal_id']//*[normalize-space(text())='" + serviceName.trim() + "']"))
				&& isDisplayed(recipientName)
				&& isDisplayed(recipientEml)
				&& isDisplayed(recipientMessage)
				&& isDisplayed(fileModalClose)
				&& isDisplayed(fileModuleRecepientModalId));

	}

	/**
	 * click on more Action option in MyFile
	 */
	@Override
	public void clickOnMoreActionOptionInMyFile(String fileName, String option) throws IOException, InterruptedException
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);
		searchFile(fileName);
		findVisibleElement(By.xpath("(//*[@id='file_module_mainTableId']//following::*[contains(text(),'" + fileName.trim() + "')]//preceding::*[@data-original-title='" + FileLabels.UI_TEXT_MOREACTIONS + "'])[last()]")).click();
		findVisibleElement(By.xpath("(.//*[@data-original-title='" + FileLabels.UI_TEXT_MOREACTIONS + "' and @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "'])[1]")).click();
		if (isDisplayed(deleteMsg))
		{
			findVisibleElement(deleteMsg);

			if (isDisplayed(By.id("file_module_deleteFoldDocModal_okForRevokeDelete")))
			{
				findClickableElement(By.id("file_module_deleteFoldDocModal_okForRevokeDelete")).click();
			}
			else
			{

				findClickableElement(deleteModal_Delete).click();
			}
		}
		while (isDisplayed(deleting))
		{
			;
		}

	}

	/**
	 * Verify file in MyFile
	 */
	@Override
	public boolean verifyFileInMyFile(String fileName)
	{
		if (isDisplayed(By.xpath(".//*[@id='file_module_mainTableId']//following::*[normalize-space(text())='" + fileName.trim() + "']")))
		{
			return true;
		}
		return false;
	}

	/**
	 * Verify Tagging interface displayed or not
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean checkTagginginterface()
	{

		return (isDisplayed(DocuSign));

	}

	/**
	 * click on send to recipient modal
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnSendButtonInRecipientDialog()
	{
		WebElement ele = findVisibleElement(fileModuleRecepientModalId, Speed.slow);
		ele.click();
		while (isDisplayed(fileModuleRecepientModalId))
		{
			;
		}
	}

	/**
	 * @author dharti.patel
	 *         Verify files
	 * @param fileName
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Override
	public boolean verifyFiles(String fileName)
	{
		return (isDisplayed(By.xpath(".//*[@id='file_module_mainTableId']//following::a[normalize-space(text())='" + fileName.trim() + "']")));
	}

	/**
	 * @author dharti.patel
	 * @param option
	 * @return
	 * 		Verify option in more Action of Particular file
	 */
	@Override
	public boolean verifyMoreActionOptionOfParticularFile(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);

		if (isDisplayed(By.xpath(".//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + optionResult.trim() + "']")))
		{
			return true;
		}

		return false;
	}

	/**
	 * @author dharti.patel
	 * @param fileName
	 *        Click on More Action In My Files
	 */
	@Override
	public void clickOnMoreActionMyFiles(String fileName)
	{
		try
		{
			searchFile(fileName);
			findVisibleElement(By.xpath("(//*[@id='file_module_mainTableId']//following::*[contains(text(),'" + fileName.trim() + "')]//preceding::*[@data-original-title='More actions'])[last()]")).click();

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}

	/**
	 * @author dharti.patel
	 *         Click on Action Button
	 */
	@Override
	public void clickOnActionButton()
	{
		WebElement actionBtn = findVisibleElement(locActionBtn);
		if (isDisplayed(locActionBtn) && actionBtn.isEnabled())
		{
			actionBtn.click();
		}
	}

	/**
	 * @author dharti.patel
	 * @param fileName
	 *        get File extension
	 */
	@Override
	public String getFileExtension(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * @author dharti.patel
	 * @param fileExtension
	 * @param option
	 *        Verify SendTo option in Action Button
	 */
	@Override
	public boolean verifySendToInActionButton(String fileExtension, String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);

		if (isDisplayed(By.xpath("(.//*[@id='file_module_action_button' and  @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "'])[1]")))
		{
			WebElement op = findVisibleElement(By.xpath("(.//*[@id='file_module_action_button' and  @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "'])[1]"));

			if (fileExtension.equals("placeholder"))
			{

				if (isDisplayed(By.xpath("(.//*[@id='file_module_action_button' and @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "'])[1]")))
				{

					WebElement locClass = findVisibleElement(By.xpath(".//*[@id='file_module_action_button' and @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "']//preceding::*[@class='disabled']"));

					if (locClass.getAttribute("class").equals("disabled"))
					{
						return true;
					}
				}
			}

			return op.isEnabled();

		}

		return false;
	}

	/**
	 * @author dharti.patel
	 *         click on check out option in Check out Model
	 */
	@Override
	public void clickOnCheckOut()
	{
		findVisibleElement(locCheckinOutPage, Speed.slow);
		WebElement locCheckOut = findVisibleElement(locCheckOutBtn, Speed.slow);
		locCheckOut.click();

	}

	/**
	 * verify Send to option In Action Button
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifySendToDisabledInActionButton(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);

		if (isDisplayed(By.xpath("(.//*[@id='file_module_action_button' and @aria-expanded='true']//following::a[normalize-space(text())='" + optionResult.trim() + "'])[1]")))
		{

			WebElement locClass = findVisibleElement(By.xpath("//*[normalize-space(text())='" + optionResult.trim() + "']//preceding::*[@id='file_module_action_button' and @aria-expanded='true']//following::*[@class='disabled']"));
			if (locClass.getAttribute("class").equals("disabled"))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * @author dharti.patel
	 *         click on Close Button of Checked out Window
	 */
	@Override
	public void clickOnCloseInCheckedOutWindow()
	{
		findVisibleElement(locCheckinOutPage, Speed.slow);
		findVisibleElement(locCloseBtnCheckOutModel).click();
	}

	/**
	 * @author dharti.patel
	 *         verify More Action option In Viewer
	 */
	@Override
	public boolean verifyMoreActionOptionInViewer(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);
		if (isDisplayed(By.xpath(".//*[@data-original-title='" + FileLabels.UI_TEXT_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + optionResult.trim() + "']")))
		{
			return true;
		}
		return false;
	}

	/**
	 * @author dharti.patel
	 *         Click on cancel Link in check out window
	 */
	@Override
	public void clickOnCancelCheckOut()
	{
		findVisibleElement(locCheckinOutPage);
		WebElement locCancelCheckOut = findVisibleElement(By.xpath(".//*[@id='CHECK_IN_CHECK_OUT_PAGE_MODAL_BODY']//following::*[normalize-space(text())='" + FileLabels.DOCUMENT_CHECKOUT_MODAL_LABEL_CANCELCHECKOUT + "']"), Speed.slow);
		locCancelCheckOut.click();

	}

	/**
	 * @author dharti.patel
	 *         Verify Service on Send to Model
	 */
	@Override
	public boolean verifyServiceOnSendToModel(String serviceName)
	{
		findVisibleElement(By.xpath(".//*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + "']"), Speed.slow);

		int totalService = driver.findElements(By.xpath(".//*[@class='media-body']//following::*[@class='marg0 linkColor']//strong")).size();

		for (int i = 0; i < totalService; i++)
		{
			if (totalService == 1)
			{
				if (serviceName.trim().equals(findVisibleElement(By.xpath(".//*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + "']//following::*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_CHOOSEANYONESERVICETOSENDABOVESELECTEDITEM + "']//following::*[normalize-space(text())='" + serviceName.trim() + "']")).getText()))
				{
					return true;
				}

			}

		}

		return false;

	}

	/**
	 * @author dharti.patel
	 *         Click on Close Button on Send to Model
	 */
	@Override
	public void clickOnCloseButtonSendToModel()
	{
		findVisibleElement(By.xpath(".//*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + "']"), Speed.slow);
		if (isDisplayed(locCloseBtnThirdPartyService))
		{
			findVisibleElement(locCloseBtnThirdPartyService).click();

		}
		else
		{
			findVisibleElement(locCustomOkMsg).click();
		}

	}

	/**
	 * Click Action button
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickActionButton()
	{
		WebElement actionsButton = findVisibleElement(locActionBtn);
		if (actionsButton.isEnabled())
		{
			actionsButton.click();
		}
	}

	/**
	 * Click close button of Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickCloseButtonOnSendToDialog()
	{
		WebElement ele = findVisibleElement(confirmMessageModel, Speed.slow);
		ele.click();
	}

	/**
	 * Verify Error Message on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyErrorMessageForFileSendTo(String smg)
	{

		findClickableElement(fileModuleRecipient, Speed.slow);

		WebElement ele = findVisibleElement(fileModuleRecepientModalId);
		ele.click();

		WebElement ele1 = findVisibleElement(collaborateCustomerModalMsg);
		String error = ele1.getText();
		return error.contains(smg);

	}

	/**
	 * Enter details on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void enterDetailsIntoSendToDialog(String enterName, String email, String message)
	{
		setMember(enterName);
		WebElement ele2 = findVisibleElement(locRecipientMsg);
		ele2.sendKeys(message);

	}

	/**
	 * Click Serice on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnSpecifiedServiceFromSendToDialog(String serviceName)
	{
		findVisibleElement(fileModuleThirdParty, Speed.slow);
		WebElement ele = findVisibleElement(By.xpath(".//*[@id='file_module_third_party_services_modal_id_BODY']//*[normalize-space(text())='" + serviceName.trim() + "']"), Speed.slow);
		ele.click();
	}

	/**
	 * @author dharti.patel
	 *         Click on option in More Action
	 */
	@Override
	public void clickOnOptionFromFileMoreAction(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);
		By option1 = By.xpath("(.//*[@class='icon icon-actions dropdown-toggle' and @aria-expanded='true']//following::*[normalize-space(text())='" + optionResult.trim() + "'])[1]");
		WebElement elem1 = findVisibleElement(option1, Speed.slow);
		elem1.click();

	}

	// paras.vankadi
	/**
	 * click on Only New File Button
	 *
	 * @author paras.vankadi
	 */
	@Override
	public void clickNewFileButton()
	{

		findVisibleElement(fileBanner);

		findClickableElement(fileBanner_New_Button, Speed.slow).click();

	}

	/**
	 * click on Only Upload File Button
	 *
	 * @author paras.vankadi
	 */
	@Override
	public void clickUploadFileButton()
	{

		findVisibleElement(fileBanner);

		findClickableElement(fileBanner_Upload_Button, Speed.slow).click();

	}

	/**
	 * verify New button option
	 *
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyNewWordDocument()
	{

		return isDisplayed(newWordDocumentName) && isDisplayed(addFolder_Details_chooseButton) && isDisplayed(office365CommonClose) && isDisplayed(officeSaveButton) && isDisplayed(officeSaveButton) && isDisplayed(office365Common_TITLE);
	}

	/**
	 * verify Expand All And Collapse All link for World,powerpoint and Excels
	 *
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyExpandallAndCollapseAlllink()
	{

		return isDisplayed(expandLink, Speed.slow) && isDisplayed(collapseLink, Speed.slow);
	}

	/**
	 * Add folder at specific location in Office Online
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param folderName
	 *        name of the folder
	 * @param destination
	 *        location in which folder has to be created
	 */
	@Override
	public void addNewWordDocumentAtSpecificLocation(String folderName, String destination)
	{
		findPresentElement(modalContent, Speed.slow);

		WebElement folderNameInput = findVisibleElement(folder_Details_NameInput, Speed.slow);
		folderNameInput.clear();
		folderNameInput.sendKeys(folderName.trim());
		clickChooseButton();
		findPresentElement(modalContent, Speed.slow);
		selectFolderNameInSelectLocation(destination);
		clickSelectInChooseLocationModal();
	}

	/**
	 * Save Document in Office Online
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @author paras.vankadi
	 */
	@Override
	public void clickNewWordDocumentSave()
	{

		findClickableElement(officeSaveButton, Speed.slow).click();

	}

	/**
	 * Folder location popup modules is close
	 *
	 * @author paras.vankadi
	 */
	@Override
	public void closeAddFolderLocationModal()
	{

		findClickableElement(addFolderLocationModalClose, Speed.slow).click();

	}

	/**
	 * verify folder name base on permission TC2737
	 *
	 * @param folderName
	 *        name of the folder
	 * @author paras.vankadi
	 */

	@Override
	public Boolean verifyFolderNameInFileSelectionOFOnlineOfficeDocument(String folder)
	{
		By folderName = By.xpath(".//*[@id='file_module_innerModalDiv']//*[normalize-space(text())='" + folder.trim() + "']");
		findVisibleElement(file_module_addFolder_location, Speed.slow);

		return isDisplayed(folderName);
	}

	/**
	 * verify Upload and New Button
	 *
	 * @param option
	 * @author paras.vankadi
	 */

	@Override
	public boolean verifyFileHeaderOption(String option)
	{

		By oprationAllForAddButton = By.xpath("//*[normalize-space(text())='" + option.trim() + "']");
		return isDisplayed(oprationAllForAddButton);
	}

	/**
	 * verify Open New Tab Of Online Office Document
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param Documentname
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyOpenNewTabOfOnlineOfficeDocument(String documentName)
	{
		By onlineOfficeNewTab = By.xpath(".//*[@id='h_logoLink']//*[normalize-space(text())='" + documentName.trim() + "']");

		return isDisplayed(onlineOfficeNewTab, Speed.slow);
	}

	@Override
	public void gotoMoreActionsOFHover(String file, String operation)
	{
		gotoMoreActions(file, operation, "click");
	}

	/**
	 * click on More Action in your selected file
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param file
	 *        file name
	 * @param operation
	 *        option name
	 * @param action
	 *        type
	 *        click and hover for more action button
	 * @author paras.vankadi
	 */
	@Override
	public void gotoMoreActions(String file, String operation, String actionType)
	{
		try
		{
			searchFile(file);
			By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim() + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
			findClickableElement(moreOption, Speed.slow).click();

			By oprationXpath = By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation.trim() + "'])[1]");

			findPresentElement(moreOptionExpanded, Speed.slow);
			if (isDisplayed(oprationXpath))
			{
				if (actionType.equalsIgnoreCase(FileLabels.CLICK_ON_MORE_ACTION))
				{
					findClickableElement(oprationXpath).click();
				}
				else if (actionType.equalsIgnoreCase(FileLabels.HOVER_ON_MORE_ACTION))
				{
					moveToElement(oprationXpath);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * click on More Action in your selected file and also click on Sub menu
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param file
	 *        file name
	 * @param operation
	 *        option name
	 * @param subMenuOptionValue
	 *        new functionality i.e Open in word,ppt,xlsx
	 * @author paras.vankadi
	 */
	@Override
	public void clickOnSubmenuOptionOfFileMoreActions(String file, String operation, String subMenuOptionValue)
	{
		gotoMoreActions(file, operation, "hover");
		clickOnSubmenuOption(subMenuOptionValue);
	}

	/**
	 * click on Sub Menu for Online Office
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param subMenuOptionValue
	 *        new functionality i.e Open in word,ppt,xlsx
	 * @author paras.vankadi
	 */

	public void clickOnSubmenuOption(String subMenuOptionValue)
	{
		By subMenuOption = By.xpath(".//*[@class='subDropdwonWrapper']//a[normalize-space(.)='" + subMenuOptionValue + "']");

		findVisibleElement(subMenuOption, Speed.slow).click();

	}

	@Override
	public boolean verifySubmenuOption(String subMenuOptionValue)
	{
		By subMenuOption = By.xpath(".//*[@class='subDropdwonWrapper']//a[normalize-space(.)='" + subMenuOptionValue.trim() + "']");

		findVisibleElement(subMenuMoreActionFile, Speed.slow);

		return isDisplayed(subMenuOption);
	}

	/**
	 * verify on More Action in your selected file and also click on Sub menu
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param file
	 *        file name
	 * @param operation
	 *        option name
	 * @param subMenuOptionValue
	 *        new functionality i.e Open in word,ppt,xlsx
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyOnSubmenuOptionOfFile(String file, String operation, String subMenuOptionValue)
	{
		gotoMoreActions(file, operation, "hover");
		return verifySubmenuOption(subMenuOptionValue);
	}

	/**
	 * click on More Action in your selected file and also click on Sub menu in My Files
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param file
	 *        file name
	 * @param operation
	 *        option name
	 * @param subMenuOptionValue
	 *        new functionality i.e Open in word,ppt,xlsx
	 * @author paras.vankadi
	 */
	@Override
	public void gotoMoreActionsMyFiles(String file, String operation, String actionType)
	{
		try
		{
			searchFile(file);
			By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim() + "')])[last()])/../preceding-sibling::*//*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')]");
			findClickableElement(moreOption, Speed.slow).click();

			By oprationXpath = By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation.trim() + "'])[1]");

			findPresentElement(moreOptionExpanded, Speed.slow);
			if (isDisplayed(oprationXpath))
			{
				if (actionType.equals("click"))
				{
					findClickableElement(oprationXpath).click();
				}
				else if (actionType.equals("hover"))
				{
					moveToElement(oprationXpath);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void clickOnSubmenuOptionOfFileMyFiles(String file, String operation, String subMenuOptionValue)
	{
		gotoMoreActionsMyFiles(file, operation, "hover");
		clickOnSubmenuOption(subMenuOptionValue);
	}

	/**
	 * verify on More Action in your selected file and also click on Sub menu in My Files
	 * Word Document
	 * Excel Spreadsheet
	 * Powerpoint Presentation
	 *
	 * @param file
	 *        file name
	 * @param operation
	 *        option name
	 * @param subMenuOptionValue
	 *        new functionality i.e Open in word,ppt,xlsx
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyOnSubmenuOptionOfFileMyFiles(String file, String operation, String subMenuOptionValue)
	{
		gotoMoreActionsMyFiles(file, operation, "hover");
		return verifySubmenuOption(subMenuOptionValue);
	}

	/**
	 * verify New Button dropdown value
	 *
	 * @param option
	 * @author paras.vankadi
	 */
	@Override
	public Boolean verifyOptionOfNewButton(String option)
	{
		By oprationAllForNewButton = By.xpath("//*[normalize-space(text())='New']/following-sibling::*//*[normalize-space(text())='" + option.trim() + "']");
		return isDisplayed(oprationAllForNewButton);
	}

	/**
	 * verify Upload Button dropdown value
	 *
	 * @param option
	 * @author paras.vankadi
	 */
	@Override
	public Boolean verifyOptionOfUploadButtonFile(String option)
	{

		By oprationAllForAddButton = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_UPLOAD_BUTTION + "']/following-sibling::*//*[normalize-space(text())='" + option.trim() + "']");
		return isDisplayed(oprationAllForAddButton);
	}

	@Override
	public void switchWindow(int index)
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		System.out.println(highQCollaborateWindows);
		driver.switchTo().window((String) highQCollaborateWindows.toArray()[index]);
	}

	@Override
	public boolean verifyAccessRestricted()
	{
		By verifyAccessRestricted = By.xpath("//*[normalize-space(text())='Access restricted']");

		return isDisplayed(verifyAccessRestricted);
	}

	/**
	 * Open Upload button option
	 *
	 * @param item
	 *        Upload button option to click on.
	 * @author paras.vankadi
	 */
	@Override
	public void selectItemFromUpload(String item)
	{
		WebElement itemToClick;
		findVisibleElement(fileBanner);
		if (isDisplayed(fileBanner_Upload_Button, Speed.slow))
		{
			findClickableElement(fileBanner_Upload_Button, Speed.slow).click();
			findVisibleElement(fileBanner_DropDownExpanded);
			itemToClick = findVisibleElement(By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_UPLOAD_BUTTION + "'])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"), 5);
			itemToClick.click();
			findVisibleElement(openedModal, Speed.slow);
		}
	}

	/**
	 * Open New button option
	 *
	 * @param item
	 *        New button option to click on.
	 * @author paras.vankadi
	 */
	@Override
	public void selectItemFromNew(String item)
	{
		WebElement itemToClick;
		findVisibleElement(fileBanner);
		if (isDisplayed(fileBanner_New_Button, Speed.slow))
		{
			findClickableElement(fileBanner_New_Button, Speed.slow).click();
			findVisibleElement(fileBanner_DropDownExpanded);
			itemToClick = findVisibleElement(By.xpath("(//*[contains(@class,'rightSideSection')]//*[normalize-space(text())='" + FileLabels.FILES_NEW_BUTTON + "'])[1]/following::*[contains(text(),'" + item.trim() + "')][1]"), 5);
			itemToClick.click();
			findVisibleElement(openedModal, Speed.slow);
		}
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        to be sent
	 * @created on 15/05/2018
	 */
	@Override
	public void sendTextInVersionNotesTextBox(String text)
	{
		WebElement versionNoteInput = findVisibleElement(placeHolder_DetailsTab_VersionNotes, Speed.slow);
		versionNoteInput.clear();
		versionNoteInput.sendKeys(text);
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @created on 30/05/2018
	 */

	@Override
	public boolean verifySpecialCharactersForOnlinOffice(String msg)
	{
		By verifySpecialCharactersMsg = By.id("file_module_addFile_name_365_pID");
		WebElement ele1 = findVisibleElement(verifySpecialCharactersMsg);
		String error = ele1.getText();

		return error.contains(msg);

	}

	/**
	 * Folder location popup modules is close
	 *
	 * @author paras.vankadi
	 */
	@Override
	public void closeForOnlineDocumentCreateModal()
	{

		findClickableElement(closeForOnlineDocumentCreateModal, Speed.slow).click();

	}

	/**
	 * @param value
	 * @return
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifySpecialCharactersForFileSharIconOnlinOffice(String value)
	{
		By verifySpecialCharactersMsg = By.id("OFFICE_ONLINE_SPECIAL_CHARACTER_VALIDATION_MODAL_ID_BODY");
		WebElement ele1 = findVisibleElement(verifySpecialCharactersMsg);
		String error = ele1.getText();

		return error.contains(value);
	}

	/**
	 * delete document from Action menu in file module
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnDeleteButtonFromActionButton(String option)
	{

		if (!isDisplayed(noFilesOrFolder))
		{
			clickSelectAllCheckbox();
			clickActionButton();

			WebElement optionEle = findVisibleElement(By.xpath(".//*[@id='File_module_FolderNameDivID']//*[normalize-space(text())='" + option.trim() + "']"), Speed.slow);
			optionEle.click();

			findVisibleElement(deleteFoldDocModal, Speed.slow);

			if (isDisplayed(OkForRevokeDelete))
			{
				WebElement revokeDeleteEle = findVisibleElement(OkForRevokeDelete, Speed.slow);
				revokeDeleteEle.click();
			}

			if (isDisplayed(DeleteFoldDocModalOk))
			{
				WebElement deleteEle = findVisibleElement(DeleteFoldDocModalOk, Speed.slow);
				deleteEle.click();
			}

			if (isDisplayed(okForRevokeDelete))
			{
				WebElement deleteEle = findVisibleElement(okForRevokeDelete, Speed.slow);
				deleteEle.click();
			}

			if (isDisplayed(unlockRevokeAndDelete))
			{
				WebElement deleteEle = findVisibleElement(unlockRevokeAndDelete, Speed.slow);
				deleteEle.click();
			}

		}

	}

	/**
	 * select option from action menu in file module
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */
	@Override
	public void selectOptionFromActionMenu(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);
		By option1 = By.xpath(".//*[@id='File_module_FolderNameDivID']//*[normalize-space(text())='" + optionResult.trim() + "']");
		WebElement selectSend = findVisibleElement(option1, Speed.slow);
		selectSend.click();
	}

	/**
	 * click on menu item on sent for signature link in file module
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickMenuItemOnSentForSignature(String option)
	{
		WebElement menuItem = findVisibleElement(By.xpath(".//*[normalize-space(.)='" + option.trim() + "']"), Speed.slow);
		menuItem.click();
	}

	/**
	 * goto link from view Menu dropdown from file module
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */

	@Override
	public void gotoLinkFromViewMenuDropDown(String option)
	{
		WebElement selectLinkFromViewMenu = findVisibleElement(By.xpath(".//a[normalize-space(.)='" + option.trim() + "']"), Speed.slow);
		selectLinkFromViewMenu.click();
	}

	/**
	 * verify file on list view
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyFileOnListView(String filename)
	{

		if (isDisplayed(By.xpath(".//a[normalize-space(.)='" + filename.trim() + "']")))
		{
			WebElement document = findVisibleElement(By.xpath(".//a[normalize-space(.)='" + filename.trim() + "']"), Speed.slow);
			document.click();

			if (!isDisplayed(viewerResponse))
			{
				return false;
			}

			WebElement clickoncross = findVisibleElement(clickOnCross, Speed.slow);
			clickoncross.click();

		}
		return true;

	}

	/**
	 * verify minicard of sender(user)
	 *
	 * @param user
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifySenderMiniCard(String user)
	{
		WebElement minicard = findVisibleElement(By.xpath(".//*[@id='file_module_mainTableId']//a[normalize-space(.)='" + user.trim() + "']"), Speed.slow);
		minicard.click();

		return isDisplayed(userinfo);

	}

	/**
	 * click On MoreAction In SentForSignatureTab in file module
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnMoreActionInSentForSignatureTab(String filename)
	{
		WebElement moreaction = findVisibleElement(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + filename.trim() + "')])[last()]/preceding::*[contains(@data-original-title,'More actions')])[1]"), Speed.slow);
		moreaction.click();
	}

	/**
	 * verify Options In MoreAction On Sent For Signature link
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyOptionsInMoreActionOnSentForSignature(String option)
	{

		return (isDisplayed(By.xpath(".//*[@id='file_module_mainTableId']//a[normalize-space(.)='" + option.trim() + "']")));

	}

	/**
	 * verify user link in list view
	 *
	 * @param user1,user2
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyUserLink(String... users)
	{
		WebElement filetablecontent = findVisibleElement(tableContent, Speed.slow);
		String username = filetablecontent.getText();

		for (String user : users)
		{
			if (!username.contains(user))
			{
				return false;
			}
		}

		if (!username.contains("1 more"))
		{
			return false;
		}

		return true;

	}

	/**
	 * click On MoreLink In SentForSignature
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnMoreLinkInSentForSignature()
	{
		WebElement ele = findVisibleElement(more, Speed.slow);
		ele.click();
	}

	/**
	 * verify UserDetails On MoreLink in sent for signature link
	 *
	 * @param users
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyUserDetailsOnMoreLink(String users)
	{

		findVisibleElement(awaitingUser, Speed.slow);

		return (isDisplayed(By.xpath(".//*[@id='AwaitingUserListModalID_BODY']//*[normalize-space(.)='" + users.trim() + "']")));

	}

	/**
	 * verify Close On MoreLink in sent for signature link
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyCloseOnMoreLink()
	{

		findVisibleElement(awaitingUser, Speed.slow);

		return (isDisplayed(awaintingClose) && isDisplayed(awaitingUserClose));

	}

	/**
	 * verify file On MoreLink in sent for signature
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyfileOnMoreLink(String fileName)
	{

		findVisibleElement(awaitingUser, Speed.slow);

		return isDisplayed(By.xpath(".//*[@id='sendInvitationAlertMsg']//*[normalize-space(.)='" + fileName.trim() + "']"));

	}

	/**
	 * clicking on cancel button of recipinet of morelink
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnCancelButtonOfRecipientOfMoreLink()
	{
		WebElement closebtn = findVisibleElement(awaitingUserListClose, Speed.slow);
		closebtn.click();

	}

	/**
	 * click On MoreAction button From SentForSignature link
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnMoreActionFromSentForSignatureTab(String filename)
	{

		WebElement moreaction = findVisibleElement(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + filename.trim() + "')])[last()]/following::*[contains(@data-original-title,'More actions')])[1]"), Speed.slow);

		if (moreaction.isEnabled())
		{
			moreaction.click();
		}
	}

	@Override
	public void clickOnMoreActionFromThumbnailsView(String filename)
	{

		WebElement moreaction = findVisibleElement(By.xpath("(.//*[@class='fileTitle']//span[@title='" + filename + "']//following::*[@data-original-title='More actions'])[1]"), Speed.slow);

		if (moreaction.isEnabled())
		{
			moreaction.click();
		}
	}

	/**
	 * verify remind/revoke button at column and thumbnailview
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyOptionInMoreActionOfThumbnailView(String option)
	{
		return isDisplayed(By.xpath(".//a[normalize-space(.)='" + option.trim() + "']"), Speed.slow);
	}

	/**
	 * click on sent signature link module file side
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnSentForSignatureLink()
	{
		driver.switchTo().defaultContent();
		WebElement sentforsignaturelink = findVisibleElement(sentForSingature, Speed.slow);
		sentforsignaturelink.click();

	}

	/**
	 * verify user At column view
	 *
	 * @param user1,user2
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyUserLinkAtColumnView(String... users)
	{

		clickOnSentForSignatureLink();
		clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		gotoLinkFromViewMenuDropDown(FileLabels.COLUMNVIEW);

		WebElement userlist = findVisibleElement(esignList, Speed.slow);
		String username = userlist.getText();

		for (String user : users)
		{
			if (!username.contains(user))
			{
				return false;
			}
		}

		if (!username.contains("1 more"))
		{
			return false;
		}

		return true;

	}

	/**
	 * verify file On MoreLink in sent for signature
	 *
	 * @param filename
	 *        , users
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyDetailsOnMoreLink(String fileName, String... users)
	{
		findVisibleElement(more, Speed.slow);

		WebElement ele = findVisibleElement(more, Speed.slow);
		ele.click();

		findVisibleElement(awaitingUser, Speed.slow);
		boolean flag = true;
		for (String user : users)
		{
			if (!isDisplayed(By.xpath(".//*[@id='AwaitingUserListModalID_BODY']//*[normalize-space(.)='" + user.trim() + "']")))
			{
				flag = false;
			}
		}
		if (flag)
		{
			return isDisplayed(By.xpath(".//*[@id='sendInvitationAlertMsg']//*[normalize-space(.)='" + fileName.trim() + "']"))
					&& isDisplayed(awaintingClose) && isDisplayed(awaitingUserClose);
		}
		else
		{
			return false;
		}
	}

	/**
	 * verify file on list view at thumbnail view
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyFileOnListViewAtThumbnailView(String filename)
	{

		if (isDisplayed(By.xpath(".//*[@class='fileTitle']//*[normalize-space(.)='" + filename.trim() + "']")))
		{
			WebElement document = findVisibleElement(By.xpath(".//*[normalize-space(.)='" + filename.trim() + "']"), Speed.slow);
			document.click();

			if (!isDisplayed(viewerResponse))
			{
				return false;
			}

			WebElement clickoncross = findVisibleElement(clickOnCross, Speed.slow);
			clickoncross.click();

		}
		return true;

	}

	/**
	 * verify sender mini card at thumbnail view
	 *
	 * @param user
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifySenderMiniCardAtThumbnailView(String user)
	{
		WebElement minicard = findVisibleElement(By.xpath(".//*[@id='file_module_awaiting_esign_list']//*[normalize-space(.)='" + user + "']"), Speed.slow);
		minicard.click();

		return isDisplayed(userinfo);

	}

	/**
	 * cancel user minicard
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void cancelMiniCard()
	{

		if (isDisplayed(userInfoDropDown))
		{
			WebElement cancelbutton = findVisibleElement(userInfoDropDown, Speed.slow);
			cancelbutton.click();

		}

	}

	/**
	 * verify user link at thumnail view
	 *
	 * @param user1,user2
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyUserLinkAtThumbnailView(String... users)
	{
		WebElement userlink = findVisibleElement(paddBotl, Speed.slow);
		String username = userlink.getText();

		for (String user : users)
		{
			if (!username.contains(user))
			{
				return false;
			}
		}

		if (!username.contains("1 more"))
		{
			return false;
		}

		return true;

	}

	/**
	 * Verify Add fields for your recipients modal
	 *
	 * @author dharti.patel
	 */
	@Override
	public boolean verifyAddRecipientFieldsOnTaggingInterface()
	{

		return (isDisplayed(addFieldsForRecipient)
				&& isDisplayed(closeIcon)
				&& isDisplayed(addField)
				&& isDisplayed(sendWithoutFields));

	}

	/**
	 * Click on
	 * Send without
	 * fields of
	 * recipients modal**
	 *
	 * @author dharti.patel
	 * @throws InterruptedException
	 */

	@Override
	public void sendDocumentWithoutFields() throws InterruptedException
	{
		findVisibleElement(scopeModal, Speed.slow);
		WebElement sendWithoutFieldButton = findVisibleElement(sendWithoutField);
		sendWithoutFieldButton.click();
		Thread.sleep(50000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
	}

	/**
	 * enter recipient details on recipinet dialog box
	 *
	 * @param message,documentRecipientsData
	 * @author hasmukh.prajapati
	 */

	@Override
	public void enterDetailsMoreThanTwoRecipient(String message, DocumentRecipientsData... recipients) throws InterruptedException
	{
		int i = 0;
		do
		{
			if (i == 0)
			{
				findVisibleElement(recipientName).sendKeys(recipients[i].getName());
				findVisibleElement(reciEmail).sendKeys(recipients[i].getEmail());
				if (recipients[i].getThirdPartyServiceAction() != null)
				{
					selectOptionFromDropDown(recipientAction, recipientActopnDropDown, recipientActionDropdownList, recipients[i].getThirdPartyServiceAction());
				}
			}
			else
			{
				findVisibleElement(addRecipientLink).click();
				findVisibleElement(By.xpath(".//*[@id='recipientName_" + i + "-tokenfield']"), Speed.slow).sendKeys(recipients[i].getName());
				findVisibleElement(By.xpath(".//*[@id='recipientEmail_" + i + "']")).sendKeys(recipients[i].getEmail());
				if (recipients[i].getThirdPartyServiceAction() != null)
				{
					selectOptionFromDropDown(By.xpath(".//*[@data-id='thirdpartyServiceActionMenu_" + i + "']"),
							By.xpath(".//*[@data-id='thirdpartyServiceActionMenu_" + i + "']//following-sibling::*[contains(@class,'dropdown-menu open') and @role='combobox']"),
							".//*[@data-id='thirdpartyServiceActionMenu_" + i + "']//following-sibling::*[contains(@class,'dropdown-menu open') and @role='combobox']//*[@role='option']",
							recipients[i].getThirdPartyServiceAction());
				}
			}
			i++;
		}
		while (i < recipients.length);

		findVisibleElement(recipientMessage).sendKeys(message);

	}

	/**
	 * clicking on send button from tagging interface
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnSendFromTaggingInterface() throws InterruptedException
	{

		findVisibleElement(taggingInterface, Speed.slow);

		driver.switchTo().frame(driver.findElement(tagging));

		Thread.sleep(5000);

		findVisibleElement(sidebar, Speed.slow);

		WebElement sendbtn = findVisibleElement(send, Speed.slow);
		sendbtn.click();

		findVisibleElement(scopeModal, Speed.slow);

		WebElement sendwithoutfield = findVisibleElement(sendWithoutField, Speed.slow);
		sendwithoutfield.click();

		Thread.sleep(10000);

	}

	/**
	 * click Option From more Action in sent for signature link
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnOptionFromMoreActionInSentForSignature(String option)
	{
		WebElement optionFromMoreAction = findVisibleElement(By.xpath(".//*[@class='dropdown inlineBlock open']//a[normalize-space(.)='" + option.trim() + "']"), Speed.slow);
		optionFromMoreAction.click();
	}

	/**
	 * verify Send Button on remind dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyRemindDialogSendButton()
	{
		findVisibleElement(sendRemider, Speed.slow);
		return isDisplayed(sendRemiderSendBtn);
	}

	/**
	 * verify main close button on remind dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyCloseDetailsOnRemindDialog()
	{
		findVisibleElement(sendRemider, Speed.slow);
		return (isDisplayed(By.id("file_module_third_party_service_remind_modal_id_MAIN_CLOSE_BUTTON")));

	}

	/**
	 * click On Cancel button On Remind Dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnCancelInRemindDialog()
	{
		WebElement cancelbtn = findVisibleElement(sendRemiderClosebtn, Speed.slow);
		cancelbtn.click();
	}

	/**
	 * click On Cancel button On Revoke Dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnCancelInRevokeDialog()
	{
		WebElement close = findVisibleElement(closeSignRevoke, Speed.slow);
		close.click();
	}

	@Override
	public void clickOnRevokeButton()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		WebElement ele = findVisibleElement(revokeModal);
		ele.click();
	}

	/**
	 * click on revoke Button in revoke dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnRevokeButtonInRevokeDialog()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		WebElement revokeSignRequest = findVisibleElement(revokeModal, Speed.slow);
		revokeSignRequest.click();
	}

	@Override
	public void clickOnSendButtonInRemindDialog()
	{
		WebElement sendbtn = findVisibleElement(sendRemiderSendBtn, Speed.slow);
		sendbtn.click();
	}

	/**
	 * verify message on reminder dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyReminderMessage()
	{
		return (isDisplayed(globalAlertMessageContainer) && isDisplayed(globalAlertMessageContainer));

	}

	/**
	 * verify more action button in sent for signature link
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyMoreActionButton(String filename)
	{
		return isDisplayed(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + filename.trim() + "')])[last()]/following::*[contains(@data-original-title,'More actions')])[1]"), Speed.slow);
	}

	/**
	 * verify Close Button On Revoke dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyCloseButtonOnRevoke()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		return isDisplayed(closeBtnRevoke);
	}

	/**
	 * verify revoke Sign Request On Revoke
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyrevokeSignRequestOnRevoke()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		return isDisplayed(revokeSignRequest);
	}

	/**
	 * verify Revoke Body On Revoke Dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyRevokeBodyOnRevoke()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		return isDisplayed(revokeBody);
	}

	/**
	 * verify Close Sign Request On Revoke dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyCloseSignRequestOnRevoke()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		return isDisplayed(closeSignRevoke);
	}

	/**
	 * verify Revoke Modal On Revoke dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyRevokeModalOnRevoke()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		return isDisplayed(revokeModal);
	}

	/**
	 * verify global message on revoking
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyGlobalMessageOnRevoking()
	{
		return isDisplayed(globalAlertMsg, Speed.slow);
	}

	/**
	 * verify File In Sent For Signature Link
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyFileInSentForSignatureLink(String filename)
	{
		if (isDisplayed(By.xpath(".//*[@id='file_module_awaiting_esign_list']//a[normalize-space(.)='" + filename.trim() + "']"), Speed.slow))
		{
			return true;
		}
		else
		{
			WebElement ele = findVisibleElement(fileLink, Speed.slow);
			String fileCount = ele.getText();

			if (fileCount.equalsIgnoreCase("0"))
			{
				return true;
			}
			return false;
		}

	}

	/**
	 * verify status on file module
	 *
	 * @param filename,status
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyStatusOnFileModule(String filename, String status)
	{

		driver.switchTo().defaultContent();
		WebElement fileStatus = findVisibleElement(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + filename.trim() + "')])[last()]/following::*[contains(@class,'IpadPortraitNone colDrag1')])[1]"), Speed.slow);
		String statusName = fileStatus.getText();
		return statusName.equalsIgnoreCase(status);
	}

	/**
	 * click On RevokeSigningRequestLink In FileViewer
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnRevokeSigningRequestLinkInFileViewer()
	{
		findVisibleElement(documentPreview, Speed.slow);
		WebElement revokeSigningRequestEle = findVisibleElement(revokeSigningRequest, Speed.slow);
		revokeSigningRequestEle.click();
	}

	/**
	 * click On Revoke Button on RevokeSigningRequestLink dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnRevokeButtonFromFileViewer()
	{
		findVisibleElement(revokeSignRequest, Speed.slow);
		WebElement revokeModalele = findVisibleElement(revokeModal, Speed.slow);
		revokeModalele.click();
	}

	/**
	 * verify Global Msg Revoked Successfully in file viewer
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyGlobalMsgRevokedSuccessfully()
	{
		return isDisplayed(globalMsg, Speed.slow);

	}

	/**
	 * click On Action Button In TaggingInterface
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnActionButtonInTaggingInterface() throws InterruptedException
	{
		findVisibleElement(taggingInterface, Speed.slow);

		driver.switchTo().frame(driver.findElement(tagging));

		Thread.sleep(10000);

		findVisibleElement(siteHeader, Speed.slow);

		// Thread.sleep(5000);

		WebElement ele = findVisibleElement(menuOtherActions, Speed.slow);
		ele.click();

	}

	/**
	 * click On Option In ActionButton from thirdpartyservice
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */

	@Override
	public void clickOnOptionInActionButton(String option) throws InterruptedException
	{
		WebElement optionele = findVisibleElement(By.xpath(".//*[@id='menuOtherActions']//button[normalize-space(.)='" + option.trim() + "']"), Speed.slow);
		optionele.click();
		Thread.sleep(10000);

	}

	/**
	 * verify Message For Deleting File With Sent For Signature Status
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyMessageForDeletingFileWithSentForSignatureStatus(String msg)
	{
		findVisibleElement(deleteFoldDocModal, Speed.slow);
		WebElement ele = findVisibleElement(deleteMsg, Speed.slow);
		String message = ele.getText();

		if (!message.equals(msg.trim()))
		{
			return false;
		}

		return (isDisplayed(deleteFoldDoc)
				&& isDisplayed(DeleteFoldDocModalOk));

	}

	/**
	 * verify Message For Deleting File With Voided Status
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyMessageForDeletingFileWithVoidedStatus(String msg)
	{
		findVisibleElement(modalHeader, Speed.slow);
		WebElement ele = findVisibleElement(deleteMsg, Speed.slow);
		String message = ele.getText();

		if (!msg.equals(message))

		{
			return false;
		}

		return (isDisplayed(deleteFoldDoc) && isDisplayed(deleteModal_Delete));

	}

	/**
	 * verify Message For Deleting File With Draft Status
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyMessageForDeletingFileWithDraftStatus(String msg)
	{
		findVisibleElement(modalHeader, Speed.slow);
		WebElement ele = findVisibleElement(deleteMsg, Speed.slow);
		String message = ele.getText();

		if (!msg.equals(message) && isDisplayed(deleteFoldDoc)
				&& isDisplayed(OkForRevokeDelete))
		{
			return false;
		}

		return true;

	}

	/**
	 * verify Close Button on remind dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyRemindDialogCloseButton()
	{
		findVisibleElement(sendRemider, Speed.slow);
		return isDisplayed(sendRemiderClosebtn);
	}

	/**
	 * verify Send Remider Body on remind dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyRemindDialogSendRemiderBody()
	{
		findVisibleElement(sendRemider, Speed.slow);
		return isDisplayed(sendRemiderBody);
	}

	/**
	 * verify Send Reminder on remind dialog
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyRemindDialogSendRemider()
	{
		findVisibleElement(sendRemider, Speed.slow);
		return isDisplayed(sendRemider);
	}

	/**
	 * verify Document On TaggingInterface in thirdparty
	 *
	 * @param option
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDocumentOnTaggingInterface()
	{
		return isDisplayed(imagePath, Speed.slow);

	}

	/**
	 * verify status on file module
	 *
	 * @param filename,status
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyStatus(String filename, String status)
	{

		try
		{

			Thread.sleep(10000);
			driver.switchTo().defaultContent();
			Thread.sleep(2000);

		}
		catch (InterruptedException e)
		{

			e.printStackTrace();
		}

		gotoFileModule();
		WebElement fileStatus = findVisibleElement(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + filename.trim() + "')])[last()]/following::*[contains(@class,'IpadPortraitNone colDrag1')])[1]"), Speed.slow);
		String statusName = fileStatus.getText();

		return statusName.equalsIgnoreCase(status);

	}

	@Override
	public void clickOnDiscardBtnOnDiscardDraftDialog()
	{
		findVisibleElement(By.xpath(".//*[@class='modal modal-new modal-sm']//*[@class='modal_wrap']"), Speed.slow);
		WebElement discardele = findVisibleElement(By.xpath(".//*[@class='btn btn-primary btn-lg ng-binding'][normalize-space(.)='Discard']"), Speed.slow);
		discardele.click();
	}

	/**
	 * verify File in sent for signature link
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyFileInSentForSignatureTab(String filename)
	{

		return isDisplayed(By.xpath(".//*[@id='file_module_awaiting_esign_list']//*[@title='" + filename.trim() + "']"));

	}

	/**
	 * verify details on message to allrecipient
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyDetailsOnMessageToAllRecipient()
	{

		findVisibleElement(editMessageDialog, Speed.slow);
		return (isDisplayed(messageToAll));

	}

	/**
	 * verify close button on message to allrecipient
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyDetailsCloseButtonOnEditRecipiet()
	{

		return (isDisplayed(CLOSE_SCOPE, Speed.slow));

	}

	@Override
	public void clickOnCancelBtnInEditMessage()
	{

		WebElement formGroupEle = findVisibleElement(CLOSE_SCOPE);
		formGroupEle.click();

	}

	/**
	 * verify details on message on text area in editrecipient
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyDetailsOnMessageEditeRecipient()
	{

		return (isDisplayed(messageTextArea, Speed.slow));

	}

	/**
	 * verify Missing Recipient Message
	 *
	 * @param enterName,email,message
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyMissingRecipientMessage(String expectedMessage)
	{
		findVisibleElement(editRecipientModal, Speed.slow);

		if (!isDisplayed(done))
		{
			return false;

		}

		WebElement formGroupEle = findVisibleElement(formGroup);
		formGroupEle.clear();

		WebElement doneEle = findVisibleElement(done);
		doneEle.click();

		WebElement messageContentEle = findVisibleElement(messageContent);
		String message = messageContentEle.getText();

		if (!message.equals(expectedMessage))
		{
			return false;
		}

		return true;

	}

	/**
	 * verify Upload Document Title
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyUploadDocumentTitle()
	{

		findVisibleElement(uploadDocument, Speed.slow);
		return isDisplayed(uploadDocument);

	}

	/**
	 * verify File On EditDocument
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyFileOnEditDocument(String finlename)
	{

		findVisibleElement(uploadDocument, Speed.slow);
		return isDisplayed(By.xpath(".//*[@class='ng-binding'][normalize-space(.)='" + finlename.trim() + "']"));

	}

	/**
	 * verify Done Button On EditDocument
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDoneButtonOnEditDocument()
	{

		findVisibleElement(uploadDocument, Speed.slow);
		return isDisplayed(primaryButtonDone);

	}

	/**
	 * verify Details Close Buttion On EditDocument
	 *
	 * @param filename
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyDetailsCloseButtionOnEditDocument()
	{

		findVisibleElement(uploadDocument, Speed.slow);
		return isDisplayed(close);

	}

	/**
	 * verify details on advancedoption at third party service
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyDetailsOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return verifyAdvancedOption()
				&& verifyCloseOnAdvancedOption()
				&& verifySaveOnAdvancedOption()
				&& verifyCancelOnAdvancedOption()
				&& verifyRecipientPrivilegesOnAdvancedOption()
				&& verifyReminderOnAdvancedOption()
				&& verifyExpirationOnAdvancedOption();
	}

	/**
	 * verify advancedoption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(advancedOption, Speed.slow);

	}

	/**
	 * verify Close On Advanced Option
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyCloseOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(closeModal, Speed.slow);

	}

	/**
	 * verify Save On AdvancedOption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifySaveOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(saveOptionOnAdavancedButton, Speed.slow);

	}

	/**
	 * verify Cancel On AdvancedOption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyCancelOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(cancel, Speed.slow);

	}

	/**
	 * verify Recipient Privileges On AdvancedOption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyRecipientPrivilegesOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(recipientPrivileges, Speed.slow);

	}

	/**
	 * verify Reminder On AdvancedOption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyReminderOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(reminder, Speed.slow);

	}

	/**
	 * verify Expiration On AdvancedOption
	 *
	 * @author hasmukh.prajapati
	 */

	@Override
	public boolean verifyExpirationOnAdvancedOption()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(expiration, Speed.slow);
	}

	/**
	 * verify Expiration On AdvancedOption
	 *
	 * @author dharti.patel
	 */
	@Override
	public boolean verifyTaggingInterfaceWindow()
	{

		findVisibleElement(taggingInterface, Speed.slow);

		if (isDisplayed(By.xpath(".//*[@id='file_module_tagginginterface_modal_id_TITLE'][normalize-space(.)='Send to - DocuSign']"), Speed.slow) && isDisplayed(By.id("file_module_tagginginterface_modal_id_MAIN_CLOSE_BUTTON"), Speed.slow))
		{

			driver.switchTo().frame(driver.findElement(tagging));
			try
			{
				Thread.sleep(100000);
				if (isDisplayed(By.xpath(".//*[@data-qa='send-button']//following::*[normalize-space(text())='Send']"), Speed.slow))
				{
					return true;
				}
			}
			catch (InterruptedException e)
			{

				e.printStackTrace();
			}

		}

		return false;
	}

	/**
	 * click on Send to Tagging Interface
	 *
	 * @author dharti.patel
	 */
	@Override
	public void clickOnSendTaggingInterface() throws InterruptedException
	{
		findVisibleElement(taggingInterface, Speed.slow);

		driver.switchTo().frame(driver.findElement(tagging));

		Thread.sleep(5000);
		findVisibleElement(sidebar, Speed.slow);

		WebElement sendbtn = findVisibleElement(send, Speed.slow);
		sendbtn.click();

	}

	/**
	 * Click on Add fields of recipients modal
	 *
	 * @author dharti.patel
	 */
	@Override
	public void clickOnAddFields()
	{
		findVisibleElement(ModalWrap);
		findVisibleElement(addField).click();
	}

	/**
	 * addFields In TaggingInterface
	 *
	 * @author dharti.patel
	 */
	@Override
	public void addFieldsInTaggingInterface()
	{

		findVisibleElement(sidebar, Speed.slow, 1000);

		WebElement fromSignature = findVisibleElement(signature, Speed.slow, 2000);
		WebElement toSignature = findVisibleElement(pageView, Speed.slow, 2000);
		sortItems(fromSignature, toSignature);

	}

	public void sortItems(WebElement source, WebElement destination)
	{
		Actions builder = new Actions(driver);

		Action dragAndDrop = builder.clickAndHold(source)
				.moveToElement(destination)
				.release(destination)
				.build();

		dragAndDrop.perform();
	}

	/**
	 * select service from action button in my file
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnSendToButtonFromActionButtonInMyFile(String option)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(option);

		By option1 = By.xpath(".//*[@id='file_module_sendTo'][normalize-space(.)='" + optionResult.trim() + "']");
		WebElement elem1 = findVisibleElement(option1, Speed.slow);
		elem1.click();
	}

	/**
	 * Enter details on Send to model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void enterDetailsIntoSendToDialog(String enterName, String message)
	{
		setMember(enterName);
		WebElement ele2 = findVisibleElement(locRecipientMsg);
		ele2.sendKeys(message);

	}

	/**
	 * Verify Service Name Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogServiceName(String serviceName)
	{
		return isDisplayed(By.xpath(".//*[@class='modalheaderSubTitle'][normalize-space(text())='" + serviceName.trim() + "']"));
	}

	/**
	 * Verify Email field Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogEmailBox()
	{
		return isDisplayed(locAdobeRecipientEmail);
	}

	/**
	 * Verify Name field Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogNameBox()
	{
		return isDisplayed(locAdobeRecipientName);
	}

	/**
	 * Verify Message field Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogMessageBox()
	{
		return isDisplayed(recipientMessage);
	}

	/**
	 * Verify Close Button Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogCloseButton()
	{
		return isDisplayed(fileModalClose);
	}

	/**
	 * Verify send Button Adobe Recipient Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyAdobeDialogSendButton()
	{
		return isDisplayed(sendBtn);
	}

	/**
	 * goto More Action Send to
	 *
	 * @author dharti.patel
	 */
	@Override
	public void gotoMoreActionsSendTo(String file, String operation)
	{
		String optionResult = StringEscapeUtils.unescapeHtml(operation);
		try
		{

			searchFile(file);
			By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim() + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
			findClickableElement(moreOption, Speed.slow).click();

			By oprationXpath = By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + optionResult.trim() + "'])[1]");

			findPresentElement(moreOptionExpanded, Speed.slow);
			if (isDisplayed(oprationXpath))
			{
				findClickableElement(oprationXpath).click();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * click On Send to Button DocuSign Model
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean clickOnDocuSignSendButtonOfSendToModel(String clientEmail, String clientPassword)
	{
		WebElement ele = findVisibleElement(fileModuleRecepientModalId, Speed.slow);
		ele.click();

		int windowSize = driver.getWindowHandles().size();
		if (windowSize > 1)
		{
			while (driver.getWindowHandles().size() <= 1)
			{
				;
			}
			switchWindow();
			if (isDisplayed(locUserName, Speed.slow))
			{
				findVisibleElement(locUserName, Speed.slow).sendKeys(clientEmail);
				findVisibleElement(locSubmit, Speed.slow).click();
				findVisibleElement(locPassword, Speed.slow).sendKeys(clientPassword);
				findVisibleElement(locSubmit, Speed.slow).click();
			}
			while (driver.getWindowHandles().size() > 1)
			{
				;
			}
			if (driver.getWindowHandles().size() == 1)
			{
				driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
				return true;
			}
			return false;

		}
		return true;

	}

	/**
	 * click On Cancel Button OnTagging interface
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnCancelBtnOnTagging()
	{

		driver.switchTo().defaultContent();
		WebElement cancel = findVisibleElement(cancelBtnOnTagging, Speed.slow);
		cancel.click();
	}

	/**
	 * click On Cancel Button In DeleteDialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnCancelInDeleteDialog()
	{
		findVisibleElement(deleteFoldDocModal, Speed.slow);
		WebElement cancel = findVisibleElement(deleteFoldDoc, Speed.slow);
		cancel.click();
	}

	/**
	 * verify Elements On UnlockDialog
	 *
	 * @author hasmukh.prajapati
	 */
	// verifyElementsOnUnlockDialog
	@Override
	public boolean verifyElementsOnUnlockDialog()
	{
		findVisibleElement(unlockModalId, Speed.slow);

		return (isDisplayed(unlockModalBody) && isDisplayed(unlockModalClose) && isDisplayed(unlockModal));

	}

	/**
	 * click On Unlock Button In Unlock Dialog
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public void clickOnUnlockBtnInUnlockDialog()
	{
		WebElement cancel = findVisibleElement(By.id("file_module_third_party_service_unlock_modal_id_unlock"), Speed.slow);
		cancel.click();
	}

	/**
	 * verify Global Message On UnlockinFile
	 *
	 * @author hasmukh.prajapati
	 */
	@Override
	public boolean verifyGlobalMsgOnUnlockinFile(String msg)
	{
		WebElement globalMsg = findVisibleElement(By.id("globalAlertMessageContainerMsgSpace"), Speed.slow);
		String msgEle = globalMsg.getText();

		return (msgEle.equals(msg));
	}

	/**
	 * @author vivek.mishra
	 * @return the complete table data
	 * @created on 31/05/2018
	 */
	@Override
	public Map<String, ArrayList> getTableData()
	{
		verifyFilesTable();
		int rowSize = driver.findElements(tableRows).size();
		int colSize = driver.findElements(tableColumns).size();
		Map<String, ArrayList> dataSet = new LinkedHashMap<String, ArrayList>();
		ArrayList<String> listValues;
		for (int i = 1; i <= rowSize; i++)
		{
			listValues = new ArrayList<String>();
			for (int j = 3; j <= colSize; j++)
			{
				listValues.add(findVisibleElement(By.xpath("//*[@id='file_module_mainTableId']//tbody/tr[" + i + "]//td[" + j + "]")).getText().trim());

			}
			if (isDisplayed(By.xpath("(//*[@id='file_module_mainTableId']//tbody/tr[" + i + "]//a[contains(@id,'docid')])[1]"), Speed.slow))
			{
				dataSet.put(findVisibleElement(By.xpath("(//*[@id='file_module_mainTableId']//tbody/tr[" + i + "]//a[contains(@id,'docid')])[1]")).getAttribute("id").trim(), listValues);
			}
			else
			{
				dataSet.put(findVisibleElement(By.xpath("//*[@id='file_module_mainTableId']//tbody/tr[" + i + "]")).getAttribute("id").trim(), listValues);
			}
		}
		return dataSet;
	}

	/**
	 * @author vivek.mishra
	 * @return the file table availability
	 * @created on 31/05/2018
	 */
	@Override
	public boolean verifyFilesTable()
	{
		findVisibleElement(fileFolderTable, Speed.slow);
		return (isDisplayed(fileFolderTable, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param colName of caret to be clicked
	 * @created on 31/05/2018
	 */
	@Override
	public void clickOnCaretSign(String colName)
	{
		scrollToTop();
		findVisibleElement(By.xpath("//*[@id='file_module_mainTableId']//th//*[normalize-space(.)='" + getUserData(colName).trim() + "']//*[contains(@class,'icon-caret')]"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param colName to be verified
	 * @return the status
	 * @created on 31/05/2018
	 */
	@Override
	public boolean verifyUpCaretSign(String colName)
	{
		findVisibleElement(By.xpath("//*[@id='file_module_mainTableId']//th//*[normalize-space(.)='" + getUserData(colName).trim() + "']//*[contains(@class,'icon-caret')]"), Speed.slow);
		return isDisplayed(By.xpath("//*[@id='file_module_mainTableId']//th//*[normalize-space(.)='" + getUserData(colName).trim() + "' and @class='dropup']//*[contains(@class,'icon-caret')]"));
	}

	/**
	 * @author vivek.mishra
	 * @param colName to be verified
	 * @return the status
	 * @created on 31/05/2018
	 */
	@Override
	public boolean verifyDownCaretSign(String colName)
	{
		findVisibleElement(By.xpath("//*[@id='file_module_mainTableId']//th//*[normalize-space(.)='" + getUserData(colName).trim() + "']//*[contains(@class,'icon-caret')]"), Speed.slow);
		return isDisplayed(By.xpath("//*[@id='file_module_mainTableId']//th//*[normalize-space(.)='" + getUserData(colName).trim() + "' and @class='']//*[contains(@class,'icon-caret')]"));
	}

	/**
	 * @author vivek.mishra
	 * @param colName to checked for sort
	 * @return the sorted order true or false
	 * @created on 31/05/2018
	 */
	@Override
	public boolean verifyAscendingOrder(String colName, String dataOf)
	{
		verifyFilesTable();
		if (!verifyUpCaretSign(colName))
		{
			clickOnCaretSign(colName);
			verifyFilesTable();
		}

		List<String> listData = getListOfDataOfColumn(colName, dataOf);
		if (colName.toLowerCase().trim().equals("size"))
		{
			boolean flag = false;
			List<Double> sizeList = new ArrayList<Double>();
			for (int i = 0; i < listData.size(); i++)
			{
				String val = listData.get(i).trim();

				if (!val.isEmpty() && val != null)
				{
					flag = true;
					if (val.contains("KB"))
					{
						val = val.split("KB")[0];
					}
					else if (val.contains("MB"))
					{
						val = val.split("MB")[0];
					}
					else if (val.contains("GB"))
					{
						val = val.split("GB")[0];
					}

					sizeList.add(Double.parseDouble(val));
				}
			}

			if (!flag)
			{
				return true;
			}

			List<Double> copySizeList = new ArrayList<Double>(sizeList);
			Collections.sort(sizeList);
			return (copySizeList.equals(sizeList));
		}
		List<String> copyOfList = new ArrayList<>(listData);
		Collections.sort(listData, String.CASE_INSENSITIVE_ORDER);
		return (copyOfList.equals(listData));
	}

	@Override
	public void setThirdPartyDropDown(String optionName)
	{
		selectOptionFromDropDown(locThirdPartyServiceActionMenu, thirdPartyServiceComboBox, thirdPartyServiceList, optionName);

	}

	@Override
	public void setMessage(String memberName)
	{
		WebElement addGroupMemberInputBox = findVisibleElement(recipientMessage, Speed.slow);
		addGroupMemberInputBox.clear();
		addGroupMemberInputBox.sendKeys(memberName);
		selectUserNameFromAutoSuggest(memberName);

	}

	@Override
	public void clickAddFieldsOnThirdPartyService()
	{
		if (isDisplayed(By.xpath("//button[normalize-space(text())='Send Without Fields']")))
		{
			findClickableElement(By.xpath("//button[normalize-space(text())='Send Without Fields']")).click();
		}

	}

	@Override
	public void clickOnMoreActionInSentForSignatureTab(String fileName, String option)
	{
		WebElement moreaction = findVisibleElement(By.xpath(".//*[@id='file_module_mainTableId']//a[normalize-space(.)='" + fileName + "']//following::a[@data-original-title='More actions'][1]//following::a[normalize-space(text())='" + option + "'][1]"), Speed.slow);
		moreaction.click();

	}

	@Override
	public void clickMoreActionOnSentForSignature(String fileName)
	{
		WebElement moreaction = findVisibleElement(By.xpath(".//*[@id='file_module_mainTableId']//a[normalize-space(.)='" + fileName + "']//following::a[@data-original-title='More actions'][1]"), Speed.slow);
		moreaction.click();

	}

	@Override
	public void clickOnLeftPanelSentForSignature()
	{
		findVisibleElement(sentForSignature).click();

	}

	/**
	 * verify Status field in File Viewer
	 * <<<<<<< HEAD
	 * =======
	 * >>>>>>> qa/4.4
	 *
	 * @author dharti.patel
	 */
	@Override
	public boolean verifyStatusInViewer(String status)
	{
		findVisibleElement(locRightmodel, Speed.slow);
		if (isDisplayed(locStatuslabel))
		{
			return isDisplayed(By.xpath("//*[@id='documentReviewStatusViewer']//following::*[normalize-space(text())='" + status.trim() + "']"));

		}
		return false;
	}

	/**
	 * Click on Revoke signing request in File Viewer
	 *
	 * @author dharti.patel
	 */
	@Override
	public void clickOnRevokeLinkInViewer()
	{
		WebElement locRevokeLink = findVisibleElement(By.xpath("//*[@id='documentReviewStatusViewer']//following::*[normalize-space(text())='" + FileLabels.FILES_THIRDPARTYSERVICE_AWAITING_ESIGN_REVOKESIGNINGREQUEST + "']"));
		locRevokeLink.click();
	}

	/**
	 * Close More Action of File Viewer
	 *
	 * @author dharti.patel
	 */
	@Override
	public void closeMoreActionInViewer()
	{
		WebElement locMoreAction = findVisibleElement(By.xpath(".//*[@data-original-title='" + FileLabels.UI_TEXT_MOREACTIONS + "' and @aria-expanded='true']"));
		locMoreAction.click();
	}

	@Override
	public void gotoMoreActionsForFilePreview(String file, String operation, String actionType)
	{
		try
		{
			// searchFile(file);
			// By moreOption = By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim() + "')])[last()]/following::*[contains(@data-original-title,'" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')])[1]");
			By moreOption = By.xpath(".//*[@id='document_preview_modal_right_main']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "']");
			System.out.println(moreOption);
			findClickableElement(moreOption, Speed.slow).click();

			By oprationXpath = By.xpath("(//*[@id='document_preview_modal_right_main']//*[@data-original-title='" + FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation.trim() + "'])[1]");
			System.out.println(oprationXpath);
			findPresentElement(moreOptionExpanded, Speed.slow);
			if (isDisplayed(oprationXpath))
			{
				if (actionType.equalsIgnoreCase(FileLabels.CLICK_ON_MORE_ACTION))
				{
					findClickableElement(oprationXpath).click();
				}
				else if (actionType.equalsIgnoreCase(FileLabels.HOVER_ON_MORE_ACTION))
				{
					moveToElement(oprationXpath);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Click on more action of File Preview Sub option
	 *
	 * @author paras.vankadi
	 * @param file
	 *        file name
	 * @param operation
	 *        click on sub menu for more action
	 * @param subMenuOptionValue
	 *        hover for file
	 */
	@Override
	public void clickMoreActionsForFilePreviewSubOption(String file, String operation, String subMenuOptionValue)
	{
		gotoMoreActionsForFilePreview(file, operation, FileLabels.HOVER_ON_MORE_ACTION);
		clickOnSubmenuOption(subMenuOptionValue);
	}

	@Override
	public boolean verifyWatermarkInViewer(String textToVerify)
	{
		WebElement elem = findVisibleElement(watermark, Speed.slow);
		String elemText = "";
		elemText = elem.getText().trim();
		return elemText.equals(textToVerify.trim());
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @created on 30/05/2018
	 */
	@Override
	public boolean verifyNoPreview()
	{
		findVisibleElement(notViewreSupport, Speed.slow);
		return isDisplayed(notViewreSupport);
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @created on 30/05/2018
	 */
	@Override
	public boolean verifyWatermarkNotDisplay()
	{
		return isDisplayed(watermark, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param colName to checked for sort
	 * @return the sorted order true or false
	 * @created on 31/05/2018
	 */
	@Override
	public boolean verifyDescendingOrder(String colName, String dataOf)
	{
		verifyFilesTable();
		if (!verifyDownCaretSign(colName))
		{
			clickOnCaretSign(colName);
			verifyFilesTable();
		}

		List<String> listData = getListOfDataOfColumn(colName, dataOf);
		if (colName.toLowerCase().trim().equals("size"))
		{
			boolean flag = false;
			List<Double> sizeList = new ArrayList<Double>();
			for (int i = 0; i < listData.size(); i++)
			{
				String val = listData.get(i).trim();

				if (!val.isEmpty() && val != null)
				{
					flag = true;
					if (val.contains("KB"))
					{
						val = val.split("KB")[0];
					}
					else if (val.contains("MB"))
					{
						val = val.split("MB")[0];
					}
					else if (val.contains("GB"))
					{
						val = val.split("GB")[0];
					}

					sizeList.add(Double.parseDouble(val));
				}
			}

			if (!flag)
			{
				return true;
			}

			List<Double> copySizeList = new ArrayList<Double>(sizeList);
			Collections.sort(sizeList, Collections.reverseOrder());
			return (copySizeList.equals(sizeList));
		}
		List<String> copyOfList = new ArrayList<>(listData);
		reverseSort(listData, String.CASE_INSENSITIVE_ORDER);
		return (copyOfList.equals(listData));
	}

	/**
	 * Verify Status in Audit History
	 *
	 * @author dhaval.zaveri
	 */
	@Override
	public boolean verifyStatusInAuditHistory(String actionStatus)
	{
		findVisibleElement(locAuditHistoryModel, Speed.slow);
		int size = driver.findElements(By.xpath(".//*[@id='file_module_docAudit_Tbody']//following::tr")).size();
		for (int i = 1; i <= size; i++)
		{
			String action = findVisibleElement(By.xpath(".//*[@id='file_module_docAudit_Tbody']//following::tr[" + i + "]/td[2]"), Speed.slow).getText().trim();
			if (action.equals(actionStatus.trim()))
			{
				return true;
			}

		}
		return false;
	}

	@Override
	public void gotoSettingsTab()
	{
		findVisibleElement(addFolderSettingsTab).click();
	}

	@Override
	public void selectFileViewFromSettingTab(String option)
	{
		if (isDisplayed(fileViewSelect))
		{

		}
		// {
		Select oSelect = new Select(driver.findElement(fileViewSelect));
		oSelect.selectByVisibleText(option);
		// }
		// else
		// {
		// System.err.println(option + " not found.");
		// }
	}

	@Override
	public void addFileOrFolderToFavouritesMyFiles(String itemToFavourite)
	{

		searchFile(itemToFavourite);

		By favXpathMyFile = By.xpath(".//*[@title='" + itemToFavourite.trim() + "']/ancestor::tr//a[@data-original-title='Add to favourites']");

		By alreayFavXpathMyFile = By.xpath(".//*[@title='" + itemToFavourite.trim() + "']/ancestor::tr//a[@data-original-title='Remove from favourites']");

		if (isDisplayed(alreayFavXpathMyFile))
		{
			System.out.println(itemToFavourite + " is already in favourites");
		}
		else
		{
			findVisibleElement(favXpathMyFile).click();
		}
	}

	/**
	 * Click on unlock revoke and delete button
	 *
	 * @author darshan.nayak
	 */
	@Override
	public void clickOnUnlockRevokeAndDeleteBtnInDeleteDialog()
	{
		WebElement unlockRevokeDelete = findVisibleElement(unlockRevokeAndDelete, Speed.slow);
		unlockRevokeDelete.click();
	}

	/**
	 * Click on revoke and delete button in delete dialog
	 *
	 * @author darshan.nayak
	 */
	@Override
	public void clickOnRevokeAndDeleteBtnInDeleteDialog()
	{
		WebElement revokeAndDeleteEle = findVisibleElement(deleteFoldDocModalOk, Speed.slow);
		revokeAndDeleteEle.click();
	}

	/**
	 * @author bhumika.vaghasiya
	 * @created on 07/06/2018
	 */
	@Override
	public void clickOnUnlockAndDeleteBtnInDeleteDialog()
	{
		WebElement revokeAndDeleteEle = findVisibleElement(okForRevokeDelete, Speed.slow);
		revokeAndDeleteEle.click();
		while (isDisplayed(okForRevokeDelete))
		{
			;
		}
	}

	@Override
	public boolean clickOnCancelBtnOnAdvancedOptionAtThirdPArty()
	{
		findVisibleElement(advancedOption, Speed.slow);

		return isDisplayed(cancel, Speed.slow);

	}
}
