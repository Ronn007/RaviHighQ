package com.highq.labels.collaborate;

public class FileLabels extends GlobalLabels
{
	/* *********** Left Panel - Files ***************/
	public static final String FILES_LEFTPANEL_INDEX = getRBValue("document.index.label");
	public static final String FILES_LEFTPANEL_RECENT = getRBValue("document.recent.label");
	public static final String FILES_LEFTPANEL_FAVOURITES = getRBValue("systemvocabulary.displayname.filterbar.favourites");
	public static final String FILES_LEFTPANEL_ATTACHMENTS = getRBValue("ui.text.attachments");
	public static final String FILES_LEFTPANEL_DELETEDITEMS = getRBValue("document.deleteitems.label");
	public static final String FILES_LEFTPANEL_CLEARSEARCH = getRBValue("isheet.label.item.clearSearch");
	public static final String FILES_LEFTPANEL_NORESULTSFOUND = getRBValue("label.display.tokenField.noresultfound");

	/* *********** Files Banner ***********************/
	public static final String FILES_ADD = getRBValue("document.middlePanel.addBtn");
	public static final String FILES_ACTION = getRBValue("document.middlePanel.actionBtn");
	public static final String FILES_VIEW = getRBValue("document.middlePanel.viewBtn");
	public static final String FILES_ACTIONS = getRBValue("document.indexView.middlePanel.actionBtn");
	public static final String FILES_PRINTPREVIEW = getRBValue("document.indexView.middlePanel.treeBtn.print");
	public static final String FILES_EXPORT = getRBValue("document.indexView.middlePanel.treeBtn.exportindex");
	public static final String FILES_CLEARSEARCHTERM = getRBValue("document.modal.advancedSearch.clearSearch");
	public static final String FILES_NEW_BUTTON = getRBValue("ui.text.new");
	public static final String FILES_UPLOAD_BUTTION = getRBValue("ui.text.upload");

	/* *************** FilesBanner - Search DropDown **************/
	public static final String FILES_SEARCHFILTER_FILETYPES = getRBValue("ui.text.fileTypes");
	public static final String FILES_SEARCHFILTER_TAGS = getRBValue("document.modal.addTagModal.label.tags");
	public static final String FILES_SEARCHFILTER_AUTHOR = getRBValue("file.author.text");
	public static final String FILES_SEARCHFILTER_READ = getRBValue("ui.text.read");
	public static final String FILES_SEARCHFILTER_UNREAD = getRBValue("ui.text.unread");
	public static final String FILES_SEARCHFILTER_WITHCOMMENTS = getRBValue("ui.text.withComments");
	public static final String FILES_SEARCHFILTER_WITHTASKS = getRBValue("ui.text.withTasks");
	public static final String FILES_SEARCHFILTER_ADVANCEDSEARCH = getRBValue("ui.text.advancedSearch");
	public static final String FILES_SEARCHFILTER_CLEARFILTERS = getRBValue("file.clearFiler");

	/* *************** Advanced Search - Search DropDown (for search,cancel,... any other footer buttons look for "footer" xpaths in this file) **************/
	public static final String FILES_ADVANCEDSEARCH_ANYOFTHESEWORDS = getRBValue("isheet.advSearch.anyWords");
	public static final String FILES_ADVANCEDSEARCH_ALLOFTHESEWORDS = getRBValue("isheet.advSearch.allWords");
	public static final String FILES_ADVANCEDSEARCH_EXCLUDETHESEWORDS = getRBValue("isheet.advSearch.excludeWords");
	public static final String FILES_ADVANCEDSEARCH_DESCRIPTION = getRBValue("label.display.Description");
	public static final String FILES_ADVANCEDSEARCH_TITLE = getRBValue("label.display.title");
	public static final String FILES_ADVANCEDSEARCH_CONTENTTYPE = getRBValue("ui.text.contenttype");
	public static final String FILES_ADVANCEDSEARCH_FILETYPE = getRBValue("ui.text.fileType");
	public static final String FILES_ADVANCEDSEARCH_FOLDERS = getRBValue("document.modal.addFolderModal.sortContent.folders.list.title");
	public static final String FILES_ADVANCEDSEARCH_LASTMODIFIEDDATE = getRBValue("isheet.advSearch.lastModifiedDate");
	public static final String FILES_ADVANCEDSEARCH_SEARCHANDHIT = getRBValue("ui.placeholder.searchAndHit");
	public static final String FILES_ADVANCEDSEARCH_ALSOSEARCHSUBFOLDERS = getRBValue("document.label.searchSubFolder");

