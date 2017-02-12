package com.cit.ie.storagegroups;


import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;


public class Test024 extends WebDriverManager{

	private String sgName;	



	@Test
	private void _024_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLNONE_0POINT5GB() throws JSONException, IOException, InterruptedException {
		sgName="00DC24";
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
		//SET SRP
		pswpo.setSrpInformation(pswpo,"default_srp");
		//SET SLO
		pswpo.setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		pswpo.setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		pswpo.setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.jsClickElement(pswpo.selectRunMethodMenu);
		Thread.sleep(3000);
		pswpo.jsClickElement(pswpo.createSgRunNow);
		sgpo.waitForElementToDisappear(pswpo.TASK_IN_PROCESS_XPATH);
		sgpo.quitWebDriver();
		pswpo.verifyAndCleanup(sgName);
		
	}
			
}









