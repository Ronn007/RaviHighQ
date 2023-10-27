package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminPage;

public class SystemAdminWeb extends BannerPageWeb implements SystemAdminPage
{
	By leftPanelUserAdminOption = By.xpath(".//*[@id='UserAdmin']");
	By leftPanelOrgAdminOption = By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_ORGADMIN + "']");
	By leftPanelSystemSettingsOption = By.xpath(".//*[@id='leftPanel']//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_SYSTEMSETTINGS + "']");
	By leftPanelGlobalNavigationOption = By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_GLOBALNAVIGATION + "']");
	By leftPanelFileOrFileTypesOption = By.xpath(".//*[@id='leftPanel']//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_FILEORFILETYPES + "']");
	By leftPanelSystemVocabularyOption = By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.SYSTEM_VOCABULARY + "']");
	By leftPanelExceptionDomainsOption = By.xpath(".//*[@id='ExceptionDomains']");
	By leftPanelSystemAuditsAndReports = By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.SYSTEM_AUDITS_AND_REPORTS + "']");
	By leftPanelIsheetAdmin = By.xpath(".//*[@id='isheet']//*[@class='pull-left' and normalize-space()='" + SystemAdminLabels.ISHEET_ADMIN + "']");
	By thirdPartyServicesContainer = By.id("selectedThirdPartyServicesListContainer");

	public SystemAdminWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public SearchUserPageWeb gotoUserAdmin()
	{
		WebElement leftPanelUserAdminOptionLink = findClickableElement(leftPanelUserAdminOption);
		if (isDisplayed(leftPanelUserAdminOption))
		{
			leftPanelUserAdminOptionLink.click();
		}
		return new SearchUserPageWeb(driver);
	}

	@Override
	public OrgAdminWeb gotoOrgAdmin()
	{
		WebElement leftPanelUserAdminOptionLink = findClickableElement(leftPanelOrgAdminOption);
		if (isDisplayed(leftPanelOrgAdminOption))
		{
			leftPanelUserAdminOptionLink.click();
		}
		return new OrgAdminWeb(driver);
	}

	@Override
	public GlobalNavigationAdminWeb gotoGlobalNavigation() throws InterruptedException
	{
		WebElement leftPanelGlobalNavigationEle = findClickableElement(leftPanelGlobalNavigationOption);
		if (isDisplayed(leftPanelGlobalNavigationOption))
		{
			leftPanelGlobalNavigationEle.click();
		}
		return new GlobalNavigationAdminWeb(driver);
	}

	@Override
	public SystemAdminSystemSettingsWeb gotoSystemSettings()
	{
		WebElement leftPanelUserAdminOptionLink = findVisibleElement(leftPanelSystemSettingsOption, Speed.slow);
		leftPanelUserAdminOptionLink.click();
		return new SystemAdminSystemSettingsWeb(driver);
	}

	@Override
	public SystemAdminFileOrFileTypesWeb gotoFileOrFileTypes()
	{
		WebElement elem = findClickableElement(leftPanelFileOrFileTypesOption);
		if (isDisplayed(leftPanelFileOrFileTypesOption))
		{
			elem.click();
		}
		return new SystemAdminFileOrFileTypesWeb(driver);
	}

	@Override
	public SystemAdminVocabularyWeb gotoSystemVocabularyPage()
	{
		WebElement leftPanelUserAdminOptionLink = findVisibleElement(leftPanelSystemVocabularyOption);
		leftPanelUserAdminOptionLink.click();
		return new SystemAdminVocabularyWeb(driver);
	}

	@Override
	public SystemAdminSystemVocabularyWeb gotoSystemVocabulary()
	{
		WebElement ele = findVisibleElement(leftPanelSystemVocabularyOption);
		ele.click();
		return new SystemAdminSystemVocabularyWeb(driver);
	}

	@Override
	public SystemAdminExceptionDomainsWeb gotoExceptionDomains()
	{
		WebElement leftPanelUserAdminOptionLink = findVisibleElement(leftPanelExceptionDomainsOption);
		leftPanelUserAdminOptionLink.click();
		return new SystemAdminExceptionDomainsWeb(driver);
	}

	@Override
	public boolean verifySystemAdminPage()
	{
		return isDisplayed(leftPanelOrgAdminOption);
	}

	@Override
	public SystemAuditsReportsWeb gotoSystemAuditsAndReports()
	{
		WebElement ele = findVisibleElement(leftPanelSystemAuditsAndReports);
		ele.click();
		return new SystemAuditsReportsWeb(driver);
	}
	
	/**
	 * this method is used to click on iSheet admin label on System admin left panel
	 * 
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @return
	 */
	public SystemAdminIsheetAdminWeb gotoIsheetAdmin()
	{
		findClickableElement(leftPanelIsheetAdmin, Speed.slow).click();
		return new SystemAdminIsheetAdminWeb(driver);
	}

}
