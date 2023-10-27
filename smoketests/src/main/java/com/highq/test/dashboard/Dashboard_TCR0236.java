/**
 * 
 */
package com.highq.test.dashboard;

import java.io.IOException;
import java.text.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @creation date 28/03/2018
 */
public class Dashboard_TCR0236 extends BannerPageWeb
{
	DashboardPage dashboardPage;
	ModulesPage modulesPage;
	AdminPage adminPage;
	AdminActivityPage adminActivityPage;
	WikiPage wikiPage;
	BlogPage blogPage;
	ActivityPage activityPage;
	DocumentPage documentPage;
	DocumentAddDataPage addDoc;
	BannerPage bannerPage;
	SystemAdminPage systemAdminPage;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsPage;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "Dashboard_TCR0236";

	String posts = "Posts";
	String external = "External";
	String upload = "Upload";
	String insert = "Insert";
	String delete = "Delete";
	String cancel = "Cancel";
	String deleteModal = "Delete post?";
	String microblogPost = "This is my microblog " + getRandomString();
	String activityMicroblogPost = "This is my activity microblog " + getRandomString();
	String externalURL = "https://www.google.co.in";
	String fileName = "automation.jpg";
	String deleteModalMessage = "Are you sure you want to permanently delete this post?";

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws ParseException
	 * @throws IOException
	 * @creation date 29/03/2018
	 */
	@Test
	public void DashboardTCR0236() throws InterruptedException, ParseException, IOException
	{
		scenario1();
		scenario2();
		scenario3();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @creation date 29/03/2018
	 */
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

		systemAdminPage = adminActivityPage.gotoSystemAdmin();
		systemAdminSystemSettingsPage = systemAdminPage.gotoSystemSettings();
		systemAdminSystemSettingsPage.removeAllLabelsInDefaultScopeMicroblogging();
		systemAdminSystemSettingsPage.addLabelInDefaultScopeMicroBlogging(siteName);
		systemAdminSystemSettingsPage.saveSettings();
		dashboardPage = systemAdminSystemSettingsPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		//// ********************* To verify post with url **********************////////////////

		activityPage = dashboardPage.gotoActivityModule();
		activityPage.gotoDashboard();
		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		activityPage.sendTextInMicroblogTextBox(microblogPost);
		activityPage.clickOnInsertLinkButton();
		activityPage.verifyModal();

		Assert.assertTrue(activityPage.verifyTabsInInsertLinkModal("Recent"));
		Assert.assertTrue(activityPage.verifyTabsInInsertLinkModal("Browse"));
		Assert.assertTrue(activityPage.verifyTabsInInsertLinkModal(external));
		Assert.assertTrue(activityPage.verifyTabsInInsertLinkModal(upload));
		Assert.assertTrue(activityPage.verifyTabsInInsertLinkModal("Search"));

		activityPage.clickOnTabsInInsertLinkModal(external);
		activityPage.verifyExternalTabOpenedInInsertLinkModal();
		Assert.assertTrue(activityPage.verifyShortURLCheckBox());

		activityPage.sendTextInExternalURLTextBox(externalURL);
		activityPage.clickOnModalButton(insert);
		activityPage.verifyRecentActivityArea();
		activityPage.clickOnMicroBlogPostButton();
		activityPage.verifyRecentActivityArea();

		activityPage.verifyPost(siteName, microblogPost);
		Assert.assertTrue(activityPage.verifyPost(siteName, externalURL));

		//// ********************* To verify post with image **********************////////////////

		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		activityPage.clickOnMicroblogTextBox();
		activityPage.clickOnInsertLinkButton();
		activityPage.verifyModal();
		activityPage.clickOnTabsInInsertLinkModal(upload);
		activityPage.verifyUploadTabOpenedInInsertLinkModal();
		activityPage.attachFileInInsertLinkModal(fileName);
		activityPage.clickOnModalButton(insert);
		activityPage.verifyRecentActivityArea();

		Assert.assertTrue(activityPage.verifyLabelInShareWithTextBox(siteName.trim()));
		activityPage.clickOnMicroBlogPostButton();
		activityPage.verifyRecentActivityArea();

		Assert.assertTrue(activityPage.verifyPost(siteName, fileName));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @created on 03/04/2018
	 */
	public void scenario2() throws InterruptedException
	{
		dashboardPage = activityPage.gotoDashboard();
		systemAdminPage = dashboardPage.gotoSystemAdmin();
		systemAdminSystemSettingsPage = systemAdminPage.gotoSystemSettings();
		systemAdminSystemSettingsPage.removeAllLabelsInDefaultScopeMicroblogging();
		systemAdminSystemSettingsPage.saveSettings();
		dashboardPage = systemAdminSystemSettingsPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		activityPage = dashboardPage.gotoActivityModule();
		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		activityPage.sendTextInMicroblogTextBox(activityMicroblogPost);
		Assert.assertTrue(activityPage.verifyLabelInShareWithTextBox(siteName.trim()));
		activityPage.clickOnMicroBlogPostButton();
		activityPage.verifyRecentActivityArea();
		Assert.assertTrue(activityPage.verifyPost(siteName, activityMicroblogPost));
	}

	/**
	 * @author vivek.mishra
	 * @created on 04/04/2018
	 */
	public void scenario3()
	{
		activityPage.clickOnOptionInMoreActionOfPost(siteName, activityMicroblogPost, delete);
		activityPage.verifyModal();
		Assert.assertTrue(activityPage.verifyModal(deleteModal));
		Assert.assertTrue(activityPage.verifyModalButton(cancel));
		Assert.assertTrue(activityPage.verifyModalButton(delete));
		Assert.assertTrue(activityPage.verifyModalMessage(deleteModalMessage));
		activityPage.clickOnModalButton(cancel);
		activityPage.verifyRecentActivityArea();
		Assert.assertTrue(activityPage.verifyPost(siteName, activityMicroblogPost));
		Assert.assertFalse(activityPage.verifyModal(deleteModal));

		activityPage.clickOnOptionInMoreActionOfPost(siteName, activityMicroblogPost, delete);
		activityPage.verifyModal();
		activityPage.clickOnModalButton(delete);
		Assert.assertFalse(activityPage.verifyPost(siteName, activityMicroblogPost));
	}

}
