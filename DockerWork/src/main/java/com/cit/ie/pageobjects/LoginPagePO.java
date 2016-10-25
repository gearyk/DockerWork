package com.cit.ie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.selenium.WebDriverManager;


/**
 * @name LoginPage PageObject
 * @description Page Objects for Login Page
 * @author gearyk2
 */
public class LoginPagePO extends WebDriverManager
{
    public LoginPagePO(){
    	
    }
	
	//Object Locators
	public static final String LOGIN_PAGE_TITLE = ".//*[@class='login_product_name layout-row']";

	public WebElement loginPageTitle(){
		WebElement element = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_PAGE_TITLE)));
		return element;
	}
	
	
	
	
	
}
