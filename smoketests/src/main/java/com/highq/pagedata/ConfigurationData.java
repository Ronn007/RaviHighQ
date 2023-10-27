package com.highq.pagedata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;

public class ConfigurationData
{
	private Map<String, Map<String, Boolean>> orgData;

	Map<String, List<String>> userMap;

	private UserStatus status;

	private boolean stausLocked;

	private String newPassword;

	private String siteName;

	private boolean siteGroupPermission;

	private String[] moduleList;

	private Map<String, Map<String, Map<UserPermission, Boolean>>> systemAdminUserRoles;

	private Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles;

	private Map<String, Map<String, Boolean>> modulePermission;

	private Map<String, String> userData;

	private ArrayList<Map<String, List<String>>> groupList;

	private LinkedHashMap<String, String> siteData;

	private String defaultLandingPage;

	private Map<String, LinkedHashMap<String, Boolean>> siteUserRoles;

	private LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission;

	private LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission;

	private Map<String, String> userIdAndPassword;

	private List<String> siteUsers;

	private String groupName = "";

	private String description = "";

	private String copyFolderAndFilePermissionFrom = "";

	private String[] members;

	private LinkedHashMap<String, String[]> adminGroupUsers;

	private Map<String, Map<String, String>> groupDetails;

	private LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserFilePermission;

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCopyFolderAndFilePermissionFrom()
	{
		return copyFolderAndFilePermissionFrom;
	}

	public void setCopyFolderAndFilePermissionFrom(String copyFolderAndFilePermissionFrom)
	{
		this.copyFolderAndFilePermissionFrom = copyFolderAndFilePermissionFrom;
	}

	public String[] getMembers()
	{
		return members;
	}

	public void setMembers(String... members)
	{
		this.members = members;
	}

	public ArrayList<Map<String, List<String>>> getGroupList()
	{
		return groupList;
	}

	public void setGroupList(ArrayList<Map<String, List<String>>> groupList)
	{
		this.groupList = groupList;
	}

	public Map<String, Map<String, Boolean>> getOrgData()
	{
		return orgData;
	}

	public void setOrgData(Map<String, Map<String, Boolean>> orgData)
	{
		this.orgData = orgData;
	}

	public Map<String, List<String>> getUserMap()
	{
		return userMap;
	}

	public void setUserMap(Map<String, List<String>> userMap)
	{
		this.userMap = userMap;
	}

	public UserStatus getStatus()
	{
		return status;
	}

	public void setStatus(UserStatus status)
	{
		this.status = status;
	}

	public boolean isStausLocked()
	{
		return stausLocked;
	}

	public void setStausLocked(boolean stausLocked)
	{
		this.stausLocked = stausLocked;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getSiteName()
	{
		return siteName;
	}

	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}

	public boolean getSiteGroupPermission()
	{
		return siteGroupPermission;
	}

	public void setSiteGroupPermission(boolean siteGroupPermission)
	{
		this.siteGroupPermission = siteGroupPermission;
	}

	public String[] getModuleList()
	{
		return moduleList;
	}

	public void setModuleList(String... moduleList)
	{
		this.moduleList = moduleList;
	}

	public Map<String, Map<String, Map<String, Boolean>>> getUserDataAndRoles()
	{
		return userDataAndRoles;
	}

	public void setUserDataAndRoles(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles)
	{
		this.userDataAndRoles = userDataAndRoles;
	}

	public Map<String, Map<String, Boolean>> getModulePermission()
	{
		return modulePermission;
	}

	public void setModulePermission(Map<String, Map<String, Boolean>> modulePermission)
	{
		this.modulePermission = modulePermission;
	}

	public Map<String, String> getUserData()
	{
		return userData;
	}

	public void setUserData(Map<String, String> userData)
	{
		this.userData = userData;
	}

	public Map<String, Map<String, Map<UserPermission, Boolean>>> getSystemAdminUserRoles()
	{
		return systemAdminUserRoles;
	}

	public void setSystemAdminUserRoles(Map<String, Map<String, Map<UserPermission, Boolean>>> systemAdminUserRoles)
	{
		this.systemAdminUserRoles = systemAdminUserRoles;
	}

	public LinkedHashMap<String, String> getSiteData()
	{
		return siteData;
	}

	public void setSiteData(LinkedHashMap<String, String> siteData)
	{
		this.siteData = siteData;
	}

	public String getDefaultLandingPage()
	{
		return defaultLandingPage;
	}

	public void setDefaultLandingPage(String defaultLandingPage)
	{
		this.defaultLandingPage = defaultLandingPage;
	}

	public Map<String, LinkedHashMap<String, Boolean>> getSiteUserRoles()
	{
		return siteUserRoles;
	}

	public void setSiteUserRoles(Map<String, LinkedHashMap<String, Boolean>> siteUserRoles)
	{
		this.siteUserRoles = siteUserRoles;
	}

	public LinkedHashMap<String, Map<String, Map<String, Boolean>>> getSiteUserModulePermission()
	{
		return siteUserModulePermission;
	}

	public void setSiteUserModulePermission(
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission)
	{
		this.siteUserModulePermission = siteUserModulePermission;
	}

	public Map<String, String> getUserIdAndPassword()
	{
		return userIdAndPassword;
	}

	public void setUserIdAndPassword(Map<String, String> userIdAndPassword)
	{
		this.userIdAndPassword = userIdAndPassword;
	}

	public LinkedHashMap<String, Map<String, Map<String, Boolean>>> getSiteGroupModulePermission()
	{
		return siteGroupModulePermission;
	}

	public void setSiteGroupModulePermission(
			LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteGroupModulePermission)
	{

		this.siteGroupModulePermission = siteGroupModulePermission;
	}

	public LinkedHashMap<String, String[]> getAdminGroupUsers()
	{
		return adminGroupUsers;
	}

	public void setAdminGroupUsers(LinkedHashMap<String, String[]> adminGroupUsers)
	{
		this.adminGroupUsers = adminGroupUsers;
	}

	public List<String> getSiteUsers()
	{
		return siteUsers;
	}

	public void setSiteUsers(List<String> siteUsers)
	{
		this.siteUsers = siteUsers;
	}

	public Map<String, Map<String, String>> getGroupDetails()
	{
		return groupDetails;
	}

	public void setGroupDetails(Map<String, Map<String, String>> groupDetails)
	{
		this.groupDetails = groupDetails;
	}

	public LinkedHashMap<String, Map<String, Map<String, Boolean>>> getSiteUserFilePermission()
	{
		return siteUserFilePermission;
	}

	public void setSiteUserFilePermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserFilePermission)
	{
		this.siteUserFilePermission = siteUserFilePermission;
	}

}
