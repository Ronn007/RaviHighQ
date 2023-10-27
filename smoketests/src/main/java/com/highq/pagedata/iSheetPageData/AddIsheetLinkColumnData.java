package com.highq.pagedata.iSheetPageData;

import java.util.List;

public class AddIsheetLinkColumnData extends AddIsheetColumnData
{
	private List<String> iSheetName;
	private String defaultLinkValue;
	private boolean allowUsersToRenameLinks;

	public List<String> getiSheetName()
	{
		return iSheetName;
	}

	public void setiSheetName(List<String> iSheetName)
	{
		this.iSheetName = iSheetName;
	}

	public String getDefaultLinkValue()
	{
		return defaultLinkValue;
	}

	public void setDefaultLinkValue(String defaultLinkValue)
	{
		this.defaultLinkValue = defaultLinkValue;
	}

	public boolean isAllowUsersToRenameLinks()
	{
		return allowUsersToRenameLinks;
	}

	public void setAllowUsersToRenameLinks(boolean allowUsersToRenameLinks)
	{
		this.allowUsersToRenameLinks = allowUsersToRenameLinks;
	}
}
