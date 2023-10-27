package com.highq.test.file;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.By;
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

public class File_TC1502 extends BannerPageWeb
{

	String sysAdminEmail = "auto@highq.com";
	String normalUser = "normaluser1502@file.com";
	String sysAdminPassword = "Pa&&worD123";
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage addDoc;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminFilesPage adminFilesWeb;
	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";
	String siteName = "File_TC1502";
	String serviceName = "Send to DocuSign (sandbox)";
	String doc1 = "doc1.txt";
	String documnet = "doc2.txt";
	String remind = "Remind";
	String revoke = "Revoke";
	String voidedStatus = "Voided";
	String delete = "Delete";
	String jsonFileName = "preConfiguration_File_TC1502.json";
	By changeStatusValue = By.id("showContentStatus");
	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";
	String optionInActionMenu = "Needs to Sign";
	String message = "Message For Recipient";
	String messageForSentForSignature = "This file has been sent for signing.\nAre you sure you want to revoke the signing request and delete this file?";
	String validateMessageForVodedStatus = FileLabels.DOCUMENT_DELETE_AREYOUSURE;
	String validateMessageForDraftStatus = "Are you sure you want to delete the selected items?message should be displayed with Cancel and delete button";

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
	public void Revokeandremind(String appURL) throws IOException, InterruptedException, JSONException
	{
		preCondition(appURL);
		logout();
		preconfiguration();
		logout();
		scenario1_case1(appURL);
		scenario1_case2();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();

	}

	public void scenario1_case1(String appURL) throws InterruptedException, IOException, JSONException
	{
		/* To check the functionality of 'Remind' option & fields in Remind modal window.. */

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

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
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);
		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(optionInActionMenu);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(remind);
		Assert.assertTrue(documentWeb.verifyRemindDialogSendButton());
		Assert.assertTrue(documentWeb.verifyRemindDialogCloseButton());
		Assert.assertTrue(documentWeb.verifyRemindDialogSendRemiderBody());
		Assert.assertTrue(documentWeb.verifyRemindDialogSendRemider());
		documentWeb.clickOnCancelInRemindDialog();
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(remind);
		documentWeb.clickOnSendButtonInRemindDialog();
		Assert.assertTrue(documentWeb.verifyReminderMessage());
		logout();

	}

	public void scenario1_case2() throws InterruptedException, IOException
	{
		/* To check the functionality of 'Remind' option & fields in Remind modal window. */

		bannerPageWeb = login(normalUser, "Pa&&wo0DUser123");
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = gotoFileModule();
		documentWeb.clickOnSentForSignatureLink();
		Assert.assertFalse(documentWeb.verifyMoreActionButton(doc1));
		logout();

	}

	public void scenario2() throws InterruptedException, IOException
	{
		/* To check the functionality of 'Revoke' option on selecting Revoke option from More actions button. */

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

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
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(optionInActionMenu);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(revoke);
		Assert.assertTrue(documentWeb.verifyRevokeModalOnRevoke());
		Assert.assertTrue(documentWeb.verifyCloseSignRequestOnRevoke());
		Assert.assertTrue(documentWeb.verifyRevokeBodyOnRevoke());
		Assert.assertTrue(documentWeb.verifyrevokeSignRequestOnRevoke());
		Assert.assertTrue(documentWeb.verifyCloseButtonOnRevoke());
		documentWeb.clickOnCancelInRevokeDialog();
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(revoke);
		documentWeb.clickOnRevokeButtonInRevokeDialog();
		Assert.assertTrue(documentWeb.verifyGlobalMessageOnRevoking());
		documentWeb.clickOnSentForSignatureLink();
		Assert.assertTrue(documentWeb.verifyFileInSentForSignatureLink(doc1));
		documentWeb.gotoFileModule();
		Assert.assertTrue(documentWeb.verifyStatusOnFileModule(doc1, voidedStatus));

	}

	public void scenario3() throws InterruptedException, IOException
	{
		/* this sceanrio is covered in scenario2 */
	}

	public void scenario4() throws IOException, InterruptedException
	{
		/* To Revoke the file from File viewer page. */

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
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(optionInActionMenu);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickOnSelectedFile(doc1);
		documentWeb.clickOnRevokeSigningRequestLinkInFileViewer();
		documentWeb.clickOnRevokeButtonFromFileViewer();
		documentWeb.verifyGlobalMsgRevokedSuccessfully();
		documentWeb.clickOnCrossButtonInFileviewer();
		documentWeb.gotoFileModule();
		documentWeb.clickOnSentForSignatureLink();
		Assert.assertTrue(documentWeb.verifyFileInSentForSignatureLink(doc1));

	}

	public void scenario5() throws InterruptedException, IOException
	{
		/* Verify Remind and Revoke option for files with Draft status */

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
			documentRecipientsData[i].setThirdPartyServiceAction(optionInActionMenu);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();

		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		Assert.assertTrue(documentWeb.verifyStatusOnFileModule(doc1, FileLabels.FILE_STUTUS_DRAFT));
		documentWeb.clickOnSentForSignatureLink();
		Assert.assertFalse(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REMIND));
		Assert.assertFalse(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REVOKE));

	}

	public void scenario6() throws InterruptedException, IOException
	{
		/* Messages displayed on deleting files with various status */

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
		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(documnet);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.clickSelectAllCheckbox();
		documentWeb.clickActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[1];

		for (int i = 0; i < documentRecipientsData.length; i++)
		{
			documentRecipientsData[i] = new DocumentRecipientsData();
			documentRecipientsData[i].setName("User" + i);
			documentRecipientsData[i].setEmail("user" + i + "@gmail.com");
			documentRecipientsData[i].setThirdPartyServiceAction(optionInActionMenu);
		}

		documentWeb.enterDetailsMoreThanTwoRecipient(message, documentRecipientsData);
		documentWeb.clickOnSendButtonInRecipientDialog();

		documentWeb.clickOnSendFromTaggingInterface();
		documentWeb.clickOnSentForSignatureLink();

		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		documentWeb.clickOnOptionFromMoreActionInSentForSignature(revoke);
		documentWeb.clickOnRevokeButtonInRevokeDialog();
		documentWeb.gotoFileModule();
		Assert.assertTrue(documentWeb.verifyStatusOnFileModule(doc1, FileLabels.VOIDEDSTATUS));
		Assert.assertTrue(documentWeb.verifyStatusOnFileModule(documnet, FileLabels.VOIDEDSTATUS));

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
		adminFilesWeb.selectCheckbox(changeStatusValue, true);

		if (adminFilesWeb.verifyThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
		{
			adminFilesWeb.setAdobeSign(SystemAdminLabels.ADOBE_SIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF);
		}

		adminFilesWeb.setDocuSign(AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();

	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
	}
}
