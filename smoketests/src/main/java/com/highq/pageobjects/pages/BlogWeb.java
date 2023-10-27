package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.BlogLabels;
import com.highq.pageobjects.base.BlogPage;

public class BlogWeb extends BannerPageWeb implements BlogPage
{
	Actions build;

	// =================================== Blog Home Page ==================================
	String leftPanel = "//*[@id='blog_module_leftPanelPage']";

	By addPostPlusSymbol = By.xpath(".//a[@data-original-title = '" + BlogLabels.BLOG_ADDPOST + "']");

	By noPostsMessage = By.xpath(".//*[@class='noResultFound']");

	By addPostButton = By.xpath(".//button[text()='" + BlogLabels.BLOG_ADDPOST + "']");

	By favouritesLink = By.id("blog_module_favourites");

	By defaultCategoryLink = By.xpath(".//*[@id='initialDisplay']//span[text()='" + BlogLabels.BLOG_DEFAULTCATEGORY + "']");

	By defaultCategoryCount = By.xpath(".//*[@id='initialDisplay']//span[@class='label label-default pull-right']");

	By allCategoryLink = By.xpath(".//*[@id='initialDisplay']//span[text()='" + BlogLabels.BLOG_ALLCATEGORY + "']");

	By hideLeftPanelLink = By.xpath(leftPanel + "//span[text()='" + BlogLabels.BLOG_LEFTPANELHIDE + "']");

	By archiveLabel = By.xpath(leftPanel + "//*[normalize-space()='" + BlogLabels.BLOG_LEFTPANNEL_ARCHIVE + "']");

	// =================================== Add/Edit Blog Page [Content Tab] ==================================
	By addEditBlogTitleTextBox = By.id("addEditBlogTitleID");

	By addEditBlogSaveButtonTop = By.id("addEditBlogSaveBtnTopID");

	By addEditBlogSaveButtonBottom = By.id("addEditBlogSaveBtnBottomID");

	By addEditBlogCancelButtonTop = By.xpath(".//button[@id='addEditBlogSaveBtnTopID']//preceding::button[@class='btn btn-cancel'][1]");

	By addEditBlogCancelButtonBottom = By.xpath(".//button[@id='addEditBlogSaveBtnBottomID']//preceding::button[@class='btn btn-cancel'][1]");

	By contentTab = By.id("blogContentAndTagContainerIDClick");

	By settingsTab = By.id("settingTabContainerIDClick");

	By attachmentsTab = By.xpath(".//*[text()='" + BlogLabels.BLOG_ATTACHMENTS + "']");

	By ckEditor = By.id("cke_addBlogCKContentID");

	By addBlogContent = By.id("addBlogCKContentID");

	By addEditBlogTagTextBox = By.id("add_blog_tagList_ID-tokenfield");

	By addEditBlogNotificationsDropDown = By.id("blogNotificationID");

	By addEditBlogCategoriesDropDown = By.id("addEditBlog_SelectedCategoriesCount");

	By addEditBlogStatusDropDown = By.id("workFlowActionID");

	/** List Button in Add BLOG module */
	By addListInAddBlog = By.className("cke_button_icon cke_button__mymacrolink_icon");

	// =================================== Add/Edit Blog Page [Attachment Tab] ==================================

	By attachmentBrowseButton = By.id("addEditBlogAttachmentsckUploadFile");

	By attachmentFileUploadInput = By.xpath(".//input[@id='attachmentModal_uploadTab_fileInputTypeID']");

	By addAttachmentRecentTab = By.id("attachmentModal_displayRecentTab");

	By addAttachmentRecentSiteDropdown = By.id("attachmentModal_dropDownSiteList_recentTab_selectedSiteName");

	By addAttachmentRecentSiteSearchTextBox = By.id("attachmentModal_dropDownSiteList_recentTab_siteSearchInput");

	By addAttachmentRecentSiteList = By.id(".//*[@id='attachmentModal_dropDownSiteList_recentTab_siteDropdownDiv']");

	By addAttachmentRecentNoResultFound = By.xpath(".//*[@id='activityList']//*[normalize-space(.)='" + BlogLabels.BLOG_ADDATTACHMENT_NORESULTFOUND + "']");

	By addAttachmentBrowseTab = By.id("attachmentModal_displayBrowseTab");

	By addAttachmentBrowseSiteDropdown = By.id("attachmentModal_dropDownSiteList_browseTab_filesFolder_selectedSiteName");

	By addAttachmentExpandAllLink = By.xpath(".//*[@id='link_modal_expandAllDiv']//a[contains(text(), '" + BlogLabels.BLOG_ADDATTACHMENT_EXPANDALL + "')]");

	By addAttachmentCollapseLink = By.xpath(".//*[@id='link_modal_expandAllDiv']//a[contains(text(), '" + BlogLabels.BLOG_ADDATTACHMENT_COLLAPSE + "')]");

	By addAttachmentUploadTab = By.id("attachmentModal_displayUploadTab");

	By addAttachmentUploadFile = By.id("attachmentModal_uploadTab_fileInputTypeID");

	By addAttachmentSearchTab = By.id("attachmentModal_displaySearchTab");

	By addAttachmentSearchDropDown = By.id("insertLinkModal_dropDownSiteList_searchTab_selectedSiteName");

	By addAttachmentSearchTextBox = By.id("insertlink_searchTab_searchContentInput");

	By addAttachmentCancelButton = By.id("ATTACHMENT_MODAL_cancelButton");

	By addAttachmentAttachButton = By.id("ATTACHMENT_MODAL_insertButton");

	By addAttachmentCloseButton = By.id("ATTACHMENT_MODAL_MAIN_CLOSE_BUTTON");

	// =================================== Add/Edit Blog Page [Settings Tab] ==================================

	By blogAuthorTestBox = By.id("blogAuthorID");

	By addHeaderImageLink = By.xpath(".//a[text()='" + BlogLabels.BLOG_SETTINGS_HEADERIMAGE_INSERT + "']");

	By publishDateDropDown = By.id("addBlog_select_publishedDate");

	By allowCommentsCheckBox = By.id("allowBlogCommentID");

	By browseButton = By.xpath(".//*[@id='headerImage_uploadDiv']//a");

	By headerImageInput = By.id("headerImage_file");

	By headerImageCancelButton = By.id("HEADER_IMAGE_MODAL_CANCEL_BTN");

	By headerImageSaveButton = By.id("HEADER_IMAGE_MODAL_SAVE_BTN");

	By headerImageCloseButton = By.id("HEADER_IMAGE_MODAL_MAIN_CLOSE_BUTTON");

	By headerImageMessage = By.xpath("//*[@id='HEADER_IMAGE_MODAL']//*[contains(text(),'" + BlogLabels.BLOG_SETTINGS_HEADERIMAGE_MESSAGE + "')]");

	By headerImageProgressBar = By.id("progressbar_div");

	By headerImageProgressBarCancelLink = By.xpath(".//*[@class='icon-remove progressClose tooltipShow floatNone']");

	/** To quit the modals that appears */
	By modalQuitButton = By.xpath("//*[contains(@id,'MAIN_CLOSE_BUTTON')]");

