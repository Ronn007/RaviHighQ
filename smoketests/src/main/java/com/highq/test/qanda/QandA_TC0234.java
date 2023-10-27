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
import com.highq.pageobjects.base.AdminBidderOrganisationsPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminQAPermissionsPage;
import com.highq.pageobjects.base.AdminQandAGroupsPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.QandAPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 * @created on 20/04/2018
 */
public class QandA_TC0234 extends BannerPageWeb
{
	SystemAdminPage systemAdminPage;
	SearchUserPage searchUserPage;
	DocumentPage documentPage;
	AdminPage adminPage;
	ModulesPage modulesPage;
	AddUserPage addUserPage;
	AdminAdvancedPage adminAdvancedPage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AdminQAPermissionsPage adminQAPermissionsPage;
	QandAPage qandAPage;
	BaseQandA baseQandAPage;
	BannerPage bannerPage;
	AdminSecurityPage adminSecurityPage;
	AdminBidderOrganisationsPage adminBidderOrganisationsPage;
	AdminQandAGroupsPage adminQAGroupsPage;
	DocumentAddDataPage addDoc;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";

	String nPassword = "WikiNormal@123";
	String[] sellerUsers = {"s1", "s2", "s3", "s4", "s5"};
	String[] bidderUsers = {"b1", "b2", "b3"};
	String orgType = "Internal";
	String siteName = "QAndA_TC0234";
	String[] domain = {"bidder.com", "seller.com"};
	String[] question = {"My question 1", "My question 2"};
	String bidderGroup = "Bidder Group";
	String sellerGroup = "Seller Group";
	String fileName = "doc1.txt";

	String pendingQuestion = "Question submitted " + getRandomString();
	String draftQuestion = "Drafted question " + getRandomString();
	String question1 = "Question 1 " + getRandomString();
	String draftReply = "Draft reply";
	String finalReply = "Final reply";
	String approvalStatus = "Sent for approval";
	String pendingStatus = "Pending";
	String draftStatus = "Draft";
	String answeredStatus = "Answered";
	String pendingApprovalStatus = "Pending approval";
	String submit = "Submit";
	String saveAsDraft = "Save as draft";

	String msg = " Are you ready to restore?" + " <br/> " + " Permissions can't be restored and will inherit the parent folder permissions";

	String ok = "OK";

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 20/04/2018
	 */
	@Test
	public void QATC0234() throws InterruptedException, IOException
	{
		Precondition_TC0234();
		scenario1();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 20/04/2018
	 */
	private void Precondition_TC0234() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPage = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain[0], permissionOfOrg);
		orgData.put(domain[1], permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain[1], Arrays.asList(sellerUsers));
		userMap.put(domain[0], Arrays.asList(bidderUsers));

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		for (int i = 0; i < sellerUsers.length; i++)
			siteUserModulePermission.put(sellerUsers[i] + "@" + domain[1], modulePermission);
		for (int i = 0; i < bidderUsers.length; i++)
			siteUserModulePermission.put(bidderUsers[i] + "@" + domain[0], modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(nPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));

		// Give specific permissions to particular site leve;

		adminPage = gotoAdminModule();
		adminSecurityPage = adminPage.clickSecurityInLeftPanel();
		adminSecurityPage.setAdvanceQAPermissions(true);
		adminSecurityPage.saveAdvancedChanges();
		adminPage = adminSecurityPage.gotoAdminModule();
		adminBidderOrganisationsPage = adminPage.clickBidderOrganisationsInLeftPanel();

		String[] bidderDomain = {domain[0].split("\\.")[0]};
		adminBidderOrganisationsPage.setBidderOrganisation(bidderDomain[0], true);
		adminBidderOrganisationsPage.clickSave();
		adminPage = adminBidderOrganisationsPage.gotoAdminModule();
		adminQAGroupsPage = adminPage.clickOnQandAGroupsInLeftPannel();
		adminQAGroupsPage.verifyGroupList();
		if (!adminQAGroupsPage.verifyGroup(bidderGroup))
			adminQAGroupsPage.createBidderGroup(bidderGroup, bidderDomain);

