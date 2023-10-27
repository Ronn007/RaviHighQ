package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import com.highq.pageobjects.pages.BannerPageWeb;

public interface SystemAdminAddUsersPage extends BannerPage
{
	void enterEmailIds(Map<String, List<String>> emailIds);

	Map<Boolean, BannerPageWeb> clickOnNext();

}
