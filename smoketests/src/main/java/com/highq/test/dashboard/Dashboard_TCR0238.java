/**
 * 
 */
package com.highq.test.dashboard;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @cretaed on 05/04/2018
 */
public class Dashboard_TCR0238 extends BannerPageWeb
{
	DashboardPage dashboardPage;
	BannerPage bannerPage;
	ModulesPage modulesPage;
	AdminPage adminPage;
	AdminActivityPage adminActivityPage;
	ActivityPage activityPage;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "Dashboard_TCR0238";

	String microblogPost = "This is my microblog";
	String posts = "Posts";
	String commentData = "comment 1";
	String commentData2 = "comment 2";
	String fileName = "doc1.txt";
	String fileName2 = "doc2.txt";
	String commentBoxFooterText = "Use @ to mention people and # to add tags";
	String attach = "Attach";
	String edit = "Edit";
	String deleteModal = "Delete comment?";
	String delete = "Delete";

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws InterruptedException
	 * @cretaed on 05/04/2018
	 */
	@Test
	public void DashboardTCR0238() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
		scenario3();
	}

	public void scenario1() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		modulesPage = adminPage.clickOnModulesInLeftPanel();
		modulesPage.enableAllModules();
		adminPage = modulesPage.gotoAdminModule();
		adminActivityPage = adminPage.clickActivityInLeftPanel();
		adminActivityPage.enableMicroblogging(true);
		adminActivityPage.clickOnSave();

		activityPage = adminActivityPage.gotoActivityModule();
		activityPage.gotoDashboard();
		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		activityPage.sendTextInMicroblogTextBox(microblogPost);
		activityPage.sendTextInShareWithTextBox(siteName);
		activityPage.clickOnMicroBlogPostButton();
		activityPage.verifyRecentActivityArea();
		activityPage.verifyPost(siteName, microblogPost);
		activityPage.clickOnCommentButtonOfPost(microblogPost, siteName);
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);

		Assert.assertTrue(activityPage.verifyCommentBoxFooterMessage(microblogPost, siteName, commentBoxFooterText));
		Assert.assertTrue(activityPage.verifyAttachmentIconOfCommentBoxOfPost(microblogPost, siteName));
		Assert.assertTrue(activityPage.verifyLinkIconOfCommentBoxOfPost(microblogPost, siteName));
		Assert.assertTrue(activityPage.verifyTextFormaterIconOfCommentBoxOfPost(microblogPost, siteName));
		Assert.assertTrue(activityPage.verifyCommentCancelButton());
		Assert.assertTrue(activityPage.verifyCommentPostButton());

		activityPage.sendCommentInPostCommentBox(microblogPost, siteName, commentData);
		activityPage.clickOnAttachmentIconOfCommentBoxOfPost(microblogPost, siteName);
		activityPage.verifyModal();
		activityPage.attachFileInUploadTabInAddAttachmentsModal(fileName);
		activityPage.clickOnModalButton(attach);
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);
		activityPage.clickOnCommentPostButton();
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);
		activityPage.verifyComment(microblogPost, siteName, commentData);

		Assert.assertTrue(activityPage.getCommentCountOfPost(microblogPost, siteName) == 1);
	}

	/**
	 * @author vivek.mishra
	 * @created on 12/04/2018
	 */
	public void scenario2()
	{
		activityPage.clickOnOptionOfCommentOfPost(microblogPost, siteName, commentData, edit);
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);
		activityPage.clickOnRemoveButtonOfFileOfComment(fileName);
		activityPage.editCommentBoxText(microblogPost, siteName, commentData, commentData2);
		activityPage.clickOnAttachmentIconOfCommentBoxOfPost(microblogPost, siteName);
		activityPage.verifyModal();
		activityPage.attachFileInUploadTabInAddAttachmentsModal(fileName2);
		activityPage.clickOnModalButton(attach);
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);
		activityPage.clickOnCommentPostButton();
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);

		Assert.assertTrue(activityPage.verifyComment(microblogPost, siteName, commentData2));
		Assert.assertFalse(activityPage.verifyAttachedFileToComment(microblogPost, siteName, commentData2, fileName));
		Assert.assertTrue(activityPage.verifyAttachedFileToComment(microblogPost, siteName, commentData2, fileName2));
	}

	/**
	 * @author vivek.mishra
	 * @created on 13/04/2018
	 */
	public void scenario3()
	{
		activityPage.clickOnOptionOfCommentOfPost(microblogPost, siteName, commentData2, delete);
		activityPage.verifyModal();
		Assert.assertTrue(activityPage.verifyModal(deleteModal));
		activityPage.clickOnModalButton(delete);
		activityPage.verifyCommentSectionOfPost(microblogPost, siteName);
		Assert.assertFalse(activityPage.verifyComment(microblogPost, siteName, commentData2));
	}

}
