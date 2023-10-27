package com.highq.test.siteAdmin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminBlogWeb;
import com.highq.pageobjects.pages.AdminEventWeb;
import com.highq.pageobjects.pages.AdminGeneralWeb;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.AdminSecurityWeb;
import com.highq.pageobjects.pages.AdminTaskPageWeb;
import com.highq.pageobjects.pages.AdminTermsAndConditionsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.BlogWeb;
import com.highq.pageobjects.pages.DashboardWeb;
import com.highq.pageobjects.pages.DocumentWeb;
import com.highq.pageobjects.pages.EventsWeb;
import com.highq.pageobjects.pages.ModulesPageWeb;
import com.highq.pageobjects.pages.SiteNavigationWeb;
import com.highq.pageobjects.pages.TasksWeb;
import com.highq.pageobjects.pages.WikiWeb;
import com.highq.parsers.JSONParser;

public class SiteNavigation_TC0886 extends BannerPageWeb
{

	static String sysAdminEmail = "";
	static String sysAdminPassword = "";
	static String siteAdminName = "Site Admin";
	static String siteAdminEmail = "site.admin@highq.com";
	String sitePassword = "HighQ@123";
	private String jsonFileName = "preConfiguration_ActiveSite.json";
	private String jsonFileName1 = "preConfiguration_ActiveSite1.json";
	private String jsonFileName2 = "preConfiguration_ActiveSite2.json";
	private String jsonFileName3 = "preConfiguration_ActiveSite3.json";
	private String jsonFileName4 = "preConfiguration_ActiveSite4.json";
	private String renameLink = "Rename";
	public final String ip = "192.168.154.134,192.168.156.216,192.168.156.129,192.168.156.155,192.168.156.193,192.168.156.100,192.168.156.139,192.168.156.118,192.168.156.111,192.168.156.108,192.168.156.149,192.168.156.145,192.168.156.183,192.168.156.174,192.168.156.40,192.168.156.48,192.168.156.239,192.168.7.151,192.168.7.38,192.168.156.228,192.168.154.38,192.168.154.69,192.168.154.126,192.168.154.97,192.168.154.73,192.168.156.28,192.168.156.239,192.168.156.54,192.168.156.242,192.168.7.146,192.168.7.110,192.168.7.120,192.168.156.77,192.168.156.71,192.168.156.72,192.168.156.230,192.168.156.167,192.168.156.180,192.168.7.94,192.168.7.42";
	private String selectModulesMessage = "Select the modules you would like to use in this site. To rename or customise the site navigation go to the Site navigation settings page.";
	private WebDriver webDriver;
	private String normalUser = "Normal User";
	private String normalUserEmail = "normal.user@highq.com";
	private String externalUserNotAddinSite = "normal.userinternal@apiinternal.com";
	private String siteName = "";
	String siteName1 = "";
	private String doc1 = "doc1.txt";
	private String doc1Name = "doc1";
	private String blogTitle = "Blog For Automation";
	private String renameBlogTitle = "Rename Blog For Automation";
	private String blogCategory = "Cat1";
	private String menuItem = "Menu item";
	private String container = "Container";
	String containerName = "Main Container";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
	private String preparationSiteName;
	private String readOnlySiteName;
	private String passcodeSiteName;
	private String passwordSiteName;
	private String tearmsSiteName;
	private String iPSiteName;
	private String suspendedSiteName;

	private BannerPage bannerPageWeb;
	private AdminPageWeb adminPageWeb;
	private ModulesPageWeb modulePageWeb;
	private DashboardWeb dashboardPageWeb;
	private SiteNavigationWeb siteNavigationWeb;
	private PreConfiguration preConfigurationTest;

	@BeforeClass
	public void setUp() throws IOException
	{
		webDriver = getDriver();
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
		sysAdminEmail = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminEmail"), String.class);
		sysAdminPassword = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminPassword"), String.class);
		preConfigurationTest = new PreConfiguration(driver);

		siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		createAllSitesWithAllModes();
	}

	@Test(priority = 1)
	public void RaviDemo1() throws IOException, InterruptedException
	{
		Reporter.log("<b style=color:red>Test case Made By :- Ravi Pandit</b></br>");
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
		scenario10Case1();
		scenario10Case2();
		scenario10Case3();
		scenario10Case4();
		scenario10Case5();
		scenario11();
		scenario12();
		scenario13();
		scenario14Case1();
		scenario14Case2();
		// Task Bug
		// scenario14Case3();
		scenario14Case4();
		scenario14Case5();
		scenario14Case6();
		scenario14Case7();
		scenario14Case8();
		scenario14Case9();
		scenario15();
		scenario16();
		scenario17();
		scenario18();
		scenario19();
		scenario20();
		scenario21();
		scenario22();

	}

