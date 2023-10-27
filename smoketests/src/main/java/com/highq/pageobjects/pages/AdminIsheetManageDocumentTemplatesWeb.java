package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.base.TestBaseSetup;
import com.highq.pageobjects.base.AdminIsheetManageDocumentTemplatesPage;

public class AdminIsheetManageDocumentTemplatesWeb extends AdminIsheetWeb implements AdminIsheetManageDocumentTemplatesPage
{

	public AdminIsheetManageDocumentTemplatesWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	By backButton = By.id("adminIsheetDocTemplate_backBtnID");
	By addTemplateButton = By.id("adminIsheetDocTemplate_openAddDocTemplatebtnID");
	By docList = By.id("sheetDocReportList");

	/** Add Template Modal */

	By addTemplateModal = By.id("ADD_DOC_TEMPLATE_MODAL");
	By addTemplateModalOpened = By.xpath(".//*[@id='ADD_DOC_TEMPLATE_MODAL' and contains(@style,'display: block')]");
	By addTemplateModalClosed = By.xpath(".//*[@id='ADD_DOC_TEMPLATE_MODAL' and contains(@style,'display: none')]");
	By templateNameInput = By.id("reportNameID");
	By templateFileInput = By.id("sheetDocReport_docxUpload_file");
	By chooseButton = By.id("chooseButtonId");
	By addTemplateSaveButton = By.id("ADD_DOC_TEMPLATE_MODAL_addDocTempletSave");

	By linkedIsheetDropDown = By.xpath(".//*[@data-id='sheetList']");
	By generateDocumentAsDropDown = By.xpath(".//*[@data-id='generateDocumentForm']");
	By selectIsheetColumnDropDown = By.xpath(".//*[@data-id='attachmentColumnList']");
	By addTemplateDropDownBox = By.xpath(".//*[@class='dropdown-menu open']//*[@aria-expanded='true']");
	String addTemplateDropDownList = ".//*[@class='dropdown-menu open']//*[@aria-expanded='true']//li";

	/** Choose Location Modal */

	By chooseLocationModal = By.id("file_module_DDReport_Choose_Location_modal");
	By chooseLocationModalOpened = By.xpath(".//*[@id='file_module_DDReport_Choose_Location_modal' and contains(@style,'display: block')]");
	By chooseLocationModalClosed = By.xpath(".//*[@id='file_module_DDReport_Choose_Location_modal' and contains(@style,'display: none')]");
	By chooseLocationModalSaveButton = By.id("file_module_DDReport_Choose_Location_modal_select");
	By chooseLocationModalCancelButton = By.id("file_module_DDReport_Choose_Location_modal_close");

