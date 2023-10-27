package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.ViewUserProfileWeb;

public interface MyProfilePage extends BannerPage
{
	public void expandLeftPanel();

	public ViewUserProfileWeb gotoSummaryInLeftPanel();

	public void gotoFullProfileInLeftPanel();

	public void gotoFollowingInLeftPanel();

	public void gotoFollowersInLeftPanel();

	public void gotoSharedImagesInLeftPanel();

	public boolean verifyUserInFollowingOrFollowers(String userName);
}
