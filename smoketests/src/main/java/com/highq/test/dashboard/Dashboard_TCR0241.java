package com.highq.test.dashboard;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchContentPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;


/**
 * @author khushbu.dhandhukiya
 *
 */
public class Dashboard_TCR0241 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword ="Password@123";
	String author="Ravi Middha";
	String orgType = "Internal";
	String siteName = "Dashboard_TCR0241";
	String microblogPost = "This is my TCR0241";
	String posts = "Posts";
	String commentText="This is TC0241 comment " + getRandomString();
	String modelTitle="People who like this…";
	String microblogging="ON at system level, default OFF at site level for new sites";
	String postMicroblogValue = "This is TCR0241 test case microblog";
	String[] option= {"Like","Unlike"};
	
	DashboardPage dashboardPage;
	SearchContentPage searchContentWeb;
	BannerPage bannerPageWeb;
	DocumentPage documentWeb;
	ConfigurationData configurationData = new ConfigurationData();
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	AdminPage adminPage;
	ModulesPage modulesPage;
	AdminActivityPage adminActivityPage;
	ActivityPage activityPage;
	
	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException	
	 */
	@Test(priority = 1)
	public void dashboardTCR0241() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();
		scenario1();		
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardPage=bannerPageWeb.gotoDashboard();
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
		activityPage.clickOnRecentActivityTabs(posts);
		activityPage.clickOnCommentButtonOfPost(microblogPost, siteName);
		activityPage.sendCommentInPostCommentBox(microblogPost, siteName, commentText);
				
		activityPage.clickOnCommentPostButton();
		
		Assert.assertTrue(activityPage.verifyRecentActivityArea());
		Assert.assertTrue(activityPage.verifyPost(siteName, microblogPost));
		
						
		activityPage.clickOnOptionOfCommentOfPost(microblogPost, siteName, commentText, option[0]);	
							
		Assert.assertTrue(activityPage.verifyOperationOnCommentOfPost(microblogPost, siteName, commentText, option[1]));
		Assert.assertTrue(activityPage.getCommentCountOfPost(microblogPost, siteName) == 1);
		
		int afterLikeCount=activityPage.getCommentLikeCount(microblogPost, siteName, commentText, option[1]);
		
		activityPage.clickOnCommentLikeCount(microblogPost, siteName, commentText, option[1]);;
		
		activityPage.clickOnCloseLikeModal();
		activityPage.clickOnOptionOfCommentOfPost(microblogPost, siteName, commentText, option[1]);
		int beforeLikeCount=activityPage.getCommentLikeCount(microblogPost, siteName, commentText, option[1]);
		
		int totalDiffrence=afterLikeCount-beforeLikeCount;
		Assert.assertTrue(totalDiffrence==1);
		Assert.assertTrue(activityPage.verifyOperationOnCommentOfPost(microblogPost, siteName, commentText, option[0]));
		activityPage.logout();
			
	}
	
	@SuppressWarnings("unused")
	private void siteAndUserConfiguration() throws InterruptedException, IOException
	{		
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);		
		
		/**Set Microblogging = ON at system level, default OFF at site level for new sites.*/
		systemAdminWeb =  bannerPageWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.selectMicroBlogging(microblogging);
		systemSettingsWeb.saveSettings();
		systemAdminWeb=systemSettingsWeb.goBack();
		dashboardPage=systemAdminWeb.gotoDashboard();
		dashboardPage.clickOnpostTab();
		dashboardPage.enterPostMicroBlogValue(postMicroblogValue);
		dashboardPage.clickOnpostButton();
		logout();
	}
	
}

