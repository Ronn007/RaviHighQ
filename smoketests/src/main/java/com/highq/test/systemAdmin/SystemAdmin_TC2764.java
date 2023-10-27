package com.highq.test.systemAdmin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author hetal.thakkar
 */
public class SystemAdmin_TC2764 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String none = "None";
	String immediate = "Immediate";
	String daily = "Daily";
	String weekly = "Weekly";
	String siteName = "SystemAdmin_TC2764" + getRandomString();

	BannerPage bannerWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	PreConfiguration preConfiguration;
	AdminPage adminPageWeb;

	By dropDownButton = By.id("CUSTOM_SITE_EMAIL_ALERTS_DEFAULT");

	@Test
	public void SystemAdminTC2764() throws InterruptedException
	{
		scenario1();

	}

	/**
	 * @author hetal.thakkar
	 * @created on 09/05/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setSiteEmailalerts(none);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeSaveChangesMessage();

		Assert.assertTrue(aspConfigurationWeb.verifySiteEmailAlertOptionFromDropDown(none));
		Assert.assertTrue(aspConfigurationWeb.verifySiteEmailAlertOptionFromDropDown(immediate));
		Assert.assertTrue(aspConfigurationWeb.verifySiteEmailAlertOptionFromDropDown(daily));
		Assert.assertTrue(aspConfigurationWeb.verifySiteEmailAlertOptionFromDropDown(weekly));

		aspConfigurationWeb.setSiteEmailalerts(daily);
		Assert.assertFalse(aspConfigurationWeb.isSiteEmailAlertDropdownEnable());
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeSaveChangesMessage();
		Assert.assertTrue(aspConfigurationWeb.isSiteEmailAlertDropdownValueSelected(daily));

		aspConfigurationWeb.setSiteEmailalerts(none);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeSaveChangesMessage();

		dashboardWeb = aspConfigurationWeb.gotoDashboard();
		preConfiguration = new PreConfiguration(driver);
		adminPageWeb = preConfiguration.createSiteAndNavigateToAdmin(siteName);
		adminPageWeb.clickEmailAlertsInLeftPanel();

		Assert.assertTrue(adminPageWeb.verifyDefaultAlertForNewSiteUser(none));

		aspAdminWeb = bannerWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setSiteEmailalerts(daily);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeSaveChangesMessage();

		aspConfigurationWeb.gotoDashboard();
		adminPageWeb = preConfiguration.createSiteAndNavigateToAdmin(siteName);
		adminPageWeb.clickEmailAlertsInLeftPanel();

		Assert.assertTrue(adminPageWeb.verifyDefaultAlertForNewSiteUser(none));

	}
}
