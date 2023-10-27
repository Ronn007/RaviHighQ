package com.highq.pageobjects.pages;

import java.util.StringTokenizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.SystemAdminIsheetAdminLabels;
import com.highq.pageobjects.base.SystemAdminTemplateIsheetsListPage;

public class SystemAdminTemplateIsheetsListWeb extends BannerPageWeb implements SystemAdminTemplateIsheetsListPage
{
	By totalIsheetRows = By.xpath(".//*[@name='deleteTemplates']//*[@class='responsive columnView']//tr[not(@class='columnHeadings')]");

	public SystemAdminTemplateIsheetsListWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * This method is used to click on Add / Edit Column of an iSheet in SystemAdmin -> IsheetAdmin
	 *
	 * @author janki.hirani
	 * @created on 24 May 2018
	 * @param iSheetName
	 */
	public SystemAdminIsheetColumnsListWeb clickOnAddEditIsheetColumn(String iSheetName)
	{
		int iSheetCount = driver.findElements(totalIsheetRows).size();
		for (int i = 1; i <= iSheetCount; i++)
		{
			String iSheet = findVisibleElement(By.xpath(".//*[@name='deleteTemplates']//*[@class='responsive columnView']//tr[not(@class='columnHeadings')][" + i + "]/child::*[2]"), Speed.slow).getText();
			if (iSheet.contains("("))
			{
				StringTokenizer split = new StringTokenizer(iSheet, "(");
				iSheet = split.nextToken().trim();
			}
			if (iSheet.equals(iSheetName))
			{
				findClickableElement(By.xpath(".//*[@name='deleteTemplates']//*[@class='responsive columnView']//tr[not(@class='columnHeadings')][" + i + "]//*[contains(@onclick,'columnList') and normalize-space()='" + SystemAdminIsheetAdminLabels.COLUMNS_ADD_OR_EDIT + "']")).click();
			}
		}
		return new SystemAdminIsheetColumnsListWeb(driver);
	}
}
