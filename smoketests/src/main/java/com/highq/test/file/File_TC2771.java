package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.DocumentRecipientsData;
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

public class File_TC2771 extends BannerPageWeb
{

	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	AspAdminPage aspAdminWeb;
	BannerPage bannerPageWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage addDoc;
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";
	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";
	String siteName = "File_TC2771";
	String doc1 = "doc1.txt";
	String doc = "doc1";
	String doc2 = "doc2.txt";
	String serviceName = "Send to DocuSign (sandbox)";
	String userName = "internaladmin User";
	String RecipientMessage = "Recipient Message";
	String missingContactMessage = "At least one recipient is missing contact information.";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";
	String message = "Message For Recipient";
	String userNameDisaplayed = "Siteadmin2771";

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
	public void TaggingInterfaceSeveralFunction(String appURL) throws IOException, InterruptedException, JSONException
	{

		preCondition(appURL);
		scenario2();
		scenario2A();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		// scenario1(appURL);
	}

	public void scenario1(String appURL) throws IOException, InterruptedException
	{
		/* To check “PREVIEW” ACTIONS Menu on Tagging interface iframe */

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.PREVIEW_LABEL);
		Assert.assertTrue(documentWeb.verifyDocumentOnTaggingInterface());
		documentWeb.clickOnCancelBtnOnTagging();
		logout();

	}

	public void scenario2() throws IOException, InterruptedException
	{
		/* To check “SAVE AND CLOSE” ACTIONS Menu on Tagging interface iframe */

		// bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		//
		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		// documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		Assert.assertTrue(documentWeb.verifyStatus(doc1, FileLabels.FILE_STUTUS_DRAFT));
		documentWeb.clickOnSentForSignatureLink();
		Assert.assertTrue(documentWeb.verifyFileInSentForSignatureTab(doc1));
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.UNLOCK));
		Assert.assertFalse(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REVOKE));
		Assert.assertFalse(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REVOKE));
		logout();

	}

	public void scenario2A() throws IOException, InterruptedException
	{
		/* Unlock files with Draft status */

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		// documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.UNLOCK));
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(FileLabels.UNLOCK);
		Assert.assertTrue(documentWeb.verifyElementsOnUnlockDialog());
		documentWeb.clickOnUnlockBtnInUnlockDialog();
		Assert.assertTrue(documentWeb.verifyGlobalMsgOnUnlockinFile(FileLabels.UNLOCKTRYAGAIN_MODAL_SUCCESS));
		gotoFileModule();
		documentWeb.clickOnSelectedFile(doc1);
		documentWeb.clickOnMoreActionOfFileViewer();
		documentWeb.gotoOptiOnOnMoreActionOfFileViewer(FileLabels.AUDITHISTORY);
		Assert.assertTrue(documentWeb.verifyActionOnAuditHistoryFromMoreAction(FileLabels.SIGNING_LOCK_REMOVED));
		documentWeb.clickOnCloseButtonOfAuditHistoryDialogBox();
		documentWeb.clickOnCrossButtonInFileviewer();
	}

	public void scenario3() throws IOException, InterruptedException
	{
		/* To check “DISCARD” ACTIONS Menu on Tagging interface iframe */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.DISCARD);
		documentWeb.clickOnDiscardBtnOnDiscardDraftDialog();
		Assert.assertTrue(documentWeb.verifyStatus(doc1, ""));

	}

	public void scenario4() throws IOException, InterruptedException
	{
		/* To check “Edit Message” ACTIONS Menu on Tagging interface iframe */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.EDIT_MESSAGE);
		Assert.assertTrue(documentWeb.verifyDetailsOnMessageToAllRecipient());
		Assert.assertTrue(documentWeb.verifyDetailsCloseButtonOnEditRecipiet());
		Assert.assertTrue(documentWeb.verifyDetailsOnMessageEditeRecipient());
		documentWeb.clickOnCancelBtnInEditMessage();
		documentWeb.clickOnCancelBtnOnTagging();

	}

	public void scenario5() throws IOException, InterruptedException
	{
		/* To check “Edit Recipients” ACTIONS Menu on Tagging interface iframe */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.EDIT_RECIPIENTS);
		Assert.assertTrue(documentWeb.verifyMissingRecipientMessage(missingContactMessage));
		documentWeb.clickOnCancelBtnOnTagging();

	}

	public void scenario6() throws IOException, InterruptedException
	{
		/* To check “Edit Documents” ACTIONS Menu on Tagging interface iframe */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.EDIT_DOCUMENTS);
		Assert.assertTrue(documentWeb.verifyUploadDocumentTitle());
		Assert.assertTrue(documentWeb.verifyFileOnEditDocument(doc));
		Assert.assertTrue(documentWeb.verifyDoneButtonOnEditDocument());
		Assert.assertTrue(documentWeb.verifyDetailsCloseButtionOnEditDocument());

		documentWeb.clickOnCancelBtnOnTagging();

	}

	public void scenario7() throws IOException, InterruptedException
	{
		/* To check “Advanced options” ACTIONS Menu on Tagging interface iframe */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(FileLabels.NEEDTOSIGN);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.ADVANCED_OPTIONS);
		Assert.assertTrue(documentWeb.verifyDetailsOnAdvancedOption());
		documentWeb.clickOnCancelBtnOnAdvancedOptionAtThirdPArty();
		documentWeb.clickOnCancelBtnOnTagging();
		logout();
	}

	public void scenario8() throws IOException, InterruptedException, JSONException
	{
		// Scenario 8: To check tagging interface iframe.
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("preConfiguration_File_TC2771.json"));

		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setEnabelDocumentReviewWorkflow(false);
		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		// send Document Without fields

		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();
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

		documentWeb.enterDetailsIntoSendToDialog(userNameDisaplayed, message);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();

		Assert.assertTrue(!documentWeb.checkTagginginterface());

		// send Document With fields
		documentWeb = gotoFileModule();

		if (documentWeb.verifyFiles(doc2))
		{
			documentWeb.deleteFile(doc2);
		}
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc2);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();
		documentWeb.clickOnMoreActionOfParticularFile(doc2);
		if (totalServiceOn == 1)
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNm);

		}
		else
		{
			documentWeb.clickOnOptionFromFileMoreAction(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO);
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

		}

		documentWeb.enterDetailsIntoSendToDialog(userNameDisaplayed, message);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.clickOnAddFields();
		documentWeb.addFieldsInTaggingInterface();
		documentWeb.clickOnSendTaggingInterface();

		Assert.assertTrue(!documentWeb.checkTagginginterface());

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
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickActionButton();

		if (totalServiceOnSystemAdmin == 1)
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + serviceNmSystemAdmin);
		}
		else
		{
			documentWeb.clickOnMoreActionOptionInMyFile(doc1, FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
			documentWeb.clickOnSpecifiedServiceFromSendToDialog(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		}

		documentWeb.enterDetailsIntoSendToDialog(userNameDisaplayed, message);
		documentWeb.clickOnSendButtonInRecipientDialog();
		Assert.assertTrue(documentWeb.verifyTaggingInterfaceWindow());
		documentWeb.clickOnSendTaggingInterface();
		Assert.assertTrue(documentWeb.verifyAddRecipientFieldsOnTaggingInterface());
		documentWeb.sendDocumentWithoutFields();
		Assert.assertTrue(!documentWeb.checkTagginginterface());
		documentWeb.logout();

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
		aspConfigurationWeb.setDocuSignSandboxCheckBoxInEsignatureServices(docuSignTrue);
		aspConfigurationWeb.clickConfigureLinkOfService(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMsg();
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(aspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX);
		}

		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.closeGlobalMsg();

		dashboardWeb = systemAdminSystemSettingsWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableStatus(true);
		if (adminFilesWeb.verifyThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);
		}

		adminFilesWeb.setDocuSign(AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();
		documentWeb = adminFilesWeb.gotoFileModule();

	}

}
