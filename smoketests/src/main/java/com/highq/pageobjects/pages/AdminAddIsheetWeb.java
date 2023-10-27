package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminAddIsheetPage;

public class AdminAddIsheetWeb extends AdminIsheetWeb implements AdminAddIsheetPage
{
	public AdminAddIsheetWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By isheetTitleInput = By.id("title");
	By isheetDescriptionTextarea = By.id("description");
	By isheetStatusBtn = By.xpath(".//*[@data-id='isheetStatus']");
	By isheetStatusBtnOpened = By.xpath(".//*[@data-id='isheetStatus' and @aria-expanded = 'true']");
	By iSheetAccessTypePrivate = By.id("isheet_sheetAccessTypeID1");
	By iSheetAccessTypePublic = By.id("isheet_sheetAccessTypeID2");
	By iSheetFieldDescriptionSlideIn = By.id("isheet_fieldDescriptionID1");
	By iSheetFieldDescriptionInline = By.id("isheet_fieldDescriptionID2");

	By fileMetadataInput = By.id("sheetMetaData");
	By folderMetadataInput = By.id("sheetFolderMetaData");
	By allowViewLinkInput = By.id("sheetAllowViewLink");
	By allowSectionInput = By.id("chk_allowSection");
	By allowLookupsInput = By.id("chk_allowInjection");
	By displayIsheetInput = By.id("chk_displaySheet");
	By searchDefaultViewInput = By.id("chk_showSearchAsDefaultView");
	By enableArchivingInput = By.id("enableVersion");
	By overrideModifiedDateInput = By.id("overrideItemModifiedDate");
	By enableRecordLockingInput = By.id("enableRowLocking");
	By enableSheetAlerter = By.id("sheetAlerter");
	By emailAlertConditionInput = By.id("alerterCondition");
	By sheetAlerter = By.id("sheetAlerterDiv");

	By addIsheetTopSaveBtn = By.id("adminAddIsheetSaveBtnTop");
	By addIsheetBottomSaveBtn = By.id("adminAddIsheetSaveBtnBottom");

	By addIsheetTopCancelBtn = By.id("adminAddIsheetCancleBtnTop");
	By addIsheetBottomCancelBtn = By.id("adminAddIsheetCancleBtnBottom");
	By globalErrorMsg = By.xpath(".//*[@id='addIsheetMsgID_ErrorDiv' and not(@style='display: none;')]");

	@Override
	public void addIsheetTitle(String title)
	{
		WebElement isheetTitleEle = findClickableElement(isheetTitleInput);
		isheetTitleEle.clear();
		isheetTitleEle.sendKeys(title);
	}

	@Override
	public void addIsheetDescription(String description)
	{
		WebElement isheetDescriptionEle = findClickableElement(isheetDescriptionTextarea);
		isheetDescriptionEle.clear();
		isheetDescriptionEle.sendKeys(description);
	}

	@Override
	public void clickIsheetStatusDropdown()
	{
		WebElement isheetStatusEle = findClickableElement(isheetStatusBtn);
		isheetStatusEle.click();
	}

	@Override
	public void selectIsheetStatus(String option)
	{
		WebElement statusEle = findClickableElement(By.xpath(".//*[@class='dropdown-menu open' and @role='combobox']//*[normalize-space(text())='" + option + "']"));
		statusEle.click();
	}

	@Override
	public void selectIsheetAccessType(String option)
	{
		switch (option.toLowerCase())
		{
			case "public":
				WebElement iSheetAccessTypePublicEle = findClickableElement(iSheetAccessTypePublic);
				iSheetAccessTypePublicEle.click();
				break;
			case "private":
				WebElement iSheetAccessTypePrivateEle = findClickableElement(iSheetAccessTypePrivate);
				iSheetAccessTypePrivateEle.click();
				break;
			default:
				System.err.println("Enter valid option value");
				break;
		}
	}

	@Override
	public void selectIsheetFieldDescription(String option)
	{
		switch (option.toLowerCase())
		{
			case "slide in":
				WebElement iSheetFieldDescriptionSlideInEle = findClickableElement(iSheetFieldDescriptionSlideIn);
				iSheetFieldDescriptionSlideInEle.click();
				break;
			case "inline":
				WebElement iSheetFieldDescriptionInlineEle = findClickableElement(iSheetFieldDescriptionInline);
				iSheetFieldDescriptionInlineEle.click();
				break;
			default:
				System.err.println("Enter valid option value");
				break;
		}
	}

	@Override
	public void addIsheetSelectCheckBoxOption(String option, boolean val)
	{
		selectCheckBoxOption(option, val);
	}

	@Override
	public AdminIsheetWeb addIsheetClickSave()
	{
		WebElement saveEle = findPresentElement(addIsheetTopSaveBtn);
		scrollToElement(saveEle);
		saveEle.click();
		if (isDisplayed(globalErrorMsg))
		{
			return this;
		}
		return new AdminIsheetWeb(driver);
	}

	@Override
	public AdminIsheetWeb addIsheetClickCancel()
	{
		WebElement cancelEle = findPresentElement(addIsheetTopCancelBtn);
		scrollToElement(cancelEle);
		cancelEle.click();
		return new AdminIsheetWeb(driver);
	}
}
