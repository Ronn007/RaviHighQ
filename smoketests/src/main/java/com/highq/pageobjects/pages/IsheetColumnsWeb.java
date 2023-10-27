package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.IsheetLabels;
import com.highq.pageobjects.base.IsheetColumnsPage;

public class IsheetColumnsWeb extends AdminPageWeb implements IsheetColumnsPage
{
	By backButton = By.xpath("//*[normalize-space(text())='" + IsheetLabels.ISHEETCOLUMNS_BACK + "']");
	By addNewColumnButton = By.xpath("//*[normalize-space(text())='" + IsheetLabels.ISHEETCOLUMNS_ADDNEWCOLUMN + "']");
	By sortColumnsButton = By.xpath("//*[normalize-space(text())='" + IsheetLabels.ISHEETCOLUMNS_SORTCOLUMNS + "']");

	public IsheetColumnsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public AddColumnsWeb clickAddNewColumn()
	{
		findVisibleElement(addNewColumnButton).click();
		return new AddColumnsWeb(driver);
	}

	public boolean verifyColumnExists(String columnName)
	{
		By column = By.xpath("//*[normalize-space(text())='" + columnName.trim() + "']");
		return isDisplayed(column);
	}

}
