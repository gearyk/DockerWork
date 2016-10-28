package com.cit.ie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.HelperMethods;



/**
 * @name LoginPage PageObject
 * @description Page Objects for Login Page
 * @author gearyk2
 */
public class LoginPagePO extends HelperMethods
{
	public LoginPagePO(WebDriver wdriver) {
		 super(wdriver);
	     driver = wdriver;
	     wait = new WebDriverWait(driver, timeOut);
	     driver.manage().window().maximize();
	     driver.get("http://myapp.ie");
	    }
	
	//Object Locators
	public static final String LOGIN_PAGE_TITLE_XPATH = ".//*[@class='login_product_name layout-row']";
	public static final String USERNAME_FIELD_XPATH = ".//input[@name='uName']";
	public static final String PASSWORD_FIELD_XPATH = ".//input[@name='pWrd']";
	public static final String LOGIN_BUTTON_XPATH = ".//*/button[@aria-label='Login']";

	public WebElement loginPageTitle(){
		WebElement element = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_PAGE_TITLE_XPATH)));
		return element;
	}
	
	public WebElement usernameField(){
		WebElement element = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(USERNAME_FIELD_XPATH)));
		return element;
	}
	
	public WebElement passwordField(){
		WebElement element = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(PASSWORD_FIELD_XPATH)));
		return element;
	}
	
	public WebElement loginButton(){
		WebElement element = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_BUTTON_XPATH)));
		return element;
	}
	
	
	
	
	
	
	
	
	
	
	
}
