package com.highq.pagedata.iSheetPageData;

public class AddDateAndTimeColumnData extends AddIsheetColumnData
{
	private String selectTheFormat;
	private String dateFormat;
	private String defaultDateOption;
	private String defaultTime;

	public String getSelectTheFormat()
	{
		return selectTheFormat;
	}

	public void setSelectTheFormat(String selectTheFormat)
	{
		this.selectTheFormat = selectTheFormat;
	}

	public String getDateFormat()
	{
		return dateFormat;
	}

	public void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	public String getDefaultDateOption()
	{
		return defaultDateOption;
	}

	public void setDefaultDateOption(String defaultDateOption)
	{
		this.defaultDateOption = defaultDateOption;
	}

	public String getDefaultTime()
	{
		return defaultTime;
	}

	public void setDefaultTime(String defaultTime)
	{
		this.defaultTime = defaultTime;
	}
}
