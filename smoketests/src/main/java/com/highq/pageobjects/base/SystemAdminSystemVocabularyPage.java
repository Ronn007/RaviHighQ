package com.highq.pageobjects.base;

public interface SystemAdminSystemVocabularyPage extends BannerPage
{
	public void changeModuleName(String moduleName, String newModuleName);

	public void resetAllModuleName();

	public void clickOnSave();

	public void clickOnBack();
}
