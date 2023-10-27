package com.highq.test.file;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
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
 * @author darshan.nayak
 */
public class File_TC2901 extends BannerPageWeb
{
	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

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

	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_SANDBOX};
	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";
	String optionInActionMenu = "Needs to Sign";
	String message = "Message For Recipient";

	String userName = "Siteadmin2901";
	String message2 = "Recipient Message";

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";

	String delete = FileLabels.FILES_COMMON_DELETE;
	String doc1 = "doc1.txt";
	String doc2 = "doc2.txt";
	String doc3 = "doc3.txt";

	String[] document = {doc1, doc2, doc3};

	String jsonFileName = "preConfiguration_File_TC2901.json";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;

	String messageForDelete = "One or more of these files are checked out or have been sent for signing or locked.\nAre you sure you want to unlock or revoke and delete the file(s)?";
	String messageForSentForSignature = "This file has been sent for signing.\nAre you sure you want to revoke the signing request and delete this file?";
	String messageForUnlockRevokeAndDelete = "One or more of these files have been sent for signing.\nAre you sure you want to revoke or unlock and delete the file(s)?";
	String unlockAndDeleteVerifyMsg = "This file has been sent for signing.\nAre you sure you want to unlock and delete this file?";
	String unlockAndCheckoutVerifyMsg = "One or more of these files are checked out or have been sent for signing.\nAre you sure you want to unlock and delete the file(s)?";

	String[] actionNameForAuditForSendForSignature = {FileLabels.FILE_DELETED, FileLabels.SIGN_REQUEST_REVOKED};
	String[] actionNameForAuditForDraft = {FileLabels.FILE_DELETED, FileLabels.SIGNING_LOCK_REMOVED};
	String[] actionNameForAuditUnlockAndDelete = {FileLabels.FILE_DELETED, FileLabels.SIGNING_LOCK_REMOVED};
	String[] actionNameForAuditDelete = {FileLabels.FILE_DELETED};

	String siteName = "File_TC2901";

	private String adobeSignHostUrl = "https://secure.na1.echosign.com/";

	@Parameters({"appURL"})
	@Test(priority = 1)
	public void unlockRevokeAndDelete(String appURL) throws InterruptedException, IOException, JSONException
	{
		preCondition(appURL);
		preConfiguration();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
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
		systemAdminSystemSettingsWeb.clickOnOptionInMoreActionOfThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_AUTHORISE_OPTION);
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

	}

	/**
	 * @throws IOException
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));

	}

	/**
	 * Test case for scenario 1 for file with status sent for signature
	 * @author darshan.nayak
	 * @throws IOException
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	public void scenario1() throws IOException, JSONException, InterruptedException
	{
		/* scenario 1 :file with status sent for signature */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableStatus(true);
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}

		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);
		// documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		/* Some Files must be existing in the site. */
		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);
		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(delete);
		Assert.assertTrue(documentWeb.verifyMessageForDeletingFileWithSentForSignatureStatus(messageForSentForSignature));
		documentWeb.clickOnRevokeAndDeleteBtnInDeleteDialog();

		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.gotoMoreActions(doc1, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditForSendForSignature.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditForSendForSignature[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();

	}

	/**
	 * Test case for scenario 2 for file with status Draft, sent for signature and checked out
	 * @author darshan.nayak
	 * @throws IOException
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	public void scenario2() throws IOException, JSONException, InterruptedException
	{
		/* scenario 2 :file with status Draft, sent for signature and checked out */

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = gotoAdminModule();

		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.enableStatus(true);
		adminFilesWeb.enableCheckInCheckOut(true);
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}

		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		/* Delete all ready existing files */
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		/* Some Files must be existing in the site. Upload files */
		for (int i = 0; i < document.length; i++)
		{
			documentWeb.selectItemFromAdd(files);
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(document[i]);
			documentWeb.addFile(addDoc, null);
			documentWeb.clickAddInModal();
		}

		/* File send for signature */
		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		/* File for checkout */
		documentWeb.clickOnMoreActionOfParticularFile(doc2);
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.FILES_COMMON_CHECKOUT);
		documentWeb.clickOnCheckOut();

		/* File send for drafts */
		documentWeb.clickOnMoreActionOfParticularFile(doc3);

		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		documentWeb = gotoFileModule();
		documentWeb.clickSelectAllCheckbox();
		documentWeb.clickActionButton();

		documentWeb.selectOptionFromActionMenu(delete);
		Assert.assertTrue(documentWeb.verifyMessageForDeletingFileWithSentForSignatureStatus(messageForDelete));
		documentWeb.clickOnUnlockRevokeAndDeleteBtnInDeleteDialog();

		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);

		documentWeb.gotoMoreActions(doc1, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditForSendForSignature.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditForSendForSignature[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentWeb.gotoMoreActions(doc2, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditDelete.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditDelete[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentWeb.gotoMoreActions(doc3, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditForDraft.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditForDraft[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();

	}

	/**
	 * Test case for scenario 3 for file status with Draft
	 * @author darshan.nayak
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void scenario3() throws IOException, InterruptedException
	{

		/* scenario 3 : file status with Draft */
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}

		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		if (documentWeb.verifyLeftPanelItem(FileLabels.FILES_LEFTPANEL_DELETEDITEMS))
		{
			documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
			documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		}

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

		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);

		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		Assert.assertTrue(documentWeb.verifyStatus(doc1, FileLabels.FILE_STUTUS_DRAFT));

		documentWeb.clickOnMoreActionOfParticularFile(doc1);
		documentWeb.clickOnOptionFromFileMoreAction(delete);

		assertTrue(documentWeb.verifyDeleteMessage(unlockAndDeleteVerifyMsg));

		documentWeb.clickOnUnlockAndDeleteBtnInDeleteDialog();
		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.gotoMoreActions(doc1, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditUnlockAndDelete.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditUnlockAndDelete[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
	}

	/**
	 * Test case for scenario 4 for file status with Draft and sent for signature
	 * @author darshan.nayak
	 * @throws IOException
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	public void scenario4() throws IOException, JSONException, InterruptedException
	{
		/* scenario 4 : file status with Draft and sent for signature */
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}

		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		documentWeb = gotoFileModule();
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);
		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc2);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		documentWeb.clickOnMoreActionOfParticularFile(doc2);
		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);
		documentWeb.clickOnSentForSignatureLink();

		documentWeb = gotoFileModule();
		documentWeb.clickSelectAllCheckbox();
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(delete);
		Assert.assertTrue(documentWeb.verifyMessageForDeletingFileWithSentForSignatureStatus(messageForUnlockRevokeAndDelete));
		documentWeb.clickOnUnlockRevokeAndDeleteBtnInDeleteDialog();

		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.gotoMoreActions(doc1, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditForSendForSignature.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditForSendForSignature[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
		documentWeb.gotoMoreActions(doc2, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditForDraft.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditForDraft[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
	}

	/**
	 * Test case for scenario 5 for file status with Draft and checked out
	 * @author bhumika.vaghasiya
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void scenario5() throws IOException, InterruptedException
	{

		/* scenario 5 for file status with Draft and checked out */
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setDocuSign(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}

		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}
		if (adminFilesWeb.verifyThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		}
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.enableCheckInCheckOut(true);
		adminFilesWeb.saveFilesChanges();

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);
		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc2);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.clickOnMoreActionOfParticularFile(doc1);

		checkMoreActionLabelAndClickOnSendBtn(totalServiceOn, serviceNm);

		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		Assert.assertTrue(documentWeb.verifyStatus(doc1, FileLabels.FILE_STUTUS_DRAFT));

		documentWeb.checkoutFile(doc2);
		documentWeb = gotoFileModule();
		documentWeb.clickSelectAllCheckbox();
		documentWeb.clickActionButton();

		documentWeb.selectOptionFromActionMenu(delete);

		assertTrue(documentWeb.verifyDeleteMessage(unlockAndCheckoutVerifyMsg));

		documentWeb.clickOnUnlockAndDeleteBtnInDeleteDialog();
		documentWeb.selectLeftPanelFileOptions(FileLabels.FILES_LEFTPANEL_DELETEDITEMS);
		documentWeb.gotoMoreActions(doc1, FileLabels.FILES_MOREACTION_AUDITHISTORY);
		for (int i = 0; i < actionNameForAuditUnlockAndDelete.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditUnlockAndDelete[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
		documentWeb.gotoMoreActions(doc2, FileLabels.FILES_MOREACTION_AUDITHISTORY);

		for (int i = 0; i < actionNameForAuditDelete.length; i++)
		{
			Assert.assertTrue(documentWeb.verifyStatusInAuditHistory(actionNameForAuditDelete[i]));
		}
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
		documentWeb.logout();
	}

	/**
	 * Check more action label and click on send button
	 * @author darshan.nayak
	 * @param totalServiceOn
	 * @param serviceNm
	 */
	private void checkMoreActionLabelAndClickOnSendBtn(int totalServiceOn, String serviceNm)
	{
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
	}
}
