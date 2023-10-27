package com.highq.test.home;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.pagedata.PreConfiguration;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.HomePage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author krishna.bhadani
 */
public class HomeTC1890 extends BannerPageWeb
{

	private WebDriver driver;

	private String sysAdminEmail = "krishna.bhadani@highq.com";
	private String sysAdminPassword = "Admin@123";
	private String siteAdminEmail = "site.adminhome@highq.com";
	private String jsonFileName = "home/preConfiguration_Home_1890.json";

	private String siteName = "Home_TC1890";
	private String ckTitle = "Home_TC1890-CK-Title";
	private String content = "Automation Test Of CK";
	private String msg = "<SCRIPT>alert('This is XSS script running')</SCRIPT>";

	private DashboardPage dashboardWeb;
	private HomePage homeWeb;
	private AspAdminPage aspAdminWeb;
	private AspConfigurationPage aspConfigurationPage;

	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws Exception
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Test(priority = 1)
	public void HomeTC1890TestCase() throws Exception
	{
		preconfiguration();
		scenario1CheckFieldsInCKeditorPanelAndAddtheCKeditorpanel();
		scenario2EnableTheCKeditorInThePanel();
		scenario3EnterScriptInSourceOfCKEditorInContentEditorPanel();
	}

	private void scenario1CheckFieldsInCKeditorPanelAndAddtheCKeditorpanel() throws Exception
	{
		login(siteAdminEmail, sysAdminPassword);
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);

		addCkPanelInDashBoard();

		Reporter.log("expectedResult");
		assertTrue(homeWeb.verifyPanelWinodw(HomeLabels.CONTENT_EDITOR));
		assertTrue(homeWeb.verifyMobileDeviceInCKEditor());

		Reporter.log(" A new modal should open with heading of the modal as Add panel  CK editor");
		Reporter.log(" Cancel & Add button should display in bottom right of the modal.");
		Reporter.log(" On Clicking Cancel button, both modals (Add Panel & Add panel - CK editor) should get closed and CK editor panel should not be added.");
		Reporter.log(" On clicking Add button, modal should get closed and CK editor panel should get added");
		Reporter.log(" In bottom left left the modal it should display < Back to panel section link. Clicking on it CK editor modal should get closed and Add panel modal should remain open.");
		Reporter.log("Fields in Add panel - CK Editor modal should be as below:");
		Reporter.log("Title - should display with text field. Placeholder label of the text field as Title");
		Reporter.log("Hide in - should display with Mobile devices check box.");
		Reporter.log("Panel class - should display with text field having place holder label of the field as Enter panel class. Panel class field is for providing CSS style to the panel.");

		homeWeb.addCkEditorPanel(ckTitle, true);
		homeWeb.clickOnAdd();
		assertTrue(homeWeb.verifyPanelTitle(ckTitle));
		assertTrue(homeWeb.verifyAddPanelIcon());

		assertTrue(homeWeb.verifyCKeditor());

		homeWeb.clickOnSave();
		homeWeb.clickOnEditIcon();
		assertTrue(homeWeb.verifyMoreActionInAddedPanel(ckTitle, HomeLabels.EDIT));
		assertTrue(homeWeb.verifyMoreActionInAddedPanel(ckTitle, HomeLabels.REMOVE));

		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.CONTENT_EDITOR);

		assertTrue(homeWeb.verifyBackToPanelSectionInModelWindow());

		Reporter.log("Content editor panel should add in the selected section and by default CK editor tool bar should load on adding the panel..");
		Reporter.log("CK editor modal should get closed and a CK editor panel should get added in the selected section. Below CK editor panel added it should display Add panel (+) icon to add another panel.");
		Reporter.log("Title provided while adding the CK editor panel should display in top of the added panel. If no title is provided then it should display as blank");
		Reporter.log("More actions button should display along with CK title with Edit & Remove options displaying in it.");

		homeWeb.clickOnClose();
	}

	private void scenario2EnableTheCKeditorInThePanel() throws Exception
	{
		addCkPanelInDashBoard();

		homeWeb.addCkEditorPanel(ckTitle, true);
		homeWeb.clickOnAdd();
		assertTrue(homeWeb.verifyCKeditor());

		homeWeb.addcontentInCkEditor(content);

		homeWeb.clickOnSave();

		assertTrue(homeWeb.verifyContentofCKeditorPanel(content));
		Reporter.log("Contents, macros, images, Tables, attachments, Links, Style formats etc added in the CK editor should display in the CK Editor pane");
		logout();
	}

	private void scenario3EnterScriptInSourceOfCKEditorInContentEditorPanel() throws Exception
	{
		login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = gotoASPAdmin();
		aspConfigurationPage = aspAdminWeb.openConfigurationPage();
		aspConfigurationPage.setEnableXSSDirectoryRestrictedWord(true);
		aspConfigurationPage.saveConfigurations();
		// remove this code
		aspAdminWeb = gotoASPAdmin();
		addCkPanelInDashBoard();

		homeWeb.addCkEditorPanel(ckTitle, true);
		homeWeb.clickOnAdd();
		homeWeb.clickOnSourceInCKEditor();
		homeWeb.InsertDataInSourceCKEditor(msg);
		homeWeb.clickOnSave();

		assertTrue(homeWeb.verifyMessageInCKEditor(HomeLabels.PLEASE_REMOVE_SCRIPT_OR_OBJECT_OR_APPLET_FROM_GIVEN_INPUT));
		Reporter.log("User should not be allowed to save the Home Dashboard with script tags in Source box.");
		Reporter.log("It should display a validation message Please remove 'script' OR 'object' OR 'applet' from given input”");
		homeWeb.clickOnOkInErrorModal();
		logout();
	}

	private void addCkPanelInDashBoard() throws Exception
	{
		dashboardWeb = gotoDashboardByClickOnLogo();
		dashboardWeb.searchSite(siteName);

		homeWeb = gotoHomeModule();
		homeWeb.clickOnEditIcon();
		homeWeb.removeAllSections();
		homeWeb.clickOnAddSection();
		homeWeb.clickonSingleAddPanelIcon();
		homeWeb.clickonPanelLinkOnAddPanelWindow(HomeLabels.CONTENT_EDITOR);

	}

	private void preconfiguration() throws Exception
	{
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		login(sysAdminEmail, sysAdminPassword);
		assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
		logout();
	}

}
