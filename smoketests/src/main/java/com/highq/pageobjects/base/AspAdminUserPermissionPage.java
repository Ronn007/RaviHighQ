package com.highq.pageobjects.base;

import org.openqa.selenium.By;
import com.highq.base.CollaborateLabel.UserPermission;

public interface AspAdminUserPermissionPage extends BannerPage
{

	public boolean verifyUserPermissionHeading();

	public void setUserPermission(UserPermission userpermissionValue, boolean permission);

	public void setSelection(By locator, boolean requiredState);

	public void clickOnSave();
}
