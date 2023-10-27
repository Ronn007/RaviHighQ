package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.pageobjects.base.SystemAdminIsheetAdminAddColumnPage;

public class SystemAdminIsheetAdminAddColumnWeb extends BannerPageWeb implements SystemAdminIsheetAdminAddColumnPage
{
	By importExternatalDataCheckbox = By.id("sheetColumnImportAIData");
	By importExternalDatalabel = By.xpath(".//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_IMPORTDATAFROMEXTERNALSRC + "']");
	By sourceLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SOURCES + "']");
	By sourceDropdown = By.xpath(".//*[@data-id='sourceListDropDown']");
	By analysisEngineLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ANALYSISENGINE + "']");
	By engineDropdown = By.xpath(".//*[@data-id='engineListDropDown']");
	By fieldsLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDS + "']");
	By selectButton = By.id("selectFieldsButton");
	By columnNameTextBox = By.id("columnName");
	By saveButton = By.xpath(".//*[@id='listColumn']//*[normalize-space()='Save']");

	public SystemAdminIsheetAdminAddColumnWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * This method is used to verify that "import data from external source" label and check box is displayed or not
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public boolean verifyImportCheckBoxDisplayed()
	{
		return isDisplayed(importExternatalDataCheckbox, Speed.slow) && isDisplayed(importExternalDatalabel);
	}

	/**
	 * This method is used to verify that source property is Displayed
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public boolean vrifySourceProprtyDisplayedWithDropdown()
	{
		return isDisplayed(sourceLabel, sourceDropdown);
	}

	/**
	 * This method is used to verify that Analysis Engine label and drop down beside it displayed or not
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public boolean verifyAnalysisEngineDisplayedWithEngineDropdown()
	{
		return isDisplayed(analysisEngineLabel, engineDropdown);
	}

	/**
	 * This method is used to verify that Fields property is displayed
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public boolean verifyFieldsPropertyDisplayedWithSelectButton()
	{
		return isDisplayed(fieldsLabel, selectButton);
	}

	/**
	 * This method is used to enter column name
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param columnName
	 */
	public void enterColumnName(String columnName)
	{
		findVisibleElement(columnNameTextBox, Speed.slow).sendKeys(columnName);
	}

	/**
	 * This method is used to click on save column
	 * 
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public SystemAdminIsheetColumnsListWeb clickOnSave()
	{
		findClickableElement(saveButton, Speed.slow).click();
		return new SystemAdminIsheetColumnsListWeb(driver);
	}
}
