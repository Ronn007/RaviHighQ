package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import java.util.Map;
import com.highq.pageobjects.pages.AdminIsheetWeb;

public interface AdminIsheetManageColumnPermissionsPage extends BannerPage
{
	public void selectEnablePermission(boolean val);

	public AdminIsheetWeb clickSaveOnEnableIsheetPermission();

	public AdminIsheetWeb clickCancelOnEnableIsheetPermission();

	void setIsheetColumnPermission(LinkedHashMap<String, Map<String, Boolean>> permissionData);

	void setUserPermission(String domain, String user, Map<String, Boolean> permissionData);

	void setDomainPermission(String domain, Map<String, Boolean> permissionData);

	public void setInheritIsheetPermission(boolean desiredState);

}
