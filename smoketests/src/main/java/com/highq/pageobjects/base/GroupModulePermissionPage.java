package com.highq.pageobjects.base;

import java.util.Map;
import org.openqa.selenium.By;

public interface GroupModulePermissionPage extends BannerPage
{

	public void setModulePermissionForGroup(Map<String, Map<String, Boolean>> moduleMap);

	public void selectPermissionCheckbox(By option, boolean check);

}
