package com.highq.test.mysite;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.base.CollaborateLabel.UserStatus;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
import com.highq.pagedata.TaskAddDataPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author vivek mishra
 * @cretaed on 14/05/2018
 */
public class MySite_TC0191 extends BannerPageWeb
{
	DashboardPage dashboardPage;
	LoginPage loginPage;
	BannerPage bannerPage;
	DocumentAddDataPage addDocPage;
	TaskAddDataPage addTaskDataPage;
	TasksPage tasksPage;
	MyFilesPage myFilePage;

	SystemAdminPage systemAdminPage;
	SystemAdminSystemSettingsPage systemSettingsPage;
	AspAdminPage aspAdminPage;
	AspConfigurationPage aspConfigurationPage;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword ="Password@123";
	String[] userNames = {"normal.siteuser", "site.admin"};
	String domain = "tcr0191.com";
	String newPassword = "Password@123";
	String orgType = "Internal";
	String myfileEnable="ON";
	String enableMyFileSharing="ON for all system users";
	String addFile="Files";

	String operation="Add version";
	String editDetails = "Edit details";
	String newFolder = "Folder";
	String placeHolderFile = "Placeholder file";
	String placeHolderFileName = "placeholderfile";
	String placeHolderDisclaimerText = "placeholder Disclaimer";
	String tempFile;

	String disclaimerModal = "Disclaimer";
	String disclaimerText = "New Disclaimer";
	String cancel = "Cancel";
	String accept = "Accept";
	String add = "Add";
	String myFiles = "My files";
	String zippedFiles = "Zipped files";

	String taskName="Task_TCR0191 Title";
	String taskDescription = "Dteailed description";
	String taskDate="20 Apr 2019";
	String taskpriority="Normal";
	String taskStatus="Not started";
	String taskTag = "tskTag";

	String fileName = "doc1.txt";
	String zipFile = "fileFolderZipFile.zip";
	String tagName = "tag 1";
	String versionNote = "version detailed notes";

	String editedFileName = "doc2.txt";
	String editedTitle = "doc1 edited";
	String editedTag = "tag 2";
	String editedDisclaimer = "Disclaimer edited";
	String editedVersionNote = "version detailed notes edited";

	String folderName = " Folder 1";
	String folderDescription = "Folder discription note";

	String folderNameEdited = " Folder 1 Edited";
	String folderDescriptionEdited = "Folder discription note Edited";

	String editedTitle2 = "doc1 edited second time";
	String editedTag2 = "tag 3";
	String editedDisclaimer2 = "Disclaimer edited second time";



	ConfigurationData configurationData = new ConfigurationData();

