package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAuditsReportsPage;

public class SystemAuditsReportsWeb extends BannerPageWeb implements SystemAuditsReportsPage
{

	By sentForsignatureSystemAudit = By.xpath("//*[normalize-space(text())='" + SystemAdminLabels.SYSTEM_SENT_FOR_SIGNATURE_SYSTEM_AUDIT + "']");
	By leftPanelSystemAuditsAndReports = By.xpath(".//*[normalize-space(text())='Sent for signature system audit']");
	String verifyAllContent = "//*[@class='ffname']//a[normalize-space(text())=";
	By nameFieldPath = By.xpath("//*[normalize-space(text())='Name']");
	By statusFieldPath = By.xpath("//*[normalize-space(text())='Status']");
	By siteFieldPath = By.xpath("//*[normalize-space(text())='Site']");
	By recipientFieldPath = By.xpath("//*[normalize-space(text())='Recipient']");
	By revokeButtonInRevokeModel = By.id("esign_audits_third_party_service_revoke_modal_id_revoke");
	By cancelButtonInRevokeModel = By.id("esign_audits_third_party_service_revoke_modal_id_Close");
	By closeButtonRevokeModel = By.id("esign_audits_third_party_service_revoke_modal_id_MAIN_CLOSE_BUTTON");

	By sendButtonInReminderModel = By.id("esign_audits_third_party_service_remind_modal_id_send");
	By cancelButtonInReminderModel = By.id("esign_audits_third_party_service_remind_modal_id_Close");
	By closeButtonReminderModel = By.id("esign_audits_third_party_service_remind_modal_id_MAIN_CLOSE_BUTTON");

	By searchTextBox = By.id("file_module_filterTextValue");
	By locUnlockModel = By.id("file_module_third_party_service_unlock_modal_id");
	By locUnclockBody = By.id("file_module_third_party_service_unlock_modal_id_BODY");
	By locUnclockButton = By.id("file_module_third_party_service_unlock_modal_id_unlock");
	By locCancelBtnUnlockModel = By.id("file_module_third_party_service_unlock_modal_id_Close");
	By globalMsgContainer = By.id("globalAlertMessageContainer");

	public SystemAuditsReportsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public boolean verifySystemAuditPage()
	{
		return isDisplayed(leftPanelSystemAuditsAndReports);
	}

	@Override
	public void gotoSentForSignatureSystemAuditPage()
	{
		WebElement ele = findVisibleElement(sentForsignatureSystemAudit);
		ele.click();
	}

	@Override
	public boolean verifyFileName(String fileName)
	{
		return isDisplayed(By.xpath(verifyAllContent + "'" + fileName + "']"));
	}

	@Override
	public boolean verifyOnlyStatus(String status)
	{
		return isDisplayed(By.xpath("//p[1][normalize-space(text())='" + status + "']"));
	}

	@Override
	public boolean verifyStatus(String fileName, String status)
	{
		return isDisplayed(By.xpath(verifyAllContent + "'" + fileName + "']//following::td[1]//p[normalize-space(text())='" + status + "']"));
	}

	@Override
	public boolean verifySiteName(String fileName, String siteName)
	{
		return isDisplayed(By.xpath(verifyAllContent + "'" + fileName + "']//following::td[2]//a[normalize-space(text())='" + siteName + "']"));
	}

	@Override
	public boolean verifyRecipient(String fileName, String recipientName)
	{
		return isDisplayed(By.xpath(verifyAllContent + "'" + fileName + "']//following::td[3]//a[normalize-space(text())='" + recipientName + "']"));
	}

	@Override
	public boolean verifyAllColumnsInAuditPage()
	{
		boolean nameField = isDisplayed(nameFieldPath);
		boolean statusField = isDisplayed(statusFieldPath);
		boolean siteField = isDisplayed(siteFieldPath);
		boolean recipientField = isDisplayed(recipientFieldPath);
		return nameField && statusField && siteField && recipientField;
	}

