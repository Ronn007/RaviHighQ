package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.UserProfileLabels;
import com.highq.pageobjects.base.ViewUserProfilePage;

public class ViewUserProfileWeb extends MyProfileWeb implements ViewUserProfilePage
{
	By summary_FullUserName = By.id("userProfilefullName");
	By summary_EditProfileButton = By.xpath("(//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_SUMMARY_EDITPROFILE + "'])[1]");
	By summary_MoreActionsIcon = By.xpath("(//*[@data-original-title = '" + UserProfileLabels.USERPROFILE_SUMMARY_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "'])[1]");
	By summary_MoreActionsDropDownMenu = By.xpath("(//*[@data-original-title = '" + UserProfileLabels.USERPROFILE_SUMMARY_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "'])[1]/following-sibling::*[contains(@class,'dropdown-menu') and contains(@style,'height')]");

	By jobTitleAndDepartment = By.xpath("//*[@class='profileUserData']/*[1]/*[last()]");
	By companyName = By.xpath("//*[@class='profileUserData']/*[2]");
	By email = By.xpath(".//*[@class='profileUserData']/child::*[@class='margBott5 margTop10']");
	By emailLink = By.xpath(".//*[@class='profileUserData']/child::*[@class='margBott5 margTop10']/a");
	By office = By.xpath("//*[contains(text(),'" + UserProfileLabels.USERPROFILE_OFFICE + "')]//a");
	By mobile = By.xpath("//*[contains(text(),'" + UserProfileLabels.USERPROFILE_MOBILE + "')]//a");
	By secretaryName = By.xpath("//*[@class='profileUserData']/*[6]");
	By secretaryEmail = By.xpath("(//*[contains(@href,'" + UserProfileLabels.USERPROFILE_MAILTO + "')])[2]");
	By bio = By.xpath(".//*[@id='userBioData']/*[last()]/*[last()]");
	By allSpecialities = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_SPECIALITIES + "']/following-sibling::*[1]/*[@class='tagContainer']//a");
	By allLinks = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_LINKS + "']/following-sibling::*[1]/*[@class='tagContainer']//a");
	By allSocialNetworkLinks = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_SOCIALNETWORKANDMESSAGING + "']/following-sibling::*[1]/*[@class='tagContainer']//*[@href='#']");
	By allMessageLinks = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_SOCIALNETWORKANDMESSAGING + "']/following-sibling::*[1]/*[@class='tagContainer']//a[not(@href='#')]");
	By experience_jobTitle = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_EXPERIENCE + "']/following-sibling::*[1]/h5[1]");
	By experience_companyName = By.xpath("//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_EXPERIENCE + "']/following-sibling::*[1]//*[@class='margBott2']/*[1]");
	By experience_timePeriod = By.xpath("(//*[normalize-space(text())='" + UserProfileLabels.USERPROFILE_EXPERIENCE + "']/following-sibling::*[1]//*[contains(@class,'greyFont')])[1]");
	By officeAddressAndcompany = By.id("officeAdd");
	By userAvtar = By.xpath(".//*[@id='profileMiddlePannel']/div[1]/img");
	By officeAddress = By.xpath("//*[contains(text(),'" + UserProfileLabels.USERPROFILE_OFFICE_ADDRESS + "')]");
	By sendMessage = By.id("sendMessageBtn");
	By follow = By.id("followBtn");
	By userProfile = By.xpath(".//*[@class='pull-left radius3 profileAvtar']");
	By userProfileMiddlePannel = By.id("profileMiddlePannel");

	public ViewUserProfileWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * Verify Full user name in summary
	 * 
	 * @param userName
	 *        full name of the user
	 * @author dheeraj.rajput
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public boolean verifyFullUserName(String userName)
	{
		WebElement elem = findVisibleElement(summary_FullUserName);
		System.out.println("TEXT = " + elem.getText().trim());
		return elem.getText().trim().equals(userName.trim());
	}

	/**
	 * Click Edit profile button
	 * 
	 * @author dheeraj.rajput
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public EditProfileWeb clickEditProfileButton()
	{
		findVisibleElement(summary_EditProfileButton).click();
		return new EditProfileWeb(driver);
	}

	/**
	 * Open more actions menu
	 * 
	 * @author dheeraj.rajput
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public void openMoreActionsMenu()
	{
		if (!isDisplayed(summary_MoreActionsDropDownMenu))
		{
			findVisibleElement(summary_MoreActionsIcon).click();
			findVisibleElement(summary_MoreActionsDropDownMenu);
		}
	}

	/**
	 * Verify more actions menu item is present or not
	 * 
	 * @param itemName
	 *        name of the item to verify
	 * @return true
	 *         if successful
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public boolean verifyMoreActionsMenuItem(String itemName)
	{
		By moreActionsItem = By.xpath("(//*[@data-original-title = '" + UserProfileLabels.USERPROFILE_SUMMARY_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "'])[1]/following-sibling::*[contains(@class,'dropdown-menu') and contains(@style,'height')]//*[normalize-space(text())='" + itemName.trim() + "']");
		return isDisplayed(moreActionsItem, Speed.slow);
	}

	/**
	 * Select item from more action menu
	 * 
	 * @param itemName
	 *        name of the item to select
	 * @author dheeraj.rajput
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public void selectMoreActionsMenuItem(String itemName)
	{
		openMoreActionsMenu();
		By moreActionsItem = By.xpath("(//*[@data-original-title = '" + UserProfileLabels.USERPROFILE_SUMMARY_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "'])[1]/following-sibling::*[contains(@class,'dropdown-menu') and contains(@style,'height')]//*[normalize-space(text())='" + itemName.trim() + "']");
		findVisibleElement(moreActionsItem).click();
	}

	/**
	 * Verify Edit profile button is visible or not
	 * 
	 * @return true
	 *         if successful
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public boolean verifyEditProfileButtonVisibility()
	{
		return isDisplayed(summary_EditProfileButton, Speed.slow);
	}

	/**
	 * Verify job title
	 * 
	 * @param jobTitle
	 *        jop title
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyJobTitle(String jobTitle)
	{
		WebElement elem = findVisibleElement(jobTitleAndDepartment);
		String[] sourceJobTitleText = elem.getText().trim().split(",");
		return sourceJobTitleText[0].trim().equals(jobTitle.trim());
	}

	/**
	 * Verify department name
	 * 
	 * @param departmentName
	 *        name of the department
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 23 January 2018
	 *         Updated:
	 */
	public boolean verifyDepartment(String departmentName)
	{
		WebElement elem = findVisibleElement(jobTitleAndDepartment);
		String[] sourceDeptText = elem.getText().trim().split(",");
		return sourceDeptText[1].trim().equals(departmentName.trim());
	}

	/**
	 * Verify company name
	 * 
	 * @param company
	 *        name of the company
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyCompanyName(String company)
	{
		WebElement elem = findVisibleElement(companyName);
		return elem.getText().trim().equals(company.trim());
	}

	/**
	 * Verify email
	 * 
	 * @param emailAddress
	 *        email of the user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyEmail(String emailAddress)
	{
		WebElement elem = findVisibleElement(email);
		return elem.getText().trim().equals(emailAddress.trim());
	}

	/**
	 * Verify office number
	 * 
	 * @param countryCode
	 *        country code
	 * @param areaCode
	 *        area code
	 * @param phone
	 *        phone number
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyOfficePhone(String countryCode, String areaCode, String phone)
	{
		WebElement elem = findVisibleElement(office);
		String officePhoneNumber = "+" + countryCode + " " + areaCode + " " + phone;
		return elem.getText().trim().equals(officePhoneNumber.trim());
	}

	/**
	 * Verify mobile number
	 * 
	 * @param countryCode
	 *        country code
	 * @param phone
	 *        phone number
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyMobilePhone(String countryCode, String phone)
	{
		WebElement elem = findVisibleElement(mobile);
		String mobileNumber = "+" + countryCode + " " + phone;
		return elem.getText().trim().equals(mobileNumber.trim());
	}

	/**
	 * Verify secretary name
	 * 
	 * @param name
	 *        name of the secretary
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifySecretaryName(String name)
	{
		WebElement elem = findVisibleElement(secretaryName);
		String fetchedName = elem.getText().trim();
		fetchedName = fetchedName.substring(fetchedName.indexOf(":") + 1).trim();
		return fetchedName.trim().equals(name.trim());
	}

	/**
	 * Verify secretary email
	 * 
	 * @param email
	 *        email of the secretary
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifySecretaryEmail(String email)
	{
		WebElement elem = findVisibleElement(secretaryEmail);
		return elem.getText().trim().equals(email.trim());
	}

	/**
	 * Verify bio of the user
	 * 
	 * @param bio
	 *        bio of user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyBio(String bioText)
	{
		WebElement elem = findVisibleElement(bio);
		return elem.getText().trim().equals(bioText.trim());
	}

	/**
	 * Verify specialties
	 * 
	 * @param speciality
	 *        variable argument creating an array of speciality
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifySpecialities(String... speciality)
	{
		List<WebElement> spec = driver.findElements(allSpecialities);
		List<String> specText = new ArrayList<String>();
		for (WebElement elem : spec)
		{
			specText.add(elem.getText().trim());
		}
		return Arrays.asList(speciality).containsAll(specText);
	}

	/**
	 * Verify link and link url
	 * 
	 * @param linkMap
	 *        Map--> Key : Link name(String)
	 *        Value: Link URL(String)
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyLinkAndLinkURL(LinkedHashMap<String, String> linkMap)
	{
		List<WebElement> links = driver.findElements(allLinks);
		LinkedHashMap<String, String> generatedMap = new LinkedHashMap<String, String>();
		for (WebElement elem : links)
		{
			String key = elem.getText().trim();
			String value = elem.getAttribute("title").trim();
			generatedMap.put(key, value);
		}
		return generatedMap.equals(linkMap);
	}

	/**
	 * Verify link and link url
	 * 
	 * @param socialMap
	 *        Map--> Key : Social platform name(String)
	 *        Value: Social URL(String)
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifySocialPlatformAndLink(LinkedHashMap<String, String> socialMap)
	{
		List<WebElement> links = driver.findElements(allSocialNetworkLinks);
		LinkedHashMap<String, String> generatedMap = new LinkedHashMap<String, String>();
		for (WebElement elem : links)
		{
			String key = elem.getText().trim();
			String value = elem.getAttribute("title").trim();
			generatedMap.put(key, value);
		}
		return generatedMap.equals(socialMap);
	}

	/**
	 * Verify messaging
	 * 
	 * @param messaging
	 *        variable argument creating an array of messages
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 24 January 2018
	 *         Updated:
	 */
	public boolean verifyMessaging(String... messaging)
	{
		List<WebElement> msg = driver.findElements(allMessageLinks);
		List<String> msgText = new ArrayList<String>();
		for (WebElement elem : msg)
		{
			msgText.add(elem.getText().trim());
		}
		return Arrays.asList(messaging).containsAll(msgText);
	}

	/**
	 * Verify experience job title
	 * 
	 * @param jobTitle
	 *        title of the job
	 * @return true
	 *         if successful
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyExperienceJobTitle(String jobTitle)
	{
		WebElement elem = findVisibleElement(experience_jobTitle);
		return elem.getText().trim().equals(jobTitle.trim());
	}

	/**
	 * Verify experience Company name
	 * 
	 * @param companyName
	 *        name of the company
	 * @return true
	 *         if successful
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyExperienceCompany(String companyName)
	{
		WebElement elem = findVisibleElement(experience_companyName);
		String company = elem.getText().trim();
		return company.contains(companyName.trim());
	}

	/**
	 * Verify experience time period
	 * 
	 * @param fromMonth
	 *        from month
	 * @param fromYear
	 *        from year
	 * @param toMonth
	 *        to month
	 * @param toYear
	 *        to Year
	 * @return true
	 *         if successful
	 *         Created: 08 March 2018
	 *         Updated:
	 */
	public boolean verifyExperienceTimePeriod(String fromMonth, String fromYear, String toMonth, String toYear)
	{
		WebElement elem = findVisibleElement(experience_timePeriod);
		String timePeriod = elem.getText().trim();
		timePeriod = timePeriod.substring(0, timePeriod.indexOf("(")).trim();
		String expectedTimePeriod = fromMonth + " " + fromYear + " to  " + toMonth + " " + toYear;
		return timePeriod.equals(expectedTimePeriod.trim());
	}

	/**
	 * @author ankit.motaval
	 *         verify Office Address label
	 * @created on 12/04/2018
	 */
	public boolean verifyOfficeAddressAndCompanyAvailable()
	{
		return isDisplayed(officeAddressAndcompany, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Secretary Name Available
	 * @created on 12/04/2018
	 */
	public boolean verifySecretaryNameAvailable()
	{
		return isDisplayed(secretaryName, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Secretary Email Available
	 * @created on 12/04/2018
	 */
	public boolean verifySecretaryEmailAvailable()
	{
		return isDisplayed(secretaryEmail, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Job Title Available
	 * @created on 12/04/2018
	 */
	public boolean verifyJobTitleAndDepartmentAvailable(String jobTitleDepartment)
	{
		return isDisplayed(By.xpath(".//*[@id='function']//strong[normalize-space(text())='" + jobTitleDepartment + "']"), Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Bio Available
	 * @created on 12/04/2018
	 */
	public boolean verifyBioAvailable()
	{
		return isDisplayed(bio, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Specialities Available
	 * @created on 12/04/2018
	 */
	public boolean verifySpecialitiesAvailable()
	{
		return isDisplayed(allSpecialities, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Social Platform And Link Available
	 * @created on 12/04/2018
	 */
	public boolean verifySocialPlatformAndLinkAvailable()
	{
		return isDisplayed(allSocialNetworkLinks, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Link Available
	 * @created on 12/04/2018
	 */
	public boolean verifyLinkAndLinkURLAvailable()
	{
		return isDisplayed(allLinks, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify User Avtar
	 * @created on 12/04/2018
	 */
	public boolean verifyUserAvtarAvailabe()
	{
		String contentText = findVisibleElement(userAvtar, Speed.slow).getAttribute("src");
		if (contentText.contains("downloadUserAvatar.action"))
		{
			return true;
		}
		return false;
	}

	/**
	 * @author ankit.motaval
	 *         verify Mobile Number Available
	 * @created on 20/04/2018
	 */
	public boolean verifyMobileAvailable()
	{
		return isDisplayed(mobile, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Office Number Available
	 * @created on 20/04/2018
	 */
	public boolean verifyOfficeNumberAvailable()
	{
		return isDisplayed(office, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Office Address Label Available
	 * @created on 20/04/2018
	 */
	public boolean verifyOfficeAddressLabel()
	{
		return isDisplayed(officeAddress, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Email link is Available
	 * @created on 20/04/2018
	 */
	public boolean verifyEmailLink()
	{
		return isDisplayed(emailLink, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Send a message button available
	 * @created on 01/05/2018
	 */
	public boolean isAvailableSendMessageButton()
	{
		return isDisplayed(sendMessage, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Follow button available
	 * @created on 01/05/2018
	 */
	public boolean isAvailableFollowButton()
	{
		return isDisplayed(follow, Speed.slow);
	}

	/**
	 * @author jhanvi.dave
	 * @Created: 12 May 2018
	 * @Updated: 16 May 2018
	 * @param
	 * @return
	 */
	public boolean verifyProfilePicture()
	{
		findVisibleElement(userProfileMiddlePannel, Speed.slow);
		return isDisplayed(userProfile);

	}

}
