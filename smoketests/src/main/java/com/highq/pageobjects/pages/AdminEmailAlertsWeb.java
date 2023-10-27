package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminEmailAlertsPage;

public class AdminEmailAlertsWeb extends BannerPageWeb implements AdminEmailAlertsPage
{
	By saveButton = By.id("siteAdmin_emailAlerts_saveBottom");

	/** Default alert for new site users */
	By defaultAlertDropDown = By.xpath("//*[@data-id='siteAdmin_emailAlerts_Defaultalertselectpicker']");
	By defaultAlertComboBox = By.xpath("//*[@class='dropdown-menu open']");
	String defaultAlertOptions = "//*[@class='dropdown-menu open']//li";

	/** Search panel */
	By searchInput = By.id("siteAdmin_emailAlerts_searchFilterUsers");
	By clearSearchIcon = By.xpath("//*[@data-original-title='Clear search term' and not(contains(@style,'none'))]");
	By userTable = By.id("siteAdmin_emailAlert_userListheader");

	/** Filter */
	By filterButton = By.id("siteAdmin_emailAlerts_Filter");
	By filterExpanded = By.xpath(".//*[@id='siteAdmin_emailAlerts_Filter' and @aria-expanded='true']/following-sibling::*[contains(@class,'dropdown')]");
	By filter_Organisation = By.xpath(".//*[@id='accordion1']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_EMAILALERTS_FILTER_ORGANIZATION + "']//*[contains(@class,'icon-chevron-right')]");
	By filter_Status = By.xpath(".//*[@id='accordion1']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_EMAILALERTS_FILTER_STATUS + "']//*[contains(@class,'icon-chevron-right')]");
	By filter_Frequency = By.xpath(".//*[@id='accordion1']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_EMAILALERTS_FILTER_FREQUENCY + "']//*[contains(@class,'icon-chevron-right')]");
	By filter_Organisation_SearchInput = By.id("siteadmin_UserFilterOrganizationSerachButtonID");
	By filter_Organisation_ClearSearchIcon = By.id("siteadmin_UserFilterOrganizationSerachClearButtonID");
	By filter_Organisation_NoResultsFound = By.xpath(".//*[@id='siteadmin_userfilterOrganizationListDivID']//*[normalize-space(text())='No results found']");
	By clearFilters = By.id("linkClearFilter");

	public AdminEmailAlertsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Click on save
	 * 
	 * @author dheeraj.rajput
	 */
	public void clickSave()
	{
		findVisibleElement(saveButton).click();
		findVisibleElement(globalMessageBarCloseLink, Speed.slow).click();
	}

	/**
	 * Select default alert for new site users
	 * 
	 * @param alertOption
	 *        alert option to select from dropdown
	 * @author dheeraj.rajput
	 */
	public void selectDefaultAlertOption(String alertOption)
	{
		selectOptionFromDropDown(defaultAlertDropDown, defaultAlertComboBox, defaultAlertOptions, alertOption);
	}

	/**
	 * Search email alert options
	 * 
	 * @param optionToSearch
	 *        option to search - Email, username, organisation ...
	 * @author dheeraj.rajput
	 * @return false
	 *         if no results found
	 */
	public boolean searchEmailAlertOptions(String optionToSearch)
	{
		WebElement searchBox = findVisibleElement(searchInput);
		searchBox.clear();
		searchBox.sendKeys(optionToSearch.trim());
		findVisibleElement(userTable);
		if (noResultsFound())
			return false;
		else
			return true;
	}

	/**
	 * Set email alert frequency
	 * 
	 * @param userEmail
	 *        email address of user
	 * @param frequency
	 *        mail frequency (immediate,daily,weekly)
	 * @author dheeraj.rajput
	 */
	public void setEmailAlertFrequencyForUser(String userEmail, String frequency)
	{
		if (searchEmailAlertOptions(userEmail))
		{
			By immediateFrequencyCheckBox = By.xpath("//*[normalize-space(text())='" + userEmail.trim() + "']/ancestor::*[@class='userListName']/following-sibling::*/*[contains(@id,'immediate')]");
			By dailyFrequencyCheckBox = By.xpath("//*[normalize-space(text())='" + userEmail.trim() + "']/ancestor::*[@class='userListName']/following-sibling::*/*[contains(@id,'daily')]");
			By weeklyFrequencyCheckBox = By.xpath("//*[normalize-space(text())='" + userEmail.trim() + "']/ancestor::*[@class='userListName']/following-sibling::*/*[contains(@id,'weekly')]");
			String lowerCase = frequency.trim().toLowerCase();
			if (SiteAdminLabels.SITEADMIN_EMAILALERTS_FREQUENCY_IMMEDIATE.toLowerCase().equals(lowerCase))
			{
				setSelection(immediateFrequencyCheckBox, true);
				setSelection(dailyFrequencyCheckBox, false);
				setSelection(weeklyFrequencyCheckBox, false);
			}
			else if (SiteAdminLabels.SITEADMIN_EMAILALERTS_FREQUENCY_DAILY.toLowerCase().equals(lowerCase))
			{
				setSelection(dailyFrequencyCheckBox, true);
				setSelection(immediateFrequencyCheckBox, false);
				setSelection(weeklyFrequencyCheckBox, false);
			}
			else if (SiteAdminLabels.SITEADMIN_EMAILALERTS_FREQUENCY_WEEKLY.toLowerCase().equals(lowerCase))
			{
				setSelection(weeklyFrequencyCheckBox, true);
				setSelection(immediateFrequencyCheckBox, false);
				setSelection(dailyFrequencyCheckBox, false);
			}
			else
			{
				System.out.println(frequency + " not found.");
			}
		}
		else
			System.err.println(findVisibleElement(noResultsFound).getText());

	}

	/**
	 * Expand filter
	 * 
	 * @author dheeraj.rajput
	 */
	public void expandFilter()
	{
		if (!isDisplayed(filterExpanded))
			findVisibleElement(filterButton).click();

	}

	/**
	 * Filter by organisation
	 * 
	 * @param organisationName
	 *        organisation name to select
	 * @param state
	 *        Boolean : true - to check organisation checkbox
	 *        false - to uncheck organisation checkbox
	 * @author dheeraj.rajput
	 */
	public void filterByOrganisation(String organisationName, boolean state)
	{
		expandFilter();
		if (isDisplayed(filter_Organisation, Speed.slow))
			findVisibleElement(filter_Organisation).click();
		WebElement searchBox = findVisibleElement(filter_Organisation_SearchInput);
		searchBox.clear();
		searchBox.sendKeys(organisationName.trim());
		findVisibleElement(filter_Organisation_ClearSearchIcon);
		if (!isDisplayed(filter_Organisation_NoResultsFound, Speed.slow))
		{
			By checkboxToClick = By.xpath("//*[@id='siteadmin_userfilterOrganizationListDivID']//*[normalize-space(text())='" + organisationName.trim() + "']/preceding-sibling::*[@type='checkbox']");
			setSelection(checkboxToClick, state);
			findVisibleElement(filter_Organisation_ClearSearchIcon).click();
		}
		else
			System.err.println(findVisibleElement(filter_Organisation_NoResultsFound).getText());

	}

	/**
	 * Filter by status
	 * 
	 * @param status
	 *        status name to select
	 * @param state
	 *        Boolean : true - to check status checkbox
	 *        false - to uncheck status checkbox
	 * @author dheeraj.rajput
	 */
	public void filterByStatus(String status, boolean state)
	{
		expandFilter();
		if (isDisplayed(filter_Status, Speed.slow))
			findVisibleElement(filter_Status).click();
		By checkboxToClick = By.xpath("//*[@id='siteadmin_userfilterStatusListDivID']//*[normalize-space(text())='" + status.trim() + "']/preceding-sibling::*[@type='checkbox']");
		setSelection(checkboxToClick, state);
	}

	/**
	 * Filter by frequency
	 * 
	 * @param frequency
	 *        frequency name to select
	 * @param state
	 *        Boolean : true - to check frequency checkbox
	 *        false - to uncheck frequency checkbox
	 * @author dheeraj.rajput
	 */
	public void filterByFrequency(String frequency, boolean state)
	{
		expandFilter();
		if (isDisplayed(filter_Frequency, Speed.slow))
			findVisibleElement(filter_Frequency).click();
		By checkboxToClick = By.xpath("//*[@id='siteadmin_userfilterFrequencyListDivID']//*[normalize-space(text())='" + frequency + "']/preceding-sibling::*[@type='checkbox']");
		setSelection(checkboxToClick, state);
	}

	/**
	 * Clear filter
	 * 
	 * @author dheeraj.rajput
	 */
	public void clearFilter()
	{
		WebElement clearFilterLink = findVisibleElement(clearFilters);
		if (clearFilterLink.isEnabled())
			clearFilterLink.click();
	}

}
