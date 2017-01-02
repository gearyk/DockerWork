package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.Constants;
import com.cit.ie.base.HelperMethods;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.ChangeSrpPO;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.SetIOLimitsPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;

@SuppressWarnings("static-access")
public class StorageGroupTests extends WebDriverManager{

	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
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
	private void _000_B_VERIFY_EXISTANCE_OF_PAGE_ELEMENTS() throws JSONException, IOException, InterruptedException {
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
	private void _000_A_VERIFY_WIZARD_BUTTONS() throws JSONException, IOException, InterruptedException {
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		setSrpInformation(pswpo,"default_srp");
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		setSrpInformation(pswpo,"GOLD");
		pswpo.volumeSize.click();
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys("12");
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo, "None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sgName);
		//SET SRP AND SLO
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.createSgRunNow.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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

	@Test
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.sendKeys(sg64);
		Thread.sleep(2500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
		pswpo.storageGroupNameTextField.click();
		pswpo.storageGroupNameTextField.clear();
		pswpo.storageGroupNameTextField.sendKeys(sgMoreThan64);
		Thread.sleep(2500);
		Assert.assertTrue(pswpo.createSgRunNow.isEnabled(), "Run Now Button is Greyed in");
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		Thread.sleep(5000);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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
		pswpo.waitForElementVisiblity(pswpo.PROVISION_STORAGE_TITLE_XPATH);
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

	@Test
	private void _044_EDIT_STORAGEGROUPSTANDALONE_CHANGESRP_FROM_DEFINED_TO_NONE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK44";
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
		changeSRPInformation("None");
		Thread.sleep(5000);
		verifyAndCleanup(sgName);
	}

	@Test
	private void _045_EDIT_STORAGEGROUPSTANDALONE_CHANGESRP_ON_CHILD_SG() throws JSONException, IOException, InterruptedException {
		//THIS FUNCTIONALITY IS NOT YET DELIVERED SO I WILL JUST TEST THE BUTTONS EXISTANCE
		sgName="000DOCK45";
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
		pswpo.addStorageGroupButton.click();
		pswpo.setRowForChildSG(1);
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.sgRow(sgName).click();
		Thread.sleep(2000);
		sgpo.moreActionsStorageGroupButton.click();
		Thread.sleep(1500);
		sgpo.changeSRPButton.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		ChangeSrpPO cspo=new ChangeSrpPO(getDriver());
		cspo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
		Assert.assertTrue(cspo.csgSrpListBox.isDisplayed());
		cspo.csgSrpListBox.click();
		Thread.sleep(1500);
		Assert.assertTrue(cspo.csgSrp2Button.isDisplayed());
		Assert.assertTrue(cspo.csgDeafultSRPButton.isDisplayed());
		Thread.sleep(5000);
		verifyAndCleanup(sgName);
		verifyAndCleanup(sgName+"_1");
		verifyAndCleanup(sgName+"_2");
	}

	@Test
	private void _046_SET_IOLIMITS_VALIDATE_DATA() throws JSONException, IOException, InterruptedException {
		//THIS FUNCTIONALITY IS NOT YET DELIVERED SO I WILL JUST TEST THE BUTTONS EXISTANCE
		sgName="000DOCK46";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		sgpo.waitForStorageGroupsPageObjects();
		sgpo.sgRow(sgName).click();
		Thread.sleep(2000);
		sgpo.moreActionsStorageGroupButton.click();
		Thread.sleep(1500);
		sgpo.setIOLimitsButton.click();
		sgpo.waitForElementToDisappear(Constants.RETRIEVING);
		SetIOLimitsPO siolpo=new SetIOLimitsPO(getDriver());
		Assert.assertTrue(siolpo.hostIOcb.isDisplayed());
		Assert.assertTrue(siolpo.hostMBcb.isDisplayed());
		Assert.assertTrue(siolpo.hostIOlabel.isDisplayed());
		Assert.assertTrue(siolpo.hostMBlabel.isDisplayed());
		Thread.sleep(5000);
		verifyAndCleanup(sgName);
	}

	@Test
	private void _047_DELETE_SG_WITH_64CHARS() throws JSONException, IOException, InterruptedException {
		//CREATE THE GROUP
		sgName="0000DOCK047";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
	}

	@Test
	private void _048_DELETE_EMPTY_SG_WITH_SRP() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK48";
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
		setSrpInformation(pswpo,"default_srp");
		pswpo.createSgRunNow.click();
		//pswpo.addStorageGroupButton.click();
		//pswpo.setRowForChildSG(1);
		//pswpo.sloListBoxCSG().click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test(groups = { "delete" })
	private void _049_DELETE_EMPTY_SG_AND_VOLUME_DETAILS_DEFINED() throws JSONException, IOException, InterruptedException {

		sgName="000DOCK49";
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
		setSrpInformation(pswpo,"GOLD");
		pswpo.volumeSize.click();
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys("12");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);

	}

	@Test
	private void _050_DELETE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK50";
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
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo, "None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _051_DELETE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCFALSE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK51";
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
		//SET SRP AND SLO
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _052_DELETE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK52";
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
		//SET SRP
		pswpo.srpListBox.click();
		pswpo.noneSRP.click();
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _053_DELETE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE_PERSISTRUE() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK53";
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
		//SET SRP
		setSrpInformation(pswpo,"None");
		setSloInformation(pswpo,"None");
		//SET WORKLOAD
		//Leave as Unspecified
		pswpo.editStorageGroupIcon.click();
		pswpo.allocateCapacityCB.click();
		pswpo.persistCapacityCB.click();
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _054_DELETE_STORAGEGROUP_SRPDEFAULT_SLONONE_WLNONE_1GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK54";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"none");
		//SET WORKLOAD
		//Leave as Unspecified/NONE
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}



