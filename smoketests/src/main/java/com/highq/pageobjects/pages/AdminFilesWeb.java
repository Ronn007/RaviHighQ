package com.highq.pageobjects.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.AdminFilesPage;

public class AdminFilesWeb extends AdminPageWeb implements AdminFilesPage
{

	/***** File Permissions **********/
	By headerFilePermissions = By.xpath("//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEPERMISSIONHEADER + "']");
	By filePermissions_SiteOnlyRadio = By.id("permissionLevel_Site");
	By filePermissions_SiteAndFoldersRadio = By.id("permissionLevel_SiteAndFolder");
	By filePermissions_SiteFolderAndFilesRadio = By.id("permissionLevel_SiteFolderFiles");

	/********** File and Folder Metadata ***********/
	By headerFileAndFolderMetadata = By.xpath(
			"//*[contains(text(),'" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERMATADATA + "')]");
	By fileAndFolderMeta_ShowAuthorCheckbox = By.xpath("//*[@name='site.showContentAuthor']");
	By fileAndFolderMeta_ShowLastModifiedDateCheckbox = By.xpath("//*[@name='site.showModifiedDateTime']");
	By fileAndFolderMeta_ShowSizeCheckbox = By.xpath("//*[@name='site.showDocumentSize']");
	By fileAndFolderMeta_ShowVersionDraftCheckbox = By.xpath("//*[@name='site.showVersion']");
	By fileAndFolderMeta_ShowStatusCheckbox = By.xpath("//*[@name='site.showContentStatus']");
	By fileAndFolderMeta_NamingConvDropDown = By.id("showVersionID");

	/********** Advanced Options ***********/
	By headerAdvancedOptions = By.xpath(
			"//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_ADVANCEDOPTIONS + "']");
	By advancedOptions_EnableFileAnnotationsCheckbox = By.xpath(".//*[@name='site.fileannotationsEnable']");
	By advancedOptions_EnableCheckInOutCheckbox = By.xpath(".//*[@name='site.checkOutEnable']");
	By advancedOptions_EnableCommentCheckbox = By.xpath(".//*[@name='site.allowDocumentComment']");
	By advancedOptions_EnableApprovalCheckbox = By.xpath(".//*[@name='site.approvalDocumentWorkFlow']");
	By advancedOptions_EnableAutoNumberingCheckbox = By.xpath(".//*[@name='site.applyAutoIndex']");
	By advancedOptions_EnableAutomaticPDFConvCheckbox = By.xpath(".//*[@name='site.convertDocToPdf']");
	By advancedOptions_ActionRequiresAuthenCheckbox = By.xpath(".//*[@name='site.secureDocumentEmails']");
	By advancedOptions_EnableForceAndSuppressEmailCheckbox = By.xpath(".//*[@name='site.emailNotification']");
	By advancedOptions_EnableBulkDownloadCheckbox = By.xpath(".//*[@name='site.allowBulkDownload']");
	By advancedOptions_EnableDocReviewCheckbox = By.xpath(".//*[@name='site.enableDocumentReview']");
	By advancedOptions_ShowFilesIndexCheckbox = By.xpath(".//*[@name='site.showSitemap']");

	By advancedOptions_EnableAutoNumberingDropDown = By.id("autoNumberLevel");
	By advancedOptions_setNotificationLink = By.xpath("(//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_SETEMAILNOTIFICATION + "'])[1]");

	/********** Viewer Settings ***********/
	By headerViewerSettings = By.xpath("//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGSHEADER + "']");
	By viewerSettings_UserStandardHTML5Radio = By.id("viewerOptionsID_USE_STANDARD_HTML5_VIEWER");
	By viewerSettings_UseAdobeForPrintingCheckbox = By.xpath(".//*[@name='site.useAdobeReaderForPrinting']");
	By viewerSettings_UserLegacyFlashRadio = By.id("viewerOptionsID_USE_LEGACY_FLASH_BASED_VIEWER");

	/********** DRM Options ***********/
	By headerDRMOptions = By
			.xpath("//*[normalize-space(text())='" + SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DRMHEADER + "']");
	By drm_WaterMarkInput = By.id("watermarkConfigCSV");
	By drm_PDFConvSettingLink = By.xpath("//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_PDFCONVERSIONSETTINGS + "']");

	By drm_enableOnlineViewerCheckbox = By.id("drmOptionID_ONLINE_VIEWER_WITH_DRM");
	By drm_encryptFilesWithSecloreCheckbox = By.id("drmOptionID_Seclore");
	By drm_encryptNativePDFWithFileOpenPluginCheckbox = By.id("drmOptionID_PDF_SECURITY");
	By drm_convertAllFileToPDFCheckbox = By.id("drmOptionID_PDF_SECURITY_WITH_AUTOMATIC_PDF_CONVERSION");

	/********* Notification ***********/
	By notificationModal = By.id("siteAdmin_module_setNotification_modal");
	By notification_AllCheckbox = By
			.xpath("//*[@id='setNotificationModal']//*[@name='site.documentApprovalAlertToAll']");
	By notification_Save = By.xpath("(//*[@class='modal-footer']//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_SAVE + "'])[last()]");

	/************** PDF Conversion Settings ******************/
	By pdfConversionModal = By.id("openPdfConversionSettingsModal");
	By pdfConversion_PDFextensionInput = By.id("docExtAutoSuggest-tokenfield");
	By pdfConversion_SaveButton = By.id("openPdfConversionSettingsModal_saveOpenPdfConSettingsModal");
	By pdfConversion_CancelButton = By.id("openPdfConversionSettingsModal_cancelOpenPdfConSettingsModal");

	/** Default files and folder setting - Folders */
	By folder_DefaultSortOrderDropDown = By.xpath("//*[@data-id='folderSortID']");
	By folder_DefaultSortOrderComboBox = By
			.xpath("//*[@data-id='folderSortID']/following-sibling::*[@role='combobox']");
	By folder_DefaultSortOrder_ApplyToAllCheckBox = By.id("applyDefaultFolder_SortOrder");

	By folder_DefaultFolderViewDropDown = By.xpath("//*[@data-id='siteAdmin_file_defaultFolderRenderView']");
	By folder_DefaultFolderViewComboBox = By
			.xpath("//*[@data-id='siteAdmin_file_defaultFolderRenderView']/following-sibling::*[@role='combobox']");
	By folder_DefaultFolderView_ApplyToAllCheckBox = By.id("siteAdmin_file_applyAllDefaultFolderRenderView");

	By folder_DefaultFolderPermissionsDropDown = By.xpath("//*[@data-id='defaultFolder_Permission']");
	By folder_DefaultFolderPermissionsComboBox = By
			.xpath("//*[@data-id='defaultFolder_Permission']/following-sibling::*[@role='combobox']");
	By folder_DefaultFolderPermissions_ApplyToAllCheckBox = By.id("site_admin_inherit_Permission");

	String folder_sortOrderList = "//*[@data-id='folderSortID']/following-sibling::*[@role='combobox']//li";
	String folder_viewList = "//*[@data-id='siteAdmin_file_defaultFolderRenderView']/following-sibling::*[@role='combobox']//li";
	String folder_permissionList = "//*[@data-id='defaultFolder_Permission']/following-sibling::*[@role='combobox']//li";

	/** Default files and folder setting - Files */
	By files_DefaultSortOrderDropDown = By.xpath("//*[@data-id='docSortID']");
	By files_DefaultSortOrderComboBox = By.xpath("//*[@data-id='docSortID']/following-sibling::*[@role='combobox']");
	By files_DefaultSortOrder_ApplyToAllCheckBox = By.id("applyDefaultFile_SortOrder");

	String files_sortOrderList = "//*[@data-id='docSortID']/following-sibling::*[@role='combobox']//li";

	/** Hybrid EKM configuration */
	By encryptionKeyManagerDropDown = By.xpath("//*[@data-id='keyManagerApplianceId']");
	By encryptionKeyManagerComboBox = By
			.xpath("//*[@data-id='keyManagerApplianceId']/following-sibling::*[@role='combobox']");
	String encryptionKeys = "//*[@data-id='keyManagerApplianceId']/following-sibling::*[@role='combobox']//li";
	By fileStoreDropDown = By.xpath("//*[@data-id='encryptorDecryptorApplianceId']");
	By fileStoreComboBox = By
			.xpath("//*[@data-id='encryptorDecryptorApplianceId']/following-sibling::*[@role='combobox']");
	String fileStoreList = "//*[@data-id='encryptorDecryptorApplianceId']/following-sibling::*[@role='combobox']//li";
	By enableFileSearchIndexing = By.id("fileIndexONId");

	/** Third party services */
	By docuSignDropDown = By
			.xpath("//*[contains(text(),'DocuSign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]");
	By docuSignComboBox = By.xpath(
			"//*[contains(text(),'DocuSign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]/following-sibling::*[@role='combobox']");
	String docuSignOptionList = "//*[contains(text(),'DocuSign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]/following-sibling::*[@role='combobox']//li";

	By adobeSignDropDown = By
			.xpath("//*[contains(text(),'Adobe Sign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]");
	By adobeSignComboBox = By.xpath(
			"//*[contains(text(),'Adobe Sign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]/following-sibling::*[@role='combobox']");
	String adobeSignOptionList = "//*[contains(text(),'Adobe Sign')]/following::*[contains(@data-id,'thirdPartyServiceSelect')][1]/following-sibling::*[@role='combobox']//li";

	By lastOpenedModal = By.xpath("(//*[@class='modal-content'])[last()]");
	By continueButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_THIRDPARTYSERVICE_CONTINUE + "']");
	By cancelButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_THIRDPARTYSERVICE_CANCEL + "']");
	By saveButton = By.id("adminDocumentSaveBtnBottom");// By.xpath("(//*[normalize-space(text())='Save'])[1]");
	By okButton = By.xpath("(//*[@class='modal-footer'])[last()]//*[normalize-space(text())='OK']");
	By documentAnalysisEngineConfigurationLbl = By.xpath(".//*[@id='frmAdminDocuments']/*[text()='"
			+ AdminLabels.SITEADMIN_MODULESETTINS_FILES_DOCUMENT_ANALYSIS_ENGINE_CONFIGURATION_LBL + "']");
	By enableAiEngineTrainingChechbox = By.id("siteAiEngineTraining");
	String allDivList = ".//*[@id='frmAdminDocuments']/div";

	By labelOfManageModal = By.xpath(".//*[@id='manageClassifierListID_TITLE']/div[text()='Manage']");
	By engineConfig = By.xpath("//*[@id='frmAdminDocuments']//*[normalize-space(text())='"
			+ SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINECONFIGURATION + "']");
	By highqEngineOptions = By.xpath(
			"//*[@id='frmAdminDocuments']//*[not(contains(@class,'form-control open'))]/*[@data-id='engineAnalysisServiceSelect_HIGHQ']");
	By kiraEngineOptions = By.xpath(
			"//*[@id='frmAdminDocuments']//*[not(contains(@class,'form-control open'))]/*[@data-id='engineAnalysisServiceSelect_KIRA']");
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;

	By advancedOptions_EnableDocumentAnalysis = By.xpath(".//*[@name='site.docAnalysisSupport']");

	/* DocuSign */
	By locUserName = By.xpath(".//*[@id='username']");
	By locSubmit = By.xpath(".//*[@type='submit']");
	By locPassword = By.xpath(".//*[@id='password']");

	By locCheckInOut = By.id("checkOutEnable");
	By locThridpartyDiv = By.id("thirdPartyServiceMoreActionDivForSite");
	By locOkBtnDisableWindow = By.id("THIRD_PARTY_OFF_MODAL_ID_ok");
	By locFileDocemntDiv = By.id("frmAdminDocuments");
	By locRevokebtn = By.id("system_admin_third_party_service_revoke_id_revoke");
	By locMainCloseBtnOnRevokeModel = By.id("system_admin_third_party_service_revoke_id_MAIN_CLOSE_BUTTON");
	By locCloseBtnRevokeModel = By.id("system_admin_third_party_service_revoke_id_close");
	By enableSiteInbox = By.id("inboxEnable");
	By addEmailIDSiteInbox = By.id("inboxAccountNameID");
	By enableOpenInOfficeCheckBox = By.id("enableOnlineOfficeID");
	By enableOpenInOfficeOnlineForSiteAdmin = By
			.xpath(".//*[@id='frmAdminDocuments']/div//*[normalize-space(.)='Enable Open in Office Online']");
	By engineServiceModal = By
			.xpath(".//*[contains(@class,'adminFilesPage modal-open')]//*[@id='configureEngineServiceModal']");
	By engineModalSave = By.id("site_admin_engine_management_modal_id_save");
	By modalClosed = By.xpath("//*[@class='modal fade largeModal' and @id='site_admin_engine_management_modal_id']");
	By locEnableStatus = By.id("showContentStatus");
	By middlePanel = By.id("siteAdmin_module_mainMiddlePanelDivID");

	public AdminFilesWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * Set Admin File permission
	 *
	 * @param permission
	 *        one of the three permissions(site only,site and folders or site,
	 *        folders and files)
	 * @author dheeraj.rajput
	 */
	@Override
	public void setFilePermission(String permission)
	{
		String lowerCase = permission.trim().toLowerCase();
		if (SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEONLY.toLowerCase()
				.equals(lowerCase))
		{
			findVisibleElement(filePermissions_SiteOnlyRadio).click();
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEANDFOLDERS
				.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(filePermissions_SiteAndFoldersRadio).click();
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES
				.toLowerCase().equals(lowerCase))
		{
			findVisibleElement(filePermissions_SiteFolderAndFilesRadio).click();
		}
		else
		{
			System.err.println(permission + " is not a correct options.");
		}
	}

	/**
	 * Set viewer settings
	 *
	 * @param setting
	 *        {@value html5 - to select Use standard HTML5 viewer radio, legacy
	 *        - to select Use legacy Flash-based viewer radio}
	 * @author dheeraj.rajput
	 */
	@Override
	public void setViewerSettings(String setting)
	{
		String lowerCase = setting.trim().toLowerCase();
		if (SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGS_STANDARDHTML5VIEWER.toLowerCase()
				.equals(lowerCase))
		{
			findVisibleElement(viewerSettings_UserStandardHTML5Radio).click();
		}
		else if (SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGS_LEGACYFLASHBASEDVIEWER.toLowerCase()
				.equals(lowerCase))
		{
			findVisibleElement(viewerSettings_UserLegacyFlashRadio).click();
		}
		else
		{
			System.err.println(setting + " is not a correct options.");
		}
	}

	/**
	 * Check/Uncheck any Admin Files checkbox
	 *
	 * @param optionToCheck
	 *        visible text of the checkbox element(exact text is needed)
	 * @param state
	 *        {@value true - to check, false - to uncheck}
	 * @author dheeraj.rajput
	 */

	@Override
	public void selectFileOptionCheckbox(String optionToCheck, boolean state)
	{
		By fileOption = By
				.xpath("(//*[normalize-space(.)='" + optionToCheck.trim() + "'])[last()]/*[@type='checkbox']");
		setSelection(fileOption, state);
	}

	/**
	 * Select naming convention
	 *
	 * @param selectOption
	 *        naming convention to select
	 * @author dheeraj.rajput
	 */

	@Override
	public void selectNamingConvention(String selectOption)
	{
		WebElement namingConvDrpDwn = findVisibleElement(fileAndFolderMeta_NamingConvDropDown);
		Select select = new Select(namingConvDrpDwn);
		select.selectByVisibleText(selectOption.trim());
	}

	/**
	 * Select auto numbering option
	 *
	 * @param selectOption
	 *        option to select
	 * @author dheeraj.rajput
	 */

	@Override
	public void selectAutoNumberingLevel(String selectOption)
	{
		WebElement autoNumDrpDwn = findVisibleElement(advancedOptions_EnableAutoNumberingDropDown);
		Select select = new Select(autoNumDrpDwn);
		select.selectByVisibleText(selectOption.trim());
	}

	/**
	 * Select checkbox
	 *
	 * @param option
	 *        xpath of checkbox
	 * @param check
	 *        {@value true - to check, false - to uncheck}
	 * @author dheeraj.rajput
	 */

	@Override
	public void selectCheckbox(By option, boolean check)
	{
		WebElement element = findVisibleElement(option);
		boolean checked = element.isSelected();
		if (checked != check)
		{
			element.click();
		}
	}

	/**
	 * Click on save
	 *
	 * @author dheeraj.rajput
	 */

	@Override
	public void saveFilesChanges()
	{
		findVisibleElement(saveButton).click();
		waitTillGlobalMessageDissappears();
	}

	/**
	 * Send watermark text
	 *
	 * @param text
	 *        to send in Input box
	 * @author dheeraj.rajput
	 */
	@Override
	public void sendDRMWaterMarkText(String text)
	{
		if (isDisplayed(drm_WaterMarkInput))
		{
			WebElement inputBox = findVisibleElement(drm_WaterMarkInput);
			inputBox.clear();
			inputBox.sendKeys(text);
		}
	}

	/**
	 * Set PDF conversion setting for different file extensions
	 *
	 * @param text
	 *        string containing file extension.(Separate extensions in string
	 *        with ',' while sending multiple file types).
	 * @author dheeraj.rajput
	 */
	@Override
	public void setPDFConversionSetting(String text)
	{
		if (!isDisplayed(drm_PDFConvSettingLink))
		{
			convertAllFilesToPDFAndEncrypt(true);
		}
		else
		{
			findVisibleElement(drm_PDFConvSettingLink).click();
			findPresentElement(pdfConversionModal, Speed.slow, 10);
			WebElement inputBox = findVisibleElement(pdfConversion_PDFextensionInput);
			if (text.contains(","))
			{
				String[] extensions = text.split(",");
				for (int i = 0; i < extensions.length; i++)
				{
					inputBox.clear();
					inputBox.sendKeys(extensions[i].trim());
					inputBox.sendKeys(Keys.TAB);
				}
			}
			else
			{
				inputBox.clear();
				inputBox.sendKeys(text.trim());
				inputBox.sendKeys(Keys.TAB);
			}
			findVisibleElement(pdfConversion_SaveButton).click();
		}
	}

	/**
	 * Set default folder sort order
	 *
	 * @param sortOrder
	 *        order to select
	 * @param applyToAll
	 *        Boolean - true: "Apply to all folder"
	 */

	@Override
	public void setDefaultFolderSortOrder(String sortOrder, boolean applyToAll)
	{
		selectOptionFromDropDown(folder_DefaultSortOrderDropDown, folder_DefaultSortOrderComboBox, folder_sortOrderList,
				sortOrder);
		if (applyToAll)
		{
			findVisibleElement(folder_DefaultSortOrder_ApplyToAllCheckBox).click();
			findPresentElement(modalContent);
			clickContinue();
		}
		else
		{
			findVisibleElement(folder_DefaultSortOrder_ApplyToAllCheckBox).click();
		}
	}

	/**
	 * Set default folder view
	 *
	 * @param view
	 *        view to select
	 * @param applyToAll
	 *        Boolean - true: "Apply to all folder"
	 */

	@Override
	public void setDefaultFolderView(String view, boolean applyToAll)
	{
		selectOptionFromDropDown(folder_DefaultFolderViewDropDown, folder_DefaultFolderViewComboBox, folder_viewList,
				view);
		if (applyToAll)
		{
			findVisibleElement(folder_DefaultFolderView_ApplyToAllCheckBox).click();
			findPresentElement(modalContent);
			clickContinue();
		}
		else
		{
			findVisibleElement(folder_DefaultFolderView_ApplyToAllCheckBox).click();
		}
	}

	/**
	 * Set default folder permissions
	 *
	 * @param permission
	 *        permission to select
	 * @param applyToAll
	 *        Boolean - true: "Apply to all folder"
	 */
	@Override
	public void setDefaultFolderPermissions(String permission, boolean applyToAll)
	{
		selectOptionFromDropDown(folder_DefaultFolderPermissionsDropDown, folder_DefaultFolderPermissionsComboBox,
				folder_permissionList, permission);
		if (applyToAll)
		{
			findVisibleElement(folder_DefaultFolderPermissions_ApplyToAllCheckBox).click();
			findPresentElement(modalContent);
			clickContinue();
		}
		else
		{
			findVisibleElement(folder_DefaultFolderPermissions_ApplyToAllCheckBox).click();
		}
	}

	/**
	 * Set default file sort order
	 *
	 * @param sortOrder
	 *        order to select
	 * @param applyToAll
	 *        Boolean - true: "Apply to all existing folders"
	 */

	@Override
	public void setDefaultFilesSortOrder(String sortOrder, boolean applyToAll)
	{
		selectOptionFromDropDown(files_DefaultSortOrderDropDown, files_DefaultSortOrderComboBox, files_sortOrderList,
				sortOrder);
		if (applyToAll)
		{
			findVisibleElement(files_DefaultSortOrder_ApplyToAllCheckBox).click();
			findPresentElement(modalContent);
			clickContinue();
		}
		else
		{
			findVisibleElement(files_DefaultSortOrder_ApplyToAllCheckBox).click();
		}
	}

	@Override
	public void enableOnlineViewerWithDRM()
	{
		setSelection(drm_enableOnlineViewerCheckbox, true);
	}

	@Override
	public void clickContinue()
	{
		findVisibleElement(continueButton).click();
	}

	@Override
	public void clickOK()
	{
		findVisibleElement(okButton).click();
	}

	@Override
	public void disableOnlineViewerWithDRM()
	{
		if (findVisibleElement(drm_enableOnlineViewerCheckbox).isSelected())
		{
			setSelection(drm_enableOnlineViewerCheckbox, false);
			findVisibleElement(lastOpenedModal);
			clickOK();
		}
	}

	@Override
	public void encryptFilesWithSeclorePlugin(boolean state)
	{
		setSelection(drm_encryptFilesWithSecloreCheckbox, state);
	}

	@Override
	public void encryptFilesWithFileOpenPlugin(boolean state)
	{
		setSelection(drm_encryptNativePDFWithFileOpenPluginCheckbox, state);
	}

	@Override
	public void convertAllFilesToPDFAndEncrypt(boolean state)
	{
		setSelection(drm_convertAllFileToPDFCheckbox, state);
	}

	@Override
	public void serEncryptionKeyManager(String encryptionKey)
	{
		selectOptionFromDropDown(encryptionKeyManagerDropDown, encryptionKeyManagerComboBox, encryptionKeys,
				encryptionKey);
	}

	@Override
	public void enableFileSearchIndexing(boolean state)
	{
		setSelection(enableFileSearchIndexing, state);
	}

	@Override
	public void setDocuSign(String option)
	{
		selectOptionFromDropDown(docuSignDropDown, docuSignComboBox, docuSignOptionList, option);
	}

	@Override
	public void setAdobeSign(String option)
	{
		selectOptionFromDropDown(adobeSignDropDown, adobeSignComboBox, adobeSignOptionList, option);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyDocumentAnalysisEngineConfigurationDisdplay()
	{
		return isDisplayed(documentAnalysisEngineConfigurationLbl);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyEnableAIEngineTrainingDisdplay()
	{
		return isDisplayed(enableAiEngineTrainingChechbox);
	}

	/**
	 * @author tejash.trivedi
	 */
	@Override
	public void enableAIEngineTraining(boolean state)
	{
		setSelection(enableAiEngineTrainingChechbox, state);
	}

	/**
	 * @param engineName
	 * @param actionOption
	 * @author tejash.trivedi
	 */
	@Override
	public void clickMoreActionOptionDocumentAnalysisEngineConfiguration(String engineName, String actionOption)
	{
		int size = driver.findElements(By.xpath(allDivList)).size();
		for (int i = 1; i <= size; i++)
		{
			if (isDisplayed(
					By.xpath(allDivList + "[" + i + "]//strong[text()='" + engineName.toUpperCase().trim() + "']")))
			{
				findVisibleElement(By.xpath(allDivList + "[" + i + "]//*[@id='engineServiceMoreActionDiv']")).click();
				findVisibleElement(By.xpath(allDivList + "[" + i + "]//a[normalize-space(.)='" + actionOption + "']"))
						.click();
				break;
			}
		}
	}

	/**
	 * @param engineName
	 * @param label
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyLabelOfTrainingDisplayed(String engineName, String label)
	{
		int size = driver.findElements(By.xpath(allDivList)).size();
		for (int i = 1; i <= size; i++)
		{
			if (findVisibleElement(
					By.xpath(allDivList + "[" + i + "]//strong[text()='" + engineName.toUpperCase().trim() + "']"))
							.isDisplayed())
			{
				return findVisibleElement(By.xpath(allDivList + "[" + i + "]//*[normalize-space(.)='" + label + "']"))
						.isDisplayed();
			}
		}
		return false;
	}

	/**
	 * @param engineName
	 * @return
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	@Override
	public boolean verifyLabelOfEngineNameDisplayed(String engineName)
	{
		int size = driver.findElements(By.xpath(allDivList)).size();
		for (int i = 1; i <= size; i++)
		{
			String engine = findVisibleElement(By.xpath(allDivList + "[" + i + "]//strong")).getText().trim();
			if (engine.equals(engineName))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param engineName
	 * @param value
	 * @return
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	@Override
	public boolean verifyValuesOfEngineDisplayed(String engineName, String value)
	{
		int size = driver.findElements(By.xpath(allDivList)).size();
		for (int i = 1; i <= size; i++)
		{
			String engine = findVisibleElement(By.xpath(allDivList + "[" + i + "]//strong")).getText().trim();
			if (engine.equals(engineName))
			{
				return isDisplayed(By.xpath(
						".//*[@id='frmAdminDocuments']/div[" + i + "]//a/span[normalize-space(.)='" + value + "']"));
			}
		}
		return false;
	}

	/**
	 * @return
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	public boolean verifyLabelOfManageModalDisplayed()
	{
		return isDisplayed(labelOfManageModal);
	}

	/**
	 * This enableDocumentAnalysisEngine() is used to On or Off the AnalysisEngine
	 *
	 * @author janki.hirani created date: 28/04/2018
	 * @param engineName
	 * @param option
	 * @throws InterruptedException
	 */
	public void enableDocumentAnalysisEngine(String engineName, String option) throws InterruptedException
	{
		if (isDisplayed(engineConfig, Speed.slow))
		{
			if (engineName.equals(highqEngine))
			{
				findVisibleElement(highqEngineOptions).click();
				findVisibleElement(
						By.xpath("//*[@id='frmAdminDocuments']//ul[@aria-expanded='true']//*[normalize-space(text())='"
								+ option.trim() + "']")).click();
			}
			else if (engineName.equals(kiraEngine))
			{
				findVisibleElement(kiraEngineOptions).click();
				findVisibleElement(
						By.xpath("//*[@id='frmAdminDocuments']//ul[@aria-expanded='true']//*[normalize-space(text())='"
								+ option.trim() + "']")).click();
			}
		}
	}

	/**
	 * <<<<<<< Updated upstream returns text message of enable document analysis
	 * 
	 * @author anil.sikhwal
	 * @return String
	 */
	public String getEnableDocAnalysisTextMsg()
	{
		WebElement elem = findVisibleElement(advancedOptions_EnableDocumentAnalysis, Speed.slow);
		WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;",
				elem);

		return parent.getText().trim();
	}

	/**
	 * Verify visibility of Document Analysis check box
	 * 
	 * @author anil.sikhwal
	 * @return boolean
	 */
	public boolean verifyPresenceDocAnalysisCheckbox()
	{
		return isDisplayed(advancedOptions_EnableDocumentAnalysis, Speed.slow);
	}

	/**
	 * returns if checkbox is selected
	 * 
	 * @author anil.sikhwal
	 * @return boolean
	 */
	public boolean getEnableDocAnalysisChekcboxStatus()
	{
		return findVisibleElement(advancedOptions_EnableDocumentAnalysis, Speed.slow).isSelected();
	}

	/**
	 * @author dharti.patel Set DocuSign Option =======
	 * @author dharti.patel Set DocuSign Option >>>>>>> Stashed changes
	 * @param serviceName
	 */
	public void setDocuSign(String serviceName, String option)

	{
		selectOptionFromDropDown(docuSignDropDown, docuSignComboBox, docuSignOptionList, option);
		if (isDisplayed(
				By.xpath(".//*[@class='modal-title'][normalize-space(text())='Disable " + serviceName.trim() + "']")))
		{
			clickOnOkDisableService(serviceName);
		}
	}

	/**
	 * @author dharti.patel Set Adobe Option
	 * @param serviceName
	 */
	public void setAdobeSign(String serviceName, String option)

	{
		selectOptionFromDropDown(adobeSignDropDown, adobeSignComboBox, adobeSignOptionList, option);
		if (isDisplayed(
				By.xpath(".//*[@class='modal-title'][normalize-space(text())='Disable " + serviceName.trim() + "']")))
		{
			clickOnOkDisableService(serviceName);
		}

	}

	/**
	 * @author dharti.patel Count total service On
	 */
	public int countServiceOn()
	{
		findVisibleElement(By.id("thirdPartyServiceMoreActionDivForSite"), Speed.slow);
		int countServiceOn = 0;
		int countServiceOnDefault = 0;
		int totalCountServiceOn = 0;

		countServiceOn = driver.findElements(By.xpath(".//*[contains(@class,'btn dropdown') and @title='"
				+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON + "']")).size();
		countServiceOnDefault = driver.findElements(By.xpath(".//*[contains(@class,'btn dropdown') and @title='"
				+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ONDEFAULT + "']")).size();
		totalCountServiceOn = countServiceOn + countServiceOnDefault;

		return totalCountServiceOn;

	}

	/**
	 * @author dharti.patel return name of On Service
	 * @param totalServiceOn
	 */

	public String getOnServiceName(int totalServiceOn)
	{
		String service = null;

		if (isDisplayed(By.xpath(".//*[contains(@class,'btn dropdown') and @title='"
				+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON + "']"))
				|| isDisplayed(By.xpath(".//*[contains(@class,'btn dropdown') and @title='"
						+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ONDEFAULT + "']")))
		{
			WebElement e = findVisibleElement(By.xpath(
					".//*[normalize-space(text())='" + AdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES
							+ "']//following::p[@class='marg0']/strong"));
			service = e.getText();

		}

		return service;

	}

	/**
	 * @author dharti.patel set value of check In/checkOut
	 * @param value
	 */
	public void enableCheckInCheckOut(boolean value)
	{
		WebElement locCheck = findVisibleElement(locCheckInOut, Speed.slow);
		if (value)
		{
			if (!locCheck.isSelected())
			{
				locCheck.click();
			}

		}
		else
		{
			if (locCheck.isSelected())
			{
				locCheck.click();
			}

		}
	}

	/**
	 * @author dharti.patel verify thirdParty Service
	 * @param serviceName
	 */
	public boolean verifyThirdPartyService(String serviceName)
	{
		findVisibleElement(locThridpartyDiv);
		if (isDisplayed(
				By.xpath(".//*[@id='thirdPartyServiceMoreActionDivForSite']//following::*[normalize-space(text())='"
						+ serviceName.trim() + "']"),
				Speed.slow))
		{
			return true;
		}
		return false;
	}

	/**
	 * @author dharti.patel Click on OK button on Disable(OFF) service model
	 * @param serviceName
	 */
	public void clickOnOkDisableService(String serviceName)
	{
		findVisibleElement(
				By.xpath(".//*[@class='modal-title'][normalize-space(text())='Disable " + serviceName.trim() + "']"));
		WebElement okBtn = findVisibleElement(locOkBtnDisableWindow);
		okBtn.click();
	}

	/**
	 * @author dharti.patel verify selected option of ThirdParty service In dropdown
	 * @param serviceName
	 * @param option
	 */

	public boolean verifySelectedStatusofThirdParty(String serviceName, String option)
	{
		findVisibleElement(locThridpartyDiv);

		if (isDisplayed(
				By.xpath(".//*[@id='thirdPartyServiceMoreActionDivForSite']//following::*[normalize-space(text())='"
						+ serviceName.trim() + "']")))
		{
			WebElement op = findVisibleElement(By.xpath("(//strong[normalize-space(text())='" + serviceName.trim()
					+ "']//following::*[contains(@class,'btn dropdown-toggle btn-default')])[1]"));
			op.click();

			if (isDisplayed(By.xpath("(.//*[normalize-space(text())='" + option.trim()
					+ "']//preceding::*[@class='dropdown-menu inner' and @aria-expanded='true']//li[@class='selected']//a[@aria-selected='true' and @role='option'])[1]")))
			{
				return true;
			}

		}
		return false;
	}

	/**
	 * @author dharti.patel verify ThirdParty Option
	 */
	public boolean verifyThirdPartyOption()
	{
		findVisibleElement(locFileDocemntDiv, Speed.slow);
		if (isDisplayed(By.xpath(".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='"
				+ AdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES + "']")))
		{
			return true;
		}
		return false;

	}

	/**
	 * @author dharti.patel verify Service Description like Logo, Service Name,
	 *         Desctiption ...
	 * @param serviceName
	 */
	public boolean verifyServiceDescription(String serviceName)
	{
		if (isDisplayed(By.xpath(".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='"
				+ AdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES + "']")))
		{
			if (serviceName.trim().equals(SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX))
			{
				if (isDisplayed(By.xpath(".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='"
						+ AdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES
						+ "']//following::*[normalize-space(text())='" + serviceName.trim() + "']")))
				{
					String DocuSignDescription = "DocuSign allows users to securely and effortlessly eSign documents across the world. Supported within Microsoft Word and PDF and with the ability to sign in 43 localized languages, users can ensure their documents for all occasions are safely and securely signed.";
					return (isDisplayed(By.xpath(
							".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='Third party services']//following::*[normalize-space(text())='"
									+ serviceName.trim()
									+ "']/preceding::*[contains(@src,'getThirdPartyServiceLogo')]"))
							&& isDisplayed(By.xpath(".//*[normalize-space(text())='"
									+ AdminLabels.ASPADMIN_DOCUSIGN_SANDBOX + "']//following::*[contains(text(),'"
									+ DocuSignDescription.trim() + "')]")));
				}
			}
			else if (serviceName.trim().equals(SystemAdminLabels.ADOBE_SIGN_SANDBOX))
			{
				if (isDisplayed(By.xpath(".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='"
						+ AdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES
						+ "']//following::*[normalize-space(text())='" + serviceName.trim() + "']")))
				{
					String AdobeSignDescription = "Adobe Sign (formerly EchoSign) allows users to add e-signatures to existing business processes and to create all-digital workflows, speeding up business transactions from start to finish.";
					return (isDisplayed(By.xpath(
							".//*[@id='frmAdminDocuments']//following::*[normalize-space(text())='Third party services']//following::*[normalize-space(text())='"
									+ serviceName.trim()
									+ "']/preceding::*[contains(@src,'getThirdPartyServiceLogo')]"))
							&& isDisplayed(By.xpath(".//*[normalize-space(text())='"
									+ SystemAdminLabels.ADOBE_SIGN_SANDBOX + "']//following::*[contains(text(),'"
									+ AdobeSignDescription.trim() + "')]")));
				}
			}

		}

		return false;
	}

	/**
	 * @author dharti.patel verify Service Option of DropDown
	 * @param serviceName
	 */
	public boolean verifyThirdPartyOption(String serviceName)
	{
		if (isDisplayed(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
				+ "']//following::*[contains(@class,'btn-group bootstrap-select form-control')])[1]")))
		{
			findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
					+ "']//following::*[contains(@class,'btn-group bootstrap-select form-control')])[1]")).click();

			return ((isDisplayed(By.xpath(
					"//*[contains(@class,'dropdown-menu inner') and @aria-expanded='true']//span[normalize-space(text())='"
							+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ONDEFAULT + "']"))
					|| isDisplayed(By.xpath(
							"//*[contains(@class,'dropdown-menu inner') and @aria-expanded='true']//span[normalize-space(text())='"
									+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFFDEFAULT + "']")))
					&& isDisplayed(By.xpath(
							"//*[contains(@class,'dropdown-menu inner') and @aria-expanded='true']//span[normalize-space(text())='"
									+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_ON + "']"))
					&& isDisplayed(By.xpath(
							"//*[contains(@class,'dropdown-menu inner') and @aria-expanded='true']//span[normalize-space(text())='"
									+ AdminLabels.SITE_ADMIN_FILE_THIRDPARTYSERVICE_OFF + "']")));
		}

		return false;
	}

	/**
	 * @author dharti.patel verify Selected Service Option of DropDown
	 * @param serviceName
	 * @param value
	 */
	public boolean verifySelectedValueOfService(String serviceName, String value)
	{
		findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
				+ "']//following::*[contains(@class,'btn-group bootstrap-select form-control')])[1]")).click();

		String option = findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
				+ "']//following::*[@class='selected']//a[@role='option' and @aria-selected='true']//span[normalize-space(text())='"
				+ value.trim() + "'])[1]")).getText();

		if (option.equals(value.trim()))
		{
			findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
					+ "']//following::*[contains(@class,'btn-group bootstrap-select form-control')])[1]")).click();
			return true;
		}
		return false;
	}

	/**
	 * @author dharti.patel Click on More Action option of Service
	 * @param serviceName
	 * @param option
	 */
	public void clickOnMoreActionOptionOfService(String serviceName, String option)
	{
		findVisibleElement(By.xpath("(.//*[normalize-space(text())='" + serviceName.trim()
				+ "']//preceding::a[@data-original-title='" + AdminLabels.UI_TEXT_MOREACTIONS + "'])[1]")).click();
		findVisibleElement(By.xpath(".//*[@data-original-title='" + AdminLabels.UI_TEXT_MOREACTIONS
				+ "' and @aria-expanded='true']//following::*[normalize-space(text())='" + option.trim() + "']"))
						.click();

	}

	/**
	 * @author dharti.patel Verify Authorised on Servcie
	 * @param serviceName
	 */
	public boolean verifyAuthorisedService(String serviceName)
	{

		findVisibleElement(locThridpartyDiv, Speed.slow);
		if (isDisplayed(By.xpath(".//*[@id='thirdPartyServiceMoreActionDivForSite']//*[normalize-space(text())='"
				+ serviceName.trim() + "']"), Speed.slow))
		{
			return isDisplayed(By.xpath(".//*[@id='thirdPartyServiceMoreActionDivForSite']//*[normalize-space(text())='"
					+ serviceName.trim() + "']//following::*[normalize-space(text())='"
					+ SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_AUTHORISEDLABEL + "']"));
		}

		return false;
	}

	/**
	 * @author dharti.patel Authorised DocuSign Service
	 * @param serviceName
	 * @param clientEmail
	 * @param clientPassword
	 */
	public boolean authoriseDocuSignService(String serviceName, String clientEmail, String clientPassword) throws InterruptedException
	{
		Thread.sleep(1000);
		int size = driver.getWindowHandles().size();

		if (size == 1)
		{
			return verifyAuthorisedService(serviceName);

		}
		else
		{
			while (driver.getWindowHandles().size() <= 1)
			{
				;
			}
			switchWindow();

			if (isDisplayed(locUserName, Speed.slow))
			{
				findVisibleElement(locUserName, Speed.slow).sendKeys(clientEmail);
				findVisibleElement(locSubmit, Speed.slow).click();
				findVisibleElement(locPassword, Speed.slow).sendKeys(clientPassword);
				findVisibleElement(locSubmit, Speed.slow).click();
			}

			while (driver.getWindowHandles().size() > 1)
			{
				;
			}
			if (driver.getWindowHandles().size() == 1)
			{
				driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
				return true;
			}
			return false;
		}

	}

	/**
	 * @author dharti.patel Click on Revoke Button of Revoke Model Window
	 */
	public void clickOnRevokeButtonOnRevokeModel()
	{
		findVisibleElement(By.xpath(".//*[normalize-space(text())='" + AdminLabels.REVOKE_AUTHORISATION_LABEL + "']"));
		findVisibleElement(locRevokebtn, Speed.slow).click();
	}

	/**
	 * @author dharti.patel Verify Revoke Model Window
	 */
	public boolean verifyRevokeWindowModel(String serviceName)
	{
		return (isDisplayed(By.xpath(".//*[normalize-space(text())='" + AdminLabels.REVOKE_AUTHORISATION_LABEL + "']"))
				&& isDisplayed(locMainCloseBtnOnRevokeModel)
				&& isDisplayed(By.xpath(
						".//*[@id='system_admin_third_party_service_revoke_id_BODY'][normalize-space(text())='Are you sure you want to revoke the authorisation for "
								+ serviceName.trim()
								+ "? All documents sent for signing with this service will be revoked.']"))
				&& isDisplayed(locCloseBtnRevokeModel));
	}

	/**
	 * This addEmailIDSiteInbox() is used to add email for Site admin property
	 *
	 * @author paras.vankadi
	 * @param toEmail
	 * @throws IOException
	 */
	@Override

	public void addEmailIDSiteInbox(String toEmail) throws IOException
	{
		WebElement inputBox = findVisibleElement(addEmailIDSiteInbox);
		inputBox.clear();
		inputBox.sendKeys(toEmail);
	}

	/**
	 * Set enable OpenOffice Online for Site admin property
	 *
	 * @author paras.vankadi
	 * @param state
	 *        ON/OFF
	 */
	@Override

	public void enableOpenOfficeOnline(boolean state)
	{
		setSelection(enableOpenInOfficeCheckBox, state);
	}

	/**
	 * verify Site Admin Open Office Online
	 *
	 * @author paras.vankadi
	 */
	@Override

	public boolean verifySiteAdminOpenOfficeOnline()
	{
		return isDisplayed(enableOpenInOfficeOnlineForSiteAdmin);

	}

	/**
	 * verify CheckBox Site Admin Open Office Online
	 *
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyCheckBoxSiteAdminOpenOfficeOnline()
	{

		return findVisibleElement(enableOpenInOfficeCheckBox, Speed.slow).isSelected();

	}

	/**
	 * Set enable Site Index
	 *
	 * @author paras.vankadi
	 * @param state
	 */
	@Override
	public void setEnableSiteIndex(boolean state) throws IOException
	{

		setSelection(enableSiteInbox, state);

	}

	/**
	 * This method is used to select a project from Project dropdown in Manage modal
	 * of KIRA engine
	 *
	 * @author janki.hirani created date: 14/05/2018
	 * @param projectName
	 */
	public void selectProjectFromManageModal(String projectName)
	{
		findVisibleElement(engineServiceModal, Speed.slow, 5).click();
		findClickableElement(
				By.xpath("//*[@id='selectedEngineProject']/*[normalize-space(.)='" + projectName.trim() + "']"))
						.click();
	}

	/**
	 * This method is used to click on save button in Manage modal of KIRA engine
	 *
	 * @author janki.hirani created date: 14/05/2018
	 */
	public void clickOnSaveInManageModal()
	{
		findClickableElement(engineModalSave).click();
		findPresentElement(modalClosed);
	}

	/**
	 * Enable Status
	 *
	 * @author dharti.patel
	 */
	public void enableStatus(boolean value)
	{
		WebElement locCheck = findVisibleElement(locEnableStatus, Speed.slow);
		if (value)
		{
			if (!locCheck.isSelected())
			{
				locCheck.click();
			}

		}
		else
		{
			if (locCheck.isSelected())
			{
				locCheck.click();
			}

		}
	}

	/**
	 * Verify file option checkbox is selected or not
	 *
	 * @param optionToCheck
	 *        visible text of the checkbox element(exact text is needed)
	 * @author dheeraj.rajput
	 * @return true if checkbox is selected
	 * @Created 23 May 2018
	 * @Updated
	 */
	public boolean verifyFileOptionCheckboxIsSelected(String optionToCheck)
	{
		findVisibleElement(middlePanel);
		By fileOption = By
				.xpath("(//*[normalize-space(.)='" + optionToCheck.trim() + "'])[last()]/*[@type='checkbox']");
		return isSelected(fileOption);
	}

	/**
	 * Set Enable Document review workflow
	 * 
	 * @param value
	 * @author dharti.patel
	 */
	public void setEnabelDocumentReviewWorkflow(boolean value)
	{
		setSelection(advancedOptions_EnableDocReviewCheckbox, value);
	}

	@Override
	public boolean verifydocumentAnalysisEngineDisplay(String engine)
	{
		By docEngine = By
				.xpath(".//*[@id='engineServiceMoreActionDiv']//following-sibling::*//*[normalize-space(text())='"
						+ engine + "']");
		return isDisplayed(docEngine);
	}

}
