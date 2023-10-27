package com.highq.test.dashboard;

import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.pages.BannerPageWeb;

public class BaseDashboardTest extends BannerPageWeb
{
	public BaseDashboardTest(WebDriver driver)
	{
		this.driver = driver;
	}

}
