package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.pageobjects.base.AdminBillingPage;

/**
 * @author nidhi.shah
 */
public class AdminBillingWeb extends BannerPageWeb implements AdminBillingPage
{
	public AdminBillingWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By notesTextArea = By.id("siteAdmin_billingNotesId");
	By saveButton = By.id("billingInformationSaveBtnBottomID");
	By lastInvoiceDateInput = By.id("billingLastInvoiceDate");
	By nextInvoiceDateInput = By.id("siteAdmin_billingNextInvoiceDateId");

	@Override
	public void clickSave()
	{
		WebElement saveEle = findClickableElement(saveButton);
		saveEle.click();
	}

	@Override
	public void setNotes(String notes)
	{
		WebElement notesTextAreaEle = findClickableElement(notesTextArea);
		notesTextAreaEle.clear();
		notesTextAreaEle.sendKeys(notes);
	}

	@Override
	public void setNextInvoiceDate(String nextInvoiceDate)
	{
		WebElement nextInvoiceDateEle = findClickableElement(nextInvoiceDateInput);
		nextInvoiceDateEle.clear();
		nextInvoiceDateEle.sendKeys(nextInvoiceDate.trim());
	}
}
