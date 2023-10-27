package com.highq.test.wiki;

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
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AddUserPage;
import com.highq.pageobjects.base.AdminAdvancedPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.LoginPage;
import com.highq.pageobjects.base.ModulesPage;
import com.highq.pageobjects.base.SearchUserPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.TasksPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author nidhi.shah
 */
public class Wiki_TC0174 extends BannerPageWeb
{
	/** Automation test for Like Wiki scenarios */
	/** Login Credentials */
	String sysAdminEmail = "ravi.middha@highq.com";// "admin.user@highq.com";
	String sysAdminPassword = "Password@123";
	String orgType = "Internal";

	String jsonFileName = "preConfiguration_Wiki_TC0174.json";
	String siteName = "Wiki_TC0174_2";
	String[] userNames = {"normal.user0174@wiki0174.com", "site.admin0174@wiki0174.com"};
	String domain = "wiki174.com";
	String newPassword = "Password@123";
	String userRole = "Site admin";

	String wikiTitle = "Wiki_TC0174";
	String[] wikiVersions = {"Wiki_TC0174_V2" + getRandomString(), "Wiki_TC0174_V3" + getRandomString(), "Wiki_TC0174_V4" + getRandomString()};
	String wikiContent = "Wiki Content for TC0174";

	String tag = "tag1";
	String file = "doc1.txt";
	String hubURL = "highqhubuat.in";
	String infoOption = "Info";
	String editOption = "Edit";
	String wikiCompareErrorMessage = "You can only compare two versions of the page";
	String versionTwo = "v.2";
	String versionThree = "v.3";
	String versionFour = "v.4";

	int totalVersions;
	String wikiLink;
	Map<Boolean, BannerPageWeb> result;

	DocumentPage documentWeb;
	AdminPage adminPageWeb;
	ModulesPage modulesPageWeb;
	TasksPage tasksWeb;
	WikiPage wikiWeb;
	DashboardPage dashboardWeb;
	SystemAdminPage systemAdminWeb;
	SearchUserPage searchUserweb;
	AdminAdvancedPage adminAdvancedWeb;
	AddUserPage addUserWeb;
	LoginPage loginPageWeb;
	ConfigurationData configurationData = new ConfigurationData();
	BannerPage bannerPageWeb;

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
	@Test(priority = 1, invocationCount = 1)
	public void wikiTC0174() throws InterruptedException, IOException, JSONException
	{
		preCondition();
		expectedOverview();
		scenario2();
		scenario3();
	}

	/**
	 * @throws InterruptedException
	 */
	public void expectedOverview() throws InterruptedException
	{
		wikiWeb.searchPages(wikiVersions[wikiVersions.length - 1]);
		wikiWeb.selectSearchedWiki(wikiVersions[wikiVersions.length - 1]);
		wikiWeb.selectMoreOptionsOperationsOfWiki(infoOption);

		// 1. Wiki info page should be displayed with three tabs: Overview, Version history and Attachments.

		Assert.assertTrue(wikiWeb.verifyOverviewTabOnInfo());
		Assert.assertTrue(wikiWeb.verifyVersionsTabOnInfo());
		Assert.assertTrue(wikiWeb.verifyAttachmentsTabOnInfo());

		/** Overview Tab */

		// 1. By default, the original URL should be in the text box.
		wikiWeb.clickOnOverviewTab();
		wikiLink = wikiWeb.getWikiLinkInfo();
		Assert.assertTrue(wikiLink.split("/")[1].equalsIgnoreCase(driver.getCurrentUrl().trim().split("/")[1]));

		// 2. On click of the check box named "Use short URL", a shortened URL should be returned if instance configured in hub otherwise the original URL.
		wikiWeb.selectShortUrlCheckBox();
		Assert.assertTrue(wikiLink.split("/")[1].equalsIgnoreCase(driver.getCurrentUrl().trim().split("/")[1]) || wikiWeb.getWikiLinkInfo().contains(hubURL));
		totalVersions = wikiWeb.getOptionValueFromInfoModal("Versions");

		// 3. On focus of that text box, the URL should be selected.
		// Assert.assertTrue(wikiWeb.getSelectedWikiLink().equalsIgnoreCase(wikiLink));

		// 4. Click on tag, user should be redirected to search content page showing respective results.
		/** Pending */
		wikiWeb.clickTagInInfoModal(tag);
		Assert.assertTrue(wikiWeb.verifyTagSearchDisplay());
		driver.navigate().back();
		wikiWeb.selectMoreOptionsOperationsOfWiki(infoOption);

		// 5. On click of close, the modal should be closed.
		wikiWeb.clickCancelOnInfoModal();
		Assert.assertTrue(wikiWeb.verifyInfoModalClosed());

		// 6. On click of Export as PDF, wiki should be exported according to proper selection in Export to PDF modal.
		wikiWeb.selectMoreOptionsOperationsOfWiki(infoOption);
		wikiWeb.clickOnOverviewTab();
		wikiWeb.clickExportOnInfoModal();
		wikiWeb.selectThisPageOnly();
		wikiWeb.selectIncludeIndexOption(true);
		wikiWeb.cleanDownloadsFolder();
		wikiWeb.clickOnExportOrPrint();
		assertTrue(wikiWeb.verifyDownloadedFile(wikiVersions[wikiVersions.length - 1] + ".pdf"));

		// 7. On click of Print, wiki should be printed according to proper selection in Print options modal.
		wikiWeb.clickPrintOnInfoModel();
		wikiWeb.selectThisPageOnly();
		wikiWeb.selectIncludeIndexOption(true);
		wikiWeb.clickOnExportOrPrint();
		wikiWeb.switchToPrintWindow();
		Assert.assertTrue(wikiWeb.verifyWikiTitleInPrintWindow(wikiVersions[wikiVersions.length - 1]));
		wikiWeb.closePrintWindow();
	}

