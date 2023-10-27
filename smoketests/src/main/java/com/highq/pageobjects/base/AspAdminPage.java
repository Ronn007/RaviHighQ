package com.highq.pageobjects.base;

import com.highq.pageobjects.pages.AspConfigurationWeb;

public interface AspAdminPage
{
	public AspConfigurationWeb openConfigurationPage() throws InterruptedException;

	public boolean verifyAspConfigurationPage();
}
