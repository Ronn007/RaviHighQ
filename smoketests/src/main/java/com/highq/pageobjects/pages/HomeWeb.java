package com.highq.pageobjects.pages;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import com.highq.labels.collaborate.HomeLabels;
import com.highq.pageobjects.base.HomePage;

public class HomeWeb extends BannerPageWeb implements HomePage
{
	Actions build;

	By favoriteIcon = By.xpath("//*[contains(@class,'headPageTitle')]//*[contains(@class,'icon-star')]");
	By favoriteIconSelected = By.xpath("//*[contains(@class,'headPageTitle')]//*[contains(@class,'icon-star-selected')]");
	By moreActionIcon = By.xpath("//*[contains(@class,'headPageTitle')]//*[contains(@data-original-title,'" + HomeLabels.HOME_DATAORIGINALTITLE_MOREACTIONS + "')]");
	By moreActionModal = By.xpath("//*[contains(@class,'headPageTitle')]//*[contains(@data-original-title,'" + HomeLabels.HOME_DATAORIGINALTITLE_MOREACTIONS + "')]/following-sibling::ul");
	By moreActionShare = By.xpath("//*[normalize-space(text())='" + HomeLabels.HOME_MOREACTIONS_SHARE + "']");
	By moreActionExportToPDF = By.xpath("//*[normalize-space(text())='" + HomeLabels.HOME_MOREACTIONS_EXPORTTOPDF + "']");
	By moreActionPrintPreview = By.xpath("//*[normalize-space(text())='" + HomeLabels.HOME_MOREACTIONS_PRINTPREVIEW + "']");
	By siteHomeTitleHeader = By.id("siteHomeTitleID");
	By siteHomeTitleInput = By.id("siteHomeTitleEditableID");
	By siteHomeTitleSaveIcon = By.id("SITE_HOME_TITLE_CHECK_BUTTON_ID");
	By siteHomeTitleCancelIcon = By.id("//*[@id='SITE_HOME_TITLE_CHECK_BUTTON_ID']/following-sibling::*[contains(@class,'edit-cancel')]");

	/********** Edit Content *****************/
	By editContentInput = By.id("siteHomeCKContentID");
	By editContentSaveIconBottom = By.id("SITE_HOME_CKEDITOR_INLINE_CHECK_BUTTON_ID");
	By editContentSaveIconTop = By.id("SITE_HOME_CKEDITOR_INLINE_CHECK_BUTTON_ID_1");
	By editContentCancelIconTop = By.xpath("//*[@id='SITE_HOME_CKEDITOR_INLINE_CHECK_BUTTON_ID_1']/following-sibling::*[contains(@class,'edit-cancel')]");
	By editContentCancelIconBottom = By.xpath("//*[@id='SITE_HOME_CKEDITOR_INLINE_CHECK_BUTTON_ID']/following-sibling::*[contains(@class,'edit-cancel')]");
	By editIcon = By.id("editDashIcon");
	By siteHomeContent = By.id("siteHomeContentID");

