package com.highq.test.mysite;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class MyTasks_TC0219 extends BannerPageWeb
{
	/** Search Task */

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "MyTasks_TC0219";
	String[] userNames = {"normaluserforlogin", "site.admin"};
	String domain = "sitelevel0219.com";
	String newPassword = "Password@123";
	String orgType = "Internal";

	String dummyTask = "task" + getRandomString();
	String[] tasks = {"task-1", "task-2", "task-3"};
	String startsWithText = "task";

	DocumentPage documentWeb;
	TasksPage tasksWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;
	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void MyTasksTC0219() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario01();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();
	}

	private void scenario01() throws InterruptedException, IOException
	{

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);

		tasksWeb = bannerPageWeb.goToMyTasks();

		/** Add task if it is not available */
		for (int i = 0; i < tasks.length; i++)
		{
			if (!tasksWeb.verifyTaskVisibility(tasks[i]))
			{
				tasksWeb.clickHeaderAddButton();
				tasksWeb.setTaskTitle(tasks[i]);
				tasksWeb.clickOnAddInAddTaskModal();
			}
		}
		/** Verify task via search */
		for (int i = 0; i < tasks.length; i++)
		{
			assertTrue(tasksWeb.verifyTaskVisibility(tasks[i]));
		}

		/** Verify task is not displayed in search results if it is not created */
		assertTrue(!tasksWeb.verifyTaskVisibility(dummyTask));

		/** Verify all the task with required start text are displayed in search result */
		tasksWeb.searchTask(startsWithText);
		assertTrue(tasksWeb.verifyTaskStartsWith(startsWithText));

		tasksWeb.gotoDashboard();
		logout();
	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

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
}
