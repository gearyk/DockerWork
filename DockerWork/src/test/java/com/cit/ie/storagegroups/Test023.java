package com.cit.ie.storagegroups;


import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;

@SuppressWarnings("static-access")
public class Test023 extends WebDriverManager{

	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private String sgName;	



	@Test
	private void _023_CREATE_CASCADED_STORAGEGROUP_CHILDSG_NAME_ALREADYTAKEN() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK23";
		String sgNameTaken="000DOCK23_1";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		//CREATE STANDALONE SG
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgNameTaken);
		setSrpInformation(pswpo,"default_srp");
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		//CREATE CASCADED SG WITH CHILD NAME TAKEN
		sgpo.createStorageGroupButton.click();
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		pswpo.addStorageGroupButton.click();
		pswpo.setRowForChildSG(1);
		//SET SLO ON ROW 1
		setCSGRowInformation(pswpo,"Optimized","None","MB","4","6");
		//SET SLO ON ROW 2
		pswpo.setRowForChildSG(2);
		setCSGRowInformation(pswpo,"Diamond","OLTP","MB","4","6");
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.createSgRunNow.click();
		Thread.sleep(30000);
		Assert.assertTrue(pswpo.errorSGAlreadyExists.isDisplayed());
		verifyAndCleanup(sgNameTaken);
		verifyAndCleanup(sgName);
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
				Thread.sleep(1500);
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









