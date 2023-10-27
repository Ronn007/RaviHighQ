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
 * @creation date 09/03/2018
 */
public class Blog_TC0182 extends BannerPageWeb
{
	DashboardPage dashboardWeb;
	BlogPage blogPage;
	BannerPage bannerPageWeb;
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Blog_TC0182";
	String domain = "blogorg.com";

	String blogName = "My first Blog " + getRandomString();
	String blogContent = "Blog Content";
	String blogName2 = "My second Blog " + getRandomString();
	String blogContent2 = "Blog Content 2";
	String blogName3 = "My third Blog " + getRandomString();
	String editedBlog = "Edited blog " + getRandomString();
	String blogContent3 = "Blog Content 3";
	String categoryName = "Default";
	String draftStatus = "Draft";
	String myDrafts = "My drafts";
	String autoSaveModal = "Auto-saved draft";
	String edit = "Edit";
	String cancel = "Cancel";
	String delete = "Delete";
	String deletedDraftPage = "Delete draft blog page?";

	String autoSaveModalMessage = "The system automatically saves a draft as you are editing. Auto-saves can be accessed by clicking your name in the top right and selecting \"My drafts\". Do you want to keep your auto-saved draft?";
	String resumeDiscardYellowStripMessage = "You were previously editing a draft. Do you want to resume editing or discard?";
	String deleteDraftConfirmationMessage = "Are you sure you want to delete this page?";
	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 08/03/2018
	 */
	@Test
	public void BlogTC0182() throws InterruptedException
	{
		TC0182_PreConditionsOfUserCreation();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 08/03/2018
	 */
	public void TC0182_PreConditionsOfUserCreation() throws InterruptedException
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
	 * @creation date 08/03/2018
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
		blogPage.setStatus(draftStatus);
		blogPage.clickOnSaveOnAddBlog();
		blogPage.gotoBlogModule();
		blogPage.clickOnOptionInLeftPannel(myDrafts);
		blogPage.verifyDeleteButtonInMyDrafts();

		Assert.assertTrue(blogPage.verifyBlogInMyDrafts(blogName));
		Assert.assertFalse(blogPage.verifyMoreAction());
	}

	/**
	 * @author vivek.mishra
	 * @cretaed on 12/03/2018
	 */
	public void scenario2()
	{
		blogPage.gotoBlogModule();
		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName2);
		blogPage.addBlogContent(blogContent2);
		blogPage.selectCategory(categoryName);
		blogPage.waitForAutoSaveNotification();
		blogPage.clickOnCancelOnAddBlog();
		blogPage.verifyModal();
		blogPage.verifyModal(autoSaveModal);

		Assert.assertTrue(blogPage.verifyModalMessage(autoSaveModalMessage));
		blogPage.clickOnDiscardButtonInAutoSaveDraftModal();

		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName2);
		blogPage.addBlogContent(blogContent2);
		blogPage.selectCategory(categoryName);
		blogPage.waitForAutoSaveNotification();
		blogPage.clickOnCancelOnAddBlog();
		blogPage.verifyModal();
		blogPage.clickOnSaveButtonInAutoSaveDraftModal();
		blogPage.gotoBlogModule();
		blogPage.clickOnOptionInLeftPannel(myDrafts);
		blogPage.verifyDeleteButtonInMyDrafts();
		blogPage.verifyBlogInMyDrafts(blogName2);
		Assert.assertTrue(blogPage.verifyBlogInMyDrafts(blogName2));
	}

	/**
	 * @author vivek.mishra
	 * @cretaed on 12/03/2018
	 */
	public void scenario3()
	{
		blogPage.gotoBlogModule();
		blogPage.clickOnAddPostButton();
		blogPage.sendTextInBlogTitle(blogName3);
		blogPage.addBlogContent(blogContent3);
		blogPage.selectCategory(categoryName);
		blogPage.clickOnSaveOnAddBlog();
		blogPage.VerifyBlogTitleInDetailsSection(blogName3);

		blogPage.clickOnOptionInMoreActionInDetailsSection(edit);
		blogPage.verifyBlogEditPage();
		blogPage.sendTextInBlogTitle(editedBlog);
		blogPage.waitForAutoSaveNotification();
		blogPage.clickOnCancelOnAddBlog();
		blogPage.verifyModal();
		blogPage.clickOnSaveButtonInAutoSaveDraftModal();

		blogPage.VerifyBlogTitleInDetailsSection(blogName3);
		blogPage.clickOnOptionInMoreActionInDetailsSection(edit);
		blogPage.verifyBlogEditPage();
		Assert.assertTrue(blogPage.verifyResumeDiscardYelloStripMessage(resumeDiscardYellowStripMessage));

		blogPage.clickOnResumeInYellowStrip();
		Assert.assertTrue(blogPage.verifyBlogEditPage());

		blogPage.gotoBlogModule();
		blogPage.clickOnBlogInBlogList(blogName3);
		blogPage.VerifyBlogTitleInDetailsSection(blogName3);
		blogPage.clickOnOptionInMoreActionInDetailsSection(edit);
		blogPage.verifyBlogEditPage();
		blogPage.clickOnDiscardInYellowStrip();
		blogPage.verifyBlogEditPage();
		Assert.assertTrue(blogPage.verifyBlogEditPage());

		blogPage.gotoBlogModule();
		blogPage.clickOnOptionInLeftPannel(myDrafts);
		blogPage.verifyDeleteButtonInMyDrafts();
		Assert.assertFalse(blogPage.verifyBlogInMyDrafts(blogName3));
	}

	/**
	 * @author vivek.mishra
	 * @cretaed on 12/03/2018
	 */
	public void scenario4()
	{
		blogPage.clickOnDeleteIconOfBlogInMyDrafts(blogName);
		blogPage.verifyModal();
		Assert.assertTrue(blogPage.verifyModal(deletedDraftPage));
		Assert.assertTrue(blogPage.verifyModalMessage(deleteDraftConfirmationMessage));
		Assert.assertTrue(blogPage.verifyModalButton(cancel));
		Assert.assertTrue(blogPage.verifyModalButton(delete));

		blogPage.clickOnModalButton(cancel);
		blogPage.verifyDeleteButtonInMyDrafts();
		Assert.assertFalse(blogPage.verifyModal(deletedDraftPage));

		blogPage.clickOnDeleteIconOfBlogInMyDrafts(blogName);
		blogPage.verifyModal();
		blogPage.clickOnModalButton(delete);
		Assert.assertFalse(blogPage.verifyBlogInMyDrafts(blogName));
	}

	/**
	 * @author vivek.mishra
	 * @cretaed on 12/03/2018
	 */
	public void scenario5()
	{
		Assert.assertTrue(blogPage.verifySearchTextBoxIsEmpty());
		blogPage.sendTextInSearchTextBox(blogName2);
		blogPage.verifyBlogListInMyDrafts();
		Assert.assertTrue(blogPage.verifyBlogInMyDrafts(blogName2));
	}

}
