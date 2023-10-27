package com.highq.pageobjects.base;

public interface AdminBidderOrganisationsPage extends BannerPage
{
	public void clickSave();

	public void selectAllBidderCheckbox(boolean state);

	public void setBidderOrganisation(String organisation, boolean state);
}
