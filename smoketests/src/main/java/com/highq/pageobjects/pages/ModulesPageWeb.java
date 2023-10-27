package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.ModulesPage;

public class ModulesPageWeb extends BannerPageWeb implements ModulesPage
{

	By homeCheckBox = By.id("SITE_MODULE_HOME");
	By activityCheckBox = By.id("SITE_MODULE_ACTIVITY");
	By filesCheckBox = By.id("SITE_MODULE_DOCUMENTS");
	By wikiCheckBox = By.id("SITE_MODULE_WIKI");
	By blogsCheckBox = By.id("SITE_MODULE_BLOGS");
	By tasksCheckBox = By.id("SITE_MODULE_TASKS");
	By eventsCheckBox = By.id("SITE_MODULE_EVENTS");
	By qaCheckBox = By.id("SITE_MODULE_QA");
	By iSheetsCheckBox = By.id("SITE_MODULE_ISHEET");
	By peopleCheckBox = By.id("SITE_MODULE_PEOPLE");

	By siteLandingPageLable = By.xpath("//label[contains(text(),'" + SiteAdminLabels.SITEADMIN_MODULESPAGE_SITELANDINGPAGE + "')]");
	By siteLandingPageDropDown = By.xpath("//*[@data-id ='cmbSiteHomePage']");
	By siteLandingPageDropDownComboBox = By
			.xpath("//*[@data-id='cmbSiteHomePage']/following-sibling::*[@role='combobox']");
	String siteLandingPageDropDown_PageList = "//*[@data-id='cmbSiteHomePage']/following-sibling::*[@role='combobox']//li";

	By saveButtonLink = By.id("saveSitelandingPagebtnBottom");
	By resetDefaultName = By.xpath(".//*[@id='system_locale_translate_modal_reset']");
	By save = By.xpath(".//*[@id='system_locale_translate_modal_add']");

	By SaveAnimatedMessage = By.xpath(".//*[@id='animatedMessageBox' and contains(@style,'display: none;')]");

	By modalContent = By.xpath("(//*[@class='modal-content'])[last()]");
	By moduleHeader = By.xpath("//h3[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESPAGE_MODULEHEADER + "']");

	public ModulesPageWeb(WebDriver driver)
	{
		// super(driver);
		this.driver = driver;

	}

	/**
	 * @modified by vivek mishra
	 * @modified on 17/03/2018
	 */

	@Override
	public void enableAllModules() throws InterruptedException
	{
		By[] modules = {homeCheckBox, activityCheckBox, filesCheckBox, wikiCheckBox, blogsCheckBox, tasksCheckBox, eventsCheckBox, qaCheckBox, iSheetsCheckBox, peopleCheckBox};
		findVisibleElement(moduleHeader, Speed.slow);
		for (int i = 0; i < modules.length; i++)
		{
			// WebElement moreOptionselement = findVisibleElement(modules[i]);
			if (isDisplayed(modules[i]))
			{
				setSelection(modules[i], true);// moreOptionselement.click();
			}
		}
		clickOnSaveButton();
	}

	@Override
	public void enableSpecificModule(By moduleCheckBox, boolean isEnable)
	{
		WebElement moreOptionselement = findVisibleElement(moduleCheckBox);
		if ((moreOptionselement.isDisplayed() && !(moreOptionselement.isSelected())) && isEnable)
		{
			moreOptionselement.click();
		}
		else if ((moreOptionselement.isDisplayed() && (moreOptionselement.isSelected())) && !isEnable)
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println(moduleCheckBox.toString() + " = is not displayed");
		}

	}

