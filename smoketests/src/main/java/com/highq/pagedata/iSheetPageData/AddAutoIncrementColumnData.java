package com.highq.pagedata.iSheetPageData;

public class AddAutoIncrementColumnData extends AddIsheetColumnData
{
	private String startValue;
	private String prefix;
	private String suffix;
	private String minLength;

	public String getStartValue()
	{
		return startValue;
	}

	public void setStartValue(String startValue)
	{
		this.startValue = startValue;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public String getSuffix()
	{
		return suffix;
	}

	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}

	public String getMinLength()
	{
		return minLength;
	}

	public void setMinLength(String minLength)
	{
		this.minLength = minLength;
	}
}
