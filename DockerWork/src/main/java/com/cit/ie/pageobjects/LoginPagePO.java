package com.cit.ie.pageobjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.cit.ie.base.HelperMethods;

/**
 * @name LoginPage PageObject
 * @description Page Objects for login Page
 * @author gearyk2
 */
public class LoginPagePO extends HelperMethods
{

	public LoginPagePO(WebDriver wdriver) throws InterruptedException {

		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		System.out.println("Window size = "+driver.manage().window().getSize());
		//driver.get("https://10.73.28.71:8443/univmax/jsclient/#/login");
		//driver.get("https://10.73.28.70:8443/univmax");
		driver.get(this.appURL);
		PageFactory.initElements(driver, this);
	}

	//LOCATORS
	public final String LOGIN_PAGE_TITLE_XPATH = ".//*[@class='login_product_name layout-row']";
	public final String USERNAME_FIELD_XPATH = ".//input[@name='uName']";
	public final String PASSWORD_FIELD_XPATH = ".//input[@name='pWrd']";
	public final String LOGIN_BUTTON_XPATH = ".//*/button[@aria-label='Login']";
	public final String VERSION_NUMBER_XPATH = ".//*/div[text()[contains(.,'Version')]]";
	public final String LOGIN_LOGO_XPATH = ".//*[@id='login_logo']";
	public final String BAD_LOGIN_XPATH=".//*/span[text()='Error Logging In']";

	//WEB ELEMENTS
	@FindBy(xpath=LOGIN_PAGE_TITLE_XPATH)
	public WebElement loginPageTitle;
	@FindBy(name="uName")
	public WebElement usernameField;
	@FindBy(name="pWrd")
	public WebElement passwordField;
	@FindBy(xpath=LOGIN_BUTTON_XPATH)
	public WebElement loginButton;
	@FindBy(xpath=LOGIN_LOGO_XPATH)
	public WebElement loginLogo;
	@FindBy(xpath=VERSION_NUMBER_XPATH)
	public WebElement versionNumber;
	@FindBy(xpath=BAD_LOGIN_XPATH)
	public WebElement errorLoggingIn;

	public void waitForLoginPageObjects() throws InterruptedException{
		waitForElementVisiblity(LOGIN_LOGO_XPATH);
		waitForElementVisiblity(USERNAME_FIELD_XPATH);
		waitForElementVisiblity(PASSWORD_FIELD_XPATH);
	}

	public WebElement loginPage(){
		WebElement element = (new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_PAGE_TITLE_XPATH)));
		return element;
	}

	public void doLogin(String username, String password, String sgName) throws InterruptedException{


		int count=0;
		//WHILE THIS PAGE IS PRESENT and LOGIN ATTEMPTS ARE LESS THAN 3
		//USING THIS TO WORKAROUND LOGIN BUG
		do 
		{	
			Thread.sleep(5000);
			count++;
			System.out.println("LOGIN PAGE COUNT" +count);	
			jsClickElement(usernameField);
			usernameField.clear();
			usernameField.sendKeys(username);
			jsClickElement(passwordField);
			passwordField.clear();
			passwordField.sendKeys(password);
			jsClickElement(loginButton);
			Thread.sleep(20000);
			if (loginPageTitle.isDisplayed()){
				Thread.sleep(20000);
			}
			if (loginPageTitle.isDisplayed()){
				Thread.sleep(20000);
			}
			if (loginPageTitle.isDisplayed()){
				Thread.sleep(20000);
			}
			
		}
		while(loginPageTitle.isDisplayed());


		//		Actions builder = new Actions(driver); 
		//		boolean proceed=false;
		//		int count=0;
		//		while(proceed==false){
		//		count++;
		//		System.out.println(sgName +": Try "+count+ "  -- Should not be doing this more than once");
		//		jsClickElement(usernameField);
		//		builder.clickAndHold(usernameField).sendKeys(username).release().build().perform();
		//		builder.click(usernameField).build().perform();
		//		Thread.sleep(3000);
		//		builder.sendKeys(usernameField, username);	
		//		usernameField.clear();
		//		usernameField.sendKeys(username);
		//		JavascriptExecutor executor = (JavascriptExecutor)driver;
		//		executor.executeScript("arguments[0].setAttribute('value', '" + username +"')", usernameField);
		//		Thread.sleep(1500);
		//		executor.executeScript("arguments[0].keydown();arguments[0].change();arguments[0].blur(); return true",usernameField);
		//		Thread.sleep(1500);

		//		builder.clickAndHold(passwordField).sendKeys(password).release().build().perform()		Thread.sleep(5000);
		//		builder.click(passwordField).build().perform();
		//		Thread.sleep(3000);
		//		builder.sendKeys(passwordField, password);

		//		jsClickElement(passwordField);
		//		passwordField.clear();
		//		passwordField.sendKeys(password);
		//		System.out.println("Password Field for "+sgName+" = "+passwordField.getAttribute("value"));
		//		Assert.assertTrue(passwordField.getAttribute("value").contains("smc"));
		//		executor.executeScript("arguments[0].setAttribute('value', '" + password +"')", passwordField);
		//		Thread.sleep(1500);
		//		executor.executeScript("arguments[0].keydown();arguments[0].change(); arguments[0].blur(); return true",passwordField);
		//		Thread.sleep(1500);
		//System.out.println(loginButton.isDisplayed());
		//		System.out.println("Is login button enabled to click after entering data? for : "+sgName+" :"+loginButton.isEnabled());
		//		proceed=loginButton.isEnabled();
		//		}
		//		jsClickElement(loginButton);
		//		try {
		//			Thread.sleep(1500);
		//			wait=new WebDriverWait(driver,200,4000);
		//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(USERNAME_FIELD_XPATH)));
		//			Thread.sleep(3500);
		//		} catch (UnhandledAlertException e) {
		//            System.err.println("Caught UnhandledAlertException in Wait for Element to disappear: RETRYING"); 
		//            wait=new WebDriverWait(driver,200,4000);
		//            driver.switchTo().alert().accept();
		//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(USERNAME_FIELD_XPATH)));
		//			Thread.sleep(3500);
		//        }
		//		Thread.sleep(3000);
	}

}
