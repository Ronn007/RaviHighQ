package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminAuditsContentManagementPage;

/**
 * @author paras.vankadi
 */
public class AdminAuditsContentManagementWeb extends AdminPageWeb implements AdminAuditsContentManagementPage
{

	public AdminAuditsContentManagementWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	@Override
	public boolean verifyOpenedInOfficeOnlineOfContentManagement(String msg)
	{
		By verifyOpenedInOfficeOnlineCheckbox = By.xpath(".//*[normalize-space(text())='" + msg.trim() + "']");
		return isDisplayed(verifyOpenedInOfficeOnlineCheckbox);
	}

}
