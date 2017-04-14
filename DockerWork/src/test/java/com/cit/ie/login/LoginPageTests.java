package com.cit.ie.login;


import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;


public class LoginPageTests extends WebDriverManager {


	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {
		String sgName="L01";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test1. Thread id is: " + id+ " :: thread driver get = "+threadDriver.get());
		if(threadDriver!=null)
		{ 
			System.out.println("Test1 about to find remote");
			findRemote(threadDriver.get());
		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		System.out.println("x5555xxx");
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
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smc",sgName);
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		Assert.assertTrue(hdpo.u4vLogo.isDisplayed());
	}

	@SuppressWarnings("static-access")
	@Test
	private void _003LoginWithIncorrectCaseOnUsernameNEG() throws JSONException, IOException, InterruptedException {
		String sgName="L03";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test3. Thread id is: " + id + " :: thread driver get = "+threadDriver.get());
		if(threadDriver.get()!=null)
		{ 
			System.out.println("Test3 about to find remote");
			findRemote(threadDriver.get());
		}
		getDriver().get("https://10.60.141.19:8443/univmax/");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smc",sgName);
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}
	
	

	@Test
	@SuppressWarnings({ "static-access" })
	private void _004LoginWithIncorrectCaseOnPasswordNEG() throws JSONException, IOException, InterruptedException {
		String sgName="L04";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test4. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smC",sgName);
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	@Test
	@SuppressWarnings({ "static-access" })
	private void _005LoginWithIncorrectUsernameNEG() throws JSONException, IOException, InterruptedException {
		String sgName="L05";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test5. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("hello","smc",sgName);
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	@Test
	@SuppressWarnings({ "static-access" })
	private void _006LoginWithIncorrectPasswordNEG() throws JSONException, IOException, InterruptedException {	
		String sgName="L06";
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test6. Thread id is: " + id);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.usernameField.click();
		po.usernameField.clear();
		po.usernameField.sendKeys("smc");
		po.passwordField.click();
		po.passwordField.clear();
		po.passwordField.sendKeys("hello");
		po.loginButton.click();
		Thread.sleep(3000);
		po.waitForElementVisiblity(po.BAD_LOGIN_XPATH);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
		getDriver().quit();
		
	}



}
