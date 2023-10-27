package com.highq.test.mysite;

import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pagedata.PreConfiguration;
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
 * @author surbhi.khetan
 */
public class MyFiles_TCR0202 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	MyFilesPage myFilesWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemAdminSystemSettingsWeb;
	DocumentPage documentWeb;
	DocumentAddDataPage documentAddDataPage;

	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String jsonFileName = "preConfiguration_MyFiles_TCR202.json";
	String username = "usernormal1@file202.com";
	String password = "Password@123";
	String addMenuItem = "Files";
	String file = "doc2.txt";
	String add = "Folder";
	String folder = "FolderOfTCR0202";
	String destination = "My files(1)";
	String index = "My files";
	String leftPanelOption = "Favourites";

	@Test
	public void myFiles_TCR0202() throws IOException, JSONException, InterruptedException, UnsupportedFlavorException
	{

		siteAndUserConfiguration();
		precondition();
		scenario1();
	}

	private void siteAndUserConfiguration() throws IOException, JSONException, InterruptedException
	{

		/** Site and user setup */
		PreConfiguration preConfigurationTest = new PreConfiguration(driver);
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		Assert.assertTrue(preConfigurationTest.setPreConfigurationDataFromJson(jsonFileName));
	}

	private void precondition() throws InterruptedException, IOException
	{

		/**
		 * ASP Admin => Enable My File Sharing : On
		 * System Admin => Enable My File Sharing: On
		 */

		dashboardWeb = bannerPageWeb.gotoDashboard();
		systemAdminWeb = dashboardWeb.gotoSystemAdmin();
		systemAdminSystemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemAdminSystemSettingsWeb.enableMyFiles("ON");
		systemAdminSystemSettingsWeb.enableMyFilesSharing("ON for all system users");
		systemAdminSystemSettingsWeb.saveSettings();

		dashboardWeb = systemAdminSystemSettingsWeb.gotoDashboard();
		aspAdminWeb = dashboardWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableMyFiles("ON");
		aspConfigurationWeb.enableMyFilesSharing("ON for all system users");
		aspConfigurationWeb.saveConfigurations();
		logout();

		bannerPageWeb = login(username, password);
		myFilesWeb = bannerPageWeb.goToMyFiles();
		myFilesWeb.deleteAllDocs();

		/** Upload files/folder */

		myFilesWeb.selectItemFromAdd(addMenuItem);
		documentAddDataPage = new DocumentAddDataPage();
		documentAddDataPage.clean();
		documentAddDataPage.setFileuploadpath(file);
		myFilesWeb.addFile(documentAddDataPage, null);
		myFilesWeb.clickAddInModal();

		myFilesWeb.selectItemFromNew(add);
		myFilesWeb.addFolderAtSpecificLocation(folder, destination);
		myFilesWeb.clickAddInAddFolderModal();
	}

	private void scenario1() throws IOException, InterruptedException
	{

		markFileandFolderFavorite();
		removeFileAndFolderFromFavorite();
	}

	private void markFileandFolderFavorite() throws IOException, InterruptedException
	{

		myFilesWeb.selectLeftPanelFileOptions(index);

		myFilesWeb.addFileOrFolderToFavourites(folder);
		myFilesWeb.addFileOrFolderToFavourites(file);

		/** Verify if favourite icon is selected */
		assertTrue(myFilesWeb.verifyFileOrFolderFavouriteIconIsSelected(file));
		assertTrue(myFilesWeb.verifyFileOrFolderFavouriteIconIsSelected(folder));

		/** Verify if file/folder is present in favourites tab */
		myFilesWeb.selectLeftPanelFileOptions(leftPanelOption);
		assertTrue(myFilesWeb.verifyItemInFavourites(file));
		assertTrue(myFilesWeb.verifyItemInFavourites(folder));

		/** Verify if file/folder is present in favourites - Dashboard */
		assertTrue(myFilesWeb.verifyFavouritesElementInMyFavourites(file));
		myFilesWeb.clickOnFavourite();
		assertTrue(myFilesWeb.verifyFavouritesElementInMyFavourites(folder));
		myFilesWeb.clickOnFavourite();

		/** Verify content which is at the top in Dashboard - My Favourites */
		myFilesWeb.clickOnFavourite();
		assertTrue(myFilesWeb.verifyLastFavoriteContentAccessed(folder));
		myFilesWeb.clickOnFavourite();

		/** Perform some action on favourite items */
		myFilesWeb.selectLeftPanelFileOptions(index);
		myFilesWeb.previewFile(file);
		myFilesWeb.closePreviewPage();
		myFilesWeb.gotoDashboard();

		/** Verify if Last favorite content accessed is displayed first in Dashboard-My favourites. */
		myFilesWeb.clickOnFavourite();
		assertTrue(myFilesWeb.verifyLastFavoriteContentAccessed(file));
	}

	private void removeFileAndFolderFromFavorite()
	{

		myFilesWeb.goToMyFiles();
		myFilesWeb.selectLeftPanelFileOptions(index);
		myFilesWeb.removeFileOrFolderFromFavourites(file);
		myFilesWeb.removeFileOrFolderFromFavourites(folder);

		/** Verify if favourite icon is de-selected */
		Assert.assertFalse(myFilesWeb.verifyFileOrFolderFavouriteIconIsSelected(file));
		Assert.assertFalse(myFilesWeb.verifyFileOrFolderFavouriteIconIsSelected(folder));

		/** Verify if file/folder is removed from favourites tab */
		myFilesWeb.selectLeftPanelFileOptions(leftPanelOption);
		Assert.assertFalse(myFilesWeb.verifyItemInFavourites(file));
		Assert.assertFalse(myFilesWeb.verifyItemInFavourites(folder));

		/** Verify if file/folder is removed from favourites - Dashboard */
		myFilesWeb.gotoDashboard();
		Assert.assertFalse(myFilesWeb.verifyFavouritesElementInMyFavourites(file));
		myFilesWeb.clickOnFavourite();
		Assert.assertFalse(myFilesWeb.verifyFavouritesElementInMyFavourites(folder));
	}
}
