package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminActivityPage;

/**
 * @author nidhi.shah
 */
public class AdminActivityWeb extends BannerPageWeb implements AdminActivityPage
{
	public AdminActivityWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By enableSiteActivityCheckbox = By.id("disableRecentActivity");
	By enableMicrobloggingCheckbox = By.id("microBlogging");
	By displayUserAvatarsCheckbox = By.id("displayUserAvatars");
	By permissionAllInput = By.id("activityModulePermissionTypeDefault");
	By permissionRestrictedInput = By.id("activityModulePermissionTypeRestricted");
	By saveButton = By.id("adminActivitySaveBtnBottom");

	public void enableSiteActivity(boolean state)
	{
		setSelection(enableSiteActivityCheckbox, state);
	}

	public void enableMicroblogging(boolean state)
	{
		setSelection(enableMicrobloggingCheckbox, state);
	}

	public void displayUserAvatars(boolean state)
	{
		setSelection(displayUserAvatarsCheckbox, state);
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 17/03/2018
	 */
	public void clickOnSave()
	{
		WebElement saveEle = findVisibleElement(saveButton, Speed.slow);
		saveEle.click();
	}
}
