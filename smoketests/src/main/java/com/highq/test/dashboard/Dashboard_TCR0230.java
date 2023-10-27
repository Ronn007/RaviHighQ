package com.highq.test.dashboard;

import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0230 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String siteName = "Dashboard_TC0230" + getRandomString();
	String templateName = "None";
	String description = "Dashboard_TC0230" + getRandomString();
	String landingPage = "Home";
	String startDate = "31 12 2017";
	String endDate = "31 12 2018";
	String siteType = "Service";
	String clientNumber = "1234";
	String matterNumber = "5674";
	String category = "Category1";
	String logo = "automation.jpg";
	String adminNotes = "Dashboard_TC0230 Admin Notes";

	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;

	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Override
	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 1)
	public void dashboardTC0230() throws InterruptedException, IOException
	{
		scenario1();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnCreateSite();

		Assert.assertTrue(dashboardWeb.verifyAddSiteModalOpened());
		Assert.assertTrue(dashboardWeb.verifySelectedModulesOnAddSite());

		dashboardWeb.enterSiteName(siteName);
		dashboardWeb.addSiteSetSiteTemplate(templateName);
		dashboardWeb.addSiteSetDescription(description);
		dashboardWeb.addSiteEnableAllSiteModules();
		dashboardWeb.addSiteSetLandingPage(landingPage);
		dashboardWeb.addSiteSetStartDate(startDate);
		dashboardWeb.addSiteEndDate(endDate);
		// dashboardWeb.addSiteSetSiteType(siteType);
		dashboardWeb.addSiteSetClientNumber(clientNumber);
		dashboardWeb.addSiteSetMatterNumber(matterNumber);
		dashboardWeb.setSiteCategory(category);
		dashboardWeb.addSiteUploadSiteLogo(logo);
		dashboardWeb.addSiteSetAdminNotes(adminNotes);

		dashboardWeb.clickOnSaveToCreateSite();

		Assert.assertTrue(verifySiteName(siteName));

		// //*[@data-id='createSiteModal_categoryID']
	}
}
