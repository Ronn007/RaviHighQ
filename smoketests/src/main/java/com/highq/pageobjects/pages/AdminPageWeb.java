package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.CollaborateLabel.AdminOptions;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminBidderOrganisationsPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminQAPermissionsPage;
import com.highq.pageobjects.base.AdminQandAGroupsPage;

public class AdminPageWeb extends BannerPageWeb implements AdminPage
{

	String adminModules = ".//*[@id='leftPanel']";
	By siteSummaryLink = By.id("siteAdmin_module_leftTreeOption_summary");
	By showLeftPanelArrow = By.xpath("//*[contains(@class,'icon-chevron-right') and contains(@onclick,'showHideLeft()')]");
	By hideLeftPanelArrow = By.xpath("//*[contains(@class,'icon-chevron-left') and contains(@onclick,'showHideLeft()')]");

	/** Menu - > expand arrows */
	By siteSettingsExpandArrow = By.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTINGS
			+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By moduleSettingsExpandArrow = By
			.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTINGS
					+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By qandaExpandArrow = By.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_QANDA
			+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By userManagementExpandArrow = By
			.xpath("(//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT
					+ "'])[1]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By auditsAndReportsExpandArrow = By
			.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDITSANDREPORTS
					+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By reportsExpandArrow = By.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORTS
			+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");
	By auditsExpandArrow = By.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDITS
			+ "'])[last()]/preceding-sibling::*[contains(@class,'icon-chevron-right')]");