	@Test
	public void MyFileTCR0191() throws InterruptedException, IOException
	{
		preconfiguration();
		scenario1MyFilePreview();
		scenario2AddversionFile();
		scenario3AddZippedFile();
		scenario4AddPlaceHolders();
		scenario5Addfolder();
		scenario6EditFile();
		scenario7EditFolder();
	}
	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws InterruptedException
	 * @created on 14/05/2018 
	 */
	private void scenario1MyFilePreview() throws IOException, InterruptedException 
	{
		bannerPage = login(userNames[0]+"@"+domain, newPassword);
		dashboardPage = bannerPage.gotoDashboard();
		myFilePage=dashboardPage.goToMyFiles();
		addFileWithTask();	
		Assert.assertTrue(myFilePage.verifyDocumentUploaded(fileName));

		tasksPage=myFilePage.goToMyTasks();	
		tasksPage.selectTask(taskName);
		Assert.assertTrue(tasksPage.verifyAttachmentInViewTask(fileName));

		tasksPage.clickOnTaskAttachment();
		tasksPage.verifyModal();
		Assert.assertTrue(tasksPage.verifyModal(disclaimerModal));
		Assert.assertTrue(tasksPage.verifyModalMessage(disclaimerText));
		Assert.assertTrue(tasksPage.verifyModalButton(cancel));
		Assert.assertTrue(tasksPage.verifyModalButton(accept));

		tasksPage.clickOnModalButton(cancel);
		Assert.assertFalse(tasksPage.verifyModal(disclaimerModal));
		Assert.assertFalse(tasksPage.verifyFilePreviewPage());

		tasksPage.clickOnTaskAttachment();
		tasksPage.verifyModal();
		tasksPage.clickOnModalButton(accept);
		Assert.assertTrue(verifyPreviewDocument(fileName,tagName,versionNote,taskName));
		myFilePage.closePreviewPage();	
		tasksPage.clickOnCloseInViewTask();
	}
	/**
	 * @author vivek.mishra
	 * @created on 15/05/2018
	 */
	private void scenario2AddversionFile()
	{
		myFilePage=tasksPage.goToMyFiles();
		myFilePage.gotoMoreActions(fileName, operation);
		myFilePage.verifyModal();
		myFilePage.attachFileInAddNewVersionModal(editedFileName);
		myFilePage.attachFileInMarkUpCopy(fileName);
		myFilePage.sendTextInTitleTextBoxInAddNewVersionModal(editedTitle);
		myFilePage.sendTextInVersionNotesTextBox(editedVersionNote);
		myFilePage.editTagInAddNewVersionModal(tagName, editedTag);
		myFilePage.sendTextInDisclaimerTextBox(editedDisclaimer);
		myFilePage.clickOnModalButton(add);

		tempFile = editedTitle+".txt";
		Assert.assertTrue(myFilePage.verifyDocumentUploaded(tempFile));
		myFilePage.previewFile(tempFile);
		myFilePage.verifyModal();
		Assert.assertTrue(tasksPage.verifyModalMessage(editedDisclaimer));

		myFilePage.clickOnModalButton(accept);
		Assert.assertTrue(verifyPreviewDocument(tempFile,editedTag,editedVersionNote,taskName));
		Assert.assertTrue(myFilePage.verifyMarkedUpCopyUploaded());
		myFilePage.closePreviewPage();
	}
	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @created on 16/05/2018
	 * 
	 */
	private void scenario3AddZippedFile() throws IOException
	{
		myFilePage.selectItemFromUpload(zippedFiles);
		Map<String, String> zipFileMap = new HashMap<>();
		zipFileMap.put("TAGS", tagName);
		zipFileMap.put("DISCLAIMER", disclaimerText);
		myFilePage.addZipFile(zipFile, zipFileMap);
		myFilePage.clickAddInModal();
		myFilePage.goToMyFiles();
		myFilePage.verifyDocumentUploaded(tempFile);

		Assert.assertTrue(myFilePage.verifyUploadedZipFiles(zipFile));
	}
	/**
	 * @author vivek.mishra
	 * @created on 16/05/2018
	 */
	private void scenario4AddPlaceHolders()
	{
		myFilePage.selectItemFromNew(placeHolderFile);
		Map<String, String> placeHolderMap = new HashMap<>();
		placeHolderMap.put("NAME", placeHolderFileName);
		placeHolderMap.put("TAGS", tagName);
		placeHolderMap.put("DISCLAIMER", placeHolderDisclaimerText);
		myFilePage.addPlaceHolderFileDetails(placeHolderMap);
		myFilePage.clickAddInModal();

		/** Verify placeholder file is created successfully. */

		Assert.assertTrue(myFilePage.verifyPlaceHolderFile(placeHolderFileName));
		String tempFile2 = placeHolderFileName+".placeholder";
		myFilePage.previewFile(tempFile2);
		myFilePage.verifyModal();
		Assert.assertTrue(tasksPage.verifyModalMessage(placeHolderDisclaimerText));
		myFilePage.clickOnModalButton(accept);
		Assert.assertTrue(myFilePage.verifyPreviewFileTitle(tempFile2));
		Assert.assertTrue(myFilePage.verifyPreviewTag(tagName));
		myFilePage.closePreviewPage();
	}
	/**
	 * @author vivek.mishra
	 * @creted on 16/05/2018
	 */
	private void scenario5Addfolder()
	{
		myFilePage.selectItemFromNew(newFolder);
		myFilePage.createNewFolderInRoot(folderName, folderDescription);
		myFilePage.clickAddInModal();
		Assert.assertTrue(myFilePage.verifyFolderTitle(folderName));
		Assert.assertTrue(myFilePage.verifyFolderDescription(folderDescription));
		myFilePage.selectLeftPanelFileOptions(myFiles);
		Assert.assertTrue(myFilePage.verifyDocumentUploaded(folderName));
	}
	/**
	 * @author vivek.mishra
	 * @creted on 17/05/2018
	 */
	private void scenario6EditFile()
	{	
		Map<String, String> editDetailsMap = new HashMap<>();
		editDetailsMap.put("TITLE", editedTitle2);
		editDetailsMap.put("TAGS", editedTag2);
		editDetailsMap.put("DISCLAIMER TEXT", editedDisclaimer2);
		myFilePage.editFileDetails(tempFile, editDetailsMap);
		myFilePage.clickSaveInModal();
		myFilePage.selectLeftPanelFileOptions(myFiles);
		tempFile = editedTitle2+".txt";
		Assert.assertTrue(myFilePage.verifyDocumentUploaded(tempFile));
		myFilePage.previewFile(tempFile);
		myFilePage.verifyModal();
		Assert.assertTrue(tasksPage.verifyModalMessage(editedDisclaimer2));
		myFilePage.clickOnModalButton(accept);
		Assert.assertTrue(myFilePage.verifyPreviewFileTitle(tempFile));
		Assert.assertTrue(myFilePage.verifyPreviewTag(editedTag2));
		myFilePage.closePreviewPage();
	}
	/**
	 * @author vivek.mishra
	 * @creted on 17/05/2018
	 */
	private void scenario7EditFolder()
	{
		myFilePage.gotoMoreActions(folderName, editDetails);
		myFilePage.editFolderDetails("NAME", folderNameEdited);
		myFilePage.editFolderDetails("DESCRIPTION", folderDescriptionEdited);
		myFilePage.clickSaveInModal();

		/** Verify folder title and description updated */
		Assert.assertTrue(myFilePage.verifyFolderTitle(folderNameEdited));
		Assert.assertTrue(myFilePage.verifyFolderDescription(folderDescriptionEdited));
		myFilePage.selectLeftPanelFileOptions(myFiles);
		Assert.assertTrue(myFilePage.verifyDocumentUploaded(folderNameEdited));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @created on 14/05/2018
	 */
	private void preconfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPage = login(sysAdminEmail, sysAdminPassword);

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

		String[] configurationList = {"setOrg","addSystemAdminUsers"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList,configurationData));

