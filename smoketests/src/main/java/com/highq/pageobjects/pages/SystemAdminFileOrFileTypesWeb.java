package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminFileOrFileTypesPage;

public class SystemAdminFileOrFileTypesWeb extends BannerPageWeb implements SystemAdminFileOrFileTypesPage {

	By pdfDocument = By
			.xpath("//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_FILETYPES_PDFDOCUMENT + "']");
	By backButton = By.xpath(
			".//*[@id='leftPanel']//*[normalize-space(text())='" + SystemAdminLabels.SYSADMIN_FILETYPES_BACK + "']");
	By doc = By.xpath("//*[text()='Add new']");

	By addNewButton = By.xpath("//a[normalize-space(text())='Add new']");

	public SystemAdminFileOrFileTypesWeb(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public EditFileIconsWeb gotoPDFDocumentEditPage() {
		findVisibleElement(pdfDocument).click();
		return new EditFileIconsWeb(driver);
	}

	@Override
	public SystemAdminWeb goBack() {
		scrollToTop();
		findVisibleElement(backButton).click();
		return new SystemAdminWeb(driver);
	}

	@Override
	public EditFileIconsWeb gotoDocumentEditPage(String docType) {
		By lastPageNo = By.xpath("((.//*[@id='divItemPageFooter']//a))[last()]");
		int lastPage = Integer.parseInt(getText(lastPageNo).trim());

		By doc = By.xpath("//*[normalize-space(text())='" + docType.trim() + "']");
		int i = 1;

		do {
			if (isDisplayed(doc)) {
				findVisibleElement(doc, Speed.slow).click();
				break;
			}
			By nextPage = By.xpath(".//*[@id='divItemPageFooter']//a[normalize-space(.)='" + ++i + "']");
			findClickableElement(nextPage, Speed.slow).click();
			findVisibleElement(By.id("divGrid"), Speed.slow);
		} while (i <= lastPage);

		return new EditFileIconsWeb(driver);
	}

	@Override
	public void clickAddButton() {
		findVisibleElement(addNewButton, Speed.slow).click();
	}

}
