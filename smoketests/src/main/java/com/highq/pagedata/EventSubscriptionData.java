package com.highq.pagedata;

/**
 * @author nidhi.shah
 */
public class EventSubscriptionData
{
	private String calendarName;

	private String calendarUrl;

	private Boolean autoUpdate;

	private Boolean updateNow;

	private String category;

	private String syncEvent;

	/**
	 * @param calendarName
	 */
	public void setCalendarName(String calendarName)
	{
		this.calendarName = calendarName;
	}

	/**
	 * @return
	 */
	public String getCalendarName()
	{
		return this.calendarName;
	}

	/**
	 * @param calendarUrl
	 */
	public void setCalendarUrl(String calendarUrl)
	{
		this.calendarUrl = calendarUrl;
	}

	/**
	 * @return
	 */
	public String getCalendarUrl()
	{
		return this.calendarUrl;
	}

	/**
	 * @param autoUpdate
	 */
	public void setAutoUpdate(Boolean autoUpdate)
	{
		this.autoUpdate = autoUpdate;
	}

	/**
	 * @return
	 */
	public Boolean getAutoUpdate()
	{
		return this.autoUpdate;
	}

	/**
	 * @param updateNow
	 */
	public void setUpdateNow(Boolean updateNow)
	{
		this.updateNow = updateNow;
	}

	/**
	 * @return
	 */
	public Boolean getUpdateNow()
	{
		return this.updateNow;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * @return
	 */
	public String getCategory()
	{
		return this.category;
	}

	public String getSyncEvent()
	{
		return syncEvent;
	}

	public void setSyncEvent(String syncEvent)
	{
		this.syncEvent = syncEvent;
	}
}
