package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import com.highq.pageobjects.base.SiteNavigationPage;

/**
 * @author ravi.pandit
 */
public class SiteNavigationWeb extends AdminPageWeb implements SiteNavigationPage
{

	private static By clickSaveSiteNavPage = By.id("siteNavigation_saveButtonTop");
	private static By previewBtnSiteNavPage = By.id("siteNavigation_previewButtonTop");
	private static By cancelBtnSiteNavPage = By.id("siteNavigation_cancelBtnTop");

	private static By automaticSiteNavigation = By.id("automaticSiteNavigation");
	private static By customSiteNavigation = By.id("customSiteNavigation");
	private static By siteNavigationText = By.id("siteAdmin_module_leftTreeOption_siteNavigation");
	private static By renameModal = By.xpath(".//*[@id='system_locale_translate_modal' and @class='modal fade in']");
	private static By renameModalSaveButton = By.id("system_locale_translate_modal_add");
	private static By renameModalCancelButton = By.id("system_locale_translate_modal_close");

	private static By resetDefaultNamesLink = By.id("system_locale_translate_modal_reset");
	private static By addButtonInCustome = By.xpath("//*[@id='iterateElementInsideDiv']//button[normalize-space(.)='Add']");
	private static By addMenuItemModel = By.xpath(".//*[@id='GLOBAL_NAV_ADD_MENU_DIV' and contains(@class,'fade in')]");
	private static By addMenuContainerModel = By.xpath(".//*[@id='GLOBAL_NAV_ADD_CONTAINER_DIV' and contains(@class,'fade in')]");

	private static By addMenuItemBrowsTab = By.id("globalNavigationAddMenuModal_browseTabClick");
	private static By addMenuItemExternalTab = By.id("globalNavigationAddMenuModal_externalTabClick");
	private static By addMenuItemSearchTab = By.id("globalNavigationAddMenuModal_searchTabClick");

	// Add Conatiner Model
	private static By addContainerTitle = By.id("globalNavigationAddContainerModal_titleID");
	private static By addContainerNoOfColums = By.id("globalNavigationAddContainerModal_noOfColumns");

	// Add Menu Item with External Tab
	private static By externalTitleId = By.id("globalNavigationAddMenuModal_external_titleID");
	private static By externalURLId = By.id("globalNavigationAddMenuModal_external_urlID");
	private static By globalNavigationAddMenuModalExternalOpenInID = By.id("globalNavigationAddMenuModal_external_openInID");

	private static By addMenuModalGlobalNavCancel = By.id("addMenuModal_globalNav_cancel");
	private static By addMenuModalGlobalNavAddBtn = By.id("addMenuModal_globalNav_AddBtn");

	private static By addContainerModalGlobalNavCancel = By.id("addContainerModal_globalNav_cancel");
	private static By addContainerModalGlobalNavAddBtn = By.id("addContainerModal_globalNav_AddBtn");

	private static By globalNavigationMenuData = By.id("globalNavigationMenuData");
	private static By editCustomItemEditModel = By.xpath("//*[@id='GLOBAL_NAV_EDIT_MENU_DIV' and contains(@class,'fade in')]");

	private static By customEditModelGeneralTab = By.id("globalNavigationEditMenuModal_generalTab");
	private static By customEditModelCustomeCSSTab = By.id("globalNavigationEditMenuModal_customCssTab");
	private static By customEditModelSaveButton = By.id("editMenuModal_globalNav_doneBtn");
	private static By customEditModelCancelButton = By.id("editMenuModal_globalNav_cancel");
	private static By searchTextBoxOnBrowsTab = By.id("browseTabSiteSearchInputEle");
	By translateModal = By.xpath(".//*[@id='system_locale_translate_modal_BODY']");

	public SiteNavigationWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * Click Save button in Site Navigation Page
	 */
	@Override
	public void clickSaveonSiteNavigationPage()
	{
		findVisibleElement(clickSaveSiteNavPage);
		findVisibleElement(clickSaveSiteNavPage).click();
		findPresentElement(clickSaveSiteNavPage, Speed.slow);
	}

