package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import java.util.zip.ZipInputStream;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.BlogLabels;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminGeneralPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EditFileIconsPage;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.AddUserWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.DocumentWeb;

/**
 * @author gaurav.soni
 */
public class TC2704 extends BannerPageWeb
{

	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";

	// String sysAdminEmail = "tom.chick@Highq.com";
	// String sysAdminPassword = "Admin@123";

	// String sysAdminEmail;// = "auto.officeonline@highq.com";// "admin.user@highq.com";
	// String sysAdminPassword;// = "Password@123";

	String orgType = "Internal";

	String siteName = "TC2704";
	String siteNameTab = "TC2704__all_Tab";
	String siteNamePer = "TC2704_Permission";
	String siteNameMatrix = "TC2704_Matrix";
	String siteNameNonZipFile = "TC2704_Non_Supported_files";

	String siteAdminEmail = "site.admin27@file.com";
	String contentAdminEmail = "content.admin27@file.com";
	String normalUserEmail = "normal.user27@file.com";

	String[] userEmails = {siteAdminEmail, contentAdminEmail, normalUserEmail};
	String[] userids = {"site.admin27", "content.admin27", "normal.user27"};

	String domain = "file.com";
	String newPassword = "Password@12345rd";

	String zipFile = "onlineSupportedFiles.zip";
	String zipFilePer = "permFiles.zip";
	String editNotSupZip = "EditNotSupport.zip";
	String allExtFiles = "TC2704SC5.zip";

	String allNonSupFiles = "ALLnonSupportedFiles.zip";

	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	final String optionValueFalse = "False";

	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	DashboardPage dashboardWeb;
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	DocumentPage documentWeb;
	BlogPage blogWeb;

	By fileBanner = By.xpath("(//*[contains(@class,'rightSideSection')])[1]//*[@class='infoHeadFixed']");

	String[] files = {};
	String[] allOfficeNonSupFiles = {};

	String[] fileViewList = {FileLabels.FILES_LISTVIEW, FileLabels.FILES_COLUMNVIEW, FileLabels.FILES_THUMBNAILVIEW};
	String[] myFileViewList = {FileLabels.FILES_LISTVIEW, FileLabels.FILES_THUMBNAILVIEW};

	String[] fileTabs = {siteNameTab, "Recent", "Favourites", "Attachments"}; //

	String openInOpt = "Open in...";

	static Map<String, String> fileOpts;
	static Map<String, Boolean> fileWithRights;
	static Map<String, Boolean> siteAdminFileOfficeOnlineON;
	static Map<String, Boolean> siteAdminFileOfficeOnlineOFFList1;
	static Map<String, Boolean> siteAdminFileOfficeOnlineOFFList2;
	static Map<String, Boolean> webDevFromFileAllFileslist;
	static Map<String, Boolean> webDevFromFileTypeCommon;
	static Map<String, Boolean> nonSupportedFileTypes;

	Set<String> openINNotAvilabe;

	PreConfiguration preConfigurationTest;

	@BeforeClass
	public void setUp() throws InterruptedException, IOException, JSONException
	{
		driver = getDriver();
		preConfigurationTest = new PreConfiguration(driver);
		initilizeMpaValues();

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		sitePreconfigurationTC2704();
		systemPreconfiguration(webDevFromFileTypeCommon);
		logout();
	}

	@Test(groups = {"officeOnline"})
	public void TC2704() throws InterruptedException, IOException, JSONException
	{
		scenario1P1();
		scenario1P2();
		scenario1P3();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
	}

	// @Test(priority = 1)
	void scenario1P1() throws InterruptedException
	{

		for (int i = 0; i < userEmails.length; i++)
		{
			documentWeb = loginAndGotoFileModule(userEmails[i], newPassword, siteName);
			verifyFileOpenInSubmenu(files);
			logout();
		}

	}

