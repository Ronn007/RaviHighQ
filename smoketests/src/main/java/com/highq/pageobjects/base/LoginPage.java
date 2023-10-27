package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.DashboardWeb;

public interface LoginPage extends BannerPage
{
	public void setEmailId(String logInEmailId);

	public void setPassword(String password);

	public DashboardWeb clickSignIn();

	void setRememberMe(boolean desiredState);

	boolean verifyEmailDisplayForTechnicalSupport();

	boolean verifyHighQLogoDisplayInFooter();

	boolean verifyCopyRightsLink();

	boolean verifyTermsOfUseLink();

	boolean verifyHelpLink();

	boolean verifyPrivacyPolicyLink();

	void clickEmailDisplayForTechnicalSupport();

	void clickHighQLogoDisplayInFooter();

	void clickCopyRightsLink();

	void clickTermsOfUseLink();

	void clickHelpLink();

	void clickPrivacyPolicyLink();

	boolean verifyResetPasswordLInk();

	void clickResetPasswordLink();

	boolean verifyTermsAndConditionPage();

	boolean verifyResetPasswordPage();


}
