package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.pageobjects.base.AdminIsheetEditColumnPage;

public class AdminIsheetEditColumnWeb extends AdminIsheetAddColumnWeb implements AdminIsheetEditColumnPage
{

	By nothingSelected = By.xpath(".//*[@data-id='engineListDropDown' and @title='Nothing selected']");

	public AdminIsheetEditColumnWeb(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to verify that engines selected at the time of add are displayed in selected edit
	 *
	 * @author janki.hirani
	 *         created date: 17/05/2018
	 * @param commaSeparatedEnginesName
	 * @return
	 */
	public boolean verifySelectedEngines(String commaSeparatedEnginesName)
	{
		return isDisplayed(By.xpath(".//*[@id='engineListTDID']//button[@data-id='engineListDropDown' and (contains(@title,'" + commaSeparatedEnginesName + "'))]"));
	}

	/**
	 * This method is used to verify Nothing is selected in AnalysisEngine drop down
	 *
	 * @author janki.hirani
	 *         created date: 17/05/2018
	 * @return
	 */
	public boolean verifyDefaultNothingSelectedInAnalysisEngine()
	{
		return isDisplayed(nothingSelected);
	}

}
