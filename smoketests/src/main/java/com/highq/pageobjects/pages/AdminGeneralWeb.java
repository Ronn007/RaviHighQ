package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminGeneralPage;

/**
 * @author nidhi.shah
 */
public class AdminGeneralWeb extends BannerPageWeb implements AdminGeneralPage
{
	public AdminGeneralWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By siteNameInput = By.id("siteName");
	By siteDescriptionInput = By.id("description");
	By siteAdminNotesInput = By.id("internalNotes");
	By siteClientNumberInput = By.id("clientNo");
	By siteMatterNumberInput = By.id("matterNumber");
	By siteLogoInput = By.id("logoFileUpload");
	By saveLink = By.xpath("(//*[text()='" + SiteAdminLabels.SITEADMIN_GENERAL_SAVE + "'])[last()]");
	By startDateInput = By.id("siteStartDate");
	By endDateInput = By.id("siteEndDate");

	// ***************************************** Category *****************************************

	By siteCategoryInput = By.id("category");
	By addNewCategoryLink = By.id("addNewList_link");
	By addSiteCategoryDialogue = By.id("siteCategoryDiv");
	By addSiteCategoryInput = By.id("createSiteModal_addNewSiteCategory");
	By addSiteCategoryCloseLink = By.xpath(".//*[@id='siteCategoryDiv']//*[@class='close']");
	By addSiteCategorySaveLink = By.id("addNewCategory_ok");
	By addSiteCategoryCancelLink = By.id("addNewCategory_cancel");

	// ***************************************** Type *****************************************

	By siteTypeMatterInput = By.id("matterSiteType");
	By siteTypeServiceInput = By.id("serviceSiteType");

	// **************************************** Status **********************************
	By statusDropDown = By.xpath("//*[@data-id='status']");
	By statusComboBox = By.xpath("//*[@data-id='status']/following-sibling::*[@role='combobox']");
	String statusDropDown_StatusList = "//*[@data-id='status']/following-sibling::*[@role='combobox']//li";

	// ************************* Catogories *****************************
	By categoriesDropDown = By.xpath("//*[@data-id='siteAdmin_general_selectedSiteCategoryID']");
	By categoriesComboBox = By.xpath("//*[@data-id='siteAdmin_general_selectedSiteCategoryID']/following-sibling::*[@role='combobox']");
	String categoriesDropDown_CategoryList = "//*[@data-id='siteAdmin_general_selectedSiteCategoryID']/following-sibling::*[@role='combobox']//li";

	// *********************** Contact us ***************************
	By contactUsDropDown = By.xpath("//*[@data-id='contactUs']");
	By contactUsComboBox = By.xpath("//*[@data-id='contactUs']/following-sibling::*[@role='combobox']");
	String contactUsDropDown_ContactUsList = "//*[@data-id='contactUs']/following-sibling::*[@role='combobox']//li";
	By contactUsURLInput = By.id("contactUsURL");

