/**
 * @author vivek.mishra
 */
package com.highq.test.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminEventPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek.mishra
 */
public class Event_TC0151 extends BannerPageWeb
{
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	LoginPage loginPageWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	AdminEventPage adminEventPage;
	EventsPage eventsPage;
	BannerPage bannerPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String nPassword = "NewPassword@123";
	String[] userNames = {"sitenormaluser", "siteadminuser"};
	String orgType = "Internal";
	String siteName = "Event_TC0151";
	String domain = "eventorg.com";

	String[] eventTitle = {"Event 151 " + getRandomString(), "Auto Save Draft Event " + getRandomString(), "Edited Event " + getRandomString(), "Auto Save Edited " + getRandomString(), "New Event " + getRandomString()};
	String[] draftEvents = {"a1", "a2", "a3", "a4", "a5"};
	String category = "Default";
	String editedCategeory = "Cat1";
	String draftStatus = "Draft";
	String publishedStatus = "Published";
	String autoSaveStatus = "Auto-saved";
	String autoSavedDraftMessage = "The system automatically saves a draft as you are editing. Auto-saves can be accessed by clicking your name in the top right corner of the screen and selecting \"My drafts\". Do you want to keep your auto-saved draft?";
	String myDraftsLink = "My drafts";
	String autoSaveDraftModal = "Auto-saved draft";
	String addEventModal = "Add event";
	String editEventModal = "Edit event";
	String deleteDraftEventModal = "Delete draft event?";
	String editOption = "Edit";
	String eventLocation = "Default";
	String startDate = "22 Jan 2018";
	String endDate = "22 Feb 2025";
	String startTime = "10:15";
	String endTime = "20:20";
	String contact = "first contact";
	String description = "No Comments";
	String tag = "Tag1";
	String file = "doc1.txt";
	String dateAndTime;
	String draftDeleteConfirmationMessage = "Are you sure you want to delete this event?";

