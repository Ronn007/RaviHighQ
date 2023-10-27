package com.highq.test.aiml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
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
public class AIMLTC2717 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String aspAdminEmail = "auto.user@highq.com";
	String systemAdminEmail = "system.admin@aimltc2717.com";
	String siteAdminEmail = "site.admin@aimltc2717.com";
	String newPassword = "Pa&&worD123";

	String orgType = "Internal";

	String[] siteName = {"AIMLTC2717", "AIMLTC2717 - 1"};
	String[] userNames = {"site.admin", "system.admin"};
	String domain = "aimltc2717.com";
	String userRole = "site admin";

	String jsonFileName = "aiml/preConfiguration_AIMLTC2717.json";

	String scenario1 = "<b> <font color='black' size='4'>Scenario 1: Check 'Document analysis support' option for file extensions</font></b>";
	String scenario2 = "<b> <font color='black' size='4'>Scenario 2: Check 'Enable AI engine training' property in system settings page.</font></b>";
	String scenario3 = "<b> <font color='black' size='4'>Scenario 3: Check 'Enable AI engine training' property in Site Admin.</font></b>";
	String scenario4 = "<b> <font color='black' size='4'>Scenario 4 : Verify Document analysis when Enable AI engine unchecked(OFF) at site level</font></b>";
	String scenario5 = "<b> <font color='black' size='4'>Scenario 5 : Verify Document analysis when Enable AI engine checked (ON) at site level</font></b>";
	String scenario6 = "<b> <font color='black' size='4'>Scenario 6: Add New Classifier & check displayed fields.</font></b>";
	String scenario7 = "<b> <font color='black' size='4'>Scenario 7: Add New Classifier & click on cancel button.</font></b>";
	String scenario8 = "<b> <font color='black' size='4'>Scenario 8: Add New Public Classifier</font></b>";
	String scenario9 = "<b> <font color='black' size='4'>Scenario 9: Add New Private Classifier</font></b>";
	String scenario10 = "<b> <font color='black' size='4'>Scenario 10: Add new created classifier in the folder.</font></b>";
	String scenario11 = "<b> <font color='black' size='4'>Scenario 11: Remove added classifier in edit folder</font></b>";
	String scenario12 = "<b> <font color='black' size='4'>Scenario 12: Search valid Classifier name in search text box</font></b>";
	String scenario13 = "<b> <font color='black' size='4'>Scenario 13: Search Invalid Classifier name in search text box</font></b>";
	String scenario14 = "<b> <font color='black' size='4'>Scenario 14: Verify Audit at site level</font></b>";

	String precon = "<b> <font color='black' size='2'>Pre-condition</font></b>";
	String steps = "<b> <font color='black' size='2'>Steps:</font></b>";
	String step1 = "1. Login with ASP Admin users.";
	String step2 = "2. Click on User avatar from Top Navigation >> ASP admin >> Configuration";
	String step3 = "3. Check 'Document analysis configuration' properties in ASP configuration page.";
	String step4Scenario2 = "4. Enter any integer value greater than 0 in 'No. of documents to be processed in a job' text box.";
	String step4Scenario3 = "4. Enter negative integer, decimal value, special character or non integer value in 'No. of documents to be processed in a job' text box.";
	String step4Scenario4 = "4. Now manually provide any valid cron expression (for eg: 0 0/10 * * * ?) in 'Document analysis cron expression' text box";
	String step4Scenario5 = "4. Enter invalid cron expression (any alphabets, random numeric values etc) in 'Document analysis cron expression' text box.";
	String step5 = "5. Click on Save button.";
	String preconScenario6 = "'Enable document analysis' must be set as True from ASP Admin >> configuration.";

	String expected = "<b> <font color='black' size='2'>Expected:</font></b>";
	String docAnalysisConfig = "Document analysis configuration";
	String enableAITraining = "Enable AI training";
	String option1 = "TRUE";
	String option2 = "FALSE";

	String[] extension = {".txt", ".pdf"};
	String doctTypeTxt = "txt";
	ArrayList<String> classifiersList = new ArrayList<>();
	String random = RandomStringUtils.randomAlphabetic(5);
	String classifierName = random;
	String classifierLongDescription = random + "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
	String classifierDescription = "Description Add for Testing";
	String publicRadio = "Public";
	String privateRadio = "Private";
	String moreActionOption = "Edit details";
	String[] addItem = {"File", "Zipped files", "Folder"};
	String zipFile25 = "25 Files.zip";
	String zipFile50 = "50 Files.zip";
	String fileName = "testDoc.txt";

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
	public void AIMLTC2717TestCase() throws InterruptedException, JSONException, IOException
	{
		precondition();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
		scenario10();
		scenario11();
		scenario12();
		scenario13();
		scenario14();
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
		Reporter.log(steps);
		Reporter.log("1. Login with System Admin users.");
		bannerPageWeb = login(systemAdminEmail, newPassword);
		Reporter.log("2. Click on User avatar from Top Navigation >> System admin >> File/File types");
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		sysAdminFileOrFileTypesWeb = systemAdminWeb.gotoFileOrFileTypes();
		Reporter.log("3. Click on any of the displayed file extensions.");
		editFileIconsWeb = sysAdminFileOrFileTypesWeb.gotoDocumentEditPage(doctTypeTxt);

		Reporter.log(expected);

		Assert.assertTrue(editFileIconsWeb.verifyDocumentAnalysisSupportCheckboxDisplay());

		Reporter.log("\"Document analysis support\" field with checkbox should be displayed in Edit file icon page.");

		sysAdminFileOrFileTypesWeb = editFileIconsWeb.clickCancel();
		editFileIconsWeb = sysAdminFileOrFileTypesWeb.gotoDocumentEditPage("Add new");

		Assert.assertTrue(editFileIconsWeb.verifyDocumentAnalysisSupportCheckboxDisplay());

		Reporter.log("'Document analysis support' field with checkbox should also display while adding a new file extension in the system.");
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario2() throws InterruptedException
	{
		Reporter.log(scenario2);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.enableDocumentAnalysisByHighQ(option1);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		logout();
		Reporter.log(steps);

		Reporter.log("1. Login with System Admin user.");
		bannerPageWeb = login(systemAdminEmail, newPassword);

		Reporter.log("2. 2. Click on User avatar from Top Navigation >> System admin >> System settings.");
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log("3. Check weather 'Enable AI engine training' option is displaying or not in system settings page.");

		Reporter.log(expected);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyEnableAIEngineTrainingDropdownDisplay());
		Reporter.log("'Enable AI engine training' option with drop down menu should display in System settings page when 'Enable AI training' option is set as TRUE from ASP Admin >> Configuration.");

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE));
		Reporter.log("Values in drop down menu as: OFF (Default), ON ");

		logout();

		Reporter.log(steps);
		Reporter.log("4. Now Login with ASP Admin user.");
		Reporter.log("5. Go to ASP Admin >> Configuration; Mark the 'Enable AI training' property as 'FALSE' then click on Save button.");
		Reporter.log("6. Now check again with the system admin user weather 'Enable AI engine training' option is displaying or not in system settings page.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option2);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log(expected);

		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyEnableAIEngineTrainingDropdownDisplay());
		Reporter.log("'Enable AI engine training' option should not display in System settings page when 'Enable AI  training' property is set as FALSE from ASP Admin >> Configuration.");
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario3() throws InterruptedException
	{
		Reporter.log(scenario3);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		bannerPageWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		logout();
		Reporter.log(steps);
		Reporter.log("1. Login with System Admin user.");
		bannerPageWeb = login(systemAdminEmail, newPassword);

		Reporter.log("2. Go to any Site >> Site Admin >> Module settings >> Files.");
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		Reporter.log("3. Check weather 'Enable AI engine training' option is displaying or not under Advanced settings section.");

		Reporter.log(expected);

		Assert.assertTrue(adminFilesWeb.verifyEnableAIEngineTrainingDisdplay());

		Reporter.log("'Enable AI engine training' option with checkbox should display in Site Admin >> File page when 'Enable AI  training' option is set as TRUE from ASP Admin >> Configuration & 'Enable AI engine training' is set as ON from System settings page.");

		Reporter.log(steps);
		Reporter.log("4. Now turn off 'Enable AI engine training' property in system settings page or mark it as 'FALSE' in ASP configuration page.");

		bannerPageWeb = gotoDashboard();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("OFF");
		systemAdminSystemSettingsWeb.saveSettings();

		Reporter.log("5. Go to any site >> Site Admin >> Module settings >> Files, check again weather 'Enable AI engine training' option is displaying or not under Advanced settings section.");
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		Reporter.log(expected);

		Assert.assertTrue(!adminFilesWeb.verifyEnableAIEngineTrainingDisdplay());

		Reporter.log("'Enable AI engine training' option should not display in Site Admin >> Module settings >> Files, when 'Enable AI training' option is set as FALSE from ASP Admin >> Configuration or 'Enable AI engine training' property is set as 'OFF' from System settings page.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario4() throws InterruptedException
	{
		Reporter.log(scenario4);
		Reporter.log(precon);
		Reporter.log("'Enable AI  training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("' Enable AI engine training' must be unchecked from site admin >> file >>Enable AI engine training ");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(false);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.deleteAllDocs();

		Reporter.log("3.Click on add folder");

		documentWeb.selectItemFromNew(addItem[2]);

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Verify Document analysis section");

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyDocumentAnalysisDisplayed());

		Reporter.log("Document analysis section should not display.");
		documentWeb.clickCancelAddFolderModal();

		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario5() throws InterruptedException
	{
		classifiersList.clear();
		classifiersList.add("Corporate Consent Agreements");
		classifiersList.add("Employment Contract");
		classifiersList.add("Facility Agreements");
		classifiersList.add("Intellectual Property Agreement");
		classifiersList.add("Financial Services Agreements");
		classifiersList.add("Lease Agreement (Property)");
		classifiersList.add("Non - Disclosure Agreement");
		classifiersList.add("Pledge Agreements");
		classifiersList.add("Sales Agreement");
		classifiersList.add("Software License Agreement");
		classifiersList.add("Stock Purchase Agreement");
		String classifierName1 = "Corporate Consent Agreements";
		String classifierName2 = "Employment Contract";
		Reporter.log(scenario5);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder");

		documentWeb.selectItemFromNew(addItem[2]);

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Verify Document analysis section");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyDocumentAnalysisDisplayed());

		Reporter.log("Document analysis section should display.");

		Assert.assertTrue(documentWeb.verifyClassifierSelectButtonDisplayed());

		Reporter.log("Classifier label should display with Select... button in Document analysis section.");

		Reporter.log(steps);

		Reporter.log("6.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersModalDisplayed());

		Reporter.log("Classifiers modal should open with modal title as 'Classifiers'.");

		Assert.assertTrue(documentWeb.verifyClassifiersList(classifiersList));

		Reporter.log("List of all Classifiers should display in a modal along with the radio button to select the classifier.");

		Assert.assertTrue(documentWeb.verifyClassifiersLabelDisplayed());

		Reporter.log("Above listed classifier list, a static label \"Select a Classifier to add \" should display.");

		Assert.assertTrue(documentWeb.verifyClassifierSearchTextboxDisplayed());

		Reporter.log("Auto suggest search field should display in the modal to search any classifier from the list. Placeholder label of search field as 'Search classifiers'");

		Assert.assertTrue(documentWeb.verifyClassifierButtonsDisplayed());

		Reporter.log("New classifier, Cancel & Done button should display in bottom of the modal.");

		Assert.assertTrue(documentWeb.verifyClassifierDoneButtonsDisabledDisplayed());

		Reporter.log("By default 'Done' button should be disabled.");

		Reporter.log(steps);

		Reporter.log("7.Click on cancel button (or Close icon)");

		documentWeb.clickCancelClassifierModal();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyClassifiersModalDisplayed());

		Reporter.log("Classifier model should close without saving any data.");

		Reporter.log(steps);

		Reporter.log("8. Again click on select... button from Settings tab of Add folder modal");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("9. Select radio button of any displayed classifier.");

		documentWeb.selectClassifierFromList(classifierName1);

		Assert.assertTrue(documentWeb.verifyClassifierDoneButtonEnabledDisplayed());

		Reporter.log("Done button should enable on selecting any classifier from the list.");

		Reporter.log(steps);

		Reporter.log("10. Click on Done button.");
		documentWeb.clickOnClassifierModalDoneButton();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyClassifiersModalDisplayed());

		Reporter.log("Classifier modal should close ");

		Assert.assertTrue(documentWeb.verifyClassifierTokenDisplayed(classifierName1)
				&& documentWeb.verifyClassifierTokenRemoveButtonDisplayed());

		Reporter.log("token of selected classifier should generate with remove icon below Select button of Classifier. ");

		Reporter.log(steps);

		Reporter.log("11. Again click on Select... button & select any other classifier from the list.");

		documentWeb.clickOnClassifierSelectButton();

		documentWeb.selectClassifierFromList(classifierName2);

		Reporter.log("12. Click on Done button.");

		documentWeb.clickOnClassifierModalDoneButton();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyClassifierTokenDisplayed(classifierName1));

		Reporter.log("Token of previously selected classifier should be removed.");

		Assert.assertTrue(documentWeb.verifyClassifierTokenDisplayed(classifierName2));

		Reporter.log("Token of new selected classifier should be generated. ");

		Reporter.log(steps);

		Reporter.log("13.Click on remove icon of added classifier.");

		documentWeb.clickOnClassifierTokenRemoveButton();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyClassifierTokenDisplayed(classifierName2));

		Reporter.log("Added classifier should be removed.");

		documentWeb.clickCancelAddFolderModal();

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario6() throws InterruptedException
	{
		Reporter.log(scenario6);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario6");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyNewClassifierModalDisplayed());

		Reporter.log("New Classifier modal should open with modal title as \"New classifier\"");

		Assert.assertTrue(documentWeb.verifyNewClassifierModalFieldsDisplayed());

		Reporter.log("Fields displaying in the modal should be as below:");
		Reporter.log("1. Name - It should display with text field. Place holder label of text field as \"Enter classifier name\"");
		Reporter.log("2. Description - It should display with text field. Place holder label of text field as \"Add description\"");
		Reporter.log("3. Scope of classifier? - It should display with 2 radio button options: Public (Default selected) & Private.");

		Assert.assertTrue(documentWeb.verifyNewClassifierModalButtonsDisplayed());

		Reporter.log("Cancel & Add button should display in bottom of the modal. By default 'Add' button should be disabled. It should enable only after Classifier Name is provided.");

		Reporter.log(steps);

		Reporter.log("7. Hover mouse over \"?\" icon of Scope of classifier.");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyTooltipMessageScopeOfClassifier("Public classifiers are available to be used in any site once they are trained. Private classifiers are only available within this site."));
		Reporter.log("A tool tip with a message 'Public classifiers are available to be used in any site once they are trained. Private classifiers are only available within this site.' should display.");

		Reporter.log(steps);

		Reporter.log("8.Enter classifier Name and enter a long Description in it");

		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario6");
		documentWeb.addClassifierDescriptionNewClassifierModal(classifierLongDescription);

		Reporter.log("9.Click on Add button");

		documentWeb.clickAddButtonNewClassifiersModal();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyNewClassifierModalDisplayed()
				&& (documentWeb.verifyClassifiersName(classifierName + "scenario6")));

		Reporter.log("New classifier modal should close and new added classifier should display in classifier list ");

		Assert.assertTrue(documentWeb.verifyClassifiersDescriptionLink(classifierLongDescription, "more"));
		Reporter.log("'more' link should display in a description when a long description is added.");

		Reporter.log(steps);

		Reporter.log("10.Click on more link of new added classifier description");

		documentWeb.clickOnClassifiersDescriptionLink(classifierLongDescription, "more");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersDescriptionLink(classifierLongDescription, "Less"));
		Reporter.log("Less link should now display with the description.");
		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario7() throws InterruptedException
	{
		Reporter.log(scenario7);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario7");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log("7.Click on cancel button (or Close icon)");

		documentWeb.clickCancelAddNewClassifierModal();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyNewClassifierModalDisplayed());

		Reporter.log("New Classifier modal should Close.");

		Assert.assertTrue(documentWeb.verifyClassifiersModalDisplayed());

		Reporter.log("User should be redirected back on Classifiers modal.");

		documentWeb.clickCancelClassifiersModal();

		documentWeb.clickCancelAddFolderModal();

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario8() throws InterruptedException
	{
		Reporter.log(scenario8);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[1]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario8");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log("7.Add Classifier name and description");

		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario8");
		documentWeb.addClassifierDescriptionNewClassifierModal(classifierDescription + "scenario8");

		Reporter.log("8.Select public radio button");

		documentWeb.selectRadioButtonScopeOfClassifier(publicRadio);

		Reporter.log("9.Click on Add button");

		documentWeb.clickAddButtonNewClassifiersModal();

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersName(classifierName + "scenario8"));

		Reporter.log("New added public classifier should be added.");

		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();

		dashboardWeb = gotoDashboardByClickOnLogo();

		dashboardWeb.searchSite(siteName[1]);

		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.clickSettingsTabAddFolderModal();

		documentWeb.clickOnClassifierSelectButton();
		Assert.assertTrue(documentWeb.verifyClassifiersName(classifierName + "scenario8"));

		Reporter.log("New added classifier should display in current as well as in other sites of the instance.");
		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();
	}

	private void scenario9() throws InterruptedException
	{
		Reporter.log(scenario9);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario8");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log("7.Add Classifier name and description");

		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario9");
		documentWeb.addClassifierDescriptionNewClassifierModal(classifierDescription + "scenario9");

		Reporter.log("8.Select public radio button");

		documentWeb.selectRadioButtonScopeOfClassifier(privateRadio);

		Reporter.log("9.Click on Add button");

		documentWeb.clickAddButtonNewClassifiersModal();

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersName(classifierName + "scenario9"));

		Reporter.log("New added public classifier should be added.");

		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();

		dashboardWeb.searchSite(siteName[1]);

		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.clickSettingsTabAddFolderModal();

		documentWeb.clickOnClassifierSelectButton();
		Assert.assertTrue(!documentWeb.verifyClassifiersName(classifierName + "scenario9"));

		Reporter.log("New added public classifier should be visible only withing the current site. It should not be visible in other sites of the instance.");
		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario10() throws InterruptedException
	{
		Reporter.log(scenario10);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		logout();

		Reporter.log("Case1: Add Classifier");

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario10");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log("7.Add Classifier name and description");

		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario10");
		documentWeb.addClassifierDescriptionNewClassifierModal(classifierLongDescription + "scenario10");

		Reporter.log("8.Click on Add button");

		documentWeb.clickAddButtonNewClassifiersModal();

		Reporter.log("9.Check radio button of new added classifier and click on Done button");

		documentWeb.selectClassifierFromList(classifierName + "scenario10");
		documentWeb.clickOnClassifierModalDoneButton();

		Assert.assertTrue(documentWeb.verifyClassifierTokenDisplayed(classifierName + "scenario10"));

		Reporter.log("Selected classifier should display in Settings tab of Add folder modal.");

		Reporter.log(steps);

		Reporter.log("10.Click on more link of classifier description");

		documentWeb.clickOnClassifierSelectButton();
		documentWeb.clickOnClassifiersDescriptionLink(classifierLongDescription + "scenario10", "more");

		Assert.assertTrue(documentWeb.verifyClassifiersDescriptionLink(classifierLongDescription + "scenario10", "Less"));

		Reporter.log("Classifier description should expand by clicking on more link.");

		Reporter.log("Note: More link is only visible when classifier description is having multiline text.");

		Reporter.log(steps);
		Reporter.log("11.Click on Less link of classifier description");

		documentWeb.clickOnClassifiersDescriptionLink(classifierLongDescription + "scenario10", "Less");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersDescriptionLink(classifierLongDescription + "scenario10", "more"));

		Reporter.log("Classifier description should Collapse by clicking on Less link");

		Reporter.log(steps);

		Reporter.log("12.Click on Add button after classifier is selected.");

		documentWeb.selectClassifierFromList(classifierName + "scenario10");
		documentWeb.clickOnClassifierModalDoneButton();

		documentWeb.clickOnAddFolderModalAddButton();
		documentWeb.selectLeftPanelFileOptions("AIMLTC2717");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyFolder("Folder for scenario10"));

		Reporter.log("Folder should be created & the selected classifier should bind with that folder.");

		Reporter.log(steps);

		Reporter.log("13.Click on More actions button of created folder >> Click on Edit details >> Go to Settings tab.");

		documentWeb.gotoMoreActions("Folder for scenario10", moreActionOption);
		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifierTokenDisplayed(classifierName + "scenario10"));

		Reporter.log("Selected classifier should display in Document analysis section below Classifier label.");
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();

		Reporter.log("Case2: Add/Edit Duplicate Classifier");

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on add folder and enter Folder name");
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario8");

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Click on New Classifier button");

		documentWeb.clickNewClassifierButtonClassifierModal();

		Reporter.log("7. Enter classifier name which is already exist ");

		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario10");
		documentWeb.addClassifierDescriptionNewClassifierModal(classifierDescription + "scenario10");

		Reporter.log("8.Click on Add button");

		Reporter.log(expected);

		documentWeb.clickAddButtonNewClassifiersModal();

		Assert.assertTrue(documentWeb.verifyMessageForDuplicateClassifierAdd("This classifier name already exists. Please choose a unique name."));

		documentWeb.clickCancelAddNewClassifierModal();
		documentWeb.clickCancelClassifiersModal();
		documentWeb.clickCancelAddFolderModal();

		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario11() throws InterruptedException
	{

		Reporter.log(scenario11);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();

		Reporter.log("One classifier should be added in folder");

		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectItemFromNew(addItem[2]);
		documentWeb.addFolderNameAddFolderModal("Folder for scenario11");

		documentWeb.clickSettingsTabAddFolderModal();
		documentWeb.clickOnClassifierSelectButton();
		documentWeb.clickNewClassifierButtonClassifierModal();
		documentWeb.addClassifierNameNewClassifierModal(classifierName + "scenario11");
		documentWeb.clickAddButtonNewClassifiersModal();

		documentWeb.selectClassifierFromList(classifierName + "scenario11");
		documentWeb.clickOnClassifierModalDoneButton();
		documentWeb.clickOnAddFolderModalAddButton();

		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on More action of Folder where classifier added");

		documentWeb.gotoMoreActions("Folder for scenario11", moreActionOption);

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on remove icon of classifier below select select button of classifier");

		documentWeb.clickOnClassifierTokenRemoveButton();

		Reporter.log(expected);

		Assert.assertTrue(!documentWeb.verifyClassifierTokenDisplayed(classifierName + "scenario11"));
		Reporter.log("Selected classifier should be removed");

		Reporter.log("7.Click on Save button");

		documentWeb.clickOnAddFolderModalAddButton();
		documentWeb.selectLeftPanelFileOptions("AIMLTC2717");

		Assert.assertTrue(documentWeb.verifyFolder("Folder for scenario11"));

		Reporter.log("Folder should be save successfully");

		Reporter.log("13.Click on More actions button of created folder >> Click on Edit details >> Go to Settings tab.");

		documentWeb.gotoMoreActions("Folder for scenario11", moreActionOption);
		documentWeb.clickSettingsTabAddFolderModal();

		Assert.assertTrue(!documentWeb.verifyClassifierTokenDisplayed(classifierName + "scenario11"));

		Reporter.log("Edit again same folder and verify removed classifier should not display in folder setting tab.");
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario12() throws InterruptedException
	{
		Reporter.log(scenario12);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();
		logout();

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on More action of Folder where classifier added");

		documentWeb.gotoMoreActions("Folder for scenario11", moreActionOption);

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Enter valid Classifier name in search text box");

		documentWeb.searchClassifier(classifierName + "scenario11");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyClassifiersName(classifierName + "scenario11"));

		Reporter.log("Search result should be filter automatic based on search text");
		Reporter.log("Search result should be display with based on classifier title name");

		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario13() throws InterruptedException
	{
		classifiersList.clear();
		classifiersList.add("Corporate Consent Agreements");
		classifiersList.add("Employment Contract");
		classifiersList.add("Facility Agreements");
		classifiersList.add("Intellectual Property Agreement");
		classifiersList.add("Financial Services Agreements");
		classifiersList.add("Lease Agreement (Property)");
		classifiersList.add("Non - Disclosure Agreement");
		classifiersList.add("Pledge Agreements");
		classifiersList.add("Sales Agreement");
		classifiersList.add("Software License Agreement");
		classifiersList.add("Stock Purchase Agreement");
		Reporter.log(scenario13);
		Reporter.log(precon);
		Reporter.log("'Enable AI training' must be set as True from ASP Admin >> configuration.");
		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableAITraining(option1);
		aspConfigurationWeb.saveConfigurations();

		Reporter.log("'Enable AI engine training' must be set as ON from System settings page.");

		bannerPageWeb = gotoDashboardByClickOnLogo();
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromEnableAIEngineTrainingDropdown("ON");
		systemAdminSystemSettingsWeb.saveSettings();
		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("'Enable AI engine training' must be checked from site admin >> file >>Enable AI engine training");

		dashboardWeb.searchSite(siteName[0]);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableAIEngineTraining(true);
		adminFilesWeb.saveFilesChanges();
		logout();
		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on file module");

		documentWeb = dashboardWeb.gotoFileModule();

		Reporter.log("3.Click on More action of Folder where classifier added");

		documentWeb.gotoMoreActions("Folder for scenario11", moreActionOption);

		Reporter.log("4.Click on Settings tab");

		documentWeb.clickSettingsTabAddFolderModal();

		Reporter.log("5.Click on Select... button");

		documentWeb.clickOnClassifierSelectButton();

		Reporter.log("6.Enter invalid Classifier name in search text box");

		documentWeb.searchClassifier(classifierName + "scenario13");

		Reporter.log(expected);

		Assert.assertTrue(documentWeb.verifyMessageForNoClassifiersNameDisplayed("No results found"));

		Reporter.log("Search result should be filter automatic based on search text");

		documentWeb.clickOnClassifierSearchRemoveButton();

		Assert.assertTrue(documentWeb.verifyClassifiersList(classifiersList));
		Reporter.log("Search result should be display with based on classifier title name");

		documentWeb.clickCancelClassifierModal();
		documentWeb.clickCancelAddFolderModal();
		gotoDashboardByClickOnLogo();
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario14() throws InterruptedException
	{
		Reporter.log(scenario14);
		Reporter.log("Case 1 : Checked Enable AI from site admin");
		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on Admin section");

		adminPageWeb = dashboardWeb.gotoAdminModule();

		Reporter.log("3.Click on File ");

		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		adminFilesWeb.enableAIEngineTraining(false);
		adminFilesWeb.saveFilesChanges();

		Reporter.log("4.Checked Enable AI training check box(Default unchecked when create new site)");

		adminFilesWeb.enableAIEngineTraining(true);

		Reporter.log("5.Click on Save button");

		adminFilesWeb.saveFilesChanges();

		Reporter.log("6.Now Click on audit >> Site management ");

		adminPageWeb = adminFilesWeb.gotoAdminModule();

		adminAuditsSiteManagementWeb = adminPageWeb.clickSiteManagementInLeftPanel();

		Reporter.log("7.Select Site update checkbox and Click on submit button");

		adminAuditsSiteManagementWeb = adminAuditsSiteManagementWeb.clickOnSubmit();

		Reporter.log(expected);

		Assert.assertTrue(adminAuditsSiteManagementWeb.verifySiteUpdateListDisplayed());

		Reporter.log("List of site update should be displayed");

		Reporter.log(steps);
		Reporter.log("8.Click on first Action name (Site update)");

		adminAuditsSiteManagementWeb.clickOnActionNameOnSiteManagementAudit(2);

		Reporter.log(expected);
		Assert.assertTrue(adminAuditsSiteManagementWeb.verifyActionDetails("Enable AI engine training", "No", "Yes"));

		Reporter.log("Action Details module should be open and Enable AI training Label should be display in grid");
		Reporter.log("Old parameter and new parameter column should be displayed");
		Reporter.log("Old parameter should be \"No\" and New parameter should be \"Yes\"");

		adminAuditsSiteManagementWeb.clickOnCancelInActionDeTails();

		logout();

		Reporter.log("Case 2: Checked disabled AI from site admin");
		Reporter.log(precon);

		Reporter.log("Enable AI training should checked from site admin >> File");

		Reporter.log(steps);

		Reporter.log("1.Login with Site Admin user.");

		bannerPageWeb = login(siteAdminEmail, newPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName[0]);

		Reporter.log("2.Click on Admin section");

		adminPageWeb = dashboardWeb.gotoAdminModule();

		Reporter.log("3.Click on File ");

		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		Reporter.log("4.Unchecked Enable AI training check box");

		adminFilesWeb.enableAIEngineTraining(false);

		Reporter.log("5.Click on Save button");

		adminFilesWeb.saveFilesChanges();

		Reporter.log("6.Now Click on audit >> Site management ");

		adminPageWeb = adminFilesWeb.gotoAdminModule();

		adminPageWeb.clickSiteManagementInLeftPanel();

		Reporter.log("7.Select Site update checkbox and Click on submit button");

		adminAuditsSiteManagementWeb = adminAuditsSiteManagementWeb.clickOnSubmit();

		Reporter.log(expected);

		Assert.assertTrue(adminAuditsSiteManagementWeb.verifySiteUpdateListDisplayed());

		Reporter.log("List of site update should be displayed");

		Reporter.log(steps);
		Reporter.log("8.Click on first Action name (Site update)");
		adminAuditsSiteManagementWeb.clickOnActionNameOnSiteManagementAudit(2);

		Reporter.log(expected);

		Assert.assertTrue(adminAuditsSiteManagementWeb.verifyActionDetails("Enable AI engine training", "Yes", "No"));

		Reporter.log("Action Details module should be open and Enable AI training Label should be display in grid");

		Reporter.log("Old parameter should be \"Yes\" and New parameter should be \"NO\"");
		adminAuditsSiteManagementWeb.clickOnCancelInActionDeTails();

		logout();
	}
}