	@Test
	private void _055_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_1GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK55";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Diamond");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}


	@Test
	private void _056_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_0POINT5GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK56";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Diamond");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp_rep");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _057_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_200GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK57";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","200","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _058_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_0POINT5GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK58";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);

	}

	@Test
	private void _059_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLNONE_0POINT5GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK59";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","GB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _060_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLOLTP_500POINT5MB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK60";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Platinum");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","500.5","MB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);

	}

	@Test
	private void _061_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID4_WLOLTP_20000MB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK61";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Gold");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"oltp");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","20000","MB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);

	}

	@Test
	private void _062_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID5_WLDSS_150CYL() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK62";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Silver");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","150","Cyl");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _063_DELETE_STORAGEGROUP_SRPDEFAULT_SLOID6_WLDSS_MAXCYL() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK63";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Bronze");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"dss");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","99999","Cyl");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _064_DELETE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1TB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK64";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","1","TB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _065_DELETE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_0POINT5TB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK65";
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
		//SET SRP
		setSrpInformation(pswpo,"default_srp");
		//SET SLO
		setSloInformation(pswpo,"Optimized");
		//SET WORKLOAD
		setWorkloadInformation(pswpo,"None");
		//SET VOLUME INFO
		setVolumeInformation(pswpo,"1","0.5","TB");
		pswpo.createSgRunNow.click();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}
	@Test
	private void _066_DELETE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK66";
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
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _067_DELETE_STANDALONE_STORAGEGROUP_SRPDEFAULT_SLOOPTIMIZED_WLNONE_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK67";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(5000);
	}

	@Test
	private void _068_DELETE_CASCADED_STORAGEGROUP_SRPDEFAULT_2SLOS_2WLS_1GB_ALLOCT() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK68";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_1");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_2");
	}



	@Test
	private void _070_DELETE_CASCADED_STORAGEGROUP_SRPDEFAULT_4SLOS_MULTIPLEVOLS() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK70";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_1");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_2");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_3");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_4");
	}

	@Test
	private void _071_DELETE_CASCADED_STORAGEGROUP_SRPDEFAULT_5WORKLOADS() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK71";
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
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_1");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_2");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_3");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_4");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_5");
	}
	
	@Test
	private void _072_DELETE_STORAGEGROUP_SRPDEFAULT_4_DIFF_SLO_WL_COMBINATIONS() throws JSONException, IOException, InterruptedException {
		sgName="000DOCK72";
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
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		returnToStorageGroupsPage();
		pswpo.waitForElementToDisappear(Constants.RETRIEVING);
		//DELETE THE GROUP
		deleteStorageGroup(sgpo,sgName);
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_1");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_2");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_3");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_4");
		Thread.sleep(2000);
		deleteStorageGroup(sgpo,sgName+"_5");
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
	 * @description Navigate to Storage Groups Page
	 * @throws InterruptedException
	 */
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
	 * @param srp
	 * @throws InterruptedException
	 */
	private void changeSRPInformation(String srp) throws InterruptedException {
		ChangeSrpPO cspo=new ChangeSrpPO(getDriver());
		cspo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
		cspo.srpListBox.click();
		Thread.sleep(1500);
		cspo.defaultSrpButton.click();
		Thread.sleep(2500);
		cspo.srpListBox.click();
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
		Thread.sleep(1500);
		cspo.runNowButton.click();
		cspo.waitForElementToDisappear(Constants.RETRIEVING);
		Thread.sleep(1500);
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
	 * @param sgpo
	 * @param sgame
	 * @throws InterruptedException
	 */
	private void deleteStorageGroup(StorageGroupsPO sgpo, String sgname) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("DELETING : "+sgname);
		sgpo.sgRow(sgname).click();
		Thread.sleep(5000);
		sgpo.deleteStorageGroupButton.click();
		Thread.sleep(5000);
		sgpo.deleteSGPopupOKButton.click();
		sgpo.waitForElementToDisappear(sgpo.DELETE_STORAGE_GROUP_POPUP_OK_BUTTON_XPATH);
		Thread.sleep(5000);
		Assert.assertTrue(sgpo.successStorageGroupDeleted.isDisplayed());
		Thread.sleep(5000);
		sgpo.acknowledgeSGDeletedButton.click();
		Thread.sleep(3000);
	}



	@SuppressWarnings("unused")
	private void cleanUp(){
		System.out.println("Close and Quit");

	}



}
