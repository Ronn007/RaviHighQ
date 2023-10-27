package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import com.highq.pageobjects.pages.SearchUserPageWeb;

public interface InsertSystemUserPage extends BannerPage
{
	public void setOrg(Map<String, List<String>> users);

	public SearchUserPageWeb clickOnSave();

}
