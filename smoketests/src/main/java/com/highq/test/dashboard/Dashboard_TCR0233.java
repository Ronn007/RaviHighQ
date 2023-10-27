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
 * @created on 17/03/2018
 */
public class Dashboard_TCR0233 extends BannerPageWeb
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
	String siteName = "Dashboard_TCR0233";

	String wikiName = "My Wiki " + getRandomString();
	String blogName = "My Blog " + getRandomString();
	String blogContent = "Blog Content";
	String microblogPost = "This is my microblog";
	String categoryName = "Default";
	String activity = "Activity";
	String filter = "Filter";
	String sites = "Sites";
	String posts = "Posts";
	String all = "All";
	String contentType = "Content type";
	String people = "People";
	String fileName = "doc1.txt";

	String profileName = "Ravi Middha";

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws ParseException
	 * @throws IOException
	 * @creation date 17/03/2018
	 */
	@Test
	public void DashboardTCR0233() throws InterruptedException, ParseException, IOException
	{
		scenario1();
		scenario2();
		scenario3();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @creation date 17/03/2018
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPage = login(sysAdminEmail, sysAdminPassword);
		dashboardPage = bannerPage.gotoDashboard();
		systemAdminPage = dashboardPage.gotoSystemAdmin();
		systemAdminSystemSettingsPage = systemAdminPage.gotoSystemSettings();
		systemAdminSystemSettingsPage.removeAllLabelsInDefaultScopeMicroblogging();
		systemAdminSystemSettingsPage.saveSettings();
		dashboardPage = systemAdminSystemSettingsPage.gotoDashboard();
		dashboardPage.searchSite(siteName);
		adminPage = dashboardPage.gotoAdminModule();
		modulesPage = adminPage.clickOnModulesInLeftPanel();
		modulesPage.enableAllModules();
		adminPage = modulesPage.gotoAdminModule();
		adminActivityPage = adminPage.clickActivityInLeftPanel();
		adminActivityPage.enableMicroblogging(true);
		adminActivityPage.clickOnSave();

		// *********************** To Add a file ****************************///

		documentPage = adminActivityPage.gotoFileModule();
		documentPage.selectItemFromAdd("Files");
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();
		documentPage.verifyDocumentUploaded(fileName);

		// *********************** To Add a wiki ****************************///

		wikiPage = documentPage.gotoWikiModule();
		wikiPage.clickOnAddWiki();
		wikiPage.setTitle(wikiName);
		wikiPage.saveWiki();
		wikiPage.verifyWikiTitle(wikiName);

		// *********************** To Add a blog ****************************///

		blogPage = wikiPage.gotoBlogModule();
		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName);
		blogPage.addBlogContent(blogContent);
		blogPage.selectCategory(categoryName);
		blogPage.clickOnSaveOnAddBlog();
		blogPage.VerifyBlogTitleInDetailsSection(blogName);

		// *********************** To Add a microblog post ****************************///

		activityPage = blogPage.gotoActivityModule();
		activityPage.gotoDashboard();
		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		activityPage.sendTextInMicroblogTextBox(microblogPost);
		activityPage.sendTextInShareWithTextBox(siteName);
		activityPage.clickOnMicroBlogPostButton();
		activityPage.verifyPost(siteName, microblogPost);
		activityPage.clickOnRecentActivityTabs(all);
		activityPage.verifyRecentActivityArea();
		Assert.assertTrue(activityPage.verifyPost(siteName, microblogPost));
		Assert.assertTrue(activityPage.verifyUserName(siteName, fileName, profileName));
		Assert.assertTrue(activityPage.verifyPost(siteName, blogName));
		Assert.assertTrue(activityPage.verifyPost(siteName, wikiName));

		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.verifyRecentActivityArea();
		Assert.assertTrue(activityPage.verifyPost(siteName, microblogPost));
		Assert.assertFalse(activityPage.verifyUserName(siteName, fileName, profileName));
		Assert.assertFalse(activityPage.verifyPost(siteName, blogName));
		Assert.assertFalse(activityPage.verifyPost(siteName, wikiName));

		activityPage.clickOnRecentActivityTabs(activity);
		activityPage.verifyRecentActivityArea();
		Assert.assertTrue(activityPage.verifyPost(siteName, blogName));
		Assert.assertTrue(activityPage.verifyPost(siteName, wikiName));
		Assert.assertTrue(activityPage.verifyUserName(siteName, fileName, profileName));
		Assert.assertFalse(activityPage.verifyPost(siteName, microblogPost));
	}

	/**
	 * @author vivek.mishra
	 * @created on 20/03/2018
	 */
	public void scenario2()
	{
		activityPage.verifyActivityTabInRecentActivity();
		activityPage.clickOnRecentActivityTabs(filter);

		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown(sites));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown(contentType));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown(people));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Save current filters as default"));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Restore default system filters"));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Clear filters"));

		activityPage.expandArrowsInFilterDropDown(sites);

		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Favourite sites"));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Sites I am a member of"));
		Assert.assertTrue(activityPage.verifySiteListInFilterDropDown());

		activityPage.expandArrowsInFilterDropDown(contentType);

		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Blog"));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Wiki"));
		Assert.assertTrue(activityPage.verifyOptionsInFilterDropDown("Files"));

		activityPage.expandArrowsInFilterDropDown(people);

		Assert.assertTrue(activityPage.verifyUserListInFilterDropDown());
		activityPage.clickOnRecentActivityTabs(filter);
	}

	/**
	 * @author vivek.mishra
	 * @throws ParseException
	 * @created on 20/03/2018
	 */
	public void scenario3() throws ParseException
	{
		Assert.assertTrue(activityPage.verifyUserName(siteName, fileName, profileName));
		Assert.assertTrue(activityPage.verifyUploadedFile(siteName, fileName));
		Assert.assertTrue(activityPage.verifyFileSize(siteName, fileName));
	}
}
