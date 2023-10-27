package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminTermsAndConditionsPage;

public class AdminTermsAndConditionsWeb extends BannerPageWeb implements AdminTermsAndConditionsPage
{
	public AdminTermsAndConditionsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveButton = By.id("adminTermsSaveBtnBottom");
	By enableSiteTermsAndConditionsCheckbox = By.id("applySiteTerms");
	By termsAndConditionContentEditorBox = By.id("siteTerm");

	By showOnFirstSiteLoginRadio = By.id("firstLoginSiteTerm");
	By showOnEverySiteLoginRadio = By.id("everyLoginSiteTerm");
	By showTermsAndConditionNextTimeUserLoginsCheckbox = By.id("nextLoginSiteTerms");

	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveButton);
		saveEle.click();
	}

	/**
	 * Enable site terms and conditions
	 * 
	 * @param state
	 *        Boolean: true - to check Enable site terms and conditions checkbox
	 *        false - to uncheck Enable site terms and conditions checkbox
	 * @author dheeraj.rajput
	 */
	public void enableSiteTermsAndCondition(boolean state)
	{
		setSelection(enableSiteTermsAndConditionsCheckbox, state);
	}

	/**
	 * Add content in terms and condition content editor box
	 * 
	 * @param text
	 *        String to add
	 * @author dheeraj.rajput
	 */
	public void setTermsAndConditionText(String text)
	{
		WebElement contentEditor = findVisibleElement(termsAndConditionContentEditorBox);
		contentEditor.clear();
		contentEditor.sendKeys(text.trim());
	}

	/**
	 * Click on Show on first site login only radio
	 * 
	 * @param showNextTime
	 *        Boolean: true - to check Show terms and conditions next time any user logs in checkbox
	 *        false - to uncheck Show terms and conditions next time any user logs in checkbox
	 * @author dheeraj.rajput
	 */
	public void showOnFirstSiteLogin(boolean showNextTime)
	{
		findVisibleElement(showOnFirstSiteLoginRadio).click();
		setSelection(showTermsAndConditionNextTimeUserLoginsCheckbox, showNextTime);
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
