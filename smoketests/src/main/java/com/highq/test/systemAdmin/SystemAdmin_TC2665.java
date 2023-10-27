package com.highq.test.systemAdmin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.InsertSiteUserLabels;
import com.highq.labels.collaborate.SearchUserLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminGeneralPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.EditProfilePage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.SearchContentPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminExceptionDomainsPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.ViewUserProfilePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author ankit.motaval
 */
public class SystemAdmin_TC2665 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String normalPassword = "Password@1234";
	String domain = "highq.com";
	String anonymousDomain = "anonymous.highq.com";
	String displayName = "Rahul Dravid1";
	String[] userNames = {"rahul.dravid1", "rahul.dravid2", "rahul.dravid3"};
	int userid1;
	int userid2;
	String emailPrefix = "anonymiseduser";
	String imageName = "profilepicture.jpg";
	String address = "HighQ,  2nd floor, 55 King William Street,   London,  United Kingdom, EC4R 9AD,  +44 20 7220 5340";
	String[] specialities = {"spec1", "spec2", "spec3"};
	String socialPlatform = "LinkedIn";
	String socialURL = "http://www.linkedin.com/data";
	String messagingPlatform = "Google Talk";
	String messagingData = "Sending a message via third party internet service";
	String linkName = "New Link";
	String linkURL = "http://www.google.com";
	String secretaryName = "Shivam Soni";
	String secretaryEmail = "shivam.soni@highq.com";
	String department = "Automation";
	String officePhone_countryCode = "91";
	String officePhone_areaCode = "1234";
	String officePhone_number = "123456789012345";
	String mobilePhone_countryCode = "91";
	String mobilePhone_phone = "8888888888";
	String jobTitle = "Automation Engineer";
	String bio = "Sending a message via third party internet service";
	String orgType = "Internal";
	String date = "22 Jan 2018";
	String messageBody = "Private Message";
	String siteName = "SystemAdmin_TC2665";
	String eventName = "First Event";
	String anonymiseModelMessage1 = "Are you sure you want to anonymise the selected user(s)?";
	String anonymiseModelMessage2 = "Once anonymised, all information of the selected user(s) is deleted permanently and this action cannot be revoked.";
	String moduleName = "People";
	String jsonFileName = "preConfiguration_SystemAdmin_TC2665.json";
	String enable = "TRUE";
	String disable = "FALSE";
	String jobTitleDepartment = "Automation Engineer, Automation";
	String companyName = "HighQ";
	String category = "Default";
	String archive = "Archive";

	BannerPage bannerPageWeb;
	PreConfiguration preConfiguration;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserPageWeb;
	ViewUserProfilePage viewUserProfileWeb;
	EditProfilePage editProfileWeb;
	AdminPage adminPageWeb;
	EventsPage eventsPage;
	DashboardPage dashboardWeb;
	SearchContentPage searchContentWeb;
	AddUserPage addUserWeb;
	AspAdminPage aspAdminWeb;
	SystemAdminExceptionDomainsPage systemAdminExceptionDomainsWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminGeneralPage adminGeneralWeb;

	Map<String, List<String>> userMap;
	ConfigurationData configurationData = new ConfigurationData();

	WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test
	public void systemAdminTC2665() throws InterruptedException, IOException, JSONException
	{
		preConditionForScenario1();
		scenario1();
		preConditionForScenario2();
		scenario2();
	}

	public void scenario1() throws InterruptedException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		searchUserPageWeb = systemAdminWeb.gotoUserAdmin();
		searchUserPageWeb.clickOnSearch();

		Assert.assertFalse(searchUserPageWeb.verifyMakeAnonymiseButton());

		searchUserPageWeb.expandSearchInUserAdministration();
		searchUserPageWeb.selectUserStatus(SearchUserLabels.SEARCHUSERPAGE_ARCHIVED, false);
		searchUserPageWeb.clickOnSearch();

		Assert.assertTrue(searchUserPageWeb.verifyMakeAnonymiseButton());

		searchUserPageWeb.clickOnMakeAnonymise();

		Assert.assertTrue(bannerPageWeb.verifyAlertMessage(SearchUserLabels.MAKEANONYMISE_USERALERT));

		makeUserAnonymise();

		Assert.assertTrue(searchUserPageWeb.verifyMakeAnonymiseModel(SearchUserLabels.MAKEANONYMISE_MODEL_TITLE, SearchUserLabels.SEARCHUSERPAGE_MAKEANONYMISE));

		Assert.assertTrue(searchUserPageWeb.verifyMakeAnonymiseModelMessage(anonymiseModelMessage1) && searchUserPageWeb.verifyMakeAnonymiseModelMessage(anonymiseModelMessage2));

		searchUserPageWeb.clickOnCancelButtonOfAnonymiseModel();

		userid1 = bannerPageWeb.getUserID(userNames[0], domain);

		searchUserPageWeb.clickOnMakeAnonymise();
		searchUserPageWeb.clickOnAnonymiseButtonOfAnonymiseModel();
		userMap = new HashMap<>();
		userMap.put(anonymousDomain, Arrays.asList(emailPrefix + userid1));
		searchUserPageWeb.selectUserFromSearchPage(userMap, UserStatus.Archived, false);

		Assert.assertFalse(searchUserPageWeb.isCheckboxEnable(emailPrefix + userid1 + "@" + anonymousDomain));

		viewUserProfileWeb = searchUserPageWeb.clickOnSearchResultUser(emailPrefix + userid1);

		Assert.assertTrue(viewUserProfileWeb.verifyFullUserName(emailPrefix + userid1));
		Assert.assertTrue(viewUserProfileWeb.verifyEmail(emailPrefix + userid1 + "@" + anonymousDomain));
		Assert.assertFalse(viewUserProfileWeb.verifyEmailLink());
		Assert.assertFalse(viewUserProfileWeb.verifyUserAvtarAvailabe());
		Assert.assertFalse(viewUserProfileWeb.verifyEditProfileButtonVisibility());
		Assert.assertFalse(viewUserProfileWeb.isAvailableSendMessageButton());
		Assert.assertFalse(viewUserProfileWeb.isAvailableFollowButton());

		viewUserProfileWeb.gotoFullProfileInLeftPanel();
		Assert.assertFalse(viewUserProfileWeb.verifyEditProfileButtonVisibility());
		Assert.assertTrue(viewUserProfileWeb.verifyFullUserName(emailPrefix + userid1));
		Assert.assertTrue(viewUserProfileWeb.verifyEmail(emailPrefix + userid1 + "@" + anonymousDomain));
		Assert.assertFalse(viewUserProfileWeb.verifyEmailLink());
		Assert.assertFalse(viewUserProfileWeb.isAvailableSendMessageButton());
		Assert.assertFalse(viewUserProfileWeb.isAvailableFollowButton());

		Assert.assertFalse(viewUserProfileWeb.verifyUserAvtarAvailabe());
		Assert.assertFalse(viewUserProfileWeb.verifyJobTitleAndDepartmentAvailable(jobTitleDepartment));
		Assert.assertFalse(viewUserProfileWeb.verifyOfficeAddressAndCompanyAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifyMobileAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifyOfficeNumberAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifySecretaryNameAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifyBioAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifySocialPlatformAndLinkAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifyLinkAndLinkURLAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifySpecialitiesAvailable());
		Assert.assertFalse(viewUserProfileWeb.verifyOfficeAddressLabel());

		viewUserProfileWeb.gotoFollowingInLeftPanel();
		Assert.assertFalse(viewUserProfileWeb.verifyEditProfileButtonVisibility());
		Assert.assertFalse(viewUserProfileWeb.isAvailableSendMessageButton());
		Assert.assertFalse(viewUserProfileWeb.isAvailableFollowButton());

		viewUserProfileWeb.gotoFollowersInLeftPanel();
		Assert.assertFalse(viewUserProfileWeb.verifyEditProfileButtonVisibility());
		Assert.assertFalse(viewUserProfileWeb.isAvailableSendMessageButton());
		Assert.assertFalse(viewUserProfileWeb.isAvailableFollowButton());

		viewUserProfileWeb.gotoSharedImagesInLeftPanel();
		Assert.assertFalse(viewUserProfileWeb.verifyEditProfileButtonVisibility());
		Assert.assertFalse(viewUserProfileWeb.isAvailableSendMessageButton());
		Assert.assertFalse(viewUserProfileWeb.isAvailableFollowButton());
		logout();
	}

	public void scenario2() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		searchUserPageWeb = systemAdminWeb.gotoUserAdmin();
		userid1 = bannerPageWeb.getUserID(userNames[0], domain);
		userid2 = bannerPageWeb.getUserID(userNames[1], domain);
		makeUserAnonymise();
		searchUserPageWeb.clickOnAnonymiseButtonOfAnonymiseModel();
		dashboardWeb = bannerPageWeb.gotoDashboard();

		verifyMicroblogPost();

		clickOnSearchButton();
		clickOnSearchDropDown();
		selectSearchFilterOption(moduleName);
		searchContentWeb = searchContentInGlobalSearch(displayName);

		Assert.assertFalse(searchContentWeb.verifySearchBoxContent(displayName));

		clickOnPrivateMessage();
		Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(emailPrefix + userid1, messageBody, false));
		dashboardWeb.clickCancelInMessageBox();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();
		eventsPage.clickOnEventInEventList(eventName);

		Assert.assertTrue(eventsPage.verifyContactInEditSection(emailPrefix + userid2));

		eventsPage.clickOnListButton();
		eventsPage.deleteEventInEventList(eventName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		Assert.assertTrue(adminPageWeb.verifySiteOwnerName(emailPrefix + userid1));
		Assert.assertTrue(adminPageWeb.verifySiteOwnerEmail(emailPrefix + userid1 + "@" + anonymousDomain));

		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.clickAddUsers();
		userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames[0]));
		addUserWeb.enterEmailIds(userMap);

		Assert.assertTrue(addUserWeb.verifyEmptyResultMessage(InsertSiteUserLabels.INSERTSITEUSER_DISPLAY_TOKENFIELD_NORESULTFOUND));

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminExceptionDomainsWeb = systemAdminWeb.gotoExceptionDomains();
		systemAdminExceptionDomainsWeb.gotoExceptionDomain(anonymousDomain);

		Assert.assertFalse(systemAdminExceptionDomainsWeb.isEnableDomainNameTextBox());
		Assert.assertFalse(systemAdminExceptionDomainsWeb.isEnableStatusDropdown());
		Assert.assertFalse(systemAdminExceptionDomainsWeb.VerifySaveButton());
		Assert.assertFalse(systemAdminExceptionDomainsWeb.VerifyCancelButton());

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.setSiteName("newSite" + getRandomString());
		adminGeneralWeb.clickOnSave();
	}

	public void preConditionForScenario2() throws InterruptedException, IOException, JSONException
	{
		preConfiguration = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setEnableUserId(disable);
		aspConfigurationWeb.setEnableResetPasswordVerificationDetail(disable);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.gotoSystemAdmin();

		userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));
		searchUserPageWeb = preConfiguration.createAndResetUsers(userMap, sysAdminPassword, UserStatus.Active, false);
		searchUserPageWeb.expandSearchInUserAdministration();
		searchUserPageWeb.searchUser(userNames[0], UserStatus.Active, false);
		Map<UserPermission, Boolean> userRole = new HashMap<>();
		userRole.put(UserPermission.Create_Site, true);
		userRole.put(UserPermission.System_Admin, true);
		searchUserPageWeb.setRoles(userNames[0] + "@" + domain, userRole);
		logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, sysAdminPassword);
		preConfiguration.createSiteAndNavigateToAdmin(siteName);
		logout();

		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfiguration.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, normalPassword);
		clickOnPrivateMessage();
		clickOnNewMessages();
		sendPrivateMessage(messageBody, sysAdminEmail);

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		eventsPage.clickOnAddButton();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventName);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
		eventsPage.selectEmailFromContactTextBox(userNames[1] + "@" + domain);
		eventsPage.clickOnAddButtonInAddEventModal();
		eventsPage.clickOnEventInEventList(eventName);
		eventsPage.clickOnLikeIconInEditSection();
		eventsPage.gotoDashboard();
		logout();
	}

	public void preConditionForScenario1() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		preConfiguration = new PreConfiguration(driver);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);
		preConfiguration.setOrganisation(orgData);

		userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));
		searchUserPageWeb = preConfiguration.createAndResetUsers(userMap, sysAdminPassword, UserStatus.Active, false);

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setEnableResetPasswordVerificationDetail(enable);
		aspConfigurationWeb.setEnableUserId(enable);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		searchUserPageWeb = systemAdminWeb.gotoUserAdmin();
		searchUserPageWeb.searchUser(userNames[0], UserStatus.Active, false);
		viewUserProfileWeb = searchUserPageWeb.clickOnSearchResultUser(displayName);
		viewUserProfileWeb.openMoreActionsMenu();
		editProfileWeb = viewUserProfileWeb.clickEditProfileButton();
		editProfileWeb.changeProfilePicture(imageName);
		editProfileWeb.setJobTitle(jobTitle);
		editProfileWeb.addNewDepartment(department);
		editProfileWeb.setOfficePhone(officePhone_countryCode, officePhone_areaCode, officePhone_number);
		editProfileWeb.setMobilePhone(mobilePhone_countryCode, mobilePhone_phone);
		editProfileWeb.setSecretaryEmail(secretaryEmail);
		editProfileWeb.setSecretaryName(secretaryName);
		editProfileWeb.setDateOfBirth(date);
		editProfileWeb.setBRID("125");
		editProfileWeb.setBio(bio);
		editProfileWeb.setSpecialities(specialities);
		editProfileWeb.selectSocialPlatform(socialPlatform);
		editProfileWeb.setSocialURL(socialURL);
		editProfileWeb.selectMessagePlatform(messagingPlatform);
		editProfileWeb.setMessageText(messagingData);
		editProfileWeb.setLinkName(linkName);
		editProfileWeb.setLinkURL(linkURL);
		editProfileWeb.clickSave();
		logout();
	}

	private void makeUserAnonymise() throws InterruptedException
	{
		userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		searchUserPageWeb.selectUserFromSearchPage(userMap, UserStatus.Active, false);
		searchUserPageWeb.selectUserOptions(archive);

		searchUserPageWeb.selectUserFromSearchPage(userMap, UserStatus.Archived, false);
		searchUserPageWeb.clickOnMakeAnonymise();

	}

	private void verifyMicroblogPost()
	{
		dashboardWeb.clickOnActivity();
		Assert.assertTrue(dashboardWeb.verifyLikedUserName(emailPrefix + userid1));

		dashboardWeb.cilckOnUserRecentActivity(emailPrefix + userid1);
		Assert.assertTrue(dashboardWeb.verifyUserTitleInCard(emailPrefix + userid1));
		Assert.assertTrue(dashboardWeb.verifyUserEmailInCard(emailPrefix + userid1 + "@" + anonymousDomain));
		Assert.assertFalse(dashboardWeb.verifyUserEmailLinkOnCard());
		Assert.assertFalse(dashboardWeb.verifyUserAvtarAvailabe());
		Assert.assertFalse(dashboardWeb.verifyCompanyName(companyName));
		Assert.assertFalse(dashboardWeb.isAvailableSendMessageButtonOnCard());
		Assert.assertFalse(dashboardWeb.isAvailableFollowButtonOnCard());
	}

}
