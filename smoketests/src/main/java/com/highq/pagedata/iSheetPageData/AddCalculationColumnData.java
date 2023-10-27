package com.highq.pagedata.iSheetPageData;

import java.util.LinkedHashMap;

public class AddCalculationColumnData extends AddIsheetColumnData
{
	private String decimalPlaces;
	private String formula;
	private LinkedHashMap<String, Object> columnNamesOfFormula;
	private boolean showThousandSeparators;
	private boolean showAsPercentage;
	private String expectedResultOfFormula;

	public String getDecimalPlaces()
	{
		return decimalPlaces;
	}

	public void setDecimalPlaces(String decimalPlaces)
	{
		this.decimalPlaces = decimalPlaces;
	}

	public String getFormula()
	{
		return formula;
	}

	public void setFormula(String formula)
	{
		this.formula = formula;
	}

	public LinkedHashMap<String, Object> getColumnNamesOfFormula()
	{
		return columnNamesOfFormula;
	}

	public void setColumnNamesOfFormula(LinkedHashMap<String, Object> columnNamesOfFormula)
	{
		this.columnNamesOfFormula = columnNamesOfFormula;
	}

	public boolean isShowThousandSeparators()
	{
		return showThousandSeparators;
	}

	public void setShowThousandSeparators(boolean showThousandSeparators)
	{
		this.showThousandSeparators = showThousandSeparators;
	}

	public boolean isShowAsPercentage()
	{
		return showAsPercentage;
	}

	public void setShowAsPercentage(boolean showAsPercentage)
	{
		this.showAsPercentage = showAsPercentage;
	}

	public String getExpectedResultOfFormula()
	{
		return expectedResultOfFormula;
	}

	public void setExpectedResultOfFormula(String expectedResultOfFormula)
	{
		this.expectedResultOfFormula = expectedResultOfFormula;
	}
}
