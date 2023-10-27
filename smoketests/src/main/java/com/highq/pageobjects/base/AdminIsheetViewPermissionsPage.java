package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import java.util.Map;
import com.highq.pageobjects.pages.AdminIsheetManageViewsWeb;

public interface AdminIsheetViewPermissionsPage extends BannerPage
{
	public void setAccessToAllUsers(boolean desiredState);

	public AdminIsheetManageViewsWeb clickSaveOnViewPermissions();

	public AdminIsheetManageViewsWeb clickCancelOnViewPermissions();

	public void setIsheetPermission(LinkedHashMap<String, Map<String, Boolean>> permissionData);

	public void setUserViewPermission(String domain, String user, Map<String, Boolean> permissionData);

	public void setDomainViewPermission(String domain, Map<String, Boolean> permissionData);
}
