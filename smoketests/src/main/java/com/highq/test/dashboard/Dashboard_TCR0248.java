package com.highq.test.dashboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author ashlesha.shastri
 * @created on 12/04/2018
 * @updated khushbu.dhandhukiya
 * @updated date 10/05/2018
 */
public class Dashboard_TCR0248 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	String domain = "highq.com";
	String userNames ="ravi.middha";
	String newPassword="Password@123";
	String siteName = "Dashboard_TC0248";
	String jsonFileName="preConfiguration_Dashboard_TCR0248.json";
	
	
	
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	DashboardPage dashboardWeb;
	PreConfiguration preConfiguration;
	SystemAdminPage systemAdminWeb;
	AdminPage adminPage;
	EventsPage eventsWeb;
	BannerPage bannerPageWeb;

	

	Map<String, List<String>> userMap;
	List<String> userList;

	

	String[] eventName = {"Event 1" + getRandomString(), "Event 2" + getRandomString()};
	String[] startDate = {"02 Feb 2020", "02 Feb 2021"};
	String categoryName = "Default";
	String contact = sysAdminEmail;

	ConfigurationData configurationData = new ConfigurationData();


	@Test
	public void dashboardTCR0248() throws InterruptedException, IOException, JSONException
	{
		precondition();
		sitePreconfiguration();
		scenario1();
	}


	public void precondition() throws InterruptedException, IOException, JSONException
	{

		/** Site and user setup */	
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
				
	}
	
	public void sitePreconfiguration()
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		
	
		
		eventsWeb = gotoEventModule();
		for (int i = 0; i < eventName.length; i++)
		{
			eventsWeb.clickOnAddButton();
			eventsWeb.sendTextInTitleTextBoxInAddEventModal(eventName[i]);
			eventsWeb.sendDateInStartDateTextBox(startDate[i]);
			eventsWeb.sendTextInContactsTextBoxInModal(contact);
			eventsWeb.selectCategoryInCategeoryDropDownInAddEventModal(categoryName);
			eventsWeb.clickOnAddButtonInAddEventModal();

			Assert.assertTrue(eventsWeb.verifyEventInEventList(eventName[i]));
			
			
		}

	}

	
	public void scenario1()
	{
		dashboardWeb = eventsWeb.gotoDashboard();
		for (int i = 0; i < eventName.length; i++)
		{
			Assert.assertTrue(dashboardWeb.verifyEventDisplayedOnDashboard(eventName[i]));
		}

		eventsWeb = dashboardWeb.clickOnEventDisplayedOnDashboard(eventName[1]);
		eventsWeb.verifyEventInEditSection(eventName[1]);
		eventsWeb = gotoEventModule();
		for(int i=0;i<eventName.length;i++)
		{
			eventsWeb.deleteEventInEventList(eventName[i]);
		}
		eventsWeb.logout();
	}

}
