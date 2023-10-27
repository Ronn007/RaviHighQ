package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.pageobjects.base.AspAdminUserPermissionPage;

public class AspAdminUserPermissionWeb extends BannerPageWeb implements AspAdminUserPermissionPage
{

	public AspAdminUserPermissionWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By UserPermissionHeading = By.xpath("//*[normalize-space(text()) = '" + AspAndSystemAdmin.ASPADMIN_USERPERMISSION_HEADER + "']");
	By systemAdminChkBox = By.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_USERROLES_SYSTEMADMIN + "']//following::input[@type='checkbox'][1]");
	By internalUserChkBox = By.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_USERROLES_INTERTNALUSER + "']//following::input[@type='checkbox'][1]");
	By internalAspAdminChkBox = By.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_USERROLES_INTERTNALASPADMIN + "']//following::input[@type='checkbox'][1]");
	By createSiteChkBox = By.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_USERROLES_CREATESITE + "']//following::input[@type='checkbox'][1]");
	By saveButtonLink = By.linkText(AspAndSystemAdmin.ASPADMIN_USERROLES_SAVE);
	By cancelButtonLink = By.linkText(AspAndSystemAdmin.ASPADMIN_USERROLES_CANCEL);

	public boolean verifyUserPermissionHeading()
	{

		WebElement userParmissionHeader = findVisibleElement(UserPermissionHeading);
		if (userParmissionHeader.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setUserPermission(UserPermission UserPermissionValue, boolean permission)
	{
		String permissionToSelect = UserPermissionValue.toString();
		if (permissionToSelect.contains("_"))
		{
			permissionToSelect = UserPermissionValue.toString().replace("_", " ").trim();
		}

		By UserPermissionChkBox = By.xpath("//*[normalize-space(text())='" + permissionToSelect + "']//following::input[@type='checkbox'][1]");
		setSelection(UserPermissionChkBox, permission);
	}

	public void setSelection(By locator, boolean requiredState)
	{
		System.out.println(locator);
		WebElement ele = findClickableElement(locator);
		boolean currentState = ele.isSelected();
		if (currentState != requiredState)
		{
			ele.click();
		}
	}

	public void clickOnSave()
	{
		WebElement saveButton = findClickableElement(saveButtonLink);
		saveButton.click();
	}
}
