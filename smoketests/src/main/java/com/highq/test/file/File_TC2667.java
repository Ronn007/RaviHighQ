package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dharti.patel
 */
public class File_TC2667 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	// String sysAdminEmail = "alpesh.soni@highq.com";
	// String sysAdminPassword = "Passw0rd";

	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

	// String sysAdminEmail = "tom.chick@highq.com";
	// String sysAdminPassword = "password";

	AdminPage adminPageWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;

	String enableAPI = "TRUE";

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String invalidClientSecretDocuSign = "a9267776a47-dffe-4cf9-832e-3ffd2edc62e3";
	// private String clientIdAadobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	// private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	private String clientIdDocuSign2 = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign2 = "14d64429-c3ec-4813-aaca-959ca2f874fa";
	private String clientEmail2 = "dharti.patel@highq.com";
	private String clientPwd2 = "Jsnmomdad@8938";

	private String serviceDisabledMsg = "This service cannot be disabled as some documents are still pending a signature.";

	String[] thirdPartyMoreActionOption = {SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION, "Remove"};

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
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	@Parameters({"appURL"})
	@Test(priority = 1)
	public void File_TC2667(String appURL) throws IOException, JSONException, InterruptedException
	{
		preCondition(appURL);
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();

	}

	/**
	 * @param appURL
	 * @throws InterruptedException
	 */
	public void preCondition(String appURL) throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableAPIOption(enableAPI);
		aspConfigurationWeb.setAppBaseURL(true, appURL);
		aspConfigurationWeb.saveConfigurations();

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario1() throws InterruptedException
	{
		/* Scenario 1: To check 'Third party services' option in ASP Admin for Docu Sign. */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();

		/* verify DocuSign (sandbox) service */
		Assert.assertTrue(aspConfigurationWeb.verifyDocuSignSandboxServiceLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyDocuSignSandboxServiceDropDownOption());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureLinkForService(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));

		/* verify DocuSign (production) service */
		Assert.assertTrue(aspConfigurationWeb.verifyDocuSignProductionServiceLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyDocuSignProductionServiceDropDownOption());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureLinkForService(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_PRODUCTION));

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario2() throws InterruptedException
	{
		/* Scenario 2: To check fields in Configure modal window for Docu Sign */

		/* verify Configure Model Window of DocuSign (sandbox) */

		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureWindowLabelOfESignatureService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));
		Assert.assertTrue(aspConfigurationWeb.verifyClientIdLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyClientSecretKeyLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyClientIDInputboxOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyClientSecretKeyInputboxOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureCloseWindow());
		Assert.assertTrue(aspConfigurationWeb.verifyTestOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyCancelButtonOfConfigureESignatureService());
		aspConfigurationWeb.clickOnCancelConfigureModel();

		/* verify Configure Model Window of DocuSign (production) */
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_PRODUCTION);
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureWindowLabelOfESignatureService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_PRODUCTION));
		Assert.assertTrue(aspConfigurationWeb.verifyClientIdLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyClientSecretKeyLabel());
		Assert.assertTrue(aspConfigurationWeb.verifyClientIDInputboxOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyClientSecretKeyInputboxOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigureCloseWindow());
		Assert.assertTrue(aspConfigurationWeb.verifyTestOfConfigureESignatureService());
		Assert.assertTrue(aspConfigurationWeb.verifyCancelButtonOfConfigureESignatureService());
		aspConfigurationWeb.clickOnCancelConfigureModel();

	}

	public void scenario3() throws InterruptedException
	{
		/* Scenario 3: To generate the token in Configure modal window. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices("TRUE");
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		Assert.assertTrue(aspConfigurationWeb.verifyConfigurationServiceMessage());
		Assert.assertTrue(aspConfigurationWeb.verifySaveConfigureService());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigurationInputBoxAfterSuccess());
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		if (!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		}

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyStatusConfigureService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));

		for (int i = 0; i < thirdPartyMoreActionOption.length; i++)
		{
			systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(thirdPartyMoreActionOption[i]));
		}

	}

	public void scenario4() throws InterruptedException
	{
		/* Scenario 4: To check validation message in Configure modal window when wrong Client ID & secret key is provided. */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, invalidClientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd);
		Assert.assertTrue(aspConfigurationWeb.verifyEnterValuesInConfigureService());
		aspConfigurationWeb.clickOnCancelConfigureModel();

	}

	public void scenario5() throws InterruptedException
	{
		/* Scenario 5: To check configure modal window for the already configured service. */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();

		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.verifValuesofThirdPartyConfigure());
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign2, clientSecretDocuSign2, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail2, clientPwd2));
		Assert.assertTrue(aspConfigurationWeb.verifyConfigurationServiceMessage());
		Assert.assertTrue(aspConfigurationWeb.verifySaveConfigureService());
		Assert.assertTrue(aspConfigurationWeb.verifyConfigurationInputBoxAfterSuccess());
		aspConfigurationWeb.clickOnSaveConfigureService();

	}

	public void scenario6() throws InterruptedException
	{

		/* Scenario 6: Remove DocuSign Service from System Settings and again trying to add. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.clickConfigureLinkOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.verifyClientIDInputboxOfConfigureESignatureService());
		aspConfigurationWeb.clickOnCancelConfigureModel();

	}

	public void scenario7() throws InterruptedException
	{

		/* Scenario 7: Remove DocuSign Service from ASP admin side and verify on System Setting. */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices("FALSE");

		if (!aspConfigurationWeb.verifyDisableDocuSignModelOpend())
		{
			aspConfigurationWeb.saveConfigurations();
			systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			Assert.assertFalse(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		}
		else
		{
			Assert.assertTrue(aspConfigurationWeb.verifyDisableDocuSignMessage());
			aspConfigurationWeb.closeDisableDocuSign();
		}

	}

	public void scenario8() throws InterruptedException
	{

		/* Scenario 8: Verify message on trying to remove DocuSign Service if any document is pending for Sign */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices("FALSE");
		if (!aspConfigurationWeb.verifyDisableDocuSignModelOpend())
		{
			aspConfigurationWeb.saveConfigurations();
			systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			Assert.assertFalse(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		}
		else
		{
			Assert.assertTrue(aspConfigurationWeb.verifyDisableDocuSignMessage());
			aspConfigurationWeb.closeDisableDocuSign();
		}

	}

	public void scenario9() throws InterruptedException
	{

		/* Scenario 9: To check weather services are available or not for docusign at system admin side */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.clickOnESignatureServices();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices("FALSE");

		if (!aspConfigurationWeb.verifyDisableDocuSignModelOpend())
		{
			aspConfigurationWeb.saveConfigurations();
			systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();

			Assert.assertFalse(systemAdminSystemSettingsWeb.verifydThirdPArtyServicesOnAddServiceModal(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
			systemAdminSystemSettingsWeb.clickOnCancelButtonThirdPartyService();

			aspAdminWeb = systemAdminSystemSettingsWeb.gotoASPAdmin();
			aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
			aspConfigurationWeb.clickOnESignatureServices();
			aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices("TRUE");
			aspConfigurationWeb.saveConfigurations();
			aspConfigurationWeb.closeGlobalMsg();

			systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();

			Assert.assertTrue(systemAdminSystemSettingsWeb.verifydThirdPArtyServicesOnAddServiceModal(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
			systemAdminSystemSettingsWeb.clickOnCancelButtonThirdPartyService();
		}
		else
		{
			Assert.assertTrue(aspConfigurationWeb.verifyDisableDocuSignMessage());
			aspConfigurationWeb.closeDisableDocuSign();

		}
		logout();
	}
}