		String[] sellerDomain = {domain[1].split("\\.")[0]};
		adminQAGroupsPage.verifyGroupList();
		if (!adminQAGroupsPage.verifyGroup(sellerGroup))
			adminQAGroupsPage.createSellerGroup(sellerGroup, sellerDomain);

		adminPage = adminQAGroupsPage.gotoAdminModule();
		adminQAPermissionsPage = adminPage.clickOnQandAPermissionsInLeftPanel();

		Map<String, Map<String, Map<String, Object>>> qaUserDataAndRoles = new LinkedHashMap<>();
		Map<String, Map<String, Object>> sellerUsersData = new LinkedHashMap<>();
		Map<String, Object> permissionData = new LinkedHashMap<>();
		permissionData.put("topic expert", "General");
		permissionData.put("reply to question", true);
		Map<String, Object> permissionData2 = new LinkedHashMap<>();
		permissionData2.put("seller admin", true);
		Map<String, Object> permissionData3 = new LinkedHashMap<>();
		permissionData3.put("approve replies", true);
		Map<String, Object> permissionData4 = new LinkedHashMap<>();
		permissionData4.put("reply to question", true);
		Map<String, Object> permissionData5 = new LinkedHashMap<>();
		permissionData5.put("view all questions", true);
		sellerUsersData.put(sellerUsers[0], permissionData);
		sellerUsersData.put(sellerUsers[1], permissionData2);
		sellerUsersData.put(sellerUsers[2], permissionData3);
		sellerUsersData.put(sellerUsers[3], permissionData4);
		sellerUsersData.put(sellerUsers[4], permissionData5);
		qaUserDataAndRoles.put(sellerDomain[0], sellerUsersData);

		Map<String, Map<String, Object>> bidderUsersData = new LinkedHashMap<>();
		Map<String, Object> permissionData6 = new LinkedHashMap<>();
		permissionData6.put("ask question", true);
		Map<String, Object> permissionData7 = new LinkedHashMap<>();
		permissionData7.put("submit question", true);
		Map<String, Object> permissionData8 = new LinkedHashMap<>();
		permissionData8.put("bidder admin", true);
		bidderUsersData.put(bidderUsers[0], permissionData6);
		bidderUsersData.put(bidderUsers[1], permissionData7);
		bidderUsersData.put(bidderUsers[2], permissionData8);
		qaUserDataAndRoles.put(bidderDomain[0], bidderUsersData);

		adminQAPermissionsPage.setQAndAPermissionUserWise(qaUserDataAndRoles);
		documentPage = adminQAPermissionsPage.gotoFileModule();
		if (!documentPage.verifyDocumentUploaded(fileName))
		{
			documentPage.selectItemFromAdd("Files");
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(fileName);
			documentPage.addFile(addDoc, null);
			documentPage.clickAddInAddFileModal();
			documentPage.verifyDocumentUploaded(fileName);
		}
		adminQAPermissionsPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @created on 20/04/2018
	 */
	public void scenario1()
	{
		scenario1_Case1();
		scenario1_Case2();
		scenario1_Case3();
		scenario1_Case4();
		scenario1_Case5();
		scenario1_Case6();
	}

