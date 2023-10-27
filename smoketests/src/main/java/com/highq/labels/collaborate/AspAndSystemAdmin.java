package com.highq.labels.collaborate;

public class AspAndSystemAdmin extends GlobalLabels {

	/*
	 * ########## Constants for ASP and system admin page begins
	 * #######################
	 */

	/* ASP ADMIN USER PERMISSION */
	public static final String ASPADMIN_USERPERMISSION_HEADER = getRBValue(
			"label.display.setPermission_User.middelTitle");// ; "User permission";
	public static final String ASPADMIN_USERROLES_SYSTEMADMIN = "System Admin";
	public static final String ASPADMIN_USERROLES_INTERTNALUSER = "Internal User";
	public static final String ASPADMIN_USERROLES_INTERTNALASPADMIN = "Internal ASP Admin";
	public static final String ASPADMIN_USERROLES_CREATESITE = "Create Site";
	public static final String ASPADMIN_USERROLES_SAVE = getRBValue("ui.button.text.save");// ; "Save";
	public static final String ASPADMIN_USERROLES_CANCEL = getRBValue("ui.button.text.cancel");// ; "Cancel";

	/* ASP ADMIN */
	public static final String ASPADMIN_CONFIGURATION = "Configuration";

	/* ASP ADMIN CONFIGURATION */

	public static final String ASPADMIN_CONFIGURATION_EXPORTOPTION = getRBValue("label.display.Report.ExportOption");// ;
																														// "Export
																														// option";
	public static final String ASPADMIN_CONFIGURATION_SAVE = getRBValue("ui.button.text.save");// ; "Save";
	public static final String ASPADMIN_CONFIGURATION_CHECKOUTENABLE = "Checkout enable";
	public static final String ASPADMIN_CONFIGURATION_ENABLECOMMENTINGFORALLSITE = "Enable commenting for all site";
	public static final String ASPADMIN_CONFIGURATION_ENABLEADEPTOLPAGECOUNT = "Enable adeptol page count";
	public static final String ASPADMIN_CONFIGURATION_ENABLEDOCUMENTREVIEW = "Enable Document Review";
	public static final String ASPADMIN_CONFIGURATION_BULKDOWNLOADWITHPIPESTREAM = "Bulk download with pipe stream";
	public static final String ASPADMIN_CONFIGURATION_DOCUMENTANALYSIS = "Document analysis configuration";

	/*
	 * ########## Constants for ASP and system admin page begins
	 * #######################
	 */

	public static final String ASPADMIN_CONFIGURATION_WIKIMODULEENABLE = "Wiki Enabled";
	public static final String ASPADMIN_CONFIGURATION_BLOGMODULEENABLE = "Blog Enabled";
	public static final String ASPADMIN_CONFIGURATION_TASKMODULEENABLE = "Task Enabled";
	public static final String ASPADMIN_CONFIGURATION_EVENTMODULEENABLE = "Event Enabled";
	public static final String ASPADMIN_CONFIGURATION_QAMODULEENABLE = "Q&A Enabled";
	public static final String ASPADMIN_CONFIGURATION_ISHEETMODULEENABLE = "iSheet Enabled";
	public static final String ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYHIGHQ = "Enable document analysis by HighQ";
	public static final String ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYKIRA = "Enable document analysis by KIRA";
	public static final String ASPADMIN_CONFIGURATION_ENABLEDOCUMENTANALYSISBYLEVERTON = "Enable document analysis by Leverton";
	public static final String ASPADMIN_CONFIGURATION_ENABLEMYFILES = "My files";
	public static final String ASPADMIN_CONFIGURATION_ENABLEMYFILESSHARING = "Enable My files sharing";
	public static final String ASPADMIN_CONFIGURATION_ENABLEAPI = "Enable API";
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE = "eSignature Service";
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX = getRBValue(
			"aspadmin.docusign.sandbox");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_PRODUCTION = getRBValue(
			"aspadmin.docusign.production");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CONFIGURATION = getRBValue(
			"files.thirdPartyService.modal.configure");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENTID = getRBValue(
			"apiregistration.label.clientid");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_DOCUSIGN_SANDBOX_CLIENT_SECRETE_KEY = getRBValue(
			"systemadmin.systemsettings.display.name.modal.clientsceretkey");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_PLACEHOLDER_CLIENTID = getRBValue(
			"systemadmin.systemsettings.display.configure.modal.enterClientID.placeholder");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_PLACEHOLDER_SECRETKEY = getRBValue(
			"systemadmin.systemsettings.display.configure.modal.enterClientSceretKey.placeholder");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_SUCCESSFUL_MSG = getRBValue(
			"systemadmin.systemsettings.display.name.modal.testsuccessful");
	public static final String THIRDPARTY_SERVICE_AUTHORISE_FAIL_MSG = getRBValue(
			"systemadmin.systemsettings.display.name.modal.testfail");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_CLIENTID = getRBValue(
			"systemadmin.systemsettings.display.name.modal.invalidclientidstatus");
	public static final String ASPADMIN_CONFIGURATION_ESIGNATURESERVICE_INVALID_KEY = getRBValue(
			"systemadmin.systemsettings.display.name.modal.invalidclientsecretstatus");
	public static final String OPEN_PDF_DOCUMENT_DIRECTLY = "Open PDF document directly";
	public static final String OFFICE_ONLINE_TOKEN_EXPIRATION_TIME = "Office online token expiration time (in minutes) should not be zero.";

	public static final String ASPADMIN_CONFIGURATION_UI_OPTION_ON = getRBValue("ui.text.on");
	public static final String ASPADMIN_CONFIGURATION_ENABLEFILESHARING_ALLSYSTEMUSERS = getRBValue(
			"systemadmin.systemsettings.enablemyfilesharing.allsystemusers");

	public static final String OFFICE_ONLINE_TOKEN_EXPIRATION_TIME_IN_MINUTES = "Office online token expiration time (in minutes)";
	public static final String ASPADMIN_CONFIGURATION_ENABLEMYTASKS = "My tasks";
}
