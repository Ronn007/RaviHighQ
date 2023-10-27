package com.highq.pageobjects.base;

import java.util.List;
import com.highq.pageobjects.pages.AdminIsheetAddColumnWeb;
import com.highq.pageobjects.pages.AdminIsheetEditColumnWeb;
import com.highq.pageobjects.pages.AdminIsheetManageColumnPermissionsWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetManageColumnPage extends BannerPage
{
	public AdminIsheetWeb manageColumnsClickBack();

	public AdminIsheetAddColumnWeb manageColumnsClickAddColumns();

	public void manageColumnsClickSortColumns();

	public AdminIsheetEditColumnWeb clickOnColumn(String columnName);

	public void clickOnColumnMoreAction(String columnName);

	public void selectDeleteOnColumnMoreAction();

	public void clickOnAddSectionBtn();

	public void clickOnSection(String sectionName);

	public void enterSectionName(String sectionName);

	public void enterSectionDescription(String sectionDescription);

	public void clickSaveOnAddSectionModal();

	public void clickCancelOnAddSectionModal();

	public void clickCloseOnAddSectionModal();

	public boolean verifyColumnExist(String columnName);

	public void dragAndDropIsheetColumns(String sourceColumnName, String destinationColumnName);

	public void clickApplyOnSortIsheetColumnModal();

	public void clickCancelOnSortIsheetColumnModal();

	public void clickCloseOnSortIsheetColumnModal();

	public void dragAndDropIsheetSections(String sourceSectionsName, String destinationSectionsName);

	public void clickApplyOnSortIsheetSectionsModal();

	public void clickCancelOnSortIsheetSectionsModal();

	public void clickCloseOnSortIsheetSectionsModal();

	public void clickOnAddDropdown();

	public boolean verifyAddDropdown(String option);

	public boolean verifyAddButton();

	public AdminIsheetWeb selectFromAddDropdown(String option);

	public List<String> getAllSectionsName();

	public void clickCancelOnDeleteColumnModal();

	public void clickDeleteOnDeleteColumnModal();

	public boolean verifyWarningMsgOnDeleteColumnModal(String warningMsg);

	public boolean verifyGlobalMsgForColumns(String errorMessage);

	public boolean verifyColumnsAreClickable(boolean clickable);

	public boolean verifyAddColumnButton();

	public AdminIsheetManageColumnPermissionsWeb selectEditPermissionsOnColumnMoreAction();
}