	List<String> missingEvents = new ArrayList<String>();
	List<String> availableEvents = new ArrayList<String>();

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void EventTC0151() throws InterruptedException, IOException
	{
		preConditions();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @creation date 18/01/2018
	 */
	public void preConditions() throws InterruptedException, IOException
	{
		TC0151_PreConditionsOfUserCreation();
		TC0151_BasicRequirements();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 18/01/2018
	 */
	public void TC0151_BasicRequirements() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminEventPage = adminPageWeb.clickEventsInLeftPanel();
		adminEventPage.verifyCategoryList();
		if (!adminEventPage.verifyCategoryInCategoriesList(editedCategeory))
		{
			adminEventPage.addCategory(editedCategeory);
			adminEventPage.verifyAdmineventsPage();
		}
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 17/01/2018
	 */
	public void TC0151_PreConditionsOfUserCreation() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, Boolean> userRolesOfUser = new LinkedHashMap<>();
		userRolesOfUser.put("site admin", true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Files", moduleEditPermission);
		modulePermission.put("Events", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, userRolesOfUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(nPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "events");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 17/01/2018
	 */
	public void scenario1() throws InterruptedException
	{
		scenario1_Case1();
		scenario1_Case2();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @creation date 17/01/2018
	 */
	public void scenario1_Case1() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		eventsPage.clickOnAddButton();
		eventsPage.verifyModal();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[0]);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(draftStatus);
		eventsPage.clickOnAddButtonInAddEventModal();

		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertTrue(eventsPage.verifyEventInMyDrafts(eventTitle[0]));
		Assert.assertTrue(eventsPage.verifyStatusInMyDraft(eventTitle[0], draftStatus));
		Assert.assertFalse(eventsPage.verifyAddToFavouratesIconInEditSection());
		Assert.assertFalse(eventsPage.verifyMoreActionInEditSection());
	}

	/**
	 * @author vivek.mishra
	 * @creation date 17/01/2018
	 */
	public void scenario1_Case2()
	{
		eventsPage.gotoEventModule();
		eventsPage.clickOnAddButton();
		eventsPage.verifyModal();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[1]);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
		eventsPage.waitForAutoSaveMessage();
		eventsPage.clickOnCancelButtonInAddEventModal();

		Assert.assertTrue(eventsPage.verifyModal(autoSaveDraftModal));
		Assert.assertTrue(eventsPage.verifyAutoSaveDraftMessage(autoSavedDraftMessage));
		Assert.assertTrue(eventsPage.verifySaveButtonInAutoSaveDraftModal());
		Assert.assertTrue(eventsPage.verifyDiscardButtonInAutoSaveDraftModal());

		eventsPage.clickOnDiscardButtonInAutoSaveDraftModal();
		Assert.assertFalse(eventsPage.verifyModal(autoSaveDraftModal));
		eventsPage.verifyAddButton();
		Assert.assertFalse(eventsPage.verifyModal(addEventModal));
		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		Assert.assertFalse(eventsPage.verifyEventInMyDrafts(eventTitle[1]));

		eventsPage.gotoEventModule();
		eventsPage.clickOnAddButton();
		eventsPage.verifyModal();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[1]);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
		eventsPage.waitForAutoSaveMessage();
		eventsPage.clickOnCancelButtonInAddEventModal();

		Assert.assertTrue(eventsPage.verifyModal(autoSaveDraftModal));

		eventsPage.clickOnSaveButtonInAutoSaveDraftModal();
		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertTrue(eventsPage.verifyEventInMyDrafts(eventTitle[1]));
		Assert.assertTrue(eventsPage.verifyStatusInMyDraft(eventTitle[1], autoSaveStatus));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 17/01/2018
	 */
	public void scenario2()
	{
		eventsPage.clickOnEventInMyDrafts(eventTitle[0]);
		Assert.assertTrue(eventsPage.verifyModal("Add event"));
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[2]);
		Assert.assertTrue(eventsPage.verifyStatusSelectedInModal(draftStatus));
		eventsPage.clickOnAddButtonInAddEventModal();
		eventsPage.verifyModal();
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertTrue(eventsPage.verifyEventInMyDrafts(eventTitle[2]));

		eventsPage.clickOnEventInMyDrafts(eventTitle[2]);
		Assert.assertTrue(eventsPage.verifyModal("Add event"));
		eventsPage.selectOptionInStatusDropDownInAddEventModal(publishedStatus);
		eventsPage.clickOnAddButtonInAddEventModal();
		eventsPage.verifyModal();
		eventsPage.verifyDetailsSection();
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventTitle[2]));
	}

	/**
	 * @author vivek.mishra
	 * @throws ParseException
	 * @creation date 18/01/2018
	 */
	public void scenario3()
	{
		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.clickOnEventInMyDrafts(eventTitle[1]);
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[3]);
		eventsPage.sendTextInLocationTextBoxInAddEventModal(eventLocation);
		eventsPage.sendDateInStartDateTextBox(startDate);
		eventsPage.sendStartTimeInStartTimeTextBox(startTime);
		eventsPage.sendDateInEndDateTextBox(endDate);
		eventsPage.sendEndTimeInEndTimeTextBox(endTime);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(editedCategeory);
		eventsPage.sendTextInContactsTextBoxInModal(contact);
		eventsPage.sendTextInDescriptionTextBoxInAddEventModal(description);
		eventsPage.sendTextInTagTextBoxInAddEventModal(tag);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(publishedStatus);
		eventsPage.attachFileInAddEventModal(file);
		eventsPage.clickOnAddButtonInAddEventModal();

		eventsPage.verifyModal();
		eventsPage.verifyDetailsSection();
		eventsPage.verifyEventInEditSection(eventTitle[3]);
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventTitle[3]));
		Assert.assertTrue(eventsPage.verifyLocationInEditSection(eventLocation));
		Assert.assertTrue(eventsPage.verifyDescriptionInEditSection(description));
		Assert.assertTrue(eventsPage.verifyCategoryInEditSection(editedCategeory));

		dateAndTime = startDate + " " + startTime;
		Assert.assertTrue(eventsPage.verifyStartDateTimeOfAnEventInEditEvent(dateAndTime));
		dateAndTime = endDate + " " + endTime;
		Assert.assertTrue(eventsPage.verifyEndDateTimeOfAnEventInEditEvent(dateAndTime));
		Assert.assertTrue(eventsPage.verifyTagInEditSection(tag));
		Assert.assertTrue(eventsPage.verifyContactInEditSection(contact));
		Assert.assertTrue(eventsPage.verifyAttachmentInEditSection(file));
	}

	/**
	 * @author vivek.mishra
	 * @created On 18/01/2018
	 */
	public void scenario4()
	{
		eventsPage.gotoEventModule();
		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventTitle[2], editOption);
		eventsPage.verifyModal(editEventModal);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(draftStatus);
		eventsPage.clickOnAddButtonInAddEventModal();

		eventsPage.clickOnOptionInMoreActionOfAnEvent(eventTitle[3], editOption);
		eventsPage.verifyModal(editEventModal);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(draftStatus);
		eventsPage.clickOnAddButtonInAddEventModal();

		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.verifyMyDraftsEventTable();
		for (int i = 0; i < draftEvents.length; i++)
		{
			if (!eventsPage.verifyEventInMyDrafts(draftEvents[i]))
			{
				missingEvents.add(draftEvents[i]);
			}
		}

		eventsPage.gotoEventModule();
		eventsPage.verifyAddButton();
		eventsPage.clickOnAddButton();
		eventsPage.verifyModal();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle[4]);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(draftStatus);
		eventsPage.clickOnAddButtonInAddEventModal();

		for (int i = 0; i < missingEvents.size(); i++)
		{
			if (missingEvents.get(i).equals(draftEvents[3]) || missingEvents.get(i).equals(draftEvents[4]))
			{
				eventsPage.verifyAddButton();
				eventsPage.clickOnAddButton();
				eventsPage.verifyModal();
				eventsPage.sendTextInTitleTextBoxInAddEventModal(missingEvents.get(i));
				eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
				eventsPage.waitForAutoSaveMessage();
				eventsPage.clickOnCancelButtonInAddEventModal();
				eventsPage.clickOnSaveButtonInAutoSaveDraftModal();
				eventsPage.gotoEventModule();
			}
			else
			{
				eventsPage.verifyAddButton();
				eventsPage.clickOnAddButton();
				eventsPage.verifyModal();
				eventsPage.sendTextInTitleTextBoxInAddEventModal(missingEvents.get(i));
				eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category);
				eventsPage.selectOptionInStatusDropDownInAddEventModal(draftStatus);
				eventsPage.clickOnAddButtonInAddEventModal();
			}
		}

		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.verifyMyDraftsEventTable();
		eventsPage.verifyEventInMyDrafts(eventTitle[2]);
		eventsPage.clickOnDeleteIconInMyDraft(eventTitle[2]);
		Assert.assertTrue(eventsPage.verifyModal(deleteDraftEventModal));
		eventsPage.clickOnCancelButtonInDeletModal();
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertTrue(eventsPage.verifyEventInMyDrafts(eventTitle[2]));

		eventsPage.clickOnDeleteIconInMyDraft(eventTitle[2]);
		Assert.assertTrue(eventsPage.verifyModal(deleteDraftEventModal));
		eventsPage.clickOnDeleteButtonInDeleteModal();
		eventsPage.gotoEventModule();
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertFalse(eventsPage.verifyEventInMyDrafts(eventTitle[2]));

		availableEvents = eventsPage.getListOfElementsInMyDrafts();
		availableEvents.remove(eventTitle[4]);
		availableEvents.remove(eventTitle[3]);
		eventsPage.selectCheckBoxInMyDrafts(eventTitle[4]);
		eventsPage.selectCheckBoxInMyDrafts(eventTitle[3]);
		eventsPage.clickOnDeleteButtonInMyDrafts();

		Assert.assertTrue(eventsPage.verifyQuitButtonInDeletModal());
		Assert.assertTrue(eventsPage.verifyModal(deleteDraftEventModal));
		Assert.assertTrue(eventsPage.verifyDeleteModalMessage(draftDeleteConfirmationMessage));
		Assert.assertTrue(eventsPage.verifyCancelButtonInDeletModal());
		Assert.assertTrue(eventsPage.verifyDeleteButtonInDeletModal());

		eventsPage.clickOnDeleteButtonInDeleteModal();

		Assert.assertFalse(eventsPage.verifyEventInMyDrafts(eventTitle[4]));
		Assert.assertFalse(eventsPage.verifyEventInMyDrafts(eventTitle[3]));
		int eventSize = availableEvents.size();
		for (int i = 1; i <= eventSize; i++)
			Assert.assertTrue(eventsPage.verifyEventPosition(i, availableEvents.get(i - 1)));
	}

	/**
	 * @author vivek.mishra
	 *         Creation Date 19/01/2018
	 */
	public void scenario5()
	{
		eventsPage.clickOnLabelsInLeftPanel(myDraftsLink);

		eventsPage.clickOnFilterIcon();
		Assert.assertTrue(eventsPage.verifyOptionsInFiterIconInMyDrafts(draftStatus));
		Assert.assertTrue(eventsPage.verifyOptionsInFiterIconInMyDrafts(autoSaveStatus));
		Assert.assertTrue(eventsPage.verifyOptionsInFiterIconInMyDrafts("All"));
		eventsPage.clickOnFilterIcon();

		eventsPage.selectOptionInFiterIconInMyDrafts(draftStatus);
		eventsPage.verifyAllEventsStatusInMyDrafts(draftStatus);

		eventsPage.selectOptionInFiterIconInMyDrafts(autoSaveStatus);
		eventsPage.verifyAllEventsStatusInMyDrafts(autoSaveStatus);

		eventsPage.selectOptionInFiterIconInMyDrafts("All");
		for (int i = 0; i < draftEvents.length; i++)
		{
			if (i > 2)
				Assert.assertTrue(eventsPage.verifyStatusInMyDraft(draftEvents[i], autoSaveStatus));
			else
				Assert.assertTrue(eventsPage.verifyStatusInMyDraft(draftEvents[i], draftStatus));
		}

		eventsPage.sendTextInQuickSearchTextBoxInMyDrafts(draftEvents[0]);
		eventsPage.verifyMyDraftsEventTable();
		Assert.assertTrue(eventsPage.verifyEventInMyDrafts(draftEvents[0]));
	}
}
