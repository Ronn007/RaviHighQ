package com.highq.pageobjects.pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pageobjects.base.AdminTaskPage;

public class AdminTaskPageWeb extends AdminPageWeb implements AdminTaskPage
{
	WebDriver driver;
	By taskListTable = By.id("taskListTable");
	private String taskList = ".//*[@id='taskListTable']";
	By modalContent = By.xpath("(//*[@class='modal-content'])[last()]");
	String statusRemoveModal = ".//*[@id='taskRemoveStatus']";
	By addtaskListValue = By.id("addTaskListValue");
	By taskRemoveModal = By.xpath(".//*[@id='TaskRemoveModal' and @aria-hidden='false']");
	By addButton = By.id("siteadmin_task_category_add_id");
	String taskCustomStatusTable = ".//*[@id='taskCustomStatusTable']//li";

	/** TaskList */
	String totalTaskLists = "//*[@id='taskListTable']//*[contains(@class,'list-group-item')]";
	By removeOption = By
			.xpath("//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVE + "']");
	By removeListBoxTitle = By
			.xpath("//*[contains(text(),'" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVELIST + "')]");
	By rmvList_newListDropDown = By.id("removeTaskModalSiteList");
	By rmvList_dltAllItems = By
			.xpath("(//*[contains(@id,'deleteAllBtnID') or contains(@id,'deleteAllLinkBtnID')])[last()]");
	By removeButton = By
			.xpath("(//button[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVE + "'])[last()]");
	By totalTaskListSpans = By.xpath(".//*[@id='taskListTable']//span");

	/** Task Status */
	By removeStatusBoxTitle = By
			.xpath("//*[contains(text(),'" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVETASKSTATUS + "')]");
	By addTaskStatusButton = By.xpath("//*[contains(text(),'"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVECUSTOMSTATUSES + "')]/following::*[contains(text(),'"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVECUSTOMSTATUSES_ADD + "')][1]");
	By taskCustomStatusNameValue = By
			.xpath(".//*[@id='addtaskCustomStatusValue' or @id='addTaskModuleCustomStatusValue']");
	By colorSelect = By.xpath(
			".//*[@id='frmTaskCustomStatusAddEditDialogPage' or @id='task_status_edit']//*[contains(@class,'colorSelect')]");

	By taskListNameRequiredMsg = By.xpath("//*[@id='addTaskListValue_pID']");
	By renameOptionXpath = By
			.xpath("//*[normalize-space(.)='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_RENAME + "']");
	By renameTaskListName = By.id("renameTaskListID");
	By renameTaskListButton = By.id("siteAdmin_module_renameTaskList_modal_renameBtnID");
	By taskListExistMessage = By
			.xpath(".//p[text()='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_LISTALREADYEXISTSMESSAGE + "']");

	By modalAddButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_MODALADD + "']");
	By modalSaveButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_MODALSAVE + "']");
	By modalDeleteButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_MODALDELETE + "']");
	By statusDropDownEditLink = By.xpath("(//*[contains(@class,'open')])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_MODALEDIT + "']");
	By statusDropDownRemoveLink = By.xpath("(//*[contains(@class,'open')])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_MODALREMOVE + "']");

	By saveButtonBottom = By.id("adminTaskSaveBtnBottom");

	/** Default View */
	By tasks_DefaultViewDropDown = By.xpath("//*[@data-id='siteAdmin_task_taskViewByID']");
	By tasks_DefaultViewComboBox = By
			.xpath("//*[@data-id='siteAdmin_task_taskViewByID']/following-sibling::*[@role='combobox']");
	String tasks_DefaultViewList = "//*[@data-id='siteAdmin_task_taskViewByID']/following-sibling::*[@role='combobox']//li";

	/** Default Grouping */
	By tasks_DefaultGroupingDropDown = By.xpath("//*[@data-id='siteAdmin_task_taskGroupByOptionID']");
	By tasks_DefaultGroupingComboBox = By
			.xpath("//*[@data-id='siteAdmin_task_taskGroupByOptionID']/following-sibling::*[@role='combobox']");
	String tasks_DefaultGroupingList = "//*[@data-id='siteAdmin_task_taskGroupByOptionID']/following-sibling::*[@role='combobox']//li";

	/** Default sort order */
	By tasks_DefaultSortOrderDropDown = By.xpath("//*[@data-id='siteAdmin_task_taskSortOrderByID']");
	By tasks_DefaultSortOrderComboBox = By
			.xpath("//*[@data-id='siteAdmin_task_taskSortOrderByID']/following-sibling::*[@role='combobox']");
	String tasks_DefaultSortOrderList = "//*[@data-id='siteAdmin_task_taskSortOrderByID']/following-sibling::*[@role='combobox']//li";

	By allowTaskCommentsCheckbox = By.id("allowTaskComment");
	By allowPDFExportCheckbox = By.id("allowTaskPDFExport");
	By deleteAllCompletedTasksLink = By.xpath("//*[contains(@onclick,'openDeleteAllCompletedTaskModal')]");

	By deleteAllCompletedTaskMessage = By.xpath("//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_DELETEALLCOMPLTEDTASKMESSAGE + "']");
	By taskList_DeleteAllItemsButton = By.id("siteAdmin_module_removeTaskList_modal_deleteAllLinkBtnID");
	By taskList_RemoveButton = By.id("siteAdmin_module_removeTaskList_modal_removeTaskListBtnID");
	By customStatus_DeleteAllItemsButton = By.id("siteAdmin_task_removeCustomstatus_modal_deleteAllBtnID");
	By customStatus_RemoveButton = By.id("siteAdmin_task_removeCustomstatus_modal_removeTaskCustomstatusBtnID");

	Actions build;
	protected JavascriptExecutor jse;

	By enableTaskTimelineView = By
			.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITE_ADMIN_LABEL_TASKTIMELINEVIEWENABLE + "']");

	By enableTimelineViewCheckBox = By.id("enableTimelineView");

	By defaultViewDropDown = By.xpath("//*[@data-id='siteAdmin_task_taskViewByID']");
	By defaultViewComboBox = By
			.xpath("//*[@data-id='siteAdmin_task_taskViewByID']/following-sibling::*[@role='combobox']");
	String defaultViewList = "//*[@data-id='siteAdmin_task_taskViewByID']/following-sibling::*[@role='combobox']//li";

	By defaultViewName = By.xpath("//*[@data-id='siteAdmin_task_taskViewByID']/*[contains(@class,'filter-option')]");

	By permissionTypeRestricted = By.id("permissionTypeRestricted");

	By categoryPermissionModal = By.xpath(".//*[@id='catgegoryPermissionModal' and contains(@class,'fade in')]");

	By saveButtonInPermissionOfListModal = By.id("catgegoryPermissionModal_saveCategoryPermission");
	By totalList = By.xpath("*//span[contains(@id,'taskListLabel')]");

	public AdminTaskPageWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	@Override
	public void removeAllTaskLists() throws IOException, InterruptedException
	{
		findVisibleElement(addtaskListValue);
		List<WebElement> taskLists = driver.findElements(By.xpath(totalTaskLists));
		if (taskLists.size() > 0)
		{
			for (int i = 1; i <= taskLists.size(); i++)
			{
				By myEle = By.xpath(totalTaskLists + "[1]/span[1]");
				findVisibleElement(myEle, Speed.slow).click();
				findClickableElement(By.xpath("//*[@id='taskListTable']//*[contains(@class,'list-group-item')][1]//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_REMOVE + "']")).click();
				findVisibleElement(removeListBoxTitle);
				if (isDisplayed(taskList_DeleteAllItemsButton))
				{
					findVisibleElement(taskList_DeleteAllItemsButton).click();
				}
				else
				{
					findVisibleElement(taskList_RemoveButton).click();
				}
				scrollToTop();
			}
		}
		else
		{
			System.out.println("No task list is available");
		}
	}

	@Override
	public void clickOnDeleteTasklist() throws IOException, InterruptedException
	{
		findVisibleElement(removeButton).click();
	}

	@Override
	public String enterTaskListName(String taskListName)
	{
		try
		{
			WebElement elem = findVisibleElement(addtaskListValue, 30, 200);
			moveToElement(addtaskListValue);
			elem.clear();
			elem.sendKeys(taskListName);
			elem.sendKeys(Keys.ENTER);

			if (isDisplayed(taskListNameRequiredMsg))
			{
				String taskListNameRequiredMessage;
				WebElement errorMessage = findVisibleElement(taskListNameRequiredMsg);
				taskListNameRequiredMessage = errorMessage.getText().trim();
				return taskListNameRequiredMessage;
			}
			else
			{
				verifyTaskListVisibility(taskListName);
				return "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String clickOnAdd() throws IOException, InterruptedException
	{
		moveToElement(addButton);
		findClickableElement(addButton).click();
		try
		{
			Alert alert = driver.switchTo().alert();
			String popupMsg = alert.getText().trim();
			alert.accept();
			driver.switchTo().defaultContent();
			return popupMsg;
		}
		catch (Exception e)
		{
			System.out.println("Error : " + e);
			return "";
		}
	}

	@Override
	public String getParticularStatusColor(String statusName)
	{
		By status = By.xpath(".//*[@id='taskCustomStatusTable']//li//span[text()='" + statusName.trim() + "']");
		WebElement requiredStatus = findPresentElement(status);
		String statusData = requiredStatus.getText();
		if (statusData.equals(statusName))
		{
			return gethashValueOfColor(By.xpath(".//*[@id='taskCustomStatusTable']//li//span[text()='"
					+ statusName.trim() + "']//preceding-sibling::span[@class='categoryColor']"), "background-color");
		}
		return null;
	}

	@Override
	public String gethashValueOfColor(By xpath, String cssProperty)
	{
		String color = driver.findElement(xpath).getCssValue(cssProperty);
		System.out.println(color);
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		System.out.println(actualColor);
		return actualColor;
	}

	@Override
	public boolean verifyTaskListVisibility(String taskList)
	{
		// findPresentElement(taskListTable);
		return isDisplayed(By.xpath("(.//*[@id='taskListTable']//*[normalize-space(text())='" + taskList.trim() + "'])[last()]"));
	}

	@Override
	public void removeAllStatus() throws IOException, InterruptedException
	{
		By statusTable = By.xpath(taskCustomStatusTable);
		findVisibleElement(statusTable);
		List<WebElement> allStatus = driver.findElements(statusTable);
		if (allStatus.size() > 0)
		{

			for (int i = 4; i <= allStatus.size(); i++)
			{
				By myEle = By.xpath(taskCustomStatusTable + "[" + i + "]/span[1]");
				if (isDisplayed(myEle))
				{
					scrollToBottom();
					findClickableElement(myEle, Speed.slow).click();
					findClickableElement(statusDropDownRemoveLink).click();
					findVisibleElement(removeStatusBoxTitle);
					if (isDisplayed(customStatus_DeleteAllItemsButton))
					{
						findVisibleElement(customStatus_DeleteAllItemsButton).click();
					}
					else
					{
						findVisibleElement(customStatus_RemoveButton).click();
					}
					scrollToTop();
				}
				else
				{
					System.out.println("Only [Default] status are available : No additional Status available.");
					break;
				}

			}
		}
	}

	@Override
	public void clickOnAddForStatus()
	{
		findClickableElement(addTaskStatusButton, Speed.slow, 10).click();
	}

	@Override
	public void editTaskListName(String taskList, String newTaskListName) throws IOException
	{
		// WebElement taskListEdit = findClickableElement(By.xpath("(//span[text()='" +
		// taskList + "']//following::a[text()='Edit'])[1]"));
		String taskMoreAction = "//*[@id='taskListTable']//*[normalize-space(text())='" + taskList.trim()
				+ "']//preceding::*[@data-original-title='More actions'][1]";
		WebElement taskListEdit = findClickableElement(By.xpath(taskMoreAction));
		taskListEdit.click();

		By renameXpath = By.xpath(taskMoreAction + "/following-sibling::*//*[normalize-space(text())='"
				+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_TASK_RENAME + "']");
		WebElement renameOption = findClickableElement(renameXpath, Speed.slow);
		renameOption.click();

		WebElement editTaskListName = findClickableElement(renameTaskListName);
		editTaskListName.clear();
		editTaskListName.sendKeys(newTaskListName.trim());

		WebElement renameBtn = findClickableElement(renameTaskListButton);
		renameBtn.click();
	}

	@Override
	public boolean verifyIsTaskListDuplicated(String taskListName) throws IOException
	{
		String xpathTaskListName = "//*[@id='taskListTable']//*[normalize-space(text())='" + taskListName.trim() + "']";
		try
		{
			List<WebElement> allTaskLists = driver.findElements(By.xpath(xpathTaskListName));
			if (allTaskLists.size() > 1)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getCause());
		}

		return false;
	}

	@Override
	public boolean verifyTaskListNoneExistMsgVisbility() throws IOException
	{
		return isDisplayed(taskListExistMessage);
	}

	@Override
	public void addStatus(String statusName, int colorIndex)
	{
		findVisibleElement(addTaskStatusButton).click();
		findVisibleElement(modalContent);
		findVisibleElement(taskCustomStatusNameValue).sendKeys(statusName.trim());
		if (colorIndex != 0)
		{
			findClickableElement(colorSelect).click();
			findClickableElement(
					By.xpath(".//*[@id='customColorLI' or @id='taskCustomColorLI']/span[" + colorIndex + "]")).click();
		}
		findClickableElement(modalAddButton).click();
	}

	@Override
	public void EditStatus(String statusName, int colorIndex)
	{
		By moreAction = By.xpath(".//*[@id='taskCustomStatusTable']//*[normalize-space(text())='" + statusName.trim()
				+ "']/preceding-sibling::*[2]");
		findVisibleElement(moreAction).click();
		findVisibleElement(statusDropDownEditLink).click();
		findVisibleElement(modalContent);
		findVisibleElement(taskCustomStatusNameValue).sendKeys(statusName.trim());
		if (colorIndex != 0)
		{
			findClickableElement(colorSelect).click();
			findClickableElement(
					By.xpath(".//*[@id='customColorLI' or @id='taskCustomColorLI']/span[" + colorIndex + "]")).click();
		}
		findClickableElement(modalSaveButton).click();
	}

	@Override
	public void saveTaskChanges()
	{
		findVisibleElement(saveButtonBottom).click();
		findVisibleElement(globalMessageBar, Speed.slow);
	}

	@Override
	public void setDefaultView(String view)
	{
		selectOptionFromDropDown(tasks_DefaultViewDropDown, tasks_DefaultViewComboBox, tasks_DefaultViewList, view);
	}

	@Override
	public void setDefaultGrouping(String grouping)
	{
		selectOptionFromDropDown(tasks_DefaultGroupingDropDown, tasks_DefaultGroupingComboBox,
				tasks_DefaultGroupingList, grouping);
	}

	@Override
	public void setDefaultSortOrder(String sortOrder)
	{
		selectOptionFromDropDown(tasks_DefaultSortOrderDropDown, tasks_DefaultSortOrderComboBox,
				tasks_DefaultSortOrderList, sortOrder);
	}

	@Override
	public void allowComments(boolean state)
	{
		setSelection(allowTaskCommentsCheckbox, state);
	}

	@Override
	public void allowPDFExports(boolean state)
	{
		setSelection(allowPDFExportCheckbox, state);
	}

	@Override
	public void deleteAllCompletedTasks()
	{
		findVisibleElement(deleteAllCompletedTasksLink).click();
		findPresentElement(modalContent);
		findVisibleElement(deleteAllCompletedTaskMessage);
		findVisibleElement(modalDeleteButton).click();
		findPresentElement(globalMessageBar);
	}

	/**
	 * @author jyoti.raj Created : 2nd April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyEnableTimelineViewIsDisplayedOrNot()
	{
		return isDisplayed(enableTaskTimelineView, Speed.slow);
	}

	/**
	 * Verify custom status is present or not
	 *
	 * @param status
	 *        name of the status
	 * @return true
	 *         if found
	 * @author dheeraj.rajput
	 * @Created: 02 April 2018
	 * @Updated:
	 */
	@Override
	public boolean verifyCustomStatus(String status)
	{
		By statusXpath = By.xpath(".//*[@id='taskCustomStatusTable']//*[normalize-space(text())='" + status.trim() + "']");
		return isDisplayed(statusXpath);
	}

	/**
	 * @author jyoti.raj Created : 2nd April 2018 Updated :
	 * @return
	 */
	@Override
	public boolean verifyEnableTimelineViewIsSelectedOrNot()
	{
		return isSelected(enableTimelineViewCheckBox);
	}

	/**
	 * @author jyoti.raj Created : 3rd April 2018 Updated :
	 */
	@Override
	public void enableTimelineView(boolean requiredState)
	{
		setSelection(enableTimelineViewCheckBox, requiredState);
	}

	/**
	 * @author jyoti.raj Created : 5th April 2018 Updated :
	 * @param defaultView
	 */
	@Override
	public void selectDefaultViewFromDropDown(String defaultView)
	{
		selectOptionFromDropDown(defaultViewDropDown, defaultViewComboBox, defaultViewList, defaultView.trim());
	}

	/**
	 * @author jyoti.raj Created : 5th April 2018 Updated :
	 * @param defaultView
	 * @return
	 */
	@Override
	public boolean verifyDefaultViewFromDropDown(String defaultView)
	{
		return verifyOptionFromDropDown(defaultViewDropDown, defaultViewComboBox, defaultViewList, defaultView.trim());
	}

	/**
	 * @author jyoti.raj Created : 5th April 2018 Updated :
	 * @param defaultView
	 * @return
	 */
	@Override
	public boolean verifyDefaultView(String defaultView)
	{
		String view = findVisibleElement(defaultViewName).getText();
		return view.trim().equals(defaultView.trim());
	}

	/**
	 * In default view its shows list view and in audit details it shows column view
	 * so will update the method when the bug will resolved
	 *
	 * @author jyoti.raj Created : 6th April 2018 Updated :
	 * @return
	 */
	@Override
	public String getDefaultView()
	{
		String view = findVisibleElement(defaultViewName).getText();
		if (view.equals(TaskLabels.TASKS_COLUMNVIEW))
		{
			view = "Column view";
		}
		return view;
	}

	/**
	 * @author jyoti.raj
	 *         Created : 30th April 2018
	 *         Updated :
	 * @param taskListName
	 * @throws IOException
	 */
	@Override
	public void clickOnPermissions(String taskListName) throws IOException
	{
		findVisibleElement(By.xpath(taskList + "/div"));

		List<WebElement> total = driver.findElements(totalList);
		for (int i = 1; i <= total.size(); i++)
		{

			if (getText(By.xpath("*//li[" + i + "]//span[contains(@id,'taskListLabel')]")).equals(taskListName))
			{
				findVisibleElement(By.xpath(taskList + "//li[" + i + "]//a[@class='icon icon-actions']")).click();
				findVisibleElement(By.xpath(taskList + "//li[" + i + "]//li[2]//a[text()='" + TaskLabels.TASK_TASKLIST_SETPERMISSION + "']")).click();
				findVisibleElement(categoryPermissionModal, Speed.slow);
			}
		}

	}

	/**
	 * @author jyoti.raj
	 *         Created : 30th April 2018
	 *         Updated :
	 * @throws IOException
	 */
	@Override
	public void clickOnRestricted() throws IOException
	{
		findVisibleElement(categoryPermissionModal, Speed.slow);
		if (!isSelected(permissionTypeRestricted))
		{
			findVisibleElement(permissionTypeRestricted, Speed.slow).click();
		}
	}

	/**
	 * @author jyoti.raj
	 *         Created : 30th April 2018
	 *         Updated :
	 * @param groupName
	 * @param permission
	 * @param checkOrUncheck
	 */
	@Override
	public void setTaskListPermission(String groupName, String permission, boolean checkOrUncheck)
	{
		findVisibleElement(categoryPermissionModal, Speed.slow);
		String xpathPermissionChkbox = "*//input[@title='" + permission + ", " + groupName + "']";
		try
		{
			boolean flag = findVisibleElement(By.xpath(xpathPermissionChkbox)).isSelected();
			if (checkOrUncheck && !flag)
			{
				findVisibleElement(By.xpath(xpathPermissionChkbox)).click();
			}
			else if (!checkOrUncheck && flag)
			{
				findVisibleElement(By.xpath(xpathPermissionChkbox)).click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getCause());
		}
	}

	/**
	 * @author jyoti.raj
	 *         Created : 30th April 2018
	 *         Updated :
	 */
	@Override
	public void clickOnSaveButtonInSetPermissionsOfList()
	{
		findVisibleElement(categoryPermissionModal, Speed.slow);
		findVisibleElement(saveButtonInPermissionOfListModal).click();
	}

	/**
	 * @author jhanvi.dave
	 *         Created : 15th May 2018
	 */
	public boolean verifyDefaultGrouping(String defaultGroup)
	{
		return verifyOptionFromDropDown(tasks_DefaultGroupingDropDown, tasks_DefaultGroupingComboBox, tasks_DefaultGroupingList, defaultGroup);
	}
}
