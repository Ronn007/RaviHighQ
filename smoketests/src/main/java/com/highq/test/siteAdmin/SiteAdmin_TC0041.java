package com.highq.test.siteAdmin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.InsertSiteUserLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminGeneralPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminPageWeb;
import com.highq.pageobjects.pages.BannerPageWeb;

public class SiteAdmin_TC0041 extends BannerPageWeb
{

	public WebDriver webDriver;

	static String sysAdminEmail = "ronak.bhatt@highq.com";
	static String sysAdminPassword = "Admin@123";
	static String siteAdminName = "Site Admin";
	static String siteAdminEmail = "site.admin@highq.com";
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminGeneralPage adminGeneralWeb;
	AddUserPage addUserWeb;
	String sitePassword = "HighQ@123";
	String jsonFileName = "siteNavigation/preConfiguration_SiteAdmin_TC0045.json";
	String siteName = "SiteAdmincase";
	Timestamp startTime;
	Timestamp endTime;
	PreConfiguration preConfigurationTest = new PreConfiguration(driver);
	Map<String, List<String>> userMap = null;
	ConfigurationData configurationData;
	Map<Boolean, BannerPageWeb> result;

	private HashMap uHashMapserMap;

	@BeforeClass
	public void setUp()
	{
		webDriver = getDriver();
	}

	@Test(priority = 1)
	public void TC0041() throws InterruptedException, IOException, JSONException
	{
		// preconfiguration();
		// scenario1();
		// scenario2();
		// scenario3();
		// scenario4();
		// scenario5();
		scenaio6();

	}

