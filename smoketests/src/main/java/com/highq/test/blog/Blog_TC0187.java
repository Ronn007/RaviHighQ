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
 * @created on 16/03/2018
 */
public class Blog_TC0187 extends BannerPageWeb
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
	String siteName = "Blog_TC0187";
	String domain = "blogorg.com";

	String blogName = "My first Blog " + getRandomString();
	String blogContent = "Blog Content";
	String categoryName = "Default";
	String commentData = "Comment " + getRandomString();

	String exportToPDF = "Export to PDF";
	String export = "Export";

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 16/03/2018
	 */
	@Test
	public void BlogTC0187() throws InterruptedException
	{
		TC0187_PreConditionsOfUserCreation();
		basicRequirements();
		scenario1();
		scenario2();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 16/03/2018
	 */
	public void TC0187_PreConditionsOfUserCreation() throws InterruptedException
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
	 * @creation date 16/03/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		blogPage = dashboardWeb.gotoBlogModule();

		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName);
		blogPage.addBlogContent(blogContent);
		blogPage.selectCategory(categoryName);
		blogPage.clickOnSaveOnAddBlog();
		blogPage.VerifyBlogTitleInDetailsSection(blogName);

		blogPage.clickOnCommentBlogButton();
		blogPage.sendTextInBlogCommentBox(commentData);
		blogPage.clickOnPostCommentButton();
		blogPage.verifyCommentBlock(commentData);
		blogPage.clickOnOptionInMoreActionInDetailsSection(exportToPDF);
		blogPage.verifyPrintAndExportModal();
		blogPage.selectIncludeCommentsCheckBox();
		blogPage.clickOnModalButton(export);
		blogPage.waitForFileGettingDownloaded();

		Assert.assertTrue(blogPage.verifyDownloadedFile(blogName + ".pdf"));
	}

	/**
	 * @author vivek.mishra
	 * @created on 16/03/2018
	 */
	public void scenario2()
	{
		blogPage.clickOnOptionInMoreActionInDetailsSection("Print preview");
		blogPage.verifyPrintAndExportModal();
		blogPage.selectIncludeCommentsCheckBox();
		blogPage.clickOnModalButton("Print");
		blogPage.switchWindow();
		Assert.assertTrue(blogPage.verifyBlogInPrintPage(blogName));
		Assert.assertTrue(blogPage.verifyBlogCommentInPrintPage(commentData));
	}

	/**
	 * @author vivek.mishra
	 * @created on 16/03/2018
	 */
	public void basicRequirements()
	{
		adminBlogPage = adminPageWeb.clickOnBlogInLeftPanel();
		adminBlogPage.selectAllowCommentsCheckBox();
		adminBlogPage.selectAllowPDFExportCheckBox();
		adminBlogPage.clickOnSave();
		logout();
	}

}
