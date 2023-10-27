package com.highq.pageobjects.pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.highq.base.TestBaseSetup;
import com.highq.labels.collaborate.BannerLabels;
import com.highq.labels.collaborate.DashboardLabels;
import com.highq.pageobjects.base.DashboardPage;

public class DashboardWeb extends BannerPageWeb implements DashboardPage
{
	/************ Menu panel *************/
	By homeButton = By.xpath("//a[@data-original-title='Dashboard']");
	By instanceNameText = By.xpath(".//*[@class='mainNav']//*[@class='NavtextOuter']//span");
	By searchSiteButton = By.id("dashboardLeftPaneSearchIcon");
	By searchSiteTextBox = By.id("dashboardSiteList_SiteSearchInput");

	/******* Left panel *******/
	By dashBoaradTitleText = By.className("dashTitle clearfix");
	By leftPanelSearchIconLink = By.id("dashboardLeftPaneSearchIcon");
	By addSiteLink = By.xpath("//a[@data-original-title='Add site']");
	By recentTabLink = By.xpath("//li[@id='dashboardSiteList_lnkRecentItems']/a");
	By favouritesLink = By.xpath("//li[@id='dashboardSiteList_lnkFavouriteItems']/a");
	By moreOptions = By.xpath(".//*[text()='" + DashboardLabels.DASHBOARD_LEFTPANEL_MOREOPTION + "']");
	By categoriesLink = By.xpath("//li[@id='dashboardSiteList_lnkCagetoriesItems']/a");

	By createSiteTabTitle = By.id("CREATE_SITE_MODAL_TITLE");
	By siteNameText = By.xpath(".//*[@id='CREATE_SITE_MODAL' and @aria-hidden='false']//label[normalize-space(.)='" + DashboardLabels.DASHBOARD_SITENAME + "']");
	By createNewXpathInputBox = By.id("createSiteModal_siteName");
	By createSiteSaveButton = By.id("CREATE_SITE_MODAL_saveButton");
	By searchSiteList = By.xpath(".//*[@id='dashboardLeftPaneSiteListContainer']/ul");

	/**** Middle panel *******/
	By recentActivityText = By.xpath(".//*[@id='collaborateMainContainer']/div[contains(@class,'dashMiddle')]/div[contains(@class,'dashTitle')]");
	By allTabLink = By.xpath(".//*[@id='dashboardMiddle_allFilter']/a");
	By postsTabLink = By.xpath(".//*[@id='dashboardMiddle_postFilter']/a");
	By activityTabLink = By.xpath(".//*[@id='dashboardMiddle_systemUpdateFilter']/a");
	By filterTabLink = By.xpath(".//*[@id='dashboardMiddle_filters']/a");

	/********** Microblog write post *********/
	By postTextArea = By.id("dashboardMiddle_microblogCkDiv");
	By postNoteText = By.xpath("//div[@id='dashboardMiddle_microblogDetailID']/span");
	By shareWithText = By.xpath(".//*[@id='dashboardMiddle_microblogDetailID']/strong");
	By shareWithInputBox = By.id("dashboardMiddle_microblogShareUpdate-tokenfield");
	By postLink = By.xpath(".//*[@id='dashboardMiddle_microblogDetailID']//a[@data-original-title='Link']");
	By postAttachment = By.xpath(".//*[@id='dashboardMiddle_microblogDetailID']//a[@data-original-title='Attachment']");
	By postTextFormatting = By.xpath(".//*[@id='dashboardMiddle_microblogDetailID']//a[@data-original-title='Text formatting']");
	By postCancellButton = By.xpath(".//*[@id='dashboardMiddle_microblogDetailID']//button[contains(@class,'btn-cancel')]");
	By postButton = By.id("dashboardMiddle_microblog_PostBtn");

	/********* Right panel *********/
	By rightPanelPersonalTaskList = By.id("dashboardRightPanePersonalTaskList");
	By rightPanelAssignToMeTaskList = By.id("dashboardRightPaneAssignToMeTaskList");
	By rightPanelAddTaskSymbol = By.id("dashboardAddTaskButtonSymbol");

	/******* My Files Tab *************/
	By myFilesHeadingText = By.xpath(".//*[@id='collaborateMainContainer']//div[contains(@class,'dashRight')]/div[contains(@class,'dashTitle')]");
	By myFilesReceivedTabLink = By.xpath(".//*[@id='dashboardRightPaneMyReceivedFiles']/a");
	By myFilesSharedTabLink = By.xpath(".//*[@id='dashboardRightPaneMySharedFiles']/a");
	By myFilesSeeAllFilesLink = By.xpath("//a[text()='" + DashboardLabels.DASHBOARD_MYFILES_SEEALLFILES + "']");
	By sendAFileText = By.xpath("//button[text()='" + DashboardLabels.DASHBOARD_MYFILES_SENDAFILE + "']");
	By filesNotPresentText = By.xpath(".//*[@id='dashboardRightPaneMyFilesItems']/ul/div");

	/****** Tasks tab *********/
	By taskHeadingText = By.xpath(".//*[@id='collaborateMainContainer']//div[normalize-space(.)='" + DashboardLabels.DASHBOARD_TASKS_HEADING + "']");
	By addTaskLink = By.id("dashboardAddTaskButtonSymbol");
	By pesronalTabLink = By.xpath(".//*[@id='dashboardRightPanePersonalTaskList']/a");
	By assignedToMe = By.xpath(".//*[@id='dashboardRightPaneAssignToMeTaskList']/a");
	By tasksNotPresentText = By.xpath(".//*[@id='dashBoardRightPaneTaskList']/div");
	By noSitesFound = By.xpath("//*[normalize-space(text())='" + DashboardLabels.DASHBOARD_TASKS_NOSITESFOUND + "']");

