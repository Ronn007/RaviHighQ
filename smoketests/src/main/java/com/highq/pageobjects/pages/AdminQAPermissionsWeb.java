/**
 * 
 */
package com.highq.pageobjects.pages;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pageobjects.base.AdminQAPermissionsPage;

/**
 * @author vivek.mishra
 */
public class AdminQAPermissionsWeb extends BannerPageWeb implements AdminQAPermissionsPage
{
	// ************************************* Web Elements ************************************************/

	/** EditQ&APermissions button in admin QandA page */
	By editQAndAPermissions = By.xpath("//*[@id='qaPermissionForm']//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_QANDAPERMISSION_EDITQANDAPERMISSION + "']");

	/** MangeTopics button in admin QandA page */
	By manageTopics = By.xpath("//*[@id='qaPermissionForm']//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_QANDAPERMISSION_MANAGETOPICS + "']");

	/** Topic name text box after clicking on manage topics */
	By topicName = By.id("addTopicName");

	/** Cancel button to cancel Topic name text box after clicking on manage topics */
	By cancelButtonInEditTopic = By.id("siteAdmin_module_addTopic_modal_cancelLinkBtnID");

	/** Save button to cancel Topic name text box after clicking on manage topics */
	By saveButtonInEditTopic = By.id("siteAdmin_module_addTopic_modal_renameBtnID");

	/** addTopic button in admin QandA page */
	By addTopic = By.xpath("//*[@id='siteAdmin_module_mainMiddlePanelDivID']//*[normalize-space()='" + SiteAdminLabels.SITEADMIN_QANDAPERMISSION_ADDTOPIC + "']");

	By saveButtonInEditQAPermission = By.xpath("(//*[@id='qaPermissionForm']//*[contains(@class,'btn btn-default margLeft')])[2]");

	By cancelButtonInEditQAPermission = By.xpath("(//*[@id='qaPermissionForm']//*[contains(@class,'btn btn-cancel')])[2]");

	By modal = By.xpath("//*[@class='modal fade in']//*[@class='modal-content']");

	// ************************************* Constructor ************************************************/
	/**
	 * @author vivek.mishra
	 * @param driver
	 */
	public AdminQAPermissionsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	// ************************************* Methods ************************************************/

	/**
	 * @author vivek.mishra
	 *         To click on save button in edit topic page
	 */
	public void clickSaveButtonInEditTopic()
	{
		findVisibleElement(saveButtonInEditTopic, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in edit topic page
	 */
	public void clickCancelButtonInEditTopic()
	{
		findVisibleElement(cancelButtonInEditTopic, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To send text in topic by passing string value
	 */
	public void sendTextInTopicName(String text)
	{
		WebElement topicEle = findVisibleElement(topicName, Speed.slow);
		topicEle.clear();
		topicEle.sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 *         To click on Manage Topics Button in admin QandA page
	 */
	public void clickOnManageTopicsButton()
	{
		findVisibleElement(manageTopics, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on EditQAndAPermissions Button in admin QandA page
	 */
	public void clickOnEditQAndAPermissionsButton()
	{
		findVisibleElement(editQAndAPermissions, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Edit Button in list of topics in manage topics
	 * 		@title= title to edit
	 */
	public void clickOnEditInListOfTopics(String topic)
	{
		clickOnManageTopicsButton();
		findVisibleElement(By.xpath("//*[@id='fileData']//*[normalize-space()='" + topic.trim() + "']//following-sibling::td/a[normalize-space()='" + SiteAdminLabels.SITEADMIN_QANDAPERMISSION_LISTOFTOPICS_EDIT + "']"), Speed.slow).click();

	}

	/**
	 * @author vivek.mishra
	 *         To Rename the topic in edit topic page
	 */
	public void renameTopic(String title, String text)
	{
		clickOnEditInListOfTopics(title);
		sendTextInTopicName(text);
		clickSaveButtonInEditTopic();
	}

	/**
	 * @author vivek.mishra
	 *         Select Check Box in admin QandA page by passing org name roles and permission parameter
	 */
	public void adminQAndAPermissionsGroupWise(String orgName, String permission, boolean value)
	{
		By currentElement;
		switch (permission.toLowerCase())
		{
			case "ask question":
				currentElement = By.xpath("//*[@id='orgAccess']//following-sibling::*[normalize-space(text())='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'ASK_QUESTION')]");
				setSelection(currentElement, value);
				break;
			case "submit question":
				currentElement = By.xpath("//*[@id='orgAccess']//following::*[text()='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'SUBMIT_QUESTION')]");
				setSelection(currentElement, value);
				break;
			case "bidder admin":
				currentElement = By.xpath("//*[@id='orgAccess']//following-sibling::*[normalize-space(text())='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'BIDDER_ADMIN')]");
				setSelection(currentElement, value);
				break;
			case "seller admin":
				currentElement = By.xpath("//*[@id='orgAccess']//following-sibling::*[normalize-space(text())='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'SELLER_ADMIN')]");
				setSelection(currentElement, value);
				break;
			case "approve replies":
				currentElement = By.xpath("//*[@id='orgAccess']//following-sibling::*[normalize-space(text())='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'APPROVE_REPLIES')]");
				setSelection(currentElement, value);
				break;
			case "reply to question":
				currentElement = By.xpath("//*[@id='orgAccess']//following::*[text()='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'REPLYTO_QUESTION')]");
				setSelection(currentElement, value);
				break;
			case "view all questions":
				currentElement = By.xpath("//*[@id='orgAccess']//following::*[text()='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'VIEWALL_QUESTION')]");
				setSelection(currentElement, value);
				break;
			case "qanda email":
				currentElement = By.xpath("//*[@id='orgAccess']//following::*[text()='" + orgName.trim() + "']/../../../..//*[contains(@onclick,'QAEMAIL')]");
				setSelection(currentElement, value);
				break;
			default:
				break;
		}
	}

	/**
	 * @param userDataAndRoles
	 *        To set the permissions group wise
	 *        We need to pass group name roles corresponding boolean value
	 */
	public void setQAndAPermissionGroupWise(Map<String, Map<String, Boolean>> userDataAndRoles)
	{
		clickOnEditQAndAPermissionsButton();
		String domain = null;

		for (Map.Entry<String, Map<String, Boolean>> domainMap : userDataAndRoles.entrySet())
		{
			if (domainMap.getKey().contains("."))
			{
				domain = domainMap.getKey().split("\\.")[0].trim();
			}
			else
				domain = domainMap.getKey().trim();
			String roles = null;
			boolean permission;
			for (Map.Entry<String, Boolean> rolesMap : domainMap.getValue().entrySet())
			{
				roles = rolesMap.getKey();
				permission = rolesMap.getValue();
				adminQAndAPermissionsGroupWise(domain, roles, permission);
			}
		}
		clickOnSaveButtonInEditQAPermission();
	}

	/**
	 * @author vivek.mishra
	 *         Select Check Box in admin QandA page by passing org name, user name ,roles and permission parameter
	 */
	public void adminQAndAPermissionsUserWise(String orgName, String userName, String permission, Object value)
	{
		By currentElement;
		switch (permission.toLowerCase())
		{
			case "ask question":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='ASK_QUESTION']");
				setSelection(currentElement, (boolean) value);
				break;
			case "submit question":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='SUBMIT_QUESTION']");
				setSelection(currentElement, (boolean) value);
				break;
			case "bidder admin":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='BIDDER_ADMIN']");
				setSelection(currentElement, (boolean) value);
				break;
			case "topic expert":
				selectTopic(orgName, userName, value);
				break;
			case "seller admin":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='SELLER_ADMIN']");
				setSelection(currentElement, (boolean) value);
				break;
			case "approve replies":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='APPROVE_REPLIES']");
				setSelection(currentElement, (boolean) value);
				break;
			case "reply to question":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='REPLYTO_QUESTION']");
				setSelection(currentElement, (boolean) value);
				break;
			case "view all questions":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='VIEWALL_QUESTION']");
				setSelection(currentElement, (boolean) value);
				break;
			case "qanda email":
				currentElement = By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[@name='QAEMAIL']");
				setSelection(currentElement, (boolean) value);
				break;

			default:
				break;
		}
	}

	/**
	 * @author vivek.mishra
	 * @param userDataAndRoles
	 *        To set the permissions group wise
	 *        We need to pass group name user name roles and corresponding boolean value
	 */
	public void setQAndAPermissionUserWise(Map<String, Map<String, Map<String, Object>>> userDataAndRoles)
	{
		clickOnEditQAndAPermissionsButton();
		String domain = null;
		for (Map.Entry<String, Map<String, Map<String, Object>>> domainMap : userDataAndRoles.entrySet())
		{
			if (domainMap.getKey().contains("."))
				domain = domainMap.getKey().split("\\.")[0].trim();
			else
				domain = domainMap.getKey().trim();
			String user = null;
			;
			for (Map.Entry<String, Map<String, Object>> userMap : domainMap.getValue().entrySet())
			{
				user = userMap.getKey();
				String role = null;
				Object permission;
				for (Map.Entry<String, Object> permissionMap : userMap.getValue().entrySet())
				{
					role = permissionMap.getKey();
					permission = permissionMap.getValue();
					adminQAndAPermissionsUserWise(domain, user, role, permission);
				}
			}
		}
		clickOnSaveButtonInEditQAPermission();
	}

	/**
	 * @author vivek.mishra
	 *         To click on check box
	 */
	public void selectPermissionCheckbox(By option, boolean check)
	{
		WebElement element = findVisibleElement(option, Speed.slow);
		if (element.isSelected() != check)
			element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param topic
	 *        To add a topic in Admin Q&A page
	 */
	public void addTopic(String[] topic)
	{
		clickOnManageTopicsButton();
		for (int i = 0; i < topic.length; i++)
		{
			if (!verifyTopic(topic[i]))
			{
				clickOnAddTopicButton();
				sendTextInTopicName(topic[i]);
				clickSaveButtonInEditTopic();
			}
		}
	}

	/**
	 * @author vivek.mishra
	 *         To click on add topic button in add topic page
	 */
	public void clickOnAddTopicButton()
	{
		findVisibleElement(addTopic, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param topic
	 * @return
	 * 		To verify the topic is available
	 */
	public boolean verifyTopic(String topicName)
	{
		return (isDisplayed(By.xpath("//*[@class='adminContainer']//*[normalize-space()='" + topicName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on Delete Button in list of topics in manage topics
	 * 		@title= title to edit
	 */
	public void clickOnDeleteInListOfTopics(String topic)
	{
		clickOnManageTopicsButton();
		findVisibleElement(By.xpath("//*[@id='fileData']//*[normalize-space()='" + topic.trim() + "']//following-sibling::td/a[normalize-space()='" + SiteAdminLabels.SITEADMIN_QANDAPERMISSION_LISTOFTOPICS_DELETE + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on save button in edit QA permission
	 */
	public void clickOnSaveButtonInEditQAPermission()
	{
		findVisibleElement(saveButtonInEditQAPermission, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in edit QA permission
	 */
	public void clickOnCancelButtonInEditQAPermission()
	{
		findVisibleElement(cancelButtonInEditQAPermission, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param orgName
	 * @param userName
	 * @param value to be selected
	 * @created on 23/04/201
	 */
	public void selectTopic(String orgName, String userName, Object value)
	{
		findVisibleElement(By.xpath("(//*[normalize-space(text())='" + orgName.trim() + "']/ancestor::tr/following-sibling::*//*[normalize-space(text())='" + getUserData(userName.trim()) + "'])[1]/ancestor::td/following-sibling::*//*[normalize-space(text())='Select']"), Speed.slow).click();
		verifyModal();
		By element = By.xpath("//*[@id='frmSelectTopicDialogPage']//*[contains(@class,'control-label checkbox') and normalize-space()='" + value + "']//*[@type='checkbox']");
		setSelection(element, true);
		clickOnModalButton("Save");
	}

	/**
	 * @author vivek.mishra
	 * @return the modal availability
	 * @created on 23/04/2018
	 */
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return (isDisplayed(modal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param button
	 *        to click on modal button
	 * @created on 23/04/2018
	 */
	public void clickOnModalButton(String button)
	{
		findVisibleElement(By.xpath("//*[@class='modal fade in']//*[@class='modal-footer']//*[normalize-space(text())='" + button.trim() + "']"), Speed.slow).click();
	}

}
