package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;

@SuppressWarnings("static-access")
public class Test003 extends WebDriverManager{
	
	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private String sgName;
	

	@Test(priority=1)
	private void _003_CREATE_EMPTY_SG_AND_VOLUME_DETAILS_DEFINED() throws JSONException, IOException, InterruptedException {
		HelperMethods.printTimeStart("Test003");
		sgName="00DC03";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.gotoStorageGroupsPage(lppo,sgName);
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.jsClickElement(sgpo.createStorageGroupButton);
		
		
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.waitForElementVisiblity(pswpo.WAIT_FOR_PAGELOAD);
		pswpo.jsClickElement(pswpo.storageGroupNameTextField);
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		Thread.sleep(2000);
		pswpo.setSrpInformation(pswpo,"GOLD");
		pswpo.jsClickElement(pswpo.volumeSize);
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys("12");
		pswpo.jsClickElement(pswpo.selectRunMethodMenu);
		Thread.sleep(3000);
		pswpo.jsClickElement(pswpo.createSgRunNow);
		sgpo.waitForElementToDisappear(pswpo.TASK_IN_PROCESS_XPATH);
		sgpo.quitWebDriver();
		pswpo.verifyAndCleanup(sgName);
		HelperMethods.printTimeFinish("TEST003");
	}
	

}
