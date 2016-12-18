package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.ChangeSrpPO;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;
import com.sun.jna.platform.win32.NTSecApi.PLSA_FOREST_TRUST_INFORMATION;

public class StorageGroupTests extends WebDriverManager{

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
	
	@Test
	private void _001_B_VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
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
	}
	
	@Test
	private void _001_A_VerifyWizardButtons() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
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
	}
	
	//@Test
	private void _000VerifyRolesAndPermissions() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		
	}
	
	@Test
	private void _001_CREATE_SG_WITH_64CHARS() throws JSONException, IOException, InterruptedException {
		
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
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		pswpo.createSgRunNow.click();
		verifyAndCleanup(sgName);	
		
	}

	

	
	
	@Test
	private void _002_CREATE_EMPTY_SG_WITH_SRP() throws JSONException, IOException, InterruptedException {
			
			sgName="000DOCK02";
			if(threadDriver!=null)
			{
				findRemote(threadDriver.get());
			}
			gotoStorageGroupsPage();
			StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
			sgpo.waitForStorageGroupsPageObjects();
			sgpo.createStorageGroupButton.click();
			ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
			pswpo.elementWait(pswpo.provisionStorageTitle);
			pswpo.storageGroupNameTextField.click();
			pswpo.storageGroupNameTextField.sendKeys(sgName);
			setSrpInformation(pswpo,"default_srp");
			pswpo.createSgRunNow.click();
			//pswpo.addStorageGroupButton.click();
			//pswpo.setRowForChildSG(1);
			//pswpo.sloListBoxCSG().click();
			Thread.sleep(5000);
			verifyAndCleanup(sgName);	

	}
	
	@Test
	private void _003_CREATE_EMPTY_SG_AND_VOLUME_DETAILS_DEFINED() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK03";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		setSrpInformation(pswpo,"GOLD");
		pswpo.volumeSize.click();
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys("12");
		pswpo.createSgRunNow.click();
		Thread.sleep(5000);
		verifyAndCleanup(sgName);	
		
	}
	
	@Test
	private void _004_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK04";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo, "None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.createSgRunNow.click();
		Thread.sleep(3000);
		verifyAndCleanup(sgName);	
		
		
	}
	
	@Test
	private void _005_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCFALSE() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK05";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP AND SLO
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.createSgRunNow.click();
		Thread.sleep(3000);
		verifyAndCleanup(sgName);	
		
		
	}
	
	@Test
	private void _006_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK06";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		pswpo.srpListBox.click();
		pswpo.noneSRP.click();
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.createSgRunNow.click();
		Thread.sleep(3000);
		verifyAndCleanup(sgName);	
		
		
	}
	
	@Test
	private void _007_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE_PERSISTRUE() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK07";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.persistCapacityCB.click();
		pswpo.createSgRunNow.click();
		Thread.sleep(3000);
		verifyAndCleanup(sgName);	
		
		
	}
	
	@Test
	private void _008_CREATE_STORAGEGROUP_SRPDEFAULT_SLONONE_WLNONE_1GB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK08";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"none");
		//SET WORKLOAD
		//Leave as Unspecified/NONE
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);	
	}


	
	@Test
	private void _009_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_1GB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK09";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Diamond");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);		
	}

	
	@Test
	private void _010_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_0POINT5GB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK10";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Diamond");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp_rep");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);		
	}
	
	@Test
	private void _011_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_200GB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK11";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","200","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);		
	}
	
	@Test
	private void _012_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_0POINT5GB() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK12";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);	
		
	}
	
	@Test
	private void _013_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLNONE_0POINT5GB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK13";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);	

	}
	
	@Test
	private void _014_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLOLTP_500POINT5MB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK14";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","500.5","MB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		
		
	}
	
	@Test
	private void _015_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID4_WLOLTP_20000MB() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK15";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Gold");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","20000","MB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		
		
	}
	
	@Test
	private void _016_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID5_WLDSS_150CYL() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK16";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Silver");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","150","Cyl");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _017_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID6_WLDSS_MAXCYL() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK17";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Bronze");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","99999","Cyl");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		
		}
	
	@Test
	private void _018_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1TB() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK18";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","TB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _019_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_0POINT5TB() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK19";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","TB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	@Test
	private void _020_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK20";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _021_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK21";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _022_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_2SLOS_2WLS_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK22";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
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
		verifyAndCleanup(sgName);	
		verifyAndCleanup(sgName+"_1");	
		verifyAndCleanup(sgName+"_2");	
		}

	
	
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
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgNameTaken);
		setSrpInformation(pswpo,"default_srp");
		pswpo.createSgRunNow.click();
		Thread.sleep(20000);
		//CREATE CASCADED SG WITH CHILD NAME TAKEN
		sgpo.createStorageGroupButton.click();
		pswpo.elementWait(pswpo.provisionStorageTitle);
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
		Thread.sleep(150000);
		Assert.assertTrue(pswpo.errorSGAlreadyExists.isDisplayed());
		verifyAndCleanup(sgNameTaken);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _024_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_4SLOS_MULTIPLEVOLS() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK24";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
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
		pswpo.createSgRunNow.click();
		Thread.sleep(30000);
		verifyAndCleanup(sgName);	
		verifyAndCleanup(sgName+"_1");	
		verifyAndCleanup(sgName+"_2");	
		verifyAndCleanup(sgName+"_3");	
		verifyAndCleanup(sgName+"_4");	
		
		}
	
	@Test
	private void _025_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_5WORKLOADS() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK25";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
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
		Thread.sleep(30000);
		verifyAndCleanup(sgName);	
		verifyAndCleanup(sgName+"_1");	
		verifyAndCleanup(sgName+"_2");	
		verifyAndCleanup(sgName+"_3");	
		verifyAndCleanup(sgName+"_4");	
		verifyAndCleanup(sgName+"_5");	
		}
	//@Test
	private void _026_CREATE_STORAGEGROUP_SRPDEFAULT_4_DIFF_SLO_WL_COMBINATIONS() throws JSONException, IOException, InterruptedException {
		
		sgName="000DOCK26";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
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
		setCSGRowInformation(pswpo,"Silver","DSS","MB","4","20");
		//SET SLO ON ROW 4
		pswpo.setRowForChildSG(4);
		setCSGRowInformation(pswpo,"Bronze","DSS_REP","MB","5","20");
		pswpo.createSgRunNow.click();
		Thread.sleep(30000);
		verifyAndCleanup(sgName);	
		verifyAndCleanup(sgName+"_1");	
		verifyAndCleanup(sgName+"_2");	
		verifyAndCleanup(sgName+"_3");	
		verifyAndCleanup(sgName+"_4");	
		verifyAndCleanup(sgName+"_5");	
		}
	
	@Test
	private void _027_CREATE_STORAGEGROUP_OVERPROVISION() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK28";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
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
		Thread.sleep(20000);
		verifyAndCleanup(sgName);
		}
	
	@Test
	private void _028_CREATE_STORAGEGROUP_NEGATIVE_VERIFY_RUN_NOW_IS_GREYED_OUT() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK28";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		//NO INFORMATION PASSED TO TEST
		Assert.assertFalse(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed out");
	}
	
	@Test
	private void _029_CREATE_STORAGEGROUP_NEGATIVE_CANNOT_MOVE_TO_NEXT_PAGE_WITH_INVALID_DATA() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK29";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		//NO INFORMATION PASSED TO TEST
		Assert.assertFalse(pswpo.nextPageButton.isEnabled(), "Next Page Button is Greyed out");
	}	
	
	
	@Test
	private void _030_CREATE_STORAGEGROUP_NEGATIVE_SGID_GREATERTHAN64_SPECIALCHARS() throws JSONException, IOException, InterruptedException {
		String sg64="012345678901234567890123456789012345678901234567890123456789ABCD";
		String sgMoreThan64="012345678901234567890123456789012345678901234567890123456789ABCD";
		String sgSpecial="sg$£%";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sg64);
		Thread.sleep(2500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.clear();
		pswpo.storageGroupNameTextField.sendKeys(sgMoreThan64);
		Thread.sleep(2500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
		String x=pswpo.storageGroupNameTextField.getText();
		Assert.assertTrue(pswpo.storageGroupNameTextField.getAttribute("value").length()==64, "Max length is 64");
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.clear();
		pswpo.storageGroupNameTextField.sendKeys(sgSpecial);
		Thread.sleep(2500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
		Assert.assertTrue(pswpo.storageGroupNameTextField.getAttribute("value").length()==2, "Length is two, special chars not included");
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.clear();
		pswpo.storageGroupNameTextField.sendKeys(sg64);
		Thread.sleep(500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
		
		}
	
	@Test
	private void _038_CREATE_STORAGEGROUP_NEGATIVE_SRPNONE_SLOPLATINUM() throws Exception {
		sgName="000DOCK38";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		//SET SLO
		pswpo.sloListBox.click();
		Thread.sleep(3000);
		Assert.assertTrue(HelperMethods.assertElementNotPresent(threadDriver.get(), ProvisionStorageWizardPO.SL_PLATINUM));
	}
	
	@Test
	private void _039_CREATE_STORAGEGROUP_NEGATIVE_SRPNONE_SLOSILVER() throws Exception {
		sgName="000DOCK39";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		//SET SLO
		pswpo.sloListBox.click();
		Thread.sleep(3000);
		Assert.assertTrue(HelperMethods.assertElementNotPresent(threadDriver.get(), ProvisionStorageWizardPO.SL_SILVER));
	}
	
	@Test
	private void _040_CREATE_STORAGEGROUP_NEGATIVE_SRPOPTIMIZED_WLOLTP() throws Exception {
		sgName="000DOCK40";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		//SET SLO
		setSloInformation(pswpo, "Optimized");
		//SET WL
		pswpo.workloadListBox.click();
		Thread.sleep(3000);
		Assert.assertTrue(HelperMethods.assertElementNotPresent(threadDriver.get(), ProvisionStorageWizardPO.WL_DSS));
		}
	
	@Test
	private void _041_CREATE_STANDALONE_STORAGEGROUP_NEGATIVE_ALLOCFALSE_PERSISTTRUE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK41";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		Thread.sleep(500);
		pswpo.persistCapacityCB.click();
		Assert.assertFalse(pswpo.persistCapacityCB.isSelected());
		pswpo.allocateCapacityCB.click();
		Thread.sleep(500);
		Assert.assertTrue(pswpo.persistCapacityCB.isEnabled());
		pswpo.persistCapacityCB.click();
		Thread.sleep(500);
		
		
		}
	
	@Test
	private void _042_EDIT_STORAGEGROUPSTANDALONE_CHANGESRP() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK42";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.sgRow(sgName).click();
		Thread.sleep(5000);
		sgpo.moreActionsStorageGroupButton.click();
		Thread.sleep(1500);
		sgpo.changeSRPButton.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
		changeSRPInformation("SRP_2");
		Thread.sleep(5000);
		verifyAndCleanup(sgName);
	}

	
	@Test
	private void _043_EDIT_STORAGEGROUPSTANDALONE_CHANGESRP_ON_EMPTYSG() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK43";
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		gotoStorageGroupsPage();
		StorageGroupsPO sgpo=new StorageGroupsPO(getDriver());
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.createStorageGroupButton.click();
		ProvisionStorageWizardPO pswpo=new ProvisionStorageWizardPO(getDriver());
		pswpo.elementWait(pswpo.provisionStorageTitle);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"srp_2");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.sgRow(sgName).click();
		Thread.sleep(5000);
		sgpo.moreActionsStorageGroupButton.click();
		Thread.sleep(1500);
		sgpo.changeSRPButton.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
		changeSRPInformation("default_srp");
		Thread.sleep(5000);
		verifyAndCleanup(sgName);
	}

	//@Test
	private void _051_SET_IOLIMITS_MB_SEC() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _052_SET_IOLIMITS_IO_SEC()throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _053_PUT_SET_ALL_IO_LIMITS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _054_SET_IOLIMITS_ON_PARENTSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _055_SET_IOLIMITS_ON_CHILD_SG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _056_EDIT_WORKLOAD_STANDALONE_STORAGEGROUP()throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _057_EDIT_WORKLOAD_CHILD_STORAGEGROUP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _058_EDIT_SLO_STANDALONE_STORAGEGROUP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _059_EDIT_SLO_CHILD_STORAGEGROUP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _060_CHANGESRP_ONEMPTYSG_DEFINEDTONONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _061_CHANGESRP_ONEMPTYSG_NONETODEFINED() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _062_UPDATE_SRP_FORSTANDALONESG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _063_EDIT_SRP_CHILD_STORAGEGROUP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _064_EXPANDSTORAGE_EMPTYSTANDALONESG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}

	//@Test
	private void _065_EXPANDSTORAGE_NEWVOLSFALSE_OPTIMIZED_STANDALONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _066_EXPANDSTORAGE_NEWVOLSFALSE_SLOID2_STANDALONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _067_EXPANDSTORAGE_NEWVOLSTRUE_OPTIMIZED_CHILDSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _068_EXPANDSTORAGE_NEWVOLSFALSE_SLOID3_CHILDSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _069_EXPANDSTORAGE_NEWVOLSTRUE_NOSLO_CHILDSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _070_REMOVE_SG_FORCEFALSE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _071_REMOVE_ALL_SGS_FORCETRUE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _072_COMPRESSION_FAST() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _073_ADDEXISTINGSGTOSTANDALONE_SAMESRP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _074_ADDEXISTINGSGTOSTANDALONE_DIFFSRP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _075_ADDEXISTINGSGTOSTANDALONE_EMPTYSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _076_ADDEXISTINGSGTOCASCADED_EMPTYSG_DIFFSRP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _077_ADD_NEWSG_TO_EMPTYSG_SRPNONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _078_ADD_NEWSG_TO_SAMESRP_TO_STANDALONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}

	//@Test
	private void _079_ADD_NEWSG_TO_DIFFSRP_TO_STANDALONE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _080_ADD_NEWSG_SAMESRP_TO_CASCADED() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _081_ADD_NEWSG_DIFFSRP_TO_CASCADED() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _082_ADD_NEWSG_DEFAULTNAME_ISTAKEN_() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _083_ADD_NEWSG_4SGS_VARIOUS_SLOS_WLS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _084_RENAME_STANDALONE_WITH_SPECIALCHARS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _085_RENAME_PARENT_WITH_SPECIALCHARS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _086_RENAME_CHILD_WITH_SPECIALCHARS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _087_STORAGEGROUP_NEGATIVE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _088_ADD_REMOVE_EXISTING_SG_NEGATIVE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _089_EXPANDSTORAGE_NEGATIVE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _090_COMPRESSIONNONFAST_NEGATIVE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _091_STORAGEGROUP_DETAILS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _092_STORAGEGROUP_DETAILS_CASEINSENSITIVE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _093_STORAGEGROUP_FILTERBYBYSGID() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _094_STORAGEGROUP_FILTERBYSYMVOLUMENAME()throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _095_STORAGEGROUP_FILTERBYSLOCOMPLIANCE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _096_STORAGEGROUP_FILTERBYCAPGB() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _097_STORAGEGROUP_FILTERBYNUMOFVOLS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}

	//@Test
	private void _098_STORAGEGROUP_FILTERBYNUMOFCHILDSGS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _099() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _100_STORAGEGROUP_FILTERBYNUMOFPARENTSGS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _101_STORAGEGROUP_FILTERBYNUMOFSNAPS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	

	//@Test
	private void _102_STORAGEGROUP_FILTERBYPARENT() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _103_STORAGEGROUP_FILTERBYCHILD() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _104_STORAGEGROUP_FILTERBYSRPNAME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _105_STORAGEGROUP_FILTERBYSLONAME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _106_STORAGEGROUP_FILTERBYCHILDSGNAME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _107_STORAGEGROUP_FILTERBYPARENTSGNAME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}

		
	//@Test
	private void _108_STORAGEGROUP_FILTERBYCKD() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _109_STORAGEGROUP_FILTERBYFBA() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _110_STORAGEGROUP_FILTERBYCOMPRESSION() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _111_STORAGEGROUP_NEGATIVESYMID() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _112_STORAGEGROUP_NEGATIVESGID() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}


	private void gotoStorageGroupsPage() throws InterruptedException {
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.waitForLoginPageObjects();
		lppo.doLogin("smc","smc");
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();
		
	}
	
	private void returnToStorageGroupsPage() throws InterruptedException {
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();
		
	}
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param slo
	 * @throws InterruptedException 
	 */
	private void setSloInformation(ProvisionStorageWizardPO po, String slo) throws InterruptedException {
		po.sloListBox.click();
		Thread.sleep(1000);
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
	}

	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param srp
	 */
	private void setSrpInformation(ProvisionStorageWizardPO pswpo,String srp) {
		pswpo.srpListBox.click();
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

		
	}

	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param numberOfVolumes
	 * @param volumeSize
	 * @param volumeUnit
	 */
	private void setVolumeInformation(ProvisionStorageWizardPO pswpo,String numberOfVolumes, String volumeSize, String volumeUnit) {
		pswpo.numberOfVolumes.click();
		pswpo.numberOfVolumes.clear();
		pswpo.numberOfVolumes.sendKeys(numberOfVolumes);
		pswpo.volumeSize.click();
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys(volumeSize);
		pswpo.volumeUnit.click();
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
		
	}
	
	private void setWorkloadInformation(ProvisionStorageWizardPO pswpo,String workload) {
		pswpo.workloadListBox.click();
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
	}
	
	/**
	 * @author gearyk2
	 * @param srp
	 * @throws InterruptedException
	 */
	private void changeSRPInformation(String srp) throws InterruptedException {
		ChangeSrpPO cspo=new ChangeSrpPO(getDriver());
		cspo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
		cspo.srpListBox.click();
		Thread.sleep(1500);
		switch(srp.toLowerCase()){
		case "srp_2": 
			cspo.srp2Button.click();
			break;
		case "default_srp": 
			cspo.defaultSrpButton.click();
			break;
		case "none": 
			cspo.noneSrpButton.click();
			break;
		default:
			cspo.noneSrpButton.click();
			break;
		}
		cspo.runNowButton.click();
		cspo.waitForElementToDisappear(Constants.RETRIEVING);
		Assert.assertTrue(cspo.changeSRPSuccessMessage.isDisplayed());
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
		Thread.sleep(500);
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
		Thread.sleep(500);
		pswpo.workloadListBoxCSG().click();
		Thread.sleep(500);
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
		Thread.sleep(500);
		//SET VOLUME INFO ON ROW
		pswpo.numberOfVoumesCSG().click();
		Thread.sleep(500);
		pswpo.numberOfVoumesCSG().clear();
		Thread.sleep(500);
		pswpo.numberOfVoumesCSG().sendKeys(volumeNumber);
		Thread.sleep(500);
		pswpo.volumeUnitDropdownCSG().click();
		Thread.sleep(500);
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
		Thread.sleep(500);
		pswpo.volumeCapacityCSG().click();
		Thread.sleep(500);
		pswpo.volumeCapacityCSG().clear();
		pswpo.volumeCapacityCSG().sendKeys(volumeCapacity);
		Thread.sleep(500);
	}
	
	/**
	 * @author gearyk2
	 * @param sgName
	 * @description verify the response code of the RESTGET for this storage group
	 * and then call a REST DELETE for the storage group
	 */
	private void verifyAndCleanup(String sgName) {
		//VERIFY THAT GROUP HAS BEEN CREATED
		RESTClient.refreshRestDB(baseURL);
		RESTClient.GET(baseURL+sgName);
		RESTClient.printResponses();
		Assert.assertEquals(RESTClient.responseStatus,200);
		//CLEANUP
		RESTClient.DELETE(baseURL+sgName);
		RESTClient.printResponses();
		Assert.assertEquals(RESTClient.responseStatus,204);
	}
	
	


}