	// @Test(priority = 2)
	void scenario1P2() throws InterruptedException, IOException, JSONException
	{

		sitePreconfigurationTC2704FileAllTABS();

		for (int i = 0; i < userEmails.length; i++)
		{
			documentWeb = loginAndGotoFileModule(userEmails[i], newPassword, siteNameTab);

			for (int n = 3; n < fileTabs.length; n++)
			{
				documentWeb.selectLeftPanelFileOptions(fileTabs[n]);

				if (fileTabs[n].equals(siteNameTab))
				{
					for (int k = 0; k < fileViewList.length; k++)
					{
						documentWeb.selectItemFromView(fileViewList[k]);
						verifyFileOpenInSubmenu(files);
					}
					documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);
				}
				else
				{
					verifyFileOpenInSubmenu(files);
				}
			}

			logout();
		}

	}

	// @Test(priority = 3)
	void scenario1P3() throws InterruptedException
	{

		String[] myFileTabs = {"My files", "Recent", "Favourites", "Attachments"};

		bannerPageWeb = login(normalUserEmail, newPassword);
		bannerPageWeb.gotoDashboard();
		documentWeb = bannerPageWeb.goToMyFiles();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd("Zipped files");
		documentWeb.addZipFile(zipFile, null);
		documentWeb.clickAddInModal();

		markAsFvaorits(files);

		String taskTitle = "sample";

		TasksPage tasksWeb = bannerPageWeb.goToMyTasks();
		if (!tasksWeb.verifyTaskVisibility(taskTitle))
		{
			tasksWeb.clickHeaderAddButton();
			tasksWeb.setTaskTitle(taskTitle);
			for (int i = 0; i < files.length; i++)
				tasksWeb.attachFileInTask(files[i]);

			tasksWeb.clickOnAddTaskButtonInModel();
		}

		documentWeb = bannerPageWeb.goToMyFiles();

		for (int n = 0; n < myFileTabs.length; n++)
		{

			documentWeb.selectLeftPanelFileOptions(myFileTabs[n]);

			if (fileTabs[n].equals(siteNameTab))
			{
				for (int k = 0; k < myFileViewList.length; k++)
				{
					documentWeb.selectItemFromView(myFileViewList[k]);
					verifyFileOpenInSubmenu(files);
				}
				documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);
			}
			else
			{
				verifyFileOpenInSubmenu(files);
			}
		}

		logout();
	}

	// @Test(priority = 4)
	void scenario2() throws InterruptedException, IOException, JSONException
	{

		presetUp_2704_per();

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNamePer);

		for (Entry<String, Boolean> entry : fileWithRights.entrySet())
		{
			String fileNmae = entry.getKey();
			boolean hasFileEditPer = entry.getValue();

			String smlOpt = getFileRelavantOpenInSubMenuOpt(fileNmae);

			documentWeb.gotoMoreActions(fileNmae, openInOpt, "hover");

			Assert.assertTrue(documentWeb.verifySubmenuOption(smlOpt + " Online"), "Fail to find Online option for File :" + fileNmae);

			if (hasFileEditPer)
				Assert.assertTrue(documentWeb.verifySubmenuOption(smlOpt), "Fail to find offline option for File :" + fileNmae);
		}

		logout();
	}

	// @Test(priority = 5)
	void scenario3() throws InterruptedException
	{
		try
		{
			changeSiteStatus(siteName, "Read only");

			for (int i = 0; i < userEmails.length; i++)
			{
				documentWeb = loginAndGotoFileModule(userEmails[i], newPassword, siteName);
				documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);

				for (int j = 0; j < files.length; j++)
				{
					String smlOpt = getFileRelavantOpenInSubMenuOpt(files[j]);

					documentWeb.gotoMoreActions(files[j], openInOpt, "hover");
					Assert.assertTrue(documentWeb.verifySubmenuOption(smlOpt + " Online"));
					Assert.assertTrue(!documentWeb.verifySubmenuOption(smlOpt));
				}

				logout();
			}

		}
		catch (Exception e)
		{
			Assert.assertTrue(false, e.toString());
		}
		finally
		{
			changeSiteStatus(siteName, "Active");
		}

	}

	// @Test(priority = 6)
	void scenario4() throws InterruptedException, IOException, JSONException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		sitePreconfigurationTC2704_non_supported_files(editNotSupZip);
		logout();

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNameNonZipFile);

		Assert.assertFalse(documentWeb.verifyMoreActionsItem("pdffile.pdf", openInOpt));
		Assert.assertFalse(documentWeb.verifyMoreActionsItem("txtfile.txt", openInOpt));

		logout();

	}

	// @Test(priority = 7)
	void scenario5() throws InterruptedException, IOException, JSONException
	{

		preConditionSCenario5();

		SetSiteAdminOpenOfficeOnline(true, siteAdminFileOfficeOnlineON);

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNameMatrix);

		for (Entry<String, Boolean> entry : siteAdminFileOfficeOnlineON.entrySet())
			verifyFileOpenInSubmenuOptions(entry.getKey(), entry.getValue(), true);

		logout();

		SetSiteAdminOpenOfficeOnline(false, siteAdminFileOfficeOnlineOFFList1);

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNameMatrix);

		for (Entry<String, Boolean> entry : siteAdminFileOfficeOnlineOFFList1.entrySet())
		{
			String fileNmae = entry.getKey();
			Assert.assertTrue(!documentWeb.verifyMoreActionsItem(fileNmae, "Open in..."));
		}

		logout();

		SetSiteAdminOpenOfficeOnline(false, siteAdminFileOfficeOnlineOFFList2);

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNameMatrix);

		for (Entry<String, Boolean> entry : siteAdminFileOfficeOnlineOFFList2.entrySet())
		{
			String fileNmae = entry.getKey();
			verifyFileOpenInSubmenuOptions(fileNmae, entry.getValue(), false);
		}

		logout();

	}

	// @Test(priority = 6)
	void scenario6() throws InterruptedException, IOException, JSONException
	{
		String errMsg = "Open in option should not be displayed to file ";

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		sitePreconfigurationTC2704_non_supported_files(allNonSupFiles);
		systemPreconfiguration(nonSupportedFileTypes);
		logout();

		documentWeb = loginAndGotoFileModule(normalUserEmail, newPassword, siteNameNonZipFile);

		for (String fileNmae : allOfficeNonSupFiles)
		{
			verifyFileOpenInSubmenuOptions(fileNmae, true, false);
		}
		logout();
	}

	void preConditionSCenario5() throws InterruptedException, IOException, JSONException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("Files/File_2704_matrix.json"));

		addFilesToSite(allExtFiles);

		logout();
	}

	private void sitePreconfigurationTC2704() throws InterruptedException, IOException, JSONException
	{
		dashboardWeb = bannerPageWeb.gotoDashboard();

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("Files/File_2704.json"));

		addFilesToSite(zipFile);

	}

	private void sitePreconfigurationTC2704_non_supported_files(String zipFile) throws InterruptedException, IOException, JSONException
	{
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("Files/File_2704_non_supported_files.json"));
		addFilesToSite(zipFile);
	}

	private void sitePreconfigurationTC2704FileAllTABS() throws InterruptedException, IOException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("Files/File_2704_all_tabs.json"));

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteNameTab);

		adminPageWeb = bannerPageWeb.gotoAdminModule();

		createFileMetaIsheetIfNotCreated();

		addFilesToSite(zipFile);

		documentWeb.gotoTitleHeaderMoreActionItem("Edit details");
		documentWeb.gotoSettingsTab();
		documentWeb.selectFileViewFromSettingTab("Default");
		documentWeb.clickSaveInModal();
		documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);

		for (int j = 0; j < files.length; j++)
		{
			documentWeb.editFileDetails(files[j], null);
			documentWeb.clickSaveInModal();
		}

		logout();

		markFavfilesForAllusersAndAddBlog();

	}

	private void presetUp_2704_per() throws InterruptedException, IOException, JSONException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson("Files/File_2704_Per.json"));

		addFilesToSite(zipFilePer);

		adminPageWeb = bannerPageWeb.gotoAdminModule();

		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();

		adminFilesWeb.setFilePermission(SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES);

		adminFilesWeb.enableOpenOfficeOnline(true);

		adminFilesWeb.saveFilesChanges();

		AddUserWeb addUserWeb = adminPageWeb.clickUsersInLeftPanel();

		addUserWeb.openUserPermissionModel(normalUserEmail);

		for (Entry<String, Boolean> entry : fileWithRights.entrySet())
		{
			String fileNmae = entry.getKey();
			boolean hasFileEditPer = entry.getValue();

			addUserWeb.setFilesViewPermissionForFile(fileNmae, true);

			if (hasFileEditPer)
				addUserWeb.setFolderAddFilePermissionForFile(fileNmae, true);
		}
		addUserWeb.clickSaveInSetPermissions();

		logout();

	}

	private void systemPreconfiguration(Map<String, Boolean> testTypes) throws InterruptedException
	{

		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableEditFile(optionValueON);
		aspConfigurationWeb.enableEnableOpenInOfficeOnline(optionValueON);
		aspConfigurationWeb.enableDocumentAnalysisByHighQ(optionValueFalse);
		aspConfigurationWeb.enableDocumentAnalysisByKira(optionValueFalse);
		aspConfigurationWeb.enableDocumentAnalysisByLeverton(optionValueFalse);
		aspConfigurationWeb.saveConfigurations();

		systemAdminWeb = aspConfigurationWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.enableOpenInOfficeOnlineForSystemAdmin(optionValueON);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.goBack();

		setOnlineOfficeSeettingfromFileTypes(testTypes);

	}

	void initilizeMpaValues() throws IOException
	{
		fileOpts = new HashMap();
		fileOpts.put("xls", "Excel");
		fileOpts.put("xlsx", "Excel");
		fileOpts.put("xlsm", "Excel");
		fileOpts.put("xlsb", "Excel");
		fileOpts.put("ods", "Excel");
		fileOpts.put("xlam", "Excel");
		fileOpts.put("xltm", "Excel");
		fileOpts.put("xltx", "Excel");

		fileOpts.put("ppt", "PowerPoint");
		fileOpts.put("pptm", "PowerPoint");
		fileOpts.put("potm", "PowerPoint");
		fileOpts.put("potx", "PowerPoint");
		fileOpts.put("ppsx", "PowerPoint");
		fileOpts.put("pptx", "PowerPoint");
		fileOpts.put("odp", "PowerPoint");
		fileOpts.put("ppam", "PowerPoint");
		fileOpts.put("ppsm", "PowerPoint");
		fileOpts.put("sldx", "PowerPoint");
		fileOpts.put("sldm", "PowerPoint");
		fileOpts.put("thmx", "PowerPoint");

		fileOpts.put("docx", "Word");
		fileOpts.put("dotx", "Word");
		fileOpts.put("dotm", "Word");
		fileOpts.put("docm", "Word");
		fileOpts.put("odt", "Word");
		fileOpts.put("doc", "Word");
		fileOpts.put("dot", "Word");

		fileWithRights = new HashMap();
		fileWithRights.put("File1.docx", false);
		fileWithRights.put("File4.docx", true);
		fileWithRights.put("File2.pptx", false);
		fileWithRights.put("File5.pptx", true);
		fileWithRights.put("File3.xlsx", false);
		fileWithRights.put("File6.xlsx", true);

		siteAdminFileOfficeOnlineON = new HashMap();
		siteAdminFileOfficeOnlineON.put("File12.pptx", true);
		siteAdminFileOfficeOnlineON.put("File6.docx", true);
		siteAdminFileOfficeOnlineON.put("File4.xlsm", true);

		siteAdminFileOfficeOnlineON.put("File2.xlsb", false);
		siteAdminFileOfficeOnlineON.put("File10.ppsx", false);
		siteAdminFileOfficeOnlineON.put("File5.odt", false);

		siteAdminFileOfficeOnlineOFFList1 = new HashMap();
		siteAdminFileOfficeOnlineOFFList1.put("File9.pptx", false);
		siteAdminFileOfficeOnlineOFFList1.put("File7.ods", false);
		siteAdminFileOfficeOnlineOFFList1.put("File1.xlsx", false);

		siteAdminFileOfficeOnlineOFFList2 = new HashMap();
		siteAdminFileOfficeOnlineOFFList2.put("File11.odp", true);
		siteAdminFileOfficeOnlineOFFList2.put("File8.docm", true);
		siteAdminFileOfficeOnlineOFFList2.put("File3.xlsx", true);

		webDevFromFileAllFileslist = new HashMap<>();
		webDevFromFileAllFileslist.putAll(siteAdminFileOfficeOnlineON);
		webDevFromFileAllFileslist.putAll(siteAdminFileOfficeOnlineOFFList1);
		webDevFromFileAllFileslist.putAll(siteAdminFileOfficeOnlineOFFList2);

		openINNotAvilabe = new HashSet();
		openINNotAvilabe.add("File9.pptx");
		openINNotAvilabe.add("File7.ods");
		openINNotAvilabe.add("File1.xlsx");

		files = getFileListFromZip(zipFile);
		webDevFromFileTypeCommon = getExtsFromFileList(files);

		allOfficeNonSupFiles = getFileListFromZip(allNonSupFiles);
		nonSupportedFileTypes = getExtsFromFileList(allOfficeNonSupFiles);

		unzip(zipFile);

	}

	String getFileRelavantOpenInSubMenuOpt(String fileName)
	{
		return fileOpts.get(getExtFromFileNmae(fileName));
	}

	Map<String, Boolean> getExtsFromFileList(String fileName[])
	{
		Map<String, Boolean> sample = new HashMap();
		for (int i = 0; i < fileName.length; i++)
		{
			sample.put(getExtFromFileNmae(fileName[i]), true);
		}

		return sample;
	}

	String getExtFromFileNmae(String fileName)
	{
		return fileName.substring(fileName.indexOf('.') + 1, fileName.length());
	}

	void SetSiteAdminOpenOfficeOnline(Boolean val, Map<String, Boolean> fileslist) throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteNameMatrix);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.enableOpenOfficeOnline(val);
		adminFilesWeb.saveFilesChanges();

		systemPreconfiguration(fileslist);

		logout();
	}

	void verifyFileOpenInSubmenu(String[] fileslist)
	{
		for (String file : fileslist)
		{
			verifyFileOpenInSubmenuOptions(file, true, true);
		}
	}

	void verifyFileOpenInSubmenuOptions(String fileName, boolean hasOfflineEditOpt, boolean hasOnlineOpt)
	{

		String fileEXT = getExtFromFileNmae(fileName);
		String smlOpt = getFileRelavantOpenInSubMenuOpt(fileEXT);

		System.out.println("Verifying file : " + fileName + " option " + smlOpt);

		documentWeb.gotoMoreActions(fileName, openInOpt, "hover");

		Assert.assertEquals(documentWeb.verifySubmenuOption(smlOpt), hasOfflineEditOpt);

		Assert.assertEquals(documentWeb.verifySubmenuOption(smlOpt + " Online"), hasOnlineOpt);

	}

	void markAsFvaorits(String[] fileslist)
	{
		for (int j = 0; j < fileslist.length; j++)
		{
			documentWeb.addFileOrFolderToFavouritesMyFiles(fileslist[j]);
		}
	}

	DocumentWeb loginAndGotoFileModule(String userName, String password, String siteName)
	{
		bannerPageWeb = login(userName, password);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		return dashboardWeb.gotoFileModule();
	}

	private void createFileMetaIsheetIfNotCreated()
	{
		String isheetColumnName = "File Categoty";
		String isheetTitle = "Test Isheet";

		AdminIsheetPage adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (!adminIsheetWeb.verifyIsheetTitleHasFileMetaEnabled(isheetTitle))
		{
			adminIsheetWeb.clickOnAddIsheet();
			AdminAddIsheetPage createSheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions("iSheet");
			createSheetWeb.addIsheetTitle(isheetTitle);
			createSheetWeb.addIsheetSelectCheckBoxOption("File metadata template", true);
			adminIsheetWeb = createSheetWeb.addIsheetClickSave();
		}
		if (!adminIsheetWeb.verifyIsheetTitleHasFileMetaEnabled(isheetTitle))
		{
			assertFalse(true);
		}
		AdminIsheetManageColumnPage isheetColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, "Manage columns");
		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			AdminIsheetAddColumnPage addColumnsWeb = isheetColumnWeb.manageColumnsClickAddColumns();
			addColumnsWeb.addColumnName(isheetColumnName);
			addColumnsWeb.clickSaveOnAddColumn();
		}
		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			assertFalse(true);
		}

	}

	void changeSiteStatus(String siteNmae, String status)
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteNmae);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		AdminGeneralPage adminGeneralweb = adminPageWeb.clickGeneralInLeftPanel();
		adminGeneralweb.selectStatus(status);
		if (status.equals("Read only"))
			adminGeneralweb.clickonOkonDocRevokePopUp();
		adminGeneralweb.clickOnSave();
		logout();
	}

	void markFavfilesForAllusersAndAddBlog()
	{
		for (int i = 2; i < userEmails.length; i++)
		{
			documentWeb = loginAndGotoFileModule(userEmails[i], newPassword, siteNameTab);

			for (int j = 0; j < files.length; j++)
			{
				findVisibleElement(fileBanner);
				documentWeb.addFileOrFolderToFavourites(files[j]);
			}
			if (userEmails[i].equals(normalUserEmail))
			{
				gotoBlogModuleAndaddBlog();
			}
			logout();
		}
	}

	void giveFolderAdminPerUser(AdminPage adminPageWeb, String userEmail, String siteNameTab)
	{
		AddUserPage addUserWeb = adminPageWeb.clickUsersInLeftPanel();

		addUserWeb.openUserPermissionModel(userEmail);

		addUserWeb.setFolderAdminPermission(siteNameTab, true);

		addUserWeb.clickSaveInSetPermissions();
	}

	void gotoBlogModuleAndaddBlog()
	{
		blogWeb = gotoBlogModule();

		if (!blogWeb.verifyBlogInBlogList("sample") || !verifyBlogAllAttchments())
		{
			if (blogWeb.verifyBlogInBlogList("sample"))
			{
				blogWeb.clickOnOptionInMoreActionInDetailsSection("Delete");
				blogWeb.deleteBlog();
			}

			blogWeb.clickOnAddPlusSign();
			blogWeb.sendTextInBlogTitle("sample");
			blogWeb.selectCategory("Default");
			blogWeb.addBlogContent("This is a blog");
			blogWeb.clickOnTabsInBlogModule(BlogLabels.BLOG_ATTACHMENTS);
			AddBlogAllAttchments();
			blogWeb.clickOnSaveOnAddBlog();

		}

	}

	boolean verifyBlogAllAttchments()
	{
		for (int j = 0; j < files.length; j++)
		{
			if (!blogWeb.VerifyAttachedFileInDetailsSection(files[j]))
				return false;
		}
		return true;

	}

	void AddBlogAllAttchments()
	{
		for (int j = 0; j < files.length; j++)
		{
			blogWeb.attachFileInAddBlobAttachmentTab(files[j]);
		}
	}

	void addFilesToSite(String zipFileName)
	{
		documentWeb = bannerPageWeb.gotoFileModule();
		findVisibleElement(fileBanner);
		documentWeb.selectItemFromView(FileLabels.FILES_LISTVIEW);
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromAdd("Zipped files");
		documentWeb.addZipFile(zipFileName, null);
		documentWeb.clickAddInModal();
	}

	void setOnlineOfficeSeettingfromFileTypes(Map<String, Boolean> testTypes)
	{
		SystemAdminFileOrFileTypesPage fileTypes = systemAdminWeb.gotoFileOrFileTypes();

		for (Entry<String, Boolean> entry : testTypes.entrySet())
		{
			String fileEXT = getExtFromFileNmae(entry.getKey());
			boolean hasEditingViaWebDAVPer = entry.getValue();

			EditFileIconsPage editfile = fileTypes.gotoDocumentEditPage(fileEXT);
			editfile.enableOnlineOfficeEdit(true);

			editfile.enableOnlineEditingViaWebDAV(hasEditingViaWebDAVPer);

			if (hasEditingViaWebDAVPer && editfile.isDisplayNameVisible())
				editfile.addDisplayName(fileEXT + "File");

			editfile.clickSave();
		}
	}

	public void unzip(String zipFileNmae) throws IOException
	{
		String zipFilePath = TestBaseSetup.currentDir + "\\testData\\" + zipFileNmae + "";
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null)
		{
			String filePath = TestBaseSetup.currentDir + "\\testData\\" + entry.getName() + "";
			extractFile(zipIn, filePath);
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	/**
	 * Extracts a zip entry (file entry)
	 * 
	 * @param zipIn
	 * @param filePath
	 * @throws IOException
	 */
	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException
	{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[1024];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1)
		{
			bos.write(bytesIn, 0, read);
		}
		bos.close();
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
