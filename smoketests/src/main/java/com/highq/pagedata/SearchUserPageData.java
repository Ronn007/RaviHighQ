package com.highq.pagedata;

import com.highq.base.CollaborateLabel.UserStatus;

public class SearchUserPageData
{
	private UserStatus status;
	private boolean stausLocked;

	public UserStatus getStatus()
	{
		return status;
	}

	public void setStatus(UserStatus status)
	{
		this.status = status;
	}

	public boolean isStausLocked()
	{
		return stausLocked;
	}

	public void setStausLocked(boolean stausLocked)
	{
		this.stausLocked = stausLocked;
	}

}