	/**
	 * Automatic Site Navigation Click Save button on Raname Model
	 */
	@Override
	public void clickRenameModalSaveButton()
	{
		findVisibleElement(renameModalSaveButton).click();
		findPresentElement(renameModalSaveButton, Speed.slow);
	}

	/**
	 * Automatic Site Navigation Click Cancel button on Raname Model
	 */
	@Override
	public void clickRenameModalCancelButton()
	{
		findVisibleElement(renameModalCancelButton).click();
		findPresentElement(renameModalCancelButton, Speed.slow);
	}

	/**
	 * @return
	 */
	@Override
	public boolean verifySiteNavigationModuleName()
	{
		return isDisplayed(siteNavigationText);
	}

	/**
	 * @return
	 */
	@Override
	public boolean verifyAutomaticSiteNavigationOption()
	{
		return isDisplayed(automaticSiteNavigation);
	}

	/**
	 * @return
	 */
	@Override
	public boolean verifyCustomSiteNavigationOption()
	{

		return isDisplayed(customSiteNavigation);
	}

	/**
	 * @param moduleName
	 * @return
	 */
	@Override
	public boolean verifyModuleRenameLink(String moduleName)
	{
		findVisibleElement(By.xpath(".//*[@id='iterateElementInsideDiv']//following::span[normalize-space(.)='" + moduleName + "']//preceding::a[2]")).click();
		boolean result = isDisplayed(By.xpath(".//*[@id='iterateElementInsideDiv']//following::span[normalize-space(.)='" + moduleName + "']//preceding::a[2]//following::a[1][normalize-space(.)='Rename']"));
		findVisibleElement(By.xpath(".//*[@id='iterateElementInsideDiv']//following::span[normalize-space(.)='" + moduleName + "']//preceding::a[2]")).click();
		return result;
	}

	/**
	 * @param moduleName
	 *        Click on rename link in Automatic site Navigation Page
	 */
	@Override
	public void clickRenameModules(String moduleName)
	{
		findVisibleElement(By.xpath(".//*[@id='iterateElementInsideDiv']//following::span[normalize-space(.)='"
				+ moduleName + "']//preceding::a[2]")).click();
		findClickableElement(By.xpath(".//*[@id='iterateElementInsideDiv']//following::span[normalize-space(.)='"
				+ moduleName + "']//preceding::a[2]//following::a[1][normalize-space(.)='Rename']")).click();

	}

	/**
	 * Automatic Site Navigation Fill the values in Rename Model
	 * 
	 * @param englishUK
	 * @param englishUS
	 * @param nederlands
	 * @param dutch
	 * @param francais
	 * @param espanol
	 */
	@Override
	public void renameModule(String englishUK, String englishUS, String nederlands, String dutch, String francais, String espanol)
	{

		findVisibleElement(renameModal);
		if (englishUK != null || englishUK != "")
		{
			findVisibleElement(By.xpath(".//*[@id='systemLocal_1']")).clear();
			findClickableElement(By.xpath(".//*[@id='systemLocal_1']")).sendKeys(englishUK);
		}

		if (englishUS != null || englishUS != "")
		{
			findClickableElement(By.xpath(".//*[@id='systemLocal_2']")).sendKeys(englishUS);
		}

		if (nederlands != null || nederlands != "")
		{
			findClickableElement(By.xpath(".//*[@id='systemLocal_3']")).sendKeys(nederlands);
		}

		if (dutch != null || dutch != "")
		{
			findClickableElement(By.xpath(".//*[@id='systemLocal_4']")).sendKeys(dutch);
		}

		if (francais != null || francais != "")
		{
			findClickableElement(By.xpath(".//*[@id='systemLocal_5']")).sendKeys(francais);
		}

		if (espanol != null || espanol != "")
		{
			findClickableElement(By.xpath(".//*[@id='systemLocal_6']")).sendKeys(espanol);
		}
	}

	/**
	 * Automatic Site Navigation Click Reset default names Link
	 */
	@Override
	public void resetDefaultNameLink()
	{
		findVisibleElement(translateModal, Speed.slow);
		findVisibleElement(resetDefaultNamesLink, Speed.slow).click();
	}

	/**
	 * @param moduleName
	 * @return
	 */
	@Override
	public boolean verifyNewModuleName(String moduleName)
	{
		return isDisplayed(By.xpath("//*[@id='iterateElementInsideDiv']//*[normalize-space(.)='" + moduleName.trim() + "']"));
	}

