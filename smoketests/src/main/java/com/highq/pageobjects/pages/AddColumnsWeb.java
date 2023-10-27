package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.AddColumnsLabels;
import com.highq.pageobjects.base.AddColumnsPage;

public class AddColumnsWeb extends AdminPageWeb implements AddColumnsPage
{
	By columnNameInput = By.id("columnName");
	By saveButton = By.xpath("//*[normalize-space(text())='" + AddColumnsLabels.ADDCOLUMN_SAVE + "']");
	By errorMessage = By.xpath("//*[@class='errorMessage']");

	public AddColumnsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public void setColumnName(String columnName)
	{
		WebElement columnInptXpath = findVisibleElement(columnNameInput);
		columnInptXpath.clear();
		columnInptXpath.sendKeys(columnName.trim());
	}

	public IsheetColumnsWeb saveColumnChanges()
	{
		findVisibleElement(saveButton).click();
		if (isDisplayed(errorMessage))
			System.err.println(findVisibleElement(errorMessage).getText());
		return new IsheetColumnsWeb(driver);
	}

}
