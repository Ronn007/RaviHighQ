package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.UserRolesLabel;
import com.highq.pageobjects.base.UserRolesPage;

public class UserRolesWeb extends BannerPageWeb implements UserRolesPage
{

	public UserRolesWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveButton = By.xpath("//*[text()='" + UserRolesLabel.USERROLES_SAVE + "']");
	By cancellLink = By.xpath("//*[text()='" + UserRolesLabel.USERROLES_CANCEL + "']");
	By userPermissions_allUsers = By.xpath(".//*[@id='partiesList']/table/tbody/tr[not(@class='structureRow')]");

	public void setUserRoles(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles)
	{
		for (Map.Entry<String, Map<String, Map<String, Boolean>>> domainDataAndUserDetails : userDataAndRoles.entrySet())
		{
			String domain = null;
			if (domainDataAndUserDetails.getKey().contains("."))
			{
				domain = domainDataAndUserDetails.getKey().split("\\.")[0].trim();
			}
			else
			{
				domain = domainDataAndUserDetails.getKey().trim();
			}
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

			String userTxt = findVisibleElement(By.xpath(".//*[@id='partiesList']/table/tbody/tr[not(@class='structureRow')][" + j + "]//td[@style]//a")).getText().trim();
			System.out.println("User Name : " + userTxt + "");
			if (userTxt.equalsIgnoreCase(getUserData(userName)))
			{
				By siteAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='siteAdmin']");
				if (!role.toLowerCase().trim().equalsIgnoreCase("site admin"))
				{
					WebElement siteAdminCheckBox = findVisibleElement(siteAdminChkBox);
					if (siteAdminCheckBox.isSelected())
					{
						siteAdminCheckBox.click();
					}
				}
				String trim = role.toLowerCase().trim();
				if (UserRolesLabel.USERROLES_SITEADMIN.toLowerCase().equals(trim))
				{
					selectPermissionCheckbox(siteAdminChkBox, desiredState);
				}
				else if (UserRolesLabel.USERROLES_MEMBERADMIN.toLowerCase().equals(trim))
				{
					By memberAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='MODULE_MEMBERMANAGER']");
					selectPermissionCheckbox(memberAdminChkBox, desiredState);
				}
				else if (UserRolesLabel.USERROLES_CONTENTADMIN.toLowerCase().equals(trim))
				{
					By contentAdminChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='MODULE_CONTENTMANAGER']");
					selectPermissionCheckbox(contentAdminChkBox, desiredState);
				}
				else if (UserRolesLabel.USERROLES_REPORTING.toLowerCase().equals(trim))
				{
					By reportingChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='MODULE_REPORTING']");
					selectPermissionCheckbox(reportingChkBox, desiredState);
				}
				else if (UserRolesLabel.USERROLES_QAADMIN.toLowerCase().equals(trim))
				{
					By qnaChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='MODULE_QA']");
					selectPermissionCheckbox(qnaChkBox, desiredState);
				}
				else if (UserRolesLabel.USERROLES_SENDINVITATION.toLowerCase().equals(trim))
				{
					By sendInvitationChkBox = By.xpath(".//*[@id='userPermission']//tr[not(@class='structureRow')][" + j + "]//input[@name='invitation']");
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

	public void clickOnSave()
	{
		WebElement saveButtonLink = findClickableElement(saveButton);
		saveButtonLink.click();
	}

	public void clickOnCancel() throws InterruptedException
	{
		WebElement saveButtonLink = findClickableElement(cancellLink);
		saveButtonLink.click();
	}

}
