package com.highq.pageobjects.base;

import java.util.List;
import org.openqa.selenium.By;
import com.highq.pageobjects.pages.SystemAdminWeb;

public interface SystemAdminSystemSettingsPage extends BannerPage
{

	public void setCommentsOnFile(String optionValue) throws InterruptedException;

	public void saveSettings() throws InterruptedException;

	public SystemAdminWeb goBack();

	public void removeAllLabelsInDefaultScopeMicroblogging();

	public void addLabelInDefaultScopeMicroBlogging(String labelName);

	public void deSelectDefaultScopeOfMicroBloggingCheckBox();

	public void selectDefaultScopeOfMicroBloggingCheckBox();

	public boolean verifyContactUserEmailAddress(String email);

	public void setEnableGdprContactForFooter(String optionValue);

	public boolean isContactUserEmailTextBoxAvailabe();

	public void editContactUsEmailAddress(String email) throws InterruptedException;

	public void setEnableGDPRCheckBox(boolean status);

	public void setCheckoutEnableOption(String optionValue) throws InterruptedException;

	@Override
	public void selectConfiguration(By configurationName, By configurationDropDown, By configurationCheckbox,
			String optionValue);

	public void enableMyFiles(String optionToSelect);

	public void enableMyFilesSharing(String optionToSelect);

	boolean verifyDocumentAnalysisOptionWithDropDown() throws InterruptedException;

	void selectOptionFromHighqEngineDropdown(String option);

	boolean verifyDocumentAnalysisHighQDropdownValues(String option);

	boolean verifyEnableAIEngineTrainingDropdownDisplay();

	void selectOptionFromEnableAIEngineTrainingDropdown(String option);

	void clickonDocumentAnalysisEngineConfigurationDropdown(String option);

	public void selectOptionFromDocumentAnalysisEngineConfigurationDropdown(String string, String string2);

	public boolean verifyLabelManageModalDisplay();

	void clickOncancelButtonManageModal();

	public boolean verifySearchTextboxManageModalDisplay();

	public boolean verifyClassifiersListManageModal(List<String> classifiersList);

	public void selectDocumentAnalysis(String engine, String option) throws InterruptedException;

	public void selectShareViaEmailPermission(String optionValue);

	public boolean verifyAddedThirdPArtyServicesInSystemSetting(String Servicename);

	public void clickOnOptionInMoreActionOfThirdPartyService(String serviceName, String option);

	public boolean verifydThirdPArtyServicesOnAddServiceModal(String serviceName);

	public void clickOnAddBtnOfThirdPartyServices() throws InterruptedException;

	public boolean verifyStatusConfigureService(String serviceName);

	public boolean verifyConfigureServiceMoreAction(String option);

	public void addServiceInThirdParty(String serviceName);

	public void clickOnMoreActionOfThirdPartyService(String serviceName);

	public void clickOnCancelButtonThirdPartyService();

	public boolean verifyThirdPartyServicesOption();

	public boolean verifyAddButtonThirdPartyService();

	public boolean verifyCloseButtonAddThirdPartyService();

	public void clickCloseAddWindowModel();

	public boolean verifyCancelButtonThirdPartyService();

	public boolean verifySelectServiceMsg();

	public void clickAddService();

	public void selectAllServiceAddWindowModel();

	public boolean verifyNoServiceAvailableMsg();

	public boolean verifyRemoveLabelRemoveModel();

	public boolean verifyCancelButtonRemoveModel();

	public void clickCancelButtonRemoveModel();

	public boolean verifyRemoveButtonInRemoveWindowModel();

	public boolean verifyCloseButtonRemoveModel();

	public boolean verifyRemoveMessgaeRemoveModel(String serviceName);

	public void clickRemoveButtonRemoveModel();

	public boolean verifyMsgAfterRemoveService();

	public boolean verifyConfigureWindowLabelOfESignatureService(String servicelabel);

	public boolean verifyConfigureCloseWindow();

	public boolean verifyClientIdLabel();

	public boolean verifyClientSecretKeyLabel();

	public boolean verifyClientIDInputboxOfConfigureESignatureService();

	public boolean verifyClientSecretKeyInputboxOfConfigureESignatureService();

	public boolean verifyTestOfConfigureESignatureService();

	public boolean verifyCancelButtonOfConfigureESignatureService();

	public void clickOnCancelConfigureModel();

	public void clickOnTestConfigureModel();

	public boolean verifyConfigurationServiceMessage();

	public boolean verifyEnterValuesInConfigureService();

	public boolean configureThirdPatyCredentials(String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword);

	public boolean verifySaveConfigureService();

	public void clickOnSaveConfigureService();

	public boolean verifyConfigurationInputBoxAfterSuccess();

	public boolean verifyClientIDInputboxAfterConfigureService();

	public boolean verifyClientSecretKeyInputboxAfterConfigureService();

	public boolean verifyAuthorisedService(String serviceName) throws InterruptedException;

	public boolean verifyRevokeAuthorisationLabel();

	public boolean verifyCloseButtonRevokeAuthWindow();

	public boolean verifyRevokeAuthBodyMessage(String serviceName);

	public boolean verifyCancelRevokeAuthWindow();

	public boolean verifyRevokeButtonRevokeAuthWindow();

	public void clickOnRevokeButtonOfRevokeAuthWindow();

	public boolean verifyCloseAddWindowModel();

	public boolean verifyAddButtonService(String serviceName);

	public boolean configureThirdPatyCredentialsForAdobe(String hostUrl, String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword) throws InterruptedException;

	public boolean verifyStatusConfigureServiceofAdobe(String serviceName);

	public boolean authoriseAdobeService(String serviceName, String clientEmail, String clientPassword)
			throws InterruptedException;

	public boolean authoriseDocuSignService(String serviceName, String clientEmail, String clientPassword)
			throws InterruptedException;

	public boolean verifyConfigureWindowAdobeLabelOfESignatureService(String servicelabel);

	public void selectAdobeOptionThirdPartyService(String serviceName, String option);

	public void selectDocuSignOptionThirdPartyService(String serviceName, String option);

	public int totalSevriceOn();

	public String getOnServiceName(int totalServiceOn);

	public boolean verifySelectedValueOfThirdPartyService(String serviceName, String value);

	public void closeGlobalMsg();

	boolean verifyOnlineOfficeForSystemAdmin(String... listOFPropety) throws InterruptedException;

	void enableOpenInOfficeOnlineForSystemAdmin(String optionValue);

	void selectConfigurationForSystemAdmin(By configurationName, By configurationDropDown, String optionValue)
			throws InterruptedException;

	boolean verifySelectConfigurationVaule(By configurationName, By configurationDropDown, String... accVal)
			throws InterruptedException;

	boolean verifySystemAdminOpenOfficeOnline();

	public void documentAnalysisEngineConfigureKira(String kiraInstanceUrl, String kiraToken);

	public void clickOnMoreActionOfDocumentAnalysisEngine(String engineName);

	public void selectMicroBlogging(String optionToSelect);

	void enableTwoFactorAuthentication(boolean desiredState, String[] organization);

	void enableTwoFactorAuthenticationForUsersOrSystemAdmin(String twoFactorAuthentication, boolean desiredState);

	void enableMyTasks(String optionToSelect);

	boolean verifyDocumentAnalysisEngineWithDropdown(String engineName);

	public boolean verifyDocumentAnalysisDropdownValues(String engineName, String option);

	public void enterAdobeSignHostUrl(String url);

	public void verifyReditectUrlOnConfigureService();
}
