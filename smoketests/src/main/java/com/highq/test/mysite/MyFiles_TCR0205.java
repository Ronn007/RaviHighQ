package com.highq.test.mysite;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
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
public class MyFiles_TCR0205 extends BannerPageWeb
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

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String jsonFileName = "preConfiguration_MyFiles_TCR0205.json";
	String username = "usernormal1@file205.com";
	String username1 = "usernormal2@file205.com";
	String password = "Password@123";
	String recipientMail = "usernormal2@file205.com";
	String subject = "myFiles_TCR0205 - Share file/folder";
	String add = "Folder";
	String uploadItem = "doc1.txt";
	String folder = "FolderOfTCR0205";
	String message = "This is a test message";
	String operation = "Share";
	String destination = "My files(1)";
	String shared = "Shared items";
	String accessLevel = "Anyone with the link";
	String permission = "Download, view and print";
	String fileExpiry = "after 30 days";
	String received = "Received items";
	String index = "My files";
	String removeShareMessageFile = "doc1 was shared via the quick Send a file process. If you remove the share, the file will also be permanently deleted.\n" +
			"\n" +
			"Are you sure you want to remove the share and permanently delete the file?";

	String removeShareMessageFolder = "FolderOfTCR0205 may have been shared multiple times with different people. Removing the share will remove all shares for all people.\n" +
			"\n" +
			"Are you sure you want to remove all shares?";

	@Test
	public void myFiles_TCR0205() throws IOException, JSONException, InterruptedException, UnsupportedFlavorException
	{

		siteAndUserConfiguration();
		precondition();
		scenario1();
	}

	private void scenario1()
	{

		sharedItemFile();
		sharedItemFolder();
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
		logout();

		/** Sending files and adding folder so that it exist in shared items */
		bannerPageWeb = login(username, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
		myFilesWeb.SendAfile(uploadItem, recipientMail, subject, accessLevel, permission, fileExpiry);
		myFilesWeb.clickOnCloseButtonOfSendAFileModal();
		myFilesWeb.selectItemFromNew(add);
		myFilesWeb.addFolderAtSpecificLocation(folder, destination);
		myFilesWeb.clickAddInAddFolderModal();
		myFilesWeb.selectLeftPanelFileOptions(index);
		myFilesWeb.gotoMoreActions(folder, operation);
		myFilesWeb.shareViaEmail(recipientMail, subject, subject, accessLevel, fileExpiry);
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
	}

	private void sharedItemFile()
	{

		myFilesWeb.selectLeftPanelFileOptions(shared);
		myFilesWeb.removeShare(uploadItem);

		/** Verify message when user clicks on delete button */
		Assert.assertTrue(myFilesWeb.verifyRemoveShareMessage(removeShareMessageFile));

		/** Verify cancel and remove buttons */
		Assert.assertTrue(myFilesWeb.verifyCancelButton());
		Assert.assertTrue(myFilesWeb.verifyRemoveButton());

		/** Verify modal visibility when user clicks on cancel button */
		myFilesWeb.clickOnCancelOfRemoveShare();
		Assert.assertTrue(myFilesWeb.verifySharedFileVisibility(uploadItem));

		myFilesWeb.removeShare(uploadItem);
		myFilesWeb.clickOnDeleteOfRemoveShare();

		/** Verify file visibility when user clicks on delete button */
		Assert.assertFalse(myFilesWeb.verifySharedFileVisibility(uploadItem));
		dashboardWeb = myFilesWeb.gotoDashboard();
		logout();

		/** Verify file visibility of shared files in received items of user after it is removed */
		bannerPageWeb = login(username1, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(received);
		Assert.assertFalse(myFilesWeb.verifyFilesInReceivedItems(uploadItem));

		dashboardWeb = myFilesWeb.gotoDashboard();
		logout();
	}

	private void sharedItemFolder()
	{

		bannerPageWeb = login(username, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(shared);
		myFilesWeb.removeShare(folder);

		/** Verify message when user clicks on delete button */
		Assert.assertTrue(myFilesWeb.verifyRemoveShareMessage(removeShareMessageFolder));

		/** Verify cancel and remove buttons */
		Assert.assertTrue(myFilesWeb.verifyCancelButton());
		Assert.assertTrue(myFilesWeb.verifyRemoveButton());

		myFilesWeb.clickOnCancelOfRemoveShare();
		Assert.assertTrue(myFilesWeb.verifySharedFileVisibility(folder));

		myFilesWeb.removeShare(folder);
		myFilesWeb.clickOnDeleteOfRemoveShare();

		/** Verify file visibility when user clicks on delete button */
		Assert.assertFalse(myFilesWeb.verifySharedFileVisibility(folder));
		dashboardWeb = myFilesWeb.gotoDashboard();
		logout();

		/** Verify file visibility of shared files in received items of user after it is removed */
		bannerPageWeb = login(username1, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(received);
		Assert.assertFalse(myFilesWeb.verifyFilesInReceivedItems(uploadItem));
	}

}
