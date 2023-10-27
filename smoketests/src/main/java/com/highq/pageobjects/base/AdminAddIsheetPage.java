package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminAddIsheetPage extends BannerPage
{

	public void addIsheetTitle(String title);

	public void addIsheetDescription(String description);

	public void clickIsheetStatusDropdown();

	public void selectIsheetStatus(String option);

	public void selectIsheetAccessType(String option);

	public void selectIsheetFieldDescription(String option);

	public void addIsheetSelectCheckBoxOption(String option, boolean val);

	public AdminIsheetWeb addIsheetClickSave();

	public AdminIsheetWeb addIsheetClickCancel();

}
