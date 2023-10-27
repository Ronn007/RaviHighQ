package com.highq.pageobjects.pages;

import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.ModulePermissionLabels;
import com.highq.pageobjects.base.ModulePermissionPage;
import com.highq.pageobjects.pages.GroupModulePermissionWeb.ModuleNames;

public class ModulePermissionWeb extends BannerPageWeb implements ModulePermissionPage
{

	By modulePermissions_Modal = By.xpath("//*[@name='frmModulePermission']");
	By modulePermissions_Save = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + ModulePermissionLabels.MODULEPERMISSION_SAVE + "'])[last()]");
	By modulePermissions_Cancel = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + ModulePermissionLabels.MODULEPERMISSION_CANCEL + "'])[last()]");

	By filePermission_Folder_view = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'folderAccess')])[1]");
	By filePermission_Folder_addFile = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'updateAll')])[1]");
	By filePermission_Folder_admin = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'addDocument')])[1]");
	By filePermission_File_view = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'viewAll')])[1]");
	By filePermission_File_disablePrintCopy = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'disablePrintOrCopy')])[1]");
	By filePermission_File_disableSave = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'disableSave')])[1]");
	By filePermission_File_watermark = By.xpath("(//*[@class='PermissionData']//*[@id='permission'][1]//*[contains(@name,'applyWatermark')])[1]");

	By filePermission_ExpandAll = By.id("siteAdmin_userfilePermission_Tree_expandAll");
	By filePermission_CollapseAll = By.id("siteAdmin_userfilePermission_Tree_collapseAll");
	By saveButton = By.xpath("(//*[normalize-space(.)='" + ModulePermissionLabels.MODULEPERMISSION_SAVE + "'])[last()]");

	public ModulePermissionWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Set module permission for a user
	 * 
	 * @param moduleMap
	 *        map with Key: (String)Module Name, Value: Map<String,Boolean>[Key: (String)Permission Name, Value: (boolean)Permission State]
	 * @author dheeraj.rajput
	 */
	public AddUserWeb setModulePermissionForUsers(Map<String, Map<String, Boolean>> moduleMap)
	{
		boolean success = true;
		for (Map.Entry<String, Map<String, Boolean>> entry : moduleMap.entrySet())
		{
			String moduleName = entry.getKey();
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
						String permissionName = entry2.getKey();
						boolean permissionState = entry2.getValue();
						if (EnumUtils.isValidEnum(ModuleNames.class, moduleName) && permissionName.equalsIgnoreCase("edit"))
						{
							System.err.println(permissionName + " permission can not be set for: " + moduleName);
						}
						else
						{
							By viewCheckBox = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + moduleName.trim() + "'])[last()]/following::*[contains(normalize-space(@title),'" + permissionName.trim() + " " + moduleName.trim() + "')]");
							By editCheckBox = By.xpath("(//*[@name='frmModulePermission']//*[normalize-space(.)='" + moduleName.trim() + "'])[last()]/following::*[contains(normalize-space(@title),'" + permissionName.trim() + " " + moduleName.trim() + "')]");

							WebElement viewChkBox = findPresentElement(viewCheckBox, 5);
							WebElement editChkBox = findPresentElement(editCheckBox, 5);
							if (!viewChkBox.isEnabled() && !editChkBox.isEnabled())
							{
								System.err.println("Please uncheck [Use groups for permissioning] option from Advanced menu in site admin");
								success = false;
								if (isDisplayed(modulePermissions_Cancel))
								{
									findVisibleElement(modulePermissions_Cancel).click();
								}
							}
							else
							{

								switch (permissionName.toLowerCase())
								{
									case "view":
										if (!permissionState)
											System.err.println("You are removing all the permissions for: " + moduleName);
										selectPermissionCheckbox(viewCheckBox, permissionState);
										break;

									case "edit":
										selectPermissionCheckbox(editCheckBox, permissionState);
										break;
								}
							}
						}
					}
				}
			}
		}

		if (isDisplayed(modulePermissions_Save))
		{
			findVisibleElement(modulePermissions_Save).click();
			if (isDisplayed(globalMessageBarCloseLink))
				findVisibleElement(globalMessageBarCloseLink).click();

			findVisibleElement(breadCrumb, Speed.slow, 10);
		}

		return new AddUserWeb(driver);

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

	/**
	 * Set folder permission for a user
	 * 
	 * @param permissionName
	 *        to set (parameter has to be one of the three(view,add file,admin) values)
	 * @param state
	 *        true/false to check/uncheck the checkbox
	 * @author dheeraj.rajput
	 */
	public void selectFolderPermission(String permissionName, boolean state)
	{
		driver.switchTo().frame(0);
		String lowerCase = permissionName.trim().toLowerCase();
		if (ModulePermissionLabels.MODULEPERMISSION_VIEW.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_Folder_view, state);
		}
		else if (ModulePermissionLabels.MODULEPERMISSION_ADDFILE.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_Folder_addFile, state);
		}
		else if (ModulePermissionLabels.MODULEPERMISSION_ADMIN.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_Folder_admin, state);
		}
		else
		{
			System.err.println(permissionName + " not found.");
		}
		driver.switchTo().defaultContent();
		clickSave();
	}

	/**
	 * Set file permission for a user
	 * 
	 * @param permissionName
	 *        to set (parameter has to be one of the three(view,disable print,disable save,watermark) values)
	 * @param state
	 *        true/false to check/uncheck the checkbox
	 * @author dheeraj.rajput
	 */
	public void selectFilePermission(String permissionName, boolean state)
	{
		String lowerCase = permissionName.trim().toLowerCase();
		if (ModulePermissionLabels.MODULEPERMISSION_VIEW.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_File_view, state);
		}
		else if (ModulePermissionLabels.MODULEPERMISSION_ADMIN.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_File_disablePrintCopy, state);
		}
		else if (ModulePermissionLabels.MODULEPERMISSION_DISABLESAVE.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_File_disableSave, state);
		}
		else if (ModulePermissionLabels.MODULEPERMISSION_WATERMARK.toLowerCase().equals(lowerCase))
		{
			selectPermissionCheckbox(filePermission_File_watermark, state);
		}
		else
		{
			System.err.println(permissionName + " not found.");
		}
	}

	public void clickSave()
	{
		findVisibleElement(saveButton).click();
	}
}
