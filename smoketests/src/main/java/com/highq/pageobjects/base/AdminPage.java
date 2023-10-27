package com.highq.pageobjects.base;

import org.openqa.selenium.By;
import com.highq.base.CollaborateLabel.AdminOptions;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminActivityWeb;
import com.highq.pageobjects.pages.AdminAdvancedWeb;
import com.highq.pageobjects.pages.AdminAnnouncementWeb;
import com.highq.pageobjects.pages.AdminAuditsContentManagementWeb;
import com.highq.pageobjects.pages.AdminAuditsSiteManagementWeb;
import com.highq.pageobjects.pages.AdminBidderTeamWeb;
import com.highq.pageobjects.pages.AdminBillingWeb;
import com.highq.pageobjects.pages.AdminBlogWeb;
import com.highq.pageobjects.pages.AdminEventWeb;
import com.highq.pageobjects.pages.AdminFilesWeb;
import com.highq.pageobjects.pages.AdminGeneralWeb;
import com.highq.pageobjects.pages.AdminHomeWeb;
import com.highq.pageobjects.pages.AdminIsheetWeb;
import com.highq.pageobjects.pages.AdminIsheetsWeb;
import com.highq.pageobjects.pages.AdminPeopleWeb;
import com.highq.pageobjects.pages.AdminSecurityWeb;
import com.highq.pageobjects.pages.AdminSiteSummaryWeb;
import com.highq.pageobjects.pages.AdminSplashWeb;
import com.highq.pageobjects.pages.AdminTaskPageWeb;
import com.highq.pageobjects.pages.AdminTermsAndConditionsWeb;
import com.highq.pageobjects.pages.AdminUserGroupsWeb;
import com.highq.pageobjects.pages.AdminWikiWeb;
import com.highq.pageobjects.pages.ModulesPageWeb;
import com.highq.pageobjects.pages.SiteNavigationWeb;
import com.highq.pageobjects.pages.TasksWeb;
import com.highq.pageobjects.pages.UserRolesWeb;

public interface AdminPage extends BannerPage
{
	public void showLeftPanel();

	public void hideLeftPanel();

	public void expandSiteSettings();

	public void expandModuleSettings();

	public void expandQandA();

	public void expandUserManagement();

	public void expandAuditsAndReports();

	public void expandReports();

	public void expandAudits();

	public AdminSiteSummaryWeb clickSiteSummaryInLeftPanel();

	public AdminGeneralWeb clickGeneralInLeftPanel();

	public ModulesPageWeb clickModulesInLeftPanel();

	public AdminSecurityWeb clickSecurityInLeftPanel();

	public AdminSplashWeb clickSplashPageInLeftPanel();

	public AdminAnnouncementWeb clickAnnouncementInLeftPanel();

	public AdminTermsAndConditionsWeb clickTermsAndConditionsInLeftPanel();

	public AdminBillingWeb clickBillingInformationInLeftPanel();

	public AdminHomeWeb clickHomeInLeftPanel();

	public AdminActivityWeb clickActivityInLeftPanel();

	public AdminFilesWeb clickFilesInLeftPanel();

	public AdminWikiWeb clickWikiInLeftPanel();

	public AdminBlogWeb clickOnBlogInLeftPanel();

	public AdminTaskPageWeb clickTasksInLeftPanel();

	public AdminEventWeb clickEventsInLeftPanel();

	public AdminIsheetWeb clickiSheetsInLeftPanel();

	public AdminPeopleWeb clickPeopleInLeftPanel();

	public AdminQAPermissionsPage clickOnQandAPermissionsInLeftPanel();

	public AddUserWeb clickUsersInLeftPanel();

	public AdminUserGroupsWeb clickGroupsInLeftPanel();

	public void clickEmailAlertsInLeftPanel();

	public void clickSummaryReportsInLeftPanel();

	public void clickAccessByOrganisationInLeftPanel();

	public void clickAccessByUserInLeftPanel();

	public void clickMostPopularAccessInLeftPanel();

	public void clickSiteUsersInLeftPanel();

	public void clickLoginsByOrganisationInLeftPanel();

	public void clickLoginsByUserInLeftPanel();

	public void clickUserManagementInLeftPanel();

	public AdminAuditsSiteManagementWeb clickSiteManagementInLeftPanel();

	public AdminAuditsContentManagementWeb clickContentManagementInLeftPanel();

	public void clickLoginInLeftPanel();

	public void clickOnQandManagementInLeftPanel();

	public ModulesPageWeb clickOnModulesInLeftPanel();

	public String replaceTextInXpath(String xpath, String valueToReplace);

	public void clickOnSubModuleOfQandA(By childLink);

	public void clickOnSubModuleOfReporting(By parentLink, By childLink);

	public AdminTaskPageWeb clickOnTaskInLeftPanel();

	public AdminWikiWeb clickOnWikiInLeftPanel();

	public TasksWeb selectUserOption(String option);

	public AdminUserGroupsWeb selectGroupFromUserPermissions();

	public void clickOnSubModuleOfUserPermissions(AdminOptions menuOption);

	public AddUserWeb selectUserFromUserPermissions();

	public UserRolesWeb selectRolesFromUserPermissions();

	public AdminAdvancedWeb clickOnAdvancedInLeftPanel();

	public AdminIsheetsWeb clickIsheetsInLeftPanel();

	public AdminFilesWeb clickOnFilesInLeftPanel();

	public AdminQandAGroupsPage clickOnQandAGroupsInLeftPannel();

	public AdminBidderOrganisationsPage clickBidderOrganisationsInLeftPanel();

	public AdminBidderTeamWeb clickBidderTeamInLeftPanel();

	public SiteNavigationWeb clickSiteNavigationInLeftPanel();

	public boolean verifyDefaultAlertForNewSiteUser(String option);

	public boolean verifySiteOwnerName(String ownerName);

	public boolean verifySiteOwnerEmail(String ownerEmail);

}
