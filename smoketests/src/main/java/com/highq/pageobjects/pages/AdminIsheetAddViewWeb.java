package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetAddViewPage;

public class AdminIsheetAddViewWeb extends AdminIsheetManageViewsWeb implements AdminIsheetAddViewPage
{

	public AdminIsheetAddViewWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	AdminIsheetAddColumnPage adminIsheetAddColumnPage;

	By locInptViewName = By.id("viewTitle");
	By locDrpdwnAvailableColumns = By.id("leftSidelistOfColumns");
	By locDrpdwnSelectedColumns = By.id("rightSidelistOfSortedColumns");
	By locSpnRightArrowMoveAll = By.xpath(".//*[@data-original-title = 'Move all to right']//*[contains(@class,'arrowFoward')]");
	By locSpnRightArrowMoveSelected = By.xpath(".//*[@data-original-title = 'Move right']");
	By locSpanLeftArrowMoveSelected = By.xpath(".//*[@data-original-title = 'Move left']");
	By locSpnLeftArrowMoveAll = By.xpath(".//*[@data-original-title = 'Move all to left']");
	By locSpnMoveColumnsUp = By.xpath(".//*[@class='sortSelectUp']//*[contains(@class,'icon-arrow-circle-up')]");
	By locSpnMoveColumnDown = By.xpath(".//*[contains(@class,'sortSelectDown')]//*[contains(@class,'icon-arrow-circle-down')]");
	By locDrpdwnFirstSortByColumn = By.id("firstSortedColumnId");
	By locDrpdwnSecondSortByColumn = By.id("secondSortedColumnId");
	By locBtnAddViewCancelTop = By.id("isheetViewCancel");
	By locBtnAddViewSaveTop = By.id("isheetViewTopSave");
	By locBtnAddViewSaveBottom = By.id("isheetViewBottomSave");
	By locBtnAddViewCancelBottom = By.xpath(".//*[@id='isheetViewBottomSave']//preceding::*[text()='Cancel'][1]");
	By locBtnAddFilter = By.linkText("Add");
	By locBtnRemoveFilter = By.linkText("Remove");
	By locBtnFilterValue = By.id("btnDropDown");
	By locBtnFilterValueOpened = By.xpath(".//*[@id='btnDropDown' and @aria-expanded='true']");
	By locRdBtnFilterValueMe = By.id("lookUpOptionId");
	By locRdBtnFilterValueOther = By.id("lookUpUserIDOptionId");
	By locInptFilterValueOther = By.id("lookUpOtherValue");
	By locInptSearchFilterValue = By.id("isheet_module_filterUserSearchButtonID");
	By locInptFilterValue = By.id("mainFillterID");
	By locSpnMoveViewFilterConditionUp = By.xpath(".//*[@id='filterDataGrid']//following::*[contains(@class,'icon-arrow-circle-up')]");
	By locSpnMoveViewFilterConditionDown = By.xpath(".//*[@id='filterDataGrid']//following::*[contains(@class,'icon-arrow-circle-down')]");
	By locDrpdwnSelectDateFilter = By.id("dateFilter");
	By locDrpdwnSelectDateSign = By.id("dateSignId");
	By locInptDateDays = By.id("dateDayID");

	@Override
	public void enterViewName(String viewName)
	{
		WebElement inptViewName = findClickableElement(locInptViewName);
		inptViewName.clear();
		inptViewName.sendKeys(viewName);
	}

	/**
	 * Select available columns
	 *
	 * @param columnNames
	 *        column names(Variable argument)
	 *        Updated: 16 March 2018(dheeraj.rajput)
	 */
	@Override
	public void selectAvailableColumns(String... columnNames)
	{
		findVisibleElement(locDrpdwnAvailableColumns, Speed.slow);
		for (int i = 0; i < columnNames.length; i++)
		{
			By optionXpath = By.xpath(".//*[@id='leftSidelistOfColumns']//*[normalize-space(text())='" + columnNames[i].trim() + "']");
			if (isDisplayed(optionXpath))
			{
				findVisibleElement(locDrpdwnSelectedColumns).sendKeys(Keys.CONTROL);
				findVisibleElement(optionXpath).click();
			}
		}
	}

	@Override
	public void selectSelectedColumns(String... columnNames)
	{
		Select drpdwnSelectedColumns = new Select(findClickableElement(locDrpdwnSelectedColumns));
		for (String columnName : columnNames)
		{
			findVisibleElement(locDrpdwnSelectedColumns).sendKeys(Keys.CONTROL);
			drpdwnSelectedColumns.selectByVisibleText(columnName);
		}
	}

	@Override
	public void moveSelectedColumnUp()
	{
		WebElement spnMoveColumnsUp = findClickableElement(locSpnMoveColumnsUp);
		spnMoveColumnsUp.click();
	}

