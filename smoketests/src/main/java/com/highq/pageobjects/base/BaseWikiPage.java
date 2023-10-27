package com.highq.pageobjects.base;

import java.util.Map;
import com.highq.pageobjects.pages.SystemAdminWeb;

public interface BaseWikiPage extends BannerPage
{
	public SystemAdminWeb setOrganisation(Map<String, Map<String, Boolean>> orgData) throws InterruptedException;

}
