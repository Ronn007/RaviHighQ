package com.highq.pageobjects.base;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.highq.pageobjects.pages.AdminUserGroupsWeb;

public interface AdminUserCreateGroupPage extends BannerPage
{
	public void setGroupName(String groupName);

	public boolean addUsersInGroup(Map<String, List<String>> userData) throws IOException;

	public void addOrganizationInGroup(String orgName) throws IOException;

	public void selectCopyFilePermissionFrom(String premission) throws IOException;

	public AdminUserGroupsWeb clickSaveButton();

	public AdminUserGroupsWeb clickCancelButton();
}
