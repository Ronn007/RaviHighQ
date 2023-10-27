package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AdminIsheetsWeb;

public interface AdminIsheetManageDocumentTemplatesPage extends BannerPage
{

	public AdminIsheetsWeb clickBack();

	public void clickAddTemplate();

	public void addTemplateName(String templateName);

	public void selectTemplateType(String templateType);

	public void addTemplateFile(String file);

	public void selectLinkedIsheet(String isheetName);

	public void selectLinkedViewOption(String viewType);

	public void selectLinkedView(String viewName, boolean state);

	public void selectDocumentGenerationType(String docFormat);

	public void selectUserPermissionOption(String option, boolean state);

	public void selectSaveDocumentOption(String option, boolean state);

	public void selectIsheetColumnToSaveDocument(String column);

	public void selectCheckBoxOnAddTemplateModal(String option, boolean state);

	public void clickChooseButton();

	public void setDocumentFolderLocation(String location);

	public void clickSaveOnChooseLocationModal();

	public void clickSaveOnAddTemplateModal();

	boolean verifyTemplateExists(String templateName);

	void clickOnTemplate(String templateName);

}
