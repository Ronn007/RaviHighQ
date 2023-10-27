package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminAuditsSiteManagementPage;

/**
 * @author jyoti.raj
 */
public class AdminAuditsSiteManagementWeb extends AdminPageWeb implements AdminAuditsSiteManagementPage
{

	By submitButton = By.xpath(".//*[@id='lstvAudit']//button[normalize-space(text())='Submit']");
	By cancelButton = By.id("actionDetailReportingModalID_actionDetailReportingModalID_close");
	By titleoriginal = By.xpath(".//*[@id='AuditDetail']/div[3]//tr[1]//td[1]");
	By oldParamorigional = By.xpath(".//*[@id='AuditDetail']/div[3]//tr[1]//td[2]");
	By newParamOriginal = By.xpath(".//*[@id='AuditDetail']/div[3]//tr[1]//td[3]");

	By siteUpdateList = By.xpath(".//*[@id='lstvAudit']//tbody//tr[1]");

	public AdminAuditsSiteManagementWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @return
	 */
	public AdminAuditsSiteManagementWeb clickOnSubmit()
	{
		WebElement submit = findVisibleElement(submitButton);
		submit.click();
		return new AdminAuditsSiteManagementWeb(driver);

	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @param position
	 */
	public void clickOnActionNameOnSiteManagementAudit(int position)
	{
		By siteUpdated = By.xpath("//tr[" + position + "]/td[1]/a[normalize-space(text())='Site updated']");
		WebElement action = findVisibleElement(siteUpdated);
		action.click();

	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 * @param title
	 * @param oldParam
	 * @param newParam
	 * @return
	 */

	public boolean verifyActionDetails(String title, String oldParam, String newParam)
	{
		String titleO = findVisibleElement(titleoriginal).getText().trim();
		String oldP = findVisibleElement(oldParamorigional).getText().trim();
		String newP = findVisibleElement(newParamOriginal).getText().trim();
		return ((titleO.equals(title) && oldP.equals(oldParam) && newP.equals(newParam)));
	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 */
	public void clickOnCancelInActionDeTails()
	{
		WebElement cancel = findVisibleElement(cancelButton);
		cancel.click();

	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifySiteUpdateListDisplayed()
	{
		return isDisplayed(siteUpdateList);
	}

}
