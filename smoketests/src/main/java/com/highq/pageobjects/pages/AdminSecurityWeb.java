package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminSecurityPage;

public class AdminSecurityWeb extends AdminPageWeb implements AdminSecurityPage
{

	By statusDropDown = By.id("status");
	By displayUserAvatar = By.xpath("//*[@name='site.displayUserAvatars']");

	/** User groups permissions xpaths ****/

	By useGroupsForPermissioning = By.id("enforceUserGroups");
	By userAlertConfirmationModelOpened = By.xpath("//*[@id='selectUserAlertConfirmationModal' and @class='modal fade in']");
	By userAlertConfirmationModelMessage = By.xpath("//*[@id='selectUserAlertConfirmationModal_BODY']");
	By userAlertConfirmationModelCancel = By.id("selectUserAlertConfirmationModal_cancelUserAlertPermission");
	By userAlertConfirmationModelOk = By.id("selectUserAlertConfirmationModal_saveUserAlertPermission");
	By userAlertConfirmationModelClosed = By.xpath("//*[@id='selectUserAlertConfirmationModal' and @class='modal fade']");

	By bidderSiteCheckBox = By.id("bidderSiteID");
	By priceSensitiveMatter = By.xpath("//*[@name='site.psm']");
	By enableAdvancedSite = By.xpath("//*[@name='site.allowSiteAdministration']");
	By disableRecentActivity = By.xpath("//*[@name='site.disableRecentActivity']");
	By removeSiteNameFromHeader = By.xpath("//*[@name='site.removeSiteNameOnBanner']");
	By enableTwoFactorAuthentication = By.id("passcodeEnableID");
	By passwordEnabled = By.id("passwordEnableID");
	By passwordInput = By.id("sitePasswordID");

	/********************* Contact Us ************************/
	By contactUs_radio1 = By.id("contactUsLinkOptionOne");
	By contactUs_radio2 = By.id("contactUsLinkOptionTwo");
	By contactUs_inputBox = By.id("contactUsLink");
	By contactUs_chooseAdminLink = By.xpath("//a[normalize-space(.)='Choose admins']");
	By contactUs_chooseAdminModal = By.id("chooseAdminsModalAdvanced");
	By contactUs_chooseAdmin_selectAllCheckbox = By.xpath("//*[@name='site.contactUsLinkAlertToAll']");

	/********************* Ip configuration ************************/

	By enableIPAddressRestriction = By.id("ipRestrictionEnable");
	By enterIPAddress_InputBox = By.id("restrictedInlineIP");

	By ipAlertConfirmationModelOpened = By.xpath("//*[@id='enableIPListAlertModal' and @class='modal fade in']");
	By ipAlertConfirmationModelMessage = By.xpath("//*[@id='enableIPListAlertModal_BODY' and contains(text(),'Enabling this option will only allow access to this site from specified IP addresses')]");
	By ipAlertConfirmationModelCancel = By.id("enableIPListAlertModal_cancelEnableIPList");
	By ipAlertConfirmationModelOk = By.id("enableIPListAlertModal_saveEnableIPList");
	By ipAlertConfirmationModelClosed = By.xpath("//*[@id='enableIPListAlertModal' and @class='modal fade']");

	By authenticationRequiredForRSS = By.id("rssSecurity");

	By advanced_SaveButton = By.xpath("(//*[contains(text(),'Save')])[1]");

	By confirmationMessageTitle = By.id("enable2LevelAuthForSiteAlertModal_TITLE");

