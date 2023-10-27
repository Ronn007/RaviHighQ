package com.highq.test.mysite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class MyTasks_TC0216 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = AspAndSystemAdmin.ASPADMIN_CONFIGURATION_UI_OPTION_ON;
	String[] userNames = {"normaluser"};
	String orgType = "Internal";
	String domain = "task216.com";
	String newPassword = "Password@123";
	String[] tasks = {"task-1", "task-2", "task-3"};
	String[] importTasks = {"TEST TITLE 1", "TEST TITLE 2"};
	String excelFileName = "MyTasks_TC0216.xlsx";
	String importOption = TaskLabels.TASKS_IMPORT_FROM_EXCEL;
	String printOption = TaskLabels.TASKS_PRINT_PREVIEW;
	String exportPDFOption = TaskLabels.TASKS_EXPORT_TO_PDF;
	String exportExcelOption = TaskLabels.TASKS_EXPORT_TO_EXCEL;

	TasksPage tasksWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	ConfigurationData configurationData = new ConfigurationData();

	@Test(priority = 1)
	public void MyTasksTC0216() throws InterruptedException
	{
		precondition();
		sitePrecondition();
		scenario1();
		scenario2();
	}

	private void precondition() throws InterruptedException
	{
		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		String[] configurationList = {"setOrg", "addSystemAdminUsers"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();

	}

	private void sitePrecondition() throws InterruptedException
	{
		/** Enable My Tasks */
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyTasks(optionToSelect);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		tasksWeb = bannerPageWeb.goToMyTasks();
		tasksWeb.deleteAllTasks();

		/** Add few tasks */
		for (int i = 0; i < tasks.length; i++)
		{
			tasksWeb.clickHeaderAddButton();
			tasksWeb.setTaskTitle(tasks[i]);
			tasksWeb.clickOnAddInAddTaskModal();
		}

	}

	private void scenario1() throws InterruptedException
	{
		myTasksHomePageCase1();
		myTasksHomePageCase2();
		individualTaskCase1();
		individualTaskCase2();
	}

	private void myTasksHomePageCase1()
	{
		tasksWeb.selectMoreActionOption(exportPDFOption);
		tasksWeb.selectMoreActionOption(exportExcelOption);

		/** PDF and excel file verification is pending */
	}

	private void myTasksHomePageCase2() throws InterruptedException
	{
		tasksWeb.selectMoreActionOption(printOption);
		tasksWeb.switchWindow();

		/** Verify task visibility in print preview */
		for (int i = 0; i < tasks.length; i++)
		{
			Assert.assertTrue(tasksWeb.verifyTaskVisiblityInPrintPreview(tasks[i]));
		}
		tasksWeb.clickOnCloseButtonOfPrintPreview();
		tasksWeb.switchToParentWindow();
	}

	private void individualTaskCase1()
	{
		tasksWeb.selectTask(tasks[0]);
		tasksWeb.selectMoreActionOptionForIndividualTask(exportPDFOption);
		tasksWeb.closeTask();
		tasksWeb.clearSearch();
		/** PDF and excel file verification is pending */
	}

	private void individualTaskCase2()
	{
		tasksWeb.selectTask(tasks[0]);
		tasksWeb.selectMoreActionOptionForIndividualTask(printOption);
		tasksWeb.switchWindow();

		/** Verify individual task visibility in print preview */
		Assert.assertTrue(tasksWeb.verifyTaskVisiblityInPrintPreviewForIndividualTask(tasks[0]));
		tasksWeb.clickOnCloseButtonOfPrintPreview();
		tasksWeb.switchToParentWindow();
		tasksWeb.closeTask();
		tasksWeb.clearSearch();
	}

	private void scenario2() throws InterruptedException
	{

		/** Import task */
		tasksWeb.selectMoreActionOption(importOption);
		tasksWeb.importExcelFile(excelFileName);

		/** Verify if all the tasks are imported */
		for (int i = 0; i < importTasks.length; i++)
		{
			Assert.assertTrue(tasksWeb.verifyTaskVisibility(importTasks[i]));
		}

	}
}
