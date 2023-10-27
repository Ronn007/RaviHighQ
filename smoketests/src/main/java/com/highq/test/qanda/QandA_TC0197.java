/**
 * 
 */
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
public class QandA_TC0197 extends BannerPageWeb
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
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0197";
	String domain = "qaorg.com";
	String fileName = "doc1.txt";
	String question = "My first question " + getRandomString();
	String[] topic = {"Topic1", "Topic2"};
	String reply = "Reply data";
	String priority = "High";
	String comment = "Comment 1";
	String customMessage = "Your comment has been approved and a response will be posted to the Q&A module shortly";
	String status = "Pending";
	String status2 = "Answered";
	String draftCustomMessage = "This comment will be saved to your Draft items and can be submitted later from the Q&A module.";

	@Test
	public void QATC0197() throws InterruptedException, IOException
	{
		preConditions();
		scenario1();
		scenario2();
	}

	public void preConditions() throws InterruptedException, IOException
	{
		TC0197_PreConditionsOfUserCreation();
		TC0197_BasicRequirements();
	}

	private void TC0197_PreConditionsOfUserCreation() throws InterruptedException
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
		baseQandAWeb.setQAndAPermission(qAndADataAndRoles);
		adminQAndAWeb = baseQandAWeb.addTopics(topic);
		adminQAndAWeb.gotoDashboard();
		logout();
	}

	private void TC0197_BasicRequirements() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		documentWeb = dashboardWeb.gotoFileModule();
		if (!documentWeb.verifyDocumentUploaded(fileName))
		{
			documentWeb.selectItemFromAdd("Files");
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInAddFileModal();
			documentWeb.verifyDocumentUploaded(fileName);
		}
		documentWeb.logout();
	}

	public void scenario1()
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		qAndAWeb = dashboardWeb.gotoQAndAModule();

		qAndAWeb.addQuestion(question);
		qAndAWeb.replyQuestion(question, reply);
		qAndAWeb.verifyQuestionInQuestionListing(question);
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer("Comment");
		qAndAWeb.verifyAskQuestionModal();
		qAndAWeb.selectTopicInAskQuestionModal(topic[0]);
		qAndAWeb.selectPriorityInAskQuestionModal(priority);
		qAndAWeb.sendTextInQuestionTextBox(comment);
		qAndAWeb.clickOnFootersInAskQuestionModal("Submit");
		qAndAWeb.verifyFadeModal();

		Assert.assertTrue(qAndAWeb.verifyCustomMessage(customMessage));
		qAndAWeb.clickOnOkButtonInFadeModalDialog();
		qAndAWeb.verifyQuestionInQuestionListing(question);

		Assert.assertTrue(qAndAWeb.verifyStatusInQuestionListing(question, status));
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnShowTagInQADetailContainer();

		Assert.assertTrue(qAndAWeb.verifyStatusValueInQADetailContainer(status));
		Assert.assertTrue(qAndAWeb.verifyQuestionValueInQADetailContainer(comment));

		qAndAWeb.clickOnCloseButtonInQADetailContainer();
		qAndAWeb.verifyQuestionInQuestionListing(question);
	}

	public void scenario2()
	{
		qAndAWeb.replyQuestion(question, reply);
		qAndAWeb.verifyAskQuestionButton();
		qAndAWeb.verifyQuestionInQuestionListing(question);
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.selectOptionInTopMoreActionInQADetailContainer("Comment");
		qAndAWeb.verifyAskQuestionModal();
		qAndAWeb.selectTopicInAskQuestionModal(topic[0]);
		qAndAWeb.selectPriorityInAskQuestionModal(priority);
		qAndAWeb.sendTextInQuestionTextBox(comment);
		qAndAWeb.clickOnFootersInAskQuestionModal("Save as draft");
		qAndAWeb.verifyFadeModal();

		Assert.assertTrue(qAndAWeb.verifyCustomMessage(draftCustomMessage));
		qAndAWeb.clickOnOkButtonInFadeModalDialog();
		qAndAWeb.verifyQuestionInQuestionListing(question);

		Assert.assertTrue(qAndAWeb.verifyStatusInQuestionListing(question, status2));
		qAndAWeb.clickOnQuestion(question);
		qAndAWeb.verifyQADetailContainer();
		qAndAWeb.clickOnShowTagInQADetailContainer();

		Assert.assertTrue(qAndAWeb.verifyStatusValueInQADetailContainer(status2));
		Assert.assertTrue(qAndAWeb.verifyQuestionValueInQADetailContainer(comment));
	}
}
