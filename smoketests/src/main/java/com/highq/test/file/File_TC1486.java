package com.highq.test.file;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.DocumentRecipientsData;
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

public class File_TC1486 extends BannerPageWeb
{

	String sysAdminEmail = "auto@highq.com";
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
	String siteName = "File_TC1486";
	String serviceName = "Send to DocuSign (sandbox)";
	String doc1 = "doc1.txt";
	String doc2 = "doc1";
	String senderUser = "Auto auto";
	String user1 = "User0";
	String user2 = "User1";
	String user3 = "User2";
	String optionInActionMenu = "Needs to Sign";
	String message = "Message For Recipient";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";
	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";
	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_SANDBOX};
	private String clientIdAdobeSign = "CBJCHBCAABAAkj38z1BxB73Hp-JsV6Z8Cti7xBY0K2ug";
	private String clientSecretAdobeSign = "UdqW2c7lR5nz6BqQBF9p4KxTZgwnnR7H";
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
	 */
	@Parameters({"appURL"})
	@Test(priority = 1)
	public void FileTC1486(String appURL) throws IOException, InterruptedException
	{
		scenario1(appURL);
		scenario2();
		scenario3();
		scenario4();
	}

	public void scenario1(String appURL) throws InterruptedException, IOException
	{
		/* To check sent file with List View */

		preCondition(appURL);

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[3];

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
		documentWeb.clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		documentWeb.gotoLinkFromViewMenuDropDown(FileLabels.LISTVIEW);
		Assert.assertTrue(documentWeb.verifyFileOnListView(doc1));
		Assert.assertTrue(documentWeb.verifySenderMiniCard(senderUser));
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		documentWeb.gotoLinkFromViewMenuDropDown(FileLabels.LISTVIEW);
		documentWeb.clickOnMoreActionInSentForSignatureTab(doc1);
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REVOKE));
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REMIND));
		Assert.assertTrue(documentWeb.verifyUserLink(user1, user2));
		documentWeb.clickOnMoreLinkInSentForSignature();
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user1));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user2));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user3));
		Assert.assertTrue(documentWeb.verifyCloseOnMoreLink());
		Assert.assertTrue(documentWeb.verifyfileOnMoreLink(doc2));

		documentWeb.clickOnCancelButtonOfRecipientOfMoreLink();

	}

	public void scenario2() throws InterruptedException, IOException
	{
		/* To check sent file with Column View */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb = gotoFileModule();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[3];

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
		documentWeb.clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		documentWeb.gotoLinkFromViewMenuDropDown(FileLabels.COLUMNVIEW);
		Assert.assertTrue(documentWeb.verifyFileOnListView(doc1));
		documentWeb.clickOnMoreActionFromSentForSignatureTab(doc1);
		Assert.assertTrue(documentWeb.verifyOptionInMoreActionOfThumbnailView(FileLabels.REMIND));
		Assert.assertTrue(documentWeb.verifyOptionInMoreActionOfThumbnailView(FileLabels.REVOKE));
		Assert.assertTrue(documentWeb.verifyUserLinkAtColumnView(user1, user2));
		documentWeb.clickOnMoreLinkInSentForSignature();
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user1));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user2));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user3));
		Assert.assertTrue(documentWeb.verifyfileOnMoreLink(doc2));
		documentWeb.clickOnCancelButtonOfRecipientOfMoreLink();

	}

	public void scenario3() throws InterruptedException, IOException
	{
		/* To check sent file with Thumbnail View */

		documentWeb = gotoFileModule();

		documentWeb.clickOnDeleteButtonFromActionButton(FileLabels.SYSTEMADMIN_MENU_MOREDELETE);

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb = gotoFileModule();
		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.selectOptionFromActionMenu(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[3];

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
		documentWeb.clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		documentWeb.gotoLinkFromViewMenuDropDown(FileLabels.THUMBNAILVIEW);
		Assert.assertTrue(documentWeb.verifyFileOnListViewAtThumbnailView(doc2));
		Assert.assertTrue(documentWeb.verifySenderMiniCardAtThumbnailView(senderUser));
		documentWeb.cancelMiniCard();
		documentWeb.clickOnMoreActionFromThumbnailsView(doc1);
		Assert.assertTrue(documentWeb.verifyOptionInMoreActionOfThumbnailView(FileLabels.REMIND));
		Assert.assertTrue(documentWeb.verifyOptionInMoreActionOfThumbnailView(FileLabels.REVOKE));
		Assert.assertTrue(documentWeb.verifyUserLinkAtThumbnailView(user1, user2));
		documentWeb.clickOnMoreLinkInSentForSignature();
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user1));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user2));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user3));
		Assert.assertTrue(documentWeb.verifyfileOnMoreLink(doc2));
		documentWeb.clickOnCancelButtonOfRecipientOfMoreLink();
		logout();

	}

	public void scenario4() throws IOException, InterruptedException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		documentWeb = dashboardWeb.goToMyFiles();

		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc1);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(doc1);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButtonInMyFile(serviceName);

		DocumentRecipientsData[] documentRecipientsData = new DocumentRecipientsData[3];

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
		documentWeb.clickMenuItemOnSentForSignature(FileLabels.FILES_VIEW_BUTTION);
		documentWeb.gotoLinkFromViewMenuDropDown(FileLabels.LISTVIEW);
		Assert.assertTrue(documentWeb.verifyFileOnListView(doc1));
		Assert.assertTrue(documentWeb.verifySenderMiniCard(senderUser));
		documentWeb.clickOnSentForSignatureLink();
		documentWeb.clickOnMoreActionInSentForSignatureTab(doc1);
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REVOKE));
		Assert.assertTrue(documentWeb.verifyOptionsInMoreActionOnSentForSignature(FileLabels.REMIND));
		Assert.assertTrue(documentWeb.verifyUserLink(user1, user2));
		documentWeb.clickOnMoreLinkInSentForSignature();
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user1));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user2));
		Assert.assertTrue(documentWeb.verifyUserDetailsOnMoreLink(user3));
		Assert.assertTrue(documentWeb.verifyCloseOnMoreLink());
		Assert.assertTrue(documentWeb.verifyfileOnMoreLink(doc2));

		documentWeb.clickOnCancelButtonOfRecipientOfMoreLink();
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
		systemAdminSystemSettingsWeb.selectAdobeOptionThirdPartyService(SystemAdminLabels.ADOBE_SIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.logout();

	}

}
