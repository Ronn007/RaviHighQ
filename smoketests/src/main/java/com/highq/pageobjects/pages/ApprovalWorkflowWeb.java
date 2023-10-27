package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.ApprovalWorkflowPage;

public class ApprovalWorkflowWeb extends BannerPageWeb implements ApprovalWorkflowPage
{
	By toapprove = By.xpath(".//*[@id='approvalWorkFlow_tab']//*[@id='approvalWorkFlow_toApproveTab']");
	By PendingApproval = By.xpath(".//*[@id='approvalWorkFlow_tab']//*[@id='approvalWorkFlow_pendingApprovalTab']");
	By rejected = By.xpath("//*[@id='approvalWorkFlow_tab']//*[@id='approvalWorkFlow_rejectedTab']");

	public ApprovalWorkflowWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author khushbu.dhandhukiya
	 *         used to verify ToApprove Tab
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyToApprove()
	{

		return isDisplayed(toapprove);
	}

	/**
	 * @author khushbu.dhandhukiya
	 *         used to verify PendingApproval Tab
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyPendingApproval()
	{

		return isDisplayed(PendingApproval);
	}

	/**
	 * @author khushbu.dhandhukiya
	 *         used to verify Rejected Tab
	 * @creation date 16/04/2018
	 */
	@Override
	public boolean verifyRejected()
	{

		return isDisplayed(rejected);
	}

}
