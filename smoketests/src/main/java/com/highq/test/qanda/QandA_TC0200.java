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
public class QandA_TC0200 extends BannerPageWeb
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
	QandAPage qAndAPage;
	BaseQandA baseQandAWeb;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0200";
	String domain = "qaorg.com";
	String[] questionSet = {"My question 1", "My question 2", "My question 3", "My question 4", "My question 5"};
	String question = "Question";
	String author = "Author";
	String organisation = "Organisation";
	String askedOn = "Asked on";
	String status = "Status";
	String priority = "Priority";
	String relatedTo = "Related to";
	String no = "No";
	String id = "ID";
	int length;
	ConfigurationData configurationData = new ConfigurationData();

	@Test
	public void QATC0200() throws InterruptedException, IOException
	{
		preConditions();
		scenario1();
	}

	public void preConditions() throws InterruptedException, IOException
	{
		TC0200_PreConditionsOfUserCreation();
		TC0200_BasicRequirements();
	}

	private void TC0200_PreConditionsOfUserCreation() throws InterruptedException
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

	private void TC0200_BasicRequirements()
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		qAndAPage = dashboardWeb.gotoQAndAModule();
		qAndAPage.verifyQuestionList();
		for (int i = 0; i < questionSet.length; i++)
		{
			if (!qAndAPage.verifyQuestionInQuestionListing(questionSet[i]))
				qAndAPage.addQuestion(questionSet[i]);
		}
		qAndAPage.logout();
	}

	public void scenario1()
	{
		length = questionSet.length;
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		qAndAPage = dashboardWeb.gotoQAndAModule();

		///////// verify Ascending order ////////////////////

		qAndAPage.clickOnCaretSign(question);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(question));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(question));
		
		qAndAPage.clickOnCaretSign(organisation);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(organisation));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(organisation));
		
		qAndAPage.clickOnCaretSign(author);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(author));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(author));
		
		qAndAPage.clickOnCaretSign(askedOn);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(askedOn));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(askedOn));
		
		qAndAPage.clickOnCaretSign(status);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(status));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(status));
		
		qAndAPage.clickOnCaretSign(priority);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(priority));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(priority));
		
		qAndAPage.clickOnCaretSign(relatedTo);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(relatedTo));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(relatedTo));
		
		qAndAPage.clickOnCaretSign(no);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(no));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(no));
		
		qAndAPage.clickOnCaretSign(id);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyUpCaretSign(id));
		Assert.assertTrue(qAndAPage.verifyAscendingOrder(id));

		///////// verify Descending order ////////////////////

		qAndAPage.clickOnCaretSign(question);
		qAndAPage.clickOnCaretSign(question);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(question));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(question));
		
		qAndAPage.clickOnCaretSign(organisation);
		qAndAPage.clickOnCaretSign(organisation);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(organisation));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(organisation));
		
		qAndAPage.clickOnCaretSign(author);
		qAndAPage.clickOnCaretSign(author);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(author));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(author));
		
		qAndAPage.clickOnCaretSign(askedOn);
		qAndAPage.clickOnCaretSign(askedOn);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(askedOn));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(askedOn));
		
		qAndAPage.clickOnCaretSign(status);
		qAndAPage.clickOnCaretSign(status);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(status));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(status));
		
		qAndAPage.clickOnCaretSign(priority);
		qAndAPage.clickOnCaretSign(priority);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(priority));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(priority));
		
		qAndAPage.clickOnCaretSign(relatedTo);
		qAndAPage.clickOnCaretSign(relatedTo);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(relatedTo));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(relatedTo));
		
		qAndAPage.clickOnCaretSign(id);
		qAndAPage.clickOnCaretSign(id);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(id));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(id));
		
		qAndAPage.clickOnCaretSign(no);
		qAndAPage.clickOnCaretSign(no);
		Assert.assertTrue(qAndAPage.verifyQuestionList());
		Assert.assertTrue(qAndAPage.verifyDownCaretSign(no));
		Assert.assertTrue(qAndAPage.verifyDescendingOrder(no));
	}
}
