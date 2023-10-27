package com.highq.pagedata.iSheetPageData;

import java.util.List;

public class AddLookupColumnData extends AddIsheetColumnData
{
	private String sheet;
	private String view;
	private List<String> selectColumns;
	private boolean enforceRelationshipBehavior;
	private boolean allowMultipleValues;
	private boolean displayColumnNameprefix;

	public String getSheet()
	{
		return sheet;
	}

	public void setSheet(String sheet)
	{
		this.sheet = sheet;
	}

	public String getView()
	{
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public List<String> getSelectColumns()
	{
		return selectColumns;
	}

	public void setSelectColumns(List<String> selectColumns)
	{
		this.selectColumns = selectColumns;
	}

	public boolean isEnforceRelationshipBehavior()
	{
		return enforceRelationshipBehavior;
	}

	public void setEnforceRelationshipBehavior(boolean enforceRelationshipBehavior)
	{
		this.enforceRelationshipBehavior = enforceRelationshipBehavior;
	}

	public boolean isAllowMultipleValues()
	{
		return allowMultipleValues;
	}

	public void setAllowMultipleValues(boolean allowMultipleValues)
	{
		this.allowMultipleValues = allowMultipleValues;
	}

	public boolean isDisplayColumnNameprefix()
	{
		return displayColumnNameprefix;
	}

	public void setDisplayColumnNameprefix(boolean displayColumnNameprefix)
	{
		this.displayColumnNameprefix = displayColumnNameprefix;
	}
}
