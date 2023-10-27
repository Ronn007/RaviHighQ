package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminIsheetSaveAsTemplateWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetEditPage extends AdminAddIsheetPage
{
	public boolean verifyWarningMsgForDMDIsheet();

	public void clickCancelInWarningModal();

	public AdminIsheetWeb editIsheetClickSave();

	public AdminIsheetSaveAsTemplateWeb clickOnSaveAsTemplate();
}
