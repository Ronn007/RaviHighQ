package com.highq.pageobjects.base;

import java.io.IOException;
import org.openqa.selenium.By;

public interface AdminFilesPage extends BannerPage
{
	public void setFilePermission(String permission);

	public void setViewerSettings(String setting);

	public void selectFileOptionCheckbox(String optionToCheck, boolean state);

	public void selectNamingConvention(String selectOption);

	public void selectAutoNumberingLevel(String selectOption);

	public void selectCheckbox(By option, boolean check);

	public void saveFilesChanges();

	public void sendDRMWaterMarkText(String text);

	public void setPDFConversionSetting(String text);

	public void setDefaultFolderSortOrder(String sortOrder, boolean applyToAll);

	public void setDefaultFolderView(String view, boolean applyToAll);

	public void setDefaultFolderPermissions(String permission, boolean applyToAll);

	public void setDefaultFilesSortOrder(String sortOrder, boolean applyToAll);

	public void enableOnlineViewerWithDRM();

	public void clickContinue();

	public void clickOK();

	public void disableOnlineViewerWithDRM();

	public void encryptFilesWithSeclorePlugin(boolean state);

	public void encryptFilesWithFileOpenPlugin(boolean state);

	public void convertAllFilesToPDFAndEncrypt(boolean state);

	public void serEncryptionKeyManager(String encryptionKey);

	public void enableFileSearchIndexing(boolean state);

	public void setDocuSign(String option);

	public void setAdobeSign(String option);

	public boolean verifyDocumentAnalysisEngineConfigurationDisdplay();

	public boolean verifyEnableAIEngineTrainingDisdplay();

	public void enableAIEngineTraining(boolean state);

	public boolean verifyLabelOfTrainingDisplayed(String engineName, String label);

	public void clickMoreActionOptionDocumentAnalysisEngineConfiguration(String engineName, String actionOption);

	public boolean verifyLabelOfEngineNameDisplayed(String string);

	public boolean verifyValuesOfEngineDisplayed(String engineName, String value);

	public void enableDocumentAnalysisEngine(String engineName, String option) throws InterruptedException;

	public String getEnableDocAnalysisTextMsg();

	public boolean verifyPresenceDocAnalysisCheckbox();

	public boolean getEnableDocAnalysisChekcboxStatus();

	public int countServiceOn();

	public String getOnServiceName(int totalServiceOn);

	public void enableCheckInCheckOut(boolean value);

	public boolean verifyThirdPartyService(String serviceName);

	public void clickOnOkDisableService(String serviceName);

	public boolean verifySelectedStatusofThirdParty(String serviceName, String option);

	public void setAdobeSign(String serviceName, String option);

	public void setDocuSign(String serviceName, String option);

	public boolean verifyThirdPartyOption();

	public boolean verifyServiceDescription(String serviceName);

	public boolean verifyThirdPartyOption(String serviceName);

	public boolean verifySelectedValueOfService(String serviceName, String value);

	public void clickOnMoreActionOptionOfService(String serviceName, String option);

	public void clickOnRevokeButtonOnRevokeModel();

	public boolean verifyRevokeWindowModel(String serviceName);

	void addEmailIDSiteInbox(String toEmail) throws IOException;

	void enableOpenOfficeOnline(boolean state);

	boolean verifySiteAdminOpenOfficeOnline();

	boolean verifyCheckBoxSiteAdminOpenOfficeOnline();

	void setEnableSiteIndex(boolean state) throws IOException;

	public boolean verifyFileOptionCheckboxIsSelected(String optionToCheck);

	public void selectProjectFromManageModal(String projectName);

	public void clickOnSaveInManageModal();

	public void enableStatus(boolean value);

	public void setEnabelDocumentReviewWorkflow(boolean value);

	public boolean authoriseDocuSignService(String serviceName, String clientEmail, String clientPassword) throws InterruptedException;

	public boolean verifyAuthorisedService(String serviceName);

	public boolean verifydocumentAnalysisEngineDisplay(String engine);
}
