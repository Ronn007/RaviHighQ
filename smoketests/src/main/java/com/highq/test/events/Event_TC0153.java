/**
 * 
 */
package com.highq.test.events;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminEventPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @creation date 24/01/2018
 */
public class Event_TC0153 extends BannerPageWeb
{
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	AdminEventPage adminEventPage;
	EventsPage eventsPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Event_TC0153";
	String domain = "eventorg.com";

	String editCommentLink = "Edit";
	String deleteCommentLink = "Delete";
	String replyCommentLink = "Reply";
	String eventName = "First Event " + getRandomString();
	String likeLink = "Like";
	String unlikeLink = "Unlike";
	String likedStatus = "You like this";
	String comment = "Comment1";
	String newComment = "Edited Comment";
	String reply = "Reply 1";

	int commentCount;

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @creation date 24/01/2018
	 */
	@Test
	public void EventTC0153() throws InterruptedException
	{
		TC0153_PreConditionsOfUserCreation();
		scenario1();
		scenario2();
		scenario4();
		scenario3();
		scenario5();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 24/01/2018
	 */
	public void TC0153_PreConditionsOfUserCreation() throws InterruptedException
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
		modulePermission.put("Events", moduleEditPermission);

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
		configurationData.setModuleList("home", "files", "wiki", "tasks", "events");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 24/01/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		if (!eventsPage.verifyEventInEventList(eventName))
			eventsPage.addEvent(eventName);
		eventsPage.verifyEventInEventList(eventName);

		eventsPage.clickOnEventInEventList(eventName);
		eventsPage.verifyEventInEditSection(eventName);
		commentCount = eventsPage.getCommentCountInEditSection();
		eventsPage.sendTextInCommentTextBoxInEditSection(comment);
		eventsPage.clickOnPostCommentButton();
		Assert.assertTrue(eventsPage.verifyCommentInEditSection(comment));

		Assert.assertTrue(eventsPage.verifyCommentIconInEditSection());
		Assert.assertTrue(commentCount < eventsPage.getCommentCountInEditSection());
	}

	/**
	 * @author vivek.mishra
	 * @creation date 26/01/2018
	 */
	public void scenario2()
	{
		eventsPage.clickOnOptionOfCommentInEditSection(editCommentLink, comment);
		Assert.assertTrue(eventsPage.verifyActiveCommentTextBoxInEditSection());
		Assert.assertTrue(eventsPage.verifyCommentBoxRenderedTextInEditSection(comment));
		eventsPage.editTextInCommentTextBoxInEditSection(newComment, comment);
		eventsPage.clickOnPostCommentButton();
		Assert.assertTrue(eventsPage.verifyCommentInEditSection(newComment));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 26/01/2018
	 */
	public void scenario4()
	{
		eventsPage.clickOnOptionOfCommentInEditSection(replyCommentLink, newComment);
		Assert.assertTrue(eventsPage.verifyReplyCommentRenderedTextInEditSection(getUserData(userNames[0].toLowerCase())));
		eventsPage.editReplyCommentTextBoxText(reply);
		eventsPage.clickOnPostReplyButtonInEditSection();
		Assert.assertTrue(eventsPage.verifyCommentInEditSection(reply));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 29/01/2018
	 */
	public void scenario3()
	{
		eventsPage.clickOnOptionOfCommentInEditSection(deleteCommentLink, newComment);
		eventsPage.clickOnDeleteButtonInDeleteModal();
		Assert.assertFalse(eventsPage.verifyCommentInEditSection(newComment));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 29/01/2018
	 */
	public void scenario5()
	{
		scenario5Case1();
		scenario5Case2();
	}

	/**
	 * @author vivek.mishra
	 * @creation date 29/01/2018
	 */
	public void scenario5Case1()
	{
		eventsPage.clickOnLikeUnlikeEventButonInEditSection(likeLink);
		Assert.assertTrue(eventsPage.verifyLikeUnlikeEventButtonInEditSection(unlikeLink));
		Assert.assertTrue(eventsPage.verifyLikeStatusTextInEditSection(likedStatus));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 29/01/2018
	 */
	public void scenario5Case2()
	{
		eventsPage.clickOnLikeUnlikeEventButonInEditSection(unlikeLink);
		Assert.assertTrue(eventsPage.verifyLikeUnlikeEventButtonInEditSection(likeLink));
		Assert.assertFalse(eventsPage.verifyLikeStatusTextInEditSection(likedStatus));
	}
}