	@Override
	public boolean verifyDocumentPath(String fileName, String filePath)
	{
		return isDisplayed(By.xpath("//a[normalize-space(text())='" + fileName + "']//following::*[@class='fileMeta greyMeta']//a[normalize-space(text())='" + filePath + "']"));
	}

	@Override
	public boolean verifyDocumentVersion(String fileName, String version)
	{
		return isDisplayed(By.xpath("//a[normalize-space(text())='" + fileName + "']//following::span[2][contains(text(),'" + version + "')]"));
	}

	@Override
	public boolean verifySendByUser(String fileName, String userName)
	{
		return isDisplayed(By.xpath("//a[normalize-space(text())='" + fileName + "']//following::span[2]//a[contains(text(),'" + userName + "')]"));
	}

	@Override
	public void clickNameFieldOnSorting()
	{
		findVisibleElement(nameFieldPath).click();
	}

	@Override
	public void clickStatusFieldOnSorting()
	{
		findVisibleElement(statusFieldPath).click();
	}

	@Override
	public void clickSiteFieldOnSorting()
	{
		findVisibleElement(siteFieldPath).click();
	}

	@Override
	public void clickRecipientFieldOnSorting()
	{
		findVisibleElement(recipientFieldPath).click();
	}

	@Override
	public void clickOnMoreActionOnDocument(String fileName, String optionName)
	{
		findClickableElement(By.xpath("//*[@class='ffname']//a[normalize-space(text())='" + fileName.trim() + "']//following::td[4]//a[@title='More actions']")).click();
		findClickableElement(By.xpath("//*[@class='ffname']//a[normalize-space(text())='" + fileName.trim() + "']//following::td[4]//a[normalize-space(text())='" + optionName.trim() + "']")).click();
	}

	@Override
	public boolean verifyMoreActionOptionsonDocument(String fileName, String optionName)
	{
		findClickableElement(By.xpath("//*[@class='ffname']//a[normalize-space(text())='" + fileName.trim() + "']//following::td[4]//a[@title='More actions']")).click();
		boolean elementDisp = isDisplayed(By.xpath("//*[@class='ffname']//a[normalize-space(text())='" + fileName.trim() + "']//following::td[4]//a[normalize-space(text())='" + optionName.trim() + "']"));
		findClickableElement(By.xpath("//*[@class='ffname']//a[normalize-space(text())='" + fileName.trim() + "']//following::td[4]//a[@title='More actions']")).click();
		return elementDisp;
	}

	@Override
	public boolean verifyRevokeModel()
	{
		return isDisplayed(By.xpath("//*[@id='esign_audits_third_party_service_revoke_modal_id' and contains(@class,'in')]"));
	}

	@Override
	public boolean verifyRevokeSignRequestMsg()
	{
		return isDisplayed(By.xpath("//*[@id='esign_audits_third_party_service_revoke_modal_id']//div[normalize-space(text())='Revoke sign request?']"));
	}

	@Override
	public boolean verifyCloseButtonInRevokeModel()
	{
		return isDisplayed(closeButtonRevokeModel);
	}

	@Override
	public void clickCloseButtonInRevokeModel()
	{
		findClickableElement(closeButtonRevokeModel).click();
	}

	@Override
	public boolean verifyRevokeModelMessage()
	{
		String msg = findVisibleElement(By.id("esign_audits_third_party_service_revoke_modal_id_BODY")).getText();
		return ("Are you sure you want to revoke the request for this and any related documents to be signed?").equals(msg);
	}

	@Override
	public boolean verifyRevokeButtoninRevokeModel()
	{
		return isDisplayed(revokeButtonInRevokeModel);
	}

	@Override
	public boolean verifyCancelButtoninRevokeModel()
	{
		return isDisplayed(cancelButtonInRevokeModel);
	}

	@Override
	public void clickRevokeButtoninRevokeModel()
	{
		findClickableElement(revokeButtonInRevokeModel).click();
	}

	@Override
	public void clickCancelButtoninRevokeModel()
	{
		findClickableElement(cancelButtonInRevokeModel).click();
	}

	@Override
	public boolean verifyReminderModel()
	{
		return isDisplayed(By.xpath("//*[@id='esign_audits_third_party_service_remind_modal_id' and contains(@class,'in')]"));
	}