	/* *************** Folder Title Container - Below File Banner **************/
	public static final String FILES_FOLDERTITLE_FULLSCREEN = getRBValue("document.middlePanel.viewBtn.fullScreen");
	public static final String FILES_FOLDERTITLE_ADDTOFAVOURITES = getRBValue("systemvocabulary.displayname.mark.favourite");

	/* *********** File - Main Table Header ***************/
	public static final String FILES_MAINTABLEHEADER_NAME = getRBValue("document.middlePanel.table.column.name");
	public static final String FILES_MAINTABLEHEADER_SIZE = getRBValue("document.middlePanel.table.column.size");
	public static final String FILES_MAINTABLEHEADER_AUTHOR = getRBValue("document.middlePanel.table.column.author");
	public static final String FILES_MAINTABLEHEADER_LASTMODIFIED = getRBValue("document.middlePanel.table.column.lastModified");
	public static final String FILES_MAINTABLE_NOFILESORFOLDERS = getRBValue("ui.text.nocontentfound");

	/* *********** File - Add Dropdown options ***************/
	public static final String FILES_ADDDROPDOWN_FILES = getRBValue("systemvocabulary.displayname.documents");
	public static final String FILES_ADDDROPDOWN_ZIPPEDFILES = "Zipped files";
	public static final String FILES_ADDDROPDOWN_PLACEHOLDERFILES = "Placeholder files";
	public static final String FILES_ADDDROPDOWN_NEWFOLDER = getRBValue("document.middlePanel.addBtn.newFolder");
	public static final String FILES_ADDDROPDOWN_FOLDER = getRBValue("ui.text.folder");

	/* *********** File - Add Files(opens after Add->Files selection) ***************/
	public static final String FILES_ADDFOLDER_SETTINGS = getRBValue("ui.text.settings");
	public static final String FILES_ADDFILES_FILES = getRBValue("systemvocabulary.displayname.documents");
	public static final String FILES_ADDFILES_METADATA = getRBValue("document.modal.addFolderModal.metadataTab");
	public static final String FILES_ADDFILES_TASKS = getRBValue("ui.text.Tasks");
	public static final String FILES_ADDFILES_TASKSTAB_ADDTASK = getRBValue("ui.title.task.add");
	public static final String FILES_ADDFILES_FILES_RENAME = getRBValue("file.name.rename");// "Rename";

	/* **************** File - Zipped Files ****************************/
	public static final String FILES_ZIPFILE_METADATATAB_FILEVIEW = "File view";
	public static final String FILES_ZIPFILE_METADATATAB_DISCLAIMER = getRBValue("ui.button.text.disclaimer");
	public static final String FILES_ZIPFILE_METADATATAB_TAGS = getRBValue("document.modal.addTagModal.label.tags");

	/* ********************* File - Preview Page ***********************/
	public static final String FILES_PREVIEWPAGE_DETAILS_TYPE = getRBValue("document.checkout.modal.label.FileType");
	public static final String FILES_PREVIEWPAGE_DETAILS_SIZE = getRBValue("document.checkout.modal.label.FileSize");
	public static final String FILES_PREVIEWPAGE_DETAILS_AUTHOR = getRBValue("file.author.text");
	public static final String FILES_PREVIEWPAGE_DETAILS_CREATED = getRBValue("ui.text.created");
	public static final String FILES_PREVIEWPAGE_DETAILS_MODIFIED = getRBValue("ui.txt.modified");
	public static final String FILES_PREVIEWPAGE_DETAILS_NOTES = getRBValue("file.version.note.text");
	public static final String FILES_PREVIEWPAGE_DETAILS_LIKE = getRBValue("ui.text.like");
	public static final String FILES_PREVIEWPAGE_DETAILS_COMMENT = "Comment";
	public static final String FILES_PREVIEWPAGE_DETAILS_METADATA = getRBValue("document.modal.addFolderModal.metadataTab");
	public static final String FILES_PREVIEWPAGE_DETAILS_COMMENTS = getRBValue("ui.text.comments");

	/* ***************** Attribute values ***********************/
	public static final String FILES_ATTRIBUTE_MOREACTIONS = getRBValue("ui.text.moreactions");
	public static final String FILES_ATTRIBUTE_NEXTPAGE = "Next Page";
	public static final String FILES_ATTRIBUTE_FULLSCREEN = getRBValue("document.middlePanel.viewBtn.fullScreen");
	public static final String FILES_ATTRIBUTE_LINK = getRBValue("ui.text.Link");
	public static final String FILES_ATTRIBUTE_ATTACHMENT = getRBValue("ui.text.Attachment");
	public static final String FILES_ATTRIBUTE_TEXTFORMATTING = getRBValue("ui.text.formatting");

