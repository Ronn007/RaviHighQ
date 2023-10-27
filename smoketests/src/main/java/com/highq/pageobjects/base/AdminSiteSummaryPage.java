package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminPageWeb;

/**
 * @author jyoti.raj
 */
public interface AdminSiteSummaryPage extends BannerPage
{

	public void clickOnSaveAsTemplate();

	public void enterTemplateName(String tempName);

	public AdminPageWeb clickOnSaveInSaveAsTemplate();

}
