package com.highq.pageobjects.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.highq.base.DBConnection;
import com.highq.base.DBManager;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.BannerLabels;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;

public class BannerPageWeb extends TestBaseSetup implements BannerPage
{
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	BannerPage bannerPageWeb;
	public static String domain;

	final String DIALOGBODY = "(//*[@class='dialogBody'])[last()]";
	public static ArrayList<String> resetUserPassword = new ArrayList<>();

	By closeButtonSaveChangesMessage = By.xpath(".//*[@id='animatedMessageBox']//a");
	public LinkedHashMap<String, Boolean> configurationMap = new LinkedHashMap<>();
	By logoImagaeLink = By.className("logo");
	public By breadCrumb = By.xpath("//*[contains(@class,'breadCrumbNav')]");
	public By homeButton = By.xpath("//a[@data-original-title='Dashboard']");
	public By logoImageButton = By.xpath("//*[@class='logo']");
	By searchLink = By.xpath("//a[contains(@class,'searchBtn headNavIcon')]//i[contains(@class,'icon-search')]");
	By searchTextBox = By.id("bannerTopGlobalSearchText");
	By searchDropDown = By.xpath(
			".//*[@id='globalSearchfilterType_deskTopView_containerDivID']/button[contains(@class,'dropdown-toggle')]");
	By searchFilterOpened = By
			.xpath(".//*[@id='globalSearchfilterType_deskTopView_containerDivID']//*[@aria-expanded='true']");
	By searchDropDownHeaderText = By.xpath(".//*[@id='globalSearchfilterType']/li[1]");
	By globalSearchAllLink = By.xpath(
			"//div[@id='globalSearchfilterType_deskTopView_containerDivID']//li[@id='bannertop_globalsearch_all']/a");
	By globalSearchPeopleLink = By.xpath(
			"//div[@id='globalSearchfilterType_deskTopView_containerDivID']//li[@id='bannertop_globalsearch_people']/a");
	By globalSearchSiteLink = By.xpath(
			"//div[@id='globalSearchfilterType_deskTopView_containerDivID']//li[@id='bannertop_globalsearch_sites']/a");
	By globalSearchContentLink = By.xpath(
			"//div[@id='globalSearchfilterType_deskTopView_containerDivID']//li[@id='bannertop_globalsearch_content']/a");
	By globalSearchAdvanceSearchLink = By
			.xpath("//div[@id='globalSearchfilterType_deskTopView_containerDivID']//a[text()='"
					+ BannerLabels.BANNERPAGE_ADVANCEDSEARCH + "']");
	By advanceSearchModalOpened = By
			.xpath(".//*[@id='file_module_advanceSearch_modal' and contains(@style,'display: block')]");
	By advanceSearchModalClosed = By
			.xpath(".//*[@id='file_module_advanceSearch_modal' and contains(@style,'display: none')]");
	By advanceSearchModalCancelButton = By.id("file_module_advanceSearch_modal_cancel");
	By advanceSearchModalSearchButton = By.id("file_module_advanceSearch_modal_search");

	By notificationLink = By.xpath("//li[contains(@class,'notificationBtn')]//a[contains(@class,'headNavIcon')]/i");
	By unreadNotificationIdText = By.id("approvedCountID");
	By notificationModalOpened = By.xpath("//*[@class='dropdown notificationBtn open']");
	By seeAllNotificationsLink = By.id("bannerTopNotificationDropdownSeeAll");
	By seeAllNotificationsModalOpened = By
			.xpath(".//*[@id='NOTIFICATION_MODAL' and contains(@style,'display: block')]");
	By seeAllNotificationsModalClosed = By.xpath(".//*[@id='NOTIFICATION_MODAL' and contains(@style,'display: none')]");
	By seeAllNotificationsCloseLink = By.id("NOTIFICATION_MODAL_MAIN_CLOSE_BUTTON");

	By notificationMenuHeadingText = By.xpath("//li[@class='dropdown notificationBtn open']//h6");
	By notificationMenuSettingsLink = By.xpath("//li[@class='dropdown notificationBtn open']//a[text()='"
			+ BannerLabels.BANNERPAGE_NOTIFICATION_SETTINGS + "']");

	public By userAvtar = By.xpath("//li[contains(@class,'dropdown userMenuBtn')]");
	public By userAvtarMenu = By.xpath("//li[@class='dropdown userMenuBtn open']//li[normalize-space(.)='{0}']");

