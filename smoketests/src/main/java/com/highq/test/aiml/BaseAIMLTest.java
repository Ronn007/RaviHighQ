package com.highq.test.aiml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeSuite;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.AspAdminWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

public class BaseAIMLTest extends BannerPageWeb
{
	public String sysAdminEmail = "ravi.middha@highq.com";// "auto@highq.com";//"ravi.middha@highq.com";//"admin.user@highq.com";//"tom.chick@highq.com";
	public String sysAdminPassword = /* "Pa&&worD123"; */"Password@123";

	// ****Expected Values***********//
	List<String> expDocAnalysDrpdwn_AspAdmin = new ArrayList<>();
	List<String> expDocAnlysDrpown_SysAdmin = new ArrayList<>();
	String expectedCronExprsn = "0 0/5 * * * ?";
	String expeectedDocCount = "1000";
	String expectedSaveChangesMsg = "Changes applied";
	String expectedInvalidDocCountAlertmsg = "No. of documents to be processed in a job must be positive integer.";
	String expectedInvalidDocCountAlertmsg0input = "No. of documents to be processed in a job is not valid. It must be greater than or equal to 1.";
	String expectedInvalidCronExprsnMsg = "Document analysis cron expression is invalid";

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;

	AspAdminPage aspAdminWeb;
	SystemAdminPage sysAdminWeb;
	SystemAdminSystemSettingsPage sysAdminSystemSettingWeb;
	SystemAdminFileOrFileTypesPage sysAdminFileOrFilesTypeWeb;
	EditFileIconsPage editFileIconsWeb;

	AspConfigurationPage aspConfigWeb;
	MyFilesPage myFilesWeb;

	ModulesPage modulesPageWeb;
	Map<Boolean, BannerPageWeb> result;
	DashboardPage dashboardWeb;
	LoginPage loginPageWeb;
	BannerPage bannerPageWeb;

	@BeforeSuite
	public void setup()
	{
		super.getDriver();
		expDocAnalysDrpdwn_AspAdmin.add("TRUE");
		expDocAnalysDrpdwn_AspAdmin.add("FALSE");
		expDocAnlysDrpown_SysAdmin.add("ON");
		expDocAnlysDrpown_SysAdmin.add("OFF");
		aspAdminWeb = new AspAdminWeb(driver);

	}

	public void gotoAspAdminDocAnalysis() throws InterruptedException
	{
		bannerPageWeb.gotoASPAdmin();
		aspAdminWeb.openConfigurationPage();
		aspConfigWeb.clickOnLeftPanelLabel(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_DOCUMENTANALYSIS);
	}

	public void gotoSysAdminDocAnalysis() throws InterruptedException
	{
		bannerPageWeb.gotoSystemAdmin();
		sysAdminWeb.gotoSystemSettings();
	}

	public void gotoSysAdminFileOrFileTypes()
	{
		bannerPageWeb.gotoSystemAdmin();
		sysAdminFileOrFilesTypeWeb = sysAdminWeb.gotoFileOrFileTypes();

	}
}
