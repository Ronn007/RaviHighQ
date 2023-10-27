package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.ViewUserProfileWeb;

public interface PeoplePage extends BannerPage
{

	public void exportPeopleList();

	public void searchPeople(String searchString);

	public void clearSearchBox();

	public void expandFilter();

	public void filterByOrganisation(String orgName);

	public boolean verifyFilteredOrgName(String orgName);

	public void followUser(String userEmail);

	public void unfollowUser(String userEmail);

	public void sendMessage(String userEmail, String message, String... otherRecipientsMail);

	public boolean verifyName(String name);

	public boolean verifyOrganisation(String organisation);

	public boolean verifyEmail(String email);

	public boolean verifyOfficePhone(String phone);

	public void clickOnAttachmentIcon();

	public boolean verifyFollowingButtonAgainstUser(String userEmail);

	public boolean verifyFollowButtonAgainstUser(String userEmail);

	public boolean verifyUnfollowButtonOnMouseHover(String userEmail);

	public ViewUserProfileWeb clickUserName(String userEmail);

	public boolean verifyOrganisationInSearchFilter(String orgName);

	public boolean verifyOrganisationCountInSearchFilter(int expectedOrganisationCount);

	public boolean verifySearchResultIsEmpty();

	public boolean verifyNameFieldIsPresent();

	public boolean verifyOrganisationFieldIsPresent();

	public boolean verifyEmailFieldIsPresent();

	public boolean verifyOfficePhoneFieldIsPresent();

	public boolean verifyMobilePhoneFieldIsPresent();

	public boolean verifyUserAvatarIconIsPresent();

}
