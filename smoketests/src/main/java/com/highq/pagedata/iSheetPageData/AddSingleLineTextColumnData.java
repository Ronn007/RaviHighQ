package com.highq.pagedata.iSheetPageData;

public class AddSingleLineTextColumnData extends AddIsheetColumnData
{
	private String maxChar;
	private boolean allowFieldPopulation;
	private String allowPopulationTargetSheet;
	private String allowPopulationTargetSheetView;

	public String getMaxChar()
	{
		return maxChar;
	}

	public void setMaxChar(String maxChar)
	{
		this.maxChar = maxChar;
	}

	public boolean isAllowFieldPopulation()
	{
		return allowFieldPopulation;
	}

	public void setAllowFieldPopulation(boolean allowFieldPopulation)
	{
		this.allowFieldPopulation = allowFieldPopulation;
	}

	public String getAllowPopulationTargetSheet()
	{
		return allowPopulationTargetSheet;
	}

	public void setAllowPopulationTargetSheet(String allowPopulationTargetSheet)
	{
		this.allowPopulationTargetSheet = allowPopulationTargetSheet;
	}

	public String getAllowPopulationTargetSheetView()
	{
		return allowPopulationTargetSheetView;
	}

	public void setAllowPopulationTargetSheetView(String allowPopulationTargetSheetView)
	{
		this.allowPopulationTargetSheetView = allowPopulationTargetSheetView;
	}
}
