package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.EditProfileLabels;
import com.highq.pageobjects.base.EditProfilePage;

public class EditProfileWeb extends BannerPageWeb implements EditProfilePage
{
	Select dropDown;
	By openedModal = By.xpath("(//*[contains(@class,'modal-content')])[last()]");
	/** Contact Information */
	By contactInfo_changeProfileLink = By.xpath("//*[@class='changeLink']");
	By contactInfo_firstNameInput = By.id("userFirstName");
	By contactInfo_lastNameInput = By.id("userLastName");
	By contactInfo_jobTitleInput = By.xpath("//*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_JOBTITLE + "']/following-sibling::*/*[@type='text']");
	By contactInfo_departmentDropDown = By.xpath("//*[@name='user.departmentID']");
	By contactInfo_department_addNewLink = By.xpath("//*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_DEPARTMENT + "']/following-sibling::*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_ADDNEW + "']");
	By contactInfo_companyInput = By.xpath("//*[@name='user.orgName']");
	By contactInfo_officeAddressDropDownButton = By.xpath(".//*[@id='orgAddressDivID']/button");
	By contactInfo_officeAddressDropDownBox = By.xpath(".//*[@id='orgAddressDivID']//*[@class='list-unstyled listData']");
	String contactInfo_officeAddressList = ".//*[@id='orgAddressDivID']//*[@class='list-unstyled listData']//li";
	By contactInfo_officeAddress_addNewLink = By.xpath("//*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_OFFICEADDRESS + "']/following-sibling::*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_ADDNEW + "']");
	By contactInfo_emailUserNameInput = By.id("userEmailID");
	By contactInfo_emailDomainDropDown = By.id("userDomainListID");
	By contactInfo_officePhone_countryCodeInput = By.id("phoneCountryCodeID");
	By contactInfo_officePhone_areaCodeInput = By.id("phoneAreacodeID");
	By contactInfo_officePhone_phoneNumberInput = By.id("phoneNumberID");
	By contactInfo_mobilePhone_countryCodeInput = By.id("mobileCountryCodeID");
	By contactInfo_mobilePhone_phoneNumberInput = By.id("mobileNumberID");
	By contactInfo_secretaryNameInput = By.xpath("//*[@name='user.secretaryName']");
	By contactInfo_secretaryEmailInput = By.id("secretaryEmail");

	/** About Me */
	By aboutMe_bioInput = By.xpath("//*[@name='user.bio']");
	By aboutMe_specialitiesInput = By.id("specialities-tokenfield");
	By aboutMe_experienceAddButton = By.xpath("//*[contains(@onclick,'addUserExperiencePage()')]");

	/** Social */
	By social_addButton = By.xpath("//*[contains(@onclick,'addLinks()')]");
	By social_platformDropDown = By.id("linkOptionID0");
	By social_enterURLInput = By.id("linkTextID0");
	By social_lastRecordRemoveIcon = By.xpath("(.//*[@id='userSocialLinkListRow']//*[contains(@onclick,'removeUserLink')])[last()]");
	By social_removeModal_removeButton = By.id("collaborateMessageOkButton");
	String social_allRemoveIcon = ".//*[@id='userSocialLinkListRow']//*[contains(@onclick,'removeUserLink')]";

	/** Messaging */
	By messaging_addButton = By.xpath("//*[contains(@onclick,'addMessaging()')]");
	By messaging_platformDropDown = By.id("messagingOptionID0");
	By messaging_enterMessageInput = By.id("messagingTextID0");
	By messaging_lastRecordRemoveIcon = By.xpath("(.//*[@id='userMessagingLinkListRow']//*[contains(@onclick,'removeMessaging')])[last()]");
	String messaging_allRemoveIcon = ".//*[@id='userMessagingLinkListRow']//*[contains(@onclick,'removeMessaging')]";

	/** Links */
	By links_addButton = By.xpath("//*[contains(@onclick,'addCustomLink()')]");
	By links_enterNameInput = By.id("customLinkNameID0");
	By links_enterURLInput = By.id("customUrlLinkID0");
	By links_lastRecordRemoveIcon = By.xpath("(.//*[@id='userCustomLinkListRow']//*[contains(@onclick,'removeCustomLink')])[last()]");
	String links_allRemoveIcon = ".//*[@id='userCustomLinkListRow']//*[contains(@onclick,'removeCustomLink')]";

	By saveButtonBottom = By.id("userProfileSaveButton");
	By cancelButtonBottom = By.xpath(".//*[@id='userProfileSaveButton']/preceding-sibling::*[normalize-space(text())='" + EditProfileLabels.EDITPROFILE_CONTACTINFO_CANCEL + "']");

	/** Add new department */
	By addNewDept_departmentNameInput = By.id("departmentName");
	By addNewDept_saveButton = By.id("USER_PROFILE_ADD_DEPARTMENT_MODAL_saveButton");
	By addNewDept_cancelButton = By.id("USER_PROFILE_ADD_DEPARTMENT_MODAL_cancelButton");

	By uploadProfile_browseInput = By.id("fileUploadID");
	By uploadProfile_saveButton = By.id("USER_PROFILE_UPLOAD_USER_AVATAR_MODAL_saveButton");
	By removeProfilePicture = By.xpath("(//*[contains(@class,'editPictureDelete')])[1]");
	By removePictureModal = By.id("collaborateCustomMessageModal");
	By removePictureButton = By.id("collaborateMessageOkButton");
	By removeMessage = By.id("collaborateCustomModalMessage");
	By errorMessageTop = By.id("errorElementContainer");
	By enterLastNameErrorMsg = By.id("userLastName_pID");
	By enterFirstNameErrorMsg = By.id("userFirstName_pID");
	By enterValidSecretaryEmailErrorMsg = By.id("secretaryEmail_pID");
	By enterEmailAddressError = By.id("userEmailID_pID");
	String specialityTokens = "//*[@class='token']";
	By wrapper = By.xpath("//*[@class='wrapper']");

	/* Add experience */
	By addExperience_companyNameInput = By.id("people_module_userExperience_companyName");
	By addExperience_jobTitleInput = By.id("people_module_userExperience_jobTitle");
	By addExperience_locationInput = By.id("people_module_userExperience_location");
	By addExperience_fromMonthDropDown = By.id("fromMonth");
	By addExperience_toMonthDropDown = By.id("toMonth");
	By addExperience_timePeriod_fromYearInput = By.id("people_module_userExperience_fromYear");
	By addExperience_timePeriod_toYearInput = By.id("people_module_userExperience_toYear");
	By addExperience_IcurrentlyWorkHereCheckbox = By.id("userExperienceCurrentlyWorkingID");
	By addExperience_descriptionInput = By.id("userExperienceDescriptionID");
	By addExperience_saveButton = By.id("USER_PROFILE_ADD_EXPERIENCE_MODAL_saveButton");
	By addExperience_cancelButton = By.id("USER_PROFILE_ADD_EXPERIENCE_MODAL_cancelButton");
	By dateOfBirth = By.id("dateOfBirth");
	By brid = By.id("bridID");

	public EditProfileWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Click on save button
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public ViewUserProfileWeb clickSave()
	{
		findVisibleElement(saveButtonBottom).click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * Click on cancel button
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public ViewUserProfileWeb clickCancel()
	{
		findVisibleElement(cancelButtonBottom).click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * Set first name
	 * 
	 * @param firstName
	 *        first name of the user
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setFirstName(String firstName)
	{
		WebElement firstNameElem = findVisibleElement(contactInfo_firstNameInput);
		firstNameElem.clear();
		firstNameElem.sendKeys(firstName.trim());
	}

	/**
	 * Set last name
	 * 
	 * @param lastName
	 *        last name of the user
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setLastName(String lastName)
	{
		WebElement lastNameElem = findVisibleElement(contactInfo_lastNameInput);
		lastNameElem.clear();
		lastNameElem.sendKeys(lastName.trim());
	}

	/**
	 * Clear first name
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void clearFirstName()
	{
		WebElement firstNameElem = findVisibleElement(contactInfo_firstNameInput);
		firstNameElem.clear();
	}

	/**
	 * Clear last name
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void clearLastName()
	{
		WebElement lastNameElem = findVisibleElement(contactInfo_lastNameInput);
		lastNameElem.clear();
	}

	/**
	 * Set job title
	 * 
	 * @param jobTitle
	 *        job title of the user
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setJobTitle(String jobTitle)
	{
		WebElement jobTitleElem = findVisibleElement(contactInfo_jobTitleInput);
		jobTitleElem.clear();
		jobTitleElem.sendKeys(jobTitle.trim());
	}

	/**
	 * Select department
	 * 
	 * @param departmentName
	 *        name of the department
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 */
	public void selectDepartment(String departmentName)
	{
		dropDown = new Select(findVisibleElement(contactInfo_departmentDropDown));
		dropDown.selectByVisibleText(departmentName.trim());
	}

	/**
	 * Click add new link of department
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void clickAddNewDepartment()
	{
		findVisibleElement(contactInfo_department_addNewLink).click();
		findVisibleElement(openedModal);
	}

	/**
	 * Set department name in add new department modal
	 * 
	 * @param departmentName
	 *        name of the department
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setDepartmentNameInAddNewDepartment(String departmentName)
	{
		WebElement departmentInputElem = findVisibleElement(addNewDept_departmentNameInput);
		departmentInputElem.clear();
		departmentInputElem.sendKeys(departmentName.trim());
	}

	/**
	 * Click save in add new department modal
	 * 
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void clickSaveInAddNewDepartment()
	{
		findVisibleElement(addNewDept_saveButton).click();
	}

	/**
	 * Add new department
	 * 
	 * @param departmentName
	 *        name of the department to add
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */

	public void addNewDepartment(String departmentName)
	{
		clickAddNewDepartment();
		setDepartmentNameInAddNewDepartment(departmentName);
		clickSaveInAddNewDepartment();
	}

	/**
	 * Set company name
	 * 
	 * @param companyName
	 *        name of the company
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setCompany(String companyName)
	{
		WebElement companyNameElem = findVisibleElement(contactInfo_companyInput);
		companyNameElem.clear();
		companyNameElem.sendKeys(companyName.trim());
	}

	/**
	 * Select office address
	 * 
	 * @param officeAddress
	 *        address to select
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void selectOfficeAddress(String officeAddress)
	{
		selectOptionFromDropDown(contactInfo_officeAddressDropDownButton, contactInfo_officeAddressDropDownBox, contactInfo_officeAddressList, officeAddress.trim());
	}

	/**
	 * Set email user id
	 * 
	 * @param emailUserID
	 *        email user id
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 */
	public void setEmailUserID(String emailUserID)
	{
		WebElement emailUser = findVisibleElement(contactInfo_emailUserNameInput);
		emailUser.clear();
		emailUser.sendKeys(emailUserID.trim());
	}

	/**
	 * Select email domain
	 * 
	 * @param domainName
	 *        email domain
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 */
	public void selectEmailDomain(String domainName)
	{
		dropDown = new Select(findVisibleElement(contactInfo_emailDomainDropDown));
		dropDown.selectByVisibleText(domainName.trim());
	}

	/**
	 * Set email address
	 * 
	 * @param email
	 *        email address of the user
	 * @author dheeraj.rajput
	 *         Created: 22 January 2048
	 *         Updated:
	 */
	public void setEmailAddress(String email)
	{
		String[] emailAddress = email.split("@");
		setEmailUserID(emailAddress[0].trim());
		selectEmailDomain(emailAddress[1].trim());
	}

	/**
	 * Set office phone country code
	 * 
	 * @param countryCode
	 *        country code associated with phone number
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setOfficePhoneCountryCode(String countryCode)
	{
		WebElement countryCodeElem = findVisibleElement(contactInfo_officePhone_countryCodeInput);
		countryCodeElem.clear();
		countryCodeElem.sendKeys(countryCode.trim());
	}

	/**
	 * Set office phone area code
	 * 
	 * @param areaCode
	 *        area code associated with phone number
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setOfficePhoneAreaCode(String areaCode)
	{
		WebElement areaCodeElem = findVisibleElement(contactInfo_officePhone_areaCodeInput);
		areaCodeElem.clear();
		areaCodeElem.sendKeys(areaCode.trim());
	}

	/**
	 * Set office phone number
	 * 
	 * @param phoneNumber
	 *        phone number of the user
	 * @author dheeraj.rajput
	 *         Created: 22 January 2018
	 *         Updated:
	 */
	public void setOfficePhoneNumber(String phoneNumber)
	{
		WebElement phoneNumberElem = findVisibleElement(contactInfo_officePhone_phoneNumberInput);
		phoneNumberElem.clear();
		phoneNumberElem.sendKeys(phoneNumber.trim());
	}

	/**
	 * Set office phone number
	 * 
	 * @param countryCode
	 *        country code
	 * @param areaCode
	 *        area code
	 * @param phoneNo
	 *        phone number
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setOfficePhone(String countryCode, String areaCode, String phoneNo)
	{
		setOfficePhoneCountryCode(countryCode);
		setOfficePhoneAreaCode(areaCode);
		setOfficePhoneNumber(phoneNo);
	}

	/**
	 * Set mobile country code
	 * 
	 * @param countryCode
	 *        country code
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setMobileCountryCode(String countryCode)
	{
		WebElement mobileCountry = findVisibleElement(contactInfo_mobilePhone_countryCodeInput);
		mobileCountry.clear();
		mobileCountry.sendKeys(countryCode.trim());
	}

	/**
	 * Set mobile phone number
	 * 
	 * @param phoneNo
	 *        phone number
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setMobilePhoneNumber(String phoneNo)
	{
		WebElement mobilePhone = findVisibleElement(contactInfo_mobilePhone_phoneNumberInput);
		mobilePhone.clear();
		mobilePhone.sendKeys(phoneNo.trim());
	}

	/**
	 * Set mobile phone number of user
	 * 
	 * @param countryCode
	 *        country code of the user
	 * @param mobileNo
	 *        mobile number of the user]
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setMobilePhone(String countryCode, String mobileNo)
	{
		setMobileCountryCode(countryCode);
		setMobilePhoneNumber(mobileNo);
	}

	/**
	 * Set secretary name
	 * 
	 * @param name
	 *        name of the secretary
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setSecretaryName(String name)
	{
		WebElement secrataryInputElem = findVisibleElement(contactInfo_secretaryNameInput);
		secrataryInputElem.clear();
		secrataryInputElem.sendKeys(name.trim());
	}

	/**
	 * Set secretary email
	 * 
	 * @param email
	 *        email of the secretary
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setSecretaryEmail(String email)
	{
		WebElement secrataryEmailElem = findVisibleElement(contactInfo_secretaryEmailInput);
		secrataryEmailElem.clear();
		secrataryEmailElem.sendKeys(email.trim());
	}

	/**
	 * Click on Change link available on user avatar
	 * 
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void clickChangeProfilePictureLink()
	{
		findVisibleElement(contactInfo_changeProfileLink).click();
		findVisibleElement(openedModal);
	}

	/**
	 * Click on save button in profile modal
	 * 
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void saveProfileChange()
	{
		findVisibleElement(uploadProfile_saveButton).click();
		findVisibleElement(removeProfilePicture);
	}

	/**
	 * Change profile picture
	 * 
	 * @param imageName
	 *        name of the file to upload
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void changeProfilePicture(String imageName)
	{
		clickChangeProfilePictureLink();
		uploadFile(uploadProfile_browseInput, imageName.trim());
		saveProfileChange();
	}

	/**
	 * Verify remove icon is visible on top of profile picture
	 * 
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyRemoveIconOnProfilePic()
	{
		return isDisplayed(removeProfilePicture, Speed.slow);
	}

	/**
	 * Remove profile picture
	 * 
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void removeProfilePicture()
	{
		WebElement removeIcon = findVisibleElement(removeProfilePicture, Speed.slow);
		removeIcon.click();
		findVisibleElement(removePictureModal);
		findVisibleElement(removeMessage);
		findVisibleElement(removePictureButton).click();
		findVisibleElement(contactInfo_changeProfileLink);
	}

	/**
	 * Verify Enter first name error is shown
	 * 
	 * @return true
	 *         if error message is available
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyEnterFirstNameError()
	{
		return isDisplayed(enterFirstNameErrorMsg, Speed.slow) && isDisplayed(errorMessageTop);
	}

	/**
	 * Verify Enter last name error is shown
	 * 
	 * @return true
	 *         if error message is available
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyEnterLastNameError()
	{
		return isDisplayed(enterLastNameErrorMsg, Speed.slow) && isDisplayed(errorMessageTop);
	}

	/**
	 * Verify department is present
	 * 
	 * @param departmentName
	 *        name of department
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyDepartmentIsPresent(String departmentName)
	{
		boolean found = false;
		dropDown = new Select(findVisibleElement(contactInfo_departmentDropDown));
		List<WebElement> dropDownOptions = dropDown.getOptions();
		for (WebElement elem : dropDownOptions)
		{
			if (elem.getText().equals(departmentName.trim()))
				found = true;
		}
		return found;
	}

	/**
	 * Verify Enter valid secretory email error is shown
	 * 
	 * @return true
	 *         if error message is available
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyEnterValidSecretoryEmailError()
	{
		return isDisplayed(enterValidSecretaryEmailErrorMsg, Speed.slow) && isDisplayed(errorMessageTop);
	}

	/**
	 * clear email user id
	 * 
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 */
	public void clearEmailUserID()
	{
		WebElement emailUser = findVisibleElement(contactInfo_emailUserNameInput);
		emailUser.clear();
	}

	/**
	 * Verify Enter email address error is shown
	 * 
	 * @return true
	 *         if error message is available
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyEnterEmailAddressError()
	{
		return isDisplayed(enterEmailAddressError, Speed.slow) && isDisplayed(errorMessageTop);
	}

	/**
	 * Set About me -> bio
	 * 
	 * @param bio
	 *        bio to be set
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setBio(String bio)
	{
		WebElement bioInputElem = findVisibleElement(aboutMe_bioInput);
		bioInputElem.clear();
		bioInputElem.sendKeys(bio.trim());
	}

	/**
	 * Set Specialties
	 * 
	 * @param specialities
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 */
	public void setSpecialities(String... specialities)
	{
		WebElement elem = findVisibleElement(aboutMe_specialitiesInput);
		for (int i = 0; i < specialities.length; i++)
		{
			elem.sendKeys(specialities[i].trim());
			elem.sendKeys(Keys.TAB);
		}
	}

	/**
	 * Select social platform
	 * 
	 * @param platform
	 *        platform name
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void selectSocialPlatform(String platform)
	{
		dropDown = new Select(findVisibleElement(social_platformDropDown));
		dropDown.selectByVisibleText(platform.trim());
	}

	/**
	 * Set social URL
	 * 
	 * @param url
	 *        social URL
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setSocialURL(String url)
	{
		WebElement elem = findVisibleElement(social_enterURLInput);
		elem.clear();
		elem.sendKeys(url.trim());
	}

	/**
	 * Select message platform
	 * 
	 * @param platform
	 *        platform name
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void selectMessagePlatform(String platform)
	{
		dropDown = new Select(findVisibleElement(messaging_platformDropDown));
		dropDown.selectByVisibleText(platform.trim());
	}

	/**
	 * Set message text
	 * 
	 * @param message
	 *        message text
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setMessageText(String message)
	{
		WebElement elem = findVisibleElement(messaging_enterMessageInput);
		elem.clear();
		elem.sendKeys(message.trim());
	}

	/**
	 * Set link name
	 * 
	 * @param linkName
	 *        link name
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setLinkName(String linkName)
	{
		WebElement elem = findVisibleElement(links_enterNameInput);
		elem.clear();
		elem.sendKeys(linkName.trim());
	}

	/**
	 * Set link URL
	 * 
	 * @param linkURL
	 *        link URL
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public void setLinkURL(String linkURL)
	{
		WebElement elem = findVisibleElement(links_enterURLInput);
		elem.clear();
		elem.sendKeys(linkURL.trim());
	}

	/**
	 * Verify Company input is enable
	 * 
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyCompanyInputIsEnabled()
	{
		return findVisibleElement(contactInfo_companyInput).isEnabled();
	}

	/**
	 * Verify Email ID input is enable
	 * 
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyEmailIDIsEnabled()
	{
		return findVisibleElement(contactInfo_emailUserNameInput).isEnabled();
	}

	/**
	 * Clear all specialties
	 * 
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public void clearAllSpecialities()
	{
		int size = driver.findElements(By.xpath(specialityTokens)).size();
		if (size > 0)
		{
			for (int i = size; i > 0; i--)
			{
				findVisibleElement(By.xpath(specialityTokens + "[" + i + "]//*[@class='close']")).click();
			}
		}
	}

	/**
	 * Click on Add button in Social
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void clickAddSocial()
	{
		WebElement socialAddButton = findVisibleElement(social_addButton);
		socialAddButton.click();
	}

	/**
	 * Click on Add button in Messaging
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void clickAddMessaging()
	{
		WebElement messagingAddButton = findVisibleElement(messaging_addButton);
		messagingAddButton.click();
	}

	/**
	 * Click on Add button in Links
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void clickAddLinks()
	{
		WebElement linksAddButton = findVisibleElement(links_addButton);
		linksAddButton.click();
	}

	/**
	 * Fetch total number of remove icons present in Social
	 * 
	 * @return count of remove icons
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public int fetchRemoveIconCountOfSocialPlatforms()
	{
		By removeIcons = By.xpath(social_allRemoveIcon);
		findVisibleElement(social_addButton, Speed.slow);
		return driver.findElements(removeIcons).size();
	}

	/**
	 * Verify remove icon count of social matches expected count
	 * 
	 * @param expectedCount
	 *        count to be verified
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyRemoveIconCountOfSocialPlatforms(int expectedCount)
	{
		findVisibleElement(social_addButton, Speed.slow);
		int actualCount = fetchRemoveIconCountOfSocialPlatforms();
		return actualCount == expectedCount;
	}

	/**
	 * Remove Last social link
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void removeLastSocialLink()
	{
		WebElement removeIcon = findVisibleElement(social_lastRecordRemoveIcon);
		removeIcon.click();
		findPresentElement(wrapper, Speed.slow);
		WebElement removeElement = findVisibleElement(social_removeModal_removeButton);
		removeElement.click();
		findPresentElement(wrapper, Speed.slow);
	}

	/**
	 * Remove Last messaging link
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void removeLastMessagingLink()
	{
		WebElement removeIcon = findVisibleElement(messaging_lastRecordRemoveIcon);
		removeIcon.click();
		findPresentElement(wrapper, Speed.slow);
		WebElement removeElement = findVisibleElement(social_removeModal_removeButton);
		removeElement.click();
		findPresentElement(wrapper, Speed.slow);
	}

	/**
	 * Fetch total number of remove icons present in Messaging
	 * 
	 * @return count of remove icons
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public int fetchRemoveIconCountOfMessagingPlatforms()
	{
		By removeIcons = By.xpath(messaging_allRemoveIcon);
		findVisibleElement(messaging_addButton, Speed.slow);
		return driver.findElements(removeIcons).size();
	}

	/**
	 * Verify remove icon count of messaging matches expected count
	 * 
	 * @param expectedCount
	 *        count to be verified
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyRemoveIconCountOfMessagingPlatforms(int expectedCount)
	{
		findVisibleElement(messaging_addButton, Speed.slow);
		int actualCount = fetchRemoveIconCountOfMessagingPlatforms();
		return actualCount == expectedCount;
	}

	/**
	 * Remove Last link
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void removeLastLinks()
	{
		WebElement removeIcon = findVisibleElement(links_lastRecordRemoveIcon);
		removeIcon.click();
		findPresentElement(wrapper, Speed.slow);
		WebElement removeElement = findVisibleElement(social_removeModal_removeButton);
		removeElement.click();
		findPresentElement(wrapper, Speed.slow);
	}

	/**
	 * Fetch total number of remove icons present in Links
	 * 
	 * @return count of remove icons
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public int fetchRemoveIconCountOfLinksPlatforms()
	{
		By removeIcons = By.xpath(links_allRemoveIcon);
		findVisibleElement(links_addButton, Speed.slow);
		return driver.findElements(removeIcons).size();
	}

	/**
	 * Verify remove icon count of messaging matches expected count
	 * 
	 * @param expectedCount
	 *        count to be verified
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyRemoveIconCountOfLinksPlatforms(int expectedCount)
	{
		findVisibleElement(links_addButton, Speed.slow);
		int actualCount = fetchRemoveIconCountOfLinksPlatforms();
		return actualCount == expectedCount;
	}

	/**
	 * Click on Add button in About me -> Experience
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void clickAddExperienceButton()
	{
		WebElement addButton = findVisibleElement(aboutMe_experienceAddButton);
		addButton.click();
		findVisibleElement(wrapper, Speed.slow);
	}

	/**
	 * Set company name in add experience
	 * 
	 * @param companyName
	 *        name of the company
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setCompanyNameInAddExperience(String companyName)
	{
		WebElement elem = findVisibleElement(addExperience_companyNameInput);
		if (companyName.isEmpty() || companyName == null)
			System.out.println("Company Name can not be left empty.");
		else
		{
			elem.clear();
			elem.sendKeys(companyName.trim());
		}
	}

	/**
	 * Set job title in add experience
	 * 
	 * @param jobTitle
	 *        title of job
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setJobTitleInAddExperience(String jobTitle)
	{
		WebElement elem = findVisibleElement(addExperience_jobTitleInput);
		if (jobTitle.isEmpty() || jobTitle == null)
			System.out.println("Job title can not be left empty.");
		else
		{
			elem.clear();
			elem.sendKeys(jobTitle.trim());
		}
	}

	/**
	 * Set location in add experience
	 * 
	 * @param location
	 *        location
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setLocationInAddExperience(String location)
	{
		WebElement elem = findVisibleElement(addExperience_locationInput);
		elem.clear();
		elem.sendKeys(location.trim());
	}

	/**
	 * Select from month in add experience
	 * 
	 * @param month
	 *        from month
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void selectFromMonthInAddExperience(String month)
	{
		WebElement elem = findVisibleElement(addExperience_fromMonthDropDown);
		if (month.isEmpty() || month == null)
			System.out.println("From Month can not be left empty.");
		else
		{
			Select dropDown = new Select(elem);
			dropDown.selectByVisibleText(month.trim());
		}
	}

	/**
	 * Select To month in add experience
	 * 
	 * @param month
	 *        from month
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void selectToMonthInAddExperience(String month)
	{
		if (!findVisibleElement(addExperience_IcurrentlyWorkHereCheckbox).isSelected())
		{
			WebElement elem = findVisibleElement(addExperience_toMonthDropDown);
			if (month.isEmpty() || month == null)
				System.out.println("To Month can not be left empty.");
			else
			{
				Select dropDown = new Select(elem);
				dropDown.selectByVisibleText(month.trim());
			}
		}
		else
			System.out.println("Uncheck I currently work here checkbox first");
	}

	/**
	 * Set from year in add experience
	 * 
	 * @param fromYear
	 *        from year
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setFromYearInAddExperience(String fromYear)
	{
		WebElement elem = findVisibleElement(addExperience_timePeriod_fromYearInput);
		if (fromYear.isEmpty() || fromYear == null)
			System.out.println("From year can not be left empty.");
		else
		{
			elem.clear();
			elem.sendKeys(fromYear.trim());
		}
	}

	/**
	 * Set to year in add experience
	 * 
	 * @param toYear
	 *        to year
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setToYearInAddExperience(String toYear)
	{
		if (!findVisibleElement(addExperience_IcurrentlyWorkHereCheckbox).isSelected())
		{
			WebElement elem = findVisibleElement(addExperience_timePeriod_toYearInput);
			if (toYear.isEmpty() || toYear == null)
				System.out.println("To year can not be left empty.");
			else
			{
				elem.clear();
				elem.sendKeys(toYear.trim());
			}
		}
		else
			System.out.println("Uncheck I currently work here checkbox first");
	}

	/**
	 * Check/Uncheck I currently work here checkbox
	 * 
	 * @param state
	 *        Created: 08 March 2018
	 *        Updated:
	 */
	public void checkICurrentlyWorkHere(boolean state)
	{
		setSelection(addExperience_IcurrentlyWorkHereCheckbox, state);
	}

	/**
	 * Set description in add experience
	 * 
	 * @param description
	 *        description to set
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void setDescriptionInAddExperience(String description)
	{
		WebElement elem = findVisibleElement(addExperience_descriptionInput);
		elem.clear();
		elem.sendKeys(description.trim());
	}

	/**
	 * Click save in add experience
	 * 
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void clickSaveInAddExperience()
	{
		findVisibleElement(addExperience_saveButton).click();
		findPresentElement(wrapper, Speed.slow);
	}

	/**
	 * Add experience details
	 * 
	 * @param experienceDetails
	 *        Map<String,String> Key: experience details fields, Value: value to be sent
	 * @param currentlyWorkHere
	 *        true/false : to check/uncheck I currently work here checkbox
	 *        Created: 08 March 2018
	 *        Updated:
	 */
	public void addExperienceDetails(Map<String, String> experienceDetails, boolean currentlyWorkHere)
	{
		checkICurrentlyWorkHere(currentlyWorkHere);
		for (Map.Entry<String, String> entryMap : experienceDetails.entrySet())
		{
			String fieldName = entryMap.getKey();
			String fieldValue = entryMap.getValue();
			switch (fieldName.toLowerCase())
			{
				case "company":
					setCompanyNameInAddExperience(fieldValue);
					break;

				case "job title":
					setJobTitleInAddExperience(fieldValue);
					break;

				case "location":
					setLocationInAddExperience(fieldValue);
					break;

				case "from":
					selectFromMonthInAddExperience(fieldValue);
					break;

				case "fromyear":
					setFromYearInAddExperience(fieldValue);
					break;

				case "to":
					selectToMonthInAddExperience(fieldValue);
					break;

				case "toyear":
					setToYearInAddExperience(fieldValue);
					break;

				case "description":
					setDescriptionInAddExperience(fieldValue);
					break;

				default:
					System.out.println(fieldName + " not found.");
			}
		}
	}

	/**
	 * Remove experience
	 * 
	 * @param jobTitle
	 *        title of the job
	 * @author dheeraj.rajput
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public void removeExperience(String jobTitle)
	{
		By removeXpath = By.xpath(".//*[@id='userExperienceListDivID']//*[normalize-space(text())='" + jobTitle.trim() + "']/preceding-sibling::*[1]//*[contains(@onclick,'deleteUserExperience')]");
		if (isDisplayed(removeXpath, Speed.slow))
		{
			findVisibleElement(removeXpath).click();
			findPresentElement(wrapper, Speed.slow);
			WebElement removeElement = findVisibleElement(social_removeModal_removeButton);
			removeElement.click();
			findPresentElement(wrapper, Speed.slow);
		}
	}

	/**
	 * Verify job title in experience
	 * 
	 * @param jobTitle
	 *        title of the job
	 * @author dheeraj.rajput
	 * @return true
	 *         if successful
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyExperienceJobTitle(String jobTitle)
	{
		By title = By.xpath("(.//*[@id='userExperienceListDivID']//*[normalize-space(text())='" + jobTitle.trim() + "'])[last()]");
		return isDisplayed(title, Speed.slow);
	}

	/**
	 * Set Date of Birth
	 * 
	 * @param date
	 * @author ankit.motaval
	 *         Created: 20 January 2018
	 *         Updated:
	 */
	public void setDateOfBirth(String date)
	{
		WebElement element = findVisibleElement(dateOfBirth, Speed.slow);
		element.click();
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(date.trim());
	}

	/**
	 * Set BRID
	 * 
	 * @param ID
	 * @author ankit.motaval
	 *         Created: 20 January 2018
	 *         Updated:
	 */
	public void setBRID(String id)
	{
		WebElement brIdElem = findVisibleElement(brid);
		brIdElem.clear();
		brIdElem.sendKeys(id.trim());
	}
}