	/******** Share *******************/
	By modalContent = By.xpath("(//*[@class='modal-content'])[last()]");
	By shareEmail = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_EMAIL + "']");
	By shareLink = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_LINK + "']");
	By shareMessage = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_MESSAGE + "']");
	By shareEmailRecipientsInput = By.id("file_module_emailRecipients-tokenfield");
	By shareEmailSubjectInput = By.id("file_module_email_subject");
	By shareEmailMessageInput = By.id("file_module_email_message");
	By shareMessageRecipientsInput = By.id("file_module_Site_Share_PrivateMessageUserList-tokenfield");
	By shareMessageMessageInput = By.id("file_module_Site_Share_PrivateMessageEditor");
	By shareCancel = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_CANCEL + "']");
	By shareSend = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='" + HomeLabels.HOME_SHARE_SEND + "']");
	By shareRecipientHover = By.xpath("(//*[@class='typeahead_labels']//*)[last()]");

	/******** Dash Board Content *******************/
	By removeSecXpath = By.id("removeSec");
	By removeButton = By.xpath(".//*[@id='removeSection']//*[text()='" + HomeLabels.REMOVE + "']");
	By addpanel = By.xpath(".//*[contains(@id,'panel')]");
	By addsection = By.id("GridAddRow");
	By panelHeader = By.xpath(".//*[starts-with(@id,'header')]");
	By clickOnMoreAction = By.xpath(".//*[starts-with(@id,'container')]//following::a[@data-original-title='More actions' OR @title='More actions']");
	By clickOnSave = By.xpath(".//*[@id='dashboardBuilderID']//button[text()='" + HomeLabels.SAVE + "']");
	By clickOnSaveInModal = By.xpath(".//*[@id='modal-footer']//button[text()='" + HomeLabels.SAVE + "']");

	/******** Panel Window *******************/

	By addButton = By.xpath(".//*[@id='modalClose']/button[text()='" + HomeLabels.ADD_BUTTON + "']");
	By cancelButton = By.xpath(".//*[@id='modalClose']//button[text()='" + HomeLabels.CANCEL_BUTTON + "']");
	By backToPanelSection = By.xpath(".//*[@id='modal-footer']//a[normalize-space()='" + HomeLabels.BACK_TO_PANEL_SECTION + "']");
	By selectPanel = By.xpath(".//*[starts-with(@class,'modal-body')]//child::p[text()='" + HomeLabels.SELECT_PANEL + "']");
	By closeButtonInAddPanel = By.xpath(".//button[text()='" + HomeLabels.CLOSE + "']");
	By closeButtonInModal = By.xpath(".//*[contains(@id,'ColumnModalFooter')]//*[text()='" + HomeLabels.CLOSE + "']");
	By doneButtonInModal = By.xpath(".//*[contains(@id,'ColumnModalFooter')]//button[text()='" + HomeLabels.DONE + "']");
	By doneButtonDisabled = By.xpath(".//*[contains(@id,'ColumnModalFooter')]//*[text()='" + HomeLabels.DONE + "' and@disabled='disabled']");
	By cancelButtonInSelectModal = By.xpath(".//*[contains(@id,'ColumnModalFooter')]//*[text()='" + HomeLabels.CANCEL + "']");
	By cancelButtonInModal = By.xpath(".//*[@id='modal-footer']//button[text()='" + HomeLabels.CANCEL + "']");
	By clearSearch = By.xpath(".//*[contains(@id,'ColumnInputTextFeild')]//parent::div//button");
	By addPanelID = By.id("AddModalPanleID");
	By searchTextBox = By.xpath(".//*[contains(@id,'ColumnInputTextFeild')]");
	By visualisationTitleInput = By.xpath(".//*[@id='visualisationTitle']//input");
	By siteButton = By.xpath(".//*[@id='allSiteId']//button");
	By searchSite = By.xpath(".//*[@id='siteText']");
	By columnModalTitle = By.xpath(".//*[contains(@id,'ColumnModalHeader')]//strong");
	By columnModalCrossButton = By.xpath(".//*[contains(@id,'ColumnModalHeader')]//button");
	By chartPreviewMessage = By.xpath(".//*[@id='fusionChartDisplay']//div");

	/******** CK Panel *******************/

	By ckTitle = By.xpath(".//*[@id='ckTitle']//input");
	By ckMobileDevice = By.xpath(".//*[@id='ckMobileDevice']//input");
	By ckTop = By.xpath(".//*[@class='cke_top']");
	By element = By.xpath(".//*[contains(@id,'element')]");
	By elementp = By.xpath(".//*[contains(@id,'element')]//p");
	By sourceInCkEditor = By.xpath(".//*[contains(@class,'sourcedialog') and text()='Source']");
	By sourceTextArea = By.xpath(".//*[@class='cke_dialog_ui_input_textarea cke_source']");
	By sourceOkButton = By.xpath(".//*[@class='cke_dialog_ui_button' and text()='" + HomeLabels.OK + "']");
	By errorModal = By.xpath(".//*[@id='errorModal']//p");
	By errorModalOkButton = By.id("dashboardBuilderErrorMessageOk");

	/******** Data Visualisation *******************/
	By chartTypeOption = By.xpath(".//*[@class='dropdown-menu pull-left copyDropdown']//b");
	By chartNameSize = By.xpath(".//*[@class='dropdown-menu pull-left copyDropdown']//parent::ul//span");
	By chartpreview = By.xpath(".//*[@id='data']//b[text()='" + HomeLabels.CHART_PREVIEW + "']");
	By dataButton = By.xpath(".//*[@id='data' and text()='" + HomeLabels.DATA + "']");
	By customise = By.xpath(".//*[@id='customize' and text()='" + HomeLabels.CUSTOMISE + "']");
	By data = By.xpath(".//*[@id='data' and text()='" + HomeLabels.DATA + "']");
	By customiseDisabled = By.xpath(".//*[@class='disabled']//a[text()='" + HomeLabels.CUSTOMISE + "']");
	By viewDisabled = By.xpath(".//*[@id='viewBtnID' and @disabled='disabled']");
	By previewButton = By.xpath(".//button[text()='" + HomeLabels.PREVIEW + "']");
	By chartTitleTxtBox = By.xpath(".//*[@id='chartTitle']//input");
	By applyButton = By.xpath(".//*[@id='customize']//*[text()='Apply']");

	By svg = By.xpath(".//*[name()='svg']");
	By categoryLabel = By.xpath(".//*[@id='chooseAxisDataForChartX']//*[text()='" + HomeLabels.CATEGORY + "']");
	By valueLabel = By.xpath(".//*[@id='chooseAxisDataForChartY']//*[text()='" + HomeLabels.VALUE + "']");
	By filterLabel = By.xpath(".//*[@id='chooseFilterDataForChartY']//*[text()='" + HomeLabels.FILTER + "']");
	By seriesLabel = By.xpath(".//*[@id='chooseSeriesDataForChartY']//*[text()='" + HomeLabels.SERIES + "']");
	By categoryButton = By.xpath(".//*[@id='chooseAxisDataForChartX']//button[normalize-space()='Select" + new Character('\u2026') + "']");
	By valueButton = By.xpath(".//*[@id='chooseAxisDataForChartY']//button[normalize-space()='Select" + new Character('\u2026') + "']");
	By filterButton = By.xpath(".//*[@id='chooseFilterDataForChartY']//button[normalize-space()='Select" + new Character('\u2026') + "']");
	By seriesButton = By.xpath(".//*[@id='chooseSeriesDataForChartY']//button[normalize-space()='Select" + new Character('\u2026') + "']");
	By toolTip = By.xpath(".//*[@id='fusioncharts-tooltip-element']//span");
	By errorDiv = By.xpath(".//*[contains(@id,'element')]//div");

	By pieChartInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][4]//*[name()='g'][5]//*[name()='path']");
	By standChartInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");
	By stackedChartInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");
	By pieChart = By.xpath(".//*[name()='svg']//*[name()='g'][4]//*[name()='g'][5]//*[name()='path']");
	By standChart = By.xpath(".//*[name()='svg']//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");
	By stackedChart = By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");

	By pieChartInDashBoard = By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][4]//*[name()='g'][5]//*[name()='path']");
	By standChartInDashBoard = By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");
	By stackedChartInDashBord = By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect']");

	By multiSeriesChartLegendsInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text']");
	By stackedChartLegendsInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='text']//*[name()='tspan']");
	By pieChartLegendsInChartPreview = By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text']//*[name()='tspan']");
	By multiSeriesChartLegendsInDashBoard = By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text']//*[name()='tspan']");
	By stackedChartLegendsInDashBord = By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text']//*[name()='tspan']");

	By mainSection = By.xpath(".//*[@class='mainSection']");
	By addModal = By.xpath(".//*[@id='AddModalPanleID']");
	By dataVisualizationPanel = By.xpath(".//*[@id='DataVisualizationPanelID']");

	By iSheetsButton = By.xpath(".//*[@id='showModuleDataId']//button");
	By searchIsheets = By.xpath(".//*[@id='sheetText']");
	By sourceButton = By.xpath(".//*[@id='showModuleId']//button");
	By viewButton = By.xpath(".//*[@id='showViewId']//button");
	By chartTypeButton = By.xpath(".//*[@id='selectChartDropdownID']/button");
	By editPanelTitle = By.xpath(".//*[contains(text(),'Panel title')]");

	ArrayList<String> modalList = new ArrayList<>();
	Map<String, String> map = new HashMap<>();
	By favouriteButton = By.xpath(".//*[@id='dashboardBuilder_favourite_ID']/a");

	By dashboardTitle = By.xpath(".//*[@id='dashboardTitle']/input");
	By elementNew = By.xpath(".//*[contains(@id,'element')]");
	By saveButtonOnEditPanel = By.xpath(".//*[@id='modalClose']/button[text()='" + HomeLabels.SAVE + "']");
	By insertLinkModel = By.xpath(".//*[@id='INSERT_LINK_DIALOG_MODAL' and @class='modal fade in']");
	By externalLinkTab = By.id("insertLinkModal_displayExternalTab");
	By externalLinkTextbox = By.id("externalURL");
	By insertLinkButton = By.id("INSERT_LINK_DIALOG_MODAL_insertButton");
	By linkIconOnCKEditor = By.xpath(".//*[@title='Link']");
	By macroIconOnCKEditor = By.xpath(".//a[contains(@id,'cke') and @title='List']");
	By insertListModel = By.xpath(".//*[@id='INSERT_MACRO_MODAL' and @class='modal fade in']");
	By listTitleTextbox = By.name("macro.MACRO_PROP_TYPE_TITLE");
	By insertMacroButton = By.id("INSERT_MACRO_MODAL_POST_BTN");
	By editMacroIcon = By.id("editMacro");
	By closeButtonPrint = By.xpath(".//*[@id='dashboardBuilderID']//button[text()='Close']");
	By defaultChartType = By.xpath(".//*[@id='selectChartDropdownID']//button//span[2]");
	By chartDropDown = By.xpath(".//*[@id='selectChartDropdownID']//button");

	public HomeWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Edit Site Home Title
	 * @param title
	 *            to add/edit
	 * @author dheeraj.rajput
	 */
	@Override
	public void editSiteHomeTitle(String title)
	{
		findVisibleElement(siteHomeTitleHeader).click();
		WebElement titleInput = findVisibleElement(siteHomeTitleInput);
		titleInput.clear();
		titleInput.sendKeys(title);
		findVisibleElement(siteHomeTitleSaveIcon).click();
	}

	/**
	 * Verify Site Home Title
	 * @param title
	 *            to verify
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifySiteHomeTitle(String title)
	{
		WebElement titleHeader = findVisibleElement(siteHomeTitleHeader);
		return titleHeader.getText().equals(title.trim());
	}

	/**
	 * Add to favorite(Click on favorite star Icon),
	 * click on favorite icon only if it is not selected.
	 * @author dheeraj.rajput
	 */
	@Override
	public void addToFavourites()
	{
		if (!isDisplayed(favoriteIconSelected))
		{
			findVisibleElement(favoriteIcon).click();
		}
		else
		{
			System.out.println("Favorites Icon is already selected");
		}
	}

	/**
	 * Select More Action options (Share, Export to PDF, Print Preview,...)
	 * @param option
	 *            to click
	 * @author dheeraj.rajput
	 */
	@Override
	public void selectMoreActionOption(String option)
	{
		findVisibleElement(moreActionIcon).click();
		findVisibleElement(moreActionModal);
		WebElement optionToClick = findVisibleElement(By.xpath("//*[normalize-space(text())='" + option.trim() + "']"));
		optionToClick.click();
	}

	/**
	 * Click on Edit Icon available besides Favorite icon.
	 * @author dheeraj.rajput
	 */
	@Override
	public void clickOnEditContentIcon()
	{
		findVisibleElement(editIcon).click();
	}

	/**
	 * Add/Overwrite content
	 * @param content
	 *            to add.
	 * @author dheeraj.rajput
	 */
	@Override
	public void addContent(String content)
	{
		clickOnEditContentIcon();
		WebElement contentInput = findVisibleElement(editContentInput);
		contentInput.clear();
		contentInput.sendKeys(content);
		findVisibleElement(editContentSaveIconTop).click();
	}

	/**
	 * Verify existing content
	 * @param content
	 *            to verify.
	 * @author dheeraj.rajput
	 */
	@Override
	public boolean verifyContent(String content)
	{
		String contentText = findVisibleElement(siteHomeContent).getText();
		return contentText.equals(content);
	}

	/**
	 * Share home content via e-mail(Share->Email).
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param subject
	 *            mail subject.
	 * @param message
	 *            to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaEmail(String recipientMail, String subject, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		}
		findVisibleElement(shareEmail).click();
		WebElement recipientInput = findVisibleElement(shareEmailRecipientsInput);
		WebElement subjectInput = findVisibleElement(shareEmailSubjectInput);
		WebElement messageInput = findVisibleElement(shareEmailMessageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		findVisibleElement(shareSend, Speed.slow);
		if (isDisplayed(shareRecipientHover))
		{
			WebElement recipientHover = findVisibleElement(shareRecipientHover);
			if (recipientHover.getText().trim().equalsIgnoreCase(recipientMail.trim()))
			{
				recipientHover.click();
			}
		}
		else
		{
			System.err.println(recipientMail + " not found.");
		}
		subjectInput.clear();
		subjectInput.sendKeys(subject);
		if (!message.isEmpty())
		{
			messageInput.clear();
			messageInput.sendKeys(message);
		}
		findVisibleElement(shareSend).click();
	}

	/**
	 * Share home content via message(Share->Message).
	 * @param recipientMail
	 *            e-mail of the recipient.
	 * @param message
	 *            to send in e-mail.
	 * @author dheeraj.rajput
	 */
	@Override
	public void shareViaMessage(String recipientMail, String message)
	{
		if (!isDisplayed(modalContent))
		{
			selectMoreActionOption(HomeLabels.HOME_MOREACTIONS_SHARE);
		}
		findVisibleElement(shareMessage).click();
		WebElement recipientInput = findVisibleElement(shareMessageRecipientsInput);
		WebElement messageInput = findVisibleElement(shareMessageMessageInput);
		recipientInput.clear();
		recipientInput.sendKeys(recipientMail);
		findVisibleElement(shareSend, Speed.slow, 5);
		if (isDisplayed(shareRecipientHover))
		{
			WebElement recipientHover = findVisibleElement(shareRecipientHover);
			if (recipientHover.getText().trim().equalsIgnoreCase(recipientMail.trim()))
			{
				recipientHover.click();
			}
		}
		else
		{
			System.err.println(recipientMail + " not found.");
		}
		messageInput.clear();
		messageInput.sendKeys(message);
		findVisibleElement(shareSend).click();
	}

	// =============Common Method For Home Dash Board========//
	/**
	 * Click On Edit Icon ( Home Module Dash Board)
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnEditIcon()
	{
		WebElement ele = findVisibleElement(editIcon);
		ele.click();

	}

	/**
	 * Click On Remove All Section ( Home Module Dash Board)
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void removeAllSections()
	{
		while (isDisplayed(removeSecXpath))
		{
			WebElement eleRemoveSec = findVisibleElement(removeSecXpath);
			eleRemoveSec.click();
			WebElement eleRemoveButton = findVisibleElement(removeButton);
			findVisibleElement(removeButton, Speed.slow);
			eleRemoveButton.click();
			findVisibleElement(mainSection);
		}
	}

	/**
	 * Click On Single Add Panel Icon(Home Module Dash Board)
	 * =======
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickonSingleAddPanelIcon()
	{
		WebElement ele = findVisibleElement(addpanel);
		ele.click();
	}

	/**
	 * @param position
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnMultiplePanelIcon(int position)
	{
		findVisibleElement(By.xpath("(.//*[contains(@id,'panel')])[" + position + "]"), Speed.slow).click();
	}

	/**
	 * Click On Add Section
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnAddSection()
	{
		WebElement ele = findVisibleElement(addsection);
		ele.click();
	}

	/**
	 * Click On Panel Link On Add Panel Window
	 * @param panelname
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickonPanelLinkOnAddPanelWindow(String panelname)
	{
		WebElement ele = findVisibleElement(By.xpath(".//*[@id='listItems thumbDocument']//following::*[text()='" + panelname.trim() + "']"));
		findVisibleElement(addPanelID, Speed.slow);
		ele.click();
		findVisibleElement(By.xpath(".//*[@id='modal-header']//*[text()='" + panelname.trim() + "']"), Speed.slow);
	}

	/**
	 * Click On Save
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnSave()
	{
		scrollToTop();
		WebElement ele = findVisibleElement(clickOnSave, Speed.slow);
		ele.click();
	}

	/**
	 * verify Panel Title
	 * @param panelTitle
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyPanelTitle(String panelTitle)
	{
		findVisibleElement(panelHeader, Speed.slow);

		int num = driver.findElements(panelHeader).size();
		for (int i = 1; i <= num; i++)
		{
			String var = getText(By.xpath(".//following::*[starts-with(@id,'headerTitle_1_1_" + i + "')]"));
			if (var.trim().equalsIgnoreCase(panelTitle.trim()))
			{
				return true;
			}
		}
		return false;

	}

	/**
	 * verify More Action Option In Added Panel
	 * @param panelname
	 * @param operation
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyMoreActionInAddedPanel(String panelname, String operation)
	{
		findVisibleElement(By.xpath(".//*[starts-with(@id,'sortable')]//div[1]//div[starts-with(@id,'header')]"), Speed.slow);
		int num = driver.findElements(By.xpath(".//*[starts-with(@id,'sortable')]//div[1]//div[starts-with(@id,'header')]")).size();
		for (int i = 1; i <= num; i++)
		{
			String var = getText(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//div[starts-with(@id,'header')]"));
			if (var.equalsIgnoreCase(panelname))
			{
				findVisibleElement(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//div[starts-with(@id,'header') and normalize-space()='" + panelname + "']")).click();

				if (isDisplayed(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//a[@title='More actions']")))
				{
					findVisibleElement(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//a[@title='More actions']")).click();
				}
				else
				{
					findVisibleElement(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//a[@data-original-title='More actions']")).click();
				}

				findVisibleElement(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//a[text()='" + operation + "']"), Speed.slow);

				return isDisplayed(By.xpath(".//*[starts-with(@id,'sortable')]//div[" + i + "]//a[text()='" + operation + "']"));

			}
		}
		return false;

	}

	// ============= Panel Window Field========//

	/**
	 * Verify Panel Model Fields
	 * @param panelname
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyPanelWinodw(String panelname)
	{
		findVisibleElement(By.xpath(".//*[@id='modal-header']"), Speed.slow);

		return isDisplayed(By.xpath(".//*[@id='modal-header']//*[text()='" + panelname.trim() + "']"))
				&& isDisplayed(addButton)
				&& isDisplayed(cancelButton)
				&& isDisplayed(backToPanelSection);
	}

	/**
	 * verify back To Panel Section
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyBackToPanelSectionInModelWindow()
	{
		WebElement ele = findVisibleElement(backToPanelSection);
		findVisibleElement(backToPanelSection, Speed.slow);
		ele.click();

		return isDisplayed(selectPanel);
	}

	/**
	 * Click On Close
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnClose()
	{
		WebElement ele = findVisibleElement(closeButtonInAddPanel);
		ele.click();
		findVisibleElement(mainSection);
	}

	/**
	 * Click On Add
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnAdd()
	{
		WebElement ele = findVisibleElement(addButton, Speed.slow);
		ele.click();
	}

	/**
	 * Click On Cancel
	 * @author krishna.bhadani
	 * @Created: 03 April 2018
	 */
	@Override
	public void clickOnCancel()
	{
		WebElement ele = findVisibleElement(cancelButton);
		ele.click();
		if (isDisplayed(addModal))
		{
			findVisibleElement(addModal);
		}
	}

	/**
	 * click On Save Modal
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnSaveInModal()
	{
		findVisibleElement(clickOnSaveInModal, Speed.slow).click();
		findVisibleElement(mainSection);
	}

	// ============= Content editor Method ========//
	/**
	 * Verify Mobile Device In Ck Editor
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyMobileDeviceInCKEditor()
	{
		WebElement ele = findVisibleElement(ckMobileDevice);
		return ele.isSelected();
	}

	/**
	 * Add Ck Editor
	 * @param panelTitle
	 * @param displayOnMobileDevice
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void addCkEditorPanel(String panelTitle, boolean displayOnMobileDevice)
	{
		WebElement ele = findVisibleElement(ckTitle);
		ele.clear();
		ele.sendKeys(panelTitle);
		setSelection(ckMobileDevice, displayOnMobileDevice);
	}

	/**
	 * verify Add PanelIcon
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyAddPanelIcon()
	{
		return isDisplayed(addpanel);
	}

	/**
	 * Verify Ck Editor
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyCKeditor()
	{
		return isDisplayed(ckTop, Speed.slow);
	}

	/**
	 * add Content In CKEditor
	 * @param content
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void addcontentInCkEditor(String content)
	{
		WebElement ele = findVisibleElement(element);
		ele.sendKeys(content);
	}

	/**
	 * verify Content Of Ck Editor
	 * @param content
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyContentofCKeditorPanel(String content)
	{
		String var = getText(elementp);
		return var.trim().equals(content.trim());

	}

	/**
	 * Click On Source In CK Editor
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnSourceInCKEditor()
	{
		WebElement ele = findVisibleElement(sourceInCkEditor);
		ele.click();
	}

	/**
	 * insert Data In Source
	 * @param msg
	 *            insert message in Ck Editor
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void InsertDataInSourceCKEditor(String msg)
	{
		WebElement eleSourceTextArea = findVisibleElement(sourceTextArea);
		WebElement elesourceOkButton = findVisibleElement(sourceOkButton);
		findVisibleElement(sourceTextArea, Speed.slow);
		eleSourceTextArea.sendKeys(msg.trim());
		elesourceOkButton.click();

	}

	/**
	 * Click On OK In Error Modal
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public void clickOnOkInErrorModal()
	{
		WebElement ele = findVisibleElement(errorModalOkButton);
		ele.click();
	}

	/**
	 * verify message In CK Editor
	 * @param msg
	 *            error Message
	 * @author krishna.bhadani
	 * @Created: 02 April 2018
	 */
	@Override
	public boolean verifyMessageInCKEditor(String msg)
	{
		String str = getText(errorModal);
		return msg.trim().equals(str.trim());
	}

	// ============= Data Visualisation Method ========//

	/**
	 * @param addDataInDataVisualisation
	 *            pass map to insert data like(panel fields name,fields values)
	 * @author krishna.bhadani
	 * @Created: 03 April 2018
	 */
	@Override
	public void addDataInDataVisualisation(Map<String, Object> addDataInDataVisualisation)
	{
		for (Entry<String, Object> value : addDataInDataVisualisation.entrySet())
		{
			switch (value.getKey())
			{
				case HomeLabels.PANEL_TITLE:
					findVisibleElement(visualisationTitleInput).sendKeys(value.getValue().toString());
					break;
				case HomeLabels.SITE:
					findVisibleElement(siteButton).click();
					findPresentElement(searchSite).sendKeys(value.getValue().toString());
					findVisibleElement(By.xpath(".//*[@id='siteScrollDiv']//a[normalize-space()='" + value.getValue().toString() + "']")).click();
					break;
				case HomeLabels.SOURCE:
					findVisibleElement(sourceButton).click();
					findVisibleElement(By.xpath(".//*[@id='showModuleId']//a[normalize-space()='" + value.getValue().toString() + "']")).click();
					break;
				case HomeLabels.ISHEETS:
					findVisibleElement(iSheetsButton).click();
					findPresentElement(searchIsheets).sendKeys(value.getValue().toString());
					findVisibleElement(By.xpath(".//*[@id='showModuleDataId']//a[normalize-space()='" + value.getValue().toString() + "']")).click();
					break;
				case HomeLabels.VIEW:
					findVisibleElement(viewButton).click();
					findVisibleElement(By.xpath(".//*[@id='showViewId']//a[normalize-space()='" + value.getValue().toString() + "']")).click();
					break;
				case HomeLabels.CHART_TYPE:
					findVisibleElement(chartTypeButton).click();
					findVisibleElement(By.xpath(".//*[@class='dropdown-menu pull-left copyDropdown']//span[text()='" + value.getValue().toString() + "']")).click();
					break;
				default:
					System.out.println("fields not match  ");
			}
		}
	}

	/**
	 * click on Select... In Data Tab
	 * @param buttonName
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnSelectButtonInData(String buttonName)
	{
		switch (buttonName)
		{
			case HomeLabels.CATEGORY:
				findVisibleElement(categoryButton, Speed.slow).click();
				break;
			case HomeLabels.VALUE:
				findVisibleElement(valueButton, Speed.slow).click();
				break;
			case HomeLabels.SERIES:
				findVisibleElement(seriesButton, Speed.slow).click();
				break;
			case HomeLabels.FILTER:
				findVisibleElement(filterButton, Speed.slow).click();
				break;

			default:
				System.out.println("button not match");
		}
	}

	/**
	 * Click on close Button in modal Window
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnCloseInModalWindow()
	{
		WebElement ele = findVisibleElement(closeButtonInModal);
		ele.click();
		findVisibleElement(dataVisualizationPanel);
	}

	/**
	 * click On Cancel In Modal Window
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnCancelInSelectModalWindow()
	{
		WebElement ele = findVisibleElement(cancelButtonInSelectModal);
		ele.click();
		findVisibleElement(dataVisualizationPanel);
	}

	/**
	 * click On Cancel In Modal Window
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnCancelInModalWindow()
	{
		WebElement ele = findVisibleElement(cancelButtonInModal);
		ele.click();
	}

	/**
	 * click On Done In Modal Window
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnDoneInModalWindow()
	{
		WebElement ele = findVisibleElement(doneButtonInModal);
		ele.click();
		findVisibleElement(dataVisualizationPanel);
	}

	/**
	 * @param itemName
	 * @param selectbuttonName
	 *            selectbuttonName->Category , Series , Filter , Value
	 * @author krishna.bhadani
	 */

	@Override
	public void selectOneItemInModalWindow(String sourceName, String itemName)
	{
		findVisibleElement(searchTextBox).sendKeys(itemName.trim());
		findVisibleElement(By.xpath(".//*[starts-with(@id,'" + sourceName.toLowerCase() + "')]//span[text()='" + itemName + "']"), Speed.slow).click();
	}

	/**
	 * @param itemName
	 * @param selectButtonName
	 *            selectbuttonName->Category , Series , Filter , Value
	 *            select-> check box =true,false
	 * @author krishna.bhadani
	 */
	@Override
	public void selectOneOrMoreItemsModalWindow(String sourceName, List<String> itemName, boolean select)
	{
		for (int i = 0; i < itemName.size(); i++)
		{
			setSelection(By.xpath(".//*[starts-with(@id,'" + sourceName.toLowerCase() + "')]//span[text()='" + itemName.get(i) + "']//parent::p//input"), select);
		}
		clickOnDoneInModalWindow();
		findVisibleElement(dataVisualizationPanel);
	}

	/**
	 * Remove Token
	 * @param itemName
	 * @param selectButtonName
	 *            selectbuttonName->Category , Series , Filter , Value
	 */
	@Override
	public void removeToken(String selectButtonName, String itemName)
	{
		findVisibleElement(By.xpath("//*[starts-with(@id,'" + selectButtonName.toLowerCase() + "')]//*[contains(text(),'" + itemName + "')]//preceding::a[1]"), Speed.slow).click();
	}

	/**
	 * @param selectButtonName
	 *            selectbuttonName->Category , Series , Filter , Value
	 * @param tokenName
	 *            selectbuttonName->Category , Series , Filter , Value
	 * @param tokenName.trim()
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnTokenDropDown(String selectButtonName, String tokenName)
	{
		findVisibleElement(By.xpath("//*[starts-with(@id,'" + selectButtonName.toLowerCase() + "')]//*[contains(text(),'" + tokenName.trim() + "')]"), Speed.slow).click();
	}

	/**
	 * select Aggregations From Drop Down
	 * @param selectButtonName
	 *            selectButtonName -> Value,filter
	 * @param tokenName.trim()
	 * @param aggregationsName
	 *            aggregationsName- > Sum,Count, Average
	 * @author krishna.bhadani
	 */
	@Override
	public void selectAggregationsFromDropDown(String selectButtonName, String tokenName, String aggregationsName, boolean select)
	{
		if (selectButtonName.equals(HomeLabels.FILTER))
		{
			clickOnTokenDropDown(selectButtonName, tokenName.trim());
			findVisibleElement(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label"), Speed.slow);
			int size = driver.findElements(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label")).size();
			for (int j = 1; j <= size; j++)
			{
				String str = getText(By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]"));
				if (aggregationsName.equals(str))
				{
					By checkBoxElem = By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]//input");
					setSelection(checkBoxElem, select);
				}
			}
			clickOnTokenDropDown(selectButtonName, tokenName.trim());
		}
		else
		{
			clickOnTokenDropDown(selectButtonName, tokenName.trim());
			findVisibleElement(By.xpath(".//*[contains(@id,'value')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//a[text()='" + aggregationsName + "']")).click();
		}

	}

	/**
	 * Click On Preview Button
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnPreviewButton()
	{
		findVisibleElement(previewButton, Speed.slow).click();
	}

	/**
	 * @param tokenName.trim()
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnSelectAllInFilterToken(String tokenName)
	{
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		findVisibleElement(By.xpath(".//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'selectAll')]")).click();
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
	}

	/**
	 * @param tokenName.trim()
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnClearFiltersInFilterToken(String tokenName)
	{

		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		findVisibleElement(By.xpath(".//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'clearAll')]")).click();
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
	}

	/**
	 * @param panelTitle
	 * @author krishna.bhadani
	 */
	public void clickOnMoreAction(String panelTitle)
	{
		findVisibleElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + panelTitle.trim() + "']"), Speed.slow).click();
		findVisibleElement(By.xpath(".//*[contains(@id,'headerTitle')]//*[text()='" + panelTitle.trim() + "']//ancestor::div[contains(@id,'container')]//a[contains(@id,'moreActionMenu')]"), Speed.slow).click();
	}

	/**
	 * @param panelTitle
	 * @param option
	 */
	@Override
	public void clickOnMoreActionOption(String panelTitle, String option)
	{
		clickOnMoreAction(panelTitle);
		findVisibleElement(By.xpath(".//*[contains(@id,'headerTitle')]//*[text()='" + panelTitle.trim() + "']//ancestor::div[contains(@id,'container')]//a[contains(@id,'moreActionMenu')]//parent::span//a[text()='" + option + "']"), Speed.slow).click();
		findVisibleElement(editPanelTitle);
	}

	/**
	 * @param chartName
	 * @param legends
	 * @author krishna.bhadani
	 */
	@Override
	public void clickOnLegends(String dashBoardOrchartPreview, String chartName, String legends)
	{

		if (HomeLabels.MULTI_SERIES_COLUMN.equals(chartName))
		{
			clickMultiSeriesChartLegends(dashBoardOrchartPreview, legends);
		}
		else if (HomeLabels.STACKED_COLUMN.equals(chartName))
		{
			clickOnStackedColumnLegends(dashBoardOrchartPreview, legends);
		}
		else
		{
			System.out.println(HomeLabels.PIE + "and" + HomeLabels.STANDARD_COLUMN + "legends not Display");
		}

	}

	/**
	 * verify Aggregation Token
	 * @param tokenName
	 * @param tokenName.trim()
	 * @param aggregation
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyAggregationToken(String tokenName, String aggregation)
	{
		return isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName + "')]//parent::div//following-sibling::div[contains(@id,'selectedToken')]//span[text()='" + aggregation + "']"), Speed.slow)
				&& isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName + "')]//parent::div//following-sibling::div[contains(@id,'selectedToken')]//span[text()='" + aggregation + "']//preceding::a[1]"), Speed.slow);
	}

	/**
	 * verify Aggregation Drop Down For Filter
	 * @param tokenName
	 * @param aggregationsName
	 *            map->Key=aggregation Name
	 *            value= status -> true , false
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyAggregationDropDownFilter(String tokenName, Map<String, Boolean> aggregationsName)
	{
		Map<String, Boolean> aggregationsMap = new LinkedHashMap<>();
		String str;
		boolean selected;

		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		findVisibleElement(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label"), Speed.slow);
		int size = driver.findElements(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label")).size();
		for (int j = 1; j <= size; j++)
		{
			str = getText(By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]"));
			selected = findVisibleElement(By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]//input"), Speed.slow).isSelected();
			aggregationsMap.put(str, selected);
		}
		Reporter.log(aggregationsMap.toString());
		boolean verify = isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'txt') and @placeholder='" + HomeLabels.SEARCH + "']"), Speed.slow)
				&& isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'selectAll') and text()='" + HomeLabels.SELECT_ALL + "']"), Speed.slow)
				&& isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'clearAll') and text()='" + HomeLabels.CLEAR_FILTERS + "']"), Speed.slow);

		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		return aggregationsMap.equals(aggregationsName) && verify;
	}

	/**
	 * verify Search In Filter Token
	 * @param searchItem
	 * @param tokenName.trim()
	 * @param selected
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifySearchInFilterToken(String searchItem, String tokenName, boolean aggregationsSelected)
	{
		String str = null;
		boolean sel = false;

		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		findVisibleElement(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'txt')]"), Speed.slow).sendKeys(searchItem);
		if (isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'btnClearSearch') and @data-original-title='" + HomeLabels.CLEAR_SEARCH_ITEM + "']"))
				|| isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'btnClearSearch') and @title='" + HomeLabels.CLEAR_SEARCH_ITEM + "']")))
		{

			int size = driver.findElements(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label")).size();
			for (int j = 1; j <= size; j++)
			{
				str = getText(By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]"));
				sel = findVisibleElement(By.xpath("(.//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label)[" + j + "]//input")).isSelected();
			}
			if (!str.trim().contains(searchItem.trim()))
			{
				return false;
			}
		}
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		return sel == aggregationsSelected;
	}

	/**
	 * verify Error Message In Filter Token
	 * @param searchItem
	 * @param tokenName.trim()
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyNoResultsFoundInFilterToken(String searchItem, String tokenName)
	{
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		findVisibleElement(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'txt')]"), Speed.slow).sendKeys(searchItem);
		boolean verify = isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label[text()='" + HomeLabels.NO_RESULTS_FOUND + "']"));
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		return verify;
	}

	/**
	 * verify Filter Token Modal Field
	 * @param tokenName
	 * @param tokenName.trim()
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyFilterTokenModalField(String tokenName)
	{
		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		boolean verify = isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'txt') and @placeholder='" + HomeLabels.SEARCH + "']"))
				&& isDisplayed(By.xpath(".//*[contains(@id,'filter')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//label[text()='" + HomeLabels.NO_RESULTS_FOUND + "']"))
				&& !isDisplayed(By.xpath(".//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'selectAll')]"))
				&& isDisplayed(By.xpath(".//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//*[contains(@id,'clearAll')]"));

		clickOnTokenDropDown(HomeLabels.FILTER.toLowerCase(), tokenName.trim());
		return verify;
	}

	/**
	 * verify Aggregation Drop Down For Value
	 * @param tokenName
	 * @param tokenName.trim()
	 * @param aggregationsName
	 *            aggregationsName-> Count , sum , Average
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyAggregationDropDownValue(String tokenName, List<String> aggregationsName)
	{
		ArrayList<String> list = new ArrayList<>();
		String str;

		clickOnTokenDropDown(HomeLabels.VALUE.toLowerCase(), tokenName.trim());
		int size = driver.findElements(By.xpath(".//*[contains(@id,'value')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//li")).size();
		for (int j = 1; j <= size; j++)
		{
			str = getText(By.xpath(".//*[contains(@id,'value')]//*[contains(text(),'" + tokenName.trim() + "')]//parent::div//li[" + j + "]//a"));
			list.add(str);
		}
		clickOnTokenDropDown(HomeLabels.VALUE.toLowerCase(), tokenName.trim());
		return aggregationsName.containsAll(list);
	}

	/**
	 * Verify Token For Category And Series
	 * @param selectButtonName
	 *            selectbuttonName->Category , Series
	 * @param tokenName.trim()
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyToken(String selectButtonName, String tokenName, String aggregationsName)
	{
		String str;
		String s;
		List<String> tokenNamesList = new ArrayList<>();
		if (isDisplayed(By.xpath(".//*[@id='data']//*[contains(@id,'" + selectButtonName.toLowerCase().trim() + "')]")))
		{
			if (aggregationsName.isEmpty())
			{
				str = getText(By.xpath("(.//*[contains(@id,'" + selectButtonName.toLowerCase() + "')]//parent::div//span)[1]"));
				return str.trim().equals(tokenName);
			}
			else
			{
				int num = driver.findElements(By.xpath(".//*[contains(@id,'" + selectButtonName.toLowerCase() + "')]//strong//parent::span")).size();
				for (int i = 1; i <= num; i++)
				{
					str = getText(By.xpath("(.//*[contains(@id,'" + selectButtonName.toLowerCase() + "')]//strong//parent::span)[" + i + "]"));
					tokenNamesList.add(str);
				}
				if (selectButtonName.equals(HomeLabels.VALUE))
				{
					s = aggregationsName.trim() + " of " + tokenName;
					return tokenNamesList.contains(s);
				}
				else
				{
					s = aggregationsName.trim() + " by " + tokenName;
					return tokenNamesList.contains(s);
				}
			}
		}
		return false;
	}

	/**
	 * verify Source in Data Visualisation
	 * @param sourceName
	 * @return
	 */
	@Override
	public boolean verifySourceInDataVisualisation(String sourceName)
	{
		findPresentElement(sourceButton).click();
		if (isDisplayed(By.xpath(".//*[@id='selectModuleID']//a[normalize-space()='" + sourceName.trim() + "']")))
		{
			findPresentElement(sourceButton).click();
			return true;
		}
		return false;
	}

	/**
	 * @param charttype
	 *            enter chart type :Pie,Column,Bar
	 * @param chartname
	 *            enter chart name
	 * @return
	 * @author krishna.bhadani
	 */

	@Override
	public boolean verifyChartType(List<String> charttype, List<String> chartname)
	{
		ArrayList<String> chartType = new ArrayList<>();
		ArrayList<String> chartName = new ArrayList<>();

		findClickableElement(chartDropDown).click();
		int num = driver.findElements(chartTypeOption).size();
		int num1 = driver.findElements(chartNameSize).size();

		for (int k = 1; k <= num; k++)
		{
			chartName.add(getText(By.xpath("(.//*[@class='dropdown-menu pull-left copyDropdown']//b)[" + k + "]")));
		}

		for (int i = 1; i <= num1; i++)
		{
			chartType.add(getText(By.xpath("(.//*[@class='dropdown-menu pull-left copyDropdown']//parent::ul//span)[" + i + "]")));
		}
		Reporter.log(" UI get chartName" + chartName.toString());
		Reporter.log("UI get chartType" + chartType.toString());
		return chartName.containsAll(chartname) && chartType.containsAll(charttype);
	}

	/**
	 * verify Default Chart Type Name
	 * @param optionName
	 * @return
	 * @author krishna.bhadani
	 */

	@Override
	public boolean verifyDefaultChartTypeName(String optionName)
	{
		String str = getText(defaultChartType);
		return str.equals(optionName);
	}

	/**
	 * verify Default Source
	 * @param optionName
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyDefaultSource(String optionName)
	{
		return isDisplayed(By.xpath(".//*[@id='showModuleId']//span[normalize-space()='" + optionName + "']"));
	}

	/**
	 * @param viewName
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyDefaultView(String viewName)
	{
		return isDisplayed(By.xpath(".//*[@id='showViewId']//span[normalize-space()='" + viewName + "']"));
	}

	/**
	 * @param isheetName
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyDefaultISheet(String isheetName)
	{
		return isDisplayed(By.xpath(".//*[@id='showModuleDataId']//span[normalize-space()='" + isheetName + "']"));
	}

	/**
	 * @param siteName
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyDefaultSite(String siteName)
	{
		return isDisplayed(By.xpath(".//*[@id='allSiteId']//span[normalize-space()='" + siteName + "']"));
	}

	/**
	 * verify chart preview fields
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyChartPreviewFields()
	{
		return isDisplayed(chartpreview)
				&& isDisplayed(dataButton)
				&& isDisplayed(svg);
	}

	/**
	 * @param viewName
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyView(String viewName)
	{
		findVisibleElement(viewButton).click();
		return isDisplayed(By.xpath(".//*[@id='showViewId']//a[text()=" + viewName + "]"));
	}

	/**
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyCustomiseButtonInDisabledMode()
	{
		return isDisplayed(customiseDisabled);
	}

	/**
	 * @return
	 * @author krishna.bhadani
	 */
	public boolean verifyViewButtonInDisabledMode()
	{
		return isDisplayed(viewDisabled);
	}

	/**
	 * verify data Tab In Chart Preview
	 * @param selectButtonName
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifySelectButtonInData(String selectButtonName)
	{
		switch (selectButtonName)
		{
			case HomeLabels.CATEGORY:
				return isDisplayed(categoryLabel) && isDisplayed(categoryButton);
			case HomeLabels.VALUE:
				return isDisplayed(valueLabel) && isDisplayed(valueButton);
			case HomeLabels.SERIES:
				return isDisplayed(seriesLabel) && isDisplayed(seriesButton);
			case HomeLabels.FILTER:
				return isDisplayed(filterLabel) && isDisplayed(filterButton);
			default:
				return false;
		}
	}

	/**
	 * verify Preview Button
	 * @param isDisabled
	 *            button is Disabled->true
	 *            button is enabled->false
	 * @return
	 */
	@Override
	public boolean verifyPreviewButtonsInDisabledMode()
	{
		return isDisplayed(By.xpath(".//button[text()='" + HomeLabels.PREVIEW + "' and @disabled='disabled']"));
	}

	/**
	 * verify Select Modal Title
	 * @param modalTitle
	 *            modal Title Name
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifySelectModalTitle(String modalTitle)
	{
		String str = getText(By.xpath(".//*[contains(@id,'ColumnModalHeader')]//div"));
		String title = "Select - " + modalTitle;
		return str.trim().equals(title.trim());
	}

	/**
	 * verify Modal Window Field
	 * @param modalLabel
	 *            cross
	 *            modalLabel-> name of the lable
	 *            -> select one or more items
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyModalWindowField(String modalLabel)
	{
		return isDisplayed(columnModalCrossButton)
				&& isDisplayed(By.xpath(".//*[text()='" + modalLabel.trim() + "']"))
				&& isDisplayed(By.xpath(".//*[contains(@id,'ColumnInputTextFeild') and @placeholder='" + HomeLabels.SEARCH + "']"));
	}

	/**
	 * verify List In Select One Item Modal Window
	 * @param selectButtonName
	 *            modal Name->Category , Value,Series ,Filter
	 * @return
	 */
	@Override
	public boolean verifyListInSelectOneItemModalWindow(String sourceName, List<String> itemList)
	{
		ArrayList<String> uiGetList = new ArrayList<>();
		int size = driver.findElements(By.xpath("//*[contains(@id,'" + sourceName.toLowerCase() + "')]//a//span")).size();
		for (int i = 1; i <= size; i++)
		{
			uiGetList.add(findVisibleElement(By.xpath("//*[contains(@id,'" + sourceName.toLowerCase() + "')]//a[" + i + "]//span")).getText());
		}
		return uiGetList.containsAll(itemList);
	}

	/**
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyCloseButtonInModal()
	{
		return isDisplayed(closeButtonInModal);
	}

	/**
	 * verify Done and Cancel Button In Modal
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyDoneCancelButtonInModal()
	{
		return isDisplayed(cancelButton) && isDisplayed(doneButtonDisabled);
	}

	@Override
	public boolean verifyListInSelectOneOrMoreItemsModalWindow(String sourceName, List<String> itemList)
	{
		ArrayList<String> uiGetList = new ArrayList<>();
		boolean verify = false;
		int size = driver.findElements(By.xpath(".//*[starts-with(@id,'" + sourceName.toLowerCase() + "')]//p//span")).size();
		for (int i = 1; i <= size; i++)
		{
			uiGetList.add(findVisibleElement(By.xpath(".//*[starts-with(@id,'" + sourceName.toLowerCase() + "')]//p[" + i + "]//span")).getText());
			verify = isDisplayed(By.xpath(".//*[starts-with(@id,'" + sourceName.toLowerCase() + "')]//p[" + i + "]//input"));

		}
		return uiGetList.containsAll(itemList) && verify;
	}

	/**
	 * Verify Clear Search
	 * @return
	 */
	@Override
	public boolean verifyClearSearch()
	{
		boolean verify = isDisplayed(clearSearch);
		findVisibleElement(clearSearch).click();
		String str = getText(searchTextBox);
		return verify && str.isEmpty();
	}

	/**
	 * @param searchKey
	 * @param selectButtonName
	 *            select modal Name->Category , Value,Series ,Filter
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifySearchInSelectOneItemModal(String searchKey)
	{
		findVisibleElement(searchTextBox).clear();
		findVisibleElement(searchTextBox).sendKeys(searchKey);
		if (isDisplayed(By.xpath(".//*[contains(@id,'ColumnModalBody')]//div[text()='" + HomeLabels.NO_RESULTS_FOUND + "']")) && verifyClearSearch())
		{
			return false;
		}
		else
		{
			findVisibleElement(By.xpath(".//*[contains(@id,'ColumnModalBody')]//a"), Speed.slow);
			int size = driver.findElements(By.xpath(".//*[contains(@id,'ColumnModalBody')]//a")).size();
			for (int i = 1; i <= size; i++)
			{
				String str = getText(By.xpath(".//*[contains(@id,'ColumnModalBody')]//a[" + i + "]//span"));
				if (!str.toLowerCase().contains(searchKey))
				{
					return false;
				}
			}

			return verifyClearSearch();
		}

	}

	/**
	 * verify Search In Select One Or More Item Modal
	 * @param searchKey
	 * @param selectButtonName
	 *            select modal Name->Category , Value,Series ,Filter
	 * @return
	 */
	@Override
	public boolean verifySearchInSelectOneOrMoreItemModal(String searchKey, boolean status)
	{
		findVisibleElement(searchTextBox).clear();
		findVisibleElement(searchTextBox).sendKeys(searchKey);
		boolean verify = false;
		if (isDisplayed(By.xpath(".//*[contains(@id,'ColumnModalBody')]//div[text()='" + HomeLabels.NO_RESULTS_FOUND + "']")))
		{
			return false;
		}
		else
		{
			findVisibleElement(By.xpath(".//*[contains(@id,'ColumnModalBody')]//span"), Speed.slow);
			int size = driver.findElements(By.xpath(".//*[contains(@id,'ColumnModalBody')]//span")).size();
			for (int i = 1; i <= size; i++)
			{
				String str = getText(By.xpath(".//*[contains(@id,'ColumnModalBody')]//p[" + i + "]//span"));
				verify = findVisibleElement(By.xpath(".//*[contains(@id,'ColumnModalBody')]//p[" + i + "]//input")).isSelected();
				if (!str.toLowerCase().contains(searchKey.toLowerCase()))
				{
					return false;
				}
			}

			return verifyClearSearch() && verify == status;
		}
	}

	/**
	 * verify Message In Chart Preview Area
	 * @param message
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyMessageInChartPreviewArea(String message)
	{
		String str = getText(chartPreviewMessage);
		return str.equals(message);
	}

	/**
	 * verify Error Message In Dash Board
	 * @param message
	 * @return
	 * @author krishna.bhadani
	 */

	@Override
	public boolean verifyErrorMessageInDashBoard(String panelName, String message)
	{
		findVisibleElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[2][text()='" + panelName.trim() + "']//parent::div[contains(@id,'headerTitle')]//following-sibling::div[contains(@id,'element')]"), Speed.slow);
		String str = getText(By.xpath(".//*[contains(@id,'headerTitle')]//div[2][text()='" + panelName.trim() + "']//parent::div[contains(@id,'headerTitle')]//following-sibling::div[contains(@id,'element')]"));
		return str.equals(message);
	}

	/**
	 * verify Chart In Chart Preview
	 * @param chartName
	 * @param toolTip
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyChart(String chartName, Map<String, String> toolTip, int numberOfElementInValue)
	{

		if (HomeLabels.STANDARD_PIE.equals(chartName))
		{
			map.clear();
			map = pieChart();
			Reporter.log("<B>UI Get ToolTip</B>" + map);
			Reporter.log("<B>Pass By TestCase</B>" + toolTip);
			return map.equals(toolTip);
		}
		else if (HomeLabels.STANDARD_COLUMN.equals(chartName))
		{
			map.clear();
			map = standardColumnChart();
			Reporter.log("<B>UI Get ToolTip</B>" + map);
			Reporter.log("<B>Pass By TestCase</B>" + toolTip);
			return map.equals(toolTip);
		}
		else if (HomeLabels.STACKED_COLUMN.equals(chartName) || HomeLabels.MULTI_SERIES_COLUMN.equals(chartName))
		{
			map.clear();
			map = stackedColumnChart(numberOfElementInValue);
			Reporter.log("<B>UI Get ToolTip</B>" + map);
			Reporter.log("<B>Pass By TestCase</B>" + toolTip);
			return map.equals(toolTip);
		}
		else
		{
			System.out.println(chartName + " not found");
		}
		return false;
	}

	/**
	 * @param chartName
	 * @param chartStatus
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyChartForLegends(String dashBoardOrchartPreview, String chartName, List<Boolean> chartStatus)
	{
		int standard;
		List<Boolean> uiStatus = new ArrayList<>();

		if (HomeLabels.STACKED_COLUMN.equals(chartName) || HomeLabels.MULTI_SERIES_COLUMN.equals(chartName))
		{
			if (dashBoardOrchartPreview.equals(HomeLabels.CHART_PREVIEW))
			{
				standard = driver.findElements(stackedChartInChartPreview).size();
				for (int i = 1; i <= standard; i++)
				{
					int count = driver.findElements(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect']")).size();
					if (count > 1)
					{
						for (int j = 1; j <= count; j++)
						{
							uiStatus.add(isDisplayed(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect'][" + j + "]")));
						}
						uiStatus.add(isDisplayed(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + standard + "]//*[name()='rect']")));
					}
					else
					{
						uiStatus.add(isDisplayed(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect']")));
					}
				}
			}
			else
			{
				standard = driver.findElements(stackedChartInDashBord).size();

				for (int i = 1; i <= standard; i++)
				{
					int count = driver.findElements(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect']")).size();
					if (count > 1)
					{
						for (int j = 1; j <= count; j++)
						{
							uiStatus.add(isDisplayed(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect'][" + j + "]")));
						}
						uiStatus.add(isDisplayed(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + standard + "]//*[name()='rect']")));
					}
					else
					{
						uiStatus.add(isDisplayed(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (i + standard) + "]//*[name()='rect']")));
					}
				}
			}
		}
		else
		{
			System.out.println(HomeLabels.PIE + "and" + HomeLabels.STANDARD_COLUMN + "legends not Display");
		}
		return uiStatus.containsAll(chartStatus);
	}

	/**
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifySaveButtonsInDisabledMode()
	{
		return isDisplayed(By.xpath(".//button[text()='" + HomeLabels.SAVE + "' and @disabled='disabled']"));
	}

	private Map<String, String> pieChart()
	{
		findVisibleElement(pieChart, Speed.slow);
		int pie = driver.findElements(pieChart).size();
		Reporter.log("Pie chart size :-" + pie);
		for (int i = 1; i <= pie; i++)
		{
			moveToElement(By.xpath(".//*[name()='svg']//*[name()='g'][4]//*[name()='g'][5]//*[name()='path'][" + i + "]"));
			// WebElement ele = findVisibleElement(By.xpath(".//*[name()='svg']//*[name()='g'][4]//*[name()='g'][5]//*[name()='path'][" + i + "]"));
			// Point p = ele.getLocation();
			// int x = p.getX();
			// int y = p.getY();
			// Actions a = new Actions(driver);
			// a.moveToElement(ele, x, y);
			String str = getText(toolTip);
			String[] list = str.split(",");
			map.put(list[0].trim(), list[1].trim());
		}
		return map;
	}

	private Map<String, String> standardColumnChart()
	{
		ArrayList<String> s = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		findVisibleElement(standChart, Speed.slow);
		int standard = driver.findElements(standChart).size();
		Reporter.log("standard chart size:-" + standard);
		for (int i = 1; i <= standard; i++)
		{
			moveToElement(By.xpath(".//*[name()='svg']//*[name()='g'][4]//*[name()='g'][2]//*[name()='rect'][" + i + "]"));
			String str = getText(toolTip);
			String[] list = str.split(",");
			s.clear();
			for (int k = 0; k < list.length - 1; k++)
			{
				s.add(list[k].trim());
			}
			sb.setLength(0);
			for (String strList : s)
			{
				sb.append(strList);
			}
			map.put(sb.toString(), list[list.length - 1].trim());
		}
		return map;
	}

	private Map<String, String> stackedColumnChart(int valueSize)
	{
		ArrayList<String> s = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		findVisibleElement(stackedChart, Speed.slow);
		int standard = driver.findElements(stackedChart).size();
		Reporter.log("stacked column chart size:-" + standard);
		for (int i = 1; i <= valueSize; i++)
		{
			int count = driver.findElements(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (valueSize + i) + "]//*[name()='rect']")).size();
			for (int j = 1; j <= count; j++)
			{
				moveToElement(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][4]//*[name()='g'][2]//*[name()='g'][" + (valueSize + i) + "]//*[name()='rect'][" + j + "]"));
				String str = getText(toolTip);
				String[] list = str.split(",");
				s.clear();
				for (int k = 0; k < list.length - 1; k++)
				{
					s.add(list[k].trim());
				}
				sb.setLength(0);
				for (String strList : s)
				{
					sb.append(strList);
				}
				map.put(sb.toString(), list[list.length - 1].trim());
			}
		}
		return map;
	}

	/**
	 * verify legends
	 * @param chartName
	 * @param legends
	 * @return
	 * @author krishna.bhadani
	 */
	@Override
	public boolean verifyLegends(String chartName, String legends)
	{
		if (HomeLabels.MULTI_SERIES_COLUMN.equals(chartName))
		{
			return multiSeriesChartLegends(legends);
		}
		else if (HomeLabels.STACKED_COLUMN.equals(chartName) || HomeLabels.STANDARD_PIE.equals(chartName))
		{
			return stackedColumnLegends(chartName, legends);
		}
		else
		{
			System.out.println(HomeLabels.STANDARD_COLUMN + "legends not Display");
			return true;
		}

	}

	private boolean multiSeriesChartLegends(String legends)
	{
		boolean verify;
		String legendsnew = null;
		List<String> list = new ArrayList<>();
		Reporter.log("Pass by test case:- Legends" + legends);

		if (isDisplayed(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text']//*[name()='tspan' and normalize-space()='" + legends + "']")))
		{
			return true;
		}
		else
		{
			List<String> str = new ArrayList<>();
			if (legends.contains(" "))
			{
				String[] str1 = legends.split(" ");
				legendsnew = str1[0].concat(str1[1]);
				list = Arrays.asList(legendsnew);
			}
			else
			{
				list = Arrays.asList(legends);
			}
			int size = driver.findElements(multiSeriesChartLegendsInChartPreview).size();
			for (int i = 1; i <= size; i++)
			{
				int size1 = driver.findElements(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]//*[name()='tspan']")).size();
				if (size1 > 1)
				{
					String str1;
					str1 = getText(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]"));
					System.out.println(str1);
					str.add(str1);
				}
			}
			Reporter.log("legends List " + str + "legends List pass by test script " + list);
			verify = str.containsAll(list);
		}
		return verify;
	}

	private void clickMultiSeriesChartLegends(String dashBoardOrchartPreview, String legends)
	{
		int size;
		if (dashBoardOrchartPreview.equals(HomeLabels.CHART_PREVIEW))
		{
			findVisibleElement(multiSeriesChartLegendsInChartPreview, Speed.slow);
			size = driver.findElements(multiSeriesChartLegendsInChartPreview).size();
			for (int i = 1; i <= size; i++)
			{
				String str1 = getText(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]//*[name()='tspan']"));
				if (str1.equals(legends))
				{
					WebElement ele = findClickableElement(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]"), Speed.slow);

					Actions action = new Actions(driver);
					action.click(ele).build().perform();

				}
			}
		}
		else
		{
			findVisibleElement(multiSeriesChartLegendsInDashBoard, Speed.slow);
			size = driver.findElements(multiSeriesChartLegendsInDashBoard).size();

			for (int i = 1; i <= size; i++)
			{
				String str1 = getText(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]//*[name()='tspan']"));
				if (str1.equals(legends))
				{
					WebElement ele = findClickableElement(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]"), Speed.slow);

					Actions action = new Actions(driver);
					action.click(ele).build().perform();
				}
			}
		}
	}

	private void clickOnStackedColumnLegends(String dashBoardOrchartPreview, String legends)
	{
		int size;
		if (dashBoardOrchartPreview.equals(HomeLabels.CHART_PREVIEW))
		{
			findVisibleElement(stackedChartLegendsInChartPreview, Speed.slow);
			size = driver.findElements(stackedChartLegendsInChartPreview).size();

			for (int i = 1; i <= size; i++)
			{
				String str1 = getText(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]//*[name()='tspan']"));
				if (str1.equals(legends))
				{
					WebElement ele = findClickableElement(By.xpath(".//*[@id='showChartDivId']//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]"));

					Actions action = new Actions(driver);
					action.click(ele).build().perform();
				}
			}
		}
		else
		{
			findVisibleElement(stackedChartLegendsInDashBord, Speed.slow);
			size = driver.findElements(stackedChartLegendsInDashBord).size();

			for (int i = 1; i <= size; i++)
			{
				String str1 = getText(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]//*[name()='tspan']"));
				if (str1.equals(legends))
				{
					WebElement ele = findClickableElement(By.xpath(".//*[contains(@id,'container')]//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]"));

					Actions action = new Actions(driver);
					action.click(ele).build().perform();
				}
			}
		}
	}

	private boolean stackedColumnLegends(String chartName, String legends)
	{
		boolean verify = false;
		String legendsnew = null;
		List<String> list = new ArrayList<>();
		int size = 0;
		findVisibleElement(stackedChartLegendsInChartPreview, Speed.slow);
		Reporter.log("Pass by test case:- Legends" + legends);
		if (isDisplayed(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='text']//*[name()='tspan' and normalize-space()='" + legends + "']")))
		{
			return true;
		}
		else
		{
			List<String> str = new ArrayList<>();
			if (legends.contains(" "))
			{
				String[] str1 = legends.split(" ");
				legendsnew = str1[0].concat(str1[1]);
				list = Arrays.asList(legendsnew);
			}
			else
			{
				list = Arrays.asList(legends);
			}
			if (chartName.equals(HomeLabels.STACKED_COLUMN))
			{
				size = driver.findElements(stackedChartLegendsInChartPreview).size();
				for (int i = 1; i <= size; i++)
				{
					int size1 = driver.findElements(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]//*[name()='tspan']")).size();
					if (size1 > 1)
					{
						String str1;
						str1 = getText(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][9]//*[name()='text'][" + i + "]"));
						System.out.println(str1);
						str.add(str1);
					}
				}
				Reporter.log("legends List " + str + "legends List pass by test script " + list);
				verify = str.containsAll(list);
			}
			else
			{
				size = driver.findElements(pieChartLegendsInChartPreview).size();
				for (int i = 1; i <= size; i++)
				{
					int size1 = driver.findElements(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]//*[name()='tspan']")).size();
					if (size1 > 1)
					{
						String str1;
						str1 = getText(By.xpath(".//*[name()='svg']//*[name()='g'][2]//*[name()='g'][8]//*[name()='text'][" + i + "]"));
						System.out.println(str1);
						str.add(str1);
					}
				}
				Reporter.log("legends List " + str + "legends List pass by test script " + list);
				verify = str.containsAll(list);
			}

		}
		return verify;
	}

	/**
	 * verify Favourite ToolTip Message
	 * @param message
	 * @return
	 * @author tejash.trivedi
	 */
	@Override
	public boolean verifyFavouriteToolTipMessage(String message)
	{
		return isDisplayed(By.xpath(".//*[@id='dashboardBuilder_favourite_ID']/*[@data-original-title='" + message + "']"));
	}

	/**
	 * click On Favourite Icon
	 * @author tejash.trivedi
	 */
	@Override
	public void clickOnFavouriteIcon()
	{
		findVisibleElement(favouriteButton).click();
	}

	/**
	 * @author ashlesha.shastri
	 * @param title
	 *            To verify Dashboard title
	 * @created 1/05/2018
	 */
	@Override
	public boolean verifyDashboardTitle(String title)
	{
		return isDisplayed(By.xpath(".//div[@class='dashboardPageTitle ng-binding' and normalize-space()='" + title.trim() + "']"), Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 * @param mailto
	 * @param startTime
	 * @param endTime
	 * @param emailSubject
	 * @param emailMessage
	 * @param appURL
	 * @created 1/05/2018
	 */
	@Override
	public boolean verifyMailWithHomeModuleLink(String mailto, Timestamp startTime, Timestamp endTime, String emailSubject, String emailMessage, String appURL)
	{
		String query;
		if (emailSubject.trim().isEmpty() || emailSubject.trim() == null)
		{
			query = "select TOP 1 * from Email where mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		else
		{
			query = "select TOP 1 * from Email where mailsubject = '" + emailSubject.trim() + "' and mailto = '" + mailto.trim() + "' and createddate between \'" + startTime + "\' and \'" + endTime + "\'" + " order by id DESC";
		}
		mailContent = getMailContent(query);
		if (mailContent.isEmpty() || mailContent == null)
		{
			return false;
		}
		else
		{
			createHtmlFile(mailHtmlFile, mailContent);
			getLocalHtmlPage(mailHtmlFile);
			mailto = mailto.substring(0, mailto.indexOf("@"));
			if (verifyContent(mailto, emailMessage))
			{
				System.out.println(emailMessage + " is present");
				WebElement elem = findVisibleElement(By.xpath(".//*[contains(text(),'" + appURL + "')]"));
				elem.click();
				return true;
			}
		}
		return false;

	}

	/**
	 * @author ashlesha.shastri
	 * @param title
	 *            To Enter Dashboard title
	 * @created 1/05/2018
	 */

	@Override
	public void enterDashboardTitle(String title)
	{
		WebElement elem = findVisibleElement(dashboardTitle, Speed.slow);
		elem.clear();
		elem.sendKeys(title);
	}

	/**
	 * @author ashlesha.shastri
	 *         Add content in Specific CKEditor
	 * @param panalName
	 * @param content
	 * @created 1/05/2018
	 */
	@Override
	public void addcontentInCkEditor(String panelName, String content)
	{
		WebElement elem = findVisibleElement(elementNew, Speed.slow);
		elem.clear();
		elem.sendKeys(content);
	}

	/**
	 * @author ashlesha.shastri
	 * @created 30/04/2018
	 *          For Verify content in CKEditor Panel
	 * @param panalName
	 * @param content
	 */
	@Override
	public boolean verifyContentofCKeditorPanel(String panelName, String content)
	{
		String var = getText(By.xpath(".//div[text()='" + panelName + "']/ancestor::*[contains(@id,'container')]//div[contains(@id,'element')]//p"));
		return var.trim().equals(content.trim());
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          For select more action option of specific Panel
	 * @param panalName
	 * @param option
	 */
	@Override
	public void selectMoreActionOptionOfPanel(String panelName, String option)
	{
		Reporter.log(panelName);
		moveToElement(elementNew);
		findClickableElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + panelName + "']/ancestor::div[contains(@id,'container')]//a[@data-original-title='More actions']")).click();
		WebElement optionToClick = findVisibleElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + panelName + "']/ancestor::div[contains(@id,'container')]//*[normalize-space(text())='" + option.trim() + "']"));
		optionToClick.click();
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          For click on Save on Edit Panel Model
	 */
	@Override
	public void clickOnSaveOnEditPanelModel()
	{
		WebElement ele = findClickableElement(saveButtonOnEditPanel);
		ele.click();
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          to add a link in specific content editor
	 * @param panelName
	 * @param linkURL
	 */
	@Override
	public void addLinkInCkEditor(String panelName, String linkURL)
	{
		WebElement elem = findVisibleElement(By.xpath(".//div[text()='" + panelName + "']/ancestor::*[contains(@id,'container')]//div[contains(@id,'element')]"));
		elem.click();
		findVisibleElement(linkIconOnCKEditor, Speed.slow).click();
		findVisibleElement(insertLinkModel, Speed.slow);
		findVisibleElement(externalLinkTab, Speed.slow).click();
		findVisibleElement(externalLinkTextbox).sendKeys(linkURL);
		findVisibleElement(insertLinkButton).click();
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          to verify a link in specific content editor
	 * @param contentPanelTitle
	 * @param linkURL
	 */
	@Override
	public boolean verifyLinkInCKEditor(String contentPanelTitle, String linkURL)
	{
		return isDisplayed(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + contentPanelTitle + "']/ancestor::div[contains(@id,'container')]//a[@href='" + linkURL + "']"), Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          to verify a link in specific content editor
	 * @param panelName
	 * @param macroName
	 */
	@Override
	public void addMacroInCKEditor(String panelName, String macroName)
	{

		WebElement elem = findVisibleElement(By.xpath(".//div[text()='" + panelName + "']/ancestor::*[contains(@id,'container')]//div[contains(@id,'element')]"));
		elem.click();
		findVisibleElement(macroIconOnCKEditor, Speed.slow).click();
		findVisibleElement(insertListModel, Speed.slow);
		findVisibleElement(listTitleTextbox, Speed.slow).clear();
		findVisibleElement(listTitleTextbox, Speed.slow).sendKeys(macroName);
		findVisibleElement(insertMacroButton, Speed.slow).click();

	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          to verify a macro in specific content editor
	 * @param panelName
	 * @param macroName
	 */
	@Override
	public boolean verifyMacroInCKEditor(String contentPanelTitle, String macroName)
	{
		return isDisplayed(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + contentPanelTitle + "']/ancestor::div[contains(@id,'container')]//div[contains(@id,'list')]/h4/strong[text()='" + macroName + "']"));
	}

	/**
	 * @author ashlesha.shastri
	 * @created 27/04/2018
	 *          to edit a macro in specific content editor
	 * @param panelName
	 * @param macroName
	 */
	@Override
	public void editMacroInCKEditor(String contentPanelTitle, String macroName)
	{
		WebElement elem = findClickableElement(By.xpath(".//div[text()='" + contentPanelTitle + "']/ancestor::*[contains(@id,'container')]//div[contains(@id,'element')]//*[@id='editMacro' and @data-original-title='Edit']"));
		elem.click();
		findVisibleElement(insertListModel, Speed.slow);
		findVisibleElement(listTitleTextbox, Speed.slow).clear();
		findVisibleElement(listTitleTextbox, Speed.slow).sendKeys(macroName);
		findVisibleElement(insertMacroButton, Speed.slow).click();

	}

	/**
	 * @author ashlesha.shastri
	 * @created 30/04/2018
	 *          to verify a macro in specific content editor
	 */
	@Override
	public boolean verifyFavourite()
	{
		return isDisplayed(favoriteIconSelected, Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 * @created 30/04/2018
	 *          to verify a content of tooltip when an item is marked as favourite/unfavourite
	 * @param favoriteTooltip
	 */
	@Override
	public boolean verifyFavouriteTooltip(String favoriteTooltip)
	{
		moveToElement(favoriteIcon);
		return isDisplayed(By.xpath(".//*[@class='tooltip-inner' and text()='" + favoriteTooltip + "']"), Speed.slow);
	}

	/**
	 * @author ashlesha.shastri
	 * @created 30/04/2018
	 *          Remove from favorite(Click on favorite star Icon),
	 */
	@Override
	public void removeFromFavourites()
	{
		if (isDisplayed(favoriteIconSelected))
		{
			findVisibleElement(favoriteIcon).click();
		}
		else
		{
			System.out.println("Favorites Icon is already not selected");
		}
	}

	/**
	 * @author surbhi.khetan
	 * @created 15/05/2018
	 *          Click on close button of window when an item is opened for print preview
	 */
	@Override
	public void clickOnCloseButtonOfPrintPreview()
	{

		findVisibleElement(closeButtonPrint, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @created 15/05/2018
	 *          <<<<<<< HEAD
	 * @param dashboard
	 *            title
	 *            To verify dashboard title in print preview
	 *            =======
	 * @param dashboard
	 *            title
	 *            To verify dashboard title in print preview
	 *            >>>>>>> stash
	 */
	@Override
	public boolean verifyDashboardTitleinPrintPreview(String title)
	{

		findVisibleElement(closeButtonPrint, Speed.slow);
		String dashboardTitle = driver.findElement(By.id("dashboardTitle")).getText();
		return title.trim().equalsIgnoreCase(dashboardTitle.trim());
	}

	/**
	 * @author surbhi.khetan
	 * @created 15/05/2018
	 * @param panelName
	 * @param content
	 *            <<<<<<< HEAD
	 *            To verify content of CKEditor in print preview
	 *            =======
	 *            To verify content of CKEditor in print preview
	 *            >>>>>>> stash
	 */
	@Override
	public boolean verifyContentofCKeditorPanelInPrintPreview(String panelName, String content)
	{
		String var = driver.findElement(By.xpath(".//div[text()='" + panelName + "']/ancestor::*[contains(@id,'container')]//div[contains(@id,'element')]//p")).getText();
		return var.trim().equals(content.trim());
	}

	/**
	 * @author surbhi.khetan
	 * @created 15/05/2018
	 * @param contentPanelTitle
	 * @param linkURL
	 *            <<<<<<< HEAD
	 *            To verify link in CKEditor in print preview
	 *            =======
	 *            To verify link in CKEditor in print preview
	 *            >>>>>>> stash
	 */
	@Override
	public boolean verifyLinkInCKEditorInPrintPreview(String contentPanelTitle, String linkURL)
	{
		String var = driver.findElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + contentPanelTitle + "']/ancestor::div[contains(@id,'container')]//a[@href='" + linkURL + "']")).getText();
		return var.trim().equals(linkURL.trim());
	}

	/**
	 * @author surbhi.khetan
	 * @created 15/05/2018
	 * @param contentPanelTitle
	 * @param macroName
	 *            <<<<<<< HEAD
	 *            To verify macro in print preview
	 *            =======
	 *            To verify macro in print preview
	 *            >>>>>>> stash
	 */
	@Override
	public boolean verifyMacroInCKEditorInPrintPreview(String contentPanelTitle, String macroName)
	{
		String var = driver.findElement(By.xpath(".//*[contains(@id,'headerTitle')]//div[text()='" + contentPanelTitle + "']/ancestor::div[contains(@id,'container')]//div[contains(@id,'list')]/h4/strong[text()='" + macroName + "']")).getText();
		return var.trim().equals(macroName.trim());
	}

	/**
	 * @author tejash.trivedi
	 *         Created on 24 May 2018
	 * @param chartTitle
	 * @throws IOException
	 */
	@Override
	public void gotoAddChartTitle(String chartTitle)
	{
		if (!isDisplayed(customiseDisabled))
		{
			clickOnCustomizeButton();
			findVisibleElement(chartTitleTxtBox).sendKeys(chartTitle);
			clickOnApplyButton();
		}
	}

	/**
	 * @author tejash.trivedi
	 *         Created on 24 May 2018
	 */
	public void clickOnCustomizeButton()
	{
		findClickableElement(customise).click();
	}

	/**
	 * @param legendPlace
	 * @author krishna.bhadani
	 */
	public void clickOnDisplayLegendInCustomise(String legendPlace)
	{
		findVisibleElement(By.xpath(".//*[@id='customize']//label[text()='Display legend']//parent::div//option[text()='" + legendPlace + "']")).click();
	}

	/**
	 * @author tejash.trivedi
	 *         Created on 24 May 2018
	 */
	public void clickOnApplyButton()
	{
		findClickableElement(applyButton).click();
	}

	/**
	 * click on data
	 * @author krishna.bhadani
	 */
	public void clickOnData()
	{
		findClickableElement(data).click();
	}

	/**
	 * @author tejash.trivedi
	 *         Created on 24 May 2018
	 * @param chartTitle
	 * @return
	 */
	@Override
	public boolean verifyChartVisibility(String chartTitle)
	{
		findPresentElement(svg, Speed.slow);

		if (isDisplayed(By.xpath(".//*[name()='svg']//*[name()='g']//*[name()='tspan' and normalize-space()='" + chartTitle + "']")))
		{
			return true;
		}
		return false;
	}

	/**
	 * @author tejash.trivedi
	 *         Created on 25 May 2018
	 * @param viewOption
	 * @return
	 */
	@Override
	public boolean verifyOptionsOfViewDropdown(String viewOption)
	{
		if (isDisplayed(By.xpath(".//*[@id='showViewId']//span[normalize-space()='" + viewOption + "']")))
		{
			return true;
		}
		return false;
	}
}
