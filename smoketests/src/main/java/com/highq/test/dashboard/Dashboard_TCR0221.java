package com.highq.test.dashboard;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.GlobalNavigationAdminPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0221 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	SystemAdminPage systemAdminWeb;
	GlobalNavigationAdminPage globalNavigationAdminWeb;
	BannerPage bannerPageWeb;

	String globalNavigationOption = "On";
	String[] menuItemOptions = {"Menu item", "Container"};
	String menuItemTitle = "Google Search";
	String menuItemURL = "https://www.google.co.in";
	String openIn = "Same window";
	String containerName = "ContainerTCR0221";
	int noOfCoumns = 2;
	String customMenuTitle = "Custom Menu Item";
	String customMenuContent = "Custom Menu Content";

	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 1)
	public void dashboardTC0221() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
		scenario3();
	}

	void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		globalNavigationAdminWeb = systemAdminWeb.gotoGlobalNavigation();
		globalNavigationAdminWeb.deleteMenuItem(menuItemTitle);
		Assert.assertTrue(globalNavigationAdminWeb.verifyGolbalNavigationLabel());
		globalNavigationAdminWeb.clickOnGlobalNavigationDropdown();
		Assert.assertTrue(globalNavigationAdminWeb.verifyOptionOnGolbalNavigationDropdown("On"));
		Assert.assertTrue(globalNavigationAdminWeb.verifyOptionOnGolbalNavigationDropdown("Off"));
		Assert.assertTrue(globalNavigationAdminWeb.verifyMenuItemLabel());
		Assert.assertTrue(globalNavigationAdminWeb.verifyMenuItemOption("Menu item"));
		Assert.assertTrue(globalNavigationAdminWeb.verifyMenuItemOption("Container"));
		Assert.assertTrue(globalNavigationAdminWeb.verifyDragAndDropMessage());
		Assert.assertTrue(globalNavigationAdminWeb.verifyNoMenuFoundMessage());
		Assert.assertTrue(globalNavigationAdminWeb.verifyTopSaveButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyBottomSaveButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyTopPreviewButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyBottomPreviewButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyTopPreviewButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyBottomPreviewButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyTopCancelButton());
		Assert.assertTrue(globalNavigationAdminWeb.verifyBottomCancelButton());
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario2() throws InterruptedException, IOException
	{
		globalNavigationAdminWeb.selectGlobalNavigationOption(globalNavigationOption);
		globalNavigationAdminWeb.clickOnMenuItemDropDown();
		globalNavigationAdminWeb.selectMenuItemOption(menuItemOptions[0]);
		globalNavigationAdminWeb.clickExternalOnAddMenuItemDialog();
		globalNavigationAdminWeb.addExternalMenuItem(menuItemTitle, menuItemURL, openIn);
		globalNavigationAdminWeb.clickSaveOnGlobalNavigation();

		dashboardWeb = dashboardWeb.gotoDashboard();
		Assert.assertTrue(dashboardWeb.verifyGlobalNavigationMenuOption(menuItemTitle));
		dashboardWeb.clickOnMenuItemOnGlobalNavigation(menuItemTitle);
		Assert.assertTrue(driver.getCurrentUrl().contains(menuItemURL));
		driver.navigate().back();
	}

	public void scenario3() throws InterruptedException, IOException
	{
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		globalNavigationAdminWeb = systemAdminWeb.gotoGlobalNavigation();
		globalNavigationAdminWeb.deleteMenuItem(containerName);
		globalNavigationAdminWeb.selectGlobalNavigationOption(globalNavigationOption);
		globalNavigationAdminWeb.clickOnMenuItemDropDown();
		globalNavigationAdminWeb.selectMenuItemOption(menuItemOptions[1]);
		globalNavigationAdminWeb.addContainer(containerName, noOfCoumns);
		globalNavigationAdminWeb.clickSaveOnGlobalNavigation();
		Assert.assertTrue(globalNavigationAdminWeb.menuItemPresent(containerName));

		globalNavigationAdminWeb.selectGlobalNavigationOption(globalNavigationOption);
		globalNavigationAdminWeb.clickOnMenuItemDropDown();
		globalNavigationAdminWeb.selectMenuItemOption(menuItemOptions[0]);
		globalNavigationAdminWeb.clickCustomOnAddMenuItemDialog();
		globalNavigationAdminWeb.addCustomMenuItem(containerName, customMenuTitle, customMenuContent);
		globalNavigationAdminWeb.clickSaveOnGlobalNavigation();

		dashboardWeb = dashboardWeb.gotoDashboard();
		Assert.assertTrue(dashboardWeb.verifyGlobalNavigationMenuOption(containerName));
		Assert.assertTrue(dashboardWeb.verifyGlobalNavigationContainerMenuItem(containerName, customMenuContent));
	}
}
