/**
 * 
 */
package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;

/**
 * @author vivek.mishra
 */
public interface BlogPage extends BannerPage
{

	public void clickOnAddPlusSign();

	public boolean verifyFavouritesLink();

	public void clickOnHide();

	public int getCategoryCount(String category);

	public void sendTextInBlogTitle(String blogName);

	public void clickOnSaveOnAddBlog();

	public void clickOnCancelOnAddBlog();

	public void clickOnTabsInBlogModule(String tabName);

	public void addBlogContent(String blogContent);

	public void addBlogTag(String tag);

	public void addBlogTags(List<String> tags);

	public void clickOnNotifications();

	public void setNotifications(String notificationPreference);

	public void clickOnCategories();

	public void selectCategory(String category);

	public void clickOnStatus();

	public void setStatus(String status);

	public void clickOnBrowseInAttachments();

	public void clickOnAddAttachmentsRecentTab();

	public void clickOnSiteInRecentTab();

	public void selectSiteInRecentTab(String siteName) throws InterruptedException;

	public void attachFileInAddBlobAttachmentTab(String fileName);

	public boolean verifySearchresultFound();

	public int verifyDefaultCategoryCount(String categoryName);

	public void addBlog(Map<String, String> blogData);

	public void clickonTabInAddAttachmentModal(String tabName);

	public void clickOnModalQuitButton();

	public void selectTypeInInsertListModal(String option);

	public void sendTextInTitleInInsertListModal(String title);

	public void sendTextInSearchTextBoxInSiteDropDown(String text);

	public void selectSiteInSiteDropDownInInsertListModal(String siteName);

	public void clcikOnSiteDropDownInInsertListModal();

	public void selectItemsInToDisplayDropDownInInsertListModal(String count);

	public void clickOnMetaDataDropDownInInsertListModal();

	public void selectMetaDataInInsertListModal(String metadata);

	public void selectMetaDataInInsertListModal(String metadata[]);

	public void clickOnCancelButtonInInsertListModal();

	public void clickOnInsertButtonInInsertListModal();

	public void clickOnListButtonInAddBlog();

	public void addList(Map<String, String> blogData);

	public boolean verifyGlobalMessage();

	public boolean verifyEneterTitleMessage();

	public boolean verifyEneterCategoryMessage();

	public boolean verifyEneterContentMessage();

	public void clickOnReadMoreButton();

	public boolean verifyAutoSaveDraftModal();

	public void clickOnDiscardButtonInAutoSaveDraftModal();

	public void clickOnSaveButtonInAutoSaveDraftModal();

	public void clickOnCancelButtonInHeaderImageModal();

	public void clickOnSaveButtonInHeaderImageModal();

	public boolean verifyHeaderImageModal();

	public void clickOnInsertButtonInAddPostSettingsTab();

	public void AttachFileInAddBlobSettingsTabHeaderImageModal(String fileName);

	public boolean verifyBlogEditPage();

	public void clickOnBlogInBlogList(String blogName);

	public boolean verifyTopRecentBlog(String blogName);

	public boolean verifyBlogInBlogList(String blogName);

	public boolean VerifyBlogTitleInDetailsSection(String blogName);

	public boolean VerifyBlogCategoryInDetailsSection(String category);

	public boolean VerifyBlogTagInDetailsSection(String tagName);

	public boolean VerifyAttachedFileInDetailsSection(String fileName);

	public boolean verifyHeaderImageAvailabilityInDetailsSection();

	public void clickOnAddPostButton();

	public void selectOptionInPublishDateDropDown(String option);

	public void sendTimeInPublishDateTimeTextBox(String time);

	public void sendDateInPublishDateDateTextBox(String date);

	public void clickOnOptionInLeftPannel(String option);

	public boolean verifyScheduledPostsPage();

	public boolean verifyBlogInScheduledPosts(String blogName);

	public void clickOnMinuteIncrementButton();

	public boolean verifyTimePickerModal();

	public void clickOnWatchIconInEditSection();

	public void clickOnOptionInMoreActionInBlogList(String blogName, String option);

	public void clickOnMoreActionInBlogList(String blogName);

	public void editAttachedFileInAddBlobAttachmentTab(String oldFile, String newFile);

	public void editCategory(String oldCategory, String newCategory);

	public void editBlogTag(String oldTag, String newTag);

	public void appendBlogContent(String blogContent);

	public boolean verifyBlogContent(String content);

	public String getTextFromTitleTextBox();

	public void clickOnOptionInMoreActionInDetailsSection(String option);

	public void clickOnMoreActionInDetailSection();

	public boolean verifyDeletePostMessage();

	public void cancelDeleteBlogProcess();

	public void deleteBlog();

	public boolean isBlogDeleted(String blogName);

	public boolean verifyDeleteButtonInMyDrafts();

	public boolean verifyBlogInMyDrafts(String blogName);

	public boolean verifyMoreAction();

	public void waitForAutoSaveNotification();

	public boolean verifyModal(String modalName);

	public boolean verifyModal();

	public boolean verifyModalMessage(String message);

	public boolean verifyResumeDiscardYelloStripMessage(String message);

	public void clickOnDiscardInYellowStrip();

	public void clickOnResumeInYellowStrip();

	public void clickOnDeleteIconOfBlogInMyDrafts(String blogName);

	public boolean verifyModalButton(String buttonName);

	public void clickOnModalButton(String buttonName);

	public boolean verifySearchTextBoxIsEmpty();

	public void sendTextInSearchTextBox(String text);

	public boolean verifyBlogListInMyDrafts();

	public boolean verifyFavouriteIconIsSelected(String blogName);

	public void addBlogToFavourites(String blogName);

	public void removeBlogFromFavourites(String blogName);

	public void addBlogData(Map<String, String> blogData);

	public boolean verifyArchiveLabel();

	public void clickOnArchiveMonth(String month);

	public boolean verifyCkEditor();

	public boolean verifySettingsTabContainer();

	public boolean verifyAttachmentsTabContainer();

	public void sendTextInBlogCommentBox(String text);

	public void clickOnPostCommentButton();

	public void clickOnCancelCommentButton();

	public void clickOnCommentBlogButton();

	public void clickOnLikeUnlikeBlogButton();

	public int getCommentCount();

	public boolean verifyCommentIconInDetailSection();

	public boolean verifyCommentBlock(String comment);

	public boolean verifyCommentOptionInDetailsSection(String comment, String option);

	public void clickOnCommentOptionInDetailsSection(String comment, String option);

	public boolean verifyComment(String comment);

	public boolean verifyBlogLikeUnlikeStatus(String status);

	public int getBlogLikeCount();

	public int getCommentLikeCount(String comment);

	public void appendTextInBlogCommentBox(String text);

	public String getLastReplyOfCommentInDetailsSection();

	public String getCommentBoxRenderedText();

	public String getBlogLikeMessage();

	public boolean verifyBlogCommentInPrintPage(String comment);

	public boolean verifyBlogInPrintPage(String blogName);

	public boolean verifyPrintAndExportModal();

	public void selectIncludeCommentsCheckBox();

	public void waitForFileGettingDownloaded();
	
	public String getBlogMetaFromDetailsMiddlePanel(String blogName);
}
