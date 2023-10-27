/**
 *
 */
package com.highq.test.wiki;

import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AdminTaskPage;
import com.highq.pageobjects.base.AdminUserGroupsPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.pageobjects.pages.DashboardWeb;

/**
 * @author vivek.mishra
 */
public class Wiki_TC0165 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	AdminTaskPage adminTaskPage;
	AddUserPage addUserWeb;
	AdminUserGroupsPage adminUserGroupsWeb;
	AdminAdvancedPage adminAdvancedWeb;
	ConfigurationData configurationData = new ConfigurationData();

	String delete = "Delete";

	String[] userNames = {"normal.user0165", "admin.user0165"};
	String nPassword = "Password@123";
	String siteName = "Wiki_TC0165T_1";
	String domain = "wikitc0165.com";

	String[] wikiTitle = {"Wiki 1 " + getRandomString(), "home level " + getRandomString(), "siblingTitle", "childTitle", "Wiki 2 " + getRandomString(), "Wiki 3 " + getRandomString()};
	String ckContent = "Ck content data";
	String tag = "demoTag";
	String file = "doc1.txt";
	String newCkContent = "Ck content data new";
	String newTag = "demoTag new";
	String newFile = "doc2.txt";
	String autoSaved = "Auto-saved";
	String autoSaveDraftMessage = "The system automatically saves a draft as you are editing. Auto-saves can be accessed by clicking your name in the top right and selecting \"My drafts\". Do you want to keep your auto-saved draft?";
	String resumeEditingMessage = "You were previously editing a draft, do you want to resume editing or discard?";
	LoginPage loginPageWeb;
	WikiPage wikiWeb;
	DashboardWeb dashboardWeb;
	BannerPage bannerPageWeb;
	String jsonFileName = "preConfiguration_Wiki_TC0165.json";

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test
	public void wikiTC0165() throws InterruptedException, IOException, JSONException
	{
		precondition();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		siteCleanup();
	}

	/**
	 * @author vivek.mishra
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void scenario1() throws IOException, InterruptedException
	{
		bannerPageWeb = login(userNames[0] + "@" + domain, nPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();
		wikiWeb.clickOnAddWiki();

		wikiWeb.setTitle(wikiTitle[0]);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(ckContent);
		wikiWeb.enterTag(tag);
		wikiWeb.attachFileInAddWiki(file);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[0]);

		Assert.assertTrue(wikiWeb.verifyWikiTitle(wikiTitle[0]));
		Assert.assertTrue(wikiWeb.verifyCkContentData(ckContent));
		Assert.assertTrue(wikiWeb.verifyTagsAdded(tag));
		Assert.assertTrue(wikiWeb.verifyAttachedFile(file));
	}

	/**
	 * @author vivek.mishraS
	 */
	public void scenario2()
	{
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");

		wikiWeb.setTitle(wikiTitle[1]);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(newCkContent);
		wikiWeb.editTagInEditWikiPage(tag, newTag);
		wikiWeb.editAttachmentInEditWiki(file, newFile);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[1]);

		Assert.assertTrue(wikiWeb.verifyWikiTitle(wikiTitle[1]));
		Assert.assertTrue(wikiWeb.verifyCkContentData(newCkContent));
		Assert.assertTrue(wikiWeb.verifyTagsAdded(newTag));
		Assert.assertTrue(wikiWeb.verifyAttachedFile(newFile));
	}

	/**
	 * @author vivek.mishra
	 */
	public void scenario3()
	{
		wikiWeb.verifyWikiTitle(wikiTitle[1]);

		wikiWeb.gotoWikiModule();
		Assert.assertFalse(wikiWeb.verifyOptionInMoreActionInSelectedWiki(delete));

		wikiWeb.selectWikiFromLeftPanel(wikiTitle[1]);
		wikiWeb.verifyWikiTitle(wikiTitle[1]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki(delete);
		Assert.assertTrue(wikiWeb.verifyDeleteWikiModal());
		wikiWeb.clickCancelOnDeleteWikiModal();
		Assert.assertFalse(wikiWeb.verifyDeleteWikiModal());

		wikiWeb.selectOptionInMoreActionInSelectedWiki(delete);
		Assert.assertTrue(wikiWeb.verifyDeleteWikiModal());
		wikiWeb.clickOkOnDeleteWikiModal();
		Assert.assertFalse(wikiWeb.verifyWikiFromLeftPanel(wikiTitle[1]));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario4() throws InterruptedException
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiTitle[4]);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(ckContent);
		wikiWeb.saveWiki();

		wikiWeb.selectOptionInMoreActionInSelectedWiki("Add top-level page");
		wikiWeb.setTitle(wikiTitle[5]);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[5]);

		wikiWeb.selectWikiFromLeftPanel(wikiTitle[4]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Add sibling page");
		wikiWeb.setTitle(wikiTitle[2]);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[2]);

		wikiWeb.selectWikiFromLeftPanel(wikiTitle[4]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Add child page");
		wikiWeb.setTitle(wikiTitle[3]);
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[3]);

		Assert.assertTrue(wikiWeb.verifyWikiHomeLevelWiki(wikiTitle[5]));
		Assert.assertTrue(wikiWeb.verifySiblingLevelWiki(wikiTitle[4], wikiTitle[2]));
		Assert.assertTrue(wikiWeb.verifyChildLevelWiki(wikiTitle[4], wikiTitle[3]));
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario5() throws InterruptedException
	{
		wikiWeb.selectWikiFromLeftPanel(wikiTitle[4]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		String tempContent = ckContent + getRandomString();
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(tempContent);
		wikiWeb.waitForAutoSavedNotification();
		wikiWeb.cancelWiki();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.verifyWikiTitle(wikiTitle[4]);

		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		Assert.assertTrue(wikiWeb.verifyResumeDiscardYellowStrip());
		wikiWeb.clickOnResumeYellowStrip();
		Assert.assertTrue(wikiWeb.getTextFromCKContentTextBox().trim().equals(tempContent));
		wikiWeb.cancelWiki();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.verifyWikiTitle(wikiTitle[4]);

		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		Assert.assertTrue(wikiWeb.verifyResumeDiscardYellowStrip());
		Assert.assertTrue(wikiWeb.verifyDiscardTagInResumeEditingMessage());
		Assert.assertTrue(wikiWeb.verifyResumeTagInResumeEditingMessage());
		Assert.assertTrue(wikiWeb.verifyResumeEditingMessage(resumeEditingMessage));

		wikiWeb.clickOnDiscardYellowStrip();
		Assert.assertTrue(wikiWeb.getTextFromCKContentTextBox().equals(ckContent.trim()));
		wikiWeb.saveWiki();
		wikiWeb.verifyWikiTitle(wikiTitle[4]);

		wikiWeb.gotoWikiModule();
		if (wikiWeb.verifyMyDraftsLinkIsVisbile())
		{
			wikiWeb.clickOnMyDraftsInLeftMenu();
			if (wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle[4]))
				Assert.assertTrue(wikiWeb.verifyWikiStatusInMyDrafts(wikiTitle[4], autoSaved));
		}
	}

	/**
	 * @author vivek.mishra
	 * @throws InterruptedException
	 */
	public void scenario6() throws InterruptedException
	{
		wikiWeb.selectWikiFromLeftPanel(wikiTitle[4]);
		wikiWeb.selectOptionInMoreActionInSelectedWiki("Edit");
		String tempContent = ckContent + getRandomString();
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(tempContent);
		wikiWeb.waitForAutoSavedNotification();
		wikiWeb.cancelWiki();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.verifyWikiTitle(wikiTitle[4]);

		wikiWeb.clickOnEditTitleButton();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.saveAutoSaveDraftDialogue();
		Assert.assertTrue(wikiWeb.getTextFromCKContentTextBox().equals(tempContent));
		wikiWeb.cancelWiki();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.verifyWikiTitle(wikiTitle[4]);

		wikiWeb.clickOnEditTitleButton();
		wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage);
		wikiWeb.discardAutoSaveDraftDialogue();
		Assert.assertTrue(wikiWeb.activeWikiTitle());
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}

	/**
	 * @author vivek.mishra
	 */
	public void siteCleanup()
	{
		wikiWeb.gotoWikiModule();
		for (int i = wikiTitle.length - 1; i > 1; i--)
		{
			wikiWeb.deleteWiki(wikiTitle[i]);
		}
	}

}
