package com.highq.pageobjects.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.AdminLabels;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.pageobjects.base.AspConfigurationPage;

public class AspConfigurationWeb extends BannerPageWeb implements AspConfigurationPage
{

	By exportOptionLabel = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_EXPORTOPTION + "']");
	By exportOptionList = By.id("CUSTOM_EXPORT_OPTION");
	By exportOptionCheckBox = By.id("EXPORT_OPTION");
	// By saveButtonLink =
	// By.linkText(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_SAVE);
	By saveButtonLink = By.xpath(".//*[@title='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_SAVE + "']");
	By checkoutEnableLabel = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_CHECKOUTENABLE + "']");
	By checkoutEnableList = By.id("CUSTOM_CHECKOUT_ENABLE");
	By checkoutEnableCheckbox = By.id("CHECKOUT_ENABLE");
	By configSaveMessage = By.id("messageTxt");
	By enableCommentingForAllSitesLabel = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLECOMMENTINGFORALLSITE + "']");
	By enableCommentingForAllSitesList = By.id("CUSTOM_ENABLE_COMMENTING_FOR_ALL_SITE");
	By enableCommentingForAllSitesCheckbox = By.id("ENABLE_COMMENTING_FOR_ALL_SITE");

	By enableMyFilesLabel = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEMYFILES + "']");
	By enableMyFilesDropDownList = By.id("CUSTOM_ASP_PERSONAL_FILES");
	By enableMyFilesCheckbox = By.id("ASP_PERSONAL_FILES");

	By enableMyFilesSharingLabel = By.xpath(
			"//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEMYFILESSHARING + "']");
	By enableMyFilesSharingDropDownList = By.id("CUSTOM_ASP_ENABLE_PERSONAL_FILES_SHARING");
	By enableMyFilesSharingCheckbox = By.id("ASP_ENABLE_PERSONAL_FILES_SHARING");

	By enableAdeptolPageCountLabel = By.xpath(
			"//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEADEPTOLPAGECOUNT + "']");
	By enableAdeptolPageCountList = By.id("CUSTOM_ENABLE_ADEPTOL_PAGE_COUNT");
	By enableAdeptolPageCountCheckbox = By.id("ENABLE_ADEPTOL_PAGE_COUNT");

	By rssFeedSecurityCheckBox = By.id("RSSFEED_SECURITY_ENABLE");

	By rssFeedSecurityDropDown = By.id("CUSTOM_RSSFEED_SECURITY_ENABLE");

	By locDrpdwnEnableIsheetVersionAudit = By.id("CUSTOM_ENABLE_ISHEET_ITEM_VERSION_AUDIT");
	By locChkbxEnableIsheetVersionAudit = By.id("ENABLE_ISHEET_ITEM_VERSION_AUDIT");

	By xssDirectory = By.id("XSSDIRECTORY_RESTRICTEDWORD_REGEXP");

	By enableDocumentReviewLabel = By.xpath(
			"//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEDOCUMENTREVIEW + "']");
	By enableDocumentReviewList = By.id("CUSTOM_ENABLE_DOCUMENT_REVIEW");
	By enableDocumentReviewCheckbox = By.id("ENABLE_DOCUMENT_REVIEW");

	By wikiModuleEnable = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_WIKIMODULEENABLE + "']");
	By wikiModuleEnableList = By.id("CUSTOM_GRIFFIN_WIKI_ENABLED");
	By wikiModuleEnableCheckBox = By.id("GRIFFIN_WIKI_ENABLED");

	By blogModuleEnable = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_BLOGMODULEENABLE + "']");
	By blogModuleEnableList = By.id("CUSTOM_GRIFFIN_BLOG_ENABLED");
	By blogModuleEnableCheckBox = By.id("GRIFFIN_BLOG_ENABLED");

	By taskModuleEnable = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_TASKMODULEENABLE + "']");
	By taskModuleEnableList = By.id("CUSTOM_GRIFFIN_TASKS_ENABLED");
	By taskModuleEnableCheckBox = By.id("GRIFFIN_TASKS_ENABLED");

	By eventModuleEnable = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_EVENTMODULEENABLE + "']");
	By eventModuleEnableList = By.id("CUSTOM_GRIFFIN_EVENTS_ENABLED");
	By eventModuleEnableCheckBox = By.id("GRIFFIN_EVENTS_ENABLED");

	By qaModuleEnable = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_QAMODULEENABLE + "']");
	By qaModuleEnableList = By.id("CUSTOM_GRIFFIN_QA_ENABLED");
	By qaModuleEnableCheckBox = By.id("GRIFFIN_QA_ENABLED");

	By isheetModuleEnable = By.xpath(
			"//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ISHEETMODULEENABLE + "']");
	By isheetModuleEnableList = By.id("CUSTOM_GRIFFIN_ISHEET_ENABLED");
	By isheetModuleEnableCheckBox = By.id("GRIFFIN_ISHEET_ENABLED");

	By bulkDownloadWithPipeStreamLabel = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_BULKDOWNLOADWITHPIPESTREAM + "']");
	By bulkDownloadWithPipeStreamList = By.id("CUSTOM_BULK_DOWNLOAD_WITH_PIPE_STREAM");
	By bulkDownloadWithPipeStreamCheckbox = By.id("BULK_DOWNLOAD_WITH_PIPE_STREAM");

	By ddAndContractAutomationReportLabel = By
			.xpath("//td[normalize-space(text())='DD and contract automation report']");
	By ddAndContractAutomationReportList = By.id("CUSTOM_DD_AND_CONTRACT_AUTOMATION_REPORT");
	By ddAndContractAutomationReportCheckbox = By.id("DD_AND_CONTRACT_AUTOMATION_REPORT");

	By locChkbxEnableContactUslink = By.id("GRIFFIN_CONTACTUSLINK");
	By locTextEnableContactUslink = By.id("CUSTOM_GRIFFIN_CONTACTUSLINK");
	By locChkbxEnableResetPasswordVerificationDetail = By.id("RESET_PASSWORD_VERIFICATION_DETAIL_REQUIRED");
	By locDrpdwnEnableResetPasswordVerificationDetail = By.id("CUSTOM_RESET_PASSWORD_VERIFICATION_DETAIL_REQUIRED");
	By locChkbxEnableUserId = By.id("ENABLE_USER_ID");
	By locDrpdwnEnableUserId = By.id("CUSTOM_ENABLE_USER_ID");

	By highqEngineDropdown = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_HIGHQ']");

	By kiraDropdown = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_KIRA']");

	By levertonDropdown = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_LEVERTON']");

	By highqEngineDefault = By
			.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_HIGHQ']/option[@value='FALSE' and @selected='selected']");

	By highqEngineNonDefault = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_HIGHQ']/option[@value='TRUE']");

	By kiraEngineDefault = By
			.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_KIRA']/option[@value='FALSE' and @selected='selected']");

	By kiraEngineNonDefault = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_KIRA']/option[@value='TRUE']");

	By ravanEngineDefault = By
			.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_RAVAN']/option[@value='FALSE' and @selected='selected']");

	By ravanEngineNonDefault = By.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_RAVAN']/option[@value='TRUE']");

	By luminanceEngineDefault = By.xpath(
			".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_LUMINANCE']/option[@value='FALSE' and @selected='selected']");

	By luminanceEngineNonDefault = By
			.xpath(".//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_LUMINANCE']/option[@value='TRUE']");

	By aiTrainingDefault = By
			.xpath(".//*[@id='CUSTOM_ENABLE_AI_TRAINING']/option[@value='FALSE' and @selected='selected']");

	By aiTrainingNonDefault = By.xpath(".//*[@id='CUSTOM_ENABLE_AI_TRAINING']/option[@value='TRUE']");

	By documentAnalysisCronExpressionTextbox = By.xpath(".//*[@id='CUSTOM_CRON_EXPRESSION_DOCUMENT_ANALYSIS']");

	By noOfDocsTextbox = By.id("CUSTOM_DOCUMENT_ANALYSIS_COUNT");

	By enableDocumentAnalysisByHighqCheckBox = By.id("ENABLE_DOCUMENT_ANALYSIS_HIGHQ");
	By enableDocumentAnalysisByKiraCheckBox = By.id("ENABLE_DOCUMENT_ANALYSIS_KIRA");
	By enableDocumentAnalysisByLevertonCheckBox = By.id("ENABLE_DOCUMENT_ANALYSIS_LEVERTON");
	By enableAITrainingCheckBox = By.id("ENABLE_AI_TRAINING");
	By documentAnalysisCronExpressionCheckBox = By.id("CRON_EXPRESSION_DOCUMENT_ANALYSIS");
	By noOfDocsCheckbox = By.id("DOCUMENT_ANALYSIS_COUNT");

	By enableDocumentAnalysisByHighQLbl = By.xpath("//*[normalize-space(text())='Enable document analysis by HighQ']");
	By enableDocumentAnalysisByKiraLbl = By.xpath("//*[normalize-space(text())='Enable document analysis by KIRA']");
	By enableDocumentAnalysisByLevertonLbl = By
			.xpath("//*[normalize-space(text())='Enable document analysis by Leverton']");

	By enableAITrainingLbl = By.xpath("//*[normalize-space(text())='Enable AI training']");
	By enableAITrainingDropdown = By.xpath(".//*[@id='CUSTOM_ENABLE_AI_TRAINING']");

	By emailPrefix = By.id("CUSTOM_EMAIL_SUBJECT_PREFIX");

	By taskBeforeOneDayTriggerCronExpression = By
			.id("CUSTOM_CRON_EXPRESSION_ALERTER_TASKBEFOREONEDAYTRIGGER_CRONEXPRESSION");

	By enableDocumentAnalysisByHighqLabel = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYHIGHQ + "']");
	By enableDocumentAnalysisByHighqList = By.id("CUSTOM_ENABLE_DOCUMENT_ANALYSIS_HIGHQ");

	By enableDocumentAnalysisByKiraLabel = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYKIRA + "']");
	By enableDocumentAnalysisByKiraList = By.id("CUSTOM_ENABLE_DOCUMENT_ANALYSIS_KIRA");

	By enableDocumentAnalysisByLevertonLabel = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYLEVERTON + "']");
	By enableDocumentAnalysisByLevertonList = By.id("CUSTOM_ENABLE_DOCUMENT_ANALYSIS_LEVERTON");

	By siteEmailAlertDropDownButton = By.id("CUSTOM_SITE_EMAIL_ALERTS_DEFAULT");
	By optionListOfsiteEmailAlertDropDown = By.xpath(".//*[@id='CUSTOM_SITE_EMAIL_ALERTS_DEFAULT']");
	By locchkbnEnableSiteEmailAlert = By.id("SITE_EMAIL_ALERTS_DEFAULT");
	By locDrpdwnEnableSiteEmailAlert = By.id("CUSTOM_SITE_EMAIL_ALERTS_DEFAULT");

	By lableEnableAPI = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEAPI + "']");
	By locDrpdwnEnableAPI = By.id("CUSTOM_ENABLE_API_APPLICATION_REGISTRATION");
	By locChkbxEnableAPI = By.id("ENABLE_API_APPLICATION_REGISTRATION");
	By locInputbxAppBaseURL = By.id("CUSTOM_APP_BASE_URL");
	By locChkbxAppBaseURL = By.id("APP_BASE_URL");
	By lableESignatureService = By.xpath("//*[@class='pull-left' and normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE + "']");
	By lableDocuSignSandbox = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX + "']");
	By locDrpdwnDocuSignSandbox = By.id("CUSTOM_DOCU_SIGN_SANDBOX");
	By optionDocuSignSandboxTRUE = By.xpath("//*[@id='CUSTOM_DOCU_SIGN_SANDBOX']/option[@value='TRUE']");
	By optionDocuSignSandboxFALSE = By.xpath("//*[@id='CUSTOM_DOCU_SIGN_SANDBOX']/option[@value='FALSE']");
	By trueFalsedocusignservicesCheckbox = By.id("DOCU_SIGN_SANDBOX");

	By linkConfigureDocuSignSanbox = By.xpath("(//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "'])[1]");
	By labelConfigureNameDocuSignSandbox = By.xpath("//*[@class='modalheaderSubTitle' and normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX + "']");
	By imageConfigureModelCloseWindow = By.id("system_admin_third_party_service_configure_id_MAIN_CLOSE_BUTTON");
	By labelClientId = By.xpath("//*[@class='control-label' and contains(text(),'"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENTID + "')]");
	By labelClientSecretKey = By.xpath("//*[@class='control-label' and contains(text(),'"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENT_SECRETE_KEY + "')]");
	By locCancelButtonOfConfigureWindow = By.id("system_admin_third_party_service_configure_id_close");
	By configurationModalWindowOpened = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id' and contains(@style,'display: block;')]");
	By configurationModalWindowClosed = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id' and contains(@style,'display: none;')]");
	By locClientIDInputbxOfConfigureWindow = By.id("thirdPartyClientId");
	By locClientSecreteKeyInputbxOfConfigureWindow = By.id("thirdPartyClientSceretKey");
	By locTestBtnConfigureWindow = By.id("system_admin_third_party_service_configure_id_test");
	By labelConfigureServiceWindow = By.xpath("//*[@class='modal-title']");

	By locDrpdwnDocuSignProduction = By.id("CUSTOM_DOCU_SIGN_PRODUCTION");
	By optionDocuSignProductionTRUE = By.xpath("//*[@id='CUSTOM_DOCU_SIGN_PRODUCTION']/option[@value='TRUE']");
	By optionDocuSignProductionFALSE = By.xpath("//*[@id='CUSTOM_DOCU_SIGN_PRODUCTION']/option[@value='FALSE']");
	By lableDocuSignProduction = By.xpath("//*[normalize-space(text())='"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_PRODUCTION + "']");

	By locSuccessMsgConfigureService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[@class='alert alert-success']");
	By successMsgCongurationService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'alert-success')]//*[@id='successElementContainer']");
	By locSaveBtnConfigurationService = By.id("system_admin_third_party_service_configure_id_save");
	By locErrorMsgConfigureService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'errormsgbox')]");
	By invalidSecretKey = By.id("thirdParty_Clientsceretkey");
	By invalidClientId = By.id("thirdParty_ClientId");
	By locErrorMsgClientid = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'errormsgbox')]//*[@id='successElementContainer']");

	By chkBoxOfDocusign = By.id("DOCU_SIGN_SANDBOX");
	By thirdPartyDisabledModalId = By.id("THIRD_PARTY_DISABLED_ASP_MODAL_ID");

	By locContainterOfConfigureWindowModel = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id' and contains(@style,'display: block;')]");

	By locUserName = By.xpath(".//*[@id='username']");
	By locSubmit = By.xpath(".//*[@type='submit']");
	By locPassword = By.xpath(".//*[@id='password']");

	By globalMessageOnAspAdmin = By.xpath(".//*[@class='animatePopup_close']");
	By globalMessageBarCloseLink = By
			.xpath(".//*[@id='globalAlertMessageContainer']//*[@data-original-title='Remove']");
	By enableOpenPdfDocumentdirectlyLabel = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.OPEN_PDF_DOCUMENT_DIRECTLY + "']");
	By enableOpenPdfDocumentDirectlyList = By.id("CUSTOM_OPEN_PDF_DOCUMENT_DIRECTLY");
	By enableOpenPdfDocumentDirectlyCheckbox = By.id("OPEN_PDF_DOCUMENT_DIRECTLY");

	Pattern pattern = Pattern.compile(".?\\*+.?");

	By editInOfficeLockTimeOutCheckTime = By
			.xpath("//*[normalize-space(text())='Edit in Office lock timeout check time (in minutes)']");
	By enableOpenInOfficeOnline = By.xpath("//*[normalize-space(text())='Enable Open in Office Online']");

	By enableOpneINOfficeOnlineONOFF = By.id("CUSTOM_ENABLE_OPEN_IN_OFFICE_ONLINE_FOR_FILES");
	By enableOpneINOfficeOnlineCheckbox = By.id("ENABLE_OPEN_IN_OFFICE_ONLINE_FOR_FILES");
	By enable = By.xpath("//*[normalize-space(text())='Edit in Office lock timeout check time (in minutes)']");

	By checkOfficeOnlineTokenExpirationTime = By
			.xpath("//*[normalize-space(text())='Office online token expiration time (in minutes)']");
	By addOfficeOnlineTokenExpirationTime = By.id("CUSTOM_TTL_FOR_EDITING_SESSION");
	By checkBoxEnableForOfficeTimePeriod = By.id("TTL_FOR_EDITING_SESSION");
	By locGlobalMsg = By.id("animatedMessageBox");
	By locGlobalClose = By.xpath(".//*[@class='animatePopup_close']");

	By locDisableDocuSignClose = By.id("THIRD_PARTY_DISABLED_ASP_MODAL_ID_close");
	By locDisableDocuSignBody = By.id("THIRD_PARTY_DISABLED_ASP_MODAL_ID_BODY");

	By enablePrivacyPolicyLabel = By.xpath(".//*[text()='Enable privacy policy']");
	By enablePrivacyPolicyDropDown = By.id("CUSTOM_ENABLE_PRIVACY_POLICY");
	By enablePrivacyPolicyCheckbox = By.id("ENABLE_PRIVACY_POLICY");

	/*
	 * Added by Anil.Sikhwal
	 */

	By enableDocumentAnalysisDropDown = By.id("CUSTOM_ENABLE_DOCUMENT_ANALYSIS");
	By enableDocumentAnalysisCheckbox = By.id("ENABLE_DOCUMENT_ANALYSIS");

	By enableCustomCronExprsnDocAnalysis = By.id("CUSTOM_CRON_EXPRESSION_DOCUMENT_ANALYSIS");
	By enableCustomCronExprsnDocAnalysisCheckbox = By.id("CRON_EXPRESSION_DOCUMENT_ANALYSIS");
	By invalidCronExprsnTextMsg = By.xpath("//*[@class='alert alert-danger']");

	By changesSavedTextMsg = By.id("messageTxt");

	By checkboxForTaskBeforeOneDayTriggerIconExpression = By.id("CRON_EXPRESSION_ALERTER_TASKBEFOREONEDAYTRIGGER_CRONEXPRESSION");

	By checkboxOfDefaultContactUsLink = By.id("GRIFFIN_CONTACTUSLINK");

	public AspConfigurationWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void setExportOption(String optionValue) throws InterruptedException
	{
		Select exportOptionsList = new Select(findVisibleElement(exportOptionList));
		String selectedValue = exportOptionsList.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement exportOptionChkBox = findClickableElement(exportOptionCheckBox);
			if (exportOptionChkBox.isSelected())
			{
				exportOptionChkBox.click();
			}
			exportOptionsList.selectByVisibleText(optionValue.trim());

			exportOptionChkBox.click();

		}

	}

	@Override
	public void saveConfigurations() throws InterruptedException
	{
		// findVisibleElement(configurationModalWindowOpened).click();
		WebElement saveButton = findVisibleElement(saveButtonLink, Speed.slow);
		saveButton.click();
	}

	@Override
	public void setCheckoutEnableOption(String optionValue) throws InterruptedException
	{
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(checkoutEnableList));
		String selectedValue = checkoutEnableListDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement checkoutEnableChkbox = findClickableElement(checkoutEnableCheckbox);
			if (checkoutEnableChkbox.isSelected())
			{
				checkoutEnableChkbox.click();
			}
			checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim());
			checkoutEnableChkbox.click();
		}
	}

	@Override
	public void selectConfiguration(By configurationName, By configurationDropDown, By configurationCheckbox,
			String optionValue)
	{
		Select configurationDrpDwn = new Select(findVisibleElement(configurationDropDown, Speed.slow));
		String selectedValue = configurationDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement configurationChkbox = findVisibleElement(configurationCheckbox, Speed.slow);
			if (configurationChkbox.isSelected())
			{
				configurationChkbox.click();
			}
			configurationDrpDwn.selectByVisibleText(optionValue.trim());
			// configurationChkbox.click();
		}
	}

	@Override
	public void enableCommentingForAllSites(String optionToSelect)
	{
		selectConfiguration(enableCommentingForAllSitesLabel, enableCommentingForAllSitesList,
				enableCommentingForAllSitesCheckbox, optionToSelect);
	}

	@Override
	public void enableAdeptolPageCount(String optionToSelect)
	{
		selectConfiguration(enableAdeptolPageCountLabel, enableAdeptolPageCountList, enableAdeptolPageCountCheckbox,
				optionToSelect);
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @param permission
	 *        To set my files ON/OFF
	 * @creation date 18/04/2018
	 */
	@Override
	public void enableMyFiles(String optionToSelect)
	{
		selectConfiguration(enableMyFilesLabel, enableMyFilesDropDownList, enableMyFilesCheckbox, optionToSelect);
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @param permission
	 *        To set Enable Myfiles sharing ON/OFF
	 * @creation date 18/04/2018
	 */

	@Override
	public void enableMyFilesSharing(String optionToSelect)
	{
		selectConfiguration(enableMyFilesSharingLabel, enableMyFilesSharingDropDownList, enableMyFilesSharingCheckbox,
				optionToSelect);
	}

	/**
	 * @author vivek.mishra
	 * @param permission
	 *        To set RSS feed security permission
	 * @creation date 12/02/2018
	 */
	@Override
	public void setRssFeedSecurity(String permission)
	{
		permission = permission.toUpperCase();
		WebElement chkBox = findVisibleElement(rssFeedSecurityCheckBox, Speed.slow);
		if (permission.equals("TRUE"))
		{
			if (!chkBox.isSelected())
			{
				clickOnRssFeedSecurityCheckBox();
			}
		}
		else
		{
			if (chkBox.isSelected())
			{
				clickOnRssFeedSecurityCheckBox();
			}
			Select dropDown = new Select(findVisibleElement(rssFeedSecurityDropDown, Speed.slow));
			dropDown.selectByValue(permission);
		}

	}

	/**
	 * @author vivek.mishra To click on RSS feed security check box
	 * @creation date 12/02/2018
	 */
	@Override
	public void clickOnRssFeedSecurityCheckBox()
	{
		WebElement chkBox = findVisibleElement(rssFeedSecurityCheckBox, Speed.slow);
		chkBox.click();
	}

	/**
	 * @param optionValue
	 * @throws InterruptedException
	 * @author nidhi.shah
	 */
	@Override
	public void setEnableIsheetVersionAuditOption(String optionValue) throws InterruptedException
	{
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableIsheetVersionAudit));
		String selectedValue = checkoutEnableListDrpDwn.getFirstSelectedOption().getText().trim();
		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement checkoutEnableChkbox = findClickableElement(locChkbxEnableIsheetVersionAudit);
			if (checkoutEnableChkbox.isSelected())
			{
				checkoutEnableChkbox.click();
			}
			checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim());
			checkoutEnableChkbox.click();
		}
	}

	/**
	 * Enable Document Review and set its value TRUE/FALSE
	 * 
	 * @param optionToSelect
	 *        option to select->TRUE/FALSE
	 * @author dheeraj.rajput
	 * @Created: 02 April 2018
	 * @Updated:
	 */
	@Override
	public void enableDocumentReview(String optionToSelect)
	{
		selectConfiguration(enableDocumentReviewLabel, enableDocumentReviewList, enableDocumentReviewCheckbox,
				optionToSelect);
	}

	/**
	 * Set bulk download with pipestream option
	 * 
	 * @param optionToSelect
	 *        option to select->TRUE/FALSE
	 * @author dheeraj.rajput
	 * @Created: 18 April 2018
	 * @Updated:
	 */
	@Override
	public void setBulkDownloadWithPipeStream(String optionToSelect)
	{
		selectConfiguration(bulkDownloadWithPipeStreamLabel, bulkDownloadWithPipeStreamList,
				bulkDownloadWithPipeStreamCheckbox, optionToSelect);
	}

	public void setDefaultContactUsLink(boolean requiredState)
	{
		WebElement ele = findVisibleElement(checkboxOfDefaultContactUsLink, Speed.slow);
		boolean currentState = ele.isSelected();
		if (currentState != requiredState)
		{
			ele.click();
		}
	}

	/**
	 * Set given option of 'DD and contract automation report'
	 * 
	 * @param optionToSelect
	 * @author nidhi.shah
	 * @Created: 24-04-2018
	 * @Updated:
	 */
	@Override
	public void setDDAndContractAutomationReport(String optionToSelect) throws InterruptedException
	{
		selectConfiguration(ddAndContractAutomationReportLabel, ddAndContractAutomationReportList,
				ddAndContractAutomationReportCheckbox, optionToSelect);
	}

	/**
	 * @author ankit.motaval get Value from contact us link textbox
	 * @created on 09/04/2018
	 */
	public String getValueOfContactUsLink()
	{
		setSelection(locChkbxEnableContactUslink, false);
		return findVisibleElement(locTextEnableContactUslink, Speed.slow).getAttribute("value");
	}

	/**
	 * @param permission
	 *        permission true->false
	 * @author krishna.bhadani
	 */
	@Override
	public void setEnableXSSDirectoryRestrictedWord(boolean permission)
	{
		setSelection(xssDirectory, permission);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableWikiModule(String optionToSelect)
	{
		selectConfiguration(wikiModuleEnable, wikiModuleEnableList, wikiModuleEnableCheckBox, optionToSelect);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableBlogModule(String optionToSelect)
	{
		selectConfiguration(blogModuleEnable, blogModuleEnableList, blogModuleEnableCheckBox, optionToSelect);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableTaskModule(String optionToSelect)
	{
		selectConfiguration(wikiModuleEnable, wikiModuleEnableList, wikiModuleEnableCheckBox, optionToSelect);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableEventModule(String optionToSelect)
	{
		selectConfiguration(taskModuleEnable, taskModuleEnableList, taskModuleEnableCheckBox, optionToSelect);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableQAModule(String optionToSelect)
	{
		selectConfiguration(qaModuleEnable, qaModuleEnableList, qaModuleEnableCheckBox, optionToSelect);
	}

	/**
	 * @param optionToSelect
	 * @author ravi.pandit
	 */
	public void enableIsheetModule(String optionToSelect)
	{
		selectConfiguration(isheetModuleEnable, isheetModuleEnableList, isheetModuleEnableCheckBox, optionToSelect);
	}

	/*
	 * @author ankit.motaval
	 * @param optionValue Set Enable Reset password verification detail required
	 * @created on 18/04/2018
	 */
	public void setEnableResetPasswordVerificationDetail(String optionValue) throws InterruptedException
	{
		setSelection(locChkbxEnableResetPasswordVerificationDetail, false);
		Select checkoutEnableListDrpDwn = new Select(
				findVisibleElement(locDrpdwnEnableResetPasswordVerificationDetail, Speed.slow));
		checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim().toUpperCase());

	}

	/**
	 * @author ankit.motaval
	 * @param optionValue
	 *        Set Enable User Id
	 * @created on 18/04/2018
	 */
	public void setEnableUserId(String optionValue) throws InterruptedException
	{
		setSelection(locChkbxEnableUserId, false);
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableUserId, Speed.slow));
		checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim().toUpperCase());
	}

	/**
	 * enable Data Visualisation Panel
	 * 
	 * @param permission
	 * @author krishna.bhadani
	 */
	@Override
	public void enableDataVisualisationPanel(boolean permission)
	{
		Select source = new Select(findVisibleElement(By.xpath(".//*[@id='CUSTOM_ENABLE_DATA_VISUALISATION']")));
		source.selectByVisibleText(String.valueOf(permission).toUpperCase());
	}

	/**
	 * @author jyoti.raj
	 * @Created: 03 May 2018
	 * @Updated:
	 * @return
	 */
	public String getValueOfEmailSubjectPrefix()
	{
		return findVisibleElement(emailPrefix).getAttribute("value").trim();
	}

	@Override
	public void setNoOfDocsInJob(String value)
	{
		setSelection(noOfDocsCheckbox, false);
		WebElement textBox = findVisibleElement(noOfDocsTextbox, Speed.slow);
		textBox.clear();
		textBox.sendKeys(value);
	}

	/**
	 * @param value
	 * @author tejash.trivedi
	 */
	@Override
	public void setDocumentAnalysisCronExpression(String value)
	{
		setSelection(documentAnalysisCronExpressionCheckBox, false);
		WebElement textBox = findVisibleElement(documentAnalysisCronExpressionTextbox, Speed.slow);
		textBox.clear();
		textBox.sendKeys(value);
	}

	/**
	 * @param permission
	 *        permission true->false
	 * @author krishna.bhadani
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void setEnableDocumentAnalysisByHighQ(String permission) throws InterruptedException
	{
		selectConfiguration(enableDocumentAnalysisByHighQLbl, highqEngineDropdown,
				enableDocumentAnalysisByHighqCheckBox, permission);
	}

	/**
	 * @param permission
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void setEnableAITraining(String permission) throws InterruptedException
	{
		selectConfiguration(enableAITrainingLbl, enableAITrainingDropdown, enableAITrainingCheckBox, permission);
	}

	/**
	 * @param permission
	 * @author tejash.trivedi
	 * @throws InterruptedException
	 */
	@Override
	public void setEnableDocumentAnalysisByKira(String permission) throws InterruptedException
	{
		selectConfiguration(enableDocumentAnalysisByKiraLbl, kiraDropdown, enableDocumentAnalysisByKiraCheckBox,
				permission);
	}

	/**
	 * @param permission
	 * @author tejash.trivedi
	 * @throws InterruptedException
	 */
	@Override
	public void setEnableDocumentAnalysisByLeverton(String permission) throws InterruptedException
	{
		selectConfiguration(enableDocumentAnalysisByLevertonLbl, levertonDropdown,
				enableDocumentAnalysisByLevertonCheckBox, permission);
	}

	/**
	 * @param option
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnLeftPanelLabel(String option)
	{
		By search = By.xpath(".//*[@id='leftPanel']//*[text()='" + option.trim() + "']");
		WebElement labelName = findVisibleElement(search, Speed.slow);
		labelName.click();
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void resetEnableDocumentAnalysisByHighq() throws InterruptedException
	{
		WebElement elem = findClickableElement(enableDocumentAnalysisByHighqCheckBox);
		if (!elem.isSelected())
		{
			elem.click();
		}
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void resetEnableDocumentAnalysisByKira() throws InterruptedException
	{

		if (!findClickableElement(enableDocumentAnalysisByKiraCheckBox).isSelected())
		{
			findClickableElement(enableDocumentAnalysisByKiraCheckBox).click();
		}
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void resetEnableDocumentAnalysisByLeverton() throws InterruptedException
	{
		if (!findClickableElement(enableDocumentAnalysisByLevertonCheckBox).isSelected())
		{
			findClickableElement(enableDocumentAnalysisByLevertonCheckBox).click();
		}
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public void resetEnableAITraining() throws InterruptedException
	{
		if (!findClickableElement(enableAITrainingCheckBox).isSelected())
		{
			findClickableElement(enableAITrainingCheckBox).click();
		}
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override

	public void resetDocumentAnalysisCronExpression() throws InterruptedException
	{
		setSelection(documentAnalysisCronExpressionCheckBox, true);
	}

	/**
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override

	public void resetNoOfDocs() throws InterruptedException
	{
		setSelection(noOfDocsCheckbox, true);
	}

	/**
	 * @param option
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyLeftPanelLabel(String option)
	{
		By search = By.xpath(".//*[@id='leftPanel']//*[text()='" + option.trim() + "']");
		return isDisplayed(search);
	}

	/**
	 * @param option
	 * @return
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyRightPanelLabel(String option) throws InterruptedException
	{
		By search = By.xpath(".//*[@id='customizeForm']//*[text()='" + option.trim() + "']");
		return isDisplayed(search);
	}

	/**
	 * @return
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifySearchEngineValues() throws InterruptedException
	{
		resetEnableDocumentAnalysisByHighq();
		resetEnableDocumentAnalysisByKira();
		saveConfigurations();
		return isDisplayed(highqEngineDefault) && isDisplayed(highqEngineNonDefault) && isDisplayed(kiraEngineDefault)
				&& isDisplayed(kiraEngineNonDefault);
	}

	/**
	 * @return
	 * @throws InterruptedException
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyAITrainingValues() throws InterruptedException
	{
		return isDisplayed(aiTrainingDefault) && isDisplayed(aiTrainingNonDefault);
	}

	/**
	 * @param value
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyCronExpressionValue(String value)
	{
		String val = findPresentElement(documentAnalysisCronExpressionTextbox).getAttribute("value").trim();
		return val.equalsIgnoreCase(value);
	}

	/**
	 * @param value
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyNoOfDocsValue(String value)
	{
		String val = findPresentElement(noOfDocsTextbox).getAttribute("value").trim();
		return val.equalsIgnoreCase(value);
	}

	/**
	 * @param value
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyNoOfDocsValidationMessage(String value)
	{
		String val = driver.switchTo().alert().getText().trim();
		driver.switchTo().alert().accept();
		return val.equals(value.trim());
	}

	/**
	 * @param value
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyCronExpressionValidationMessage(String value)
	{
		By val = By.xpath(".//*[@id='customizeForm']//span[text()='" + value.trim() + "']");
		return isDisplayed(val);
	}

	/**
	 * @param message
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifySaveConfigurationMessage(String message)
	{
		String msg = findVisibleElement(configSaveMessage).getText().trim();
		return msg.equalsIgnoreCase(message);
	}

	/**
	 * @author jyoti.raj
	 * @Created: 04 May 2018
	 * @Updated:
	 * @param expression
	 */
	public void setTaskBeforeOneDayTriggerCronExpression(String expression)
	{
		if (isSelected(checkboxForTaskBeforeOneDayTriggerIconExpression))
		{
			findVisibleElement(checkboxForTaskBeforeOneDayTriggerIconExpression).click();
		}
		WebElement element = findVisibleElement(taskBeforeOneDayTriggerCronExpression);
		element.clear();
		element.sendKeys(expression);
	}

	/**
	 * @author dhaval.modi select option From enable document Analysis
	 * @creation date 20/04/2018
	 * @param value
	 */
	public void enableDocumentAnalysisByLeverton(String value)
	{
		selectConfiguration(enableDocumentAnalysisByLevertonLabel, enableDocumentAnalysisByLevertonList,
				enableDocumentAnalysisByLevertonCheckBox, value);
	}

	/**
	 * @author dhaval.modi select option From enable document Analysis
	 * @creation date 20/04/2018
	 * @param value
	 */
	public void enableDocumentAnalysisByHighQ(String value)
	{
		selectConfiguration(enableDocumentAnalysisByHighqLabel, enableDocumentAnalysisByHighqList,
				enableDocumentAnalysisByHighqCheckBox, value);
	}

	/**
	 * @author dhaval.modi select option From enable document Analysis
	 * @creation date 20/04/2018
	 * @param value
	 */
	public void enableDocumentAnalysisByKira(String value)
	{
		selectConfiguration(enableDocumentAnalysisByKiraLabel, enableDocumentAnalysisByKiraList,
				enableDocumentAnalysisByKiraCheckBox, value);
	}

	/**
	 * @author hetal.thakkar verify site email alert option from DropDown
	 * @created on 09/05/2018
	 */
	@Override
	public boolean verifySiteEmailAlertOptionFromDropDown(String option)
	{
		WebElement ele = driver.findElement(optionListOfsiteEmailAlertDropDown);
		List<WebElement> x = ele.findElements(By.tagName("option"));
		String str = "";
		for (WebElement ele1 : x)
		{
			str = ele1.getAttribute("innerHTML");
			if (str.contains(option.trim()))
			{
				return true;
			}

		}
		return false;
	}

	/**
	 * @author hetal.thakkar set site email alerts
	 * @created on 09/05/2018
	 */
	@Override
	public void setSiteEmailalerts(String optionValue)
	{
		Select checkoutEmailAlertListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableSiteEmailAlert));
		String selectedValue = checkoutEmailAlertListDrpDwn.getFirstSelectedOption().getText().trim();
		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement checkoutEnableChkbox = findClickableElement(locchkbnEnableSiteEmailAlert, Speed.slow);
			if (optionValue.equalsIgnoreCase("Daily"))
			{
				if (!checkoutEnableChkbox.isSelected())
				{
					checkoutEnableChkbox.click();
				}
			}
			else
			{
				if (checkoutEnableChkbox.isSelected())
				{
					checkoutEnableChkbox.click();
				}
				checkoutEmailAlertListDrpDwn.selectByVisibleText(optionValue.trim());
			}
		}
	}

	/**
	 * @author hetal.thakkar check is site email alert dropdown enable
	 * @created on 09/05/2018
	 */
	@Override
	public boolean isSiteEmailAlertDropdownEnable()
	{
		return findPresentElement(locDrpdwnEnableSiteEmailAlert, Speed.slow).isEnabled();
	}

	/**
	 * @author hetal.thakkar check is site email alert dropdown value selected
	 * @created on 09/05/2018
	 */
	@Override
	public boolean isSiteEmailAlertDropdownValueSelected(String option)
	{
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableSiteEmailAlert, Speed.slow));
		WebElement selectedElement = checkoutEnableListDrpDwn.getFirstSelectedOption();
		String value = selectedElement.getText();
		return value.trim().equals(option.trim());

	}

	/**
	 * Method to set value of 'Enable API'
	 * 
	 * @param optionValue
	 * @throws InterruptedException
	 * @author dharti.patel
	 * @creation date 09/04/2018
	 */

	@Override
	public void enableAPIOption(String optionValue)
	{
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableAPI));
		String selectedValue = checkoutEnableListDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement checkoutEnableChkbox = findClickableElement(locChkbxEnableAPI);
			if (checkoutEnableChkbox.isSelected())
			{
				checkoutEnableChkbox.click();
			}
			checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim());
			checkoutEnableChkbox.click();
		}

	}

	/**
	 * 'Set App Base URL'
	 * 
	 * @param status,appURL
	 * @author dharti.patel
	 * @creation date 09/04/2018
	 */
	@Override
	public void setAppBaseURL(boolean status, String appURL)
	{
		clickOnAppBaseURLCheckBox(status);
		WebElement inputbxAppBaseURL = findVisibleElement(locInputbxAppBaseURL, Speed.slow);
		inputbxAppBaseURL.clear();
		String url = appURL.split(".com")[0];
		url = url.concat(".com/");
		inputbxAppBaseURL.sendKeys(url);

	}

	/**
	 * 'Click on App Base URL Checkbox'
	 * 
	 * @param status
	 * @author dharti.patel
	 * @creation date 09/04/2018
	 */
	@Override
	public void clickOnAppBaseURLCheckBox(boolean status)
	{
		WebElement checkoutEnableChkbox = findVisibleElement(locChkbxAppBaseURL, Speed.slow);
		if (checkoutEnableChkbox.isSelected() == status)
			checkoutEnableChkbox.click();
	}

	/**
	 * 'Click on eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public void clickOnESignatureServices()
	{
		WebElement clickOnESignature = findVisibleElement(lableESignatureService, Speed.slow);
		clickOnESignature.click();
	}

	/**
	 * 'Verify DocuSign (sandbox) Label of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyDocuSignSandboxServiceLabel()
	{
		return isDisplayed(lableDocuSignSandbox, Speed.slow);
	}

	/**
	 * 'Verify DocuSign (sandbox) DropDown Option TRUE/FLASE of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyDocuSignSandboxServiceDropDownOption()
	{
		WebElement drpDwnDocuSignSandbox = findVisibleElement(locDrpdwnDocuSignSandbox, Speed.slow);
		drpDwnDocuSignSandbox.click();

		Select options = new Select(drpDwnDocuSignSandbox);
		if (options.getOptions().size() == 2)
		{
			WebElement optionTRUEDocuSignSandbox = findVisibleElement(optionDocuSignSandboxTRUE, Speed.slow);
			WebElement optionFALSEDocuSignSandbox = findVisibleElement(optionDocuSignSandboxFALSE, Speed.slow);

			return (optionTRUEDocuSignSandbox.isDisplayed() && optionFALSEDocuSignSandbox.isDisplayed());
		}
		return false;

	}

	/**
	 * Method to verify 'Configure' Link of given service name
	 * 
	 * @param serviceName
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyConfigureLinkForService(String serviceName)
	{
		return isDisplayed(By.xpath("(//*[normalize-space(text())='" + serviceName.trim()
				+ "']//following::*[normalize-space(text())='"
				+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "'])[1]"),
				Speed.slow);
	}

	/**
	 * 'Verify DocuSign (production) Label of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyDocuSignProductionServiceLabel()
	{
		return isDisplayed(lableDocuSignProduction, Speed.slow);
	}

	/**
	 * <<<<<<< HEAD
	 * 'Verify DocuSign (production) DropDown Option TRUE/FLASE of eSignature Service'
	 * =======
	 * 'Verify DocuSign (production) DropDown Option TRUE/FLASE of eSignature
	 * Service'
	 * >>>>>>> 69088bbd249e5e34ddb5560e944da4158ce82234
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyDocuSignProductionServiceDropDownOption()
	{
		WebElement drpDwnDocuSignProduction = findVisibleElement(locDrpdwnDocuSignProduction, Speed.slow);
		drpDwnDocuSignProduction.click();

		Select options = new Select(drpDwnDocuSignProduction);
		if (options.getOptions().size() == 2)
		{

			WebElement optionTRUEDocuSignProduction = findVisibleElement(optionDocuSignProductionTRUE, Speed.slow);
			WebElement optionFALSEDocuSignProduction = findVisibleElement(optionDocuSignProductionFALSE, Speed.slow);

			return (optionTRUEDocuSignProduction.isDisplayed() && optionFALSEDocuSignProduction.isDisplayed());
		}
		return false;
	}

	/**
	 * 'Verify Configure window Label of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyConfigureWindowLabelOfESignatureService(String servicelabel)
	{
		String configureLabel = findVisibleElement(labelConfigureServiceWindow, Speed.slow).getText();
		return ((configureLabel.split("-")[0].trim() + "-").equalsIgnoreCase(
				AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "-")
				&& servicelabel.equalsIgnoreCase(configureLabel.split("-")[1].trim()));
	}

	/**
	 * 'Verify Client ID Label of Configure Link of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyClientIdLabel()
	{
		return isDisplayed(labelClientId, Speed.slow);
	}

	/**
	 * 'Verify Client Secret Key Label of Configure Link of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyClientSecretKeyLabel()
	{
		return isDisplayed(labelClientSecretKey, Speed.slow);
	}

	/**
	 * 'Verify Configure Close Model of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	public boolean verifyConfigureCloseWindow()
	{
		return isDisplayed(imageConfigureModelCloseWindow, Speed.slow);
	}

	/**
	 * 'Verify Cancel button of Configure Window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyCancelButtonOfConfigureESignatureService()
	{
		return isDisplayed(locCancelButtonOfConfigureWindow, Speed.slow);
	}

	/**
	 * <<<<<<< HEAD
	 * 'Verify Inputbox and Placeholder value of ClientID of Configure Window of eSignature Service'
	 * =======
	 * 'Verify Inputbox and Placeholder value of ClientID of Configure Window of
	 * eSignature Service'
	 * >>>>>>> 69088bbd249e5e34ddb5560e944da4158ce82234
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyClientIDInputboxOfConfigureESignatureService()
	{
		WebElement clientIDInputbx = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);

		if (clientIDInputbx.isDisplayed())
		{
			String clientIDPlaceholderValue = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow)
					.getAttribute("placeholder");
			String clientIdValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");

			return (clientIDPlaceholderValue
					.equalsIgnoreCase(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_PLACEHOLDER_CLIENTID)
					|| pattern.matcher(clientIdValue).matches());

		}
		return false;
	}

	/**
	 * <<<<<<< HEAD
	 * 'Verify Inputbox and Placeholder value of Client Secret Key of Configure Window of eSignature Service'
	 * =======
	 * 'Verify Inputbox and Placeholder value of Client Secret Key of Configure
	 * Window of eSignature Service'
	 * >>>>>>> 69088bbd249e5e34ddb5560e944da4158ce82234
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyClientSecretKeyInputboxOfConfigureESignatureService()
	{
		WebElement clientSecretKeyInputbx = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		if (clientSecretKeyInputbx.isDisplayed())
		{
			String clientSecretKeyPlaceholderValue = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow,
					Speed.slow).getAttribute("placeholder");
			String clientSecretKeyValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");

			return (clientSecretKeyPlaceholderValue
					.equalsIgnoreCase(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_PLACEHOLDER_SECRETKEY)
					|| pattern.matcher(clientSecretKeyValue).matches());

		}
		return false;
	}

	/**
	 * 'Verify Test Button of Configure Window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public boolean verifyTestOfConfigureESignatureService()
	{
		WebElement testBtn = findVisibleElement(locTestBtnConfigureWindow, Speed.slow);

		if (testBtn.isDisplayed())
		{
			String clientIdValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");
			String clientSecretKeyValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");
			if (pattern.matcher(clientIdValue).matches() && pattern.matcher(clientSecretKeyValue).matches())
			{
				return testBtn.isEnabled();
			}

			return !testBtn.isEnabled();

		}
		return false;
	}

	/**
	 * Method to click on 'Configure' link of given service.
	 * 
	 * @param serviceName
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public void clickConfigureLinkOfService(String serviceName)
	{
		WebElement serviceConfigureLink = findVisibleElement(By.xpath("(//*[normalize-space(text())='"
				+ serviceName.trim() + "']//following::*[normalize-space(text())='"
				+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "'])[1]"),
				Speed.slow);
		serviceConfigureLink.click();
	}

	/**
	 * 'Click on Cancel Button of Configure Model window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 11/04/2018
	 */
	public void clickOnCancelConfigureModel()
	{
		WebElement cancelBtnConfigureWindow = findVisibleElement(locCancelButtonOfConfigureWindow, Speed.slow);
		cancelBtnConfigureWindow.click();
		while (isDisplayed(configurationModalWindowOpened))
			;
		// findPresentElement(configurationModalWindowClosed, Speed.slow);
	}

	/**
	 * Click on 'Test' Button of Configure Model window of eSignature Service
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public void clickOnTestConfigureModel()
	{
		WebElement testBtnConfigureWindow = findVisibleElement(locTestBtnConfigureWindow, Speed.slow);
		testBtnConfigureWindow.click();
		// while(driver.getWindowHandles().size() <= 1);
	}

	/**
	 * Configure ThirdParty Credential
	 * 
	 * @param clientId,
	 *        secretKey, serviceName, clientEmail, clientPassword
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */

	public boolean configureThirdPatyCredentials(String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword) throws InterruptedException
	{
		WebElement clientIDInput = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement secreteKeyInput = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		clientIDInput.clear();
		clientIDInput.sendKeys(clientId);

		secreteKeyInput.clear();
		secreteKeyInput.sendKeys(secretKey);

		clickOnTestConfigureModel();

		Thread.sleep(1000);
		if (verifyConfigurationServiceMessage())
		{
			return true;
		}
		else
		{
			if (!verifyConfigurationServiceMessage() && !verifyEnterValuesInConfigureService())
			{
				while (driver.getWindowHandles().size() <= 1)
					;
				switchWindow();
				if (isDisplayed(locUserName, Speed.slow))
				{
					findVisibleElement(locUserName, Speed.slow).sendKeys(clientEmail);
					findVisibleElement(locSubmit, Speed.slow).click();
					findVisibleElement(locPassword, Speed.slow).sendKeys(clientPassword);
					findVisibleElement(locSubmit, Speed.slow).click();
				}

				while (driver.getWindowHandles().size() > 1)
					;
				if (driver.getWindowHandles().size() == 1)
				{
					driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
					return true;
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify Configuration Service Message
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifyConfigurationServiceMessage()
	{

		findVisibleElement(locContainterOfConfigureWindowModel, Speed.slow, 300);

		if (isDisplayed(locSuccessMsgConfigureService))
		{
			String successMsg = findVisibleElement(successMsgCongurationService, Speed.slow).getText();
			return successMsg.trim().equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_SUCCESSFUL_MSG);
		}
		return false;
	}

	/**
	 * Verify Configuration Service Save Button
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifySaveConfigureService()
	{
		return isDisplayed(locSaveBtnConfigurationService, Speed.slow);
	}

	/**
	 * Click on Configuration Service Save Button
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public void clickOnSaveConfigureService()
	{
		WebElement saveBtnConfigurationService = findVisibleElement(locSaveBtnConfigurationService, Speed.slow);
		saveBtnConfigurationService.click();
	}

	/**
	 * Verify Configuration Service InputBox is not editable
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifyConfigurationInputBoxAfterSuccess()
	{
		WebElement clientIdInputBx = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement clientSecreteKeyInputBx = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow,
				Speed.slow);
		return !(clientIdInputBx.isEnabled() && clientSecreteKeyInputBx.isEnabled());
	}

	/**
	 * Verify Configuration Service InputBox error message
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifyEnterValuesInConfigureService()
	{
		if (isDisplayed(locErrorMsgConfigureService, Speed.slow))
		{
			String errorMsg = findVisibleElement(locErrorMsgClientid, Speed.slow).getText();
			if (errorMsg.trim().equals(AspAndSystemAdmin.THIRDPARTY_SERVICE_AUTHORISE_FAIL_MSG))
			{
				WebElement invalidClientIDInput = findVisibleElement(invalidClientId, Speed.slow);
				WebElement invalidSecreteKeyInput = findVisibleElement(invalidSecretKey, Speed.slow);
				return (invalidClientIDInput.getText().trim()
						.equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_CLIENTID)
						&& invalidSecreteKeyInput.getText().trim().equalsIgnoreCase(
								AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_KEY));
			}

		}
		return false;
	}

	/**
	 * Verify values of Configuration Service InputBox in form of "****"
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifValuesofThirdPartyConfigure()
	{
		WebElement clientIDInputbx = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement clientSecretKeyInputbx = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		if (clientSecretKeyInputbx.isDisplayed() && clientIDInputbx.isDisplayed())
		{
			String clientIdValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");
			String clientSecretKeyValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");

			return (pattern.matcher(clientIdValue).matches() && pattern.matcher(clientSecretKeyValue).matches());

		}
		return false;
	}

	/**
	 * set(Default) values of Configuration Service
	 * 
	 * @author dharti.patel
	 * @throws InterruptedException
	 * @creation date 12/04/2018
	 */
	public void setDocuSignSandboxCheckBoxInEsignatureServices(String status)
	{
		// setSelection(chkBoxOfDocusign, status);
		selectConfiguration(lableDocuSignSandbox, locDrpdwnDocuSignSandbox, trueFalsedocusignservicesCheckbox, status);

	}

	/**
	 * verify Configuration Service Message when document is pending
	 * 
	 * @author dharti.patel
	 * @creation date 12/04/2018
	 */
	public boolean verifyMessageOnDisableDocuSign(String msg)
	{
		findVisibleElement(thirdPartyDisabledModalId, Speed.slow);
		return isDisplayed(By.xpath(".//div[normalize-space(.)='" + msg.trim() + "']"), Speed.slow);
	}

	/**
	 * @author hasmukh.prajapati
	 * @param optionToSelect
	 *        This method enables "Open Pdf Document Directly"
	 * @creation date 17/04/2018
	 */
	@Override
	public void enableOpenPdfDocumentDirectly(String optionToSelect)
	{
		selectConfiguration(enableOpenPdfDocumentdirectlyLabel, enableOpenPdfDocumentDirectlyList,
				enableOpenPdfDocumentDirectlyCheckbox, optionToSelect);
	}

	/**
	 * @author hasmukh.prajapati This method close "GlobalMessageOnAspAdmin"
	 * @creation date 17/04/2018
	 */
	public void closeGlobalMessageOnAspAdmin()
	{
		if (isDisplayed(globalMessageOnAspAdmin, Speed.slow))
		{

			findPresentElement(globalMessageBarCloseLink, Speed.slow, 20);
		}
		findVisibleElement(breadCrumb, Speed.slow);
	}

	/**
	 * verify Asp Admin System Configuration Label
	 * 
	 * @param labelName
	 *        name of label to be verified
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyAspAdminSystemConfigurationLabel(String labelName)
	{
		By labelNameForAspAdmin = By
				.xpath(".//*[@id='customizeForm']//*[normalize-space(text())='" + labelName.trim() + "']");
		return isDisplayed(labelNameForAspAdmin);

	}

	/**
	 * set Online Office property
	 * 
	 * @param optionValue
	 * @author paras.vankadi
	 */
	@Override
	public void enableOpenInOfficeOnline(String optionValue)
	{
		selectConfiguration(enableOpenInOfficeOnline, enableOpneINOfficeOnlineONOFF, enableOpneINOfficeOnlineCheckbox,
				optionValue);
	}

	/**
	 * verify all Asp Admin property dropdown values
	 * 
	 * @param optionValue
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifySelectConfigurationVaule(By configurationName, By configurationDropDown,
			By configurationCheckbox, String... accVal) throws InterruptedException
	{
		Select configurationDrpDwn = new Select(findVisibleElement(configurationDropDown));

		WebElement configurationChkbox = findClickableElement(configurationCheckbox);
		if (configurationChkbox.isSelected())
		{
			configurationChkbox.click();
		}

		List<WebElement> selectedValue = configurationDrpDwn.getOptions();

		ArrayList<String> al = new ArrayList<>();

		for (WebElement ele : selectedValue)
		{

			al.add(ele.getText().trim());

		}

		return al.containsAll(Arrays.asList(accVal));

	}

	/**
	 * verify Office Online Property
	 * 
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyOnlineOfficeProperty(String... listOFPropety) throws InterruptedException
	{

		return verifySelectConfigurationVaule(enableOpenInOfficeOnline, enableOpneINOfficeOnlineONOFF,
				enableOpneINOfficeOnlineCheckbox, listOFPropety);
	}

	/**
	 * verify Office Token Expiration Time
	 * 
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifyOfficeOnlineTokenExpirationTime()
	{

		return isDisplayed(checkOfficeOnlineTokenExpirationTime);
	}

	/**
	 * set vaule for Office Online Token Expiration Time
	 * 
	 * @param number
	 * @author paras.vankadi
	 */
	@Override
	public void addOfficeOnlineTokenExpirationTime(String number) throws IOException
	{

		WebElement inputBox = findClickableElement(checkBoxEnableForOfficeTimePeriod);
		if (inputBox.isSelected())
		{
			inputBox.click();
		}
		inputBox = findVisibleElement(addOfficeOnlineTokenExpirationTime);
		inputBox.clear();
		inputBox.sendKeys(number);
	}

	/**
	 * @author hasmukh.prajapati This method close "GlobalMessageOnAspAdmin"
	 * @creation date 17/04/2018
	 */
	public void closeGlobalMsg()
	{
		if (isDisplayed(locGlobalMsg))
		{
			findVisibleElement(locGlobalClose).click();
		}
	}

	/**
	 * @author dharti.patel verify disable Docusign
	 */
	public boolean verifyDisableDocuSignModelOpend()
	{
		return isDisplayed(thirdPartyDisabledModalId);
	}

	/**
	 * @author dharti.patel verify disable Docusign Message
	 */
	public boolean verifyDisableDocuSignMessage()
	{
		findVisibleElement(thirdPartyDisabledModalId);

		if (isDisplayed(
				By.xpath(".//*[@class='modal-title'][normalize-space(text())='" + AdminLabels.DISABLE_DOCUSIGN + "']")))

		{
			String msg = findVisibleElement(locDisableDocuSignBody).getText();

			return msg.equals(AdminLabels.ASPADMIN_SERVICE_NOT_ABLE_TO_DISABLED);
		}

		return false;

	}

	/**
	 * @author dharti.patel Close disable DocuSign Model
	 */
	public void closeDisableDocuSign()
	{
		findVisibleElement(thirdPartyDisabledModalId);
		findVisibleElement(locDisableDocuSignClose).click();
	}

	/**
	 * @author ashlesha.shastri
	 * @param optionToSelect
	 *        To Enable Privacy Policy
	 * @creation date 20/04/2018
	 */
	@Override
	public void setEnablePrivacyPolicy(String optionToSelect)
	{
		selectConfiguration(enablePrivacyPolicyLabel, enablePrivacyPolicyDropDown, enablePrivacyPolicyCheckbox,
				optionToSelect);
	}

	@Override
	public boolean verifyDocumentAnalysisCronExprsnPresence()
	{
		if (isDisplayed(documentAnalysisCronExpressionTextbox))
		{
			return isDisplayed(documentAnalysisCronExpressionCheckBox);
		}

		return false;
	}

	@Override
	public boolean verifyNoOfDocsPresence()
	{
		if (isDisplayed(noOfDocsTextbox))
		{
			return isDisplayed(noOfDocsCheckbox);
		}
		return false;
	}

	@Override
	public List<String> getDocumentAnalysisDropdownValues(String engineName)
	{
		By enableDocumentAnalysis = By
				.xpath("//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_" + engineName.toUpperCase() + "']");
		List<String> allValues = new ArrayList<>();
		Select documentAnalysisDropdown = new Select(findVisibleElement(enableDocumentAnalysis, Speed.slow));
		List<WebElement> allValuesInSelect = documentAnalysisDropdown.getOptions();

		allValuesInSelect.forEach(item -> {
			allValues.add(item.getText());
		});
		return allValues;
	}

	@Override
	public void selectDocumentAnalysisCheckbox(String engineName, boolean state)
	{
		By selectDocAnalysisCheckbox = By.id("ENABLE_DOCUMENT_ANALYSIS_" + engineName.toUpperCase());
		setSelection(selectDocAnalysisCheckbox, state);
	}

	/**
	 * Verify presence of configuration value in System configuration
	 * 
	 * @param configNameInSystemConfig
	 *        configuration name
	 * @author anil.sikhwal
	 * @creation date 24/05/2018
	 */
	public boolean verifyConfigInSystemConfiguration(String configNameInSystemConfig)
	{
		return isDisplayed(By.xpath(
				"//*[@id='customizeForm']//table//*[normalize-space(text())='" + configNameInSystemConfig + "']"),
				Speed.slow);
	}

	/**
	 * Verify presence of 'Enable Document Analysis' dropdown
	 * 
	 * @param
	 * @author anil.sikhwal
	 * @return true if dropdown displayed
	 */
	public boolean verifyPresenceOfEnableDocumentAnalysDropDown()
	{
		return isDisplayed(enableDocumentAnalysisDropDown, Speed.slow);
	}

	/**
	 * Verify presence of 'Document analysis cron expression' input box
	 * 
	 * @param
	 * @author anil.sikhwal
	 */
	public boolean verifyPresenceOfCronExprsn()
	{
		return isDisplayed(enableCustomCronExprsnDocAnalysis, Speed.slow);
	}

	/**
	 * Verify presence of 'No. of documents to be processed in a job' input box
	 * 
	 * @param
	 * @author anil.sikhwal
	 */
	public boolean verifyPresenceOfdocCount()
	{
		return isDisplayed(noOfDocsTextbox, Speed.slow);
	}

	/**
	 * Get default selected value from dropdown 'Enable Document Analysis'
	 *
	 * @author anil.sikhwal
	 * @return
	 */
	public String getDocumentAnalysisDropdownDefaultValue()
	{
		String defaultValue = "";
		if (verifyPresenceOfEnableDocumentAnalysDropDown())
		{
			enableDocAnalysDrpdwnChkbox();
			Select documentAnalysisDropdown = new Select(
					findVisibleElement(enableDocumentAnalysisDropDown, Speed.slow));
			defaultValue = documentAnalysisDropdown.getFirstSelectedOption().getText();
		}
		else
		{
			System.err.println("Dropdown is not Visible");
		}
		return defaultValue;
	}

	/**
	 * click on document analysis check box
	 * 
	 * @param
	 * @author anil.sikhwal
	 */

	public void enableDocAnalysDrpdwnChkbox()
	{
		WebElement elem = findVisibleElement(enableDocumentAnalysisCheckbox, Speed.slow);

		if (!elem.isSelected())
		{
			elem.click();
		}
	}

	/**
	 * Get all values of dropdown 'Enable Document Analysis'
	 * 
	 * @author anil.sikhwal
	 * @return
	 */
	public List<String> getDocumentAnalysisDropdown()
	{

		if (verifyPresenceOfEnableDocumentAnalysDropDown())
		{
			List<String> allValues = new ArrayList<>();
			Select documentAnalysisDropdown = new Select(
					findVisibleElement(enableDocumentAnalysisDropDown, Speed.slow));
			List<WebElement> allValuesInSelect = documentAnalysisDropdown.getOptions();

			allValuesInSelect.forEach(item -> {
				allValues.add(item.getText());
			});
			return allValues;
		}
		else
		{
			System.err.println("Dropdown is not Visible");
			return null;
		}
	}

	@Override
	public String getDefaultDocumentAnalysisDropdownValue(String engineName)
	{
		selectDocumentAnalysisCheckbox(engineName, true);
		By enableDocumentAnalysisDropDown = By
				.xpath("//*[@id='CUSTOM_ENABLE_DOCUMENT_ANALYSIS_" + engineName.toUpperCase() + "']");
		Select documentAnalysisDropdown = new Select(findVisibleElement(enableDocumentAnalysisDropDown, Speed.slow));
		return documentAnalysisDropdown.getFirstSelectedOption().getText();
	}

	@Override
	public void setDocumentAnalysisDropdownValue(String engineName, String option) throws InterruptedException
	{
		switch (engineName.toLowerCase())
		{
			case "highq":
				setEnableDocumentAnalysisByHighQ(option);
				break;
			case "leverton":
				setEnableDocumentAnalysisByLeverton(option);
				break;
			case "kira":
				setEnableDocumentAnalysisByKira(option);
				break;
			default:
				break;
		}
	}

	@Override
	public void enableEnableOpenInOfficeOnline(String optionValue)
	{
		selectConfiguration(enableOpenInOfficeOnline, enableOpneINOfficeOnlineONOFF, enableOpneINOfficeOnlineCheckbox, optionValue);
	}

	@Override
	public void enableEditFile(String optionValue)
	{
		selectConfiguration(enableEditFileLbl, enableEditFile, enableEditFileckBox, optionValue);
	}

}
