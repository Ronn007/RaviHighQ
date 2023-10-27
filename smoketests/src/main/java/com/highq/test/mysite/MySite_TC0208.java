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

public class MySite_TC0208 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = "ON";
	String optionValue = "ON for all system users";
	String orgType = "Internal";
	String userName = "normal.user1";
	String domain = "file.com";
	String newPassword = "Password@123";
	String[] userNames = {"normaluser", "siteadminuser"};
	String files = "doc1.txt";
	String addItem = "New folder";
	String folderName = "Folder TC0208";
	String folderDescription = "Folder is created";
	String[] addMenuItem = {"Files", "Folder"};
	String actionItem = "Remove";

	String[] recipientUsers = {"normaluser@file.com", "normal.user1@file.com"};

	String message = "This is Test message";
	String subject = "User Send a File";
	String removeMessage = MyFilesLabels.MYFILES_DELETE_CONFIRMATION_MESSAGE;
	String operation = "Share";
	String option = "Received items";
	String accessLevel="Anyone with the link";
	String linkeExpires="Never";

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
	public void MySiteTC0208() throws InterruptedException, IOException, JSONException
	{
		precondition();
		sitePrecondition();
		scenario1();

	}

	public void scenario1() throws InterruptedException, IOException
	{
		/** Secnario1 Delete a received file and folder from Received Items **/
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.searchFile(files);
		myFilesWeb.selectFileCheckBox(files);
		myFilesWeb.selectItemFromAction(actionItem);

		/** verify RemoveReceived item model **/
		assertTrue(myFilesWeb.verifyRemovedReceivedItemModal());
		assertTrue(myFilesWeb.verifyRemoveShareMessage(removeMessage));
		assertTrue(myFilesWeb.verifyRemoveButton());
		assertTrue(myFilesWeb.verifyCancelButton());

		myFilesWeb.clickOnCancelOfRemoveShare();
		myFilesWeb.selectLeftPanelFileOptions(option);
		/** verify after click on cancel file/folder should Remain same **/
		assertTrue(myFilesWeb.verifyReceivedFileVisibility(files));
		assertTrue(myFilesWeb.verifyReceivedFileVisibility(folderName));
		assertTrue(myFilesWeb.verifyReceivedItemPage());

		myFilesWeb.searchFile(files);
		myFilesWeb.selectFileCheckBox(files);
		myFilesWeb.selectItemFromAction(actionItem);
		myFilesWeb.clickOnDeleteOfRemoveShare();
		assertTrue(!myFilesWeb.verifyReceivedFileVisibility(files));

		myFilesWeb.searchFile(folderName);
		myFilesWeb.selectFileCheckBox(folderName);
		myFilesWeb.selectItemFromAction(actionItem);

		/** verify Remove Received model,cancel,Remove button and remove message **/
		assertTrue(myFilesWeb.verifyRemovedReceivedItemModal());
		assertTrue(myFilesWeb.verifyRemoveShareMessage(removeMessage));
		assertTrue(myFilesWeb.verifyRemoveButton());
		assertTrue(myFilesWeb.verifyCancelButton());

		myFilesWeb.clickOnCancelOfRemoveShare();

		assertTrue(myFilesWeb.verifyReceivedFileVisibility(folderName));
		assertTrue(myFilesWeb.verifyReceivedItemPage());

		myFilesWeb.searchFile(folderName);
		myFilesWeb.selectFileCheckBox(folderName);
		myFilesWeb.selectItemFromAction(actionItem);
		myFilesWeb.clickOnDeleteOfRemoveShare();
		assertTrue(!myFilesWeb.verifyReceivedFileVisibility(folderName));

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
		userMap.put(domain, Arrays.asList(userName));

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

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		myFilesWeb = dashboardWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();

		myFilesWeb.selectItemFromAdd(addMenuItem[1]);
		myFilesWeb.createNewFolderInRoot(folderName, folderDescription);
		myFilesWeb.clickAddInModal();
		myFilesWeb.goToMyFiles();
		myFilesWeb.gotoMoreActions(folderName, operation);
		myFilesWeb.shareViaEmail(recipientUsers[0],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		
		myFilesWeb.goToMyFiles();
		myFilesWeb.selectItemFromAdd(addMenuItem[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(files);
		myFilesWeb.addFile(addDoc, null);
		myFilesWeb.clickAddInModal();
		myFilesWeb.gotoMoreActions(files, operation);
		myFilesWeb.shareViaEmail(recipientUsers[0],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		myFilesWeb.logout();

	}

}
