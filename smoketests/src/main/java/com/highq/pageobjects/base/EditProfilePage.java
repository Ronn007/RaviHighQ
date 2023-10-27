package com.highq.pageobjects.base;

import java.util.Map;
import com.highq.pageobjects.pages.ViewUserProfileWeb;

public interface EditProfilePage extends BannerPage
{
	public ViewUserProfileWeb clickSave();

	public ViewUserProfileWeb clickCancel();

	public void setFirstName(String firstName);

	public void setLastName(String lastName);

	public void clearFirstName();

	public void clearLastName();

	public void setJobTitle(String jobTitle);

	public void selectDepartment(String departmentName);

	public void clickAddNewDepartment();

	public void setDepartmentNameInAddNewDepartment(String departmentName);

	public void clickSaveInAddNewDepartment();

	public void addNewDepartment(String departmentName);

	public void setCompany(String companyName);

	public void selectOfficeAddress(String officeAddress);

	public void setEmailUserID(String emailUserID);

	public void selectEmailDomain(String domainName);

	public void setEmailAddress(String email);

	public void setOfficePhoneCountryCode(String countryCode);

	public void setOfficePhoneAreaCode(String areaCode);

	public void setOfficePhoneNumber(String phoneNumber);

	public void setOfficePhone(String countryCode, String areaCode, String phoneNo);

	public void setMobileCountryCode(String countryCode);

	public void setMobilePhoneNumber(String phoneNo);

	public void setMobilePhone(String countryCode, String mobileNo);

	public void setSecretaryName(String name);

	public void setSecretaryEmail(String email);

	public void clickChangeProfilePictureLink();

	public void saveProfileChange();

	public void changeProfilePicture(String imageName);

	public boolean verifyRemoveIconOnProfilePic();

	public void removeProfilePicture();

	public boolean verifyEnterFirstNameError();

	public boolean verifyEnterLastNameError();

	public boolean verifyDepartmentIsPresent(String departmentName);

	public boolean verifyEnterValidSecretoryEmailError();

	public void clearEmailUserID();

	public boolean verifyEnterEmailAddressError();

	public void setBio(String bio);

	public void setSpecialities(String... specialities);

	public void selectSocialPlatform(String platform);

	public void setSocialURL(String url);

	public void selectMessagePlatform(String platform);

	public void setMessageText(String message);

	public void setLinkName(String linkName);

	public void setLinkURL(String linkURL);

	public boolean verifyCompanyInputIsEnabled();

	public boolean verifyEmailIDIsEnabled();

	public void clearAllSpecialities();

	public void clickAddSocial();

	public void clickAddMessaging();

	public void clickAddLinks();

	public int fetchRemoveIconCountOfSocialPlatforms();

	public boolean verifyRemoveIconCountOfSocialPlatforms(int expectedCount);

	public void removeLastSocialLink();

	public void removeLastMessagingLink();

	public int fetchRemoveIconCountOfMessagingPlatforms();

	public boolean verifyRemoveIconCountOfMessagingPlatforms(int expectedCount);

	public void removeLastLinks();

	public int fetchRemoveIconCountOfLinksPlatforms();

	public boolean verifyRemoveIconCountOfLinksPlatforms(int expectedCount);

	public void clickAddExperienceButton();

	public void setCompanyNameInAddExperience(String companyName);

	public void setJobTitleInAddExperience(String jobTitle);

	public void setLocationInAddExperience(String location);

	public void selectFromMonthInAddExperience(String month);

	public void selectToMonthInAddExperience(String month);

	public void setFromYearInAddExperience(String fromYear);

	public void setToYearInAddExperience(String toYear);

	public void checkICurrentlyWorkHere(boolean state);

	public void setDescriptionInAddExperience(String description);

	public void clickSaveInAddExperience();

	public void addExperienceDetails(Map<String, String> experienceDetails, boolean currentlyWorkHere);

	public void removeExperience(String jobTitle);

	public boolean verifyExperienceJobTitle(String jobTitle);

	public void setDateOfBirth(String date);

	public void setBRID(String id);
}
