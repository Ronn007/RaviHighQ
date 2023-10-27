package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import java.util.Map;
import com.highq.pagedata.ConfigurationData;
import com.highq.pageobjects.pages.AddUserWeb;

public interface AdminUserGroupsPage extends BannerPage
{
	public void clickOnAddDropdownList();

	public AdminUserCreateGroupPage clickOnNewSiteGroup();

	public void clickOnSystemGroup();

	public boolean removeAllGroup();

	public void removeGroup(String groupNameToBeRemoved);

	public void addNewSiteGroupDetails(ConfigurationData confg);

	public void saveNewSiteGroupChanges();

	public void addAnotherGroupInNewSiteGroup(boolean state);

	public void clickCancelInNewSiteGroup();

	public void selectAddOptions(String optionName);

	public boolean searchGroup(String groupName);

	public void clearSearchResults();

	public boolean verifyGroupIsPresent(String groupName);

	public void clickNextInAddSystemGroup();

	public void clickAddInAddSystemGroup();

	public void clickCancelInAddSystemGroup();

	public void editGroupDetails(String groupName, String newGroupName, String description, String... members);

	public void setGroupName(String groupName);

	public void setDescription(String description);

	public void setMember(String memberName);

	public void clickSaveInLastOpenedModal();

	public void setGroupModulePermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission);

	public void openGroupPermissionModel(String groupName);

	public void addSystemGroup(String groupName, Map<String, Map<String, Boolean>> moduleMap);

	public void clickSaveInSetGroupPermissions();

	public AddUserWeb setModulePermissionForGroups(Map<String, Map<String, Boolean>> moduleMap);

	public void setViewAndEditCheckBoxForModules(String moduleName);

	public void addAllSystemGroupsWithModulePermission(Map<String, Map<String, Boolean>> modulePermissionMap);

	public void clickPermissionReport();

	public void clickHereToDownload();

	public void clickDownloadInPermissionReport();

	public void clickCancelInPermissionReport();

	public void downloadPermissionReport();

	public void clickCancelInLastOpenedModal();

	public void clickCopyInCopyPermissionModal();

	public boolean isSelectGroupWarningDisplayed();

	public void copyGroupPermissionTo(String sourceGroup, String... destinationGroup);

	public void clickCopyPermissions();

	public void expandGroupFilter();

	public void clearGroupFilters();

	public void filterGroupByType(String type, boolean state);

	public void editMemberInSiteAdminGroup(String... members);

	public void editMemberInMemberAdminGroup(String... members);

	public void editMemberInContentAdminGroup(String... members);

	public void editMemberInReportingAdminGroup(String... members);

	public void editMemberInQAAdminGroup(String... members);

	public void editMembers(String... members);

	public void editUsersInAdminGroups(LinkedHashMap<String, String[]> users);

	public void addNewSiteGroupDetails(Map<String, Map<String, String>> groupDetails);

	public boolean verifyUserInGroupDetails(String userEmail);

	public void clickOnGroupName(String groupName);

	public void clickSaveInOpenModal();

}
