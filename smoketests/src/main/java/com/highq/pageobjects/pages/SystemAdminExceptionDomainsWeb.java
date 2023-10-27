package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.OrgAdminLabels;
import com.highq.labels.collaborate.OrgLabels;
import com.highq.pageobjects.base.SystemAdminExceptionDomainsPage;

public class SystemAdminExceptionDomainsWeb extends BannerPageWeb implements SystemAdminExceptionDomainsPage
{
	By searchTextBox = By.id("organisation_searchTxt");
	By searchButton = By.linkText(OrgAdminLabels.ORGADMIN_SEARCH);
	By searchResult = By.xpath("//table[contains(@class,'tabTableRow')]/tbody");
	By addNewExceptionDomain = By.linkText(OrgAdminLabels.ORGADMIN_ADD_NEW_EXCEPTIONDOMAIN);
	By domainNameInputBox = By.id("Organisation_orgDomain");
	By domainStatusDropdown = By.id("Organisation_orgDomainStatus");
	By saveButtonlink = By.xpath("//*[normalize-space(text())='" + OrgLabels.ORGWEB_SAVE + "']");
	By cancelButtonlink = By.xpath(".//a[@class='cancel' and contains(@title,'Cancel')]");

	public SystemAdminExceptionDomainsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author ankit.motaval
	 * @param domainName
	 *        Search Exception Domain
	 * @created on 24/04/2018
	 */
	public void searchExceptionDomains(String domainName)
	{
		WebElement searchBar = findVisibleElement(searchTextBox, Speed.slow, 30);
		searchBar.clear();
		searchBar.sendKeys(domainName);

		WebElement searchButtonLink = findClickableElement(searchButton);
		searchButtonLink.click();
	}

	/**
	 * @author ankit.motaval
	 * @param domainName
	 *        Search or Add Exception Domain
	 * @created on 24/04/2018
	 */
	public void gotoExceptionDomain(String domainName)
	{
		searchExceptionDomains(domainName);
		if (!isDisplayed(searchResult))
		{
			WebElement addNewOrgLink = findClickableElement(addNewExceptionDomain);
			addNewOrgLink.click();
			setDomainName(domainName);
		}
		else
		{
			int size = driver.findElements(By.xpath(".//*[@id='name']/table[1]/tbody/tr")).size();
			for (int i = 1; i <= size; i++)
			{
				By domainPath = By.xpath(".//*[@id='name']/table[1]/tbody/tr[" + i + "]/td[1]");
				if (isDisplayed(domainPath))
				{
					WebElement elem = findVisibleElement(domainPath);
					if (elem.getText().trim().equalsIgnoreCase(domainName))
					{
						elem.click();
						break;
					}
				}
			}
		}
	}

	/**
	 * @author ankit.motaval
	 * @param domainName
	 *        Set Exception Domain Name
	 * @created on 24/04/2018
	 */
	public void setDomainName(String domainName)
	{
		WebElement orgnameTextBox = findVisibleElement(domainNameInputBox);
		orgnameTextBox.clear();
		orgnameTextBox.sendKeys(domainName);
	}

	/**
	 * @author ankit.motaval
	 *         Verify Domain Name Textbox is Enable
	 * @created on 24/04/2018
	 */
	public boolean isEnableDomainNameTextBox()
	{
		return findVisibleElement(domainNameInputBox).isEnabled();
	}

	/**
	 * @author ankit.motaval
	 *         Verify Domain Status Dropdown is Enable
	 * @created on 24/04/2018
	 */
	public boolean isEnableStatusDropdown()
	{
		return findVisibleElement(domainStatusDropdown).isEnabled();
	}

	/**
	 * @author ankit.motaval
	 *         Verify Save Button Available
	 * @created on 24/04/2018
	 */
	public boolean VerifySaveButton()
	{
		return isDisplayed(saveButtonlink, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         Verify Cancel Button Available
	 * @created on 24/04/2018
	 */
	public boolean VerifyCancelButton()
	{
		return isDisplayed(cancelButtonlink, Speed.slow);
	}
}
