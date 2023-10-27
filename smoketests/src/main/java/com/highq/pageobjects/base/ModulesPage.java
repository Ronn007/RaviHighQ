package com.highq.pageobjects.base;

import org.openqa.selenium.By;
import com.highq.pageobjects.pages.AdminPageWeb;

public interface ModulesPage extends BannerPage
{
	public void enableAllModules() throws InterruptedException;

	public void enableSpecificModule(By moduleCheckBox, boolean isEnable);

	public void setSelectDefaultLandingPage(String pageName);

	public AdminPageWeb clickOnSaveButton();

	public void enableSpecificModules(boolean isEnable, String... moduleName);

	public void enableSpecificModules(String... moduleName);

	public void renameSpecificModules(String rename, String moduleName);

	public void resetDefaultNameSpecificModules(String moduleName);
}