	/**
	 * Click on Automatic Site Navigation
	 */
	@Override
	public void clickOnAutomaticSiteNavigation()
	{
		findVisibleElement(automaticSiteNavigation).click();
	}

	/**
	 * Click on Custom Site Navigation
	 */
	@Override
	public void clickOnCustomSiteNavigation()
	{
		findVisibleElement(customSiteNavigation).click();
	}

	/**
	 * Click on Custom Site Navigation Page Add Button
	 */
	@Override
	public void clickAddButtoninCustomnavigation()
	{
		findVisibleElement(addButtonInCustome).click();
	}

	/**
	 * @param subMenu
	 *        Click on Custom Site Navigation Page sub menu of Add Button
	 */
	@Override
	public void clickonSubMenuofCustomNavigation(String subMenu)
	{
		findVisibleElement(By.xpath("//*[@id='iterateElementInsideDiv']//button[normalize-space(.)='Add']//following::li[normalize-space(.)='" + subMenu.trim() + "']")).click();
	}

	@Override
	public void gotoAddItemBrowseTab()
	{
		findVisibleElement(addMenuItemBrowsTab).click();

	}

	/**
	 * Add Item Menu With Browser Tab
	 * 
	 * @param linkTo
	 * @param siteName
	 * @param contentName
	 * @param categoryName
	 * @param userName
	 * @param openIn
	 */
	@Override
	public void addMenuItemWithBrowseTab(String linkTo, String siteName, String contentName, String categoryName, String userName)
	{

		selectLinkToinBrowseTab(linkTo);

		if (linkTo.equals("Site"))
		{
			enterTextOnSearchBrowseTab(siteName);
			findVisibleElement(By.xpath("//*[@id='insertLinkBrowseSiteDivID']//a[normalize-space(.)='" + siteName + "']")).click();

		}
		if (linkTo.equals("Module"))
		{
			selectSiteFromModulesBrowseTab(siteName);

			// select module from selected site
			findClickableElement(By.xpath("//*[@id='insertLinkBrowseTabModuleListDivID']//a[@title='" + contentName.trim() + "']")).click();
		}
		if (linkTo.equals("Files/Folders"))
		{
			selectSiteFromFileFoldersBrowseTab(siteName);
			findClickableElement(By.xpath("//*[contains(@onclick,'expandAllTree')]")).click();
			findClickableElement(By.xpath("//span[normalize-space(.)='" + contentName.trim() + "' and @class='fancytree-title']")).click();
		}
		if (linkTo.equals("Wiki page"))
		{
			selectSiteFromWikiBrowseTab(siteName);
			findClickableElement(By.xpath("//*[@id='insertLinkModal_browserTab_wikiTrees']//span[text()='" + contentName + "']")).click();

		}
		if (linkTo.equals("Blog post"))
		{
			selectSiteFromBlogBrowseTab(siteName);

			if (categoryName != "")
			{
				selectOptionFromDropDown(By.id("insertLinkModal_browseTab_blogPostCategoryDropDown"), categoryName);
			}

			findVisibleElement(By.xpath("//*[@id='insertLinkModal_browserTab_blogPost']//a[@title='" + contentName.trim() + "']")).click();
		}
		if (linkTo.equals("Blog category"))
		{

			selectSiteFromBlogCategoryBrowseTab(siteName);

			findClickableElement(By.xpath("//*[@id='insertLinkModal_browseTab_blogCatgoryListData']//a[@title='" + contentName + "']")).click();

		}
		if (linkTo.equals("Task"))
		{
			selectSiteFromTaskBrowseTab(siteName);

			if (categoryName != "")
			{
				selectOptionFromDropDown(By.id("insertLinkModal_browseTab_taskPageTaskListDropDown"), categoryName);
			}

			findVisibleElement(By.xpath("//*[@id='insertLinkModal_browseTab_taskPageData']//a[@title='" + contentName.trim() + "']")).click();
		}
		if (linkTo.equals("Task list"))
		{
			selectSiteFromTaskListBrowseTab(siteName);

			findClickableElement(By.xpath("//*[@id='insertLinkModal_browseTab_taskListData']//a[@title='" + contentName + "']")).click();
		}
		if (linkTo.equals("Event category"))
		{
			selectSiteFromEventCategoryListBrowseTab(siteName);

			findClickableElement(By.xpath("//*[@id='insertLinkModal_browseTab_eventCatgoryListData']//a[normalize-space(.)='" + contentName + "']")).click();
		}
		if (linkTo.equals("Event"))
		{
			selectSiteFromEventListBrowseTab(siteName);

			findClickableElement(By.xpath("//*[@id='insertLinkModal_browserTab_eventPage']//a[normalize-space(.)='" + contentName + "']")).click();
		}
		if (linkTo.equals("Person"))
		{
			selectSiteFromPersonBrowseTab(siteName);

			if (categoryName != "")
			{
				selectOptionFromDropDown(By.id("insertLinkModal_browseTab_personOrgDropDown"), categoryName);
			}

			findVisibleElement(By.xpath("//*[@id='insertLinkModal_browserTab_personPage']//span[normalize-space(.)='" + userName.trim() + "']")).click();
		}

	}

