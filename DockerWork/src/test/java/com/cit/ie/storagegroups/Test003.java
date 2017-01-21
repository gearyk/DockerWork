package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
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
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.waitForElementVisiblity(pswpo.WAIT_FOR_PAGELOAD);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		Thread.sleep(2000);
		setSrpInformation(pswpo,"GOLD");
		pswpo.volumeSize.click();
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys("12");
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		sgpo.quitWebDriver();
		pswpo.verifyAndCleanup(sgName);
		HelperMethods.printTimeFinish("TEST003");
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
				lppo.doLogin("smc","smc");lppo.waitForElementToDisappear(lppo.USERNAME_FIELD_XPATH);Thread.sleep(5000);
				Thread.sleep(5000);
				HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
				Thread.sleep(5000);
				hdpo.waitForHomeDashboardPageObjects();
				hdpo.navigateToStorageGroups();

			}
			
			/**
			 * @author gearyk2
			 * @param pswpo
			 * @param srp
			 * @throws InterruptedException
			 */
			private void setSrpInformation(ProvisionStorageWizardPO pswpo,String srp) throws InterruptedException {
				pswpo.srpListBox.click();
				Thread.sleep(2000);
				switch(srp.toLowerCase()){
				case "default_srp":
					pswpo.defaultSRP.click();
					break;
				case "srp_2":
					pswpo.srp2SRP.click();
					break;
				case "none":
					pswpo.noneSRP.click();
					break;
				default:
					pswpo.noneSRP.click();
					break;
				}
				Thread.sleep(3000);
			}
			
	
}
