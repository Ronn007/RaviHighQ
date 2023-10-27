package com.highq.test.dashboard;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminGeneralPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.PasscodePage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.PasscodeWeb;

/**
 * @author surbhi.khetan
 */
public class Dashboard_TCR0250 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	ModulesPage modulesPageWeb;
	AdminActivityPage adminActivityPageWeb;
	ActivityPage activityPageWeb;
	AdminGeneralPage adminGeneralWeb;
	PreConfiguration preConfiguration;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	SearchUserPage searchUserweb;
	AdminSecurityPage adminSecurityWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage documentAddDataPage;
	PasscodePage passcodePageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	Map<String, List<String>> userMap;
	List<String> userList;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String newPassword = "Password@123";
	String orgType = "Internal";
	String domain = "dashboard250.com";
	String siteName = "Dashboard_TCR0250";
	String jsonFileName = "preConfiguration_Dashboard_TCR0250.json";
	String posts = "Posts";
	String microblogPost = "This is a test microblog";
	String recipientMail = "dashboard.normaluser250@dashboard250.com";
	String userName = "Dashboard Normaluser250";
	String subject = "This is a test subject";
	String message = "This is a test message";
	String share = "Share";
	String microblogMessage = "\"This is a test microblog\" via Ravi Middha";
	String editMicroblogMessage = "This is an edited message";
	String fileName = "testDoc.txt";
	String addMenuItem = "Files";
	String file = "doc2.txt";
	String[] userNames = {"dashboard.normaluser250"};
	String[] site = {"Preparation" + getRandomString(), "Archived" + getRandomString(), "ReadOnly" + getRandomString(),
			"PasswordProtected" + getRandomString(), "2-factorAuth"+ getRandomString() };
	String[] status = {"Preparation", "Archived", "Read only", "Active", "Active"};

	Timestamp startTime;
	Timestamp endTime;

	@Test
	public void dashboardTCR0250() throws IOException, JSONException, InterruptedException, UnsupportedFlavorException
	{

		preCondition();
		siteAndUserConfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
	}

	private void siteAndUserConfiguration() throws IOException, JSONException, InterruptedException
	{

		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		
		/** Add a file in site */
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectItemFromAdd(addMenuItem);
		documentAddDataPage = new DocumentAddDataPage();
		documentAddDataPage.clean();
		documentAddDataPage.setFileuploadpath(file);
		documentWeb.addFile(documentAddDataPage, null);
		documentWeb.clickAddInModal();
		dashboardWeb = documentWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminActivityPageWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityPageWeb.enableMicroblogging(true);
		adminActivityPageWeb.clickOnSave();

		activityPageWeb = adminActivityPageWeb.gotoActivityModule();
		activityPageWeb.gotoDashboard();

		/** Posting a microblog */
		activityPageWeb.clickOnRecentActivityTabs(posts);
		activityPageWeb.verifyRecentActivityArea();
		activityPageWeb.sendTextInMicroblogTextBox(microblogPost);
		activityPageWeb.sendTextInShareWithTextBox(siteName);
		activityPageWeb.clickOnMicroBlogPostButton();
		activityPageWeb.verifyPost(siteName, microblogPost);
		Assert.assertTrue(activityPageWeb.verifyPost(siteName, microblogPost));
	}

	private void scenario1()
	{

		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.gotoRePost();

		startTime = activityPageWeb.getStartDateTimeStamp();

		/** Share microblog via email */
		activityPageWeb.shareViaEmail(recipientMail, subject, message);
		activityPageWeb.clickSendInShareModal();
		endTime = activityPageWeb.getEndDateTimeStamp();

		/** Verify mail that is sent to email address of recipient */
		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, subject, message));

	}

	/**
	 * @throws UnsupportedFlavorException
	 * @throws IOException
	 */
	private void scenario2() throws UnsupportedFlavorException, IOException
	{

		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.copyShareLink(false);
		activityPageWeb.clickCancelInShareModal();
		dashboardWeb = activityPageWeb.gotoDashboard();
		logout();

		/** Share microblog via link */
		bannerPageWeb = login(recipientMail, sysAdminPassword);
		bannerPageWeb.openCopiedURL();

		/** Verify link is copied and generated at user end */
		Assert.assertTrue(activityPageWeb.verifyPost(siteName, microblogPost));
		logout();
	}

	private void scenario3()
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		activityPageWeb = bannerPageWeb.gotoActivityModule();
		activityPageWeb.gotoDashboard();
		activityPageWeb.clickOnRecentActivityTabs(posts);
		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.shareViaMessage(recipientMail, message);
		startTime = activityPageWeb.getStartDateTimeStamp();

		/** Share microblog via message */
		activityPageWeb.clickSendInShareModal();
		endTime = activityPageWeb.getEndDateTimeStamp();
		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, "", message));
		dashboardWeb = activityPageWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(recipientMail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		dashboardWeb.clickOnPrivateMessage();

		/** Verify message that is being sent to the user */
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(sysAdminEmail, message,true));
			dashboardWeb.clickCancelInMessageBox();
		}
		else
			Assert.assertFalse(true);
		logout();

	}

	/**
	 * @throws UnsupportedFlavorException
	 */
	private void scenario4() throws UnsupportedFlavorException, IOException
	{

		bannerPageWeb = login(recipientMail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		activityPageWeb = bannerPageWeb.gotoActivityModule();
		activityPageWeb.gotoDashboard();

		/** Re-post of microblog */
		activityPageWeb.clickOnRecentActivityTabs(posts);
		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.gotoRePost();

		/** Verify site list in share with */
		Assert.assertTrue(activityPageWeb.verifySiteFromAutoSuggestedList(siteName));

		for (int i = 0; i < site.length; i++)
			Assert.assertFalse(activityPageWeb.verifySiteFromAutoSuggestedList(site[i]));

		/** Verify text area message */
		Assert.assertTrue(activityPageWeb.verifyMicroblogMessage(microblogMessage));

		/** Verify if modal is closed when clicked on cancel button */
		activityPageWeb.clickCancelInShareModal();
		Assert.assertFalse(activityPageWeb.verifyShareModalVisibility());

		/**
		 * Verify if microblog is edited and posted when clicked on re-pot button
		 * and Verify when an attachment is uploaded
		 */
		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.shareViaRePostWithAttachment(editMicroblogMessage, siteName, fileName);
		Assert.assertTrue(activityPageWeb.verifyPost(siteName, editMicroblogMessage));
		Assert.assertTrue(activityPageWeb.verifyUploadedFile(siteName, fileName));

		/** Verify when attachment is uploaded from recent tab */
		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, microblogPost, share);
		activityPageWeb.shareViaRePostWithRecentAttachment(editMicroblogMessage, siteName, file);
		Assert.assertTrue(activityPageWeb.verifyPost(siteName, editMicroblogMessage));
		Assert.assertTrue(activityPageWeb.verifyUploadedFile(siteName, file));

		/** Verify if links are present */
		activityPageWeb.clickOnOptionInMoreActionOfPost(siteName, editMicroblogMessage, share);
		activityPageWeb.gotoRePost();
		Assert.assertTrue(activityPageWeb.verifyInsertLinkButton());
		Assert.assertTrue(activityPageWeb.verifyAttachmentButton());
		Assert.assertTrue(activityPageWeb.verifyTextFormatButton());
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	void preCondition() throws InterruptedException, IOException
	{

		/** Site and user setup */
		preConfiguration = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);
		systemAdminWeb = preConfiguration.setOrganisation(orgData);

		userList = new ArrayList<>();
		for (int i = 0; i < userNames.length; i++)
		{
			userList.add(userNames[i]);
		}

		userMap = new HashMap<>();
		userMap.put(domain, userList);

		searchUserweb = preConfiguration.createAndResetUsers(userMap, newPassword, UserStatus.Active, false);

		/** Changing status of site */
		for (int i = 0; i < site.length; i++)
		{
			dashboardWeb = searchUserweb.gotoDashboard();

			dashboardWeb.searchSite(site[i]);
			adminPageWeb = dashboardWeb.gotoAdminModule();
			modulesPageWeb = adminPageWeb.clickOnModulesInLeftPanel();
			modulesPageWeb.enableSpecificModules(true, "activity");
			adminPageWeb = modulesPageWeb.clickOnSaveButton();

			adminPageWeb = preConfiguration.createSiteUsers(userMap);
			adminPageWeb = gotoAdminModule();
			adminActivityPageWeb = adminPageWeb.clickActivityInLeftPanel();
			adminActivityPageWeb.enableMicroblogging(true);
			adminActivityPageWeb.clickOnSave();
			adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
			adminGeneralWeb.selectStatus(status[i]);
			adminGeneralWeb.clickOnSave();
		}

		/** Password Protected site */
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(site[3]);
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.setPasswordEnabled(true);
		adminSecurityWeb.sendPassword(sysAdminPassword);
		adminSecurityWeb.clickOnSave();
		
		/** Two factor authentication enabled */
		dashboardWeb = adminSecurityWeb.gotoDashboard(); 
		dashboardWeb.searchSite(site[4]); 
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.setEnableTwoFactorAuthentication(true);
		adminSecurityWeb.clickOnSave();
		passcodePageWeb = new PasscodeWeb(driver);
		passcodePageWeb.enterPasscode(passcodePageWeb.getPasscode(sysAdminEmail));
		passcodePageWeb.clickOnVerifyPasscode();		
		logout();
	}

}
