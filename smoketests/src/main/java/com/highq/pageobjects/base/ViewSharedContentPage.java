package com.highq.pageobjects.base;

public interface ViewSharedContentPage extends BannerPage
{
	public void setSelfRegisterUserEmailId(String logInEmailId);

	public void clickOnVerifyEmailAddress();

	public void setPassword(String password);

	public void clickSubmit();
}
