package com.highq.test.dashboard;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
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
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.MyFilesPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.pages.BannerPageWeb;


/**
 * @author khushbu.dhandhukiya
 *
 */
public class Dashboard_TCR0245 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword ="Password@123";
	String newPassword = "Password@123";
	String[] userNames = {"normaluser","normal.user1"};
	String optionToSelect="ON";
	String optionValue="ON for all system users";
	String orgType = "Internal";
	String userName = "normal.user1";
	String domain = "file.com";
	String[] files = {"doc1.txt","doc2.txt","doc3.txt"};
	String addMenuItem = "Files";	
	String operation="Share";
	String message="This is Test message";
	String subject="User Send a File";
	String option="Received items";
	String accessLevel="Anyone with the link";
	String linkeExpires="Never";
	String[] recipientUsers= {"normaluser@file.com","normal.user1@file.com"};
	
	
	BannerPage bannerPageWeb;
	DocumentPage documentWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	DocumentAddDataPage addDoc;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	AdminPage adminPageWeb;
	AspAdminPage aspAdminWeb;
	MyFilesPage myFilesWeb;
	ConfigurationData configurationData = new ConfigurationData();
	/**
	 *
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException 
	 */
	@Test(priority = 1)
	public void dashboardTCR0245() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();
		sitePrecondition();
		scenario1();
		scenario1Case1();
		
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb=bannerPageWeb.gotoDashboard();
		
		assertTrue(dashboardWeb.verifyRecentTabSiteList());
		assertTrue(dashboardWeb.verifyMyFilesTab());
		
		
		/** verify received and shared tab */
		
		assertTrue(dashboardWeb.verifyReceivedFilesTab());
		assertTrue(dashboardWeb.verifySharedFilesTab());
		dashboardWeb.clickOnSharedTab();
		
		assertTrue(dashboardWeb.verifySharedItem(files[1]));
		dashboardWeb.logout();
		
		
		/**Accessing the files from "Received files" tab. */
			
		bannerPageWeb=login(userNames[0]+"@"+domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.gotoMoreActions(files[0],operation);
		myFilesWeb.shareViaEmail(recipientUsers[1],subject, message,accessLevel,linkeExpires);

		myFilesWeb.clickSendInShareModal();
		myFilesWeb.gotoMoreActions(files[0],operation);
		myFilesWeb.shareViaEmail(recipientUsers[1],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickSendInShareModal();

		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		myFilesWeb.gotoMoreActions(files[0],operation);
		myFilesWeb.shareViaEmail(recipientUsers[1],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		myFilesWeb.logout();
		
				
		
		bannerPageWeb=login(userNames[1]+"@"+domain, newPassword);
		dashboardWeb=bannerPageWeb.gotoDashboard();
		dashboardWeb.clickOnReceivedTab();
		assertTrue(dashboardWeb.verifyReceivedItem(files[0]));
		dashboardWeb.clickOnFile(files[0]);
		dashboardWeb.clickOnForwardArrowOfViewer();
		dashboardWeb.clickOnBackwordArrowOfViewer();
		dashboardWeb.clickOnCloseFileViewer();
		dashboardWeb.logout();
		
		
		/** Accessing the files from "Shared files" tab.**/
		
		bannerPageWeb=login(userNames[0]+"@"+domain, newPassword);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.gotoMoreActions(files[0],operation);
		myFilesWeb.shareViaEmail(recipientUsers[1],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickSendInShareModal();
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();

		myFilesWeb.gotoDashboard();
		dashboardWeb.clickOnSharedTab();
		assertTrue(dashboardWeb.verifySharedItem(files[0]));
		dashboardWeb.clickOnSharedTab();
		dashboardWeb.clickOnFile(files[0]);
		dashboardWeb.clickOnDownoloadFileViewer();
		dashboardWeb.clickOnForwardArrowOfViewer();
		dashboardWeb.clickOnBackwordArrowOfViewer();
		dashboardWeb.clickOnCloseFileViewer();
		dashboardWeb.logout();
			
	}
	
	public void scenario1Case1() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.verifySeeAllFilesLink();
		dashboardWeb.verifySendAFileButton();
		
		dashboardWeb.clickOnSeeAllLink();
		assertTrue(myFilesWeb.verifyMyFilePageRender());
		myFilesWeb.gotoDashboard();
		dashboardWeb.goToSendAFile();
		assertTrue(dashboardWeb.verifySendAFile());
		assertTrue(dashboardWeb.verifyFileTabInSendAFile());
		assertTrue(dashboardWeb.verifyEmailTabInSendAFile());
		dashboardWeb.closeSendAFileModal();
		dashboardWeb.logout();
	
	}
	private void sitePrecondition() throws InterruptedException, IOException, JSONException {
		
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		
	
		
		aspAdminWeb=dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb =aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableMyFiles(optionToSelect);
		aspConfigurationWeb.enableMyFilesSharing(optionValue);
		aspConfigurationWeb.saveConfigurations();
		
		
		
		systemAdminWeb=dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb=systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyFiles(optionToSelect);
		systemAdminSystemSettingsWeb.enableMyFilesSharing(optionValue);
		systemAdminSystemSettingsWeb.saveSettings();
		systemAdminSystemSettingsWeb.logout();
		
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		myFilesWeb = dashboardWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();
	
		myFilesWeb.selectItemFromAdd(addMenuItem);
		for(int i=0;i<files.length;i++)
		{
			addDoc = new DocumentAddDataPage();
			addDoc.clean();
			addDoc.setFileuploadpath(files[i]);
			myFilesWeb.addFile(addDoc, null);
		}
		myFilesWeb.clickAddInModal();
		myFilesWeb.gotoMoreActions(files[1],operation);
		myFilesWeb.shareViaEmail(recipientUsers[0],subject, message,accessLevel,linkeExpires);
		myFilesWeb.clickSendInShareModal();
		myFilesWeb.clickCloseOnLinkSuccessfullySentModel();
		myFilesWeb = dashboardWeb.goToMyFiles();
		myFilesWeb.logout();
		
		
	}
	
	@SuppressWarnings("unused")
	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{

		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

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
				
		
		preConfigurationTest.setOrganisation(configurationData.getOrgData());
		preConfigurationTest.createAndResetUsers(configurationData.getUserMap(), configurationData.getNewPassword(), configurationData.getStatus(),configurationData.isStausLocked());
		logout();
	}
	
}

