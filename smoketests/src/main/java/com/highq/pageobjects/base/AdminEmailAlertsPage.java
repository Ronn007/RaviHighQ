package com.highq.pageobjects.base;

public interface AdminEmailAlertsPage extends BannerPage
{
	public void clickSave();

	public void selectDefaultAlertOption(String alertOption);

	public boolean searchEmailAlertOptions(String optionToSearch);

	public void setEmailAlertFrequencyForUser(String userEmail, String frequency);

	public void expandFilter();

	public void filterByOrganisation(String organisationName, boolean state);

	public void filterByStatus(String status, boolean state);

	public void filterByFrequency(String status, boolean state);

	public void clearFilter();
}
