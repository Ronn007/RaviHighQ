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
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author badal.gandhi
 * @creation date 08/03/2018
 */
public class Blog_TC0183 extends BannerPageWeb
{
	DashboardPage dashboardWeb;
	BlogPage blogWeb;
	BannerPage bannerPageWeb;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String newPassword = "Password@123";
	String[] userNames = {"normal.user0183", "site.admin0183"};
	String orgType = "Internal";
	String siteName = "Blog_TC0183";
	String domain = "sl.com";

	String blogName = "My first Blog " + getRandomString();
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
	String deleteOperation = "Delete";

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @creation date 08/03/2018
	 */
	@Test(priority = 1)
	public void BlogTC0183() throws InterruptedException
	{
		preconditions();
		scenario1();
		scenario2();
		cleanup();
	}

	/**
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @creation date 08/03/2018
	 */
	private void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		blogWeb = dashboardWeb.gotoBlogModule();

		if (!blogWeb.verifyBlogInBlogList(blogName))
		{
			Map<String, String> blogData = new LinkedHashMap<>();
			blogData.put(title, blogName);
			blogData.put(content, blogContent);
			blogData.put(tag, blogTag);
			blogData.put(category, categoryName);

			blogWeb.addBlog(blogData);
			blogWeb.VerifyBlogTitleInDetailsSection(blogName);

		}
		else
		{
			Assert.assertTrue(blogWeb.verifyBlogInBlogList(blogName));
			blogWeb.clickOnBlogInBlogList(blogName);
		}

		Assert.assertTrue(!blogWeb.verifyFavouriteIconIsSelected(blogName));

		blogWeb.addBlogToFavourites(blogName);
		Assert.assertTrue(blogWeb.verifyFavouriteIconIsSelected(blogName));
		clickOnFavourite();
		Assert.assertTrue(searchInFavourite(blogName));

		logout();

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		blogWeb = dashboardWeb.gotoBlogModule();
		Assert.assertTrue(!blogWeb.verifyFavouriteIconIsSelected(blogName));

		logout();
	}

	/**
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @creation date 12/03/2018
	 */
	private void scenario2() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogWeb = dashboardWeb.gotoBlogModule();

		Assert.assertTrue(blogWeb.verifyFavouriteIconIsSelected(blogName));
		blogWeb.removeBlogFromFavourites(blogName);
		Assert.assertTrue(!blogWeb.verifyFavouriteIconIsSelected(blogName));

		clickOnFavourite();
		Assert.assertTrue(!searchInFavourite(blogName));

	}

	private void cleanup()
	{
		blogWeb = gotoBlogModule();
		blogWeb.clickOnOptionInMoreActionInBlogList(blogName, deleteOperation);
		Assert.assertTrue(blogWeb.verifyDeletePostMessage());
		blogWeb.deleteBlog();
		Assert.assertTrue(!blogWeb.isBlogDeleted(blogName));
		logout();

	}

	private void preconditions() throws InterruptedException
	{
		siteAndUserConfiguration();

	}

	/**
	 * @author badal.gandhi
	 * @throws InterruptedException
	 * @creation date 08/03/2018
	 */
	public void siteAndUserConfiguration() throws InterruptedException
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
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "blog");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		logout();
	}

}