	By privateMessageLink = By.id("bannerTopPrivateMessage");
	By privateMessageModalOpened = By.xpath(".//*[@id='bannerTopPrivateMessage']//*[@aria-expanded = 'true']");
	By noMessages = By.xpath(".//*[@id='bannerTopPrivateMessageList']//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_MESSAGE_NOMESSAGES + "']");
	By newMessageButton = By.xpath(
			".//*[@id='bannerTopPrivateMessage']//*[text()='" + BannerLabels.BANNERPAGE_MESSAGE_NEWMESSAGE + "']");
	By newMessageModalOpened = By.xpath(".//*[@id='NEW_PRIVATE_MESSAGE_MODAL' and contains(@style,'display: block')]");
	By newMessageRecipients = By.id("newPrivateMessageUserList-tokenfield");
	By newMessageEditor = By.id("newPrivateMessageEditor");
	By newMessageSendButton = By.id("NEW_PRIVATE_MESSAGE_MODAL_sendButton");
	By newMessageCancelButton = By.id("NEW_PRIVATE_MESSAGE_MODAL_cancelButton");
	By newMessageModalClosed = By.xpath(".//*[@id='NEW_PRIVATE_MESSAGE_MODAL' and contains(@style,'display: none')]");
	By seeAllMessagesLink = By.id("bannerTopPrivateMessageDropdownSeeAll");
	By seeAllMessagesModalOpened = By.xpath(".//*[@id='ALL_PRIVATE_MESSAGE_MODAL' and @aria-hidden='false']");
	By seeAllMessagesModalSearchBox = By.id("bannertopPrivateMessageListForModalInputEle");
	By seeAllMessagesSearchDropDown = By
			.xpath(".//*[@class='input-group-btn modalTopDrop']//*[@data-toggle = 'dropdown']");
	By seeAllMessagesSearchDropDownOpened = By
			.xpath(".//*[@class='input-group-btn modalTopDrop open']//*[@data-toggle = 'dropdown']");
	By searchMessageFilterBy_ACTIVE = By.id("ACTIVE");
	By searchMessageFilterBy_UNREAD = By.id("UNREAD");
	By searchMessageFilterBy_ARCHIVED = By.id("ARCHIVED");
	By searchMessagePrivateMessageList = By.id("PRIVATE_MESSAGE_LIST_CONTAINER");

	By favouritesLink = By.id("bannerTopFav");
	By favouritesLinkOpened = By.xpath(".//*[@id='bannerTopFav']//*[@aria-expanded = 'true']");
	By favouritesSearchBoxInput = By.id("bannertopFavouriteItemListInputEle");
	By favouritesfavouriteFilter = By.id("bannertopFavouritesFilter");
	By favouritesRecentlyViewedFilter = By.id("bannertopRecentViewedFilter");
	By seeAllFavourites = By.xpath(".//*[@id='bannerTopFav']//*[@id='bannerTopFavouriteDropdownSeeAllFav']");
	By awaitingApprovalMessage = By.id("approvedBannerID");
	By awaitingApprovalCount = By.id("approvedCountBannerID");
	By favouriteSearchBox = By.id("bannertopFavouriteItemListInputEle");

	/************ Xpaths for menu ***************/
	String mainMenuListXpath = ".//ul[contains(@class,'mainMenu')]";
	By homeModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Home']");
	By filesModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Files']");
	By activityModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Activity']");
	By wikiModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Wiki']");
	By blogModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Blog']");
	By tasksModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Tasks']");
	By eventsModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Events']");
	By iSheetsModuleLink = By.xpath(mainMenuListXpath + "//a[@title='iSheets']");
	By qandAModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Q&A']");
	By peopleModuleLink = By.xpath(mainMenuListXpath + "//a[@title='People']");
	By reportModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Report']");
	By adminModuleLink = By.xpath(mainMenuListXpath + "//a[@title='Admin']");
	By newAadminModuleLink = By.xpath(mainMenuListXpath + "//a[@title='New Admin']");
	By myFilesModuleLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='My files']");
	By myTasksModuleLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='My tasks']");
	By MyDraftsLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='My drafts']");
	By approvalWorkflow = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Approval workflow']");

	By settingsLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Settings']");
	By highqHub = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='HighQ Hub']");
	By emailIdTextBox = By.id("Login_email");
	By logout = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Logout']");
	By help = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Help']");
	By installHighQDriveLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Install HighQ Drive']");
	By installOfficePluginLink = By.xpath(".//*[contains(@class,'mainMenu')]//*[@title='Install Office Plugin']");

	By globalMessageBar = By.xpath(".//*[@id='globalAlertMessageContainer' and not(@class='globalMsg hide')]");
	By globalMessageBarCloseLink = By
			.xpath(".//*[@id='globalAlertMessageContainer']//*[@data-original-title='Remove']");

	By globalMessageBarVisible = By.id("globalAlertMessageContainerMsgSpace");
	By footer_Ok = By.xpath(".//*[contains(@class,'in')]//*[@id='collaborateMessageOkButton']"); // By.id("collaborateMessageOkButton");
	By footer_Cancel = By.xpath("(//*[contains(@class,'modal-footer')])[last()]//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_MODELFOOTER_CANCEL + "']");

	By navigationBar = By.xpath(" .//*[normalize-space(@class)='breadCrumbNav']");
	By bannerTopFavourite = By.id("bannerTopFav");
	By bannerTopFavouriteFilter = By.id("bannertopFavouritesFilter");
	By bannerTopFavouriteMenuOpened = By.xpath(".//*[@id='bannerTopFav' and @class='dropdown favMenuBtn open']");
	By bannerTopFavouriteDisplayList = By.id("bannertopfavouritedisplaylist");
	By bannerTopFavouritesRecentlyViewedFilter = By.id("bannertopRecentViewedFilter");
	By sendFile = By.id("bannertop_send_a_file_link");
	/************ Add Attachment *********************/
	By attachmentModal = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]");
	By attachment_RecentTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_RECENT + "']");
	By attachment_BrowseTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_BROWSE + "']");
	By attachment_UploadTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_UPLOAD + "']");
	By attachment_SearchTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_SEARCH + "']");
	By attachment_RecentTab_SiteDropDownButton = By
			.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_recentTab')])[1]");
	By attachment_BrowseTab_SiteDropDownButton = By
			.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_browseTab')])[1]");
	By attachment_SearchTab_SiteDropDownButton = By
			.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_searchTab')])[1]");
	By attachment_loader = By.xpath("(//*[@class='grayloader' and @style='display: block;'])[last()]");
	By attachment_loaderBig = By.xpath("(//*[contains(@*,'gray-loaderbig')])[last()]");
	By attachment_attachButton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_ATTACH + "'])[last()]");

	/**************** Add Attachment - site search ***************/
	By attachment_siteSearch_dropDownWindow = By.xpath("(//*[contains(@class,'dropdown open')])[last()]");
	By attachment_siteSearch_noResultsFound = By
			.xpath("(//*[contains(@class,'dropdown open')]//*[normalize-space(text())='"
					+ BannerLabels.BANNERPAGE_ADDATTACHMENT_SITESEARCH_NORESULTFOUND + "'])[last()]");
	By attachment_siteSearch_clearSearchIcon = By
			.xpath("(//*[contains(@class,'dropdown open')]//*[@data-original-title='Clear search term'])[last()]");
	By attachment_siteSearch_searchInput = By
			.xpath("(//*[contains(@class,'dropdown open')]//*[contains(@id,'siteSearchInput')])[last()]");

	By attachment_BrowseTab_ExpandAll = By
			.xpath("(//*[contains(text(),'" + BannerLabels.BANNERPAGE_ADDATTACHMENT_BROWSE_EXPANDALL + "')])[1]");
	By attachment_UploadTab_browseInput = By.xpath("(//*[normalize-space(text())='"
			+ BannerLabels.BANNERPAGE_ADDATTACHMENT_BROWSE_INPUTBOX + "']/preceding-sibling::*/input)[last()]");
	By attachment_SearchTab_searchBox = By.id("insertlink_searchTab_searchContentInput");
	By attachment_SearchTab_searchDataAllElements = By.xpath(".//*[@id='insertLink_searchTab_searchDatalist']/*");
	By btnDone = By.xpath("(//span[contains(text(),'" + BannerLabels.BANNERPAGE_ADDATTACHMENT_DONE + "')])[last()]");

	By modalContent = By.xpath("(//*[@aria-hidden='false']//*[@class='modal-content'])[last()]");
	By modelError = By
			.xpath("(//*[@aria-hidden='false']//*[@class='modal-content'])[last()]//*[@id='errorElementContainer']");
	By modalContentClosed = By.xpath("(//*[@aria-hidden='false']//*[@class='modal-content'])[last()]");
	By defaultPermissionRadioButton = By.id("permissionTypeDefault");
	By restrictedPermissionRadioButton = By.id("permissionTypeRestricted");
	By categoryPermissionSaveLink = By.id("catgegoryPermissionModal_saveCategoryPermission");
	By share_recipientHover = By.xpath("//*[@class='typeahead_labels']//*[@class='typeahead_secondary']");// By.xpath("(//*[@class='typeahead_labels']//*)[last()]");

	By share_UserNameHover = By.xpath("(//*[@class='typeahead_primary']//*)[last()]");

	// *************************************** Set Notifications
	// ***********************************************

	By setNotificationsLink = By.xpath("(//*[text()='Set email notifications'])[1]");
	By notificationCheckBox = By.xpath(".//input[@name='site.wikiApprovalAlertToAll']");
	By setNotificationSaveButton = By.xpath(".//*[@id='frmSetNotificationDialogPage']//*[text()='"
			+ BannerLabels.BANNERPAGE_SETNOTIFICATIONMODEL_SAVE + "']");
	By setNotificationCancelButton = By.xpath(".//*[@id='frmSetNotificationDialogPage']//*[text()='"
			+ BannerLabels.BANNERPAGE_SETNOTIFICATIONMODEL_CANCEL + "']");
	By setNotificationCloseLink = By.xpath(".//*[@id='setNotificationModalWiki']//*[@class='close']");
	By notification_selectAllLink = By
			.xpath("//*[normalize-space(text())='" + BannerLabels.BANNERPAGE_SETNOTIFICATIONMODEL_SELECTALL + "']");
	By notification_clearAllLink = By
			.xpath("//*[normalize-space(text())='" + BannerLabels.BANNERPAGE_SETNOTIFICATIONMODEL_CLEARALL + "']");
	By notification_setNotificationsButton = By.id("siteAdmin_module_setNotification_modal_setNotificationBtnID");

	By noResultsFound = By.xpath(
			".//*[@id='showMsg']/*[normalize-space(text())='" + BannerLabels.BANNERPAGE_NORESULTFOUNDMESSAGE + "']");
	By firstMessageLink = By.xpath("(.//*[@id='bannerTopPrivateMessageList']//*[contains(@class,'read')])[1]");
	By firstMessageGreyMeta = By.xpath("(//*[@id='bannerTopPrivateMessageList']//*[contains(@class,'greyMeta')])[1]");
	String lastMessageContainer = "(.//*[@id='PRIVATE_MESSAGE_CONVERSATION_LIST_CONTAINER']//*[contains(@class,'ckContentArea messConvo')])[last()]";
	// private WebDriver driver;

	By moreActionMenuLinkInModel = By.xpath(
			".//*[contains(@style,'display: block')]//*[@class='icon icon-actions dropdown-toggle' and contains(@data-original-title,'More actions')]");

	/******** Share *******************/
	By share_Email = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Email']");
	By share_recepent = By.className("token");
	By share_Link = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Link']");
	By share_Link_UseShortURLCheckbox = By.id("chkshortenURLID");
	By share_Link_URLInput = By.id("file_module_copyLink_txt");
	By share_Link_SelectLinkButton = By.xpath("//*[normalize-space(text())='Select link']");
	By share_Message = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Message']");
	By share_Email_recipientsInput = By.id("file_module_emailRecipients-tokenfield");
	By share_Email_subjectInput = By.id("file_module_email_subject");
	By share_Email_messageInput = By.id("file_module_email_message");
	By share_Message_recipientsInput = By.id("file_module_Site_Share_PrivateMessageUserList-tokenfield");
	By share_Message_messageInput = By.id("file_module_Site_Share_PrivateMessageEditor");
	By share_Cancel = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Cancel']");
	By share_Send = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Send']");
	By share_disabled_Send = By.xpath("//*[normalize-space(text())='Send' and @disabled='disabled']");
	By share_Post = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Post']");
	By modalInvisible = By.xpath("(//*[@class='modal fade'])[last()]");
	By accessRestricted = By.xpath("//*[normalize-space(text())='Access restricted']");
	By activeWiki = By.xpath("//*[@id='leftTreeAndPageLinks']//*[contains(@class,'fancytree-active')]");
	By share_contentModal = By.xpath("//*[contains(@class,'modalBodyScroll')]");
	By share_Microblog = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Microblog']");
	By share_Microblog_messageInput = By.id("file_module_Site_Share_MicroblogEditor");
	By share_Microblog_shareWithInput = By.id("file_module_Site_Share_Microblog_SiteList-tokenfield");
	By share_Microblog_selectedSite = By.xpath("(//*[contains(@class,'token-label')])[1]");
	By share_SentSuccessfully = By.xpath(".//*[normalize-space(text())='Link successfully sent to:']");
	By share_Close = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Close']");

	By globalMessageBarHidden = By.xpath("//*[@id='globalAlertMessageContainer' and @style='opacity: 0;']");

	By recentActivity_Posts = By
			.xpath("//*[contains(@class,'nav-pills moremenu')]//*[normalize-space(text())='Posts']");

	By currentMonthYearInCalender = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[@class='picker-switch']");

	By currentYearInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[@class='picker-switch']");

	By previousYearButtonInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[contains(@class,'prev')]");

	By nextYearButtonInMonthPicker = By
			.xpath("//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[contains(@class,'next')]");

	By highqHubLink = By.xpath(".//*[@class='radius5 navBtn'  and contains(@value,'Register')]");

	By logoutButton = By.id("collaborateCustomModalMessage");

	By avtarHelpPage = By.id("logo");

	String logoutMessage = BannerLabels.BANNERPAGE_LOGOUT_MESSAGE;

	By noFavouritesMsg = By.xpath(".//*[@id='bannertopfavouritedisplaylist']//li");

	By favoriteItem = By.xpath("(//*[@id='bannertopfavouritedisplaylist']//span[@class='TruncateTxt linkblack'])[1]");

	By previewPage_Loader = By.xpath("//*[@class='detailsCont']//img[last()]");

	public static final String GET_TIME_STAMP_QUERY = "SELECT GETUTCDATE()";
	public static final long ADD_TIME_DELAY = 3000L;
	String mailContent = "";
	String mailHtmlFile = "MailContent.html";
	public String parentWindow;
	public String childWindow;
	DBManager dbManager = new DBManager();
	public final String modules[] = {SiteAdminLabels.SITEADMIN_MODULESPAGE_HOME,
			SiteAdminLabels.SITEADMIN_MODULESPAGE_ACTIVITY, SiteAdminLabels.SITEADMIN_MODULESPAGE_FILES,
			SiteAdminLabels.SITEADMIN_MODULESPAGE_WIKI, SiteAdminLabels.SITEADMIN_MODULESPAGE_BLOG,
			SiteAdminLabels.SITEADMIN_MODULESPAGE_TASKS, SiteAdminLabels.SITEADMIN_MODULESPAGE_EVENTS,
			SiteAdminLabels.SITEADMIN_MODULESPAGE_QANDA, SiteAdminLabels.SITEADMIN_MODULESPAGE_ISHEETS,
			SiteAdminLabels.SITEADMIN_MODULESPAGE_PEOPLE};

	By contactus = By.id("gdprContactId");

	/**************** HUB **************/
	By locInptHubURL = By.id("CUSTOM_HIGHQXCHANGE_URL");
	By locLnkRegisterInstance = By.linkText("Register instance");
	String instanceRegisterMessage = "Instance already register with highq exchange";
	String hubURL = "https://hubuat.hqdev.highq.com/hubuat/";
	By globalMessageContainer = By
			.xpath("//*[@id='globalAlertMessageContainerMsgSpace']//ancestor::*[@class='globalMsg']");
	By globalMessageContainerHidden = By
			.xpath("//*[@id='globalAlertMessageContainerMsgSpace']//ancestor::*[@class='globalMsg hide']");
	By body = By.xpath("//body");
	By linkInMailContent = By.xpath("//*[starts-with(normalize-space(text()),'https://')]");
	By linkInLastMessage = By.xpath(lastMessageContainer + "//a");

	By receivedLink = By.xpath("(//*[contains(@href,'https://highqhubuat')])[last()]");
	By loader = By.xpath(".//*[@id='LONG_PATH_CHECKING_MODAL_BODY']/div/img");

	By download = By.xpath("//*[normalize-space(text())='Download']");

	By disclaimerText = By.xpath("(//*[@class='modal-content']//*[contains(@class,'modal-body')])[last()]//*[1]");

	By footer_Accept = By
			.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-footer']//*[normalize-space(text())='"
					+ FileLabels.FILES_COMMON_ACCEPT + "'])[last()]");

	By previewPageHeader_CloseIcon = By
			.xpath("((//*[contains(@id,'modal_header')])[last()]//*[@class='close tooltipShow'])[1]");

	By previewPageHeader_FileName = By.xpath("(//*[contains(@id,'modal_header')])[last()]//*[@class='iconMeta']/*[1]");

	By previewPage_headerDetails = By.xpath(
			"(//*[@class='dialogBody'])[last()]//*[normalize-space(text())='" + FileLabels.FILES_COMMON_DETAILS + "']");

	By previewPage_ShowArrow = By.xpath(DIALOGBODY + "//*[contains(@class,'icon-chevron-left')]");

	final String PREVIEWPAGE_TOTALTAGS = DIALOGBODY + "//*[@class='tagContainer']//a";

	By versionNote = By.xpath("(//*[@class='dialogBody'])[last()]//*[@class='greyFont']/following-sibling::*[1]");

	By fileModuleAdeptol = By.id("FILE_MODULE_ADEPTOL");

	By folder = By.xpath(".//span[@data-original-title='Folder']");

	By homeLink = By.xpath("(//*[contains(@href,'https://autoind')])[last()]");

	By firstNotification = By
			.xpath("(.//*[@class='dropdown notificationBtn open']//*[contains(@class,'clearfix linkblack')])[1]");

	/** Banner -> Site Name drop down */
	By siteNameDropDown = By.xpath("//*[@class='mainNav']//*[@class='NavtextOuter']");

	By siteNameInput = By.id("dashboardNavigationSiteList_SiteSearchInput");

	By siteDropDownMenu = By.xpath("//*[@class='mainNav']//*[contains(@class,'siteDropdown open')]");

	By siteDropDown_noResultsFound = By
			.xpath(".//*[@id='dashboardNavigationPaneSiteList']//*[contains(@class,'noResultFound')]");

	By downloading = By.id("globalProcessMessageContainerMsgSpace");

	By enableOpenInOfficeOnline = By.xpath("//*[normalize-space(text())='Enable Open in Office Online']");
	By enableOpneINOfficeOnlineONOFF = By.id("CUSTOM_ENABLE_OPEN_IN_OFFICE_ONLINE_FOR_FILES");
	By enableOpneINOfficeOnlineCheckbox = By.id("ENABLE_OPEN_IN_OFFICE_ONLINE_FOR_FILES");

	By enableEditFileLbl = By.xpath("//*[normalize-space(text())='Enable \"Edit in Office\" for files']");
	By enableEditFile = By.id("CUSTOM_ENABLE_EDIT_IN_OFFICE_FOR_FILES");
	By enableEditFileckBox = By.id("ENABLE_EDIT_IN_OFFICE_FOR_FILES");

	/**
	 * @BeforeClass public void setUp() { driver = getDriver(); }
	 */

	@Override
	public TasksWeb gotoTaskModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findClickableElement(tasksModuleLink, Speed.slow, 10);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Task module not found");
		}
		return new TasksWeb(driver);
	}

	@Override
	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	@Override
	public AdminPageWeb gotoAdminModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(adminModuleLink, Speed.slow);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Admin module not found");
		}
		return new AdminPageWeb(driver);
	}

	@Override
	public String replaceTextInUserMenuXpath(String option)
	{
		String menuListXpath = "//li[@class='dropdown userMenuBtn open']//li[normalize-space(.)='{0}']";
		String menuItem;
		if (option.toString().contains("_"))
		{
			menuItem = option.toString().trim().replaceAll("_", " ");
		}
		else
		{
			menuItem = option.toString().trim();
		}
		menuListXpath = menuListXpath.replace("{0}", menuItem);
		return menuListXpath;
	}

	@Override
	public String getUserData(String dataToBeFormatted)
	{

		String result = null;

		if (dataToBeFormatted.contains("@"))
		{
			dataToBeFormatted = dataToBeFormatted.split("@")[0].trim();
		}
		if (dataToBeFormatted.contains("."))
		{
			// String userDetails[] = dataToBeFormatted.split("\\.");
			// result = StringUtils.capitalize(userDetails[0].toLowerCase()) + " " +
			// StringUtils.capitalize(userDetails[1].toLowerCase());
			String firstPart = dataToBeFormatted.substring(0, dataToBeFormatted.indexOf(".")).toLowerCase().trim();
			String lastPart = dataToBeFormatted.substring(dataToBeFormatted.indexOf(".") + 1).toLowerCase().trim();
			result = StringUtils.capitalize(firstPart) + " " + StringUtils.capitalize(lastPart);
		}
		else
		{
			result = StringUtils.capitalize(dataToBeFormatted.toLowerCase());
		}
		return result;
	}

	@Override
	public HomeWeb gotoHomeModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findClickableElement(homeModuleLink, Speed.slow, 10);

		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Home module not found");
		}

		return new HomeWeb(driver);
	}

	@Override
	public DocumentWeb gotoFileModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findClickableElement(filesModuleLink, Speed.slow, 10);

		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("File module not found");
		}

		return new DocumentWeb(driver);
	}

	@Override
	public DashboardWeb gotoDashboard()
	{
		waitTillGlobalMessageDissappears();
		scrollToTop();
		WebElement ele = findVisibleElement(homeButton, Speed.slow, 20);
		ele.click();
		return new DashboardWeb(driver);
	}

	public void scrollToTop()
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		boolean vertscrollStatus = (Boolean) je.executeScript(
				"return document.documentElement.scrollHeight>document.documentElement.clientHeight;") ? true : false;
		System.out.println("******************** " + vertscrollStatus);
		if (vertscrollStatus)
		{
			findVisibleElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
		}
		else
		{
			je.executeScript("scrollBy(0, 0)");
		}
	}

	@Override
	public void scrollToBottom()
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	@Override
	public SystemAdminWeb gotoSystemAdmin()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SYSTEMADMIN);
		return new SystemAdminWeb(driver);
	}

	@Override
	public AspAdminWeb gotoASPAdmin()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_ASPADMIN);
		return new AspAdminWeb(driver);
	}

	@Override
	public void clickOnUserAvtar()
	{
		WebElement userAvatar = findVisibleElement(userAvtar, Speed.slow);
		userAvatar.click();
	}

	@Override
	public void clickOnUserAvtarOptions(String option)
	{
		String menuXpath = replaceTextInUserMenuXpath(option);
		WebElement userMenuItem = findVisibleElement(By.xpath(menuXpath), Speed.slow);
		userMenuItem.click();
	}

	@Override
	public LoginPageWeb logout()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_LOGOUT);
		WebElement logOutConfirmationOkButton = findVisibleElement(footer_Ok, Speed.slow);
		logOutConfirmationOkButton.click();
		return new LoginPageWeb(driver);
	}

	@Override
	public void scrollToElement(By by) throws InterruptedException
	{
		WebElement element = findPresentElement(by);
		// JavascriptExecutor je = (JavascriptExecutor) driver;
		// je.executeScript("scrollBy("+xcord+", "+ycord+")");
		// je.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Override
	public boolean isAlertPresent()
	{
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try
		{
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		}
		catch (TimeoutException eTO)
		{
			foundAlert = false;
		}
		return foundAlert;
	}

	@Override
	public void moveToElement(By by)
	{
		Actions build = new Actions(driver);
		WebElement element;
		try
		{
			element = findVisibleElement(by, Speed.slow);
			build.moveToElement(element).build().perform();
		}
		catch (NoSuchElementException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String getUserId(String dataToBeFormatted)
	{
		String result = null;
		if (dataToBeFormatted.contains(" "))
		{
			String userDetails[] = dataToBeFormatted.split(" ");
			result = userDetails[0].toLowerCase() + "." + userDetails[1].toLowerCase();
		}
		else
		{
			result = dataToBeFormatted.toLowerCase();
		}

		return result;
	}

	@Override
	public WikiWeb gotoWikiModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(wikiModuleLink, Speed.slow, 5);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		return new WikiWeb(driver);
	}

	@Override
	public void setSelection(By locator, boolean requiredState)
	{
		WebElement ele = findVisibleElement(locator, Speed.slow);
		boolean currentState = ele.isSelected();
		if (currentState != requiredState)
		{
			ele.click();
		}
	}

	@Override
	public boolean verifySiteName(String siteName)
	{
		findVisibleElement(navigationBar, Speed.slow, 20);
		return (isDisplayed(
				By.xpath("//*[contains(@title,'" + siteName + "') and normalize-space()='" + siteName + "']")));
	}

	@Override
	public ActivityWeb gotoActivityModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(activityModuleLink, Speed.slow);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		return new ActivityWeb(driver);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public QandAWeb gotoQAndAModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(qandAModuleLink, Speed.slow, 5);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Q&A module not found");
		}
		return new QandAWeb(driver);
	}

	@Override
	public void clickOnFavourite()
	{
		WebElement bannerTopFavouriteEle = findClickableElement(bannerTopFavourite);
		bannerTopFavouriteEle.click();
	}

	@Override
	public void clickOnFavouriteFilter()
	{
		findVisibleElement(bannerTopFavouriteMenuOpened, Speed.slow);
		WebElement bannerTopFavouriteFilterEle = findVisibleElement(bannerTopFavouriteFilter);
		bannerTopFavouriteFilterEle.click();
	}

	@Override
	public void clickOnRecentlyViewedFilter()
	{
		findVisibleElement(bannerTopFavouriteMenuOpened, Speed.slow);
		WebElement bannerTopRecentlyViewedFilterEle = findVisibleElement(bannerTopFavouritesRecentlyViewedFilter);
		bannerTopRecentlyViewedFilterEle.click();
	}

	@Override
	public boolean verifySiteInFavouriteList(String siteName)
	{
		findVisibleElement(bannerTopFavouriteMenuOpened, Speed.slow);
		return isDisplayed(
				By.xpath(".//*[@id='bannertopfavouritedisplaylist']//*[normalize-space(text())='" + siteName + "']"));
	}

	/**
	 * @param message
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyMessageForNoFavourites(String message)
	{
		findVisibleElement(navigationBar, Speed.slow);
		String msg = findVisibleElement(noFavouritesMsg, Speed.slow).getText().trim();
		return (msg.contains(message));
	}

	@Override
	public boolean verifySearchLink()
	{
		return isDisplayed(searchLink);
	}

	@Override
	public void clickOnSearchButton()
	{
		WebElement searchEle = findClickableElement(searchLink);
		searchEle.click();
	}

	@Override
	public void clickOnSearchDropDown()
	{
		WebElement searchDropDownEle = findClickableElement(searchDropDown);
		searchDropDownEle.click();
	}

	@Override
	public boolean verifySearchFilterOptions(String filterOptions)
	{
		findVisibleElement(searchFilterOpened, Speed.slow, 5);
		return isDisplayed(By.xpath("(.//*[@id='globalSearchfilterType']//following::*[normalize-space(text())='"
				+ filterOptions + "'])[1]"));
	}

	@Override
	public boolean verifyAdvancedSearchOption()
	{
		findVisibleElement(searchFilterOpened, Speed.slow, 5);
		return isDisplayed(globalSearchAdvanceSearchLink);
	}

	@Override
	public boolean verifyNotificationLink()
	{
		return isDisplayed(notificationLink);
	}

	@Override
	public void clickOnNotificationLink()
	{
		WebElement notificationEle = findClickableElement(notificationLink);
		notificationEle.click();
	}

	@Override
	public boolean verifyNotificationModal()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		return isDisplayed(notificationModalOpened);
	}

	@Override
	public boolean verifyNotificationOptions(String notificationOptions)
	{
		if (verifyNotificationModal())
		{
			return isDisplayed(
					By.xpath(".//*[@class='dropdown notificationBtn open']//*[text()='" + notificationOptions + "']"));
		}
		return false;
	}

	@Override
	public boolean isSeeAllNotificationsEnabled()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		WebElement seeAllNotificationEle = findClickableElement(seeAllNotificationsLink);
		return seeAllNotificationEle.isEnabled();
	}

	@Override
	public void clickOnSeeAllNotifications()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		WebElement seeAllNotificationEle = findClickableElement(seeAllNotificationsLink);
		seeAllNotificationEle.click();
	}

	@Override
	public boolean verifySeeAllNotificationsModalOpened()
	{
		findVisibleElement(seeAllNotificationsModalOpened, Speed.slow);
		return isDisplayed(seeAllNotificationsModalOpened);
	}

	@Override
	public void cancelSeeAllNotificationsModal()
	{
		findVisibleElement(seeAllNotificationsModalOpened, Speed.slow);
		WebElement seeAllNotificationsCloseEle = findClickableElement(seeAllNotificationsCloseLink);
		seeAllNotificationsCloseEle.click();
		findPresentElement(seeAllNotificationsModalClosed, Speed.slow);
	}

	@Override
	public boolean verifyAwaitingApprovalMessage()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		WebElement awaitingApprovalMessageEle = findVisibleElement(awaitingApprovalMessage);
		return awaitingApprovalMessageEle.getText().contains("awaiting approval");
	}

	@Override
	public boolean verifyAwaitingApprovalCount()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		WebElement awaitingApprovalCountEle = findVisibleElement(awaitingApprovalCount);
		return Integer.parseInt(awaitingApprovalCountEle.getText().trim()) >= 1;
	}

	@Override
	public void clickOnAwaitingApproval()
	{
		findVisibleElement(notificationModalOpened, Speed.slow);
		WebElement awaitingApprovalCountEle = findVisibleElement(awaitingApprovalCount);
		awaitingApprovalCountEle.click();
	}

	@Override
	public boolean verifyPrivateMessageLink()
	{
		return isDisplayed(privateMessageLink);
	}

	@Override
	public void clickOnPrivateMessage()
	{
		WebElement privateMessageEle = findClickableElement(privateMessageLink);
		privateMessageEle.click();
	}

	@Override
	public boolean verifyPrivateMessageModal()
	{
		findVisibleElement(privateMessageModalOpened, Speed.slow);
		return isDisplayed(privateMessageModalOpened);
	}

	@Override
	public boolean verifyPrivateMessageOptions(String privateMessageOption)
	{
		if (verifyPrivateMessageModal())
		{
			return isDisplayed(
					By.xpath(".//*[@class='dropdown msgMenuBtn open']//*[text()='" + privateMessageOption + "']"));
		}
		return false;
	}

	@Override
	public boolean verifyFavouriteLink()
	{
		return isDisplayed(favouritesLink);
	}

	@Override
	public void clickOnFavouriteLink()
	{
		WebElement favouriteEle = findClickableElement(favouritesLink);
		favouriteEle.click();
	}

	@Override
	public boolean verifyFavouriteModalOpened()
	{
		findVisibleElement(favouritesLinkOpened, Speed.slow);
		return isDisplayed(favouritesLinkOpened);
	}

	@Override
	public boolean verifySearchBoxOnFavouriteModal()
	{
		if (verifyFavouriteModalOpened())
		{
			return isDisplayed(favouritesSearchBoxInput);
		}
		return false;
	}

	@Override
	public boolean verifyFavouriteFilterFavouriteModal()
	{
		if (verifyFavouriteModalOpened())
		{
			return isDisplayed(favouritesfavouriteFilter);
		}
		return false;
	}

	@Override
	public boolean verifyRecentlyViewedFilterFavouriteModal()
	{
		if (verifyFavouriteModalOpened())
		{
			return isDisplayed(favouritesRecentlyViewedFilter);
		}
		return false;
	}

	@Override
	public boolean verifySeeAllFavourites()
	{
		if (verifyFavouriteModalOpened())
		{
			return isDisplayed(seeAllFavourites);
		}
		return false;
	}

	@Override
	public boolean verifyUserAvtarLink()
	{
		return isDisplayed(userAvtar);
	}

	@Override
	public boolean verifyUserAvtarOptions(String option)
	{
		String menuXpath = replaceTextInUserMenuXpath(option);
		WebElement userMenuItem = findClickableElement(By.xpath(menuXpath));
		return userMenuItem.isDisplayed();
	}

	@Override
	public boolean valuePresent(List<WebElement> list, String value)
	{
		for (WebElement listValue : list)
		{
			if (listValue.getText().toString().equalsIgnoreCase(value))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public IsheetsWeb gotoIsheetsModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(iSheetsModuleLink, Speed.slow, 5);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Isheets module not found");
		}
		return new IsheetsWeb(driver);
	}

	@Override
	public BannerPage login(String email, String pwd)
	{
		loginPageWeb = new LoginPageWeb(driver);
		loginPageWeb.setEmailId(email);
		loginPageWeb.setPassword(pwd);
		bannerPageWeb = loginPageWeb.clickSignIn();
		return bannerPageWeb;
	}

	@Override
	public boolean verifyCofigurationPriority(String[] configurationList, int index)
	{
		ArrayList<String> requiredConfigSteps = new ArrayList<>();
		for (int i = 0; i <= index; i++)
		{
			requiredConfigSteps.add(configurationList[i].toLowerCase());
		}
		for (Map.Entry<String, Boolean> configureMap : configurationMap.entrySet())
		{

			// System.out.println("configurationList[" + index + "] = " +
			// configurationList[index]);
			// System.out.println("Key == " + configureMap.getKey());
			// System.out.println("Value == " + configureMap.getValue());

			if (configureMap.getValue() && !requiredConfigSteps.contains(configureMap.getKey().toLowerCase()))
			{
				return false;
			}

			if (configureMap.getKey().equalsIgnoreCase(configurationList[index]))
			{
				break;
			}

		}

		return true;
	}

	@Override
	public void addAttachment(String attachFrom, String siteName, String attachmentName)
	{
		findVisibleElement(attachmentModal, Speed.slow);
		String lowerCase = attachFrom.trim().toLowerCase();
		if (BannerLabels.BANNERPAGE_ADDATTACHMENT_RECENT.toLowerCase().equals(lowerCase))
		{
			addAttachmentFromRecentTab(siteName, attachmentName);
		}
		else if (BannerLabels.BANNERPAGE_ADDATTACHMENT_BROWSE.toLowerCase().equals(lowerCase))
		{
			addAttachmentFromBrowseTab(siteName, attachmentName);
		}
		else if (BannerLabels.BANNERPAGE_ADDATTACHMENT_UPLOAD.toLowerCase().equals(lowerCase))
		{
			addAttachmentFromUploadTab(attachmentName);
		}
		else if (BannerLabels.BANNERPAGE_ADDATTACHMENT_SEARCH.toLowerCase().equals(lowerCase))
		{
			addAttachmentFromSearchTab(siteName, attachmentName);
		}
	}

	@Override
	public void addAttachmentFromRecentTab(String site, String attachmentName)
	{
		findVisibleElement(attachment_RecentTab, Speed.slow, 5).click();
		if (!site.trim().isEmpty())
		{
			selectAttachmentSite(attachment_RecentTab_SiteDropDownButton, site);
		}
		By attachment = By.xpath("(//*[@id='activityList']//*[normalize-space(text())='" + attachmentName + "'])[1]");
		if (isDisplayed(attachment))
		{
			moveToElement(attachment);
			WebElement attachmentXpath = findVisibleElement(attachment, Speed.slow);
			attachmentXpath.click();
			findPresentElement(modalContentClosed, Speed.slow, 30);
		}
		else
		{
			System.err.println(attachmentName + " not found");
		}
	}

	@Override
	public void addAttachmentFromBrowseTab(String site, String attachmentName)
	{
		findVisibleElement(attachment_BrowseTab, Speed.slow, 5).click();
		if (!site.trim().isEmpty())
		{
			selectAttachmentSite(attachment_BrowseTab_SiteDropDownButton, site);
			if (isDisplayed(attachment_loaderBig))
			{
				while (isDisplayed(attachment_loaderBig))
				{
					;
				}
			}
			By attachment = By
					.xpath("//*[contains(@id,'browserTab_fileAndFolderFancyTree')]//*[normalize-space(text())='"
							+ attachmentName + "']/preceding-sibling::*[@class='fancytree-checkbox']");
			findVisibleElement(attachment_BrowseTab_ExpandAll).click();
			if (isDisplayed(attachment))
			{
				moveToElement(attachment);
				WebElement attachmentXpath = findVisibleElement(attachment, Speed.slow);
				attachmentXpath.click();
				findVisibleElement(attachment_attachButton).click();
				findPresentElement(modalContentClosed, Speed.slow, 30);
			}
			else
			{
				System.err.println(attachmentName + " not found");
			}
		}
		else
		{
			System.err.println("Site Name can not be empty");
		}
	}

	@Override
	public void addAttachmentFromUploadTab(String filePath)
	{
		findVisibleElement(attachment_UploadTab, Speed.slow, 5).click();
		uploadFile(attachment_UploadTab_browseInput, filePath);
		findVisibleElement(attachment_attachButton).click();
		findPresentElement(modalContentClosed, Speed.slow, 30);
	}

	@Override
	public void addAttachmentFromSearchTab(String site, String attachmentName)
	{
		findVisibleElement(attachment_SearchTab, Speed.slow, 5).click();
		if (!site.trim().isEmpty())
		{
			selectAttachmentSite(attachment_SearchTab_SiteDropDownButton, site);
		}
		findVisibleElement(attachment_SearchTab_searchBox, Speed.slow).clear();
		findVisibleElement(attachment_SearchTab_searchBox)
				.sendKeys(attachmentName.substring(0, attachmentName.indexOf(".")));
		while (isDisplayed(attachment_loader))
		{
			;
		}
		findVisibleElement(attachment_SearchTab_searchDataAllElements, Speed.slow, 30);
		By attachment = By.xpath("(//*[normalize-space(text())='" + attachmentName + "'])[last()]");
		if (isDisplayed(attachment))
		{
			moveToElement(attachment);
			WebElement attachmentXpath = findVisibleElement(attachment, Speed.slow);
			attachmentXpath.click();
			findPresentElement(modalContentClosed, Speed.slow, 30);
		}
	}

	@Override
	public void searchAttachmentSite(String site)
	{
		findVisibleElement(modalContent, Speed.slow, 5);
		WebElement txtSearch = findVisibleElement(attachment_siteSearch_searchInput, Speed.slow, 10);

		if (isDisplayed(attachment_siteSearch_clearSearchIcon))
		{
			WebElement clear = findVisibleElement(attachment_siteSearch_clearSearchIcon);
			clear.click();
		}
		txtSearch.clear();
		txtSearch.sendKeys(site);
		while (isDisplayed(attachment_loader))
		{
			;
		}

	}

	@Override
	public void selectAttachmentSite(By siteDropDown, String site)
	{
		findVisibleElement(siteDropDown).click();
		searchAttachmentSite(site);
		if (!isDisplayed(attachment_siteSearch_noResultsFound))
		{
			WebElement selectName = findVisibleElement(
					By.xpath("(//*[contains(@class,'dropdown open')]//*[normalize-space(text())='" + site.trim()
							+ "'])[last()]"));
			selectName.click();
		}
		else
		{
			System.err.println("No Results found");
		}
	}

	@Override
	public void uploadFile(By browseButton, String fileuploadpath)
	{
		try
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileuploadpath + "").getCanonicalPath()
					.trim();
			WebElement browseFile = findPresentElement(browseButton, 15, 200);
			browseFile.sendKeys(path);
			findVisibleElement(btnDone, 90, 200);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean verifyText(By elementObject, String textToBeVerified)
	{
		String elementText = findVisibleElement(elementObject, Speed.slow).getText().trim();
		return elementText.equals(textToBeVerified.trim());
	}

	@Override
	public boolean verifyNoMessages()
	{
		findVisibleElement(privateMessageModalOpened, Speed.slow);
		return isDisplayed(noMessages);
	}

	@Override
	public void clickOnNewMessages()
	{
		findVisibleElement(privateMessageModalOpened, Speed.slow);
		WebElement newMessageEle = findClickableElement(newMessageButton);
		newMessageEle.click();
	}

	@Override
	public void sendPrivateMessage(String message, String... recipients)
	{
		String[] userName;
		findVisibleElement(newMessageModalOpened, Speed.slow);
		WebElement newMessageRecipientsEle = findClickableElement(newMessageRecipients);
		for (String recipient : recipients)
		{
			userName = recipient.split("@");
			newMessageRecipientsEle.sendKeys(userName[0]);
			findClickableElement(newMessageSendButton, Speed.slow);
			selectOptionFromAutoSuggest(recipient);
		}
		WebElement messageEditorEle = findClickableElement(newMessageEditor);
		messageEditorEle.sendKeys(message);
		clickSendOnNewMessageModal();
		findPresentElement(newMessageModalClosed, Speed.slow);
	}

	@Override
	public void clickSendOnNewMessageModal()
	{
		findVisibleElement(newMessageModalOpened, Speed.slow);
		WebElement sendEle = findVisibleElement(newMessageSendButton);
		if (sendEle.isEnabled())
		{
			sendEle.click();
		}
		else
		{
			System.out.println("Send Button is Disabled. Add Recipients and message");
		}
		findPresentElement(newMessageModalClosed, Speed.slow);
	}

	@Override
	public boolean verifySeeAllMessagesEnabled()
	{
		findVisibleElement(privateMessageModalOpened, Speed.slow);
		WebElement seeAllMessagesEle = findClickableElement(seeAllMessagesLink);
		return seeAllMessagesEle.isEnabled();
	}

	@Override
	public void clickOnSeeAllMessages()
	{
		findVisibleElement(privateMessageModalOpened, Speed.slow);
		WebElement seeAllMessagesEle = findClickableElement(seeAllMessagesLink);
		seeAllMessagesEle.click();
	}

	@Override
	public boolean verifySeeAllMessagesSearchBox()
	{
		findVisibleElement(seeAllMessagesModalOpened, Speed.slow);
		return isDisplayed(seeAllMessagesModalSearchBox);
	}

	@Override
	public void searchPrivateMessage(String searchText)
	{
		findVisibleElement(seeAllMessagesModalOpened, Speed.slow);
		WebElement searchEle = findClickableElement(seeAllMessagesModalSearchBox);
		searchEle.clear();
		searchEle.sendKeys(searchText);
		searchEle.sendKeys(Keys.ENTER);
	}

	@Override
	public void clickSeeAllMessagesSearchDropDown()
	{
		findVisibleElement(seeAllMessagesModalOpened, Speed.slow);
		WebElement searchDropDownEle = findClickableElement(seeAllMessagesSearchDropDown);
		searchDropDownEle.click();
	}

	@Override
	public boolean verifySearch()
	{
		return isDisplayed(searchMessagePrivateMessageList, Speed.slow);
	}

	@Override
	public boolean verifySearchMessageFilterOption(String option)
	{
		findVisibleElement(seeAllMessagesSearchDropDownOpened, Speed.slow);
		String lowerCase = option.toLowerCase();
		if (BannerLabels.BANNERPAGE_MESSAGE_ACTIVE.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(searchMessageFilterBy_ACTIVE);
		}
		else if (BannerLabels.BANNERPAGE_MESSAGE_UNREAD.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(searchMessageFilterBy_UNREAD);
		}
		else if (BannerLabels.BANNERPAGE_MESSAGE_ARCHIVED.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(searchMessageFilterBy_ARCHIVED);
		}
		return false;
	}

	@Override
	public void selectSearchMessageFilterOption(String option)
	{
		findVisibleElement(seeAllMessagesSearchDropDownOpened, Speed.slow);
		String lowerCase = option.toLowerCase();
		if (BannerLabels.BANNERPAGE_MESSAGE_ACTIVE.toLowerCase().equals(lowerCase))
		{
			WebElement searchMessageFilterByActiveEle = findClickableElement(searchMessageFilterBy_ACTIVE);
			searchMessageFilterByActiveEle.click();
		}
		else if (BannerLabels.BANNERPAGE_MESSAGE_UNREAD.toLowerCase().equals(lowerCase))
		{
			WebElement searchMessageFilterByUnreadEle = findClickableElement(searchMessageFilterBy_UNREAD);
			searchMessageFilterByUnreadEle.click();
		}
		else if (BannerLabels.BANNERPAGE_MESSAGE_ARCHIVED.toLowerCase().equals(lowerCase))
		{
			WebElement searchMessageFilterByArchivedEle = findClickableElement(searchMessageFilterBy_ARCHIVED);
			searchMessageFilterByArchivedEle.click();
		}
		else
		{
			System.err.println("Enter Valid Filter Option");
		}
	}

	@Override
	public void selectOptionFromAutoSuggest(String option)
	{
		if (isDisplayed(share_recipientHover, Speed.slow))
		{
			List<WebElement> recipientList = driver.findElements(share_recipientHover);
			findVisibleElement(share_recipientHover, Speed.slow);
			for (WebElement elem : recipientList)
			{
				if (elem.getText().trim().equals(option.toLowerCase().trim()))
				{
					elem.click();
					break;
				}
			}
		}
	}

	@Override
	public void selectUserNameFromAutoSuggest(String option)
	{
		if (isDisplayed(share_recipientHover, Speed.slow))
		{
			WebElement recipientHover = findVisibleElement(share_UserNameHover, Speed.slow);
			if (recipientHover.getText().trim().equals(option.trim()))
			{
				recipientHover.click();
			}
		}
	}

	@Override
	public void setRestrictedPermissions(Map<String, Map<String, Boolean>> permissions) throws InterruptedException
	{
		WebElement restrictedPermissionRadioButtonEle = findClickableElement(restrictedPermissionRadioButton);
		restrictedPermissionRadioButtonEle.click();

		for (Map.Entry<String, Map<String, Boolean>> groups : permissions.entrySet())
		{
			String groupName = groups.getKey();
			findVisibleElement(By.xpath("(//*[contains(.,'" + groupName.trim() + "')])[last()]"));

			for (Map.Entry<String, Boolean> permission : groups.getValue().entrySet())
			{
				if (permission.getKey().equalsIgnoreCase("Edit"))
				{
					// WebElement editPermission =
					// findClickableElement(By.xpath(".//*[contains(text(),'" + groupName +
					// "')]//following::input[contains(@id,'editGroup')][1]"));
					WebElement editPermission = findClickableElement(
							By.xpath("//*[@title='Edit, " + groupName.trim() + "'][1]"));
					Boolean currentStatus = editPermission.isSelected();
					if (currentStatus != permission.getValue())
					{
						editPermission.click();
					}
				}
				else
				{
					// WebElement viewPermission =
					// findClickableElement(By.xpath(".//*[contains(text(),'" + groupName +
					// "')]//following::input[contains(@id,'viewGroup')][1]"));
					WebElement viewPermission = findClickableElement(
							By.xpath("//*[@title='View, " + groupName + "'][1]"));
					Boolean currentStatus = viewPermission.isSelected();
					if (currentStatus != permission.getValue())
					{
						viewPermission.click();
					}
				}
			}
		}
		clickOnSaveOnCategoryPermission();
	}

	@Override
	public void setDefaultPermissions()
	{
		WebElement defaultCategoryPermissionEle = findClickableElement(defaultPermissionRadioButton);
		defaultCategoryPermissionEle.click();
		clickOnSaveOnCategoryPermission();
	}

	@Override
	public void clickOnSaveOnCategoryPermission()
	{
		WebElement categoryPermissionSaveEle = findClickableElement(categoryPermissionSaveLink);
		categoryPermissionSaveEle.click();
	}

	/**
	 * Clean Downloads directory
	 */
	@Override
	public void cleanDownloadsFolder()
	{
		try
		{
			String downloadFolder = new File(TestBaseSetup.currentDir + "\\Downloads\\").getCanonicalPath().trim();
			File folderToClean = new File(downloadFolder);
			FileUtils.cleanDirectory(folderToClean);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get latest file from a directory
	 *
	 * @param dirPath
	 *        path of a directory
	 * @return last modified file
	 */
	@Override
	public File getLatestFilefromDir(String dirPath)
	{
		findPresentElement(breadCrumb, Speed.slow);
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0)
		{
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++)
		{
			if (lastModifiedFile.lastModified() < files[i].lastModified())
			{
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * Verify download file in Downloads folder
	 *
	 * @param fileName
	 *        with extension to verify
	 * @return true if successful.
	 */
	@Override
	public boolean verifyDownloadedFile(String fileName)
	{
		try
		{
			findPresentElement(breadCrumb, Speed.slow);
			String folderPath = new File(TestBaseSetup.currentDir + "\\Downloads\\").getCanonicalPath().trim();
			File latestFile = getLatestFilefromDir(folderPath);
			System.out.println("Last modified file from Downloads: " + latestFile.getName().trim());
			return latestFile.getName().trim().equals(fileName.trim());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void clickAdvancedSearchOption()
	{
		findVisibleElement(searchFilterOpened, Speed.slow, 5);
		WebElement globalSearchAdvanceSearchEle = findClickableElement(globalSearchAdvanceSearchLink);
		globalSearchAdvanceSearchEle.click();
		findVisibleElement(advanceSearchModalOpened, Speed.slow);
	}

	@Override
	public boolean verifyAdvanceSearchModalOpened()
	{
		return isDisplayed(advanceSearchModalOpened, Speed.slow);
	}

	@Override
	public void clickCancelOnAdvanceSearchModal()
	{
		findVisibleElement(advanceSearchModalOpened, Speed.slow);
		WebElement cancelEle = findClickableElement(advanceSearchModalCancelButton);
		cancelEle.click();
		findPresentElement(advanceSearchModalClosed, Speed.slow);
	}

	@Override
	public SearchContentWeb searchContentInGlobalSearch(String searchText)
	{
		WebElement searchEle = findClickableElement(searchTextBox);
		searchEle.sendKeys(searchText);
		searchEle.sendKeys(Keys.ENTER);
		return new SearchContentWeb(driver);
	}

	@Override
	public void selectSearchFilterOption(String filterOption)
	{
		findVisibleElement(searchFilterOpened, Speed.slow, 5);
		String lowerCase = filterOption.toLowerCase();
		if (BannerLabels.BANNERPAGE_SEARCHFILTEROPTION_ALL.toLowerCase().equals(lowerCase))
		{
			WebElement filterOptionAllEle = findClickableElement(globalSearchAllLink);
			filterOptionAllEle.click();
		}
		else if (BannerLabels.BANNERPAGE_SEARCHFILTEROPTION_PEOPLE.toLowerCase().equals(lowerCase))
		{
			WebElement filterOptionPeopleEle = findClickableElement(globalSearchPeopleLink);
			filterOptionPeopleEle.click();
		}
		else if (BannerLabels.BANNERPAGE_SEARCHFILTEROPTION_SITES.toLowerCase().equals(lowerCase))
		{
			WebElement filterOptionSitesEle = findClickableElement(globalSearchSiteLink);
			filterOptionSitesEle.click();
		}
		else if (BannerLabels.BANNERPAGE_SEARCHFILTEROPTION_CONTENT.toLowerCase().equals(lowerCase))
		{
			WebElement filterOptionContentEle = findClickableElement(globalSearchContentLink);
			filterOptionContentEle.click();
		}
		else
		{
			System.out.println("Select Valid Option");
		}
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public EventsWeb gotoEventModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(eventsModuleLink, Speed.slow);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		else
		{
			System.out.println("Event module not found");
		}
		return new EventsWeb(driver);
	}

	/**
	 * @param dropDownButton
	 *        xpath of the drop down button that has to be clicked
	 * @param dropDownBox
	 *        xpath of the drop down box
	 * @param optionList
	 *        String containing xpath of dropdown list element
	 * @param optionToSelect
	 *        Option to select from drop down
	 */
	@Override
	public void selectOptionFromDropDown(By dropDownButton, By dropDownBox, String optionList, String optionToSelect)
	{
		if (!isDisplayed(dropDownBox))
		{
			findVisibleElement(dropDownButton).click();
		}

		findPresentElement(dropDownBox);
		WebElement elem = findVisibleElement(
				By.xpath(optionList + "//*[normalize-space(text())='" + optionToSelect.trim() + "']"));
		elem.click();
	}

	public boolean verifyOptionFromDropDown(By dropDownButton, By dropDownBox, String optionList, String optionToSelect)

	{
		if (!isDisplayed(dropDownBox))
		{
			findVisibleElement(dropDownButton).click();
		}
		findPresentElement(dropDownBox);
		return isDisplayed(By.xpath(optionList + "//*[normalize-space(.)='" + optionToSelect.trim() + "']"));
	}

	@Override
	public void setNotification(String email, Boolean state)
	{
		clickOnSetNotificatons();
		findVisibleElement(notification_clearAllLink).click();
		By notificationEle = By
				.xpath("//*[normalize-space(text())='" + email.trim() + "']/ancestor::*[3]//*[@type='checkbox']");
		setSelection(notificationEle, state);
		findClickableElement(notification_setNotificationsButton).click();
	}

	@Override
	public void setNotificationForAll()
	{
		findVisibleElement(notification_selectAllLink).click();
		findVisibleElement(notification_setNotificationsButton).click();
	}

	@Override
	public void clickOnSetNotificatons()
	{
		WebElement setNotificationsEle = findClickableElement(setNotificationsLink);
		setNotificationsEle.click();
	}

	/**
	 * Check if no results found is displayed
	 *
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean noResultsFound()
	{
		return isDisplayed(noResultsFound, Speed.slow);
	}

	/**
	 * Verify recent message recieved in message box after opening first message
	 *
	 * @param userName
	 *        username to verify
	 * @param message
	 *        message to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyRecentMessageRecieved(String userName, String message, boolean capitalize)
	{
		openFirstMessage();
		By usernameXpath;
		if (capitalize)
		{
			usernameXpath = By.xpath(
					lastMessageContainer + "//*[normalize-space(text())='" + getUserData(userName.trim()) + "']");
		}
		else
		{
			usernameXpath = By.xpath(lastMessageContainer + "//*[normalize-space(text())='" + userName.trim() + "']");
		}
		By messageXpath = By.xpath(lastMessageContainer + "//*[normalize-space(text())='" + message.trim() + "']");
		if (isDisplayed(usernameXpath, Speed.slow) && isDisplayed(messageXpath))
		{
			findVisibleElement(usernameXpath);
			findVisibleElement(messageXpath);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Open first message from message notification window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void openFirstMessage()
	{
		findVisibleElement(firstMessageLink).click();
	}

	/**
	 * Click cancel to close message box
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCancelInMessageBox()
	{
		findVisibleElement(footer_Cancel).click();
		findPresentElement(modalContentClosed, Speed.slow);
	}

	/**
	 * Verify first message is recently recieved
	 *
	 * @return true if grey meta value is "Just now" or under "5" minutes;
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyFirstMessageIsRecentlyRecieved()
	{
		String greyMetaText = findVisibleElement(firstMessageGreyMeta, Speed.slow).getText().trim();
		if (greyMetaText.equalsIgnoreCase("just now"))
		{
			return true;
		}
		else if (greyMetaText.contains("minute"))
		{
			int minute = Integer.parseInt(greyMetaText.substring(0, greyMetaText.indexOf(" ")).trim());
			if (minute < 5)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean verifyFavouriteOrRecentlyViewedDisplayList()
	{
		return isDisplayed(bannerTopFavouriteDisplayList, Speed.slow);
	}

	@Override
	public void waitTillGlobalMessageDissappears()
	{
		while (isDisplayed(globalMessageContainer, Speed.slow))
		{
			;
		}
		findPresentElement(globalMessageContainerHidden, Speed.slow);
	}

	@Override
	public PeopleWeb gotoPeopleModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findClickableElement(peopleModuleLink, Speed.slow, 10);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		return new PeopleWeb(driver);
	}

	@Override
	public MyProfileWeb gotoMyProfile()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYPROFILE);
		return new MyProfileWeb(driver);
	}

	/**
	 * Click Email tab in opened window
	 */
	@Override
	public void gotoEmailTab()
	{
		findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(share_Email, Speed.slow).click();
	}

	/**
	 * Click Link tab in opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoLinkTab()
	{
		findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(share_Link, Speed.slow).click();
	}

	/**
	 * Click Message tab in opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoMessageTab()
	{
		findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(share_Message, Speed.slow).click();
	}

	/**
	 * Click Microblog tab in opened window
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void gotoMicroblogTab()
	{
		try
		{
			findVisibleElement(modalContent, Speed.slow);
			findVisibleElement(share_Microblog, Speed.slow).click();
		}
		catch (Exception e)
		{
			System.err.println("Microblog is disabled : enable it from Site Admin -> Advanced first.");
		}
	}

	/**
	 * Share content via e-mail(Share->Email).
	 *
	 * @param recipientMail
	 *        e-mail of the recipient.
	 * @param subject
	 *        mail subject.
	 * @param message
	 *        to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaEmail(String recipientMail, String subject, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_contentModal);
		gotoEmailTab();
		WebElement recipientInput = findVisibleElement(share_Email_recipientsInput);
		WebElement subjectInput = findVisibleElement(share_Email_subjectInput);
		WebElement messageInput = findVisibleElement(share_Email_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(getUserData(recipientMail));
		findVisibleElement(share_Send, Speed.slow);
		selectOptionFromAutoSuggest(recipientMail);
		subjectInput.clear();
		subjectInput.sendKeys(subject);
		if (!message.isEmpty() || message != null)
		{
			messageInput.clear();
			messageInput.sendKeys(message);
		}
	}

	/**
	 * Share content via message(Share->Message).
	 *
	 * @param recipientMail
	 *        e-mail of the recipient.
	 * @param message
	 *        to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaMessage(String recipientMail, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_contentModal);
		gotoMessageTab();
		WebElement recipientInput = findVisibleElement(share_Message_recipientsInput);
		WebElement messageInput = findVisibleElement(share_Message_messageInput);
		recipientInput.clear();
		recipientInput.sendKeys(getUserData(recipientMail));
		findVisibleElement(share_Send, Speed.slow);
		selectOptionFromAutoSuggest(recipientMail);
		messageInput.clear();
		messageInput.sendKeys(message);
	}

	/**
	 * Copy share URL(Share->Link)
	 *
	 * @param shortUrl
	 *        true - check Use short URL checkbox false - uncheck Use short URL
	 *        checkbox
	 * @author dheeraj.rajput
	 */
	@Override
	public void copyShareLink(boolean shortUrl)
	{
		if (!isDisplayed(modalContent, Speed.slow))
		{
			selectOptionInMoreAction("Share");
		}
		findVisibleElement(share_Link, Speed.slow).click();
		WebElement shortURL = findVisibleElement(share_Link_UseShortURLCheckbox);
		boolean selected = shortURL.isSelected();
		if (selected != shortUrl)
		{
			shortURL.click();
		}
		findVisibleElement(share_Link_SelectLinkButton, Speed.slow).click();
		findVisibleElement(share_Link_URLInput).sendKeys(Keys.chord(Keys.CONTROL, "c"));
	}

	@Override
	public void clickSendInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Send).click();
			findPresentElement(modalContent, Speed.slow);
			if (isDisplayed(share_SentSuccessfully, Speed.slow))
			{
				findVisibleElement(share_SentSuccessfully);
				findVisibleElement(share_Close).click();
			}
			findPresentElement(modalInvisible, Speed.slow);
		}
	}

	/**
	 * Open copied URL (Always use it after copyShareLink(boolean shortUrl))
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void openCopiedURL() throws UnsupportedFlavorException, IOException
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String url = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.println("URL: " + url);
		driver.get(url);
	}

	/**
	 * To Select more action options in selected wiki page
	 */
	@Override
	public void selectOptionInMoreAction(String option)
	{
		clickOnMoreAction();
		findClickableElement(
				By.xpath("(//*[contains(@class,'dropdown-menu pull-right')])[last()]//*[normalize-space(text())='"
						+ option + "']")).click();
	}

	@Override

	public void clickOnMoreAction()
	{
		findClickableElement(moreActionMenuLinkInModel, Speed.slow).click();
	}

	/**
	 * Get text of an element
	 *
	 * @Updated 24 May 2018 (@author dheeraj.rajput) to handle Input element
	 */
	@Override
	public String getText(By element)
	{
		String elemText;
		WebElement elementForGetText = findVisibleElement(element);
		try
		{
			elemText = ((JavascriptExecutor) driver).executeScript("return arguments[0].value", elementForGetText)
					.toString().trim();
		}
		catch (Exception e)
		{
			elemText = elementForGetText.getText().trim();
		}
		return elemText;
	}

	@Override
	public boolean verifyEmptyRecipent()
	{
		return !isDisplayed(share_recepent, Speed.slow);
	}

	@Override
	public boolean disabledSendButtonInShareDialogueBox()
	{
		findVisibleElement(share_disabled_Send);
		return isDisplayed(share_disabled_Send, Speed.slow);
	}

	@Override
	public String getSubjectFromShareModel()
	{
		return getText(share_Email_subjectInput);
	}

	/**
	 * Verify default subject in Share Modal present or not
	 *
	 * @return true if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultSubjectIsPresent()
	{
		return verifyInputTextIsPresent(share_Email_subjectInput);
	}

	/**
	 * Click on Post button in Share modal
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickPostInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Post).click();
			findPresentElement(modalInvisible, Speed.slow);
		}
	}

	/**
	 * Verify input element has default text or not
	 *
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyInputTextIsPresent(By elementXpath)
	{
		WebElement elem = findVisibleElement(elementXpath, Speed.slow);
		String elemText = "";
		try
		{
			elemText = ((JavascriptExecutor) driver).executeScript("return arguments[0].value", elem).toString();
		}
		catch (Exception e)
		{
			elemText = elem.getText();
		}
		if (elemText.isEmpty() || elemText.equals(null))
		{
			return false;
		}
		return true;
	}

	/**
	 * Click on Cancel button in Share modal
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickCancelInShareModal()
	{
		if (isDisplayed(modalContent))
		{
			findVisibleElement(share_Cancel).click();
			findPresentElement(modalInvisible, Speed.slow);
		}
		else
		{
			System.err.println("Share modal is not open");
		}
	}

	/**
	 * Gets the start date time stamp.
	 *
	 * @return the start date time stamp
	 */
	@Override
	public Timestamp getStartDateTimeStamp()
	{
		Timestamp timeStamp = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try
		{
			Object value = getResult(GET_TIME_STAMP_QUERY);
			System.out.println("----> " + value.toString());
			if (value != null)
			{
				timeStamp = new java.sql.Timestamp(sdf.parse(value.toString()).getTime());
			}
			Thread.sleep(1000);
		}
		catch (Exception e)
		{

		}

		return timeStamp;
	}

	/**
	 * Gets the end date time stamp.
	 *
	 * @return the end date time stamp
	 * @throws Exception
	 *         the exception
	 */
	@Override
	public Timestamp getEndDateTimeStamp()
	{
		return new Timestamp(getStartDateTimeStamp().getTime() + ADD_TIME_DELAY);
	}

	/**
	 * @param sql
	 *        Query
	 * @param all
	 *        Dynamic parameters which are expected in Query
	 * @return value From First column and First Row
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public synchronized Object getResult(String sql, Object... params)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try
		{
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
			{
				pstmt.setObject(i + 1, params[i]);
			}
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				return rs.getObject(1);
			}
			else
			{
				return null;
			}
		}
		catch (SQLException se)
		{
			System.err.println("SQLException accured in getResult() method:" + se);

		}
		catch (Exception e)
		{
			// logger.error(null, e);

		}
		finally
		{
			DBConnection.closeDBconnection(con);
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// logger.error(null, e);
				}
			}
		}
		return null;
	}

	/**
	 * @param sql
	 *        Query
	 * @return value From First column and First Row
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public synchronized Object getResult(String sql)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try
		{
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				return rs.getObject(1);
			}
			else
			{
				return null;
			}
		}
		catch (SQLException se)
		{
			System.err.println("SQLException accured in getResult() method:" + se);

		}
		catch (Exception e)
		{
			// logger.error(null, e);

		}
		finally
		{
			DBConnection.closeDBconnection(con);
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch (SQLException e)
				{
					// logger.error(null, e);
				}
			}
		}
		return null;
	}

	@Override
	public boolean verifyMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject,
			String emailMessage)
	{
		String query;
		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
		{
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'"
					+ startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		else
		{
			query = "select TOP 1 * from Email where mailsubject = '" + emailSubject.trim() + "' and mailto = '"
					+ mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'"
					+ " order by id DESC";
		}
		System.out.println(query);
		mailContent = getMailContent(query);
		if (mailContent.isEmpty() || mailContent == null)
		{
			return false;
		}
		else
		{
			createHtmlFile(mailHtmlFile, mailContent);
			getLocalHtmlPage(mailHtmlFile);
			mailto = mailto.substring(0, mailto.indexOf("@"));
			if (verifyContent(mailto, emailMessage))
			{
				System.out.println(emailMessage + " is present");
				closeCurrentTab();
				return true;
			}
		}
		return false;
	}

	@Override
	public String getMailContent(String mailQuery)
	{
		System.out.println("Get mail Content Query : " + mailQuery);
		String mailBody = "";
		try
		{
			List<Map<String, Object>> rsMap = dbManager.getResultSet(mailQuery);
			if (!rsMap.isEmpty())
			{
				int size = rsMap.size() - 1;
				mailBody = (String) rsMap.get(size).get("htmlbody");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return mailBody;
	}

	@Override
	public void createHtmlFile(String fileName, String fileContent)
	{
		try
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileName + "").getCanonicalPath().trim();
			File file = new File(path);
			// If file does not exist, create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileContent);
			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void getLocalHtmlPage(String fileName)
	{
		try
		{
			String path = new File(TestBaseSetup.currentDir + "\\testData\\" + fileName + "").getCanonicalPath().trim();
			// findPresentElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL +
			// "t"));
			((JavascriptExecutor) driver).executeScript("window.open()");
			Set<String> highQCollaborateWindows = driver.getWindowHandles();
			parentWindow = (String) highQCollaborateWindows.toArray()[0];
			childWindow = (String) highQCollaborateWindows.toArray()[1];
			driver.switchTo().window(childWindow);
			driver.get(path);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyContent(String mailto, String content)
	{
		String mailId;
		if (getUserData(mailto).contains(" "))
		{
			mailId = getUserData(mailto).split(" ")[0];
		}
		else
		{
			mailId = getUserData(mailto);
		}

		findVisibleElement(body, Speed.slow);
		By contentBody = By.xpath(".//body[contains(.,'" + mailId.trim() + "') and contains(.,'" + content + "')]");
		return isDisplayed(contentBody);
	}

	@Override
	public void closeCurrentTab()
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow).close();
		driver.switchTo().window(parentWindow);
	}

	/**
	 * Verify default message in Share Modal present or not
	 *
	 * @return true if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultMessageIsPresent()
	{
		return verifyInputTextIsPresent(share_Microblog_messageInput);
	}

	/**
	 * Verify default microblog share with site name matches with expected site name
	 *
	 * @param siteName
	 *        to verify.
	 * @return true if successful, false otherwise
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyDefaultMicroblogSite(String siteName)
	{
		WebElement shareWith = findVisibleElement(share_Microblog_selectedSite);
		return shareWith.getText().trim().equals(siteName.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param element
	 * @return
	 * @creation date 23/01/2018
	 */
	@Override
	public boolean verifyFavouritesElementInMyFavourites(String element)
	{
		clickOnFavourite();
		return (isDisplayed(By.xpath(
				"(//*[@id='bannertopfavouritedisplaylist']//*[normalize-space(text())='" + element.trim() + "'])[1]"),
				Speed.slow));
	}

	/**
	 * @author vivek.mishra Click on Posts tab in Activity module
	 * @creation date 09/02/2018
	 */
	@Override
	public void gotoPosts()
	{
		WebElement element = findVisibleElement(recentActivity_Posts, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra To switch between window
	 * @creation date 08/02/2018
	 */
	@Override
	public void switchWindow()
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
	}

	/**
	 * @param userId
	 *        user id
	 * @param userDomain
	 *        user domain
	 * @author badal.gandhi
	 */
	@Override
	public void setUserListForResetPassword(String userId, String userDomain)
	{
		String query;
		query = "select count(*) from userpasswordhistory where userid = (select userid from users where email = '"
				+ userId.toLowerCase().trim()
				+ "' and orgid = (select orgid from organisationdomain where domainname = '" + userDomain.toLowerCase()
				+ "') )";

		int res = (Integer) getResult(query);
		if (res < 1)
		{
			resetUserPassword.add(userId.concat("@" + userDomain).toLowerCase().trim());
		}

	}

	/**
	 * @return
	 * @author nidhi.shah
	 */
	@Override
	public boolean verifyHubConfiguration()
	{
		WebElement inptHubURL = findVisibleElement(locInptHubURL);
		if (inptHubURL.getAttribute("value").equalsIgnoreCase(hubURL))
		{
			WebElement registerInstance = findVisibleElement(locLnkRegisterInstance);
			registerInstance.click();
			if (isAlertPresent())
			{
				driver.switchTo().alert().accept();
			}
			if (isAlertPresent())
			{
				String alertMessage = driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();
				return alertMessage.equalsIgnoreCase(instanceRegisterMessage);
			}
		}
		return false;
	}

	/**
	 * @return
	 * @author nidhi.shah]
	 */
	@Override
	public boolean configureHub()
	{
		return false;
	}

	/**
	 * @return
	 * @author nidhi.shah
	 */
	@Override
	public boolean verifyMicroserviceConfiguration()
	{
		return true;
	}

	/**
	 * @return
	 * @author nidhi.shah
	 */
	@Override
	public boolean configureMicroService()
	{
		return false;
	}

	/**
	 * @author vivek.mishra
	 * @creation date 15/02/2018
	 */
	@Override
	public BlogWeb gotoBlogModule()
	{
		waitTillGlobalMessageDissappears();
		WebElement moreOptionselement = findVisibleElement(blogModuleLink, Speed.slow);
		if (moreOptionselement.isDisplayed() && moreOptionselement.isEnabled())
		{
			moreOptionselement.click();
		}
		return new BlogWeb(driver);
	}

	/**
	 * @return true if search result is present false if no result found
	 * @author badal.gandhi
	 */
	@Override
	public boolean searchInFavourite(String searchText)
	{
		WebElement searchBox = findVisibleElement(favouriteSearchBox);
		if (searchBox.isEnabled())
		{
			searchBox.clear();
			searchBox.sendKeys(searchText);

			By searchResult = By.xpath("//*[@id='bannertopfavouritedisplaylist']//child::*[normalize-space(text())='"
					+ searchText.trim() + "']");
			findPresentElement(bannerTopFavouriteDisplayList, Speed.slow);

			return isDisplayed(searchResult, Speed.slow);
		}
		else
		{
			return false;
		}
	}

	/**
	 * @return object of task module
	 * @author badal.gandhi
	 */
	@Override
	public TasksWeb goToMyTasks()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYTASKS);
		return new TasksWeb(driver);
	}

	/**
	 * @return object of file module
	 * @author badal.gandhi
	 */
	@Override
	public MyFilesWeb goToMyFiles()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYFILES);
		return new MyFilesWeb(driver);
	}

	/**
	 * used to click on HighQ Hub from User Avtar
	 *
	 * @return object of dashboardPage
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public DashboardWeb gotoHighQHub()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HIGHQHUB);
		return new DashboardWeb(driver);
	}

	/**
	 * used to click on Logout from User Avtar
	 *
	 * @return object of dashboardPage
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public DashboardWeb gotoLogout()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_LOGOUT);
		return new DashboardWeb(driver);
	}

	/**
	 * used to click on Help from User Avtar
	 *
	 * @return object of dashboardPage
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public DashboardWeb gotoHelp()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HELP);
		return new DashboardWeb(driver);
	}

	/**
	 * used to verify Highq Hub link is visible or not returns true if successful,
	 * false otherwise
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyHighqHubLink()
	{

		return isDisplayed(highqHubLink);
	}

	/**
	 * used to verify Logout From system
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyUserLogoutFromSystem()
	{
		return isDisplayed(emailIdTextBox);
	}

	/**
	 * used to verify logout OK Button
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyOkButton()
	{

		return isDisplayed(footer_Ok);
	}

	/**
	 * used to verify logout Cancel Button
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public boolean verifyCancelButton()
	{

		return isDisplayed(footer_Cancel);
	}

	/**
	 * used to verify logout Message
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyLogoutMessage(String message)
	{

		WebElement logoutMsg = findVisibleElement(logoutButton);
		return logoutMsg.getText().trim().equals(logoutMessage.trim());

	}

	/**
	 * used to verify help page is Displayed or not
	 *
	 * @return true if successful, false otherwise
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public boolean verifyHelpPage()
	{
		findVisibleElement(avtarHelpPage, Speed.slow);
		return isDisplayed(avtarHelpPage, Speed.slow);
	}

	/**
	 * used click on the Back Button of browser
	 *
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public void browserBackNavigate()
	{
		driver.navigate().back();
	}

	@Override
	public DashboardWeb gotoInstallHighQDriveLink()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashboardWeb gotoInstallOfficePluginLink()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * used to click on My Draft from User Avtar
	 *
	 * @return object of drafPage
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public MyDraftWeb goToMyDrafts()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYDRAFTS);
		return new MyDraftWeb(driver);
	}

	/**
	 * used to click on Send A File from User Avtar
	 *
	 * @return object of dashboardPage
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public DashboardWeb goToSendAFile()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SENDAFILE);
		return new DashboardWeb(driver);
	}

	/**
	 * used to click on Approval Workflow from User Avtar
	 *
	 * @return object of ApprovalWorkflow Page
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */

	@Override
	public ApprovalWorkflowWeb gotoapprovalWorkflow()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_APPROVALWORKFLOW);
		return new ApprovalWorkflowWeb(driver);
	}

	/**
	 * used to click on Setting from User Avtar
	 *
	 * @return object of Settings Page
	 * @author khushbu.dhandhukiya
	 * @creation date 17/04/2018
	 */
	@Override
	public SettingsWeb gotoSettings()
	{
		clickOnUserAvtar();
		clickOnUserAvtarOptions(BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SETTINGS);
		return new SettingsWeb(driver);
	}

	/**
	 * @author vivek.mishra
	 * @return the today's date in dd MMM yyyy formate
	 * @created on 17/04/2018
	 */
	@Override
	public String getCurrentDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	public boolean isSelected(By by)
	{
		try
		{
			if (isDisplayed(by, Speed.slow))
			{
				return driver.findElement(by).isSelected();
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * @author Anil.patidar Created : 11th April 2018 Updated :
	 */
	@Override
	public String getCustomDateValue(int days, String format)
	{
		SimpleDateFormat formattedDate = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);

		return formattedDate.format(c.getTime());

	}

	/**
	 * @author jyoti.raj Create : 12th April 2018 Updated :
	 * @param dateAndTimeFormat
	 * @return
	 */
	@Override
	public String getDateAndSystemTime(String dateAndTimeFormat)
	{
		DateFormat dateFormat = new SimpleDateFormat(dateAndTimeFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * @author vivek.mishra date in MMM/dd/yyyy formate To select the date in
	 *         currently open calender modal date in MMM/dd/yyyy formate To select
	 *         the date in currently open calender modal
	 * @created on 19/04/2018
	 */
	@Override
	public void setDateInCalenderModal(String date)
	{
		String actualDay, actualMonth;
		int actualYear;

		String[] date1 = date.split("/");
		actualYear = Integer.parseInt(date1[2].trim());
		actualDay = date1[1].trim();
		actualMonth = date1[0].trim();

		if (!(actualYear == getCurrentYearInCalenderModal()
				&& actualMonth.contains(getCurrentMonthInCalenderModal())))
		{
			clickOnYearMonthPickerInCalenderModal();
			boolean result = true;
			while (result)
			{
				int yearDiff = actualYear - getCurrentYearFromMonthPicker();
				if (yearDiff > 0)
				{

					clickOnNextInMonthPicker();
				}
				else if (yearDiff < 0)
				{
					clickOnPreviousInMonthPicker();
				}
				else
				{
					result = false;
				}
			}
			clickOnMonthInMonthPicker(actualMonth);
		}
		findVisibleElement(By.xpath(
				"//*[contains(@class,'picker-open')]//*[@class='datepicker-days']//*[not(contains(@class,'day old')) and normalize-space()='"
						+ actualDay.trim() + "']"),
				Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return To get the current year from the calender To get the current year
	 *         from the calender
	 * @created on 19/04/2018
	 */
	@Override
	public int getCurrentYearInCalenderModal()
	{
		return (Integer
				.parseInt(findVisibleElement(currentMonthYearInCalender, Speed.slow).getText().split(" ")[1].trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return To get the current Month from the calender To get the current Month
	 *         from the calender
	 * @created on 19/04/2018
	 */
	@Override
	public String getCurrentMonthInCalenderModal()
	{
		return (findVisibleElement(currentMonthYearInCalender, Speed.slow).getText().split(" ")[0].trim());
	}

	/**
	 * @author vivek.mishra To click on year month column in calender modal
	 * @created on 19/04/2018
	 */
	@Override
	public void clickOnYearMonthPickerInCalenderModal()
	{
		findVisibleElement(currentMonthYearInCalender, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return To get the current year from the month picker in calender
	 * @created on 19/04/2018
	 */
	@Override
	public int getCurrentYearFromMonthPicker()
	{
		return (Integer.parseInt(findVisibleElement(currentYearInMonthPicker, Speed.slow).getText().trim()));
	}

	/**
	 * @author vivek.mishra To click on next button in month picker in calender
	 * @created on 19/04/2018
	 */
	@Override
	public void clickOnNextInMonthPicker()
	{
		findVisibleElement(nextYearButtonInMonthPicker).click();
	}

	/**
	 * @author vivek.mishra To click on prev button in month picker in calender
	 * @created on 19/04/2018
	 */
	@Override
	public void clickOnPreviousInMonthPicker()
	{
		findVisibleElement(previousYearButtonInMonthPicker).click();
	}

	/**
	 * @author vivek.mishra To select the particular month from month picker in
	 *         calender
	 * @created on 19/04/2018
	 */
	@Override
	public void clickOnMonthInMonthPicker(String month)
	{
		findVisibleElement(By.xpath(
				"//*[contains(@class,'picker-open')]//*[@class='datepicker-months']//*[not(@class='month disabled') and normalize-space(text())='"
						+ getUserData(month.trim()) + "']"),
				Speed.slow).click();
	}

	/**
	 * Select option from dropdown if it is not the default option(Mainly for ASP
	 * and System admin)
	 *
	 * @param configurationName
	 *        By variable containing xpath of label for which option has to be
	 *        selected
	 * @param configurationDropDown
	 *        By variable containing xpath of Select option
	 * @param configurationCheckbox
	 *        By variable containing xpath of the default checkbox
	 * @param optionValue
	 *        String value to select from drop down
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectConfiguration(By configurationName, By configurationDropDown, By configurationCheckbox,
			String optionValue)
	{
		Select configurationDrpDwn = new Select(findVisibleElement(configurationDropDown, Speed.slow));
		String selectedValue = configurationDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement configurationChkbox = findVisibleElement(configurationCheckbox, Speed.slow);
			if (configurationChkbox.isSelected())
			{
				configurationChkbox.click();
			}
			configurationDrpDwn.selectByVisibleText(optionValue.trim());
		}
	}

	/**
	 * Click on attached link in mail content body
	 *
	 * @author dheeraj.rajput
	 * @Created 26 April 2018
	 * @Updated
	 */
	@Override
	public void clickOnLinkInMailContentMessageBody()
	{
		findVisibleElement(body, Speed.slow);
		WebElement elem = driver.findElement(linkInMailContent);
		elem.click();
	}

	/**
	 * @author ankit.motaval
	 * @param userId
	 * @param userDomain
	 *        Get User ID from DB
	 * @created on 11/04/2018
	 */
	@Override
	public int getUserID(String userId, String userDomain)
	{
		String query = "select u.userid from users u inner join organisationdomain orgdomain on u.emaildomain = orgdomain.id where u.email='"
				+ userId + "' and orgdomain.domainname='" + userDomain + "'";
		return (Integer) getResult(query);
	}

	/**
	 * @author tejash.trivedi Created on 23 May 2018 Get User's Organmisation Name
	 *         by Email
	 * @param userId
	 * @return
	 */
	@Override
	public String getUserOrgName(String userEmail)
	{
		String[] userName = userEmail.split("@");
		// String query = "select name from organisations where emailsuffix ='" +
		// userName[1] + "'";

		String query = "select name from organisations where orgid=(select orgid from organisationdomain where domainname='"
				+ userName[1] + "')";
		return (String) getResult(query);
	}

	/**
	 * @author tejash.trivedi Created on 23 May 2018 Get UserName from Email
	 * @param userEmail
	 * @return
	 */
	public String getUserName(String userEmail)
	{
		String query = "select fullName from Griffin_view_userDetail where email ='" + userEmail + "'";
		return (String) getResult(query);
	}

	@Override
	public boolean verifyAlertMessage(String message)
	{
		String alertMessage = driver.switchTo().alert().getText().trim();
		driver.switchTo().alert().accept();
		return alertMessage.equals(message.trim());
	}

	/**
	 * @author ankit.motaval Accept Browser Alert
	 * @created on 11/04/2018
	 */
	@Override
	public void acceptAlert()
	{
		if (isAlertPresent())
		{
			driver.switchTo().alert().accept();
		}
	}

	/**
	 * @author ankit.motaval verify contact us email from footer
	 * @created on 09/04/2018
	 */
	@Override
	public boolean verifyContactusEmailFromFooter(String email)
	{
		String contentText = findVisibleElement(contactus, Speed.slow).getAttribute("href");
		return contentText.substring(7).equals(email);
	}

	/**
	 * @author ankit.motaval verify contact us link visible
	 * @created on 09/04/2018
	 */
	@Override
	public boolean isContactUslinkVisible()
	{
		return isDisplayed(contactus, Speed.slow);
	}

	/**
	 * @author ankit.motaval verify contact us Label
	 * @created on 09/04/2018
	 */
	@Override
	public boolean verifyContactUsLabel(String labelName)
	{
		String contentText = findVisibleElement(contactus, Speed.slow).getText();
		return labelName.equals(contentText);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void closeSaveChangesMessage()
	{
		if (isDisplayed(closeButtonSaveChangesMessage, Speed.slow))
		{
			findVisibleElement(closeButtonSaveChangesMessage).click();
		}
		findVisibleElement(breadCrumb);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public DashboardWeb gotoDashboardByClickOnLogo()
	{
		waitTillGlobalMessageDissappears();
		WebElement ele = findVisibleElement(logoImageButton, Speed.slow, 20);
		ele.click();
		return new DashboardWeb(driver);
	}

	@Override
	public void callStoreProcedure(String spName, String params) throws Exception
	{
		Connection connection = null;
		CallableStatement callableStatement = null;
		try
		{
			String queryStr = "{call " + spName + "(" + params + ")}";
			connection = DBConnection.getConnection();
			callableStatement = connection.prepareCall(queryStr);
			callableStatement.execute();
		}

		finally
		{
			DBConnection.closeDBconnection(connection);
			if (callableStatement != null)
			{

				try
				{
					callableStatement.close();
				}
				catch (SQLException e)
				{

				}
			}
		}
	}

	/**
	 * Click on attached link in mail content body
	 *
	 * @author dheeraj.rajput
	 * @Created 26 April 2018
	 * @Updated
	 */
	@Override
	public String gethashValueOfColor(By xpath, String cssProperty)
	{
		String color = driver.findElement(xpath).getCssValue(cssProperty);
		System.out.println(color);
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		System.out.println(actualColor);
		return actualColor;
	}

	/**
	 * Click on last message attached link
	 *
	 * @author dheeraj.rajput
	 * @Created 26 April 2018
	 * @Updated
	 * @author jyoti.raj Create : 26th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public void clickOnLastMessageLinkInMessageContainer()
	{
		findVisibleElement(linkInLastMessage, Speed.slow).click();
		switchWindow();
	}

	/**
	 * @author jyoti.raj Create : 26th April 2018 Updated :
	 * @param date
	 * @return
	 */
	@Override
	public Date addDays(Date date, int days)
	{

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/*
	 * @author jyoti.raj Create : 16th May 2018 Updated : (non-Javadoc)
	 * @see com.highq.pageobjects.base.BannerPage#switchToNextTab(java.lang.String)
	 */
	@Override
	public void switchToNextTab(String url)
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.get(url);
	}

	/*
	 * @author jyoti.raj Create : 16th May 2018 Updated : (non-Javadoc)
	 * @see com.highq.pageobjects.base.BannerPage#switchToPreviosTab()
	 */
	@Override
	public void switchToPreviosTab()
	{
		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
	}

	/*
	 * @author Ravi Pandit select option from dropdown Select Input
	 */
	public void selectOptionFromDropDown(By dropDownBox, String optionToSelect)
	{
		findVisibleElement(dropDownBox);
		Select fruits = new Select(driver.findElement(dropDownBox));
		fruits.selectByVisibleText(optionToSelect);

	}

	/**
	 * @param ModuleName
	 * @return
	 * @author ravi.pandit Verify Custom Module Name With Feature Custom
	 *         SiteNavigation
	 */
	@Override
	public boolean verifyModuleName(String moduleName)
	{
		return isDisplayed(By.xpath("//*[contains(@class,'mainMenu')]//a[normalize-space(.)='" + moduleName + "']"));
	}

	/**
	 * @param moduleName
	 * @author ravi.pandit
	 * @return Click on Custom site Navigation Applied site Module Name
	 */
	@Override
	public BannerPageWeb clickOnModuleName(String moduleName)
	{
		findClickableElement(By.xpath("//*[contains(@class,'mainMenu')]//a[normalize-space(.)='" + moduleName + "']"))
				.click();
		return new BannerPageWeb();
	}

	/**
	 * @param containerName
	 * @author ravi.pandit For Click on Container on SitePage With Feature Custom
	 *         SiteNavigation
	 */
	@Override
	public void clickContainer(String containerName)
	{
		findClickableElement(
				By.xpath("//*[contains(@class,'mainMenu')]//a[normalize-space(.)='" + containerName + "']")).click();
	}

	/**
	 * @param containerName
	 * @param subItemName
	 * @return
	 * @author ravi.pandit Verify Custom Sub Item Menu Name With Feature Custom
	 *         SiteNavigation
	 */
	@Override
	public boolean verifySubMenuItem(String containerName, String subItemName)
	{
		return isDisplayed(By.xpath("//*[contains(@class,'mainMenu')]//a[normalize-space(.)='" + containerName
				+ "' and @aria-expanded='true']//following::ul[1]//a[normalize-space(.)='" + subItemName + "']"));
	}

	/**
	 * @author surbhi.khetan
	 * @param filename
	 *        verify default value in share with
	 * @creation date 23/04/2018
	 */
	@Override
	public boolean verifyLastFavoriteContentAccessed(String filename)
	{

		String elementText = findVisibleElement(favoriteItem, Speed.slow).getText();
		return elementText.trim().equals(filename.trim());
	}

	/**
	 * @author surbhi.khetan verify download page after link is copied when shared
	 *         with copy link
	 * @creation date 24/04/2018
	 */
	@Override
	public boolean verifyDownloadPage()
	{

		return isDisplayed(download);
	}

	/**
	 * @author surbhi.khetan click On download button after copied link is hit
	 * @creation date 24/04/2018
	 */
	@Override
	public void clickOnDownlaod()
	{

		findClickableElement(download, Speed.slow).click();
		while (isDisplayed(loader, Speed.slow))
		{
			;
		}

	}

	/**
	 * @author surbhi.khetan click On link from messages - banner after file is
	 *         shared with the user
	 * @creation date 30/04/2018
	 */
	@Override
	public void clickOnReceivedLink()
	{

		findVisibleElement(receivedLink, Speed.slow).click();

	}

	/**
	 * @author surbhi.khetan Check if file type is folder in the received link
	 * @creation date 01/05/2018
	 */
	@Override
	public Boolean verifyFolderInLink()
	{
		return isDisplayed(folder);
	}

	/**
	 * Click on attached link in mail content body
	 *
	 * @author ankit.motaval
	 * @Created 10 May 2018
	 * @Updated
	 */
	@Override
	public void clickOnLinkInMailContentMessageBody(String link)
	{
		findVisibleElement(body, Speed.slow);
		WebElement elem = driver.findElement(By.xpath(".//a[normalize-space(text())='" + link + "']"));
		elem.click();
	}

	/**
	 * Verify disclaimer
	 *
	 * @param disclaimer
	 *        to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyAndAcceptDisclaimer(String disclaimer)
	{
		findVisibleElement(modalContent, Speed.slow);
		WebElement disclaimerBody = findVisibleElement(disclaimerText, Speed.slow);
		if (disclaimerBody.getText().trim().equals(disclaimer.trim()))
		{
			findVisibleElement(footer_Accept, Speed.slow).click();
			return true;
		}
		return false;
	}

	/**
	 * Verify file title in file Preview page
	 *
	 * @param fileTitle
	 *        to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyPreviewFileTitle(String fileTitle)
	{
		// while (isDisplayed(previewPage_FileLoader));
		findVisibleElement(previewPageHeader_CloseIcon, Speed.slow);
		String fetchedTitle = findVisibleElement(previewPageHeader_FileName, Speed.slow).getText().trim();
		return fetchedTitle.equals(fileTitle.trim());
	}

	/**
	 * Verify tag in file Preview page
	 *
	 * @param tag
	 *        tag name to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyPreviewTag(String tag)
	{
		expandPreviewRightPanel();
		findVisibleElement(previewPage_headerDetails, Speed.slow);
		int size = driver.findElements(By.xpath(PREVIEWPAGE_TOTALTAGS)).size();
		for (int i = 1; i <= size; i++)
		{
			By tagXpath = By.xpath(PREVIEWPAGE_TOTALTAGS + "[" + i + "]");
			if (isDisplayed(tagXpath))
			{
				WebElement elem = findVisibleElement(tagXpath);
				return elem.getText().trim().equalsIgnoreCase(tag.trim());
			}
		}
		return false;
	}

	/**
	 * Expand preview page right Panel
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void expandPreviewRightPanel()
	{
		if (isDisplayed(previewPage_Loader))
		{
			while (isDisplayed(previewPage_Loader))
			{
				;
			}
		}
		if (isDisplayed(previewPage_ShowArrow))
		{
			findVisibleElement(previewPage_ShowArrow).click();
		}
	}

	/**
	 * @author ganga.charan
	 * @param Note
	 *        verify Note version in preview document
	 */
	@Override
	public boolean verifyVersionNote(String metaDataNote)
	{
		return (findVisibleElement(versionNote, Speed.slow).getText().trim().equals(metaDataNote.trim()));
	}

	/**
	 * @author ganga.charan
	 * @param TaskName
	 *        verify task with document
	 */
	@Override
	public boolean verifyMetaDataTaskWithDocument(String metaDataTaskName)
	{
		return isDisplayed(
				By.xpath(DIALOGBODY + "//*[@id='file_module_fileInfoCommentContainerID']//*[normalize-space(text())='"
						+ metaDataTaskName.trim() + "']"),
				Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @return the file prview page availability
	 * @cretaed on 14/05/2018
	 */
	@Override
	public boolean verifyFilePreviewPage()
	{
		return (isDisplayed(fileModuleAdeptol, Speed.slow));
	}

	/**
	 * This method is used to verify that an element is clicable or not
	 *
	 * @author janki.hirani
	 * @created on 28 May 2018
	 * @param by
	 * @return
	 */
	public boolean isClickable(By by)
	{
		try
		{
			moveToElement(by);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Verify module is active or not
	 *
	 * @param moduleName
	 *        name of the module
	 * @return true if module is active
	 * @author dheeraj.rajput
	 * @Created 17 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyActiveModule(String moduleName)
	{
		findVisibleElement(breadCrumb);
		By module = By.xpath("//*[contains(@class,'mainMenu')]//*[normalize-space(text())='" + moduleName
				+ "']/ancestor::*[contains(@class,'active')]");
		return isDisplayed(module, Speed.slow);
	}

	/**
	 * Click on site name drop down in banner
	 *
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 */
	@Override
	public void clickOnSiteNameDropDownInBanner()
	{
		findVisibleElement(siteNameDropDown, Speed.slow).click();
		findVisibleElement(siteDropDownMenu, Speed.slow);
	}

	/**
	 * Search site in banner drop down
	 *
	 * @param siteName
	 *        name of the site to search
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 */
	@Override
	public void searchSiteInBannerDropDown(String siteName)
	{
		if (!isDisplayed(siteDropDownMenu))
		{
			clickOnSiteNameDropDownInBanner();
		}
		findVisibleElement(siteNameInput, Speed.slow).sendKeys(siteName.trim());
	}

	/**
	 * Select site in banner drop down
	 *
	 * @param siteName
	 *        name of the site
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 */
	@Override
	public BannerPage selectSiteFromBannerDropDown(String siteName)
	{
		searchSiteInBannerDropDown(siteName);
		By site = By
				.xpath(".//*[@id='dashboardNavigationPaneSiteList']//*[normalize-space(text())='" + siteName + "']");
		if (!isDisplayed(siteDropDown_noResultsFound, Speed.slow))
		{
			findVisibleElement(site, Speed.slow).click();
			return new BannerPageWeb();
		}
		return null;
	}

	/**
	 * Get size of a file
	 *
	 * @param fileNameWithExtension
	 *        file name with extension
	 * @return size of the file(double)
	 * @author vivek.mishra
	 * @Created 22 May 2018
	 * @Updated
	 */
	@Override
	public double getFileSize(String fileNameWithExtension)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileNameWithExtension.trim();
		File file = new File(path);
		DecimalFormat df = new DecimalFormat("0.00");
		double size = file.length();
		size = size / 1024;
		String formate = df.format(size);
		try
		{
			size = (Double) df.parse(formate);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * Close preview page
	 *
	 * @author dheeraj.rajput
	 */
	@Override
	public void closePreviewPage()
	{
		findVisibleElement(previewPageHeader_CloseIcon).click();
		findPresentElement(modalContentClosed, Speed.slow);
	}

	/**
	 * Verify tooltip of given option
	 *
	 * @return true --> If tooltip is being displayed, false otherwise.
	 * @param tooltipOption
	 *        Ex. Dashboard, Notifications, Favourites
	 * @author nidhi.shah
	 * @created 17-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyTooltipDisplayed(String tooltipOption)
	{
		findVisibleElement(By.xpath(".//*[@data-original-title='" + tooltipOption.trim() + "']"), Speed.slow);
		Actions action = new Actions(driver);
		action.moveToElement(findVisibleElement(By.xpath(".//*[@data-original-title='" + tooltipOption.trim() + "']")))
				.perform();
		return isDisplayed(By.xpath(
				".//*[@data-original-title='" + tooltipOption.trim() + "' and contains(@aria-describedby,'tooltip')]"),
				Speed.slow);
	}

	/**
	 * Verify first notification
	 *
	 * @return true --> If given message is equal to first displayed notification.,
	 *         false otherwise.
	 * @author nidhi.shah
	 * @created 18-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyFirstNotification(String notificationMessage)
	{
		if (!verifyNotificationModal())
		{
			clickOnNotificationLink();
		}
		return notificationMessage.equals(findVisibleElement(firstNotification).getText());
	}

	/**
	 * Verify given list against the list displayed on the instance under the
	 * favourite tab.
	 *
	 * @param favouriteContentList
	 * @return true --> If given list is same as that displayed on the instance,
	 *         false otherwise.
	 * @author nidhi.shah
	 * @created 21-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyFavouriteContentList(List<String> favouriteContentList)
	{
		String contentHeader = "";
		int size = 9;
		if (favouriteContentList.size() < 9)
		{
			size = favouriteContentList.size();
		}
		for (int i = 1; i <= size; i++)
		{
			contentHeader = findVisibleElement(By.xpath(
					".//*[@id='bannertopfavouritedisplaylist']//*[contains(@class,'list-unstyled')]//li[not(contains(@class,'divider'))]["
							+ i + "]//strong")).getText();
			if (!favouriteContentList.get(i - 1).trim().equals(contentHeader))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify given list against the list displayed on the instance under the
	 * recently viewed tab.
	 *
	 * @param favouriteContentList
	 * @return true --> If given list is same as that displayed on the instance,
	 *         false otherwise.
	 * @author nidhi.shah
	 * @created 21-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyRecentlyViewedContentList(List<String> favouriteContentList)
	{
		String contentHeader = "";
		int size = 3;
		if (favouriteContentList.size() < 3)
		{
			size = favouriteContentList.size();
		}
		for (int i = 1; i <= size; i++)
		{
			contentHeader = findVisibleElement(By.xpath(
					".//*[@id='bannertopfavouritedisplaylist']//*[contains(@class,'list-unstyled')]//li[not(contains(@class,'divider'))]["
							+ i + "]//strong")).getText();
			if (!favouriteContentList.get(i - 1).trim().equals(contentHeader))
			{
				return false;
			}
		}
		return true;

	}

	/**
	 * @author surbhi.khetan click On link from messages - banner after file is
	 *         shared with the user
	 * @creation date 30/04/2018
	 */
	@Override
	public void clickOnReceivedHomeLink()
	{
		findVisibleElement(homeLink, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan switching to the parent window after child window is
	 *         being closed
	 * @creation date 15/05/2018
	 */
	@Override
	public void switchToParentWindow()
	{

		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		driver.switchTo().window(parentWindow);
	}

	/**
	 * @author ashlesha.shastri
	 * @param email
	 * @param password
	 * @param rememberMe
	 *        For Login with Remember me
	 * @creation date 30/04/2018
	 */
	@Override
	public BannerPageWeb login(String email, String pwd, boolean rememberMe)
	{
		loginPageWeb = new LoginPageWeb(driver);
		loginPageWeb.setEmailId(email);
		loginPageWeb.setPassword(pwd);
		loginPageWeb.setRememberMe(rememberMe);
		bannerPageWeb = loginPageWeb.clickSignIn();
		return (BannerPageWeb) bannerPageWeb;
	}

	/**
	 * @author ashlesha.shastri
	 * @param path
	 *        For open Tab and hit URL
	 * @creation date 30/04/2018
	 */
	@Override
	public void openTabHitPath(String path)
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
		driver.get(path);
	}

	/**
	 * @author ashlesha.shastri For Getting Current URL
	 * @creation date 30/04/2018
	 */
	@Override
	public String getCurrentURL()
	{
		return driver.getCurrentUrl();
	}

	/**
	 * @author ashlesha.shastri Go Back in Browser
	 * @creation date 30/04/2018
	 */
	@Override
	public void goBackInBrowser()
	{
		driver.navigate().back();
	}

	@Override
	public LoginPage login()
	{
		loginPageWeb = new LoginPageWeb(driver);
		return loginPageWeb;
	}

	@Override
	public String getPopupMsgFromSystemPopupAndAccept()
	{
		String msg = "";
		msg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return msg;
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @return tip Address
	 * @cretaed on 29/05/2018
	 */

	@Override
	public String getIpAdress()
	{
		InetAddress ipAddr = null;
		try
		{
			ipAddr = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipAddr.getHostAddress();
	}

	/**
	 * <<<<<<< HEAD
	 *
	 * @author vivek.mishra
	 *         To sort the elements in reverse order with ignore case
	 * @created on 31/05/2018
	 */
	@Override
	public <T> void reverseSort(List<T> list, Comparator<? super T> c)
	{
		list.sort(c.reversed());
	}

	/**
	 * @param tabName
	 * @author tejash.trivedi
	 *         Created on 14/06/2018
	 */
	@Override
	public void clickOnTabOfFavouriteLink(String tabName)
	{
		WebElement favouriteEle = findClickableElement(By.xpath(".//*[@id='bannertopFavouritesFilterTabs']//a[normalize-space()='" + tabName + "']"));
		favouriteEle.click();
	}
}
