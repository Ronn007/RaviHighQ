package com.highq.pagedata.iSheetPageData;

import java.util.Date;

public class AddIsheetColumnData
{
	private String columnName;
	private String columnType;
	private String section;
	private String description;
	private String defaultValue;
	private String columnWidth;
	private boolean mandatory;
	private boolean allowSearch;
	private boolean addToDefaultView;
	private String columnConditionDisplayOption;
	private String columnConditionType;
	private String columnConditionFilterOption;
	private String columnConditionFilterOperator;
	private String columnConditionFilterValue;
	private Date columnConditionFilterDate;
	private Date columnConditionFilterTime;

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}

	public String getSection()
	{
		return section;
	}

	public void setSection(String section)
	{
		this.section = section;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getColumnWidth()
	{
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth)
	{
		this.columnWidth = columnWidth;
	}

	public boolean isMandatory()
	{
		return mandatory;
	}

	public void setMandatory(boolean mandatory)
	{
		this.mandatory = mandatory;
	}

	public boolean isAllowSearch()
	{
		return allowSearch;
	}

	public void setAllowSearch(boolean allowSearch)
	{
		this.allowSearch = allowSearch;
	}

	public boolean isAddToDefaultView()
	{
		return addToDefaultView;
	}

	public void setAddToDefaultView(boolean addToDefaultView)
	{
		this.addToDefaultView = addToDefaultView;
	}

	public String getColumnConditionDisplayOption()
	{
		return columnConditionDisplayOption;
	}

	public void setColumnConditionDisplayOption(String columnConditionDisplayOption)
	{
		this.columnConditionDisplayOption = columnConditionDisplayOption;
	}

	public String getColumnConditionType()
	{
		return columnConditionType;
	}

	public void setColumnConditionType(String columnConditionType)
	{
		this.columnConditionType = columnConditionType;
	}

	public String getColumnConditionFilterOption()
	{
		return columnConditionFilterOption;
	}

	public void setColumnConditionFilterOption(String columnConditionFilterOption)
	{
		this.columnConditionFilterOption = columnConditionFilterOption;
	}

	public String getColumnConditionFilterOperator()
	{
		return columnConditionFilterOperator;
	}

	public void setColumnConditionFilterOperator(String columnConditionFilterOperator)
	{
		this.columnConditionFilterOperator = columnConditionFilterOperator;
	}

	public String getColumnConditionFilterValue()
	{
		return columnConditionFilterValue;
	}

	public void setColumnConditionFilterValue(String columnConditionFilterValue)
	{
		this.columnConditionFilterValue = columnConditionFilterValue;
	}

	public Date getColumnConditionFilterDate()
	{
		return columnConditionFilterDate;
	}

	public void setColumnConditionFilterDate(Date columnConditionFilterDate)
	{
		// Add code to convert date in specific format
		this.columnConditionFilterDate = columnConditionFilterDate;
	}

	public Date getColumnConditionFilterTime()
	{
		return columnConditionFilterTime;
	}

	public void setColumnConditionFilterTime(Date columnConditionFilterTime)
	{
		this.columnConditionFilterTime = columnConditionFilterTime;
	}
}
