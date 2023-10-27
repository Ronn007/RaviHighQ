package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.InsertSiteUserLabels;
import com.highq.pageobjects.base.InsertSiteUserPage;

public class InsertSiteUserWeb extends BannerPageWeb implements InsertSiteUserPage
{

	public InsertSiteUserWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	// By orgTextBox = By.id("EXCnormal11@gmail.com");
	By fnameTextBox = By.xpath("//*[contains(@name,'fName')]");
	By lnameTextBox = By.xpath("//*[contains(@name,'lName')]");
	By saveButton = By.linkText(InsertSiteUserLabels.INSERTSITEUSER_SAVE);
	By cancelButton = By.linkText(InsertSiteUserLabels.INSERTSITEUSER_CANCEL);
	By noLastNameMsg = By.xpath(".//*[@id='collaborateCustomMessageModal' and @aria-hidden='false']");
	By continueButtonInPopup = By.id("collaborateMessageOkButton");
	By cancelButtonInPopup = By.id("collaborateMessageCancelButton");
	By nextButton = By.xpath("(.//*[contains(text(),'" + InsertSiteUserLabels.INSERTSITEUSER_NEXT + "')])[last()]");
	By addButton = By.id("adminUserNextBtnTop");
	By selectAllUserCheckBox = By.id("selectAddUserChkbox");
	By sendInvitationButton = By.id("addUserSendInviationTopButton");
	By adduserSendInvitationModelOpened = By.xpath("//*[@id='addUserSendInvitationModal' and @class='modal fade in']");
	By adduserSendInvitationModelClosed = By.xpath("//*[@id='addUserSendInvitationModal' and @class='modal fade']");

	By sendInvitationModel_sendButton = By.id("addUserSendInvitationModal_addUserSendInvitationBtn");
	By userTable = By.id("userRoleTable");
	By userPermissions_allUsers = By.xpath(".//*[@id='partiesList']/table/tbody/tr[not(@class='structureRow')]");

	String[] userRoles = {InsertSiteUserLabels.INSERTSITEUSER_USERROLE_SITEADMIN, InsertSiteUserLabels.INSERTSITEUSER_USERROLE_MEMBERADMIN, InsertSiteUserLabels.INSERTSITEUSER_USERROLE_CONTENTADMIN, InsertSiteUserLabels.INSERTSITEUSER_USERROLE_REPORTING, InsertSiteUserLabels.INSERTSITEUSER_USERROLE_QAADMIN, InsertSiteUserLabels.INSERTSITEUSER_USERROLE_SENDINVITATION};

	@Override
	public void setOrg(Map<String, List<String>> users)
	{
		for (Map.Entry<String, List<String>> map : users.entrySet())
		{
			domain = map.getKey();
			String org = domain.split("\\.")[0];
			try
			{
				WebElement ele = driver.findElement(By.id(domain));
				if (ele.isDisplayed())
				{
					ele.sendKeys(org);
				}
			}
			catch (Exception e)
			{
				continue;
			}
		}
	}

	@Override
	public AddUserWeb finishUserCreationProcess()
	{
		// finish operation
		while (isDisplayed(nextButton, Speed.slow))
		{
			WebElement nextButtonLink = findClickableElement(nextButton);
			nextButtonLink.click();

		}

		//
		// moveToElement(nextButton);
		// WebElement nextButtonLink = findClickableElement(driver, nextButton);
		// nextButtonLink.click();
		// findPresentElement(driver, userTable, Speed.slow, 10);
		//
		// moveToElement(nextButton);
		// WebElement nextButton2Link = findClickableElement(driver, nextButton);
		// nextButton2Link.click();

		WebElement addUserLink = findClickableElement(addButton, Speed.slow);
		addUserLink.click();
		sendInvitationToAllusers();

		return new AddUserWeb(driver);
	}

	public void sendInvitationToAllusers()
	{
		if (isDisplayed(selectAllUserCheckBox, Speed.slow))
		{
			setSelection(selectAllUserCheckBox, true);
		}
		if (isDisplayed(sendInvitationButton, Speed.slow))
		{
			findVisibleElement(sendInvitationButton).click();
		}

		findPresentElement(adduserSendInvitationModelOpened, Speed.slow);
		findVisibleElement(sendInvitationModel_sendButton).click();
		findPresentElement(adduserSendInvitationModelClosed);
	}

