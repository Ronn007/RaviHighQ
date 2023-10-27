package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminSystemVocabularyPage;

public class SystemAdminSystemVocabularyWeb extends BannerPageWeb implements SystemAdminSystemVocabularyPage
{
	By back = By.id("Back");

	public SystemAdminSystemVocabularyWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * change Module Name From System Vocabulary
	 * 
	 * @param moduleName
	 * @param newModuleName
	 * @author krishna.bhadani
	 */
	public void changeModuleName(String moduleName, String newModuleName)
	{
		findVisibleElement(By.xpath(".//span[normalize-space(.)='" + moduleName.trim() + "']/ancestor::div[2]//a[text()='" + SystemAdminLabels.SYSADMIN_EDIT + "']")).click();

		WebElement moduleNameEle = findVisibleElement(By.xpath(".//*[contains(@id,'textVocabulary') and @value='" + moduleName.trim() + "']"));
		moduleNameEle.clear();
		moduleNameEle.sendKeys(newModuleName.trim());
		findVisibleElement(By.xpath(".//span[normalize-space(.)='" + moduleName.trim() + "']/ancestor::div[2]//a[text()='" + SystemAdminLabels.SYSADMIN_DONE + "']")).click();
	}

	/**
	 * Reset All Module Name
	 * 
	 * @author krishna.bhadani
	 */
	public void resetAllModuleName()
	{
		findVisibleElement(By.xpath(".//a[normalize-space(.)='" + SystemAdminLabels.RESET_DEFAULTS + "']")).click();
	}

	/**
	 * Click On Save In System Vocabulary Page
	 * 
	 * @author krishna.bhadani
	 */
	public void clickOnSave()
	{
		findVisibleElement(By.xpath(".//a[normalize-space(.)='" + SystemAdminLabels.SYSADMIN_SYSTEMSETTINGS_SAVE + "']")).click();
	}

	/**
	 * Click On Back
	 * 
	 * @author krishna.bhadani
	 */
	public void clickOnBack()
	{
		findVisibleElement(back).click();
	}
}