	By createSiteModalOpened = By.xpath(".//*[@id='CREATE_SITE_MODAL' and contains(@style,'display: block')]");
	By createSiteModalClosed = By.xpath(".//*[@id='CREATE_SITE_MODAL' and contains(@style,'display: none')]");
	By closeSendFileIcon = By.id("QUICK_FILE_SHARE_MAIN_CLOSE_BUTTON");
	By createSiteModalTemplateDropDown = By.id("createSiteModal_selectTemplateSiteID");
	By createSiteDescriptionInput = By.id("createSiteModal_description");
	By createSiteLandingPageDropDown = By.id("createSiteModal_siteHomePageModuleAlias");
	By createSiteStartDateInput = By.id("createSiteModal_siteStartDate");
	By createSiteEndDateInput = By.id("createSiteModal_siteExpiryDate");
	By createSiteMatterTypeInput = By.id("createSiteModal_matterSiteType");
	By createSiteServiceTypeInput = By.id("createSiteModal_serviceSiteType");
	By createSiteClientNumberInput = By.id("createSiteModal_clientNo");
	By createSiteMatterNumberInput = By.id("createSiteModal_matterNumber");
	By createSiteCategoryDropDown = By.id("createSiteModal_categoryID");

	By addNewCategory = By.xpath(".//*[normalize-space(text())='" + DashboardLabels.DASHBOARD_ADDNEWCATEGORY + "']");
	By addCategoryNameInput = By.id("createSiteModal_addNewSiteCategory");
	By submitCategoryButton = By.xpath(".//*[@class='btn-sm edit-submit']");

	By createSiteLogoInput = By.id("createSiteModal_fileToUpload");
	By createSiteAdminNotesInput = By.id("createSiteModal_purposeOfSite");

	By enableSiteModuleDropDown = By.id("createSiteModal_SelectedModulesCount");
	By enableSiteModuleHomeInput = By.id("SITE_MODULE_HOME");
	By enableSiteModuleActivityInput = By.id("SITE_MODULE_ACTIVITY");
	By enableSiteModuleDocumentsInput = By.id("SITE_MODULE_DOCUMENTS");
	By enableSiteModuleWikiInput = By.id("SITE_MODULE_WIKI");
	By enableSiteModuleBlogInput = By.id("SITE_MODULE_BLOGS");
	By enableSiteModuleTaskInput = By.id("SITE_MODULE_TASKS");
	By enableSiteModuleEventsInput = By.id("SITE_MODULE_EVENTS");
	By enableSiteModuleQandAInput = By.id("SITE_MODULE_QA");
	By enableSiteModuleIsheetInput = By.id("SITE_MODULE_ISHEET");
	By enableSiteModulePeopleInput = By.id("SITE_MODULE_PEOPLE");