	@Override
	public void enableSpecificModules(boolean isEnable, String... moduleName)
	{
		for (int i = 0; i < moduleName.length; i++)
		{
			String lowerCase = moduleName[i].toLowerCase();

			if (SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME.toLowerCase().equals(lowerCase))
			{
				setSelection(homeCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY.toLowerCase().equals(lowerCase))
			{
				setSelection(activityCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_FILES.toLowerCase().equals(lowerCase))
			{
				setSelection(filesCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI.toLowerCase().equals(lowerCase))
			{
				setSelection(wikiCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG.toLowerCase().equals(lowerCase))
			{
				setSelection(blogsCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS.toLowerCase().equals(lowerCase))
			{
				setSelection(tasksCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS.toLowerCase().equals(lowerCase))
			{
				setSelection(eventsCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_QANDA.toLowerCase().equals(lowerCase))
			{
				setSelection(qaCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS.toLowerCase().equals(lowerCase))
			{
				setSelection(iSheetsCheckBox, isEnable);
			}
			else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_PEOPLE.toLowerCase().equals(lowerCase))
			{
				setSelection(peopleCheckBox, isEnable);
			}
			else
			{
			}
		}
	}

	@Override
	public void setSelectDefaultLandingPage(String pageName)
	{
		findVisibleElement(siteLandingPageDropDown).click();
		findPresentElement(siteLandingPageDropDownComboBox);
		WebElement elem = findVisibleElement(By.xpath(siteLandingPageDropDown_PageList + "//*[normalize-space(text())='" + pageName.trim() + "']"));
		elem.click();
	}

	/**
	 * modified by vivek mishra
	 *
	 * @modified on 27/03/2018
	 */
	@Override
	public AdminPageWeb clickOnSaveButton()
	{
		WebElement saveButton = findVisibleElement(saveButtonLink, Speed.slow);
		saveButton.click();
		return new AdminPageWeb(driver);
	}

	@Override
	public void enableSpecificModules(String... moduleName)
	{
		ArrayList<String> modulesList = new ArrayList<>(Arrays.asList(moduleName));

		for (int i = 0; i < modules.length; i++)
		{
			if (modulesList.contains(modules[i].toLowerCase()))
			{
				enableSpecificModules(true, modules[i]);
			}
			else
			{
				enableSpecificModules(false, modules[i]);
			}
		}
	}

	/**
	 * rename a Specific Module
	 *
	 * @param rename
	 * @param moduleName
	 * @author krishna.bhadani
	 */
	@Override
	public void renameSpecificModules(String rename, String moduleName)
	{
		if (SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_FILES.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_FILES);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_QANDA.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_QANDA);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS);
			rename(rename, moduleName);
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESPAGE_PEOPLE.equals(moduleName))
		{
			clickOnRename(SiteAdminLabels.SITEADMIN_MODULESPAGE_PEOPLE);
			rename(rename, moduleName);
		}
		else
		{
			System.out.println("module is not displayed");
		}

	}

	/**
	 * @param moduleName
	 */
	public void clickOnRename(String moduleName)
	{
		findVisibleElement(By.xpath("//*[@id='iterateElementInsideDiv']//*[normalize-space(text())='" + moduleName + "']/ancestor::div[1]/preceding-sibling::*/*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_RENAME + "']")).click();

	}

	/**
	 * @param rename
	 * @param module
	 */
	public void rename(String rename, String module)
	{
		findVisibleElement(By.xpath(".//*[contains(@id,'systemLocal')]//input[@value='" + module.trim() + "']")).clear();
		findVisibleElement(By.xpath(".//*[contains(@id,'systemLocal')]//input[@value='" + module.trim() + "']")).sendKeys(rename);
		findVisibleElement(By.xpath(".//*[@id='system_locale_translate_modal_add']")).click();
	}

	/**
	 * Reset Default Name In Specific Modules
	 *
	 * @param changedModuleName
	 * @author krishna.bhadani
	 */
	@Override
	public void resetDefaultNameSpecificModules(String changedModuleName)
	{
		findVisibleElement(By.xpath(".//*[@id='iterateElementInsideDiv']//li"), Speed.slow);
		findVisibleElement(By.xpath("//*[@id='iterateElementInsideDiv']//*[normalize-space(text())='" + changedModuleName.trim() + "']/ancestor::div[1]/preceding-sibling::*/*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_RENAME + "']")).click();
		resetdefault();

	}

	/**
	 * Click On Reset Default
	 *
	 * @author krishna.bhadani
	 */
	public void resetdefault()
	{
		findVisibleElement(resetDefaultName, Speed.slow).click();
		findVisibleElement(save, Speed.slow).click();
	}

	public boolean varifyModuleTitleName()
	{
		return isDisplayed(moduleHeader);
	}

	public boolean varifyModulPageText(String message)
	{
		if (isDisplayed(By.xpath(".//*[normalize-space(.)='" + message + "']")))
		{
			return true;
		}
		return false;
	}

	public boolean varifyAllModuleWithCheckbox()
	{

		By[] modules = {homeCheckBox, activityCheckBox, filesCheckBox, wikiCheckBox, blogsCheckBox, tasksCheckBox,
				eventsCheckBox, qaCheckBox, iSheetsCheckBox, peopleCheckBox};
		findVisibleElement(moduleHeader, Speed.slow);
		int count = 0;
		for (int i = 0; i < modules.length; i++)
		{

			if (isDisplayed(modules[i]))
			{
				count++;
			}
		}
		if (count == 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Ravi Pandit
	 * varifyAllModuleinSiteLandingPage
	 * Verify All Modules is Display in Site Landing Page Dropdown
	 *
	 * @return
	 */
	public boolean varifyAllModuleinSiteLandingPage()
	{
		// ui.text.home=Home
		findVisibleElement(siteLandingPageDropDown).click();
		findPresentElement(siteLandingPageDropDownComboBox);
		String modulesName[] = {SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME, SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY,
				"Files (root folder)", "Files (recent activity)", "Files (advanced search)", SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI, SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG,
				SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS, SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS, SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS};

		int count = 0;
		for (int i = 0; i < modulesName.length; i++)
		{
			if (isDisplayed(By.xpath(siteLandingPageDropDown_PageList + "//*[normalize-space(text())='" + modulesName[i] + "']")))
			{
				count++;
			}
		}
		if (count == 10)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
}
