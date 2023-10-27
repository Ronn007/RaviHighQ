/**
 * 
 */
package com.highq.test.qanda;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminQAPermissionsPage;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.AdminQAPermissionsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 */
public class BaseQandA extends BannerPageWeb
{
	AdminPage adminPageWeb;
	AdminQAPermissionsPage adminQAndAWeb;

	public BaseQandA(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author vivek.mishra
	 * @param qAndADataAndRoles
	 * @return
	 * 		To set the permission
	 */
	public AdminQAPermissionsWeb setQAndAPermission(Map<String, Map<String, Boolean>> qAndADataAndRoles)
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminQAndAWeb = adminPageWeb.clickOnQandAPermissionsInLeftPanel();
		adminQAndAWeb.setQAndAPermissionGroupWise(qAndADataAndRoles);
		return new AdminQAPermissionsWeb(driver);
	}

	/**
	 * @author vivek.mishra
	 * @param topic
	 * @return
	 * 		To add a new topic
	 */
	public AdminQAPermissionsWeb addTopics(String[] topic)
	{
		adminPageWeb = new AdminPageWeb(driver);
		adminQAndAWeb = adminPageWeb.clickOnQandAPermissionsInLeftPanel();
		adminQAndAWeb.addTopic(topic);
		return new AdminQAPermissionsWeb(driver);
	}

}
