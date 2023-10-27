package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.SystemAdminIsheetAdminPage;

public class SystemAdminIsheetAdminWeb extends BannerPageWeb implements SystemAdminIsheetAdminPage
{

	By totalTempRow = By.xpath(".//*[@id='deleteTemplates']//*[@class='responsive columnView']//tr");
	By selectAllCheckbox = By.xpath(".//*[@id='deleteTemplates']//*[@class='columnHeadings']//*[@type='checkbox']");
	By deleteTempButton = By.xpath(".//*[@id='docsNavLeft']//*[@title='Delete template']");

	public SystemAdminIsheetAdminWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * This method is used to verify that given template name is displayed in the iSheet template list
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param templateName
	 * @return
	 */
	public boolean verifyIsheetTemplateIsDisplayed(String templateName)
	{
		return isDisplayed(By.xpath(".//*[@id='deleteTemplates']//a[normalize-space()='" + templateName.trim() + "']"), Speed.slow);
	}

	/**
	 * This method is used to select a template from iSheet template list
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param templateName
	 */
	public void selectTemplate(String templateName)
	{
		scrollToBottom();
		WebElement checkBox = findVisibleElement(By.xpath(".//*[@id='deleteTemplates']//a[normalize-space()='" + templateName.trim() + "']/parent::*/preceding-sibling::*/*[@type='checkbox']"), Speed.slow);
		checkBox.click();
	}

	/**
	 * Thsi method is used to click on Delete template button on top
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 */
	public void clickOnDeleteTemplateButton()
	{
		findClickableElement(deleteTempButton, Speed.slow).click();
		getPopupMsgFromSystemPopupAndAccept();
	}

	/**
	 * This method is used to delete all the templates
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 */
	public void deleteAllTemplates()
	{
		if (!isSelected(selectAllCheckbox))
		{
			findClickableElement(selectAllCheckbox, Speed.slow).click();
		}
		clickOnDeleteTemplateButton();
	}

	/**
	 * This method is used to click on iSheet template name
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param templateName
	 */
	public SystemAdminTemplateIsheetsListWeb clickOnTemplateName(String templateName)
	{
		findVisibleElement(By.xpath(".//*[@id='deleteTemplates']//a[normalize-space()='" + templateName + "']"), Speed.slow).click();
		return new SystemAdminTemplateIsheetsListWeb(driver);
	}
}
