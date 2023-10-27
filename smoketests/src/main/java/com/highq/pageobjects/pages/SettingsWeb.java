package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.SettingsPage;

public class SettingsWeb extends BannerPageWeb implements SettingsPage
{

	By general = By.id("generalTabID");
	By siteEmailAlerts = By.id("siteEmailAlertsTabID");
	By systemNotifications = By.id("userPreference_systemNotification");

	public SettingsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * used to verify verify GeneralTab
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 15/04/2018
	 */

	@Override
	public boolean verifyGeneralTab()
	{

		return isDisplayed(general);
	}

	/**
	 * used to verify verify SetEmail AlertTab
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 15/04/2018
	 */

	@Override
	public boolean verifySetEmailAlertTab()
	{

		return isDisplayed(siteEmailAlerts);
	}

	/**
	 * used to verify SystemNotifications
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 15/04/2018
	 */

	@Override
	public boolean verifySystemNotifications()
	{

		return isDisplayed(systemNotifications);
	}

}