	/*********************** IP MODEL Footer *********************/
	By footer_OKbutton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_SECURITY_IPCONFIRMATIONMODEL_OK + "'])[last()]");
	By footer_Cancelbutton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_SECURITY_IPCONFIRMATIONMODEL_CANCEL + "'])[last()]");
	By advancedQAPermissionCheckBox = By.id("advancedQAPermission");

	By modal = By.xpath("//*[@class='modal fade in']//*[@class='modal-content']");

	By save = By.id("adminSecuritySaveBtnTop");

	Actions build;
	protected JavascriptExecutor jse;

	public AdminSecurityWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public void setUseGroupForPermissioning(boolean permission) throws InterruptedException
	{

		setSelection(useGroupsForPermissioning, permission);

		if (isDisplayed(userAlertConfirmationModelMessage, Speed.slow))
		{
			findPresentElement(userAlertConfirmationModelOpened, Speed.slow);
			findVisibleElement(userAlertConfirmationModelOk).click();
			findPresentElement(userAlertConfirmationModelClosed, Speed.slow);
		}
	}

	public void setEnableTwoFactorAuthentication(boolean permission)
	{
		setSelection(enableTwoFactorAuthentication, permission);
		if (isDisplayed(confirmationMessageTitle, Speed.slow))
			findVisibleElement(footer_OKbutton).click();

	}

	public void setPasswordEnabled(boolean permission)
	{
		setSelection(passwordEnabled, permission);
	}

	public void sendPassword(String password)
	{
		WebElement pwdInput = findVisibleElement(passwordInput);
		if (pwdInput.isEnabled())
		{
			pwdInput.clear();
			pwdInput.sendKeys(password);
		}
		else
			System.err.println("Please check [Password enabled] checkbox");
	}

	public void enableIPAddressRestrictions(boolean permission)
	{
		setSelection(enableIPAddressRestriction, permission);
		acceptIPAddressRestrictionModel();
	}

	public void acceptIPAddressRestrictionModel()
	{
		findPresentElement(ipAlertConfirmationModelOpened, Speed.slow, 10);
		if (isDisplayed(ipAlertConfirmationModelMessage, Speed.slow))
		{
			findVisibleElement(ipAlertConfirmationModelOk).click();
		}
		findPresentElement(ipAlertConfirmationModelClosed, Speed.slow, 10);

	}

	public void cancelIPAddressRestrictionModel()
	{
		findPresentElement(ipAlertConfirmationModelOpened, Speed.slow, 10);
		if (isDisplayed(ipAlertConfirmationModelMessage, Speed.slow))
		{
			findVisibleElement(ipAlertConfirmationModelCancel).click();
		}
		findPresentElement(ipAlertConfirmationModelClosed, Speed.slow, 10);

	}

	public void setrestrictedIps(String commaSeparatedIP)
	{
		WebElement ipAddressInput = findVisibleElement(enterIPAddress_InputBox);
		ipAddressInput.clear();
		ipAddressInput.sendKeys(commaSeparatedIP);
	}

	public void setAuthenticationRequiredForRSS(boolean permission)
	{
		setSelection(authenticationRequiredForRSS, permission);
	}

	public void saveAdvancedChanges()
	{
		findVisibleElement(advanced_SaveButton, Speed.slow).click();
		waitTillGlobalMessageDissappears();
	}

	public boolean verifyBidderSiteEnabled()
	{
		return (isDisplayed(bidderSiteCheckBox));
	}

	/**
	 * @author vivek.mishra
	 *         To set the AdvanceQa option
	 * @created on 20/04/2018
	 */
	public void setAdvanceQAPermissions(boolean permission)
	{
		if (permission)
		{
			setBidderSitePermission(true);
			WebElement advanceQA = findVisibleElement(advancedQAPermissionCheckBox, Speed.slow);
			if (!advanceQA.isSelected())
			{
				setSelection(advancedQAPermissionCheckBox, permission);
				verifyModal();
				clickOnModalButton("OK");
			}
		}
	}

	public boolean verifyAdvanceQAPermission()
	{
		return (isDisplayed(advancedQAPermissionCheckBox));
	}

	/**
	 * @author vivek.mishra
	 *         To set the biider site option
	 * @created on 20/04/2018
	 */
	public void setBidderSitePermission(boolean permission)
	{
		setSelection(bidderSiteCheckBox, permission);
	}

	public void enableAdvancedSiteAdminOption(boolean permission)
	{
		setSelection(enableAdvancedSite, permission);
	}

	/**
	 * @author vivek.mishra
	 * @return the modal availability
	 * @created on 20/04/2018
	 */
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return (isDisplayed(modal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param buttonText to be clicked
	 * @created on 20/04/2018
	 */
	public void clickOnModalButton(String buttonText)
	{
		findVisibleElement(By.xpath("//*[@class='modal fade in']//*[@class='modal-content']//*[normalize-space(text())='" + buttonText.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 *         Click on save button
	 * @creation date 16/04/2018
	 */
	public void clickOnSave()
	{

		WebElement saveEle = findVisibleElement(save, Speed.slow);
		saveEle.click();
	}

}
