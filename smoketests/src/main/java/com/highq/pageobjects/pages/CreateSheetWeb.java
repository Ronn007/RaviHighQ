package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.CreateSheetLabels;
import com.highq.pageobjects.base.AdminIsheetsPage;
import com.highq.pageobjects.base.CreateSheetPage;

public class CreateSheetWeb extends AdminPageWeb implements CreateSheetPage
{

	By createSheetHeader = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_HEADER + "']");
	By titleInput = By.id("title");
	By descriptionInput = By.id("description");
	By statusDropDown = By.id("isheet_status");
	By accessType_privateRadio = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_ACCESSTYPE_PRIVATE + "']/preceding-sibling::*[1]");
	By accessType_publicRadio = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_ACCESSTYPE_PUBLIC + "']/preceding-sibling::*[1]");
	By fileMetadataTemplateCheckbox = By.id("sheetMetaData");
	By folderMetadataTemplateCheckbox = By.id("sheetFolderMetaData");
	By allowUserToSeeLinkCheckbox = By.xpath("//*[@name='isheet.allowViewLink']");
	By allowSectionCheckbox = By.xpath("//*[@name='isheet.allowSection']");
	By allowLookupCheckbox = By.xpath("//*[@name='isheet.allowInjection']");
	By displayIsheetCheckbox = By.xpath("//*[@name='isheet.displaySheet']");
	By displaySearchAsDefaultViewCheckbox = By.xpath("//*[@name='isheet.showSearchAsDefaultView']");
	By enableVersionCheckbox = By.id("enableVersion");
	By overrideRecordModifiedDateCheckbox = By.id("overrideItemModifiedDate");
	By enableBulkInsertUpdateCheckbox = By.id("enableBulkInsertUpdateChunk");
	By fieldDescription_slideInRadio = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_ACCESSTYPE_SLIDEIN + "']/preceding-sibling::*[1]");
	By fieldDescription_inlineRadio = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_ACCESSTYPE_INLINE + "']/preceding-sibling::*[1]");
	By enableRecordLockingCheckbox = By.id("enableRowLocking");

	By saveButton = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_SAVE + "']");
	By cancelButton = By.xpath("//*[normalize-space(text())='" + CreateSheetLabels.CREATESHEET_CANCEL + "']");

	Select dropDown;

	public CreateSheetWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public void setTitle(String sheetTitle)
	{
		findVisibleElement(createSheetHeader);
		findVisibleElement(titleInput).sendKeys(sheetTitle.trim());
	}

	public void setDescription(String description)
	{
		findVisibleElement(createSheetHeader);
		findVisibleElement(descriptionInput).sendKeys(description.trim());
	}

	public void selectStatus(String status)
	{
		findVisibleElement(createSheetHeader);
		WebElement statusDrpDwn = findVisibleElement(statusDropDown);
		dropDown = new Select(statusDrpDwn);
		dropDown.selectByVisibleText(status.trim());
	}

	public void selectAccessType(String accessType)
	{
		findVisibleElement(createSheetHeader);
		String lowerCase = accessType.trim().toLowerCase();
		if (CreateSheetLabels.CREATESHEET_ACCESSTYPE_PRIVATE.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(accessType_privateRadio).click();
		}
		else if (CreateSheetLabels.CREATESHEET_ACCESSTYPE_PUBLIC.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(accessType_publicRadio).click();
		}
	}

	public void selectFieldDescription(String fieldDescription)
	{
		findVisibleElement(createSheetHeader);
		String lowerCase = fieldDescription.trim().toLowerCase();
		if (CreateSheetLabels.CREATESHEET_ACCESSTYPE_SLIDEIN.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(fieldDescription_slideInRadio).click();
		}
		else if (CreateSheetLabels.CREATESHEET_ACCESSTYPE_INLINE.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(fieldDescription_inlineRadio).click();
		}
	}

	public void fileMetadataTemplate(boolean state)
	{
		setSelection(fileMetadataTemplateCheckbox, state);
	}

	public void folderMetadataTemplate(boolean state)
	{
		setSelection(folderMetadataTemplateCheckbox, state);
	}

	public void allowAllUserToSeeTheViewLink(boolean state)
	{
		setSelection(allowUserToSeeLinkCheckbox, state);
	}

	public void allowSections(boolean state)
	{
		setSelection(allowSectionCheckbox, state);
	}

	public void allowLookup(boolean state)
	{
		setSelection(allowLookupCheckbox, state);
	}

	public void displayIsheet(boolean state)
	{
		setSelection(displayIsheetCheckbox, state);
	}

	public void displaySearchAsDefaultView(boolean state)
	{
		setSelection(displaySearchAsDefaultViewCheckbox, state);
	}

	public void enableVersion(boolean state)
	{
		setSelection(enableVersionCheckbox, state);
	}

	public void overrideRecordModifiedDate(boolean state)
	{
		setSelection(overrideRecordModifiedDateCheckbox, state);
	}

	public void enableBulkInsertUpdateInBatches(boolean state)
	{
		setSelection(enableBulkInsertUpdateCheckbox, state);
	}

	public void enableRecordLocking(boolean state)
	{
		setSelection(enableRecordLockingCheckbox, state);
	}

	public AdminIsheetsPage clickSave()
	{
		findVisibleElement(saveButton).click();
		return new AdminIsheetsWeb(driver);
	}

	public AdminIsheetsPage clickCancel()
	{
		findVisibleElement(cancelButton).click();
		return new AdminIsheetsWeb(driver);
	}

}