	@Override
	public void moveSelectedColumnDown()
	{
		WebElement spnMoveColumnsDown = findClickableElement(locSpnMoveColumnDown);
		spnMoveColumnsDown.click();
	}

	@Override
	public void clickMoveRightAll()
	{
		WebElement spnRightArrowMoveAll = findClickableElement(locSpnRightArrowMoveAll);
		spnRightArrowMoveAll.click();
	}

	@Override
	public void clickMoveLeftAll()
	{
		WebElement spnLeftArrowMoveAll = findClickableElement(locSpnLeftArrowMoveAll);
		spnLeftArrowMoveAll.click();
	}

	@Override
	public void clickMoveRightSelected()
	{
		WebElement spnRightArrowMoveSelected = findClickableElement(locSpnRightArrowMoveSelected);
		spnRightArrowMoveSelected.click();
	}

	@Override
	public void clickMoveLeftSelected()
	{
		WebElement spanLeftArrowMoveSelected = findClickableElement(locSpanLeftArrowMoveSelected);
		spanLeftArrowMoveSelected.click();
	}

	public void selectFirstSortByColumn(String columnName)
	{
		Select drpdwnFirstSortByColumn = new Select(findClickableElement(locDrpdwnFirstSortByColumn));
		drpdwnFirstSortByColumn.selectByVisibleText(columnName);
	}

	public void selectSecondSortByColumn(String columnName)
	{
		Select drpdwnSecondSortByColumn = new Select(findClickableElement(locDrpdwnSecondSortByColumn));
		drpdwnSecondSortByColumn.selectByVisibleText(columnName);
	}

	@Override
	public AdminIsheetManageViewsWeb clickSaveOnAddView()
	{
		if (isDisplayed(locBtnAddViewSaveTop))
		{
			WebElement btnSaveTop = findClickableElement(locBtnAddViewSaveTop);
			btnSaveTop.click();
		}
		else
		{
			WebElement btnSaveBottom = findClickableElement(locBtnAddViewSaveBottom);
			btnSaveBottom.click();
		}
		return new AdminIsheetManageViewsWeb(driver);
	}

	@Override
	public AdminIsheetManageViewsWeb clickCancelOnAddView()
	{
		if (isDisplayed(locBtnAddViewSaveTop))
		{
			WebElement btnCancelTop = findClickableElement(locBtnAddViewCancelTop);
			btnCancelTop.click();
		}
		else
		{
			WebElement btnCancelBottom = findClickableElement(locBtnAddViewCancelBottom);
			btnCancelBottom.click();
		}
		return new AdminIsheetManageViewsWeb(driver);
	}

	@Override
	public void selectOrderOfFirstColumn(String order)
	{
		switch (order.toLowerCase())
		{
			case "ascending":
				WebElement radioButtonSortingOrderAsc = findClickableElement(By.xpath("(.//*[@id='firstSortedColumnId']//following::*[normalize-space()='Show records in ascending order (A,B,C or 1,2,3)']//child::*[@type='radio'])[1]"));
				radioButtonSortingOrderAsc.click();
				break;
			case "descending":
				WebElement radioButtonSortingOrderDesc = findClickableElement(By.xpath("(.//*[@id='firstSortedColumnId']//following::*[normalize-space()='Show records in descending order (C,B,A or 3,2,1)']//child::*[@type='radio'])[1]"));
				radioButtonSortingOrderDesc.click();
				break;
			default:
				System.err.println("Enter Valid option");
				break;
		}
	}

	@Override
	public void selectOrderOfSelectColumn(String order)
	{
		switch (order.toLowerCase())
		{
			case "ascending":
				WebElement radioButtonSortingOrderAsc = findClickableElement(By.xpath("(.//*[@id='secondSortedColumnId']//following::*[normalize-space()='Show records in ascending order (A,B,C or 1,2,3)']//child::*[@type='radio'])[1]"));
				radioButtonSortingOrderAsc.click();
				break;
			case "descending":
				WebElement radioButtonSortingOrderDesc = findClickableElement(By.xpath("(.//*[@id='secondSortedColumnId']//following::*[normalize-space()='Show records in descending order (C,B,A or 3,2,1)']//child::*[@type='radio'])[1]"));
				radioButtonSortingOrderDesc.click();
				break;
			default:
				System.err.println("Enter Valid option");
				break;
		}
	}

	@Override
	public void selectViewConditionType(String conditionType) throws InterruptedException
	{
		adminIsheetAddColumnPage.setColumnConditionType(conditionType);
	}

	public void setViewConditionFilterBy(String filterBy) throws InterruptedException
	{
		adminIsheetAddColumnPage.setColumnConditionFilterBy(filterBy);
	}

