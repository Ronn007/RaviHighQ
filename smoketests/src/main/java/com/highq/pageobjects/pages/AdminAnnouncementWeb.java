package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminAnnouncementPage;

/**
 * @author nidhi.shah
 */
public class AdminAnnouncementWeb extends BannerPageWeb implements AdminAnnouncementPage
{
	public AdminAnnouncementWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By displayContentInput = By.xpath(".//*[text()='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_ANNOUNCEMENTS_DISPLAYCONTENT + "']//following::input[1]");
	By saveButton = By.id("announcementSaveBtnBottomID");
	By enableSiteAnnouncementCheckbox = By.id("enableSiteTermsId");
	By announcementContentBox = By.id("displayContentID");

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveButton);
		saveEle.click();
	}

	/**
	 * Enable/disable site announcement
	 * 
	 * @param state
	 *        Boolean: true - to check enable site announcement checkbox
	 *        false - to uncheck enable site announcement checkbox
	 * @author dheeraj.rajput
	 */
	public void enableSiteAnnouncement(boolean state)
	{
		setSelection(enableSiteAnnouncementCheckbox, state);
	}

	/**
	 * Set announcement text in content box
	 * 
	 * @param text
	 *        String to send in content box
	 * @author dheeraj.rajput
	 */
	public void setAnnouncementContent(String text)
	{
		WebElement contentBox = findVisibleElement(announcementContentBox);
		contentBox.clear();
		contentBox.sendKeys(text.trim());
	}
}
