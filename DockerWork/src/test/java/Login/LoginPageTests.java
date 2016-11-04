package Login;


import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.selenium.*;


public class LoginPageTests extends WebDriverManager {


	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		Assert.assertTrue(po.loginButton.isDisplayed());
		Assert.assertTrue(po.loginLogo.isDisplayed());
		Assert.assertTrue(po.loginPageTitle.isDisplayed());
		Assert.assertTrue(po.passwordField.isDisplayed());
		Assert.assertTrue(po.usernameField.isDisplayed());
		Assert.assertTrue(po.versionNumber.isDisplayed());
	}
	
	
	
	
	@Test
	private void _0002() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		po.loginButton.isDisplayed();
		Assert.assertTrue(po.loginButton.isDisplayed());
	}
	
	
	
}
