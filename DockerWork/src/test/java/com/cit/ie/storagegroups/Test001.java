package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;

public class Test001 extends WebDriverManager{
	
	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private String sgName;
	
	@Test(priority=1)
	private void _001_CREATE_SG_WITH_64CHARS() throws JSONException, IOException, InterruptedException {
		HelperMethods.printTimeStart("Test001");
		sgName="012345678901234567890123456789012345678901234567890123456789ABCD";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		verifyAndCleanup(sgName);
		HelperMethods.printTimeFinish("TEST001");
	}
	//********************************* HELPER METHODS FOR THIS CLASS *********************************

		/**
		 * @author gearyk2
		 * @description Navigate to Storage Groups Page
		 * @throws InterruptedException
		 */
		private void gotoStorageGroupsPage() throws InterruptedException {
			LoginPagePO lppo=new LoginPagePO(getDriver());
			lppo.waitForLoginPageObjects();
			lppo.doLogin("smc","smc");
			Thread.sleep(5000);
			HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
			Thread.sleep(5000);
			hdpo.waitForHomeDashboardPageObjects();
			hdpo.navigateToStorageGroups();

		}
		
		/**
		 * @author gearyk2
		 * @param sgName
		 * @throws InterruptedException
		 * @description verify the response code of the RESTGET for this storage group
		 * and then call a REST DELETE for the storage group
		 */
		private void verifyAndCleanup(String sgName) throws InterruptedException {
			//VERIFY THAT GROUP HAS BEEN CREATED
			RESTClient.refreshRestDB(baseURL);
			RESTClient.GET(baseURL+sgName);
			RESTClient.printResponses();
			Assert.assertEquals(RESTClient.responseStatus,200);
			//CLEANUP
			RESTClient.DELETE(baseURL+sgName);
			RESTClient.printResponses();
			Assert.assertEquals(RESTClient.responseStatus,204);
			Thread.sleep(1000);
		}

}
