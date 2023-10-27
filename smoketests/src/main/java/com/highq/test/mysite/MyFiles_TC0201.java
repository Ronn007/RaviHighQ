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

/**
 * @author khushbu.dhandhukiya
 */
public class MyFiles_TC0201 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = "ON";
	String optionValue = "ON for all system users";
	String addMenuItem = "Files";
	String[] multiFiles = {"doc1.txt", "doc2.txt", "doc3.txt"};
	String addItem = "Folder";
	String folderName = "Folder TC0201";
	String folderDescription = "Folder is created";
	String orgType = "Internal";
	String[] userNames = {"normaluser", "siteadminuser"};
	String domain = "file.com";
	String newPassword = "Password@123";
	String actionItem = "Move or Copy";
	String parentFolder = "My files";
	String siteName = "My site";

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
	public void MyFilesTC0201() throws InterruptedException, IOException, JSONException
	{
		precondition();
		sitePrecondition();
		scenario1();
		scenario2();
		scenario3();

	}

	public void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Scenario 1 : Move Files and folders */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();

		myFilesWeb.searchFile(multiFiles[1]);
		myFilesWeb.selectFileCheckBox(multiFiles[1]);
		myFilesWeb.selectItemFromAction(actionItem);

		myFilesWeb.moveFile("", parentFolder, folderName);
		myFilesWeb.searchFile(multiFiles[1]);
		assertTrue(!myFilesWeb.verifyDocumentUploaded(multiFiles[1]));

		/** Verify file is successfully moved to destination */
		myFilesWeb.selectLeftPanelFileOptions(folderName);
		myFilesWeb.searchFile(multiFiles[1]);
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[1]));
		myFilesWeb.logout();

	}

	public void scenario2() throws InterruptedException, IOException
	{
		/** Scenario 2: Scenario 1 : Copy Files and folders */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();

		myFilesWeb.searchFile(multiFiles[2]);
		myFilesWeb.selectFileCheckBox(multiFiles[2]);
		myFilesWeb.selectItemFromAction(actionItem);
		myFilesWeb.copyFile(siteName, parentFolder, folderName);

		myFilesWeb.selectLeftPanelFileOptions(folderName);
		myFilesWeb.searchFile(multiFiles[2]);
		/** Verify files and Folders Should be copy at Selected Location */
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[2]));
		myFilesWeb.logout();
	}

	public void scenario3() throws InterruptedException, IOException
	{
		/** Scenario 3: Scenario 1 : Copy Files and folders */
		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.searchFile(multiFiles[0]);
		assertTrue(myFilesWeb.verifyDocumentUploaded(multiFiles[0]));

		myFilesWeb.searchFile(folderName);
		/** verify search file should be display **/
		assertTrue(myFilesWeb.verifyDocumentUploaded(folderName));
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

}
