package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.highq.labels.collaborate.PeopleLabels;
import com.highq.pageobjects.base.PeoplePage;

public class PeopleWeb extends BannerPageWeb implements PeoplePage
{
	Actions build;

	By exportButton = By.id("exportBtn");
	/**************** Search filter ***********************/
	By searchInput = By.id("searchUserInput");
	By search_Loader = By.id("searchLoader");
	By search_ClearIcon = By.id("searchCloseBtn");

	/********** Filter *******************/
	By openFilterIcon = By.xpath(".//*[@id='searchUserInput']/../following-sibling::*/button");
	By filterMenu = By.id("filterMenu");
	By filter_AllOrgLink = By.xpath("//*[@id='filterMenu']//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ALLORGANISATIONS + "']");
	String filter_OrganisationList = ".//*[@id='filterMenu']//*[@class='TruncateTxt']";

	/************* Table **********/
	By peopleDataTable = By.id("peopleData");
	By peopleData_headerName = By.xpath(".//*[@id='peopleData']//th[normalize-space(.)='" + PeopleLabels.PEOPLE_HEADER_NAME + "']/div");
	By peopleData_headerOrganisation = By.xpath(".//*[@id='peopleData']//th[normalize-space(.)='" + PeopleLabels.PEOPLE_HEADER_ORGANISATION + "']/div");
	By peopleData_headerEmail = By.xpath(".//*[@id='peopleData']//th[normalize-space(.)='" + PeopleLabels.PEOPLE_HEADER_EMAIL + "']");
	By peopleData_headerOfficePhone = By.xpath(".//*[@id='peopleData']//th[normalize-space(.)='" + PeopleLabels.PEOPLE_HEADER_OFFICEPHONE + "']");
	By peopleData_headerMobilePhone = By.xpath(".//*[@id='peopleData']//th[normalize-space(.)='" + PeopleLabels.PEOPLE_HEADER_MOBILEPHONE + "']");

	By peopleData_allRows = By.xpath(".//*[@id='peopleData']//tr");
	By peopleData_singleResult_Name = By.xpath("(.//*[@id='peopleData']//tr[1]/td[1]//a)[1]");
	By peopleData_singleResult_Organisation = By.xpath("(.//*[@id='peopleData']//tr[1]/td[2])[1]");
	By peopleData_singleResult_Email = By.xpath("(.//*[@id='peopleData']//tr[1]/td[3]//a)[1]");
	By peopleData_singleResult_OfficePhone = By.xpath("(.//*[@id='peopleData']//tr[1]/td[4])[1]");

	By peopleData_singleResult_SendAMessageButton = By.xpath("(.//*[@id='peopleData']//tr[1]//*[normalize-space(.)='" + PeopleLabels.PEOPLE_SENDAMESSAGE + "'])[1]");
	By peopleData_singleResult_FollowButton = By.xpath("(.//*[@id='peopleData']//tr[1]//*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOW + "'])[1]");
	By peopleData_singleResult_FollowingButton = By.xpath("(.//*[@id='peopleData']//tr[2]//*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOWING + "'])[1]");
	By peopleData_singleResult_UnfollowButton = By.xpath(".//*[@id='peopleData']//tr[1]//*[contains(@onclick,'doUnfollow')]");

	/************* send a message *****************/
	By modalContent = By.xpath("(//*[contains(@style,'display: block;')]//*[@class='modal-content'])[last()]");
	By modalContentClosed = By.xpath("(//*[contains(@style,'display: none;')]//*[@class='modal-content'])[last()]");
	By newMessageModal = By.id("NEW_PRIVATE_MESSAGE_MODAL_BODY");
	By newMessage_RecipientsTextbox = By.id("newPrivateMessageUserList-tokenfield");
	By newMessage_MessageTextbox = By.id("newPrivateMessageEditor");
	By newMessage_MessageHelpInfo = By.xpath(".//*[@id='newPrivateMessage_dropContainer']//*[contains(@class,'greyMeta')]");
	By newMessage_Cancel = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + PeopleLabels.PEOPLE_CANCEL + "'])[last()]");
	By newMessage_Send = By.id("NEW_PRIVATE_MESSAGE_MODAL_sendButton");// By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='Send'])[last()]");
	By newMessage_linkIcon = By.xpath("(//*[@class='modal-footer']//*[contains(@class,'icon-link')])[last()]");
	By newMessage_attachIcon = By.xpath("(//*[@class='modal-footer']//*[contains(@class,'icon-paper-clip')])[last()]");
	By newMessage_textFormattingIcon = By.xpath("(//*[@class='modal-content'])[last()]//*[contains(@class,'icon-text-formating')]");

	/************ Add Attachment *********************/
	By attachmentModal = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]");
	By attachment_RecentTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_RECENT + "']");
	By attachment_BrowseTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_BROWSE + "']");
	By attachment_UploadTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_UPLOAD + "']");
	By attachment_SearchTab = By.xpath("(//*[@id='ATTACHMENT_MODAL'])[last()]//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_SEARCH + "']");
	By attachment_RecentTab_SiteDropDownButton = By.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_recentTab')])[1]");
	By attachment_BrowseTab_SiteDropDownButton = By.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_browseTab')])[1]");
	By attachment_SearchTab_SiteDropDownButton = By.xpath("((//*[@id='ATTACHMENT_MODAL'])[last()]//*[contains(@onclick,'dropDownSiteList_searchTab')])[1]");
	By attachment_loader = By.xpath("(//*[@class='grayloader' and @style='display: block;'])[last()]");
	By attachment_loaderBig = By.xpath("(//*[contains(@*,'gray-loaderbig')])[last()]");
	By attachment_attachButton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_ATTACH + "'])[last()]");

	/**************** Add Attachment - site search ***************/
	By attachment_siteSearch_dropDownWindow = By.xpath("(//*[contains(@class,'dropdown open')])[last()]");
	By attachment_siteSearch_noResultsFound = By.xpath("(//*[contains(@class,'dropdown open')]//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_NORESULTSFOUND + "'])[last()]");
	By attachment_siteSearch_clearSearchIcon = By.xpath("(//*[contains(@class,'dropdown open')]//*[@data-original-title='" + PeopleLabels.PEOPLE_DATAORIGINALTITLEATTRIBUTE_CLEARSEARCHTERM + "'])[last()]");
	By attachment_siteSearch_searchInput = By.xpath("(//*[contains(@class,'dropdown open')]//*[contains(@id,'siteSearchInput')])[last()]");

	By attachment_BrowseTab_ExpandAll = By.xpath("(//*[contains(text(),'" + PeopleLabels.PEOPLE_ATTACHMENT_EXPANDALL + "')])[1]");
	By attachment_UploadTab_browseInput = By.xpath("(//*[normalize-space(text())='" + PeopleLabels.PEOPLE_ATTACHMENT_ORDRAGFILESHERE + "']/preceding-sibling::*/input)[last()]");
	By attachment_SearchTab_searchBox = By.id("insertlink_searchTab_searchContentInput");
	By attachment_SearchTab_searchDataAllElements = By.xpath(".//*[@id='insertLink_searchTab_searchDatalist']/*");
	By btnDone = By.xpath("(//span[contains(text(),'" + PeopleLabels.PEOPLE_DONE + "')])[last()]");

	By avatarIcon = By.xpath("(.//*[@id='peopleData']//*[contains(@src,'avatar')])[1]");

	public PeopleWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Export People list,
	 * clicks on export button.
	 * 
	 * @author dheeraj.rajput
	 */
	public void exportPeopleList()
	{
		findVisibleElement(exportButton, Speed.slow).click();
	}

	/**
	 * Search a user.
	 * 
	 * @param searchString
	 *        can be user name or user email.
	 * @author dheeraj.rajput
	 */
	public void searchPeople(String searchString)
	{
		WebElement searchTextBox = findVisibleElement(searchInput);
		searchTextBox.clear();
		searchTextBox.sendKeys(searchString);
		while (isDisplayed(search_Loader))
			;
	}

	/**
	 * Clears search input
	 * 
	 * @author dheeraj.rajput
	 */
	public void clearSearchBox()
	{
		if (isDisplayed(search_ClearIcon))
			findVisibleElement(search_ClearIcon).click();
	}

	/**
	 * Open Filter menu
	 * 
	 * @author dheeraj.rajput
	 */
	public void expandFilter()
	{
		if (!isDisplayed(filterMenu))
			findVisibleElement(openFilterIcon).click();
		findVisibleElement(filterMenu);
	}

	/**
	 * Filter by organisation name
	 * 
	 * @param orgName
	 *        to set filter for(i.e. All organisations or the organisation name)
	 * @author dheeraj.rajput
	 */
	public void filterByOrganisation(String orgName)
	{
		expandFilter();
		WebElement orgXpath = findVisibleElement(By.xpath("//*[@id='filterMenu']//*[normalize-space(text())='" + orgName.trim() + "']"));
		orgXpath.click();
	}

	/**
	 * Verify name of all the organisation is same after organisation filter is applied [Use it after filterByOrganisation]
	 * 
	 * @param orgName
	 *        to verify.
	 * @author dheeraj.rajput
	 */
	public boolean verifyFilteredOrgName(String orgName)
	{
		findVisibleElement(peopleData_headerOrganisation, Speed.slow);
		int totalRows = driver.findElements(peopleData_allRows).size();
		for (int i = 1; i <= totalRows - 1; i++)
		{
			WebElement orgColumn = findVisibleElement(By.xpath(".//*[@id='peopleData']//tr[" + i + "]/td[2]"), 5);
			if (!orgColumn.getText().trim().equals(orgName.trim()))
			{
				System.err.println("Filter for organisation [" + orgName + "] is not set");
				return false;
			}
		}
		return true;
	}

	/**
	 * Follow a user
	 * 
	 * @param userEmail
	 *        email of the user to follow.
	 * @author dheeraj.rajput
	 */
	public void followUser(String userEmail)
	{
		searchPeople(userEmail);
		findVisibleElement(peopleDataTable, Speed.slow);
		By followButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOW + "']");
		if (isDisplayed(followButton, Speed.slow))
			findVisibleElement(followButton).click();
	}

	/**
	 * Unfollow a user
	 * 
	 * @param userEmail
	 *        email of the user to unfollow.
	 * @author dheeraj.rajput
	 */
	public void unfollowUser(String userEmail)
	{
		searchPeople(userEmail);
		findVisibleElement(peopleDataTable, Speed.slow);
		By unfollowButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[contains(@onclick,'doUnfollow')]");
		if (isDisplayed(unfollowButton))
			findVisibleElement(unfollowButton).click();
		else
			System.err.println("Unfollow option not found");
	}

	/**
	 * Send a message for a single user
	 * 
	 * @param userEmail
	 *        email of the user to send message to.
	 * @param message
	 *        content that needs to be sent.
	 * @param otherRecipientsMail
	 *        email address of other recipient if any(Variable argument)
	 * @author dheeraj.rajput
	 */
	public void sendMessage(String userEmail, String message, String... otherRecipientsMail)
	{
		searchPeople(userEmail);
		findVisibleElement(peopleDataTable, Speed.slow);

		findVisibleElement(peopleData_singleResult_SendAMessageButton).click();
		findVisibleElement(newMessageModal, Speed.slow);
		if (otherRecipientsMail.length > 0)
		{
			for (int i = 0; i < otherRecipientsMail.length; i++)
			{
				findVisibleElement(newMessage_RecipientsTextbox).sendKeys(getUserData(otherRecipientsMail[i]));
				selectOptionFromAutoSuggest(otherRecipientsMail[i]);
			}
		}
		findVisibleElement(newMessage_MessageTextbox).sendKeys(message);
		findVisibleElement(newMessage_Send).click();
	}

	/**
	 * Verify user's name
	 * 
	 * @param name
	 *        to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	public boolean verifyName(String name)
	{
		findVisibleElement(peopleDataTable, Speed.slow);
		WebElement nameXpath = findVisibleElement(peopleData_singleResult_Name);
		return nameXpath.getText().trim().equals(getUserData(name.trim()));
	}

	/**
	 * Verify organisation
	 * 
	 * @param organisation
	 *        to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	public boolean verifyOrganisation(String organisation)
	{
		findVisibleElement(peopleDataTable, Speed.slow);
		WebElement orgXpath = findVisibleElement(peopleData_singleResult_Organisation);
		return orgXpath.getText().trim().equals(organisation.trim());
	}

	/**
	 * Verify email
	 * 
	 * @param email
	 *        to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	public boolean verifyEmail(String email)
	{
		findVisibleElement(peopleDataTable, Speed.slow);
		WebElement emailXpath = findVisibleElement(peopleData_singleResult_Email);
		return emailXpath.getText().trim().equals(email.trim());
	}

	/**
	 * Verify Office Phone
	 * 
	 * @param phone
	 *        number to verify
	 * @return true if successful
	 * @author dheeraj.rajput
	 */
	public boolean verifyOfficePhone(String phone)
	{
		findVisibleElement(peopleDataTable, Speed.slow);
		WebElement phoneXpath = findVisibleElement(peopleData_singleResult_OfficePhone);
		return phoneXpath.getText().trim().equals(phone.trim());
	}

	/**
	 * Click on attachment Icon available in Send a message box
	 * 
	 * @author dheeraj.rajput
	 */
	public void clickOnAttachmentIcon()
	{
		findVisibleElement(newMessage_attachIcon, Speed.slow).click();
	}

	/**
	 * Verify availability of Following button for a user
	 * 
	 * @param userEmail
	 *        email address of the user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public boolean verifyFollowingButtonAgainstUser(String userEmail)
	{
		By followingButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOWING + "']");
		return isDisplayed(followingButton, Speed.slow);
	}

	/**
	 * Verify availability of Follow button for a user
	 * 
	 * @param userEmail
	 *        email address of the user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public boolean verifyFollowButtonAgainstUser(String userEmail)
	{
		By followingButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOW + "']");
		return isDisplayed(followingButton, Speed.slow);
	}

	/**
	 * Verify Unfollow button is shown on mouse hover of following button
	 * 
	 * @param userEmail
	 *        email of the user
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 *         Created: 18 January 2018
	 *         Updated:
	 */
	public boolean verifyUnfollowButtonOnMouseHover(String userEmail)
	{
		By followingButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[normalize-space(.)='" + PeopleLabels.PEOPLE_FOLLOWING + "']");
		moveToElement(followingButton);
		By unfollowButton = By.xpath(".//*[@id='peopleData']//*[normalize-space(text())='" + userEmail.trim() + "']/../following-sibling::*/*[normalize-space(.)='" + PeopleLabels.PEOPLE_UNFOLLOW + "']");
		System.out.println("Unfollow text: " + findVisibleElement(unfollowButton).getText());
		return isDisplayed(unfollowButton);
	}

	/**
	 * Click on user name to open user profile
	 * 
	 * @param userEmail
	 *        email address of the user
	 * @author dheeraj.rajput
	 *         Created: 19 January 2018
	 *         Updated:
	 */
	public ViewUserProfileWeb clickUserName(String userEmail)
	{
		searchPeople(userEmail.trim());
		findVisibleElement(peopleData_singleResult_Name, Speed.slow).click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * Verify Organisation is present in Search Filter
	 * 
	 * @param orgName
	 *        name of the organisation to verify
	 * @author dheeraj.rajput
	 * @return true
	 *         if successful
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyOrganisationInSearchFilter(String orgName)
	{
		expandFilter();
		int organisationCount = driver.findElements(By.xpath(filter_OrganisationList)).size();
		for (int i = 1; i <= organisationCount; i++)
		{
			String fetchedOrgName = findVisibleElement(By.xpath("(" + filter_OrganisationList + ")[" + i + "]")).getText();
			if (orgName.trim().equals(fetchedOrgName.trim()))
				return true;
		}
		return false;
	}

	/**
	 * Verify total number of organisation in search organisation filter
	 * 
	 * @param expectedOrganisationCount
	 *        organisation count to verify(int)
	 * @author dheeraj.rajput
	 * @return true
	 *         if successful
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyOrganisationCountInSearchFilter(int expectedOrganisationCount)
	{
		expandFilter();
		int organisationCount = driver.findElements(By.xpath(filter_OrganisationList)).size();
		return (organisationCount == expectedOrganisationCount);
	}

	/**
	 * Verify search result is empty
	 * 
	 * @author dheeraj.rajput
	 * @return true
	 *         if successful
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifySearchResultIsEmpty()
	{
		return !isDisplayed(peopleData_singleResult_Name);
	}

	/**
	 * Verify availability of Name field
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyNameFieldIsPresent()
	{
		return isDisplayed(peopleData_headerName);
	}

	/**
	 * Verify availability of Organisation field
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyOrganisationFieldIsPresent()
	{
		return isDisplayed(peopleData_headerOrganisation);
	}

	/**
	 * Verify availability of Email field
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyEmailFieldIsPresent()
	{
		return isDisplayed(peopleData_headerEmail);
	}

	/**
	 * Verify availability of Office Phone field
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyOfficePhoneFieldIsPresent()
	{
		return isDisplayed(peopleData_headerOfficePhone);
	}

	/**
	 * Verify availability of Mobile Phone field
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyMobilePhoneFieldIsPresent()
	{
		return isDisplayed(peopleData_headerMobilePhone);
	}

	/**
	 * Verify availability of user avatar icon against name
	 * 
	 * @return true if successful
	 * @author dheeraj.rajput
	 *         Created: 05 March 2018
	 *         Updated:
	 */
	public boolean verifyUserAvatarIconIsPresent()
	{
		return isDisplayed(avatarIcon);
	}
}
