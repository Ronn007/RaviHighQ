package com.highq.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminIsheetEditViewPage;

public class AdminIsheetEditViewWeb extends AdminIsheetAddViewWeb implements AdminIsheetEditViewPage
{
	public AdminIsheetEditViewWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
}
