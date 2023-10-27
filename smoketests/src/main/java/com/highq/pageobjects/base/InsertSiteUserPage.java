package com.highq.pageobjects.base;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import com.highq.pageobjects.pages.AddUserWeb;

public interface InsertSiteUserPage extends BannerPage
{

	public void setOrg(Map<String, List<String>> users);

	public AddUserWeb finishUserCreationProcess();

	public void setUserRoles(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles);

	public void setRoles(String domain, String userName, String role, boolean desiredState);

	public void selectPermissionCheckbox(By option, boolean check);

	public AddUserWeb assignRolesAndfinishUserCreationProcess(Map<String, Map<String, Map<String, Boolean>>> userDataAndRoles);

	public void sendInvitationToAllusers();
}
