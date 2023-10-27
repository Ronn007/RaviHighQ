package com.highq.pagedata;

/**
 * @author nidhi.shah
 */
public class EventCategoryData
{
	private String categoryName;

	private String bgColor;

	private Boolean darkText;

	/**
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	/**
	 * @return
	 */
	public String getCategoryName()
	{
		return this.categoryName;
	}

	/**
	 * @param bgColor
	 */
	public void setBgColor(String bgColor)
	{
		this.bgColor = bgColor;
	}

	/**
	 * @return
	 */
	public String getBgColor()
	{
		return this.bgColor;
	}

	/**
	 * @return
	 */
	public Boolean getDarkText()
	{
		return darkText;
	}

	/**
	 * @param darkText
	 */
	public void setDarkText(Boolean darkText)
	{
		this.darkText = darkText;
	}
}