	/** Site Settings */
	By siteSettings = By
			.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTINGS + "'])[last()]");
	By generalLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_GENERAL + "'])[last()]");
	By modulesLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_MODULES + "'])[last()]");
	By siteNavigation = By.xpath("(//*[@id='general']//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_SITENAVIGATION_MODULES + "'])[last()]");
	By securityLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_SECURITY + "'])[last()]");
	By splashPageLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_SPLASHPAGE + "'])[last()]");
	By announcementsLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_ANNOUNCEMENTS + "'])[last()]");
	By termsConditionLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_TERMSANDCONDITIONS + "'])[last()]");
	By billingInfoLink = By.xpath("(//*[@id='general']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_SITESETTING_BILLINGINFORMATION + "'])[last()]");

	/** Module settings */
	By moduleSettings = By
			.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTINGS + "'])[last()]");

	By homeLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_HOME + "'])[last()]");
	By activityLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_ACTIVITY + "'])[last()]");
	By filesLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_FILES + "'])[last()]");
	By wikiLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_WIKI + "'])[last()]");
	By blogLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_BLOG + "'])[last()]");
	By tasksLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_TASKS + "'])[last()]");
	By eventsLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_EVENTS + "'])[last()]");
	By iSheetsLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_ISHEETS + "'])[last()]");
	By QnALink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='Q&A'])[last()]");
	By peopleLink = By.xpath("(//*[@id='modules']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_MODULESETTING_PEOPLE + "'])[last()]");

	/** Q & A */
	By QAndA = By.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_QANDA + "'])[last()]");
	By qAndAPermissionsLink = By.xpath("(//*[@id='qaManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_QANDASETTING_QANDAPERMISSION + "'])[last()]");

	/** User management */
	By userManagement = By
			.xpath("(//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT + "'])[1]");
	By userMngt_UsersLink = By.xpath("(//*[@id='userManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT_USERS + "'])[last()]");
	By userMngt_GroupsLink = By.xpath("(//*[@id='userManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT_GROUPS + "'])[last()]");
	By userMngt_EmailAlertsLink = By.xpath("(//*[@id='userManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT_EMAILALERTS + "'])[last()]");
	By userMngt_BidderOrganisationsLink = By.xpath("(//*[@id='userManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT_BIDDERORGANISATIONS + "'])[last()]");
	By userMngt_BidderTeamLink = By.xpath("(//*[@id='userManagement']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_USERMANAGEMENT_BIDDERTEAM + "'])[last()]");

	/** Audits and reports -> Reports */
	By auditsAndReports = By
			.xpath("(//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDITSANDREPORTS + "'])[1]");
	By reports = By.xpath("(//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORTS + "'])[1]");
	By audits = By.xpath("(//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDITS + "'])[1]");

	By reports_SummaryReportLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_SUMMARYREPORT + "'])[last()]");
	By reports_AccessByOrganisationLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_ACCESSBYORGANIZATION + "'])[last()]");
	By reports_AccessByUserLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_ACCESSBYUSER + "'])[last()]");
	By reports_MostPopularAccessLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_MOST_POPULARACCESS + "'])[last()]");
	By reports_SiteUsersLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_SITEUSERS + "'])[last()]");
	By reports_LoginsByOrganisationLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_LOGINSBYORGANIZATIONS + "'])[last()]");
	By reports_LoginsByUserLink = By.xpath("(//*[@id='reports']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_REPORT_LOGINSBYUSER + "'])[last()]");

	/** Audits and reports -> Audits */
	By audits_UserManagementLink = By.xpath("(//*[@id='audits']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDIT_USERMANAGEMENT + "'])[last()]");
	By audits_SiteManagementLink = By.xpath("(//*[@id='audits']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDIT_SITEMANAGEMENT + "'])[last()]");
	By auditsSiteManagementPage = By.id("siteAdmin_module_mainMiddlePanelDivID");
	By audits_ContentManagementLink = By.xpath("(//*[@id='audits']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDIT_CONTENTMANAGEMENT + "'])[last()]");
	By audits_LoginLink = By.xpath("(//*[@id='audits']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDIT_LOGIN + "'])[last()]");
	By audits_QandAManagementLink = By.xpath("(//*[@id='audits']//*[normalize-space(.)='"
			+ SiteAdminLabels.SITEADMIN_LEFTPANEL_AUDIT_QANDAMANAGEMENT + "'])[last()]");
	By advancedLink = By.xpath(adminModules + "//span[text()='Advanced']");

	By usersPermissionsLink = By.xpath(adminModules + "//span[text()='User permissions']");
	/************* Sub links of User permissions begins ********************/
	By usersLink = By.xpath(adminModules + "//span[text()='{0}']");
	By groupsLink = By.xpath(adminModules + "//span[text()='{0}']");
	By rolesLink = By.xpath(adminModules + "//span[text()='{0}']");
	/************* Sub links of User permissions ends ********************/

	By qAndALink = By.xpath(adminModules + "//span[text()='Q&A']");

	By reportingLink = By.xpath(adminModules + "//span[text()='Reporting']");
	/* ###################### Sub links of reporting begins ################### */

	By reportsLink = By.xpath(adminModules + "//span[text()='Reports']");

	/************* Sub links of reports begins ********************/
	By summaryReportsLink = By.xpath(adminModules + "//span[text()='Summary report']");
	By accessByOrganizationLink = By.xpath(adminModules + "//span[text()='Access by organisation']");
	By accessByUserLink = By.xpath(adminModules + "//span[text()='Access by user']");
	By mostPopularAccessLink = By.xpath(adminModules + "//span[text()='Most popular access']");
	By siteUsersLink = By.xpath(adminModules + "//span[text()='Site users']");
	By loginsByOrganizationLink = By.xpath(adminModules + "//span[text()='Logins by organisation']");
	By loginsByUserLink = By.xpath(adminModules + "//span[text()='Logins by user']");

	/************* Sub links of reports ends ********************/

	By auditsLink = By.xpath(adminModules + "//span[text()='Audits']");

	/************* Sub links of Audits begins ********************/
	By userManagementLink = By.xpath(adminModules + "//span[text()='User management']");
	By siteManagementLink = By.xpath(adminModules + "//span[text()='Site management']");
	By contentManagementLink = By.xpath(adminModules + "//span[text()='Content management']");
	By logintManagementLink = By.xpath(adminModules + "//span[text()='Login']");
	By qAndAManagementLink = By.xpath(adminModules + "//span[text()='Q&A management']");
	/************* Sub links of Audits ends ********************/

	/* ###################### Sub links of reporting ends ################### */

	By billingLink = By.xpath(adminModules + "//span[text()='Billing']");
	By confugureSiteStorageLink = By.xpath(adminModules + "//span[text()='Configure site storage']");

	By qAndAGroupsLink = By.xpath("(//*[@id='qaManagement']//*[normalize-space(.)='Q&A groups'])[last()]");

	By leftPanelTree = By.xpath("//*[@class='leftPanelSection']//*[@class='treeSearchSection siteAdminTree']");

	By siteAdmin_MiddlePanel = By.id("siteAdmin_module_mainMiddlePanelDivID");

	By siteOwnerEmail = By.id("siteOwnerEmail");

	public AdminPageWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Show admin left panel
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void showLeftPanel()
	{
		if (isDisplayed(showLeftPanelArrow, Speed.slow))
		{
			findVisibleElement(showLeftPanelArrow, Speed.slow).click();
		}
		moveToElement(leftPanelTree);
	}

	/**
	 * Hide admin left panel
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void hideLeftPanel()
	{
		if (isDisplayed(hideLeftPanelArrow))
		{
			findVisibleElement(hideLeftPanelArrow).click();
		}
	}

	/**
	 * Expand Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandSiteSettings()
	{
		showLeftPanel();
		if (isDisplayed(siteSettingsExpandArrow))
		{
			findVisibleElement(siteSettingsExpandArrow).click();
		}
	}

	/**
	 * Expand Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandModuleSettings()
	{
		showLeftPanel();
		if (isDisplayed(moduleSettingsExpandArrow))
		{
			findVisibleElement(moduleSettingsExpandArrow).click();
		}
	}

	/**
	 * Expand Q and A Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandQandA()
	{
		showLeftPanel();
		if (isDisplayed(qandaExpandArrow, Speed.slow))
		{
			findVisibleElement(qandaExpandArrow, Speed.slow).click();
		}
	}

	/**
	 * Expand User Management
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandUserManagement()
	{
		showLeftPanel();
		if (isDisplayed(userManagementExpandArrow))
		{
			findVisibleElement(userManagementExpandArrow).click();
		}
	}

	/**
	 * Expand Audit and Reports
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandAuditsAndReports()
	{
		showLeftPanel();
		if (isDisplayed(auditsAndReportsExpandArrow))
		{
			findVisibleElement(auditsAndReportsExpandArrow).click();
		}
	}

	/**
	 * Expand Reports
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandReports()
	{
		showLeftPanel();
		expandAuditsAndReports();
		if (isDisplayed(reportsExpandArrow))
		{
			findVisibleElement(reportsExpandArrow).click();
		}
	}

	/**
	 * Expand Audits
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandAudits()
	{
		showLeftPanel();
		expandAuditsAndReports();
		if (isDisplayed(auditsExpandArrow))
		{
			findVisibleElement(auditsExpandArrow).click();
		}
	}

	/**
	 * Click Site Summary
	 *
	 * @author dheeraj.rajput
	 * @return
	 */
	@Override
	public AdminSiteSummaryWeb clickSiteSummaryInLeftPanel()
	{
		showLeftPanel();
		findVisibleElement(siteSummaryLink).click();
		return new AdminSiteSummaryWeb(driver);
	}

	/**
	 * Click General in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminGeneralWeb clickGeneralInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(generalLink).click();
		return new AdminGeneralWeb(driver);
	}

	/**
	 * Click Modules in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public ModulesPageWeb clickModulesInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(modulesLink, Speed.slow).click();
		return new ModulesPageWeb(driver);
	}

	/**
	 * Click Security in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminSecurityWeb clickSecurityInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(securityLink).click();
		return new AdminSecurityWeb(driver);
	}

	/**
	 * Click Splash Page in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminSplashWeb clickSplashPageInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(splashPageLink).click();
		return new AdminSplashWeb(driver);
	}

	/**
	 * Click Announcements in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminAnnouncementWeb clickAnnouncementInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(announcementsLink).click();
		return new AdminAnnouncementWeb(driver);
	}

	/**
	 * Click Terms and Conditions in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminTermsAndConditionsWeb clickTermsAndConditionsInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(termsConditionLink).click();
		return new AdminTermsAndConditionsWeb(driver);
	}

	/**
	 * Click Billing information in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminBillingWeb clickBillingInformationInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(billingInfoLink).click();
		return new AdminBillingWeb(driver);
	}

	/**
	 * Click Home in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminHomeWeb clickHomeInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(homeLink).click();
		return new AdminHomeWeb(driver);
	}

	/**
	 * Click Activity in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminActivityWeb clickActivityInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(activityLink, Speed.slow).click();
		return new AdminActivityWeb(driver);
	}

	/**
	 * Click Files in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminFilesWeb clickFilesInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(filesLink).click();
		findVisibleElement(siteAdmin_MiddlePanel);
		return new AdminFilesWeb(driver);
	}

	/**
	 * Click Wiki in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminWikiWeb clickWikiInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(wikiLink).click();
		return new AdminWikiWeb(driver);
	}

	/**
	 * Click Blog in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminBlogWeb clickOnBlogInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(blogLink).click();
		return new AdminBlogWeb(driver);
	}

	/**
	 * Click Tasks in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminTaskPageWeb clickTasksInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(tasksLink).click();
		return new AdminTaskPageWeb(driver);
	}

	/**
	 * Click Events in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminEventWeb clickEventsInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(eventsLink).click();
		return new AdminEventWeb(driver);
	}

	/**
	 * Click iSheets in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminIsheetWeb clickiSheetsInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(iSheetsLink).click();
		return new AdminIsheetWeb(driver);
	}

	/**
	 * Click People in Module Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminPeopleWeb clickPeopleInLeftPanel()
	{
		expandModuleSettings();
		findVisibleElement(peopleLink).click();
		return new AdminPeopleWeb(driver);
	}

	/**
	 * Click Q&A Permissions in Q&A
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminQAPermissionsPage clickOnQandAPermissionsInLeftPanel()
	{
		expandQandA();
		findVisibleElement(qAndAPermissionsLink, Speed.slow).click();
		return new AdminQAPermissionsWeb(driver);
	}

	/**
	 * Click Users in User Management
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AddUserWeb clickUsersInLeftPanel()
	{
		expandUserManagement();
		findVisibleElement(userMngt_UsersLink, Speed.slow).click();

		return new AddUserWeb(driver);
	}

	/**
	 * Click Groups in User Management
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminUserGroupsWeb clickGroupsInLeftPanel()
	{
		expandUserManagement();
		findVisibleElement(userMngt_GroupsLink, Speed.slow).click();
		return new AdminUserGroupsWeb(driver);
	}

	/**
	 * Click Email Alerts in User Management
	 */
	@Override
	public void clickEmailAlertsInLeftPanel()
	{
		expandUserManagement();
		findVisibleElement(userMngt_EmailAlertsLink).click();
	}

	/**
	 * Click Summary Report in Reports
	 */
	@Override
	public void clickSummaryReportsInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_SummaryReportLink).click();
	}

	/**
	 * Click Access by organisation in Reports
	 */
	@Override
	public void clickAccessByOrganisationInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_AccessByOrganisationLink).click();
	}

	/**
	 * Click Access by user in Reports
	 */
	@Override
	public void clickAccessByUserInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_AccessByUserLink).click();
	}

	/**
	 * Click Most Popular Access in Reports
	 */
	@Override
	public void clickMostPopularAccessInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_MostPopularAccessLink).click();
	}

	/**
	 * Click Site Users in Reports
	 */
	@Override
	public void clickSiteUsersInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_SiteUsersLink).click();
	}

	/**
	 * Click Logins by organisation in Reports
	 */
	@Override
	public void clickLoginsByOrganisationInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_LoginsByOrganisationLink).click();
	}

	/**
	 * Click Logins by user in Reports
	 */
	@Override
	public void clickLoginsByUserInLeftPanel()
	{
		expandReports();
		findVisibleElement(reports_LoginsByUserLink).click();
	}

	/**
	 * Click User Management in Audits
	 */
	@Override
	public void clickUserManagementInLeftPanel()
	{
		expandAudits();
		findVisibleElement(audits_UserManagementLink).click();
	}

	/**
	 * Click Site Management in Audits
	 *
	 * @return
	 */
	@Override
	public AdminAuditsSiteManagementWeb clickSiteManagementInLeftPanel()
	{
		expandAudits();
		findVisibleElement(audits_SiteManagementLink).click();
		findPresentElement(auditsSiteManagementPage);
		return new AdminAuditsSiteManagementWeb(driver);
	}

	/**
	 * Click Content Management in Audits
	 */
	@Override
	public AdminAuditsContentManagementWeb clickContentManagementInLeftPanel()
	{
		expandAudits();
		findVisibleElement(audits_ContentManagementLink).click();
		return new AdminAuditsContentManagementWeb(driver);
	}

	/**
	 * Click Login in Audits
	 */
	@Override
	public void clickLoginInLeftPanel()
	{
		expandAudits();
		findVisibleElement(audits_LoginLink).click();
	}

	/**
	 * Click Q&A Management in Audits
	 */
	@Override
	public void clickOnQandManagementInLeftPanel()
	{
		expandAudits();
		findVisibleElement(audits_QandAManagementLink).click();
	}

	@Override
	public AdminAdvancedWeb clickOnAdvancedInLeftPanel()
	{
		WebElement addSite = findVisibleElement(advancedLink);

		// if (addSite.isDisplayed())
		// {
		addSite.click();
		// }

		return new AdminAdvancedWeb(driver);
	}

	@Override
	public ModulesPageWeb clickOnModulesInLeftPanel()
	{
		return clickModulesInLeftPanel();
	}

	@Override
	public String replaceTextInXpath(String xpath, String valueToReplace)
	{
		String menuItem;
		if (valueToReplace.contains("_"))
		{
			menuItem = valueToReplace.trim().replaceAll("_", " ");
		}
		else
		{
			menuItem = valueToReplace.trim();
		}
		xpath = xpath.replace("{0}", menuItem);
		return xpath;
	}

	@Override
	public void clickOnSubModuleOfQandA(By childLink)
	{
		WebElement userPermissionsParent = findVisibleElement(qAndALink);
		// if (userPermissionsParent.isDisplayed())
		// {
		userPermissionsParent.click();
		// }
		WebElement userPermissionsChild = findVisibleElement(childLink);
		// if (userPermissionsChild.isDisplayed())
		// {
		userPermissionsChild.click();
		// }
	}

	@Override
	public void clickOnSubModuleOfReporting(By parentLink, By childLink)
	{

		WebElement userPermissionsRoot = findVisibleElement(reportingLink);
		// if (userPermissionsRoot.isDisplayed())
		// {
		userPermissionsRoot.click();
		// }

		WebElement userPermissionsParent = findVisibleElement(parentLink);
		// if (userPermissionsParent.isDisplayed())
		// {
		userPermissionsParent.click();
		// }

		WebElement userPermissionsChild = findVisibleElement(childLink);
		// if (userPermissionsChild.isDisplayed())
		// {
		userPermissionsChild.click();
		// }
	}

	@Override
	public AdminTaskPageWeb clickOnTaskInLeftPanel()
	{
		return clickTasksInLeftPanel();
	}

	@Override
	public TasksWeb selectUserOption(String option)
	{
		WebElement userAvatar = findVisibleElement(userAvtar);
		// if (userAvatar.isDisplayed())
		// {
		userAvatar.click();
		// }
		String menuXpath = replaceTextInUserMenuXpath(option);
		WebElement userMenuItem = findVisibleElement(By.xpath(menuXpath));
		// if (userMenuItem.isDisplayed())
		// {
		userMenuItem.click();
		// }
		return new TasksWeb(driver);
	}

	@Override
	public AdminWikiWeb clickOnWikiInLeftPanel()
	{
		return clickWikiInLeftPanel();
	}

	@Override
	public AdminUserGroupsWeb selectGroupFromUserPermissions()
	{

		WebElement userPermissionsParent = findPresentElement(usersPermissionsLink);
		if (userPermissionsParent.isDisplayed())
		{
			userPermissionsParent.click();
		}
		String childXpath = adminModules + "//span[text()='{0}']";
		By childLink = By.xpath(replaceTextInXpath(childXpath, "Groups"));
		WebElement userPermissionsChild = findVisibleElement(childLink);
		if (userPermissionsChild.isDisplayed())
		{
			userPermissionsChild.click();
		}
		return new AdminUserGroupsWeb(driver);
	}

	@Override
	public void clickOnSubModuleOfUserPermissions(AdminOptions menuOption)
	{
		WebElement userPermissionsParent = findClickableElement(usersPermissionsLink);
		if (userPermissionsParent.isDisplayed())
		{
			userPermissionsParent.click();
		}
		// need to add wait to ensure visibility of child link
		String childXpath = adminModules + "//span[text()='{0}']";
		By childLink = By.xpath(replaceTextInXpath(childXpath, menuOption.toString().replace("_", " ")));
		WebElement userPermissionsChild = findClickableElement(childLink);
		if (userPermissionsChild.isDisplayed())
		{
			userPermissionsChild.click();
		}
	}

	@Override
	public AddUserWeb selectUserFromUserPermissions()
	{

		WebElement userPermissionsParent = findPresentElement(usersPermissionsLink);
		if (userPermissionsParent.isDisplayed())
		{
			userPermissionsParent.click();
		}
		String childXpath = adminModules + "//span[text()='{0}']";
		By childLink = By.xpath(replaceTextInXpath(childXpath, "Users"));
		WebElement userPermissionsChild = findVisibleElement(childLink);
		if (userPermissionsChild.isDisplayed())
		{
			userPermissionsChild.click();
		}
		return new AddUserWeb(driver);
	}

	@Override
	public UserRolesWeb selectRolesFromUserPermissions()
	{

		WebElement userPermissionsParent = findPresentElement(usersPermissionsLink);
		if (userPermissionsParent.isDisplayed())
		{
			userPermissionsParent.click();
		}
		String childXpath = adminModules + "//span[text()='{0}']";
		By childLink = By.xpath(replaceTextInXpath(childXpath, "Roles"));
		WebElement userPermissionsChild = findVisibleElement(childLink);
		if (userPermissionsChild.isDisplayed())
		{
			userPermissionsChild.click();
		}
		return new UserRolesWeb(driver);
	}

	@Override
	public AdminIsheetsWeb clickIsheetsInLeftPanel()
	{
		WebElement addSite = findVisibleElement(iSheetsLink);

		// if (addSite.isDisplayed())
		// {
		addSite.click();
		// }

		return new AdminIsheetsWeb(driver);
	}

	@Override
	public AdminFilesWeb clickOnFilesInLeftPanel()
	{
		return clickFilesInLeftPanel();
	}

	/**
	 * Click Q&A Groups link
	 *
	 * @return
	 */
	@Override
	public AdminQandAGroupsPage clickOnQandAGroupsInLeftPannel()
	{
		expandQandA();
		findVisibleElement(qAndAGroupsLink, Speed.slow).click();
		return new AdminQandAGroupsWeb(driver);
	}

	/**
	 * Click Modules in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminBidderOrganisationsPage clickBidderOrganisationsInLeftPanel()
	{
		expandUserManagement();
		findVisibleElement(userMngt_BidderOrganisationsLink, Speed.slow).click();
		return new AdminBidderOrganisationsWeb(driver);
	}

	/**
	 * Click Modules in Site Settings
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public AdminBidderTeamWeb clickBidderTeamInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(userMngt_BidderTeamLink, Speed.slow).click();
		return new AdminBidderTeamWeb(driver);
	}

	@Override
	public SiteNavigationWeb clickSiteNavigationInLeftPanel()
	{
		expandSiteSettings();
		findVisibleElement(siteNavigation, Speed.slow).click();
		return new SiteNavigationWeb(driver);
	}

	/**
	 * @author hetal.thakkar
	 *         Verify default alert for new site user
	 * @created on 09/05/2018
	 */
	public boolean verifyDefaultAlertForNewSiteUser(String option)
	{
		return isDisplayed(By.xpath("//*[@data-id='siteAdmin_emailAlerts_Defaultalertselectpicker']/span[normalize-space(text())='" + option + "']"));
	}

	/**
	 * @author ankit.motaval
	 *         verify Site owner Name
	 * @created on 08/05/2018
	 */
	@Override
	public boolean verifySiteOwnerName(String ownerName)
	{
		return isDisplayed(By.xpath(".//*[@class='usercardLink']//strong[normalize-space(.)='" + ownerName.trim() + "']"));
	}

	/**
	 * @author ankit.motaval
	 *         verify Site owner Email
	 * @created on 08/05/2018
	 */
	@Override
	public boolean verifySiteOwnerEmail(String ownerEmail)
	{
		String getText = findVisibleElement(siteOwnerEmail, Speed.slow).getText();
		return getText.trim().equals(ownerEmail.trim());
	}

}
