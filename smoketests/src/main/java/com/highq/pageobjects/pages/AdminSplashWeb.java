package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminSplashPage;

/**
 * @author nidhi.shah
 */
public class AdminSplashWeb extends BannerPageWeb implements AdminSplashPage
{
	public AdminSplashWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveButton = By.id("adminSplashSaveBtnBottom");
	By enableSiteSplashPageCheckbox = By.id("applysitehomepage");
	By firstLoginInput = By.id("firstLoginSiteHomePage");
	By everyLoginInput = By.id("everyLoginSiteHomePage");
	By splashPageContentEditorBox = By.id("siteHomePage");

	By showOnFirstSiteLoginRadio = By.id("firstLoginSiteHomePage");
	By showOnEverySiteLoginRadio = By.id("everyLoginSiteHomePage");
	By showSplashNextTimeUserLoginsCheckbox = By.id("nextLoginSiteHomePage");

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveButton);
		saveEle.click();
	}

	/**
	 * Enable/disable site splash page
	 * 
	 * @param state
	 *        Boolean: true - to check enable site splash page checkbox
	 *        false - to uncheck enable site splash page checkbox
	 * @author dheeraj.rajput
	 */
	@Override
	public void enableSiteSplashPage(boolean state)
	{
		setSelection(enableSiteSplashPageCheckbox, state);
	}

	/**
	 * Add content in splash page content editor box
	 * 
	 * @param text
	 *        String to add
	 * @author dheeraj.rajput
	 */
	public void setSplashText(String text)
	{
		WebElement contentEditor = findVisibleElement(splashPageContentEditorBox);
		contentEditor.clear();
		contentEditor.sendKeys(text.trim());
	}

	/**
	 * Click on Show on the first site login only radio
	 * 
	 * @param showNextTime
	 *        Boolean: true - to check Show splash page next time any user logs in checkbox
	 *        false - to uncheck Show splash page next time any user logs in checkbox
	 * @author dheeraj.rajput
	 */
	public void showOnFirstSiteLogin(boolean showNextTime)
	{
		findVisibleElement(showOnFirstSiteLoginRadio).click();
		setSelection(showSplashNextTimeUserLoginsCheckbox, showNextTime);
	}

	/**
	 * Click on Show on every site login radio
	 * 
	 * @author dheeraj.rajput
	 */
	public void showOnEverySiteLogin()
	{
		findVisibleElement(showOnEverySiteLoginRadio).click();
	}

}
