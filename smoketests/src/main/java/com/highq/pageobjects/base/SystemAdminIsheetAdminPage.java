package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.SystemAdminTemplateIsheetsListWeb;

public interface SystemAdminIsheetAdminPage extends BannerPage
{
	public boolean verifyIsheetTemplateIsDisplayed(String templateName);

	public void selectTemplate(String templateName);

	public void clickOnDeleteTemplateButton();

	public void deleteAllTemplates();

	public SystemAdminTemplateIsheetsListWeb clickOnTemplateName(String templateName);
}
