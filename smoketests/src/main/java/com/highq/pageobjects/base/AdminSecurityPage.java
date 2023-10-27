package com.highq.pageobjects.base;

public interface AdminSecurityPage extends BannerPage
{
	public void setUseGroupForPermissioning(boolean permission) throws InterruptedException;

	public void setEnableTwoFactorAuthentication(boolean permission);

	public void setPasswordEnabled(boolean permission);

	public void sendPassword(String password);

	public void enableIPAddressRestrictions(boolean permission);

	public void acceptIPAddressRestrictionModel();

	public void cancelIPAddressRestrictionModel();

	public void setrestrictedIps(String commaSeparatedIP);

	public void setAuthenticationRequiredForRSS(boolean permission);

	public void saveAdvancedChanges();

	public boolean verifyBidderSiteEnabled();

	public void setAdvanceQAPermissions(boolean permission);

	public boolean verifyAdvanceQAPermission();

	public void setBidderSitePermission(boolean permission);

	public void enableAdvancedSiteAdminOption(boolean permission);

	public boolean verifyModal();

	public void clickOnModalButton(String buttonText);

	public void clickOnSave();
}
