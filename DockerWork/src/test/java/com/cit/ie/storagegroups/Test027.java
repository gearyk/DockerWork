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

public class Test027 extends WebDriverManager{

	private String sgName;	



	@Test
	private void _027_CREATE_STORAGEGROUP_OVERPROVISION() throws JSONException, IOException, InterruptedException {
		sgName="00DC28";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"none");
		//SET WORKLOAD
		//Leave as Unspecified/NONE
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","999999","MB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(3000);
		pswpo.verifyAndCleanup(sgName);
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
			
			/**
			 * @author gearyk2
			 * @param pswpo
			 * @param slo
			 * @throws InterruptedException
			 */
			private void setSloInformation(ProvisionStorageWizardPO po, String slo) throws InterruptedException {
				po.sloListBox.click();
				Thread.sleep(2000);
				switch(slo.toLowerCase()){
				case "platinum":
					po.platinum.click();
					break;
				case "diamond":
					po.diamond.click();
					break;
				case "gold":
					po.gold.click();
					break;
				case "silver":
					po.silver.click();
					break;
				case "bronze":
					po.bronze.click();
					break;
				case "optimized":
					po.optimized.click();
					break;
				case "none":
					po.none.click();
					break;
				default:
					po.none.click();
					break;
				}
				Thread.sleep(1500);
			}
			
			/**
			 * @author gearyk2
			 * @param pswpo
			 * @param numberOfVolumes
			 * @param volumeSize
			 * @param volumeUnit
			 * @throws InterruptedException
			 */
			private void setVolumeInformation(ProvisionStorageWizardPO pswpo,String numberOfVolumes, String volumeSize, String volumeUnit) throws InterruptedException {
				pswpo.numberOfVolumes.click();
				Thread.sleep(1500);
				pswpo.numberOfVolumes.clear();
				pswpo.numberOfVolumes.sendKeys(numberOfVolumes);
				pswpo.volumeUnit.click();
				Thread.sleep(1500);
				switch(volumeUnit.toLowerCase()){
				case "gb":
					pswpo.GB.click();
					break;
				case "mb":
					pswpo.MB.click();
					break;
				case "tb":
					pswpo.TB.click();
					break;
				case "cyl":
					pswpo.CYL.click();
					break;
				default:
					pswpo.GB.click();
					break;
				}
				Thread.sleep(1500);
				pswpo.volumeSize.click();
				Thread.sleep(1500);
				pswpo.volumeSize.clear();
				pswpo.volumeSize.sendKeys(volumeSize);
				Thread.sleep(1500);
			}
			
			/**
			 * @author gearyk2
			 * @param pswpo
			 * @param workload
			 * @throws InterruptedException
			 */
			private void setWorkloadInformation(ProvisionStorageWizardPO pswpo,String workload) throws InterruptedException {
				pswpo.workloadListBox.click();
				Thread.sleep(1500);
				switch(workload.toLowerCase()){
				case "oltp":
					pswpo.oltp.click();
					break;
				case "oltp_rep":
					pswpo.oltp_rep.click();
					break;
				case "dss":
					pswpo.dss.click();
					break;
				case "dss_rep":
					pswpo.dss_rep.click();
					break;
				case "none":
					pswpo.no_workload.click();
					break;
				default:
					break;
				}
				Thread.sleep(1500);
			}
			
}









