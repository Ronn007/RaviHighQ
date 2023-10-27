package com.highq.test.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.FileLabels;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author paras.vankadi
 */
public class File_TC2820 extends BannerPageWeb
{

	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "auto.officeonline@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String normalUserEmail = "normal.onlineOffice@Highq.com";// "admin.user@highq.com";
	String normalUserPassword = "Password@123";
	String siteUserEmail = "site.onlineOffice@highq.com";// "admin.user@highq.com";
	String SiteUserPassword = "Password@123";

	String siteName = "File_TC2820";
	String[] userNames = {"tom.chick", "site.onlineOffice", "contain.onlineOffice"};
	String[] userEmail = {"tom.chick@Highq.com", "site.onlineOffice@highq.com", "contain.onlineOffice@highq.com"};

	String jsonFileName = "Files/preConfiguration_File_TC2754.json";
	final String optionValueON = "ON";
	final String optionValueOFF = "OFF";
	String[] addItem = {"Files", "Zipped files", "Placeholder file", "New folder"};
	String folderDescription = "Folder for File_TC2820";

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
	String[] documentOption = {"Word Online", "Excel Online", "PowerPoint Online"};
	String[] documentName = {"Word", "Excel", "PowerPoint"};
	String moreOptionItem = "Open in...";
	String[] testFileTypes = {"docx", "xlsx", "pptx"};
	Map<String, String> zipFileMap = new HashMap<>();
	final String TAGS = "tags";
	final String DISCLAIMER = "disclaimer";
	String newTag = "tag2";
	String newDisclaimer = "disclaimer2";
	String zipFile = "onlineSupportedFiles.zip";

	String specialCharacters = ",<,>,|";
	String validationMsg = "Please remove the following characters as they are not supported by office online: \" < > |";
	String wordDocument = "Word Document";
	String folderName = "Folder 1";
	String createFolder = "Folder";
	String[] option = {"Word Document", "Excel Spreadsheet", "PowerPoint Presentation"};
	Map<String, String> editDetails;
	final String TITLE = "title";
	final String DISCLAIMERTEXT = "disclaimer text";
	String[] view = {FileLabels.FILES_LISTVIEW, FileLabels.FILES_COLUMNVIEW, FileLabels.FILES_THUMBNAILVIEW};
	String[] filename = {"File5.docx", "File1.xlsx", "File8.pptx"};
	String[] filenameSpecialCharacters = {specialCharacters + ".docx", specialCharacters + ".xlsx", specialCharacters + ".pptx"};
	String[] leftPanelOption = {siteName, FileLabels.FILES_LEFTPANEL_FAVOURITES, FileLabels.FILES_LEFTPANEL_RECENT};
	String File5 = "File5";

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void TC2820() throws InterruptedException, IOException
	{

		 scenario1();
		scenario2();

	}

	private void scenario1() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		gotoFileModuleAndAddZippFile(zipFile);

		for (int i = 0; i < option.length; i++)
		{
			documentWeb.selectLeftPanelFileOptions(siteName);
			verifySpecialCharacters(FileLabels.FILES_LISTVIEW, option[i]);

		}
		for (int i = 0; i < option.length; i++)
		{
			verifySpecialCharacters(FileLabels.FILES_COLUMNVIEW, option[i]);

		}
		for (int i = 0; i < option.length; i++)
		{
			verifySpecialCharacters(FileLabels.FILES_THUMBNAILVIEW, option[i]);

		}

		logout();

	}

	private void scenario2() throws InterruptedException, IOException
	{

		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();

		dashboardWeb.searchSite(siteName);
		documentWeb = bannerPageWeb.gotoFileModule();

		editDetails = new HashMap<>();
		editDetails.clear();
		editDetails.put(TITLE, specialCharacters);

		for (int i = 0; i < filename.length; i++)
		{
			documentWeb.selectLeftPanelFileOptions(siteName);
			documentWeb.addFileOrFolderToFavourites(filename[i]);
			documentWeb.editFileDetails(filename[i], editDetails);
			documentWeb.clickSaveInModal();
		}
		verifySpecialCharactersforPreview(FileLabels.FILES_LISTVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_COLUMNVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_THUMBNAILVIEW);
		documentWeb.selectLeftPanelFileOptions(leftPanelOption[1]);

		verifySpecialCharactersforPreview(FileLabels.FILES_LISTVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_COLUMNVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_THUMBNAILVIEW);
		documentWeb.selectLeftPanelFileOptions(leftPanelOption[2]);

		verifySpecialCharactersforPreview(FileLabels.FILES_LISTVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_COLUMNVIEW);
		verifySpecialCharactersforPreview(FileLabels.FILES_THUMBNAILVIEW);

		for (int j = 0; j < filename.length; j++)
		{
			documentWeb.previewFile(filenameSpecialCharacters[j]);
			documentWeb.clickMoreActionsForFilePreviewSubOption(filenameSpecialCharacters[j], moreOptionItem, documentOption[j]);
			Assert.assertTrue(documentWeb.verifySpecialCharactersForFileSharIconOnlinOffice(validationMsg));
			documentWeb.closeForOnlineDocumentCreateModal();
			documentWeb.closePreviewPage();
		}
		logout();

	}

	void verifySpecialCharacters(String view, String documentType)
	{
		documentWeb.selectItemFromView(view);
		documentWeb.selectItemFromNew(documentType);
		documentWeb.addNewWordDocumentAtSpecificLocation(specialCharacters, folderName);
		documentWeb.clickNewWordDocumentSave();
		Assert.assertTrue(documentWeb.verifySpecialCharactersForOnlinOffice(validationMsg));
		documentWeb.closeForOnlineDocumentCreateModal();
	}

	void gotoFileModuleAndAddZippFile(String zipFileName)
	{
		documentWeb = bannerPageWeb.gotoFileModule();
		documentWeb.deleteAllDocs();
		documentWeb.selectItemFromUpload("Zipped files");

		zipFileMap.clear();
		zipFileMap.put(TAGS, newTag);
		documentWeb.addZipFile(zipFileName, zipFileMap);
		documentWeb.clickAddInModal();

		documentWeb.selectItemFromNew(createFolder);
		documentWeb.createNewFolderInRoot(folderName, folderDescription);
		documentWeb.clickAddInModal();
	}

	void verifySpecialCharactersforPreview(String view)
	{
		documentWeb.selectItemFromView(view);
		for (int j = 0; j < filename.length; j++)
		{

			documentWeb.clickOnSubmenuOptionOfFileMoreActions(filenameSpecialCharacters[j], moreOptionItem, documentOption[j]);
			Assert.assertTrue(documentWeb.verifySpecialCharactersForFileSharIconOnlinOffice(validationMsg));
			documentWeb.closeForOnlineDocumentCreateModal();

		}
	}

}
