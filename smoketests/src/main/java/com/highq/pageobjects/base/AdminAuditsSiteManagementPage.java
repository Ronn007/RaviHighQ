package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminAuditsSiteManagementWeb;

/**
 * @author jyoti.raj
 */
public interface AdminAuditsSiteManagementPage extends BannerPage
{

	public AdminAuditsSiteManagementWeb clickOnSubmit();

	public void clickOnActionNameOnSiteManagementAudit(int position);

	public boolean verifyActionDetails(String title, String oldParam, String newParam);

	public void clickOnCancelInActionDeTails();

	boolean verifySiteUpdateListDisplayed();

}
