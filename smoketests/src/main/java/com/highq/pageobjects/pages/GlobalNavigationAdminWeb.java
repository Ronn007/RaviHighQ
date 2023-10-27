package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.GlobalNavigationLabels;
import com.highq.pageobjects.base.GlobalNavigationAdminPage;

public class GlobalNavigationAdminWeb extends BannerPageWeb implements GlobalNavigationAdminPage
{
	By globalNavigationDropDown = By.id("globalNavigationDisplay_property");
	By menuItemDropDown = By.xpath(".//button[@class='btn btn-default dropdown-toggle']");
	By dragAndDropMessage = By.xpath(".//*[normalize-space(.)='" + GlobalNavigationLabels.GLOBALNAV_DRAGANDDROPMESSAGE + "']");

	By previewButtonBottom = By.id("globalNavigation_previewButtonBottom");
	By previewButtonTop = By.id("globalNavigation_previewButtonTop");

	By saveButtonBottom = By.id("globalNavigation_saveButtonBottom");
	By saveButtonTop = By.id("globalNavigation_saveButtonTop");

	By cancelButtonBottom = By.xpath(".//*[@id='globalNavigation_previewButtonBottom']//preceding::button[text()='" + GlobalNavigationLabels.GLOBALNAV_CANCEL + "'][1]");
	By cancelButtonTop = By.xpath(".//*[@id='globalNavigation_previewButtonTop']//preceding::button[text()='" + GlobalNavigationLabels.GLOBALNAV_CANCEL + "'][1]");

	By menuItemDropDownOpened = By.xpath(".//button[@class='btn btn-default dropdown-toggle' and @aria-expanded='true']");

	By addMenuItemDialogOpened = By.xpath(".//*[@id='GLOBAL_NAV_ADD_MENU_DIV' and contains(@style,'display: block')]");
	By addMenuItemDialogClosed = By.xpath(".//*[@id='GLOBAL_NAV_ADD_MENU_DIV' and contains(@style,'display: none')]");
	By addMenuItemExternalTab = By.id("globalNavigationAddMenuModal_externalTabClick");
	By addMenuItemExternalTab_title = By.id("globalNavigationAddMenuModal_external_titleID");
	By addMenuItemExternalTab_URL = By.id("globalNavigationAddMenuModal_external_urlID");
	By addMenuItemExternalTab_OpenIn = By.id("globalNavigationAddMenuModal_external_openInID");
	By addMenuItemExternalAddButton = By.id("addMenuModal_globalNav_AddBtn");

	By addContainerModal = By.id("GLOBAL_NAV_ADD_CONTAINER_DIV");
	By addContainerModalOpened = By.xpath(".//*[@id='GLOBAL_NAV_ADD_CONTAINER_DIV' and contains(@style,'display: block')]");
	By addContainerModalClosed = By.xpath(".//*[@id='GLOBAL_NAV_ADD_CONTAINER_DIV' and contains(@style,'display: none')]");
	By locInptContainerTitle = By.id("globalNavigationAddContainerModal_titleID");
	By locDrpdwnNoOfColumns = By.id("globalNavigationAddContainerModal_noOfColumns");
	By locBtnAddContainer = By.id("addContainerModal_globalNav_AddBtn");
	By locBtnCancelContainerModal = By.id("addContainerModal_globalNav_cancel");
	By locBtnCloseContainerModal = By.id("GLOBAL_NAV_ADD_CONTAINER_DIV_MAIN_CLOSE_BUTTON");

	By locTabAddCustomMenuItem = By.id("globalNavigationAddMenuModal_customTabClick");
	By locDrpdwnChooseContainer = By.id("globalNavigationAddMenuModal_chooseContainer");
	By locLinkAddNewContainer = By.xpath(".//*[@id='GLOBAL_NAV_ADD_MENU_DIV']//*[text()='" + GlobalNavigationLabels.GLOBALNAV_ADDNEW + "']");
	By locInptCustomMenuTitle = By.id("globalNavigationAddMenuModal_customTitleID");
	By locInptCustomMenuContent = By.id("globalNavigationAddMenuModal_customCKContentID");

	By globalNavigationLabel = By.xpath(".//label[text()='Global navigation']");
	By menuItemLabel = By.xpath(".//label[text()='Menu item']");
	By noMenuFoundMessage = By.xpath(".//*[@id='globalNavigationNoRecordFoundDiv' and text()='" + GlobalNavigationLabels.GLOBALNAV_NOMENUFOUNDMESSAGE + "']");
	By globalNavigationMenuItemsList = By.xpath(".//*[@id='globalNavigationMenuData']//*[contains(@class,'globalNavMenuItem')]");

	public GlobalNavigationAdminWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void selectGlobalNavigationOption(String option)
	{
		Select globalNavigationSelect = new Select(findClickableElement(globalNavigationDropDown, Speed.slow));
		if (!globalNavigationSelect.getFirstSelectedOption().getText().equalsIgnoreCase(option))
		{
			globalNavigationSelect.selectByVisibleText(option);
		}
	}

