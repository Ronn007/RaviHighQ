package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.SystemAdminSelfRegisteredUsersPage;

public class SystemAdminSelfRegisteredUsersWeb extends BannerPageWeb implements SystemAdminSelfRegisteredUsersPage
{
	By filterButton = By.xpath(".//*[@id='docsNavRight']//a");
	By anonymiseButton = By.id("btn_anonymiseUser_id");
	By archiveButton = By.id("btn_removeUser_id");
	By confirmUserPopup = By.xpath(".//*[@id='collaborateCustomMessageModal' and contains(@style,'display: block')]");
	By collaborateMessageOkButton = By.id("collaborateMessageOkButton");
	By anonymiseCancelButton = By.id("collaborateMessageCancelButton");
	By searchText = By.id("searchText");
	By anonymiseOkButton = By.id("collaborateMessageOkButton");

	public SystemAdminSelfRegisteredUsersWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author ankit.motaval
	 *         Click on Filter Button
	 * @created on 09/05/2018
	 */
	public void clickFilterButton()
	{
		WebElement filterButtonElement = findVisibleElement(filterButton, Speed.slow);
		filterButtonElement.click();
	}

	/**
	 * @author ankit.motaval
	 *         Select User status form filter
	 * @param option
	 * @created on 09/05/2018
	 */
	public void selectUserStatusFromFilter(String option)
	{
		WebElement checkboxElement = findVisibleElement(By.xpath("// label[normalize-space(.)='" + option + "']//preceding::input[@type='checkbox'][1]"), Speed.slow);
		checkboxElement.click();
	}

	/**
	 * @author ankit.motaval
	 *         Click on make Anonymise Button
	 * @created on 09/05/2018
	 */
	public void clickAnonymiseButton()
	{
		WebElement checkboxElement = findVisibleElement(anonymiseButton, Speed.slow);
		checkboxElement.click();
	}

	/**
	 * @author ankit.motaval
	 *         verify Make Anonymise Button
	 * @created on 09/05/2018
	 */
	public boolean verifyMakeAnonymiseButton()
	{
		return isDisplayed(anonymiseButton);
	}

	/**
	 * @author ankit.motaval
	 *         Select user from Self registered User
	 * @param UserEmail
	 * @created on 09/05/2018
	 */
	public void selectUser(String UserEmail)
	{
		By userChkBox = By.xpath("//td[normalize-space(.)='" + UserEmail.trim() + "']//preceding::input[@id='chkUserID'][1]");
		setSelection(userChkBox, true);
	}

	/**
	 * @author ankit.motaval
	 *         Click on Archive Button
	 * @created on 09/05/2018
	 */
	public void clickOnArchiveButton()
	{
		WebElement checkboxElement = findVisibleElement(archiveButton, Speed.slow);
		checkboxElement.click();
		if (isDisplayed(confirmUserPopup, Speed.slow))
		{
			WebElement acceptPopUp = findVisibleElement(collaborateMessageOkButton, Speed.slow);
			acceptPopUp.click();
		}
	}

	/**
	 * @author ankit.motaval
	 *         Click on Cancel Button of Make Anonymous Model
	 * @created on 09/05/2018
	 */
	public void clickOnCancelButtonOfAnonymiseModel()
	{
		findVisibleElement(anonymiseCancelButton, Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 *         Verify email from Self registered User
	 * @created on 09/05/2018
	 */
	public boolean verifyEmailID(String EmailId)
	{
		return isDisplayed(By.xpath("//td[normalize-space(.)='" + EmailId.trim() + "']"));
	}

	/**
	 * @author ankit.motaval
	 *         Search user
	 * @created on 09/05/2018
	 */
	public void searchUser(String EmailId)
	{
		WebElement checkboxElement = findVisibleElement(searchText, Speed.slow);
		checkboxElement.sendKeys(EmailId);
	}

	/**
	 * @author ankit.motaval
	 *         Click on Anonymise Button of Make Anonymous Model
	 * @created on 09/05/2018
	 */
	public void clickOnAnonymiseButtonOfAnonymiseModel()
	{
		findVisibleElement(anonymiseOkButton, Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 *         Clear search box
	 * @created on 09/05/2018
	 */
	public void clearSearchBox()
	{
		WebElement checkboxElement = findVisibleElement(searchText, Speed.slow);
		checkboxElement.clear();
	}
}
