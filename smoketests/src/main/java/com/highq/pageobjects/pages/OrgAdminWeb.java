package com.highq.pageobjects.pages;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.OrgAdminLabels;
import com.highq.pageobjects.base.OrgAdminPage;
import com.highq.pageobjects.base.OrgPage;

public class OrgAdminWeb extends BannerPageWeb implements OrgAdminPage
{
	OrgPage orgWeb;// = new OrgWeb(driver);

	By orgAdminBanner = By.xpath("//*[normalize-space(text())='" + OrgAdminLabels.ORGADMIN_ORGANISATIONADMINISTRATION + "']");
	By searchTextBox = By.id("Organisation_searchTxt");
	By searchButton = By.linkText(OrgAdminLabels.ORGADMIN_SEARCH);
	By searchResult = By.xpath("//table[contains(@class,'tabTableRow')]/tbody");
	By addNewOrganisation = By.linkText(OrgAdminLabels.ORGADMIN_ADDNEWORGANISATION);
	By backLink = By.xpath("//*[normalize-space(text())='" + OrgAdminLabels.ORGADMIN_BACK + "']");

	public OrgAdminWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	public void verifyOrgPermission(Map<String, Map<String, Boolean>> orgData) throws InterruptedException
	{
		for (Map.Entry<String, Map<String, Boolean>> orgPermissions : orgData.entrySet())
		{
			String org = orgPermissions.getKey().split("\\.")[0];
			for (Map.Entry<String, Boolean> permission : orgPermissions.getValue().entrySet())
			{
				orgWeb = (OrgPage) gotoOrganization(org);
				orgWeb.setOrgStatus(permission.getKey(), permission.getValue());
				findPresentElement(orgAdminBanner, Speed.slow, 15);
			}
		}
	}

	public OrgWeb gotoOrganization(String organization)
	{
		searchOrg(organization);
		if (!isDisplayed(searchResult))
		{
			WebElement addNewOrgLink = findClickableElement(addNewOrganisation);
			addNewOrgLink.click();
			orgWeb = new OrgWeb(driver);
			orgWeb.setOrgName(organization);
		}
		else
		{
			int size = driver.findElements(By.xpath(".//*[@id='name']/table[1]/tbody/tr")).size();
			boolean result = false;
			for (int i = 1; i <= size; i++)
			{
				By organizationPath = By.xpath(".//*[@id='name']/table[1]/tbody/tr[" + i + "]/td[1]");
				if (isDisplayed(organizationPath))
				{
					// moveToElement(organizationPath);
					WebElement elem = findVisibleElement(organizationPath);
					if (elem.getText().trim().equalsIgnoreCase(organization))
					{
						result = true;
						elem.click();
						break;
					}
				}
			}
			if (!result)
			{
				WebElement addNewOrgLink = findClickableElement(addNewOrganisation);
				addNewOrgLink.click();
				orgWeb = new OrgWeb(driver);
				orgWeb.setOrgName(organization);
			}
			// By organizationPath = By.xpath("//td[normalize-space(text())='" + organization + "']");
			// //moveToElement(organizationPath);
			// WebElement org = findVisibleElement(organizationPath);
			// org.click();
		}

		return new OrgWeb(driver);
	}

	public void searchOrg(String orgName)
	{
		WebElement searchBar = findVisibleElement(searchTextBox, Speed.slow, 10);
		searchBar.clear();
		searchBar.sendKeys(orgName);

		WebElement searchButtonLink = findClickableElement(searchButton);
		searchButtonLink.click();

	}

	public SystemAdminWeb backToSystemAdmin() throws InterruptedException
	{
		// //moveToElement(backLink);
		scrollToTop();
		WebElement backButton = findClickableElement(backLink);
		backButton.click();

		return new SystemAdminWeb(driver);
	}

}
