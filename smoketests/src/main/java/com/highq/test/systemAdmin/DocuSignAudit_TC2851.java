package com.highq.test.systemAdmin;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.AdminGeneralWeb;
import com.highq.pageobjects.pages.AdminSecurityWeb;
import com.highq.pageobjects.pages.AdminTermsAndConditionsWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.DocumentWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;
import com.highq.pageobjects.pages.SystemAuditsReportsWeb;
import com.highq.parsers.JSONParser;

public class DocuSignAudit_TC2851 extends BannerPageWeb
{
	public final String ip = "192.168.154.134,192.168.156.216,192.168.156.129,192.168.156.155,192.168.156.193,192.168.156.100,192.168.156.139,192.168.156.118,192.168.156.111,192.168.156.108,192.168.156.149,192.168.156.145,192.168.156.183,192.168.156.174,192.168.156.40,192.168.156.48,192.168.156.239,192.168.7.151,192.168.7.38,192.168.156.228,192.168.154.38,192.168.154.69,192.168.154.126,192.168.154.97,192.168.154.73,192.168.156.28,192.168.156.239,192.168.156.54,192.168.156.242,192.168.7.146,192.168.7.110,192.168.7.120,192.168.156.77,192.168.156.71,192.168.156.72,192.168.156.230,192.168.156.167,192.168.156.180,192.168.7.94,192.168.7.42";
	String password = "HighQ@123";
	static String sysAdminEmail = "";
	static String sysAdminPassword = "";
	private String jsonFileName = "systemAuditPositiveSite.json";
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
	String fileName = "doc1.txt";
	String fileNameWI = "doc1";
	String fileName1 = "doc2.txt";
	String remind = "Remind";
	String revoke = "Revoke";
	String voidedDocument = "Document Voided";
	String sentForSignature = "Sent for signature";
	String voided = "Voided";
	private String preparationSiteName;
	private String readOnlySiteName;
	private String passcodeSiteName;
	private String passwordSiteName;
	private String tearmsSiteName;
	private String iPSiteName;
	private String suspendedSiteName;

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	DocumentWeb documentWeb;
	WebDriver driver;
	PreConfiguration preConfigurationTest;
	SystemAuditsReportsWeb systemAuditsReportsWeb;
	SystemAdminWeb systemAdminWeb;
	DocumentAddDataPage addDoc;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AspAndSystemAdmin aspAndSystemAdmin;
	AdminFilesPage adminFilesWeb;

	String siteName = "Site_For_System_Audit_Positive";
	String doc1 = "doc1.txt";
	String serviceName = "Send to DocuSign (sandbox)";
	String enableAPI = "TRUE";
	String docuSignTrue = "TRUE";
	String unlock = "Unlock";
	String draft = "Draft";
	String signingLockRemovedStatus = "Signing lock removed";

	String[] services = {SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX};

	private String clientIdDocuSign = "f7ea6802-7464-4dec-aad0-6bcbb4c06025";
	private String clientSecretDocuSign = "a9266a47-dffe-4cf9-832e-3ffd2edc62e3";

	private String clientEmail = "thirdpartyservice.qa@highq.com";
	private String clientPwd = "Admin@123";

	String message = "Message For Recipient";
	String userName = "Siteadmin2851";
	String message2 = "Recipient Message";

	String unlockBodyMsg = "Are you sure you want to unlock the request for this and any related documents to be signed?";

