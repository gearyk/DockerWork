package com.cit.ie.login;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.LoginPagePO;

public class L3 extends WebDriverManager {
	
	@SuppressWarnings("static-access")
	@Test
	private void _003LoginWithIncorrectCaseOnUsernameNEG() throws JSONException, IOException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test3. Thread id is: " + id + " :: thread driver get = "+threadDriver.get());
		if(threadDriver.get()!=null)
		{ 
			System.out.println("Test3 about to find remote");
			findRemote(threadDriver.get());
		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		Thread.sleep(5000);
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smC","smc");
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

}
