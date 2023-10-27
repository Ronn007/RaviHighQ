/**
 * 
 */
package com.highq.test.blog;

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
import com.highq.pageobjects.base.AdminBlogPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @creation date 14/02/2018
 */
public class Blog_TC0179 extends BannerPageWeb
{
	DashboardPage dashboardWeb;
	BlogPage blogPage;
	AdminPage adminPageWeb;
	AdminBlogPage adminBlogPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Blog_TC0179";
	String domain = "blogorg.com";

	String blogName = "My first Blog " + getRandomString();
	String blogName2 = "My Second Blog " + getRandomString();
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

	String editedBlogName = "Edited Blog " + getRandomString();
	String editedContent = "Edited Content";
	String editedCategory = "Cat1";
	String editedTag = "Tag2";
	String editedFile = "doc2.txt";

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 14/02/2018
	 */
	@Test
	public void BlogTC0179() throws InterruptedException
	{
		TC0179_PreConditionsOfUserCreation();
		basicRequirements();
		scenario1();
		scenario2();
		scenario3();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 14/02/2018
	 */
	public void TC0179_PreConditionsOfUserCreation() throws InterruptedException
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

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(nPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "blog");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		adminPageWeb = gotoAdminModule();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 14/02/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogPage = dashboardWeb.gotoBlogModule();

		Map<String, String> blogData = new LinkedHashMap<>();
		blogData.put(title, blogName);
		blogData.put(content, blogContent);
		blogData.put(tag, blogTag);
		blogData.put(category, categoryName);
		blogData.put(attachments, fileName);
		blogData.put(settings, imageFile);

		blogPage.addBlog(blogData);
		blogPage.VerifyBlogTitleInDetailsSection(blogName);

		Assert.assertTrue(blogPage.VerifyBlogTitleInDetailsSection(blogName));
		Assert.assertTrue(blogPage.verifyHeaderImageAvailabilityInDetailsSection());
		Assert.assertTrue(blogPage.verifyBlogContent(blogContent));
		Assert.assertTrue(blogPage.VerifyBlogCategoryInDetailsSection(categoryName));
		Assert.assertTrue(blogPage.VerifyBlogTagInDetailsSection(blogTag));
		Assert.assertTrue(blogPage.VerifyAttachedFileInDetailsSection(fileName));

		blogPage.gotoBlogModule();
		Assert.assertTrue(blogPage.verifyTopRecentBlog(blogName));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 14/02/2018
	 */
	public void scenario2() throws InterruptedException
	{
		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName2);
		blogPage.addBlogContent(blogContent);
		blogPage.addBlogTag(blogTag);
		blogPage.selectCategory(categoryName);
		blogPage.clickOnTabsInBlogModule(settings);
		blogPage.selectOptionInPublishDateDropDown("Custom date");
		blogPage.clickOnWatchIconInEditSection();
		blogPage.verifyTimePickerModal();
		blogPage.clickOnMinuteIncrementButton();
		blogPage.clickOnMinuteIncrementButton();
		blogPage.clickOnMinuteIncrementButton();
		blogPage.clickOnWatchIconInEditSection();
		blogPage.clickOnSaveOnAddBlog();

		blogPage.VerifyBlogTitleInDetailsSection(blogName2);
		blogPage.gotoBlogModule();
		blogPage.clickOnOptionInLeftPannel("Scheduled posts");
		blogPage.verifyScheduledPostsPage();
		Assert.assertTrue(blogPage.verifyBlogInScheduledPosts(blogName2));

		blogPage.logout();
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogPage = dashboardWeb.gotoBlogModule();

		Assert.assertFalse(blogPage.verifyBlogInBlogList(blogName2));

		blogPage.logout();
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogPage = dashboardWeb.gotoBlogModule();

		if (!blogPage.verifyBlogInBlogList(blogName2))
			Thread.sleep(150000);
		blogPage.gotoBlogModule();
		Assert.assertTrue(blogPage.verifyBlogInBlogList(blogName2));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 06/03/2018
	 */
	public void scenario3()
	{
		blogPage.clickOnOptionInMoreActionInBlogList(blogName, "Edit");
		blogPage.sendTextInBlogTitle(editedBlogName);
		blogPage.addBlogContent(editedContent);
		blogPage.editBlogTag(blogTag, editedTag);
		blogPage.editCategory(categoryName, editedCategory);
		blogPage.clickOnTabsInBlogModule("Attachments");
		blogPage.editAttachedFileInAddBlobAttachmentTab(fileName, editedFile);
		blogPage.clickOnSaveOnAddBlog();

		blogPage.VerifyBlogTitleInDetailsSection(editedBlogName);
		Assert.assertTrue(blogPage.VerifyBlogTitleInDetailsSection(editedBlogName));
		Assert.assertTrue(blogPage.verifyBlogContent(editedContent));
		Assert.assertTrue(blogPage.VerifyBlogCategoryInDetailsSection(editedCategory));
		Assert.assertTrue(blogPage.VerifyBlogTagInDetailsSection(editedTag));
		Assert.assertTrue(blogPage.VerifyAttachedFileInDetailsSection(editedFile));
	}

	/**
	 * @author vivek.mishra
	 *         To add category for as precondition
	 * @created on 07/03/2018
	 */
	public void basicRequirements()
	{
		adminBlogPage = adminPageWeb.clickOnBlogInLeftPanel();
		adminBlogPage.verifyCategoryList();
		if (!adminBlogPage.verifyCategory(editedCategory))
			adminBlogPage.addCategory(editedCategory);
		adminBlogPage.verifyCategory(editedCategory);
		adminBlogPage.clickOnSave();
		logout();
	}

}
