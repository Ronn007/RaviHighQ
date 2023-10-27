package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminBidderOrganisationsPage;

public class AdminBidderOrganisationsWeb extends BannerPageWeb implements AdminBidderOrganisationsPage
{
	public AdminBidderOrganisationsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveButton = By.id("memberTeam_v4_SaveBtnBottum");
	By allBidderCheckbox = By.id("allOrganisationStatus");

	/**
	 * Save bidder changes
	 * 
	 * @author dheeraj.rajput
	 */
	public void clickSave()
	{
		WebElement saveEle = findVisibleElement(saveButton, Speed.slow);
		saveEle.click();
		waitTillGlobalMessageDissappears();
	}

	/**
	 * Make all organisations bidder
	 * 
	 * @param state
	 *        Boolean : true - to check
	 *        false - to uncheck
	 * @author dheeraj.rajput
	 */
	public void selectAllBidderCheckbox(boolean state)
	{
		setSelection(allBidderCheckbox, state);
	}

	/**
	 * Set bidder organisation
	 * 
	 * @param organisation
	 *        name of the organisation that has to be made bidder
	 * @param state
	 *        Boolean : true - to check
	 *        false - to uncheck
	 * @author dheeraj.rajput
	 */
	public void setBidderOrganisation(String organisation, boolean state)
	{
		By bidderCheckbox = By.xpath("//*[normalize-space(text())='" + organisation.trim() + "']/..//*[@type='checkbox']");
		if (findVisibleElement(bidderCheckbox, Speed.slow).isEnabled())
			setSelection(bidderCheckbox, state);
	}

}
