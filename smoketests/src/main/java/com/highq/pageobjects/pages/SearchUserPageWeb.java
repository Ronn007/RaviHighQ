package com.highq.pageobjects.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.CollaborateLabel.UserPermission;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.SearchUserLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.SearchUserPageData;
import com.highq.pageobjects.base.AspAdminUserPermissionPage;
import com.highq.pageobjects.base.EditUserProfilePage;
import com.highq.pageobjects.base.SearchUserPage;

public class SearchUserPageWeb extends BannerPageWeb implements SearchUserPage
{

	By leftPanelUserAdminOption = By.id("UserAdmin");
	EditUserProfilePage editUserProfileWeb;

	public SearchUserPageWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By userSearchDisplayName = By.id("user_search_DisplayName");
	By userSearchEmailAddress = By.id("user.search_EmailAddress");
	By searchButton = By.xpath(".//*[@id='userForm']//a[@title='" + SearchUserLabels.SEARCHUSERPAGE_SEARCH + "']");
	By searchResult = By.id("proxyForm");

	By resetButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_RESET + "']");
	By inviteButton = By.id("InviteId");
	By rolesButton = By.id("PermissionID");
	By activeButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_ACTIVE + "']");
	By moveButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_MOVE + "']");
	By exportButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_EXPORT + "']");
	By unlockUserButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_UNLOCKUSER + "']");
	By archiveButton = By.xpath(".//*[@id='proxyForm']/div[1]//a/strong[text()='" + SearchUserLabels.SEARCHUSERPAGE_ARCHIVE + "']");
	By inactiveButton = By.xpath(".//*[@id='proxyForm']/div[1]//a[text()='" + SearchUserLabels.SEARCHUSERPAGE_INACTIVE + "']");
	By logo = By.id("logo");
	By passwordText = By.id("Login_password");
	By confirmPasswordText = By.id("Login_confirmpassword");
	By termsAndCondition = By.id("ChoosePasswordTermAndCondition");
	By setPasswordButton = By.xpath(".//*[@id='choosepasswordform']/button");
	By sameaPasswordError = By.id("Login_password_pID");
	By statusLocked = By.xpath("//label[normalize-space(.)='" + SearchUserLabels.SEARCHUSERPAGE_STATUS + "']//following::input[@id='user_search_locked'][1]");
	By searchButtonLink = By.xpath(".//div[@class='accLink']//a");
	By archiveLink = By.xpath("//a/strong[text()='" + SearchUserLabels.SEARCHUSERPAGE_ARCHIVE + "']");
	By userSearchResult = By.id("userSearchResult");
	By resetUserPopup = By.xpath(".//*[@id='collaborateCustomMessageModal' and contains(@style,'display: block')]");
	By adduserButton = By.xpath(".//*[@id='emailDomainList']//span[normalize-space(.)='" + SearchUserLabels.SEARCHUSERPAGE_ADDUSERS + "']");
	By resetPassswordModel = By.xpath(".//*[@id='collaborateCustomMessageModal' and @aria-hidden='false']");
	By domainSuggetionsList = By.xpath(".//*[@id='domainSuggest_container']//input[1]");
	By collaborateMessageOkButton = By.id("collaborateMessageOkButton");

	By domainSuggetionBox = By.className("as-list");

	By makeAnonymiseButton = By.id("annonymiseUser");
	By anonymiseModelHeading = By.id("customMessageModalTitle");
	By anonymiseButton = By.id("collaborateMessageOkButton");
	By anonymiseCancelButton = By.id("collaborateMessageCancelButton");
	By anonymiseModelMessage = By.id("collaborateCustomModalMessage");
	By selfRegisteredButton = By.xpath(".//*[@id='emailDomainList']//span[normalize-space(.)='" + SearchUserLabels.SEARCHUSERPAGE_SELF_REGISTERED_USERS + "']");
	// (//td[normalize-space(.)='alan.el-charkowi@highq.com']//preceding::input[@id='chkUserID'])[1]
	// (// a[normalize-space(.)='Alan El-charkowi']//following::a[normalize-space(.)='link'])[1]
	By systemAdminContainer = By.xpath(".//*[@class='systemAdminContainer']");

	Map<String, String> resetPassLink = new LinkedHashMap<>();

	AspAdminUserPermissionPage aspAdminUserPermissionWeb;
	ConfigurationData configurationData;

	@Override
	public void enterUserName(String userName)
	{

		WebElement ele = findClickableElement(userSearchDisplayName);
		if (ele.isDisplayed())
		{
			ele.sendKeys(userName);
		}
	}

	@Override
	public void enterUserEmail(String userEmail)
	{
		WebElement ele = findClickableElement(userSearchEmailAddress);
		if (ele.isDisplayed())
		{
			ele.sendKeys(userEmail);
		}
	}

	@Override
	public SearchUserPageWeb setPassword(Map<String, List<String>> users, String password, SearchUserPageData userStausData) throws InterruptedException
	{

		Map<String, String> links = selectAndResetUsers(users, password, userStausData.getStatus(), userStausData.isStausLocked());
		System.out.println(links);

		System.out.println(links.size());
		WebDriver simultaneousDriver;
		for (Map.Entry<String, String> u : links.entrySet())
		{
			String key = u.getKey();
			String value = u.getValue();

			System.out.println(key + "\n-----------------\n" + value);
			System.out.println("-----------------");
			simultaneousDriver = setDriver(value);
			simultaneousDriver.get(value);
			simultaneousDriver.findElement(passwordText).sendKeys(password);
			simultaneousDriver.findElement(confirmPasswordText).sendKeys(password);
			simultaneousDriver.findElement(termsAndCondition).click();
			simultaneousDriver.findElement(setPasswordButton).click();
			editUserProfileWeb = new EditUserProfileWeb(simultaneousDriver);
			editUserProfileWeb.logout();
			simultaneousDriver.close();
		}
		return new SearchUserPageWeb(driver);
	}

	@Override
	public void clickOnSearch()
	{
		WebElement ele = findClickableElement(searchButton);
		if (ele.isDisplayed())
		{
			ele.click();
		}
	}

	@Override
	public SystemAdminAddUsersWeb gotoAddUser()
	{
		findClickableElement(adduserButton).click();
		return new SystemAdminAddUsersWeb(driver);
	}

	@Override
	public void resetPassword(Map<String, List<String>> users, String password) throws InterruptedException
	{
		if (isDisplayed(resetPassswordModel))
		{
			findClickableElement(By.id("collaborateMessageOkButton")).click();
		}
		for (Map.Entry<String, List<String>> user : users.entrySet())
		{
			List<String> userList = user.getValue();
			for (String u : userList)
			{
				String link = getResetPasswordLink(u);
				WebDriver simultaneousDriver = setDriver(link);
				simultaneousDriver.findElement(passwordText).sendKeys(password);
				simultaneousDriver.findElement(confirmPasswordText).sendKeys(password);
				simultaneousDriver.findElement(termsAndCondition).click();
				simultaneousDriver.findElement(setPasswordButton).click();
				EditUserProfilePage editUserProfileWeb = new EditUserProfileWeb(simultaneousDriver);
				editUserProfileWeb.logout();
			}
		}

	}

	public Map<String, String> selectAndResetUsers(Map<String, List<String>> users, String password, UserStatus status, boolean locked) throws InterruptedException
	{

		for (Map.Entry<String, List<String>> user : users.entrySet())
		{
			domain = user.getKey();
			int totalReleventUsersInDb = getDomainUserCount(domain, UserStatus.Active);
			int totalUserToReset = user.getValue().size();

			///////////// #################################################### ///////////////////////
			if (totalReleventUsersInDb - totalUserToReset > 1)
			{
				// search by org
				expandSearchInUserAdministration();
				searchDomain(domain, status, locked);
				for (int i = 0; i < user.getValue().size(); i++)
				{
					String userId = user.getValue().get(i).concat("@" + domain);
					if (resetUserPassword.contains(userId.toLowerCase()))
					{
						resetUserId(userId.toLowerCase(), password);
					}
				}
				// resetBulkUser(domain, user.getValue());
			}
			else
			{
				// searchIndividual

				for (int i = 0; i < user.getValue().size(); i++)
				{
					expandSearchInUserAdministration();
					String userId = user.getValue().get(i).concat("@" + domain);
					if (resetUserPassword.contains(userId.toLowerCase()))
					{
						searchUser(userId.toLowerCase(), status, locked);
						resetUserId(userId.toLowerCase(), password);
					}
				}
				// searchAndResetUser(domain, user.getValue());
			}
			///////////// #################################################### ///////////////////////
		}

		for (Map.Entry<String, List<String>> resetPassLink : users.entrySet())
		{
			System.out.println(resetPassLink.getKey() + " ****************" + resetPassLink.getValue().size() + "\n");
		}

		return resetPassLink;

	}

	@Override
	public void expandSearchInUserAdministration()
	{
		findVisibleElement(systemAdminContainer);
		if (isDisplayed(searchButtonLink, Speed.slow))
		{
			WebElement searchLink = findClickableElement(searchButtonLink);
			searchLink.click();
		}
	}

	@Override
	public void searchDomain(String domain, UserStatus status, boolean locked) throws InterruptedException
	{
		/***** Searching domain **/
		System.out.println("Domain :: " + domain);
		WebElement domainTextBox = findClickableElement(domainSuggetionsList);
		if (domainTextBox.isDisplayed())
		{
			domainTextBox.sendKeys(Keys.BACK_SPACE);
			domainTextBox.sendKeys(Keys.BACK_SPACE);
			domainTextBox.sendKeys(domain);
			By domainFromList = By.xpath(".//*[@id='domainSuggest_container']//ul[@class='as-list']/li[@title='" + domain.trim().toLowerCase() + "']");

			findVisibleElement(domainSuggetionBox, Speed.slow);

			// Thread.sleep(2000);
			// scrollToElement(domainFromList);
			WebElement domainLink = findVisibleElement(domainFromList, Speed.slow);
			domainLink.click();
		}
		WebElement emailAddressTextbox = findVisibleElement(userSearchEmailAddress, Speed.slow);
		emailAddressTextbox.clear();

		selectUserStatus(status.toString(), locked);
		WebElement searchButtonLink = findVisibleElement(searchButton, Speed.slow);
		searchButtonLink.click();

		findVisibleElement(userSearchResult, Speed.slow);

		/**** Domain search done ****/
	}

	@Override
	public void selectUserStatus(String status, boolean locked) throws InterruptedException
	{
		By statusButtonXpath = By.xpath("//label[normalize-space(.)='" + status.trim() + "']/input");
		// scrollToElement(statusButtonXpath);
		WebElement statusButton = findClickableElement(statusButtonXpath);
		statusButton.click();

		WebElement lockedChkBox = findClickableElement(statusLocked);
		if (!lockedChkBox.isSelected() && locked)
		{
			lockedChkBox.click();
		}

	}

	@Override
	public void resetBulkUser(String domain, List<String> userList)
	{
		for (String user : userList)
		{
			String username = user.concat("@" + domain).trim();
			System.out.println(username);
			try
			{
				System.out.println(username);
				By userChkBox = By.xpath("//td[normalize-space(.)='" + username + "']//preceding::input[@id='chkUserID'][1]");
				// scrollToElement(userChkBox);
				WebElement userCheckBox = findClickableElement(userChkBox, Speed.slow, 3);

				if (!userCheckBox.isSelected())
				{
					userCheckBox.click();
				}
				selectUserOptions(SearchUserLabels.SEARCHUSERPAGE_RESET);
				String link = getResetPasswordLink(username);
				resetPassLink.put(username, link);
				System.out.println(resetPassLink);
			}
			catch (Exception e)
			{
				System.out.println("No user found : " + user + " ::::: Exception is  : " + e);
			}
		}

	}

	@Override
	public void searchAndResetUser(String domain, List<String> userList)
	{
		for (int i = 0; i < userList.size(); i++)
		{
			String userId = userList.get(i).concat("@" + domain);
			try
			{
				searchUser(userId, UserStatus.Active, false);
			}
			catch (InterruptedException e)
			{
				System.out.println("Search user is not worked");
			}
			String restLink = getUserRestPasswordLink(userId);
			resetPassLink.put(userId, restLink);
		}
	}

	@Override
	public String getResetPasswordLink(String userEmail)
	{
		String link = null;
		try
		{

			// By userChkBox = By.xpath("//td[normalize-space(.)='" + userEmail + "']//preceding::input[@id='chkUserID'][1]");
			// scrollToElement(userChkBox);
			// if (isDisplayed(driver, userChkBox))
			// {
			link = findClickableElement(By.xpath("//td[normalize-space(.)='" + userEmail.trim() + "']//following::a[normalize-space(.)='" + SearchUserLabels.SEARCHUSERPAGE_LINK + "'][1]")).getAttribute("href");
			String resetPasswordLink = link.substring(link.lastIndexOf("/") + 1);
			System.out.println("resetPassword link : " + resetPasswordLink);

			int dotComIndex = url.indexOf(".com/") + 4;
			if (url.substring(dotComIndex + 1, url.length()).contains("/"))
			{
				System.out.println("Print");
				int next = url.indexOf("/", dotComIndex + 1);
				url = url.substring(0, next);
			}

			return url + "/" + resetPasswordLink;
			// }
		}
		catch (Exception e)
		{
			System.out.println("No user available" + e);
		}
		return null;
	}

	@Override
	public void searchUser(String userEmail, UserStatus status, boolean locked) throws InterruptedException
	{
		try
		{
			WebElement emailAddressTextbox = findClickableElement(userSearchEmailAddress, Speed.slow);
			emailAddressTextbox.clear();
			emailAddressTextbox.sendKeys(userEmail);
			selectUserStatus(status.toString(), locked);
			WebElement searchLink = findClickableElement(searchButton, Speed.slow);
			searchLink.click();
			findPresentElement(userSearchResult, Speed.slow);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@Override
	public String getUserRestPasswordLink(String userId)
	{
		String link = null;
		try
		{
			System.out.println("-----------> " + "//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
			By userChkBox = By.xpath("//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
			// scrollToElement(userChkBox);
			WebElement userCheckBox = findClickableElement(userChkBox, Speed.slow);
			if (!userCheckBox.isSelected())
			{
				userCheckBox.click();
			}
			selectUserOptions(SearchUserLabels.SEARCHUSERPAGE_RESET);
			link = getResetPasswordLink(userId);
			System.out.println("---> " + link);
		}
		catch (Exception e)
		{
			System.out.println("No user found : " + userId + " ::::: Exception is  : " + e);
		}
		return link;
	}

	/**
	 * @modified by vivek.mishra
	 * @modified on 11/04/2018
	 */
	public void selectUserOptions(String operation)
	{
		String operationToPerform = operation.toString().trim();
		if (operationToPerform.contains("_"))
			operationToPerform = operation.toString().replace("_", " ").trim();
		if (SearchUserLabels.SEARCHUSERPAGE_ARCHIVE.equals(operation.toString()))
		{
			WebElement archiveButtonLink = findVisibleElement(archiveLink, Speed.slow);
			archiveButtonLink.click();
		}
		else
		{
			By userOperation = By.xpath(".//*[@id='proxyForm']/div/a[text()='" + operationToPerform.trim() + "']");
			WebElement resetButtonLink = findVisibleElement(userOperation, Speed.slow);
			if (isDisplayed(userOperation, Speed.slow))
				resetButtonLink.click();
		}

		if (isAlertPresent())
		{
			driver.switchTo().alert().accept();
		}

		if (isDisplayed(resetUserPopup, Speed.slow))
		{
			WebElement acceptPopUp = findVisibleElement(collaborateMessageOkButton, Speed.slow);
			acceptPopUp.click();
		}
	}

	@Override
	public void resetUserId(String userId, String password)
	{
		By userChkBox = By.xpath("//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
		System.out.println("---->" + "//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
		// scrollToElement(userChkBox);
		// WebElement userCheckBox = findVisibleElement(userChkBox, Speed.slow);
		setSelection(userChkBox, true);

		selectUserOptions(SearchUserLabels.SEARCHUSERPAGE_RESET);
		String link = getResetPasswordLink(userId) + "," + password;
		resetPassLink.put(userId, link);
		System.out.println(resetPassLink);

	}

	@Override
	public void setUserRoles(Map<String, Map<String, Map<UserPermission, Boolean>>> users, UserStatus status, boolean locked) throws InterruptedException
	{
		for (Entry<String, Map<String, Map<UserPermission, Boolean>>> user : users.entrySet())
		{
			domain = user.getKey();
			// searchIndividual
			expandSearchInUserAdministration();

			Map<String, Map<UserPermission, Boolean>> userData = user.getValue();

			for (Entry<String, Map<UserPermission, Boolean>> userDataAndPermission : userData.entrySet())
			{
				String userId = userDataAndPermission.getKey().concat("@" + domain);
				System.out.println("001 --- > " + userId);
				searchUser(userId, status, locked);

				setRoles(userId, userDataAndPermission.getValue());

			}
		}

	}

	public void setRoles(String userId, Map<UserPermission, Boolean> UserPermissions) throws InterruptedException
	{

		System.out.println("002 --- > " + userId);
		By userChkBox = By.xpath("//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
		// if(!isDisplayed(driver, userChkBox)) {
		// System.out.println("User is not present");
		// Assert.assertTrue(false);
		// }
		System.out.println("---->" + "//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]");
		// scrollToElement(userChkBox);

		WebElement userCheckBox = findPresentElement(userChkBox, Speed.slow, 10);

		if (!userCheckBox.isSelected())
		{
			userCheckBox.click();
		}
		aspAdminUserPermissionWeb = clickRoles(SearchUserLabels.SEARCHUSERPAGE_ROLES);

		for (Map.Entry<UserPermission, Boolean> UserPermissionData : UserPermissions.entrySet())
		{
			for (UserPermission permission : UserPermission.values())
			{
				if (permission.toString().equals(UserPermissionData.getKey().toString()))
				{
					aspAdminUserPermissionWeb.setUserPermission(UserPermissionData.getKey(), UserPermissionData.getValue());
				}
				else if (!UserPermissions.containsKey(UserPermissionData.getKey()))
				{
					aspAdminUserPermissionWeb.setUserPermission(permission, false);
				}
			}
		}
		aspAdminUserPermissionWeb.clickOnSave();

	}

	@Override
	public AspAdminUserPermissionWeb clickRoles(String operation)
	{
		String operationToPerform = operation.toString();
		if (operationToPerform.contains("_"))
		{
			operationToPerform = operation.toString().replace("_", " ").trim();
		}
		// expandSearchInUserAdministration();
		WebElement resetButtonLink = findClickableElement(By.xpath(".//*[@id='proxyForm']/div/a[text()='" + operationToPerform.trim() + "']"), Speed.slow);
		resetButtonLink.click();

		if (isDisplayed(resetUserPopup))
		{
			WebElement acceptPopUp = findClickableElement(By.id("collaborateMessageOkButton"));
			acceptPopUp.click();
		}
		return new AspAdminUserPermissionWeb(driver);
	}

	/** This method used in new preconditions */
	public SearchUserPageWeb setPassword(Map<String, List<String>> users, String password, UserStatus status, boolean statusLocked) throws InterruptedException
	{

		Map<String, String> links = selectAndResetUsers(users, password, status, statusLocked);
		System.out.println(links);

		System.out.println(links.size());
		WebDriver simultaneousDriver;
		for (Map.Entry<String, String> u : links.entrySet())
		{
			String key = u.getKey();
			String values[] = u.getValue().split(",");

			String resetLink = values[0];
			String resetPassword = values[1];

			System.out.println(key + "\n-----------------\n" + resetLink + "\n");
			System.out.println("Reset password = " + resetPassword);
			System.out.println("-----------------");
			simultaneousDriver = setDriver(resetLink);
			simultaneousDriver.get(resetLink);
			simultaneousDriver.findElement(passwordText).sendKeys(resetPassword);
			simultaneousDriver.findElement(confirmPasswordText).sendKeys(resetPassword);
			simultaneousDriver.findElement(termsAndCondition).click();
			simultaneousDriver.findElement(setPasswordButton).click();
			editUserProfileWeb = new EditUserProfileWeb(simultaneousDriver);
			editUserProfileWeb.logout();
			simultaneousDriver.close();
		}
		return new SearchUserPageWeb(driver);
	}

	public SearchUserPageWeb setPassword(Map<String, List<String>> users, Map<String, String> userIdAndPassword, UserStatus status, boolean statusLocked) throws InterruptedException
	{

		Map<String, String> links = selectAndResetUsers(users, userIdAndPassword, status, statusLocked);
		System.out.println(links);

		System.out.println(links.size());
		WebDriver simultaneousDriver;
		for (Map.Entry<String, String> u : links.entrySet())
		{
			String key = u.getKey();
			String values[] = u.getValue().split(",");

			String resetLink = values[0];
			String resetPassword = values[1];

			System.out.println(key + "\n-----------------\n" + resetLink + "\n");
			System.out.println("Reset password = " + resetPassword);
			System.out.println("-----------------");
			simultaneousDriver = setDriver(resetLink);
			simultaneousDriver.get(resetLink);
			simultaneousDriver.findElement(passwordText).sendKeys(resetPassword);
			simultaneousDriver.findElement(confirmPasswordText).sendKeys(resetPassword);
			simultaneousDriver.findElement(termsAndCondition).click();
			simultaneousDriver.findElement(setPasswordButton).click();
			editUserProfileWeb = new EditUserProfileWeb(simultaneousDriver);
			editUserProfileWeb.logout();
			simultaneousDriver.close();
		}
		return new SearchUserPageWeb(driver);
	}

	public Map<String, String> selectAndResetUsers(Map<String, List<String>> users, Map<String, String> userIdAndPassword, UserStatus status, boolean locked) throws InterruptedException
	{

		for (Map.Entry<String, List<String>> user : users.entrySet())
		{
			domain = user.getKey();
			int totalReleventUsersInDb = getDomainUserCount(domain, UserStatus.Active);
			int totalUserToReset = user.getValue().size();

			///////////// #################################################### ///////////////////////
			if (totalReleventUsersInDb - totalUserToReset > 1)
			{
				// search by org
				expandSearchInUserAdministration();
				searchDomain(domain, status, locked);
				for (int i = 0; i < user.getValue().size(); i++)
				{
					String userId = user.getValue().get(i).concat("@" + domain);
					if (resetUserPassword.contains(userId.toLowerCase()))
					{
						resetUserId(userId.toLowerCase(), userIdAndPassword.get(userId));
					}
				}
			}
			else
			{
				// searchIndividual

				for (int i = 0; i < user.getValue().size(); i++)
				{
					expandSearchInUserAdministration();
					String userId = user.getValue().get(i).concat("@" + domain);
					if (resetUserPassword.contains(userId.toLowerCase()))
					{
						searchUser(userId.toLowerCase(), status, locked);
						resetUserId(userId.toLowerCase(), userIdAndPassword.get(userId));
					}
				}
				// searchAndResetUser(domain, user.getValue());
			}
			///////////// #################################################### ///////////////////////
		}

		for (Map.Entry<String, List<String>> resetPassLink : users.entrySet())
		{
			System.out.println(resetPassLink.getKey() + " ****************" + resetPassLink.getValue().size() + "\n");
		}

		return resetPassLink;

	}

	/**
	 * @author ankit.motaval
	 *         verify Make Anonymise Button
	 * @created on 11/04/2018
	 */
	public boolean verifyMakeAnonymiseButton()
	{
		return isDisplayed(makeAnonymiseButton, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         click on Make Anonymous Button
	 * @created on 11/04/2018
	 */
	public void clickOnMakeAnonymise()
	{
		findClickableElement(makeAnonymiseButton, Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 * @param heading
	 *        Verify Make Anonymous Model
	 * @created on 11/04/2018
	 */
	public boolean verifyMakeAnonymiseModel(String heading, String buttonName)
	{
		return verifyMakeAnonymiseModelHeading(heading) && verifyMakeAnonymiseModelButton(buttonName) && isDisplayed(anonymiseCancelButton, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 * @param heading
	 *        Verify Make Anonymous Model Heading
	 * @created on 11/04/2018
	 */
	public boolean verifyMakeAnonymiseModelHeading(String heading)
	{
		String contentText = findVisibleElement(anonymiseModelHeading, Speed.slow).getText();
		return contentText.equals(heading.trim());
	}

	/**
	 * @author ankit.motaval
	 * @param heading
	 *        Verify Make Anonymous Button
	 * @created on 11/04/2018
	 */
	public boolean verifyMakeAnonymiseModelButton(String buttonName)
	{
		String contentText = findVisibleElement(anonymiseButton, Speed.slow).getText();
		return contentText.equals(buttonName.trim());
	}

	/**
	 * @author ankit.motaval
	 * @param message
	 *        Verify Make Anonymous Model Message
	 * @created on 11/04/2018
	 */
	public boolean verifyMakeAnonymiseModelMessage(String message)
	{
		String contentText = findVisibleElement(anonymiseModelMessage, Speed.slow).getText();
		return contentText.contains(message);
	}

	/**
	 * @author ankit.motaval
	 *         Click on Cancel Button of Make Anonymous Model
	 * @created on 11/04/2018
	 */
	public void clickOnCancelButtonOfAnonymiseModel()
	{
		findVisibleElement(anonymiseCancelButton, Speed.slow).click();

	}

	/**
	 * @author ankit.motaval
	 *         Click on Anonymise Button of Make Anonymous Model
	 * @created on 11/04/2018
	 */
	public void clickOnAnonymiseButtonOfAnonymiseModel()
	{
		findVisibleElement(anonymiseButton, Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 * @param username
	 *        Click on Search result user
	 * @created on 11/04/2018
	 */
	public ViewUserProfileWeb clickOnSearchResultUser(String username)
	{
		findVisibleElement(By.xpath(".//*[@id='userSearchResult']//a[normalize-space(.)='" + username.trim() + "']"), Speed.slow).click();
		return new ViewUserProfileWeb(driver);
	}

	/**
	 * @author ankit.motaval
	 * @param users
	 * @param status
	 * @param locked
	 *        Select user from search page
	 * @throws InterruptedException
	 * @created on 11/04/2018
	 */
	public void selectUserFromSearchPage(Map<String, List<String>> users, UserStatus status, boolean locked) throws InterruptedException
	{
		for (Map.Entry<String, List<String>> user : users.entrySet())
		{
			domain = user.getKey();

			// search by org
			expandSearchInUserAdministration();
			searchDomain(domain, status, locked);
			for (int i = 0; i < user.getValue().size(); i++)
			{
				String userId = user.getValue().get(i).concat("@" + domain);
				setSelection(By.xpath("//td[normalize-space(.)='" + userId.trim() + "']//preceding::input[@id='chkUserID'][1]"), true);
			}
		}
	}

	/**
	 * @author ankit.motaval
	 * @param userEmail
	 *        Verify checkbox is enable
	 * @created on 23/04/2018
	 */
	public boolean isCheckboxEnable(String userEmail)
	{
		return findPresentElement(By.xpath("//td[normalize-space(.)='" + userEmail.trim() + "']//preceding::input[@id='chkUserID'][1]")).isEnabled();
	}

	/**
	 * @author ankit.motaval
	 *         Go to Self Registered Users Page
	 * @created on 23/04/2018
	 */
	public SystemAdminSelfRegisteredUsersWeb gotoSelfRegisteredUsers()
	{
		findClickableElement(selfRegisteredButton).click();
		return new SystemAdminSelfRegisteredUsersWeb(driver);
	}

}
