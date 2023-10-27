package com.highq.test.home;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Timestamp;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.pagedata.ConfigurationData;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminActivityPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author ashlesha.shastri
 * @created 23/04/2018
 * @modified 11/05/2017
 * @modified by surbhi.khetan
 */

public class Home_TCR0257 extends BannerPageWeb
{
	BannerPage bannerPageWeb;
	AdminPage adminPageWeb;
	AdminActivityPage adminactivityPageWeb;
	AddUserPage addUserPageWeb;
	DashboardPage dashboardPageWeb;
	HomePage homePageWeb;
	ActivityPage activityPageWeb;
	ConfigurationData configurationData = new ConfigurationData();

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String siteName = "Home_TCR0257";
	String jsonFileName = "preConfiguration_Home_TCR0257.json";
	String[] userNames = {"normaluser", "adminuser"};
	String domain = "highq.com";
	String dashboardTitle = "Dashboard Title";
	String dashboardTitleEdit = "Dashboard Title Edit";
	String content = "Content Demo";
	String CKEditorPanelTitle = "CK Editor Panel";
	String CKEditorPanelTitleEdit = "CK Editor Panel Edit";
	String contentEdit = "Content Demo Edit";
	String editOption = "Edit";
	String linkURL = "https://collaborate.highq.com";
	String macroName = "Macro Test";
	String editedMacroName = "Edited Macro";
	String addFavourite = "Add to favourites";
	String removeFavourite = "Remove from favourites";
	String favouriteHome = siteName + " - Home";
	String message = "For Testing Home Module " + siteName;
	String subject = "Send Home Module Link Via Email";
	String messageRecipient = userNames[0] + "@" + domain;
	String mailRecipient = userNames[0] + "@" + domain;
	String mailRecipient1 = userNames[1] + "@" + domain;
	String messageRecipient1 = userNames[1] + "@" + domain;
	String contentEditPrint = "Content Demo Edithttps://collaborate.highq.com";
	
	Timestamp startTime;
	Timestamp endTime;
	

	@Test
	@Parameters({"appURL"})
	public void homeTCR0257(String URL) throws IOException, JSONException, InterruptedException, UnsupportedFlavorException
	{
		//preCondition();
		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6(URL);
		scenario8();
	}

	public void preCondition() throws IOException, JSONException, InterruptedException
	{
		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		addUserPageWeb = adminPageWeb.clickUsersInLeftPanel();
		
		/** Folder permission to normal user */
		addUserPageWeb.openUserPermissionModel(userNames[0] + "@" + domain);
		addUserPageWeb.setFolderAdminPermission(siteName, true);
		addUserPageWeb.clickSaveInSetPermissions();

		/** enable microblogging in site */
		adminactivityPageWeb = adminPageWeb.clickActivityInLeftPanel();
		adminactivityPageWeb.enableMicroblogging(true);
		adminactivityPageWeb.clickOnSave();
		adminactivityPageWeb.logout();

	}

	public void scenario1()
	{
		bannerPageWeb = login(userNames[1] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();
		homePageWeb.clickOnEditIcon();
		homePageWeb.enterDashboardTitle(dashboardTitle);
		homePageWeb.clickOnSave();
		
		/**Verify dashboard title  */
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitle));
		homePageWeb.clickOnEditIcon();
		homePageWeb.enterDashboardTitle(dashboardTitleEdit);
		homePageWeb.clickOnSave();
		
