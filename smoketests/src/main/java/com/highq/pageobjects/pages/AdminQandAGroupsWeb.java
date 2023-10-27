/**
 * 
 */
package com.highq.pageobjects.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminQandACreateBidderGroupPage;
import com.highq.pageobjects.base.AdminQandACreateSellerGroupPage;
import com.highq.pageobjects.base.AdminQandAGroupsPage;

/**
 * @author vivek.mishra
 */
public class AdminQandAGroupsWeb extends BannerPageWeb implements AdminQandAGroupsPage
{
	AdminQandACreateBidderGroupPage adminCreateBidderGroupPage;
	AdminQandACreateSellerGroupPage adminCreateSellerGroupPage;
	AdminQandAGroupsPage adminQandAGroupsPage;

	/** Remove group button */
	By removeQandAGroupButton = By.id("RemoveOrgGroup");

	/** Create bidder group button */
	By createBidderGroupButton = By.id("CreateBidderGroup");

	/** Create seller group button */
	By createSellerGroupButton = By.id("CreateSellerGroup");

	By groupList = By.id("fileData");

	/**
	 * @param driver
	 */
	public AdminQandAGroupsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @param group
	 *        To click on particular group
	 */
	public void clickOnGroup(String groupName)
	{
		findVisibleElement(By.xpath("//*[text()='" + groupName.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @param group
	 *        To click on radio button of a group
	 */
	public void selectRadioOfGroup(String group)
	{

		findVisibleElement(By.xpath("//*[text()='" + group.trim() + "']/..//preceding-sibling::td/input"), Speed.slow).click();
	}

	/**
	 * To click on remove Q&A group button
	 */
	public void clickOnRemoveQandAGroupButton()
	{
		findVisibleElement(removeQandAGroupButton, Speed.slow).click();
	}

	/**
	 * To click on remove Q&A group button
	 */
	public void clickOnCreateBidderGroupButton()
	{
		findVisibleElement(createBidderGroupButton, Speed.slow).click();
	}

	/**
	 * To click on remove Q&A group button
	 */
	public void clickOnCreateSellerGroupButton()
	{
		findVisibleElement(createSellerGroupButton, Speed.slow).click();
	}

	/**
	 * @param groupName
	 *        To Remove a group
	 */
	public void removeGroup(String groupName)
	{
		selectRadioOfGroup(groupName);
		clickOnRemoveQandAGroupButton();
		if (isAlertPresent())
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	/**
	 * @param groupName
	 * @param organization
	 *        To create a bidder group
	 * @return
	 */
	public AdminQandAGroupsPage createBidderGroup(String groupName, String[] organization)
	{
		clickOnCreateBidderGroupButton();
		adminCreateBidderGroupPage = new AdminQandACreateBidderGroupWeb(driver);
		adminCreateBidderGroupPage.verifyCreateBidderGroupPage();
		adminCreateBidderGroupPage.sendTextInGroupNameTextBox(groupName);
		adminCreateBidderGroupPage.moveOrganizationFromAllOrganizationToMemberOrganization(organization);
		adminQandAGroupsPage = adminCreateBidderGroupPage.clickOnSaveButton();
		return adminQandAGroupsPage;
	}

	/**
	 * @param groupName
	 * @param organization
	 *        To create a seller group
	 * @return
	 */
	public AdminQandAGroupsPage createSellerGroup(String groupName, String[] organization)
	{
		clickOnCreateSellerGroupButton();
		adminCreateSellerGroupPage = new AdminQandACreateSellerGroupWeb(driver);
		adminCreateSellerGroupPage.verifyCreateSellerGroupPage();
		adminCreateSellerGroupPage.sendTextInGroupNameTextBox(groupName);
		adminCreateSellerGroupPage.moveOrganizationFromAllOrganizationToMemberOrganization(organization);
		adminQandAGroupsPage = adminCreateSellerGroupPage.clickOnSaveButton();
		return adminQandAGroupsPage;
	}

	/**
	 * @author vivek.mishra
	 * @return the group list availability
	 * @created on 20/04/2018
	 */
	public boolean verifyGroupList()
	{
		findVisibleElement(groupList, Speed.slow);
		return (isDisplayed(groupList, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param groupName to be verified
	 * @return
	 * @created on 20/04/2018
	 */
	public boolean verifyGroup(String groupName)
	{
		return (isDisplayed(By.xpath("//*[@class='table fixedLayout']//*[normalize-space(text())='" + groupName.trim() + "']"), Speed.slow));
	}
}
