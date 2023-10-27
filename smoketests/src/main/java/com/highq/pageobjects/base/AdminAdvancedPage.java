package com.highq.pageobjects.base;

import org.openqa.selenium.By;

public interface AdminAdvancedPage extends BannerPage
{

	public void selectStatus(String status);

	public boolean verifySelectedStatus(String status);

	public void setDisplayUserAvatars(boolean permission);

	public void setUseGroupForPermissioning(boolean permission) throws InterruptedException;

	public void setPriceSensitiveMatter(boolean permission);

	public void enableAdvancedSiteAdminOption(boolean permission);

	public void setDisableRecentActivity(boolean permission);

	public void setRemoveSiteNameFromHeader(boolean permission);

	public void setEnableTwoFactorAuthentication(boolean permission);

	public void setPasswordEnabled(boolean permission);

	public void sendPassword(String password);

	public void sendContactUsEmailOrURL(String emailOrURL);

	public void contactUsChooseAdmins(String adminName);

	public void selectSiteOwner(String siteOwner);

	public void sendSiteOwnerFirstName(String firstName);

	public void sendSiteOwnerLastName(String lastName);

	public void sendSiteOwnerEmail(String email);

	public void setEnableIPAddressAccessList(boolean permission);

	public void sendIPAddressMultipleIP(String commaSeparatedIP);

	public void setAuthenticationRequiredForRSS(boolean permission);

	public void setMicroblogging(boolean permission);

	public void scrollToTop() throws InterruptedException;

	public void selectCheckbox(By option, boolean check);

	public void saveAdvancedChanges();
}