		aspAdminPage = bannerPage.gotoASPAdmin();
		aspConfigurationPage = aspAdminPage.openConfigurationPage();
		aspConfigurationPage.enableMyFiles(myfileEnable);
		aspConfigurationPage.enableMyFilesSharing(enableMyFileSharing);
		aspConfigurationPage.saveConfigurations();

		systemAdminPage =  aspConfigurationPage.gotoSystemAdmin();
		systemSettingsPage = systemAdminPage.gotoSystemSettings();
		systemSettingsPage.enableMyFiles(myfileEnable);
		systemSettingsPage.enableMyFilesSharing(enableMyFileSharing);
		systemSettingsPage.saveSettings();	

		logout();	
	}
	/**
	 * @author vivek.mishra
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @creted on 14/05/2018
	 */
	private void addFileWithTask() throws IOException, InterruptedException
	{
		cleanUpMyFile();		
		if(!myFilePage.verifyDocumentUploaded(fileName))
		{
			myFilePage.selectItemFromUpload(addFile);
			addDocPage = new DocumentAddDataPage();
			addDocPage.clean();
			addDocPage.setFileuploadpath(fileName);
			addDocPage.setTag(tagName);
			addDocPage.setVersionNotes(versionNote);
			addDocPage.setFiledisclaimer(disclaimerText);

			addTaskDataPage=new TaskAddDataPage();
			addTaskDataPage.clean();
			addTaskDataPage.setTaskTitle(taskName);
			addTaskDataPage.setTaskDescription(taskDescription);
			addTaskDataPage.setTaskDueDate(taskDate);
			addTaskDataPage.setTaskPriority(taskpriority);
			addTaskDataPage.setTaskStatus(taskStatus);
			addTaskDataPage.setTaskTag(taskTag);	
			myFilePage.addFile(addDocPage, addTaskDataPage);
			myFilePage.clickAddInAddFileModal();
			myFilePage.verifyDocumentUploaded(fileName);
		}
	}
	/**
	 * @author vivek.mishra
	 * @param fileNameToVerify
	 * @param fileTagToVerify
	 * @param versionNote
	 * @param metadataTaskName
	 * @return
	 * @creted on 14/05/2018
	 */
	private boolean verifyPreviewDocument(String fileNameToVerify,String fileTagToVerify,String versionNote,String metadataTaskName) 
	{ 
		if(!myFilePage.verifyPreviewFileTitle(fileNameToVerify))
			return false;
		if(!myFilePage.verifyPreviewTag(fileTagToVerify))
			return false;
		if(!myFilePage.verifyVersionNote(versionNote))
			return false;
		if(!myFilePage.verifyMetaDataTaskWithDocument(metadataTaskName))
			return false;

		return true;
	}
	/**
	 * @author vivek.mishra
	 * @created on 14/05/2018
	 */
	private void cleanUpMyFile()
	{
		tasksPage=myFilePage.goToMyTasks();
		tasksPage.deleteAllTasks();
		myFilePage=tasksPage.goToMyFiles();
		myFilePage.selectLeftPanelFileOptions(myFiles);
		myFilePage.deleteAllDocs();
	}
}