	@Override
	public void selectLinkToinBrowseTab(String linkTo)
	{
		findVisibleElement(addMenuItemModel, 15);
		gotoAddItemBrowseTab();
		selectOptionFromDropDown(By.id("insertLinkBrowseTabLinkToDropDownID"), linkTo);
	}

	@Override
	public void clickonFilterButtonOnAddMenuItem()
	{
		findVisibleElement(By.xpath("//*[contains(@class,'btn dropdown-toggle tooltipShow')]")).click();
	}

	/**
	 * @param title
	 * @param urlString
	 * @param openIn
	 * @param isVisible
	 *        Add Menu item With External Tab
	 */
	@Override
	public void addMenuItemWithExternal(String title, String urlString, String openIn, boolean isVisible)
	{
		findVisibleElement(addMenuItemModel);
		findVisibleElement(addMenuItemExternalTab).click();

		findVisibleElement(externalTitleId).sendKeys(title);
		findVisibleElement(externalURLId).sendKeys(urlString);

		// selectOptionFromDropDown(globalNavigationAddMenuModalExternalOpenInID, openIn);

		// WebElement visibleInternal = findClickableElement(By.id("globalNavigationEditMenuModal_external_visibilityID"));
		// if (isVisible)
		// {
		// if (!visibleInternal.isSelected())
		// {
		// findVisibleElement(By.id("globalNavigationEditMenuModal_external_visibilityID")).click();
		// }
		// }
		// else
		// {
		// if (visibleInternal.isSelected())
		// {
		// findVisibleElement(By.id("globalNavigationEditMenuModal_external_visibilityID")).click();
		// }
		// }
	}

	/**
	 * @param siteName
	 * @param content
	 *        Add Menu item With Search Tab
	 */
	@Override
	public void addMenuItemWithSearchTab(String siteName, String content)
	{
		findVisibleElement(addMenuItemModel);
		findVisibleElement(addMenuItemSearchTab).click();

		findVisibleElement(By.xpath("//div[@class='selectDrop dropdown']/button[@data-toggle='dropdown']")).click();
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_searchTab_siteList']//a[normalize-space(.)='" + siteName.trim() + "']")).click();

		findVisibleElement(By.id("insertlink_searchTab_searchContentInput")).sendKeys(content);
		findVisibleElement(By.xpath(".//*[@id='insertLink_searchTab_searchDatalist']//li//a[normalize-space(.)='" + content + "']")).click();
	}

	/**
	 * Click Add button on model of add item menu With External
	 */
	@Override
	public void clickAddButtonInAddMenuItemModel()
	{
		findVisibleElement(addMenuModalGlobalNavAddBtn).click();
		findPresentElement(addMenuModalGlobalNavAddBtn, Speed.slow);
	}

	/**
	 * Click Cancel button on model of add item menu With External
	 */
	@Override
	public void clickCancelButtonInAddMenuItemModel()
	{
		findVisibleElement(addMenuModalGlobalNavCancel).click();
		findPresentElement(addMenuModalGlobalNavCancel, Speed.slow);
	}

