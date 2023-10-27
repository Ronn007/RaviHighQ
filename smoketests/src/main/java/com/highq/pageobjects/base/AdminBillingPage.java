package com.highq.pageobjects.base;

public interface AdminBillingPage extends BannerPage
{
	public void clickSave();

	public void setNotes(String notes);

	public void setNextInvoiceDate(String nextInvoiceDate);
}
