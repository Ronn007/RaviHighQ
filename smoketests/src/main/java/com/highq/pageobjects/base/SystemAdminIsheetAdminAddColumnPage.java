package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.SystemAdminIsheetColumnsListWeb;

public interface SystemAdminIsheetAdminAddColumnPage extends BannerPage
{
	public boolean verifyImportCheckBoxDisplayed();

	public boolean vrifySourceProprtyDisplayedWithDropdown();

	public boolean verifyAnalysisEngineDisplayedWithEngineDropdown();

	public boolean verifyFieldsPropertyDisplayedWithSelectButton();

	public void enterColumnName(String columnName);

	public SystemAdminIsheetColumnsListWeb clickOnSave();
}
