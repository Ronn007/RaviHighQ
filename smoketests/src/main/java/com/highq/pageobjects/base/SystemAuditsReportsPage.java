package com.highq.pageobjects.base;

public interface SystemAuditsReportsPage extends BannerPage
{
	public boolean verifySystemAuditPage();

	public void gotoSentForSignatureSystemAuditPage();

	public boolean verifyFileName(String fileName);

	public boolean verifyOnlyStatus(String status);

	public boolean verifyStatus(String fileName, String status);

	public boolean verifySiteName(String fileName, String siteName);

	public boolean verifyRecipient(String fileName, String recipientName);

	public boolean verifyAllColumnsInAuditPage();

	public boolean verifyDocumentPath(String fileName, String filePath);

	public boolean verifyDocumentVersion(String fileName, String version);

	public boolean verifySendByUser(String fileName, String userName);

	public void clickNameFieldOnSorting();

	public void clickStatusFieldOnSorting();

	public void clickSiteFieldOnSorting();

	public void clickRecipientFieldOnSorting();

	public void clickOnMoreActionOnDocument(String fileName, String optionName);

	public boolean verifyMoreActionOptionsonDocument(String fileName, String optionName);

	public boolean verifyRevokeModel();

	public boolean verifyRevokeSignRequestMsg();

	public boolean verifyCloseButtonInRevokeModel();

	public void clickCloseButtonInRevokeModel();

	public boolean verifyRevokeModelMessage();

	public boolean verifyRevokeButtoninRevokeModel();

	public boolean verifyCancelButtoninRevokeModel();

	public void clickRevokeButtoninRevokeModel();

	public void clickCancelButtoninRevokeModel();

	public boolean verifyReminderModel();

	public boolean verifySendReminderMsgReminderModel();

	public boolean verifyCloseButtonOnReminderModel();

	public boolean verifyRemindModelMessage();

	public boolean verifySendButtoninReminderModel();

	public boolean verifyCancelButtoninReminderModel();

	public void clickSendButtoninReminderModel();

	public void clickCancelButtoninReminderModel();

	public void clickCloseButtonOnReminderModel();

	public boolean verifySearchTextBox();

	public boolean verifyCloseButtonInSearchTextBox();

	public void clickCloseButtonInSearchTextBox();

	public void enterSearchTextBox(String value);

	public boolean verifyNoFilesMessage();
	
	public boolean verifyUnlockModelBody(String message);
	
	public boolean verifyUnlockButton();
	
	public boolean verifyCancelButtonOfUnlockBody();
	
	public void clickOnUnlockButton();

}
