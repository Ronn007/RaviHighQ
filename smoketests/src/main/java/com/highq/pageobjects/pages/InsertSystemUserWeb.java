package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.InsertSystemUsersLabels;
import com.highq.pageobjects.base.InsertSystemUserPage;

public class InsertSystemUserWeb extends BannerPageWeb implements InsertSystemUserPage
{

	public InsertSystemUserWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	// By orgTextBox = By.id("EXCnormal11@gmail.com");
	By fnameTextBox = By.xpath("//*[contains(@name,'fName')]");
	By lnameTextBox = By.xpath("//*[contains(@name,'lName')]");
	By saveButton = By.linkText(InsertSystemUsersLabels.INSERTSYSTEMUSER_SAVE);
	By cancelButton = By.linkText(InsertSystemUsersLabels.INSERTSYSTEMUSER_CANCEL);
	By noLastNameMsg = By.xpath(".//*[@id='collaborateCustomMessageModal' and contains(@style,'display: block;')]");
	By continueButtonInPopup = By.id("collaborateMessageOkButton");
	By cancelButtonInPopup = By.id("collaborateMessageCancelButton");
	By existingUserMessage = By.xpath("//*[@class='errorMessage']//span[contains(text(),'" + InsertSystemUsersLabels.INSERTSYSTEMUSER_USERALREADYINSYSTEMMESSAGE + "')]");

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
	public SearchUserPageWeb clickOnSave()
	{
		// scrollToElement(saveButton);
		WebElement saveButtonLink = findClickableElement(saveButton);
		saveButtonLink.click();
		try
		{
			if (isAlertPresent())
			{
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}

			if (isDisplayed(noLastNameMsg))
			{
				WebElement continueButtonInPopUplink = findClickableElement(continueButtonInPopup, Speed.slow, 10);
				continueButtonInPopUplink.click();
				if (isAlertPresent())
				{
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}
			}

		}
		catch (Exception e)
		{
			System.out.println("Last name available for user " + e.getMessage());
		}
		return new SearchUserPageWeb(driver);
	}

}
