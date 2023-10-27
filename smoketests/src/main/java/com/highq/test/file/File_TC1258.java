package com.highq.test.file;

import java.io.IOException;
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
public class File_TC1258 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
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
	String docuSignTrue = "TRUE";

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String invalidClientSecretDocuSign = "a9267776a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";
	private String adobeSignHostUrl = "https://secure.na1.echosign.com/";

	// private String clientIdAdobeSign2 = "CBJCHBCAABAAoDrp6w3RLCf5H1rCitMhNbRjzroLoiLF";
	// private String clientSecretAdobeSign2 = "En0Qr7rPpbpGtE-snEC0XKyQPjdm30rk";

	String[] thirdPartyMoreActionOption = {SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION, "Remove"};
	String[] adobeOption = {SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_PRODUCTION};
	String[] adobeOptionAfterAuthorised = {SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_REVOKEAUTHORISE, "Remove"};
	String[] adobeOptionAfterReokeService = {SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE, "Remove"};

	String[] services = {SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_PRODUCTION, "DocuSign (production)", SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX};

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
	 */
	@Parameters({"appURL"})
	@Test(priority = 1)
	public void File_TC1258(String appURL) throws IOException, InterruptedException
	{
		preCondition(appURL);
		scenario1();
		scenario2();
		scenario3A();
		scenario3B();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
		scenario10();
		scenario11();
		scenario12();

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
		/* Scenario 1: To check 'Third party services' option in System settings. */
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyThirdPartyServicesOption());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddButtonThirdPartyService());
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario2() throws InterruptedException
	{
		/* Scenario 2: To check Third party service list modal window from System settings. */

		aspAdminWeb = gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices(docuSignTrue);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCloseButtonAddThirdPartyService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifySelectServiceMsg());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddButtonService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCancelButtonThirdPartyService());
		systemAdminSystemSettingsWeb.clickOnCancelButtonThirdPartyService();

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario3A() throws InterruptedException
	{
		/* Scenario 3A: To Add Docusign service from Service list modal window. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		for (int s = 0; s < services.length; s++)
		{
			if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(services[s]))
			{
				systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(services[s], SystemAdminLabels.UI_TEXT_REMOVE);
				systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
			}
		}

		aspAdminWeb = systemAdminSystemSettingsWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices(docuSignTrue);
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));

		for (int i = 0; i < thirdPartyMoreActionOption.length; i++)
		{
			systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(thirdPartyMoreActionOption[i]));
		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();

		if (!systemAdminSystemSettingsWeb.verifyNoServiceAvailableMsg())
		{
			systemAdminSystemSettingsWeb.selectAllServiceAddWindowModel();
			systemAdminSystemSettingsWeb.clickAddService();
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyNoServiceAvailableMsg());
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCloseAddWindowModel());
		}
		systemAdminSystemSettingsWeb.clickCloseAddWindowModel();
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario3B() throws InterruptedException
	{
		/* Scenario 3B: To Add Adobe Sign services from Service list modal window. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		for (int i = 0; i < adobeOption.length; i++)
		{
			if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(adobeOption[i]))
			{

				systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(adobeOption[i], SystemAdminLabels.UI_TEXT_REMOVE);
				systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
			}

			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(adobeOption[i]);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(adobeOption[i]));

			for (int j = 0; j < adobeOptionAfterReokeService.length; j++)
			{
				systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(adobeOption[i]);
				Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(adobeOptionAfterReokeService[j]));
			}
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario4() throws InterruptedException
	{
		/* Scenario 4: To remove the service after it is added. */

		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{

			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();

		}
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();

		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRemoveLabelRemoveModel());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCloseButtonRemoveModel());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRemoveMessgaeRemoveModel(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRemoveButtonInRemoveWindowModel());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCancelButtonRemoveModel());
		systemAdminSystemSettingsWeb.clickCancelButtonRemoveModel();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
		systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyMsgAfterRemoveService());
		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario5() throws InterruptedException
	{

		/* Scenario 5: To check fields in Configure modal window for Adobe Sign */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();

		}
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureWindowLabelOfESignatureService(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		systemAdminSystemSettingsWeb.enterAdobeSignHostUrl(adobeSignHostUrl);
		systemAdminSystemSettingsWeb.verifyReditectUrlOnConfigureService();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyClientIDInputboxOfConfigureESignatureService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyClientSecretKeyInputboxOfConfigureESignatureService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyTestOfConfigureESignatureService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCancelButtonOfConfigureESignatureService());
		systemAdminSystemSettingsWeb.clickOnCancelConfigureModel();

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario6() throws InterruptedException
	{

		/* Scenario 6: To generate the token in Configure modal window. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();

		}
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifySaveConfigureService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigurationInputBoxAfterSuccess());
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyStatusConfigureService(SystemAdminLabels.ADOBE_SIGN_SANDBOX));

		for (int i = 0; i < thirdPartyMoreActionOption.length; i++)
		{
			systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(thirdPartyMoreActionOption[i]));
		}

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario7() throws InterruptedException
	{

		/* Scenario 7: To check validation message in Configure modal window for Adobe Sign Service when wrong Client ID & secret key is provided. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{

			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, invalidClientSecretDocuSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyEnterValuesInConfigureService());
		systemAdminSystemSettingsWeb.clickOnCancelConfigureModel();

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario8() throws InterruptedException
	{

		/* Scenario 8: To check status dropdown field for the configured Adobe Third Party services. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX));

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyStatusConfigureServiceofAdobe(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario9() throws InterruptedException
	{

		/* Scenario 9: To check configure modal window for the already configured Adobe service. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);

		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyClientIDInputboxAfterConfigureService());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyClientSecretKeyInputboxAfterConfigureService());
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifySaveConfigureService());
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();

	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario10() throws InterruptedException
	{

		/* Scenario 10: To authorise the third party Adobe service after it is configured. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}
		aspAdminWeb = systemAdminSystemSettingsWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices(docuSignTrue);
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION);
		Assert.assertTrue(systemAdminSystemSettingsWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));

		for (int i = 0; i < adobeOptionAfterAuthorised.length; i++)
		{
			systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(adobeOptionAfterAuthorised[i]));
		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario11() throws InterruptedException
	{

		/* Scenario 11: To revoke the authorisation for the configured service. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();

		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);

		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX));

		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION);

		Assert.assertTrue(systemAdminSystemSettingsWeb.authoriseAdobeService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_REVOKEAUTHORISE);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRevokeAuthorisationLabel());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCloseButtonRevokeAuthWindow());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRevokeAuthBodyMessage(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyCancelRevokeAuthWindow());
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyRevokeButtonRevokeAuthWindow());
		systemAdminSystemSettingsWeb.clickOnRevokeButtonOfRevokeAuthWindow();
		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyAuthorisedService(SystemAdminLabels.ADOBE_SIGN_SANDBOX));

		for (int i = 0; i < adobeOptionAfterReokeService.length; i++)
		{
			systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
			Assert.assertTrue(systemAdminSystemSettingsWeb.verifyConfigureServiceMoreAction(adobeOptionAfterReokeService[i]));
		}
		// systemAdminSystemSettingsWeb.clickOnMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);

		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario12() throws InterruptedException
	{

		/* Scenario 12: To remove the third party service after it is authorised. */

		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
			systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		}

		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();

		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION);
		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAuthorisedService(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.UI_TEXT_REMOVE);
		systemAdminSystemSettingsWeb.clickRemoveButtonRemoveModel();
		Assert.assertTrue(!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
		systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);

		Assert.assertTrue(systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();
		systemAdminSystemSettingsWeb.closeGlobalMsg();
		logout();

	}

}