	/**
	 *
	 */
	public void scenario2()
	{
		/** Version History */

		// 1. The latest version should be shown as (Current) */
		wikiWeb.clickOnVersionsTab();
		Assert.assertTrue(Integer.parseInt(wikiWeb.getCurrentVersion()) == totalVersions);

		// 2. On click of restore, the modal should be closed, and wiki should be restored as a new version from that version.
		wikiWeb.restoreWikiVersion(versionTwo);
		Assert.assertTrue(wikiWeb.verifyInfoModalClosed());
		Assert.assertTrue(wikiWeb.verifyWikiTitle(wikiVersions[0]));

		// When the info modal will be opened next time, a newly created version should be shown.
		wikiWeb.selectMoreOptionsOperationsOfWiki(infoOption);
		totalVersions = wikiWeb.getOptionValueFromInfoModal("Versions");
		wikiWeb.clickOnVersionsTab();
		Assert.assertTrue(Integer.parseInt(wikiWeb.getCurrentVersion()) == totalVersions);

		// 3. On click of compare button, user should be redirected to compare wiki page, if 2 wiki versions are selected.
		wikiWeb.clickCompareOnInfoModal();
		Assert.assertTrue(wikiWeb.getCustomModalMessage().trim().equalsIgnoreCase(wikiCompareErrorMessage));
		wikiWeb.clickOKOnCustomMessageModal();
		wikiWeb.compareWikiVersions(versionThree, versionFour);
		Assert.assertTrue(wikiWeb.verifyCompareWikiDialogOpened());

		// 4. On click of close, the modal should be closed.
		wikiWeb.clickCloseOnCompareDialog();
		Assert.assertTrue(wikiWeb.verifyCompareWikiDialogClosed());
	}

	/**
	 * @throws InterruptedException
	 */
	public void scenario3() throws InterruptedException
	{
		/** Attachment Tab */
		wikiWeb.clickOnAttachmentsTab();
		wikiWeb.openAttachmentFromWikiInfo(file);

		// 1. Clicking on the file name will open the file in viewer.
		Assert.assertTrue(wikiWeb.verifyFileInFilePreviewWindow(file));
		wikiWeb.clickCancelOnFileOPreviewWindow();

		// 2. On click of close, the modal will be closed.
		Assert.assertTrue(wikiWeb.verifyFilePreviewWindowClosed());
	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws JSONException
	 */
	public void preCondition() throws InterruptedException, IOException, JSONException
	{
		// siteAndUserConfiguration();
		precondition();
		String userForLogin = userNames[1];
		bannerPageWeb = login(userForLogin, newPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();
		createWiki(wikiTitle);
		for (int i = 0; i < wikiVersions.length; i++)
		{
			wikiWeb.selectMoreOptionsOperationsOfWiki(editOption);
			wikiWeb.setTitle(wikiVersions[i]);
			wikiWeb.saveWiki();
		}
	}

	/**
	 * @param wikiName
	 */
	public void selectWiki(String wikiName)
	{
		wikiWeb.selectWikiFromLeftPanel(wikiName);
		wikiWeb.clearSearchItem();
	}

	/**
	 * @param wikiName
	 */
	public void createWiki(String wikiName)
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiName);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
		wikiWeb.enterTag(tag);
		wikiWeb.attachFileInAddWiki(file);
		wikiWeb.saveWiki();
	}

	void siteAndUserConfiguration() throws InterruptedException, IOException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		permissionOfOrg.put(orgType, true);
		orgData.put(domain, permissionOfOrg);

		Map<String, List<String>> userMap = new HashMap<>();
		userMap.put(domain, Arrays.asList(userNames));

		LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);

		Map<String, Boolean> moduleEditPermission = new LinkedHashMap<String, Boolean>();

		moduleEditPermission.put("View", true);
		moduleEditPermission.put("Edit", true);
		Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		modulePermission.put("Tasks", moduleEditPermission);
		modulePermission.put("Files", moduleEditPermission);

		LinkedHashMap<String, LinkedHashMap<String, Boolean>> siteUserRoles = new LinkedHashMap<>();
		siteUserRoles.put(userNames[1] + "@" + domain, rolesOfSiteUser);

		LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		siteUserModulePermission.put(userNames[0] + "@" + domain, modulePermission);

		configurationData.setOrgData(orgData);
		configurationData.setUserMap(userMap);
		configurationData.setNewPassword(newPassword);
		configurationData.setStatus(UserStatus.Active);
		configurationData.setStausLocked(false);
		configurationData.setSiteName(siteName);
		configurationData.setSiteGroupPermission(false);
		configurationData.setModuleList("home", "files", "wiki", "tasks", "q&a");
		configurationData.setSiteUserRoles(siteUserRoles);
		configurationData.setSiteUserModulePermission(siteUserModulePermission);

		String[] configurationList = {"setOrg", "addSystemAdminUsers", "createSite", "setGroupPermission", "enableModules", "addsiteuserswithoutroles", "setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setConfiguration(configurationList, configurationData));
		adminPageWeb = gotoAdminModule();
		logout();
	}

	void precondition() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		adminPageWeb = gotoAdminModule();
		logout();
	}
}
