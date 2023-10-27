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
public class QandA_TC0190 extends BannerPageWeb
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

	String advanceSearch = "Advanced search";
	String clearFilter = "Clear filters";
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0190";
	String domain = "qaorg.com";
	String fileName = "doc2.txt";
	String newFile = "doc1.txt";
	String question = "My first question";
	String invalidAuthor = "Invalid";
	String invalidId = "123456";
	String id;
	String no;

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void QATC0190() throws InterruptedException, IOException
	{
		preConditions();
		scenario1();
		scenario2();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void preConditions() throws InterruptedException, IOException
	{
		TC0190_PreConditionsOfUserCreation();
		TC0190_BasicRequirements();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void TC0190_PreConditionsOfUserCreation() throws InterruptedException, IOException
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
	 * @throws IOException
	 */
	private void TC0190_BasicRequirements() throws InterruptedException, IOException
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
		qAndAWeb = documentWeb.gotoQAndAModule();
		qAndAWeb.addQuestion(question);
		qAndAWeb.logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		qAndAWeb = dashboardWeb.gotoQAndAModule();
		qAndAWeb.sendTextInQuickSearchTextBox(question);
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyQuestionInQuestionListing(question));

		qAndAWeb.sendTextInQuickSearchTextBox(getUserData(userNames[1]));
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyAuthorInQuestionListing(userNames[1]));
		qAndAWeb.sendTextInQuickSearchTextBox(invalidAuthor);
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyNoQuestions());
		qAndAWeb.sendTextInQuickSearchTextBox(domain.split("\\.")[0]);
		// qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyOrganizationInQuestionListing(domain.split("\\.")[0]));

		id = qAndAWeb.getQuestionID(question);
		qAndAWeb.sendTextInQuickSearchTextBox(id);
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyQuestionID(question, id));
		no = qAndAWeb.getQuestionNO(question);
		qAndAWeb.sendTextInQuickSearchTextBox(no);
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyQuestionNO(question, no));
		qAndAWeb.sendTextInQuickSearchTextBox(invalidId);
		qAndAWeb.waitForLoader();
		Assert.assertTrue(qAndAWeb.verifyNoQuestions());
		qAndAWeb.clearQuickSearchTextBox();
		qAndAWeb.waitForLoader();
	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario2()
	{
		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		Assert.assertTrue(qAndAWeb.verifyAdvanceSearchModal());
		Assert.assertTrue(qAndAWeb.verifySearchButtonInAdvanceSearchModal());
		Assert.assertTrue(qAndAWeb.verifyCancelButtonInAdvanceSearchModal());

		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyQuestionInQuestionListing(question));

		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		Assert.assertTrue(qAndAWeb.verifyAdvanceSearchModal());
		qAndAWeb.clickOnCancelInAdvanceSearchModal();
		Assert.assertFalse(qAndAWeb.verifyAdvanceSearchModal());

		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Any of these words"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("ID"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Number"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Modified date"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("File/Folder"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Organisation "));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Author"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Status"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Priority"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("Read"));
		Assert.assertTrue(qAndAWeb.verifyFieldsInAdvanceSearchModal("New"));
		qAndAWeb.clickOnCancelInAdvanceSearchModal();

		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		qAndAWeb.sendTextInAdvanceSearchTextBoxes("Any of these words", question);
		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyQuestionInQuestionListing(question));

		qAndAWeb.clickOnOptionsInQuickSearch(clearFilter);
		qAndAWeb.waitForLoader();
		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		qAndAWeb.sendTextInAdvanceSearchTextBoxes("ID", id);
		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyQuestionID(question, id));

		qAndAWeb.clickOnOptionsInQuickSearch(clearFilter);
		qAndAWeb.waitForLoader();
		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		qAndAWeb.sendTextInAdvanceSearchTextBoxes("Number", no);
		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyQuestionNO(question, no));

		qAndAWeb.clickOnOptionsInQuickSearch(clearFilter);
		qAndAWeb.waitForLoader();
		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		String org = domain.split("\\.")[0];
		qAndAWeb.sendTextInAdvanceSearchTextBoxes("Organisation", org);
		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyOrganizationInQuestionListing(org));

		qAndAWeb.clickOnOptionsInQuickSearch(clearFilter);
		qAndAWeb.waitForLoader();
		qAndAWeb.clickOnOptionsInQuickSearch(advanceSearch);
		qAndAWeb.verifyAdvanceSearchModal();
		qAndAWeb.sendTextInAdvanceSearchTextBoxes("Author", userNames[1].toLowerCase() + "@" + domain);
		qAndAWeb.clickOnSearchInAdvanceSearchModal();
		Assert.assertTrue(qAndAWeb.verifyAuthorInQuestionListing(userNames[1]));
	}

}
