package com.highq.test.blog;

import static org.testng.Assert.assertFalse;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author badal.gandhi
 */
public class Blog_TC0186 extends BannerPageWeb
{

	DashboardPage dashboardWeb;
	BlogPage blogWeb;
	BannerPage bannerPageWeb;
	Map<Boolean, BannerPageWeb> result;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String newPassword = "Password@123";
	String[] userNames = {"normal.user0186_02", "site.admin0186_02"};
	String orgType = "Internal";
	String siteName = "Blog_TC0186";
	String domain = "sl.com";

	String blogName = "Blog TC 0186";
	String blogTag = "Tag1";
	String blogContent = "Blog Content";
	String categoryName = "Default";
	String fileName = "doc1.txt";
	String imageFile = "headerImage.jpg";
	String title = "Title";
	String content = "content";
	String tag = "Tags";
	String category = "Category";
	String attachments = "Attachments";
	String settings = "settings";
	String copiedBlogName = "Copy of " + blogName;
	String shareOperation = "Share";
	String deleteOperation = "Delete";
	String publishDateType = "Custom date";
	String recipientMail = userNames[1] + "@" + domain;
	String subject = "Task_TC0178 Share Task Automation";
	String message = "Testing functionality of Share Task.";
	ActivityPage activityWeb;
	Timestamp startTime;
	Timestamp endTime;
	AdminActivityPage adminActivityWeb;
	AdminPage adminPageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@Test
	public void BlogTC0186() throws InterruptedException, IOException, UnsupportedFlavorException
	{
		preconfiguration();
		scenario01();
		scenario02();
		scenario03();
		scenario04();
	}

	private void preconfiguration() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();

	}

	private void scenario01() throws InterruptedException, IOException
	{
		shareBlogViaEmail();
	}

	private void scenario02() throws UnsupportedFlavorException, IOException, InterruptedException
	{
		shareBlogByLink();
	}

	private void scenario03() throws InterruptedException, IOException
	{
		shareBlogViaMessage();
	}

	private void scenario04() throws InterruptedException, IOException
	{
		shareBlogViaMicroBlog();
	}

	private void shareBlogViaEmail() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);
		blogWeb = gotoBlogModule();

		addBlogPrecondition();

		blogWeb.clickOnOptionInMoreActionInDetailsSection(shareOperation);

		Assert.assertTrue(verifyEmptyRecipent());
		Assert.assertTrue(disabledSendButtonInShareDialogueBox());
		Assert.assertTrue(verifyDefaultSubjectIsPresent());

		// Verify Mail is not sent after clicking on cancel
		blogWeb.shareViaEmail(recipientMail, subject, message);
		startTime = blogWeb.getStartDateTimeStamp();

		blogWeb.clickCancelInShareModal();
		endTime = blogWeb.getEndDateTimeStamp();
		Assert.assertTrue(!verifyMail(recipientMail, startTime, endTime, subject, message));

		blogWeb = gotoBlogModule();
		// Verify Mail sent after clicking on Send
		blogWeb.clickOnOptionInMoreActionInBlogList(blogName, shareOperation);
		blogWeb.shareViaEmail(recipientMail, subject, message);
		startTime = blogWeb.getStartDateTimeStamp();
		blogWeb.clickSendInShareModal();
		endTime = blogWeb.getEndDateTimeStamp();

		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, subject, message));
		dashboardWeb = blogWeb.gotoDashboard();
		logout();
	}

	private void shareBlogByLink() throws UnsupportedFlavorException, IOException, InterruptedException
	{

		/** Scenario 2: Share via link */

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		// Search site and go to blog module

		dashboardWeb.searchSite(siteName);
		blogWeb = gotoBlogModule();

		blogWeb.clickOnOptionInMoreActionInBlogList(blogName, shareOperation);
		blogWeb.copyShareLink(false);
		blogWeb.clickCancelInShareModal();
		dashboardWeb = blogWeb.gotoDashboard();
		logout();

		// login with normal user
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);

		// open copied URL
		bannerPageWeb.openCopiedURL();

		Assert.assertTrue(blogWeb.VerifyBlogTitleInDetailsSection(blogName));
		logout();

	}

	private void shareBlogViaMessage() throws InterruptedException, IOException
	{

		/** Scenario 3: Share via message */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		// Search site and go to blog module

		dashboardWeb.searchSite(siteName);
		blogWeb = gotoBlogModule();
		blogWeb.clickOnOptionInMoreActionInBlogList(blogName, shareOperation);

		blogWeb.shareViaMessage(recipientMail, message);

		// Verify Message sent after clicking on Send
		startTime = blogWeb.getStartDateTimeStamp();
		blogWeb.clickSendInShareModal();
		endTime = blogWeb.getEndDateTimeStamp();

		Assert.assertTrue(verifyMail(recipientMail, startTime, endTime, "", message));

		dashboardWeb = blogWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(userNames[0], message,true));
			dashboardWeb.clickCancelInMessageBox();
		}
		else
			Assert.assertFalse(true);
		logout();

	}

	private void shareBlogViaMicroBlog() throws InterruptedException, IOException
	{

		/** Scenario 3: Share via Micro blog */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		// Search site and go to Task module

		dashboardWeb.searchSite(siteName);
		blogWeb = gotoBlogModule();

		blogWeb.clickOnOptionInMoreActionInBlogList(blogName, shareOperation);

		blogWeb.gotoMicroblogTab();

		Assert.assertTrue(blogWeb.verifyDefaultMessageIsPresent());
		Assert.assertTrue(blogWeb.verifyDefaultMicroblogSite(siteName));

		blogWeb.clickPostInShareModal();
		activityWeb = blogWeb.gotoActivityModule();

		verifyMicroblogPost();

		activityWeb.gotoDashboard();
		// Verify microblog post in dashboard
		verifyMicroblogPost();

	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
		userRolesOfUser.put("site admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);
		modulePermission.put("Blog", moduleEditPermission);
		modulePermission.put("People", moduleEditPermission);
		modulePermission.put("Activity", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("all");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		// Site Admin -> Enable microblogging

		adminPageWeb = bannerPageWeb.gotoAdminModule();

		// Site Admin -> Enable microblogging
		adminActivityWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityWeb.enableMicroblogging(true);
		adminActivityWeb.clickOnSave();

		logout();

	}

	private void addBlogPrecondition() throws InterruptedException, IOException
	{
		blogWeb = dashboardWeb.gotoBlogModule();

		if (!blogWeb.verifyBlogInBlogList(blogName))
		{
			Map<String, String> blogData = new LinkedHashMap<>();
			blogData.put(title, blogName);
			blogData.put(content, blogContent);
			blogData.put(tag, blogTag);
			blogData.put(category, categoryName);

			blogWeb.addBlog(blogData);
		}
		else
		{
			Assert.assertTrue(blogWeb.verifyBlogInBlogList(blogName));
			blogWeb.clickOnBlogInBlogList(blogName);
		}

	}

	public void verifyMicroblogPost()
	{
		activityWeb.gotoPosts();
		// Verify site name in activity post
		if (!activityWeb.verifyGrayMetaSiteName(siteName))
			assertFalse(true);
		// Verify authenticity of link by opening it and verifying wiki title
		activityWeb.openFirstPostLink();
		if (!blogWeb.VerifyBlogTitleInDetailsSection(blogName))
			assertFalse(true);
		activityWeb.closeCurrentWindow();
	}

}
