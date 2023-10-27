package com.highq.test.mysite;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.labels.collaborate.MyFilesLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class MySite_TC0198 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = "ON";
	String optionValue = "ON for all system users";
	String orgType = "Internal";
	String newPassword = "Password@123";
	String addMenuItem = "Files";
	String[] multiFiles = {"doc1.txt", "doc2.txt", "doc3.txt"};
	String addItem = "Folder";
	String folderName = "Folder TC0198";
	String folderDescription = "Folder is created";
	String[] userNames = {"normaluser", "siteadminuser"};
	String domain = "file.com";
	String actionItem = "Delete";
	String deleteMsg = MyFilesLabels.MYFILES_COMMON_DELETE;
	String option = "Deleted items";
	String deleteMsgFromDeletedItems = MyFilesLabels.MYFILES_COMMON_DELETE_MESSAGE_FROM_DELETEDITEMS;
	String restoreMsg = "Are you ready to restore?" + "\n" + " Permissions can't be restored and will inherit the parent folder permissions".trim();
	String restoreMsgTitle = "Message";
	String parentFolder = "My files";
	String siteName = "My site";
	String deleteOperation = "Delete";
	String restoreOperation = "Restore";
	String gotoRootFolder = "My files";
	String moveOperation = "Move";

	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	MyFilesPage myFilesWeb;

	ConfigurationData configurationData = new ConfigurationData();

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void MySiteTC0198() throws InterruptedException, IOException, JSONException
	{
		precondition();
		sitePrecondition();
		scenario1();
		PreconditionForscenario2();
		scenario2();
		PreconditionForscenario3();
		scenario3();
		PreconditionForscenario3Case2();
		scenario3Case2();

	}

	public void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Delete My Files and folder */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.gotoMoreActions(multiFiles[0], actionItem);

		assertTrue(myFilesWeb.verifyDeleteMessage(deleteMsg));
		assertTrue(myFilesWeb.verifyDeleteButton());
		assertTrue(myFilesWeb.verifyDeleteCancelButton());

		myFilesWeb.clickOnCancelOfDeleteButton();
		/** verify After click on cancel button File should Remain in file middle panel **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));

		myFilesWeb.gotoMoreActions(multiFiles[0], actionItem);
		myFilesWeb.clickOnDelete();

		/** verify After click on cancel button File should not be displayed. **/
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[0]));

		myFilesWeb.selectLeftPanelFileOptions(option);
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));

		myFilesWeb.goToMyFiles();

	}

	public void scenario2() throws InterruptedException, IOException
	{
		/** Scenario 2: Delete My Files and folders from Deleted items */

		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.clickOnActionButton(deleteOperation);

		/** verify delete button,cancel button and Delete message **/
		assertTrue(myFilesWeb.verifyDeleteMessage(deleteMsgFromDeletedItems));
		assertTrue(myFilesWeb.verifyDeleteButton());
		assertTrue(myFilesWeb.verifyDeleteCancelButton());

		myFilesWeb.clickOnCancelOfDeleteButton();
		/** verify After click on cancel button File should Remain in file middle panel **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));

		myFilesWeb.gotoMoreActions(multiFiles[0], actionItem);
		myFilesWeb.clickOnDelete();
		/** verify after click on delete from deleted items it should Render deleted item page **/
		assertTrue(myFilesWeb.verifyDeletedPageRender());

		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.clickOnActionButton(deleteOperation);
		myFilesWeb.clickOnDelete();
		;
		/** verify after delete All files from deleted items it should Render My file page **/
		assertTrue(myFilesWeb.verifyMyFilePageRender());

		myFilesWeb.goToMyFiles();

	}

	public void scenario3() throws InterruptedException, IOException
	{
		/** Scenario 3: Restore / Move from "Deleted items" */
		/** case 1:Restore File/folder from Deleted items **/

		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.clickOnActionButton(restoreOperation);

		/** verify Restore Message From deleted items **/

		assertTrue(myFilesWeb.verifyRestoreTitle(restoreMsgTitle));
		assertTrue(myFilesWeb.verifyRestoreMessage(restoreMsg.trim()));

		myFilesWeb.clickOnCancel();
		/** verify After click on cancel button File should Remain in file middle panel **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[2]));

		myFilesWeb.clickSelectAllCheckbox();
		myFilesWeb.clickOnActionButton(restoreOperation);
		myFilesWeb.clickOnContine();

		/** verify After click on continue button file should not be display in file middle panel **/
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[0]));
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[2]));

		myFilesWeb.selectLeftPanelFileOptions(gotoRootFolder);

		/** verify Files and folder should be restored to the default location from where it was deleted. **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[2]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(folderName));

		myFilesWeb.goToMyFiles();

	}

	public void scenario3Case2() throws InterruptedException, IOException
	{
		/** Scenario 3: Restore / Move from "Deleted items" */
		/** case 2:move File/folder from Deleted items **/

		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.clickOnActionButton(moveOperation);

		/** verify Move Window will be open **/
		assertTrue(myFilesWeb.verifyMoveModal());

		myFilesWeb.moveFile(siteName, parentFolder, "");
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[0]));
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[2]));
		assertTrue(!myFilesWeb.verifyDocumentUploaded(folderName));

		myFilesWeb.selectLeftPanelFileOptions(gotoRootFolder);

		/** verify files and Folders Should be moved at Selected Location from deleted item **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[2]));
		assertTrue(myFilesWeb.verifyDocumentUploaded(folderName));
		myFilesWeb.goToMyFiles();
		myFilesWeb.logout();

	}

	private void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);

		String[] configurationList = {"setOrg", "addSystemAdminUsers"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		logout();

	}

	private void sitePrecondition() throws InterruptedException, IOException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();

		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableMyFiles(optionToSelect);
		aspConfigurationWeb.enableMyFilesSharing(optionValue);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyFiles(optionToSelect);
		systemAdminSystemSettingsWeb.enableMyFilesSharing(optionValue);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.logout();

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = dashboardWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
		myFilesWeb.selectItemFromAdd(addMenuItem);
		for (int i = 0; i < multiFiles.length; i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(multiFiles[i]);
			myFilesWeb.addFile(addDoc, null);
		}
		myFilesWeb.clickAddInModal();
		myFilesWeb.selectItemFromNew(addItem);
		myFilesWeb.createNewFolderInRoot(folderName, folderDescription);
		myFilesWeb.clickAddInModal();
		myFilesWeb.goToMyFiles();
		myFilesWeb.logout();

	}

	private void PreconditionForscenario2() throws InterruptedException, IOException, JSONException
	{

		myFilesWeb.selectItemFromAdd(addMenuItem);
		for (int i = 0; i < multiFiles.length; i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(multiFiles[i]);
			myFilesWeb.addFile(addDoc, null);
		}
		myFilesWeb.clickAddInModal();
		myFilesWeb.selectItemFromNew(addItem);
		myFilesWeb.createNewFolderInRoot(folderName, folderDescription);
		myFilesWeb.clickAddInModal();
		myFilesWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
		myFilesWeb.goToMyFiles();

	}

	private void PreconditionForscenario3() throws InterruptedException, IOException, JSONException
	{

		myFilesWeb.selectItemFromAdd(addMenuItem);
		for (int i = 0; i < multiFiles.length; i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(multiFiles[i]);
			myFilesWeb.addFile(addDoc, null);
		}
		myFilesWeb.clickAddInModal();
		myFilesWeb.selectItemFromNew(addItem);
		myFilesWeb.createNewFolderInRoot(folderName, folderDescription);
		myFilesWeb.clickAddInModal();
		myFilesWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
		myFilesWeb.goToMyFiles();

	}

	private void PreconditionForscenario3Case2() throws InterruptedException, IOException, JSONException
	{

		myFilesWeb.deleteAllDocs();
		myFilesWeb.goToMyFiles();

	}

}
