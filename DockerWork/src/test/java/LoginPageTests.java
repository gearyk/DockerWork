

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.selenium.*;


public class LoginPageTests {

	//LoginPagePO loginPage;
	
	@BeforeMethod
	public void setup(){
	 LoginPagePO loginPage=new LoginPagePO();
	 loginPage.getDriver().get("https://:8443/univmax/jsclient/#/login");
	 
	}
	
	
	@SuppressWarnings("static-access")
	@Test
	public void Test1() throws InterruptedException{
		
		//WebDriver driver = WebDriverManager.getInstance().getDriver();
		LoginPagePO test1=new LoginPagePO();
		//test1.getInstance().getDriver();
		//WebDriver driver=myloginPage.getInstance().getDriver();
		//Assert.assertTrue(driver.findElement(By.xpath(LoginPagePO.LOGIN_PAGE_TITLE)).isDisplayed());
		Assert.assertTrue(test1.loginPageTitle().isDisplayed());

		
	}
	
	@AfterMethod
	public void tearDown(){
		WebDriverManager.getInstance().closeDriver();
	}
}
