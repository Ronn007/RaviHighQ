package com.highq.test.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
public class File_TC1263 extends BannerPageWeb
{
	private WebDriver driver;

	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

	// String sysAdminEmail = "alpesh.soni@highq.com";
	// String sysAdminPassword = "Passw0rd";

	// String sysAdminEmail = "tom.chick@highq.com";
	// String sysAdminPassword = "password";

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

	String jsonFileName = "preConfiguration_File_TC1263.json";

	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_SANDBOX};

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";

	String siteName = "File_TC1263";
	String normalUser = "normaluser1263@file.com";
	String siteAdmin = "siteAdmin1263@file.com";

	String file = "doc1.txt";

	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	final String NAME = "name";
	final String VERSIONNOTES = "version notes";
	String placeholderFile = "Placeholder file";

	String placeHolderTags = "ptag1";
	String placeHolderDisclaimerText = "placeholder Disclaimer";
	String placeholderFileName = "placeholder1";

	String userName = "Siteadmin1263";
	String invalidEmail = "abcdef.com";
	String userName1 = "internaladmin.user@automation.com";

	String serviceName = "Send to Adobe Sign (sandbox)";

	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;

	String folderName = "Folder 1";
	String folderDescription = "Testing folder ...";
	String doc1 = "doc1.txt";
	String fileSize = "filesize.pdf";
	String withoutExt = "EventCAT";
	String folder = "Documents.zip";
	String pwdFile = "PasswordProtected.pdf";
	String mp3File = "mp3File.mp3";

	String smg = "The maximum total file size allowed for the signing process is 25MB";
	String message = "Unable to convert Document(EventCAT)";
	String message1 = "Document is password protected";
	String message2 = "Recipient Message";
	String messag3 = "Invalid email address";
	String messag4 = "Unable to convert Document(mp3File.mp3)";

	String doc2 = "doc2.txt";

	String[] filesdemo = {"doc1.txt", "doc2.txt", "doc3.txt"};

	String[] sendToOption = {FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + SystemAdminLabels.ADOBE_SIGN_SANDBOX};

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
	public void File_TC1263(String appURL) throws InterruptedException, IOException, JSONException
	{
		preCondition(appURL);
		preConfiguration();
		senario1A();
		senario1B();
		senario1C();
		senario2A();
		senario2B();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7A();
		scenario7B();
		scenario8A();
		scenario9();
		scenario10A();
		scenario10B();
		scenario10C();
		scenario11();
		scenario12();
		scenario13();
		scenario14();
		scenario15();
		scenario16A();
		scenario16B();
		scenario17();

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
		systemAdminSystemSettingsWeb.logout();

	}

	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));

		/* Atleast 1 third party service must be ON from “Site Admin --> Files” */
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();

		/* Some Files must be existing in the site. */
		documentWeb = gotoFileModule();
		documentWeb.deleteAllDocs();
		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();
		documentWeb.logout();
	}

	public void senario1A() throws IOException, InterruptedException
	{
		/* Scenario 1A: To check 'Send to' option in Files module when Login user is having only File view permission. */
		bannerPageWeb = login(normalUser, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = gotoFileModule();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		for (int i = 0; i < sendToOption.length; i++)
		{
			Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(sendToOption[i]));
		}

		documentWeb.logout();
	}

	public void senario1B() throws IOException, InterruptedException
	{
		/* Scenario 1B: To check 'Send to' option in Files module when single service is enabled in site admin. */
		try
		{
			login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);
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
			addDoc.setFileuploadpath(file);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
			documentWeb.clickOnMoreActionOfParticularFile(file);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));

			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));

			}

			/* verify send to option in My files */

			systemAdminWeb = gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS);
			int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
			String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);
			systemAdminSystemSettingsWeb.saveSettings();

			documentWeb = goToMyFiles();
			if (documentWeb.verifyFileInMyFile(doc1))
			{
				documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");
			}
			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(doc1);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
			documentWeb.clickOnMoreActionMyFiles(doc1);
			if (totalServiceOnSystemAdmin == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		finally
		{
			systemAdminWeb = gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
			systemAdminSystemSettingsWeb.saveSettings();
			systemAdminSystemSettingsWeb.closeGlobalMsg();

			dashboardWeb = systemAdminSystemSettingsWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
			adminFilesWeb.saveFilesChanges();

		}

	}

	public void senario1C() throws IOException, InterruptedException
	{
		/* Scenario 1C: Verify 'Send to...' options' disability in Action menu (COL-63287) */
		try
		{

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			int totalServiceOn = adminFilesWeb.countServiceOn();
			String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
			documentWeb = gotoFileModule();

			/** Add Placeholder file */

			if (documentWeb.verifyFiles(placeholderFileName))
			{
				documentWeb.deleteFile(placeholderFileName);
			}
			documentWeb.selectItemFromNew(placeholderFile);
			Map<String, String> placeHolderMap = new HashMap<>();
			placeHolderMap.put(NAME, placeholderFileName);
			placeHolderMap.put(TAGS, placeHolderTags);
			placeHolderMap.put(DISCLAIMER, placeHolderDisclaimerText);
			documentWeb.addPlaceHolderFileDetails(placeHolderMap);
			documentWeb.clickAddInModal();
			documentWeb.gotoFileModule();

			documentWeb.selectFileCheckBox("placeholder1.placeholder");
			String fileExtension = documentWeb.getFileExtension("placeholder1.placeholder");
			documentWeb.clickOnActionButton();

			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifySendToInActionButton(fileExtension, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{

				Assert.assertTrue(documentWeb.verifySendToInActionButton(fileExtension, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));

			}

			/* already checked out file */
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(true);
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
			documentWeb.clickAddInModal();
			documentWeb.gotoMoreActions(doc1, FileLabels.DOCUMENT_CHECKOUT_MESSAGE);
			documentWeb.clickOnCheckOut();
			documentWeb.clickOnMoreActionOfParticularFile(doc1);

			if (documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_CHECKIN_MESSAGE))
			{
				documentWeb.selectFileCheckBox(doc1);
				documentWeb.clickOnActionButton();
				if (totalServiceOn == 1)
				{
					Assert.assertTrue(documentWeb.verifySendToDisabledInActionButton(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
				}
				else
				{

					Assert.assertTrue(documentWeb.verifySendToDisabledInActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));

				}
			}

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		finally
		{
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(false);
			adminFilesWeb.saveFilesChanges();

		}

		/* sent for signature file is wrokflow not done. */
	}

	public void senario2A() throws IOException, InterruptedException
	{
		/* Scenario 2: To check 'Send to' option in Files module when multiple services are enabled in site admin. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(file))
		{
			documentWeb.deleteFile(file);
		}

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(file);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		/* verify send to option in More Action */
		String fileExtension = documentWeb.getFileExtension(file);
		documentWeb.clickOnMoreActionOfParticularFile(file);
		if (totalServiceOn == 1)
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
		}
		else
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
		}
		/* verify send to option in Action Menu */

		documentWeb.selectFileCheckBox(file);
		documentWeb.clickOnActionButton();
		if (totalServiceOn == 1)
		{
			Assert.assertTrue(documentWeb.verifySendToInActionButton(fileExtension, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));

		}
		else
		{

			Assert.assertTrue(documentWeb.verifySendToInActionButton(fileExtension, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));

		}

	}

	public void senario2B() throws IOException, InterruptedException
	{
		/* Scenario 2B: Click on send to will open services modal in case of multiple services configured. if multiple file selected than it will shows only docusign service in modal (COL-63569) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		if (adminFilesWeb.verifyThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX))
		{

			adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

		}

		if (adminFilesWeb.verifyThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX))

		{

			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

		}

		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();
		documentWeb.deleteAllDocs();

		for (int i = 0; i < filesdemo.length; i++)
		{
			if (documentWeb.verifyFiles(filesdemo[i]))
			{
				documentWeb.deleteFile(filesdemo[i]);

			}

			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(filesdemo[i]);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
			documentWeb.gotoFileModule();

		}

		documentWeb.clickSelectAllCheckbox();
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());

		Assert.assertTrue(documentWeb.verifyServiceOnSendToModel(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX));
		documentWeb.clickOnCloseButtonSendToModel();

	}

	public void scenario3() throws IOException, InterruptedException
	{
		/* Scenario 3: To check 'Send to' option in Files module when no service is enabled in site admin. */
		try
		{

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);

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
			documentWeb.clickAddInModal();

			/* verify send to option in More Action */
			String fileExtension = documentWeb.getFileExtension(doc1);
			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			/* verify send to option in Action Menu */

			documentWeb.selectFileCheckBox(doc1);
			documentWeb.clickOnActionButton();
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifySendToInActionButton(fileExtension, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{

				Assert.assertTrue(!documentWeb.verifySendToInActionButton(fileExtension, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));

			}

			/* verify send to option for Add Folder */
			if (documentWeb.verifyFiles(folderName))
			{
				documentWeb.deleteFile(folderName);

			}
			documentWeb.selectItemFromNew("Folder");
			documentWeb.createNewFolderInRoot(folderName, folderDescription);
			documentWeb.clickAddInModal();
			documentWeb.gotoFileModule();
			documentWeb.clickOnMoreActionOfParticularFile(folderName);
			if (totalServiceOn == 1)

			{
				Assert.assertTrue(!documentWeb.verifyMoreActionsItem(folderName, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionsItem(folderName, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			/* verify send to option for file and folder in My files */
			systemAdminWeb = gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
			String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);

			documentWeb = goToMyFiles();

			if (!documentWeb.verifyFileInMyFile(doc1))
			{
				documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");
			}
			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(doc1);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
			documentWeb.clickOnMoreActionMyFiles(doc1);
			if (totalServiceOnSystemAdmin == 1)

			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		finally
		{
			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
			adminFilesWeb.saveFilesChanges();

		}

	}

	public void scenario4() throws IOException, InterruptedException
	{
		/* Scenario 4: To check 'Send to' option for checked out file in when third party service is enabled in site admin. */
		try
		{

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(true);
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
			documentWeb.logout();

			bannerPageWeb = login(siteAdmin, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			documentWeb = gotoFileModule();
			documentWeb.gotoMoreActions(doc1, FileLabels.DOCUMENT_CHECKOUT_MESSAGE);
			documentWeb.clickOnCheckOut();
			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			documentWeb.clickOnSelectedFile(doc1);
			documentWeb.clickOnMoreActionOfFileViewer();
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}
			documentWeb.clickOnCrossButtonInFileviewer();
			documentWeb.logout();

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			documentWeb = gotoFileModule();
			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			documentWeb.clickOnSelectedFile(doc1);
			documentWeb.clickOnMoreActionOfFileViewer();
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(!documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}
			documentWeb.clickOnCrossButtonInFileviewer();
			documentWeb.logout();

			bannerPageWeb = login(siteAdmin, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			documentWeb = gotoFileModule();
			documentWeb.gotoMoreActions(doc1, FileLabels.DOCUMENT_CHECKIN_MESSAGE);
			documentWeb.clickOnCancelCheckOut();
			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			documentWeb.clickOnSelectedFile(doc1);
			documentWeb.clickOnMoreActionOfFileViewer();
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}
			documentWeb.clickOnCrossButtonInFileviewer();
			documentWeb.logout();

			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);
			documentWeb = gotoFileModule();
			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionOfParticularFile(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}

			documentWeb.clickOnSelectedFile(doc1);
			documentWeb.clickOnMoreActionOfFileViewer();
			if (totalServiceOn == 1)
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
			}
			else
			{
				Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
			}
			documentWeb.clickOnCrossButtonInFileviewer();

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		finally
		{
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(false);
			adminFilesWeb.saveFilesChanges();

		}

	}

	public void scenario5()
	{
		/* Scenario 5: To check 'Check out' option for the document which is sent for the e-Sign. */

		try
		{
			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);

			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableStatus(true);
			adminFilesWeb.enableCheckInCheckOut(true);
			adminFilesWeb.saveFilesChanges();

			/* send Document Without fields */

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
			documentWeb.clickAddInModal();
			documentWeb.selectFileCheckBox(doc1);
			documentWeb.clickActionButton();
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
			documentWeb.enterDetailsIntoSendToDialog(userName, message2);
			documentWeb.clickOnSendButtonInRecipientDialog();
			documentWeb.verifyTaggingInterfaceWindow();
			documentWeb.clickOnSendTaggingInterface();
			documentWeb.sendDocumentWithoutFields();

			documentWeb.clickOnMoreActionOfParticularFile(doc1);
			Assert.assertTrue(!documentWeb.verifyMoreActionOptionOfParticularFile("Check out"));

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		finally
		{

			adminPageWeb = documentWeb.gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableStatus(false);
			adminFilesWeb.enableCheckInCheckOut(false);
			adminFilesWeb.saveFilesChanges();
		}

	}

	public void scenario6() throws IOException, InterruptedException
	{
		/* Scenario 6: To check 'Send to' option in File viewer page. */

		documentWeb = gotoFileModule();
		adminPageWeb = documentWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableStatus(false);
		adminFilesWeb.enableCheckInCheckOut(false);
		adminFilesWeb.saveFilesChanges();

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		if (adminFilesWeb.verifyThirdPartyService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX))
		{

			adminFilesWeb.setDocuSign(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

		}

		if (adminFilesWeb.verifyThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX))

		{

			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

		}
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
		documentWeb.clickAddInModal();

		documentWeb.clickOnSelectedFile(doc1);
		documentWeb.clickOnMoreActionOfFileViewer();

		if (totalServiceOn == 1)
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));
		}
		else
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
		}
		documentWeb.clickOnCrossButtonInFileviewer();

		/* verify send to option in My files */
		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
		String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);

		documentWeb = goToMyFiles();

		if (documentWeb.verifyFileInMyFile(doc1))
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.clickOnSelectedFile(doc1);
		documentWeb.clickOnMoreActionOfFileViewer();
		if (totalServiceOnSystemAdmin == 1)
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin));
		}
		else
		{
			Assert.assertTrue(documentWeb.verifyMoreActionOptionInViewer(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim()));
		}
		documentWeb.clickOnCrossButtonInFileviewer();

	}

	public void scenario7A() throws IOException, InterruptedException
	{
		/* Scenario 7A: To send black listed file (third party side) for e-Signing. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		documentWeb = gotoFileModule();
		if (documentWeb.verifyFiles(mp3File))
		{
			documentWeb.deleteFile(mp3File);
		}

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(mp3File);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		documentWeb.clickOnMoreActionOfParticularFile(mp3File);
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.verifyErrorMessageForFileSendTo(messag4));
		documentWeb.confirmDeleteCommentMessageBox();

	}

	public void scenario7B() throws IOException, InterruptedException
	{
		/* Scenario 7B: To send a file for e-Sign & sign back that document by Recipient user. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(pwdFile))
		{
			documentWeb.deleteFile(pwdFile);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(pwdFile);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnMoreActionOfParticularFile(pwdFile);
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.verifyErrorMessageForFileSendTo(message1));
		documentWeb.confirmDeleteCommentMessageBox();
		if (documentWeb.verifyFiles(withoutExt))
		{
			documentWeb.deleteFile(withoutExt);

		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(withoutExt);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.clickOnMoreActionOfParticularFile(withoutExt);
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.verifyErrorMessageForFileSendTo(message));
		documentWeb.confirmDeleteCommentMessageBox();

		/* verify send to option in My files */
		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
		String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);

		documentWeb = goToMyFiles();
		String[] filenames = {pwdFile, withoutExt};
		for (int i = 0; i < filenames.length; i++)
		{
			if (documentWeb.verifyFileInMyFile(filenames[i]))
			{
				documentWeb.clickOnMoreActionOptionInMyFile(filenames[i], "Delete");

			}

			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(filenames[i]);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInAddFileModal();
		}

		if (totalServiceOnSystemAdmin == 1)
		{
			documentWeb.clickOnMoreActionOptionInMyFile(pwdFile, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
		}
		else
		{
			documentWeb.clickOnMoreActionOptionInMyFile(pwdFile, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		}

		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.verifyErrorMessageForFileSendTo(message1));
		documentWeb.confirmDeleteCommentMessageBox();

		if (totalServiceOnSystemAdmin == 1)
		{
			documentWeb.clickOnMoreActionOptionInMyFile(withoutExt, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
		}
		else
		{
			documentWeb.clickOnMoreActionOptionInMyFile(withoutExt, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		}
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.verifyErrorMessageForFileSendTo(message));
		documentWeb.confirmDeleteCommentMessageBox();

	}

	public void scenario8A() throws IOException, InterruptedException
	{

		/* Scenario 8A: To check 'Send to' option when file is checked out simultaneously. */
		try
		{

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);

			adminPageWeb = dashboardWeb.gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(true);
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
			documentWeb.gotoMoreActions(doc1, FileLabels.DOCUMENT_CHECKOUT_MESSAGE);
			documentWeb.clickOnCheckOut();

			documentWeb.selectFileCheckBox(doc1);
			documentWeb.clickActionButton();
			Assert.assertTrue(documentWeb.verifySendToDisabledInActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO));

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		finally
		{

			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.enableCheckInCheckOut(false);
			adminFilesWeb.saveFilesChanges();

		}
	}

	public void scenario9() throws IOException, InterruptedException
	{
		/* Scenario 9: To check options in “Send to” modal window. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
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
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		Assert.assertTrue(documentWeb.verifyDetailsOnSendToDialog(doc1));
		documentWeb.clickOnCloseButtonSendToModel();

		/* verify send to option in My files */
		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
		String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);

		documentWeb = goToMyFiles();
		if (documentWeb.verifyFileInMyFile(doc1))
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");

		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		if (totalServiceOnSystemAdmin == 1)
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
		}
		else
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		}

		Assert.assertTrue(documentWeb.verifyDetailsOnSendToDialog(doc1));
		documentWeb.clickOnCloseButtonSendToModel();

	}

	public void scenario10A() throws InterruptedException, IOException
	{
		/* Scenario 10A: To check Recipient modal window after single third party service (Adobe Sign) is selected. */
		/* Note: scenario 11 is covered in it */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
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
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();

		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		Assert.assertTrue(documentWeb.verifyAdobeDialogServiceName(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		Assert.assertTrue(documentWeb.verifyAdobeDialogEmailBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogNameBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogMessageBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogCloseButton());
		Assert.assertTrue(documentWeb.verifyAdobeDialogSendButton());

		documentWeb.clickOnCloseOfAdobeSignDialoobx();

		/* verify send to option in My files */
		systemAdminWeb = gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
		String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);

		documentWeb = goToMyFiles();
		if (documentWeb.verifyFileInMyFile(doc1))
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");

		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		if (totalServiceOnSystemAdmin == 1)
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
		}
		else
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		}

		documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.ADOBE_SIGN_SANDBOX);
		Assert.assertTrue(documentWeb.verifyAdobeDialogServiceName(SystemAdminLabels.ADOBE_SIGN_SANDBOX));
		Assert.assertTrue(documentWeb.verifyAdobeDialogEmailBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogNameBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogMessageBox());
		Assert.assertTrue(documentWeb.verifyAdobeDialogCloseButton());
		Assert.assertTrue(documentWeb.verifyAdobeDialogSendButton());
		documentWeb.clickOnCloseOfAdobeSignDialoobx();

	}

	public void scenario10B() throws IOException, InterruptedException
	{
		/* Scenario 10B: To check Recipient modal window after single third party service (Docu Sign) is selected from more action (COL-63288) */

		try
		{

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			if (adminFilesWeb.verifyThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
			{
				adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);
			}

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

			documentWeb.selectFileCheckBox(doc1);
			documentWeb.clickActionButton();
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);
			Assert.assertTrue(documentWeb.verifyDocuSignSendToModel(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX));
			Assert.assertTrue(documentWeb.verifyOptionInNeedsToSignOnRecepientDialog());
			documentWeb.clickOnCloseInRecipientDialog();

			/* verify send to option in My files */
			systemAdminWeb = gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, FileLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_DISABLED);
			int totalServiceOnSystemAdmin = systemAdminSystemSettingsWeb.totalSevriceOn();
			String serviceNmSystemAdmin = systemAdminSystemSettingsWeb.getOnServiceName(totalServiceOnSystemAdmin);
			systemAdminSystemSettingsWeb.saveSettings();

			documentWeb = goToMyFiles();
			if (documentWeb.verifyFileInMyFile(doc1))
			{
				documentWeb.clickOnMoreActionOptionInMyFile(doc1, "Delete");

			}
			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(doc1);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInAddFileModal();
			if (totalServiceOnSystemAdmin == 1)
			{
				documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
			}
			else
			{
				documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
			}
			Assert.assertTrue(documentWeb.verifyDetailsOnRecepientDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX));

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		finally
		{
			documentWeb.clickOnCloseInRecipientDialog();
			systemAdminWeb = documentWeb.gotoSystemAdmin();
			systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
			systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
			systemAdminSystemSettingsWeb.saveSettings();
			systemAdminSystemSettingsWeb.closeGlobalMsg();

			dashboardWeb = gotoDashboard();
			dashboardWeb.searchSite(siteName);
			adminPageWeb = gotoAdminModule();
			adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
			adminFilesWeb.saveFilesChanges();

		}

	}

	public void scenario10C() throws IOException, InterruptedException
	{

		/* Scenario 10C: To check Recipient modal window after multiple third party service (Docu Sign) is selected from Action Menu. (COL-63288) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);

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

		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();
		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(documentWeb.verifyDocuSignSendToModel(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX));
		Assert.assertTrue(documentWeb.verifyOptionInNeedsToSignOnRecepientDialog());
		documentWeb.clickOnCloseInRecipientDialog();

	}

	public void scenario11() throws IOException, InterruptedException
	{
		/* Scenario 11: "Need to sign" drop down in "Multiple recipients and actions" modal (COL-63572) */
		// Note: this case is convered in 10A.
	}

	public void scenario12() throws IOException, InterruptedException
	{

		/* Scenario 12: To check validation message on entering wrong email syntax in Recipient modal window. */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
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

		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();
		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(documentWeb.verifyEmailOfRecipient(messag3, userName, invalidEmail, message2));
		documentWeb.clickOnCloseInRecipientDialog();

	}

	public void scenario13() throws IOException, InterruptedException
	{
		/* Scenario 13: To check Recipient Name & Recipient email text field auto fills on selecting Recipient email/Name from auto suggest user list (COL-63571) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
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
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();
		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.setMember(userName);
		Assert.assertTrue(documentWeb.verifyDisabledRecipientNameAndEmailTextbox());
		documentWeb.clickOnCloseInRecipientDialog();

	}

	public void scenario14() throws IOException, InterruptedException
	{
		/* Scenario 14: To check Recipient field after Recipient token is generated (COL-63571) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

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

		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();
		documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message);
		Assert.assertTrue(documentWeb.verifyDisabledRecipientNameAndEmailTextbox());
		Assert.assertTrue(documentWeb.verifyRecipientToken(userName));
		documentWeb.clickOnCancelGeneratedToken();
		Assert.assertTrue(documentWeb.verifyEmptyTextField());
		documentWeb.clickOnCloseInRecipientDialog();

	}

	public void scenario15() throws IOException, InterruptedException
	{
		/* Scenario 15: Verify message on trying to process esign for more than 25MB file (COL-63569) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);

		documentWeb = gotoFileModule();
		if (documentWeb.verifyFiles(fileSize))
		{
			documentWeb.deleteFile(fileSize);
		}

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileSize);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileSize);
		documentWeb.clickActionButton();

		if (totalServiceOn == 1)

		{
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);

		}

		Assert.assertTrue(documentWeb.verifyFileSizeMessage(smg));
		documentWeb.clickOnCloseButtonSendToModel();

	}

	public void scenario16A() throws IOException, InterruptedException
	{
		/* Scenario 16A: Verify disability of 'Send to...' when only folder is selected (COL-63569) */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);

		documentWeb = gotoFileModule();
		if (documentWeb.verifyFiles(folderName))
		{
			documentWeb.deleteFile(folderName);
		}

		documentWeb.selectItemFromNew(FileLabels.SITE_ADMIN_PERMISSIONS_HEADER_UI_TEXT_FOLDER);
		documentWeb.createNewFolderInRoot(folderName, folderDescription);
		documentWeb.clickAddInModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(folderName);
		documentWeb.clickActionButton();

		if (totalServiceOn == 1)

		{
			Assert.assertTrue(documentWeb.verifySendToDisabledInActionButton(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm));

		}
		else
		{
			Assert.assertTrue(documentWeb.verifySendToDisabledInActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO));

		}

	}

	public void scenario16B() throws IOException, InterruptedException
	{
		/* Scenario 16B: Folder with multiple file is selected than it is enabled and ignore the folder just listing selected files in modal (COL-63569) */

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

		if (documentWeb.verifyFiles(folderName))
		{
			documentWeb.deleteFile(folderName);
		}
		documentWeb.selectItemFromNew(FileLabels.SITE_ADMIN_PERMISSIONS_HEADER_UI_TEXT_FOLDER);
		documentWeb.createNewFolderInRoot(folderName, folderDescription);
		documentWeb.clickAddInModal();
		documentWeb = gotoFileModule();

		documentWeb.selectFileCheckBox(doc1);
		documentWeb.selectFileCheckBox(folderName);
		documentWeb.clickActionButton();

		if (totalServiceOn == 1)

		{
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);

		}

		Assert.assertTrue(documentWeb.verifyFilesOnSendToDialog(doc1));
		Assert.assertFalse(documentWeb.verifyFilesOnSendToDialog(folderName));
		documentWeb.clickOnCloseButtonSendToModel();

	}

	public void scenario17() throws InterruptedException, IOException
	{

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

		documentWeb.clickOnMoreActionOfParticularFile(doc1);
		if (totalServiceOn == 1)

		{
			documentWeb.gotoMoreActionsSendTo(doc1, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.gotoMoreActionsSendTo(doc1, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);

		}
		documentWeb.clickOnSpecifiedServiceFromSendToDialog(FileLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		Assert.assertTrue(documentWeb.clickOnDocuSignSendButtonOfSendToModel(clientEmail, clientPwd));
		Assert.assertTrue(documentWeb.checkTagginginterface());
		documentWeb.logout();
	}

}
