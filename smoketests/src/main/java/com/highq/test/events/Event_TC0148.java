/**
 * 
 */
package com.highq.test.events;

import java.io.IOException;
import java.text.ParseException;
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
public class Event_TC0148 extends BannerPageWeb
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
	String siteName = "Event_TC0148";
	String domain = "eventorg.com";
	String[] category = {"cat1", "cat2", "cat3"};

	String eventTitle = "First Event " + getRandomString();
	String eventLocation = "Default";
	String startDate = "22 Jan 2018";
	String endDate = "22 Feb 2050";
	String startTime = "10:15";
	String endTime = "20:20";
	String contact = "first contact";
	String description = "No Comments";
	String tag = "Tag1";
	String status = "Published";
	String file = "doc1.txt";
	String successAddEventMessage = "The event was added successfully";
	String successUpdateEventMessage = "The event was updated successfully";

	String newEventTitle = "First Event Modified ";
	String newEventLocation = "Default Location Modified";
	String newStartDate = "22 Feb 2018";
	String newEndDate = "22 Mar 2050";
	String newStartTime = "12:18";
	String newEndTime = "23:25";
	String newContact = "first contact modified";
	String newDescription = "No Comments changed";
	String newTag = "Tag2";
	String newFile = "doc2.txt";

	String dateAndTime;

	ConfigurationData configurationData = new ConfigurationData();

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void EventTC0148() throws InterruptedException, IOException, ParseException
	{
		preConditions();
		scenario1();
		scenario2();
		cleanSite();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void preConditions() throws InterruptedException, IOException
	{
		TC0148_PreConditionsOfUserCreation();
		TC0148_BasicRequirements();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void TC0148_PreConditionsOfUserCreation() throws InterruptedException
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
	 */
	public void TC0148_BasicRequirements() throws InterruptedException
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();

		adminEventPage = adminPageWeb.clickEventsInLeftPanel();
		for (int i = 0; i < category.length; i++)
		{
			if (!adminEventPage.verifyCategoryInCategoriesList(category[i]))
			{
				adminEventPage.addCategory(category[i]);
				adminEventPage.verifyAdmineventsPage();
			}
		}
		logout();
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void scenario1() throws InterruptedException, ParseException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		eventsPage = dashboardWeb.gotoEventModule();

		eventsPage.clickOnAddButton();
		eventsPage.verifyModal();
		eventsPage.sendTextInTitleTextBoxInAddEventModal(eventTitle);
		eventsPage.sendTextInLocationTextBoxInAddEventModal(eventLocation);
		eventsPage.sendDateInStartDateTextBox(startDate);
		eventsPage.sendStartTimeInStartTimeTextBox(startTime);
		eventsPage.sendDateInEndDateTextBox(endDate);
		eventsPage.sendEndTimeInEndTimeTextBox(endTime);
		eventsPage.selectCategoryInCategeoryDropDownInAddEventModal(category[0]);
		eventsPage.sendTextInContactsTextBoxInModal(contact);
		eventsPage.sendTextInDescriptionTextBoxInAddEventModal(description);
		eventsPage.sendTextInTagTextBoxInAddEventModal(tag);
		eventsPage.selectOptionInStatusDropDownInAddEventModal(status);
		eventsPage.attachFileInAddEventModal(file);
		eventsPage.clickOnAddButtonInAddEventModal();

		Assert.assertTrue(eventsPage.verifyGlobalAlertMessage(successAddEventMessage));
		Assert.assertTrue(eventsPage.verifyEventInEventList(eventTitle));
		Assert.assertTrue(eventsPage.verifyLocationInEventList(eventTitle, eventLocation));
		Assert.assertTrue(eventsPage.verifyDescriptionInEventList(eventTitle, description));
		Assert.assertTrue(eventsPage.verifyCategoryInEventList(eventTitle, category[0]));

		eventsPage.clickOnEventInEventList(eventTitle);
		Assert.assertTrue(eventsPage.verifyEventInEditSection(eventTitle));
		dateAndTime = startDate + " " + startTime;
		Assert.assertTrue(eventsPage.verifyStartDateTimeOfAnEventInEditEvent(dateAndTime));
		dateAndTime = endDate + " " + endTime;
		Assert.assertTrue(eventsPage.verifyEndDateTimeOfAnEventInEditEvent(dateAndTime));
		Assert.assertTrue(eventsPage.verifyTagInEditSection(tag));
		Assert.assertTrue(eventsPage.verifyContactInEditSection(contact));
		Assert.assertTrue(eventsPage.verifyAttachmentInEditSection(file));
	}

	public void scenario2()
	{
		eventsPage = dashboardWeb.gotoEventModule();
		eventsPage.clickOnEventInEventList(eventTitle);
		eventsPage.verifyEventInEditSection(eventTitle);
		eventsPage.clickOnOptionInMoreActionInEditSection("Edit");
		eventsPage.verifyModal();

		eventsPage.sendTextInTitleTextBoxInAddEventModal(newEventTitle);
		eventsPage.sendTextInLocationTextBoxInAddEventModal(newEventLocation);
		eventsPage.sendDateInStartDateTextBox(newStartDate);
		eventsPage.sendStartTimeInStartTimeTextBox(newStartTime);
		eventsPage.sendDateInEndDateTextBox(newEndDate);
		eventsPage.sendEndTimeInEndTimeTextBox(newEndTime);
		eventsPage.editCategoryInCategeoryDropDownInAddEventModal(category[0], category[1]);
		eventsPage.editContactsTextBoxInAddEventModal(contact, newContact);
		eventsPage.sendTextInDescriptionTextBoxInAddEventModal(newDescription);
		eventsPage.editTagTextBoxInAddEventModal(tag, newTag);
		eventsPage.editAttachedFileInAddEventModal(file, newFile);
		eventsPage.clickOnAddButtonInAddEventModal();

		Assert.assertTrue(eventsPage.verifyGlobalAlertMessage(successUpdateEventMessage));
		Assert.assertTrue(eventsPage.verifyEventInEditSection(newEventTitle));
		Assert.assertTrue(eventsPage.verifyLocationInEditSection(newEventLocation));
		Assert.assertTrue(eventsPage.verifyDescriptionInEditSection(newDescription));
		Assert.assertTrue(eventsPage.verifyCategoryInEditSection(category[1]));

		dateAndTime = newStartDate + " " + newStartTime;
		Assert.assertTrue(eventsPage.verifyStartDateTimeOfAnEventInEditEvent(dateAndTime));
		dateAndTime = newEndDate + " " + newEndTime;
		Assert.assertTrue(eventsPage.verifyEndDateTimeOfAnEventInEditEvent(dateAndTime));
		Assert.assertTrue(eventsPage.verifyTagInEditSection(newTag));
		Assert.assertTrue(eventsPage.verifyContactInEditSection(newContact));
		Assert.assertTrue(eventsPage.verifyAttachmentInEditSection(newFile));
	}

	/**
	 * @author vivek.mishra
	 *         To clean up the site
	 * @creation date 17/02/2018
	 */
	public void cleanSite()
	{
		eventsPage.gotoEventModule();
		eventsPage.verifyEventInEventList(newEventTitle);
		eventsPage.clickOnOptionInMoreActionOfAnEvent(newEventTitle, "Delete");
		eventsPage.verifyModal();
		eventsPage.clickOnDeleteButtonInDeleteModal();
	}
}
