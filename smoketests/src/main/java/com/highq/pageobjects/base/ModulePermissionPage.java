package com.highq.pageobjects.base;

import java.util.Map;
import org.openqa.selenium.By;
import com.highq.pageobjects.pages.AddUserWeb;

public interface ModulePermissionPage extends BannerPage
{

	public AddUserWeb setModulePermissionForUsers(Map<String, Map<String, Boolean>> moduleMap);

	public void selectPermissionCheckbox(By option, boolean check);

	public void selectFolderPermission(String permissionName, boolean state);

	public void selectFilePermission(String permissionName, boolean state);

	public void clickSave();

}
