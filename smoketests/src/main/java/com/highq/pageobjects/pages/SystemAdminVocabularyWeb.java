package com.highq.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.SystemAdminVocabularyPage;

/**
 * @author ankit.motaval
 */
public class SystemAdminVocabularyWeb extends BannerPageWeb implements SystemAdminVocabularyPage
{
	String idEditButton = "editVocabularyID";
	String idDoneButton = "saveVocabularyID";
	String idTextbox = "textVocabularyID";

	By savebutton = By.xpath(".//*[@class='margBott10 pull-right']//a[text()='Save']");
	By gdprConntact = By.xpath(".//span[normalize-space(text())='" + SystemAdminLabels.GDPR_CONTACT_USER_EMAIL_ADDRESS_LABEL + "']");

	public SystemAdminVocabularyWeb(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * @author ankit.motaval
	 *         Edit GDPR Contact
	 * @created on 09/04/2018
	 */
	public void editGdprContact(String labelName, String value)
	{
		String idTextVocab = findVisibleElement(By.xpath(".//span[normalize-space(text())='" + labelName + "']"), Speed.slow).getAttribute("id");
		String idNumber = idTextVocab.substring(17);
		findVisibleElement(By.id(idEditButton + idNumber), Speed.slow).click();
		WebElement textBox = findVisibleElement(By.id(idTextbox + idNumber), Speed.slow);
		textBox.clear();
		textBox.sendKeys(value);
		findVisibleElement(By.id(idDoneButton + idNumber), Speed.slow).click();
	}

	/**
	 * @author ankit.motaval
	 *         Click on save
	 * @created on 09/04/2018
	 */
	public void saveSettings()
	{
		findVisibleElement(savebutton).click();
	}

}
