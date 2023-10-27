package com.highq.pageobjects.base;

public interface AdminIsheetSaveAsTemplatePage extends AdminIsheetEditPage
{
	public boolean verifyWarningMsg(String msg, boolean displayed);

	public void enterTemplateName(String tempName);

}
