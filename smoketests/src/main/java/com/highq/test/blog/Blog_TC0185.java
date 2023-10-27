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
 * @author vivek.mishra
 * @created on 13/03/2018
 */
public class Blog_TC0185 extends BannerPageWeb
{
	DashboardPage dashboardWeb;
	BlogPage blogPage;
	BannerPage bannerPageWeb;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Blog_TC0185";
	String domain = "blogorg.com";

	String blogName = "My first Blog " + getRandomString();
	String blogContent = "Blog Content";
	String categoryName = "Default";
	String commentData = "Comment " + getRandomString();
	String editedCommentData = "Edited Comment " + getRandomString();
	String replyData = "Reply " + getRandomString();
	String delete = "Delete";
	String like = "Like";
	String unLike = "Unlike";
	String reply = "Reply";
	String edit = "Edit";
	String deleteCommentModal = "Delete comment?";
	String blogLikeMessage = "You like this";

	int commentCount, commentCount2, likeCount;

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 13/03/2018
	 */
	@Test
	public void BlogTC0185() throws InterruptedException
	{
		TC0185_PreConditionsOfUserCreation();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 13/03/2018
	 */
	public void TC0185_PreConditionsOfUserCreation() throws InterruptedException
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

		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 13/03/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogPage = dashboardWeb.gotoBlogModule();

		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName);
		blogPage.addBlogContent(blogContent);
		blogPage.selectCategory(categoryName);
		blogPage.clickOnSaveOnAddBlog();
		blogPage.VerifyBlogTitleInDetailsSection(blogName);
		commentCount = blogPage.getCommentCount();

		Assert.assertTrue(blogPage.verifyCommentIconInDetailSection());

		blogPage.clickOnCommentBlogButton();
		blogPage.sendTextInBlogCommentBox(commentData);
		blogPage.clickOnPostCommentButton();
		blogPage.verifyCommentBlock(commentData);
		commentCount2 = blogPage.getCommentCount();

		Assert.assertTrue(commentCount2 == 1);
		Assert.assertTrue(commentCount < commentCount2);
	}

	/**
	 * @author vivek.mishra
	 * @created on 13/03/2018
	 */
	public void scenario2()
	{
		likeCount = blogPage.getCommentLikeCount(commentData);
		blogPage.clickOnCommentOptionInDetailsSection(commentData, like);
		Assert.assertTrue(blogPage.verifyCommentOptionInDetailsSection(commentData, unLike));
		Assert.assertTrue(likeCount < blogPage.getCommentLikeCount(commentData));
	}

	/**
	 * @author vivek.mishra
	 * @created on 14/03/2018
	 */
	public void scenario3()
	{
		blogPage.clickOnCommentOptionInDetailsSection(commentData, reply);
		blogPage.appendTextInBlogCommentBox(replyData);
		blogPage.clickOnPostCommentButton();
		String lastReplyData = getUserData(userNames[0]) + " " + replyData;
		Assert.assertTrue(lastReplyData.equals(blogPage.getLastReplyOfCommentInDetailsSection()));
	}

	/**
	 * @author vivek.mishra
	 * @created on 14/03/2018
	 */
	public void scenario4()
	{
		blogPage.clickOnCommentOptionInDetailsSection(commentData, edit);
		Assert.assertTrue(blogPage.getCommentBoxRenderedText().equals(commentData.trim()));
		blogPage.sendTextInBlogCommentBox(editedCommentData);
		blogPage.clickOnPostCommentButton();
		blogPage.verifyCommentBlock(editedCommentData);
		Assert.assertTrue(blogPage.verifyComment(editedCommentData));
	}

	/**
	 * @author vivek.mishra
	 * @created on 14/03/2018
	 */
	public void scenario5()
	{
		blogPage.clickOnCommentOptionInDetailsSection(editedCommentData, delete);
		Assert.assertTrue(blogPage.verifyModal(deleteCommentModal));
		blogPage.clickOnModalButton(delete);
		Assert.assertFalse(blogPage.verifyComment(editedCommentData));
	}

	/**
	 * @author vivek.mishra
	 * @created on 14/03/2018
	 */
	public void scenario6()
	{
		blogPage.clickOnLikeUnlikeBlogButton();
		Assert.assertTrue(blogPage.verifyBlogLikeUnlikeStatus(unLike));
		Assert.assertTrue(blogPage.getBlogLikeMessage().equals(blogLikeMessage.trim()));
	}

}
