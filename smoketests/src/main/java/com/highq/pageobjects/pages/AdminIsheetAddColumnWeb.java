package com.highq.pageobjects.pages;

import static org.testng.Assert.assertFalse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;

public class AdminIsheetAddColumnWeb extends AdminIsheetWeb implements AdminIsheetAddColumnPage
{

	public AdminIsheetAddColumnWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/** Add Columns */

	By addColumnName = By.id("columnName");
	By selectSection = By.id("sectionID");

	By locBtnColumnTypeDrpdown = By.xpath(".//*[@data-id='colType']");
	By locBtnSectionDrpdown = By.xpath(".//*[@data-id='sectionID']");
	By locDropDownComboBox = By.xpath("//*[@class='dropdown-menu inner' and @aria-expanded='true']");
	String locDropDownList = "//*[@class='dropdown-menu inner' and @aria-expanded='true']//li";

	// ================ Column Types STARTS ===================

	By addColumnDescription = By.id("description");
	By addColumnMaxCharacters = By.id("maxCharacters");
	By addColumnMinCharacters = By.id("minCharacters");
	By addColumnDecimalPlaces = By.id("decimalValue");
	By locBtnDecimalPlacesDrpDwn = By.xpath(".//*[@data-id='decimalValue']");
	By locBtnAllowPopulationTargetSheetDrpDwn = By.xpath(".//*[@data-id='allowLookupTargetSheetListID']");
	By locBtnAlloPopulationTargetSheetViewDrpDwn = By.xpath("allowLookupTargetSheetViewListID");
	By addColumnDefaultValue = By.id("defaultValue");
	By addColumnWidth = By.id("columnWidthValue");
	By addColumnNoOfLines = By.id("NoofLines");
	By addColumnAddChoiceBtn = By.id("addGrid");
	By addColumnRemoveChoiceBtn = By.id("removeGrid");

	By addColumnDateOnlyFormat = By.id("dateTime1");
	By addColumnDateTimeFormat = By.id("dateTime2");
	By addColumnUserLookup = By.id("isheetUserLookup");
	By locRdButtonDateFormat1 = By.id("dateFormat1");
	By locRdButtonDateFormat2 = By.id("dateFormat2");
	By locRdButtonDateFormat3 = By.id("dateFormat3");
	By locRdButtonDateFormat4 = By.id("dateFormat4");
	By locRdButtonDefaultDateValue = By.id("defaultDateValue3");
	By locInptDefaultDateValue = By.id("defaultSpecificDate");
	By locSelectDateHours = By.id("dateHours");
	By locSelectDateMins = By.id("dateMins");

	By locBtnSelectLookupDrpDwn = By.xpath(".//*[@data-id='isheetUserLookup']");
	By addColumnLookupField = By.id("isheetUserLookupFieldDisplay");
	By locBtnFieldDisplayDrpDwn = By.xpath("//*[@data-id='isheetUserLookupFieldDisplay']");
	By addColumnFormulaValidatedLink = By.id("validateFormulaTextID");
	By validateFormulaModalOpened = By.xpath(".//*[@id='FORMULA_MODAL' and @aria-hidden='false']");
	By validateFormulaModalClosed = By.xpath(".//*[@id='FORMULA_MODAL' and @aria-hidden='true']");
	By validateFormulaModalCloseBtn = By.id("FORMULA_MODAL_MAIN_CLOSE_BUTTON");
	By validateFormulaModalEvaluateBtn = By.id("FORMULA_MODAL_formulaModalEvaluate");
	By validateFormulaModalAgreeBtn = By.id("FORMULA_MODAL_formulaModalAgree");
	By validateFormulaModalErrorMessage = By.xpath(".//*[@class='alert alert-danger']");
	By formulaResult = By.xpath(".//*[normalize-space(.)='Formula result']//following::*[1]");
	By addColumnStartValue = By.id("startValue");
	By addColumnPreFix = By.id("prefix");
	By addColumnSuffix = By.id("sufix");
	By addColumnMinLength = By.id("autoIncrementLength");

	By isheetDropdown = By.id("targetSheetListID");
	By locBtnSheetDrpDwn = By.xpath(".//*[@data-id='targetSheetListID']");
	By isheetViewLookupDropdown = By.id("injectSheetView");
	By locBtnViewDrpDwn = By.xpath(".//*[@data-id='injectSheetView']");

	By addJoinColumnLinkName = By.id("linkNameID");
	By addJoinColumnDisplayViewDropdown = By.id("targetSheetViewListID");
	By locBtnDisplayViewDrpDwn = By.xpath(".//*[@data-id='targetSheetViewListID']");
	By joinConditionCurrentSheet = By.id("currentSheetColumnListID");
	By locBtnCurrentSheetDrpDwn = By.xpath(".//*[@data-id='currentSheetColumnListID']");
	By joinConditionTargetSheet = By.id("targetSheetColumnListID");
	By locBtnTargetSheetDrpDwn = By.xpath(".//*[@data-id='targetSheetColumnListID']");
	By addJoinBtn = By.id("addJoinColumn");
	By removeJoinBtn = By.id("removeJoinColumn");
	By selectCurrentSheetErrorMessage = By.id("currentSheetColumnListID_pID");
	By selectTargetSheetErrorMessage = By.id("targetSheetColumnListTDID_pID");
	By selectDataGridErrorMessage = By.id("filterDataGrid_view_pID");
	By joinConditionGrid = By.id("joingridboxvalue");

	// ================ Column Types ENDS ===================

	By optionMandatory = By.id("sheetColumn_SHEET_COL_PROP_TYPE_MANDATORY");
	By optionAllowSearch = By.id("allowSearch");
	By optionAddToDefaultView = By.id("defaultView");
	By optionAllowPopulation = By.id("sheetColumnPropAllowLookup");
	By columnConditionDisplayField = By.id("colCondition1");
	By columnConditionHideField = By.id("colCondition2");
	By columnConditionAnd = By.id("condition");
	By columnConditionOr = By.id("condition2");
	By filterByDropdown = By.id("fillterID");
	By locBtnFilterOption = By.xpath(".//*[@data-id='fillterID']");
	By filterOperatorDropDown = By.id("fillterOperatorID");
	By locBtnFilterOperator = By.xpath(".//*[@data-id='fillterOperatorID']");
	By filterValueInput = By.id("lookUpOtherValue");
	By filterValueDate = By.id("fillterDateId");
	By filterValueTime = By.id("isheetAdminTime");
	By addConditionAddBtn = By.id("addToGrid");
	By deleteConditionBtn = By.id("deleteFromGrid");
	By gridBox = By.id("gridbox");
	By moveFilterConditionRowUP = By.xpath(".//*[@id='filterDataGrid_view']//*[contains(@class,'icon-arrow-circle-up')]");
	By moveFilterConditionRowDown = By.xpath(".//*[@id='filterDataGrid_view']//*[contains(@class,'icon-arrow-circle-down')]");
	By addColumnBottomSaveBtn = By.id("isheetAddColumnBottomSave");
	By addColumnTopSaveBtn = By.id("isheetAddColumnTopSave");
	By addColumnBottomCancelBtn = By.id("isheetAddColumnBottomCancel");
	By addColumnTopCancelBtn = By.id("isheetAddColumnBottomCancel");
	By filterValueMandatoryMessageDisplayed = By.xpath(".//*[@id='lookUpOtherValue_pID' and contains(@style,'display: block')]");
	By addConditionGridErrorMessageDisplayed = By.xpath(".//*[@id='addColumnGridMsgID_ErrorDiv' and not(contains(@style,'display: none;'))]");
	By addConditionGridErrorMessageClose = By.xpath(".//*[@id='addColumnGridMsgID_ErrorDiv']//*[@id='isheet_module_add_item_closeGlobal_msg']");
	By addConditionGridErrorMessage = By.xpath(".//*[@id='addColumnGridMsgID_ErrorDiv']//*[@id='errorElementContainer']");
	By adminIsheetMiddlePanel = By.id("siteAdmin_module_mainMiddlePanelDivID");
	By manageColumnPage = By.id("addColumnID");
	By locLinkGlobalMessageClose = By.xpath(".//*[@id='addColumnMsgID_ErrorDiv']//*[@id='isheet_module_add_item_closeGlobal_msg']");

