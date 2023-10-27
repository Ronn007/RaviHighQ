package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.SystemAdminFileOrFileTypesWeb;

public interface EditFileIconsPage extends BannerPage
{

	public void addDescription(String desc);

	public void selectFile(String fileName);

	public void onlineViewerSupport(boolean state);

	public void preConvertToPDF(boolean state);

	public void convertToPDF(boolean state);

	public void ocrSupport(boolean state);

	public void enableOnlineEditingViaWebDAV(boolean state);

	public void addDisplayName(String name);

	public SystemAdminFileOrFileTypesWeb clickSave();

	public SystemAdminFileOrFileTypesWeb clickCancel();

	boolean verifyDocumentAnalysisSupportCheckboxDisplay();

	public String getEditFileIconPageTitle();

	public boolean verifyVisibilityDocAnalysisSprtText();

	public String getDocAnalysisSprtText();

	public boolean verifyVisibilityDocAnalysisSprtCheckbox();

	public boolean getStateDocAnalysisSupportCheckbox();

	boolean isOfficeOnlinePropAvilableForFileAndFileType();

	void enableOnlineOfficeEdit(boolean state);

	boolean isDisplayNameVisible();
}
