package com.highq.pageobjects.base;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import com.highq.pageobjects.pages.AdminIsheetManageColumnWeb;

public interface AdminIsheetAddColumnPage extends BannerPage
{
	public void addColumnName(String columnName);

	public void selectColumnType(String columnType);

	public void selectSection(String section);

	public void addColumnDescription(String description);

	public void addColumnMaxCharacters(String maxChars);

	public void addColumnDefaultValue(String defaultValue);

	public void addColumnWidth(String columnWidth);

	public void addColumnNoOfLines(String noOfLines);

	public void clickAddChoice();

	public void clickRemoveChoice();

	public void addChoiceSelectDisplayMethod(String displayMethod);

	public void addChoiceIncludeOtherOption(String option);

	public void addColumnMinCharacters(String minChars);

	public void addColumnSelectDecimalPlaces(String decimalPlaces) throws InterruptedException;

	public void selectDateColumnFormat(String format);

	public void selectDateFormat(String dateFormat);

	public void setDefaultDateValue(String defaultDate) throws ParseException;

	public void setDefaultTime(String hours, String mins);

	public void addColumnSelectUserLookup(String lookupVal) throws InterruptedException;

	public void addColumnSelectUserLookupField(String lookupField) throws InterruptedException;

	public void addColumnSelectSheet(String site) throws InterruptedException;

	public void addLookupColumnSelectView(String view) throws InterruptedException;

	public void selectLookupColumn(List<String> columnName, boolean val);

	public void selectRestrictDelete(boolean val);

	public void addJoinColumnLinkName(String linkName);

	public void addJoinColumnDisplayView(String view) throws InterruptedException;

	public void selectCurrentSheetColumn(String columnName) throws InterruptedException;

	public void selectTargetSheetColumn(String columnName) throws InterruptedException;

	public void clickAddJoin();

	public void clickRemoveJoin();

	public boolean verifyCurrentSheetErrorMessage(String errorMessage);

	public boolean verifyTargetSheetErrorMessage(String errorMessage);

	public boolean verifySelectConditionErrorMessage(String errorMessage);

	public void selectJoinCondition(String currentSheetColumn, String targetSheetColumn, boolean val);

	public void addColumnSelectImageColumnType(String option);

	public void selectIsheetForIsheetLink(List<String> isheetName, boolean val);

	public void enterFormula(String formula, Object... columnNames) throws InterruptedException;

	public String getOperand(String columnName);

	public void clickOnValidateFormula();

	public void clickEvaluateOnValidateFormula();

	public void clickAgreeOnValidateFormula();

	public void clickCloseOnValidateFormula();

	public boolean checkFormula(String formula, LinkedHashMap<String, Object> values, String expectedAnswer) throws InterruptedException;

	public void enterStartValue(String startValue);

	public void enterPrefix(String prefix);

	public void enterSuffix(String suffix);

	public void enterMinLength(String minLength);

	public void selectAddColumnCheckBoxOptions(String option, boolean val);

	public void setColumnDisplaySetting(String option) throws InterruptedException;

	public void setColumnConditionType(String conditionType) throws InterruptedException;

	public void setColumnConditionFilterOperator(String filterOperator) throws InterruptedException;

	public void setColumnConditionFilterValue(String compareWith) throws InterruptedException;

	public void setColumnConditionFilterDate(Object date) throws InterruptedException;

	public void setColumnConditionFilterTime(Object time) throws InterruptedException;

	public void clickAddColumnCondition() throws InterruptedException;

	public void clickRemoveColumnCondition() throws InterruptedException;

	public AdminIsheetManageColumnWeb clickSaveOnAddColumn();

	public AdminIsheetManageColumnWeb clickCancelOnAddColumn();

	public void moveColumnFilterConditionUP() throws InterruptedException;

	public void moveColumnFilterConditionDown() throws InterruptedException;

	public void selectColumnFilterCondition(String conditionType, String column, String operator, String filterValue, boolean val) throws InterruptedException;

	public boolean verifyFilterValueMandatoryMessage(String message) throws InterruptedException;

	public boolean verifyGridErrorMessage(String message) throws InterruptedException;

	public void closeGridErrorMessage() throws InterruptedException;

	public void setColumnConditionFilterBy(String filterBy) throws InterruptedException;

	public void selectAllowPopulationTargetSheet(String targetSheet) throws InterruptedException;

	public void selectAllowPopulationTargetSheetView(String targetSheetView) throws InterruptedException;

	public void addChoice(String colorCode, String choice);

	public void enterMultipleChoiceForSameColor(String colorCode, String... choices);

	public void enterChoice(String colorCode, String choice);

	public void selectChoice(String colorCode);

	public boolean verifyImportCheckBoxDisplayed();

	public boolean verifyImportCheckBoxSelcted();

	public void clickOnImportExternalDataCheckbox(boolean val);

	public boolean verifyErrorMessageForSelectEngine(String msg);

	public void selectEnginesFromDropdown(String engines, boolean value);

	public void clickOnAnalysisEngineDropdown();

	public boolean verifyEnginesSelected(String engines);

	public void clickOnSelectButton();

	public boolean verifySelectFieldsModal();

	public void clickOnCancelSelectFieldModal();

	public void selectDeselectFieldsFromModal(String engine, String fields, boolean selection);

	public void clickOnSaveSelectFieldModal();

	public boolean verifyRemoveFieldTooltip();

	public boolean verifyCheckedFieldsOnModal(String field, String engine);

	public void removeField(String fields, String engine);

	public boolean verifyDelimiterFieldsOptions(List<String> delimiters);

	public boolean verifyDelimiterField();

	public boolean verifyImportDataFieldModal();

	public boolean verifyListedEnginesInDropDown(List<String> engineList);

	public boolean verifySelectedSourceFromDropdown(String source);

	public boolean vrifySourceProprtyDisplayedWithDropdown();

	public boolean verifyAnalysisEngineDisplayedWithEngineDropdown();

	public boolean verifyFieldsPropertyDisplayedWithSelectButton();

	public boolean verifyFieldsWithEngineInSelectModal(LinkedHashMap<String, String> values);

	public void enterValueInSearchBoxInSelectFieldModal(String fieldName);

	public boolean verifySearchedFieldIsDisplayed(String fieldName);

	public void clickOnFilterIconInSelectFieldModal();

	public void clearSearchBoxInSelectFieldModal();

	public void selectEngineFromFilter(List<String> engineList);

	public void clearFilterInSelectFieldsModal();

	public boolean verifyFilteredEnginesDisplayed(List<String> engineList);

	public boolean verifyClearFilterIsDisabledAndNoAnyEngineIsSelected();

	public boolean verifySaveButtonStatusInSelectFieldModal(boolean status);

	public boolean verifySelectedFieldsDisplayed(LinkedHashMap<String, String> values);

	public void selectOrDeselectAllFieldsFromSelectFieldModal(boolean select);

	public boolean verifyErrorMsg(String errorMsg);

	public boolean verifyFilterAndSearchBothApplied(String fieldName, List<String> engineList);

	public boolean verifyDefaultSelectedDelimiterInDropdown(String delimiterName);

	public void clickOnSorceDropDown();

	public boolean verifySourceFromDropdown(List<String> optionsList);

	public void addColorCode(String colorCode);

	public boolean selectDelimiterOptionsFromDropDown(String delimiterName);

	public void setAllowRichHTMLText(boolean state);

}
