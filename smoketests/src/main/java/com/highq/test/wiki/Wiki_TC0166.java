package com.highq.test.wiki;

import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.WikiPage;
import com.highq.pageobjects.pages.BannerPageWeb;
import com.highq.parsers.JSONParser;

/**
 * @author badal.gandhi
 */
public class Wiki_TC0166 extends BannerPageWeb
{

	/** Automation test for Like Wiki scenarios */
	private WebDriver driver;
	String jsonFileName = "preConfiguration_TC0166.json";
	JsonNode resultsFile;
	{
		String currentDir = System.getProperty("user.dir");
		try
		{
			resultsFile = JSONParser.getJsonNode(new File(currentDir + "//PreConfiguraionFiles//" + jsonFileName.trim()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Login Credentials */
	String sysAdminEmail = resultsFile.get("GlobalData").get("sysAdminEmail").asText();
	String sysAdminPassword = resultsFile.get("GlobalData").get("sysAdminPassword").asText();
	String normalUserPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData").get("Organization 1").get("user 1").get("newPassword").asText();;
	String siteAdminPassword = resultsFile.get("GlobalData").get("userResetData").get("userResetPasswordData").get("Organization 1").get("user 2").get("newPassword").asText();;
	String defaultPassword = resultsFile.get("GlobalData").get("userResetData").get("defaultPassword").asText().trim();

	String siteName = resultsFile.get("GlobalData").get("PreConfiguration").get("Sites").get("name").asText();

	String[] userNames = {"normal.siteuser0161_0019@sitelevel0166.com", "site.admin@sitelevel0166.com"};

	String wikiStatus = "Draft";
	String wikiTitle = "Wiki_TC0166_Test003_01";
	String autoSavedWiki = "Wiki_TC0166_AutoSaved_02";
	String wikiContent = "Wiki Content for TC0166";
	String autoSaveDraftMessage = "The system automatically saves a draft as you are editing. Auto-saves can be accessed by clicking your name in the top right and selecting \"My drafts\". Do you want to keep your auto-saved draft?";
	String deleteDraftMessage = "Are you sure you want to delete this page?";
	String tag = "demoTag";
	String file = "doc1.txt";
	String autoSavedStatus = "Auto-saved";
	String draftStatus = "Draft";
	String autoSaved = "Auto-saved";
	DashboardPage dashboardWeb;
	WikiPage wikiWeb;
	BannerPage bannerPageWeb;

	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	@Test(priority = 1)
	public void WikiTC0166() throws InterruptedException, IOException, JSONException
	{
		preconfiguration();
		Create_wiki_draft();
		auto_Save_draft();
		searchDraftedWiki();
		deleteDraftedWiki();
		cleanup();

	}

	private void cleanup()
	{
		wikiWeb.gotoWikiModule();
		wikiWeb.deleteWiki(autoSavedWiki);
		wikiWeb.logout();
	}

	private void preconfiguration() throws InterruptedException, IOException, JSONException
	{
		siteAndUserConfiguration();
		configurationsForNormalUsers();
	}

	public void Create_wiki_draft() throws InterruptedException, IOException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);

		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);

		wikiWeb = dashboardWeb.gotoWikiModule();

		if (wikiWeb.verifyMyDraftsLinkIsVisbile())
		{
			wikiWeb.clickOnMyDraftsInLeftMenu();
			if (!wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle))
			{
				addWikiAsDraft();
				wikiWeb.gotoWikiModule();
				Assert.assertTrue(wikiWeb.verifyMyDraftsLinkIsVisbile());
				wikiWeb.clickOnMyDraftsInLeftMenu();
				Assert.assertTrue(wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle));
			}
		}
		else if (!wikiWeb.verifyMyDraftsLinkIsVisbile())
		{
			addWikiAsDraft();
			wikiWeb.gotoWikiModule();
			Assert.assertTrue(wikiWeb.verifyMyDraftsLinkIsVisbile());
			wikiWeb.clickOnMyDraftsInLeftMenu();
			Assert.assertTrue(wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle));
		}
	}

	public void auto_Save_draft() throws InterruptedException, IOException
	{
		wikiWeb.gotoWikiModule();

		if (wikiWeb.verifySearchedPage(autoSavedWiki))
		{
			wikiWeb.selectSearchedWiki(autoSavedWiki);
			wikiWeb.selectMoreOptionsOperationsOfWiki("Edit");
			wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent + getRandomString());
		}
		else
		{
			wikiWeb.clickOnAddWiki();
			wikiWeb.setTitle(autoSavedWiki);
			wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
			wikiWeb.enterTag(tag);
			wikiWeb.attachFileInAddWiki(file);
		}
		wikiWeb.waitForAutoSavedNotification();
		wikiWeb.cancelWiki();
		Assert.assertTrue(wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage));
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.gotoWikiModule();

		wikiWeb.verifyMyDraftsLinkIsVisbile();
		wikiWeb.clickOnMyDraftsInLeftMenu();
		Assert.assertTrue(wikiWeb.verifyWikiStatus(autoSavedWiki, autoSavedStatus));
		wikiWeb.selectWikiFromMyDraft(autoSavedWiki);
		wikiWeb.saveWiki();

		Assert.assertTrue(wikiWeb.verifyMyDraftsLinkIsVisbile());
		wikiWeb.clickOnMyDraftsInLeftMenu();
		Assert.assertTrue(!wikiWeb.verifyDiscardedAutoSavedDraft(autoSavedWiki, draftStatus));
		wikiWeb.logout();
	}

	public void deleteDraftedWiki() throws InterruptedException, IOException
	{

		wikiWeb = gotoWikiModule();
		wikiWeb.clickOnMyDraftsInLeftMenu();
		wikiWeb.deleteWikiFromDraft(wikiTitle);
		Assert.assertTrue(wikiWeb.verifyDeleteDraftMessage(deleteDraftMessage));
		wikiWeb.clickCancelInDeleteWikiFromDraft();
		Assert.assertTrue(wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle));

		wikiWeb.deleteWikiFromDraft(wikiTitle);
		Assert.assertTrue(wikiWeb.verifyDeleteDraftMessage(deleteDraftMessage));
		wikiWeb.clickDeleteInDeleteWikiFromDraft();
		if(wikiWeb.verifyPresenceOfWikiInDrafts(wikiTitle))
			Assert.assertTrue(wikiWeb.verifyWikiStatusInMyDrafts(wikiTitle, autoSaved));

		wikiWeb.deleteWikiFromDraft(autoSavedWiki);
		Assert.assertTrue(wikiWeb.verifyDeleteDraftMessage(deleteDraftMessage));
		wikiWeb.clickDeleteInDeleteWikiFromDraft();
		Assert.assertTrue(!wikiWeb.verifyPresenceOfWikiInDrafts(autoSavedWiki));
	}

	public void searchDraftedWiki() throws InterruptedException, IOException
	{
		bannerPageWeb = login(userNames[0], normalUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();

		wikiWeb = gotoWikiModule();
		wikiWeb.clickOnMyDraftsInLeftMenu();
		Assert.assertTrue(wikiWeb.searchWikiInDraft(autoSavedWiki));
	}

	private void addWikiAsDraft()
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(wikiTitle);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
		wikiWeb.selectWikiStatus(wikiStatus);
		wikiWeb.saveWiki();
	}

	private void addWikiAsAutoSuggestedDraft() throws InterruptedException
	{
		wikiWeb.clickOnAddWiki();
		wikiWeb.setTitle(autoSavedWiki);
		wikiWeb.sendTextInCkContetTextBoxInAddWikiPage(wikiContent);
		wikiWeb.enterTag(tag);
		wikiWeb.attachFileInAddWiki(file);
		wikiWeb.waitForAutoSavedNotification();
		wikiWeb.cancelWiki();
		Assert.assertTrue(wikiWeb.verifyAutoSaveDraftDialogueContent(autoSaveDraftMessage));
		wikiWeb.saveAutoSaveDraftDialogue();
		wikiWeb.gotoWikiModule();

	}

	private void siteAndUserConfiguration() throws InterruptedException, IOException, JSONException
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);

		login(sysAdminEmail, sysAdminPassword);

		// Map<String, Map<String, Boolean>> orgData = new LinkedHashMap<>();
		// Map<String, Boolean> permissionOfOrg = new LinkedHashMap<>();
		// permissionOfOrg.put(orgType, true);
		// orgData.put(domain, permissionOfOrg);
		//
		// Map<String, List<String>> userMap = new HashMap<>();
		// userMap.put(domain, Arrays.asList(userNames));
		// SearchUserPageData userStausData = new SearchUserPageData();
		// userStausData.setStatus(UserStatus.Active);
		// userStausData.setStausLocked(false);
		//
		// LinkedHashMap<String, Boolean> rolesOfSiteUser = new LinkedHashMap<>();
		// rolesOfSiteUser.put("site admin".trim().toLowerCase(), true);
		//
		// Map<String, Boolean> modulePermissions = new LinkedHashMap<String, Boolean>();
		// modulePermissions.put("View", true);
		// modulePermissions.put("Edit", true);
		// Map<String, Map<String, Boolean>> modulePermission = new LinkedHashMap<String, Map<String, Boolean>>();
		// modulePermission.put("Wiki", modulePermissions);
		// modulePermission.put("Files", modulePermissions);
		//
		// LinkedHashMap<String,LinkedHashMap<String,Boolean>> siteUserRoles = new LinkedHashMap<>();
		// siteUserRoles.put(userNames[1]+"@"+domain, rolesOfSiteUser);
		//
		// LinkedHashMap<String, Map<String, Map<String, Boolean>>> siteUserModulePermission = new LinkedHashMap<>();
		// siteUserModulePermission.put(userNames[0]+"@"+domain, modulePermission);
		//
		// Map<String, String> userIdAndPassword = new LinkedHashMap<>();
		// userIdAndPassword.put(userNames[0]+"@"+domain, normalUserPassword);
		// userIdAndPassword.put(userNames[1]+"@"+domain, siteAdminPassword);
		//
		// configurationData.setOrgData(orgData);
		// configurationData.setUserMap(userMap);
		// //configurationData.setNewPassword(newPassword);
		// configurationData.setStatus(UserStatus.Active);
		// configurationData.setStausLocked(false);
		// configurationData.setSiteName(siteName);
		// configurationData.setSiteGroupPermission(false);
		// configurationData.setModuleList("home", "files", "wiki", "activity");
		// configurationData.setSiteUserRoles(siteUserRoles);
		// configurationData.setSiteUserModulePermission(siteUserModulePermission);
		// configurationData.setUserIdAndPassword(userIdAndPassword);
		//
		//
		// String[] configurationList = {"setOrg","addSystemAdminUsers","createSite","setGroupPermission","enableModules","addsiteuserswithoutroles","setSiteUserRoles"};
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

	private void configurationsForNormalUsers() throws InterruptedException
	{
		bannerPageWeb = login(userNames[0], normalUserPassword);
		dashboardWeb = bannerPageWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		wikiWeb = dashboardWeb.gotoWikiModule();

		if (!wikiWeb.verifyMyDraftsLinkIsVisbile())
		{
			addWikiAsDraft();
			addWikiAsAutoSuggestedDraft();
			wikiWeb.gotoWikiModule();
			wikiWeb.verifyMyDraftsLinkIsVisbile();
		}
		else if (wikiWeb.verifyMyDraftsLinkIsVisbile())
		{
			wikiWeb.clickOnMyDraftsInLeftMenu();
			if (!wikiWeb.verifyWikiStatus(wikiTitle, draftStatus))
			{
				addWikiAsDraft();
			}
			wikiWeb.clickOnMyDraftsInLeftMenu();

			if (!wikiWeb.verifyWikiStatus(autoSavedWiki, autoSavedStatus))
			{
				addWikiAsAutoSuggestedDraft();
			}
		}
		wikiWeb.logout();

	}

}
