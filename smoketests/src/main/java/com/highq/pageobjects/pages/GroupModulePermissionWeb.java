package com.highq.pageobjects.pages;

import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.GroupModulePermissionLabels;
import com.highq.pageobjects.base.GroupModulePermissionPage;

public class GroupModulePermissionWeb extends BannerPageWeb implements GroupModulePermissionPage
{

	By modulePermissions_Modal = By.xpath("//*[@name='frmModulePermission']");
	By modulePermissions_Save = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + GroupModulePermissionLabels.GROUPMODULEPERMISSION_SAVE + "'])[last()]");
	By modulePermissions_Cancel = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + GroupModulePermissionLabels.GROUPMODULEPERMISSION_CANCEL + "'])[last()]");

	By userPermissions_allUsers = By.xpath(".//*[@id='partiesList']/table/tbody/tr[not(@class='structureRow')]");

	public enum ModuleNames
	{
		Home, Activity, Files
	}

	public GroupModulePermissionWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Set module permission for a group
	 * 
	 * @param moduleMap
	 *        map with Key: (String)Module Name, Value: Map<String,Boolean>[Key:
	 *        (String)Permission Name, Value: (boolean)Permission State]
	 * @author dheeraj.rajput
	 */
	public void setModulePermissionForGroup(Map<String, Map<String, Boolean>> moduleMap)
	{
		boolean success = true;
		for (Map.Entry<String, Map<String, Boolean>> entry : moduleMap.entrySet())
		{
			String moduleName = entry.getKey().trim();
			if (moduleName != null)
			{
				By moduleXpath = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='"
						+ moduleName.trim() + "'])[last()]");
				if (!isDisplayed(moduleXpath))
				{
					System.err.println(moduleName + " is not displayed");
					success = false;
				}
				else
				{
					System.out.println("Module Name: " + moduleName);

					Map<String, Boolean> permissionMap = entry.getValue();

					for (Map.Entry<String, Boolean> entry2 : permissionMap.entrySet())
					{

						String permissionName = entry2.getKey().trim();
						boolean permissionState = entry2.getValue();
						if (EnumUtils.isValidEnum(ModuleNames.class, moduleName) && permissionName.equalsIgnoreCase("edit"))
						{
							System.err.println(permissionName + " permission can not be set for: " + moduleName);
						}
						else
						{
							By viewCheckBox = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + moduleName + "'])[last()]/following::*[contains(normalize-space(@title),'" + permissionName + " " + moduleName + "')]");
							By editCheckBox = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + moduleName + "'])[last()]/following::*[contains(normalize-space(@title),'" + permissionName + " " + moduleName + "')]");

							WebElement viewChkBox = findPresentElement(viewCheckBox, 5);
							WebElement editChkBox = findPresentElement(editCheckBox, 5);
							if (!viewChkBox.isEnabled() && !editChkBox.isEnabled())
							{
								System.err.println("Please uncheck [Use groups for permissioning] option from Advanced menu in site admin");
								success = false;
							}
							else
							{

								String lowerCase = permissionName.toLowerCase();
								if (GroupModulePermissionLabels.GROUPMODULEPERMISSION_VIEW.toLowerCase().equals(lowerCase))
								{
									if (!permissionState)
										System.err.println("You are removing all the permissions for: " + moduleName);
									selectPermissionCheckbox(viewCheckBox, permissionState);
								}
								else if (GroupModulePermissionLabels.GROUPMODULEPERMISSION_EDIT.toLowerCase().equals(lowerCase))
								{
									selectPermissionCheckbox(editCheckBox, permissionState);
								}
							}
						}
					}
				}
			}
		}
		if (success)
		{
			findVisibleElement(modulePermissions_Save).click();
			if (isDisplayed(globalMessageBarCloseLink))
				findVisibleElement(globalMessageBarCloseLink).click();
		}
	}

	/**
	 * Check/Uncheck permission checkbox
	 * 
	 * @param option
	 *        xpath of the checkbox
	 * @param check
	 *        true | false
	 * @author dheeraj.rajput
	 */
	public void selectPermissionCheckbox(By option, boolean check)
	{
		WebElement element = findVisibleElement(option);
		boolean checked = element.isSelected();
		if (checked != check)
			element.click();
	}

}