	@Override
	public void clickOnMenuItemDropDown()
	{
		WebElement menuItemEle = findClickableElement(menuItemDropDown);
		menuItemEle.click();
	}

	@Override
	public void selectMenuItemOption(String option)
	{
		if (!isDisplayed(menuItemDropDownOpened, Speed.slow))
		{
			clickOnMenuItemDropDown();
		}
		findVisibleElement(menuItemDropDownOpened, Speed.slow, 5);

		WebElement optionEle = findClickableElement(By.xpath(".//*[@role='menu']//*[text()='" + option.trim() + "']"));
		optionEle.click();
	}

	@Override
	public void clickExternalOnAddMenuItemDialog()
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement externalEle = findClickableElement(addMenuItemExternalTab);
		externalEle.click();
	}

	@Override
	public void addMenuItemExternalTitle(String title)
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement titleEle = findClickableElement(addMenuItemExternalTab_title);
		titleEle.sendKeys(title);
	}

	@Override
	public void addMenuItemExternalURL(String url)
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement urlEle = findClickableElement(addMenuItemExternalTab_URL);
		urlEle.sendKeys(url);
	}

	@Override
	public void addMenuItemExternalSelectOpenInOption(String option)
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement openInEle = findPresentElement(addMenuItemExternalTab_OpenIn);
		Select openInSelect = new Select(openInEle);
		openInSelect.selectByVisibleText(option);
	}

	@Override
	public void clickAddOnAddMenuItemDialog()
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement addEle = findClickableElement(addMenuItemExternalAddButton);
		addEle.click();
		findPresentElement(addMenuItemDialogClosed, Speed.slow, 10);
	}

	@Override
	public void addExternalMenuItem(String title, String url, String openIn)
	{
		addMenuItemExternalTitle(title);
		addMenuItemExternalURL(url);
		addMenuItemExternalSelectOpenInOption(openIn);
		clickAddOnAddMenuItemDialog();
	}

	@Override
	public void clickSaveOnGlobalNavigation()
	{
		WebElement saveEle = findClickableElement(saveButtonTop);
		saveEle.click();
	}

	@Override
	public void deleteMenuItem(String menuItem)
	{
		while (menuItemPresent(menuItem))
		{
			WebElement moreActionsEle = findClickableElement(By.xpath(".//*[@id='globalNavigationMenuData']//*[text()='" + menuItem.trim() + "']//following::*[@data-original-title='" + GlobalNavigationLabels.GLOBALNAV_DATAORIGINALTITLE_MOREACTIONS + "'][1]"), Speed.slow);
			moreActionsEle.click();

			WebElement deleteEle = findVisibleElement(By.xpath(".//*[contains(@class,'dropdown pull-right open')]//*[text()='" + GlobalNavigationLabels.GLOBALNAV_DELETE + "']"), Speed.slow);
			deleteEle.click();
		}
		clickSaveOnGlobalNavigation();
	}

	@Override
	public void enterContainerTitle(String containerTitle)
	{
		WebElement inptContainerTitle = findClickableElement(locInptContainerTitle);
		inptContainerTitle.sendKeys(containerTitle);
	}

	@Override
	public void setColumnNumber(int columnNumber)
	{
		Select drpdwnNoOfColumns = new Select(findClickableElement(locDrpdwnNoOfColumns));
		drpdwnNoOfColumns.selectByVisibleText(Integer.toString(columnNumber));
	}

	@Override
	public void clickAddOnAddContainer()
	{
		WebElement btnAddContainer = findClickableElement(locBtnAddContainer);
		btnAddContainer.click();
	}

	@Override
	public void addContainer(String containerName, int noOfColumns)
	{
		findVisibleElement(addContainerModalOpened, Speed.slow);
		enterContainerTitle(containerName);
		setColumnNumber(noOfColumns);
		clickAddOnAddContainer();
		findPresentElement(addContainerModalClosed, Speed.slow);
	}

	public void addContainer(String containerName)
	{
		findVisibleElement(addContainerModalOpened, Speed.slow);
		enterContainerTitle(containerName);
		clickAddOnAddContainer();
		findPresentElement(addContainerModalClosed, Speed.slow);
	}

	@Override
	public boolean menuItemPresent(String menuItem)
	{
		return isDisplayed(By.xpath(".//*[@id='globalNavigationMenuData']//*[text()='" + menuItem.trim() + "']"));
	}

	@Override
	public void clickCustomOnAddMenuItemDialog()
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement tabAddCustomMenuItem = findClickableElement(locTabAddCustomMenuItem);
		tabAddCustomMenuItem.click();
	}

	@Override
	public void clickAddNewContainerLink()
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		WebElement linkAddNewContainer = findClickableElement(locLinkAddNewContainer);
		linkAddNewContainer.click();
	}

	@Override
	public void setContainerForCustomMenuItem(String containerName)
	{
		Select drpdwnChooseContainer = new Select(findClickableElement(locDrpdwnChooseContainer));
		if (valuePresent(drpdwnChooseContainer.getOptions(), containerName))
		{
			drpdwnChooseContainer.selectByVisibleText(containerName);
		}
		else
		{
			clickAddNewContainerLink();
			addContainer(containerName);
		}
	}

	@Override
	public void enterCustomMenuItemTitle(String title)
	{
		WebElement inptCustomMenuTitle = findClickableElement(locInptCustomMenuTitle);
		inptCustomMenuTitle.sendKeys(title);
	}

	@Override
	public void enterCustomMenuItemContent(String content)
	{
		WebElement inptCustomMenuContent = findClickableElement(locInptCustomMenuContent);
		inptCustomMenuContent.sendKeys(content);
	}

	@Override
	public void addCustomMenuItem(String containerName, String customMenuTitle, String customMenuContent)
	{
		findVisibleElement(addMenuItemDialogOpened, Speed.slow, 10);
		setContainerForCustomMenuItem(containerName);
		enterCustomMenuItemTitle(customMenuTitle);
		enterCustomMenuItemContent(customMenuContent);
		clickAddOnAddMenuItemDialog();
		findPresentElement(addMenuItemDialogClosed, Speed.slow, 10);
	}

	/**
	 * Verify 'Global Navigation' Label
	 * @return true -> If label is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyGolbalNavigationLabel()
	{
		return isDisplayed(globalNavigationLabel, Speed.slow);
	}

	/**
	 * Verify options of Global Navigation dropdown
	 * @return true -> If option is present,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyOptionOnGolbalNavigationDropdown(String option)
	{
		return isDisplayed(By.xpath(".//*[@id='globalNavigationDisplay_property']//*[text()='" + option.trim() + "']"));
	}

	/**
	 * Click on 'Global Navigation' dropdown
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public void clickOnGlobalNavigationDropdown()
	{
		findVisibleElement(globalNavigationDropDown, Speed.slow).click();
	}

	/**
	 * Verify 'Menu Item' Label
	 * @return true -> If label is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyMenuItemLabel()
	{
		return isDisplayed(menuItemLabel, Speed.slow);
	}

	/**
	 * Verify options of 'Menu Item' dropdown
	 * @return true -> If option is present,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyMenuItemOption(String option)
	{
		if (!isDisplayed(menuItemDropDownOpened))
		{
			clickOnMenuItemDropDown();
		}
		findVisibleElement(menuItemDropDownOpened, Speed.slow);
		return isDisplayed(By.xpath(".//*[@type='button' and @data-toggle='dropdown' and normalize-space()='Add' and @aria-expanded='true']//following-sibling::*//*[text()='" + option.trim() + "']"));
	}

	/**
	 * Verify 'Drag and drop' message
	 * @return true -> If message is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyDragAndDropMessage()
	{
		return isDisplayed(dragAndDropMessage, Speed.slow);
	}

	/**
	 * Verify 'no menu item found' message
	 * @return true ->
	 *         If 'No' menu item is present and message is getting displayed.
	 *         If one or more menu items are present and message is NOT getting displayed.
	 * @return false ->
	 *         If 'No' menu item is present and message is NOT getting displayed.
	 *         If one or more menu items are present and still message is getting displayed.
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyNoMenuFoundMessage()
	{
		if (isDisplayed(globalNavigationMenuItemsList))
		{
			return !isDisplayed(noMenuFoundMessage, Speed.slow);
		}
		else
		{
			return isDisplayed(noMenuFoundMessage, Speed.slow);
		}
	}

	/**
	 * Verify TOP SAVE button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyTopSaveButton()
	{
		return isDisplayed(saveButtonTop, Speed.slow);
	}

	/**
	 * Verify BOTTOM SAVE button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyBottomSaveButton()
	{
		return isDisplayed(saveButtonBottom, Speed.slow);
	}

	/**
	 * Verify TOP PREVIEW button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyTopPreviewButton()
	{
		return isDisplayed(previewButtonTop, Speed.slow);
	}

	/**
	 * Verify BOTTOM PREVIEW button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyBottomPreviewButton()
	{
		return isDisplayed(previewButtonBottom, Speed.slow);
	}

	/**
	 * Verify TOP CANCEL button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyTopCancelButton()
	{
		return isDisplayed(cancelButtonTop, Speed.slow);
	}

	/**
	 * Verify BOTTOM CANCEL button
	 * @return true -> If button is displayed,
	 *         false otherwise
	 * @author nidhi.shah
	 * @created 22-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyBottomCancelButton()
	{
		return isDisplayed(cancelButtonBottom, Speed.slow);
	}
}
