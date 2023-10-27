/**
 * 
 */
package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminQandACreateBidderGroupPage;

/**
 * @author vivek.mishra
 */
public class AdminQandACreateBidderGroupWeb extends BannerPageWeb implements AdminQandACreateBidderGroupPage
{

	/** Group Name text box */
	By groupNameTextBox = By.id("groupName");

	/** Group ID text box */
	By groupIDTextBox = By.id("bidderGroupID");

	/** Question Limit text box */
	By questionLimitTextBox = By.id("questionLimit");

	/** All Organization List */
	By allOrganizationList = By.id("leftSidelistOfOrganisation");

	/** Member Organization List */
	By memberOrganizationList = By.id("rightSidelistOfOrganisation");

	/** Move right arrow */
	By moveRightArrow = By.xpath("//*[@class='sortSelectforwards']/span");

	/** Move left arrow */
	By moveLeftArrow = By.xpath("//*[contains(@class,'sortSelectBack')]/span");

	/** Save Button */
	By saveButton = By.id("adminQASaveBtnBottom");

	/** Cancel Button */
	By cancelButton = By.className("btn btn-cancel");

	/** Bidder group page */
	By bidderGroupPage = By.name("organisationGroup");

	/**
	 * @param driver
	 */
	public AdminQandACreateBidderGroupWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @param text
	 *        To send the text in group name text box
	 */
	public void sendTextInGroupNameTextBox(String text)
	{
		WebElement textBox = findVisibleElement(groupNameTextBox, Speed.slow);
		textBox.clear();
		textBox.sendKeys(text.trim());
	}

	/**
	 * @param text
	 *        To send the text in group ID text box
	 */
	public void sendTextInGroupIDTextBox(String text)
	{
		WebElement textBox = findVisibleElement(groupIDTextBox, Speed.slow);
		textBox.clear();
		textBox.sendKeys(text.trim());
	}

	/**
	 * @param text
	 *        To send the text in Question limit text box
	 */
	public void sendTextInQuestionLimitTextBox(String limit)
	{
		WebElement textBox = findPresentElement(questionLimitTextBox);
		textBox.clear();
		textBox.sendKeys(limit);
	}

	/**
	 * @param organization
	 * @return
	 * 		To verify the organization in all organization list
	 */
	public boolean verifyOrganizationInAllOrganizationList(String organization)
	{
		findVisibleElement(allOrganizationList, Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='leftSidelistOfOrganisation']//*[normalize-space()='" + organization.trim() + "']"), Speed.slow));
	}

	/**
	 * @param organization
	 * @return
	 * 		To verify the organization in member organization list
	 */
	public boolean verifyOrganizationInMemberOrganizationList(String organization)
	{
		findVisibleElement(memberOrganizationList, Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='rightSidelistOfOrganisation']//*[normalize-space()='" + organization.trim() + "']"), Speed.slow));
	}

	/**
	 * @param organization
	 *        To click on organization in member organization list
	 */
	public void clickOnOrganizationInMemberOrganizationList(String organization)
	{
		if (verifyOrganizationInMemberOrganizationList(organization))
			findVisibleElement(By.xpath("//*[@id='rightSidelistOfOrganisation']//*[normalize-space()='" + organization.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @param organization
	 *        To click on organization in All organization list
	 */
	public void clickOnOrganizationInAllOrganizationList(String organization)
	{
		if (verifyOrganizationInAllOrganizationList(organization))
			findVisibleElement(By.xpath("//*[@id='leftSidelistOfOrganisation']//*[normalize-space()='" + organization.trim() + "']"), Speed.slow).click();
	}

	/**
	 * To click on save button
	 * 
	 * @return
	 */
	public AdminQandAGroupsWeb clickOnSaveButton()
	{
		findVisibleElement(saveButton, Speed.slow).click();
		return new AdminQandAGroupsWeb(driver);
	}

	/**
	 * To click on cancel button
	 * 
	 * @return
	 */
	public AdminQandAGroupsWeb clickOnCancelButton()
	{
		findClickableElement(cancelButton).click();
		return new AdminQandAGroupsWeb(driver);
	}

	/**
	 * @param organization
	 *        To move organization from all organization list to member organization list
	 */
	public void moveOrganizationFromAllOrganizationToMemberOrganization(String[] organization)
	{
		for (int i = 0; i < organization.length; i++)
		{
			if (verifyOrganizationInAllOrganizationList(organization[i]))
			{
				clickOnOrganizationInAllOrganizationList(organization[i]);
				clickOnMoveRightArrow();
			}
		}
	}

	/**
	 * To click on left directed arrow
	 */
	public void clickOnMoveLeftArrow()
	{
		findVisibleElement(moveLeftArrow, Speed.slow).click();
	}

	/**
	 * To click on right directed arrow
	 */
	public void clickOnMoveRightArrow()
	{
		findVisibleElement(moveRightArrow, Speed.slow).click();
	}

	/**
	 * @return
	 * 		To verify the bidder group page
	 */
	public boolean verifyCreateBidderGroupPage()
	{
		findVisibleElement(bidderGroupPage, Speed.slow);
		return (isDisplayed(bidderGroupPage, Speed.slow));
	}

	/**
	 * @param organization
	 *        To move organization from member organization list to all organization list
	 */
	public void moveOrganizationFromMemberOrganizationToAllOrganization(String[] organization)
	{
		for (int i = 0; i < organization.length; i++)
		{
			if (verifyOrganizationInMemberOrganizationList(organization[i]))
			{
				clickOnOrganizationInMemberOrganizationList(organization[i]);
				clickOnMoveRightArrow();
			}
		}
	}

}
