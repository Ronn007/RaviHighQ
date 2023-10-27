package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.FileLabels;
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
public class File_TC1491 extends BannerPageWeb
{
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage addDoc;

	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";

	String siteName = "File_TC1491";

	String userName = "Siteadmin1491";
	String message2 = "Recipient Message";

	String jsonFileName = "preConfiguration_File_TC1491.json";

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";
	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	String[] services = {SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_PRODUCTION, "DocuSign (production)", SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX};

	String doc1 = "doc1.txt";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
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
	public void File_TC1491(String appURL) throws IOException, InterruptedException, JSONException
	{
		preCondition(appURL);
		preConfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();

	}

	/**
	 * @param appURL
	 * @throws InterruptedException
	 */
	public void preCondition(String appURL) throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
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
		if (!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		}
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE);
		systemAdminSystemSettingsWeb.configureThirdPatyCredentialsForAdobe(adobeSignHostUrl, clientIdAdobeSign, clientSecretAdobeSign, SystemAdminLabels.ADOBE_SIGN_SANDBOX, clientEmail, clientPwd);
		systemAdminSystemSettingsWeb.clickOnSaveConfigureService();

		/* Adobe (sandbox) On for every Site */
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		// systemAdminSystemSettingsWeb.logout();
	}

	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		/* Scenario 1: To check status field in File viewer page when 'Enable document review' is “OFF” */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setEnabelDocumentReviewWorkflow(false);
		adminFilesWeb.enableCheckInCheckOut(true);
		adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnSelectedFile(doc1);
		assertFalse(documentWeb.verifyStatusInViewer(FileLabels.UI_TEXT_UNALLOCATED));
		documentWeb.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario2() throws InterruptedException, IOException
	{
		/* Scenario 2: To check status field for the document having 'Sent for signature' status in File viewer page when 'Enable document review' is “OFF” */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setEnabelDocumentReviewWorkflow(false);
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		if (totalServiceOn == 1)
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

		}

		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		documentWeb.clickOnSelectedFile(doc1);

		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN));
		assertFalse(documentWeb.verifyStatusInViewer(FileLabels.UI_TEXT_UNALLOCATED));
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_AWAITING_ESIGN_REVOKESIGNINGREQUEST));
		documentWeb.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario3() throws InterruptedException, IOException
	{
		/* Scenario 3: To check status field for the document having 'Sent for signature' status in File viewer page when 'Enable document review' is “ON” */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setEnabelDocumentReviewWorkflow(true);
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		if (totalServiceOn == 1)
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

		}

		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		documentWeb.clickOnSelectedFile(doc1);
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.UI_TEXT_UNALLOCATED));
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN));
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_AWAITING_ESIGN_REVOKESIGNINGREQUEST));
		documentWeb.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario4() throws InterruptedException, IOException
	{
		/* Scenario 4: To send file for e-Signing from File viewer page & check the Global notification in viewer. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnSelectedFile(doc1);
		documentWeb.clickOnMoreActionOfFileViewer();
		if (totalServiceOn == 1)
		{

			documentWeb.gotoOptiOnOnMoreActionOfFileViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{

			documentWeb.gotoOptiOnOnMoreActionOfFileViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		}

		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();
		documentWeb.clickOnCrossButtonInFileviewer();

		documentWeb.gotoFileModule();
		documentWeb.clickOnSelectedFile(doc1);
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN));
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.FILES_THIRDPARTYSERVICE_AWAITING_ESIGN_REVOKESIGNINGREQUEST));

		documentWeb.clickOnMoreActionOfFileViewer();
		if (totalServiceOn == 1)
		{
			assertFalse(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			documentWeb.closeMoreActionInViewer();
		}
		else
		{
			assertFalse(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO));
			documentWeb.closeMoreActionInViewer();
		}

		documentWeb.clickOnRevokeLinkInViewer();
		documentWeb.clickOnRevokeButtonInRevokeDialog();
		assertTrue(documentWeb.verifyGlobalMsgRevokedSuccessfully());
		assertTrue(documentWeb.verifyStatusInViewer(FileLabels.VOIDEDSTATUS));

		documentWeb.clickOnMoreActionOfFileViewer();
		if (totalServiceOn == 1)
		{
			assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));

		}
		else
		{
			assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO));

		}

		documentWeb.clickOnCrossButtonInFileviewer();
		documentWeb.logout();

	}

}
