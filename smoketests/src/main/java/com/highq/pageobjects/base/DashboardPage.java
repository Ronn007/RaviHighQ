package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.EventsWeb;

public interface DashboardPage extends BannerPage
{
	public void clickOnCreateSite() throws InterruptedException;

	public void enterSiteName(String siteName);

	public AdminPageWeb clickOnSaveToCreateSite();

	public BannerPageWeb searchSite(String siteName);

	public BannerPageWeb searchSite(Map<String, String> siteData) throws InterruptedException;

	@Override
	public String getRandomString();

	@Override
	public void clickOnUserAvtar();

	public void addSiteSetSiteTemplate(String templateName) throws InterruptedException;

	public void addSiteSetDescription(String description) throws InterruptedException;

	public void addSiteSetLandingPage(String landingPage) throws InterruptedException;

	public void addSiteSetStartDate(String startDate) throws InterruptedException;

	public void addSiteEndDate(String endDate) throws InterruptedException;

	public void addSiteSetSiteType(String siteType) throws InterruptedException;

	public void addSiteSetClientNumber(String clientNumber) throws InterruptedException;

	public void addSiteSetMatterNumber(String matterNumber) throws InterruptedException;

	public void setSiteCategory(String category) throws InterruptedException;

	public void addSiteUploadSiteLogo(String logo) throws InterruptedException;

	public void addSiteSetAdminNotes(String adminNotes) throws InterruptedException;

	public void clickOnModuleDropDown();

	public void addSiteEnableModules(List<String> modules) throws InterruptedException;

	public void addSiteEnableAllSiteModules() throws InterruptedException;

	public boolean verifyAddSiteModalOpened();

	public boolean verifySelectedModulesOnAddSite() throws InterruptedException;

	public void clickOnInstanceName();

	public void selectOptionFromSiteDropDown(String option);

	public void makeSiteFavouriteInSiteDropDown(String siteName, boolean value);

	public boolean verifyMySite();

	public boolean verifyAllSiteAndTemplates();

	public boolean verifyTabsOfRecentActivity(String tab);

	public boolean verifyRecentTabSiteList();

	public boolean verifyFavouritesTabSiteList();

	public void clickOnMoreLeftPanel();

	public boolean verifyCategoriesTabSiteList();

	public boolean verifyAddSiteLink();

	public boolean verifySearchSiteIcon();

	public boolean verifySharedFilesTab();

	public boolean verifySeeAllFilesLink();

	public boolean verifySendAFileButton();

	public boolean verifyPersonalTaskTab();

	public boolean verifyAssignToMeTaskTab();

	public boolean verifyAddTaskIcon();

	public boolean verifyReceivedFilesTab();

	public boolean verifyMySiteLink();

	public boolean verifyApprovalWorkflow();

	public boolean verifyGlobalNavigationMenuOption(String option);

	public void clickOnMenuItemOnGlobalNavigation(String option);

	public boolean verifyGlobalNavigationContainerMenuItem(String containerName, String menuItem);

	public void cilckOnUserRecentActivity(String username);

	public boolean verifyUserTitleInCard(String username);

	public boolean verifyUserEmailInCard(String email);

	public boolean verifyUserAvtarAvailabe();

	public boolean verifyLikedUserName(String username);

	public boolean verifyCompanyName(String value);

	public boolean verifyUserEmailLinkOnCard();

	public void clickOnActivity();

	public boolean isAvailableSendMessageButtonOnCard();

	public boolean isAvailableFollowButtonOnCard();

	public boolean verifySendAFile();

	public void closeSendAFileModal();

	public void clickOnAssignedToMeTab();

	public boolean verifyTaskVisibility(String task);

	public void clickOnAddTask();

	public boolean verifyTaskTextBox();

	public boolean verifyTaskDatePicker();

	public void addPersonalTask(String description);

	public void clickOnTaskFromPersonalTab(String task);

	public void clickSeeAllTask();

	public boolean verifyMyTasksTab();

	public boolean verifyEventDisplayedOnDashboard(String event);

	public EventsWeb clickOnEventDisplayedOnDashboard(String event);

	public boolean verifyMyFilesTab();

	public void clickOnSharedTab();

	public boolean verifySharedItem(String file);

	public void clickOnReceivedTab();

	public boolean verifyReceivedItem(String file);

	public void clickOnFile(String file);

	public void clickOnForwardArrowOfViewer();

	public void clickOnBackwordArrowOfViewer();

	public void clickOnCloseFileViewer();

	public void clickOnDownoloadFileViewer();

	public void clickOnSeeAllLink();

	public void clickOnSendAFileButton();

	public boolean verifyFileTabInSendAFile();

	public boolean verifyEmailTabInSendAFile();

	public void clickOnpostTab();

	public void enterPostMicroBlogValue(String microblogpostvalue);

	public void clickOnpostButton();

}
