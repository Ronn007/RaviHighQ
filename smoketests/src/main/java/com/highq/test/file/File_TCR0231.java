package com.highq.test.file;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.base.CollaborateLabel.DocFolderOperation;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminSecurityPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.pages.AdminTaskPageWeb;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.TasksWeb;
import com.highq.parsers.JSONParser;

/**
 * @author dheeraj.rajput
 */
public class File_TCR0231 extends BannerPageWeb
{
	/** Due Deligence */
	private WebDriver driver;
	String jsonFileName = "Files/preConfiguration_File_TCR0231.json";
	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser
					.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();// "ravi.middha@highq.com";//
																						// "admin.user@highq.com";
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();// "Password@123";
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 1").get("newPassword").asText();
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 2").get("newPassword").asText();
	String contentAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData")
			.get("Organization 1").get("user 3").get("newPassword").asText();
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String orgType = "Internal";

	String[] userNames = {"usernormal1@l5.com", "usersiteadmin@l5.com", "usercontentadmin@l5.com"};
	String domain = "l5.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String folderPermissionName = "Admin";
	String[] addMenuItems = {"Files", "Folder"};

	String[] files = {"doc1.txt", "doc2.txt", "doc3.txt", "images.png"};

	String enableDocumentReview = "TRUE";
	String siteAdmin_EnableDocReviewOption = "Enable document review workflow";
	String metaDataToDisplay = "Status";
	String statusName = "User Status";
	String leftPanelItem = "Index";
	String folderName = "Folder231";
	String actionsItemAllocateForReview = "Allocate for review";
	String actionsItemMarkAsNA = "Mark as N/A";
	String enableIndexPage = "Enable Index page";
	String versionNumber = "Version number";
	String statusUnallocated = "Unallocated";
	String taskPreText = "Review ";
	String statusNotStarted = "Not started";
	String statusNotApplicable = "Not applicable";

	String fileName1 = files[0].substring(0, files[0].indexOf("."));
	String fileName2 = files[1].substring(0, files[1].indexOf("."));
	String fileName3 = files[2].substring(0, files[2].indexOf("."));
	String[] filesForFolder = {"profilepicture.jpg"};
	String newVersionTitle = "testDoc";
	String newVersionFile = "testDoc.txt";
	String newVersionNote = "New Version Added";
	String checkingAllocationMessage = "Files that have reviews already allocated have been detected. You can not allocate a review to these files. Would you like to continue without these files?";
	String checkingFileIsNAForReviewMsg = "This file(s) has already been allocated and will be deleted from the assigned reviewers tasks. Do you want to continue?";
	String leftPanelOption = "Deleted items";
	String checkingFileAsNATitle = "Checking files is not applicable for review";
	String markingFileAsNATitle = "Marking files as not applicable for review";
	String userForLogin;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	DashboardPage dashboardWeb;
	AddUserPage addUserWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	AdminFilesPage adminFilesWeb;
	AdminSecurityPage adminSecurityWeb;
	BannerPage bannerPageWeb;
	AdminTaskPageWeb adminTaskWeb;
	TasksPage tasksWeb;
	BaseFileTest baseFileTest = new BaseFileTest(driver);

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void FileTCR0231() throws InterruptedException, IOException
	{
		scenario1();
		scenario2();
	}

