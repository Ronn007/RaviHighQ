package com.highq.test.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author paras.vankadi
 */
public class Files_TC2722 extends BannerPageWeb
{
	/** Add_Edit_Delete_Download File */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String normalUserEmail = "normal.onlineOffice@Highq.com";// "admin.user@highq.com";
	String normalUserPassword = "Password@123";

	String siteName = "File_TC2722";
	String[] userNames = {"auto.officeonline", "site.onlineOffice", "contain.onlineOffice", "normal.onlineOffice"};
	String[] userEmail = {"auto.officeonline@highq.com", "site.onlineOffice@highq.com", "contain.onlineOffice@highq.com", "normal.onlineOffice@Highq.com"};

	String jsonFileName = "Files/preConfiguration_File_TC2722.json";
	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	String[] addItem = {"Files", "Zipped files", "Placeholder file", "New folder"};
	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	String newTag = "tag2";
	String newDisclaimer = "disclaimer2";

	String[] files = {"File2.doc", "File3.ppt", "file1.xlsx"};
	String userForLogin;
	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DocumentPage documentWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	AddUserPage addUserWeb;
	SystemAdminPage systemAdminWeb;
	Map<String, String> fileOpts;
	BannerPage bannerPageWebSimultaneously;
	DocumentPage documentWebSimultaneously;
	DashboardPage dashboardWebSimultaneously;
	AdminPage adminPageWebSimultaneously;
	AdminFilesPage adminFilesWebSimultaneously;
	Set<String> set = new HashSet<String>();
	Map<String, String> zipFileMap = new HashMap<>();
	String zipFile = "onlineSupportedFiles.zip";
	String openInOpt = "Open in...";
	String[] filesName;

	@BeforeClass
	public void setUp() throws InterruptedException, IOException, JSONException
	{
		driver = getDriver();
		initilizeMpaValues();
	}

	@Test(priority = 1)
	public void TC2722() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1();
		scenario2();
		scenario3();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();

		Map<String, Boolean> onlineOfficeVerify = new HashMap();
		onlineOfficeVerify.clear();

		onlineOfficeVerify.put("docx", true);
		onlineOfficeVerify.put("docm", true);
		onlineOfficeVerify.put("odt", true);

		onlineOfficeVerify.put("xlsx", true);
		onlineOfficeVerify.put("xlsm", true);
		onlineOfficeVerify.put("xlsb", true);
		onlineOfficeVerify.put("ods", true);

		onlineOfficeVerify.put("pptx ", true);
		onlineOfficeVerify.put("odp", true);
		onlineOfficeVerify.put("ppsx", true);

		Assert.assertTrue(verifysetOnlineOfficeSetting(onlineOfficeVerify, optionValueON));
		onlineOfficeVerify.clear();
		onlineOfficeVerify.put("docx", false);
		onlineOfficeVerify.put("docm", false);
		onlineOfficeVerify.put("odt", false);

		onlineOfficeVerify.put("xlsx", false);
		onlineOfficeVerify.put("xlsm", false);
		onlineOfficeVerify.put("xlsb", false);
		onlineOfficeVerify.put("ods", false);

