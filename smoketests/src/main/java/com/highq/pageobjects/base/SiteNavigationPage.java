package com.highq.pageobjects.base;

import org.openqa.selenium.WebElement;

public interface SiteNavigationPage extends BannerPage
{
	public void clickSaveonSiteNavigationPage();

	public void clickRenameModalSaveButton();

	public void clickRenameModalCancelButton();

	public boolean verifySiteNavigationModuleName();

	public boolean verifyAutomaticSiteNavigationOption();

	public boolean verifyCustomSiteNavigationOption();

	public boolean verifyModuleRenameLink(String moduleName);

	public void clickRenameModules(String moduleName);

	public void renameModule(String englishUK, String englishUS, String nederlands, String dutch, String francais, String espanol);

	public void resetDefaultNameLink();

	public boolean verifyNewModuleName(String moduleName);

	public void clickOnAutomaticSiteNavigation();

	public void clickOnCustomSiteNavigation();

	public void clickAddButtoninCustomnavigation();

	public void clickonSubMenuofCustomNavigation(String subMenu);

	public void gotoAddItemBrowseTab();

	public void addMenuItemWithBrowseTab(String linkTo, String siteName, String contentName, String categoryName, String userName);

	public void selectLinkToinBrowseTab(String linkTo);

	public void clickonFilterButtonOnAddMenuItem();

	public void addMenuItemWithExternal(String title, String urlString, String openIn, boolean isVisible);

	public void addMenuItemWithSearchTab(String siteName, String content);

	public void clickAddButtonInAddMenuItemModel();

	public void clickCancelButtonInAddMenuItemModel();

	public boolean checkTitleValidationMessageinItemContainer(String content, String message);

	public void fillDataAddContainerModel(String title, String noofColumns, boolean isVisible);

	public void clickAddButtonInAddContainerModel();

	public void clickCancelButtonInAddContainerModel();

	public void clickEditIteminCustomNavigation(String menuName);

	public void addGeneralTabValueinEditIteminCustomNavigation(String title, String tooltip, String urlString, String openIn, String noOfcolumn, boolean isVisible);

	public void editCSSValueinMenuIteminCustomNavigation(String desktopClassName, String desktopStyle, String mobileClassName, String mobileStyle);

	public void editCSSValueinContainerIteminCustomNavigation(String desktopClassName, String desktopStyle, String mobileClassName, String mobileStyle);

	public void editIteminCustomNavigationSaveBtn();

	public void editIteminCustomNavigationCancelBtn();

	public boolean verifySiteIsBrowseTab(String siteName);

	public boolean verifyFilterOptionInBrowsTab(String contentName);

	public void clickOnStatusInBrowsTab();

	public void clickonFiltersonBrowsTab(String parent, String filterName);

	public void enterTextOnSearchBrowseTab(String textName);

	public boolean verifydropDownSiteListBrowseTab();

	public void selectSiteFromModulesBrowseTab(String siteName);

	public void selectSiteFromFileFoldersBrowseTab(String siteName);

	public void selectSiteFromWikiBrowseTab(String siteName);

	public void selectSiteFromBlogBrowseTab(String siteName);

	public void selectSiteFromBlogCategoryBrowseTab(String siteName);

	public void selectSiteFromTaskBrowseTab(String siteName);

	public void selectSiteFromTaskListBrowseTab(String siteName);

	public void selectSiteFromPersonBrowseTab(String siteName);

	public boolean verifySitesFromModulesBrowseTab(String moduleName);

	public boolean verifyFilesAndFolderdropDownBrowseTab();

	public boolean verifyfileOrFolderNameinBrowseTab(String fileName);

	public boolean verifyWikidropDownBrowseTab();

	public void clickonPreviewButton();

	public void clickonCancelPreviewButton();

	public void dragAndDropItemsinContainer(WebElement source, WebElement destination);

	public void clickOnCloseButtonAddMenu();

	public void clickOnCloseButtonAddContainerModel();

	public boolean verifySaveButtononSiteNavPage();

	public boolean verifyPreviewButtononSiteNavPage();

	public boolean verifyCancelButtononSiteNavPage();

	public boolean verifyMenuItemInCustomNav(String subMenuName);

	public boolean verifyAddItemMenuTabs(String tabName);

	public boolean verifySiteDropdownInBrowsTab();

	public boolean verifyContainerModel();

	public boolean verifyAddButtonInConatinerModel();

	public boolean verifyCancelButtonInConatinerModel();

	public boolean verifyPreviewModeMsg();

	public void selectSiteFromEventListBrowseTab(String siteName);

	public void selectSiteFromEventCategoryListBrowseTab(String siteName);

	public void clickDeleteIteminCustomNavigation(String menuName);

	public boolean checkEditValidationMessageinContainer(String message);

	public boolean checkAddValidationMessageinContainerModel(String message);

	public void deleteAllModulesInCustomModules();
}
