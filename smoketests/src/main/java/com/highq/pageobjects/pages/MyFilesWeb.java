package com.highq.pageobjects.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.FileLabels;
import com.highq.pageobjects.base.MyFilesPage;

/**
 * @author khushbu.dhandhukiya
 */
public class MyFilesWeb extends DocumentWeb implements MyFilesPage {

	By send_Email_recipientsInput = By.id("file_module_qfs_emailRecipients-tokenfield");
	By send_Email_subjectInput = By.id("file_module_qfs_emailLink_Subject");
	By sendAFile = By.id("file_module_sendAFile_button");
	By browse = By.id("qfsUploadFile");
	By next = By.id("QUICK_FILE_SHARE_file_module_QFS_next_btn");
	By send = By.id("QUICK_FILE_SHARE_file_module_QFS_next_btn");
	By share_Email_recipientsInput = By.id("file_module_myFiles_emailRecipients-tokenfield");
	By share_Email_subjectInput = By.id("file_module_myFiles_emailLink_Subject");
	By share_Email_messageInput = By.id("file_module_myFiles_emailLink_Message");
	By remove_share_message = By.id("collaborateCustomModalMessage");
	By cancel = By.id("collaborateMessageCancelButton");
	By remove = By.id("collaborateMessageOkButton");
	By delete = By.id("file_module_deleteFoldDocModal");
	By deleteButton = By.id("file_module_deleteFoldDocModal_ok");
	By cancelButton = By.id("file_module_deleteFoldDocModal_cancel");
	By deleteMsgBody = By.id("file_module_deleteFoldDocModal_BODY");
	By addButton = By.xpath(".//*[@id='file_module_mainMiddlePanelDivID']//*[normalize-space(.)='Add']");

	By restoreTitle = By.id("customMessageModalTitle");
	By restoreMsg = By.id("collaborateCustomModalMessage");
	By moveModal = By.id("file_module_moveCopy_modal");
	By receivedItemPage = By.xpath(
			".//*[@id='file_module_mainMiddlePanelDivID']//*[@class='modulePageTitles paddBott15 margRight30 m-paddLeft10' and normalize-space(.)='Received items']");
	By noFilesAndFolder = By
			.xpath(".//*[@id='file_module_tableContentDivID' and *[normalize-space(.)='No files or folders']");
	By deletedItemPage = By
			.xpath(".//*[@id='file_module_mainMiddlePanelDivID' and *[normalize-space(.)='Deleted items']]");

	By generatingPDFFile = By.id("showPrintProccessID");
	By fileBanner_ActionButton = By.id("file_module_action_button");

	By bulkPrintModal = By.id("showPrintReadyID");
	By indexCancel = By.id("INDEX_VIEW_PRINT_SITE_MAP_MODAL_cancelLinkButton");
	By indexDownload = By.id("INDEX_VIEW_PRINT_SITE_MAP_MODAL_downloadPrintButton");
	By indexDownloadButton = By.id("INDEX_VIEW_BULK_DOWNLOAD_SELECTION_OPTIONS_MODAL_downloadButton");
	By indexDownloadCancelButton = By.id("INDEX_VIEW_BULK_DOWNLOAD_SELECTION_OPTIONS_MODAL_cancelLinkButton");

