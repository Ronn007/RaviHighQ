package com.highq.pageobjects.base;

import java.text.ParseException;

public interface ActivityPage extends BannerPage
{

	public void gotoPosts();

	public boolean verifyGrayMetaSiteName(String siteName);

	public void openFirstPostLink();

	public void closeCurrentWindow();

	public void clickOnAssignedToMe();

	public void clickOnAllTasksLabel();

	public void clickOnClearFiltersInFilterDropDown();

	public void selectSearchedIdCheckBoxInPeopleInFilterDropDown(String mailId);

	public void sendTextInSearchTextBoxInPeopleInFilterDropDown(String mailId);

	public void selectAllOfTheAboveCheckBoxInPeopleInFilterDropDown();

	public void clickOnPeopleInFilterDropDown();

	public void selectCheckBoxInContentType(boolean state, String... data);

	public void clickOnContentTypeInFilterDropDown();

	public void clickOnFilterDropDown();

	public void clickOnActivityLabel();

	public boolean verifyEventArea();

	public boolean verifyTaskArea();

	public boolean verifyRecentActivityArea();

	public boolean verifyBlogArea();

	public void clickOnCommentAttachmentLink();

	public boolean verifyAttachmentModal();

	public void clickOnTabOnHeaderInAddAttachmentModal(String tabName);

	public void clickOnSiteDropDownInAddAttachmentModal();

	public void sendTextInSearchTextBoxInAddAttachmentModal(String siteName);

	public void selectSiteInAddAttachmentModal(String siteName);

	public void clickOnPostLink(String siteName, String post);

	public void clickOnRecentActivityTabs(String tabName);

	public boolean verifyFilterDropDownOpen();

	public void expandArrowsInFilterDropDown(String arrowName);

	public void collapseArrowsInFilterDropDown(String arrowName);

	public boolean verifyExpansionArrowStatusInFilterDropDown(String arrowName);

	public boolean verifyOptionsInFilterDropDown(String option);

	public boolean verifySiteListInFilterDropDown();

	public boolean verifyUserListInFilterDropDown();

	public boolean verifyActivityTabInRecentActivity();

	public boolean verifyUploadedFile(String siteName, String fileName);

	public boolean verifyFileSize(String siteName, String fileName) throws ParseException;

	public boolean verifyUserName(String siteName, String postName, String userName);

	public void sendTextInMicroblogTextBox(String text);

	public void clickOnMicroblogTextBox();

	public void removeLabelFromShareWithTextBox(String labelName);

	public void sendTextInShareWithTextBox(String text);

	public void clickOnMicroBlogCancelButton();

	public void clickOnMicroBlogPostButton();

	public void selectSiteNameFromAutoSuggest(String siteName);

	public boolean verifyPost(String siteName, String post);

	public boolean verifySiteActivityMiddlePannel();

	public void clickOnInsertLinkButton();

	public void clickOnAttachmentButton();

	public void clickOnTextFormatButton();

	public boolean verifyModal();

	public boolean verifyTabsInInsertLinkModal(String tabName);

	public void clickOnTabsInInsertLinkModal(String tabName);

	public boolean verifySearchTabOpenedInInsertLinkModal();

	public boolean verifyUploadTabOpenedInInsertLinkModal();

	public boolean verifyExternalTabOpenedInInsertLinkModal();

	public boolean verifyBrowseTabOpenedInInsertLinkModal();

	public boolean verifyRecentTabOpenedInInsertLinkModal();

	public void selectShortURLCheckBox();

	public void sendTextInExternalURLTextBox(String URL);

	public boolean verifyShortURLCheckBox();

	public boolean verifyExternalURLTextBox();

	public void clickOnModalButton(String buttonName);

	public void attachFileInInsertLinkModal(String fileName);

	public boolean verifyLabelInShareWithTextBox(String labelName);

	public void clickOnMoreActionOfPost(String siteName, String post);

	public void clickOnOptionInMoreActionOfPost(String siteName, String post, String option);

	public boolean verifyModal(String modalName);

	public boolean verifyModalButton(String buttonName);

	public boolean verifyModalMessage(String expectedMessage);

	public void clickOnLikeButtonOfPost(String post, String siteName);

	public void clickOnCommentButtonOfPost(String post, String siteName);

	public void sendCommentInPostCommentBox(String post, String siteName, String comment);

	public void clickOnCommentCancelButton();

	public void clickOnCommentPostButton();

	public boolean verifyCommentSectionOfPost(String post, String siteName);

	public boolean verifyComment(String post, String siteName, String comment);

	public void clickOnOptionOfCommentOfPost(String post, String siteName, String comment, String option);

	public int getCommentCountOfPost(String post, String siteName);

	public boolean verifyPostFooterLabels(String post, String siteName, String labelName);

	public boolean verifyCommentBoxFooterMessage(String post, String siteName, String message);

	public boolean verifyTextFormaterIconOfCommentBoxOfPost(String post, String siteName);

	public boolean verifyLinkIconOfCommentBoxOfPost(String post, String siteName);

	public boolean verifyAttachmentIconOfCommentBoxOfPost(String post, String siteName);

	public boolean verifyCommentPostButton();

	public boolean verifyCommentCancelButton();