	@Override
	public boolean checkTitleValidationMessageinItemContainer(String content, String message)
	{
		findVisibleElement(addMenuItemModel);
		String titleValMessage = "";
		String urlValMessage = "";
		if (isDisplayed(By.xpath("//*[@id='globalNavigationAddMenuModal_external_titleID_pID'][contains(@style,'display: block')]")))
		{
			titleValMessage = findVisibleElement(By.xpath("//*[@id='globalNavigationAddMenuModal_external_titleID_pID'][contains(@style,'display: block')]")).getText();
		}
		if (isDisplayed(By.xpath("//*[@id='globalNavigationAddMenuModal_external_urlID_pID'][contains(@style,'display: block')]")))
		{
			urlValMessage = findVisibleElement(By.xpath("//*[@id='globalNavigationAddMenuModal_external_urlID_pID'][contains(@style,'display: block')]")).getText();
		}
		if (content.equals("title"))
		{
			return (message).equals(titleValMessage);
		}
		if (content.equals("url"))
		{
			return (message).equals(urlValMessage);
		}

		return false;
	}

	@Override
	public boolean checkEditValidationMessageinContainer(String message)
	{
		findVisibleElement(editCustomItemEditModel);
		String titleValMessage = findVisibleElement(By.xpath("//*[@id='globalNavigationEditMenuModal_titleID_pID'][contains(@style,'display: block')]")).getText();

		return (message).equals(titleValMessage);
	}

	@Override
	public boolean checkAddValidationMessageinContainerModel(String message)
	{
		findVisibleElement(editCustomItemEditModel);
		String titleValMessage = findVisibleElement(By.xpath("//*[@id='globalNavigationAddContainerModal_titleID_pID'][contains(@style,'display: block')]")).getText();

		return (message).equals(titleValMessage);
	}

	/**
	 * Fill the data in Add Container Model
	 * 
	 * @param title
	 * @param noofColumns
	 */
	@Override
	public void fillDataAddContainerModel(String title, String noofColumns, boolean isVisible)
	{
		WebElement visibleInternal = findClickableElement(By.id("globalNavigationAddContainerModal_visibilityID"));
		findVisibleElement(addMenuContainerModel);
		findVisibleElement(addContainerTitle).sendKeys(title);
		if (noofColumns != null || noofColumns != "")
		{
			selectOptionFromDropDown(addContainerNoOfColums, noofColumns);
		}
		if (isVisible)
		{
			if (!visibleInternal.isSelected())
			{
				findVisibleElement(By.id("globalNavigationAddContainerModal_visibilityID")).click();
			}
		}
		else
		{
			if (visibleInternal.isSelected())
			{
				findVisibleElement(By.id("globalNavigationAddContainerModal_visibilityID")).click();
			}
		}
	}

	/**
	 * Click on add Button in Add Container Model
	 */
	@Override
	public void clickAddButtonInAddContainerModel()
	{
		findVisibleElement(addContainerModalGlobalNavAddBtn).click();
		findPresentElement(addContainerModalGlobalNavAddBtn, Speed.slow);
	}

	/**
	 * Click on Cancel Button in Add Container Model
	 */
	@Override
	public void clickCancelButtonInAddContainerModel()
	{
		findVisibleElement(addContainerModalGlobalNavCancel).click();
		findPresentElement(addContainerModalGlobalNavCancel, Speed.slow);
	}

	/**
	 * @param menuName
	 *        Only click on Edit Item in Custom Site Navigation Page
	 */
	@Override
	public void clickEditIteminCustomNavigation(String menuName)
	{

		findVisibleElement(globalNavigationMenuData);

		findClickableElement(By.xpath(globalNavigationMenuData + "//following::a[@title='" + menuName.trim() + "']//following::a[1]")).click();

		findClickableElement(By.xpath(globalNavigationMenuData + "//following::a[@title='" + menuName.trim() + "']//following::span[1]//following::li[normalize-space(.)='Edit details']")).click();
	}

	@Override
	public void clickDeleteIteminCustomNavigation(String menuName)
	{

		findVisibleElement(globalNavigationMenuData);

		findClickableElement(By.xpath(globalNavigationMenuData + "//following::a[@title='" + menuName.trim() + "']//following::a[1]")).click();

		findClickableElement(By.xpath(globalNavigationMenuData + "//following::a[@title='" + menuName.trim() + "']//following::span[1]//following::li[normalize-space(.)='Delete']")).click();
	}

