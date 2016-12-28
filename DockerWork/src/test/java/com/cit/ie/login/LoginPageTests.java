package com.cit.ie.login;


import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;


public class LoginPageTests extends WebDriverManager {


	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test1. Thread id is: " + id+ " :: thread driver get = "+threadDriver.get());
//		if(threadDriver!=null)
//		{ 
//			System.out.println("Test1 about to find remote");
//			findRemote(threadDriver.get());
//		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		Assert.assertTrue(po.loginLogo.isDisplayed());
		Assert.assertTrue(po.loginPageTitle.isDisplayed());
		Assert.assertTrue(po.passwordField.isDisplayed());
		Assert.assertTrue(po.usernameField.isDisplayed());
		Assert.assertTrue(po.versionNumber.isDisplayed());
	}




	@Test
	private void _002LoginWithDefaults() throws JSONException, IOException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test2. Thread id is: " + id+ " :: thread driver get = "+threadDriver.get());
//		if(threadDriver.get()!=null)
//		{ 
//			System.out.println("Test2 about to find remote");
//			findRemote(threadDriver.get());
//		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smc");
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		Assert.assertTrue(hdpo.u4vLogo.isDisplayed());
	}

	@Test
	private void _003LoginWithIncorrectCaseOnUsernameNEG() throws JSONException, IOException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test3. Thread id is: " + id + " :: thread driver get = "+threadDriver.get());
//		if(threadDriver.get()!=null)
//		{ 
//			System.out.println("Test3 about to find remote");
//			findRemote(threadDriver.get());
//		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smC","smc");
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}
	
	

	//@Test
	private void _004LoginWithIncorrectCaseOnPasswordNEG() throws JSONException, IOException, InterruptedException {
		
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test4. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smC");
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	//@Test
	private void _005LoginWithIncorrectUsernameNEG() throws JSONException, IOException, InterruptedException {
		
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test5. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("hello","smc");
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	//@Test
	@SuppressWarnings("static-access")
	private void _006LoginWithIncorrectPasswordNEG() throws JSONException, IOException, InterruptedException {
		
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test6. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","hello");
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}



}
