package com.highq.pagedata.iSheetPageData;

public class AddJoinColumnData extends AddIsheetColumnData
{
	private String linkName;
	private String sheet;
	private String displayView;
	private String joinConditionCurrentSheet;
	private String joinConditionTargetSheet;

	public String getLinkName()
	{
		return linkName;
	}

	public void setLinkName(String linkName)
	{
		this.linkName = linkName;
	}

	public String getSheet()
	{
		return sheet;
	}

	public void setSheet(String sheet)
	{
		this.sheet = sheet;
	}

	public String getDisplayView()
	{
		return displayView;
	}

	public void setDisplayView(String displayView)
	{
		this.displayView = displayView;
	}

	public String getJoinConditionCurrentSheet()
	{
		return joinConditionCurrentSheet;
	}

	public void setJoinConditionCurrentSheet(String joinConditionCurrentSheet)
	{
		this.joinConditionCurrentSheet = joinConditionCurrentSheet;
	}

	public String getJoinConditionTargetSheet()
	{
		return joinConditionTargetSheet;
	}

	public void setJoinConditionTargetSheet(String joinConditionTargetSheet)
	{
		this.joinConditionTargetSheet = joinConditionTargetSheet;
	}
}
