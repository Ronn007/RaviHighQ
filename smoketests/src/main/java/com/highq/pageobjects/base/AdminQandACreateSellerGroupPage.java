/**
 * 
 */
package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminQandAGroupsWeb;

/**
 * @author vivek.mishra
 */
public interface AdminQandACreateSellerGroupPage extends BannerPage
{
	public void clickOnMoveRightArrow();

	public void clickOnMoveLeftArrow();

	public void sendTextInGroupNameTextBox(String text);

	public AdminQandAGroupsWeb clickOnSaveButton();

	public AdminQandAGroupsWeb clickOnCancelButton();

	public void clickOnOrganizationInAllOrganizationList(String organization);

	public void clickOnOrganizationInMemberOrganizationList(String organization);

	public boolean verifyOrganizationInMemberOrganizationList(String organization);

	public boolean verifyOrganizationInAllOrganizationList(String organization);

	public void moveOrganizationFromAllOrganizationToMemberOrganization(String[] organization);

	public boolean verifyCreateSellerGroupPage();

	public void moveOrganizationFromMemberOrganizationToAllOrganization(String[] organization);

}
