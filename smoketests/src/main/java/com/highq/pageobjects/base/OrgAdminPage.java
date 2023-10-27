package com.highq.pageobjects.base;

import java.util.Map;
import com.highq.pageobjects.pages.OrgWeb;
import com.highq.pageobjects.pages.SystemAdminWeb;

public interface OrgAdminPage extends BannerPage
{
	public void verifyOrgPermission(Map<String, Map<String, Boolean>> orgData) throws InterruptedException;

	public OrgWeb gotoOrganization(String organization);

	public SystemAdminWeb backToSystemAdmin() throws InterruptedException;

}
