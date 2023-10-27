package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminConfigureSiteStoragePage;

/**
 * @author nidhi.shah
 */
public class AdminConfigureSiteStorageWeb extends BannerPageWeb implements AdminConfigureSiteStoragePage
{
	public AdminConfigureSiteStorageWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By fileSearchIndexingDropDown = By.id("fileIndexONId");
	By saveLink = By.linkText(SiteAdminLabels.SITEADMIN_CONFIGURESITESTORAGE);

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveLink);
		saveEle.click();
	}

	@Override
	public void setFileSearchIndexingValue(String value)
	{
		Select fileSearchIndexingEle = new Select(findClickableElement(fileSearchIndexingDropDown));
		fileSearchIndexingEle.selectByVisibleText(value);
	}
}
