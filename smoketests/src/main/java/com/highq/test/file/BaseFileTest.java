package com.highq.test.file;

import org.openqa.selenium.WebDriver;
import com.highq.labels.collaborate.FileLabels;
import com.highq.pageobjects.base.DocumentPage;
import com.highq.pageobjects.pages.BannerPageWeb;

public class BaseFileTest extends BannerPageWeb
{
	DocumentPage documentWeb;
	String[] addMenuItems = {FileLabels.FILES_ADDDROPDOWN_FILES, FileLabels.FILES_ADDDROPDOWN_ZIPPEDFILES, FileLabels.FILES_ADDDROPDOWN_PLACEHOLDERFILES, FileLabels.FILES_ADDDROPDOWN_FOLDER};

	public BaseFileTest(WebDriver driver)
	{
		this.driver = driver;
	}

	public void addFile(String fileName)
	{
		documentWeb.selectItemFromAdd(addMenuItems[0]);
		documentWeb.uploadFileInAddFiles(fileName);
		documentWeb.clickAddInModal();
	}

	public void addFolderInRoot(String folderName, String description)
	{
		documentWeb.selectItemFromAdd(addMenuItems[3]);
		documentWeb.createNewFolderInRoot(folderName, description);
		documentWeb.clickAddInModal();
	}

}
