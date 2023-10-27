package com.highq.pageobjects.pages;

import static org.testng.Assert.assertFalse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.AdminIsheetAddColumnLabel;
import com.highq.labels.collaborate.AdminIsheetManageColumnsLabels;
import com.highq.pageobjects.base.AdminIsheetImportColumnPage;

public class AdminIsheetImportColumnWeb extends AdminIsheetWeb implements AdminIsheetImportColumnPage
{

	WebDriver selenium = driver;

	By selectEngineButton = By.xpath(".//*[@data-id='engineListDropDown' and @aria-expanded='true']");
	By selectSourceDropdown = By.xpath(".//*[@data-id='sourceListDropDown' and @aria-expanded='true']");
	By engineDropdown = By.xpath(".//*[@data-id='engineListDropDown']");
	By sourceDropdown = By.xpath(".//*[@data-id='sourceListDropDown']");
	By addColumnBottomSaveBtn = By.id("isheetAddColumnBottomSave");
	By manageColumnPage = By.id("addColumnID");
	By selectButton = By.id("selectFieldsButton");
	By totalFieldsRow = By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))]");
	By nothingSelected = By.xpath(".//*[@data-id='engineListDropDown' and @title='Nothing selected']");
	By saveButtonEnabled = By.xpath(".//*[@id='CREATE_FIELDS_MODAL_saveButton' and (not(@disabled='disabled'))]");
	By saveButtonDisabled = By.xpath(".//*[@id='CREATE_FIELDS_MODAL_saveButton' and @disabled='disabled']");
	By selectModal = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@class='modal-content']");
	By searchField = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='iSheetAdmin_searchEngine']");
	By selectmsg = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[contains(text() , '" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SELECTFIELDS + "')]");
	By filter = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@data-original-title='Filter']");
	By filterTextBox = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='iSheetAdmin_searchEngine']");
	By fieldscount = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='addiSheetCol_AIfieldList_modal_totalUserCount']");
	By saveButton = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_saveButton']");
	By cancelButton = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_closeButton']");
	By crossSign = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='CREATE_FIELDS_MODAL_MAIN_CLOSE_BUTTON']");
	By globalErrorMsg = By.xpath(".//*[@id='addColumnMsgID_ErrorDiv' and (not(@style='display: none;'))]");
	By totalSelectedFields = By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')]");
	By selectAllFields = By.id("AIfieldList_selectAll_chkboxID");
	By filterByEngineOpened = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[contains(@class,'icon-chevron-down')]");
	By filterByEngineClosed = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@id='collapseOneArrow']");
	By filterDropdownOpen = By.xpath(".//*[@id='CREATE_FIELDS_MODAL' and @class='modal fade in']//*[@data-original-title='Filter' and @aria-expanded='true']");
	By clearFilter = By.xpath("//*[@id='linkClearFilter' and (not(contains(@class,'textDecNone disabled')))]/*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_CLEARFILTERS + "']");
	By engineListFilter = By.xpath(".//*[@id='engineListFilterDiv']/div");
	By totalEnginesText = By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(@style='display: none;')]//*[contains(@id,'AIfieldList_engine')]");
	By totalColumns = By.xpath(".//*[@id='importColumnFieldListTable']//th");
	By columnNameXpath = By.xpath(".//*[@id='importColumnFieldListTable']//*[normalize-space(.)='Column name']");
	By totalRow = By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')]");
	By errorMsgFor20Character = By.xpath(".//*[@id='addColumnMsgID_ErrorDiv' and (not(@style='display: none;'))]//*[@id='errorElementContainer' and normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_IMPORTCOLUMN_ERRORMSG_MAXNOOFCOLUMNS + "']");
	By duplicateColumnNameError = By.xpath(".//*[contains(@id,'importColumnNameDivId') and text()='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_IMPORTCOLUMN_ERRORMSG_DUPLICATETITLE + "' and (not(@style='display: none;'))]");
	By sectionRequiredError = By.xpath(".//*[contains(@id,'importColumnSectionDivId') and text()='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_IMPORTCOLUMN_REQUIRED + "' and (not(@style='display: none;'))]");
	By adminIsheetMiddlePanel = By.id("siteAdmin_module_mainMiddlePanelDivID");
	By importColumnBottomCancelBtn = By.id("isheetImportColumnBottomCancel");
	By sourceLabel = By.xpath(".//*[@id='listColumn']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_SOURCES + "']");
	By analysisEngineLabel = By.xpath("//*[@id='listColumn']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_ANALYSISENGINE + "']");
	By fieldsLabel = By.xpath("//*[@id='listColumn']/*[@class='form-horizontal']//*[normalize-space(.)='" + AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDS + "']");

	public AdminIsheetImportColumnWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to click on save button in Import Column page
	 *
	 * @author janki.hirani
	 *         created date: 02/05/2018
	 * @return
	 */
	public AdminIsheetManageColumnWeb clickSaveOnImportColumn()
	{
		WebElement saveEle = findPresentElement(addColumnBottomSaveBtn);
		saveEle.click();
		findVisibleElement(adminIsheetMiddlePanel, Speed.slow, 10);
		if (isDisplayed(globalErrorMsg, Speed.slow))
		{
			return null;
		}
		return new AdminIsheetManageColumnWeb(driver);
	}

	/**
	 * This method is used to click on source dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 */
	public void clickOnSorceDropDown()
	{
		if (!isDisplayed(selectSourceDropdown))
		{
			findClickableElement(sourceDropdown).click();
		}
	}

	/**
	 * Method to verify source from drop-down
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 */
	public boolean verifySourceFromDropdown(List<String> optionsList)
	{
		clickOnSorceDropDown();

		for (String sourceName : optionsList)
		{
			if (!isDisplayed(By.xpath(".//*[@aria-expanded='true']//a[normalize-space(.)='" + sourceName.trim() + "']")))
			{
				findClickableElement(sourceDropdown).click();
				return false;
			}
		}
		findClickableElement(sourceDropdown).click();
		return true;
	}

	/**
	 * This method used to open the dropdown menu if its not opened
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 */
	public void clickOnAnalysisEngineDropdown()
	{
		if (!isDisplayed(selectEngineButton))
		{
			findVisibleElement(engineDropdown).click();
		}
	}

	/**
	 * This method is used to verify listed engines in Analysis engine drop down
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param engineList
	 * @return
	 */
	public boolean verifyListedEnginesInDropDown(List<String> engineList)
	{
		int count = 0;
		clickOnAnalysisEngineDropdown();
		for (String engineName : engineList)
		{
			if (isDisplayed(By.xpath(".//*[@id='engineListTDID']//*[@aria-expanded='true']//span[normalize-space(.)='" + engineName.trim() + "']"), By.xpath(".//*[@id='engineListTDID']//*[@aria-expanded='true']//span[normalize-space(.)='" + engineName.trim() + "']/following::span[1]")))
			{
				count += 1;
			}
		}
		findVisibleElement(engineDropdown).click();
		return count == engineList.size();
	}

	/**
	 * This method is used to verify Import columns page is loaded
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @return
	 */
	public boolean verifyImportColumnsPage()
	{
		return isDisplayed(By.xpath("//*[@id='siteAdmin_module_mainMiddlePanelDivID']//*[normalize-space()='" + AdminIsheetManageColumnsLabels.ISHEET_ADMIN_ADDCOLUMN_IMPORTCOLUMNS + "']"), Speed.slow);
	}

	/**
	 * This method is used to verify Nothing is selected in AnalysisEngine drop down
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @return
	 */
	public boolean verifyDefaultNothingSelectedInAnalysisEngine()
	{
		return isDisplayed(nothingSelected);
	}

	/**
	 * This method is used to verify the error message when clicked on select button without selecting any engine
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param msg
	 * @return
	 */
	public boolean verifyErrorMessageForSelectEngine(String msg)
	{
		return isDisplayed(By.xpath(".//*[@id='engineListTDID']//*[@id='engineListDropDown_pID' and @style='display: block;' and contains(text(),'" + msg.trim() + "')]")) &&
				isDisplayed(globalErrorMsg);
	}

	/**
	 * This method is used to verify that selected engines are displayed as selected in dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param commaSeparatedEnginesName
	 * @return
	 */
	public boolean verifySelectedEngines(String commaSeparatedEnginesName)
	{
		return isDisplayed(By.xpath(".//*[@id='engineListTDID']//button[@data-id='engineListDropDown' and (contains(@title,'" + commaSeparatedEnginesName + "'))]"));
	}

	/**
	 * This method is used to select engines from Analusis engines dropdown
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param engines
	 * @param value
	 */
	public void selectEnginesFromDropdown(String engines, boolean value)
	{
		if (value)
		{
			if (!verifySelectedEngines(engines))
			{
				clickOnAnalysisEngineDropdown();
				findVisibleElement(By.xpath(".//*[@id='engineListTDID']//a/span[normalize-space(.)='" + engines.trim() + "']//following::span[1]")).click();
			}
		}
		else
		{
			if (verifySelectedEngines(engines))
			{
				clickOnAnalysisEngineDropdown();
				findVisibleElement(By.xpath(".//*[@id='engineListTDID']//a/span[normalize-space(.)='" + engines.trim() + "']//following::span[1]")).click();
			}
		}
		findVisibleElement(engineDropdown).click();
	}

	/**
	 * This method is used to click on select button
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 */
	public void clickOnSelectButton()
	{
		WebElement selectBtn = findVisibleElement(selectButton, Speed.slow);
		selectBtn.click();
		findVisibleElement(selectModal, Speed.slow, 10);
	}

	/**
	 * This method is used to verify that all the fields are displayed with its engine in select field modal
	 *
	 * @author janki.hirani
	 *         created date: 03/05/2018
	 * @param values
	 * @return
	 */
	public boolean verifyFieldsWithEngineInSelectModal(LinkedHashMap<String, String> values)
	{
		int count = 0;
		for (Map.Entry<String, String> value : values.entrySet())
		{
			findPresentElement(totalFieldsRow, Speed.slow);
			int totalFields = selenium.findElements(totalFieldsRow).size();
			System.out.println(" Int:::: " + totalFields);
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
	 * This verifySaveButtonStatusInSelectFieldModal() is used to verify that the Save button is enabled or desabled
	 *
	 * @author janki.hirani
	 *         created date: 04/05/2018
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
	 * This method is used to verify all propeties in select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 04/05/2018
	 * @return
	 */
	public boolean verifySelectFieldsModal()
	{
		return isDisplayed(selectModal, searchField, selectmsg, filter, filterTextBox, cancelButton, saveButton, fieldscount, crossSign);
	}

	/**
	 * This method is used to select or deselect fields from select field modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param engine
	 * @param fields
	 * @param selection
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
	 * This method is used to click on save button in Select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 */
	public void clickOnSaveSelectFieldModal()
	{
		findVisibleElement(saveButton, Speed.slow).click();
	}

	/**
	 * This method is used to verify that all the selected fields from the Select field Modal are displayed after save in allow section enabled iSheet
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param values
	 * @return
	 */
	public boolean verifySelectedFieldsDisplayed(LinkedHashMap<String, String> values)
	{
		int count = 0;
		int totalFields = selenium.findElements(totalSelectedFields).size();
		for (int i = 1; i <= totalFields; i++)
		{
			for (Map.Entry<String, String> value : values.entrySet())
			{
				if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/td[text()='" + value.getKey() + "']"), By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[normalize-space(.)='" + value.getValue() + "']")))
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
	 * This method is used to verify selected fields in select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param field
	 * @param engine
	 * @return
	 */
	public boolean verifyCheckedFieldsOnModal(String field, String engine)
	{
		WebElement element = findVisibleElement(By.xpath(".//td[normalize-space(.)='" + engine.trim() + "']/preceding-sibling::td[normalize-space(.)='" + field.trim() + "']//preceding::input[1]"), Speed.slow);
		scrollToElement(element);
		return element.isSelected();
	}

	/**
	 * This method is used to click on select all checkbox in Select Field modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param select
	 */
	public void selectOrDeselectAllFieldsFromSelectFieldModal(boolean select)
	{
		findVisibleElement(selectAllFields, Speed.slow).click();
		boolean status = findVisibleElement(selectAllFields).isSelected();
		if (select != status)
		{
			findClickableElement(selectAllFields).click();
		}
	}

	/**
	 * This method is used to close the Select fields Modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 */
	public void clickOnCancelSelectFieldModal()
	{
		findVisibleElement(cancelButton, Speed.slow).click();
	}

	/**
	 * This method is used to apply filter on any engine
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
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
	 * This method is used to verify that filter on engine is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param engineList
	 * @return
	 */
	public boolean verifyFilteredEnginesDisplayed(List<String> engineList)
	{
		int count = 0;
		findPresentElement(totalFieldsRow, Speed.slow);
		int totalFields = selenium.findElements(totalFieldsRow).size();
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
		return count == totalFields;
	}

	/**
	 * This method is used to click on filter icon
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
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
	 * This method is used to clear engines filter in Select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
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
	 * This method is used to verify that clear filter is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @return
	 */
	public boolean verifyClearFilterIsDisabledAndNoAnyEngineIsSelected()
	{
		clickOnFilterIconInSelectFieldModal();

		if (!isDisplayed(filterByEngineOpened))
		{
			findVisibleElement(filterByEngineClosed).click();
		}
		int totalFields = selenium.findElements(engineListFilter).size();
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
	 * This method is used to enter value in Searchbox
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param fieldName
	 */
	public void enterValueInSearchBoxInSelectFieldModal(String fieldName)
	{
		findClickableElement(filterTextBox).clear();
		WebElement textBoxEle = findClickableElement(filterTextBox);
		textBoxEle.clear();
		textBoxEle.sendKeys(fieldName);
	}

	/**
	 * This method is used to verify that search is applied successfully
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param fieldName
	 * @return
	 */
	public boolean verifySearchedFieldIsDisplayed(String fieldName)
	{
		int count = 0;
		findPresentElement(totalFieldsRow, Speed.slow);
		int totalFields = selenium.findElements(totalFieldsRow).size();
		for (int i = 1; i <= totalFields; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]/td[contains(@id,'AIfieldList_field') and (contains(text(),'" + fieldName.trim() + "'))]")))
			{
				count += 1;
			}
		}
		return count == totalFields;
	}

	/**
	 * This method is used to verify that both Searched text and Filtered Engine both displayed at a time
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
		int totalFields = selenium.findElements(totalFieldsRow).size();
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
		System.out.println("Count:: " + count + "Values:: " + totalFields);
		return count == totalFields;
	}

	/**
	 * This method is used to verify that engines are listed in sorted order in Select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @return
	 */
	public boolean verifyEnginesInSortedOrderInSelectFieldsModal()// dev pending
	{
		List<String> obtainedList = new ArrayList<>();
		List<String> sortedList = new ArrayList<>();
		List<WebElement> elementList = driver.findElements(totalEnginesText);
		for (WebElement we : elementList)
		{
			obtainedList.add(we.getText());
		}
		sortedList = obtainedList;
		Collections.sort(sortedList);
		return sortedList.equals(obtainedList);
	}

	public boolean verifyFieldsInSortedOrderInSelectFieldsModal()// dev pendding
	{
		List<String> obtainedList = new ArrayList<>();
		List<String> sortedList = new ArrayList<>();
		List<WebElement> elementList = driver.findElements(totalEnginesText);
		for (WebElement we : elementList)
		{
			obtainedList.add(we.getText());
		}
		sortedList = obtainedList;
		Collections.sort(sortedList);
		return sortedList.equals(obtainedList);
	}

	/**
	 * This method is used to verify columns displayed after adding some fields
	 *
	 * @author janki.hirani
	 *         created date: 07/05/2018
	 * @param columns
	 * @return
	 */
	public boolean verifyColumnsInFieldsAndColumnSetting(List<String> columns)
	{
		List<String> columnList = new ArrayList<>();
		// String fields = AdminIsheetAddColumnLabel.SITEADMIN_MODULESETTINGS_ISHEETS_MANAGECOLUMNS_ADDCOLUMNS_FIELDS;
		for (String columnName : columns)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldListTable']//*[normalize-space(.)='" + columnName.trim() + "']"), Speed.slow))
			{
				columnList.add(columnName);
			}
		}
		System.out.println("Columns::::" + columns);
		System.out.println("ColumnList::::" + columnList);
		Collections.reverse(columns);
		Collections.reverse(columnList);
		System.out.println("Columns::::" + columns);
		System.out.println("ColumnList::::" + columnList);
		return columns.equals(columnList);
	}

	/**
	 * This method is used to verify that all the rows below ColumnName Column are editable in Fields and column settings block
	 *
	 * @author janki.hirani
	 *         created date: 08/05/2018
	 * @return
	 */
	public boolean verifyAllRowElementBelowColumnNameColumnIsEditable()
	{
		List<String> columnsList = new ArrayList<>();
		List<String> editedColumns = new ArrayList<>();
		String columnName = "";
		if (isDisplayed(columnNameXpath))
		{
			int totalRows = selenium.findElements(totalRow).size();
			for (int i = 1; i <= totalRows; i++)
			{
				columnName = findVisibleElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[contains(@id,'importColumnNameDivId')]/input")).getAttribute("value");
				editedColumns.add(columnName);
			}

			columnsList.clear();
			Collections.reverse(editedColumns);
			for (int i = 1; i <= totalRows; i++)
			{
				findClickableElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[1][contains(@id,'importColumnNameDivId')]/input")).clear();
				findClickableElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[1][contains(@id,'importColumnNameDivId')]/input")).sendKeys(editedColumns.get(i - 1).trim());
				columnName = findVisibleElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[contains(@id,'importColumnNameDivId')]/input")).getAttribute("value");
				columnsList.add(columnName);
			}
		}
		return columnsList.equals(editedColumns);
	}

	/**
	 * This method is used to verify cross sign displayed for all added fields
	 *
	 * @author janki.hirani
	 *         created date: 08/05/2018
	 * @return
	 */
	public boolean verifyRemoveTooltopDisplayedForAllRows()
	{
		int count = 0;
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']//*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-original-title='Remove']")))
			{
				count += 1;
			}
		}
		return count == totalRows;
	}

	/**
	 * This method is used to verify columns in columnType dropdown in Fields and column settings block
	 *
	 * @author janki.hirani
	 *         created date: 08/05/2018
	 * @param fieldName
	 * @param columnTypes
	 * @return
	 */
	public boolean verifyAvailableColumnTypesInDropdown(String fieldName, List<String> columnTypes)
	{
		int count = 0;
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + fieldName.trim() + "']")))
			{
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='columnTypesListDropDown']")).click();
				for (String columnType : columnTypes)
				{
					if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[" + i + "]//*[@id='columnTypesListDropDown']/*[normalize-space()='" + columnType.trim() + "']")))
					{
						count += 1;
					}
				}
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='columnTypesListDropDown']")).click();
				break;
			}
		}
		return count == columnTypes.size();
	}

	/**
	 * This method is used to verify default selected column type in dropdown in fields and column setting block
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param fieldName
	 * @param columnType
	 * @return
	 */
	public boolean verifyDefaultSelectedColumnTypeInFieldsAndColumnSetting(String fieldName, String columnType)
	{
		boolean displayed = false;
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + fieldName.trim() + "']")))
			{
				displayed = isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='columnTypesListDropDown' and @title='" + columnType.trim() + "']"));
			}
		}
		return displayed;
	}

	/**
	 * This method is used to verify available section in dropdown in fields and column setting block
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param fieldName
	 * @param sectionList
	 * @return
	 */
	public boolean verifyAvailableSectionsInDropdown(String fieldName, List<String> sectionList)
	{
		int count = 0;
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + fieldName.trim() + "']")))
			{
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='sectionListDropDown']")).click();
				for (String section : sectionList)
				{
					if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[" + i + "]//*[@id='sectionListDropDown']/*[normalize-space()='" + section.trim() + "']")))
					{
						count += 1;
					}
				}
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='sectionListDropDown']")).click();
				break;
			}
		}
		return count == sectionList.size();
	}

	/**
	 * This method is used to verify default column name is same as its field name
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param fieldName
	 * @return
	 */
	public boolean verifyDefaultColumnNameIsSameAsFieldName(String fieldName)
	{
		String columnName;
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + fieldName.trim() + "']")))
			{
				columnName = findVisibleElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[contains(@id,'importColumnNameDivId')]/input")).getAttribute("value");
				break;
			}
		}
		return fieldName.equals(fieldName);
	}

	/**
	 * This method is used to remove a field row from fields and column block
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param fieldsList
	 */
	public void removeSelectedFieldsFromFieldsAndColumnSetting(LinkedHashMap<String, String> values)
	{
		int totalFields = selenium.findElements(totalSelectedFields).size();
		for (int i = 1; i <= totalFields; i++)
		{
			for (Map.Entry<String, String> value : values.entrySet())
			{
				if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + value.getKey() + "']"), By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[normalize-space(.)='" + value.getValue() + "']")))
				{
					findVisibleElement(By.xpath(".//*[@id='importColumnFieldList']//*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-original-title='Remove']")).click();
				}
			}
		}
	}

	/**
	 * This method is used to rename the column name
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param oldColumnName
	 * @param fieldName
	 * @param newColumnName
	 */
	public void renameColumnNameInFieldsAndColumnSettingsBlock(String oldColumnName, String engineName, String newColumnName)
	{
		int totalFields = selenium.findElements(totalSelectedFields).size();
		for (int i = 1; i <= totalFields; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[normalize-space(.)='" + engineName.trim() + "']"), By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[contains(@id,'importColumnNameDivId')]/*[@value='" + oldColumnName.trim() + "']")))
			{
				WebElement columnName = findClickableElement(By.xpath(".//*[contains(@id,'AIfieldList_select')][" + i + "]//*[1][contains(@id,'importColumnNameDivId')]/input"));
				columnName.clear();
				columnName.sendKeys(newColumnName.trim());
				break;
			}
		}
	}

	/**
	 * This method is used to verify error message for duplicate column name
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 * @param msg
	 * @return
	 */
	public boolean verifyErrorMessageForDuplicateColumnName()
	{
		return isDisplayed(duplicateColumnNameError, globalErrorMsg);
	}

	/**
	 * This method is used to remove all the selected fields from fields and column settings
	 *
	 * @author janki.hirani
	 *         created date: 09/05/2018
	 */
	public void removeAllFields()
	{
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = totalRows; i >= 1; i--)
		{
			findClickableElement(By.xpath(".//*[@id='importColumnFieldList']//*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-original-title='Remove']")).click();
		}
	}

	/**
	 * This method is used to select section for particular column in fields and column settings block
	 *
	 * @author janki.hirani
	 *         created date: 14/05/2018
	 * @param engineName
	 * @param fieldName
	 * @param sectionName
	 */
	public void selectSectionFromDropdownInFieldsAndColumnSettingBlock(String engineName, String fieldName, String sectionName)
	{
		int totalRows = selenium.findElements(totalRow).size();
		for (int i = 1; i <= totalRows; i++)
		{
			if (isDisplayed(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]/*[text()='" + fieldName.trim() + "']"), By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[normalize-space(.)='" + engineName.trim() + "']")))
			{
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='sectionListDropDown']")).click();
				findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[" + i + "]//*[@id='sectionListDropDown']/*[normalize-space()='" + sectionName.trim() + "']")).click();
			}
		}
	}

	/**
	 * This method is used to verify error message when no any section selected
	 *
	 * @author janki.hirani
	 *         created date: 14/05/2018
	 * @return
	 */
	public boolean verifyErrorMessageForSectionRequired()
	{
		return isDisplayed(sectionRequiredError, globalErrorMsg);
	}

	/**
	 * This method is used to verify global error message when more than 20 fields tried to be added
	 *
	 * @author janki.hirani
	 *         created date: 14/05/2018
	 * @return
	 */
	public boolean verifyErrorMsgForSelectedMoreThan20Fields()
	{
		return isDisplayed(errorMsgFor20Character);
	}

	/**
	 * This method is used to select given nuber of fields from select fields modal
	 *
	 * @author janki.hirani
	 *         created date: 15/05/2018
	 * @param numerOfFields
	 */
	public List<String> selectGivenNumberOfFieldsInSelectFieldModal(int numberOfFields)
	{
		List<String> columnsList = new ArrayList<>();
		for (int i = 1; i <= numberOfFields; i++)
		{
			findClickableElement(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]//*[contains(@id,'AIfieldList_select')]")).click();
			columnsList.add(findVisibleElement(By.xpath(".//*[@id='addiSheetColumnTable']/tbody/tr[not(contains(@style,'display: none;'))][" + i + "]//*[contains(@id,'AIfieldList_field')]")).getText());
		}
		return columnsList;
	}

	/**
	 * This method is used to select section from dropdown for given number of fields in fields and column settings block
	 *
	 * @author janki.hirani
	 *         created date: 15/05/2018
	 * @param numberOfFields
	 */
	public void selectSectionForGivenNumberOfFields(int numberOfFields)
	{
		for (int i = 1; i <= numberOfFields; i++)
		{
			Select importIsheetTemplateEle = new Select(findPresentElement(By.xpath(".//*[@id='importColumnFieldList']/*[" + i + "]//*[@id='sectionListDropDown']")));
			importIsheetTemplateEle.selectByIndex(1);
			// findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[contains(@id,'AIfieldList_select')][" + i + "]//*[@data-id='sectionListDropDown']")).click();
			// findClickableElement(By.xpath(".//*[@id='importColumnFieldList']/*[" + i + "]//*[@id='sectionListDropDown']/*[2]")).click();
		}
	}

	/**
	 * This method is used to verify that no any fields displayed after applied invalid search
	 *
	 * @author janki.hirani
	 *         created date: 16/05/2018
	 * @return
	 */
	public boolean verifyNoAnyEnginesAreDisplayed()
	{
		return !(isDisplayed(totalFieldsRow));
	}

	/**
	 * This method is used to verify global error message when engines are off from file
	 *
	 * @author janki.hirani
	 *         created date: 16/05/2018
	 * @return
	 */
	public boolean verifyErrorMsg(String errorMsg)
	{
		return isDisplayed(By.xpath(".//*[@id='addColumnMsgID_ErrorDiv' and not(@style='display: none;')]//*[@id='errorElementContainer' and normalize-space()='" + errorMsg.trim() + "']"));
	}

	/**
	 * This method is used to click on cancel button
	 *
	 * @author janki.hirani
	 * @created on 01 June 2018
	 * @return
	 */
	public AdminIsheetManageColumnWeb clickCancelOnImportColumn()
	{
		WebElement cancelEle = findVisibleElement(importColumnBottomCancelBtn);
		cancelEle.click();
		findVisibleElement(manageColumnPage, Speed.slow, 10);
		if (isDisplayed(manageColumnPage))
		{
			return new AdminIsheetManageColumnWeb(driver);
		}
		assertFalse(true);
		return null;
	}

	/**
	 * This vrifySourceProprtyDisplayedWithDropdown() is used to verify that source property is Displayed
	 *
	 * @author janki.hirani
	 * @created on 01 June 2018
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
	 * @created on 01 June 2018
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
	 * @created on 01 June 2018
	 * @return
	 */
	public boolean verifyFieldsPropertyDisplayedWithSelectButton()
	{
		return isDisplayed(fieldsLabel, selectButton);
	}
}