	private void scenario1() throws InterruptedException, IOException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		AddUserPage addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.selectAllUsers();
		addUserWeb.removeUser();
		bannerPageWeb = addUserWeb.gotoDashboard();
		logout();

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.selectContactUsType("Email");
		adminGeneralWeb.clickOnContactUsSearchIcon();
		Assert.assertTrue(adminGeneralWeb.verifyUsersinContachUsModel("Site Admin", "site.admin@highq.com"));
		Assert.assertTrue(adminGeneralWeb.verifyUsersinContachUsModel("Contain Admin", "contain.admin@highq.com"));
		adminGeneralWeb.clickOncancelContactUsAdminUser();
		adminGeneralWeb.setContactUsEmailAddress("ronn@highq.com");
		adminGeneralWeb.clickOnSave();
		Assert.assertTrue(adminGeneralWeb.verifyContactUsLinkFooter());
		logout();

	}

	private void scenario2() throws InterruptedException, IOException, JSONException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.selectContactUsType("Email");
		adminGeneralWeb.clickOnContactUsSearchIcon();
		adminGeneralWeb.selectAllAdminUserContactUs();
		adminGeneralWeb.clickOnAdminUser();
		Assert.assertTrue(adminGeneralWeb.verifyemailURL_contactusLinkUserName("Site Admin"));
		Assert.assertTrue(adminGeneralWeb.verifyemailURL_contactusLinkUserName("Contain Admin"));
		adminGeneralWeb.clearAllContactUs();
		adminGeneralWeb.selectContactUsType("Email");
		adminGeneralWeb.clickOnContactUsSearchIcon();

		Assert.assertFalse(adminGeneralWeb.verifyAddButonAddUser());
		adminGeneralWeb.clickOncancelContactUsAdminUser();

		dashboardWeb = adminGeneralWeb.gotoDashboard();
		logout();

	}

	private void scenario3() throws InterruptedException, IOException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		AddUserPage addUserWeb = adminPageWeb.clickUsersInLeftPanel();

		addUserWeb.selectAllUsers();
		addUserWeb.removeUser();
		bannerPageWeb = addUserWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();

		startTime = addUserWeb.getStartDateTimeStamp();

		addUserWeb.clickAddUsers();
		uHashMapserMap = new HashMap<>();
		uHashMapserMap.put("highq.com", Arrays.asList("ronn"));
		addUserWeb.enterEmailIds(uHashMapserMap);
		clickOnAdd();

		endTime = addUserWeb.getEndDateTimeStamp();

		logout();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(verifyMail("ronn@highq.com", startTime, endTime, "abc", "Please click here to activate your account."));
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

	}

	private void scenario4() throws InterruptedException, IOException, JSONException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.selectContactUsType("URL");
		adminGeneralWeb.setContactUsURL("http://www.google.com");
		adminGeneralWeb.clickOnSave();
		Assert.assertTrue(adminGeneralWeb.verifyContactUsLinkFooter());
		adminGeneralWeb.clickOnFooterContactUslink();

		ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

		String url = driver.getCurrentUrl();

		Assert.assertTrue(url.equalsIgnoreCase("http://www.google.com/"));

	}

	private void scenario5() throws InterruptedException, IOException, JSONException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		List<String> useradd = new ArrayList<String>();
		useradd.add("admin.ronak@highq.com");
		createSiteUsersForNotInvition(useradd);
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel("admin.ronak@highq.com");
		By adminUserPermissionGroupsTabLink = By.id("siteAdmin_adminModulePermissionTabID");
		findVisibleElement(adminUserPermissionGroupsTabLink, Speed.slow).click();
		((AddUserWeb) addUserWeb).setRoles(SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN, true);
		addUserWeb.clickSaveInSetPermissions();

		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		scrollToBottom();
		adminGeneralWeb.clearAllSiteOwner();
		scrollToBottom();
		findPresentElement(By.xpath(".//*[@id='newOwnerList-tokenfield']")).sendKeys("admin.ronak");
		adminGeneralWeb.selectOptionFromAutoSuggest("admin.ronak@highq.com");
		Assert.assertTrue(adminGeneralWeb.verifySiteOwner("Admin Ronak"));

	}

	private void scenaio6() throws IOException, JSONException, InterruptedException
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel("abc.admin@highq.com");
		By adminUserPermissionGroupsTabLink = By.id("siteAdmin_adminModulePermissionTabID");
		findVisibleElement(adminUserPermissionGroupsTabLink, Speed.slow).click();
		((AddUserWeb) addUserWeb).setRoles(SiteAdminLabels.SITEADMIN_USERS_USERROLE_SITEADMIN, false);
		addUserWeb.clickSaveInSetPermissions();

		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		scrollToBottom();
		Assert.assertTrue(adminGeneralWeb.verifyContactUsLinkFooter());
		logout();

	}

	private void scenaio7() throws IOException, JSONException, InterruptedException
	{

	}

	public void clickOnAdd()
	{
		By nextButton = By.xpath("(//*[@class='fullContainer']//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_USERS_ADDSITEADMIN_USERS_NEXT + "'])[last()]");
		By lastname = By.xpath(".//*[@id='siteAdmin_addUser_step2_withoutLname_confirmModal_continueConfirmationID']");

		while (isDisplayed((nextButton), Speed.slow))
		{
			WebElement ele = findClickableElement(nextButton);
			if (ele.isDisplayed())
			{
				ele.click();

				if (isDisplayed(lastname, Speed.slow))
				{
					ele = findClickableElement(lastname);
					ele.click();

				}
			}
		}

		By addButton = By.xpath(".//*[@id='adminUserNextBtnBottom']");
		WebElement ele = findClickableElement(addButton, Speed.slow);
		if (ele.isDisplayed())
		{
			ele.click();
		}

		ele = findClickableElement(By.xpath(".//*[@id='addUserSendInviationBottomButton']"), Speed.slow);
		if (ele.isDisplayed())
		{
			ele.click();
			ele = findClickableElement(By.xpath(".//*[@id='addUserSendInvitationModal_addUserSendInvitationBtn']"), Speed.slow);
			if (ele.isDisplayed())
			{
				ele.click();
			}

		}

	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setDefaultContactUsLink(true);
		aspConfigurationWeb.saveConfigurations();

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName + getRandomString());
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		Assert.assertTrue(adminGeneralWeb.verifyemailURL_contactusLink("POC@highq.com"));
		dashboardWeb = adminGeneralWeb.gotoDashboard();
		logout();

	}

	public boolean verifyMail(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage)
	{
		String query = null;
		String mailContent = "";
		String mailBody = "";
		String htmlFileName = "MailContent.html";

		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
			Assert.assertTrue(false);
		else
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";

		mailContent = bannerPageWeb.getMailContent(query);
		if (mailContent.isEmpty() || mailContent == null)
			return false;
		else
		{
			bannerPageWeb.createHtmlFile(htmlFileName, mailContent);
			bannerPageWeb.getLocalHtmlPage(htmlFileName);
			mailto = mailto.substring(0, mailto.indexOf("@"));
			if (bannerPageWeb.verifyContent(mailto, emailMessage))
			{
				System.out.println(emailMessage + " is present");
				bannerPageWeb.closeCurrentTab();
				return true;
			}
		}
		return false;
	}

	private void openMailLink(String mailto)
	{
		String query = null;
		String mailBody = "";
		String htmlFileName = "MailContent.html";
		String mailContent;
		if (mailto.trim().isEmpty() || mailto.trim() == null)
		{
			Assert.assertTrue(false);
		}
		else
		{
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		mailBody = bannerPageWeb.getMailContent(query);
		bannerPageWeb.createHtmlFile(htmlFileName, mailBody);
		bannerPageWeb.getLocalHtmlPage(htmlFileName);
		bannerPageWeb.clickOnLinkInMailContentMessageBody("Please click here to activate your account.");
	}

	public void createSiteUsersForNotInvition(List<String> siteUsers)
	{
		AdminPage adminPageWeb = new AdminPageWeb(driver);
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.selectAllUsers();
		addUserWeb.removeUser();
		addUserWeb.clickAddUsers();

		addUserWeb.enterEmailIds(siteUsers);

		addUserWeb.initiateUserCreationProcess();

		finishUserCreationProcess();
	}

	public void finishUserCreationProcess()
	{
		By nextButton = By.xpath("(.//*[contains(text(),'" + InsertSiteUserLabels.INSERTSITEUSER_NEXT + "')])[last()]");
		By addButton = By.id("adminUserNextBtnTop");
		// finish operation
		while (isDisplayed(nextButton, Speed.slow))
		{
			WebElement nextButtonLink = findClickableElement(nextButton);
			nextButtonLink.click();

		}

		WebElement addUserLink = findClickableElement(addButton, Speed.slow);
		addUserLink.click();
	}
}
