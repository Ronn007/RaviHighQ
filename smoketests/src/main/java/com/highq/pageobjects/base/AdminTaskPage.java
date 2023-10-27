package com.highq.pageobjects.base;

import java.io.IOException;
import org.openqa.selenium.By;

public interface AdminTaskPage extends BannerPage
{
	public void removeAllTaskLists() throws IOException, InterruptedException;

	public void clickOnDeleteTasklist() throws IOException, InterruptedException;

	public String enterTaskListName(String taskListName);

	public String clickOnAdd() throws IOException, InterruptedException;

	public String getParticularStatusColor(String statusName);

	public String gethashValueOfColor(By xpath, String cssProperty);

	public boolean verifyTaskListVisibility(String taskList);

	public void removeAllStatus() throws IOException, InterruptedException;

	public void clickOnAddForStatus();

	public void editTaskListName(String taskList, String newTaskListName) throws IOException;

	public boolean verifyIsTaskListDuplicated(String taskListName) throws IOException;

	public boolean verifyTaskListNoneExistMsgVisbility() throws IOException;

	public void addStatus(String statusName, int colorIndex);

	public void EditStatus(String statusName, int colorIndex);

	public void saveTaskChanges();

	public void setDefaultView(String view);

	public void setDefaultGrouping(String grouping);

	public void setDefaultSortOrder(String sortOrder);

	public void allowComments(boolean state);

	public void allowPDFExports(boolean state);

	public void deleteAllCompletedTasks();

	public boolean verifyEnableTimelineViewIsDisplayedOrNot();

	public boolean verifyEnableTimelineViewIsSelectedOrNot();

	public void enableTimelineView(boolean requiredState);

	public void selectDefaultViewFromDropDown(String defaultView);

	public boolean verifyDefaultViewFromDropDown(String defaultView);

	public boolean verifyDefaultView(String defaultView);

	public String getDefaultView();

	public void clickOnPermissions(String taskListName) throws IOException;

	public void clickOnRestricted() throws IOException;

	public void setTaskListPermission(String groupName, String permission, boolean checkOrUncheck);

	public boolean verifyCustomStatus(String status);

	public void clickOnSaveButtonInSetPermissionsOfList();

	public boolean verifyDefaultGrouping(String defaultGroup);

}
