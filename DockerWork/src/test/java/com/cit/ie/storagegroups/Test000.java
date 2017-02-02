package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.StorageGroupsPO;

public class Test000 extends WebDriverManager{
	
	@Test(priority=1)
	private void _000_A_VERIFY_WIZARD_BUTTONS() throws JSONException, IOException, InterruptedException {
		HelperMethods.printTimeStart("Test000");
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.waitForLoginPageObjects();
		lppo.doLogin("smc","smc");lppo.waitForElementToDisappear(lppo.USERNAME_FIELD_XPATH);Thread.sleep(5000);
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		Assert.assertTrue(sgpo.createStorageGroupButton.isDisplayed());
		//Assert.assertTrue(sgpo.deleteStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.editStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.storageGroupPageTitle.isDisplayed());
		HelperMethods.printTimeFinish("TEST000");
	}

	//********************************* HELPER METHODS FOR THIS CLASS *********************************




	}