	/* ********************Add Folder*************************** */
	public static final String FILES_FOLDER_DETAILS_NAME = getRBValue("document.modal.addFolderModal.label.folderName");
	public static final String FILES_FOLDER_DETAILS_DESCRIPTION = getRBValue("document.modal.addFolderModal.label.folderDescription");

	/* *******************Move **************************/
	public static final String FILES_MOVE_CHOOSEASITE = getRBValue("copyfilesfolders.modal.label.chooseSite");
	public static final String FILES_MOVE_EXPANDALL = getRBValue("document.indexView.middlePanel.treeBtn.expandall");
	public static final String FILES_MOVE_COLLAPSEALL = getRBValue("document.indexView.middlePanel.treeBtn.collapseall");

	/* ***************** Bulk Print ************************/
	public static final String FILES_BULKPRINT_GENERATINGPDFFILESMSG = "Generating PDF file� this may take some time";
	public static final String FILES_BULKPRINT_YOURFILEISREADYTODOWNLOADMSG = "Your file is now ready to download";
	public static final String FILES_BULKPRINT_CLICKDOWNLOADTOCONTINUEMSG = getRBValue("document.indexView.bulkPrint.modal.proccess.continue.download.msg");

	/* ****************** Checkout ****************************/
	public static final String FILES_CHECKOUT_FILENAME = getRBValue("document.checkout.modal.label.FileName");
	public static final String FILES_CHECKOUT_STATUS = getRBValue("document.checkout.modal.label.status");
	public static final String FILES_CHECKOUT_DOWNLOADINGFILES = "Downloading file(s)�";
	public static final String FILES_CHECKOUT_CANCELCHECKOUT = getRBValue("document.checkout.modal.label.CancelCheckOut");

	/* ********************** Like and Comment ***********/
	public static final String FILES_LIKEANDCOMMENT_LIKE = getRBValue("ui.text.like");
	public static final String FILES_LIKEANDCOMMENT_UNLIKE = getRBValue("ui.text.unlike");
	public static final String FILES_LIKEANDCOMMENT_POST = getRBValue("ui.button.text.post");
	public static final String FILES_LIKEANDCOMMENT_LOADMORECOMMENTS = getRBValue("label.display.comment.loadMore");
	public static final String FILES_LIKEANDCOMMENT_DELETECOMMENTCONFIRMATION = getRBValue("comment.deletComment.confirmMsg");

	/* *********************** File Edit *******************/
	public static final String FILES_EDIT_TITLE = getRBValue("label.display.title");
	public static final String FILES_EDIT_TAGS = getRBValue("document.modal.addTagModal.label.tags");
	public static final String FILES_EDIT_DISCLAIMERTEXT = getRBValue("label.display.TermsAndConditionContent");

	/* *********************** Placeholder file *******************/
	public static final String FILES_PLACEHOLDERFILE_TAGS = getRBValue("document.modal.addTagModal.label.tags");
	public static final String FILES_PLACEHOLDERFILE_DISCLAIMER = getRBValue("label.display.ApplyTermsAndCondition.disclaimer");
	public static final String FILES_PLACEHOLDERFILE_NAME = getRBValue("ui.text.name");
	public static final String FILES_PLACEHOLDERFILE_VERSIONNOTES = getRBValue("label.display.documentVersionComments.name");
	public static final String FILES_PLACEHOLDERFILE_DETAILSTAB_NAME = getRBValue("ui.text.name");