	@Override
	public boolean verifySendReminderMsgReminderModel()
	{
		return isDisplayed(By.xpath("//*[@id='esign_audits_third_party_service_remind_modal_id']//div[normalize-space(text())='Send reminder?']"));
	}

	@Override
	public boolean verifyCloseButtonOnReminderModel()
	{
		return isDisplayed(closeButtonReminderModel);
	}

	@Override
	public boolean verifyRemindModelMessage()
	{
		String msg = findVisibleElement(By.id("esign_audits_third_party_service_remind_modal_id_BODY")).getText();
		return ("Are you sure you want to send a reminder to the original recipients?").equals(msg);
	}

	@Override
	public boolean verifySendButtoninReminderModel()
	{
		return isDisplayed(sendButtonInReminderModel);
	}

	@Override
	public boolean verifyCancelButtoninReminderModel()
	{
		return isDisplayed(cancelButtonInReminderModel);
	}

	@Override
	public void clickSendButtoninReminderModel()
	{
		findClickableElement(sendButtonInReminderModel).click();
	}

	@Override
	public void clickCancelButtoninReminderModel()
	{
		findClickableElement(cancelButtonInReminderModel).click();
	}

	@Override
	public void clickCloseButtonOnReminderModel()
	{
		findClickableElement(closeButtonReminderModel).click();
	}

	@Override
	public boolean verifySearchTextBox()
	{
		return isDisplayed(searchTextBox);
	}

	@Override
	public boolean verifyCloseButtonInSearchTextBox()
	{
		return isDisplayed(By.xpath("//*[@id='lstvAdmin']//button[contains(@class,'searchClose')]"));
	}

	@Override
	public void clickCloseButtonInSearchTextBox()
	{
		findClickableElement(By.xpath("//*[@id='lstvAdmin']//button[contains(@class,'searchClose')]")).click();
	}

	@Override
	public void enterSearchTextBox(String value)
	{
		findVisibleElement(searchTextBox);
		findVisibleElement(searchTextBox).clear();
		findVisibleElement(searchTextBox).sendKeys(value);
	}

	@Override
	public boolean verifyNoFilesMessage()
	{
		return isDisplayed(By.xpath("//*[@id='file_module_awaiting_esign_noRecentFiles']//div[normalize-space(text())='No files']"));
	}

	/**
	 * Verify Unlock Body message in Unlock Model
	 * 
	 * @author dhaval.zaveri
	 */
	public boolean verifyUnlockModelBody(String message)
	{
		if (isDisplayed(locUnlockModel, Speed.slow))
		{
			return findVisibleElement(locUnclockBody, Speed.slow).getText().equals(message.trim());
		}
		return false;
	}

	/**
	 * Verify Unlock Button in Unlock Model
	 * 
	 * @author dhaval.zaveri
	 */
	public boolean verifyUnlockButton()
	{
		return isDisplayed(locUnclockButton, Speed.slow);
	}

	/**
	 * Verify Cancel Button in Unlock Model
	 * 
	 * @author dhaval.zaveri
	 */
	public boolean verifyCancelButtonOfUnlockBody()
	{
		return isDisplayed(locCancelBtnUnlockModel, Speed.slow);
	}

	/**
	 * Click on unlock button in Unlock Model
	 * 
	 * @author dhaval.zaveri
	 */
	public void clickOnUnlockButton()
	{
		findVisibleElement(locUnclockButton, Speed.slow).click();
	}

	/**
	 * Verify Unlock Global Message
	 * 
	 * @author dhaval.zaveri
	 */
	public boolean verifyUnlockGlobalMessage()
	{
		if (isDisplayed(globalMsgContainer, Speed.slow))
		{
			return isDisplayed(By.xpath(".//*[@id='globalAlertMessageContainerMsgSpace'][normalize-space(text())='" + SystemAdminLabels.FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN_UNLOCK_TRYAGAIN_MODAL_SUCCESS + "']"), Speed.slow);
		}

		return false;
	}

}
