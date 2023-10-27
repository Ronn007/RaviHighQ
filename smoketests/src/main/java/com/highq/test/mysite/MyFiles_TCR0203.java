package com.highq.test.mysite;

import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author surbhi.khetan
 */
public class MyFiles_TCR0203 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	MyFilesPage myFilesWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage documentAddDataPage;
	ActivityPage activityPageWeb;
	AdminPage adminPageWebp;
	AdminPage adminPageWeb;
	AdminActivityPage adminActivityPageWeb;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String jsonFileName = "preConfiguration_MyFiles_TCR203.json";
	String username = "usernormal1@file203.com";
	String username1 = "usernormal2@file203.com";
	String password = "Password@123";
	String shared = "Shared items";
	String recipientMail = "usernormal2@file203.com";
	String subject = "myFiles_TCR0203 - test subject";
	String addMenuItem = "Files";
	String add = "Folder";
	String[] files = {"doc1.txt", "FolderOfTCR0203"};
	String uploadItem = "doc2.txt";
	String destination = "My files(1)";
	String index = "My files";
	String linkMessage = "Link successfully sent to:";
	String received = "Received items";
	String operation = "Share";
	String site = "MyFiles_TCR0203";
	String accessLevel = "Anyone with the link";
	String permission = "Download, view and print";
	String fileExpiry = "after 30 days";
	String filename = "FolderOfTCR0203.zip";

	@Test
	public void myFiles_TCR0203() throws IOException, JSONException, InterruptedException, UnsupportedFlavorException
	{

		siteAndUserConfiguration();
		precondition();
		scenario1();
		scenario2();
	}

	private void siteAndUserConfiguration() throws IOException, JSONException, InterruptedException
	{

		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
	}

	private void precondition() throws InterruptedException, IOException
	{

		/**
		 * ASP Admin => Enable My File Sharing : On
		 * System Admin => Enable My File Sharing: On
		 */

		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyFiles("ON");
		systemAdminSystemSettingsWeb.enableMyFilesSharing("ON for all system users");
		systemAdminSystemSettingsWeb.saveSettings();

		dashboardWeb = systemAdminSystemSettingsWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableMyFiles("ON");
		aspConfigurationWeb.enableMyFilesSharing("ON for all system users");
		aspConfigurationWeb.saveConfigurations();

		/** Enable microblogging at site level */
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(site);
		adminPageWeb = gotoAdminModule();
		adminActivityPageWeb = adminPageWeb.clickActivityInLeftPanel();
		adminActivityPageWeb.enableMicroblogging(true);
		adminActivityPageWeb.clickOnSave();
		dashboardWeb = adminActivityPageWeb.gotoDashboard();
		logout();

		/** Some files and folder should be added in My files */
		bannerPageWeb = login(username, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();

		/** Upload files */
		myFilesWeb.selectItemFromAdd(addMenuItem);
		documentAddDataPage = new DocumentAddDataPage();
		documentAddDataPage.clean();
		documentAddDataPage.setFileuploadpath(files[0]);
		myFilesWeb.addFile(documentAddDataPage, null);
		myFilesWeb.clickAddInModal();

		/** Add Folder in my files */
		myFilesWeb.selectItemFromNew(add);
		myFilesWeb.addFolderAtSpecificLocation(files[1], destination);
		myFilesWeb.clickAddInAddFolderModal();
		myFilesWeb.selectLeftPanelFileOptions(index);

	}

	private void scenario1() throws InterruptedException
	{

		/** Send a file to a user */
		myFilesWeb.SendAfile(uploadItem, recipientMail, subject, accessLevel, permission, fileExpiry);

		/** Verify modal window title */
		Assert.assertTrue(myFilesWeb.verifyShareModalMessage(linkMessage));

		/** Verify if recipient mail is displayed in modal */
		Assert.assertTrue(myFilesWeb.verifyRecipientMailInShareModal(recipientMail));
		myFilesWeb.clickOnCloseButtonOfSendAFileModal();

		/** Verify modal is closed when user clicks on close button */
		Assert.assertFalse(myFilesWeb.verifyShareModalVisibility());

		myFilesWeb.selectLeftPanelFileOptions(shared);
		/** Verify file is displayed in shared items of logged in user */
		Assert.assertTrue(myFilesWeb.verifySharedFileVisibility(uploadItem));

		dashboardWeb = myFilesWeb.gotoDashboard();
		logout();

		/** Verify file visibility of shared files in received items of recipient user */
		bannerPageWeb = login(username1, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(received);
		Assert.assertTrue(myFilesWeb.verifyFilesInReceivedItems(uploadItem));

		dashboardWeb = myFilesWeb.gotoDashboard();
		logout();

	}

	private void scenario2() throws UnsupportedFlavorException, IOException, InterruptedException
	{

		shareViaEmail();
		shareViaLink();
		shareViaMessage();
		shareViaMicroblog();

	}

	private void shareViaEmail() throws InterruptedException
	{

		for (int i = 0; i < files.length; i++)
		{

			bannerPageWeb = login(username, password);
			myFilesWeb = bannerPageWeb.goToMyFiles();

			/** Share via email */
			myFilesWeb.gotoMoreActions(files[i], operation);
			myFilesWeb.shareViaEmail(recipientMail, subject, subject, accessLevel, fileExpiry);

			/** Verify modal window title */
			Assert.assertTrue(myFilesWeb.verifyShareModalMessage(linkMessage));

			/** Verify if recipient mail is displayed in modal */
			Assert.assertTrue(myFilesWeb.verifyRecipientMailInShareModal(recipientMail));
			myFilesWeb.clickOnCloseButtonOfSendAFileModal();

			/** Verify modal is closed when user clicks on close button */
			Assert.assertFalse(myFilesWeb.verifyShareModalVisibility());
			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();

			/** Verify file visibility of shared files in received items of recipient user */
			bannerPageWeb = login(username1, password);
			myFilesWeb = bannerPageWeb.goToMyFiles();
			myFilesWeb.selectLeftPanelFileOptions(received);
			Assert.assertTrue(myFilesWeb.verifyFilesInReceivedItems(files[i]));

			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();
		}

	}

	private void shareViaLink() throws UnsupportedFlavorException, IOException, InterruptedException
	{

		for (int i = 0; i < files.length; i++)
		{

			bannerPageWeb = login(username, password);
			myFilesWeb = bannerPageWeb.goToMyFiles();

			myFilesWeb.gotoMoreActions(files[i], operation);

			/** Share via link */
			myFilesWeb.copyShareLink(false, accessLevel, fileExpiry);
			myFilesWeb.clickCancelInShareModal();

			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();

			bannerPageWeb = login(username1, password);
			/** Open copied URL */
			bannerPageWeb.openCopiedURL();

			/** Verify user is re-directed to download page */
			bannerPageWeb.verifyDownloadPage();
			bannerPageWeb.cleanDownloadsFolder();
			bannerPageWeb.clickOnDownlaod();

			/** Verify if file is downloaded or not */

			if (bannerPageWeb.verifyFolderInLink())
				assertTrue(bannerPageWeb.verifyDownloadedFile(filename));
			else
				assertTrue(bannerPageWeb.verifyDownloadedFile(files[i]));
			bannerPageWeb.logout();

		}
	}

	private void shareViaMessage() throws InterruptedException
	{

		for (int i = 0; i < files.length; i++)
		{

			bannerPageWeb = login(username, password);
			myFilesWeb = bannerPageWeb.goToMyFiles();

			/** Share via message */
			myFilesWeb.gotoMoreActions(files[i], operation);
			myFilesWeb.shareViaMessage(recipientMail, subject, fileExpiry);
			myFilesWeb.clickSendInShareModal();

			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();

			bannerPageWeb = login(username1, password);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.clickOnPrivateMessage();
			if (dashboardWeb.verifyFirstMessageIsRecentlyRecieved())
			{
				/** Verify if shared item is present in messages */
				Assert.assertTrue(dashboardWeb.verifyRecentMessageRecieved(username, subject,true));
				dashboardWeb.clickOnReceivedLink();

				/** Verify user is re-directed to download page */
				bannerPageWeb.verifyDownloadPage();
				bannerPageWeb.cleanDownloadsFolder();
				bannerPageWeb.clickOnDownlaod();
				/** Verify if file is downloaded or not */

				if (bannerPageWeb.verifyFolderInLink())
					assertTrue(bannerPageWeb.verifyDownloadedFile(filename));
				else
					assertTrue(bannerPageWeb.verifyDownloadedFile(files[i]));
			}
			else
				Assert.assertFalse(true);

			myFilesWeb = dashboardWeb.goToMyFiles();
			myFilesWeb.selectLeftPanelFileOptions(received);

			/** Verify if files is present in received items of user */
			Assert.assertTrue(myFilesWeb.verifyFilesInReceivedItems(files[i]));

			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();
		}

	}

	private void shareViaMicroblog() throws InterruptedException
	{

		for (int i = 0; i < files.length; i++)
		{

			bannerPageWeb = login(username, password);
			myFilesWeb = bannerPageWeb.goToMyFiles();

			/** Share via microblog */
			myFilesWeb.gotoMoreActions(files[i], operation);
			myFilesWeb.shareViaMicroblog(site, subject, fileExpiry);

			dashboardWeb = myFilesWeb.gotoDashboard();
			logout();

			bannerPageWeb = login(username1, password);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(site);
			activityPageWeb = bannerPageWeb.gotoActivityModule();
			activityPageWeb.gotoDashboard();
			activityPageWeb.verifyPost(site, subject);

			/** Verify if post is present on dashboard */
			Assert.assertTrue(activityPageWeb.verifyPost(site, subject));
			activityPageWeb.openFirstPostReceivedLink();

			/** Verify if user is re-directed to download page */
			bannerPageWeb.verifyDownloadPage();
			bannerPageWeb.cleanDownloadsFolder();
			bannerPageWeb.clickOnDownlaod();

			/** Verify if file is downloaded or not */
			if (bannerPageWeb.verifyFolderInLink())
				assertTrue(bannerPageWeb.verifyDownloadedFile(filename));
			else
				assertTrue(bannerPageWeb.verifyDownloadedFile(files[i]));
			bannerPageWeb.logout();
		}
	}
}
