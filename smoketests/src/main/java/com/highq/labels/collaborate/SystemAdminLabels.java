package com.highq.labels.collaborate;

public class SystemAdminLabels extends GlobalLabels
{
	/* ******************************** System Admin -> Add Users Web *************************************/
	public static final String SYSADMIN_ADDUSER_EMAILADDRESSINVALIDMSG = "email address is invalid";
	public static final String SYSADMIN_ADDUSER_NEXT = getRBValue("ui.button.text.next");
	public static final String SYSADMIN_ADDUSER_CANCEL = getRBValue("ui.button.text.cancel");
	public static final String SYSADMIN_ADDUSER_SAVE = getRBValue("ui.button.text.save");
	public static final String SYSADMIN_DONE = getRBValue("ui.buton.text.done");
	public static final String SYSADMIN_EDIT = getRBValue("ui.button.text.edit");

	/* ******************************** System Admin -> File / File Types *****************************/
	public static final String SYSADMIN_FILETYPES_PDFDOCUMENT = "PDF Document";
	public static final String SYSADMIN_FILETYPES_BACK = getRBValue("ui.button.text.back");

	/* ******************************** System Admin -> System Settings Web ****************************/
	public static final String SYSADMIN_SYSTEMSETTINGS_COMMENTONFILES = "Comments on files";
	public static final String SYSADMIN_SYSTEMSETTINGS_BACK = getRBValue("ui.button.text.back");
	public static final String SYSADMIN_SYSTEMSETTINGS_SAVE = getRBValue("ui.button.text.save");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_DISABLED = getRBValue("systemadmin.systemsettings.display.name.modal.disabled");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTOFFINEVERYSITE_TITLE = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultOFFInEverySite.title");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_ENABLEDDEFAULTONINEVERYSITE_TITLE = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultONInEverySite.title");
	public static final String DOCUMENTANALYSIS_DISPLAY_NAME_ENGINECONFIGURATION = "Document analysis engine configuration";
	public static final String SYSADMIN_SYSTEMSETTINGS_DOCUMENTANALYSIS = "Document analysis";

	// getRBValue("documentAnalysis.display.name.engineConfiguration");

	/* ********************************* System Admin Web *************************************************/
	public static final String SYSADMIN_ORGADMIN = "Org admin";
	public static final String SYSADMIN_SYSTEMSETTINGS = "System settings";
	public static final String SYSADMIN_GLOBALNAVIGATION = getRBValue("systemadmin.leftnavigationpanel.globalnavigation.label");
	public static final String SYSADMIN_FILEORFILETYPES = "File / File types";
	public static final String SYSADMIN_SYSTEM_VOCABULARY = "System vocabulary";
	public static final String GDPR_CONTACT_USER_EMAIL_ADDRESS_LABEL = getRBValue("gdpr.contact.user.email.address.label");
	public static final String SYSADMIN_RECIPIENTS_REGISTER = getRBValue("lfs.share.modal.tab.email.access.registerusers");
	public static final String SYSTEM_VOCABULARY = "System vocabulary";
	public static final String SYSTEM_AUDITS_AND_REPORTS = getRBValue("systemadmin.leftnavigationpanel.systemaudit.label");
	public static final String ISHEET_ADMIN = "iSheet admin";
	public static final String RESET_DEFAULTS = "Reset defaults";

	/* *************************************** Third Party ****************************************************************/

	public static final String THIRDPARTY_SERVICE_DISABLED_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.disabled");
	public static final String THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultOFFInEverySite.title");
	public static final String THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultONInEverySite.title");
	public static final String THIRDPARTY_SERVICE_AUTHORISE_OPTION = getRBValue("systemadmin.systemsettings.display.thirdPartyService.option.authorise");
	public static final String UI_TEXT_REMOVE = getRBValue("ui.text.remove");
	public static final String ASPADMIN_DOCUSIGN_SANDBOX = getRBValue("aspadmin.docusign.sandbox");
	public static final String ADD_SERVICE_IN_SYSTEM_ADMIN = getRBValue("systemadmin.systemsettings.display.name.modal.addService");
	public static final String THIRDPARTY_SELECT_SERVICE_MSG = getRBValue("systemadmin.systemsettings.display.name.modal.selectOneOrMoreServices");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_NAME_MODAL_NOAVAILABLESERVICETOADD = getRBValue("systemadmin.systemsettings.display.name.modal.noAvailableServiceToAdd");
	public static final String ADOBE_SIGN_PRODUCTION = "Adobe Sign (production)";
	public static final String ADOBE_SIGN_SANDBOX = "Adobe Sign (sandbox)";
	public static final String DOCUSIGN_SIGN_SANDBOX = "DocuSign (sandbox)";
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_REMOVE_MODAL_MESSAGE = getRBValue("systemadmin.systemsettings.display.remove.modal.message");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_REMOVE_MODAL_TITLE = getRBValue("systemadmin.systemsettings.display.remove.modal.title");
	public static final String THIRDPARTY_SERVICE_REMOVE_MESSAGE = getRBValue("thirdparty.service.remove.message");
	public static final String FILES_THIRDPARTYSERVICE_MODAL_CONFIGURE = getRBValue("files.thirdPartyService.modal.configure");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_OPTION_REVOKEAUTHORISE = getRBValue("systemadmin.systemsettings.display.thirdPartyService.option.revokeAuthorise");
	public static final String SYSTEMADMIN_SYSTEMSETTINGS_DISPLAY_THIRDPARTYSERVICE_AUTHORISEDLABEL = getRBValue("systemadmin.systemsettings.display.thirdPartyService.authorisedLabel");
	public static final String THIRDPARTYSERVICE_DISPLAY_NAME_THIRDPARTYSERVICES = getRBValue("thirdPartyService.display.name.thirdPartyServices");

	public static final String SYSTEM_FILEFILETYPE_ENABLEOPENINOFFICEONLINE_LABLE = getRBValue("systemadmin.filefiletype.icon.enableOpenInOfficeOnline.lable");

	// System Admin Audits And Reports
	public static final String SYSTEM_SENT_FOR_SIGNATURE_SYSTEM_AUDIT = "Sent for signature system audit";
	public static final String SYSTEM_SENT_FOR_SIGNATURE_MSG = getRBValue("files.thirdPartyService.tab.awaiting.esign");

	public static final String SYSTEMVOCABULARY_DISPLAYNAME_MICROBLOGGING = getRBValue("systemvocabulary.displayname.microblogging");
	public static final String FILES_THIRDPARTYSERVICE_TAB_AWAITING_ESIGN_UNLOCK_TRYAGAIN_MODAL_SUCCESS = getRBValue("files.thirdPartyService.tab.awaiting.esign.unlock_tryagain.modal.success");
}
