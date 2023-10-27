package com.highq.pageobjects.base;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface HomePage extends BannerPage
{

	public void editSiteHomeTitle(String title);

	public boolean verifySiteHomeTitle(String title);

	public void addToFavourites();

	public void selectMoreActionOption(String option);

	public void clickOnEditContentIcon();

	public void addContent(String content);

	public boolean verifyContent(String content);

	@Override
	public void shareViaEmail(String recipientMail, String subject, String message);

	@Override
	public void shareViaMessage(String recipientMail, String message);

	public void clickOnEditIcon();

	public void removeAllSections();

	public void clickonSingleAddPanelIcon();

	public void clickOnAddSection();

	public void clickonPanelLinkOnAddPanelWindow(String panelname);

	public boolean verifyPanelWinodw(String panelname);

	public void addCkEditorPanel(String panelTitle, boolean displayOnMobileDevice);

	public boolean verifyMobileDeviceInCKEditor();

	public boolean verifyPanelTitle(String panelTitle);

	public boolean verifyAddPanelIcon();

	public boolean verifyCKeditor();

	public boolean verifyBackToPanelSectionInModelWindow();

	public boolean verifyMoreActionInAddedPanel(String panelname, String operation);

	public void clickOnSave();

	public void addcontentInCkEditor(String content);

	public boolean verifyContentofCKeditorPanel(String content);

	public void clickOnSourceInCKEditor();

	public void InsertDataInSourceCKEditor(String msg);

	public boolean verifyMessageInCKEditor(String msg);

	public void clickOnClose();

	public void clickOnAdd();

	public void clickOnOkInErrorModal();

	public void addDataInDataVisualisation(Map<String, Object> addDataInDataVisualisation);

	public boolean verifySourceInDataVisualisation(String sourceName);

	public void clickOnCancel();

	public boolean verifyChartType(List<String> charttype, List<String> chartname);

	public boolean verifyDefaultChartTypeName(String optionName);

	public boolean verifyDefaultSource(String optionName);

	public boolean verifyChartPreviewFields();

	public boolean verifySelectButtonInData(String selectButtonName);

	public void clickOnSelectButtonInData(String buttonName);

	public boolean verifySelectModalTitle(String modalTitle);

	public boolean verifyModalWindowField(String modalLabel);

	public boolean verifyListInSelectOneItemModalWindow(String sourceName, List<String> itemList);

	public void clickOnCloseInModalWindow();

	public boolean verifyListInSelectOneOrMoreItemsModalWindow(String sourceName, List<String> itemList);

	public boolean verifyCloseButtonInModal();

	public boolean verifyDoneCancelButtonInModal();

	public void clickOnCancelInModalWindow();

	public void clickOnDoneInModalWindow();

	public boolean verifySearchInSelectOneItemModal(String searchKey);

	public boolean verifySearchInSelectOneOrMoreItemModal(String searchKey, boolean status);

	public boolean verifyClearSearch();

	public void selectOneItemInModalWindow(String sourceName, String itemName);

	public void selectOneOrMoreItemsModalWindow(String sourceName, List<String> itemName, boolean select);

	public void removeToken(String selectButtonName, String itemName);

	public void clickOnTokenDropDown(String selectButtonName, String tokenName);

	public void selectAggregationsFromDropDown(String selectButtonName, String tokenName, String aggregationsName, boolean select);

	public boolean verifyAggregationDropDownFilter(String tokenName, Map<String, Boolean> aggregationsName);

	public boolean verifyAggregationDropDownValue(String tokenName, List<String> aggregationsName);

	public boolean verifyFilterTokenModalField(String tokenName);

	public boolean verifySearchInFilterToken(String searchItem, String tokenName, boolean aggregationsSelected);

	public boolean verifyAggregationToken(String tokenName, String aggregation);

	public void clickOnPreviewButton();

	public boolean verifyMessageInChartPreviewArea(String message);

	public boolean verifyChart(String chartName, Map<String, String> toolTip, int numberOfElementInValue);

	public boolean verifyNoResultsFoundInFilterToken(String searchItem, String tokenName);

	public boolean verifyLegends(String chartName, String legends);

	public void clickOnSelectAllInFilterToken(String tokenName);

	public void clickOnClearFiltersInFilterToken(String tokenName);

	public boolean verifyFavouriteToolTipMessage(String toolTipMsg1);

	public void clickOnFavouriteIcon();

	public void clickOnCancelInSelectModalWindow();

	public boolean verifyPreviewButtonsInDisabledMode();

	public boolean verifyErrorMessageInDashBoard(String panelName, String message);

	public void clickOnMultiplePanelIcon(int position);

	public void clickOnMoreActionOption(String panelTitle, String option);

	public void clickOnLegends(String dashBoardOrchartPreview, String chartName, String legends);

	public boolean verifyChartForLegends(String dashBoardOrchartPreview, String chartName, List<Boolean> chartStatus);

	public void clickOnSaveInModal();

	public boolean verifySaveButtonsInDisabledMode();

	boolean verifyDashboardTitle(String title);

	boolean verifyMailWithHomeModuleLink(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage, String appURL);

	void enterDashboardTitle(String title);

	void addcontentInCkEditor(String panelName, String content);

	boolean verifyContentofCKeditorPanel(String panelName, String content);

	void selectMoreActionOptionOfPanel(String panelName, String option);

	void clickOnSaveOnEditPanelModel();

	void addLinkInCkEditor(String panelName, String linkURL);

	boolean verifyLinkInCKEditor(String contentPanelTitle, String linkURL);

	void addMacroInCKEditor(String panelName, String macroName);

	boolean verifyMacroInCKEditor(String contentPanelTitle, String macroName);

	void editMacroInCKEditor(String contentPanelTitle, String macroName);

	boolean verifyFavourite();

	boolean verifyFavouriteTooltip(String favoriteTooltip);

	void removeFromFavourites();

	void clickOnCloseButtonOfPrintPreview();

	boolean verifyDashboardTitleinPrintPreview(String title);

	boolean verifyContentofCKeditorPanelInPrintPreview(String panelName, String content);

	boolean verifyLinkInCKEditorInPrintPreview(String contentPanelTitle, String linkURL);

	boolean verifyMacroInCKEditorInPrintPreview(String contentPanelTitle, String macroName);

	public void gotoAddChartTitle(String chartTitle);

	public boolean verifyChartVisibility(String chartTitle);

	public boolean verifyOptionsOfViewDropdown(String noneSelected);

	boolean verifyToken(String selectButtonName, String tokenName, String aggregationsName);

	public boolean verifyCustomiseButtonInDisabledMode();

	public void clickOnApplyButton();

	public void clickOnCustomizeButton();

	public void clickOnDisplayLegendInCustomise(String legendPlace);

	public void clickOnData();

	public boolean verifyView(String viewName);

	public boolean verifyDefaultView(String viewName);

	public boolean verifyDefaultISheet(String isheetName);

	public boolean verifyDefaultSite(String siteName);

	public boolean verifyViewButtonInDisabledMode();
}
