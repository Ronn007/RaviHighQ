package com.highq.pageobjects.base;

import org.openqa.selenium.WebElement;
import com.highq.pageobjects.pages.AdminIsheetEditWeb;
import com.highq.pageobjects.pages.AdminIsheetManageDocumentTemplatesWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetPage extends BannerPage
{
	public void clickOnAddIsheet();

	public AdminIsheetWeb selectAddIsheetOptions(String option);

	public void selectAlliSheets();

	public void selectISheet(String isheetName);

	public AdminIsheetEditWeb clickOnIsheetName(String iSheetName);

	public void clickOnIsheetMoreAction(String iSheetName);

	public AdminIsheetWeb selectOptionOnIsheetMoreAction(String iSheetName, String option);

	public void clickDeleteIsheet();

	public void clickSortIsheet();

	public void selectImportIsheetTemplate(String template);

	public void selectLinkedIsheetOption(boolean option);

	public void setImportSynchronizeData();

	public void clickImportOnImportIsheetModal();

	public void clickCancelOnImportIsheetModal();

	public void clickCloseOnImportIsheetModal();

	public void clickApplyOnSortIsheetModal();

	public void clickCancelOnSortIsheetModal();

	public void clickCloseOnSortIsheetModal();

	public void clickDeleteOnDeleteIsheetModal();

	public void clickCancelOnDeleteIsheetModal();

	public void clickCloseOnDeleteIsheetModal();

	public boolean verifyDeleteIsheetModalMessage(String message);

	public void deleteIsheet(String iSheetName);

	public void selectCheckBoxOption(String option, boolean val);

	public void selectRadioOption(String option);

	public boolean verifyIsheetTitleHasFolderMetaEnabled(String isheetTitle);

	public boolean verifyIsheetTitleHasFileMetaEnabled(String isheetTitle);

	public void sortItems(WebElement source, WebElement destination);

	public void dragAndDropIsheets(String sourceIsheetName, String destinationIsheetName);

	public boolean isheetExist(String isheetTitle);

	public AdminIsheetManageDocumentTemplatesWeb clickManageDocumentTemplatesLink();
}
