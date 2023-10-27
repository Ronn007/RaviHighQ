package com.highq.test.aiml;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class AIML_TCR0237 extends BannerPageWeb {
	public String sysAdminEmail = "ravi.middha@highq.com";
	public String sysAdminPassword = "Password@123";

	BannerPage bannerPageWeb;
	AdminPage adminWeb;
	AdminFilesPage adminFilesWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage sysAdminSystemSettingsWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFilesTypeWeb;
	SystemAdminPage sysAdminWeb;
	EditFileIconsPage editFileIconsWeb;
	DashboardPage dashboardWeb;

	String expectedListValues[] = { "TRUE", "FALSE" };
	String defaultCronExpression = "0 0/5 * * * ?";
	String defaultNoofDocs = "1000";
	String allEngines[] = { "highq", "Kira" };
	String dropdownValuesOfEngine[] = { " Enable Default ON (Default) in every site,",
			"Enable Default OFF in every site", "Disabled" };

	String expectedCronExprsn = "0 0/5 * * * ?";
	String expeectedDocCount = "1000";
	String expectedSaveChangesMsg = "Changes applied";
	String expectedInvalidDocCountAlertmsg = "No. of documents to be processed in a job must be positive integer.";
	String expectedInvalidDocCountAlertmsg0input = "No. of documents to be processed in a job is not valid. It must be greater than or equal to 1.";
	String expectedInvalidCronExprsnMsg = "Document analysis cron expression is invalid";

	String siteName = "AIML_TCR0237";
	String expeEnableDocAnalysisTxt_File = "Enable document analysis";
	String expEditIconPageHeading = "Edit file icon";
	String expDocAnalysisSprtText = "Document analysis support";
	String extension = "docx";
	String docCount = "1001";
	String cronExprsn = "0 0/10 * * * ?";

	@BeforeTest
	public void init() {

	}

	@Test
	public void SystemAndSiteLevelConfig() throws InterruptedException {
		Scenario1();
		Scenario2();
		Scenario3();
		Scenario4();
		Scenario5();
		Scenario8();
		// to do
		// Scenario();

		 Scenario11();
	}

	private void Scenario11() throws InterruptedException {
		enableDocAnalysisEngine();
		gotoSystemSettings();
		for (String engine : allEngines) {
			sysAdminSystemSettingsWeb.selectDocumentAnalysis(engine, dropdownValuesOfEngine[0]);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminWeb = dashboardWeb.gotoAdminModule();
			adminWeb.expandModuleSettings();
			adminFilesWeb = adminWeb.clickFilesInLeftPanel();
			Assert.assertTrue(adminFilesWeb.verifyDocumentAnalysisEngineConfigurationDisdplay());
			Assert.assertTrue(adminFilesWeb.verifydocumentAnalysisEngineDisplay(engine));

			// To verify Dropdown visibility in system admin page

		}

		// to verify above conditions when settings turned off (-ve testcase)

	}

	private void Scenario10() throws InterruptedException {
		enableDocAnalysisEngine();
		gotoSystemSettings();
		sysAdminSystemSettingsWeb.verifyDocumentAnalysisEngineWithDropdown(allEngines[0]);
		for (String engineName : allEngines) {
			sysAdminSystemSettingsWeb.verifyDocumentAnalysisHighQDropdownValues(engineName);
			sysAdminSystemSettingsWeb.clickonDocumentAnalysisEngineConfigurationDropdown(engineName);
		}
	}

	private void enableDocAnalysisEngine() throws InterruptedException {
		gotoASPAdmin();
		aspAdminWeb.openConfigurationPage();
		aspConfigWeb.clickOnLeftPanelLabel("Document analysis configuration");
		aspConfigWeb.enableDocumentAnalysisByHighQ("TRUE");
		aspConfigWeb.enableDocumentAnalysisByKira("TRUE");
		aspConfigWeb.enableDocumentAnalysisByLeverton("TRUE");

	}

	private void Scenario8() throws InterruptedException {
		String[] extension = { "doc", "docx", "pdf", "odt", "txt", "ppt", "jpg" };
		gotoSysAdminFileOrFileTypes();
		for (int i = 0; i < extension.length; i++) {
			editFileIconsWeb = sysAdminFileOrFilesTypeWeb.gotoDocumentEditPage(extension[i].toLowerCase());
			Assert.assertEquals(editFileIconsWeb.getEditFileIconPageTitle().trim(), expEditIconPageHeading.trim());
			Assert.assertTrue(editFileIconsWeb.verifyVisibilityDocAnalysisSprtText());
			Assert.assertEquals(editFileIconsWeb.getDocAnalysisSprtText(), expDocAnalysisSprtText);
			Assert.assertTrue(editFileIconsWeb.verifyDocumentAnalysisSupportCheckboxDisplay());
			switch (extension[i].toLowerCase()) {
			case "doc":
			case "docx":
			case "pdf":
			case "odt":
			case "txt":
				Assert.assertTrue(editFileIconsWeb.getStateDocAnalysisSupportCheckbox());
				break;
			default:
				Assert.assertFalse(editFileIconsWeb.getStateDocAnalysisSupportCheckbox());
				break;
			}
			editFileIconsWeb.clickCancel();
		}
		sysAdminFileOrFilesTypeWeb.clickAddButton();
		Assert.assertTrue(editFileIconsWeb.verifyVisibilityDocAnalysisSprtText());
	}

	private void Scenario5() throws InterruptedException {

		gotoAspAdminDocAnalysis();
		aspConfigWeb.setDocumentAnalysisCronExpression(getRandomString());
		aspConfigWeb.saveConfigurations();

		Assert.assertTrue(aspConfigWeb.verifyCronExpressionValidationMessage(expectedInvalidCronExprsnMsg));
	}

	private void Scenario4() throws InterruptedException {
		gotoAspAdminDocAnalysis();
		aspConfigWeb.setDocumentAnalysisCronExpression(cronExprsn);

		aspConfigWeb.saveConfigurations();

		Assert.assertTrue(aspConfigWeb.verifySaveConfigurationMessage(expectedSaveChangesMsg));
		Assert.assertTrue(aspConfigWeb.verifyCronExpressionValue(cronExprsn));

		// return to default values
		closeSaveChangesMessage();
		aspConfigWeb.resetDocumentAnalysisCronExpression();
		aspConfigWeb.saveConfigurations();
		closeSaveChangesMessage();
	}

	private void Scenario3() throws InterruptedException {

		gotoAspAdminDocAnalysis();

		aspConfigWeb.setNoOfDocsInJob(getRandomString());
		aspConfigWeb.saveConfigurations();
		String actualAlertmsg = "";
		if (isAlertPresent()) {
			actualAlertmsg = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
		}
		Assert.assertEquals(actualAlertmsg, expectedInvalidDocCountAlertmsg);

		// Enter doc count as 0 and verify error message
		aspConfigWeb.setNoOfDocsInJob("0");
		aspConfigWeb.saveConfigurations();
		actualAlertmsg = "";
		if (isAlertPresent()) {
			actualAlertmsg = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();

		}
		Assert.assertEquals(actualAlertmsg, expectedInvalidDocCountAlertmsg0input);

	}

	private void Scenario2() throws InterruptedException {
		gotoAspAdminDocAnalysis();
		aspConfigWeb.setNoOfDocsInJob(docCount);
		aspConfigWeb.saveConfigurations();

		Assert.assertTrue(aspConfigWeb.verifySaveConfigurationMessage(expectedSaveChangesMsg));

		Assert.assertTrue(aspConfigWeb.verifyNoOfDocsValue(docCount));

		// return to default values
		closeSaveChangesMessage();
		aspConfigWeb.resetNoOfDocs();
		aspConfigWeb.saveConfigurations();
	}

	private void Scenario1() throws InterruptedException {
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		gotoASPConfiguration();
		Assert.assertTrue(aspConfigWeb.verifyLeftPanelLabel("Document analysis configuration"));

		aspConfigWeb.clickOnLeftPanelLabel("Document analysis configuration");
		Assert.assertTrue(aspConfigWeb.verifySearchEngineValues());
		Assert.assertTrue(aspConfigWeb.verifyDocumentAnalysisCronExprsnPresence());
		Assert.assertTrue(aspConfigWeb.verifyNoOfDocsPresence());
		Assert.assertTrue(aspConfigWeb.verifySearchEngineValues());

		for (String engine : allEngines) {
			Assert.assertEquals(aspConfigWeb.getDocumentAnalysisDropdownValues(engine),
					Arrays.asList(expectedListValues));
			Assert.assertEquals(aspConfigWeb.getDefaultDocumentAnalysisDropdownValue(engine), expectedListValues[1]);

			aspConfigWeb.setDocumentAnalysisDropdownValue(engine, "FALSE");
			aspConfigWeb.saveConfigurations();
			gotoSystemSettings();
			Assert.assertFalse(sysAdminSystemSettingsWeb.verifyDocumentAnalysisEngineWithDropdown(engine));

			/*
			 * To do for 'TRUE' condition
			 */
			aspConfigWeb.setDocumentAnalysisDropdownValue(engine, "TRUE");
			aspConfigWeb.saveConfigurations();

			gotoSystemSettings();
			Assert.assertTrue(sysAdminSystemSettingsWeb.verifyDocumentAnalysisEngineWithDropdown(engine));

			gotoASPConfiguration();

		}

		Assert.assertTrue(aspConfigWeb.verifyCronExpressionValue(defaultCronExpression));
		Assert.assertTrue(aspConfigWeb.verifyNoOfDocsValue(defaultNoofDocs));

	}

	private void gotoSystemSettings() {
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		sysAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
	}

	private void gotoASPConfiguration() throws InterruptedException {
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigWeb = aspAdminWeb.openConfigurationPage();
	}

	public void gotoAspAdminDocAnalysis() throws InterruptedException {
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigWeb = aspAdminWeb.openConfigurationPage();
		aspConfigWeb.clickOnLeftPanelLabel(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_DOCUMENTANALYSIS);
	}

	// public void gotoSysAdminDocAnalysis() throws InterruptedException {
	// bannerPageWeb.gotoSystemAdmin();
	// sysAdminWeb.gotoSystemSettings();
	// }
	//
	public void gotoSysAdminFileOrFileTypes() {
		sysAdminWeb = bannerPageWeb.gotoSystemAdmin();
		sysAdminFileOrFilesTypeWeb = sysAdminWeb.gotoFileOrFileTypes();

	}
}
