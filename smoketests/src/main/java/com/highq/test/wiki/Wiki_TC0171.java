/**
 *
 */
package com.highq.test.wiki;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 */
public class Wiki_TC0171 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	ConfigurationData configurationData = new ConfigurationData();

	String edit = "Edit";
	String[] userNames = {"normal.user171", "admin.user171"};
	String nPassword = "Password@123";
	String siteName = "Wiki_TC0171T";
	String domain = "wiki0171.com";
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	WikiPage wikiWeb;
	BannerPage bannerWeb;
	String[] wikiName = {"wiki1 " + getRandomString(), "wiki2 " + getRandomString()};
	String comment = "new_comment_";
	String file = "doc1.txt";
	String newFile = "doc2.txt";
	String alter = "altered text";
	String newComment;
	String wikiLikeMessage = "You like this";
	String jsonFileName = "preConfiguration_Wiki_TC0171.json";

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test
	public void wikiTC0171() throws InterruptedException, IOException, JSONException
	{
		preConfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		siteCleanup();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void preConfiguration() throws InterruptedException, IOException, JSONException
	{
		precondition();
		TC0171_NormalUserPreCondition();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario1() throws InterruptedException
	{
		bannerWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		int count = wikiWeb.getCommentCount();
		wikiWeb.addComment(comment);
		wikiWeb.verifyLastCommentInSelectedWiki(comment);
		int addCount = wikiWeb.getCommentCount();

		Assert.assertTrue(count != addCount);
		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(comment, "Like"));
		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(comment, "Reply"));
		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(comment, edit));
		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(comment, "Delete"));
		Assert.assertTrue(wikiWeb.verifyAddTagButton());
		newComment = "@" + userNames[1];
		wikiWeb.addTag(newComment);
		wikiWeb.verifyTagsAdded(newComment);
	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario2()
	{
		newComment = comment + getRandomString();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.addComment(newComment);
		int likeCount = wikiWeb.getCommentLikeCount(newComment);
		wikiWeb.clickOnWikiCommentFooterLabels(newComment, "Like");
		int newLikeCount = wikiWeb.getCommentLikeCount(newComment);
		Assert.assertFalse(likeCount > newLikeCount);

		wikiWeb.clickOnLikeCommentCount(newComment);
		Assert.assertTrue(wikiWeb.verifyPeopleWhoLikeThisModal());
		wikiWeb.clickOnModalCloseButton();
		wikiWeb.verifyPeopleWhoLikeThisModal();

		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(newComment, "Unlike"));
		wikiWeb.clickOnWikiCommentFooterLabels(newComment, "Unlike");
		Assert.assertTrue(wikiWeb.verifyCommentOptionsInCommentGrid(newComment, "Like"));
		likeCount = wikiWeb.getCommentLikeCount(newComment);
		Assert.assertTrue(likeCount < newLikeCount);
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario3() throws InterruptedException
	{
		newComment = comment + getRandomString();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.addComment(newComment);

		wikiWeb.clickOnWikiCommentFooterLabels(newComment, "Reply");
		Assert.assertTrue(wikiWeb.verifyCommentBoxText(userNames[0]));
		wikiWeb.sendTextInWikiCommentBox(alter);
		wikiWeb.clickOnWikiCommentPostButton();
		String replyData = getUserData(userNames[0]) + " " + alter;

		// It Contains both conditions : When user reply to a comment than a new comment will be added to that particular content and it will be displayed at the end of all comments.
		Assert.assertTrue(wikiWeb.getCommentReplyData().trim().equals(replyData.trim()));
	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario4()
	{
		newComment = comment + getRandomString();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.addComment(newComment);
		wikiWeb.clickOnWikiCommentFooterLabels(newComment, edit);
		wikiWeb.clickOnAttachmentButtonOfComment();
		wikiWeb.verifyAttachmentModal();
		wikiWeb.attachOnBrowseButtonInAttachmentModalUploadTab(file);
		wikiWeb.clickOnAttachButtonInAttachmentmodal();
		wikiWeb.clickOnWikiCommentPostButton();

		wikiWeb.clickOnWikiCommentFooterLabels(newComment, edit);
		Assert.assertTrue(wikiWeb.verifyCommentBoxIsEditable(newComment));
		String currentFile = wikiWeb.verifyCommentAttachment(newComment);
		Assert.assertTrue(currentFile.equalsIgnoreCase(file));
		Assert.assertTrue(wikiWeb.verifyRemoveAttachmentButtonInCommentBox());
		wikiWeb.clickOnRemoveAttachmentButtonInEditComment(newComment);

		wikiWeb.clickOnAttachmentButtonOfComment();
		wikiWeb.verifyAttachmentModal();
		wikiWeb.attachOnBrowseButtonInAttachmentModalUploadTab(newFile);
		wikiWeb.clickOnAttachButtonInAttachmentmodal();
		wikiWeb.clickOnWikiCommentPostButton();

		wikiWeb.clickOnWikiCommentFooterLabels(newComment, edit);
		currentFile = wikiWeb.verifyCommentAttachment(newComment);
		Assert.assertTrue(currentFile.equalsIgnoreCase(newFile));
		wikiWeb.clickOnWikiCommentPostButton();

	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario5()
	{
		wikiWeb.deleteWikiComment(newComment);
		Assert.assertFalse(wikiWeb.verifyWikiComment(newComment));
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario6() throws InterruptedException
	{
		bannerWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();
		wikiWeb.selectWikiFromLeftPanel(wikiName[0]);
		wikiWeb.clickOnWikiLikeTag();
		Assert.assertTrue(wikiWeb.getTextOfWikiLikeTag().trim().equals("Unlike".trim()));
		Assert.assertTrue(wikiWeb.verifyWikiLikeMessage(wikiLikeMessage));

	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	private void TC0171_NormalUserPreCondition() throws InterruptedException
	{
		bannerWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();

		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiName[0]);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiName[0]);
		wikiWeb.logout();
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void siteCleanup() throws InterruptedException
	{
		wikiWeb.gotoWikiModule();
		wikiWeb.deleteWiki(wikiName[0]);
	}
}
