package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.StorageGroupsPO;

public class SG1 extends WebDriverManager{

	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private WebElement el;
	private String sgName;
	/*
	 * SLO1=OPTIMIZED
	 * SLO2=DIAMOND
	 * SLO3=PLATINUM
	 * SLO4=GOLD
	 * SLO5=SILVER
	 * SLO6=BRONZE
	 * 
	 */
	
	@BeforeMethod
	private void setup() throws JSONException, IOException{
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
	}
	@Test
	private void _001_B_VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {

		
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
		Assert.assertTrue(sgpo.moreActionsStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.searchStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.filterStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.viewDetailsStorageGroupButton.isDisplayed());
		Assert.assertTrue(sgpo.storageGroupHeader.isDisplayed());
		Assert.assertTrue(sgpo.complianceHeader.isDisplayed());
		Assert.assertTrue(sgpo.srpHeader.isDisplayed());
		Assert.assertTrue(sgpo.sloHeader.isDisplayed());
		Assert.assertTrue(sgpo.capacityHeader.isDisplayed());
		Assert.assertTrue(sgpo.emulationHeader.isDisplayed());
		Assert.assertTrue(sgpo.columnFilterButton.isDisplayed());
		sgpo.haltTest();
	}
	
	@Test
	private void _001_A_VerifyWizardButtons() throws JSONException, IOException, InterruptedException {

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
		sgpo.haltTest();
	}

}
