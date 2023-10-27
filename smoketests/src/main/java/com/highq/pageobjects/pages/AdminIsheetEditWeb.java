package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.AdminIsheetLabels;
import com.highq.pageobjects.base.AdminIsheetEditPage;

public class AdminIsheetEditWeb extends AdminAddIsheetWeb implements AdminIsheetEditPage
{
	By warningModalForDMDIsheet = By.xpath(".//*[@id='isheet_admin_update_sheet_modal' and (contains(@class,'modal-dialog-center in'))]//*[text()='" + AdminIsheetLabels.SITEADMIN_MODULESETTINGS_ISHEETS_EDITISHEET_UPDATE_WARNINGMSG + "']");
	By cancelBtnOnWarningModal = By.id("isheet_admin_update_sheet_modal_isheet_admin_update_sheet_modal_cancel_btn");
	By iSheetsPage = By.xpath(".//*[@id='siteAdmin_module_mainMiddlePanelDivID']//*[normalize-space()='" + AdminIsheetLabels.SITEADMIN_ISHEET_TITLE + "' and (contains(@class,'adminTitle'))]");
	By saveAsTemplate = By.id("button_saveAsTemplate");

	public AdminIsheetEditWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to verify warning Message when DMD Isheet with AI fields is tried to change to normal iSheet
	 *
	 * @author janki.hirani
	 *         created date: 18/05/2018
	 * @return
	 */
	public boolean verifyWarningMsgForDMDIsheet()
	{
		WebElement warning = findPresentElement(warningModalForDMDIsheet, Speed.slow);
		return warning.isDisplayed();
	}

	/**
	 * This method is used to click on Cancel In Warning Modal
	 *
	 * @author janki.hirani
	 *         created date: 18/05/2018
	 */
	public void clickCancelInWarningModal()
	{
		findClickableElement(cancelBtnOnWarningModal, Speed.slow).click();
	}

	/**
	 * This method is used to click on save in Edit iSheet Page
	 *
	 * @author janki.hirani
	 *         created date: 18/05/2018
	 * @return
	 */
	public AdminIsheetWeb editIsheetClickSave()
	{
		WebElement saveEle = findPresentElement(addIsheetTopSaveBtn);
		scrollToElement(saveEle);
		saveEle.click();

		if (isDisplayed(iSheetsPage, Speed.slow))
		{
			return new AdminIsheetWeb(driver);
		}
		return null;
	}

	/**
	 * This method is used to click on Save as template button in Edit Isheet page
	 *
	 * @author janki.hirani
	 *         created date: 23/05/2018
	 */
	public AdminIsheetSaveAsTemplateWeb clickOnSaveAsTemplate()
	{
		findVisibleElement(saveAsTemplate).click();
		return new AdminIsheetSaveAsTemplateWeb(driver);
	}
}
