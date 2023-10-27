package com.highq.pageobjects.base;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.pages.ActivityWeb;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.ApprovalWorkflowWeb;
import com.highq.pageobjects.pages.AspAdminWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.BlogWeb;
import com.highq.pageobjects.pages.DashboardWeb;
import com.highq.pageobjects.pages.DocumentWeb;
import com.highq.pageobjects.pages.EventsWeb;
import com.highq.pageobjects.pages.HomeWeb;
import com.highq.pageobjects.pages.IsheetsWeb;
import com.highq.pageobjects.pages.LoginPageWeb;
import com.highq.pageobjects.pages.MyDraftWeb;
import com.highq.pageobjects.pages.MyFilesWeb;
import com.highq.pageobjects.pages.MyProfileWeb;
import com.highq.pageobjects.pages.PeopleWeb;
import com.highq.pageobjects.pages.QandAWeb;
import com.highq.pageobjects.pages.SearchContentWeb;
import com.highq.pageobjects.pages.SettingsWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;
import com.highq.pageobjects.pages.TasksWeb;
import com.highq.pageobjects.pages.WikiWeb;

public interface BannerPage
{
	public TasksWeb gotoTaskModule();

	public String getRandomString();

	public AdminPageWeb gotoAdminModule();

	public String replaceTextInUserMenuXpath(String option);

	public String getUserData(String dataToBeFormatted);

	public HomeWeb gotoHomeModule();

	public DocumentWeb gotoFileModule();

	public DashboardWeb gotoDashboard();

	public SystemAdminWeb gotoSystemAdmin();

	public void clickOnUserAvtar();

	public void clickOnUserAvtarOptions(String option);

	public LoginPageWeb logout();

	public void scrollToElement(By by) throws InterruptedException;

	public boolean isAlertPresent();

	public void moveToElement(By by);

	public void scrollToBottom();

	public String getUserId(String dataToBeFormatted);

	public WikiWeb gotoWikiModule();

	public AspAdminWeb gotoASPAdmin();

	public void setSelection(By locator, boolean requiredState);

	public boolean verifySiteName(String siteName);

	public ActivityWeb gotoActivityModule();

	public void clickOnFavourite();

	public void clickOnFavouriteFilter();

	public boolean verifySiteInFavouriteList(String siteName);

	public QandAWeb gotoQAndAModule();

	public boolean verifySearchLink();

	public void clickOnSearchButton();

	public void clickOnSearchDropDown();

	public boolean verifySearchFilterOptions(String filterOptions);

	public boolean verifyAdvancedSearchOption();

	public boolean verifyNotificationLink();

	public void clickOnNotificationLink();

	public boolean verifyNotificationModal();

	public boolean verifyNotificationOptions(String notificationOptions);

	public boolean isSeeAllNotificationsEnabled();

	public void clickOnSeeAllNotifications();

	public boolean verifySeeAllNotificationsModalOpened();

	public void cancelSeeAllNotificationsModal();

	public boolean verifyAwaitingApprovalMessage();

	public boolean verifyAwaitingApprovalCount();

	public void clickOnAwaitingApproval();

	public boolean verifyPrivateMessageLink();

	public void clickOnPrivateMessage();

	public boolean verifyPrivateMessageModal();

	public boolean verifyPrivateMessageOptions(String privateMessageOption);

	public boolean verifyFavouriteLink();

	public void clickOnFavouriteLink();

	public boolean verifyFavouriteModalOpened();

	public boolean verifySearchBoxOnFavouriteModal();

	public boolean verifyFavouriteFilterFavouriteModal();

	public boolean verifyRecentlyViewedFilterFavouriteModal();

	public boolean verifySeeAllFavourites();

	public boolean verifyUserAvtarLink();

	public boolean verifyUserAvtarOptions(String option);

	public boolean valuePresent(List<WebElement> list, String value);

	public IsheetsWeb gotoIsheetsModule();

	public BannerPage login(String email, String pwd);

	public boolean verifyCofigurationPriority(String[] configurationList, int index);

	public void addAttachment(String attachFrom, String siteName, String attachmentName);

	public void addAttachmentFromRecentTab(String site, String attachmentName);

	public void addAttachmentFromBrowseTab(String site, String attachmentName);

	public void addAttachmentFromUploadTab(String filePath);

	public void addAttachmentFromSearchTab(String site, String attachmentName);

