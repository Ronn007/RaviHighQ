package com.highq.test.systemAdmin;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.highq.labels.collaborate.SystemAdminLabels;
import com.highq.pageobjects.base.AspAdminPage;
import com.highq.pageobjects.base.AspConfigurationPage;
import com.highq.pageobjects.base.BannerPage;
import com.highq.pageobjects.base.SystemAdminPage;
import com.highq.pageobjects.base.SystemAdminSystemSettingsPage;
import com.highq.pageobjects.base.SystemAdminVocabularyPage;
import com.highq.pageobjects.pages.BannerPageWeb;

/**
 * @author ankit.motaval
 */
public class SystemAdmin_TC2666 extends BannerPageWeb
{
	String sysAdminEmail = "ravi.middha@highq.com";
	String sysAdminPassword = "Password@123";
	String enable = "ON";
	String disable = "OFF";
	String oldEmail = "POC@highq.com";
	String newEmail = "normal.user@highq.com";
	String contactusNewValue = "GDPR New Contact";
	String contactusOldValue = SystemAdminLabels.GDPR_CONTACT_USER_EMAIL_ADDRESS_LABEL;

	BannerPage bannerWeb;
	SystemAdminPage systemAdminWeb;
	SystemAdminSystemSettingsPage systemSettingsWeb;
	AspAdminPage aspAdminWeb;
	AspConfigurationPage aspConfigurationWeb;
	SystemAdminVocabularyPage systemAdminVocabularyWeb;

	@Test
	public void SystemAdminTC2666() throws InterruptedException
	{
		scenario1();
		scenario2();
		scenario3();
		scenario4();
	}

	/**
	 * @author ankit.motaval
	 * @created on 09/04/2018
	 */
	public void scenario1() throws InterruptedException
	{
		bannerWeb = login(sysAdminEmail, sysAdminPassword);
		aspAdminWeb = bannerWeb.gotoASPAdmin();
		aspConfigurationWeb = aspAdminWeb.openConfigurationPage();
		String emailValue = aspConfigurationWeb.getValueOfContactUsLink();
		systemAdminWeb = bannerWeb.gotoSystemAdmin();
		systemSettingsWeb = systemAdminWeb.gotoSystemSettings();
		systemSettingsWeb.setEnableGdprContactForFooter(enable);
		systemSettingsWeb.saveSettings();

		Assert.assertTrue(systemSettingsWeb.verifyContactUserEmailAddress(emailValue));

		Assert.assertTrue(bannerWeb.verifyContactusEmailFromFooter(emailValue));

	}

	/**
	 * @author ankit.motaval
	 * @created on 09/04/2018
	 */
	public void scenario2() throws InterruptedException
	{
		systemSettingsWeb.setEnableGdprContactForFooter(disable);
		systemSettingsWeb.saveSettings();

		Assert.assertFalse(systemSettingsWeb.isContactUserEmailTextBoxAvailabe());

		Assert.assertFalse(bannerWeb.isContactUslinkVisible());

	}

	/**
	 * @author ankit.motaval
	 * @created on 09/04/2018
	 */
	public void scenario3() throws InterruptedException
	{

		systemSettingsWeb.setEnableGdprContactForFooter(enable);
		systemSettingsWeb.editContactUsEmailAddress(newEmail);
		systemSettingsWeb.saveSettings();

		Assert.assertTrue(systemSettingsWeb.verifyContactUserEmailAddress(newEmail));

		systemSettingsWeb.editContactUsEmailAddress(oldEmail);
		systemSettingsWeb.saveSettings();
		systemSettingsWeb.goBack();
	}

	/**
	 * @author ankit.motaval
	 * @created on 09/04/2018
	 */
	public void scenario4()
	{
		systemAdminVocabularyWeb = systemAdminWeb.gotoSystemVocabularyPage();
		systemAdminVocabularyWeb.editGdprContact(contactusOldValue, contactusNewValue);
		systemAdminVocabularyWeb.saveSettings();

		Assert.assertTrue(bannerWeb.verifyContactUsLabel(contactusNewValue));

		systemAdminVocabularyWeb.editGdprContact(contactusNewValue, contactusOldValue);
		systemAdminVocabularyWeb.saveSettings();

	}

}