	public AddUserWeb assignRolesAndfinishUserCreationProcess(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles)
	{
		// finish operation
		while (isDisplayed(nextButton))
		{
			if (isDisplayed(userTable))
			{
				// assignUserRoles
				setUserRoles(userDataAndRoles);
			}

			WebElement nextButtonLink = findClickableElement(nextButton);
			nextButtonLink.click();
		}

		if (isDisplayed(addButton))
		{
			WebElement finishButtonLink = findClickableElement(addButton);
			finishButtonLink.click();
		}
		return new AddUserWeb(driver);
	}

	public void setUserRoles(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles)
	{
		for (Map.Entry<String, Map<String, Map<String, Boolean>>> domainDataAndUserDetails : userDataAndRoles.entrySet())
		{
			String domain = domainDataAndUserDetails.getKey();
			String userId;
			// Map<String, Map<String, Boolean>> userIdandRoles = domainDataAndUserDetails.getValue();
			for (Map.Entry<String, Map<String, Boolean>> userIdandRoles : domainDataAndUserDetails.getValue().entrySet())
			{
				userId = userIdandRoles.getKey();

				for (Map.Entry<String, Boolean> rolesAndPermission : userIdandRoles.getValue().entrySet())
				{
					String role = rolesAndPermission.getKey();
					boolean permission = rolesAndPermission.getValue();
					setRoles(domain, userId, role, permission);
				}
			}
		}
	}

	public void setRoles(String domain, String userName, String role, boolean desiredState)
	{
		// boolean result = false;
		if (domain.contains("."))
		{
			domain = domain.split("\\.")[0].trim();
		}
		List<WebElement> allUserList = driver.findElements(userPermissions_allUsers);
		int count = 0;
		for (WebElement e : allUserList)
		{
			String userTxt = e.getText().trim();
			System.err.println(e.getText());
			if (userTxt.equalsIgnoreCase(domain.trim()))
			{
				count++;
				break;
			}
			count++;
		}
		for (int j = count + 1; j <= allUserList.size(); j++)
		{
			String userTxt = findVisibleElement(By.xpath(".//*[@id='partiesList']/table/tbody/tr[not(@class='structureRow')][" + j + "]//td[@style]//a")).getText();
			System.out.println("User Name : " + userTxt + "");
			if (userTxt.equalsIgnoreCase(getUserData(userName)))
			{
				By siteAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='siteAdmin']");
				if (!role.toLowerCase().trim().equalsIgnoreCase(InsertSiteUserLabels.INSERTSITEUSER_USERROLE_SITEADMIN))
				{
					WebElement siteAdminCheckBox = findVisibleElement(siteAdminChkBox);
					if (siteAdminCheckBox.isSelected())
					{
						siteAdminCheckBox.click();
					}
				}
				String trim = role.toLowerCase().trim();
				if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_SITEADMIN.toLowerCase().equals(trim))
				{
					selectPermissionCheckbox(siteAdminChkBox, desiredState);
				}
				else if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_MEMBERADMIN.toLowerCase().equals(trim))
				{
					By memberAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='" + InsertSiteUserLabels.INSERTSITEUSER_NAMEATTRIBUTE_MODULEMEMBERMANAGER + "']");
					selectPermissionCheckbox(memberAdminChkBox, desiredState);
				}
				else if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_CONTENTADMIN.toLowerCase().equals(trim))
				{
					By contentAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='" + InsertSiteUserLabels.INSERTSITEUSER_NAMEATTRIBUTE_MODULECONTENTMANAGER + "']");
					selectPermissionCheckbox(contentAdminChkBox, desiredState);
				}
				else if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_REPORTING.toLowerCase().equals(trim))
				{
					By reportingChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='" + InsertSiteUserLabels.INSERTSITEUSER_NAMEATTRIBUTE_MODULEREPORTING + "']");
					selectPermissionCheckbox(reportingChkBox, desiredState);
				}
				else if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_QAADMIN.toLowerCase().equals(trim))
				{
					By qnaChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='" + InsertSiteUserLabels.INSERTSITEUSER_NAMEATTRIBUTE_MODULEQA + "']");
					selectPermissionCheckbox(qnaChkBox, desiredState);
				}
				else if (InsertSiteUserLabels.INSERTSITEUSER_USERROLE_SENDINVITATION.toLowerCase().equals(trim))
				{
					By sendInvitationChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='" + InsertSiteUserLabels.INSERTSITEUSER_NAMEATTRIBUTE_INVITATION + "']");
					selectPermissionCheckbox(sendInvitationChkBox, desiredState);
				}
				j = allUserList.size() + 1;
				// result = true;
			}

		}
	}

	public void selectPermissionCheckbox(By option, boolean check)
	{
		WebElement element = findVisibleElement(option);
		boolean checked = element.isSelected();
		if (checked != check)
			element.click();
	}
}