	/* ***************** Files-Commons(Modal headers,title, etc...) ***********************/
	public static final String FILES_COMMON_ADDFILES = getRBValue("document.modal.addFolderModal.permission.label.addFiles");
	public static final String FILES_COMMON_DETAILS = getRBValue("ui.text.details");
	public static final String FILES_COMMON_DELETE = getRBValue("ui.button.text.delete");
	public static final String FILES_COMMON_SAVE = getRBValue("ui.button.text.save");
	public static final String FILES_COMMON_CANCEL = getRBValue("ui.button.text.cancel");
	public static final String FILES_COMMON_CLOSE = getRBValue("ui.button.text.close");
	public static final String FILES_COMMON_SEARCH = getRBValue("ui.text.search");
	public static final String FILES_COMMON_ACCEPT = getRBValue("ui.button.text.accept");
	public static final String FILES_COMMON_CONTINUE = getRBValue("ui.button.text.continue");
	public static final String FILES_COMMON_MOVE = getRBValue("ui.button.text.move");
	public static final String FILES_COMMON_DOWNLOAD = getRBValue("ui.text.download");
	public static final String FILES_COMMON_CHECKOUT = getRBValue("document.checkout.modal.actionBtn.checkout");
	public static final String FILES_COMMON_CHECKIN = getRBValue("document.checkout.modal.actionBtn.checkIn");
	public static final String FILES_COMMON_COPY = getRBValue("ui.button.text.copy");
	public static final String FILES_COMMON_DONE = getRBValue("ui.buton.text.done");
	public static final String FILES_COMMON_DISCLAIMER = getRBValue("label.display.ApplyTermsAndCondition.disclaimer");
	public static final String FILES_COMMON_DELETING = "Deleting�";
	public static final String FILES_COMMON_THERESTOREHASCOMPLETED = getRBValue("document.modal.restoreDeletedModal.infomsg.restoreDone");
	public static final String FILES_COMMON_UPLOADING = "Uploading�";
	public static final String FILES_COMMON_MORE = getRBValue("ui.text.more");
	public static final String FILES_COMMON_DOWNLOADING = "Downloading";
	public static final String FILES_COMMON_ADDZIPPEDFILETITLE = "Add zipped files";
	public static final String FILES_COMMON_PREPARINGDOWNLOAD = getRBValue("document.download.longpathCheck.modal.title");
	public static final String FILES_COMMON_EDIT = getRBValue("ui.button.text.edit");

	/* ************************ File - More Action Items **********************/
	public static final String FILES_MOREACTION_SHARE = getRBValue("document.middlePanel.shareMenu.share");
	public static final String FILES_MOREACTION_ASKQUESTION = getRBValue("document.middlePanel.shareMenu.askQuestion");
	public static final String FILES_MOREACTION_AUDITHISTORY = getRBValue("document.middlePanel.shareMenu.auditHistory");
	public static final String FILES_MOREACTION_VERSIONHISTORY = "Version history";
	public static final String FILES_MOREACTION_DELETE = getRBValue("document.middlePanel.shareMenu.delete");
	public static final String FILES_ALLOCATE_FOR_REVIEW = getRBValue("document.indexView.middlePanel.actionBtn.allcateReview");

	public static final String CLASSIFIER_BUTTON = "Select";
	public static final String CLASSIFIER_ADD_LABEL = "Select a classifier to add";
	public static final String CLASSIFIER_SEARCH_PLACEHOLDER_LABEL = "Search classifiers";
	public static final String CLASSIFIER_SEARCH_NO_RESULTS_FOUND_LABEL = "No results found";
	public static final String DOCUMENT_ANALYSIS_LABEL = "Document analysis";

	public static final String FILES_REMOVE_SHARE = "Remove share";

	public static final String FILES_MARK_UP_COPY = getRBValue("label.display.documentMarkupVersionbrows.name");

	/* *********************** Audit History *******************/
	public static final String ISHEET_LABEL_AUDITHISTORY = getRBValue("isheet.label.auditHistory");
	public static final String DOCUMENT_MIDDLEPANEL_SHAREMENU_AUDIT_VIEWD = getRBValue("document.middlePanel.shareMenu.audit.viewd");
	public static final String DOCUMENT_INDEXVIEW_MIDDLEPANEL_ACTIONBTN_DOWNLOAD = getRBValue("document.indexView.middlePanel.actionBtn.download");
	public static final String ISHEET_LABEL_DOWNLOADED = getRBValue("isheet.label.downloaded");
	public static final String SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS = getRBValue("systemvocabulary.displayname.documents");
	public static final String DOCUMENT_MIDDLEPANEL_SHAREMENU_AUDIT_PRINTED = getRBValue("document.middlePanel.shareMenu.audit.printed");

	/* ************************** More Action Option ******************************* */

	public static final String DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO = getRBValue("document.middlePanel.shareMenu.sendTo");
	public static final String FILES_THIRDPARTYSERVICE_MODAL_SENDTO = getRBValue("files.thirdPartyService.modal.sendTo");
	public static final String UI_TEXT_MOREACTIONS = getRBValue("ui.text.moreactions");
	public static final String DOCUMENT_CHECKOUT_MESSAGE = getRBValue("document.checkout.message");
	public static final String DOCUMENT_CHECKIN_MESSAGE = getRBValue("document.checkin.message");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_DISABLED = getRBValue("systemadmin.systemsettings.display.name.modal.disabled");

