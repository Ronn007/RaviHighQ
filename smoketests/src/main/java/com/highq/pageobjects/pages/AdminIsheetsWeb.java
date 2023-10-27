package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminIsheetsPage;

public class AdminIsheetsWeb extends AdminPageWeb implements AdminIsheetsPage
{

	By createSheetButton = By.xpath("//*[normalize-space(text())='Create sheet']");
	By deleteButton = By.xpath("//*[normalize-space(text())='Delete']");
	By sortSheetButton = By.xpath("//*[normalize-space(text())='Sort sheet']");
	By importIsheetTemplateButton = By.xpath("//*[normalize-space(text())='Import iSheet template']");
	By settingsButton = By.xpath("//*[normalize-space(text())='Settings']");
	/** Isheet table */
	By isheetTable = By.xpath("//*[@class='contentMain']");
	String fileMetaXpath = "//*[normalize-space(text())='(File Metadata)']";
	String isheetNameCommon = "//*[@class='contentMain']//*[normalize-space(text())='";
	String folderMetaXpath = "//*[normalize-space(text())='(Folder Metadata)']";

	public AdminIsheetsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public CreateSheetWeb clickOnCreateSheet()
	{
		findVisibleElement(createSheetButton).click();
		return new CreateSheetWeb(driver);
	}

	public boolean verifyIsheetTitle(String isheetTitle)
	{

		findVisibleElement(isheetTable, Speed.slow, 10);
		return isDisplayed(By.xpath(isheetNameCommon + isheetTitle.trim() + "']"));
	}

	public boolean verifyIsheetTitleHasFileMetaEnabled(String isheetTitle)
	{

		findVisibleElement(isheetTable, Speed.slow, 10);
		return isDisplayed(By.xpath(isheetNameCommon + isheetTitle.trim() + "']" + fileMetaXpath));
	}

	public IsheetColumnsWeb addEditColumn(String isheetTitle)
	{
		WebElement addEditColumnLink = findVisibleElement(By.xpath("//*[@class='contentMain']//*[normalize-space(text())='" + isheetTitle.trim() + "']//following-sibling::*//*[contains(@onclick,'columnList')]"));
		addEditColumnLink.click();
		return new IsheetColumnsWeb(driver);
	}

	public boolean verifyIsheetTitleHasFolderMetaEnabled(String isheetTitle)
	{

		findVisibleElement(isheetTable, Speed.slow, 10);
		return isDisplayed(By.xpath(isheetNameCommon + isheetTitle.trim() + "']" + folderMetaXpath));
	}
}