		/** Verify edited dashboard title */
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
	}

	public void scenario2()
	{
		homePageWeb.clickOnEditIcon();
		homePageWeb.removeAllSections();
		homePageWeb.clickOnAddSection();
		homePageWeb.clickonSingleAddPanelIcon();
		homePageWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.CONTENT_EDITOR);
		homePageWeb.addCkEditorPanel(CKEditorPanelTitle, true);
		homePageWeb.clickOnAdd();
		homePageWeb.addcontentInCkEditor(CKEditorPanelTitle, content);
		homePageWeb.clickOnSave();
		
		/** Verify added text in CKEditor */ 
		assertTrue(homePageWeb.verifyPanelTitle(CKEditorPanelTitle));
		assertTrue(homePageWeb.verifyContentofCKeditorPanel(CKEditorPanelTitle, content));

		homePageWeb.clickOnEditIcon();
		homePageWeb.selectMoreActionOptionOfPanel(CKEditorPanelTitle, editOption);
		homePageWeb.addCkEditorPanel(CKEditorPanelTitleEdit, true);
		homePageWeb.clickOnSaveOnEditPanelModel();
		homePageWeb.addcontentInCkEditor(CKEditorPanelTitle, contentEdit);
		homePageWeb.clickOnSave();
		
		/** Verify edited text in CKEditor */ 
		assertTrue(homePageWeb.verifyPanelTitle(CKEditorPanelTitleEdit));
		assertTrue(homePageWeb.verifyContentofCKeditorPanel(CKEditorPanelTitleEdit, contentEdit));
	}

	public void scenario3()
	{
		homePageWeb.clickOnEditIcon();
		homePageWeb.addLinkInCkEditor(CKEditorPanelTitleEdit, linkURL);
		homePageWeb.clickOnSave();
		
		/** Verify added link in CKEditor */ 
		assertTrue(homePageWeb.verifyLinkInCKEditor(CKEditorPanelTitleEdit, linkURL));
	}

	public void scenario4()
	{
		homePageWeb.clickOnEditIcon();
		homePageWeb.addMacroInCKEditor(CKEditorPanelTitleEdit, macroName);
		homePageWeb.clickOnSave();
		
		/** Verify Added macro in ck editor */
		assertTrue(homePageWeb.verifyMacroInCKEditor(CKEditorPanelTitleEdit, macroName));
		homePageWeb.clickOnEditIcon();
		homePageWeb.editMacroInCKEditor(CKEditorPanelTitleEdit, editedMacroName);
		homePageWeb.clickOnSave();
		
		/** Verify Edited  macro in ck editor */
		assertTrue(homePageWeb.verifyMacroInCKEditor(CKEditorPanelTitleEdit, editedMacroName));
	}

	public void scenario5()
	{
		homePageWeb.addToFavourites();
		homePageWeb.gotoHomeModule();
		
		/** Verification when an item is added to favorite */
		assertTrue(homePageWeb.verifyFavouriteTooltip(removeFavourite));
		assertTrue(homePageWeb.verifyFavourite());
		assertTrue(homePageWeb.verifyFavouritesElementInMyFavourites(favouriteHome));
		homePageWeb.gotoHomeModule();
		homePageWeb.removeFromFavourites();
		homePageWeb.gotoHomeModule();
		
		/** Verification when an item is removed from favorite */
		assertTrue(homePageWeb.verifyFavouriteTooltip(addFavourite));
		assertFalse(homePageWeb.verifyFavourite());
		Assert.assertFalse(homePageWeb.verifyFavouritesElementInMyFavourites(favouriteHome));
		homePageWeb.clickOnFavourite();
		logout();
	}

	public void scenario6(String URL) throws UnsupportedFlavorException, IOException, InterruptedException
	{
		/** ------------------ Check by System Admin User ----------------------- */
		bannerPageWeb = login(userNames[1] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();

		/** Share Home Module Via Mail */
		startTime = homePageWeb.getStartDateTimeStamp();
		homePageWeb.shareViaEmail(mailRecipient, subject, message);
		homePageWeb.clickOnClose();
		endTime = homePageWeb.getEndDateTimeStamp();
		assertTrue(homePageWeb.verifyMailWithHomeModuleLink(mailRecipient, startTime, endTime, subject, message, URL));
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
		homePageWeb.closeCurrentTab();
		
		/** Share Home Module Via Link  */
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		homePageWeb.copyShareLink(false);
		homePageWeb.clickCancelInShareModal();
		homePageWeb.logout();

		/** Verify link with recipient user */
		bannerPageWeb = login(userNames[0] + "@" + domain, sysAdminPassword);
		bannerPageWeb.openCopiedURL();
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
		logout();

		/** Share Home Module Via Message */ 
		bannerPageWeb = login(userNames[1] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();
		startTime = homePageWeb.getStartDateTimeStamp();
		homePageWeb.shareViaMessage(messageRecipient, message);
		endTime = homePageWeb.getEndDateTimeStamp();
		if (!verifyMail(messageRecipient, startTime, endTime, "", message))
			assertFalse(true);
		logout();

		/** Verify message with recipient user */
		bannerPageWeb = login(messageRecipient, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.clickOnPrivateMessage();
		if (dashboardPageWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			assertTrue(dashboardPageWeb.verifyRecentMessageRecieved(userNames[1], message,true));
			dashboardPageWeb.clickOnReceivedHomeLink();
			dashboardPageWeb.switchWindow();
			assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
			homePageWeb.closeCurrentTab();
			homePageWeb.clickCancelInMessageBox();
		}
		else
			assertFalse(true);
		logout();

		/** Share Home Module via Microblog */ 
		bannerPageWeb = login(userNames[1] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		homePageWeb.gotoMicroblogTab();

		if (!homePageWeb.verifyDefaultMicroblogSite(siteName))
			assertFalse(true);
		homePageWeb.clickPostInShareModal();

		activityPageWeb = homePageWeb.gotoActivityModule();
		/** Verify microblog post in activity tab */
		verifyMicroblogPost();

		activityPageWeb.gotoDashboard();
		
		/** Verify microblog post in dashboard */
		verifyMicroblogPost();
		logout();
		
		
		/** ------------------ Check by Normal User ----------------------- */
		bannerPageWeb = login(userNames[0] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();

		/** Share Home Module Via Mail */
		startTime = homePageWeb.getStartDateTimeStamp();
		homePageWeb.shareViaEmail(mailRecipient1, subject, message);
		homePageWeb.clickOnClose();
		endTime = homePageWeb.getEndDateTimeStamp();
		assertTrue(homePageWeb.verifyMailWithHomeModuleLink(mailRecipient1, startTime, endTime, subject, message, URL));
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
		homePageWeb.closeCurrentTab();
		
		/** Share Home Module Via Link  */
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		homePageWeb.copyShareLink(false);
		homePageWeb.clickCancelInShareModal();
		homePageWeb.logout();

		/** Verify link with recipient user */
		bannerPageWeb = login(userNames[1] + "@" + domain, sysAdminPassword);
		bannerPageWeb.openCopiedURL();
		assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
		logout();

		/** Share Home Module Via Message */ 
		bannerPageWeb = login(userNames[0] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();
		startTime = homePageWeb.getStartDateTimeStamp();
		homePageWeb.shareViaMessage(messageRecipient1, message);
		endTime = homePageWeb.getEndDateTimeStamp();
		if (!verifyMail(messageRecipient1, startTime, endTime, "", message))
			assertFalse(true);
		logout();

		/** Verify message with recipient user */
		bannerPageWeb = login(messageRecipient1, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.clickOnPrivateMessage();
		if (dashboardPageWeb.verifyFirstMessageIsRecentlyRecieved())
		{
			assertTrue(dashboardPageWeb.verifyRecentMessageRecieved(userNames[0], message,true));
			dashboardPageWeb.clickOnReceivedHomeLink();
			dashboardPageWeb.switchWindow();
			assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
			homePageWeb.closeCurrentTab();
			homePageWeb.clickCancelInMessageBox();
		}
		else
			assertFalse(true);
		logout();

		/** Share Home Module via Microblog */ 
		bannerPageWeb = login(userNames[0] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = dashboardPageWeb.gotoHomeModule();
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		homePageWeb.gotoMicroblogTab();

		if (!homePageWeb.verifyDefaultMicroblogSite(siteName))
			assertFalse(true);
		homePageWeb.clickPostInShareModal();

		activityPageWeb = homePageWeb.gotoActivityModule();
		/** Verify microblog post in activity tab */
		verifyMicroblogPost();

		activityPageWeb.gotoDashboard();
		
		/** Verify microblog post in dashboard */
		verifyMicroblogPost();
		logout();

	}

	public void scenario7()
	{
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_EXPORTTOPDF);
		
		/** Reading content from PDF is pending */ 

	}

	public void scenario8()
	{
		for(int i=0; i<userNames.length; i++) {
		bannerPageWeb = login(userNames[i] + "@" + domain, sysAdminPassword);
		dashboardPageWeb = bannerPageWeb.gotoDashboard();
		dashboardPageWeb.searchSite(siteName);
		homePageWeb = bannerPageWeb.gotoHomeModule();		
		homePageWeb.selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_PRINTPREVIEW);
		homePageWeb.switchWindow();
		
		/** Verify contents in print preview  */
		assertTrue(homePageWeb.verifyDashboardTitleinPrintPreview(dashboardTitleEdit));
		assertTrue(homePageWeb.verifyContentofCKeditorPanelInPrintPreview(CKEditorPanelTitleEdit, contentEditPrint));
		assertTrue(homePageWeb.verifyMacroInCKEditorInPrintPreview(CKEditorPanelTitleEdit, editedMacroName));
		homePageWeb.clickOnCloseButtonOfPrintPreview();
		homePageWeb.switchToParentWindow();
		homePageWeb.logout(); 
		}
	}

	public void verifyMicroblogPost()
	{
		/** Verify microblog post  */
		activityPageWeb.gotoPosts();
		if (!activityPageWeb.verifyGrayMetaSiteName(siteName))
			assertFalse(true);
		
		activityPageWeb.openFirstPostLink();
		Assert.assertTrue(homePageWeb.verifyDashboardTitle(dashboardTitleEdit));
		activityPageWeb.closeCurrentWindow();
	}

}