	private void scenario1() throws InterruptedException, IOException
	{
		/** Scenario 1 : Allocate Review to File */
		precondition();

		/** Login with normal user and verify Actions button is not visible inside Index page */
		userForLogin = userNames[0];
		bannerPageWeb = login(userForLogin, defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		assertTrue(!documentWeb.verifyActionsButtonIsVisible());
		documentWeb.gotoDashboard();
		logout();

		/** Login with content admin and verify Actions button is visible and Allocate for review option is available */
		userForLogin = userNames[2];
		bannerPageWeb = login(userForLogin, defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		assertTrue(documentWeb.verifyActionsButtonIsVisible());
		documentWeb.selectFileFromIndex(files[0]);
		documentWeb.clickActionsButton();
		assertTrue(documentWeb.verifyItemOfActionsButton(actionsItemAllocateForReview));
		documentWeb.gotoDashboard();
		logout();

		/** Login with site admin user */
		userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		documentWeb = dashboardWeb.gotoFileModule();
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		tasksWeb = new TasksWeb(driver);
		/** Allocate single file for review */
		if (documentWeb.verifyFileStatusInIndex(files[0], statusUnallocated))
		{
			documentWeb.selectFileFromIndex(files[0]);
			documentWeb.selectItemFromActions(actionsItemAllocateForReview);
			assertTrue(!tasksWeb.verifyAttachmentTabVisibilityInAddTask());
			tasksWeb.setTaskAssignees(userNames[0]);
			tasksWeb.clickOnAddTaskButtonInModel();
		}
		tasksWeb.gotoTaskModule();
		/** Verify allocated task in Tasks Module */
		verifyTaskAssigneeAndAttachment(taskPreText + fileName1, userNames[0], files[0]);

		documentWeb = tasksWeb.gotoFileModule();
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		/** Verify File status of unallocated file changes to not started */
		assertTrue(documentWeb.verifyFileStatusInIndex(files[0], statusNotStarted));

		/** Allocate multiple files for review */
		if (documentWeb.verifyFileStatusInIndex(files[1], statusUnallocated) && documentWeb.verifyFileStatusInIndex(files[2], statusUnallocated))
		{
			documentWeb.selectFileFromIndex(files[1]);
			documentWeb.selectFileFromIndex(files[2]);
			documentWeb.selectItemFromActions(actionsItemAllocateForReview);
			assertTrue(!tasksWeb.verifyAttachmentTabVisibilityInAddTask());
			tasksWeb.setTaskAssignees(userNames[0]);
			tasksWeb.clickOnAddTaskButtonInModel();
		}
		tasksWeb.gotoTaskModule();
		/** Verify separate task is created for multiple files in Tasks Module */
		verifyTaskAssigneeAndAttachment(taskPreText + fileName2, userNames[0], files[1]);
		verifyTaskAssigneeAndAttachment(taskPreText + fileName3, userNames[0], files[2]);
		documentWeb = tasksWeb.gotoFileModule();
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);

		/** Verify File status of unallocated file changes to not started */
		assertTrue(documentWeb.verifyFileStatusInIndex(files[1], statusNotStarted));
		assertTrue(documentWeb.verifyFileStatusInIndex(files[2], statusNotStarted));

		/** Allocate folder for Review */
		documentWeb.selectFileFromIndex(folderName);
		documentWeb.selectItemFromActions(actionsItemAllocateForReview);
		assertTrue(!tasksWeb.verifyAttachmentTabVisibilityInAddTask());
		tasksWeb.setTaskAssignees(userNames[0]);
		tasksWeb.clickOnAddTaskButtonInModel();

		tasksWeb.gotoTaskModule();
		/** Verify separate task is created for all the files of folder in Tasks Module */
		for (int i = 0; i < filesForFolder.length; i++)
		{
			verifyTaskAssigneeAndAttachment(taskPreText + filesForFolder[i].substring(0, filesForFolder[i].indexOf(".")), userNames[0], filesForFolder[i]);
		}
		documentWeb = tasksWeb.gotoFileModule();

		/** Add new version fo file and verify task and attachment changes accordingly */
		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.searchFile(files[0]);
		String version = documentWeb.getFileVersion();
		documentWeb.gotoMoreActions(files[0], DocFolderOperation.Add_version.toString().replace("_", " "));
		documentWeb.uploadDoc_onAddNewVersionWin(newVersionFile, newVersionTitle, newVersionNote);
		documentWeb.clickAddInModal();
		documentWeb.searchFile(newVersionTitle);
		assertTrue(documentWeb.verifyVersionCountIncremented(version));

		tasksWeb = documentWeb.gotoTaskModule();
		verifyTaskAssigneeAndAttachment(taskPreText + newVersionTitle, userNames[0], newVersionFile);

		documentWeb = tasksWeb.gotoFileModule();

		/** Allocate aready allocated file for review and verify message */
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		documentWeb.selectFileFromIndex(files[1]);
		documentWeb.selectItemFromActions(actionsItemAllocateForReview);
		assertTrue(documentWeb.verifyCheckingAllocationMessage(checkingAllocationMessage));
		documentWeb.clickCloseInCheckingAllocationModal();
		assertTrue(documentWeb.verifyFileStatusInIndex(files[1], statusNotStarted));
	}

	private void scenario2() throws InterruptedException
	{
		/** Scenario 2 : Mark as N/A Button */

		/** Select unallocated file and click on Mark as N/A button from action button. */
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		assertTrue(documentWeb.verifyFileStatusInIndex(files[3], statusUnallocated));
		documentWeb.selectFileFromIndex(files[3]);
		documentWeb.selectItemFromActions(actionsItemMarkAsNA);
		/** Verify "Checking files as not applicable for review" and "Marking file as not applicable for review" message */
		assertTrue(documentWeb.verifyCheckingFilesAsNAForReviewTitleIsVisible(checkingFileAsNATitle));
		// assertTrue(documentWeb.verifyMarkingFilesAsNAForReviewTitleIsVisible(markingFileAsNATitle));
		/** Verify file status changes to Not Applicable */
		assertTrue(documentWeb.verifyFileStatusInIndex(files[3], statusNotApplicable));

		/** Select an allocated file and click on Mark as N/A button and click close */
		documentWeb.selectLeftPanelFileOptions(leftPanelItem);
		documentWeb.selectFileFromIndex(files[1]);
		documentWeb.selectItemFromActions(actionsItemMarkAsNA);
		/** Verify "This file(s) has already been allocated and will be deleted from the assigned reviewers tasks. Do you want to continue?" message */
		assertTrue(documentWeb.verifyCheckingAllocationMessage(checkingFileIsNAForReviewMsg));
		documentWeb.clickCloseInCheckingAllocationModal();
		assertTrue(documentWeb.verifyFileStatusInIndex(files[1], statusNotStarted));

		/** Select an allocated file and click on Mark as N/A button and click continue */
		documentWeb.selectItemFromActions(actionsItemMarkAsNA);
		assertTrue(documentWeb.verifyCheckingAllocationMessage(checkingFileIsNAForReviewMsg));
		documentWeb.clickContinueInCheckingAllocationModal();

		/** Verify file status changes to Not applicable */
		assertTrue(documentWeb.verifyFileStatusInIndex(files[1], statusNotApplicable));
		tasksWeb = documentWeb.gotoTaskModule();
		/** Verify task for that file is no longer available */
		assertTrue(!tasksWeb.verifyTaskVisibility(taskPreText + fileName2));

		documentWeb = tasksWeb.gotoFileModule();

		documentWeb.selectLeftPanelFileOptions(siteName);
		documentWeb.deleteAllDocs();
		if (!documentWeb.verifyNoFilesOrFolderPresent())
			assertFalse(true);

		dashboardWeb = documentWeb.gotoDashboard();
		logout();

	}

	private void verifyTaskAssigneeAndAttachment(String taskName, String taskAssignee, String attachmentToVerify) throws InterruptedException
	{
		tasksWeb.selectTask(taskName);
		assertTrue(tasksWeb.verifyTaskAssignee(taskAssignee));
		assertTrue(tasksWeb.verifyAttachmentInViewTask(attachmentToVerify));
	}

	private void precondition() throws InterruptedException, IOException
	{
		/**
		 * Create site and add normal user, site admin, Set Enable Document Review =
		 * true from asp admin.
		 */
		preconfiguration();
		/**
		 * Set folder admin permission for normal user,Set Enable document review = true
		 * from site admin > Files. Set Show status = true from site admin >> Files.
		 * There should be some custom status added from site admin >> Task
		 */
		siteAdminConfiguration();

	}

	private void siteAdminConfiguration() throws InterruptedException, IOException
	{

		userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, defaultPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		addUserWeb = adminPageWeb.clickUsersInLeftPanel();
		addUserWeb.openUserPermissionModel(userNames[0]);
		/** Set Folder Admin Permission */
		addUserWeb.setFolderAdminPermission(siteName, true);
		addUserWeb.clickSaveInSetPermissions();
		/**
		 * Set Enable document review = true from site admin > Files. Set Show status =
		 * true from site admin >> Files, Enable Index Page and Show Version Number
		 */
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();
		adminFilesWeb.selectFileOptionCheckbox(siteAdmin_EnableDocReviewOption, true);
		adminFilesWeb.selectFileOptionCheckbox(metaDataToDisplay, true);
		adminFilesWeb.selectFileOptionCheckbox(enableIndexPage, true);
		adminFilesWeb.selectFileOptionCheckbox(versionNumber, true);
		adminFilesWeb.saveFilesChanges();

		/** Add custom status from site admin >> Task */
		adminPageWeb = adminFilesWeb.gotoAdminModule();
		adminTaskWeb = adminPageWeb.clickTasksInLeftPanel();
		if (!adminTaskWeb.verifyCustomStatus(statusName))
			adminTaskWeb.addStatus(statusName, 1);

		/** Add few files if not available */
		documentWeb = adminTaskWeb.gotoFileModule();
		baseFileTest.documentWeb = documentWeb;
		for (int i = 0; i < files.length; i++)
		{
			searchAndAddFile(files[i]);
		}
		documentWeb.searchFolder(folderName);
		if (!documentWeb.verifySearchedFolder(folderName))
		{
			documentWeb.clearLeftPanelSearchItem();
			documentWeb.selectItemFromAdd(addMenuItems[1]);
			documentWeb.createNewFolderInRoot(folderName, "");
			documentWeb.clickAddInModal();
			documentWeb.searchFolder(folderName);
			assertTrue(documentWeb.verifySearchedFolder(folderName));
			documentWeb.selectLeftPanelFileOptions(folderName);
			baseFileTest.documentWeb = documentWeb;
			for (int j = 0; j < filesForFolder.length; j++)
			{
				searchAndAddFile(filesForFolder[j]);
			}
		}
		documentWeb.clearLeftPanelSearchItem();
		documentWeb.selectLeftPanelFileOptions(siteName);
		logout();
	}

	private void searchAndAddFile(String fileName) throws IOException, InterruptedException
	{
		documentWeb.searchFile(fileName);
		if (!documentWeb.verifyDocumentUploaded(fileName))
		{
			baseFileTest.addFile(fileName);
			/** Verify file is present */
			documentWeb.searchFile(fileName);
			assertTrue(documentWeb.verifyDocumentUploaded(fileName));
		}
	}

	private void preconfiguration() throws InterruptedException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		/** Enable Document Review */
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableDocumentReview(enableDocumentReview);
		aspConfigurationWeb.saveConfigurations();
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
