package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;

public class AdminIsheetManageColumnWeb extends AdminIsheetWeb implements AdminIsheetManageColumnPage
{
	public AdminIsheetManageColumnWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/** Manage Columns Home Page */

	By manageColumnsBackBtn = By.id("isheet_manage_column_back_button");
	By manageColumnsAddColumnBtn = By.id("addColumn_button");
	By manageColumnsSortColumnsBtn = By.id("sortColumn");
	By moreActionsDeleteColumn = By.xpath(".//*[@class='dropdown pull-left open']//*[@id='isheet_column_delete_link']");
	By moreActionsEditPermissionsColumn = By.xpath(".//*[@class='dropdown pull-left open']//*[@id='isheet_column_edit_permission_link']");
	By locBtnAddSection = By.id("addNewSection");
	By locBtnSortSection = By.id("sortSection");
	By locTableIsheetColumns = By.id("fileData");
	By addButtonForDmdIsheet = By.xpath(".//*[@id='addColumnID']//*[normalize-space(.)='Add']");
	By addDropdownOpen = By.xpath("//*[@id='addColumnID']//*[contains(@class,'open')]//*[normalize-space(.)='Add']");

	/** Add Section */

	By locInptSectionName = By.id("name");
	By locInptSectionDescription = By.id("description");
	By locBtnSectionSave = By.id("ISHEET_ADD_EDIT_SECTION_MODAL_applyButton");
	By locBtnSectionCancel = By.id("ISHEET_ADD_EDIT_SECTION_MODAL_cancelLinkButton");
	By locBtnSectionModalClose = By.id("ISHEET_ADD_EDIT_SECTION_MODAL_MAIN_CLOSE_BUTTON");
	By addSectionModalOpened = By.xpath(".//*[@id='ISHEET_ADD_EDIT_SECTION_MODAL' and contains(@style,'display: block')]");
	By addSectionModalClosed = By.xpath(".//*[@id='ISHEET_ADD_EDIT_SECTION_MODAL' and contains(@style,'display: none')]");

	/** Sort Column */

	By iSheetSortColumnModal = By.id("ISHEET_SORT_COLUMN_MODAL");
	By iSheetSortColumnModalOpened = By.id(".//*[@id='ISHEET_SORT_COLUMN_MODAL' and @aria-hidden='false']");
	By iSheetSortColumnModalClosed = By.id(".//*[@id='ISHEET_SORT_COLUMN_MODAL' and @aria-hidden='true']");
	By iSheetSortColumnModalCancelBtn = By.id("ISHEET_SORT_COLUMN_MODAL_cancelLinkButton");
	By iSheetSortColumnModalApplyBtn = By.id("ISHEET_SORT_COLUMN_MODAL_applyButton");
	By iSheetSortColumnModalCloseBtn = By.id("ISHEET_SORT_COLUMN_MODAL_MAIN_CLOSE_BUTTON");
	By iSheetSortColumnModalSortedData = By.id("isheetSortedData");

	/** Sort Section */

	By locModalSortSections = By.id("ISHEET_SORT_SECTION_MODAL");
	By locBtnApplySortSection = By.id("ISHEET_SORT_SECTION_MODAL_applyButton");
	By locBtnCancelSortSection = By.id("ISHEET_SORT_SECTION_MODAL_cancelLinkButton");
	By locBtnCloseSortSection = By.id("ISHEET_SORT_SECTION_MODAL_MAIN_CLOSE_BUTTON");
	By locModalSortSectionsOpened = By.xpath(".//*[@id='ISHEET_SORT_SECTION_MODAL' and contains(@style,'display: block')]");
	By locModalSortSectionsClosed = By.xpath(".//*[@id='ISHEET_SORT_SECTION_MODAL' and contains(@style,'display: none')]");

	By rowCount = By.xpath(".//*[@id='fileData']/tbody/tr");
	By cancelOnDeleteModal = By.id("isheet_admin_sheet_column_delete_model_isheet_admin_sheet_column_cancel_btn");
	By deleteOnDeleteModal = By.id("isheet_admin_sheet_column_delete_model_isheet_admin_sheet_column_delete_btn");
	By columnErrorMsg = By.xpath(".//*[@id='columnListErrorMsgId_ErrorDiv' and (not(@style='display: none;'))]//*[@id='errorElementContainer']");
	By deleteWarningMsg = By.id("isheet_admin_sheet_column_delete_model_BODY");

