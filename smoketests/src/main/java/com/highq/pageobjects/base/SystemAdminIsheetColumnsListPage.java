package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.SystemAdminEditColumnWeb;
import com.highq.pageobjects.pages.SystemAdminIsheetAdminAddColumnWeb;

public interface SystemAdminIsheetColumnsListPage extends BannerPage
{
	public SystemAdminEditColumnWeb clickOnColumnName(String columnName);

	public SystemAdminIsheetAdminAddColumnWeb clickOnAddNewColumn();

	public boolean verifyColumnDisplayed(String columnName);
}
