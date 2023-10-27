package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.CreateSheetWeb;
import com.highq.pageobjects.pages.IsheetColumnsWeb;

public interface AdminIsheetsPage extends BannerPage
{

	public CreateSheetWeb clickOnCreateSheet();

	public boolean verifyIsheetTitle(String isheetTitle);

	public boolean verifyIsheetTitleHasFileMetaEnabled(String isheetTitle);

	public IsheetColumnsWeb addEditColumn(String isheetTitle);

	public boolean verifyIsheetTitleHasFolderMetaEnabled(String isheetTitle);

}
