package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminBidderTeamPage;

public class AdminBidderTeamWeb extends AdminPageWeb implements AdminBidderTeamPage
{

	By saveButton = By.id("bidderTeam_v4_SaveBtnBottomID");

	public AdminBidderTeamWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * Select all users of a bidder team organisation
	 * 
	 * @param orgName
	 *        name of the organisation
	 * @param state
	 *        true - Check
	 *        false - Uncheck
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Updated:
	 */
	public void selectAllUsersOfABidderTeamOrganisation(String orgName, boolean state)
	{
		By rightArrow = By.xpath("//*[normalize-space(text())='" + orgName.trim() + "']/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
		if (isDisplayed(rightArrow, Speed.slow))
			findVisibleElement(rightArrow).click();
		By selectAllXpath = By.xpath("//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::*[2]/following-sibling::*//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_SELECTALL + "']");
		findVisibleElement(selectAllXpath).click();
	}

	/**
	 * Save bidder team changes
	 * 
	 * @author dheeraj.rajput
	 *         Created: 07 March 2018
	 *         Update:
	 */
	public void saveBidderTeamChanges()
	{
		WebElement elem = findVisibleElement(saveButton);
		elem.click();
		waitTillGlobalMessageDissappears();
	}

}