	/**
	 * Click Back.
	 *
	 * @author nidhi.shah
	 * @return
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public AdminIsheetsWeb clickBack()
	{
		WebElement backButtonEle = findVisibleElement(backButton);
		backButtonEle.click();
		return new AdminIsheetsWeb(driver);
	}

	/**
	 * Click Add Template
	 *
	 * @author nidhi.shah
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void clickAddTemplate()
	{
		WebElement addTemplateButtonEle = findVisibleElement(addTemplateButton);
		addTemplateButtonEle.click();
		findVisibleElement(addTemplateModalOpened, Speed.slow);
	}

	/**
	 * Enter Template Name.
	 *
	 * @author nidhi.shah
	 * @param templateName
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void addTemplateName(String templateName)
	{
		WebElement templateNameInputEle = findVisibleElement(templateNameInput);
		templateNameInputEle.sendKeys(templateName.trim());
	}

	/**
	 * Select Template Type.
	 *
	 * @author nidhi.shah
	 * @param templateType
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectTemplateType(String templateType)
	{
		WebElement templateTypeEle = findVisibleElement(By.xpath(".//*[normalize-space(text())='" + templateType.trim() + "']//*[@type='radio']"));
		templateTypeEle.click();
	}

	/**
	 * Add Template File.
	 *
	 * @author nidhi.shah
	 * @param file
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void addTemplateFile(String file)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + file;
		findPresentElement(templateFileInput).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * Select Linked Isheet Name
	 *
	 * @author nidhi.shah
	 * @param isheetName
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectLinkedIsheet(String isheetName)
	{
		selectOptionFromDropDown(linkedIsheetDropDown, addTemplateDropDownBox, addTemplateDropDownList, isheetName);
	}

	/**
	 * Select Linked View Option
	 *
	 * @author nidhi.shah
	 * @param viewType
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectLinkedViewOption(String viewType)
	{
		WebElement templateTypeEle = findVisibleElement(By.xpath(".//*[normalize-space(text())='" + viewType.trim() + "']//*[@type='radio']"));
		templateTypeEle.click();
	}

	/**
	 * Select Linked View Name
	 *
	 * @author nidhi.shah
	 * @param viewName
	 * @param state
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectLinkedView(String viewName, boolean state)
	{
		selectCheckBoxOnAddTemplateModal(viewName, state);
	}

	/**
	 * Select Document Generation Type
	 *
	 * @author nidhi.shah
	 * @param docFormat
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectDocumentGenerationType(String docFormat)
	{
		selectOptionFromDropDown(generateDocumentAsDropDown, addTemplateDropDownBox, addTemplateDropDownList, docFormat);
	}

	/**
	 * Select User Permission Option
	 *
	 * @author nidhi.shah
	 * @param option
	 * @param state
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectUserPermissionOption(String option, boolean state)
	{
		selectCheckBoxOnAddTemplateModal(option, state);
	}

	/**
	 * Select Document Save Option
	 *
	 * @author nidhi.shah
	 * @param option
	 * @param state
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectSaveDocumentOption(String option, boolean state)
	{
		selectCheckBoxOnAddTemplateModal(option, state);
	}

	/**
	 * Select Column
	 *
	 * @author nidhi.shah
	 * @param column
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectIsheetColumnToSaveDocument(String column)
	{
		selectOptionFromDropDown(selectIsheetColumnDropDown, addTemplateDropDownBox, addTemplateDropDownList, column);
	}

	/**
	 * Select Checkbox
	 *
	 * @author nidhi.shah
	 * @param option
	 * @param state
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void selectCheckBoxOnAddTemplateModal(String option, boolean state)
	{
		setSelection(By.xpath(".//*[normalize-space()='" + option.trim() + "']//*[@type='checkbox']"), state);
	}

	/**
	 * Click Choose Button
	 *
	 * @author nidhi.shah
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void clickChooseButton()
	{
		WebElement chooseButtonEle = findVisibleElement(chooseButton);
		chooseButtonEle.click();
	}

	/**
	 * Set location for the document
	 *
	 * @author nidhi.shah
	 * @param location
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void setDocumentFolderLocation(String location)
	{
		By locfile = By.xpath("(.//*[@id='file_module_DDReport_Choose_Location_modal']//*[normalize-space(text())='" + location.trim() + "'])[1]//preceding::*[@class='fancytree-checkbox'][1]");
		setSelection(locfile, true);
	}

	/**
	 * Save choose location modal
	 *
	 * @author nidhi.shah
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void clickSaveOnChooseLocationModal()
	{
		WebElement saveButtonEle = findVisibleElement(chooseLocationModalSaveButton);
		saveButtonEle.click();
	}

	/**
	 * Save Add Template Modal
	 *
	 * @author nidhi.shah
	 * @Created: 25-04-2018
	 * @Modified
	 */
	@Override
	public void clickSaveOnAddTemplateModal()
	{
		WebElement saveButtonEle = findVisibleElement(addTemplateSaveButton);
		saveButtonEle.click();
	}

	/**
	 * Verify If template already exists - True in case of exist and false otherwise
	 *
	 * @param templateName
	 * @return boolean
	 * @author nidhi.shah
	 * @Created: 01-05-2018
	 * @Modified
	 */
	@Override
	public boolean verifyTemplateExists(String templateName)
	{
		if (isDisplayed(docList, Speed.slow))
		{
			return isDisplayed(By.xpath(".//*[@id='sheetDocReportList']//*[normalize-space(text())='" + templateName.trim() + "']"));
		}
		return false;
	}

	/**
	 * Click on given template Name
	 *
	 * @param templateName
	 * @author nidhi.shah
	 * @Created: 01-05-2018
	 * @Modified
	 */
	@Override
	public void clickOnTemplate(String templateName)
	{
		WebElement templateNameEle = findVisibleElement(By.xpath(".//*[@id='sheetDocReportList']//*[normalize-space(text())='" + templateName.trim() + "']"));
		templateNameEle.click();
		findVisibleElement(addTemplateModalOpened, Speed.slow);
	}
}
