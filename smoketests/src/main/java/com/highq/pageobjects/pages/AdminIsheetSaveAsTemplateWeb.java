package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminIsheetSaveAsTemplatePage;

public class AdminIsheetSaveAsTemplateWeb extends AdminIsheetEditWeb implements AdminIsheetSaveAsTemplatePage
{

	By templateName = By.id("templatename");
	By globalErrorMsg = By.xpath(".//*[@id='addIsheetMsgID_ErrorDiv' and not(@style='display: none;')]");

	public AdminIsheetSaveAsTemplateWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to verify warning message for iSheet which links to external data sources
	 *
	 * @author janki.hirani
	 * @created on 23 May 2018
	 * @param msg
	 * @return
	 */
	public boolean verifyWarningMsg(String msg, boolean displayed)
	{
		if (displayed)
		{
			return isDisplayed(By.xpath(".//*[@id='addIsheetMsgID_ErrorDiv' and not(@style='display: none;')]//*[@id='errorElementContainer' and text()='" + msg + "']"), Speed.slow);
		}
		else
		{
			return !isDisplayed(globalErrorMsg);
		}
	}

	/**
	 * This method is used to enter template name
	 *
	 * @author janki.hirani
	 * @created on 23 May 2018
	 * @param tempName
	 */
	public void enterTemplateName(String tempName)
	{
		findVisibleElement(templateName, Speed.slow).sendKeys(tempName);
	}

}