	private void scenario1()
	{
		Reporter.log("Scenario 1 : Things on modules page </br>");
		String siteName = null;
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		Reporter.log("1. Site should be created.");
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName));
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

			logout();
			bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
			dashboardPageWeb = bannerPageWeb.gotoDashboard();
			bannerPageWeb = dashboardPageWeb.searchSite(siteName);
			adminPageWeb = dashboardPageWeb.gotoAdminModule();
			siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
			siteNavigationWeb.clickOnAutomaticSiteNavigation();
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :- ");
		// siteName = "Site_For_TC0886";
		Reporter.log("1. Login with site admin and go to admin page.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2. Click on modules link from left panel.</br>");
		modulePageWeb = adminPageWeb.clickOnModulesInLeftPanel();

		Reporter.log("Expected result :");
		Reporter.log("1. It should display Modules on top of the page as title.</br>");
		Assert.assertTrue(modulePageWeb.varifyModuleTitleName(), "Module Name is Not Diaplay.</br>");

		Reporter.log("2. It should display a message " + selectModulesMessage + "with site navigation link and when user click on this link user should be redirected to site navigation page.</br>");
		Assert.assertEquals(modulePageWeb.getText(By.xpath(".//p[@class='margBott20']")), selectModulesMessage);

		Reporter.log("3. Display all the modules with check box to do enable and disable modules in site.</br>");
		Assert.assertTrue(modulePageWeb.varifyAllModuleWithCheckbox(), "All Module Name with checkbox is Not Diaplay.</br>");

		Reporter.log("4. Rename link should not display here. </br>");
		Assert.assertTrue(!modulePageWeb.varifyModulPageText(renameLink), "Message is not Diaplayed.</br>");
		Reporter.log("5. Site landing page drop down with all possible modules as value and save button.</br>");
		Assert.assertTrue(modulePageWeb.varifyAllModuleinSiteLandingPage());
	}

	private void scenario2()
	{
		Reporter.log("Scenario 2: Site Landing Page");

		String siteName = null;
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition</br>");
		Reporter.log("1. Site should be created.</br>");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

			Reporter.log("Expected result :</br>");
			Reporter.log("If Selected Site Landing Page is</br>");
			logout();
			bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
			dashboardPageWeb = bannerPageWeb.gotoDashboard();
			bannerPageWeb = dashboardPageWeb.searchSite(siteName);
			Reporter.log("1. Home - User will be redirect to Home module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME));

			Reporter.log("2. Activity - User will be redirect to Home module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY));

			Reporter.log("3.  Document (Root Folder) - User will be redirect to Home module of site.</br>");
			setLandinPage(" Files (root folder)");
			Assert.assertTrue(checkLandinPage(siteName, "Files"));

			Reporter.log("4. Document (Recent Activity) - - User will be redirect to Home module of site.</br>");
			setLandinPage("Files (recent activity)");
			Assert.assertTrue(checkLandinPage(siteName, "Files"));

			Reporter.log("5. Document (Advance Search)  - User will be redirect to Home module of site.</br>");
			setLandinPage("Files (advanced search)");
			Assert.assertTrue(checkLandinPage(siteName, "Files"));

			Reporter.log("6. Wiki - User will be redirect to Wiki module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI));

			Reporter.log("7. Blog - User will be redirect to Blog module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG + "s"));

			Reporter.log("8. Tasks - User will be redirect to Tasks module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS));

			Reporter.log("9. Events - User will be redirect to Events module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS);
			Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS));

			Reporter.log("10.  iSheets - User will be redirect to iSheets module of site.</br>");
			setLandinPage(SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS);
			// Assert.assertTrue(checkLandinPage(siteName, SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS));

			Reporter.log("11. If no any site landing page is selected and try to save the page than You must select a landing page validation message should display.</br>");
			setLandinPage("Select a landing page");
			String validationMessage = getText(By.xpath(".//*[@id='cmbSiteHomePage_pID']"));
			Assert.assertTrue(("You must select a landing page").equals(validationMessage));

			setLandinPage(" Files (root folder)");
			Assert.assertTrue(checkLandinPage(siteName, "Files"));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void scenario3()
	{

		Reporter.log("Scenario 3: Select landing page for which user have no rights</br>");
		String siteName = null;
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		Reporter.log("1.Site should be created.");
		Reporter.log("2.Normal user should be added in site.");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :");

		Reporter.log("1.Login with site admin and go to admin page.</br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Reporter.log("2.Click on modules link from left panel.");
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("3.Select any module as site landing page and do give view permission to normal user for selected landing module.</br>");
		setLandinPage("Wiki");
		removeModulePermissionToUser(normalUserEmail, "Wiki", false, false);

		Reporter.log("4.Now login with normal user and go to above site.</br>");
		logout();
		bannerPageWeb = login(normalUserEmail, sysAdminPassword);

		Reporter.log("Expected result :</br>");
		Reporter.log("1.Normal user should be redirect to the first enable Module(True for all options).</br>");
		Assert.assertTrue(!checkLandinPage(siteName, "Wiki"));
	}

	private void scenario4()
	{
		Reporter.log("Scenario 4 : Things on site navigation page.</br>");
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		logout();
		login(sysAdminEmail, sysAdminPassword);
		try
		{
			String currentDir = System.getProperty("user.dir");
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName));
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :</br>");
		Reporter.log("1.Login with site admin and go to admin page.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2.Click on site navigation link from left panel.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("Expected result :</br>");

		Reporter.log("1.It should display Site navigation on top of the page as title.</br>");
		Assert.assertTrue(siteNavigationWeb.verifySiteNavigationModuleName(), "Module Name is Not Display.");

		Reporter.log("It should display two configuration options like</br>");
		Reporter.log("2.1 : Automatic site navigation : default selected option</br>");
		Assert.assertTrue(siteNavigationWeb.verifyAutomaticSiteNavigationOption(), "Automatic site navigation option is Not Display.");

		Reporter.log("2.2 : Custom site navigation</br>");
		Assert.assertTrue(siteNavigationWeb.verifyCustomSiteNavigationOption(), "Custom site navigation option is Not Display.");

		String actualTooltipValue = "This is the default site navigation which allows you to rename each module. Additionally, the navigation bar is connected to and will reflect the enabled modules.";

		Reporter.log("3. It should display tool tip for  Automatic site navigation property " + actualTooltipValue + "</br>");

		String uiAutomatictooltipValue = findVisibleElement(By.xpath("//*[@id='automaticSiteNavigation']//following::span[1][contains(@class,'wrapTooltip')]")).getAttribute("data-original-title");
		Assert.assertEquals(uiAutomatictooltipValue, actualTooltipValue);

		String actualTooltipValueCustome = "The custom site navigation option allows you to create your own site navigation. This enables you to rearrange the navigation order, add new menu items and containers and to remove modules completely. This option is 'disconnected' from the module settings and does not reflect the modules and settings that are enabled.";

		Reporter.log("4.It should display tool tip for Custom site navigation property  " + actualTooltipValueCustome + "</br>");

		String uiCustomtooltipValue = findVisibleElement(By.xpath("//*[@id='customSiteNavigation']//following::span[1][contains(@class,'wrapTooltip')]")).getAttribute("data-original-title");
		Assert.assertEquals(uiCustomtooltipValue, actualTooltipValueCustome);

		Reporter.log("5.When automatic site navigation option is selected than the modules which are enable from modules page only those modules should display.</br>");
		siteNavigationWeb.clickOnAutomaticSiteNavigation();
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Files"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Blog"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Tasks"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("People"));

		Reporter.log("6.If user have selected automatic site navigation from site navigation page than all the modules which are displaying on site navigation page should display on main menu.</br>");
		Reporter.log("7.Rename link should display in each module share menu and when click on that link rename modal should open and user should able to change module name from here.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyModuleRenameLink("Files"));
		Assert.assertTrue(siteNavigationWeb.verifyModuleRenameLink("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyModuleRenameLink("Blog"));
		Assert.assertTrue(siteNavigationWeb.verifyModuleRenameLink("Tasks"));
		Assert.assertTrue(siteNavigationWeb.verifyModuleRenameLink("People"));

		Reporter.log("8.When user create a new site than it should be created with default  Automatic site navigation option checked.</br>");
		Reporter.log("9.When user select custom site navigation by default no any modules or menu item should display and it should display A menu was not located. Click add to add a menu item. message.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		Assert.assertTrue(siteNavigationWeb.verifySaveButtononSiteNavPage());
		Assert.assertTrue(siteNavigationWeb.verifyPreviewButtononSiteNavPage());
		Assert.assertTrue(siteNavigationWeb.verifyCancelButtononSiteNavPage());

		Reporter.log("All Module Should be Display");
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Files"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Blog"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Tasks"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("People"));
	}

	private void scenario5()
	{
		Reporter.log("<b>Scenario 5 : Rename module name from site navigation page when automatic site navigation is selected.</b></br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :</br>");
		Reporter.log("1. Login as Admin user</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2. Go to site navigation – automatic site navigation.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnAutomaticSiteNavigation();

		Reporter.log("3. Click on the rename link from any module share menu.</br>");
		siteNavigationWeb.clickRenameModules("Files");

		Reporter.log("4.Change any module name and check the module name at main site menubar or at any place where module name is displaying.</br>");
		siteNavigationWeb.renameModule("Files New", "", "", "", "", "");
		siteNavigationWeb.clickRenameModalSaveButton();

		Reporter.log("Expected Result:");
		Reporter.log("1.System should display the new changed module name on the main site menubar.</br>");
		siteNavigationWeb.verifyNewModuleName("Files New");

		Reporter.log("2. Module name should be display in language which user has selected from settings page.</br>");
		Reporter.log("3.Module name must allow aposhtrophe symbol.</br>");
		Reporter.log("4.Click on the reset default names link from model system should set the new changed module names to default (the same name which set in system Admin - system vocabulary)</br>");
		siteNavigationWeb.resetDefaultNameLink();
		siteNavigationWeb.clickRenameModalSaveButton();

		siteNavigationWeb.verifyNewModuleName("Files");

	}

	private void scenario6()
	{
		Reporter.log("Scenario 6 : verify cancel button functionality.</br>");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps:</br>");
		Reporter.log("1. Login with site admin.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2. Go to site navigation – automatic site navigation.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnAutomaticSiteNavigation();

		Reporter.log("3. Click on the rename link from any module share menu</br>.");
		siteNavigationWeb.clickRenameModules("Files");
		siteNavigationWeb.renameModule("Files New", "", "", "", "", "");

		Reporter.log("4.Click on cancel button</br>");
		siteNavigationWeb.clickRenameModalCancelButton();

		Reporter.log("Expected:");
		Reporter.log("Model should be close without save any data.</br>");
		siteNavigationWeb.verifyNewModuleName("Files");
	}

	private void scenario7()
	{
		Reporter.log("Scenario 7 :  When custom site navigation option is selected.</br>");
		logout();
		login(sysAdminEmail, sysAdminPassword);
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName));
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and site admin, content admin and normal user should be added in site.</br>");

		Reporter.log("Steps :</br>");
		Reporter.log("1. Login with site admin and go to admin module.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2.Select site navigation from left panel.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

		Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
		siteNavigationWeb.addMenuItemWithExternal("Google", "https://www.google.com/", "New window", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();

		Reporter.log("4.Save the page.</br>");

		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("Expected result :</br>");
		Reporter.log("1.When user select custom site navigation, whatever modules selected from module page by default available on this page.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Files"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Blog"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Tasks"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Events"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("People"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Google"));

		Reporter.log("2.If user have permission for all the selected modules than it should also available on main site navaigation bar.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_FILES));
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI));
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG));
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS));
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS));
		Assert.assertTrue(siteNavigationWeb.verifyModuleName(SiteAdminLabels.SITEADMIN_MODULESPAGE_PEOPLE));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Google"));

		Reporter.log("3.If any module we enable or disable from the module page should not addedd or removed from the custom site navigation page.</br>");
		isDisableModulesFromSiteAdmin(siteName, false, SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI, SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS);
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Events"));

		Reporter.log("4.If once the site navigation page is saved with custom option and switch to the automatic option and save the page with automatic option and when again switch to the custom option the earlier settings should be retained.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnAutomaticSiteNavigation();
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		siteNavigationWeb.clickOnCustomSiteNavigation();
		siteNavigationWeb.clickSaveonSiteNavigationPage();
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Google"));

		Reporter.log("5.When we delete all the modules from the custom site navigation than it should display A menu was not located. Click add to add a menu item. message.</br>");
		siteNavigationWeb.deleteAllModulesInCustomModules();

		String menuWasNotLocated = siteNavigationWeb.findVisibleElement(By.id("navigationMessage")).getText();

		Assert.assertTrue(("Click Add to add a menu item.").equals(menuWasNotLocated));
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("6.If we save custom site navigation with no any module and switch to the automatic site navigation and save the page and when again switch to the custom site navigation it should display all the modules whic are available in automatic site navigation page.</br>");
		siteNavigationWeb.clickOnAutomaticSiteNavigation();
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		siteNavigationWeb.clickOnCustomSiteNavigation();
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Files"));
		// Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Wiki"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Blog"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Tasks"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("Q&A"));
		Assert.assertTrue(siteNavigationWeb.verifyNewModuleName("People"));

	}

	private void scenario8()
	{
		Reporter.log("Scenario 8 : When custom site navigation option is selected.");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and site admin, content admin and normal user should be added in site.</br>");

		Reporter.log("Steps :</br>");
		Reporter.log("1. Login with site admin and go to admin module.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2.Go to above site > admin module > site navigation.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation and do not add any menu items.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		siteNavigationWeb.deleteAllModulesInCustomModules();
		Reporter.log("4.Save the page.</br>");
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.All users should redirect to site landing module and on site navigation bar only Admin module should dislay to internal admin and site admin. Content admin and normal user should able to see any module on  site navigation bar.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyModuleName("Admin"));
		Reporter.log("2. If normal user have not permission for module which is selected as site landing page than it should redirect to next enable module in which normal user have view permission.</br>");
		logout();
		bannerPageWeb = login(normalUserEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);

		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		removeAllModulePermissionToUser(normalUserEmail, false, false);
		Reporter.log("3.If only one module is enable in site and set as landing module and normal user do not have permission for that module than it should display You do not have access to any modules message when user enters in this site.</br>");

		logout();
		bannerPageWeb = login(normalUserEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		Assert.assertTrue(dashboardPageWeb.isDisplayed(By.xpath("//*[normalize-space(text())='No access to modules']")));
	}

	private void scenario9()
	{
		Reporter.log("Scenario 8 : Site navigation – custom site navigation – add button.</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and some modules should be enable.</br>");

		Reporter.log("Steps :</br>");
		Reporter.log("1. Login with site admin and go to admin module.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("2.  From left panel select site navgation page.</br>");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Go to site navigation – custom site navigation – add button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		siteNavigationWeb.clickAddButtoninCustomnavigation();

		Reporter.log("Expected :</br>");
		Reporter.log("1.It should display two options 1. menu item and 2. container.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyMenuItemInCustomNav(menuItem));
		Assert.assertTrue(siteNavigationWeb.verifyMenuItemInCustomNav(container));

		Reporter.log("2.When click on menu item it should open add menu item modal with three tabs  1.browse 2.External 3.Search.</br>");
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);
		Assert.assertTrue(siteNavigationWeb.verifyAddItemMenuTabs("Browse"));
		Assert.assertTrue(siteNavigationWeb.verifyAddItemMenuTabs("External"));
		Assert.assertTrue(siteNavigationWeb.verifyAddItemMenuTabs("Search"));

		Reporter.log("3.In the Browse tab Site drop down should default selected to the current site for all of the options, except Site. For search tab Site drop down should default selected to the All sites</br>");
		siteNavigationWeb.gotoAddItemBrowseTab();
		Assert.assertTrue(siteNavigationWeb.verifySiteDropdownInBrowsTab());
		siteNavigationWeb.clickCancelButtonInAddMenuItemModel();

		Reporter.log("4.When click on container it should open add container modal with cancel and add button.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(container);

		Assert.assertTrue(siteNavigationWeb.verifyContainerModel());
		Assert.assertTrue(siteNavigationWeb.verifyAddButtonInConatinerModel());
		Assert.assertTrue(siteNavigationWeb.verifyCancelButtonInConatinerModel());

		siteNavigationWeb.clickCancelButtonInAddContainerModel();

	}

	private void scenario10Case1()
	{
		Reporter.log("Scenario 9 : Site navigation – custom site navigation – add Site menu item</br>");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName(siteName1))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");

			siteNavigationWeb.addMenuItemWithBrowseTab("Site", siteName1, "", "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("Expected :</br>");
		Reporter.log("1.Site menu item should be created and it should display in site navigation bar. </br>");
		Reporter.log("2.This menu item should be display to all user who have permission for that site. </br>");
		Reporter.log("3.The users who do not have permission for site than it should not visible in site navigation bar. </br>");

		checkWithTwoUsersVisibleTextInSite(siteName, "", siteName1, false);

	}

	private void scenario10Case2()
	{
		Reporter.log("Case 2 : Search drop down filter options.");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Pre condition :");
		Reporter.log("1.Some sites should be exist with all the status like active, read only, preparation and archived.");
		Reporter.log("2.Internal admin, site admin, content admin and normal user should be added in some sites.");
		Reporter.log("3.Site category should be added in some sites.");

		addCategiesAndAddInSite(siteName1, "AutomatioDemo");
		Reporter.log("4.Some sites should be marked as favorites.");

		Reporter.log("Steps :- ");
		Reporter.log("1. Log in with site admin go to any site.");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);

		Reporter.log("2. Go to admin module – site navigation page.");
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("3. Click on custom site navigation.");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

		Reporter.log("5. Click on browse tab.");
		siteNavigationWeb.gotoAddItemBrowseTab();

		Reporter.log("6. Select “site” from link to drop down.");
		Reporter.log("7. Click on search drop down menu.");
		siteNavigationWeb.clickonFilterButtonOnAddMenuItem();

		Reporter.log("Expected :");
		Reporter.log("1.When click on search drop down filter menu should be opened with site status and category drill down menu and also with All sites, recent and favourites options also with Clear filters link.");
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Status"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("All sites"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Recent"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Favourites"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Clear filters"));

		Reporter.log("2.Site status drill down should display with Active, read only and preparation options and these options depends on user role and permission.");
		siteNavigationWeb.clickOnStatusInBrowsTab();
		Assert.assertTrue(siteNavigationWeb.verifySubFilterOptionInBrowsTab("Active"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Read only"));
		Assert.assertTrue(siteNavigationWeb.verifyFilterOptionInBrowsTab("Preparation"));

		Reporter.log("3.Category drill down should display all sites categories ( in acceding order ) which are added in sites and these site categories also depends on user permission.");
		Reporter.log("4.If user is not participated in any of site than site status and category filters should be hide.");

		siteNavigationWeb.clickOnCloseButtonAddMenu();
	}

	private void scenario10Case3()
	{
		Reporter.log("Case 3 : Filter sites with filter options.");
		Reporter.log("1.Some sites should be exist with all the status like active, read only, preparation and archived.");
		Reporter.log("2.Internal admin, site admin, content admin and normal user should be added in some sites.");
		Reporter.log("3.Site category should be added in some sites.");
		Reporter.log("4.Some sites should be marked as favorites.");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :");
		Reporter.log("1. Log in with site admin go to any site.");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);

		Reporter.log("2. Go to admin module – site navigation page.");
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("3. Click on custom site navigation.");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4. Click on add button and select menu item.");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

		Reporter.log("5. Click on browse tab.");
		siteNavigationWeb.clickonFilterButtonOnAddMenuItem();
		siteNavigationWeb.clickOnStatusInBrowsTab();

		Reporter.log("6. Select “site” from link to drop down.");
		Reporter.log("7. Click on filter drop down and select any filter option.");
		siteNavigationWeb.clickonFilterButtonOnAddMenuItem();

		Reporter.log("Expected :");
		Reporter.log("1. Sites should be filter according to selected filter.");

		Reporter.log("For e.g");
		Reporter.log("-If Status selected as Active than only Active sites should be displayed.");
		siteNavigationWeb.clickonFiltersonBrowsTab("Status", "Active");
		siteNavigationWeb.verifySiteIsBrowseTab(siteName);

		Reporter.log("-If All sites than all sites should be displayed as per user permission.");
		Reporter.log("-If Recent  than recently accessed site should be displayed and recently accessed site should be display at the top in list.");
		siteNavigationWeb.clickonFiltersonBrowsTab("", "Recent");
		siteNavigationWeb.verifySiteIsBrowseTab(siteName);

		Reporter.log("-If favorites than only favorited sites should be display.");
		siteNavigationWeb.clickonFiltersonBrowsTab("", "Favourites");
		siteNavigationWeb.verifySiteIsBrowseTab(siteName);

		siteNavigationWeb.clickOnCloseButtonAddMenu();
	}

	private void scenario10Case4()
	{
		Reporter.log("Case 4 : Search site with default filter option “All sites”");

		try
		{

			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Pre condition : ");
		Reporter.log("1.Some sites should be exist with all the status like active, read only, preparation and archived.");
		Reporter.log("2.Internal admin, site admin, content admin and normal user should be added in some sites.");
		Reporter.log("3.Site category should be added in some sites.");
		Reporter.log("4.Some sites should be marked as favorites.");

		Reporter.log("Steps :");
		Reporter.log("1. Log in with site admin go to any site.");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);

		Reporter.log("2. Go to admin module – site navigation page.");
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("3. Click on custom site navigation.");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.");
		siteNavigationWeb.clickAddButtoninCustomnavigation();

		Reporter.log("5. Click on browse tab.");
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);
		siteNavigationWeb.clickonFilterButtonOnAddMenuItem();
		siteNavigationWeb.clickOnStatusInBrowsTab();

		Reporter.log("6. Select “site” from link to drop down.");
		Reporter.log("7. Enter search text in search text box.");
		siteNavigationWeb.enterTextOnSearchBrowseTab(siteName);

		Reporter.log("Expected : ");
		Reporter.log("1. When start typing in search text it should start applying and when removed, it should revert back to the Default.");
		Reporter.log("2. Related sites should be filter out and displayed in list.");
		Assert.assertTrue(siteNavigationWeb.verifySiteIsBrowseTab(siteName));

		Reporter.log("3. If there is no site present related to entered text than “No results found” message should be displayed.");
		siteNavigationWeb.enterTextOnSearchBrowseTab("123456");
		Assert.assertTrue(siteNavigationWeb.verifySiteIsBrowseTab("No results found"));

		siteNavigationWeb.clickOnCloseButtonAddMenu();
	}

	private void scenario10Case5()
	{
		Reporter.log("Case 5 : Search site with filter option other than default.");

		try
		{
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Steps :");
		Reporter.log("1. Log in with site admin go to any site.");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);

		Reporter.log("2. Go to admin module – site navigation page.");
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		Reporter.log("3. Click on custom site navigation.");
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.");
		siteNavigationWeb.clickAddButtoninCustomnavigation();

		Reporter.log("5. Click on browse tab.");
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);
		siteNavigationWeb.clickonFilterButtonOnAddMenuItem();
		siteNavigationWeb.clickOnStatusInBrowsTab();

		Reporter.log("6. Select “site” from link to drop down.");
		Reporter.log("7. Click on filter drop down and select any filter option.");
		Reporter.log("8. Enter search text in search text box.");
		siteNavigationWeb.enterTextOnSearchBrowseTab(siteName);

		Reporter.log("Expected :");
		Reporter.log("1. Result should be displayed in combination of both filter and search text.");
		Reporter.log("For e.g  : If  filter option selected as “Active” status and  also enter a search text than result should be displayed club of search text and all active sites.");
		siteNavigationWeb.clickonFiltersonBrowsTab("Status", "Active");

		Assert.assertTrue(siteNavigationWeb.verifySiteIsBrowseTab(siteName));
		Reporter.log("2. If user selected any filter option and also entered some search text and When remove search text, the filter option should revert back to the selected option.");

		siteNavigationWeb.clickOnCloseButtonAddMenu();
	}

	private void scenario11()
	{
		Reporter.log("Scenario 10 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Module Option</br>");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName("Files"))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5. Click on browse tab and select “Module” from link to drop down.</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Module", siteName, "Files", "", "");

			Reporter.log("6.Add any module as menu item and save the page.</br>");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("Expected :</br>");
		Reporter.log("1.Module menu item should be created and it should display in site navigation bar.</br>");
		Reporter.log("2.This menu item should be display to all user who have permission for that module.</br>");
		Reporter.log("3.The users who do not have permission for module than it should not visible in site navigation bar.</br>");
		Reporter.log("4.Module list should be display as per selected site on modal.</br>");
		Reporter.log("5.When user click on module menu from site navigation bar should be redirected to that module page.</br>");
		checkWithTwoUsersVisibleTextInSite(siteName, "", "Files", false);
	}

	private void scenario12() throws IOException, InterruptedException
	{
		Reporter.log("Scenario 11 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With File/Folders Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		addFilesinSite(siteName, doc1);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName(doc1))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Files/Folders", siteName, doc1, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("Expected :</br>");
		Reporter.log("1.Module menu item should be created and it should display in site navigation bar.</br>");
		Reporter.log("2.This menu item should be display to all user who have permission for that module.</br>");
		Reporter.log("3.The users who do not have permission for file or folder than it should not visible in site navigation bar.</br>");
		Reporter.log("4.When user click on file should be open in viewer and if  click on folder should be redirected to that folder.</br>");

		checkWithTwoUsersVisibleTextInSite(siteName1, "", doc1, false);
	}

	private void scenario13()
	{
		Reporter.log("Scenario 11 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Wiki page Option </br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName1));
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String wikiTitle = "Wiki For Automation";
		addWiki(siteName, wikiTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName(wikiTitle))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag");
			siteNavigationWeb.addMenuItemWithBrowseTab("Wiki page", siteName, wikiTitle, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("Expected :</br>");
		Reporter.log("1.Module menu item should be created and it should display in site navigation bar.</br>");
		Reporter.log("2.This menu item should be display to all user who have permission for that module.</br>");
		Reporter.log("3.The users who do not have permission for wiki page than it should not visible in site navigation bar.</br>");
		Reporter.log("4.When user click on wiki page should be redirected to that wiki page.</br>");

		checkWithTwoUsersVisibleTextInSite(siteName1, "", wikiTitle, false);
	}

	private void scenario14Case1()
	{
		Reporter.log("Scenario 13 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Blog post Option</br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName1));
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		addBlog(siteName, blogTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName(blogTitle))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Blog post", siteName, blogTitle, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", blogTitle, false);
	}

	private void scenario14Case2()
	{
		Reporter.log("Scenario 13 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Blog category Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		addBlogCategory(siteName, blogCategory);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		if (!siteNavigationWeb.verifyModuleName(blogCategory))
		{
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Blog category", siteName, blogCategory, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", blogCategory, false);
	}

	private void scenario14Case3()
	{
		Reporter.log("Scenario 14 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Task Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String taskTitle = "Task For Automation";
		addTask(siteName, taskTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		if (!siteNavigationWeb.verifyModuleName(taskTitle))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Task", siteName, taskTitle, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", taskTitle, false);
	}

	private void scenario14Case4()
	{
		Reporter.log("Scenario 15 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Task list Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String taskListName = "List1";
		addTaskList(siteName, taskListName);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(taskListName))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Task list", siteName, taskListName, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", taskListName, false);
	}

	private void scenario14Case5()
	{
		Reporter.log("Scenario 15 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Event Category Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String eventCatName = "List1";
		addEventCategory(siteName, eventCatName);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(eventCatName))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Event category", siteName, eventCatName, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", eventCatName, false);
	}

	private void scenario14Case6()
	{
		Reporter.log("Scenario 15 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Event Option</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName1.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String eventName = "Event For Automation";
		addEvent(siteName, eventName);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(eventName))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Event", siteName, eventName, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", eventName, false);
	}

	private void scenario14Case7()
	{
		Reporter.log("Scenario 16 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Person Option</br>");

		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName2));
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(siteAdminEmail))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Person", siteName, "", "", siteAdminName);
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", siteAdminName, false);
	}

	private void scenario14Case8()
	{
		Reporter.log("Scenario 16 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Isheet view Option</br>");

		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName2));
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(siteAdminEmail))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Person", siteName, "", "", siteAdminName);
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", siteAdminName, false);
	}

	private void scenario14Case9()
	{
		Reporter.log("Scenario 16 : Site navigation – custom site navigation – add menu item.<b> For browse tab </b> With Isheet item Option</br>");

		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Reporter.log("Pre Condition");
		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//" + jsonFileName2));
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(siteAdminEmail))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Person", siteName, "", "", siteAdminName);
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		checkWithTwoUsersVisibleTextInSite(siteName1, "", siteAdminName, false);
	}

	private void scenario15()
	{
		Reporter.log("Scenario 14 : Site navigation – custom site navigation – add menu item from <b> External Tab </b></br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName("Google"))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithExternal("Google", "https://www.google.com/", "New window", true);
			siteNavigationWeb.clickAddButtonInAddMenuItemModel();

			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}
		Reporter.log("Expected :</br>");
		Reporter.log("1.External type menu item should be created and it should display in site navigation bar.</br>");
		Reporter.log("2.All site user should be able to see external type menu item and when click on that link should be redirected to proper page in same window or new window based on configuration.</br>");
		checkWithTwoUsersVisibleTextInSite(siteName1, "", "Google", false);

		Reporter.log("3.If user is trying add external type menu item with out passing title than it should display validation message required.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();
		siteNavigationWeb.clickOnCustomSiteNavigation();
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

		siteNavigationWeb.addMenuItemWithExternal("", "https://www.google.com/", "New window", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();

		siteNavigationWeb.checkTitleValidationMessageinItemContainer("title", "(Required)");
		siteNavigationWeb.clickCancelButtonInAddMenuItemModel();

		Reporter.log("4.If user is trying add external type menu item with out passing URL than it should display validation message required.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);
		siteNavigationWeb.addMenuItemWithExternal("Google", "", "New window", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();

		siteNavigationWeb.checkTitleValidationMessageinItemContainer("title", "(Required)");
		siteNavigationWeb.clickCancelButtonInAddMenuItemModel();

		Reporter.log("5.If user is trying add external type menu item with passing invalid URL than it should display validation message Enter a valid URL.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);
		siteNavigationWeb.addMenuItemWithExternal("Google", "www.google.com/", "New window", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();

		siteNavigationWeb.checkTitleValidationMessageinItemContainer("title", "Enter a valid URL");

		Reporter.log("</br>");
		siteNavigationWeb.clickOnCloseButtonAddMenu();
	}

	private void scenario16()
	{
		Reporter.log("Scenario 15 : Site navigation – custom site navigation – add menu item from search tab.</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(doc1))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
			siteNavigationWeb.addMenuItemWithSearchTab(siteName, doc1Name);
			siteNavigationWeb.clickAddButtonInAddMenuItemModel();

			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("Expected : </br>");
		Reporter.log("1.Menu item should be created and it should display in site navigation bar.</br>");
		Reporter.log("2.User who have permission for that menu item should be able to see in site navigation bar.</br>");
		Reporter.log("3.User who do not have permission for that menu item should not be visible to that user.</br>");
		Reporter.log("4.When click on menu item from navigation bar the redirection should be proper.</br>");

		checkWithTwoUsersVisibleTextInSite(siteName1, "", doc1, false);
	}

	private void scenario17()
	{
		Reporter.log("Scenario 16 : Site navigation – custom site navigation – edit menu item.</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName3.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		addBlog(siteName, blogTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(doc1))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5. Click on any tab from add menu modal.</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Blog post", siteName, blogTitle, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("7.Now click on edit details from share menu of any menu item.</br>");
		siteNavigationWeb.clickEditIteminCustomNavigation(blogTitle);

		Reporter.log("8.Change the title and tool tip and save.</br>");
		siteNavigationWeb.addGeneralTabValueinEditIteminCustomNavigation(renameBlogTitle, renameBlogTitle, "", "", "", false);
		siteNavigationWeb.editIteminCustomNavigationSaveBtn();

		Reporter.log("Expected : </br>");
		Reporter.log("1.New changed title and tool tip should display on page.</br>");
		siteNavigationWeb.verifyModuleName(renameBlogTitle);
		Reporter.log("2.New changed title and tool tip should be also reflected in site navigation bar.</br>");
		Reporter.log("3.If user is trying edit menu item with out passing title than it should display validation message required.</br>");
		siteNavigationWeb.addGeneralTabValueinEditIteminCustomNavigation("", renameBlogTitle, "", "", "", false);
		siteNavigationWeb.editIteminCustomNavigationSaveBtn();

		Assert.assertTrue(siteNavigationWeb.checkEditValidationMessageinContainer("Required)"));
	}

	private void scenario18()
	{
		Reporter.log("Scenario 17 : Site navigation – custom site navigation – delete menu item.</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName3.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		addBlog(siteName, blogTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();
		if (!siteNavigationWeb.verifyModuleName(blogTitle))
		{
			Reporter.log("4.Click on add button and select menu item.</br>");
			siteNavigationWeb.clickAddButtoninCustomnavigation();
			siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

			Reporter.log("5. Click on any tab from add menu modal.</br>");
			siteNavigationWeb.addMenuItemWithBrowseTab("Blog post", siteName1, blogTitle, "", "");
			siteNavigationWeb.clickSaveonSiteNavigationPage();
		}

		Reporter.log("7.Now click on delete from share menu of any menu item and save the page.</br>");
		siteNavigationWeb.clickDeleteIteminCustomNavigation(blogTitle);

		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.menu item should be deleted and it should be also deleted from site navigation bar.</br>");

		Assert.assertTrue(!siteNavigationWeb.verifyModuleName(blogTitle));

	}

	private void scenario19()
	{
		Reporter.log("Scenario 18 : Add container</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String containerName = "Main Container";
		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(container);

		Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
		siteNavigationWeb.fillDataAddContainerModel(containerName, "1", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.Container should be added and it should not display in site navigation bar because no any menu items added in container.</br>");
		Assert.assertTrue(!siteNavigationWeb.verifyModuleName(containerName));

		Reporter.log("2.If there is atleast one menu item added in container than container should be display in site navligation bar.</br>");
		Reporter.log("3.If user is trying add container with out passing title than it should display validation message required.</br>");
		siteNavigationWeb.fillDataAddContainerModel("", "1", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();

		siteNavigationWeb.checkAddValidationMessageinContainerModel("(Required)");
		checkWithTwoUsersVisibleTextInSite(siteName, "Main Container", "", false);

		siteNavigationWeb.clickOnCloseButtonAddContainerModel();
	}

	private void scenario20()
	{
		Reporter.log("Scenario 19 : Edit container</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String containerName = "Main Container";
		String renameContainerName = "Rename Main Container";
		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(container);

		Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
		siteNavigationWeb.fillDataAddContainerModel(containerName, "1", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		siteNavigationWeb.clickEditIteminCustomNavigation(containerName);
		siteNavigationWeb.addGeneralTabValueinEditIteminCustomNavigation(renameContainerName, renameContainerName, "", "", "", false);
		siteNavigationWeb.editIteminCustomNavigationSaveBtn();

		Reporter.log("Expected :</br>");
		Reporter.log("1.New changed title and tool tip should display on page</br>");
		Assert.assertTrue(!siteNavigationWeb.verifyModuleName(containerName));

		Reporter.log("2.New changed title and tool tip should be also reflected in site navigation bar.</br>");
		Reporter.log("3.If user is trying edit container with out passing title than it should display validation message required.</br>");
		siteNavigationWeb.clickEditIteminCustomNavigation(containerName);
		siteNavigationWeb.addGeneralTabValueinEditIteminCustomNavigation("", renameContainerName, "", "", "", false);
		siteNavigationWeb.editIteminCustomNavigationSaveBtn();

		siteNavigationWeb.checkAddValidationMessageinContainerModel("(Required)");
		checkWithTwoUsersVisibleTextInSite(siteName, "Main Container", "", false);
	}

	private void scenario21()
	{
		Reporter.log("Scenario 20 : Delete container</br>");

		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :</br>");
		Reporter.log("1.Site should be created.</br>");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(container);

		Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
		siteNavigationWeb.fillDataAddContainerModel(containerName, "1", true);
		siteNavigationWeb.clickAddButtonInAddMenuItemModel();
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		siteNavigationWeb.clickDeleteIteminCustomNavigation(containerName);
		siteNavigationWeb.clickSaveonSiteNavigationPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.Container should be deleted and it should be also deleted from site navigation bar.</br>");
		Assert.assertTrue(!siteNavigationWeb.verifyModuleName(containerName));
	}

	private void scenario22()
	{
		Reporter.log("Scenario 21 : Preview functionality. </br>");
		try
		{
			String currentDir = System.getProperty("user.dir");
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName2.trim()));
			siteName1 = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String wikiTitle = "Wiki For Automation";
		addWiki(siteName, wikiTitle);

		Reporter.log("Case 1. Add Site menu item</br>");

		Reporter.log("Pe condition :");
		Reporter.log("1.Site should be created.");
		Reporter.log("2.Select admin module – site navigation from left panel.</br>");
		logout();
		bannerPageWeb = login(siteAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName1);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		siteNavigationWeb = adminPageWeb.clickSiteNavigationInLeftPanel();

		Reporter.log("3.Select custom site navigation radio button.</br>");
		siteNavigationWeb.clickOnCustomSiteNavigation();

		Reporter.log("4.Click on add button and select menu item.</br>");
		siteNavigationWeb.clickAddButtoninCustomnavigation();
		siteNavigationWeb.clickonSubMenuofCustomNavigation(menuItem);

		Reporter.log("5.Go to Add Menu Item With Site Tag</br>");
		siteNavigationWeb.addMenuItemWithBrowseTab("Wiki page", siteName, wikiTitle, "", "");

		siteNavigationWeb.clickonPreviewButton();

		Reporter.log("Expected :</br>");
		Reporter.log("1. User should be switched to preview mode and You are in preview mode label message should display.</br>");
		Assert.assertTrue(siteNavigationWeb.verifyPreviewModeMsg());
		Assert.assertTrue(bannerPageWeb.verifyModuleName(wikiTitle));

		Reporter.log("2. If user click on save button or cancel preview link than preview mode should be closed.</br>");
		siteNavigationWeb.clickonCancelPreviewButton();
		Assert.assertTrue(!siteNavigationWeb.verifyPreviewModeMsg());
	}

	private void createAllSitesWithAllModes()
	{
		try
		{
			// logout();
			login(sysAdminEmail, sysAdminPassword);
			// For Preparation Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_PreparationSite.json"));
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_PreparationSite.json".trim()));
			preparationSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setSiteStatus(preparationSiteName, "Preparation");

			// For Readonly Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_ReadOnlySite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_ReadOnlySite.json".trim()));
			readOnlySiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setSiteStatus(readOnlySiteName, "Read only");

			// For Passcode Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_PasscodeSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_PasscodeSite.json".trim()));
			passcodeSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(passcodeSiteName, "Passcode");

			// For Password Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_PasswordSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_PasswordSite.json".trim()));
			passwordSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(passwordSiteName, "Password");

			// For Tearms And Condition Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_TearmsConditionSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_TearmsConditionSite.json".trim()));
			tearmsSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(tearmsSiteName, "Tearms");

			// For IP Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_IPSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_IPSite.json".trim()));
			iPSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(iPSiteName, "IP");

			// For Suspended User
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//siteNavigation//preConfiguration_SuspendedSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + "preConfiguration_SuspendedSite.json".trim()));
			suspendedSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			suspendUserFromSite(suspendedSiteName, "Site Admin");

			// Favourite Site

			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//siteNavigation//" + jsonFileName.trim()));
			String siteNames = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			clickFavouriteOnSitewithDashboard(siteNames);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setSiteStatus(String siteNames, String sitestatus)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AdminGeneralWeb adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.selectStatus(sitestatus);
		adminGeneralWeb.clickOnSave();

	}

	private void setDiffStatus(String siteNames, String sitestatus)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();

		if (sitestatus.equals("Password"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setPasswordEnabled(true);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("Passcode"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setPasswordEnabled(true);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("IP"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setrestrictedIps(ip);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("Terms"))
		{
			AdminTermsAndConditionsWeb adminTermsAndConditionsWeb = adminPageWeb.clickTermsAndConditionsInLeftPanel();
			adminTermsAndConditionsWeb.enableSiteTermsAndCondition(true);
			adminTermsAndConditionsWeb.setTermsAndConditionText("This Is Automation Demo");
			adminTermsAndConditionsWeb.clickOnSave();
		}

	}

	private void suspendUserFromSite(String siteNames, String userName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AddUserWeb addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.selectAllUsers();
		addUserWeb.clickOnSuspend();
	}

	private void clickFavouriteOnSitewithDashboard(String siteNames)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		// dashboardPageWeb.clickOnFavouriteSiteOnDashboard(siteNames);
	}

	private void setLandinPage(String moduleName)
	{
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		modulePageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		// modulePageWeb.enableSpecificModules(moduleName, "Files");
		modulePageWeb.setSelectDefaultLandingPage(moduleName);
		adminPageWeb = modulePageWeb.clickOnSaveButton();
	}

	private boolean checkLandinPage(String siteName, String moduleName)
	{
		dashboardPageWeb = dashboardPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteName);
		String moduleNameFull = "module" + moduleName + " active";
		if (isDisplayed(By.xpath("//*[@id='collaborateCustomMessageModal' and contains(@class,'in')]")))
		{
			findClickableElement(By.id("collaborateMessageOkButton")).click();
		}
		if (isDisplayed(By.xpath("//*[@id='file_module_advanceSearch_modal' and contains(@class,'fade in')]")))
		{
			findClickableElement(By.id("file_module_advanceSearch_modal_cancel")).click();
		}
		return isDisplayed(By.xpath(".//li[@class='" + moduleNameFull + "']"));
	}

	private void removeModulePermissionToUser(String userName, String moduleName, boolean isView, boolean isEdit)
	{
		AddUserWeb addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userName);
		Map<String, Boolean> childMap = new HashMap<String, Boolean>();
		childMap.put("View", isView);
		childMap.put("Edit", isEdit);

		addUserWeb.setModulePermission(moduleName, childMap);
		addUserWeb.clickSaveInSetPermissions();
	}

	private void removeAllModulePermissionToUser(String userName, boolean isView, boolean isEdit)
	{
		AddUserWeb addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userName);
		Map<String, Boolean> childMap = new HashMap<String, Boolean>();
		childMap.put("View", isView);
		childMap.put("Edit", isEdit);

		addUserWeb.setModulePermission("Home", childMap);
		addUserWeb.setModulePermission("Activity", childMap);
		addUserWeb.setModulePermission("Files", childMap);
		addUserWeb.setModulePermission("Wiki", childMap);
		addUserWeb.setModulePermission("Blog", childMap);
		addUserWeb.setModulePermission("Events", childMap);
		addUserWeb.setModulePermission("Tasks", childMap);
		addUserWeb.clickSaveInSetPermissions();
	}

	private void addCategiesAndAddInSite(String siteNames, String categoryName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AdminGeneralWeb adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.addNewCategory(categoryName);
		if (adminGeneralWeb.isDisplayed(
				By.xpath("//*[@id='createSiteModal_addNewSiteCategory_pID' and @style='display: block;']")))
		{
			adminGeneralWeb.selectCategories(categoryName);
		}

	}

	private void addFilesinSite(String siteNames, String... fileName) throws IOException, InterruptedException
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		DocumentWeb documentWebPage = dashboardPageWeb.gotoFileModule();
		for (String file : fileName)
		{
			if (!documentWebPage.verifyDocumentUploaded(file))
			{
				documentWebPage.selectItemFromUpload(files);
				DocumentAddDataPage addDoc = new DocumentAddDataPage();
				addDoc.setFileuploadpath(file);
				try
				{
					documentWebPage.addFile(addDoc, null);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				documentWebPage.clickAddInAddFileModal();
			}
		}

	}

	private void addWiki(String siteNames, String... wikiName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		WikiWeb wikiWeb = dashboardPageWeb.gotoWikiModule();
		for (String wikiTitle : wikiName)
		{
			if (!wikiWeb.verifyWikiFromLeftPanel(wikiTitle))
			{
				wikiWeb.clickOnAddWiki();
				wikiWeb.setTitle(wikiTitle);
				wikiWeb.sendTextInCkContetTextBoxInAddWikiPage("This is For Automatio Demo");
				wikiWeb.saveWiki();
			}
		}

	}

	private void addBlog(String siteNames, String blogTitle)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		BlogWeb blogWeb = dashboardPageWeb.gotoBlogModule();
		if (!blogWeb.verifyBlogInBlogList(blogTitle))
		{
			Map<String, String> blogData = new LinkedHashMap<>();
			blogData.put("Title", blogTitle);
			blogData.put("content", "Blog Content");
			blogData.put("Tags", "Tag1");
			blogData.put("Category", "Default");

			blogWeb.addBlog(blogData);
		}

	}

	private void addBlogCategory(String siteNames, String blogCategory)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AdminBlogWeb adminBlogWeb = adminPageWeb.clickOnBlogInLeftPanel();
		adminBlogWeb.verifyCategoryList();
		if (!adminBlogWeb.verifyCategory(blogCategory))
		{
			adminBlogWeb.addCategory(blogCategory);
			adminBlogWeb.clickOnSave();
		}

	}

	private void addTask(String siteNames, String taskTitle)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		TasksWeb taskPage = dashboardPageWeb.gotoTaskModule();
		if (!taskPage.verifyTaskVisibility(taskTitle))
		{
			taskPage.clickHeaderAddButton();
			taskPage.setTaskTitle(taskTitle);
			taskPage.setTaskDueDate("04 Dec 2050");
			taskPage.clickOnAddTaskButtonInModel();
		}
	}

	private void addTaskList(String siteNames, String taskListName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AdminTaskPageWeb adminTaskPageWeb = adminPageWeb.clickOnTaskInLeftPanel();
		if (!adminTaskPageWeb.verifyTaskListVisibility(taskListName))
		{
			adminTaskPageWeb.enterTaskListName(taskListName);
		}
	}

	private void addEventCategory(String siteNames, String eventCategoryName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		AdminEventWeb adminEventPageWeb = adminPageWeb.clickEventsInLeftPanel();
		if (!adminEventPageWeb.verifyCategoryInCategoriesList(eventCategoryName))
		{
			adminEventPageWeb.addCategory(eventCategoryName);
		}
	}

	private void addEvent(String siteNames, String eventTitle)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);
		EventsWeb eventWeb = dashboardPageWeb.gotoEventModule();
		if (!eventWeb.verifyEventInEventList(eventTitle))
		{
			eventWeb.addEvent(eventTitle);
		}
	}

	public void checkWithTwoUsersVisibleTextInSite(String siteNames, String rootContainer, String checkMenuItem, boolean isClick)
	{
		logout();
		bannerPageWeb = login(normalUserEmail, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardPageWeb.searchSite(siteNames);

		if (!("").equals(rootContainer))
		{
			Assert.assertTrue(bannerPageWeb.verifyModuleName(rootContainer));
		}

		if (isClick)
		{
			bannerPageWeb.clickContainer(rootContainer);

			if (checkMenuItem != null || ("").equals(checkMenuItem))
			{
				Assert.assertTrue(bannerPageWeb.verifySubMenuItem(rootContainer, checkMenuItem));
			}
		}
		else
		{
			Assert.assertTrue(dashboardPageWeb.verifyModuleName(checkMenuItem));
		}

		logout();
		bannerPageWeb = login(externalUserNotAddinSite, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.enterOnlySiteName(siteNames);
		Assert.assertTrue(!dashboardPageWeb.verifySiteName(siteNames));
	}

	private void isDisableModulesFromSiteAdmin(String siteName, boolean isEnable, String... moduleName)
	{
		adminPageWeb = dashboardPageWeb.gotoAdminModule();
		ModulesPageWeb modulesPageWeb = adminPageWeb.clickModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(isEnable, moduleName);
		modulesPageWeb.clickOnSaveButton();
	}

}
