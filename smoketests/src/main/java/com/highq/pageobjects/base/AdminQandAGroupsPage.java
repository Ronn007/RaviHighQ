/**
 * 
 */
package com.highq.pageobjects.base;

/**
 * @author vivek.mishra
 */
public interface AdminQandAGroupsPage extends BannerPage
{

	public void clickOnCreateBidderGroupButton();

	public void clickOnRemoveQandAGroupButton();

	public void selectRadioOfGroup(String group);

	public void clickOnGroup(String group);

	public void removeGroup(String groupName);

	public AdminQandAGroupsPage createBidderGroup(String groupName, String[] organization);

	public AdminQandAGroupsPage createSellerGroup(String groupName, String[] organization);

	public boolean verifyGroupList();

	public boolean verifyGroup(String groupName);

}
