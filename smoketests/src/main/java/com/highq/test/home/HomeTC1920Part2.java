package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author tejash.trivedi
 */
public class HomeTC1920Part2 extends BannerPageWeb
{
	WebDriver driver;

	String aspAdminEmail = "auto.user@highq.com";
	String siteAdminEmail = "site.admin1920@highq.com";
	String newPassword = "Pa&&worD123";
	String[] siteName = {"HomeTC1920 - Site1", "HomeTC1920 - Site2"};
	String[] userNames = {"site.admin1920", "system.admin1920"};
	String domain = "highq.com";
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;
	String kiraInstanceUrl = "https://preview.app.kirasystems.com/platform-api/v1";
	String kiraToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzIjoiQ29LN2FYZURXS1hQUkIwVlI5WmdjTEMwIiwiZiI6MjJ9.VsfKrbDzYWw2ridOF-PYSoyTxe80V03sYCAuUAofqeo";

	String analysisOption1 = "ON";
	String precon = "<b> <font color='black' size='2'>Pre-condition</font></b>";
	String steps = "<b> <font color='black' size='2'>Steps:</font></b>";
	String expected = "<b> <font color='black' size='2'>Expected:</font></b>";
	String docAnalysis = "Document analysis";
	String option1 = "Enabled, default ON in every site";
	String optionTrue = "TRUE";
	String optionFalse = "FALSE";
	Map<String, Object> addDataInVisualisation = new LinkedHashMap<>();
	String orgType = "Internal";
	String userRole = "site admin";

	AdminSecurityPage adminSecurityWeb;
	ConfigurationData configurationData = new ConfigurationData();
	DashboardPage dashboardWeb;
	BannerPage bannerPageWeb;
	AspConfigurationPage aspConfigurationPage;
	HomePage homeWeb;
	AspAdminPage aspAdminWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsPage;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	ModulesPage modulesPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void HomeTC1920TestCase() throws Exception
	{
		preconfiguration();
		scenario2();
	}

	/**
	 * @throws Exception
	 */
	public void preconfiguration() throws Exception
	{
		Reporter.log("<font color='black'><B> Scenario 2: Check options displaying in Data Visualisation” panel in Source drop down field.</B></font>");

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(aspAdminEmail, newPassword);

		for (int i = 0; i < siteName.length; i++)
		{
			Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
			Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
			permissionOfOrg.put(orgType, true);
			orgData.put(domain, permissionOfOrg);

			configurationData.setOrgData(orgData);

			Map<String, List<String>> userMap = new HashMap<>();
			userMap.put(domain, Arrays.asList(userNames));
			configurationData.setUserMap(userMap);

			configurationData.setNewPassword(newPassword);
			configurationData.setStatus(UserStatus.Active);
			configurationData.setStausLocked(false);

			LinkedHashMap<String, String> siteData = new LinkedHashMap<>();
			siteData.put("name", siteName[i]);
			configurationData.setSiteData(siteData);
			configurationData.setSiteGroupPermission(false);

			configurationData.setModuleList("all");

			LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
			userRolesOfUser.put(userRole.toLowerCase(), true);
			LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
			siteUserRoles.put(userNames[0] + "@" + domain, userRolesOfUser);

			Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
			modulePermissions.put("View", true);
			Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
			modulePermission.put("Files", modulePermissions);
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
			siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);
			configurationData.setSiteUserRoles(siteUserRoles);
			configurationData.setSiteUserModulePermission(siteUserModulePermission);

			String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission",
					"enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
			Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
			adminPageWeb = gotoAdminModule();
			adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.enableAdvancedSiteAdminOption(true);
			adminSecurityWeb.saveAdvancedChanges();
		}

		Reporter.log(precon);

		Reporter.log("Site-A must exist. File, Task & Isheet module must enable in site.");

		Reporter.log("'Enable document analysis' for (HighQ or KIRA or Leverton) must be set as TRUE from ASP Admin >> Configuration.");

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDocumentAnalysisByHighQ(optionTrue);
		aspConfigurationPage.enableDocumentAnalysisByKira(optionTrue);
		aspConfigurationPage.enableDocumentAnalysisByLeverton(optionTrue);
		aspConfigurationPage.saveConfigurations();

		Reporter.log("Document analysis engines (Highq, Kira etc) must be configured & set as 'Enable, default ON in every site' from System settings page.");

		aspConfigurationPage.waitTillGlobalMessageDissappears();
		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemSettingsPage = systemAdminWeb.gotoSystemSettings();
		systemSettingsPage.selectDocumentAnalysis(highqEngine, option1);
		systemSettingsPage.selectOptionFromDocumentAnalysisEngineConfigurationDropdown(kiraEngine, "Configure");
		systemSettingsPage.documentAnalysisEngineConfigureKira(kiraInstanceUrl, kiraToken);

		systemSettingsPage.selectDocumentAnalysis(kiraEngine, option1);

		systemSettingsPage.saveSettings();

		systemSettingsPage.waitTillGlobalMessageDissappears();
		dashboardWeb = bannerPageWeb.gotoDashboard();

		Reporter.log(" 'Document analysis engine' must be set as ON from Site Admin >> Module settings >> Files in Site-A");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		Reporter.log("Site-B must exist and all Document analysis engines must be turned OFF in it & Task module must be disabled in it.");

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[1]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		adminPageWeb = adminFilesWeb.gotoAdminModule();
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(false, SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
		adminPageWeb = modulesPageWeb.clickOnSaveButton();

		logout();
	}

