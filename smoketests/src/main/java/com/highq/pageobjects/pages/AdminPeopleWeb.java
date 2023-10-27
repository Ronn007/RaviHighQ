package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminPeoplePage;

/**
 * @author nidhi.shah
 */
public class AdminPeopleWeb extends BannerPageWeb implements AdminPeoplePage
{
	public AdminPeopleWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveLink = By.linkText(SiteAdminLabels.SITEADMIN_PEOPLE_SAVE); // .//*[normalize-space(text()) = 'HighQ']//preceding::input[1]
	By saveBottomButton = By.id("adminPeoplSaveBtnBottom");

	@Override
	public void setOption(String option, Boolean value)
	{
		WebElement optionEle = findClickableElement(By.xpath(".//*[normalize-space(.) = '" + option.trim() + "']//preceding-sibling::*[@type='checkbox'][1]"));
		if (optionEle.isEnabled())
		{
			Boolean currentStatus = optionEle.isSelected();
			if (currentStatus != value)
			{
				optionEle.click();
			}
		}
	}

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveBottomButton);
		saveEle.click();
	}
}
