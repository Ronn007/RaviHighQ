package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.BannerPageWeb;

public interface PasscodePage extends BannerPage
{

	public String getPasscode(String userEmail);

	public void enterPasscode(String passcode);

	public BannerPageWeb clickOnVerifyPasscode();

	public boolean verifyPasscodePage();

}
