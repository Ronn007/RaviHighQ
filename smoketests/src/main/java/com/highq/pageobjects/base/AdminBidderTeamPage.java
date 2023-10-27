package com.highq.pageobjects.base;

public interface AdminBidderTeamPage extends BannerPage
{
	public void selectAllUsersOfABidderTeamOrganisation(String orgName, boolean state);

	public void saveBidderTeamChanges();
}