	/** Modal Appears by clicking on insert list in add BLOG */
	By insertMacroModal = By.id("INSERT_MACRO_MODAL");

	/** To type list macro drop down a type in insert list modal */
	By typeDropDownInInsertListModal = By.id("insertMacroListDropDownID");

	/** Title text box in insert list modal */
	By titleInInsertListModal = By.name("macro.MACRO_PROP_TYPE_TITLE");

	/** Items to display drop down in insert list modal */
	By itemsToDisplayDropDownInInsertListModal = By.id("macroPage_itemsToDisplay_fieldID");

	/** MetaData Drop Down in insert list modal */
	By MetaDataDropDownInInsertListModal = By.xpath("//*[@id='insertMacroList_recentActivityTab_metaDataList']//*[@class='btn dropdown-toggle errorElement']");

	/** Site Drop Down in insert list modal */
	By siteDropDownInInsertListModal = By.xpath("//*[@id='insertMacro_siteDropDown_selectedSiteName']/..");

	/** Search text box in Site Drop Down in insert list modal */
	By searchBoxInSiteDropDown = By.id("insertMacro_siteSearchInput");

	/** Cancel Button in insert list modal */
	By cancelButtonInInsertListModal = By.id("INSERT_MACRO_MODAL_CANCEL_BTN");

	/** Insert Button in insert list modal */
	By insertButtonInInsertListModal = By.id("INSERT_MACRO_MODAL_POST_BTN");

	/** Global message in add BLOG */
	By globalMessageInAddBlog = By.xpath("//*[@id='isheet_module_add_item_closeGlobal_msg']/..");

	/** Enter title message when no title provided in add blog */
	By enterTitleMessage = By.id("addEditBlogTitleID_pID");

	/** Category message when no Category is selected in add blog */
	By enterCategoryMessage = By.id("addEditBlog_SelectedCategoriesCount_pID");

	/** Content message when no Content is provided in add blog */
	By enterContentMessage = By.id("addBlogCKContentID_pID");

	/** Read More button at blog home page under a blog */
	By readMoreButton = By.xpath("//*[contains(text(),'" + BlogLabels.BLOG_READMORE + "')]");

	/** Modal Apears when we click on cancel button in add blog */
	By autoSaveDraftModal = By.id("collaborateCustomMessageModal");

	/** Discard button in auto save draft modal apears when we click on cancel button in add blog */
	By autoSaveDraftModalDicardButton = By.id("collaborateMessageCancelButton");

	/** Save button in auto save draft modal apears when we click on cancel button in add blog */
	By autoSaveDraftModalSaveButton = By.id("collaborateMessageOkButton");

	By headerImageModal = By.id("HEADER_IMAGE_MODAL");

	By saveButtonInHeaderImageModal = By.id("HEADER_IMAGE_MODAL_SAVE_BTN");

	By cancelButtonInHeaderImageModal = By.id("HEADER_IMAGE_MODAL_CANCEL_BTN");

	By insertButtonInAddPost = By.xpath("//*[@id='settingTabContainerID']//*[contains(@class,'btn btn-default btn-sm uploadBtn')]");

	By browseButtonInHeaderImageModal = By.id("headerImage_file");

	By blogEditPage = By.id("addEditBlogFormID");

	By recentBlog = By.xpath("(//*[@id='blogListBody']//a[@class='linkblack'])[1]");

	By headerImageInDetailsSection = By.xpath("//*[@id='blogModuleMiddlePanelID']//img[@class='img-responsive']");

	By publishDateDateTextBox = By.id("blogPublishedDate");

	By publishDateTimeTextBox = By.xpath("(//*[@id='createBlog_blogPublishedDate_timePicker']/input)[1]");

	By blogModuleMiddlePannelInScheduledPosts = By.id("blogModuleMiddlePanelID");

	By watchIconInEditSection = By.xpath("//*[@id='createBlog_blogPublishedDate_timePicker']//*[@data-original-title='Start time']");

	By timePickerModal = By.xpath("//*[@class='timepicker-picker']//*[@class='table-condensed']");

	By minuteIncrementButton = By.xpath("//*[@class='timepicker-picker']//*[@data-action='incrementMinutes']//*[@class='icon icon-chevron-up']");

	By moreActionButtonInDetailsSection = By.xpath("//*[@data-original-title='More actions']");

	By deleteButtonInMyDrafts = By.id("deleteDraftsBtn");

	By modal = By.id("collaborateCustomMessageModal");

	By modalMessage = By.id("collaborateCustomModalMessage");

	By resumeDiscardYellowStrip = By.id("resumeDiscardID");

