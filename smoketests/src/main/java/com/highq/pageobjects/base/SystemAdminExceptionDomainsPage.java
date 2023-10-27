package com.highq.pageobjects.base;

public interface SystemAdminExceptionDomainsPage extends BannerPage
{
	public void searchExceptionDomains(String domainName);

	public void gotoExceptionDomain(String domainName);

	public void setDomainName(String domainName);

	public boolean isEnableDomainNameTextBox();

	public boolean isEnableStatusDropdown();

	public boolean VerifySaveButton();

	public boolean VerifyCancelButton();
}
