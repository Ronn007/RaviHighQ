package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import java.util.Map;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetManagePermissionsPage extends BannerPage
{
	public void selectEnablePermission(boolean val);

	public AdminIsheetWeb clickSaveOnEnableIsheetPermission();

	public AdminIsheetWeb clickCancelOnEnableIsheetPermission();

	public void setPermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData);

	public void setUserPermission(String domain, String user, Map<String, Map<String, Boolean>> permissionData);

	public void setDomainPermission(String domain, Map<String, Map<String, Boolean>> permissionData);
}
