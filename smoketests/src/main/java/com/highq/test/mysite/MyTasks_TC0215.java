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
import com.highq.labels.collaborate.TaskLabels;
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
 * @author badal.gandhi
 */
public class MyTasks_TC0215 extends BannerPageWeb
{
	/** Favourite Task */

	private WebDriver driver;
	String sysAdminEmail = "ravi.middha@highq.com";// "auto@highq.com";//"ravi.middha@highq.com";//"admin.user@highq.com";//"tom.chick@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "MyTasks_TC0215";
	String[] userNames = {"normaluserforlogin", "site.admin"};
	String domain = "sitelevel0215.com";
	String newPassword = "Password@123";
	String orgType = "Internal";

	String taskTitle = "MyTasks_TC0215";
	String status = "Not started";

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
	public void MyTasksTC0215() throws InterruptedException, IOException
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
		if (!tasksWeb.verifyTaskVisibility(taskTitle))
		{
			tasksWeb.clickHeaderAddButton();
			tasksWeb.setTaskTitle(taskTitle);
			Map<String, String> addTaskData = new LinkedHashMap<>();
			addTaskData.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle);
			addTaskData.put(TaskLabels.TASKS_ADDTASK_STATUS, status);
			tasksWeb.setTaskAddData(addTaskData);
			tasksWeb.clickOnAddTaskButtonInModel();
			Assert.assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));
		}

		/** Favourite a task */
		tasksWeb.selectTask(taskTitle);
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.addTaskToFavourites();
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());

		/** Verify same task is available in favourites tab */
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));

		tasksWeb.selectTask(taskTitle);
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());

		clickOnFavourite();
		Assert.assertTrue(searchInFavourite(taskTitle));

		/** Remove task from favourites */
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(tasksWeb.verifyTaskVisibility(taskTitle));
		tasksWeb.selectTask(taskTitle);
		assertTrue(tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.removeTaskFromFavourites();
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());
		tasksWeb.clickFavouritesTabInTask();
		assertTrue(!tasksWeb.verifyTaskVisibility(taskTitle));

		/** Verify favourite star is no longer selected for task */
		tasksWeb.selectTask(taskTitle);
		assertTrue(!tasksWeb.verifyFavouriteTaskIconIsSelected());

		tasksWeb = goToMyTasks();
		clickOnFavourite();
		Assert.assertTrue(!searchInFavourite(taskTitle));

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
