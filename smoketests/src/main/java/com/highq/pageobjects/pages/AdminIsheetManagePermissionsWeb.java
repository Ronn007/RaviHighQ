package com.highq.pageobjects.pages;

import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.highq.pageobjects.base.AdminIsheetManagePermissionsPage;

public class AdminIsheetManagePermissionsWeb extends AdminIsheetWeb implements AdminIsheetManagePermissionsPage
{

	public AdminIsheetManagePermissionsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By enablePermissionChkBox = By.id("sheetPermission");
	By managePermissionSaveBtn = By.id("managePermissionSaveBottom");
	By managePermissionCancelBtn = By.id("managePermissionCancleBottom");

	@Override
	public void selectEnablePermission(boolean val)
	{
		setSelection(enablePermissionChkBox, val);
	}

	@Override
	public AdminIsheetWeb clickSaveOnEnableIsheetPermission()
	{
		WebElement saveEle = findPresentElement(managePermissionSaveBtn);
		scrollToElement(saveEle);
		saveEle.click();
		return new AdminIsheetWeb(driver);
	}

	@Override
	public AdminIsheetWeb clickCancelOnEnableIsheetPermission()
	{
		WebElement cancelEle = findClickableElement(managePermissionCancelBtn);
		scrollToElement(cancelEle);
		cancelEle.click();
		return new AdminIsheetWeb(driver);
	}

	@Override
	public void setPermission(LinkedHashMap<String, Map<String, Map<String, Boolean>>> permissionData)
	{
		if (findClickableElement(enablePermissionChkBox).isSelected())
		{
			String userEmail;
			String domain;
			String domainName;

			for (Map.Entry<String, Map<String, Map<String, Boolean>>> permission : permissionData.entrySet())
			{
				if (permission.getKey().contains("@"))
				{
					userEmail = permission.getKey();
					domain = getUserOrgName(userEmail);

					if (isDisplayed(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]")))
					{
						WebElement iconArrow = findClickableElement(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]"));
						iconArrow.click();
					}
					findVisibleElement(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-down')][1]"));
					setUserPermission(domain, getUserData(userEmail), permission.getValue());
				}
				else
				{
					domainName = permission.getKey();
					setDomainPermission(domainName, permission.getValue());
				}
			}
		}
		else
		{
			System.err.println("Select Enable Permission Checkbox");
			Assert.assertTrue(false);
		}
	}

	@Override
	public void setUserPermission(String domain, String user, Map<String, Map<String, Boolean>> permissionData)
	{
		String permissionScope;
		Map<String, Boolean> userPermissions;
		String viewEditPermission;
		boolean desiredPermissionState;
		By locCheckBoxPermission;
		for (Map.Entry<String, Map<String, Boolean>> permission : permissionData.entrySet())
		{
			permissionScope = permission.getKey();
			userPermissions = permission.getValue();

			for (Map.Entry<String, Boolean> userPermission : userPermissions.entrySet())
			{
				viewEditPermission = userPermission.getKey();
				desiredPermissionState = userPermission.getValue();

				if (permissionScope.equalsIgnoreCase("all") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='VIEW_ALL'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("all") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='EDIT_ALL'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("my org") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='VIEW_MY_ORG_GROUP'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("my org") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='EDIT_MY_ORG_GROUP'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("own") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='VIEW_OWN'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("own") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='EDIT_OWN'][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
			}
		}
	}

	@Override
	public void setDomainPermission(String domain, Map<String, Map<String, Boolean>> permissionData)
	{
		String permissionScope;
		Map<String, Boolean> userPermissions;
		String viewEditPermission;
		boolean desiredPermissionState;
		By locCheckBoxPermission;
		for (Map.Entry<String, Map<String, Boolean>> permission : permissionData.entrySet())
		{
			permissionScope = permission.getKey();
			userPermissions = permission.getValue();

			for (Map.Entry<String, Boolean> userPermission : userPermissions.entrySet())
			{
				viewEditPermission = userPermission.getKey();
				desiredPermissionState = userPermission.getValue();

				if (permissionScope.equalsIgnoreCase("all") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'vAll')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("all") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'eAll')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("my org") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'vOrgGrp')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("my org") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'eOrgGrp')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("own") && viewEditPermission.equalsIgnoreCase("view"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'vOwn')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
				if (permissionScope.equalsIgnoreCase("own") && viewEditPermission.equalsIgnoreCase("edit"))
				{
					locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'eOwn')][1]");
					if (findVisibleElement(locCheckBoxPermission).isEnabled())
					{
						setSelection(locCheckBoxPermission, desiredPermissionState);
					}
				}
			}
		}
	}
}
