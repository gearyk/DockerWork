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

		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}

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
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}

		//LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		LoginPagePO po=new LoginPagePO(getDriver());
		po.waitForLoginPageObjects();
		po.doLogin("smc","smc");
		HomeDashboardPO hdpo=new HomeDashboardPO(getDriver());
		hdpo.waitForHomeDashboardPageObjects();
		Assert.assertTrue(hdpo.u4vLogo.isDisplayed());
	}

	@Test
	private void _003LoginWithIncorrectCaseOnUsernameNEG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smC","smc");
		po.elementWait(po.errorLoggingIn);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	@Test
	private void _004LoginWithIncorrectCaseOnPasswordNEG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}

		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","smC");
		po.elementWait(po.errorLoggingIn);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	@Test
	private void _005LoginWithIncorrectUsernameNEG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}

		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("hello","smc");
		po.elementWait(po.errorLoggingIn);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}

	@Test
	private void _006LoginWithIncorrectPasswordNEG() throws JSONException, IOException, InterruptedException {
		if(threadDriver!=null)
		{
			findRemote(threadDriver.get());
		}

		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.waitForLoginPageObjects();
		po.doLogin("smc","hello");
		po.elementWait(po.errorLoggingIn);
		Assert.assertTrue(po.errorLoggingIn.getText().contains("Error Logging In"));
	}



}
