package com.highq.pageobjects.base;

import java.io.IOException;

/**
 * @author khushbu.dhandhukiya
 */
public interface MyFilesPage extends DocumentPage
{

	public void sendViaEmail(String recipientMail, String subject);

	public void removeShare(String file);

	public boolean verifyRemoveShareMessage(String message);

	public void SendAfile(String file, String recipientMail, String subject, String accesslevel,
			String permsision, String fileExpiry) throws InterruptedException;

	public void shareViaEmail(String recipientMail, String subject, String message);

	public boolean verifyCancelButton();

	public boolean verifyRemoveButton();

	public void clickOnCancelOfRemoveShare();

	public void clickOnDeleteOfRemoveShare();

	public boolean verifySharedFileVisibility(String filename);

	public void gotoMoreActions(String file, String operation);

	public boolean verifyRemovedReceivedItemModal();

	public boolean verifyReceivedFileVisibility(String filename);

	public boolean verifyReceivedItemPage();

	public boolean verifyDeleteMessage(String message);

	public boolean verifyDeleteCancelButton();

	public boolean verifyDeleteButton();

	public void clickOnCancelOfDeleteButton();

	public void clickOnDelete();

	public boolean verifyNoFilesOrFolderExists();

	public void clickOnActionButton(String action);

	public boolean verifyDeletedPageRender();

	public boolean verifyMyFilePageRender();

	public boolean verifyRestoreTitle(String message);

	public boolean verifyRestoreMessage(String message);

	public void clickOnCancel();

	public void clickOnContine();

	public boolean verifyMoveModal();

	public boolean verifyPrepareForBulkDownload(String message);

	public void selectIndexFileCheckBox(String fileName);

	public void clickOnIndexActionButton(String action);

	public boolean verifyBulkPrintMsg(String message);

	public boolean verifyIndexCancelButton();

	public boolean verifyIndexDownloadButton();

	public void clickOnIndexCancelButton();

	public void clickOnIndexDownloadButton();

	public boolean verifyZipFileContainsDesiredFilesAndFolders(String zipFileNameWithExtension, String... filesOrFolders) throws IOException;

	public void clickOnIndexDownload();

	public boolean verifyFilesInReceivedItems(String filename);

	public void addFileOrFolderToFavourites(String itemToFavourite);

	public boolean verifyFileOrFolderFavouriteIconIsSelected(String favouriteItem);

	public boolean verifyItemInFavourites(String itemToVerify);

	public void removeFileOrFolderFromFavourites(String itemToRemoveFromFavourites);

	public boolean verifyShareModalMessage(String linkMessage);

	public boolean verifyRecipientMailInShareModal(String userEmail);

	public void clickOnCloseButtonOfSendAFileModal();

	public boolean verifyShareModalVisibility();

	public void clickOnSendButtonOfShareModal();

	public void copyShareLink(boolean shortUr, String linkaccess, String linkexpiry) throws InterruptedException;

	public void shareViaMicroblog(String sitename, String message, String microblogexpiry) throws InterruptedException;

	public void shareViaMessage(String recipientMail, String message, String messagexpiry) throws InterruptedException;

	void shareViaEmail(String recipientMail, String subject, String message, String accesslevel, String expiry) throws InterruptedException;
	
	public void attachFileInAddNewVersionModal(String fileName);
	
	public boolean verifyModal();
	
	public void sendTextInTitleTextBoxInAddNewVersionModal(String text);
	
	public void addTag(String tagName);
	
//	public void sendTextInVersionNotesTextBox(String text);
	
	public void setDisClaimerCheckBox(boolean state);
	
	public void sendTextInDisclaimerTextBox(String text);
	
	public void attachFileInMarkUpCopy(String fileName);
	
	public void editTagInAddNewVersionModal(String oldTag, String newTag);
	
	public void clickOnModalButton(String buttonName);
	
	public boolean verifyMarkedUpCopyUploaded();

	public void clickActiveTabOnAuditHistory();

	public boolean verifyShareWithNameAuditHistoryModel(String name);

	public void clickCloseOnLinkSuccessfullySentModel();

}
