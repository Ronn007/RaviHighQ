package com.highq.test.wiki;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.SearchUserPageData;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserCreateGroupPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.GroupModulePermissionPage;
import com.highq.pageobjects.base.InsertSiteUserPage;
import com.highq.pageobjects.base.InsertSystemUserPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulePermissionPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.OrgAdminPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminAddUsersPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.AdminUserGroupsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.DashboardWeb;
import com.highq.pageobjects.pages.InsertSiteUserWeb;
import com.highq.pageobjects.pages.InsertSystemUserWeb;
import com.highq.pageobjects.pages.SearchUserPageWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;

public class BaseWikiTest extends BannerPageWeb // implements BaseWikiPage
{
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;

	By errorMessage = By.xpath("//span[contains(text(),'An error occurred while creating the group')]");

	public BaseWikiTest(WebDriver driver)
	{
		this.driver = driver;
	}

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	// DashboardPage dashboardWeb = new DashboardWeb(driver);
	// SystemAdminPage systemAdminWeb = new SystemAdminWeb(driver);
	// SearchUserPage searchUserweb = new SearchUserPageWeb(driver);
	// SystemAdminAddUsersPage addUsersWeb = new SystemAdminAddUsersWeb(driver);
	// GroupModulePermissionPage groupModulePermissionWeb = new GroupModulePermissionWeb(driver);
	// ModulePermissionPage modulePermissionWeb = new ModulePermissionWeb(driver);
	//
	// AdminUserCreateGroupPage adminUserCreateGroupsWeb = new AdminUserCreateGroupsWeb(driver);
	//
	// InsertSystemUserPage insertSystemUserWeb = new InsertSystemUserWeb(driver);
	// InsertSiteUserPage insertSiteUserWeb = new InsertSiteUserWeb(driver);
	// OrgAdminPage orgAdminWeb = new OrgAdminWeb(driver);
	// LoginPage loginPageWeb = new LoginPageWeb(driver);

	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	SystemAdminAddUsersPage addUsersWeb;
	GroupModulePermissionPage groupModulePermissionWeb;
	ModulePermissionPage modulePermissionWeb;

	AdminUserCreateGroupPage adminUserCreateGroupsWeb;

	InsertSystemUserPage insertSystemUserWeb;
	InsertSiteUserPage insertSiteUserWeb;
	OrgAdminPage orgAdminWeb;
	LoginPage loginPageWeb;

	Map<Boolean, BannerPageWeb> result;

	// public BaseWikiTest(WebDriver driver)
	// {
	// this.driver = driver;
	// }
	//

	public String getUserData(String dataToBeFormatted)
	{
		String result = null;
		if (dataToBeFormatted.contains("."))
		{
			String userDetails[] = dataToBeFormatted.split(".");
			result = StringUtils.capitalize(userDetails[0]) + " " + StringUtils.capitalize(userDetails[1]);
		}
		else
		{
			result = StringUtils.capitalize(dataToBeFormatted.toLowerCase());
		}

		return result;
	}

	public SystemAdminWeb setOrganisation(Map<String, Map<String, Boolean>> orgData) throws InterruptedException
	{
		dashboardWeb = new DashboardWeb(driver);
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();

		orgAdminWeb = systemAdminWeb.gotoOrgAdmin();

		orgAdminWeb.verifyOrgPermission(orgData);
		systemAdminWeb = orgAdminWeb.backToSystemAdmin();

		return new SystemAdminWeb(driver);
	}

	public SearchUserPageWeb createAndResetUsers(Map<String, List<String>> userMap, String newPassword, SearchUserPageData userStausData) throws InterruptedException
	{
		systemAdminWeb = new SystemAdminWeb(driver);
		searchUserweb = systemAdminWeb.gotoUserAdmin();
		addUsersWeb = searchUserweb.gotoAddUser();
		addUsersWeb.enterEmailIds(userMap);
		result = addUsersWeb.clickOnNext();
		if (result.get(true) != null)
		{
			insertSystemUserWeb = (InsertSystemUserWeb) result.get(true);
			insertSystemUserWeb.setOrg(userMap);
			searchUserweb = insertSystemUserWeb.clickOnSave();

			// set password method is used to perform search user and reset password of respective users
			searchUserweb = searchUserweb.setPassword(userMap, newPassword, userStausData);
		}
		else
		{
			searchUserweb = (SearchUserPageWeb) result.get(false);
		}

		return new SearchUserPageWeb(driver);
	}

