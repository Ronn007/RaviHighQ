package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import java.util.List;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;

public interface AdminIsheetImportColumnPage extends BannerPage
{
	public AdminIsheetManageColumnWeb clickSaveOnImportColumn();

	public void clickOnSorceDropDown();

	public boolean verifySourceFromDropdown(List<String> optionsList);

	public void clickOnAnalysisEngineDropdown();

	public boolean verifyListedEnginesInDropDown(List<String> engineList);

	public boolean verifyImportColumnsPage();

	public boolean verifyDefaultNothingSelectedInAnalysisEngine();

	public boolean verifyErrorMessageForSelectEngine(String msg);

	public boolean verifySelectedEngines(String commaSeparatedEnginesName);

	public void selectEnginesFromDropdown(String engines, boolean value);

	public void clickOnSelectButton();

	public boolean verifyFieldsWithEngineInSelectModal(LinkedHashMap<String, String> values);

	public boolean verifySaveButtonStatusInSelectFieldModal(boolean status);

	public boolean verifySelectFieldsModal();

	public void selectDeselectFieldsFromModal(String engine, String fields, boolean selection);

	public void clickOnSaveSelectFieldModal();

	public boolean verifySelectedFieldsDisplayed(LinkedHashMap<String, String> values);

	public boolean verifyCheckedFieldsOnModal(String field, String engine);

	public void selectOrDeselectAllFieldsFromSelectFieldModal(boolean select);

	public void clickOnCancelSelectFieldModal();

	public void selectEngineFromFilter(List<String> engineList);

	public boolean verifyFilteredEnginesDisplayed(List<String> engineList);

	public void clickOnFilterIconInSelectFieldModal();

	public void clearFilterInSelectFieldsModal();

	public boolean verifyClearFilterIsDisabledAndNoAnyEngineIsSelected();

	public void enterValueInSearchBoxInSelectFieldModal(String fieldName);

	public boolean verifySearchedFieldIsDisplayed(String fieldName);

	public boolean verifyFilterAndSearchBothApplied(String fieldName, List<String> engineList);

	public boolean verifyColumnsInFieldsAndColumnSetting(List<String> columns);

	public boolean verifyAllRowElementBelowColumnNameColumnIsEditable();

	public boolean verifyRemoveTooltopDisplayedForAllRows();

	public boolean verifyAvailableColumnTypesInDropdown(String fieldName, List<String> columnTypes);

	public boolean verifyDefaultSelectedColumnTypeInFieldsAndColumnSetting(String fieldName, String columnType);

	public boolean verifyAvailableSectionsInDropdown(String fieldName, List<String> sectionList);

	public boolean verifyDefaultColumnNameIsSameAsFieldName(String fieldName);

	public void removeSelectedFieldsFromFieldsAndColumnSetting(LinkedHashMap<String, String> values);

	public void renameColumnNameInFieldsAndColumnSettingsBlock(String oldColumnName, String engineName, String newColumnName);

	public boolean verifyErrorMessageForDuplicateColumnName();

	public void removeAllFields();

	public void selectSectionFromDropdownInFieldsAndColumnSettingBlock(String engineName, String fieldName, String sectionName);

	public boolean verifyErrorMessageForSectionRequired();

	public boolean verifyErrorMsgForSelectedMoreThan20Fields();

	public List<String> selectGivenNumberOfFieldsInSelectFieldModal(int numerOfFields);

	public void selectSectionForGivenNumberOfFields(int numberOfFields);

	public boolean verifyNoAnyEnginesAreDisplayed();

	public boolean verifyErrorMsg(String errorMsg);

	public AdminIsheetManageColumnWeb clickCancelOnImportColumn();

	public boolean vrifySourceProprtyDisplayedWithDropdown();

	public boolean verifyAnalysisEngineDisplayedWithEngineDropdown();

	public boolean verifyFieldsPropertyDisplayedWithSelectButton();

}
