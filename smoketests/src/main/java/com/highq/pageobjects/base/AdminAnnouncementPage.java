package com.highq.pageobjects.base;

public interface AdminAnnouncementPage extends BannerPage
{
	public void clickOnSave();

	public void enableSiteAnnouncement(boolean state);

	public void setAnnouncementContent(String text);
}
