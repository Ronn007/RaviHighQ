package com.highq.pageobjects.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.pages.GroupModulePermissionWeb.ModuleNames;

public class AddUserWeb extends BannerPageWeb implements AddUserPage
{

	public String userRoles[] = {SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN.toLowerCase(), SiteAdminLabels.SITEADMIN_USERS_USERROLE_MEMBERADMIN.toLowerCase(), SiteAdminLabels.SITEADMIN_USERS_USERROLE_CONTENTADMIN.toLowerCase(), SiteAdminLabels.SITEADMIN_USERS_USERROLE_REPORTINGADMIN.toLowerCase(), SiteAdminLabels.SITEADMIN_USERS_USERROLE_QANDAADMIN.toLowerCase()};

	By addUsers = By.id("siteAdmin_users_add");
	By export = By.xpath(".//*[@id='docsNavLeft']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERS_EXPORT + "']");
	By setEmailAlerts = By.xpath(".//*[@id='docsNavLeft']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERS_SETEMAILALERTS + "']");
	By moreActionDisabled = By.xpath(".//*[@id='docsNavLeft']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_MORE + "' and contains(@class,'disabled')]");
	By moreActionEnabled = By.xpath(".//*[@id='docsNavLeft']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_MORE + "' and not(contains(@class,'disabled'))]");

	By filterOrg = By.id("organisation");
	By filterGroup = By.id("group");

	By invitationNotSent = By.id("unsentInvitation");
	By unallocatedUsers = By.id("unallocatedUsers");
	By notLoggedIn = By.id("notLoggedIn");
	By suspendedUsers = By.id("suspendedUsers");
	By errorMsg = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_USERALREADYEXIST + "']");
	By selectAllUsers = By.id("selectAllUsers");
	By listOfUsers = By.id("listMemberDiv");

	By emailsOfUsers = By.id("site_admin_user_emailCSVID-tokenfield");
	By quickSearch = By.id("recipientsList");
	By nextButton = By.xpath("(//*[@class='fullContainer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_ADDSITEADMIN_USERS_NEXT + "'])[last()]");
	By cancelButton = By.xpath("(//*[@class='fullContainer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_ADDSITEADMIN_USERS_CANCEL + "'])[last()]");
	By allReadyUsersAddedError = By.xpath(".//*[@id='addSiteParticipants']//span[@class='actionMessage']");
	By modulePermissions_Modal = By.xpath("//*[contains(@name,'Permission')]");

	By rolesStep = By.xpath("//*[@class='text-info' and normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_USER_ROLES + "']");

	By addUserPage = By.xpath("//*[@class='contentDocument']");

	By setUserPermission = By.xpath("//*[contains(@class,'dropdown pull-right open')]//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSIONS + "']");
	By userPermissionModelOpened = By.xpath("//*[@id='userSetPermissionModal' and @class='modal fade fullScreenModal in']");
	By userPermissionModelClosed = By.xpath("//*[@id='userSetPermissionModal' and @class='modal fade fullScreenModal']");

	By adminUserPermissionModuleTabLink = By.id("siteAdmin_modulePermissionTabID");
	By adminUserPermissionFileTabLink = By.id("siteAdmin_filePermissionTabID");
	By adminUserPermissionGroupsTabLink = By.id("siteAdmin_adminModulePermissionTabID");

	By siteAdminCheckBox = By.id("siteAdmin");
	By memberAdminCheckBox = By.id("siteAdmin_AdminGrp_MODULE_MEMBERMANAGER");
	By contentAdminCheckBox = By.id("siteAdmin_AdminGrp_MODULE_CONTENTMANAGER");
	By reportingAdminCheckBox = By.id("siteAdmin_AdminGrp_MODULE_REPORTING");
	By qAndAadminCheckBox = By.id("Q&A admin");
	By modulePermissionsCancel = By.xpath("(//*[@id='userSetPermissionModal']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERS_ADDSITEADMIN_USERS_CANCEL + "'])[last()]");
	By modulePermissionsSave = By.id("userSetPermissionModal_setPermission");

	By homeViewCheckBox = By.id("homeModuleView");

	By activityViewCheckBox = By.id("activityModuleView");

	By filesViewCheckBox = By.id("documentView");

	By wikiViewCheckBox = By.id("wikiView");
	By wikiEditCheckBox = By.id("wikiEdit");

	By blogViewCheckBox = By.id("blogView");
	By blogEditCheckBox = By.id("blogEdit");

	By eventsViewCheckBox = By.id("eventView");
	By eventsEditCheckBox = By.id("eventEdit");

	By tasksViewCheckBox = By.id("taskView");
	By tasksEditCheckBox = By.id("taskEdit");

	String filePermission = "//*[@id='siteAdmin_userfilePermission_Tree']//*[normalize-space(text())='";
	By errorMsgBox = By.xpath("(//*[contains(@class,'errormsgbox')])[last()]");
	By userProcess = By.xpath("//*[@class='row bs-wizard']");

	// Filter
	By filterButton = By.xpath(".//*[@id='userMainSearchFilterDiv']/*[@type='button']");
	By openedDropDown = By.xpath(".//*[@id='userMainSearchFilterDiv']/*[contains(@class,'dropCollapse')]");
	By filter_Organisation_ExpandArrow = By.xpath(".//*[@id='collapseOneArrow' and contains(@class,'icon-chevron-right')]");
	By filter_Organisation_SearchInput = By.id("siteadmin_UserFilterOrganizationSerachButtonID");
	By filter_Organisation_ClearSearchIcon = By.id("siteadmin_UserFilterOrganizationSerachClearButtonID");
	By filter_Organisation_NoResultsFound = By.xpath(".//*[@id='siteadmin_userfilterOrganizationListDivID']//*[contains(@class,'greyFont')]");

	By allViewCheckBox = By.xpath("//*[contains(@id,'View') and @type='checkbox']");
	By allEditCheckBox = By.xpath("//*[contains(@id,'Edit') and @type='checkbox']");

	private By viewCheckBox;
	private By editCheckBox;

	By enterEmailProgressDot = By.id("siteAdmin_user_enterEmails");
	By nextButtonBottom = By.id("siteAdmin_user_step2_NextBottom");
	By setUserPermission_Files_expandAll = By.id("siteAdmin_userfilePermission_Tree_expandAll");

	By setUserPermission_Files_inheritanceModal = By.id("siteAdmin_fileUserPermissionInheritanceConfirmationModalID");
	By setUserPermission_Files_Inheritance_OKButton = By.id("siteAdmin_fileUserPermissionInheritanceConfirmationModalID_continueInheritButtonID");
	By setUserPermission_Files_collapseAll = By.id("siteAdmin_userfilePermission_Tree_collapseAll");

	By emptyResult = By.id("emptyResult");
	By usersMiddlePannel = By.id("siteAdmin_module_mainMiddlePanelDivID");
	By userAvatar = By.id("userAvatarContainerID");
	By changeAvatar = By.xpath(".//*[@id='showAvtarPreviewID']/a[@class='changeLink']");
	By browseAvatar = By.xpath(".//input[@id='fileUploadID']");
	By uploadProfile_saveButton = By.id("USER_PROFILE_UPLOAD_USER_AVATAR_MODAL_saveButton");
	By saveProfilePicture = By.id("siteAdmin_userDetails_modal_userDetailsSaveBtnID");

	public AddUserWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void clickAddUsers()
	{
		WebElement ele = findClickableElement(addUsers, Speed.slow);
		if (ele.isDisplayed())
		{
			ele.click();
		}
	}

	@Override
	public void clickOnExport()
	{
		WebElement ele = findClickableElement(export);
		if (ele.isDisplayed())
		{
			ele.click();
		}
	}

	@Override
	public void clickSetEmailAlerts()
	{
		WebElement ele = findClickableElement(setEmailAlerts);
		if (ele.isDisplayed())
		{
			ele.click();
		}
	}

	@Override
	public void selectAllUsers()
	{
		WebElement ele = findClickableElement(selectAllUsers);
		if (ele.isDisplayed() && (!ele.isSelected()))
		{
			ele.click();
		}
	}

	@Override
	public void selectInvitationNotSent(boolean requiredState)
	{
		setSelection(invitationNotSent, requiredState);
	}

	@Override
	public void selectUnallocatedUsers(boolean requiredState)
	{
		setSelection(unallocatedUsers, requiredState);
	}

	@Override
	public void selectNotLoggedIn(boolean requiredState)
	{
		setSelection(notLoggedIn, requiredState);
	}

	@Override
	public void selectSuspendedUsers(boolean requiredState)
	{
		setSelection(suspendedUsers, requiredState);
	}

	@Override
	public void clickNextToAddUser()
	{
		WebElement ele = findClickableElement(nextButton);
		if (ele.isDisplayed())
		{
			ele.click();
		}
	}

	@Override
	public void enterEmailIds(Map<String, List<String>> emailIds)
	{
		StringBuffer ids = new StringBuffer();
		WebElement ele = findVisibleElement(emailsOfUsers);
		if (emailIds.size() != 0)
		{
			for (Map.Entry<String, List<String>> map : emailIds.entrySet())
			{
				domain = map.getKey();
				List<String> list = map.getValue();
				for (String email : list)
				{
					ids = ids.append(email.concat("@" + domain) + ",");
				}
			}
		}
		if (ele.isDisplayed())
		{
			ele.sendKeys(ids);
		}
	}

	@Override
	public void enterEmailIds(List<String> emailIds)
	{
		StringBuffer ids = new StringBuffer();
		WebElement ele = findVisibleElement(emailsOfUsers);
		if (emailIds.size() != 0)
		{
			for (String email : emailIds)
			{
				ids = ids.append(email + ",");
			}
		}
		if (ele.isDisplayed())
		{
			ele.sendKeys(ids);
		}
	}

	@Override
	public Map<Boolean, BannerPageWeb> clickOnNext()
	{
		Map<Boolean, BannerPageWeb> map = new HashMap<>();
		WebElement ele = findClickableElement(nextButton);
		if (ele.isDisplayed())
		{
			ele.click();
			try
			{
				if (isDisplayed(errorMsg))
				{
					findClickableElement(cancelButton).click();
					map.put(false, new AddUserWeb(driver));
				}
				else
				{
					map.put(true, new InsertSiteUserWeb(driver));
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getCause());
			}
		}
		return map;
	}

	@Override
	public Map<Boolean, BannerPageWeb> initiateUserCreationProcess()
	{
		Map<Boolean, BannerPageWeb> map = new HashMap<>();
		WebElement ele = findVisibleElement(nextButton, Speed.slow);
		if (ele.isDisplayed())
		{
			ele.click();
			findVisibleElement(userProcess, Speed.slow);
			findVisibleElement(cancelButton, Speed.slow);
			findVisibleElement(nextButton, Speed.slow);
			try
			{
				if (isDisplayed(errorMsg, Speed.slow) && isDisplayed(errorMsgBox, Speed.slow))
				{
					findVisibleElement(cancelButton, Speed.slow).click();
					map.put(false, new AddUserWeb(driver));
				}
				else
				{
					// finish user creation process
					map.put(true, new InsertSiteUserWeb(driver));
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getCause());
			}
		}
		return map;
	}

	@Override
	public ModulePermissionWeb openUserPermissions(String orgName, String userName, String permission)
	{
		if (orgName.contains("."))
		{
			orgName = orgName.split("\\.")[0].trim();
		}
		if (userName != null)
		{

			findVisibleElement(By.xpath("//*[contains(@class,'userHeader')]//*[normalize-space(text())='" + orgName + "']/following::*[contains(text(),'" + getUserData(userName) + "')][1]/preceding::*[normalize-space(text())='" + permission + "'][1]")).click();
			findPresentElement(modulePermissions_Modal, Speed.slow, 20);
		}
		return new ModulePermissionWeb(driver);
	}

	@Override
	public boolean verifyAddUserPage()
	{
		findPresentElement(addUserPage, Speed.slow, 10);
		return (isDisplayed(addUserPage));
	}

	@Override
	public void setUserRoles(ConfigurationData configurationData)
	{
		if (configurationData.getUserMap().size() != 0)
		{
			for (Map.Entry<String, List<String>> map : configurationData.getUserMap().entrySet())
			{
				domain = map.getKey();
				List<String> list = map.getValue();
				for (String email : list)
				{
					String userId = email.concat("@" + domain);
					openUserPermissionModel(userId);
					// Set module permission
					if (configurationData.getSiteUserModulePermission() != null)
					{
						if (configurationData.getSiteUserModulePermission().get(userId) != null)
						{
							setUserModulePermissions(configurationData.getSiteUserModulePermission().get(userId));
						}
					}

					// Set file permission
					if (configurationData.getSiteUserFilePermission() != null && !configurationData.getSiteUserFilePermission().isEmpty())
					{
						for (Map.Entry<String, Map<String, Map<String, Boolean>>> filePermissionMap : configurationData.getSiteUserFilePermission().entrySet())
						{
							if (filePermissionMap.getValue() != null && filePermissionMap.getKey().equalsIgnoreCase(userId))
							{
								for (Map.Entry<String, Map<String, Boolean>> permissions : filePermissionMap.getValue().entrySet())
								{
									String folderName = permissions.getKey();
									for (Map.Entry<String, Boolean> permissionMap : permissions.getValue().entrySet())
									{
										String permissionName = permissionMap.getKey();
										boolean permissionState = permissionMap.getValue();
										setFolderPermission(folderName, permissionName, permissionState);
									}

								}
							}
						}
					}

					if (configurationData.getSiteUserRoles() != null)
					{
						if (configurationData.getSiteUserRoles().get(userId) != null)
						{
							setRolesOfSiteUser(configurationData.getSiteUserRoles().get(userId));
						}
					}

					clickSaveInSetPermissions();

				}
			}
		}

	}

	private void setUserModulePermissions(Map<String, Map<String, Boolean>> map)
	{
		findVisibleElement(adminUserPermissionModuleTabLink, Speed.slow).click();
		setModulePermissionForUsers(map);

	}

	/**
	 * Set module permission for a user
	 *
	 * @param moduleMap
	 *        map with Key: (String)Module Name, Value: Map<String,Boolean>[Key: (String)Permission Name, Value: (boolean)Permission State]
	 * @author dheeraj.rajput
	 */
	@Override
	public AddUserWeb setModulePermissionForUsers(Map<String, Map<String, Boolean>> moduleMap)
	{
		for (Map.Entry<String, Map<String, Boolean>> entry : moduleMap.entrySet())
		{
			String moduleName = entry.getKey();
			Map<String, Boolean> permissionMap = entry.getValue();
			if (moduleName != null)
			{
				By moduleXpath = By.xpath("(//*[@id='userSetPermissionModal']//*[normalize-space(.)='" + moduleName.trim() + "'])[last()]");
				if (!isDisplayed(moduleXpath) && !moduleName.toLowerCase().equals("all"))
				{
					System.err.println(moduleName + " is not displayed");
				}
				else if (moduleName.equalsIgnoreCase("all"))
				{
					if ((permissionMap.get("Edit") != null && permissionMap.get("Edit") == true) && (permissionMap.get("View") != null && permissionMap.get("View") == false))
					{
						System.err.println("Permission values are incompatible");
						break;
					}
					if (permissionMap.get("Edit") != null)
					{
						List<WebElement> editCheckBoxes = driver.findElements(allEditCheckBox);
						for (int i = 1; i <= editCheckBoxes.size(); i++)
						{
							By editCheckBox = By.xpath("(//*[contains(@id,'Edit') and @type='checkbox'])[" + i + "]");
							setSelection(editCheckBox, permissionMap.get("Edit"));
						}
					}
					if ((permissionMap.get("View") != null)) // && permissionMap.get("Edit")!= null) && !permissionMap.get("Edit"))
					{
						// List<WebElement> editCheckBoxes = driver.findElements(allEditCheckBox);
						// for(int i=1;i<=editCheckBoxes.size();i++)
						// {
						// By editCheckBox = By.xpath("(//*[contains(@id,'Edit') and @type='checkbox'])["+i+"]");
						// setSelection(editCheckBox, false);
						// }

						List<WebElement> viewCheckBoxes = driver.findElements(allEditCheckBox);
						for (int i = 1; i <= viewCheckBoxes.size(); i++)
						{
							By viewCheckBox = By.xpath("(//*[contains(@id,'View') and @type='checkbox'])[" + i + "]");
							setSelection(viewCheckBox, permissionMap.get("View"));
						}
					}
				}
				else
				{
					setModulePermission(moduleName, permissionMap);
				}
			}
		}

		return new AddUserWeb(driver);

	}

	public void setModulePermission(String moduleName, Map<String, Boolean> permissionMap)
	{
		for (Map.Entry<String, Boolean> entry2 : permissionMap.entrySet())
		{
			String permissionName = entry2.getKey();
			boolean permissionState = entry2.getValue();
			if (EnumUtils.isValidEnum(ModuleNames.class, moduleName) && permissionName.equalsIgnoreCase("edit"))
			{
				System.err.println(permissionName + " permission can not be set for: " + moduleName);
			}
			else
			{
				setViewAndEditCheckBoxForModules(moduleName);
				WebElement viewChkBox = null;
				WebElement editChkBox = null;
				if (viewCheckBox != null)
				{
					viewChkBox = findVisibleElement(viewCheckBox);
				}
				if (editCheckBox != null)
				{
					editChkBox = findVisibleElement(editCheckBox);
				}

				// if((isDisplayed(viewCheckBox)&& !viewChkBox.isEnabled()) || (!isDisplayed(editCheckBox)&& editChkBox == null))
				// {
				if ((isDisplayed(viewCheckBox) && !viewChkBox.isEnabled()) && editChkBox != null)
				{
					if ((isDisplayed(editCheckBox) && !viewChkBox.isEnabled()))
					{
						System.err.println("Please uncheck [Use groups for permissioning] option from Advanced menu in site admin");
					}
				}
				// }

				else
				{

					switch (permissionName.toLowerCase())
					{
						case "view":
							if (!permissionState)
							{
								System.err.println("You are removing all the permissions for: " + moduleName);
							}
							setSelection(viewCheckBox, permissionState);
							break;

						case "edit":
							setSelection(editCheckBox, permissionState);
							break;
					}
				}
			}
		}

	}

	private void setViewAndEditCheckBoxForModules(String moduleName)
	{

		String lowerCase = moduleName.toLowerCase();
		if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_HOME.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = homeViewCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_ACTIVITY.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = activityViewCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_FILES.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = filesViewCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_WIKI.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = wikiViewCheckBox;
			editCheckBox = wikiEditCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_BLOG.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = blogViewCheckBox;
			editCheckBox = blogEditCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_EVENTS.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = eventsViewCheckBox;
			editCheckBox = eventsEditCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_TASKS.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = tasksViewCheckBox;
			editCheckBox = tasksEditCheckBox;
		}
		else
		{
			System.out.println(moduleName + "  Module is not available for permissioning");
		}

	}

	private void setRolesOfSiteUser(LinkedHashMap<String, Boolean> roles)
	{

		findVisibleElement(adminUserPermissionGroupsTabLink, Speed.slow).click();
		boolean result = true;
		Boolean siteAdminStatus = null;
		for (Map.Entry<String, Boolean> es : roles.entrySet())
		{
			if (es.getKey().equalsIgnoreCase(SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN))
			{
				siteAdminStatus = roles.get(es.getKey());
			}
		}
		// Boolean siteAdminStatus = roles.get(SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN);
		if (siteAdminStatus == null || siteAdminStatus == false)
		{
			setSelection(siteAdminCheckBox, false);
		}
		else
		{
			setRoles(SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN.toLowerCase(), siteAdminStatus);
			result = false;
		}
		if (result)
		{
			for (int i = 1; i < userRoles.length; i++)
			{

				Boolean currentAdminStatus = roles.get(userRoles[i].trim());
				if (currentAdminStatus == null)
				{
					setRoles(userRoles[i], false);
				}
				else
				{
					setRoles(userRoles[i], roles.get(userRoles[i]));
				}
			}
		}
	}

	public void setRoles(String role, Boolean desiredState)
	{

		String trim = role.toLowerCase().trim();
		if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN.toLowerCase().equals(trim))
		{
			if (isDisplayed(siteAdminCheckBox, Speed.slow))
			{
				setSelection(siteAdminCheckBox, desiredState);
			}
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_MEMBERADMIN.toLowerCase().equals(trim))
		{
			if (isDisplayed(memberAdminCheckBox, Speed.slow))
			{
				setSelection(memberAdminCheckBox, desiredState);
			}
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_CONTENTADMIN.toLowerCase().equals(trim))
		{
			if (isDisplayed(contentAdminCheckBox, Speed.slow))
			{
				setSelection(contentAdminCheckBox, desiredState);
			}

		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_REPORTINGADMIN.toLowerCase().equals(trim))
		{
			if (isDisplayed(reportingAdminCheckBox, Speed.slow))
			{
				setSelection(reportingAdminCheckBox, desiredState);
			}
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_QANDAADMIN.toLowerCase().equals(trim))
		{
			if (isDisplayed(qAndAadminCheckBox, Speed.slow))
			{
				setSelection(qAndAadminCheckBox, desiredState);
			}

		}
		else
		{
			System.out.println("Invalid Role --> " + role);
		}

	}

	@Override
	public void openUserPermissionModel(String userId)
	{
		By userMoreAction = By.xpath("(//*[normalize-space(text())='" + userId.trim().toLowerCase() + "']//following::*[@data-original-title='More actions'])[1]");
		findVisibleElement(userMoreAction).click();

		findVisibleElement(setUserPermission, Speed.slow).click();

		findPresentElement(userPermissionModelOpened, Speed.slow);

	}

	@Override
	public void clickSaveInSetPermissions()
	{
		findVisibleElement(modulePermissionsSave).click();
		findPresentElement(userPermissionModelClosed, Speed.slow);
	}

	/**
	 * Set folder permission
	 *
	 * @param folderName
	 *        name of the folder
	 * @param permissionName
	 *        permission to set
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFolderPermission(String folderName, String permissionName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		String lowerCase = permissionName.trim().toLowerCase();
		if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_VIEW.toLowerCase().equals(lowerCase))
		{
			setFolderViewPermission(folderName, state);
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADDFILE.toLowerCase().equals(lowerCase))
		{
			setFolderAddFilePermission(folderName, state);
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADMIN.toLowerCase().equals(lowerCase))
		{
			setFolderAdminPermission(folderName, state);
		}
		else
		{
			System.err.println(permissionName + " not found.");
		}
	}

	/**
	 * Set folder view permission
	 *
	 * @param folderName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFolderViewPermission(String folderName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		By viewCheckBox = By.xpath(filePermission + folderName.trim() + "']/following::*[contains(@id,'folderViewAccessID')][1]");
		setSelection(viewCheckBox, state);
	}

	/**
	 * Set folder add file permission
	 *
	 * @param folderName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFolderAddFilePermission(String folderName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		setFolderViewPermission(folderName, true);
		setFolderAdminPermission(folderName, false);
		By addFileCheckBox = By.xpath(filePermission + folderName.trim() + "']/following::*[contains(@id,'folderUpdateAllID')][1]");
		setSelection(addFileCheckBox, state);
	}

	/**
	 * Set folder admin permission
	 *
	 * @param folderName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFolderAdminPermission(String folderName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		setFolderViewPermission(folderName, true);
		By adminCheckBox = By.xpath(filePermission + folderName.trim() + "']/following::*[contains(@id,'folderAddDocumentID')][1]");
		setSelection(adminCheckBox, state);
	}

	/**
	 * Click on Files tab in User permission modal
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickFilesTabInUserPermissionsModal()
	{
		findVisibleElement(adminUserPermissionFileTabLink, Speed.slow).click();
	}

	/**
	 * Click on filter button
	 *
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	@Override
	public void clickFilterButton()
	{
		WebElement filterButtonElement = findVisibleElement(filterButton, Speed.slow);
		filterButtonElement.click();
	}

	/**
	 * Expand filter menu
	 *
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	@Override
	public void expandFilter()
	{
		if (!isDisplayed(openedDropDown))
		{
			clickFilterButton();
			findVisibleElement(openedDropDown, Speed.slow);
		}
	}

	/**
	 * Search organisation in filter
	 *
	 * @param orgName
	 *        name of the organisation to search
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	@Override
	public void searchOrganisationInFilter(String orgName)
	{
		expandFilter();
		if (isDisplayed(filter_Organisation_ExpandArrow, Speed.slow))
		{
			findVisibleElement(filter_Organisation_ExpandArrow).click();
		}
		WebElement searchInput = findVisibleElement(filter_Organisation_SearchInput);
		searchInput.clear();
		searchInput.sendKeys(orgName.trim());
	}

	/**
	 * Select organisation from filter
	 *
	 * @param orgName
	 *        name of the organisation
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	@Override
	public void selectFilterUserOrganisation(String orgName)
	{
		searchOrganisationInFilter(orgName);
		By orgXpath = By.xpath(".//*[@id='siteadmin_userfilterOrganizationListDivID']//*[normalize-space(text())='" + orgName.trim() + "']/preceding-sibling::*[@type='checkbox']");
		setSelection(orgXpath, true);
	}

	/**
	 * Verify user name
	 *
	 * @param email
	 *        email address of the user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	@Override
	public boolean verifyUserName(String email)
	{
		By userNameXpath = By.xpath("//*[normalize-space(text())='" + email.trim() + "']/preceding-sibling::*[@id='siteAdmin_users_userName']/*[1]");
		if (isDisplayed(userNameXpath))
		{
			String fetchedUserName = findVisibleElement(userNameXpath).getText().trim();
			return fetchedUserName.equals(getUserData(email));
		}
		else
		{
			System.out.println(email + " not found.");
			return false;
		}
	}

	/**
	 * Click on expand all in file permissions
	 *
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	public void clickExpandAllInFilePermission()
	{
		WebElement expandAllLink = findVisibleElement(setUserPermission_Files_expandAll);
		expandAllLink.click();
	}

	/**
	 * Set inheritance
	 *
	 * @param folderName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	@Override
	public void setInheritance(String folderName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		By checkBox = By.xpath(filePermission + folderName.trim() + "']/following::*[contains(@id,'inherit')][1]");
		setSelection(checkBox, state);
		if (isDisplayed(setUserPermission_Files_inheritanceModal, Speed.slow))
		{
			findVisibleElement(setUserPermission_Files_inheritanceModal);
			WebElement okButton = findVisibleElement(setUserPermission_Files_Inheritance_OKButton);
			okButton.click();
		}
	}

	/**
	 * Set files view permission for folder
	 *
	 * @param folderName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	public void setFilesViewPermissionForFolder(String folderName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		By checkBox = By.xpath(filePermission + folderName.trim() + "']/following::*[contains(@id,'folderFileViewAll')][1]");
		if (isDisplayed(checkBox) && findVisibleElement(checkBox).isEnabled())
		{
			setSelection(checkBox, state);
		}
	}

	/**
	 * Set files view permission for folder
	 *
	 * @param fileName
	 *        name of the folder
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	public void setFilesViewPermissionForFile(String fileName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		By checkBox = By.xpath(filePermission + fileName.trim() + "']/following::*[contains(@id,'documentViewFile')][1]");
		if (isDisplayed(checkBox) && findVisibleElement(checkBox).isEnabled())
		{
			setSelection(checkBox, state);
		}
	}

	/**
	 * Set folder add file permission for file
	 *
	 * @param fileName
	 *        name of the file
	 * @param state
	 *        Boolean: true -> set permission
	 *        false -> remove permission
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	public void setFolderAddFilePermissionForFile(String fileName, boolean state)
	{
		clickFilesTabInUserPermissionsModal();
		clickExpandAllInFilePermission();
		By addFileCheckBox = By.xpath(filePermission + fileName.trim() + "']/following::*[contains(@id,'documentUpdateAddFile')][1]");
		if (isDisplayed(addFileCheckBox) && findVisibleElement(addFileCheckBox).isEnabled())
		{
			setSelection(addFileCheckBox, state);
		}
	}

	/**
	 * Click on collapse all in file permissions
	 *
	 * @author dheeraj.rajput
	 * @Created 20 April 2018
	 */
	public void clickCollapseAllInFilePermission()
	{
		WebElement collapseAllLink = findVisibleElement(setUserPermission_Files_collapseAll);
		collapseAllLink.click();
	}

	/**
	 * @author ankit.motaval
	 * @param message
	 *        verify Empty Result Message
	 * @created on 18/04/2018
	 */
	@Override
	public boolean verifyEmptyResultMessage(String message)
	{
		String contentText = findVisibleElement(emptyResult, Speed.slow).getText();
		return contentText.equals(message.trim());
	}

	/**
	 * Check/Uncheck any Admin Files watermark checkbox
	 * 
	 * @param optionToCheck
	 *        visible text of the checkbox element(exact text is needed)
	 * @param state
	 *        {@value true - to check,
	 *        false - to uncheck}
	 * @author khushbu.dhandhukiya
	 */

	@Override
	public void selectFileOptionWatermarkCheckbox(String optionToCheck, boolean state)
	{
		By fileOption = By.xpath("(//*[normalize-space(.)='" + optionToCheck.trim() + "'])/following::*[contains(@id,'WatermarkID')]");
		setSelection(fileOption, state);
	}

	@Override
	public void clickOnSuspend()
	{
		findClickableElement(By.id("siteAdmin_users_moreAction")).click();
		findClickableElement(By.id("suspendUsersLiID")).click();
	}

	public void removeUser()
	{
		findClickableElement(By.id("siteAdmin_users_moreAction")).click();
		findClickableElement(By.id("RemoveUser")).click();
		findClickableElement(By.id("removeSiteParticipantsModal_removeUser")).click();
	}

	/**
	 * @author jhanvi.dave
	 * @Created 10 May 2018
	 */
	@Override
	public void uploadUserAvatars(String userMail, String image)
	{
		findVisibleElement(usersMiddlePannel, Speed.slow);
		WebElement uname;
		uname = findVisibleElement(By.xpath(".//*[@id='siteAdmin_userPage_userListPageMainDivID']//*[@class='userListName']//*[normalize-space()='" + userMail + "']/preceding-sibling::*[@id='siteAdmin_users_userName']"), Speed.slow);
		uname.click();
		findVisibleElement(userAvatar, Speed.slow);
		WebElement element = findVisibleElement(changeAvatar, Speed.slow);
		element.click();
		uploadFile(browseAvatar, image.trim());
		WebElement webElement = findVisibleElement(uploadProfile_saveButton, Speed.slow);
		webElement.click();
		WebElement save = findVisibleElement(saveProfilePicture, Speed.slow);
		save.click();

	}

}
