package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.EventCategoryData;
import com.highq.pagedata.EventSubscriptionData;
import com.highq.pageobjects.base.AdminEventPage;

/**
 * @author nidhi.shah
 */
public class AdminEventWeb extends BannerPageWeb implements AdminEventPage
{
	// ***************************************** Events Options ************************************************

	public AdminEventWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By saveButton = By.id("adminEventSaveBtnBottom");// By.xpath(".//*[@id='frmAdminEvent']//*[text()='Save']");
	By timeZonesDropDown = By.id("timeZones");
	By defaultTimeDropDown = By.id("cmbSiteEventView");
	By weekStartDropDown = By.id("weekStartDayID");

	// ***************************************** Import and subscribe ************************************************

	// Import

	By importIcsLink = By.id("siteAdmin_event_importButtonID");
	By icsFileInput = By.id("fileUploadID");
	By importIcsFileCancelLink = By.xpath(".//*[@id='importICSSaveCancelBtn']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_IMPORT_CANCEL + "']");
	By importIcsFileSaveLink = By.xpath(".//*[@id='importICSSaveCancelBtn']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_IMPORT_SAVE + "']");
	By importIcsFileCloseLink = By.xpath(".//*[@id='myModal_setImportIcsModalEvent']//*[@class='close']");
	By importIcsFileImportButton = By.id("importICSEventModalID_saveImportIcsEvent");

	// Subscribe

	By subscribeLink = By.id("siteAdmin_event_subscribeButtonID");
	By subscribeCalendarNameInput = By.id("subscribeModal_feedName");
	By subscribeCalendarURLInput = By.id("subscribeModal_feedURL");
	By subscribeAutoUpdateInput = By.id("subscribeModal_feedAutoUpdate");
	By subscribeRefreshInput = By.id("subscribeModal_feedRefresh");
	By subscribeCancelLink = By.xpath(".//*[@id='subscribFeedForm']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SUBSCRIBE_CANCEL + "']");
	By subscribeSaveLink = By.xpath(".//*[@id='subscribFeedForm']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SUBSCRIBE_SAVE + "']");
	By subscribeCloseLink = By.xpath(".//*[@id='feedSubscribModal']//*[@class='close']");
	By subscribe_SubscribeButton = By.id("subscribeEventModalID_saveSubscribeEvent");

	By subscribe_CategoryDropDown = By.xpath("//*[@data-id='subscribeModal_categoryID']");
	By subscribe_CategoryComboBox = By.xpath("(//*[@class='dropdown-menu open'])[last()]");
	String subscribeCategoryList = "(//*[@class='dropdown-menu open'])[last()]//li";

	By subscribe_SyncEventsDropDown = By.xpath("//*[@data-id='subscribeModal_syncEvents']");

	// Category

	By addCategoryLink = By.id("siteadmin_event_category_add_Id");
	By addCategoryInput = By.id("addCategoryValue");
	By backgroundColorLink = By.id("activeColorID");
	By backgroundColorList = By.id("customColorLI");
	By backgroundColorClass = By.xpath(".//*[@class='btn-group pull-left open']");
	By addCategoryDialog = By.id("frmEventAddEditDialogPage");
	By newCategoryAddButton = By.id("siteAdmin_event_addCategories_modal_addEventCategoriesBtnID");
	By newCategoryCancelButton = By.xpath(".//*[@id='frmEventAddEditDialogPage']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_NEWCATEGORY_CANCEL + "']");
	By newCategorySaveButton = By.id("siteAdmin_event_addCategories_modal_addEventCategoriesBtnID");
	By addCategoryCustomColorLink = By.xpath("(.//*[@class='btn-group pull-left open']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_ADDCATEGORY_CUSTOMCOLOR + "'])[last()]");
	By addCategoryColorCodeInput = By.id("categoryColorCodeID");
	By addCategoryDarkTextInput = By.id("darkTextID");
	By addCategoryLightTextInput = By.id("lightTextID");
	By defaultPermissionRadioButton = By.id("permissionTypeDefault");
	By restrictedPermissionRadioButton = By.id("permissionTypeRestricted");
	By categoryPermissionSaveLink = By.xpath(".//*[@id='catgegoryPermission']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_SAVE + "']");
	By categoryRemoveLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_REMOVE + "']");
	By categoryCustomColorDropDown = By.id("customselectOptionID");
	By categoryEditLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_EDIT + "']");
	By categorySetPermissionLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='+SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_SETPERMISSION+']");

	// *************************************** Set Notifications ***********************************************

	By setNotificationsLink = By.xpath("//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION + "']");// By.xpath(".//*[@id='frmAdminEvent']//*[text()='Set notifications']");
	By notificationCheckBox = By.xpath(".//input[@name='site.eventApprovalAlertToAll']");
	By setNotificationSaveButton = By.xpath(".//*[@id='frmSetNotificationDialogPage']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_SAVE + "']");
	By setNotificationCancelButton = By.xpath(".//*[@id='frmSetNotificationDialogPage']//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_CANCEL + "']");
	By setNotificationCloseLink = By.xpath(".//*[@id='setNotificationModalBlog']//*[@class='close']");
	By notificationModal = By.id("siteAdmin_module_setNotification_modal");
	By notification_SelectAllLink = By.xpath("//*[@id='siteAdmin_module_setNotification_modal']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_SELECTALL + "']");
	By notification_ClearAllLink = By.xpath("//*[@id='siteAdmin_module_setNotification_modal']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_CLEARALL + "']");

	/** Time zone */
	By timeZoneDropDown = By.xpath("//*[@data-id='siteAdmin_event_cmbTimeZones']");
	By timeZoneComboBox = By.xpath("//*[@data-id='siteAdmin_event_cmbTimeZones']/following-sibling::*[@role='combobox']");
	String timeZoneList = "//*[@data-id='siteAdmin_event_cmbTimeZones']/following-sibling::*[@role='combobox']//li";

	/** Default view */
	By defaultViewDropDown = By.xpath("//*[@data-id='siteAdmin_event_cmbSiteEventView']");
	By defaultViewComboBox = By.xpath("//*[@data-id='siteAdmin_event_cmbSiteEventView']/following-sibling::*[@role='combobox']");
	String defaultViewList = "//*[@data-id='siteAdmin_event_cmbSiteEventView']/following-sibling::*[@role='combobox']//li";

	/** Week starts on */
	By weekStartsOnDropDown = By.xpath("//*[@data-id='siteAdmin_event_cmbWeekStartDayID']");
	By weekStartsOnComboBox = By.xpath("//*[@data-id='siteAdmin_event_cmbWeekStartDayID']/following-sibling::*[@role='combobox']");
	String weekStartsOnList = "//*[@data-id='siteAdmin_event_cmbWeekStartDayID']/following-sibling::*[@role='combobox']//li";

	By lastOpenedModal = By.xpath("(//*[@class='modal-content'])[last()]");

	By addCategoryModal = By.id("siteAdmin_event_addCategories_modal");

	By adminEventsPage = By.id("frmAdminEvent");

	By enableTasksCategory = By.id("siteAdmin_event_enableTaskCategoryID");

	By importICSModal = By.id("importICSEventModalID");

	By importICSFileProgressBar = By.xpath("(//*[@id='progressbarPercentageTextID'])[last()]");

	By importResultMessage = By.id("importResultMessage");

	By closeButtonInImportICSModal = By.id("importICSEventModalID_closeImportIcsEvent");

	By subscribeModal = By.id("subscribeEventModalID");

	By categoryListTable = By.xpath("//*[@id='categoryListTable']//*[@class='list-group']");

	@Override
	public void clickOnSetNotificatons()
	{
		WebElement setNotificationsEle = findClickableElement(setNotificationsLink);
		setNotificationsEle.click();
	}

	@Override
	public void clickOnSave()
	{
		WebElement saveButtonEle = findClickableElement(saveButton);
		saveButtonEle.click();
	}

	@Override
	public void clickSaveOnImportIcsFile()
	{
		WebElement importIcsFileSaveEle = findClickableElement(importIcsFileSaveLink);
		importIcsFileSaveEle.click();
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 06/02/2018
	 */
	public void setCalendarName(String calendarName)
	{
		WebElement subscribeCalendarNameEle = findVisibleElement(subscribeCalendarNameInput, Speed.slow);
		subscribeCalendarNameEle.sendKeys(calendarName);
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 06/02/2018
	 */
	public void setCalendarUrl(String calendarUrl)
	{
		WebElement subscribeCalendarURLEle = findVisibleElement(subscribeCalendarURLInput, Speed.slow);
		subscribeCalendarURLEle.sendKeys(calendarUrl);
	}

	@Override
	public void clickOnCategoryPermission(String category)
	{
		WebElement categoryNameEle = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*[2]/*[@data-original-title='More actions']"));
		categoryNameEle.click();
		findVisibleElement(categorySetPermissionLink).click();
	}

	@Override
	public void selectAutoUpdate(Boolean value)
	{
		WebElement subscribeAutoUpdateEle = findClickableElement(subscribeAutoUpdateInput);
		Boolean currentStatus = subscribeAutoUpdateEle.isSelected();
		if (currentStatus != value)
		{
			subscribeAutoUpdateEle.click();
		}
	}

	@Override
	public void selectUpdateNow(Boolean value)
	{
		WebElement subscribeRefreshInputEle = findClickableElement(subscribeRefreshInput);
		Boolean currentStatus = subscribeRefreshInputEle.isSelected();
		if (currentStatus != value)
		{
			subscribeRefreshInputEle.click();
		}
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 01/02/2018
	 */
	public void clickOnImportIcsLink()
	{
		WebElement importIcsEle = findVisibleElement(importIcsLink, Speed.slow);
		importIcsEle.click();
	}

	@Override
	public void clickSaveOnSubscribe()
	{
		WebElement subscribeSaveEle = findClickableElement(subscribeSaveLink);
		subscribeSaveEle.click();
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 06/02/2018
	 */
	public void clickSubscribe()
	{
		WebElement subscribeEle = findClickableElement(subscribeLink, Speed.slow);
		subscribeEle.click();
	}

	@Override
	public void clickAddCategory()
	{
		WebElement addCategoryEle = findClickableElement(addCategoryLink);
		addCategoryEle.click();
	}

	@Override
	public void clickOnBackgroundColor()
	{
		WebElement backgroundColorEle = findClickableElement(backgroundColorLink);
		backgroundColorEle.click();
	}

	@Override
	public void clickOnAddInNewCategoryDialog()
	{
		WebElement addEle = findClickableElement(newCategoryAddButton);
		addEle.click();
	}

	@Override
	public void clickOnSaveInEditCategoryDialog()
	{
		WebElement saveEle = findClickableElement(newCategorySaveButton);
		saveEle.click();
	}

	@Override
	public void setEventOptions(String option, boolean value)
	{
		WebElement blogOptionsEle = findClickableElement(By.xpath("//*[text()='" + option.trim() + "']//preceding-sibling::*[@type='checkbox']"));
		Boolean currentStatus = blogOptionsEle.isSelected();
		if (currentStatus != value)
		{
			blogOptionsEle.click();
		}
		clickOnSave();
	}

	@Override
	public void setTimeZone(String timeZone)
	{
		selectOptionFromDropDown(timeZoneDropDown, timeZoneComboBox, timeZoneList, timeZone.trim());
	}

	@Override
	public void setDefaultView(String defaultView)
	{
		selectOptionFromDropDown(defaultViewDropDown, defaultViewComboBox, defaultViewList, defaultView.trim());
	}

	@Override
	public void setWeekStart(String weekDay)
	{
		selectOptionFromDropDown(weekStartsOnDropDown, weekStartsOnComboBox, weekStartsOnList, weekDay.trim());
	}

	@Override
	public void setCategoryName(String categoryName)
	{
		WebElement addCategoryEle = findClickableElement(addCategoryInput);
		addCategoryEle.clear();
		addCategoryEle.sendKeys(categoryName);
	}

	@Override
	public Boolean isBackgroundColorExpanded()
	{
		return isDisplayed((By) findPresentElement(backgroundColorClass));
	}

	/**
	 * @modified by vivek mishra
	 * @modified on 01/02/2018
	 */
	public void importIcsFile(String fileName, String category)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		clickOnImportIcsLink();
		verifyImportICSModal();
		WebElement icsFileEle = findPresentElement(icsFileInput);
		icsFileEle.sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
		WebElement categoryEle = findVisibleElement(By.xpath("//*[text()='" + category.trim() + "']//preceding-sibling::input[1]"), Speed.slow);
		if (!categoryEle.isSelected())
			categoryEle.click();
		clickImportOnImportIcsFile();
	}

	@Override
	public void setSubscription(EventSubscriptionData eventSubscriptionData)
	{
		clickSubscribe();

		setCalendarName(eventSubscriptionData.getCalendarName());
		setCalendarUrl(eventSubscriptionData.getCalendarUrl());
		selectAutoUpdate(eventSubscriptionData.getAutoUpdate());
		selectUpdateNow(eventSubscriptionData.getUpdateNow());
		selectSubscribeCategory(eventSubscriptionData.getCategory());
		selectSubscribeSyncEvents(eventSubscriptionData.getSyncEvent());
		clickSubscribeInSubscribeModal();
	}

	@Override
	public void addCategory(EventCategoryData eventCategoryData)
	{
		clickAddCategory();
		setCategoryName(eventCategoryData.getCategoryName());
		setBackgroundColor(eventCategoryData);
	}

	@Override
	public void setBackgroundColor(EventCategoryData eventCategoryData)
	{
		if (!isBackgroundColorExpanded())
		{
			clickOnBackgroundColor();
		}

		List<WebElement> bgColorList = driver.findElements(backgroundColorList);

		for (WebElement bgColor : bgColorList)
		{
			String[] styleAttributeValues = bgColor.getAttribute("style").split(":");
			if (styleAttributeValues[1].equalsIgnoreCase(eventCategoryData.getBgColor()))
			{
				bgColor.click();
				if (isDisplayed(newCategoryAddButton))
				{
					clickOnAddInNewCategoryDialog();
				}
				else
				{
					clickOnSaveInEditCategoryDialog();
				}
			}
		}

		if (isDisplayed(addCategoryDialog))
		{
			findVisibleElement(categoryCustomColorDropDown).click();
			WebElement addCategoryCustomColorEle = findClickableElement(addCategoryCustomColorLink);
			addCategoryCustomColorEle.click();

			WebElement addCategoryColorCodeEle = findClickableElement(addCategoryColorCodeInput);
			addCategoryColorCodeEle.clear();
			addCategoryColorCodeEle.sendKeys(eventCategoryData.getBgColor());

			if (eventCategoryData.getDarkText())
			{
				WebElement addCategoryDarkTextEle = findClickableElement(addCategoryDarkTextInput);
				addCategoryDarkTextEle.click();
			}
			else
			{
				WebElement addCategoryLightTextEle = findClickableElement(addCategoryLightTextInput);
				addCategoryLightTextEle.click();
			}

			if (isDisplayed(newCategoryAddButton))
			{
				clickOnAddInNewCategoryDialog();
			}
			else
			{
				clickOnSaveInEditCategoryDialog();
			}
		}
	}

	@Override
	public void removeCategory(String category)
	{
		WebElement categoryMoreAction = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*[2]/*[@data-original-title='More actions']"));
		categoryMoreAction.click();
		findVisibleElement(categoryRemoveLink).click();

	}

	@Override
	public void editCategory(String category, EventCategoryData eventCategoryData)
	{
		WebElement categoryNameEle = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*[2]/*[@data-original-title='More actions']"));
		categoryNameEle.click();
		findVisibleElement(categoryEditLink).click();

		addCategory(eventCategoryData);
	}

	@Override
	public void setEventRestrictedPermission(String category, Map<String, Map<String, Boolean>> permissions) throws InterruptedException
	{
		WebElement categoryNameEle = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*[2]/*[@data-original-title='More actions']"));
		categoryNameEle.click();
		if (isDisplayed(categorySetPermissionLink))
		{
			findVisibleElement(categorySetPermissionLink).click();
			setRestrictedPermissions(permissions);
		}
		else
		{
			System.out.println("Site is user based site");
		}
	}

	@Override
	public void setEventDefaultPermissions(String category)
	{
		WebElement categoryNameEle = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*[2]/*[@data-original-title='More actions']"));
		categoryNameEle.click();
		if (isDisplayed(categorySetPermissionLink))
		{
			findVisibleElement(categorySetPermissionLink).click();
			setDefaultPermissions();
		}
		else
		{
			System.out.println("Site is user based site");
		}
	}

	public void clickImportOnImportIcsFile()
	{
		WebElement importIcsFileSaveEle = findClickableElement(importIcsFileImportButton, Speed.slow);
		importIcsFileSaveEle.click();
	}

	public void clickSubscribeInSubscribeModal()
	{
		findVisibleElement(subscribe_SubscribeButton, Speed.slow).click();
	}

	public void selectSubscribeCategory(String categoryName)
	{
		selectOptionFromDropDown(subscribe_CategoryDropDown, subscribe_CategoryComboBox, subscribeCategoryList, categoryName);
	}

	public void selectSubscribeSyncEvents(String syncEvent)
	{
		selectOptionFromDropDown(subscribe_SyncEventsDropDown, subscribe_CategoryComboBox, subscribeCategoryList, syncEvent);
	}

	/**
	 * @author vivek.mishra
	 *         To add a simple category with name param only
	 * @param category as string
	 */
	public void addCategory(String category)
	{
		clickAddCategory();
		verifyAddCategoryModal();
		setCategoryName(category);
		clickOnAddInNewCategoryDialog();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the add category modal
	 */
	public boolean verifyAddCategoryModal()
	{
		return (isDisplayed(addCategoryModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the availiabilty of category
	 * @param category
	 * @return
	 */
	public boolean verifyCategoryInCategoriesList(String category)
	{
		return (isDisplayed(By.xpath("//*[contains(@id,'categoryLabel') and normalize-space(text())='" + category.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the admin events page
	 * @return
	 */
	public boolean verifyAdmineventsPage()
	{
		return (isDisplayed(adminEventsPage, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To enable the enable Tasks category
	 * @creation date 30/01/2018
	 */
	public void enableEnableTasksCategory()
	{
		WebElement element = findVisibleElement(enableTasksCategory, Speed.slow);
		if (!element.isSelected())
			element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 01/02/2018
	 */
	public boolean verifyImportICSModal()
	{
		return (isDisplayed(importICSModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the visibilty of progress bar
	 * @creation date 02/02/2018
	 */
	public boolean verifyImportICSFileProgressBar()
	{
		findPresentElement(importICSFileProgressBar, Speed.slow);
		if (findPresentElement(importICSFileProgressBar, Speed.slow) != null)
			return true;
		return false;
	}

	/**
	 * @author vivek.mishra
	 * @param msg that should be displayed
	 * @return the match result
	 * @creation date 02/02/2018
	 */
	public boolean verifyImportResultMessage(String msg)
	{
		return (findVisibleElement(importResultMessage, Speed.slow).getText().trim().equalsIgnoreCase(msg));
	}

	/**
	 * @author vivek.mishra
	 *         To close the importICSModal
	 * @creation date 02/02/2018
	 */
	public void clickOnCloseButtonInImportICSModal()
	{
		WebElement closeButton = findVisibleElement(closeButtonInImportICSModal, Speed.slow);
		closeButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 06/02/2018
	 */
	public boolean verifySubscribeModal()
	{
		findVisibleElement(subscribeModal, Speed.slow);
		return (isDisplayed(subscribeModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the category list is available or not
	 * @creation date 14/03/2018
	 */
	public boolean verifyCategoryList()
	{
		findVisibleElement(categoryListTable, Speed.slow);
		return (isDisplayed(categoryListTable, Speed.slow));
	}
}