	/** Manage Columns Home Page */

	@Override
	public AdminIsheetWeb manageColumnsClickBack()
	{
		WebElement manageColumnsBackEle = findClickableElement(manageColumnsBackBtn);
		manageColumnsBackEle.click();
		return new AdminIsheetWeb(driver);
	}

	@Override
	public AdminIsheetAddColumnWeb manageColumnsClickAddColumns()
	{
		if (verifyAddColumnButton())
		{
			WebElement manageColumnsAddColumnEle = findClickableElement(manageColumnsAddColumnBtn);
			manageColumnsAddColumnEle.click();
		}
		else
		{
			clickOnAddDropdown();
			WebElement addIsheetOptionEle = findVisibleElement(By.xpath("//*[@id='addColumnID']//*[@aria-expanded='true']//following::a[normalize-space(.)='Column']"));
			addIsheetOptionEle.click();
		}

		return new AdminIsheetAddColumnWeb(driver);
	}

	@Override
	public void manageColumnsClickSortColumns()
	{
		WebElement manageColumnsSortColumnEle = findClickableElement(manageColumnsSortColumnsBtn);
		manageColumnsSortColumnEle.click();
	}

	@Override
	public AdminIsheetEditColumnWeb clickOnColumn(String columnName)
	{
		WebElement isheetEle = findClickableElement(By.xpath(".//*[contains(@onclick,'editRow') and text()='" + columnName.trim() + "']"));
		isheetEle.click();

		return new AdminIsheetEditColumnWeb(driver);
	}

	@Override
	public void clickOnColumnMoreAction(String columnName)
	{
		WebElement moreActionsEle = findClickableElement(By.xpath("(.//*[text()='" + columnName.trim() + "']//following::*[@id='isheet_column_more_action'])[1]"));
		moreActionsEle.click();
	}

	@Override
	public void selectDeleteOnColumnMoreAction()
	{
		WebElement moreActionsDeleteEle = findClickableElement(moreActionsDeleteColumn);
		moreActionsDeleteEle.click();

	}

	@Override
	public AdminIsheetManageColumnPermissionsWeb selectEditPermissionsOnColumnMoreAction()
	{
		WebElement moreActionsDeleteEle = findClickableElement(moreActionsEditPermissionsColumn);
		moreActionsDeleteEle.click();
		return new AdminIsheetManageColumnPermissionsWeb(driver);
	}

	/** Add Section */

	@Override
	public void clickOnAddSectionBtn()
	{
		WebElement btnAddSection = findClickableElement(locBtnAddSection);
		btnAddSection.click();
	}

	@Override
	public void clickOnSection(String sectionName)
	{
		WebElement section = findClickableElement(By.xpath(".//*[contains(@onclick,'editSection')]//*[text()='" + sectionName.trim() + "']"));
		section.click();
	}

	@Override
	public void enterSectionName(String sectionName)
	{
		findVisibleElement(addSectionModalOpened, Speed.slow);
		WebElement inptSectionName = findClickableElement(locInptSectionName);
		inptSectionName.clear();
		inptSectionName.sendKeys(sectionName);
	}

	@Override
	public void enterSectionDescription(String sectionDescription)
	{
		findVisibleElement(addSectionModalOpened, Speed.slow);
		WebElement inptSectionDescription = findClickableElement(locInptSectionDescription);
		inptSectionDescription.clear();
		inptSectionDescription.sendKeys(sectionDescription);
	}

	@Override
	public void clickSaveOnAddSectionModal()
	{
		findVisibleElement(addSectionModalOpened, Speed.slow);
		WebElement btnSectionSave = findClickableElement(locBtnSectionSave);
		btnSectionSave.click();
		findPresentElement(addSectionModalClosed, Speed.slow);
	}

	@Override
	public void clickCancelOnAddSectionModal()
	{
		findVisibleElement(addSectionModalOpened, Speed.slow);
		WebElement btnSectionCancel = findClickableElement(locBtnSectionCancel);
		btnSectionCancel.click();
		findPresentElement(addSectionModalClosed, Speed.slow);
	}

