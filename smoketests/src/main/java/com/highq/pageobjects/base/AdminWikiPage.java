package com.highq.pageobjects.base;

public interface AdminWikiPage extends BannerPage
{
	// *************************************** Basic Operations on Elements ***********************************************

	public void clickOnSave();

	// *************************************** Functional Operations ***********************************************

	public void setWikiOptions(String option, boolean value);

}
