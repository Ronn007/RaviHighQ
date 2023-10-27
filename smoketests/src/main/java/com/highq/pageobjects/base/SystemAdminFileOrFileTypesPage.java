package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.EditFileIconsWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;

public interface SystemAdminFileOrFileTypesPage extends BannerPage {
	public EditFileIconsWeb gotoPDFDocumentEditPage();

	public SystemAdminWeb goBack();

	public EditFileIconsWeb gotoDocumentEditPage(String docType);

	public void clickAddButton();
}
