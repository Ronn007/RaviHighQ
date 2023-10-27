package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.QandALabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.QandAPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.QandAWeb;
import com.highq.parsers.JSONParser;
import com.highq.test.qanda.BaseQandA;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0242 extends BannerPageWeb
{
	/** Share icon of file and folders */
	private WebDriver driver;
	String jsonFileName = "Files/preConfiguration_File_TCR0242.json";
	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser
					.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();

	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 1").get("newPassword").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 2").get("newPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com"};
	String newPassword = "Password@123";

	String[] files = {"doc1.txt", "doc2.txt"};
	String[] folders = {"Alpha", "Beta"};
	String extension = ".zip";

	String enableIndexPage = "Enable Index page";
	String versionNumber = "Version number";
	String enableBulkDownloads = "Enable bulk downloads";
	String leftPanelItem = "Deleted items";
	String bulkDownloadWithPipeStream = "TRUE";
	String[] moreActionItems = {FileLabels.FILES_COMMON_DOWNLOAD, FileLabels.FILES_MOREACTION_SHARE, FileLabels.FILES_MOREACTION_ASKQUESTION, FileLabels.FILES_MOREACTION_AUDITHISTORY, FileLabels.FILES_MOREACTION_VERSIONHISTORY, FileLabels.FILES_MOREACTION_DELETE};
	String message = "Testing share functionality of file module.";
	String subject = "Sharing ";
	String mailBody = "";
	String htmlFileName = "MailContent.html";
	String question = "Did it work?";
	String auditsTabTitle = "Audits";
	String versionsTabTitle = "Versions";
	String accessedByTitle = "Accessed by";
	String deleteMsg = "Are you sure you want to delete the selected items?";
	String post = "Here is a link to \"0\":";
	Timestamp startTime;
	Timestamp endTime;
	String[] qaPermissions = {"ask question", "submit question", "reply to question", "view all questions"};

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	AdminFilesPage adminFilesWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminActivityPage adminActivityWeb;
	BaseQandA baseQandAWeb;
	QandAPage qaWeb;
	ActivityPage activityWeb;
	BaseFileTest baseFileTest = new BaseFileTest(driver);

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0242() throws InterruptedException, IOException, UnsupportedFlavorException
	{
		precondition();
		scenario1();
		scenario2();
		scenario4();
		scenario5();
		scenario6();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1 : Download from file share icon. */
		/** Login with normal user */
		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.cleanDownloadsFolder();
		/** Download a file and verify it gets downloaded */
		documentWeb.gotoMoreActions(files[0], moreActionItems[0]);
		assertTrue(documentWeb.verifyDownloadedFile(files[0]));
	}

	private void scenario2() throws InterruptedException, IOException, UnsupportedFlavorException
	{
		/** Send a file via Email and verify email */

		sendFileOrFolderViaEmail(files[0], userNames[1], subject + files[0], message);
		openMailLink(subject + files[0], userNames[1], message);
		assertTrue(documentWeb.verifyPreviewFileTitle(files[0]));
		documentWeb.closePreviewPage();
		documentWeb.closeCurrentTab();
		/** Send a folder via Email and verify email */

		sendFileOrFolderViaEmail(folders[0], userNames[1], subject + folders[0], message);
		openMailLink(subject + folders[0], userNames[1], message);
		assertTrue(documentWeb.verifyFolderTitle(folders[0]));
		documentWeb.closeCurrentTab();
		/** Share via link */
		documentWeb.gotoMoreActions(files[0], moreActionItems[1]);
		// Copy File Link
		documentWeb.copyShareLink(false);
		documentWeb.clickCancelInShareModal();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
		bannerPageWeb = login(userNames[1], newPassword);
		bannerPageWeb.openCopiedURL();
		// Verify copied link
		assertTrue(documentWeb.verifyPreviewFileTitle(files[0]));
		documentWeb.closePreviewPage();
		documentWeb.gotoMoreActions(folders[0], moreActionItems[1]);
		// Copy Folder Link
		documentWeb.copyShareLink(false);
		documentWeb.clickCancelInShareModal();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
		bannerPageWeb = login(userNames[0], newPassword);
		bannerPageWeb.openCopiedURL();
		// Verify copied link
		assertTrue(documentWeb.verifyFolderTitle(folders[0]));
		documentWeb.selectLeftPanelFileOptions(siteName);

		/** Share via message */
		documentWeb.gotoMoreActions(files[0], moreActionItems[1]);
		documentWeb.shareViaMessage(userNames[1], message);
		documentWeb.clickSendInShareModal();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(userNames[1], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			assertTrue(dashboardWeb.verifyRecentMessageRecieved(userNames[0], message,true));
			dashboardWeb.clickOnLastMessageLinkInMessageContainer();
			assertTrue(documentWeb.verifyPreviewFileTitle(files[0]));
			documentWeb.closePreviewPage();
			documentWeb.switchWindow();
			documentWeb.closeCurrentTab();
		}
		else
			Assert.assertFalse(true);

		documentWeb.clickCancelInMessageBox();
		dashboardWeb = documentWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.gotoMoreActions(folders[0], moreActionItems[1]);
		documentWeb.shareViaMessage(userNames[0], message);
		documentWeb.clickSendInShareModal();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnPrivateMessage();
		if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			assertTrue(dashboardWeb.verifyRecentMessageRecieved(userNames[1], message,true));
			dashboardWeb.clickOnLastMessageLinkInMessageContainer();
			assertTrue(documentWeb.verifyFolderTitle(folders[0]));
			documentWeb.switchWindow();
			documentWeb.closeCurrentTab();
		}
		else
			Assert.assertFalse(true);
		documentWeb.clickCancelInMessageBox();

		/** Share via microblog */
		dashboardWeb = documentWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.gotoMoreActions(files[0], moreActionItems[1]);
		documentWeb.gotoMicroblogTab();
		documentWeb.clickPostInShareModal();

		activityWeb = documentWeb.gotoActivityModule();
		assertTrue(activityWeb.verifyPost(siteName, post.replace("0", files[0])));
		verifyFileFromActivityAndDashboard(files[0]);

		activityWeb = documentWeb.gotoActivityModule();
		activityWeb.gotoDashboard();
		activityWeb.gotoPosts();
		verifyFileFromActivityAndDashboard(files[0]);

		dashboardWeb = documentWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.gotoMoreActions(folders[0], moreActionItems[1]);
		documentWeb.gotoMicroblogTab();
		documentWeb.clickPostInShareModal();

		activityWeb = documentWeb.gotoActivityModule();
		assertTrue(activityWeb.verifyPost(siteName, post.replace("0", folders[0])));
		verifyFolderFromActivityAndDashboard(folders[0]);

		activityWeb = documentWeb.gotoActivityModule();
		activityWeb.gotoDashboard();
		activityWeb.gotoPosts();
		verifyFolderFromActivityAndDashboard(folders[0]);
		logout();

	}

	private void scenario4()
	{
		/** Scenario 4: Add question form file and folder share icon. */

		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Add question for a file and verify it in QandA module */
		documentWeb.gotoMoreActions(files[0], moreActionItems[2]);
		question = question + getRandomString();
		askAndVerifyQuestion(question);

		/** Add question for a folder and verify it in QandA module */
		documentWeb.gotoMoreActions(folders[0], moreActionItems[2]);
		question = question + getRandomString();
		askAndVerifyQuestion(question);
	}

	private void scenario5()
	{
		/** Scenario 5: Version history/Audit history form file share icon. */
		/** Verify audit history tabs for normal user */
		documentWeb.gotoMoreActions(files[0], moreActionItems[4]);
		assertTrue(documentWeb.verifyAccessedbyTabVisibilityInAuditHistoryModal());
		assertTrue(documentWeb.verifyVersionsTabVisibilityInAuditHistoryModal());
		assertFalse(documentWeb.verifyAuditsTabVisibilityInAuditHistoryModal());
		documentWeb.clickCloseInAuditHistoryButton();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		/** Verify audit history tabs for site admin user */
		bannerPageWeb = login(userNames[1], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.gotoMoreActions(files[0], moreActionItems[3]);
		assertTrue(documentWeb.verifyAccessedbyTabVisibilityInAuditHistoryModal());
		assertTrue(documentWeb.verifyVersionsTabVisibilityInAuditHistoryModal());
		assertTrue(documentWeb.verifyAuditsTabVisibilityInAuditHistoryModal());
		documentWeb.clickCloseInAuditHistoryButton();
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

	}

	private void scenario6()
	{
		/** Scenario 6: Delete from file and folder share icon. */
		bannerPageWeb = login(userNames[0], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		documentWeb.gotoMoreActions(files[1], moreActionItems[5]);
		assertTrue(documentWeb.verifyDeleteMessage(deleteMsg));
		documentWeb.clickDeleteInDeleteModal();
		assertFalse(documentWeb.verifyDocumentUploaded(files[1]));

		documentWeb.gotoMoreActions(folders[1], moreActionItems[5]);
		// assertTrue(documentWeb.verifyDeleteMessage(deleteMsg));
		documentWeb.clickDeleteInDeleteModal();
		assertFalse(documentWeb.verifyDocumentUploaded(folders[1]));
		assertFalse(documentWeb.verifyLeftPanelItem(leftPanelItem));
		dashboardWeb = documentWeb.gotoDashboard();
		logout();

		bannerPageWeb = login(userNames[1], newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Verify deleted file and folder is available in Deleted items */
		assertTrue(documentWeb.verifyLeftPanelItem(leftPanelItem));
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		assertTrue(documentWeb.verifyDocumentUploaded(folders[1]));
		assertTrue(documentWeb.verifyDocumentUploaded(files[1]));
		documentWeb.deleteFile(files[1]);
		documentWeb.deleteFile(folders[1]);
		documentWeb.logout();
	}

	private void askAndVerifyQuestion(String question)
	{
		qaWeb = new QandAWeb(driver);
		question = question + getRandomString();
		qaWeb.sendTextInQuestionTextBox(question);
		qaWeb.clickOnFootersInAskQuestionModal(QandALabels.QANDA_SUBMIT);
		qaWeb.verifyFadeModal();
		qaWeb.clickOnOkButtonInFadeModalDialog();
		qaWeb = gotoQAndAModule();
		assertTrue(qaWeb.verifyQuestionInQuestionListing(question));
		qaWeb.clickOnQuestion(question);
		assertTrue(qaWeb.verifyQuestionValueInQADetailContainer(question));
		documentWeb = qaWeb.gotoFileModule();
	}

	private void sendFileOrFolderViaEmail(String documentName, String recipient, String mailSubject, String message)
	{
		documentWeb.gotoMoreActions(documentName, moreActionItems[1]);
		documentWeb.shareViaEmail(recipient, mailSubject, message);
		startTime = documentWeb.getStartDateTimeStamp();
		documentWeb.clickSendInShareModal();
		endTime = documentWeb.getEndDateTimeStamp();
	}

	private void openMailLink(String subject, String recepient, String message)
	{
		String query;
		if (subject.trim().isEmpty() || subject.trim() == null)
		{
			query = "select TOP 1 * from Email where mailto = '" + recepient.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		else
		{
			query = "select TOP 1 * from Email where mailsubject = '" + subject.trim() + "' and mailto = '" + recepient.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		mailBody = documentWeb.getMailContent(query);
		documentWeb.createHtmlFile(htmlFileName, mailBody);
		documentWeb.getLocalHtmlPage(htmlFileName);
		assertTrue(documentWeb.verifyContent(recepient, message));
		documentWeb.clickOnLinkInMailContentMessageBody();
	}

	private void verifyFileFromActivityAndDashboard(String fileName)
	{
		activityWeb.clickOnPostLink(siteName, post.replace("0", fileName));
		documentWeb.switchWindow();
		assertTrue(documentWeb.verifyPreviewFileTitle(fileName));
		documentWeb.closePreviewPage();
		documentWeb.closeCurrentTab();
	}

	private void verifyFolderFromActivityAndDashboard(String folderName)
	{
		activityWeb.clickOnPostLink(siteName, post.replace("0", folderName));
		documentWeb.switchWindow();
		assertTrue(documentWeb.verifyFolderTitle(folderName));
		documentWeb.closeCurrentTab();
	}

	private void precondition() throws InterruptedException
	{
		/**
		 * Create site and add normal user, site admin and give module permission
		 */
		preconfiguration();
		/**
		 * Enable Microbloging
		 * Set file permission level "Site, folders and files" from site admin >> files.
		 * Set show versions/drafts = true from site admin >> files.
		 * Add files and folders in site.
		 * Set QandA permissions
		 */
		siteAdminConfiguration();

	}

	private void siteAdminConfiguration()
	{
		bannerPageWeb = login(userNames[1], defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();

		/**
		 * Set file permission level "Site, folders and files" from site admin >> files.
		 * Set show versions/drafts = true from site admin >> files.
		 */
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setFilePermission(SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES);
		adminFilesWeb.selectFileOptionCheckbox(versionNumber, true);
		adminFilesWeb.saveFilesChanges();
		adminPageWeb = adminFilesWeb.gotoAdminModule();

		/** Enable Microbloging */
		adminActivityWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityWeb.enableMicroblogging(true);
		adminActivityWeb.clickOnSave();
		adminPageWeb = adminActivityWeb.gotoAdminModule();

		/** Set QandA permissions */
		Map<String, Map<String, Boolean>> qAndADataAndRoles = new LinkedHashMap<>();
		Map<String, Boolean> forumPermissions = new LinkedHashMap<>();
		for (int i = 0; i < qaPermissions.length; i++)
		{
			forumPermissions.put(qaPermissions[i], true);
		}
		qAndADataAndRoles.put(domain, forumPermissions);
		baseQandAWeb = new BaseQandA(driver);
		baseQandAWeb.setQAndAPermission(qAndADataAndRoles);

		/** Add folders and files */
		documentWeb = baseQandAWeb.gotoFileModule();
		baseFileTest.documentWeb = documentWeb;
		/** Add files */
		for (int i = 0; i < files.length; i++)
		{
			documentWeb.searchFile(files[i]);
			if (!documentWeb.verifyDocumentUploaded(files[i]))
			{
				baseFileTest.addFile(files[i]);
				documentWeb.searchFile(files[i]);
				assertTrue(documentWeb.verifyDocumentUploaded(files[i]));
			}
		}
		/** Add folders */
		for (int i = 0; i < folders.length; i++)
		{
			documentWeb.searchFolder(folders[i]);
			if (!documentWeb.verifySearchedFolder(folders[i]))
			{
				baseFileTest.addFolderInRoot(folders[i], "");
				documentWeb.searchFolder(folders[i]);
				assertTrue(documentWeb.verifySearchedFolder(folders[i]));
			}
		}
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		try
		{
			Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}

}
