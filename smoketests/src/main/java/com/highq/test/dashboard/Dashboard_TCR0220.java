package com.highq.test.dashboard;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.BannerLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.ApprovalWorkflowPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.MyDraftPage;
import com.highq.pageobjects.base.MyProfilePage;
import com.highq.pageobjects.base.SettingsPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.ViewUserProfilePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author khushbu.dhandhukiya
 */
public class Dashboard_TCR0220 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	String userName = "Ravi Middha";
	String Message = BannerLabels.BANNERPAGE_LOGOUT_MESSAGE;

	String jsonFileName = "preConfiguration_Dashboard_TCR0220.json";
	String siteName = "Dashboard_TC0220";
	String[] userNames = {"dashboard.normaluser220@highq.com"};
	String domain = "highq.com";
	String newPassword = "Pa&&worD@123";
	String[] userAvtarOptions = {BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYPROFILE, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYDRAFTS, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYFILES, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_MYTASKS, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SENDAFILE,
			BannerLabels.BANNERPAGE_USERAVATAROPTIONS_APPROVALWORKFLOW, BannerLabels.BANNERPAGE_NOTIFICATION_SETTINGS, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_SYSTEMADMIN,
			BannerLabels.BANNERPAGE_USERAVATAROPTIONS_ASPADMIN, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HIGHQHUB, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_HELP, BannerLabels.BANNERPAGE_USERAVATAROPTIONS_LOGOUT};

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	TasksPage tasksWeb;
	DashboardPage dashboardWeb;
	BannerPage bannerPageWeb;
	MyProfilePage myprofileWeb;
	SystemAdminPage systemAdminWeb;
	AspAdminPage aspAdminWeb;
	MyDraftPage draftpage;
	ApprovalWorkflowPage approvalworkflowweb;
	SettingsPage settingsweb;
	ViewUserProfilePage viewUserProfileWeb;
	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void dashboardTC0220() throws InterruptedException, IOException, JSONException
	{
		precondition();
		scenario1();
		scenario2();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		clickOnUserAvtar();
		for (int i = 0; i < userAvtarOptions.length; i++)
		{
			Assert.assertTrue(verifyUserAvtarOptions(userAvtarOptions[i]));
		}

		clickOnUserAvtar();
		myprofileWeb = dashboardWeb.gotoMyProfile();
		viewUserProfileWeb = myprofileWeb.gotoSummaryInLeftPanel();
		Assert.assertTrue(viewUserProfileWeb.verifyEditProfileButtonVisibility());

		draftpage = viewUserProfileWeb.goToMyDrafts();
		Assert.assertTrue(draftpage.verifyMyDraftPage());

		documentWeb = draftpage.goToMyFiles();
		Assert.assertTrue(documentWeb.verifyMyFileSendButton());

		tasksWeb = dashboardWeb.goToMyTasks();
		Assert.assertTrue(tasksWeb.verifyHeaderAddButton());

		dashboardWeb = dashboardWeb.goToSendAFile();
		Assert.assertTrue(dashboardWeb.verifySendAFile());
		dashboardWeb.closeSendAFileModal();

		approvalworkflowweb = dashboardWeb.gotoapprovalWorkflow();
		Assert.assertTrue(approvalworkflowweb.verifyToApprove());
		Assert.assertTrue(approvalworkflowweb.verifyPendingApproval());
		Assert.assertTrue(approvalworkflowweb.verifyRejected());

		settingsweb = dashboardWeb.gotoSettings();
		Assert.assertTrue(settingsweb.verifyGeneralTab());
		Assert.assertTrue(settingsweb.verifySetEmailAlertTab());
		Assert.assertTrue(settingsweb.verifySystemNotifications());

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		Assert.assertTrue(systemAdminWeb.verifySystemAdminPage());

		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		Assert.assertTrue(aspAdminWeb.verifyAspConfigurationPage());

		bannerPageWeb = dashboardWeb.gotoHighQHub();
		Assert.assertTrue(bannerPageWeb.verifyHighqHubLink());
		bannerPageWeb.browserBackNavigate();

		bannerPageWeb = dashboardWeb.gotoHelp();
		bannerPageWeb.switchWindow();
		Assert.assertTrue(bannerPageWeb.verifyHelpPage());
		bannerPageWeb.closeCurrentTab();

		bannerPageWeb.logout();
	}

	public void scenario2() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		bannerPageWeb.gotoLogout();
		Assert.assertTrue(bannerPageWeb.verifyOkButton());
		Assert.assertTrue(bannerPageWeb.verifyCancelButton());
		Assert.assertTrue(bannerPageWeb.verifyLogoutMessage(Message));
		bannerPageWeb.clickCancelInMessageBox();

		bannerPageWeb.logout();
		Assert.assertTrue(bannerPageWeb.verifyUserLogoutFromSystem());

	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}
}
