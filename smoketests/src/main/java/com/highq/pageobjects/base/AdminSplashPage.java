package com.highq.pageobjects.base;

public interface AdminSplashPage extends BannerPage
{
	public void clickOnSave();

	public void enableSiteSplashPage(boolean state);

	public void setSplashText(String text);

	public void showOnFirstSiteLogin(boolean showNextTime);

	public void showOnEverySiteLogin();

}