	@Override
	public void clickCloseOnAddSectionModal()
	{
		findVisibleElement(addSectionModalOpened, Speed.slow);
		WebElement btnSectionModalClose = findClickableElement(locBtnSectionModalClose);
		btnSectionModalClose.click();
		findPresentElement(addSectionModalClosed, Speed.slow);
	}

	@Override
	public boolean verifyColumnExist(String columnName)
	{
		findVisibleElement(locTableIsheetColumns, Speed.slow);
		if (columnName.contains("'"))
		{
			return isDisplayed(By.xpath("//*[@id='fileData']//*[normalize-space(text())=" + "\"" + columnName.trim() + "\"" + "]"));
		}
		else
		{
			return isDisplayed(By.xpath("//*[@id='fileData']//*[normalize-space(text())='" + columnName.trim() + "']"));
		}
	}

	/** Sort Column */

	@Override
	public void dragAndDropIsheetColumns(String sourceColumnName, String destinationColumnName)
	{
		findVisibleElement(iSheetSortColumnModalOpened, Speed.slow, 5);
		WebElement sourceColumnEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_COLUMN_MODAL']//*[normalize-space(text())='" + sourceColumnName.trim() + "']"));
		WebElement destinationColumnEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_COLUMN_MODAL']//*[normalize-space(text())='" + destinationColumnName.trim() + "']"));
		sortItems(sourceColumnEle, destinationColumnEle);
	}

	@Override
	public void clickApplyOnSortIsheetColumnModal()
	{
		findVisibleElement(iSheetSortColumnModalOpened, Speed.slow, 5);
		WebElement sortColumnModalApplyEle = findClickableElement(iSheetSortColumnModalApplyBtn);
		sortColumnModalApplyEle.click();
	}

	@Override
	public void clickCancelOnSortIsheetColumnModal()
	{
		findVisibleElement(iSheetSortColumnModalOpened, Speed.slow, 5);
		WebElement sortColumnModalCancelEle = findClickableElement(iSheetSortColumnModalCancelBtn);
		sortColumnModalCancelEle.click();
	}

	@Override
	public void clickCloseOnSortIsheetColumnModal()
	{
		findVisibleElement(iSheetSortColumnModalOpened, Speed.slow, 5);
		WebElement sortColumnModalCloseEle = findClickableElement(iSheetSortColumnModalCloseBtn);
		sortColumnModalCloseEle.click();
	}

	/** Sort Section */

	@Override
	public void dragAndDropIsheetSections(String sourceSectionsName, String destinationSectionsName)
	{
		findVisibleElement(locModalSortSectionsOpened, Speed.slow, 5);
		WebElement sourceSectionEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_SECTION_MODAL']//*[normalize-space(text())='" + sourceSectionsName.trim() + "']"));
		WebElement destinationSectionEle = findClickableElement(By.xpath(".//*[@id='ISHEET_SORT_SECTION_MODAL']//*[normalize-space(text())='" + destinationSectionsName.trim() + "']"));
		sortItems(sourceSectionEle, destinationSectionEle);
	}

	@Override
	public void clickApplyOnSortIsheetSectionsModal()
	{
		findVisibleElement(locModalSortSectionsOpened, Speed.slow, 5);
		WebElement sortSectionsModalApplyEle = findClickableElement(locBtnApplySortSection);
		sortSectionsModalApplyEle.click();
	}

	@Override
	public void clickCancelOnSortIsheetSectionsModal()
	{
		findVisibleElement(locModalSortSectionsOpened, Speed.slow, 5);
		WebElement sortSectionsModalCancelEle = findClickableElement(locBtnCancelSortSection);
		sortSectionsModalCancelEle.click();
	}

	@Override
	public void clickCloseOnSortIsheetSectionsModal()
	{
		findVisibleElement(locModalSortSectionsOpened, Speed.slow, 5);
		WebElement sortSectionsModalCloseEle = findClickableElement(locBtnCloseSortSection);
		sortSectionsModalCloseEle.click();
	}

	@Override
	public void clickOnAddDropdown()
	{
		if (!isDisplayed(addDropdownOpen))
		{
			findVisibleElement(addButtonForDmdIsheet, Speed.slow).click();
		}
	}

