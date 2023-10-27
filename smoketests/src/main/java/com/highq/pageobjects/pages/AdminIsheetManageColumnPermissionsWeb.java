package com.highq.pageobjects.pages;

import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.highq.pageobjects.base.AdminIsheetManageColumnPermissionsPage;

public class AdminIsheetManageColumnPermissionsWeb extends AdminIsheetWeb implements AdminIsheetManageColumnPermissionsPage
{

	public AdminIsheetManageColumnPermissionsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By enablePermissionChkBox = By.id("columnPermission");
	By managePermissionSaveBtn = By.id("manageColumnPermissionSaveBottom");
	By managePermissionCancelBtn = By.id("manageColumnPermissionCancleBottom");

	@Override
	public void selectEnablePermission(boolean val)
	{
		setSelection(enablePermissionChkBox, val);
	}

	@Override
	public AdminIsheetManageColumnWeb clickSaveOnEnableIsheetPermission()
	{
		WebElement saveEle = findPresentElement(managePermissionSaveBtn);
		scrollToElement(saveEle);
		saveEle.click();
		return new AdminIsheetManageColumnWeb(driver);
	}

	@Override
	public AdminIsheetManageColumnWeb clickCancelOnEnableIsheetPermission()
	{
		WebElement cancelEle = findClickableElement(managePermissionCancelBtn);
		scrollToElement(cancelEle);
		cancelEle.click();
		return new AdminIsheetManageColumnWeb(driver);
	}

	/**
	 * @param desiredState
	 */
	@Override
	public void setInheritIsheetPermission(boolean desiredState)
	{
		selectCheckBoxOption("Inherit iSheet permission", desiredState);
	}

	@Override
	public void setIsheetColumnPermission(LinkedHashMap<String, Map<String, Boolean>> permissionData)
	{
		if (!findClickableElement(enablePermissionChkBox).isSelected())
		{
			String userEmail;
			// String[] userName;
			String userDisplayName;
			String domain;
			String domainName;

			for (Map.Entry<String, Map<String, Boolean>> permission : permissionData.entrySet())
			{
				if (permission.getKey().contains("@"))
				{
					userEmail = permission.getKey();
					// userName = userEmail.split("@");
					userDisplayName = getUserName(userEmail);
					domain = getUserOrgName(userEmail);

					if (isDisplayed(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]")))
					{
						WebElement iconArrow = findClickableElement(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-right')][1]"));
						iconArrow.click();
					}
					findVisibleElement(By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//child::*[contains(@class,'icon-chevron-down')][1]"));
					setUserPermission(domain, userDisplayName, permission.getValue());
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
	public void setUserPermission(String domain, String user, Map<String, Boolean> permissionData)
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
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='VIEW_ALL'][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
			if (permissionValue.equalsIgnoreCase("edit"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[normalize-space(text())='" + user.trim() + "']//parent::*//following::*[@name='EDIT_ALL'][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}

		}
	}

	@Override
	public void setDomainPermission(String domain, Map<String, Boolean> permissionData)
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
			if (permissionValue.equalsIgnoreCase("edit"))
			{
				locCheckBoxPermission = By.xpath(".//*[normalize-space()='" + domain.trim() + "']//parent::*//following::*[contains(@name,'eAll')][1]");
				if (findVisibleElement(locCheckBoxPermission).isEnabled())
				{
					setSelection(locCheckBoxPermission, desiredPermissionState);
				}
			}
		}
	}
}