	public void clickOnTextFormaterIconOfCommentBoxOfPost(String post, String siteName);

	public void clickOnLinkIconOfCommentBoxOfPost(String post, String siteName);

	public void clickOnAttachmentIconOfCommentBoxOfPost(String post, String siteName);

	public void clickOnAttachmentModalTab(String tabName);

	public boolean verifyUploadTabOpenedInAddAttachmentsModal();

	public void attachFileInUploadTabInAddAttachmentsModal(String fileName);

	public void clickOnRemoveButtonOfFileOfComment(String fileName);

	public boolean verifyAttachedFileToComment(String post, String siteName, String comment, String fileName);

	public void editCommentBoxText(String post, String siteName, String oldComment, String newComment);

	public boolean verifyPostDueDate(String post, String siteName, String dueDate);

	public void clickOnBlogPost(String postName, String siteName);

	public void openFirstPostReceivedLink();

	boolean verifyMicroblogPost(String post);

	public void gotoRePost();

	public void shareViaRePostWithAttachment(String message, String sitename, String filename);

	public void shareViaRePostWithRecentAttachment(String message, String sitename, String fileName);

	public boolean verifyMicroblogMessage(String post);

	public boolean verifyShareModalVisibility();

	public boolean verifyInsertLinkButton();

	public boolean verifyAttachmentButton();

	public boolean verifyTextFormatButton();

	public boolean verifySiteFromAutoSuggestedList(String sitename);

	public boolean verifyDefaultShareWith(String defaultShareWith);
	
	public boolean verifyNewlyCreatedTaskInTasksPanel(String taskName);

	public TasksPage clickOnTaskNameInTasksPanel(String taskName);

	public TasksPage clickOnSeeAllTasks();

	public boolean verifyEventTitleInRightPanel(String eventTitle);

	public boolean verifyEventGreyMetaInRightPanel(String eventTitle, String... greyMeta);

	public EventsPage clickOnEventInRightPanel(String eventTitle);

	public boolean verifyTagCloudIsPresent();

	public boolean verifyTagInLeftPanel(String tag);

	public SearchContentPage clickOnTagInLeftPanel(String tag);

	public boolean verifyFileSize(String siteName, String fileName, double size);

	public boolean verifyFileTypeIconIsPresent(String siteName, String fileName);

	public boolean verifyFileDownloadIconIsPresent(String siteName, String fileName);

	public boolean verifyUserNameAndEmailInUserCard(String expectedUserName, String expectedUserEmail);

	public void clickOnAuthorName(String post, String siteName);

	public void closeUserCard();

	public void clickOnPost(String post, String siteName);

	public void downloadFileByClickingOnDownloadIcon(String siteName, String fileName);

	public DocumentPage clickOnFilePathLink(String siteName, String fileName);

	public void clickOnSiteNameOfAPost(String post, String siteName);

	public boolean verifyTopMenuTabInOpenModal(String menuTitle);

	public void clickOnTopMenuTabInOpenModal(String menuTitle);

	public boolean verifyEmailTabLabelInShareModal(String labelName, String... greyMeta);

	public boolean verifyButtonIsEnabledInOpenModal(String buttonText);

	public boolean verifyDefaultSubjectInShareModal(String subjectToVerify);

	public void clearSubjectInShareModal();

	public boolean verifyTextVisibilityInOpenModal(String expectedText);

	public void clickOnUseShortURLCheckboxInShareModal(boolean state);

	public boolean verifyGeneratedURLIsShortURLInShareModal();

	public boolean verifyDefaultMessageInMessageTabOfShareModal(String messageToVerify);

	public boolean verifyOperationOnCommentOfPost(String post, String siteName, String comment, String option);

	public void clickOnCommentLikeCount(String post, String siteName, String comment, String option);

	public boolean verifyPeopleWhoLikeCommentModal();

	public void clickOnCloseLikeModal();

	public int getCommentLikeCount(String post, String siteName, String comment, String option);

	public boolean verifyOptionInMoreActionOfPost(String option);

	public boolean verifyYouLikeThisMessageOfAPost(String userName, String siteName, String post, String messageToVerify);

	public boolean verifyUnlikeLinkOfAPost(String userName, String siteName, String post);

	public boolean verifyPostInRecentActivity(String siteName, String post);

	public boolean verifyAllFiltersCleared();

	public void clickOnFirstUserCardInRecentActivityMiddlePanel();

	public boolean verifyPostOwnerIsSameForAllMiddlePanelPosts(String userEmail);

	public boolean verifyPeopleWhoLikeThisModalAndUserList();

	public void sendTextInReplyCommentBox(String post, String siteName, String comment);
	
	public boolean verifyUserNameInCommentMentionsInput(String post, String siteName, String... users);
	
	public String getAuthorOfAComment(String post, String siteName, String comment);
	
	public boolean verifyCommentWithUserMetionedInIt(String post, String siteName, String expectedComment);
	
	public int getMiddlePanelActivityCount();
	
	public void clickLoadMoreButtonInMiddlePanel();
	
	public boolean verifyLoadMoreButtonVisibility();
	
	public boolean verifyBlogInLeftPanel(String blogName);
	
	public BlogPage clickOnBlogInLeftPanel(String blogName);
	
	public String getBlogMetaFromRightPanel(String blogName);
}
