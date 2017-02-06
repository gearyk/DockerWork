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
public class Test011 extends WebDriverManager{

	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private String sgName;	


	@Test
	private void _011_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_200GB() throws JSONException, IOException, InterruptedException {
		HelperMethods.printTimeStart("Test011");
		sgName="00DC11";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.gotoStorageGroupsPage(lppo);
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.jsClickElement(sgpo.createStorageGroupButton);
		
	
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.waitForElementVisiblity(pswpo.WAIT_FOR_PAGELOAD);
		pswpo.jsClickElement(pswpo.storageGroupNameTextField);
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		pswpo.setSrpInformation(pswpo,"default_srp");
		//SET SLO
		pswpo.setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		pswpo.setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		pswpo.setVolumeInformation(pswpo,"1","200","GB");
		pswpo.jsClickElement(pswpo.selectRunMethodMenu);
		Thread.sleep(3000);
		pswpo.jsClickElement(pswpo.createSgRunNow);
		sgpo.waitForElementToDisappear(pswpo.TASK_IN_PROCESS_XPATH);
		sgpo.quitWebDriver();
		pswpo.verifyAndCleanup(sgName);
		HelperMethods.printTimeFinish("TEST011");
		
	}
	

			
}