	@Override
	public void setViewConditionFilterOperator(String filterOperator) throws InterruptedException
	{
		adminIsheetAddColumnPage.setColumnConditionFilterOperator(filterOperator);
	}

	@Override
	public void clickIsheetViewMoreAction(String viewName)
	{
		WebElement moreActionEle = findClickableElement(By.xpath("(.//*[normalize-space(text())='" + viewName + "']//parent::*//following::*[@data-original-title='More actions'][1]"));
		moreActionEle.click();
	}

	@Override
	public void selectViewFilterCondition(String conditionType, String column, String operator, String filterValue, boolean val) throws InterruptedException
	{
		adminIsheetAddColumnPage.selectColumnFilterCondition(conditionType, column, operator, filterValue, val);
	}

	@Override
	public void setViewConditionFilterDate(Object date) throws InterruptedException
	{
		adminIsheetAddColumnPage.setColumnConditionFilterDate(date);
	}

	@Override
	public void setViewConditionFilterTime(Object time) throws InterruptedException
	{
		adminIsheetAddColumnPage.setColumnConditionFilterTime(time);
	}

	@Override
	public void clickAddFilterOnAddView()
	{
		WebElement btnAddFilter = findClickableElement(locBtnAddFilter);
		btnAddFilter.click();
	}

	@Override
	public void clickRemoveFilterOnAddView()
	{
		WebElement btnRemoveFilter = findClickableElement(locBtnRemoveFilter);
		btnRemoveFilter.click();
	}

	@Override
	public void clickFilterValueDropDown()
	{
		WebElement btnFilterValue = findClickableElement(locBtnFilterValue);
		btnFilterValue.click();
	}

	@Override
	public void selectFilterValueOption(String option)
	{
		findVisibleElement(locBtnFilterValueOpened);
		selectRadioOption(option);
	}

	@Override
	public void searchContentForFilterValueOption(String content)
	{
		findVisibleElement(locBtnFilterValueOpened);
		WebElement inptSearchFilterValue = findClickableElement(locInptSearchFilterValue);
		inptSearchFilterValue.clear();
		inptSearchFilterValue.sendKeys(content);
	}

	@Override
	public void enterLookupValueForFilterValueOption(String value)
	{
		findVisibleElement(locBtnFilterValueOpened);
		WebElement inptFilterValueOther = findClickableElement(locInptFilterValueOther);
		inptFilterValueOther.clear();
		inptFilterValueOther.sendKeys(value);
	}

	@Override
	public void selectDateFilterOption(String option)
	{
		Select drpdwnSelectDateFilter = new Select(findClickableElement(locDrpdwnSelectDateFilter));
		drpdwnSelectDateFilter.selectByVisibleText(option.trim());
	}

	@Override
	public void selectDateSign(String dateSign)
	{
		Select drpdwnSelectDateSign = new Select(findClickableElement(locDrpdwnSelectDateSign));
		drpdwnSelectDateSign.selectByVisibleText(dateSign);
	}

	@Override
	public void addDateDays(int days)
	{
		WebElement inptDateDays = findClickableElement(locInptDateDays);
		inptDateDays.clear();
		inptDateDays.sendKeys(Integer.toString(days));
		;
	}

	@Override
	public void moveViewFilterConditionUp()
	{
		WebElement spnMoveViewFilterConditionUp = findClickableElement(locSpnMoveViewFilterConditionUp);
		spnMoveViewFilterConditionUp.click();
	}

	@Override
	public void moveViewFilterConditionDown()
	{
		WebElement spnMoveViewFilterConditionDown = findClickableElement(locSpnMoveViewFilterConditionDown);
		spnMoveViewFilterConditionDown.click();
	}

	/**
	 * Verify available column name
	 *
	 * @param columnName
	 *        name of the column to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 16 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyAvailableColumns(String... columnNames)
	{
		findVisibleElement(locDrpdwnAvailableColumns);
		for (int i = 0; i < columnNames.length; i++)
		{
			By optionXpath = By.xpath(".//*[@id='leftSidelistOfColumns']//*[normalize-space(text())='" + columnNames[i].trim() + "']");
			if (!isDisplayed(optionXpath, Speed.slow))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify selected column name
	 *
	 * @param columnName
	 *        name of the column to verify
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 16 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifySelectedColumns(String... columnNames)
	{
		findVisibleElement(locDrpdwnSelectedColumns);
		for (int i = 0; i < columnNames.length; i++)
		{
			By optionXpath = By.xpath(".//*[@id='rightSidelistOfSortedColumns']//*[normalize-space(text())='" + columnNames[i].trim() + "']");
			if (!isDisplayed(optionXpath))
			{
				return false;
			}
		}
		return true;
	}
}
