package com.highq.test.dashboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0211 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String siteName = "Dashboard_TC0211";
	String[] userNames = {"normaluser.tcr0211", "normaluser1.tcr0211", "normaluser2.tcr0211"};
	String domain = "dashboard211.com";
	String newPassword = "Password@123";

	String[] messageFilterOptions = {"Active", "Unread", "Archived"};
	String newMessage = "New message";
	String messageBody = "Private Message";
	String seeAllMessages = "See all messages";

	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	BannerPage bannerPageWeb;
	Map<String, List<String>> userMap;
	List<String> userList;
	ConfigurationData configurationData = new ConfigurationData();

	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 1)
	public void dashboardTCR0211() throws InterruptedException, IOException
	{
		siteAndUserConfiguration();
		scenario1();
		scenario2();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[2] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		if (dashboardWeb.verifyPrivateMessageLink())
		{
			clickOnPrivateMessage();
			Assert.assertTrue(verifyNoMessages());
		}
		logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		if (dashboardWeb.verifyPrivateMessageLink())
		{
			clickOnPrivateMessage();
			if (verifyPrivateMessageOptions(newMessage))
			{
				clickOnNewMessages();
				sendPrivateMessage(messageBody, userNames[1] + "@" + domain);
			}
		}
		logout();

		bannerPageWeb = login(userNames[1] + "@" + domain, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		clickOnPrivateMessage();
		if (verifyPrivateMessageOptions(seeAllMessages))
		{
			Assert.assertTrue(verifySeeAllMessagesEnabled());
			clickOnSeeAllMessages();
			Assert.assertTrue(verifySeeAllMessagesSearchBox());
			clickSeeAllMessagesSearchDropDown();
			for (int i = 0; i < messageFilterOptions.length; i++)
			{
				Assert.assertTrue(verifySearchMessageFilterOption(messageFilterOptions[i]));
			}
		}
	}

	public void scenario2() throws InterruptedException, IOException
	{
		for (int i = 0; i < messageFilterOptions.length; i++)
		{
			selectSearchMessageFilterOption(messageFilterOptions[i]);
			searchPrivateMessage(messageBody);
			Assert.assertTrue(verifySearch());
			clickSeeAllMessagesSearchDropDown();
		}
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		String[] configurationList = {"setOrg", "addSystemAdminUsers"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();
	}

}