	By btnDone = By.xpath("(//span[contains(text(),'" + DashboardLabels.DASHBOARD_DONE + "')])[last()]");
	By addSiteModulesOpened = By.xpath(".//*[@class='selectDrop dropdown dropdown open']");
	By mySite = By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + DashboardLabels.DASHBOARD_MYSITE + "']");
	By siteDropDownOpened = By.xpath(".//*[@class='dropdown siteDropdown open']//*[@aria-expanded='true']");

	By mySiteDropDown = By.xpath(".//*[@class='dropdown siteDropdown']//*[contains(@title,'My site')]");
	By allSiteAndTemplates = By.xpath(".//*[normalize-space(@class)='breadCrumbNav']//*[@title='All sites and templates']");
	By approvalWorkflow = By.xpath(".//*[@class='breadCrumbNav ']//*[@title = 'Approval workflow']");

	By locBtnSiteCategoryDrpdown = By.xpath(".//*[@data-id='createSiteModal_categoryID']");
	By locBtnCategoryComboBox = By.xpath("//*[@class='dropdown-menu open']");
	String locsiteCategoryList = "//*[@class='dropdown-menu open']//li";

	By globalNavigationMore = By.xpath(".//*[@class='secondaryNav']//*[text()='" + DashboardLabels.DASHBOARD_LEFTPANEL_MOREOPTION + "']");
	By globalNavigationSidePanel = By.xpath(".//*[@class='sideNavPanel']");
	By globalNavigationSidePanelDisplayed = By.xpath(".//*[@class='sideNavPanel' and @style='display: block;']");
	By userTitleOnCardView = By.xpath(".//*[@class='usertitle']");
	By userEmailOnCardView = By.xpath(".//*[@class='margBott5 userEmailid']");
	By userEmailLinkOnCardView = By.xpath(".//*[@class='margBott5 userEmailid']/a");
	By userProfileOnCardView = By.xpath(".//*[@class='clearfix']/img");
	By sendMessage = By.xpath("sendMessageBtn");
	By follow = By.xpath("followBtn");

	By taskInput = By.id("dashboardPersonalTaskInputContainer");
	By taskDatePicker = By.id("dashboardTaskDatePickerIcon");
	By seeAllTaskLink = By.xpath(".//*[@id='dashBoardRightPaneTaskList']//a[text()='" + DashboardLabels.DASHBOARD_SEE_ALL_TASKS + "']");
	By myTasks = By.xpath(".//*[@class='moduleTasks active']//*[contains(@title,'" + BannerLabels.BANNERPAGE_MYTASKS_lABEL + "')]");
	
	By sharedTab = By.xpath(".//*[@id='dashboardRightPaneMySharedFiles']/a");
	By receivedTab = By.xpath(".//*[@id='dashboardRightPaneMyReceivedFiles']/a");
	By forwordArrow = By.xpath(".//*[@class='icon-chevron-right']");
	By backwordArrow = By.xpath(".//*[@class='icon-chevron-left']");
	By close=By.xpath(".//*[@class='close tooltipShow']");
	By downloadFileViewer =By.xpath(".//*[@id='document_preview_modal_header_main']//button[normalize-space(.)='Download']");
	By fileTab = By.id("file_module_QFS_tab_li_Files");
	By emailTab = By.id("file_module_QFS_tab_li_Email");
	By dashboardMyfileTab = By.xpath("//*[@id='collaborateMainContainer']//*[normalize-space(.)='My files']");


	public DashboardWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	@Override
	public void clickOnCreateSite()
	{
		WebElement addSite = findClickableElement(addSiteLink);
		addSite.click();
		findPresentElement(createSiteTabTitle);
	}

	@Override
	public void enterSiteName(String siteName)
	{
		WebElement siteTxtBox = findClickableElement(createNewXpathInputBox);
		siteTxtBox.sendKeys(siteName);
	}

	@Override
	public AdminPageWeb clickOnSaveToCreateSite()
	{
		WebElement nextButton = findClickableElement(createSiteSaveButton);
		nextButton.click();
		while (isDisplayed(createSiteModalOpened))
		{
			;
		}
		return new AdminPageWeb(driver);
	}

	@Override
	public BannerPageWeb searchSite(String siteName)
	{
		findVisibleElement(searchSiteButton);
		// Map<Boolean, BannerPageWeb> result = new HashMap<>();
		WebElement searchButton = findClickableElement(searchSiteButton);
		WebElement site = null;
		searchButton.click();
		WebElement searchsiteTextBox = findVisibleElement(searchSiteTextBox);
		searchsiteTextBox.sendKeys(siteName);
		findVisibleElement(searchSiteList, Speed.slow);
		By siteLink = By.xpath("(//*[@id='dashboardLeftPaneSiteListContainer']//*[normalize-space(text())='" + siteName.trim() + "'])[last()]");
		boolean noSiteFound = isDisplayed(noSitesFound) || !isDisplayed(siteLink);
		if (noSiteFound)
		{
			System.err.println("No site found");
			clickOnCreateSite();
			enterSiteName(siteName);
			clickOnSaveToCreateSite();
		}
		else
		{
			site = findClickableElement(siteLink);
			site.click();
		}
		return new BannerPageWeb();
	}

	public void enterOnlySiteName(String siteName)
	{
		findVisibleElement(searchSiteButton);
		WebElement searchButton = findClickableElement(searchSiteButton);
		searchButton.click();
		WebElement searchsiteTextBox = findVisibleElement(searchSiteTextBox);
		searchsiteTextBox.sendKeys(siteName);
	}

	@Override
	public BannerPageWeb searchSite(Map<String, String> siteData) throws InterruptedException
	{
		findVisibleElement(searchSiteButton);
		WebElement searchButton = findClickableElement(searchSiteButton);
		WebElement site = null;
		searchButton.click();
		WebElement searchsiteTextBox = findVisibleElement(searchSiteTextBox);
		searchsiteTextBox.sendKeys(siteData.get(DashboardLabels.DASHBOARD_ADDSITE_SITENAME.toLowerCase()));
		findVisibleElement(searchSiteList, Speed.slow);
		By siteLink = By.xpath("(//*[@id='dashboardLeftPaneSiteListContainer']//*[normalize-space(text())='" + siteData.get(DashboardLabels.DASHBOARD_ADDSITE_SITENAME.toLowerCase()).trim() + "'])[last()]");
		boolean noSiteFound = isDisplayed(noSitesFound) || !isDisplayed(siteLink);
		if (noSiteFound)
		{
			System.err.println("No site found");
			clickOnCreateSite();
			for (Map.Entry<String, String> siteMetadata : siteData.entrySet())
			{
				String lowerCase = siteMetadata.getKey().toLowerCase();
				if (DashboardLabels.DASHBOARD_ADDSITE_SITENAME.toLowerCase().equals(lowerCase))
				{
					enterSiteName(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_TEMPLATE.toLowerCase().equals(lowerCase))
				{
					addSiteSetSiteTemplate(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_DESCRIPTION.toLowerCase().equals(lowerCase))
				{
					addSiteSetDescription(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_LANDINGPAGE.toLowerCase().equals(lowerCase))
				{
					addSiteSetLandingPage(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_STARTDATE.toLowerCase().equals(lowerCase))
				{
					addSiteSetStartDate(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_ENDDATE.toLowerCase().equals(lowerCase))
				{
					addSiteEndDate(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_SITETYPE.toLowerCase().equals(lowerCase))
				{
					addSiteSetSiteType(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_CLIENTNUMBER.toLowerCase().equals(lowerCase))
				{
					addSiteSetClientNumber(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_MATTERNUMBER.toLowerCase().equals(lowerCase))
				{
					addSiteSetMatterNumber(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_CATEGORY.toLowerCase().equals(lowerCase))
				{
					setSiteCategory(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_LOGO.toLowerCase().equals(lowerCase))
				{
					addSiteUploadSiteLogo(siteMetadata.getValue());
				}
				else if (DashboardLabels.DASHBOARD_ADDSITE_ADMINNOTES.toLowerCase().equals(lowerCase))
				{
					addSiteSetAdminNotes(siteMetadata.getValue());
				}
				else
				{
				}
			}
			clickOnSaveToCreateSite();
		}
		else
		{
			site = findClickableElement(siteLink);
			site.click();
		}
		return new BannerPageWeb();
	}

	@Override
	public void addSiteSetSiteTemplate(String templateName) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteModalTemplateDropDown);
		Select createSiteModalTemplateEle = new Select(findClickableElement(createSiteModalTemplateDropDown));
		createSiteModalTemplateEle.selectByVisibleText(templateName);
	}

	@Override
	public void addSiteSetDescription(String description) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteDescriptionInput);
		WebElement createSiteDescriptionEle = findClickableElement(createSiteDescriptionInput);
		createSiteDescriptionEle.sendKeys(description);
	}

	@Override
	public void addSiteSetLandingPage(String landingPage) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteLandingPageDropDown);
		Select createSiteLandingPageEle = new Select(findClickableElement(createSiteLandingPageDropDown));
		createSiteLandingPageEle.selectByVisibleText(landingPage);
	}

	@Override
	public void addSiteSetStartDate(String startDate) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteStartDateInput);
		WebElement startDateEle = findVisibleElement(createSiteStartDateInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + startDate + "'", startDateEle);
		startDateEle.sendKeys(Keys.TAB);
	}

	@Override
	public void addSiteEndDate(String endDate) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteEndDateInput);
		WebElement endDateEle = findVisibleElement(createSiteEndDateInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + endDate + "'", endDateEle);
		endDateEle.sendKeys(Keys.TAB);
	}

	@Override
	public void addSiteSetSiteType(String siteType) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		String lowerCase = siteType.toLowerCase();
		if (DashboardLabels.DASHBOARD_ADDSITE_SITETYPE_MATTER.toLowerCase().equals(lowerCase))
		{
			WebElement siteTypeMatterEle = findClickableElement(createSiteMatterTypeInput);
			siteTypeMatterEle.click();
		}
		else if (DashboardLabels.DASHBOARD_ADDSITE_SITETYPE_SERVICE.toLowerCase().equals(lowerCase))
		{
			WebElement siteTypeServiceEle = findClickableElement(createSiteServiceTypeInput);
			siteTypeServiceEle.click();
		}
		else
		{
			System.out.println("Enter valid site type");
		}
	}

	@Override
	public void addSiteSetClientNumber(String clientNumber) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteClientNumberInput);
		WebElement siteClientNumberEle = findClickableElement(createSiteClientNumberInput);
		siteClientNumberEle.clear();
		siteClientNumberEle.sendKeys(clientNumber);
	}

	@Override
	public void addSiteSetMatterNumber(String matterNumber) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteMatterNumberInput);
		WebElement siteMatterNumberEle = findClickableElement(createSiteMatterNumberInput);
		siteMatterNumberEle.clear();
		siteMatterNumberEle.sendKeys(matterNumber);
	}

	@Override
	public void setSiteCategory(String categoryName) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteCategoryDropDown);
		findClickableElement(locBtnSiteCategoryDrpdown).click();
		if (valuePresent(driver.findElements(By.xpath("//*[@class='dropdown-menu open']//li")), categoryName))
		{
			findClickableElement(locBtnSiteCategoryDrpdown).click();
			selectOptionFromDropDown(locBtnSiteCategoryDrpdown, locBtnCategoryComboBox, locsiteCategoryList, categoryName);
		}
		else
		{
			addNewCategory(categoryName);
		}
	}

	public void addNewCategory(String categoryName) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteCategoryDropDown);

		WebElement addNewCategoryEle = findClickableElement(addNewCategory);
		addNewCategoryEle.click();

		WebElement addCategoryNameEle = findClickableElement(addCategoryNameInput, Speed.slow, 5);
		addCategoryNameEle.sendKeys(categoryName.trim());

		WebElement submitCategoryEle = findClickableElement(submitCategoryButton);
		submitCategoryEle.click();
	}

	@Override
	public void addSiteUploadSiteLogo(String logo) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteLogoInput);
		String path = TestBaseSetup.currentDir + "\\testData\\" + logo;
		WebElement siteLogoEle = findPresentElement(createSiteLogoInput);
		siteLogoEle.sendKeys(path);
	}

	@Override
	public void addSiteSetAdminNotes(String adminNotes) throws InterruptedException
	{
		findPresentElement(createSiteModalOpened, Speed.slow);
		// scrollToElement(createSiteAdminNotesInput);
		WebElement createSiteAdminNotesEle = findClickableElement(createSiteAdminNotesInput);
		createSiteAdminNotesEle.sendKeys(adminNotes);
	}

	@Override
	public void clickOnModuleDropDown()
	{
		findVisibleElement(createSiteModalOpened, Speed.slow);
		WebElement enableSiteModuleEle = findPresentElement(enableSiteModuleDropDown);
		enableSiteModuleEle.click();
		// findPresentElement(addSiteModulesOpened);
	}

	@Override
	public void addSiteEnableModules(List<String> modulesList) throws InterruptedException
	{
		findVisibleElement(createSiteModalOpened, Speed.slow);
		if (!isDisplayed(addSiteModulesOpened))
		{
			clickOnModuleDropDown();
		}

		for (String moduleName : modulesList)
		{
			WebElement enableModuleEle = findPresentElement(By.xpath(".//*[@class='selectDrop dropdown dropdown open']//label[@title='" + moduleName + "']//input"));
			// scrollToElement(By.xpath(".//*[@class='selectDrop dropdown dropdown open']//label[@title='" + moduleName + "']//input"));
			if (enableModuleEle.isDisplayed() && !(enableModuleEle.isSelected()))
			{
				enableModuleEle.click();
			}
			else
			{
				System.out.println(moduleName + " = is not displayed");
			}
		}
	}

	@Override
	public void addSiteEnableAllSiteModules() throws InterruptedException
	{
		findVisibleElement(createSiteModalOpened, Speed.slow);
		if (!isDisplayed(addSiteModulesOpened))
		{
			clickOnModuleDropDown();
		}

		By[] modules = {enableSiteModuleHomeInput, enableSiteModuleActivityInput, enableSiteModuleDocumentsInput, enableSiteModuleWikiInput, enableSiteModuleBlogInput, enableSiteModuleTaskInput, enableSiteModuleEventsInput, enableSiteModuleQandAInput, enableSiteModuleIsheetInput, enableSiteModulePeopleInput};

		for (int i = 0; i < modules.length; i++)
		{
			WebElement moreOptionselement = findPresentElement(modules[i]);
			if (moreOptionselement.isDisplayed() && !(moreOptionselement.isSelected()))
			{
				moreOptionselement.click();
			}
			else
			{
				System.out.println(modules[i].toString() + " = is not displayed");
			}
		}
	}

	@Override
	public boolean verifyAddSiteModalOpened()
	{
		return isDisplayed(createSiteModalOpened, Speed.slow);
	}

	@Override
	public boolean verifySelectedModulesOnAddSite() throws InterruptedException
	{
		findVisibleElement(createSiteModalOpened, Speed.slow);
		if (!isDisplayed(addSiteModulesOpened))
		{
			clickOnModuleDropDown();
			findPresentElement(addSiteModulesOpened);
		}
		By[] modules = {enableSiteModuleHomeInput, enableSiteModuleActivityInput, enableSiteModuleDocumentsInput, enableSiteModuleWikiInput, enableSiteModuleBlogInput, enableSiteModuleTaskInput, enableSiteModuleEventsInput, enableSiteModuleQandAInput, enableSiteModuleIsheetInput, enableSiteModulePeopleInput};

		for (int i = 0; i < modules.length; i++)
		{
			WebElement moreOptionselement = findPresentElement(modules[i]);
			// scrollToElement(modules[i]);
			if (modules[i].equals(enableSiteModuleDocumentsInput) || modules[i].equals(enableSiteModulePeopleInput))
			{
				if (moreOptionselement.isDisplayed() && !(moreOptionselement.isSelected()))
				{
					return false;
				}
			}
			else
			{
				if (moreOptionselement.isDisplayed() && moreOptionselement.isSelected())
				{
					return false;
				}
			}
		}
		if (isDisplayed(addSiteModulesOpened))
		{
			clickOnModuleDropDown();
		}
		return true;
	}

	@Override
	public void clickOnInstanceName()
	{
		WebElement instanceNameEle = findClickableElement(instanceNameText, Speed.slow);
		instanceNameEle.click();
	}

	@Override
	public void selectOptionFromSiteDropDown(String option)
	{
		findVisibleElement(siteDropDownOpened, Speed.slow);
		WebElement dropdownOptionEle = findClickableElement(By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + option + "']"));
		dropdownOptionEle.click();
	}

	@Override
	public void makeSiteFavouriteInSiteDropDown(String siteName, boolean value)
	{
		findVisibleElement(siteDropDownOpened, Speed.slow);
		if (value)
		{
			if (!isDisplayed(By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + siteName.trim() + "']//preceding-sibling::*[@data-original-title='Remove from favourites']")))
			{
				WebElement siteEle = findClickableElement(By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + siteName.trim() + "']//preceding-sibling::*[@data-original-title='Add to favourites']"));
				siteEle.click();
			}
		}
		else
		{
			if (!isDisplayed(By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + siteName.trim() + "']//preceding-sibling::*[@data-original-title='Add to favourites']")))
			{
				WebElement siteEle = findClickableElement(By.xpath(".//*[@class='dropdown-menu headerDrop']//*[normalize-space(text())='" + siteName.trim() + "']//preceding-sibling::*[@data-original-title='Remove from favourites']"));
				siteEle.click();
			}
		}
	}

	@Override
	public boolean verifyTabsOfRecentActivity(String tab)
	{
		String lowerCase = tab.toLowerCase();
		if (DashboardLabels.DASHBOARD_TAB_ALL.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(allTabLink);
		}
		else if (DashboardLabels.DASHBOARD_TAB_POSTS.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(postsTabLink);
		}
		else if (DashboardLabels.DASHBOARD_TAB_ACTIVITY.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(activityTabLink);
		}
		else if (DashboardLabels.DASHBOARD_TAB_FILTER.toLowerCase().equals(lowerCase))
		{
			return isDisplayed(filterTabLink);
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean verifyMySiteLink()
	{
		findVisibleElement(siteDropDownOpened, Speed.slow);
		return isDisplayed(mySite);
	}

	@Override
	public boolean verifyRecentTabSiteList()
	{
		return isDisplayed(recentTabLink, Speed.slow);
	}

	@Override
	public boolean verifyFavouritesTabSiteList()
	{
		return isDisplayed(favouritesLink, Speed.slow);
	}

	@Override
	public void clickOnMoreLeftPanel()
	{
		if (isDisplayed(moreOptions))
		{
			WebElement moreEle = findClickableElement(moreOptions);
			moreEle.click();
		}
	}

	@Override
	public boolean verifyCategoriesTabSiteList()
	{
		return isDisplayed(categoriesLink, Speed.slow);
	}

	@Override
	public boolean verifyAddSiteLink()
	{
		return isDisplayed(addSiteLink, Speed.slow);
	}

	@Override
	public boolean verifySearchSiteIcon()
	{
		return isDisplayed(leftPanelSearchIconLink, Speed.slow);
	}

	@Override
	public boolean verifyReceivedFilesTab()
	{
		return isDisplayed(myFilesReceivedTabLink, Speed.slow);
	}

	@Override
	public boolean verifySharedFilesTab()
	{
		return isDisplayed(myFilesSharedTabLink, Speed.slow);
	}

	@Override
	public boolean verifySeeAllFilesLink()
	{
		return isDisplayed(myFilesSeeAllFilesLink, Speed.slow);
	}

	@Override
	public boolean verifySendAFileButton()
	{
		return isDisplayed(sendAFileText, Speed.slow);
	}

	@Override
	public boolean verifyPersonalTaskTab()
	{
		return isDisplayed(pesronalTabLink, Speed.slow);
	}

	@Override
	public boolean verifyAssignToMeTaskTab()
	{
		return isDisplayed(assignedToMe, Speed.slow);
	}

	@Override
	public boolean verifyAddTaskIcon()
	{
		findClickableElement(addTaskLink, Speed.slow);
		return isDisplayed(addTaskLink);
	}

	@Override
	public boolean verifyMySite()
	{
		return isDisplayed(mySiteDropDown, Speed.slow);
	}

	@Override
	public boolean verifyAllSiteAndTemplates()
	{
		return isDisplayed(allSiteAndTemplates, Speed.slow);
	}

	@Override
	public boolean verifyApprovalWorkflow()
	{
		return isDisplayed(approvalWorkflow, Speed.slow);
	}

	@Override
	public boolean verifyGlobalNavigationMenuOption(String option)
	{
		if (!isDisplayed(By.xpath(".//*[@class='secondaryNav']//*[text()='" + option.trim() + "']")))
		{
			if (isDisplayed(globalNavigationMore))
			{
				WebElement moreEle = findClickableElement(globalNavigationMore);
				moreEle.click();

				findVisibleElement(globalNavigationSidePanelDisplayed, Speed.slow);
				moveToElement(globalNavigationSidePanel);

				findVisibleElement(By.xpath(".//*[@class='sideNavPanel' and @style='display: block;']//*[text()='" + option.trim() + "']"));
				// WebElement globalNavigationOptionEle = findVisibleElement(By.xpath(".//*[@class='sideNavPanel' and @style='display: block;']//*[text()='" + option + "']"));
				// scrollToElement(globalNavigationOptionEle);
			}
			return isDisplayed(By.xpath(".//*[@class='sideNavPanel' and @style='display: block;']//*[text()='" + option.trim() + "']"));
		}
		else
		{
			return true;
		}
	}

	@Override
	public void clickOnMenuItemOnGlobalNavigation(String option)
	{
		if (!isDisplayed(By.xpath(".//*[@class='secondaryNav']//*[text()='" + option.trim() + "']")))
		{
			if (isDisplayed(globalNavigationMore))
			{
				WebElement moreEle = findClickableElement(globalNavigationMore);
				moreEle.click();

				findVisibleElement(globalNavigationSidePanelDisplayed, Speed.slow);

				WebElement globalNavigationOptionEle = findPresentElement(By.xpath(".//*[@class='sideNavPanel' and @style='display: block;']//*[text()='" + option + "']"));
				scrollToElement(globalNavigationOptionEle);

				globalNavigationOptionEle.click();
			}
		}
		else
		{
			WebElement menuItemEle = findClickableElement(By.xpath(".//*[@class='secondaryNav']//*[text()='" + option.trim() + "']"));
			menuItemEle.click();
		}
	}

	@Override
	public boolean verifyGlobalNavigationContainerMenuItem(String containerName, String menuItem)
	{
		if (verifyGlobalNavigationMenuOption(containerName))
		{
			clickOnMenuItemOnGlobalNavigation(containerName);
			return isDisplayed(By.xpath(".//*[@class='secondaryNav']//*[text()='" + containerName.trim() + "']//parent::*//child::*[normalize-space(text())='" + menuItem.trim() + "']"));
		}
		return false;
	}

	/**
	 * @author ankit.motaval
	 * @param username
	 *        Click on Recent Activity
	 * @created on 11/04/2018
	 */
	@Override
	public void cilckOnUserRecentActivity(String username)
	{
		if (isDisplayed(By.xpath("(.//*[@id='dashboard_recentActivity']//*[text()='" + username.trim() + "'])[1]"), Speed.slow))
		{
			findVisibleElement(By.xpath("(.//*[@id='dashboard_recentActivity']//*[text()='" + username.trim() + "'])[1]"), Speed.slow).click();
		}

	}

	/**
	 * @author ankit.motaval
	 * @param username
	 *        verify user Title on card
	 * @created on 11/04/2018
	 */
	@Override
	public boolean verifyUserTitleInCard(String username)
	{
		String getText = findVisibleElement(userTitleOnCardView, Speed.slow).getText();
		return getText.trim().equals(username.trim());
	}

	/**
	 * @author ankit.motaval
	 * @param email
	 *        verify user Email on card
	 * @created on 11/04/2018
	 */
	@Override
	public boolean verifyUserEmailInCard(String email)
	{
		String getText = findVisibleElement(userEmailOnCardView, Speed.slow).getText();
		return getText.trim().equals(email.trim());
	}

	/**
	 * @author ankit.motaval
	 *         verify user Avtar on card
	 * @created on 11/04/2018
	 */
	@Override
	public boolean verifyUserAvtarAvailabe()
	{
		String contentText = findVisibleElement(By.xpath(".//*[@class='clearfix']/img"), Speed.slow).getAttribute("src");
		if (contentText.contains("downloadUserAvatar.action"))
		{
			return true;
		}
		return false;
	}

	/**
	 * @author ankit.motaval
	 * @param username
	 *        verify Liked username on Microblog post
	 * @created on 11/04/2018
	 */
	@Override
	public boolean verifyLikedUserName(String username)
	{
		return isDisplayed(By.xpath("//*[starts-with(@id, 'contentLike')]/a[normalize-space(.)='" + username.trim() + "']"), Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 * @param value
	 *        verify Company name on microblog post
	 * @created on 11/04/2018
	 */
	@Override
	public boolean verifyCompanyName(String value)
	{
		return isDisplayed(By.xpath(".//*[@class='margBott12 userJobtitle']//span[contains(text(),'" + value + "')]"), Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify user email link on card
	 * @created on 24/04/2018
	 */
	@Override
	public boolean verifyUserEmailLinkOnCard()
	{
		return isDisplayed(userEmailLinkOnCardView, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         click on Activity
	 * @created on 24/04/2018
	 */
	@Override
	public void clickOnActivity()
	{
		findVisibleElement(activityTabLink, Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 *         verify Send a message button available
	 * @created on 01/05/2018
	 */
	@Override
	public boolean isAvailableSendMessageButtonOnCard()
	{
		return isDisplayed(sendMessage, Speed.slow);
	}

	/**
	 * @author ankit.motaval
	 *         verify Follow button available
	 * @created on 01/05/2018
	 */
	@Override
	public boolean isAvailableFollowButtonOnCard()
	{
		return isDisplayed(follow, Speed.slow);
	}

	@Override
	public boolean verifySendAFile()
	{
		By sendAFile = By.id("QUICK_FILE_SHARE_TITLE");
		return isDisplayed(sendAFile);
	}

	@Override
	public void closeSendAFileModal()
	{

		WebElement closeModule = findClickableElement(closeSendFileIcon);
		closeModule.click();
	}

	/**
	 * @author surbhi.khetan
	 *         verify default value in share with
	 * @creation date 16/04/2018
	 */
	@Override
	public void clickOnAssignedToMeTab()
	{
		WebElement assignToMeTab = findClickableElement(assignedToMe);
		assignToMeTab.click();
	}

	/**
	 * @author surbhi.khetan
	 * @param task
	 *        verify task visibility in assigned to me and personal tab on dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyTaskVisibility(String task)
	{
		return (isDisplayed(By.xpath(".//*[@id='dashboard_rightPane_taskList']//a[text()='" + task.trim() + "']")));
	}

	/**
	 * @author surbhi.khetan
	 *         click on + icon to add task from dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnAddTask()
	{
		WebElement addTask = findClickableElement(rightPanelAddTaskSymbol);
		addTask.click();
	}

	/**
	 * @author surbhi.khetan
	 *         verify task text box when user clicks on + icon from dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyTaskTextBox()
	{
		return isDisplayed(taskInput);
	}

	/**
	 * @author surbhi.khetan
	 *         verify task date picker when user clicks on + icon from dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyTaskDatePicker()
	{
		return isDisplayed(taskDatePicker);
	}

	/**
	 * @author surbhi.khetan
	 * @param description
	 *        Add personal task from dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public void addPersonalTask(String description)
	{

		WebElement AddPersonaltask = findClickableElement(taskInput);
		AddPersonaltask.sendKeys(description);
		AddPersonaltask.sendKeys(Keys.ENTER);
	}

	/**
	 * @author surbhi.khetan
	 * @param task
	 *        Click on personal tab from dashbaord panel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickOnTaskFromPersonalTab(String task)
	{
		WebElement personalTask = findClickableElement(By.xpath(".//*[@id='dashboard_rightPane_taskList']//a[text()='" + task.trim() + "']"));
		personalTask.click();
	}

	/**
	 * @author surbhi.khetan
	 *         Click on see all tasks link from dashboard panel
	 * @creation date 17/04/2018
	 */
	@Override
	public void clickSeeAllTask()
	{

		WebElement seeAllTask = findClickableElement(seeAllTaskLink);
		seeAllTask.click();
	}

	/**
	 * @author surbhi.khetan
	 *         verify re-direction to My tasks link
	 * @creation date 17/04/2018
	 */
	@Override
	public boolean verifyMyTasksTab()
	{

		return isDisplayed(myTasks);
	}

	/**
	 * @author ashlesha.shastri
	 *         To verify Event on dashboard
	 * @created on 12/04/2018
	 */
	@Override
	public boolean verifyEventDisplayedOnDashboard(String event)
	{
		return isDisplayed(By.xpath(".//*[@id='dashboardRightPaneEventContainer']/ul/li/span[@class='TruncateTxt']/a[text()='" + event.trim() + "']"), Speed.slow);
	}
	/**
	 * @author ashlesha.shastri
	 *         To click on event on dashboard then redirect to event page
	 * @created on 12/04/2018
	 */
	@Override
	public EventsWeb clickOnEventDisplayedOnDashboard(String event)
	{
		WebElement eventEle = findVisibleElement(By.xpath(".//*[@id='dashboardRightPaneEventContainer']/ul/li/span[@class='TruncateTxt']/a[text()='" + event.trim() + "']"), Speed.slow);
		eventEle.click();
		return new EventsWeb(driver);
	}
	
	/**
	 * @author khushbu.dhandhukiya
	 * @created date 14/05/2018
	 * 
	 */
	public boolean verifyMyFilesTab()
	{
		return isDisplayed(dashboardMyfileTab, Speed.slow);
	}
	/**
	 * @author khushbu.dhandhukiya
	 *         click On shared tab on Dashboard right panel
	 * @creation date 14/05/2018
	 */
	public void clickOnSharedTab()
	{

		findVisibleElement(sharedTab, Speed.slow).click();

	}

	/**
	 * @author khushbu.dhandhukiya
	 * 
	 * @creation date 14/05/2018
	 */
	public boolean verifySharedItem(String file)
	{
		return (isDisplayed(By.xpath(".//*[@id='dashboardRightPaneMyFilesItems']//a[normalize-space(.)='" + file.trim() + "']")));
	}

	/**
	 * @author khushbu.dhandhukiya
	 *         click On received tab on Dashboard right panel
	 * @creation date 14/05/2018
	 */
	public void clickOnReceivedTab()
	{
		findVisibleElement(receivedTab, Speed.slow).click();
	}

	
	/**
	 * @author khushbu.dhandhukiya
	 * 
	 * @creation date 14/05/2018
	 */
	public boolean verifyReceivedItem(String file)
	{
		return (isDisplayed(By.xpath(".//*[@id='dashboardRightPaneMyFilesItems']//a[normalize-space(.)='" + file.trim() + "']")));
	}
	
	/**
	 * @author khushbu.dhandhukiya
	 *        click on file view in My file right panel
	 * @creation date 14/05/2018
	 */
	public void clickOnFile(String file)
	{

		WebElement fileAdd = findVisibleElement(By.xpath(".//*[@id='dashboardRightPaneMyFilesItems']//a[normalize-space(.)='" + file.trim() + "']"));
		fileAdd.click();
	}
		
	/**
	 * @author khushbu.dhandhukiya
	 *         click On forward Arrow in file viewer
	 * @creation date 14/05/2018
	 */
	public void clickOnForwardArrowOfViewer()
	{
		findVisibleElement(forwordArrow, Speed.slow).click();
	}
	/**
	 * @author khushbu.dhandhukiya
	 *         click On backword Arrow in file viewer
	 * @creation date 14/05/2018
	 */
	public void clickOnBackwordArrowOfViewer()
	{
		findVisibleElement(backwordArrow, Speed.slow).click();
	}
	/**
	 * @author khushbu.dhandhukiya
	 *         click On backward Arrow in file viewer
	 * @creation date 14/05/2018
	 */
	public void clickOnCloseFileViewer()
	{
		findVisibleElement(close, Speed.slow).click();
	}
	/**
	 * @author khushbu.dhandhukiya
	 *         click On download fron file viewer
	 * @creation date 15/05/2018
	 */
	public void clickOnDownoloadFileViewer()
	{
		findVisibleElement(downloadFileViewer, Speed.slow).click();
	}

	/**
0	 * @author khushbu.dhandhukiya
	 *         click On See All link from dashboard 
	 * @creation date 15/05/2018
	 */
	public void clickOnSeeAllLink()
	{
		findVisibleElement(myFilesSeeAllFilesLink, Speed.slow).click();
	}

	/**
	 * @author khushbu.dhandhukiya
	 *         click On send a file button from dashboard
	 * @creation date 15/05/2018
	 */
	public void clickOnSendAFileButton()
	{
		findVisibleElement(myFilesSeeAllFilesLink, Speed.slow).click();
	}

	/**
	 * @author khushbu.dhandhukiya
	 * 
	 * @creation date 15/05/2018
	 */
	public boolean verifyFileTabInSendAFile()
	{
		return (isDisplayed(fileTab, Speed.slow));
	}

	/**
	 * @author khushbu.dhandhukiya
	 * @creation date 15/05/2018
	 */
	public boolean verifyEmailTabInSendAFile()
	{
		return (isDisplayed(emailTab, Speed.slow));
	}

	@Override
	public void clickOnpostTab()
	{
		if (isDisplayed(postsTabLink))
		{
			WebElement microblogpost = findClickableElement(postsTabLink);
			microblogpost.click();
		}
	}

	@Override
	public void enterPostMicroBlogValue(String microblogpostvalue)
	{
		WebElement microblogvalue = findClickableElement(postTextArea);
		microblogvalue.sendKeys(microblogpostvalue);
	}

	@Override
	public void clickOnpostButton()
	{
		if (isDisplayed(postButton))
		{
			WebElement microblogpost = findClickableElement(postButton);
			microblogpost.click();
		}
	}
	

}
