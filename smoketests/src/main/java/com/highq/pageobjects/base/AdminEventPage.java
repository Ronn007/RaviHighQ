package com.highq.pageobjects.base;

import java.util.Map;
import com.highq.pagedata.EventCategoryData;
import com.highq.pagedata.EventSubscriptionData;

public interface AdminEventPage extends BannerPage
{
	public void clickOnSetNotificatons();

	public void clickOnSave();

	public void clickSaveOnImportIcsFile();

	public void setCalendarName(String calendarName);

	public void setCalendarUrl(String calendarUrl);

	public void clickOnCategoryPermission(String category);

	public void selectAutoUpdate(Boolean value);

	public void selectUpdateNow(Boolean value);

	public void clickOnImportIcsLink();

	public void clickSaveOnSubscribe();

	public void clickSubscribe();

	public void clickAddCategory();

	public void clickOnBackgroundColor();

	public void clickOnAddInNewCategoryDialog();

	public void clickOnSaveInEditCategoryDialog();

	public void setEventOptions(String option, boolean value);

	public void setTimeZone(String timeZone);

	public void setDefaultView(String defaultView);

	public void setWeekStart(String weekDay);

	public void setCategoryName(String categoryName);

	public Boolean isBackgroundColorExpanded();

	public void importIcsFile(String fileName, String category);

	public void setSubscription(EventSubscriptionData eventSubscriptionData);

	public void addCategory(EventCategoryData eventCategoryData);

	public void setBackgroundColor(EventCategoryData eventCategoryData);

	public void removeCategory(String category);

	public void editCategory(String category, EventCategoryData eventCategoryData);

	public void setEventRestrictedPermission(String category, Map<String, Map<String, Boolean>> permissions) throws InterruptedException;

	public void setEventDefaultPermissions(String category);

	public void clickImportOnImportIcsFile();

	public void clickSubscribeInSubscribeModal();

	public void selectSubscribeCategory(String categoryName);

	public void selectSubscribeSyncEvents(String syncEvent);

	public void addCategory(String category);

	public boolean verifyAddCategoryModal();

	public boolean verifyCategoryInCategoriesList(String category);

	public boolean verifyAdmineventsPage();

	public void enableEnableTasksCategory();

	public boolean verifyImportICSModal();

	public boolean verifyImportICSFileProgressBar();

	public boolean verifyImportResultMessage(String msg);

	public void clickOnCloseButtonInImportICSModal();

	public boolean verifySubscribeModal();

	public boolean verifyCategoryList();
}
