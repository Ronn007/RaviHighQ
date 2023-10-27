package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.OrgAdminWeb;

public interface OrgPage extends BannerPage
{
	public void setOrgStatus(String status, boolean requiredState) throws InterruptedException;

	public OrgAdminWeb backToOrgAdmin() throws InterruptedException;

	public void setOrgName(String orgName);
}
