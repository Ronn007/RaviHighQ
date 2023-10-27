package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminSiteSummaryPage;

/**
 * @author jyoti.raj
 */
public class AdminSiteSummaryWeb extends AdminPageWeb implements AdminSiteSummaryPage
{

	By saveAsTemplate = By.xpath("//strong[normalize-space(text())='Save as template']");
	By templateName = By.id("templateName");
	By saveButton = By.id("saveAsTemplatePageModal_saveAsTemplatePage");

	public AdminSiteSummaryWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 */
	public void clickOnSaveAsTemplate()
	{
		WebElement template = findVisibleElement(saveAsTemplate);
		template.click();

	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @param tempName
	 */
	public void enterTemplateName(String tempName)
	{

		WebElement element = findVisibleElement(templateName);
		element.clear();
		element.sendKeys(tempName);
	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @return
	 */
	public AdminPageWeb clickOnSaveInSaveAsTemplate()
	{
		WebElement element = findVisibleElement(saveButton);
		element.click();
		return new AdminPageWeb(driver);
	}

}