	By linkSentToMessage = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_TITLE");
	By recipientMail = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_BODY");
	By close = By.id("FILE_MODULE_EMAIL_RESULT_MODAL_MAIN_CLOSE_BUTTON");
	By sendAfileModal = By.xpath(".//*[@id='FILE_MODULE_EMAIL_RESULT_MODAL']/div");
	By sendShare = By.id("file_module_shared_file_link_file_module_shareModal_emailAndMessage_send_btn");
	By getLink = By.id("file_module_shared_file_link_file_module_shareModal_getLink_btn");
	By share_Link_URLInput = By.id("file_module_myFiles_copyLink");
	By share_recipientHover = By.xpath("(//*[@class='typeahead_primary']//*)[last()]");
	By postbutton = By.id("file_module_shared_file_link_file_module_shareModal_microblog_post_btn");
	By accessLevel = By.id("file_module_qfs_email_accessLevel");
	By filePermission = By.id("file_module_qfs_email_permission");
	By expiry = By.id("file_module_qfs_linkExpires");
	By emailAccessLevel = By.id("file_module_myFiles_email_accessLevel");
	By emailExpiry = By.id("file_module_linkExpires");
	By linkAccessLevel = By.id("file_module_myFiles_copyLink_accessLevel");
	By linkExpiry = By.id("file_module_copyLinkExpires");
	By messageExpiry = By.id("file_module_message_linkExpires");
	By microblogExpiry = By.id("file_module_microblog_linkExpires");
	By fileBrowseButton = By.xpath("//*[@id='document_version_modal_tab_Details']//*[@id='fileVersion']");

	By fadeModal = By.xpath("//*[contains(@class,'in') and contains(@class,'modal fade')]");

	By titleTextBoxInAddNewVersionModal = By.id("docversion_name");

	By tagsTextBoxInAddNewVersionModal = By.id("document_version_tagList_ID-tokenfield");

	By versionNotesTextBoxInAddNewVersionModal = By.id("documentVersion_documentComments");

	By disclaimerCheckBox = By.id("file_module_addVersion_disclaimer");

	By disclaimerTextBox = By.id("file_module_addVersion_disclaimer_text");

	By markUpCopyBrowseButton = By.id("markUpFile");

	By markedUpCopy = By
			.xpath("//*[contains(@id,'docid') and normalize-space(text())='" + FileLabels.FILES_MARK_UP_COPY + "']");

	public MyFilesWeb(WebDriver driver) {

		super(driver);
	}

	/**
	 * used to verify Removed Received ItemModal
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 20/04/2018
	 */

