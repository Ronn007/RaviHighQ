package com.highq.pageobjects.base;

import java.util.Map;

public interface AdminBlogPage extends BannerPage
{
	// *************************************** Basic Operations on Elements ***********************************************

	public void clickOnSave();

	public void clickOnAddButton();

	public void clickCancelOnRemoveCategory();

	public void clickOnCloseOnRemoveCategory();

	public void clickOnSaveOnRemoveCategory();

	public void clickCancelOnCategoryPermission();

	public void clickOnCloseOnCategoryPermission();

	public void clickOnSaveOnCategoryPermission();

	// *************************************** Functional Operations ***********************************************

	public void setBlogOptions(String option, boolean value);

	public void addCategory(String categoty);

	public void renameCategory(String category, String newCategory);

	public void editCategoryCancel(String category, String newCategory);

	public void removeCategory(String category);

	public void setBlogDefaultPermissions(String category);

	public void setBlogRestrictedPermission(String category, Map<String, Map<String, Boolean>> groupPermissions) throws InterruptedException;

	public void clickOnSetNotificatons();

	public void clickCancelInRenameModal();

	public void selectNewCategoryFromRemoveCategory(String categoryName);

	public void openCategoryMoreOption(String categoryName);

	public boolean verifyCategory(String categoryName);

	public boolean verifyCategoryList();

	public void deSelectAllowPDFExportCheckBox();

	public void deSelectAllowCommentsCheckBox();

	public void selectAllowPDFExportCheckBox();

	public void selectAllowCommentsCheckBox();

}