	/**
	 * @throws Exception
	 */
	public void scenario2() throws Exception
	{
		Reporter.log("<font color='black'><B> Scenario 2: Check options displaying in Data Visualisation” panel in Source drop down field.</B></font>");

		Reporter.log(precon);

		Reporter.log("Site-A must exist. File, Task & Isheet module must enable in site.");

		Reporter.log("'Enable document analysis' for (HighQ or KIRA or Leverton) must be set as TRUE from ASP Admin >> Configuration.");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDocumentAnalysisByHighQ(optionTrue);
		aspConfigurationPage.enableDocumentAnalysisByKira(optionTrue);
		aspConfigurationPage.enableDocumentAnalysisByLeverton(optionTrue);
		aspConfigurationPage.saveConfigurations();

		Reporter.log("Document analysis engines (Highq, Kira etc) must be configured & set as 'Enable, default ON in every site' from System settings page.");

		aspConfigurationPage.waitTillGlobalMessageDissappears();
		dashboardWeb = bannerPageWeb.gotoDashboard();

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemSettingsPage = systemAdminWeb.gotoSystemSettings();
		systemSettingsPage.selectDocumentAnalysis(highqEngine, option1);
		systemSettingsPage.selectOptionFromDocumentAnalysisEngineConfigurationDropdown(kiraEngine, "Configure");
		systemSettingsPage.documentAnalysisEngineConfigureKira(kiraInstanceUrl, kiraToken);

		systemSettingsPage.selectDocumentAnalysis(kiraEngine, option1);

		systemSettingsPage.saveSettings();

		systemSettingsPage.waitTillGlobalMessageDissappears();
		dashboardWeb = bannerPageWeb.gotoDashboard();

		Reporter.log(" 'Document analysis engine' must be set as ON from Site Admin >> Module settings >> Files in Site-A");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		Reporter.log("Site-B must exist and all Document analysis engines must be turned OFF in it & Task module must be disabled in it.");

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[1]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableDocumentAnalysisEngine(highqEngine, analysisOption1);
		adminFilesWeb.saveFilesChanges();

		adminPageWeb = adminFilesWeb.gotoAdminModule();
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(false, SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
		adminPageWeb = modulesPageWeb.clickOnSaveButton();

		logout();
		Reporter.log(steps);

		Reporter.log("1. Login with Site Admin/System Admin users.");

		bannerPageWeb = login(siteAdminEmail, newPassword);

		Reporter.log("2. Go to any site >> Home Dashboard.");

		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName[0]);
		homeWeb = bannerPageWeb.gotoHomeModule();

		Reporter.log("3. Click on Edit (pencil) icon.");

		homeWeb.clickOnEditIcon();

		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();

		Reporter.log("4. Click on Add panel icon displaying within the added section.");

		homeWeb.clickonSingleAddPanelIcon();

		Reporter.log("5. From Add panel modal window, select Data Visualisation”.");

		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);

		Reporter.log("6. After Add panel modal opens, select Site-A” from Site drop down list.");

		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.SITE, siteName[0]);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		Reporter.log("7. Click on Source drop down and check the options displaying under it.");

		Reporter.log(expected);

		assertTrue(homeWeb.verifyDefaultSource(HomeLabels.NONE_SELECTED));

		Reporter.log("By Default None selected” should display in Source drop down menu.");

		assertTrue(homeWeb.verifySourceInDataVisualisation(HomeLabels.ISHEETS)
				&& homeWeb.verifySourceInDataVisualisation(docAnalysis)
				&& homeWeb.verifySourceInDataVisualisation(HomeLabels.TASKS));

		Reporter.log("Other options in Source drop down menu should be as below: 1. ISheets 2. Tasks");

		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();

		Reporter.log(steps);

		Reporter.log("8. Now from Site drop down list select 'Site-B'.");

		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName[1]);
		homeWeb = bannerPageWeb.gotoHomeModule();

		Reporter.log("9. Again check options displaying in Source drop down menu.");

		homeWeb.clickOnEditIcon();

		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();

		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);

		Reporter.log(expected);

		assertTrue(homeWeb.verifyDefaultSource(HomeLabels.NONE_SELECTED));

		Reporter.log("By Default None selected” should display in Source drop down menu.");

		assertTrue(homeWeb.verifySourceInDataVisualisation(HomeLabels.ISHEETS)
				&& homeWeb.verifySourceInDataVisualisation(docAnalysis));

		Reporter.log("iSheets option should only display in Source drop down menu.");

		assertTrue(!homeWeb.verifySourceInDataVisualisation(HomeLabels.TASKS));

		Reporter.log("'Tasks' should not display in Source drop down list for the site in which Task module is not enabled.");

		homeWeb.clickOnCancel();
		homeWeb.clickOnClose();
		logout();

		bannerPageWeb = login(aspAdminEmail, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();

		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.enableDocumentAnalysisByHighQ(optionFalse);
		aspConfigurationPage.enableDocumentAnalysisByKira(optionFalse);
		aspConfigurationPage.enableDocumentAnalysisByLeverton(optionFalse);
		aspConfigurationPage.saveConfigurations();

		logout();

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName[0]);
		homeWeb = bannerPageWeb.gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.DATA_VISUALISATION);
		addDataInVisualisation.clear();
		addDataInVisualisation.put(HomeLabels.SITE, siteName[0]);
		homeWeb.addDataInDataVisualisation(addDataInVisualisation);

		Reporter.log(expected);

		assertTrue(!homeWeb.verifySourceInDataVisualisation(docAnalysis));
		Reporter.log("Note: If all Document analysis engines are turned OFF from ASP configuration or all document analysis engines are disabled from System settings page then Document analysis” option will not display in Source drop down menu for any of the selected sites.");
	}
}