	public boolean verifyRemovedReceivedItemModal() {
		return (isDisplayed(remove_share_message, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya verify Received file is visible or not
	 * @creation date 20/04/2018
	 */

	public boolean verifyReceivedFileVisibility(String filename) {

		return (isDisplayed(By
				.xpath("(.//*[@id='file_module_myFiles_receivedItemList']//*[contains(text(),'" + filename + "')])[1]"),
				Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya verify Received item page
	 * @creation date 20/04/2018
	 */

	public boolean verifyReceivedItemPage() {
		return (isDisplayed(receivedItemPage, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya verify delete message
	 * @creation date 23/04/2018
	 */
	public boolean verifyDeleteMessage(String message) {

		String elementText = findVisibleElement(deleteMsgBody, Speed.slow).getText();
		return elementText.trim().equals(message.trim());
	}

	/**
	 * @author khushbu.dhandhukiya verify delete cancel button
	 * @creation date 23/04/2018
	 */

	public boolean verifyDeleteCancelButton() {

		findVisibleElement(delete, Speed.slow);
		return (isDisplayed(cancelButton, Speed.slow));

	}

	/**
	 * @author khushbu.dhandhukiya verify delete button
	 * @creation date 23/04/2018
	 */
	public boolean verifyDeleteButton() {

		findVisibleElement(delete, Speed.slow);
		return (isDisplayed(deleteButton, Speed.slow));

	}

	/**
	 * @author khushbu.dhandhukiya click cancel of delete file
	 * @creation date 23/04/2018
	 */

	public void clickOnCancelOfDeleteButton() {

		findVisibleElement(cancelButton, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya click on delete from delete file
	 * @creation date 23/04/2018
	 */

	public void clickOnDelete() {

		findVisibleElement(deleteButton, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya verify file or folder not Exist
	 * @creation date 23/04/2018
	 */

	public boolean verifyNoFilesOrFolderExists() {
		return (isDisplayed(noFilesAndFolder, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya click on action button
	 * @creation date 23/04/2018
	 */

	public void clickOnActionButton(String action) {
		findClickableElement(selectAllChkbox).click();
		selectItemFromAction(action);

	}

	/**
	 * @author khushbu.dhandhukiya verify After delete file from deleted items it
	 *         will render deleted item page.
	 * @creation date 23/04/2018
	 */
	public boolean verifyDeletedPageRender() {
		return (isDisplayed(deletedItemPage, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya verify After All File deleted from deleted items
	 *         it will render Myfile page.
	 * @creation date 23/04/2018
	 */

	public boolean verifyMyFilePageRender() {
		return (isDisplayed(addButton, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya verify Restore Title message.
	 * @creation date 23/04/2018
	 */

	public boolean verifyRestoreTitle(String message) {

		String elementText = findVisibleElement(restoreTitle, Speed.slow).getText();
		return elementText.trim().equals(message.trim());
	}

	/**
	 * @author khushbu.dhandhukiya verify Restore message.
	 * @creation date 23/04/2018
	 */

	public boolean verifyRestoreMessage(String message) {

		String elementText = findVisibleElement(restoreMsg, Speed.slow).getText();
		return elementText.trim().equals(message.trim());
	}

	/**
	 * @author khushbu.dhandhukiya click on cancel button of Restore modal
	 * @creation date 23/04/2018
	 */

	public void clickOnCancel() {

		findVisibleElement(cancel, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya click on continue button of Restore modal
	 * @creation date 23/04/2018
	 */

	public void clickOnContine() {

		findVisibleElement(remove, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya verify move File Modal
	 * @creation date 24/04/2018
	 */

	public boolean verifyMoveModal() {
		return (isDisplayed(moveModal, Speed.slow));

	}

	/**
	 * @author khushbu.dhandhukiya verify message of Prepare for bulkdownload modal
	 * @creation date 26/04/2018
	 */

	public boolean verifyPrepareForBulkDownload(String message) {

		String elementText = findVisibleElement(generatingPDFFile, Speed.slow).getText();
		return elementText.trim().equals(message.trim());

	}

	/**
	 * @author khushbu.dhandhukiya used for index checkbox
	 * @creation date 26/04/2018
	 */

	public void selectIndexFileCheckBox(String fileName) {
		By fileChkBox = By.xpath("//*[normalize-space(text())='" + fileName.trim()
				+ "']/ancestor::*[2]/preceding-sibling::*//*[@class='fancytree-checkbox']");
		setSelection(fileChkBox, true);
	}

	/**
	 * @author khushbu.dhandhukiya used to click on action button from Index page
	 * @creation date 27/04/2018
	 */

	public void clickOnIndexActionButton(String action) {

		// selectItemFromAction(action);
		WebElement itemToClick = null;
		findVisibleElement(fileBanner);
		WebElement actionButton = findVisibleElement(fileBanner_ActionButton);
		if (isDisplayed(fileBanner_ActionButton) && actionButton.isEnabled()) {
			actionButton.click();
			itemToClick = findVisibleElement(By.xpath(
					"(//*[contains(@class,'dropdown-menu')]//*[normalize-space(text())='" + action.trim() + "'])[1]"));
			itemToClick.click();

		}
	}

	/**
	 * @author khushbu.dhandhukiya verify Bulkprint message
	 * @creation date 26/04/2018
	 */

	public boolean verifyBulkPrintMsg(String message) {

		String elementText = findVisibleElement(bulkPrintModal, Speed.slow).getText();
		return elementText.trim().equals(message.trim());

	}

	/**
	 * @author khushbu.dhandhukiya verify bulkprint cancel button
	 * @creation date 27/04/2018
	 */

	public boolean verifyIndexCancelButton() {
		return (isDisplayed(indexCancel, Speed.slow));

	}

	/**
	 * @author khushbu.dhandhukiya verify bulkprint Download button
	 * @creation date 27/04/2018
	 */

	public boolean verifyIndexDownloadButton() {
		return (isDisplayed(indexDownload, Speed.slow));

	}

	/**
	 * @author khushbu.dhandhukiya used to click on cancel button from bulkprint
	 * @creation date 27/04/2018
	 */

	public void clickOnIndexCancelButton() {

		findVisibleElement(indexCancel, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya used to click on Download button from bulkprint
	 * @creation date 27/04/2018
	 */
	public void clickOnIndexDownloadButton() {

		findVisibleElement(indexDownload, Speed.slow).click();

	}

	/**
	 * Verify zip file contains expected file(s) or folder(s)
	 * 
	 * @param zipFileNameWithExtension
	 *            name of the zip file with its extension
	 * @param fileOrFolder
	 *            name(s) of file or folders (Add file name with extension)
	 * @return true if required file or folder is found in zip
	 * @throws IOException
	 * @author dheeraj.rajput
	 * @Created 19 April 2018
	 * @Updated
	 */
	public boolean verifyZipFileContainsDesiredFilesAndFolders(String zipFileNameWithExtension,
			String... filesOrFolders) throws IOException {
		findVisibleElement(breadCrumb, Speed.slow);
		List<String> fileOrFolderList = new ArrayList<>();
		for (int i = 0; i < filesOrFolders.length; i++) {
			fileOrFolderList.add(filesOrFolders[i].trim());
		}
		Set<String> setOfFileAndFoldersOfZipFile = getZipFileList(zipFileNameWithExtension);
		return setOfFileAndFoldersOfZipFile.containsAll(fileOrFolderList);
	}

	/**
	 * @author khushbu.dhandhukiya used to click on download button from Action menu
	 *         :-bulkdownload
	 * @creation date 27/04/2018
	 */

	public void clickOnIndexDownload() {

		findVisibleElement(indexDownloadButton, Speed.slow).click();
		while (isDisplayed(downloading));

	}

	/**
	 * Share content via e-mail in send a file option on My files page
	 * 
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param subject
	 *            mail subject.
	 * @param message
	 *            to send in e-mail. * @creation date 19/04/2018
	 * @author surbhi.khetan
	 */
	public void sendViaEmail(String recipientMail, String subject) {
		WebElement recipientInput = findVisibleElement(send_Email_recipientsInput);
		WebElement subjectInput = findVisibleElement(send_Email_subjectInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		selectOptionFromAutoSuggest(recipientMail);
		subjectInput.clear();
		subjectInput.sendKeys(subject);
	}

	/**
	 * @author surbhi.khetan
	 * @param file
	 *            removes file which is sent by the user from shared items
	 * @creation date 19/04/2018
	 */
	public void removeShare(String file) {
		By moreOption = By.xpath("((.//*[@id='file_module_myFiles_sharedItemList']//*[contains(text(),'" + file.trim()
				+ "')])/following::*[contains(@data-original-title,'" + FileLabels.FILES_REMOVE_SHARE + "')])[1]");
		findClickableElement(moreOption, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @param message
	 *            verify message when user clicks on remove button in his shared
	 *            items
	 * @creation date 19/04/2018
	 */
	public boolean verifyRemoveShareMessage(String message) {

		String elementText = findVisibleElement(remove_share_message, Speed.slow).getText();
		return elementText.trim().equals(message.trim());
	}

	/**
	 * @author surbhi.khetan
	 * @param file
	 * @param recipientMail
	 * @param subject
	 * @param accesslevel
	 * @param permsision
	 * @param fileExpiry
	 *            send a file to user
	 * @throws InterruptedException
	 * @creation date 19/04/2018
	 */
	public void SendAfile(String file, String recipientMail, String subject, String accesslevel, String permsision,
			String fileExpiry) throws InterruptedException {

		findClickableElement(sendAFile, Speed.slow).click();
		uploadFile(browse, file);
		findClickableElement(next, Speed.slow).click();
		sendViaEmail(recipientMail, subject);
		selectQFSOption(accessLevel, accesslevel);
		selectQFSOption(filePermission, permsision);
		selectQFSOption(expiry, fileExpiry);
		findVisibleElement(share_Send).click();
	}

	/**
	 * Share content via e-mail from My files page
	 * 
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param subject
	 *            mail subject.
	 * @param message
	 *            to send in e-mail.
	 * @param accesslevel
	 * @param fileExpiry
	 *            * @creation date 19/04/2018
	 * @author surbhi.khetan
	 * @throws InterruptedException
	 */
	@Override
	public void shareViaEmail(String recipientMail, String subject, String message, String accesslevel, String expiry)
			throws InterruptedException {

		WebElement recipientInput = findVisibleElement(share_Email_recipientsInput);
		WebElement subjectInput = findVisibleElement(share_Email_subjectInput);
		WebElement messageInput = findVisibleElement(share_Email_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		selectOptionFromAutoSuggest(recipientMail);
		recipientInput.sendKeys(Keys.ENTER);
		subjectInput.clear();
		subjectInput.sendKeys(subject);
		if (!message.isEmpty() || message != null) {
			messageInput.clear();
			messageInput.sendKeys(message);
		}

		selectQFSOption(emailAccessLevel, accesslevel);
		selectQFSOption(emailExpiry, expiry);
		findVisibleElement(shareSend, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan verify cancel button visibility when user clicks on
	 *         remove share from shared items
	 * @creation date 19/04/2018
	 */
	public boolean verifyCancelButton() {

		findVisibleElement(remove_share_message, Speed.slow);
		return (isDisplayed(cancel, Speed.slow));

	}

	/**
	 * @author surbhi.khetan verify remove button visibility when user clicks on
	 *         remove share from shared items
	 * @creation date 19/04/2018
	 */
	public boolean verifyRemoveButton() {

		findVisibleElement(remove_share_message, Speed.slow);
		return (isDisplayed(remove, Speed.slow));
	}

	/**
	 * @author surbhi.khetan click on cancel button when user removes file from
	 *         share items
	 * @creation date 19/04/2018
	 */
	public void clickOnCancelOfRemoveShare() {

		findVisibleElement(cancel, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @param filename
	 *            verify items in share items
	 * @creation date 19/04/2018
	 */
	public boolean verifySharedFileVisibility(String filename) {

		return (isDisplayed(
				By.xpath("(.//*[@id='file_module_myFiles_sharedItemList']//*[contains(text(),'" + filename + "')])[1]"),
				Speed.slow));
	}

	/**
	 * @author surbhi.khetan click on cancel button when user removes file from
	 *         share items
	 * @creation date 19/04/2018
	 */
	public void clickOnDeleteOfRemoveShare() {

		findVisibleElement(remove, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @param file
	 * @param operation
	 *            to click on more actions of file/folder from my files page
	 * @creation date 19/04/2018
	 */
	public void gotoMoreActions(String file, String operation) {
		searchFile(file);
		findVisibleElement(By.xpath("((//*[@id='file_module_mainTableId']//*[contains(text(),'" + file.trim()
				+ "')])[1])/../preceding-sibling::*//*[contains(@data-original-title,'"
				+ FileLabels.FILES_ATTRIBUTE_MOREACTIONS + "')]"), Speed.slow).click();
		findVisibleElement(By.xpath("(//*[@id='file_module_mainTableId']//*[@data-original-title='"
				+ FileLabels.FILES_ATTRIBUTE_MOREACTIONS
				+ "' and @aria-expanded='true']//following::*[normalize-space(text())='" + operation.trim() + "'])[1]"),
				Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan verify items in received items
	 * @creation date 19/04/2018
	 */
	public boolean verifyFilesInReceivedItems(String filename) {

		return (isDisplayed(By.xpath("(//*[@class='blackLink'][text()='" + filename.trim() + "'])[1]")));
	}

	/**
	 * Add file or folder to favourites
	 * 
	 * @param itemToFavourite
	 *            item that needs to be made favourite
	 * @author surbhi.khetan
	 */
	public void addFileOrFolderToFavourites(String itemToFavourite) {
		By alreadyFavXpath = By.xpath("(//*[text()='" + itemToFavourite
				+ "'])[last()]/../preceding-sibling::*//*[contains(@class,'icon-star-selected')]");
		By favStarXPath = By.xpath("(//*[text()='" + itemToFavourite
				+ "'])[last()]/../preceding-sibling::*//*[contains(@class,'icon-star')]");
		if (isDisplayed(alreadyFavXpath))
			System.out.println(itemToFavourite + " is already in favourites");
		else
			findVisibleElement(favStarXPath).click();
	}

	/**
	 * Verify file or folder's favourite icon is selected
	 * 
	 * @param favouriteItem
	 *            name of the file or folder
	 * @return true if successful
	 * @author surbhi.khetan
	 */
	public boolean verifyFileOrFolderFavouriteIconIsSelected(String favouriteItem) {
		By alreadyFavXpath = By.xpath("(//*[text()='" + favouriteItem
				+ "'])[last()]/../preceding-sibling::*//*[contains(@class,'icon-star-selected')]");
		return isDisplayed(alreadyFavXpath);
	}

	/**
	 * Verify file or folder is available in favourite screen
	 * 
	 * @param itemToVerify
	 *            file or folder name
	 * @return true if successful
	 * @author surbhi.khetan
	 */
	public boolean verifyItemInFavourites(String itemToVerify) {
		try {
			searchFile(itemToVerify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isDisplayed(noFavourites)) {
			findVisibleElement(noFavourites);
			return false;
		}
		return verifyFileOrFolderFavouriteIconIsSelected(itemToVerify);
	}

	/**
	 * Remove file or folder from favourites
	 * 
	 * @param itemToRemoveFromFavourites
	 *            item that has to be removed from favourites
	 * @author surbhi.khetan
	 */
	public void removeFileOrFolderFromFavourites(String itemToRemoveFromFavourites) {
		By alreadyFavXpath = By.xpath("(//*[text()='" + itemToRemoveFromFavourites
				+ "'])[last()]/../preceding-sibling::*//*[contains(@class,'icon-star-selected')]");
		if (isDisplayed(alreadyFavXpath))
			findVisibleElement(alreadyFavXpath).click();
		else
			System.out.println(itemToRemoveFromFavourites + " is not in favourites.");
	}

	/**
	 * @author surbhi.khetan
	 * @param linkMessage
	 *            verify send a modal message
	 * @creation date 24/04/2018
	 */
	public boolean verifyShareModalMessage(String linkMessage) {

		String elementText = findVisibleElement(linkSentToMessage, Speed.slow).getText();
		return elementText.trim().equals(linkMessage.trim());
	}

	/**
	 * @author surbhi.khetan
	 * @param useremail
	 *            verify recipient mail in send a file modal
	 * @creation date 24/04/2018
	 */
	public boolean verifyRecipientMailInShareModal(String userEmail) {

		String elementText = findVisibleElement(recipientMail, Speed.slow).getText();
		return elementText.trim().equals(userEmail.trim());
	}

	/**
	 * @author surbhi.khetan click on close button of send a file modal
	 * @creation date 24/04/2018
	 */
	public void clickOnCloseButtonOfSendAFileModal() {

		findClickableElement(close, Speed.slow).click();

	}

	/**
	 * @author surbhi.khetan verify Send A File Modal Visibility
	 * @creation date 24/04/2018
	 */
	public boolean verifyShareModalVisibility() {

		return isDisplayed(sendAfileModal);

	}

	/**
	 * @author surbhi.khetan click On Send Button Of ShareModal
	 * @creation date 24/04/2018
	 */
	public void clickOnSendButtonOfShareModal() {

		findClickableElement(sendShare, Speed.slow).click();

	}

	/**
	 * Copy share URL(Share->Link)
	 *
	 * @param shortUrl
	 *            true - check Use short URL checkbox false - uncheck Use short URL
	 *            checkbox
	 * @param linkaccess
	 * @param linkexpiry
	 * @creation date 24/04/2018
	 * @author surbhi.khetan
	 * @throws InterruptedException
	 */
	@Override
	public void copyShareLink(boolean shortUr, String linkaccess, String linkexpiry) throws InterruptedException {

		if (!isDisplayed(modalContent, Speed.slow)) {
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_Link, Speed.slow).click();
		selectQFSOption(linkAccessLevel, linkaccess);
		selectQFSOption(linkExpiry, linkexpiry);
		findVisibleElement(getLink, Speed.slow).click();
		findVisibleElement(share_Link_SelectLinkButton, Speed.slow).click();
		findVisibleElement(share_Link_URLInput).sendKeys(Keys.chord(Keys.CONTROL, "c"));
	}

	/**
	 * Share content via message from My Files(Share->microblog).
	 * 
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param message
	 *            to send in e-mail.
	 * @param microblogexpiry
	 * @author surbhi.khetan * @creation date 30/04/2018
	 * @throws InterruptedException
	 */
	@Override
	public void shareViaMicroblog(String sitename, String message, String microblogexpiry) throws InterruptedException {
		if (!isDisplayed(modalContent)) {
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_contentModal);
		gotoMicroblogTab();
		WebElement recipientInput = findVisibleElement(share_Microblog_shareWithInput, Speed.slow);
		WebElement messageInput = findVisibleElement(share_Microblog_messageInput, Speed.slow);
		recipientInput.clear();
		recipientInput.sendKeys(sitename);
		selectUserNameFromAutoSuggest(sitename);
		messageInput.clear();
		messageInput.sendKeys(message);
		selectQFSOption(microblogExpiry, microblogexpiry);
		findVisibleElement(postbutton, Speed.slow).click();

	}

	/**
	 * Select site name from auto-suggested list when file shared via microblog
	 * 
	 * @param option
	 * @author surbhi.khetan * @creation date 30/04/2018
	 * @throws InterruptedException
	 */
	@Override
	public void selectUserNameFromAutoSuggest(String option) {
		if (isDisplayed(share_UserNameHover, Speed.slow)) {
			WebElement recipientHover = findVisibleElement(share_UserNameHover, Speed.slow);
			if (recipientHover.getText().trim().equals(option.trim())) {
				recipientHover.click();
			}
		} else {
			System.err.println(option + " not found.");
		}
	}

	/**
	 * Select value from dropdown of access level. expiry and permission in semd
	 * file/share modal
	 * 
	 * @param qfsDropdown
	 * @param optionValue
	 * @author surbhi.khetan
	 * @throws InterruptedException
	 */
	public void selectQFSOption(By qfsDropdown, String optionValue) throws InterruptedException {
		Select qfsdropdown = new Select(findVisibleElement(qfsDropdown));
		String selectedValue = qfsdropdown.getFirstSelectedOption().getText().trim();

		qfsdropdown.selectByVisibleText(optionValue.trim());
	}

	/**
	 * Share content via message(Share->Message).
	 * 
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param message
	 *            to send in e-mail.
	 * @param messagexpiry
	 * @author surbhi.khetan
	 * @throws InterruptedException
	 */
	@Override
	public void shareViaMessage(String recipientMail, String message, String messagexpiry) throws InterruptedException {
		if (!isDisplayed(modalContent)) {
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_contentModal);
		gotoMessageTab();
		WebElement recipientInput = findVisibleElement(share_Message_recipientsInput);
		WebElement messageInput = findVisibleElement(share_Message_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		findVisibleElement(share_Send, Speed.slow);
		selectOptionFromAutoSuggest(recipientMail);
		messageInput.clear();
		messageInput.sendKeys(message);
		selectQFSOption(messageExpiry, messagexpiry);
	}

	/**
	 * @author ankit.motaval Click on Activity tab of Audit History Model
	 * @created on 07/05/2018
	 */
	public void clickActiveTabOnAuditHistory() {
		WebElement element = findVisibleElement(activeTab);
		element.click();
	}

	/**
	 * @author ankit.motaval Verify share with name of Audit History Model
	 * @created on 07/05/2018
	 */
	public boolean verifyShareWithNameAuditHistoryModel(String name) {
		return isDisplayed(
				By.xpath(".// *[@id='file_module_docAudit_Tbody']//*[normalize-space(text())='" + name + "']"));
	}

	/**
	 * @author ankit.motaval Click on close Buton of Link Successfully sent Model
	 * @created on 07/05/2018
	 */
	public void clickCloseOnLinkSuccessfullySentModel() {
		WebElement element = findVisibleElement(closeButtop, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param fileName
	 *            to be attached
	 * @created on 14/05/2018
	 */
	public void attachFileInAddNewVersionModal(String fileName) {
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		findPresentElement(fileBrowseButton, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @return the modal availability
	 * @created on 14/05/2018
	 */
	public boolean verifyModal() {
		findVisibleElement(fadeModal, Speed.slow);
		return (isDisplayed(fadeModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *            to be sent
	 * @created on 14/05/2018
	 */
	public void sendTextInTitleTextBoxInAddNewVersionModal(String text) {
		WebElement element = findVisibleElement(titleTextBoxInAddNewVersionModal, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param tagName
	 *            to be added
	 * @created on 14/05/2018
	 */
	public void addTag(String tagName) {
		findVisibleElement(tagsTextBoxInAddNewVersionModal, Speed.slow).sendKeys(tagName.trim() + Keys.ENTER);
	}

	// /**
	// * @author vivek.mishra
	// * @param text to be sent
	// * @created on 14/05/2018
	// */
	// public void sendTextInVersionNotesTextBox(String text)
	// {
	// WebElement element =
	// findVisibleElement(versionNotesTextBoxInAddNewVersionModal,Speed.slow);
	// element.clear();
	// element.sendKeys(text.trim());
	// }

	/**
	 * @author vivek.mishra
	 * @param state
	 *            to be set
	 * @created on 14/05/2018
	 */
	public void setDisClaimerCheckBox(boolean state) {
		setSelection(disclaimerCheckBox, state);
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *            to be sent
	 * @created on 14/05/2018
	 */
	public void sendTextInDisclaimerTextBox(String text) {
		setDisClaimerCheckBox(true);
		WebElement element = findVisibleElement(disclaimerTextBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param fileName
	 *            to be added
	 * @created on 15/05/2018
	 */
	public void attachFileInMarkUpCopy(String fileName) {
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		findPresentElement(markUpCopyBrowseButton, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param oldTag
	 *            to be removed
	 * @param newTag
	 *            to be added
	 * @created on 15/05/2018
	 */
	public void editTagInAddNewVersionModal(String oldTag, String newTag) {
		findVisibleElement(By
				.xpath("//*[@class='token']//*[normalize-space(text())='" + oldTag.trim() + "']//preceding-sibling::a"),
				Speed.slow).click();
		addTag(newTag);
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName
	 *            to be clicked
	 * @created on 15/05/2018
	 */
	public void clickOnModalButton(String buttonName) {
		WebElement element = findVisibleElement(By.xpath(
				"(//*[contains(@class,'in') and contains(@class,'modal fade')]//*[@class='modal-footer']//*[normalize-space(text())='"
						+ buttonName.trim() + "'])[last()]"),
				Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return the mark up copy attached or not
	 * @created on 15/05/2018
	 */
	public boolean verifyMarkedUpCopyUploaded() {
		return (isDisplayed(markedUpCopy, Speed.slow));
	}

}
