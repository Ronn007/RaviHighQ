package com.highq.pageobjects.pages;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminBlogPage;

/**
 * @author nidhi.shah
 */
public class AdminBlogWeb extends AdminPageWeb implements AdminBlogPage
{
	public AdminBlogWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	WebDriver driver;

	// ***************************************** Blog Options ************************************************

	By saveButton = By.id("adminBlogSaveBtnBottom");// By.xpath("(.//*[@id='categoryListTable']//preceding::a[text()='Save'])[1]");

	// *************************************** Blog Categories ***********************************************

	By addCategoryTextBox = By.id("addCategoryValue");

	By addButton = By.linkText("Add");

	// Remove

	By deleteAllItemsButton = By.xpath("(//*[normalize-space(.)='Delete all items'])[last()]");// By.xpath(".//a[normilize-space(.)='Delete all items']");

	By removeCategoryTargetID = By.id("cmbTargetCategoryID");

	By removeCategoryCloseLink = By.id("siteAdmin_module_removecategory_modal_MAIN_CLOSE_BUTTON");// By.xpath(".//*[@id='removeCatgegoryDialog']//*[@class='close']");

	By removeCategoryCancelLink = By.id("siteAdmin_module_removecategory_modal_CANCEL_BTN");// By.xpath(".//*[@id='removeCatgegoryDialog']//*[text()='Cancel']");

	By removeCategorySaveButton = By.id("siteAdmin_module_removecategory_DeleteItems_modal_deleteBtnID");// By.xpath(".//*[@id='removeCatgegoryDialog']//*[text()='Save']");

	// Permissions

	By defaultPermissionRadioButton = By.id("permissionTypeDefault");

	By restrictedPermissionRadioButton = By.id("permissionTypeRestricted");

	By categoryPermissionSaveLink = By.id("catgegoryPermissionModal_saveCategoryPermission");// By.xpath(".//*[@id='catgegoryPermission']//*[text()='Save']");

	By categoryPermissionCancelLink = By.id("catgegoryPermissionModal_cancelCategoryPermission");// By.xpath(".//*[@id='catgegoryPermission']//*[text()='Cancel']");

	By categoryPermissionCloseLink = By.id("catgegoryPermissionModal_MAIN_CLOSE_BUTTON");// By.xpath(".//*[@id='catgegoryPermissionModal']//*[@class='close']");

	// *************************************** Set Notifications ***********************************************

