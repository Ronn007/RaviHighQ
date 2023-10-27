package com.highq.pageobjects.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SearchContentLabels;
import com.highq.pageobjects.base.SearchContentPage;

/**
 * @author nidhi.shah
 */
public class SearchContentWeb extends BannerPageWeb implements SearchContentPage
{
	public SearchContentWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	By searchBoxInput = By.id("file_module_advSearch_boolExp");
	By searchResultDiv = By.id("file_module_advSearch_searchResultDivID");
	By noResultFoundMessage = By.xpath(".//*[@id='file_module_advSearch_searchResultDivID']//*[text()='" + SearchContentLabels.SEARCHCONTENT_NOSEARCHRESULTWASFOUND + "']");

	By searchResultMiddlePanel = By.id("file_module_advSearch_searchResultDivID");

	By searchButton = By.xpath("//*[@class='input-group-btn stopPropogation']//*[@class='btn']");
	By numberOfSearchResults = By.xpath(".//*[@id='file_module_advSearch_searchResultDivID']//*[@class='clearfix']");

	/* Sort By */
	By sortByLink = By.id("sortTypeDisplayID");
	By sortByDropDown = By.xpath("//*[contains(@class,'dropdown open')]");
	String sortByOptionList = "//*[contains(@class,'dropdown open')]//li";

	@Override
	public boolean verifySearchBox()
	{
		return isDisplayed(searchBoxInput, Speed.slow);
	}

	@Override
	public boolean verifySearchBoxContent(String content)
	{
		WebElement searchBoxEle = findClickableElement(searchBoxInput);
		return searchBoxEle.getAttribute("value").trim().contains(content);
	}

	@Override
	public boolean verifySearch()
	{
		return isDisplayed(searchResultDiv, Speed.slow);
	}

	/**
	 * @author vivek.mishra
	 * @param text
	 * @return the serch result availiable
	 * @creation date 31/01/2018
	 */
	@Override
	public boolean verifySearchResult(String text)
	{
		return (isDisplayed(By.xpath("(//*[normalize-space(text())='" + text.trim() + "'])[1]"), Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @return
	 * 		To verify middle pannel is loaded
	 * @creation date 28/03/2018
	 */
	@Override
	public boolean verifySearchResultMiddlePanel()
	{
		findVisibleElement(searchResultMiddlePanel, Speed.slow);
		return (isDisplayed(searchResultMiddlePanel, Speed.slow));
	}

	/**
	 * @author vivek.mishra
	 * @param text to be searched
	 * @creted on 16/04/2018
	 */
	@Override
	public void sendTextInSearchBox(String text)
	{
		WebElement element = findVisibleElement(searchBoxInput, Speed.slow);
		element.click();
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * @author vivek.mishra
	 *         to click on quick search button
	 * @creted on 16/04/2018
	 */
	@Override
	public void clickOnSearchButton()
	{
		WebElement element = findVisibleElement(searchButton, Speed.slow);
		element.click();
	}

	/**
	 * Verify 'No search result was found' message.
	 * 
	 * @return true --> If "No search result was found" message is displayed
	 *         false otherwise.
	 * @author nidhi.shah
	 * @created : 17-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyNoSearchResultFoundMessage()
	{
		return isDisplayed(noResultFoundMessage, Speed.slow);
	}

	/**
	 * Verify content of all displayed search results.
	 * 
	 * @return true --> If searched result contains searchString,
	 *         false otherwise.
	 * @param searchString
	 * @author nidhi.shah
	 * @created : 17-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyAllSearchResult(String searchString)
	{
		if (verifySearch() && !verifyNoSearchResultFoundMessage())
		{
			int numberOfResults = driver.findElements(numberOfSearchResults).size();

			String searchedContent = "";
			for (int i = 1; i <= numberOfResults; i++)
			{
				searchedContent = findVisibleElement(By.xpath("((.//*[@id='file_module_advSearch_searchResultDivID']//*[@class='clearfix'])[" + i + "]//strong)[1]")).getText();
				if (!searchedContent.contains(searchString))
				{
					return false;
				}
			}
			return true;
		}
		return true;
	}

	/**
	 * Verify content of all displayed search results of People.
	 * 
	 * @return true --> If searched result contains searchString,
	 *         false otherwise.
	 * @param searchString
	 * @author nidhi.shah
	 * @created : 17-05-2018
	 * @modified
	 */
	@Override
	public boolean verifyPeopleSearchResult(String searchString)
	{
		if (verifySearch() && !verifyNoSearchResultFoundMessage())
		{
			int numberOfResults = driver.findElements(numberOfSearchResults).size();
			String[] name = searchString.split("[\\s@&.?$+-_*%!#^]+");
			String searchedContent = "";
			List<Boolean> status = new ArrayList<>();
			for (int i = 1; i <= numberOfResults; i++)
			{
				status.clear();
				searchedContent = findVisibleElement(By.xpath("(.//*[@id='file_module_advSearch_searchResultDivID']//*[@class='clearfix']//*[contains(@class,'usercardLink')])[" + i + "]")).getAttribute("title");
				for (int j = 0; j < name.length; j++)
				{
					if (searchedContent.toLowerCase().contains(name[j].toLowerCase()))
					{
						status.add(true);
						break;
					}
					else
					{
						status.add(false);
					}
				}
				if (!status.contains(true))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Verify Sort option
	 * 
	 * @param sortByValue
	 *        sort by option
	 * @return true
	 *         if successful
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifySortBy(String sortByValue)
	{
		WebElement sortBy = findVisibleElement(sortByLink);
		return sortBy.getText().trim().equals(sortByValue.trim());
	}

	/**
	 * Select Sort By option
	 * 
	 * @param sortByValue
	 *        sort by option
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public void selectSortBy(String sortByValue)
	{
		if (!verifySortBy(sortByValue))
		{
			selectOptionFromDropDown(sortByLink, sortByDropDown, sortByOptionList, sortByValue);
		}
	}

	/**
	 * Verify token
	 * 
	 * @param token
	 *        token to verify
	 * @return true
	 *         if token found
	 * @author dheeraj.rajput
	 * @Created 21 May 2018
	 * @Updated
	 */
	public boolean verifyToken(String token)
	{
		By tokenXpath = By.xpath(".//*[@id='file_advanceSearch_tokenFields']//*[normalize-space(text())='" + token + "']");
		return isDisplayed(tokenXpath, Speed.slow);
	}
}
