package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.pageobjects.base.AspAdminPage;

public class AspAdminWeb extends BannerPageWeb implements AspAdminPage
{

	By configurationLink = By.xpath(".//*[@id='configurationID']//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION + "']");

	public AspAdminWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	public AspConfigurationWeb openConfigurationPage() throws InterruptedException
	{
		WebElement configuration = findVisibleElement(configurationLink, Speed.slow);
		configuration.click();
		return new AspConfigurationWeb(driver);
	}

	public boolean verifyAspConfigurationPage()
	{
		return isDisplayed(configurationLink, Speed.slow);
	}
}
