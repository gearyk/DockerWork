package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;

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
	private void _0000VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {

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
	private void _00000VerifyWizardButtons() throws JSONException, IOException, InterruptedException {

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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
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
		Thread.sleep(10000);
		verifyAndCleanup(sgName);
		
		
	}
	
	//@Test
	private void _015_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID4_WLOLTP_20000MB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _016_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID5_WLDSS_150CYL() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _017_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID6_WLDSS_MAXCYL() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	
	//@Test
	private void _018_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1TB() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	
	//@Test
	private void _019_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_0POINT5TB() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _020_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _021_CREATE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _022_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_2SLOS_2WLS_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _023_CREATE_CASCADED_STORAGEGROUP_CHILDSG_NAME_ALREADYTAKEN() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _024_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_4SLOS_MULTIPLEVOLS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _025_CREATE_CASCADED_STORAGEGROUP_SRPDEFAULT_5WORKLOADS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _026_CREATE_STORAGEGROUP_SRPDEFAULT_4_DIFF_SLO_WL_COMBINATIONS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _027_CREATE_STORAGEGROUP_OVERPROVISION() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _028_CREATE_STORAGEGROUP_NEGATIVE_SYMID() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _029_CREATE_STORAGEGROUP_NEGATIVE_NOSGNAME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _030_CREATE_STORAGEGROUP_NEGATIVE_SGID_GREATERTHAN64_SPECIALCHARS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _031_CREATE_STORAGEGROUP_NEGATIVE_SYMID() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _032_CREATE_STORAGEGROUP_NEGATIVE_NOTEMPTY() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _033_CREATE_STORAGEGROUP_NEGATIVE_MISSING_MANDATORY() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _034_CREATE_STORAGEGROUP_NEGATIVE_INVALIDSLO() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _035_CREATE_STORAGEGROUP_NEGATIVE_INVALIDWL() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _036_CREATE_STORAGEGROUP_NEGATIVE_INVALIDSRP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _037_CREATE_STORAGEGROUP_NEGATIVE_INVALID_UNIT() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _038_CREATE_STORAGEGROUP_NEGATIVE_SRPNONE_SLOBRONZE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _039_CREATE_STORAGEGROUP_NEGATIVE_SRPNONE_SLOBRONZE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _040_CREATE_STORAGEGROUP_NEGATIVE_SRPOPTIMIZED_WLOLTP() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _041_CREATE_STANDALONE_STORAGEGROUP_NEGATIVE_ALLOCFALSE_PERSISTTRUE() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _042_EDIT_STORAGEGROUPSTANDALONE_REMOVEVOLUME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _043_EDIT_STORAGEGROUPSTANDALONE_ADDVOLUME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _044_EDIT_STORAGEGROUPSTANDALONE_ADDVOLUME() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _045_EDIT_ADD_AND_REMOVE_VOLS_FROM_CASCADED_STORAGEGROUP_DIFF_SLO() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _046_EDIT_ADD_AND_REMOVE_VOLS_FROM_CASCADED_STORAGEGROUP_DIFF_WORKLOADS() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _047_EDIT_ADD_AND_REMOVE_VOLS_FROM_CASCADED_STORAGEGROUP_NONFAST() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _048_EDIT_ADD_VOLUMES_TO_TWO_SGS_ONE_FAST_ONE_NONFAST() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _049_ADD_VOLUMESTONONFASTSG_MOVEVOLUMETO_TOFASTSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
		}
	//@Test
	private void _050_ADD_VOLUMESTOFASTSG_MOVEVOLUMETO_DIFFERENTFASTSG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
			{
			findRemote(threadDriver.get());
			}	
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
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param slo
	 */
	private void setSloInformation(ProvisionStorageWizardPO po, String slo) {
		po.sloListBox.click();
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
		pswpo.worloadListBox.click();
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
			pswpo.dss_rep.click();
			break;
		default:
			break;
		}
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
