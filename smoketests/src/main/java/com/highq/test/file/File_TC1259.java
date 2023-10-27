package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dharti.patel
 */
public class File_TC1259 extends BannerPageWeb
{
	private WebDriver driver;

	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

	// String sysAdminEmail = "alpesh.soni@highq.com";
	// String sysAdminPassword = "Passw0rd";

	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";

	AdminPage adminPageWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	DashboardPage dashboardWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage addDoc;

	String jsonFileName = "preConfiguration_File_TC1259.json";

	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_SANDBOX};

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";

	String siteName = "File_TC1259";
	String normalUser = "normaluser1263@file.com";
	String siteAdmin = "siteadmin1263@file.com";

	String file = "doc1.txt";
	String fileDispalyName = "doc1";

	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	final String NAME = "name";
	final String VERSIONNOTES = "version notes";
	String placeholderFile = "Placeholder file";

	String placeHolderTags = "ptag1";
	String placeHolderVersionNote = "version1";
	String placeHolderDisclaimerText = "placeholder Disclaimer";

	private String adobeSignHostUrl = "https://secure.na1.echosign.com/";

	/**
	 * 
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @param appURL
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws JSONException
	 */
	@Parameters({"appURL"})
	@Test(priority = 1)
	public void File_TC1259(String appURL) throws IOException, InterruptedException, JSONException
	{
		preCondition(appURL);
		preConfiguration();
		senario1();
		senario2();
		senario3();
		senario4();
		scenario5();
		scenario6();
		scenario7();

	}

	/**
	 * @param appURL
	 * @throws InterruptedException
	 */
	public void preCondition(String appURL) throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		for (int i = 0; i < services.length; i++)
		{
			if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(services[i]))
			{
				systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(services[i], SystemAdminLabels.UI_TEXT_REMOVE);
				systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
			}
		}

		aspAdminWeb = systemAdminSystemSettingsWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableAPIOption(enableAPI);
		aspConfigurationWeb.setAppBaseURL(true, appURL);

		/* DocuSign (sandbox) Configure */
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices(docuSignTrue);
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();

		/* DocuSign (sandbox) On for every Site */
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);

		}
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);

		/* Adobe (sandbox) configure */
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();

		if (systemAdminSystemSettingsWeb.verifydThirdPArtyServicesOnAddServiceModal(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		}
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();

		/* Adobe (sandbox) On for every Site */
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();

	}

	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void senario1() throws IOException, InterruptedException
	{
		/* Scenario 1: To Check the Third party services in Site Admin. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(adminFilesWeb.verifyThirdPartyOption());

		for (int i = 0; i < services.length; i++)
		{
			Assert.assertTrue(adminFilesWeb.verifyThirdPartyService(services[i]));
			Assert.assertTrue(adminFilesWeb.verifyServiceDescription(services[i]));
			Assert.assertTrue(adminFilesWeb.verifyThirdPartyOption(services[i]));
		}

	}

	public void senario2() throws IOException, InterruptedException
	{
		/* Scenario 2: To Check the configured Third party services in Site Admin when status of Third party service is set as 'Enabled' from system admin. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		for (int i = 0; i < services.length; i++)
		{
			Assert.assertTrue(adminFilesWeb.verifySelectedValueOfService(services[i], AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ONDEFAULT));

		}

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		for (int i = 0; i < services.length; i++)
		{
			Assert.assertTrue(adminFilesWeb.verifySelectedValueOfService(services[i], AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFFDEFAULT));

		}

	}

	public void senario3() throws IOException, InterruptedException
	{
		/* Scenario 3: To Check the configured Third party services in Site Admin when status of service is set as “Disabled” from system admin. */

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(!adminFilesWeb.verifyThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));

	}

	public void senario4() throws IOException, InterruptedException
	{
		/* Scenario 4: To Check the configured Third party services in Site Admin when all services are disabled from system settings. */

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS);
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(!adminFilesWeb.verifyThirdPartyOption());

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(adminFilesWeb.verifyThirdPartyOption());

	}

	public void scenario5() throws IOException, InterruptedException
	{
		/* Scenario 5: To overwrite the Third party services status in site Admin from the value set in System settings. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		Assert.assertTrue(adminFilesWeb.verifySelectedValueOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON));

	}

	public void scenario6() throws IOException, InterruptedException
	{
		/* Scenario 6: To Authorise the DocuSign service from Site Admin */

		systemAdminWeb = adminFilesWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.clickOnMoreActionOptionOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
		Assert.assertTrue(systemAdminSystemSettingsWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		adminFilesWeb.saveFilesChanges();

	}

	public void scenario7() throws InterruptedException
	{
		/* Scenario 7: To Revoke authorise the DocuSign service from Site Admin */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.clickOnMoreActionOptionOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_REVOKEAUTHORISE);
		Assert.assertTrue(adminFilesWeb.verifyRevokeWindowModel(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));
		adminFilesWeb.clickOnRevokeButtonOnRevokeModel();
		adminFilesWeb.logout();
	}

}
