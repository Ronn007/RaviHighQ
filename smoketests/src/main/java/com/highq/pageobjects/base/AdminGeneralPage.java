package com.highq.pageobjects.base;

public interface AdminGeneralPage extends BannerPage
{
	public void setSiteName(String siteName);

	public void setSiteDescription(String siteDescription);

	public void setSiteAdminNotes(String siteAdminNotes);

	public void setSiteCategory(String category);

	public void setClientNumber(String clientNumber);

	public void setMatterNumber(String matterNumber);

	public void uploadSiteLogo(String filePath);

	public void clickOnSave();

	public void setStartDate(String startDate);

	public void setEndDate(String endDate);

	// ***************************************** Category *****************************************

	public void clickOnAddNewCategory();

	public void clickSaveOnAddSiteCategory();

	public void clickCancelOnAddSiteCategory();

	public void addNewCategory(String category);

	// ***************************************** Type *****************************************

	public void selectSiteType(String siteType);

	public void selectStatus(String status);

	public void selectCategories(String... categories);

	public void selectContactUsType(String type);

	public void setContactUsURL(String url);

	public void setSiteOwner(String ownerName);

	public void setSiteOwnerFirstName(String firstName);

	public void setSiteOwnerLastName(String lastName);

	public void setSiteOwnerEmailAddress(String email);

	void clickonOkonDocRevokePopUp();

	void clickOnContactUsSearchIcon();

	void clickOnAdminUser();

	void clickOncancelContactUsAdminUser();

	void clearAllContactUs();

	void clearAllSiteOwner();

	public void selectAllAdminUserContactUs();

	public void clearAllAdminUserContactUs();

	void setContactUsEmailAddress(String email);

	public boolean verifyemailURL_contactusLink(String email);

	public boolean verifyemailURL_contactusLinkUserName(String username);

	public boolean verifyUsersinContachUsModel(String userName, String email);

	public boolean verifyContactUsLinkFooter();

	public boolean verifySiteOwner(String username);

	public boolean verifyAddButonAddUser();

	public void clickOnFooterContactUslink();

}
