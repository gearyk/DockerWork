package com.cit.ie.login;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.WebDriverManager;
import com.cit.ie.pageobjects.LoginPagePO;

public class L1 extends WebDriverManager {
	
	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Test1. Thread id is: " + id+ " :: thread driver get = "+threadDriver.get());
		if(threadDriver!=null)
		{ 
			System.out.println("Test1 about to find remote");
			findRemote(threadDriver.get());
		}
		getDriver().get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		Thread.sleep(5000);
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		Assert.assertTrue(po.loginLogo.isDisplayed());
		Assert.assertTrue(po.loginPageTitle.isDisplayed());
		Assert.assertTrue(po.passwordField.isDisplayed());
		Assert.assertTrue(po.usernameField.isDisplayed());
		Assert.assertTrue(po.versionNumber.isDisplayed());
	}

}
