package com.highq.pageobjects.pages;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminUserCreateGroupPage;

public class AdminUserCreateGroupsWeb extends BannerPageWeb implements AdminUserCreateGroupPage
{

	/*************************************** Create group xpaths *************************/
	By groupNameLable = By.xpath("//strong[text()='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_GROUPNAME + "']");
	By groupNameTextBox = By.id("groupName");

	By groupMembers = By.xpath("//strong[text()='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_GROUPMEMBERS + "']");
	By noSiteUsers = By.xpath(".//*[@id='treebox_xmlTreeFoldersObjLeft']//span[contains(text(),'" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_NOSITEUSERS + "')]");
	String organizationName = ".//*[@id='treebox_xmlTreeFoldersObjLeft']//span[@class='fancytree-title'][starts-with(text(),'";
	By addButtonLink = By.xpath(".//*[@id='groupARButton']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_ADD + "']");
	By removeButtonLink = By.xpath(".//*[@id='groupARButton']//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_REMOVE + "']");
	By copyFilePermissionList = By.id("permissionsFromGroup");
	By saveButtonLink = By.xpath("//a[text()='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_SAVE + "']");
	By cancellButtonLink = By.xpath("//a[text()='" + SiteAdminLabels.SITEADMIN_CREATEGROUPS_CANCEL + "']");

	public AdminUserCreateGroupsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void setGroupName(String groupName)
	{
		WebElement grpNameTxtBox = findVisibleElement(groupNameTextBox);
		grpNameTxtBox.clear();
		grpNameTxtBox.sendKeys(groupName);
	}

	@Override
	public boolean addUsersInGroup(Map<String, List<String>> userData) throws IOException
	{
		if (isDisplayed(noSiteUsers))
		{
			return false;
		}
		else
		{
			System.out.println("Add Group Members");
			for (Map.Entry<String, List<String>> map : userData.entrySet())
			{
				String orgName = map.getKey();
				orgName = orgName.split("\\.")[0];
				By expandOrg = By.xpath(".//*[@id='treebox_xmlTreeFoldersObjLeft']//span[contains(text(),'" + orgName + "')]/parent::*[contains(@class,'fancytree-has-children')]");
				if (isDisplayed(By.xpath(organizationName + orgName + "')]")) && isDisplayed(expandOrg))
				{
					Actions cls = new Actions(driver);
					cls.doubleClick(findClickableElement(By.xpath(organizationName + orgName + "')]"))).build().perform();
					// findClickableElement(driver, By.xpath(".//*[@id='treebox_xmlTreeFoldersObjLeft']//span[contains(text(),'" + orgName + "')]/parent::*[contains(@class,'fancytree-expanded')]"));
				}

				for (int i = 0; i < map.getValue().size(); i++)
				{
					System.out.println(map.getValue().get(i).trim());
					findClickableElement(By.xpath(".//*[@id='treebox_xmlTreeFoldersObjLeft']//span[@class='fancytree-title'][normalize-space(.)='" + getUserData(map.getValue().get(i).trim()) + "']")).click();
					findClickableElement(addButtonLink).click();
				}
			}
			return true;
		}

	}

	@Override
	public void addOrganizationInGroup(String orgName) throws IOException
	{
		System.out.println("Add organization to group");
		orgName = orgName.split("\\.")[0];
		WebElement organization = findClickableElement(By.xpath(organizationName + orgName + "')]"));
		if (organization.isDisplayed())
		{
			organization.click();
			findClickableElement(addButtonLink).click();
		}

	}

	@Override
	public void selectCopyFilePermissionFrom(String premission) throws IOException
	{
		System.out.println("Select copy permission");
		Select permissionsFromGroup = new Select(findVisibleElement(copyFilePermissionList));
		permissionsFromGroup.selectByVisibleText(premission.trim());
	}

	@Override
	public AdminUserGroupsWeb clickSaveButton()
	{
		WebElement saveButton = findClickableElement(saveButtonLink);
		saveButton.click();
		return new AdminUserGroupsWeb(driver);
	}

	@Override
	public AdminUserGroupsWeb clickCancelButton()
	{
		WebElement cancelButton = findClickableElement(cancellButtonLink);
		cancelButton.click();
		return new AdminUserGroupsWeb(driver);
	}

}
