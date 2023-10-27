package com.highq.pageobjects.base;

public interface SearchContentPage extends BannerPage
{
	public boolean verifySearchBox();

	public boolean verifySearchBoxContent(String content);

	@Override
	public boolean verifySearch();

	public boolean verifySearchResult(String text);

	public boolean verifySearchResultMiddlePanel();

	public void sendTextInSearchBox(String text);

	@Override
	public void clickOnSearchButton();

	boolean verifyNoSearchResultFoundMessage();

	boolean verifyAllSearchResult(String searchString);

	boolean verifyPeopleSearchResult(String searchString);

	public boolean verifySortBy(String sortByValue);

	public void selectSortBy(String sortByValue);

	public boolean verifyToken(String token);
}
