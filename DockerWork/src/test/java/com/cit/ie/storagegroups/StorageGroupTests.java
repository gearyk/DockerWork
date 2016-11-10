package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.StorageGroupsPO;

public class StorageGroupTests extends WebDriverManager{


	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.waitForLoginPageObjects();
		lppo.doLogin("smc","smc");
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		Assert.assertTrue(sgpo.createStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.deleteStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.editStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.storageGroupPageTitle.isDisplayed());
	}


}
