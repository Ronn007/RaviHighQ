package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.DocFolderOperation;
import com.highq.labels.collaborate.TaskLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.TaskAddDataPage;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAddIsheetPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminIsheetAddColumnPage;
import com.highq.pageobjects.base.AdminIsheetManageColumnPage;
import com.highq.pageobjects.base.AdminIsheetPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0204 extends BannerPageWeb
{
	/** Add_Edit_Delete_Download File */
	private WebDriver driver;
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";

	String orgType = "Internal";

	String siteName = "File_TC0204";
	String[] userNames = {"usernormalforsmokefileupdated", "usersiteadminforsmokefileupdated"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String folderPermissionName = "Admin";
	String isheetTitle = "Test Isheet";
	String fileName = "testDoc.txt";
	String[] multiFiles = {"doc1.txt", "doc2.txt"};
	String fileTag = "TC0204";
	String fileDisclaimer = "test file for TC0204";
	String[] addItem = {"File", "Zipped files", "Placeholder file", "Folder"};
	String moreActionOption = "Edit details";
	Map<String, String> editDetails;
	String newTitle = "testDoc2";
	String newTag = "tag2";
	String newDisclaimer = "disclaimer2";
	String zipFile = "Documents.zip";
	String fileExtension = ".txt";

	String placeHolderFileName = "placeholderfile";
	String placeHolderTags = "tag1,tag2";
	String placeHolderVersionNote = "version1";
	String placeHolderDisclaimerText = "placeholder Disclaimer";
	String fileForTask = "doc3.txt";
	String isheetColumnName = "File Name";
	String isheetColumnData = "Scenario5";
	String newIsheetColumnData = "Scenario5 Edited metadata";
	String previewTitleToVerify = "";
	String leftPanelOption = "Deleted items";
	String folderName = "FileTC204";
	String folderDescription = "Folder for File_TC0204";

	String newVersionTitle = "testDoc2";
	String newVersionFile = "testDoc.txt";
	String newVersionNote = "New Version Added";

	String taskTitle = "Task For File_TC0204";
	String taskDueDate = "25 Dec 2019";
	String taskAssignee = userNames[0] + "@" + domain;
	String taskList = "None";
	String taskPriority = "Normal";
	String taskStatus = "Not started";
	String optionToCheck = "Version number";
	String namingConvention = "Version";
	String taskAssigneeUsername = userNames[0];

	String zipFolder = "ZipFolder";
	String zipDesclaimer = "Desclaimer for zip folder";

	final String TITLE = "title";
	final String TAGS = "tags";
	final String DISCLAIMERTEXT = "disclaimer text";
	final String DISCLAIMER = "disclaimer";
	final String NAME = "name";
	final String VERSIONNOTES = "version notes";
	String jsonFileName = "Files/preConfiguration_File_TCR0204.json";

	String userForLogin;

	AdminIsheetPage adminIsheetWeb;
	AdminAddIsheetPage createSheetWeb;
	AdminIsheetManageColumnPage isheetColumnWeb;
	AdminIsheetAddColumnPage addColumnsWeb;
	AdminSecurityPage adminSecurityWeb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	DocumentAddDataPage addDoc;
	TasksPage tasksWeb;
	AdminFilesPage adminFilesWeb;
	BannerPage bannerPageWeb;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0204() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();
		scenario9();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1: Add single file */
		precondition();
		/** Login with normal user */
		userForLogin = userNames[0] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Add file with tag and disclaimer */
		documentWeb.selectItemFromAdd(addItem[0]);
		uploadFileWithTagAndDisclaimer(fileName, fileTag, fileDisclaimer);
		documentWeb.clickAddInAddFileModal();

		/** Verify file is uploaded successfully. */
		documentWeb.searchFile(fileName);
		if (!documentWeb.verifyDocumentUploaded(fileName))
			assertFalse(true);
		/** Verify file title,tag and disclaimer */
		if (!verifyPreview(fileName, fileTag, fileDisclaimer, "", ""))
			assertFalse(true);

		editDetails = new HashMap<>();
		editDetails.put(TITLE, newTitle);
		editDetails.put(TAGS, newTag);
		editDetails.put(DISCLAIMERTEXT, newDisclaimer);
		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.clickSaveInModal();
		previewTitleToVerify = newTitle + fileExtension;
		/** Verify edited file title,tag and disclaimer */
		if (!verifyPreview(previewTitleToVerify, newTag, newDisclaimer, "", ""))
			assertFalse(true);
	}

	private void scenario2() throws IOException, InterruptedException
	{
		/** Scenario 2: Add multiple files with tag and disclaimer */
		boolean scenarioPassed = false;
		documentWeb.selectItemFromAdd(addItem[0]);
		for (int i = 0; i < multiFiles.length; i++)
			uploadFileWithTagAndDisclaimer(multiFiles[i], fileTag, fileDisclaimer);

		documentWeb.clickAddInModal();
		/** Verify uploaded files */
		for (int i = 0; i < multiFiles.length; i++)
		{
			documentWeb.gotoMoreActions(multiFiles[i], moreActionOption);
			if (verifyEditFileDetails(multiFiles[i], fileTag, fileDisclaimer))
				scenarioPassed = true;
			else
				scenarioPassed = false;
			documentWeb.clickCancel();
		}

		if (!scenarioPassed)
			assertFalse(true);

	}

	private void scenario3() throws IOException, InterruptedException
	{
		documentWeb.selectItemFromAdd(addItem[3]);
		documentWeb.createNewFolderInRoot(zipFolder, zipDesclaimer);
		documentWeb.clickAddInModal();
		documentWeb.searchFolder(zipFolder);
		if (!documentWeb.verifySearchedFolder(zipFolder))
			assertFalse(true);
		documentWeb.selectLeftPanelFileOptions(zipFolder);
		documentWeb.clearLeftPanelSearchItem();
		/** Scenario 3: Add Zipped file */
		documentWeb.selectItemFromAdd(addItem[1]);
		Map<String, String> zipFileMap = new HashMap<>();
		zipFileMap.put(TAGS, newTag);
		zipFileMap.put(DISCLAIMER, newDisclaimer);
		documentWeb.addZipFile(zipFile, zipFileMap);
		documentWeb.clickAddInModal();

		/** Verify zip is uploaded successfully. */
		if (!documentWeb.verifyUploadedZipFiles(zipFile))
			assertFalse(true);

		documentWeb.selectLeftPanelFileOptions(siteName);
	}

	private void scenario4() throws IOException, InterruptedException
	{
		/** Scenario 4: Add Placeholder file */
		documentWeb.selectItemFromAdd(addItem[2]);
		Map<String, String> placeHolderMap = new HashMap<>();
		placeHolderMap.put(NAME, placeHolderFileName);
		placeHolderMap.put(TAGS, placeHolderTags);
		placeHolderMap.put(VERSIONNOTES, placeHolderVersionNote);
		placeHolderMap.put(DISCLAIMER, placeHolderDisclaimerText);
		documentWeb.addPlaceHolderFileDetails(placeHolderMap);
		documentWeb.clickAddInModal();

		/** Verify placeholder file is created successfully. */
		documentWeb.searchFile(placeHolderFileName);
		if (!documentWeb.verifyPlaceHolderFile(placeHolderFileName))
			assertFalse(true);

	}

	private void scenario5() throws IOException, InterruptedException
	{
		/** Scenario 5: Add single file with metadata */

		documentWeb.selectItemFromAdd(addItem[0]);
		uploadFileWithTagAndDisclaimer(fileName, fileTag, fileDisclaimer);
		documentWeb.addMetadata(isheetColumnName, isheetColumnData);
		documentWeb.clickAddInAddFileModal();

		/** Verify file is uploaded successfully. */
		documentWeb.searchFile(fileName);
		if (!documentWeb.verifyDocumentUploaded(fileName))
			assertFalse(true);

		/** Verify file details and metadata */
		if (!verifyPreview(fileName, fileTag, fileDisclaimer, isheetColumnName, isheetColumnData))
			assertFalse(true);
		/** Edit file details and metadata */
		editDetails = new HashMap<>();
		newTitle = "Scenario5";
		editDetails.put(TITLE, newTitle);
		editDetails.put(TAGS, newTag);
		editDetails.put(DISCLAIMERTEXT, newDisclaimer);
		documentWeb.editFileDetails(fileName, editDetails);
		documentWeb.editFileMetadata(fileName, isheetColumnName, newIsheetColumnData);
		documentWeb.clickSaveInModal();
		/** Verify Edited file details */
		previewTitleToVerify = newTitle + fileExtension;
		if (!verifyPreview(previewTitleToVerify, newTag, newDisclaimer, isheetColumnName, newIsheetColumnData))
			assertFalse(true);

	}

	private void scenario6() throws IOException, InterruptedException
	{
		documentWeb.selectItemFromAdd(addItem[0]);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileForTask);

		documentWeb.addFile(addDoc, null);
		documentWeb.gotoTasksTab();
		tasksWeb = documentWeb.clickAddButtonInTasksTab();

		TaskAddDataPage addtask = new TaskAddDataPage();
		addtask.clean();
		addtask.setTaskTitle(taskTitle);
		addtask.setTaskList(taskList);
		addtask.setTaskPriority(taskPriority);
		addtask.setTaskStatus(taskStatus);
		addtask.setAssignee(taskAssignee);
		addtask.setAttachment(fileName);
		addtask.setTaskDueDate(taskDueDate);

		tasksWeb.addTask(addtask);
		if (!tasksWeb.verifyAttachementInAddTask(fileName))
			assertFalse(true);

		if (!tasksWeb.verifyAddAnotherTaskLink())
			assertFalse(true);

		tasksWeb.clickOnAddTaskButtonInModel();
		documentWeb.clickAddInAddFileModal();
		tasksWeb = documentWeb.gotoTaskModule();
		if (tasksWeb.verifyTaskVisibility(taskTitle))
			System.out.println(taskTitle + " created successfully.");
		Map<String, String> taskDetails = new HashMap<>();
		taskDetails.put(TaskLabels.TASKS_ADDTASK_TITLE, taskTitle);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_DUEDATE, taskDueDate);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_LIST, taskList);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_PRIORITY, taskPriority);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_STATUS, taskStatus);
		taskDetails.put(TaskLabels.TASKS_ADDTASK_ASSIGNEE, taskAssigneeUsername);

		tasksWeb.selectTask(taskTitle);
		if (!tasksWeb.verifyTaskData(taskDetails))
			assertFalse(true);

		if (!tasksWeb.verifyAttachmentInViewTask(fileName) && tasksWeb.verifyAttachmentInViewTask(fileForTask))
			assertFalse(true);
		tasksWeb.deleteAllTasks();

		documentWeb = tasksWeb.gotoFileModule();

	}

	private void scenario7() throws IOException, InterruptedException
	{
		documentWeb.searchFile(newTitle);
		String version = documentWeb.getFileVersion();
		documentWeb.gotoMoreActions(newTitle, DocFolderOperation.Add_version.toString().replace("_", " "));
		documentWeb.uploadDoc_onAddNewVersionWin(newVersionFile, newVersionTitle, newVersionNote);
		documentWeb.clickAddInModal();
		documentWeb.searchFile(newVersionTitle);
		if (!documentWeb.verifyVersionCountIncremented(version))
			assertFalse(true);
	}

	private void scenario8() throws IOException, InterruptedException
	{
		documentWeb.cleanDownloadsFolder();
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.downloadFile(multiFiles[0]);
		/** Verify file in Downloads folder */
		assertTrue(documentWeb.verifyDownloadedFile(multiFiles[0]));

	}

	private void scenario9() throws InterruptedException, IOException
	{
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.selectItemFromAdd(addItem[3]);
		documentWeb.createNewFolderInRoot(folderName, folderDescription);
		documentWeb.clickAddInModal();
		documentWeb.searchFolder(folderName);
		if (!documentWeb.verifySearchedFolder(folderName))
			assertFalse(true);
		documentWeb.clearLeftPanelSearchItem();
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteFile(fileForTask);
		if (documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
		/** Login with Site Admin */
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();

		/** Verify file in deleted items */
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		if (!documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);
		/** Restore and verify file is removed from deleted items */
		documentWeb.restoreFile(fileForTask);
		if (documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);

		documentWeb.selectLeftPanelFileOptions(siteName);
		if (!documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);

		/** Delete the file and move the same file to a folder from deleted items */
		documentWeb.deleteFile(fileForTask);
		if (documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);
		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		documentWeb.gotoMoreActions(fileForTask, DocFolderOperation.Move.toString());
		documentWeb.moveFile("", siteName, folderName);
		documentWeb.searchFolder(folderName);

		/** Verify file is successfully moved to folder */
		documentWeb.selectLeftPanelFileOptions(folderName);
		documentWeb.clearLeftPanelSearchItem();
		if (!documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);

		documentWeb.deleteFile(fileForTask);
		if (documentWeb.verifyDocumentUploaded(fileForTask))
			assertFalse(true);
		/** Remove all deleted items */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteAllDocs();
		if (!documentWeb.verifyNoFilesOrFolderPresent())
			assertFalse(true);

		documentWeb.selectLeftPanelFileOptions(leftPanelOption);
		documentWeb.deleteAllDocs();

		documentWeb.selectLeftPanelFileOptions(siteName);
		if (documentWeb.verifyLeftPanelItem(leftPanelOption))
			assertFalse(true);
		dashboardWeb = documentWeb.gotoDashboard();
		logout();
	}

	private boolean verifyPreview(String fileNameToVerify, String fileTagToVerify, String desclaimerToVerify, String metadataColumn, String metadataToVerify) throws IOException, InterruptedException
	{
		boolean verified = true;
		documentWeb.previewFile(fileNameToVerify);
		if (!documentWeb.verifyAndAcceptDisclaimer(desclaimerToVerify))
			assertFalse(true);
		if (!documentWeb.verifyPreviewFileTitle(fileNameToVerify))
			verified = false;
		if (!documentWeb.verifyPreviewTag(fileTagToVerify))
			verified = false;
		if (!metadataColumn.trim().isEmpty() && metadataColumn.trim() != null)
		{
			if (!documentWeb.verifyPreviewMetaData(metadataColumn, metadataToVerify))
				verified = false;
		}
		documentWeb.closePreviewPage();
		return verified;
	}

	private void uploadFileWithTagAndDisclaimer(String fileName, String fileTag, String disclaimer) throws IOException, InterruptedException
	{
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		addDoc.setTag(fileTag);
		addDoc.setFiledisclaimer(disclaimer);
		documentWeb.addFile(addDoc, null);
	}

	private boolean verifyEditFileDetails(String title, String tag, String disclaimer)
	{
		if (documentWeb.verifyEditFileTitle(title))
		{
			if (documentWeb.verifyTag(tag))
				return documentWeb.verifyDisclaimerText(disclaimer);
		}
		return false;
	}

	private void precondition() throws InterruptedException
	{
		/** Site and user setup */
		preconfiguration();
		/** Set folder permission for normal user */
		setFolderAdminPermission();
		/** Create file metadata isheet if it is not created */
		createFileMetaIsheetIfNotCreated();
	}

	private void setFolderAdminPermission() throws InterruptedException
	{
		userForLogin = userNames[1] + "@" + domain;
		bannerPageWeb = login(userForLogin, newPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0] + "@" + domain);
		addUserWeb.setFolderAdminPermission(siteName, true);
		addUserWeb.clickSaveInSetPermissions();
		adminFilesWeb = adminPageWeb.clickOnFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(optionToCheck, true);
		// adminFilesWeb.selectNamingConvention(namingConvention);
		adminFilesWeb.saveFilesChanges();
	}

	private void createFileMetaIsheetIfNotCreated()
	{
		adminIsheetWeb = adminPageWeb.clickiSheetsInLeftPanel();
		if (!adminIsheetWeb.verifyIsheetTitleHasFileMetaEnabled(isheetTitle))
		{
			adminIsheetWeb.clickOnAddIsheet();
			createSheetWeb = (AdminAddIsheetPage) adminIsheetWeb.selectAddIsheetOptions("iSheet");
			createSheetWeb.addIsheetTitle(isheetTitle);
			createSheetWeb.addIsheetSelectCheckBoxOption("File metadata template", true);
			adminIsheetWeb = createSheetWeb.addIsheetClickSave();
		}
		if (!adminIsheetWeb.verifyIsheetTitleHasFileMetaEnabled(isheetTitle))
		{
			assertFalse(true);
		}
		isheetColumnWeb = (AdminIsheetManageColumnPage) adminIsheetWeb.selectOptionOnIsheetMoreAction(isheetTitle, "Manage columns");
		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			addColumnsWeb = isheetColumnWeb.manageColumnsClickAddColumns();
			addColumnsWeb.addColumnName(isheetColumnName);
			addColumnsWeb.clickSaveOnAddColumn();
		}
		if (!isheetColumnWeb.verifyColumnExist(isheetColumnName))
		{
			assertFalse(true);
		}
		logout();
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

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
		adminPageWeb = gotoAdminModule();
		adminSecurityWeb = adminPageWeb.clickSecurityInLeftPanel();
		adminSecurityWeb.enableAdvancedSiteAdminOption(true);
		adminSecurityWeb.saveAdvancedChanges();
		logout();
	}
}
