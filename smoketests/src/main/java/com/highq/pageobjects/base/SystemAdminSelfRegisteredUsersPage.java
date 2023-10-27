package com.highq.pageobjects.base;

public interface SystemAdminSelfRegisteredUsersPage extends BannerPage
{
	public void clickFilterButton();

	public void selectUserStatusFromFilter(String option);

	public void clickAnonymiseButton();

	public boolean verifyMakeAnonymiseButton();

	public void selectUser(String UserEmail);

	public void clickOnArchiveButton();

	public void clickOnCancelButtonOfAnonymiseModel();

	public boolean verifyEmailID(String EmailId);

	public void searchUser(String EmailId);

	public void clickOnAnonymiseButtonOfAnonymiseModel();

	public void clearSearchBox();
}
