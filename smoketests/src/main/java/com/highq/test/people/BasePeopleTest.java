package com.highq.test.people;

import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.pages.BannerPageWeb;

public class BasePeopleTest extends BannerPageWeb
{
	public BasePeopleTest(WebDriver driver)
	{
		this.driver = driver;
	}
}
