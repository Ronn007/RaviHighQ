/**
 * 
 */
package com.highq.pageobjects.base;

import java.util.Map;
import org.openqa.selenium.By;

/**
 * @author vivek.mishra
 */
public interface AdminQAPermissionsPage extends BannerPage
{
	public void sendTextInTopicName(String text);

	public void clickOnManageTopicsButton();

	public void clickOnEditQAndAPermissionsButton();

	public void clickOnEditInListOfTopics(String title);

	public void renameTopic(String title, String text);

	public void adminQAndAPermissionsGroupWise(String orgName, String permission, boolean value);

	public void setQAndAPermissionGroupWise(Map<String, Map<String, Boolean>> qAndADataAndRoles);

	public void adminQAndAPermissionsUserWise(String orgName, String userName, String permission, Object value);

	public void setQAndAPermissionUserWise(Map<String, Map<String, Map<String, Object>>> userDataAndRoles);

	public void selectPermissionCheckbox(By option, boolean check);

	public void clickOnAddTopicButton();

	public void addTopic(String[] topic);

	public boolean verifyTopic(String topic);

	public void clickOnSaveButtonInEditQAPermission();

	public void clickOnCancelButtonInEditQAPermission();

	public boolean verifyModal();

	public void clickOnModalButton(String button);

	public void selectTopic(String orgName, String userName, Object topic);

}
