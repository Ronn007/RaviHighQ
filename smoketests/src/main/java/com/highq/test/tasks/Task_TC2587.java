package com.highq.test.tasks;

import java.io.File;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminAuditsSiteManagementPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSiteSummaryPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author jyoti.raj
 */
public class Task_TC2587 extends BannerPageWeb
{

	private WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	String jsonFileName = "preConfiguration_Task_TC2587.json";

	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser
					.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String templateName1 = "Template1" + getRandomString();
	String templateName2 = "Template2" + getRandomString();
	String enableTimelineViewInTask = "Enable timeline view in task";
	String yes = "Yes";
	String no = "No";
	String taskView = "Task view";

	DashboardPage dashboardPage;
	AdminPage adminPage;
	AdminTaskPage adminTaskPage;
	AdminAuditsSiteManagementPage adminAuditsSiteManagementPage;
	AdminSiteSummaryPage adminSiteSummaryPage;
	TasksPage tasksPage;

	@Test(priority = 1)
	public void TaskTC2587() throws InterruptedException, IOException, JSONException
	{
		preconfiguration();
		scenario1();
		scenario2();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

	private void scenario1()
	{
		login(sysAdminEmail, defaultPassword);

		enableDisableTimelineViewFormSiteAdmin(false);

		Assert.assertTrue(adminTaskPage.verifyEnableTimelineViewIsDisplayedOrNot());
		Assert.assertFalse(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());

		adminTaskPage.enableTimelineView(true);
		adminTaskPage.saveTaskChanges();

		Assert.assertTrue(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());

		Assert.assertTrue(adminTaskPage.verifyDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW));

		adminAuditsSiteManagementPage = adminPage.clickSiteManagementInLeftPanel();
		adminAuditsSiteManagementPage.clickOnSubmit();
		adminAuditsSiteManagementPage.clickOnActionNameOnSiteManagementAudit(1);

		Assert.assertTrue(adminAuditsSiteManagementPage.verifyActionDetails(enableTimelineViewInTask, no, yes));
		adminAuditsSiteManagementPage.clickOnCancelInActionDeTails();

		adminSiteSummaryPage = adminPage.clickSiteSummaryInLeftPanel();
		adminSiteSummaryPage.clickOnSaveAsTemplate();
		adminSiteSummaryPage.enterTemplateName(templateName1);
		adminPage = adminSiteSummaryPage.clickOnSaveInSaveAsTemplate();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		Assert.assertTrue(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());

		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(false);
		adminTaskPage.saveTaskChanges();

		Assert.assertFalse(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());
		Assert.assertFalse(adminTaskPage.verifyDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW));

		adminAuditsSiteManagementPage = adminPage.clickSiteManagementInLeftPanel();
		adminAuditsSiteManagementPage.clickOnSubmit();
		adminAuditsSiteManagementPage.clickOnActionNameOnSiteManagementAudit(1);

		Assert.assertTrue(adminAuditsSiteManagementPage.verifyActionDetails(enableTimelineViewInTask, yes, no));
		adminAuditsSiteManagementPage.clickOnCancelInActionDeTails();

		adminSiteSummaryPage = adminPage.clickSiteSummaryInLeftPanel();
		adminSiteSummaryPage.clickOnSaveAsTemplate();
		adminSiteSummaryPage.enterTemplateName(templateName2);
		adminPage = adminSiteSummaryPage.clickOnSaveInSaveAsTemplate();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		Assert.assertFalse(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());
		logout();

	}

	private void scenario2()
	{
		login(sysAdminEmail, defaultPassword);
		dashboardPage = gotoDashboard();

		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();

		String defaultViewName = adminTaskPage.getDefaultView();

		adminTaskPage.enableTimelineView(true);
		adminTaskPage.saveTaskChanges();
		Assert.assertTrue(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());
		Assert.assertTrue(adminTaskPage.verifyDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW));

		adminTaskPage.selectDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();
		Assert.assertTrue(adminTaskPage.verifyDefaultView(TaskLabels.TASKS_TIMELINEVIEW));

		tasksPage = adminTaskPage.gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyTimelineViewRenderInTaskModule());

		adminPage = tasksPage.gotoAdminModule();
		adminAuditsSiteManagementPage = adminPage.clickSiteManagementInLeftPanel();
		adminAuditsSiteManagementPage.clickOnSubmit();
		adminAuditsSiteManagementPage.clickOnActionNameOnSiteManagementAudit(1);

		Assert.assertTrue(adminAuditsSiteManagementPage.verifyActionDetails(taskView, defaultViewName,
				TaskLabels.TASKS_TIMELINEVIEW));
		adminAuditsSiteManagementPage.clickOnCancelInActionDeTails();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.selectDefaultViewFromDropDown(TaskLabels.TASKS_CARDVIEW);
		adminTaskPage.saveTaskChanges();

		Assert.assertFalse(adminTaskPage.verifyDefaultView(TaskLabels.TASKS_TIMELINEVIEW));

		tasksPage = adminTaskPage.gotoTaskModule();
		Assert.assertTrue(tasksPage.verifyCardViewRenderInTaskModule());

		adminPage = tasksPage.gotoAdminModule();
		adminTaskPage = adminPage.clickTasksInLeftPanel();

		adminTaskPage.enableTimelineView(false);
		adminTaskPage.saveTaskChanges();

		Assert.assertFalse(adminTaskPage.verifyEnableTimelineViewIsSelectedOrNot());
		Assert.assertFalse(adminTaskPage.verifyDefaultViewFromDropDown(TaskLabels.TASKS_TIMELINEVIEW));
	}

	private void enableDisableTimelineViewFormSiteAdmin(boolean flag)
	{
		dashboardPage = gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();

		adminTaskPage = adminPage.clickTasksInLeftPanel();
		adminTaskPage.enableTimelineView(flag);
		if (flag == true)
			adminTaskPage.setDefaultView(TaskLabels.TASKS_TIMELINEVIEW);
		adminTaskPage.saveTaskChanges();
	}

}
