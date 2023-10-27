package com.highq.test.dashboard;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.SearchContentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Dashboard_TCR0209 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String[] userNames = {"site.admin209"};
	String domain = "dashboard209.com";
	String newPassword = "Password123";
	String userRole = "Site admin";

	String[] searchFilterOptions = {"All", "People", "Sites", "Content"};
	String[] searchText = {"wiki", "Ravi Middha", "Dashboard_TCR0209", "wiki"};
	String siteName = "Dashboard_TCR0209";

	DashboardPage dashboardWeb;
	SearchContentPage searchContentWeb;
	BannerPage bannerPageWeb;

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
	public void dashboardTCR0209() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		Map<String, String> filterOption = new LinkedHashMap<>();
		for (int i = 0; i < searchFilterOptions.length; i++)
		{
			filterOption.put(searchFilterOptions[i], searchText[i]);
		}

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		for (Map.Entry<String, String> filter : filterOption.entrySet())
		{
			gotoDashboard();
			clickOnSearchButton();
			clickOnSearchDropDown();
			selectSearchFilterOption(filter.getKey());
			searchContentWeb = searchContentInGlobalSearch(filter.getValue());
			Assert.assertTrue(searchContentWeb.verifySearchBox());
			if (!filter.getKey().equalsIgnoreCase("People"))
			{
				Assert.assertTrue(searchContentWeb.verifySearchBoxContent(filter.getValue()));
				Assert.assertTrue(searchContentWeb.verifyAllSearchResult(filter.getValue()));
			}
			else
			{
				Assert.assertTrue(searchContentWeb.verifySearchBoxContent("(Ravi OR Middha)"));
				Assert.assertTrue(searchContentWeb.verifyPeopleSearchResult(filter.getValue()));
			}
			Assert.assertTrue(searchContentWeb.verifySearch());
		}

	}

	public void scenario2() throws InterruptedException, IOException
	{
		gotoDashboard();
		if (verifySearchLink())
		{
			clickOnSearchButton();
			clickOnSearchDropDown();
			if (verifyAdvancedSearchOption())
			{
				clickAdvancedSearchOption();
				Assert.assertTrue(verifyAdvanceSearchModalOpened());
				clickCancelOnAdvanceSearchModal();
			}
		}
	}
}