	@Override
	public boolean verifyAddDropdown(String option)
	{
		return isDisplayed(By.xpath(".//*[@id='addColumnID']//*[@aria-expanded='true']//following::a[normalize-space(.)='" + option.trim() + "']"));
	}

	@Override
	public boolean verifyAddButton()
	{
		return isDisplayed(addButtonForDmdIsheet);
	}

	@Override
	public AdminIsheetWeb selectFromAddDropdown(String option)
	{
		clickOnAddDropdown();
		WebElement addIsheetOptionEle = findVisibleElement(By.xpath("//*[@id='addColumnID']//*[@aria-expanded='true']//following::a[normalize-space(.)='" + option.trim() + "']"));
		addIsheetOptionEle.click();

		switch (option.toLowerCase())
		{
			case "column":
				return new AdminIsheetAddColumnWeb(driver);

			case "import columns":
				return new AdminIsheetImportColumnWeb(driver);

			default:
				System.err.println();
				return this;
		}
	}

	/**
	 * This method is used to retain the groups added in iSheet
	 *
	 * @author janki.hirani
	 *         created date: 08/05/2018
	 * @return
	 */
	@Override
	public List<String> getAllSectionsName()
	{
		List<String> groupList = new ArrayList<>();
		String sectionName = "";
		int rows = driver.findElements(rowCount).size();
		for (int i = 1; i <= rows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='fileData']/tbody/tr[" + i + "]//*[contains(@onclick,'editSection')]/strong")))
			{
				sectionName = findVisibleElement(By.xpath(".//*[@id='fileData']//tr[" + i + "]//*[contains(@onclick,'editSection')]/strong")).getText();
				groupList.add(sectionName);
			}
		}
		return groupList;
	}

	/**
	 * This method is used to click on cancel button on delete column modal
	 *
	 * @author janki.hirani
	 *         created date: 22/05/2018
	 */
	@Override
	public void clickCancelOnDeleteColumnModal()
	{
		findClickableElement(cancelOnDeleteModal, Speed.slow).click();
	}

	/**
	 * This method is used to click on delete button on delete column modal
	 *
	 * @author janki.hirani
	 *         created date: 22/05/2018
	 */
	@Override
	public void clickDeleteOnDeleteColumnModal()
	{
		findClickableElement(deleteOnDeleteModal, Speed.slow).click();
	}

	/**
	 * This method is used to verify the warning message displayed in delete column modal
	 *
	 * @author janki.hirani
	 *         created date: 22/05/2018
	 * @param warningMsg
	 * @return
	 */
	@Override
	public boolean verifyWarningMsgOnDeleteColumnModal(String warningMsg)
	{
		WebElement msgBodyEle = findVisibleElement(deleteWarningMsg, Speed.slow);
		String warningMessage = msgBodyEle.getText();
		return warningMessage.equals(warningMsg);
	}

	/**
	 * This method is used to global error message for columns
	 *
	 * @author janki.hirani
	 *         created date: 22/05/2018
	 * @param errorMessage
	 * @return
	 */
	public boolean verifyGlobalMsgForColumns(String errorMessage)
	{
		if (isDisplayed(columnErrorMsg, Speed.slow))
		{
			String actualMsg = findVisibleElement(columnErrorMsg, Speed.slow).getText();
			return actualMsg.equals(errorMessage);
		}
		return false;
	}

	/**
	 * This method is used to verify that columns are editable or not
	 *
	 * @author janki.hirani
	 * @created on 28 May 2018
	 * @return
	 */
	public boolean verifyColumnsAreClickable(boolean clickable)
	{
		findVisibleElement(rowCount, Speed.slow);
		int rows = driver.findElements(rowCount).size();
		for (int i = 1; i <= rows; i++)
		{
			if (clickable && !isDisplayed(By.xpath(".//*[@id='fileData']/tbody/tr[" + i + "]/td[1]//a")))
			{
				return false;
			}
			else
			{
				if (isDisplayed(By.xpath(".//*[@id='fileData']/tbody/tr[" + i + "]/td[1]//a")))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to verify that "Add column" button is Displayed
	 *
	 * @author janki.hirani
	 * @created on 28 May 2018
	 * @return
	 */
	public boolean verifyAddColumnButton()
	{
		return isDisplayed(manageColumnsAddColumnBtn, Speed.slow);
	}
}
