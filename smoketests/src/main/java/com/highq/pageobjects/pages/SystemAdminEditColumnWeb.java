package com.highq.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.SystemAdminEditColumnPage;

public class SystemAdminEditColumnWeb extends SystemAdminIsheetAdminAddColumnWeb implements SystemAdminEditColumnPage
{
	public SystemAdminEditColumnWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

}
