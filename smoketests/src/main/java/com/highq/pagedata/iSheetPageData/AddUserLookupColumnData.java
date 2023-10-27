package com.highq.pagedata.iSheetPageData;

public class AddUserLookupColumnData extends AddIsheetColumnData
{
	private String selectLookup;
	private String fieldDisplay;
	private boolean allowMultipleUsers;

	public String getSelectLookup()
	{
		return selectLookup;
	}

	public void setSelectLookup(String selectLookup)
	{
		this.selectLookup = selectLookup;
	}

	public String getFieldDisplay()
	{
		return fieldDisplay;
	}

	public void setFieldDisplay(String fieldDisplay)
	{
		this.fieldDisplay = fieldDisplay;
	}

	public boolean isAllowMultipleUsers()
	{
		return allowMultipleUsers;
	}

	public void setAllowMultipleUsers(boolean allowMultipleUsers)
	{
		this.allowMultipleUsers = allowMultipleUsers;
	}

}
