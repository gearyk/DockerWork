package com.cit.ie.storagegroups;

import org.testng.Assert;

import com.cit.ie.rest.RESTClient;


public class CleanUpSGs extends RESTClient{
	
	private static String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";
	private String sgName;
	
	public static void main(String[] args) throws InterruptedException{
		
		GET(baseURL);
		String[] allStorageGroups=getReadableString(responseOutput, "storageGroupId").split(",");
		for(int i=0;i<allStorageGroups.length;i++){
			if (allStorageGroups[i].trim().length()>8 && allStorageGroups[i].trim().substring(0, 7).contains("000DOCK") ){
			System.out.println("DELETING SG "+allStorageGroups[i].trim());
			verifyAndCleanup(allStorageGroups[i].trim());
			}
		}
		System.out.println("END SG DELETE");
	}
	
	/**
	 * @author gearyk2
	 * @param sgName
	 * @throws InterruptedException
	 * @description verify the response code of the RESTGET for this storage group
	 * and then call a REST DELETE for the storage group
	 */
	private static void verifyAndCleanup(String sgName) throws InterruptedException {
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

}
