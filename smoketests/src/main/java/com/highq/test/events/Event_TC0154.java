/**
 * 
 */
package com.highq.test.events;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
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
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminActivityPage;
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
 * @creation date 06/02/2018
 */
public class Event_TC0154 extends BannerPageWeb
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
	AdminActivityPage adminActivityPage;
	ActivityPage activityPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Event_TC0154";
	String domain = "eventorg.com";

	ConfigurationData configurationData = new ConfigurationData();

	String share = "Share";
	String link = "Link";
	String eventName = "First Event " + getRandomString();
	String recipients = "Recipients";
	String subject = "Subject";
	String message = "Message";
	String shareWith = "Share with";
	String optional = "(optional)";
	String selectLink = "Select link";
	String microBlog = "Microblog";
	String invalidUserName = "xyz@xyz.com";
	String text, unm;
	String popUpModal = "Link successfully sent to:";
	String copyPasteMessage = "Copy and paste the below link into an email, blog or webpage";
	String sharedLink, shortLink;
	String messageBoXText = "Here is a link to \"" + eventName.trim() + "\":";
	String messageDownText = "The link will automatically be added after your message when it is sent";

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 06/02/2018
	 */
	@Test
	public void EventTC0154() throws InterruptedException, UnsupportedFlavorException, IOException
	{
		preconditions();
		scenario1();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 06/02/2018
	 */
	public void preconditions() throws InterruptedException
	{
		TC0154_PreConditionsOfUserCreation();
		TC0154_BasicRequireMents();
	}

	public void TC0154_BasicRequireMents()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();

		adminActivityPage = adminPageWeb.clickActivityInLeftPanel();
		adminActivityPage.enableMicroblogging(true);
		adminActivityPage.clickOnSave();

		adminActivityPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 06/02/2018
	 */
	public void TC0154_PreConditionsOfUserCreation() throws InterruptedException
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
		modulePermission.put("Activity", moduleEditPermission);

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
		configurationData.setModuleList("home", "files", "wiki", "activity", "events");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @creation date 06/02/2018
	 */
	public void scenario1() throws InterruptedException, UnsupportedFlavorException, IOException
	{
		/* Expected for Share via Email: */

		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		if (!eventsPage.verifyEventInEventList(eventName))
			eventsPage.addEvent(eventName);
		eventsPage.verifyEventInEventList(eventName);

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);

		Assert.assertTrue(eventsPage.verifyLabelInShareModal(recipients));
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(subject));
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(message));
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(optional));
		Assert.assertTrue(eventsPage.verifyCancelButtonInShareModal());
		Assert.assertTrue(eventsPage.verifySendButtonInShareModal());
		Assert.assertTrue(eventsPage.verifyOptionFromAutoSuggest(userNames[0] + "@" + domain));
		Assert.assertTrue(eventsPage.verifyOptionFromAutoSuggest(userNames[1] + "@" + domain));
		Assert.assertFalse(eventsPage.verifyOptionFromAutoSuggest(invalidUserName));

		eventsPage.clearRecipientsTextBoxInShareModal();
		eventsPage.sendTextInRecipientsTextBoxInShareModal(userNames[0] + "@" + domain);
		eventsPage.selectOptionFromAutoSuggest(userNames[0] + "@" + domain);
		text = eventsPage.getSubjectTextBoxTextInShareModal();
		Assert.assertTrue(text.trim().equalsIgnoreCase(getUserData(userNames[1].trim()) + " shared \"" + eventName.trim() + "\" with you"));
		eventsPage.clearSubjectTextBoxInShareModal();
		Assert.assertTrue(eventsPage.verifySendButtonDisabledInShareModal());
		eventsPage.sendTextInSubjectTextBoxInShareModal(text);

		eventsPage.clickOnSendButtonInShareModal();
		eventsPage.verifyModal();
		Assert.assertTrue(eventsPage.verifyPopUpModalText(userNames[0] + "@" + domain));
		Assert.assertTrue(eventsPage.verifyModal(popUpModal));

		eventsPage.clickOnPopUpModalCloseButton();

		/* Expected for Share via Link: */

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);
		eventsPage.clickOnLabelsOnHeaderInShareModal(link);

		Assert.assertTrue(eventsPage.verifyLabelInShareModal(selectLink));
		Assert.assertTrue(eventsPage.verifyCancelButtonInShareModal());
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(copyPasteMessage));

		sharedLink = eventsPage.getLinkTextBoxTextInShareModal();
		eventsPage.enableUseShortURLCheckBox();
		shortLink = eventsPage.getLinkTextBoxTextInShareModal();
		Assert.assertFalse(sharedLink.equals(shortLink));

		eventsPage.openCopiedURL(sharedLink);
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventName));
		eventsPage.closeCurrentTab();

		eventsPage.verifyModal(share);
		eventsPage.clickOnQuitButtonInShareModal();
		Assert.assertFalse(eventsPage.verifyModal(share));

		/* Expected for Share via Message: */

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);
		eventsPage.clickOnLabelsOnHeaderInShareModal(message);

		Assert.assertTrue(eventsPage.verifyLabelInShareModal(recipients));
		Assert.assertTrue(eventsPage.getMessageTextBoxTextInMessageTabInShareModal().trim().equals(messageBoXText.trim()));
		eventsPage.clearMessageTextBoxTextInMessageTabInShareModal();
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(message));
		Assert.assertTrue(eventsPage.verifySendButtonDisabledInShareModal());
		eventsPage.sendTextInRecipientsTextBoxInShareModal(userNames[0] + "@" + domain);
		eventsPage.selectOptionFromAutoSuggest(userNames[0] + "@" + domain);

		Assert.assertTrue(eventsPage.verifyCancelButtonInShareModal());
		Assert.assertTrue(eventsPage.verifySendButtonInShareModal());
		Assert.assertTrue(eventsPage.verifySendButtonDisabledInShareModal());

		eventsPage.clickOnCancelButtonInShareModal();
		Assert.assertFalse(eventsPage.verifyModal(share));

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);
		eventsPage.clickOnLabelsOnHeaderInShareModal(message);
		eventsPage.sendTextInRecipientsTextBoxInShareModal(userNames[0] + "@" + domain);
		eventsPage.selectOptionFromAutoSuggest(userNames[0] + "@" + domain);
		eventsPage.clickOnSendButtonInShareModal();
		Assert.assertFalse(eventsPage.verifyModal(share));

		eventsPage.logout();
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(userNames[1], messageBoXText.trim(),true));
			dashboardWeb.clickCancelInMessageBox();
		}

		dashboardWeb.logout();

		/* Expected for Share via Microblog: */

		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);
		eventsPage.clickOnLabelsOnHeaderInShareModal(microBlog);

		Assert.assertTrue(eventsPage.verifyLabelInShareModal(message));

		String msg = eventsPage.getMessageTextBoxTextInMicroblogTabInShareModal().trim();
		eventsPage.clearMessageTextBoxTextInMicroblogTabInShareModal();

		Assert.assertTrue(eventsPage.verifyLabelInShareModal(shareWith));
		Assert.assertTrue(eventsPage.verifyCancelButtonInShareModal());

		Assert.assertTrue(msg.equals(messageBoXText.trim()));
		Assert.assertTrue(eventsPage.verifyLabelInShareModal(messageDownText));
		Assert.assertTrue(eventsPage.verifyPostButtonInShareModal());

		eventsPage.removeSiteFromShareWithTextBoxInShareModal(siteName);
		Assert.assertTrue(eventsPage.verifyPostButtonDisabledInShareModal());
		eventsPage.selectTextFromAutoSuggestInShareWithInShareModal(siteName);
		Assert.assertTrue(eventsPage.verifyPostButtonDisabledInShareModal());

		eventsPage.removeSiteFromShareWithTextBoxInShareModal(siteName);
		eventsPage.sendTextInMessageBoxInMicroblogTabInShareModal(msg);
		Assert.assertTrue(eventsPage.verifyPostButtonDisabledInShareModal());

		eventsPage.clickOnCancelButtonInShareModal();
		Assert.assertFalse(eventsPage.verifyModal(share));

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventName, share);
		eventsPage.verifyModal(share);
		eventsPage.clickOnLabelsOnHeaderInShareModal(microBlog);
		eventsPage.clickOnPostButtonInShareModal();
		Assert.assertFalse(eventsPage.verifyModal(share));

		eventsPage.logout();
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		activityPage = dashboardWeb.gotoActivityModule();
		activityPage.verifySiteActivityMiddlePannel();
		activityPage.gotoPosts();
		activityPage.verifySiteActivityMiddlePannel();
		activityPage.verifyPost(siteName, messageBoXText);
		activityPage.clickOnPostLink(siteName, messageBoXText);

		activityPage.switchWindow();
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventName));
	}
}
