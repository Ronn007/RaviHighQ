package com.highq.test.wiki;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class Wiki_TC0176 extends BannerPageWeb
{
	/** Automation test for Share Wiki */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Wiki_TC0176";
	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "site admin";

	String wikiTitle = "Wiki_TC0176";
	String wikiContent = "Wiki Content for TC0176";

	String recipientMail = userNames[0];
	String subject = "Wiki_TC0176 Share Wiki Automation";
	String message = "Testing functionality of Share Wiki.";

	String mailContent = "";
	String mailHtmlFile = "MailContent.html";

	Timestamp startTime;
	Timestamp endTime;
	String messageRecipient = userNames[1];
	String selectMoreOption = "Share";
	String userName = userNames[0];
	String jsonFileName = "preConfiguration_Wiki_TC0176.json";
	String defaultMicroblogMessage = "Here is a link to ";

	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	WikiPage wikiWeb;
	DashboardPage dashboardWeb;
	AdminSecurityPage adminSecurityWeb;
	AddUserPage addUserWeb;
	BaseWikiTest baseWikiTest;
	ActivityPage activityWeb;
	AdminActivityPage adminActivityWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void WikiTC0176() throws InterruptedException, UnsupportedFlavorException, IOException, JSONException
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
	}

	public void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Share via Email Tab */
		siteAndUserSetup();
		siteAdminOperations();

		// login with normal user
		String userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		// Search site and go to wiki module
		searchSiteAndGotoWikiModule(siteName);
		// Create or search wiki
		precondition();
		wikiWeb.selectOptionInMoreActionInSelectedWiki(selectMoreOption);
		// Verify Subject box is not empty
		if (!wikiWeb.verifyDefaultSubjectIsPresent())
			assertFalse(true);

		// Verify Mail is not sent after clicking on cancel
		wikiWeb.shareViaEmail(recipientMail, subject, message);
		startTime = wikiWeb.getStartDateTimeStamp();
		System.out.println("Start Time: " + startTime);
		wikiWeb.clickCancelInShareModal();
		endTime = wikiWeb.getEndDateTimeStamp();
		if (verifyMail(recipientMail, startTime, endTime, subject, message))
			assertFalse(true);

		// Verify Mail sent after clicking on Send
		wikiWeb.shareViaEmail(recipientMail, subject, message);
		startTime = wikiWeb.getStartDateTimeStamp();
		wikiWeb.clickSendInShareModal();
		endTime = wikiWeb.getEndDateTimeStamp();
		if (!verifyMail(recipientMail, startTime, endTime, subject, message))
			assertFalse(true);
		dashboardWeb = wikiWeb.gotoDashboard();
		logout();
	}

	public void scenario2() throws InterruptedException, UnsupportedFlavorException, IOException
	{
		/** Scenario 2: Share via link */
		// login with site admin
		String userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		// Search site and go to wiki module
		searchSiteAndGotoWikiModule(siteName);
		// Open wiki
		selectWiki(wikiTitle);
		if (!wikiWeb.verifyWikiTitle(wikiTitle))
			assertFalse(true);
		wikiWeb.copyShareLink(false);
		wikiWeb.clickCancelInShareModal();
		dashboardWeb = wikiWeb.gotoDashboard();
		logout();

		// login with normal user
		userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		// open copied wiki URL
		bannerPageWeb.openCopiedURL();
		// Verify active wiki is same as wikiTitle
		if (wikiWeb.verifyActiveWiki(wikiTitle))
			System.out.println(wikiTitle + " is active.");
		else
			assertFalse(true);
		logout();
	}

	public void scenario3() throws InterruptedException
	{
		/** Scenario 3: Share via Message */

		// login with normal user
		String userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		// Search site and go to wiki module
		searchSiteAndGotoWikiModule(siteName);
		// Open wiki
		selectWiki(wikiTitle);
		if (!wikiWeb.verifyWikiTitle(wikiTitle))
			assertFalse(true);
		// Send message details in message tab
		wikiWeb.shareViaMessage(messageRecipient, message);

		// Verify Message sent after clicking on Send
		startTime = wikiWeb.getStartDateTimeStamp();
		wikiWeb.clickSendInShareModal();
		endTime = wikiWeb.getEndDateTimeStamp();
		if (!verifyMail(messageRecipient, startTime, endTime, "", message))
			assertFalse(true);
		dashboardWeb = wikiWeb.gotoDashboard();
		logout();
		userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			assertTrue(dashboardWeb.verifyRecentMessageRecieved(userName, message,true));
			dashboardWeb.clickCancelInMessageBox();
		}
		else
			assertFalse(true);
		logout();

	}

	public void scenario4() throws InterruptedException
	{
		/** Scenario 4: Share wiki via Microblog */
		// login with normal user
		String userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, newPassword);
		// Search site and go to wiki module
		searchSiteAndGotoWikiModule(siteName);
		// Open wiki
		selectWiki(wikiTitle);
		if (!wikiWeb.verifyWikiTitle(wikiTitle))
			assertFalse(true);
		//
		wikiWeb.selectMoreOptionsOperationsOfWiki(selectMoreOption);
		wikiWeb.gotoMicroblogTab();
		// Verify default message box text is present
		if (!wikiWeb.verifyDefaultMicroblogMessageInShareModal(defaultMicroblogMessage + "\"" + wikiTitle.trim() + "\":"))
			assertFalse(true);
		// Verify default site name
		if (!wikiWeb.verifyDefaultMicroblogSite(siteName))
			assertFalse(true);
		// Share microblog
		wikiWeb.clickPostInShareModal();

		activityWeb = wikiWeb.gotoActivityModule();
		// Verify microblog post in activity tab
		verifyMicroblogPost();

		activityWeb.gotoDashboard();
		// Verify microblog post in dashboard
		verifyMicroblogPost();
		logout();
	}

	public void searchSiteAndGotoWikiModule(String sitename) throws InterruptedException
	{
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(sitename);
		wikiWeb = dashboardWeb.gotoWikiModule();
	}

	public void precondition()
	{
		if (wikiWeb.verifySearchedPage(wikiTitle))
		{
			selectWiki(wikiTitle);
		}
		else
		{
			wikiWeb.clickOnAddWiki();
			createWiki(wikiTitle, wikiContent);
			selectWiki(wikiTitle);
		}
		if (wikiWeb.verifyWikiTitle(wikiTitle))
			System.out.println(wikiTitle + " created successfully.");
		else
			assertFalse(true);
	}

	public void selectWiki(String wikiName)
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName);
		wikiWeb.clearSearchItem();
	}

	public void createWiki(String wikiName, String content)
	{
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(content);
		wikiWeb.saveWiki();
	}

	private void siteAndUserSetup() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();

		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();

	}

	public void siteAdminOperations() throws InterruptedException
	{
		// Login with site admin user
		String userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		// Search site
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();

		// Site Admin -> Enable microblogging
		adminActivityWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityWeb.enableMicroblogging(true);
		adminActivityWeb.clickOnSave();

		// Site Admin -> Enable Wiki and People modules
		adminPageWeb = adminActivityWeb.gotoAdminModule();
		modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
		modulesPageWeb.enableSpecificModules(true, "wiki", "people", "activity");
		adminPageWeb = modulesPageWeb.clickOnSaveButton();

		// Set View and Edit permission for normal user
		Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();

		modulePermissions.put("View", true);
		modulePermissions.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Wiki", modulePermissions);
		modulePermission.put("Activity", modulePermissions);

		Map<String, String> userData = new LinkedHashMap<>();
		userData.put(domain, userNames[0]);

		addUserWeb = adminPageWeb.clickUsersInLeftPanel();// preConfigurationTest.setModulePermissionsForMultipleUsers(userData, modulePermission);
		addUserWeb.openUserPermissionModel(userNames[0]);
		addUserWeb.setModulePermissionForUsers(modulePermission);
		addUserWeb.clickSaveInSetPermissions();
		dashboardWeb = addUserWeb.gotoDashboard();
		logout();
	}

	public boolean verifyMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage)
	{
		String query;
		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		else
			query = "select TOP 1 * from Email where mailsubject = '" + emailSubject.trim() + "' and mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";

		mailContent = wikiWeb.getMailContent(query);
		if (mailContent.isEmpty() || mailContent == null)
			return false;
		else
		{
			wikiWeb.createHtmlFile(mailHtmlFile, mailContent);
			wikiWeb.getLocalHtmlPage(mailHtmlFile);
			mailto = mailto.substring(0, mailto.indexOf("@"));
			if (wikiWeb.verifyContent(mailto, emailMessage))
			{
				System.out.println(emailMessage + " is present");
				wikiWeb.closeCurrentTab();
				return true;
			}
		}
		return false;
	}

	public void verifyMicroblogPost()
	{
		activityWeb.gotoPosts();
		// Verify site name in activity post
		if (!activityWeb.verifyGrayMetaSiteName(siteName))
			assertFalse(true);
		// Verify authenticity of link by opening it and verifying wiki title
		activityWeb.openFirstPostLink();
		if (!wikiWeb.verifyWikiTitle(wikiTitle))
			assertFalse(true);
		activityWeb.closeCurrentWindow();
	}
}
