package com.highq.test.file;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.FileLabels;
import com.highq.pagedata.DocumentAddDataPage;
import com.highq.pageobjects.base.AdminFilesPage;
import com.highq.pageobjects.base.AdminPage;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.DashboardPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class File_TC0836 extends BannerPageWeb
{

	BannerPage bannerPageWeb;
	String sysAdminEmail = "auto@highq.com";
	String sysAdminPassword = "Pa&&worD123";
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	DashboardPage dashboardWeb;
	String siteName = "FileTC0836";
	AdminPage adminPageWeb;
	AdminFilesPage adminFilesWeb;
	DocumentPage documentPage;
	DocumentAddDataPage addDoc;
	String fileName = "doc1.txt";
	String fileName1 = "ChildDoc2_2.pdf";
	String auditHistory = FileLabels.ISHEET_LABEL_AUDITHISTORY;
	String viewed = FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_AUDIT_VIEWD;
	String download = FileLabels.DOCUMENT_INDEXVIEW_MIDDLEPANEL_ACTIONBTN_DOWNLOAD;
	String downloaded = FileLabels.ISHEET_LABEL_DOWNLOADED;
	String files = FileLabels.SYSTEMVOCABULARY_DISPLAYNAME_DOCUMENTS;
	String printed = FileLabels.DOCUMENT_MIDDLEPANEL_SHAREMENU_AUDIT_PRINTED;

	String trueValue = "TRUE";
	String viewedInAdobeReader = "Viewed in Adobe Reader";

	By convertDocToPdf = By.id("convertDocToPdf");
	By openPdfDocumentDirectly = By.id("openPdfDocumentDirectly");
	By useAdobeReaderAfterPrinting = By.id("useAdobeReaderAfterPrinting");

	/**
	 * 
	 */
	@BeforeClass
	public void setUp()
	{
		driver = getDriver();
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Test(priority = 1)
	public void FileTC0836() throws InterruptedException, IOException
	{

		scenario1();
		scenario2();
		scenario3();
		scenario4();
		scenario5();
		scenario6();
		scenario7();
		scenario8();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario1() throws InterruptedException, IOException
	{

		preCondition();
		dashboardWeb = aspConfigurationWeb.gotoDashboard();
		dashboardWeb.searchSite(siteName);
		adminPageWeb = dashboardWeb.gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, false);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, false);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, false);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(download);

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(downloaded));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnPrintButtInFileViewer();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(printed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario2() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, true);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, false);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, false);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName1);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName1);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(download);

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(downloaded));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnPrintButtInFileViewer();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(printed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario3() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, true);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, true);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, false);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName1);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName1);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewedInAdobeReader));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario4() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, true);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, true);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, true);

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName1);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName1);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewedInAdobeReader));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario5() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, true);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, false);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, true);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName1);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName1);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(download);

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(downloaded));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario6() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, false);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, true);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, false);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(download);

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(downloaded));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnPrintButtInFileViewer();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(printed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario7() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, false);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, true);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, false);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName1);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName1);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewedInAdobeReader));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void scenario8() throws InterruptedException, IOException
	{

		adminPageWeb = gotoAdminModule();
		adminFilesWeb = adminPageWeb.clickFilesInLeftPanel();

		adminFilesWeb.selectCheckbox(convertDocToPdf, false);
		adminFilesWeb.selectCheckbox(openPdfDocumentDirectly, false);
		adminFilesWeb.selectCheckbox(useAdobeReaderAfterPrinting, true);
		adminFilesWeb.saveFilesChanges();

		documentPage = adminFilesWeb.gotoFileModule();
		documentPage.deleteAllDocs();
		documentPage.selectItemFromAdd(files);
		addDoc = new DocumentAddDataPage();
		addDoc.clean();
		addDoc.setFileuploadpath(fileName);
		documentPage.addFile(addDoc, null);
		documentPage.clickAddInAddFileModal();

		documentPage.clickOnSelectedFile(fileName);
		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(viewed));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(download);

		documentPage.clickOnMoreActionOfFileViewer();
		documentPage.gotoOptiOnOnMoreActionOfFileViewer(auditHistory);

		Assert.assertTrue(documentPage.verifyActionOnAuditHistoryFromMoreAction(downloaded));

		documentPage.clickOnCloseButtonOfAuditHistoryDialogBox();

		documentPage.clickOnCrossButtonInFileviewer();

	}

	/**
	 * @throws InterruptedException
	 */
	private void preCondition() throws InterruptedException
	{
		bannerPageWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerPageWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		aspConfigurationWeb.enableOpenPdfDocumentDirectly(trueValue);
		aspConfigurationWeb.saveConfigurations();
		aspConfigurationWeb.closeGlobalMessageOnAspAdmin();
	}

}
