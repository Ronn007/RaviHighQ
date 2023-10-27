package com.highq.labels.collaborate;

public class SiteAdminLabels extends GlobalLabels
{

	/* Constants for admin page web */

	/* Temp For Login Testing */
	public static final String LOGIN_TEXT = getRBValue("label.display.login.signin");
	/*
	 * ########## Constants for site admin page web begins #######################
	 */

	/* PANEL HEADINGS */
	public static final String SITEADMIN_LEFTPANEL_SITESETTINGS = getRBValue("site.admin.sitesettings");// ; "Site
																										// settings";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTINGS = getRBValue(
			"site.admin.lefttree.navigation.module.setting");// ; "Module settings";
	public static final String SITEADMIN_LEFTPANEL_QANDA = getRBValue("site.admin.lefttree.navigation.qamanagement");// ;
																														// "Q&A";
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT = getRBValue(
			"site.admin.lefttree.navigation.usermanagement");// ; "User management";
	public static final String SITEADMIN_LEFTPANEL_AUDITSANDREPORTS = getRBValue(
			"label.display.Report.report.navigation.title");// ; "Audits and reports";
	public static final String SITEADMIN_LEFTPANEL_REPORTS = getRBValue("label.display.Report.reports");// ; "Reports";
	public static final String SITEADMIN_LEFTPANEL_AUDITS = getRBValue("document.middlePanel.shareMenu.audit");// ;
																												// "Audits";

	/* SITE SETTING LINKS */

	public static final String SITEADMIN_LEFTPANEL_SITESETTING_GENERAL = getRBValue("site.admin.lefttree.navigation.general");// ; "General";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_MODULES = getRBValue("site.admin.lefttree.navigation.modules");// ; "Modules";
	public static final String SITEADMIN_LEFTPANEL_SITENAVIGATION_MODULES = "Site navigation";// "Site navigation";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_SECURITY = getRBValue("site.admin.lefttree.navigation.security");// ; "Security";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_SPLASHPAGE = getRBValue("site.admin.lefttree.navigation.general.splashpage");// ; "Splash page";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_ANNOUNCEMENTS = getRBValue("site.admin.lefttree.navigation.general.announcements");// ; "Announcements";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_TERMSANDCONDITIONS = getRBValue(
			"site.admin.lefttree.navigation.general.termsAndCondition");// ; "Terms and Conditions";
	public static final String SITEADMIN_LEFTPANEL_SITESETTING_BILLINGINFORMATION = getRBValue(
			"site.admin.lefttree.navigation.general.billing.information");// ; "Billing information";

	public static final String SITEADMIN_LEFTPANEL_SITESETTING_ANNOUNCEMENTS_DISPLAYCONTENT = getRBValue(
			"label.display.site.displaycontent");// ; "Display content";

	/* MODULE SETTING LINKS */

	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_HOME = getRBValue("systemvocabulary.displayname.home");// ;
																														// "Home";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_FILES = getRBValue(
			"systemvocabulary.displayname.documents");// ; "Files";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_WIKI = getRBValue("systemvocabulary.displayname.wiki");// ;
																														// "Wiki";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_BLOG = getRBValue("systemvocabulary.displayname.blog");// ;
																														// "Blog";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_TASKS = getRBValue(
			"systemvocabulary.displayname.task");// ; "Tasks";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_EVENTS = getRBValue(
			"systemvocabulary.displayname.events");// ; "Events";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_ISHEETS = getRBValue(
			"systemvocabulary.displayname.isheets");// ; "iSheets";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_PEOPLE = getRBValue("site.admin.People");// ; "People";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_ACTIVITY = getRBValue(
			"systemvocabulary.displayname.activity");// ; "Activity";
	public static final String SITEADMIN_LEFTPANEL_MODULESETTING_RENAME = "Rename";

	/* Q&A SETTING LINKS */
	public static final String SITEADMIN_LEFTPANEL_QANDASETTING_QANDAPERMISSION = getRBValue(
			"site.admin.lefttree.navigation.qamanagement.qapermissions");// ; "Q&A permissions";

	/* USER MANAGEMENT LINKS */
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT_USERS = getRBValue(
			"site.admin.lefttree.navigation.usermanagement.users");// ; "Users";
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT_GROUPS = getRBValue(
			"site.admin.lefttree.navigation.usermanagement.groups");// ; "Groups";
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT_EMAILALERTS = getRBValue(
			"site.admin.lefttree.navigation.usermanagement.emailAlerts");// ; "Email alerts";
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT_BIDDERORGANISATIONS = getRBValue(
			"site.admin.lefttree.navigation.usermanagement.memberManage");// ; "Bidder organisations";
	public static final String SITEADMIN_LEFTPANEL_USERMANAGEMENT_BIDDERTEAM = getRBValue(
			"site.admin.lefttree.navigation.usermanagement.bidderTeam");// ; "Bidder organisations";

	/* REPORTS LINKS */
	public static final String SITEADMIN_LEFTPANEL_REPORT_SUMMARYREPORT = getRBValue(
			"label.display.Report.summary.report");// ; "Summary report";
	public static final String SITEADMIN_LEFTPANEL_REPORT_ACCESSBYORGANIZATION = getRBValue(
			"label.display.Report.AccessByOrganisation");// ; "Access by organisation";
	public static final String SITEADMIN_LEFTPANEL_REPORT_ACCESSBYUSER = getRBValue(
			"label.display.Report.AccessByUser");// ; "Access by user";
	public static final String SITEADMIN_LEFTPANEL_REPORT_MOST_POPULARACCESS = getRBValue(
			"label.display.Report.mostPopularAccess");// ; "Most popular access";
	public static final String SITEADMIN_LEFTPANEL_REPORT_SITEUSERS = getRBValue("label.display.Report.siteusers");// ;
																													// "Site
																													// users";
	public static final String SITEADMIN_LEFTPANEL_REPORT_LOGINSBYORGANIZATIONS = getRBValue(
			"label.display.Report.LoginByOrganisation");// ; "Logins by organisation";
	public static final String SITEADMIN_LEFTPANEL_REPORT_LOGINSBYUSER = getRBValue(
			"label.display.Report.LoginsByUser");// ; "Logins by user";

	/* AUDITS LINKS */

	public static final String SITEADMIN_LEFTPANEL_AUDIT_USERMANAGEMENT = getRBValue(
			"site.admin.lefttree.navigation.usermanagement");// ; "User management";
	public static final String SITEADMIN_LEFTPANEL_AUDIT_SITEMANAGEMENT = getRBValue(
			"label.display.Report.site.management");// ; "Site management";
	public static final String SITEADMIN_LEFTPANEL_AUDIT_CONTENTMANAGEMENT = getRBValue(
			"label.display.Report.content.management");// ; "Content management";
	public static final String SITEADMIN_LEFTPANEL_AUDIT_LOGIN = getRBValue("ui.button.text.login");// ; "Login";
	public static final String SITEADMIN_LEFTPANEL_AUDIT_QANDAMANAGEMENT = getRBValue(
			"label.display.Report.qa.management");// ; "Q&A management";

	/* ################### SITE ADMIN --> SITE SETTINGS --> MODULE PAGE ######################## */
	public static final String SITEADMIN_MODULESPAGE_MODULEHEADER = getRBValue("admin.modules.module.label");// ;
																												// "Modules";
	public static final String SITEADMIN_MODULESPAGE_RENAME = getRBValue("ui.text.rename");// ; "Rename";
	public static final String SITEADMIN_MODULESPAGE_HOME = getRBValue("systemvocabulary.displayname.home");// ; "Home";
	public static final String SITEADMIN_MODULESPAGE_ACTIVITY = getRBValue("systemvocabulary.displayname.activity");// ;
																													// "Activity";
	public static final String SITEADMIN_MODULESPAGE_FILES = getRBValue("systemvocabulary.displayname.documents");// ;
																													// "Files";
	public static final String SITEADMIN_MODULESPAGE_WIKI = getRBValue("systemvocabulary.displayname.wiki");// ; "Wiki";
	public static final String SITEADMIN_MODULESPAGE_BLOG = getRBValue("systemvocabulary.displayname.blog");// ; "Blog";
	public static final String SITEADMIN_MODULESPAGE_TASKS = getRBValue("systemvocabulary.displayname.task");// ;
																												// "Tasks";
	public static final String SITEADMIN_MODULESPAGE_EVENTS = getRBValue("systemvocabulary.displayname.events");// ;
																												// "Events";
	public static final String SITEADMIN_MODULESPAGE_QANDA = getRBValue("site.admin.lefttree.navigation.qamanagement");// ;
																														// "Q&A";
	public static final String SITEADMIN_MODULESPAGE_ISHEETS = getRBValue("systemvocabulary.displayname.isheets");// ;
																													// "iSheets";
	public static final String SITEADMIN_MODULESPAGE_PEOPLE = getRBValue("site.admin.People");// ; "People";

	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE = getRBValue("admin.modules.sitehomepage.label");// ; "Site landing page";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_SELECTALANDINGPAGE = getRBValue("site.admin.module.select.lending.page");// ; "Select a landing page";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_HOME = getRBValue("systemvocabulary.displayname.home");// ; "Home";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_ACTIVITY = getRBValue("systemvocabulary.displayname.activity");// ; "Activity";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_FILESROOTFOLDER = "Files (root folder)";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_FILESRECENTACTIVITY = "Files (recent activity)";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_FILESADVANCESEARCH = "Files (advanced search)";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_WIKI = getRBValue(
			"systemvocabulary.displayname.wiki");// ; "Wiki";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_BLOG = getRBValue(
			"systemvocabulary.displayname.blog");// ; "Blog";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_TASKS = getRBValue(
			"systemvocabulary.displayname.task");// ; "Tasks";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_EVENTS = getRBValue(
			"systemvocabulary.displayname.events");// ; "Events";
	public static final String SITEADMIN_MODULESPAGE_SITELANDINGPAGE_ISHEETS = getRBValue(
			"systemvocabulary.displayname.isheets");// ; "iSheets";

	/*
	 * ########################## SITE ADMIN --> SITE SETTINGS --> SECURITY PAGE
	 * #########################
	 */

	public static final String SITEADMIN_SECURITY_SAVE = getRBValue("ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_SECURITY_IPCONFIRMATIONMODEL_OK = getRBValue("ui.button.text.ok");// ; "OK";
	public static final String SITEADMIN_SECURITY_IPCONFIRMATIONMODEL_CANCEL = getRBValue("ui.button.text.cancel");// ;
																													// "Cancel";

	/*
	 * ########################## SITE ADMIN --> MODULE SETTINGS --> FILES
	 * #########################
	 */

	public static final String SITEADMIN_MODULESETTINGS_FILES_FILEPERMISSIONHEADER = "File permissions"; // 4.2
	public static final String SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERMATADATA = "File  and folder metadata"; // 4.2
	public static final String SITEADMIN_MODULESETTINGS_FILES_ADVANCEDOPTIONS = "Advanced options"; // 4.2
	public static final String SITEADMIN_MODULESETTINGS_FILES_SETEMAILNOTIFICATION = getRBValue(
			"site.admin.approvalworkflow.setNotification");// ; "Set email notifications";

	public static final String SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGSHEADER = getRBValue(
			"site.admin.files.viewersettings");// ; "Viewer settings";
	public static final String SITEADMIN_MODULESETTINGS_FILES_DRMHEADER = getRBValue(
			"site.admin.files.files.title.drm");// ; "Digital Rights Management(DRM)";
	public static final String SITEADMIN_MODULESETTINGS_FILES_PDFCONVERSIONSETTINGS = getRBValue(
			"site.admin.files.files.drm.pdfconversionsettings");// ; "PDF conversion settings";

	public static final String SITEADMIN_MODULESETTINGS_FILES_SAVE = getRBValue("site.admin.files.save");// ; "Save";

	public static final String SITEADMIN_MODULESETTINGS_FILES_THIRDPARTYSERVICE_CONTINUE = getRBValue(
			"ui.button.text.continue");// ; "Continue";
	public static final String SITEADMIN_MODULESETTINGS_FILES_THIRDPARTYSERVICE_CANCEL = getRBValue(
			"ui.button.text.cancel");// ; "cancelButton";

	public static final String SITEADMIN_MODULESETTINGS_FILES_THIRDPARTYSERVICE_OKBUTTON = getRBValue(
			"ui.button.text.ok");// ; "OK";

	public static final String SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEONLY = getRBValue("site.admin.files.fileandfolderpermissions.siteonly");// ; "site only";
	public static final String SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEANDFOLDERS = getRBValue("site.admin.files.fileandfolderpermissions.siteandfolders");// ; "site and folders";
	public static final String SITEADMIN_MODULESETTINGS_FILES_FILEANDFOLDERPERMISSION_OPTION_SITEFOLDERSANDFILES = getRBValue("site.admin.files.fileandfolderpermissions.sitefoldersandfiles");// ; "site, folders and files";
	public static final String SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION = getRBValue(
			"site.admin.approvalworkflow.setNotification");// ; "Set email notifications";
	public static final String SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_SAVE = getRBValue(
			"ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_CANCEL = getRBValue(
			"ui.button.text.cancel");// ; "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_SELECTALL = getRBValue(
			"label.display.SelectAll");// ; "Select all";
	public static final String SITEADMIN_MODULESETTINGS_WIKI_ENABLEAPPROVALWORKFLOW_SETEMAILNOTIFICATION_CLEARALL = getRBValue(
			"ui.text.clearall");// ; "Clear all";

	public static final String SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGS_STANDARDHTML5VIEWER = getRBValue("site.admin.files.viewersettings.usestandardhtml5viewer");// ; "use standard html5 viewer";
	public static final String SITEADMIN_MODULESETTINGS_FILES_VIEWERSETTINGS_LEGACYFLASHBASEDVIEWER = getRBValue("site.admin.files.viewersettings.uselegacyflashbasedviewer");// ; "use legacy flash-based viewer";
	/*
	 * ########################## SITE ADMIN --> MODULE SETTINGS --> BLOG
	 * #########################
	 */

	public static final String SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION = getRBValue(
			"site.admin.approvalworkflow.setNotification");// ; "Set email notifications";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_SAVE = getRBValue(
			"ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_CANCEL = getRBValue(
			"ui.button.text.cancel");// ; "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_SELECTALL = getRBValue(
			"label.display.SelectAll");// ; "Select all";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_SETEMAILNOTIFICATION_CLEARALL = getRBValue(
			"ui.text.clearall");// ; "Clear all";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_SETPERMISSION = getRBValue(
			"site.admin.blog.category.setPermission");// ; "Set permissions";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_RENAME = getRBValue(
			"site.admin.blog.category.rename");// ; "Rename";
	public static final String SITEADMIN_MODULESETTINGS_BLOG_CATEGORY_REMOVE = getRBValue(
			"site.admin.blog.category.remove");// ; "Remove";

	/*
	 * ########################## SITE ADMIN --> MODULE SETTINGS --> TASK
	 * #########################
	 */

	public static final String SITEADMIN_MODULESETTINGS_TASK_REMOVE = getRBValue("site.admin.task.tasklist.remove");// ;
																													// "Remove";
	public static final String SITEADMIN_MODULESETTINGS_TASK_REMOVELIST = getRBValue(
			"site.admin.task.rmeove.title.removeList");// ; "Remove list ?";
	public static final String SITEADMIN_MODULESETTINGS_TASK_REMOVETASKSTATUS = getRBValue(
			"task.siteadmin.status.label.remove");// ; "Remove task status ?";
	public static final String SITEADMIN_MODULESETTINGS_TASK_REMOVECUSTOMSTATUSES = getRBValue(
			"site.admin.tasks.CustomStatus");// ; "Custom statuses";
	public static final String SITEADMIN_MODULESETTINGS_TASK_REMOVECUSTOMSTATUSES_ADD = getRBValue(
			"site.admin.tasks.Add");// ; "Add";
	public static final String SITEADMIN_MODULESETTINGS_TASK_RENAME = getRBValue("site.admin.task.tasklist.rename");// ;
																													// "Rename";
	public static final String SITEADMIN_MODULESETTINGS_TASK_LISTALREADYEXISTSMESSAGE = getRBValue(
			"site.admin.tasks.listName.exists");// ; "Task list name already exists.";
	public static final String SITEADMIN_MODULESETTINGS_TASK_MODALADD = getRBValue("site.admin.tasks.Add");// ; "Add";
	public static final String SITEADMIN_MODULESETTINGS_TASK_MODALSAVE = getRBValue("ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_MODULESETTINGS_TASK_MODALDELETE = getRBValue("ui.button.text.delete");// ;
																												// "Delete";
	public static final String SITEADMIN_MODULESETTINGS_TASK_MODALEDIT = getRBValue("task.label.edit");// ; "Edit";
	public static final String SITEADMIN_MODULESETTINGS_TASK_MODALREMOVE = getRBValue("ui.text.remove");// ; "Remove";
	public static final String SITEADMIN_MODULESETTINGS_TASK_DELETEALLCOMPLTEDTASKMESSAGE = getRBValue(
			"site.admin.task.deletealltask.body");// ; "Are you sure you want to permanently delete all completed tasks?
													// They will not be retrievable once they are permanently deleted.";
	public static final String SITE_ADMIN_LABEL_TASKTIMELINEVIEWENABLE = getRBValue(
			"site.admin.label.taskTimelineviewEnable"); // Enable Timeline View

	/*
	 * ########################## SITE ADMIN --> MODULE SETTINGS --> EVENTS
	 * #########################
	 */

	public static final String SITEADMIN_MODULESETTINGS_EVENTS_IMPORT_CANCEL = getRBValue("ui.button.text.cancel");// ;
																													// "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_IMPORT_SAVE = getRBValue(
			"site.admin.event.importICSfile.modal.btn.text.import");// ; "Save";

	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SUBSCRIBE_CANCEL = getRBValue("ui.button.text.cancel");// ;
																														// "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SUBSCRIBE_SAVE = getRBValue(
			"site.admin.event.subscribe.modal.Btn.Subscribe");// ; "Save";

	public static final String SITEADMIN_MODULESETTINGS_EVENTS_NEWCATEGORY_CANCEL = getRBValue("ui.button.text.cancel");// ;
																														// "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_ADDCATEGORY_CUSTOMCOLOR = getRBValue(
			"site.admin.event.categories.modal.eventColor.customColor");// ; "Custom color";

	public static final String SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_SAVE = getRBValue("ui.button.text.save");// ;
																													// "Save";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_REMOVE = getRBValue("ui.text.remove");// ;
																												// "Remove";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_EDIT = getRBValue("task.label.edit");// ;
																												// "Edit";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_SETPERMISSION = getRBValue(
			"site.admin.event.category.setPermission");// ; "Set permissions";

	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION = getRBValue(
			"site.admin.approvalworkflow.setNotification");// ; "Set email notifications";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_SAVE = getRBValue(
			"ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_CANCEL = getRBValue(
			"ui.button.text.cancel");// ; "Cancel";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_SELECTALL = getRBValue(
			"label.display.SelectAll");// ; "Select all";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_SETEMAILNOTIFICATION_CLEARALL = getRBValue(
			"ui.text.clearall");// ; "Clear all";
	public static final String SITEADMIN_MODULESETTINGS_EVENTS_CATEGORY_RENAME = getRBValue(
			"site.admin.blog.category.rename");// ; "Rename";

	/*
	 * ########################## SITE ADMIN --> MODULE SETTINGS --> Q&A PREMISSION
	 * #########################
	 */

	public static final String SITEADMIN_QANDAPERMISSION_EDITQANDAPERMISSION = getRBValue(
			"qa.label.edit.qapermissions");// ; "Edit Q&A permissions";
	public static final String SITEADMIN_QANDAPERMISSION_MANAGETOPICS = getRBValue("qa.label.manage.topics");// ;
																												// "Manage
																												// topics";
	public static final String SITEADMIN_QANDAPERMISSION_CANCEL = getRBValue("ui.button.text.cancel");// ; "Cancel";
	public static final String SITEADMIN_QANDAPERMISSION_SAVE = getRBValue("ui.button.text.save");// ; "Save";
	public static final String SITEADMIN_QANDAPERMISSION_ADDTOPIC = getRBValue("qaManagTopic.Add");// ; "Add topic";

	public static final String SITEADMIN_QANDAPERMISSION_LISTOFTOPICS_EDIT = getRBValue("qa.label.edit");// ; "Edit";
	public static final String SITEADMIN_QANDAPERMISSION_LISTOFTOPICS_DELETE = getRBValue("qa.label.delete");// ;
																												// "delete";

	/* ########################## SITE ADMIN --> CONFIGURE SITE STORAGE ######################### */

	public static final String SITEADMIN_CONFIGURESITESTORAGE = getRBValue("ui.button.text.save");// ; "Save";

	/*
	 * ########################## SITE ADMIN --> EMAIL ALERTS
	 * #########################
	 */

	public static final String SITEADMIN_EMAILALERTS_FILTER_ORGANIZATION = getRBValue("site.admin.users.organisations");// ;
																														// "Organisations";
	public static final String SITEADMIN_EMAILALERTS_FILTER_STATUS = getRBValue("label.display.Status");// ; "Status";
	public static final String SITEADMIN_EMAILALERTS_FILTER_FREQUENCY = getRBValue("ui.text.frequency");// ;
																										// "Frequency";

	public static final String SITEADMIN_EMAILALERTS_FILTER_NORESULTFOUND = getRBValue("ui.text.message.nocontent");// ;
																													// "No
																													// results
																													// found";
	public static final String SITEADMIN_EMAILALERTS_FREQUENCY_IMMEDIATE = getRBValue(
			"site.admin.emailAlerts.immediate");// ; "immediate";
	public static final String SITEADMIN_EMAILALERTS_FREQUENCY_DAILY = getRBValue("site.admin.emailAlerts.daily");// ;
																													// "daily";
	public static final String SITEADMIN_EMAILALERTS_FREQUENCY_WEEKLY = getRBValue("site.admin.emailAlerts.weekly");// ;
																													// "weekly";

	/*
	 * ########################## SITE ADMIN --> GENERAL #########################
	 */

	public static final String SITEADMIN_GENERAL_SAVE = getRBValue("site.admin.general.save");// ; "Save";
	public static final String SITEADMIN_GENERAL_SITEOWNER_ADDANOTHERUSER = getRBValue(
			"site.admin.general.addotheruser");// ; "Add other user";

	/* ########################## SITE ADMIN --> PEOPLE ######################### */

	public static final String SITEADMIN_PEOPLE_SAVE = getRBValue("ui.button.text.save");// ; "Save";

	/*
	 * ########################## SITE ADMIN --> ADMIN USER CREATE GROUPS WEB (till
	 * 4.2 ) #########################
	 */

	public static final String SITEADMIN_CREATEGROUPS_GROUPNAME = "1. Group name";
	public static final String SITEADMIN_CREATEGROUPS_GROUPMEMBERS = "2. Group members";
	public static final String SITEADMIN_CREATEGROUPS_NOSITEUSERS = "Site Users [0]";
	public static final String SITEADMIN_CREATEGROUPS_ADD = getRBValue("ui.button.text.add");// ; "Add";
	public static final String SITEADMIN_CREATEGROUPS_REMOVE = getRBValue("ui.text.remove");// ; "Remove";

	public static final String SITEADMIN_CREATEGROUPS_SAVE = getRBValue("site.admin.general.save");// ; "Save";
	public static final String SITEADMIN_CREATEGROUPS_CANCEL = getRBValue("ui.button.text.cancel");// ; "Cancel";

	/*
	 * ########################## SITE ADMIN --> ADMIN USER GROUPS WEB
	 * #########################
	 */

	public static final String SITEADMIN_USERGROUPS_NEWSITEGROUP = getRBValue("site.admin.groups.new.site.group");// ;
																													// "New
																													// site
																													// group";
	public static final String SITEADMIN_USERGROUPS_NEWSYSTEMGROUP = getRBValue("site.admin.groups.system.group");// ;
																													// "System
																													// group";
	public static final String SITEADMIN_USERGROUPS_REMOVEGROUP = getRBValue("site.admin.groups.remove.btn");// ;
																												// "Remove
																												// group";
	public static final String SITEADMIN_USERGROUPS_PERMISSIONSREPORT = "Permissions report";
	public static final String SITEADMIN_USERGROUPS_COPYFILEPERMISSIONS = "Copy file permissions"; // 4.2
	public static final String SITEADMIN_USERGROUPS_GROUPUSERPERMISSION = "Group user permissions"; // 4.2

	public static final String SITEADMIN_USERGROUPS_SAVE = getRBValue("site.admin.groups.newsitegroup.save");// ;
																												// "Save";
	public static final String SITEADMIN_USERGROUPS_CANCEL = getRBValue("ui.button.text.cancel");// ; "Cancel";

	public static final String SITEADMIN_USERGROUPS_SETGROUPPERMISSION = getRBValue(
			"site.admin.groups.set.group.permissions");// ; "Set group permissions";
	public static final String SITEADMIN_USERGROUPS_PERMISSIONREPORT_DOWNLOADLINK = getRBValue(
			"site.admin.group.permissionreport.exportDownload");// ; "Click here to download";
	public static final String SITEADMIN_USERGROUPS_PERMISSIONREPORT_REPORTGENERATINGWAIT = getRBValue(
			"site.admin.group.permissionreport.generating.report.please.wait");// ; "Generating report... please wait";

	public static final String SITEADMIN_USERGROUPS_USERADMIN_SITEADMIN = getRBValue(
			"site.admim.users.siteadmin.display.lable");// ; "Site admin";
	public static final String SITEADMIN_USERGROUPS_USERADMIN_MEMBERADMIN = "Member admin";
	public static final String SITEADMIN_USERGROUPS_USERADMIN_CONTENTADMIN = "Content admin";
	public static final String SITEADMIN_USERGROUPS_USERADMIN_REPORTINGADMIN = "Reporting admin";
	public static final String SITEADMIN_USERGROUPS_PERMISSIONREPORT_QANDAADMIN = "Q&A admin";

	public static final String SITEADMIN_USERGROUPS_EDITMEMBERS = getRBValue("site.admin.groups.edit.members");// ;
																												// "Edit
																												// members";
	public static final String SITEADMIN_USERGROUPS_REMOVE = getRBValue("site.admin.groups.remove");// ; "Remove";

	public static final String SITEADMIN_USERGROUPS_EDITDETAILS = getRBValue("site.admin.groups.edit.details");// ;
																												// "Edit
																												// details";

	public static final String SITEADMIN_USERGROUPS_MOREACTIONS_COPYGROUPPERMISSIONSTO = "Copy group permissions to";

	public static final String SITEADMIN_USERGROUPS_ADMINGROUP = getRBValue("site.admin.groups.admin.group");// ; "admin
																												// group";
	public static final String SITEADMIN_USERGROUPS_SITEGROUP = getRBValue("site.admin.groups.site.group");// ; "site
																											// group";
	public static final String SITEADMIN_USERGROUPS_SYSTEMGROUP = getRBValue("site.admin.groups.system.group");// ;
																												// "system
																												// group";

	/* ######### ADD USERS ########## */

	/* USER ROLES */
	public static final String SITEADMIN_USERS_USERROLE_SITEADMIN = getRBValue("ui.text.siteAdmin");// ; "site admin";
	public static final String SITEADMIN_USERS_USERROLE_MEMBERADMIN = "Member admin";
	public static final String SITEADMIN_USERS_USERROLE_CONTENTADMIN = "Content admin";
	public static final String SITEADMIN_USERS_USERROLE_REPORTINGADMIN = "Reporting admin";
	public static final String SITEADMIN_USERS_USERROLE_QANDAADMIN = "Q&A admin";

	public static final String SITEADMIN_USERS_EXPORT = getRBValue("ui.text.export");// ; "Export";
	public static final String SITEADMIN_USERS_SETEMAILALERTS = "Set email alerts";

	public static final String SITEADMIN_USERS_USERALREADYEXIST = getRBValue(
			"site.admin.user.addUser.confirmdetail.alreadyExistUser.validation.message");// ; "The users you are adding
																							// already exist in this
																							// site.";

	public static final String SITEADMIN_USERS_ADDSITEADMIN_USERS_NEXT = getRBValue("ui.button.text.next");// ; "Next";
	public static final String SITEADMIN_USERS_ADDSITEADMIN_USERS_CANCEL = getRBValue("site.admin.users.cancelButton");// ;
																														// "Cancel";
	public static final String SITEADMIN_USERS_USER_ROLES = getRBValue("label.display.users.roles");// ; "Roles";
	public static final String SITEADMIN_USERS_USER_PERMISSIONS = getRBValue("site.admim.users.setPermission");// ; "Set
																												// permissions";

	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_HOME = getRBValue("systemvocabulary.displayname.home");// ; "Home";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_ACTIVITY = getRBValue("systemvocabulary.displayname.activity");// ; "Activity";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_FILES = getRBValue("systemvocabulary.displayname.documents");// ; "Files";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_WIKI = getRBValue("systemvocabulary.displayname.wiki");// ; "Wiki";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_BLOG = getRBValue("systemvocabulary.displayname.blog");// ; "Blog";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_EVENTS = getRBValue("systemvocabulary.displayname.events");// ; "Events";
	public static final String SITEADMIN_USERS_USER_PERMISSION_MODULES_TASKS = getRBValue("systemvocabulary.displayname.task");// ; "Tasks";
	/*
	 * ######### ADMIN ADVANCED WEB (AVAILABLE IN 4.2 DAPRICATED FROM 4.3)
	 * ##########
	 */

	public static final String SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_VIEW = getRBValue("site.admin.permissions.header.ui.text.view");// ; "view";
	public static final String SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADDFILE = getRBValue("site.admin.permissions.header.ui.text.addfile");// ; "add file";
	public static final String SITEADMIN_USERS_USER_PERMISSION_FOLDERPERMISSION_ADMIN = getRBValue("site.admin.permissions.header.ui.text.admin");// ; "admin";

	/* ######### ADMIN ADVANCED WEB (AVAILABLE IN 4.2 DAPRICATED FROM 4.3) ########## */

	public static final String SITEADMIN_ADVANCED_CHOSEADMINS = "Choose admins";

	public static final String SITEADMIN_ADVANCED_SAVE = getRBValue("ui.button.text.save");// ; "Save";

	public static final String SITEADMIN_ADVANCED_MODEL_OK = getRBValue("ui.button.text.ok");// ; "OK";
	public static final String SITEADMIN_ADVANCED_MODEL_CANCEL = getRBValue("ui.button.text.cancel");// ; "Cancel";

	public static final String SITEADMIN_SELECTALL = getRBValue("site.admin.People.selectAll");// ; "Cancel";

	/* ########## Constants for site admin page web ends ####################### */
	public static final String SITEADMIN_MORE = "More";

	public static final String THIRDPARTY_SERVICE_DISABLED_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.disabled");
	public static final String THIRDPARTY_SERVICE_OFF_EVERY_SITE_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultOFFInEverySite.title");
	public static final String THIRDPARTY_SERVICE_ON_EVERY_SITE_STATUS = getRBValue("systemadmin.systemsettings.display.name.modal.enabledDefaultONInEverySite.title");
	/* ######### Documrnt Analysis Engine ######### */
	public static final String DOCUMENTANALYSISENGINE = "Document Analysis Engine";
	public static final String SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINECONFIGURATION = getRBValue("documentAnalysis.display.name.engineConfiguration"); // "Document analysis engine configuration";
	public static final String SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_HIGHQ = "HIGHQ";
	public static final String SITEADMIN_MODULESETTINGS_FILES_DOCUMENTANALYSISSERVICES_ENGINENAME_KIRA = "KIRA";
}
