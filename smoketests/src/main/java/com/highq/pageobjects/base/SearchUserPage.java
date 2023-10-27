package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.SearchUserPageData;
import com.highq.pageobjects.pages.AspAdminUserPermissionWeb;
import com.highq.pageobjects.pages.DashboardWeb;
import com.highq.pageobjects.pages.SearchUserPageWeb;
import com.highq.pageobjects.pages.SystemAdminAddUsersWeb;
import com.highq.pageobjects.pages.SystemAdminSelfRegisteredUsersWeb;
import com.highq.pageobjects.pages.ViewUserProfileWeb;

public interface SearchUserPage extends BannerPage
{
	public void enterUserName(String userName);

	public void enterUserEmail(String userEmail);

	public SearchUserPageWeb setPassword(Map<String, List<String>> users, String password, SearchUserPageData userStausData) throws InterruptedException;

	public void clickOnSearch();

	public SystemAdminAddUsersWeb gotoAddUser();

	public void resetPassword(Map<String, List<String>> users, String password) throws InterruptedException;

	public DashboardWeb gotoDashboard();

	public Map<String, String> selectAndResetUsers(Map<String, List<String>> users, String password, UserStatus status, boolean locked) throws InterruptedException;

	public void expandSearchInUserAdministration();

	public void searchDomain(String domain, UserStatus status, boolean locked) throws InterruptedException;

	public void selectUserStatus(String status, boolean locked) throws InterruptedException;

	public void resetBulkUser(String domain, List<String> userList);

	public void searchAndResetUser(String domain, List<String> userList);

	public String getResetPasswordLink(String userEmail);

	public void searchUser(String userEmail, UserStatus status, boolean locked) throws InterruptedException;

	public String getUserRestPasswordLink(String userId);

	public void selectUserOptions(String operation);

	public void resetUserId(String userId, String password);

	public AspAdminUserPermissionWeb clickRoles(String operation);

	public void scrollToTop() throws InterruptedException;

	public SearchUserPageWeb setPassword(Map<String, List<String>> users, String password, UserStatus status, boolean statusLocked) throws InterruptedException;

	public void setUserRoles(Map<String, Map<String, Map<UserPermission, Boolean>>> users, UserStatus status, boolean locked) throws InterruptedException;

	public void setRoles(String userId, Map<UserPermission, Boolean> UserPermissions) throws InterruptedException;

	public SearchUserPageWeb setPassword(Map<String, List<String>> users, Map<String, String> userIdAndPassword, UserStatus status, boolean statusLocked) throws InterruptedException;

	public Map<String, String> selectAndResetUsers(Map<String, List<String>> users, Map<String, String> userIdAndPassword, UserStatus status, boolean locked) throws InterruptedException;

	public boolean verifyMakeAnonymiseButton();

	public void clickOnMakeAnonymise();

	public boolean verifyMakeAnonymiseModel(String heading, String buttonName);

	public boolean verifyMakeAnonymiseModelHeading(String heading);

	public boolean verifyMakeAnonymiseModelButton(String buttonName);

	public boolean verifyMakeAnonymiseModelMessage(String message);

	public void clickOnCancelButtonOfAnonymiseModel();

	public void clickOnAnonymiseButtonOfAnonymiseModel();

	public ViewUserProfileWeb clickOnSearchResultUser(String username);

	public void selectUserFromSearchPage(Map<String, List<String>> users, UserStatus status, boolean locked) throws InterruptedException;

	public boolean isCheckboxEnable(String userEmail);

	public SystemAdminSelfRegisteredUsersWeb gotoSelfRegisteredUsers();
}
