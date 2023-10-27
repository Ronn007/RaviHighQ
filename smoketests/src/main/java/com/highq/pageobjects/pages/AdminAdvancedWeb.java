package com.highq.pageobjects.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminAdvancedPage;

public class AdminAdvancedWeb extends AdminPageWeb implements AdminAdvancedPage
{

	By statusDropDown = By.id("status");
	By displayUserAvatar = By.xpath("//*[@name='site.displayUserAvatars']");
	By useGroupsForPermissioning = By.id("enforceUserGroupsID");
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
	By contactUs_chooseAdminLink = By.xpath("//a[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_ADVANCED_CHOSEADMINS + "']");
	By contactUs_chooseAdminModal = By.id("chooseAdminsModalAdvanced");
	By contactUs_chooseAdmin_selectAllCheckbox = By.xpath("//*[@name='site.contactUsLinkAlertToAll']");

	/********************* Site Owner ************************/
	By siteOwnerDropDown = By.id("siteOwner");
	By siteOwnerFirstName = By.id("siteOwnerFirstName");
	By siteOwnerLastName = By.id("siteOwnerLastName");
	By siteOwnerEmail = By.id("siteOwnerEmail");

	By enableIPAddressAccessList = By.id("ipRestrictionEnable");
	By enterIPAddress_textArea = By.id("availableIP");

	By authenticationRequiredForRSS = By.id("rssSecurity");
	By microblogging = By.id("site.microBlogging");

	By advanced_SaveButton = By.xpath("(//*[contains(text(),'" + SiteAdminLabels.SITEADMIN_ADVANCED_SAVE + "')])[1]");

	By confirmationMessageTitle = By.id("customMessageModalTitle");

	/*********************** Footer *********************/
	By footer_OKbutton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_ADVANCED_MODEL_OK + "'])[last()]");
	By footer_Cancelbutton = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_ADVANCED_MODEL_CANCEL + "'])[last()]");

	Actions build;
	protected JavascriptExecutor jse;

	public AdminAdvancedWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public void selectStatus(String status)
	{
		WebElement statusDrpDwn = findVisibleElement(statusDropDown);
		Select select = new Select(statusDrpDwn);
		select.selectByVisibleText(status);
	}

	public boolean verifySelectedStatus(String status)
	{
		WebElement statusDrpDwn = findVisibleElement(statusDropDown);
		Select select = new Select(statusDrpDwn);
		String selectedOption = select.getFirstSelectedOption().getText().trim();
		return selectedOption.equals(status.trim());
	}

	public void setDisplayUserAvatars(boolean permission)
	{
		selectCheckbox(displayUserAvatar, permission);
	}

	public void setUseGroupForPermissioning(boolean permission) throws InterruptedException
	{

		setSelection(useGroupsForPermissioning, permission);

		if (isAlertPresent())
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	public void setPriceSensitiveMatter(boolean permission)
	{
		selectCheckbox(priceSensitiveMatter, permission);
	}

	public void enableAdvancedSiteAdminOption(boolean permission)
	{
		selectCheckbox(enableAdvancedSite, permission);
	}

	public void setDisableRecentActivity(boolean permission)
	{
		selectCheckbox(disableRecentActivity, permission);
	}

	public void setRemoveSiteNameFromHeader(boolean permission)
	{
		selectCheckbox(removeSiteNameFromHeader, permission);
	}

	public void setEnableTwoFactorAuthentication(boolean permission)
	{
		selectCheckbox(enableTwoFactorAuthentication, permission);
		if (isDisplayed(confirmationMessageTitle))
			findVisibleElement(footer_OKbutton).click();

	}

	public void setPasswordEnabled(boolean permission)
	{
		selectCheckbox(passwordEnabled, permission);
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

	public void sendContactUsEmailOrURL(String emailOrURL)
	{
		WebElement radio = findVisibleElement(contactUs_radio1);
		radio.click();
		WebElement contactUsInput = findVisibleElement(contactUs_inputBox);
		contactUsInput.clear();
		contactUsInput.sendKeys(emailOrURL);
	}

	public void contactUsChooseAdmins(String adminName)
	{
		findVisibleElement(contactUs_radio2).click();
		findVisibleElement(contactUs_chooseAdminLink).click();
		findPresentElement(contactUs_chooseAdminModal, Speed.slow, 5);
		if (adminName.trim().equalsIgnoreCase("all"))
			selectCheckbox(contactUs_chooseAdmin_selectAllCheckbox, true);
		else
		{
			selectCheckbox(contactUs_chooseAdmin_selectAllCheckbox, false);
			By adminToSelect = By.xpath("//*[@id='chooseAdminsModalAdvanced']//label[normalize-space(.)='" + adminName.trim() + "']//input");
			selectCheckbox(adminToSelect, true);
		}
	}

	public void selectSiteOwner(String siteOwner)
	{
		WebElement siteOwnerDrpDwn = findVisibleElement(siteOwnerDropDown);
		Select select = new Select(siteOwnerDrpDwn);
		select.selectByVisibleText(siteOwner);
	}

	public void sendSiteOwnerFirstName(String firstName)
	{
		WebElement siteOwnerFName = findVisibleElement(siteOwnerFirstName);
		siteOwnerFName.clear();
		siteOwnerFName.sendKeys(firstName);
	}

	public void sendSiteOwnerLastName(String lastName)
	{
		WebElement siteOwnerLName = findVisibleElement(siteOwnerLastName);
		siteOwnerLName.clear();
		siteOwnerLName.sendKeys(lastName);
	}

	public void sendSiteOwnerEmail(String email)
	{
		WebElement siteOwner_Email = findVisibleElement(siteOwnerEmail);
		siteOwner_Email.clear();
		siteOwner_Email.sendKeys(email);
	}

	public void setEnableIPAddressAccessList(boolean permission)
	{
		selectCheckbox(enableIPAddressAccessList, permission);
		Alert alert = driver.switchTo().alert();
		String alertMessage = driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		alert.accept();
	}

	public void sendIPAddressMultipleIP(String commaSeparatedIP)
	{
		WebElement ipAddressInput = findVisibleElement(enterIPAddress_textArea);
		ipAddressInput.clear();
		ipAddressInput.sendKeys(commaSeparatedIP);
	}

	public void setAuthenticationRequiredForRSS(boolean permission)
	{
		selectCheckbox(authenticationRequiredForRSS, permission);
	}

	public void setMicroblogging(boolean permission)
	{
		selectCheckbox(microblogging, permission);
	}

	public void selectCheckbox(By option, boolean check)
	{
		WebElement element = findVisibleElement(option);
		boolean checked = element.isSelected();
		if (checked != check)
			element.click();
	}

	public void saveAdvancedChanges()
	{
		scrollToBottom();
		findClickableElement(advanced_SaveButton).click();
	}

	public boolean verifyBidderSiteEnabled()
	{
		return (isDisplayed(bidderSiteCheckBox));
	}
}