	/* ************************** File Upload and New, Folder Button Label ******************************* */
	public static final String SITE_ADMIN_PERMISSIONS_HEADER_UI_TEXT_FOLDER = getRBValue("site.admin.permissions.header.ui.text.folder");

	/* ************************** DocuSign Label ******************************* */
	public static final String ASPADMIN_DOCUSIGN_SANDBOX = getRBValue("aspadmin.docusign.sandbox");

	/* ************************** Check out ******************************* */
	public static final String DOCUMENT_CHECKOUT_MODAL_LABEL_CANCELCHECKOUT = getRBValue("document.checkout.modal.label.CancelCheckOut");

	public static final String FILES_THIRDPARTYSERVICE_MODAL_CHOOSEANYONESERVICETOSENDABOVESELECTEDITEM = getRBValue("files.thirdPartyService.modal.chooseAnyOneServicetoSendAboveSelectedItem");
	public static final String SITE_ADMIN_USERS_SENDBUTTON = getRBValue("site.admin.users.sendButton");
	public static final String SYSTEMADMIN_MENU_MOREDELETE = getRBValue("systemadmin.globalnavigation.menu.moredelete.label");
	public static final String FILES_LISTVIEW = getRBValue("document.middlePanel.viewBtn.listView");
	public static final String FILES_COLUMNVIEW = getRBValue("document.middlePanel.viewBtn.columnView");
	public static final String FILES_THUMBNAILVIEW = getRBValue("document.middlePanel.viewBtn.thumbNailView");
	public static final String CLICK_ON_MORE_ACTION = "click";
	public static final String HOVER_ON_MORE_ACTION = "hover";

	public static final String FILES_DOCUMENTANALYSIS_MODAL_MANAGE = getRBValue("files.DocumentAnalysis.modal.manage");
	public static final String LISTVIEW = getRBValue("site.admin.files.folders.defaultfolderview.listView");
	public static final String COLUMNVIEW = getRBValue("site.admin.files.folders.defaultfolderview.columnView");
	public static final String THUMBNAILVIEW = getRBValue("site.admin.files.folders.defaultfolderview.thumbNailView");
	public static final String FILES_VIEW_BUTTION = getRBValue("isheet.label.viewOnly");
	public static final String UI_TEXT_NOCONTENTFOUND = getRBValue("ui.text.nocontentfound");

	public static final String UI_BUTTON_TEXT_OKREVOKEDELETE = getRBValue("ui.button.text.okRevokeDelete");

	public static final String PREVIEW_LABEL = getRBValue("people.userProfilePage.edit.uploadUserAvatar.modal.label.priview");
	public static final String SAVE_AND_CLOSE = "Save and Close";
	public static final String EDIT_MESSAGE = "Edit Message";
	public static final String EDIT_RECIPIENTS = "Edit Recipients";
	public static final String EDIT_DOCUMENTS = "Edit Documents";
	public static final String ADVANCED_OPTIONS = "Advanced Options";
	public static final String VOIDEDSTATUS = "Voided";
	public static final String REVOKE = "Revoke";
	public static final String REMIND = "Remind";
	public static final String FILE_STUTUS_DRAFT = getRBValue("wiki.mydraft.drafttype.draft");
	public static final String DISCARD = getRBValue("event.autoSaveDraft.discard");

	public static final String DOCUMENT_DELETE_AREYOUSURE = getRBValue("document.delete.areYouSure");
	public static final String UNLOCK = getRBValue("ui.button.text.unlock");
	public static final String UNLOCKTRYAGAIN_MODAL_SUCCESS = getRBValue("files.thirdPartyService.tab.awaiting.esign.unlock_tryagain.modal.success");
	public static final String AUDITHISTORY = getRBValue("systemadmin.systemauditreports.listpage.menu.lbl.audithistory");
	public static final String SIGNING_LOCK_REMOVED = "Signing lock removed";
	public static final String NEEDTOSIGN = getRBValue("thirdparty.service.document.action.needtosign");

	public static final String FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN = getRBValue("files.thirdPartyService.tab.awaiting.esign");
	public static final String UI_TEXT_UNALLOCATED = getRBValue("ui.text.unAllocated");
	public static final String FILES_THIRDPARTYSERVICE_AWAITING_ESIGN_REVOKESIGNINGREQUEST = getRBValue("files.thirdPartyService.awaiting.esign.revokeSigningRequest");

    public static final String FILES_NEW_FOLDER = "Folder";
    public static final String FILE_DELETED = "File deleted";
    public static final String SIGN_REQUEST_REVOKED = "Sign request revoked";
}
