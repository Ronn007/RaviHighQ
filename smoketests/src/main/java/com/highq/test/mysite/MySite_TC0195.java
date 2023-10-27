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

public class MySite_TC0195 extends BannerPageWeb
{

	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String optionToSelect = "ON";
	String optionValue = "ON for all system users";
	String orgType = "Internal";
	String newPassword = "Password@123";
	String addMenuItem = "Files";
	String domain = "file.com";
	String[] userNames = {"normaluser", "siteadminuser"};
	String[] multiFiles = {"doc1.txt", "doc2.txt", "doc3.txt", "profilepicture.jpg"};
	String addItem = "Folder";
	String folderName = "Folder TC0195";
	String folderDescription = "Folder is created";
	String actionItem = "Download";
	String siteNameRoot = "My files";
	String downloadOperation = "Download";
	String siteName = "My site.zip";
	String option = "Index";
	String bulkPrintOperation = "Bulk print";
	String msg = "0%" + "\n" + "Generating PDF file… this may take some time".trim();
	String bulkMsg = "Your file is now ready to download" + "\n" + "Click download to continue. Please note that for larger sites or folders, the download may take some time.".trim();

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
	public void MySiteTC0195() throws InterruptedException, IOException, JSONException
	{
		precondition();
		sitePrecondition();
		scenario1();
		scenario2();
		scenario3();
		scenario3Case2();

	}

	public void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Download single file */

		bannerPageWeb = login(userNames[0] + "@" + domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.cleanDownloadsFolder();
		myFilesWeb.selectLeftPanelFileOptions(siteNameRoot);
		myFilesWeb.downloadFile(multiFiles[0]);
		/** Verify file in Downloads folder */
		assertTrue(myFilesWeb.verifyDownloadedFile(multiFiles[0]));

	}

	public void scenario2() throws InterruptedException, IOException
	{
		/** Scenario 2: Download folder */

		myFilesWeb = bannerPageWeb.goToMyFiles();

		myFilesWeb.cleanDownloadsFolder();
		myFilesWeb.searchFile(folderName);
		myFilesWeb.selectFileCheckBox(folderName);
		myFilesWeb.clickOnActionButton(downloadOperation);

		/** Verify file in Downloads folder with sitename(.zip) */
		assertTrue(myFilesWeb.verifyZipFileContainsDesiredFilesAndFolders(siteName, folderName));
		assertTrue(myFilesWeb.verifyDownloadedFile(siteName));

	}

	public void scenario3() throws InterruptedException, IOException
	{
		/** Scenario 3: Bulk Print and Bulk Download */

		myFilesWeb.goToMyFiles();

		myFilesWeb.selectLeftPanelFileOptions(option);
		myFilesWeb.selectIndexFileCheckBox(multiFiles[3]);
		myFilesWeb.clickOnIndexActionButton(bulkPrintOperation);

		assertTrue(myFilesWeb.verifyBulkPrintMsg(bulkMsg));
		assertTrue(myFilesWeb.verifyIndexCancelButton());
		assertTrue(myFilesWeb.verifyIndexDownloadButton());
		myFilesWeb.clickOnIndexCancelButton();
		assertTrue(!myFilesWeb.verifyIndexDownloadButton());
		myFilesWeb.clickOnIndexActionButton(bulkPrintOperation);
		myFilesWeb.clickOnIndexDownloadButton();
		myFilesWeb.clickOnIndexCancelButton();
		myFilesWeb.selectLeftPanelFileOptions(option);

		/** expected result remaining due to Pdf Document text reader verification after download the file **/
		/** confirm with Ravi **/

	}

	public void scenario3Case2() throws InterruptedException, IOException
	{

		myFilesWeb.goToMyFiles();
		myFilesWeb.cleanDownloadsFolder();
		myFilesWeb.selectLeftPanelFileOptions(option);

		myFilesWeb.selectIndexFileCheckBox(siteNameRoot);
		myFilesWeb.clickOnIndexActionButton(downloadOperation);
		myFilesWeb.clickOnIndexDownload();

		/** Verify file in Downloads folder with sitename(.zip) */

		assertTrue(myFilesWeb.verifyDownloadedFile(siteName));
		assertTrue(myFilesWeb.verifyZipFileContainsDesiredFilesAndFolders(siteName, multiFiles));

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
		myFilesWeb.selectItemFromAdd(addMenuItem);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(multiFiles[1]);
		myFilesWeb.addFile(addDoc, null);
		myFilesWeb.clickAddInModal();
		myFilesWeb.goToMyFiles();
		myFilesWeb.logout();

	}

}
