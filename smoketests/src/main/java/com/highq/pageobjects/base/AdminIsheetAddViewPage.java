package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminIsheetManageViewsWeb;

public interface AdminIsheetAddViewPage extends BannerPage
{
	public void enterViewName(String viewName);

	public void selectAvailableColumns(String... columnNames);

	public void selectSelectedColumns(String... columnNames);

	public void moveSelectedColumnUp();

	public void moveSelectedColumnDown();

	public void clickMoveRightAll();

	public void clickMoveLeftAll();

	public void clickMoveRightSelected();

	public void clickMoveLeftSelected();

	public AdminIsheetManageViewsWeb clickSaveOnAddView();

	public AdminIsheetManageViewsWeb clickCancelOnAddView();

	public void selectOrderOfFirstColumn(String order);

	public void selectOrderOfSelectColumn(String order);

	public void selectViewConditionType(String conditionType) throws InterruptedException;

	public void setViewConditionFilterOperator(String filterOperator) throws InterruptedException;

	public void clickIsheetViewMoreAction(String viewName);

	public void selectViewFilterCondition(String conditionType, String column, String operator, String filterValue, boolean val) throws InterruptedException;

	public void setViewConditionFilterDate(Object date) throws InterruptedException;

	public void setViewConditionFilterTime(Object time) throws InterruptedException;

	public void clickAddFilterOnAddView();

	public void clickRemoveFilterOnAddView();

	public void clickFilterValueDropDown();

	public void selectFilterValueOption(String option);

	public void searchContentForFilterValueOption(String content);

	public void enterLookupValueForFilterValueOption(String value);

	public void selectDateFilterOption(String option);

	public void selectDateSign(String dateSign);

	public void addDateDays(int days);

	public void moveViewFilterConditionUp();

	public void moveViewFilterConditionDown();

	public boolean verifyAvailableColumns(String... columnName);

	public boolean verifySelectedColumns(String... columnName);
}