	public SearchUserPageWeb setUserRoles(Map<String, Map<String, Map<UserPermission, Boolean>>> users, UserStatus status, boolean locked) throws InterruptedException
	{
		searchUserweb = new SearchUserPageWeb(driver);
		searchUserweb.setUserRoles(users, UserStatus.Active, false);

		return new SearchUserPageWeb(driver);
	}

	public AdminPageWeb createSiteUsersWithRoles(Map<String, List<String>> userMap, Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.selectUserFromUserPermissions();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(userMap);
		result.clear();
		result = addUserWeb.initiateUserCreationProcess();

		if (result.get(true) != null)
		{
			insertSiteUserWeb = (InsertSiteUserWeb) result.get(true);
			insertSiteUserWeb.setOrg(userMap);
			addUserWeb = insertSiteUserWeb.assignRolesAndfinishUserCreationProcess(userDataAndRoles);
		}
		else
		{
			addUserWeb = (AddUserWeb) result.get(false);
		}
		adminPageWeb = addUserWeb.gotoAdminModule();

		return new AdminPageWeb(driver);
	}

	public AdminPageWeb createSiteUsers(Map<String, List<String>> userMap)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.selectUserFromUserPermissions();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(userMap);
		result.clear();
		result = addUserWeb.initiateUserCreationProcess();

		if (result.get(true) != null)
		{
			insertSiteUserWeb = (InsertSiteUserWeb) result.get(true);
			insertSiteUserWeb.setOrg(userMap);
			addUserWeb = insertSiteUserWeb.finishUserCreationProcess();
		}
		else
		{
			addUserWeb = (AddUserWeb) result.get(false);
		}
		adminPageWeb = addUserWeb.gotoAdminModule();

		return new AdminPageWeb(driver);
	}

	public AdminUserGroupsWeb createUserGroup(String groupName, Map<String, List<String>> userMap) throws IOException
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminUserGroupsWeb = adminPageWeb.selectGroupFromUserPermissions();
		adminUserGroupsWeb.clickOnAddDropdownList();

		adminUserCreateGroupsWeb = adminUserGroupsWeb.clickOnNewSiteGroup();

		/** creating group by adding users from various organizations *****/
		adminUserCreateGroupsWeb.setGroupName(groupName);

		adminUserCreateGroupsWeb.addUsersInGroup(userMap);
		adminUserGroupsWeb = adminUserCreateGroupsWeb.clickSaveButton();

		if (isDisplayed(errorMessage))
		{
			adminUserGroupsWeb = adminUserCreateGroupsWeb.clickCancelButton();
		}

		return new AdminUserGroupsWeb(driver);
	}

	public AddUserWeb setModulePermissionsForMultipleUsers(Map<String, String> userData, Map<String, Map<String, Boolean>> modulePermission)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.selectUserFromUserPermissions();

		for (Map.Entry<String, String> userInfo : userData.entrySet())
		{
			String domain = userInfo.getKey();
			String userId = userInfo.getValue();
			modulePermissionWeb = addUserWeb.openUserPermissions(domain, userId, "Modules");
			addUserWeb = modulePermissionWeb.setModulePermissionForUsers(modulePermission);
		}

		return new AddUserWeb(driver);

	}

	public AddUserWeb setModulePermissionsForUser(String orgName, String userName, Map<String, Map<String, Boolean>> modulePermission)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.selectUserFromUserPermissions();

		modulePermissionWeb = addUserWeb.openUserPermissions(orgName, userName, "Modules");
		modulePermissionWeb.setModulePermissionForUsers(modulePermission);

		return new AddUserWeb(driver);
	}

}
