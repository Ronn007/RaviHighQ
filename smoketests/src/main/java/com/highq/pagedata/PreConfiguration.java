package com.highq.pagedata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
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
import com.highq.pageobjects.base.UserRolesPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.AdminUserGroupsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.DashboardWeb;
import com.highq.pageobjects.pages.InsertSiteUserWeb;
import com.highq.pageobjects.pages.InsertSystemUserWeb;
import com.highq.pageobjects.pages.SearchUserPageWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;
import com.highq.parsers.JSONParser;

public class PreConfiguration extends BannerPageWeb
{
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	AdminSecurityPage adminSecurityWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	UserRolesPage userRolesWeb;
	Map<Boolean, BannerPageWeb> result;

	By errorMessage = By.xpath("//span[contains(text(),'An error occurred while creating the group')]");
	By okInGroupSave = By.id("selectUserAlertConfirmationModal_saveUserAlertPermission");

	public PreConfiguration(WebDriver driver)
	{
		this.driver = driver;
	}

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

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

	public SystemAdminWeb setOrganisation(Map<String, Map<String, Boolean>> orgData) throws InterruptedException
	{
		dashboardWeb = new DashboardWeb(driver);
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();

		orgAdminWeb = systemAdminWeb.gotoOrgAdmin();

		orgAdminWeb.verifyOrgPermission(orgData);
		systemAdminWeb = orgAdminWeb.backToSystemAdmin();

		return new SystemAdminWeb(driver);
	}