	public void searchAttachmentSite(String site);

	public boolean verifyText(By elementObject, String textToBeVerified);

	public boolean verifyNoMessages();

	public void clickOnNewMessages();

	public void selectAttachmentSite(By siteDropDown, String site);

	public void uploadFile(By browseButton, String fileuploadpath);

	public void setRestrictedPermissions(Map<String, Map<String, Boolean>> permissions) throws InterruptedException;

	public void setDefaultPermissions();

	public void clickOnSaveOnCategoryPermission();

	public void cleanDownloadsFolder();

	public File getLatestFilefromDir(String dirPath);

	public boolean verifyDownloadedFile(String fileName);

	public void clickAdvancedSearchOption();

	public void sendPrivateMessage(String message, String... recipients);

	public void clickSendOnNewMessageModal();

	public boolean verifySeeAllMessagesEnabled();

	public void clickOnSeeAllMessages();

	public boolean verifySeeAllMessagesSearchBox();

	public void searchPrivateMessage(String searchText);

	public void clickSeeAllMessagesSearchDropDown();

	public boolean verifySearch();

	public boolean verifySearchMessageFilterOption(String option);

	public void selectSearchMessageFilterOption(String option);

	public void selectOptionFromAutoSuggest(String option);

	public boolean verifyAdvanceSearchModalOpened();

	public void clickCancelOnAdvanceSearchModal();

	public SearchContentWeb searchContentInGlobalSearch(String searchText);

	public void selectSearchFilterOption(String filterOption);

	public EventsWeb gotoEventModule();

	public void selectOptionFromDropDown(By dropDownButton, By dropDownBox, String optionList, String optionToSelect);

	public void setNotification(String email, Boolean state);

	public void setNotificationForAll();

	public void clickOnSetNotificatons();

	public boolean noResultsFound();

	public boolean verifyRecentMessageRecieved(String userName, String message, boolean capitalize);

	public void openFirstMessage();

	public void clickCancelInMessageBox();

	public boolean verifyFirstMessageIsRecentlyRecieved();

	public void clickOnRecentlyViewedFilter();

	public boolean verifyFavouriteOrRecentlyViewedDisplayList();

	public void waitTillGlobalMessageDissappears();

	public PeopleWeb gotoPeopleModule();

	public MyProfileWeb gotoMyProfile();

	void gotoEmailTab();

	void gotoLinkTab();

	void gotoMessageTab();

	void gotoMicroblogTab();

	void shareViaEmail(String recipientMail, String subject, String message);

	void shareViaMessage(String recipientMail, String message);

	void copyShareLink(boolean shortUrl);

	void openCopiedURL() throws UnsupportedFlavorException, IOException;

	void selectOptionInMoreAction(String option);

	void clickOnMoreAction();

	public String getText(By element);

	public boolean verifyEmptyRecipent();

	public boolean disabledSendButtonInShareDialogueBox();

	public void clickSendInShareModal();

	public String getSubjectFromShareModel();

	public boolean verifyDefaultSubjectIsPresent();

	public void clickPostInShareModal();

	public boolean verifyInputTextIsPresent(By elementXpath);

	public void clickCancelInShareModal();

	public Timestamp getStartDateTimeStamp();

	public Timestamp getEndDateTimeStamp();

	public Object getResult(String sql, Object... params);

	public Object getResult(String sql);

