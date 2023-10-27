package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.GlobalNavigationAdminWeb;
import com.highq.pageobjects.pages.OrgAdminWeb;
import com.highq.pageobjects.pages.SearchUserPageWeb;
import com.highq.pageobjects.pages.SystemAdminExceptionDomainsWeb;
import com.highq.pageobjects.pages.SystemAdminFileOrFileTypesWeb;
import com.highq.pageobjects.pages.SystemAdminIsheetAdminWeb;
import com.highq.pageobjects.pages.SystemAdminSystemSettingsWeb;
import com.highq.pageobjects.pages.SystemAdminSystemVocabularyWeb;
import com.highq.pageobjects.pages.SystemAdminVocabularyWeb;
import com.highq.pageobjects.pages.SystemAuditsReportsWeb;

public interface SystemAdminPage extends BannerPage
{
	public SearchUserPageWeb gotoUserAdmin();

	public OrgAdminWeb gotoOrgAdmin();

	public SystemAdminSystemSettingsWeb gotoSystemSettings();

	public GlobalNavigationAdminWeb gotoGlobalNavigation() throws InterruptedException;

	public SystemAdminFileOrFileTypesWeb gotoFileOrFileTypes();

	public SystemAdminVocabularyWeb gotoSystemVocabularyPage();

	public SystemAdminSystemVocabularyWeb gotoSystemVocabulary();

	public SystemAdminExceptionDomainsWeb gotoExceptionDomains();

	public boolean verifySystemAdminPage();

	public SystemAuditsReportsWeb gotoSystemAuditsAndReports();

	public SystemAdminIsheetAdminWeb gotoIsheetAdmin();

}
