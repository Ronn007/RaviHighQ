package com.highq.test.systemAdmin;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSelfRegisteredUsersPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.ViewSharedContentPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.ViewSharedContentWeb;

public class SystemAdmin_TC2763 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String file = "doc1.txt";
	String moreActionOptionShare = "Share";
	String moreActionOptionAuditHistory = "Audit history";
	String domain = "highq.com";
	String recipientMail = "Jasmin" + getRandomString();
	String subject = "Share Email For Automation verification";
	String message = "Testing functionality of Share Task.";
	String htmlFileName = "MailContent.html";
	String mailContent;
	String viewLink = "Click here to download or view the file.";
	String confirmEmailLink = "Confirm your email address";
	String mailBody = "";
	String archive = "Archived";
	String active = "Active";
	String locked = "Locked";
	String anonymousDomain = "anonymous.highq.com";
	String emailPrefix = "anonymiseduser";
	String fileOption = "Files";
	String actionTypeClick = "click";
	String linkExpiry = "Never";
	int userid;

	BannerPage bannerPageWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	DocumentAddDataPage addDoc;
	WebDriver driver;
	Timestamp startTime;
	Timestamp endTime;
	ViewSharedContentPage viewSharedContentWeb;
	SearchUserPage searchUserPageWeb;
	SystemAdminSelfRegisteredUsersPage systemAdminSelfRegisteredUsersWeb;
	MyFilesPage myFilesWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test
	public void systemAdminTC2763() throws InterruptedException, IOException, UnsupportedFlavorException
	{
		scenario1();
	}

	public void scenario1() throws InterruptedException, IOException, UnsupportedFlavorException
	{
		preConditionForScenario1();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		searchUserPageWeb = systemAdminWeb.gotoUserAdmin();
		systemAdminSelfRegisteredUsersWeb = searchUserPageWeb.gotoSelfRegisteredUsers();

		userid = bannerPageWeb.getUserID(recipientMail, domain);

		Assert.assertFalse(systemAdminSelfRegisteredUsersWeb.verifyMakeAnonymiseButton());

		systemAdminSelfRegisteredUsersWeb.selectUser(recipientMail + "@" + domain);
		systemAdminSelfRegisteredUsersWeb.clickOnArchiveButton();

		systemAdminSelfRegisteredUsersWeb.clickFilterButton();
		systemAdminSelfRegisteredUsersWeb.selectUserStatusFromFilter(active);
		systemAdminSelfRegisteredUsersWeb.selectUserStatusFromFilter(archive);
		systemAdminSelfRegisteredUsersWeb.selectUserStatusFromFilter(locked);
		systemAdminSelfRegisteredUsersWeb.clickFilterButton();

		Assert.assertFalse(systemAdminSelfRegisteredUsersWeb.verifyMakeAnonymiseButton());

		systemAdminSelfRegisteredUsersWeb.clickFilterButton();
		systemAdminSelfRegisteredUsersWeb.selectUserStatusFromFilter(active);
		systemAdminSelfRegisteredUsersWeb.selectUserStatusFromFilter(locked);

		Assert.assertTrue(systemAdminSelfRegisteredUsersWeb.verifyMakeAnonymiseButton());

		systemAdminSelfRegisteredUsersWeb.selectUser(recipientMail + "@" + domain);
		systemAdminSelfRegisteredUsersWeb.clickAnonymiseButton();

		systemAdminSelfRegisteredUsersWeb.clickOnCancelButtonOfAnonymiseModel();
		systemAdminSelfRegisteredUsersWeb.searchUser(recipientMail + "@" + domain);
		Assert.assertTrue(systemAdminSelfRegisteredUsersWeb.verifyEmailID(recipientMail + "@" + domain));

		systemAdminSelfRegisteredUsersWeb.selectUser(recipientMail + "@" + domain);

		systemAdminSelfRegisteredUsersWeb.clickAnonymiseButton();
		systemAdminSelfRegisteredUsersWeb.clickOnAnonymiseButtonOfAnonymiseModel();

		systemAdminSelfRegisteredUsersWeb.clearSearchBox();
		systemAdminSelfRegisteredUsersWeb.searchUser(emailPrefix + userid + "@" + anonymousDomain);
		Assert.assertTrue(systemAdminSelfRegisteredUsersWeb.verifyEmailID(emailPrefix + userid + "@" + anonymousDomain));

		myFilesWeb = systemAdminSelfRegisteredUsersWeb.goToMyFiles();
		myFilesWeb.gotoMoreActions(file, moreActionOptionAuditHistory);
		myFilesWeb.clickActiveTabOnAuditHistory();
		Assert.assertTrue(myFilesWeb.verifyShareWithNameAuditHistoryModel(emailPrefix + userid));

	}

	public void preConditionForScenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemAdminWeb = bannerPageWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.selectShareViaEmailPermission(SystemAdminLabels.SYSADMIN_RECIPIENTS_REGISTER);
		systemAdminSystemSettingsWeb.saveSettings();
		myFilesWeb = systemAdminSystemSettingsWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
		myFilesWeb.selectItemFromAdd(fileOption);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(file);
		myFilesWeb.addFile(addDoc, null);
		myFilesWeb.clickAddInAddFileModal();

		myFilesWeb.gotoMoreActions(file, moreActionOptionShare);
		startTime = myFilesWeb.getStartDateTimeStamp();
		myFilesWeb.shareViaEmail(recipientMail + "@" + domain, subject, message, SystemAdminLabels.SYSADMIN_RECIPIENTS_REGISTER, linkExpiry);
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		endTime = myFilesWeb.getEndDateTimeStamp();
		logout();

		openMailLink(subject, recipientMail + "@" + domain, message, viewLink);

		viewSharedContentWeb = new ViewSharedContentWeb(driver);
		viewSharedContentWeb.setSelfRegisterUserEmailId(recipientMail + "@" + domain);
		startTime = myFilesWeb.getStartDateTimeStamp();
		viewSharedContentWeb.clickOnVerifyEmailAddress();
		endTime = myFilesWeb.getEndDateTimeStamp();

		openMailLink("", recipientMail + "@" + domain, "", confirmEmailLink);

		viewSharedContentWeb.setPassword(sysAdminPassword);
		viewSharedContentWeb.clickSubmit();
		viewSharedContentWeb.logout();

	}

	private void openMailLink(String subject, String recepient, String message, String link)
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
		mailBody = myFilesWeb.getMailContent(query);
		myFilesWeb.createHtmlFile(htmlFileName, mailBody);
		myFilesWeb.getLocalHtmlPage(htmlFileName);
		myFilesWeb.clickOnLinkInMailContentMessageBody(link);
	}
}