	public SearchUserPageWeb createAndResetUsers(Map<String, List<String>> userMap, String newPassword, UserStatus status, boolean statusLocked) throws InterruptedException
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
			searchUserweb = searchUserweb.setPassword(userMap, newPassword, status, statusLocked);
		}
		else
		{
			searchUserweb = (SearchUserPageWeb) result.get(false);
			searchUserweb = searchUserweb.setPassword(userMap, newPassword, status, statusLocked);
		}

		return new SearchUserPageWeb(driver);
	}

	public SearchUserPageWeb createAndResetUsers(Map<String, List<String>> userMap, Map<String, String> userIdAndPassword, UserStatus status, boolean statusLocked) throws InterruptedException
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
			searchUserweb = searchUserweb.setPassword(userMap, userIdAndPassword, status, statusLocked);
		}
		else
		{
			searchUserweb = (SearchUserPageWeb) result.get(false);
			searchUserweb = searchUserweb.setPassword(userMap, userIdAndPassword, status, statusLocked);
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
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(userMap);
		result.clear();
		result = addUserWeb.initiateUserCreationProcess();

		if (result.get(true) != null)
		{
			insertSiteUserWeb = (InsertSiteUserWeb) result.get(true);
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
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(userMap);
		result.clear();
		result = addUserWeb.initiateUserCreationProcess();

		if (result.get(true) != null)
		{
			insertSiteUserWeb = (InsertSiteUserWeb) result.get(true);
			addUserWeb = insertSiteUserWeb.finishUserCreationProcess();
		}
		else
		{
			addUserWeb = (AddUserWeb) result.get(false);
		}
		adminPageWeb = gotoAdminModule();

		return new AdminPageWeb(driver);
	}

	public AdminPageWeb createSiteUsers(List<String> siteUsers)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(siteUsers);
		result.clear();
		result = addUserWeb.initiateUserCreationProcess();

		if (result.get(true) != null)
		{
			insertSiteUserWeb = (InsertSiteUserWeb) result.get(true);
			addUserWeb = insertSiteUserWeb.finishUserCreationProcess();
		}
		else
		{
			addUserWeb = (AddUserWeb) result.get(false);
		}
		adminPageWeb = gotoAdminModule();

		return new AdminPageWeb(driver);
	}

	public AdminUserGroupsWeb createUserGroup(ConfigurationData configurationData) throws IOException
	{
		adminPageWeb = new AdminPageWeb(driver);
		gotoAdminModule();
		adminUserGroupsWeb = adminPageWeb.clickGroupsInLeftPanel();
		adminUserGroupsWeb.addNewSiteGroupDetails(configurationData);

		adminUserGroupsWeb.saveNewSiteGroupChanges();

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

	public AdminPageWeb createSiteAndNavigateToAdmin(Map<String, String> siteData) throws InterruptedException
	{
		// dashboardWeb = new DashboardWeb(driver);
		// result = dashboardWeb.searchSite(siteData);
		dashboardWeb.searchSite(siteData);
		/*
		 * if (result.get(true) != null)
		 * {
		 * documentWeb = (DocumentPage) result.get(true);
		 */
		adminPageWeb = dashboardWeb.gotoAdminModule();
		/*
		 * }
		 * else
		 * {
		 * adminPageWeb = (AdminPageWeb) result.get(false);
		 * }
		 */

		return new AdminPageWeb(driver);
	}

	public AdminPageWeb createSiteAndNavigateToAdmin(String siteName) throws InterruptedException
	{
		dashboardWeb = new DashboardWeb(driver);
		// result = dashboardWeb.searchSite(siteName);
		dashboardWeb.searchSite(siteName);
		/*
		 * if (result.get(true) != null)
		 * {
		 * documentWeb = (DocumentPage) result.get(true);
		 */
		adminPageWeb = dashboardWeb.gotoAdminModule();
		/*
		 * }
		 * else
		 * {
		 * adminPageWeb = (AdminPageWeb) result.get(false);
		 * }
		 */
		return new AdminPageWeb(driver);
	}

	public AdminPageWeb setUserGroupPermission(boolean permission) throws InterruptedException
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.setUseGroupForPermissioning(permission);
		adminSecurityWeb.saveAdvancedChanges();
		adminPageWeb = gotoAdminModule();
		return new AdminPageWeb(driver);
	}

	public AdminPageWeb enableSiteModules(String... moduleName) throws InterruptedException
	{
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		if (moduleName[0].equalsIgnoreCase("all"))
		{
			modulesPageWeb.enableAllModules();
		}
		else
		{
			modulesPageWeb.enableSpecificModules(moduleName);
			adminPageWeb = modulesPageWeb.clickOnSaveButton();
		}
		return new AdminPageWeb(driver);
	}

	public AdminPageWeb enableSiteModulesAndSetLandingPage(String landingPage, String... moduleName) throws InterruptedException
	{
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		if (moduleName[0].equalsIgnoreCase("all"))
		{
			modulesPageWeb.enableAllModules();
		}
		else
		{
			modulesPageWeb.enableSpecificModules(moduleName);
		}
		modulesPageWeb.setSelectDefaultLandingPage(landingPage);

		adminPageWeb = modulesPageWeb.clickOnSaveButton();
		return new AdminPageWeb(driver);
	}

	{
		configurationMap.put("setorg", true);
		configurationMap.put("addsystemadminusers", true);
		configurationMap.put("setuserroles", false);
		configurationMap.put("createsite", true);
		configurationMap.put("setgrouppermission", false);
		configurationMap.put("enablemodules", true);
		configurationMap.put("addsiteuserswithroles", false);
		configurationMap.put("addsiteuserswithoutroles", false);
		configurationMap.put("setsiteuserroles", false);
		configurationMap.put("addusersinadmingroups", false);
		configurationMap.put("creategroup", false);
		configurationMap.put("setmodulepermissionofgroup", false);
	}

	public boolean setConfiguration(String[] configurationList, ConfigurationData configurationData) throws InterruptedException
	{

		for (int i = 0; i < configurationList.length; i++)
		{
			// System.out.println(configurationList[i].toLowerCase());
			switch (configurationList[i].toLowerCase())
			{
				case "setorg":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					setOrganisation(configurationData.getOrgData());
					break;
				case "addsystemadminusers":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					if (configurationData.getUserIdAndPassword() != null)
					{
						createAndResetUsers(configurationData.getUserMap(), configurationData.getUserIdAndPassword(), configurationData.getStatus(), configurationData.isStausLocked());
					}
					else
					{
						createAndResetUsers(configurationData.getUserMap(), configurationData.getNewPassword(), configurationData.getStatus(), configurationData.isStausLocked());

					}
					break;

				case "setuserroles":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					setUserRoles(configurationData.getSystemAdminUserRoles(), configurationData.getStatus(), configurationData.isStausLocked());
					break;

				case "createsite":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					gotoDashboard();
					if (configurationData.getSiteName() == null)
					{
						createSiteAndNavigateToAdmin(configurationData.getSiteData());
					}
					else
					{
						createSiteAndNavigateToAdmin(configurationData.getSiteName());
					}
					break;
				case "setgrouppermission":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					setUserGroupPermission(configurationData.getSiteGroupPermission());
					break;

				case "enablemodules":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					if (configurationData.getDefaultLandingPage() == null)
					{
						enableSiteModules(configurationData.getModuleList());
					}
					else
					{
						enableSiteModulesAndSetLandingPage(configurationData.getDefaultLandingPage(), configurationData.getModuleList());
					}

					break;

				case "addsiteuserswithroles":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					createSiteUsersWithRoles(configurationData.getUserMap(), configurationData.getUserDataAndRoles());
					break;

				case "addsiteuserswithoutroles":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					if (configurationData.getSiteUsers() != null)
					{
						createSiteUsers(configurationData.getSiteUsers());
					}
					else
					{
						createSiteUsers(configurationData.getUserMap());
					}
					break;
				case "setsiteuserroles":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					setSiteUserRoles(configurationData);
					break;
				case "addusersinadmingroups":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					addUsersInAdminGroups(configurationData.getAdminGroupUsers());
					break;

				case "creategroup":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					gotoAdminModule();
					setUserGroupPermission(true);
					try
					{
						if (configurationData.getGroupDetails() != null)
						{
							createUserGroup(configurationData.getGroupDetails());
						}
						else
						{
							createUserGroup(configurationData);
						}

					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
				case "setmodulepermissionofgroup":
					if (!verifyCofigurationPriority(configurationList, i))
					{
						return false;
					}
					setGroupsModulePermission(configurationData.getSiteGroupModulePermission());
					break;

				default:
					break;
			}

		}

		return true;
	}

	private void setSiteUserRoles(ConfigurationData configurationData)
	{
		adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.setUserRoles(configurationData);
	}

	private void setGroupsModulePermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission)
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminUserGroupsWeb = adminPageWeb.clickGroupsInLeftPanel();
		adminUserGroupsWeb.setGroupModulePermission(siteGroupModulePermission);
	}

	private void addUsersInAdminGroups(LinkedHashMap<String, String[]> adminGroupUsers)
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminUserGroupsWeb = adminPageWeb.clickGroupsInLeftPanel();
		adminUserGroupsWeb.editUsersInAdminGroups(adminGroupUsers);
	}

	public AdminUserGroupsWeb createUserGroup(Map<String, Map<String, String>> groupDetails) throws IOException
	{
		adminPageWeb = new AdminPageWeb(driver);
		gotoAdminModule();
		adminUserGroupsWeb = adminPageWeb.clickGroupsInLeftPanel();
		adminUserGroupsWeb.addNewSiteGroupDetails(groupDetails);
		return new AdminUserGroupsWeb(driver);
	}

	@SuppressWarnings("unchecked")
	public boolean setPreConfigurationDataFromJson(String jsonFileName) throws IOException, JSONException, InterruptedException
	{
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));

		Map<String, Map<String, Object>> organizationData = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("OrgData"), Map.class);
		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, List<String>> userMap = new HashMap<>();
		Map<String, Boolean> orgPermission = new LinkedHashMap<>();

		Map<String, Map<String, Object>> resetPasswordData = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData"), Map.class);
		Map<String, String> userIdAndPassword = new LinkedHashMap<>();
		String orgName = null;

		ConfigurationData configurationData = new ConfigurationData();
		String userStatus = resultsFile.get("GlobalData").get("userResetData").get("userStatus").asText().trim();
		Boolean isStatusLocked = resultsFile.get("GlobalData").get("userResetData").get("isStatusLocked").asBoolean();

		for (Map.Entry<String, Map<String, Object>> res : organizationData.entrySet())
		{
			Boolean internalValue = (Boolean) res.getValue().get("Internal");
			orgPermission.clear();
			orgPermission.put("Internal", internalValue);
			orgData.put((String) res.getValue().get("name"), orgPermission);
			userMap.put((String) res.getValue().get("name"), (List<String>) res.getValue().get("users"));

		}

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);

		// for(Map.Entry<String, Map<String, Boolean>> r : orgData.entrySet())
		// {
		// System.out.println(r.getKey());
		// for(Map.Entry<String, Boolean> r1 : r.getValue().entrySet())
		// {
		// System.out.println(r1.getKey() +" "+r1.getValue());
		// }
		// }
		//
		// for(Map.Entry<String, List<String>> users : userMap.entrySet())
		// {
		// String domain = users.getKey();
		// System.out.println(domain+" "+users.getValue());
		// }

		String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();
		/*
		 * if(newPassword.isEmpty())
		 * {
		 */ for (Map.Entry<String, Map<String, Object>> data : resetPasswordData.entrySet())
		{
			System.out.println(data.getKey());
			Map<String, Object> d = data.getValue();
			for (Map.Entry<String, Object> d1 : d.entrySet())
			{
				if (d1.getKey().equalsIgnoreCase("name"))
				{
					orgName = d1.getValue().toString().trim();
					System.out.println(orgName);
					continue;
				}
				else
				{
					Map<String, Object> userCredentialMap = new LinkedHashMap<String, Object>();
					userCredentialMap = (Map<String, Object>) d1.getValue();
					String id = userCredentialMap.get("id").toString().trim();// resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData").get(data.getKey()).get(d1.getKey()).get("id").asText();
					String password = userCredentialMap.get("newPassword").toString().trim();// resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData").get(data.getKey()).get(d1.getKey()).get("newPassword").asText();
					String userEmail = id + "@" + orgName;
					if (!password.isEmpty())
					{
						// System.out.println(userEmail +" "+password);
						userIdAndPassword.put(userEmail, password);
					}
					else
					{
						if (!defaultPassword.isEmpty())
						{
							// System.out.println("Password for user: "+userEmail+" not found, setting default(global) password.");
							// System.out.println(userEmail +" "+defaultPassword);
							userIdAndPassword.put(userEmail, defaultPassword);
						}
						else
						{
							System.err.println("Either [Default Password] or [New password] for " + userEmail + " is required, both cannot be left empty.");
							return false;
						}
					}
				}
			}
		}
		configurationData.setUserIdAndPassword(userIdAndPassword);
		// }
		/*
		 * else {
		 * configurationData.setNewPassword(newPassword);
		 * }
		 */

		if (userStatus.equalsIgnoreCase("active"))
		{
			configurationData.setStatus(UserStatus.Active);
		}
		else if (userStatus.equalsIgnoreCase("archived"))
		{
			configurationData.setStatus(UserStatus.Archived);
		}
		else if (userStatus.equalsIgnoreCase("inactive"))
		{
			configurationData.setStatus(UserStatus.Inactive);
		}

		configurationData.setStausLocked(isStatusLocked);

		List<String> preConfigurationOrder = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("PreConfiguration 1"), List.class);
		String[] configurationOrder = preConfigurationOrder.stream().toArray(String[]::new);

		Map<String, String> siteMap = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites"), Map.class);

		LinkedHashMap<String, String> siteData = new LinkedHashMap<>();

		for (Map.Entry<String, String> siteFields : siteMap.entrySet())
		{
			// System.out.println(siteFields.getKey()+" "+ siteFields.getValue());
			siteData.put(siteFields.getKey(), siteFields.getValue());
		}

		configurationData.setSiteData(siteData);

		Boolean siteGroupPermission = resultsFile.get("GlobalData").get("PreConfiguration").get("SiteGroupPermission").asBoolean();
		// System.out.println(siteGroupPermission);
		configurationData.setSiteGroupPermission(siteGroupPermission);

		List<String> modulesToBeEnabled = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("modules"), List.class);
		String[] modules = modulesToBeEnabled.stream().toArray(String[]::new);

		configurationData.setModuleList(modules);

		List<String> siteUsers = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("siteUsers"), List.class);
		if (!siteUsers.get(0).contains("above all"))
		{
			configurationData.setSiteUsers(siteUsers);
		}

		if (siteGroupPermission)
		{
			Map<String, Map<String, String>> groupDetails = new LinkedHashMap<>();
			Map<String, String> groupData = new LinkedHashMap<>();

			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission = new LinkedHashMap<>();
			Map<String, String> groups = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("UserGroups"), Map.class);
			for (Map.Entry<String, String> group : groups.entrySet())
			{

				groupData = new LinkedHashMap<>();
				// System.out.println(user.getKey());
				Map<String, String> groupInfo = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("UserGroups").get(group.getKey()), Map.class);
				String groupName = null;
				for (Map.Entry<String, String> s : groupInfo.entrySet())
				{
					try
					{
						if (!s.getValue().isEmpty())
						{
							if (s.getKey().equals("name"))
							{
								groupName = s.getValue().trim();
								continue;
							}
							groupData.put(s.getKey(), s.getValue());
							// System.out.println(s.getKey()+" "+s.getValue());
						}
					}
					catch (ClassCastException e)
					{
						continue;
						// System.out.println(e);
					}
				}
				groupDetails.put(groupName, groupData);

				Map<String, Map<String, Boolean>> userModules = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("UserGroups").get(group.getKey()).get("Permissions").get("Modules"), Map.class);
				siteGroupModulePermission.put(groupName, userModules);
			}
			// System.out.println(groups.size());
			// System.out.println(groupData);
			configurationData.setGroupDetails(groupDetails);
			configurationData.setSiteGroupModulePermission(siteGroupModulePermission);
			// System.out.println(groupDetails);

			Map<String, String> adminGroups = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("adminGroups"), Map.class);
			LinkedHashMap<String, String[]> adminGroupUsers = new LinkedHashMap<>();
			for (Map.Entry<String, String> adminGrp : adminGroups.entrySet())
			{

				System.out.println(adminGrp.getKey());
				Map<String, String> groupInfo = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("adminGroups").get(adminGrp.getKey()), Map.class);

				for (Map.Entry<String, String> adminGrpDetails : groupInfo.entrySet())
				{
					String[] members;
					if (adminGrpDetails.getValue().contains(","))
					{
						members = adminGrpDetails.getValue().split(",");
					}
					else
					{
						members = new String[] {adminGrpDetails.getValue()};
					}
					// System.out.println(adminGrp.getKey()+" " + members[0]);
					adminGroupUsers.put(adminGrp.getKey(), members);
				}

			}
			configurationData.setAdminGroupUsers(adminGroupUsers);

		}
		else
		{
			LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> filePermissionMap = new LinkedHashMap<>();
			Map<String, String> users = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Users"), Map.class);
			for (Map.Entry<String, String> user : users.entrySet())
			{
				// for user module permission
				String userId = resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get(user.getKey()).get("id").asText();
				Map<String, Map<String, Boolean>> userModules = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get(user.getKey()).get("Permissions").get("Modules"), Map.class);
				siteUserModulePermission.put(userId, userModules);
				// userModules.clear();

				Map<String, Boolean> fileFolderPermission = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get(user.getKey()).get("Permissions").get("Files"), LinkedHashMap.class);
				Map<String, Map<String, Boolean>> finalPermissionMap = new LinkedHashMap<>();
				if (fileFolderPermission != null && !fileFolderPermission.isEmpty())
				{
					finalPermissionMap.put(siteMap.get("name"), fileFolderPermission);
					filePermissionMap.put(userId, finalPermissionMap);
				}
				// for user admin groups
				LinkedHashMap<String, Boolean> userRoles = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Users").get(user.getKey()).get("Permissions").get("AdminGroups"), LinkedHashMap.class);
				if (userRoles.size() != 0)
				{
					LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
					for (Map.Entry<String, Boolean> userGrp : userRoles.entrySet())
					{
						if (userGrp.getKey().equalsIgnoreCase("site admin") && userGrp.getValue())
						{
							userModules.clear();
						}
						rolesOfSiteUser.put(userGrp.getKey().toLowerCase(), userGrp.getValue());
					}
					siteUserRoles.put(userId, rolesOfSiteUser);
				}

			}
			configurationData.setSiteUserRoles(siteUserRoles);
			configurationData.setSiteUserModulePermission(siteUserModulePermission);
			configurationData.setSiteUserFilePermission(filePermissionMap);

			//
			// System.out.println("------------------ user roles ------------------------");
			// for(Map.Entry<String, LinkedHashMap<String,Boolean>> userMod : siteUserRoles.entrySet())
			// {
			// String moduleName = userMod.getKey().trim();
			// System.out.println(moduleName);
			// for(Map.Entry<String, Boolean> m : userMod.getValue().entrySet())
			// {
			// System.out.println(m.getKey()+" "+m.getValue());
			// }
			//
			// }
			//
			// System.out.println("------------------ Module Permission ------------------------");
			//
			// for(Map.Entry<String, Map<String, Map<String, Boolean>>> userMod1 : siteUserModulePermission.entrySet())
			// {
			// String userId = userMod1.getKey();
			//
			// System.out.println(userId);
			// for(Map.Entry<String, Map<String,Boolean>> userMod : userMod1.getValue().entrySet())
			// {
			// String moduleName = userMod.getKey().trim();
			// System.out.println(moduleName);
			// for(Map.Entry<String, Boolean> m : userMod.getValue().entrySet())
			// {
			// System.out.println(m.getKey()+" "+m.getValue());
			// }
			//
			// }
			// }

		}
		return setConfiguration(configurationOrder, configurationData);

	}
}
