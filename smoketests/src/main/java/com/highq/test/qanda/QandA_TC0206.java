package com.highq.test.qanda;

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
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminQAPermissionsPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.QandAPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 */
public class QandA_TC0206 extends BannerPageWeb
{
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	AdminQAPermissionsPage adminQAndAWeb;
	DocumentAddDataPage addDoc;
	QandAPage qAndAWeb;
	BaseQandA baseQandAWeb;
	BannerPage bannerPageWeb;

	String delete = "Delete";
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0206";
	String domain = "qaorg.com";
	String[] question = {"My question 1", "My question 2"};
	String customMessage = "Are you sure you want to delete this question trail?";
	String reply = "Reply Data";
	String comment = "First Comment";
	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void QATC0206() throws InterruptedException, IOException
	{
		TC0206_PreConditionsOfUserCreation();
		scenario1();
		scenario2();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void TC0206_PreConditionsOfUserCreation() throws InterruptedException, IOException
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
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		adminPageWeb = gotoAdminModule();
		Map<String, Map<String, Boolean>> qAndADataAndRoles = new LinkedHashMap<>();
		Map<String, Boolean> forumPermissions = new LinkedHashMap<>();
		forumPermissions.put("ask question", true);
		forumPermissions.put("submit question", true);
		forumPermissions.put("reply to question", true);
		forumPermissions.put("view all questions", true);
		qAndADataAndRoles.put(domain, forumPermissions);
		baseQandAWeb = new BaseQandA(driver);
		adminQAndAWeb = baseQandAWeb.setQAndAPermission(qAndADataAndRoles);
		adminQAndAWeb.gotoDashboard();
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		qAndAWeb = dashboardWeb.gotoQAndAModule();
		qAndAWeb.clickOnAskQuestionButton();
		qAndAWeb.verifyAskQuestionModal();
		qAndAWeb.sendTextInQuestionTextBox(question[0]);
		qAndAWeb.clickOnFootersInAskQuestionModal("Save as draft");
		qAndAWeb.verifyFadeModal();
		qAndAWeb.clickOnOkButtonInFadeModalDialog();
		qAndAWeb.verifyQuestionInQuestionListing(question[0]);

		qAndAWeb.clickOnQuestion(question[0]);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer(delete);
		Assert.assertTrue(qAndAWeb.verifyDeleteModal());
		Assert.assertTrue(qAndAWeb.verifyCustomMessage(customMessage));
		qAndAWeb.clickOnCancelButtonInDeleteModal();
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		Assert.assertTrue(qAndAWeb.verifyQuestionInQuestionListing(question[0]));

		qAndAWeb.clickOnQuestion(question[0]);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer(delete);
		Assert.assertTrue(qAndAWeb.verifyDeleteModal());
		Assert.assertTrue(qAndAWeb.verifyCustomMessage(customMessage));
		qAndAWeb.clickOnDeleteButtonInDeleteModal();
		qAndAWeb.verifyAskQuestionButton();
		Assert.assertFalse(qAndAWeb.verifyQuestionInQuestionListing(question[0]));
	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario2()
	{
		scenario2Precondition();
		qAndAWeb.clickOnQuestion(question[1]);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer(delete);
		Assert.assertTrue(qAndAWeb.verifyDeleteModal());
		Assert.assertTrue(qAndAWeb.verifyCustomMessage(customMessage));
		qAndAWeb.clickOnCancelButtonInDeleteModal();
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		qAndAWeb.verifyQuestionList();
		Assert.assertTrue(qAndAWeb.verifyQuestionInQuestionListing(question[1]));

		qAndAWeb.clickOnQuestion(question[1]);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer(delete);
		Assert.assertTrue(qAndAWeb.verifyDeleteModal());
		Assert.assertTrue(qAndAWeb.verifyCustomMessage(customMessage));
		qAndAWeb.clickOnDeleteButtonInDeleteModal();
		qAndAWeb.verifyAskQuestionButton();
		Assert.assertFalse(qAndAWeb.verifyQuestionInQuestionListing(question[1]));

	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario2Precondition()
	{
		qAndAWeb.addQuestion(question[1]);
		qAndAWeb.replyQuestion(question[1], reply);
		qAndAWeb.addComment(question[1], comment);
	}
}
