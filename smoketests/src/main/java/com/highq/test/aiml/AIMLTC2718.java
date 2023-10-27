package com.highq.test.aiml;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAuditsSiteManagementPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.HomeWeb;

/**
 * @author tejash.trivedi
 */
public class AIMLTC2718 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String aspAdminEmail = "auto.user@highq.com";
	String systemAdminEmail = "system.admin@aimltc2718.com";
	String siteAdminEmail = "site.admin@aimltc2718.com";
	String newPassword = "Pa&&worD123";
	String orgType = "Internal";
	String[] siteName = {"AIMLTC2718", "AIMLTC2718 - 1"};
	String[] userNames = {"site.admin", "system.admin"};
	String domain = "aimltc2718.com";
	String userRole = "site admin";
	String scenario1 = "<b> <font color='black' size='4'>Scenario 1: Check 'Document analysis engine configuration' property in system settings page.</font></b>";
	String precon = "<b> <font color='black' size='2'>Pre-condition</font></b>";
	String steps = "<b> <font color='black' size='2'>Steps:</font></b>";
	String expected = "<b> <font color='black' size='2'>Expected:</font></b>";
	String docAnalysisConfig = "Document analysis configuration";
	String enableAITraining = "Enable AI training";
	String option1 = "TRUE";
	String option2 = "FALSE";

	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;
	AdminSecurityPage adminSecurityWeb;
	ConfigurationData configurationData = new ConfigurationData();
	DashboardPage dashboardWeb;
	HomeWeb homeWeb;
	AdminPage adminPageWeb;
	AddUserPage addUserWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserPageWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFileTypesWeb;
	EditFileIconsPage editFileIconsWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage documentAddDataPage;
	AdminAuditsSiteManagementPage adminAuditsSiteManagementWeb;

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
	public void AIMLTC2718TestCase() throws InterruptedException, JSONException, IOException
	{
		precondition();
		scenario1();

	}

	private void precondition() throws InterruptedException, JSONException, IOException
	{
		/** Site and user setup */

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

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		searchUserPageWeb = systemAdminWeb.gotoUserAdmin();
		searchUserPageWeb.searchUser(systemAdminEmail, UserStatus.Active, false);

		Map<UserPermission, Boolean> userRole = new HashMap<>();
		userRole.put(UserPermission.System_Admin, true);

		searchUserPageWeb.setRoles(systemAdminEmail, userRole);

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario1() throws InterruptedException
	{
		Reporter.log(scenario1);
		Reporter.log(precon);

		Reporter.log("Enable document analysis by HighQ under Enable document analysis must be set as True from ASP Admin >> configuration.");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableDocumentAnalysisByHighQ(option1);
		aspConfigurationWeb.saveConfigurations();

		logout();

		Reporter.log(steps);
		Reporter.log("1. Login with System Admin users.");
		bannerPageWeb = login(systemAdminEmail, newPassword);
		Reporter.log("2. Click on User avatar from Top Navigation >> System admin >> System settings.");
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log("3. Check weather 'Document analysis' option is displaying or not in system settings page.");

		Reporter.log(expected);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("Under 'Document analysis engine configuration' option with drop down menu should display in System settings page for 'HighQ Engine' when 'Enable document analysis by HighQ' option is set as TRUE from ASP Admin >> Configuration.");

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE)
				&& systemAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTOFFINEVERYSITE_TITLE)
				&& systemAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE));

		Reporter.log("By default high engine should be 'Enable Default ON for every site'");

		Reporter.log("Values in drop down menu as: Enable, Default ON in every site, Enable, Default OFF in every site and Disabled");

		logout();

		Reporter.log(steps);

		Reporter.log("4. Now Login with ASP Admin user.");
		Reporter.log("5. Go to ASP Admin >> Configuration; Mark the 'Enable document analysis' status as 'FALSE' then click on Save button.");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableDocumentAnalysisByHighQ(option1);
		aspConfigurationWeb.saveConfigurations();
		logout();

		Reporter.log("6. Now check again with the system admin user weather 'Manage' option in more action is displaying or not in system settings page.");
		bannerPageWeb = login(systemAdminEmail, newPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log(expected);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("It should not show highq Engine where Enable document analysis by HighQ is False from asp admin>> configuration");

		logout();
	}
}
