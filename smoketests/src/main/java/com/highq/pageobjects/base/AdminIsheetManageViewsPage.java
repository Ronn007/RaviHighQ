package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminIsheetAddViewWeb;
import com.highq.pageobjects.pages.AdminIsheetEditViewWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetManageViewsPage extends BannerPage
{
	public AdminIsheetEditViewWeb selectView(String viewName);

	public AdminIsheetWeb clickBackOnManageViews();

	public AdminIsheetAddViewWeb clickAddView();

	public void clickSortView();

	public void clickOnViewMoreAction(String viewName);

	public AdminIsheetWeb selectOptionOnViewMoreAction(String viewName, String option);

	public void clickDeleteOnDeleteViewModal();

	public void clickCancelOnDeleteViewModal();

	public void clickCloseOnDeleteViewModal();

	public boolean verifyDeleteViewModalMessage(String message);

	public void dragAndDropIsheetViews(String sourceViewName, String destinationViewName);

	public void clickApplyOnSortIsheetViewModal();

	public void clickCancelOnSortIsheetViewModal();

	public void clickCloseOnSortIsheetViewModal();

	public boolean verifyView(String viewName);

}
