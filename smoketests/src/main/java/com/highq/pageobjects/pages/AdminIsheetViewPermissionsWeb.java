package com.highq.pageobjects.pages;

import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.highq.pageobjects.base.AdminIsheetViewPermissionsPage;

public class AdminIsheetViewPermissionsWeb extends AdminIsheetManageViewsWeb implements AdminIsheetViewPermissionsPage
{
	public AdminIsheetViewPermissionsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By locChkbxAllUserAccess = By.xpath(".//*[text()='Access to all users']//child::*[@type='checkbox']");
	By locBtnViewPermissionSaveBottom = By.id("viewPermissionSaveBottom");
	By locBtnViewPermissionSaveTop = By.id("viewPermissionSaveTop");
	By locBtnViewPermissionCancelTop = By.id("viewPermissionCancelTop");
	By locBtnViewPermissionCancelBottom = By.id("viewPermissionCancelBottom");
	By locInptSearchBox = By.xpath(".//*[@placeholder='Quick search' and @type = 'text']");

	@Override
	public void setAccessToAllUsers(boolean desiredState)
	{
		selectCheckBoxOption("Access to all users", desiredState);
	}

	@Override
	public AdminIsheetManageViewsWeb clickSaveOnViewPermissions()
	{
		WebElement btnViewPermissionSaveTop = findPresentElement(locBtnViewPermissionSaveTop);
		scrollToElement(btnViewPermissionSaveTop);
		btnViewPermissionSaveTop.click();
		return new AdminIsheetManageViewsWeb(driver);
	}

	@Override
	public AdminIsheetManageViewsWeb clickCancelOnViewPermissions()
	{
		WebElement btnViewPermissionCancelTop = findPresentElement(locBtnViewPermissionCancelTop);
		scrollToElement(btnViewPermissionCancelTop);
		btnViewPermissionCancelTop.click();
		return new AdminIsheetManageViewsWeb(driver);
	}

	@Override
	public void setIsheetPermission(LinkedHashMap<String, Map<String, Boolean>> permissionData)
	{
		if (!findClickableElement(locChkbxAllUserAccess).isSelected())
		{
			String userEmail;
			String userDisplayName;
			String domainName;

			for (Map.Entry<String, Map<String, Boolean>> permission : permissionData.entrySet())
			{
				if (permission.getKey().contains("@"))
				{
					userEmail = permission.getKey();
					String org = getUserOrgName(userEmail);
					userDisplayName = getUserName(userEmail);

					if (isDisplayed(By.xpath(".//*[normalize-space()='" + org.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]")))
					{
						WebElement iconArrow = findClickableElement(By.xpath(".//*[normalize-space()='" + org.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]"));
						iconArrow.click();
					}
					findVisibleElement(By.xpath(".//*[normalize-space()='" + org.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-down')][1]"), Speed.slow);
					setUserViewPermission(org, userDisplayName, permission.getValue());
				}
				else
				{
					domainName = permission.getKey();
					setDomainViewPermission(domainName, permission.getValue());
				}
			}
		}
		else
		{
			System.err.println("Deselect Access to all users Checkbox");
			Assert.assertTrue(false);
		}
	}

	@Override
	public void setUserViewPermission(String domain, String user, Map<String, Boolean> permissionData)
	{
		String permissionValue;
		boolean desiredPermissionState;
		By locCheckBoxPermission;

		for (Map.Entry<String, Boolean> userPermission : permissionData.entrySet())
		{
			permissionValue = userPermission.getKey();
			desiredPermissionState = userPermission.getValue();

			if (permissionValue.equalsIgnoreCase("view"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='VIEW_OWN'][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
			if (permissionValue.equalsIgnoreCase("print"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='PRINT'][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
			if (permissionValue.equalsIgnoreCase("export"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='EXPORT'][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
		}
	}

	@Override
	public void setDomainViewPermission(String domain, Map<String, Boolean> permissionData)
	{
		String permissionValue;
		boolean desiredPermissionState;
		By locCheckBoxPermission;

		for (Map.Entry<String, Boolean> domainPermission : permissionData.entrySet())
		{
			permissionValue = domainPermission.getKey();
			desiredPermissionState = domainPermission.getValue();

			if (permissionValue.equalsIgnoreCase("view"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'vOwn')][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
			if (permissionValue.equalsIgnoreCase("print"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'print')][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
			if (permissionValue.equalsIgnoreCase("export"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'export')][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
		}
	}
}