	By setNotificationsLink = By.xpath("(//*[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION + "'])[1]");

	By notificationCheckBox = By.xpath(".//input[@name='site.blogApprovalAlertToAll']");

	By setNotificationButton = By.id("siteAdmin_module_setNotification_modal_setNotificationBtnID");

	By setNotificationCancelButton = By.id("siteAdmin_module_setNotification_modal_cancelLinkBtnID");

	By setNotificationCloseLink = By.xpath("siteAdmin_module_setNotification_modal_closeBtnID");

	By notification_selectAllLink = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_SELECTALL + "']");
	By notification_clearAllLink = By.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_CLEARALL + "']");

	// *************************************** Basic Operations on Elements ***********************************************

	By categorySetPermissionLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_SETPERMISSION + "']");
	By categoryRenameLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_RENAME + "']");
	By renameCategoryInput = By.id("griffin_categoryName");
	By renameCategory_RenameButton = By.id("siteAdmin_module_renameCategory_modal_renameBtnID");
	By renameCategory_CancelButton = By.id("siteAdmin_module_renameCategory_modal_cancelLinkBtnID");
	By categoryRemoveLink = By.xpath("//*[@aria-expanded='true']/following-sibling::*[@role='menu']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_REMOVE + "']");

	/** Week starts on */
	By remove_NewCategoryDropDown = By.xpath("//*[@data-id='cmbTargetCategoryID']");
	By remove_NewCategoryComboBox = By.xpath("//*[@class='dropdown-menu open']");

	By categoryList = By.className("list-group");
	String remove_NewCategoryList = "//*[@class='dropdown-menu open']//li";

	By allowCommentsCheckBox = By.name("site.allowBlogComment");

	By allowPDFExportCheckBox = By.name("site.allowBlogPDFExport");

	/**
	 * @modified by vivek.mishra
	 * @modified on 07/03/2018
	 */
	public void clickOnSave()
	{
		WebElement saveButtonEle = findVisibleElement(saveButton, Speed.slow);
		saveButtonEle.click();
	}

	@Override
	public void clickOnAddButton()
	{
		WebElement addButtonEle = findClickableElement(addButton);
		addButtonEle.click();
	}

	@Override
	public void clickCancelOnRemoveCategory()
	{
		WebElement removeCategoryCancelEle = findClickableElement(removeCategoryCancelLink);
		removeCategoryCancelEle.click();
	}

	@Override
	public void clickOnCloseOnRemoveCategory()
	{
		WebElement removeCategoryCloseEle = findClickableElement(removeCategoryCloseLink);
		removeCategoryCloseEle.click();
	}

	@Override
	public void clickOnSaveOnRemoveCategory()
	{
		WebElement removeCategorySaveEle = findClickableElement(removeCategorySaveButton);
		removeCategorySaveEle.click();
	}

	@Override
	public void clickCancelOnCategoryPermission()
	{
		WebElement categoryPermissionCancelEle = findClickableElement(categoryPermissionCancelLink);
		categoryPermissionCancelEle.click();
	}

	@Override
	public void clickOnCloseOnCategoryPermission()
	{
		WebElement categoryPermissionCloseEle = findClickableElement(categoryPermissionCloseLink);
		categoryPermissionCloseEle.click();
	}

	@Override
	public void clickOnSaveOnCategoryPermission()
	{
		WebElement categoryPermissionSaveEle = findClickableElement(categoryPermissionSaveLink);
		categoryPermissionSaveEle.click();
	}

	public void clickOnCategoryPermission(String category)
	{
		WebElement categoryNameEle = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*/*[@data-original-title='More actions']"));
		categoryNameEle.click();
		findVisibleElement(categorySetPermissionLink).click();
	}

	// *************************************** Functional Operations ***********************************************

	@Override
	public void setBlogOptions(String option, boolean value)
	{
		WebElement blogOptionsEle = findClickableElement(By.xpath("//*[normalize-space(.)='" + option.trim() + "']//preceding-sibling::*[@type='checkbox']"));
		Boolean currentStatus = blogOptionsEle.isSelected();
		if (currentStatus != value)
		{
			blogOptionsEle.click();
		}
	}

	/**
	 * @modified by vivek.mishra
	 * @modified on 07/03/2018
	 */
	public void addCategory(String categoty)
	{
		WebElement addCategoryEle = findVisibleElement(addCategoryTextBox, Speed.slow);
		addCategoryEle.sendKeys(categoty.trim());
		addCategoryEle.sendKeys(Keys.ENTER);
	}

	@Override
	public void renameCategory(String category, String newCategory)
	{
		if (isDisplayed(By.xpath("//*[@id='categoryListTable']//*[text()='" + category.trim() + "']")))
		{
			WebElement moreOpt = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*/*[@data-original-title='More actions']"));
			moreOpt.click();
			findVisibleElement(categoryRenameLink).click();

			findVisibleElement(modalContent);
			WebElement categoryNameEle = findClickableElement(renameCategoryInput);
			categoryNameEle.clear();
			categoryNameEle.sendKeys(newCategory.trim());

			WebElement categorySaveEle = findClickableElement(renameCategory_RenameButton);
			categorySaveEle.click();
		}
		else
		{
			System.out.println("Category is not available");
		}
	}

	@Override
	public void editCategoryCancel(String category, String newCategory)
	{
		if (isDisplayed(By.xpath(".//span[text()='" + category + "']")))
		{
			WebElement editCategoryEle = findClickableElement(By.xpath(".//span[text()='" + category + "']//following::a[text()='Edit'][1]"));
			editCategoryEle.click();

			WebElement categoryNameEle = findClickableElement(By.xpath(".//input[@value='" + category + "']"));
			categoryNameEle.clear();
			categoryNameEle.sendKeys(newCategory);

			WebElement categorySaveEle = findClickableElement(By.xpath(".//input[@value='" + category + "']//following::a[text()='Cancel'][1]"));
			categorySaveEle.click();
		}
		else
		{
			System.out.println("Category is not available");
		}
	}

	@Override
	public void removeCategory(String category)
	{
		WebElement moreOpt = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + category.trim() + "']//preceding-sibling::*/*[@data-original-title='More actions']"));
		moreOpt.click();
		findVisibleElement(categoryRemoveLink).click();
		if (isDisplayed(deleteAllItemsButton))
		{
			findClickableElement(deleteAllItemsButton, Speed.slow).click();
		}
	}

	@Override
	public void setBlogDefaultPermissions(String category)
	{
		openCategoryMoreOption(category);
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

	@Override
	public void setBlogRestrictedPermission(String category, Map<String, Map<String, Boolean>> permissions) throws InterruptedException
	{
		openCategoryMoreOption(category);
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

	public void clickCancelInRenameModal()
	{
		findVisibleElement(renameCategory_CancelButton).click();
	}

	public void selectNewCategoryFromRemoveCategory(String categoryName)
	{
		selectOptionFromDropDown(remove_NewCategoryDropDown, remove_NewCategoryComboBox, remove_NewCategoryList, categoryName);
	}

	public void openCategoryMoreOption(String categoryName)
	{
		WebElement moreOpt = findClickableElement(By.xpath(".//*[@id='categoryListTable']//*[normalize-space(text())='" + categoryName.trim() + "']//preceding-sibling::*/*[@data-original-title='More actions']"));
		moreOpt.click();
	}

	/**
	 * @author vivek.mishra
	 * @param categoryName
	 * @return
	 * @created on 07/03/2018
	 */
	public boolean verifyCategory(String categoryName)
	{
		return (isDisplayed(By.xpath("//*[contains(@id,'categoryLabel') and  normalize-space(text())='" + categoryName.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the category list availability
	 * @created on 07/03/2018
	 */
	public boolean verifyCategoryList()
	{
		findVisibleElement(categoryList, Speed.slow);
		return (isDisplayed(categoryList, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To select allow comments check box
	 * @created on 15/03/2018
	 */
	public void selectAllowCommentsCheckBox()
	{
		WebElement checkBox = findVisibleElement(allowCommentsCheckBox, Speed.slow);
		if (!checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 *         To select allow pdf export check box
	 * @created on 15/03/2018
	 */
	public void selectAllowPDFExportCheckBox()
	{
		WebElement checkBox = findVisibleElement(allowPDFExportCheckBox, Speed.slow);
		if (!checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 *         To deSelect allow comments check box
	 * @created on 15/03/2018
	 */
	public void deSelectAllowCommentsCheckBox()
	{
		WebElement checkBox = findVisibleElement(allowCommentsCheckBox, Speed.slow);
		if (checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 *         To deSelect allow pdf export check box
	 * @created on 15/03/2018
	 */
	public void deSelectAllowPDFExportCheckBox()
	{
		WebElement checkBox = findVisibleElement(allowPDFExportCheckBox, Speed.slow);
		if (checkBox.isSelected())
			checkBox.click();
	}
}
