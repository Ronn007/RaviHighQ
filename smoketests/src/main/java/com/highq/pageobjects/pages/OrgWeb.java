package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.OrgLabels;
import com.highq.pageobjects.base.OrgPage;

public class OrgWeb extends BannerPageWeb implements OrgPage
{

	By saveButtonlink = By.xpath("//*[normalize-space(text())='" + OrgLabels.ORGWEB_SAVE + "']");
	By backLink = By.xpath("//*[normalize-space(text())='" + OrgLabels.ORGWEB_BACK + "']");
	By orgNameInputBox = By.id("organisation_txtName");
	By leftPanelItems = By.xpath("//*[@id='leftPanel']/ul");

	public OrgWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	public void setOrgStatus(String status, boolean requiredState) throws InterruptedException
	{
		By orgStatus = By.xpath("//label[normalize-space(text())='" + status.trim() + "']//following::input[1]");
		setSelection(orgStatus, requiredState);
		findClickableElement(saveButtonlink).click();
		backToOrgAdmin();

	}

	public void setOrgName(String orgName)
	{
		WebElement orgnameTextBox = findVisibleElement(orgNameInputBox);
		orgnameTextBox.clear();
		orgnameTextBox.sendKeys(orgName);
	}

	public OrgAdminWeb backToOrgAdmin() throws InterruptedException
	{
		//// moveToElement(backLink);
		scrollToTop();
		if (driver.findElements(leftPanelItems).size() > 1)
		{
			WebElement backButton = findClickableElement(backLink, Speed.slow, 10);
			backButton.click();
		}

		return new OrgAdminWeb(driver);
	}

}
