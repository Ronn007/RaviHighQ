/**
 * 
 */
package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AddUserWeb;

/**
 * @author vivek.mishra
 */
public interface AdminUserManageOrganizationsPage extends BannerPage
{

	public void setBidderNonBidderPermission(String org, String type);

	public AddUserWeb clickOnSaveButton();

	public AddUserWeb clickOnCancelButton();

}
