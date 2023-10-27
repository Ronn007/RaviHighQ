package com.highq.pageobjects.base;

public interface ApprovalWorkflowPage extends BannerPage
{

	public boolean verifyToApprove();

	public boolean verifyPendingApproval();

	public boolean verifyRejected();
}
