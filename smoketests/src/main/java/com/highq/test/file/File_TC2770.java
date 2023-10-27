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
public class File_TC2770 extends BannerPageWeb
{
	private WebDriver driver;

	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

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

	String jsonFileName = "preConfiguration_File_TC2770.json";

	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.ADOBE_SIGN_SANDBOX};

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	String siteName = "File_TC2770";
	String normalUser = "normaluser2770@file.com";
	String siteAdmin = "siteadmin2770@file.com";

	String file = "doc1.txt";
	String doc2 = "doc2.txt";

	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	final String NAME = "name";
	final String VERSIONNOTES = "version notes";
	String placeholderFile = "Placeholder file";

	String placeHolderTags = "ptag1";
	String placeHolderDisclaimerText = "placeholder Disclaimer";
	String placeholderFileName = "placeholder1";

	String userName = "Siteadmin1263";
	// String userName = "Auto";
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

	String[] filesdemo = {"doc1.txt", "doc2.txt", "doc3.txt"};

	String[] sendToOption = {FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, FileLabels.FILES_THIRDPARTYSERVICE_MODAL_SENDTO + " " + SystemAdminLabels.ADOBE_SIGN_SANDBOX};

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
	public void File_TC2770(String appURL) throws IOException, InterruptedException, JSONException
	{
		// preCondition(appURL);
		// preConfiguration();
		scenario1();

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
		aspConfigurationWeb.clickConfigureLinkOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);
		Assert.assertTrue(aspConfigurationWeb.configureThirdPatyCredentials(clientIdDocuSign, clientSecretDocuSign, SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		aspConfigurationWeb.clickOnSaveConfigureService();
		aspConfigurationWeb.saveConfigurations();

		/* DocuSign (sandbox) On for every Site */
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		if (!systemAdminSystemSettingsWeb.verifyAddedThirdPArtyServicesInSystemSetting(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			systemAdminSystemSettingsWeb.clickOnAddBtnOfThirdPartyServices();
			systemAdminSystemSettingsWeb.addServiceInThirdParty(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX);

		}
		systemAdminSystemSettingsWeb.selectDocuSignOptionThirdPartyService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS);

		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.logout();

	}

	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));

		// dashboardWeb = bannerPageWeb.gotoDashboard();
		// dashboardWeb.searchSite(siteName);
		/* Atleast 1 third party service must be ON from “Site Admin --> Files” */
		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setDocuSign(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON);
		adminFilesWeb.saveFilesChanges();
		adminFilesWeb.logout();
	}

	public void scenario1() throws IOException, InterruptedException
	{
		/* Scenario 1: To check tagging interface iframe (COL-63290). */
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableStatus(true);
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
		documentWeb.clickOnSendToButtonFromActionButton("Send to DocuSign (sandbox)");

		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		Assert.assertTrue(documentWeb.verifyTaggingInterfaceWindow());
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.sendDocumentWithoutFields();
		Assert.assertTrue(!documentWeb.checkTagginginterface());

		/* send Document With fields */
		if (documentWeb.verifyFiles(doc2))
		{
			documentWeb.deleteFile(doc2);
		}
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(doc2);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInModal();

		documentWeb.selectFileCheckBox(doc2);
		documentWeb.clickActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to DocuSign (sandbox)");
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		Assert.assertTrue(documentWeb.verifyTaggingInterfaceWindow());
		documentWeb.clickOnSendTaggingInterface();
		documentWeb.clickOnAddFields();
		documentWeb.addFieldsInTaggingInterface();
		documentWeb.clickOnSendTaggingInterface();

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
		documentWeb.clickOnSendToButtonFromActionButtonInMyFile("Send to DocuSign (sandbox)");
		documentWeb.enterDetailsIntoSendToDialog(userName, message2);
		documentWeb.clickOnSendButtonInRecipientDialog();
		Assert.assertTrue(documentWeb.verifyTaggingInterfaceWindow());
		documentWeb.clickOnSendTaggingInterface();
		Assert.assertTrue(documentWeb.verifyAddRecipientFieldsOnTaggingInterface());
		documentWeb.sendDocumentWithoutFields();
		Assert.assertTrue(!documentWeb.checkTagginginterface());

		documentWeb.logout();
	}

}
