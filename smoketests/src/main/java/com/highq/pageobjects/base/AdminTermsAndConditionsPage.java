package com.highq.pageobjects.base;

public interface AdminTermsAndConditionsPage extends BannerPage
{
	public void clickOnSave();

	public void enableSiteTermsAndCondition(boolean state);

	public void setTermsAndConditionText(String text);

	public void showOnFirstSiteLogin(boolean showNextTime);

	public void showOnEverySiteLogin();
}
