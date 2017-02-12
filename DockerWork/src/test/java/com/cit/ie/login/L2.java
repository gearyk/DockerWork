package com.cit.ie.login;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;

public class L2 extends WebDriverManager {
	
	@Test
	private void _002LoginWithDefaults() throws JSONException, IOException, InterruptedException {
		String sgName="L02";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test2. Thread id is: " + id+ " :: thread driver get = "+threadDriver.get());
		if(threadDriver.get()!=null)
		{ 
			System.out.println("Test2 about to find remote");
			findRemote(threadDriver.get());
		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		Thread.sleep(5000);
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smc",sgName);
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		Assert.assertTrue(hdpo.u4vLogo.isDisplayed());
	}

}
