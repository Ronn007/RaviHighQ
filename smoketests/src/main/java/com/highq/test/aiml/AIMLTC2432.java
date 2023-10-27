package com.highq.test.aiml;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.HashMap;
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
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
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
public class AIMLTC2432 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String aspAdminEmail = "auto.user@highq.com";
	String systemAdminEmail = "system.admin@highq.com";
	String siteAdminEmail = "site.admin@highq.com";
	String newPassword = "Pa&&worD123";
	String jsonFileName = "aiml/preConfiguration_AIMLTC2432.json";
	String siteName = "AIMLTC2432";
	String toolTipMsg2 = "Remove from favourites";
	String scenario1 = "<b> <font color='black' size='4'>Scenario 1: Check 'Document analysis configuration' properties in ASP configuration.</font></b>";
	String scenario2 = "<b> <font color='black' size='4'>Scenario 2: Save ASP configuration page manually providing any value in 'No. of documents to be processed in a job' property.</font></b>";
	String scenario3 = "<b> <font color='black' size='4'>Scenario 3: Check validation message on providing invalid values in 'No. of documents to be processed in a job' property in ASP configuration.</font></b>";
	String scenario4 = "<b> <font color='black' size='4'>Scenario 4: Save ASP configuration page manually providing any valid cron expression in 'Document analysis cron expression' property.</font></b>";
	String scenario5 = "<b> <font color='black' size='4'>Scenario 5: Check validation message on providing invalid cron expression in 'Document analysis cron expression' property in ASP configuration.</font></b>";
	String scenario6 = "<b> <font color='black' size='4'>Scenario 6: Check 'Document analysis' property in system settings page.</font></b>";
	String scenario7 = "<b> <font color='black' size='4'>Scenario 7: Check 'Enable Document analysis' property in Site Admin.</font></b>";
	String scenario8 = "<b> <font color='black' size='4'>Scenario 8: Check 'Document analysis support' option for file extensions</font></b>";
	String scenario9 = "<b> <font color='black' size='4'>Scenario 9: Check 'Enable AI engine training' property in system settings page.</font></b>";
	String scenario10 = "<b> <font color='black' size='4'>Scenario 10: Check 'Enable AI engine training' property in Site Admin.</font></b>";

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
	String highqEngine = "Enable document analysis by HighQ";
	String kiraEngine = "Enable document analysis by KIRA";
	String ravanEngine = "Ravan Document Analysis engine";
	String LuminanceEngine = "Enable document analysis by Leverton";
	String enableAITraining = "Enable AI training";
	String cronExpression = "Document analysis cron expression";
	String noofDocInJob = "No. of documents to be processed in a job";
	String optionTrue = "TRUE";
	String optionFalse = "FALSE";
	String fileName = "testDoc";
	String[] extension = {".txt", ".pdf"};
	String doctTypeTxt = "txt";

	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;
	DashboardPage dashboardWeb;
	HomeWeb homeWeb;
	AdminPage adminPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserPageWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFileTypesWeb;
	EditFileIconsPage editFileIconsWeb;
	SystemAdminSystemSettingsPage systemSettingsPage;

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
	public void AIMLTC2432TestCase() throws InterruptedException, JSONException, IOException
	{
		precondition();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
	}

	private void precondition() throws InterruptedException, JSONException, IOException
	{
		/** Site and user setup */

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(aspAdminEmail, newPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
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
	 * @throws IOException
	 * @throws JSONException
	 */
	private void scenario1() throws InterruptedException
	{
		Reporter.log(scenario1);

		Reporter.log(steps);
		Reporter.log(step1);

		bannerPageWeb = login(aspAdminEmail, newPassword);

		Reporter.log(step2);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log(step3);

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();

		resetDocumentAnalysisConfiguration();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifyLeftPanelLabel(docAnalysisConfig));

		Reporter.log("\"Document analysis configuration\" option should display in left section.");

		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);

		Assert.assertTrue(aspConfigurationWeb.verifyRightPanelLabel(highqEngine)
				&& aspConfigurationWeb.verifyRightPanelLabel(kiraEngine)
				// && aspConfigurationWeb.verifyRightPanelLabel(ravanEngine)
				&& aspConfigurationWeb.verifyRightPanelLabel(LuminanceEngine)
				&& aspConfigurationWeb.verifyRightPanelLabel(enableAITraining)
				&& aspConfigurationWeb.verifyRightPanelLabel(cronExpression)
				&& aspConfigurationWeb.verifyRightPanelLabel(noofDocInJob));

		Reporter.log("'Document analysis configuration' section should display in configuration page and "
				+ "under it multiple AI engine names (Enable document analysis by HighQ, Enable document analysis by KIRA, Enable document analysis by Leverton), "
				+ "Enable AI training, 'Document analysis cron expression' & 'No. of documents to be processed in a job' properties should display under it.");

		Assert.assertTrue(aspConfigurationWeb.verifySearchEngineValues());

		Reporter.log("All different AI engines should display with drop down menu having values as: FALSE (Default) & TRUE");

		closeSaveChangesMessage();

		Assert.assertTrue(aspConfigurationWeb.verifyAITrainingValues());

		Reporter.log("'Enable AI training' should display with drop down menu having values as: FALSE (Default) & TRUE");

		Assert.assertTrue(aspConfigurationWeb.verifyCronExpressionValue("0 0/5 * * * ?"));

		Reporter.log("'Document analysis cron expression' should display with text box and default expression set as 5 minutes. (0 0/5 * * * ?) ");

		Assert.assertTrue(aspConfigurationWeb.verifyNoOfDocsValue("1000"));

		Reporter.log("'No. of documents to be processed in a job' should display with text box having default value as 1000.");

		aspConfigurationWeb.enableDocumentAnalysisByHighQ(optionFalse);
		aspConfigurationWeb.saveConfigurations();

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("If any AI Engine false then Engine will not display at system admin >> system setting.");

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		Assert.assertTrue(!adminFilesWeb.verifyDocumentAnalysisEngineConfigurationDisdplay());

		Reporter.log("If any AI Engine false then Engine will not display at site level site admin>>module setting>>file");

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableDocumentAnalysisByHighQ(optionTrue);
		aspConfigurationWeb.saveConfigurations();

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("If any AI Engine True then Engine will display at system admin >> system setting.");

		systemAdminSystemSettingsWeb.selectOptionFromHighqEngineDropdown(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE);

		systemAdminSystemSettingsWeb.saveSettings();

		dashboardWeb = bannerPageWeb.gotoDashboardByClickOnLogo();

		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		Assert.assertTrue(adminFilesWeb.verifyDocumentAnalysisEngineConfigurationDisdplay());

		Reporter.log("If any AI Engine True then Engine will display at site level site admin>>module setting>>file");

		logout();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void scenario2() throws InterruptedException
	{
		Reporter.log(scenario2);

		Reporter.log(steps);
		Reporter.log(step1);

		bannerPageWeb = login(aspAdminEmail, newPassword);

		Reporter.log(step2);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log(step3);

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);

		Reporter.log(step4Scenario2);

		aspConfigurationWeb.setNoOfDocsInJob("10");

		Reporter.log(step5);

		aspConfigurationWeb.saveConfigurations();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifySaveConfigurationMessage("Changes applied"));

		Reporter.log("Confirmation message \"Changes applied\"  should display in the page.");

		Assert.assertTrue(aspConfigurationWeb.verifyNoOfDocsValue("10"));

		Reporter.log("ASP configuration page should save successfully providing any valid integer value (greater than 0) in No. of documents to be processed in a job' property.");

		/* Below Note is for Information Only no Verification Required as per Manual Tester */

		Reporter.log("Note: 'No. of documents to be processed in a job' property defines the number of document that will process in every executed cron job.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void scenario3() throws InterruptedException
	{
		Reporter.log(scenario3);

		Reporter.log(steps);
		Reporter.log(step1);

		bannerPageWeb = login(aspAdminEmail, newPassword);

		Reporter.log(step2);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log(step3);

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);

		Reporter.log(step4Scenario3);

		aspConfigurationWeb.setNoOfDocsInJob("-1000.256/*~!@#$%^&*()_+|\"{}\":?><,./;'[]");

		Reporter.log(step5);

		aspConfigurationWeb.saveConfigurations();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifyNoOfDocsValidationMessage("No. of documents to be processed in a job must be positive integer."));

		Reporter.log("User should not be able to save ASP configuration page with negative integer, "
				+ "decimal value, special character or non integer value in 'No. of documents to be processed in a job' property.");

		Reporter.log("It should display a validation message 'No. of documents to be processed in a job must be positive integer.' in configuration page.");

		Reporter.log(steps);

		Reporter.log("6. Now enter 0 in 'No. of documents to be processed in a job' text box");

		aspConfigurationWeb.setNoOfDocsInJob("0");

		Reporter.log("7. Click on Save button.");

		aspConfigurationWeb.saveConfigurations();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifyNoOfDocsValidationMessage("No. of documents to be processed in a job is not valid. It must be greater than or equal to 1."));

		Reporter.log("User should not be able to save ASP configuration page with integer value less than 1 in 'No. of documents to be processed in a job' property.");

		Reporter.log("It should display a validation message 'No. of documents to be processed in a job is not valid. It must be greater than or equal to 1.' in configuration page.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario4() throws InterruptedException
	{
		Reporter.log(scenario4);

		Reporter.log(steps);
		Reporter.log(step1);

		bannerPageWeb = login(aspAdminEmail, newPassword);

		Reporter.log(step2);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log(step3);

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);

		Reporter.log(step4Scenario4);

		aspConfigurationWeb.setDocumentAnalysisCronExpression("0 0/10 * * * ?");

		Reporter.log(step5);

		aspConfigurationWeb.saveConfigurations();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifySaveConfigurationMessage("Changes applied"));

		Reporter.log("ASP configuration page should save successfully with the manually provided cron expression in 'Document analysis cron expression' property.");

		Reporter.log("Confirmation message \"Your changes have been applied\" should display in the page.");

		/* Below Note is for Information Only no Verification Required as per Manual Tester */
		Reporter.log("Note: 'Document analysis cron expression' property defines the time frame when the cron job for AI/ML document processing will be executed.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario5() throws InterruptedException
	{
		Reporter.log(scenario5);

		Reporter.log(steps);
		Reporter.log(step1);

		bannerPageWeb = login(aspAdminEmail, newPassword);

		Reporter.log(step2);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log(step3);

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);

		Reporter.log(step4Scenario5);

		aspConfigurationWeb.setDocumentAnalysisCronExpression("Test@123");

		Reporter.log(step5);

		aspConfigurationWeb.saveConfigurations();

		Reporter.log(expected);

		Assert.assertTrue(aspConfigurationWeb.verifyCronExpressionValidationMessage("Document analysis cron expression is invalid"));

		Reporter.log("User should not be able to save ASP configuration page with invalid values in 'Document analysis cron expression' property.");

		Reporter.log("It should display a validation message 'Document analysis cron expression is invalid.' in configuration page.");

		/* Below Note is for Information Only no Verification Required as per Manual Tester */
		Reporter.log("Note: 'Document analysis cron expression' property defines the time frame when the cron job for AI/ML document processing will be executed.");

		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario6() throws InterruptedException
	{
		Reporter.log(scenario6);
		Reporter.log(precon);
		Reporter.log("preconScenario6");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableDocumentAnalysisByHighQ(optionTrue);
		aspConfigurationWeb.setEnableDocumentAnalysisByKira(optionTrue);
		aspConfigurationWeb.saveConfigurations();
		logout();

		Reporter.log(steps);

		Reporter.log("1. Login with System Admin user.");
		Reporter.log("2. Click on User avatar from Top Navigation >> System admin >> System settings.");
		Reporter.log("3. 3. Check weather 'Document analysis(highq/Kira/Leverton)' option is displaying or not in system settings page.");

		bannerPageWeb = login(systemAdminEmail, newPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log(expected);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("'Document analysis Engine(highq/Kira/Leverton)' option with drop down menu should display in System settings page when 'Enable document analysis(highq/Kira/Leverton)' option is set as TRUE from ASP Admin >> Configuration.");

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE));

		Reporter.log("Values in drop down menu as: Enable Default ON (Default) in every site, Enable Default OFF in every site and Disable");

		logout();

		Reporter.log(steps);

		Reporter.log("4. Now Login with ASP Admin user.");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();

		Reporter.log("5. Go to ASP Admin >> Configuration; Mark the 'Enable document analysis Engine(highq/Kira/Leverton)' status as 'FALSE' then click on Save button.");

		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableDocumentAnalysisByHighQ(optionFalse);
		aspConfigurationWeb.setEnableDocumentAnalysisByKira(optionFalse);
		aspConfigurationWeb.saveConfigurations();
		logout();
		Reporter.log("6. Now check again with the system admin user weather 'Document analysis Engine(highq/Kira/Leverton)' option is displaying or not in system settings page.");

		bannerPageWeb = login(systemAdminEmail, newPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		Reporter.log(expected);

		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyDocumentAnalysisOptionWithDropDown());

		Reporter.log("'Document analysis Engine(highq/Kira/Leverton)' option should not display in System settings page when 'Enable document analysis(highq/Kira/Leverton)' option is set as FALSE from ASP Admin >> Configuration.");
		logout();
	}

	/**
	 * @throws InterruptedException
	 */
	private void scenario7() throws InterruptedException
	{
		Reporter.log(scenario7);
		Reporter.log(precon);
		Reporter.log("'Enable document analysis' must be set as True from ASP Admin >> configuration.");
		Reporter.log("'Document analysis' must be set as ON from System settings page.");

		bannerPageWeb = login(aspAdminEmail, newPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnLeftPanelLabel(docAnalysisConfig);
		aspConfigurationWeb.setEnableDocumentAnalysisByHighQ(optionTrue);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromHighqEngineDropdown(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE);
		systemAdminSystemSettingsWeb.saveSettings();
		logout();

		Reporter.log(steps);

		Reporter.log("1. Login with System Admin user.");

		bannerPageWeb = login(systemAdminEmail, newPassword);

		Reporter.log("2. Go to any Site >> Site Admin >> Module settings >> File.");
		Reporter.log("3. Check weather 'Enable Document analysis(highq/Kira/Leverton)' option is displaying or not under Document analysis engine configuration.");
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		Assert.assertTrue(adminFilesWeb.verifyDocumentAnalysisEngineConfigurationDisdplay());

		Reporter.log("'Enable Document analysis' option with ON (Default)  as selected in dropdown should be  display in Site Admin >> File page when 'Enable document analysis(highq/Kira/Leverton)' option is set as TRUE from ASP Admin >> Configuration & 'Document analysis(highq/Kira/Leverton)' is set as Enable Default ON (Default) in every from System settings page.");
		Reporter.log("By default setting should be display which is selected at system level(i.e if system level engine setting is  “Enable Default ON (Default) in every” then site level display ON (Default))");
		Reporter.log(steps);

		Reporter.log("4. Now Enable Default ON (Default) in every 'Document analysis engine configuration' property in system settings page or mark it as 'FALSE' in ASP configuration page.");

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectOptionFromHighqEngineDropdown(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_DISABLED);
		systemAdminSystemSettingsWeb.saveSettings();

		closeSaveChangesMessage();

		dashboardWeb = gotoDashboardByClickOnLogo();

		Reporter.log("5. Go to any site >> Site Admin >> Module settings >> File, check again weather 'Enable Document analysis' option is displaying or not under Advanced settings page.");

		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		Assert.assertTrue(!adminFilesWeb.verifyDocumentAnalysisEngineConfigurationDisdplay());

		Reporter.log("'Document analysis engine configuration' option should not display in Site Admin >> Module settings >> File, when 'Enable document analysis' option is set as FALSE from ASP Admin >> Configuration or 'Document analysis' property is set as 'Disable' from System settings page.");

		logout();

		/* Below Note is for Information Only no Verification Required as per Manual Tester */

		Reporter.log("Note: 'Document analysis configuration' property in ASP configuration page & 'Document analysis engine configuration' property in System settings is mandatory to be turned ON to display 'Enable Document analysis' in Site Admin >> Module settings >> File. If any one of the property is disabled from ASP configuration or system settings then 'Document analysis engine configuration' option will not display at Site level. There is multiple Engine if anyone engine OFF at asp admin/ system admin then it will not display in site admin>> module setting >> file");
	}

	/**
	 * @throws InterruptedException
	 */
	private void resetDocumentAnalysisConfiguration() throws InterruptedException
	{
		aspConfigurationWeb.resetEnableDocumentAnalysisByHighq();
		aspConfigurationWeb.resetEnableDocumentAnalysisByKira();
		aspConfigurationWeb.resetEnableDocumentAnalysisByLeverton();
		aspConfigurationWeb.resetEnableAITraining();
		aspConfigurationWeb.resetDocumentAnalysisCronExpression();
		aspConfigurationWeb.resetNoOfDocs();
		aspConfigurationWeb.saveConfigurations();
	}

}
