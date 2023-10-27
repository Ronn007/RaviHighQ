package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.pageobjects.base.AdminIsheetPage;

public class AdminIsheetWeb extends AdminPageWeb implements AdminIsheetPage
{
	public AdminIsheetWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By addIsheetBtn = By.id("isheet_admin_action_add_isheet_button");
	By addIsheetOpened = By.xpath(".//*[@id='isheet_admin_action_add_isheet_button' and @aria-expanded='true']");
	By addIsheetOptionLink = By.linkText("iSheet");
	By importFromTemplateOptionLink = By.linkText("Import from template");
	By deleteIsheetBtn = By.id("isheet_admin_action_delete_button");
	By sortIsheetBtn = By.id("isheet_admin_action_sort_isheet_button");

	By manageDocumentTemplatesLink = By.linkText("Manage document templates");

	By selectAllIsheetsInput = By.id("isheet_admin_selectAll_checkBox_Name");

	/** Import iSheet */

	By importIsheetModal = By.id("ISHEET_IMPORT_TEMPLATE_MODAL");
	By importIsheetModalOpened = By.xpath(".//*[@id='ISHEET_IMPORT_TEMPLATE_MODAL' and @aria-hidden='false']");
	By importIsheetModalClosed = By.xpath(".//*[@class='siteAdmin normalScreenMode adminIsheetPage']");
	By importIsheetTemplateList = By.id("templateList");
	By linkedIsheetNo = By.id("duplicateiSheetType");
	By linkedIsheetYes = By.id("iSheetTemplateType");
	By importSynchronizeDataChkBox = By.id("importSynchronizeDataID");
	By importIsheetModalImportBtn = By.id("ISHEET_IMPORT_TEMPLATE_MODAL_isheetImportTemplateSave");
	By importIsheetModalCancelBtn = By.id("ISHEET_IMPORT_TEMPLATE_MODAL_isheetImportTemplateCancel");
	By importIsheetModalCloseBtn = By.id("ISHEET_IMPORT_TEMPLATE_MODAL_MAIN_CLOSE_BUTTON");

	/** Sort iSheet */

	By sortIsheetModal = By.id("ISHEET_SORT_BY_DRAGGING_MODAL");
	By sortIsheetModalOpened = By.xpath(".//*[@id='ISHEET_SORT_BY_DRAGGING_MODAL' and @aria-hidden='false']");
	By sortIsheetModalClosed = By.xpath(".//*[@id='ISHEET_SORT_BY_DRAGGING_MODAL' and @aria-hidden='true']");
	By sortIsheetModalCancelBtn = By.id("ISHEET_SORT_BY_DRAGGING_MODAL_cancelLinkButton");
	By sortIsheetModalApplyBtn = By.id("ISHEET_SORT_BY_DRAGGING_MODAL_applyButton");
	By sortIsheetModalCloseBtn = By.id("ISHEET_SORT_BY_DRAGGING_MODAL_MAIN_CLOSE_BUTTON");
	By sortIsheetModalDraggingModal = By.id("ISHEET_SORT_BY_DRAGGING_MODAL_BODY");
	By sortIsheetModalSortedData = By.id("isheetSortedData");
	By sortIsheetModalSortedSheetFrm = By.id("sortedSheetForm");

	/** Delete iSheet */

	By deleteIsheetModal = By.id("isheet_admin_sheet_delete_model");
	// By deleteIsheetModalOpened = By.xpath(".//*[@id='isheet_admin_sheet_delete_model' and @aria-hidden='false']");
	// By deleteIsheetModalClosed = By.xpath(".//*[@id='isheet_admin_sheet_delete_model' and @aria-hidden='true']");
	By deleteIsheetModalOpened = By.xpath(".//*[@id='isheet_admin_sheet_delete_model' and (not(contains(@style,'display: none;')))]");
	By deleteIsheetModalClosed = By.xpath(".//*[@class='siteAdmin normalScreenMode adminIsheetPage']");
	By deleteIsheetModalMessage = By.id("isheet_admin_sheet_delete_model_BODY");
	By deleteIsheetModalDeleteBtn = By.id("isheet_admin_sheet_delete_model_isheet_admin_delete");
	By deleteIsheetModalCancelBtn = By.id("isheet_admin_sheet_delete_model_isheet_admin_sheet_cancel_btn");
	By deleteIsheetModalCloseBtn = By.id("isheet_admin_sheet_delete_model_MAIN_CLOSE_BUTTON");

