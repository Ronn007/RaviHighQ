package com.highq.test.systemAdmin;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class SystemAdmin_TC2733 extends BannerPageWeb
{
	BannerPage bannerPageWeb;

	String spName = "Griffin_GDPR_Search";
	String value = "123";

	WebDriver driver;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test
	public void systemAdminTC2733() throws InterruptedException, IOException, JSONException
	{
		scenario1();
	}

	public void scenario1()
	{
		bannerPageWeb = new BannerPageWeb();
		try
		{
			bannerPageWeb.callStoreProcedure(spName, value);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