	/* Choice columnn objects */

	By choiceDescriptionBox = By.id("description");
	By firstChoiceColorPicker = By.xpath("(//*[@id='gridbox1']//*[@type='text'])[1]");
	By upArrowToMoveChoices = By.xpath("(//*[contains(normalize-space(.),'Enter choices')]//*[contains(@class,'icon-arrow-circle-up')])[1]");
	By downArrowToMoveChoices = By.xpath("(//*[contains(normalize-space(.),'Enter choices')]//*[contains(@class,'icon-arrow-circle-down')])[1]");

	/** Add/Edit Column */

	/* For Import External Data */

	By importExternatalDataCheckbox = By.id("sheetColumnImportAIData");
	By importExternalDatalabel = By.xpath(".//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_IMPORTDATAFROMEXTERNALSRC + "']");
	By selectButton = By.id("selectFieldsButton");
	By selectModal = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@class='modal-content']");
	By searchField = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='iSheetAdmin_searchEngine']");
	By selectmsg = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[contains(text() , '" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SELECTFIELDS + "')]");
	By filter = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@data-original-title='Filter']");
	By filterTextBox = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='iSheetAdmin_searchEngine']");
	By fieldscount = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='addiSheetCol_AIfieldList_modal_totalUserCount']");
	By saveButton = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_saveButton']");
	By cancelButton = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_closeButton']");
	By removeFieldTooltip = By.xpath(".//*[@id='addiSheetColumnSelectedFieldLsit']//a");
	By delimiterDisplayed = By.xpath(".//*[@id='delimiterListDivID' and (not(contains(@class,'hide')))]");
	By delimiterListDropdownOpened = By.xpath(".//*[@data-id='delimiterTypesID' and (@aria-expanded='true')]");
	By delimiterDropdown = By.xpath(".//*[@id='delimiterList']//*[@data-id='delimiterTypesID']");
	By crossSign = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_MAIN_CLOSE_BUTTON']");

	By selectEngineButton = By.xpath(".//*[@data-id='engineListDropDown' and @aria-expanded='true']");
	By engineDropdown = By.xpath(".//*[@data-id='engineListDropDown']");
	By sourceLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SOURCES + "']");
	By sourceDropdown = By.xpath(".//*[@data-id='sourceListDropDown']");
	By sourceOpen = By.xpath(".//*[@data-id='sourceListDropDown' and @aria-expanded='true']");
	By analysisEngineLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ANALYSISENGINE + "']");
	By totalFieldsRow = By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))]");
	By fieldsLabel = By.xpath("//*[@id='sourceDivID']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDS + "']");
	By totalSelectedFields = By.xpath(".//*[@id='selectedFieldListID']/*[@id='addiSheetColumnSelectedFieldList']");
	By selectAllFields = By.id("AIfieldList_selectAll_chkboxID");
	By saveButtonEnabled = By.xpath(".//*[@id='CREATE_FIELDS_MODAL_saveButton' and (not(@disabled='disabled'))]");
	By saveButtonDisabled = By.xpath(".//*[@id='CREATE_FIELDS_MODAL_saveButton' and @disabled='disabled']");
	By clearFilter = By.xpath("//*[@id='linkClearFilter' and (not(contains(@class,'textDecNone disabled')))]/*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_CLEARFILTERS + "']");
	By filterDropdownOpen = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@data-original-title='Filter' and @aria-expanded='true']");
	By engineListFilter = By.xpath(".//*[@id='engineListFilterDiv']/div");
	By filterByEngineOpened = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[contains(@class,'icon-chevron-down')]");
	By filterByEngineClosed = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='collapseOneArrow']");
	By importFromExternalSourceBlock = By.xpath(".//*[@id='sourceDivID' and @class='margTop10']");
	By globalErrorMsg = By.xpath(".//*[@id='addColumnMsgID_ErrorDiv' and (not(@style='display: none;'))]//*[@id='errorElementContainer']");
	By selectModalClosed = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade']");
	By selectModalOpened = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='addiSheetColumnTable']");

	@Override
	public void addColumnName(String columnName)
	{
		WebElement addColumnNameEle = findClickableElement(addColumnName);
		addColumnNameEle.clear();
		addColumnNameEle.sendKeys(columnName);
	}

	@Override
	public void selectColumnType(String columnType)
	{
		selectOptionFromDropDown(locBtnColumnTypeDrpdown, locDropDownComboBox, locDropDownList, columnType);
	}

	@Override
	public void selectSection(String section)
	{
		selectOptionFromDropDown(locBtnSectionDrpdown, locDropDownComboBox, locDropDownList, section);
	}

	// =============================================== Column Type Starts ======================================================

	// Single Line Text -

	@Override
	public void addColumnDescription(String description)
	{
		WebElement addColumnDescriptionEle = findPresentElement(addColumnDescription);
		scrollToElement(addColumnDescriptionEle);
		addColumnDescriptionEle.clear();
		addColumnDescriptionEle.sendKeys(description);
	}

	@Override
	public void addColumnMaxCharacters(String maxChars)
	{
		WebElement addColumnMaxCharactersEle = findPresentElement(addColumnMaxCharacters);
		scrollToElement(addColumnMaxCharactersEle);
		addColumnMaxCharactersEle.clear();
		addColumnMaxCharactersEle.sendKeys(maxChars);
	}

	@Override
	public void addColumnDefaultValue(String defaultValue)
	{
		WebElement addColumnDefaultValueEle = findPresentElement(addColumnDefaultValue);
		scrollToElement(addColumnDefaultValueEle);
		addColumnDefaultValueEle.clear();
		addColumnDefaultValueEle.sendKeys(defaultValue);
	}

	@Override
	public void addColumnWidth(String columnWidth)
	{
		WebElement addColumnWidthEle = findPresentElement(addColumnWidth);
		scrollToElement(addColumnWidthEle);
		addColumnWidthEle.clear();
		addColumnWidthEle.sendKeys(columnWidth);
	}

	@Override
	public void selectAllowPopulationTargetSheet(String targetSheet) throws InterruptedException
	{
		scrollToElement(locBtnAllowPopulationTargetSheetDrpDwn);
		selectOptionFromDropDown(locBtnAllowPopulationTargetSheetDrpDwn, locDropDownComboBox, locDropDownList, targetSheet);
	}

	@Override
	public void selectAllowPopulationTargetSheetView(String targetSheetView) throws InterruptedException
	{
		scrollToElement(locBtnAlloPopulationTargetSheetViewDrpDwn);
		selectOptionFromDropDown(locBtnAlloPopulationTargetSheetViewDrpDwn, locDropDownComboBox, locDropDownList, targetSheetView);
	}

	// Multiple Line Text

	@Override
	public void addColumnNoOfLines(String noOfLines)
	{
		WebElement addColumnNoOfLinesEle = findPresentElement(addColumnNoOfLines);
		scrollToElement(addColumnNoOfLinesEle);
		addColumnNoOfLinesEle.clear();
		addColumnNoOfLinesEle.sendKeys(noOfLines);
	}

	// Choice

	@Override
	public void clickAddChoice()
	{
		WebElement addEle = findClickableElement(addColumnAddChoiceBtn);
		addEle.click();
	}

	@Override
	public void clickRemoveChoice()
	{
		WebElement removeEle = findClickableElement(addColumnRemoveChoiceBtn);
		removeEle.click();
	}

	@Override
	public void addChoiceSelectDisplayMethod(String displayMethod)
	{
		selectRadioOption(displayMethod);
	}

	@Override
	public void addChoiceIncludeOtherOption(String option)
	{
		selectRadioOption(option);
	}

	// Number

	@Override
	public void addColumnMinCharacters(String minChars)
	{
		WebElement addColumnMinCharactersEle = findPresentElement(addColumnMinCharacters);
		scrollToElement(addColumnMinCharactersEle);
		addColumnMinCharactersEle.clear();
		addColumnMinCharactersEle.sendKeys(minChars);
	}

	@Override
	public void addColumnSelectDecimalPlaces(String decimalPlaces) throws InterruptedException
	{
		// scrollToElement(locBtnDecimalPlacesDrpDwn);
		String digit = decimalPlaces;
		Pattern pattern = Pattern.compile("[0-5]");
		if (pattern.matcher(digit).matches())
		{
			selectOptionFromDropDown(locBtnDecimalPlacesDrpDwn, locDropDownComboBox, locDropDownList, decimalPlaces);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}

	// Date & Time

	@Override
	public void selectDateColumnFormat(String format)
	{
		selectRadioOption(format);
	}

	@Override
	public void selectDateFormat(String dateFormat)
	{
		selectRadioOption(dateFormat);
	}

	@Override
	public void setDefaultDateValue(String defaultDate) throws ParseException
	{
		String date;
		String dateFormat;
		if (defaultDate.equals(AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NONE))
		{
			selectRadioOption(AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_NONE);
		}
		else if (defaultDate.equalsIgnoreCase(AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_TODAYSDATE))
		{
			selectRadioOption(defaultDate);
		}
		else
		{
			WebElement rdButtonDefaultDateValue = findClickableElement(locRdButtonDefaultDateValue, Speed.slow);
			rdButtonDefaultDateValue.click();

			if (findPresentElement(locRdButtonDateFormat1).isSelected())
			{
				// DD/MM/YYYY
				// formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY", Locale.ENGLISH);
				// date = LocalDate.parse(defaultDate, formatter);
				// System.out.println(date);

				dateFormat = "DD/MM/YYYY";
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				date = sdf.format(sdf.parse(defaultDate));

			}
			else if (findPresentElement(locRdButtonDateFormat2).isSelected())
			{
				// MM/DD/YYYY
				// formatter = DateTimeFormatter.ofPattern("MM/DD/YYYY", Locale.ENGLISH);
				// date = LocalDate.parse(defaultDate, formatter);
				// System.out.println(date);

				dateFormat = "MM/DD/YYYY";
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				date = sdf.format(sdf.parse(defaultDate));
			}
			else if (findPresentElement(locRdButtonDateFormat3).isSelected())
			{
				// DD.MM.YYYY
				// formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY", Locale.ENGLISH);
				// date = LocalDate.parse(defaultDate, formatter);
				// System.out.println(date);

				dateFormat = "DD.MM.YYYY";
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				date = sdf.format(sdf.parse(defaultDate));
			}
			else
			{
				// DD MMM YYYY
				// formatter = DateTimeFormatter.ofPattern("DD MMM YYYY");
				// date = LocalDate.parse(defaultDate, formatter);
				// System.out.println(date);

				dateFormat = "DD MMM YYYY";
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				date = sdf.format(sdf.parse(defaultDate));
			}

			WebElement inptDefaultDateValue = findClickableElement(locInptDefaultDateValue, Speed.slow);
			inptDefaultDateValue.sendKeys(date);
		}
	}

	@Override
	public void setDefaultTime(String hours, String mins)
	{
		Pattern pattern;
		if (findVisibleElement(addColumnDateTimeFormat).isSelected())
		{
			pattern = Pattern.compile("[00-23]");
			if (pattern.matcher(hours).matches())
			{
				Select selectDateHours = new Select(findClickableElement(locSelectDateHours, Speed.slow));
				if (!selectDateHours.getFirstSelectedOption().getText().equalsIgnoreCase(hours))
				{
					selectDateHours.selectByVisibleText(hours);
				}
			}
			else
			{
				assertFalse(true);
			}
			pattern = Pattern.compile("[00-59]");
			if (pattern.matcher(mins).matches())
			{
				Select selectDateMins = new Select(findClickableElement(locSelectDateMins, Speed.slow));
				if (!selectDateMins.getFirstSelectedOption().getText().equalsIgnoreCase(mins))
				{
					selectDateMins.selectByVisibleText(mins);
				}
			}
			else
			{
				assertFalse(true);
			}

		}
		else
		{
			assertFalse(true);
		}
	}

	// User Lookup

	@Override
	public void addColumnSelectUserLookup(String lookupVal) throws InterruptedException
	{
		scrollToElement(locBtnSelectLookupDrpDwn);
		selectOptionFromDropDown(locBtnSelectLookupDrpDwn, locDropDownComboBox, locDropDownList, lookupVal);
	}

	@Override
	public void addColumnSelectUserLookupField(String lookupField) throws InterruptedException
	{
		scrollToElement(locBtnFieldDisplayDrpDwn);
		selectOptionFromDropDown(locBtnFieldDisplayDrpDwn, locDropDownComboBox, locDropDownList, lookupField);
	}

	// lookup

	@Override
	public void addColumnSelectSheet(String site) throws InterruptedException
	{
		scrollToElement(locBtnSheetDrpDwn);
		selectOptionFromDropDown(locBtnSheetDrpDwn, locDropDownComboBox, locDropDownList, site);
	}

	@Override
	public void addLookupColumnSelectView(String view) throws InterruptedException
	{
		// scrollToElement(locBtnViewDrpDwn);
		selectOptionFromDropDown(locBtnViewDrpDwn, locDropDownComboBox, locDropDownList, view);
	}

	@Override
	public void selectLookupColumn(List<String> columnName, boolean val)
	{
		for (String column : columnName)
		{
			selectCheckBoxOption(column, val);
		}
	}

	@Override
	public void selectRestrictDelete(boolean val)
	{
		selectCheckBoxOption("Restrict Delete", val);
	}

	// Join

	@Override
	public void addJoinColumnLinkName(String linkName)
	{
		WebElement linkNameEle = findPresentElement(addJoinColumnLinkName);
		scrollToElement(linkNameEle);
		linkNameEle.clear();
		linkNameEle.sendKeys(linkName);
	}

	@Override
	public void addJoinColumnDisplayView(String view) throws InterruptedException
	{
		scrollToElement(locBtnDisplayViewDrpDwn);
		selectOptionFromDropDown(locBtnDisplayViewDrpDwn, locDropDownComboBox, locDropDownList, view);
	}

	@Override
	public void selectCurrentSheetColumn(String columnName) throws InterruptedException
	{
		scrollToElement(locBtnCurrentSheetDrpDwn);
		selectOptionFromDropDown(locBtnCurrentSheetDrpDwn, locDropDownComboBox, locDropDownList, columnName);
	}

	@Override
	public void selectTargetSheetColumn(String columnName) throws InterruptedException
	{
		scrollToElement(locBtnTargetSheetDrpDwn);
		selectOptionFromDropDown(locBtnTargetSheetDrpDwn, locDropDownComboBox, locDropDownList, columnName);
	}

	@Override
	public void clickAddJoin()
	{
		WebElement addEle = findPresentElement(addJoinBtn);
		scrollToElement(addEle);
		addEle.click();
	}

	@Override
	public void clickRemoveJoin()
	{
		WebElement removeEle = findPresentElement(removeJoinBtn);
		scrollToElement(removeEle);
		removeEle.click();
	}

	@Override
	public boolean verifyCurrentSheetErrorMessage(String errorMessage)
	{
		if (isDisplayed(selectCurrentSheetErrorMessage))
		{
			return findVisibleElement(selectCurrentSheetErrorMessage).getText().equalsIgnoreCase(errorMessage);
		}
		return false;
	}

	@Override
	public boolean verifyTargetSheetErrorMessage(String errorMessage)
	{
		if (isDisplayed(selectTargetSheetErrorMessage))
		{
			return findVisibleElement(selectTargetSheetErrorMessage).getText().equalsIgnoreCase(errorMessage);
		}
		return false;
	}

	@Override
	public boolean verifySelectConditionErrorMessage(String errorMessage)
	{
		if (isDisplayed(selectDataGridErrorMessage))
		{
			return findVisibleElement(selectDataGridErrorMessage).getText().equalsIgnoreCase(errorMessage);
		}
		return false;
	}

	@Override
	public void selectJoinCondition(String currentSheetColumn, String targetSheetColumn, boolean val)
	{
		setSelection(By.xpath("(.//*[@id='joingridboxvalue']//*[normalize-space()='" + currentSheetColumn.trim() + "']//following-sibling::*[normalize-space()='" + targetSheetColumn.trim() + "']//preceding-sibling::*[last()]//*)[1]"), val);
	}

	// Image

	@Override
	public void addColumnSelectImageColumnType(String option)
	{
		selectRadioOption(option);
	}

	// iSheet Link

	@Override
	public void selectIsheetForIsheetLink(List<String> isheetName, boolean val)
	{
		for (String iSheet : isheetName)
		{
			selectCheckBoxOption(iSheet, val);
		}

	}

	// Calculator

	@Override
	public void enterFormula(String formula, Object... columnNames) throws InterruptedException
	{
		// scrollToElement(addColumnDefaultValue);
		for (Object columnName : columnNames)
		{
			formula = formula.replace(columnName.toString(), getOperand(columnName.toString()));
		}
		addColumnDefaultValue(formula);
	}

	@Override
	public String getOperand(String columnName)
	{
		WebElement operandEle = findClickableElement(By.xpath("((.//*[text()='" + columnName.trim() + "']//parent::*)[1])//descendant::*[last()]"));
		return operandEle.getText();
	}

	@Override
	public void clickOnValidateFormula()
	{
		WebElement validateFormulaEle = findClickableElement(addColumnFormulaValidatedLink);
		validateFormulaEle.click();
		findPresentElement(validateFormulaModalOpened, Speed.slow);
	}

	@Override
	public void clickEvaluateOnValidateFormula()
	{
		findVisibleElement(validateFormulaModalOpened, Speed.slow, 10);
		WebElement evaluateEle = findClickableElement(validateFormulaModalEvaluateBtn);
		if (evaluateEle.isEnabled())
		{
			evaluateEle.click();
		}
	}

	@Override
	public void clickAgreeOnValidateFormula()
	{
		findVisibleElement(validateFormulaModalOpened, Speed.slow, 10);
		WebElement agreeEle = findClickableElement(validateFormulaModalAgreeBtn);
		if (agreeEle.isEnabled())
		{
			agreeEle.click();
		}
	}

	@Override
	public void clickCloseOnValidateFormula()
	{
		findVisibleElement(validateFormulaModalOpened, Speed.slow, 10);
		WebElement closeEle = findClickableElement(validateFormulaModalCloseBtn);
		if (closeEle.isEnabled())
		{
			closeEle.click();
		}
	}

	@Override
	public boolean checkFormula(String formula, LinkedHashMap<String, Object> values, String expectedAnswer) throws InterruptedException
	{
		enterFormula(formula, values.keySet().toArray());
		clickOnValidateFormula();
		for (Map.Entry<String, Object> value : values.entrySet())
		{
			// if (value.getValue() instanceof Integer || value.getValue() instanceof Float)
			// {
			WebElement valueEle = findClickableElement(By.xpath(".//*[@name='" + getOperand(value.getKey()).trim() + "']"));
			valueEle.sendKeys(value.getValue().toString());
			// }
		}
		clickEvaluateOnValidateFormula();
		if (isDisplayed(validateFormulaModalErrorMessage))
		{
			return isDisplayed(By.xpath(".//*[@class='errorMessage']//*[normalize-space(text())='" + expectedAnswer.trim() + "']"));
		}
		else if (isDisplayed(formulaResult))
		{
			WebElement formulaResultEle = findVisibleElement(formulaResult);
			if (formulaResultEle.getText().split("\\.")[0].equals(expectedAnswer))
			{
				clickAgreeOnValidateFormula();
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	// Auto Increment

	@Override
	public void enterStartValue(String startValue)
	{
		WebElement startValueEle = findPresentElement(addColumnStartValue);
		scrollToElement(startValueEle);
		startValueEle.clear();
		startValueEle.sendKeys(startValue);
	}

	@Override
	public void enterPrefix(String prefix)
	{
		WebElement prefixEle = findPresentElement(addColumnPreFix);
		scrollToElement(prefixEle);
		prefixEle.clear();
		prefixEle.sendKeys(prefix);
	}

	@Override
	public void enterSuffix(String suffix)
	{
		WebElement suffixEle = findPresentElement(addColumnSuffix);
		scrollToElement(suffixEle);
		suffixEle.clear();
		suffixEle.sendKeys(suffix);
	}

	@Override
	public void enterMinLength(String minLength)
	{
		WebElement minLengthEle = findPresentElement(addColumnMinLength);
		scrollToElement(minLengthEle);
		minLengthEle.clear();
		minLengthEle.sendKeys(minLength);
	}

	// =============================================== Column Type Ends ======================================================

	@Override
	public void selectAddColumnCheckBoxOptions(String option, boolean val)
	{
		selectCheckBoxOption(option, val);
	}

	@Override
	public void setColumnDisplaySetting(String option) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		switch (option.toLowerCase())
		{
			case "display field":
				WebElement columnConditionDisplayEle = findClickableElement(columnConditionDisplayField);
				columnConditionDisplayEle.click();
				break;
			case "hide field":
				WebElement columnConditionHideEle = findClickableElement(columnConditionHideField);
				columnConditionHideEle.click();
				break;
			default:
				System.err.println("Enter Valid Data");
				break;
		}
	}

	@Override
	public void setColumnConditionType(String conditionType) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		switch (conditionType.toLowerCase())
		{
			case "and":
				WebElement columnConditionAndEle = findClickableElement(columnConditionAnd);
				columnConditionAndEle.click();
				break;
			case "or":
				WebElement columnConditionOrEle = findClickableElement(columnConditionOr);
				columnConditionOrEle.click();
				break;
			default:
				System.err.println("Enetr Valid data");
				break;
		}
	}

	@Override
	public void setColumnConditionFilterBy(String filterBy) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		selectOptionFromDropDown(locBtnFilterOption, locDropDownComboBox, locDropDownList, filterBy);
	}

	@Override
	public void setColumnConditionFilterOperator(String filterOperator) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		selectOptionFromDropDown(locBtnFilterOperator, locDropDownComboBox, locDropDownList, filterOperator);
	}

	@Override
	public void setColumnConditionFilterValue(String compareWith) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(filterValueInput))
		{
			WebElement compareWithEle = findClickableElement(filterValueInput);
			compareWithEle.sendKeys(compareWith);
		}
		else
		{
			System.err.println("Select related column value");
			Assert.assertTrue(false);
		}
	}

	@Override
	public void setColumnConditionFilterDate(Object date) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(filterValueDate))
		{
			WebElement filterValueDateEle = findClickableElement(filterValueDate);
			filterValueDateEle.click();
			filterValueDateEle.clear();
			filterValueDateEle.sendKeys(date.toString());
		}
		else
		{
			System.err.println("Select related column value");
			Assert.assertTrue(false);
		}
	}

	@Override
	public void setColumnConditionFilterTime(Object time) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(filterValueTime))
		{
			WebElement filterValueDateEle = findClickableElement(filterValueTime);
			filterValueDateEle.click();
			filterValueDateEle.clear();
			filterValueDateEle.sendKeys(time.toString());
		}
		else
		{
			System.err.println("Select related column value");
			Assert.assertTrue(false);
		}
	}

	@Override
	public void clickAddColumnCondition() throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		WebElement addConditionAddEle = findClickableElement(addConditionAddBtn);
		addConditionAddEle.click();
	}

	@Override
	public void clickRemoveColumnCondition() throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		WebElement deleteConditionEle = findClickableElement(deleteConditionBtn);
		deleteConditionEle.click();
	}

	@Override
	public AdminIsheetManageColumnWeb clickSaveOnAddColumn()
	{
		WebElement saveEle = findVisibleElement(addColumnBottomSaveBtn, Speed.slow);
		saveEle.click();
		findVisibleElement(adminIsheetMiddlePanel, Speed.slow, 10);
		if (isDisplayed(globalErrorMsg, Speed.slow))
		{
			return null;
		}
		return new AdminIsheetManageColumnWeb(driver);
	}

	@Override
	public AdminIsheetManageColumnWeb clickCancelOnAddColumn()
	{
		WebElement cancelEle = findVisibleElement(addColumnBottomCancelBtn);
		cancelEle.click();
		findVisibleElement(manageColumnPage, Speed.slow, 10);
		if (isDisplayed(manageColumnPage))
		{
			return new AdminIsheetManageColumnWeb(driver);
		}
		assertFalse(true);
		return null;
	}

	@Override
	public void moveColumnFilterConditionUP() throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		WebElement moveRowUPEle = findClickableElement(moveFilterConditionRowUP);
		moveRowUPEle.click();
	}

	@Override
	public void moveColumnFilterConditionDown() throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		WebElement moveRowDownEle = findClickableElement(moveFilterConditionRowDown);
		moveRowDownEle.click();
	}

	@Override
	public void selectColumnFilterCondition(String conditionType, String column, String operator, String filterValue, boolean val) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (column != null && operator != null && filterValue != null)
		{
			if (conditionType != null)
			{
				setSelection(By.xpath("(.//*[normalize-space(.) = '" + conditionType.trim() + "']//following-sibling::*[normalize-space(.)='" + column.trim() + "']//following-sibling::*[normalize-space(.)='" + operator.trim() + "']//following-sibling::*[normalize-space(.)='" + filterValue.trim() + "']//preceding-sibling::*[last()]//*)[1]"), val);
			}
			else
			{
				setSelection(By.xpath(".//*[normalize-space(.)='" + column.trim() + "']//following-sibling::*[normalize-space(.)='" + operator.trim() + "']//following-sibling::*[normalize-space(.)='" + filterValue.trim() + "']//preceding-sibling::*[last()]//*"), val);
			}
		}
		else
		{
			System.err.println("Column or Operator or Filter Value can not be NULL");
		}
	}

	@Override
	public boolean verifyFilterValueMandatoryMessage(String message) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(filterValueMandatoryMessageDisplayed))
		{
			return findVisibleElement(filterValueMandatoryMessageDisplayed).getText().trim().equalsIgnoreCase(message);
		}
		return false;
	}

	@Override
	public boolean verifyGridErrorMessage(String message) throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(addConditionGridErrorMessageDisplayed))
		{
			return findVisibleElement(addConditionGridErrorMessage).getText().trim().equalsIgnoreCase(message);
		}
		return false;
	}

	@Override
	public void closeGridErrorMessage() throws InterruptedException
	{
		scrollToElement(columnConditionDisplayField);
		if (isDisplayed(addConditionGridErrorMessageDisplayed))
		{
			WebElement closeEle = findClickableElement(addConditionGridErrorMessageClose);
			closeEle.click();
		}
	}

	/**
	 * This method is used to add new choice with color code
	 *
	 * @param colorCode
	 *        uses color code to identify enter choice text box for that entry
	 * @param choice
	 *        Choice Value
	 * @author badal.gandhi
	 *         created on 16-02-2018
	 */
	@Override
	public void addChoice(String colorCode, String choice)
	{
		findVisibleElement(addColumnAddChoiceBtn).click();
		int size = driver.findElements(By.xpath("(//*[contains(@id,'gridbox')]//*[@type='text'])")).size();
		addColorCode(colorCode);
		By choiceTextBox = By.xpath("(.//*[contains(@id,'gridbox')]//*[@type='text'])[" + size + "]/ancestor::td/preceding-sibling::*[1]");
		findVisibleElement(choiceTextBox, Speed.slow).click();
		By choiceTextBoxInput = By.xpath("(.//*[contains(@id,'gridbox')]//*[@type='text'])[" + size + "]/ancestor::td/preceding-sibling::*[1]//input");

		WebElement ele = findVisibleElement(choiceTextBoxInput, Speed.slow);
		ele.clear();
		ele.sendKeys(choice);

	}

	/**
	 * @param colorCode
	 * @author krishna.bhadani
	 */
	public void addColorCode(String colorCode)
	{
		int size = driver.findElements(By.xpath("(//*[contains(@id,'gridbox')]//*[@type='text'])")).size();
		WebElement colorPicker = findVisibleElement(By.xpath("(//*[contains(@id,'gridbox')]//*[@type='text'])[" + size + "]"));
		colorPicker.clear();
		colorPicker.sendKeys(colorCode);
	}

	/**
	 * This method is used to set choice based on color code
	 *
	 * @author badal.gandhi
	 *         created on 16-02-2018
	 */
	@Override
	public void enterChoice(String colorCode, String choice)
	{
		By choiceId = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])[1]//preceding::*[@align='left' and @valign='middle'][1]");
		WebElement choiceTextBox = findVisibleElement(choiceId, Speed.slow);
		choiceTextBox.click();
		By inputXpath = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])[1]//preceding::*[@align='left' and @valign='middle'][1]/input");
		WebElement inputBox = findVisibleElement(inputXpath, Speed.slow);
		inputBox.clear();
		inputBox.sendKeys(choice.trim());
	}

	/**
	 * This method is used to set choice of multiple entry of same color
	 *
	 * @param colorCode
	 *        uses color code to identify enter choice text box for that entry
	 * @param choices
	 *        List of choices which user needs to set against respective entry
	 * @author badal.gandhi
	 *         created on 16-02-2018
	 */
	@Override
	public void enterMultipleChoiceForSameColor(String colorCode, String... choices)
	{
		// By choiceId = By.xpath("(//*[@type='text' and @value='"+colorCode.trim()+"' ])//preceding::*[@align='left' and @valign='middle'][1]");
		// List<WebElement> elements = driver.findElements(choiceId);
		for (int j = 0; j <= choices.length - 1; j++)
		{
			findVisibleElement(addColumnAddChoiceBtn).click();
			By choiceValues = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])[" + (j + 1) + "]//preceding::*[@align='left' and @valign='middle'][1]");
			WebElement choiceTextBox = findVisibleElement(choiceValues, Speed.slow);
			choiceTextBox.click();
			By inputXpath = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])[" + (j + 1) + "]//preceding::*[@align='left' and @valign='middle'][1]/input");
			WebElement inputBox = findVisibleElement(inputXpath, Speed.slow);
			inputBox.clear();
			inputBox.sendKeys(choices[j]);
		}
	}

	/**
	 * This method is used to select choice based on color code
	 *
	 * @param colorCode
	 *        uses color code to select choice entry
	 * @author badal.gandhi
	 *         created on 17-02-2018
	 */
	@Override
	public void selectChoice(String colorCode)
	{
		By choiceId = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])//preceding::img[1]");
		List<WebElement> elements = driver.findElements(choiceId);
		for (int j = 0; j < elements.size(); j++)
		{

			By choiceValues = By.xpath("(//*[@type='text' and @value='" + colorCode.trim() + "' ])[" + (j + 1) + "]//preceding::img[1]");
			WebElement choiceTextBox = findVisibleElement(choiceValues, Speed.slow);
			choiceTextBox.click();
		}
	}

	/**
	 * Method to verify Import Check-box Displayed
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyImportCheckBoxDisplayed()
	{
		return isDisplayed(importExternatalDataCheckbox, Speed.slow) && isDisplayed(importExternalDatalabel);
	}

	/**
	 * Method to verify Import Check-box selected
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyImportCheckBoxSelcted()
	{
		return findVisibleElement(importExternatalDataCheckbox, Speed.slow).isSelected();
	}

	/**
	 * Method to click on check-box of Import data from external source
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public void clickOnImportExternalDataCheckbox(boolean val)
	{
		setSelection(importExternatalDataCheckbox, val);
	}

	/**
	 * Method to verify error message
	 *
	 * @param msg
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyErrorMessageForSelectEngine(String msg)
	{
		return isDisplayed(By.xpath(".//*[@id='engineListTDID']//*[@id='engineListDropDown_pID' and @style='display: block;' and contains(text(),'" + msg.trim() + "')]")) &&
				isDisplayed(globalErrorMsg);
	}

	/**
	 * Method to select from source dropdown
	 */
	public void selectEnginesFromDropdown(String engines, boolean value)
	{
		if (value)
		{
			if (!verifyEnginesSelected(engines))
			{
				clickOnAnalysisEngineDropdown();
				findVisibleElement(By.xpath(".//*[@id='engineListTDID']//a[normalize-space(.)='" + engines.trim() + "']//following::span[1]")).click();
			}
		}
		else
		{
			if (verifyEnginesSelected(engines))
			{
				clickOnAnalysisEngineDropdown();
				findVisibleElement(By.xpath(".//*[@id='engineListTDID']//a[normalize-space(.)='" + engines.trim() + "']//following::span[1]")).click();
			}
		}
		findVisibleElement(engineDropdown).click();
	}

	/**
	 * This method used to open the dropdown menu if its not opened
	 */
	public void clickOnAnalysisEngineDropdown()
	{
		if (!isDisplayed(selectEngineButton))
		{
			findVisibleElement(engineDropdown).click();
		}
	}

	/**
	 * Method to verify engines selected or not
	 */
	public boolean verifyEnginesSelected(String commaSeparatedEnginesName)
	{
		return isDisplayed(By.xpath(".//*[@id='engineListTDID']//*[@data-id='engineListDropDown' and (contains(@title,'" + commaSeparatedEnginesName.trim() + "'))]"));
	}

	/**
	 * Method to click on Select Button
	 */
	public void clickOnSelectButton()
	{
		findVisibleElement(selectButton, Speed.slow).click();
		findPresentElement(selectModalOpened, 10);
	}

	/**
	 * Method to verify different fields from Modal
	 */
	public boolean verifySelectFieldsModal()
	{
		return isDisplayed(selectModal, searchField, selectmsg, filter, filterTextBox, cancelButton, saveButton, fieldscount, crossSign);
	}

	/**
	 * Method to click on cancel on select field modal
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public void clickOnCancelSelectFieldModal()
	{
		findVisibleElement(cancelButton, Speed.slow).click();
		findPresentElement(selectModalClosed, Speed.slow);
	}

	/**
	 * Method to select or deselect fields from Modal
	 *
	 * @param engines
	 * @param fields
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public void selectDeselectFieldsFromModal(String engine, String fields, boolean selection)
	{
		if (selection)
		{
			if (!findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + fields.trim() + "']//preceding::input[1]"), Speed.slow).isSelected())
			{
				findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + fields.trim() + "']//preceding::input[1]"), Speed.slow).click();
			}
		}
		else
		{
			if (findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + fields.trim() + "']//preceding::input[1]"), Speed.slow).isSelected())
			{
				findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + fields.trim() + "']//preceding::input[1]"), Speed.slow).click();
			}
		}
	}

	/**
	 * Method to click on save on select field modal
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public void clickOnSaveSelectFieldModal()
	{
		findVisibleElement(saveButton, Speed.slow).click();
	}

	/**
	 * Method to verify remove field tool-tip
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyRemoveFieldTooltip()
	{

		int totalFields = driver.findElements(totalSelectedFields).size();
		for (int i = 1; i < totalFields; i++)
		{
			if (!isDisplayed(By.xpath("//*[@id='addiSheetColumnSelectedFieldList'][" + i + "]//*[@data-original-title='Remove']")))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to remove fields from particular engine
	 *
	 * @param engines
	 * @param fields
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public void removeField(String fields, String engine)
	{

		WebElement element = findVisibleElement(By.xpath(".//*[@id='selectedFieldListID']//"
				+ "following::span[normalize-space(.)='" + fields.trim() + "']"
				+ "//following::span[1][normalize-space(.)='" + engine.trim() + "']//preceding::a[1]"));

		element.click();
	}

	/**
	 * Method to verify fields check-box checked or not
	 *
	 * @param field
	 * @param engine
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyCheckedFieldsOnModal(String field, String engine)
	{
		WebElement element = findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + field.trim() + "']//preceding::input[1]"), Speed.slow);
		scrollToElement(element);
		return element.isSelected();
	}

	/**
	 * Method to verify delimiter field options
	 *
	 * @param delimiter
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyDelimiterFieldsOptions(List<String> delimiters)
	{
		if (!isDisplayed(delimiterListDropdownOpened))
		{
			findVisibleElement(delimiterDropdown).click();
		}

		for (String delimiterName : delimiters)
		{
			if (!isDisplayed(By.xpath(".//*[@class='dropdown-menu open']//a[normalize-space(.)='" + delimiterName.trim() + "']")))
			{
				return false;
			}
		}
		findVisibleElement(delimiterDropdown).click();
		return true;
	}

	/**
	 * Method to verify delimiter field
	 *
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyDelimiterField()
	{
		return isDisplayed(delimiterDisplayed);
	}

	/**
	 * Method to verify engines selected or not
	 *
	 * @param engines
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	public boolean verifyImportDataFieldModal()
	{
		return isDisplayed(importFromExternalSourceBlock);
	}

	/**
	 * This verifyListedEnginesInDropDown() is used to verify listed engines in Analysis engines dropdown
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param engineList
	 * @return
	 */
	public boolean verifyListedEnginesInDropDown(List<String> engineList)
	{
		int count = 0;
		clickOnAnalysisEngineDropdown();
		for (String engineName : engineList)
		{
			if (isDisplayed(By.xpath(".//*[@id='engineListTDID']//a[normalize-space(.)='" + engineName.trim() + "']")))
			{
				count += 1;
			}
		}
		findVisibleElement(engineDropdown).click();
		return count == engineList.size();
	}

	/**
	 * Method to verify source from drop-down
	 *
	 * @param delimiter
	 * @author dhaval.modi
	 *         Created - 17 - 04 - 2018
	 *         Modified -
	 */
	@Override
	public boolean verifySelectedSourceFromDropdown(String source)
	{
		return isDisplayed(By.xpath(".//*[@data-id='sourceListDropDown' and @title='" + source.trim() + "']"));
	}

	/**
	 * This vrifySourceProprtyDisplayedWithDropdown() is used to verify that source property is Displayed
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @return
	 */
	public boolean vrifySourceProprtyDisplayedWithDropdown()
	{
		return isDisplayed(sourceLabel, sourceDropdown);
	}

	/**
	 * This method is used to verify Analysis Engine is Displayed with engine list drop down
	 *
	 * @author janki.hirani
	 * @created on 01 May 2018
	 * @return
	 */
	public boolean verifyAnalysisEngineDisplayedWithEngineDropdown()
	{
		return isDisplayed(analysisEngineLabel, engineDropdown);
	}

	/**
	 * This verifyFieldsPropertyDisplayedWithSelectButton() is used to verify that Fields property is displayed
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @return
	 */
	public boolean verifyFieldsPropertyDisplayedWithSelectButton()
	{
		return isDisplayed(fieldsLabel, selectButton);
	}

	/**
	 * This verifyFieldsWithEngineInSelectModal() is used to verify that all the fields of selected engine are displayed in select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param values
	 * @return
	 */
	public boolean verifyFieldsWithEngineInSelectModal(LinkedHashMap<String, String> values)
	{
		int count = 0;
		for (Map.Entry<String, String> value : values.entrySet())
		{
			findPresentElement(totalFieldsRow, Speed.slow);
			int totalFields = driver.findElements(totalFieldsRow).size();
			System.out.println("Total Fields:: " + totalFields);
			for (int i = 1; i <= totalFields; i++)
			{
				if (isDisplayed(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_field') and text()='" + value.getKey() + "']"), By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(@style='display: none;')][" + i + "]/td[contains(@id,'AIfieldList_engine') and text()='" + value.getValue() + "']")))
				{
					count += 1;
					break;
				}
			}

		}
		System.out.println("Count:: " + count + "Values:: " + values.size());
		return count == values.size();
	}

	/**
	 * This enterValueInSearchBoxInSelectFieldModfal() is used to enter value in Searchbox
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param fieldName
	 */
	public void enterValueInSearchBoxInSelectFieldModal(String fieldName)
	{
		findClickableElement(filterTextBox, 3).clear();
		WebElement textBoxEle = findClickableElement(filterTextBox);
		textBoxEle.clear();
		textBoxEle.sendKeys(fieldName);
	}

	/**
	 * This verifySearchedFieldIsDisplayed() is used to verify that search is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param fieldName
	 * @return
	 */
	public boolean verifySearchedFieldIsDisplayed(String fieldName)
	{
		int count = 0;
		findPresentElement(totalFieldsRow, Speed.slow);
		int totalFields = driver.findElements(totalFieldsRow).size();
		System.out.println("totalFields:: " + totalFields);
		for (int i = 1; i <= totalFields; i++)
		{
			System.out.println("Inside for loop........");
			if (isDisplayed(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_field') and (contains(text(),'" + fieldName.trim() + "'))]")))
			{
				System.out.println("Conditon became true......");
				count += 1;
			}
		}

		System.out.println("total count:::: " + count);
		return count == totalFields;
	}

	/**
	 * This clickOnFilterIconInSelectFieldModal() is used to click on filter icon
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 */
	public void clickOnFilterIconInSelectFieldModal()
	{
		if (!isDisplayed(filterDropdownOpen))
		{
			WebElement textBoxEle = findClickableElement(filter);
			textBoxEle.click();
		}
	}

	/**
	 * This clearSearchBoxInSelectFieldModal() is used to clear the test from Search textbox in Select field modal
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 */
	public void clearSearchBoxInSelectFieldModal()
	{
		findClickableElement(filterTextBox).clear();
	}

	/**
	 * this selectEngineFromFilter() is used to select filters from filter dropdown to apply filter
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param engineList
	 */
	public void selectEngineFromFilter(List<String> engineList)
	{
		if (!isDisplayed(filterByEngineOpened))
		{
			findVisibleElement(filterByEngineClosed).click();
		}

		for (String engine : engineList)
		{
			if (!findVisibleElement(By.xpath(".//*[@id='siteAdmin_userManage_systemGroup' and @value='" + engine.trim() + "']")).isSelected())
			{
				findVisibleElement(By.xpath(".//*[@id='siteAdmin_userManage_systemGroup' and @value='" + engine.trim() + "']")).click();
			}
		}
		findVisibleElement(filterByEngineOpened).click();
		findVisibleElement(filter).click();
	}

	/**
	 * This verifyFilteredEnginesDisplayed() is used to verify that filter on engines is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param engineList
	 * @return
	 */
	public boolean verifyFilteredEnginesDisplayed(List<String> engineList)
	{
		int count = 0;
		int totalFields = driver.findElements(totalFieldsRow).size();
		System.out.println("totalFields:: " + totalFields);
		for (String engineName : engineList)
		{
			for (int i = 1; i <= totalFields; i++)
			{
				if (isDisplayed(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_engine') and normalize-space(.)='" + engineName.trim() + "']")))
				{
					count += 1;
				}
			}
		}
		System.out.println("totalcount:: " + count);
		return count == totalFields;
	}

	/**
	 * This clearFilterInSelectFieldsModal() is used to click on clear filter text in filter
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 */
	public void clearFilterInSelectFieldsModal()
	{
		clickOnFilterIconInSelectFieldModal();

		if (isDisplayed(clearFilter))
		{
			findClickableElement(clearFilter).click();
		}
	}

	/**
	 * This verifyClearFilterIsDisabledAndNoAnyEngineIsSelected() is used to verify that clear filter is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @return
	 */
	public boolean verifyClearFilterIsDisabledAndNoAnyEngineIsSelected()
	{
		clickOnFilterIconInSelectFieldModal();

		if (!isDisplayed(filterByEngineOpened))
		{
			findVisibleElement(filterByEngineClosed).click();
		}

		int totalFields = driver.findElements(engineListFilter).size();
		int count = totalFields;
		for (int i = 1; i <= totalFields; i++)
		{
			if (findVisibleElement(By.xpath(".//*[@id='engineListFilterDiv']/div[" + i + "]//*[@id='siteAdmin_userManage_systemGroup']")).isSelected())
			{
				count -= 1;
			}
		}
		boolean status = isDisplayed(clearFilter);
		findVisibleElement(filterByEngineClosed).click();
		findVisibleElement(filter).click();
		return !status && count == totalFields;
	}

	/**
	 * This verifySaveButtonStatusInSelectFieldModal() is used to verify that the Save button is enabled or desabled
	 *
	 * @author janki.hirani
	 *         created date: 01/05/2018
	 * @param status
	 * @return
	 */
	public boolean verifySaveButtonStatusInSelectFieldModal(boolean status)
	{
		if (status)
		{
			return isDisplayed(saveButtonEnabled);
		}
		else
		{
			return isDisplayed(saveButtonDisabled);
		}
	}

	/**
	 * This verifySelectedFieldsDisplayed() is used to verify that all the selected fields from the Select field Modal are displayed after save in allow section disabled iSheet
	 *
	 * @author janki.hirani
	 *         created date: 02/05/2018
	 * @param values
	 * @return
	 */
	public boolean verifySelectedFieldsDisplayed(LinkedHashMap<String, String> values)
	{
		int count = 0;
		for (Map.Entry<String, String> value : values.entrySet())
		{

			int totalFields = driver.findElements(totalSelectedFields).size();
			for (int i = 1; i <= totalFields; i++)
			{
				if (isDisplayed(By.xpath("//*[@id='addiSheetColumnSelectedFieldList'][" + i + "]/*[text()='" + value.getKey() + "']"), By.xpath("//*[@id='addiSheetColumnSelectedFieldList'][" + i + "]/*[@id='engineName' and @name='" + value.getValue() + "']")))
				{
					count += 1;
					break;
				}
			}
		}
		System.out.println("Count:: " + count + "Values:: " + values.size());
		return count == values.size();
	}

	/**
	 * This selectOrDeselectAllFieldsFromSelectFieldModal() is used to click on select all checkbox in Select Field modal
	 *
	 * @author janki.hirani
	 *         created date: 02/05/2018
	 * @param select
	 */
	public void selectOrDeselectAllFieldsFromSelectFieldModal(boolean select)
	{
		findClickableElement(selectAllFields).click();
		boolean status = findVisibleElement(selectAllFields).isSelected();
		if (select != status)
		{
			findClickableElement(selectAllFields).click();
		}
	}

	/**
	 * This verifyFilterAndSearchBothApplied() is used to verify that both Searched text and Filtered Engine both displayed at a time
	 *
	 * @author janki.hirani
	 *         created date: 02/05/2018
	 * @param fieldName
	 * @param engineList
	 * @return
	 */
	public boolean verifyFilterAndSearchBothApplied(String fieldName, List<String> engineList)
	{
		int count = 0;
		findPresentElement(totalFieldsRow, Speed.slow);
		int totalFields = driver.findElements(totalFieldsRow).size();
		System.out.println("totalFields:: " + totalFields);
		for (String engineName : engineList)
		{
			for (int i = 1; i <= totalFields; i++)
			{
				if (isDisplayed(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_engine') and normalize-space(.)='" + engineName.trim() + "']"), By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_field') and contains(text(),'" + fieldName.trim() + "')]")))
				{
					count += 1;
				}
			}
		}
		System.out.println("total count:: " + count);
		return count == totalFields;
	}

	/**
	 * This method is used to verify global error message when engines are off from file
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param errorMsg
	 * @return
	 */
	public boolean verifyErrorMsg(String errorMsg)
	{
		return isDisplayed(By.xpath(".//*[@id='addColumnMsgID_ErrorDiv' and not(@style='display: none;')]//*[@id='errorElementContainer' and normalize-space()='" + errorMsg.trim() + "']"));
	}

	/**
	 * This verifyDefaultSelectedDelimiterInDropdown() is used to verify the default selected delimeter in Delimeter dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param delimiterName
	 * @return
	 */
	public boolean verifyDefaultSelectedDelimiterInDropdown(String delimiterName)
	{
		return isDisplayed(By.xpath(".//*[@data-id='delimiterTypesID' and @title='" + delimiterName.trim() + "']"));
	}

	/**
	 * This clickOnSorceDropDown() is used to open the Source Dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 */
	public void clickOnSorceDropDown()
	{
		if (!isDisplayed(sourceOpen))
		{
			findClickableElement(sourceDropdown).click();
		}
	}

	/**
	 * This method verifySourceFromDropdown() is used to verify available sorces from the Source dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param optionsList
	 * @return
	 */
	public boolean verifySourceFromDropdown(List<String> optionsList)
	{
		clickOnSorceDropDown();

		for (String sourceName : optionsList)
		{
			if (!isDisplayed(By.xpath(".//*[contains(@class,'open')]//a[normalize-space(.)='" + sourceName.trim() + "']")))
			{
				findClickableElement(sourceDropdown).click();
				return false;
			}
		}
		findClickableElement(sourceDropdown).click();
		return true;
	}

	/**
	 * This method is used to select given delimiter from delimiter drop down
	 *
	 * @author janki.hirani
	 *         created date: 17/05/2018
	 * @param delimiterName
	 * @return
	 */
	public boolean selectDelimiterOptionsFromDropDown(String delimiterName)
	{
		if (!isDisplayed(delimiterListDropdownOpened))
		{
			findVisibleElement(delimiterDropdown).click();
		}

		findClickableElement(By.xpath(".//*[@class='dropdown-menu open']//a[normalize-space(.)='" + delimiterName.trim() + "']")).click();
		findVisibleElement(delimiterDropdown).click();
		return true;
	}

	/**
	 * This method is used to enable disable rich html text for multiline column
	 *
	 * @author janki.hirani
	 *         created date: 18/05/2018
	 * @param state
	 */
	public void setAllowRichHTMLText(boolean state)
	{
		setSelection(By.id("richHtmlText"), state);
	}

}
