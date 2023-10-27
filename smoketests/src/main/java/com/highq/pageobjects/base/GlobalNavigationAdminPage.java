package com.highq.pageobjects.base;

public interface GlobalNavigationAdminPage extends BannerPage
{
	public void selectGlobalNavigationOption(String option);

	public void clickOnMenuItemDropDown();

	public void selectMenuItemOption(String option);

	public void clickExternalOnAddMenuItemDialog();

	public void addMenuItemExternalTitle(String title);

	public void addMenuItemExternalURL(String url);

	public void addMenuItemExternalSelectOpenInOption(String option);

	public void clickAddOnAddMenuItemDialog();

	public void addExternalMenuItem(String title, String url, String openIn);

	public void clickSaveOnGlobalNavigation();

	public void deleteMenuItem(String menuItem);

	public void enterContainerTitle(String containerTitle);

	public void setColumnNumber(int columnNumber);

	public void clickAddOnAddContainer();

	public void addContainer(String containerName, int noOfColumns);

	public boolean menuItemPresent(String menuItem);

	public void clickCustomOnAddMenuItemDialog();

	public void clickAddNewContainerLink();

	public void setContainerForCustomMenuItem(String containerName);

	public void enterCustomMenuItemTitle(String title);

	public void enterCustomMenuItemContent(String content);

	public void addCustomMenuItem(String containerName, String customMenuTitle, String customMenuContent);

	boolean verifyGolbalNavigationLabel();

	boolean verifyOptionOnGolbalNavigationDropdown(String option);

	void clickOnGlobalNavigationDropdown();

	boolean verifyMenuItemLabel();

	boolean verifyMenuItemOption(String option);

	boolean verifyDragAndDropMessage();

	boolean verifyNoMenuFoundMessage();

	boolean verifyTopSaveButton();

	boolean verifyBottomSaveButton();

	boolean verifyTopPreviewButton();

	boolean verifyBottomPreviewButton();

	boolean verifyTopCancelButton();

	boolean verifyBottomCancelButton();

}
