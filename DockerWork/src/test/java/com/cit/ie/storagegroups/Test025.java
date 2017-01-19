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

@SuppressWarnings("static-access")
public class Test025 extends WebDriverManager{
	
	private String sgName;	



	@Test
	private void _025_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_5WORKLOADS() throws JSONException, IOException, InterruptedException {
		sgName="00DC25";
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
		pswpo.addStorageGroupButton.click();
		pswpo.addStorageGroupButton.click();
		pswpo.addStorageGroupButton.click();
		pswpo.addStorageGroupButton.click();
		//SET SLO ON ROW 1
		pswpo.setRowForChildSG(1);
		setCSGRowInformation(pswpo,"Optimized","None","MB","4","6");
		//SET SLO ON ROW 2
		pswpo.setRowForChildSG(2);
		setCSGRowInformation(pswpo,"Diamond","OLTP","GB","1","1");
		//SET SLO ON ROW 3
		pswpo.setRowForChildSG(3);
		setCSGRowInformation(pswpo,"Gold","OLTP_REP","CYL","2","100");
		//SET SLO ON ROW 4
		pswpo.setRowForChildSG(4);
		setCSGRowInformation(pswpo,"Bronze","DSS","MB","4","20");
		//SET SLO ON ROW 4
		pswpo.setRowForChildSG(4);
		setCSGRowInformation(pswpo,"Bronze","DSS_REP","MB","5","20");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(3000);
		pswpo.verifyAndCleanup(sgName);
		pswpo.verifyAndCleanup(sgName+"_1");
		pswpo.verifyAndCleanup(sgName+"_2");
		pswpo.verifyAndCleanup(sgName+"_3");
		pswpo.verifyAndCleanup(sgName+"_4");
		pswpo.verifyAndCleanup(sgName+"_5");
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
			
			/**
			 * @author gearyk2
			 * @param pswpo
			 * @param workload
			 * @param volumeUnit
			 * @param volumeNumber
			 * @param volumeCapacity
			 * @throws InterruptedException
			 */
			private void setCSGRowInformation(ProvisionStorageWizardPO pswpo, String slo, String workload, String volumeUnit, String volumeNumber, String volumeCapacity) throws InterruptedException {
				pswpo.sloListBoxCSG().click();
				Thread.sleep(2500);
				switch(slo.toLowerCase()){
				case "platinum":
					pswpo.platinumCSG().click();
					break;
				case "diamond":
					pswpo.diamondCSG().click();
					break;
				case "gold":
					pswpo.goldCSG().click();
					break;
				case "silver":
					pswpo.silverCSG().click();
					break;
				case "bronze":
					pswpo.bronzeCSG().click();
					break;
				case "optimized":
					pswpo.optimizedCSG().click();
					break;
				case "none":
					pswpo.noSloCSG().click();
					break;
				default:
					pswpo.noSloCSG().click();
					break;
				}
				Thread.sleep(1500);
				pswpo.workloadListBoxCSG().click();
				Thread.sleep(1500);
				switch(workload.toLowerCase()){
				case "oltp":
					pswpo.oltpCSG().click();
					break;
				case "oltp_rep":
					pswpo.oltpRepCSG().click();
					break;
				case "dss":
					pswpo.dssCSG().click();
					break;
				case "dss_rep":
					pswpo.dssRepCSG().click();
					break;
				case "none":
					pswpo.notSpecifiedWLCSG().click();
					break;
				default:
					break;
				}
				Thread.sleep(1500);
				//SET VOLUME INFO ON ROW
				pswpo.numberOfVoumesCSG().click();
				Thread.sleep(1500);
				pswpo.numberOfVoumesCSG().clear();
				Thread.sleep(1500);
				pswpo.numberOfVoumesCSG().sendKeys(volumeNumber);
				Thread.sleep(1500);
				pswpo.volumeUnitDropdownCSG().click();
				Thread.sleep(1500);
				switch(volumeUnit.toLowerCase()){
				case "gb":
					pswpo.csgGB.click();
					break;
				case "mb":
					pswpo.csgMB.click();
					break;
				case "tb":
					pswpo.csgTB.click();
					break;
				case "cyl":
					pswpo.csgCYL.click();
					break;
				default:
					pswpo.csgGB.click();
					break;
				}
				Thread.sleep(1500);
				pswpo.volumeCapacityCSG().click();
				Thread.sleep(1500);
				pswpo.volumeCapacityCSG().clear();
				Thread.sleep(1500);
				pswpo.volumeCapacityCSG().sendKeys(volumeCapacity);
				Thread.sleep(1500);
			}
			
}









