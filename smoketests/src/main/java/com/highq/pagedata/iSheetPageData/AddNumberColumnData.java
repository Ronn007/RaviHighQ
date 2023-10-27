package com.highq.pagedata.iSheetPageData;

public class AddNumberColumnData extends AddIsheetColumnData
{
	private String minValue;
	private String maxValue;
	private String decimalValue;
	private boolean showAsPercentage;
	private boolean showThousandSeparator;

	public String getMinValue()
	{
		return minValue;
	}

	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	public String getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	public String getDecimalValue()
	{
		return decimalValue;
	}

	public void setDecimalValue(String decimalValue)
	{
		this.decimalValue = decimalValue;
	}

	public boolean isShowAsPercentage()
	{
		return showAsPercentage;
	}

	public void setShowAsPercentage(boolean showAsPercentage)
	{
		this.showAsPercentage = showAsPercentage;
	}

	public boolean isShowThousandSeparator()
	{
		return showThousandSeparator;
	}

	public void setShowThousandSeparator(boolean showThousandSeparator)
	{
		this.showThousandSeparator = showThousandSeparator;
	}
}
