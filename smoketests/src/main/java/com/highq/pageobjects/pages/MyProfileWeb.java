package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.MyProfilePage;

public class MyProfileWeb extends BannerPageWeb implements MyProfilePage
{
	/** Left Panel */
	By expandLeftPanelArrow = By.xpath("//*[@class='leftsidePanel']//*[contains(@class,'icon-chevron-right')]");
	String leftPanelTree = "//*[@class='treeSearchSection']";
	By leftPanel_SummaryLink = By.id("people_module_userProfile_summary");
	By leftPanel_FullProfileLink = By.id("people_module_userProfile_fullProfile");
	By leftPanel_FollowersLink = By.id("people_module_userProfile_followers");
	By leftPanel_FollowingLink = By.id("people_module_userProfile_following");
	By leftPanel_SharedImagesLink = By.id("people_module_userProfile_sharedImages");

	public MyProfileWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Expand my profile left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public void expandLeftPanel()
	{
		if (isDisplayed(expandLeftPanelArrow))
		{
			findVisibleElement(expandLeftPanelArrow).click();
			findVisibleElement(By.xpath(leftPanelTree));
		}
	}

	/**
	 * Click on summary in left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 * @return
	 * 		Created: 18 January 2018
	 *         Updated:
	 */
	public ViewUserProfileWeb gotoSummaryInLeftPanel()
	{
		expandLeftPanel();
		findVisibleElement(leftPanel_SummaryLink).click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * Click on Full profile in left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public void gotoFullProfileInLeftPanel()
	{
		expandLeftPanel();
		findVisibleElement(leftPanel_FullProfileLink).click();
	}

	/**
	 * Click on Following in left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public void gotoFollowingInLeftPanel()
	{
		expandLeftPanel();
		findVisibleElement(leftPanel_FollowingLink).click();
	}

	/**
	 * Click on Followers in left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public void gotoFollowersInLeftPanel()
	{
		expandLeftPanel();
		findVisibleElement(leftPanel_FollowersLink).click();
	}

	/**
	 * Click on Shared images in left panel
	 * 
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public void gotoSharedImagesInLeftPanel()
	{
		expandLeftPanel();
		findVisibleElement(leftPanel_SharedImagesLink).click();
	}

	/**
	 * Verify user available in following or followers page
	 * 
	 * @param userName
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 */
	public boolean verifyUserInFollowingOrFollowers(String userName)
	{
		By userXpath = By.xpath(".//*[@class='clearfix']//*[@class='profileData']//*[normalize-space(text())='" + getUserData(userName.trim()) + "']");
		return isDisplayed(userXpath, Speed.slow);
	}

}
