package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.IsheetColumnsWeb;

public interface AddColumnsPage extends BannerPage
{

	public void setColumnName(String columnName);

	public IsheetColumnsWeb saveColumnChanges();
}
