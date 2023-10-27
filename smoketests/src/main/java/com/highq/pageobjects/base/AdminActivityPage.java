package com.highq.pageobjects.base;

public interface AdminActivityPage extends BannerPage
{
	public void enableSiteActivity(boolean state);

	public void enableMicroblogging(boolean state);

	public void displayUserAvatars(boolean state);

	public void clickOnSave();
}
