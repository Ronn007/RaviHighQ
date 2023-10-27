package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AddColumnsWeb;

public interface IsheetColumnsPage extends BannerPage
{
	public AddColumnsWeb clickAddNewColumn();

	public boolean verifyColumnExists(String columnName);

}
