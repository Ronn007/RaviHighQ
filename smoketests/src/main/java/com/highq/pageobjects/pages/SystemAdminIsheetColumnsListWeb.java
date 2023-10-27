package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.SystemAdminIsheetColumnsListPage;

public class SystemAdminIsheetColumnsListWeb extends BannerPageWeb implements SystemAdminIsheetColumnsListPage
{
	By addNewColumnBtn = By.xpath(".//*[@id='docsNavLeft']//*[@title='Add new column']");

	public SystemAdminIsheetColumnsListWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * This method is used to click on column name to edit the column
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param columnName
	 * @return
	 */
	public SystemAdminEditColumnWeb clickOnColumnName(String columnName)
	{
		findClickableElement(By.xpath(".//*[@name='addColumn']//*[text()='" + columnName.trim() + "']"), Speed.slow).click();
		return new SystemAdminEditColumnWeb(driver);
	}

	/**
	 * This method is used to click on Add new column button
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public SystemAdminIsheetAdminAddColumnWeb clickOnAddNewColumn()
	{
		findClickableElement(addNewColumnBtn, Speed.slow).click();
		return new SystemAdminIsheetAdminAddColumnWeb(driver);
	}

	/**
	 * This method is used to verify that column is displayed
	 * 
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param columnName
	 * @return
	 */
	public boolean verifyColumnDisplayed(String columnName)
	{
		return isDisplayed(By.xpath(".//*[@name='addColumn']//*[text()='" + columnName.trim() + "']"), Speed.slow);
	}
}
