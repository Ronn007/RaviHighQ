package com.highq.pageobjects.pages;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.ActivityLabels;
import com.highq.labels.collaborate.DashboardLabels;
import com.highq.pageobjects.base.ActivityPage;
import com.highq.pageobjects.base.BlogPage;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.base.EventsPage;
import com.highq.pageobjects.base.SearchContentPage;
import com.highq.pageobjects.base.TasksPage;

/**
 * @author vivek.mishra
 */

public class ActivityWeb extends BannerPageWeb implements ActivityPage
{
	Actions build;

	public ActivityWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By recentActivityHeader = By.xpath("//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RECENTACTIVITYHEADER + "']");
	/** Recent Activity header links */
	By recentActivity_All = By.xpath("//*[contains(@class,'nav-pills moremenu')]//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RECENTACTIVITY_ALL + "']");
	By recentActivity_Posts = By.xpath("//*[contains(@class,'nav-pills moremenu')]//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RECENTACTIVITY_POSTS + "']");
	By recentActivity_Activity = By.xpath("//*[contains(@class,'nav-pills moremenu')]//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RECENTACTIVITY_ACTIVITY + "']");
	By recentActivity_Filter = By.xpath("//*[contains(@class,'nav-pills moremenu')]//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RECENTACTIVITY_FILTER + "']");

	/** Recent Activity Panel 1 */
	By recent_Panel1 = By.xpath("(.//*[contains(@id,'recentActivity')]//div[@class='panel-body'])[1]");
	By recent_Panel1_grayMeta = By.xpath("(.//*[contains(@id,'recentActivity')]//div[@class='panel-body'])[1]/*[1]//*[@class='greyMeta']");
	By recent_Panel1_graymetaSiteLinks = By.xpath("(.//*[contains(@id,'recentActivity')]//div[@class='panel-body'])[1]/*[1]//*[@class='greyMeta']/a");
	By recent_Panel1_postContentLink = By.xpath("(.//*[contains(@id,'recentActivity')]//div[@class='panel-body'])[1]/*[@class='postContent']//a");

	/** Blog space in activity module */
	By blogAreaInActivity = By.className("hidden-xs hidden-sm col-md-3 col-lg-3 dashLeft");

	/** Recent Activity space in activity module */
	By recentActivityAreaInActivity = By.xpath("//*[contains(@class,'dashMiddle')]");

	/** Task space in activity module */
	By taskAreaInActivity = By.id("siteActivity_tasks");

	/** Event space in activity module */
	By eventAreaInActivity = By.id("siteActivity_eventList");

	/** Activity Label in activity module Under recent activity */
	By activityLabel = By.id("siteActivityMiddlePane_systemUpdateFilter");

	/** Content Type in Filter drop down in activity module Under recent activity */
	By contentTypeInFilterDropDown = By.className("accordArrow icon-chevron-down");

	/** People in Filter drop down in activity module Under recent activity */
	By peopleInFilterDropDown = By.xpath("//*[@class='collapsed' and normalize-space()='" + ActivityLabels.ACTIVITY_RECENTACTIVITY_FILTER_PEOPLE + "']/span");

	/** People in Filter drop down in activity module Under recent activity */
	By allOfTheAutoCheckBoxInPeopleInFilterDropDown = By.name("activityFilter_peopleFilter");

	/** People search text box in Filter drop down in activity module Under recent activity */
	By searchTextBoxInPeopleInFilterDropDown = By.id("dashboardActivity_UserSearchInput");

	/** Clear filters label in Filter drop down in activity module Under recent activity */
	By clearFiltersLabelInPeopleInFilterDropDown = By.xpath("//*[@id='siteActivityMiddlePane_filters']//*[@class='delete']");

	/** All tasks label in Under Tasks */
	By allTasksLabel = By.xpath("//*[@id='siteActivity_allTaskList']//*[normalize-space()='" + ActivityLabels.ACTIVITY_ALLTASKS + "']");

	/** Assigned To me label in Under Tasks */
	By assignedToMeLabel = By.id("siteActivity_assignToMeTaskList");

	/** Author name under recent activity */
	By authorName = By.id("siteActivity_assignToMeTaskList");

	/** Author Details modal after clicking on Author name under recent activity */
	By userInfoModal = By.className("userinfoDropdown");

	/** Attachment link of comment */
	By attachmentLinkOfComment = By.xpath("(//*[contains(@id,'comment_uploadAttachment')])[last()]");

	/** attachment modal */
	By attachmentModal = By.id("ATTACHMENT_MODAL");

	/** Site drop down in add attachment modal */
	By siteDropDownInAddAttachmentModal = By.className("btn dropdown-toggle errorElement");

	/** Search box in site drop down */
	By serchBoxInSiteDropDownInAddAttachmentModal = By.id("attachmentModal_dropDownSiteList_recentTab_siteSearchInput");

	By sitesCollapsedArrowInFilterDropDown = By.xpath("//*[@class='panel']//*[@class='accordArrow icon-chevron-right']");

	By sitesExpandedArrowInFilterDropDown = By.xpath("//*[@class='panel']//*[@class='accordArrow icon-chevron-down']");

	By filterDropDown = By.xpath("//*[@class='dropdown open']//*[@id='dashboardMiddle_filterDropDown']");

	By siteListInFilterDropDown = By.id("dashboardMiddleSiteList_checkboxDiv");

	By userListInFilterDropDown = By.id("dashboardMiddleUserFilter_checkboxDiv");

	By activityTabInRecentActivity = By.id("dashboard_recentActivity");

	By microblogTextBoxDisabled = By.id("dashboardMiddle_microblogCkDiv");

	By microBlogTextBoxEnabled = By.xpath("//*[@id='dashboardMiddle_microblogCkDiv' and contains(@class,'ckheightExpand')]");

	By microblogCancelButton = By.xpath("//*[@id='dashboardMiddle_microblog_PostBtn']/preceding-sibling::button");

	By microblogPostButtonEnabled = By.xpath("//*[@id='dashboardMiddle_microblog_PostBtn' and not(contains(@class,'disabled'))]");

	By shareWithTextBox = By.id("dashboardMiddle_microblogShareUpdate-tokenfield");

	By shareReceipintHover = By.xpath("(//*[@class='typeahead_primary'])[last()]");

	By siteActivityMiddlePannel = By.id("siteActivityPage_middlePane_content");

	By insertLinkButton = By.xpath("//*[@data-original-title='Link']");

	By attachmentButton = By.xpath("//*[@class='icon-paper-clip postIcon uploadBtn tooltipShow pull-left' or contains(@onclick,'addMicroblogAttachment()')]");// By.className("icon-paper-clip postIcon uploadBtn tooltipShow pull-left");

	By textFormatingButton = By.className("icon-text-formating postIcon uploadBtn tooltipShow");

	By modal = By.xpath("(//*[contains(@class,'in')])[last()]");

	By recentTabInInsertLinkModal = By.xpath("//*[@id='insertLinkModal_recentActivityTabBody' and @class='mobileModalScroll']");

	By browseTabInInsertLinkModal = By.xpath("//*[@id='insertLinkModal_browseLinkBody' and @class='mobileModalScroll']");

	By externalTabInInsertLinkModal = By.xpath("//*[@id='insertLinkModal_externalLinkBody' and @class='mobileModalScroll']");

	By uploadTabInInsertLinkModal = By.xpath("//*[@id='insertLinkModal_uploadLinkBody' and @class='mobileModalScroll']");

	By searchTabInInsertLinkModal = By.xpath("//*[@id='insertLinkModal_searchLinkBody' and @class='mobileModalScroll']");

	By externalURLTextBox = By.id("externalURL");

	By externalURLShortCheckBox = By.id("externalIsShort");

	By browseButtonInInsertLinkModal = By.id("insertLinkModal_uploadTab_fileInputTypeID");

	By modalMessage = By.xpath("//*[contains(@class,'in')]//*[@class='modal-content']//*[@id='collaborateCustomModalMessage']");

	By postCommentTextBox = By.xpath("//*[@class='commentBlock dropContainer removeBorder ']//*[contains(@class,'cke_show_border')]");

	By commentPostButtonEnabled = By.xpath("//*[contains(@id,'postBtn_addCommentField') and not(contains(@class,'disabled'))]");

	By commentPostButtonDisabled = By.xpath("(//*[contains(@id,'comment_commentFooter')]//button[@class='btn btn-default margLeft5 disabled'])[1]");

	By commentCancelButton = By.xpath("(//*[contains(@id,'comment_commentFooter')]//button[@class='btn btn-cancel'])[1]");

	By uploadTabOpened = By.xpath("//*[@id='attachmentModal_uploadTabBody' and not(@class='mobileModalScroll hide')]");

	By browseButtonInAddAttachmentModal = By.id("attachmentModal_uploadTab_fileInputTypeID");

	By attachButtonInAddAttachmentModal = By.id("ATTACHMENT_MODAL_insertButton");

	By firstLink = By.xpath("(//*[contains(@href,'https://highqhubuat')])[1]");

	By re_post = By.xpath("(//*[@class='modal-content'])[last()]//*[normalize-space(text())='Re-post']");

	By microblogMessage = By.xpath(".//*[@id='microblogModal_microblogCkDiv']//p");

	By share_Repost = By.xpath("(//*[@class='modal-content']//*[normalize-space(text())='" + DashboardLabels.SHARE_RE_POST + "'])[2]");

	By repostMessage = By.id("microblogModal_microblogCkDiv");

	By shareWithSite = By.id("microblogModal_microblogShareUpdate-tokenfield");

	By insertLink = By.xpath("(//*[@data-original-title='" + DashboardLabels.UI_TEXT_LINK + "'])[last()]");

	By attachmentLink = By.xpath("(//*[contains(@id,'microblogDetailID')]//*[@data-original-title='" + DashboardLabels.UI_TEXT_ATTACHMENT + "'])[last()]");

	By textFormatLink = By.xpath("(//*[@data-original-title='" + DashboardLabels.UI_TEXT_FORMATTING + "'])[last()]");

	By defaultShare = By.xpath(".//*[@id='microblogModal_dropContainer']//span[@class='token-label']");
	
	/* Right Panel - Task */
	By rightPanel_TaskList = By.id("siteActivityPage_rightPane_taskList");
	By rightPanel_SeeAllTasksLink = By.xpath(".//*[@id='siteActivity_taskList']//*[normalize-space(text())='" + ActivityLabels.ACTIVITY_RIGHTPANEL_SEEALLTASKS + "']");

	/* Task Module */
	By taskHome = By.id("taskHomeLeftPaneID");

	By eventModule_RightPanel = By.id("eventRightPannel");

	/* Left Panel - tag cloud */
	By tagCloud = By.xpath("//*[@class='TagClouds']");

	/* User Card */
	By userInfoCard = By.xpath("//*[@class='userinfoDropdown']");
	By userCard_userName = By.xpath("//*[@class='userinfoDropdown']//*[@class='usertitle']");
	By userCard_email = By.xpath("//*[@class='userinfoDropdown']//*[contains(@onclick,'mailTo')]");
	By userCard_closeIcon = By.xpath("//*[@class='userinfoDropdown']//*[@class='close']");

	String parentWindow;
	String childWindow;
	String upload = "Upload";

	String displayBlock = "//*[contains(@class,'in')]//*[@class='modal-content']";// "//*[contains(@style,'display: block;')]";
	String modalBodyTopHeader = displayBlock + "//*[contains(@class,'modalBodyTop')]";

	By share_Link_UserShortURLCheckbox = By.id("chkshortenURLID");
	By share_Link_LinkInput = By.id("file_module_copyLink_txt");

	String hubURLPrefix = "https://highqhubuat.in/";

	By closeLikeModal = By.id("CONTENT_LIKE_MODAL_MAIN_CLOSE_BUTTON");

	By likeCommentModal = By.id("CONTENT_LIKE_MODAL_TITLE");

	String filter_rightChevronIcons = "(//*[@id='siteActivityMiddlePane_filters']//*[contains(@class,'icon-chevron-right')])";
	String filter_visibleCheckboxes = "(//*[contains(@class,'collapse in')]//*[@type='checkbox'])";
	String filter_allCheckboxes = ".//*[@id='siteActivityMiddlePane_filters']//*[@type='checkbox']";
	String filter_allInput = ".//*[@id='siteActivityMiddlePane_filters']//*[@type='text']";
	By middlePanel_firstUserCard = By.xpath("(//*[@id='siteActivityPage_middlePane']//*[normalize-space(@class)='usercardLink'])[1]");
	String middlePanel_allPostMetaUserCards = "//*[@id='siteActivityPage_middlePane']//*[normalize-space(@class)='postMeta']//*[normalize-space(@class)='usercardLink']";
	By contentLikeModal = By.id("CONTENT_LIKE_MODAL");
	By peopleWhoLikeThis_userList = By.xpath(".//*[@id='CONTENT_LIKE_MODAL']//*[normalize-space(@class)='usercardLink']");
	By visibleActivities = By.xpath(".//*[contains(@id,'recentActivity')]//*[contains(@class,'postSection')]");
	By middlePanel_loadMoreButton = By.id("siteActivityMiddlePane_loadMoreButton");
	By activity_leftPanel = By.id("siteActivtyPage_leftPane");
	By blogModule_middlePanel = By.id("blogModuleMiddlePanelID");

	/**
	 * Click on Posts tab in Activity module
	 * 
	 * @author dheeraj.rajput
	 */
	public void gotoPosts()
	{
		findVisibleElement(recentActivity_Posts).click();
	}

	/**
	 * Verify gray meta site name of first activity
	 * 
	 * @param siteName
	 *        to verify
	 * @author dheeraj.rajput
	 */
	public boolean verifyGrayMetaSiteName(String siteName)
	{
		findVisibleElement(recent_Panel1);
		By site = By.xpath("(.//*[contains(@id,'recentActivity')]//div[@class='panel-body'])[1]//*[@class='greyMeta']/*[normalize-space(text())='" + siteName.trim() + "']");
		return isDisplayed(site);
	}

	/**
	 * Open link available in first post
	 * 
	 * @author dheeraj.rajput
	 */
	public void openFirstPostLink()
	{
		findVisibleElement(recent_Panel1_postContentLink).click();
		// ((JavascriptExecutor)driver).executeScript("window.open()");
		Set<String> highQCollaborateWindows = driver.getWindowHandles();
		parentWindow = (String) highQCollaborateWindows.toArray()[0];
		childWindow = (String) highQCollaborateWindows.toArray()[1];
		driver.switchTo().window(childWindow);
	}

	/**
	 * Close current window and switch to parent window
	 * 
	 * @author dheeraj.rajput
	 */
	public void closeCurrentWindow()
	{
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	/**
	 * To verify user info modal
	 */
	public boolean verifyUserInfoModal()
	{
		return (isDisplayed(userInfoModal));
	}

	/**
	 * To click on Author name under recent activity
	 * 
	 * @Updated 23 May 2018 (By @author dheeraj.rajput)
	 */
	public void clickOnAuthorName(String post, String siteName)
	{
		String panelBody = "//*[normalize-space(text())='" + post + "']//ancestor::*[@class='panel-body']";
		By siteAndPost = By.xpath(panelBody + "//*[normalize-space(text())='" + siteName + "']");
		if (isDisplayed(siteAndPost, Speed.slow))
		{
			By authorCard = By.xpath(panelBody + "//*[contains(@class,'usercardLink')]");
			findVisibleElement(authorCard, Speed.slow).click();
			findVisibleElement(userInfoCard);
		}
	}

	/**
	 * To send text in comment box under recent activity
	 * 
	 * @throws InterruptedException
	 * @param1=Author of activity
	 * @param2=activity name
	 */

	/**
	 * To click on Assigned to me label under Tasks
	 */
	public void clickOnAssignedToMe()
	{
		findClickableElement(assignedToMeLabel).click();
	}

	/**
	 * To click on All tasks label under Tasks
	 */
	public void clickOnAllTasksLabel()
	{
		findClickableElement(allTasksLabel).click();
	}

	/**
	 * To click on clear filters in filter drop down under recent activity
	 */
	public void clickOnClearFiltersInFilterDropDown()
	{
		findClickableElement(clearFiltersLabelInPeopleInFilterDropDown).click();
	}

	/**
	 * To select check box in People in filter drop down under recent activity after search
	 * 
	 * @argument=mailID to search particular user
	 */
	public void selectSearchedIdCheckBoxInPeopleInFilterDropDown(String mailId)
	{
		sendTextInSearchTextBoxInPeopleInFilterDropDown(getUserData(mailId));
		WebElement presentCheckBox = findPresentElement(By.xpath("//*[normalize-space(text())='" + mailId + "']//preceding::input[@type='checkbox'][1]"));
		if (!presentCheckBox.isSelected())
		{
			presentCheckBox.click();
		}
	}

	/**
	 * To send text in search text box in People in filter drop down under recent activity
	 * 
	 * @argument=mailID to search particular user
	 */
	public void sendTextInSearchTextBoxInPeopleInFilterDropDown(String mailId)
	{
		expandArrowsInFilterDropDown(ActivityLabels.ACTIVITY_RECENTACTIVITY_FILTER_PEOPLE);
		findPresentElement(searchTextBoxInPeopleInFilterDropDown).sendKeys(mailId);
	}

	/**
	 * To select check box in People in filter drop down under recent activity
	 */
	public void selectAllOfTheAboveCheckBoxInPeopleInFilterDropDown()
	{
		findClickableElement(allOfTheAutoCheckBoxInPeopleInFilterDropDown).click();
	}

	/**
	 * To click on People in filter drop down under recent activity
	 */
	public void clickOnPeopleInFilterDropDown()
	{
		findClickableElement(peopleInFilterDropDown).click();
	}

	/**
	 * To click on check box in content type in under recent activity
	 */
	public void selectCheckBoxInContentType(boolean state, String... data)
	{
		expandArrowsInFilterDropDown(ActivityLabels.ACTIVITY_RECENTACTIVITY_FILTER_CONTENTTYPE);
		for (int i = 0; i < data.length; i++)
		{
			WebElement presentElement = findPresentElement(By.xpath("//*[@class='checkbox']//*[normalize-space()='" + data[i] + "']/input"));
			if (!presentElement.isSelected())
			{
				presentElement.click();
			}
		}
	}

	/**
	 * To click on Content type in filter drop down under recent activity
	 */
	public void clickOnContentTypeInFilterDropDown()
	{
		findClickableElement(contentTypeInFilterDropDown).click();
	}

	/**
	 * To click on Activity label under recent activity
	 */
	public void clickOnFilterDropDown()
	{
		findClickableElement(filterDropDown).click();
	}

	/**
	 * To click on Activity label under recent activity
	 */
	public void clickOnActivityLabel()
	{
		findClickableElement(activityLabel).click();
	}

	/**
	 * To verify Event area is available or not
	 */
	public boolean verifyEventArea()
	{
		return (isDisplayed(eventAreaInActivity));
	}

	/**
	 * To verify Recent Activity area is available or not
	 */
	public boolean verifyTaskArea()
	{
		return (isDisplayed(taskAreaInActivity));
	}

	/**
	 * To verify Recent Activity area is available or not
	 */
	public boolean verifyRecentActivityArea()
	{
		findVisibleElement(recentActivityAreaInActivity, Speed.slow);
		return (isDisplayed(recentActivityAreaInActivity, Speed.slow));
	}

	/**
	 * To verify Blog area is available or not
	 */
	public boolean verifyBlogArea()
	{
		return (isDisplayed(blogAreaInActivity));
	}

	/**
	 * To click on attachments of comment
	 */
	public void clickOnCommentAttachmentLink()
	{
		findClickableElement(attachmentLinkOfComment).click();
	}

	/**
	 * @return
	 * 		To verify the attachment modal
	 */
	public boolean verifyAttachmentModal()
	{
		return (isDisplayed(attachmentModal, Speed.slow));
	}

	/**
	 * @param tabName
	 *        To click on particular tab in add attachment modal
	 */
	public void clickOnTabOnHeaderInAddAttachmentModal(String tabName)
	{
		findClickableElement(By.xpath("(//*[@id='attachmentModal_navigationTabDiv']//*[normalize-space()='" + getUserData(tabName.trim()) + "'])[last()]")).click();
	}

	/**
	 * To click on site drop down in add attachment modal
	 */
	public void clickOnSiteDropDownInAddAttachmentModal()
	{
		findClickableElement(siteDropDownInAddAttachmentModal).click();
	}

	/**
	 * @param siteName
	 *        To search a site in site drop down in add attachment modal
	 */
	public void sendTextInSearchTextBoxInAddAttachmentModal(String siteName)
	{
		clickOnSiteDropDownInAddAttachmentModal();
		findPresentElement(serchBoxInSiteDropDownInAddAttachmentModal).sendKeys(siteName);
	}

	/**
	 * @param siteName
	 *        To click on particular site in site drop down
	 */
	public void selectSiteInAddAttachmentModal(String siteName)
	{
		sendTextInSearchTextBoxInAddAttachmentModal(siteName);
		findClickableElement(By.xpath("(//*[@id='attachmentModal_dropDownSiteList_recentTab_siteList']//*[normalize-space()='" + siteName.trim() + "'])[last()]")).click();
	}

	/**
	 * @author vivek.mishra
	 * @param tabName
	 *        To click on tabs in recent activity page ex : all,posts,recent
	 * @created on 18/03/2018
	 */
	public void clickOnRecentActivityTabs(String tabName)
	{
		WebElement elementTab = findVisibleElement(By.xpath("//*[@class='nav nav-pills moremenu']//a[normalize-space(text())='" + tabName.trim() + "']"), Speed.slow);
		elementTab.click();
	}

	/**
	 * @author vivek.mishra
	 *         To expand the sites arrow in filter drop down
	 * @created on 18/03/2018
	 */
	public void expandArrowsInFilterDropDown(String arrowName)
	{
		if (!verifyExpansionArrowStatusInFilterDropDown(arrowName))
		{
			WebElement element = findVisibleElement(By.xpath("//*[@data-toggle = 'collapse' and contains(normalize-space(.),'" + arrowName.trim() + "')]/span[contains(@class,'icon-chevron-right')]"), Speed.slow);
			element.click();
		}
	}

	/**
	 * @author vivek.mishra
	 * @return true if filter drop down opened
	 * @created on 18/03/2018
	 */
	public boolean verifyFilterDropDownOpen()
	{
		findVisibleElement(filterDropDown, Speed.slow);
		return (isDisplayed(filterDropDown, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To collapse the sites arrow in filter drop down
	 * @created on 18/03/2018
	 */
	public void collapseArrowsInFilterDropDown(String arrowName)
	{
		if (verifyExpansionArrowStatusInFilterDropDown(arrowName))
		{
			WebElement element = findVisibleElement(By.xpath("//*[@data-toggle = 'collapse' and contains(normalize-space(.),'" + arrowName.trim() + "')]/span[contains(@class,'icon-chevron-down')]"), Speed.slow);
			element.click();
		}
	}

	/**
	 * @author vivek.mishra
	 * @param arrowName of which need to check status
	 * @return true if expanded else return false
	 * @created on 20/03/2018
	 */
	public boolean verifyExpansionArrowStatusInFilterDropDown(String arrowName)
	{
		WebElement element = findVisibleElement(By.xpath("//*[@data-toggle = 'collapse' and contains(normalize-space(.),'" + arrowName.trim() + "')]"));
		String attributeValue = element.getAttribute("class");
		if (attributeValue.equals("collapsed"))
			return false;
		else
			return true;
	}

	/**
	 * @author vivek.mishra
	 * @param option to be verified in filter drop down
	 * @return
	 * @created on 20/03/2018
	 */
	public boolean verifyOptionsInFilterDropDown(String option)
	{
		// findVisibleElement(By.xpath("(//*[@class='dropdown-menu dropAccord dropdownMaxWidth']//*[normalize-space(.)='" + option.trim() + "'])[last()]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[@class='dropdown-menu dropAccord dropdownMaxWidth']//*[normalize-space(.)='" + option.trim() + "'])[last()]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the site list in filter drop down
	 * @created on 20/03/2018
	 */
	public boolean verifySiteListInFilterDropDown()
	{
		findVisibleElement(siteListInFilterDropDown, Speed.slow);
		return (isDisplayed(siteListInFilterDropDown, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the user list availability
	 * @created on 20/03/2018
	 */
	public boolean verifyUserListInFilterDropDown()
	{
		findVisibleElement(userListInFilterDropDown, Speed.slow);
		return (isDisplayed(userListInFilterDropDown, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param siteName in which file is uploaded
	 * @param fileName uploaded file
	 * @return
	 * @created on 20/03/2018
	 */
	public boolean verifyUploadedFile(String siteName, String fileName)
	{
		findVisibleElement(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + fileName.trim() + "'])[1]"));
		return (isDisplayed(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + fileName.trim() + "'])[1]")));
	}

	/**
	 * @author vivek.mishra
	 * @param siteName in which file is uploaded
	 * @param fileName uploaded file
	 * @return
	 * @throws ParseException
	 * @created on 20/03/2018
	 */
	public boolean verifyFileSize(String siteName, String fileName) throws ParseException
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		File file = new File(path);
		DecimalFormat df = new DecimalFormat("0.00");
		double size = file.length();
		size = size / 1024;
		String formate = df.format(size);
		size = (Double) df.parse(formate);
		findVisibleElement(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + fileName.trim() + "']/..//following-sibling::div[contains(normalize-space(.),'" + size + "')])[1]"));
		return (isDisplayed(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + fileName.trim() + "']/..//following-sibling::div[contains(normalize-space(.),'" + size + "')])[1]")));
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param fileName
	 * @param userName
	 * @return
	 * @created on 20/03/2018
	 */
	public boolean verifyUserName(String siteName, String postName, String userName)
	{
		if (isDisplayed(By.xpath("(//*[normalize-space(text())='" + postName.trim() + "']/ancestor::div[contains(@class,'post')][1]//preceding-sibling::*[@class='clearfix']//*[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[contains(@class,'postMeta')]//strong)[1]"), Speed.slow))
		{
			String actualUserName = findVisibleElement(By.xpath("//*[normalize-space(text())='" + postName.trim() + "']/ancestor::div[contains(@class,'post')][1]//preceding-sibling::*[@class='clearfix']//*[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[contains(@class,'postMeta')]//strong"), Speed.slow).getText().trim();
			return (actualUserName.equals(userName.trim()));
		}
		return false;
	}

	/**
	 * @author vivek.mishra
	 *         To make microblogText box enable when it is in disabled mode
	 * @creation date 23/03/2018
	 */
	public void clickOnMicroblogTextBox()
	{
		WebElement textBoxDisabled = findVisibleElement(microblogTextBoxDisabled, Speed.slow);
		textBoxDisabled.click();
	}

	/**
	 * @author vivek.mishra
	 * @param text to be sent
	 * @creation date 23/03/2018
	 */
	public void sendTextInMicroblogTextBox(String text)
	{
		clickOnMicroblogTextBox();
		WebElement textBoxEnabled = findVisibleElement(microBlogTextBoxEnabled, Speed.slow);
		textBoxEnabled.clear();
		textBoxEnabled.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 * @param labelName to be removed
	 * @creation date 23/03/2018
	 */
	public void removeLabelFromShareWithTextBox(String labelName)
	{
		WebElement label = findVisibleElement(By.xpath("//*[@class='token-label' and normalize-space(text())='" + labelName.trim() + "']/preceding-sibling::a"), Speed.slow);
		label.click();
	}

	/**
	 * @author vivek.mishra
	 * @param text to be send
	 * @creation date 23/03/2018
	 */
	public void sendTextInShareWithTextBox(String text)
	{
		WebElement textBox = findVisibleElement(shareWithTextBox, Speed.slow);
		textBox.click();
		textBox.sendKeys(text.trim());
		selectSiteNameFromAutoSuggest(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on microblog post button
	 * @creation date 23/03/2018
	 */
	public void clickOnMicroBlogPostButton()
	{
		WebElement postButton = findVisibleElement(microblogPostButtonEnabled, Speed.slow);
		moveToElement(microblogPostButtonEnabled);
		postButton.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on microblog cancel button
	 * @creation date 23/03/2018
	 */
	public void clickOnMicroBlogCancelButton()
	{
		WebElement postButton = findVisibleElement(microblogCancelButton, Speed.slow);
		postButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @param siteName to be clicked
	 *        To select site from drop down site list
	 * @creation date 26/03/2018
	 */
	public void selectSiteNameFromAutoSuggest(String siteName)
	{
		if (isDisplayed(shareReceipintHover, Speed.slow))
		{
			WebElement recipientHover = findVisibleElement(By.xpath("(//*[@class='typeahead_primary']//*[normalize-space(text())='" + siteName.trim() + "'])[last()]"), Speed.slow);
			recipientHover.click();
		}
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param activity
	 * @return the activity visibility
	 * @creation date 26/03/2018
	 */
	public boolean verifyActivity(String siteName, String activity)
	{
		return (isDisplayed(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + activity.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param post
	 * @return the status
	 * @creation date 09/02/2018
	 */
	public boolean verifyPost(String siteName, String post)
	{
		if (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[contains(@class,'post')]//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/..)[1]"), Speed.slow))
		{
			String postTime = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[contains(@class,'post')]//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/..)[1]"), Speed.slow).getText().trim();

			if (postTime.contains("Just now"))
				return true;
			else if (postTime.contains("minute"))
			{
				int minute = Integer.parseInt(postTime.substring(0, postTime.indexOf(" ")).trim());
				if (minute < 5)
					return true;
			}
		}
		return false;
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param post
	 *        Post link of which need to click
	 * @creation date 09/02/2018
	 */
	public void clickOnPostLink(String siteName, String post)
	{
		WebElement element = findVisibleElement(By.xpath("(//*[@class='postMeta ']//*[@class='greyMeta']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + post + "']//following-sibling::*//a[not(contains(@class,'embedly-card'))])[1]"), Speed.slow);
		element.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the site activity page middle pannel
	 * @creation date 26/03/2018
	 */
	public boolean verifySiteActivityMiddlePannel()
	{
		findVisibleElement(siteActivityMiddlePannel, Speed.slow);
		return (isDisplayed(siteActivityMiddlePannel, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 *         To click on insert link button
	 * @creation date 02/04/2018
	 */
	public void clickOnInsertLinkButton()
	{
		WebElement insertLinkBtn = findVisibleElement(insertLinkButton, Speed.slow);
		insertLinkBtn.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on Attachment button
	 * @creation date 02/04/2018
	 */
	public void clickOnAttachmentButton()
	{
		WebElement attachmentBtn = findVisibleElement(attachmentLink, Speed.slow);
		attachmentBtn.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on text formate button
	 * @creation date 02/04/2018
	 */
	public void clickOnTextFormatButton()
	{
		WebElement textFormatBtn = findVisibleElement(textFormatingButton, Speed.slow);
		textFormatBtn.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @creation date 02/04/2018
	 */
	public boolean verifyModal()
	{
		findVisibleElement(modal, Speed.slow);
		return (isDisplayed(modal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param tabName
	 * @return
	 * 		To verify the tabs in insert link modal
	 * @creation date 02/04/2018
	 */
	public boolean verifyTabsInInsertLinkModal(String tabName)
	{
		findVisibleElement(By.xpath("//*[@id='insertLink_navigationLinks']//*[normalize-space(text())='" + getUserData(tabName.trim()) + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[@id='insertLink_navigationLinks']//*[normalize-space(text())='" + getUserData(tabName.trim()) + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param tabName
	 *        To click on tab in insert link modal
	 * @creation date 02/04/2018
	 */
	public void clickOnTabsInInsertLinkModal(String tabName)
	{
		WebElement tab = findVisibleElement(By.xpath("//*[@id='insertLink_navigationLinks']//*[normalize-space(text())='" + getUserData(tabName.trim()) + "']"), Speed.slow);
		tab.click();
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the recent tab opened
	 * @creation date 02/04/2018
	 */
	public boolean verifyRecentTabOpenedInInsertLinkModal()
	{
		findVisibleElement(recentTabInInsertLinkModal, Speed.slow);
		return (isDisplayed(recentTabInInsertLinkModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the browse tab opened
	 * @creation date 02/04/2018
	 */
	public boolean verifyBrowseTabOpenedInInsertLinkModal()
	{
		findVisibleElement(browseTabInInsertLinkModal, Speed.slow);
		return (isDisplayed(browseTabInInsertLinkModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the external tab opened
	 * @creation date 02/04/2018
	 */
	public boolean verifyExternalTabOpenedInInsertLinkModal()
	{
		findVisibleElement(externalTabInInsertLinkModal, Speed.slow);
		return (isDisplayed(externalTabInInsertLinkModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the upload tab opened
	 * @creation date 02/04/2018
	 */
	public boolean verifyUploadTabOpenedInInsertLinkModal()
	{
		findVisibleElement(uploadTabInInsertLinkModal, Speed.slow);
		return (isDisplayed(uploadTabInInsertLinkModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the search tab opened
	 * @creation date 02/04/2018
	 */
	public boolean verifySearchTabOpenedInInsertLinkModal()
	{
		findVisibleElement(searchTabInInsertLinkModal, Speed.slow);
		return (isDisplayed(searchTabInInsertLinkModal, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the external URL text box
	 * @creation date 02/04/2018
	 */
	public boolean verifyExternalURLTextBox()
	{
		findVisibleElement(externalURLTextBox, Speed.slow);
		return (isDisplayed(externalURLTextBox, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify the short url checkBox
	 * @creation date 02/04/2018
	 */
	public boolean verifyShortURLCheckBox()
	{
		findVisibleElement(externalURLShortCheckBox, Speed.slow);
		return (isDisplayed(externalURLShortCheckBox, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param URL to be sent
	 * @creation date 02/04/2018
	 */
	public void sendTextInExternalURLTextBox(String URL)
	{
		WebElement textBox = findVisibleElement(externalURLTextBox, Speed.slow);
		textBox.click();
		textBox.clear();
		textBox.sendKeys(URL.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To select the short URL check box
	 * @creation date 02/04/2018
	 */
	public void selectShortURLCheckBox()
	{
		WebElement checkBox = findVisibleElement(externalURLShortCheckBox, Speed.slow);
		if (!checkBox.isSelected())
			checkBox.click();
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName to be clicked
	 * @creation date 02/04/2018
	 */
	public void clickOnModalButton(String buttonName)
	{
		WebElement button = findVisibleElement(By.xpath("//*[contains(@class,'in')]//*[@class='modal-content']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow);
		button.click();
	}

	/**
	 * @author vivek.mishra
	 * @param fileName to be attached
	 * @creation date 02/04/2018
	 */
	public void attachFileInInsertLinkModal(String fileName)
	{
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName;
		findPresentElement(browseButtonInInsertLinkModal, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 * @param labelName to be available
	 * @return
	 * @creation date 03/04/2018
	 */
	public boolean verifyLabelInShareWithTextBox(String labelName)
	{
		return (isDisplayed(By.xpath("//*[@class='token-label' and normalize-space(text())='" + labelName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param post
	 *        to click on more action button of a post
	 * @creation date 04/04/2018
	 */
	public void clickOnMoreActionOfPost(String siteName, String post)
	{
		WebElement moreAction = findVisibleElement(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + post.trim() + "'])[1]//ancestor::*[@class='panel-body']//a[@class='icon icon-actions']"), Speed.slow);
		moreAction.click();
	}

	/**
	 * @author vivek.mishra
	 * @param siteName
	 * @param post
	 * @param option
	 *        To click on an option in more action of a post
	 * @creation date 04/04/2018
	 */
	public void clickOnOptionInMoreActionOfPost(String siteName, String post, String option)
	{
		clickOnMoreActionOfPost(siteName, post);
		WebElement moreActionOption = findVisibleElement(By.xpath("//*[contains(@class,'inlineBlock open')]//*[normalize-space(text())='" + option.trim() + "']"), Speed.slow);
		moreActionOption.click();
	}

	/**
	 * @author vivek.mishra
	 * @param modalName
	 * @return
	 * 		To verify the modal
	 * @creation date 04/04/2018
	 */
	public boolean verifyModal(String modalName)
	{
		return (isDisplayed(By.xpath("//*[@class='modal-title' and normalize-space()='" + modalName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param buttonName
	 * @return
	 * @creation date 04/04/2018
	 */
	public boolean verifyModalButton(String buttonName)
	{
		findVisibleElement(By.xpath("//*[contains(@class,'in')]//*[@class='modal-content']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow);
		return (isDisplayed(By.xpath("//*[contains(@class,'in')]//*[@class='modal-content']//*[normalize-space(text())='" + buttonName.trim() + "']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param expectedMessage wil be matched with actual message
	 * @return the matching result
	 * @creation date 04/04/2018
	 */
	public boolean verifyModalMessage(String expectedMessage)
	{
		String actualMessage = findVisibleElement(modalMessage, Speed.slow).getText().trim();
		return (actualMessage.equals(expectedMessage.trim()));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 *        To click on like button of particular post
	 * @creation date 09/04/2018
	 */
	public void clickOnLikeButtonOfPost(String post, String siteName)
	{
		WebElement likeButton = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*[@class='greyMeta']//a[contains(@id,'likeActivity')])[1]"), Speed.slow);
		likeButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 *        To click on particular post comment button
	 * @creation date 09/04/2018
	 */
	public void clickOnCommentButtonOfPost(String post, String siteName)
	{
		WebElement commentButton = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*[@class='greyMeta']//a[contains(@id,'commentLink')])[1]"), Speed.slow);
		commentButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param comment to be entered
	 * @creation date 09/04/2018
	 */
	public void sendCommentInPostCommentBox(String post, String siteName, String comment)
	{
		clickOnCommentButtonOfPost(post, siteName);
		WebElement textBox = findVisibleElement(postCommentTextBox, Speed.slow);
		textBox.click();
		textBox.clear();
		textBox.sendKeys(comment.trim());
	}

	/**
	 * @author vivek.mishra
	 *         To click on post comment button
	 * @creation date 10/04/2018
	 */
	public void clickOnCommentPostButton()
	{
		WebElement postButton = findVisibleElement(commentPostButtonEnabled, Speed.slow);
		postButton.click();
	}

	/**
	 * @author vivek.mishra
	 *         To click on cancel comment button
	 * @creation date 10/04/2018
	 */
	public void clickOnCommentCancelButton()
	{
		WebElement cancelButton = findVisibleElement(commentCancelButton, Speed.slow);
		cancelButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return
	 * 		To verify complete post section to be available
	 * @creation date 10/04/2018
	 */
	public boolean verifyCommentSectionOfPost(String post, String siteName)
	{
		findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec'])[1]"), Speed.slow);
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param comment
	 * @return the comment availability
	 * @creation date 10/04/2018
	 */
	public boolean verifyComment(String post, String siteName, String comment)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param comment
	 * @param option
	 *        To click on options of a post comment
	 * @creation date 10/04/2018
	 */
	public void clickOnOptionOfCommentOfPost(String post, String siteName, String comment, String option)
	{
		findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() + "']/ancestor::*[@class='truncateHTML']//following-sibling::*//a[normalize-space(text())='" + getUserData(option.trim()) + "'])[1]"), Speed.slow).click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the count of the comments of a post
	 * @creation date 11/04/2018
	 */
	public int getCommentCountOfPost(String post, String siteName)
	{
		WebElement commentLabel = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*[@class='greyMeta']//a[contains(@id,'commentLink')])[1]"), Speed.slow);
		String commentLabelData = commentLabel.getText().trim();
		if (!commentLabelData.equals(ActivityLabels.ACTIVITY_RECENTACTIVITY_COMMENT))
		{
			commentLabelData = commentLabelData.split(" ")[0].trim();
			return (Integer.parseInt(commentLabelData));
		}
		return 0;
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param labelName to be verified
	 * @return the label visibility
	 * @creation date 11/04/2018
	 */
	public boolean verifyPostFooterLabels(String post, String siteName, String labelName)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*[@class='greyMeta']//a[normalize-space(text())='" + getUserData(labelName.trim()) + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param message
	 * @return the visibility of message apearing under comment box
	 * @creation date 11/04/2018
	 */
	public boolean verifyCommentBoxFooterMessage(String post, String siteName, String message)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[normalize-space(text())='" + message.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public boolean verifyAttachmentIconOfCommentBoxOfPost(String post, String siteName)
	{
		return (isDisplayed(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-paper-clip postIcon uploadBtn tooltipShow pull-left']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public boolean verifyLinkIconOfCommentBoxOfPost(String post, String siteName)
	{
		return (isDisplayed(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-link postIcon pull-left tooltipShow']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public boolean verifyTextFormaterIconOfCommentBoxOfPost(String post, String siteName)
	{
		return (isDisplayed(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-text-formating  postIcon uploadBtn tooltipShow']"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the cancel button visibility
	 * @creation date 12/04/2018
	 */
	public boolean verifyCommentCancelButton()
	{
		return (isDisplayed(commentCancelButton, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return the post button visibility
	 * @creation date 12/04/2018
	 */
	public boolean verifyCommentPostButton()
	{
		return (isDisplayed(commentPostButtonDisabled, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public void clickOnAttachmentIconOfCommentBoxOfPost(String post, String siteName)
	{
		WebElement attachment = findVisibleElement(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-paper-clip postIcon uploadBtn tooltipShow pull-left']"), Speed.slow);
		attachment.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public void clickOnLinkIconOfCommentBoxOfPost(String post, String siteName)
	{
		WebElement link = findVisibleElement(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-link postIcon pull-left tooltipShow']"), Speed.slow);
		link.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @return the attachment icon visibility under the comment box of a post
	 * @creation date 12/04/2018
	 */
	public void clickOnTextFormaterIconOfCommentBoxOfPost(String post, String siteName)
	{
		WebElement formater = findVisibleElement(By.xpath("//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//a[@class='icon-text-formating  postIcon uploadBtn tooltipShow']"), Speed.slow);
		formater.click();
	}

	/**
	 * @author vivek.mishra
	 * @param tabName to be clicked
	 * @creation date 12/04/2018
	 */
	public void clickOnAttachmentModalTab(String tabName)
	{
		WebElement uploadTab = findVisibleElement(By.xpath("//*[@id='attachmentModal_navigationLinks']//*[normalize-space(text())='" + getUserData(tabName.trim()) + "']"), Speed.slow);
		uploadTab.click();
	}

	/**
	 * @author vivek.mishra
	 * @param fileName to be attached
	 * @creation date 12/04/2018
	 */
	public void attachFileInUploadTabInAddAttachmentsModal(String fileName)
	{
		clickOnAttachmentModalTab(upload);
		verifyUploadTabOpenedInAddAttachmentsModal();
		String path = TestBaseSetup.currentDir + "\\testData\\" + fileName.trim();
		findPresentElement(browseButtonInAddAttachmentModal, Speed.slow).sendKeys(path);
		findVisibleElement(btnDone, 90, 200);
	}

	/**
	 * @author vivek.mishra
	 * @return the upload tab when opened true
	 * @created on 12/04/2018
	 */
	public boolean verifyUploadTabOpenedInAddAttachmentsModal()
	{
		findVisibleElement(uploadTabOpened, Speed.slow);
		return (isDisplayed(uploadTabOpened, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param fileName to be removed
	 * @created on 12/04/2018
	 */
	public void clickOnRemoveButtonOfFileOfComment(String fileName)
	{
		WebElement removeButton = findVisibleElement(By.xpath("//*[contains(@id,'comment_editCommentAttachmentQueue')]//*[normalize-space(text())='" + fileName.trim() + "']//preceding-sibling::a[@data-original-title='Remove']"), Speed.slow);
		removeButton.click();
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param comment
	 * @param fileName
	 * @return the availability of attached file
	 * @created on 12/04/2018
	 */
	public boolean verifyAttachedFileToComment(String post, String siteName, String comment, String fileName)
	{
		verifyComment(post, siteName, comment);
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() + "']/ancestor::*[@id='commentMeta']//strong[normalize-space(text())='" + fileName.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param oldComment
	 * @param newComment
	 *        To edit the comment box
	 * @created on 12/04/2018
	 */
	public void editCommentBoxText(String post, String siteName, String oldComment, String newComment)
	{
		WebElement currentTextBox = findVisibleElement(By.xpath("//*[contains(@id,'addCommentField')]//*[normalize-space(text())='" + oldComment.trim() + "']"), Speed.slow);
		currentTextBox.clear();
		WebElement newTextBox = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//*[contains(@id,'addCommentField')]//*[normalize-space(text())='']/..)[1]"), Speed.slow);
		newTextBox.click();
		newTextBox.sendKeys(newComment.trim());
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * @created on 20/03/2018
	 */
	public boolean verifyActivityTabInRecentActivity()
	{
		findVisibleElement(activityTabInRecentActivity, Speed.slow);
		return (isDisplayed(activityTabInRecentActivity, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param post
	 * @param siteName
	 * @param dueDate in dd/MMM/yyyy formate
	 * @return the date availability
	 * @created on 17/04/2018
	 */
	public boolean verifyPostDueDate(String post, String siteName, String dueDate)
	{
		if (isDisplayed(By.xpath("(//*[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*//*[normalize-space(text())='" + post.trim() + "']/..//following-sibling::*[@class='greyMeta clearfix'])[1]"), Speed.slow))
		{
			String dateData = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*//*[normalize-space(text())='" + post.trim() + "']/..//following-sibling::*[@class='greyMeta clearfix'])[1]"), Speed.slow).getText().trim();
			return (dateData.contains(dueDate.trim()));
		}
		return false;
	}

	/**
	 * @author vivek.mishra
	 * @param postName to be clicked
	 * @param siteName
	 * @return
	 * @created on 17/04/2018
	 */
	public void clickOnBlogPost(String postName, String siteName)
	{
		WebElement presentPost = findVisibleElement(By.xpath("(//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='clearfix']//following-sibling::*//*[normalize-space(text())='" + postName.trim() + "'])[1]"));
		presentPost.click();
	}

	/**
	 * @author surbhi.khetan
	 * @param post
	 *        Shre via microblog
	 * @creation date 12/04/2018
	 */
	@Override
	public boolean verifyMicroblogPost(String post)
	{

		By microblogPost = By.xpath(".//*[@id='dashboard_recentActivity']//p[text()='" + post.trim() + "']");
		return isDisplayed(microblogPost, Speed.slow);
	}

	/**
	 * @author surbhi.khetan
	 *         go to re-post tab
	 * @creation date 12/04/2018
	 */
	public void gotoRePost()
	{
		findVisibleElement(modalContent, Speed.slow);
		findVisibleElement(re_post, Speed.slow).click();
	}

	/**
	 * @author surbhi.khetan
	 * @param message
	 * @param sitename
	 * @param filename
	 *        share via re-post
	 * @creation date 12/04/2018
	 */
	public void shareViaRePostWithAttachment(String message, String sitename, String fileName)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreAction("Share");
		}
		gotoRePost();
		WebElement messageInput = findVisibleElement(repostMessage);
		messageInput.clear();
		messageInput.sendKeys(message);
		WebElement site = findVisibleElement(shareWithSite);
		site.sendKeys(sitename);
		findVisibleElement(share_Repost, Speed.slow);
		selectSiteNameFromAutoSuggest(sitename);
		clickOnAttachmentButton();
		addAttachmentFromUploadTab(fileName);
		findVisibleElement(share_Repost, Speed.slow);
		clickRePostInShareModal();
	}

	/**
	 * @author surbhi.khetan
	 * @param message
	 * @param sitename
	 * @param filename
	 *        share via re-post
	 * @creation date 23/04/2018
	 */
	public void shareViaRePostWithRecentAttachment(String message, String sitename, String fileName)
	{
		if (!isDisplayed(modalContent))
		{
			selectOptionInMoreAction("Share");
		}
		gotoRePost();
		WebElement messageInput = findVisibleElement(repostMessage);
		messageInput.clear();
		messageInput.sendKeys(message);
		WebElement site = findVisibleElement(shareWithSite);
		site.sendKeys(sitename);
		findVisibleElement(share_Repost, Speed.slow);
		selectSiteNameFromAutoSuggest(sitename);
		clickOnAttachmentButton();
		addAttachmentFromRecentTab(sitename, fileName);
		findVisibleElement(share_Repost, Speed.slow);
		clickRePostInShareModal();
	}

	/**
	 * @author surbhi.khetan
	 * @param post
	 *        verify message of microblog after it is shared with user on re-post tab
	 * @creation date 13/04/2018
	 */
	public boolean verifyMicroblogMessage(String post)
	{
		String elementText = findVisibleElement(microblogMessage, Speed.slow).getText();
		return elementText.trim().equals(post.trim());
	}

	/**
	 * @author surbhi.khetan
	 *         verify share modal visibility
	 * @creation date 13/04/2018
	 */
	public boolean verifyShareModalVisibility()
	{

		return isDisplayed(modalContent);
	}

	/**
	 * @author surbhi.khetan
	 *         click on re-post button
	 * @creation date 13/04/2018
	 */
	public void clickRePostInShareModal()
	{
		findClickableElement(share_Repost).click();
	}

	/**
	 * @author surbhi.khetan
	 *         verify insert link visibility in re-post tab
	 * @creation date 13/04/2018
	 */
	public boolean verifyInsertLinkButton()
	{

		findVisibleElement(modalContent, Speed.slow);
		return (isDisplayed(insertLink, Speed.slow));
	}

	/**
	 * @author surbhi.khetan
	 *         verify attachment visibility in re-post tab
	 * @creation date 13/04/2018
	 */
	public boolean verifyAttachmentButton()
	{

		findVisibleElement(modalContent, Speed.slow);
		return (isDisplayed(attachmentLink, Speed.slow));
	}

	/**
	 * @author surbhi.khetan
	 *         verify text format link visibility in re-post tab
	 * @creation date 13/04/2018
	 */
	public boolean verifyTextFormatButton()
	{

		findVisibleElement(modalContent, Speed.slow);
		return (isDisplayed(textFormatLink, Speed.slow));

	}

	/**
	 * @author surbhi.khetan
	 * @param sitename
	 *        verify site list in share with
	 * @creation date 16/04/2018
	 */
	public boolean verifySiteFromAutoSuggestedList(String sitename)
	{

		WebElement site = findVisibleElement(shareWithSite, Speed.slow);
		site.clear();
		site.sendKeys(sitename);
		By siteName = By.xpath(".//*[@class='typeahead_primary']//*[normalize-space(.)='" + sitename + "']");
		return isDisplayed(siteName, Speed.slow);
	}

	/**
	 * @author surbhi.khetan
	 *         verify default value in share with
	 * @creation date 16/04/2018
	 */
	public boolean verifyDefaultShareWith(String value)
	{

		String elementText = findVisibleElement(defaultShare, Speed.slow).getText();
		return elementText.trim().equals(value.trim());
	}

	/**
	 * Open received link available in first post
	 * 
	 * @creation date 01/05/2018
	 * @author surbhi.khetan
	 */
	public void openFirstPostReceivedLink()
	{
		findVisibleElement(firstLink).click();

	}
	
	/**
	 * @author khushbu.dhandhukiya
	 * @param post
	 * @param siteName
	 * @param comment
	 * @param option
	 *        
	 * @creation date 17/05/2018
	 */
	public boolean verifyOperationOnCommentOfPost(String post, String siteName, String comment, String option)
	{
		return(isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() + "']/ancestor::*[@class='truncateHTML']//following-sibling::*//a[normalize-space(text())='" + getUserData(option.trim()) + "'])[1]"), Speed.slow));
		
	}
	
	
	/**
	 * @author khushbu.dhandhukiya
	 * @param post
	 * @param siteName
	 * @param comment
	 * @param option
	 *        
	 * @creation date 17/05/2018
	 */
	public void clickOnCommentLikeCount(String post, String siteName, String comment, String option)
	{
		
		WebElement presentOperation = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() +"']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() +"']/ancestor::*[@class='truncateHTML']//following-sibling::*//a[normalize-space(text())='" + getUserData(option.trim()) + "']//following-sibling::*//a)[1]"));
		presentOperation.click();
		findVisibleElement(contentLikeModal, Speed.slow);
	}
	
	/**
	 * @author khushbu.dhandhukiya
	 * verify people who like the comment 
	 * @creation date 17/05/2018
	 */
	public boolean verifyPeopleWhoLikeCommentModal()
	{
		return(isDisplayed(likeCommentModal,Speed.slow));
		
	}
	
	/**
	 * @author khushbu.dhandhukiya
	 *        close Like user modal
	 * @creation date 17/05/2018
	 */
	
	public void clickOnCloseLikeModal()
	{
		findVisibleElement(closeLikeModal,Speed.slow).click();
	}
	
	/**
	 * @author khushbu.dhandhukiya
	 * @param post
	 * @param siteName
	 * @param comment
	 * @param option
	 *        used to get count of Like comment
	 * @creation date 18/05/2018
	 */
	public int getCommentLikeCount(String post, String siteName, String comment, String option)
	{
		if (isDisplayed(By.xpath("(//*[normalize-space(text())='" + post.trim() + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() + "']/ancestor::*[@class='truncateHTML']//following-sibling::*//a[normalize-space(text())='" + getUserData(option.trim()) + "']//following-sibling::*//a)[1]"), Speed.slow))
		{
			String countText = findVisibleElement(By.xpath("(//*[normalize-space(text())='" + post.trim() +"']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName.trim() + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//p[normalize-space(text())='" + comment.trim() +"']/ancestor::*[@class='truncateHTML']//following-sibling::*//a[normalize-space(text())='" + getUserData(option.trim()) + "']//following-sibling::*//a)[1]")).getText().trim();
			int totalCount=Integer.parseInt(countText);
			return totalCount;
		}
			
		return 0;
	}

	/**
	 * Verify newly created task is visible in right panel -> Tasks
	 * 
	 * @param taskName
	 *        name of the task to verify
	 * @return true
	 *         if task found
	 * @author dheeraj.rajput
	 * @Created 18 May 2018
	 * @Updated
	 */
	public boolean verifyNewlyCreatedTaskInTasksPanel(String taskName)
	{
		findVisibleElement(rightPanel_TaskList, Speed.slow);
		By task = By.xpath(".//*[@id='siteActivityPage_rightPane_taskList']//*[normalize-space(text())='" + taskName + "']");
		return isDisplayed(task);
	}

	/**
	 * Click on task name in right panel -> Tasks
	 * 
	 * @param taskName
	 *        name of the task to click on
	 * @return TasksWeb
	 * @author dheeraj.rajput
	 * @Created 18 May 2018
	 * @Updated
	 */
	public TasksPage clickOnTaskNameInTasksPanel(String taskName)
	{
		findVisibleElement(rightPanel_TaskList, Speed.slow);
		By task = By.xpath(".//*[@id='siteActivityPage_rightPane_taskList']//*[normalize-space(text())='" + taskName + "']");
		WebElement taskElem = findVisibleElement(task);
		taskElem.click();
		findVisibleElement(taskHome, Speed.slow);
		return new TasksWeb(driver);
	}

	/**
	 * Click on See all tasks link in right panel
	 * 
	 * @return TasksWeb
	 * @author dheeraj.rajput
	 * @Created 18 May 2018
	 * @Updated
	 */
	public TasksPage clickOnSeeAllTasks()
	{
		WebElement seeAllTasks = findVisibleElement(rightPanel_SeeAllTasksLink, Speed.slow);
		seeAllTasks.click();
		findVisibleElement(taskHome, Speed.slow);
		return new TasksWeb(driver);
	}

	/**
	 * Verify event title in right panel
	 * 
	 * @param eventTitle
	 *        title of the event to verify
	 * @return true
	 *         if title matches
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyEventTitleInRightPanel(String eventTitle)
	{
		By event = By.xpath(".//*[@id='siteActivity_eventList']//*[normalize-space(text())='" + eventTitle + "']");
		return isDisplayed(event, Speed.slow);
	}

	/**
	 * Verify Event in right panel
	 * 
	 * @param eventTitle
	 *        title of the event to verify
	 * @param greyMeta
	 *        metadata to verify(i.e. Start date,location,...)
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyEventGreyMetaInRightPanel(String eventTitle, String... greyMeta)
	{
		By eventMetaXpath = By.xpath(".//*[@id='siteActivity_eventList']//*[normalize-space(text())='" + eventTitle + "']/../following-sibling::*[@class='greyMeta']");
		if (verifyEventTitleInRightPanel(eventTitle))
		{
			WebElement eventMeta = findVisibleElement(eventMetaXpath);
			for (int i = 0; i < greyMeta.length; i++)
			{
				if (!eventMeta.getText().trim().contains(greyMeta[i].trim()))
				{
					return false;
				}
			}
			return true;

		}
		return false;
	}

	/**
	 * Click on event title in right panel
	 * 
	 * @param eventTitle
	 *        title of event
	 * @return EventsPage
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public EventsPage clickOnEventInRightPanel(String eventTitle)
	{
		By event = By.xpath(".//*[@id='siteActivity_eventList']//*[normalize-space(text())='" + eventTitle + "']");
		WebElement eventElem = findVisibleElement(event, Speed.slow);
		eventElem.click();
		findVisibleElement(eventModule_RightPanel, Speed.slow);
		return new EventsWeb(driver);
	}

	/**
	 * Verify tag cloud in left panel
	 * 
	 * @return true
	 *         if tag cloud panel is visible
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyTagCloudIsPresent()
	{
		return isDisplayed(tagCloud, Speed.slow);
	}

	/**
	 * Verify tag in left panel
	 * 
	 * @param tag
	 *        tag name
	 * @return true
	 *         if tag is visible
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyTagInLeftPanel(String tag)
	{
		if (verifyTagCloudIsPresent())
		{
			By tagXpath = By.xpath("//*[@class='TagClouds']//*[normalize-space(text())='" + tag.toLowerCase() + "']");
			return isDisplayed(tagXpath, Speed.slow);
		}
		return false;
	}

	/**
	 * Click on tag in left panel
	 * 
	 * @param tag
	 *        name of the tag
	 * @return SearchContentPage
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public SearchContentPage clickOnTagInLeftPanel(String tag)
	{
		if (verifyTagCloudIsPresent())
		{
			By tagXpath = By.xpath("//*[@class='TagClouds']//*[normalize-space(text())='" + tag.toLowerCase() + "']");
			WebElement tagElement = findVisibleElement(tagXpath);
			tagElement.click();
		}
		return new SearchContentWeb(driver);
	}

	/**
	 * Verify file size
	 * 
	 * @param siteName
	 *        name of the site
	 * @param fileName
	 *        name of the file
	 * @param size
	 *        size of the file to verify
	 * @return true
	 *         if the file with expected size found
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 * @Note to get the file size use getFileSize(String fileNameWithExtension) method
	 */
	public boolean verifyFileSize(String siteName, String fileName, double size)
	{
		return isDisplayed(By.xpath("(//*[@class='postMeta ']//*[normalize-space(text())='" + siteName.trim() + "']//following::*[normalize-space(text())='" + fileName.trim() + "']/..//following-sibling::div[contains(normalize-space(.),'" + size + "')])[1]"));
	}

	/**
	 * Verify file type icon is visible against file
	 * 
	 * @param siteName
	 *        site name
	 * @param fileName
	 *        file name against which visibility of icon has to be verified
	 * @return true
	 *         if file type icon found
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 */
	public boolean verifyFileTypeIconIsPresent(String siteName, String fileName)
	{
		By fileIcon = By.xpath("//*[@class='postMeta ']//*[normalize-space(text())='" + siteName + "']//following::*[normalize-space(text())='" + fileName + "']/ancestor::div[2]//*[contains(@class,'imgIcon')]");
		return isDisplayed(fileIcon, Speed.slow);
	}

	/**
	 * Verify file download icon is visible against file
	 * 
	 * @param siteName
	 *        site name
	 * @param fileName
	 *        file name against which visibility of icon has to be verified
	 * @return true
	 *         if download icon found
	 * @author dheeraj.rajput
	 * @Created 22 May 2018
	 * @Updated
	 */
	public boolean verifyFileDownloadIconIsPresent(String siteName, String fileName)
	{
		By fileDownloadIcon = By.xpath("//*[@class='postMeta ']//*[normalize-space(text())='" + siteName + "']//following::*[normalize-space(text())='" + fileName + "']/../following-sibling::*[contains(@class,'icon-arrow-circle-down')]");
		return isDisplayed(fileDownloadIcon, Speed.slow);
	}

	/**
	 * Verify user name and email in user card
	 * 
	 * @param expectedUserName
	 *        expected user name
	 * @param expectedUserEmail
	 *        expected user email
	 * @return true
	 *         if username and email matches
	 * @author dheeraj.rajput
	 * @Created 23 May 2018
	 * @Updated
	 */
	public boolean verifyUserNameAndEmailInUserCard(String expectedUserName, String expectedUserEmail)
	{
		findVisibleElement(userInfoCard);
		String actualUserName = findVisibleElement(userCard_userName).getText().trim();
		String actualUserEmail = findVisibleElement(userCard_email).getText().trim();
		return actualUserName.equals(expectedUserName.trim()) && actualUserEmail.equals(expectedUserEmail.trim());
	}

	/**
	 * Close User card
	 * 
	 * @author dheeraj.rajput
	 * @Created 23 May 2018
	 * @Updated
	 */
	public void closeUserCard()
	{
		findVisibleElement(userInfoCard);
		findVisibleElement(userCard_closeIcon).click();
	}

	/**
	 * Click on post
	 * 
	 * @param post
	 *        name of the post
	 * @param siteName
	 *        site name
	 * @Created 23 May 2018
	 * @Updated
	 */
	public void clickOnPost(String post, String siteName)
	{
		By postXpath = By.xpath("(//*[@class='postMeta ']//*[@class='greyMeta']//*[normalize-space(text())='" + siteName + "']//following::*[contains(@class,'postData')]//*[normalize-space(text())='" + post + "'])[1]");
		findVisibleElement(postXpath, Speed.slow).click();
	}

	/**
	 * Download file by clicking on download icon besides file name
	 * 
	 * @param siteName
	 *        site name
	 * @param fileName
	 *        file name
	 * @author dheeraj.rajput
	 * @Created 23 May 2018
	 * @Updated
	 */
	@Override
	public void downloadFileByClickingOnDownloadIcon(String siteName, String fileName)
	{
		By fileDownloadIcon = By.xpath("//*[@class='postMeta ']//*[normalize-space(text())='" + siteName + "']//following::*[normalize-space(text())='" + fileName + "']/../following-sibling::*[contains(@class,'icon-arrow-circle-down')]");
		findVisibleElement(fileDownloadIcon).click();
		while (isDisplayed(downloading, Speed.slow))
		{
			;
		}
	}

	/**
	 * Click on file path link
	 * 
	 * @param siteName
	 *        site name in which file is located
	 * @param fileName
	 *        file name
	 * @author dheeraj.rajput
	 * @Created 23 May 2018
	 * @Updated
	 */
	@Override
	public DocumentPage clickOnFilePathLink(String siteName, String fileName)
	{
		By filepath = By.xpath("//*[@class='postMeta ']//*[normalize-space(text())='" + siteName + "']//following::*[normalize-space(text())='" + fileName + "']/../following-sibling::*[contains(@class,'greyMeta')]//*[starts-with(normalize-space(),'/')]");
		findVisibleElement(filepath, Speed.slow).click();
		return new DocumentWeb(driver);
	}

	/**
	 * Click on sitename of a post
	 * 
	 * @param post
	 *        post name
	 * @param siteName
	 *        name of site
	 * @author dheeraj.rajput
	 * @Created 23 May 2018
	 * @Updated
	 */
	@Override
	public void clickOnSiteNameOfAPost(String post, String siteName)
	{
		By siteNameLink = By.xpath("(//*[normalize-space(text())='" + post + "']/ancestor::*[contains(@class,'post')]//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName + "'])[1]");
		findVisibleElement(siteNameLink, Speed.slow).click();
	}

	/**
	 * Verify menu tabs in modal which is visible
	 * 
	 * @param menuTitle
	 *        title of the menu to verify
	 * @return true
	 *         if menu title found
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyTopMenuTabInOpenModal(String menuTitle)
	{
		findVisibleElement(By.xpath(modalBodyTopHeader),Speed.slow);
		By menu = By.xpath(modalBodyTopHeader + "//*[normalize-space(text())='" + menuTitle.trim() + "']");
		return isDisplayed(menu, Speed.slow);
	}

	/**
	 * Click menu tabs in modal which is visible
	 * 
	 * @param menuTitle
	 *        title of the menu to click
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	@Override
	public void clickOnTopMenuTabInOpenModal(String menuTitle)
	{
		By menu = By.xpath(modalBodyTopHeader + "//*[normalize-space(text())='" + menuTitle.trim() + "']");
		findVisibleElement(menu, Speed.slow).click();
		findVisibleElement(By.xpath(modalBodyTopHeader));
	}

	/**
	 * Verify Email tab label in share modal
	 * 
	 * @param labelName
	 *        name of the label
	 * @param greyMeta
	 *        grey meta to verify (if any)
	 * @return true
	 *         if title(or title with greyMeta) found
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyEmailTabLabelInShareModal(String labelName, String... greyMeta)
	{
		gotoEmailTab();
		String commonXpath = displayBlock + "//*[@id='shareModal_emailContainerDivID']//*[normalize-space(text())='" + labelName.trim() + "']";
		By emailLabel = By.xpath(commonXpath);
		By emailLabelWithGreyMeta = By.xpath(commonXpath + "//*[contains(@class,'greyMeta')]");
		if (!(greyMeta == null) && isDisplayed(emailLabelWithGreyMeta, Speed.slow))
		{
			for (int i = 0; i < greyMeta.length; i++)
			{
				String fetchedGreyMeta = findVisibleElement(emailLabelWithGreyMeta).getText().trim();
				if (!fetchedGreyMeta.contains(greyMeta[i].trim()))
					return false;
			}
			return true;
		}
		else
		{
			return isDisplayed(emailLabel, Speed.slow);
		}
	}

	/**
	 * Verify button is enabled or disabled in open modal
	 * 
	 * @param buttonText
	 *        button text of the button, of which state has to be verified
	 * @return true
	 *         if button is enabled
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyButtonIsEnabledInOpenModal(String buttonText)
	{
		By button = By.xpath(displayBlock + "//button[normalize-space(text())='" + buttonText + "']");
		return findVisibleElement(button).isEnabled();
	}

	/**
	 * Verify default subject in share modal
	 * 
	 * @param subjectToVerify
	 *        subject to verify
	 * @return true
	 *         default subject matches
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyDefaultSubjectInShareModal(String subjectToVerify)
	{
		String actualSubject = getText(share_Email_subjectInput);
		return actualSubject.equals(subjectToVerify.trim());
	}

	/**
	 * Clear subject in share modal
	 * 
	 * @author dheeraj.rajput
	 * @Created 24 May 2018
	 * @Updated
	 */
	public void clearSubjectInShareModal()
	{
		findVisibleElement(share_Email_subjectInput).clear();
	}

	/**
	 * Verify text visibility in open modal
	 * 
	 * @param expectedText
	 *        expected text to verify in open modal
	 * @return true
	 *         if text is visible
	 * @author dheeraj.rajput
	 * @Created 28 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyTextVisibilityInOpenModal(String expectedText)
	{
		By button = By.xpath(displayBlock + "//*[normalize-space(text())='" + expectedText + "']");
		return isDisplayed(button, Speed.slow);
	}

	/**
	 * Click on use short URL checkbox in Share modal
	 * 
	 * @param state
	 *        true -> check
	 *        false -> uncheck
	 * @author dheeraj.rajput
	 * @Created 28 May 2018
	 * @Updated
	 */
	@Override
	public void clickOnUseShortURLCheckboxInShareModal(boolean state)
	{
		setSelection(share_Link_UserShortURLCheckbox, state);
	}

	/**
	 * Verify generated url is short url
	 * 
	 * @return true
	 *         if url starts with hubuat prefix
	 * @author dheeraj.rajput
	 * @Created 28 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyGeneratedURLIsShortURLInShareModal()
	{
		String url = getText(share_Link_LinkInput);
		return url.startsWith(hubURLPrefix);
	}

	/**
	 * Verify default message in Message tab of share modal
	 * 
	 * @param messageToVerify
	 *        message to verify
	 * @return true
	 *         if default message matches
	 * @author dheeraj.rajput
	 * @Created 28 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyDefaultMessageInMessageTabOfShareModal(String messageToVerify)
	{
		gotoMessageTab();
		String actualMessage = getText(share_Message_messageInput);
		return actualMessage.equals(messageToVerify.trim());
	}

	/**
	 * verify more action option of post
	 * 
	 * @param option
	 *        more action option to verify
	 * @return true
	 *         if option is visible
	 * @author dheeraj.rajput
	 * @Created 29 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyOptionInMoreActionOfPost(String option)
	{
		By moreActionOption = By.xpath("//*[contains(@class,'inlineBlock open')]//*[normalize-space(text())='" + option.trim() + "']");
		return isDisplayed(moreActionOption, Speed.slow);
	}

	/**
	 * Verify you like this message for a post
	 * 
	 * @param userName
	 *        user name of the owner
	 * @param siteName
	 *        site name of the post
	 * @param post
	 *        post text
	 * @param messageToVerify
	 *        message to verify
	 * @return true
	 *         if message found
	 * @author dheeraj.rajput
	 * @Created 30 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyYouLikeThisMessageOfAPost(String userName, String siteName, String post, String messageToVerify)
	{
		By youLikeThisXpath = By.xpath("(//*[normalize-space(text())='" + userName + "']/ancestor::*[normalize-space(@class)='postMeta']//*[normalize-space(text())='" + siteName + "']/following::*[@class='postContent']//*[normalize-space(text())='" + post + "']/ancestor::*[contains(@class,'postSection')]//*[contains(@id,'commentSec')]//*[@id='contentLike_'])[1]");
		WebElement youLikeThisElem = findVisibleElement(youLikeThisXpath);
		String actualMessage = youLikeThisElem.getText().trim();
		String[] expectedMsg = messageToVerify.split(" ");
		for (int i = 0; i < expectedMsg.length; i++)
		{
			if (!actualMessage.contains(expectedMsg[i].trim()))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify unlike link is present for a post
	 * 
	 * @param userName
	 *        user name of the owner
	 * @param siteName
	 *        site name of the post
	 * @param post
	 *        post text
	 * @return true
	 *         if unlike link is found
	 * @author dheeraj.rajput
	 * @Created 30 May 2018
	 * @Updated
	 */
	@Override
	public boolean verifyUnlikeLinkOfAPost(String userName, String siteName, String post)
	{
		By unlikeLink = By.xpath("(//*[normalize-space(text())='" + userName + "']/ancestor::*[normalize-space(@class)='postMeta']//*[normalize-space(text())='" + siteName + "']/following::*[@class='postContent']//*[normalize-space(text())='" + post + "']/ancestor::*[contains(@class,'postSection')]//*[normalize-space(text())='Unlike'])[1]");
		return isDisplayed(unlikeLink, Speed.slow);
	}

	/**
	 * Verify post in recent activity
	 * 
	 * @param siteName
	 *        name of the site of which post has to be verified
	 * @param post
	 *        post content
	 * @return true
	 *         if post found
	 * @author dheeraj.rajput
	 * @Created 01 June 2018
	 * @Updated
	 */
	@Override
	public boolean verifyPostInRecentActivity(String siteName, String post)
	{
		return isDisplayed(By.xpath("(//*[contains(@class,'post')]//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='"+siteName+"']/ancestor::*[contains(@class,'postSection')]//*[normalize-space(text())='"+post+"'])[1]"), Speed.slow);
	}

	/**
	 * Verify all filter are cleared
	 * 
	 * @return true
	 *         if all the filters are cleared
	 * @author dheeraj.rajput
	 * @Created 01 June 2018
	 * @Updated
	 */
	public boolean verifyAllFiltersCleared()
	{
		By allCheckbox = By.xpath(filter_allCheckboxes);
		By allInputs = By.xpath(filter_allInput);
		for (WebElement checkbox : driver.findElements(allCheckbox))
		{
			if (checkbox.isSelected())
			{
				return false;
			}
		}

		for (WebElement input : driver.findElements(allInputs))
		{
			if (!input.getText().isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Click on first user card link in recent activity middle panel
	 * 
	 * @author dheeraj.rajput
	 * @Created 01 June 2018
	 * @Updated
	 */
	public void clickOnFirstUserCardInRecentActivityMiddlePanel()
	{
		WebElement userCard = findVisibleElement(middlePanel_firstUserCard, Speed.slow);
		userCard.click();
	}

	/**
	 * Verify post owner is same for all middle panel posts
	 * 
	 * @param userEmail
	 *        email of the user
	 * @return true
	 *         if all the posts have same owner
	 * @author dheeraj.rajput
	 * @Created 01 June 2018
	 * @Updated
	 */
	public boolean verifyPostOwnerIsSameForAllMiddlePanelPosts(String userEmail)
	{
		By userCards = By.xpath(middlePanel_allPostMetaUserCards);
		for (WebElement userCard : driver.findElements(userCards))
		{
			if (!userCard.getText().trim().equals(getUserData(userEmail)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify people who like this modal is visible and it contains users
	 * 
	 * @return true
	 *         if modal is visible and user count >1
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public boolean verifyPeopleWhoLikeThisModalAndUserList()
	{
		int userCount = driver.findElements(peopleWhoLikeThis_userList).size();
		return isDisplayed(contentLikeModal, Speed.slow) && userCount >= 1;
	}

	/**
	 * Send text in reply comment box
	 * 
	 * @param post
	 *        content of the post
	 * @param siteName
	 *        name of the site
	 * @param comment
	 *        comment to be sent
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public void sendTextInReplyCommentBox(String post, String siteName, String comment)
	{
		By inputBox = By.xpath("(//*[normalize-space(text())='" + post + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName + "']/ancestor::*[@class='panel postSection']//*[normalize-space(@class)='commentMeta']//*[starts-with(@id,'addCommentField') and @role='textbox'])[last()]");
		findVisibleElement(inputBox).click();
		findVisibleElement(inputBox).sendKeys(comment);
	}

	/**
	 * Verify User name in comment input box
	 * 
	 * @param post
	 *        content of the post
	 * @param siteName
	 *        name of the site
	 * @param users
	 *        userName(s)(Variable argument)
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public boolean verifyUserNameInCommentMentionsInput(String post, String siteName, String... users)
	{
		String userList = "(//*[normalize-space(text())='" + post + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName + "']/ancestor::*[@class='panel postSection']//*[normalize-space(@class)='commentMeta']//*[starts-with(@id,'addCommentField')]//*[contains(@class,'CKContextMention')])";
		int size = driver.findElements(By.xpath(userList)).size();
		for (int i = 0; i < size; i++)
		{
			String userName = getText((By.xpath(userList + "[" + (i + 1) + "]")));
			if (!userName.equals(users[i].trim()))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Get author of a comment
	 * 
	 * @param post
	 *        content of the post
	 * @param siteName
	 *        name of the site
	 * @param comment
	 *        comment of which user has to be fetched
	 * @return author name
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public String getAuthorOfAComment(String post, String siteName, String comment)
	{
		By userNameXpath = By.xpath("(//*[normalize-space(text())='" + post + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//*[normalize-space(text())='" + siteName + "']/ancestor::*[contains(@class,'postSection')]//*[normalize-space(@class)='commentMeta']//*[normalize-space(text())='" + comment + "']/../../preceding-sibling::*[normalize-space(@class)='usercardLink'])");
		return findVisibleElement(userNameXpath, Speed.slow).getText().trim();
	}

	/**
	 * Verify comment which has user mentioned in it
	 * 
	 * @param post
	 *        content of the post
	 * @param siteName
	 *        name of the site
	 * @param expectedComment
	 *        comment to verify
	 * @return true
	 *         if expectedComment == actualComment
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public boolean verifyCommentWithUserMetionedInIt(String post, String siteName, String expectedComment)
	{
		By commentXpath = By.xpath("//*[normalize-space(text())='" + post + "']/ancestor::*[@class='postContent']//preceding-sibling::*[@class='clearfix']//a[normalize-space(text())='" + siteName + "']/ancestor::*[@class='panel postSection']//*[@class='commentSec']//*[@id='commentMeta']//*[contains(@id,'CKContextMention')]/..");
		String actualComment = findVisibleElement(commentXpath).getText().trim();
		return actualComment.equals(expectedComment.trim());
	}

	/**
	 * Get middle panel activity count
	 * 
	 * @return count
	 *         of visible activity
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public int getMiddlePanelActivityCount()
	{
		findVisibleElement(recentActivity_All, Speed.slow);
		return driver.findElements(visibleActivities).size();
	}

	/**
	 * Click on load more button in middlePanel
	 * 
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 * 			Note: Load more button will only be displayed if total activities are 20 or more
	 */
	public void clickLoadMoreButtonInMiddlePanel()
	{
		if (verifyLoadMoreButtonVisibility())
		{
			findVisibleElement(middlePanel_loadMoreButton).click();
		}
	}

	/**
	 * Verify load more button is displayed or not
	 * 
	 * @return true
	 *         if load more button is displayed
	 * @author dheeraj.rajput
	 * @Created 04 June 2018
	 * @Updated
	 */
	public boolean verifyLoadMoreButtonVisibility()
	{
		scrollToBottom();
		return isDisplayed(middlePanel_loadMoreButton, Speed.slow);
	}

	/**
	 * Verify blogName in left panel
	 * 
	 * @param blogName
	 *        expected blog name
	 * @return true
	 *         if expected blog is found
	 * @author dheeraj.rajput
	 * @Created 05 June 2018
	 * @Updated
	 */
	public boolean verifyBlogInLeftPanel(String blogName)
	{
		findVisibleElement(activity_leftPanel);
		By blogXpath = By.xpath("//*[@id='siteActivtyPage_leftPane']//*[contains(@class,'blogList')]//*[normalize-space(text())='" + blogName + "']");
		return isDisplayed(blogXpath);
	}

	/**
	 * Click on blog in legta panel
	 * 
	 * @param blogName
	 *        expected blog name
	 * @return BlogWeb
	 * @author dheeraj.rajput
	 * @Created 05 June 2018
	 * @Updated
	 */
	public BlogPage clickOnBlogInLeftPanel(String blogName)
	{
		findVisibleElement(activity_leftPanel);
		By blogXpath = By.xpath("//*[@id='siteActivtyPage_leftPane']//*[contains(@class,'blogList')]//*[normalize-space(text())='" + blogName + "']");
		findVisibleElement(blogXpath).click();
		findVisibleElement(blogModule_middlePanel);
		return new BlogWeb(driver);
	}

	/**
	 * Get blog metadata from right panel
	 * 
	 * @param blogName
	 *        expected blog name
	 * @return metadata
	 *         String containing blog metadata(i.e author,posted date,etc)
	 * @author dheeraj.rajput
	 * @Created 05 June 2018
	 * @Updated
	 */
	public String getBlogMetaFromRightPanel(String blogName)
	{
		By blogMetaXpath = By.xpath("//*[@id='siteActivtyPage_leftPane']//*[contains(@class,'blogList')]//*[normalize-space(text())='" + blogName + "']/following-sibling::*[1]");
		return findVisibleElement(blogMetaXpath).getText().trim();
	}
}