	/** iSheet More Actions */

	By isheetMoreActions_Delete = By.xpath(".//*[@class='dropdown pull-left open dropup']//*[text()='Delete']");
	By isheetMoreActions_SetAsDefaultSheet = By.xpath(".//*[@class='dropdown pull-left open dropup']//*[text()='Set as default sheet']");
	By isheetMoreActions_ManageViews = By.xpath(".//*[@class='dropdown pull-left open dropup']//*[text()='Manage views']");
	By iSheetMoreActions_ManageColumns = By.xpath(".//*[@class='dropdown pull-left open dropup']//*[text()='Manage columns']");

	/** Isheet table */
	By isheetTable = By.id("fileData");
	String fileMetaXpath = "//*[normalize-space(text())='(File Metadata)']";
	String folderMetaXpath = "//*[normalize-space(text())='(Folder Metadata)']";

	/** Manage document templates */

	By manageDocumentTemplatsLink = By.id("isheetAdmin_manageDocTemplateID");

	@Override
	public void clickOnAddIsheet()
	{
		WebElement addEle = findClickableElement(addIsheetBtn);
		addEle.click();
	}

	@Override
	public AdminIsheetWeb selectAddIsheetOptions(String option)
	{
		findVisibleElement(addIsheetOpened, Speed.slow);
		switch (option.toLowerCase())
		{
			case "isheet":
				WebElement addIsheetOptionEle = findClickableElement(addIsheetOptionLink);
				addIsheetOptionEle.click();
				return new AdminAddIsheetWeb(driver);
			case "import from template":
				WebElement importFromTemplateOptionEle = findClickableElement(importFromTemplateOptionLink);
				importFromTemplateOptionEle.click();
				return this;
			default:
				System.err.println("Enter valid option value");
				return this;
		}
	}

	@Override
	public void selectAlliSheets()
	{
		WebElement selectAllIsheetsEle = findClickableElement(selectAllIsheetsInput);
		selectAllIsheetsEle.click();
	}

	@Override
	public void selectISheet(String isheetName)
	{
		WebElement isheetEle = findClickableElement(By.xpath("(.//*[text()='" + isheetName.trim() + "'])[1]//preceding::*[@type='checkbox'][1]"));
		isheetEle.click();
	}

	@Override
	public AdminIsheetEditWeb clickOnIsheetName(String iSheetName)
	{
		WebElement isheetEle = findClickableElement(By.xpath("(.//*[text()='" + iSheetName.trim() + "'])[1]"));
		isheetEle.click();

		return new AdminIsheetEditWeb(driver);
	}

	@Override
	public void clickOnIsheetMoreAction(String iSheetName)
	{
		WebElement moreActionEle = findClickableElement(By.xpath("(.//*[contains(text(),'" + iSheetName.trim() + "')]//following::*[@id='isheetAdminMoreAction'])[1]"), Speed.slow);
		moreActionEle.click();
	}

	@Override
	public AdminIsheetWeb selectOptionOnIsheetMoreAction(String iSheetName, String option)
	{
		clickOnIsheetMoreAction(iSheetName);
		findPresentElement(By.xpath("(.//*[contains(text(),'" + iSheetName.trim() + "')]//following::*[@id='isheetAdminMoreAction' and @aria-expanded='true'])[1]"), Speed.slow);
		WebElement moreActionsOptionEle = findClickableElement(By.xpath(".//*[@id='isheetAdminMoreAction' and @aria-expanded='true']//following-sibling::*//*[normalize-space(text())='" + option.trim() + "']"));
		moreActionsOptionEle.click();

		switch (option.toLowerCase())
		{
			case "manage columns":
				return new AdminIsheetManageColumnWeb(driver);
			case "manage views":
				return new AdminIsheetManageViewsWeb(driver);
			case "manage permissions":
				return new AdminIsheetManagePermissionsWeb(driver);
			case "set as default sheet":
				return this;
			case "delete":
				return this;
			default:
				System.err.println("Enter Valid Option");
				return this;
		}
	}

