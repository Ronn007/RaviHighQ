package com.highq.pageobjects.base;

import java.util.LinkedHashMap;
import com.highq.pageobjects.pages.EditProfileWeb;

public interface ViewUserProfilePage extends MyProfilePage
{
	public boolean verifyFullUserName(String userName);

	public EditProfileWeb clickEditProfileButton();

	public void openMoreActionsMenu();

	public boolean verifyMoreActionsMenuItem(String itemName);

	public void selectMoreActionsMenuItem(String itemName);

	public boolean verifyEditProfileButtonVisibility();

	public boolean verifyJobTitle(String jobTitle);

	public boolean verifyDepartment(String departmentName);

	public boolean verifyCompanyName(String company);

	public boolean verifyEmail(String emailAddress);

	public boolean verifyOfficePhone(String countryCode, String areaCode, String phone);

	public boolean verifyMobilePhone(String countryCode, String phone);

	public boolean verifySecretaryName(String name);

	public boolean verifySecretaryEmail(String email);

	public boolean verifyBio(String bioText);

	public boolean verifySpecialities(String... speciality);

	public boolean verifyLinkAndLinkURL(LinkedHashMap<String, String> linkMap);

	public boolean verifySocialPlatformAndLink(LinkedHashMap<String, String> socialMap);

	public boolean verifyMessaging(String... messaging);

	public boolean verifyExperienceJobTitle(String jobTitle);

	public boolean verifyExperienceCompany(String companyName);

	public boolean verifyExperienceTimePeriod(String fromMonth, String fromYear, String toMonth, String toYear);

	public boolean verifyOfficeAddressAndCompanyAvailable();

	public boolean verifySecretaryNameAvailable();

	public boolean verifySecretaryEmailAvailable();

	public boolean verifyJobTitleAndDepartmentAvailable(String jobTitleDepartment);

	public boolean verifyBioAvailable();

	public boolean verifySpecialitiesAvailable();

	public boolean verifySocialPlatformAndLinkAvailable();

	public boolean verifyLinkAndLinkURLAvailable();

	public boolean verifyUserAvtarAvailabe();

	public boolean verifyMobileAvailable();

	public boolean verifyOfficeNumberAvailable();

	public boolean verifyOfficeAddressLabel();

	public boolean verifyEmailLink();

	public boolean isAvailableSendMessageButton();

	public boolean isAvailableFollowButton();

	public boolean verifyProfilePicture();
}
