/**
 * 
 */
package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AddUserPage;

/**
 * @author vivek.mishra
 */
public class AdminUserManageOrganizationsWeb extends BannerPageWeb
{
	AddUserPage addUserPage;

	By cancelButton = By.xpath("//*[@id='updateOrgStatus']//*[@class='cancel']");

	By saveButton = By.xpath("//*[@id='updateOrgStatus']//*[@class='btn btn-primary']");

	public AdminUserManageOrganizationsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @param org
	 * @param type
	 *        To enable disable bidder/Non-bidder permission
	 */
	public void setBidderNonBidderPermission(String org, String type)
	{
		if (type.equalsIgnoreCase("Bidder"))
			findClickableElement(By.xpath("//td[normalize-space()='" + org + "']//following-sibling::*/input[@value='2']")).click();
		else if (type.equalsIgnoreCase("Non-bidder"))
			findClickableElement(By.xpath("//td[normalize-space()='" + org + "']//following-sibling::*/input[@value='1']")).click();
	}

	public AddUserWeb clickOnCancelButton()
	{
		findClickableElement(cancelButton).click();
		return (new AddUserWeb(driver));
	}

	public AddUserWeb clickOnSaveButton()
	{
		findClickableElement(saveButton).click();
		return (new AddUserWeb(driver));
	}

}