	// ****************** Site Owner *****************************
	By siteOwner_FirstNameInput = By.id("siteOwnerFirstName");
	By siteOwner_LastNameInput = By.id("siteOwnerLastName");
	By siteOwner_EmailAddressInput = By.id("siteOwnerEmail");
	By siteOwner_RemoveThisUserLink = By.id("removeUserId");
	By siteOwner_AddOtherUserLink = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_GENERAL_SITEOWNER_ADDANOTHERUSER + "']");
	By siteOwner_NewOwnerInput = By.id("newOwnerList-tokenfield");
	By thirdPartyModal = By.id("THIRD_PARTY_OFF_MODAL_ID_BODY");
	By OKButton = By.id("THIRD_PARTY_OFF_MODAL_ID_ok");

	By docRevokeModel = By.id("THIRD_PARTY_OFF_MODAL_ID_BODY");

	By docRevokeModelOkBtn = By.id("THIRD_PARTY_OFF_MODAL_ID_ok");
	By contactUsEmailAddres = By.xpath(".//*[@id='siteadmin_contactus_selected_email']//div[@class='token']/span");

	By contactUsSearchIcon = By.id("siteadmin_contactus_searchicon");
	By cancelcontactUsSearchAdminUser = By.id("siteAdmin_module_setNotification_modal_cancelLinkBtnID");

	By waitforAdminUser = By.xpath(".//*[@id='siteAdmin_module_setNotification_modal' and contains(@class,'fade in')]");

	@Override
	public void setSiteName(String siteName)
	{
		WebElement siteNameEle = findClickableElement(siteNameInput);
		siteNameEle.clear();
		siteNameEle.sendKeys(siteName);
	}

	@Override
	public void setSiteDescription(String siteDescription)
	{
		WebElement siteDescriptionEle = findClickableElement(siteDescriptionInput);
		siteDescriptionEle.clear();
		siteDescriptionEle.sendKeys(siteDescription);
	}

	@Override
	public void setSiteAdminNotes(String siteAdminNotes)
	{
		WebElement siteAdminNotesEle = findClickableElement(siteAdminNotesInput);
		siteAdminNotesEle.clear();
		siteAdminNotesEle.sendKeys(siteAdminNotes);
	}

	@Override
	public void setSiteCategory(String category)
	{
		Select siteCategoryEle = new Select(findClickableElement(siteCategoryInput));
		siteCategoryEle.selectByVisibleText(category);
	}

	@Override
	public void setClientNumber(String clientNumber)
	{
		WebElement siteClientNumberEle = findClickableElement(siteClientNumberInput);
		siteClientNumberEle.clear();
		siteClientNumberEle.sendKeys(clientNumber);
	}

	@Override
	public void setMatterNumber(String matterNumber)
	{
		WebElement siteMatterNumberEle = findClickableElement(siteMatterNumberInput);
		siteMatterNumberEle.clear();
		siteMatterNumberEle.sendKeys(matterNumber);
	}

	@Override
	public void uploadSiteLogo(String filePath)
	{
		WebElement siteLogoEle = findPresentElement(siteLogoInput);
		siteLogoEle.sendKeys(filePath);
	}

	@Override
	public void clickOnSave()
	{
		WebElement saveEle = findClickableElement(saveLink);
		saveEle.click();
	}

	@Override
	public void setStartDate(String startDate)
	{
		WebElement startDateEle = findVisibleElement(startDateInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + startDate + "'", startDateEle);
		startDateEle.sendKeys(Keys.TAB);
	}

	@Override
	public void setEndDate(String endDate)
	{
		WebElement endDateEle = findClickableElement(endDateInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + endDate + "'", endDateEle);
		endDateEle.sendKeys(Keys.TAB);
	}

	// ***************************************** Category *****************************************

	@Override
	public void clickOnAddNewCategory()
	{
		WebElement addNewCategoryEle = findClickableElement(addNewCategoryLink);
		addNewCategoryEle.click();
	}

	@Override
	public void clickSaveOnAddSiteCategory()
	{
		WebElement addSiteCategorySaveEle = findClickableElement(addSiteCategorySaveLink);
		addSiteCategorySaveEle.click();
	}

	@Override
	public void clickCancelOnAddSiteCategory()
	{
		WebElement addSiteCategoryCancelEle = findClickableElement(addSiteCategoryCancelLink);
		addSiteCategoryCancelEle.click();
	}

	@Override
	public void addNewCategory(String category)
	{
		if (!isDisplayed(addSiteCategoryInput))
		{
			clickOnAddNewCategory();
		}
		if (isDisplayed(addSiteCategoryInput))
		{
			WebElement addSiteCategoryEle = findClickableElement(addSiteCategoryInput);
			addSiteCategoryEle.clear();
			addSiteCategoryEle.sendKeys(category);
			clickSaveOnAddSiteCategory();
		}
	}

	// ***************************************** Type *****************************************

	@Override
	public void selectSiteType(String siteType)
	{
		switch (siteType.toLowerCase())
		{
			case "matter":
				WebElement siteTypeMatterEle = findClickableElement(siteTypeMatterInput);
				siteTypeMatterEle.click();
				break;

			case "service":
				WebElement siteTypeServiceEle = findClickableElement(siteTypeServiceInput);
				siteTypeServiceEle.click();
				break;

			default:
				System.out.println("Enter valid site type");
				break;
		}
	}

	public void selectStatus(String status)
	{
		findVisibleElement(statusDropDown).click();
		findPresentElement(statusComboBox);
		WebElement elem = findVisibleElement(By.xpath(statusDropDown_StatusList + "//*[normalize-space(text())='" + status.trim() + "']"));
		elem.click();

		if (isDisplayed(thirdPartyModal))
		{
			WebElement ok = findVisibleElement(OKButton);
			ok.click();
		}
	}

	public void selectCategories(String... categories)
	{
		findVisibleElement(categoriesDropDown).click();
		findPresentElement(categoriesComboBox);
		for (int i = 0; i < categories.length; i++)
		{
			WebElement elem = findVisibleElement(By.xpath(categoriesDropDown_CategoryList + "//*[normalize-space(text())='" + categories[i].trim() + "']"));
			elem.click();
		}
		findVisibleElement(categoriesDropDown).click();
	}

	public void selectContactUsType(String type)
	{
		findVisibleElement(contactUsDropDown).click();
		findPresentElement(contactUsComboBox);
		WebElement elem = findVisibleElement(By.xpath(contactUsDropDown_ContactUsList + "//*[normalize-space(text())='" + type.trim() + "']"));
		elem.click();
	}

	public void setContactUsURL(String url)
	{
		WebElement elem = findVisibleElement(contactUsURLInput);
		elem.clear();
		elem.sendKeys(url.trim());
	}

	public void setSiteOwner(String ownerName)
	{
		WebElement elem = findVisibleElement(siteOwner_NewOwnerInput);
		elem.clear();
		elem.sendKeys(ownerName.trim());
	}

	public void clickAddOtherUser()
	{
		findVisibleElement(siteOwner_AddOtherUserLink).click();
	}

	public void setSiteOwnerFirstName(String firstName)
	{
		WebElement elem = findVisibleElement(siteOwner_FirstNameInput);
		elem.clear();
		elem.sendKeys(firstName.trim());
	}

	public void setSiteOwnerLastName(String lastName)
	{
		WebElement elem = findVisibleElement(siteOwner_LastNameInput);
		elem.clear();
		elem.sendKeys(lastName.trim());
	}

	public void clickOnContactUsSearchIcon()
	{
		if (isDisplayed(contactUsSearchIcon))
		{
			findVisibleElement(contactUsSearchIcon, Speed.slow).click();
		}
	}

	public boolean verifyAddButonAddUser()
	{
		return isDisplayed(By.xpath(".//*[@id='siteAdmin_module_setNotification_modal_setNotificationBtnID' and @class='btn btn-default']"));
	}

	public void clickOnAdminUser()
	{
		findVisibleElement(By.xpath(".//*[@id='siteAdmin_module_setNotification_modal_setNotificationBtnID']"), Speed.slow).click();
	}

	public void clickOnFooterContactUslink()
	{
		findVisibleElement(By.xpath("//span[@class='fooContactus']//a[normalize-space(.)='Contact us']"), Speed.slow).click();
	}

	public void clickOncancelContactUsAdminUser()
	{

		findVisibleElement(cancelcontactUsSearchAdminUser, Speed.slow).click();

	}

	public void selectAllAdminUserContactUs()
	{
		findVisibleElement(By.xpath(".//*[@id='frmSetNotificationDialogPageFormID']//span//a[text()='Select all']"), Speed.slow).click();
	}

	public void clearAllAdminUserContactUs()
	{
		findVisibleElement(By.xpath(".//*[@id='frmSetNotificationDialogPageFormID']//span//a[text()='Clear all']"), Speed.slow).click();
	}

	public void clearAllContactUs()
	{
		int total = driver.findElements(By.xpath(".//*[@id='siteadmin_contactus_selected_email']//div[@class='token']/span")).size();
		for (int i = 1; i <= total; i++)
		{
			findVisibleElement(By.xpath("//*[@class='form-group']//*[normalize-space(text())='Contact us']")).click();
			String emailID = findVisibleElement(By.xpath("(.//*[@id='siteadmin_contactus_selected_email']//div[@class='token']/span)[1]"), Speed.slow).getText().trim();
			findVisibleElement(By.xpath(".//*[@class='tokenfield tokenScroll form-control']//span[normalize-space(text())='" + emailID + "']//preceding-sibling::a")).click();
		}
	}

	public void clearAllSiteOwner()
	{
		moveToElement(By.xpath("//div[@class='tokenfield tokenScroll form-control disabled']//div[@class='token']//span"));
		scrollToBottom();
		int total = driver.findElements(By.xpath("//div[@class='tokenfield tokenScroll form-control disabled']//div[@class='token']//span")).size();
		for (int i = 1; i <= total; i++)
		{
			findVisibleElement(By.xpath(".//*[@id='frmAdminGeneral']/div[@class='form-group hideContent']//label[@class='col-sm-2 control-label']")).click();
			findVisibleElement(By.xpath("//div[@class='tokenfield tokenScroll form-control disabled']//div[@class='token']//span"), Speed.slow).getText().trim();
			findVisibleElement(By.xpath("//div[@class='relativeDiv']//a")).click();
		}
	}

	public boolean verifyUsersinContachUsModel(String userName, String email)
	{
		findVisibleElement(waitforAdminUser, Speed.slow);
		boolean userNameMsg = isDisplayed(By.xpath(".//*[@class='listItems']//a[contains(@class,'peopleName')]//strong[text()='" + userName + "']"));
		boolean emailMsg = isDisplayed(By.xpath(".//*[@class='listItems']//a[@class='linkGrey' and text()='" + email + "']"));
		return userNameMsg && emailMsg;
	}

	public boolean verifyemailURL_contactusLink(String email)
	{
		String getText = findVisibleElement(contactUsEmailAddres, Speed.slow).getText();
		return getText.trim().equals(email.trim());
	}

	public boolean verifyemailURL_contactusLinkUserName(String username)
	{
		String getText = findVisibleElement(By.xpath(".//*[@id='siteadmin_contactus_selected_email']//div[@class='token']/span[text()='" + username + "']"), Speed.slow).getText();
		return getText.trim().equals(username.trim());
	}

	public boolean verifySiteOwner(String username)
	{
		String getText = findVisibleElement(By.xpath(".//*[@id='newsiteOwnerListId']//div[@class='token']//span"), Speed.slow).getText();
		return getText.trim().equals(username.trim());
	}

	public void setSiteOwnerEmailAddress(String email)
	{
		WebElement elem = findVisibleElement(siteOwner_EmailAddressInput);
		elem.clear();
		elem.sendKeys(email.trim());
	}

	public void setContactUsEmailAddress(String email)
	{
		WebElement textBox = findVisibleElement(By.xpath(".//*[@id='contactUsAdminListAutoSuggest-tokenfield']"), Speed.slow);
		textBox.clear();
		textBox.sendKeys(email.trim());
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		action.release().perform();
	}

	@Override
	public void clickonOkonDocRevokePopUp()
	{
		WebElement elem = findVisibleElement(docRevokeModel, 5);
		if (elem != null)
		{
			findVisibleElement(docRevokeModelOkBtn).click();
		}
	}

	public boolean verifyContactUsLinkFooter()
	{
		return isDisplayed(By.xpath("//span[@class='fooContactus']//a[normalize-space(.)='Contact us']"));
	}

	// @Override
	// public void setSDate(String date)
	// {
	// // .//*[@id='siteStartDate']//following::*[@class='datepickerCal'][1]
	// WebElement calendar = findClickableElement(By.xpath(".//*[@id='siteStartDate']//following::*[@class='datepickerCal'][1]"));
	// calendar.click();
	// setDate(date);
	// }
	//
	// By calenderDiv = By.id("calendarDiv");
	//
	// By calenderYear = By.id("calendar_year_txt");
	// By calenderMonth = By.id("calendar_month_txt");
	//
	// By monthDropDown = By.id("monthDropDown");
	// By yearDropDown = By.xpath(".//*[@id='yearDropDown']//div");
	//
	// public void setDate(String date)
	// {
	// if (isDisplayed(calenderDiv))
	// {
	// String[] dates = date.split(" ");
	// int year = Integer.parseInt(dates[2]);
	// WebElement calendarYearEle = findClickableElement(calenderYear);
	// if (Integer.parseInt(calendarYearEle.getText()) != year)
	// {
	// calendarYearEle.click();
	// if (isDisplayed(yearDropDown))
	// {
	// List<WebElement> years = driver.findElements(yearDropDown);
	// int size = years.size();
	// List<WebElement> yearsRange;
	// while (year < Integer.parseInt(years.get(1).getText()) || year > Integer.parseInt(years.get(size - 2).getText()))
	// {
	// if (year < Integer.parseInt(years.get(1).getText()))
	// {
	// //moveToElement(years.get(0));
	// }
	// else
	// {
	// //moveToElement(years.get(size - 1));
	// }
	// years = driver.findElements(yearDropDown);
	// }
	// yearsRange = driver.findElements(yearDropDown);
	// for (int i = 1; i < yearsRange.size() - 2; i++)
	// {
	// if (Integer.parseInt(yearsRange.get(i).getText().trim()) == year)
	// {
	// yearsRange.get(i).click();
	// }
	// }
	// }
	// }
	// }
	// }
}