		onlineOfficeVerify.put("pptx ", false);
		onlineOfficeVerify.put("odp", false);
		onlineOfficeVerify.put("ppsx", false);
		Assert.assertTrue(verifysetOnlineOfficeSetting(onlineOfficeVerify, optionValueOFF));

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueOFF);
		aspConfigurationWeb.saveConfigurations();

		onlineOfficeVerify.clear();
		onlineOfficeVerify.put("docx", false);
		onlineOfficeVerify.put("docm", false);
		onlineOfficeVerify.put("odt", false);

		onlineOfficeVerify.put("xlsx", false);
		onlineOfficeVerify.put("xlsm", false);
		onlineOfficeVerify.put("xlsb", false);
		onlineOfficeVerify.put("ods", false);

		onlineOfficeVerify.put("pptx ", false);
		onlineOfficeVerify.put("odp", false);
		onlineOfficeVerify.put("ppsx", false);
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		Assert.assertTrue(verifysetOnlineOfficeSeettingfromFileTypes(onlineOfficeVerify));
		// logout();

	}

	private void scenario2() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemPreconfiguration(true);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(true);

		adminFilesWeb.saveFilesChanges();
		logout();
		for (int i = 0; i < userNames.length; i++)
		{
			bannerPageWeb = login(userEmail[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			documentWeb = dashboardWeb.gotoFileModule();
			for (String entry : filesName)
			{
				verifyFileOpenInSubmenuOptions(entry, true, true);
			}
			logout();
		}

	}

	private void scenario3() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		systemPreconfiguration(false);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(true);

		adminFilesWeb.saveFilesChanges();
		logout();
		for (int i = 0; i < userNames.length; i++)
		{
			bannerPageWeb = login(userEmail[i], sysAdminPassword);
			dashboardWeb = bannerPageWeb.gotoDashboard();
			dashboardWeb.searchSite(siteName);

			documentWeb = dashboardWeb.gotoFileModule();
			for (String entry : filesName)
			{
				verifyFileOpenInSubmenuOptions(entry, true, false);
			}
			logout();
		}

	}

	boolean verifysetOnlineOfficeSetting(Map<String, Boolean> testTypes, String optionValueON) throws InterruptedException
	{
		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.goBack();

		return verifysetOnlineOfficeSeettingfromFileTypes(testTypes);

	}

	boolean verifysetOnlineOfficeSeettingfromFileTypes(Map<String, Boolean> testTypes)
	{
		boolean val = true;
		SystemAdminFileOrFileTypesPage fileTypes = systemAdminWeb.gotoFileOrFileTypes();
		for (Entry<String, Boolean> entry : testTypes.entrySet())
		{
			String fileEXT = entry.getKey();
			boolean accOfficeStatus = entry.getValue();

			EditFileIconsPage editfile = fileTypes.gotoDocumentEditPage(fileEXT);

			if (editfile.isOfficeOnlinePropAvilableForFileAndFileType() != accOfficeStatus)
			{
				val = false;
				break;
			}
			editfile.clickCancel();

		}
		return val;
	}

	private void siteAdminConfiguration() throws InterruptedException
	{

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		gotoFileModuleAndAddZippFile(zipFile);

		adminPageWeb = documentWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.setFilePermission(SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES);
		adminFilesWeb.saveFilesChanges();
		documentWeb = adminFilesWeb.goToMyFiles();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromUpload("Zipped files");
		zipFileMap.clear();
		zipFileMap.put(TAGS, newTag);
		zipFileMap.put(DISCLAIMER, newDisclaimer);

		documentWeb.addZipFile(zipFile, zipFileMap);
		documentWeb.clickAddInModal();

		// logout();
	}

	void gotoFileModuleAndAddZippFile(String zipFileName)
	{
		documentWeb = bannerPageWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromUpload("Zipped files");

		zipFileMap.clear();
		zipFileMap.put(TAGS, newTag);
		zipFileMap.put(DISCLAIMER, newDisclaimer);
		documentWeb.addZipFile(zipFileName, zipFileMap);
		documentWeb.clickAddInModal();
	}

	private void systemPreconfiguration(boolean onlineCheckBox) throws InterruptedException
	{

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.goBack();

		SystemAdminFileOrFileTypesPage fileTypes = systemAdminWeb.gotoFileOrFileTypes();
		String[] testFileTypes = {"docx", "docm", "odt", "xlsx", "xlsm", "xlsb", "ods", "pptx", " odp", "ppsx"};
		for (int i = 0; i < testFileTypes.length; i++)
		{
			EditFileIconsPage editfile = fileTypes.gotoDocumentEditPage(testFileTypes[i]);
			{
				editfile.enableOnlineOfficeEdit(onlineCheckBox);
				editfile.enableOnlineEditingViaWebDAV(true);
				editfile.clickSave();
			}
		}
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		siteAdminConfiguration();
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

		logout();
	}

	void verifyFileOpenInSubmenuOptions(String fileName, boolean hasOfflineEditOpt, boolean hasOnlineOpt)
	{

		String fileEXT = getExtFromFileNmae(fileName);
		String smlOpt = getFileRelavantOpenInSubMenuOpt(fileEXT);

		documentWeb.gotoMoreActions(fileName, openInOpt, "hover");

		Assert.assertEquals(documentWeb.verifySubmenuOption(smlOpt + " Online"), hasOnlineOpt);

		Assert.assertEquals(documentWeb.verifySubmenuOption(smlOpt), hasOfflineEditOpt);

	}

	String getFileRelavantOpenInSubMenuOpt(String fileName)
	{
		return fileOpts.get(getExtFromFileNmae(fileName));
	}

	String getExtFromFileNmae(String fileName)
	{
		return fileName.substring(fileName.indexOf('.') + 1, fileName.length());
	}

	void initilizeMpaValues() throws IOException
	{
		fileOpts = new HashMap();
		fileOpts.put("xlsx", "Excel");
		fileOpts.put("xlsm", "Excel");
		fileOpts.put("xlsb", "Excel");
		fileOpts.put("ods", "Excel");

		fileOpts.put("ppsx", "PowerPoint");
		fileOpts.put("pptx", "PowerPoint");
		fileOpts.put("odp", "PowerPoint");

		fileOpts.put("docx", "Word");
		fileOpts.put("docm", "Word");
		fileOpts.put("odt", "Word");
		filesName = getFileListFromZip(zipFile);
	}

	public String[] getFileListFromZip(String zipFileName) throws IOException
	{
		String zipFilePath;

		zipFilePath = new File(TestBaseSetup.currentDir + "\\testData\\" + zipFileName + "").getCanonicalPath().trim();

		ZipFile zipFile = new ZipFile(zipFilePath);

		ArrayList<String> fileList = new ArrayList<>();

		Enumeration<?> entries = zipFile.entries();
		while (entries.hasMoreElements())
		{
			ZipEntry ze = (ZipEntry) entries.nextElement();
			fileList.add(ze.getName());
		}
		return fileList.toArray(new String[0]);
	}
}
