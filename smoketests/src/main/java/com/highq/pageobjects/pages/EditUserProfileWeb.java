package com.highq.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import com.highq.base.CollaborateLabel.AllUserActions;
import com.highq.pageobjects.base.EditUserProfilePage;

public class EditUserProfileWeb extends BannerPageWeb implements EditUserProfilePage
{

	public EditUserProfileWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public String replaceTextInUserMenuXpath(AllUserActions option)
	{
		String menuListXpath = "//li[@class='dropdown userMenuBtn open']//li[normalize-space(.)='{0}']";
		String menuItem;
		if (option.toString().contains("_"))
		{
			menuItem = option.toString().trim().replaceAll("_", " ");
		}
		else
		{
			menuItem = option.toString().trim();
		}
		menuListXpath = menuListXpath.replace("{0}", menuItem);
		return menuListXpath;
	}

}
