package com.cit.ie.storagegroups;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.pageobjects.StorageGroupsPO;
import com.cit.ie.rest.RESTClient;

public class StorageGroupTests extends WebDriverManager{

	
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
	
	//@Test
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
		
		String _64BitName="012345678901234567890123456789012345678901234567890123456789ABCD";
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
		pswpo.storageGroupNameTextField.sendKeys(_64BitName);
		pswpo.createSgRunNow.click();
		
		
		//VERIFY THAT GROUP HAS BEEN CREATED
		RESTClient.GET("https://10.73.28.231:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/"+_64BitName);
		Assert.assertEquals(RESTClient.responseStatus,200);
		//CLEANUP
		RESTClient.DELETE("https://10.73.28.231:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/"+_64BitName);
		Assert.assertEquals(RESTClient.responseStatus,204);
		
		
	}

	private void gotoStorageGroupsPage() throws InterruptedException {
		LoginPagePO lppo=new LoginPagePO(getDriver());
		lppo.waitForLoginPageObjects();
		lppo.doLogin("smc","smc");
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();
		
	}
	
	//@Test
	private void _002_CREATE_EMPTY_SG_WITH_SRP() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _003_CREATE_EMPTY_SG_AND_VOLUME_DETAILS_DEFINED() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _004_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _005_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCFALSE() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _006_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _007_CREATE_STORAGEGROUP_EMPTYSETTOTRUE_SRPNONE_SLONONE_WLNONE_ALLOCTRUE_PERSISTRUE() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _008_CREATE_STORAGEGROUP_SRPDEFAULT_SLONONE_WLNONE_1GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _009_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_1GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _010_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID2_WLOLTP_0POINT5GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _011_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_200GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _012_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLDSS_0POINT5GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _013_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLNONE_0POINT5GB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
	}
	
	//@Test
	private void _014_CREATE_STORAGEGROUP_SRPDEFAULT_SLOID3_WLOLTP_500POINT5MB() throws JSONException, IOException, InterruptedException {

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		
		
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







	
	


}
