package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import com.highq.pagedata.ConfigurationData;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.ModulePermissionWeb;

public interface AddUserPage extends BannerPage
{
	void clickAddUsers();

	void clickOnExport();

	void clickSetEmailAlerts();

	void clickNextToAddUser();

	void selectAllUsers();

	void selectInvitationNotSent(boolean requiredState);

	void selectUnallocatedUsers(boolean requiredState);

	void selectNotLoggedIn(boolean requiredState);

	void selectSuspendedUsers(boolean requiredState);

	void enterEmailIds(Map<String, List<String>> emailIds);

	public Map<Boolean, BannerPageWeb> clickOnNext();

	public ModulePermissionWeb openUserPermissions(String orgName, String userName, String permission);

	Map<Boolean, BannerPageWeb> initiateUserCreationProcess();

	public boolean verifyAddUserPage();

	public void setUserRoles(ConfigurationData configurationData);

	public void openUserPermissionModel(String userId);

	public AddUserWeb setModulePermissionForUsers(Map<String, Map<String, Boolean>> moduleMap);

	public void clickSaveInSetPermissions();

	public void setFolderPermission(String folderName, String permissionName, boolean state);

	public void setFolderViewPermission(String folderName, boolean state);

	public void setFolderAddFilePermission(String folderName, boolean state);

	public void setFolderAdminPermission(String folderName, boolean state);

	public void clickFilesTabInUserPermissionsModal();

	public void clickFilterButton();

	public void expandFilter();

	public void searchOrganisationInFilter(String orgName);

	public void selectFilterUserOrganisation(String orgName);

	public boolean verifyUserName(String email);

	public void enterEmailIds(List<String> emailIds);

	public boolean verifyEmptyResultMessage(String message);

	void setInheritance(String folderName, boolean state);

	public void clickOnSuspend();

	public void removeUser();

	public void uploadUserAvatars(String userMail, String image);

	public void selectFileOptionWatermarkCheckbox(String optionToCheck, boolean state);

}
