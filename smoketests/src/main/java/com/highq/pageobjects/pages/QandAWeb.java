package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.QandALabels;
import com.highq.pageobjects.base.QandAPage;

/**
 * @author vivek.mishra
 */
public class QandAWeb extends BannerPageWeb implements QandAPage
{
	public String parentWindow;
	public String childWindow;
	public String innerChildWindow;

	By askQuestionButton = By.id("qa_module_askQuestionID");
	By headerViewButton = By.xpath(".//*[@id='qaHomeMiddlePaneID']//button[normalize-space(.)='" + QandALabels.QANDA_HEADER_VIEW + "']");
	By quickSearchTextBox = By.id("qa_module_quickSearchButtonID");
	By qAndASearchFilterDropDown = By.xpath("//*[contains(@class,'fileSearch')]//*[@*='dropdown']");

	By search_Organisation = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + QandALabels.QANDA_SEARCH_ORGANISATION + "'])[last()]");
	By search_Author = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + QandALabels.QANDA_SEARCH_AUTHOR + "'])[last()]");
	By search_ReadCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + QandALabels.QANDA_SEARCH_READ + "'])[last()]");
	By search_UnreadCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + QandALabels.QANDA_SEARCH_UNREAD + "'])[last()]");
	By search_WithCommentsCheckBox = By.xpath("(//*[contains(@class,'dropAccord')]//*[normalize-space(.)='" + QandALabels.QANDA_SEARCH_WITHCOMMENTS + "'])[last()]");

	/** Modal appears after click on ask question button */
	By askQuestionModal = By.xpath("//*[@id='qaQueAnswerModalID']/div");

	/** Question text box in ask question modal */
	By questionTextBox = By.id("questionText");

	/** Fade modal dialog */
	By fadeModalDialog = By.xpath("//*[@class='modal fade modal-dialog-center in']/div");

	/** ok Button in Fade modal dialog */
	By OkButtonInFadeModalDialog = By.xpath("//*[@class='modal fade modal-dialog-center in']//*[normalize-space()='" + QandALabels.QANDA_OK + "']");

	/** Topic drop down in ask question modal */
	By topicDropDown = By.id("topicListID");

	/** priority drop down in ask question modal */
	By priorityDropDown = By.id("priorityListID");

	/** Browse button in ask question modal */
	By browseButtonInAskQuestionModal = By.id("addEditQAAttachmentsckUploadFile");

	/** FileUpload done tag */
	By btnDone = By.xpath("(//span[contains(text(),'" + QandALabels.QANDA_DONE + "')])[last()]");

	/** Custom message in fade modal */
	By customMessage = By.id("collaborateCustomModalMessage");

	/** Q&A detail container appears after clicking on question */
	By qaDetailContainer = By.id("qaDetailContainer");

	/** close button in Q&A detail container appears after clicking on question */
	By closeButtonInQADetailContainer = By.xpath("//*[contains(@class,'icon icon-remove linkGrey')]");

	/** Show tag in QA detail container */
	By showTagInQADetailContainer = By.id("show");// By.xpath("//*[@id='show']");

	/** Status value in QA detail container */
	By statusValueInQADetailContainer = By.xpath("//*[@class='clearfix qaDetails']//*[contains(text(),'" + QandALabels.QANDA_DETAILCONTAINER_STATUS + "')]//following-sibling::div");

	/** Related To value in QA detail container */
	By relatedToValueInQADetailContainer = By.xpath("//*[@class='clearfix qaDetails']//*[contains(text(),'" + QandALabels.QANDA_DETAILCONTAINER_RELATEDTO + "')]//following-sibling::div");

	/** Topic value in QA detail container */
	By topicValueInQADetailContainer = By.xpath("//*[@class='clearfix qaDetails']//*[contains(text(),'" + QandALabels.QANDA_DETAILCONTAINER_TOPIC + "')]//following-sibling::div");

	/** Priority value in QA detail container */
	By priorityValueInQADetailContainer = By.xpath("//*[@class='clearfix qaDetails']//*[contains(text(),'" + QandALabels.QANDA_DETAILCONTAINER_PRIORITY + "')]//following-sibling::div");

	/** Attached file in QA detail container */
	By attachedFileInQADetailContainer = By.xpath("//*[@class='clearfix qaDetails']//*[contains(text(),'" + QandALabels.QANDA_DETAILCONTAINER_ATTACHMENTS + "')]//following-sibling::div//*[contains(@id,'docid')]");

	/** Question value in QA detail container */
	By questionValueInQADetailContainer = By.xpath("(//*[@class='clearfix qaDetails']//*[@class='truncateHTML']/pre)[last()]");

	/** More action button of a question in QA detail container */
	By questionMoreActionInQADetailContainer = By.xpath("//*[@class='dropdown inlineBlock']/a");

	/** Edit button in More action button of a question in QA detail container */
	By editInMoreActionInQADetailContainer = By.xpath("(//*[contains(@class,'dropdown inline')]//*[normalize-space()='" + QandALabels.QANDA_DETAILCONTAINER_EDIT + "'])[2]");

	/** delete button in More action button of a question in QA detail container */
	By deleteInMoreActionInQADetailContainer = By.xpath("(//*[contains(@class,'dropdown inline')]//*[normalize-space()='" + QandALabels.QANDA_DETAILCONTAINER_DELETE + "'])[2]");

	/** Reply text box in QA detail container */
	By replyTextBoxInQADetailContainer = By.id("quickReply");

	/** Reply tag in QA detail container */
	By replyTagInQADetailContainer = By.xpath("(//*[normalize-space()='" + QandALabels.QANDA_DETAILCONTAINER_REPLY + "'])[1]");

	/** Reply value in QA detail container */
	By replyValueInQADetailContainer = By.xpath("//*[contains(@class,'qaQuestionList')]/pre");

	/** draft reply status */
	By draftReplyStatus = By.xpath("//*[@class='qaQuestionList qaDraft']//*[@class='label label-default']");

	/** Answer modal comes after click on edit reply from QA detail container */
	By answerModal = By.xpath("//*[@class='modal fade in']//*[contains(@class,'modal-content')]");

	/** Answer text box in answer modal comes after click on edit reply from QA detail container */
	By answerTextBoxInAnswerModal = By.id("answerText");

	/** Quick search drop down button */
	By quickSearchDropDown = By.xpath("//*[@class='input-group-btn dropdown']//button[contains(@class,'btn dropdown-toggle')]");

	/** Question list loader */
	By questionLoader = By.xpath("//*[@id='collaborateCommon_imageLoader']//img");

	/** No questions label in question grid */
	By noQuestions = By.xpath("//*[@id='qaRowList']//*[contains(text(),'" + QandALabels.QANDA_NOQUESTIONS + "')]");

	/** Advance search modal */
	By advanceSearchModal = By.id("qa_module_advanceSearch_modal");

	/** Search button in Advance search modal */
	By searchButtonInAdvanceSearchModal = By.id("qa_module_advanceSearch_modal_search");

	/** Cancel button in Advance search modal */
	By cancelButtonInAdvanceSearchModal = By.id("qa_module_advanceSearch_modal_cancel");

	/** Question list in question grid */
	By questionList = By.xpath("//*[contains(@id,'qaRowList')]");

	/** Modified date drop down in Advance search modal */
	By modifiedDateDropDownInAdvanceSearchModal = By.id("qaModifiedDateSelectionID");

	/** Modified date drop down in Advance search modal */
	By fileFolderDropDownInAdvanceSearchModal = By.id("qafolderSelectDrop");

	/** Select items modal in Advance search modal after click on choose option in file/folder */
	By selectItemsModal = By.id("qa_module_advance_search_TreeModal");

	/** Cancel button in Select items modal */
	By cancelButtonInSelectItemsModal = By.id("qa_module_advance_search_TreeModal_close");

	/** Select button in Select items modal */
	By selectButtonInSelectItemsModal = By.id("qa_module_advance_search_TreeModal_add");

	/** Asked on column in question grid */
	By askedOn = By.className("colAsk hidden-xs");

	/** Quick search clear button */
	By quickSearchClearButton = By.xpath("//*[@id='qa_module_quickSearchButtonID']//following-sibling::button");

	/** More action button in */
	By moreActionButtonInQAPage = By.xpath("//*[contains(@class,'clearfix taskTabPanel margBott')]//*[@class='icon icon-actions dropdown-toggle']");

	/** Export modal */
	By exportModal = By.id("qa_module_export_MODAL");

	/** Import template option in Export modal */
	By importTemplateButtonInExportModal = By.xpath("//*[@id='qa_module_export_MODAL']//*[contains(@id,'Import')]");

	/** XlSX option in Export modal */
	By xlsxButtonInExportModal = By.xpath("//*[@id='qa_module_export_MODAL']//*[contains(@id,'XLSX')]");

	/** Cancel button in Export modal */
	By cancelButtonInExportModal = By.id("qa_module_export_MODAL_close");

	/** Export button in Export modal */
	By exportButtonInExportModal = By.id("qa_module_export_MODAL_add");

	/** Import modal */
	By importModal = By.id("qa_module_import_MODAL");

	/** Cancel button in Import modal */
	By cancelButtonInImportModal = By.id("qa_module_import_MODAL_close");

	/** Next button in Import modal */
	By nextButtonInImportModal = By.id("qa_module_import_MODAL_next");

	/** Submit answer check box in Import modal */
	By submitAnswerCheckBoxInImportModal = By.id("importForExportSubmitAnswer");

	/** Browse button in Import modal */
	By browseButtonInImportModal = By.id("qa_module_importModal_FileUploadDivID");

	/** Top more action button in QA detail container */
	By topMoreActionButtonInQADetailContainer = By.xpath("(//*[@class='icon icon-actions dropdown-toggle' and @data-original-title='" + QandALabels.QANDA_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "' ])[2]");

	/** Change topic button in Top more action button in QA detail container */
	By changeTopicInTopMoreActionButtonInQADetailContainer = By.xpath("(//*[normalize-space()='" + QandALabels.QANDA_CHANGETOPIC + "'])[2]");

	/** Print preview button in Top more action button in QA detail container */
	By printPreviewInTopMoreActionButtonInQADetailContainer = By.xpath("(//*[normalize-space()='" + QandALabels.QANDA_PRINTPREVIEW + "'])[2]");

	/** Change topic modal */
	By changeTopicModal = By.id("qa_module_topicChange_modal");

	/** Cancel button in Change topic modal */
	By cancelButtonInChangeTopicModal = By.id("qa_module_topicChange_modal_cancel");

	/** Submit button in Change topic modal */
	By submitButtonInChangeTopicModal = By.id("qa_module_topicChange_modal_changeTopic");

	/** Disclose modal */
	By discloseModal = By.id("qa_module_disclose_question");

	/** Save button in Disclose modal */
	By saveButtonInDiscloseModal = By.id("qa_module_disclose_question_qa_module_disclose_question_save_btn");

	/** Cancel button in Disclose modal */
	By cancelButtonInDiscloseModal = By.id("qa_module_disclose_question_file_module_shareModal_close_btn");

	/** Disclose to all organization check box in Disclose modal */
	By discloseToAllOrganizationCheckBoxInDiscloseModal = By.id("disclosedToAllID");

	/** Close button in print window */
	By closeButtonInPrintWindow = By.xpath("//*[text()='" + QandALabels.QANDA_CLOSE + "']");

	/** Print button in print window */
	By printButtonInPrintWindow = By.xpath("//*[@class='btn btn-default']");

	/** Print preview page */
	By printPreviewPage = By.id("printQuestionDetailBodySection");

	/** Question value in Print preview page */
	By questionValueInPrintPreviewPage = By.xpath("(//*[@class='truncateHTML']/pre)[last()]");

	/** Print page */
	By printPage = By.xpath("(//*[@class='cancel'])[1]");

	/** Delete modal */
	By deleteModal = By.xpath("//*[@id='collaborateCustomMessageModal']//div[@class='modal-content']");

	/** Cancel button in Delete modal */
	By cancelButtonInDeleteModal = By.id("collaborateMessageCancelButton");

	/** Delete button in Delete modal */
	By deleteButtonInDeleteModal = By.id("collaborateMessageOkButton");

	By downnloadingFileMessage = By.xpath("(//*[normalize-space(text())='Processing may take some time. Please wait.'])[last()]");

	By attachmentsTabOpened = By.xpath("//*[@id='qaAttachmentID' and not(@class='hide')]");

	By modal = By.xpath("//*[@class='modal fade in']//*[contains(@class,'modal-content')]");
	
	By rowSizeData = By.xpath("//*[@id='qaRowList']//*[contains(@id,'listQues')]");

	By colSizeData = By.xpath("//*[@id='qaRowList']//*[contains(@id,'listQues')][1]//*[contains(@class,'col')]");

	WebElement element;

	public QandAWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author vivek.mishra
	 */
	public void clickOnAskQuestionButton()
	{
		WebElement askQuestionLink = findVisibleElement(askQuestionButton, Speed.slow);
		askQuestionLink.click();
	}

	/**
	 * @author vivek.mishra
	 */
	public void clickOnHeaderViewButton()
	{
		WebElement headerViewButtonLink = findVisibleElement(headerViewButton, Speed.slow);
		headerViewButtonLink.click();
	}

	/**
	 * @author vivek.mishra
	 */
	public void selectView(String sortByOption)
	{
		WebElement viewOption = findVisibleElement(By.xpath(".//*[@id='qaOrderByOptions']//*[text()='" + sortByOption.trim() + "']"), Speed.slow);
		viewOption.click();
	}

	/**
	 * @author vivek.mishra
	 */
	public void setSearchText(String searchText)
	{
		WebElement searchTextBox = findVisibleElement(quickSearchTextBox, Speed.slow);
		searchTextBox.clear();
		searchTextBox.sendKeys(searchText.trim());
	}

	/**
	 * @author vivek.mishra
	 */
	public void clickOnFilterButton()
	{
		WebElement filterButton = findVisibleElement(qAndASearchFilterDropDown, Speed.slow);
		filterButton.click();
	}

	/**
	 * @author vivek.mishra
	 *         To verify that ask question modal opened or not
	 */
	public boolean verifyAskQuestionModal()
	{
		findVisibleElement(askQuestionModal, Speed.slow);
		return (isDisplayed(askQuestionModal));
	}

	/**
	 * @author vivek.mishra
	 *         To send text in question text box in ask question modal
	 */
	public void sendTextInQuestionTextBox(String text)
	{
		findVisibleElement(questionTextBox, Speed.slow).sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on footer buttons in ask question modal
	 */
	public void clickOnFootersInAskQuestionModal(String footerName)
	{
		findVisibleElement(By.xpath("//*[contains(@id,'qaQueAnswerModalID') and text() = '" + footerName.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To verify ask question button
	 */
	public boolean verifyAskQuestionButton()
	{
		findVisibleElement(askQuestionButton, Speed.slow);
		return (isDisplayed(askQuestionButton, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To verify fade Modal dialog
	 */
	public boolean verifyFadeModal()
	{
		findVisibleElement(fadeModalDialog, Speed.slow);
		return (isDisplayed(fadeModalDialog, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on Ok button in fade modal dialog
	 */
	public void clickOnOkButtonInFadeModalDialog()
	{
		findVisibleElement(OkButtonInFadeModalDialog, Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 *         To add a question with question parameter only
	 */
	public void addQuestion(String question)
	{
		verifyAskQuestionButton();
		clickOnAskQuestionButton();
		verifyAskQuestionModal();
		sendTextInQuestionTextBox(question);
		clickOnFootersInAskQuestionModal("Submit");
		verifyFadeModal();
		clickOnOkButtonInFadeModalDialog();
		verifyQuestionInQuestionListing(question);
	}

	/**
	 * @author vivek.mishra
	 *         To add a question with question parameter only
	 */
	public void addQuestion(String question, String topic, String priority, String file)
	{
		clickOnAskQuestionButton();
		verifyAskQuestionModal();
		sendTextInQuestionTextBox(question);
		if (topic != "")
			selectTopicInAskQuestionModal(topic);
		if (priority != "")
			selectPriorityInAskQuestionModal(priority);
		if (file != "")
		{
			attachFileInAskQuestionModal(file);
		}
		clickOnFootersInAskQuestionModal("Submit");
		verifyFadeModal();
		clickOnOkButtonInFadeModalDialog();
	}

	/**
	 * @author vivek.mishra
	 *         To select the priority in ask question modal
	 */
	public void selectTopicInAskQuestionModal(String topic)
	{
		Select select = new Select(findPresentElement(topicDropDown));
		select.selectByVisibleText(topic);
	}

	/**
	 * @author vivek.mishra
	 *         To click on Topic drop down in ask question modal
	 */
	public void clickOnTopicDropDown()
	{

		findClickableElement(topicDropDown).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Priority drop down in ask question modal
	 */
	public void clickOnPriorityDropDown()
	{
		findClickableElement(priorityDropDown).click();
	}

	/**
	 * @author vivek.mishra
	 *         To select the priority in ask question modal
	 */
	public void selectPriorityInAskQuestionModal(String priority)
	{
		Select select = new Select(findPresentElement(priorityDropDown));
		select.selectByVisibleText(priority);
	}

	/**
	 * @author vivek.mishra
	 *         To select tab in ask question modal
	 */
	public void selectTabInAskQuestionModal(String tab)
	{
		findClickableElement(By.xpath("(//*[@id='qaQueAnswerModalID_BODY']//*[normalize-space()='" + tab.trim() + "'])[2]")).click();
	}

	/**
	 * @author vivek.mishra
	 *         To Attach file in ask question modal
	 */
	public void attachFileInAskQuestionModal(String file)
	{
		selectTabInAskQuestionModal("Attachments");
		verifyAttachmentsTabOpenedInAskQuestionModal();
		String path = TestBaseSetup.currentDir + "\\testData\\" + file.trim();
		findPresentElement(browseButtonInAskQuestionModal).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 *         To verify the custom message appears in fade modal
	 */
	public boolean verifyCustomMessage(String currentMessage)
	{
		return (findPresentElement(customMessage).getText().trim().equals(currentMessage.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on question in Q&A module
	 */
	public void clickOnQuestion(String question)
	{
		verifyQuestionInQuestionListing(question);
		element = findVisibleElement(By.xpath("(//*[@class='qaText' and contains(text(),'" + question.trim() + "')])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To verify the Q&A detail container modal
	 */
	public boolean verifyQADetailContainer()
	{
		findPresentElement(qaDetailContainer, Speed.slow);
		return (isDisplayed(qaDetailContainer));
	}

	/**
	 * @author vivek.mishra
	 *         To click on QA detail container close button
	 */
	public void clickOnCloseButtonInQADetailContainer()
	{
		element = findVisibleElement(closeButtonInQADetailContainer, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on show tag in QA detail container
	 */
	public void clickOnShowTagInQADetailContainer()
	{
		if (isDisplayed(showTagInQADetailContainer))
			findClickableElement(showTagInQADetailContainer).click();
	}

	/**
	 * @author vivek.mishra
	 *         To verify the status value in Q&A detail container
	 */
	public boolean verifyStatusValueInQADetailContainer(String status)
	{
		return (findPresentElement(statusValueInQADetailContainer, Speed.slow).getText().trim().equals(status.trim()));

	}

	/**
	 * @author vivek.mishra
	 *         To verify the Related To value in Q&A detail container
	 */
	public boolean verifyRelatedToValueInQADetailContainer(String relatedTo)
	{
		return (findPresentElement(relatedToValueInQADetailContainer).getText().trim().equals(relatedTo.trim()));

	}

	/**
	 * @author vivek.mishra
	 *         To verify the Priority value in Q&A detail container
	 */
	public boolean verifyPriorityValueInQADetailContainer(String priority)
	{
		return (findPresentElement(priorityValueInQADetailContainer).getText().trim().equals(priority.trim()));

	}

	/**
	 * @author vivek.mishra
	 *         To verify the topic value in Q&A detail container
	 */
	public boolean verifyTopicValueInQADetailContainer(String topic)
	{
		return (findPresentElement(topicValueInQADetailContainer).getText().trim().equals(topic.trim()));

	}

	/**
	 * @author vivek.mishra
	 *         To verify the Attachment value in Q&A detail container
	 */
	public boolean verifyAttachedFileInQADetailContainer(String file)
	{
		return (findPresentElement(attachedFileInQADetailContainer).getText().trim().equals(file.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To verify the Question value in Q&A detail container
	 */
	public boolean verifyQuestionValueInQADetailContainer(String question)
	{
		return (findPresentElement(questionValueInQADetailContainer).getText().trim().equals(question.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To edit the particular question
	 */
	public void editQuestion(String question)
	{
		clickOnQuestion(question);
		verifyQADetailContainer();
		clickOnEditInMoreAction();
		verifyAskQuestionModal();
	}

	/**
	 * @author vivek.mishra
	 *         To click on more action button of a question in qa detail container
	 */
	public void clickOnQuestionMoreActionButtonInQADetailContainer()
	{
		findClickableElement(questionMoreActionInQADetailContainer).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on edit button in QA detail container
	 */
	public void clickOnEditInMoreAction()
	{
		clickOnQuestionMoreActionButtonInQADetailContainer();
		findClickableElement(editInMoreActionInQADetailContainer).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Delete button in QA detail container
	 */
	public void clickOnDeleteInMoreAction()
	{
		clickOnQuestionMoreActionButtonInQADetailContainer();
		findClickableElement(deleteInMoreActionInQADetailContainer).click();
	}

	/**
	 * @author vivek.mishra
	 *         To Edit text in question text box in ask question modal
	 */
	public void editTextInQuestionTextBox(String text)
	{
		findPresentElement(questionTextBox).clear();
		findPresentElement(questionTextBox).sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 *         To Attach file in ask question modal
	 */
	public void editAttachmentInAskQuestionModal(String file, String newFile)
	{
		removeAttachmentInAskQuestionModal(file);
		String path = TestBaseSetup.currentDir + "\\testData\\" + newFile;
		findPresentElement(browseButtonInAskQuestionModal).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 *         To remove a file in ask question modal
	 */
	public void removeAttachmentInAskQuestionModal(String file)
	{
		selectTabInAskQuestionModal("Attachments");
		WebElement element = findPresentElement(By.xpath("//*[@class='postUpload clearfix']//*[normalize-space()='" + file.trim() + "']//preceding-sibling::a"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param button
	 *        To click on buttons in QA detail container
	 */
	public void clickOnQADetailContainerBottomButtons(String button)
	{
		verifyQADetailContainer();
		WebElement element = findVisibleElement(By.xpath("//*[@id='qaViewAttachmentID']//following-sibling::*//*[normalize-space()='" + button.trim() + "' and not(contains(@disabled,'disabled'))]"), Speed.slow);
		verifyQADetailContainer();
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on reply tag in QA detail container
	 */
	public void clickOnReplyTagInQADetailContainer()
	{
		element = findVisibleElement(replyTagInQADetailContainer, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in quick reply text box in QA detail container
	 */
	public void sendTextInQuickReplyTextBoxInQADetailContainer(String text)
	{
		findVisibleElement(replyTextBoxInQADetailContainer, Speed.slow).sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 * @param actualReply
	 * @return
	 * 		To verify the reply value in QA detail container
	 */
	public boolean verifyReplyValueInQADetailContainer(String actualReply)
	{
		return (findVisibleElement(replyValueInQADetailContainer, Speed.slow).getText().trim().equals(actualReply.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return the status
	 *         To verify the status in question listing page
	 */
	public boolean verifyStatusInQuestionListing(String question, String status)
	{
		return (findVisibleElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//following-sibling::div[@class='colStatus hidden-xs']"), Speed.slow).getText().trim().equals(status.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param status
	 * @return
	 * 		To verify the draft reply status
	 */
	public boolean verifyDraftReplyStatus(String status)
	{
		return (findVisibleElement(draftReplyStatus, Speed.slow).getText().trim().equals(status.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the answer modal appears
	 */
	public boolean verifyAnswerModal()
	{
		findVisibleElement(answerModal, Speed.slow);
		return (isDisplayed(answerModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To edit the replied answer in answer modal
	 */
	public void editAnswerTextBoxInAnswermodal(String text)
	{
		WebElement textBox = findVisibleElement(answerTextBoxInAnswerModal, Speed.slow);
		textBox.clear();
		textBox.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on footer buttons in answer modal
	 * @param button
	 */
	public void clickOnAnswerModalFooterButtons(String button)
	{
		findVisibleElement(By.xpath("//*[@class='modal-content overflowHidden']//*[@class='modal-footer']//button[normalize-space()='" + button.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To send text in quick search bar
	 */
	public void sendTextInQuickSearchTextBox(String text)
	{
		WebElement element = findVisibleElement(quickSearchTextBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on quick search drop down button
	 */
	public void clickOnQuickSearchDropDown()
	{
		WebElement quickSearch = findVisibleElement(quickSearchDropDown, Speed.slow);
		quickSearch.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on options available in quick search drop down
	 */
	public void clickOnOptionsInQuickSearch(String option)
	{
		clickOnQuickSearchDropDown();
		findVisibleElement(By.xpath("//*[@class='input-group-btn dropdown open']//*[normalize-space()='" + option.trim() + "']"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To verify the question in question listing
	 */
	public boolean verifyQuestionInQuestionListing(String question)
	{
		return (isDisplayed(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To wait for the loader that appears at the time of loading the question
	 */
	public void waitForLoader()
	{
		while (isDisplayed(questionLoader, Speed.slow))
			System.err.println("Loader is running");
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To verify the Organization in question listing
	 */
	public boolean verifyOrganizationInQuestionListing(String org)
	{
		String currentOrg = findVisibleElement(By.xpath("(//*[@class='colOrg wordWrap hidden-xs' and text()='" + org.trim() + "'])[1]"), Speed.slow).getText();
		return (currentOrg.trim().equalsIgnoreCase(org.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To verify the Author in question listing
	 */
	public boolean verifyAuthorInQuestionListing(String author)
	{
		String currentAuthor = findPresentElement(By.xpath("(//*[@class='colAuth hidden-xs']//a[normalize-space()='" + getUserData(author).trim() + "'])[1]")).getText();
		return (currentAuthor.trim().equals(getUserData(author).trim()));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the no questions in question grid
	 */
	public boolean verifyNoQuestions()
	{
		return (isDisplayed(noQuestions, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To get the ID of a question
	 */
	public String getQuestionID(String question)
	{
		return (findPresentElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colId hidden-xs']")).getText().trim());
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To get the NO of a question
	 */
	public String getQuestionNO(String question)
	{
		return (findPresentElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colNo']")).getText().trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the NO in question grid
	 */
	public boolean verifyQuestionNO(String question, String NO)
	{
		findPresentElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colNo' and text()='" + NO.trim() + "']"));
		return (isDisplayed(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colNo' and text()='" + NO.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the ID in question grid
	 */
	public boolean verifyQuestionID(String question, String ID)
	{
		findPresentElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colId hidden-xs' and text()='" + ID.trim() + "']"));
		return (isDisplayed(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//preceding-sibling::div[@class='colId hidden-xs' and text()='" + ID.trim() + "']")));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the advance search modal
	 */
	public boolean verifyAdvanceSearchModal()
	{
		findPresentElement(advanceSearchModal, Speed.slow);
		return (isDisplayed(advanceSearchModal));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the search Button In Advance search modal
	 */
	public boolean verifySearchButtonInAdvanceSearchModal()
	{
		findPresentElement(searchButtonInAdvanceSearchModal);
		return (isDisplayed(searchButtonInAdvanceSearchModal));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the cancel Button In Advance search modal
	 */
	public boolean verifyCancelButtonInAdvanceSearchModal()
	{
		findPresentElement(cancelButtonInAdvanceSearchModal);
		return (isDisplayed(cancelButtonInAdvanceSearchModal));
	}

	/**
	 * @author vivek.mishra
	 *         To click on search button in advance search modal
	 */
	public void clickOnSearchInAdvanceSearchModal()
	{
		findClickableElement(searchButtonInAdvanceSearchModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in advance search modal
	 */
	public void clickOnCancelInAdvanceSearchModal()
	{
		findClickableElement(cancelButtonInAdvanceSearchModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the question list in question grid
	 */
	public boolean verifyQuestionList()
	{
		return (isDisplayed(questionList, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param field
	 * @return
	 * 		To verify the fields in Advance search modal
	 */
	public boolean verifyFieldsInAdvanceSearchModal(String field)
	{
		findPresentElement(By.xpath("(//*[normalize-space()='" + field.trim() + "'])[last()]"));
		return (isDisplayed(By.xpath("(//*[normalize-space()='" + field.trim() + "'])[last()]")));
	}

	/**
	 * @author vivek.mishra
	 * @param textBoxName
	 * @param text
	 *        To send text in Advance search modal text boxes
	 */
	public void sendTextInAdvanceSearchTextBoxes(String textBoxName, String text)
	{
		WebElement element = findPresentElement(By.xpath("((//*[normalize-space()='" + textBoxName.trim() + "'])[last()]//following-sibling::*//input)[last()]"));
		element.clear();
		element.sendKeys(text.toLowerCase());
		if (textBoxName.equals(QandALabels.QANDA_SEARCH_ORGANISATION))
			clickOnAutoSuggest(text);
		else if (textBoxName.equals(QandALabels.QANDA_SEARCH_AUTHOR))
			selectOptionFromAutoSuggest(text);
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select option in Modified date drop down in advance search modal
	 */
	public void selectOptionInModifiedDateDropDown(String option)
	{
		Select select = new Select(findPresentElement(modifiedDateDropDownInAdvanceSearchModal));
		select.selectByVisibleText(option.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on File/Folder drop down in advance search modal
	 */
	public void clickOnFileFolderDropDownInAdvanceSearchModal()
	{
		findClickableElement(fileFolderDropDownInAdvanceSearchModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select option in File/Folder drop down in advance search modal
	 */
	public void selectOptionInFileFolderDropDown(String option, String file)
	{
		clickOnFileFolderDropDownInAdvanceSearchModal();
		findClickableElement(By.xpath("(//*[@id='qafolderSelectDrop']//*[normalize-space()='" + option.trim() + "'])[Last()]"));
		if (option.trim().equalsIgnoreCase("Choose"))
			selectSiteInSelectItemsModal(file);
		clickOnSelectButtonInselectItemsModal();
		verifyAdvanceSearchModal();
	}

	/**
	 * @author vivek.mishra
	 *         To verify selectItemsModal in Advance search drop down
	 */
	public boolean verifySelectItemsModalInAdvanceSearchModal()
	{
		findPresentElement(selectItemsModal, Speed.slow);
		return (isDisplayed(selectItemsModal));
	}

	/**
	 * @author vivek.mishra
	 *         To select site in select items modal
	 */
	public void selectSiteInSelectItemsModal(String file)
	{
		verifySelectItemsModalInAdvanceSearchModal();
		WebElement element = findPresentElement(By.xpath("(//*[@id='qa_module_advance_search_TreeModal']//*[normalize-space()='" + file.trim() + "'])[last()]//preceding-sibling::span[@class='fancytree-checkbox']"));
		if (!element.isSelected())
			element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in select items modal
	 */
	public void clickOnCancelButtonInselectItemsModal()
	{
		findClickableElement(cancelButtonInSelectItemsModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on select button in select items modal
	 */
	public void clickOnSelectButtonInselectItemsModal()
	{
		findClickableElement(selectButtonInSelectItemsModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To clear the quick search text box
	 */
	public void clearQuickSearchTextBox()
	{
		findPresentElement(quickSearchClearButton).click();
	}

	/**
	 * @author vivek.mishra
	 * @param suggestion
	 *        To click on suggestion box in Organization in advance search
	 */
	public void clickOnSuggestionBoxInOrganisationInAdvanceSearch(String suggestion)
	{
		if (verifySuggestionBoxInOrganisationInAdvanceSearch(suggestion))
			findPresentElement(By.xpath("//strong[text()='" + suggestion.trim() + "']"), Speed.slow).click();

	}

	/**
	 * @author vivek.mishra
	 *         To click on more action button in Q&A page
	 */
	public void clickOnMoreActionInQAPage()
	{
		WebElement element = findPresentElement(moreActionButtonInQAPage, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To select option in more action in Q&A page
	 */
	public void selectOptionInMoreActionInQAPage(String option)
	{
		clickOnMoreActionInQAPage();
		findClickableElement(By.xpath("//*[@data-original-title='" + QandALabels.QANDA_DATAORIGINALTITLEATTRIBUTE_MOREACTIONS + "' ]//following-sibling::*//a[text()='" + option.trim() + "']")).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the export modal
	 */
	public boolean verifyExportModal()
	{
		findPresentElement(exportModal, Speed.slow);
		return (isDisplayed(exportModal));
	}

	/**
	 * @author vivek.mishra
	 *         To click on import template button in export modal
	 */
	public void clickOnImportTemplateButtonInExportModal()
	{
		verifyExportModal();
		findClickableElement(importTemplateButtonInExportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on XLSX button in export modal
	 */
	public void clickOnXLSXButtonInExportModal()
	{
		verifyExportModal();
		findClickableElement(xlsxButtonInExportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Cancel button in export modal
	 */
	public void clickOnCancelButtonInExportModal()
	{
		findClickableElement(cancelButtonInExportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Export button in export modal
	 */
	public void clickOnExportButtonInExportModal()
	{
		if (findPresentElement(exportButtonInExportModal).isEnabled())
			findClickableElement(exportButtonInExportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Cancel button in import modal
	 */
	public void clickOnCancelButtonInImportModal()
	{
		findClickableElement(cancelButtonInImportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on next button in import modal
	 */
	public void clickOnNextButtonInImportModal()
	{
		if (findPresentElement(nextButtonInImportModal).isEnabled())
			findClickableElement(nextButtonInImportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Submit answer check box in import modal
	 */
	public void clickOnSubmitAnswerCheckBoxInImportModal()
	{
		if (!findPresentElement(submitAnswerCheckBoxInImportModal).isSelected())
			findClickableElement(submitAnswerCheckBoxInImportModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To attach file in import modal
	 */
	public void attachFileToBrowseButtonInImportModal(String file)
	{
		String path = TestBaseSetup.currentDir + "\\Downloads\\" + file;
		findPresentElement(browseButtonInImportModal).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the import modal
	 */
	public boolean verifyImportModal()
	{
		findPresentElement(importModal, Speed.slow);
		return (isDisplayed(importModal));
	}

	/**
	 * @author vivek.mishra
	 *         To click on more action button on top in QA detail container
	 */
	public void clickOnTopMoreActionButtonInQADetailContainer()
	{
		WebElement element = findPresentElement(topMoreActionButtonInQADetailContainer, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on change topic in more action in QA detail container
	 */
	public void clickOnChangeTopic()
	{
		verifyQADetailContainer();
		clickOnTopMoreActionButtonInQADetailContainer();
		findClickableElement(changeTopicInTopMoreActionButtonInQADetailContainer).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in change topic modal
	 */
	public void clickOnCancelButtonInChangeTopicModal()
	{
		findClickableElement(cancelButtonInChangeTopicModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on submit button in change topic modal
	 */
	public void clickOnSubmitButtonInChangeTopicModal()
	{
		findClickableElement(submitButtonInChangeTopicModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the change topic modal
	 */
	public boolean verifyChangeTopicModal()
	{
		findPresentElement(changeTopicModal, Speed.slow);
		return (isDisplayed(changeTopicModal));
	}

	/**
	 * @author vivek.mishra
	 * @param topic
	 *        To select topic in change topic modal
	 */
	public void selectTopicInChangeTopicModal(String topic)
	{
		findClickableElement(By.xpath("(//*[@id='qa_module_topicList']//*[normalize-space()='" + topic.trim() + "'])[last()]/input")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param topic
	 *        To change the topic
	 */
	public void changeTopic(String topic)
	{
		clickOnChangeTopic();
		verifyChangeTopicModal();
		selectTopicInChangeTopicModal(topic);
		clickOnSubmitButtonInChangeTopicModal();
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To verify the question topic in question list
	 */
	public boolean verifyTopicInQuestionListing(String question, String topic)
	{
		return (findPresentElement(By.xpath("(//*[@class='qaText' and normalize-space()='" + question.trim() + "'])[1]/..//following-sibling::div")).getText().equals(topic.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @param reply
	 *        To reply a question
	 */
	public void replyQuestion(String question, String reply)
	{
		clickOnQuestion(question);
		verifyQADetailContainer();
		sendTextInQuickReplyTextBoxInQADetailContainer(reply);
		clickOnQADetailContainerBottomButtons(QandALabels.QANDA_DETAILCONTAINER_REPLY);
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select option in top more action button in QA detail container
	 */
	public void selectOptionInTopMoreActionInQADetailContainer(String option)
	{
		verifyQADetailContainer();
		clickOnTopMoreActionButtonInQADetailContainer();
		findClickableElement(By.xpath("(//*[normalize-space()='" + getUserData(option).trim() + "'])[2]"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the disclose modal
	 */
	public boolean verifyDiscloseModal()
	{
		findPresentElement(discloseModal, Speed.slow);
		return (isDisplayed(discloseModal));
	}

	/**
	 * @author vivek.mishra
	 *         To click on save button in disclose modal
	 */
	public void clickOnSaveButtonInDiscloseModal()
	{
		findClickableElement(saveButtonInDiscloseModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in disclose modal
	 */
	public void clickOnCancelButtonInDiscloseModal()
	{
		findClickableElement(cancelButtonInDiscloseModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on disclose to all organizations check box in disclose modal
	 */
	public void clickOnDiscloseToAllOrganizationsCheckBoxInDiscloseModal()
	{
		if (!findPresentElement(discloseToAllOrganizationCheckBoxInDiscloseModal).isSelected())
			findClickableElement(discloseToAllOrganizationCheckBoxInDiscloseModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To switch to print window
	 */
	public void switchToPrintWindow()
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
		driver.manage().window().maximize();
	}

	/**
	 * @author vivek.mishra
	 *         To close the print window
	 */
	public void closePrintWindow()
	{
		findClickableElement(closeButtonInPrintWindow).click();
		driver.switchTo().window(parentWindow);
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the print preview page
	 */
	public boolean verifyPrintPreviewPage()
	{
		findPresentElement(printPreviewPage, Speed.slow);
		return (isDisplayed(printPreviewPage));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the print button in print preview page
	 */
	public boolean verifyPrintButtonInPrintPreviewPage()
	{
		return (isDisplayed(printButtonInPrintWindow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the Close button in print preview page
	 */
	public boolean verifyCloseButtonInPrintPreviewPage()
	{
		return (isDisplayed(closeButtonInPrintWindow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the question value in print preview page
	 */
	public boolean verifyQuestionValueInPrintPreviewPage(String question)
	{
		return (findPresentElement(questionValueInPrintPreviewPage).getText().trim().equals(question.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param field
	 * @param value
	 * @return
	 * 		To verify the details value in print preview page
	 */
	public boolean verifyDetailsInPrintPreviewPage(String field, String value)
	{
		return (findPresentElement(By.xpath("//*[contains(text(),'" + field.trim() + "')]//following-sibling::div")).getText().split(",")[0].trim().equalsIgnoreCase(value.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on print button in print preview page
	 */
	public void clickOnPrintButtonInPrintPreviewPage()
	{
		WebElement elemenrt = findPresentElement(printButtonInPrintWindow);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", elemenrt);
	}

	/**
	 * @author vivek.mishra
	 *         To click on close button in print preview page
	 */
	public void clickOnCloseButtonInPrintPreviewPage()
	{
		findClickableElement(closeButtonInPrintWindow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @return
	 * 		To get the ID of a question
	 */
	public String getAskedOnInQuestionList(String question)
	{
		return (findPresentElement(By.xpath("(//*[@class='qaText' and text()='" + question.trim() + "'])[1]/../../..//following-sibling::div[@class='colAsk hidden-xs']")).getText().trim());
	}

	/**
	 * @author vivek.mishra
	 *         To switch to print page
	 */
	public void switchToPrintPage()
	{
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		innerChildWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(innerChildWindow);
		driver.manage().window().maximize();
	}

	/**
	 * @author vivek.mishra
	 *         To verify the final print page
	 */
	public boolean verifyPrintPage()
	{
		findPresentElement(printPage, Speed.slow);
		return (isDisplayed(printPage));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the loader
	 */
	public boolean verifyLoader()
	{
		return (isDisplayed(questionLoader));
	}

	/**
	 * @author vivek.mishra
	 * @param header
	 * @return
	 * 		To verify the up down caret sign in QA header
	 */
	public boolean verifyUpDownCaretSign(String header)
	{
		return (isDisplayed(By.xpath("//strong[normalize-space()='" + header.trim() + "']/span[@class='icon-caret-up-down']")));
	}

	/**
	 * @author vivek.mishra
	 * @param header
	 * @return
	 * 		To verify the up caret sign in QA header
	 */
	public boolean verifyUpCaretSign(String header)
	{
		return (isDisplayed(By.xpath("//strong[normalize-space()='" + header.trim() + "']/span[@class='icon-caret-up']")));
	}

	/**
	 * @author vivek.mishra
	 * @param header
	 * @return
	 * 		To verify the down caret sign in QA header
	 */
	public boolean verifyDownCaretSign(String header)
	{
		return (isDisplayed(By.xpath("//strong[normalize-space()='" + header.trim() + "']/span[@class='icon-caret-down']")));
	}

	/**
	 * @author vivek.mishra
	 * @param header
	 *        To click on caret sign of a particular in header
	 */
	public void clickOnCaretSign(String header)
	{
		findClickableElement(By.xpath("//strong[normalize-space()='" + header.trim() + "']/span")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param position
	 * @param questionValue
	 * @return
	 * 		To verify the order of the question in question list
	 */
	public boolean verifyQuestionOrder(int position, String questionValue)
	{
		return (findPresentElement(By.xpath("(//*[@class='qaText'])[" + position + "]")).getText().trim().equals(questionValue.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @see com.highq.pageobjects.base.QandAPage#verifyDeleteModal()
	 */
	public boolean verifyDeleteModal()
	{
		findPresentElement(deleteModal, Speed.slow);
		return (isDisplayed(deleteModal));
	}

	/**
	 * @author vivek.mishra
	 */
	public void clickOnCancelButtonInDeleteModal()
	{
		findClickableElement(cancelButtonInDeleteModal).click();
	}

	/**
	 * @author vivek.mishra
	 */
	public void clickOnDeleteButtonInDeleteModal()
	{
		findClickableElement(deleteButtonInDeleteModal).click();
	}

	/**
	 * @author vivek.mishra
	 * @param question
	 * @param comment
	 *        To add a simple comment of a question
	 */
	public void addComment(String question, String comment)
	{
		verifyQuestionInQuestionListing(question);
		clickOnQuestion(question);
		selectOptionInTopMoreActionInQADetailContainer(QandALabels.QANDA_COMMENT);
		verifyAskQuestionModal();
		sendTextInQuestionTextBox(comment);
		clickOnFootersInAskQuestionModal(QandALabels.QANDA_SUBMIT);
		verifyFadeModal();
		clickOnOkButtonInFadeModalDialog();
		verifyQuestionInQuestionListing(question);
	}

	/**
	 * @author vivek.mishra
	 */
	public boolean verifySuggestionBoxInOrganisationInAdvanceSearch(String suggestion)
	{
		return (isDisplayed(By.xpath("//strong[text()='" + suggestion.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 *        To click on auto suggest box
	 * @creation date 25/01/2018
	 */
	public void clickOnAutoSuggest(String text)
	{
		if (isDisplayed(By.xpath("//*[contains(@class,'typeahead_primary')]"), Speed.slow))
		{
			if (isDisplayed(By.xpath("//*[contains(@class,'typeahead_primary')]/strong[normalize-space(text())='" + text.trim().toUpperCase() + "']"), Speed.slow))
			{
				WebElement element = findVisibleElement(By.xpath("//*[contains(@class,'typeahead_primary')]/strong[normalize-space(text())='" + text.trim().toUpperCase() + "']"));
				element.click();
			}
		}
	}

	/**
	 * @author vivek.mishra
	 *         To wait for file untill getting exported
	 * @creation date 26/03/2018
	 */
	public void waitForFileGettingDownloaded()
	{
		if (isDisplayed(downnloadingFileMessage, Speed.slow))
			while (isDisplayed(downnloadingFileMessage, Speed.slow))
				;
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the attachments tab is open
	 * @cretaion date 03/04/2018
	 */
	public boolean verifyAttachmentsTabOpenedInAskQuestionModal()
	{
		findVisibleElement(attachmentsTabOpened, Speed.slow);
		return (isDisplayed(attachmentsTabOpened, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @cretaion date 26/04/2018
	 */
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return (isDisplayed(modal, Speed.slow));
	}
	
	/**
	 * @author vivek.mishra
	 * @return the complete list data in map
	 * @created on 28/05/2018
	 */
	public Map<String, ArrayList> getQAListData()
	{
		verifyQuestionList();
		int rowSize = driver.findElements(rowSizeData).size();
		int colSize = driver.findElements(colSizeData).size();
		Map<String, ArrayList> dataSet = new LinkedHashMap<String, ArrayList>();
		ArrayList<String> listValues; 
		for(int i=1;i<=rowSize;i++)
		{
			listValues = new ArrayList<String>();
			for(int j=1;j<=colSize;j++)
			{
				listValues.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[contains(@id,'listQues')]["+i+"]//*[contains(@class,'col')])["+j+"]")).getText().trim());
			}
			dataSet.put(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[contains(@id,'listQues')]["+i+"]//*[contains(@class,'col')])[1]")).getText().trim(), listValues);
		}
		return dataSet;
	}

	/**
	 * @author vivek.mishra
	 * @param columnName of which data to get
	 * @return the list of values of that column
	 * @created on 28/05/2018
	 */
	public ArrayList<String> getColData(String columnName)
	{
		ArrayList<String> colData = new ArrayList<String>();
		colData.clear();
		int rowSize = driver.findElements(rowSizeData).size();
		switch (columnName.toLowerCase().trim())
		{
			case "id":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colId hidden-xs'])["+i+"]")).getText().trim());
				}
				break;

			case "no":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colNo'])["+i+"]")).getText().trim());
				}

				break;
			case "question":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colQue'])["+i+"]")).getText().trim());
				}

				break;
			case "orgnasition":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colOrg wordWrap hidden-xs'])["+i+"]")).getText().trim());
				}

				break;
			case "author":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colAuth hidden-xs'])["+i+"]")).getText().trim());
				}

				break;
			case "asked on":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colAsk hidden-xs'])["+i+"]")).getText().trim());
				}

				break;
			case "status":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colStatus hidden-xs'])["+i+"]")).getText().trim());
				}

				break;
			case "priority":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colPriority hidden-xs'])["+i+"]")).getText().trim());
				}

				break;

			case "related to":
				for(int i=1;i<=rowSize;i++)
				{
					colData.add(findVisibleElement(By.xpath("(//*[@id='qaRowList']//*[@class='colRelated hidden-xs'])["+i+"]")).getText().trim());
				}

				break;

			default:
				break;
		}
		return colData;
	}

	/**
	 * @author vivek.mishra
	 * @param colName to checked for sort
	 * @return the sorted order true or false
	 * @created on 28/05/2018
	 */
	public boolean verifyAscendingOrder(String colName)
	{
		waitForLoader();
		verifyQuestionList();
		Map<String, ArrayList> tableBeforeSort = getQAListData();
		if(!verifyUpCaretSign(colName))
		{
			clickOnCaretSign(colName);
			verifyQuestionList();
		}
		ArrayList<String> colElements = getColData(colName);
		if(colName.trim().toLowerCase().equals("no") || colName.trim().toLowerCase().equals("id"))
		{
			List<Double> sizeList = new ArrayList<Double>();
			
			for(int i=0; i<colElements.size();i++)
			{
				String val = colElements.get(i).trim();
				sizeList.add(Double.parseDouble(val));
			}
			List<Double> copySizeList = new ArrayList<Double>(sizeList);
			Collections.sort(sizeList);
			return(copySizeList.equals(sizeList));
		}
		ArrayList<String> copyElements = new ArrayList<>(colElements);
		Collections.sort(colElements,String.CASE_INSENSITIVE_ORDER);
		Map<String, ArrayList> tableAfterSort = getQAListData();
		return(copyElements.equals(colElements) && tableBeforeSort.equals(tableAfterSort));
	}

	/**
	 * @author vivek.mishra
	 * @param colName to checked for sort
	 * @return the sorted order true or false
	 * @created on 28/05/2018
	 */
	public boolean verifyDescendingOrder(String colName)
	{
		waitForLoader();
		verifyQuestionList();
		Map<String, ArrayList> tableBeforeSort = getQAListData();
		if(!verifyDownCaretSign(colName))
		{
			clickOnCaretSign(colName);
			verifyQuestionList();
		}

		ArrayList<String> colElements = getColData(colName);
		if(colName.trim().toLowerCase().equals("no") || colName.trim().toLowerCase().equals("id"))
		{
			List<Double> sizeList = new ArrayList<Double>();
			for(int i=0; i<colElements.size();i++)
			{
				String val = colElements.get(i).trim();
				sizeList.add(Double.parseDouble(val));
			}
			List<Double> copySizeList = new ArrayList<Double>(sizeList);
			Collections.sort(sizeList,Collections.reverseOrder());
			return(copySizeList.equals(sizeList));
		}
		ArrayList<String> copyElements = new ArrayList<>(colElements);
		reverseSort(copyElements,String.CASE_INSENSITIVE_ORDER);
		Map<String, ArrayList> tableAfterSort = getQAListData();
		return(copyElements.equals(colElements) && tableBeforeSort.equals(tableAfterSort));
	}
}
