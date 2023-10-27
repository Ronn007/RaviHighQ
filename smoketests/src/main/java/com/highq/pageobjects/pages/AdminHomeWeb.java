package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminHomePage;

/**
 * @author nidhi.shah
 */
public class AdminHomeWeb extends BannerPageWeb implements AdminHomePage
{
	public AdminHomeWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By titleInput = By.id("displayPageTitle");
	By permissionAllInput = By.id("homeModulePermissionTypeDefault");
	By permissionRestrictedInput = By.id("homeModulePermissionTypeRestricted");
	By saveLink = By.id("adminHomeSaveBtnBottom");

	@Override
	public void setTitle(Boolean value)
	{
		WebElement titleEle = findClickableElement(titleInput);
		Boolean currentStatus = findClickableElement(titleInput).isSelected();
		if (value != currentStatus)
		{
			titleEle.click();
		}
	}

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveLink);
		saveEle.click();
	}
}