	@BeforeClass
	public void setUp() throws IOException
	{
		driver = getDriver();
		String currentDir = System.getProperty("user.dir");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		sysAdminEmail = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminEmail"), String.class);
		sysAdminPassword = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("sysAdminPassword"), String.class);
		preConfigurationTest = new PreConfiguration(driver);

	}

	@Parameters({"appURL"})
	@Test(priority = 1)
	public void DocuSignAuditPre(String appURL) throws Exception
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario9(appURL);
	}

	public void scenario1() throws IOException, InterruptedException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//" + jsonFileName));
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		Reporter.log("Scenario 1 : Things on sent for signature system audit page.</br>");
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and files module should be enabled.</br>");
		Reporter.log("2.Some files should be added in site.</br>");
		Reporter.log("3.Some files should be sent for signature with usingsend to docusign functionality.</br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		// documentWeb.setThirdPartyDropDown("");
		// documentWeb.setMessage("");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		Reporter.log("Steps :</br>");
		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();

		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();

		Reporter.log("3.Cick on sent for signature system audit from left panel.</br>");
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.It should open sent for signature system audit page.</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifySystemAuditPage());

		Reporter.log("2.On this page it should display Export to excel button, total number of files count beside the search box, search box and filter options.</br>");
		// This is not developed

		Reporter.log("3.All the documents which are sent for signature should display with four columns name, status, site and recipient.</br>");
		systemAuditsReportsWeb.verifyAllColumnsInAuditPage();

		Reporter.log("4.On this page documents should be display with link and when click on this link user should be redirected to that file path and open in viewer.</br>");

		Reporter.log("5.With the document it should also display some meta data like document path, document version and the user name who sent document for signature.</br>");
		systemAuditsReportsWeb.verifyDocumentPath(fileName, "/" + siteName + "/");
		systemAuditsReportsWeb.verifyDocumentVersion(fileName, "v1");
		systemAuditsReportsWeb.verifySendByUser(fileName, "Auto");

		Reporter.log("7.In the recipient column it should display the user name to whom you send document for signature. Here if the recipient user more than two than it should display more link and when click on this link all user should be listed in modal. It should display link for all user name and when click on this link on link it should redirected to user profile page.</br>");
		systemAuditsReportsWeb.verifyRecipient(fileName, "Normal User");

		Reporter.log("6.Site name should be also display with link and when click on this link user should be redirected to  the site landing page.</br>");

	}

	public void scenario2() throws IOException, InterruptedException
	{
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		createAllSitesWithAllModes();

		Reporter.log("Scenario 2 : Documents visibility on sent for signature system audit page.</br>");

		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Some sites with different status should be created. (e.g active, preparation, read only and archived)</br>");
		Reporter.log("2.Some site should be present in which diferent restriction applied. (e.g password, 2FA, ip and terms & condition)</br>");
		Reporter.log("3.Files module should be enabledin all above site.</br>");
		Reporter.log("4.Some files should be added in all above sites.</br>");
		Reporter.log("5.Atleast a file should be sent for signature with using send to docusign functionality from all the sites.</br>");
		Reporter.log("6.Atleast a file should be sent for signature with using send to docusign functionality from any user my site.</br>");
		Reporter.log("7.Atleast a file should be sent for signature with using send to docusign functionality and that should be deleted permenatly after send for signature.</br>");
		logout();
		login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		Reporter.log("Steps :</br>");
		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();

		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();

		Reporter.log("3.Cick on sent for signature system audit from left panel.</br>");
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.All the documents from all the sites should be dislayed on the page to system admin as he has by default permission for all the sites.</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifySystemAuditPage());

		Reporter.log("2.The documents which are belogs to restricted site should be also displayed on this page and when user click on this document link appropriate restriction modal should be open and after accept the T&Cs, enter the password, or do 2FA depending on the restriction user should be redirected to valid document path and docuement should open in viewer.</br>");

		Reporter.log("3.The documents which is belogs to other user mysite should be also displayed on this page and when user click on this document link should not be redirected any where.</br>");

		Reporter.log("4.The document which is deleted from the system should also be display on this page but document link should be displayed as text.</br>");

	}

	private void scenario3() throws IOException, InterruptedException
	{
		Reporter.log("Scenario 3 : Different entries for same documents on sent for signature system audit page.</br>");

		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		Reporter.log("Pre condition :<br>");
		Reporter.log("1.Site should be created and files module should be enabled.<br>");
		Reporter.log("2.Some files should be added in site.<br>");
		Reporter.log("Steps :<br>");
		Reporter.log("1.Log in with site admin and go to above site.<br>");
		Reporter.log("2.Click on files module and send any file for signature to some users by selecting send to docusign option.<br>");
		Reporter.log("3.Now again select same file and send for signature to some users by selecting send to docusign option.<br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		// Check Document in Sent For Signature Status
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();
		Assert.assertTrue(systemAuditsReportsWeb.verifyStatus(fileName, sentForSignature));

		// Revoke The Document
		driver.navigate().refresh();
		dashboardWeb = systemAuditsReportsWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.clickOnLeftPanelSentForSignature();
		documentWeb.clickMoreActionOnSentForSignature(fileName);
		documentWeb.clickOnMoreActionInSentForSignatureTab(fileName, revoke);
		documentWeb.clickOnRevokeButton();

		// Check Document in Status=voided
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();
		Assert.assertTrue(systemAuditsReportsWeb.verifyStatus(fileName, voided));

		driver.navigate().refresh();
		dashboardWeb = systemAuditsReportsWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		// Check Document in Sent For Signature Status
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();
		Assert.assertTrue(systemAuditsReportsWeb.verifyStatus(fileName, sentForSignature));

		systemAdminWeb = systemAuditsReportsWeb.gotoSystemAdmin();

		Reporter.log("Expected :<br>");
		Reporter.log("1.If the same document is sent multiple times in separate transactions then it should separate entries for it in the system audit page so here two audits should be displayed for same documents.<br>");

		Reporter.log("5.Cick on sent for signature system audit from left panel.<br>");
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();
		Assert.assertTrue(systemAuditsReportsWeb.verifyStatus(fileName, sentForSignature));

	}

	private void scenario4() throws Exception
	{
		Reporter.log("Scenario 4 : Pagination and sorting</br>");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		Reporter.log("Case 1 : pagination </br>");
		Reporter.log("<b>NOTE :-Pagination is Not Possible For Automation...</b></br>");

		Reporter.log("Case 2 : Sorting</br>");
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and files module should be enabled.</br>");
		Reporter.log("2.Some files should be added in site.</br>");
		Reporter.log("3.Some files should be sent for signature with using send to docusign functionality.</br>");
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		Map<String, String> editDetails = new HashMap<>();
		editDetails.put("title", "000000");

		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.clickSaveInModal();
		documentWeb.gotoFileModule();

		documentWeb.selectFileCheckBox("000000.txt");
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		driver.navigate().refresh();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		editDetails = new HashMap<>();
		editDetails.put("title", "zzzzzz");

		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.clickSaveInModal();
		documentWeb.gotoFileModule();

		documentWeb.selectFileCheckBox("zzzzzz.txt");
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		Reporter.log("Steps :</br>");
		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		Reporter.log("3.Cick on sent for signature system audit from left panel.</br>");
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.User should be able to sort all four columns (name, status, site and recipient) by clicking on column name.</br>");
		systemAuditsReportsWeb.clickNameFieldOnSorting();
		Assert.assertTrue(systemAuditsReportsWeb.verifyFileName("000000.txt"));

		systemAuditsReportsWeb.clickNameFieldOnSorting();
		Assert.assertTrue(systemAuditsReportsWeb.verifyFileName("zzzzzz.txt"));

		systemAuditsReportsWeb.clickStatusFieldOnSorting();
		Assert.assertTrue(systemAuditsReportsWeb.verifyOnlyStatus(sentForSignature));

		systemAuditsReportsWeb.clickStatusFieldOnSorting();
		Assert.assertTrue(systemAuditsReportsWeb.verifyOnlyStatus("Draft"));

		systemAuditsReportsWeb.clickSiteFieldOnSorting();

		systemAuditsReportsWeb.clickRecipientFieldOnSorting();
	}

	private void scenario5() throws IOException, InterruptedException
	{
		Reporter.log("Scenario 5 : Revoke and remind</br>");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		Reporter.log("Case 1 : pagination </br>");
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and files module should be enabled.</br>");
		Reporter.log("2.Some files should be added in site.</br>");
		Reporter.log("3.Some files should be sent for signature with using send to docusign functionality.</br>");
		Reporter.log("4.Some files should be present with diffrent statuses in the sent for signature system audit page. (e.g created, Draft, Sent for signature, Voided, Signed)</br>");

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();
		Reporter.log("Steps :</br>");
		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		Reporter.log("3.Cick on sent for signature system audit from left panel.</br>");

		Reporter.log("Expected : </br>");
		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("1.Share menu should only applicable for files with sent for signature status.</br>");
		Reporter.log("2.It should display two options in share menu. 1. revoke and 2.remind</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifyMoreActionOptionsonDocument(fileName, revoke));
		Assert.assertTrue(systemAuditsReportsWeb.verifyMoreActionOptionsonDocument(fileName, remind));

		Reporter.log("3.When click on revoke option revoke modal window should get open with heading as Revoke sign request icon in top right of the modal window to close the modal window.</br>");
		systemAuditsReportsWeb.clickOnMoreActionOnDocument(fileName, revoke);
		Assert.assertTrue(systemAuditsReportsWeb.verifyRevokeModel());
		Assert.assertTrue(systemAuditsReportsWeb.verifyRevokeSignRequestMsg());
		Assert.assertTrue(systemAuditsReportsWeb.verifyCloseButtonInRevokeModel());

		Reporter.log("4.Message in revoke modal should display as Are you sure you want to revoke the request for this and any related documents to be signed </br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifyRevokeModelMessage());

		Reporter.log("5.Cancel  Revoke button should display in bottom right of the modal window.</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifyRevokeButtoninRevokeModel());
		Assert.assertTrue(systemAuditsReportsWeb.verifyCancelButtoninRevokeModel());
		systemAuditsReportsWeb.clickCloseButtonInRevokeModel();

		Reporter.log("6.Click on revoke button, File which is sent for the signature should be revoked successfully & a global notification message should display as Sign request revoked successfully. and an e-mail should be sent to the recipient user to inform about the file revoked from e-Signing. E-mail is send by the Third party service to the recipient user.</br>");
		Reporter.log("7.File which is revoked should be removed from Sent for signature system audit page and count of the file beside the search box should also be reduced. user should be able to send the same file again for e-Sign after it is been revoked.</br>");
		Reporter.log("8.Click on cancel button should close the modal window.</br>");

		systemAuditsReportsWeb.clickOnMoreActionOnDocument(fileName, revoke);
		systemAuditsReportsWeb.clickCancelButtoninRevokeModel();

		systemAuditsReportsWeb.clickOnMoreActionOnDocument(fileName, revoke);
		systemAuditsReportsWeb.clickRevokeButtoninRevokeModel();
		// Message is Remaning
		driver.navigate().refresh();
		dashboardWeb = systemAuditsReportsWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("9.When user click on remind option remind modal window should get open with heading in top left of the modal window as Send reminder X icon on top right of the modal window to close the modal window.</br>");
		systemAuditsReportsWeb.clickOnMoreActionOnDocument(fileName, remind);
		Assert.assertTrue(systemAuditsReportsWeb.verifyReminderModel());
		Assert.assertTrue(systemAuditsReportsWeb.verifySendReminderMsgReminderModel());
		Assert.assertTrue(systemAuditsReportsWeb.verifyCloseButtonOnReminderModel());

		Reporter.log("10.Message in Remind modal should display as Are you sure you want to send a reminder to the original recipients with Cancel Send button.</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifyRemindModelMessage());
		Assert.assertTrue(systemAuditsReportsWeb.verifyCancelButtoninReminderModel());
		Assert.assertTrue(systemAuditsReportsWeb.verifySendButtoninReminderModel());
		systemAuditsReportsWeb.clickCloseButtonOnReminderModel();

		Reporter.log("11.Click on send button from reminder modal window, a reminder mail should be sent to the original  recipient user and a global success message should display in top navigation as Reminder sent successfully. with X icon to close the notification message. In reminder mail, link of the document which is to be signed should be sent to the Recipient user by third party service.</br>");
		systemAuditsReportsWeb.clickOnMoreActionOnDocument(fileName, remind);
		systemAuditsReportsWeb.clickSendButtoninReminderModel();
		// Message is Remaning

		Reporter.log("12.Click on cancel button will close the modal window and reminder will not be send to the any recipient user.</br>");
		Assert.assertTrue(!systemAuditsReportsWeb.verifyRevokeModel());

	}

	private void scenario6() throws IOException, InterruptedException
	{
		Reporter.log("Scenario 6 :- Search</br>");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and files module should be enabled.</br>");
		Reporter.log("2.Some files should be added in site.</br>");
		Reporter.log("3.Some files should be sent for signature with using send to docusign functionality.</br>");
		Reporter.log("Steps :</br>");
		// driver.navigate().refresh();
		// logout();
		// bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = dashboardWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		Reporter.log("3.Cick on sent for signature system audit from left panel.</br>");

		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Assert.assertTrue(systemAuditsReportsWeb.verifySearchTextBox());

		Reporter.log("4.In search text area add some search text for e.g highq</br>");
		systemAuditsReportsWeb.enterSearchTextBox(fileNameWI);

		Reporter.log("Expected :</br>");
		Reporter.log("1.Searching should be work based on document name.</br>");
		Reporter.log("2.So as per search text highq all the matching documents should be listed.</br>");
		Assert.assertTrue(systemAuditsReportsWeb.verifyFileName(fileName));

		Reporter.log("3.If there is no any matching document found than it should dislay No results found message.</br>");
		systemAuditsReportsWeb.enterSearchTextBox("afldsj gsog sdfg dh");
		systemAuditsReportsWeb.verifyNoFilesMessage();
	}

	private void scenario7() throws Exception
	{
		Reporter.log("Scenario 8 : Different statues</br>");
		JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + jsonFileName.trim()));
		String siteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);

		Reporter.log("Pre condition :</br>");
		Reporter.log("1.Site should be created and files module should be enabled.</br>");
		Reporter.log("2.Some files should be added in site.</br>");
		Reporter.log("3.Some files should be sent for signature with using send to docusign functionality.</br>");
		addFileAndsendForDocuSign(siteName);
		addFileAndsendForDocuSignVoided(siteName);

		Reporter.log("Steps :</br>");
		Reporter.log("1.Log in with system admin and click on system admin from user profile menu.</br>");
		Reporter.log("2.Click on system audits/reports from the left panel.</br>");
		Reporter.log("3.Click on sent for signature system audit from left panel.</br>");

		driver.navigate().refresh();
		systemAdminWeb = documentWeb.gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();

		Reporter.log("Expected :</br>");
		Reporter.log("1.If the document is send for signature than the status should be Sent for signature.</br>");
		Reporter.log("2.If the recipients user signed the document than the status should be chnaged to signed.</br>");
		systemAuditsReportsWeb.verifyStatus(fileName, sentForSignature);
		Reporter.log("3.If the recipients user reject the document than the status should be chnaged to voided.</br>");
		systemAuditsReportsWeb.verifyStatus(voidedDocument, voided);
		Reporter.log("4.If sender user make the changes but not sending the document for signature the that document status should be display as draft.</br>");

	}

	public void scenario9(String appURL) throws InterruptedException, IOException, JSONException
	{
		/* Scenario 9: Unlocking document in Draft status (COL-69215) */

		preCondition(appURL);
		preConfiguration();

		systemAdminWeb = gotoSystemAdmin();
		systemAuditsReportsWeb = systemAdminWeb.gotoSystemAuditsAndReports();
		systemAuditsReportsWeb.gotoSentForSignatureSystemAuditPage();
		Assert.assertTrue(systemAuditsReportsWeb.verifyStatus(doc1, draft));
		Assert.assertTrue(systemAuditsReportsWeb.verifyMoreActionOptionsonDocument(doc1, unlock));
		systemAuditsReportsWeb.clickOnMoreActionOnDocument(doc1, unlock);
		assertTrue(systemAuditsReportsWeb.verifyUnlockModelBody(unlockBodyMsg));
		assertTrue(systemAuditsReportsWeb.verifyUnlockButton());
		assertTrue(systemAuditsReportsWeb.verifyCancelButtonOfUnlockBody());
		systemAuditsReportsWeb.clickOnUnlockButton();
		assertTrue(systemAuditsReportsWeb.verifyUnlockGlobalMessage());

		dashboardWeb = gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.clickOnMoreActionOfParticularFile(doc1);
		documentWeb.clickOnOptionFromFileMoreAction(FileLabels.ISHEET_LABEL_AUDITHISTORY);
		assertTrue(documentWeb.verifyStatusInAuditHistory(signingLockRemovedStatus));
		logout();
	}

	private void createAllSitesWithAllModes()
	{
		try
		{
			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Preparation Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditPreparationSite.json"));
			JsonNode resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditPositiveSite.json".trim()));
			preparationSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setSiteStatus(preparationSiteName, "Preparation");
			addFileAndsendForDocuSign(preparationSiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Readonly Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditReadOnlySite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditReadOnlySite.json".trim()));
			readOnlySiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setSiteStatus(readOnlySiteName, "Read only");
			addFileAndsendForDocuSign(readOnlySiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Passcode Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditPasscodeSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditPasscodeSite.json".trim()));
			passcodeSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(passcodeSiteName, "Passcode");
			addFileAndsendForDocuSign(passcodeSiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Password Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditPasswordSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditPasswordSite.json".trim()));
			passwordSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(passwordSiteName, "Password");
			addFileAndsendForDocuSign(passwordSiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Tearms And Condition Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditTearmsConditionSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditTearmsConditionSite.json".trim()));
			tearmsSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(tearmsSiteName, "Tearms");
			addFileAndsendForDocuSign(tearmsSiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For IP Site
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditIPSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditIPSite.json".trim()));
			iPSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			setDiffStatus(iPSiteName, "IP");
			addFileAndsendForDocuSign(iPSiteName);

			logout();
			bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
			// For Suspended User
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("//SystemAudit//systemAuditSuspendedSite.json"));
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//SystemAudit//" + "systemAuditSuspendedSite.json".trim()));
			suspendedSiteName = new ObjectMapper().convertValue(resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name"), String.class);
			suspendUserFromSite(suspendedSiteName, "Site Admin");
			addFileAndsendForDocuSign(suspendedSiteName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setSiteStatus(String siteNames, String sitestatus)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteNames);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		AdminGeneralWeb adminGeneralWeb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralWeb.selectStatus(sitestatus);
		adminGeneralWeb.clickOnSave();

	}

	private void setDiffStatus(String siteNames, String sitestatus)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteNames);
		adminPageWeb = dashboardWeb.gotoAdminModule();

		if (sitestatus.equals("Password"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setPasswordEnabled(true);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("Passcode"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setPasswordEnabled(true);
			adminSecurityWeb.sendPassword(password);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("IP"))
		{
			AdminSecurityWeb adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
			adminSecurityWeb.setrestrictedIps(ip);
			adminSecurityWeb.saveAdvancedChanges();
		}
		else if (sitestatus.equals("Terms"))
		{
			AdminTermsAndConditionsWeb adminTermsAndConditionsWeb = adminPageWeb.clickTermsAndConditionsInLeftPanel();
			adminTermsAndConditionsWeb.enableSiteTermsAndCondition(true);
			adminTermsAndConditionsWeb.setTermsAndConditionText("This Is Automation Demo");
			adminTermsAndConditionsWeb.clickOnSave();
		}

	}

	private void suspendUserFromSite(String siteNames, String userName)
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteNames);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		AddUserWeb addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.selectAllUsers();
		addUserWeb.clickOnSuspend();

	}

	private void addFileAndsendForDocuSign(String siteName) throws IOException, InterruptedException
	{
		driver.navigate().refresh();
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();
	}

	private void addFileAndsendForDocuSignVoided(String siteName) throws IOException, InterruptedException
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		Map<String, String> editDetails = new HashMap<>();
		editDetails = new HashMap<>();
		editDetails.put("title", voidedDocument);

		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.clickSaveInModal();
		documentWeb.gotoFileModule();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

		documentWeb.clickOnLeftPanelSentForSignature();
		documentWeb.clickMoreActionOnSentForSignature(voidedDocument);
		documentWeb.clickOnMoreActionInSentForSignatureTab(voidedDocument, revoke);
		documentWeb.clickOnRevokeButton();
	}

	private void addFileAndsendForDocuSignDraft(String siteName) throws IOException, InterruptedException
	{
		logout();
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		bannerPageWeb = dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentWeb.addFile(addDoc, null);
		documentWeb.clickAddInAddFileModal();
		Map<String, String> editDetails = new HashMap<>();
		editDetails = new HashMap<>();
		editDetails.put("title", "Document Draft");

		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.clickSaveInModal();
		documentWeb.gotoFileModule();

		documentWeb.selectFileCheckBox(fileName);
		documentWeb.clickOnActionButton();
		documentWeb.clickOnSendToButtonFromActionButton("Send to " + SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		// documentWeb.clickOnSendToButtonFromActionButton(FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_SENDTO.trim());
		// documentWeb.clickOnSpecifiedServiceFromSendToDialog(SystemAdminLabels.DOCUSIGN_SIGN_SANDBOX);
		documentWeb.setMember("Normal User");
		documentWeb.clickOnSendButtonInRecipientDialog();
		documentWeb.clickOnSendFromTaggingInterface();

	}

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

		systemAdminSystemSettingsWeb.saveSettings();

	}

	public void preConfiguration() throws IOException, JSONException, InterruptedException
	{
		dashboardWeb = gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableStatus(true);
		if (!adminFilesWeb.verifyAuthorisedService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
		{
			adminFilesWeb.clickOnMoreActionOptionOfService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, AdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_AUTHORISE);
			assertTrue(adminFilesWeb.authoriseDocuSignService(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX, clientEmail, clientPwd));
		}

		int totalServiceOn = adminFilesWeb.countServiceOn();
		String serviceNm = adminFilesWeb.getOnServiceName(totalServiceOn);
		adminFilesWeb.saveFilesChanges();

		documentWeb = dashboardWeb.gotoFileModule();

		if (documentWeb.verifyFiles(doc1))
		{
			documentWeb.deleteFile(doc1);
		}

		documentWeb.selectItemFromAdd(FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS);
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
		documentWeb.clickOnActionButtonInTaggingInterface();
		documentWeb.clickOnOptionInActionButton(FileLabels.SAVE_AND_CLOSE);

		Assert.assertTrue(documentWeb.verifyStatus(doc1, FileLabels.FILE_STUTUS_DRAFT));

	}

}
