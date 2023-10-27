package com.highq.pagedata.iSheetPageData;

public class AddMultipleLineTextColumnData extends AddIsheetColumnData
{
	private String numberOfLines;
	private String width;
	private boolean allowRichHtmlext;
	private boolean allowFieldPopulation;

	public String getNumberOfLines()
	{
		return numberOfLines;
	}

	public void setNumberOfLines(String numberOfLines)
	{
		this.numberOfLines = numberOfLines;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public boolean isAllowRichHtmlext()
	{
		return allowRichHtmlext;
	}

	public void setAllowRichHtmlext(boolean allowRichHtmlext)
	{
		this.allowRichHtmlext = allowRichHtmlext;
	}

	public boolean isAllowFieldPopulation()
	{
		return allowFieldPopulation;
	}

	public void setAllowFieldPopulation(boolean allowFieldPopulation)
	{
		this.allowFieldPopulation = allowFieldPopulation;
	}
}
