package com.highq.test.dashboard;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author surbhi.khetan
 */
public class Dashboard_TCR0247 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	TasksPage tasksWeb;

	String jsonFileName = "preConfiguration_Dashboard_TCR0247.json";
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String password = "Password@123";
	String siteName = "Dashboard_TCR0247";
	String userName = "dashboard.normaluser247";
	String domain = "dashboard247.com";
	String taskTitle = "Task_" + getRandomString();
	String personalTask = "Personal" + getRandomString();

	@Test
	public void dashboardTCR0247() throws IOException, JSONException, InterruptedException
	{

		siteAndUserConfiguration();
		scenario1();
	}

	private void scenario1()
	{

		bannerPageWeb = login(userName + "@" + domain, password);
		dashboardWeb = gotoDashboard();
		dashboardWeb.clickOnAssignedToMeTab();

		/** Verify assigned tasks to user */
		Assert.assertTrue(dashboardWeb.verifyTaskVisibility(taskTitle));

		dashboardWeb.clickOnAddTask();
		/** Verify text box area */
		Assert.assertTrue(dashboardWeb.verifyTaskTextBox());
		/** Verify task date picker */
		Assert.assertTrue(dashboardWeb.verifyTaskDatePicker());

		dashboardWeb.addPersonalTask(personalTask);
		/** Verify personal task of user */
		Assert.assertTrue(dashboardWeb.verifyTaskVisibility(personalTask));

		dashboardWeb.clickOnTaskFromPersonalTab(personalTask);
		/** Verify re-direction to my tasks page */
		Assert.assertTrue(tasksWeb.verifyViewTaskTitle(personalTask));
		Assert.assertTrue(dashboardWeb.verifyMyTasksTab());

		dashboardWeb = gotoDashboard();
		dashboardWeb.clickSeeAllTask();
		/** Verify re-direction to my tasks page */
		Assert.assertTrue(dashboardWeb.verifyMyTasksTab());

	}

	/** User and site setup */
	private void siteAndUserConfiguration() throws IOException, JSONException, InterruptedException
	{
		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		/** Delete all tasks */
		bannerPageWeb = login(userName + "@" + domain, password);
		tasksWeb = bannerPageWeb.gotoTaskModule();
		tasksWeb.deleteAllTasks();
		tasksWeb.goToMyTasks();
		tasksWeb.deleteAllTasks();
		logout();

		/** Add task */
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		tasksWeb = dashboardWeb.gotoTaskModule();
		tasksWeb.clickHeaderAddButton();
		Map<String, String> addTaskData = new LinkedHashMap<>();
		addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle);
		addTaskData.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, userName + "@" + domain);
		tasksWeb.setTaskAddData(addTaskData);
		tasksWeb.clickOnAddTaskButtonInModel();
		logout();
	}

}
