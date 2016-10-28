

import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.selenium.*;


public class LoginPageTests extends WebDriverManager {


	@Test
	private void MyTests1() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=new LoginPagePO(getDriver());
		Assert.assertTrue(po.loginButton().isDisplayed());
	}
	
	
	
	@Test
	private void Test1() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=new LoginPagePO(getDriver());
		Assert.assertTrue(po.loginButton().isDisplayed());
	}
	
	@Test
	private void Test2() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=new LoginPagePO(getDriver());
		Assert.assertTrue(po.loginButton().isDisplayed());
	}
	
	@Test
	private void Test3() throws JSONException, IOException {
		
		if(threadDriver!=null)
        {
            findRemote(threadDriver.get());
        }
		LoginPagePO po=new LoginPagePO(getDriver());
		Assert.assertTrue(po.loginButton().isDisplayed());
	}
	
	
	
}
