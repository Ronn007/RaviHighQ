package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.DashboardLabels;
import com.highq.pageobjects.base.MyDraftPage;

/**
 * @author khushbu.dhandhukiya
 */
public class MyDraftWeb extends BannerPageWeb implements MyDraftPage
{

	public MyDraftWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * used to verify my Draft page
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 16/04/2018
	 */

	@Override
	public boolean verifyMyDraftPage()
	{

		By myDraft = By.xpath(".//*[normalize-space(.)='" + DashboardLabels.DASHBOARD_MYDRAFT + "' and contains(@class,'TruncateTxt')]");
		return isDisplayed(myDraft, Speed.slow);
	}

}
