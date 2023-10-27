package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.FileLabels;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.EditFileIconsPage;

public class EditFileIconsWeb extends BannerPageWeb implements EditFileIconsPage
{

	By fileExtensions = By.id("docExtension");
	By description = By.id("docTypeDescription");
	By selectFile = By.id("fileUpload.upload");
	By onlineViewerSupport = By.id("viewerSupport");
	By preconvertToPDF = By.id("convertToPDFBeforeSend");
	By convertToPDF = By.id("convertToPDF");
	By ocrSupport = By.id("ocrSupport");
	By enableOnlineEditViaWebDAV = By.id("onlineEditingViaWebDAVID");
	By displayName = By.id("onlineEditingViaWebDAVDisplayNameID");

	By saveButton = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_COMMON_SAVE + "']");
	By cancelButton = By.xpath("//*[normalize-space(text())='" + FileLabels.FILES_COMMON_CANCEL + "']");
	By documentAnalysisSupportCheckbox = By.id("docAnalysisSupport");
	By enableOpenInOfficeOnline = By.xpath(".//strong[normalize-space(text())='"
			+ SystemAdminLabels.SYSTEM_FILEFILETYPE_ENABLEOPENINOFFICEONLINE_LABLE + "']");

	By editFileIconPageTitle = By.className("blueHeader");
	By docAnalysisSprtText = By.xpath("//*[@id='docAnalysisSupport']/../preceding-sibling::*[1]");
	By docAnalysisSupportCheckbox = By.id("docAnalysisSupport");

	public EditFileIconsWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void addDescription(String desc)
	{
		WebElement elem = findVisibleElement(description);
		elem.clear();
		elem.sendKeys(desc.trim());
	}

	@Override
	public void selectFile(String fileName)
	{
		WebElement elem = findPresentElement(selectFile);
		elem.sendKeys(fileName.trim());
	}

	@Override
	public void onlineViewerSupport(boolean state)
	{
		setSelection(onlineViewerSupport, state);
	}

	@Override
	public void preConvertToPDF(boolean state)
	{
		setSelection(preconvertToPDF, state);
	}

	@Override
	public void convertToPDF(boolean state)
	{
		setSelection(convertToPDF, state);
	}

	@Override
	public void ocrSupport(boolean state)
	{
		setSelection(ocrSupport, state);
	}

	@Override
	public void enableOnlineEditingViaWebDAV(boolean state)
	{
		setSelection(enableOnlineEditViaWebDAV, state);
	}

	@Override
	public void addDisplayName(String name)
	{
		WebElement elem = findVisibleElement(displayName);
		elem.clear();
		elem.sendKeys(name.trim());
	}

	@Override
	public SystemAdminFileOrFileTypesWeb clickSave()
	{
		findVisibleElement(saveButton).click();
		return new SystemAdminFileOrFileTypesWeb(driver);
	}

	@Override
	public SystemAdminFileOrFileTypesWeb clickCancel()
	{
		findVisibleElement(cancelButton).click();
		return new SystemAdminFileOrFileTypesWeb(driver);
	}

	/**
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyDocumentAnalysisSupportCheckboxDisplay()
	{
		return isDisplayed(documentAnalysisSupportCheckbox);
	}

	/**
	 * verify Offic Online PropAvilable For FileAndFile Type
	 *
	 * @author paras.vankadi
	 */
	@Override
	public boolean isOfficeOnlinePropAvilableForFileAndFileType()
	{
		return isDisplayed(enableOpenInOfficeOnline);
	}

	@Override
	public void enableOnlineOfficeEdit(boolean state)
	{
		By checkBoxopenOnlineOffice = By.id("openInOfficeOnlineID");
		setSelection(checkBoxopenOnlineOffice, state);

	}

	@Override
	public boolean isDisplayNameVisible()
	{
		return isDisplayed(displayName);
	}

	/**
	 * Edit file Icon Title
	 * 
	 * @author anil.sikhwal
	 * @return String
	 */
	@Override
	public String getEditFileIconPageTitle()
	{
		return findVisibleElement(editFileIconPageTitle, Speed.slow).getText();
	}

	/**
	 * @author anil.sikhwal
	 * @return true if document analysis support is visible
	 */
	@Override
	public boolean verifyVisibilityDocAnalysisSprtText()
	{
		return findVisibleElement(docAnalysisSprtText, Speed.slow).isDisplayed();
	}

	/**
	 * @author anil.sikhwal
	 * @return document Analysis Support Text msg
	 */
	@Override
	public String getDocAnalysisSprtText()
	{
		return findVisibleElement(docAnalysisSprtText, Speed.slow).getText();
	}

	/**
	 * @author anil.sikhwal
	 * @return true if 'document analysis support'checkbox is visible
	 */
	@Override
	public boolean verifyVisibilityDocAnalysisSprtCheckbox()
	{
		return isDisplayed(docAnalysisSupportCheckbox);
	}

	/**
	 * verify that document analysis support checkbox selected or not
	 * 
	 * @author anil.sikhwal
	 * @return true if document analysis support checkbox is selected
	 */
	@Override
	public boolean getStateDocAnalysisSupportCheckbox()
	{
		return findVisibleElement(docAnalysisSupportCheckbox, Speed.slow).isSelected();
	}
}
