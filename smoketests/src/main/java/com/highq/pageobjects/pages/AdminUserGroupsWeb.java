package com.highq.pageobjects.pages;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pageobjects.base.AdminUserCreateGroupPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.pages.GroupModulePermissionWeb.ModuleNames;

public class AdminUserGroupsWeb extends BannerPageWeb implements AdminUserGroupsPage
{

	By addDropDownList = By.id("siteAdmin_userMangage_addGroup");// By.xpath("//li[@id='Add']//span[contains(text(),'Add')]");
	By addNewSiteGroup = By.linkText(SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSITEGROUP);
	By addNewSystemGroup = By.linkText(SiteAdminLabels.SITEADMIN_USERGROUPS_SYSTEMGROUP);

	By removeGroup = By.xpath("//span[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_REMOVEGROUP + "']");
	By premissionsReport = By.xpath("//span[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_PERMISSIONSREPORT + "']");
	By copyFilePermissions = By.xpath("//span[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_COPYFILEPERMISSIONS + "']");

	By groupUserPermissionsLabel = By.xpath("//h4[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_GROUPUSERPERMISSION + "']");

	By allGroups = By.id("siteAdmin_userManage_allTargetGroupID");
	By firstGroupSelector = By.xpath("(//input[@type='radio'])[1]");
	By modulePermissions_Modal = By.xpath("//*[@name='frmModulePermission']");
	By modulePermissions_Save = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERGROUPS_SAVE + "'])[last()]");
	By modulePermissions_Cancel = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERGROUPS_CANCEL + "'])[last()]");

	By lastOpenedModal = By.xpath("(//*[contains(@class,'modal-content')])[last()]");

	By saveButtonInLastOpenedModel = By.xpath("(//*[contains(@class,'modal-content')])[last()]//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_SAVE + "']");
	By cancelButtonInLastOpenedModel = By.xpath("(//*[contains(@class,'modal-content')])[last()]//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_CANCEL + "']");
	/** New Site group */
	By newSiteGroup_groupNameInput = By.id("site_admin_newSiteGroup_groupName");
	By newSiteGroup_descriptionInput = By.id("site_admin_newSiteGroup_description");
	By newSiteGroup_copyFolderAndFilePermissionDropDown = By.xpath("//*[@data-id='site_admin_newSiteGroup_permission']");
	By newSiteGroup_copyFolderAndFilePermissionCombobox = By.xpath("//*[@class='dropdown-menu open']");
	String newSiteGroup_copyFolderAndFilePermissionList = "//*[@class='dropdown-menu open']//li";
	By newSiteGroup_membersInput = By.id("site_admin_newSiteGroup_members-tokenfield");
	By newSiteGroup_addAnotherGroupCheckbox = By.id("newSiteGroupModal_addAnotherGroup");
	By newSiteGroup_saveButton = By.id("newSiteGroupModal_saveNewSiteGroup");
	By newSiteGroup_cancelButton = By.id("newSiteGroupModal_cancelNewSiteGroup");

	String moreActionBox = "//*[@class='dropdown-menu pull-right' and contains(@style,'height')]";

	By setGroupPermissionOption = By.xpath("//*[contains(@class,'dropdown pull-left open')]//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERGROUPS_SETGROUPPERMISSION + "']");
	By groupPermissionModelOpened = By.xpath("//*[@id='singleGroupSetPermissionModal' and @class='modal fade fullScreenModal in']");
	By groupPermissionModelClosed = By.xpath("//*[@id='singleGroupSetPermissionModal' and @class='modal fade fullScreenModal']");

	By adminGroupPermissionModuleTabLink = By.id("siteAdmin_groupmodulePermissionTabID");
	/** remove modal */
	By confirmRemoveGroupText = By.id("confirmDeleteGroupModal_BODY");
	By removeGroup_removeButton = By.id("confirmDeleteGroupModal_removeSiteGroupBtnID");
	By removeButtonTop = By.id("siteAdmin_GroupPage_removeGroup");

	/** Search group */
	By searchInput = By.id("siteAdmin_userManage_searchGroup");
	By clearSearchedItem = By.id("siteAdmin_dropdown_searchuser");
	By groupTable = By.id("siteAdmin_group_groupList");

	/** Add system group */
	By addSystemGroupNextButton = By.id("siteAdmin_addSystemGroup_modal_nextBtnID");
	By addSystemGroupCancelButton = By.id("siteAdmin_addSystemGroup_modal_cancelLinkBtnID");
	By addSystemGroupAddButton = By.id("siteAdmin_addSystemGroup_modal_addBtnID");
	By addSystemGroupSelectAllGroupCheckBox = By.id("siteAdmin_sysGrpList_selectAll_chkboxID");
	By addSystemGroup_SelectAllHomeViewCheckbox = By.id("selectAllGroup_home_View");
	By addSystemGroup_SelectAllActivityViewCheckbox = By.id("selectAllGroup_activitySystem_View");
	By addSystemGroup_SelectAllFilesViewCheckbox = By.id("selectAllGroup_document_View");
	By addSystemGroup_SelectAllWikiViewCheckbox = By.id("selectAllGroup_wiki_View");
	By addSystemGroup_SelectAllWikiEditCheckbox = By.id("selectAllGroup_wiki_Edit");
	By addSystemGroup_SelectAllBlogViewCheckbox = By.id("selectAllGroup_blog_View");
	By addSystemGroup_SelectAllBlogEditCheckbox = By.id("selectAllGroup_blog_Edit");
	By addSystemGroup_SelectAllTaskViewCheckbox = By.id("selectAllGroup_task_View");
	By addSystemGroup_SelectAllTaskEditCheckbox = By.id("selectAllGroup_task_Edit");
	By addSystemGroup_SelectAllEventsViewCheckbox = By.id("selectAllGroup_event_View");
	By addSystemGroup_SelectAllEventsEditCheckbox = By.id("selectAllGroup_event_Edit");

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

	By saveButtonInLastOpenedModal = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERGROUPS_SAVE + "' and not(contains(@class,'hide'))]");
	By cancelButtonInLastOpenedModal = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERGROUPS_CANCEL + "' and not(contains(@class,'hide'))]");

	By modulePermissionsCancel = By.xpath("(//*[@id='singleGroupSetPermissionModal']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_USERGROUPS_CANCEL + "'])[last()]");
	By modulePermissionsSave = By.id("singleGroupSetPermissionModal_setSingleGroupPermission");

	/** Permission Report */
	By permissionReportButton = By.id("siteAdmin_userMangage_export");
	By clickHereToDownload = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERGROUPS_PERMISSIONREPORT_DOWNLOADLINK + "']");
	By permissionReport_downloadButton = By.id("exportFilePermissionReportModelId_downloadBtn");
	By permissionReport_cancelButton = By.id("exportFilePermissionReportModelId_cancelDownloadBtn");
	By permissionReport_generatingReportLoader = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERGROUPS_PERMISSIONREPORT_REPORTGENERATINGWAIT + "']");
	By permissionReport_title = By.id("exportFilePermissionReportModelId_TITLE");

	/** Copy Permission to */
	By mustSelectWarning = By.xpath("(//*[@id='errorElementContainer'])[last()]");
	By copyPermission_copyButton = By.id("copyGroupPermissionsTo_copy");
	By copyPermission_fromGroup = By.id("siteAdmin_copyPermissionTo_fromGroup");
	By copyPermission_selectAllGroupCheckbox = By.id("siteAdmin_copyPermission_selectAll_checkBox");
	By copyPermissionButton = By.id("confirmCopyPermission_copyPermission");
	By copyPermissionToConfirmationMsg = By.xpath(".//*[@id='frmConfirmationCopyPermission']/div");

	/** Filter */
	By filterIcon = By.id("siteAdmin_userManage_filterIconId");
	By filter_dropDownMenuExpanded = By.xpath(".//*[@id='filterGroupTypes']//*[contains(@class,'dropdown-menu')]");
	By filter_typeExpandArrow = By.xpath(".//*[@id='siteAdmin_userManage_filterGroupTypeId']//*[contains(@class,'icon-chevron-right')]");
	By filter_type_AdminGroupCheckbox = By.id("siteAdmin_userManage_adminGroup");
	By filter_type_SiteGroupCheckbox = By.id("siteAdmin_userManage_siteGroup");
	By filter_type_SystemGroupCheckbox = By.id("siteAdmin_userManage_systemGroup");
	By filter_linkClearFilter = By.id("linkClearFilter");

	By siteAdminMoreAction = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_USERADMIN_SITEADMIN + "']//following::*[@data-original-title='More actions'][1]");
	By memberAdminMoreAction = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_USERADMIN_MEMBERADMIN + "']//following::*[@data-original-title='More actions'][1]");
	By contentAdminMoreAction = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_USERADMIN_CONTENTADMIN + "']//following::*[@data-original-title='More actions'][1]");
	By reportingAdminMoreAction = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_USERADMIN_REPORTINGADMIN + "']//following::*[@data-original-title='More actions'][1]");
	By qaAdminMoreAction = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_PERMISSIONREPORT_QANDAADMIN + "']//following::*[@data-original-title='More actions'][1]");

	By editMembersText = By.xpath("//*[@class='dropdown pull-left open']//*[text()='" + SiteAdminLabels.SITEADMIN_USERGROUPS_EDITMEMBERS + "']");

	private By viewCheckBox;
	private By editCheckBox;

	By middlePanel = By.id("siteAdmin_module_mainMiddlePanelDivID");
	By updateSiteGroupModal = By.id("updateSiteGroupModal");
	By saveButtonOfOpenedModal = By.xpath("//*[contains(@class,'modal') and contains(@style,'block;')]//*[normalize-space(text())='Save']");

	public AdminUserGroupsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Click on Add button
	 */
	@Override
	public void clickOnAddDropdownList()
	{
		WebElement dropDownList = findClickableElement(addDropDownList);
		dropDownList.click();
	}

	/**
	 * Click on New site group after clicking on Add button
	 */
	@Override
	public AdminUserCreateGroupPage clickOnNewSiteGroup()
	{
		WebElement newSiteGroupLink = findClickableElement(addNewSiteGroup);
		newSiteGroupLink.click();
		return new AdminUserCreateGroupsWeb(driver);
	}

	/**
	 * Click on System group after clicking on Add button
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickOnSystemGroup()
	{
		WebElement addNewsysyemGroupLink = findClickableElement(addNewSystemGroup);
		addNewsysyemGroupLink.click();
	}

	/**
	 * Remove all groups
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean removeAllGroup()
	{
		setSelection(allGroups, true);
		WebElement removeButtonXpath = findVisibleElement(removeButtonTop);
		if (removeButtonXpath.isEnabled())
		{
			removeButtonXpath.click();
			findPresentElement(lastOpenedModal);
			findVisibleElement(removeGroup_removeButton).click();
			findVisibleElement(globalMessageBarCloseLink, Speed.slow).click();
			return true;
		}
		return false;
	}

	/**
	 * Remove a group
	 * 
	 * @param groupNameToBeRemoved
	 *        name of the group to remove
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void removeGroup(String groupNameToBeRemoved)
	{
		gotoMoreActionItemOfGroup(groupNameToBeRemoved, SiteAdminLabels.SITEADMIN_USERGROUPS_REMOVE);
		String removeMsg = findVisibleElement(confirmRemoveGroupText).getText().trim();
		if (removeMsg.contains(groupNameToBeRemoved.trim()))
		{
			findVisibleElement(removeGroup_removeButton).click();
			findVisibleElement(addDropDownList);
			findVisibleElement(globalMessageBarCloseLink, Speed.slow).click();
		}
		else
		{
			System.err.println(groupNameToBeRemoved + " is not displayed in remove group warning.");
		}
	}

	/**
	 * Add new site group details
	 * 
	 * @param confg
	 *        Configuration data object to get required values
	 * @author dheeraj.rajput
	 */
	@Override
	public void addNewSiteGroupDetails(ConfigurationData confg)
	{
		boolean nameFound = true;
		String grpName = confg.getGroupName().trim();
		String desc = confg.getDescription().trim();
		String copyPermissionFrom = confg.getCopyFolderAndFilePermissionFrom().trim();
		String[] members = confg.getMembers();
		clickOnAddDropdownList();
		selectAddOptions(SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSITEGROUP);
		if (grpName == null)
		{
			System.err.println("You must enter Group name.");
			nameFound = false;
		}
		else
		{
			setGroupName(grpName);
		}

		if (nameFound)
		{
			if (desc != null)
			{
				setDescription(desc);
			}

			if (copyPermissionFrom != null)
			{
				selectOptionFromDropDown(newSiteGroup_copyFolderAndFilePermissionDropDown, newSiteGroup_copyFolderAndFilePermissionCombobox, newSiteGroup_copyFolderAndFilePermissionList, copyPermissionFrom);
			}
			if (members.length > 0)
			{
				for (int i = 0; i < members.length; i++)
				{
					WebElement addGroupMemberInputBox = findVisibleElement(newSiteGroup_membersInput, Speed.slow);
					addGroupMemberInputBox.click();
					setMember(members[i]);
				}
			}
		}
	}

	/**
	 * Click save in New site group modal
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void saveNewSiteGroupChanges()
	{
		findVisibleElement(newSiteGroup_saveButton).click();

		if (isDisplayed(modelError, Speed.slow))
		{
			findVisibleElement(newSiteGroup_cancelButton).click();
		}

		findPresentElement(lastOpenedModal);
	}

	/**
	 * Click on add another checkbox
	 * 
	 * @param state
	 *        Boolean: true - to check add another group
	 *        false - to uncheck add another group
	 * @author dheeraj.rajput
	 */
	@Override
	public void addAnotherGroupInNewSiteGroup(boolean state)
	{
		setSelection(newSiteGroup_addAnotherGroupCheckbox, state);
	}

	/**
	 * Click cancel in New site group modal
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCancelInNewSiteGroup()
	{
		findVisibleElement(newSiteGroup_cancelButton).click();
	}

	/**
	 * Open more action menu of a group
	 * 
	 * @param groupName
	 *        to open more actions menu for
	 * @author dheeraj.rajput
	 */
	public void gotoMoreActionItemOfGroup(String groupName, String itemToOpen)
	{
		By moreOptionXpath = By.xpath("//*[normalize-space(text())='" + groupName.trim() + "']/ancestor::*[2]//*[@data-original-title='More actions']");
		findVisibleElement(moreOptionXpath).click();
		findVisibleElement(By.xpath(moreActionBox), Speed.slow);
		WebElement optionXpath = findVisibleElement(By.xpath(moreActionBox + "//*[normalize-space(text())='" + itemToOpen.trim() + "']"));
		optionXpath.click();
		findPresentElement(lastOpenedModal);
	}

	/**
	 * Select add option for group
	 * 
	 * @param optionName
	 *        add option to open
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectAddOptions(String optionName)
	{
		String lowerCase = optionName.trim().toLowerCase();
		if (SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSITEGROUP.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(addNewSiteGroup).click();
			findPresentElement(lastOpenedModal);
		}
		else if (SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSYSTEMGROUP.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(addNewSystemGroup).click();
			findPresentElement(lastOpenedModal);
		}
	}

	/**
	 * Search email alert options
	 * 
	 * @param groupName
	 *        group name to search
	 * @author dheeraj.rajput
	 * @return false
	 *         if no results found
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean searchGroup(String groupName)
	{
		WebElement searchBox = findVisibleElement(searchInput);
		searchBox.clear();
		searchBox.sendKeys(groupName.trim());
		findVisibleElement(groupTable);
		if (noResultsFound())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Click on clear searched item icon
	 * 
	 * @author dheeraj.rajput
	 */
	@Override
	public void clearSearchResults()
	{
		if (isDisplayed(clearSearchedItem))
		{
			findVisibleElement(clearSearchedItem).click();
		}
	}

	/**
	 * Verify group is present
	 * 
	 * @param groupName
	 *        to verify.
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyGroupIsPresent(String groupName)
	{
		findVisibleElement(middlePanel, Speed.slow);
		return isDisplayed(By.xpath("//*[@id='siteAdmin_group_groupList']//*[normalize-space(text())='" + groupName.trim() + "']"));
	}

	/**
	 * Click next in Add System group modal
	 */
	@Override
	public void clickNextInAddSystemGroup()
	{
		findVisibleElement(addSystemGroupNextButton).click();
	}

	/**
	 * Click add in add system group modal
	 */
	@Override
	public void clickAddInAddSystemGroup()
	{
		findVisibleElement(addSystemGroupAddButton).click();
		findPresentElement(modalContentClosed, Speed.slow);
	}

	/**
	 * Click cancel in add system group modal
	 */
	@Override
	public void clickCancelInAddSystemGroup()
	{
		findVisibleElement(addSystemGroupCancelButton).click();
	}

	/**
	 * Add system group
	 * 
	 * @param groupName
	 *        name of the group to add
	 * @param moduleMap
	 *        Map(Key: String - module name
	 *        Value: Map<String,Boolean> -> Key : String - permission name(view/edit), Value: Boolean(true/false)
	 * @author dheeraj.rajput
	 */
	@Override
	public void addSystemGroup(String groupName, Map<String, Map<String, Boolean>> moduleMap)
	{
		boolean valid = true;
		By viewCheckbox = null;
		By editCheckbox = null;
		clickOnAddDropdownList();
		selectAddOptions(SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSYSTEMGROUP);
		By groupCheckbox = By.xpath("//*[normalize-space(text())='" + groupName.trim() + "']/ancestor::*[2]/preceding-sibling::*/*[@type='checkbox']");
		String userID = findVisibleElement(groupCheckbox).getAttribute("value").trim();
		setSelection(groupCheckbox, true);
		clickNextInAddSystemGroup();
		for (Map.Entry<String, Map<String, Boolean>> entry : moduleMap.entrySet())
		{
			String moduleName = entry.getKey().trim();
			if (moduleName != null)
			{

				Map<String, Boolean> permissionMap = entry.getValue();
				if (permissionMap.get("View") == false && permissionMap.get("Edit") == true)
				{
					System.err.println("Invalid permissions : View -> false and Edit -> true");
					clickCancelInAddSystemGroup();
					valid = false;
				}
				else
				{
					for (Map.Entry<String, Boolean> entry2 : permissionMap.entrySet())
					{
						String permissionName = entry2.getKey().trim();
						boolean permissionState = entry2.getValue();
						if (EnumUtils.isValidEnum(ModuleNames.class, moduleName) && permissionName.equalsIgnoreCase("edit"))
						{
							System.err.println(permissionName + " permission can not be set for: " + moduleName);
						}
						else
						{
							String lowerCase = moduleName.toLowerCase();
							if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_HOME.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("homeModuleView" + userID);
								setSelection(viewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_ACTIVITY.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("activitySystemView" + userID);
								setSelection(viewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_FILES.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("documentView" + userID);
								setSelection(viewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_WIKI.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("wikiView" + userID);
								editCheckbox = By.id("wikiEdit" + userID);
								clickPermissionCheckBox(permissionName, permissionState, viewCheckbox, editCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_BLOG.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("blogView" + userID);
								editCheckbox = By.id("blogEdit" + userID);
								clickPermissionCheckBox(permissionName, permissionState, viewCheckbox, editCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_TASKS.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("taskView" + userID);
								editCheckbox = By.id("taskEdit" + userID);
								clickPermissionCheckBox(permissionName, permissionState, viewCheckbox, editCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_EVENTS.toLowerCase().equals(lowerCase))
							{
								viewCheckbox = By.id("eventView" + userID);
								editCheckbox = By.id("eventEdit" + userID);
								clickPermissionCheckBox(permissionName, permissionState, viewCheckbox, editCheckbox);
							}
						}
					}
				}
			}
		}
		if (valid)
		{
			clickAddInAddSystemGroup();
		}
	}

	/**
	 * Click on group permission checkbox(View/Edit permission)
	 * 
	 * @param permissionName
	 *        name of permission
	 * @param permissionState
	 *        Boolean: true -> check
	 *        false -> uncheck
	 * @param viewCheckBox
	 *        xpath of view checkbox
	 * @param editCheckBox
	 *        xpath of edit checkbox
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	public void clickPermissionCheckBox(String permissionName, boolean permissionState, By viewCheckBox, By editCheckBox)
	{
		if (permissionName.equalsIgnoreCase("edit") && permissionState == true)
		{
			setSelection(editCheckBox, true);
		}
		else
		{
			setSelection(viewCheckBox, permissionState);
			setSelection(editCheckBox, false);
		}
	}

	/**
	 * Edit group details
	 * 
	 * @param groupName
	 *        name of the group to open edit modal
	 * @param newGroupName
	 *        group name to set
	 * @param description
	 *        description to set
	 * @param members
	 *        members to set
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void editGroupDetails(String groupName, String newGroupName, String description, String... members)
	{
		gotoMoreActionItemOfGroup(groupName, SiteAdminLabels.SITEADMIN_USERGROUPS_EDITDETAILS);
		if (newGroupName != null)
		{
			setGroupName(newGroupName);
		}
		if (description != null)
		{
			setDescription(description);
		}

		if (members.length > 0)
		{
			for (int i = 0; i < members.length; i++)
			{
				setMember(members[i]);
			}
		}
	}

	/**
	 * Set group name
	 * 
	 * @param groupName
	 *        name of the group to set
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void setGroupName(String groupName)
	{
		WebElement groupNameXpath = findVisibleElement(newSiteGroup_groupNameInput);
		groupNameXpath.clear();
		groupNameXpath.sendKeys(groupName.trim());
	}

	/**
	 * Set description
	 * 
	 * @param description
	 *        description to set
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void setDescription(String description)
	{
		WebElement descXpath = findVisibleElement(newSiteGroup_descriptionInput);
		descXpath.clear();
		descXpath.sendKeys(description.trim());
	}

	/**
	 * Set memeber
	 * 
	 * @param memberName
	 *        member name to set
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void setMember(String memberName)
	{
		WebElement addGroupMemberInputBox = findVisibleElement(newSiteGroup_membersInput, Speed.slow);
		addGroupMemberInputBox.clear();
		addGroupMemberInputBox.sendKeys(getUserData(memberName));
		selectOptionFromAutoSuggest(memberName);
	}

	/**
	 * Click save in last opened modal
	 * 
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void clickSaveInLastOpenedModal()
	{
		findVisibleElement(saveButtonInLastOpenedModal).click();
		findPresentElement(lastOpenedModal);
	}

	/**
	 * set module permission for group
	 * 
	 * @param siteGroupModulePermission
	 *        Map(Key: String - group name
	 *        Value: Map<String, Map<String, Boolean>> -> Key : Module name, Value : Map<String, Boolean>> -> Key : String - permission name(view/edit), Value: Boolean(true/false)
	 * @author badal.gandhi
	 *         Note : This method is used in precondition
	 */
	@Override
	public void setGroupModulePermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission)
	{
		if (siteGroupModulePermission.size() != 0)
		{
			for (Entry<String, Map<String, Map<String, Boolean>>> map : siteGroupModulePermission.entrySet())
			{
				String groupName = map.getKey().trim();
				openGroupPermissionModel(groupName);

				if (siteGroupModulePermission.get(groupName) != null)
				{
					setModulePermissionForGroups(siteGroupModulePermission.get(groupName));
				}
				clickSaveInSetGroupPermissions();

			}
		}

	}

	/**
	 * open set group permission model for group
	 *
	 * @param groupName
	 *        Group name for which user wants to open module permission model
	 * @author badal.gandhi
	 */
	@Override
	public void openGroupPermissionModel(String groupName)
	{
		By moreOptionXpath = By.xpath("//*[normalize-space(text())='" + groupName.trim() + "']/ancestor::*[2]//*[@data-original-title='More actions']");
		findVisibleElement(moreOptionXpath).click();

		findVisibleElement(setGroupPermissionOption, Speed.slow).click();
		findPresentElement(groupPermissionModelOpened, Speed.slow);
	}

	/**
	 * Set module permission for a user
	 *
	 * @param moduleMap
	 *        map with Key: (String)Module Name, Value: Map<String,Boolean>[Key: (String)Permission Name, Value: (boolean)Permission State]
	 * @author dheeraj.rajput
	 */
	@Override
	public AddUserWeb setModulePermissionForGroups(Map<String, Map<String, Boolean>> moduleMap)
	{
		findVisibleElement(adminGroupPermissionModuleTabLink, Speed.slow).click();
		for (Map.Entry<String, Map<String, Boolean>> entry : moduleMap.entrySet())
		{
			String moduleName = entry.getKey();
			if (moduleName != null)
			{
				By moduleXpath = By.xpath("(//*[@id='singleGroupSetPermissionModal']//*[normalize-space(.)='" + moduleName.trim() + "'])[last()]");
				if (!isDisplayed(moduleXpath))
				{
					System.err.println(moduleName + " is not displayed");
				}
				else
				{
					System.out.println("Module Name: " + moduleName);

					Map<String, Boolean> permissionMap = entry.getValue();

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

							if (!viewChkBox.isEnabled() && !editChkBox.isEnabled())
							{
								System.err.println("Please uncheck [user permissioning] option from Advanced menu in site admin");
								if (isDisplayed(modulePermissionsCancel))
								{
									findVisibleElement(modulePermissionsCancel).click();
								}
							}
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
			}
		}
		return new AddUserWeb(driver);
	}

	/**
	 * set view and edit check box page object of respective module
	 *
	 * @param moduleName
	 *        module name for which user wants to set pageobject for permission
	 * @author badal.gandhi
	 */
	@Override
	public void setViewAndEditCheckBoxForModules(String moduleName)
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
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_TASKS.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = tasksViewCheckBox;
			editCheckBox = tasksEditCheckBox;
		}
		else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_EVENTS.toLowerCase().equals(lowerCase))
		{
			viewCheckBox = eventsViewCheckBox;
			editCheckBox = eventsEditCheckBox;
		}
		else
		{
			System.out.println(moduleName + "  Module is not available for permissioning");
		}

	}

	/**
	 * save group permission for user
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void clickSaveInSetGroupPermissions()
	{
		findVisibleElement(modulePermissionsSave).click();
		findPresentElement(groupPermissionModelClosed, Speed.slow);
	}

	/**
	 * Add all system groups with module permission
	 * 
	 * @param modulePermissionMap
	 *        Map: Key -> String value containing moduleName
	 *        Value -> Map<String,Boolean> -> Key : String -> permission name(View/Edit)
	 *        Vakue : Boolean -> permission state(true/false)
	 * @author dheeraj.rajput
	 *         Created : 16 January 2018
	 *         Updated :
	 */
	@Override
	public void addAllSystemGroupsWithModulePermission(Map<String, Map<String, Boolean>> modulePermissionMap)
	{
		boolean valid = true;
		clickOnAddDropdownList();
		selectAddOptions(SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSYSTEMGROUP);
		setSelection(addSystemGroupSelectAllGroupCheckBox, true);
		clickNextInAddSystemGroup();
		for (Map.Entry<String, Map<String, Boolean>> entry : modulePermissionMap.entrySet())
		{
			String moduleName = entry.getKey().trim();
			if (moduleName != null)
			{

				Map<String, Boolean> permissionMap = entry.getValue();
				if (permissionMap.get("View") == false && permissionMap.get("Edit") == true)
				{
					System.err.println("Invalid permissions : View -> false and Edit -> true");
					clickCancelInAddSystemGroup();
					valid = false;
				}
				else
				{
					for (Map.Entry<String, Boolean> entry2 : permissionMap.entrySet())
					{
						String permissionName = entry2.getKey().trim();
						boolean permissionState = entry2.getValue();
						if (EnumUtils.isValidEnum(ModuleNames.class, moduleName) && permissionName.equalsIgnoreCase("edit"))
						{
							System.err.println(permissionName + " permission can not be set for: " + moduleName);
						}
						else
						{
							String lowerCase = moduleName.toLowerCase();
							if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_HOME.toLowerCase().equals(lowerCase))
							{
								setSelection(addSystemGroup_SelectAllHomeViewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_ACTIVITY.toLowerCase().equals(lowerCase))
							{
								setSelection(addSystemGroup_SelectAllActivityViewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_FILES.toLowerCase().equals(lowerCase))
							{
								setSelection(addSystemGroup_SelectAllFilesViewCheckbox, permissionState);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_WIKI.toLowerCase().equals(lowerCase))
							{
								clickPermissionCheckBox(permissionName, permissionState, addSystemGroup_SelectAllWikiViewCheckbox, addSystemGroup_SelectAllWikiEditCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_BLOG.toLowerCase().equals(lowerCase))
							{
								clickPermissionCheckBox(permissionName, permissionState, addSystemGroup_SelectAllBlogViewCheckbox, addSystemGroup_SelectAllBlogEditCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_TASKS.toLowerCase().equals(lowerCase))
							{
								clickPermissionCheckBox(permissionName, permissionState, addSystemGroup_SelectAllTaskViewCheckbox, addSystemGroup_SelectAllTaskEditCheckbox);
							}
							else if (SiteAdminLabels.SITEADMIN_USERS_USER_PERMISSION_MODULES_EVENTS.toLowerCase().equals(lowerCase))
							{
								clickPermissionCheckBox(permissionName, permissionState, addSystemGroup_SelectAllEventsViewCheckbox, addSystemGroup_SelectAllEventsEditCheckbox);
							}
						}
					}
				}
			}
		}
		if (valid)
		{
			clickAddInAddSystemGroup();
		}
	}

	/**
	 * Open permission report
	 * 
	 * @author dheeraj.rajput
	 *         Created : 16 January 2018
	 *         Updated :
	 */
	@Override
	public void clickPermissionReport()
	{
		findVisibleElement(permissionReportButton).click();
		findPresentElement(lastOpenedModal);
		findVisibleElement(permissionReport_title);
	}

	/**
	 * Click on click here to download link
	 * 
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void clickHereToDownload()
	{
		findVisibleElement(clickHereToDownload).click();
	}

	/**
	 * Click Download button in permission report
	 * 
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void clickDownloadInPermissionReport()
	{
		findVisibleElement(permissionReport_downloadButton).click();
	}

	/**
	 * Click Cancel button in permission report
	 * 
	 * @author dheeraj.rajput
	 *         Created: 16 January 2018
	 *         Updated:
	 */
	@Override
	public void clickCancelInPermissionReport()
	{
		findVisibleElement(permissionReport_cancelButton).click();
	}

	/**
	 * Download permission report
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void downloadPermissionReport()
	{
		clickPermissionReport();
		while (isDisplayed(permissionReport_generatingReportLoader))
		{
			;
		}
		clickDownloadInPermissionReport();
	}

	/**
	 * Click Cancel in last opened modal
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void clickCancelInLastOpenedModal()
	{
		findVisibleElement(cancelButtonInLastOpenedModal).click();
		findPresentElement(lastOpenedModal);
	}

	/**
	 * Click Copy in Copy permission to modal
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void clickCopyInCopyPermissionModal()
	{
		findVisibleElement(copyPermission_copyButton);
	}

	/**
	 * Return true if select one or more groups warning is displayed
	 * 
	 * @return true
	 *         if found
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public boolean isSelectGroupWarningDisplayed()
	{
		return isDisplayed(mustSelectWarning, Speed.slow);
	}

	/**
	 * Copy group permission to other groups
	 * 
	 * @param sourceGroup
	 *        source group whose permissions are going to be copied
	 * @param destinationGroup
	 *        Variable Parameter: destination group where permission will be copied,
	 *        "all" will copy source group permission to all available groups.
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void copyGroupPermissionTo(String sourceGroup, String... destinationGroup)
	{
		gotoMoreActionItemOfGroup(sourceGroup, SiteAdminLabels.SITEADMIN_USERGROUPS_MOREACTIONS_COPYGROUPPERMISSIONSTO);
		findVisibleElement(lastOpenedModal);
		WebElement fromElem = findVisibleElement(copyPermission_fromGroup);
		if (fromElem.getText().trim().equals(sourceGroup))
		{
			for (int i = 0; i < destinationGroup.length; i++)
			{
				if (destinationGroup[0].trim().equalsIgnoreCase("all"))
				{
					setSelection(copyPermission_selectAllGroupCheckbox, true);
				}
				else
				{
					By groupCheckBox = By.xpath("//*[@id='siteAdmin_copyPermissionTo_groupList']//*[normalize-space(text())='" + destinationGroup[i].trim() + "']/../preceding-sibling::*/*[@type='checkbox']");
					if (isDisplayed(groupCheckBox))
					{
						setSelection(groupCheckBox, true);
					}
					else
					{
						System.err.println(destinationGroup[i].trim() + " checkbox is not displayed");
					}
				}
			}
			clickCopyInCopyPermissionModal();
			if (isSelectGroupWarningDisplayed())
			{
				System.err.println(findVisibleElement(mustSelectWarning).getText());
			}
			else
			{
				findVisibleElement(copyPermissionToConfirmationMsg);
				clickCopyPermissions();
			}
		}
		else
		{
			System.err.println(sourceGroup + " is not the [From group] in opened modal");
		}

	}

	/**
	 * Click on Copy permissions button
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void clickCopyPermissions()
	{
		findVisibleElement(copyPermissionButton).click();
		findVisibleElement(globalMessageBarCloseLink, Speed.slow).click();
	}

	/**
	 * Expand group filter
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void expandGroupFilter()
	{
		if (!isDisplayed(filter_linkClearFilter))
		{
			findVisibleElement(filterIcon).click();
			findVisibleElement(filter_dropDownMenuExpanded);
		}
	}

	/**
	 * Click on Clear filters link
	 * 
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void clearGroupFilters()
	{
		findVisibleElement(filter_linkClearFilter).click();
	}

	/**
	 * Filter group by type
	 * 
	 * @param type
	 *        type to filter by
	 * @param state
	 *        Boolean: true - Check
	 *        false - Uncheck
	 * @author dheeraj.rajput
	 *         Created: 17 January 2018
	 *         Updated:
	 */
	@Override
	public void filterGroupByType(String type, boolean state)
	{
		expandGroupFilter();
		if (isDisplayed(filter_typeExpandArrow))
		{
			findVisibleElement(filter_typeExpandArrow).click();
		}
		String trim = type.toLowerCase().trim();
		if (SiteAdminLabels.SITEADMIN_USERGROUPS_ADMINGROUP.toLowerCase().trim().equals(trim))
		{
			setSelection(filter_type_AdminGroupCheckbox, state);
		}
		else if (SiteAdminLabels.SITEADMIN_USERGROUPS_SITEGROUP.toLowerCase().trim().equals(trim))
		{
			setSelection(filter_type_SiteGroupCheckbox, state);
		}
		else if (SiteAdminLabels.SITEADMIN_USERGROUPS_SYSTEMGROUP.toLowerCase().trim().equals(trim))
		{
			setSelection(filter_type_SystemGroupCheckbox, state);
		}
		else
		{
			System.err.println(type + " not found");
		}
	}

	@Override
	public void editUsersInAdminGroups(LinkedHashMap<String, String[]> users)
	{
		for (Map.Entry<String, String[]> user : users.entrySet())
		{
			String role = user.getKey();
			String members[] = user.getValue();
			String trim = role.toLowerCase().trim();
			if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN.toLowerCase().trim().equals(trim))
			{
				editMemberInSiteAdminGroup(members);
			}
			else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_MEMBERADMIN.toLowerCase().trim().equals(trim))
			{
				editMemberInMemberAdminGroup(members);
			}
			else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_CONTENTADMIN.toLowerCase().trim().equals(trim))
			{
				editMemberInContentAdminGroup(members);
			}
			else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_REPORTINGADMIN.toLowerCase().trim().equals(trim))
			{
				editMemberInReportingAdminGroup(members);
			}
			else if (SiteAdminLabels.SITEADMIN_USERS_USERROLE_QANDAADMIN.toLowerCase().trim().equals(trim))
			{
				editMemberInQAAdminGroup(members);
			}
			else
			{
				System.out.println("Invalid Role --> " + role);
			}
		}
	}

	@Override
	public void editMemberInSiteAdminGroup(String... members)
	{
		findVisibleElement(siteAdminMoreAction).click();
		editMembers(members);
	}

	@Override
	public void editMemberInMemberAdminGroup(String... members)
	{
		findVisibleElement(memberAdminMoreAction).click();
		editMembers(members);
	}

	@Override
	public void editMemberInContentAdminGroup(String... members)
	{
		findVisibleElement(contentAdminMoreAction).click();
		editMembers(members);
	}

	@Override
	public void editMemberInReportingAdminGroup(String... members)
	{
		findVisibleElement(reportingAdminMoreAction).click();
		editMembers(members);
	}

	@Override
	public void editMemberInQAAdminGroup(String... members)
	{
		findVisibleElement(qaAdminMoreAction).click();
		editMembers(members);
	}

	@Override
	public void editMembers(String... members)
	{
		findVisibleElement(editMembersText, Speed.slow).click();
		findPresentElement(lastOpenedModal, Speed.slow);
		if (members.length > 0)
		{
			for (int i = 0; i < members.length; i++)
			{
				if (!verifyUserInGroupDetails(members[i]))
				{
					WebElement addGroupMemberInputBox = findVisibleElement(newSiteGroup_membersInput, Speed.slow);
					addGroupMemberInputBox.click();
					setMember(members[i]);
				}
			}
		}

		findVisibleElement(saveButtonInLastOpenedModel).click();

	}

	/**
	 * Add new site group details
	 * 
	 * @param confg
	 *        Configuration data object to get required values
	 * @author dheeraj.rajput
	 */
	@Override
	public void addNewSiteGroupDetails(Map<String, Map<String, String>> groupDetails)
	{
		boolean nameFound = true;
		for (Map.Entry<String, Map<String, String>> groupData : groupDetails.entrySet())
		{
			String grpName = groupData.getKey();
			String desc = groupData.getValue().get("description");
			String copyPermissionFrom = groupData.getValue().get("copyFolderAndFilePermissionFrom");

			String[] members;
			if (groupData.getValue().get("members").contains(","))
			{
				members = groupData.getValue().get("members").split(",");
			}
			else
			{
				members = new String[] {groupData.getValue().get("members")};
			}
			if (grpName == null)
			{
				System.err.println("You must enter Group name.");
				nameFound = false;
			}
			else if (verifyGroupIsPresent(grpName))
			{
				clickOnGroupName(grpName);
			}
			else
			{
				clickOnAddDropdownList();
				selectAddOptions(SiteAdminLabels.SITEADMIN_USERGROUPS_NEWSITEGROUP);
				setGroupName(grpName);
			}

			if (nameFound)
			{
				if (desc != null)
				{
					setDescription(desc);
				}

				if (copyPermissionFrom != null)
				{
					selectOptionFromDropDown(newSiteGroup_copyFolderAndFilePermissionDropDown, newSiteGroup_copyFolderAndFilePermissionCombobox, newSiteGroup_copyFolderAndFilePermissionList, copyPermissionFrom);
				}
				if (members.length > 0)
				{
					for (int i = 0; i < members.length; i++)
					{
						if (!verifyUserInGroupDetails(members[i]))
						{
							WebElement addGroupMemberInputBox = findVisibleElement(newSiteGroup_membersInput, Speed.slow);
							addGroupMemberInputBox.click();
							setMember(members[i]);
						}
					}
				}
			}

			clickSaveInOpenModal();
			// saveNewSiteGroupChanges();
		}
	}

	/**
	 * Verify user in group details modal
	 * 
	 * @param userEmail
	 *        email of the user to verify
	 * @return true
	 *         if user email found
	 * @author dheeraj.rajput
	 * @Created 04 May 2018
	 */
	public boolean verifyUserInGroupDetails(String userEmail)
	{
		findVisibleElement(newSiteGroup_groupNameInput);
		By user = By.xpath(".//*[@id='siteAdmin_addGroup_userlistTbodyID']//*[normalize-space(text())='" + userEmail.trim() + "']");
		return isDisplayed(user);
	}

	/**
	 * Click on group name
	 * 
	 * @param groupName
	 *        name of the group
	 * @author dheeraj.rajput
	 * @Created 04 May 2018
	 * @Updated
	 */
	public void clickOnGroupName(String groupName)
	{
		findVisibleElement(middlePanel, Speed.slow);
		By groupLink = By.xpath("//*[@id='siteAdmin_group_groupList']//*[normalize-space(text())='" + groupName.trim() + "']");
		findVisibleElement(groupLink).click();
		findVisibleElement(updateSiteGroupModal, Speed.slow);
	}

	/**
	 * Click save in visible modal
	 * 
	 * @author dheeraj.rajput
	 * @Created 04 May 2018
	 * @Updated
	 */
	public void clickSaveInOpenModal()
	{
		findVisibleElement(saveButtonOfOpenedModal, Speed.slow).click();
	}
}