	public boolean verifyMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject,
			String emailMessage);

	public String getMailContent(String mailQuery);

	public void createHtmlFile(String fileName, String fileContent);

	public void getLocalHtmlPage(String fileName);

	public boolean verifyContent(String mailto, String content);

	public void closeCurrentTab();

	public boolean verifyDefaultMessageIsPresent();

	public boolean verifyDefaultMicroblogSite(String siteName);

	public void selectUserNameFromAutoSuggest(String option);

	public boolean verifyFavouritesElementInMyFavourites(String element);

	public void gotoPosts();

	public void switchWindow();

	public boolean verifyHubConfiguration();

	public boolean configureHub();

	public boolean verifyMicroserviceConfiguration();

	public boolean configureMicroService();

	public void setUserListForResetPassword(String userId, String userDomain);

	public BlogWeb gotoBlogModule();

	public boolean searchInFavourite(String searchText);

	public TasksWeb goToMyTasks();

	public MyFilesWeb goToMyFiles();

	public String getCustomDateValue(int days, String format);

	public String getDateAndSystemTime(String dateAndTimeFormat);

	public void selectConfiguration(By configurationName, By configurationDropDown, By configurationCheckbox,
			String optionValue);

	public String getCurrentDate();

	public void setDateInCalenderModal(String date);

	public int getCurrentYearInCalenderModal();

	public String getCurrentMonthInCalenderModal();

	public void clickOnYearMonthPickerInCalenderModal();

	public int getCurrentYearFromMonthPicker();

	public void clickOnNextInMonthPicker();

	public void clickOnPreviousInMonthPicker();

	public void clickOnMonthInMonthPicker(String month);

	public void clickOnLinkInMailContentMessageBody();

	public void clickOnLastMessageLinkInMessageContainer();

	public boolean verifyModuleName(String moduleName);

	public BannerPageWeb clickOnModuleName(String moduleName);

	public void clickContainer(String containerName);

	public boolean verifySubMenuItem(String containerName, String subItemName);

	public boolean verifyContactusEmailFromFooter(String email);

	public boolean isContactUslinkVisible();

	public boolean verifyContactUsLabel(String labelName);

	public boolean verifyAlertMessage(String message);

	public int getUserID(String userId, String userDomain);

	public void acceptAlert();

	boolean verifyMessageForNoFavourites(String message);

	public void closeSaveChangesMessage();

	DashboardWeb gotoDashboardByClickOnLogo();

	public void callStoreProcedure(String spName, String params) throws Exception;

	public String gethashValueOfColor(By xpath, String cssProperty);

	public Date addDays(Date date, int days);

	public void switchToNextTab(String url);

	public void switchToPreviosTab();

	public MyDraftWeb goToMyDrafts();

	public DashboardPage goToSendAFile();

	public ApprovalWorkflowWeb gotoapprovalWorkflow();

	public SettingsWeb gotoSettings();

	public DashboardWeb gotoHighQHub();

	public boolean verifyHighqHubLink();

	public boolean verifyUserLogoutFromSystem();

	public DashboardWeb gotoLogout();

	public boolean verifyOkButton();

	public boolean verifyCancelButton();

	public boolean verifyLogoutMessage(String message);

	public DashboardWeb gotoHelp();

	public boolean verifyHelpPage();

	public DashboardWeb gotoInstallHighQDriveLink();

	public DashboardWeb gotoInstallOfficePluginLink();

	public void browserBackNavigate();

	public boolean verifyLastFavoriteContentAccessed(String filename);

	public boolean verifyDownloadPage();

	public void clickOnDownlaod();

	public void clickOnReceivedLink();

	public Boolean verifyFolderInLink();

	public boolean verifyAndAcceptDisclaimer(String disclaimer);

	public boolean verifyPreviewFileTitle(String fileTitle);

	public boolean verifyPreviewTag(String tag);

	public void expandPreviewRightPanel();

	public boolean verifyVersionNote(String metaDataNote);

	public boolean verifyMetaDataTaskWithDocument(String metaDataTaskName);

	public boolean verifyFilePreviewPage();

	public void clickOnLinkInMailContentMessageBody(String link);

	public String getIpAdress();

	public boolean verifyActiveModule(String moduleName);

	public void clickOnSiteNameDropDownInBanner();

	public void searchSiteInBannerDropDown(String siteName);

	public BannerPage selectSiteFromBannerDropDown(String siteName);

	public double getFileSize(String fileNameWithExtension);

	public void closePreviewPage();

	boolean verifyTooltipDisplayed(String tooltipOption);

	boolean verifyFirstNotification(String notificationMessage);

	boolean verifyFavouriteContentList(List<String> favouriteContentList);

	boolean verifyRecentlyViewedContentList(List<String> favouriteContentList);

	public void clickOnReceivedHomeLink();

	public void switchToParentWindow();

	BannerPageWeb login(String email, String pwd, boolean rememberMe);

	void openTabHitPath(String path);

	String getCurrentURL();

	void goBackInBrowser();

	public LoginPage login();

	public <T> void reverseSort(List<T> list, Comparator<? super T> c);

	public String getPopupMsgFromSystemPopupAndAccept();

	String getUserOrgName(String userEmail);

	public void clickOnTabOfFavouriteLink(String tabName);
}
