package login;


import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cit.ie.base.HelperMethods;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.selenium.*;


public class LoginPageTests extends WebDriverManager {


	@Test
	private void _001VerifyExistanceOfPageElements() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		//ExpectedConditions.presenceOfElementLocated(locator)
		LoginPagePO po=PageFactory.initElements(getDriver(),LoginPagePO.class);
		//Assert.assertTrue(po.loginButton.isDisplayed());
		po.waitForLoginPageObjects();
		Assert.assertTrue(po.loginLogo.isDisplayed());
		//Assert.assertTrue(po.loginPageTitle.isDisplayed());
		//Assert.assertTrue(po.passwordField.isDisplayed());
		//Assert.assertTrue(po.usernameField.isDisplayed());
		//Assert.assertTrue(po.versionNumber.isDisplayed());
	}
	
	
	
	
	//@Test
	private void _0002() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=new LoginPagePO(getDriver());
		Assert.assertTrue(po.loginPage().isDisplayed());
	}
	
	
	
}
