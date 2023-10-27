package com.highq.test.people;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.EditProfilePage;
import com.highq.pageobjects.base.MyProfilePage;
import com.highq.pageobjects.base.PeoplePage;
import com.highq.pageobjects.base.ViewUserProfilePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class People_TCR0251 extends BannerPageWeb
{
	/** User Profile */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "People_TCR0251";
	String[] userNames = {"normalsmokeuser1", "siteadminsmokeuser1"};
	String domain = "smoke.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";
	String moreActionItem = "Edit profile";
	String imageName = "profilepicture.jpg";
	String firstName = "Normal";
	String lastName = "User";
	String department = "New department";
	String jobTitle = "Manager";
	String emailAddress = "";

	String officePhone_countryCode = "1234";
	String officePhone_areaCode = "12345";
	String officePhone_number = "123456789012345";

	String mobilePhone_countryCode = "9999";
	String mobilePhone_phone = "8888888888";
	String invalidSecratoryEmail = "invalidemail";
	String validSecratoryEmail = "email@valid.com";
	String secretaryName = "Alexa";
	String bio = "Software Engineer since 10 years";
	String[] specialities = {"spec1", "spec2", "spec3"};
	String socialPlatform = "LinkedIn";
	String socialURL = "http://www.linkedin.com/data";
	String messagingPlatform = "Google Talk";
	String messagingData = "Sending a message via third party internet service";
	String linkName = "New Link";
	String linkURL = "http://www.google.com";
	String companyName = "smoke";
	String companyColumn = "Company";
	String jobTitleColumn = "Job title";
	String fromColumn = "From";
	String toColumn = "To";
	String fromYearColumn = "fromyear";
	String toYearColumn = "toyear";

	String experienceDetails_Company = "Google";
	String experienceDetails_JobTitle = "Data Scientist";
	String experienceDetails_FromMonth = "January";
	String experienceDetails_FromYear = "2012";
	String experienceDetails_ToMonth = "March";
	String experienceDetails_ToYear = "2017";
	String jsonFileName = "preConfiguration_People_TCR0251.json";

	String userForLogin;

	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AdminSecurityPage adminSecurityWeb;
	PeoplePage peopleWeb;
	MyProfilePage myprofileWeb;
	ViewUserProfilePage viewUserProfileWeb;
	EditProfilePage editProfileWeb;
	BannerPage bannerPageWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void PeopleTCR0251() throws InterruptedException, IOException, JSONException
	{
		scenario1();
	}

	private void scenario1() throws InterruptedException, IOException, JSONException
	{
		/** Scenario 1: Edit User Profile */
		precondition();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		viewUserProfileWeb = peopleWeb.clickUserName(userNames[0] + "@" + domain);
		viewUserProfileWeb.gotoSummaryInLeftPanel();
		viewUserProfileWeb.openMoreActionsMenu();
		assertTrue(viewUserProfileWeb.verifyMoreActionsMenuItem(moreActionItem));
		logout();

		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		/** Search site and verify Edit profile button is not visible when other user's profile is opened */
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		viewUserProfileWeb = peopleWeb.clickUserName(userNames[1] + "@" + domain);
		viewUserProfileWeb.gotoSummaryInLeftPanel();
		assertTrue(!viewUserProfileWeb.verifyEditProfileButtonVisibility());

		/** Verify Edit profile button is visible when user views its own profile */
		dashboardWeb = viewUserProfileWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		peopleWeb = dashboardWeb.gotoPeopleModule();
		viewUserProfileWeb = peopleWeb.clickUserName(userNames[0] + "@" + domain);
		viewUserProfileWeb.gotoSummaryInLeftPanel();
		editProfileWeb = viewUserProfileWeb.clickEditProfileButton();
		/** Change profile picture */
		editProfileWeb.changeProfilePicture(imageName);
		assertTrue(editProfileWeb.verifyRemoveIconOnProfilePic());
		/** Remove profile picture and verify Remove icon is not available on default profile picture */
		editProfileWeb.removeProfilePicture();
		assertTrue(!editProfileWeb.verifyRemoveIconOnProfilePic());

		editProfileWeb.clearFirstName();
		editProfileWeb.clearLastName();
		editProfileWeb.clickSave();
		/** Verify enter first name and enter last name error is shown if we save profile changes leaving first name and last name fields blank */
		assertTrue(editProfileWeb.verifyEnterFirstNameError() && editProfileWeb.verifyEnterLastNameError());

		editProfileWeb.setFirstName(firstName);
		editProfileWeb.setLastName(lastName);
		editProfileWeb.setJobTitle(jobTitle);
		/** Add department if it is not added and select */
		if (!editProfileWeb.verifyDepartmentIsPresent(department))
		{
			editProfileWeb.addNewDepartment(department);
		}
		editProfileWeb.selectDepartment(department);
		assertTrue(editProfileWeb.verifyDepartmentIsPresent(department));

		/** Verify company name is disabled */
		assertTrue(!editProfileWeb.verifyCompanyInputIsEnabled());
		/** Clear email user id input;save and verify Enter email address error is shown */
		// editProfileWeb.clearEmailUserID();
		// editProfileWeb.clickSave();
		// assertTrue(editProfileWeb.verifyEnterEmailAddressError());
		//
		// editProfileWeb.setEmailAddress(userNames[0]+"@"+domain);
		editProfileWeb.setOfficePhone(officePhone_countryCode, officePhone_areaCode, officePhone_number);
		editProfileWeb.setMobilePhone(mobilePhone_countryCode, mobilePhone_phone);
		editProfileWeb.setSecretaryName(secretaryName);

		/** Enter invalid secretary email address and verify Invalid secretary email error is shown */
		editProfileWeb.setSecretaryEmail(invalidSecratoryEmail);
		editProfileWeb.clickSave();
		assertTrue(editProfileWeb.verifyEnterValidSecretoryEmailError());

		editProfileWeb.setSecretaryEmail(validSecratoryEmail);
		editProfileWeb.setBio(bio);
		editProfileWeb.clearAllSpecialities();
		editProfileWeb.setSpecialities(specialities);
		editProfileWeb.clickAddSocial();
		editProfileWeb.selectSocialPlatform(socialPlatform);
		editProfileWeb.setSocialURL(socialURL);
		editProfileWeb.clickAddMessaging();
		editProfileWeb.selectMessagePlatform(messagingPlatform);
		editProfileWeb.setMessageText(messagingData);
		editProfileWeb.clickAddLinks();
		editProfileWeb.setLinkName(linkName);
		editProfileWeb.setLinkURL(linkURL);

		Map<String, String> experienceDetailsMap = new LinkedHashMap<String, String>();
		experienceDetailsMap.put(companyColumn, experienceDetails_Company);
		experienceDetailsMap.put(jobTitleColumn, experienceDetails_JobTitle);
		experienceDetailsMap.put(fromColumn, experienceDetails_FromMonth);
		experienceDetailsMap.put(fromYearColumn, experienceDetails_FromYear);
		experienceDetailsMap.put(toColumn, experienceDetails_ToMonth);
		experienceDetailsMap.put(toYearColumn, experienceDetails_ToYear);
		if (editProfileWeb.verifyExperienceJobTitle(experienceDetails_JobTitle))
			editProfileWeb.removeExperience(experienceDetails_JobTitle);

		editProfileWeb.clickAddExperienceButton();
		editProfileWeb.addExperienceDetails(experienceDetailsMap, false);
		editProfileWeb.clickSaveInAddExperience();

		/* Remove social, messaging and link and verify */
		int count = 0;
		count = editProfileWeb.fetchRemoveIconCountOfSocialPlatforms();
		editProfileWeb.removeLastSocialLink();
		assertTrue(editProfileWeb.verifyRemoveIconCountOfSocialPlatforms(count - 1));

		count = editProfileWeb.fetchRemoveIconCountOfMessagingPlatforms();
		editProfileWeb.removeLastMessagingLink();
		assertTrue(editProfileWeb.verifyRemoveIconCountOfMessagingPlatforms(count - 1));

		count = editProfileWeb.fetchRemoveIconCountOfLinksPlatforms();
		editProfileWeb.removeLastLinks();
		assertTrue(editProfileWeb.verifyRemoveIconCountOfLinksPlatforms(count - 1));

		viewUserProfileWeb = editProfileWeb.clickSave();
		viewUserProfileWeb.gotoFullProfileInLeftPanel();
		// assertTrue(viewUserProfileWeb.verifyFullUserName(firstName+" "+lastName));
		assertTrue(viewUserProfileWeb.verifyJobTitle(jobTitle));
		assertTrue(viewUserProfileWeb.verifyDepartment(department));
		assertTrue(viewUserProfileWeb.verifyCompanyName(companyName));
		assertTrue(viewUserProfileWeb.verifyEmail(userNames[0] + "@" + domain));
		assertTrue(viewUserProfileWeb.verifyOfficePhone(officePhone_countryCode, officePhone_areaCode, officePhone_number));
		assertTrue(viewUserProfileWeb.verifyMobilePhone(mobilePhone_countryCode, mobilePhone_phone));
		assertTrue(viewUserProfileWeb.verifySecretaryName(secretaryName));
		viewUserProfileWeb.gotoSummaryInLeftPanel();
		assertTrue(viewUserProfileWeb.verifySecretaryEmail(validSecratoryEmail));
		viewUserProfileWeb.gotoFullProfileInLeftPanel();
		assertTrue(viewUserProfileWeb.verifyBio(bio));
		assertTrue(viewUserProfileWeb.verifySpecialities(specialities));
		assertTrue(viewUserProfileWeb.verifyExperienceCompany(experienceDetails_Company));
		assertTrue(viewUserProfileWeb.verifyExperienceJobTitle(experienceDetails_JobTitle));
		assertTrue(viewUserProfileWeb.verifyExperienceTimePeriod(experienceDetails_FromMonth, experienceDetails_FromYear, experienceDetails_ToMonth, experienceDetails_ToYear));

		LinkedHashMap<String, String> linkMap = new LinkedHashMap<String, String>();
		linkMap.put(linkName, linkURL);
		assertTrue(viewUserProfileWeb.verifyLinkAndLinkURL(linkMap));

		LinkedHashMap<String, String> socialMap = new LinkedHashMap<String, String>();
		socialMap.put(socialPlatform, socialURL);
		assertTrue(viewUserProfileWeb.verifySocialPlatformAndLink(socialMap));

		assertTrue(viewUserProfileWeb.verifyMessaging(messagingData));

		logout();
	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		/** Create site and add normal user, site admin */
		preconfiguration();

	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}

}