	/**
	 * @author vivek.mishra
	 * @created on 24/04/2018
	 */
	public void scenario1_Case1()
	{
		bannerPage = login(bidderUsers[0] + "@" + domain[0], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.addQuestion(pendingQuestion);
		qandAPage.addQuestion(question1);

		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, approvalStatus));
		qandAPage.clickOnAskQuestionButton();
		qandAPage.verifyAskQuestionModal();
		qandAPage.sendTextInQuestionTextBox(draftQuestion);
		qandAPage.clickOnFootersInAskQuestionModal(saveAsDraft);
		qandAPage.verifyFadeModal();
		qandAPage.clickOnOkButtonInFadeModalDialog();
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(draftQuestion, draftStatus));
		qandAPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @created on 25/04/2018
	 */
	public void scenario1_Case2()
	{
		bannerPage = login(bidderUsers[1] + "@" + domain[0], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.clickOnFootersInAskQuestionModal(submit);
		qandAPage.verifyFadeModal();
		qandAPage.clickOnOkButtonInFadeModalDialog();
		qandAPage.verifyAskQuestionButton();
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, pendingApprovalStatus));

		qandAPage.clickOnQuestion(question1);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.clickOnFootersInAskQuestionModal(saveAsDraft);
		qandAPage.verifyFadeModal();
		qandAPage.clickOnOkButtonInFadeModalDialog();
		qandAPage.verifyAskQuestionButton();
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(question1, approvalStatus));
		qandAPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @created on 26/04/2018
	 */
	public void scenario1_Case3()
	{
		bannerPage = login(bidderUsers[2] + "@" + domain[0], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.clickOnFootersInAskQuestionModal(saveAsDraft);
		qandAPage.verifyFadeModal();
		qandAPage.clickOnOkButtonInFadeModalDialog();
		qandAPage.verifyAskQuestionButton();
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, pendingApprovalStatus));

		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.clickOnFootersInAskQuestionModal(submit);
		qandAPage.verifyFadeModal();
		qandAPage.clickOnOkButtonInFadeModalDialog();
		qandAPage.verifyAskQuestionButton();
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, pendingStatus));
		qandAPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @created on 26/04/2018
	 */
	public void scenario1_Case4()
	{
		bannerPage = login(sellerUsers[3] + "@" + domain[1], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.sendTextInQuickReplyTextBoxInQADetailContainer(draftReply);
		qandAPage.clickOnQADetailContainerBottomButtons(saveAsDraft);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, draftStatus));
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		Assert.assertTrue(qandAPage.verifyDraftReplyStatus(draftStatus));

		qandAPage.clickOnEditInMoreAction();
		qandAPage.verifyModal();
		qandAPage.editAnswerTextBoxInAnswermodal(finalReply);
		qandAPage.clickOnAnswerModalFooterButtons(submit);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, approvalStatus));
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();

		Assert.assertTrue(qandAPage.verifyReplyValueInQADetailContainer(finalReply));
		qandAPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @created on 26/04/2018
	 */
	public void scenario1_Case5()
	{
		bannerPage = login(sellerUsers[2] + "@" + domain[1], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		Assert.assertTrue(qandAPage.verifyReplyValueInQADetailContainer(finalReply));
		qandAPage.clickOnEditInMoreAction();
		qandAPage.verifyModal();
		qandAPage.clickOnAnswerModalFooterButtons(saveAsDraft);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, approvalStatus));

		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.verifyModal();
		qandAPage.clickOnAnswerModalFooterButtons(submit);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, pendingApprovalStatus));
		qandAPage.logout();
	}

	/**
	 * @author vivek.mishra
	 * @created on 26/04/2018
	 */
	public void scenario1_Case6()
	{
		bannerPage = login(sellerUsers[1] + "@" + domain[1], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.verifyModal();
		qandAPage.clickOnAnswerModalFooterButtons(saveAsDraft);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, pendingApprovalStatus));

		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		qandAPage.clickOnEditInMoreAction();
		qandAPage.verifyModal();
		qandAPage.clickOnAnswerModalFooterButtons(submit);
		Assert.assertTrue(qandAPage.verifyStatusInQuestionListing(pendingQuestion, answeredStatus));
		qandAPage.logout();

		bannerPage = login(bidderUsers[0] + "@" + domain[0], nPassword);
		dashboardPage = bannerPage.gotoDashboard();
		dashboardPage.searchSite(siteName);

		qandAPage = dashboardPage.gotoQAndAModule();
		qandAPage.clickOnQuestion(pendingQuestion);
		qandAPage.verifyQADetailContainer();
		Assert.assertTrue(qandAPage.verifyReplyValueInQADetailContainer(finalReply));
	}
}