	@Override
	public void clickDeleteIsheet()
	{
		WebElement deleteIsheetEle = findClickableElement(deleteIsheetBtn);
		if (deleteIsheetEle.isEnabled())
		{
			deleteIsheetEle.click();
		}
		else
		{
			System.err.println("Delete Button is disabled.");
		}
	}

	@Override
	public void clickSortIsheet()
	{
		WebElement sortIsheetEle = findClickableElement(sortIsheetBtn);
		sortIsheetEle.click();
	}

	/** Import iSheet */

	@Override
	public void selectImportIsheetTemplate(String template)
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		Select importIsheetTemplateEle = new Select(findPresentElement(importIsheetTemplateList));
		importIsheetTemplateEle.selectByVisibleText(template);
	}

	@Override
	public void selectLinkedIsheetOption(boolean option)
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		if (option)
		{
			WebElement linkedIsheetYesEle = findClickableElement(linkedIsheetYes);
			linkedIsheetYesEle.click();
		}
		else
		{
			WebElement linkedIsheetNoEle = findClickableElement(linkedIsheetNo);
			linkedIsheetNoEle.click();
		}
	}

	@Override
	public void setImportSynchronizeData()
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		WebElement linkedIsheetYesEle = findClickableElement(linkedIsheetYes);
		WebElement importSynchronizeDataEle = findVisibleElement(importSynchronizeDataChkBox);
		if (linkedIsheetYesEle.isSelected() && importSynchronizeDataEle.isEnabled())
		{
			importSynchronizeDataEle.click();
		}
	}

	@Override
	public void clickImportOnImportIsheetModal()
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		WebElement importIsheetModalImportEle = findClickableElement(importIsheetModalImportBtn);
		importIsheetModalImportEle.click();
		findPresentElement(importIsheetModalClosed);
	}

	@Override
	public void clickCancelOnImportIsheetModal()
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		WebElement importIsheetModalCancelEle = findClickableElement(importIsheetModalCancelBtn);
		importIsheetModalCancelEle.click();
		findPresentElement(importIsheetModalClosed);
	}

	@Override
	public void clickCloseOnImportIsheetModal()
	{
		findVisibleElement(importIsheetModalOpened, Speed.slow, 5);
		WebElement importIsheetModalCloseEle = findClickableElement(importIsheetModalCloseBtn);
		importIsheetModalCloseEle.click();
		findPresentElement(importIsheetModalClosed);
	}

	/** Sort iSheet */

	@Override
	public void clickApplyOnSortIsheetModal()
	{
		findVisibleElement(sortIsheetModalOpened, Speed.slow, 5);
		WebElement sortIsheetModalApplyEle = findClickableElement(sortIsheetModalApplyBtn);
		sortIsheetModalApplyEle.click();
	}

	@Override
	public void clickCancelOnSortIsheetModal()
	{
		findVisibleElement(sortIsheetModalOpened, Speed.slow, 5);
		WebElement sortIsheetModalCancelEle = findClickableElement(sortIsheetModalCancelBtn);
		sortIsheetModalCancelEle.click();
	}

	@Override
	public void clickCloseOnSortIsheetModal()
	{
		findVisibleElement(sortIsheetModalOpened, Speed.slow, 5);
		WebElement sortIsheetModalCloseEle = findClickableElement(sortIsheetModalCloseBtn);
		sortIsheetModalCloseEle.click();
	}

	@Override
	public void dragAndDropIsheets(String sourceIsheetName, String destinationIsheetName)
	{
		findVisibleElement(sortIsheetModalOpened, Speed.slow, 5);
		WebElement sourceIsheetNameEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_BY_DRAGGING_MODAL']//*[normalize-space(text())='" + sourceIsheetName.trim() + "']"));
		WebElement destinationIsheetNameEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_BY_DRAGGING_MODAL']//*[normalize-space(text())='" + destinationIsheetName.trim() + "']"));
		sortItems(sourceIsheetNameEle, destinationIsheetNameEle);
	}

	/** Delete iSheet */

	@Override
	public void clickDeleteOnDeleteIsheetModal()
	{
		findVisibleElement(deleteIsheetModalOpened);
		WebElement deleteIsheetModalDeleteEle = findClickableElement(deleteIsheetModalDeleteBtn);
		deleteIsheetModalDeleteEle.click();
		findPresentElement(deleteIsheetModalClosed);
	}

	@Override
	public void clickCancelOnDeleteIsheetModal()
	{
		findVisibleElement(deleteIsheetModalOpened);
		WebElement deleteIsheetModalDeleteEle = findClickableElement(deleteIsheetModalCancelBtn);
		deleteIsheetModalDeleteEle.click();
		findPresentElement(deleteIsheetModalClosed);
	}

	@Override
	public void clickCloseOnDeleteIsheetModal()
	{
		findVisibleElement(deleteIsheetModalOpened);
		WebElement deleteIsheetModalDeleteEle = findClickableElement(deleteIsheetModalCloseBtn);
		deleteIsheetModalDeleteEle.click();
		findPresentElement(deleteIsheetModalClosed);
	}

	@Override
	public boolean verifyDeleteIsheetModalMessage(String message)
	{
		findVisibleElement(deleteIsheetModalOpened);
		WebElement deleteIsheetModalMessageEle = findVisibleElement(deleteIsheetModalMessage);
		return deleteIsheetModalMessageEle.getText().equalsIgnoreCase(message);
	}

	@Override
	public void deleteIsheet(String iSheetName)
	{
		selectISheet(iSheetName);
		clickDeleteIsheet();
		clickDeleteOnDeleteIsheetModal();
	}

	@Override
	public boolean verifyIsheetTitleHasFolderMetaEnabled(String isheetTitle)
	{
		findVisibleElement(isheetTable, Speed.slow);
		return isDisplayed(By.xpath("//*[@id='fileData']//*[normalize-space(text())='" + isheetTitle.trim() + "']//parent::*//following-sibling::*[normalize-space(.)='(Folder metadata)']"));
	}

	@Override
	public boolean verifyIsheetTitleHasFileMetaEnabled(String isheetTitle)
	{
		findVisibleElement(isheetTable, Speed.slow);
		return isDisplayed(By.xpath("//*[@id='fileData']//*[normalize-space(text())='" + isheetTitle.trim() + "']//parent::*//following-sibling::*[normalize-space(.)='(File metadata)']"));
	}

	@Override
	public boolean isheetExist(String isheetTitle)
	{
		findVisibleElement(isheetTable, Speed.slow);
		return isDisplayed(By.xpath("(//*[@id='fileData']//*[normalize-space(text())='" + isheetTitle.trim() + "'])[last()]"));
	}

	/** Common Methods */

	@Override
	public void selectCheckBoxOption(String option, boolean val)
	{
		if (isDisplayed(By.xpath(".//*[normalize-space()='" + option.trim() + "']//child::*[@type='checkbox']")))
		{
			setSelection(By.xpath(".//*[normalize-space()='" + option.trim() + "']//child::*[@type='checkbox']"), val);
		}
	}

	@Override
	public void selectRadioOption(String option)
	{
		WebElement formatEle = findPresentElement(By.xpath(".//*[normalize-space()='" + option.trim() + "']//child::*[@type='radio']"));
		scrollToElement(formatEle);
		formatEle.click();
	}

	@Override
	public void sortItems(WebElement source, WebElement destination)
	{
		Actions builder = new Actions(driver);

		Action dragAndDrop = builder.clickAndHold(source)
				.moveToElement(destination)
				.release(destination)
				.build();

		dragAndDrop.perform();
	}

	/**
	 * Click On 'Manage Document Template Link'
	 *
	 * @return
	 * @author nidhi.shah
	 * @Created: 24-04-2018
	 * @Modified:
	 */
	@Override
	public AdminIsheetManageDocumentTemplatesWeb clickManageDocumentTemplatesLink()
	{
		WebElement manageDocumentTemplatsLinkEle = findVisibleElement(manageDocumentTemplatsLink);
		manageDocumentTemplatsLinkEle.click();
		// while (isDisplayed(grayLoader)) {
		// ;
		// }
		return new AdminIsheetManageDocumentTemplatesWeb(driver);
	}
}
