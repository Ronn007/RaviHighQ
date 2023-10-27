package com.highq.pageobjects.base;

public interface CreateSheetPage extends BannerPage
{

	public void setTitle(String sheetTitle);

	public void setDescription(String description);

	public void selectStatus(String status);

	public void selectAccessType(String accessType);

	public void selectFieldDescription(String fieldDescription);

	public void fileMetadataTemplate(boolean state);

	public void folderMetadataTemplate(boolean state);

	public void allowAllUserToSeeTheViewLink(boolean state);

	public void allowSections(boolean state);

	public void allowLookup(boolean state);

	public void displayIsheet(boolean state);

	public void displaySearchAsDefaultView(boolean state);

	public void enableVersion(boolean state);

	public void overrideRecordModifiedDate(boolean state);

	public void enableBulkInsertUpdateInBatches(boolean state);

	public void enableRecordLocking(boolean state);

	public AdminIsheetsPage clickSave();

	public AdminIsheetsPage clickCancel();
}
