/**
 * 
 */
package com.highq.test.qanda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
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
 * @since 4.2.9
 */
public class QandA_TC0189 extends BannerPageWeb
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
	QandAPage qAndAWeb;
	DocumentAddDataPage addDoc;
	BaseQandA baseQandAWeb;
	BannerPage bannerPageWeb;
	ConfigurationData configurationData = new ConfigurationData();

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0189";
	String domain = "qaorg.com";
	String fileName = "doc2.txt";
	String newFile = "doc1.txt";
	String folder = "folder1";
	String question = "My first question";
	String topic = "General";
	String priority = "Low";
	String editPriority = "High";
	String status = "Pending";
	String draftStatus = "Draft";
	String reply = "You have replied it";
	String answeredStatus = "Answered";
	String draftReply = "You have replied it for draft";
	String editedAnswer = "Answer details Edited";

	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	@Test
	public void QATC0189() throws InterruptedException
	{
		TC0189_PreConditionsOfUserCreation();
		scenario1();
		scenario2();
		scenario3();
	}

	private void TC0189_PreConditionsOfUserCreation() throws InterruptedException
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

	public void scenario1()
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		qAndAWeb = dashboardWeb.gotoQAndAModule();
		qAndAWeb.addQuestion(question);
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.clickOnReplyTagInQADetailContainer();
		qAndAWeb.sendTextInQuickReplyTextBoxInQADetailContainer(reply);
		qAndAWeb.clickOnQADetailContainerBottomButtons("Reply");

		qAndAWeb.verifyAskQuestionButton();
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.clickOnShowTagInQADetailContainer();
		Assert.assertTrue(qAndAWeb.verifyStatusValueInQADetailContainer(answeredStatus));
		Assert.assertTrue(qAndAWeb.verifyReplyValueInQADetailContainer(reply));
		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		qAndAWeb.verifyAskQuestionButton();
		Assert.assertTrue(qAndAWeb.verifyStatusInQuestionListing(question, answeredStatus));
	}

	public void scenario2()
	{
		qAndAWeb.addQuestion(question);
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnReplyTagInQADetailContainer();
		qAndAWeb.sendTextInQuickReplyTextBoxInQADetailContainer(draftReply);
		qAndAWeb.clickOnQADetailContainerBottomButtons("Save as draft");

		qAndAWeb.verifyAskQuestionButton();
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnShowTagInQADetailContainer();
		Assert.assertTrue(qAndAWeb.verifyStatusValueInQADetailContainer(draftStatus));
		Assert.assertTrue(qAndAWeb.verifyReplyValueInQADetailContainer(draftReply));
		Assert.assertTrue(qAndAWeb.verifyDraftReplyStatus(draftStatus));
		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		qAndAWeb.verifyAskQuestionButton();
		Assert.assertTrue(qAndAWeb.verifyStatusInQuestionListing(question, draftStatus));
	}

	public void scenario3()
	{
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnEditInMoreAction();
		qAndAWeb.verifyAnswerModal();
		qAndAWeb.editAnswerTextBoxInAnswermodal(editedAnswer);
		qAndAWeb.clickOnAnswerModalFooterButtons("Save as draft");
		qAndAWeb.verifyAskQuestionButton();
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();

		Assert.assertTrue(qAndAWeb.verifyDraftReplyStatus(draftStatus));
		Assert.assertTrue(qAndAWeb.verifyReplyValueInQADetailContainer(editedAnswer));

		qAndAWeb.clickOnEditInMoreAction();
		qAndAWeb.verifyAnswerModal();
		qAndAWeb.editAnswerTextBoxInAnswermodal(editedAnswer);
		qAndAWeb.clickOnAnswerModalFooterButtons("Submit");
		qAndAWeb.verifyAskQuestionButton();
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();

		Assert.assertTrue(qAndAWeb.verifyReplyValueInQADetailContainer(editedAnswer));
		qAndAWeb.clickOnShowTagInQADetailContainer();
		Assert.assertTrue(qAndAWeb.verifyStatusValueInQADetailContainer(answeredStatus));
		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		qAndAWeb.verifyAskQuestionButton();
		Assert.assertTrue(qAndAWeb.verifyStatusInQuestionListing(question, answeredStatus));
	}
}
