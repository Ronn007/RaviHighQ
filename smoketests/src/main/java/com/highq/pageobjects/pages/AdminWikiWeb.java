package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminWikiPage;

public class AdminWikiWeb extends BannerPageWeb implements AdminWikiPage
{
	public AdminWikiWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	// ***************************************** Wiki Options ************************************************

	By saveButton = By.id("adminWikiSaveBtnBottom");

	// *************************************** Set Notifications ***********************************************

	By notificationCheckBox = By.xpath(".//input[@name='site.wikiApprovalAlertToAll']");

	By setNotificationButton = By.id("siteAdmin_module_setNotification_modal_setNotificationBtnID");

	By setNotificationCancelButton = By.id("siteAdmin_module_setNotification_modal_cancelLinkBtnID");

	By setNotificationCloseLink = By.xpath("siteAdmin_module_setNotification_modal_closeBtnID");

	By notification_selectAllLink = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_SELECTALL + "']");
	By notification_clearAllLink = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_CLEARALL + "']");

	// *************************************** Basic Operations on Elements ***********************************************

	@Override
	public void clickOnSave()
	{
		WebElement saveButtonEle = findClickableElement(saveButton);
		saveButtonEle.click();
	}

	// *************************************** Functional Operations ***********************************************

	@Override
	public void setWikiOptions(String option, boolean value)
	{
		WebElement elem = findClickableElement(By.xpath("(//*[normalize-space(.)='" + option.trim() + "'])[last()]/*[@type='checkbox']"));
		Boolean currentStatus = elem.isSelected();
		if (currentStatus != value)
		{
			elem.click();
		}
		clickOnSave();
	}

}