	/**
	 * @param title
	 * @param tooltip
	 * @param urlString
	 * @param openIn
	 * @param isVisible
	 *        set Value in General/Details Tab in Edit items
	 */
	@Override
	public void addGeneralTabValueinEditIteminCustomNavigation(String title, String tooltip, String urlString, String openIn, String noOfcolumn, boolean isVisible)
	{

		findVisibleElement(editCustomItemEditModel);
		findClickableElement(customEditModelGeneralTab).click();

		findVisibleElement(By.id("globalNavigationEditMenuModal_titleID")).sendKeys(title);
		findVisibleElement(By.id("globalNavigationEditMenuModal_toolTipID")).sendKeys(tooltip);

		if (urlString != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_urlID")).sendKeys(urlString);
		}

		if (noOfcolumn != "")
		{
			selectOptionFromDropDown(By.id("globalNavigationEditMenuModal_megaMenuNumberOfColumns"), noOfcolumn);
		}

		if (openIn != "")
		{
			selectOptionFromDropDown(By.id("globalNavigationEditMenuModal_external_openInID"), openIn);
		}

		WebElement visibleInternal = findClickableElement(By.id("globalNavigationEditMenuModal_external_visibilityID"));
		if (isVisible && !visibleInternal.isSelected())
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_external_visibilityID")).click();
		}
		else
		{
			if (visibleInternal.isSelected())
			{
				findVisibleElement(By.id("globalNavigationEditMenuModal_external_visibilityID")).click();
			}
		}
	}

	/**
	 * @param desktopClassName
	 * @param desktopStyle
	 * @param mobileClassName
	 * @param mobileStyle
	 *        set Value in Custom CSS Tab in Edit items
	 */
	@Override
	public void editCSSValueinMenuIteminCustomNavigation(String desktopClassName, String desktopStyle,
			String mobileClassName, String mobileStyle)
	{
		findVisibleElement(editCustomItemEditModel);
		findClickableElement(customEditModelCustomeCSSTab).click();

		if (desktopClassName != null || desktopClassName != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_menuCssClassName")).sendKeys(desktopClassName);
		}

		if (desktopStyle != null || desktopStyle != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_menuCssStyle")).sendKeys(desktopStyle);
		}

		if (mobileClassName != null || mobileClassName != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_mobileMenuCssClassName")).sendKeys(mobileClassName);
		}

		if (mobileStyle != null || mobileStyle != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_mobileMenuCssStyle")).sendKeys(mobileStyle);
		}
	}

	/**
	 * @param desktopClassName
	 * @param desktopStyle
	 * @param mobileClassName
	 * @param mobileStyle
	 *        set Value in Custom CSS Tab in Edit items
	 */
	@Override
	public void editCSSValueinContainerIteminCustomNavigation(String desktopClassName, String desktopStyle, String mobileClassName, String mobileStyle)
	{
		findVisibleElement(editCustomItemEditModel);
		findClickableElement(customEditModelCustomeCSSTab).click();

		if (desktopClassName != null || desktopClassName != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_menuCssClassName")).sendKeys(desktopClassName);
		}

		if (desktopStyle != null || desktopStyle != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_menuCssStyle")).sendKeys(desktopStyle);
		}

		if (mobileClassName != null || mobileClassName != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_mobileMenuCssClassName")).sendKeys(mobileClassName);
		}

		if (mobileStyle != null || mobileStyle != "")
		{
			findVisibleElement(By.id("globalNavigationEditMenuModal_mobileMenuCssStyle")).sendKeys(mobileStyle);
		}
	}

	/**
	 * Click Save on Edit Custome Site Navigation Model
	 */
	@Override
	public void editIteminCustomNavigationSaveBtn()
	{
		findVisibleElement(customEditModelSaveButton);
	}

	/**
	 * Click Cancel on Edit Custome Site Navigation Model
	 */
	@Override
	public void editIteminCustomNavigationCancelBtn()
	{
		findVisibleElement(customEditModelCancelButton);
	}

	/**
	 * @param siteName
	 * @return
	 * 		Verify SiteName avaliable in Browse Tab on Add Item Menu
	 */
	@Override
	public boolean verifySiteIsBrowseTab(String siteName)
	{
		findVisibleElement(By.id("insertLinkBrowseSiteListDiv"));
		return isDisplayed(By.xpath("//*[@id='insertLinkBrowseSiteListDiv']//following::*[normalize-space(.)='" + siteName + "']"));
	}

	@Override
	public boolean verifyFilterOptionInBrowsTab(String contentName)
	{
		return isDisplayed(By.xpath("//*[@id='browseTabSite_filterByOptions']//*[normalize-space(.)='" + contentName + "']"));
	}

	public boolean verifySubFilterOptionInBrowsTab(String contentName)
	{
		return isDisplayed(By.xpath("// *[@id='browseTabSite_filterByOptions']//a[contains(text(),'" + contentName + "')]"));
	}

	@Override
	public void clickOnStatusInBrowsTab()
	{
		findVisibleElement(By.xpath("//*[@id='browseTabSite_filterByOptions']//*[normalize-space(.)='Status']")).click();
	}

	@Override
	public void clickonFiltersonBrowsTab(String parent, String filterName)
	{
		if (parent != "")
		{
			findClickableElement(By.xpath("//*[@id='accordionSearch']//a[normalize-space(.)='" + parent + "']")).click();
		}
		if (parent != "" && !isDisplayed(By.xpath("//*[contains(@id,'collapseList') and contains(@class,'collapse in')]")))
		{
			findClickableElement(By.xpath("//*[@id='accordionSearch']//a[normalize-space(.)='" + parent + "']")).click();
		}
		findVisibleElement(By.xpath("//*[contains(@id,'collapseList') and contains(@class,'collapse in')]"));
		findClickableElement(By.xpath("//*[@id='browseTabSite_filterByOptions']//a[contains(text(),'" + filterName + "')]")).click();
		clickonFilterButtonOnAddMenuItem();
	}

	@Override
	public void enterTextOnSearchBrowseTab(String textName)
	{
		findVisibleElement(searchTextBoxOnBrowsTab).sendKeys(textName);
	}

	@Override
	public boolean verifydropDownSiteListBrowseTab()
	{
		return isDisplayed(By.id("insertLinkModal_dropDownSiteList_browseTab_module_selectedSiteName"));
	}

	@Override
	public void selectSiteFromModulesBrowseTab(String siteName)
	{
		By dropDownButton = By.id("insertLinkModal_dropDownSiteList_browseTab_module_selectedSiteName");
		By dropDownBox = By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_module_selectedSiteName']//following::ul[1]");
		String optionList = "//*[@id='insertLinkModal_dropDownSiteList_browseTab_module_selectedSiteName']//following::ul[1]//li";

		selectOptionFromDropDown(dropDownButton, dropDownBox, optionList, siteName);
	}

	@Override
	public void selectSiteFromFileFoldersBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_filesFolder_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_filesFolder_siteList']//a[@title='" + siteName + "']")).click();
	}

	@Override
	public void selectSiteFromWikiBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_wiki_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_wiki_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromBlogBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_blog_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_blog_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromBlogCategoryBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_blogCategory_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_blogCategory_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromTaskBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_task_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_task_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromTaskListBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_taskList_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_taskList_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromEventCategoryListBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_eventCategory_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_eventCategory_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromEventListBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_event_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_event_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public void selectSiteFromPersonBrowseTab(String siteName)
	{
		findClickableElement(By.xpath("//*[contains(@onclick,'checkAndRefreshDropDownSiteList')]")).click();
		findVisibleElement(By.id("insertLinkModal_dropDownSiteList_browseTab_person_siteSearchInput")).sendKeys(siteName);
		findClickableElement(By.xpath("//*[@id='insertLinkModal_dropDownSiteList_browseTab_person_siteList']//a[@title='" + siteName.trim() + "']")).click();
	}

	@Override
	public boolean verifySitesFromModulesBrowseTab(String moduleName)
	{
		return isDisplayed(By.xpath("//*[@id='insertLinkBrowseTabModuleListDivID']//a[@title='" + moduleName.trim() + "']"));
	}

	@Override
	public boolean verifyFilesAndFolderdropDownBrowseTab()
	{
		return isDisplayed(By.id("insertLinkModal_dropDownSiteList_browseTab_filesFolder_selectedSiteName"));
	}

	@Override
	public boolean verifyfileOrFolderNameinBrowseTab(String fileName)
	{
		return isDisplayed(By.xpath("//span[normalize-space(.)='" + fileName.trim() + "' and @class='fancytree-title']"));
	}

	@Override
	public boolean verifyWikidropDownBrowseTab()
	{
		return isDisplayed(By.xpath("//*[contains(@onclick,'insertLinkModal_dropDownSiteList_browseTab_wiki') and @data-toggle='dropdown']"));
	}

	@Override
	public void clickonPreviewButton()
	{
		findClickableElement(By.id("siteNavigation_previewButtonTop")).click();
	}

	@Override
	public void clickonCancelPreviewButton()
	{
		findClickableElement(By.id("siteNavigation_cancelPreviewBtnTop")).click();
	}

	@Override
	public boolean verifySaveButtononSiteNavPage()
	{
		return isDisplayed(clickSaveSiteNavPage);
	}

	@Override
	public boolean verifyPreviewButtononSiteNavPage()
	{
		return isDisplayed(previewBtnSiteNavPage);
	}

	@Override
	public boolean verifyCancelButtononSiteNavPage()
	{
		return isDisplayed(cancelBtnSiteNavPage);
	}

	@Override
	public boolean verifyMenuItemInCustomNav(String subMenuName)
	{
		return isDisplayed(By.xpath("//*[@id='iterateElementInsideDiv']//button[normalize-space(.)='Add']//following::ul[1]//a[normalize-space(.)='" + subMenuName + "']"));
	}

	@Override
	public boolean verifyAddItemMenuTabs(String tabName)
	{
		return isDisplayed(By.xpath("//*[@id='GLOBAL_NAV_ADD_MENU_DIV_BODY']//li[normalize-space(.)='" + tabName + "']"));
	}

	@Override
	public boolean verifySiteDropdownInBrowsTab()
	{
		return isDisplayed(By.id("insertLinkBrowseTabLinkToDropDownID"));
	}

	@Override
	public boolean verifyContainerModel()
	{
		return isDisplayed(addMenuContainerModel);
	}

	@Override
	public boolean verifyAddButtonInConatinerModel()
	{
		return isDisplayed(addContainerModalGlobalNavAddBtn);
	}

	@Override
	public boolean verifyCancelButtonInConatinerModel()
	{
		return isDisplayed(addContainerModalGlobalNavCancel);
	}

	@Override
	public boolean verifyPreviewModeMsg()
	{
		String previewMsg = findVisibleElement(By.id("siteNavigation_previeModeMessageId")).getText();
		return ("You are in preview mode").equals(previewMsg);
	}

	@Override
	public void dragAndDropItemsinContainer(WebElement source, WebElement destination)
	{
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(source).moveToElement(destination).release(destination).build();
		dragAndDrop.perform();
	}

	@Override
	public void clickOnCloseButtonAddMenu()
	{
		findVisibleElement(By.id("GLOBAL_NAV_ADD_MENU_DIV_MAIN_CLOSE_BUTTON")).click();
	}

	@Override
	public void clickOnCloseButtonAddContainerModel()
	{
		findVisibleElement(By.id("GLOBAL_NAV_ADD_CONTAINER_DIV_MAIN_CLOSE_BUTTON")).click();
	}

	@Override
	public void deleteAllModulesInCustomModules()
	{
		int eleSize = driver.findElements(By.xpath("//*[@id='globalNavigationMenuData']//li[contains(@class,'globalNavMenuItem')]")).size();

		for (int i = 1; i <= eleSize; i++)
		{
			findClickableElement(By.xpath("// *[@id='globalNavigationMenuData']//li[contains(@class,'globalNavMenuItem')][1]//a[@data-original-title='More actions']")).click();
			findClickableElement(By.xpath("//*[@id='globalNavigationMenuData']//li[contains(@class,'globalNavMenuItem')][1]//a[normalize-space(text())='Delete']")).click();
		}
	}

}
