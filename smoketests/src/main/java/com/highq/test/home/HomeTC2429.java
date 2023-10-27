package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author tejash.trivedi
 */
public class HomeTC2429 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.user@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Pa&&worD123";
	String[] userNames = {"normal.siteuser2429@home2429.com", "site.admin@home2429.com"};
	String jsonFileName = "home/preConfiguration_Home_2429.json";
	String newPassword = "Pa&&worD123";
	String siteName = "Home_TC2429_01";
	String toolTipMsg1 = "Add to favourites";
	String toolTipMsg2 = "Remove from favourites";
	String home = " - Home";
	String precon = "<b> <font color='black' size='2'>Pre-condition</font></b>";
	String steps = "<b> <font color='black' size='2'>Steps:</font></b>";
	String expected = "<b> <font color='black' size='2'>Expected:</font></b>";
	String favTabName = "Favourites";
	LoginPage loginPageWeb;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerWeb;
	DashboardPage dashboardWeb;
	HomePage homeWeb;
	AdminPage adminPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test(priority = 1)
	public void HomeTC2429TestCase() throws InterruptedException, IOException, JSONException
	{
		scenario1();
		scenario2();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void scenario1() throws InterruptedException, IOException, JSONException
	{
		/**
		 * Scenario 1: Check favourites functionality in Home Dashboard.
		 * Case 1: Mark as favourite.
		 */
		precondition();
		Reporter.log(precon);

		Reporter.log("Home Dashboard must be configured in site & Home Module must be enabled in it.");
		Reporter.log("All users of the site must be having View permission of Home Module.");

		Reporter.log("Case 1: Mark as favourite.");

		Reporter.log(steps);

		Reporter.log("1.Login with any role user of the site.");

		bannerWeb = login(userNames[0], newPassword);
		dashboardWeb = gotoDashboard();
		Reporter.log("2. Go to above created site.");
		dashboardWeb.searchSite(siteName);

		Reporter.log("3. Go to Home Dashboard.");

		homeWeb = dashboardWeb.gotoHomeModule();

		Reporter.log("4. Hover mouse over the favourite icon.");

		Reporter.log(expected);

		Assert.assertTrue(homeWeb.verifyFavouriteToolTipMessage(toolTipMsg1));

		Reporter.log("In tool tip it should display a message \"Add to favourites\".");

		Reporter.log("5. Click on Favourite icon displaying on the Home Dashboard.");

		homeWeb.clickOnFavouriteIcon();

		Assert.assertTrue(homeWeb.verifyFavouriteToolTipMessage(toolTipMsg2));
		Reporter.log("Home Dashboard should be marked favourite for the login user. (favourites icon will be filled with orange color after it is marked as favourite)");

		Reporter.log("6. After Home dashboard is marked as favourite, Click on Favourites icon displaying in top navigation bar.");

		bannerWeb.clickOnFavouriteLink();
		homeWeb.clickOnTabOfFavouriteLink(favTabName);
		Assert.assertTrue(homeWeb.verifySiteInFavouriteList(siteName + home));

		Reporter.log("Home Dashboard of the site which is marked as favourite should display under Favourites option in Top navigation bar.");

		Reporter.log("It should display Site Home icon with 'Site name' – Home along with the date when Home dashboard was marked as favourite under Favourites option in Top navigation bar.");

		Reporter.log("Case 2: Remove as favourite.");
		Reporter.log(precon);
		Reporter.log("Home Dashboard must be marked as favourite for the login user.");

		Reporter.log(steps);

		Reporter.log("1.Login with any role user of the site.");

		Reporter.log("2. Go to above created site.");

		Reporter.log("3. Go to Home Dashboard.");

		Reporter.log("4. Hover mouse over the favourite icon.");

		Reporter.log(expected);

		Assert.assertTrue(homeWeb.verifyFavouriteToolTipMessage(toolTipMsg2));
		Reporter.log("In tool tip it should display a message \"Remove from favourites\".");

		Reporter.log("5. Click on Favourite icon");

		homeWeb.clickOnFavouriteIcon();

		Reporter.log("Home Dashboard should be removed from favourite for the login user. (orange colour will be removed from the favourite icon after it is removed from favourites.)");
		Assert.assertTrue(homeWeb.verifyFavouriteToolTipMessage(toolTipMsg1));

		driver.navigate().refresh();

		Reporter.log("6.After Home dashboard is removed from favourite, Click on Favourites icon displaying in top navigation bar.");

		homeWeb.clickOnFavouriteLink();
		homeWeb.clickOnTabOfFavouriteLink(favTabName);
		Assert.assertTrue(homeWeb.verifyMessageForNoFavourites("You don't have any favourites."));

		Reporter.log("Home Dashboard of the site which is removed from favourites should also be removed from Favourites option in Top navigation bar.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void scenario2() throws InterruptedException, IOException
	{

		Reporter.log("Scenario 2: Check Home Dashboard marked as favourite through different user.");
		Reporter.log(precon);

		Reporter.log("Home Dashboard must be configured in site & Home Module must be enabled in it.");
		Reporter.log("All users of the site must be having View permission of Home Module.");
		Reporter.log("User-1 & User-2 must exist in the site.");
		Reporter.log("Home Dashboard in the site must be marked as favourite only by User-1.");

		Reporter.log(steps);

		bannerWeb = login(userNames[1], newPassword);
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		homeWeb = dashboardWeb.gotoHomeModule();
		homeWeb.clickOnFavouriteIcon();
		logout();

		Reporter.log("1. Login with User-2");

		bannerWeb = login(userNames[0], newPassword);

		Reporter.log("2. Go to above created site.");

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		Reporter.log("3. Go to Home Dashboard.");

		homeWeb = dashboardWeb.gotoHomeModule();

		Reporter.log("Check favourite icon in Home Dashboard.");

		Reporter.log(expected);

		Assert.assertTrue(homeWeb.verifyFavouriteToolTipMessage(toolTipMsg1));
		homeWeb.clickOnFavouriteLink();
		homeWeb.clickOnTabOfFavouriteLink(favTabName);
		Assert.assertTrue(homeWeb.verifyMessageForNoFavourites("You don't have any favourites."));

		Reporter.log("Home Dashboard should not display as marked favourite for User-2 when it is marked as favourite by User-1.");
		Reporter.log("It should also not listed under the Favourites option in Top navigation bar for User-2 when it is marked as favourite by User-1.");
		logout();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void precondition() throws InterruptedException, IOException, JSONException
	{
		Reporter.log("1.Login with Site Admin/System Admin users.");
		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerWeb = login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		bannerWeb = login(userNames[0], newPassword);
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		homeWeb = dashboardWeb.gotoHomeModule();

		if (!homeWeb.verifyFavouriteToolTipMessage(toolTipMsg1))
		{
			homeWeb.clickOnFavouriteIcon();
		}
		logout();
	}
}
