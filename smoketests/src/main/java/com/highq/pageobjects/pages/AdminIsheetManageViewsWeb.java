package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminIsheetManageViewsPage;

public class AdminIsheetManageViewsWeb extends AdminIsheetWeb implements AdminIsheetManageViewsPage
{

	public AdminIsheetManageViewsWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/** iSheet Manage Views Home */

	By locBtnBack = By.linkText("Back");
	By locBtnAddView = By.xpath("//*[@onclick='IsheetListViewPageCollection.createView();']");// By.linkText("Add view");
	By locBtnSortViews = By.id("sortIsheetViewButton");
	By locSpnDefaultTag = By.id("setasdefault");
	By locSpnAlerterTag = By.id("setasAlerter");

	/** Delete iSheet View */

	By locDeleteViewModal = By.id("collaborateCustomMessageModal");
	By locDeleteViewModalMessage = By.id("collaborateCustomModalMessage");
	By locBtnDeleteView = By.id("collaborateMessageOkButton");
	By locBtnCancelDeleteViewModal = By.id("collaborateMessageCancelButton");
	By locBtnCloseDeleteViewModal = By.id("collaborateMessageCloseButton");

	/** Sort view */

	By locBtnApplySortViews = By.id("ISHEET_VIEW_SORT_BY_DRAGGING_MODAL_applyButton");
	By locBtnCancelSortViews = By.id("ISHEET_VIEW_SORT_BY_DRAGGING_MODAL_cancelLinkButton");
	By locBtnCloseSortViews = By.id("ISHEET_VIEW_SORT_BY_DRAGGING_MODAL_MAIN_CLOSE_BUTTON");
	By locModalSortViewsOpened = By.xpath(".//*[@id='ISHEET_VIEW_SORT_BY_DRAGGING_MODAL' and contains(@style,'display: block')]");
	By locModalSortViewsClosed = By.xpath(".//*[@id='ISHEET_VIEW_SORT_BY_DRAGGING_MODAL' and contains(@style,'display: none')]");

	/** iSheet Manage Views Home */

	@Override
	public AdminIsheetEditViewWeb selectView(String viewName)
	{
		WebElement viewname = findClickableElement(By.xpath(".//*[normalize-space(text())='" + viewName.trim() + "'][1]"));
		viewname.click();
		return new AdminIsheetEditViewWeb(driver);
	}

	@Override
	public AdminIsheetWeb clickBackOnManageViews()
	{
		WebElement btnBack = findClickableElement(locBtnBack);
		btnBack.click();
		return new AdminIsheetWeb(driver);
	}

	@Override
	public AdminIsheetAddViewWeb clickAddView()
	{
		WebElement btnAddView = findClickableElement(locBtnAddView);
		btnAddView.click();
		return new AdminIsheetAddViewWeb(driver);
	}

	@Override
	public void clickSortView()
	{
		WebElement btnSortViews = findClickableElement(locBtnSortViews);
		btnSortViews.click();
	}

	@Override
	public void clickOnViewMoreAction(String viewName)
	{
		WebElement iSheetView = findClickableElement(By.xpath("(.//*[normalize-space(text())='" + viewName.trim() + "']//following::*[@data-original-title='More actions'])[1]"));
		iSheetView.click();
	}

	@Override
	public AdminIsheetWeb selectOptionOnViewMoreAction(String viewName, String option)
	{
		clickOnViewMoreAction(viewName);
		findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + viewName.trim() + "']//following::*[@data-original-title='More actions' and @aria-expanded='true'])[1]"));
		WebElement moreActionsOptionEle = findClickableElement(By.xpath(".//*[contains(@class,'dropdown pull-left open')]//*[text()='" + option.trim() + "']"));
		moreActionsOptionEle.click();

		switch (option.toLowerCase())
		{
			case "edit permissions":
				return new AdminIsheetViewPermissionsWeb(driver);
			case "set as default view":
				return this;
			case "set as alerter view":
				return this;
			case "delete":
				return this;
			default:
				System.err.println("Enter Valid Option");
				return this;
		}
	}

	/** Delete iSheet View */

	@Override
	public void clickDeleteOnDeleteViewModal()
	{
		if (isDisplayed(locDeleteViewModal, Speed.slow))
		{
			findClickableElement(locBtnDeleteView).click();
			if (isDisplayed(locDeleteViewModal))
			{
				while (isDisplayed(locDeleteViewModal))
				{
					;
				}
			}
		}
	}

	@Override
	public void clickCancelOnDeleteViewModal()
	{
		if (isDisplayed(locDeleteViewModal, Speed.slow))
		{
			findClickableElement(locBtnCancelDeleteViewModal).click();
			if (isDisplayed(locDeleteViewModal))
			{
				while (isDisplayed(locDeleteViewModal))
				{
					;
				}
			}
		}
	}

	@Override
	public void clickCloseOnDeleteViewModal()
	{
		if (isDisplayed(locDeleteViewModal, Speed.slow))
		{
			findClickableElement(locBtnCloseDeleteViewModal).click();
			if (isDisplayed(locDeleteViewModal))
			{
				while (isDisplayed(locDeleteViewModal))
				{
					;
				}
			}
		}
	}

	@Override
	public boolean verifyDeleteViewModalMessage(String message)
	{
		if (isDisplayed(locDeleteViewModal, Speed.slow))
		{
			return findVisibleElement(locDeleteViewModalMessage).getText().equalsIgnoreCase(message);
		}
		return false;
	}

	/** Sort view */

	@Override
	public void dragAndDropIsheetViews(String sourceViewName, String destinationViewName)
	{
		findVisibleElement(sortIsheetModalOpened, Speed.slow, 5);
		WebElement sourceViewEle = findClickableElement(By.xpath(".//*[@id='ISHEET_VIEW_SORT_BY_DRAGGING_MODAL']//*[normalize-space(text())='" + sourceViewName.trim() + "']"));
		WebElement destinationViewEle = findClickableElement(By.xpath(".//*[@id='ISHEET_VIEW_SORT_BY_DRAGGING_MODAL']//*[normalize-space(text())='" + destinationViewName.trim() + "']"));
		sortItems(sourceViewEle, destinationViewEle);
	}

	@Override
	public void clickApplyOnSortIsheetViewModal()
	{
		findVisibleElement(locModalSortViewsOpened, Speed.slow, 5);
		WebElement sortViewModalApplyEle = findClickableElement(locBtnApplySortViews);
		sortViewModalApplyEle.click();
	}

	@Override
	public void clickCancelOnSortIsheetViewModal()
	{
		findVisibleElement(locModalSortViewsOpened, Speed.slow, 5);
		WebElement sortViewModalCancelEle = findClickableElement(locBtnCancelSortViews);
		sortViewModalCancelEle.click();
	}

	@Override
	public void clickCloseOnSortIsheetViewModal()
	{
		findVisibleElement(locModalSortViewsOpened, Speed.slow, 5);
		WebElement sortViewModalCloseEle = findClickableElement(locBtnCloseSortViews);
		sortViewModalCloseEle.click();
	}

	@Override
	public boolean verifyView(String viewName)
	{
		By viewXpath = By.xpath("//*[normalize-space(text())='" + viewName.trim() + "']");
		return isDisplayed(viewXpath, Speed.slow);
	}
}
