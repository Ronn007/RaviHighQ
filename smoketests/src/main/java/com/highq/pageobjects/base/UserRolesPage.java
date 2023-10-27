package com.highq.pageobjects.base;

import java.util.Map;
import org.openqa.selenium.By;

public interface UserRolesPage extends BannerPage
{
	public void setUserRoles(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles);

	public void setRoles(String domain, String userName, String role, boolean desiredState);

	public void clickOnSave();

	public void clickOnCancel() throws InterruptedException;

	public void selectPermissionCheckbox(By option, boolean check);

}
