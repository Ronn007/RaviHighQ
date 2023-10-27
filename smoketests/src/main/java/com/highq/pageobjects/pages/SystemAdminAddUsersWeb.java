package com.highq.pageobjects.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminAddUsersPage;

/**
 * @author dakshesh.dalwadi
 */
/**
 * @author dakshesh.dalwadi
 */
/**
 * @author dakshesh.dalwadi
 */
public class SystemAdminAddUsersWeb extends BannerPageWeb implements SystemAdminAddUsersPage
{

	String invalidEmailAlertText = SystemAdminLabels.SYSADMIN_ADDUSER_EMAILADDRESSINVALIDMSG;
	By emailIDCSV = By.id("emailCSVID");
	By nextButton = By.xpath(".//*[@id='insertSystemUser']//a[contains(text(),'" + SystemAdminLabels.SYSADMIN_ADDUSER_NEXT + "')]");
	By cancelButton = By.linkText(SystemAdminLabels.SYSADMIN_ADDUSER_CANCEL);
	By errorMessage = By.className("actionMessage");
	By fnameTextBox = By.xpath("//*[contains(@name,'fName')]");
	By lnameTextBox = By.xpath("//*[contains(@name,'lName')]");
	By saveButton = By.linkText(SystemAdminLabels.SYSADMIN_ADDUSER_SAVE);

	/****************************/
	By userIcon = By.className("imgGroupForMemberAdmin");

	public SystemAdminAddUsersWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public Map<Boolean, BannerPageWeb> clickOnNext()
	{
		Map<Boolean, BannerPageWeb> map = new HashMap<>();

		// moveToElement(nextButton);
		WebElement nextButtonLink = findClickableElement(nextButton);
		if (nextButtonLink.isDisplayed())
		{
			nextButtonLink.click();
			try
			{

				if (isAlertPresent())
				{
					Alert invalidEmailAlert = driver.switchTo().alert();
					String invalidMail = invalidEmailAlert.getText();
					if (invalidMail.contains(invalidEmailAlertText))
					{
						invalidEmailAlert.accept();
						System.out.println("Please insert valid email Id");
						Assert.assertFalse(true);
					}
				}
				if (isDisplayed(errorMessage))
				{
					WebElement cancellButtonLink = findClickableElement(cancelButton);
					cancellButtonLink.click();
					map.put(false, new SearchUserPageWeb(driver));
				}
				else
					map.put(true, new InsertSystemUserWeb(driver));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public void enterEmailIds(Map<String, List<String>> emailIds)
	{
		StringBuffer ids = new StringBuffer();
		WebElement ele = findClickableElement(emailIDCSV);
		if (emailIds.size() != 0)
		{
			for (Map.Entry<String, List<String>> map : emailIds.entrySet())
			{
				domain = map.getKey();
				List<String> list = map.getValue();
				for (String email : list)
				{
					setUserListForResetPassword(email, domain);
					ids = ids.append(email.concat("@" + domain) + ",");
					// System.out.println(ids);
				}
			}
		}
		if (ele.isDisplayed())
		{
			ele.sendKeys(ids);
		}
	}

	// public void enterUsers(Map<String, List<String>> users)
	// {
	// String emailIds = null;
	// WebElement ele = waitAndFindElement(driver, emailIDCSV);
	// for (Map.Entry<String, List<String>> map : users.entrySet())
	// {
	// // String donain = map.getKey();
	// List<String> userList = map.getValue();
	// for (String u : userList)
	// {
	// emailIds = u + ",";
	// }
	// }
	// ele.sendKeys(emailIds);
	// }
}
