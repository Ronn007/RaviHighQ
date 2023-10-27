/**
 * 
 */
package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminQandAGroupsWeb;

/**
 * @author vivek.mishra
 */
public interface AdminQandACreateBidderGroupPage extends BannerPage
{

	public void moveOrganizationFromAllOrganizationToMemberOrganization(String[] organization);

	public AdminQandAGroupsWeb clickOnCancelButton();

	public AdminQandAGroupsWeb clickOnSaveButton();

	public void clickOnOrganizationInAllOrganizationList(String organization);

	public void clickOnOrganizationInMemberOrganizationList(String organization);

	public boolean verifyOrganizationInMemberOrganizationList(String organization);

	public boolean verifyOrganizationInAllOrganizationList(String organization);

	public void sendTextInQuestionLimitTextBox(String limit);

	public void sendTextInGroupIDTextBox(String text);

	public void sendTextInGroupNameTextBox(String text);

	public void clickOnMoveRightArrow();

	public void clickOnMoveLeftArrow();

	public boolean verifyCreateBidderGroupPage();

	public void moveOrganizationFromMemberOrganizationToAllOrganization(String[] organization);
}
