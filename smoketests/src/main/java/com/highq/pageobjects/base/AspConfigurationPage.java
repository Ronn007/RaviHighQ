package com.highq.pageobjects.base;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;

public interface AspConfigurationPage extends BannerPage
{

	public void setExportOption(String optionValue) throws InterruptedException;

	public void saveConfigurations() throws InterruptedException;

	public void setCheckoutEnableOption(String optionValue) throws InterruptedException;

	public void selectConfiguration(By configurationName, By configurationDropDown, By configurationCheckbox,
			String optionValue);

	public void enableCommentingForAllSites(String optionToSelect);

	public void enableAdeptolPageCount(String optionToSelect);

	public void clickOnRssFeedSecurityCheckBox();

	public void setRssFeedSecurity(String permission);

	public void setEnableIsheetVersionAuditOption(String optionValue) throws InterruptedException;

	public String getValueOfContactUsLink();

	public void enableDocumentReview(String optionToSelect);

	public void setDDAndContractAutomationReport(String optionToSelect) throws InterruptedException;

	public void setEnableXSSDirectoryRestrictedWord(boolean permission);

	public void enableDataVisualisationPanel(boolean permission);

	public void setBulkDownloadWithPipeStream(String optionToSelect);

	public void enableMyFiles(String optionToSelect);

	public void enableMyFilesSharing(String optionToSelect);

	void setEnableDocumentAnalysisByHighQ(String permission) throws InterruptedException;

	void setEnableAITraining(String permission) throws InterruptedException;

	void setEnableDocumentAnalysisByKira(String permission) throws InterruptedException;

	void setEnableDocumentAnalysisByLeverton(String permission) throws InterruptedException;

	void clickOnLeftPanelLabel(String option);

	boolean verifyLeftPanelLabel(String option);

	boolean verifyRightPanelLabel(String option) throws InterruptedException;

	boolean verifySearchEngineValues() throws InterruptedException;

	boolean verifyAITrainingValues() throws InterruptedException;

	boolean verifyCronExpressionValue(String value);

	boolean verifyNoOfDocsValue(String value);

	boolean verifyNoOfDocsValidationMessage(String value);

	boolean verifyCronExpressionValidationMessage(String value);

	boolean verifySaveConfigurationMessage(String message);

	void setNoOfDocsInJob(String value);

	void setDocumentAnalysisCronExpression(String value);

	void resetEnableDocumentAnalysisByHighq() throws InterruptedException;

	void resetEnableDocumentAnalysisByKira() throws InterruptedException;

	void resetEnableDocumentAnalysisByLeverton() throws InterruptedException;

	void resetEnableAITraining() throws InterruptedException;

	void resetDocumentAnalysisCronExpression() throws InterruptedException;

	void resetNoOfDocs() throws InterruptedException;

	public String getValueOfEmailSubjectPrefix();

	public void setTaskBeforeOneDayTriggerCronExpression(String expression);

	public void enableDocumentAnalysisByHighQ(String value);

	public void enableDocumentAnalysisByKira(String value);

	public void enableDocumentAnalysisByLeverton(String value);

	public void setEnableResetPasswordVerificationDetail(String optionValue) throws InterruptedException;

	public void setEnableUserId(String optionValue) throws InterruptedException;

	public boolean verifySiteEmailAlertOptionFromDropDown(String option);

	public void setSiteEmailalerts(String optionValue);

	public boolean isSiteEmailAlertDropdownEnable();

	public boolean isSiteEmailAlertDropdownValueSelected(String option);

	public void enableAPIOption(String optionToSelect);

	public void setAppBaseURL(boolean status, String appURL);

	public void clickOnAppBaseURLCheckBox(boolean status);

	public void setDefaultContactUsLink(boolean requiredState);

	public void clickOnESignatureServices();

	public boolean verifyDocuSignSandboxServiceLabel();

	public boolean verifyDocuSignSandboxServiceDropDownOption();

	public boolean verifyConfigureLinkForService(String serviceName);

	public boolean verifyDocuSignProductionServiceLabel();

	public boolean verifyDocuSignProductionServiceDropDownOption();

	public void clickConfigureLinkOfService(String serviceName);

	public boolean verifyConfigureCloseWindow();

	public boolean verifyClientIdLabel();

	public boolean verifyClientSecretKeyLabel();

	public boolean verifyCancelButtonOfConfigureESignatureService();

	public boolean verifyClientIDInputboxOfConfigureESignatureService();

	public boolean verifyClientSecretKeyInputboxOfConfigureESignatureService();

	public boolean verifyTestOfConfigureESignatureService();

	public boolean verifyConfigureWindowLabelOfESignatureService(String servicelabel);

	public void clickOnCancelConfigureModel();

	public void clickOnTestConfigureModel();

	public boolean configureThirdPatyCredentials(String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword) throws InterruptedException;

	public boolean verifyConfigurationServiceMessage();

	public boolean verifySaveConfigureService();

	public void clickOnSaveConfigureService();

	public boolean verifyConfigurationInputBoxAfterSuccess();

	public boolean verifyEnterValuesInConfigureService();

	public boolean verifValuesofThirdPartyConfigure();

	public boolean verifyMessageOnDisableDocuSign(String msg);

	public void setDocuSignSandboxCheckBoxInEsignatureServices(String status);

	public void enableOpenPdfDocumentDirectly(String optionToSelect);

	public void closeGlobalMessageOnAspAdmin();

	void addOfficeOnlineTokenExpirationTime(String number) throws IOException;

	boolean verifyOfficeOnlineTokenExpirationTime();

	boolean verifyOnlineOfficeProperty(String... listOFPropety) throws InterruptedException;

	boolean verifySelectConfigurationVaule(By configurationName, By configurationDropDown, By configurationCheckbox,
			String[] accVal) throws InterruptedException;

	void enableOpenInOfficeOnline(String optionValue);

	boolean verifyAspAdminSystemConfigurationLabel(String labelName);

	public void closeGlobalMsg();

	public boolean verifyDisableDocuSignModelOpend();

	public boolean verifyDisableDocuSignMessage();

	public void closeDisableDocuSign();

	public void setEnablePrivacyPolicy(String optionToSelect);

	public boolean verifyConfigInSystemConfiguration(String configNameInSystemConfig);

	public boolean verifyPresenceOfEnableDocumentAnalysDropDown();

	public boolean verifyPresenceOfCronExprsn();

	public boolean verifyPresenceOfdocCount();

	public String getDocumentAnalysisDropdownDefaultValue();

	public List<String> getDocumentAnalysisDropdown();

	public boolean verifyDocumentAnalysisCronExprsnPresence();

	public boolean verifyNoOfDocsPresence();

	public List<String> getDocumentAnalysisDropdownValues(String engineName);

	public void selectDocumentAnalysisCheckbox(String engineName, boolean state);

	public String getDefaultDocumentAnalysisDropdownValue(String engineName);

	void enableEnableOpenInOfficeOnline(String optionValue);

	void enableEditFile(String optionValue);

	public void setDocumentAnalysisDropdownValue(String engineName, String option) throws InterruptedException;
}