	By reumeButtonInYelloStrip = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space(text())='" + BlogLabels.BLOG_RESUME_INYELLOWSTRIP + "']");

	By discardButtonInYelloStrip = By.xpath("//*[@id='resumeDiscardID']//*[normalize-space(text())='" + BlogLabels.BLOG_DISCARD_INYELLOWSTRIP + "']");

	By searchTextBox = By.id("myDraftSearch");

	By blogListInMyDrafts = By.id("blogMyDraftBody");
	String deletePostMessage = "Are you sure you want to delete this post?";
	By deletePostModelMessage = By.xpath(".//*[@id='collaborateCustomMessageModal']//child::*[@id='collaborateCustomModalMessage']");
	By deleteModel = By.xpath(".//*[@id='collaborateCustomMessageModal']");
	By deleteModelDisappeared = By.xpath(".//*[@id='collaborateCustomMessageModal' and contains(@style,'display: none;')]");
	By cancelDeletePost = By.xpath(".//*[@id='collaborateCustomMessageModal']//child::*[@id='collaborateMessageCancelButton']");
	By deletePost = By.xpath(".//*[@id='collaborateCustomMessageModal']//child::*[@id='collaborateMessageOkButton']");

	/** Favourite */
	By favouriteStarSelected = By.xpath("(//*[contains(@class,'icon-star-selected') and @data-original-title='" + BlogLabels.BLOG_REMOVEFROMFAVOURITES + "'])[last()]");
	By favouriteButton = By.xpath("(//*[contains(@class,'icon-star') and @data-original-title='" + BlogLabels.BLOG_ADDTOFAVOURITES + "'])[last()]");

	By attachmentsTabContainer = By.xpath("//*[@id='addEditAttachmentContainerID' and @class='relativeDiv']");

	By settingsTabContainer = By.xpath("//*[@id='settingTabContainerID' and @class='form-horizontal ckContentHeight taberrordata']");

	By likeBlogButton = By.id("likeContent");

	By commentBlogButton = By.xpath("//*[contains(@id,'commentLink')]");

	By blogCommentBox = By.xpath("//*[contains(@id,'addCommentField') and @role='textbox']");

	By cancelButtonInDetailSection = By.xpath("//*[contains(@id,'comment_commentFooter')]//*[@class='btn btn-cancel']");

	By postButtonInDetailSection = By.xpath("//*[contains(@id,'postBtn_addCommentField') and  not(contains(@class,'disabled'))]");

	By commentCount = By.id("blogCommentCount");

	By likeCount = By.id("blogLikeCount");

	By commentIcon = By.xpath("//*[contains(@class,'icon icon-comment-outline')]");

	By lastReplyOfComment = By.xpath("(//*[@class='ckContentArea']//p)[last()]");

	By commentBoxRenderedText = By.xpath("//*[@class='ckContentArea inlineCK cke_editable cke_editable_inline cke_contents_ltr cke_show_borders cke_focus']/p");

	By contentLike = By.id("contentLike_");

	By includeCommentsCheckBox = By.id("includeCommentForElseModal");

	By printAndExportModal = By.id("PRINT_AND_EXPORT_CONFIRM_MODAL");

	By downloading = By.id("globalProcessMessageContainerMsgSpace");

	/* ************************************* METHODS ***************************************** */

	WebElement element;

	public BlogWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnSaveButtonInAutoSaveDraftModal()
	{
		element = findVisibleElement(autoSaveDraftModalSaveButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Discard button in Auto save draft modal
	 */
	@Override
	public void clickOnDiscardButtonInAutoSaveDraftModal()
	{
		element = findVisibleElement(autoSaveDraftModalDicardButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To Verify auto save draft modal appears when we click on cancel button in add blog
	 */
	@Override
	public boolean verifyAutoSaveDraftModal()
	{
		return (isDisplayed(autoSaveDraftModal));
	}

	/**
	 * @author vivek.mishra
	 *         To click on read more button under a blog added
	 */
	@Override
	public void clickOnReadMoreButton()
	{
		findClickableElement(readMoreButton).click();
	}

	/**
	 * @author vivek.mishra
	 *         To Verify message appears on top when no title provided in add blog
	 */
	@Override
	public boolean verifyEneterContentMessage()
	{
		return (isDisplayed(enterContentMessage));
	}

	/**
	 * @author vivek.mishra
	 *         To Verify message appears on top when no title provided in add blog
	 */
	@Override
	public boolean verifyEneterCategoryMessage()
	{
		return (isDisplayed(enterCategoryMessage));
	}

	/**
	 * @author vivek.mishra
	 *         To Verify message appears on top when no title provided in add blog
	 */
	@Override
	public boolean verifyEneterTitleMessage()
	{
		return (isDisplayed(enterTitleMessage));
	}

	/**
	 * @author vivek.mishra
	 *         To Verify message appears on top when no data provided in add blog
	 */
	@Override
	public boolean verifyGlobalMessage()
	{
		return (isDisplayed(globalMessageInAddBlog));
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnAddPlusSign()
	{
		WebElement addPostPlusSymbolEle = findVisibleElement(addPostPlusSymbol, Speed.slow);
		addPostPlusSymbolEle.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public boolean verifyFavouritesLink()
	{
		findClickableElement(favouritesLink);
		return isDisplayed(favouritesLink);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnHide()
	{
		if (isDisplayed(hideLeftPanelLink))
		{
			findClickableElement(hideLeftPanelLink).click();
		}
		else
		{
			System.out.println("Element is already hidden");
		}
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public int getCategoryCount(String category)
	{
		WebElement categoryCountEle = findVisibleElement(By.xpath(".//*[@id='initialDisplay']//a[@title='" + category + "']//preceding::span[@class='label label-default pull-right']"));
		return Integer.parseInt(categoryCountEle.getText());
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void sendTextInBlogTitle(String blogName)
	{
		WebElement addEditBlogTitleEle = findVisibleElement(addEditBlogTitleTextBox, Speed.slow);
		addEditBlogTitleEle.clear();
		addEditBlogTitleEle.sendKeys(blogName);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnSaveOnAddBlog()
	{
		WebElement addEditBlogSaveTopEle = findVisibleElement(addEditBlogSaveButtonTop, Speed.slow);
		addEditBlogSaveTopEle.click();
	}

	/**
	 * @author vivek.mishra
	 * @modified on 15/02/2018
	 */
	@Override
	public void clickOnCancelOnAddBlog()
	{
		WebElement addEditBlogCancelBottomEle = findVisibleElement(addEditBlogCancelButtonBottom, Speed.slow);
		addEditBlogCancelBottomEle.click();
	}

	/**
	 * @author vivek.mishra
	 *         To go to tabs in add blog
	 */
	@Override
	public void clickOnTabsInBlogModule(String tabName)
	{
		element = findVisibleElement(By.xpath("//*[@class='nav nav-pills tabError']//a[normalize-space(.)='" + getUserData(tabName.trim()) + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void addBlogContent(String blogContent)
	{
		verifyCkEditor();
		WebElement addBlogContentEle = findVisibleElement(addBlogContent, Speed.slow);
		addBlogContentEle.clear();
		addBlogContentEle.sendKeys(blogContent);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void addBlogTag(String tag)
	{
		element = findVisibleElement(addEditBlogTagTextBox, Speed.slow);
		element.sendKeys(tag.trim());
		element.sendKeys(Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void addBlogTags(List<String> tags)
	{
		element = findVisibleElement(addEditBlogTagTextBox, Speed.slow);
		for (int i = 0; i < tags.size(); i++)
		{
			element.sendKeys(tags.get(i));
			element.sendKeys(Keys.ENTER);
		}
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnNotifications()
	{
		element = findVisibleElement(addEditBlogNotificationsDropDown, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void setNotifications(String notificationPreference)
	{
		clickOnNotifications();
		element = findVisibleElement(By.xpath(".//*[@id='blogDropDownStatusID']//a[text()='" + notificationPreference.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnCategories()
	{
		element = findVisibleElement(addEditBlogCategoriesDropDown, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void selectCategory(String category)
	{
		clickOnCategories();
		element = findVisibleElement(By.xpath(".//label[@title='" + category.trim() + "']//input"), Speed.slow);
		if (!element.isSelected())
		{
			element.click();
		}
		clickOnCategories();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnStatus()
	{
		element = findVisibleElement(addEditBlogStatusDropDown, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void setStatus(String status)
	{
		clickOnStatus();
		element = findVisibleElement(By.xpath(".//*[@id='addEditBlogDropDownStatusID']//a[text()='" + status.trim() + "']"), Speed.slow);
		element.click();
	}

	@Override
	public void clickOnBrowseInAttachments()
	{
		WebElement attachmentBrowseEle = findClickableElement(attachmentBrowseButton, Speed.slow);
		attachmentBrowseEle.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnAddAttachmentsRecentTab()
	{
		WebElement addAttachmentRecentEle = findClickableElement(addAttachmentRecentTab);
		addAttachmentRecentEle.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void clickOnSiteInRecentTab()
	{
		WebElement addAttachmentRecentSiteEle = findClickableElement(addAttachmentRecentSiteDropdown, Speed.slow);
		addAttachmentRecentSiteEle.click();
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public void selectSiteInRecentTab(String siteName) throws InterruptedException
	{
		clickOnSiteInRecentTab();
		WebElement addAttachmentRecentSiteSearchEle = findClickableElement(addAttachmentRecentSiteSearchTextBox);
		addAttachmentRecentSiteSearchEle.sendKeys(siteName);

		if (isDisplayed(By.xpath(".//*[@id='attachmentModal_dropDownSiteList_recentTab_siteList']//a[text()='" + siteName + "']")))
		{
			WebElement siteEle = findClickableElement(By.xpath(".//*[@id='attachmentModal_dropDownSiteList_recentTab_siteList']//a[text()='" + siteName + "']"), Speed.slow);
			siteEle.click();
		}
		else
		{
			addAttachmentRecentSiteSearchEle.clear();
			List<WebElement> siteListEle = driver.findElements(addAttachmentRecentSiteList);
			for (WebElement site : siteListEle)
			{
				if (site.getText().trim().equalsIgnoreCase(siteName))
				{
					scrollToElement(site);
					site.click();
				}
			}
		}
	}

	/**
	 * @author vivek.mishra
	 *         To attach a file with browse button in add Blog attachments tab
	 */
	@Override
	public void attachFileInAddBlobAttachmentTab(String fileName)
	{
		verifyAttachmentsTabContainer();
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		findPresentElement(attachmentBrowseButton, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 */
	@Override
	public boolean verifySearchresultFound()
	{
		return (isDisplayed(addAttachmentRecentNoResultFound));
	}

	/**
	 * @author vivek.mishra
	 *         To get the count of
	 *         default categories in blog module
	 */
	@Override
	public int verifyDefaultCategoryCount(String categoryName)
	{
		return (Integer.parseInt(findPresentElement(defaultCategoryCount).getText()));
	}

	/**
	 * @author vivek.mishra
	 *         To Add a blog in blog module by passing complete data in map
	 * @modified on 15/02/2018
	 */
	@Override
	public void addBlog(Map<String, String> blogData)
	{
		clickOnAddPlusSign();
		for (Map.Entry<String, String> m : blogData.entrySet())
		{
			String lowerCase = m.getKey().toLowerCase();
			if (BlogLabels.BLOG_ADDBLOG_TITLE.toLowerCase().equals(lowerCase))
			{
				sendTextInBlogTitle(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_TAGS.toLowerCase().equals(lowerCase))
			{
				addBlogTag(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_NOTIFICATION.toLowerCase().equals(lowerCase))
			{
				setNotifications(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_STATUS.toLowerCase().equals(lowerCase))
			{
				setStatus(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_CATEGORY.toLowerCase().equals(lowerCase))
			{
				selectCategory(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_CONTENT.toLowerCase().equals(lowerCase))
			{
				addBlogContent(m.getValue());
			}
			else if (BlogLabels.BLOG_ATTACHMENTS.toLowerCase().equals(lowerCase))
			{
				clickOnTabsInBlogModule(BlogLabels.BLOG_ATTACHMENTS);
				attachFileInAddBlobAttachmentTab(m.getValue());
			}
			else if (BlogLabels.BLOG_SETTINGS.toLowerCase().equals(lowerCase))
			{
				clickOnTabsInBlogModule(BlogLabels.BLOG_SETTINGS);
				if (m.getValue() != null || m.getValue() != " ")
				{
					AttachFileInAddBlobSettingsTabHeaderImageModal(m.getValue());
				}

			}
		}
		verifyBlogEditPage();
		clickOnSaveOnAddBlog();
	}

	/**
	 * @author vivek.mishra
	 *         To select a tab in add attachment modal
	 */
	@Override
	public void clickonTabInAddAttachmentModal(String tabName)
	{
		findClickableElement(By.xpath("//*[@id='attachmentModal_navigationLinks']//a[text()='" + tabName + "']")).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on quit modal button to close the popup modal
	 */
	@Override
	public void clickOnModalQuitButton()
	{
		findClickableElement(modalQuitButton).click();
	}

	/**
	 * @author vivek.mishra
	 *         To select a type in insert list modal
	 */
	@Override
	public void selectTypeInInsertListModal(String macroType)
	{
		Select select = new Select((WebElement) typeDropDownInInsertListModal);
		select.selectByValue(macroType);
	}

	/**
	 * @author vivek.mishra
	 *         To send a text in title text box in insert list modal
	 */
	@Override
	public void sendTextInTitleInInsertListModal(String title)
	{
		findPresentElement(titleInInsertListModal).sendKeys(title);
	}

	/**
	 * @author vivek.mishra
	 *         To send a text in search box in site drop down in insert list modal
	 */
	@Override
	public void sendTextInSearchTextBoxInSiteDropDown(String text)
	{
		findPresentElement(searchBoxInSiteDropDown).sendKeys(text);
	}

	/**
	 * @author vivek.mishra
	 *         To click on site drop down in insert list modal
	 */
	@Override
	public void clcikOnSiteDropDownInInsertListModal()
	{
		findClickableElement(siteDropDownInInsertListModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on a site in site drop down in insert list modal
	 */
	@Override
	public void selectSiteInSiteDropDownInInsertListModal(String siteName)
	{
		clcikOnSiteDropDownInInsertListModal();
		sendTextInSearchTextBoxInSiteDropDown(siteName);
		findClickableElement(By.xpath("//*[@id='insertMacro_siteDropDown_siteList']//a[contains(text(),'" + siteName + "')]")).click();
	}

	/**
	 * @author vivek.mishra
	 *         To select item in items to display drop down in insert list modal
	 */
	@Override
	public void selectItemsInToDisplayDropDownInInsertListModal(String count)
	{
		Select select = new Select((WebElement) itemsToDisplayDropDownInInsertListModal);
		select.selectByValue(count);
	}

	/**
	 * @author vivek.mishra
	 *         To click on metadata drop down in insert list modal
	 */
	@Override
	public void clickOnMetaDataDropDownInInsertListModal()
	{
		findClickableElement(MetaDataDropDownInInsertListModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To select data in metadata drop down in insert list modal
	 */
	@Override
	public void selectMetaDataInInsertListModal(String metaData)
	{
		clickOnMetaDataDropDownInInsertListModal();
		findClickableElement(By.xpath("//*[contains(@id,'insertMacroList_recentActivity" + metaData + "')]")).click();

	}

	/**
	 * @author vivek.mishra
	 *         To select data in metadata by passing string array drop down in insert list modal
	 */
	@Override
	public void selectMetaDataInInsertListModal(String metaData[])
	{
		clickOnMetaDataDropDownInInsertListModal();
		for (int i = 0; i < metaData.length; i++)
		{
			findClickableElement(By.xpath("//*[contains(@id,'insertMacroList_recentActivity" + metaData[i] + "')]")).click();
		}

	}

	/**
	 * @author vivek.mishra
	 *         To click on Cancel Button in insert list modal
	 */
	@Override
	public void clickOnCancelButtonInInsertListModal()
	{
		findClickableElement(cancelButtonInInsertListModal).click();

	}

	/**
	 * @author vivek.mishra
	 *         To click on Insert Button in insert list modal
	 */
	@Override
	public void clickOnInsertButtonInInsertListModal()
	{
		findClickableElement(insertButtonInInsertListModal).click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on list Button in Add BLOG
	 */
	@Override
	public void clickOnListButtonInAddBlog()
	{
		findClickableElement(addListInAddBlog).click();
	}

	/**
	 * @author vivek.mishra
	 *         To Add a list in Add BLOG
	 */
	@Override
	public void addList(Map<String, String> listData)
	{
		clickOnListButtonInAddBlog();
		for (Map.Entry<String, String> m : listData.entrySet())
		{
			String lowerCase = m.getKey().toLowerCase();
			if (BlogLabels.BLOG_ADDBLOG_TYPE.toLowerCase().equals(lowerCase))
			{
				selectTypeInInsertListModal(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_TITLE.toLowerCase().equals(lowerCase))
			{
				sendTextInTitleInInsertListModal(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_SITE.toLowerCase().equals(lowerCase))
			{
				selectSiteInSiteDropDownInInsertListModal(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_ITEMSTODISPLAY.toLowerCase().equals(lowerCase))
			{
				selectItemsInToDisplayDropDownInInsertListModal(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_CATEGORY.toLowerCase().equals(lowerCase))
			{
				selectMetaDataInInsertListModal(m.getValue());
			}
			else
			{
				System.out.println("Yoy have not provided proper data in map");
			}
		}
		clickOnInsertButtonInInsertListModal();
	}

	/**
	 * @author vivek.mishra
	 *         To attach a file with browse button in add Blog settings tab header image modal
	 * @creation date 15/02/2018
	 */
	@Override
	public void AttachFileInAddBlobSettingsTabHeaderImageModal(String fileName)
	{
		verifySettingsTabContainer();
		clickOnInsertButtonInAddPostSettingsTab();
		verifyHeaderImageModal();
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName;
		findPresentElement(browseButtonInHeaderImageModal, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
		clickOnSaveButtonInHeaderImageModal();
	}

	/**
	 * @author vivek.mishra
	 *         To click on insert button in add post settings tab
	 * @creation date 15/02/2018
	 */
	@Override
	public void clickOnInsertButtonInAddPostSettingsTab()
	{
		element = findVisibleElement(insertButtonInAddPost, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return the modal availability
	 * @creation date 15/02/2018
	 */
	@Override
	public boolean verifyHeaderImageModal()
	{
		findVisibleElement(headerImageModal, Speed.slow);
		return (isDisplayed(headerImageModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on save button in header image modal
	 * @creation date 15/02/2018
	 */
	@Override
	public void clickOnSaveButtonInHeaderImageModal()
	{
		element = findVisibleElement(saveButtonInHeaderImageModal, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in header image modal
	 * @creation date 15/02/2018
	 */
	@Override
	public void clickOnCancelButtonInHeaderImageModal()
	{
		element = findVisibleElement(cancelButtonInHeaderImageModal, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return the availability of blog edit page
	 * @creation date 15/02/2018
	 */
	@Override
	public boolean verifyBlogEditPage()
	{
		findVisibleElement(blogEditPage, Speed.slow);
		return (isDisplayed(blogEditPage, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param blogName to be clicked
	 * @creation date 16/02/2018
	 */
	@Override
	public void clickOnBlogInBlogList(String blogName)
	{
		element = findVisibleElement(By.xpath("(//*[@id='blogListBody']//*[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param blogName should be on top
	 * @return The blog recently added is on top
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean verifyTopRecentBlog(String blogName)
	{
		return (findVisibleElement(recentBlog, Speed.slow).getText().trim().equals(blogName.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param
	 * @return blogName availability
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean verifyBlogInBlogList(String blogName)
	{
		return (isDisplayed(By.xpath("(//*[@id='blogListBody']//*[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param blogName
	 * @return
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean VerifyBlogTitleInDetailsSection(String blogName)
	{
		findVisibleElement(By.xpath("//*[@id='blogModuleMiddlePanelID']//h1[normalize-space(text())='" + blogName.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='blogModuleMiddlePanelID']//h1[normalize-space(text())='" + blogName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param category
	 * @return
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean VerifyBlogCategoryInDetailsSection(String category)
	{
		findVisibleElement(By.xpath("//*[@id='blogModuleMiddlePanelID']//*[@class='categoryContainer editBlock-content']/a[normalize-space(text())='" + category.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='blogModuleMiddlePanelID']//*[@class='categoryContainer editBlock-content']/a[normalize-space(text())='" + category.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param tagName
	 * @return
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean VerifyBlogTagInDetailsSection(String tagName)
	{
		element = findVisibleElement(By.xpath("//*[@id='blogTagID']//a[normalize-space(text())='" + tagName.trim().toLowerCase() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='blogTagID']//a[normalize-space(text())='" + tagName.trim().toLowerCase() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param fileName
	 * @return
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean VerifyAttachedFileInDetailsSection(String fileName)
	{
		return (isDisplayed(By.xpath("//*[contains(@id,'docid') and @title='" + fileName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the image availability
	 * @creation date 16/02/2018
	 */
	@Override
	public boolean verifyHeaderImageAvailabilityInDetailsSection()
	{
		return (isDisplayed(headerImageInDetailsSection, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on add post button
	 * @creation date 16/02/2018
	 */
	@Override
	public void clickOnAddPostButton()
	{
		element = findVisibleElement(addPostButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To select an option in publish date drop down
	 * @cretaion date 16/02/2018
	 */
	@Override
	public void selectOptionInPublishDateDropDown(String option)
	{
		Select select = new Select(findVisibleElement(publishDateDropDown, Speed.slow));
		select.selectByVisibleText(option.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param date
	 *        To send date in date text box of publish date
	 * @cretaion date 16/02/2018
	 */
	@Override
	public void sendDateInPublishDateDateTextBox(String date)
	{
		element = findVisibleElement(publishDateDateTextBox, Speed.slow);
		element.click();
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(date);
	}

	/**
	 * @author vivek.mishra
	 * @param date
	 *        To send time in time text box of publish date
	 * @cretaion date 16/02/2018
	 */
	@Override
	public void sendTimeInPublishDateTimeTextBox(String time)
	{
		element = findVisibleElement(publishDateTimeTextBox, Speed.slow);
		((JavascriptExecutor) driver).executeScript("arguments[0].value ='" + time + "';", element);
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To click on option in left pannel
	 * @creation date 19/02/2018
	 */
	@Override
	public void clickOnOptionInLeftPannel(String option)
	{
		element = findVisibleElement(By.xpath("//*[@id='blogLeftPaneContainerDiv']//*[normalize-space(text())='" + getUserData(option.trim()) + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return the blog availability
	 * @creation date 05/03/2018
	 */
	@Override
	public boolean verifyBlogInScheduledPosts(String blogName)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the availability of scheduled posts page
	 * @creation date 05/03/2018
	 */
	@Override
	public boolean verifyScheduledPostsPage()
	{
		findVisibleElement(blogModuleMiddlePannelInScheduledPosts, Speed.slow);
		return (isDisplayed(blogModuleMiddlePannelInScheduledPosts, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on watch icon in edit section
	 * @creation date 05/03/2018
	 */
	@Override
	public void clickOnWatchIconInEditSection()
	{
		element = findVisibleElement(watchIconInEditSection, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return the time picker modal availability
	 * @creation date 05/03/2018
	 */
	@Override
	public boolean verifyTimePickerModal()
	{
		findVisibleElement(timePickerModal, Speed.slow);
		return (isDisplayed(timePickerModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on minute increment button
	 * @creation date 05/03/2018
	 */
	@Override
	public void clickOnMinuteIncrementButton()
	{
		element = findVisibleElement(minuteIncrementButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param blogName
	 * @creation date 06/03/2018
	 */
	@Override
	public void clickOnMoreActionInBlogList(String blogName)
	{
		element = findVisibleElement(By.xpath("(//*[@id='blogListBody']//*[normalize-space(text())='" + blogName.trim() + "']//following::*[@data-original-title='More actions'])[1]"));
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param blogName
	 * @param Option
	 * @creation date 06/03/2018
	 */
	@Override
	public void clickOnOptionInMoreActionInBlogList(String blogName, String option)
	{
		clickOnMoreActionInBlogList(blogName);
		element = findVisibleElement(By.xpath("(//*[@id='blogListBody']//*[normalize-space(text())='" + blogName.trim() + "']//following::*[normalize-space(text())='" + getUserData(option.trim()) + "'])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To edit the blog content
	 * @creation date 07/03/2018
	 */
	@Override
	public void appendBlogContent(String blogContent)
	{
		verifyCkEditor();
		WebElement addBlogContentEle = findVisibleElement(addBlogContent, Speed.slow);
		addBlogContentEle.sendKeys(blogContent);
	}

	/**
	 * @author vivek.mishra
	 *         To edit the blog tag
	 * @creation date 07/03/2018
	 */
	@Override
	public void editBlogTag(String oldTag, String newTag)
	{
		element = findVisibleElement(By.xpath("//*[@id='blogContentAndTagContainerID']//*[@class='token']//*[normalize-space(text())='" + oldTag.trim().toLowerCase() + "']//preceding-sibling::a"), Speed.slow);
		element.click();
		element = findVisibleElement(addEditBlogTagTextBox, Speed.slow);
		element.sendKeys(newTag.trim());
		element.sendKeys(Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 *         To edit the category
	 * @creation date 07/03/2018
	 */
	@Override
	public void editCategory(String oldCategory, String newCategory)
	{
		clickOnCategories();
		element = findVisibleElement(By.xpath(".//label[@title='" + oldCategory.trim() + "']//input"), Speed.slow);
		if (element.isSelected())
		{
			element.click();
		}
		element = findVisibleElement(By.xpath(".//label[@title='" + newCategory.trim() + "']//input"), Speed.slow);
		if (!element.isSelected())
		{
			element.click();
		}
		clickOnCategories();
	}

	/**
	 * @author vivek.mishra
	 *         To edit attached file with browse button in add Blog attachments tab
	 * @creation date 07/03/2018
	 */
	@Override
	public void editAttachedFileInAddBlobAttachmentTab(String oldFile, String newFile)
	{
		element = findVisibleElement(By.xpath("//*[@class='postUpload']//*[@class='TruncateTxt linkblack' and normalize-space(text())='" + oldFile.trim() + "']//preceding-sibling::a"), Speed.slow);
		element.click();
		String path = TestBaseSetup.currentDir + "\\testData\\" + newFile.trim();
		findPresentElement(attachmentBrowseButton, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 * @param content
	 * @return
	 * 		To verify the content of the blog
	 * @creation date 07/03/2018
	 */
	@Override
	public boolean verifyBlogContent(String content)
	{
		findVisibleElement(By.xpath("//*[@id='blogContentID']//p[normalize-space(text())='" + content.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='blogContentID']//p[normalize-space(text())='" + content.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To text value available in title text box in edit page
	 * @creation date 08/03/2018
	 */
	@Override
	public String getTextFromTitleTextBox()
	{
		String text = findVisibleElement(addEditBlogTitleTextBox, Speed.slow).getAttribute("value").trim();
		return (text);
	}

	/**
	 * @author vivek.mishra
	 *         To click on more action button in details section
	 * @creation date 08/03/2018
	 */
	@Override
	public void clickOnMoreActionInDetailSection()
	{
		element = findVisibleElement(moreActionButtonInDetailsSection, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param option
	 *        To click on an option in more action in details section
	 * @creation date 08/03/2018
	 */
	@Override
	public void clickOnOptionInMoreActionInDetailsSection(String option)
	{
		clickOnMoreActionInDetailSection();
		element = findVisibleElement(By.xpath("//*[@class='dropdown inlineBlock margLeft10 open']//*[normalize-space(text())='" + option.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author badal.gandhi
	 * @param option
	 *        To verify if delete post message is present
	 * @creation date 08/03/2018
	 */
	@Override
	public boolean verifyDeletePostMessage()
	{
		return findVisibleElement(deletePostModelMessage, Speed.slow).getText().equalsIgnoreCase(deletePostMessage);
	}

	/**
	 * @author badal.gandhi
	 * @param option
	 *        Cancel in delete model
	 * @creation date 08/03/2018
	 */
	@Override
	public void cancelDeleteBlogProcess()
	{
		verifyDeletePostMessage();
		findVisibleElement(cancelDeletePost).click();
		findPresentElement(deleteModelDisappeared);
	}

	/**
	 * @author badal.gandhi
	 * @param option
	 *        To click on delete button in delete model
	 * @creation date 08/03/2018
	 */
	@Override
	public void deleteBlog()
	{
		verifyDeletePostMessage();
		findVisibleElement(deletePost).click();
		findPresentElement(deleteModelDisappeared);
	}

	/**
	 * @author badal.gandhi
	 * @param option
	 *        Verify if post is deleted
	 * @creation date 08/03/2018
	 */
	@Override
	public boolean isBlogDeleted(String blogName)
	{
		return isDisplayed(By.xpath("//*[@id='blogModuleMiddlePanelID']//h1[normalize-space(text())='" + blogName.trim() + "']"), Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 08/03/2018
	 */
	@Override
	public boolean verifyDeleteButtonInMyDrafts()
	{
		findVisibleElement(deleteButtonInMyDrafts, Speed.slow);
		return (isDisplayed(deleteButtonInMyDrafts, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param blogName to be verified
	 * @return
	 * @creation date 08/03/2018
	 */
	@Override
	public boolean verifyBlogInMyDrafts(String blogName)
	{
		return (isDisplayed(By.xpath("(//*[@id='blogMyDraftBody']//*[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 08/03/2018
	 */
	@Override
	public boolean verifyMoreAction()
	{
		return (isDisplayed(moreActionButtonInDetailsSection, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To wait for auto save notification on footer
	 * @creation date 09/03/2018
	 */
	@Override
	public void waitForAutoSaveNotification()
	{
		findVisibleElement(By.xpath("//*[@id='addEditBlogAutoSaveMessageID' and contains(text(),'Auto-saved at')]"), Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param modalName
	 * @return
	 * 		To verify the modal
	 * @creation date 09/03/2018
	 */
	@Override
	public boolean verifyModal(String modalName)
	{
		return (isDisplayed(By.xpath("//*[@class='modal-title' and normalize-space()='" + modalName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @creation date 09/03/2018
	 */
	@Override
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return isDisplayed(modal, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param message
	 * @return
	 * @creation date 09/03/2018
	 */
	@Override
	public boolean verifyModalMessage(String message)
	{
		return (findVisibleElement(modalMessage, Speed.slow).getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param message to be verified
	 * @return the match status
	 * @creation date 12/03/2018
	 */
	@Override
	public boolean verifyResumeDiscardYelloStripMessage(String message)
	{
		element = findVisibleElement(resumeDiscardYellowStrip, Speed.slow);
		return (element.getText().trim().equals(message.trim()));
	}

	/**
	 * @author vivek.mishra
	 *         To click on resume button in yellow strip
	 * @creation date 12/03/2018
	 */
	@Override
	public void clickOnResumeInYellowStrip()
	{
		element = findVisibleElement(reumeButtonInYelloStrip, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on discard button in yellow strip
	 * @creation date 12/03/2018
	 */
	@Override
	public void clickOnDiscardInYellowStrip()
	{
		element = findVisibleElement(discardButtonInYelloStrip, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param blogName to be deleted
	 * @creation date 12/03/2018
	 */
	@Override
	public void clickOnDeleteIconOfBlogInMyDrafts(String blogName)
	{
		element = findVisibleElement(By.xpath("(//*[@id='blogMyDraftBody']//*[normalize-space(text())='" + blogName.trim() + "']/../..//following-sibling::*//*[@data-original-title='Delete'])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName
	 * @creation date 12/03/2018
	 */
	@Override
	public boolean verifyModalButton(String buttonName)
	{
		findVisibleElement(By.xpath("//*[@class='modal-footer']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@class='modal-footer']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName to be clicked
	 * @creation date 12/03/2018
	 */
	@Override
	public void clickOnModalButton(String buttonName)
	{
		element = findVisibleElement(By.xpath("//*[@class='modal-footer']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 12/03/2018
	 */
	@Override
	public boolean verifySearchTextBoxIsEmpty()
	{
		element = findVisibleElement(searchTextBox, Speed.slow);
		return (element.getText().isEmpty());
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @creation date 12/03/2018
	 */
	@Override
	public void sendTextInSearchTextBox(String text)
	{
		element = findVisibleElement(searchTextBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim() + Keys.ENTER);
	}

	/**
	 * @author vivek.mishra
	 *         To verify the blog list is present
	 * @creation date 12/03/2018
	 */
	@Override
	public boolean verifyBlogListInMyDrafts()
	{
		findVisibleElement(blogListInMyDrafts, Speed.slow);
		return (isDisplayed(blogListInMyDrafts, Speed.slow));
	}

	/**
	 * Verifies if favorite star is selected or not.
	 *
	 * @return true
	 *         if successful.
	 * @author badal.gandhi
	 */
	@Override
	public boolean verifyFavouriteIconIsSelected(String blogName)
	{
		// System.out.println(favouriteStarSelected);
		By favouriteTask = By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "']/..//following-sibling::*//*[@data-original-title='" + BlogLabels.BLOG_REMOVEFROMFAVOURITES + "'])[1]");
		return isDisplayed(favouriteTask, Speed.slow);
	}

	/**
	 * Click on add to favorite Star Icon
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void addBlogToFavourites(String blogName)
	{
		if (!verifyFavouriteIconIsSelected(blogName))
		{
			By favouriteStar = By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "']/..//following-sibling::*//*[@data-original-title='" + BlogLabels.BLOG_ADDTOFAVOURITES + "'])[1]");
			findVisibleElement(favouriteStar).click();
			By favouriteBlog = By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "']/..//following-sibling::*//*[@data-original-title='" + BlogLabels.BLOG_REMOVEFROMFAVOURITES + "'])[1]");
			findVisibleElement(favouriteBlog);
		}
		else
		{
			System.out.println("Favourite Star is already selected.");
		}
	}

	/**
	 * Click on remove from Star Icon
	 *
	 * @author badal.gandhi
	 */
	@Override
	public void removeBlogFromFavourites(String blogName)
	{
		if (verifyFavouriteIconIsSelected(blogName))
		{
			By favouriteBlog = By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "']/..//following-sibling::*//*[@data-original-title='" + BlogLabels.BLOG_REMOVEFROMFAVOURITES + "'])[1]");
			findVisibleElement(favouriteBlog).click();
			By favouriteStar = By.xpath("(//*[normalize-space(text())='" + blogName.trim() + "']/..//following-sibling::*//*[@data-original-title='" + BlogLabels.BLOG_ADDTOFAVOURITES + "'])[1]");
			findVisibleElement(favouriteStar, Speed.slow);
		}
		else
		{
			System.out.println("No favourites star available.");
		}
	}

	/**
	 * @author badal.gandhi
	 *         To Add blog data in blog module by passing complete data in map
	 * @modified on 13/03/2018
	 */
	@Override
	public void addBlogData(Map<String, String> blogData)
	{
		clickOnAddPlusSign();
		for (Map.Entry<String, String> m : blogData.entrySet())
		{
			String lowerCase = m.getKey().toLowerCase();
			if (BlogLabels.BLOG_ADDBLOG_TITLE.toLowerCase().equals(lowerCase))
			{
				sendTextInBlogTitle(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_TAGS.toLowerCase().equals(lowerCase))
			{
				addBlogTag(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_NOTIFICATION.toLowerCase().equals(lowerCase))
			{
				setNotifications(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_STATUS.toLowerCase().equals(lowerCase))
			{
				setStatus(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_CATEGORY.toLowerCase().equals(lowerCase))
			{
				selectCategory(m.getValue());
			}
			else if (BlogLabels.BLOG_ADDBLOG_CONTENT.toLowerCase().equals(lowerCase))
			{
				addBlogContent(m.getValue());
			}
			else if (BlogLabels.BLOG_ATTACHMENTS.toLowerCase().equals(lowerCase))
			{
				clickOnTabsInBlogModule(BlogLabels.BLOG_ATTACHMENTS);
				attachFileInAddBlobAttachmentTab(m.getValue());
			}
			else if (BlogLabels.BLOG_SETTINGS.toLowerCase().equals(lowerCase))
			{
				clickOnTabsInBlogModule(BlogLabels.BLOG_SETTINGS);
				if (m.getValue() != null || m.getValue() != " ")
				{
					AttachFileInAddBlobSettingsTabHeaderImageModal(m.getValue());
				}

			}
		}
		verifyBlogEditPage();
	}

	/**
	 * @author badal.gandhi
	 *         To verify archive label in left panel
	 * @created on 13/03/2018
	 */
	@Override
	public boolean verifyArchiveLabel()
	{
		findVisibleElement(By.xpath(leftPanel));
		return isDisplayed(archiveLabel, Speed.slow);
	}

	/**
	 * @author badal.gandhi
	 *         To click on archive month in left panel
	 * @created on 13/03/2018
	 */
	@Override
	public void clickOnArchiveMonth(String month)
	{
		By monthXpath = By.xpath("//*[@id='blog_module_leftPanelPage']//*[normalize-space(text())='" + month.trim() + "']");
		findVisibleElement(monthXpath).click();
		findVisibleElement(By.xpath("//*[@id='blogModuleMiddlePanelID']//*[normalize-space(text())='" + month.trim() + "']"), Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the ck editor is availiable or not
	 * @created on 13/03/2018
	 */
	@Override
	public boolean verifyCkEditor()
	{
		findVisibleElement(ckEditor, Speed.slow);
		return (isDisplayed(ckEditor, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify attachments tab is opened
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyAttachmentsTabContainer()
	{
		findVisibleElement(attachmentsTabContainer, Speed.slow);
		return (isDisplayed(attachmentsTabContainer, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify settings tab is opened
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifySettingsTabContainer()
	{
		findVisibleElement(settingsTabContainer, Speed.slow);
		return (isDisplayed(settingsTabContainer, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on like button in blog detail section
	 * @creation date 13/03/2018
	 */
	@Override
	public void clickOnLikeUnlikeBlogButton()
	{
		element = findVisibleElement(likeBlogButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on comment button in blog detail section
	 * @creation date 13/03/2018
	 */
	@Override
	public void clickOnCommentBlogButton()
	{
		element = findVisibleElement(commentBlogButton, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel button in blog detail section
	 * @creation date 13/03/2018
	 */
	@Override
	public void clickOnCancelCommentButton()
	{
		element = findVisibleElement(cancelButtonInDetailSection, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on post button in blog detail section
	 * @creation date 13/03/2018
	 */
	@Override
	public void clickOnPostCommentButton()
	{
		element = findVisibleElement(postButtonInDetailSection, Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param text to be sent
	 * @creation date 13/03/2018
	 */
	@Override
	public void sendTextInBlogCommentBox(String text)
	{
		element = findVisibleElement(blogCommentBox, Speed.slow);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the comment icon availability
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyCommentIconInDetailSection()
	{
		findVisibleElement(commentIcon, Speed.slow);
		return (isDisplayed(commentIcon, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * 		@return]
	 *         To get count of comments
	 * @creation date 13/03/2018
	 */
	@Override
	public int getCommentCount()
	{
		element = findVisibleElement(commentCount, Speed.slow);
		return (Integer.parseInt(element.getText()));
	}

	/**
	 * @author vivek.mishra
	 * 		@return]
	 *         To get count of like
	 * @creation date 13/03/2018
	 */
	@Override
	public int getBlogLikeCount()
	{
		element = findVisibleElement(likeCount, Speed.slow);
		return (Integer.parseInt(element.getText()));
	}

	/**
	 * @author vivek.mishra
	 * @param status
	 * @return
	 * 		To verify the blog current status like/unlike
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyBlogLikeUnlikeStatus(String status)
	{
		element = findVisibleElement(likeBlogButton, Speed.slow);
		return (element.getText().trim().equals(getUserData(status.trim())));
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @return
	 * 		To verify the comment in details section
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyComment(String comment)
	{
		return (isDisplayed(By.xpath("(//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @param option
	 * @creation date 13/03/2018
	 */
	@Override
	public void clickOnCommentOptionInDetailsSection(String comment, String option)
	{
		element = findVisibleElement(By.xpath("(//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../..//following-sibling::*//*[normalize-space(text())='" + getUserData(option.trim()) + "'])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @param option
	 * @return
	 * 		To verifyThe comment option
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyCommentOptionInDetailsSection(String comment, String option)
	{
		verifyCommentBlock(comment);
		return (isDisplayed(By.xpath("(//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../..//following-sibling::*//*[normalize-space(text())='" + getUserData(option.trim()) + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @return
	 * 		To verify the comment block
	 * @creation date 13/03/2018
	 */
	@Override
	public boolean verifyCommentBlock(String comment)
	{
		findVisibleElement(By.xpath("(//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../../..)[1]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../../..)[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param comment
	 * @return
	 * 		To get comment like count
	 * @creation date 13/03/2018
	 */
	@Override
	public int getCommentLikeCount(String comment)
	{
		element = findVisibleElement(By.xpath("//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../..//following-sibling::*//span[not(@class='linkSep')]"), Speed.slow);
		if (element.getText().isEmpty() || element.getText() == null)
		{
			return 0;
		}
		else
		{
			element = findVisibleElement(By.xpath("//*[@class='commentBlock']//*[@class='ckContentArea']/p[normalize-space(text())='" + comment.trim() + "']/../..//following-sibling::*//span[not(@class='linkSep')]/a"), Speed.slow);
			return (Integer.parseInt(element.getText().trim()));
		}
	}

	/**
	 * @author vivek.mishra
	 * @param text to be appended
	 * @creation date 14/03/2018
	 */
	@Override
	public void appendTextInBlogCommentBox(String text)
	{
		element = findVisibleElement(blogCommentBox, Speed.slow);
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @return the last reply data
	 * @creation date 14/03/2018
	 */
	@Override
	public String getLastReplyOfCommentInDetailsSection()
	{
		element = findVisibleElement(lastReplyOfComment, Speed.slow);
		return (element.getText().trim());
	}

	/**
	 * @author vivek.mishra
	 * @return the rendered text
	 * @creation date 14/03/2018
	 */
	@Override
	public String getCommentBoxRenderedText()
	{
		element = findVisibleElement(commentBoxRenderedText, Speed.slow);
		return (element.getText().trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the blog like message
	 * @creation date 14/03/2018
	 */
	@Override
	public String getBlogLikeMessage()
	{
		element = findVisibleElement(contentLike, Speed.slow);
		return (element.getText().trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the print and export modal
	 * @created on 15/03/2018
	 */
	@Override
	public boolean verifyPrintAndExportModal()
	{
		findVisibleElement(printAndExportModal, Speed.slow);
		return (isDisplayed(printAndExportModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param blogName to be verified
	 * @return
	 * @created on 15/03/2018
	 */
	@Override
	public boolean verifyBlogInPrintPage(String blogName)
	{
		findVisibleElement(By.xpath("(//*[@id='printBodySection']//h1[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[@id='printBodySection']//h1[normalize-space(text())='" + blogName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param comment to be vrified
	 * @return
	 * @created on 15/03/2018
	 */
	@Override
	public boolean verifyBlogCommentInPrintPage(String comment)
	{
		findVisibleElement(By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + comment.trim() + "'])[1]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[@id='commentMeta']//*[normalize-space(text())='" + comment.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on include comments check box in export to pdf modal
	 * @created on 15/03/2018
	 */
	@Override
	public void selectIncludeCommentsCheckBox()
	{
		WebElement includeCmtCheckBox = findVisibleElement(includeCommentsCheckBox, Speed.slow);
		if (!includeCmtCheckBox.isSelected())
		{
			includeCmtCheckBox.click();
		}
	}

	/**
	 * @author vivek.mishra
	 *         To wait untill file is getting downloaded
	 * @creation date 26/03/2018
	 */
	@Override
	public void waitForFileGettingDownloaded()
	{
		if (isDisplayed(downloading, Speed.slow))
		{
			while (isDisplayed(downloading))
			{
				;
			}
		}
	}

	/**
	 * Get blog metadata form middle panel
	 * 
	 * @param blogName
	 *        expected blog name
	 * @return metadata
	 * @author dheeraj.rajput
	 * @Created 05 June 2018
	 * @Updated
	 */
	public String getBlogMetaFromDetailsMiddlePanel(String blogName)
	{
		By blogMeta = By.xpath("//*[normalize-space(text())='" + blogName + "']/../following-sibling::*/*[contains(@class,'meta')]");
		return findVisibleElement(blogMeta).getText().trim();
	}

}
