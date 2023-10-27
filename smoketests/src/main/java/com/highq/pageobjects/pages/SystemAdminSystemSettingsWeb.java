package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.labels.collaborate.AspAndSystemAdmin;
import com.highq.labels.collaborate.SiteAdminLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;

public class SystemAdminSystemSettingsWeb extends BannerPageWeb implements SystemAdminSystemSettingsPage
{
	By commentsOnFilesLable = By
			.xpath("//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_SYSTEMSETTINGS_COMMENTONFILES + "']");
	By commentsOnFilesList = By.id("CUSTOM_COMMENTS_ON_FILES");
	By commentsOnFilesCheckbox = By.id("COMMENTS_ON_FILES");

	By leftPanel_BackOption = By.xpath(".//*[@id='leftPanel']//*[normalize-space(text())='"
			+ SystemAdminLabels.SYSADMIN_SYSTEMSETTINGS_BACK + "']");

	By saveButtonLink = By.linkText(SystemAdminLabels.SYSADMIN_SYSTEMSETTINGS_SAVE);

	By defaultScopeOfMicroblogging = By.xpath("//*[contains(@id,'as-selections')]");

	By defaultScopeMicrobloggingTextBox = By
			.xpath("//*[contains(@id,'as-original')]/input[@class='input-medium as-input']");

	By defaultScopeOfMicrobloggingList = By.xpath("//*[contains(@id,'as-selections')]/li");

	By defaultScopeOfMicrobloggingCheckBox = By.id("DEFAULT_SCOPE_FOR_MICROBLOGGING");

	By checkoutEnableList = By.id("CUSTOM_CHECKOUT_ENABLE");
	By checkoutEnableCheckbox = By.id("CHECKOUT_ENABLE");

	By enableMyFilesLabel = By.xpath(".//*[normalize-space(.)='My files']");
	By enableMyFilesDropDownList = By.id("CUSTOM_PERSONAL_FILES");
	By enableMyFilesCheckbox = By.id("PERSONAL_FILES");

	By enableMyFilesSharingLabel = By.xpath(
			"//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEMYFILESSHARING + "']");
	By enableMyFilesSharingDropDownList = By.id("CUSTOM_ENABLE_PERSONAL_FILES_SHARING");
	By enableMyFilesSharingCheckbox = By.id("ENABLE_PERSONAL_FILES_SHARING");

	By documentAnalysisEngineConfigurationLbl = By
			.xpath(".//*[@id='lstvAdmin']//h2[normalize-space(.)='Document analysis engine configuration']");

	By documentAnalysisEngineConfigurationHighqDropdown = By
			.xpath(".//*[@id='selectedEngineDetailListContainer']//button/span[@id='engineServiceSettingText_HIGHQ']");

	By documentAnalysisEngineConfigurationKiraDropdown = By
			.xpath(".//*[@id='selectedEngineDetailListContainer']//button/span[@id='engineServiceSettingText_KIRA']");

	By documentAnalysisEngineConfigurationDropdown = By
			.xpath(".//*[@id='selectedEngineDetailListContainer']//button/span[@id='engineServiceSettingText_");

	By enableAIEngineTrainingDropdown = By.id("CUSTOM_ENABLE_AI_ENGINE_TRAINING_SYS_ADMIN");

	By openedDropdown = By.id("engineServiceMenu_HIGHQ");

	By moreActionList = By.xpath(".//*[@id='selectedEngineDetailListContainer']/div/div");

	By classifiersListManageModal = By.xpath(".//*[@id='classifierListUl']/li");

	By labelManageModal = By
			.xpath(".//*[@id='manageSystemLevelClassifierListID_TITLE']/div[contains(text(),'Manage')]");

	By searchTextboxManageModal = By
			.xpath(".//*[@id='systemAdmin_manageclassifier_search' and @placeholder='Search classifiers']");

	By classifierSearchNoResultsLabelManageModal = By.xpath(".//*[@id='noDataFoundDiv']//*[text()='No results found']");

	By cancelButtonManageModal = By.id("manageSystemLevelClassifierListID_MAIN_CLOSE_BUTTON");

	By highqEngineOptions = By.id("engineServiceSettingText_HIGHQ");
	By kiraEngineOptions = By.id("engineServiceSettingText_KIRA");
	By globalMsgContainer = By.xpath("//*[@id='animatedMessageBox' and (not(contains(@style,'display: none;')))]");
	// By globalMsgContainerHidden = By.xpath("//*[@id='animatedMessageBox' and
	// (contains(@style,'display: none;'))]");
	By globalMessageContainerHidden = By
			.xpath("//*[@id='globalAlertMessageContainerMsgSpace']//ancestor::*[@class='globalMsg hide']");

	By kiraInstanceUrl = By.id("engineClientURL");
	By kiraToken = By.id("engineClientSceretKey");
	By kiraTestButton = By.id("system_admin_document_analysis_service_configure_id_test");
	By kiraSaveButton = By.id("system_admin_document_analysis_service_configure_id_save");
	By configurstionDoneMsg = By.xpath(
			".//*[@id='configureModal_SuccessDiv']//span[normalize-space(.)='Test successful, You can now save the configuration.']");
	String highqEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ;
	String kiraEngine = SiteAdminLabels.SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA;

	By addButtonInAddServices = By.id("system_admin_add_services");

	By tpServicesDialog = By.id("system_admin_third_party_services_modal_id");

	By thirdPartyServicesContainer = By.id("selectedThirdPartyServicesListContainer");

	By locAddServiceLabel = By
			.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN + "']");

	By locCancelAddService = By.id("system_admin_third_party_services_modal_id_Close");

	By locThirdPartyOption = By.xpath(".//*[normalize-space(text())='Third party services']");

	By locCloseBtnAddThirdPartyService = By.id("system_admin_third_party_services_modal_id_MAIN_CLOSE_BUTTON");

	By locAddService = By.id("system_admin_third_party_services_modal_id_save");

	By locChkAddService = By.xpath(".//*[contains(@id,'chkThirdPartyServices')]");

	By locSelectServiceMsg = By
			.xpath(".//*[@id='system_admin_third_party_services_modal_id_BODY']//following::*[normalize-space(text())='"
					+ SystemAdminLabels.THIRDPARTY_SELECT_SERVICE_MSG + "']");

	By locThirdPartyServiceLogo = By
			.xpath(".//*[@id='system_admin_third_party_services_modal_id_BODY']//following::*[normalize-space(text())='"
					+ SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX
					+ "']/preceding::*[contains(@src,'getThirdPartyServiceLogo')]");

	By locAddWindowModel = By.id("system_admin_third_party_services_modal_id_BODY");

	By locService = By
			.xpath(".//*[@id='system_admin_third_party_services_modal_id_BODY']//following::*[normalize-space(text())='"
					+ SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX + "']");

	By locNoServiceAvailableMsg = By.id("collaborateCustomModalMessage");

	By locContainerAddServiceModel = By.id("collaborateCustomMessageModal");

	By locRemoveServiceContainer = By.id("system_admin_third_party_service_remove_id");

	By locRemoveBtn = By.id("system_admin_third_party_service_remove_id_remove");

	By locCloseBtnRemoveModel = By.id("system_admin_third_party_service_remove_id_MAIN_CLOSE_BUTTON");

	By locRemoveWindowBody = By.id("system_admin_third_party_service_remove_id_BODY");

	By locRemoveLabel = By
			.xpath(".//*[@id='system_admin_third_party_service_remove_id']//following::*[normalize-space(text())='"
					+ SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_REMOVE_MODAL_TITLE + "']");

	By locCancelRemoveWindow = By.id("system_admin_third_party_service_remove_id_close");

	By locMsgAfterRemoveService = By.id("messageTxt");

	By locMsgBox = By.id("animatedMessageBox");

	By labelConfigureServiceWindow = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id']//following::*[@class='modal-title']");

	By imageConfigureModelCloseWindow = By.id("system_admin_third_party_service_configure_id_MAIN_CLOSE_BUTTON");

	By labelClientId = By.xpath("//*[@class='control-label' and contains(text(),'"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENTID + "')]");

	By labelClientSecretKey = By.xpath("//*[@class='control-label' and contains(text(),'"
			+ AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENT_SECRETE_KEY + "')]");

	By locClientIDInputbxOfConfigureWindow = By.id("thirdPartyClientId");

	By locClientSecreteKeyInputbxOfConfigureWindow = By.id("thirdPartyClientSceretKey");

	By locTestBtnConfigureWindow = By.id("system_admin_third_party_service_configure_id_test");

	By locCancelButtonOfConfigureWindow = By.id("system_admin_third_party_service_configure_id_close");

	By configurationModalWindowOpened = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id' and contains(@style,'display: block;')]");

	By locContainterOfConfigureWindowModel = By
			.xpath(".//*[@id='system_admin_third_party_service_configure_id' and contains(@style,'display: block;')]");

	By locSuccessMsgConfigureService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[@class='alert alert-success']");

	By successMsgCongurationService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'alert-success')]//*[@id='successElementContainer']");

	By locErrorMsgConfigureService = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'errormsgbox')]");

	By locErrorMsgClientid = By.xpath(
			".//*[@id='system_admin_third_party_service_configure_id_BODY']//following::*[contains(@class,'errormsgbox')]//*[@id='successElementContainer']");

	By invalidClientId = By.id("thirdParty_ClientId");

	By invalidSecretKey = By.id("thirdParty_Clientsceretkey");

	By locUserName = By.xpath(".//*[@id='username']");

	By locSubmit = By.xpath(".//*[@type='submit']");

	By locPassword = By.xpath(".//*[@id='password']");

	By locSaveBtnConfigurationService = By.id("system_admin_third_party_service_configure_id_save");

	By locRevokeAuthorisationContainer = By.id("system_admin_third_party_service_revoke_id");

	By locRevokeAuthlabel = By.xpath(
			".//*[@id='system_admin_third_party_service_revoke_id']//following::*[normalize-space(text())='Revoke authorisation?']");

	By locClosebtnRevokeAuth = By.id("system_admin_third_party_service_revoke_id_MAIN_CLOSE_BUTTON");

	By locCancelRevokeAuthBtn = By.id("system_admin_third_party_service_revoke_id_close");

	By locRevokeBtnRevokeAuthWindow = By.id("system_admin_third_party_service_revoke_id_revoke");

	By locAddserviceModelContainer = By.id("system_admin_third_party_services_modal_id");

	By locSaveService = By.id("system_admin_third_party_services_modal_id_save");

	By locRemoveWindowClose = By
			.xpath(".//*[@id='system_admin_third_party_service_remove_id' and contains(@style,'display: none;')]");

	By locConfigureContainer = By.id("system_admin_third_party_service_configure_id");

	By locAddServiceContainer = By.xpath(
			".//*[@id='selectedThirdPartyServicesListContainer']//preceding::*[@id='system_admin_add_services']");

	By locMssageBox = By.xpath(".//*[@id='animatedMessageBox' and contains(@style,'display: block;')]");

	By locAdobeEmail = By.id("userEmail");

	By locAdobePwd = By.id("userPassword");

	By locAdobeSubmit = By.id("login");

	By locThirdParty = By.xpath(".//*[normalize-space(text())='"
			+ SystemAdminLabels.THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES + "']");

	By locOkBtn = By.id("collaborateMessageOkButton");
	By locDisableModel = By.id("THIRD_PARTY_DISABLED_MODAL_ID");
	By locOkBtnDisableModel = By.id("THIRD_PARTY_DISABLED_MODAL_ID_ok");
	By locGlobalMsg = By.id("animatedMessageBox");
	By locGlobalClose = By.xpath(".//*[@class='animatePopup_close']");
	By moreActionButtonOpen = By.xpath(
			"//*[@class='dropdown inlineBlock open' or @class='dropdown pull-right margLeft5 open']//*[@data-original-title='More actions' or @title='More actions']");

	Pattern pattern = Pattern.compile(".?\\*+.?");

	WebElement element;

	By customEmailAddressGdprContact = By.id("CUSTOM_EMAIL_ADDRESS_GDPR_CONTACT");

	By locDrpdwnEnableCustomGdprContact = By.id("CUSTOM_GDPR_CONTACT");
	By locchkbnEnableCustomGdprContact = By.id("GDPR_CONTACT");
	By locchkbnShareViaEmailPermission = By.id("CUSTOM_DEFAULT_SHARE_VIA_EMAIL_PERMISSION");
	By enableSystemAdminOpneINOfficeOnlineONOFF = By.id("CUSTOM_OPEN_IN_OFFICE_ONLINE_FOR_SYSTEM");
	By enableSystemAdminOpenInOfficeOnlineLabel = By
			.xpath("//*[normalize-space(text())='Enable Open in Office Online']");
	By documentAnalysisEngineConfigureKiraModal = By
			.xpath(".//*[@id='system_admin_document_analysis_service_configure_id' and @aria-hidden='false']");

	By microBloggingLabel = By
			.id("//*[normalize-space(text())='" + SystemAdminLabels.SYSTEMVOCABULARY_DISPLAYNAME_MICROBLOGGING + "']");
	By customMicroBloggingList = By.id("CUSTOM_MICRO_BLOGGING");
	By microblogging_checkeBox = By.id("MICRO_BLOGGING");

	By forAllUsersCheckbox = By.id("ALL_USERS");

	By forSpecificOrganizationCheckbox = By.id("FOR_SPECIFIC_ORGANIZATION");

	By forSystemAdminCheckbox = By.id("FOR_SYSTEM_ADMINISTRATORS'");

	By specificOrganizationTextbox = By.id("organisationListId");

	By orgCross = By.xpath(".//*[@id='specificOrganisationDivInner']//a");

	String forAllUserLabel = "For all users";
	String forSpecificOrdanizationlabel = "For specific organizations";
	String forSystemAdminlabel = "For System Admins";

	By enableMyTasksLabel = By
			.xpath("//*[normalize-space(text())='" + AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ENABLEMYTASKS + "']");
	By enableMyTasksDropDownList = By.id("CUSTOM_PERSONAL_TASKS");
	By enableMyTasksCheckbox = By.id("PERSONAL_TASKS");

	By thirdPartyRedirecturl = By.id("thirdPartyRedirectURL");
	By selectLink = By.xpath(".//*[@class='greyMeta']//*[normalize-space(.='Select link')]");
	By thirdPartyAdobeHostURL = By.id("thirdPartyAdobeHostURL");

	public SystemAdminSystemSettingsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void setCommentsOnFile(String optionValue) throws InterruptedException
	{
		// scrollToElement(commentsOnFilesLable);
		Select selectionDrpDwn = new Select(findVisibleElement(commentsOnFilesList));
		String selectedValue = selectionDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			WebElement optionChkbox = findClickableElement(commentsOnFilesCheckbox);
			if (optionChkbox.isSelected())
			{
				optionChkbox.click();
			}
			selectionDrpDwn.selectByVisibleText(optionValue.trim());
			optionChkbox.click();
		}
	}

	@Override
	public void saveSettings() throws InterruptedException
	{
		WebElement saveButton = findVisibleElement(saveButtonLink, Speed.slow);
		saveButton.click();

	}

	@Override
	public SystemAdminWeb goBack()
	{
		scrollToTop();
		WebElement saveButton = findVisibleElement(leftPanel_BackOption);
		saveButton.click();

		return new SystemAdminWeb(driver);
	}

	/**
	 * @author vivek.mishra To remove all labels from default scope microblogging
	 *         list
	 * @creation date 29/03/2018
	 */
	@Override
	public void removeAllLabelsInDefaultScopeMicroblogging()
	{
		deSelectDefaultScopeOfMicroBloggingCheckBox();
		findVisibleElement(defaultScopeOfMicroblogging, Speed.slow);
		List<WebElement> elements = driver.findElements(defaultScopeOfMicrobloggingList);
		for (int i = elements.size() - 1; i > 0; i--)
		{
			WebElement element = findVisibleElement(By.xpath("//*[contains(@id,'as-selections')]/li[" + i + "]/a"),
					Speed.slow);
			element.click();
		}
	}

	/**
	 * @author vivek.mishra
	 * @param labelName
	 *        to be added To add a label in default scope microblogging text box
	 * @creation date 02/04/2018
	 */
	@Override
	public void addLabelInDefaultScopeMicroBlogging(String labelName)
	{
		findVisibleElement(defaultScopeOfMicroblogging, Speed.slow);
		element = findVisibleElement(defaultScopeMicrobloggingTextBox, Speed.slow);
		element.click();
		element.sendKeys(labelName.trim());
		element = findVisibleElement(
				By.xpath("//*[contains(@class,'as-result-item') and @title='" + labelName.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra To select the default scope of microblogging check box
	 * @creation date 02/04/2018
	 */
	@Override
	public void selectDefaultScopeOfMicroBloggingCheckBox()
	{
		WebElement checkBox = findVisibleElement(defaultScopeOfMicrobloggingCheckBox, Speed.slow);
		if (!checkBox.isSelected())
		{
			checkBox.click();
		}
	}

	/**
	 * @author vivek.mishra To select the default scope of microblogging check box
	 * @creation date 02/04/2018
	 */
	@Override
	public void deSelectDefaultScopeOfMicroBloggingCheckBox()
	{
		WebElement checkBox = findVisibleElement(defaultScopeOfMicrobloggingCheckBox, Speed.slow);
		if (checkBox.isSelected())
		{
			checkBox.click();
		}
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
	 * @return
	 * @author tejash.trivedi
	 * @throws InterruptedException
	 */
	@Override
	public boolean verifyDocumentAnalysisOptionWithDropDown() throws InterruptedException
	{
		scrollToElement(documentAnalysisEngineConfigurationLbl);
		return isDisplayed(documentAnalysisEngineConfigurationLbl)
				&& isDisplayed(documentAnalysisEngineConfigurationHighqDropdown);
	}

	@Override
	public void clickonDocumentAnalysisEngineConfigurationDropdown(String option)
	{
		if (!isDisplayed(openedDropdown))
		{
			WebElement documentAnalysisEngineConfigurationDD = findVisibleElement(
					By.xpath(".//*[@id='engineServiceSettingText_" + option.toUpperCase() + "']"), Speed.slow);
			documentAnalysisEngineConfigurationDD.click();
		}
	}

	/**
	 * @param option
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyDocumentAnalysisHighQDropdownValues(String option)
	{
		clickonDocumentAnalysisEngineConfigurationDropdown("HIGHQ");
		WebElement value = findVisibleElement(
				By.xpath(".//*[@id='engineServiceMenu_HIGHQ']//*[@title='" + option + "']"));
		return value.isDisplayed();
	}

	/**
	 * @param option
	 * @author tejash.trivedi
	 */
	@Override
	public void selectOptionFromHighqEngineDropdown(String option)
	{
		clickonDocumentAnalysisEngineConfigurationDropdown("HIGHQ");
		WebElement value = findVisibleElement(
				By.xpath(".//*[@id='engineServiceMenu_HIGHQ']//*[@title='" + option + "']"));
		value.click();
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyEnableAIEngineTrainingDropdownDisplay()
	{
		return isDisplayed(enableAIEngineTrainingDropdown);
	}

	/**
	 * @param option
	 * @author tejash.trivedi
	 */
	@Override
	public void selectOptionFromEnableAIEngineTrainingDropdown(String option)
	{
		WebElement elementOption = findVisibleElement(enableAIEngineTrainingDropdown);
		Select select = new Select(elementOption);
		select.selectByVisibleText(option);
	}

	/**
	 * @param engineName
	 * @param option
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	@Override
	public void selectOptionFromDocumentAnalysisEngineConfigurationDropdown(String engineName, String option)
	{
		String xpath = ".//*[@id='selectedEngineDetailListContainer']/div/div";
		int size = driver.findElements(moreActionList).size();
		for (int i = 1; i <= size; i++)
		{
			if (isDisplayed(By.xpath(xpath + "[" + i + "]//strong[text()='" + engineName.toUpperCase() + "']")))
			{
				findPresentElement(By.xpath(xpath + "[" + i + "]//a[@data-original-title='More actions']")).click();
				if (isDisplayed(By.xpath(xpath + "[" + i + "]//a[text()='" + option + "']")))
				{
					findPresentElement(By.xpath(xpath + "[" + i + "]//a[text()='" + option + "']")).click();
				}
				break;
			}
		}
	}

	/**
	 * @param url
	 * @param token
	 * @author tejash.trivedi Created Date 02 May 2018
	 */
	@Override
	public void documentAnalysisEngineConfigureKira(String url, String token)
	{
		if (isDisplayed(documentAnalysisEngineConfigureKiraModal, Speed.slow))
		{
			findVisibleElement(kiraInstanceUrl).sendKeys(url);
			findVisibleElement(kiraToken).sendKeys(token);
			findVisibleElement(kiraTestButton, Speed.slow).click();
			if (isDisplayed(configurstionDoneMsg, Speed.slow))
			{
				findVisibleElement(kiraSaveButton, Speed.slow).click();
			}
		}
	}

	/**
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	@Override
	public void clickOncancelButtonManageModal()
	{
		findVisibleElement(cancelButtonManageModal).click();
	}

	/**
	 * @return
	 * @author tejash.trivedi Created Date 01 May 2018
	 */
	@Override
	public boolean verifyLabelManageModalDisplay()
	{
		return isDisplayed(labelManageModal);
	}

	/**
	 * @return
	 * @author tejash.trivedi Created Date 03 May 2018
	 */
	@Override
	public boolean verifySearchTextboxManageModalDisplay()
	{
		return isDisplayed(searchTextboxManageModal);

	}

	/**
	 * @return
	 * @author tejash.trivedi Created Date 03 May 2018
	 */
	@Override
	public boolean verifyClassifiersListManageModal(List<String> classifiersList)
	{
		int count = 0;
		ArrayList<String> list = new ArrayList<>();
		if (!isDisplayed(classifierSearchNoResultsLabelManageModal))
		{
			int size = driver.findElements(classifiersListManageModal).size();
			for (int i = 1; i <= size; i++)
			{
				WebElement classifier = findVisibleElement(
						By.xpath(".//*[@id='classifierListUl']/li[" + i + "]//span[contains(@id,'label')]"));
				String name = classifier.getText().trim();
				list.add(name);
			}
			for (int i = 0; i < classifiersList.size(); i++)
			{
				if (list.get(i).contains(classifiersList.get(i).trim()))
				{
					count++;
				}
			}
			if (count != 0 && count == classifiersList.size())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This waitTillGlobalMessageDissappears() is used to wait till the global
	 * message navigation disabled
	 * 
	 * @author janki.hirani
	 * @created on 30/04/2018
	 */
	@Override
	public void waitTillGlobalMessageDissappears()
	{
		while (isDisplayed(globalMsgContainer, Speed.slow))
		{
			;
		}
		findPresentElement(globalMessageContainerHidden, Speed.slow);
	}

	/**
	 * @author dhaval.modi select option From Engine
	 * @creation date 20/04/2018
	 * @param engine
	 * @param option
	 * @throws InterruptedException
	 */
	@Override
	public void selectDocumentAnalysis(String engine, String option) throws InterruptedException
	{
		if (engine.equals(highqEngine))
		{
			scrollToElement(highqEngineOptions);
			findVisibleElement(highqEngineOptions).click();
			findVisibleElement(By.xpath(".//*[@id='engineServiceMenu_HIGHQ']//*[@title='" + option.trim() + "']")).click();
		}
		else if (engine.equals(kiraEngine))
		{
			scrollToElement(kiraEngineOptions);
			findVisibleElement(kiraEngineOptions).click();
			findVisibleElement(By.xpath(".//*[@id='engineServiceMenu_KIRA']//*[@title='" + option.trim() + "']"))
					.click();
		}
	}

	/**
	 * @author ankit.motaval verify contact user email Address
	 * @created on 09/04/2018
	 */
	@Override
	public boolean verifyContactUserEmailAddress(String email)
	{
		String contentText = findVisibleElement(customEmailAddressGdprContact, Speed.slow).getAttribute("value");
		return contentText.equals(email.trim());
	}

	/**
	 * @author ankit.motaval set enable GDPR contact drop down value
	 * @created on 09/04/2018
	 */
	@Override
	public void setEnableGdprContactForFooter(String optionValue)
	{
		setEnableGDPRCheckBox(false);
		Select checkoutEnableListDrpDwn = new Select(findVisibleElement(locDrpdwnEnableCustomGdprContact, Speed.slow));
		checkoutEnableListDrpDwn.selectByVisibleText(optionValue.trim());
	}

	@Override
	public void setEnableGDPRCheckBox(boolean status)
	{
		setSelection(locchkbnEnableCustomGdprContact, status);
	}

	/**
	 * @author ankit.motaval verify contact us email from textbox
	 * @created on 09/04/2018
	 */
	@Override
	public boolean isContactUserEmailTextBoxAvailabe()
	{
		return isDisplayed(customEmailAddressGdprContact, Speed.slow);
	}

	/**
	 * @author ankit.motaval Edit contact us email address
	 * @created on 09/04/2018
	 */
	@Override
	public void editContactUsEmailAddress(String email) throws InterruptedException
	{
		WebElement textBox = findVisibleElement(customEmailAddressGdprContact, Speed.slow);
		textBox.clear();
		textBox.sendKeys(email.trim());
	}

	/**
	 * @author ankit.motaval Select share via email permission
	 * @created on 07/05/2018
	 */
	@Override
	public void selectShareViaEmailPermission(String optionValue)
	{
		Select selectionDrpDwn = new Select(findVisibleElement(locchkbnShareViaEmailPermission));
		String selectedValue = selectionDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{
			selectionDrpDwn = new Select(findVisibleElement(locchkbnShareViaEmailPermission, Speed.slow));
			selectionDrpDwn.selectByVisibleText(optionValue.trim());
		}
	}

	/**
	 * @author vivek.mishra To select the default scope of microblogging check box
	 * @creation date 02/04/2018
	 */
	@Override
	public boolean verifyAddedThirdPArtyServicesInSystemSetting(String serviceName)
	{
		findVisibleElement(thirdPartyServicesContainer, Speed.slow);
		return isDisplayed(By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//*[normalize-space(text())='"
				+ serviceName.trim() + "']"), Speed.slow);
	}

	/**
	 * @author dharti.patel click on Add thirdParty service
	 * @throws InterruptedException
	 * @creation date 12/04/2018
	 */
	@Override
	public void clickOnAddBtnOfThirdPartyServices() throws InterruptedException
	{
		findVisibleElement(locThirdParty, Speed.slow, 20);
		findVisibleElement(locAddServiceContainer, Speed.slow);
		WebElement addButton = findVisibleElement(addButtonInAddServices, Speed.slow);
		addButton.click();

	}

	/**
	 * @author dharti.patel verify thirdParty service on Add Button
	 * @creation date 12/04/2018
	 */
	@Override
	public boolean verifydThirdPArtyServicesOnAddServiceModal(String serviceName)
	{
		findVisibleElement(tpServicesDialog, Speed.slow);
		return isDisplayed(By.xpath(".//strong[normalize-space(.)='" + serviceName.trim() + "']"), Speed.slow);
	}

	/**
	 * @author dharti.patel click on More Action On Third Party Service
	 * @creation date 12/04/2018
	 */
	@Override
	public void clickOnOptionInMoreActionOfThirdPartyService(String serviceName, String option)
	{
		clickOnMoreActionOfThirdPartyService(serviceName);
		WebElement moreActionOption = findVisibleElement(
				By.xpath("//*[@class='dropdown inlineBlock open']//*[normalize-space(text())='" + option.trim() + "']"),
				Speed.slow);

		moreActionOption.click();
	}

	/**
	 * @author dharti.patel click on More Action On Third Party Service
	 * @creation date 12/04/2018
	 */
	@Override
	public void clickOnMoreActionOfThirdPartyService(String serviceName)
	{
		WebElement moreActionButton = findVisibleElement(
				By.xpath("//*[@id='selectedThirdPartyServicesListContainer']//*[normalize-space(text())='"
						+ serviceName.trim()
						+ "']/ancestor::*[contains(@class,'col-sm')]//preceding-sibling::*[@id='thirdPartyServiceMoreActionDiv']//*[@class='icon icon-actions dropdown-toggle']"),
				Speed.slow);
		if (!isDisplayed(By.xpath("//*[@id='selectedThirdPartyServicesListContainer']//*[normalize-space(text())='"
				+ serviceName.trim()
				+ "']/ancestor::*[contains(@class,'col-sm')]//preceding-sibling::*[@id='thirdPartyServiceMoreActionDiv']//*[@data-original-title='More actions' and @aria-expanded='true']")))
		{
			moreActionButton.click();

		}
	}

	/**
	 * @author dharti.patel verify status of Third Party Service
	 * @creation date 12/04/2018
	 */
	@Override
	public boolean verifyStatusConfigureService(String serviceName)
	{
		findVisibleElement(
				By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']"));
		if (isDisplayed(
				By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]")))
		{
			findVisibleElement(
					By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
							+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]")).click();
			return ((isDisplayed(By.xpath(".//*[@servicename='" + serviceName.trim() + "' and @title='"
					+ SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS.trim() + "']"), Speed.slow))
					&& (isDisplayed(By.xpath(
							".//*[@title='" + SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS.trim() + "']"),
							Speed.slow))
					&& (isDisplayed(By.xpath(
							".//*[@title='" + SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS.trim() + "']"),
							Speed.slow)));
		}

		return false;
	}

	/**
	 * @author dharti.patel verify Adobe status of Third Party Service
	 * @creation date 19/04/2018
	 */

	@Override
	public boolean verifyStatusConfigureServiceofAdobe(String serviceName)
	{
		findVisibleElement(
				By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']"));
		if (isDisplayed(
				By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]")))
		{
			findVisibleElement(
					By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
							+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]")).click();
			return ((isDisplayed(By.xpath(".//*[@servicename='" + serviceName.trim() + "' and @title='"
					+ SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS.trim() + "']"), Speed.slow))
					&& (isDisplayed(
							By.xpath(".//*[@servicename='" + serviceName.trim() + "']//following::*[@title='"
									+ SystemAdminLabels.THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS.trim() + "']"),
							Speed.slow))
					&& (isDisplayed(
							By.xpath(".//*[@servicename='" + serviceName.trim() + "']//following::*[@title='"
									+ SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS.trim() + "']"),
							Speed.slow)));
		}

		return false;
	}

	/**
	 * @author dharti.patel verify option of More Action Third Party Service
	 * @creation date 19/04/2018
	 */

	@Override
	public boolean verifyConfigureServiceMoreAction(String option)
	{
		boolean displayed = isDisplayed(
				By.xpath("//*[@class='dropdown inlineBlock open']//*[normalize-space(text())='" + option.trim() + "']"),
				Speed.slow);
		findVisibleElement(moreActionButtonOpen, Speed.slow).click();
		return displayed;
	}

	/**
	 * @author dharti.patel Add Third Party Service
	 * @creation date 12/04/2018
	 */
	@Override
	public void addServiceInThirdParty(String serviceName)
	{
		findVisibleElement(locAddServiceLabel, Speed.slow);
		findVisibleElement(By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN
				+ "']//following::*[normalize-space(text())='" + serviceName.trim() + "']"), Speed.slow);

		if (isDisplayed(By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN
				+ "']//following::*[normalize-space(text())='" + serviceName.trim() + "']"), Speed.slow))
		{
			if (!findVisibleElement(By.xpath(".//*[normalize-space(text())='"
					+ SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN + "']//following::*[normalize-space(text())='"
					+ serviceName.trim() + "']//preceding::*[contains(@id,'chkThirdPartyServices')][1]"), Speed.slow)
							.isSelected())
			{
				findVisibleElement(By.xpath(".//*[normalize-space(text())='"
						+ SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN + "']//following::*[normalize-space(text())='"
						+ serviceName.trim() + "']//preceding::*[contains(@id,'chkThirdPartyServices')][1]"),
						Speed.slow).click();
			}

			findVisibleElement(locSaveService, Speed.slow).click();

		}

	}

	/**
	 * @author dharti.patel click on cancel button of third party service
	 * @creation date 12/04/2018
	 */
	@Override
	public void clickOnCancelButtonThirdPartyService()
	{
		findVisibleElement(locCancelAddService).click();
	}

	/**
	 * @author dharti.patel Verify 'third party service' option
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyThirdPartyServicesOption()
	{

		return isDisplayed(locThirdPartyOption, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify 'Add button third party service'
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyAddButtonThirdPartyService()
	{

		return isDisplayed(addButtonInAddServices, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify 'Close button Add third party service'
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyCloseButtonAddThirdPartyService()
	{
		findVisibleElement(locAddserviceModelContainer, Speed.slow);
		return isDisplayed(locCloseBtnAddThirdPartyService, Speed.slow);

	}

	/**
	 * @author dharti.patel Verify 'Add Button' of Add third party service window
	 *         model'
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyAddButtonService(String serviceName)
	{
		findVisibleElement(tpServicesDialog);
		WebElement addservice = findVisibleElement(locSaveService);

		if (isDisplayed(By.xpath(".//*[normalize-space(text())='Add service']//following::*[normalize-space(text())='"
				+ serviceName.trim() + "']")))
		{
			WebElement chkAddservice = findVisibleElement(
					By.xpath(".//*[normalize-space(text())='" + SystemAdminLabels.ADD_SERVICE_IN_SYSTEM_ADMIN.trim()
							+ "']//following::*[normalize-space(text())='" + serviceName.trim()
							+ "']//preceding::*[contains(@id,'chkThirdPartyServices')][1]"));

			if (chkAddservice.isSelected())
			{
				return addservice.isEnabled();
			}
			else
			{
				return !addservice.isEnabled();

			}

		}

		return false;
	}

	/**
	 * @author dharti.patel Verify cancel button of third party service Add window
	 *         model
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyCancelButtonThirdPartyService()
	{
		return isDisplayed(locCancelAddService, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Message 'Select one or more services' third party
	 *         service Add window model
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifySelectServiceMsg()
	{
		findVisibleElement(locAddserviceModelContainer, Speed.slow);
		return isDisplayed(locSelectServiceMsg, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Service Desription On Add Window model
	 * @creation date 16/04/2018
	 */
	public boolean verifyServiceDescriptionAddWindowModel(String serviceName)
	{
		if (isDisplayed(locAddWindowModel, Speed.slow))
		{
			if (isDisplayed(locService, Speed.slow))
			{
				String locserviceName = findVisibleElement(
						By.xpath(".//*[normalize-space(text())='" + serviceName.trim() + "']"), Speed.slow).getText();
				String name = locserviceName.split(" ")[0];
				return ((isDisplayed(locThirdPartyServiceLogo)) && (isDisplayed(By.xpath(
						"(.//*[@id='system_admin_third_party_services_modal_id_BODY']//following::*[normalize-space(text())='"
								+ SystemAdminLabels.ASPADMIN_DOCUSIGN_SANDBOX + "']//following::*[contains(text(),'"
								+ name.trim() + "')])[1]"))))
						&& (isDisplayed(By.xpath(
								".//*[@id='system_admin_third_party_services_modal_id_BODY']//following::*[normalize-space(text())='"
										+ serviceName.trim()
										+ "']/preceding::*[contains(@id,'chkThirdPartyServices')]")));
			}
		}

		return false;
	}

	/**
	 * @author dharti.patel Click on 'Add Button' third party service Add window
	 *         model
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickAddService()
	{
		WebElement addService = findVisibleElement(locAddService, Speed.slow);
		addService.click();

	}

	/**
	 * @author dharti.patel Select 'All Servcie' third party service Add window
	 *         model
	 * @creation date 17/04/2018
	 */
	@Override
	public void selectAllServiceAddWindowModel()
	{
		List<WebElement> els = driver.findElements(locChkAddService);
		for (WebElement el : els)
		{
			if (!el.isSelected())
			{
				el.click();
			}
		}

	}

	/**
	 * @author dharti.patel Verify Message third party service Add window model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyNoServiceAvailableMsg()
	{
		if (isDisplayed(locNoServiceAvailableMsg))
		{
			String locNoServiceMsg = findVisibleElement(locNoServiceAvailableMsg, Speed.slow).getText();
			return locNoServiceMsg
					.equals(SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_NOAVAILABLESERVICETOADD);
		}

		return false;
	}

	/**
	 * @author dharti.patel Verify Remove Button third party service Remove window
	 *         model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRemoveButtonInRemoveWindowModel()
	{
		findVisibleElement(locRemoveServiceContainer, Speed.slow);
		return isDisplayed(locRemoveBtn, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Close Button third party service Remove window
	 *         model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyCloseButtonRemoveModel()
	{
		findVisibleElement(locRemoveServiceContainer, Speed.slow);
		return isDisplayed(locCloseBtnRemoveModel, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Message third party service Remove window model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRemoveMessgaeRemoveModel(String serviceName)
	{
		findVisibleElement(locRemoveServiceContainer, Speed.slow);
		if (isDisplayed(locRemoveWindowBody))
		{
			String removeMsg = findVisibleElement(locRemoveWindowBody, Speed.slow).getText();
			String msg = "Are you sure you want to remove " + serviceName.trim()
					+ " and revoke all documents currently sent for signature?";

			return removeMsg.equals(msg);
		}

		return false;
	}

	/**
	 * @author dharti.patel Verify Remove Label on third party service Remove window
	 *         model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRemoveLabelRemoveModel()
	{
		findVisibleElement(locRemoveServiceContainer, Speed.slow);
		return isDisplayed(locRemoveLabel, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Cancel Button on third party service Remove
	 *         window model
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyCancelButtonRemoveModel()
	{
		findVisibleElement(locRemoveServiceContainer, Speed.slow);
		return isDisplayed(locCancelRemoveWindow, Speed.slow);
	}

	/**
	 * @author dharti.patel click on Cancel Button on third party service Remove
	 *         window model
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickCancelButtonRemoveModel()
	{
		WebElement locCancel = findVisibleElement(locCancelRemoveWindow, Speed.slow);
		locCancel.click();

	}

	/**
	 * @author dharti.patel click on Remove Button on third party service Remove
	 *         window model
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickRemoveButtonRemoveModel()
	{
		WebElement locCancel = findVisibleElement(locRemoveBtn, Speed.slow);
		locCancel.click();
		findPresentElement(locRemoveWindowClose, Speed.slow);
	}

	/**
	 * @author dharti.patel Verify Message After Remove third party service
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyMsgAfterRemoveService()
	{
		// findVisibleElement(locMsgBox,Speed.slow);
		if (isDisplayed(locMssageBox, Speed.slow))
		{
			String locMsgRemoveSuccess = findVisibleElement(locMsgAfterRemoveService).getText();
			return locMsgRemoveSuccess.equals(SystemAdminLabels.THIRDPARTY_SERVICE_REMOVE_MESSAGE);
		}

		return false;
	}

	/**
	 * 'Verify Configure window Label of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyConfigureWindowLabelOfESignatureService(String servicelabel)
	{
		findVisibleElement(locConfigureContainer, Speed.slow);
		String configureLabel = findVisibleElement(labelConfigureServiceWindow, Speed.slow).getText();
		return ((configureLabel.split("-")[0].trim() + "-")
				.equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "-")
				&& servicelabel.equals(configureLabel.split("-")[1].trim()));
	}

	/**
	 * 'Verify Configure window Label of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyConfigureWindowAdobeLabelOfESignatureService(String servicelabel)
	{
		String configureLabel = findVisibleElement(labelConfigureServiceWindow, Speed.slow).getText();
		return ((configureLabel.split("-")[0].trim() + "-")
				.equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION + "-")
				&& servicelabel.equals(configureLabel.split("-")[1].trim()));
	}

	/**
	 * 'Verify Configure Close Model of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 10/04/2018
	 */
	@Override
	public boolean verifyConfigureCloseWindow()
	{
		return isDisplayed(imageConfigureModelCloseWindow, Speed.slow);
	}

	/**
	 * 'Verify Client ID Label of Configure Link of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyClientIdLabel()
	{
		return isDisplayed(labelClientId, Speed.slow);
	}

	/**
	 * 'Verify Client Secret Key Label of Configure Link of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyClientSecretKeyLabel()
	{
		return isDisplayed(labelClientSecretKey, Speed.slow);
	}

	/**
	 * 'Verify Inputbox and Placeholder value of ClientID of Configure Window of
	 * eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
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
	 * 'Verify Inputbox and Placeholder value of Client Secret Key of Configure
	 * Window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
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
	 * @creation date 17/04/2018
	 */
	@Override
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
	 * 'Verify Cancel button of Configure Window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyCancelButtonOfConfigureESignatureService()
	{
		return isDisplayed(locCancelButtonOfConfigureWindow, Speed.slow);
	}

	/**
	 * 'Click on Cancel Button of Configure Model window of eSignature Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnCancelConfigureModel()
	{
		WebElement cancelBtnConfigureWindow = findVisibleElement(locCancelButtonOfConfigureWindow, Speed.slow);
		cancelBtnConfigureWindow.click();
		while (isDisplayed(configurationModalWindowOpened))
		{
			;
			// findPresentElement(configurationModalWindowClosed, Speed.slow);
		}
	}

	/**
	 * Configure ThirdParty Credential
	 * 
	 * @param clientId,
	 *        secretKey, serviceName, clientEmail, clientPassword
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */

	@Override
	public boolean configureThirdPatyCredentials(String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword)
	{
		WebElement clientIDInput = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement secreteKeyInput = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		clientIDInput.clear();
		clientIDInput.sendKeys(clientId);

		secreteKeyInput.clear();
		secreteKeyInput.sendKeys(secretKey);

		clickOnTestConfigureModel();
		if (verifyConfigurationServiceMessage())
		{
			return true;
		}
		else
		{
			if (!verifyConfigurationServiceMessage() && !verifyEnterValuesInConfigureService())
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
		return true;
	}

	/**
	 * Configure ThirdParty Credential for Adobe service
	 * 
	 * @param clientId,
	 *        secretKey, serviceName, clientEmail, clientPassword
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean configureThirdPatyCredentialsForAdobe(String hostUrl, String clientId, String secretKey, String serviceName,
			String clientEmail, String clientPassword) throws InterruptedException
	{
		findVisibleElement(locConfigureContainer, Speed.slow);

		WebElement adobeHostUrl = findVisibleElement(thirdPartyAdobeHostURL);
		WebElement clientIDInput = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement secreteKeyInput = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		adobeHostUrl.clear();
		adobeHostUrl.sendKeys(hostUrl);

		clientIDInput.clear();
		clientIDInput.sendKeys(clientId);

		secreteKeyInput.clear();
		secreteKeyInput.sendKeys(secretKey);

		findVisibleElement(thirdPartyRedirecturl, Speed.slow);

		WebElement adobeHostUrlLink = findVisibleElement(selectLink);
		adobeHostUrlLink.click();

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
				{
					;
				}
				switchWindow();
				if (isDisplayed(locAdobeEmail, Speed.slow))
				{
					findVisibleElement(locAdobeEmail, Speed.slow).sendKeys(clientEmail);

					findVisibleElement(locAdobePwd, Speed.slow).sendKeys(clientPassword);
					findVisibleElement(locAdobeSubmit, Speed.slow).click();
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
		return true;
	}

	/**
	 * Click on 'Test' Button of Configure Model window of eSignature Service
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnTestConfigureModel()
	{
		WebElement testBtnConfigureWindow = findVisibleElement(locTestBtnConfigureWindow, Speed.slow);
		testBtnConfigureWindow.click();
		// while(driver.getWindowHandles().size() <= 1);
	}

	/**
	 * Verify Configuration Service Message
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyConfigurationServiceMessage()
	{

		findVisibleElement(locContainterOfConfigureWindowModel, Speed.slow);

		if (isDisplayed(locSuccessMsgConfigureService, Speed.slow))
		{
			String successMsg = findVisibleElement(successMsgCongurationService, Speed.slow, 1000).getText();
			return successMsg.trim().equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_SUCCESSFUL_MSG);
		}
		return false;
	}

	/**
	 * Verify Configuration Service InputBox error message
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
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
						.equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_CLIENTID.trim())
						&& invalidSecreteKeyInput.getText().trim()
								.equals(AspAndSystemAdmin.ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_KEY.trim()));
			}

		}
		return false;
	}

	/**
	 * Verify Configuration Service Save Button
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifySaveConfigureService()
	{
		return isDisplayed(locSaveBtnConfigurationService, Speed.slow);
	}

	/**
	 * Click on Configuration Service Save Button
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnSaveConfigureService()
	{
		WebElement saveBtnConfigurationService = findVisibleElement(locSaveBtnConfigurationService, Speed.slow);
		saveBtnConfigurationService.click();
	}

	/**
	 * Verify Configuration Service InputBox is not editable
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyConfigurationInputBoxAfterSuccess()
	{
		WebElement clientIdInputBx = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);
		WebElement clientSecreteKeyInputBx = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow,
				Speed.slow);
		return !(clientIdInputBx.isEnabled() && clientSecreteKeyInputBx.isEnabled());
	}

	/**
	 * 'Verify Inputbox value of ClientID of After Configure Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyClientIDInputboxAfterConfigureService()
	{
		WebElement clientIDInputbx = findVisibleElement(locClientIDInputbxOfConfigureWindow, Speed.slow);

		if (clientIDInputbx.isDisplayed())
		{
			String clientIdValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");
			return (pattern.matcher(clientIdValue).matches());

		}
		return false;
	}

	/**
	 * 'Verify Inputbox value of Client Secret Key After Configure Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyClientSecretKeyInputboxAfterConfigureService()
	{
		WebElement clientSecretKeyInputbx = findVisibleElement(locClientSecreteKeyInputbxOfConfigureWindow, Speed.slow);

		if (clientSecretKeyInputbx.isDisplayed())
		{
			String clientSecretKeyValue = findVisibleElement(locClientIDInputbxOfConfigureWindow).getAttribute("value");

			return (pattern.matcher(clientSecretKeyValue).matches());

		}
		return false;
	}

	/**
	 * 'Verify Authorised label Service'
	 * 
	 * @author dharti.patel
	 * @throws InterruptedException
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyAuthorisedService(String serviceName) throws InterruptedException
	{
		Thread.sleep(1000);
		// findVisibleElement(thirdPartyServicesContainer, Speed.slow);
		if (isDisplayed(By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//*[normalize-space(text())='"
				+ serviceName.trim() + "']"), Speed.slow))
		{
			return isDisplayed(
					By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//*[normalize-space(text())='"
							+ serviceName.trim() + "']//following::*[normalize-space(text())='"
							+ SystemAdminLabels.SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_AUTHORISEDLABEL
							+ "']"),
					Speed.slow);
		}

		return false;
	}

	/**
	 * 'Verify Revoke Authorisation label Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRevokeAuthorisationLabel()
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		return isDisplayed(locRevokeAuthlabel, Speed.slow);
	}

	/**
	 * 'Verify Close Button Revoke Authorisation Service'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyCloseButtonRevokeAuthWindow()
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		return isDisplayed(locClosebtnRevokeAuth, Speed.slow);
	}

	/**
	 * 'Verify Revoke Authorisation Body Message'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRevokeAuthBodyMessage(String serviceName)
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		return isDisplayed(By.xpath(
				".//*[@id='system_admin_third_party_service_revoke_id_BODY'][normalize-space(text())='Are you sure you want to revoke the authorisation for "
						+ serviceName.trim() + "? All documents sent for signing with this service will be revoked.']"),
				Speed.slow);
	}

	/**
	 * 'Verify Cancel Button Revoke Authorisation'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyCancelRevokeAuthWindow()
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		return isDisplayed(locCancelRevokeAuthBtn, Speed.slow);
	}

	/**
	 * 'Verify Revoke Button Revoke Authorisation'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyRevokeButtonRevokeAuthWindow()
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		return isDisplayed(locRevokeBtnRevokeAuthWindow, Speed.slow);
	}

	/**
	 * 'Click On Revoke Button Revoke Authorisation'
	 * 
	 * @author dharti.patel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnRevokeButtonOfRevokeAuthWindow()
	{
		findVisibleElement(locRevokeAuthorisationContainer, Speed.slow);
		WebElement locRevokeBtn = findVisibleElement(locRevokeBtnRevokeAuthWindow, Speed.slow);
		locRevokeBtn.click();
	}

	/**
	 * 'Verify Close Button Add ServiceModel when there is no service available'
	 * 
	 * @author dharti.patel
	 * @creation date 18/04/2018
	 */
	@Override
	public boolean verifyCloseAddWindowModel()
	{

		return isDisplayed(locOkBtn);
	}

	/**
	 * 'Click on Close Button Add ServiceModel when there is no service available'
	 * 
	 * @author dharti.patel
	 * @creation date 18/04/2018
	 */
	@Override
	public void clickCloseAddWindowModel()
	{
		findVisibleElement(locOkBtn, Speed.slow).click();

	}

	/**
	 * 'Adobe Authorised service'
	 * 
	 * @author dharti.patel
	 * @throws InterruptedException
	 * @creation date 18/04/2018
	 */
	@Override
	public boolean authoriseAdobeService(String serviceName, String clientEmail, String clientPassword)
			throws InterruptedException
	{
		Thread.sleep(2000);

		if (driver.getWindowHandles().size() == 1)
		{
			return verifyAuthorisedService(serviceName);
		}

		else
		{

			switchWindow();

			if (isDisplayed(locAdobeEmail, Speed.slow))
			{
				findVisibleElement(locAdobeEmail, Speed.slow).sendKeys(clientEmail);
				findVisibleElement(locAdobePwd, Speed.slow).sendKeys(clientPassword);
				findVisibleElement(locAdobeSubmit, Speed.slow).click();
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
	 * 'DocuSign Authorised service'
	 * 
	 * @author dharti.patel
	 * @throws InterruptedException
	 * @creation date 18/04/2018
	 */
	@Override
	public boolean authoriseDocuSignService(String serviceName, String clientEmail, String clientPassword)
			throws InterruptedException
	{

		Thread.sleep(1000);

		if (driver.getWindowHandles().size() == 1)
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
	 * 'click on Adobe option of third party service'
	 * 
	 * @author dharti.patel
	 * @creation date 24/04/2018
	 */
	@Override
	public void selectAdobeOptionThirdPartyService(String serviceName, String option)
	{
		findVisibleElement(
				By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']"));
		findVisibleElement(
				By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]"),
				Speed.slow).click();

		if (option
				.trim().equals(
						findVisibleElement(
								By.xpath(".//*[@servicename='" + serviceName.trim() + "' and @title='"
										+ SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS.trim() + "']"),
								Speed.slow).getText()))
		{
			WebElement locDisabled = findVisibleElement(By.xpath(".//*[@servicename='" + serviceName.trim()
					+ "' and @title='" + SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS.trim() + "']"),
					Speed.slow);
			locDisabled.click();

			if (isDisplayed(By.xpath(".//*[normalize-space(text())='Disable " + serviceName.trim() + "']")))
			{
				if (isDisplayed(locDisableModel))
				{
					findVisibleElement(locOkBtnDisableModel).click();
				}
			}
		}
		else
		{
			WebElement locOption = findVisibleElement(By.xpath(
					".//*[@servicename='" + serviceName.trim() + "']//following::*[@title='" + option.trim() + "']"),
					Speed.slow);
			locOption.click();
		}

	}

	/**
	 * @author dharti.patel click on DocuSign option of third party service
	 * @creation date 12/04/2018
	 */
	@Override
	public void selectDocuSignOptionThirdPartyService(String serviceName, String option)
	{
		findVisibleElement(
				By.xpath(".//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']"));
		findVisibleElement(
				By.xpath("(.//*[@id='selectedThirdPartyServicesListContainer']//following::*[normalize-space()='"
						+ serviceName.trim() + "']//following::*[@class='btn dropdown-toggle'])[1]")).click();
		if (option
				.trim().equals(
						findVisibleElement(
								By.xpath(".//*[@servicename='" + serviceName.trim() + "' and @title='"
										+ SystemAdminLabels.THIRDPARTY_SERVICE_DISABLED_STATUS.trim() + "']"),
								Speed.slow).getText()))
		{
			WebElement lodDocuSignDisable = findVisibleElement(
					By.xpath(".//*[@servicename='" + serviceName.trim() + "' and @title='" + option.trim() + "']"),
					Speed.slow);
			lodDocuSignDisable.click();

			if (isDisplayed(locDisableModel))
			{
				findVisibleElement(locOkBtnDisableModel).click();
			}
		}
		else
		{
			WebElement lodDocuSignDoption = findVisibleElement(By.xpath(".//*[@title='" + option.trim() + "']"),
					Speed.slow);
			lodDocuSignDoption.click();
		}

	}

	/**
	 * @author dharti.patel Count Total Service ON
	 */
	@Override
	public int totalSevriceOn()
	{
		findVisibleElement(thirdPartyServicesContainer, Speed.slow);
		int totalCountServiceOn = 0;

		totalCountServiceOn = driver
				.findElements(By.xpath(".//*[@class='btn dropdown-toggle']//following::*[@class='selected' and @title='"
						+ SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS + "']"))
				.size();

		return totalCountServiceOn;
	}

	/**
	 * @author dharti.patel get name of ON Service
	 */
	@Override
	public String getOnServiceName(int totalServiceOn)
	{
		String service = null;

		WebElement e = findVisibleElement(
				By.xpath("(.//*[@class='btn dropdown-toggle']//following::*[@class='selected' and @title='"
						+ SystemAdminLabels.THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS
						+ "']//preceding::p[@class='marg0']/strong)[last()]"));
		service = e.getText();

		return service;

	}

	/**
	 * @author dharti.patel verify Selected value of dropdown
	 */
	@Override
	public boolean verifySelectedValueOfThirdPartyService(String serviceName, String value)
	{
		findVisibleElement(By.xpath(".//*[normalize-space(text())='" + serviceName.trim()
				+ "']//following::*[@class='btn dropdown-toggle' and @aria-expanded='false']")).click();
		if (isDisplayed(By.xpath("//*[@class='btn dropdown-toggle' and @aria-expanded='true']//following::a[@title='"
				+ value.trim() + "' and @class='selected']")))
		{
			return true;
		}

		return false;
	}

	/**
	 * @author dharti.patel Close global Message
	 */
	@Override
	public void closeGlobalMsg()
	{
		if (isDisplayed(locGlobalMsg))
		{
			findVisibleElement(locGlobalClose).click();
		}
	}

	/**
	 * select Configuration ForS ystemAdmin
	 * 
	 * @param configuration
	 *        Name configuration name
	 * @param configurationDropDown
	 *        configurationDropDown value
	 * @param optionValue
	 *        optionValue
	 * @author paras.vankadi
	 */

	@Override
	public void selectConfigurationForSystemAdmin(By configurationName, By configurationDropDown, String optionValue)
			throws InterruptedException
	{
		Select configurationDrpDwn = new Select(findVisibleElement(configurationDropDown));
		String selectedValue = configurationDrpDwn.getFirstSelectedOption().getText().trim();

		if (!optionValue.equalsIgnoreCase(selectedValue))
		{

			configurationDrpDwn.selectByVisibleText(optionValue.trim());
			// configurationChkbox.click();
		}
	}

	/**
	 * enable Open In Office Online For SystemAdmin property
	 * 
	 * @param optionValue
	 *        ON/OFF
	 * @author paras.vankadi
	 */

	@Override
	public void enableOpenInOfficeOnlineForSystemAdmin(String optionValue)
	{
		try
		{
			selectConfigurationForSystemAdmin(enableSystemAdminOpenInOfficeOnlineLabel,
					enableSystemAdminOpneINOfficeOnlineONOFF, optionValue);

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * verify all System Admin property dropdown values
	 * 
	 * @param optionValue
	 * @author paras.vankadi
	 */
	@Override
	public boolean verifySelectConfigurationVaule(By configurationName, By configurationDropDown, String... accVal)
			throws InterruptedException
	{
		Select configurationDrpDwn = new Select(findVisibleElement(configurationDropDown));

		List<WebElement> selectedValue = configurationDrpDwn.getOptions();
		ArrayList<String> al = new ArrayList<>();

		for (WebElement ele : selectedValue)
		{

			al.add(ele.getText().trim());

		}
		return al.containsAll(Arrays.asList(accVal));

	}

	/**
	 * verify Online Office dropdown values
	 * 
	 * @param optionValue
	 *        OFF/ON
	 * @author paras.vankadi
	 */

	@Override
	public boolean verifyOnlineOfficeForSystemAdmin(String... listOFPropety) throws InterruptedException
	{

		return verifySelectConfigurationVaule(enableSystemAdminOpenInOfficeOnlineLabel,
				enableSystemAdminOpneINOfficeOnlineONOFF, listOFPropety);
	}

	@Override
	public boolean verifySystemAdminOpenOfficeOnline()
	{
		return isDisplayed(enableSystemAdminOpneINOfficeOnlineONOFF, Speed.slow);

	}

	/**
	 * This method is used to click on more action of given engine
	 * 
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param engineName
	 */
	public void clickOnMoreActionOfDocumentAnalysisEngine(String engineName)
	{
		if (isDisplayed(By.xpath("//*[normalize-space(text())='" + engineName.toUpperCase().trim()
				+ "']/ancestor::*[@class='overflowHidden']/preceding-sibling::*//*[@data-original-title='More actions' and not(@aria-expanded='true')]"),
				Speed.slow))
		{
			findClickableElement(By.xpath("//*[normalize-space(text())='" + engineName.toUpperCase().trim()
					+ "']/ancestor::*[@class='overflowHidden']/preceding-sibling::*//*[@data-original-title='More actions' and not(@aria-expanded='true')]"),
					Speed.slow).click();
		}
	}

	/**
	 * @author ashlesha.shastri
	 * @param desired
	 *        state
	 * @param organisation
	 *        name To enable two factor authentication for oraganisation
	 * @created on 09/04/2018
	 */
	@Override
	public void enableTwoFactorAuthentication(boolean desiredState, String... organization)
	{
		setSelection(forSpecificOrganizationCheckbox, desiredState);
		element = findVisibleElement(specificOrganizationTextbox, Speed.slow);
		while (isDisplayed(orgCross, Speed.slow))
			findVisibleElement(orgCross, Speed.slow).click();
		for (String str : organization)
		{
			element.sendKeys(str.trim());
			findVisibleElement(
					By.xpath("(//*[contains(@id,'as-result-item')]//*[normalize-space(.)='" + str.trim() + "'])[1]"),
					Speed.slow).click();
		}
	}

	/**
	 * @author ashlesha.shastri
	 * @param desired
	 *        state
	 * @param organisation
	 *        name To enable two factor authentication for either all users or
	 *        system admin
	 * @created on 09/04/2018
	 */
	@Override
	public void enableTwoFactorAuthenticationForUsersOrSystemAdmin(String twoFactorAuthentication,
			boolean desiredState)
	{

		if (twoFactorAuthentication.equalsIgnoreCase(forAllUserLabel))
			setSelection(forAllUsersCheckbox, desiredState);
		else if (twoFactorAuthentication.equalsIgnoreCase(forSystemAdminlabel))
			setSelection(forSystemAdminCheckbox, desiredState);
	}

	@Override
	public void selectMicroBlogging(String optionToSelect)
	{
		selectConfiguration(microBloggingLabel, customMicroBloggingList, microblogging_checkeBox, optionToSelect);
	}

	/**
	 * @author surbhi.khetan
	 * @param optionToSelect
	 *        To enable my tasks from system settings
	 * @created on 29/05/2018
	 */
	@Override
	public void enableMyTasks(String optionToSelect)
	{
		selectConfiguration(enableMyTasksLabel, enableMyTasksDropDownList, enableMyTasksCheckbox, optionToSelect);

	}

	/**
	 * @author anil.sikhwal
	 * @param engineName
	 *        Engine name value
	 */
	@Override
	public boolean verifyDocumentAnalysisEngineWithDropdown(String engineName)
	{
		By engineNameOption = By.xpath("//*[@id='selectedEngineDetailListContainer']//*[normalize-space(text())='"
				+ engineName.toUpperCase() + "']");
		if (isDisplayed(engineNameOption))
		{
			By engineDropdwn = By
					.xpath(".//*[@id='selectedEngineDetailListContainer']//button/span[@id='engineServiceSettingText_"
							+ engineName.toUpperCase() + "']");
			return isDisplayed(engineDropdwn);
		}
		return false;
	}

	@Override
	public boolean verifyDocumentAnalysisDropdownValues(String engineName, String option)
	{
		clickonDocumentAnalysisEngineConfigurationDropdown(engineName.toUpperCase());
		By value = By
				.xpath(".//*[@id='engineServiceMenu_" + engineName.toUpperCase() + "']//*[@title='" + option + "']");
		return isDisplayed(value);
	}

	/**
	 * @author hasmukh.prajapati
	 * @param url
	 *        enter AdobeSign Host Url
	 */
	public void enterAdobeSignHostUrl(String url)
	{
		findVisibleElement(locConfigureContainer, Speed.slow);

		if (isDisplayed(thirdPartyAdobeHostURL))
		{
			WebElement adobeHostUrl = findVisibleElement(thirdPartyAdobeHostURL);
			adobeHostUrl.sendKeys(url);
		}

	}

	/**
	 * @author hasmukh.prajapati
	 *         verify Reditect Url On ConfigureService
	 */
	public void verifyReditectUrlOnConfigureService()
	{
		if (isDisplayed(thirdPartyRedirecturl))
		{
			if (isDisplayed(selectLink))
			{
				WebElement adobeHostUrl = findVisibleElement(selectLink);
				adobeHostUrl.click();
			}
		}

	}

}
